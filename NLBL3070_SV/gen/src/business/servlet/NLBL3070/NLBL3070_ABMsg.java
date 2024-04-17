//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20210524110800000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3070_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL3070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3070 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3070_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** TRX_HDR_NUM_A1*/
	public final EZDBStringItem              trxHdrNum_A1;

    /** SVC_CONFIG_MSTR_PK_A1*/
	public final EZDBBigDecimalItem              svcConfigMstrPk_A1;

    /** BACK_ORD_IMPCT_TP_DESC_TXT_A1*/
	public final EZDBStringItem              backOrdImpctTpDescTxt_A1;

    /** SO_NUM_A1*/
	public final EZDBStringItem              soNum_A1;

    /** T_MDL_NM_A1*/
	public final EZDBStringItem              t_MdlNm_A1;

    /** RDD_DT_A1*/
	public final EZDBDateItem              rddDt_A1;

    /** SCHD_CARR_PICK_UP_DT_A1*/
	public final EZDBDateItem              schdCarrPickUpDt_A1;

    /** SCHD_DELY_DT_A1*/
	public final EZDBDateItem              schdDelyDt_A1;

    /** NEXT_ACT_DT_A1*/
	public final EZDBDateItem              nextActDt_A1;

    /** TECH_MEET_TRUCK_FLG_A1*/
	public final EZDBStringItem              techMeetTruckFlg_A1;

    /** SCHD_DELY_TS_DPLY_TXT_A2*/
	public final EZDBStringItem              schdDelyTsDplyTxt_A2;

    /** RQST_RCV_DT_TXT_S2*/
	public final EZDBStringItem              rqstRcvDtTxt_S2;

    /** CARR_CD_A1*/
	public final EZDBStringItem              carrCd_A1;

    /** CARR_NM_A1*/
	public final EZDBStringItem              carrNm_A1;

    /** SCHD_ISTL_DT_A1*/
	public final EZDBDateItem              schdIstlDt_A1;

    /** SCHD_DELY_TS_DPLY_TXT_A1*/
	public final EZDBStringItem              schdDelyTsDplyTxt_A1;

    /** RQST_RCV_DT_TXT_A1*/
	public final EZDBStringItem              rqstRcvDtTxt_A1;

    /** SCHD_DURN_TM_NUM_A1*/
	public final EZDBBigDecimalItem              schdDurnTmNum_A1;

    /** FSR_NUM_A1*/
	public final EZDBStringItem              fsrNum_A1;

    /** SVC_TASK_SCHD_DT_A1*/
	public final EZDBDateItem              svcTaskSchdDt_A1;

    /** TECH_NM_A1*/
	public final EZDBStringItem              techNm_A1;

    /** SCHD_COORD_STS_CD_A1*/
	public final EZDBStringItem              schdCoordStsCd_A1;

    /** SCHD_COORD_STS_CD_AL*/
	public final EZDBStringItemArray              schdCoordStsCd_AL;

    /** SCHD_COORD_STS_DESC_TXT_AL*/
	public final EZDBStringItemArray              schdCoordStsDescTxt_AL;

    /** SCHD_COORD_STS_DESC_TXT_A1*/
	public final EZDBStringItem              schdCoordStsDescTxt_A1;

    /** MAN_UPD_PRTY_NUM_A1*/
	public final EZDBBigDecimalItem              manUpdPrtyNum_A1;

    /** XX_PSN_FIRST_MID_LAST_NM_A2*/
	public final EZDBStringItem              xxPsnFirstMidLastNm_A2;

    /** TOC_NM_A1*/
	public final EZDBStringItem              tocNm_A1;

    /** CTAC_PSN_TEL_NUM_A1*/
	public final EZDBStringItem              ctacPsnTelNum_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDBStringItem              dsAcctNm_A1;

    /** FIRST_LINE_ADDR_A1*/
	public final EZDBStringItem              firstLineAddr_A1;

    /** SCD_LINE_ADDR_A1*/
	public final EZDBStringItem              scdLineAddr_A1;

    /** CTY_ADDR_A1*/
	public final EZDBStringItem              ctyAddr_A1;

    /** ST_CD_A1*/
	public final EZDBStringItem              stCd_A1;

    /** POST_CD_A1*/
	public final EZDBStringItem              postCd_A1;

    /** CTRY_CD_A1*/
	public final EZDBStringItem              ctryCd_A1;

    /** RTL_WH_NM_A1*/
	public final EZDBStringItem              rtlWhNm_A1;

    /** WMS_DROP_FLG_A1*/
	public final EZDBStringItem              wmsDropFlg_A1;

    /** WMS_DROP_RQST_FLG_A1*/
	public final EZDBStringItem              wmsDropRqstFlg_A1;

    /** SEND_RQST_FLG_A1*/
	public final EZDBStringItem              sendRqstFlg_A1;

    /** SHPG_SVC_LVL_CD_A1*/
	public final EZDBStringItem              shpgSvcLvlCd_A1;

    /** CARR_ACCT_NUM_A1*/
	public final EZDBStringItem              carrAcctNum_A1;

    /** XX_PSN_FIRST_MID_LAST_NM_A1*/
	public final EZDBStringItem              xxPsnFirstMidLastNm_A1;

    /** TEMP_SCHD_RSN_CD_A1*/
	public final EZDBStringItem              tempSchdRsnCd_A1;

    /** TEMP_SCHD_CMNT_TXT_A1*/
	public final EZDBStringItem              tempSchdCmntTxt_A1;

    /** PDD_DT_A1*/
	public final EZDBDateItem              pddDt_A1;

    /** DS_ORD_CATG_DESC_TXT_A1*/
	public final EZDBStringItem              dsOrdCatgDescTxt_A1;

    /** DS_ORD_TP_DESC_TXT_A1*/
	public final EZDBStringItem              dsOrdTpDescTxt_A1;

    /** OPEN_FLG_A1*/
	public final EZDBStringItem              openFlg_A1;

    /** SCHD_OPEN_FLG_A1*/
	public final EZDBStringItem              schdOpenFlg_A1;

    /** MAN_SEND_RQST_FLG_AW*/
	public final EZDBStringItem              manSendRqstFlg_AW;

    /** MAN_SEND_RQST_FLG_AC*/
	public final EZDBStringItem              manSendRqstFlg_AC;

    /** CARR_CD_OD*/
	public final EZDBStringItem              carrCd_OD;

    /** NEXT_ACT_DT_BK*/
	public final EZDBDateItem              nextActDt_BK;

    /** NEXT_ACT_DT_BW*/
	public final EZDBDateItem              nextActDt_BW;

    /** SCE_ORD_TP_CD_A1*/
	public final EZDBStringItem              sceOrdTpCd_A1;


	/**
	 * NLBL3070_ABMsg is constructor.
	 * The initialization when the instance of NLBL3070_ABMsg is generated.
	 */
	public NLBL3070_ABMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3070_ABMsg is constructor.
	 * The initialization when the instance of NLBL3070_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3070_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		trxHdrNum_A1 = (EZDBStringItem)newItem("trxHdrNum_A1");
		svcConfigMstrPk_A1 = (EZDBBigDecimalItem)newItem("svcConfigMstrPk_A1");
		backOrdImpctTpDescTxt_A1 = (EZDBStringItem)newItem("backOrdImpctTpDescTxt_A1");
		soNum_A1 = (EZDBStringItem)newItem("soNum_A1");
		t_MdlNm_A1 = (EZDBStringItem)newItem("t_MdlNm_A1");
		rddDt_A1 = (EZDBDateItem)newItem("rddDt_A1");
		schdCarrPickUpDt_A1 = (EZDBDateItem)newItem("schdCarrPickUpDt_A1");
		schdDelyDt_A1 = (EZDBDateItem)newItem("schdDelyDt_A1");
		nextActDt_A1 = (EZDBDateItem)newItem("nextActDt_A1");
		techMeetTruckFlg_A1 = (EZDBStringItem)newItem("techMeetTruckFlg_A1");
		schdDelyTsDplyTxt_A2 = (EZDBStringItem)newItem("schdDelyTsDplyTxt_A2");
		rqstRcvDtTxt_S2 = (EZDBStringItem)newItem("rqstRcvDtTxt_S2");
		carrCd_A1 = (EZDBStringItem)newItem("carrCd_A1");
		carrNm_A1 = (EZDBStringItem)newItem("carrNm_A1");
		schdIstlDt_A1 = (EZDBDateItem)newItem("schdIstlDt_A1");
		schdDelyTsDplyTxt_A1 = (EZDBStringItem)newItem("schdDelyTsDplyTxt_A1");
		rqstRcvDtTxt_A1 = (EZDBStringItem)newItem("rqstRcvDtTxt_A1");
		schdDurnTmNum_A1 = (EZDBBigDecimalItem)newItem("schdDurnTmNum_A1");
		fsrNum_A1 = (EZDBStringItem)newItem("fsrNum_A1");
		svcTaskSchdDt_A1 = (EZDBDateItem)newItem("svcTaskSchdDt_A1");
		techNm_A1 = (EZDBStringItem)newItem("techNm_A1");
		schdCoordStsCd_A1 = (EZDBStringItem)newItem("schdCoordStsCd_A1");
		schdCoordStsCd_AL = (EZDBStringItemArray)newItemArray("schdCoordStsCd_AL");
		schdCoordStsDescTxt_AL = (EZDBStringItemArray)newItemArray("schdCoordStsDescTxt_AL");
		schdCoordStsDescTxt_A1 = (EZDBStringItem)newItem("schdCoordStsDescTxt_A1");
		manUpdPrtyNum_A1 = (EZDBBigDecimalItem)newItem("manUpdPrtyNum_A1");
		xxPsnFirstMidLastNm_A2 = (EZDBStringItem)newItem("xxPsnFirstMidLastNm_A2");
		tocNm_A1 = (EZDBStringItem)newItem("tocNm_A1");
		ctacPsnTelNum_A1 = (EZDBStringItem)newItem("ctacPsnTelNum_A1");
		dsAcctNm_A1 = (EZDBStringItem)newItem("dsAcctNm_A1");
		firstLineAddr_A1 = (EZDBStringItem)newItem("firstLineAddr_A1");
		scdLineAddr_A1 = (EZDBStringItem)newItem("scdLineAddr_A1");
		ctyAddr_A1 = (EZDBStringItem)newItem("ctyAddr_A1");
		stCd_A1 = (EZDBStringItem)newItem("stCd_A1");
		postCd_A1 = (EZDBStringItem)newItem("postCd_A1");
		ctryCd_A1 = (EZDBStringItem)newItem("ctryCd_A1");
		rtlWhNm_A1 = (EZDBStringItem)newItem("rtlWhNm_A1");
		wmsDropFlg_A1 = (EZDBStringItem)newItem("wmsDropFlg_A1");
		wmsDropRqstFlg_A1 = (EZDBStringItem)newItem("wmsDropRqstFlg_A1");
		sendRqstFlg_A1 = (EZDBStringItem)newItem("sendRqstFlg_A1");
		shpgSvcLvlCd_A1 = (EZDBStringItem)newItem("shpgSvcLvlCd_A1");
		carrAcctNum_A1 = (EZDBStringItem)newItem("carrAcctNum_A1");
		xxPsnFirstMidLastNm_A1 = (EZDBStringItem)newItem("xxPsnFirstMidLastNm_A1");
		tempSchdRsnCd_A1 = (EZDBStringItem)newItem("tempSchdRsnCd_A1");
		tempSchdCmntTxt_A1 = (EZDBStringItem)newItem("tempSchdCmntTxt_A1");
		pddDt_A1 = (EZDBDateItem)newItem("pddDt_A1");
		dsOrdCatgDescTxt_A1 = (EZDBStringItem)newItem("dsOrdCatgDescTxt_A1");
		dsOrdTpDescTxt_A1 = (EZDBStringItem)newItem("dsOrdTpDescTxt_A1");
		openFlg_A1 = (EZDBStringItem)newItem("openFlg_A1");
		schdOpenFlg_A1 = (EZDBStringItem)newItem("schdOpenFlg_A1");
		manSendRqstFlg_AW = (EZDBStringItem)newItem("manSendRqstFlg_AW");
		manSendRqstFlg_AC = (EZDBStringItem)newItem("manSendRqstFlg_AC");
		carrCd_OD = (EZDBStringItem)newItem("carrCd_OD");
		nextActDt_BK = (EZDBDateItem)newItem("nextActDt_BK");
		nextActDt_BW = (EZDBDateItem)newItem("nextActDt_BW");
		sceOrdTpCd_A1 = (EZDBStringItem)newItem("sceOrdTpCd_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3070_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3070_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"trxHdrNum_A1", "trxHdrNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"svcConfigMstrPk_A1", "svcConfigMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"backOrdImpctTpDescTxt_A1", "backOrdImpctTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"soNum_A1", "soNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"t_MdlNm_A1", "t_MdlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"rddDt_A1", "rddDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"schdCarrPickUpDt_A1", "schdCarrPickUpDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"schdDelyDt_A1", "schdDelyDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"nextActDt_A1", "nextActDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"techMeetTruckFlg_A1", "techMeetTruckFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"schdDelyTsDplyTxt_A2", "schdDelyTsDplyTxt_A2", "A2", null, TYPE_HANKAKUEISU, "19", null},
	{"rqstRcvDtTxt_S2", "rqstRcvDtTxt_S2", "S2", null, TYPE_HANKAKUEISU, "10", null},
	{"carrCd_A1", "carrCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"carrNm_A1", "carrNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"schdIstlDt_A1", "schdIstlDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"schdDelyTsDplyTxt_A1", "schdDelyTsDplyTxt_A1", "A1", null, TYPE_HANKAKUEISU, "19", null},
	{"rqstRcvDtTxt_A1", "rqstRcvDtTxt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"schdDurnTmNum_A1", "schdDurnTmNum_A1", "A1", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"fsrNum_A1", "fsrNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"svcTaskSchdDt_A1", "svcTaskSchdDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"techNm_A1", "techNm_A1", "A1", null, TYPE_HANKAKUEISU, "45", null},
	{"schdCoordStsCd_A1", "schdCoordStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"schdCoordStsCd_AL", "schdCoordStsCd_AL", "AL", "20", TYPE_HANKAKUEISU, "2", null},
	{"schdCoordStsDescTxt_AL", "schdCoordStsDescTxt_AL", "AL", "20", TYPE_HANKAKUEISU, "50", null},
	{"schdCoordStsDescTxt_A1", "schdCoordStsDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"manUpdPrtyNum_A1", "manUpdPrtyNum_A1", "A1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxPsnFirstMidLastNm_A2", "xxPsnFirstMidLastNm_A2", "A2", null, TYPE_HANKAKUEISU, "90", null},
	{"tocNm_A1", "tocNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"ctacPsnTelNum_A1", "ctacPsnTelNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"firstLineAddr_A1", "firstLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr_A1", "scdLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr_A1", "ctyAddr_A1", "A1", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_A1", "stCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_A1", "postCd_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"ctryCd_A1", "ctryCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlWhNm_A1", "rtlWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsDropFlg_A1", "wmsDropFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"wmsDropRqstFlg_A1", "wmsDropRqstFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"sendRqstFlg_A1", "sendRqstFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"shpgSvcLvlCd_A1", "shpgSvcLvlCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"carrAcctNum_A1", "carrAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxPsnFirstMidLastNm_A1", "xxPsnFirstMidLastNm_A1", "A1", null, TYPE_HANKAKUEISU, "90", null},
	{"tempSchdRsnCd_A1", "tempSchdRsnCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"tempSchdCmntTxt_A1", "tempSchdCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "180", null},
	{"pddDt_A1", "pddDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsOrdCatgDescTxt_A1", "dsOrdCatgDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpDescTxt_A1", "dsOrdTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"openFlg_A1", "openFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"schdOpenFlg_A1", "schdOpenFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"manSendRqstFlg_AW", "manSendRqstFlg_AW", "AW", null, TYPE_HANKAKUEISU, "1", null},
	{"manSendRqstFlg_AC", "manSendRqstFlg_AC", "AC", null, TYPE_HANKAKUEISU, "1", null},
	{"carrCd_OD", "carrCd_OD", "OD", null, TYPE_HANKAKUEISU, "20", null},
	{"nextActDt_BK", "nextActDt_BK", "BK", null, TYPE_NENTSUKIHI, "8", null},
	{"nextActDt_BW", "nextActDt_BW", "BW", null, TYPE_NENTSUKIHI, "8", null},
	{"sceOrdTpCd_A1", "sceOrdTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"TRX_HDR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_A1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A1
        {"BACK_ORD_IMPCT_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//backOrdImpctTpDescTxt_A1
        {"SO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_A1
        {"T_MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_A1
        {"RDD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//rddDt_A1
        {"SCHD_CARR_PICK_UP_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//schdCarrPickUpDt_A1
        {"SCHD_DELY_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//schdDelyDt_A1
        {"NEXT_ACT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//nextActDt_A1
        {"TECH_MEET_TRUCK_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techMeetTruckFlg_A1
        {"SCHD_DELY_TS_DPLY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdDelyTsDplyTxt_A2
        {"RQST_RCV_DT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_S2
        {"CARR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_A1
        {"CARR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_A1
        {"SCHD_ISTL_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//schdIstlDt_A1
        {"SCHD_DELY_TS_DPLY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdDelyTsDplyTxt_A1
        {"RQST_RCV_DT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_A1
        {"SCHD_DURN_TM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdDurnTmNum_A1
        {"FSR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_A1
        {"SVC_TASK_SCHD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//svcTaskSchdDt_A1
        {"TECH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_A1
        {"SCHD_COORD_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordStsCd_A1
        {"SCHD_COORD_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordStsCd_AL
        {"SCHD_COORD_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordStsDescTxt_AL
        {"SCHD_COORD_STS_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordStsDescTxt_A1
        {"MAN_UPD_PRTY_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manUpdPrtyNum_A1
        {"XX_PSN_FIRST_MID_LAST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnFirstMidLastNm_A2
        {"TOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_A1
        {"CTAC_PSN_TEL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnTelNum_A1
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"FIRST_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_A1
        {"SCD_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr_A1
        {"CTY_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_A1
        {"ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_A1
        {"POST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_A1
        {"CTRY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd_A1
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A1
        {"WMS_DROP_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsDropFlg_A1
        {"WMS_DROP_RQST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsDropRqstFlg_A1
        {"SEND_RQST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sendRqstFlg_A1
        {"SHPG_SVC_LVL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_A1
        {"CARR_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrAcctNum_A1
        {"XX_PSN_FIRST_MID_LAST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnFirstMidLastNm_A1
        {"TEMP_SCHD_RSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tempSchdRsnCd_A1
        {"TEMP_SCHD_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tempSchdCmntTxt_A1
        {"PDD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//pddDt_A1
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_A1
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_A1
        {"OPEN_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openFlg_A1
        {"SCHD_OPEN_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdOpenFlg_A1
        {"MAN_SEND_RQST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manSendRqstFlg_AW
        {"MAN_SEND_RQST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manSendRqstFlg_AC
        {"CARR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_OD
        {"NEXT_ACT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//nextActDt_BK
        {"NEXT_ACT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//nextActDt_BW
        {"SCE_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_A1
	};

	/**
	 * get Array of common (basic) data.
	 * @return Array of common (basis) data
	 */
	protected String[][] getBaseContents() {
		return BASE_CONTENTS;
	}

	/**
	 * get Array of Display Field.
	 * @return Array of  Display  Fields
	 */
	protected String[][] getDispContents() {
		return DISP_CONTENTS;
	}

}

