package com.rukhshan.pushNotificationDemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rukhshan.pushNotificationDemo.UserFCMCredential;
import com.rukhshan.pushNotificationDemo.client.FCMClient;

public class PushNotificationService {
	List<UserFCMCredential> users = new ArrayList<UserFCMCredential>();

	@Autowired
	FCMClient fcmClient;

	public void storeFCMToken(UserFCMCredential fcmCredential) {
		UserFCMCredential user = null;
		for (UserFCMCredential credential : users) {
			if (credential.getUsername().equals(fcmCredential.getUsername()))
				user = credential;
		}
		if (user == null) {
			user = new UserFCMCredential();
			user.setUsername(fcmCredential.getUsername());
			user.setFcmToken(fcmCredential.getFcmToken());
			users.add(user);
		} else {
			user.setFcmToken(fcmCredential.getFcmToken());
		}
	}

	public void sendNotification(String username, String message) throws Exception{
		UserFCMCredential user = null;
		for (UserFCMCredential credential : users) {
			if (credential.getUsername().equals(username))
				user = credential;
		}
		if (user == null)
			throw new Exception("User not found.");
		else
			fcmClient.send(user.getFcmToken(), message);
	}
}
