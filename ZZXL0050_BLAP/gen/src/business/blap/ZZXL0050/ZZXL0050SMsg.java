//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20090918145003000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZXL0050SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZXL0050;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZXL0050 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZXL0050SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** DT_PROC_CD*/
	public final EZDSStringItem              dtProcCd;

    /** XX_PROC_TP_CD_L*/
	public final EZDSStringItem              xxProcTpCd_L;

    /** XX_DSPL_TP_TXT_L*/
	public final EZDSStringItem              xxDsplTpTxt_L;

    /** DT_MGT_PGM_ID*/
	public final EZDSStringItem              dtMgtPgmId;

    /** XX_SCR_NM*/
	public final EZDSStringItem              xxScrNm;

    /** A*/
	public final business.blap.ZZXL0050.ZZXL0050_ASMsgArray              A;

    /** DT_PROC_CD_01*/
	public final EZDSStringItem              dtProcCd_01;

    /** DT_MGT_PGM_ID_01*/
	public final EZDSStringItem              dtMgtPgmId_01;

    /** MGT_DT_01*/
	public final EZDSDateItem              mgtDt_01;

    /** _EZUpdateDateTime_01*/
	public final EZDSStringItem              ezUpTime_01;

    /** _EZUpTimeZone_01*/
	public final EZDSStringItem              ezUpTimeZone_01;


	/**
	 * ZZXL0050SMsg is constructor.
	 * The initialization when the instance of ZZXL0050SMsg is generated.
	 */
	public ZZXL0050SMsg() {
		this(false, -1);
	}

	/**
	 * ZZXL0050SMsg is constructor.
	 * The initialization when the instance of ZZXL0050SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZXL0050SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		dtProcCd = (EZDSStringItem)newItem("dtProcCd");
		xxProcTpCd_L = (EZDSStringItem)newItem("xxProcTpCd_L");
		xxDsplTpTxt_L = (EZDSStringItem)newItem("xxDsplTpTxt_L");
		dtMgtPgmId = (EZDSStringItem)newItem("dtMgtPgmId");
		xxScrNm = (EZDSStringItem)newItem("xxScrNm");
		A = (business.blap.ZZXL0050.ZZXL0050_ASMsgArray)newMsgArray("A");
		dtProcCd_01 = (EZDSStringItem)newItem("dtProcCd_01");
		dtMgtPgmId_01 = (EZDSStringItem)newItem("dtMgtPgmId_01");
		mgtDt_01 = (EZDSDateItem)newItem("mgtDt_01");
		ezUpTime_01 = (EZDSStringItem)newItem("ezUpTime_01");
		ezUpTimeZone_01 = (EZDSStringItem)newItem("ezUpTimeZone_01");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZXL0050SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZXL0050SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dtProcCd", "dtProcCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxProcTpCd_L", "xxProcTpCd_L", "L", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDsplTpTxt_L", "xxDsplTpTxt_L", "L", null, TYPE_HANKAKUEISU, "22", null},
	{"dtMgtPgmId", "dtMgtPgmId", null, null, TYPE_HANKAKUEISU, "10", null},
	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"A", "A", null, "200", "business.blap.ZZXL0050.ZZXL0050_ASMsgArray", null, null},
	
	{"dtProcCd_01", "dtProcCd_01", "01", null, TYPE_HANKAKUEISU, "1", null},
	{"dtMgtPgmId_01", "dtMgtPgmId_01", "01", null, TYPE_HANKAKUEISU, "10", null},
	{"mgtDt_01", "mgtDt_01", "01", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_01", "ezUpTime_01", "01", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_01", "ezUpTimeZone_01", "01", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"DT_PROC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dtProcCd
        {"XX_PROC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxProcTpCd_L
        {"XX_DSPL_TP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsplTpTxt_L
        {"DT_MGT_PGM_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dtMgtPgmId
        {"XX_SCR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
		null,	//A
        {"DT_PROC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dtProcCd_01
        {"DT_MGT_PGM_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dtMgtPgmId_01
        {"MGT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mgtDt_01
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_01
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_01
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

