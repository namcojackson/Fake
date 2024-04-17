// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20180104145220000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0150Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NWCL0150;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NWCL0150 is data bean class.
 */
public class NWCL0150Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** CPO_ORD_NUM*/
	public static final String cpoOrdNum = "cpoOrdNum";

	/** INV_NUM*/
	public static final String invNum = "invNum";

	/** CFS_LEASE_PKG_HLD_FLG_HF*/
	public static final String cfsLeasePkgHldFlg_HF = "cfsLeasePkgHldFlg_HF";

	/** XX_FLG_NM_HF*/
	public static final String xxFlgNm_HF = "xxFlgNm_HF";

	/** CFS_LEASE_PKG_HLD_FLG_SL*/
	public static final String cfsLeasePkgHldFlg_SL = "cfsLeasePkgHldFlg_SL";

	/** LEASE_PKG_CRAT_FLG_CF*/
	public static final String leasePkgCratFlg_CF = "leasePkgCratFlg_CF";

	/** XX_FLG_NM_CF*/
	public static final String xxFlgNm_CF = "xxFlgNm_CF";

	/** LEASE_PKG_CRAT_FLG_SL*/
	public static final String leasePkgCratFlg_SL = "leasePkgCratFlg_SL";

	/** ATTRB_VAL_NUM*/
	public static final String attrbValNum = "attrbValNum";

	/** A*/
	public static final String A = "A";

	/** CFS_PO_NUM_A1*/
	public static final String cfsPoNum_A1 = "cfsPoNum_A1";

	/** CPO_ORD_NUM_A1*/
	public static final String cpoOrdNum_A1 = "cpoOrdNum_A1";

	/** DS_ACCT_NUM_A1*/
	public static final String dsAcctNum_A1 = "dsAcctNum_A1";

	/** DS_ACCT_NM_A1*/
	public static final String dsAcctNm_A1 = "dsAcctNm_A1";

	/** CFS_PO_AMT_A1*/
	public static final String cfsPoAmt_A1 = "cfsPoAmt_A1";

	/** INV_CPLT_AMT_RATE_A1*/
	public static final String invCpltAmtRate_A1 = "invCpltAmtRate_A1";

	/** CR_REBIL_HLD_FLG_A1*/
	public static final String crRebilHldFlg_A1 = "crRebilHldFlg_A1";

	/** CFS_LEASE_PKG_HLD_FLG_A1*/
	public static final String cfsLeasePkgHldFlg_A1 = "cfsLeasePkgHldFlg_A1";

	/** LEASE_PKG_CRAT_FLG_A1*/
	public static final String leasePkgCratFlg_A1 = "leasePkgCratFlg_A1";

	/** PO_INFO_PROC_FLG_A1*/
	public static final String poInfoProcFlg_A1 = "poInfoProcFlg_A1";

	/** CFS_MAN_HLD_ACT_TP_CD_A1*/
	public static final String cfsManHldActTpCd_A1 = "cfsManHldActTpCd_A1";

	/** CFS_LEASE_PKG_PO_HDR_PK_A1*/
	public static final String cfsLeasePkgPoHdrPk_A1 = "cfsLeasePkgPoHdrPk_A1";

	/** _EZUpdateDateTime_A1*/
	public static final String ezUpTime_A1 = "ezUpTime_A1";

	/** _EZUpTimeZone_A1*/
	public static final String ezUpTimeZone_A1 = "ezUpTimeZone_A1";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** XX_PAGE_SHOW_CUR_NUM*/
	public static final String xxPageShowCurNum = "xxPageShowCurNum";

	/** XX_PAGE_SHOW_TOT_NUM*/
	public static final String xxPageShowTotNum = "xxPageShowTotNum";

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/**
	 * Method name:NWCL0150 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NWCL0150Bean is generated
	 * <dd>Remarks:
	 */
	public NWCL0150Bean() {
		super("business.servlet.NWCL0150.NWCL0150BMsg");
	}

	/**
	 * get  CPO_ORD_NUM.
	 * @return CPO_ORD_NUM
	 */
	public String getCpoOrdNum() {
		return outputValue(cpoOrdNum);
	}

	/**
	 * set  CPO_ORD_NUM.
	 * @param data CPO_ORD_NUM
	 */
	public void setCpoOrdNum(String data) {
		inputValue(cpoOrdNum,data);
	}

	/**
	 * get  INV_NUM.
	 * @return INV_NUM
	 */
	public String getInvNum() {
		return outputValue(invNum);
	}

	/**
	 * set  INV_NUM.
	 * @param data INV_NUM
	 */
	public void setInvNum(String data) {
		inputValue(invNum,data);
	}

	/**
	 * get  CFS_LEASE_PKG_HLD_FLG_HF.
	 * @param idx1 Index Number
	 * @return CFS_LEASE_PKG_HLD_FLG_HF
	 */
	public String getCfsLeasePkgHldFlg_HF(int idx1) {
	 	 return outputValue(cfsLeasePkgHldFlg_HF, idx1);
	}

	/**
	 * get  XX_FLG_NM_HF.
	 * @param idx1 Index Number
	 * @return XX_FLG_NM_HF
	 */
	public String getXxFlgNm_HF(int idx1) {
	 	 return outputValue(xxFlgNm_HF, idx1);
	}

	/**
	 * get  CFS_LEASE_PKG_HLD_FLG_SL.
	 * @return CFS_LEASE_PKG_HLD_FLG_SL
	 */
	public String getCfsLeasePkgHldFlg_SL() {
		return outputValue(cfsLeasePkgHldFlg_SL);
	}

	/**
	 * set  CFS_LEASE_PKG_HLD_FLG_SL.
	 * @param data CFS_LEASE_PKG_HLD_FLG_SL
	 */
	public void setCfsLeasePkgHldFlg_SL(String data) {
		inputValue(cfsLeasePkgHldFlg_SL,data);
	}

	/**
	 * get  LEASE_PKG_CRAT_FLG_CF.
	 * @param idx1 Index Number
	 * @return LEASE_PKG_CRAT_FLG_CF
	 */
	public String getLeasePkgCratFlg_CF(int idx1) {
	 	 return outputValue(leasePkgCratFlg_CF, idx1);
	}

	/**
	 * get  XX_FLG_NM_CF.
	 * @param idx1 Index Number
	 * @return XX_FLG_NM_CF
	 */
	public String getXxFlgNm_CF(int idx1) {
	 	 return outputValue(xxFlgNm_CF, idx1);
	}

	/**
	 * get  LEASE_PKG_CRAT_FLG_SL.
	 * @return LEASE_PKG_CRAT_FLG_SL
	 */
	public String getLeasePkgCratFlg_SL() {
		return outputValue(leasePkgCratFlg_SL);
	}

	/**
	 * set  LEASE_PKG_CRAT_FLG_SL.
	 * @param data LEASE_PKG_CRAT_FLG_SL
	 */
	public void setLeasePkgCratFlg_SL(String data) {
		inputValue(leasePkgCratFlg_SL,data);
	}

	/**
	 * get  ATTRB_VAL_NUM.
	 * @return ATTRB_VAL_NUM
	 */
	public String getAttrbValNum() {
		return outputValue(attrbValNum);
	}

	/**
	 * set  ATTRB_VAL_NUM.
	 * @param data ATTRB_VAL_NUM
	 */
	public void setAttrbValNum(String data) {
		inputValue(attrbValNum,data);
	}

	/**
	 * get  CFS_PO_NUM_A1.
	 * @param idx1 List Number
	 * @return CFS_PO_NUM_A1
	 */
	public String getCfsPoNum_A1(int idx1) {
		return outputValue(A, idx1, cfsPoNum_A1);
	}

	/**
	 * set  CFS_PO_NUM_A1.
	 * @param data CFS_PO_NUM_A1Array
	 */
	public void setCfsPoNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cfsPoNum_A1, data[j]);
		}
	}

	/**
	 * get  CPO_ORD_NUM_A1.
	 * @param idx1 List Number
	 * @return CPO_ORD_NUM_A1
	 */
	public String getCpoOrdNum_A1(int idx1) {
		return outputValue(A, idx1, cpoOrdNum_A1);
	}

	/**
	 * set  CPO_ORD_NUM_A1.
	 * @param data CPO_ORD_NUM_A1Array
	 */
	public void setCpoOrdNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cpoOrdNum_A1, data[j]);
		}
	}

	/**
	 * get  DS_ACCT_NUM_A1.
	 * @param idx1 List Number
	 * @return DS_ACCT_NUM_A1
	 */
	public String getDsAcctNum_A1(int idx1) {
		return outputValue(A, idx1, dsAcctNum_A1);
	}

	/**
	 * set  DS_ACCT_NUM_A1.
	 * @param data DS_ACCT_NUM_A1Array
	 */
	public void setDsAcctNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsAcctNum_A1, data[j]);
		}
	}

	/**
	 * get  DS_ACCT_NM_A1.
	 * @param idx1 List Number
	 * @return DS_ACCT_NM_A1
	 */
	public String getDsAcctNm_A1(int idx1) {
		return outputValue(A, idx1, dsAcctNm_A1);
	}

	/**
	 * set  DS_ACCT_NM_A1.
	 * @param data DS_ACCT_NM_A1Array
	 */
	public void setDsAcctNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsAcctNm_A1, data[j]);
		}
	}

	/**
	 * get  CFS_PO_AMT_A1.
	 * @param idx1 List Number
	 * @return CFS_PO_AMT_A1
	 */
	public String getCfsPoAmt_A1(int idx1) {
		return outputValue(A, idx1, cfsPoAmt_A1);
	}

	/**
	 * set  CFS_PO_AMT_A1.
	 * @param data CFS_PO_AMT_A1Array
	 */
	public void setCfsPoAmt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cfsPoAmt_A1, data[j]);
		}
	}

	/**
	 * get  INV_CPLT_AMT_RATE_A1.
	 * @param idx1 List Number
	 * @return INV_CPLT_AMT_RATE_A1
	 */
	public String getInvCpltAmtRate_A1(int idx1) {
		return outputValue(A, idx1, invCpltAmtRate_A1);
	}

	/**
	 * set  INV_CPLT_AMT_RATE_A1.
	 * @param data INV_CPLT_AMT_RATE_A1Array
	 */
	public void setInvCpltAmtRate_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, invCpltAmtRate_A1, data[j]);
		}
	}

	/**
	 * get  CR_REBIL_HLD_FLG_A1.
	 * @param idx1 List Number
	 * @return CR_REBIL_HLD_FLG_A1
	 */
	public String getCrRebilHldFlg_A1(int idx1) {
		return outputValue(A, idx1, crRebilHldFlg_A1);
	}

	/**
	 * set  CR_REBIL_HLD_FLG_A1.
	 * @param data CR_REBIL_HLD_FLG_A1Array
	 */
	public void setCrRebilHldFlg_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, crRebilHldFlg_A1, data[j]);
		}
	}

	/**
	 * get  CFS_LEASE_PKG_HLD_FLG_A1.
	 * @param idx1 List Number
	 * @return CFS_LEASE_PKG_HLD_FLG_A1
	 */
	public String getCfsLeasePkgHldFlg_A1(int idx1) {
		return outputValue(A, idx1, cfsLeasePkgHldFlg_A1);
	}

	/**
	 * set  CFS_LEASE_PKG_HLD_FLG_A1.
	 * @param data CFS_LEASE_PKG_HLD_FLG_A1Array
	 */
	public void setCfsLeasePkgHldFlg_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cfsLeasePkgHldFlg_A1, data[j]);
		}
	}

	/**
	 * get  LEASE_PKG_CRAT_FLG_A1.
	 * @param idx1 List Number
	 * @return LEASE_PKG_CRAT_FLG_A1
	 */
	public String getLeasePkgCratFlg_A1(int idx1) {
		return outputValue(A, idx1, leasePkgCratFlg_A1);
	}

	/**
	 * set  LEASE_PKG_CRAT_FLG_A1.
	 * @param data LEASE_PKG_CRAT_FLG_A1Array
	 */
	public void setLeasePkgCratFlg_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, leasePkgCratFlg_A1, data[j]);
		}
	}

	/**
	 * get  PO_INFO_PROC_FLG_A1.
	 * @param idx1 List Number
	 * @return PO_INFO_PROC_FLG_A1
	 */
	public String getPoInfoProcFlg_A1(int idx1) {
		return outputValue(A, idx1, poInfoProcFlg_A1);
	}

	/**
	 * set  PO_INFO_PROC_FLG_A1.
	 * @param data PO_INFO_PROC_FLG_A1Array
	 */
	public void setPoInfoProcFlg_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, poInfoProcFlg_A1, data[j]);
		}
	}

	/**
	 * get  CFS_MAN_HLD_ACT_TP_CD_A1.
	 * @param idx1 List Number
	 * @return CFS_MAN_HLD_ACT_TP_CD_A1
	 */
	public String getCfsManHldActTpCd_A1(int idx1) {
		return outputValue(A, idx1, cfsManHldActTpCd_A1);
	}

	/**
	 * set  CFS_MAN_HLD_ACT_TP_CD_A1.
	 * @param data CFS_MAN_HLD_ACT_TP_CD_A1Array
	 */
	public void setCfsManHldActTpCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cfsManHldActTpCd_A1, data[j]);
		}
	}

	/**
	 * get  CFS_LEASE_PKG_PO_HDR_PK_A1.
	 * @param idx1 List Number
	 * @return CFS_LEASE_PKG_PO_HDR_PK_A1
	 */
	public String getCfsLeasePkgPoHdrPk_A1(int idx1) {
		return outputValue(A, idx1, cfsLeasePkgPoHdrPk_A1);
	}

	/**
	 * set  CFS_LEASE_PKG_PO_HDR_PK_A1.
	 * @param data CFS_LEASE_PKG_PO_HDR_PK_A1Array
	 */
	public void setCfsLeasePkgPoHdrPk_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cfsLeasePkgPoHdrPk_A1, data[j]);
		}
	}

	/**
	 * get  _EZUpdateDateTime_A1.
	 * @param idx1 List Number
	 * @return _EZUpdateDateTime_A1
	 */
	public String getEzUpTime_A1(int idx1) {
		return outputValue(A, idx1, ezUpTime_A1);
	}

	/**
	 * set  _EZUpdateDateTime_A1.
	 * @param data _EZUpdateDateTime_A1Array
	 */
	public void setEzUpTime_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTime_A1, data[j]);
		}
	}

	/**
	 * get  _EZUpTimeZone_A1.
	 * @param idx1 List Number
	 * @return _EZUpTimeZone_A1
	 */
	public String getEzUpTimeZone_A1(int idx1) {
		return outputValue(A, idx1, ezUpTimeZone_A1);
	}

	/**
	 * set  _EZUpTimeZone_A1.
	 * @param data _EZUpTimeZone_A1Array
	 */
	public void setEzUpTimeZone_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTimeZone_A1, data[j]);
		}
	}

	/**
	 * get  XX_PAGE_SHOW_FROM_NUM.
	 * @return XX_PAGE_SHOW_FROM_NUM
	 */
	public String getXxPageShowFromNum() {
		return outputValue(xxPageShowFromNum);
	}

	/**
	 * set  XX_PAGE_SHOW_FROM_NUM.
	 * @param data XX_PAGE_SHOW_FROM_NUM
	 */
	public void setXxPageShowFromNum(String data) {
		inputValue(xxPageShowFromNum,data);
	}

	/**
	 * get  XX_PAGE_SHOW_TO_NUM.
	 * @return XX_PAGE_SHOW_TO_NUM
	 */
	public String getXxPageShowToNum() {
		return outputValue(xxPageShowToNum);
	}

	/**
	 * set  XX_PAGE_SHOW_TO_NUM.
	 * @param data XX_PAGE_SHOW_TO_NUM
	 */
	public void setXxPageShowToNum(String data) {
		inputValue(xxPageShowToNum,data);
	}

	/**
	 * get  XX_PAGE_SHOW_OF_NUM.
	 * @return XX_PAGE_SHOW_OF_NUM
	 */
	public String getXxPageShowOfNum() {
		return outputValue(xxPageShowOfNum);
	}

	/**
	 * set  XX_PAGE_SHOW_OF_NUM.
	 * @param data XX_PAGE_SHOW_OF_NUM
	 */
	public void setXxPageShowOfNum(String data) {
		inputValue(xxPageShowOfNum,data);
	}

	/**
	 * get  XX_PAGE_SHOW_CUR_NUM.
	 * @return XX_PAGE_SHOW_CUR_NUM
	 */
	public String getXxPageShowCurNum() {
		return outputValue(xxPageShowCurNum);
	}

	/**
	 * set  XX_PAGE_SHOW_CUR_NUM.
	 * @param data XX_PAGE_SHOW_CUR_NUM
	 */
	public void setXxPageShowCurNum(String data) {
		inputValue(xxPageShowCurNum,data);
	}

	/**
	 * get  XX_PAGE_SHOW_TOT_NUM.
	 * @return XX_PAGE_SHOW_TOT_NUM
	 */
	public String getXxPageShowTotNum() {
		return outputValue(xxPageShowTotNum);
	}

	/**
	 * set  XX_PAGE_SHOW_TOT_NUM.
	 * @param data XX_PAGE_SHOW_TOT_NUM
	 */
	public void setXxPageShowTotNum(String data) {
		inputValue(xxPageShowTotNum,data);
	}

	/**
	 * get  XX_SORT_TBL_NM.
	 * @return XX_SORT_TBL_NM
	 */
	public String getXxSortTblNm() {
		return outputValue(xxSortTblNm);
	}

	/**
	 * set  XX_SORT_TBL_NM.
	 * @param data XX_SORT_TBL_NM
	 */
	public void setXxSortTblNm(String data) {
		inputValue(xxSortTblNm,data);
	}

	/**
	 * get  XX_SORT_ITEM_NM.
	 * @return XX_SORT_ITEM_NM
	 */
	public String getXxSortItemNm() {
		return outputValue(xxSortItemNm);
	}

	/**
	 * set  XX_SORT_ITEM_NM.
	 * @param data XX_SORT_ITEM_NM
	 */
	public void setXxSortItemNm(String data) {
		inputValue(xxSortItemNm,data);
	}

	/**
	 * get  XX_SORT_ORD_BY_TXT.
	 * @return XX_SORT_ORD_BY_TXT
	 */
	public String getXxSortOrdByTxt() {
		return outputValue(xxSortOrdByTxt);
	}

	/**
	 * set  XX_SORT_ORD_BY_TXT.
	 * @param data XX_SORT_ORD_BY_TXT
	 */
	public void setXxSortOrdByTxt(String data) {
		inputValue(xxSortOrdByTxt,data);
	}

}

