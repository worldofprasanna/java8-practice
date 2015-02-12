package com.prsna.code;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
	
	public static void main(String[] args) {
		
		// Old method to create list of Authors
		List<String> authorsOld = Arrays.asList("Vairamuthu", "Sujatha", "Vaali", "Vannathasan", "Ramakrishnan");
		
		// Passing method as an argument. Foreach accepts Consumer FI which accepts one argument and does the processing
		// Consumer : (Integer) -> void
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		authorsOld.forEach(System.out::println);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		
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
		// Another syntatic representation for passing method to for each

		authorsByName.forEach((key, value) -> System.out.println ("Key: "+ key +", Value: "+value));
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		/* Can be written as
	
		BiConsumer<Object, List<String>> authorsConsumer = (key, value) -> System.out.println ("Key: "+ key +", Value: "+value);
		authorsByName.forEach(authorsConsumer);
		*/
		
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
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");		
		// Filter is a lazy operation and it will never execute the line
		// until it encounters the terminal step (say count)
		
		authors.stream()
		.filter((str) -> {
			System.out.println("Eventhoug its within println, this will never be printed");
			return str.length() > 5;
		});
		
		// Creating Person objects using the Person Factory. This can also be created using Supplier FI
		PersonFactory<Person> personFactory = Person::new;
		// 
		Person person1 = personFactory.create("A", 10);
		Person person2 = personFactory.create("B", 15);
		Person person3 = personFactory.create("C", 5);
		Person person4 = personFactory.create("D", 9);
		Person person5 = personFactory.create("E", 7);
		// Creates the Stream of Person objects
		Stream<Person> personStream = Stream.of(person1, person2, person3, person4, person5);
		// Creates Comparator object using thier age
		Comparator<Person> ageComparator = (p1, p2) -> p1.getAge().compareTo(p2.getAge());
		// Stream of persons are sorted and collected as list
		List<Person> personSortedList = personStream.sorted(ageComparator).collect(Collectors.toList());
		// Prints the sorted list of persons
		personSortedList.forEach((person) -> System.out.println(person.getName()+" is "+person.getAge()+" years old."));
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	}

}

class Person{
	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Person(String name, Integer age){
		this.age=age;
		this.name=name;
	}
}
// Functional Interface is optional
//@FunctionalInterface
interface PersonFactory<P extends Person>{
	P create (String name, Integer age);
}
