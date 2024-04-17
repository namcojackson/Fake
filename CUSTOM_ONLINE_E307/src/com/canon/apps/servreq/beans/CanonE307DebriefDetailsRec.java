package com.canon.apps.servreq.beans;

import java.util.Date;
import java.sql.SQLData;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;

import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307DebriefDetailsRec  implements SQLData{
	private String strTaskNumber;
	private String strSRNumber;
	private String strTaskStatus;
	private String strSerialNumber;
	private String strTagNumber;
	private String strSolutionName;
	private String strCustomerCase;
	private String strITTNumber;
	private String strProblemCode;
	private String strCorrectionCode;
	private String strReasonCode;
	private String strLocationCode;
	private String strIWRCheck;
	private String strMachineStatus;
	private String strDebriefNumber;
	private String strDebriefStatus;
	private String strCreationDt;
	private String strCreatedBy;
	private String strLstUpdDt;
	private String strLastUpdatedBy;
	private String strAttribute1;
	private String strAttribute2;
	private String strAttribute3;
	private String strAttribute4;
	private String strAttribute5;
	private String strAttribute6;
	private String strAttribute7;
	private String strAttribute8;
	private String strAttribute9;
	private String strAttribute10;
	private String strTaskTypeCd;
	private String strAhsFlg;
	private String strLnBzTpCd;
	private String strCovTm;
	private String strOnlnValdFlg;
	private String strFutureDate;

	
	public String getSQLTypeName() {
        return CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_DATA_REC";
    }	
	public CanonE307DebriefDetailsRec() {
		// TODO Auto-generated constructor stub 
	}


	public CanonE307DebriefDetailsRec(String strTaskNumber, String strSRNumber,
			String strTaskStatus, String strSerialNumber, String strTagNumber,
			String strSolutionName, String strCustomerCase,
			String strITTNumber, String strProblemCode,
			String strCorrectionCode, String strReasonCode,
			String strLocationCode, String strIWRCheck,
			String strMachineStatus, String strDebriefNumber,
			String strDebriefStatus, String strCreationDt, String strCreatedBy,
			String strLstUpdDt, String strLastUpdatedBy, String strAttribute1,
			String strAttribute2, String strAttribute3, String strAttribute4,
			String strAttribute5, String strAttribute6, String strAttribute7,
			String strAttribute8, String strAttribute9, String strAttribute10,
			String strTaskTypeCd, String strAhsFlg, String strLnBzTpCd,
			String strCovTm, String strOnlnValdFlg, String strFutureDate) {
		super();
		this.strTaskNumber = strTaskNumber;
		this.strSRNumber = strSRNumber;
		this.strTaskStatus = strTaskStatus;
		this.strSerialNumber = strSerialNumber;
		this.strTagNumber = strTagNumber;
		this.strSolutionName = strSolutionName;
		this.strCustomerCase = strCustomerCase;
		this.strITTNumber = strITTNumber;
		this.strProblemCode = strProblemCode;
		this.strCorrectionCode = strCorrectionCode;
		this.strReasonCode = strReasonCode;
		this.strLocationCode = strLocationCode;
		this.strIWRCheck = strIWRCheck;
		this.strMachineStatus = strMachineStatus;
		this.strDebriefNumber = strDebriefNumber;
		this.strDebriefStatus = strDebriefStatus;
		this.strCreationDt = strCreationDt;
		this.strCreatedBy = strCreatedBy;
		this.strLstUpdDt = strLstUpdDt;
		this.strLastUpdatedBy = strLastUpdatedBy;
		this.strAttribute1 = strAttribute1;
		this.strAttribute2 = strAttribute2;
		this.strAttribute3 = strAttribute3;
		this.strAttribute4 = strAttribute4;
		this.strAttribute5 = strAttribute5;
		this.strAttribute6 = strAttribute6;
		this.strAttribute7 = strAttribute7;
		this.strAttribute8 = strAttribute8;
		this.strAttribute9 = strAttribute9;
		this.strAttribute10 = strAttribute10;
		this.strTaskTypeCd = strTaskTypeCd;
		this.strAhsFlg = strAhsFlg;
		this.strLnBzTpCd = strLnBzTpCd;
		this.strCovTm = strCovTm;
		this.strOnlnValdFlg = strOnlnValdFlg;
		this.strFutureDate = strFutureDate;
	}
	public String getStrTaskNumber() {
		return strTaskNumber;
	}
	public void setStrTaskNumber(String strTaskNumber) {
		this.strTaskNumber = strTaskNumber;
	}
	public String getStrSRNumber() {
		return strSRNumber;
	}
	public void setStrSRNumber(String strSRNumber) {
		this.strSRNumber = strSRNumber;
	}
	public String getStrTaskStatus() {
		return strTaskStatus;
	}
	public void setStrTaskStatus(String strTaskStatus) {
		this.strTaskStatus = strTaskStatus;
	}
	public String getStrSerialNumber() {
		return strSerialNumber;
	}
	public void setStrSerialNumber(String strSerialNumber) {
		this.strSerialNumber = strSerialNumber;
	}
	public String getStrTagNumber() {
		return strTagNumber;
	}
	public void setStrTagNumber(String strTagNumber) {
		this.strTagNumber = strTagNumber;
	}
	public String getStrSolutionName() {
		return strSolutionName;
	}
	public void setStrSolutionName(String strSolutionName) {
		this.strSolutionName = strSolutionName;
	}
	public String getStrCustomerCase() {
		return strCustomerCase;
	}
	public void setStrCustomerCase(String strCustomerCase) {
		this.strCustomerCase = strCustomerCase;
	}
	public String getStrITTNumber() {
		return strITTNumber;
	}
	public void setStrITTNumber(String strITTNumber) {
		this.strITTNumber = strITTNumber;
	}
/*	public String getStrModFlag() {
		return strModFlag;
	}
	public void setStrModFlag(String strModFlag) {
		this.strModFlag = strModFlag;
	}*/
	public String getStrProblemCode() {
		return strProblemCode;
	}
	public void setStrProblemCode(String strProblemCode) {
		this.strProblemCode = strProblemCode;
	}
	public String getStrCorrectionCode() {
		return strCorrectionCode;
	}
	public void setStrCorrectionCode(String strCorrectionCode) {
		this.strCorrectionCode = strCorrectionCode;
	}
	public String getStrReasonCode() {
		return strReasonCode;
	}
	public void setStrReasonCode(String strReasonCode) {
		this.strReasonCode = strReasonCode;
	}
	public String getStrLocationCode() {
		return strLocationCode;
	}
	public void setStrLocationCode(String strLocationCode) {
		this.strLocationCode = strLocationCode;
	}
	public String getStrIWRCheck() {
		return strIWRCheck;
	}
	public void setStrIWRCheck(String strIWRCheck) {
		this.strIWRCheck = strIWRCheck;
	}
	public String getStrMachineStatus() {
		return strMachineStatus;
	}
	public void setStrMachineStatus(String strMachineStatus) {
		this.strMachineStatus = strMachineStatus;
	}
	public String getStrCreatedBy() {
		return strCreatedBy;
	}
	public void setStrCreatedBy(String strCreatedBy) {
		this.strCreatedBy = strCreatedBy;
	}
	public String getStrLastUpdatedBy() {
		return strLastUpdatedBy;
	}
	public void setStrLastUpdatedBy(String strLastUpdatedBy) {
		this.strLastUpdatedBy = strLastUpdatedBy;
	}
	public String getStrDebriefNumber() {
		return strDebriefNumber;
	}
	public void setStrDebriefNumber(String strDebriefNumber) {
		this.strDebriefNumber = strDebriefNumber;
	}
	public String getStrDebriefStatus() {
		return strDebriefStatus;
	}
	public void setStrDebriefStatus(String strDebriefStatus) {
		this.strDebriefStatus = strDebriefStatus;
	}
	public String getStrCreationDt() {
		return strCreationDt;
	}
	public void setStrCreationDt(String strCreationDt) {
		this.strCreationDt = strCreationDt;
	}
	public String getStrLstUpdDt() {
		return strLstUpdDt;
	}
	public void setStrLstUpdDt(String strLstUpdDt) {
		this.strLstUpdDt = strLstUpdDt;
	}
	public String getStrAttribute1() {
		return strAttribute1;
	}
	public void setStrAttribute1(String strAttribute1) {
		this.strAttribute1 = strAttribute1;
	}
	public String getStrAttribute2() {
		return strAttribute2;
	}
	public void setStrAttribute2(String strAttribute2) {
		this.strAttribute2 = strAttribute2;
	}
	public String getStrAttribute3() {
		return strAttribute3;
	}
	public void setStrAttribute3(String strAttribute3) {
		this.strAttribute3 = strAttribute3;
	}
	public String getStrAttribute4() {
		return strAttribute4;
	}
	public void setStrAttribute4(String strAttribute4) {
		this.strAttribute4 = strAttribute4;
	}
	public String getStrAttribute5() {
		return strAttribute5;
	}
	public void setStrAttribute5(String strAttribute5) {
		this.strAttribute5 = strAttribute5;
	}
	public String getStrAttribute6() {
		return strAttribute6;
	}
	public void setStrAttribute6(String strAttribute6) {
		this.strAttribute6 = strAttribute6;
	}
	public String getStrAttribute7() {
		return strAttribute7;
	}
	public void setStrAttribute7(String strAttribute7) {
		this.strAttribute7 = strAttribute7;
	}
	public String getStrAttribute8() {
		return strAttribute8;
	}
	public void setStrAttribute8(String strAttribute8) {
		this.strAttribute8 = strAttribute8;
	}
	public String getStrAttribute9() {
		return strAttribute9;
	}
	public void setStrAttribute9(String strAttribute9) {
		this.strAttribute9 = strAttribute9;
	}
	public String getStrAttribute10() {
		return strAttribute10;
	}
	public void setStrAttribute10(String strAttribute10) {
		this.strAttribute10 = strAttribute10;
	}
	public String getStrTaskTypeCd() {
		return strTaskTypeCd;
	}
	public void setStrTaskTypeCd(String strTaskTypeCd) {
		this.strTaskTypeCd = strTaskTypeCd;
	}
	public String getStrAhsFlg() {
		return strAhsFlg;
	}
	public void setStrAhsFlg(String strAhsFlg) {
		this.strAhsFlg = strAhsFlg;
	}
	public String getStrLnBzTpCd() {
		return strLnBzTpCd;
	}
	public void setStrLnBzTpCd(String strLnBzTpCd) {
		this.strLnBzTpCd = strLnBzTpCd;
	}
	public String getStrCovTm() {
		return strCovTm;
	}
	public void setStrCovTm(String strCovTm) {
		this.strCovTm = strCovTm;
	}
	public String getStrOnlnValdFlg() {
		return strOnlnValdFlg;
	}
	public void setStrOnlnValdFlg(String strOnlnValdFlg) {
		this.strOnlnValdFlg = strOnlnValdFlg;
	}
	public String getStrFutureDate() {
		return strFutureDate;
	}
	public void setStrFutureDate(String strFutureDate) {
		this.strFutureDate = strFutureDate;
	}
	public void readSQL(SQLInput stream, String type) throws SQLException {
		   strTaskNumber = stream.readString();
		   strSRNumber = stream.readString();
		   strTaskStatus = stream.readString();
		   strSerialNumber = stream.readString();
		   strTagNumber = stream.readString();
		   strSolutionName = stream.readString();
		   strCustomerCase = stream.readString();
		   strITTNumber = stream.readString();
		 // strModFlag = stream.readString();
		   strProblemCode = stream.readString();
		   strCorrectionCode = stream.readString();
		   strReasonCode = stream.readString();
		   strLocationCode = stream.readString();
		   strIWRCheck = stream.readString();
		   strMachineStatus = stream.readString();
		   strDebriefNumber = stream.readString();
		   strDebriefStatus = stream.readString();
		   strCreationDt = stream.readString();
		   strCreatedBy = stream.readString();
		   strLstUpdDt = stream.readString();
		   strLastUpdatedBy = stream.readString();
		   strAttribute1 = stream.readString();
		   strAttribute2 = stream.readString();
		   strAttribute3 = stream.readString();
		   strAttribute4 = stream.readString();
		   strAttribute5 = stream.readString();
		   strAttribute6 = stream.readString();
		   strAttribute7 = stream.readString();
		   strAttribute8 = stream.readString();
		   strAttribute9 = stream.readString();
		   strAttribute10 = stream.readString();
		   strTaskTypeCd = stream.readString();
		   strAhsFlg = stream.readString();
		   strLnBzTpCd = stream.readString();
		   strCovTm = stream.readString();
		   strOnlnValdFlg = stream.readString();
		   strFutureDate = stream.readString();
	}

	    public void writeSQL(SQLOutput stream) throws SQLException {
	    	java.sql.Date sqlObj = null;
	        stream.writeString(strTaskNumber);
	        stream.writeString(strSRNumber);
	        stream.writeString(strTaskStatus);
	        stream.writeString(strSerialNumber);
	        stream.writeString(strTagNumber);
	        stream.writeString(strSolutionName);
	        stream.writeString(strCustomerCase);
	        stream.writeString(strITTNumber);
	    //    stream.writeString(strModFlag);
	        stream.writeString(strProblemCode);
	        stream.writeString(strCorrectionCode);
	        stream.writeString(strReasonCode);
	        stream.writeString(strLocationCode);
	        stream.writeString(strIWRCheck);
	        stream.writeString(strMachineStatus);
	        stream.writeString(strDebriefNumber);
	        stream.writeString(strDebriefStatus);
	        stream.writeString(strCreationDt);
	        stream.writeString(strCreatedBy);
	        stream.writeString(strLstUpdDt);
	        stream.writeString(strLastUpdatedBy);	
	        stream.writeString(strAttribute1);
	        stream.writeString(strAttribute2);
	        stream.writeString(strAttribute3);
	        stream.writeString(strAttribute4);
	        stream.writeString(strAttribute5);	
	    	stream.writeString(strAttribute6);
	    	stream.writeString(strAttribute7);
	    	stream.writeString(strAttribute8);
	    	stream.writeString(strAttribute9);
	    	stream.writeString(strAttribute10);
	    	stream.writeString(strTaskTypeCd);
	    	stream.writeString(strAhsFlg);
	    	stream.writeString(strLnBzTpCd);
	    	stream.writeString(strCovTm);
	    	stream.writeString(strOnlnValdFlg);
	    	stream.writeString(strFutureDate);
	    }	
	   public java.sql.Date getSqlParsedDate(Date dateObj){
		 java.sql.Date sqlObj = null;
	      if(dateObj!=null){
	        try{
	          sqlObj =  new java.sql.Date(dateObj.getTime())  ;
	        }catch(Exception ex){
	          sqlObj = null;
	        }
	      }
	      return sqlObj;
	   }	    
}
