//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20171203215148000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1400_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1400;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1400 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1400_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime_A*/
	public final EZDSStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDSStringItem              ezUpTimeZone_A;

    /** ACCT_CONTR_ADMIN_RELN_PK_A*/
	public final EZDSBigDecimalItem              acctContrAdminRelnPk_A;

    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** SVC_LINE_BIZ_CD_A*/
	public final EZDSStringItem              svcLineBizCd_A;

    /** DS_ACCT_NUM_A*/
	public final EZDSStringItem              dsAcctNum_A;

    /** DS_ACCT_NM_A*/
	public final EZDSStringItem              dsAcctNm_A;

    /** CONTR_ADMIN_PSN_CD_A*/
	public final EZDSStringItem              contrAdminPsnCd_A;

    /** XX_CUST_PSN_NM_A*/
	public final EZDSStringItem              xxCustPsnNm_A;

    /** XX_CHK_BOX_A2*/
	public final EZDSStringItem              xxChkBox_A2;

    /** EFF_FROM_DT_A*/
	public final EZDSDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDSDateItem              effThruDt_A;


	/**
	 * NSAL1400_ASMsg is constructor.
	 * The initialization when the instance of NSAL1400_ASMsg is generated.
	 */
	public NSAL1400_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1400_ASMsg is constructor.
	 * The initialization when the instance of NSAL1400_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1400_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime_A = (EZDSStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDSStringItem)newItem("ezUpTimeZone_A");
		acctContrAdminRelnPk_A = (EZDSBigDecimalItem)newItem("acctContrAdminRelnPk_A");
		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		svcLineBizCd_A = (EZDSStringItem)newItem("svcLineBizCd_A");
		dsAcctNum_A = (EZDSStringItem)newItem("dsAcctNum_A");
		dsAcctNm_A = (EZDSStringItem)newItem("dsAcctNm_A");
		contrAdminPsnCd_A = (EZDSStringItem)newItem("contrAdminPsnCd_A");
		xxCustPsnNm_A = (EZDSStringItem)newItem("xxCustPsnNm_A");
		xxChkBox_A2 = (EZDSStringItem)newItem("xxChkBox_A2");
		effFromDt_A = (EZDSDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDSDateItem)newItem("effThruDt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1400_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1400_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"acctContrAdminRelnPk_A", "acctContrAdminRelnPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"svcLineBizCd_A", "svcLineBizCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_A", "dsAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A", "dsAcctNm_A", "A", null, TYPE_HANKAKUEISU, "360", null},
	{"contrAdminPsnCd_A", "contrAdminPsnCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"xxCustPsnNm_A", "xxCustPsnNm_A", "A", null, TYPE_HANKAKUEISU, "450", null},
	{"xxChkBox_A2", "xxChkBox_A2", "A2", null, TYPE_HANKAKUEIJI, "1", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"ACCT_CONTR_ADMIN_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctContrAdminRelnPk_A
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"SVC_LINE_BIZ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_A
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A
        {"CONTR_ADMIN_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAdminPsnCd_A
        {"XX_CUST_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustPsnNm_A
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A2
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
