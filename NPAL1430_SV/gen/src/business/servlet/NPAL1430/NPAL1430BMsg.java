//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160810201808000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1430BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1430;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1430 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1430BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** RMNF_MDL_ID_AC*/
	public final EZDBBigDecimalItem              rmnfMdlId_AC;

    /** T_MDL_NM*/
	public final EZDBStringItem              t_MdlNm;

    /** RMNF_MDL_STD_PRT_DESC_TXT*/
	public final EZDBStringItem              rmnfMdlStdPrtDescTxt;

    /** LAST_UPD_DT*/
	public final EZDBDateItem              lastUpdDt;

    /** A*/
	public final business.servlet.NPAL1430.NPAL1430_ABMsgArray              A;

    /** P*/
	public final business.servlet.NPAL1430.NPAL1430_PBMsgArray              P;

    /** R*/
	public final business.servlet.NPAL1430.NPAL1430_RBMsgArray              R;


	/**
	 * NPAL1430BMsg is constructor.
	 * The initialization when the instance of NPAL1430BMsg is generated.
	 */
	public NPAL1430BMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1430BMsg is constructor.
	 * The initialization when the instance of NPAL1430BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1430BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		rmnfMdlId_AC = (EZDBBigDecimalItem)newItem("rmnfMdlId_AC");
		t_MdlNm = (EZDBStringItem)newItem("t_MdlNm");
		rmnfMdlStdPrtDescTxt = (EZDBStringItem)newItem("rmnfMdlStdPrtDescTxt");
		lastUpdDt = (EZDBDateItem)newItem("lastUpdDt");
		A = (business.servlet.NPAL1430.NPAL1430_ABMsgArray)newMsgArray("A");
		P = (business.servlet.NPAL1430.NPAL1430_PBMsgArray)newMsgArray("P");
		R = (business.servlet.NPAL1430.NPAL1430_RBMsgArray)newMsgArray("R");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1430BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1430BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"rmnfMdlId_AC", "rmnfMdlId_AC", "AC", null, TYPE_SEISU_SYOSU, "20", "0"},
	{"t_MdlNm", "t_MdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"rmnfMdlStdPrtDescTxt", "rmnfMdlStdPrtDescTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"lastUpdDt", "lastUpdDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"A", "A", null, "99", "business.servlet.NPAL1430.NPAL1430_ABMsgArray", null, null},
	
	{"P", "P", null, "11", "business.servlet.NPAL1430.NPAL1430_PBMsgArray", null, null},
	
	{"R", "R", null, "10", "business.servlet.NPAL1430.NPAL1430_RBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"RMNF_MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfMdlId_AC
        {"T_MDL_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm
        {"RMNF_MDL_STD_PRT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfMdlStdPrtDescTxt
        {"LAST_UPD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//lastUpdDt
		null,	//A
		null,	//P
		null,	//R
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
