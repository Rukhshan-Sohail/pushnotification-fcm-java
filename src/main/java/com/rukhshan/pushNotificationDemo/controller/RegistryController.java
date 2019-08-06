package com.rukhshan.pushNotificationDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rukhshan.pushNotificationDemo.UserFCMCredential;
import com.rukhshan.pushNotificationDemo.service.PushNotificationService;

@RestController
public class RegistryController {

	@Autowired 
	PushNotificationService pushNotificationService;
	
	@PostMapping(value="/register", produces={MediaType.APPLICATION_JSON_VALUE}, consumes= {MediaType.ALL_VALUE})
	public ResponseEntity<String> register(@RequestBody UserFCMCredential userFcmCredential) {
		System.out.println(userFcmCredential);
		this.pushNotificationService.storeFCMToken(userFcmCredential);
		return new ResponseEntity<String>("FCM token registered against username",HttpStatus.OK);
	}
	
	@RequestMapping("/notify")
	public String notify(@RequestParam String username) {
		try {
			pushNotificationService.sendNotification(username, "This is a sample notification");
		} catch (Exception e) {
			System.out.println("Exception in sending notification!");
			e.printStackTrace();
			return "Exception in sending notification!"; 
		}
		return "Notification sent!";
	}
	
}
