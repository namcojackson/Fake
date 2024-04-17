//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220427141255000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPZC004001PMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPZC004001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPZC004001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** PO_ORD_NUM*/
	public final EZDPStringItem              poOrdNum;

    /** PO_STS_CD*/
	public final EZDPStringItem              poStsCd;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** PO_RCV_QTY*/
	public final EZDPBigDecimalItem              poRcvQty;

    /** PO_ORD_DTL_LINE_NUM*/
	public final EZDPStringItem              poOrdDtlLineNum;

    /** PO_INV_QTY*/
	public final EZDPBigDecimalItem              poInvQty;

    /** PO_APVL_STS_CD*/
	public final EZDPStringItem              poApvlStsCd;

    /** PO_APVL_PSN_CD*/
	public final EZDPStringItem              poApvlPsnCd;

    /** PO_APVL_DT*/
	public final EZDPDateItem              poApvlDt;

    /** PO_APVL_TS*/
	public final EZDPStringItem              poApvlTs;

    /** WRT_OFF_FLG*/
	public final EZDPStringItem              wrtOffFlg;

    /** xxMsgIdList*/
	public final business.parts.NPZC004001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NPZC004001PMsg is constructor.
	 * The initialization when the instance of NPZC004001PMsg is generated.
	 */
	public NPZC004001PMsg() {
		this(false, -1);
	}

	/**
	 * NPZC004001PMsg is constructor.
	 * The initialization when the instance of NPZC004001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPZC004001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		poOrdNum = (EZDPStringItem)newItem("poOrdNum");
		poStsCd = (EZDPStringItem)newItem("poStsCd");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		poRcvQty = (EZDPBigDecimalItem)newItem("poRcvQty");
		poOrdDtlLineNum = (EZDPStringItem)newItem("poOrdDtlLineNum");
		poInvQty = (EZDPBigDecimalItem)newItem("poInvQty");
		poApvlStsCd = (EZDPStringItem)newItem("poApvlStsCd");
		poApvlPsnCd = (EZDPStringItem)newItem("poApvlPsnCd");
		poApvlDt = (EZDPDateItem)newItem("poApvlDt");
		poApvlTs = (EZDPStringItem)newItem("poApvlTs");
		wrtOffFlg = (EZDPStringItem)newItem("wrtOffFlg");
		xxMsgIdList = (business.parts.NPZC004001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NPZC004001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPZC004001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"poOrdNum", "poOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"poStsCd", "poStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"poRcvQty", "poRcvQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"poOrdDtlLineNum", "poOrdDtlLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"poInvQty", "poInvQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"poApvlStsCd", "poApvlStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"poApvlPsnCd", "poApvlPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"poApvlDt", "poApvlDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"poApvlTs", "poApvlTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"wrtOffFlg", "wrtOffFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NPZC004001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum
        {"PO_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poStsCd
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"PO_RCV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvQty
        {"PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlLineNum
        {"PO_INV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poInvQty
        {"PO_APVL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poApvlStsCd
        {"PO_APVL_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poApvlPsnCd
        {"PO_APVL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poApvlDt
        {"PO_APVL_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poApvlTs
        {"WRT_OFF_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffFlg
		null,	//xxMsgIdList
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

