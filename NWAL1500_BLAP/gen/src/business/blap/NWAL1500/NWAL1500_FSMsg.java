//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231110100848000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1500_FSMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWAL1500 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1500_FSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RQST_TP_CD_FS*/
	public final EZDSStringItem              xxRqstTpCd_FS;

    /** DS_CPO_SLS_CR_PK_FS*/
	public final EZDSBigDecimalItem              dsCpoSlsCrPk_FS;

    /** DS_ORD_POSN_NUM_FS*/
	public final EZDSStringItem              dsOrdPosnNum_FS;

    /** DS_CPO_CONFIG_PK_FS*/
	public final EZDSBigDecimalItem              dsCpoConfigPk_FS;

    /** SLS_REP_TOC_CD_FS*/
	public final EZDSStringItem              slsRepTocCd_FS;

    /** SLS_REP_ROLE_TP_CD_FS*/
	public final EZDSStringItem              slsRepRoleTpCd_FS;

    /** SLS_REP_CR_PCT_FS*/
	public final EZDSBigDecimalItem              slsRepCrPct_FS;

    /** SLS_CR_QUOT_FLG_FS*/
	public final EZDSStringItem              slsCrQuotFlg_FS;

    /** LINE_BIZ_ROLE_TP_CD_FS*/
	public final EZDSStringItem              lineBizRoleTpCd_FS;


	/**
	 * NWAL1500_FSMsg is constructor.
	 * The initialization when the instance of NWAL1500_FSMsg is generated.
	 */
	public NWAL1500_FSMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1500_FSMsg is constructor.
	 * The initialization when the instance of NWAL1500_FSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1500_FSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRqstTpCd_FS = (EZDSStringItem)newItem("xxRqstTpCd_FS");
		dsCpoSlsCrPk_FS = (EZDSBigDecimalItem)newItem("dsCpoSlsCrPk_FS");
		dsOrdPosnNum_FS = (EZDSStringItem)newItem("dsOrdPosnNum_FS");
		dsCpoConfigPk_FS = (EZDSBigDecimalItem)newItem("dsCpoConfigPk_FS");
		slsRepTocCd_FS = (EZDSStringItem)newItem("slsRepTocCd_FS");
		slsRepRoleTpCd_FS = (EZDSStringItem)newItem("slsRepRoleTpCd_FS");
		slsRepCrPct_FS = (EZDSBigDecimalItem)newItem("slsRepCrPct_FS");
		slsCrQuotFlg_FS = (EZDSStringItem)newItem("slsCrQuotFlg_FS");
		lineBizRoleTpCd_FS = (EZDSStringItem)newItem("lineBizRoleTpCd_FS");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1500_FSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1500_FSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRqstTpCd_FS", "xxRqstTpCd_FS", "FS", null, TYPE_HANKAKUEISU, "1", null},
	{"dsCpoSlsCrPk_FS", "dsCpoSlsCrPk_FS", "FS", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsOrdPosnNum_FS", "dsOrdPosnNum_FS", "FS", null, TYPE_HANKAKUEISU, "6", null},
	{"dsCpoConfigPk_FS", "dsCpoConfigPk_FS", "FS", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"slsRepTocCd_FS", "slsRepTocCd_FS", "FS", null, TYPE_HANKAKUEISU, "8", null},
	{"slsRepRoleTpCd_FS", "slsRepRoleTpCd_FS", "FS", null, TYPE_HANKAKUEISU, "10", null},
	{"slsRepCrPct_FS", "slsRepCrPct_FS", "FS", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"slsCrQuotFlg_FS", "slsCrQuotFlg_FS", "FS", null, TYPE_HANKAKUEISU, "1", null},
	{"lineBizRoleTpCd_FS", "lineBizRoleTpCd_FS", "FS", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RQST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTpCd_FS
        {"DS_CPO_SLS_CR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoSlsCrPk_FS
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_FS
        {"DS_CPO_CONFIG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoConfigPk_FS
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd_FS
        {"SLS_REP_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepRoleTpCd_FS
        {"SLS_REP_CR_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepCrPct_FS
        {"SLS_CR_QUOT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCrQuotFlg_FS
        {"LINE_BIZ_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizRoleTpCd_FS
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
