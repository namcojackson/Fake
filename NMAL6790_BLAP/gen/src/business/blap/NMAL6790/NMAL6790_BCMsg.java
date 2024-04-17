//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170802125203000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6790_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6790;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6790 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6790_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B1*/
	public final EZDCStringItem              xxChkBox_B1;

    /** DS_CTAC_PNT_TP_CD_B1*/
	public final EZDCStringItem              dsCtacPntTpCd_B1;

    /** DS_CTAC_PNT_TP_NM_B1*/
	public final EZDCStringItem              dsCtacPntTpNm_B1;

    /** DS_CTAC_PNT_VAL_TXT_B1*/
	public final EZDCStringItem              dsCtacPntValTxt_B1;

    /** DS_CTAC_PSN_EXTN_NUM_B1*/
	public final EZDCStringItem              dsCtacPsnExtnNum_B1;

    /** XX_CHK_BOX_B2*/
	public final EZDCStringItem              xxChkBox_B2;

    /** XX_CHK_BOX_B3*/
	public final EZDCStringItem              xxChkBox_B3;

    /** DS_OPS_OUT_FLG_B1*/
	public final EZDCStringItem              dsOpsOutFlg_B1;

    /** DS_CTAC_PNT_ACTV_FLG_B1*/
	public final EZDCStringItem              dsCtacPntActvFlg_B1;

    /** CTAC_PSN_PK_B1*/
	public final EZDCBigDecimalItem              ctacPsnPk_B1;

    /** DS_CTAC_PNT_PK_B1*/
	public final EZDCBigDecimalItem              dsCtacPntPk_B1;

    /** SVC_CTAC_TP_CD_B1*/
	public final EZDCStringItem              svcCtacTpCd_B1;


	/**
	 * NMAL6790_BCMsg is constructor.
	 * The initialization when the instance of NMAL6790_BCMsg is generated.
	 */
	public NMAL6790_BCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6790_BCMsg is constructor.
	 * The initialization when the instance of NMAL6790_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6790_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B1 = (EZDCStringItem)newItem("xxChkBox_B1");
		dsCtacPntTpCd_B1 = (EZDCStringItem)newItem("dsCtacPntTpCd_B1");
		dsCtacPntTpNm_B1 = (EZDCStringItem)newItem("dsCtacPntTpNm_B1");
		dsCtacPntValTxt_B1 = (EZDCStringItem)newItem("dsCtacPntValTxt_B1");
		dsCtacPsnExtnNum_B1 = (EZDCStringItem)newItem("dsCtacPsnExtnNum_B1");
		xxChkBox_B2 = (EZDCStringItem)newItem("xxChkBox_B2");
		xxChkBox_B3 = (EZDCStringItem)newItem("xxChkBox_B3");
		dsOpsOutFlg_B1 = (EZDCStringItem)newItem("dsOpsOutFlg_B1");
		dsCtacPntActvFlg_B1 = (EZDCStringItem)newItem("dsCtacPntActvFlg_B1");
		ctacPsnPk_B1 = (EZDCBigDecimalItem)newItem("ctacPsnPk_B1");
		dsCtacPntPk_B1 = (EZDCBigDecimalItem)newItem("dsCtacPntPk_B1");
		svcCtacTpCd_B1 = (EZDCStringItem)newItem("svcCtacTpCd_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6790_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6790_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsCtacPntTpCd_B1", "dsCtacPntTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsCtacPntTpNm_B1", "dsCtacPntTpNm_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	{"dsCtacPntValTxt_B1", "dsCtacPntValTxt_B1", "B1", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPsnExtnNum_B1", "dsCtacPsnExtnNum_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxChkBox_B2", "xxChkBox_B2", "B2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_B3", "xxChkBox_B3", "B3", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsOpsOutFlg_B1", "dsOpsOutFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsCtacPntActvFlg_B1", "dsCtacPntActvFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"ctacPsnPk_B1", "ctacPsnPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsCtacPntPk_B1", "dsCtacPntPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcCtacTpCd_B1", "svcCtacTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"DS_CTAC_PNT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntTpCd_B1
        {"DS_CTAC_PNT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntTpNm_B1
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_B1
        {"DS_CTAC_PSN_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnExtnNum_B1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B3
        {"DS_OPS_OUT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOpsOutFlg_B1
        {"DS_CTAC_PNT_ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntActvFlg_B1
        {"CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk_B1
        {"DS_CTAC_PNT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntPk_B1
        {"SVC_CTAC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCtacTpCd_B1
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
