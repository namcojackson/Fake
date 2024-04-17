// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160906152738000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0060Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NWWL0060;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NWWL0060 is data bean class.
 */
public class NWWL0060Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** PSN_CD*/
	public static final String psnCd = "psnCd";

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

	/** XX_COMN_COL_ORD_TXT*/
	public static final String xxComnColOrdTxt = "xxComnColOrdTxt";

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/** A*/
	public static final String A = "A";

	/** NTFY_SBSCR_PK_A*/
	public static final String ntfySbscrPk_A = "ntfySbscrPk_A";

	/** NTFY_SBSCR_FLG_A*/
	public static final String ntfySbscrFlg_A = "ntfySbscrFlg_A";

	/** NTFY_HDR_PK_A*/
	public static final String ntfyHdrPk_A = "ntfyHdrPk_A";

	/** NTFY_HDR_ID_A*/
	public static final String ntfyHdrId_A = "ntfyHdrId_A";

	/** NTFY_HDR_NM_A*/
	public static final String ntfyHdrNm_A = "ntfyHdrNm_A";

	/** NTFY_HDR_DESC_TXT_A*/
	public static final String ntfyHdrDescTxt_A = "ntfyHdrDescTxt_A";

	/** NTFY_BIZ_AREA_TP_DESC_TXT_A*/
	public static final String ntfyBizAreaTpDescTxt_A = "ntfyBizAreaTpDescTxt_A";

	/** NTFY_SUB_AREA_TP_DESC_TXT_A*/
	public static final String ntfySubAreaTpDescTxt_A = "ntfySubAreaTpDescTxt_A";

	/** NTFY_DIST_LIST_NM_LIST_TXT_A*/
	public static final String ntfyDistListNmListTxt_A = "ntfyDistListNmListTxt_A";

	/** _EZUpdateDateTime_A*/
	public static final String ezUpTime_A = "ezUpTime_A";

	/** _EZUpTimeZone_A*/
	public static final String ezUpTimeZone_A = "ezUpTimeZone_A";

	/**
	 * Method name:NWWL0060 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NWWL0060Bean is generated
	 * <dd>Remarks:
	 */
	public NWWL0060Bean() {
		super("business.servlet.NWWL0060.NWWL0060BMsg");
	}

	/**
	 * get  PSN_CD.
	 * @return PSN_CD
	 */
	public String getPsnCd() {
		return outputValue(psnCd);
	}

	/**
	 * set  PSN_CD.
	 * @param data PSN_CD
	 */
	public void setPsnCd(String data) {
		inputValue(psnCd,data);
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
	 * get  XX_COMN_COL_ORD_TXT.
	 * @return XX_COMN_COL_ORD_TXT
	 */
	public String getXxComnColOrdTxt() {
		return outputValue(xxComnColOrdTxt);
	}

	/**
	 * set  XX_COMN_COL_ORD_TXT.
	 * @param data XX_COMN_COL_ORD_TXT
	 */
	public void setXxComnColOrdTxt(String data) {
		inputValue(xxComnColOrdTxt,data);
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
	 * get  NTFY_SBSCR_PK_A.
	 * @param idx1 List Number
	 * @return NTFY_SBSCR_PK_A
	 */
	public String getNtfySbscrPk_A(int idx1) {
		return outputValue(A, idx1, ntfySbscrPk_A);
	}

	/**
	 * set  NTFY_SBSCR_PK_A.
	 * @param data NTFY_SBSCR_PK_AArray
	 */
	public void setNtfySbscrPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ntfySbscrPk_A, data[j]);
		}
	}

	/**
	 * get  NTFY_SBSCR_FLG_A.
	 * @param idx1 List Number
	 * @return NTFY_SBSCR_FLG_A
	 */
	public String getNtfySbscrFlg_A(int idx1) {
		return outputValue(A, idx1, ntfySbscrFlg_A);
	}

	/**
	 * set  NTFY_SBSCR_FLG_A.
	 * @param data NTFY_SBSCR_FLG_AArray
	 */
	public void setNtfySbscrFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ntfySbscrFlg_A, data[j]);
		}
	}

	/**
	 * get  NTFY_HDR_PK_A.
	 * @param idx1 List Number
	 * @return NTFY_HDR_PK_A
	 */
	public String getNtfyHdrPk_A(int idx1) {
		return outputValue(A, idx1, ntfyHdrPk_A);
	}

	/**
	 * set  NTFY_HDR_PK_A.
	 * @param data NTFY_HDR_PK_AArray
	 */
	public void setNtfyHdrPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ntfyHdrPk_A, data[j]);
		}
	}

	/**
	 * get  NTFY_HDR_ID_A.
	 * @param idx1 List Number
	 * @return NTFY_HDR_ID_A
	 */
	public String getNtfyHdrId_A(int idx1) {
		return outputValue(A, idx1, ntfyHdrId_A);
	}

	/**
	 * set  NTFY_HDR_ID_A.
	 * @param data NTFY_HDR_ID_AArray
	 */
	public void setNtfyHdrId_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ntfyHdrId_A, data[j]);
		}
	}

	/**
	 * get  NTFY_HDR_NM_A.
	 * @param idx1 List Number
	 * @return NTFY_HDR_NM_A
	 */
	public String getNtfyHdrNm_A(int idx1) {
		return outputValue(A, idx1, ntfyHdrNm_A);
	}

	/**
	 * set  NTFY_HDR_NM_A.
	 * @param data NTFY_HDR_NM_AArray
	 */
	public void setNtfyHdrNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ntfyHdrNm_A, data[j]);
		}
	}

	/**
	 * get  NTFY_HDR_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return NTFY_HDR_DESC_TXT_A
	 */
	public String getNtfyHdrDescTxt_A(int idx1) {
		return outputValue(A, idx1, ntfyHdrDescTxt_A);
	}

	/**
	 * set  NTFY_HDR_DESC_TXT_A.
	 * @param data NTFY_HDR_DESC_TXT_AArray
	 */
	public void setNtfyHdrDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ntfyHdrDescTxt_A, data[j]);
		}
	}

	/**
	 * get  NTFY_BIZ_AREA_TP_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return NTFY_BIZ_AREA_TP_DESC_TXT_A
	 */
	public String getNtfyBizAreaTpDescTxt_A(int idx1) {
		return outputValue(A, idx1, ntfyBizAreaTpDescTxt_A);
	}

	/**
	 * set  NTFY_BIZ_AREA_TP_DESC_TXT_A.
	 * @param data NTFY_BIZ_AREA_TP_DESC_TXT_AArray
	 */
	public void setNtfyBizAreaTpDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ntfyBizAreaTpDescTxt_A, data[j]);
		}
	}

	/**
	 * get  NTFY_SUB_AREA_TP_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return NTFY_SUB_AREA_TP_DESC_TXT_A
	 */
	public String getNtfySubAreaTpDescTxt_A(int idx1) {
		return outputValue(A, idx1, ntfySubAreaTpDescTxt_A);
	}

	/**
	 * set  NTFY_SUB_AREA_TP_DESC_TXT_A.
	 * @param data NTFY_SUB_AREA_TP_DESC_TXT_AArray
	 */
	public void setNtfySubAreaTpDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ntfySubAreaTpDescTxt_A, data[j]);
		}
	}

	/**
	 * get  NTFY_DIST_LIST_NM_LIST_TXT_A.
	 * @param idx1 List Number
	 * @return NTFY_DIST_LIST_NM_LIST_TXT_A
	 */
	public String getNtfyDistListNmListTxt_A(int idx1) {
		return outputValue(A, idx1, ntfyDistListNmListTxt_A);
	}

	/**
	 * set  NTFY_DIST_LIST_NM_LIST_TXT_A.
	 * @param data NTFY_DIST_LIST_NM_LIST_TXT_AArray
	 */
	public void setNtfyDistListNmListTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ntfyDistListNmListTxt_A, data[j]);
		}
	}

	/**
	 * get  _EZUpdateDateTime_A.
	 * @param idx1 List Number
	 * @return _EZUpdateDateTime_A
	 */
	public String getEzUpTime_A(int idx1) {
		return outputValue(A, idx1, ezUpTime_A);
	}

	/**
	 * set  _EZUpdateDateTime_A.
	 * @param data _EZUpdateDateTime_AArray
	 */
	public void setEzUpTime_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTime_A, data[j]);
		}
	}

	/**
	 * get  _EZUpTimeZone_A.
	 * @param idx1 List Number
	 * @return _EZUpTimeZone_A
	 */
	public String getEzUpTimeZone_A(int idx1) {
		return outputValue(A, idx1, ezUpTimeZone_A);
	}

	/**
	 * set  _EZUpTimeZone_A.
	 * @param data _EZUpTimeZone_AArray
	 */
	public void setEzUpTimeZone_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTimeZone_A, data[j]);
		}
	}

}
