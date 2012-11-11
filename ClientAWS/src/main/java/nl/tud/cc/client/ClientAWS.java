package main.java.nl.tud.cc.client;


import javax.swing.JFrame;

import com.sun.jersey.api.client.Client;

import main.java.nl.tud.cc.gui.ClientFrame;
import main.java.nl.tud.cc.util.ErrorInfo;
import main.java.nl.tud.cc.util.ResponseHandler;



public class ClientAWS {
	
	
	public static void main(String[] args) {
		
		String inputFileString = args[0];
		String outputFilePath = args[1];
		String outputFormat = args[2];
		
		Client client = new Client();
		ErrorInfo error = new ErrorInfo("");
		
		ResponseHandler.videoConverter(client, inputFileString, outputFormat, outputFilePath, error);
		
		/*JFrame clientGUI = new ClientFrame();
		clientGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/

	}

}