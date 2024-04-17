//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231110100844000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1500_PCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1500 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1500_PCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_LINE_CTX_TP_CD_P*/
	public final EZDCStringItem              ordLineCtxTpCd_P;

    /** DS_ORD_LINE_CATG_CD_P*/
	public final EZDCStringItem              dsOrdLineCatgCd_P;

    /** DS_ORD_LINE_CATG_NM_P*/
	public final EZDCStringItem              dsOrdLineCatgNm_P;

    /** DS_ORD_LINE_CATG_DESC_TXT_P*/
	public final EZDCStringItem              dsOrdLineCatgDescTxt_P;

    /** RTL_WH_CD_P*/
	public final EZDCStringItem              rtlWhCd_P;

    /** RTL_WH_NM_P*/
	public final EZDCStringItem              rtlWhNm_P;

    /** RTL_WH_DESC_TXT_P*/
	public final EZDCStringItem              rtlWhDescTxt_P;


	/**
	 * NWAL1500_PCMsg is constructor.
	 * The initialization when the instance of NWAL1500_PCMsg is generated.
	 */
	public NWAL1500_PCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1500_PCMsg is constructor.
	 * The initialization when the instance of NWAL1500_PCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1500_PCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordLineCtxTpCd_P = (EZDCStringItem)newItem("ordLineCtxTpCd_P");
		dsOrdLineCatgCd_P = (EZDCStringItem)newItem("dsOrdLineCatgCd_P");
		dsOrdLineCatgNm_P = (EZDCStringItem)newItem("dsOrdLineCatgNm_P");
		dsOrdLineCatgDescTxt_P = (EZDCStringItem)newItem("dsOrdLineCatgDescTxt_P");
		rtlWhCd_P = (EZDCStringItem)newItem("rtlWhCd_P");
		rtlWhNm_P = (EZDCStringItem)newItem("rtlWhNm_P");
		rtlWhDescTxt_P = (EZDCStringItem)newItem("rtlWhDescTxt_P");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1500_PCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1500_PCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordLineCtxTpCd_P", "ordLineCtxTpCd_P", "P", null, TYPE_HANKAKUEISU, "30", null},
	{"dsOrdLineCatgCd_P", "dsOrdLineCatgCd_P", "P", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdLineCatgNm_P", "dsOrdLineCatgNm_P", "P", null, TYPE_HANKAKUEISU, "30", null},
	{"dsOrdLineCatgDescTxt_P", "dsOrdLineCatgDescTxt_P", "P", null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhCd_P", "rtlWhCd_P", "P", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_P", "rtlWhNm_P", "P", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlWhDescTxt_P", "rtlWhDescTxt_P", "P", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_LINE_CTX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineCtxTpCd_P
        {"DS_ORD_LINE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgCd_P
        {"DS_ORD_LINE_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgNm_P
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt_P
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_P
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_P
        {"RTL_WH_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhDescTxt_P
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

    // Fast Save
    private static String[] EXT_DETAILS = new String[0];
    private static String[] EXT_ARRAYS = new String[0];

    private static String[] SER_KEY = new String[0];
    private static Integer[] SER_TYPE = new Integer[0];

    static protected HashMap<String, Integer> convAttrType = new HashMap<String, Integer>();

    static {
        for (int[] mapTable :EZDItemAttrDefines.TYPE_MAP_TABLE) {
            convAttrType.put(String.valueOf(mapTable[0]), mapTable[1]);
        }
    }

    static {
        List<String> listDetail = new ArrayList<String>();
        List<String> listArrays = new ArrayList<String>();

        List<String> listSerKey = new ArrayList<String>();
        List<Integer> listSerType = new ArrayList<Integer>();

        for (String[] contents : BASE_CONTENTS) {
            if (contents[3] != null) {
                try {
                    Integer.parseInt(contents[4]);
                    listArrays.add(contents[0]);
                } catch (NumberFormatException e) {
                    listDetail.add(contents[0]);
                }
            } else {
                String ezdType = contents[4];
                Integer javaTYype = ezdType2JavaType(ezdType);
                listSerKey.add(contents[0]);
                listSerType.add(javaTYype);
            }
        }
        if (!listArrays.isEmpty()) {
            EXT_ARRAYS = (String[]) listArrays.toArray(new String[listArrays.size()]);
        }
        if (!listDetail.isEmpty()) {
            EXT_DETAILS = (String[]) listDetail.toArray(new String[listDetail.size()]);
        }

        if (!listSerKey.isEmpty()) {
            SER_KEY = (String[]) listSerKey.toArray(new String[listSerKey.size()]);
        }
        if (!listSerType.isEmpty()) {
            SER_TYPE = (Integer[]) listSerType.toArray(new Integer[listSerType.size()]);
        }
    }

    protected boolean isFastWriteObject() {
        return true;
    }

    protected String[] getDetails() {
        return EXT_DETAILS;
    }

    protected String[] getArrays() {
        return EXT_ARRAYS;
    }

    protected String[] getSerKey() {
        return SER_KEY;
    }

    protected Integer[] getSerType() {
        return SER_TYPE;
    }

    static protected int ezdType2JavaType(String val) {
    	return convAttrType.get(val);
    }
    // Fast Save
}

