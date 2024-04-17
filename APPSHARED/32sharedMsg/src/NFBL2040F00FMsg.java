//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160801151530000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2040F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFBL2040F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2040F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PMT_HLD_DESC_TXT_H1*/
	public final EZDFStringItem              pmtHldDescTxt_H1;

    /** XX_DT_TXT_H1*/
	public final EZDFStringItem              xxDtTxt_H1;

    /** PMT_HLD_RSN_DESC_TXT_H1*/
	public final EZDFStringItem              pmtHldRsnDescTxt_H1;

    /** PMT_HLD_PSN_CD_H1*/
	public final EZDFStringItem              pmtHldPsnCd_H1;

    /** PMT_HLD_REL_PSN_CD_H1*/
	public final EZDFStringItem              pmtHldRelPsnCd_H1;

    /** XX_DT_TXT_H2*/
	public final EZDFStringItem              xxDtTxt_H2;

    /** PMT_HLD_REL_RSN_DESC_TXT_H1*/
	public final EZDFStringItem              pmtHldRelRsnDescTxt_H1;

    /** PMT_HLD_REL_CMNT_TXT_H1*/
	public final EZDFStringItem              pmtHldRelCmntTxt_H1;


	/**
	 * NFBL2040F00FMsg is constructor.
	 * The initialization when the instance of NFBL2040F00FMsg is generated.
	 */
	public NFBL2040F00FMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2040F00FMsg is constructor.
	 * The initialization when the instance of NFBL2040F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2040F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		pmtHldDescTxt_H1 = (EZDFStringItem)newItem("pmtHldDescTxt_H1");
		xxDtTxt_H1 = (EZDFStringItem)newItem("xxDtTxt_H1");
		pmtHldRsnDescTxt_H1 = (EZDFStringItem)newItem("pmtHldRsnDescTxt_H1");
		pmtHldPsnCd_H1 = (EZDFStringItem)newItem("pmtHldPsnCd_H1");
		pmtHldRelPsnCd_H1 = (EZDFStringItem)newItem("pmtHldRelPsnCd_H1");
		xxDtTxt_H2 = (EZDFStringItem)newItem("xxDtTxt_H2");
		pmtHldRelRsnDescTxt_H1 = (EZDFStringItem)newItem("pmtHldRelRsnDescTxt_H1");
		pmtHldRelCmntTxt_H1 = (EZDFStringItem)newItem("pmtHldRelCmntTxt_H1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2040F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2040F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"pmtHldDescTxt_H1", "pmtHldDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtTxt_H1", "xxDtTxt_H1", "H1", null, TYPE_HANKAKUEISU, "10", null},
	{"pmtHldRsnDescTxt_H1", "pmtHldRsnDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"pmtHldPsnCd_H1", "pmtHldPsnCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"pmtHldRelPsnCd_H1", "pmtHldRelPsnCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxDtTxt_H2", "xxDtTxt_H2", "H2", null, TYPE_HANKAKUEISU, "10", null},
	{"pmtHldRelRsnDescTxt_H1", "pmtHldRelRsnDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"pmtHldRelCmntTxt_H1", "pmtHldRelCmntTxt_H1", "H1", null, TYPE_HANKAKUEISU, "240", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PMT_HLD_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtHldDescTxt_H1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_H1
        {"PMT_HLD_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtHldRsnDescTxt_H1
        {"PMT_HLD_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtHldPsnCd_H1
        {"PMT_HLD_REL_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtHldRelPsnCd_H1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_H2
        {"PMT_HLD_REL_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtHldRelRsnDescTxt_H1
        {"PMT_HLD_REL_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtHldRelCmntTxt_H1
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

