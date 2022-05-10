package com.practice.demo.exception;

public class ResouseNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResouseNotFoundException(Long id) {
		super("Could not find employee " + id);
		
		
		
	}

}
