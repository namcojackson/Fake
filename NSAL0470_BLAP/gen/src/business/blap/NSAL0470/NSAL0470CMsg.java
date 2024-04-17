//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170725132704000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0470CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0470;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0470 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0470CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_NUM*/
	public final EZDCStringItem              dsContrNum;

    /** DS_CONTR_CATG_CD*/
	public final EZDCStringItem              dsContrCatgCd;

    /** DS_CONTR_CTRL_STS_CD*/
	public final EZDCStringItem              dsContrCtrlStsCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** DS_CONTR_VLD_PK_H1*/
	public final EZDCBigDecimalItemArray              dsContrVldPk_H1;

    /** DS_CONTR_VLD_NM_H2*/
	public final EZDCStringItemArray              dsContrVldNm_H2;

    /** DS_CONTR_VLD_PK_H3*/
	public final EZDCBigDecimalItem              dsContrVldPk_H3;

    /** SER_NUM*/
	public final EZDCStringItem              serNum;

    /** XX_CHK_BOX_H1*/
	public final EZDCStringItem              xxChkBox_H1;

    /** XX_CHK_BOX_H2*/
	public final EZDCStringItem              xxChkBox_H2;

    /** XX_CHK_BOX_H3*/
	public final EZDCStringItem              xxChkBox_H3;

    /** XX_CHK_BOX_H4*/
	public final EZDCStringItem              xxChkBox_H4;

    /** DS_CONTR_VLD_STS_DESC_TXT_H1*/
	public final EZDCStringItem              dsContrVldStsDescTxt_H1;

    /** DS_CONTR_VLD_STS_DESC_TXT_H2*/
	public final EZDCStringItem              dsContrVldStsDescTxt_H2;

    /** DS_CONTR_VLD_STS_DESC_TXT_H3*/
	public final EZDCStringItem              dsContrVldStsDescTxt_H3;

    /** DS_CONTR_VLD_STS_DESC_TXT_H4*/
	public final EZDCStringItem              dsContrVldStsDescTxt_H4;

    /** XX_ERR_FLG*/
	public final EZDCStringItem              xxErrFlg;

    /** XX_DPLY_CTRL_FLG_AL*/
	public final EZDCStringItem              xxDplyCtrlFlg_AL;

    /** XX_ROW_NUM*/
	public final EZDCBigDecimalItem              xxRowNum;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** QLTY_ASRN_HLD_PEND_APVL_FLG*/
	public final EZDCStringItem              qltyAsrnHldPendApvlFlg;

    /** CONTR_ADMIN_PSN_CD*/
	public final EZDCStringItem              contrAdminPsnCd;

    /** XX_WRN_SKIP_FLG*/
	public final EZDCStringItem              xxWrnSkipFlg;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** A*/
	public final business.blap.NSAL0470.NSAL0470_ACMsgArray              A;


	/**
	 * NSAL0470CMsg is constructor.
	 * The initialization when the instance of NSAL0470CMsg is generated.
	 */
	public NSAL0470CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0470CMsg is constructor.
	 * The initialization when the instance of NSAL0470CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0470CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrNum = (EZDCStringItem)newItem("dsContrNum");
		dsContrCatgCd = (EZDCStringItem)newItem("dsContrCatgCd");
		dsContrCtrlStsCd = (EZDCStringItem)newItem("dsContrCtrlStsCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		dsContrVldPk_H1 = (EZDCBigDecimalItemArray)newItemArray("dsContrVldPk_H1");
		dsContrVldNm_H2 = (EZDCStringItemArray)newItemArray("dsContrVldNm_H2");
		dsContrVldPk_H3 = (EZDCBigDecimalItem)newItem("dsContrVldPk_H3");
		serNum = (EZDCStringItem)newItem("serNum");
		xxChkBox_H1 = (EZDCStringItem)newItem("xxChkBox_H1");
		xxChkBox_H2 = (EZDCStringItem)newItem("xxChkBox_H2");
		xxChkBox_H3 = (EZDCStringItem)newItem("xxChkBox_H3");
		xxChkBox_H4 = (EZDCStringItem)newItem("xxChkBox_H4");
		dsContrVldStsDescTxt_H1 = (EZDCStringItem)newItem("dsContrVldStsDescTxt_H1");
		dsContrVldStsDescTxt_H2 = (EZDCStringItem)newItem("dsContrVldStsDescTxt_H2");
		dsContrVldStsDescTxt_H3 = (EZDCStringItem)newItem("dsContrVldStsDescTxt_H3");
		dsContrVldStsDescTxt_H4 = (EZDCStringItem)newItem("dsContrVldStsDescTxt_H4");
		xxErrFlg = (EZDCStringItem)newItem("xxErrFlg");
		xxDplyCtrlFlg_AL = (EZDCStringItem)newItem("xxDplyCtrlFlg_AL");
		xxRowNum = (EZDCBigDecimalItem)newItem("xxRowNum");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		qltyAsrnHldPendApvlFlg = (EZDCStringItem)newItem("qltyAsrnHldPendApvlFlg");
		contrAdminPsnCd = (EZDCStringItem)newItem("contrAdminPsnCd");
		xxWrnSkipFlg = (EZDCStringItem)newItem("xxWrnSkipFlg");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		A = (business.blap.NSAL0470.NSAL0470_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0470CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0470CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrCatgCd", "dsContrCatgCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrCtrlStsCd", "dsContrCtrlStsCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrVldPk_H1", "dsContrVldPk_H1", "H1", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrVldNm_H2", "dsContrVldNm_H2", "H2", "99", TYPE_HANKAKUEISU, "60", null},
	{"dsContrVldPk_H3", "dsContrVldPk_H3", "H3", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H2", "xxChkBox_H2", "H2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H3", "xxChkBox_H3", "H3", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H4", "xxChkBox_H4", "H4", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsContrVldStsDescTxt_H1", "dsContrVldStsDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrVldStsDescTxt_H2", "dsContrVldStsDescTxt_H2", "H2", null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrVldStsDescTxt_H3", "dsContrVldStsDescTxt_H3", "H3", null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrVldStsDescTxt_H4", "dsContrVldStsDescTxt_H4", "H4", null, TYPE_HANKAKUEISU, "50", null},
	{"xxErrFlg", "xxErrFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_AL", "xxDplyCtrlFlg_AL", "AL", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"qltyAsrnHldPendApvlFlg", "qltyAsrnHldPendApvlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"contrAdminPsnCd", "contrAdminPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "200", "business.blap.NSAL0470.NSAL0470_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd
        {"DS_CONTR_CTRL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"DS_CONTR_VLD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrVldPk_H1
        {"DS_CONTR_VLD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrVldNm_H2
        {"DS_CONTR_VLD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrVldPk_H3
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H3
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H4
        {"DS_CONTR_VLD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrVldStsDescTxt_H1
        {"DS_CONTR_VLD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrVldStsDescTxt_H2
        {"DS_CONTR_VLD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrVldStsDescTxt_H3
        {"DS_CONTR_VLD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrVldStsDescTxt_H4
        {"XX_ERR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_AL
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"QLTY_ASRN_HLD_PEND_APVL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//qltyAsrnHldPendApvlFlg
        {"CONTR_ADMIN_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAdminPsnCd
        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
		null,	//A
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

