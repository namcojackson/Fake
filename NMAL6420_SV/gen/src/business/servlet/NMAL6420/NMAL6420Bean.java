// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20100706163441000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6420Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL6420;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL6420 is data bean class.
 */
public class NMAL6420Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** LOC_GRP_CD_PL*/
	public static final String locGrpCd_PL = "locGrpCd_PL";

	/** LOC_GRP_NM_PL*/
	public static final String locGrpNm_PL = "locGrpNm_PL";

	/** A*/
	public static final String A = "A";

	/** GLBL_CMPY_CD_A1*/
	public static final String glblCmpyCd_A1 = "glblCmpyCd_A1";

	/** XX_CHK_BOX_A1*/
	public static final String xxChkBox_A1 = "xxChkBox_A1";

	/** LOC_ROLE_TP_CD_A1*/
	public static final String locRoleTpCd_A1 = "locRoleTpCd_A1";

	/** LOC_ROLE_TP_NM_A1*/
	public static final String locRoleTpNm_A1 = "locRoleTpNm_A1";

	/** LOC_ROLE_TP_SORT_NUM_A1*/
	public static final String locRoleTpSortNum_A1 = "locRoleTpSortNum_A1";

	/** LOC_ROLE_TP_DESC_TXT_A1*/
	public static final String locRoleTpDescTxt_A1 = "locRoleTpDescTxt_A1";

	/** ACCT_CD_FLG_A1*/
	public static final String acctCdFlg_A1 = "acctCdFlg_A1";

	/** LOC_GRP_CD_A1*/
	public static final String locGrpCd_A1 = "locGrpCd_A1";

	/** _EZUpdateDateTime_A1*/
	public static final String ezUpTime_A1 = "ezUpTime_A1";

	/** _EZUpTimeZone_A1*/
	public static final String ezUpTimeZone_A1 = "ezUpTimeZone_A1";

	/**
	 * Method name:NMAL6420 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL6420Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL6420Bean() {
		super("business.servlet.NMAL6420.NMAL6420BMsg");
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

	/**
	 * get  GLBL_CMPY_CD.
	 * @return GLBL_CMPY_CD
	 */
	public String getGlblCmpyCd() {
		return outputValue(glblCmpyCd);
	}

	/**
	 * set  GLBL_CMPY_CD.
	 * @param data GLBL_CMPY_CD
	 */
	public void setGlblCmpyCd(String data) {
		inputValue(glblCmpyCd,data);
	}

	/**
	 * get  LOC_GRP_CD_PL.
	 * @param idx1 Index Number
	 * @return LOC_GRP_CD_PL
	 */
	public String getLocGrpCd_PL(int idx1) {
	 	 return outputValue(locGrpCd_PL, idx1);
	}

	/**
	 * get  LOC_GRP_NM_PL.
	 * @param idx1 Index Number
	 * @return LOC_GRP_NM_PL
	 */
	public String getLocGrpNm_PL(int idx1) {
	 	 return outputValue(locGrpNm_PL, idx1);
	}

	/**
	 * get  GLBL_CMPY_CD_A1.
	 * @param idx1 List Number
	 * @return GLBL_CMPY_CD_A1
	 */
	public String getGlblCmpyCd_A1(int idx1) {
		return outputValue(A, idx1, glblCmpyCd_A1);
	}

	/**
	 * set  GLBL_CMPY_CD_A1.
	 * @param data GLBL_CMPY_CD_A1Array
	 */
	public void setGlblCmpyCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, glblCmpyCd_A1, data[j]);
		}
	}

	/**
	 * get  XX_CHK_BOX_A1.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX_A1
	 */
	public String getXxChkBox_A1(int idx1) {
		return outputValue(A, idx1, xxChkBox_A1);
	}

	/**
	 * set  XX_CHK_BOX_A1.
	 * @param data XX_CHK_BOX_A1Array
	 */
	public void setXxChkBox_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox_A1, data[j]);
		}
	}

	/**
	 * get  LOC_ROLE_TP_CD_A1.
	 * @param idx1 List Number
	 * @return LOC_ROLE_TP_CD_A1
	 */
	public String getLocRoleTpCd_A1(int idx1) {
		return outputValue(A, idx1, locRoleTpCd_A1);
	}

	/**
	 * set  LOC_ROLE_TP_CD_A1.
	 * @param data LOC_ROLE_TP_CD_A1Array
	 */
	public void setLocRoleTpCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, locRoleTpCd_A1, data[j]);
		}
	}

	/**
	 * get  LOC_ROLE_TP_NM_A1.
	 * @param idx1 List Number
	 * @return LOC_ROLE_TP_NM_A1
	 */
	public String getLocRoleTpNm_A1(int idx1) {
		return outputValue(A, idx1, locRoleTpNm_A1);
	}

	/**
	 * set  LOC_ROLE_TP_NM_A1.
	 * @param data LOC_ROLE_TP_NM_A1Array
	 */
	public void setLocRoleTpNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, locRoleTpNm_A1, data[j]);
		}
	}

	/**
	 * get  LOC_ROLE_TP_SORT_NUM_A1.
	 * @param idx1 List Number
	 * @return LOC_ROLE_TP_SORT_NUM_A1
	 */
	public String getLocRoleTpSortNum_A1(int idx1) {
		return outputValue(A, idx1, locRoleTpSortNum_A1);
	}

	/**
	 * set  LOC_ROLE_TP_SORT_NUM_A1.
	 * @param data LOC_ROLE_TP_SORT_NUM_A1Array
	 */
	public void setLocRoleTpSortNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, locRoleTpSortNum_A1, data[j]);
		}
	}

	/**
	 * get  LOC_ROLE_TP_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return LOC_ROLE_TP_DESC_TXT_A1
	 */
	public String getLocRoleTpDescTxt_A1(int idx1) {
		return outputValue(A, idx1, locRoleTpDescTxt_A1);
	}

	/**
	 * set  LOC_ROLE_TP_DESC_TXT_A1.
	 * @param data LOC_ROLE_TP_DESC_TXT_A1Array
	 */
	public void setLocRoleTpDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, locRoleTpDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  ACCT_CD_FLG_A1.
	 * @param idx1 List Number
	 * @return ACCT_CD_FLG_A1
	 */
	public String getAcctCdFlg_A1(int idx1) {
		return outputValue(A, idx1, acctCdFlg_A1);
	}

	/**
	 * set  ACCT_CD_FLG_A1.
	 * @param data ACCT_CD_FLG_A1Array
	 */
	public void setAcctCdFlg_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, acctCdFlg_A1, data[j]);
		}
	}

	/**
	 * get  LOC_GRP_CD_A1.
	 * @param idx1 List Number
	 * @return LOC_GRP_CD_A1
	 */
	public String getLocGrpCd_A1(int idx1) {
		return outputValue(A, idx1, locGrpCd_A1);
	}

	/**
	 * set  LOC_GRP_CD_A1.
	 * @param data LOC_GRP_CD_A1Array
	 */
	public void setLocGrpCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, locGrpCd_A1, data[j]);
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

}
