//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160622132518000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2720_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2720;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2720 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2720_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** ORG_NM_A1*/
	public final EZDSStringItem              orgNm_A1;

    /** ORG_DESC_TXT_A1*/
	public final EZDSStringItem              orgDescTxt_A1;

    /** BIZ_AREA_ORG_DESC_TXT_A1*/
	public final EZDSStringItem              bizAreaOrgDescTxt_A1;

    /** LINE_BIZ_TP_DESC_TXT_A1*/
	public final EZDSStringItem              lineBizTpDescTxt_A1;

    /** XX_PSN_NM_A1*/
	public final EZDSStringItem              xxPsnNm_A1;

    /** PSN_NUM_A1*/
	public final EZDSStringItem              psnNum_A1;

    /** ORG_FUNC_ROLE_TP_DESC_TXT_A1*/
	public final EZDSStringItem              orgFuncRoleTpDescTxt_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDSDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDSDateItem              effThruDt_A1;


	/**
	 * NMAL2720_ASMsg is constructor.
	 * The initialization when the instance of NMAL2720_ASMsg is generated.
	 */
	public NMAL2720_ASMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2720_ASMsg is constructor.
	 * The initialization when the instance of NMAL2720_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2720_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		orgNm_A1 = (EZDSStringItem)newItem("orgNm_A1");
		orgDescTxt_A1 = (EZDSStringItem)newItem("orgDescTxt_A1");
		bizAreaOrgDescTxt_A1 = (EZDSStringItem)newItem("bizAreaOrgDescTxt_A1");
		lineBizTpDescTxt_A1 = (EZDSStringItem)newItem("lineBizTpDescTxt_A1");
		xxPsnNm_A1 = (EZDSStringItem)newItem("xxPsnNm_A1");
		psnNum_A1 = (EZDSStringItem)newItem("psnNum_A1");
		orgFuncRoleTpDescTxt_A1 = (EZDSStringItem)newItem("orgFuncRoleTpDescTxt_A1");
		effFromDt_A1 = (EZDSDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDSDateItem)newItem("effThruDt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2720_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2720_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"orgNm_A1", "orgNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgDescTxt_A1", "orgDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"bizAreaOrgDescTxt_A1", "bizAreaOrgDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"lineBizTpDescTxt_A1", "lineBizTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxPsnNm_A1", "xxPsnNm_A1", "A1", null, TYPE_HANKAKUEISU, "62", null},
	{"psnNum_A1", "psnNum_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgFuncRoleTpDescTxt_A1", "orgFuncRoleTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_A1
        {"ORG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgDescTxt_A1
        {"BIZ_AREA_ORG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgDescTxt_A1
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_A1
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_A1
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_A1
        {"ORG_FUNC_ROLE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpDescTxt_A1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A1
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
