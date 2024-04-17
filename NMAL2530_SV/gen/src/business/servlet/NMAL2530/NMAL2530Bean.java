// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20170223093719000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2530Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL2530;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL2530 is data bean class.
 */
public class NMAL2530Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** ORG_STRU_TP_CD_H1*/
	public static final String orgStruTpCd_H1 = "orgStruTpCd_H1";

	/** ORG_STRU_TP_CD_H2*/
	public static final String orgStruTpCd_H2 = "orgStruTpCd_H2";

	/** ORG_STRU_TP_NM_H1*/
	public static final String orgStruTpNm_H1 = "orgStruTpNm_H1";

	/** ORG_NM_H1*/
	public static final String orgNm_H1 = "orgNm_H1";

	/** ORG_NM_H2*/
	public static final String orgNm_H2 = "orgNm_H2";

	/** TOC_NM_H1*/
	public static final String tocNm_H1 = "tocNm_H1";

	/** PSN_CD_H1*/
	public static final String psnCd_H1 = "psnCd_H1";

	/** ORG_TIER_CD_H1*/
	public static final String orgTierCd_H1 = "orgTierCd_H1";

	/** ORG_TIER_CD_H2*/
	public static final String orgTierCd_H2 = "orgTierCd_H2";

	/** ORG_TIER_NM_H1*/
	public static final String orgTierNm_H1 = "orgTierNm_H1";

	/** CSR_RG_TP_CD_H1*/
	public static final String csrRgTpCd_H1 = "csrRgTpCd_H1";

	/** CSR_RG_TP_CD_H2*/
	public static final String csrRgTpCd_H2 = "csrRgTpCd_H2";

	/** CSR_RG_TP_DESC_TXT_H1*/
	public static final String csrRgTpDescTxt_H1 = "csrRgTpDescTxt_H1";

	/** EFF_FROM_DT_FR*/
	public static final String effFromDt_FR = "effFromDt_FR";

	/** EFF_FROM_DT_TO*/
	public static final String effFromDt_TO = "effFromDt_TO";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** A*/
	public static final String A = "A";

	/** ORG_CD_A1*/
	public static final String orgCd_A1 = "orgCd_A1";

	/** ORG_NM_A2*/
	public static final String orgNm_A2 = "orgNm_A2";

	/** ORG_NM_A1*/
	public static final String orgNm_A1 = "orgNm_A1";

	/** ORG_TIER_NM_A1*/
	public static final String orgTierNm_A1 = "orgTierNm_A1";

	/** EFF_FROM_DT_A1*/
	public static final String effFromDt_A1 = "effFromDt_A1";

	/** EFF_THRU_DT_A1*/
	public static final String effThruDt_A1 = "effThruDt_A1";

	/**
	 * Method name:NMAL2530 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL2530Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL2530Bean() {
		super("business.servlet.NMAL2530.NMAL2530BMsg");
	}

	/**
	 * get  ORG_STRU_TP_CD_H1.
	 * @return ORG_STRU_TP_CD_H1
	 */
	public String getOrgStruTpCd_H1() {
		return outputValue(orgStruTpCd_H1);
	}

	/**
	 * set  ORG_STRU_TP_CD_H1.
	 * @param data ORG_STRU_TP_CD_H1
	 */
	public void setOrgStruTpCd_H1(String data) {
		inputValue(orgStruTpCd_H1,data);
	}

	/**
	 * get  ORG_STRU_TP_CD_H2.
	 * @param idx1 Index Number
	 * @return ORG_STRU_TP_CD_H2
	 */
	public String getOrgStruTpCd_H2(int idx1) {
	 	 return outputValue(orgStruTpCd_H2, idx1);
	}

	/**
	 * get  ORG_STRU_TP_NM_H1.
	 * @param idx1 Index Number
	 * @return ORG_STRU_TP_NM_H1
	 */
	public String getOrgStruTpNm_H1(int idx1) {
	 	 return outputValue(orgStruTpNm_H1, idx1);
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
	 * get  ORG_NM_H2.
	 * @return ORG_NM_H2
	 */
	public String getOrgNm_H2() {
		return outputValue(orgNm_H2);
	}

	/**
	 * set  ORG_NM_H2.
	 * @param data ORG_NM_H2
	 */
	public void setOrgNm_H2(String data) {
		inputValue(orgNm_H2,data);
	}

	/**
	 * get  TOC_NM_H1.
	 * @return TOC_NM_H1
	 */
	public String getTocNm_H1() {
		return outputValue(tocNm_H1);
	}

	/**
	 * set  TOC_NM_H1.
	 * @param data TOC_NM_H1
	 */
	public void setTocNm_H1(String data) {
		inputValue(tocNm_H1,data);
	}

	/**
	 * get  PSN_CD_H1.
	 * @return PSN_CD_H1
	 */
	public String getPsnCd_H1() {
		return outputValue(psnCd_H1);
	}

	/**
	 * set  PSN_CD_H1.
	 * @param data PSN_CD_H1
	 */
	public void setPsnCd_H1(String data) {
		inputValue(psnCd_H1,data);
	}

	/**
	 * get  ORG_TIER_CD_H1.
	 * @return ORG_TIER_CD_H1
	 */
	public String getOrgTierCd_H1() {
		return outputValue(orgTierCd_H1);
	}

	/**
	 * set  ORG_TIER_CD_H1.
	 * @param data ORG_TIER_CD_H1
	 */
	public void setOrgTierCd_H1(String data) {
		inputValue(orgTierCd_H1,data);
	}

	/**
	 * get  ORG_TIER_CD_H2.
	 * @param idx1 Index Number
	 * @return ORG_TIER_CD_H2
	 */
	public String getOrgTierCd_H2(int idx1) {
	 	 return outputValue(orgTierCd_H2, idx1);
	}

	/**
	 * get  ORG_TIER_NM_H1.
	 * @param idx1 Index Number
	 * @return ORG_TIER_NM_H1
	 */
	public String getOrgTierNm_H1(int idx1) {
	 	 return outputValue(orgTierNm_H1, idx1);
	}

	/**
	 * get  CSR_RG_TP_CD_H1.
	 * @return CSR_RG_TP_CD_H1
	 */
	public String getCsrRgTpCd_H1() {
		return outputValue(csrRgTpCd_H1);
	}

	/**
	 * set  CSR_RG_TP_CD_H1.
	 * @param data CSR_RG_TP_CD_H1
	 */
	public void setCsrRgTpCd_H1(String data) {
		inputValue(csrRgTpCd_H1,data);
	}

	/**
	 * get  CSR_RG_TP_CD_H2.
	 * @param idx1 Index Number
	 * @return CSR_RG_TP_CD_H2
	 */
	public String getCsrRgTpCd_H2(int idx1) {
	 	 return outputValue(csrRgTpCd_H2, idx1);
	}

	/**
	 * get  CSR_RG_TP_DESC_TXT_H1.
	 * @param idx1 Index Number
	 * @return CSR_RG_TP_DESC_TXT_H1
	 */
	public String getCsrRgTpDescTxt_H1(int idx1) {
	 	 return outputValue(csrRgTpDescTxt_H1, idx1);
	}

	/**
	 * get  EFF_FROM_DT_FR.
	 * @return EFF_FROM_DT_FR
	 */
	public String getEffFromDt_FR() {
		return outputValue(effFromDt_FR, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_FR.
	 * @param data EFF_FROM_DT_FR
	 */
	public void setEffFromDt_FR(String data) {
		inputValue(effFromDt_FR, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  EFF_FROM_DT_TO.
	 * @return EFF_FROM_DT_TO
	 */
	public String getEffFromDt_TO() {
		return outputValue(effFromDt_TO, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_TO.
	 * @param data EFF_FROM_DT_TO
	 */
	public void setEffFromDt_TO(String data) {
		inputValue(effFromDt_TO, data, VALUE_YEAR_MONTH_DAY);
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
	 * get  ORG_CD_A1.
	 * @param idx1 List Number
	 * @return ORG_CD_A1
	 */
	public String getOrgCd_A1(int idx1) {
		return outputValue(A, idx1, orgCd_A1);
	}

	/**
	 * set  ORG_CD_A1.
	 * @param data ORG_CD_A1Array
	 */
	public void setOrgCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, orgCd_A1, data[j]);
		}
	}

	/**
	 * get  ORG_NM_A2.
	 * @param idx1 List Number
	 * @return ORG_NM_A2
	 */
	public String getOrgNm_A2(int idx1) {
		return outputValue(A, idx1, orgNm_A2);
	}

	/**
	 * set  ORG_NM_A2.
	 * @param data ORG_NM_A2Array
	 */
	public void setOrgNm_A2(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, orgNm_A2, data[j]);
		}
	}

	/**
	 * get  ORG_NM_A1.
	 * @param idx1 List Number
	 * @return ORG_NM_A1
	 */
	public String getOrgNm_A1(int idx1) {
		return outputValue(A, idx1, orgNm_A1);
	}

	/**
	 * set  ORG_NM_A1.
	 * @param data ORG_NM_A1Array
	 */
	public void setOrgNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, orgNm_A1, data[j]);
		}
	}

	/**
	 * get  ORG_TIER_NM_A1.
	 * @param idx1 List Number
	 * @return ORG_TIER_NM_A1
	 */
	public String getOrgTierNm_A1(int idx1) {
		return outputValue(A, idx1, orgTierNm_A1);
	}

	/**
	 * set  ORG_TIER_NM_A1.
	 * @param data ORG_TIER_NM_A1Array
	 */
	public void setOrgTierNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, orgTierNm_A1, data[j]);
		}
	}

	/**
	 * get  EFF_FROM_DT_A1.
	 * @param idx1 List Number
	 * @return EFF_FROM_DT_A1
	 */
	public String getEffFromDt_A1(int idx1) {
		return outputValue(A, idx1, effFromDt_A1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_A1.
	 * @param data EFF_FROM_DT_A1Array
	 */
	public void setEffFromDt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effFromDt_A1, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  EFF_THRU_DT_A1.
	 * @param idx1 List Number
	 * @return EFF_THRU_DT_A1
	 */
	public String getEffThruDt_A1(int idx1) {
		return outputValue(A, idx1, effThruDt_A1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_THRU_DT_A1.
	 * @param data EFF_THRU_DT_A1Array
	 */
	public void setEffThruDt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effThruDt_A1, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

}
