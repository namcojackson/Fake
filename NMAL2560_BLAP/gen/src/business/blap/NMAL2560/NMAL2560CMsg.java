//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530160126000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2560CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2560;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2560 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2560CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BIZ_AREA_ORG_CD*/
	public final EZDCStringItem              bizAreaOrgCd;

    /** BIZ_AREA_ORG_CD_L*/
	public final EZDCStringItemArray              bizAreaOrgCd_L;

    /** BIZ_AREA_ORG_DESC_TXT_L*/
	public final EZDCStringItemArray              bizAreaOrgDescTxt_L;

    /** XX_CHK_BOX*/
	public final EZDCStringItem              xxChkBox;

    /** STRU_DFN_CD_L*/
	public final EZDCStringItemArray              struDfnCd_L;

    /** STRU_DFN_DESC_TXT_L*/
	public final EZDCStringItemArray              struDfnDescTxt_L;

    /** A*/
	public final business.blap.NMAL2560.NMAL2560_ACMsgArray              A;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;


	/**
	 * NMAL2560CMsg is constructor.
	 * The initialization when the instance of NMAL2560CMsg is generated.
	 */
	public NMAL2560CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2560CMsg is constructor.
	 * The initialization when the instance of NMAL2560CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2560CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		bizAreaOrgCd = (EZDCStringItem)newItem("bizAreaOrgCd");
		bizAreaOrgCd_L = (EZDCStringItemArray)newItemArray("bizAreaOrgCd_L");
		bizAreaOrgDescTxt_L = (EZDCStringItemArray)newItemArray("bizAreaOrgDescTxt_L");
		xxChkBox = (EZDCStringItem)newItem("xxChkBox");
		struDfnCd_L = (EZDCStringItemArray)newItemArray("struDfnCd_L");
		struDfnDescTxt_L = (EZDCStringItemArray)newItemArray("struDfnDescTxt_L");
		A = (business.blap.NMAL2560.NMAL2560_ACMsgArray)newMsgArray("A");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2560CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2560CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"bizAreaOrgCd", "bizAreaOrgCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgCd_L", "bizAreaOrgCd_L", "L", "99", TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgDescTxt_L", "bizAreaOrgDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"struDfnCd_L", "struDfnCd_L", "L", "99", TYPE_HANKAKUEISU, "8", null},
	{"struDfnDescTxt_L", "struDfnDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "200", "business.blap.NMAL2560.NMAL2560_ACMsgArray", null, null},
	
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_L
        {"BIZ_AREA_ORG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgDescTxt_L
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"STRU_DFN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnCd_L
        {"STRU_DFN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnDescTxt_L
		null,	//A
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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
