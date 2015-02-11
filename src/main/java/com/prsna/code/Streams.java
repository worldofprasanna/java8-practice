package com.prsna.code;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {
		
		// Old method to create list of Authors
		List<String> authorsOld = Arrays.asList("Vairamuthu", "Sujatha", "Vaali", "Vannathasan", "Ramakrishnan");
		
		// Creating a stream of authors
		Stream<String> authorsStream = Stream.of("Vairamuthu", "Sujatha", "Vaali", "Vannathasan", "Ramakrishnan"); 
		
		// Used the grouping by feature of collectors to group the authors by their first character
		/* 
		 * V - Vairamuthu, Vaali, Vannathasan
		 * S - Sujatha
		 * R - Ramakrishnan
		 * 
		 * */
		Map<Object, List<String>> authorsByName = authorsStream
				.collect(Collectors.groupingBy(
						(str) -> ((String)str).charAt(0))
						);
		// Foreach consumes BiConsumer FI which is similar to Consumer but accepts 2 arguments and hence the name.
		authorsByName.forEach((key, value) -> System.out.println ("Key: "+ key +", Value: "+value));
		
		// Again creating the stream because authorsStream is already consumed and it cannot be used again.
		List<String> authors = Stream.of("Vairamuthu", "Sujatha", "Vaali", "Vannathasan", "Ramakrishnan").collect(Collectors.toList());
		
		
		// Gets the stream from list and filters using the predicate
		// Starts with V and less than 6 characters
		// and count the number of items matched the predicate
		long count = authors.stream()
		.filter((str) -> str.startsWith("V"))
		.filter((str) -> str.length() < 6)
		.count();
		
		System.out.println("Number of authors starting with V :"+count);
		
		// Filter is a lazy operation and it will never execute the line
		// until it encounters the terminal step (say count)
		
		authors.stream()
		.filter((str) -> {
			System.out.println("Eventhoug its within println, this will never be printed");
			return str.length() > 5;
		});
		

	}

}
