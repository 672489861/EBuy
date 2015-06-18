package com.zjw.common.exception;

/***
 * 
 * 自定义异常类
 * 
 * @author zjw
 *
 */

public class MyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyException(){
		super();
	}
	
	public MyException(String message){
		super(message);
	}
	
}
