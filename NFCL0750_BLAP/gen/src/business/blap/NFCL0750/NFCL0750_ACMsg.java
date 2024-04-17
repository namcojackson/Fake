//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180419160735000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0750_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL0750;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL0750 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL0750_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_A*/
	public final EZDCBigDecimalItem              xxRowNum_A;

    /** AR_WRT_OFF_RQST_PK_A*/
	public final EZDCBigDecimalItem              arWrtOffRqstPk_A;

    /** WRT_OFF_RQST_USR_ID_A*/
	public final EZDCStringItem              wrtOffRqstUsrId_A;

    /** WRT_OFF_RQST_GRP_CD_A*/
	public final EZDCStringItem              wrtOffRqstGrpCd_A;

    /** AR_WRT_OFF_RQST_TP_CD_A*/
	public final EZDCStringItem              arWrtOffRqstTpCd_A;

    /** AR_WRT_OFF_RQST_TP_DESC_TXT_A*/
	public final EZDCStringItem              arWrtOffRqstTpDescTxt_A;

    /** XX_SCR_ITEM_30_TXT_A*/
	public final EZDCStringItem              xxScrItem30Txt_A;

    /** AR_ADJ_RSN_DESC_TXT_A*/
	public final EZDCStringItem              arAdjRsnDescTxt_A;

    /** AR_ADJ_TP_DESC_TXT_A*/
	public final EZDCStringItem              arAdjTpDescTxt_A;

    /** AR_WRT_OFF_NOTE_TXT_A*/
	public final EZDCStringItem              arWrtOffNoteTxt_A;

    /** XX_GENL_FLD_AREA_TXT_FB*/
	public final EZDCStringItem              xxGenlFldAreaTxt_FB;

    /** XX_GENL_FLD_AREA_TXT_IN*/
	public final EZDCStringItem              xxGenlFldAreaTxt_IN;

    /** XX_GENL_FLD_AREA_TXT_DT*/
	public final EZDCStringItem              xxGenlFldAreaTxt_DT;

    /** XX_GENL_FLD_AREA_TXT_CN*/
	public final EZDCStringItem              xxGenlFldAreaTxt_CN;

    /** INCL_CONSL_INV_FLG_A*/
	public final EZDCStringItem              inclConslInvFlg_A;

    /** AR_DS_WF_STS_DESC_TXT_A*/
	public final EZDCStringItem              arDsWfStsDescTxt_A;

    /** WRT_OFF_APVL_USR_NM_A*/
	public final EZDCStringItem              wrtOffApvlUsrNm_A;

    /** WRT_OFF_ERR_MSG_TXT_A*/
	public final EZDCStringItem              wrtOffErrMsgTxt_A;


	/**
	 * NFCL0750_ACMsg is constructor.
	 * The initialization when the instance of NFCL0750_ACMsg is generated.
	 */
	public NFCL0750_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFCL0750_ACMsg is constructor.
	 * The initialization when the instance of NFCL0750_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL0750_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_A = (EZDCBigDecimalItem)newItem("xxRowNum_A");
		arWrtOffRqstPk_A = (EZDCBigDecimalItem)newItem("arWrtOffRqstPk_A");
		wrtOffRqstUsrId_A = (EZDCStringItem)newItem("wrtOffRqstUsrId_A");
		wrtOffRqstGrpCd_A = (EZDCStringItem)newItem("wrtOffRqstGrpCd_A");
		arWrtOffRqstTpCd_A = (EZDCStringItem)newItem("arWrtOffRqstTpCd_A");
		arWrtOffRqstTpDescTxt_A = (EZDCStringItem)newItem("arWrtOffRqstTpDescTxt_A");
		xxScrItem30Txt_A = (EZDCStringItem)newItem("xxScrItem30Txt_A");
		arAdjRsnDescTxt_A = (EZDCStringItem)newItem("arAdjRsnDescTxt_A");
		arAdjTpDescTxt_A = (EZDCStringItem)newItem("arAdjTpDescTxt_A");
		arWrtOffNoteTxt_A = (EZDCStringItem)newItem("arWrtOffNoteTxt_A");
		xxGenlFldAreaTxt_FB = (EZDCStringItem)newItem("xxGenlFldAreaTxt_FB");
		xxGenlFldAreaTxt_IN = (EZDCStringItem)newItem("xxGenlFldAreaTxt_IN");
		xxGenlFldAreaTxt_DT = (EZDCStringItem)newItem("xxGenlFldAreaTxt_DT");
		xxGenlFldAreaTxt_CN = (EZDCStringItem)newItem("xxGenlFldAreaTxt_CN");
		inclConslInvFlg_A = (EZDCStringItem)newItem("inclConslInvFlg_A");
		arDsWfStsDescTxt_A = (EZDCStringItem)newItem("arDsWfStsDescTxt_A");
		wrtOffApvlUsrNm_A = (EZDCStringItem)newItem("wrtOffApvlUsrNm_A");
		wrtOffErrMsgTxt_A = (EZDCStringItem)newItem("wrtOffErrMsgTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL0750_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL0750_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"arWrtOffRqstPk_A", "arWrtOffRqstPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wrtOffRqstUsrId_A", "wrtOffRqstUsrId_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"wrtOffRqstGrpCd_A", "wrtOffRqstGrpCd_A", "A", null, TYPE_HANKAKUEISU, "40", null},
	{"arWrtOffRqstTpCd_A", "arWrtOffRqstTpCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"arWrtOffRqstTpDescTxt_A", "arWrtOffRqstTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem30Txt_A", "xxScrItem30Txt_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"arAdjRsnDescTxt_A", "arAdjRsnDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"arAdjTpDescTxt_A", "arAdjTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"arWrtOffNoteTxt_A", "arWrtOffNoteTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxGenlFldAreaTxt_FB", "xxGenlFldAreaTxt_FB", "FB", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxGenlFldAreaTxt_IN", "xxGenlFldAreaTxt_IN", "IN", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxGenlFldAreaTxt_DT", "xxGenlFldAreaTxt_DT", "DT", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxGenlFldAreaTxt_CN", "xxGenlFldAreaTxt_CN", "CN", null, TYPE_HANKAKUEISU, "1000", null},
	{"inclConslInvFlg_A", "inclConslInvFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"arDsWfStsDescTxt_A", "arDsWfStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"wrtOffApvlUsrNm_A", "wrtOffApvlUsrNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"wrtOffErrMsgTxt_A", "wrtOffErrMsgTxt_A", "A", null, TYPE_HANKAKUEISU, "200", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"AR_WRT_OFF_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffRqstPk_A
        {"WRT_OFF_RQST_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffRqstUsrId_A
        {"WRT_OFF_RQST_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffRqstGrpCd_A
        {"AR_WRT_OFF_RQST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffRqstTpCd_A
        {"AR_WRT_OFF_RQST_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffRqstTpDescTxt_A
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt_A
        {"AR_ADJ_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjRsnDescTxt_A
        {"AR_ADJ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpDescTxt_A
        {"AR_WRT_OFF_NOTE_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteTxt_A
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_FB
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_IN
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_DT
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_CN
        {"INCL_CONSL_INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inclConslInvFlg_A
        {"AR_DS_WF_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arDsWfStsDescTxt_A
        {"WRT_OFF_APVL_USR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffApvlUsrNm_A
        {"WRT_OFF_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffErrMsgTxt_A
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
