//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170227165125000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0660_ASMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSAL0660 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0660_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_A1*/
	public final EZDSBigDecimalItem              dsContrPk_A1;

    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** XX_SCR_ITEM_34_TXT_A1*/
	public final EZDSStringItem              xxScrItem34Txt_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDSStringItem              dsAcctNm_A1;

    /** XX_FIRST_SCD_CTY_ST_ADDR_A1*/
	public final EZDSStringItem              xxFirstScdCtyStAddr_A1;

    /** CONTR_VRSN_EFF_FROM_DT_A1*/
	public final EZDSDateItem              contrVrsnEffFromDt_A1;

    /** CONTR_VRSN_EFF_THRU_DT_A1*/
	public final EZDSDateItem              contrVrsnEffThruDt_A1;

    /** DS_CONTR_CTRL_STS_NM_A1*/
	public final EZDSStringItem              dsContrCtrlStsNm_A1;

    /** XX_GENL_FLD_AREA_TXT_A1*/
	public final EZDSStringItem              xxGenlFldAreaTxt_A1;

    /** XX_ROW_NUM_A1*/
	public final EZDSBigDecimalItem              xxRowNum_A1;


	/**
	 * NSAL0660_ASMsg is constructor.
	 * The initialization when the instance of NSAL0660_ASMsg is generated.
	 */
	public NSAL0660_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0660_ASMsg is constructor.
	 * The initialization when the instance of NSAL0660_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0660_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_A1 = (EZDSBigDecimalItem)newItem("dsContrPk_A1");
		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		xxScrItem34Txt_A1 = (EZDSStringItem)newItem("xxScrItem34Txt_A1");
		dsAcctNm_A1 = (EZDSStringItem)newItem("dsAcctNm_A1");
		xxFirstScdCtyStAddr_A1 = (EZDSStringItem)newItem("xxFirstScdCtyStAddr_A1");
		contrVrsnEffFromDt_A1 = (EZDSDateItem)newItem("contrVrsnEffFromDt_A1");
		contrVrsnEffThruDt_A1 = (EZDSDateItem)newItem("contrVrsnEffThruDt_A1");
		dsContrCtrlStsNm_A1 = (EZDSStringItem)newItem("dsContrCtrlStsNm_A1");
		xxGenlFldAreaTxt_A1 = (EZDSStringItem)newItem("xxGenlFldAreaTxt_A1");
		xxRowNum_A1 = (EZDSBigDecimalItem)newItem("xxRowNum_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0660_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0660_ASMsgArray();
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
