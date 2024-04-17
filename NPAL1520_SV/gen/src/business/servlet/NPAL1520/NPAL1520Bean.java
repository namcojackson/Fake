// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20221020071144000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1520Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NPAL1520;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NPAL1520 is data bean class.
 */
public class NPAL1520Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** SRCH_OPT_PK_PD*/
	public static final String srchOptPk_PD = "srchOptPk_PD";

	/** SRCH_OPT_NM_PD*/
	public static final String srchOptNm_PD = "srchOptNm_PD";

	/** SRCH_OPT_PK_SL*/
	public static final String srchOptPk_SL = "srchOptPk_SL";

	/** SRCH_OPT_NM_TX*/
	public static final String srchOptNm_TX = "srchOptNm_TX";

	/** SRCH_OPT_USR_ID_U1*/
	public static final String srchOptUsrId_U1 = "srchOptUsrId_U1";

	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** MRP_PLN_NM*/
	public static final String mrpPlnNm = "mrpPlnNm";

	/** MDSE_CD*/
	public static final String mdseCd = "mdseCd";

	/** MDSE_DESC_SHORT_TXT*/
	public static final String mdseDescShortTxt = "mdseDescShortTxt";

	/** RTL_WH_CATG_CD_PD*/
	public static final String rtlWhCatgCd_PD = "rtlWhCatgCd_PD";

	/** RTL_WH_CATG_DESC_TXT_PD*/
	public static final String rtlWhCatgDescTxt_PD = "rtlWhCatgDescTxt_PD";

	/** RTL_WH_CATG_CD_SL*/
	public static final String rtlWhCatgCd_SL = "rtlWhCatgCd_SL";

	/** LOC_TP_CD*/
	public static final String locTpCd = "locTpCd";

	/** RTL_WH_CD*/
	public static final String rtlWhCd = "rtlWhCd";

	/** RTL_WH_NM_W1*/
	public static final String rtlWhNm_W1 = "rtlWhNm_W1";

	/** RTL_SWH_CD*/
	public static final String rtlSwhCd = "rtlSwhCd";

	/** RTL_SWH_NM_S1*/
	public static final String rtlSwhNm_S1 = "rtlSwhNm_S1";

	/** WH_MGR_PSN_CD*/
	public static final String whMgrPsnCd = "whMgrPsnCd";

	/** FULL_PSN_NM*/
	public static final String fullPsnNm = "fullPsnNm";

	/** PROCR_TP_CD_PD*/
	public static final String procrTpCd_PD = "procrTpCd_PD";

	/** PROCR_TP_DESC_TXT_PD*/
	public static final String procrTpDescTxt_PD = "procrTpDescTxt_PD";

	/** PROCR_TP_CD_SL*/
	public static final String procrTpCd_SL = "procrTpCd_SL";

	/** SRC_RTL_WH_CD*/
	public static final String srcRtlWhCd = "srcRtlWhCd";

	/** RTL_WH_NM_W2*/
	public static final String rtlWhNm_W2 = "rtlWhNm_W2";

	/** SRC_RTL_SWH_CD*/
	public static final String srcRtlSwhCd = "srcRtlSwhCd";

	/** RTL_SWH_NM_S2*/
	public static final String rtlSwhNm_S2 = "rtlSwhNm_S2";

	/** MRP_ENBL_FLG*/
	public static final String mrpEnblFlg = "mrpEnblFlg";

	/** CALC_ORD_PROC_CD*/
	public static final String calcOrdProcCd = "calcOrdProcCd";

	/** XX_COMN_COL_ORD_TXT*/
	public static final String xxComnColOrdTxt = "xxComnColOrdTxt";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** XX_RADIO_BTN*/
	public static final String xxRadioBtn = "xxRadioBtn";

	/** A*/
	public static final String A = "A";

	/** MRP_INFO_PK_A1*/
	public static final String mrpInfoPk_A1 = "mrpInfoPk_A1";

	/** MRP_PLN_NM_A1*/
	public static final String mrpPlnNm_A1 = "mrpPlnNm_A1";

	/** MDSE_CD_A1*/
	public static final String mdseCd_A1 = "mdseCd_A1";

	/** MDSE_DESC_SHORT_TXT_A1*/
	public static final String mdseDescShortTxt_A1 = "mdseDescShortTxt_A1";

	/** RTL_WH_CATG_DESC_TXT_A1*/
	public static final String rtlWhCatgDescTxt_A1 = "rtlWhCatgDescTxt_A1";

	/** RTL_WH_CD_A1*/
	public static final String rtlWhCd_A1 = "rtlWhCd_A1";

	/** RTL_WH_NM_A1*/
	public static final String rtlWhNm_A1 = "rtlWhNm_A1";

	/** RTL_SWH_CD_A1*/
	public static final String rtlSwhCd_A1 = "rtlSwhCd_A1";

	/** RTL_SWH_NM_A1*/
	public static final String rtlSwhNm_A1 = "rtlSwhNm_A1";

	/** WH_MGR_PSN_CD_A1*/
	public static final String whMgrPsnCd_A1 = "whMgrPsnCd_A1";

	/** FULL_PSN_NM_A1*/
	public static final String fullPsnNm_A1 = "fullPsnNm_A1";

	/** CALC_ORD_PROC_CD_A1*/
	public static final String calcOrdProcCd_A1 = "calcOrdProcCd_A1";

	/** ROP_QTY_A1*/
	public static final String ropQty_A1 = "ropQty_A1";

	/** MAX_INVTY_QTY_A1*/
	public static final String maxInvtyQty_A1 = "maxInvtyQty_A1";

	/** MRP_ENBL_FLG_A1*/
	public static final String mrpEnblFlg_A1 = "mrpEnblFlg_A1";

	/** PROCR_TP_DESC_TXT_A1*/
	public static final String procrTpDescTxt_A1 = "procrTpDescTxt_A1";

	/** SRC_RTL_WH_CD_A1*/
	public static final String srcRtlWhCd_A1 = "srcRtlWhCd_A1";

	/** RTL_WH_NM_A2*/
	public static final String rtlWhNm_A2 = "rtlWhNm_A2";

	/** SRC_RTL_SWH_CD_A1*/
	public static final String srcRtlSwhCd_A1 = "srcRtlSwhCd_A1";

	/** RTL_SWH_NM_A2*/
	public static final String rtlSwhNm_A2 = "rtlSwhNm_A2";

	/** XX_POP_PRM_EV*/
	public static final String xxPopPrm_EV = "xxPopPrm_EV";

	/** P*/
	public static final String P = "P";

	/** XX_POP_PRM*/
	public static final String xxPopPrm = "xxPopPrm";

	/** XX_TBL_NM_P1*/
	public static final String xxTblNm_P1 = "xxTblNm_P1";

	/** XX_TBL_CD_COL_NM_P1*/
	public static final String xxTblCdColNm_P1 = "xxTblCdColNm_P1";

	/** XX_TBL_NM_COL_NM_P1*/
	public static final String xxTblNmColNm_P1 = "xxTblNmColNm_P1";

	/** XX_TBL_SORT_COL_NM_P1*/
	public static final String xxTblSortColNm_P1 = "xxTblSortColNm_P1";

	/** XX_SCR_NM_P1*/
	public static final String xxScrNm_P1 = "xxScrNm_P1";

	/** XX_HDR_CD_LB_NM_P1*/
	public static final String xxHdrCdLbNm_P1 = "xxHdrCdLbNm_P1";

	/** XX_HDR_NM_LB_NM_P1*/
	public static final String xxHdrNmLbNm_P1 = "xxHdrNmLbNm_P1";

	/** XX_DTL_CD_LB_NM_P1*/
	public static final String xxDtlCdLbNm_P1 = "xxDtlCdLbNm_P1";

	/** XX_DTL_NM_LB_NM_P1*/
	public static final String xxDtlNmLbNm_P1 = "xxDtlNmLbNm_P1";

	/** XX_COND_CD_P1*/
	public static final String xxCondCd_P1 = "xxCondCd_P1";

	/** XX_COND_NM_P1*/
	public static final String xxCondNm_P1 = "xxCondNm_P1";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** XX_POP_PRM_E0*/
	public static final String xxPopPrm_E0 = "xxPopPrm_E0";

	/** XX_POP_PRM_E1*/
	public static final String xxPopPrm_E1 = "xxPopPrm_E1";

	/** XX_POP_PRM_E2*/
	public static final String xxPopPrm_E2 = "xxPopPrm_E2";

	/** XX_POP_PRM_E3*/
	public static final String xxPopPrm_E3 = "xxPopPrm_E3";

	/** XX_POP_PRM_M0*/
	public static final String xxPopPrm_M0 = "xxPopPrm_M0";

	/** XX_POP_PRM_M1*/
	public static final String xxPopPrm_M1 = "xxPopPrm_M1";

	/** XX_POP_PRM_M2*/
	public static final String xxPopPrm_M2 = "xxPopPrm_M2";

	/** XX_POP_PRM_M3*/
	public static final String xxPopPrm_M3 = "xxPopPrm_M3";

	/** XX_POP_PRM_M4*/
	public static final String xxPopPrm_M4 = "xxPopPrm_M4";

	/** XX_POP_PRM_M5*/
	public static final String xxPopPrm_M5 = "xxPopPrm_M5";

	/** XX_POP_PRM_M6*/
	public static final String xxPopPrm_M6 = "xxPopPrm_M6";

	/** XX_POP_PRM_M7*/
	public static final String xxPopPrm_M7 = "xxPopPrm_M7";

	/** XX_POP_PRM_M8*/
	public static final String xxPopPrm_M8 = "xxPopPrm_M8";

	/** XX_POP_PRM_M9*/
	public static final String xxPopPrm_M9 = "xxPopPrm_M9";

	/**
	 * Method name:NPAL1520 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NPAL1520Bean is generated
	 * <dd>Remarks:
	 */
	public NPAL1520Bean() {
		super("business.servlet.NPAL1520.NPAL1520BMsg");
	}

	/**
	 * get  SRCH_OPT_PK_PD.
	 * @param idx1 Index Number
	 * @return SRCH_OPT_PK_PD
	 */
	public String getSrchOptPk_PD(int idx1) {
	 	 return outputValue(srchOptPk_PD, idx1);
	}

	/**
	 * get  SRCH_OPT_NM_PD.
	 * @param idx1 Index Number
	 * @return SRCH_OPT_NM_PD
	 */
	public String getSrchOptNm_PD(int idx1) {
	 	 return outputValue(srchOptNm_PD, idx1);
	}

	/**
	 * get  SRCH_OPT_PK_SL.
	 * @return SRCH_OPT_PK_SL
	 */
	public String getSrchOptPk_SL() {
		return outputValue(srchOptPk_SL);
	}

	/**
	 * set  SRCH_OPT_PK_SL.
	 * @param data SRCH_OPT_PK_SL
	 */
	public void setSrchOptPk_SL(String data) {
		inputValue(srchOptPk_SL,data);
	}

	/**
	 * get  SRCH_OPT_NM_TX.
	 * @return SRCH_OPT_NM_TX
	 */
	public String getSrchOptNm_TX() {
		return outputValue(srchOptNm_TX);
	}

	/**
	 * set  SRCH_OPT_NM_TX.
	 * @param data SRCH_OPT_NM_TX
	 */
	public void setSrchOptNm_TX(String data) {
		inputValue(srchOptNm_TX,data);
	}

	/**
	 * get  SRCH_OPT_USR_ID_U1.
	 * @return SRCH_OPT_USR_ID_U1
	 */
	public String getSrchOptUsrId_U1() {
		return outputValue(srchOptUsrId_U1);
	}

	/**
	 * set  SRCH_OPT_USR_ID_U1.
	 * @param data SRCH_OPT_USR_ID_U1
	 */
	public void setSrchOptUsrId_U1(String data) {
		inputValue(srchOptUsrId_U1,data);
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
	 * get  MRP_PLN_NM.
	 * @return MRP_PLN_NM
	 */
	public String getMrpPlnNm() {
		return outputValue(mrpPlnNm);
	}

	/**
	 * set  MRP_PLN_NM.
	 * @param data MRP_PLN_NM
	 */
	public void setMrpPlnNm(String data) {
		inputValue(mrpPlnNm,data);
	}

	/**
	 * get  MDSE_CD.
	 * @return MDSE_CD
	 */
	public String getMdseCd() {
		return outputValue(mdseCd);
	}

	/**
	 * set  MDSE_CD.
	 * @param data MDSE_CD
	 */
	public void setMdseCd(String data) {
		inputValue(mdseCd,data);
	}

	/**
	 * get  MDSE_DESC_SHORT_TXT.
	 * @return MDSE_DESC_SHORT_TXT
	 */
	public String getMdseDescShortTxt() {
		return outputValue(mdseDescShortTxt);
	}

	/**
	 * set  MDSE_DESC_SHORT_TXT.
	 * @param data MDSE_DESC_SHORT_TXT
	 */
	public void setMdseDescShortTxt(String data) {
		inputValue(mdseDescShortTxt,data);
	}

	/**
	 * get  RTL_WH_CATG_CD_PD.
	 * @param idx1 Index Number
	 * @return RTL_WH_CATG_CD_PD
	 */
	public String getRtlWhCatgCd_PD(int idx1) {
	 	 return outputValue(rtlWhCatgCd_PD, idx1);
	}

	/**
	 * get  RTL_WH_CATG_DESC_TXT_PD.
	 * @param idx1 Index Number
	 * @return RTL_WH_CATG_DESC_TXT_PD
	 */
	public String getRtlWhCatgDescTxt_PD(int idx1) {
	 	 return outputValue(rtlWhCatgDescTxt_PD, idx1);
	}

	/**
	 * get  RTL_WH_CATG_CD_SL.
	 * @return RTL_WH_CATG_CD_SL
	 */
	public String getRtlWhCatgCd_SL() {
		return outputValue(rtlWhCatgCd_SL);
	}

	/**
	 * set  RTL_WH_CATG_CD_SL.
	 * @param data RTL_WH_CATG_CD_SL
	 */
	public void setRtlWhCatgCd_SL(String data) {
		inputValue(rtlWhCatgCd_SL,data);
	}

	/**
	 * get  LOC_TP_CD.
	 * @return LOC_TP_CD
	 */
	public String getLocTpCd() {
		return outputValue(locTpCd);
	}

	/**
	 * set  LOC_TP_CD.
	 * @param data LOC_TP_CD
	 */
	public void setLocTpCd(String data) {
		inputValue(locTpCd,data);
	}

	/**
	 * get  RTL_WH_CD.
	 * @return RTL_WH_CD
	 */
	public String getRtlWhCd() {
		return outputValue(rtlWhCd);
	}

	/**
	 * set  RTL_WH_CD.
	 * @param data RTL_WH_CD
	 */
	public void setRtlWhCd(String data) {
		inputValue(rtlWhCd,data);
	}

	/**
	 * get  RTL_WH_NM_W1.
	 * @return RTL_WH_NM_W1
	 */
	public String getRtlWhNm_W1() {
		return outputValue(rtlWhNm_W1);
	}

	/**
	 * set  RTL_WH_NM_W1.
	 * @param data RTL_WH_NM_W1
	 */
	public void setRtlWhNm_W1(String data) {
		inputValue(rtlWhNm_W1,data);
	}

	/**
	 * get  RTL_SWH_CD.
	 * @return RTL_SWH_CD
	 */
	public String getRtlSwhCd() {
		return outputValue(rtlSwhCd);
	}

	/**
	 * set  RTL_SWH_CD.
	 * @param data RTL_SWH_CD
	 */
	public void setRtlSwhCd(String data) {
		inputValue(rtlSwhCd,data);
	}

	/**
	 * get  RTL_SWH_NM_S1.
	 * @return RTL_SWH_NM_S1
	 */
	public String getRtlSwhNm_S1() {
		return outputValue(rtlSwhNm_S1);
	}

	/**
	 * set  RTL_SWH_NM_S1.
	 * @param data RTL_SWH_NM_S1
	 */
	public void setRtlSwhNm_S1(String data) {
		inputValue(rtlSwhNm_S1,data);
	}

	/**
	 * get  WH_MGR_PSN_CD.
	 * @return WH_MGR_PSN_CD
	 */
	public String getWhMgrPsnCd() {
		return outputValue(whMgrPsnCd);
	}

	/**
	 * set  WH_MGR_PSN_CD.
	 * @param data WH_MGR_PSN_CD
	 */
	public void setWhMgrPsnCd(String data) {
		inputValue(whMgrPsnCd,data);
	}

	/**
	 * get  FULL_PSN_NM.
	 * @return FULL_PSN_NM
	 */
	public String getFullPsnNm() {
		return outputValue(fullPsnNm);
	}

	/**
	 * set  FULL_PSN_NM.
	 * @param data FULL_PSN_NM
	 */
	public void setFullPsnNm(String data) {
		inputValue(fullPsnNm,data);
	}

	/**
	 * get  PROCR_TP_CD_PD.
	 * @param idx1 Index Number
	 * @return PROCR_TP_CD_PD
	 */
	public String getProcrTpCd_PD(int idx1) {
	 	 return outputValue(procrTpCd_PD, idx1);
	}

	/**
	 * get  PROCR_TP_DESC_TXT_PD.
	 * @param idx1 Index Number
	 * @return PROCR_TP_DESC_TXT_PD
	 */
	public String getProcrTpDescTxt_PD(int idx1) {
	 	 return outputValue(procrTpDescTxt_PD, idx1);
	}

	/**
	 * get  PROCR_TP_CD_SL.
	 * @return PROCR_TP_CD_SL
	 */
	public String getProcrTpCd_SL() {
		return outputValue(procrTpCd_SL);
	}

	/**
	 * set  PROCR_TP_CD_SL.
	 * @param data PROCR_TP_CD_SL
	 */
	public void setProcrTpCd_SL(String data) {
		inputValue(procrTpCd_SL,data);
	}

	/**
	 * get  SRC_RTL_WH_CD.
	 * @return SRC_RTL_WH_CD
	 */
	public String getSrcRtlWhCd() {
		return outputValue(srcRtlWhCd);
	}

	/**
	 * set  SRC_RTL_WH_CD.
	 * @param data SRC_RTL_WH_CD
	 */
	public void setSrcRtlWhCd(String data) {
		inputValue(srcRtlWhCd,data);
	}

	/**
	 * get  RTL_WH_NM_W2.
	 * @return RTL_WH_NM_W2
	 */
	public String getRtlWhNm_W2() {
		return outputValue(rtlWhNm_W2);
	}

	/**
	 * set  RTL_WH_NM_W2.
	 * @param data RTL_WH_NM_W2
	 */
	public void setRtlWhNm_W2(String data) {
		inputValue(rtlWhNm_W2,data);
	}

	/**
	 * get  SRC_RTL_SWH_CD.
	 * @return SRC_RTL_SWH_CD
	 */
	public String getSrcRtlSwhCd() {
		return outputValue(srcRtlSwhCd);
	}

	/**
	 * set  SRC_RTL_SWH_CD.
	 * @param data SRC_RTL_SWH_CD
	 */
	public void setSrcRtlSwhCd(String data) {
		inputValue(srcRtlSwhCd,data);
	}

	/**
	 * get  RTL_SWH_NM_S2.
	 * @return RTL_SWH_NM_S2
	 */
	public String getRtlSwhNm_S2() {
		return outputValue(rtlSwhNm_S2);
	}

	/**
	 * set  RTL_SWH_NM_S2.
	 * @param data RTL_SWH_NM_S2
	 */
	public void setRtlSwhNm_S2(String data) {
		inputValue(rtlSwhNm_S2,data);
	}

	/**
	 * get  MRP_ENBL_FLG.
	 * @return MRP_ENBL_FLG
	 */
	public String getMrpEnblFlg() {
		return outputValue(mrpEnblFlg);
	}

	/**
	 * set  MRP_ENBL_FLG.
	 * @param data MRP_ENBL_FLG
	 */
	public void setMrpEnblFlg(String data) {
		inputValue(mrpEnblFlg,data);
	}

	/**
	 * get  CALC_ORD_PROC_CD.
	 * @return CALC_ORD_PROC_CD
	 */
	public String getCalcOrdProcCd() {
		return outputValue(calcOrdProcCd);
	}

	/**
	 * set  CALC_ORD_PROC_CD.
	 * @param data CALC_ORD_PROC_CD
	 */
	public void setCalcOrdProcCd(String data) {
		inputValue(calcOrdProcCd,data);
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
	 * get  XX_RADIO_BTN.
	 * @return XX_RADIO_BTN
	 */
	public String getXxRadioBtn() {
		return outputValue(xxRadioBtn);
	}

	/**
	 * set  XX_RADIO_BTN.
	 * @param data XX_RADIO_BTN
	 */
	public void setXxRadioBtn(String data) {
		inputValue(xxRadioBtn,data);
	}

	/**
	 * get  MRP_INFO_PK_A1.
	 * @param idx1 List Number
	 * @return MRP_INFO_PK_A1
	 */
	public String getMrpInfoPk_A1(int idx1) {
		return outputValue(A, idx1, mrpInfoPk_A1);
	}

	/**
	 * set  MRP_INFO_PK_A1.
	 * @param data MRP_INFO_PK_A1Array
	 */
	public void setMrpInfoPk_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mrpInfoPk_A1, data[j]);
		}
	}

	/**
	 * get  MRP_PLN_NM_A1.
	 * @param idx1 List Number
	 * @return MRP_PLN_NM_A1
	 */
	public String getMrpPlnNm_A1(int idx1) {
		return outputValue(A, idx1, mrpPlnNm_A1);
	}

	/**
	 * set  MRP_PLN_NM_A1.
	 * @param data MRP_PLN_NM_A1Array
	 */
	public void setMrpPlnNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mrpPlnNm_A1, data[j]);
		}
	}

	/**
	 * get  MDSE_CD_A1.
	 * @param idx1 List Number
	 * @return MDSE_CD_A1
	 */
	public String getMdseCd_A1(int idx1) {
		return outputValue(A, idx1, mdseCd_A1);
	}

	/**
	 * set  MDSE_CD_A1.
	 * @param data MDSE_CD_A1Array
	 */
	public void setMdseCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdseCd_A1, data[j]);
		}
	}

	/**
	 * get  MDSE_DESC_SHORT_TXT_A1.
	 * @param idx1 List Number
	 * @return MDSE_DESC_SHORT_TXT_A1
	 */
	public String getMdseDescShortTxt_A1(int idx1) {
		return outputValue(A, idx1, mdseDescShortTxt_A1);
	}

	/**
	 * set  MDSE_DESC_SHORT_TXT_A1.
	 * @param data MDSE_DESC_SHORT_TXT_A1Array
	 */
	public void setMdseDescShortTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdseDescShortTxt_A1, data[j]);
		}
	}

	/**
	 * get  RTL_WH_CATG_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return RTL_WH_CATG_DESC_TXT_A1
	 */
	public String getRtlWhCatgDescTxt_A1(int idx1) {
		return outputValue(A, idx1, rtlWhCatgDescTxt_A1);
	}

	/**
	 * set  RTL_WH_CATG_DESC_TXT_A1.
	 * @param data RTL_WH_CATG_DESC_TXT_A1Array
	 */
	public void setRtlWhCatgDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, rtlWhCatgDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  RTL_WH_CD_A1.
	 * @param idx1 List Number
	 * @return RTL_WH_CD_A1
	 */
	public String getRtlWhCd_A1(int idx1) {
		return outputValue(A, idx1, rtlWhCd_A1);
	}

	/**
	 * set  RTL_WH_CD_A1.
	 * @param data RTL_WH_CD_A1Array
	 */
	public void setRtlWhCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, rtlWhCd_A1, data[j]);
		}
	}

	/**
	 * get  RTL_WH_NM_A1.
	 * @param idx1 List Number
	 * @return RTL_WH_NM_A1
	 */
	public String getRtlWhNm_A1(int idx1) {
		return outputValue(A, idx1, rtlWhNm_A1);
	}

	/**
	 * set  RTL_WH_NM_A1.
	 * @param data RTL_WH_NM_A1Array
	 */
	public void setRtlWhNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, rtlWhNm_A1, data[j]);
		}
	}

	/**
	 * get  RTL_SWH_CD_A1.
	 * @param idx1 List Number
	 * @return RTL_SWH_CD_A1
	 */
	public String getRtlSwhCd_A1(int idx1) {
		return outputValue(A, idx1, rtlSwhCd_A1);
	}

	/**
	 * set  RTL_SWH_CD_A1.
	 * @param data RTL_SWH_CD_A1Array
	 */
	public void setRtlSwhCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, rtlSwhCd_A1, data[j]);
		}
	}

	/**
	 * get  RTL_SWH_NM_A1.
	 * @param idx1 List Number
	 * @return RTL_SWH_NM_A1
	 */
	public String getRtlSwhNm_A1(int idx1) {
		return outputValue(A, idx1, rtlSwhNm_A1);
	}

	/**
	 * set  RTL_SWH_NM_A1.
	 * @param data RTL_SWH_NM_A1Array
	 */
	public void setRtlSwhNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, rtlSwhNm_A1, data[j]);
		}
	}

	/**
	 * get  WH_MGR_PSN_CD_A1.
	 * @param idx1 List Number
	 * @return WH_MGR_PSN_CD_A1
	 */
	public String getWhMgrPsnCd_A1(int idx1) {
		return outputValue(A, idx1, whMgrPsnCd_A1);
	}

	/**
	 * set  WH_MGR_PSN_CD_A1.
	 * @param data WH_MGR_PSN_CD_A1Array
	 */
	public void setWhMgrPsnCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, whMgrPsnCd_A1, data[j]);
		}
	}

	/**
	 * get  FULL_PSN_NM_A1.
	 * @param idx1 List Number
	 * @return FULL_PSN_NM_A1
	 */
	public String getFullPsnNm_A1(int idx1) {
		return outputValue(A, idx1, fullPsnNm_A1);
	}

	/**
	 * set  FULL_PSN_NM_A1.
	 * @param data FULL_PSN_NM_A1Array
	 */
	public void setFullPsnNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fullPsnNm_A1, data[j]);
		}
	}

	/**
	 * get  CALC_ORD_PROC_CD_A1.
	 * @param idx1 List Number
	 * @return CALC_ORD_PROC_CD_A1
	 */
	public String getCalcOrdProcCd_A1(int idx1) {
		return outputValue(A, idx1, calcOrdProcCd_A1);
	}

	/**
	 * set  CALC_ORD_PROC_CD_A1.
	 * @param data CALC_ORD_PROC_CD_A1Array
	 */
	public void setCalcOrdProcCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, calcOrdProcCd_A1, data[j]);
		}
	}

	/**
	 * get  ROP_QTY_A1.
	 * @param idx1 List Number
	 * @return ROP_QTY_A1
	 */
	public String getRopQty_A1(int idx1) {
		return outputValue(A, idx1, ropQty_A1);
	}

	/**
	 * set  ROP_QTY_A1.
	 * @param data ROP_QTY_A1Array
	 */
	public void setRopQty_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ropQty_A1, data[j]);
		}
	}

	/**
	 * get  MAX_INVTY_QTY_A1.
	 * @param idx1 List Number
	 * @return MAX_INVTY_QTY_A1
	 */
	public String getMaxInvtyQty_A1(int idx1) {
		return outputValue(A, idx1, maxInvtyQty_A1);
	}

	/**
	 * set  MAX_INVTY_QTY_A1.
	 * @param data MAX_INVTY_QTY_A1Array
	 */
	public void setMaxInvtyQty_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, maxInvtyQty_A1, data[j]);
		}
	}

	/**
	 * get  MRP_ENBL_FLG_A1.
	 * @param idx1 List Number
	 * @return MRP_ENBL_FLG_A1
	 */
	public String getMrpEnblFlg_A1(int idx1) {
		return outputValue(A, idx1, mrpEnblFlg_A1);
	}

	/**
	 * set  MRP_ENBL_FLG_A1.
	 * @param data MRP_ENBL_FLG_A1Array
	 */
	public void setMrpEnblFlg_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mrpEnblFlg_A1, data[j]);
		}
	}

	/**
	 * get  PROCR_TP_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return PROCR_TP_DESC_TXT_A1
	 */
	public String getProcrTpDescTxt_A1(int idx1) {
		return outputValue(A, idx1, procrTpDescTxt_A1);
	}

	/**
	 * set  PROCR_TP_DESC_TXT_A1.
	 * @param data PROCR_TP_DESC_TXT_A1Array
	 */
	public void setProcrTpDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, procrTpDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  SRC_RTL_WH_CD_A1.
	 * @param idx1 List Number
	 * @return SRC_RTL_WH_CD_A1
	 */
	public String getSrcRtlWhCd_A1(int idx1) {
		return outputValue(A, idx1, srcRtlWhCd_A1);
	}

	/**
	 * set  SRC_RTL_WH_CD_A1.
	 * @param data SRC_RTL_WH_CD_A1Array
	 */
	public void setSrcRtlWhCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, srcRtlWhCd_A1, data[j]);
		}
	}

	/**
	 * get  RTL_WH_NM_A2.
	 * @param idx1 List Number
	 * @return RTL_WH_NM_A2
	 */
	public String getRtlWhNm_A2(int idx1) {
		return outputValue(A, idx1, rtlWhNm_A2);
	}

	/**
	 * set  RTL_WH_NM_A2.
	 * @param data RTL_WH_NM_A2Array
	 */
	public void setRtlWhNm_A2(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, rtlWhNm_A2, data[j]);
		}
	}

	/**
	 * get  SRC_RTL_SWH_CD_A1.
	 * @param idx1 List Number
	 * @return SRC_RTL_SWH_CD_A1
	 */
	public String getSrcRtlSwhCd_A1(int idx1) {
		return outputValue(A, idx1, srcRtlSwhCd_A1);
	}

	/**
	 * set  SRC_RTL_SWH_CD_A1.
	 * @param data SRC_RTL_SWH_CD_A1Array
	 */
	public void setSrcRtlSwhCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, srcRtlSwhCd_A1, data[j]);
		}
	}

	/**
	 * get  RTL_SWH_NM_A2.
	 * @param idx1 List Number
	 * @return RTL_SWH_NM_A2
	 */
	public String getRtlSwhNm_A2(int idx1) {
		return outputValue(A, idx1, rtlSwhNm_A2);
	}

	/**
	 * set  RTL_SWH_NM_A2.
	 * @param data RTL_SWH_NM_A2Array
	 */
	public void setRtlSwhNm_A2(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, rtlSwhNm_A2, data[j]);
		}
	}

	/**
	 * get  XX_POP_PRM_EV.
	 * @return XX_POP_PRM_EV
	 */
	public String getXxPopPrm_EV() {
		return outputValue(xxPopPrm_EV);
	}

	/**
	 * set  XX_POP_PRM_EV.
	 * @param data XX_POP_PRM_EV
	 */
	public void setXxPopPrm_EV(String data) {
		inputValue(xxPopPrm_EV,data);
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
	 * get  XX_TBL_NM_P1.
	 * @return XX_TBL_NM_P1
	 */
	public String getXxTblNm_P1() {
		return outputValue(xxTblNm_P1);
	}

	/**
	 * set  XX_TBL_NM_P1.
	 * @param data XX_TBL_NM_P1
	 */
	public void setXxTblNm_P1(String data) {
		inputValue(xxTblNm_P1,data);
	}

	/**
	 * get  XX_TBL_CD_COL_NM_P1.
	 * @return XX_TBL_CD_COL_NM_P1
	 */
	public String getXxTblCdColNm_P1() {
		return outputValue(xxTblCdColNm_P1);
	}

	/**
	 * set  XX_TBL_CD_COL_NM_P1.
	 * @param data XX_TBL_CD_COL_NM_P1
	 */
	public void setXxTblCdColNm_P1(String data) {
		inputValue(xxTblCdColNm_P1,data);
	}

	/**
	 * get  XX_TBL_NM_COL_NM_P1.
	 * @return XX_TBL_NM_COL_NM_P1
	 */
	public String getXxTblNmColNm_P1() {
		return outputValue(xxTblNmColNm_P1);
	}

	/**
	 * set  XX_TBL_NM_COL_NM_P1.
	 * @param data XX_TBL_NM_COL_NM_P1
	 */
	public void setXxTblNmColNm_P1(String data) {
		inputValue(xxTblNmColNm_P1,data);
	}

	/**
	 * get  XX_TBL_SORT_COL_NM_P1.
	 * @return XX_TBL_SORT_COL_NM_P1
	 */
	public String getXxTblSortColNm_P1() {
		return outputValue(xxTblSortColNm_P1);
	}

	/**
	 * set  XX_TBL_SORT_COL_NM_P1.
	 * @param data XX_TBL_SORT_COL_NM_P1
	 */
	public void setXxTblSortColNm_P1(String data) {
		inputValue(xxTblSortColNm_P1,data);
	}

	/**
	 * get  XX_SCR_NM_P1.
	 * @return XX_SCR_NM_P1
	 */
	public String getXxScrNm_P1() {
		return outputValue(xxScrNm_P1);
	}

	/**
	 * set  XX_SCR_NM_P1.
	 * @param data XX_SCR_NM_P1
	 */
	public void setXxScrNm_P1(String data) {
		inputValue(xxScrNm_P1,data);
	}

	/**
	 * get  XX_HDR_CD_LB_NM_P1.
	 * @return XX_HDR_CD_LB_NM_P1
	 */
	public String getXxHdrCdLbNm_P1() {
		return outputValue(xxHdrCdLbNm_P1);
	}

	/**
	 * set  XX_HDR_CD_LB_NM_P1.
	 * @param data XX_HDR_CD_LB_NM_P1
	 */
	public void setXxHdrCdLbNm_P1(String data) {
		inputValue(xxHdrCdLbNm_P1,data);
	}

	/**
	 * get  XX_HDR_NM_LB_NM_P1.
	 * @return XX_HDR_NM_LB_NM_P1
	 */
	public String getXxHdrNmLbNm_P1() {
		return outputValue(xxHdrNmLbNm_P1);
	}

	/**
	 * set  XX_HDR_NM_LB_NM_P1.
	 * @param data XX_HDR_NM_LB_NM_P1
	 */
	public void setXxHdrNmLbNm_P1(String data) {
		inputValue(xxHdrNmLbNm_P1,data);
	}

	/**
	 * get  XX_DTL_CD_LB_NM_P1.
	 * @return XX_DTL_CD_LB_NM_P1
	 */
	public String getXxDtlCdLbNm_P1() {
		return outputValue(xxDtlCdLbNm_P1);
	}

	/**
	 * set  XX_DTL_CD_LB_NM_P1.
	 * @param data XX_DTL_CD_LB_NM_P1
	 */
	public void setXxDtlCdLbNm_P1(String data) {
		inputValue(xxDtlCdLbNm_P1,data);
	}

	/**
	 * get  XX_DTL_NM_LB_NM_P1.
	 * @return XX_DTL_NM_LB_NM_P1
	 */
	public String getXxDtlNmLbNm_P1() {
		return outputValue(xxDtlNmLbNm_P1);
	}

	/**
	 * set  XX_DTL_NM_LB_NM_P1.
	 * @param data XX_DTL_NM_LB_NM_P1
	 */
	public void setXxDtlNmLbNm_P1(String data) {
		inputValue(xxDtlNmLbNm_P1,data);
	}

	/**
	 * get  XX_COND_CD_P1.
	 * @return XX_COND_CD_P1
	 */
	public String getXxCondCd_P1() {
		return outputValue(xxCondCd_P1);
	}

	/**
	 * set  XX_COND_CD_P1.
	 * @param data XX_COND_CD_P1
	 */
	public void setXxCondCd_P1(String data) {
		inputValue(xxCondCd_P1,data);
	}

	/**
	 * get  XX_COND_NM_P1.
	 * @return XX_COND_NM_P1
	 */
	public String getXxCondNm_P1() {
		return outputValue(xxCondNm_P1);
	}

	/**
	 * set  XX_COND_NM_P1.
	 * @param data XX_COND_NM_P1
	 */
	public void setXxCondNm_P1(String data) {
		inputValue(xxCondNm_P1,data);
	}

	/**
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
	}

	/**
	 * get  XX_POP_PRM_E0.
	 * @return XX_POP_PRM_E0
	 */
	public String getXxPopPrm_E0() {
		return outputValue(xxPopPrm_E0);
	}

	/**
	 * set  XX_POP_PRM_E0.
	 * @param data XX_POP_PRM_E0
	 */
	public void setXxPopPrm_E0(String data) {
		inputValue(xxPopPrm_E0,data);
	}

	/**
	 * get  XX_POP_PRM_E1.
	 * @return XX_POP_PRM_E1
	 */
	public String getXxPopPrm_E1() {
		return outputValue(xxPopPrm_E1);
	}

	/**
	 * set  XX_POP_PRM_E1.
	 * @param data XX_POP_PRM_E1
	 */
	public void setXxPopPrm_E1(String data) {
		inputValue(xxPopPrm_E1,data);
	}

	/**
	 * get  XX_POP_PRM_E2.
	 * @return XX_POP_PRM_E2
	 */
	public String getXxPopPrm_E2() {
		return outputValue(xxPopPrm_E2);
	}

	/**
	 * set  XX_POP_PRM_E2.
	 * @param data XX_POP_PRM_E2
	 */
	public void setXxPopPrm_E2(String data) {
		inputValue(xxPopPrm_E2,data);
	}

	/**
	 * get  XX_POP_PRM_E3.
	 * @return XX_POP_PRM_E3
	 */
	public String getXxPopPrm_E3() {
		return outputValue(xxPopPrm_E3);
	}

	/**
	 * set  XX_POP_PRM_E3.
	 * @param data XX_POP_PRM_E3
	 */
	public void setXxPopPrm_E3(String data) {
		inputValue(xxPopPrm_E3,data);
	}

	/**
	 * get  XX_POP_PRM_M0.
	 * @return XX_POP_PRM_M0
	 */
	public String getXxPopPrm_M0() {
		return outputValue(xxPopPrm_M0);
	}

	/**
	 * set  XX_POP_PRM_M0.
	 * @param data XX_POP_PRM_M0
	 */
	public void setXxPopPrm_M0(String data) {
		inputValue(xxPopPrm_M0,data);
	}

	/**
	 * get  XX_POP_PRM_M1.
	 * @return XX_POP_PRM_M1
	 */
	public String getXxPopPrm_M1() {
		return outputValue(xxPopPrm_M1);
	}

	/**
	 * set  XX_POP_PRM_M1.
	 * @param data XX_POP_PRM_M1
	 */
	public void setXxPopPrm_M1(String data) {
		inputValue(xxPopPrm_M1,data);
	}

	/**
	 * get  XX_POP_PRM_M2.
	 * @return XX_POP_PRM_M2
	 */
	public String getXxPopPrm_M2() {
		return outputValue(xxPopPrm_M2);
	}

	/**
	 * set  XX_POP_PRM_M2.
	 * @param data XX_POP_PRM_M2
	 */
	public void setXxPopPrm_M2(String data) {
		inputValue(xxPopPrm_M2,data);
	}

	/**
	 * get  XX_POP_PRM_M3.
	 * @return XX_POP_PRM_M3
	 */
	public String getXxPopPrm_M3() {
		return outputValue(xxPopPrm_M3);
	}

	/**
	 * set  XX_POP_PRM_M3.
	 * @param data XX_POP_PRM_M3
	 */
	public void setXxPopPrm_M3(String data) {
		inputValue(xxPopPrm_M3,data);
	}

	/**
	 * get  XX_POP_PRM_M4.
	 * @return XX_POP_PRM_M4
	 */
	public String getXxPopPrm_M4() {
		return outputValue(xxPopPrm_M4);
	}

	/**
	 * set  XX_POP_PRM_M4.
	 * @param data XX_POP_PRM_M4
	 */
	public void setXxPopPrm_M4(String data) {
		inputValue(xxPopPrm_M4,data);
	}

	/**
	 * get  XX_POP_PRM_M5.
	 * @return XX_POP_PRM_M5
	 */
	public String getXxPopPrm_M5() {
		return outputValue(xxPopPrm_M5);
	}

	/**
	 * set  XX_POP_PRM_M5.
	 * @param data XX_POP_PRM_M5
	 */
	public void setXxPopPrm_M5(String data) {
		inputValue(xxPopPrm_M5,data);
	}

	/**
	 * get  XX_POP_PRM_M6.
	 * @return XX_POP_PRM_M6
	 */
	public String getXxPopPrm_M6() {
		return outputValue(xxPopPrm_M6);
	}

	/**
	 * set  XX_POP_PRM_M6.
	 * @param data XX_POP_PRM_M6
	 */
	public void setXxPopPrm_M6(String data) {
		inputValue(xxPopPrm_M6,data);
	}

	/**
	 * get  XX_POP_PRM_M7.
	 * @return XX_POP_PRM_M7
	 */
	public String getXxPopPrm_M7() {
		return outputValue(xxPopPrm_M7);
	}

	/**
	 * set  XX_POP_PRM_M7.
	 * @param data XX_POP_PRM_M7
	 */
	public void setXxPopPrm_M7(String data) {
		inputValue(xxPopPrm_M7,data);
	}

	/**
	 * get  XX_POP_PRM_M8.
	 * @return XX_POP_PRM_M8
	 */
	public String getXxPopPrm_M8() {
		return outputValue(xxPopPrm_M8);
	}

	/**
	 * set  XX_POP_PRM_M8.
	 * @param data XX_POP_PRM_M8
	 */
	public void setXxPopPrm_M8(String data) {
		inputValue(xxPopPrm_M8,data);
	}

	/**
	 * get  XX_POP_PRM_M9.
	 * @return XX_POP_PRM_M9
	 */
	public String getXxPopPrm_M9() {
		return outputValue(xxPopPrm_M9);
	}

	/**
	 * set  XX_POP_PRM_M9.
	 * @param data XX_POP_PRM_M9
	 */
	public void setXxPopPrm_M9(String data) {
		inputValue(xxPopPrm_M9,data);
	}

}

