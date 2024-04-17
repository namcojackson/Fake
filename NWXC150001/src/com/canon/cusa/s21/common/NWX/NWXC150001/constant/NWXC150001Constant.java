/**
 * 
 */
package com.canon.cusa.s21.common.NWX.NWXC150001.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class NWXC150001Constant {

    /** RMA_SVC_EXCH_MAP */
    public static final Map<String, String> RMA_SVC_EXCH_MAP;
    static {
        HashMap<String, String> map = new HashMap<String, String>();
        //                map.put(DS_ORD_CATG.PURCHASE_CSA, DS_ORD_TP.CASH_EXCHANGE);
        //                map.put(DS_ORD_CATG.LEASE_CSA, DS_ORD_TP.LEASE_EXCHANGE);
        //                map.put(DS_ORD_CATG.RENTAL_CSA, DS_ORD_TP.RENTAL_EXCHANGE);
        //                map.put(DS_ORD_CATG.LOAN_CSA, DS_ORD_TP.LOAN_EXCHANGE);
        //                map.put(DS_ORD_CATG.EMSD_CSA, DS_ORD_TP.EMSD_EXCHANGE);
        //                map.put(DS_ORD_CATG.CUSA_RETAIL, DS_ORD_TP.CUSA_RETAIL_EXCHANGE);
        map.put("", "");
        RMA_SVC_EXCH_MAP = Collections.unmodifiableMap(map);
    }

    /** EAZY_PACK1 */
    public static final String EAZY_PACK1 = "EAZY_PACK1";

    /** RETAIL_EQUIP_ORDER */
    public static final String RETAIL_EQUIP_ORDER = "RETAIL_EQUIP_ORDER";

    /** MAIN_MACH */
    public static final String MAIN_MACH = "MAIN_MACH";

    /** FORCE_DUMMY_WH */
    public static final String FORCE_DUMMY_WH = "FORCE_DUMMY_WH";

    /** SERVICE_EXCHANGE */
    public static final String SERVICE_EXCHANGE = "SERVICE_EXCHANGE";

    /** SERVICE_EXCHANGE_ELIG */
    public static final String SERVICE_EXCHANGE_ELIG = "SERVICE_EXCHANGE_ELIG";

    /** ROWNUM_1 = 1 */
    public static final String ROWNUM_1 = "1";

    /** Varchar Const Name: NWA_LEASE_AVAL_CSA_RTRN_RSN */
    public static final String CONST_NM_NWA_LEASE_AVAL_CSA_RTRN_RSN = "NWA_LEASE_AVAL_CSA_RTRN_RSN";
}
