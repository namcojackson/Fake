// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20161129191434000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0950Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSAL0950;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NSAL0950 is data bean class.
 */
public class NSAL0950Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** X*/
	public static final String X = "X";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** GLBL_CMPY_CD_S*/
	public static final String glblCmpyCd_S = "glblCmpyCd_S";

	/** SLS_DT_S*/
	public static final String slsDt_S = "slsDt_S";

	/** XX_FILE_DATA_D*/
	public static final String xxFileData_D = "xxFileData_D";

	/** XX_POP_PRM_SE*/
	public static final String xxPopPrm_SE = "xxPopPrm_SE";

	/** DS_CONTR_VLD_CATG_CD_SS*/
	public static final String dsContrVldCatgCd_SS = "dsContrVldCatgCd_SS";

	/** DS_CONTR_VLD_CATG_CD_SC*/
	public static final String dsContrVldCatgCd_SC = "dsContrVldCatgCd_SC";

	/** DS_CONTR_VLD_CATG_DESC_TXT_SC*/
	public static final String dsContrVldCatgDescTxt_SC = "dsContrVldCatgDescTxt_SC";

	/** DS_CONTR_VLD_NM_S*/
	public static final String dsContrVldNm_S = "dsContrVldNm_S";

	/** DS_CONTR_VLD_DESC_TXT_S*/
	public static final String dsContrVldDescTxt_S = "dsContrVldDescTxt_S";

	/** VLD_LVL_CONTR_FLG_S*/
	public static final String vldLvlContrFlg_S = "vldLvlContrFlg_S";

	/** VLD_LVL_MACH_FLG_S*/
	public static final String vldLvlMachFlg_S = "vldLvlMachFlg_S";

	/** EFF_FROM_DT_S*/
	public static final String effFromDt_S = "effFromDt_S";

	/** EFF_TO_DT_S*/
	public static final String effToDt_S = "effToDt_S";

	/** VLD_CMPT_TXT_SL*/
	public static final String vldCmptTxt_SL = "vldCmptTxt_SL";

	/** VLD_CMPT_TXT_S*/
	public static final String vldCmptTxt_S = "vldCmptTxt_S";

	/** PRIM_VLD_FLG_S*/
	public static final String primVldFlg_S = "primVldFlg_S";

	/** OVRD_VLD_RSLT_AVAL_FLG_S*/
	public static final String ovrdVldRsltAvalFlg_S = "ovrdVldRsltAvalFlg_S";

	/** VLD_REG_FLG_S*/
	public static final String vldRegFlg_S = "vldRegFlg_S";

	/** VLD_FLEET_FLG_S*/
	public static final String vldFleetFlg_S = "vldFleetFlg_S";

	/** VLD_AGGR_FLG_S*/
	public static final String vldAggrFlg_S = "vldAggrFlg_S";

	/** VLD_WTY_FLG_S*/
	public static final String vldWtyFlg_S = "vldWtyFlg_S";

	/** XX_RADIO_BTN_A*/
	public static final String xxRadioBtn_A = "xxRadioBtn_A";

	/** A*/
	public static final String A = "A";

	/** GLBL_CMPY_CD_A*/
	public static final String glblCmpyCd_A = "glblCmpyCd_A";

	/** DS_CONTR_VLD_PK_A*/
	public static final String dsContrVldPk_A = "dsContrVldPk_A";

	/** DS_CONTR_VLD_CATG_CD_AS*/
	public static final String dsContrVldCatgCd_AS = "dsContrVldCatgCd_AS";

	/** DS_CONTR_VLD_NM_A*/
	public static final String dsContrVldNm_A = "dsContrVldNm_A";

	/** DS_CONTR_VLD_DESC_TXT_A*/
	public static final String dsContrVldDescTxt_A = "dsContrVldDescTxt_A";

	/** VLD_LVL_CONTR_FLG_A*/
	public static final String vldLvlContrFlg_A = "vldLvlContrFlg_A";

	/** VLD_LVL_MACH_FLG_A*/
	public static final String vldLvlMachFlg_A = "vldLvlMachFlg_A";

	/** VLD_AGGR_FLG_A*/
	public static final String vldAggrFlg_A = "vldAggrFlg_A";

	/** VLD_FLEET_FLG_A*/
	public static final String vldFleetFlg_A = "vldFleetFlg_A";

	/** VLD_REG_FLG_A*/
	public static final String vldRegFlg_A = "vldRegFlg_A";

	/** VLD_WTY_FLG_A*/
	public static final String vldWtyFlg_A = "vldWtyFlg_A";

	/** PRIM_VLD_FLG_A*/
	public static final String primVldFlg_A = "primVldFlg_A";

	/** OVRD_VLD_RSLT_AVAL_FLG_A*/
	public static final String ovrdVldRsltAvalFlg_A = "ovrdVldRsltAvalFlg_A";

	/** EFF_FROM_DT_A*/
	public static final String effFromDt_A = "effFromDt_A";

	/** EFF_TO_DT_A*/
	public static final String effToDt_A = "effToDt_A";

	/** VLD_CMPT_TXT_A*/
	public static final String vldCmptTxt_A = "vldCmptTxt_A";

	/** _EZUpTimeZone_A*/
	public static final String ezUpTimeZone_A = "ezUpTimeZone_A";

	/** _EZUpdateDateTime_A*/
	public static final String ezUpTime_A = "ezUpTime_A";

	/** XX_REC_HIST_CRAT_TS_A*/
	public static final String xxRecHistCratTs_A = "xxRecHistCratTs_A";

	/** XX_REC_HIST_CRAT_BY_NM_A*/
	public static final String xxRecHistCratByNm_A = "xxRecHistCratByNm_A";

	/** XX_REC_HIST_UPD_TS_A*/
	public static final String xxRecHistUpdTs_A = "xxRecHistUpdTs_A";

	/** XX_REC_HIST_UPD_BY_NM_A*/
	public static final String xxRecHistUpdByNm_A = "xxRecHistUpdByNm_A";

	/** XX_REC_HIST_TBL_NM_A*/
	public static final String xxRecHistTblNm_A = "xxRecHistTblNm_A";

	/** GLBL_CMPY_CD_D*/
	public static final String glblCmpyCd_D = "glblCmpyCd_D";

	/** DS_CONTR_VLD_PK_D*/
	public static final String dsContrVldPk_D = "dsContrVldPk_D";

	/** DS_CONTR_VLD_CATG_CD_DS*/
	public static final String dsContrVldCatgCd_DS = "dsContrVldCatgCd_DS";

	/** DS_CONTR_VLD_NM_D*/
	public static final String dsContrVldNm_D = "dsContrVldNm_D";

	/** DS_CONTR_VLD_DESC_TXT_D*/
	public static final String dsContrVldDescTxt_D = "dsContrVldDescTxt_D";

	/** VLD_LVL_CONTR_FLG_D*/
	public static final String vldLvlContrFlg_D = "vldLvlContrFlg_D";

	/** VLD_LVL_MACH_FLG_D*/
	public static final String vldLvlMachFlg_D = "vldLvlMachFlg_D";

	/** EFF_FROM_DT_D*/
	public static final String effFromDt_D = "effFromDt_D";

	/** EFF_TO_DT_D*/
	public static final String effToDt_D = "effToDt_D";

	/** VLD_CMPT_TXT_DL*/
	public static final String vldCmptTxt_DL = "vldCmptTxt_DL";

	/** VLD_CMPT_TXT_D*/
	public static final String vldCmptTxt_D = "vldCmptTxt_D";

	/** PRIM_VLD_FLG_D*/
	public static final String primVldFlg_D = "primVldFlg_D";

	/** OVRD_VLD_RSLT_AVAL_FLG_D*/
	public static final String ovrdVldRsltAvalFlg_D = "ovrdVldRsltAvalFlg_D";

	/** VLD_REG_FLG_D*/
	public static final String vldRegFlg_D = "vldRegFlg_D";

	/** VLD_FLEET_FLG_D*/
	public static final String vldFleetFlg_D = "vldFleetFlg_D";

	/** VLD_AGGR_FLG_D*/
	public static final String vldAggrFlg_D = "vldAggrFlg_D";

	/** VLD_WTY_FLG_D*/
	public static final String vldWtyFlg_D = "vldWtyFlg_D";

	/**
	 * Method name:NSAL0950 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSAL0950Bean is generated
	 * <dd>Remarks:
	 */
	public NSAL0950Bean() {
		super("business.servlet.NSAL0950.NSAL0950BMsg");
	}

	/**
	 * get  XX_COMN_SCR_QUERY_COL_NM.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_QUERY_COL_NM
	 */
	public String getXxComnScrQueryColNm(int idx1) {
		return outputValue(X, idx1, xxComnScrQueryColNm);
	}

	/**
	 * set  XX_COMN_SCR_QUERY_COL_NM.
	 * @param data XX_COMN_SCR_QUERY_COL_NMArray
	 */
	public void setXxComnScrQueryColNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(X);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(X, i, xxComnScrQueryColNm, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt(int idx1) {
		return outputValue(X, idx1, xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXTArray
	 */
	public void setXxComnScrColValTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(X);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(X, i, xxComnScrColValTxt, data[j]);
		}
	}

	/**
	 * get  GLBL_CMPY_CD_S.
	 * @return GLBL_CMPY_CD_S
	 */
	public String getGlblCmpyCd_S() {
		return outputValue(glblCmpyCd_S);
	}

	/**
	 * set  GLBL_CMPY_CD_S.
	 * @param data GLBL_CMPY_CD_S
	 */
	public void setGlblCmpyCd_S(String data) {
		inputValue(glblCmpyCd_S,data);
	}

	/**
	 * get  SLS_DT_S.
	 * @return SLS_DT_S
	 */
	public String getSlsDt_S() {
		return outputValue(slsDt_S, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  SLS_DT_S.
	 * @param data SLS_DT_S
	 */
	public void setSlsDt_S(String data) {
		inputValue(slsDt_S, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  XX_FILE_DATA_D.
	 * @param data XX_FILE_DATA_D
	 */
	public void setXxFileData_D(MimeSource data) {
		inputValue(xxFileData_D, data);
	}

	/**
	 * get  XX_POP_PRM_SE.
	 * @return XX_POP_PRM_SE
	 */
	public String getXxPopPrm_SE() {
		return outputValue(xxPopPrm_SE);
	}

	/**
	 * set  XX_POP_PRM_SE.
	 * @param data XX_POP_PRM_SE
	 */
	public void setXxPopPrm_SE(String data) {
		inputValue(xxPopPrm_SE,data);
	}

	/**
	 * get  DS_CONTR_VLD_CATG_CD_SS.
	 * @return DS_CONTR_VLD_CATG_CD_SS
	 */
	public String getDsContrVldCatgCd_SS() {
		return outputValue(dsContrVldCatgCd_SS);
	}

	/**
	 * set  DS_CONTR_VLD_CATG_CD_SS.
	 * @param data DS_CONTR_VLD_CATG_CD_SS
	 */
	public void setDsContrVldCatgCd_SS(String data) {
		inputValue(dsContrVldCatgCd_SS,data);
	}

	/**
	 * get  DS_CONTR_VLD_CATG_CD_SC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_VLD_CATG_CD_SC
	 */
	public String getDsContrVldCatgCd_SC(int idx1) {
	 	 return outputValue(dsContrVldCatgCd_SC, idx1);
	}

	/**
	 * get  DS_CONTR_VLD_CATG_DESC_TXT_SC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_VLD_CATG_DESC_TXT_SC
	 */
	public String getDsContrVldCatgDescTxt_SC(int idx1) {
	 	 return outputValue(dsContrVldCatgDescTxt_SC, idx1);
	}

	/**
	 * get  DS_CONTR_VLD_NM_S.
	 * @return DS_CONTR_VLD_NM_S
	 */
	public String getDsContrVldNm_S() {
		return outputValue(dsContrVldNm_S);
	}

	/**
	 * set  DS_CONTR_VLD_NM_S.
	 * @param data DS_CONTR_VLD_NM_S
	 */
	public void setDsContrVldNm_S(String data) {
		inputValue(dsContrVldNm_S,data);
	}

	/**
	 * get  DS_CONTR_VLD_DESC_TXT_S.
	 * @return DS_CONTR_VLD_DESC_TXT_S
	 */
	public String getDsContrVldDescTxt_S() {
		return outputValue(dsContrVldDescTxt_S);
	}

	/**
	 * set  DS_CONTR_VLD_DESC_TXT_S.
	 * @param data DS_CONTR_VLD_DESC_TXT_S
	 */
	public void setDsContrVldDescTxt_S(String data) {
		inputValue(dsContrVldDescTxt_S,data);
	}

	/**
	 * get  VLD_LVL_CONTR_FLG_S.
	 * @return VLD_LVL_CONTR_FLG_S
	 */
	public String getVldLvlContrFlg_S() {
		return outputValue(vldLvlContrFlg_S);
	}

	/**
	 * set  VLD_LVL_CONTR_FLG_S.
	 * @param data VLD_LVL_CONTR_FLG_S
	 */
	public void setVldLvlContrFlg_S(String data) {
		inputValue(vldLvlContrFlg_S,data);
	}

	/**
	 * get  VLD_LVL_MACH_FLG_S.
	 * @return VLD_LVL_MACH_FLG_S
	 */
	public String getVldLvlMachFlg_S() {
		return outputValue(vldLvlMachFlg_S);
	}

	/**
	 * set  VLD_LVL_MACH_FLG_S.
	 * @param data VLD_LVL_MACH_FLG_S
	 */
	public void setVldLvlMachFlg_S(String data) {
		inputValue(vldLvlMachFlg_S,data);
	}

	/**
	 * get  EFF_FROM_DT_S.
	 * @return EFF_FROM_DT_S
	 */
	public String getEffFromDt_S() {
		return outputValue(effFromDt_S, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_S.
	 * @param data EFF_FROM_DT_S
	 */
	public void setEffFromDt_S(String data) {
		inputValue(effFromDt_S, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  EFF_TO_DT_S.
	 * @return EFF_TO_DT_S
	 */
	public String getEffToDt_S() {
		return outputValue(effToDt_S, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_TO_DT_S.
	 * @param data EFF_TO_DT_S
	 */
	public void setEffToDt_S(String data) {
		inputValue(effToDt_S, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  VLD_CMPT_TXT_SL.
	 * @return VLD_CMPT_TXT_SL
	 */
	public String getVldCmptTxt_SL() {
		return outputValue(vldCmptTxt_SL);
	}

	/**
	 * set  VLD_CMPT_TXT_SL.
	 * @param data VLD_CMPT_TXT_SL
	 */
	public void setVldCmptTxt_SL(String data) {
		inputValue(vldCmptTxt_SL,data);
	}

	/**
	 * get  VLD_CMPT_TXT_S.
	 * @return VLD_CMPT_TXT_S
	 */
	public String getVldCmptTxt_S() {
		return outputValue(vldCmptTxt_S);
	}

	/**
	 * set  VLD_CMPT_TXT_S.
	 * @param data VLD_CMPT_TXT_S
	 */
	public void setVldCmptTxt_S(String data) {
		inputValue(vldCmptTxt_S,data);
	}

	/**
	 * get  PRIM_VLD_FLG_S.
	 * @return PRIM_VLD_FLG_S
	 */
	public String getPrimVldFlg_S() {
		return outputValue(primVldFlg_S);
	}

	/**
	 * set  PRIM_VLD_FLG_S.
	 * @param data PRIM_VLD_FLG_S
	 */
	public void setPrimVldFlg_S(String data) {
		inputValue(primVldFlg_S,data);
	}

	/**
	 * get  OVRD_VLD_RSLT_AVAL_FLG_S.
	 * @return OVRD_VLD_RSLT_AVAL_FLG_S
	 */
	public String getOvrdVldRsltAvalFlg_S() {
		return outputValue(ovrdVldRsltAvalFlg_S);
	}

	/**
	 * set  OVRD_VLD_RSLT_AVAL_FLG_S.
	 * @param data OVRD_VLD_RSLT_AVAL_FLG_S
	 */
	public void setOvrdVldRsltAvalFlg_S(String data) {
		inputValue(ovrdVldRsltAvalFlg_S,data);
	}

	/**
	 * get  VLD_REG_FLG_S.
	 * @return VLD_REG_FLG_S
	 */
	public String getVldRegFlg_S() {
		return outputValue(vldRegFlg_S);
	}

	/**
	 * set  VLD_REG_FLG_S.
	 * @param data VLD_REG_FLG_S
	 */
	public void setVldRegFlg_S(String data) {
		inputValue(vldRegFlg_S,data);
	}

	/**
	 * get  VLD_FLEET_FLG_S.
	 * @return VLD_FLEET_FLG_S
	 */
	public String getVldFleetFlg_S() {
		return outputValue(vldFleetFlg_S);
	}

	/**
	 * set  VLD_FLEET_FLG_S.
	 * @param data VLD_FLEET_FLG_S
	 */
	public void setVldFleetFlg_S(String data) {
		inputValue(vldFleetFlg_S,data);
	}

	/**
	 * get  VLD_AGGR_FLG_S.
	 * @return VLD_AGGR_FLG_S
	 */
	public String getVldAggrFlg_S() {
		return outputValue(vldAggrFlg_S);
	}

	/**
	 * set  VLD_AGGR_FLG_S.
	 * @param data VLD_AGGR_FLG_S
	 */
	public void setVldAggrFlg_S(String data) {
		inputValue(vldAggrFlg_S,data);
	}

	/**
	 * get  VLD_WTY_FLG_S.
	 * @return VLD_WTY_FLG_S
	 */
	public String getVldWtyFlg_S() {
		return outputValue(vldWtyFlg_S);
	}

	/**
	 * set  VLD_WTY_FLG_S.
	 * @param data VLD_WTY_FLG_S
	 */
	public void setVldWtyFlg_S(String data) {
		inputValue(vldWtyFlg_S,data);
	}

	/**
	 * get  XX_RADIO_BTN_A.
	 * @return XX_RADIO_BTN_A
	 */
	public String getXxRadioBtn_A() {
		return outputValue(xxRadioBtn_A);
	}

	/**
	 * set  XX_RADIO_BTN_A.
	 * @param data XX_RADIO_BTN_A
	 */
	public void setXxRadioBtn_A(String data) {
		inputValue(xxRadioBtn_A,data);
	}

	/**
	 * get  GLBL_CMPY_CD_A.
	 * @param idx1 List Number
	 * @return GLBL_CMPY_CD_A
	 */
	public String getGlblCmpyCd_A(int idx1) {
		return outputValue(A, idx1, glblCmpyCd_A);
	}

	/**
	 * set  GLBL_CMPY_CD_A.
	 * @param data GLBL_CMPY_CD_AArray
	 */
	public void setGlblCmpyCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, glblCmpyCd_A, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_VLD_PK_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_VLD_PK_A
	 */
	public String getDsContrVldPk_A(int idx1) {
		return outputValue(A, idx1, dsContrVldPk_A);
	}

	/**
	 * set  DS_CONTR_VLD_PK_A.
	 * @param data DS_CONTR_VLD_PK_AArray
	 */
	public void setDsContrVldPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrVldPk_A, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_VLD_CATG_CD_AS.
	 * @param idx1 List Number
	 * @return DS_CONTR_VLD_CATG_CD_AS
	 */
	public String getDsContrVldCatgCd_AS(int idx1) {
		return outputValue(A, idx1, dsContrVldCatgCd_AS);
	}

	/**
	 * set  DS_CONTR_VLD_CATG_CD_AS.
	 * @param data DS_CONTR_VLD_CATG_CD_ASArray
	 */
	public void setDsContrVldCatgCd_AS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrVldCatgCd_AS, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_VLD_NM_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_VLD_NM_A
	 */
	public String getDsContrVldNm_A(int idx1) {
		return outputValue(A, idx1, dsContrVldNm_A);
	}

	/**
	 * set  DS_CONTR_VLD_NM_A.
	 * @param data DS_CONTR_VLD_NM_AArray
	 */
	public void setDsContrVldNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrVldNm_A, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_VLD_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_VLD_DESC_TXT_A
	 */
	public String getDsContrVldDescTxt_A(int idx1) {
		return outputValue(A, idx1, dsContrVldDescTxt_A);
	}

	/**
	 * set  DS_CONTR_VLD_DESC_TXT_A.
	 * @param data DS_CONTR_VLD_DESC_TXT_AArray
	 */
	public void setDsContrVldDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrVldDescTxt_A, data[j]);
		}
	}

	/**
	 * get  VLD_LVL_CONTR_FLG_A.
	 * @param idx1 List Number
	 * @return VLD_LVL_CONTR_FLG_A
	 */
	public String getVldLvlContrFlg_A(int idx1) {
		return outputValue(A, idx1, vldLvlContrFlg_A);
	}

	/**
	 * set  VLD_LVL_CONTR_FLG_A.
	 * @param data VLD_LVL_CONTR_FLG_AArray
	 */
	public void setVldLvlContrFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vldLvlContrFlg_A, data[j]);
		}
	}

	/**
	 * get  VLD_LVL_MACH_FLG_A.
	 * @param idx1 List Number
	 * @return VLD_LVL_MACH_FLG_A
	 */
	public String getVldLvlMachFlg_A(int idx1) {
		return outputValue(A, idx1, vldLvlMachFlg_A);
	}

	/**
	 * set  VLD_LVL_MACH_FLG_A.
	 * @param data VLD_LVL_MACH_FLG_AArray
	 */
	public void setVldLvlMachFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vldLvlMachFlg_A, data[j]);
		}
	}

	/**
	 * get  VLD_AGGR_FLG_A.
	 * @param idx1 List Number
	 * @return VLD_AGGR_FLG_A
	 */
	public String getVldAggrFlg_A(int idx1) {
		return outputValue(A, idx1, vldAggrFlg_A);
	}

	/**
	 * set  VLD_AGGR_FLG_A.
	 * @param data VLD_AGGR_FLG_AArray
	 */
	public void setVldAggrFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vldAggrFlg_A, data[j]);
		}
	}

	/**
	 * get  VLD_FLEET_FLG_A.
	 * @param idx1 List Number
	 * @return VLD_FLEET_FLG_A
	 */
	public String getVldFleetFlg_A(int idx1) {
		return outputValue(A, idx1, vldFleetFlg_A);
	}

	/**
	 * set  VLD_FLEET_FLG_A.
	 * @param data VLD_FLEET_FLG_AArray
	 */
	public void setVldFleetFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vldFleetFlg_A, data[j]);
		}
	}

	/**
	 * get  VLD_REG_FLG_A.
	 * @param idx1 List Number
	 * @return VLD_REG_FLG_A
	 */
	public String getVldRegFlg_A(int idx1) {
		return outputValue(A, idx1, vldRegFlg_A);
	}

	/**
	 * set  VLD_REG_FLG_A.
	 * @param data VLD_REG_FLG_AArray
	 */
	public void setVldRegFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vldRegFlg_A, data[j]);
		}
	}

	/**
	 * get  VLD_WTY_FLG_A.
	 * @param idx1 List Number
	 * @return VLD_WTY_FLG_A
	 */
	public String getVldWtyFlg_A(int idx1) {
		return outputValue(A, idx1, vldWtyFlg_A);
	}

	/**
	 * set  VLD_WTY_FLG_A.
	 * @param data VLD_WTY_FLG_AArray
	 */
	public void setVldWtyFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vldWtyFlg_A, data[j]);
		}
	}

	/**
	 * get  PRIM_VLD_FLG_A.
	 * @param idx1 List Number
	 * @return PRIM_VLD_FLG_A
	 */
	public String getPrimVldFlg_A(int idx1) {
		return outputValue(A, idx1, primVldFlg_A);
	}

	/**
	 * set  PRIM_VLD_FLG_A.
	 * @param data PRIM_VLD_FLG_AArray
	 */
	public void setPrimVldFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, primVldFlg_A, data[j]);
		}
	}

	/**
	 * get  OVRD_VLD_RSLT_AVAL_FLG_A.
	 * @param idx1 List Number
	 * @return OVRD_VLD_RSLT_AVAL_FLG_A
	 */
	public String getOvrdVldRsltAvalFlg_A(int idx1) {
		return outputValue(A, idx1, ovrdVldRsltAvalFlg_A);
	}

	/**
	 * set  OVRD_VLD_RSLT_AVAL_FLG_A.
	 * @param data OVRD_VLD_RSLT_AVAL_FLG_AArray
	 */
	public void setOvrdVldRsltAvalFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ovrdVldRsltAvalFlg_A, data[j]);
		}
	}

	/**
	 * get  EFF_FROM_DT_A.
	 * @param idx1 List Number
	 * @return EFF_FROM_DT_A
	 */
	public String getEffFromDt_A(int idx1) {
		return outputValue(A, idx1, effFromDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_A.
	 * @param data EFF_FROM_DT_AArray
	 */
	public void setEffFromDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effFromDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  EFF_TO_DT_A.
	 * @param idx1 List Number
	 * @return EFF_TO_DT_A
	 */
	public String getEffToDt_A(int idx1) {
		return outputValue(A, idx1, effToDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_TO_DT_A.
	 * @param data EFF_TO_DT_AArray
	 */
	public void setEffToDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effToDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  VLD_CMPT_TXT_A.
	 * @param idx1 List Number
	 * @return VLD_CMPT_TXT_A
	 */
	public String getVldCmptTxt_A(int idx1) {
		return outputValue(A, idx1, vldCmptTxt_A);
	}

	/**
	 * set  VLD_CMPT_TXT_A.
	 * @param data VLD_CMPT_TXT_AArray
	 */
	public void setVldCmptTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vldCmptTxt_A, data[j]);
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
	 * get  XX_REC_HIST_CRAT_TS_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_CRAT_TS_A
	 */
	public String getXxRecHistCratTs_A(int idx1) {
		return outputValue(A, idx1, xxRecHistCratTs_A);
	}

	/**
	 * set  XX_REC_HIST_CRAT_TS_A.
	 * @param data XX_REC_HIST_CRAT_TS_AArray
	 */
	public void setXxRecHistCratTs_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistCratTs_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_CRAT_BY_NM_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_CRAT_BY_NM_A
	 */
	public String getXxRecHistCratByNm_A(int idx1) {
		return outputValue(A, idx1, xxRecHistCratByNm_A);
	}

	/**
	 * set  XX_REC_HIST_CRAT_BY_NM_A.
	 * @param data XX_REC_HIST_CRAT_BY_NM_AArray
	 */
	public void setXxRecHistCratByNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistCratByNm_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_UPD_TS_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_UPD_TS_A
	 */
	public String getXxRecHistUpdTs_A(int idx1) {
		return outputValue(A, idx1, xxRecHistUpdTs_A);
	}

	/**
	 * set  XX_REC_HIST_UPD_TS_A.
	 * @param data XX_REC_HIST_UPD_TS_AArray
	 */
	public void setXxRecHistUpdTs_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistUpdTs_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_UPD_BY_NM_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_UPD_BY_NM_A
	 */
	public String getXxRecHistUpdByNm_A(int idx1) {
		return outputValue(A, idx1, xxRecHistUpdByNm_A);
	}

	/**
	 * set  XX_REC_HIST_UPD_BY_NM_A.
	 * @param data XX_REC_HIST_UPD_BY_NM_AArray
	 */
	public void setXxRecHistUpdByNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistUpdByNm_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_TBL_NM_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_TBL_NM_A
	 */
	public String getXxRecHistTblNm_A(int idx1) {
		return outputValue(A, idx1, xxRecHistTblNm_A);
	}

	/**
	 * set  XX_REC_HIST_TBL_NM_A.
	 * @param data XX_REC_HIST_TBL_NM_AArray
	 */
	public void setXxRecHistTblNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistTblNm_A, data[j]);
		}
	}

	/**
	 * get  GLBL_CMPY_CD_D.
	 * @return GLBL_CMPY_CD_D
	 */
	public String getGlblCmpyCd_D() {
		return outputValue(glblCmpyCd_D);
	}

	/**
	 * set  GLBL_CMPY_CD_D.
	 * @param data GLBL_CMPY_CD_D
	 */
	public void setGlblCmpyCd_D(String data) {
		inputValue(glblCmpyCd_D,data);
	}

	/**
	 * get  DS_CONTR_VLD_PK_D.
	 * @return DS_CONTR_VLD_PK_D
	 */
	public String getDsContrVldPk_D() {
		return outputValue(dsContrVldPk_D);
	}

	/**
	 * set  DS_CONTR_VLD_PK_D.
	 * @param data DS_CONTR_VLD_PK_D
	 */
	public void setDsContrVldPk_D(String data) {
		inputValue(dsContrVldPk_D,data);
	}

	/**
	 * get  DS_CONTR_VLD_CATG_CD_DS.
	 * @return DS_CONTR_VLD_CATG_CD_DS
	 */
	public String getDsContrVldCatgCd_DS() {
		return outputValue(dsContrVldCatgCd_DS);
	}

	/**
	 * set  DS_CONTR_VLD_CATG_CD_DS.
	 * @param data DS_CONTR_VLD_CATG_CD_DS
	 */
	public void setDsContrVldCatgCd_DS(String data) {
		inputValue(dsContrVldCatgCd_DS,data);
	}

	/**
	 * get  DS_CONTR_VLD_NM_D.
	 * @return DS_CONTR_VLD_NM_D
	 */
	public String getDsContrVldNm_D() {
		return outputValue(dsContrVldNm_D);
	}

	/**
	 * set  DS_CONTR_VLD_NM_D.
	 * @param data DS_CONTR_VLD_NM_D
	 */
	public void setDsContrVldNm_D(String data) {
		inputValue(dsContrVldNm_D,data);
	}

	/**
	 * get  DS_CONTR_VLD_DESC_TXT_D.
	 * @return DS_CONTR_VLD_DESC_TXT_D
	 */
	public String getDsContrVldDescTxt_D() {
		return outputValue(dsContrVldDescTxt_D);
	}

	/**
	 * set  DS_CONTR_VLD_DESC_TXT_D.
	 * @param data DS_CONTR_VLD_DESC_TXT_D
	 */
	public void setDsContrVldDescTxt_D(String data) {
		inputValue(dsContrVldDescTxt_D,data);
	}

	/**
	 * get  VLD_LVL_CONTR_FLG_D.
	 * @return VLD_LVL_CONTR_FLG_D
	 */
	public String getVldLvlContrFlg_D() {
		return outputValue(vldLvlContrFlg_D);
	}

	/**
	 * set  VLD_LVL_CONTR_FLG_D.
	 * @param data VLD_LVL_CONTR_FLG_D
	 */
	public void setVldLvlContrFlg_D(String data) {
		inputValue(vldLvlContrFlg_D,data);
	}

	/**
	 * get  VLD_LVL_MACH_FLG_D.
	 * @return VLD_LVL_MACH_FLG_D
	 */
	public String getVldLvlMachFlg_D() {
		return outputValue(vldLvlMachFlg_D);
	}

	/**
	 * set  VLD_LVL_MACH_FLG_D.
	 * @param data VLD_LVL_MACH_FLG_D
	 */
	public void setVldLvlMachFlg_D(String data) {
		inputValue(vldLvlMachFlg_D,data);
	}

	/**
	 * get  EFF_FROM_DT_D.
	 * @return EFF_FROM_DT_D
	 */
	public String getEffFromDt_D() {
		return outputValue(effFromDt_D, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_D.
	 * @param data EFF_FROM_DT_D
	 */
	public void setEffFromDt_D(String data) {
		inputValue(effFromDt_D, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  EFF_TO_DT_D.
	 * @return EFF_TO_DT_D
	 */
	public String getEffToDt_D() {
		return outputValue(effToDt_D, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_TO_DT_D.
	 * @param data EFF_TO_DT_D
	 */
	public void setEffToDt_D(String data) {
		inputValue(effToDt_D, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  VLD_CMPT_TXT_DL.
	 * @return VLD_CMPT_TXT_DL
	 */
	public String getVldCmptTxt_DL() {
		return outputValue(vldCmptTxt_DL);
	}

	/**
	 * set  VLD_CMPT_TXT_DL.
	 * @param data VLD_CMPT_TXT_DL
	 */
	public void setVldCmptTxt_DL(String data) {
		inputValue(vldCmptTxt_DL,data);
	}

	/**
	 * get  VLD_CMPT_TXT_D.
	 * @return VLD_CMPT_TXT_D
	 */
	public String getVldCmptTxt_D() {
		return outputValue(vldCmptTxt_D);
	}

	/**
	 * set  VLD_CMPT_TXT_D.
	 * @param data VLD_CMPT_TXT_D
	 */
	public void setVldCmptTxt_D(String data) {
		inputValue(vldCmptTxt_D,data);
	}

	/**
	 * get  PRIM_VLD_FLG_D.
	 * @return PRIM_VLD_FLG_D
	 */
	public String getPrimVldFlg_D() {
		return outputValue(primVldFlg_D);
	}

	/**
	 * set  PRIM_VLD_FLG_D.
	 * @param data PRIM_VLD_FLG_D
	 */
	public void setPrimVldFlg_D(String data) {
		inputValue(primVldFlg_D,data);
	}

	/**
	 * get  OVRD_VLD_RSLT_AVAL_FLG_D.
	 * @return OVRD_VLD_RSLT_AVAL_FLG_D
	 */
	public String getOvrdVldRsltAvalFlg_D() {
		return outputValue(ovrdVldRsltAvalFlg_D);
	}

	/**
	 * set  OVRD_VLD_RSLT_AVAL_FLG_D.
	 * @param data OVRD_VLD_RSLT_AVAL_FLG_D
	 */
	public void setOvrdVldRsltAvalFlg_D(String data) {
		inputValue(ovrdVldRsltAvalFlg_D,data);
	}

	/**
	 * get  VLD_REG_FLG_D.
	 * @return VLD_REG_FLG_D
	 */
	public String getVldRegFlg_D() {
		return outputValue(vldRegFlg_D);
	}

	/**
	 * set  VLD_REG_FLG_D.
	 * @param data VLD_REG_FLG_D
	 */
	public void setVldRegFlg_D(String data) {
		inputValue(vldRegFlg_D,data);
	}

	/**
	 * get  VLD_FLEET_FLG_D.
	 * @return VLD_FLEET_FLG_D
	 */
	public String getVldFleetFlg_D() {
		return outputValue(vldFleetFlg_D);
	}

	/**
	 * set  VLD_FLEET_FLG_D.
	 * @param data VLD_FLEET_FLG_D
	 */
	public void setVldFleetFlg_D(String data) {
		inputValue(vldFleetFlg_D,data);
	}

	/**
	 * get  VLD_AGGR_FLG_D.
	 * @return VLD_AGGR_FLG_D
	 */
	public String getVldAggrFlg_D() {
		return outputValue(vldAggrFlg_D);
	}

	/**
	 * set  VLD_AGGR_FLG_D.
	 * @param data VLD_AGGR_FLG_D
	 */
	public void setVldAggrFlg_D(String data) {
		inputValue(vldAggrFlg_D,data);
	}

	/**
	 * get  VLD_WTY_FLG_D.
	 * @return VLD_WTY_FLG_D
	 */
	public String getVldWtyFlg_D() {
		return outputValue(vldWtyFlg_D);
	}

	/**
	 * set  VLD_WTY_FLG_D.
	 * @param data VLD_WTY_FLG_D
	 */
	public void setVldWtyFlg_D(String data) {
		inputValue(vldWtyFlg_D,data);
	}

}

