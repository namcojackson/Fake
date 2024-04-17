//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20180612210838000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMA7010001FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMA7010001 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMA7010001FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_CATG_NM*/
	public final EZDFStringItem              prcCatgNm;

    /** PRC_LIST_TP_DESC_TXT*/
	public final EZDFStringItem              prcListTpDescTxt;

    /** PRC_LIST_EQUIP_CONFIG_NUM*/
	public final EZDFBigDecimalItem              prcListEquipConfigNum;

    /** PRC_LIST_EQUIP_CONFIG_NM*/
	public final EZDFStringItem              prcListEquipConfigNm;

    /** PRC_QLFY_TP_DESC_TXT*/
	public final EZDFStringItem              prcQlfyTpDescTxt;

    /** PRC_QLFY_VAL_TXT*/
	public final EZDFStringItem              prcQlfyValTxt;

    /** PKG_UOM_DESC_TXT*/
	public final EZDFStringItem              pkgUomDescTxt;

    /** PRC_LIST_EQUIP_PRC_AMT*/
	public final EZDFBigDecimalItem              prcListEquipPrcAmt;

    /** MIN_PRC_AMT*/
	public final EZDFBigDecimalItem              minPrcAmt;

    /** MAX_PRC_AMT*/
	public final EZDFBigDecimalItem              maxPrcAmt;

    /** PRC_TERM_AOT*/
	public final EZDFBigDecimalItem              prcTermAot;

    /** PRC_TERM_UOM_DESC_TXT*/
	public final EZDFStringItem              prcTermUomDescTxt;

    /** CUST_BID_QTY*/
	public final EZDFBigDecimalItem              custBidQty;

    /** MLY_PMT_AMT*/
	public final EZDFBigDecimalItem              mlyPmtAmt;

    /** MIN_LEASE_PMT_AMT*/
	public final EZDFBigDecimalItem              minLeasePmtAmt;

    /** MAX_LEASE_PMT_AMT*/
	public final EZDFBigDecimalItem              maxLeasePmtAmt;

    /** PRC_FMLA_NM*/
	public final EZDFStringItem              prcFmlaNm;

    /** OPEN_MKT_DESC_TXT*/
	public final EZDFStringItem              openMktDescTxt;

    /** QUOT_REV_AMT*/
	public final EZDFBigDecimalItem              quotRevAmt;

    /** COMP_CD*/
	public final EZDFStringItem              compCd;

    /** EFF_FROM_DT*/
	public final EZDFDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDFDateItem              effThruDt;


	/**
	 * NMA7010001FMsg is constructor.
	 * The initialization when the instance of NMA7010001FMsg is generated.
	 */
	public NMA7010001FMsg() {
		this(false, -1);
	}

	/**
	 * NMA7010001FMsg is constructor.
	 * The initialization when the instance of NMA7010001FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMA7010001FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcCatgNm = (EZDFStringItem)newItem("prcCatgNm");
		prcListTpDescTxt = (EZDFStringItem)newItem("prcListTpDescTxt");
		prcListEquipConfigNum = (EZDFBigDecimalItem)newItem("prcListEquipConfigNum");
		prcListEquipConfigNm = (EZDFStringItem)newItem("prcListEquipConfigNm");
		prcQlfyTpDescTxt = (EZDFStringItem)newItem("prcQlfyTpDescTxt");
		prcQlfyValTxt = (EZDFStringItem)newItem("prcQlfyValTxt");
		pkgUomDescTxt = (EZDFStringItem)newItem("pkgUomDescTxt");
		prcListEquipPrcAmt = (EZDFBigDecimalItem)newItem("prcListEquipPrcAmt");
		minPrcAmt = (EZDFBigDecimalItem)newItem("minPrcAmt");
		maxPrcAmt = (EZDFBigDecimalItem)newItem("maxPrcAmt");
		prcTermAot = (EZDFBigDecimalItem)newItem("prcTermAot");
		prcTermUomDescTxt = (EZDFStringItem)newItem("prcTermUomDescTxt");
		custBidQty = (EZDFBigDecimalItem)newItem("custBidQty");
		mlyPmtAmt = (EZDFBigDecimalItem)newItem("mlyPmtAmt");
		minLeasePmtAmt = (EZDFBigDecimalItem)newItem("minLeasePmtAmt");
		maxLeasePmtAmt = (EZDFBigDecimalItem)newItem("maxLeasePmtAmt");
		prcFmlaNm = (EZDFStringItem)newItem("prcFmlaNm");
		openMktDescTxt = (EZDFStringItem)newItem("openMktDescTxt");
		quotRevAmt = (EZDFBigDecimalItem)newItem("quotRevAmt");
		compCd = (EZDFStringItem)newItem("compCd");
		effFromDt = (EZDFDateItem)newItem("effFromDt");
		effThruDt = (EZDFDateItem)newItem("effThruDt");
	}

	/**
	 * get the type of array which is stored
	 * @return NMA7010001FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMA7010001FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcCatgNm", "prcCatgNm", null, null, TYPE_HANKAKUEISU, "75", null},
	{"prcListTpDescTxt", "prcListTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcListEquipConfigNum", "prcListEquipConfigNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prcListEquipConfigNm", "prcListEquipConfigNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcQlfyTpDescTxt", "prcQlfyTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcQlfyValTxt", "prcQlfyValTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"pkgUomDescTxt", "pkgUomDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcListEquipPrcAmt", "prcListEquipPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"minPrcAmt", "minPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"maxPrcAmt", "maxPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcTermAot", "prcTermAot", null, null, TYPE_SEISU_SYOSU, "7", "4"},
	{"prcTermUomDescTxt", "prcTermUomDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"custBidQty", "custBidQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mlyPmtAmt", "mlyPmtAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"minLeasePmtAmt", "minLeasePmtAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"maxLeasePmtAmt", "maxLeasePmtAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcFmlaNm", "prcFmlaNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"openMktDescTxt", "openMktDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"quotRevAmt", "quotRevAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"compCd", "compCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt
        {"PRC_LIST_EQUIP_CONFIG_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListEquipConfigNum
        {"PRC_LIST_EQUIP_CONFIG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListEquipConfigNm
        {"PRC_QLFY_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcQlfyTpDescTxt
        {"PRC_QLFY_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcQlfyValTxt
        {"PKG_UOM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomDescTxt
        {"PRC_LIST_EQUIP_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListEquipPrcAmt
        {"MIN_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minPrcAmt
        {"MAX_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxPrcAmt
        {"PRC_TERM_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTermAot
        {"PRC_TERM_UOM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTermUomDescTxt
        {"CUST_BID_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custBidQty
        {"MLY_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlyPmtAmt
        {"MIN_LEASE_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minLeasePmtAmt
        {"MAX_LEASE_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxLeasePmtAmt
        {"PRC_FMLA_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaNm
        {"OPEN_MKT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openMktDescTxt
        {"QUOT_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//quotRevAmt
        {"COMP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//compCd
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
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
