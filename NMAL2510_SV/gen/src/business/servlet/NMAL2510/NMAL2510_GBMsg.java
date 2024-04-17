//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160309123203000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2510_GBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2510;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2510 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2510_GBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** REV_ACCT_TP_CD_G1*/
	public final EZDBStringItem              revAcctTpCd_G1;

    /** COA_CMPY_CD_G2*/
	public final EZDBStringItem              coaCmpyCd_G2;

    /** COA_EXTN_CD_G2*/
	public final EZDBStringItem              coaExtnCd_G2;

    /** COA_BR_CD_G2*/
	public final EZDBStringItem              coaBrCd_G2;

    /** COA_CC_CD_G2*/
	public final EZDBStringItem              coaCcCd_G2;

    /** EFF_FROM_DT_G2*/
	public final EZDBDateItem              effFromDt_G2;

    /** EFF_THRU_DT_G2*/
	public final EZDBDateItem              effThruDt_G2;

    /** XX_CHK_BOX_G3*/
	public final EZDBStringItem              xxChkBox_G3;


	/**
	 * NMAL2510_GBMsg is constructor.
	 * The initialization when the instance of NMAL2510_GBMsg is generated.
	 */
	public NMAL2510_GBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2510_GBMsg is constructor.
	 * The initialization when the instance of NMAL2510_GBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2510_GBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		revAcctTpCd_G1 = (EZDBStringItem)newItem("revAcctTpCd_G1");
		coaCmpyCd_G2 = (EZDBStringItem)newItem("coaCmpyCd_G2");
		coaExtnCd_G2 = (EZDBStringItem)newItem("coaExtnCd_G2");
		coaBrCd_G2 = (EZDBStringItem)newItem("coaBrCd_G2");
		coaCcCd_G2 = (EZDBStringItem)newItem("coaCcCd_G2");
		effFromDt_G2 = (EZDBDateItem)newItem("effFromDt_G2");
		effThruDt_G2 = (EZDBDateItem)newItem("effThruDt_G2");
		xxChkBox_G3 = (EZDBStringItem)newItem("xxChkBox_G3");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2510_GBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2510_GBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"revAcctTpCd_G1", "revAcctTpCd_G1", "G1", null, TYPE_HANKAKUEISU, "5", null},
	{"coaCmpyCd_G2", "coaCmpyCd_G2", "G2", null, TYPE_HANKAKUEISU, "3", null},
	{"coaExtnCd_G2", "coaExtnCd_G2", "G2", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd_G2", "coaBrCd_G2", "G2", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_G2", "coaCcCd_G2", "G2", null, TYPE_HANKAKUEISU, "6", null},
	{"effFromDt_G2", "effFromDt_G2", "G2", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_G2", "effThruDt_G2", "G2", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_G3", "xxChkBox_G3", "G3", null, TYPE_HANKAKUEIJI, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"REV_ACCT_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//revAcctTpCd_G1
        {"COA_CMPY_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_G2
        {"COA_EXTN_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_G2
        {"COA_BR_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_G2
        {"COA_CC_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_G2
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_G2
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_G2
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_G3
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
