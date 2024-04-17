//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190402141953000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1410_EBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1410;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1410 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1410_EBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** RWS_NUM_E1*/
	public final EZDBStringItem              rwsNum_E1;

    /** ACTV_FLG_E1*/
	public final EZDBStringItem              actvFlg_E1;

    /** SCE_ORD_TP_NM_E1*/
	public final EZDBStringItem              sceOrdTpNm_E1;

    /** RTL_WH_NM_E1*/
	public final EZDBStringItem              rtlWhNm_E1;

    /** RTL_SWH_NM_E1*/
	public final EZDBStringItem              rtlSwhNm_E1;

    /** SHPG_STS_DESC_TXT_E1*/
	public final EZDBStringItem              shpgStsDescTxt_E1;

    /** XX_CRAT_DT_E1*/
	public final EZDBDateItem              xxCratDt_E1;


	/**
	 * NPAL1410_EBMsg is constructor.
	 * The initialization when the instance of NPAL1410_EBMsg is generated.
	 */
	public NPAL1410_EBMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1410_EBMsg is constructor.
	 * The initialization when the instance of NPAL1410_EBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1410_EBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		rwsNum_E1 = (EZDBStringItem)newItem("rwsNum_E1");
		actvFlg_E1 = (EZDBStringItem)newItem("actvFlg_E1");
		sceOrdTpNm_E1 = (EZDBStringItem)newItem("sceOrdTpNm_E1");
		rtlWhNm_E1 = (EZDBStringItem)newItem("rtlWhNm_E1");
		rtlSwhNm_E1 = (EZDBStringItem)newItem("rtlSwhNm_E1");
		shpgStsDescTxt_E1 = (EZDBStringItem)newItem("shpgStsDescTxt_E1");
		xxCratDt_E1 = (EZDBDateItem)newItem("xxCratDt_E1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1410_EBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1410_EBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"rwsNum_E1", "rwsNum_E1", "E1", null, TYPE_HANKAKUEISU, "8", null},
	{"actvFlg_E1", "actvFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"sceOrdTpNm_E1", "sceOrdTpNm_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlWhNm_E1", "rtlWhNm_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhNm_E1", "rtlSwhNm_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"shpgStsDescTxt_E1", "shpgStsDescTxt_E1", "E1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxCratDt_E1", "xxCratDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"RWS_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum_E1
        {"ACTV_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_E1
        {"SCE_ORD_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpNm_E1
        {"RTL_WH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_E1
        {"RTL_SWH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_E1
        {"SHPG_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsDescTxt_E1
        {"XX_CRAT_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxCratDt_E1
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

