package com.gallagher.ecommerce.Notification;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface NotificationRepository extends MongoRepository<Notification, String> {
   
}
