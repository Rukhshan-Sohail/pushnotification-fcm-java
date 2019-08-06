package com.rukhshan.pushNotificationDemo;

public class UserFCMCredential {
	private String username;
	private String fcmToken;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFcmToken() {
		return fcmToken;
	}
	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}
	@Override
	public String toString() {
		return "UserFCMCredential [username=" + username + ", fcmToken=" + fcmToken + "]";
	}
	
	
}
