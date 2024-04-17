//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20181206112439000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7010_YCMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NMAL7010_YCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_TY*/
	public final EZDCStringItem              xxChkBox_TY;

    /** PRC_TRX_AUDC_CATG_CD_Y1*/
	public final EZDCStringItem              prcTrxAudcCatgCd_Y1;

    /** PRC_TRX_AUDC_CATG_CD_Y2*/
	public final EZDCStringItem              prcTrxAudcCatgCd_Y2;

    /** XX_SCR_ITEM_30_TXT_Y1*/
	public final EZDCStringItem              xxScrItem30Txt_Y1;

    /** XX_SCR_ITEM_30_TXT_Y2*/
	public final EZDCStringItem              xxScrItem30Txt_Y2;

    /** XX_REC_NM_TXT_Y1*/
	public final EZDCStringItem              xxRecNmTxt_Y1;

    /** XX_REC_NM_TXT_Y2*/
	public final EZDCStringItem              xxRecNmTxt_Y2;

    /** EFF_FROM_DT_TY*/
	public final EZDCDateItem              effFromDt_TY;

    /** EFF_THRU_DT_TY*/
	public final EZDCDateItem              effThruDt_TY;

    /** PRC_TRX_AUDC_PK_TY*/
	public final EZDCBigDecimalItem              prcTrxAudcPk_TY;

    /** _EZUpdateDateTime_TY*/
	public final EZDCStringItem              ezUpTime_TY;

    /** _EZUpTimeZone_TY*/
	public final EZDCStringItem              ezUpTimeZone_TY;


	/**
	 * NMAL7010_YCMsg is constructor.
	 * The initialization when the instance of NMAL7010_YCMsg is generated.
	 */
	public NMAL7010_YCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7010_YCMsg is constructor.
	 * The initialization when the instance of NMAL7010_YCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7010_YCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_TY = (EZDCStringItem)newItem("xxChkBox_TY");
		prcTrxAudcCatgCd_Y1 = (EZDCStringItem)newItem("prcTrxAudcCatgCd_Y1");
		prcTrxAudcCatgCd_Y2 = (EZDCStringItem)newItem("prcTrxAudcCatgCd_Y2");
		xxScrItem30Txt_Y1 = (EZDCStringItem)newItem("xxScrItem30Txt_Y1");
		xxScrItem30Txt_Y2 = (EZDCStringItem)newItem("xxScrItem30Txt_Y2");
		xxRecNmTxt_Y1 = (EZDCStringItem)newItem("xxRecNmTxt_Y1");
		xxRecNmTxt_Y2 = (EZDCStringItem)newItem("xxRecNmTxt_Y2");
		effFromDt_TY = (EZDCDateItem)newItem("effFromDt_TY");
		effThruDt_TY = (EZDCDateItem)newItem("effThruDt_TY");
		prcTrxAudcPk_TY = (EZDCBigDecimalItem)newItem("prcTrxAudcPk_TY");
		ezUpTime_TY = (EZDCStringItem)newItem("ezUpTime_TY");
		ezUpTimeZone_TY = (EZDCStringItem)newItem("ezUpTimeZone_TY");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7010_YCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7010_YCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_TY", "xxChkBox_TY", "TY", null, TYPE_HANKAKUEIJI, "1", null},
	{"prcTrxAudcCatgCd_Y1", "prcTrxAudcCatgCd_Y1", "Y1", null, TYPE_HANKAKUEISU, "2", null},
	{"prcTrxAudcCatgCd_Y2", "prcTrxAudcCatgCd_Y2", "Y2", null, TYPE_HANKAKUEISU, "2", null},
	{"xxScrItem30Txt_Y1", "xxScrItem30Txt_Y1", "Y1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem30Txt_Y2", "xxScrItem30Txt_Y2", "Y2", null, TYPE_HANKAKUEISU, "30", null},
	{"xxRecNmTxt_Y1", "xxRecNmTxt_Y1", "Y1", null, TYPE_HANKAKUEISU, "500", null},
	{"xxRecNmTxt_Y2", "xxRecNmTxt_Y2", "Y2", null, TYPE_HANKAKUEISU, "500", null},
	{"effFromDt_TY", "effFromDt_TY", "TY", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_TY", "effThruDt_TY", "TY", null, TYPE_NENTSUKIHI, "8", null},
	{"prcTrxAudcPk_TY", "prcTrxAudcPk_TY", "TY", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_TY", "ezUpTime_TY", "TY", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_TY", "ezUpTimeZone_TY", "TY", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_TY
        {"PRC_TRX_AUDC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTrxAudcCatgCd_Y1
        {"PRC_TRX_AUDC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTrxAudcCatgCd_Y2
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt_Y1
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt_Y2
        {"XX_REC_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecNmTxt_Y1
        {"XX_REC_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecNmTxt_Y2
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_TY
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_TY
        {"PRC_TRX_AUDC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTrxAudcPk_TY
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_TY
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_TY
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
