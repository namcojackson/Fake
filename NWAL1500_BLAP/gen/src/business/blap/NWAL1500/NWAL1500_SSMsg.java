//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231110100848000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1500_SSMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NWAL1500_SSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SHPG_PLN_NUM_SP*/
	public final EZDSStringItem              shpgPlnNum_SP;

    /** _EZUpdateDateTime_SP*/
	public final EZDSStringItem              ezUpTime_SP;

    /** _EZUpTimeZone_SP*/
	public final EZDSStringItem              ezUpTimeZone_SP;


	/**
	 * NWAL1500_SSMsg is constructor.
	 * The initialization when the instance of NWAL1500_SSMsg is generated.
	 */
	public NWAL1500_SSMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1500_SSMsg is constructor.
	 * The initialization when the instance of NWAL1500_SSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1500_SSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		shpgPlnNum_SP = (EZDSStringItem)newItem("shpgPlnNum_SP");
		ezUpTime_SP = (EZDSStringItem)newItem("ezUpTime_SP");
		ezUpTimeZone_SP = (EZDSStringItem)newItem("ezUpTimeZone_SP");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1500_SSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1500_SSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"shpgPlnNum_SP", "shpgPlnNum_SP", "SP", null, TYPE_HANKAKUEISU, "8", null},
	{"ezUpTime_SP", "ezUpTime_SP", "SP", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_SP", "ezUpTimeZone_SP", "SP", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SHPG_PLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgPlnNum_SP
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_SP
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_SP
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
