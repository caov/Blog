package com.van.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @author van
	 * 对象转换为json字符串
	 */
	public static String toJson(Object object) {

		String value = null;
		try {
			value = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * @author van
	 * 将json字符串转换为对象
	 */
	public static <T> T toObject(String jsonString, Class<T> classType) {
		if (null == jsonString)
			return null;
		try {
			return (T) objectMapper.readValue(jsonString, classType);
		}  catch (Exception e) {

		}

		return null;
	}
	
	/**
	 * @author van
	 * 将json字符串转换为list
	 */
	public static <T> List<T> toObjectList(String jsonString, Class<?> classType) {
		if (null == jsonString)
			return null;
		JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(ArrayList.class, ArrayList.class,classType);
		try {
			return objectMapper.readValue(jsonString, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
