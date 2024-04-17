//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20191206145238000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7100_XBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7100 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7100_XBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_MX*/
	public final EZDBStringItem              xxChkBox_MX;

    /** PRC_MKT_PRMO_AUDC_CTRL_PK_MX*/
	public final EZDBBigDecimalItem              prcMktPrmoAudcCtrlPk_MX;

    /** PUB_FLG_MX*/
	public final EZDBStringItem              pubFlg_MX;

    /** MKT_PRMO_AUDC_CATG_CD_X1*/
	public final EZDBStringItem              mktPrmoAudcCatgCd_X1;

    /** MKT_PRMO_AUDC_CATG_CD_X2*/
	public final EZDBStringItem              mktPrmoAudcCatgCd_X2;

    /** MKT_PRMO_AUDC_CATG_CD_X3*/
	public final EZDBStringItem              mktPrmoAudcCatgCd_X3;

    /** XX_SCR_ITEM_30_TXT_X1*/
	public final EZDBStringItem              xxScrItem30Txt_X1;

    /** XX_SCR_ITEM_30_TXT_X2*/
	public final EZDBStringItem              xxScrItem30Txt_X2;

    /** XX_SCR_ITEM_30_TXT_X3*/
	public final EZDBStringItem              xxScrItem30Txt_X3;

    /** XX_REC_NM_TXT_X1*/
	public final EZDBStringItem              xxRecNmTxt_X1;

    /** XX_REC_NM_TXT_X2*/
	public final EZDBStringItem              xxRecNmTxt_X2;

    /** XX_REC_NM_TXT_X3*/
	public final EZDBStringItem              xxRecNmTxt_X3;

    /** EFF_FROM_DT_MX*/
	public final EZDBDateItem              effFromDt_MX;

    /** EFF_THRU_DT_MX*/
	public final EZDBDateItem              effThruDt_MX;

    /** _EZUpdateDateTime_MX*/
	public final EZDBStringItem              ezUpTime_MX;

    /** _EZUpTimeZone_MX*/
	public final EZDBStringItem              ezUpTimeZone_MX;


	/**
	 * NMAL7100_XBMsg is constructor.
	 * The initialization when the instance of NMAL7100_XBMsg is generated.
	 */
	public NMAL7100_XBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7100_XBMsg is constructor.
	 * The initialization when the instance of NMAL7100_XBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7100_XBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_MX = (EZDBStringItem)newItem("xxChkBox_MX");
		prcMktPrmoAudcCtrlPk_MX = (EZDBBigDecimalItem)newItem("prcMktPrmoAudcCtrlPk_MX");
		pubFlg_MX = (EZDBStringItem)newItem("pubFlg_MX");
		mktPrmoAudcCatgCd_X1 = (EZDBStringItem)newItem("mktPrmoAudcCatgCd_X1");
		mktPrmoAudcCatgCd_X2 = (EZDBStringItem)newItem("mktPrmoAudcCatgCd_X2");
		mktPrmoAudcCatgCd_X3 = (EZDBStringItem)newItem("mktPrmoAudcCatgCd_X3");
		xxScrItem30Txt_X1 = (EZDBStringItem)newItem("xxScrItem30Txt_X1");
		xxScrItem30Txt_X2 = (EZDBStringItem)newItem("xxScrItem30Txt_X2");
		xxScrItem30Txt_X3 = (EZDBStringItem)newItem("xxScrItem30Txt_X3");
		xxRecNmTxt_X1 = (EZDBStringItem)newItem("xxRecNmTxt_X1");
		xxRecNmTxt_X2 = (EZDBStringItem)newItem("xxRecNmTxt_X2");
		xxRecNmTxt_X3 = (EZDBStringItem)newItem("xxRecNmTxt_X3");
		effFromDt_MX = (EZDBDateItem)newItem("effFromDt_MX");
		effThruDt_MX = (EZDBDateItem)newItem("effThruDt_MX");
		ezUpTime_MX = (EZDBStringItem)newItem("ezUpTime_MX");
		ezUpTimeZone_MX = (EZDBStringItem)newItem("ezUpTimeZone_MX");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7100_XBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7100_XBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_MX", "xxChkBox_MX", "MX", null, TYPE_HANKAKUEIJI, "1", null},
	{"prcMktPrmoAudcCtrlPk_MX", "prcMktPrmoAudcCtrlPk_MX", "MX", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"pubFlg_MX", "pubFlg_MX", "MX", null, TYPE_HANKAKUEISU, "1", null},
	{"mktPrmoAudcCatgCd_X1", "mktPrmoAudcCatgCd_X1", "X1", null, TYPE_HANKAKUEISU, "2", null},
	{"mktPrmoAudcCatgCd_X2", "mktPrmoAudcCatgCd_X2", "X2", null, TYPE_HANKAKUEISU, "2", null},
	{"mktPrmoAudcCatgCd_X3", "mktPrmoAudcCatgCd_X3", "X3", null, TYPE_HANKAKUEISU, "2", null},
	{"xxScrItem30Txt_X1", "xxScrItem30Txt_X1", "X1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem30Txt_X2", "xxScrItem30Txt_X2", "X2", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem30Txt_X3", "xxScrItem30Txt_X3", "X3", null, TYPE_HANKAKUEISU, "30", null},
	{"xxRecNmTxt_X1", "xxRecNmTxt_X1", "X1", null, TYPE_HANKAKUEISU, "500", null},
	{"xxRecNmTxt_X2", "xxRecNmTxt_X2", "X2", null, TYPE_HANKAKUEISU, "500", null},
	{"xxRecNmTxt_X3", "xxRecNmTxt_X3", "X3", null, TYPE_HANKAKUEISU, "500", null},
	{"effFromDt_MX", "effFromDt_MX", "MX", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_MX", "effThruDt_MX", "MX", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_MX", "ezUpTime_MX", "MX", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_MX", "ezUpTimeZone_MX", "MX", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_MX
        {"PRC_MKT_PRMO_AUDC_CTRL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoAudcCtrlPk_MX
        {"PUB_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pubFlg_MX
        {"MKT_PRMO_AUDC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktPrmoAudcCatgCd_X1
        {"MKT_PRMO_AUDC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktPrmoAudcCatgCd_X2
        {"MKT_PRMO_AUDC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktPrmoAudcCatgCd_X3
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt_X1
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt_X2
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt_X3
        {"XX_REC_NM_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecNmTxt_X1
        {"XX_REC_NM_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecNmTxt_X2
        {"XX_REC_NM_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecNmTxt_X3
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_MX
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_MX
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_MX
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_MX
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
