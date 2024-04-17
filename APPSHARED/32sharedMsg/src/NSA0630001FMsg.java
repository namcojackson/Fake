//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160624101909000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSA0630001FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSA0630001 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NSA0630001FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PROC_MODE_CD*/
	public final EZDFStringItem              procModeCd;

    /** SVC_MACH_MSTR_PK*/
	public final EZDFBigDecimalItem              svcMachMstrPk;

    /** SER_NUM*/
	public final EZDFStringItem              serNum;

    /** CTAC_PSN_PK*/
	public final EZDFBigDecimalItem              ctacPsnPk;

    /** CTAC_PSN_LAST_NM*/
	public final EZDFStringItem              ctacPsnLastNm;

    /** CTAC_PSN_FIRST_NM*/
	public final EZDFStringItem              ctacPsnFirstNm;

    /** DS_CTAC_PNT_TP_CD*/
	public final EZDFStringItem              dsCtacPntTpCd;

    /** DS_CTAC_PNT_VAL_TXT*/
	public final EZDFStringItem              dsCtacPntValTxt;

    /** DS_CTAC_PSN_EXTN_NUM*/
	public final EZDFStringItem              dsCtacPsnExtnNum;

    /** SVC_CTAC_TP_CD*/
	public final EZDFStringItem              svcCtacTpCd;

    /** DS_CTAC_PNT_ACTV_FLG*/
	public final EZDFStringItem              dsCtacPntActvFlg;

    /** EFF_FROM_DT*/
	public final EZDFDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDFDateItem              effThruDt;


	/**
	 * NSA0630001FMsg is constructor.
	 * The initialization when the instance of NSA0630001FMsg is generated.
	 */
	public NSA0630001FMsg() {
		this(false, -1);
	}

	/**
	 * NSA0630001FMsg is constructor.
	 * The initialization when the instance of NSA0630001FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSA0630001FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		procModeCd = (EZDFStringItem)newItem("procModeCd");
		svcMachMstrPk = (EZDFBigDecimalItem)newItem("svcMachMstrPk");
		serNum = (EZDFStringItem)newItem("serNum");
		ctacPsnPk = (EZDFBigDecimalItem)newItem("ctacPsnPk");
		ctacPsnLastNm = (EZDFStringItem)newItem("ctacPsnLastNm");
		ctacPsnFirstNm = (EZDFStringItem)newItem("ctacPsnFirstNm");
		dsCtacPntTpCd = (EZDFStringItem)newItem("dsCtacPntTpCd");
		dsCtacPntValTxt = (EZDFStringItem)newItem("dsCtacPntValTxt");
		dsCtacPsnExtnNum = (EZDFStringItem)newItem("dsCtacPsnExtnNum");
		svcCtacTpCd = (EZDFStringItem)newItem("svcCtacTpCd");
		dsCtacPntActvFlg = (EZDFStringItem)newItem("dsCtacPntActvFlg");
		effFromDt = (EZDFDateItem)newItem("effFromDt");
		effThruDt = (EZDFDateItem)newItem("effThruDt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSA0630001FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSA0630001FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"procModeCd", "procModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnPk", "ctacPsnPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnLastNm", "ctacPsnLastNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnFirstNm", "ctacPsnFirstNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsCtacPntTpCd", "dsCtacPntTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsCtacPntValTxt", "dsCtacPntValTxt", null, null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPsnExtnNum", "dsCtacPsnExtnNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcCtacTpCd", "svcCtacTpCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsCtacPntActvFlg", "dsCtacPntActvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PROC_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procModeCd
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk
        {"CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm
        {"DS_CTAC_PNT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntTpCd
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt
        {"DS_CTAC_PSN_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnExtnNum
        {"SVC_CTAC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCtacTpCd
        {"DS_CTAC_PNT_ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntActvFlg
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
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
