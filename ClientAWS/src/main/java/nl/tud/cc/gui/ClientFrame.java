package main.java.nl.tud.cc.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.java.nl.tud.cc.util.ErrorInfo;
import main.java.nl.tud.cc.util.ResponseHandler;

import com.sun.jersey.api.client.Client;

public class ClientFrame extends JFrame implements ActionListener {
	
	  
	   private static final long serialVersionUID = 1L;
       private final String MOV = ".mov";
	   private final String WMV = ".wmv";
	   private final String FLV = ".flv";
	   
	   private JLabel selectFileLabel;
	   private JTextField browseInputTextField;
	   private JButton browseInputButton;

	   private JLabel chooseFormatLabel;
	   private JComboBox<String> formatComboBox;
	   
	   private JButton convertButton;
	   private JLabel statusLabel;
	   private String format; 
	  
	 
	   /** Constructor to setup GUI components */
	   public ClientFrame () {
	      setLayout(new GridLayout(9,1));
	      
	      selectFileLabel = new JLabel();
	      selectFileLabel.setText("Selected file:");
	      add(selectFileLabel);
	      browseInputTextField = new JTextField();
	      add(browseInputTextField);
	      browseInputButton = new JButton();
	      browseInputButton.setText("Browse");
	      browseInputButton.addActionListener(this);
	      add(browseInputButton); 
		 
		  chooseFormatLabel = new JLabel();
	      chooseFormatLabel.setText("Choose Format:");
	      add(chooseFormatLabel);
	      
	      String[] formats = { MOV, WMV, FLV};
	      formatComboBox = new JComboBox<String>(formats);
	      formatComboBox.setSelectedIndex(0);
	      formatComboBox.addActionListener(this);
	      add(formatComboBox);
	     
		  convertButton = new JButton();
		  convertButton.setText("Convert");
		  convertButton.addActionListener(this);
		  add(convertButton);
		  
		  statusLabel = new JLabel();
		  statusLabel.setText("Status:");
		  add(statusLabel);
		  
		  format = MOV;
		  
		  setTitle("Online Video Converter");  
	      setSize(500, 500);        
	      setVisible(true);         
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object externalEvent = e.getSource();
		if(externalEvent == browseInputButton){
			JFileChooser fc = new JFileChooser();
			fileChooserHandler(fc,browseInputTextField);
		}
		if(externalEvent == formatComboBox)
		{
			setFormat((String)formatComboBox.getSelectedItem());
			
		}
		if(externalEvent == convertButton){
			Client client = new Client();
			ErrorInfo error = new ErrorInfo("");
			statusLabel.setText(statusLabel.getText() + " Busy");
			//ResponseHandler.videoConverter(client, browseInputTextField.getText(), getFormat(), error);
			statusLabel.setText("Status:" + " Done " + browseInputTextField.getText() + getFormat());
		}
	}
	
	public void fileChooserHandler(JFileChooser fc ,JTextField jtf){
		int returnVal = fc.showOpenDialog(this);
		 if (returnVal == JFileChooser.APPROVE_OPTION) 
		 {
	            File file = fc.getSelectedFile();
	            jtf.setText(file.getAbsolutePath());
	     
	     }
	}
	
	public String getFormat(){
		return format;
	}
	
	public void setFormat(String f){
		format = f;
	}
}
