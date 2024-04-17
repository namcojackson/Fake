//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20231205174426000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC080001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC080001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC080001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** TPL_FROM_PTNR_ID*/
	public final EZDPStringItem              tplFromPtnrId;

    /** TPL_TO_PTNR_ID*/
	public final EZDPStringItem              tplToPtnrId;

    /** ORD_DT_TM_TS_TXT*/
	public final EZDPStringItem              ordDtTmTsTxt;

    /** ORD_ID_TXT*/
	public final EZDPStringItem              ordIdTxt;

    /** TPL_REF_NM*/
	public final EZDPStringItem              tplRefNm;

    /** TPL_LOC_TXT*/
	public final EZDPStringItem              tplLocTxt;

    /** TPL_COND_TXT*/
	public final EZDPStringItem              tplCondTxt;

    /** ORD_TP_TXT*/
	public final EZDPStringItem              ordTpTxt;

    /** TPL_ORG_TXT*/
	public final EZDPStringItem              tplOrgTxt;

    /** TPL_CTRL_ID*/
	public final EZDPStringItem              tplCtrlId;

    /** CARR_CD*/
	public final EZDPStringItem              carrCd;

    /** REQ_DT_TM_TS_TXT*/
	public final EZDPStringItem              reqDtTmTsTxt;

    /** RQST_SHIP_DT_TM_TS_TXT*/
	public final EZDPStringItem              rqstShipDtTmTsTxt;

    /** SHIP_CTAC_NM_TXT*/
	public final EZDPStringItem              shipCtacNmTxt;

    /** SHIP_CTAC_PHO_NUM*/
	public final EZDPStringItem              shipCtacPhoNum;

    /** TPL_PTNR_TXT*/
	public final EZDPStringItem              tplPtnrTxt;

    /** SHIP_CUST_TXT*/
	public final EZDPStringItem              shipCustTxt;

    /** SHIP_FIRST_LINE_NM*/
	public final EZDPStringItem              shipFirstLineNm;

    /** SHIP_SCD_LINE_NM*/
	public final EZDPStringItem              shipScdLineNm;

    /** SHIP_THIRD_LINE_NM*/
	public final EZDPStringItem              shipThirdLineNm;

    /** SHIP_FRTH_LINE_NM*/
	public final EZDPStringItem              shipFrthLineNm;

    /** SHIP_FIRST_LINE_ADDR_TXT*/
	public final EZDPStringItem              shipFirstLineAddrTxt;

    /** SHIP_SCD_LINE_ADDR_TXT*/
	public final EZDPStringItem              shipScdLineAddrTxt;

    /** SHIP_ZIP_OR_POST_CD_TXT*/
	public final EZDPStringItem              shipZipOrPostCdTxt;

    /** SHIP_CTY_TXT*/
	public final EZDPStringItem              shipCtyTxt;

    /** SHIP_CTRY_TXT*/
	public final EZDPStringItem              shipCtryTxt;

    /** SHIP_ST_OR_PROV_TXT*/
	public final EZDPStringItem              shipStOrProvTxt;

    /** SHIP_PHO_NUM_TXT*/
	public final EZDPStringItem              shipPhoNumTxt;

    /** xxMsgDescList*/
	public final business.parts.NLZC080001_xxMsgDescListPMsgArray              xxMsgDescList;

    /** xxItemList*/
	public final business.parts.NLZC080001_xxItemListPMsgArray              xxItemList;

    /** xxSerNumList*/
	public final business.parts.NLZC080001_xxSerNumListPMsgArray              xxSerNumList;

    /** xxSiteSrvyList*/
	public final business.parts.NLZC080001_xxSiteSrvyListPMsgArray              xxSiteSrvyList;

    /** xxMsgIdList*/
	public final business.parts.NLZC080001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NLZC080001PMsg is constructor.
	 * The initialization when the instance of NLZC080001PMsg is generated.
	 */
	public NLZC080001PMsg() {
		this(false, -1);
	}

	/**
	 * NLZC080001PMsg is constructor.
	 * The initialization when the instance of NLZC080001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC080001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		tplFromPtnrId = (EZDPStringItem)newItem("tplFromPtnrId");
		tplToPtnrId = (EZDPStringItem)newItem("tplToPtnrId");
		ordDtTmTsTxt = (EZDPStringItem)newItem("ordDtTmTsTxt");
		ordIdTxt = (EZDPStringItem)newItem("ordIdTxt");
		tplRefNm = (EZDPStringItem)newItem("tplRefNm");
		tplLocTxt = (EZDPStringItem)newItem("tplLocTxt");
		tplCondTxt = (EZDPStringItem)newItem("tplCondTxt");
		ordTpTxt = (EZDPStringItem)newItem("ordTpTxt");
		tplOrgTxt = (EZDPStringItem)newItem("tplOrgTxt");
		tplCtrlId = (EZDPStringItem)newItem("tplCtrlId");
		carrCd = (EZDPStringItem)newItem("carrCd");
		reqDtTmTsTxt = (EZDPStringItem)newItem("reqDtTmTsTxt");
		rqstShipDtTmTsTxt = (EZDPStringItem)newItem("rqstShipDtTmTsTxt");
		shipCtacNmTxt = (EZDPStringItem)newItem("shipCtacNmTxt");
		shipCtacPhoNum = (EZDPStringItem)newItem("shipCtacPhoNum");
		tplPtnrTxt = (EZDPStringItem)newItem("tplPtnrTxt");
		shipCustTxt = (EZDPStringItem)newItem("shipCustTxt");
		shipFirstLineNm = (EZDPStringItem)newItem("shipFirstLineNm");
		shipScdLineNm = (EZDPStringItem)newItem("shipScdLineNm");
		shipThirdLineNm = (EZDPStringItem)newItem("shipThirdLineNm");
		shipFrthLineNm = (EZDPStringItem)newItem("shipFrthLineNm");
		shipFirstLineAddrTxt = (EZDPStringItem)newItem("shipFirstLineAddrTxt");
		shipScdLineAddrTxt = (EZDPStringItem)newItem("shipScdLineAddrTxt");
		shipZipOrPostCdTxt = (EZDPStringItem)newItem("shipZipOrPostCdTxt");
		shipCtyTxt = (EZDPStringItem)newItem("shipCtyTxt");
		shipCtryTxt = (EZDPStringItem)newItem("shipCtryTxt");
		shipStOrProvTxt = (EZDPStringItem)newItem("shipStOrProvTxt");
		shipPhoNumTxt = (EZDPStringItem)newItem("shipPhoNumTxt");
		xxMsgDescList = (business.parts.NLZC080001_xxMsgDescListPMsgArray)newMsgArray("xxMsgDescList");
		xxItemList = (business.parts.NLZC080001_xxItemListPMsgArray)newMsgArray("xxItemList");
		xxSerNumList = (business.parts.NLZC080001_xxSerNumListPMsgArray)newMsgArray("xxSerNumList");
		xxSiteSrvyList = (business.parts.NLZC080001_xxSiteSrvyListPMsgArray)newMsgArray("xxSiteSrvyList");
		xxMsgIdList = (business.parts.NLZC080001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC080001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC080001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"tplFromPtnrId", "tplFromPtnrId", null, null, TYPE_HANKAKUEISU, "20", null},
	{"tplToPtnrId", "tplToPtnrId", null, null, TYPE_HANKAKUEISU, "20", null},
	{"ordDtTmTsTxt", "ordDtTmTsTxt", null, null, TYPE_HANKAKUEISU, "19", null},
	{"ordIdTxt", "ordIdTxt", null, null, TYPE_HANKAKUEISU, "20", null},
	{"tplRefNm", "tplRefNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"tplLocTxt", "tplLocTxt", null, null, TYPE_HANKAKUEISU, "20", null},
	{"tplCondTxt", "tplCondTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"ordTpTxt", "ordTpTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"tplOrgTxt", "tplOrgTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"tplCtrlId", "tplCtrlId", null, null, TYPE_HANKAKUEISU, "10", null},
	{"carrCd", "carrCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"reqDtTmTsTxt", "reqDtTmTsTxt", null, null, TYPE_HANKAKUEISU, "19", null},
	{"rqstShipDtTmTsTxt", "rqstShipDtTmTsTxt", null, null, TYPE_HANKAKUEISU, "19", null},
	{"shipCtacNmTxt", "shipCtacNmTxt", null, null, TYPE_HANKAKUEISU, "25", null},
	{"shipCtacPhoNum", "shipCtacPhoNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"tplPtnrTxt", "tplPtnrTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"shipCustTxt", "shipCustTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"shipFirstLineNm", "shipFirstLineNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipScdLineNm", "shipScdLineNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipThirdLineNm", "shipThirdLineNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipFrthLineNm", "shipFrthLineNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipFirstLineAddrTxt", "shipFirstLineAddrTxt", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipScdLineAddrTxt", "shipScdLineAddrTxt", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipZipOrPostCdTxt", "shipZipOrPostCdTxt", null, null, TYPE_HANKAKUEISU, "15", null},
	{"shipCtyTxt", "shipCtyTxt", null, null, TYPE_HANKAKUEISU, "25", null},
	{"shipCtryTxt", "shipCtryTxt", null, null, TYPE_HANKAKUEISU, "3", null},
	{"shipStOrProvTxt", "shipStOrProvTxt", null, null, TYPE_HANKAKUEISU, "2", null},
	{"shipPhoNumTxt", "shipPhoNumTxt", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxMsgDescList", "xxMsgDescList", null, "100", "business.parts.NLZC080001_xxMsgDescListPMsgArray", null, null},
	
	{"xxItemList", "xxItemList", null, "1000", "business.parts.NLZC080001_xxItemListPMsgArray", null, null},
	
	{"xxSerNumList", "xxSerNumList", null, "100", "business.parts.NLZC080001_xxSerNumListPMsgArray", null, null},
	
	{"xxSiteSrvyList", "xxSiteSrvyList", null, "8000", "business.parts.NLZC080001_xxSiteSrvyListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NLZC080001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"TPL_FROM_PTNR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tplFromPtnrId
        {"TPL_TO_PTNR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tplToPtnrId
        {"ORD_DT_TM_TS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordDtTmTsTxt
        {"ORD_ID_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordIdTxt
        {"TPL_REF_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tplRefNm
        {"TPL_LOC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tplLocTxt
        {"TPL_COND_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tplCondTxt
        {"ORD_TP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordTpTxt
        {"TPL_ORG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tplOrgTxt
        {"TPL_CTRL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tplCtrlId
        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd
        {"REQ_DT_TM_TS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//reqDtTmTsTxt
        {"RQST_SHIP_DT_TM_TS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstShipDtTmTsTxt
        {"SHIP_CTAC_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipCtacNmTxt
        {"SHIP_CTAC_PHO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipCtacPhoNum
        {"TPL_PTNR_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tplPtnrTxt
        {"SHIP_CUST_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipCustTxt
        {"SHIP_FIRST_LINE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFirstLineNm
        {"SHIP_SCD_LINE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipScdLineNm
        {"SHIP_THIRD_LINE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipThirdLineNm
        {"SHIP_FRTH_LINE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFrthLineNm
        {"SHIP_FIRST_LINE_ADDR_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFirstLineAddrTxt
        {"SHIP_SCD_LINE_ADDR_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipScdLineAddrTxt
        {"SHIP_ZIP_OR_POST_CD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipZipOrPostCdTxt
        {"SHIP_CTY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipCtyTxt
        {"SHIP_CTRY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipCtryTxt
        {"SHIP_ST_OR_PROV_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipStOrProvTxt
        {"SHIP_PHO_NUM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipPhoNumTxt
		null,	//xxMsgDescList
		null,	//xxItemList
		null,	//xxSerNumList
		null,	//xxSiteSrvyList
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

