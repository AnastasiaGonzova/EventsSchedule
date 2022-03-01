insert into employee_role
    values('e608689c-f795-4aae-bff2-37a2ff908c0d', 'e608689c-f795-4aae-bff2-37a2ff908c0a');

ALTER TABLE employee
    ADD COLUMN is_active boolean;

ALTER TABLE employee
    ADD COLUMN activation_key uuid;

UPDATE employee
    SET is_active = 'true', activation_key = null
    WHERE id = 'e608689c-f795-4aae-bff2-37a2ff908c0d';