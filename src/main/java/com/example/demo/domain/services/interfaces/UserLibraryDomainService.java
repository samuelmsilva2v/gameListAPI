package com.example.demo.domain.services.interfaces;

import java.util.UUID;

import com.example.demo.application.dtos.UserLibraryResponseDto;

public interface UserLibraryDomainService {
	
	UserLibraryResponseDto getUserLibrary(UUID userId);
	
	UserLibraryResponseDto addGameToLibrary(UUID userId, UUID gameId);
	
	UserLibraryResponseDto removeGameFromLibrary(UUID userId, UUID gameId);
}
