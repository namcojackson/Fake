package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqContractRec implements SQLData {
	
	private String  custNum;
	private String  custName ;                        
	private String  contractNumber   ;                     
	private String  contractType   ;                       
	private String  headerStartDate   ;                   
	private String  headerEndDate   ;                     
	private String  headerEffString  ;                    
	private String  lineStartDate   ;                     
	private String  lineEndDate   ;                       
	private String  lineEffString  ;                      
	private String  headerStatus   ;                       
	private String  lineStatus   ;                         
	private String  respTime ;   

	
	public String getCustNum() {
		return custNum;
	}



	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}



	public String getCustName() {
		return custName;
	}



	public void setCustName(String custName) {
		this.custName = custName;
	}



	public String getContractNumber() {
		return contractNumber;
	}



	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}



	public String getContractType() {
		return contractType;
	}



	public void setContractType(String contractType) {
		this.contractType = contractType;
	}



	public String getHeaderStartDate() {
		return headerStartDate;
	}



	public void setHeaderStartDate(String headerStartDate) {
		this.headerStartDate = headerStartDate;
	}



	public String getHeaderEndDate() {
		return headerEndDate;
	}



	public void setHeaderEndDate(String headerEndDate) {
		this.headerEndDate = headerEndDate;
	}



	public String getHeaderEffString() {
		return headerEffString;
	}

	public void setHeaderEffString(String headerEffString) {
		this.headerEffString = headerEffString;
	}

	public String getLineStartDate() {
		return lineStartDate;
	}
	public void setLineStartDate(String lineStartDate) {
		this.lineStartDate = lineStartDate;
	}
	public String getLineEndDate() {
		return lineEndDate;
	}
	public void setLineEndDate(String lineEndDate) {
		this.lineEndDate = lineEndDate;
	}
	public String getLineEffString() {
		return lineEffString;
	}
	public void setLineEffString(String lineEffString) {
		this.lineEffString = lineEffString;
	}
   public String getHeaderStatus() {
		return headerStatus;
	}
	public void setHeaderStatus(String headerStatus) {
		this.headerStatus = headerStatus;
	}
	public String getLineStatus() {
		return lineStatus;
	}
	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}
	public String getRespTime() {
		return respTime;
	}
	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}

	public CanonE307ServiceReqContractRec() {
	
	}


	public CanonE307ServiceReqContractRec(String custNum, String custName,
			String contractNumber, String contractType, String headerStartDate,
			String headerEndDate, String headerEffString, String lineStartDate,
			String lineEndDate, String lineEffString, String headerStatus,
			String lineStatus, String respTime) {
		super();
		this.custNum = custNum;
		this.custName = custName;
		this.contractNumber = contractNumber;
		this.contractType = contractType;
		this.headerStartDate = headerStartDate;
		this.headerEndDate = headerEndDate;
		this.headerEffString = headerEffString;
		this.lineStartDate = lineStartDate;
		this.lineEndDate = lineEndDate;
		this.lineEffString = lineEffString;
		this.headerStatus = headerStatus;
		this.lineStatus = lineStatus;
		this.respTime = respTime;
	}


	@Override
	public String toString() {
		return "CanonE307ServiceReqContractRec [custNum=" + custNum
				+ ", custName=" + custName + ", contractNumber="
				+ contractNumber + ", contractType=" + contractType
				+ ", headerStartDate=" + headerStartDate + ", headerEndDate="
				+ headerEndDate + ", headerEffString=" + headerEffString
				+ ", lineStartDate=" + lineStartDate + ", lineEndDate="
				+ lineEndDate + ", lineEffString=" + lineEffString
				+ ", headerStatus=" + headerStatus + ", lineStatus="
				+ lineStatus + ", respTime=" + respTime + "]";
	}

	
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_CONTRACT_REC";
	}


	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		custNum = stream.readString();
		custName =stream.readString();                          
		contractNumber   =stream.readString();                       
		contractType   =stream.readString();                         
		headerStartDate   =stream.readString();                     
		headerEndDate   =stream.readString();                       
		headerEffString  =stream.readString();                      
		lineStartDate   =stream.readString();                       
		lineEndDate   =stream.readString();                         
		lineEffString  =stream.readString();                        
		headerStatus   =stream.readString();                         
		lineStatus   =stream.readString();                           
		respTime =stream.readString();   
	}



	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(custNum);
		stream.writeString(custName );                        
		stream.writeString(contractNumber   );                     
		stream.writeString(contractType   );                       
		stream.writeString(headerStartDate   );                   
		stream.writeString(headerEndDate   );                     
		stream.writeString(headerEffString  );                    
		stream.writeString(lineStartDate   );                     
		stream.writeString(lineEndDate   );                       
		stream.writeString(lineEffString  );                      
		stream.writeString(headerStatus   );                       
		stream.writeString(lineStatus   );                         
		stream.writeString(respTime );  
		
	}

	
}
