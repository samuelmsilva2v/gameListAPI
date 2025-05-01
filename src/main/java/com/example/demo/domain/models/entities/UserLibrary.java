package com.example.demo.domain.models.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class UserLibrary {

	@Id
	private UUID id;

	@ManyToOne
	private User user;

	@ManyToMany
	@JoinTable(name = "user_library_game", joinColumns = @JoinColumn(name = "user_library_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
	private List<Game> games;
}
