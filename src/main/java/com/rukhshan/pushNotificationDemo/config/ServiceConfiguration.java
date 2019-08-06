package com.rukhshan.pushNotificationDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rukhshan.pushNotificationDemo.client.FCMClient;
import com.rukhshan.pushNotificationDemo.service.PushNotificationService;

@Configuration
public class ServiceConfiguration {

	@Bean
	public FCMClient getFcmClient() {
		return new FCMClient();
	}
	
	@Bean
	public PushNotificationService getPushNotificationService() {
		return new PushNotificationService();
	}
}
