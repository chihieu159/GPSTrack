package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);
		//DemoApplication.testSet();
	}

	public static void testSet(){
		Set<Integer> list = new HashSet<Integer>();
		Integer newValue = 1;

		boolean isAdded = true;
		isAdded = list.add(newValue);
		System.out.println("add 1: isAdded: " + isAdded);
		isAdded = list.add(newValue);
		System.out.println("add 1 again: isAdded: " + isAdded);
		isAdded = list.add(2);
		System.out.println("add 2: isAdded: " + isAdded);

		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println("testSet");




	}

	public static void testMap(){

	}
}
