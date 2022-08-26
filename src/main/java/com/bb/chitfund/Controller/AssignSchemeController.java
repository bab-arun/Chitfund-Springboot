package com.bb.chitfund.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bb.chitfund.Dto.AssignUserListDto;
import com.bb.chitfund.Service.AssignSchemeService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class AssignSchemeController {

	@Autowired
	public AssignSchemeService assignSchemeService;

	@PostMapping("/assignUserScheme/save")
	@ResponseBody
	public String assignScheme(@RequestBody AssignUserListDto assignUserListDto) throws Exception {

		assignSchemeService.saveAssignScheme(assignUserListDto);
		return "User is Assigned for scheme";
	}

}
