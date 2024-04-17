//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20090709163651000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZSL1110CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZSL1110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZSL1110 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZSL1110CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_NM*/
	public final EZDCStringItem              xxScrNm;

    /** XX_FULL_NM*/
	public final EZDCStringItem              xxFullNm;

    /** XX_CUR_PG*/
	public final EZDCBigDecimalItem              xxCurPg;

    /** XX_ROW_NUM_MN*/
	public final EZDCBigDecimalItem              xxRowNum_MN;

    /** XX_ROW_NUM_MX*/
	public final EZDCBigDecimalItem              xxRowNum_MX;

    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** GLBL_CMPY_NM*/
	public final EZDCStringItem              glblCmpyNm;

    /** A*/
	public final business.blap.ZZSL1110.ZZSL1110_ACMsgArray              A;


	/**
	 * ZZSL1110CMsg is constructor.
	 * The initialization when the instance of ZZSL1110CMsg is generated.
	 */
	public ZZSL1110CMsg() {
		this(false, -1);
	}

	/**
	 * ZZSL1110CMsg is constructor.
	 * The initialization when the instance of ZZSL1110CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZSL1110CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrNm = (EZDCStringItem)newItem("xxScrNm");
		xxFullNm = (EZDCStringItem)newItem("xxFullNm");
		xxCurPg = (EZDCBigDecimalItem)newItem("xxCurPg");
		xxRowNum_MN = (EZDCBigDecimalItem)newItem("xxRowNum_MN");
		xxRowNum_MX = (EZDCBigDecimalItem)newItem("xxRowNum_MX");
		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		glblCmpyNm = (EZDCStringItem)newItem("glblCmpyNm");
		A = (business.blap.ZZSL1110.ZZSL1110_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZSL1110CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZSL1110CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"xxFullNm", "xxFullNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"xxCurPg", "xxCurPg", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxRowNum_MN", "xxRowNum_MN", "MN", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxRowNum_MX", "xxRowNum_MX", "MX", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"glblCmpyNm", "glblCmpyNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"A", "A", null, "200", "business.blap.ZZSL1110.ZZSL1110_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SCR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm
        {"XX_CUR_PG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCurPg
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_MN
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_MX
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"GLBL_CMPY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyNm
		null,	//A
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

