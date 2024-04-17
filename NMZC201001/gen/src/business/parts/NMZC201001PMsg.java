//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160113213219000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC201001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMZC201001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC201001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** PROC_DT*/
	public final EZDPDateItem              procDt;

    /** PRNT_VND_CD*/
	public final EZDPStringItem              prntVndCd;

    /** PRNT_VND_NM*/
	public final EZDPStringItem              prntVndNm;

    /** PRNT_VND_TP_CD*/
	public final EZDPStringItem              prntVndTpCd;

    /** TAX_PAYER_ID*/
	public final EZDPStringItem              taxPayerId;

    /** INAC_DT*/
	public final EZDPDateItem              inacDt;

    /** TAX_PAYER_RG_NUM*/
	public final EZDPStringItem              taxPayerRgNum;

    /** ARCS_SPLY_NUM*/
	public final EZDPStringItem              arcsSplyNum;

    /** ARCS_SPLY_ID*/
	public final EZDPBigDecimalItem              arcsSplyId;

    /** INDY_TP_CD*/
	public final EZDPStringItem              indyTpCd;

    /** MNRTY_OWND_TP_CD*/
	public final EZDPStringItem              mnrtyOwndTpCd;

    /** SPLY_ORG_TP_CD*/
	public final EZDPStringItem              splyOrgTpCd;

    /** SPLY_ONE_TM_FLG*/
	public final EZDPStringItem              splyOneTmFlg;

    /** SM_BIZ_FLG*/
	public final EZDPStringItem              smBizFlg;

    /** WOMEN_OWND_FLG*/
	public final EZDPStringItem              womenOwndFlg;

    /** PAY_ALONE_FLG*/
	public final EZDPStringItem              payAloneFlg;

    /** DISC_TAKE_FLG*/
	public final EZDPStringItem              discTakeFlg;

    /** PO_PMT_HLD_FLG*/
	public final EZDPStringItem              poPmtHldFlg;

    /** FD_RPT_FLG*/
	public final EZDPStringItem              fdRptFlg;

    /** INC_TAX_TP_CD*/
	public final EZDPStringItem              incTaxTpCd;

    /** ST_TAX_FLG*/
	public final EZDPStringItem              stTaxFlg;

    /** SPLY_RPT_NM*/
	public final EZDPStringItem              splyRptNm;

    /** COA_AFFL_CD*/
	public final EZDPStringItem              coaAfflCd;

    /** SPLY_HUB_ZN_CD*/
	public final EZDPStringItem              splyHubZnCd;

    /** DS_ACCT_NUM*/
	public final EZDPStringItem              dsAcctNum;

    /** VND_PMT_TERM_CD*/
	public final EZDPStringItem              vndPmtTermCd;

    /** VND_PMT_TERM_DESC_TXT*/
	public final EZDPStringItem              vndPmtTermDescTxt;

    /** VND_PMT_METH_CD*/
	public final EZDPStringItem              vndPmtMethCd;

    /** PAY_GRP_CD*/
	public final EZDPStringItem              payGrpCd;

    /** xxMsgIdList*/
	public final business.parts.NMZC201001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NMZC201001PMsg is constructor.
	 * The initialization when the instance of NMZC201001PMsg is generated.
	 */
	public NMZC201001PMsg() {
		this(false, -1);
	}

	/**
	 * NMZC201001PMsg is constructor.
	 * The initialization when the instance of NMZC201001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC201001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		procDt = (EZDPDateItem)newItem("procDt");
		prntVndCd = (EZDPStringItem)newItem("prntVndCd");
		prntVndNm = (EZDPStringItem)newItem("prntVndNm");
		prntVndTpCd = (EZDPStringItem)newItem("prntVndTpCd");
		taxPayerId = (EZDPStringItem)newItem("taxPayerId");
		inacDt = (EZDPDateItem)newItem("inacDt");
		taxPayerRgNum = (EZDPStringItem)newItem("taxPayerRgNum");
		arcsSplyNum = (EZDPStringItem)newItem("arcsSplyNum");
		arcsSplyId = (EZDPBigDecimalItem)newItem("arcsSplyId");
		indyTpCd = (EZDPStringItem)newItem("indyTpCd");
		mnrtyOwndTpCd = (EZDPStringItem)newItem("mnrtyOwndTpCd");
		splyOrgTpCd = (EZDPStringItem)newItem("splyOrgTpCd");
		splyOneTmFlg = (EZDPStringItem)newItem("splyOneTmFlg");
		smBizFlg = (EZDPStringItem)newItem("smBizFlg");
		womenOwndFlg = (EZDPStringItem)newItem("womenOwndFlg");
		payAloneFlg = (EZDPStringItem)newItem("payAloneFlg");
		discTakeFlg = (EZDPStringItem)newItem("discTakeFlg");
		poPmtHldFlg = (EZDPStringItem)newItem("poPmtHldFlg");
		fdRptFlg = (EZDPStringItem)newItem("fdRptFlg");
		incTaxTpCd = (EZDPStringItem)newItem("incTaxTpCd");
		stTaxFlg = (EZDPStringItem)newItem("stTaxFlg");
		splyRptNm = (EZDPStringItem)newItem("splyRptNm");
		coaAfflCd = (EZDPStringItem)newItem("coaAfflCd");
		splyHubZnCd = (EZDPStringItem)newItem("splyHubZnCd");
		dsAcctNum = (EZDPStringItem)newItem("dsAcctNum");
		vndPmtTermCd = (EZDPStringItem)newItem("vndPmtTermCd");
		vndPmtTermDescTxt = (EZDPStringItem)newItem("vndPmtTermDescTxt");
		vndPmtMethCd = (EZDPStringItem)newItem("vndPmtMethCd");
		payGrpCd = (EZDPStringItem)newItem("payGrpCd");
		xxMsgIdList = (business.parts.NMZC201001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC201001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC201001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"procDt", "procDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"prntVndCd", "prntVndCd", null, null, TYPE_HANKAKUEISU, "30", null},
	{"prntVndNm", "prntVndNm", null, null, TYPE_HANKAKUEISU, "240", null},
	{"prntVndTpCd", "prntVndTpCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"taxPayerId", "taxPayerId", null, null, TYPE_HANKAKUEISU, "20", null},
	{"inacDt", "inacDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"taxPayerRgNum", "taxPayerRgNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arcsSplyNum", "arcsSplyNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"arcsSplyId", "arcsSplyId", null, null, TYPE_SEISU_SYOSU, "38", "0"},
	{"indyTpCd", "indyTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"mnrtyOwndTpCd", "mnrtyOwndTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyOrgTpCd", "splyOrgTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyOneTmFlg", "splyOneTmFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"smBizFlg", "smBizFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"womenOwndFlg", "womenOwndFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"payAloneFlg", "payAloneFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"discTakeFlg", "discTakeFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"poPmtHldFlg", "poPmtHldFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"fdRptFlg", "fdRptFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"incTaxTpCd", "incTaxTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"stTaxFlg", "stTaxFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"splyRptNm", "splyRptNm", null, null, TYPE_HANKAKUEISU, "80", null},
	{"coaAfflCd", "coaAfflCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"splyHubZnCd", "splyHubZnCd", null, null, TYPE_HANKAKUEISU, "150", null},
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"vndPmtTermCd", "vndPmtTermCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"vndPmtTermDescTxt", "vndPmtTermDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"vndPmtMethCd", "vndPmtMethCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"payGrpCd", "payGrpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NMZC201001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"PROC_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procDt
        {"PRNT_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd
        {"PRNT_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndNm
        {"PRNT_VND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndTpCd
        {"TAX_PAYER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxPayerId
        {"INAC_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inacDt
        {"TAX_PAYER_RG_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxPayerRgNum
        {"ARCS_SPLY_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arcsSplyNum
        {"ARCS_SPLY_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arcsSplyId
        {"INDY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indyTpCd
        {"MNRTY_OWND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mnrtyOwndTpCd
        {"SPLY_ORG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyOrgTpCd
        {"SPLY_ONE_TM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyOneTmFlg
        {"SM_BIZ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//smBizFlg
        {"WOMEN_OWND_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//womenOwndFlg
        {"PAY_ALONE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//payAloneFlg
        {"DISC_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//discTakeFlg
        {"PO_PMT_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poPmtHldFlg
        {"FD_RPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fdRptFlg
        {"INC_TAX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//incTaxTpCd
        {"ST_TAX_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stTaxFlg
        {"SPLY_RPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyRptNm
        {"COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd
        {"SPLY_HUB_ZN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyHubZnCd
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"VND_PMT_TERM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndPmtTermCd
        {"VND_PMT_TERM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndPmtTermDescTxt
        {"VND_PMT_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndPmtMethCd
        {"PAY_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//payGrpCd
		null,	//xxMsgIdList
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
