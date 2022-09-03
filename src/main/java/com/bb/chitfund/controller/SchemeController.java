package com.bb.chitfund.controller;

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

import com.bb.chitfund.dto.SchemeDto;
import com.bb.chitfund.dto.UserSchemeCountDto;
import com.bb.chitfund.entity.Scheme;
import com.bb.chitfund.service.SchemeService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class SchemeController {

	@Autowired
	public SchemeService schemeService;

	@PostMapping("/addSchemeDetails/save")
	@ResponseBody
	public String insertSchemeDetails(@RequestBody SchemeDto schemeDto) {

		schemeService.insertSchemeDetails(schemeDto);
		return "Scheme inserted successfully";
	}

	@GetMapping("/scheme-details/showAll")
	@ResponseBody
	public List<UserSchemeCountDto> findAllUsers() {

		return schemeService.getAllSchemes();
		
	}

	@GetMapping("/scheme-details/delete/{id}")
	public String deleteScheme(@PathVariable("id") int id) {
		schemeService.deleteScheme(id);
		return "Scheme record deleted";
	}

	@GetMapping("/getInstallmentAmount/bySchemeName")
	@ResponseBody
	public long getSchemeAmount(@RequestParam(name = "schemeName") String schemeName) {
		return schemeService.getSchemeAmount(schemeName);
		

	}

	@GetMapping("/getSchemeName/schemeNameList")
	@ResponseBody
	public List<String> getSchemeNameList() {
		return schemeService.getSchemeNameList();
		
	}

	@GetMapping("/getSchemeDetails/totalProgress")
	@ResponseBody
	public List<Scheme> getSchemeDetailsProgress() {

		return schemeService.getschemeProgress();
		
	}

	@GetMapping("/getschemedetails/user")
	@ResponseBody
	public List<SchemeDto> userSchemeList(@RequestParam(name = "userCode") String userCode) {
		return schemeService.getUserSchemeList(userCode);
		
	}

}
