package com.canon.oracle.custapp.util;

import java.util.ArrayList;
import java.util.Date;

import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketDetailObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketSubLinesObj;
import com.canon.oracle.custapp.custinq.dao.Canon_E193_BillingIssue;

public class CanonCustCareUtil {

	private ArrayList<Canon_E193_TicketHeaderObj> headerList;
	private ArrayList<Canon_E193_TicketLinesObj> linesList;
	private ArrayList<Canon_E193_TicketSubLinesObj> subLinesList;

	String notes;
	public ArrayList<Canon_E193_TicketHeaderObj> getHeaderList() {
		return headerList;
	}

	public void setHeaderList(ArrayList<Canon_E193_TicketHeaderObj> headerList) {
		this.headerList = headerList;
	}

	public ArrayList<Canon_E193_TicketLinesObj> getLinesList() {
		return linesList;
	}

	public void setLinesList(ArrayList<Canon_E193_TicketLinesObj> linesList) {
		this.linesList = linesList;
	}

	public ArrayList<Canon_E193_TicketSubLinesObj> getSubLinesList() {
		return subLinesList;
	}
	public void setSubLinesList(ArrayList<Canon_E193_TicketSubLinesObj> subLinesList) {
		this.subLinesList = subLinesList;
	}
	
	public void setstrNotes(String strNotes) {
		this.notes = strNotes;
	}
	public String setstrNotes(){
		return notes;
	}
	
public ArrayList execute (){
		
		ArrayList alDtls = new ArrayList();
		try{ 
 
			Canon_E193_TicketHeaderObj[]    headerArr   = new Canon_E193_TicketHeaderObj[headerList.size()];
			Canon_E193_TicketLinesObj[]     linesArr    = new Canon_E193_TicketLinesObj[linesList.size()];
			Canon_E193_TicketSubLinesObj[]  subLinesArr = new Canon_E193_TicketSubLinesObj[subLinesList.size()];
			
			 for(int i=0;i<headerList.size();i++){
				 headerArr[i] = headerList.get(i);
			 }
			 
			 for(int i=0;i<linesList.size();i++){
				 linesArr[i] = linesList.get(i);
			 }
			 for(int i=0;i<subLinesList.size();i++){
				 subLinesArr[i] = subLinesList.get(i);
			 }
			
			 Canon_E193_BillingIssue biDao = new Canon_E193_BillingIssue();
			 //System.out.println( new Date().toString()+" [CanonCustCareUtil.execute ]  BEFORE Execute ");
			 
			 
			 alDtls = biDao.addBillHeaderLineSubLinesS21(headerArr, linesArr, subLinesArr, notes);
			
			 //System.out.println("alDtls in CanonCustCareUtil  = "+alDtls);
			 
		}catch(Exception e){
			System.err.println("Exception occurred in CanonCustCareUtil execute" +e.getMessage());
			//ticket=0;
		}
		
		return alDtls;
	}


 public String checkNull(String str){
	 if (str != null) {
	    return str;
	  }else {
		return "";
     }
		
}
		
}
