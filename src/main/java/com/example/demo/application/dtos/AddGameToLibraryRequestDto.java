package com.example.demo.application.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class AddGameToLibraryRequestDto {

	private UUID gameId;
}
