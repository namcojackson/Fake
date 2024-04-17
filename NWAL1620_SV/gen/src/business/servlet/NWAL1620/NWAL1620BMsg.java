//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170920165917000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1620BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1620;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1620 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1620BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MODE_CD_CP*/
	public final EZDBStringItem              xxModeCd_CP;

    /** XX_MODE_CD*/
	public final EZDBStringItem              xxModeCd;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** CPO_ORD_NUM*/
	public final EZDBStringItem              cpoOrdNum;

    /** CPO_ORD_NUM_BK*/
	public final EZDBStringItem              cpoOrdNum_BK;

    /** XX_CHK_BOX*/
	public final EZDBStringItem              xxChkBox;

    /** XX_CHK_BOX_BK*/
	public final EZDBStringItem              xxChkBox_BK;

    /** CONFIG_CATG_CD*/
	public final EZDBStringItem              configCatgCd;

    /** CONFIG_CATG_CD_BK*/
	public final EZDBStringItem              configCatgCd_BK;

    /** CONFIG_CATG_CD_CD*/
	public final EZDBStringItemArray              configCatgCd_CD;

    /** CONFIG_CATG_DESC_TXT_DI*/
	public final EZDBStringItemArray              configCatgDescTxt_DI;

    /** DS_ORD_POSN_NUM*/
	public final EZDBStringItem              dsOrdPosnNum;

    /** DS_ORD_POSN_NUM_BK*/
	public final EZDBStringItem              dsOrdPosnNum_BK;

    /** DS_CPO_LINE_NUM*/
	public final EZDBBigDecimalItem              dsCpoLineNum;

    /** DS_CPO_LINE_NUM_BK*/
	public final EZDBBigDecimalItem              dsCpoLineNum_BK;

    /** XX_QTY_10_NUM*/
	public final EZDBBigDecimalItem              xxQty10Num;

    /** XX_QTY_10_NUM_BK*/
	public final EZDBBigDecimalItem              xxQty10Num_BK;

    /** XX_CHK_BOX_R*/
	public final EZDBStringItem              xxChkBox_R;

    /** XX_CHK_BOX_BR*/
	public final EZDBStringItem              xxChkBox_BR;

    /** XX_LINE_NUM_DI*/
	public final EZDBStringItem              xxLineNum_DI;


	/**
	 * NWAL1620BMsg is constructor.
	 * The initialization when the instance of NWAL1620BMsg is generated.
	 */
	public NWAL1620BMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1620BMsg is constructor.
	 * The initialization when the instance of NWAL1620BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1620BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxModeCd_CP = (EZDBStringItem)newItem("xxModeCd_CP");
		xxModeCd = (EZDBStringItem)newItem("xxModeCd");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		cpoOrdNum = (EZDBStringItem)newItem("cpoOrdNum");
		cpoOrdNum_BK = (EZDBStringItem)newItem("cpoOrdNum_BK");
		xxChkBox = (EZDBStringItem)newItem("xxChkBox");
		xxChkBox_BK = (EZDBStringItem)newItem("xxChkBox_BK");
		configCatgCd = (EZDBStringItem)newItem("configCatgCd");
		configCatgCd_BK = (EZDBStringItem)newItem("configCatgCd_BK");
		configCatgCd_CD = (EZDBStringItemArray)newItemArray("configCatgCd_CD");
		configCatgDescTxt_DI = (EZDBStringItemArray)newItemArray("configCatgDescTxt_DI");
		dsOrdPosnNum = (EZDBStringItem)newItem("dsOrdPosnNum");
		dsOrdPosnNum_BK = (EZDBStringItem)newItem("dsOrdPosnNum_BK");
		dsCpoLineNum = (EZDBBigDecimalItem)newItem("dsCpoLineNum");
		dsCpoLineNum_BK = (EZDBBigDecimalItem)newItem("dsCpoLineNum_BK");
		xxQty10Num = (EZDBBigDecimalItem)newItem("xxQty10Num");
		xxQty10Num_BK = (EZDBBigDecimalItem)newItem("xxQty10Num_BK");
		xxChkBox_R = (EZDBStringItem)newItem("xxChkBox_R");
		xxChkBox_BR = (EZDBStringItem)newItem("xxChkBox_BR");
		xxLineNum_DI = (EZDBStringItem)newItem("xxLineNum_DI");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1620BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1620BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxModeCd_CP", "xxModeCd_CP", "CP", null, TYPE_HANKAKUEISU, "2", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoOrdNum_BK", "cpoOrdNum_BK", "BK", null, TYPE_HANKAKUEISU, "8", null},
	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_BK", "xxChkBox_BK", "BK", null, TYPE_HANKAKUEIJI, "1", null},
	{"configCatgCd", "configCatgCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"configCatgCd_BK", "configCatgCd_BK", "BK", null, TYPE_HANKAKUEISU, "2", null},
	{"configCatgCd_CD", "configCatgCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"configCatgDescTxt_DI", "configCatgDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"dsOrdPosnNum_BK", "dsOrdPosnNum_BK", "BK", null, TYPE_HANKAKUEISU, "6", null},
	{"dsCpoLineNum", "dsCpoLineNum", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"dsCpoLineNum_BK", "dsCpoLineNum_BK", "BK", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxQty10Num", "xxQty10Num", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxQty10Num_BK", "xxQty10Num_BK", "BK", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxChkBox_R", "xxChkBox_R", "R", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_BR", "xxChkBox_BR", "BR", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxLineNum_DI", "xxLineNum_DI", "DI", null, TYPE_HANKAKUEISU, "11", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MODE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_CP
        {"XX_MODE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_BK
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_BK
        {"CONFIG_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configCatgCd
        {"CONFIG_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configCatgCd_BK
        {"CONFIG_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configCatgCd_CD
        {"CONFIG_CATG_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configCatgDescTxt_DI
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_BK
        {"DS_CPO_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineNum
        {"DS_CPO_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineNum_BK
        {"XX_QTY_10_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQty10Num
        {"XX_QTY_10_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQty10Num_BK
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_R
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_BR
        {"XX_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_DI
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

