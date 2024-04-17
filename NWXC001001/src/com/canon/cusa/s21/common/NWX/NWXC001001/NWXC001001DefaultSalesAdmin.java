package com.canon.cusa.s21.common.NWX.NWXC001001;


import java.util.HashMap;
import java.util.Map;
import java.util.List;

import parts.common.EZDDebugOutput;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/09/2009   Fujitsu         K.Kimura        Create          N/A
 * </pre>
 */
public class NWXC001001DefaultSalesAdmin {
    
    public static final Class clazz = NWXC001001DefaultSalesAdmin.class;
    public static final String clazzNm = clazz.getSimpleName();
    public static final S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(clazz);
          
    /**
     * getSalesAdmin
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param sellToCustCd String
     * @param billToCustCd String
     * @return String
     */
    public static String getDfaultSalesAdmin(String glblCmpyCd,String mdseCd,String sellToCustCd,String billToCustCd){
        
        String psnCd = null;
        
        /**
         * Parameter check
         */
        if(!isParameterCheck(glblCmpyCd,mdseCd,sellToCustCd,billToCustCd)){
            
            return psnCd;
        }
        
        /**
         *  [Common Metod]
         *  NWXC001001OrderTakeMdseTBLAccessor.findMdseCd
         */
        mdseCd = NWXC001001OrderTakeMdseTBLAccessor.findMdseCd(glblCmpyCd, mdseCd);
            
        /**
         * Get Product Line Code
         */
//        String zerothProdCtrlCd = getProdCtrlCd(glblCmpyCd,mdseCd);
//        
//        if (zerothProdCtrlCd == null) {
//            
//            return psnCd; 
//        }
        List prodCtrlList = getProdCtrlCd(glblCmpyCd,mdseCd);
        
        if (prodCtrlList.isEmpty()) {
            
            return psnCd; 
        }
        
        /**
         * Get Toc Code
         */
        List tocList = getTocCd(glblCmpyCd,prodCtrlList,sellToCustCd,billToCustCd);
     
        if (tocList.isEmpty()) {
            
            return psnCd; 
        }
        
        String tocCd = tocList.get(0).toString();
        
        /**
         * Get Person Code
         */
        psnCd = getPsnCd(glblCmpyCd,tocCd);
            
        return psnCd; 
        
    }
    
    /**
     * isParameterCheck
     * @param gblCmpyCd String
     * @param mdseCd String
     * @param shipToCustCd String
     * @param sellToCustCd String
     * @param billToCustCd String
     * @return boolean
     */
    private static boolean isParameterCheck(String glblCmpyCd, String mdseCd, String sellToCustCd, String billToCustCd) {
        
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, "[" + clazzNm + "]glblCmpyCd=" + glblCmpyCd, null);
            EZDDebugOutput.println(1, "[" + clazzNm + "]mdseCd=" + mdseCd, null);
            EZDDebugOutput.println(1, "[" + clazzNm + "]sellToCustCd=" + sellToCustCd, null);
            EZDDebugOutput.println(1, "[" + clazzNm + "]billToCustCd=" + billToCustCd, null);
        }
        
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) 
                || !ZYPCommonFunc.hasValue(mdseCd)
                || !ZYPCommonFunc.hasValue(sellToCustCd)
                || !ZYPCommonFunc.hasValue(billToCustCd)){
                
            return false;
        }
        
        return true;
    }
    
//    /**
//     * getProdCtrlCd
//     * @param gblCmpyCd String
//     * @param mdseCd String
//     * @return String
//     */
//    private static String getProdCtrlCd(String glblCmpyCd, String mdseCd) {
//        
//        /**
//         * 「NWXC001001SalesAdmin.xml」id="getProdCtrlCd"
//         */
//        Map<String, String> mdseMap = new HashMap<String, String>();
//        mdseMap.put("glblCmpyCd", glblCmpyCd);
//        mdseMap.put("mdseCd", mdseCd);
//        mdseMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        String zerothProdCtrlCd = (String) ssmBatchClient.queryObject("getProdCtrlCd", mdseMap);
//        
//        return zerothProdCtrlCd;     
//    }
    
    /**
     * getProdCtrlCd
     * @param gblCmpyCd String
     * @param mdseCd String
     * @return List prodCtrlList
     */
    private static List getProdCtrlCd(String glblCmpyCd, String mdseCd) {
        
        /**
         * 「NWXC001001SalesAdmin.xml」id="getProdCtrlCd"
         */
        mdseCd = NWXC001001OrderTakeMdseTBLAccessor.findMdseCd(glblCmpyCd, mdseCd);

        Map<String, String> mdseMap = new HashMap<String, String>();
        mdseMap.put("glblCmpyCd", glblCmpyCd);
        mdseMap.put("mdseCd", mdseCd);
        mdseMap.put("stsReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        mdseMap.put("stsTerminated", RGTN_STS.TERMINATED);
        
        List prodCtrlList = ssmBatchClient.queryObjectList("getMdseProdCd", mdseMap);
        
        return prodCtrlList;     
    }
    
//    /**
//     * getTocCd
//     * @param gblCmpyCd String
//     * @param zerothProdCtrlCd String
//     * @param sellToCustCd String
//     * @param billToCustCd String
//     * @return List
//     */
//    private static List getTocCd(String glblCmpyCd, String zerothProdCtrlCd, String sellToCustCd, String billToCustCd) {
//    
//        /**
//         * 「NWXC001001SalesAdmin.xml」id="getTocCd"
//         */
//        Map<String, String> tocMap = new HashMap<String, String>();
//        tocMap.put("glblCmpyCd", glblCmpyCd);
//        tocMap.put("zerothProdCtrlCd", zerothProdCtrlCd);
//        tocMap.put("sellToCustCd", sellToCustCd);
//        tocMap.put("billToCustCd", billToCustCd);
//        
//        List tocList = ssmBatchClient.queryObjectList("getTocCd", tocMap);
//        
//        return tocList;     
//    }

    /**
     * getTocCd
     * @param gblCmpyCd String
     * @param zerothProdCtrlCd String
     * @param sellToCustCd String
     * @param billToCustCd String
     * @return List
     */
    private static List getTocCd(String glblCmpyCd, List prodCtrlList, String sellToCustCd, String billToCustCd) {

        /**
         * 「NWXC001001SalesAdmin.xml」id="getTocCd"
         */
        Map<String, String> tocMap = new HashMap<String, String>();
        tocMap.put("glblCmpyCd", glblCmpyCd);
        tocMap.put("firstProdCtrlCd", nvl((String) ((Map) prodCtrlList.get(0)).get("FIRST_PROD_CTRL_CD")));
        tocMap.put("scdProdCtrlCd", nvl((String) ((Map) prodCtrlList.get(0)).get("SCD_PROD_CTRL_CD")));
        tocMap.put("sellToCustCd", sellToCustCd);
        tocMap.put("billToCustCd", billToCustCd);

        List tocList = ssmBatchClient.queryObjectList("getTocCd", tocMap);

        return tocList;     
    }
    
    /**
     * getPsnCd
     * @param gblCmpyCd String
     * @param tocCd String
     * @return String
     */
    private static String getPsnCd(String glblCmpyCd, String tocCd) {
        
        /**
         * 「NWXC001001SalesAdmin.xml」id="getPsnCd"
         */
        Map<String, String> psnMap = new HashMap<String, String>();
        psnMap.put("glblCmpyCd", glblCmpyCd);
        psnMap.put("tocCd", tocCd);
        psnMap.put("gnrncd", GNRN_TP.CURRENT);
        
        String psnCd = (String) ssmBatchClient.queryObject("getPsnCd", psnMap);
        
        return psnCd; 
    }

    /**
     * nvl
     * @param str String
     * @return String
     */
    private static String nvl(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
    
}
