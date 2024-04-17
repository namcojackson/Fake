package com.canon.apps.servreq.beans;

import java.math.BigDecimal;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

import canon.apps.common.CanonConstants;


public class CanonE307DebriefPartsRec implements SQLData{
	private String strFsrNum;
	private String strFsrVstNum;
	private String strSvcTskNum;
	private String strFsrUsgPk;
	private String strSvcPrtChrgFlg;
	private String strPrtByTechId;
	private String strPartItmNum;
	private String strPartItmDesc;
	private String strSrvcDt;
	private int quantity;
	private String strUom;
	private String strMod;
	private String strSrlNum;
	private String strModItm;
	private String strPrtComments;
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
        return CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_PART_REC";
    }	

	public CanonE307DebriefPartsRec() {
		// TODO Auto-generated constructor stub 
	}

	public CanonE307DebriefPartsRec(String strFsrNum, String strFsrVstNum,
			String strSvcTskNum, String strFsrUsgPk, String strSvcPrtChrgFlg,
			String strPrtByTechId, String strPartItmNum, String strPartItmDesc,
			String strSrvcDt, int quantity, String strUom, String strMod,
			String strSrlNum, String strModItm, String strPrtComments,
			String strAttribute1, String strAttribute2, String strAttribute3,
			String strAttribute4, String strAttribute5, String strAttribute6,
			String strAttribute7, String strAttribute8, String strAttribute9,
			String strAttribute10) {
		super();
		this.strFsrNum = strFsrNum;
		this.strFsrVstNum = strFsrVstNum;
		this.strSvcTskNum = strSvcTskNum;
		this.strFsrUsgPk = strFsrUsgPk;
		this.strSvcPrtChrgFlg = strSvcPrtChrgFlg;
		this.strPrtByTechId = strPrtByTechId;
		this.strPartItmNum = strPartItmNum;
		this.strPartItmDesc = strPartItmDesc;
		this.strSrvcDt = strSrvcDt;
		this.quantity = quantity;
		this.strUom = strUom;
		this.strMod = strMod;
		this.strSrlNum = strSrlNum;
		this.strModItm = strModItm;
		this.strPrtComments = strPrtComments;
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

	public String getStrSvcTskNum() {
		return strSvcTskNum;
	}

	public void setStrSvcTskNum(String strSvcTskNum) {
		this.strSvcTskNum = strSvcTskNum;
	}

	public String getStrFsrUsgPk() {
		return strFsrUsgPk;
	}

	public void setStrFsrUsgPk(String strFsrUsgPk) {
		this.strFsrUsgPk = strFsrUsgPk;
	}

	public String getStrSvcPrtChrgFlg() {
		return strSvcPrtChrgFlg;
	}

	public void setStrSvcPrtChrgFlg(String strSvcPrtChrgFlg) {
		this.strSvcPrtChrgFlg = strSvcPrtChrgFlg;
	}

	public String getStrPrtByTechId() {
		return strPrtByTechId;
	}

	public void setStrPrtByTechId(String strPrtByTechId) {
		this.strPrtByTechId = strPrtByTechId;
	}

	public String getStrPartItmNum() {
		return strPartItmNum;
	}

	public void setStrPartItmNum(String strPartItmNum) {
		this.strPartItmNum = strPartItmNum;
	}

	public String getStrPartItmDesc() {
		return strPartItmDesc;
	}

	public void setStrPartItmDesc(String strPartItmDesc) {
		this.strPartItmDesc = strPartItmDesc;
	}

	public String getStrSrvcDt() {
		return strSrvcDt;
	}

	public void setStrSrvcDt(String strSrvcDt) {
		this.strSrvcDt = strSrvcDt;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStrUom() {
		return strUom;
	}

	public void setStrUom(String strUom) {
		this.strUom = strUom;
	}

	public String getStrMod() {
		return strMod;
	}

	public void setStrMod(String strMod) {
		this.strMod = strMod;
	}

	public String getStrSrlNum() {
		return strSrlNum;
	}

	public void setStrSrlNum(String strSrlNum) {
		this.strSrlNum = strSrlNum;
	}

	public String getStrModItm() {
		return strModItm;
	}

	public void setStrModItm(String strModItm) {
		this.strModItm = strModItm;
	}

	public String getStrPrtComments() {
		return strPrtComments;
	}

	public void setStrPrtComments(String strPrtComments) {
		this.strPrtComments = strPrtComments;
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
		strFsrNum = stream.readString();
		strFsrVstNum = stream.readString();
		strSvcTskNum = stream.readString();
		strFsrUsgPk = stream.readString();
		strSvcPrtChrgFlg = stream.readString();
		strPrtByTechId = stream.readString();
		strPartItmNum = stream.readString();
		strPartItmDesc = stream.readString();
		strSrvcDt = stream.readString();
		quantity = stream.readInt();
		strUom = stream.readString();
		strMod = stream.readString();
		strSrlNum = stream.readString();
		strModItm = stream.readString();
		strPrtComments = stream.readString();
		strAttribute1 =stream.readString();
		strAttribute2 =stream.readString();
		strAttribute3 =stream.readString();
		strAttribute4 =stream.readString();
		strAttribute5 =stream.readString();
		strAttribute6 =stream.readString();
		strAttribute7 =stream.readString();
		strAttribute8 =stream.readString();
		strAttribute9 =stream.readString();
		strAttribute10 =stream.readString();
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(strFsrNum);
        stream.writeString(strFsrVstNum);
        stream.writeString(strSvcTskNum);        
        stream.writeString(strFsrUsgPk);
        stream.writeString(strSvcPrtChrgFlg);
        stream.writeString(strPrtByTechId);
        stream.writeString(strPartItmNum);
        stream.writeString(strPartItmDesc);
        stream.writeString(strSrvcDt);
        stream.writeInt(quantity);
        stream.writeString(strUom);
        stream.writeString(strMod);
        stream.writeString(strSrlNum);
        stream.writeString(strModItm);
        stream.writeString(strPrtComments);
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
}
