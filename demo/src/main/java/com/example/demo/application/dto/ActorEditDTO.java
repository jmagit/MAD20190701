package com.example.demo.application.dto;

import com.example.demo.domains.entities.Actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ActorEditDTO {
	private int actorId;
	private String firstName;
	private String lastName;

	public static ActorEditDTO from(Actor source) {
		return new ActorEditDTO(
				source.getActorId(),
				source.getFirstName(),
				source.getLastName()
				);
	}
	public static Actor from(ActorEditDTO source) {
		return new Actor(
				source.getActorId(),
				source.getFirstName(),
				source.getLastName()
				);
	}
}
