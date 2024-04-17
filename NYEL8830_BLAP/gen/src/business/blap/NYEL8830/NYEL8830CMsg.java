//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180516094218000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8830CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8830;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8830 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8830CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MENU_PROC_GRP_CD*/
	public final EZDCStringItem              menuProcGrpCd;

    /** MENU_PROC_ID*/
	public final EZDCStringItem              menuProcId;

    /** UP_TAB_NM*/
	public final EZDCStringItem              upTabNm;

    /** WF_PROC_PK*/
	public final EZDCBigDecimalItem              wfProcPk;

    /** WF_PROC_NM*/
	public final EZDCStringItem              wfProcNm;

    /** WF_PROC_STS_CD*/
	public final EZDCStringItem              wfProcStsCd;

    /** XX_WF_PROC_STS_NM*/
	public final EZDCStringItem              xxWfProcStsNm;

    /** WF_DESC_TXT_PR*/
	public final EZDCStringItem              wfDescTxt_PR;

    /** WF_BIZ_ATTRB_TXT_01*/
	public final EZDCStringItem              wfBizAttrbTxt_01;

    /** WF_WRK_ITEM_PK*/
	public final EZDCBigDecimalItem              wfWrkItemPk;

    /** XX_DT_TM_PE*/
	public final EZDCStringItem              xxDtTm_PE;

    /** A*/
	public final business.blap.NYEL8830.NYEL8830_ACMsgArray              A;

    /** B*/
	public final business.blap.NYEL8830.NYEL8830_BCMsgArray              B;

    /** Z*/
	public final business.blap.NYEL8830.NYEL8830_ZCMsgArray              Z;

    /** P*/
	public final business.blap.NYEL8830.NYEL8830_PCMsgArray              P;


	/**
	 * NYEL8830CMsg is constructor.
	 * The initialization when the instance of NYEL8830CMsg is generated.
	 */
	public NYEL8830CMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8830CMsg is constructor.
	 * The initialization when the instance of NYEL8830CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8830CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		menuProcGrpCd = (EZDCStringItem)newItem("menuProcGrpCd");
		menuProcId = (EZDCStringItem)newItem("menuProcId");
		upTabNm = (EZDCStringItem)newItem("upTabNm");
		wfProcPk = (EZDCBigDecimalItem)newItem("wfProcPk");
		wfProcNm = (EZDCStringItem)newItem("wfProcNm");
		wfProcStsCd = (EZDCStringItem)newItem("wfProcStsCd");
		xxWfProcStsNm = (EZDCStringItem)newItem("xxWfProcStsNm");
		wfDescTxt_PR = (EZDCStringItem)newItem("wfDescTxt_PR");
		wfBizAttrbTxt_01 = (EZDCStringItem)newItem("wfBizAttrbTxt_01");
		wfWrkItemPk = (EZDCBigDecimalItem)newItem("wfWrkItemPk");
		xxDtTm_PE = (EZDCStringItem)newItem("xxDtTm_PE");
		A = (business.blap.NYEL8830.NYEL8830_ACMsgArray)newMsgArray("A");
		B = (business.blap.NYEL8830.NYEL8830_BCMsgArray)newMsgArray("B");
		Z = (business.blap.NYEL8830.NYEL8830_ZCMsgArray)newMsgArray("Z");
		P = (business.blap.NYEL8830.NYEL8830_PCMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8830CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8830CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"menuProcGrpCd", "menuProcGrpCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"menuProcId", "menuProcId", null, null, TYPE_HANKAKUEISU, "18", null},
	{"upTabNm", "upTabNm", null, null, TYPE_HANKAKUEISU, "12", null},
	{"wfProcPk", "wfProcPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wfProcNm", "wfProcNm", null, null, TYPE_HANKAKUEISU, "40", null},
	{"wfProcStsCd", "wfProcStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxWfProcStsNm", "xxWfProcStsNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"wfDescTxt_PR", "wfDescTxt_PR", "PR", null, TYPE_HANKAKUEISU, "50", null},
	{"wfBizAttrbTxt_01", "wfBizAttrbTxt_01", "01", null, TYPE_HANKAKUEISU, "40", null},
	{"wfWrkItemPk", "wfWrkItemPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxDtTm_PE", "xxDtTm_PE", "PE", null, TYPE_HANKAKUEISU, "23", null},
	{"A", "A", null, "200", "business.blap.NYEL8830.NYEL8830_ACMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NYEL8830.NYEL8830_BCMsgArray", null, null},
	
	{"Z", "Z", null, "3", "business.blap.NYEL8830.NYEL8830_ZCMsgArray", null, null},
	
	{"P", "P", null, "200", "business.blap.NYEL8830.NYEL8830_PCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MENU_PROC_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcGrpCd
        {"MENU_PROC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcId
        {"UP_TAB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabNm
        {"WF_PROC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfProcPk
        {"WF_PROC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfProcNm
        {"WF_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfProcStsCd
        {"XX_WF_PROC_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfProcStsNm
        {"WF_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_PR
        {"WF_BIZ_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAttrbTxt_01
        {"WF_WRK_ITEM_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfWrkItemPk
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_PE
		null,	//A
		null,	//B
		null,	//Z
		null,	//P
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
