//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190201013448000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0410CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0410;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0410 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0410CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_P*/
	public final EZDCBigDecimalItem              dsContrPk_P;

    /** DS_CONTR_DTL_PK_P*/
	public final EZDCBigDecimalItem              dsContrDtlPk_P;

    /** XX_MODE_CD_P*/
	public final EZDCStringItem              xxModeCd_P;

    /** SLS_DT_P*/
	public final EZDCDateItem              slsDt_P;

    /** DS_CONTR_NUM*/
	public final EZDCStringItem              dsContrNum;

    /** XX_SCR_ITEM_34_TXT*/
	public final EZDCStringItem              xxScrItem34Txt;

    /** DS_CONTR_CTRL_STS_NM*/
	public final EZDCStringItem              dsContrCtrlStsNm;

    /** SVC_CONTR_BR_DESC_TXT*/
	public final EZDCStringItem              svcContrBrDescTxt;

    /** DS_CONTR_NM*/
	public final EZDCStringItem              dsContrNm;

    /** DS_ACCT_NUM*/
	public final EZDCStringItem              dsAcctNum;

    /** DS_ACCT_NM*/
	public final EZDCStringItem              dsAcctNm;

    /** DS_CONTR_CATG_CD_H*/
	public final EZDCStringItem              dsContrCatgCd_H;

    /** BLLG_CYCLE_CD_DF*/
	public final EZDCStringItem              bllgCycleCd_DF;

    /** BLLG_CYCLE_SORT_NUM_DF*/
	public final EZDCBigDecimalItem              bllgCycleSortNum_DF;

    /** XX_ROW_NUM_H*/
	public final EZDCBigDecimalItem              xxRowNum_H;

    /** CONTR_VRSN_EFF_FROM_DT*/
	public final EZDCDateItem              contrVrsnEffFromDt;

    /** CONTR_VRSN_EFF_THRU_DT*/
	public final EZDCDateItem              contrVrsnEffThruDt;

    /** EFF_THRU_DT*/
	public final EZDCDateItem              effThruDt;

    /** DS_CONTR_DTL_PK_CD*/
	public final EZDCBigDecimalItemArray              dsContrDtlPk_CD;

    /** XX_SCR_ITEM_34_TXT_DI*/
	public final EZDCStringItemArray              xxScrItem34Txt_DI;

    /** ADDL_CHRG_TP_CD_CD*/
	public final EZDCStringItemArray              addlChrgTpCd_CD;

    /** ADDL_CHRG_TP_NM_DI*/
	public final EZDCStringItemArray              addlChrgTpNm_DI;

    /** BLLG_CYCLE_CD_CD*/
	public final EZDCStringItemArray              bllgCycleCd_CD;

    /** BLLG_CYCLE_NM_DI*/
	public final EZDCStringItemArray              bllgCycleNm_DI;

    /** SVC_BILL_BY_TP_CD_CD*/
	public final EZDCStringItemArray              svcBillByTpCd_CD;

    /** SVC_BILL_BY_TP_NM_DI*/
	public final EZDCStringItemArray              svcBillByTpNm_DI;

    /** SVC_BILL_BY_TP_CD_MC*/
	public final EZDCStringItemArray              svcBillByTpCd_MC;

    /** SVC_BILL_BY_TP_NM_MD*/
	public final EZDCStringItemArray              svcBillByTpNm_MD;

    /** ADDL_CHRG_INV_TP_CD_CD*/
	public final EZDCStringItemArray              addlChrgInvTpCd_CD;

    /** ADDL_CHRG_INV_TP_NM_DI*/
	public final EZDCStringItemArray              addlChrgInvTpNm_DI;

    /** EFF_FROM_DT_AC*/
	public final EZDCDateItemArray              effFromDt_AC;

    /** EFF_THRU_DT_AC*/
	public final EZDCDateItemArray              effThruDt_AC;

    /** A*/
	public final business.blap.NSAL0410.NSAL0410_ACMsgArray              A;

    /** B*/
	public final business.blap.NSAL0410.NSAL0410_BCMsgArray              B;

    /** T*/
	public final business.blap.NSAL0410.NSAL0410_TCMsgArray              T;


	/**
	 * NSAL0410CMsg is constructor.
	 * The initialization when the instance of NSAL0410CMsg is generated.
	 */
	public NSAL0410CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0410CMsg is constructor.
	 * The initialization when the instance of NSAL0410CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0410CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_P = (EZDCBigDecimalItem)newItem("dsContrPk_P");
		dsContrDtlPk_P = (EZDCBigDecimalItem)newItem("dsContrDtlPk_P");
		xxModeCd_P = (EZDCStringItem)newItem("xxModeCd_P");
		slsDt_P = (EZDCDateItem)newItem("slsDt_P");
		dsContrNum = (EZDCStringItem)newItem("dsContrNum");
		xxScrItem34Txt = (EZDCStringItem)newItem("xxScrItem34Txt");
		dsContrCtrlStsNm = (EZDCStringItem)newItem("dsContrCtrlStsNm");
		svcContrBrDescTxt = (EZDCStringItem)newItem("svcContrBrDescTxt");
		dsContrNm = (EZDCStringItem)newItem("dsContrNm");
		dsAcctNum = (EZDCStringItem)newItem("dsAcctNum");
		dsAcctNm = (EZDCStringItem)newItem("dsAcctNm");
		dsContrCatgCd_H = (EZDCStringItem)newItem("dsContrCatgCd_H");
		bllgCycleCd_DF = (EZDCStringItem)newItem("bllgCycleCd_DF");
		bllgCycleSortNum_DF = (EZDCBigDecimalItem)newItem("bllgCycleSortNum_DF");
		xxRowNum_H = (EZDCBigDecimalItem)newItem("xxRowNum_H");
		contrVrsnEffFromDt = (EZDCDateItem)newItem("contrVrsnEffFromDt");
		contrVrsnEffThruDt = (EZDCDateItem)newItem("contrVrsnEffThruDt");
		effThruDt = (EZDCDateItem)newItem("effThruDt");
		dsContrDtlPk_CD = (EZDCBigDecimalItemArray)newItemArray("dsContrDtlPk_CD");
		xxScrItem34Txt_DI = (EZDCStringItemArray)newItemArray("xxScrItem34Txt_DI");
		addlChrgTpCd_CD = (EZDCStringItemArray)newItemArray("addlChrgTpCd_CD");
		addlChrgTpNm_DI = (EZDCStringItemArray)newItemArray("addlChrgTpNm_DI");
		bllgCycleCd_CD = (EZDCStringItemArray)newItemArray("bllgCycleCd_CD");
		bllgCycleNm_DI = (EZDCStringItemArray)newItemArray("bllgCycleNm_DI");
		svcBillByTpCd_CD = (EZDCStringItemArray)newItemArray("svcBillByTpCd_CD");
		svcBillByTpNm_DI = (EZDCStringItemArray)newItemArray("svcBillByTpNm_DI");
		svcBillByTpCd_MC = (EZDCStringItemArray)newItemArray("svcBillByTpCd_MC");
		svcBillByTpNm_MD = (EZDCStringItemArray)newItemArray("svcBillByTpNm_MD");
		addlChrgInvTpCd_CD = (EZDCStringItemArray)newItemArray("addlChrgInvTpCd_CD");
		addlChrgInvTpNm_DI = (EZDCStringItemArray)newItemArray("addlChrgInvTpNm_DI");
		effFromDt_AC = (EZDCDateItemArray)newItemArray("effFromDt_AC");
		effThruDt_AC = (EZDCDateItemArray)newItemArray("effThruDt_AC");
		A = (business.blap.NSAL0410.NSAL0410_ACMsgArray)newMsgArray("A");
		B = (business.blap.NSAL0410.NSAL0410_BCMsgArray)newMsgArray("B");
		T = (business.blap.NSAL0410.NSAL0410_TCMsgArray)newMsgArray("T");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0410CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0410CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk_P", "dsContrPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_P", "dsContrDtlPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxModeCd_P", "xxModeCd_P", "P", null, TYPE_HANKAKUEISU, "2", null},
	{"slsDt_P", "slsDt_P", "P", null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem34Txt", "xxScrItem34Txt", null, null, TYPE_HANKAKUEISU, "34", null},
	{"dsContrCtrlStsNm", "dsContrCtrlStsNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcContrBrDescTxt", "svcContrBrDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrNm", "dsContrNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"dsContrCatgCd_H", "dsContrCatgCd_H", "H", null, TYPE_HANKAKUEISU, "3", null},
	{"bllgCycleCd_DF", "bllgCycleCd_DF", "DF", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleSortNum_DF", "bllgCycleSortNum_DF", "DF", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"xxRowNum_H", "xxRowNum_H", "H", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"contrVrsnEffFromDt", "contrVrsnEffFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"contrVrsnEffThruDt", "contrVrsnEffThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrDtlPk_CD", "dsContrDtlPk_CD", "CD", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"xxScrItem34Txt_DI", "xxScrItem34Txt_DI", "DI", "99", TYPE_HANKAKUEISU, "34", null},
	{"addlChrgTpCd_CD", "addlChrgTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "16", null},
	{"addlChrgTpNm_DI", "addlChrgTpNm_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleCd_CD", "bllgCycleCd_CD", "CD", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleNm_DI", "bllgCycleNm_DI", "DI", "99", TYPE_HANKAKUEISU, "15", null},
	{"svcBillByTpCd_CD", "svcBillByTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcBillByTpNm_DI", "svcBillByTpNm_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcBillByTpCd_MC", "svcBillByTpCd_MC", "MC", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcBillByTpNm_MD", "svcBillByTpNm_MD", "MD", "99", TYPE_HANKAKUEISU, "50", null},
	{"addlChrgInvTpCd_CD", "addlChrgInvTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "4", null},
	{"addlChrgInvTpNm_DI", "addlChrgInvTpNm_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_AC", "effFromDt_AC", "AC", "99", TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_AC", "effThruDt_AC", "AC", "99", TYPE_NENTSUKIHI, "8", null},
	{"A", "A", null, "200", "business.blap.NSAL0410.NSAL0410_ACMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NSAL0410.NSAL0410_BCMsgArray", null, null},
	
	{"T", "T", null, "20", "business.blap.NSAL0410.NSAL0410_TCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_P
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_P
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_P
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt_P
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"XX_SCR_ITEM_34_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem34Txt
        {"DS_CONTR_CTRL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsNm
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt
        {"DS_CONTR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNm
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_H
        {"BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_DF
        {"BLLG_CYCLE_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleSortNum_DF
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_H
        {"CONTR_VRSN_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrVrsnEffFromDt
        {"CONTR_VRSN_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrVrsnEffThruDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_CD
        {"XX_SCR_ITEM_34_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem34Txt_DI
        {"ADDL_CHRG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgTpCd_CD
        {"ADDL_CHRG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgTpNm_DI
        {"BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_CD
        {"BLLG_CYCLE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleNm_DI
        {"SVC_BILL_BY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillByTpCd_CD
        {"SVC_BILL_BY_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillByTpNm_DI
        {"SVC_BILL_BY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillByTpCd_MC
        {"SVC_BILL_BY_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillByTpNm_MD
        {"ADDL_CHRG_INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgInvTpCd_CD
        {"ADDL_CHRG_INV_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgInvTpNm_DI
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_AC
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_AC
		null,	//A
		null,	//B
		null,	//T
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

