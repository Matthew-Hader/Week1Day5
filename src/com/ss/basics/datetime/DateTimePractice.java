/**
 * 
 */
package com.ss.basics.datetime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * @author Matthew Hader
 *
 */

/*
 * 1. Which class would you use to store your birthday in years, months, days, seconds, and nanoseconds?
 * 		LocalDateTime
 * 
 * 2. Given a random date, how would you find the date of the previous Thursday?
 * 		You can use the TemporalAdjuster previous to get the thursday prior to whatever day you entered
 * 
 * 3. What is the difference between a ZoneId and a ZoneOffset?
 * 		ZoneId: Tracks offset from UTC time using ZoneRules
 * 		ZomeOffset: Tracks absolute offset from UTC time
 * 
 * 4. How would you convert an Instant to a ZonedDateTime? How would you convert a ZonedDateTime to an Instant?
 * 		You can use the ofInstant method, but must also provide a zoneId
 * 		ZonedDateTime.ofInstant(Instant.wheneverYouWant(), ZoneId())
 */
public class DateTimePractice {

	public static void main(String[] args) {
		
		DateTimePractice test = new DateTimePractice();
		
		//5. Write an example that, for a given year, reports the length of each month within that year.
		test.daysInMonths();
		
		//6. Write an example that, for a given month of the current year, lists all of the Mondays in that month.
		test.printMondays();
		
		//7. Write an example that tests whether a given date occurs on Friday the 13th.
		test.fridayTheThirteenth();
	}
	
	//5. Answer
	public void daysInMonths() {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int year = 0;
		
		try {
			//Read input
			System.out.print("Enter year: ");
			year = Integer.parseInt(reader.readLine());
			//Below line throws an error if year isn't formatted properly
			Year testYear = Year.of(year);
			//Print out info
			for(Month month : Month.values()) {
				YearMonth yearMonth = YearMonth.of(year, month);
				System.out.printf("There are %d days in %s\n", yearMonth.lengthOfMonth(), month);
			}
		}
		catch (Exception e) {
			System.out.println("Please enter a properly formatted year");
			e.printStackTrace();
		}
	}
	
	//6. Answer
	public void printMondays() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Month month = null;
		
		try {
			System.out.print("Enter month: ");
			month = Month.valueOf(reader.readLine().toUpperCase());
			LocalDate date = Year.now().atMonth(month)
					.atDay(1)
					.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
			System.out.printf("Mondays in %s\n", month);
			Month month2 = date.getMonth();
			while(month2 == month) {
				System.out.printf("%s\n", date);
				date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
				month2 = date.getMonth();
			}
		}
		catch (Exception e) {
			System.out.println("Please enter a valid month");
			e.printStackTrace();
		}
	}
	
	//7. Answer
	public void fridayTheThirteenth() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Month month = null;
		LocalDate date = null;
		
		try {
			System.out.print("Enter month: ");
			month = Month.valueOf(reader.readLine().toUpperCase());
		}
		catch(Exception e) {
			System.out.println("Please enter a valid month");
			e.printStackTrace();
		}
		
		try {
			System.out.print("Enter day: ");
			int day = Integer.parseInt(reader.readLine());
			date = Year.now().atMonth(month).atDay(day);
			System.out.printf("is %s %d a Friday the 13th? %b\n", month, day, (date.get(ChronoField.DAY_OF_MONTH) == 13) &&
	                (date.get(ChronoField.DAY_OF_WEEK) == 5));
		}
		catch(Exception e) {
			System.out.println("Please enter a valid day");
			e.printStackTrace();
		}
	}

}
