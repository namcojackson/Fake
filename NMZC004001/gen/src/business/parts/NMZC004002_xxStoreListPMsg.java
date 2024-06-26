//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20200408161602000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC004002_xxStoreListPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMZC004002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC004002_xxStoreListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PKG_UOM_CD_S*/
	public final EZDPStringItem              pkgUomCd_S;

    /** MDSE_CD_S*/
	public final EZDPStringItem              mdseCd_S;

    /** SCC14_NUM_S*/
	public final EZDPStringItem              scc14Num_S;

    /** GTIN_CD_S*/
	public final EZDPStringItem              gtinCd_S;

    /** IN_EACH_QTY_S*/
	public final EZDPBigDecimalItem              inEachQty_S;

    /** IN_INCH_LG_S*/
	public final EZDPBigDecimalItem              inInchLg_S;

    /** IN_INCH_WDT_S*/
	public final EZDPBigDecimalItem              inInchWdt_S;

    /** IN_INCH_HGT_S*/
	public final EZDPBigDecimalItem              inInchHgt_S;

    /** IN_POUND_WT_S*/
	public final EZDPBigDecimalItem              inPoundWt_S;

    /** IN_INCH_VOL_S*/
	public final EZDPBigDecimalItem              inInchVol_S;

    /** OVRD_UPC_OR_SCC14_NUM_S*/
	public final EZDPStringItem              ovrdUpcOrScc14Num_S;

    /** UPC_OR_SCC14_LB_FLG_S*/
	public final EZDPStringItem              upcOrScc14LbFlg_S;

    /** QTY_PKG_APVL_USER_ID_S*/
	public final EZDPStringItem              qtyPkgApvlUserId_S;

    /** QTY_PKG_APVL_UPD_DT_S*/
	public final EZDPDateItem              qtyPkgApvlUpdDt_S;

    /** QTY_PKG_APVL_STS_CD_S*/
	public final EZDPStringItem              qtyPkgApvlStsCd_S;

    /** DMN_PKG_APVL_USER_ID_S*/
	public final EZDPStringItem              dmnPkgApvlUserId_S;

    /** DMN_PKG_APVL_UPD_DT_S*/
	public final EZDPDateItem              dmnPkgApvlUpdDt_S;

    /** DMN_PKG_APVL_STS_CD_S*/
	public final EZDPStringItem              dmnPkgApvlStsCd_S;

    /** ADMIN_PKG_APVL_USER_ID_S*/
	public final EZDPStringItem              adminPkgApvlUserId_S;

    /** ADMIN_PKG_APVL_UPD_DT_S*/
	public final EZDPDateItem              adminPkgApvlUpdDt_S;

    /** ADMIN_PKG_APVL_STS_CD_S*/
	public final EZDPStringItem              adminPkgApvlStsCd_S;

    /** MDSE_CD_DS*/
	public final EZDPStringItem              mdseCd_DS;

    /** PKG_UOM_CD_DS*/
	public final EZDPStringItem              pkgUomCd_DS;

    /** PRIM_PKG_UOM_FLG_DS*/
	public final EZDPStringItem              primPkgUomFlg_DS;

    /** IN_INCH_LG_UOM_CD_S*/
	public final EZDPStringItem              inInchLgUomCd_S;

    /** IN_INCH_WDT_UOM_CD_S*/
	public final EZDPStringItem              inInchWdtUomCd_S;

    /** IN_INCH_HGT_UOM_CD_S*/
	public final EZDPStringItem              inInchHgtUomCd_S;

    /** IN_POUND_WT_UOM_CD_S*/
	public final EZDPStringItem              inPoundWtUomCd_S;


	/**
	 * NMZC004002_xxStoreListPMsg is constructor.
	 * The initialization when the instance of NMZC004002_xxStoreListPMsg is generated.
	 */
	public NMZC004002_xxStoreListPMsg() {
		this(false, -1);
	}

	/**
	 * NMZC004002_xxStoreListPMsg is constructor.
	 * The initialization when the instance of NMZC004002_xxStoreListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC004002_xxStoreListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		pkgUomCd_S = (EZDPStringItem)newItem("pkgUomCd_S");
		mdseCd_S = (EZDPStringItem)newItem("mdseCd_S");
		scc14Num_S = (EZDPStringItem)newItem("scc14Num_S");
		gtinCd_S = (EZDPStringItem)newItem("gtinCd_S");
		inEachQty_S = (EZDPBigDecimalItem)newItem("inEachQty_S");
		inInchLg_S = (EZDPBigDecimalItem)newItem("inInchLg_S");
		inInchWdt_S = (EZDPBigDecimalItem)newItem("inInchWdt_S");
		inInchHgt_S = (EZDPBigDecimalItem)newItem("inInchHgt_S");
		inPoundWt_S = (EZDPBigDecimalItem)newItem("inPoundWt_S");
		inInchVol_S = (EZDPBigDecimalItem)newItem("inInchVol_S");
		ovrdUpcOrScc14Num_S = (EZDPStringItem)newItem("ovrdUpcOrScc14Num_S");
		upcOrScc14LbFlg_S = (EZDPStringItem)newItem("upcOrScc14LbFlg_S");
		qtyPkgApvlUserId_S = (EZDPStringItem)newItem("qtyPkgApvlUserId_S");
		qtyPkgApvlUpdDt_S = (EZDPDateItem)newItem("qtyPkgApvlUpdDt_S");
		qtyPkgApvlStsCd_S = (EZDPStringItem)newItem("qtyPkgApvlStsCd_S");
		dmnPkgApvlUserId_S = (EZDPStringItem)newItem("dmnPkgApvlUserId_S");
		dmnPkgApvlUpdDt_S = (EZDPDateItem)newItem("dmnPkgApvlUpdDt_S");
		dmnPkgApvlStsCd_S = (EZDPStringItem)newItem("dmnPkgApvlStsCd_S");
		adminPkgApvlUserId_S = (EZDPStringItem)newItem("adminPkgApvlUserId_S");
		adminPkgApvlUpdDt_S = (EZDPDateItem)newItem("adminPkgApvlUpdDt_S");
		adminPkgApvlStsCd_S = (EZDPStringItem)newItem("adminPkgApvlStsCd_S");
		mdseCd_DS = (EZDPStringItem)newItem("mdseCd_DS");
		pkgUomCd_DS = (EZDPStringItem)newItem("pkgUomCd_DS");
		primPkgUomFlg_DS = (EZDPStringItem)newItem("primPkgUomFlg_DS");
		inInchLgUomCd_S = (EZDPStringItem)newItem("inInchLgUomCd_S");
		inInchWdtUomCd_S = (EZDPStringItem)newItem("inInchWdtUomCd_S");
		inInchHgtUomCd_S = (EZDPStringItem)newItem("inInchHgtUomCd_S");
		inPoundWtUomCd_S = (EZDPStringItem)newItem("inPoundWtUomCd_S");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC004002_xxStoreListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC004002_xxStoreListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"pkgUomCd_S", "pkgUomCd_S", "S", null, TYPE_HANKAKUEISU, "4", null},
	{"mdseCd_S", "mdseCd_S", "S", null, TYPE_HANKAKUEISU, "16", null},
	{"scc14Num_S", "scc14Num_S", "S", null, TYPE_HANKAKUEISU, "14", null},
	{"gtinCd_S", "gtinCd_S", "S", null, TYPE_HANKAKUEISU, "14", null},
	{"inEachQty_S", "inEachQty_S", "S", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"inInchLg_S", "inInchLg_S", "S", null, TYPE_SEISU_SYOSU, "9", "4"},
	{"inInchWdt_S", "inInchWdt_S", "S", null, TYPE_SEISU_SYOSU, "9", "4"},
	{"inInchHgt_S", "inInchHgt_S", "S", null, TYPE_SEISU_SYOSU, "9", "4"},
	{"inPoundWt_S", "inPoundWt_S", "S", null, TYPE_SEISU_SYOSU, "9", "4"},
	{"inInchVol_S", "inInchVol_S", "S", null, TYPE_SEISU_SYOSU, "18", "3"},
	{"ovrdUpcOrScc14Num_S", "ovrdUpcOrScc14Num_S", "S", null, TYPE_HANKAKUSUJI, "14", null},
	{"upcOrScc14LbFlg_S", "upcOrScc14LbFlg_S", "S", null, TYPE_HANKAKUEISU, "1", null},
	{"qtyPkgApvlUserId_S", "qtyPkgApvlUserId_S", "S", null, TYPE_HANKAKUEISU, "16", null},
	{"qtyPkgApvlUpdDt_S", "qtyPkgApvlUpdDt_S", "S", null, TYPE_NENTSUKIHI, "8", null},
	{"qtyPkgApvlStsCd_S", "qtyPkgApvlStsCd_S", "S", null, TYPE_HANKAKUEISU, "2", null},
	{"dmnPkgApvlUserId_S", "dmnPkgApvlUserId_S", "S", null, TYPE_HANKAKUEISU, "16", null},
	{"dmnPkgApvlUpdDt_S", "dmnPkgApvlUpdDt_S", "S", null, TYPE_NENTSUKIHI, "8", null},
	{"dmnPkgApvlStsCd_S", "dmnPkgApvlStsCd_S", "S", null, TYPE_HANKAKUEISU, "2", null},
	{"adminPkgApvlUserId_S", "adminPkgApvlUserId_S", "S", null, TYPE_HANKAKUEISU, "16", null},
	{"adminPkgApvlUpdDt_S", "adminPkgApvlUpdDt_S", "S", null, TYPE_NENTSUKIHI, "8", null},
	{"adminPkgApvlStsCd_S", "adminPkgApvlStsCd_S", "S", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCd_DS", "mdseCd_DS", "DS", null, TYPE_HANKAKUEISU, "16", null},
	{"pkgUomCd_DS", "pkgUomCd_DS", "DS", null, TYPE_HANKAKUEISU, "4", null},
	{"primPkgUomFlg_DS", "primPkgUomFlg_DS", "DS", null, TYPE_HANKAKUEISU, "1", null},
	{"inInchLgUomCd_S", "inInchLgUomCd_S", "S", null, TYPE_HANKAKUEISU, "4", null},
	{"inInchWdtUomCd_S", "inInchWdtUomCd_S", "S", null, TYPE_HANKAKUEISU, "4", null},
	{"inInchHgtUomCd_S", "inInchHgtUomCd_S", "S", null, TYPE_HANKAKUEISU, "4", null},
	{"inPoundWtUomCd_S", "inPoundWtUomCd_S", "S", null, TYPE_HANKAKUEISU, "4", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PKG_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomCd_S
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_S
        {"SCC14_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scc14Num_S
        {"GTIN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gtinCd_S
        {"IN_EACH_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inEachQty_S
        {"IN_INCH_LG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchLg_S
        {"IN_INCH_WDT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchWdt_S
        {"IN_INCH_HGT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchHgt_S
        {"IN_POUND_WT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inPoundWt_S
        {"IN_INCH_VOL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchVol_S
        {"OVRD_UPC_OR_SCC14_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ovrdUpcOrScc14Num_S
        {"UPC_OR_SCC14_LB_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upcOrScc14LbFlg_S
        {"QTY_PKG_APVL_USER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//qtyPkgApvlUserId_S
        {"QTY_PKG_APVL_UPD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//qtyPkgApvlUpdDt_S
        {"QTY_PKG_APVL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//qtyPkgApvlStsCd_S
        {"DMN_PKG_APVL_USER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dmnPkgApvlUserId_S
        {"DMN_PKG_APVL_UPD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dmnPkgApvlUpdDt_S
        {"DMN_PKG_APVL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dmnPkgApvlStsCd_S
        {"ADMIN_PKG_APVL_USER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adminPkgApvlUserId_S
        {"ADMIN_PKG_APVL_UPD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adminPkgApvlUpdDt_S
        {"ADMIN_PKG_APVL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adminPkgApvlStsCd_S
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_DS
        {"PKG_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomCd_DS
        {"PRIM_PKG_UOM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//primPkgUomFlg_DS
        {"IN_INCH_LG_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchLgUomCd_S
        {"IN_INCH_WDT_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchWdtUomCd_S
        {"IN_INCH_HGT_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchHgtUomCd_S
        {"IN_POUND_WT_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inPoundWtUomCd_S
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

