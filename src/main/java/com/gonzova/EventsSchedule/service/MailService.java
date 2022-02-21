package com.gonzova.EventsSchedule.service;

public interface MailService {

    void sendEmail(String emailTo, String subject, String message);
}
