//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160920233608000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1050BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL1050;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL1050 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL1050BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ABC_ANLS_CLS_NM*/
	public final EZDBStringItem              abcAnlsClsNm;

    /** ABC_ANLS_CLS_NUM_SV*/
	public final EZDBStringItem              abcAnlsClsNum_SV;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** XX_SUPD_FLG*/
	public final EZDBStringItem              xxSupdFlg;

    /** A*/
	public final business.servlet.NLCL1050.NLCL1050_ABMsgArray              A;

    /** XX_POP_PRM*/
	public final EZDBStringItem              xxPopPrm;

    /** P*/
	public final business.servlet.NLCL1050.NLCL1050_PBMsgArray              P;


	/**
	 * NLCL1050BMsg is constructor.
	 * The initialization when the instance of NLCL1050BMsg is generated.
	 */
	public NLCL1050BMsg() {
		this(false, -1);
	}

	/**
	 * NLCL1050BMsg is constructor.
	 * The initialization when the instance of NLCL1050BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL1050BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		abcAnlsClsNm = (EZDBStringItem)newItem("abcAnlsClsNm");
		abcAnlsClsNum_SV = (EZDBStringItem)newItem("abcAnlsClsNum_SV");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		xxSupdFlg = (EZDBStringItem)newItem("xxSupdFlg");
		A = (business.servlet.NLCL1050.NLCL1050_ABMsgArray)newMsgArray("A");
		xxPopPrm = (EZDBStringItem)newItem("xxPopPrm");
		P = (business.servlet.NLCL1050.NLCL1050_PBMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL1050BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL1050BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"abcAnlsClsNm", "abcAnlsClsNm", null, null, TYPE_HANKAKUEISU, "40", null},
	{"abcAnlsClsNum_SV", "abcAnlsClsNum_SV", "SV", null, TYPE_HANKAKUEISU, "8", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxSupdFlg", "xxSupdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "99", "business.servlet.NLCL1050.NLCL1050_ABMsgArray", null, null},
	
	{"xxPopPrm", "xxPopPrm", null, null, TYPE_HANKAKUEISU, "300", null},
	{"P", "P", null, "10", "business.servlet.NLCL1050.NLCL1050_PBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ABC_ANLS_CLS_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abcAnlsClsNm
        {"ABC_ANLS_CLS_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abcAnlsClsNum_SV
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_SUPD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSupdFlg
		null,	//A
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm
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

