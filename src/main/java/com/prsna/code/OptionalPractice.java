package com.prsna.code;

import java.util.Optional;

public class OptionalPractice {

	public static void main(String args[]){
		//Optional<Integer> optionalInt = getInteger("12");
		Optional<Integer> optionalInt = getInteger("abc");
		if (optionalInt.isPresent())
			System.out.println("Successfully converted String to Integer");
		
		// If the Optional object has the value then execute the consumer FI
		optionalInt.ifPresent((n) -> System.out.println("Integer value is :"+n));
		
		// If Optional doesn t have any value then make 15 as default value.
		Integer val = optionalInt.orElse(new Integer(15));
		System.out.println("Value after Else part :"+val);
	}
	
	private static Optional<Integer> getInteger(String str){
		// Creates an empty Optional value
		Optional<Integer> result = Optional.empty();
		try{
			// If possible converts the string to integer
			 result = Optional.of(Integer.parseInt(str));			 
		}catch (NumberFormatException e){
			e.printStackTrace();						
		}
		return result;
	}
	
}
