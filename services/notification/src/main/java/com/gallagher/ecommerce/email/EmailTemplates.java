package com.gallagher.ecommerce.email;

import lombok.Getter;

public enum EmailTemplates{
      
    PAYMENT_CONFIRMATION("payment-confirmation.html", "payment success proccessed"),
    ORDER_CONFIRMATION("order-confirmation.html", "order confirmation");
    
    @Getter
    private final String templateName;

    @Getter
    private final String subject;


    EmailTemplates(String templateName, String subject) {
        this.templateName = templateName;
        this.subject = subject;
    }

}