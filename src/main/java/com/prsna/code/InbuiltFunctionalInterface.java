package com.prsna.code;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

// Practice class to play with the inbuilt Functional Interface available
public class InbuiltFunctionalInterface {

	public static void main(String[] args){
		
		Integer val = 15;
		
		// Checks for the integer entered to be > 10. 
		// Predicate: Integer -> Boolean		
		Predicate<Integer> pred = (n) -> n > 10;
		System.out.format("Is n=%d > 10 : %b\n", val, pred.test(val));
	
		// Just consume the value. DOT.		
		Consumer<Integer> consumer = (n) -> System.out.println("Consumed "+n);
		consumer.accept(val + 10);
		
		// Same consume function is invoked again
		Consumer<Integer> consumeFirst = (n) -> System.out.println("Consumed First "+n);
		Consumer<Integer> consumeSecond = (n) -> System.out.println("Consumed Second "+n);
		consumeFirst.andThen(consumeSecond).accept(val + 20);
		
		// Function which will count the number of characters in the string (including space)
		String str = "Hello Java8";
		Function<String, Integer> countChars = (n) -> n.length();
		System.out.println("Number of Characters in "+str+" is "+countChars.apply(str));

		// Unary Operator
		UnaryOperator<Integer> incBy10 = (n) -> n+10;
		System.out.println("Incremented "+val+" by 10 = "+incBy10.apply(val));
	}
}
