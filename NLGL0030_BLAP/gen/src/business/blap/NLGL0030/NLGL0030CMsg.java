//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161005163332000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0030CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLGL0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLGL0030 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0030CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DPLY_TAB*/
	public final EZDCStringItem              xxDplyTab;

    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** GLBL_CMPY_CD_UR*/
	public final EZDCStringItem              glblCmpyCd_UR;

    /** USR_ID*/
	public final EZDCStringItem              usrId;

    /** WH_CD_H0*/
	public final EZDCStringItemArray              whCd_H0;

    /** XX_EDT_CD_NM_H0*/
	public final EZDCStringItemArray              xxEdtCdNm_H0;

    /** WH_CD_P0*/
	public final EZDCStringItem              whCd_P0;

    /** WMS_MDSE_CD_H0*/
	public final EZDCStringItem              wmsMdseCd_H0;

    /** XX_PAGE_SHOW_FROM_NUM_H0*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_H0;

    /** XX_PAGE_SHOW_TO_NUM_H0*/
	public final EZDCBigDecimalItem              xxPageShowToNum_H0;

    /** XX_PAGE_SHOW_OF_NUM_H0*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_H0;

    /** A*/
	public final business.blap.NLGL0030.NLGL0030_ACMsgArray              A;

    /** WH_CD_H1*/
	public final EZDCStringItemArray              whCd_H1;

    /** XX_EDT_CD_NM_H1*/
	public final EZDCStringItemArray              xxEdtCdNm_H1;

    /** WH_CD_P1*/
	public final EZDCStringItem              whCd_P1;

    /** MDSE_CD_H1*/
	public final EZDCStringItem              mdseCd_H1;

    /** B*/
	public final business.blap.NLGL0030.NLGL0030_BCMsgArray              B;


	/**
	 * NLGL0030CMsg is constructor.
	 * The initialization when the instance of NLGL0030CMsg is generated.
	 */
	public NLGL0030CMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0030CMsg is constructor.
	 * The initialization when the instance of NLGL0030CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0030CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDplyTab = (EZDCStringItem)newItem("xxDplyTab");
		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		glblCmpyCd_UR = (EZDCStringItem)newItem("glblCmpyCd_UR");
		usrId = (EZDCStringItem)newItem("usrId");
		whCd_H0 = (EZDCStringItemArray)newItemArray("whCd_H0");
		xxEdtCdNm_H0 = (EZDCStringItemArray)newItemArray("xxEdtCdNm_H0");
		whCd_P0 = (EZDCStringItem)newItem("whCd_P0");
		wmsMdseCd_H0 = (EZDCStringItem)newItem("wmsMdseCd_H0");
		xxPageShowFromNum_H0 = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_H0");
		xxPageShowToNum_H0 = (EZDCBigDecimalItem)newItem("xxPageShowToNum_H0");
		xxPageShowOfNum_H0 = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_H0");
		A = (business.blap.NLGL0030.NLGL0030_ACMsgArray)newMsgArray("A");
		whCd_H1 = (EZDCStringItemArray)newItemArray("whCd_H1");
		xxEdtCdNm_H1 = (EZDCStringItemArray)newItemArray("xxEdtCdNm_H1");
		whCd_P1 = (EZDCStringItem)newItem("whCd_P1");
		mdseCd_H1 = (EZDCStringItem)newItem("mdseCd_H1");
		B = (business.blap.NLGL0030.NLGL0030_BCMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0030CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0030CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"glblCmpyCd_UR", "glblCmpyCd_UR", "UR", null, TYPE_HANKAKUEISU, "4", null},
	{"usrId", "usrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"whCd_H0", "whCd_H0", "H0", "99", TYPE_HANKAKUEISU, "20", null},
	{"xxEdtCdNm_H0", "xxEdtCdNm_H0", "H0", "99", TYPE_HANKAKUEISU, "100", null},
	{"whCd_P0", "whCd_P0", "P0", null, TYPE_HANKAKUEISU, "20", null},
	{"wmsMdseCd_H0", "wmsMdseCd_H0", "H0", null, TYPE_HANKAKUEISU, "30", null},
	{"xxPageShowFromNum_H0", "xxPageShowFromNum_H0", "H0", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_H0", "xxPageShowToNum_H0", "H0", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_H0", "xxPageShowOfNum_H0", "H0", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "20", "business.blap.NLGL0030.NLGL0030_ACMsgArray", null, null},
	
	{"whCd_H1", "whCd_H1", "H1", "99", TYPE_HANKAKUEISU, "20", null},
	{"xxEdtCdNm_H1", "xxEdtCdNm_H1", "H1", "99", TYPE_HANKAKUEISU, "100", null},
	{"whCd_P1", "whCd_P1", "P1", null, TYPE_HANKAKUEISU, "20", null},
	{"mdseCd_H1", "mdseCd_H1", "H1", null, TYPE_HANKAKUEISU, "16", null},
	{"B", "B", null, "2", "business.blap.NLGL0030.NLGL0030_BCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_UR
        {"USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usrId
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_H0
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_H0
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_P0
        {"WMS_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsMdseCd_H0
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_H0
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_H0
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_H0
		null,	//A
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_H1
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_H1
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_P1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_H1
		null,	//B
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

