//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190724033545000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2200_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2200 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2200_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SMRY_LINE_FLG_L*/
	public final EZDCStringItem              xxSmryLineFlg_L;

    /** XX_CHK_BOX_LC*/
	public final EZDCStringItem              xxChkBox_LC;

    /** DS_IMPT_ORD_CONFIG_PK_LC*/
	public final EZDCBigDecimalItem              dsImptOrdConfigPk_LC;

    /** DS_IMPT_ORD_PK_LC*/
	public final EZDCBigDecimalItem              dsImptOrdPk_LC;

    /** DS_ORD_POSN_NUM_LC*/
	public final EZDCStringItem              dsOrdPosnNum_LC;

    /** CONFIG_CATG_CD_LC*/
	public final EZDCStringItem              configCatgCd_LC;

    /** CONFIG_TP_CD_LC*/
	public final EZDCStringItem              configTpCd_LC;

    /** CONFIG_TP_DESC_TXT_LC*/
	public final EZDCStringItem              configTpDescTxt_LC;

    /** SVC_CONFIG_MSTR_PK_LC*/
	public final EZDCBigDecimalItem              svcConfigMstrPk_LC;

    /** MDL_ID_LC*/
	public final EZDCBigDecimalItem              mdlId_LC;

    /** T_MDL_NM_LC*/
	public final EZDCStringItem              t_MdlNm_LC;

    /** ADDR_LB_TXT_LC*/
	public final EZDCStringItem              addrLbTxt_LC;

    /** BILL_TO_CUST_ACCT_CD_LC*/
	public final EZDCStringItem              billToCustAcctCd_LC;

    /** BILL_TO_CUST_LOC_CD_LC*/
	public final EZDCStringItem              billToCustLocCd_LC;

    /** XX_ALL_LINE_ADDR_LB*/
	public final EZDCStringItem              xxAllLineAddr_LB;

    /** SHIP_TO_CUST_ACCT_CD_LC*/
	public final EZDCStringItem              shipToCustAcctCd_LC;

    /** SHIP_TO_CUST_LOC_CD_LC*/
	public final EZDCStringItem              shipToCustLocCd_LC;

    /** DROP_SHIP_FLG_LC*/
	public final EZDCStringItem              dropShipFlg_LC;

    /** SHIP_TO_LOC_NM_LC*/
	public final EZDCStringItem              shipToLocNm_LC;

    /** SHIP_TO_ADDL_LOC_NM_LC*/
	public final EZDCStringItem              shipToAddlLocNm_LC;

    /** XX_ALL_LINE_ADDR_LS*/
	public final EZDCStringItem              xxAllLineAddr_LS;

    /** SHIP_TO_FIRST_LINE_ADDR_LC*/
	public final EZDCStringItem              shipToFirstLineAddr_LC;

    /** SHIP_TO_SCD_LINE_ADDR_LC*/
	public final EZDCStringItem              shipToScdLineAddr_LC;

    /** SHIP_TO_THIRD_LINE_ADDR_LC*/
	public final EZDCStringItem              shipToThirdLineAddr_LC;

    /** SHIP_TO_FRTH_LINE_ADDR_LC*/
	public final EZDCStringItem              shipToFrthLineAddr_LC;

    /** SHIP_TO_FIRST_REF_CMNT_TXT_LC*/
	public final EZDCStringItem              shipToFirstRefCmntTxt_LC;

    /** SHIP_TO_SCD_REF_CMNT_TXT_LC*/
	public final EZDCStringItem              shipToScdRefCmntTxt_LC;

    /** SHIP_TO_CTY_ADDR_LC*/
	public final EZDCStringItem              shipToCtyAddr_LC;

    /** SHIP_TO_ST_CD_LC*/
	public final EZDCStringItem              shipToStCd_LC;

    /** SHIP_TO_PROV_NM_LC*/
	public final EZDCStringItem              shipToProvNm_LC;

    /** SHIP_TO_CNTY_NM_LC*/
	public final EZDCStringItem              shipToCntyNm_LC;

    /** SHIP_TO_POST_CD_LC*/
	public final EZDCStringItem              shipToPostCd_LC;

    /** SHIP_TO_CTRY_CD_LC*/
	public final EZDCStringItem              shipToCtryCd_LC;

    /** CONFIG_CRAT_TS_LC*/
	public final EZDCStringItem              configCratTs_LC;

    /** SVC_SLN_SQ_LC*/
	public final EZDCBigDecimalItem              svcSlnSq_LC;

    /** SVC_SLN_NM_LC*/
	public final EZDCStringItem              svcSlnNm_LC;

    /** DCLN_SVC_CD_LC*/
	public final EZDCStringItem              dclnSvcCd_LC;

    /** EMSD_IMPT_STS_CD_LC*/
	public final EZDCStringItem              emsdImptStsCd_LC;

    /** _EZUpdateDateTime_LC*/
	public final EZDCStringItem              ezUpTime_LC;

    /** _EZUpTimeZone_LC*/
	public final EZDCStringItem              ezUpTimeZone_LC;

    /** XX_IMAGE_TXT_AC*/
	public final EZDCStringItem              xxImageTxt_AC;

    /** XX_IMAGE_TXT_AS*/
	public final EZDCStringItem              xxImageTxt_AS;

    /** XX_IMAGE_TXT_AD*/
	public final EZDCStringItem              xxImageTxt_AD;

    /** XX_IMAGE_TXT_AB*/
	public final EZDCStringItem              xxImageTxt_AB;


	/**
	 * NWAL2200_ACMsg is constructor.
	 * The initialization when the instance of NWAL2200_ACMsg is generated.
	 */
	public NWAL2200_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2200_ACMsg is constructor.
	 * The initialization when the instance of NWAL2200_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2200_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxSmryLineFlg_L = (EZDCStringItem)newItem("xxSmryLineFlg_L");
		xxChkBox_LC = (EZDCStringItem)newItem("xxChkBox_LC");
		dsImptOrdConfigPk_LC = (EZDCBigDecimalItem)newItem("dsImptOrdConfigPk_LC");
		dsImptOrdPk_LC = (EZDCBigDecimalItem)newItem("dsImptOrdPk_LC");
		dsOrdPosnNum_LC = (EZDCStringItem)newItem("dsOrdPosnNum_LC");
		configCatgCd_LC = (EZDCStringItem)newItem("configCatgCd_LC");
		configTpCd_LC = (EZDCStringItem)newItem("configTpCd_LC");
		configTpDescTxt_LC = (EZDCStringItem)newItem("configTpDescTxt_LC");
		svcConfigMstrPk_LC = (EZDCBigDecimalItem)newItem("svcConfigMstrPk_LC");
		mdlId_LC = (EZDCBigDecimalItem)newItem("mdlId_LC");
		t_MdlNm_LC = (EZDCStringItem)newItem("t_MdlNm_LC");
		addrLbTxt_LC = (EZDCStringItem)newItem("addrLbTxt_LC");
		billToCustAcctCd_LC = (EZDCStringItem)newItem("billToCustAcctCd_LC");
		billToCustLocCd_LC = (EZDCStringItem)newItem("billToCustLocCd_LC");
		xxAllLineAddr_LB = (EZDCStringItem)newItem("xxAllLineAddr_LB");
		shipToCustAcctCd_LC = (EZDCStringItem)newItem("shipToCustAcctCd_LC");
		shipToCustLocCd_LC = (EZDCStringItem)newItem("shipToCustLocCd_LC");
		dropShipFlg_LC = (EZDCStringItem)newItem("dropShipFlg_LC");
		shipToLocNm_LC = (EZDCStringItem)newItem("shipToLocNm_LC");
		shipToAddlLocNm_LC = (EZDCStringItem)newItem("shipToAddlLocNm_LC");
		xxAllLineAddr_LS = (EZDCStringItem)newItem("xxAllLineAddr_LS");
		shipToFirstLineAddr_LC = (EZDCStringItem)newItem("shipToFirstLineAddr_LC");
		shipToScdLineAddr_LC = (EZDCStringItem)newItem("shipToScdLineAddr_LC");
		shipToThirdLineAddr_LC = (EZDCStringItem)newItem("shipToThirdLineAddr_LC");
		shipToFrthLineAddr_LC = (EZDCStringItem)newItem("shipToFrthLineAddr_LC");
		shipToFirstRefCmntTxt_LC = (EZDCStringItem)newItem("shipToFirstRefCmntTxt_LC");
		shipToScdRefCmntTxt_LC = (EZDCStringItem)newItem("shipToScdRefCmntTxt_LC");
		shipToCtyAddr_LC = (EZDCStringItem)newItem("shipToCtyAddr_LC");
		shipToStCd_LC = (EZDCStringItem)newItem("shipToStCd_LC");
		shipToProvNm_LC = (EZDCStringItem)newItem("shipToProvNm_LC");
		shipToCntyNm_LC = (EZDCStringItem)newItem("shipToCntyNm_LC");
		shipToPostCd_LC = (EZDCStringItem)newItem("shipToPostCd_LC");
		shipToCtryCd_LC = (EZDCStringItem)newItem("shipToCtryCd_LC");
		configCratTs_LC = (EZDCStringItem)newItem("configCratTs_LC");
		svcSlnSq_LC = (EZDCBigDecimalItem)newItem("svcSlnSq_LC");
		svcSlnNm_LC = (EZDCStringItem)newItem("svcSlnNm_LC");
		dclnSvcCd_LC = (EZDCStringItem)newItem("dclnSvcCd_LC");
		emsdImptStsCd_LC = (EZDCStringItem)newItem("emsdImptStsCd_LC");
		ezUpTime_LC = (EZDCStringItem)newItem("ezUpTime_LC");
		ezUpTimeZone_LC = (EZDCStringItem)newItem("ezUpTimeZone_LC");
		xxImageTxt_AC = (EZDCStringItem)newItem("xxImageTxt_AC");
		xxImageTxt_AS = (EZDCStringItem)newItem("xxImageTxt_AS");
		xxImageTxt_AD = (EZDCStringItem)newItem("xxImageTxt_AD");
		xxImageTxt_AB = (EZDCStringItem)newItem("xxImageTxt_AB");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2200_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2200_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxSmryLineFlg_L", "xxSmryLineFlg_L", "L", null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_LC", "xxChkBox_LC", "LC", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsImptOrdConfigPk_LC", "dsImptOrdConfigPk_LC", "LC", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsImptOrdPk_LC", "dsImptOrdPk_LC", "LC", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsOrdPosnNum_LC", "dsOrdPosnNum_LC", "LC", null, TYPE_HANKAKUEISU, "6", null},
	{"configCatgCd_LC", "configCatgCd_LC", "LC", null, TYPE_HANKAKUEISU, "2", null},
	{"configTpCd_LC", "configTpCd_LC", "LC", null, TYPE_HANKAKUEISU, "16", null},
	{"configTpDescTxt_LC", "configTpDescTxt_LC", "LC", null, TYPE_HANKAKUEISU, "50", null},
	{"svcConfigMstrPk_LC", "svcConfigMstrPk_LC", "LC", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlId_LC", "mdlId_LC", "LC", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"t_MdlNm_LC", "t_MdlNm_LC", "LC", null, TYPE_HANKAKUEISU, "50", null},
	{"addrLbTxt_LC", "addrLbTxt_LC", "LC", null, TYPE_HANKAKUEISU, "10", null},
	{"billToCustAcctCd_LC", "billToCustAcctCd_LC", "LC", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustLocCd_LC", "billToCustLocCd_LC", "LC", null, TYPE_HANKAKUEISU, "20", null},
	{"xxAllLineAddr_LB", "xxAllLineAddr_LB", "LB", null, TYPE_HANKAKUEISU, "400", null},
	{"shipToCustAcctCd_LC", "shipToCustAcctCd_LC", "LC", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustLocCd_LC", "shipToCustLocCd_LC", "LC", null, TYPE_HANKAKUEISU, "20", null},
	{"dropShipFlg_LC", "dropShipFlg_LC", "LC", null, TYPE_HANKAKUEISU, "1", null},
	{"shipToLocNm_LC", "shipToLocNm_LC", "LC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToAddlLocNm_LC", "shipToAddlLocNm_LC", "LC", null, TYPE_HANKAKUEISU, "60", null},
	{"xxAllLineAddr_LS", "xxAllLineAddr_LS", "LS", null, TYPE_HANKAKUEISU, "400", null},
	{"shipToFirstLineAddr_LC", "shipToFirstLineAddr_LC", "LC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToScdLineAddr_LC", "shipToScdLineAddr_LC", "LC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToThirdLineAddr_LC", "shipToThirdLineAddr_LC", "LC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToFrthLineAddr_LC", "shipToFrthLineAddr_LC", "LC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToFirstRefCmntTxt_LC", "shipToFirstRefCmntTxt_LC", "LC", null, TYPE_HANKAKUEISU, "90", null},
	{"shipToScdRefCmntTxt_LC", "shipToScdRefCmntTxt_LC", "LC", null, TYPE_HANKAKUEISU, "90", null},
	{"shipToCtyAddr_LC", "shipToCtyAddr_LC", "LC", null, TYPE_HANKAKUEISU, "25", null},
	{"shipToStCd_LC", "shipToStCd_LC", "LC", null, TYPE_HANKAKUEISU, "2", null},
	{"shipToProvNm_LC", "shipToProvNm_LC", "LC", null, TYPE_HANKAKUEISU, "25", null},
	{"shipToCntyNm_LC", "shipToCntyNm_LC", "LC", null, TYPE_HANKAKUEISU, "30", null},
	{"shipToPostCd_LC", "shipToPostCd_LC", "LC", null, TYPE_HANKAKUEISU, "15", null},
	{"shipToCtryCd_LC", "shipToCtryCd_LC", "LC", null, TYPE_HANKAKUEISU, "3", null},
	{"configCratTs_LC", "configCratTs_LC", "LC", null, TYPE_HANKAKUSUJI, "17", null},
	{"svcSlnSq_LC", "svcSlnSq_LC", "LC", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcSlnNm_LC", "svcSlnNm_LC", "LC", null, TYPE_HANKAKUEISU, "100", null},
	{"dclnSvcCd_LC", "dclnSvcCd_LC", "LC", null, TYPE_HANKAKUEISU, "1", null},
	{"emsdImptStsCd_LC", "emsdImptStsCd_LC", "LC", null, TYPE_HANKAKUEISU, "3", null},
	{"ezUpTime_LC", "ezUpTime_LC", "LC", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_LC", "ezUpTimeZone_LC", "LC", null, TYPE_HANKAKUEISU, "5", null},
	{"xxImageTxt_AC", "xxImageTxt_AC", "AC", null, TYPE_HANKAKUEISU, "1", null},
	{"xxImageTxt_AS", "xxImageTxt_AS", "AS", null, TYPE_HANKAKUEISU, "1", null},
	{"xxImageTxt_AD", "xxImageTxt_AD", "AD", null, TYPE_HANKAKUEISU, "1", null},
	{"xxImageTxt_AB", "xxImageTxt_AB", "AB", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SMRY_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg_L
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_LC
        {"DS_IMPT_ORD_CONFIG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptOrdConfigPk_LC
        {"DS_IMPT_ORD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptOrdPk_LC
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_LC
        {"CONFIG_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configCatgCd_LC
        {"CONFIG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configTpCd_LC
        {"CONFIG_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configTpDescTxt_LC
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_LC
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_LC
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_LC
        {"ADDR_LB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addrLbTxt_LC
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_LC
        {"BILL_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocCd_LC
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_LB
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd_LC
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd_LC
        {"DROP_SHIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dropShipFlg_LC
        {"SHIP_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm_LC
        {"SHIP_TO_ADDL_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToAddlLocNm_LC
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_LS
        {"SHIP_TO_FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToFirstLineAddr_LC
        {"SHIP_TO_SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToScdLineAddr_LC
        {"SHIP_TO_THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToThirdLineAddr_LC
        {"SHIP_TO_FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToFrthLineAddr_LC
        {"SHIP_TO_FIRST_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToFirstRefCmntTxt_LC
        {"SHIP_TO_SCD_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToScdRefCmntTxt_LC
        {"SHIP_TO_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCtyAddr_LC
        {"SHIP_TO_ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToStCd_LC
        {"SHIP_TO_PROV_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToProvNm_LC
        {"SHIP_TO_CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCntyNm_LC
        {"SHIP_TO_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToPostCd_LC
        {"SHIP_TO_CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCtryCd_LC
        {"CONFIG_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configCratTs_LC
        {"SVC_SLN_SQ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSlnSq_LC
        {"SVC_SLN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSlnNm_LC
        {"DCLN_SVC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dclnSvcCd_LC
        {"EMSD_IMPT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//emsdImptStsCd_LC
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_LC
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_LC
        {"XX_IMAGE_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_AC
        {"XX_IMAGE_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_AS
        {"XX_IMAGE_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_AD
        {"XX_IMAGE_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_AB
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
