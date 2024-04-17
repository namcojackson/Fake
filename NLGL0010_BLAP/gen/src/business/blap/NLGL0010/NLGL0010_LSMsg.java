//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170703164352000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0010_LSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLGL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLGL0010 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0010_LSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WMS_SHIP_TO_NM_L1*/
	public final EZDSStringItem              wmsShipToNm_L1;

    /** WMS_SHIP_TO_NM_L2*/
	public final EZDSStringItem              wmsShipToNm_L2;

    /** WMS_SHIP_TO_CTAC_NM_L1*/
	public final EZDSStringItem              wmsShipToCtacNm_L1;

    /** FILL_256_TXT_L1*/
	public final EZDSStringItem              fill256Txt_L1;

    /** CTY_ADDR_L1*/
	public final EZDSStringItem              ctyAddr_L1;

    /** ST_CD_L1*/
	public final EZDSStringItem              stCd_L1;

    /** POST_CD_L1*/
	public final EZDSStringItem              postCd_L1;

    /** SHIP_TO_CTAC_NUM_L1*/
	public final EZDSStringItem              shipToCtacNum_L1;


	/**
	 * NLGL0010_LSMsg is constructor.
	 * The initialization when the instance of NLGL0010_LSMsg is generated.
	 */
	public NLGL0010_LSMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0010_LSMsg is constructor.
	 * The initialization when the instance of NLGL0010_LSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0010_LSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wmsShipToNm_L1 = (EZDSStringItem)newItem("wmsShipToNm_L1");
		wmsShipToNm_L2 = (EZDSStringItem)newItem("wmsShipToNm_L2");
		wmsShipToCtacNm_L1 = (EZDSStringItem)newItem("wmsShipToCtacNm_L1");
		fill256Txt_L1 = (EZDSStringItem)newItem("fill256Txt_L1");
		ctyAddr_L1 = (EZDSStringItem)newItem("ctyAddr_L1");
		stCd_L1 = (EZDSStringItem)newItem("stCd_L1");
		postCd_L1 = (EZDSStringItem)newItem("postCd_L1");
		shipToCtacNum_L1 = (EZDSStringItem)newItem("shipToCtacNum_L1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0010_LSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0010_LSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wmsShipToNm_L1", "wmsShipToNm_L1", "L1", null, TYPE_HANKAKUEISU, "35", null},
	{"wmsShipToNm_L2", "wmsShipToNm_L2", "L2", null, TYPE_HANKAKUEISU, "35", null},
	{"wmsShipToCtacNm_L1", "wmsShipToCtacNm_L1", "L1", null, TYPE_HANKAKUEISU, "25", null},
	{"fill256Txt_L1", "fill256Txt_L1", "L1", null, TYPE_HANKAKUEISU, "256", null},
	{"ctyAddr_L1", "ctyAddr_L1", "L1", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_L1", "stCd_L1", "L1", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_L1", "postCd_L1", "L1", null, TYPE_HANKAKUEISU, "15", null},
	{"shipToCtacNum_L1", "shipToCtacNum_L1", "L1", null, TYPE_HANKAKUEISU, "15", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WMS_SHIP_TO_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsShipToNm_L1
        {"WMS_SHIP_TO_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsShipToNm_L2
        {"WMS_SHIP_TO_CTAC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsShipToCtacNm_L1
        {"FILL_256_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fill256Txt_L1
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_L1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_L1
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_L1
        {"SHIP_TO_CTAC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCtacNum_L1
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
