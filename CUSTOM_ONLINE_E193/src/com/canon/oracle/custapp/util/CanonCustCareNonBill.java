package com.canon.oracle.custapp.util;


import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj;
import com.canon.oracle.custapp.custinq.dao.Canon_E193_NonBillingIssue;

public class CanonCustCareNonBill {

	private Canon_E193_TicketHeaderObj headerList;
	private Canon_E193_TicketLinesObj linesList;
	
	public Canon_E193_TicketHeaderObj getHeaderList() {
		return headerList;
	}

	public void setHeaderList(Canon_E193_TicketHeaderObj headerList) {
		this.headerList = headerList;
	}

	public Canon_E193_TicketLinesObj getLinesList() {
		return linesList;
	}

	public void setLinesList(Canon_E193_TicketLinesObj linesList) {
		this.linesList = linesList;
	}

	
	public int execute (){
		
		int ticket = 0;
		
		try{ 
 
			Canon_E193_TicketHeaderObj    headerArr   = new Canon_E193_TicketHeaderObj();
			Canon_E193_TicketLinesObj     linesArr    = new Canon_E193_TicketLinesObj();
			 headerArr=headerList;
			 linesArr = linesList;
			
			 Canon_E193_NonBillingIssue biDao = new Canon_E193_NonBillingIssue();
			 
			 ticket = biDao.addNonBillHeaderLines(headerArr, linesArr);
			 
		}catch(Exception e){
			System.err.println("in execute " + e.getMessage());
			ticket=0;
		}
		
		return ticket;
	}
	
}
