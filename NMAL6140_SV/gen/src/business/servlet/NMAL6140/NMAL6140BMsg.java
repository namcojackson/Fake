//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160217175307000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6140BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6140 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6140BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_H1*/
	public final EZDBStringItem              xxChkBox_H1;

    /** XX_CHK_BOX_H2*/
	public final EZDBStringItem              xxChkBox_H2;

    /** XX_CHK_BOX_H3*/
	public final EZDBStringItem              xxChkBox_H3;

    /** FIRST_LINE_ADDR*/
	public final EZDBStringItem              firstLineAddr;

    /** SCD_LINE_ADDR*/
	public final EZDBStringItem              scdLineAddr;

    /** THIRD_LINE_ADDR*/
	public final EZDBStringItem              thirdLineAddr;

    /** FRTH_LINE_ADDR*/
	public final EZDBStringItem              frthLineAddr;

    /** CTY_ADDR*/
	public final EZDBStringItem              ctyAddr;

    /** ST_CD*/
	public final EZDBStringItem              stCd;

    /** ST_CD_H1*/
	public final EZDBStringItemArray              stCd_H1;

    /** ST_CD_H2*/
	public final EZDBStringItemArray              stCd_H2;

    /** POST_CD*/
	public final EZDBStringItem              postCd;

    /** PROV_NM*/
	public final EZDBStringItem              provNm;

    /** CNTY_NM*/
	public final EZDBStringItem              cntyNm;

    /** CTRY_CD*/
	public final EZDBStringItem              ctryCd;

    /** CTRY_NM*/
	public final EZDBStringItem              ctryNm;

    /** P*/
	public final business.servlet.NMAL6140.NMAL6140_PBMsgArray              P;


	/**
	 * NMAL6140BMsg is constructor.
	 * The initialization when the instance of NMAL6140BMsg is generated.
	 */
	public NMAL6140BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6140BMsg is constructor.
	 * The initialization when the instance of NMAL6140BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6140BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_H1 = (EZDBStringItem)newItem("xxChkBox_H1");
		xxChkBox_H2 = (EZDBStringItem)newItem("xxChkBox_H2");
		xxChkBox_H3 = (EZDBStringItem)newItem("xxChkBox_H3");
		firstLineAddr = (EZDBStringItem)newItem("firstLineAddr");
		scdLineAddr = (EZDBStringItem)newItem("scdLineAddr");
		thirdLineAddr = (EZDBStringItem)newItem("thirdLineAddr");
		frthLineAddr = (EZDBStringItem)newItem("frthLineAddr");
		ctyAddr = (EZDBStringItem)newItem("ctyAddr");
		stCd = (EZDBStringItem)newItem("stCd");
		stCd_H1 = (EZDBStringItemArray)newItemArray("stCd_H1");
		stCd_H2 = (EZDBStringItemArray)newItemArray("stCd_H2");
		postCd = (EZDBStringItem)newItem("postCd");
		provNm = (EZDBStringItem)newItem("provNm");
		cntyNm = (EZDBStringItem)newItem("cntyNm");
		ctryCd = (EZDBStringItem)newItem("ctryCd");
		ctryNm = (EZDBStringItem)newItem("ctryNm");
		P = (business.servlet.NMAL6140.NMAL6140_PBMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6140BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6140BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H2", "xxChkBox_H2", "H2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H3", "xxChkBox_H3", "H3", null, TYPE_HANKAKUEIJI, "1", null},
	{"firstLineAddr", "firstLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr", "scdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"thirdLineAddr", "thirdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"frthLineAddr", "frthLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr", "ctyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"stCd", "stCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"stCd_H1", "stCd_H1", "H1", "99", TYPE_HANKAKUEISU, "2", null},
	{"stCd_H2", "stCd_H2", "H2", "99", TYPE_HANKAKUEISU, "2", null},
	{"postCd", "postCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"provNm", "provNm", null, null, TYPE_HANKAKUEISU, "25", null},
	{"cntyNm", "cntyNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ctryCd", "ctryCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"ctryNm", "ctryNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"P", "P", null, "11", "business.servlet.NMAL6140.NMAL6140_PBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H2
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H3
        {"FIRST_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr
        {"SCD_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr
        {"THIRD_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdLineAddr
        {"FRTH_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthLineAddr
        {"CTY_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr
        {"ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd
        {"ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_H1
        {"ST_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_H2
        {"POST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd
        {"PROV_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//provNm
        {"CNTY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyNm
        {"CTRY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd
        {"CTRY_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryNm
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

