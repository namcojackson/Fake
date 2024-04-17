package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

import canon.apps.common.CanonConstants;

public class CanonE307DebriefExpensesRec implements SQLData{

	private String strExpenseLineId;
	private String strFsrNbr;
	private String strFsrVstNum;
	private String strTaskNumber;
	private String strExpItmNum;
	private String strExpItmDesc;
	private String strExpSrvcDt;
	private int expQty;
	private String strExpUom;
	private String strPrtChrgFlg;
	private String strExpComments;
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
	
	public CanonE307DebriefExpensesRec() {
		// TODO Auto-generated constructor stub 
	}

	public CanonE307DebriefExpensesRec(String strExpenseLineId,
			String strFsrNbr, String strFsrVstNum, String strTaskNumber,
			String strExpItmNum, String strExpItmDesc, String strExpSrvcDt,
			int expQty, String strExpUom, String strPrtChrgFlg,
			String strExpComments, String strAttribute1, String strAttribute2,
			String strAttribute3, String strAttribute4, String strAttribute5,
			String strAttribute6, String strAttribute7, String strAttribute8,
			String strAttribute9, String strAttribute10) {
		super();
		this.strExpenseLineId = strExpenseLineId;
		this.strFsrNbr = strFsrNbr;
		this.strFsrVstNum = strFsrVstNum;
		this.strTaskNumber = strTaskNumber;
		this.strExpItmNum = strExpItmNum;
		this.strExpItmDesc = strExpItmDesc;
		this.strExpSrvcDt = strExpSrvcDt;
		this.expQty = expQty;
		this.strExpUom = strExpUom;
		this.strPrtChrgFlg = strPrtChrgFlg;
		this.strExpComments = strExpComments;
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


	public String getStrExpenseLineId() {
		return strExpenseLineId;
	}
	
	public void setStrExpenseLineId(String strExpenseLineId) {
		this.strExpenseLineId = strExpenseLineId;
	}
	
	public String getStrFsrNbr() {
		return strFsrNbr;
	}
	
	public void setStrFsrNbr(String strFsrNbr) {
		this.strFsrNbr = strFsrNbr;
	}
	
	public String getStrFsrVstNum() {
		return strFsrVstNum;
	}
	
	public void setStrFsrVstNum(String strFsrVstNum) {
		this.strFsrVstNum = strFsrVstNum;
	}
	
	public String getStrTaskNumber() {
		return strTaskNumber;
	}
	
	public void setStrTaskNumber(String strTaskNumber) {
		this.strTaskNumber = strTaskNumber;
	}
	
	public String getStrExpItmNum() {
		return strExpItmNum;
	}
	
	public void setStrExpItmNum(String strExpItmNum) {
		this.strExpItmNum = strExpItmNum;
	}
	
	public String getStrExpItmDesc() {
		return strExpItmDesc;
	}
	
	public void setStrExpItmDesc(String strExpItmDesc) {
		this.strExpItmDesc = strExpItmDesc;
	}
	
	public String getStrExpSrvcDt() {
		return strExpSrvcDt;
	}
	
	public void setStrExpSrvcDt(String strExpSrvcDt) {
		this.strExpSrvcDt = strExpSrvcDt;
	}
	
	public int getExpQty() {
		return expQty;
	}
	
	public void setExpQty(int expQty) {
		this.expQty = expQty;
	}
	
	public String getStrExpUom() {
		return strExpUom;
	}
	
	public void setStrExpUom(String strExpUom) {
		this.strExpUom = strExpUom;
	}
	
	public String getStrPrtChrgFlg() {
		return strPrtChrgFlg;
	}
	
	public void setStrPrtChrgFlg(String strPrtChrgFlg) {
		this.strPrtChrgFlg = strPrtChrgFlg;
	}
	
	public String getStrExpComments() {
		return strExpComments;
	}
	
	public void setStrExpComments(String strExpComments) {
		this.strExpComments = strExpComments;
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
		   strExpenseLineId= stream.readString();
		   strFsrNbr = stream.readString();
		   strFsrVstNum=stream.readString();
		   strTaskNumber = stream.readString();
		   strExpItmNum = stream.readString();
		   strExpItmDesc = stream.readString();
		   strExpSrvcDt = stream.readString();
		   expQty = stream.readInt();
		   strExpUom = stream.readString();
		   strPrtChrgFlg = stream.readString();
		   strExpComments = stream.readString();
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
	 }
	 public void writeSQL(SQLOutput stream) throws SQLException {
		 	stream.writeString(strExpenseLineId);
		 	stream.writeString(strFsrNbr);
		 	stream.writeString(strFsrVstNum);
	        stream.writeString(strTaskNumber);
	        stream.writeString(strExpItmNum);
	        stream.writeString(strExpItmDesc);
	        stream.writeString(strExpSrvcDt);
	        stream.writeInt(expQty);
	        stream.writeString(strExpUom);
	        stream.writeString((strPrtChrgFlg));
	        stream.writeString(strExpComments);	
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
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_EXPENSE_REC";
	}	    		
}
