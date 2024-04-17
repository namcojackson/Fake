//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170703172120000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0010_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLGL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLGL0010 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0010_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WMS_SO_ID_B1*/
	public final EZDBStringItem              wmsSoId_B1;

    /** FILL_40_TXT_B1*/
	public final EZDBStringItem              fill40Txt_B1;

    /** WMS_ORD_NUM_B1*/
	public final EZDBStringItem              wmsOrdNum_B1;

    /** ALT_DOC_NUM_B1*/
	public final EZDBStringItem              altDocNum_B1;

    /** WMS_PRINT_DT_TM_TS_B1*/
	public final EZDBStringItem              wmsPrintDtTmTs_B1;

    /** XX_DT_TM_B1*/
	public final EZDBStringItem              xxDtTm_B1;

    /** EST_SHIP_DT_TM_TS_B1*/
	public final EZDBStringItem              estShipDtTmTs_B1;

    /** XX_DT_TM_B2*/
	public final EZDBStringItem              xxDtTm_B2;

    /** WMS_RQST_DT_TM_TS_B1*/
	public final EZDBStringItem              wmsRqstDtTmTs_B1;

    /** XX_DT_TM_B3*/
	public final EZDBStringItem              xxDtTm_B3;

    /** SHIP_TO_CUST_CD_B1*/
	public final EZDBStringItem              shipToCustCd_B1;

    /** CHRG_TO_CUST_CD_B1*/
	public final EZDBStringItem              chrgToCustCd_B1;

    /** CUST_ORD_NUM_B1*/
	public final EZDBStringItem              custOrdNum_B1;

    /** FRT_OUT_CD_B1*/
	public final EZDBStringItem              frtOutCd_B1;

    /** SO_SHIP_VIA_CD_B1*/
	public final EZDBStringItem              soShipViaCd_B1;

    /** EDI_TRNSP_TP_CD_B1*/
	public final EZDBStringItem              ediTrnspTpCd_B1;

    /** WMS_DEPT_CD_B1*/
	public final EZDBStringItem              wmsDeptCd_B1;

    /** IND_ASN_FLG_B1*/
	public final EZDBStringItem              indAsnFlg_B1;

    /** IND_SCC14_FLG_B1*/
	public final EZDBStringItem              indScc14Flg_B1;

    /** IND_UCC_FLG_B1*/
	public final EZDBStringItem              indUccFlg_B1;

    /** WMS_CONSL_FLG_B1*/
	public final EZDBStringItem              wmsConslFlg_B1;

    /** CONSL_SO_ID_B1*/
	public final EZDBStringItem              conslSoId_B1;

    /** TOT_SHIP_PRC_AMT_NUM_B1*/
	public final EZDBBigDecimalItem              totShipPrcAmtNum_B1;

    /** WMS_RESRC_TXT_B1*/
	public final EZDBStringItem              wmsResrcTxt_B1;

    /** SHIP_DT_TM_TS_B1*/
	public final EZDBStringItem              shipDtTmTs_B1;

    /** XX_DT_TM_B4*/
	public final EZDBStringItem              xxDtTm_B4;

    /** WMS_CANC_DT_TM_TS_B1*/
	public final EZDBStringItem              wmsCancDtTmTs_B1;

    /** XX_DT_TM_B5*/
	public final EZDBStringItem              xxDtTm_B5;

    /** MIX_PLT_PLT_NOTE_TXT_B1*/
	public final EZDBStringItem              mixPltPltNoteTxt_B1;

    /** ASG_SHIP_VIA_CD_B1*/
	public final EZDBStringItem              asgShipViaCd_B1;

    /** ASG_PRTY_CD_B1*/
	public final EZDBStringItem              asgPrtyCd_B1;

    /** IND_SGN_REQ_FLG_B1*/
	public final EZDBStringItem              indSgnReqFlg_B1;

    /** RTRN_LB_CD_B1*/
	public final EZDBStringItem              rtrnLbCd_B1;

    /** RTL_WH_CD_B1*/
	public final EZDBStringItem              rtlWhCd_B1;

    /** INVTY_OWNR_CD_B1*/
	public final EZDBStringItem              invtyOwnrCd_B1;

    /** SCHD_DELY_DT_B1*/
	public final EZDBDateItem              schdDelyDt_B1;

    /** CARR_CD_B1*/
	public final EZDBStringItem              carrCd_B1;

    /** SHPG_SVC_LVL_CD_B1*/
	public final EZDBStringItem              shpgSvcLvlCd_B1;

    /** RTRN_ITEM_INCL_FLG_B1*/
	public final EZDBStringItem              rtrnItemInclFlg_B1;

    /** SVC_CONFIG_MSTR_PK_B1*/
	public final EZDBBigDecimalItem              svcConfigMstrPk_B1;

    /** ASM_REQ_FLG_B1*/
	public final EZDBStringItem              asmReqFlg_B1;


	/**
	 * NLGL0010_BBMsg is constructor.
	 * The initialization when the instance of NLGL0010_BBMsg is generated.
	 */
	public NLGL0010_BBMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0010_BBMsg is constructor.
	 * The initialization when the instance of NLGL0010_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0010_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wmsSoId_B1 = (EZDBStringItem)newItem("wmsSoId_B1");
		fill40Txt_B1 = (EZDBStringItem)newItem("fill40Txt_B1");
		wmsOrdNum_B1 = (EZDBStringItem)newItem("wmsOrdNum_B1");
		altDocNum_B1 = (EZDBStringItem)newItem("altDocNum_B1");
		wmsPrintDtTmTs_B1 = (EZDBStringItem)newItem("wmsPrintDtTmTs_B1");
		xxDtTm_B1 = (EZDBStringItem)newItem("xxDtTm_B1");
		estShipDtTmTs_B1 = (EZDBStringItem)newItem("estShipDtTmTs_B1");
		xxDtTm_B2 = (EZDBStringItem)newItem("xxDtTm_B2");
		wmsRqstDtTmTs_B1 = (EZDBStringItem)newItem("wmsRqstDtTmTs_B1");
		xxDtTm_B3 = (EZDBStringItem)newItem("xxDtTm_B3");
		shipToCustCd_B1 = (EZDBStringItem)newItem("shipToCustCd_B1");
		chrgToCustCd_B1 = (EZDBStringItem)newItem("chrgToCustCd_B1");
		custOrdNum_B1 = (EZDBStringItem)newItem("custOrdNum_B1");
		frtOutCd_B1 = (EZDBStringItem)newItem("frtOutCd_B1");
		soShipViaCd_B1 = (EZDBStringItem)newItem("soShipViaCd_B1");
		ediTrnspTpCd_B1 = (EZDBStringItem)newItem("ediTrnspTpCd_B1");
		wmsDeptCd_B1 = (EZDBStringItem)newItem("wmsDeptCd_B1");
		indAsnFlg_B1 = (EZDBStringItem)newItem("indAsnFlg_B1");
		indScc14Flg_B1 = (EZDBStringItem)newItem("indScc14Flg_B1");
		indUccFlg_B1 = (EZDBStringItem)newItem("indUccFlg_B1");
		wmsConslFlg_B1 = (EZDBStringItem)newItem("wmsConslFlg_B1");
		conslSoId_B1 = (EZDBStringItem)newItem("conslSoId_B1");
		totShipPrcAmtNum_B1 = (EZDBBigDecimalItem)newItem("totShipPrcAmtNum_B1");
		wmsResrcTxt_B1 = (EZDBStringItem)newItem("wmsResrcTxt_B1");
		shipDtTmTs_B1 = (EZDBStringItem)newItem("shipDtTmTs_B1");
		xxDtTm_B4 = (EZDBStringItem)newItem("xxDtTm_B4");
		wmsCancDtTmTs_B1 = (EZDBStringItem)newItem("wmsCancDtTmTs_B1");
		xxDtTm_B5 = (EZDBStringItem)newItem("xxDtTm_B5");
		mixPltPltNoteTxt_B1 = (EZDBStringItem)newItem("mixPltPltNoteTxt_B1");
		asgShipViaCd_B1 = (EZDBStringItem)newItem("asgShipViaCd_B1");
		asgPrtyCd_B1 = (EZDBStringItem)newItem("asgPrtyCd_B1");
		indSgnReqFlg_B1 = (EZDBStringItem)newItem("indSgnReqFlg_B1");
		rtrnLbCd_B1 = (EZDBStringItem)newItem("rtrnLbCd_B1");
		rtlWhCd_B1 = (EZDBStringItem)newItem("rtlWhCd_B1");
		invtyOwnrCd_B1 = (EZDBStringItem)newItem("invtyOwnrCd_B1");
		schdDelyDt_B1 = (EZDBDateItem)newItem("schdDelyDt_B1");
		carrCd_B1 = (EZDBStringItem)newItem("carrCd_B1");
		shpgSvcLvlCd_B1 = (EZDBStringItem)newItem("shpgSvcLvlCd_B1");
		rtrnItemInclFlg_B1 = (EZDBStringItem)newItem("rtrnItemInclFlg_B1");
		svcConfigMstrPk_B1 = (EZDBBigDecimalItem)newItem("svcConfigMstrPk_B1");
		asmReqFlg_B1 = (EZDBStringItem)newItem("asmReqFlg_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0010_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0010_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wmsSoId_B1", "wmsSoId_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"fill40Txt_B1", "fill40Txt_B1", "B1", null, TYPE_HANKAKUEISU, "40", null},
	{"wmsOrdNum_B1", "wmsOrdNum_B1", "B1", null, TYPE_HANKAKUEISU, "15", null},
	{"altDocNum_B1", "altDocNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsPrintDtTmTs_B1", "wmsPrintDtTmTs_B1", "B1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTm_B1", "xxDtTm_B1", "B1", null, TYPE_HANKAKUEISU, "23", null},
	{"estShipDtTmTs_B1", "estShipDtTmTs_B1", "B1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTm_B2", "xxDtTm_B2", "B2", null, TYPE_HANKAKUEISU, "23", null},
	{"wmsRqstDtTmTs_B1", "wmsRqstDtTmTs_B1", "B1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTm_B3", "xxDtTm_B3", "B3", null, TYPE_HANKAKUEISU, "23", null},
	{"shipToCustCd_B1", "shipToCustCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"chrgToCustCd_B1", "chrgToCustCd_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"custOrdNum_B1", "custOrdNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"frtOutCd_B1", "frtOutCd_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"soShipViaCd_B1", "soShipViaCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"ediTrnspTpCd_B1", "ediTrnspTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"wmsDeptCd_B1", "wmsDeptCd_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	{"indAsnFlg_B1", "indAsnFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"indScc14Flg_B1", "indScc14Flg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"indUccFlg_B1", "indUccFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"wmsConslFlg_B1", "wmsConslFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"conslSoId_B1", "conslSoId_B1", "B1", null, TYPE_HANKAKUEISU, "15", null},
	{"totShipPrcAmtNum_B1", "totShipPrcAmtNum_B1", "B1", null, TYPE_SEISU_SYOSU, "15", "2"},
	{"wmsResrcTxt_B1", "wmsResrcTxt_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"shipDtTmTs_B1", "shipDtTmTs_B1", "B1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTm_B4", "xxDtTm_B4", "B4", null, TYPE_HANKAKUEISU, "23", null},
	{"wmsCancDtTmTs_B1", "wmsCancDtTmTs_B1", "B1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTm_B5", "xxDtTm_B5", "B5", null, TYPE_HANKAKUEISU, "23", null},
	{"mixPltPltNoteTxt_B1", "mixPltPltNoteTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"asgShipViaCd_B1", "asgShipViaCd_B1", "B1", null, TYPE_HANKAKUEISU, "40", null},
	{"asgPrtyCd_B1", "asgPrtyCd_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"indSgnReqFlg_B1", "indSgnReqFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"rtrnLbCd_B1", "rtrnLbCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"rtlWhCd_B1", "rtlWhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyOwnrCd_B1", "invtyOwnrCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"schdDelyDt_B1", "schdDelyDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"carrCd_B1", "carrCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"shpgSvcLvlCd_B1", "shpgSvcLvlCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"rtrnItemInclFlg_B1", "rtrnItemInclFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"svcConfigMstrPk_B1", "svcConfigMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"asmReqFlg_B1", "asmReqFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WMS_SO_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsSoId_B1
        {"FILL_40_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fill40Txt_B1
        {"WMS_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsOrdNum_B1
        {"ALT_DOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//altDocNum_B1
        {"WMS_PRINT_DT_TM_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsPrintDtTmTs_B1
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_B1
        {"EST_SHIP_DT_TM_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estShipDtTmTs_B1
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_B2
        {"WMS_RQST_DT_TM_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsRqstDtTmTs_B1
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_B3
        {"SHIP_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_B1
        {"CHRG_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//chrgToCustCd_B1
        {"CUST_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custOrdNum_B1
        {"FRT_OUT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtOutCd_B1
        {"SO_SHIP_VIA_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soShipViaCd_B1
        {"EDI_TRNSP_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediTrnspTpCd_B1
        {"WMS_DEPT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsDeptCd_B1
        {"IND_ASN_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indAsnFlg_B1
        {"IND_SCC14_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indScc14Flg_B1
        {"IND_UCC_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indUccFlg_B1
        {"WMS_CONSL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsConslFlg_B1
        {"CONSL_SO_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslSoId_B1
        {"TOT_SHIP_PRC_AMT_NUM",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//totShipPrcAmtNum_B1
        {"WMS_RESRC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsResrcTxt_B1
        {"SHIP_DT_TM_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipDtTmTs_B1
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_B4
        {"WMS_CANC_DT_TM_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsCancDtTmTs_B1
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_B5
        {"MIX_PLT_PLT_NOTE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mixPltPltNoteTxt_B1
        {"ASG_SHIP_VIA_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgShipViaCd_B1
        {"ASG_PRTY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgPrtyCd_B1
        {"IND_SGN_REQ_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indSgnReqFlg_B1
        {"RTRN_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnLbCd_B1
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_B1
        {"INVTY_OWNR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOwnrCd_B1
        {"SCHD_DELY_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//schdDelyDt_B1
        {"CARR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_B1
        {"SHPG_SVC_LVL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_B1
        {"RTRN_ITEM_INCL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnItemInclFlg_B1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_B1
        {"ASM_REQ_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asmReqFlg_B1
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

