package com.srisaiHome.srisaiHome;

import org.springframework.boot.SpringApplication;
import java.util.*;  
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SrisaiHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrisaiHomeApplication.class, args);
		
		
		   Scanner scan = new Scanner("Hello World!");  
	        //Printing the delimiter used  
	        System.out.println("Delimiter:" + scan.delimiter());  
	        //Print the Strings  
	        while (scan.hasNext()) {  
	            System.out.println(scan.next());  
	        }  
	}

}
