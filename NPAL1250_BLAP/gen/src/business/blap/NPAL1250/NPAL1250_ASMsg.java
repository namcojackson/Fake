//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160530215810000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1250_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1250;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1250 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1250_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SHIP_TO_CUST_PK_A*/
	public final EZDSBigDecimalItem              shipToCustPk_A;

    /** DS_ACCT_CUST_PK_A*/
	public final EZDSBigDecimalItem              dsAcctCustPk_A;

    /** SELL_TO_CUST_CD_A*/
	public final EZDSStringItem              sellToCustCd_A;

    /** DS_ACCT_NM_A*/
	public final EZDSStringItem              dsAcctNm_A;

    /** SHIP_TO_CUST_CD_A*/
	public final EZDSStringItem              shipToCustCd_A;

    /** LOC_NM_A*/
	public final EZDSStringItem              locNm_A;

    /** BIG_DEAL_NUM_A*/
	public final EZDSStringItem              bigDealNum_A;

    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDSStringItem              ezUpTimeZone_A1;

    /** _EZUpdateDateTime_A2*/
	public final EZDSStringItem              ezUpTime_A2;

    /** _EZUpTimeZone_A2*/
	public final EZDSStringItem              ezUpTimeZone_A2;

    /** _EZUpdateDateTime_A3*/
	public final EZDSStringItem              ezUpTime_A3;

    /** _EZUpTimeZone_A3*/
	public final EZDSStringItem              ezUpTimeZone_A3;

    /** EFF_FROM_DT_A*/
	public final EZDSDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDSDateItem              effThruDt_A;


	/**
	 * NPAL1250_ASMsg is constructor.
	 * The initialization when the instance of NPAL1250_ASMsg is generated.
	 */
	public NPAL1250_ASMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1250_ASMsg is constructor.
	 * The initialization when the instance of NPAL1250_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1250_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		shipToCustPk_A = (EZDSBigDecimalItem)newItem("shipToCustPk_A");
		dsAcctCustPk_A = (EZDSBigDecimalItem)newItem("dsAcctCustPk_A");
		sellToCustCd_A = (EZDSStringItem)newItem("sellToCustCd_A");
		dsAcctNm_A = (EZDSStringItem)newItem("dsAcctNm_A");
		shipToCustCd_A = (EZDSStringItem)newItem("shipToCustCd_A");
		locNm_A = (EZDSStringItem)newItem("locNm_A");
		bigDealNum_A = (EZDSStringItem)newItem("bigDealNum_A");
		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDSStringItem)newItem("ezUpTimeZone_A1");
		ezUpTime_A2 = (EZDSStringItem)newItem("ezUpTime_A2");
		ezUpTimeZone_A2 = (EZDSStringItem)newItem("ezUpTimeZone_A2");
		ezUpTime_A3 = (EZDSStringItem)newItem("ezUpTime_A3");
		ezUpTimeZone_A3 = (EZDSStringItem)newItem("ezUpTimeZone_A3");
		effFromDt_A = (EZDSDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDSDateItem)newItem("effThruDt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1250_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1250_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"shipToCustPk_A", "shipToCustPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAcctCustPk_A", "dsAcctCustPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"sellToCustCd_A", "sellToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A", "dsAcctNm_A", "A", null, TYPE_HANKAKUEISU, "360", null},
	{"shipToCustCd_A", "shipToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_A", "locNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"bigDealNum_A", "bigDealNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_A2", "ezUpTime_A2", "A2", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A2", "ezUpTimeZone_A2", "A2", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_A3", "ezUpTime_A3", "A3", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A3", "ezUpTimeZone_A3", "A3", null, TYPE_HANKAKUEISU, "5", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SHIP_TO_CUST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustPk_A
        {"DS_ACCT_CUST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctCustPk_A
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_A
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_A
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A
        {"BIG_DEAL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bigDealNum_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A2
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A2
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A3
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A3
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A
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

