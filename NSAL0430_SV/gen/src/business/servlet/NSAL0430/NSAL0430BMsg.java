//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160916131748000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0430BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0430;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0430 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0430BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_MACH_MSTR_PK*/
	public final EZDBBigDecimalItem              svcMachMstrPk;

    /** SER_NUM*/
	public final EZDBStringItem              serNum;

    /** MDSE_CD*/
	public final EZDBStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDBStringItem              mdseDescShortTxt;

    /** MDL_ID*/
	public final EZDBBigDecimalItem              mdlId;

    /** MDL_NM*/
	public final EZDBStringItem              mdlNm;

    /** A*/
	public final business.servlet.NSAL0430.NSAL0430_ABMsgArray              A;

    /** B*/
	public final business.servlet.NSAL0430.NSAL0430_BBMsgArray              B;

    /** SLS_DT*/
	public final EZDBDateItem              slsDt;

    /** MTR_EST_PROC_DT*/
	public final EZDBDateItem              mtrEstProcDt;

    /** C*/
	public final business.servlet.NSAL0430.NSAL0430_CBMsgArray              C;

    /** XX_ROW_NUM*/
	public final EZDBBigDecimalItem              xxRowNum;

    /** D*/
	public final business.servlet.NSAL0430.NSAL0430_DBMsgArray              D;

    /** P*/
	public final business.servlet.NSAL0430.NSAL0430_PBMsgArray              P;


	/**
	 * NSAL0430BMsg is constructor.
	 * The initialization when the instance of NSAL0430BMsg is generated.
	 */
	public NSAL0430BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0430BMsg is constructor.
	 * The initialization when the instance of NSAL0430BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0430BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcMachMstrPk = (EZDBBigDecimalItem)newItem("svcMachMstrPk");
		serNum = (EZDBStringItem)newItem("serNum");
		mdseCd = (EZDBStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDBStringItem)newItem("mdseDescShortTxt");
		mdlId = (EZDBBigDecimalItem)newItem("mdlId");
		mdlNm = (EZDBStringItem)newItem("mdlNm");
		A = (business.servlet.NSAL0430.NSAL0430_ABMsgArray)newMsgArray("A");
		B = (business.servlet.NSAL0430.NSAL0430_BBMsgArray)newMsgArray("B");
		slsDt = (EZDBDateItem)newItem("slsDt");
		mtrEstProcDt = (EZDBDateItem)newItem("mtrEstProcDt");
		C = (business.servlet.NSAL0430.NSAL0430_CBMsgArray)newMsgArray("C");
		xxRowNum = (EZDBBigDecimalItem)newItem("xxRowNum");
		D = (business.servlet.NSAL0430.NSAL0430_DBMsgArray)newMsgArray("D");
		P = (business.servlet.NSAL0430.NSAL0430_PBMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0430BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0430BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"mdlId", "mdlId", null, null, TYPE_SEISU_SYOSU, "22", "0"},
	{"mdlNm", "mdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "200", "business.servlet.NSAL0430.NSAL0430_ABMsgArray", null, null},
	
	{"B", "B", null, "200", "business.servlet.NSAL0430.NSAL0430_BBMsgArray", null, null},
	
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"mtrEstProcDt", "mtrEstProcDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"C", "C", null, "200", "business.servlet.NSAL0430.NSAL0430_CBMsgArray", null, null},
	
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"D", "D", null, "200", "business.servlet.NSAL0430.NSAL0430_DBMsgArray", null, null},
	
	{"P", "P", null, "200", "business.servlet.NSAL0430.NSAL0430_PBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId
        {"MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm
		null,	//A
		null,	//B
        {"SLS_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//slsDt
        {"MTR_EST_PROC_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//mtrEstProcDt
		null,	//C
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
		null,	//D
		null,	//P
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

