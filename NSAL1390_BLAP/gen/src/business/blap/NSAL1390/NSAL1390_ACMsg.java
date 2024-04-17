//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170824174257000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1390_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1390;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1390 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1390_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** LINE_BIZ_TP_CD_A*/
	public final EZDCStringItem              lineBizTpCd_A;

    /** SVC_RG_PK_A*/
	public final EZDCBigDecimalItem              svcRgPk_A;

    /** SVC_RG_DESC_TXT_A*/
	public final EZDCStringItem              svcRgDescTxt_A;

    /** SVC_CONTR_BR_CD_A*/
	public final EZDCStringItem              svcContrBrCd_A;

    /** SVC_CONTR_BR_DESC_TXT_A*/
	public final EZDCStringItem              svcContrBrDescTxt_A;

    /** XX_CHK_BOX_A2*/
	public final EZDCStringItem              xxChkBox_A2;

    /** XX_CHK_BOX_A3*/
	public final EZDCStringItem              xxChkBox_A3;

    /** XX_CHK_BOX_A4*/
	public final EZDCStringItem              xxChkBox_A4;

    /** _EZUpdateDateTime_A*/
	public final EZDCStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDCStringItem              ezUpTimeZone_A;


	/**
	 * NSAL1390_ACMsg is constructor.
	 * The initialization when the instance of NSAL1390_ACMsg is generated.
	 */
	public NSAL1390_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1390_ACMsg is constructor.
	 * The initialization when the instance of NSAL1390_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1390_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		lineBizTpCd_A = (EZDCStringItem)newItem("lineBizTpCd_A");
		svcRgPk_A = (EZDCBigDecimalItem)newItem("svcRgPk_A");
		svcRgDescTxt_A = (EZDCStringItem)newItem("svcRgDescTxt_A");
		svcContrBrCd_A = (EZDCStringItem)newItem("svcContrBrCd_A");
		svcContrBrDescTxt_A = (EZDCStringItem)newItem("svcContrBrDescTxt_A");
		xxChkBox_A2 = (EZDCStringItem)newItem("xxChkBox_A2");
		xxChkBox_A3 = (EZDCStringItem)newItem("xxChkBox_A3");
		xxChkBox_A4 = (EZDCStringItem)newItem("xxChkBox_A4");
		ezUpTime_A = (EZDCStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDCStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1390_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1390_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"lineBizTpCd_A", "lineBizTpCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"svcRgPk_A", "svcRgPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcRgDescTxt_A", "svcRgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcContrBrCd_A", "svcContrBrCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt_A", "svcContrBrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxChkBox_A2", "xxChkBox_A2", "A2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_A3", "xxChkBox_A3", "A3", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_A4", "xxChkBox_A4", "A4", null, TYPE_HANKAKUEIJI, "1", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_A
        {"SVC_RG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgPk_A
        {"SVC_RG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgDescTxt_A
        {"SVC_CONTR_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_A
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_A
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A3
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A4
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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
