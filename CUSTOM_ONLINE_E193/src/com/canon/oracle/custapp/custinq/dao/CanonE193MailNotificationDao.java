package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.HashMap;
//import java.util.Map.Entry;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

import oracle.jdbc.OracleTypes;
import canon.apps.common.CanonCustomProfile;
import canon.apps.common.CanonEmailUtil;

import com.canon.common.CanonCommonUtil;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;


public class CanonE193MailNotificationDao {
	
	/**
	 * CanonE193MailDao constructor.
	 */
	public CanonE193MailNotificationDao() {
		super();
	}
	/**
	 * Send Mail to Supervisor for Inactive user
	 * @throws CanonCustAppExceptionUtil 
	 */
	public void processEmails() throws CanonCustAppExceptionUtil  { 
		try{
			ArrayList<String> rowArrayList = new ArrayList<String>();
			System.out.println("in processEmails");
			ArrayList inactiveUserList = getSupervisorAndAssigneeNameDetails();
			//System.out.println("InActive User Details: "+ inactiveUserList.toString());
			
						
			// Generate Hash Map with different Psn code value
			getNotificationDetails(inactiveUserList);
			
			
			
			
			/*String CiNumber = "5465750";
			String employeeName = "ZZZ";
			String custContactName="Dummy User";
			String EmployeeNumber = "Q11412";
			if(custContactName==null || "".equalsIgnoreCase(custContactName))
				custContactName="Dummy User";
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String emailSubject="Customer Care ticket assigned to inactive user";
			String emailBody="Dear "+SuperVisornName+",\n    The customer care tickets listed below is assigned to user that is no longer active"+
					"\n    Please reassign the customer care tickets and ensure user is removed from Customer Care. \n"+
					"\n CI Number- "+     CiNumber +"" +
					"\n Employee Name- "+ employeeName +
					"\n Employee Number - "+EmployeeNumber+
					"\n\n\n Thanks,"+" \n Canon Customer Service \n Canon Solutions America, Inc";
			
			*/
			
			
			
		}/*catch(CanonCustAppExceptionUtil exceptionUtil){
			//throw(exceptionUtil);
		}*/catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:processEmails()"));
		} 
	}
  
	private ArrayList getSupervisorAndAssigneeNameDetails() throws CanonCustAppExceptionUtil {
		
		ArrayList emailDetails = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connEmailUtil = null;
		
		Connection connEmail = null;
	    CallableStatement pCall = null;
	    ResultSet rsEmailResults = null;
	    try {
	    	connEmailUtil = new CanonCustAppDBUtil();
	    	connEmail = (Connection)(connEmailUtil.getConnection());
	    	
	    	pCall = connEmail.prepareCall("{call CANON_E193_CS_SQLS_PKG.GET_INACTIVE_USR_NOTIF(?)}");
	    	
	    	pCall.registerOutParameter(1, OracleTypes.CURSOR);
	    	//System.out.println(" Before Query Executed ");
	    	pCall.execute();
	    	//System.out.println(" Query Executed ");
	    	rsEmailResults = (ResultSet) pCall.getObject(1);
	    	
	    	while(rsEmailResults.next()) {
	    		EmailNotificationObj lNotificationObj = new EmailNotificationObj();
	    		lNotificationObj.setTicket_Id(rsEmailResults.getString(1));
	    		lNotificationObj.setSupervior_Id(rsEmailResults.getString(2));
	    		lNotificationObj.setPsn_Id(rsEmailResults.getString(3));
	    		lNotificationObj.setAssigneeName(rsEmailResults.getString(4));
	    		lNotificationObj.setSupervisorEmail_Id(rsEmailResults.getString(5));
	    		emailDetails.add(lNotificationObj);
	    		
	    	}	    	
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonE193MailDao, Method:getSupervisorAndAssigneeDetails()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonE193MailDao, getSupervisorAndAssigneeDetails()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rsEmailResults != null) rsEmailResults.close();
	    		if(connEmailUtil != null) connEmailUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonE193MailDao, getSupervisorAndAssigneeDetails()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonE193MailDao, getSupervisorAndAssigneeDetails()"));
	    	}
	    }
	   // System.out.println("emailDetails = "+ emailDetails.toString());
		return emailDetails;
	} // End Of getSupervisorAndAssigneeNameDetails
	
	private void sendEmailNotificationToSupervisor(String receiptEmail, String emailSubject, String emailBody){
		
		System.out.println(" Email process start ");
		try {
			Email email = CanonEmailUtil.createSimpleEmail(); 
			String subjectInit = "";
			try {
				subjectInit = CanonCommonUtil
						.checkNull(CanonCustomProfile
								.getUserProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT"));
			} catch (Exception exception) {
				System.err
						.println("Error occured from getting CANON_CUSTOM_EMAIL_SUBJECT_INIT "
								+ exception.getMessage());
			}
			email.setSubject(subjectInit + emailSubject);
			if(receiptEmail==null)
				email.setMsg(emailBody).addTo(receiptEmail);
			else
				email.setMsg(emailBody).addTo(receiptEmail);
			email.send();
				
		}catch(EmailException e){
			e.printStackTrace();
		}
		
		//System.out.println(" Email process End ");
	}
	
	/**
	 * Get Assignee Name, Ticket Nunmer's and PsnCode
	 * @param arrayList
	 */
	private void getNotificationDetails(ArrayList arrayList) {
		String allTicketIDStr = "";
		String ticketId = "";
		String psn_ID = "";
		String supervisorID = "";
		HashMap<String, ArrayList<String>> superV_PsnMap = new HashMap<String, ArrayList<String>>() ;
		ArrayList<ArrayList<String>>       nodes         = new ArrayList<ArrayList<String>>();
		HashMap<String, ArrayList<String>> psnCdMap      = new HashMap<String, ArrayList<String>>();
		ArrayList<String> supervisorIDList = new ArrayList<String>();
		ArrayList<String> psnCDIdIDList = new ArrayList<String>();
		//ArrayList<String> psnList = null;
		//ArrayList<String> psnCdArrayList = null;
		//ArrayList<String> detailList = null;
		EmailNotificationObj emailNotifyObj = null;
		//System.out.println(" IN Get Notification details");
		if(arrayList!=null){
			for(int i=0; i<arrayList.size();i++){
				emailNotifyObj = (EmailNotificationObj) arrayList.get(i);
				supervisorID = emailNotifyObj.getSupervior_Id();
				psn_ID       = emailNotifyObj.getPsn_Id();
				//System.out.println( " prvSupervisorID=  " + prvSupervisorID);
				//System.out.println( " SupervisorID=  "+ supervisorID + " Psn_ID= " + psn_ID );
				if(!(supervisorIDList.contains(supervisorID)))
				{ 
				   // prvSupervisorID = supervisorID;
				    supervisorIDList.add(supervisorID);
				if((!psnCDIdIDList.contains(psn_ID))){
					psnCDIdIDList.add(psn_ID);
					ArrayList<String>	detailList = new ArrayList<String>();
					ticketId = emailNotifyObj.getTicket_Id();
					String allTicketIDs ="";
					//asssigneeName = emailNotifyObj.getmAssigneeName();
					//supervisorEmail = emailNotifyObj.getmSupervisorEmail_Id();
					allTicketIDs = allTicketIDs  + ticketId;
					// Add elements to list
					detailList.add(allTicketIDs);
					detailList.add(psn_ID);
					detailList.add( emailNotifyObj.getAssigneeName());
					detailList.add(emailNotifyObj.getSupervisorEmail_Id());
					
					//System.out.println(" detailList0 = " + detailList);
					ArrayList<String> psnCdArrayList = new ArrayList<String>();
					psnCdArrayList.add(psn_ID);
					//prvPsnCdID = psn_ID;
					// Update Map.
					psnCdMap.put(psn_ID, detailList);
					
					// Update Map.
					superV_PsnMap.put(supervisorID, psnCdArrayList);
					
				}
				
			}
			 else {
				 // For Same Supervisor ID with same psn code.
				 ArrayList<String> oldPsnCdArrayList = superV_PsnMap.get(supervisorID) ;
				 if(oldPsnCdArrayList.contains(psn_ID) && supervisorIDList.contains(supervisorID)){
					 ArrayList<String> oldDetailList = psnCdMap.get(psn_ID);
					 allTicketIDStr = oldDetailList.get(0);
					 oldDetailList.remove(0);
					 //System.out.println(" OLD allTicketIDs= "+ allTicketIDStr);
					 ticketId = emailNotifyObj.getTicket_Id();
					 allTicketIDStr = allTicketIDStr + ","+ ticketId;
					 //System.out.println(" NEW allTicketIDs= "+ allTicketIDStr);
					 oldDetailList.add(0,allTicketIDStr);
					 
					 psnCdMap.put(psn_ID, oldDetailList);
					 
					// System.out.println(" psnCdMap= " + psnCdMap.hashCode() + " Oth Index:= " + oldDetailList.toString() );
				 }
				 else{ // For Same Supervisor ID but New PSN CODE
					 allTicketIDStr = "";
					 
					// psnCdArrayList = new ArrayList<String>();
					 oldPsnCdArrayList.add(psn_ID); // New Psn Cd added to Old PsnCdArrayList
					 
					 ArrayList<String> detailList = new ArrayList<String>();
					 ticketId = emailNotifyObj.getTicket_Id();
					 allTicketIDStr = allTicketIDStr + ticketId;
					 
					 detailList.add(0,allTicketIDStr);
					 detailList.add(1,psn_ID);
					 detailList.add(2, emailNotifyObj.getAssigneeName());
					 detailList.add(3,emailNotifyObj.getSupervisorEmail_Id());
					 //System.out.println(" detailList = " + detailList);
					 // update PSN CODE MAP.
					 psnCdMap.put(psn_ID, detailList);
					 
					 // Update superV_PsnMap 
					 superV_PsnMap.put(supervisorID, oldPsnCdArrayList);
					 
				 }
					
				}
			}	
			
			createEmailForNotification(superV_PsnMap,psnCdMap);
		} // 
		
	}// getNotificationDetails
	
	/**
	 * Generate Email Body and Subject
	 * @param mainMap
	 * @param innerMap
	 */
	private void createEmailForNotification(HashMap<String, ArrayList<String>> mainMap , HashMap<String, ArrayList<String>> innerMap) {
		
	  
	    String tickeDetails = "";
	    String psnIdValue = "";
	    String assigneeName = "";
	    String supervisorEmailID = "";
	    
	 // Getting a Set of Key-value pairs		
	    Set entrySet = mainMap.entrySet();
	    // Obtaining an iterator for the entry set
	    Iterator it = entrySet.iterator();
	    String emailSubject="Customer Care ticket assigned to inactive user";
	    while (it.hasNext()) {
	    	Map.Entry<String,ArrayList<String>> mentry = (Map.Entry<String, ArrayList<String>>)it.next();
	    	//String SupervisorIDValue = mentry.getKey();
	    	String supervisorName = "";
	    	ArrayList<String> psnArrayList = mentry.getValue();
	    	String assigneeDetails = "";
			//System.out.println(" psnArrayList.size() " + psnArrayList.size());						
	    	for(int i=0; i<psnArrayList.size();i++) {
	    		String psnCdVal = psnArrayList.get(i);
	    		//System.out.println(" psnCdVal=  " + psnCdVal);
	    		
	    		ArrayList<String> detailsArrayList = innerMap.get(psnCdVal);
	    		tickeDetails = detailsArrayList.get(0);
	    		psnIdValue = detailsArrayList.get(1);
	    		assigneeName = detailsArrayList.get(2);
	    		//System.out.println( " TickeDetails =  "+tickeDetails +" psnIdValue = "+psnIdValue +" assigneeName = "+ assigneeName );
	    		if(i==0){
	    		supervisorEmailID = detailsArrayList.get(3);// kdooley@csa.canon.com
	    		
	    		supervisorName = supervisorEmailID.substring(0, supervisorEmailID.indexOf("@"));
	    		// First Character should be Upper Case.
	    		supervisorName = supervisorName.substring(0,1).toUpperCase() + supervisorName.substring(1).toLowerCase();
	    		}
	    		assigneeDetails = assigneeDetails +  "\n CI Number's- "+     tickeDetails +"" +
						"\n Employee Name- "+ assigneeName +
						"\n Employee Number - "+psnIdValue + "\n";
	    		
	    	}
	    	
	    	//System.out.println(" EmailBody = : " + assigneeDetails);
	    	String emailBody="Dear "+supervisorName+",\n    The customer care tickets listed below is assigned to user that is no longer active."+
					"\n    Please reassign the customer care tickets and ensure user is removed from Customer Care. \n";
	    	
	         emailBody = emailBody + assigneeDetails + "\n\n Thanks,"+" \n Canon Customer Service \n Canon Solutions America, Inc";
	         //System.out.println(" EmailBody = : " + emailBody);
	         
	     // Send a Notification email to each and every  unique SupervisorID 

	  	 sendEmailNotificationToSupervisor(supervisorEmailID,emailSubject,emailBody);
	  	 
	  	 System.out.println(" Success:  Mail Send to: " + supervisorEmailID  );
		}
		
		
	}
}
