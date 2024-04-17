//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20181206112439000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7010_XCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7010 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7010_XCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_CX*/
	public final EZDCStringItem              xxChkBox_CX;

    /** PRC_CUST_AUDC_CATG_CD_X1*/
	public final EZDCStringItem              prcCustAudcCatgCd_X1;

    /** PRC_CUST_AUDC_CATG_CD_X2*/
	public final EZDCStringItem              prcCustAudcCatgCd_X2;

    /** PRC_CUST_AUDC_CATG_CD_X3*/
	public final EZDCStringItem              prcCustAudcCatgCd_X3;

    /** PUB_FLG_CX*/
	public final EZDCStringItem              pubFlg_CX;

    /** XX_SCR_ITEM_30_TXT_X1*/
	public final EZDCStringItem              xxScrItem30Txt_X1;

    /** XX_SCR_ITEM_30_TXT_X2*/
	public final EZDCStringItem              xxScrItem30Txt_X2;

    /** XX_SCR_ITEM_30_TXT_X3*/
	public final EZDCStringItem              xxScrItem30Txt_X3;

    /** XX_REC_NM_TXT_X1*/
	public final EZDCStringItem              xxRecNmTxt_X1;

    /** XX_REC_NM_TXT_X2*/
	public final EZDCStringItem              xxRecNmTxt_X2;

    /** XX_REC_NM_TXT_X3*/
	public final EZDCStringItem              xxRecNmTxt_X3;

    /** REQ_FLG_CX*/
	public final EZDCStringItem              reqFlg_CX;

    /** EFF_FROM_DT_CX*/
	public final EZDCDateItem              effFromDt_CX;

    /** EFF_THRU_DT_CX*/
	public final EZDCDateItem              effThruDt_CX;

    /** DEF_PRC_LIST_FLG_CX*/
	public final EZDCStringItem              defPrcListFlg_CX;

    /** PRC_CUST_AUDC_PK_CX*/
	public final EZDCBigDecimalItem              prcCustAudcPk_CX;

    /** _EZUpdateDateTime_CX*/
	public final EZDCStringItem              ezUpTime_CX;

    /** _EZUpTimeZone_CX*/
	public final EZDCStringItem              ezUpTimeZone_CX;


	/**
	 * NMAL7010_XCMsg is constructor.
	 * The initialization when the instance of NMAL7010_XCMsg is generated.
	 */
	public NMAL7010_XCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7010_XCMsg is constructor.
	 * The initialization when the instance of NMAL7010_XCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7010_XCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_CX = (EZDCStringItem)newItem("xxChkBox_CX");
		prcCustAudcCatgCd_X1 = (EZDCStringItem)newItem("prcCustAudcCatgCd_X1");
		prcCustAudcCatgCd_X2 = (EZDCStringItem)newItem("prcCustAudcCatgCd_X2");
		prcCustAudcCatgCd_X3 = (EZDCStringItem)newItem("prcCustAudcCatgCd_X3");
		pubFlg_CX = (EZDCStringItem)newItem("pubFlg_CX");
		xxScrItem30Txt_X1 = (EZDCStringItem)newItem("xxScrItem30Txt_X1");
		xxScrItem30Txt_X2 = (EZDCStringItem)newItem("xxScrItem30Txt_X2");
		xxScrItem30Txt_X3 = (EZDCStringItem)newItem("xxScrItem30Txt_X3");
		xxRecNmTxt_X1 = (EZDCStringItem)newItem("xxRecNmTxt_X1");
		xxRecNmTxt_X2 = (EZDCStringItem)newItem("xxRecNmTxt_X2");
		xxRecNmTxt_X3 = (EZDCStringItem)newItem("xxRecNmTxt_X3");
		reqFlg_CX = (EZDCStringItem)newItem("reqFlg_CX");
		effFromDt_CX = (EZDCDateItem)newItem("effFromDt_CX");
		effThruDt_CX = (EZDCDateItem)newItem("effThruDt_CX");
		defPrcListFlg_CX = (EZDCStringItem)newItem("defPrcListFlg_CX");
		prcCustAudcPk_CX = (EZDCBigDecimalItem)newItem("prcCustAudcPk_CX");
		ezUpTime_CX = (EZDCStringItem)newItem("ezUpTime_CX");
		ezUpTimeZone_CX = (EZDCStringItem)newItem("ezUpTimeZone_CX");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7010_XCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7010_XCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_CX", "xxChkBox_CX", "CX", null, TYPE_HANKAKUEIJI, "1", null},
	{"prcCustAudcCatgCd_X1", "prcCustAudcCatgCd_X1", "X1", null, TYPE_HANKAKUEISU, "2", null},
	{"prcCustAudcCatgCd_X2", "prcCustAudcCatgCd_X2", "X2", null, TYPE_HANKAKUEISU, "2", null},
	{"prcCustAudcCatgCd_X3", "prcCustAudcCatgCd_X3", "X3", null, TYPE_HANKAKUEISU, "2", null},
	{"pubFlg_CX", "pubFlg_CX", "CX", null, TYPE_HANKAKUEISU, "1", null},
	{"xxScrItem30Txt_X1", "xxScrItem30Txt_X1", "X1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem30Txt_X2", "xxScrItem30Txt_X2", "X2", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem30Txt_X3", "xxScrItem30Txt_X3", "X3", null, TYPE_HANKAKUEISU, "30", null},
	{"xxRecNmTxt_X1", "xxRecNmTxt_X1", "X1", null, TYPE_HANKAKUEISU, "500", null},
	{"xxRecNmTxt_X2", "xxRecNmTxt_X2", "X2", null, TYPE_HANKAKUEISU, "500", null},
	{"xxRecNmTxt_X3", "xxRecNmTxt_X3", "X3", null, TYPE_HANKAKUEISU, "500", null},
	{"reqFlg_CX", "reqFlg_CX", "CX", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_CX", "effFromDt_CX", "CX", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_CX", "effThruDt_CX", "CX", null, TYPE_NENTSUKIHI, "8", null},
	{"defPrcListFlg_CX", "defPrcListFlg_CX", "CX", null, TYPE_HANKAKUEISU, "1", null},
	{"prcCustAudcPk_CX", "prcCustAudcPk_CX", "CX", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_CX", "ezUpTime_CX", "CX", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_CX", "ezUpTimeZone_CX", "CX", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_CX
        {"PRC_CUST_AUDC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCustAudcCatgCd_X1
        {"PRC_CUST_AUDC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCustAudcCatgCd_X2
        {"PRC_CUST_AUDC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCustAudcCatgCd_X3
        {"PUB_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pubFlg_CX
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt_X1
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt_X2
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt_X3
        {"XX_REC_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecNmTxt_X1
        {"XX_REC_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecNmTxt_X2
        {"XX_REC_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecNmTxt_X3
        {"REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//reqFlg_CX
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_CX
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_CX
        {"DEF_PRC_LIST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defPrcListFlg_CX
        {"PRC_CUST_AUDC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCustAudcPk_CX
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_CX
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_CX
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

