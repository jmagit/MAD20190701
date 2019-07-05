package com.example.catalogo.application.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificacionesMQ {
	@Autowired
	private AmqpTemplate amqp;
	
	@Value("${my.config.mq.catalogo}")
	private String QUEUE_NAME;
	

	public void sendAdd(String entity, String identity) {
		amqp.convertAndSend(QUEUE_NAME, new EventMessage(EventMessage.ACTION_ADD, entity, identity));
	}
	public void sendAdd(String entity, long identity) {
		sendAdd(entity, Long.toString(identity));
	}
	public void sendModify(String entity, String identity) {
		amqp.convertAndSend(QUEUE_NAME, new EventMessage(EventMessage.ACTION_MODIFY, entity, identity));
	}
	public void sendModify(String entity, long identity) {
		sendModify(entity, Long.toString(identity));
	}
	public void sendRemove(String entity, String identity) {
		amqp.convertAndSend(QUEUE_NAME, new EventMessage(EventMessage.ACTION_REMOVE, entity, identity));
	}
	public void sendRemove(String entity, long identity) {
		sendRemove(entity, Long.toString(identity));
	}

}
