package com.example.payroll.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
//@RequiredArgsConstructor
@NoArgsConstructor
public class Employee {

	private int id;

	//  @NotNull
	private String name;
	//  @NotNull
	private String role;

	@Builder
	public Employee(String name, String role) {
		this.name = name;
		this.role = role;
	}
}
