package com.ufcg.university.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Modelo ProfessorDTO", description = "Modelo de ProfessorDTO da aplicação.")
public class ProfessorDTO {

	@Schema(
			title = "Nome",
			description = "Nome do professor",
			example = "Pedro",
			required = true
	)
	private String name;

	@Schema(
			title = "Senha",
			description = "Senha",
			example = "1234Abcd",
			required = true,
			minLength = 8,
			maxLength = 16,
			format = "regex[0-9A-Za-z]"
	)
	private String password;

	@Schema(
			title = "Tempo de Serviço",
			example = "15",
			minimum = "0",
			maximum = "70",
			exclusiveMinimum = true
	)
	private Integer serviceTime;

	@Schema(
			title = "Disciplina",
			example = "Lógica",
			nullable = true,
			allowableValues = "Cálculo 1, P1, LP1, EDA, Lógica"
	)
	private String discipline;
	
	public ProfessorDTO() {}

	public ProfessorDTO(String name, String password, Integer serviceTime, String discipline) {
		this.name = name;
		this.password = password;
		this.serviceTime = serviceTime;
		this.discipline = discipline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
