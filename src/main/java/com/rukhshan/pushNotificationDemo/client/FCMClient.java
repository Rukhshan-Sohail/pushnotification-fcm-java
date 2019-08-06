package com.rukhshan.pushNotificationDemo.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;

public class FCMClient {
	@Value("${service-account-file}")
	private String serviceAccountFile;

	public FCMClient() {
		try {
			File initialFile = new File(
					"D:\\Office Work\\Edgeon\\pushNotificationDemo\\src\\main\\resources\\pushnotificationdemo-e582a-firebase-adminsdk-vsxtt-44a0ef25c7.json");
			InputStream serviceAccount = new FileInputStream(initialFile);
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			System.err.println("init fcm" + e);
		}
	}

//	public void send(Map<String, String> data) throws InterruptedException, ExecutionException {
//
//		Message message = Message.builder().putAllData(data).setTopic("Notification")
//				.setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
//						.setNotification(
//								new WebpushNotification("Background Title (server)", "Background Body (server)"))
//						.build())
//				.build();
//
//		String response = FirebaseMessaging.getInstance().sendAsync(message).get();
//		System.out.println("Sent message: " + response);
//	}

//	public void subscribe(String topic, String clientToken) {
//		try {
//			TopicManagementResponse response = FirebaseMessaging.getInstance()
//					.subscribeToTopicAsync(Collections.singletonList(clientToken), topic).get();
//			System.out.println(response.getSuccessCount() + " tokens were subscribed successfully");
//		} catch (InterruptedException | ExecutionException e) {
//			System.err.println("subscribe" + e);
//		}
//	}

	public void send(String fcmToken, String messageBody) throws InterruptedException, ExecutionException {
		Message message = Message.builder().setToken(fcmToken)
				.setNotification(new Notification("Sample Notification Title", "Sample Notification Body"))
				.setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
						.setNotification(
								new WebpushNotification("Background Title (server)", "Background Body (server)"))
						.build())
				.setAndroidConfig(AndroidConfig.builder()
						.setNotification(AndroidNotification.builder().setTitle("Sample Android Notification Title")
								.setBody("Sample Android Notification Body").build())
						.setTtl(300).build())
				.build();

		String response = null;
		response = FirebaseMessaging.getInstance().sendAsync(message).get();
		System.out.println("Sent message: " + response);
	}
}
