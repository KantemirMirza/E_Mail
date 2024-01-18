package com.kani.e_mail.service.impl;

import com.kani.e_mail.service.IEmailService;
import com.kani.e_mail.utils.E_MailUtils;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {
    private static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    private static final String UTF_8_ENCODING = "UTF-8";
    private static final String EMAIL_TEMPLATE = "E-Mail Template";
    private static final String TEXT_HTML_ENCODING = "text/html" ;
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    @Async
    public void sendSimpleMaileMessage(String name, String to, String token) {
        try{
           SimpleMailMessage message = new SimpleMailMessage();
           message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
           message.setFrom(fromEmail);
           message.setTo(to);
           message.setText(E_MailUtils.getEmailMessage(name,host,token));
           javaMailSender.send(message);
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithAttachment(String name, String to, String token) {
        try{
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(E_MailUtils.getEmailMessage(name,host,token));
            FileSystemResource fort = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/fort.jpg"));
            FileSystemResource dog = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/dog.jpg"));
            FileSystemResource homework = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/homework.dock"));

            helper.addAttachment(fort.getFilename(), fort);
            helper.addAttachment(dog.getFilename(), dog);
            helper.addAttachment(homework.getFilename(), homework);

            javaMailSender.send(message);
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithEmbeddedImages(String name, String to, String token) {
        try{
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(E_MailUtils.getEmailMessage(name,host,token));
            FileSystemResource fort = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/fort.jpg"));
            FileSystemResource dog = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/dog.jpg"));
            FileSystemResource homework = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/homework.dock"));

            helper.addInline(getContentId(fort.getFilename()), fort);
            helper.addInline(getContentId(dog.getFilename()), dog);
            helper.addInline(getContentId(homework.getFilename()), homework);

            javaMailSender.send(message);
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithEmbeddedFiles(String name, String to, String token) {
        try{
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(E_MailUtils.getEmailMessage(name,host,token));
            FileSystemResource fort = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/fort.jpg"));
            FileSystemResource dog = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/dog.jpg"));
            FileSystemResource homework = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/homework.dock"));

            helper.addInline(getContentId(fort.getFilename()), fort);
            helper.addInline(getContentId(dog.getFilename()), dog);
            helper.addInline(getContentId(homework.getFilename()), homework);

            javaMailSender.send(message);
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendHtmlEmail(String name, String to, String token) {
        try{
            Context context = new Context();
            context.setVariables(Map.of("name", name, "url", E_MailUtils.verificationUrl(host,token)));
            String text = templateEngine.process(EMAIL_TEMPLATE, context);
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text,true);

//            FileSystemResource fort = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/fort.jpg"));
//            FileSystemResource dog = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/dog.jpg"));
//            FileSystemResource homework = new FileSystemResource(new File(System.getProperty("user.home") + "/downloads/image/homework.dock"));
//            helper.addAttachment(fort.getFilename(), fort);
//            helper.addAttachment(dog.getFilename(), dog);
//            helper.addAttachment(homework.getFilename(), homework);

            javaMailSender.send(message);
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token) {
        try{
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            //helper.setText(text,true);
            Context context = new Context();
            context.setVariables(Map.of("name", name, "url", E_MailUtils.verificationUrl(host,token)));
            String text = templateEngine.process(TEXT_HTML_ENCODING, context);
            // Add Html
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(text, TEXT_HTML_ENCODING);
            multipart.addBodyPart(bodyPart);

            // Add Images
            BodyPart imageBodyPart = new MimeBodyPart();
            DataSource dataSource = new FileDataSource(System.getProperty("user.home" + "/Downloads/Images/dog.jpg"));
            imageBodyPart.setDataHandler(new DataHandler(dataSource));
            imageBodyPart.setHeader("Content-ID", "Image");
            multipart.addBodyPart(imageBodyPart);

            message.setContent(multipart);

            javaMailSender.send(message);
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
    private MimeMessage getMimeMessage() {
        return javaMailSender.createMimeMessage();
    }

    private String getContentId(String filename) {
        return "<" + filename + ">";
    }
}
