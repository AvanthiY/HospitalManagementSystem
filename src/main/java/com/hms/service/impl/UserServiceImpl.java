package com.hms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hms.repository.RolesRepository;
import com.hms.repository.UserRepository;
import com.hms.service.UserService;
import com.hms.model.dto.UserDTO;
import com.hms.model.Roles;
import com.hms.model.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RolesRepository rolesRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private ModelMapper modelMapper;


	@Override
	public UserDTO addUser(String username, String passwd, String mobileNo, List<Integer> roles) {
		User user = new User();
		user.setPassword(bcryptEncoder.encode(passwd));
		user.setUsername(username);
		user.setMobileNo(mobileNo);
		
		Set<Roles> userRoles = new HashSet<>();
		for (Integer id : roles) { 
			Optional<Roles> role = rolesRepo.findById(id);
			if (role.isPresent()) {
				userRoles.add(role.get());
			}
		}
		 
		if (!CollectionUtils.isEmpty(userRoles)) {
			user.setRoles(userRoles);
		}

		user = userRepo.save(user);

		UserDTO userDTO = modelMapper.map(user, UserDTO.class);

		return userDTO;
	}


	public UserDTO getById(Long userId) {
		Optional<User> user = userRepo.findById(userId);

		if (user.isPresent()) {
			UserDTO userDTO = modelMapper.map(user.get(), UserDTO.class);

			return userDTO;
		}

		return null;

	}


	public List<UserDTO> getAllUsers(String username, int page, int size) {

		modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.LOOSE);
		
	    	List<User> users = new ArrayList<>();
	    	List<UserDTO> userDtos = new ArrayList<>();
	    	
	    	Pageable paging = PageRequest.of(page, size);
	      
	    	Page<User> pageUsers;
	    	if (username == null)
	    		pageUsers = userRepo.findAll(paging);
	    	else
	    		pageUsers = userRepo.findByUsernameContaining(username, paging);

	    	if(pageUsers.hasContent()) {
		    	users = pageUsers.getContent();
		
		    	users.stream().forEach(p-> {
			    	  UserDTO user = modelMapper.map(p, UserDTO.class);
					  userDtos.add(user);
			      });
	    	}	      
		    /*Map<String, Object> response = new HashMap<>();
	        response.put("users", userDtos);
	        response.put("currentPage", pageUsers.getNumber());
	        response.put("totalItems", pageUsers.getTotalElements());
	        response.put("totalPages", pageUsers.getTotalPages());*/
	      

	return userDtos;
	}


	public void deleteUser(Long userId) {
		Optional<User> user = userRepo.findById(userId);

		if (user.isPresent()) {
			Set<Roles> roles = (user.get().getRoles());
			if (!roles.isEmpty()){
				roles.removeAll(roles);
				 userRepo.save(user.get());
			}
		}
		userRepo.deleteById(userId);
	}

}
