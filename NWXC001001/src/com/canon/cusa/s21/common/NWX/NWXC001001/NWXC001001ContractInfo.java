/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/23/2009   Fujitsu         T.Tanaka        Create          N/A
 * 03/22/2010   Fujitsu         M.Nakamura      Update          3399,3450
 * 08/06/2010   Fujitsu         A.Suda          Update  		212(All2)
 * 08/24/2010   Fujitsu         A.Suda          Update  		308(All2)
 * 09/03/2010   Fujitsu         A.Suda          Update  		412(All2) 
 * 09/24/2010   Fujitsu         S.Yamamoto      Update          412(All2)
 * 10/01/2010   Fujitsu         K.Tajima        Update          Performance tuning
 * 10/12/2010	Fujitsu			S.Takami		Update			534(All2)
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
//import business.db.CUST_EX_TO_ORD_RSTTMsg;
//import business.db.HLD_RSN_FOR_EXTMsg;
import business.db.MDL_ITEMTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDSETMsg;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC001001ContractInfo {

    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NWXC001001ContractInfo.class);

    private static final String VAR_CHAR_CONST_NM_PROD_CD = "AUTH_BY_MACHINE_ZEROTH_PROD";
    
    private static final String MDL_TP_CD_ACCESSORY = "2";
    private static final String MDL_TP_CD_SUPPLY    = "3";
    
    private static final String PROD_CTRL_MDSE_CD_MDL_LVL = "9999999999999999";
    
    private static final BigDecimal MDL_ID_ITEM_LVL = new BigDecimal("9999999999999999999999");
    
    private static final Integer ASG_PROD_CTRL_LAYER_NUM_MDL_LVL = Integer.valueOf(80);

    /**
     * chkContractInfo
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param shipToCustCd String
     * @param sellToCustCd String
     * @param billToCustCd String
     * @param hldRsnCd String
     * @param payerCustCd String
     * @return String
     */
    public static boolean chkContractInfo(String glblCmpyCd, String mdseCd, String shipToCustCd, String sellToCustCd, String billToCustCd, String hldRsnCd, String payerCustCd) {
        return chkContractInfo(glblCmpyCd, mdseCd, shipToCustCd, sellToCustCd, billToCustCd, hldRsnCd, payerCustCd, null);
    }

    /**
     * chkContractInfo
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param shipToCustCd String
     * @param sellToCustCd String
     * @param billToCustCd String
     * @param hldRsnCd String
     * @param payerCustCd String
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static boolean chkContractInfo(String glblCmpyCd, String mdseCd, String shipToCustCd, String sellToCustCd, String billToCustCd, String hldRsnCd, String payerCustCd, String slsDt) {
//
//        boolean result = false;
//
//        if (!hasValue(glblCmpyCd) || !hasValue(hldRsnCd)) {
//            return result;
//        }
//
//        // 1.Search CUST_EX_TO_ORD_RST at Item Level
//        // 1-1.not search MDSE
//        if (!hasValue(mdseCd)) {
//
//            if (chkContractInfoByTblAccessor(glblCmpyCd, payerCustCd, hldRsnCd)) {
//                result = true;
//            }
//
//        // 1-2.search MDSE
//        } else {
//
//            // MDSE
//            final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
//            if (mdseTMsg == null) {
//                return result;
//            }
//
//            // 2.Get SCD_HLD_RSN_FOR_EX_CD
//            final String scdHldRsnForExCd = getScdHldRsnForExCd(glblCmpyCd, hldRsnCd);
//            
//            boolean isExist = findCustExToOrdRstQtyItemLvl(glblCmpyCd, mdseTMsg, shipToCustCd, sellToCustCd, billToCustCd, hldRsnCd, scdHldRsnForExCd);
//            if (isExist) {
//                result = true;
//                
//            } else {
//                
//
//                // 3.serch model ID
//                final BigDecimal mdlId = getMdlId(glblCmpyCd, mdseCd);
//                
//                if (mdlId != null) {
//
//                    List<BigDecimal> mdlIdList = new ArrayList<BigDecimal>();
//                    mdlIdList.add(mdlId);
//
//                    // 4.Product Line Group
//                    final String zerothProdCtrlCd = mdseTMsg.zerothProdCtrlCd.getValue();
//
//                    boolean isSrchFlg = false;
//
//                    // --------------------------------------------------
//                    // CIG or SOHO
//                    // --------------------------------------------------
//                    if (getAuthByMachineProdCdList(glblCmpyCd, VAR_CHAR_CONST_NM_PROD_CD).contains(zerothProdCtrlCd)) {
//
//                        // 4-(1)
//                        // MDL_TP_CD
//                        final String mdlTpCd = getMdlTpCd(glblCmpyCd, mdlId);
//                        if (!hasValue(mdlTpCd)) {
//                            return result;
//                        }
//
//                        // 4-(2)
//                        if (asList(MDL_TP_CD_ACCESSORY, MDL_TP_CD_SUPPLY).contains(mdlTpCd)) {
//                            
//                            mdlIdList = getMachMdlIdList(glblCmpyCd, mdlId);
//                            if (isEmpty(mdlIdList)) {
//                                return result;
//                            }
//                            
//                            isSrchFlg = true;
//                        }
//                    }
//
//                    // 5.Search CUST_EX_TO_ORD_RST at Model Level
//                    isExist = findCustExToOrdRstQtyMdlLvl(glblCmpyCd, mdseTMsg, shipToCustCd, sellToCustCd, billToCustCd, hldRsnCd, scdHldRsnForExCd, mdlIdList);
//                    if (isExist) {
//                        result = true;
//
//                    } else {
//
//                        // 09/03/2010 Defect 412 --- start add ---
//                        // 6.Search EPO_MDL_ID_PROS
//                        // check only CONTRACT_RESTRICTION , SHIPPING_CONTRACT_RESTRICTION
//                        if (asList(HLD_RSN.CONTRACT_RESTRICTION, HLD_RSN.SHIPPING_CONTRACT_RESTRICTION).contains(hldRsnCd)) {
//
//                            if (hasValue(slsDt)) {
//                                
//                                // --------------------------------------------------
//                                // ISG
//                                // --------------------------------------------------
//                                if (ZerothProdCtrlCd.ISG.getCd().equals(zerothProdCtrlCd)) {
//
//                                    if (!isSrchFlg) {
//
//                                        // MDL_TP_CD
//                                        final String mdlTpCd = getMdlTpCd(glblCmpyCd, mdlId);
//                                        if (!hasValue(mdlTpCd)) {
//                                            return result;
//                                        }
//
//                                        if (asList(MDL_TP_CD_ACCESSORY, MDL_TP_CD_SUPPLY).contains(mdlTpCd)) {
//                                            mdlIdList = getMachMdlIdList(glblCmpyCd, mdlId);
//                                        }
//                                    }
//
//                                    mdlIdList.add(mdlId);
//                                    
//                                    isExist = findEpoMdlIdPros(glblCmpyCd, mdlIdList, shipToCustCd, sellToCustCd, billToCustCd, slsDt);
//                                    if (isExist) {
//                                    	// 10/12/2010 534(All2) added condition
//                                    	// is the marchandise target of TOP STOP? 
//                                    	if (NWXC001001TopStopMdseChk.isTopStopMdse(glblCmpyCd, mdseCd, slsDt)) {
//                                    		result = true;
//                                    	}// 10/12/2010 534(All2) added condition
//                                    }
//                                }
//                            }
//                        }
//                        // 09/03/2010 Defect 412 --- start end ---
//                    }
//                }
//            }
//        }

        return true;
    }

    public static boolean chkContractInfoBySellTo(String glblCmpyCd, String sellToCustCd, String billToCustCd, String hldRsnCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",   glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("sellToCustCd", sellToCustCd);
        ssmParam.put("hldRsnCd",     hldRsnCd);
        
        return (Integer) ssmClient.queryObject("cntContractInfoBySellTo", ssmParam) > 0;
    }
//
//    private static boolean chkContractInfoByTblAccessor(String glblCmpyCd, String payerCustCd, String hldRsnCd) {
//        
//        final CUST_EX_TO_ORD_RSTTMsg custExToOrdRstTMsg = new CUST_EX_TO_ORD_RSTTMsg();
//        custExToOrdRstTMsg.setSQLID("003");
//        custExToOrdRstTMsg.setConditionValue("glblCmpyCd01",    glblCmpyCd);
//        custExToOrdRstTMsg.setConditionValue("custCd01",        payerCustCd);
//        custExToOrdRstTMsg.setConditionValue("hldRsnForExCd01", hldRsnCd);
//        
//        return S21ApiTBLAccessor.count(custExToOrdRstTMsg) != 0;
//    }

    private static final EZDTMsg findByKeyWithCache(EZDTMsg tMsg) {
        
        return S21CacheTBLAccessor.findByKey(tMsg);
    }

    private static boolean findCustExToOrdRstQtyItemLvl(String glblCmpyCd, MDSETMsg mdseTMsg, String shipToCustCd, String sellToCustCd, String billToCustCd, String hldRsnCd, String scdHldRsnForExCd) {
        
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",       glblCmpyCd);
        ssmParam.put("mdseCd",           mdseTMsg.mdseCd.getValue());
        ssmParam.put("zerothProdCtrlCd", mdseTMsg.zerothProdCtrlCd.getValue());
        ssmParam.put("firstProdCtrlCd",  mdseTMsg.firstProdCtrlCd.getValue());
        ssmParam.put("scdProdCtrlCd",    mdseTMsg.scdProdCtrlCd.getValue());
        ssmParam.put("thirdProdCtrlCd",  mdseTMsg.thirdProdCtrlCd.getValue());
        ssmParam.put("frthProdCtrlCd",   mdseTMsg.frthProdCtrlCd.getValue());
        ssmParam.put("fifthProdCtrlCd",  mdseTMsg.fifthProdCtrlCd.getValue());
        ssmParam.put("sixthProdCtrlCd",  mdseTMsg.sixthProdCtrlCd.getValue());
        ssmParam.put("svnthProdCtrlCd",  mdseTMsg.svnthProdCtrlCd.getValue());
        ssmParam.put("shipToCustCd",     shipToCustCd);
        ssmParam.put("sellToCustCd",     sellToCustCd);
        ssmParam.put("billToCustCd",     billToCustCd);
        ssmParam.put("hldRsnCd",         hldRsnCd);
        ssmParam.put("mdlId",            MDL_ID_ITEM_LVL);

        // In case scdHldRsnCd is null or "" or different hldRsnCd
        if (hasValue(scdHldRsnForExCd) && !hldRsnCd.equals(scdHldRsnForExCd)) {
            ssmParam.put("OR0",         "true");
            ssmParam.put("scdHldRsnCd", scdHldRsnForExCd);
        }

        return (Integer) ssmClient.queryObject("cntContractInfoItemLvl", ssmParam) > 0;
    }

    private static boolean findCustExToOrdRstQtyMdlLvl(String glblCmpyCd, MDSETMsg mdseMsg, String shipToCustCd, String sellToCustCd, String billToCustCd, String hldRsnCd, String scdHldRsnCd, List mdlIDList) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",          glblCmpyCd);
        ssmParam.put("mdseCd",              mdseMsg.mdseCd.getValue());
        ssmParam.put("shipToCustCd",        shipToCustCd);
        ssmParam.put("sellToCustCd",        sellToCustCd);
        ssmParam.put("billToCustCd",        billToCustCd);
        ssmParam.put("hldRsnCd",            hldRsnCd);
        ssmParam.put("prodCtrlMdseCd",      PROD_CTRL_MDSE_CD_MDL_LVL);
        ssmParam.put("asgProdCtrlLayerNum", ASG_PROD_CTRL_LAYER_NUM_MDL_LVL);

        // In case scdHldRsnCd is null or "" or different hldRsnCd
        if (hasValue(scdHldRsnCd) && !hldRsnCd.equals(scdHldRsnCd)) {
            ssmParam.put("OR0",         "true");
            ssmParam.put("scdHldRsnCd", scdHldRsnCd);
        }

        if (!isEmpty(mdlIDList)) {
            ssmParam.put("AND0",      "true");
            ssmParam.put("mdlIDList", mdlIDList);
        }

        return (Integer) ssmClient.queryObject("cntContractInfoMdlLvl", ssmParam) > 0;
    }

    private static boolean findEpoMdlIdPros(String glblCmpyCd, List mdlIDList, String shipToCustCd, String sellToCustCd, String billToCustCd, String slsDt) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",   glblCmpyCd);
        ssmParam.put("mdlIDList",    mdlIDList);
        ssmParam.put("shipToCustCd", shipToCustCd);
        ssmParam.put("sellToCustCd", sellToCustCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("slsDt",        slsDt);

        return (Integer) ssmClient.queryObject("cntContractInfoByPros", ssmParam) > 0;
    }
    
    private static final List<String> getAuthByMachineProdCdList(String glblCmpyCd, String varCharConstNm) {
        
        VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
        setValue(varCharConstTMsg.glblCmpyCd,     glblCmpyCd);
        setValue(varCharConstTMsg.varCharConstNm, varCharConstNm);
        
        varCharConstTMsg = (VAR_CHAR_CONSTTMsg)findByKeyWithCache(varCharConstTMsg);
        
        if (varCharConstTMsg == null) {
            return emptyList();
        } else {
            final String varCharConstVal = varCharConstTMsg.varCharConstVal.getValue();
            if (!hasValue(varCharConstVal)) {
                return emptyList();
            } else {
                return asList(varCharConstVal.split(","));
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private static List<BigDecimal> getMachMdlIdList(String glblCmpyCd, BigDecimal mdlId) {
        
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdlId",      mdlId);
        
        return ssmClient.queryObjectList("getMachMdlIdList", ssmParam);
    }

    private static BigDecimal getMdlId(String glblCmpyCd, String mdseCd) {
        
        // MDL_ITEM
        MDL_ITEMTMsg mdlItemTMsg = new MDL_ITEMTMsg();
        setValue(mdlItemTMsg.t_GlblCmpyCd, glblCmpyCd);
        setValue(mdlItemTMsg.t_ItemCd,     mdseCd);
        
        mdlItemTMsg = (MDL_ITEMTMsg)findByKeyWithCache(mdlItemTMsg);
        
        if (mdlItemTMsg == null) {
            return null;
        } else {
            return mdlItemTMsg.t_MdlId.getValue();
        }
    }
    
    private static String getMdlTpCd(String glblCmpyCd, BigDecimal mdlId) {
        
        // MDL_NM
        MDL_NMTMsg mdlNmTMsg = new MDL_NMTMsg();
        setValue(mdlNmTMsg.t_GlblCmpyCd, glblCmpyCd);
        setValue(mdlNmTMsg.t_MdlId,      mdlId);
        
        mdlNmTMsg = (MDL_NMTMsg)findByKeyWithCache(mdlNmTMsg);
        
        if (mdlNmTMsg == null) {
            return null;
        } else {
            return mdlNmTMsg.t_MdlTpCd.getValue();
        }
    }
// 
//    private static String getScdHldRsnForExCd(String glblCmpyCd, String hldRsnCd) {
//
//        // HLD_RSN_FOR_EX
//        HLD_RSN_FOR_EXTMsg hldRsnForExTMsg = new HLD_RSN_FOR_EXTMsg();
//        setValue(hldRsnForExTMsg.glblCmpyCd,    glblCmpyCd);
//        setValue(hldRsnForExTMsg.hldRsnForExCd, hldRsnCd);
//        
//        hldRsnForExTMsg = (HLD_RSN_FOR_EXTMsg)findByKeyWithCache(hldRsnForExTMsg);
//        
//        if (hldRsnForExTMsg == null) {
//            return "";
//        } else {
//            // SCD_HLD_RSN_FOR_EX_CD
//            return hldRsnForExTMsg.scdHldRsnForExCd.getValue();
//        }
//    }
    
    private static boolean isEmpty(Collection col) {
        return col == null || col.isEmpty();
    }
    
    /**
     * ZEROTH_PROD_CTRL_CD
     */
    private static enum ZerothProdCtrlCd {
        
        ISG("04");
        
        private final String cd;
        
        private ZerothProdCtrlCd(String cd) {
            this.cd = cd;
        }

        private String getCd() {
            return cd;
        }
    }
    
}
