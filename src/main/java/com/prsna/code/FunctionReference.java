package com.prsna.code;

import java.util.concurrent.Callable;

public class FunctionReference {

	@FunctionalInterface
	interface Converter<F, T>{
		T convert(F f);
	}
	
	public static void main(String[] args) {
		
		// Converter -> Typecasting can be done easily
		Converter<String, Integer> stringToIntConverter = Integer::parseInt;
		System.out.println("Converted Value / 5 : "
				+ (stringToIntConverter.convert("500") / 5));

		try {
			Callable<Integer> getInteger = () -> 100;
			System.out.println("Callable Integer :" + getInteger.call());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
