package com.blz.techstack.service;

import java.util.List;

import com.blz.techstack.DTO.TechStackDTO;
import com.blz.techstack.model.TechStackModel;



public interface ITechStackService {
	TechStackModel addtechstack(TechStackDTO techStackDTO,String token);

	TechStackModel updateTechstack(TechStackDTO techStackDTO,Long id,String token);

	List<TechStackModel> getAllTechStack(String token);
	
	TechStackModel deleteTechStack(Long id,String token);
}
