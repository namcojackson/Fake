//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170602151334000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0640_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0640;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0640 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0640_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime_A1*/
	public final EZDBStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDBStringItem              ezUpTimeZone_A1;

    /** DS_CONTR_PK_A1*/
	public final EZDBBigDecimalItem              dsContrPk_A1;

    /** DS_CONTR_STS_CD_A1*/
	public final EZDBStringItem              dsContrStsCd_A1;

    /** XX_DPLY_CTRL_FLG_A1*/
	public final EZDBStringItem              xxDplyCtrlFlg_A1;

    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** XX_SCR_ITEM_34_TXT_A1*/
	public final EZDBStringItem              xxScrItem34Txt_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDBStringItem              dsAcctNm_A1;

    /** CONTR_VRSN_EFF_FROM_DT_A1*/
	public final EZDBDateItem              contrVrsnEffFromDt_A1;

    /** CONTR_VRSN_EFF_THRU_DT_A1*/
	public final EZDBDateItem              contrVrsnEffThruDt_A1;

    /** SVC_LINE_BIZ_CD_A1*/
	public final EZDBStringItem              svcLineBizCd_A1;

    /** SVC_CONTR_BR_CD_A1*/
	public final EZDBStringItem              svcContrBrCd_A1;

    /** SVC_CONTR_BR_DESC_TXT_A1*/
	public final EZDBStringItem              svcContrBrDescTxt_A1;

    /** XX_GENL_FLD_AREA_TXT_A1*/
	public final EZDBStringItem              xxGenlFldAreaTxt_A1;

    /** PSN_CD_A1*/
	public final EZDBStringItem              psnCd_A1;

    /** XX_PSN_NM_A1*/
	public final EZDBStringItem              xxPsnNm_A1;

    /** TOC_CD_A1*/
	public final EZDBStringItem              tocCd_A1;

    /** TOC_NM_A1*/
	public final EZDBStringItem              tocNm_A1;

    /** SVC_LINE_BIZ_CD_A2*/
	public final EZDBStringItem              svcLineBizCd_A2;

    /** SVC_CONTR_BR_CD_A2*/
	public final EZDBStringItem              svcContrBrCd_A2;

    /** SVC_CONTR_BR_DESC_TXT_A2*/
	public final EZDBStringItem              svcContrBrDescTxt_A2;

    /** XX_GENL_FLD_AREA_TXT_A2*/
	public final EZDBStringItem              xxGenlFldAreaTxt_A2;

    /** TOC_CD_A2*/
	public final EZDBStringItem              tocCd_A2;

    /** TOC_NM_A2*/
	public final EZDBStringItem              tocNm_A2;

    /** PSN_CD_A2*/
	public final EZDBStringItem              psnCd_A2;

    /** XX_PSN_NM_A2*/
	public final EZDBStringItem              xxPsnNm_A2;

    /** DS_MSG_TXT_A1*/
	public final EZDBStringItem              dsMsgTxt_A1;

    /** XX_ROW_NUM_A1*/
	public final EZDBBigDecimalItem              xxRowNum_A1;


	/**
	 * NSAL0640_ABMsg is constructor.
	 * The initialization when the instance of NSAL0640_ABMsg is generated.
	 */
	public NSAL0640_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0640_ABMsg is constructor.
	 * The initialization when the instance of NSAL0640_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0640_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime_A1 = (EZDBStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDBStringItem)newItem("ezUpTimeZone_A1");
		dsContrPk_A1 = (EZDBBigDecimalItem)newItem("dsContrPk_A1");
		dsContrStsCd_A1 = (EZDBStringItem)newItem("dsContrStsCd_A1");
		xxDplyCtrlFlg_A1 = (EZDBStringItem)newItem("xxDplyCtrlFlg_A1");
		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		xxScrItem34Txt_A1 = (EZDBStringItem)newItem("xxScrItem34Txt_A1");
		dsAcctNm_A1 = (EZDBStringItem)newItem("dsAcctNm_A1");
		contrVrsnEffFromDt_A1 = (EZDBDateItem)newItem("contrVrsnEffFromDt_A1");
		contrVrsnEffThruDt_A1 = (EZDBDateItem)newItem("contrVrsnEffThruDt_A1");
		svcLineBizCd_A1 = (EZDBStringItem)newItem("svcLineBizCd_A1");
		svcContrBrCd_A1 = (EZDBStringItem)newItem("svcContrBrCd_A1");
		svcContrBrDescTxt_A1 = (EZDBStringItem)newItem("svcContrBrDescTxt_A1");
		xxGenlFldAreaTxt_A1 = (EZDBStringItem)newItem("xxGenlFldAreaTxt_A1");
		psnCd_A1 = (EZDBStringItem)newItem("psnCd_A1");
		xxPsnNm_A1 = (EZDBStringItem)newItem("xxPsnNm_A1");
		tocCd_A1 = (EZDBStringItem)newItem("tocCd_A1");
		tocNm_A1 = (EZDBStringItem)newItem("tocNm_A1");
		svcLineBizCd_A2 = (EZDBStringItem)newItem("svcLineBizCd_A2");
		svcContrBrCd_A2 = (EZDBStringItem)newItem("svcContrBrCd_A2");
		svcContrBrDescTxt_A2 = (EZDBStringItem)newItem("svcContrBrDescTxt_A2");
		xxGenlFldAreaTxt_A2 = (EZDBStringItem)newItem("xxGenlFldAreaTxt_A2");
		tocCd_A2 = (EZDBStringItem)newItem("tocCd_A2");
		tocNm_A2 = (EZDBStringItem)newItem("tocNm_A2");
		psnCd_A2 = (EZDBStringItem)newItem("psnCd_A2");
		xxPsnNm_A2 = (EZDBStringItem)newItem("xxPsnNm_A2");
		dsMsgTxt_A1 = (EZDBStringItem)newItem("dsMsgTxt_A1");
		xxRowNum_A1 = (EZDBBigDecimalItem)newItem("xxRowNum_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0640_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0640_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrPk_A1", "dsContrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrStsCd_A1", "dsContrStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxDplyCtrlFlg_A1", "xxDplyCtrlFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxScrItem34Txt_A1", "xxScrItem34Txt_A1", "A1", null, TYPE_HANKAKUEISU, "34", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"contrVrsnEffFromDt_A1", "contrVrsnEffFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"contrVrsnEffThruDt_A1", "contrVrsnEffThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"svcLineBizCd_A1", "svcLineBizCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"svcContrBrCd_A1", "svcContrBrCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt_A1", "svcContrBrDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxGenlFldAreaTxt_A1", "xxGenlFldAreaTxt_A1", "A1", null, TYPE_HANKAKUEISU, "1000", null},
	{"psnCd_A1", "psnCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPsnNm_A1", "xxPsnNm_A1", "A1", null, TYPE_HANKAKUEISU, "62", null},
	{"tocCd_A1", "tocCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"tocNm_A1", "tocNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"svcLineBizCd_A2", "svcLineBizCd_A2", "A2", null, TYPE_HANKAKUEISU, "20", null},
	{"svcContrBrCd_A2", "svcContrBrCd_A2", "A2", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt_A2", "svcContrBrDescTxt_A2", "A2", null, TYPE_HANKAKUEISU, "50", null},
	{"xxGenlFldAreaTxt_A2", "xxGenlFldAreaTxt_A2", "A2", null, TYPE_HANKAKUEISU, "1000", null},
	{"tocCd_A2", "tocCd_A2", "A2", null, TYPE_HANKAKUEISU, "8", null},
	{"tocNm_A2", "tocNm_A2", "A2", null, TYPE_HANKAKUEISU, "50", null},
	{"psnCd_A2", "psnCd_A2", "A2", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPsnNm_A2", "xxPsnNm_A2", "A2", null, TYPE_HANKAKUEISU, "62", null},
	{"dsMsgTxt_A1", "dsMsgTxt_A1", "A1", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRowNum_A1", "xxRowNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"DS_CONTR_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_A1
        {"DS_CONTR_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsCd_A1
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A1
        {"XX_CHK_BOX",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"XX_SCR_ITEM_34_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem34Txt_A1
        {"DS_ACCT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"CONTR_VRSN_EFF_FROM_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrVrsnEffFromDt_A1
        {"CONTR_VRSN_EFF_THRU_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrVrsnEffThruDt_A1
        {"SVC_LINE_BIZ_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_A1
        {"SVC_CONTR_BR_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_A1
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_A1
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_A1
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_A1
        {"XX_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_A1
        {"TOC_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_A1
        {"TOC_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_A1
        {"SVC_LINE_BIZ_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_A2
        {"SVC_CONTR_BR_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_A2
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_A2
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_A2
        {"TOC_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_A2
        {"TOC_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_A2
        {"PSN_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_A2
        {"XX_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_A2
        {"DS_MSG_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMsgTxt_A1
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A1
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

