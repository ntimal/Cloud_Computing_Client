package main.java.nl.tud.cc.util;

public class ErrorInfo {
	private String errorMessage;
	
	public ErrorInfo(String error){
		errorMessage = error;
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
	public void setErrorMessage(String error){
		errorMessage = error;
	}
}
