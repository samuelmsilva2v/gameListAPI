package com.example.demo.application.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class UserLibraryResponseDto {

	private UUID userId;
	private UUID userLibraryId;
	private List<GameResponseDto> games;
}
