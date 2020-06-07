package acs.data;


import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;
@Component
public class ActionIdGenerator {
	  private static final AtomicInteger counter = new AtomicInteger();

	  public static int nextValue() {
	    return counter.getAndIncrement();
	  }

}
