package com.hms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.model.dto.UserDTO;

@Service
public interface UserService {
	
	UserDTO addUser(String username, String passwd, String mobileNo, List<Integer> roles);

	UserDTO getById(Long userId);

	List<UserDTO> getAllUsers(String username, int page, int size);

	void deleteUser(Long userId);

}
