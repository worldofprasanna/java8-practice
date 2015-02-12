package com.prsna.code;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ParallelStreamPractice {

	public static void main(String[] args) {

		long val = 1_000_000;
		List<String> valString = new ArrayList<>();
		for (long i=0 ;i < val; i++){
			UUID uuid = UUID.randomUUID();
			valString.add(uuid.toString());
		}
		
		long startTime = System.nanoTime();
		long processed = valString.stream()
		.sorted()
		.count();
		
		System.out.println("Total - "+processed);
		
		long endTime = System.nanoTime();
		long milliSeconds = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
		
		System.out.println("Time Taken using Sequential Stream:"+milliSeconds);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		
		startTime = System.nanoTime();
		processed = valString.parallelStream()
		.sorted()
		.count();
		
		System.out.println("Total - "+processed);
		
		endTime = System.nanoTime();
		milliSeconds = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
		
		System.out.println("Time Taken using Parallel Stream:"+milliSeconds);
	}

}
