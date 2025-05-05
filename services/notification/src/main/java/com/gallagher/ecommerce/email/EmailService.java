package com.gallagher.ecommerce.email;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.gallagher.ecommerce.kafka.order.Product;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    
    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    @Async
    public void sentPaymentSuccessEmail(
        String destinationEmail,
        String customerName,
        BigDecimal amount,
        String orderReference
    ) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                     new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        helper.setFrom("judesfranck87@gallagher.com");
        helper.setTo(destinationEmail);
        helper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());

        final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplateName();

        Map<String, Object> templateModel = new HashMap<>();

        templateModel.put("customerName", customerName);
        templateModel.put("amount", amount);
        templateModel.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(templateModel);

        try {
            String html = templateEngine.process(templateName, context);
            helper.setText(html, true); // Le true indique que le contenu est HTML
            
            mailSender.send(message);

            Logger.getLogger(EmailService.class.getName()).info("Email sent successfully to " + destinationEmail);

        } catch (Exception e) {
           log.warn(destinationEmail + " template not found");
        }
    }

    @Async
    public void sentOrderConfirmationEmail(
        String destinationEmail,
        String customerName,
        BigDecimal amount,
        String orderReference,
        List<Product> products
    ) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                     new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        helper.setFrom("judesfranck87@gallagher.com");
        helper.setTo(destinationEmail);
        helper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

        final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplateName();

        Map<String, Object> templateModel = new HashMap<>();

        templateModel.put("customerName", customerName);
        templateModel.put("totalAmount", amount);
        templateModel.put("orderReference", orderReference);
        templateModel.put("products", products);

        Context context = new Context();
        context.setVariables(templateModel);

        try {
            String html = templateEngine.process(templateName, context);
            helper.setText(html, true); // Le true indique que le contenu est HTML
            
            mailSender.send(message);

            Logger.getLogger(EmailService.class.getName()).info("Email sent successfully to " + destinationEmail);

        } catch (Exception e) {
           log.warn(destinationEmail + " template not found");
        }
    }
}