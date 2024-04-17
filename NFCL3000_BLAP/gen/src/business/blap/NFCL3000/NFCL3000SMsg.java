//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190808083122000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3000SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3000;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3000 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3000SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** XX_SORT_TBL_NM*/
	public final EZDSStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDSStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDSStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.blap.NFCL3000.NFCL3000_ASMsgArray              A;

    /** B*/
	public final business.blap.NFCL3000.NFCL3000_BSMsgArray              B;

    /** C*/
	public final business.blap.NFCL3000.NFCL3000_CSMsgArray              C;

    /** COA_BR_CD_C1*/
	public final EZDSStringItem              coaBrCd_C1;

    /** SHIP_TO_ST_CD_C1*/
	public final EZDSStringItem              shipToStCd_C1;

    /** SHIP_TO_CTRY_CD_C1*/
	public final EZDSStringItem              shipToCtryCd_C1;

    /** D*/
	public final business.blap.NFCL3000.NFCL3000_DSMsgArray              D;

    /** E*/
	public final business.blap.NFCL3000.NFCL3000_ESMsgArray              E;

    /** F*/
	public final business.blap.NFCL3000.NFCL3000_FSMsgArray              F;

    /** G*/
	public final business.blap.NFCL3000.NFCL3000_GSMsgArray              G;


	/**
	 * NFCL3000SMsg is constructor.
	 * The initialization when the instance of NFCL3000SMsg is generated.
	 */
	public NFCL3000SMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3000SMsg is constructor.
	 * The initialization when the instance of NFCL3000SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3000SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		xxSortTblNm = (EZDSStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDSStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDSStringItem)newItem("xxSortOrdByTxt");
		A = (business.blap.NFCL3000.NFCL3000_ASMsgArray)newMsgArray("A");
		B = (business.blap.NFCL3000.NFCL3000_BSMsgArray)newMsgArray("B");
		C = (business.blap.NFCL3000.NFCL3000_CSMsgArray)newMsgArray("C");
		coaBrCd_C1 = (EZDSStringItem)newItem("coaBrCd_C1");
		shipToStCd_C1 = (EZDSStringItem)newItem("shipToStCd_C1");
		shipToCtryCd_C1 = (EZDSStringItem)newItem("shipToCtryCd_C1");
		D = (business.blap.NFCL3000.NFCL3000_DSMsgArray)newMsgArray("D");
		E = (business.blap.NFCL3000.NFCL3000_ESMsgArray)newMsgArray("E");
		F = (business.blap.NFCL3000.NFCL3000_FSMsgArray)newMsgArray("F");
		G = (business.blap.NFCL3000.NFCL3000_GSMsgArray)newMsgArray("G");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3000SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3000SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "999", "business.blap.NFCL3000.NFCL3000_ASMsgArray", null, null},
	
	{"B", "B", null, "999", "business.blap.NFCL3000.NFCL3000_BSMsgArray", null, null},
	
	{"C", "C", null, "2200", "business.blap.NFCL3000.NFCL3000_CSMsgArray", null, null},
	
	{"coaBrCd_C1", "coaBrCd_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"shipToStCd_C1", "shipToStCd_C1", "C1", null, TYPE_HANKAKUEISU, "2", null},
	{"shipToCtryCd_C1", "shipToCtryCd_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"D", "D", null, "200", "business.blap.NFCL3000.NFCL3000_DSMsgArray", null, null},
	
	{"E", "E", null, "1000", "business.blap.NFCL3000.NFCL3000_ESMsgArray", null, null},
	
	{"F", "F", null, "2200", "business.blap.NFCL3000.NFCL3000_FSMsgArray", null, null},
	
	{"G", "G", null, "200", "business.blap.NFCL3000.NFCL3000_GSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
		null,	//A
		null,	//B
		null,	//C
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_C1
        {"SHIP_TO_ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToStCd_C1
        {"SHIP_TO_CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCtryCd_C1
		null,	//D
		null,	//E
		null,	//F
		null,	//G
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

