//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160122113402000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0050CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFAL0050;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFAL0050 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFAL0050CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime*/
	public final EZDCStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDCStringItem              ezUpTimeZone;

    /** SYS_SRC_CD_1*/
	public final EZDCStringItemArray              sysSrcCd_1;

    /** SYS_SRC_CD_2*/
	public final EZDCStringItemArray              sysSrcCd_2;

    /** SYS_SRC_CD_3*/
	public final EZDCStringItem              sysSrcCd_3;

    /** SYS_SRC_CD_T*/
	public final EZDCStringItem              sysSrcCd_T;

    /** TRX_CD_1*/
	public final EZDCStringItemArray              trxCd_1;

    /** TRX_CD_2*/
	public final EZDCStringItemArray              trxCd_2;

    /** TRX_CD_3*/
	public final EZDCStringItem              trxCd_3;

    /** TRX_CD_T*/
	public final EZDCStringItem              trxCd_T;

    /** TRX_RSN_CD_1*/
	public final EZDCStringItemArray              trxRsnCd_1;

    /** TRX_RSN_CD_2*/
	public final EZDCStringItemArray              trxRsnCd_2;

    /** TRX_RSN_CD_3*/
	public final EZDCStringItem              trxRsnCd_3;

    /** TRX_RSN_CD_T*/
	public final EZDCStringItem              trxRsnCd_T;

    /** AJE_ID*/
	public final EZDCStringItem              ajeId;

    /** AJE_ID_T*/
	public final EZDCStringItem              ajeId_T;

    /** SYS_SRC_NM*/
	public final EZDCStringItem              sysSrcNm;

    /** TRX_NM*/
	public final EZDCStringItem              trxNm;

    /** TRX_RSN_NM*/
	public final EZDCStringItem              trxRsnNm;

    /** AJE_PTRN_IND_TP_CD_01*/
	public final EZDCStringItem              ajePtrnIndTpCd_01;

    /** AJE_PTRN_IND_TP_CD_1T*/
	public final EZDCStringItem              ajePtrnIndTpCd_1T;

    /** AJE_PTRN_IND_TP_CD_02*/
	public final EZDCStringItem              ajePtrnIndTpCd_02;

    /** AJE_PTRN_IND_TP_CD_2T*/
	public final EZDCStringItem              ajePtrnIndTpCd_2T;

    /** AJE_PTRN_IND_TP_CD_03*/
	public final EZDCStringItem              ajePtrnIndTpCd_03;

    /** AJE_PTRN_IND_TP_CD_3T*/
	public final EZDCStringItem              ajePtrnIndTpCd_3T;

    /** AJE_PTRN_ACTL_CD_01*/
	public final EZDCStringItem              ajePtrnActlCd_01;

    /** AJE_PTRN_ACTL_CD_1T*/
	public final EZDCStringItem              ajePtrnActlCd_1T;

    /** AJE_PTRN_ACTL_CD_02*/
	public final EZDCStringItem              ajePtrnActlCd_02;

    /** AJE_PTRN_ACTL_CD_2T*/
	public final EZDCStringItem              ajePtrnActlCd_2T;

    /** AJE_PTRN_ACTL_CD_03*/
	public final EZDCStringItem              ajePtrnActlCd_03;

    /** AJE_PTRN_ACTL_CD_3T*/
	public final EZDCStringItem              ajePtrnActlCd_3T;

    /** AJE_PTRN_IND_TP_CD_1C*/
	public final EZDCStringItemArray              ajePtrnIndTpCd_1C;

    /** AJE_PTRN_IND_TP_NM_1D*/
	public final EZDCStringItemArray              ajePtrnIndTpNm_1D;

    /** AJE_PTRN_IND_TP_CD_1V*/
	public final EZDCStringItem              ajePtrnIndTpCd_1V;

    /** AJE_PTRN_IND_TP_NM_1T*/
	public final EZDCStringItem              ajePtrnIndTpNm_1T;

    /** AJE_PTRN_IND_TP_CD_2C*/
	public final EZDCStringItemArray              ajePtrnIndTpCd_2C;

    /** AJE_PTRN_IND_TP_NM_2D*/
	public final EZDCStringItemArray              ajePtrnIndTpNm_2D;

    /** AJE_PTRN_IND_TP_CD_2V*/
	public final EZDCStringItem              ajePtrnIndTpCd_2V;

    /** AJE_PTRN_IND_TP_NM_2T*/
	public final EZDCStringItem              ajePtrnIndTpNm_2T;

    /** AJE_PTRN_IND_TP_CD_3C*/
	public final EZDCStringItemArray              ajePtrnIndTpCd_3C;

    /** AJE_PTRN_IND_TP_NM_3D*/
	public final EZDCStringItemArray              ajePtrnIndTpNm_3D;

    /** AJE_PTRN_IND_TP_CD_3V*/
	public final EZDCStringItem              ajePtrnIndTpCd_3V;

    /** AJE_PTRN_IND_TP_NM_3T*/
	public final EZDCStringItem              ajePtrnIndTpNm_3T;

    /** AJE_PTRN_ACTL_CD_1C*/
	public final EZDCStringItemArray              ajePtrnActlCd_1C;

    /** AJE_PTRN_ACTL_NM_1D*/
	public final EZDCStringItemArray              ajePtrnActlNm_1D;

    /** AJE_PTRN_ACTL_CD_1V*/
	public final EZDCStringItem              ajePtrnActlCd_1V;

    /** AJE_PTRN_ACTL_NM_1T*/
	public final EZDCStringItem              ajePtrnActlNm_1T;

    /** AJE_PTRN_ACTL_CD_2C*/
	public final EZDCStringItemArray              ajePtrnActlCd_2C;

    /** AJE_PTRN_ACTL_NM_2D*/
	public final EZDCStringItemArray              ajePtrnActlNm_2D;

    /** AJE_PTRN_ACTL_CD_2V*/
	public final EZDCStringItem              ajePtrnActlCd_2V;

    /** AJE_PTRN_ACTL_NM_2T*/
	public final EZDCStringItem              ajePtrnActlNm_2T;

    /** AJE_PTRN_ACTL_CD_3C*/
	public final EZDCStringItemArray              ajePtrnActlCd_3C;

    /** AJE_PTRN_ACTL_NM_3D*/
	public final EZDCStringItemArray              ajePtrnActlNm_3D;

    /** AJE_PTRN_ACTL_CD_3V*/
	public final EZDCStringItem              ajePtrnActlCd_3V;

    /** AJE_PTRN_ACTL_NM_3T*/
	public final EZDCStringItem              ajePtrnActlNm_3T;

    /** JRNL_CATG_CD*/
	public final EZDCStringItem              jrnlCatgCd;

    /** JRNL_CATG_NM*/
	public final EZDCStringItem              jrnlCatgNm;

    /** XX_CHK_BOX_AM*/
	public final EZDCStringItem              xxChkBox_AM;

    /** XX_CHK_BOX_QT*/
	public final EZDCStringItem              xxChkBox_QT;

    /** AJE_LINE_IDX_NUM_1*/
	public final EZDCStringItemArray              ajeLineIdxNum_1;

    /** AJE_LINE_IDX_NUM_2*/
	public final EZDCStringItemArray              ajeLineIdxNum_2;

    /** AJE_LINE_IDX_NUM_3*/
	public final EZDCStringItem              ajeLineIdxNum_3;

    /** AJE_COA_CMPY_CD_1*/
	public final EZDCStringItemArray              ajeCoaCmpyCd_1;

    /** AJE_COA_CMPY_CD_2*/
	public final EZDCStringItemArray              ajeCoaCmpyCd_2;

    /** AJE_COA_CMPY_CD_3*/
	public final EZDCStringItem              ajeCoaCmpyCd_3;

    /** AJE_COA_BR_CD*/
	public final EZDCStringItem              ajeCoaBrCd;

    /** AJE_LINE_IND_TP_CD_1*/
	public final EZDCStringItemArray              ajeLineIndTpCd_1;

    /** AJE_LINE_IND_TP_NM_2*/
	public final EZDCStringItemArray              ajeLineIndTpNm_2;

    /** AJE_LINE_IND_TP_CD_3*/
	public final EZDCStringItem              ajeLineIndTpCd_3;

    /** AJE_COA_CC_CD*/
	public final EZDCStringItem              ajeCoaCcCd;

    /** AJE_COA_ACCT_CD*/
	public final EZDCStringItem              ajeCoaAcctCd;

    /** AJE_LINE_IDX_DESC_TXT*/
	public final EZDCStringItem              ajeLineIdxDescTxt;

    /** AJE_COA_PROD_CD*/
	public final EZDCStringItem              ajeCoaProdCd;

    /** AJE_COA_CH_CD_1*/
	public final EZDCStringItemArray              ajeCoaChCd_1;

    /** AJE_COA_CH_CD_2*/
	public final EZDCStringItemArray              ajeCoaChCd_2;

    /** AJE_COA_CH_CD_3*/
	public final EZDCStringItem              ajeCoaChCd_3;

    /** AJE_COA_AFFL_CD*/
	public final EZDCStringItem              ajeCoaAfflCd;

    /** AJE_COA_PROJ_CD*/
	public final EZDCStringItem              ajeCoaProjCd;

    /** AJE_COA_EXTN_CD_1*/
	public final EZDCStringItemArray              ajeCoaExtnCd_1;

    /** AJE_COA_EXTN_CD_2*/
	public final EZDCStringItemArray              ajeCoaExtnCd_2;

    /** AJE_COA_EXTN_CD_3*/
	public final EZDCStringItem              ajeCoaExtnCd_3;

    /** A*/
	public final business.blap.NFAL0050.NFAL0050_ACMsgArray              A;

    /** C*/
	public final business.blap.NFAL0050.NFAL0050_CCMsgArray              C;

    /** XX_TBL_NM*/
	public final EZDCStringItem              xxTblNm;

    /** XX_EDT_CD_NM*/
	public final EZDCStringItem              xxEdtCdNm;

    /** XX_DTL_NM*/
	public final EZDCStringItem              xxDtlNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_TBL_ITEM_TXT*/
	public final EZDCStringItem              xxTblItemTxt;

    /** XX_HDR_CD_LB_NM*/
	public final EZDCStringItem              xxHdrCdLbNm;

    /** XX_HDR_NM_LB_NM*/
	public final EZDCStringItem              xxHdrNmLbNm;

    /** XX_DTL_CD_LB_NM*/
	public final EZDCStringItem              xxDtlCdLbNm;

    /** XX_DTL_NM_LB_NM*/
	public final EZDCStringItem              xxDtlNmLbNm;

    /** XX_UPLD_FILE_NM*/
	public final EZDCStringItem              xxUpldFileNm;

    /** XX_SET_FLG*/
	public final EZDCStringItem              xxSetFlg;

    /** EVENT_ID*/
	public final EZDCStringItem              eventId;

    /** EVENT_ID_P*/
	public final EZDCStringItem              eventId_P;

    /** EVENT_ID_SG*/
	public final EZDCStringItem              eventId_SG;

    /** XX_TOT_CNT_D*/
	public final EZDCBigDecimalItem              xxTotCnt_D;

    /** XX_TOT_CNT_P*/
	public final EZDCBigDecimalItem              xxTotCnt_P;

    /** MSG_TXT_INFO_TXT_A*/
	public final EZDCStringItem              msgTxtInfoTxt_A;

    /** MSG_TXT_INFO_TXT_B*/
	public final EZDCStringItem              msgTxtInfoTxt_B;

    /** MSG_TXT_INFO_TXT_P*/
	public final EZDCStringItem              msgTxtInfoTxt_P;


	/**
	 * NFAL0050CMsg is constructor.
	 * The initialization when the instance of NFAL0050CMsg is generated.
	 */
	public NFAL0050CMsg() {
		this(false, -1);
	}

	/**
	 * NFAL0050CMsg is constructor.
	 * The initialization when the instance of NFAL0050CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFAL0050CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime = (EZDCStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDCStringItem)newItem("ezUpTimeZone");
		sysSrcCd_1 = (EZDCStringItemArray)newItemArray("sysSrcCd_1");
		sysSrcCd_2 = (EZDCStringItemArray)newItemArray("sysSrcCd_2");
		sysSrcCd_3 = (EZDCStringItem)newItem("sysSrcCd_3");
		sysSrcCd_T = (EZDCStringItem)newItem("sysSrcCd_T");
		trxCd_1 = (EZDCStringItemArray)newItemArray("trxCd_1");
		trxCd_2 = (EZDCStringItemArray)newItemArray("trxCd_2");
		trxCd_3 = (EZDCStringItem)newItem("trxCd_3");
		trxCd_T = (EZDCStringItem)newItem("trxCd_T");
		trxRsnCd_1 = (EZDCStringItemArray)newItemArray("trxRsnCd_1");
		trxRsnCd_2 = (EZDCStringItemArray)newItemArray("trxRsnCd_2");
		trxRsnCd_3 = (EZDCStringItem)newItem("trxRsnCd_3");
		trxRsnCd_T = (EZDCStringItem)newItem("trxRsnCd_T");
		ajeId = (EZDCStringItem)newItem("ajeId");
		ajeId_T = (EZDCStringItem)newItem("ajeId_T");
		sysSrcNm = (EZDCStringItem)newItem("sysSrcNm");
		trxNm = (EZDCStringItem)newItem("trxNm");
		trxRsnNm = (EZDCStringItem)newItem("trxRsnNm");
		ajePtrnIndTpCd_01 = (EZDCStringItem)newItem("ajePtrnIndTpCd_01");
		ajePtrnIndTpCd_1T = (EZDCStringItem)newItem("ajePtrnIndTpCd_1T");
		ajePtrnIndTpCd_02 = (EZDCStringItem)newItem("ajePtrnIndTpCd_02");
		ajePtrnIndTpCd_2T = (EZDCStringItem)newItem("ajePtrnIndTpCd_2T");
		ajePtrnIndTpCd_03 = (EZDCStringItem)newItem("ajePtrnIndTpCd_03");
		ajePtrnIndTpCd_3T = (EZDCStringItem)newItem("ajePtrnIndTpCd_3T");
		ajePtrnActlCd_01 = (EZDCStringItem)newItem("ajePtrnActlCd_01");
		ajePtrnActlCd_1T = (EZDCStringItem)newItem("ajePtrnActlCd_1T");
		ajePtrnActlCd_02 = (EZDCStringItem)newItem("ajePtrnActlCd_02");
		ajePtrnActlCd_2T = (EZDCStringItem)newItem("ajePtrnActlCd_2T");
		ajePtrnActlCd_03 = (EZDCStringItem)newItem("ajePtrnActlCd_03");
		ajePtrnActlCd_3T = (EZDCStringItem)newItem("ajePtrnActlCd_3T");
		ajePtrnIndTpCd_1C = (EZDCStringItemArray)newItemArray("ajePtrnIndTpCd_1C");
		ajePtrnIndTpNm_1D = (EZDCStringItemArray)newItemArray("ajePtrnIndTpNm_1D");
		ajePtrnIndTpCd_1V = (EZDCStringItem)newItem("ajePtrnIndTpCd_1V");
		ajePtrnIndTpNm_1T = (EZDCStringItem)newItem("ajePtrnIndTpNm_1T");
		ajePtrnIndTpCd_2C = (EZDCStringItemArray)newItemArray("ajePtrnIndTpCd_2C");
		ajePtrnIndTpNm_2D = (EZDCStringItemArray)newItemArray("ajePtrnIndTpNm_2D");
		ajePtrnIndTpCd_2V = (EZDCStringItem)newItem("ajePtrnIndTpCd_2V");
		ajePtrnIndTpNm_2T = (EZDCStringItem)newItem("ajePtrnIndTpNm_2T");
		ajePtrnIndTpCd_3C = (EZDCStringItemArray)newItemArray("ajePtrnIndTpCd_3C");
		ajePtrnIndTpNm_3D = (EZDCStringItemArray)newItemArray("ajePtrnIndTpNm_3D");
		ajePtrnIndTpCd_3V = (EZDCStringItem)newItem("ajePtrnIndTpCd_3V");
		ajePtrnIndTpNm_3T = (EZDCStringItem)newItem("ajePtrnIndTpNm_3T");
		ajePtrnActlCd_1C = (EZDCStringItemArray)newItemArray("ajePtrnActlCd_1C");
		ajePtrnActlNm_1D = (EZDCStringItemArray)newItemArray("ajePtrnActlNm_1D");
		ajePtrnActlCd_1V = (EZDCStringItem)newItem("ajePtrnActlCd_1V");
		ajePtrnActlNm_1T = (EZDCStringItem)newItem("ajePtrnActlNm_1T");
		ajePtrnActlCd_2C = (EZDCStringItemArray)newItemArray("ajePtrnActlCd_2C");
		ajePtrnActlNm_2D = (EZDCStringItemArray)newItemArray("ajePtrnActlNm_2D");
		ajePtrnActlCd_2V = (EZDCStringItem)newItem("ajePtrnActlCd_2V");
		ajePtrnActlNm_2T = (EZDCStringItem)newItem("ajePtrnActlNm_2T");
		ajePtrnActlCd_3C = (EZDCStringItemArray)newItemArray("ajePtrnActlCd_3C");
		ajePtrnActlNm_3D = (EZDCStringItemArray)newItemArray("ajePtrnActlNm_3D");
		ajePtrnActlCd_3V = (EZDCStringItem)newItem("ajePtrnActlCd_3V");
		ajePtrnActlNm_3T = (EZDCStringItem)newItem("ajePtrnActlNm_3T");
		jrnlCatgCd = (EZDCStringItem)newItem("jrnlCatgCd");
		jrnlCatgNm = (EZDCStringItem)newItem("jrnlCatgNm");
		xxChkBox_AM = (EZDCStringItem)newItem("xxChkBox_AM");
		xxChkBox_QT = (EZDCStringItem)newItem("xxChkBox_QT");
		ajeLineIdxNum_1 = (EZDCStringItemArray)newItemArray("ajeLineIdxNum_1");
		ajeLineIdxNum_2 = (EZDCStringItemArray)newItemArray("ajeLineIdxNum_2");
		ajeLineIdxNum_3 = (EZDCStringItem)newItem("ajeLineIdxNum_3");
		ajeCoaCmpyCd_1 = (EZDCStringItemArray)newItemArray("ajeCoaCmpyCd_1");
		ajeCoaCmpyCd_2 = (EZDCStringItemArray)newItemArray("ajeCoaCmpyCd_2");
		ajeCoaCmpyCd_3 = (EZDCStringItem)newItem("ajeCoaCmpyCd_3");
		ajeCoaBrCd = (EZDCStringItem)newItem("ajeCoaBrCd");
		ajeLineIndTpCd_1 = (EZDCStringItemArray)newItemArray("ajeLineIndTpCd_1");
		ajeLineIndTpNm_2 = (EZDCStringItemArray)newItemArray("ajeLineIndTpNm_2");
		ajeLineIndTpCd_3 = (EZDCStringItem)newItem("ajeLineIndTpCd_3");
		ajeCoaCcCd = (EZDCStringItem)newItem("ajeCoaCcCd");
		ajeCoaAcctCd = (EZDCStringItem)newItem("ajeCoaAcctCd");
		ajeLineIdxDescTxt = (EZDCStringItem)newItem("ajeLineIdxDescTxt");
		ajeCoaProdCd = (EZDCStringItem)newItem("ajeCoaProdCd");
		ajeCoaChCd_1 = (EZDCStringItemArray)newItemArray("ajeCoaChCd_1");
		ajeCoaChCd_2 = (EZDCStringItemArray)newItemArray("ajeCoaChCd_2");
		ajeCoaChCd_3 = (EZDCStringItem)newItem("ajeCoaChCd_3");
		ajeCoaAfflCd = (EZDCStringItem)newItem("ajeCoaAfflCd");
		ajeCoaProjCd = (EZDCStringItem)newItem("ajeCoaProjCd");
		ajeCoaExtnCd_1 = (EZDCStringItemArray)newItemArray("ajeCoaExtnCd_1");
		ajeCoaExtnCd_2 = (EZDCStringItemArray)newItemArray("ajeCoaExtnCd_2");
		ajeCoaExtnCd_3 = (EZDCStringItem)newItem("ajeCoaExtnCd_3");
		A = (business.blap.NFAL0050.NFAL0050_ACMsgArray)newMsgArray("A");
		C = (business.blap.NFAL0050.NFAL0050_CCMsgArray)newMsgArray("C");
		xxTblNm = (EZDCStringItem)newItem("xxTblNm");
		xxEdtCdNm = (EZDCStringItem)newItem("xxEdtCdNm");
		xxDtlNm = (EZDCStringItem)newItem("xxDtlNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxTblItemTxt = (EZDCStringItem)newItem("xxTblItemTxt");
		xxHdrCdLbNm = (EZDCStringItem)newItem("xxHdrCdLbNm");
		xxHdrNmLbNm = (EZDCStringItem)newItem("xxHdrNmLbNm");
		xxDtlCdLbNm = (EZDCStringItem)newItem("xxDtlCdLbNm");
		xxDtlNmLbNm = (EZDCStringItem)newItem("xxDtlNmLbNm");
		xxUpldFileNm = (EZDCStringItem)newItem("xxUpldFileNm");
		xxSetFlg = (EZDCStringItem)newItem("xxSetFlg");
		eventId = (EZDCStringItem)newItem("eventId");
		eventId_P = (EZDCStringItem)newItem("eventId_P");
		eventId_SG = (EZDCStringItem)newItem("eventId_SG");
		xxTotCnt_D = (EZDCBigDecimalItem)newItem("xxTotCnt_D");
		xxTotCnt_P = (EZDCBigDecimalItem)newItem("xxTotCnt_P");
		msgTxtInfoTxt_A = (EZDCStringItem)newItem("msgTxtInfoTxt_A");

		msgTxtInfoTxt_B = (EZDCStringItem)newItem("msgTxtInfoTxt_B");
		msgTxtInfoTxt_P = (EZDCStringItem)newItem("msgTxtInfoTxt_P");
	}

	/**
	 * get the type of array which is stored
	 * @return NFAL0050CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFAL0050CMsgArray();
	}

	/**
	 * Definition of Sub class for Schema Item Array(Basic data,Visible Field)
	 */
	private static class NFAL0050CMsgSchemaContents1 {

		/**
		 * Array of Schema Items in Sub class(Basic data)
		 */
		private static final String[][] BASE_CONTENTS = {
		//["key name", "variable", "suffix", "array length", "item type", "digits", "after decimal point degits"] <- This is how to define.
			{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
			{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
			{"sysSrcCd_1", "sysSrcCd_1", "1", "99", TYPE_HANKAKUEISU, "3", null},
			{"sysSrcCd_2", "sysSrcCd_2", "2", "99", TYPE_HANKAKUEISU, "3", null},
			{"sysSrcCd_3", "sysSrcCd_3", "3", null, TYPE_HANKAKUEISU, "3", null},
			{"sysSrcCd_T", "sysSrcCd_T", "T", null, TYPE_HANKAKUEISU, "3", null},
			{"trxCd_1", "trxCd_1", "1", "99", TYPE_HANKAKUEISU, "3", null},
			{"trxCd_2", "trxCd_2", "2", "99", TYPE_HANKAKUEISU, "3", null},
			{"trxCd_3", "trxCd_3", "3", null, TYPE_HANKAKUEISU, "3", null},
			{"trxCd_T", "trxCd_T", "T", null, TYPE_HANKAKUEISU, "3", null},
			{"trxRsnCd_1", "trxRsnCd_1", "1", "99", TYPE_HANKAKUEISU, "2", null},
			{"trxRsnCd_2", "trxRsnCd_2", "2", "99", TYPE_HANKAKUEISU, "2", null},
			{"trxRsnCd_3", "trxRsnCd_3", "3", null, TYPE_HANKAKUEISU, "2", null},
			{"trxRsnCd_T", "trxRsnCd_T", "T", null, TYPE_HANKAKUEISU, "2", null},
			{"ajeId", "ajeId", null, null, TYPE_HANKAKUEISU, "10", null},
			{"ajeId_T", "ajeId_T", "T", null, TYPE_HANKAKUEISU, "10", null},
			{"sysSrcNm", "sysSrcNm", null, null, TYPE_HANKAKUEISU, "240", null},
			{"trxNm", "trxNm", null, null, TYPE_HANKAKUEISU, "30", null},
			{"trxRsnNm", "trxRsnNm", null, null, TYPE_HANKAKUEISU, "50", null},
			{"ajePtrnIndTpCd_01", "ajePtrnIndTpCd_01", "01", null, TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnIndTpCd_1T", "ajePtrnIndTpCd_1T", "1T", null, TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnIndTpCd_02", "ajePtrnIndTpCd_02", "02", null, TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnIndTpCd_2T", "ajePtrnIndTpCd_2T", "2T", null, TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnIndTpCd_03", "ajePtrnIndTpCd_03", "03", null, TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnIndTpCd_3T", "ajePtrnIndTpCd_3T", "3T", null, TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnActlCd_01", "ajePtrnActlCd_01", "01", null, TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnActlCd_1T", "ajePtrnActlCd_1T", "1T", null, TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnActlCd_02", "ajePtrnActlCd_02", "02", null, TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnActlCd_2T", "ajePtrnActlCd_2T", "2T", null, TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnActlCd_03", "ajePtrnActlCd_03", "03", null, TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnActlCd_3T", "ajePtrnActlCd_3T", "3T", null, TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnIndTpCd_1C", "ajePtrnIndTpCd_1C", "1C", "99", TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnIndTpNm_1D", "ajePtrnIndTpNm_1D", "1D", "99", TYPE_HANKAKUEISU, "100", null},
			{"ajePtrnIndTpCd_1V", "ajePtrnIndTpCd_1V", "1V", null, TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnIndTpNm_1T", "ajePtrnIndTpNm_1T", "1T", null, TYPE_HANKAKUEISU, "100", null},
			{"ajePtrnIndTpCd_2C", "ajePtrnIndTpCd_2C", "2C", "99", TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnIndTpNm_2D", "ajePtrnIndTpNm_2D", "2D", "99", TYPE_HANKAKUEISU, "100", null},
			{"ajePtrnIndTpCd_2V", "ajePtrnIndTpCd_2V", "2V", null, TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnIndTpNm_2T", "ajePtrnIndTpNm_2T", "2T", null, TYPE_HANKAKUEISU, "100", null},
			{"ajePtrnIndTpCd_3C", "ajePtrnIndTpCd_3C", "3C", "99", TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnIndTpNm_3D", "ajePtrnIndTpNm_3D", "3D", "99", TYPE_HANKAKUEISU, "100", null},
			{"ajePtrnIndTpCd_3V", "ajePtrnIndTpCd_3V", "3V", null, TYPE_HANKAKUEISU, "3", null},
			{"ajePtrnIndTpNm_3T", "ajePtrnIndTpNm_3T", "3T", null, TYPE_HANKAKUEISU, "100", null},
			{"ajePtrnActlCd_1C", "ajePtrnActlCd_1C", "1C", "99", TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnActlNm_1D", "ajePtrnActlNm_1D", "1D", "99", TYPE_HANKAKUEISU, "70", null},
			{"ajePtrnActlCd_1V", "ajePtrnActlCd_1V", "1V", null, TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnActlNm_1T", "ajePtrnActlNm_1T", "1T", null, TYPE_HANKAKUEISU, "70", null},
			{"ajePtrnActlCd_2C", "ajePtrnActlCd_2C", "2C", "99", TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnActlNm_2D", "ajePtrnActlNm_2D", "2D", "99", TYPE_HANKAKUEISU, "70", null},
			{"ajePtrnActlCd_2V", "ajePtrnActlCd_2V", "2V", null, TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnActlNm_2T", "ajePtrnActlNm_2T", "2T", null, TYPE_HANKAKUEISU, "70", null},
			{"ajePtrnActlCd_3C", "ajePtrnActlCd_3C", "3C", "99", TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnActlNm_3D", "ajePtrnActlNm_3D", "3D", "99", TYPE_HANKAKUEISU, "70", null},
			{"ajePtrnActlCd_3V", "ajePtrnActlCd_3V", "3V", null, TYPE_HANKAKUEISU, "20", null},
			{"ajePtrnActlNm_3T", "ajePtrnActlNm_3T", "3T", null, TYPE_HANKAKUEISU, "70", null},
			{"jrnlCatgCd", "jrnlCatgCd", null, null, TYPE_HANKAKUEISU, "3", null},
			{"jrnlCatgNm", "jrnlCatgNm", null, null, TYPE_HANKAKUEISU, "25", null},
			{"xxChkBox_AM", "xxChkBox_AM", "AM", null, TYPE_HANKAKUEIJI, "1", null},
			{"xxChkBox_QT", "xxChkBox_QT", "QT", null, TYPE_HANKAKUEIJI, "1", null},
			{"ajeLineIdxNum_1", "ajeLineIdxNum_1", "1", "99", TYPE_HANKAKUEISU, "2", null},
			{"ajeLineIdxNum_2", "ajeLineIdxNum_2", "2", "99", TYPE_HANKAKUEISU, "2", null},
			{"ajeLineIdxNum_3", "ajeLineIdxNum_3", "3", null, TYPE_HANKAKUEISU, "2", null},
			{"ajeCoaCmpyCd_1", "ajeCoaCmpyCd_1", "1", "99", TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaCmpyCd_2", "ajeCoaCmpyCd_2", "2", "99", TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaCmpyCd_3", "ajeCoaCmpyCd_3", "3", null, TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaBrCd", "ajeCoaBrCd", null, null, TYPE_HANKAKUEISU, "20", null},
			{"ajeLineIndTpCd_1", "ajeLineIndTpCd_1", "1", "99", TYPE_HANKAKUEISU, "2", null},
			{"ajeLineIndTpNm_2", "ajeLineIndTpNm_2", "2", "99", TYPE_HANKAKUEISU, "30", null},
			{"ajeLineIndTpCd_3", "ajeLineIndTpCd_3", "3", null, TYPE_HANKAKUEISU, "2", null},
			{"ajeCoaCcCd", "ajeCoaCcCd", null, null, TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaAcctCd", "ajeCoaAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
			{"ajeLineIdxDescTxt", "ajeLineIdxDescTxt", null, null, TYPE_HANKAKUEISU, "240", null},
			{"ajeCoaProdCd", "ajeCoaProdCd", null, null, TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaChCd_1", "ajeCoaChCd_1", "1", "99", TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaChCd_2", "ajeCoaChCd_2", "2", "99", TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaChCd_3", "ajeCoaChCd_3", "3", null, TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaAfflCd", "ajeCoaAfflCd", null, null, TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaProjCd", "ajeCoaProjCd", null, null, TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaExtnCd_1", "ajeCoaExtnCd_1", "1", "99", TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaExtnCd_2", "ajeCoaExtnCd_2", "2", "99", TYPE_HANKAKUEISU, "20", null},
			{"ajeCoaExtnCd_3", "ajeCoaExtnCd_3", "3", null, TYPE_HANKAKUEISU, "20", null},
			{"A", "A", null, "18", "business.blap.NFAL0050.NFAL0050_ACMsgArray", null, null},
			{"C", "C", null, "18", "business.blap.NFAL0050.NFAL0050_CCMsgArray", null, null},
			{"xxTblNm", "xxTblNm", null, null, TYPE_HANKAKUEISU, "30", null},
			{"xxEdtCdNm", "xxEdtCdNm", null, null, TYPE_HANKAKUEISU, "100", null},
			{"xxDtlNm", "xxDtlNm", null, null, TYPE_HANKAKUEISU, "70", null},
			{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
			{"xxTblItemTxt", "xxTblItemTxt", null, null, TYPE_HANKAKUEISU, "35", null},
			{"xxHdrCdLbNm", "xxHdrCdLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
			{"xxHdrNmLbNm", "xxHdrNmLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
			{"xxDtlCdLbNm", "xxDtlCdLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
			{"xxDtlNmLbNm", "xxDtlNmLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
			{"xxUpldFileNm", "xxUpldFileNm", null, null, TYPE_KONZAI, "256", null},
			{"xxSetFlg", "xxSetFlg", null, null, TYPE_HANKAKUEISU, "1", null},
			{"eventId", "eventId", null, null, TYPE_HANKAKUEISU, "32", null},
			{"eventId_P", "eventId_P", "P", null, TYPE_HANKAKUEISU, "32", null},
			{"eventId_SG", "eventId_SG", "SG", null, TYPE_HANKAKUEISU, "32", null},
			{"xxTotCnt_D", "xxTotCnt_D", "D", null, TYPE_SEISU_SYOSU, "10", "0"},
			{"xxTotCnt_P", "xxTotCnt_P", "P", null, TYPE_SEISU_SYOSU, "10", "0"},
			{"msgTxtInfoTxt_A", "msgTxtInfoTxt_A", "A", null, TYPE_HANKAKUEISU, "65", null},
		};

		/**
		 * Array of Schema Items in Sub class(Visible Field)
		 */
		private static final String[][] DISP_CONTENTS = {
		// ["message display string","mandotry item", "minimum", "maximum", "I/O type", "yen", "comma", "zero suppress", "plus display flag", "display length", "display length after decimal point digit", "Dividing date", "Year display pattern", "zero pudding for date", "separator for date"] <- This is how to define
	        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
	        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
	        {"SYS_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcCd_1
	        {"SYS_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcCd_2
	        {"SYS_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcCd_3
	        {"SYS_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcCd_T
	        {"TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxCd_1
	        {"TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxCd_2
	        {"TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxCd_3
	        {"TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxCd_T
	        {"TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnCd_1
	        {"TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnCd_2
	        {"TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnCd_3
	        {"TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnCd_T
	        {"AJE_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeId
	        {"AJE_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeId_T
	        {"SYS_SRC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcNm
	        {"TRX_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxNm
	        {"TRX_RSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnNm
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_01
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_1T
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_02
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_2T
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_03
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_3T
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_01
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_1T
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_02
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_2T
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_03
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_3T
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_1C
	        {"AJE_PTRN_IND_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm_1D
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_1V
	        {"AJE_PTRN_IND_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm_1T
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_2C
	        {"AJE_PTRN_IND_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm_2D
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_2V
	        {"AJE_PTRN_IND_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm_2T
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_3C
	        {"AJE_PTRN_IND_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm_3D
	        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_3V
	        {"AJE_PTRN_IND_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm_3T
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_1C
	        {"AJE_PTRN_ACTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm_1D
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_1V
	        {"AJE_PTRN_ACTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm_1T
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_2C
	        {"AJE_PTRN_ACTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm_2D
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_2V
	        {"AJE_PTRN_ACTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm_2T
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_3C
	        {"AJE_PTRN_ACTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm_3D
	        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_3V
	        {"AJE_PTRN_ACTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm_3T
	        {"JRNL_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jrnlCatgCd
	        {"JRNL_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jrnlCatgNm
	        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AM
	        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_QT
	        {"AJE_LINE_IDX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeLineIdxNum_1
	        {"AJE_LINE_IDX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeLineIdxNum_2
	        {"AJE_LINE_IDX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeLineIdxNum_3
	        {"AJE_COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaCmpyCd_1
	        {"AJE_COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaCmpyCd_2
	        {"AJE_COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaCmpyCd_3
	        {"AJE_COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaBrCd
	        {"AJE_LINE_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeLineIndTpCd_1
	        {"AJE_LINE_IND_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeLineIndTpNm_2
	        {"AJE_LINE_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeLineIndTpCd_3
	        {"AJE_COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaCcCd
	        {"AJE_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaAcctCd
	        {"AJE_LINE_IDX_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeLineIdxDescTxt
	        {"AJE_COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaProdCd
	        {"AJE_COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaChCd_1
	        {"AJE_COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaChCd_2
	        {"AJE_COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaChCd_3
	        {"AJE_COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaAfflCd
	        {"AJE_COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaProjCd
	        {"AJE_COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaExtnCd_1
	        {"AJE_COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaExtnCd_2
	        {"AJE_COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaExtnCd_3
			null,	//A
			null,	//C
	        {"XX_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm
	        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm
	        {"XX_DTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNm
	        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
	        {"XX_TBL_ITEM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblItemTxt
	        {"XX_HDR_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrCdLbNm
	        {"XX_HDR_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNmLbNm
	        {"XX_DTL_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCdLbNm
	        {"XX_DTL_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNmLbNm
	        {"XX_UPLD_FILE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUpldFileNm
	        {"XX_SET_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSetFlg
	        {"EVENT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eventId
	        {"EVENT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eventId_P
	        {"EVENT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eventId_SG
	        {"XX_TOT_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotCnt_D
	        {"XX_TOT_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotCnt_P
	        {"MSG_TXT_INFO_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//msgTxtInfoTxt_A
		};

	}

	private static class NFAL0050CMsgSchemaContents2 {

		/**
		 * Array of Schema Items in Sub class(Basic data)
		 */
		private static final String[][] BASE_CONTENTS = {
		//["key name", "variable", "suffix", "array length", "item type", "digits", "after decimal point degits"] <- This is how to define.
			{"msgTxtInfoTxt_B", "msgTxtInfoTxt_B", "B", null, TYPE_HANKAKUEISU, "65", null},
			{"msgTxtInfoTxt_P", "msgTxtInfoTxt_P", "P", null, TYPE_HANKAKUEISU, "65", null},
		};

		/**
		 * Array of Schema Items in Sub class(Visible Field)
		 */
		private static final String[][] DISP_CONTENTS = {
		// ["message display string","mandotry item", "minimum", "maximum", "I/O type", "yen", "comma", "zero suppress", "plus display flag", "display length", "display length after decimal point digit", "Dividing date", "Year display pattern", "zero pudding for date", "separator for date"] <- This is how to define
	        {"MSG_TXT_INFO_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//msgTxtInfoTxt_B
	        {"MSG_TXT_INFO_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//msgTxtInfoTxt_P
		};

	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = new String[102][NFAL0050CMsgSchemaContents1.BASE_CONTENTS[0].length];
	static {
		int arcpStartPoint=0;
	System.arraycopy(NFAL0050CMsgSchemaContents1.BASE_CONTENTS, 0, BASE_CONTENTS, arcpStartPoint, NFAL0050CMsgSchemaContents1.BASE_CONTENTS.length);
	arcpStartPoint = arcpStartPoint + NFAL0050CMsgSchemaContents1.BASE_CONTENTS.length;
	System.arraycopy(NFAL0050CMsgSchemaContents2.BASE_CONTENTS, 0, BASE_CONTENTS, arcpStartPoint, NFAL0050CMsgSchemaContents2.BASE_CONTENTS.length);
	arcpStartPoint = arcpStartPoint + NFAL0050CMsgSchemaContents2.BASE_CONTENTS.length;
	}

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = new String[102][15];
	static {
		int arcpStartPoint=0;
		System.arraycopy(NFAL0050CMsgSchemaContents1.DISP_CONTENTS, 0, DISP_CONTENTS, arcpStartPoint, NFAL0050CMsgSchemaContents1.DISP_CONTENTS.length);
		arcpStartPoint = arcpStartPoint + NFAL0050CMsgSchemaContents1.DISP_CONTENTS.length;
		System.arraycopy(NFAL0050CMsgSchemaContents2.DISP_CONTENTS, 0, DISP_CONTENTS, arcpStartPoint, NFAL0050CMsgSchemaContents2.DISP_CONTENTS.length);
		arcpStartPoint = arcpStartPoint + NFAL0050CMsgSchemaContents2.DISP_CONTENTS.length;
	}

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

