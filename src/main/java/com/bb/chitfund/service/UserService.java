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
	
	
     

	public User saveUserDetails(UserDto userDto) {

		User user = new User(); // object creation for UserEntity

		if (userDto.getId() != 0) {
			Optional<User> optional = userRepo.findById(userDto.getId());
			if (optional.isPresent()) {
				user = optional.get();
			} else {

				throw new RuntimeException(" Invalid ID :: " + userDto.getId());
			}
		}
		user.setUserCode(userDto.getUserCode());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setMobile(userDto.getMobile());
		user.setEmail(userDto.getEmail());
		user.setAddress(userDto.getAddress());
		user.setRole(userDto.getRole());
		return userRepo.save(user); // save user details to database
	}

	public List<User> getAllUsers() {
		
		return userRepo.findAll();

	}

	public String deleteUser(int id) {

		userRepo.deleteById(id);
		return "  Record Deleted  " + id;
	}

	public User checkUserDetails(UserDto userDto) {
		User userData = null;
		String userCode = userDto.getUserCode();

		userData = userRepo.findByUserCode(userCode);
		if (userData != null) {
			if (userDto.getPassword().equals(userData.getPassword())) {
				return userData;
			} else {
				throw new RuntimeException(" Invalid Password :: " + userDto.getPassword());

			}
		} else {
			throw new RuntimeException(" Invalid UserCode :: " + userDto.getUserCode());

		}

	}

	public List<String> showUserCode(String schemeName) {

		Scheme scheme = schemeRepo.findBySchemeName(schemeName);
		int schemeId = scheme.getId();
		List<String> userCode = userRepo.getUserCode(schemeId);
		return userCode;
	}

	public List<String> findAssignedUsers() {
		List<String> userCode = userRepo.findAssignedusers();
		return userCode;
	}

	public List<String> getAssignedSchemeUser(String schemeName) {
		long schemeId = schemeRepo.findSchemeIdBySchemeName(schemeName);
		String status = "nextInstallment";
		List<String> userCodeList = userRepo.getAssignedSchemeUserById(schemeId, status);
		return userCodeList;
	}

}
