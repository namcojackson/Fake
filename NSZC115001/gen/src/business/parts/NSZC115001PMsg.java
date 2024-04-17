//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180823154148000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC115001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC115001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC115001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_PROC_MD*/
	public final EZDPStringItem              xxProcMd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** REF_CPO_ORD_NUM*/
	public final EZDPStringItem              refCpoOrdNum;

    /** CPO_SRC_TP_CD*/
	public final EZDPStringItem              cpoSrcTpCd;

    /** svcDtlList*/
	public final business.parts.NSZC115001_svcDtlListPMsgArray              svcDtlList;

    /** svcConfigRefList*/
	public final business.parts.NSZC115001_svcConfigRefListPMsgArray              svcConfigRefList;

    /** svcPrcList*/
	public final business.parts.NSZC115001_svcPrcListPMsgArray              svcPrcList;

    /** svcUsgPrcList*/
	public final business.parts.NSZC115001_svcUsgPrcListPMsgArray              svcUsgPrcList;

    /** svcAddlBasePrcList*/
	public final business.parts.NSZC115001_svcAddlBasePrcListPMsgArray              svcAddlBasePrcList;

    /** svcAddlChrgPrcList*/
	public final business.parts.NSZC115001_svcAddlChrgPrcListPMsgArray              svcAddlChrgPrcList;

    /** xxMsgIdList*/
	public final business.parts.NSZC115001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC115001PMsg is constructor.
	 * The initialization when the instance of NSZC115001PMsg is generated.
	 */
	public NSZC115001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC115001PMsg is constructor.
	 * The initialization when the instance of NSZC115001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC115001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxProcMd = (EZDPStringItem)newItem("xxProcMd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		refCpoOrdNum = (EZDPStringItem)newItem("refCpoOrdNum");
		cpoSrcTpCd = (EZDPStringItem)newItem("cpoSrcTpCd");
		svcDtlList = (business.parts.NSZC115001_svcDtlListPMsgArray)newMsgArray("svcDtlList");
		svcConfigRefList = (business.parts.NSZC115001_svcConfigRefListPMsgArray)newMsgArray("svcConfigRefList");
		svcPrcList = (business.parts.NSZC115001_svcPrcListPMsgArray)newMsgArray("svcPrcList");
		svcUsgPrcList = (business.parts.NSZC115001_svcUsgPrcListPMsgArray)newMsgArray("svcUsgPrcList");
		svcAddlBasePrcList = (business.parts.NSZC115001_svcAddlBasePrcListPMsgArray)newMsgArray("svcAddlBasePrcList");
		svcAddlChrgPrcList = (business.parts.NSZC115001_svcAddlChrgPrcListPMsgArray)newMsgArray("svcAddlChrgPrcList");
		xxMsgIdList = (business.parts.NSZC115001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC115001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC115001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxProcMd", "xxProcMd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"refCpoOrdNum", "refCpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoSrcTpCd", "cpoSrcTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"svcDtlList", "svcDtlList", null, "1500", "business.parts.NSZC115001_svcDtlListPMsgArray", null, null},
	
	{"svcConfigRefList", "svcConfigRefList", null, "1500", "business.parts.NSZC115001_svcConfigRefListPMsgArray", null, null},
	
	{"svcPrcList", "svcPrcList", null, "1500", "business.parts.NSZC115001_svcPrcListPMsgArray", null, null},
	
	{"svcUsgPrcList", "svcUsgPrcList", null, "2000", "business.parts.NSZC115001_svcUsgPrcListPMsgArray", null, null},
	
	{"svcAddlBasePrcList", "svcAddlBasePrcList", null, "2000", "business.parts.NSZC115001_svcAddlBasePrcListPMsgArray", null, null},
	
	{"svcAddlChrgPrcList", "svcAddlChrgPrcList", null, "2000", "business.parts.NSZC115001_svcAddlChrgPrcListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NSZC115001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_PROC_MD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxProcMd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"REF_CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//refCpoOrdNum
        {"CPO_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSrcTpCd
		null,	//svcDtlList
		null,	//svcConfigRefList
		null,	//svcPrcList
		null,	//svcUsgPrcList
		null,	//svcAddlBasePrcList
		null,	//svcAddlChrgPrcList
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

