//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190128184129000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0010CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0010 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0010CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** SVC_CONTR_BR_CD*/
	public final EZDCStringItem              svcContrBrCd;

    /** SVC_CONTR_BR_DESC_TXT*/
	public final EZDCStringItem              svcContrBrDescTxt;

    /** SVC_BY_LINE_BIZ_TP_CD_H*/
	public final EZDCStringItem              svcByLineBizTpCd_H;

    /** LINE_BIZ_TP_CD_L*/
	public final EZDCStringItemArray              lineBizTpCd_L;

    /** LINE_BIZ_TP_DESC_TXT_L*/
	public final EZDCStringItemArray              lineBizTpDescTxt_L;

    /** TECH_CD*/
	public final EZDCStringItem              techCd;

    /** SVC_TASK_RCV_DT_H1*/
	public final EZDCDateItem              svcTaskRcvDt_H1;

    /** SVC_TASK_RCV_DT_H2*/
	public final EZDCDateItem              svcTaskRcvDt_H2;

    /** TECH_SCHD_FROM_DT*/
	public final EZDCDateItem              techSchdFromDt;

    /** SVC_TASK_NUM*/
	public final EZDCStringItem              svcTaskNum;

    /** SVC_TASK_STS_CD_H1*/
	public final EZDCStringItemArray              svcTaskStsCd_H1;

    /** SVC_TASK_STS_NM_H2*/
	public final EZDCStringItemArray              svcTaskStsNm_H2;

    /** SVC_TASK_STS_CD_H3*/
	public final EZDCStringItem              svcTaskStsCd_H3;

    /** SHIP_TO_CUST_CD*/
	public final EZDCStringItem              shipToCustCd;

    /** LOC_NM*/
	public final EZDCStringItem              locNm;

    /** FSR_NUM*/
	public final EZDCStringItem              fsrNum;

    /** FSR_VISIT_NUM*/
	public final EZDCStringItem              fsrVisitNum;

    /** MDL_NM*/
	public final EZDCStringItem              mdlNm;

    /** DS_SVC_CALL_TP_CD_H1*/
	public final EZDCStringItemArray              dsSvcCallTpCd_H1;

    /** XX_ST_CD_LIST_TXT_H2*/
	public final EZDCStringItemArray              xxStCdListTxt_H2;

    /** DS_SVC_CALL_TP_CD_H3*/
	public final EZDCStringItem              dsSvcCallTpCd_H3;

    /** SVC_BILL_TP_CD_H1*/
	public final EZDCStringItemArray              svcBillTpCd_H1;

    /** SVC_BILL_TP_DESC_TXT_H2*/
	public final EZDCStringItemArray              svcBillTpDescTxt_H2;

    /** SVC_BILL_TP_CD_H3*/
	public final EZDCStringItem              svcBillTpCd_H3;

    /** XX_CHK_BOX_HO*/
	public final EZDCStringItem              xxChkBox_HO;

    /** XX_CHK_BOX_DW*/
	public final EZDCStringItem              xxChkBox_DW;

    /** XX_CHK_BOX_L0*/
	public final EZDCStringItem              xxChkBox_L0;

    /** XX_CHK_BOX_L1*/
	public final EZDCStringItem              xxChkBox_L1;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** XX_ERR_FLG*/
	public final EZDCStringItem              xxErrFlg;

    /** XX_ERR_FLG_PT*/
	public final EZDCStringItem              xxErrFlg_PT;

    /** XX_ROW_NUM*/
	public final EZDCBigDecimalItem              xxRowNum;

    /** A*/
	public final business.blap.NSBL0010.NSBL0010_ACMsgArray              A;


	/**
	 * NSBL0010CMsg is constructor.
	 * The initialization when the instance of NSBL0010CMsg is generated.
	 */
	public NSBL0010CMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0010CMsg is constructor.
	 * The initialization when the instance of NSBL0010CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0010CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		svcContrBrCd = (EZDCStringItem)newItem("svcContrBrCd");
		svcContrBrDescTxt = (EZDCStringItem)newItem("svcContrBrDescTxt");
		svcByLineBizTpCd_H = (EZDCStringItem)newItem("svcByLineBizTpCd_H");
		lineBizTpCd_L = (EZDCStringItemArray)newItemArray("lineBizTpCd_L");
		lineBizTpDescTxt_L = (EZDCStringItemArray)newItemArray("lineBizTpDescTxt_L");
		techCd = (EZDCStringItem)newItem("techCd");
		svcTaskRcvDt_H1 = (EZDCDateItem)newItem("svcTaskRcvDt_H1");
		svcTaskRcvDt_H2 = (EZDCDateItem)newItem("svcTaskRcvDt_H2");
		techSchdFromDt = (EZDCDateItem)newItem("techSchdFromDt");
		svcTaskNum = (EZDCStringItem)newItem("svcTaskNum");
		svcTaskStsCd_H1 = (EZDCStringItemArray)newItemArray("svcTaskStsCd_H1");
		svcTaskStsNm_H2 = (EZDCStringItemArray)newItemArray("svcTaskStsNm_H2");
		svcTaskStsCd_H3 = (EZDCStringItem)newItem("svcTaskStsCd_H3");
		shipToCustCd = (EZDCStringItem)newItem("shipToCustCd");
		locNm = (EZDCStringItem)newItem("locNm");
		fsrNum = (EZDCStringItem)newItem("fsrNum");
		fsrVisitNum = (EZDCStringItem)newItem("fsrVisitNum");
		mdlNm = (EZDCStringItem)newItem("mdlNm");
		dsSvcCallTpCd_H1 = (EZDCStringItemArray)newItemArray("dsSvcCallTpCd_H1");
		xxStCdListTxt_H2 = (EZDCStringItemArray)newItemArray("xxStCdListTxt_H2");
		dsSvcCallTpCd_H3 = (EZDCStringItem)newItem("dsSvcCallTpCd_H3");
		svcBillTpCd_H1 = (EZDCStringItemArray)newItemArray("svcBillTpCd_H1");
		svcBillTpDescTxt_H2 = (EZDCStringItemArray)newItemArray("svcBillTpDescTxt_H2");
		svcBillTpCd_H3 = (EZDCStringItem)newItem("svcBillTpCd_H3");
		xxChkBox_HO = (EZDCStringItem)newItem("xxChkBox_HO");
		xxChkBox_DW = (EZDCStringItem)newItem("xxChkBox_DW");
		xxChkBox_L0 = (EZDCStringItem)newItem("xxChkBox_L0");
		xxChkBox_L1 = (EZDCStringItem)newItem("xxChkBox_L1");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		xxErrFlg = (EZDCStringItem)newItem("xxErrFlg");
		xxErrFlg_PT = (EZDCStringItem)newItem("xxErrFlg_PT");
		xxRowNum = (EZDCBigDecimalItem)newItem("xxRowNum");
		A = (business.blap.NSBL0010.NSBL0010_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0010CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0010CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcContrBrCd", "svcContrBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt", "svcContrBrDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcByLineBizTpCd_H", "svcByLineBizTpCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"lineBizTpCd_L", "lineBizTpCd_L", "L", "99", TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpDescTxt_L", "lineBizTpDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"techCd", "techCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"svcTaskRcvDt_H1", "svcTaskRcvDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"svcTaskRcvDt_H2", "svcTaskRcvDt_H2", "H2", null, TYPE_NENTSUKIHI, "8", null},
	{"techSchdFromDt", "techSchdFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcTaskNum", "svcTaskNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcTaskStsCd_H1", "svcTaskStsCd_H1", "H1", "99", TYPE_HANKAKUEISU, "2", null},
	{"svcTaskStsNm_H2", "svcTaskStsNm_H2", "H2", "99", TYPE_HANKAKUEISU, "30", null},
	{"svcTaskStsCd_H3", "svcTaskStsCd_H3", "H3", null, TYPE_HANKAKUEISU, "2", null},
	{"shipToCustCd", "shipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"locNm", "locNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"fsrNum", "fsrNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"fsrVisitNum", "fsrVisitNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"mdlNm", "mdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsSvcCallTpCd_H1", "dsSvcCallTpCd_H1", "H1", "99", TYPE_HANKAKUEISU, "3", null},
	{"xxStCdListTxt_H2", "xxStCdListTxt_H2", "H2", "99", TYPE_HANKAKUEISU, "73", null},
	{"dsSvcCallTpCd_H3", "dsSvcCallTpCd_H3", "H3", null, TYPE_HANKAKUEISU, "3", null},
	{"svcBillTpCd_H1", "svcBillTpCd_H1", "H1", "99", TYPE_HANKAKUEISU, "2", null},
	{"svcBillTpDescTxt_H2", "svcBillTpDescTxt_H2", "H2", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcBillTpCd_H3", "svcBillTpCd_H3", "H3", null, TYPE_HANKAKUEISU, "2", null},
	{"xxChkBox_HO", "xxChkBox_HO", "HO", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_DW", "xxChkBox_DW", "DW", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_L0", "xxChkBox_L0", "L0", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_L1", "xxChkBox_L1", "L1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxErrFlg", "xxErrFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxErrFlg_PT", "xxErrFlg_PT", "PT", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "40", "business.blap.NSBL0010.NSBL0010_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"SVC_CONTR_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt
        {"SVC_BY_LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcByLineBizTpCd_H
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_L
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_L
        {"TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd
        {"SVC_TASK_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskRcvDt_H1
        {"SVC_TASK_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskRcvDt_H2
        {"TECH_SCHD_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techSchdFromDt
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum
        {"SVC_TASK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskStsCd_H1
        {"SVC_TASK_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskStsNm_H2
        {"SVC_TASK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskStsCd_H3
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum
        {"FSR_VISIT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrVisitNum
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm
        {"DS_SVC_CALL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSvcCallTpCd_H1
        {"XX_ST_CD_LIST_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxStCdListTxt_H2
        {"DS_SVC_CALL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSvcCallTpCd_H3
        {"SVC_BILL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillTpCd_H1
        {"SVC_BILL_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillTpDescTxt_H2
        {"SVC_BILL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillTpCd_H3
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_HO
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_DW
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_L0
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_L1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_ERR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg
        {"XX_ERR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg_PT
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
		null,	//A
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

