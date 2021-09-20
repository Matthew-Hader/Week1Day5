package com.ss.basics.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Matthew Hader
 *
 */
public class LambdaPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//1. 
		List<String> numbers = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten");
		LambdaPractice util = new LambdaPractice();
		
		//Sorted by least number of letters -> most
		numbers.stream().sorted((string1, string2) -> Integer.compare(string1.length(), string2.length()))
						.forEach((str) -> System.out.printf("%s ", str));
		System.out.print("\n");
		
		
		//Sorted by most number of letters -> least
		numbers.stream().sorted((string1, string2) -> Integer.compare(string2.length(), string1.length()))
						.forEach((str) -> System.out.printf("%s ", str));
		System.out.print("\n");
		
		
		//Sorted by first letter alphabetically
		numbers.stream().sorted((string1, string2) -> string1.compareTo(string2))
						.forEach((str) -> System.out.printf("%s ", str));
		System.out.print("\n");
		
		
		//Sorted by letter 'E' as first letter, otherwise ignored
		numbers.stream().sorted((String str1, String str2) -> {
								if(str1.charAt(0) == 'E' && str2.charAt(0) == 'E') {
									return str1.compareTo(str2);
								}
								//str1 starts with E (negative num places str1 first)
								else if(str1.charAt(0) == 'E') {
									return (str1.length() * -1);
								}
								//str2 starts with E (positive num places str2 first)
								else if(str2.charAt(0) == 'E') {
									return str1.length();
								}
								//neither starts with E (0 does not sort)
								else
									return 0;})
						.forEach((str) -> System.out.printf("%s ", str));
		System.out.print("\n");
		
		
		//Sorted by letter 'E' as first letter, otherwise ignored using method
		numbers.stream().sorted((str1, str2) -> util.sortIfE(str1, str2)).forEach((str) -> System.out.printf("%s ", str));
		System.out.print("\n");
		
		
		
		//2. 
		List<Integer> numList = Arrays.asList(1, 5, 2, 20, -3, 12, -4, 100, 7, 99);
		String numString = util.labelInts(numList);
		System.out.println(numString);
		
		
		
		//3. 
		List<String> wordList = Arrays.asList("ape", "grape", "are", "Art", "four", "aura", "as");
		String threeLetterAs = util.threeLetterAStartFilter(wordList);
		System.out.println(threeLetterAs);
	}
	
	//Method for part 1
	public int sortIfE(String str1, String str2) {
		
		//Both contain E, so compare
		if(str1.charAt(0) == 'E' && str2.charAt(0) == 'E') {
			return str1.compareTo(str2);
		}
		//str1 starts with E (negative num places str1 first)
		else if(str1.charAt(0) == 'E') {
			return (str1.length() * -1);
		}
		//str2 starts with E (positive num places str2 first)
		else if(str2.charAt(0) == 'E') {
			return str1.length();
		}
		//neither starts with E (0 does not sort)
		else
			return 0;
	}
	
	//Method for part 2
	public String labelInts(List<Integer> numList) {
		
		StringBuffer result = new StringBuffer();
		numList.stream().forEach(num -> {
			if(num % 2 == 0)
				result.append("e" + num + ", ");
			else
				result.append("o" + num + ", ");
		});
		
		return result.toString();
	}
	
	//Method for part 3
	public String threeLetterAStartFilter(List<String> inputList) {
		
		String output = inputList.stream().filter(str -> str.length() == 3 && str.charAt(0) == 'a').collect(Collectors.joining(", "));
		
		return output;
	}
}

	
