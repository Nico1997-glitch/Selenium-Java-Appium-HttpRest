package com.vogella.junit.first;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyClassTest {
	
	private MyClass tester;

	@BeforeEach
	void setup() {
	    setTester(new MyClass());
	}

    @Test
   public void testGrouped() {
    	 assertAll (//
    			 ()->
    			 testExceptionIsThrown() {

    }

}