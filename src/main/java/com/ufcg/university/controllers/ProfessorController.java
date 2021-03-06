package com.ufcg.university.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.university.annotations.ProfessorOperation;
import com.ufcg.university.dto.ProfessorDTO;
import com.ufcg.university.services.ProfessorService;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(value = "/professor")
@SecurityRequirement(name = "Authorization")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Operation(
			summary = "List All Professors",
			description = "List all professors from the system",
			responses = {
				    @ApiResponse(
				    		responseCode = "200", 
				    		description = "Request OK",
				    		headers = {
				    				@Header(
				    						name = "Header",
				    						description = "Return Request Informations",
				    						schema = @Schema(
				    								// MANY THINGS TO SET UP
				    						),
				    						required = true
				    				)
				    		}
				    ),
				    @ApiResponse(responseCode = "400", description = "Bad Request"),
				    @ApiResponse(responseCode = "403", description = "Forbidden")
			},
			extensions = {
					@Extension(
						name = "Request Info",
						properties = {
								@ExtensionProperty(
										name = "response info", 
										value = "Professor List"
								),
								@ExtensionProperty(
										name = "author", 
										value = "splab.ufcg"
								),
								@ExtensionProperty(
										name = "anything", 
										value = "anything value"
								)
						}
				)
			},
			externalDocs = @ExternalDocumentation(description = "Documentation", url = "www.ufcg.com")
	)
	public ResponseEntity<List<ProfessorDTO>> getAllProfessors() {
		return new ResponseEntity<>(this.professorService.listProfessors(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ProfessorOperation
	public ResponseEntity<?> getProfessorById(@PathVariable("id") Long id) {
		
		try {
			return new ResponseEntity<>(this.professorService.getProfessorById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Professor Not Found", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	@Operation(
			summary = "Get Professor By Id",
			description = "Get professor info by id",
			responses = {
				    @ApiResponse(responseCode = "200", description = "It's Ok"),
				    @ApiResponse(responseCode = "404", description = "Professor Not Found")
			}
	)
	public ResponseEntity<?> deleteProfessorById(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(this.professorService.deleteProfessor(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Professor Not Found", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@Operation(
			summary = "Update Professor By Id",
			description = "Update professor info by id",
			responses = {
				    @ApiResponse(responseCode = "200", description = "It's Ok"),
				    @ApiResponse(responseCode = "404", description = "Professor Not Found")
			}
	)
	public ResponseEntity<?> updateProfessorById(@RequestParam("id") Long id, @RequestBody ProfessorDTO professorDTO) {
		try {
			return new ResponseEntity<>(this.professorService.updateProfessor(id, professorDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Professor Not Found", HttpStatus.NOT_FOUND);
		}
	}
}
