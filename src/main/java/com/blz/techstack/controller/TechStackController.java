package com.blz.techstack.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.blz.techstack.DTO.TechStackDTO;
import com.blz.techstack.model.TechStackModel;
import com.blz.techstack.service.ITechStackService;
import com.blz.techstack.util.Response;


/**
 * Purpose: Techstack controller to process Techstack Data APIs.
 * @version: 4.15.1.RELEASE
 * @author: Pavan Kumar G V  
 */
@RestController
@RequestMapping("/techstack")
public class TechStackController {
	
	@Autowired
	ITechStackService techStackService;
	
	/**
	 * Purpose: To create TechStack data 
	 * @Param: techStackDTO, token and id
	 */
	@PostMapping("/addtechstack")
	public ResponseEntity<Response> addtechstack(@Valid@RequestBody TechStackDTO techStackDTO, @RequestHeader String token) {
		TechStackModel techStackModel = techStackService.addtechstack(techStackDTO, token);
		Response response = new Response("TechStack inserted successfully", 200, techStackModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	/**
	 * Purpose: To update TechStack details by id
	 * @Param: techStackDTO, token and id
	 */
	@PutMapping("/updatetechstack/{id}")
	public ResponseEntity<Response> updateTechstack(@RequestBody TechStackDTO techStackDTO,@PathVariable Long id, @RequestHeader String token) {
		TechStackModel techStackModel = techStackService.updateTechstack(techStackDTO,id, token);
		Response response = new Response("TechStack updated successfully", 200, techStackModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	/**
	 * Purpose: To get all TechStack data 
	 * @Param: token
	 */
	@GetMapping("/getalltechstack")
	public ResponseEntity<Response> getAllTechStack(@RequestHeader String token) {
		List<TechStackModel> techStackModel = techStackService.getAllTechStack(token);
		Response response = new Response("getting all the TechStack details successfully", 200, techStackModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Purpose: To delete TechStack data by id
	 * @Param: id and token
	 */
	@DeleteMapping("deletetechstack/{id}")
	public ResponseEntity<Response> deleteTechStack(@PathVariable Long id, @RequestHeader String token) {
		TechStackModel techStackModel = techStackService.deleteTechStack(id, token);
		Response response = new Response("TechStack deleted successfully", 200, techStackModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
