package com.blz.techstack.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blz.techstack.DTO.TechStackDTO;
import com.blz.techstack.exception.CustomNotFoundException;
import com.blz.techstack.model.TechStackModel;
import com.blz.techstack.repository.TechStackRepository;
import com.blz.techstack.util.TokenUtil;


@Service
public class TechStackService implements ITechStackService {

	@Autowired
	TechStackRepository techStackRepository;

	@Autowired
	TokenUtil tokenUtil;

//	@Autowired
//	AdminRepository adminRepository;

//	@Autowired
//	MailService mailService;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public TechStackModel addtechstack(TechStackDTO techStackDTO,String token) {
//		Long admId = tokenUtil.decodeToken(token);
//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
//		if(isTokenPresent.isPresent()) {
//			List<AdminModel> admin = new ArrayList<>();
//			id.stream().forEach(adminId -> {
//				Optional<AdminModel> isIdPresent = adminRepository.findById(adminId);
//				if(isIdPresent.isPresent()) {
//					admin.add(isIdPresent.get());
//				}
//			});
		boolean isUserPresent = restTemplate.getForObject("http://LMS-Admin:8067/admin/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			TechStackModel model = new TechStackModel();
//			if(admin.size()>0) {
//				model.setCreatorUser(admin);
//			}
			techStackRepository.save(model);
			return model;
		}
		throw new CustomNotFoundException(400,"Token invalid");
	}

	@Override
	public TechStackModel updateTechstack(TechStackDTO techStackDTO, Long id,String token) {
//		Long admId = tokenUtil.decodeToken(token);
//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-ADMIN:8067/admin/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<TechStackModel> isTechStackpresent = techStackRepository.findById(id);
			if(isTechStackpresent.isPresent()) {
				isTechStackpresent.get().setImagePath(techStackDTO.getImagePath());
				isTechStackpresent.get().setStatus(techStackDTO.isStatus());
				isTechStackpresent.get().setTechName(techStackDTO.getTechName());
				techStackRepository.save(isTechStackpresent.get());
				return isTechStackpresent.get();
			}
			throw new CustomNotFoundException(400,"Tech stack details not present");
		}
		throw new CustomNotFoundException(400,"Token Invalid");
	}

	@Override
	public List<TechStackModel> getAllTechStack(String token) {
//		Long admId = tokenUtil.decodeToken(token);
//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-Admin:8067/admin/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<TechStackModel> getAllTechStack = techStackRepository.findAll();
			if (getAllTechStack.size()>0) {
				return getAllTechStack;
			}else {
				throw new CustomNotFoundException(400,"Tech Stack details not present");
			}
		}
		throw new CustomNotFoundException(400,"Token invalid");
	}

	@Override
	public TechStackModel deleteTechStack(Long id,String token) {
//		Long admId = tokenUtil.decodeToken(token);
//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-Admin:8067/admin/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<TechStackModel> isTechStackPresent = techStackRepository.findById(id);
			if(isTechStackPresent.isPresent()) {
				techStackRepository.delete(isTechStackPresent.get());
				return isTechStackPresent.get();
			}
			throw new CustomNotFoundException(400,"Tech stack details not present");
		}
		throw new CustomNotFoundException(400,"Token invalid");
	}
}
