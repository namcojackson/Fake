// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20180320170040000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1350Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NPAL1350;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NPAL1350 is data bean class.
 */
public class NPAL1350Bean extends EZDCommonDataBean {

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

	/** WRK_ORD_NUM*/
	public static final String wrkOrdNum = "wrkOrdNum";

	/** DS_WRK_ORD_TP_CD_PD*/
	public static final String dsWrkOrdTpCd_PD = "dsWrkOrdTpCd_PD";

	/** DS_WRK_ORD_TP_DESC_TXT_PD*/
	public static final String dsWrkOrdTpDescTxt_PD = "dsWrkOrdTpDescTxt_PD";

	/** DS_WRK_ORD_TP_CD_PL*/
	public static final String dsWrkOrdTpCd_PL = "dsWrkOrdTpCd_PL";

	/** DS_WRK_ORD_STS_NM_PC*/
	public static final String dsWrkOrdStsNm_PC = "dsWrkOrdStsNm_PC";

	/** DS_WRK_ORD_STS_NM_PD*/
	public static final String dsWrkOrdStsNm_PD = "dsWrkOrdStsNm_PD";

	/** DS_WRK_ORD_STS_NM_PL*/
	public static final String dsWrkOrdStsNm_PL = "dsWrkOrdStsNm_PL";

	/** WRK_ORD_DT_FM*/
	public static final String wrkOrdDt_FM = "wrkOrdDt_FM";

	/** WRK_ORD_DT_TO*/
	public static final String wrkOrdDt_TO = "wrkOrdDt_TO";

	/** PRNT_MDSE_CD*/
	public static final String prntMdseCd = "prntMdseCd";

	/** MDSE_DESC_SHORT_TXT*/
	public static final String mdseDescShortTxt = "mdseDescShortTxt";

	/** RTL_WH_CD*/
	public static final String rtlWhCd = "rtlWhCd";

	/** RTL_WH_NM*/
	public static final String rtlWhNm = "rtlWhNm";

	/** RTL_SWH_CD*/
	public static final String rtlSwhCd = "rtlSwhCd";

	/** RTL_SWH_NM*/
	public static final String rtlSwhNm = "rtlSwhNm";

	/** RQST_RCV_DT_FM*/
	public static final String rqstRcvDt_FM = "rqstRcvDt_FM";

	/** RQST_RCV_DT_TO*/
	public static final String rqstRcvDt_TO = "rqstRcvDt_TO";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX_A1*/
	public static final String xxChkBox_A1 = "xxChkBox_A1";

	/** WRK_ORD_NUM_A1*/
	public static final String wrkOrdNum_A1 = "wrkOrdNum_A1";

	/** WRK_ORD_STS_CD_A1*/
	public static final String wrkOrdStsCd_A1 = "wrkOrdStsCd_A1";

	/** DS_WRK_ORD_TP_DESC_TXT_A1*/
	public static final String dsWrkOrdTpDescTxt_A1 = "dsWrkOrdTpDescTxt_A1";

	/** DS_WRK_ORD_STS_NM_A1*/
	public static final String dsWrkOrdStsNm_A1 = "dsWrkOrdStsNm_A1";

	/** WRK_ORD_DT_A1*/
	public static final String wrkOrdDt_A1 = "wrkOrdDt_A1";

	/** RQST_RCV_DT_A1*/
	public static final String rqstRcvDt_A1 = "rqstRcvDt_A1";

	/** FNSH_GOODS_MDSE_CD_A1*/
	public static final String fnshGoodsMdseCd_A1 = "fnshGoodsMdseCd_A1";

	/** FNSH_MDSE_DESC_SHORT_TXT_A1*/
	public static final String fnshMdseDescShortTxt_A1 = "fnshMdseDescShortTxt_A1";

	/** RTL_WH_CD_A1*/
	public static final String rtlWhCd_A1 = "rtlWhCd_A1";

	/** RTL_WH_NM_A1*/
	public static final String rtlWhNm_A1 = "rtlWhNm_A1";

	/** CPLT_RTL_SWH_CD_A1*/
	public static final String cpltRtlSwhCd_A1 = "cpltRtlSwhCd_A1";

	/** FNSH_GOODS_ORD_QTY_A1*/
	public static final String fnshGoodsOrdQty_A1 = "fnshGoodsOrdQty_A1";

	/** FNSH_GOODS_RCV_QTY_A1*/
	public static final String fnshGoodsRcvQty_A1 = "fnshGoodsRcvQty_A1";

	/** FNSH_GOODS_BAL_QTY_A1*/
	public static final String fnshGoodsBalQty_A1 = "fnshGoodsBalQty_A1";

	/** FNSH_GOODS_CANC_QTY_A1*/
	public static final String fnshGoodsCancQty_A1 = "fnshGoodsCancQty_A1";

	/** OLD_WRK_ORD_NUM_A1*/
	public static final String oldWrkOrdNum_A1 = "oldWrkOrdNum_A1";

	/** RTL_SWH_NM_A1*/
	public static final String rtlSwhNm_A1 = "rtlSwhNm_A1";

	/** XX_RQST_TS_A1*/
	public static final String xxRqstTs_A1 = "xxRqstTs_A1";

	/** XX_RQST_TZ_A1*/
	public static final String xxRqstTz_A1 = "xxRqstTz_A1";

	/** XX_COMN_COL_ORD_TXT*/
	public static final String xxComnColOrdTxt = "xxComnColOrdTxt";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** R*/
	public static final String R = "R";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_SEL_FLG*/
	public static final String xxSelFlg = "xxSelFlg";

	/** P*/
	public static final String P = "P";

	/** XX_POP_PRM*/
	public static final String xxPopPrm = "xxPopPrm";

	/**
	 * Method name:NPAL1350 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NPAL1350Bean is generated
	 * <dd>Remarks:
	 */
	public NPAL1350Bean() {
		super("business.servlet.NPAL1350.NPAL1350BMsg");
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
	 * get  WRK_ORD_NUM.
	 * @return WRK_ORD_NUM
	 */
	public String getWrkOrdNum() {
		return outputValue(wrkOrdNum);
	}

	/**
	 * set  WRK_ORD_NUM.
	 * @param data WRK_ORD_NUM
	 */
	public void setWrkOrdNum(String data) {
		inputValue(wrkOrdNum,data);
	}

	/**
	 * get  DS_WRK_ORD_TP_CD_PD.
	 * @param idx1 Index Number
	 * @return DS_WRK_ORD_TP_CD_PD
	 */
	public String getDsWrkOrdTpCd_PD(int idx1) {
	 	 return outputValue(dsWrkOrdTpCd_PD, idx1);
	}

	/**
	 * get  DS_WRK_ORD_TP_DESC_TXT_PD.
	 * @param idx1 Index Number
	 * @return DS_WRK_ORD_TP_DESC_TXT_PD
	 */
	public String getDsWrkOrdTpDescTxt_PD(int idx1) {
	 	 return outputValue(dsWrkOrdTpDescTxt_PD, idx1);
	}

	/**
	 * get  DS_WRK_ORD_TP_CD_PL.
	 * @return DS_WRK_ORD_TP_CD_PL
	 */
	public String getDsWrkOrdTpCd_PL() {
		return outputValue(dsWrkOrdTpCd_PL);
	}

	/**
	 * set  DS_WRK_ORD_TP_CD_PL.
	 * @param data DS_WRK_ORD_TP_CD_PL
	 */
	public void setDsWrkOrdTpCd_PL(String data) {
		inputValue(dsWrkOrdTpCd_PL,data);
	}

	/**
	 * get  DS_WRK_ORD_STS_NM_PC.
	 * @param idx1 Index Number
	 * @return DS_WRK_ORD_STS_NM_PC
	 */
	public String getDsWrkOrdStsNm_PC(int idx1) {
	 	 return outputValue(dsWrkOrdStsNm_PC, idx1);
	}

	/**
	 * get  DS_WRK_ORD_STS_NM_PD.
	 * @param idx1 Index Number
	 * @return DS_WRK_ORD_STS_NM_PD
	 */
	public String getDsWrkOrdStsNm_PD(int idx1) {
	 	 return outputValue(dsWrkOrdStsNm_PD, idx1);
	}

	/**
	 * get  DS_WRK_ORD_STS_NM_PL.
	 * @return DS_WRK_ORD_STS_NM_PL
	 */
	public String getDsWrkOrdStsNm_PL() {
		return outputValue(dsWrkOrdStsNm_PL);
	}

	/**
	 * set  DS_WRK_ORD_STS_NM_PL.
	 * @param data DS_WRK_ORD_STS_NM_PL
	 */
	public void setDsWrkOrdStsNm_PL(String data) {
		inputValue(dsWrkOrdStsNm_PL,data);
	}

	/**
	 * get  WRK_ORD_DT_FM.
	 * @return WRK_ORD_DT_FM
	 */
	public String getWrkOrdDt_FM() {
		return outputValue(wrkOrdDt_FM, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  WRK_ORD_DT_FM.
	 * @param data WRK_ORD_DT_FM
	 */
	public void setWrkOrdDt_FM(String data) {
		inputValue(wrkOrdDt_FM, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  WRK_ORD_DT_TO.
	 * @return WRK_ORD_DT_TO
	 */
	public String getWrkOrdDt_TO() {
		return outputValue(wrkOrdDt_TO, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  WRK_ORD_DT_TO.
	 * @param data WRK_ORD_DT_TO
	 */
	public void setWrkOrdDt_TO(String data) {
		inputValue(wrkOrdDt_TO, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  PRNT_MDSE_CD.
	 * @return PRNT_MDSE_CD
	 */
	public String getPrntMdseCd() {
		return outputValue(prntMdseCd);
	}

	/**
	 * set  PRNT_MDSE_CD.
	 * @param data PRNT_MDSE_CD
	 */
	public void setPrntMdseCd(String data) {
		inputValue(prntMdseCd,data);
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
	 * get  RTL_WH_NM.
	 * @return RTL_WH_NM
	 */
	public String getRtlWhNm() {
		return outputValue(rtlWhNm);
	}

	/**
	 * set  RTL_WH_NM.
	 * @param data RTL_WH_NM
	 */
	public void setRtlWhNm(String data) {
		inputValue(rtlWhNm,data);
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
	 * get  RTL_SWH_NM.
	 * @return RTL_SWH_NM
	 */
	public String getRtlSwhNm() {
		return outputValue(rtlSwhNm);
	}

	/**
	 * set  RTL_SWH_NM.
	 * @param data RTL_SWH_NM
	 */
	public void setRtlSwhNm(String data) {
		inputValue(rtlSwhNm,data);
	}

	/**
	 * get  RQST_RCV_DT_FM.
	 * @return RQST_RCV_DT_FM
	 */
	public String getRqstRcvDt_FM() {
		return outputValue(rqstRcvDt_FM, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  RQST_RCV_DT_FM.
	 * @param data RQST_RCV_DT_FM
	 */
	public void setRqstRcvDt_FM(String data) {
		inputValue(rqstRcvDt_FM, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  RQST_RCV_DT_TO.
	 * @return RQST_RCV_DT_TO
	 */
	public String getRqstRcvDt_TO() {
		return outputValue(rqstRcvDt_TO, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  RQST_RCV_DT_TO.
	 * @param data RQST_RCV_DT_TO
	 */
	public void setRqstRcvDt_TO(String data) {
		inputValue(rqstRcvDt_TO, data, VALUE_YEAR_MONTH_DAY);
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
	 * get  WRK_ORD_NUM_A1.
	 * @param idx1 List Number
	 * @return WRK_ORD_NUM_A1
	 */
	public String getWrkOrdNum_A1(int idx1) {
		return outputValue(A, idx1, wrkOrdNum_A1);
	}

	/**
	 * set  WRK_ORD_NUM_A1.
	 * @param data WRK_ORD_NUM_A1Array
	 */
	public void setWrkOrdNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, wrkOrdNum_A1, data[j]);
		}
	}

	/**
	 * get  WRK_ORD_STS_CD_A1.
	 * @param idx1 List Number
	 * @return WRK_ORD_STS_CD_A1
	 */
	public String getWrkOrdStsCd_A1(int idx1) {
		return outputValue(A, idx1, wrkOrdStsCd_A1);
	}

	/**
	 * set  WRK_ORD_STS_CD_A1.
	 * @param data WRK_ORD_STS_CD_A1Array
	 */
	public void setWrkOrdStsCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, wrkOrdStsCd_A1, data[j]);
		}
	}

	/**
	 * get  DS_WRK_ORD_TP_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return DS_WRK_ORD_TP_DESC_TXT_A1
	 */
	public String getDsWrkOrdTpDescTxt_A1(int idx1) {
		return outputValue(A, idx1, dsWrkOrdTpDescTxt_A1);
	}

	/**
	 * set  DS_WRK_ORD_TP_DESC_TXT_A1.
	 * @param data DS_WRK_ORD_TP_DESC_TXT_A1Array
	 */
	public void setDsWrkOrdTpDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsWrkOrdTpDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  DS_WRK_ORD_STS_NM_A1.
	 * @param idx1 List Number
	 * @return DS_WRK_ORD_STS_NM_A1
	 */
	public String getDsWrkOrdStsNm_A1(int idx1) {
		return outputValue(A, idx1, dsWrkOrdStsNm_A1);
	}

	/**
	 * set  DS_WRK_ORD_STS_NM_A1.
	 * @param data DS_WRK_ORD_STS_NM_A1Array
	 */
	public void setDsWrkOrdStsNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsWrkOrdStsNm_A1, data[j]);
		}
	}

	/**
	 * get  WRK_ORD_DT_A1.
	 * @param idx1 List Number
	 * @return WRK_ORD_DT_A1
	 */
	public String getWrkOrdDt_A1(int idx1) {
		return outputValue(A, idx1, wrkOrdDt_A1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  WRK_ORD_DT_A1.
	 * @param data WRK_ORD_DT_A1Array
	 */
	public void setWrkOrdDt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  wrkOrdDt_A1, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  RQST_RCV_DT_A1.
	 * @param idx1 List Number
	 * @return RQST_RCV_DT_A1
	 */
	public String getRqstRcvDt_A1(int idx1) {
		return outputValue(A, idx1, rqstRcvDt_A1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  RQST_RCV_DT_A1.
	 * @param data RQST_RCV_DT_A1Array
	 */
	public void setRqstRcvDt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  rqstRcvDt_A1, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  FNSH_GOODS_MDSE_CD_A1.
	 * @param idx1 List Number
	 * @return FNSH_GOODS_MDSE_CD_A1
	 */
	public String getFnshGoodsMdseCd_A1(int idx1) {
		return outputValue(A, idx1, fnshGoodsMdseCd_A1);
	}

	/**
	 * set  FNSH_GOODS_MDSE_CD_A1.
	 * @param data FNSH_GOODS_MDSE_CD_A1Array
	 */
	public void setFnshGoodsMdseCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fnshGoodsMdseCd_A1, data[j]);
		}
	}

	/**
	 * get  FNSH_MDSE_DESC_SHORT_TXT_A1.
	 * @param idx1 List Number
	 * @return FNSH_MDSE_DESC_SHORT_TXT_A1
	 */
	public String getFnshMdseDescShortTxt_A1(int idx1) {
		return outputValue(A, idx1, fnshMdseDescShortTxt_A1);
	}

	/**
	 * set  FNSH_MDSE_DESC_SHORT_TXT_A1.
	 * @param data FNSH_MDSE_DESC_SHORT_TXT_A1Array
	 */
	public void setFnshMdseDescShortTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fnshMdseDescShortTxt_A1, data[j]);
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
	 * get  CPLT_RTL_SWH_CD_A1.
	 * @param idx1 List Number
	 * @return CPLT_RTL_SWH_CD_A1
	 */
	public String getCpltRtlSwhCd_A1(int idx1) {
		return outputValue(A, idx1, cpltRtlSwhCd_A1);
	}

	/**
	 * set  CPLT_RTL_SWH_CD_A1.
	 * @param data CPLT_RTL_SWH_CD_A1Array
	 */
	public void setCpltRtlSwhCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cpltRtlSwhCd_A1, data[j]);
		}
	}

	/**
	 * get  FNSH_GOODS_ORD_QTY_A1.
	 * @param idx1 List Number
	 * @return FNSH_GOODS_ORD_QTY_A1
	 */
	public String getFnshGoodsOrdQty_A1(int idx1) {
		return outputValue(A, idx1, fnshGoodsOrdQty_A1);
	}

	/**
	 * set  FNSH_GOODS_ORD_QTY_A1.
	 * @param data FNSH_GOODS_ORD_QTY_A1Array
	 */
	public void setFnshGoodsOrdQty_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fnshGoodsOrdQty_A1, data[j]);
		}
	}

	/**
	 * get  FNSH_GOODS_RCV_QTY_A1.
	 * @param idx1 List Number
	 * @return FNSH_GOODS_RCV_QTY_A1
	 */
	public String getFnshGoodsRcvQty_A1(int idx1) {
		return outputValue(A, idx1, fnshGoodsRcvQty_A1);
	}

	/**
	 * set  FNSH_GOODS_RCV_QTY_A1.
	 * @param data FNSH_GOODS_RCV_QTY_A1Array
	 */
	public void setFnshGoodsRcvQty_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fnshGoodsRcvQty_A1, data[j]);
		}
	}

	/**
	 * get  FNSH_GOODS_BAL_QTY_A1.
	 * @param idx1 List Number
	 * @return FNSH_GOODS_BAL_QTY_A1
	 */
	public String getFnshGoodsBalQty_A1(int idx1) {
		return outputValue(A, idx1, fnshGoodsBalQty_A1);
	}

	/**
	 * set  FNSH_GOODS_BAL_QTY_A1.
	 * @param data FNSH_GOODS_BAL_QTY_A1Array
	 */
	public void setFnshGoodsBalQty_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fnshGoodsBalQty_A1, data[j]);
		}
	}

	/**
	 * get  FNSH_GOODS_CANC_QTY_A1.
	 * @param idx1 List Number
	 * @return FNSH_GOODS_CANC_QTY_A1
	 */
	public String getFnshGoodsCancQty_A1(int idx1) {
		return outputValue(A, idx1, fnshGoodsCancQty_A1);
	}

	/**
	 * set  FNSH_GOODS_CANC_QTY_A1.
	 * @param data FNSH_GOODS_CANC_QTY_A1Array
	 */
	public void setFnshGoodsCancQty_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fnshGoodsCancQty_A1, data[j]);
		}
	}

	/**
	 * get  OLD_WRK_ORD_NUM_A1.
	 * @param idx1 List Number
	 * @return OLD_WRK_ORD_NUM_A1
	 */
	public String getOldWrkOrdNum_A1(int idx1) {
		return outputValue(A, idx1, oldWrkOrdNum_A1);
	}

	/**
	 * set  OLD_WRK_ORD_NUM_A1.
	 * @param data OLD_WRK_ORD_NUM_A1Array
	 */
	public void setOldWrkOrdNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, oldWrkOrdNum_A1, data[j]);
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
	 * get  XX_RQST_TS_A1.
	 * @param idx1 List Number
	 * @return XX_RQST_TS_A1
	 */
	public String getXxRqstTs_A1(int idx1) {
		return outputValue(A, idx1, xxRqstTs_A1);
	}

	/**
	 * set  XX_RQST_TS_A1.
	 * @param data XX_RQST_TS_A1Array
	 */
	public void setXxRqstTs_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRqstTs_A1, data[j]);
		}
	}

	/**
	 * get  XX_RQST_TZ_A1.
	 * @param idx1 List Number
	 * @return XX_RQST_TZ_A1
	 */
	public String getXxRqstTz_A1(int idx1) {
		return outputValue(A, idx1, xxRqstTz_A1);
	}

	/**
	 * set  XX_RQST_TZ_A1.
	 * @param data XX_RQST_TZ_A1Array
	 */
	public void setXxRqstTz_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRqstTz_A1, data[j]);
		}
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
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
	}

	/**
	 * get  XX_COMN_SCR_QUERY_COL_NM.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_QUERY_COL_NM
	 */
	public String getXxComnScrQueryColNm(int idx1) {
		return outputValue(R, idx1, xxComnScrQueryColNm);
	}

	/**
	 * set  XX_COMN_SCR_QUERY_COL_NM.
	 * @param data XX_COMN_SCR_QUERY_COL_NMArray
	 */
	public void setXxComnScrQueryColNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(R);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(R, i, xxComnScrQueryColNm, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt(int idx1) {
		return outputValue(R, idx1, xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXTArray
	 */
	public void setXxComnScrColValTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(R);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(R, i, xxComnScrColValTxt, data[j]);
		}
	}

	/**
	 * get  XX_SEL_FLG.
	 * @param idx1 List Number
	 * @return XX_SEL_FLG
	 */
	public String getXxSelFlg(int idx1) {
		return outputValue(R, idx1, xxSelFlg);
	}

	/**
	 * set  XX_SEL_FLG.
	 * @param data XX_SEL_FLGArray
	 */
	public void setXxSelFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(R);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(R, i, xxSelFlg, data[j]);
		}
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

}

