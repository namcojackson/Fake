//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20200324091802000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3130CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3130;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3130 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3130CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_G1*/
	public final EZDCStringItem              glblCmpyCd_G1;

    /** DS_CPO_CONFIG_PK_G1*/
	public final EZDCBigDecimalItem              dsCpoConfigPk_G1;

    /** XX_TM_FRAME_TXT_CD*/
	public final EZDCStringItemArray              xxTmFrameTxt_CD;

    /** XX_TM_FRAME_TXT_VL*/
	public final EZDCStringItemArray              xxTmFrameTxt_VL;

    /** TRX_HDR_NUM_H1*/
	public final EZDCStringItem              trxHdrNum_H1;

    /** SO_NUM_H1*/
	public final EZDCStringItem              soNum_H1;

    /** RWS_NUM_H1*/
	public final EZDCStringItem              rwsNum_H1;

    /** TM_ZONE_CD_H1*/
	public final EZDCStringItem              tmZoneCd_H1;

    /** XX_LINK_ANCR_H1*/
	public final EZDCStringItem              xxLinkAncr_H1;

    /** DS_ACCT_NM_H1*/
	public final EZDCStringItem              dsAcctNm_H1;

    /** OPS_FROM_HOUR_MN_H1*/
	public final EZDCStringItem              opsFromHourMn_H1;

    /** XX_SCR_ITEM_7_TXT_OF*/
	public final EZDCStringItem              xxScrItem7Txt_OF;

    /** XX_TM_FRAME_TXT_OF*/
	public final EZDCStringItem              xxTmFrameTxt_OF;

    /** OPS_TO_HOUR_MN_H1*/
	public final EZDCStringItem              opsToHourMn_H1;

    /** XX_SCR_ITEM_7_TXT_OT*/
	public final EZDCStringItem              xxScrItem7Txt_OT;

    /** XX_TM_FRAME_TXT_OT*/
	public final EZDCStringItem              xxTmFrameTxt_OT;

    /** SEC_CLNC_REQ_TXT_Y*/
	public final EZDCStringItem              secClncReqTxt_Y;

    /** SEC_CLNC_REQ_TXT_N*/
	public final EZDCStringItem              secClncReqTxt_N;

    /** INS_CERT_REQ_TXT_Y*/
	public final EZDCStringItem              insCertReqTxt_Y;

    /** INS_CERT_REQ_TXT_N*/
	public final EZDCStringItem              insCertReqTxt_N;

    /** CMPY_INFO_FL_NM_H1*/
	public final EZDCStringItem              cmpyInfoFlNm_H1;

    /** FL_COV_TXT_H1*/
	public final EZDCStringItem              flCovTxt_H1;

    /** TRCTR_AND_TRAIL_ACCS_TXT_Y*/
	public final EZDCStringItem              trctrAndTrailAccsTxt_Y;

    /** TRCTR_AND_TRAIL_ACCS_TXT_N*/
	public final EZDCStringItem              trctrAndTrailAccsTxt_N;

    /** LOAD_DOCK_AVAL_TXT_Y*/
	public final EZDCStringItem              loadDockAvalTxt_Y;

    /** LOAD_DOCK_AVAL_TXT_N*/
	public final EZDCStringItem              loadDockAvalTxt_N;

    /** LOAD_DOCK_HGT_H1*/
	public final EZDCBigDecimalItem              loadDockHgt_H1;

    /** RAMP_AVAL_TXT_Y*/
	public final EZDCStringItem              rampAvalTxt_Y;

    /** RAMP_AVAL_TXT_N*/
	public final EZDCStringItem              rampAvalTxt_N;

    /** STEP_AVAL_TXT_Y*/
	public final EZDCStringItem              stepAvalTxt_Y;

    /** STEP_AVAL_TXT_N*/
	public final EZDCStringItem              stepAvalTxt_N;

    /** INSD_STEP_NUM_H1*/
	public final EZDCStringItem              insdStepNum_H1;

    /** OTSD_STEP_NUM_H1*/
	public final EZDCStringItem              otsdStepNum_H1;

    /** FRONT_DOOR_AVAL_TXT_Y*/
	public final EZDCStringItem              frontDoorAvalTxt_Y;

    /** FRONT_DOOR_AVAL_TXT_N*/
	public final EZDCStringItem              frontDoorAvalTxt_N;

    /** BACK_DOOR_AVAL_TXT_Y*/
	public final EZDCStringItem              backDoorAvalTxt_Y;

    /** BACK_DOOR_AVAL_TXT_N*/
	public final EZDCStringItem              backDoorAvalTxt_N;

    /** PWR_OTLT_CONFIG_TXT_Y*/
	public final EZDCStringItem              pwrOtltConfigTxt_Y;

    /** PWR_OTLT_CONFIG_TXT_N*/
	public final EZDCStringItem              pwrOtltConfigTxt_N;

    /** SGN_ON_BLDG_RDSD_TXT_Y*/
	public final EZDCStringItem              sgnOnBldgRdsdTxt_Y;

    /** SGN_ON_BLDG_RDSD_TXT_N*/
	public final EZDCStringItem              sgnOnBldgRdsdTxt_N;

    /** BLDG_STRY_NUM_H1*/
	public final EZDCStringItem              bldgStryNum_H1;

    /** BLDG_GURD_NTFY_TXT_Y*/
	public final EZDCStringItem              bldgGurdNtfyTxt_Y;

    /** BLDG_GURD_NTFY_TXT_N*/
	public final EZDCStringItem              bldgGurdNtfyTxt_N;

    /** INDL_PARK_TXT_Y*/
	public final EZDCStringItem              indlParkTxt_Y;

    /** INDL_PARK_TXT_N*/
	public final EZDCStringItem              indlParkTxt_N;

    /** BIZ_PARK_TXT_Y*/
	public final EZDCStringItem              bizParkTxt_Y;

    /** BIZ_PARK_TXT_N*/
	public final EZDCStringItem              bizParkTxt_N;

    /** PRO_BLDG_TXT_Y*/
	public final EZDCStringItem              proBldgTxt_Y;

    /** PRO_BLDG_TXT_N*/
	public final EZDCStringItem              proBldgTxt_N;

    /** SHPNG_CTR_TXT_Y*/
	public final EZDCStringItem              shpngCtrTxt_Y;

    /** SHPNG_CTR_TXT_N*/
	public final EZDCStringItem              shpngCtrTxt_N;

    /** PVT_RES_TXT_Y*/
	public final EZDCStringItem              pvtResTxt_Y;

    /** PVT_RES_TXT_N*/
	public final EZDCStringItem              pvtResTxt_N;

    /** DELY_TRNSP_OPT_CD_H1*/
	public final EZDCStringItem              delyTrnspOptCd_H1;

    /** DELY_TRNSP_OPT_CD_CD*/
	public final EZDCStringItemArray              delyTrnspOptCd_CD;

    /** DELY_TRNSP_OPT_DESC_TXT_VL*/
	public final EZDCStringItemArray              delyTrnspOptDescTxt_VL;

    /** STAIR_CRAW_REQ_FLG_Y*/
	public final EZDCStringItem              stairCrawReqFlg_Y;

    /** STAIR_CRAW_REQ_FLG_N*/
	public final EZDCStringItem              stairCrawReqFlg_N;

    /** STAIR_AND_LDG_WDT_H1*/
	public final EZDCBigDecimalItem              stairAndLdgWdt_H1;

    /** BLDG_ENT_DOOR_HGT_A1*/
	public final EZDCBigDecimalItem              bldgEntDoorHgt_A1;

    /** BLDG_ENT_DOOR_WDT_A1*/
	public final EZDCBigDecimalItem              bldgEntDoorWdt_A1;

    /** CRDR_WDT_A1*/
	public final EZDCBigDecimalItem              crdrWdt_A1;

    /** DOOR_WDT_A1*/
	public final EZDCBigDecimalItem              doorWdt_A1;

    /** SHPG_INSTN_CMNT_TXT_B1*/
	public final EZDCStringItem              shpgInstnCmntTxt_B1;

    /** DELY_INSTN_CMNT_TXT_B1*/
	public final EZDCStringItem              delyInstnCmntTxt_B1;

    /** ISTL_INSTN_CMNT_TXT_B1*/
	public final EZDCStringItem              istlInstnCmntTxt_B1;

    /** TECH_INSTN_CMNT_TXT_B1*/
	public final EZDCStringItem              techInstnCmntTxt_B1;

    /** ELEV_AVAL_TXT_Y*/
	public final EZDCStringItem              elevAvalTxt_Y;

    /** ELEV_AVAL_TXT_N*/
	public final EZDCStringItem              elevAvalTxt_N;

    /** ELEV_AVAL_FROM_HOUR_MN_C1*/
	public final EZDCStringItem              elevAvalFromHourMn_C1;

    /** XX_SCR_ITEM_7_TXT_EF*/
	public final EZDCStringItem              xxScrItem7Txt_EF;

    /** XX_TM_FRAME_TXT_EF*/
	public final EZDCStringItem              xxTmFrameTxt_EF;

    /** ELEV_AVAL_TO_HOUR_MN_C1*/
	public final EZDCStringItem              elevAvalToHourMn_C1;

    /** XX_SCR_ITEM_7_TXT_ET*/
	public final EZDCStringItem              xxScrItem7Txt_ET;

    /** XX_TM_FRAME_TXT_ET*/
	public final EZDCStringItem              xxTmFrameTxt_ET;

    /** ELEV_APPT_REQ_TXT_Y*/
	public final EZDCStringItem              elevApptReqTxt_Y;

    /** ELEV_APPT_REQ_TXT_N*/
	public final EZDCStringItem              elevApptReqTxt_N;

    /** ELEV_CTAC_PSN_NM_C1*/
	public final EZDCStringItem              elevCtacPsnNm_C1;

    /** ELEV_CTAC_TEL_NUM_C1*/
	public final EZDCStringItem              elevCtacTelNum_C1;

    /** ELEV_WDT_C1*/
	public final EZDCBigDecimalItem              elevWdt_C1;

    /** ELEV_DEPTH_NUM_C1*/
	public final EZDCBigDecimalItem              elevDepthNum_C1;

    /** ELEV_CAP_WT_C1*/
	public final EZDCBigDecimalItem              elevCapWt_C1;

    /** ELEV_DOOR_HGT_C1*/
	public final EZDCBigDecimalItem              elevDoorHgt_C1;

    /** ELEV_DOOR_WDT_C1*/
	public final EZDCBigDecimalItem              elevDoorWdt_C1;

    /** DELY_INSTN_ADDL_CMNT_TXT_D1*/
	public final EZDCStringItem              delyInstnAddlCmntTxt_D1;

    /** VLD_USR_ID_E1*/
	public final EZDCStringItem              vldUsrId_E1;

    /** XX_PSN_NM_E1*/
	public final EZDCStringItem              xxPsnNm_E1;

    /** XX_TS_DSP_19_TXT_E1*/
	public final EZDCStringItem              xxTsDsp19Txt_E1;

    /** CUST_INFO_BO_FLG_Y*/
	public final EZDCStringItem              custInfoBoFlg_Y;

    /** CUST_INFO_BO_FLG_N*/
	public final EZDCStringItem              custInfoBoFlg_N;

    /** PICK_UP_XTR_TONER_FLG_Y*/
	public final EZDCStringItem              pickUpXtrTonerFlg_Y;

    /** PICK_UP_XTR_TONER_FLG_N*/
	public final EZDCStringItem              pickUpXtrTonerFlg_N;

    /** PICK_UP_AT_SAME_TM_FLG_Y*/
	public final EZDCStringItem              pickUpAtSameTmFlg_Y;

    /** PICK_UP_AT_SAME_TM_FLG_N*/
	public final EZDCStringItem              pickUpAtSameTmFlg_N;

    /** CPO_ORD_NUM_P1*/
	public final EZDCStringItem              cpoOrdNum_P1;

    /** DS_ORD_POSN_NUM_P1*/
	public final EZDCStringItem              dsOrdPosnNum_P1;

    /** CONFIG_CATG_CD_P1*/
	public final EZDCStringItem              configCatgCd_P1;

    /** CTRY_CD_S1*/
	public final EZDCStringItem              ctryCd_S1;

    /** POST_CD_S1*/
	public final EZDCStringItem              postCd_S1;


	/**
	 * NLBL3130CMsg is constructor.
	 * The initialization when the instance of NLBL3130CMsg is generated.
	 */
	public NLBL3130CMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3130CMsg is constructor.
	 * The initialization when the instance of NLBL3130CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3130CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_G1 = (EZDCStringItem)newItem("glblCmpyCd_G1");
		dsCpoConfigPk_G1 = (EZDCBigDecimalItem)newItem("dsCpoConfigPk_G1");
		xxTmFrameTxt_CD = (EZDCStringItemArray)newItemArray("xxTmFrameTxt_CD");
		xxTmFrameTxt_VL = (EZDCStringItemArray)newItemArray("xxTmFrameTxt_VL");
		trxHdrNum_H1 = (EZDCStringItem)newItem("trxHdrNum_H1");
		soNum_H1 = (EZDCStringItem)newItem("soNum_H1");
		rwsNum_H1 = (EZDCStringItem)newItem("rwsNum_H1");
		tmZoneCd_H1 = (EZDCStringItem)newItem("tmZoneCd_H1");
		xxLinkAncr_H1 = (EZDCStringItem)newItem("xxLinkAncr_H1");
		dsAcctNm_H1 = (EZDCStringItem)newItem("dsAcctNm_H1");
		opsFromHourMn_H1 = (EZDCStringItem)newItem("opsFromHourMn_H1");
		xxScrItem7Txt_OF = (EZDCStringItem)newItem("xxScrItem7Txt_OF");
		xxTmFrameTxt_OF = (EZDCStringItem)newItem("xxTmFrameTxt_OF");
		opsToHourMn_H1 = (EZDCStringItem)newItem("opsToHourMn_H1");
		xxScrItem7Txt_OT = (EZDCStringItem)newItem("xxScrItem7Txt_OT");
		xxTmFrameTxt_OT = (EZDCStringItem)newItem("xxTmFrameTxt_OT");
		secClncReqTxt_Y = (EZDCStringItem)newItem("secClncReqTxt_Y");
		secClncReqTxt_N = (EZDCStringItem)newItem("secClncReqTxt_N");
		insCertReqTxt_Y = (EZDCStringItem)newItem("insCertReqTxt_Y");
		insCertReqTxt_N = (EZDCStringItem)newItem("insCertReqTxt_N");
		cmpyInfoFlNm_H1 = (EZDCStringItem)newItem("cmpyInfoFlNm_H1");
		flCovTxt_H1 = (EZDCStringItem)newItem("flCovTxt_H1");
		trctrAndTrailAccsTxt_Y = (EZDCStringItem)newItem("trctrAndTrailAccsTxt_Y");
		trctrAndTrailAccsTxt_N = (EZDCStringItem)newItem("trctrAndTrailAccsTxt_N");
		loadDockAvalTxt_Y = (EZDCStringItem)newItem("loadDockAvalTxt_Y");
		loadDockAvalTxt_N = (EZDCStringItem)newItem("loadDockAvalTxt_N");
		loadDockHgt_H1 = (EZDCBigDecimalItem)newItem("loadDockHgt_H1");
		rampAvalTxt_Y = (EZDCStringItem)newItem("rampAvalTxt_Y");
		rampAvalTxt_N = (EZDCStringItem)newItem("rampAvalTxt_N");
		stepAvalTxt_Y = (EZDCStringItem)newItem("stepAvalTxt_Y");
		stepAvalTxt_N = (EZDCStringItem)newItem("stepAvalTxt_N");
		insdStepNum_H1 = (EZDCStringItem)newItem("insdStepNum_H1");
		otsdStepNum_H1 = (EZDCStringItem)newItem("otsdStepNum_H1");
		frontDoorAvalTxt_Y = (EZDCStringItem)newItem("frontDoorAvalTxt_Y");
		frontDoorAvalTxt_N = (EZDCStringItem)newItem("frontDoorAvalTxt_N");
		backDoorAvalTxt_Y = (EZDCStringItem)newItem("backDoorAvalTxt_Y");
		backDoorAvalTxt_N = (EZDCStringItem)newItem("backDoorAvalTxt_N");
		pwrOtltConfigTxt_Y = (EZDCStringItem)newItem("pwrOtltConfigTxt_Y");
		pwrOtltConfigTxt_N = (EZDCStringItem)newItem("pwrOtltConfigTxt_N");
		sgnOnBldgRdsdTxt_Y = (EZDCStringItem)newItem("sgnOnBldgRdsdTxt_Y");
		sgnOnBldgRdsdTxt_N = (EZDCStringItem)newItem("sgnOnBldgRdsdTxt_N");
		bldgStryNum_H1 = (EZDCStringItem)newItem("bldgStryNum_H1");
		bldgGurdNtfyTxt_Y = (EZDCStringItem)newItem("bldgGurdNtfyTxt_Y");
		bldgGurdNtfyTxt_N = (EZDCStringItem)newItem("bldgGurdNtfyTxt_N");
		indlParkTxt_Y = (EZDCStringItem)newItem("indlParkTxt_Y");
		indlParkTxt_N = (EZDCStringItem)newItem("indlParkTxt_N");
		bizParkTxt_Y = (EZDCStringItem)newItem("bizParkTxt_Y");
		bizParkTxt_N = (EZDCStringItem)newItem("bizParkTxt_N");
		proBldgTxt_Y = (EZDCStringItem)newItem("proBldgTxt_Y");
		proBldgTxt_N = (EZDCStringItem)newItem("proBldgTxt_N");
		shpngCtrTxt_Y = (EZDCStringItem)newItem("shpngCtrTxt_Y");
		shpngCtrTxt_N = (EZDCStringItem)newItem("shpngCtrTxt_N");
		pvtResTxt_Y = (EZDCStringItem)newItem("pvtResTxt_Y");
		pvtResTxt_N = (EZDCStringItem)newItem("pvtResTxt_N");
		delyTrnspOptCd_H1 = (EZDCStringItem)newItem("delyTrnspOptCd_H1");
		delyTrnspOptCd_CD = (EZDCStringItemArray)newItemArray("delyTrnspOptCd_CD");
		delyTrnspOptDescTxt_VL = (EZDCStringItemArray)newItemArray("delyTrnspOptDescTxt_VL");
		stairCrawReqFlg_Y = (EZDCStringItem)newItem("stairCrawReqFlg_Y");
		stairCrawReqFlg_N = (EZDCStringItem)newItem("stairCrawReqFlg_N");
		stairAndLdgWdt_H1 = (EZDCBigDecimalItem)newItem("stairAndLdgWdt_H1");
		bldgEntDoorHgt_A1 = (EZDCBigDecimalItem)newItem("bldgEntDoorHgt_A1");
		bldgEntDoorWdt_A1 = (EZDCBigDecimalItem)newItem("bldgEntDoorWdt_A1");
		crdrWdt_A1 = (EZDCBigDecimalItem)newItem("crdrWdt_A1");
		doorWdt_A1 = (EZDCBigDecimalItem)newItem("doorWdt_A1");
		shpgInstnCmntTxt_B1 = (EZDCStringItem)newItem("shpgInstnCmntTxt_B1");
		delyInstnCmntTxt_B1 = (EZDCStringItem)newItem("delyInstnCmntTxt_B1");
		istlInstnCmntTxt_B1 = (EZDCStringItem)newItem("istlInstnCmntTxt_B1");
		techInstnCmntTxt_B1 = (EZDCStringItem)newItem("techInstnCmntTxt_B1");
		elevAvalTxt_Y = (EZDCStringItem)newItem("elevAvalTxt_Y");
		elevAvalTxt_N = (EZDCStringItem)newItem("elevAvalTxt_N");
		elevAvalFromHourMn_C1 = (EZDCStringItem)newItem("elevAvalFromHourMn_C1");
		xxScrItem7Txt_EF = (EZDCStringItem)newItem("xxScrItem7Txt_EF");
		xxTmFrameTxt_EF = (EZDCStringItem)newItem("xxTmFrameTxt_EF");
		elevAvalToHourMn_C1 = (EZDCStringItem)newItem("elevAvalToHourMn_C1");
		xxScrItem7Txt_ET = (EZDCStringItem)newItem("xxScrItem7Txt_ET");
		xxTmFrameTxt_ET = (EZDCStringItem)newItem("xxTmFrameTxt_ET");
		elevApptReqTxt_Y = (EZDCStringItem)newItem("elevApptReqTxt_Y");
		elevApptReqTxt_N = (EZDCStringItem)newItem("elevApptReqTxt_N");
		elevCtacPsnNm_C1 = (EZDCStringItem)newItem("elevCtacPsnNm_C1");
		elevCtacTelNum_C1 = (EZDCStringItem)newItem("elevCtacTelNum_C1");
		elevWdt_C1 = (EZDCBigDecimalItem)newItem("elevWdt_C1");
		elevDepthNum_C1 = (EZDCBigDecimalItem)newItem("elevDepthNum_C1");
		elevCapWt_C1 = (EZDCBigDecimalItem)newItem("elevCapWt_C1");
		elevDoorHgt_C1 = (EZDCBigDecimalItem)newItem("elevDoorHgt_C1");
		elevDoorWdt_C1 = (EZDCBigDecimalItem)newItem("elevDoorWdt_C1");
		delyInstnAddlCmntTxt_D1 = (EZDCStringItem)newItem("delyInstnAddlCmntTxt_D1");
		vldUsrId_E1 = (EZDCStringItem)newItem("vldUsrId_E1");
		xxPsnNm_E1 = (EZDCStringItem)newItem("xxPsnNm_E1");
		xxTsDsp19Txt_E1 = (EZDCStringItem)newItem("xxTsDsp19Txt_E1");
		custInfoBoFlg_Y = (EZDCStringItem)newItem("custInfoBoFlg_Y");
		custInfoBoFlg_N = (EZDCStringItem)newItem("custInfoBoFlg_N");
		pickUpXtrTonerFlg_Y = (EZDCStringItem)newItem("pickUpXtrTonerFlg_Y");
		pickUpXtrTonerFlg_N = (EZDCStringItem)newItem("pickUpXtrTonerFlg_N");
		pickUpAtSameTmFlg_Y = (EZDCStringItem)newItem("pickUpAtSameTmFlg_Y");
		pickUpAtSameTmFlg_N = (EZDCStringItem)newItem("pickUpAtSameTmFlg_N");
		cpoOrdNum_P1 = (EZDCStringItem)newItem("cpoOrdNum_P1");
		dsOrdPosnNum_P1 = (EZDCStringItem)newItem("dsOrdPosnNum_P1");
		configCatgCd_P1 = (EZDCStringItem)newItem("configCatgCd_P1");
		ctryCd_S1 = (EZDCStringItem)newItem("ctryCd_S1");
		postCd_S1 = (EZDCStringItem)newItem("postCd_S1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3130CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3130CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_G1", "glblCmpyCd_G1", "G1", null, TYPE_HANKAKUEISU, "4", null},
	{"dsCpoConfigPk_G1", "dsCpoConfigPk_G1", "G1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxTmFrameTxt_CD", "xxTmFrameTxt_CD", "CD", "99", TYPE_HANKAKUEISU, "13", null},
	{"xxTmFrameTxt_VL", "xxTmFrameTxt_VL", "VL", "99", TYPE_HANKAKUEISU, "13", null},
	{"trxHdrNum_H1", "trxHdrNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"soNum_H1", "soNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"rwsNum_H1", "rwsNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"tmZoneCd_H1", "tmZoneCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxLinkAncr_H1", "xxLinkAncr_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNm_H1", "dsAcctNm_H1", "H1", null, TYPE_HANKAKUEISU, "360", null},
	{"opsFromHourMn_H1", "opsFromHourMn_H1", "H1", null, TYPE_HANKAKUSUJI, "4", null},
	{"xxScrItem7Txt_OF", "xxScrItem7Txt_OF", "OF", null, TYPE_HANKAKUEISU, "7", null},
	{"xxTmFrameTxt_OF", "xxTmFrameTxt_OF", "OF", null, TYPE_HANKAKUEISU, "13", null},
	{"opsToHourMn_H1", "opsToHourMn_H1", "H1", null, TYPE_HANKAKUSUJI, "4", null},
	{"xxScrItem7Txt_OT", "xxScrItem7Txt_OT", "OT", null, TYPE_HANKAKUEISU, "7", null},
	{"xxTmFrameTxt_OT", "xxTmFrameTxt_OT", "OT", null, TYPE_HANKAKUEISU, "13", null},
	{"secClncReqTxt_Y", "secClncReqTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"secClncReqTxt_N", "secClncReqTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"insCertReqTxt_Y", "insCertReqTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"insCertReqTxt_N", "insCertReqTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"cmpyInfoFlNm_H1", "cmpyInfoFlNm_H1", "H1", null, TYPE_HANKAKUEISU, "10", null},
	{"flCovTxt_H1", "flCovTxt_H1", "H1", null, TYPE_HANKAKUEISU, "250", null},
	{"trctrAndTrailAccsTxt_Y", "trctrAndTrailAccsTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"trctrAndTrailAccsTxt_N", "trctrAndTrailAccsTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"loadDockAvalTxt_Y", "loadDockAvalTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"loadDockAvalTxt_N", "loadDockAvalTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"loadDockHgt_H1", "loadDockHgt_H1", "H1", null, TYPE_SEISU_SYOSU, "4", "1"},
	{"rampAvalTxt_Y", "rampAvalTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"rampAvalTxt_N", "rampAvalTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"stepAvalTxt_Y", "stepAvalTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"stepAvalTxt_N", "stepAvalTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"insdStepNum_H1", "insdStepNum_H1", "H1", null, TYPE_HANKAKUEISU, "4", null},
	{"otsdStepNum_H1", "otsdStepNum_H1", "H1", null, TYPE_HANKAKUEISU, "4", null},
	{"frontDoorAvalTxt_Y", "frontDoorAvalTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"frontDoorAvalTxt_N", "frontDoorAvalTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"backDoorAvalTxt_Y", "backDoorAvalTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"backDoorAvalTxt_N", "backDoorAvalTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"pwrOtltConfigTxt_Y", "pwrOtltConfigTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"pwrOtltConfigTxt_N", "pwrOtltConfigTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"sgnOnBldgRdsdTxt_Y", "sgnOnBldgRdsdTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"sgnOnBldgRdsdTxt_N", "sgnOnBldgRdsdTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"bldgStryNum_H1", "bldgStryNum_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"bldgGurdNtfyTxt_Y", "bldgGurdNtfyTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"bldgGurdNtfyTxt_N", "bldgGurdNtfyTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"indlParkTxt_Y", "indlParkTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"indlParkTxt_N", "indlParkTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"bizParkTxt_Y", "bizParkTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"bizParkTxt_N", "bizParkTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"proBldgTxt_Y", "proBldgTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"proBldgTxt_N", "proBldgTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"shpngCtrTxt_Y", "shpngCtrTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"shpngCtrTxt_N", "shpngCtrTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"pvtResTxt_Y", "pvtResTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"pvtResTxt_N", "pvtResTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"delyTrnspOptCd_H1", "delyTrnspOptCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"delyTrnspOptCd_CD", "delyTrnspOptCd_CD", "CD", "99", TYPE_HANKAKUEISU, "20", null},
	{"delyTrnspOptDescTxt_VL", "delyTrnspOptDescTxt_VL", "VL", "99", TYPE_HANKAKUEISU, "50", null},
	{"stairCrawReqFlg_Y", "stairCrawReqFlg_Y", "Y", null, TYPE_HANKAKUEISU, "1", null},
	{"stairCrawReqFlg_N", "stairCrawReqFlg_N", "N", null, TYPE_HANKAKUEISU, "1", null},
	{"stairAndLdgWdt_H1", "stairAndLdgWdt_H1", "H1", null, TYPE_SEISU_SYOSU, "4", "1"},
	{"bldgEntDoorHgt_A1", "bldgEntDoorHgt_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "1"},
	{"bldgEntDoorWdt_A1", "bldgEntDoorWdt_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "1"},
	{"crdrWdt_A1", "crdrWdt_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "1"},
	{"doorWdt_A1", "doorWdt_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "1"},
	{"shpgInstnCmntTxt_B1", "shpgInstnCmntTxt_B1", "B1", null, TYPE_HANKAKUEISU, "260", null},
	{"delyInstnCmntTxt_B1", "delyInstnCmntTxt_B1", "B1", null, TYPE_HANKAKUEISU, "240", null},
	{"istlInstnCmntTxt_B1", "istlInstnCmntTxt_B1", "B1", null, TYPE_HANKAKUEISU, "180", null},
	{"techInstnCmntTxt_B1", "techInstnCmntTxt_B1", "B1", null, TYPE_HANKAKUEISU, "180", null},
	{"elevAvalTxt_Y", "elevAvalTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"elevAvalTxt_N", "elevAvalTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"elevAvalFromHourMn_C1", "elevAvalFromHourMn_C1", "C1", null, TYPE_HANKAKUSUJI, "4", null},
	{"xxScrItem7Txt_EF", "xxScrItem7Txt_EF", "EF", null, TYPE_HANKAKUEISU, "7", null},
	{"xxTmFrameTxt_EF", "xxTmFrameTxt_EF", "EF", null, TYPE_HANKAKUEISU, "13", null},
	{"elevAvalToHourMn_C1", "elevAvalToHourMn_C1", "C1", null, TYPE_HANKAKUSUJI, "4", null},
	{"xxScrItem7Txt_ET", "xxScrItem7Txt_ET", "ET", null, TYPE_HANKAKUEISU, "7", null},
	{"xxTmFrameTxt_ET", "xxTmFrameTxt_ET", "ET", null, TYPE_HANKAKUEISU, "13", null},
	{"elevApptReqTxt_Y", "elevApptReqTxt_Y", "Y", null, TYPE_HANKAKUEISU, "5", null},
	{"elevApptReqTxt_N", "elevApptReqTxt_N", "N", null, TYPE_HANKAKUEISU, "5", null},
	{"elevCtacPsnNm_C1", "elevCtacPsnNm_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"elevCtacTelNum_C1", "elevCtacTelNum_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"elevWdt_C1", "elevWdt_C1", "C1", null, TYPE_SEISU_SYOSU, "4", "1"},
	{"elevDepthNum_C1", "elevDepthNum_C1", "C1", null, TYPE_SEISU_SYOSU, "4", "1"},
	{"elevCapWt_C1", "elevCapWt_C1", "C1", null, TYPE_SEISU_SYOSU, "6", "1"},
	{"elevDoorHgt_C1", "elevDoorHgt_C1", "C1", null, TYPE_SEISU_SYOSU, "4", "1"},
	{"elevDoorWdt_C1", "elevDoorWdt_C1", "C1", null, TYPE_SEISU_SYOSU, "4", "1"},
	{"delyInstnAddlCmntTxt_D1", "delyInstnAddlCmntTxt_D1", "D1", null, TYPE_HANKAKUEISU, "180", null},
	{"vldUsrId_E1", "vldUsrId_E1", "E1", null, TYPE_HANKAKUEISU, "16", null},
	{"xxPsnNm_E1", "xxPsnNm_E1", "E1", null, TYPE_HANKAKUEISU, "62", null},
	{"xxTsDsp19Txt_E1", "xxTsDsp19Txt_E1", "E1", null, TYPE_HANKAKUEISU, "19", null},
	{"custInfoBoFlg_Y", "custInfoBoFlg_Y", "Y", null, TYPE_HANKAKUEISU, "1", null},
	{"custInfoBoFlg_N", "custInfoBoFlg_N", "N", null, TYPE_HANKAKUEISU, "1", null},
	{"pickUpXtrTonerFlg_Y", "pickUpXtrTonerFlg_Y", "Y", null, TYPE_HANKAKUEISU, "1", null},
	{"pickUpXtrTonerFlg_N", "pickUpXtrTonerFlg_N", "N", null, TYPE_HANKAKUEISU, "1", null},
	{"pickUpAtSameTmFlg_Y", "pickUpAtSameTmFlg_Y", "Y", null, TYPE_HANKAKUEISU, "1", null},
	{"pickUpAtSameTmFlg_N", "pickUpAtSameTmFlg_N", "N", null, TYPE_HANKAKUEISU, "1", null},
	{"cpoOrdNum_P1", "cpoOrdNum_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdPosnNum_P1", "dsOrdPosnNum_P1", "P1", null, TYPE_HANKAKUEISU, "6", null},
	{"configCatgCd_P1", "configCatgCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"ctryCd_S1", "ctryCd_S1", "S1", null, TYPE_HANKAKUEISU, "3", null},
	{"postCd_S1", "postCd_S1", "S1", null, TYPE_HANKAKUEISU, "15", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_G1
        {"DS_CPO_CONFIG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoConfigPk_G1
        {"XX_TM_FRAME_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTmFrameTxt_CD
        {"XX_TM_FRAME_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTmFrameTxt_VL
        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_H1
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_H1
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum_H1
        {"TM_ZONE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tmZoneCd_H1
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_H1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H1
        {"OPS_FROM_HOUR_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//opsFromHourMn_H1
        {"XX_SCR_ITEM_7_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem7Txt_OF
        {"XX_TM_FRAME_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTmFrameTxt_OF
        {"OPS_TO_HOUR_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//opsToHourMn_H1
        {"XX_SCR_ITEM_7_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem7Txt_OT
        {"XX_TM_FRAME_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTmFrameTxt_OT
        {"SEC_CLNC_REQ_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//secClncReqTxt_Y
        {"SEC_CLNC_REQ_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//secClncReqTxt_N
        {"INS_CERT_REQ_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//insCertReqTxt_Y
        {"INS_CERT_REQ_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//insCertReqTxt_N
        {"CMPY_INFO_FL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpyInfoFlNm_H1
        {"FL_COV_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flCovTxt_H1
        {"TRCTR_AND_TRAIL_ACCS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trctrAndTrailAccsTxt_Y
        {"TRCTR_AND_TRAIL_ACCS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trctrAndTrailAccsTxt_N
        {"LOAD_DOCK_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//loadDockAvalTxt_Y
        {"LOAD_DOCK_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//loadDockAvalTxt_N
        {"LOAD_DOCK_HGT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//loadDockHgt_H1
        {"RAMP_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rampAvalTxt_Y
        {"RAMP_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rampAvalTxt_N
        {"STEP_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stepAvalTxt_Y
        {"STEP_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stepAvalTxt_N
        {"INSD_STEP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//insdStepNum_H1
        {"OTSD_STEP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//otsdStepNum_H1
        {"FRONT_DOOR_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frontDoorAvalTxt_Y
        {"FRONT_DOOR_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frontDoorAvalTxt_N
        {"BACK_DOOR_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//backDoorAvalTxt_Y
        {"BACK_DOOR_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//backDoorAvalTxt_N
        {"PWR_OTLT_CONFIG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pwrOtltConfigTxt_Y
        {"PWR_OTLT_CONFIG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pwrOtltConfigTxt_N
        {"SGN_ON_BLDG_RDSD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sgnOnBldgRdsdTxt_Y
        {"SGN_ON_BLDG_RDSD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sgnOnBldgRdsdTxt_N
        {"BLDG_STRY_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bldgStryNum_H1
        {"BLDG_GURD_NTFY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bldgGurdNtfyTxt_Y
        {"BLDG_GURD_NTFY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bldgGurdNtfyTxt_N
        {"INDL_PARK_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indlParkTxt_Y
        {"INDL_PARK_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indlParkTxt_N
        {"BIZ_PARK_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizParkTxt_Y
        {"BIZ_PARK_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizParkTxt_N
        {"PRO_BLDG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proBldgTxt_Y
        {"PRO_BLDG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proBldgTxt_N
        {"SHPNG_CTR_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpngCtrTxt_Y
        {"SHPNG_CTR_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpngCtrTxt_N
        {"PVT_RES_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pvtResTxt_Y
        {"PVT_RES_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pvtResTxt_N
        {"DELY_TRNSP_OPT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyTrnspOptCd_H1
        {"DELY_TRNSP_OPT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyTrnspOptCd_CD
        {"DELY_TRNSP_OPT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyTrnspOptDescTxt_VL
        {"STAIR_CRAW_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stairCrawReqFlg_Y
        {"STAIR_CRAW_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stairCrawReqFlg_N
        {"STAIR_AND_LDG_WDT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stairAndLdgWdt_H1
        {"BLDG_ENT_DOOR_HGT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bldgEntDoorHgt_A1
        {"BLDG_ENT_DOOR_WDT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bldgEntDoorWdt_A1
        {"CRDR_WDT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crdrWdt_A1
        {"DOOR_WDT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//doorWdt_A1
        {"SHPG_INSTN_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgInstnCmntTxt_B1
        {"DELY_INSTN_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyInstnCmntTxt_B1
        {"ISTL_INSTN_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlInstnCmntTxt_B1
        {"TECH_INSTN_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techInstnCmntTxt_B1
        {"ELEV_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevAvalTxt_Y
        {"ELEV_AVAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevAvalTxt_N
        {"ELEV_AVAL_FROM_HOUR_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevAvalFromHourMn_C1
        {"XX_SCR_ITEM_7_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem7Txt_EF
        {"XX_TM_FRAME_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTmFrameTxt_EF
        {"ELEV_AVAL_TO_HOUR_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevAvalToHourMn_C1
        {"XX_SCR_ITEM_7_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem7Txt_ET
        {"XX_TM_FRAME_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTmFrameTxt_ET
        {"ELEV_APPT_REQ_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevApptReqTxt_Y
        {"ELEV_APPT_REQ_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevApptReqTxt_N
        {"ELEV_CTAC_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevCtacPsnNm_C1
        {"ELEV_CTAC_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevCtacTelNum_C1
        {"ELEV_WDT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevWdt_C1
        {"ELEV_DEPTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevDepthNum_C1
        {"ELEV_CAP_WT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevCapWt_C1
        {"ELEV_DOOR_HGT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevDoorHgt_C1
        {"ELEV_DOOR_WDT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//elevDoorWdt_C1
        {"DELY_INSTN_ADDL_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyInstnAddlCmntTxt_D1
        {"VLD_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vldUsrId_E1
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_E1
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_E1
        {"CUST_INFO_BO_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custInfoBoFlg_Y
        {"CUST_INFO_BO_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custInfoBoFlg_N
        {"PICK_UP_XTR_TONER_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickUpXtrTonerFlg_Y
        {"PICK_UP_XTR_TONER_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickUpXtrTonerFlg_N
        {"PICK_UP_AT_SAME_TM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickUpAtSameTmFlg_Y
        {"PICK_UP_AT_SAME_TM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickUpAtSameTmFlg_N
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_P1
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_P1
        {"CONFIG_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configCatgCd_P1
        {"CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd_S1
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_S1
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

