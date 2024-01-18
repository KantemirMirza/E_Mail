package com.kani.e_mail.service;

public interface IEmailService {
    void sendSimpleMaileMessage(String name, String to, String token);
    void sendMimeMessageWithAttachment(String name, String to, String token);
    void sendMimeMessageWithEmbeddedImages(String name, String to, String token);
    void sendMimeMessageWithEmbeddedFiles(String name, String to, String token);
    void sendHtmlEmail(String name, String to, String token);
    void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);
}
