package com.github.pablowinck;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class ReflectiveFieldUpdater {

	public static <T> void updateFields(String key, String value, T objectToUpdate) {
		value = sanitizeValue(value);

		try {
			if (isNestedField(key)) {
				setNestedFieldValue(objectToUpdate, key, value);
			}
			else {
				setFieldValue(objectToUpdate, key, value);
			}
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

	}

	private static String sanitizeValue(String value) {
		return "null".equalsIgnoreCase(value) ? null : value;
	}

	private static boolean isNestedField(String key) {
		return key.contains(".");
	}

	private static <T> void setNestedFieldValue(T objectToUpdate, String key, String value)
			throws IllegalAccessException {
		String[] keys = key.split("\\.");
		Object currentObject = objectToUpdate;

		for (int i = 0; i < keys.length - 1; i++) {
			Field nestedField = findFieldInClass(currentObject.getClass(), keys[i]);
			if (nestedField == null) {
				return;
			}
			nestedField.setAccessible(true);
			currentObject = nestedField.get(currentObject);
		}
		setFieldValue(currentObject, keys[keys.length - 1], value);
	}

	private static void setFieldValue(Object object, String key, String value) throws IllegalAccessException {
		Field field = findFieldInClass(object.getClass(), key);
		if (field == null) {
			return;
		}
		Object castedValue = castValueToType(field.getType(), value);
		setAccessibleAndSetValue(field, object, castedValue);
	}

	private static Field findFieldInClass(Class<?> clazz, String fieldName) {
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		return null;
	}

	private static Object castValueToType(Class<?> type, String value) {
		if (value == null) {
			return null;
		}
		return tryCastingValue(type, value);
	}

	private static Object tryCastingValue(Class<?> type, String value) {
		if (type.isAssignableFrom(Long.class) || type.isAssignableFrom(long.class)) {
			return Long.parseLong(value);
		}
		if (type.isAssignableFrom(Integer.class) || type.isAssignableFrom(int.class)) {
			return Integer.parseInt(value);
		}
		if (type.isAssignableFrom(LocalDateTime.class)) {
			return LocalDateTime.parse(value);
		}
		return value;
	}

	private static void setAccessibleAndSetValue(Field field, Object object, Object value)
			throws IllegalAccessException {
		field.setAccessible(true);
		field.set(object, value);
	}

}
