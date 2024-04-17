//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231110100848000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1500_USMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NWAL1500_USMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD_LL*/
	public final EZDSStringItem              mdseCd_LL;

    /** ORD_CUST_UOM_QTY_LL*/
	public final EZDSBigDecimalItem              ordCustUomQty_LL;

    /** CUST_UOM_CD_LL*/
	public final EZDSStringItem              custUomCd_LL;

    /** ENT_CPO_DTL_DEAL_SLS_AMT_LL*/
	public final EZDSBigDecimalItem              entCpoDtlDealSlsAmt_LL;

    /** PRC_CATG_NM_LL*/
	public final EZDSStringItem              prcCatgNm_LL;

    /** RTL_WH_NM_LL*/
	public final EZDSStringItem              rtlWhNm_LL;

    /** RTL_SWH_NM_LL*/
	public final EZDSStringItem              rtlSwhNm_LL;

    /** FUNC_UNIT_FL_PRC_AMT_LL*/
	public final EZDSBigDecimalItem              funcUnitFlPrcAmt_LL;

    /** CPO_DTL_DEAL_GRS_AMT_LL*/
	public final EZDSBigDecimalItem              cpoDtlDealGrsAmt_LL;

    /** DS_ORD_LINE_CATG_DESC_TXT_LL*/
	public final EZDSStringItem              dsOrdLineCatgDescTxt_LL;

    /** CSMP_CONTR_NUM_LL*/
	public final EZDSStringItem              csmpContrNum_LL;

    /** DLR_REF_NUM_LL*/
	public final EZDSStringItem              dlrRefNum_LL;

    /** CSMP_PRC_LIST_CD_LL*/
	public final EZDSStringItem              csmpPrcListCd_LL;

    /** FUNC_SVC_REV_TRNSF_AMT_LL*/
	public final EZDSBigDecimalItem              funcSvcRevTrnsfAmt_LL;

    /** DEAL_SVC_REV_TRNSF_AMT_LL*/
	public final EZDSBigDecimalItem              dealSvcRevTrnsfAmt_LL;

    /** FUNC_SVC_COST_TRNSF_AMT_LL*/
	public final EZDSBigDecimalItem              funcSvcCostTrnsfAmt_LL;

    /** DEAL_SVC_COST_TRNSF_AMT_LL*/
	public final EZDSBigDecimalItem              dealSvcCostTrnsfAmt_LL;

    /** FUNC_MAN_FL_ADJ_AMT_LL*/
	public final EZDSBigDecimalItem              funcManFlAdjAmt_LL;

    /** DEAL_MAN_FL_ADJ_AMT_LL*/
	public final EZDSBigDecimalItem              dealManFlAdjAmt_LL;

    /** FUNC_MAN_REP_REV_ADJ_AMT_LL*/
	public final EZDSBigDecimalItem              funcManRepRevAdjAmt_LL;

    /** DEAL_MAN_REP_REV_ADJ_AMT_LL*/
	public final EZDSBigDecimalItem              dealManRepRevAdjAmt_LL;


	/**
	 * NWAL1500_USMsg is constructor.
	 * The initialization when the instance of NWAL1500_USMsg is generated.
	 */
	public NWAL1500_USMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1500_USMsg is constructor.
	 * The initialization when the instance of NWAL1500_USMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1500_USMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd_LL = (EZDSStringItem)newItem("mdseCd_LL");
		ordCustUomQty_LL = (EZDSBigDecimalItem)newItem("ordCustUomQty_LL");
		custUomCd_LL = (EZDSStringItem)newItem("custUomCd_LL");
		entCpoDtlDealSlsAmt_LL = (EZDSBigDecimalItem)newItem("entCpoDtlDealSlsAmt_LL");
		prcCatgNm_LL = (EZDSStringItem)newItem("prcCatgNm_LL");
		rtlWhNm_LL = (EZDSStringItem)newItem("rtlWhNm_LL");
		rtlSwhNm_LL = (EZDSStringItem)newItem("rtlSwhNm_LL");
		funcUnitFlPrcAmt_LL = (EZDSBigDecimalItem)newItem("funcUnitFlPrcAmt_LL");
		cpoDtlDealGrsAmt_LL = (EZDSBigDecimalItem)newItem("cpoDtlDealGrsAmt_LL");
		dsOrdLineCatgDescTxt_LL = (EZDSStringItem)newItem("dsOrdLineCatgDescTxt_LL");
		csmpContrNum_LL = (EZDSStringItem)newItem("csmpContrNum_LL");
		dlrRefNum_LL = (EZDSStringItem)newItem("dlrRefNum_LL");
		csmpPrcListCd_LL = (EZDSStringItem)newItem("csmpPrcListCd_LL");
		funcSvcRevTrnsfAmt_LL = (EZDSBigDecimalItem)newItem("funcSvcRevTrnsfAmt_LL");
		dealSvcRevTrnsfAmt_LL = (EZDSBigDecimalItem)newItem("dealSvcRevTrnsfAmt_LL");
		funcSvcCostTrnsfAmt_LL = (EZDSBigDecimalItem)newItem("funcSvcCostTrnsfAmt_LL");
		dealSvcCostTrnsfAmt_LL = (EZDSBigDecimalItem)newItem("dealSvcCostTrnsfAmt_LL");
		funcManFlAdjAmt_LL = (EZDSBigDecimalItem)newItem("funcManFlAdjAmt_LL");
		dealManFlAdjAmt_LL = (EZDSBigDecimalItem)newItem("dealManFlAdjAmt_LL");
		funcManRepRevAdjAmt_LL = (EZDSBigDecimalItem)newItem("funcManRepRevAdjAmt_LL");
		dealManRepRevAdjAmt_LL = (EZDSBigDecimalItem)newItem("dealManRepRevAdjAmt_LL");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1500_USMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1500_USMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd_LL", "mdseCd_LL", "LL", null, TYPE_HANKAKUEISU, "16", null},
	{"ordCustUomQty_LL", "ordCustUomQty_LL", "LL", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"custUomCd_LL", "custUomCd_LL", "LL", null, TYPE_HANKAKUEISU, "4", null},
	{"entCpoDtlDealSlsAmt_LL", "entCpoDtlDealSlsAmt_LL", "LL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcCatgNm_LL", "prcCatgNm_LL", "LL", null, TYPE_HANKAKUEISU, "75", null},
	{"rtlWhNm_LL", "rtlWhNm_LL", "LL", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhNm_LL", "rtlSwhNm_LL", "LL", null, TYPE_HANKAKUEISU, "30", null},
	{"funcUnitFlPrcAmt_LL", "funcUnitFlPrcAmt_LL", "LL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cpoDtlDealGrsAmt_LL", "cpoDtlDealGrsAmt_LL", "LL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdLineCatgDescTxt_LL", "dsOrdLineCatgDescTxt_LL", "LL", null, TYPE_HANKAKUEISU, "50", null},
	{"csmpContrNum_LL", "csmpContrNum_LL", "LL", null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum_LL", "dlrRefNum_LL", "LL", null, TYPE_HANKAKUEISU, "20", null},
	{"csmpPrcListCd_LL", "csmpPrcListCd_LL", "LL", null, TYPE_HANKAKUEISU, "10", null},
	{"funcSvcRevTrnsfAmt_LL", "funcSvcRevTrnsfAmt_LL", "LL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealSvcRevTrnsfAmt_LL", "dealSvcRevTrnsfAmt_LL", "LL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcSvcCostTrnsfAmt_LL", "funcSvcCostTrnsfAmt_LL", "LL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealSvcCostTrnsfAmt_LL", "dealSvcCostTrnsfAmt_LL", "LL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcManFlAdjAmt_LL", "funcManFlAdjAmt_LL", "LL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealManFlAdjAmt_LL", "dealManFlAdjAmt_LL", "LL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcManRepRevAdjAmt_LL", "funcManRepRevAdjAmt_LL", "LL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealManRepRevAdjAmt_LL", "dealManRepRevAdjAmt_LL", "LL", null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_LL
        {"ORD_CUST_UOM_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordCustUomQty_LL
        {"CUST_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custUomCd_LL
        {"ENT_CPO_DTL_DEAL_SLS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entCpoDtlDealSlsAmt_LL
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_LL
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_LL
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_LL
        {"FUNC_UNIT_FL_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcUnitFlPrcAmt_LL
        {"CPO_DTL_DEAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlDealGrsAmt_LL
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt_LL
        {"CSMP_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum_LL
        {"DLR_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum_LL
        {"CSMP_PRC_LIST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpPrcListCd_LL
        {"FUNC_SVC_REV_TRNSF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcSvcRevTrnsfAmt_LL
        {"DEAL_SVC_REV_TRNSF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealSvcRevTrnsfAmt_LL
        {"FUNC_SVC_COST_TRNSF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcSvcCostTrnsfAmt_LL
        {"DEAL_SVC_COST_TRNSF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealSvcCostTrnsfAmt_LL
        {"FUNC_MAN_FL_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcManFlAdjAmt_LL
        {"DEAL_MAN_FL_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealManFlAdjAmt_LL
        {"FUNC_MAN_REP_REV_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcManRepRevAdjAmt_LL
        {"DEAL_MAN_REP_REV_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealManRepRevAdjAmt_LL
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
