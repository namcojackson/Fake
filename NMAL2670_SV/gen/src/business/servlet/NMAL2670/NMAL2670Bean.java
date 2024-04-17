// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160309143126000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2670Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL2670;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL2670 is data bean class.
 */
public class NMAL2670Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** ORG_CD_H1*/
	public static final String orgCd_H1 = "orgCd_H1";

	/** ORG_NM_H1*/
	public static final String orgNm_H1 = "orgNm_H1";

	/** XX_QUERY_FLTR_TXT_H1*/
	public static final String xxQueryFltrTxt_H1 = "xxQueryFltrTxt_H1";

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

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** A*/
	public static final String A = "A";

	/** TRTY_RULE_PK_A1*/
	public static final String trtyRulePk_A1 = "trtyRulePk_A1";

	/** XX_DS_MSG_ENTRY_TXT_A1*/
	public static final String xxDsMsgEntryTxt_A1 = "xxDsMsgEntryTxt_A1";

	/**
	 * Method name:NMAL2670 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL2670Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL2670Bean() {
		super("business.servlet.NMAL2670.NMAL2670BMsg");
	}

	/**
	 * get  ORG_CD_H1.
	 * @return ORG_CD_H1
	 */
	public String getOrgCd_H1() {
		return outputValue(orgCd_H1);
	}

	/**
	 * set  ORG_CD_H1.
	 * @param data ORG_CD_H1
	 */
	public void setOrgCd_H1(String data) {
		inputValue(orgCd_H1,data);
	}

	/**
	 * get  ORG_NM_H1.
	 * @return ORG_NM_H1
	 */
	public String getOrgNm_H1() {
		return outputValue(orgNm_H1);
	}

	/**
	 * set  ORG_NM_H1.
	 * @param data ORG_NM_H1
	 */
	public void setOrgNm_H1(String data) {
		inputValue(orgNm_H1,data);
	}

	/**
	 * get  XX_QUERY_FLTR_TXT_H1.
	 * @return XX_QUERY_FLTR_TXT_H1
	 */
	public String getXxQueryFltrTxt_H1() {
		return outputValue(xxQueryFltrTxt_H1);
	}

	/**
	 * set  XX_QUERY_FLTR_TXT_H1.
	 * @param data XX_QUERY_FLTR_TXT_H1
	 */
	public void setXxQueryFltrTxt_H1(String data) {
		inputValue(xxQueryFltrTxt_H1,data);
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
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
	}

	/**
	 * get  TRTY_RULE_PK_A1.
	 * @param idx1 List Number
	 * @return TRTY_RULE_PK_A1
	 */
	public String getTrtyRulePk_A1(int idx1) {
		return outputValue(A, idx1, trtyRulePk_A1);
	}

	/**
	 * set  TRTY_RULE_PK_A1.
	 * @param data TRTY_RULE_PK_A1Array
	 */
	public void setTrtyRulePk_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, trtyRulePk_A1, data[j]);
		}
	}

	/**
	 * get  XX_DS_MSG_ENTRY_TXT_A1.
	 * @param idx1 List Number
	 * @return XX_DS_MSG_ENTRY_TXT_A1
	 */
	public String getXxDsMsgEntryTxt_A1(int idx1) {
		return outputValue(A, idx1, xxDsMsgEntryTxt_A1);
	}

	/**
	 * set  XX_DS_MSG_ENTRY_TXT_A1.
	 * @param data XX_DS_MSG_ENTRY_TXT_A1Array
	 */
	public void setXxDsMsgEntryTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxDsMsgEntryTxt_A1, data[j]);
		}
	}

}

