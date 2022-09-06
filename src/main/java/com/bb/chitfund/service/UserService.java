package com.bb.chitfund.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bb.chitfund.dto.UserDto;
import com.bb.chitfund.entity.Scheme;
import com.bb.chitfund.entity.User;
import com.bb.chitfund.repository.SchemeRepository;
import com.bb.chitfund.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepo;

	@Autowired
	public SchemeRepository schemeRepo;
	
	
     

	public String saveUserDetails(UserDto userDto) {

		User user = new User(); // object creation for UserEntity

		if (userDto.getId() != 0) {
			Optional<User> optional = userRepo.findById(userDto.getId());
			if (optional.isPresent()) {
				user = optional.get();
			} else {

				
				return "Invalid Id";
			}
		}
		user.setUserCode(userDto.getUserCode());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setMobile(userDto.getMobile());
		user.setEmail(userDto.getEmail());
		user.setAddress(userDto.getAddress());
		user.setRole(userDto.getRole());
		userRepo.save(user); // save user details to database
		return "User Details Saved";
	}

	public List<User> getAllUsers() {
		
		return userRepo.findAll();

	}

	public String deleteUser(int id) {

		userRepo.deleteById(id);
		return "  Record Deleted  " + id;
	}

	public String checkUserDetails(UserDto userDto) {
		User userData = null;
		String userCode = userDto.getUserCode();

		userData = userRepo.findByUserCode(userCode);
		if (userData != null) {
			if (userDto.getPassword().equals(userData.getPassword())) {
				return userData.getRole();
				
			} else {
				return "Invalid Password";
			}
		} else {
			return "Invalid UserCode";

		}

	}

	public List<String> showUserCode(String schemeName) {

		Scheme scheme = schemeRepo.findBySchemeName(schemeName);
		int schemeId = scheme.getId();
		return userRepo.getUserCode(schemeId);
			}

	public List<String> findAssignedUsers() {
		return userRepo.findAssignedusers();
		
	}

	public List<String> getAssignedSchemeUser(String schemeName) {
		long schemeId = schemeRepo.findSchemeIdBySchemeName(schemeName);
		String status = "nextInstallment";
		return userRepo.getAssignedSchemeUserById(schemeId, status);
		
	}

}
