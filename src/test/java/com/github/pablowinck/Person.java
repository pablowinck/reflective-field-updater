package com.github.pablowinck;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Person {

	private String name;

	private int age;

	private LocalDateTime birthDate;

	private Address address;

}
