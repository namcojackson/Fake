//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160225081112000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1260_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1260;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1260 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1260_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_AA*/
	public final EZDBStringItem              xxChkBox_AA;

    /** SVC_MACH_MSTR_PK_A*/
	public final EZDBBigDecimalItem              svcMachMstrPk_A;

    /** SER_NUM_A*/
	public final EZDBStringItem              serNum_A;

    /** BLLG_CYCLE_CD_A*/
	public final EZDBStringItem              bllgCycleCd_A;

    /** BASE_PRC_DEAL_AMT_A*/
	public final EZDBBigDecimalItem              basePrcDealAmt_A;

    /** MTR_READ_METH_CD_A*/
	public final EZDBStringItem              mtrReadMethCd_A;

    /** BILL_TO_ACCT_NUM_A*/
	public final EZDBStringItem              billToAcctNum_A;

    /** DS_ACCT_NM_A*/
	public final EZDBStringItem              dsAcctNm_A;

    /** CONTR_VRSN_EFF_FROM_DT_A*/
	public final EZDBDateItem              contrVrsnEffFromDt_A;

    /** CONTR_VRSN_EFF_THRU_DT_A*/
	public final EZDBDateItem              contrVrsnEffThruDt_A;

    /** XX_CHK_BOX_AB*/
	public final EZDBStringItem              xxChkBox_AB;

    /** SVC_CONFIG_MSTR_PK_A*/
	public final EZDBBigDecimalItem              svcConfigMstrPk_A;

    /** XX_ERR_FLG_A*/
	public final EZDBStringItem              xxErrFlg_A;


	/**
	 * NSAL1260_ABMsg is constructor.
	 * The initialization when the instance of NSAL1260_ABMsg is generated.
	 */
	public NSAL1260_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1260_ABMsg is constructor.
	 * The initialization when the instance of NSAL1260_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1260_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_AA = (EZDBStringItem)newItem("xxChkBox_AA");
		svcMachMstrPk_A = (EZDBBigDecimalItem)newItem("svcMachMstrPk_A");
		serNum_A = (EZDBStringItem)newItem("serNum_A");
		bllgCycleCd_A = (EZDBStringItem)newItem("bllgCycleCd_A");
		basePrcDealAmt_A = (EZDBBigDecimalItem)newItem("basePrcDealAmt_A");
		mtrReadMethCd_A = (EZDBStringItem)newItem("mtrReadMethCd_A");
		billToAcctNum_A = (EZDBStringItem)newItem("billToAcctNum_A");
		dsAcctNm_A = (EZDBStringItem)newItem("dsAcctNm_A");
		contrVrsnEffFromDt_A = (EZDBDateItem)newItem("contrVrsnEffFromDt_A");
		contrVrsnEffThruDt_A = (EZDBDateItem)newItem("contrVrsnEffThruDt_A");
		xxChkBox_AB = (EZDBStringItem)newItem("xxChkBox_AB");
		svcConfigMstrPk_A = (EZDBBigDecimalItem)newItem("svcConfigMstrPk_A");
		xxErrFlg_A = (EZDBStringItem)newItem("xxErrFlg_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1260_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1260_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_AA", "xxChkBox_AA", "AA", null, TYPE_HANKAKUEIJI, "1", null},
	{"svcMachMstrPk_A", "svcMachMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"bllgCycleCd_A", "bllgCycleCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"basePrcDealAmt_A", "basePrcDealAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mtrReadMethCd_A", "mtrReadMethCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"billToAcctNum_A", "billToAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A", "dsAcctNm_A", "A", null, TYPE_HANKAKUEISU, "360", null},
	{"contrVrsnEffFromDt_A", "contrVrsnEffFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"contrVrsnEffThruDt_A", "contrVrsnEffThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_AB", "xxChkBox_AB", "AB", null, TYPE_HANKAKUEIJI, "1", null},
	{"svcConfigMstrPk_A", "svcConfigMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxErrFlg_A", "xxErrFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AA
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_A
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_A
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_A
        {"MTR_READ_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_A
        {"BILL_TO_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToAcctNum_A
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A
        {"CONTR_VRSN_EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrVrsnEffFromDt_A
        {"CONTR_VRSN_EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrVrsnEffThruDt_A
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AB
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A
        {"XX_ERR_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg_A
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
