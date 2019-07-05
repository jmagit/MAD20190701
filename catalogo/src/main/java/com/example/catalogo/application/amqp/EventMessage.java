package com.example.catalogo.application.amqp;

import java.io.Serializable;
import java.util.Date;

import lombok.Value;

@Value
public class EventMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ACTION_ADD = "ADD";
	public static final String ACTION_MODIFY = "MODIFY";
	public static final String ACTION_REMOVE = "REMOVE";
	private String action;
	private String entity;
	private String identity;
	
	private Date enviado = new Date();
}