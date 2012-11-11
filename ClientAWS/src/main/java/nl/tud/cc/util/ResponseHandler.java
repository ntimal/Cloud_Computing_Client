package main.java.nl.tud.cc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.FormDataMultiPart;


public class ResponseHandler {

	private static final String HOST_REST = "http://sampleenv-rgsp6v7g2y.elasticbeanstalk.com/rest/";
	private static final String VIDEO_CONVERTER = "converter/video";
	
	public static void videoConverter(Client c, String inputFileString,String outputFormat, ErrorInfo ei) {
		
		WebResource webResource = c.resource(HOST_REST + VIDEO_CONVERTER);
		FormDataMultiPart fdmp = new FormDataMultiPart();
		File inputFile = new File(inputFileString);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(inputFile);
		} catch (Exception e) {
			ei.setErrorMessage(e.getMessage());
		}
		fdmp.field("inputFileStream", inputStream, MediaType.MULTIPART_FORM_DATA_TYPE);
		fdmp.field("inputFileName", inputFile.getName());
		fdmp.field("outputFormat", outputFormat);
		
		ClientResponse response = webResource.type(MediaType.MULTIPART_FORM_DATA).accept(MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class,fdmp);
		int status = response.getStatus();
		if(status == 200){
			
			File outputFile = new File(inputFileString + outputFormat);
			try {
				outputFile.createNewFile();
				saveToFile(response.getEntityInputStream(),outputFile);
			} catch (Exception e) {
				ei.setErrorMessage(e.getMessage());
			}
			
			
		}
		else
		{
			String textEntity = response.getEntity(String.class);
			System.out.println(textEntity);
		}
		
	}
	
	public static void saveToFile(InputStream uploadedInputStream,
			File f) throws Exception {

				OutputStream out = null;
				int read = 0;
				byte[] bytes = new byte[1024];

				out = new FileOutputStream(f);
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();


		}
}
