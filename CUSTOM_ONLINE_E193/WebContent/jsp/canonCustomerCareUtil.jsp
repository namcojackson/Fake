<%@page import="com.canon.oracle.custapp.util.CanonCustCareUtil"%>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketSubLinesObj"%>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj"%>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj"%>
<%@page import="java.util.ArrayList"%>

<%

    String ticketId="";

    try{
  
	    CanonCustCareUtil custCareApi = new CanonCustCareUtil();
	
	    // header parameters
		String customerName  = custCareApi.checkNull(request.getParameter("customerName"));
		String customerNum  =  custCareApi.checkNull(request.getParameter("customerNum"));
		String custAcctId  =  custCareApi.checkNull(request.getParameter("custAcctId"));
		String billingIssue  = custCareApi.checkNull(request.getParameter("billingIssue"));
		String recurring  =    custCareApi.checkNull(request.getParameter("recurring"));
		String catIdDesc  =    custCareApi.checkNull(request.getParameter("catIdDesc"));
		String invoiceNum  =    custCareApi.checkNull(request.getParameter("invoiceNum"));
		String contractNum  =    custCareApi.checkNull(request.getParameter("contractNum"));
		String contractModifier  =    custCareApi.checkNull(request.getParameter("contractModifier"));
		String orderNum  =    custCareApi.checkNull(request.getParameter("orderNum"));
		String orderType  =    custCareApi.checkNull(request.getParameter("orderType"));
		String origName  =    custCareApi.checkNull(request.getParameter("origName"));
		String origPhNum  =    custCareApi.checkNull(request.getParameter("origPhNum"));
		String origEmail  =    custCareApi.checkNull(request.getParameter("origEmail"));
		String custContact  =    custCareApi.checkNull(request.getParameter("custContact"));
		String custPhNum  =    custCareApi.checkNull(request.getParameter("custPhNum"));
		String custEmail  =    custCareApi.checkNull(request.getParameter("custEmail"));
		String origType  =    custCareApi.checkNull(request.getParameter("origType"));
		String jtfNotesFlag  =    custCareApi.checkNull(request.getParameter("jtfNotesFlag"));
		String hdrCreatedBy  =    custCareApi.checkNull(request.getParameter("hdrCreatedBy"));
		String createdByResId  =    custCareApi.checkNull(request.getParameter("createdByResId"));
		String attribute9  =    custCareApi.checkNull(request.getParameter("source"));
	
		// line parameters 
		
		String severity  =  custCareApi.checkNull(request.getParameter("serverity"));
		String reasonCd  =  custCareApi.checkNull(request.getParameter("reasonCd"));
		String reason    =   custCareApi.checkNull(request.getParameter("reason"));
		String lnCreatedBy =  custCareApi.checkNull(request.getParameter("lnCreatedBy"));
	
		
		// notes 
	    String notes =   custCareApi.checkNull(request.getParameter("notes"));;
		
		
		int iCustAcctId  = 0; 
	    int iOrderNum    =0;
	    String iHdrCreatedBy  ="";
		 String iCreatedByResId ="";
		 String iLnCreatedBy  ="";
		if(custAcctId.trim().length()>0)
		iCustAcctId = Integer.parseInt(custAcctId);
		
		
		if(orderNum.trim().length()>0)
	    iOrderNum  = Integer.parseInt(orderNum);
		
		if(hdrCreatedBy.trim().length()>0)
		iHdrCreatedBy = hdrCreatedBy;
		
		if(createdByResId.trim().length()>0)
		iHdrCreatedBy = hdrCreatedBy;
		
	
		if(lnCreatedBy.trim().length()>0)
		iLnCreatedBy = lnCreatedBy;
		
		
	    ArrayList<Canon_E193_TicketHeaderObj> headerList = new ArrayList<Canon_E193_TicketHeaderObj>();
		ArrayList<Canon_E193_TicketLinesObj> linesList = new ArrayList<Canon_E193_TicketLinesObj>();
		ArrayList<Canon_E193_TicketSubLinesObj> subLinesList = new ArrayList<Canon_E193_TicketSubLinesObj>();
		
		
		//Start header
		Canon_E193_TicketHeaderObj hdrObj = new Canon_E193_TicketHeaderObj();
		hdrObj.setCustomerName(customerName);
		hdrObj.setCustomerNo(customerNum);
		hdrObj.setCustAcctId( iCustAcctId);
		hdrObj.setBillingIssue(billingIssue);
		hdrObj.setRecurring(recurring);
		hdrObj.setCatIdDesc(catIdDesc);
		hdrObj.setInvoiceNo(invoiceNum);
		hdrObj.setContractNo(contractNum);
		hdrObj.setContractModifier(contractModifier);
		hdrObj.setOrderNo(iOrderNum);
		hdrObj.setOrderType(orderType);
		hdrObj.setOrigName(origName);
		hdrObj.setOrigPhNo(origPhNum);
		hdrObj.setOrigEmail(origEmail);
		hdrObj.setCustContact(custContact);
		hdrObj.setCustPhNo(custPhNum);
		hdrObj.setCustEmail(custEmail);
		hdrObj.setOrigType(origType);
		hdrObj.setJtfNotesFlag(jtfNotesFlag);
		hdrObj.setCreatedBy(iHdrCreatedBy);
		hdrObj.setCreatedByResId(iCreatedByResId);
		hdrObj.setAttribute9(attribute9);//Source
		headerList.add(hdrObj);
		
		custCareApi.setHeaderList(headerList);
		System.out.println("Header Parameter set");
		//System.out.println("Header PARAMS " + hdrObj.toString());
		// End header
	
		// Start lines
		Canon_E193_TicketLinesObj lineObj = new Canon_E193_TicketLinesObj();
		lineObj.setSeverity(severity);
		lineObj.setReasonCd(reasonCd);
		lineObj.setReason(reason);
		lineObj.setCreatedBy(iLnCreatedBy);
		linesList.add(lineObj);
	
		
		
		custCareApi.setLinesList(linesList);
		System.out.println("Line Parameter set");
		
		//System.out.println("Line PARAMS " + lineObj.toString());
		// End lines
	
		
		// Start Sub lines
		 custCareApi.setSubLinesList(subLinesList);
		
		// End Sub lines
	
		// Start Notes
		custCareApi.setstrNotes(notes);
		System.out.println("Notes set");
		// End Notes
		
		
	    ArrayList al =	custCareApi.execute();
		
	    System.out.println("custCareApi Excuted : return list size : "+al.size());
		
		if(al.size()>1)
			ticketId=(String) al.get(1);	
	
	 }catch(Exception e){
	  	System.out.println("ERROR : "+e.getMessage().toString());
	    	
	}
		
	response.getWriter().print(ticketId);
	
%>