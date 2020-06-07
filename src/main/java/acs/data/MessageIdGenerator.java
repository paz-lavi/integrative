package acs.data;


import java.util.concurrent.atomic.AtomicInteger;

public class MessageIdGenerator {
	 private static final AtomicInteger counter = new AtomicInteger();

	  public static int nextValue() {
	    return counter.getAndIncrement();
	  }
}
