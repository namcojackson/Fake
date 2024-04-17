package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307DebriefInstallRec implements SQLData{
	private String strItemDesc;
	private String strItem;
	private String strModel;
	private String strModelId;
	private String strSerNum;
	private String strChkLstPk;
	private String strSvcMachMstr;
	private String strSvcConfigMstr;
	private String strIstlChkVerFlg;
	private String strCorctdSrl;
	private String strIstlCpltFlg;
	private String strNewItmFlg;
	private String strRmvItmFlg;
	private String strAttribute1;
	private String strAttribute2;
	private String strAttribute3;
	private String strAttribute4;
	private String strAttribute5;


	public CanonE307DebriefInstallRec() {
		// TODO Auto-generated constructor stub
	}


	public CanonE307DebriefInstallRec(String strItemDesc, String strItem,
			String strModel, String strModelId, String strSerNum,
			String strChkLstPk, String strSvcMachMstr, String strSvcConfigMstr,
			String strIstlChkVerFlg, String strCorctdSrl,
			String strIstlCpltFlg, String strNewItmFlg, String strRmvItmFlg,
			String strAttribute1, String strAttribute2, String strAttribute3,
			String strAttribute4, String strAttribute5) {
		super();
		this.strItemDesc = strItemDesc;
		this.strItem = strItem;
		this.strModel = strModel;
		this.strModelId = strModelId;
		this.strSerNum = strSerNum;
		this.strChkLstPk = strChkLstPk;
		this.strSvcMachMstr = strSvcMachMstr;
		this.strSvcConfigMstr = strSvcConfigMstr;
		this.strIstlChkVerFlg = strIstlChkVerFlg;
		this.strCorctdSrl = strCorctdSrl;
		this.strIstlCpltFlg = strIstlCpltFlg;
		this.strNewItmFlg = strNewItmFlg;
		this.strRmvItmFlg = strRmvItmFlg;
		this.strAttribute1 = strAttribute1;
		this.strAttribute2 = strAttribute2;
		this.strAttribute3 = strAttribute3;
		this.strAttribute4 = strAttribute4;
		this.strAttribute5 = strAttribute5;
	}




	public String getStrIstlCpltFlg() {
		return strIstlCpltFlg;
	}


	public void setStrIstlCpltFlg(String strIstlCpltFlg) {
		this.strIstlCpltFlg = strIstlCpltFlg;
	}


	public String getStrItemDesc() {
		return strItemDesc;
	}


	public void setStrItemDesc(String strItemDesc) {
		this.strItemDesc = strItemDesc;
	}


	public String getStrItem() {
		return strItem;
	}

	public void setStrItem(String strItem) {
		this.strItem = strItem;
	}

	public String getStrModel() {
		return strModel;
	}

	public void setStrModel(String strModel) {
		this.strModel = strModel;
	}

	public String getStrSerNum() {
		return strSerNum;
	}

	public void setStrSerNum(String strSerNum) {
		this.strSerNum = strSerNum;
	}
	
	public String getStrModelId() {
		return strModelId;
	}

	public void setStrModelId(String strModelId) {
		this.strModelId = strModelId;
	}

	public String getStrChkLstPk() {
		return strChkLstPk;
	}

	public void setStrChkLstPk(String strChkLstPk) {
		this.strChkLstPk = strChkLstPk;
	}

	public String getStrSvcMachMstr() {
		return strSvcMachMstr;
	}

	public void setStrSvcMachMstr(String strSvcMachMstr) {
		this.strSvcMachMstr = strSvcMachMstr;
	}

	public String getStrSvcConfigMstr() {
		return strSvcConfigMstr;
	}

	public void setStrSvcConfigMstr(String strSvcConfigMstr) {
		this.strSvcConfigMstr = strSvcConfigMstr;
	}
	

	public String getStrIstlChkVerFlg() {
		return strIstlChkVerFlg;
	}


	public void setStrIstlChkVerFlg(String strIstlChkVerFlg) {
		this.strIstlChkVerFlg = strIstlChkVerFlg;
	}
	
	public String getStrCorctdSrl() {
		return strCorctdSrl;
	}


	public void setStrCorctdSrl(String strCorctdSrl) {
		this.strCorctdSrl = strCorctdSrl;
	}


	public String getStrNewItmFlg() {
		return strNewItmFlg;
	}


	public void setStrNewItmFlg(String strNewItmFlg) {
		this.strNewItmFlg = strNewItmFlg;
	}
	

	public String getStrRmvItmFlg() {
		return strRmvItmFlg;
	}


	public void setStrRmvItmFlg(String strRmvItmFlg) {
		this.strRmvItmFlg = strRmvItmFlg;
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


	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_INSTALL_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String type) throws SQLException {
		strItemDesc= stream.readString();
		strItem= stream.readString();
		strModel= stream.readString();
		strModelId = stream.readString();
		strSerNum= stream.readString();
		strChkLstPk = stream.readString();
		strSvcMachMstr = stream.readString();
		strSvcConfigMstr = stream.readString();
		strIstlChkVerFlg = stream.readString();
		strCorctdSrl = stream.readString();
		strIstlCpltFlg = stream.readString();
		strNewItmFlg = stream.readString();
		strRmvItmFlg= stream.readString();
		strAttribute1 = stream.readString();
		strAttribute2 = stream.readString();
		strAttribute3 = stream.readString();
		strAttribute4 = stream.readString();
		strAttribute5 = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strItemDesc);
		stream.writeString(strItem);
		stream.writeString(strModel);
		stream.writeString(strModelId);
		stream.writeString(strSerNum);
		stream.writeString(strChkLstPk);
		stream.writeString(strSvcMachMstr);
		stream.writeString(strSvcConfigMstr);
		stream.writeString(strIstlChkVerFlg);
		stream.writeString(strCorctdSrl);
		stream.writeString(strIstlCpltFlg);
		stream.writeString(strNewItmFlg);
		stream.writeString(strRmvItmFlg);
		stream.writeString(strAttribute1);
		stream.writeString(strAttribute2);
		stream.writeString(strAttribute3);
		stream.writeString(strAttribute4);
		stream.writeString(strAttribute5);
	}


	@Override
	public String toString() {
		return "CanonE307DebriefInstallRec [strItemDesc=" + strItemDesc
				+ ", strItem=" + strItem + ", strModel=" + strModel
				+ ", strModelId=" + strModelId + ", strSerNum=" + strSerNum
				+ ", strChkLstPk=" + strChkLstPk + ", strSvcMachMstr="
				+ strSvcMachMstr + ", strSvcConfigMstr=" + strSvcConfigMstr
				+ ", strIstlChkVerFlg=" + strIstlChkVerFlg + ", strCorctdSrl="
				+ strCorctdSrl + ", strIstlCpltFlg=" + strIstlCpltFlg
				+ ", strNewItmFlg=" + strNewItmFlg + ", strRmvItmFlg="
				+ strRmvItmFlg + ", strAttribute1=" + strAttribute1
				+ ", strAttribute2=" + strAttribute2 + ", strAttribute3="
				+ strAttribute3 + ", strAttribute4=" + strAttribute4
				+ ", strAttribute5=" + strAttribute5 + "]";
	}



}
