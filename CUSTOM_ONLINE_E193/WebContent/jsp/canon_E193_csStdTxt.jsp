<!-- $Header: ITG# 74988 canon_E193_csStdTxt.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csStdTxt.jsp - Contains standard question phrases, and messages.
 |
 | DESCRIPTION
 |  
 |  
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	09/23/2005
 |
 | HISTORY
 | DATE		WHO	 	 WHY
 | 30-Nov-06   Vikas Basal       ITG# 74988 CFS Changes
 | 18-Dec-06   Kireet K Bollam   ITG# 73987 CBS Consolidation Changes
 | 16-Oct-09   Naveen Khandelwal MW Project Changes.
 +=======================================================================--%>

<%@ page language="java" %>
<%
	String strGreetingsT1 = "Greetings";
	String strGreetingsM1 = "and Thank You for calling Canon Solutions America.";
	String strGreetingsM2 = "My name is ";
	String strGreetingsM3 = ". How may I help you?";
	String strGreetingsQ1 = "In order to assist you please let me get some information";
	String strGreetingsN1 = "(*)";
	String strGreetingsN2 = "Required field.";
	String strGreetingsN3 = "* Enter First Name and Last Name.";
	String strAccountContactInformationT1 = "Account Contact Information";
	String strAccountContactInformationQ1 = "Please provide customer contact details.";
	String strAccountContactInformationN1 = "(*)";
	String strAccountContactInformationN2 = "Required field.";
	String strAccountContactInformationN3 = "* Enter First Name and Last Name.";
	String strIdentificationOfInquiryAndAccountT1 = "Identification of Inquiry & Account";
	String strIdentificationOfInquiryAndAccountQ1 = "Is this a recurring problem?";	
	String strIdentificationOfInquiryAndAccountQ2 = "Let me identify your account. Do you have an invoice number or your account number? Could you please provide one of the <br>"+
													"following? If not, please provide one with a serial number, account name, contract number, order number or ticket number.";	
	String strAccountAddressInformationT1 = "Account Address Information";
	String strAccountAddressInformationN1 = "*CSR Notes: Optional; please obtain address details from the caller.";
	String strSelectAccountAddressT1 = "Select Account Address";
	String strSelectAccountAddressN1 = "*CSR Notes: Select any one of the below address to proceed. <br>"+
									   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
									   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
									   "You can sort this page on any column, by clicking on the header.";
	String strSelectAccountT1 = "Select Account";
	String strSelectAccountN1 = "Below account is selected.";
	String strSelectAccountN2 = "*CSR Notes: If there are open tickets please review history before proceeding.";
	String strCheckRequestT1 = "Check Request";
	String strCheckRequestQ1 = "Is this a new request or are you calling on a previous request?";
	String strCheckRequestQ2 = "Is this Billing Issue?";
	String strCheckRequestQ3 = "Do you have an invoice number?";
	String strCheckRequestQ4 = "Do you have an ticket number?";
	String strCheckRequestN1 = "*CSR Notes: If complete invoice number is not available, please use Invoice Search.";
	String strCheckRequestN2 = "*CSR Notes: If not, I can search for Invoice Number";
	String strCheckRequestN3 = "*CSR Notes: If complete ticket number is not available, please use ticket# Search";
	
	String strInvoiceSearchT1 = "Invoice Search";
	String strInvoiceSearchN1 = "*CSR Notes: Search Invoice based on dates provided by customer.";
	String strInvoiceSearchN2 = "*CSR Notes: Invoice can be filtered based on invoice source; Select one of the below invoice to proceed.";
	
	String strNBIssueListT1 = "NonBilling Issues";
	String strNBIssueListQ1 = "What is the Issue?";
	String strNBIssueCaptureT1 = "NonBilling Issue Capture";
	String strNBIssueCaptureN1 = "*CSR Notes: Capture details for this customer inquiry.";
	String strNBIssueCaptureN2 = "*CSR Notes: Capture the comments, must provide full details in comments.";
		
	String strBIssueListT1 = "Billing Issues";
	String strBIssueListQ1 = "What is the issue on this invoice?";
	String strContLineDetailsT1 = "Billing Contracts Line Details";
	String strContLineDetailsN1 = "*CSR Notes: Select line(s) that are being disputed.";
	//MW Project Changes Starts
	String strContLineDetailsUN1 = "*CSR Notes: Select line(s) that are being disputed for usage charges.";
	String strContLineDetailsBN1 = "*CSR Notes: Select line(s) that are being disputed for base charges.";
	//MW Project Changes Ends
	String strContLineUpdateT1 = "Billing Contracts Line Updates";
	String strContLineUpdateN1 = "*CSR Notes: Enter new reads provided by the customer."+
								 " Provide only disputed meter reads, leave other fields blank.";
	String strContLineUpdateN2 = "*CSR Notes: Please confirm serial numbers and meter reads with customer.";		 
	String strContLineUpdateN21 = "*CSR Notes: Enter pricing details provided by the customer.Provide only the disputed pricing attributes, leave other fields blank.";							
	String strContLineUpdateN3 = "*CSR Notes: Enter new rate details provided by the customer.";
	String strContLineUpdateN4 = "*CSR Notes: Capture details for this customer inquiry.";
	String strContLineUpdateN5 = "*CSR Notes: Capture the comments, must provide full details in comments.";
	String strSupplyLineDetailsT1 = "Billing Supply Issue";
	String strSupplyLineDetailsN1 = "*CSR Notes: Provide credits customer is requesting. If no change leave blank.";
	String strSupplyLineDetailsN2 = "*CSR Notes: Capture details for this customer inquiry.";
	String strSupplyLineDetailsN3 = "*CSR Notes: Capture the comments, must provide full details in comments.";
	String strServiceLineDetailsT1 = "Billing Service Issue";
	String strServiceLineDetailsN1 = "*CSR Notes: Provide credits customer is requesting. If no change leave blank.";
	String strServiceLineDetailsN2 = "*CSR Notes: Capture details for this customer inquiry.";
	String strServiceLineDetailsN3 = "*CSR Notes: Capture the comments, must provide full details in comments.";
	String strOtherLineDetailsT1 = " Billing AR Manual Issue";
	String strOtherLineDetailsT2 = "Billing Merchandise Issue";
	String strOtherLineDetailsT3 = "Billing Miscellaneous Issue";
	String strOtherLineDetailsN1 = "*CSR Notes: Enter new reads provided by the customer.";
	String strOtherLineDetailsN2 = "*CSR Notes: Capture details for this customer inquiry.";
	String strOtherLineDetailsN3 = "*CSR Notes: Capture the comments, must provide full details in comments.";
	String strTaxIssueT1 = "Billing Tax Issue";
	String strTaxIssueQ1 = "Are you being charged tax?";
	String strTaxIssueQ2 = "What is the tax amount you are requesting credit for?";
	String strTaxIssueQ3 = "Do you have tax exemption certificate?";
	String strTaxIssueQ4 = "What is the tax to be charged?";
	String strTaxIssueN1 = "*CSR Notes: Capture details for this customer inquiry";
	String strTaxIssueN2 = "*CSR Notes: Capture the comments, must provide full details in comments.";
	String strPOIssueT1 = "Billing PO Issue";
	String strPOIssueQ1 = "What is the correct PO number?";
	String strPOIssueN1 = "*CSR Notes: Capture details for this customer inquiry.";
	String strPOIssueN2 = "*CSR Notes: Capture the comments, must provide full details in comments.";
	String strOtherIssueT1 = "Billing Other Issue";
	String strOtherIssueN1 = "*CSR Notes: Capture details for this customer inquiry.";
	String strOtherIssueN2 = "*CSR Notes: Capture the comments, must provide full details in comments.";
	String strFreightIssueT1 = "Billing Freight Issue";
	String strFreightIssueQ1 = "What is the expected Freight amount?";
	String strFreightIssueN1 = "*CSR Notes: Capture details for this customer inquiry.";
	String strFreightIssueN2 = "*CSR Notes: Capture the comments, must provide full details in comments.";
	
	String strReasonCodeN1 = "&nbsp;&nbsp;&nbsp;Hyperlink to choose reason code (Must choose one)";
	String strInvoiceNoN1 = "&nbsp;&nbsp;&nbsp;<b>(Click on hyperlink to see invoice image)</b>";
	
	String strTicketSummaryT1 = "Ticket Summary";
	String strTicketSummaryN1 = "*This is your confirmation number.";
	String strTicketSummaryN2 = "*CSR Notes: Please review with customer and save before proceeding.";
	String strTicketSummaryN3 = "*CSR Notes: Select Call Wrap up to complete the call. Select Save to save assignments.<br>" +
								"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
							    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
	                            "Select Add Issues to add new issues for this invoice."; 
								
	String strTicketHistoryT1 = "Ticket History";
	String strTicketHistoryM1 = "History not found for this value. Please revise your search criteria";
	String strTicketHistoryN1 = "*CSR Notes: Please select search criteria and enter value.";
	String strTicketHistoryN2 = "*CSR Notes: You can sort this page on any column, by clicking on the header." +
	                            "<br>" +
								"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
								"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
								"Select Ticket Number to view Ticket Status and Resolution.";
	String strTicketHistoryN3 = "*CSR Notes: Use Clear Account Information option to start new search. " + 
								"<br>" +
								"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
								"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
	                            "If ticket creation is in process, all data entered for this account will be lost.";
	String strAdministrationT1 = "Administration";
	String strAdministrationM1 = "CRM Category details for ";
	String strWorkbenchOwnedT1 = "Workbench - Owned";
	String strWorkbenchOwnedM1 = "*CSR Notes: Please select ticket owner.";
	String strWorkbenchOwnedM2 = "No tickets found for this owner.";	
	String strWorkbenchOwnedN1 = "*CSR Notes: You can sort this page on any column, by clicking on the header.";
	String strWorkbenchOwnedN2 = "*CSR Notes: Owner can be changed for selected ticket";
	String strWorkbenchAssignedT1 = "Workbench - Assigned";
	String strWorkbenchAssignedM1 = "*CSR Notes: Please select department and ticket assignee.";
	String strWorkbenchAssignedM2 = "No tickets found for this assignee.";
	String strWorkbenchAssignedN1 = "*CSR Notes: You can sort this page on any column, by clicking on the header.";
	
	String strWorkbenchCreatedT1 = "Workbench - Created";
	String strWorkbenchCreatedM1 = "*CSR Notes: Please select department and ticket assignee.";
	String strWorkbenchCreatedM2 = "No tickets found for this assignee.";
	String strWorkbenchCreatedN1 = "*CSR Notes: You can sort this page on any column, by clicking on the header.";
	
	String strTicketStatusT1 = "Status and Resolution";
	String strTicketStatusM1 = "Issues not found for this ticket.";
	String strTicketStatusN1 = "*CSR Notes: Capture additional comments, must provide full details in comments.";
	String strTicketStatusN2 = "*CSR Notes: Please click on the category link to view additional line details.";
	String strTicketStatusN3 = "*CSR Notes: Please save changes before proceeding.";
	String strTicketStatusN4 = "*CSR Notes: Use Credit Rebill Summary option to view credits issued for meter reads and pricing.";		
	
	String strTicketConfirmationT1 = "Call Wrap Up";
	String strTicketConfirmationM1 = "Thank you for calling Canon Solutions America. Your ticket number is :";
	String strTicketConfirmationM2 = ". We will get back to you shortly with resolution.";
	String strTicketConfirmationM3 = "To add new issues to this ticket, select ";
	String strTicketConfirmationM4 = "To go to Ticket History, select ";
	String strTicketConfirmationM5 = "To go to Workbench, select ";
	String strTicketConfirmationM6 = "To enter a new ticket, select ";
	String strTicketConfirmation   = "Create a new ticket, from Copy Functionality ";
	// variables declared for Popup windows standard text.	
	String strAdminRolePM1 = "Role and Resource descriptions not found.";
	String strAssignmentPM1 = "No assignments found for this ticket.";
	String strAttachmentsPM1 = "*CSR Notes: For delete, please select a radio button ";
	String strAttachmentsPM2 = " and click on the delete button";
	String strEIContLineUpdatePM1 = "Read details not found.";
	String strEIContLineUpdatePM2 = "Pricing details not found.";
	String strEIContLineUpdatePM3 = "Rate details not found.";
	String strEIOtherLineDetailsPM1 = "Invoice information not found.";
	String strEIServiceLineDetailsPM1 = "Invoice information not found.";	
	String strEISupplyLineDetailsPM1 = "Invoice information not found.";
	String strTicketAssignmentPM1 = "Roles not found for this department.";
	String strTicketAssignmentPM2 = "Resources not found for this role";
	String strTicketHistoryPM1 = "History not found for this value. Please revise your search criteria.";
	String strTicketReasonCodePM1 = "Reason descriptions not found.";
	String strCRSummaryPM1 = "Credit/Rebill summary not found";
			
	String strReviewPopupM1 = "*CSR Notes: Below information has been provided by the customer.";
	// error messages
	String strSessionExp = "Session time out. Please relogin";
	
	// Kireet: Ticket #:ITG# 74988 - CFS Changes
	String strCFSIssueCaptureT1 = "CFS Issue Capture";
        String strCFSAccessMsgT1  = "Access Denied";
        String strCFSAccessMsgM1  = "You Are Not Authorized to create CFS Tickets. Please use the Enter & Inquiry tab to create tickets.";
//Start Changes for S21 by Mangala
String strMPSNTSCIssueCaptureT1 = "MPS-NTSC Issue Capture";
//End Changes for S21 by Mangala

	String strCFSTicketConfirmationT1 = "Ticket Confirmation";
	String strCFSTicketConfirmationM1 = "Your ticket number is :";
//	String strCFSTicketConfirmationM2 = "";
//	String strCFSTicketConfirmationM3 = "";
	String strCFSTicketConfirmationM4 = "To go to Ticket History, select ";
	String strCFSTicketConfirmationM5 = "To go to Workbench, select ";
	String strCFSTicketConfirmationM6 = "To enter a new ticket, select ";

        /* ITG# 74988 - End */  
        
        /* ITG# 73987 - Begin */
        //Kireet K Bollam
        
        String strRegionCodeIncorrect = "Region Code not defined for your user#. Please contact System Administrator for further assistance.";
	
        /* ITG# 73987 - End */
        
        /* ITG# 125696 - Begin */
	//Kireet K Bollam
	        
	String strNonBillingConvertedInv = "This is a converted invoice, Please log this ticket under non-billing category."; //"Tickets for converted invoices related to contracts are to be logged in as Non-Billing issues.";
		
        /* ITG# 125696 - End */
	String strQuickTicketT1 = "Quick Ticket";
%>
