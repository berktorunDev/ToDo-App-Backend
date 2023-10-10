package com.project.to_do_backend.util.service.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final Context defaultContext;

    public EmailService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;

        // Initialize the default context with shared data
        defaultContext = new Context();
        defaultContext.setVariable("companyName", "TODO-APP"); // Example shared data
    }

    /**
     * Send an HTML email using the default context.
     *
     * @param to           The recipient's email address.
     * @param subject      The email subject.
     * @param templateName The name of the HTML template (located in
     *                     resources/templates/).
     */
    public void sendHtmlEmail(String to, String subject, String templateName) {
        try {
            // Create a MimeMessage
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // Set the recipient email address
            helper.setTo(to);

            // Set the email subject
            helper.setSubject(subject);

            // Load the HTML template from the resources/templates directory
            // The template name is relative to the "templates" directory
            String content = templateEngine.process(templateName, defaultContext);

            // Set the HTML content of the email
            helper.setText(content, true);

            // Send the email using the JavaMailSender
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            // Handle exceptions, e.g., by logging or throwing a custom exception
            e.printStackTrace();
        }
    }
}
