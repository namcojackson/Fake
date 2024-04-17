package oracle.apps.e663.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import com.sun.mail.util.MailSSLSocketFactory;

import canon.apps.common.CanonCustomProfile;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
import parts.dbcommon.EZDConnectionMgr;

public class CanonE663ServiceEmailProg {
	
	public static final String SR_DET = "{call CANON_E663_SERVICE_EMAIL_PKG.GET_SR_CALL_INFO(?,?)}";
	public static final String EMAIL_ADDRESSES = "{call CANON_E663_SERVICE_EMAIL_PKG.GET_EMAIL_ADDRESSES(?,?)}";	
	public static final String INSERT_EMAIL_INFO = "{call CANON_E663_SERVICE_EMAIL_PKG.INSERT_EMAIL_INFO(?,?,?,?,?,?,?,?,?)}";	

	public CanonE663ServiceEmailProg() {
		// TODO Auto-generated constructor stub
	}
	
	 public static void main(String args[]) throws Exception {
	      CallableStatement statement = null;
	        Connection connection = null;
	        try {

	        	connection = TransactionScope.getConnection();

	            if (connection != null) {
	            	
	                statement = (CallableStatement) connection.prepareCall(SR_DET);
	                if (statement != null) {
	                    statement.registerOutParameter(1,OracleTypes.CURSOR);  
	                    statement.registerOutParameter(2,OracleTypes.VARCHAR);  

	                    statement.execute();    
	                    
	                    ResultSet rs = (ResultSet)statement.getObject(1);
	                    String phoneNumber = (String)statement.getObject(2);	                    
	                    while (rs.next()){
	                    	String incidentNumber = rs.getString("INCIDENT_NUMBER");
	                    	String incidentDate = rs.getString("INCIDENT_DATE");
	                    	String serialNumber = rs.getString("SERIAL_NUMBER");
	                    	String model = rs.getString("MODEL");
	                    	String taskSts = rs.getString("TASK_STATUS");
	                    	String probDesc = rs.getString("PROB_DESC");
	                    	String contactInfo = rs.getString("CONTACT_INFO");
	                    	String partyNumber = rs.getString("CUR_LOC_NUM");
	                    	String postalCd = rs.getString("CUR_POST_CD");
	                    	String countrycd = rs.getString("CUR_CTRY_CD");	
	                    	String taskNumber = rs.getString("TASK_NUMBER");
	                    	String incidentSts = rs.getString("INCIDENT_STATUS");
	                    	
	                    	String convIncidentDate = getTmZone(postalCd, countrycd, incidentDate);
	                    	System.out.println("convIncidentDate: " + convIncidentDate+" incidentDate: "+incidentDate);
	                    	System.out.println("incidentNumber: "+incidentNumber+" serialNumber: "+serialNumber+" model: "+model+" taskSts: "+taskSts+" probDesc: "+probDesc);
	                    	System.out.println("contactInfo: "+contactInfo+" partyNumber: "+partyNumber+" phoneNumber: "+phoneNumber);
	                    	String retVal = sendEmail(incidentNumber, convIncidentDate, serialNumber, model, taskSts, probDesc, contactInfo, partyNumber, phoneNumber, connection);
	                    	System.out.println("send email retVal: "+ retVal);
	                    	insertEmailInfo(serialNumber, model, incidentNumber, incidentSts, convIncidentDate, taskNumber, taskSts, retVal);
	                    }
	                } else {
	                    System.err.println("executeSQLPKG: DBStatus.UNABLE_TO_CREATE_STATEMENT ");
	                }
	            } else {
	                System.err.println("executeSQLPKG: DBStatus.UNABLE_TO_GET_CONNECTION");
	            }
	        } catch (Exception ex) {
	            //ex.printStackTrace();
				throw ex;
	        } finally {
	            if (statement != null) {
	                try {
	                    statement.close();
	                    statement = null;
	                } catch (Exception exp) {
	                    //exp.printStackTrace();
						throw exp;
	                }
	            }
	            if (connection != null) {
	                try {
	                    TransactionScope.releaseConnection(connection);
	                } catch (Exception ex) {
	                    //ex.printStackTrace();
						throw ex;
	                }
	            }

	        }
	 }
	 public static String sendEmail(String incidentNumber, String incidentDate, String serialNumber, String model, String taskSts, String probDesc, String contactInfo, String partyNumber, String phoneNumber, Connection connection) throws Exception{
		 String retVal="N";
		 
		  	String emailBody = 
					"<!DOCTYPE HTML PUBLIC  + \"-//W3C//DTD HTML 4.01 Transitional//EN \" \"http://www.w3.org/TR/html4/loose.dtd \" >" +"\n"
			+"  <html>" +"\n"
			+"  <head>" +"\n"
			+" <meta http-equiv='Content-Type' content='text/html; charset=utf-8'> " +"\n"
			+"  <meta name='viewport' content='width=device-width, initial-scale=1.0'> " +"\n"
			+" <meta name='format-detection' content='telephone=no'> " +"\n"
			+"  <title>Canon Solutions America</title> " +"\n"
			+"  </head> " +"\n"
			+"  <body > " +"\n"
			+"  <table align='center' bgcolor='#FFFFFF' border='0' cellpadding='0' cellspacing='0' width='600'>" +"\n"
			+"  <tbody>" +"\n"
			+"  <tr>" +"\n"
			+"  </tr>" +"\n"
			+"  <td width='600' height='0' align='center' valign='top'><img src='http://ess.csa.canon.com/rs/206-CLL-191/images/Canon-Solutions-America-nobar.gif' alt='Canon Solutions America - See Impossible' width='600' height='77' style='display: inline-block; border: none;'></td>"+"\n"
			+"  </tr>" +"\n"
			+"  <tr> " +"\n"
			+"  <td align='center' colspan='2' valign='middle' style='padding: 25px 0px 25px 0px; font-family: Helvetica, Arial, sans-serif;><table width='560' border='0' cellspacing='0' cellpadding='0'> " +"\n"
			+"  <table> " +"\n"			
			+"  <tbody> " +"\n"
			+" 	 <tr>  <td width='360' align='left' valign='top' style='font-family: Helvetica, Arial, sans-serif; font-size: 13px; color: #333;'>Dear Valued Customer, </b> " +"\n"
			+" <p> Thank you for contacting Canon Solutions America for your business needs. Your service request has been successfully submitted with the below details.</p>  " +"\n";
		 
		  	String srInfo = "SERIAL NUMBER:  <b> "+  serialNumber + "</b> <br>";
		  	srInfo = srInfo + "MODEL : <b>" + model + "</b><br>";
		  	srInfo = srInfo + "SERVICE REQUEST NUMBER: <b>"+ incidentNumber + "</b><br>";
		  	srInfo = srInfo + "PROBLEM DESCRIPTION: <b>" + probDesc+ "</b><br>";
		  	srInfo = srInfo + "CREATION DATE: <b>" + incidentDate + "</b><br>";
		  	srInfo = srInfo + "CONTACT INFO: <b>" + contactInfo + "</b><br><br>" ;
		  	
		  	emailBody     = emailBody +" "+ srInfo +"\n"
		  	 +" <p>You may check the status of your request or make any changes to it by contacting our call center at "+ phoneNumber +" and select option 1-1 for Service.</p>  " +"\n"
		  	 +" <p>Thank you,</p> <b>  " +"\n"
		  	 +" Canon Solutions America, Inc.</b><br></td> " +"\n"
		  	 +" <td width='20' align='left' valign='top' style='font-family: Helvetica, Arial, sans-serif; font-size: 8px; color: #FFF;'>-</td> " +"\n"
		  	 +" <td width='20' align='left' valign='top' style='border-left: 1px solid #EAEAEA; font-family: Helvetica, Arial, sans-serif; font-size: 8px; color: #FFF;'>-</td> " +"\n"
		  	 +" <td width='160' align='left valign=top style=font-family: Helvetica, Arial, sans-serif; font-size: 13px; color: #333;'><img src='http://ess.csa.canon.com/rs/206-CLL-191/images/myCSA-Logo-short-140.png' alt='myCSA'> " +"\n"
		  	 +" <p>Need an easy way to manage your account online? Use our new secure account management portal <a href='http://www.csa.canon.com/myCSA' target='_blank'>myCSA</a> to submit meter reads, place service requests, order contracted supplies, pay invoices and retrieve account information.</p></td> " +"\n"
		  	 +" </tr> " +"\n"
			 +" </tbody> " +"\n"
			 +" </table></td> " +"\n"
			 +" </tr> " +"\n"
			 +" <tr> " +"\n"
			 +" <td style='padding: 20px 0px; font-family: Verdana; font-size: 9px; text-align: center; color: #333333; border-top: 1px solid #EAEAEA;'><p>Canon is a registered trademark of Canon Inc., in the United States and elsewhere.<br> " +"\n"
		  	 +" All other referenced product names and marks are trademarks of their respective owners and are hereby acknowledged.<br> " +"\n"
			 +" <br> " +"\n"
			 +" Copyright @ "+ Calendar.getInstance().get(Calendar.YEAR)+" Canon Solutions America, Inc. All Rights Reserved.<br> " +"\n"
			 +" No reproduction or republication, in whole or in part, without written permission.<br> " +"\n"
			 +" Canon Solutions America, Inc. One Canon Park, Melville, NY 11747</p></td> " +"\n"
			 +" </tr> " +"\n"
			 +" <tr> " +"\n"
			 +" <td height='40' align='center' valign='middle' bgcolor='#CC0000' style='font-family: Verdana; font-size: 11px; font-weight: bold; text-align: center; color: #828282;'><p><a href='http://www.csa.canon.com/online/portal/csa/csa/company/privacystatement/' target='_blank'><span style='font-family: Verdana; font-size: 9px; color: #FFFFFF; text-decoration: none;'>Privacy Statement</span></a> " +"\n"
			 +"	 |  <a href='http://www.csa.canon.com/online/portal/csa/csa/company/termsofuse/' target='_blank'><span style='font-family: Verdana; font-size: 9px; color: #FFFFFF; text-decoration: none;'>Terms of Use</span></a> | <a href='http://ess.csa.canon.com/Contact.html' " +"\n"
			 +"  target='_blank'><span style='font-family: Verdana; font-size: 9px; color: #FFFFFF; text-decoration: none;'>Contact Us</span></a></p></td> " +"\n"
		  	 +" </tr> " +"\n"
			 +" </tbody> " +"\n"
			 +" </table> " +"\n"
			 +" </body> " +"\n"
			 +" </html>";
		  	
		  //	System.out.println("emailBody: "+ emailBody);
		  	ArrayList alEmail = getEmailAddress(partyNumber, connection);
		  	if(alEmail!=null && alEmail.size()>0){
		  		for(int i=0;i<alEmail.size();i++){
		  			String emailAddress = (String)alEmail.get(i);
		  			System.out.println("Email Adress: " + emailAddress);
		  			Boolean emailRetVal = sendEmailNotif(emailBody, emailAddress);
		  			if(emailRetVal){
		  				retVal="Y";
		  			}
		  		}
		  	}
		  	
		 return retVal;
	 }
	 public static Boolean sendEmailNotif(String emailBody, String emailAddress){
		 Boolean isSent = false; 
     //	String emailType ="SERVICE REQUEST SUCCESSFULLY CREATED";
     	String subject = "Canon Solutions America - Service Request Confirmation";
     	
		 String host = CanonCustomProfile.getSystemProfileValue("CANON_E663_SMTP_HOST");
		 String from = CanonCustomProfile.getSystemProfileValue("CANON_E663_FROM_EMAIL"); //"applprd@varhpr03.cusa.canon.com"; 
	
		 	System.out.println("host: "+ host+" from: "+from);
		 //	host = "10.68.20.238";
		 //	host = "va-inrelay-lb.cusa.canon.com";
		    Properties properties = System.getProperties();  
		    properties.setProperty("mail.smtp.host", host);  
		    Session session = Session.getDefaultInstance(properties);  

		   //compose the message  
		try {
			MimeMessage message = new MimeMessage(session);
			Multipart multipart = new MimeMultipart( "alternative" );

			message.setFrom(new InternetAddress(from));
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setRecipients(Message.RecipientType.TO, emailAddress);
			
			message.setSubject(subject);
			
			 MimeBodyPart htmlPart = new MimeBodyPart();
			 htmlPart.setContent( emailBody, "text/html; charset=utf-8" );
			 multipart.addBodyPart( htmlPart );
			 
			 message.setContent(multipart);

//			message.setText(emailBody);						

			// Send message
			Transport.send(message);
			 isSent = true; 
			System.out.println("Emain sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		
		return isSent;
		 
	 }
	 public static ArrayList getEmailAddress(String partyNumber, Connection connection) throws Exception{
	      CallableStatement statement = null;
	    //  Connection connection = null;
	      ArrayList emailList = new ArrayList();
	      System.out.println("getEmailAddress partyNumber: " + partyNumber);
	        try {
	        	//connection = TransactionScope.getConnection();

	            if (connection != null) {
	            	
	                statement = (CallableStatement) connection.prepareCall(EMAIL_ADDRESSES);
	                if (statement != null) {
	                	statement.setObject(1, partyNumber, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(2,OracleTypes.CURSOR);  
	                    statement.execute();        
	                    ResultSet rs = (ResultSet)statement.getObject(2);
	                    while (rs.next()){
	                    	String emailAddress = rs.getString("EMAIL_ADDRESS");
	                    	System.out.println("emailAddress: " + emailAddress);
	                    	emailList.add(emailAddress);
	                    }

	                } else {
	                    System.err.println("executeSQLPKG: DBStatus.UNABLE_TO_CREATE_STATEMENT ");
	                }
	            } else {
	                System.err.println("executeSQLPKG: DBStatus.UNABLE_TO_GET_CONNECTION");
	            }
	        } catch (Exception ex) {
	            //ex.printStackTrace();
				throw ex;
	        } finally {
	            if (statement != null) {
	                try {
	                    statement.close();
	                    statement = null;
	                } catch (Exception exp) {
	                    //exp.printStackTrace();
						throw exp;
	                }
	            }
	     /*       if (connection != null) {
	                try {
	                    TransactionScope.releaseConnection(connection);
	                } catch (Exception ex) {
	                    //ex.printStackTrace();
						throw ex;
	                }
	            } */

	        }
			return emailList;
	 }
		public static String getTmZone(String postCd, String ctryCd, String dtTm){
			String tmZone ="";
			String convertTm="";
			boolean numCheck =false;
			 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
			 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
			 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
			 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
			 System.out.println("getTimeZone.. postCd : "+postCd+" ctryCd : "+ctryCd+" dtTm: "+dtTm);
			 System.out.println(".getTimeZone.. sSysDt : "+sSysDt+" sSysTm : "+sSysTm);
			try{
				if(!("null".equals(postCd)) && !("".equals(postCd))){
					postCd = postCd.substring(0, 5);
				}
				NSXC001001SvcTimeZone tmUtil = new NSXC001001SvcTimeZone();
				SvcTimeZoneInfo info = new SvcTimeZoneInfo();
				
				String userName="";
				String timeRegion="EST";
				String appId="EXTNE307";
				String pageId="getTmZn";
				String appPageId="EXTNE307_getTmZn";
					

				
				if("".equals(dtTm) || "null".equals(dtTm)){
					info = tmUtil.convertTime(1, sSysDt, ctryCd, postCd);
				}else{
					numCheck = isNumber(dtTm);
					//System.out.println("numCheck: "+ numCheck);
					if(numCheck){
						info = tmUtil.convertTime(1, dtTm, ctryCd, postCd);
					}
				}
				
				  
				System.out.println("dtTm : "+dtTm);
				
				if("".equals(dtTm)){
					tmZone = info.getTimeZone();
				}else{
					if(numCheck){
						convertTm = info.getDateTime().substring(0, 12);
						System.out.println("tmZone: " + convertTm);
						tmZone = formatDateTime(convertTm);
						System.out.println("convertTm : "+ tmZone);
						if(tmZone!=null && tmZone.length()>0){
							String strDate = tmZone.substring(0, 11);
							String strHr = tmZone.substring(12,14);
							String strMin = tmZone.substring(15,17);
							String strAmPm = tmZone.substring(18,20);
							System.out.println("length : "+tmZone.length()+" strDate: "+strDate+" strHr : "+strHr+" strMin: "+strMin+" strAmPm: "+strAmPm);
						}
					}
				}
				
				System.out.println(".getTimeZone.. tmzone : "+ tmZone +" postCd: "+postCd + " ctryCd: " +ctryCd+" time : "+dtTm);
			} catch (Exception e) {
				System.out.println(".getTmZone.. Error timezone : "+ e.getMessage());
			} finally {	// Release DB resource (Close DB Connection)	
				System.out.println(".getTmZone.. ");
				//EZDConnectionMgr.getInstance().releaseResource();
			}
			return tmZone;
		}
	    public static boolean isEmpty(String str) {
	        return str == null || str.length() == 0;
	    }

	    static Pattern PARRETN_DEIMAL_NUM = Pattern.compile("[+-]?(?:(?:0)|(?:[123456789][\\d,]*\\d*)|(?:[\\d,]*\\.\\d*))");

	    public static boolean isNumber(String str) {
	        if(isEmpty(str))return false;
	        Matcher matcher = PARRETN_DEIMAL_NUM.matcher(str);
	        return matcher.matches();
	    }
	    public static String formatDateTime(String str){
	  	  
	  	  if (str != null) {
	  		  SimpleDateFormat toFormat = new SimpleDateFormat("MMM dd yyyy hh:mm a", Locale.US);// new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US);
	  		  SimpleDateFormat fromFormat = new SimpleDateFormat("yyyyMMddHHmm");
	  		  try{
	  			  java.util.Date d = fromFormat.parse(str);
	  			  str = toFormat.format(d);
	  			  //System.out.println("ret Date1 : "+ str);
	  		  }catch (ParseException ex) {
	  			  ex.printStackTrace();
	  		  }
	  	  }else{
	  		  return ""; 
	  	  }
	  	  return str; 
	    } 
		 public static String insertEmailInfo(String strSerialNumber, String strModel, String strIncNumber, String strIncSts, String strIncDate, String strTaskNum, String strTaskSts, String StrEmailSts) throws Exception{
		      CallableStatement statement = null;
		      Connection connection = null;
		      String retSts="";
		        try {
		        	connection = TransactionScope.getConnection();

		            if (connection != null) {
		            	
		                statement = (CallableStatement) connection.prepareCall(INSERT_EMAIL_INFO);
		                if (statement != null) {
		                	statement.setObject(1, strSerialNumber, OracleTypes.VARCHAR);
		                	statement.setObject(2, strModel, OracleTypes.VARCHAR);
		                	statement.setObject(3, strIncNumber, OracleTypes.VARCHAR);
		                	statement.setObject(4, strIncSts, OracleTypes.VARCHAR);
		                	statement.setObject(5, strIncDate, OracleTypes.VARCHAR);
		                	statement.setObject(6, strTaskNum, OracleTypes.VARCHAR);
		                	statement.setObject(7, strTaskSts, OracleTypes.VARCHAR);
		                	statement.setObject(8, StrEmailSts, OracleTypes.VARCHAR);
		                	
		                    statement.registerOutParameter(9,OracleTypes.VARCHAR);  
		                    statement.execute();        
		                    retSts = (String)statement.getObject(9);
		                    System.out.println("retSts for insertEmailInfo: "+ retSts+ " strIncNumber: "+ strIncNumber);		

		                } else {
		                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
		                }
		            } else {
		                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		            }
		        } catch (Exception ex) {
		            //ex.printStackTrace();
					throw ex;
		        } finally {
		            if (statement != null) {
		                try {
		                    statement.close();
		                    statement = null;
		                } catch (Exception exp) {
		                    //exp.printStackTrace();
							throw exp;
		                }
		            }
		            if (connection != null) {
		                try {
		                    TransactionScope.releaseConnection(connection);
		                } catch (Exception ex) {
		                    //ex.printStackTrace();
							throw ex;
		                }
		            }

		        }
				return retSts;
		 }
}
