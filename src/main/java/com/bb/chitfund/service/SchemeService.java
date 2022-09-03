package com.bb.chitfund.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bb.chitfund.dto.SchemeDto;
import com.bb.chitfund.dto.UserSchemeCountDto;
import com.bb.chitfund.entity.AssignScheme;
import com.bb.chitfund.entity.Scheme;
import com.bb.chitfund.entity.User;
import com.bb.chitfund.repository.AssignSchemeRepository;
import com.bb.chitfund.repository.SchemeRepository;
import com.bb.chitfund.repository.UserRepository;

@Service
public class SchemeService {

	@Autowired
	public AssignSchemeRepository assignSchemeRepo;

	@Autowired
	public SchemeRepository schemeRepo;

	@Autowired
	public UserRepository userRepo;

	public Scheme insertSchemeDetails(SchemeDto schemeDto) {

		Scheme scheme = new Scheme(); // object creation for schemeEntity

		if (schemeDto.getId() != 0) {
			Optional<Scheme> optional = schemeRepo.findById(schemeDto.getId());
			if (optional.isPresent()) {
				scheme = optional.get();
			} else {
				throw new RuntimeException(" Invalid ID :: " + schemeDto.getId());
			}
		}

		scheme.setSchemeName(schemeDto.getSchemeName());
		scheme.setSchemeAmount(schemeDto.getSchemeAmount());
		scheme.setNumberOfUser(schemeDto.getNumberOfUser());
		scheme.setPayAmount(schemeDto.getPayAmount());

		scheme.setSchemeDuration(schemeDto.getSchemeDuration());
		scheme.setStartDate(schemeDto.getStartDate());
		scheme.setEndDate(schemeDto.getEndDate());

		return schemeRepo.save(scheme);
	}

	public List<UserSchemeCountDto> getAllSchemes() {

		List<Scheme> schemeList = schemeRepo.findAll();
		List<UserSchemeCountDto> userCountList = new ArrayList<>();

		for (Scheme list : schemeList) {

			UserSchemeCountDto userCountDto = new UserSchemeCountDto();

			if (list.getAssignScheme() != null) {
				int userCount = list.getAssignScheme().size();
				userCountDto.setSchemeUserCount(userCount);
			} else {
				userCountDto.setSchemeUserCount(0);
			}
			userCountDto.setId(list.getId());
			userCountDto.setSchemeName(list.getSchemeName());
			userCountDto.setSchemeAmount(list.getSchemeAmount());
			userCountDto.setNumberOfUser(list.getNumberOfUser());
			userCountDto.setPayAmount(list.getPayAmount());
			userCountDto.setSchemeDuration(list.getSchemeDuration());
			userCountDto.setStartDate(list.getStartDate());
			userCountDto.setEndDate(list.getEndDate());
			userCountList.add(userCountDto);

		}

		return userCountList;

	}

	public String deleteScheme(int id) {

		schemeRepo.deleteById(id);
		return "  Record Deleted  ";
	}



	public long getSchemeAmount(String schemeName) {
		long schemeAmount = schemeRepo.findInstallmentAmount(schemeName);
		return schemeAmount;
	}

	public List<String> getSchemeNameList() {
		List<String> schemeList = schemeRepo.findSchemeName();
		return schemeList;
	}

	public List<Scheme> getschemeProgress() {

		List<Scheme> schemeProgressList = schemeRepo.findAll();
		return schemeProgressList;
	}

	public List<SchemeDto> getUserSchemeList(String userCode) {
		User user = userRepo.findByUserCode(userCode);
		List<AssignScheme> assignScheme = user.getAssignScheme();

		List<SchemeDto> schemeDtoList = new ArrayList<>();
		for (AssignScheme assiScheme : assignScheme) {
			SchemeDto schemelist = new SchemeDto();
			Scheme scheme = assiScheme.getScheme();

			schemelist.setId(scheme.getId());
			schemelist.setSchemeName(scheme.getSchemeName());
			schemelist.setSchemeAmount(scheme.getSchemeAmount());
			schemelist.setNumberOfUser(scheme.getNumberOfUser());
			schemelist.setPayAmount(scheme.getPayAmount());
			schemelist.setSchemeDuration(scheme.getSchemeDuration());
			schemelist.setStartDate(scheme.getStartDate());
			schemelist.setEndDate(scheme.getEndDate());

			schemeDtoList.add(schemelist);

		}

		return schemeDtoList;
	}

}
