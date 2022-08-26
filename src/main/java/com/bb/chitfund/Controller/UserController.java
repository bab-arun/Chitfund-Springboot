package com.bb.chitfund.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bb.chitfund.Dto.UserDto;
import com.bb.chitfund.Entity.User;
import com.bb.chitfund.Service.UserService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class UserController {

	@Autowired
	public UserService userService;

	@PostMapping("/user-details/save")
	@ResponseBody
	public String insertUserDetails(@RequestBody UserDto userDto) {
		userService.saveUserDetails(userDto);
		return "User Record Saved Successfully";
	}

	@GetMapping("/user-details/showAll")
	public List<User> findAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/user-details/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return "User record deleted";
	}

	@PostMapping("/usercode-password/check")
	@ResponseBody
	public User checkUserDetails(@RequestBody UserDto userDto) {
		User user = userService.checkUserDetails(userDto);
		return user;

	}

	@GetMapping("/userCode-List/show")
	@ResponseBody
	public List<String> getUserCodeList(@RequestParam(name = "schemeName") String schemeName) {
		List<String> userCodeList = userService.showUserCode(schemeName);
		return userCodeList;
	}

//	@GetMapping("/scheme-details/showAll")
//	@ResponseBody
//	public List<UserSchemeCountDto> findAllUsers() {
//		
//		List<UserSchemeCountDto> userCountDto=schemeService.getAllSchemes();
//		return userCountDto;
//	}

	@GetMapping("/listAssignedUser/userList")
	@ResponseBody
	public List<String> findAssignedUsers() {
		List<String> assignedUsers = userService.findAssignedUsers();
		return assignedUsers;
	}

	@GetMapping("/getAssignedSchemeUser/userCodeList")
	@ResponseBody
	public List<String> getAssignedSchemeUser(@RequestParam(name = "schemeName") String schemeName) {
		List<String> userCode = userService.getAssignedSchemeUser(schemeName);
		return userCode;
	}
}
