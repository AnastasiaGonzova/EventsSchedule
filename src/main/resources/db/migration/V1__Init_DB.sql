CREATE TABLE public.office
(
    id uuid NOT NULL,
    country character varying(127) COLLATE pg_catalog."default" NOT NULL,
    city character varying(127) COLLATE pg_catalog."default" NOT NULL,
    street character varying(127) COLLATE pg_catalog."default" NOT NULL,
    house_number character varying(127) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT office_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.office
    OWNER to postgres;


CREATE TABLE public.room
(
    id uuid NOT NULL,
    room_name character varying(127) COLLATE pg_catalog."default" NOT NULL,
    place_number integer NOT NULL,
    coffee_machine boolean NOT NULL,
    smart_board boolean NOT NULL,
    projector boolean NOT NULL,
    office_id uuid,
    CONSTRAINT room_pkey PRIMARY KEY (id),
    CONSTRAINT room_office_id_fkey FOREIGN KEY (office_id)
        REFERENCES public.office (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.room
    OWNER to postgres;


CREATE TABLE public.employee
(
    id uuid NOT NULL,
    first_name character varying(127) COLLATE pg_catalog."default" NOT NULL,
    second_name character varying(127) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(127) COLLATE pg_catalog."default" NOT NULL,
    "position" character varying(255) COLLATE pg_catalog."default" NOT NULL,
    department character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email character varying(64) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.employee
    OWNER to postgres;


CREATE TABLE public.credential
(
    id uuid NOT NULL,
    login character varying(64) COLLATE pg_catalog."default" NOT NULL,
    pass character varying(64) COLLATE pg_catalog."default" NOT NULL,
    employee_id uuid NOT NULL,
    CONSTRAINT credential_pkey PRIMARY KEY (id),
    CONSTRAINT credential_employee_id_key UNIQUE (employee_id)
,
    CONSTRAINT credential_employee_id_fkey FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.credential
    OWNER to postgres;



CREATE TABLE public.role
(
    id uuid NOT NULL,
    name character varying(127) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.role
    OWNER to postgres;


CREATE TABLE public.employee_role
(
    employee_id uuid NOT NULL,
    role_id uuid NOT NULL,
    CONSTRAINT employee_role_pkey PRIMARY KEY (employee_id, role_id),
    CONSTRAINT employee_role_employee_id_fkey FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT employee_role_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.employee_role
    OWNER to postgres;


CREATE TABLE public.event
(
    id uuid NOT NULL,
    event_name character varying(127) COLLATE pg_catalog."default" NOT NULL,
    event_describe character varying(512) COLLATE pg_catalog."default" NOT NULL,
    start_time time(6) without time zone NOT NULL,
    end_time time(6) without time zone NOT NULL,
    planner_id uuid NOT NULL,
    room_id uuid,
    event_date date NOT NULL,
    CONSTRAINT event_pkey PRIMARY KEY (id),
    CONSTRAINT event_planner_id_fkey FOREIGN KEY (planner_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT event_room_id_fkey FOREIGN KEY (room_id)
        REFERENCES public.room (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.event
    OWNER to postgres;


CREATE TABLE public.employee_event
(
    event_id uuid NOT NULL,
    employee_id uuid NOT NULL,
    presence boolean,
    invitation_key uuid,
    CONSTRAINT employee_event_pkey PRIMARY KEY (event_id, employee_id),
    CONSTRAINT employee_event_employee_id_fkey FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT employee_event_event_id_fkey FOREIGN KEY (event_id)
        REFERENCES public.event (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.employee_event
    OWNER to postgres;