package com.tw.potter;

public class Book implements Comparable<Book> {
    private int id;

    public Book(int id) {
        this.id = id;
    }

    public Book(){
        this(1);
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object other){
        return ((Book) other).getId() == getId();
    }

	public int compareTo(Book o) {
		if(getId() == o.getId()) 
			return 0;
		if(getId() > o.getId())
			return 1;
		
		return -1;
	}

	@Override
	public String toString(){
		return this.getId() + "";
	}

}
