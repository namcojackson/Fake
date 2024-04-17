/**
 * 
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC104001;

import java.math.BigDecimal;

/**
 * <pre>
 * ShpgPlnCompBean
 * </pre>
 * 
 * <pre>
 *  Date         Company         Name            Create/Update   Defect No
 *  ----------------------------------------------------------------------
 *  2009/07/07   Fujitsu         A.Suda          Create          N/A
 * </pre>
 */
public class ShpgPlnCompBean {

	/** GLBL_CMPY_CD */
	private String glblCmpyCd;

	/** MDSE_CD */
	private String mdseCd;

	/** TRX_HDR_NUM */
	private String trxHdrNum;

	/** TRX_LINE_NUM */
	private String trxLineNum;

	/** TRX_LINE_SUB_NUM */
	private String trxLineSubNum;

	/** SHPG_STS_CD */
	private String shpgStsCd;

	/** SHPG_PLN_NUM */
	private String shpgPlnNum;

	/** ODR_QTY */
	private BigDecimal ordQty;

	/** SET_MDSE_CD */
	private String setMdseCd;

	/** SOFT_ALLOC_QTY */
	private BigDecimal sotyAllocQty;

	/** PO_REQ_FLG */
	private String poReqFlg;

	public String getGlblCmpyCd() {
		return glblCmpyCd;
	}

	public void setGlblCmpyCd(String glblCmpyCd) {
		this.glblCmpyCd = glblCmpyCd;
	}

	public String getMdseCd() {
		return mdseCd;
	}

	public void setMdseCd(String mdseCd) {
		this.mdseCd = mdseCd;
	}

	public BigDecimal getOrdQty() {
		return ordQty;
	}

	public void setOrdQty(BigDecimal ordQty) {
		this.ordQty = ordQty;
	}

	public String getPoReqFlg() {
		return poReqFlg;
	}

	public void setPoReqFlg(String poReqFlg) {
		this.poReqFlg = poReqFlg;
	}

	public String getSetMdseCd() {
		return setMdseCd;
	}

	public void setSetMdseCd(String setMdseCd) {
		this.setMdseCd = setMdseCd;
	}

	public String getShpgPlnNum() {
		return shpgPlnNum;
	}

	public void setShpgPlnNum(String shpgPlnNum) {
		this.shpgPlnNum = shpgPlnNum;
	}

	public String getShpgStsCd() {
		return shpgStsCd;
	}

	public void setShpgStsCd(String shpgStsCd) {
		this.shpgStsCd = shpgStsCd;
	}

	public BigDecimal getSotyAllocQty() {
		return sotyAllocQty;
	}

	public void setSotyAllocQty(BigDecimal sotyAllocQty) {
		this.sotyAllocQty = sotyAllocQty;
	}

	public String getTrxHdrNum() {
		return trxHdrNum;
	}

	public void setTrxHdrNum(String trxHdrNum) {
		this.trxHdrNum = trxHdrNum;
	}

	public String getTrxLineNum() {
		return trxLineNum;
	}

	public void setTrxLineNum(String trxLineNum) {
		this.trxLineNum = trxLineNum;
	}

	public String getTrxLineSubNum() {
		return trxLineSubNum;
	}

	public void setTrxLineSubNum(String trxLineSubNum) {
		this.trxLineSubNum = trxLineSubNum;
	}

}
