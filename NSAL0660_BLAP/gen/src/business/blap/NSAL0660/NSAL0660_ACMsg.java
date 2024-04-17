//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170227165123000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0660_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0660;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0660 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0660_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_A1*/
	public final EZDCBigDecimalItem              dsContrPk_A1;

    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** XX_SCR_ITEM_34_TXT_A1*/
	public final EZDCStringItem              xxScrItem34Txt_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDCStringItem              dsAcctNm_A1;

    /** XX_FIRST_SCD_CTY_ST_ADDR_A1*/
	public final EZDCStringItem              xxFirstScdCtyStAddr_A1;

    /** CONTR_VRSN_EFF_FROM_DT_A1*/
	public final EZDCDateItem              contrVrsnEffFromDt_A1;

    /** CONTR_VRSN_EFF_THRU_DT_A1*/
	public final EZDCDateItem              contrVrsnEffThruDt_A1;

    /** DS_CONTR_CTRL_STS_NM_A1*/
	public final EZDCStringItem              dsContrCtrlStsNm_A1;

    /** XX_GENL_FLD_AREA_TXT_A1*/
	public final EZDCStringItem              xxGenlFldAreaTxt_A1;

    /** XX_ROW_NUM_A1*/
	public final EZDCBigDecimalItem              xxRowNum_A1;


	/**
	 * NSAL0660_ACMsg is constructor.
	 * The initialization when the instance of NSAL0660_ACMsg is generated.
	 */
	public NSAL0660_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0660_ACMsg is constructor.
	 * The initialization when the instance of NSAL0660_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0660_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_A1 = (EZDCBigDecimalItem)newItem("dsContrPk_A1");
		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		xxScrItem34Txt_A1 = (EZDCStringItem)newItem("xxScrItem34Txt_A1");
		dsAcctNm_A1 = (EZDCStringItem)newItem("dsAcctNm_A1");
		xxFirstScdCtyStAddr_A1 = (EZDCStringItem)newItem("xxFirstScdCtyStAddr_A1");
		contrVrsnEffFromDt_A1 = (EZDCDateItem)newItem("contrVrsnEffFromDt_A1");
		contrVrsnEffThruDt_A1 = (EZDCDateItem)newItem("contrVrsnEffThruDt_A1");
		dsContrCtrlStsNm_A1 = (EZDCStringItem)newItem("dsContrCtrlStsNm_A1");
		xxGenlFldAreaTxt_A1 = (EZDCStringItem)newItem("xxGenlFldAreaTxt_A1");
		xxRowNum_A1 = (EZDCBigDecimalItem)newItem("xxRowNum_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0660_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0660_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk_A1", "dsContrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxScrItem34Txt_A1", "xxScrItem34Txt_A1", "A1", null, TYPE_HANKAKUEISU, "34", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"xxFirstScdCtyStAddr_A1", "xxFirstScdCtyStAddr_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"contrVrsnEffFromDt_A1", "contrVrsnEffFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"contrVrsnEffThruDt_A1", "contrVrsnEffThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrCtrlStsNm_A1", "dsContrCtrlStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxGenlFldAreaTxt_A1", "xxGenlFldAreaTxt_A1", "A1", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRowNum_A1", "xxRowNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"XX_SCR_ITEM_34_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem34Txt_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"XX_FIRST_SCD_CTY_ST_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFirstScdCtyStAddr_A1
        {"CONTR_VRSN_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrVrsnEffFromDt_A1
        {"CONTR_VRSN_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrVrsnEffThruDt_A1
        {"DS_CONTR_CTRL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsNm_A1
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_A1
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A1
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

