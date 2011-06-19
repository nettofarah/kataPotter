package com.tw.potter;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BasketTest {

	private static final double ERROR_RATE = .5;

	@Test
	public void itCalculatesForEqualBooks(){
		assertThat(priceOf(1), is(8f));
		assertThat(priceOf(1,1), is(2 * 8f));
		assertThat(priceOf(1,1,1),  is(3 * 8f));		
	}

	@Test
	public void itCalculatesForDifferentBooks(){
		assertThat(priceOf(1,2), is(2 * .95f *8f));
		assertThat(priceOf(1,2,3), is(3 * .9f *8f));
		assertThat(priceOf(1,2,3,4), is(4 * .8f *8f));		
	}
    
	//Complex examples
	
    @Test
    public void with2EqualAnd1DifferentBook(){
        assertThat(priceOf(1,1,2), is( 8 + (8 * 2 * .95f) ));
    }
    
    @Test
    public void with2Pairs(){
    	assertThat(priceOf(1,1,2,2), is( 2 * (8 * 2 * .95f) ));
    }
    
    @Test
    public void with2PairsAnd1DifferentBook(){
    	assertThat(priceOf(0, 0, 1, 2, 2, 3), is( (8 * 4 * .8f) + (8 * 2 * .95f) ));
    }
    
    @Test
    public void itCalculatesEvenComplexCases(){
    	assertThat( (double) priceOf(0, 0, 1, 1, 2, 2, 3, 4), closeTo( 2 * (8 * 4 * .8d) , ERROR_RATE));
    	assertThat( (double) priceOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4) , closeTo( 3 * (8 * 5 * .75d) + 2 * (8 * 4 * .8d), ERROR_RATE) );
    }
  
	private float priceOf(int... ids){
		List<Book> items = new ArrayList<Book>();
		for (int id : ids) {
			items.add(new Book(id));
		}
		return new Basket(items).total();
    }

}
