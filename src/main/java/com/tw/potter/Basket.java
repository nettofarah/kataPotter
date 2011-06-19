package com.tw.potter;

import java.util.*;

public class Basket {

    private List<Book> items;
    private Map<Integer, Float> discounts = new HashMap<Integer, Float>(){{
      put(1, 1f);
      put(2, .95f);
      put(3, .9f);
      put(4, .8f);
      put(5, .75f);
    }};

    public Basket(List<Book> items) {
        this.items = items;
    }

    public float total() {
       List<Set<Book>> differentSets = splitInDifferentSets();
       return totalAllSets(differentSets);
    }

	private float totalAllSets(List<Set<Book>> differentSets) {
		float total = 0;
		for (Set<Book> set : differentSets) {
		   total += totalFor(set);
		}
		return total;
	}


    private float totalFor(Set<Book> set) {
		return discountFor(set.size()) * 8f * set.size();		
	}

	private List<Set<Book>> splitInDifferentSets() {
    	List<Set<Book>> allSequences = new ArrayList<Set<Book>>();
    	List<Book> itemsCopy = new ArrayList<Book>(items);
    	
    	while(!itemsCopy.isEmpty()){
    		Set<Book> sequence = assembleSequence(itemsCopy);
			allSequences.add(sequence);
			clean(itemsCopy, sequence);
    	}
    	
    	return allSequences;		
	}

	private void clean(List<Book> itemsCopy, Set<Book> sequence) {
		for (Book book : sequence) {
			itemsCopy.remove(book);
		}
	}

	private Set<Book> assembleSequence(List<Book> itemsCopy) {
		Set<Book> sequence = new TreeSet<Book>();
		for (Book book : itemsCopy) {
			sequence.add(book);
		}
		return sequence;
	}

	private float discountFor(int size) {
        return discounts.get(size);
    }

}
