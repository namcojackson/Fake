//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20210130090728000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC225001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC225001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC225001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** ordHdr*/
	public final business.parts.NWZC225001_ordHdrPMsgArray              ordHdr;

    /** config*/
	public final business.parts.NWZC225001_configPMsgArray              config;

    /** slsCr*/
	public final business.parts.NWZC225001_slsCrPMsgArray              slsCr;

    /** istlInfo*/
	public final business.parts.NWZC225001_istlInfoPMsgArray              istlInfo;

    /** dlvyInfo*/
	public final business.parts.NWZC225001_dlvyInfoPMsgArray              dlvyInfo;

    /** siteSrvy*/
	public final business.parts.NWZC225001_siteSrvyPMsgArray              siteSrvy;

    /** ctacPsn*/
	public final business.parts.NWZC225001_ctacPsnPMsgArray              ctacPsn;

    /** dtl*/
	public final business.parts.NWZC225001_dtlPMsgArray              dtl;

    /** rtrnDtl*/
	public final business.parts.NWZC225001_rtrnDtlPMsgArray              rtrnDtl;

    /** svcDtl*/
	public final business.parts.NWZC225001_svcDtlPMsgArray              svcDtl;

    /** svcConfigRef*/
	public final business.parts.NWZC225001_svcConfigRefPMsgArray              svcConfigRef;

    /** svcPrc*/
	public final business.parts.NWZC225001_svcPrcPMsgArray              svcPrc;

    /** svcUsgPrc*/
	public final business.parts.NWZC225001_svcUsgPrcPMsgArray              svcUsgPrc;

    /** svcAddlBasePrc*/
	public final business.parts.NWZC225001_svcAddlBasePrcPMsgArray              svcAddlBasePrc;

    /** svcAddlChrgPrc*/
	public final business.parts.NWZC225001_svcAddlChrgPrcPMsgArray              svcAddlChrgPrc;

    /** splyPgm*/
	public final business.parts.NWZC225001_splyPgmPMsgArray              splyPgm;

    /** splyPgmLine*/
	public final business.parts.NWZC225001_splyPgmLinePMsgArray              splyPgmLine;

    /** svcTermCond*/
	public final business.parts.NWZC225001_svcTermCondPMsgArray              svcTermCond;

    /** XX_INTFC_RSLT_CD*/
	public final EZDPStringItem              xxIntfcRsltCd;

    /** DS_IMPT_ORD_PK*/
	public final EZDPBigDecimalItem              dsImptOrdPk;

    /** ORD_SRC_IMPT_TS*/
	public final EZDPStringItem              ordSrcImptTs;

    /** CPO_ORD_NUM*/
	public final EZDPStringItem              cpoOrdNum;

    /** DS_CPO_CRAT_TS*/
	public final EZDPStringItem              dsCpoCratTs;

    /** xxMsgPrmList*/
	public final business.parts.NWZC225001_xxMsgPrmListPMsgArray              xxMsgPrmList;

    /** xxMsgIdList*/
	public final business.parts.NWZC225001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NWZC225001PMsg is constructor.
	 * The initialization when the instance of NWZC225001PMsg is generated.
	 */
	public NWZC225001PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC225001PMsg is constructor.
	 * The initialization when the instance of NWZC225001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC225001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		ordHdr = (business.parts.NWZC225001_ordHdrPMsgArray)newMsgArray("ordHdr");
		config = (business.parts.NWZC225001_configPMsgArray)newMsgArray("config");
		slsCr = (business.parts.NWZC225001_slsCrPMsgArray)newMsgArray("slsCr");
		istlInfo = (business.parts.NWZC225001_istlInfoPMsgArray)newMsgArray("istlInfo");
		dlvyInfo = (business.parts.NWZC225001_dlvyInfoPMsgArray)newMsgArray("dlvyInfo");
		siteSrvy = (business.parts.NWZC225001_siteSrvyPMsgArray)newMsgArray("siteSrvy");
		ctacPsn = (business.parts.NWZC225001_ctacPsnPMsgArray)newMsgArray("ctacPsn");
		dtl = (business.parts.NWZC225001_dtlPMsgArray)newMsgArray("dtl");
		rtrnDtl = (business.parts.NWZC225001_rtrnDtlPMsgArray)newMsgArray("rtrnDtl");
		svcDtl = (business.parts.NWZC225001_svcDtlPMsgArray)newMsgArray("svcDtl");
		svcConfigRef = (business.parts.NWZC225001_svcConfigRefPMsgArray)newMsgArray("svcConfigRef");
		svcPrc = (business.parts.NWZC225001_svcPrcPMsgArray)newMsgArray("svcPrc");
		svcUsgPrc = (business.parts.NWZC225001_svcUsgPrcPMsgArray)newMsgArray("svcUsgPrc");
		svcAddlBasePrc = (business.parts.NWZC225001_svcAddlBasePrcPMsgArray)newMsgArray("svcAddlBasePrc");
		svcAddlChrgPrc = (business.parts.NWZC225001_svcAddlChrgPrcPMsgArray)newMsgArray("svcAddlChrgPrc");
		splyPgm = (business.parts.NWZC225001_splyPgmPMsgArray)newMsgArray("splyPgm");
		splyPgmLine = (business.parts.NWZC225001_splyPgmLinePMsgArray)newMsgArray("splyPgmLine");
		svcTermCond = (business.parts.NWZC225001_svcTermCondPMsgArray)newMsgArray("svcTermCond");
		xxIntfcRsltCd = (EZDPStringItem)newItem("xxIntfcRsltCd");
		dsImptOrdPk = (EZDPBigDecimalItem)newItem("dsImptOrdPk");
		ordSrcImptTs = (EZDPStringItem)newItem("ordSrcImptTs");
		cpoOrdNum = (EZDPStringItem)newItem("cpoOrdNum");
		dsCpoCratTs = (EZDPStringItem)newItem("dsCpoCratTs");
		xxMsgPrmList = (business.parts.NWZC225001_xxMsgPrmListPMsgArray)newMsgArray("xxMsgPrmList");
		xxMsgIdList = (business.parts.NWZC225001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC225001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC225001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"ordHdr", "ordHdr", null, "1", "business.parts.NWZC225001_ordHdrPMsgArray", null, null},
	
	{"config", "config", null, "500", "business.parts.NWZC225001_configPMsgArray", null, null},
	
	{"slsCr", "slsCr", null, "1500", "business.parts.NWZC225001_slsCrPMsgArray", null, null},
	
	{"istlInfo", "istlInfo", null, "500", "business.parts.NWZC225001_istlInfoPMsgArray", null, null},
	
	{"dlvyInfo", "dlvyInfo", null, "500", "business.parts.NWZC225001_dlvyInfoPMsgArray", null, null},
	
	{"siteSrvy", "siteSrvy", null, "500", "business.parts.NWZC225001_siteSrvyPMsgArray", null, null},
	
	{"ctacPsn", "ctacPsn", null, "1500", "business.parts.NWZC225001_ctacPsnPMsgArray", null, null},
	
	{"dtl", "dtl", null, "3000", "business.parts.NWZC225001_dtlPMsgArray", null, null},
	
	{"rtrnDtl", "rtrnDtl", null, "1000", "business.parts.NWZC225001_rtrnDtlPMsgArray", null, null},
	
	{"svcDtl", "svcDtl", null, "1500", "business.parts.NWZC225001_svcDtlPMsgArray", null, null},
	
	{"svcConfigRef", "svcConfigRef", null, "1500", "business.parts.NWZC225001_svcConfigRefPMsgArray", null, null},
	
	{"svcPrc", "svcPrc", null, "1500", "business.parts.NWZC225001_svcPrcPMsgArray", null, null},
	
	{"svcUsgPrc", "svcUsgPrc", null, "1500", "business.parts.NWZC225001_svcUsgPrcPMsgArray", null, null},
	
	{"svcAddlBasePrc", "svcAddlBasePrc", null, "3000", "business.parts.NWZC225001_svcAddlBasePrcPMsgArray", null, null},
	
	{"svcAddlChrgPrc", "svcAddlChrgPrc", null, "3000", "business.parts.NWZC225001_svcAddlChrgPrcPMsgArray", null, null},
	
	{"splyPgm", "splyPgm", null, "100", "business.parts.NWZC225001_splyPgmPMsgArray", null, null},
	
	{"splyPgmLine", "splyPgmLine", null, "1000", "business.parts.NWZC225001_splyPgmLinePMsgArray", null, null},
	
	{"svcTermCond", "svcTermCond", null, "3000", "business.parts.NWZC225001_svcTermCondPMsgArray", null, null},
	
	{"xxIntfcRsltCd", "xxIntfcRsltCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsImptOrdPk", "dsImptOrdPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ordSrcImptTs", "ordSrcImptTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dsCpoCratTs", "dsCpoCratTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxMsgPrmList", "xxMsgPrmList", null, "200", "business.parts.NWZC225001_xxMsgPrmListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "200", "business.parts.NWZC225001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
		null,	//ordHdr
		null,	//config
		null,	//slsCr
		null,	//istlInfo
		null,	//dlvyInfo
		null,	//siteSrvy
		null,	//ctacPsn
		null,	//dtl
		null,	//rtrnDtl
		null,	//svcDtl
		null,	//svcConfigRef
		null,	//svcPrc
		null,	//svcUsgPrc
		null,	//svcAddlBasePrc
		null,	//svcAddlChrgPrc
		null,	//splyPgm
		null,	//splyPgmLine
		null,	//svcTermCond
        {"XX_INTFC_RSLT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxIntfcRsltCd
        {"DS_IMPT_ORD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptOrdPk
        {"ORD_SRC_IMPT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcImptTs
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"DS_CPO_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoCratTs
		null,	//xxMsgPrmList
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
