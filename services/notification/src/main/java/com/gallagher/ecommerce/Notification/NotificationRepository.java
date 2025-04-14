package com.gallagher.ecommerce.Notification;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gallagher.ecommerce.kafka.payement.PaymentConfirmation;

public interface NotificationRepository extends MongoRepository<Notification, String> {
   
}
