package com.syxt.notification.service;

import javax.mail.MessagingException;

import com.syxt.notification.domain.NotificationType;
import com.syxt.notification.domain.Recipient;

import java.io.IOException;

public interface EmailService {

	void send(NotificationType type, Recipient recipient, String attachment) throws MessagingException, IOException;

}
