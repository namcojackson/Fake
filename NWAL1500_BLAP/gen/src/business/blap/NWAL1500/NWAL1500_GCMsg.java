//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231110100844000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1500_GCMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NWAL1500_GCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RQST_TP_CD_GS*/
	public final EZDCStringItem              xxRqstTpCd_GS;

    /** DS_CPO_SLS_CR_PK_GS*/
	public final EZDCBigDecimalItem              dsCpoSlsCrPk_GS;

    /** DS_ORD_POSN_NUM_GS*/
	public final EZDCStringItem              dsOrdPosnNum_GS;

    /** DS_CPO_CONFIG_PK_GS*/
	public final EZDCBigDecimalItem              dsCpoConfigPk_GS;

    /** SLS_REP_TOC_CD_GS*/
	public final EZDCStringItem              slsRepTocCd_GS;

    /** SLS_REP_ROLE_TP_CD_GS*/
	public final EZDCStringItem              slsRepRoleTpCd_GS;

    /** SLS_REP_CR_PCT_GS*/
	public final EZDCBigDecimalItem              slsRepCrPct_GS;

    /** SLS_CR_QUOT_FLG_GS*/
	public final EZDCStringItem              slsCrQuotFlg_GS;

    /** LINE_BIZ_ROLE_TP_CD_GS*/
	public final EZDCStringItem              lineBizRoleTpCd_GS;


	/**
	 * NWAL1500_GCMsg is constructor.
	 * The initialization when the instance of NWAL1500_GCMsg is generated.
	 */
	public NWAL1500_GCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1500_GCMsg is constructor.
	 * The initialization when the instance of NWAL1500_GCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1500_GCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRqstTpCd_GS = (EZDCStringItem)newItem("xxRqstTpCd_GS");
		dsCpoSlsCrPk_GS = (EZDCBigDecimalItem)newItem("dsCpoSlsCrPk_GS");
		dsOrdPosnNum_GS = (EZDCStringItem)newItem("dsOrdPosnNum_GS");
		dsCpoConfigPk_GS = (EZDCBigDecimalItem)newItem("dsCpoConfigPk_GS");
		slsRepTocCd_GS = (EZDCStringItem)newItem("slsRepTocCd_GS");
		slsRepRoleTpCd_GS = (EZDCStringItem)newItem("slsRepRoleTpCd_GS");
		slsRepCrPct_GS = (EZDCBigDecimalItem)newItem("slsRepCrPct_GS");
		slsCrQuotFlg_GS = (EZDCStringItem)newItem("slsCrQuotFlg_GS");
		lineBizRoleTpCd_GS = (EZDCStringItem)newItem("lineBizRoleTpCd_GS");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1500_GCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1500_GCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRqstTpCd_GS", "xxRqstTpCd_GS", "GS", null, TYPE_HANKAKUEISU, "1", null},
	{"dsCpoSlsCrPk_GS", "dsCpoSlsCrPk_GS", "GS", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsOrdPosnNum_GS", "dsOrdPosnNum_GS", "GS", null, TYPE_HANKAKUEISU, "6", null},
	{"dsCpoConfigPk_GS", "dsCpoConfigPk_GS", "GS", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"slsRepTocCd_GS", "slsRepTocCd_GS", "GS", null, TYPE_HANKAKUEISU, "8", null},
	{"slsRepRoleTpCd_GS", "slsRepRoleTpCd_GS", "GS", null, TYPE_HANKAKUEISU, "10", null},
	{"slsRepCrPct_GS", "slsRepCrPct_GS", "GS", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"slsCrQuotFlg_GS", "slsCrQuotFlg_GS", "GS", null, TYPE_HANKAKUEISU, "1", null},
	{"lineBizRoleTpCd_GS", "lineBizRoleTpCd_GS", "GS", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RQST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTpCd_GS
        {"DS_CPO_SLS_CR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoSlsCrPk_GS
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_GS
        {"DS_CPO_CONFIG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoConfigPk_GS
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd_GS
        {"SLS_REP_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepRoleTpCd_GS
        {"SLS_REP_CR_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepCrPct_GS
        {"SLS_CR_QUOT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCrQuotFlg_GS
        {"LINE_BIZ_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizRoleTpCd_GS
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
