package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.util.Date;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;

import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307DebriefLaborDetailsRec implements SQLData{

	private String strFVTmEvntPk;
	private String strFsrNum;
	private String strFsrVstNum;
	private String strSvcTmEvntCd;
	private String strTaskNumber;
	private String strSvcLbrChgFlg;
	private String strLbrItmNum;
	private String strLbrItmDesc;
	private String strStartDt;
	private String strStartTm;
	private String strEndDt;
	private String strEndTm;
	private double lbrDuration;
	private String strModNum;
	private String strSerialNbr;
	private String strModItm;
	private String strComments;
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
	
	public String getSQLTypeName() {
        return CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_LABOR_REC";
    }	
	
	public CanonE307DebriefLaborDetailsRec() {
		// TODO Auto-generated constructor stub 
	} 

	public CanonE307DebriefLaborDetailsRec(String strFVTmEvntPk,
			String strFsrNum, String strFsrVstNum, String strSvcTmEvntCd,
			String strTaskNumber, String strSvcLbrChgFlg, String strLbrItmNum,
			String strLbrItmDesc, String strStartDt, String strStartTm,
			String strEndDt, String strEndTm, double lbrDuration,
			String strModNum, String strSerialNbr, String strModItm,
			String strComments, String strAttribute1, String strAttribute2,
			String strAttribute3, String strAttribute4, String strAttribute5,
			String strAttribute6, String strAttribute7, String strAttribute8,
			String strAttribute9, String strAttribute10) {
		super();
		this.strFVTmEvntPk = strFVTmEvntPk;
		this.strFsrNum = strFsrNum;
		this.strFsrVstNum = strFsrVstNum;
		this.strSvcTmEvntCd = strSvcTmEvntCd;
		this.strTaskNumber = strTaskNumber;
		this.strSvcLbrChgFlg = strSvcLbrChgFlg;
		this.strLbrItmNum = strLbrItmNum;
		this.strLbrItmDesc = strLbrItmDesc;
		this.strStartDt = strStartDt;
		this.strStartTm = strStartTm;
		this.strEndDt = strEndDt;
		this.strEndTm = strEndTm;
		this.lbrDuration = lbrDuration;
		this.strModNum = strModNum;
		this.strSerialNbr = strSerialNbr;
		this.strModItm = strModItm;
		this.strComments = strComments;
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
	}

	public String getStrFVTmEvntPk() {
		return strFVTmEvntPk;
	}

	public void setStrFVTmEvntPk(String strFVTmEvntPk) {
		this.strFVTmEvntPk = strFVTmEvntPk;
	}

	public String getStrFsrNum() {
		return strFsrNum;
	}

	public void setStrFsrNum(String strFsrNum) {
		this.strFsrNum = strFsrNum;
	}

	public String getStrFsrVstNum() {
		return strFsrVstNum;
	}

	public void setStrFsrVstNum(String strFsrVstNum) {
		this.strFsrVstNum = strFsrVstNum;
	}

	public String getStrSvcTmEvntCd() {
		return strSvcTmEvntCd;
	}

	public void setStrSvcTmEvntCd(String strSvcTmEvntCd) {
		this.strSvcTmEvntCd = strSvcTmEvntCd;
	}

	public String getStrTaskNumber() {
		return strTaskNumber;
	}

	public void setStrTaskNumber(String strTaskNumber) {
		this.strTaskNumber = strTaskNumber;
	}

	public String getStrSvcLbrChgFlg() {
		return strSvcLbrChgFlg;
	}

	public void setStrSvcLbrChgFlg(String strSvcLbrChgFlg) {
		this.strSvcLbrChgFlg = strSvcLbrChgFlg;
	}

	public String getStrLbrItmNum() {
		return strLbrItmNum;
	}

	public void setStrLbrItmNum(String strLbrItmNum) {
		this.strLbrItmNum = strLbrItmNum;
	}

	public String getStrLbrItmDesc() {
		return strLbrItmDesc;
	}

	public void setStrLbrItmDesc(String strLbrItmDesc) {
		this.strLbrItmDesc = strLbrItmDesc;
	}

	public String getStrStartDt() {
		return strStartDt;
	}

	public void setStrStartDt(String strStartDt) {
		this.strStartDt = strStartDt;
	}

	public String getStrStartTm() {
		return strStartTm;
	}

	public void setStrStartTm(String strStartTm) {
		this.strStartTm = strStartTm;
	}

	public String getStrEndDt() {
		return strEndDt;
	}

	public void setStrEndDt(String strEndDt) {
		this.strEndDt = strEndDt;
	}

	public String getStrEndTm() {
		return strEndTm;
	}

	public void setStrEndTm(String strEndTm) {
		this.strEndTm = strEndTm;
	}

	public double getLbrDuration() {
		return lbrDuration;
	}

	public void setLbrDuration(double lbrDuration) {
		this.lbrDuration = lbrDuration;
	}

	public String getStrModNum() {
		return strModNum;
	}

	public void setStrModNum(String strModNum) {
		this.strModNum = strModNum;
	}

	public String getStrSerialNbr() {
		return strSerialNbr;
	}

	public void setStrSerialNbr(String strSerialNbr) {
		this.strSerialNbr = strSerialNbr;
	}

	public String getStrModItm() {
		return strModItm;
	}

	public void setStrModItm(String strModItm) {
		this.strModItm = strModItm;
	}

	public String getStrComments() {
		return strComments;
	}

	public void setStrComments(String strComments) {
		this.strComments = strComments;
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

	public void readSQL(SQLInput stream, String type) throws SQLException {
		   strFVTmEvntPk = stream.readString();
		   strTaskNumber = stream.readString();
		   strFsrVstNum = stream.readString();
		   strSvcTmEvntCd = stream.readString();
		   strTaskNumber = stream.readString();
		   strSvcLbrChgFlg = stream.readString();
		   strLbrItmNum = stream.readString();
		   strLbrItmDesc = stream.readString();
		   strStartDt = stream.readString();
		   strStartTm = stream.readString();
		   strEndDt = stream.readString();
		   strEndTm = stream.readString();
		   lbrDuration = stream.readDouble();
		   strModNum = stream.readString();
		   strSerialNbr = stream.readString();
		   strModItm = stream.readString();
		   strComments = stream.readString();
		   strAttribute1=stream.readString();
		   strAttribute2=stream.readString();
		   strAttribute3=stream.readString();
		   strAttribute4=stream.readString();
		   strAttribute5=stream.readString();
		   strAttribute6=stream.readString();
		   strAttribute7=stream.readString();
		   strAttribute8=stream.readString();
		   strAttribute9=stream.readString();
		   strAttribute10=stream.readString();
	       }

	    public void writeSQL(SQLOutput stream) throws SQLException {
	    	java.sql.Date sqlObj = null;
	        stream.writeString(strFVTmEvntPk);
	        stream.writeString(strTaskNumber);
	        stream.writeString(strFsrVstNum);
	        stream.writeString(strSvcTmEvntCd);
	        stream.writeString(strTaskNumber);
	        stream.writeString(strSvcLbrChgFlg);
	        stream.writeString(strLbrItmNum);
	        stream.writeString(strLbrItmDesc);
	        stream.writeString(strStartDt);
	        stream.writeString(strStartTm);
	        stream.writeString(strEndDt);
	        stream.writeString(strEndTm);
	        stream.writeDouble(lbrDuration);
	        stream.writeString(strModNum);
	        stream.writeString(strSerialNbr);
	        stream.writeString(strModItm);
	        stream.writeString(strComments);
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
