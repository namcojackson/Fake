package com.canon.apps.servreq.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;

import canon.apps.common.CanonEmailUtil;

import com.canon.common.CanonCommonUtil;
import com.sun.xml.internal.ws.util.ByteArrayDataSource;


public class CanonE307ServiceReqEmailUtil { 

	private   String clsName="CanonE307ServiceReqEmailUtil";
	          CanonCommonUtil util;         
	
	  
	public CanonE307ServiceReqEmailUtil(){
		util= new CanonCommonUtil();
	}
	  //NSZC0430

	   
	public void sendEmail (String to,  String subject , String msgBody ){
		
		try{
			
			
			util.logMsg(false, clsName+".createServicerequest", "Before  Send Email Call");
			
			String str="----------------------- \n\n\n";
			str +=		"\n[TO] :"+to+"\n[FROM] :"+subject+"\n[MSG] :"+msgBody;
			str+="\n\n\n ----------------------- ";
			util.logMsg(false, clsName+".createServicerequest",str);
			
			
		CanonEmailUtil.createSimpleEmail().setSubject(subject).setMsg(msgBody).addTo(to).send();
		
		

		util.logMsg(false, clsName+".createServicerequest","Email Sent ...........");
		
//		.addTo("abc@test.com","abc2@test.com","abc3@test.com")        // You can add multiple email address 
//      .addCc()
//      .setFrom(from)
//		.addBcc("bcc@test.com")
//		.send();
		
		
		
		/*	CanonEmailUtil.createSimpleEmail().setSubject("Test Simple Mail")
				.setMsg("This is a test mail ... :-)")
				.addTo(TO_ADDRESS)
				.addTo("abc@test.com","abc2@test.com","abc3@test.com")
				.addCc(CC_ADDRESS)
				.addBcc("bcc@test.com")
				.send(); */
		 
		
		
		
		}catch (Exception e){
			util.logMsg(true,clsName+".sendEmail",e.getMessage());
		}
	}
	
	
	public void emailAttachment(String to, String path) throws EmailException, MalformedURLException {
		
		util.logMsg(false, clsName+".createServicerequest","Maling PDF file");
		util.logMsg(false, clsName+".createServicerequest","[TO_EMAIL] "+to);
		util.logMsg(false, clsName+".createServicerequest","[PDF LOCATION ] "+path);
		
		path="http://s21dev.cusa.canon.com/s21extn/e307/jsp/ADVC5000_0006JAM.pdf";
		
		CanonEmailUtil
				.createMultiPartEmail()
				.attach(new URL(
						path),
						"ADVC5000_0006JAM.pdf", "Printer fix Instructions")
				.setSubject("Test PDF attachment")
				.setMsg("Attached PDF")
				.addTo(to)
				.send();
	}

	
	
	
	
	public  String checkNull(HttpServletRequest req, String str ){
	     String s="";
		String tmp =  req.getParameter(str);
		if (tmp != null) {
			if(tmp.trim().length()>0){
			  s=tmp.trim();
			  util.logMsg(false, clsName+".createServicerequest", str +" = "+s);
			}
		}
		return s;  
	 }
}

