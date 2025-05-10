package com.example.demo.domain.services.impl;

import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.GameResponseDto;
import com.example.demo.application.dtos.UserLibraryResponseDto;
import com.example.demo.domain.exceptions.GameAlreadyInLibraryException;
import com.example.demo.domain.exceptions.GameNotInLibraryException;
import com.example.demo.domain.exceptions.ResourceNotFoundException;
import com.example.demo.domain.services.interfaces.UserLibraryDomainService;
import com.example.demo.infrastructure.repositories.GameRepository;
import com.example.demo.infrastructure.repositories.UserLibraryRepository;

@Service
public class UserLibraryDomainServiceImpl implements UserLibraryDomainService {

	@Autowired
	private UserLibraryRepository userLibraryRepository;

	@Autowired
	private GameRepository gameRepository;

	@Override
	public UserLibraryResponseDto getUserLibrary(UUID userId) {

		var userLibrary = userLibraryRepository.findByUserId(userId);

		if (userLibrary == null) {
			throw new RuntimeException("Biblioteca não encontrada.");
		}

		var response = new UserLibraryResponseDto();
		response.setUserId(userLibrary.getUser().getId());
		response.setUserLibraryId(userLibrary.getId());

		response.setGames(userLibrary.getGames().stream().map(game -> {

			var gameResponse = new GameResponseDto();
			gameResponse.setId(game.getId());
			gameResponse.setTitle(game.getTitle());
			gameResponse.setDescription(game.getDescription());
			gameResponse.setGenre(game.getGenre());
			gameResponse.setPlatform(game.getPlatform());
			gameResponse.setPublisher(game.getPublisher());

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String formattedDate = formatter.format(game.getReleaseDate());
			gameResponse.setReleaseDate(formattedDate);

			gameResponse.setImageUrl(game.getImageUrl());

			return gameResponse;

		}).toList());

		return response;
	}

	@Override
	public UserLibraryResponseDto addGameToLibrary(UUID userId, UUID gameId) {

		var userLibrary = userLibraryRepository.findByUserId(userId);

		var game = gameRepository.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Jogo com ID" + gameId + " não encontrado."));

		if (userLibrary.getGames().contains(game))
			throw new GameAlreadyInLibraryException("O jogo já está na biblioteca.");

		userLibrary.getGames().add(game);

		userLibraryRepository.save(userLibrary);

		var response = new UserLibraryResponseDto();
		response.setUserId(userLibrary.getUser().getId());
		response.setUserLibraryId(userLibrary.getId());
		response.setGames(userLibrary.getGames().stream().map(gameItem -> {

			var gameResponse = new GameResponseDto();
			gameResponse.setId(gameItem.getId());
			gameResponse.setTitle(gameItem.getTitle());
			gameResponse.setDescription(gameItem.getDescription());
			gameResponse.setGenre(gameItem.getGenre());
			gameResponse.setPlatform(gameItem.getPlatform());
			gameResponse.setPublisher(gameItem.getPublisher());

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String formattedDate = formatter.format(gameItem.getReleaseDate());
			gameResponse.setReleaseDate(formattedDate);

			gameResponse.setImageUrl(gameItem.getImageUrl());

			return gameResponse;

		}).toList());

		return response;
	}

	@Override
	public UserLibraryResponseDto removeGameFromLibrary(UUID userId, UUID gameId) {

		var userLibrary = userLibraryRepository.findByUserId(userId);

		var game = gameRepository.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Jogo com ID" + gameId + " não encontrado."));
		
		if (!userLibrary.getGames().contains(game))
		    throw new GameNotInLibraryException("O jogo não está na biblioteca.");

		userLibrary.getGames().remove(game);

		userLibraryRepository.save(userLibrary);

		var response = new UserLibraryResponseDto();
		response.setUserId(userLibrary.getUser().getId());
		response.setUserLibraryId(userLibrary.getId());
		response.setGames(userLibrary.getGames().stream().map(gameItem -> {

			var gameResponse = new GameResponseDto();
			gameResponse.setId(gameItem.getId());
			gameResponse.setTitle(gameItem.getTitle());
			gameResponse.setDescription(gameItem.getDescription());
			gameResponse.setGenre(gameItem.getGenre());
			gameResponse.setPlatform(gameItem.getPlatform());
			gameResponse.setPublisher(gameItem.getPublisher());

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String formattedDate = formatter.format(gameItem.getReleaseDate());
			gameResponse.setReleaseDate(formattedDate);

			gameResponse.setImageUrl(gameItem.getImageUrl());

			return gameResponse;

		}).toList());

		return response;
	}
}
