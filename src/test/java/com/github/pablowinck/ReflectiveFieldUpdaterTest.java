package com.github.pablowinck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReflectiveFieldUpdaterTest {

	@Test
	@DisplayName("When updating a field, then the field is updated")
	void updateField() {
		var person = createValidPerson();
		ReflectiveFieldUpdater.updateFields("name", "hello", person);
		assertEquals("hello", person.getName());
	}

	@Test
	@DisplayName("When updating a nested field, then the nested field is updated")
	void updateNestedField() {
		var person = createValidPerson();
		ReflectiveFieldUpdater.updateFields("address.street", "hello", person);
		assertEquals("hello", person.getAddress().getStreet());
	}

	@Test
	@DisplayName("When updating a local date time field, then the local date time field is updated")
	void updateLocalDateTimeField() {
		var person = createValidPerson();
		ReflectiveFieldUpdater.updateFields("birthDate", "2021-01-01T00:00:00", person);
		assertEquals(LocalDateTime.of(2021, 1, 1, 0, 0), person.getBirthDate());
	}

	@Test
	@DisplayName("When updating a field with 'null', then the field is updated to null")
	void updateFieldWithNull() {
		var person = createValidPerson();
		ReflectiveFieldUpdater.updateFields("name", "null", person);
		assertNull(person.getName());
	}

	private Person createValidPerson() {
		return Person.builder()
			.name("test")
			.age(42)
			.birthDate(LocalDateTime.of(2020, 1, 1, 0, 0))
			.address(Address.builder()
				.street("test street")
				.city("test city")
				.country("test country")
				.zipCode("test zip code")
				.build())
			.build();
	}

}