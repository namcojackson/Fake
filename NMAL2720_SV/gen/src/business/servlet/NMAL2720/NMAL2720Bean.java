// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160706111638000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2720Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL2720;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL2720 is data bean class.
 */
public class NMAL2720Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** BIZ_AREA_ORG_CD_H*/
	public static final String bizAreaOrgCd_H = "bizAreaOrgCd_H";

	/** BIZ_AREA_ORG_CD_P*/
	public static final String bizAreaOrgCd_P = "bizAreaOrgCd_P";

	/** BIZ_AREA_ORG_DESC_TXT_P*/
	public static final String bizAreaOrgDescTxt_P = "bizAreaOrgDescTxt_P";

	/** XX_PSN_NM_H*/
	public static final String xxPsnNm_H = "xxPsnNm_H";

	/** PSN_NUM_H*/
	public static final String psnNum_H = "psnNum_H";

	/** ORG_NM_H*/
	public static final String orgNm_H = "orgNm_H";

	/** PSN_CD_H*/
	public static final String psnCd_H = "psnCd_H";

	/** XX_CHK_BOX_AH*/
	public static final String xxChkBox_AH = "xxChkBox_AH";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX_A1*/
	public static final String xxChkBox_A1 = "xxChkBox_A1";

	/** ORG_CD_A1*/
	public static final String orgCd_A1 = "orgCd_A1";

	/** ORG_NM_A1*/
	public static final String orgNm_A1 = "orgNm_A1";

	/** ORG_DESC_TXT_A1*/
	public static final String orgDescTxt_A1 = "orgDescTxt_A1";

	/** BIZ_AREA_ORG_CD_A1*/
	public static final String bizAreaOrgCd_A1 = "bizAreaOrgCd_A1";

	/** BIZ_AREA_ORG_DESC_TXT_A1*/
	public static final String bizAreaOrgDescTxt_A1 = "bizAreaOrgDescTxt_A1";

	/** LINE_BIZ_TP_DESC_TXT_A1*/
	public static final String lineBizTpDescTxt_A1 = "lineBizTpDescTxt_A1";

	/** XX_PSN_NM_A1*/
	public static final String xxPsnNm_A1 = "xxPsnNm_A1";

	/** PSN_NUM_A1*/
	public static final String psnNum_A1 = "psnNum_A1";

	/** ORG_FUNC_ROLE_TP_DESC_TXT_A1*/
	public static final String orgFuncRoleTpDescTxt_A1 = "orgFuncRoleTpDescTxt_A1";

	/** EFF_FROM_DT_A1*/
	public static final String effFromDt_A1 = "effFromDt_A1";

	/** EFF_THRU_DT_A1*/
	public static final String effThruDt_A1 = "effThruDt_A1";

	/** TOC_CD_A1*/
	public static final String tocCd_A1 = "tocCd_A1";

	/** XX_PSN_NM_D1*/
	public static final String xxPsnNm_D1 = "xxPsnNm_D1";

	/** PSN_NUM_D1*/
	public static final String psnNum_D1 = "psnNum_D1";

	/** EFF_FROM_DT_D1*/
	public static final String effFromDt_D1 = "effFromDt_D1";

	/** EFF_THRU_DT_D1*/
	public static final String effThruDt_D1 = "effThruDt_D1";

	/** MASS_UPD_RSN_CMNT_TXT_D1*/
	public static final String massUpdRsnCmntTxt_D1 = "massUpdRsnCmntTxt_D1";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/** XX_SCR_EVENT_NM*/
	public static final String xxScrEventNm = "xxScrEventNm";

	/** P*/
	public static final String P = "P";

	/** XX_POP_PRM*/
	public static final String xxPopPrm = "xxPopPrm";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_SEL_FLG*/
	public static final String xxSelFlg = "xxSelFlg";

	/**
	 * Method name:NMAL2720 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL2720Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL2720Bean() {
		super("business.servlet.NMAL2720.NMAL2720BMsg");
	}

	/**
	 * get  BIZ_AREA_ORG_CD_H.
	 * @return BIZ_AREA_ORG_CD_H
	 */
	public String getBizAreaOrgCd_H() {
		return outputValue(bizAreaOrgCd_H);
	}

	/**
	 * set  BIZ_AREA_ORG_CD_H.
	 * @param data BIZ_AREA_ORG_CD_H
	 */
	public void setBizAreaOrgCd_H(String data) {
		inputValue(bizAreaOrgCd_H,data);
	}

	/**
	 * get  BIZ_AREA_ORG_CD_P.
	 * @param idx1 Index Number
	 * @return BIZ_AREA_ORG_CD_P
	 */
	public String getBizAreaOrgCd_P(int idx1) {
	 	 return outputValue(bizAreaOrgCd_P, idx1);
	}

	/**
	 * get  BIZ_AREA_ORG_DESC_TXT_P.
	 * @param idx1 Index Number
	 * @return BIZ_AREA_ORG_DESC_TXT_P
	 */
	public String getBizAreaOrgDescTxt_P(int idx1) {
	 	 return outputValue(bizAreaOrgDescTxt_P, idx1);
	}

	/**
	 * get  XX_PSN_NM_H.
	 * @return XX_PSN_NM_H
	 */
	public String getXxPsnNm_H() {
		return outputValue(xxPsnNm_H);
	}

	/**
	 * set  XX_PSN_NM_H.
	 * @param data XX_PSN_NM_H
	 */
	public void setXxPsnNm_H(String data) {
		inputValue(xxPsnNm_H,data);
	}

	/**
	 * get  PSN_NUM_H.
	 * @return PSN_NUM_H
	 */
	public String getPsnNum_H() {
		return outputValue(psnNum_H);
	}

	/**
	 * set  PSN_NUM_H.
	 * @param data PSN_NUM_H
	 */
	public void setPsnNum_H(String data) {
		inputValue(psnNum_H,data);
	}

	/**
	 * get  ORG_NM_H.
	 * @return ORG_NM_H
	 */
	public String getOrgNm_H() {
		return outputValue(orgNm_H);
	}

	/**
	 * set  ORG_NM_H.
	 * @param data ORG_NM_H
	 */
	public void setOrgNm_H(String data) {
		inputValue(orgNm_H,data);
	}

	/**
	 * get  PSN_CD_H.
	 * @return PSN_CD_H
	 */
	public String getPsnCd_H() {
		return outputValue(psnCd_H);
	}

	/**
	 * set  PSN_CD_H.
	 * @param data PSN_CD_H
	 */
	public void setPsnCd_H(String data) {
		inputValue(psnCd_H,data);
	}

	/**
	 * get  XX_CHK_BOX_AH.
	 * @return XX_CHK_BOX_AH
	 */
	public String getXxChkBox_AH() {
		return outputValue(xxChkBox_AH);
	}

	/**
	 * set  XX_CHK_BOX_AH.
	 * @param data XX_CHK_BOX_AH
	 */
	public void setXxChkBox_AH(String data) {
		inputValue(xxChkBox_AH,data);
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
	 * get  ORG_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return ORG_DESC_TXT_A1
	 */
	public String getOrgDescTxt_A1(int idx1) {
		return outputValue(A, idx1, orgDescTxt_A1);
	}

	/**
	 * set  ORG_DESC_TXT_A1.
	 * @param data ORG_DESC_TXT_A1Array
	 */
	public void setOrgDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, orgDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  BIZ_AREA_ORG_CD_A1.
	 * @param idx1 List Number
	 * @return BIZ_AREA_ORG_CD_A1
	 */
	public String getBizAreaOrgCd_A1(int idx1) {
		return outputValue(A, idx1, bizAreaOrgCd_A1);
	}

	/**
	 * set  BIZ_AREA_ORG_CD_A1.
	 * @param data BIZ_AREA_ORG_CD_A1Array
	 */
	public void setBizAreaOrgCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, bizAreaOrgCd_A1, data[j]);
		}
	}

	/**
	 * get  BIZ_AREA_ORG_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return BIZ_AREA_ORG_DESC_TXT_A1
	 */
	public String getBizAreaOrgDescTxt_A1(int idx1) {
		return outputValue(A, idx1, bizAreaOrgDescTxt_A1);
	}

	/**
	 * set  BIZ_AREA_ORG_DESC_TXT_A1.
	 * @param data BIZ_AREA_ORG_DESC_TXT_A1Array
	 */
	public void setBizAreaOrgDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, bizAreaOrgDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  LINE_BIZ_TP_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return LINE_BIZ_TP_DESC_TXT_A1
	 */
	public String getLineBizTpDescTxt_A1(int idx1) {
		return outputValue(A, idx1, lineBizTpDescTxt_A1);
	}

	/**
	 * set  LINE_BIZ_TP_DESC_TXT_A1.
	 * @param data LINE_BIZ_TP_DESC_TXT_A1Array
	 */
	public void setLineBizTpDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, lineBizTpDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  XX_PSN_NM_A1.
	 * @param idx1 List Number
	 * @return XX_PSN_NM_A1
	 */
	public String getXxPsnNm_A1(int idx1) {
		return outputValue(A, idx1, xxPsnNm_A1);
	}

	/**
	 * set  XX_PSN_NM_A1.
	 * @param data XX_PSN_NM_A1Array
	 */
	public void setXxPsnNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxPsnNm_A1, data[j]);
		}
	}

	/**
	 * get  PSN_NUM_A1.
	 * @param idx1 List Number
	 * @return PSN_NUM_A1
	 */
	public String getPsnNum_A1(int idx1) {
		return outputValue(A, idx1, psnNum_A1);
	}

	/**
	 * set  PSN_NUM_A1.
	 * @param data PSN_NUM_A1Array
	 */
	public void setPsnNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, psnNum_A1, data[j]);
		}
	}

	/**
	 * get  ORG_FUNC_ROLE_TP_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return ORG_FUNC_ROLE_TP_DESC_TXT_A1
	 */
	public String getOrgFuncRoleTpDescTxt_A1(int idx1) {
		return outputValue(A, idx1, orgFuncRoleTpDescTxt_A1);
	}

	/**
	 * set  ORG_FUNC_ROLE_TP_DESC_TXT_A1.
	 * @param data ORG_FUNC_ROLE_TP_DESC_TXT_A1Array
	 */
	public void setOrgFuncRoleTpDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, orgFuncRoleTpDescTxt_A1, data[j]);
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

	/**
	 * get  TOC_CD_A1.
	 * @param idx1 List Number
	 * @return TOC_CD_A1
	 */
	public String getTocCd_A1(int idx1) {
		return outputValue(A, idx1, tocCd_A1);
	}

	/**
	 * set  TOC_CD_A1.
	 * @param data TOC_CD_A1Array
	 */
	public void setTocCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, tocCd_A1, data[j]);
		}
	}

	/**
	 * get  XX_PSN_NM_D1.
	 * @return XX_PSN_NM_D1
	 */
	public String getXxPsnNm_D1() {
		return outputValue(xxPsnNm_D1);
	}

	/**
	 * set  XX_PSN_NM_D1.
	 * @param data XX_PSN_NM_D1
	 */
	public void setXxPsnNm_D1(String data) {
		inputValue(xxPsnNm_D1,data);
	}

	/**
	 * get  PSN_NUM_D1.
	 * @return PSN_NUM_D1
	 */
	public String getPsnNum_D1() {
		return outputValue(psnNum_D1);
	}

	/**
	 * set  PSN_NUM_D1.
	 * @param data PSN_NUM_D1
	 */
	public void setPsnNum_D1(String data) {
		inputValue(psnNum_D1,data);
	}

	/**
	 * get  EFF_FROM_DT_D1.
	 * @return EFF_FROM_DT_D1
	 */
	public String getEffFromDt_D1() {
		return outputValue(effFromDt_D1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_D1.
	 * @param data EFF_FROM_DT_D1
	 */
	public void setEffFromDt_D1(String data) {
		inputValue(effFromDt_D1, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  EFF_THRU_DT_D1.
	 * @return EFF_THRU_DT_D1
	 */
	public String getEffThruDt_D1() {
		return outputValue(effThruDt_D1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_THRU_DT_D1.
	 * @param data EFF_THRU_DT_D1
	 */
	public void setEffThruDt_D1(String data) {
		inputValue(effThruDt_D1, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  MASS_UPD_RSN_CMNT_TXT_D1.
	 * @return MASS_UPD_RSN_CMNT_TXT_D1
	 */
	public String getMassUpdRsnCmntTxt_D1() {
		return outputValue(massUpdRsnCmntTxt_D1);
	}

	/**
	 * set  MASS_UPD_RSN_CMNT_TXT_D1.
	 * @param data MASS_UPD_RSN_CMNT_TXT_D1
	 */
	public void setMassUpdRsnCmntTxt_D1(String data) {
		inputValue(massUpdRsnCmntTxt_D1,data);
	}

	/**
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
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
	 * get  XX_SCR_EVENT_NM.
	 * @return XX_SCR_EVENT_NM
	 */
	public String getXxScrEventNm() {
		return outputValue(xxScrEventNm);
	}

	/**
	 * set  XX_SCR_EVENT_NM.
	 * @param data XX_SCR_EVENT_NM
	 */
	public void setXxScrEventNm(String data) {
		inputValue(xxScrEventNm,data);
	}

	/**
	 * get  XX_POP_PRM.
	 * @param idx1 List Number
	 * @return XX_POP_PRM
	 */
	public String getXxPopPrm(int idx1) {
		return outputValue(P, idx1, xxPopPrm);
	}

	/**
	 * set  XX_POP_PRM.
	 * @param data XX_POP_PRMArray
	 */
	public void setXxPopPrm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxPopPrm, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXTArray
	 */
	public void setXxComnScrColValTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_QUERY_COL_NM.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_QUERY_COL_NM
	 */
	public String getXxComnScrQueryColNm(int idx1) {
		return outputValue(P, idx1, xxComnScrQueryColNm);
	}

	/**
	 * set  XX_COMN_SCR_QUERY_COL_NM.
	 * @param data XX_COMN_SCR_QUERY_COL_NMArray
	 */
	public void setXxComnScrQueryColNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrQueryColNm, data[j]);
		}
	}

	/**
	 * get  XX_SEL_FLG.
	 * @param idx1 List Number
	 * @return XX_SEL_FLG
	 */
	public String getXxSelFlg(int idx1) {
		return outputValue(P, idx1, xxSelFlg);
	}

	/**
	 * set  XX_SEL_FLG.
	 * @param data XX_SEL_FLGArray
	 */
	public void setXxSelFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxSelFlg, data[j]);
		}
	}

}
