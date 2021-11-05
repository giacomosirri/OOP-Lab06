package it.unibo.oop.lab.collections1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Example class using {@link java.util.List} and {@link java.util.Map}.
 * 
 */
public final class UseCollection {

    private static final int TO_MS = 1_000_000;

    private UseCollection() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
    	ArrayList<Integer> arrayList = new ArrayList<>();
    	for (int i = 1000; i < 2000; i++) {
    		arrayList.add(i);
    	}
    	
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
    	LinkedList<Integer> linkedList = new LinkedList<>();
    	linkedList.addAll(arrayList);
    	
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
    	var temp = arrayList.get(arrayList.size() - 1);
    	arrayList.set(arrayList.size() - 1, arrayList.get(0));
    	arrayList.set(0, temp);
    	
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
    	for (Integer i : arrayList) {
    		System.out.println(i);
    	}
    	
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
    	long arrayListTimeAdding = computeTimeAdding(arrayList);
    	System.out.println("The insertion of 100,000 new elements in the head of an arrayList took " + 
    						 arrayListTimeAdding + "ns or " + arrayListTimeAdding / TO_MS + "ms");
    	long linkedListTimeAdding = computeTimeAdding(linkedList);
    	System.out.println("The insertion of 100,000 new elements in the head of a linkedList took " + 
    						 linkedListTimeAdding + "ns or " + linkedListTimeAdding / TO_MS + "ms");
    	double ratioAdding = arrayListTimeAdding/linkedListTimeAdding;
    	System.out.println("An array list is " + (ratioAdding > 1 ? ratioAdding : 1 / ratioAdding) + 
    						" times" + (ratioAdding > 1 ? " slower " : " faster ") +
    						"than a linked list to add an element in the head of the list");
    	
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
    	long arrayListTimeReading = computeTimeReading(arrayList);
    	System.out.println("The reading of 1,000 elements in the middle of an arrayList took " + 
    						 arrayListTimeReading + "ns or " + arrayListTimeReading / TO_MS + "ms");
    	long linkedListTimeReading = computeTimeReading(linkedList);
    	System.out.println("The reading of 1,000 elements in the middle of a linkedList took " + 
    						 linkedListTimeReading + "ns or " + linkedListTimeReading / TO_MS + "ms");
    	double ratioReading = arrayListTimeReading/linkedListTimeReading;
    	System.out.println("An array list is " + (ratioReading > 1 ? ratioReading : 1 / ratioReading) 
    						 + " times" + (ratioReading > 1 ? " slower " : " faster ") +
    						 "than a linked list to read an element in the middle of the list");
    	
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         * 
         * Africa -> 1,110,635,000
         * 
         * Americas -> 972,005,000
         * 
         * Antarctica -> 0
         * 
         * Asia -> 4,298,723,000
         * 
         * Europe -> 742,452,000
         * 
         * Oceania -> 38,304,000
         */
    	var continents = new HashMap<String, Long>();
    	continents.put("Africa", 1_110_635_000L);
    	continents.put("Americas", 972_005_000L);
    	continents.put("Antarctica", 0L);
    	continents.put("Asia", 4_298_723_000L);
    	continents.put("Europe", 742_452_000L);
    	continents.put("Oceania", 38_304_000L);
    	// print for checking
    	var it = continents.entrySet().iterator();
    	while (it.hasNext()) {
    		System.out.println(it.next());
    	}
    	
        /*
         * 8) Compute the population of the world
         */
    	var iterator = continents.entrySet().iterator();
    	long worldPopulation = 0;
    	while (iterator.hasNext()) {
    		worldPopulation += iterator.next().getValue();
    	}
    	System.out.println("World population is " + worldPopulation + " people");
    	
    }
    
    private static long computeTimeAdding(List<Integer> l) {
    	long time = System.nanoTime();
    	for (int i = 0; i < 100_000; i++) {
    		l.add(0, i);
    	}
    	return System.nanoTime() - time;
    }
    
    private static long computeTimeReading(List<Integer> l) {
    	long time = System.nanoTime();
    	for (int i = 0; i < 1000; i++) {
    		l.get(l.size()/2);
    	}
    	return System.nanoTime() - time;
    }
}
