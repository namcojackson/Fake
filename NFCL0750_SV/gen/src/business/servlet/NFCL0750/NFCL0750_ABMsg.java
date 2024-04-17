//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180419161021000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0750_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL0750;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL0750 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL0750_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_A*/
	public final EZDBBigDecimalItem              xxRowNum_A;

    /** AR_WRT_OFF_RQST_PK_A*/
	public final EZDBBigDecimalItem              arWrtOffRqstPk_A;

    /** WRT_OFF_RQST_USR_ID_A*/
	public final EZDBStringItem              wrtOffRqstUsrId_A;

    /** WRT_OFF_RQST_GRP_CD_A*/
	public final EZDBStringItem              wrtOffRqstGrpCd_A;

    /** AR_WRT_OFF_RQST_TP_CD_A*/
	public final EZDBStringItem              arWrtOffRqstTpCd_A;

    /** AR_WRT_OFF_RQST_TP_DESC_TXT_A*/
	public final EZDBStringItem              arWrtOffRqstTpDescTxt_A;

    /** XX_SCR_ITEM_30_TXT_A*/
	public final EZDBStringItem              xxScrItem30Txt_A;

    /** AR_ADJ_RSN_DESC_TXT_A*/
	public final EZDBStringItem              arAdjRsnDescTxt_A;

    /** AR_ADJ_TP_DESC_TXT_A*/
	public final EZDBStringItem              arAdjTpDescTxt_A;

    /** AR_WRT_OFF_NOTE_TXT_A*/
	public final EZDBStringItem              arWrtOffNoteTxt_A;

    /** XX_GENL_FLD_AREA_TXT_FB*/
	public final EZDBStringItem              xxGenlFldAreaTxt_FB;

    /** XX_GENL_FLD_AREA_TXT_IN*/
	public final EZDBStringItem              xxGenlFldAreaTxt_IN;

    /** XX_GENL_FLD_AREA_TXT_DT*/
	public final EZDBStringItem              xxGenlFldAreaTxt_DT;

    /** XX_GENL_FLD_AREA_TXT_CN*/
	public final EZDBStringItem              xxGenlFldAreaTxt_CN;

    /** INCL_CONSL_INV_FLG_A*/
	public final EZDBStringItem              inclConslInvFlg_A;

    /** AR_DS_WF_STS_DESC_TXT_A*/
	public final EZDBStringItem              arDsWfStsDescTxt_A;

    /** WRT_OFF_APVL_USR_NM_A*/
	public final EZDBStringItem              wrtOffApvlUsrNm_A;

    /** WRT_OFF_ERR_MSG_TXT_A*/
	public final EZDBStringItem              wrtOffErrMsgTxt_A;


	/**
	 * NFCL0750_ABMsg is constructor.
	 * The initialization when the instance of NFCL0750_ABMsg is generated.
	 */
	public NFCL0750_ABMsg() {
		this(false, -1);
	}

	/**
	 * NFCL0750_ABMsg is constructor.
	 * The initialization when the instance of NFCL0750_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL0750_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_A = (EZDBBigDecimalItem)newItem("xxRowNum_A");
		arWrtOffRqstPk_A = (EZDBBigDecimalItem)newItem("arWrtOffRqstPk_A");
		wrtOffRqstUsrId_A = (EZDBStringItem)newItem("wrtOffRqstUsrId_A");
		wrtOffRqstGrpCd_A = (EZDBStringItem)newItem("wrtOffRqstGrpCd_A");
		arWrtOffRqstTpCd_A = (EZDBStringItem)newItem("arWrtOffRqstTpCd_A");
		arWrtOffRqstTpDescTxt_A = (EZDBStringItem)newItem("arWrtOffRqstTpDescTxt_A");
		xxScrItem30Txt_A = (EZDBStringItem)newItem("xxScrItem30Txt_A");
		arAdjRsnDescTxt_A = (EZDBStringItem)newItem("arAdjRsnDescTxt_A");
		arAdjTpDescTxt_A = (EZDBStringItem)newItem("arAdjTpDescTxt_A");
		arWrtOffNoteTxt_A = (EZDBStringItem)newItem("arWrtOffNoteTxt_A");
		xxGenlFldAreaTxt_FB = (EZDBStringItem)newItem("xxGenlFldAreaTxt_FB");
		xxGenlFldAreaTxt_IN = (EZDBStringItem)newItem("xxGenlFldAreaTxt_IN");
		xxGenlFldAreaTxt_DT = (EZDBStringItem)newItem("xxGenlFldAreaTxt_DT");
		xxGenlFldAreaTxt_CN = (EZDBStringItem)newItem("xxGenlFldAreaTxt_CN");
		inclConslInvFlg_A = (EZDBStringItem)newItem("inclConslInvFlg_A");
		arDsWfStsDescTxt_A = (EZDBStringItem)newItem("arDsWfStsDescTxt_A");
		wrtOffApvlUsrNm_A = (EZDBStringItem)newItem("wrtOffApvlUsrNm_A");
		wrtOffErrMsgTxt_A = (EZDBStringItem)newItem("wrtOffErrMsgTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL0750_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL0750_ABMsgArray();
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

        {"XX_ROW_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"AR_WRT_OFF_RQST_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffRqstPk_A
        {"WRT_OFF_RQST_USR_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffRqstUsrId_A
        {"WRT_OFF_RQST_GRP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffRqstGrpCd_A
        {"AR_WRT_OFF_RQST_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffRqstTpCd_A
        {"AR_WRT_OFF_RQST_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffRqstTpDescTxt_A
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt_A
        {"AR_ADJ_RSN_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjRsnDescTxt_A
        {"AR_ADJ_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpDescTxt_A
        {"AR_WRT_OFF_NOTE_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteTxt_A
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_FB
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_IN
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_DT
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_CN
        {"INCL_CONSL_INV_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inclConslInvFlg_A
        {"AR_DS_WF_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arDsWfStsDescTxt_A
        {"WRT_OFF_APVL_USR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffApvlUsrNm_A
        {"WRT_OFF_ERR_MSG_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffErrMsgTxt_A
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

