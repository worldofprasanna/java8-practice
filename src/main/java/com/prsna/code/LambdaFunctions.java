package com.prsna.code;

public class LambdaFunctions {

	// Interface with a single abstract method. Used as a holder for Lambda expression

	@FunctionalInterface
	interface Math<T>{
		T doMath (T a, T b);
	}
	
	public static void main(String []args){
	
		Integer a = 10;
		Integer b = 20;
		
		// Create new function which accepts 2 arguments and returns the sum of 2 arguments
		Math<Integer> add = (n, m) -> n + m;
		System.out.format("Result of adding a = %d and b = %d is %d \n", a, b, add.doMath(a, b));
		
		// Note a and b are local variables declared in main() and they are used within the inner anonymous function created
		Math<Integer> sub = (n, m) -> a - b;
		System.out.format("Result of subtracting a = %d and b = %d is "+sub.doMath(a, b), a, b);
		
		
		
	}
	
}
