//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160107135830000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2090BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2090 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2090BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** CPO_ORD_NUM*/
	public final EZDBStringItem              cpoOrdNum;

    /** CPO_ORD_NUM_BK*/
	public final EZDBStringItem              cpoOrdNum_BK;

    /** CHNG_RSN_TP_CD*/
	public final EZDBStringItem              chngRsnTpCd;

    /** CHNG_RSN_TP_CD_BK*/
	public final EZDBStringItem              chngRsnTpCd_BK;

    /** CHNG_RSN_TP_CD_CD*/
	public final EZDBStringItemArray              chngRsnTpCd_CD;

    /** CHNG_RSN_TP_NM_DI*/
	public final EZDBStringItemArray              chngRsnTpNm_DI;

    /** XX_COMN_SCR_COL_VAL_TXT*/
	public final EZDBStringItem              xxComnScrColValTxt;

    /** XX_COMN_SCR_COL_VAL_TXT_BK*/
	public final EZDBStringItem              xxComnScrColValTxt_BK;


	/**
	 * NWAL2090BMsg is constructor.
	 * The initialization when the instance of NWAL2090BMsg is generated.
	 */
	public NWAL2090BMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2090BMsg is constructor.
	 * The initialization when the instance of NWAL2090BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2090BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		cpoOrdNum = (EZDBStringItem)newItem("cpoOrdNum");
		cpoOrdNum_BK = (EZDBStringItem)newItem("cpoOrdNum_BK");
		chngRsnTpCd = (EZDBStringItem)newItem("chngRsnTpCd");
		chngRsnTpCd_BK = (EZDBStringItem)newItem("chngRsnTpCd_BK");
		chngRsnTpCd_CD = (EZDBStringItemArray)newItemArray("chngRsnTpCd_CD");
		chngRsnTpNm_DI = (EZDBStringItemArray)newItemArray("chngRsnTpNm_DI");
		xxComnScrColValTxt = (EZDBStringItem)newItem("xxComnScrColValTxt");
		xxComnScrColValTxt_BK = (EZDBStringItem)newItem("xxComnScrColValTxt_BK");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2090BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2090BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoOrdNum_BK", "cpoOrdNum_BK", "BK", null, TYPE_HANKAKUEISU, "8", null},
	{"chngRsnTpCd", "chngRsnTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"chngRsnTpCd_BK", "chngRsnTpCd_BK", "BK", null, TYPE_HANKAKUEISU, "1", null},
	{"chngRsnTpCd_CD", "chngRsnTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "1", null},
	{"chngRsnTpNm_DI", "chngRsnTpNm_DI", "DI", "99", TYPE_HANKAKUEISU, "25", null},
	{"xxComnScrColValTxt", "xxComnScrColValTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_BK", "xxComnScrColValTxt_BK", "BK", null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_BK
        {"CHNG_RSN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//chngRsnTpCd
        {"CHNG_RSN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//chngRsnTpCd_BK
        {"CHNG_RSN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//chngRsnTpCd_CD
        {"CHNG_RSN_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//chngRsnTpNm_DI
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_BK
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
