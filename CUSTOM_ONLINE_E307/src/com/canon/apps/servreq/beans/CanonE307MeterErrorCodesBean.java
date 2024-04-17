package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307MeterErrorCodesBean {

	private String strMtrErrCd;
	private String strMtrErrMsg;
	private String strMtrSoftWarnFlg;
	private String strLowerRdFlg;
	
	public CanonE307MeterErrorCodesBean() {
		// TODO Auto-generated constructor stub
	}


	public CanonE307MeterErrorCodesBean(String strMtrErrCd,
			String strMtrErrMsg, String strMtrSoftWarnFlg, String strLowerRdFlg) {
		super();
		this.strMtrErrCd = strMtrErrCd;
		this.strMtrErrMsg = strMtrErrMsg;
		this.strMtrSoftWarnFlg = strMtrSoftWarnFlg;
		this.strLowerRdFlg = strLowerRdFlg;
	}


	public String getStrMtrErrCd() {
		return strMtrErrCd;
	}

	public void setStrMtrErrCd(String strMtrErrCd) {
		this.strMtrErrCd = strMtrErrCd;
	}

	public String getStrMtrErrMsg() {
		return strMtrErrMsg;
	}

	public void setStrMtrErrMsg(String strMtrErrMsg) {
		this.strMtrErrMsg = strMtrErrMsg;
	}
	
	public String getStrMtrSoftWarnFlg() {
		return strMtrSoftWarnFlg;
	}

	public void setStrMtrSoftWarnFlg(String strMtrSoftWarnFlg) {
		this.strMtrSoftWarnFlg = strMtrSoftWarnFlg;
	}
	

	public String getStrLowerRdFlg() {
		return strLowerRdFlg;
	}


	public void setStrLowerRdFlg(String strLowerRdFlg) {
		this.strLowerRdFlg = strLowerRdFlg;
	}


	@Override
	public String toString() {
		return "CanonE307MeterErrorCodesBean [strMtrErrCd=" + strMtrErrCd
				+ ", strMtrErrMsg=" + strMtrErrMsg + ", strMtrSoftWarnFlg="
				+ strMtrSoftWarnFlg + ", strLowerRdFlg=" + strLowerRdFlg + "]";
	}


	
}
