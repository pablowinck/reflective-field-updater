package com.github.pablowinck;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

	private String street;

	private String city;

	private String country;

	private String zipCode;

}
