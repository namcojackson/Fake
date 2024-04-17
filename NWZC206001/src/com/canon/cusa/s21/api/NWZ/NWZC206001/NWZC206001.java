/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC206001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import business.db.MDSETMsg;
import business.db.SUPDTMsg;
import business.db.SUPD_RELNTMsg;
import business.db.SUPD_RELNTMsgArray;
import business.parts.NWZC206001PMsg;
import business.parts.NWZC206001_APMsg;

import com.canon.cusa.s21.api.NWZ.NWZC206001.constant.NWZC206001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 * NWZC206001 Supersede API
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 07/24/2013   Fujitsu         D.Yanagisawa    Create          R-MS013-002
 * 8/6/2013     Fujitsu         N.Sakai         Update          Defect#1498 
 *</pre>
 */
public class NWZC206001 extends S21ApiCommonBase implements NWZC206001Constant {

    /**
     * NWZC206001Query
     */
    private NWZC206001Query query = new NWZC206001Query();

    /**
     * Constructor
     */
    public NWZC206001() {
        super();
    }

    /**
     * <pre>
     * Supersede API
     * </pre>
     * @param param NWZC206001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC206001PMsg param, final ONBATCH_TYPE onBatchType) {

        // Checking Input value
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (!checkInput(msgMap)) {
            msgMap.flush();
            return;
        }
        // main
        doProcess(msgMap);
        // end
        msgMap.flush();

    }

    /**
     * Input parameter check.
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean checkInput(S21ApiMessageMap msgMap) {

        NWZC206001PMsg param = (NWZC206001PMsg) msgMap.getPmsg();
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.mdseCd)) {
            msgMap.addXxMsgId(NWZM0021E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZM0346E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.xxModeCd)) {
            msgMap.addXxMsgId(NWZM0012E);
            return false;
        } else {
            if (!SUPD_LIST_MODE.equals(param.xxModeCd.getValue()) && !SUPD_LATEST_MODE.equals(param.xxModeCd.getValue())) {
                msgMap.addXxMsgId(NWZM0013E);
                return false;
            }
        }

        if (!ZYPCommonFunc.hasValue(param.xxAvalOrdFlg)) {
            msgMap.addXxMsgId(NWZM1299E);
            return false;
        } else if (!ZYPConstant.FLG_ON_Y.equals(param.xxAvalOrdFlg.getValue()) && !ZYPConstant.FLG_OFF_N.equals(param.xxAvalOrdFlg.getValue())) {
            msgMap.addXxMsgId(NWZM1294E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.xxAvalPrchFlg)) {
            msgMap.addXxMsgId(NWZM1292E);
            return false;
        } else if (!ZYPConstant.FLG_ON_Y.equals(param.xxAvalPrchFlg.getValue()) && !ZYPConstant.FLG_OFF_N.equals(param.xxAvalPrchFlg.getValue())) {
            msgMap.addXxMsgId(NWZM1295E);
            return false;
        }

        return true;
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {

        // Setting variables
        NWZC206001PMsg param = (NWZC206001PMsg) msgMap.getPmsg();
        String mdseCd = param.mdseCd.getValue();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String modeCd = param.xxModeCd.getValue();
        List<Map<String, Object>> supdToMapList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> supdFromMapList = new ArrayList<Map<String, Object>>();

        /*************************************************************
         * 1.Obtaining of Supersede management information of input
         * merchandise code
         ************************************************************/
        SUPDTMsg paramSUPDTMsg = (SUPDTMsg) getSupdInfo(glblCmpyCd, mdseCd);
        if (paramSUPDTMsg == null) {
            return;
        }

        /*************************************************************
         * 2.Obtaining of Merchandise information
         *************************************************************/
        MDSETMsg paramMDSETMsg = (MDSETMsg) getMdse(glblCmpyCd, mdseCd);
        if (paramMDSETMsg == null) {
            msgMap.addXxMsgId(NWZM0036E);
            return;
        }

        /*************************************************************
         * 3.Obtaining of Supersede Relation information
         *************************************************************/
        if (!createSupdToList(msgMap, supdToMapList)) {
            return;
        }

        //Defect#1498 8/6/2013
        //if (SUPD_LIST_MODE.equals(modeCd) || supdToMapList.size()==0) {
        //if (SUPD_LIST_MODE.equals(modeCd)) {
        //Defect#1498 8/6/2013
            if (!createSupdFromList(msgMap, supdFromMapList, supdToMapList)) {
                return;
            }
        //}

        /*************************************************************
         * 4.Filtering Supersede List by XX_AVAL_ORD_FLG, XX_AVAL_PRCH_FLG
         *************************************************************/
        filterSupdMapList(msgMap, supdToMapList, supdFromMapList);

        /*************************************************************
         * 5.Edit Output Parameter
         ************************************************************/
        editOutputParameter(msgMap, supdToMapList, supdFromMapList, paramSUPDTMsg, paramMDSETMsg);

        /*************************************************************
         * 6.Update Inventory Available Quantity in Output Parameter
         ************************************************************/
        updateInvtyAvalQty(msgMap);

    }

    /**
     * Create Supersede (To) List
     * @param msgMap S21ApiMessageMap
     * @param supdToMapList List<Map<String, Object>>
     */
    private boolean createSupdToList(S21ApiMessageMap msgMap, List<Map<String, Object>> supdToMapList) {

        NWZC206001PMsg param = (NWZC206001PMsg) msgMap.getPmsg();
        String mdseCd = param.mdseCd.getValue();
        String glblCmpyCd = param.glblCmpyCd.getValue();

        List<Map<String, Object>> supdToMapResultList = (List<Map<String, Object>>) query.getSupdInfoTo(param, mdseCd);

        if (supdToMapResultList.size() != 0) {
            if (supdToMapResultList.size() != 1) {
                msgMap.addXxMsgId(NWZM1296E);
                return false;
            }
            Map<String, Object> supdToMap = supdToMapResultList.get(0);
            String supdFromMdseCd = (String) supdToMap.get("SUPD_TO_MDSE_CD");
            supdToMapList.add(supdToMap);
            while (true) {

                List<Map<String, Object>> supdToMapTmpResultList = (List<Map<String, Object>>) query.getSupdInfoTo(param, supdFromMdseCd);

                if (supdToMapTmpResultList.size() == 1) {
                    Map<String, Object> supdToMapTmp = supdToMapTmpResultList.get(0);
                    supdFromMdseCd = (String) supdToMapTmp.get("SUPD_TO_MDSE_CD");
                    if (supdToMapList.contains(supdToMapTmp) || mdseCd.equals(supdFromMdseCd)) {
                        msgMap.addXxMsgId(NWZM1297E);
                        return false;
                    }
                    supdToMapList.add(supdToMapTmp);
                } else if (supdToMapTmpResultList.size() == 0) {

                    if (existsSupdRelnTo(glblCmpyCd, supdFromMdseCd)) {
                        msgMap.addXxMsgId(NWZM1293E);
                        return false;
                    }

                    break;
                } else if (supdToMapTmpResultList.size() > 1) {
                    msgMap.addXxMsgId(NWZM1296E);
                    return false;
                }
            }
            //Defect#1498 8/6/2013
//        }
      } else {
            if (existsSupdRelnTo(glblCmpyCd, mdseCd)) {
                msgMap.addXxMsgId(NWZM1293E);
                return false;
            }
//
//            Map<String, Object> supdToMap = supdToMapResultList.get(0);
//            String supdToMdseCd = (String) supdToMap.get("SUPD_TO_MDSE_CD");
//            supdToMapList.add(supdToMap);
//
//            List<Map<String, Object>> supdToMapTmpResultList = (List<Map<String, Object>>) query.getSupdInfoFromForLatest(param, supdToMdseCd);
//
//            if (supdToMapTmpResultList.size() == 1) {
//                Map<String, Object> supdToMapTmp = supdToMapTmpResultList.get(0);
//                supdToMdseCd = (String) supdToMapTmp.get("SUPD_FROM_MDSE_CD");
//                if (supdToMapList.contains(supdToMapTmp) || mdseCd.equals(supdToMdseCd)) {
//                    msgMap.addXxMsgId(NWZM1297E);
//                    return false;
//                }
//                supdToMapList.add(supdToMapTmp);
//            }else{
//                if (existsSupdRelnTo(glblCmpyCd, mdseCd)) {
//                    msgMap.addXxMsgId(NWZM1293E);
//                    return false;
//                }
//            }
        }
        //Defect#1498 8/6/2013

        return true;
    }

    /**
     * Create Supersede (From) List
     * @param msgMap S21ApiMessageMap
     * @param supdFromMapList List<Map<String, Object>>
     * @param supdToMapList List<Map<String, Object>>
     */
    private boolean createSupdFromList(S21ApiMessageMap msgMap, List<Map<String, Object>> supdFromMapList, List<Map<String, Object>> supdToMapList) {

        NWZC206001PMsg param = (NWZC206001PMsg) msgMap.getPmsg();
        String mdseCd = param.mdseCd.getValue();

        List<Map<String, Object>> supdFromMapResultList = (List<Map<String, Object>>) query.getSupdInfoFrom(param, mdseCd);

        if (supdFromMapResultList.size() != 0) {
            if (supdFromMapResultList.size() != 1) {
                msgMap.addXxMsgId(NWZM1296E);
                return false;
            }
            Map<String, Object> supdFromMap = supdFromMapResultList.get(0);
            String supdToMdseCd = (String) supdFromMap.get("SUPD_FROM_MDSE_CD");
            supdFromMapList.add(supdFromMap);

            while (true) {

                List<Map<String, Object>> supdFromMapTmpResultList = (List<Map<String, Object>>) query.getSupdInfoFrom(param, supdToMdseCd);

                if (supdFromMapTmpResultList.size() == 1) {
                    Map<String, Object> supdFromMapTmp = supdFromMapTmpResultList.get(0);
                    supdToMdseCd = (String) supdFromMapTmp.get("SUPD_FROM_MDSE_CD");
                    if (supdFromMapList.contains(supdFromMapTmp) || supdToMapList.contains(supdFromMapTmp) || mdseCd.equals(supdToMdseCd)) {
                        msgMap.addXxMsgId(NWZM1297E);
                        return false;
                    }
                    supdFromMapList.add(supdFromMapTmp);
                } else if (supdFromMapTmpResultList.size() == 0) {

                    if (!checkSupdInfoFrom(param, supdToMdseCd)) {
                        msgMap.addXxMsgId(NWZM1293E);
                        return false;
                    }

                    break;
                } else if (supdFromMapTmpResultList.size() > 1) {
                    msgMap.addXxMsgId(NWZM1296E);
                    return false;
                }
            }
        } else {

            if (!checkSupdInfoFrom(param, mdseCd)) {
                msgMap.addXxMsgId(NWZM1293E);
                return false;
            }

        }

        return true;
    }

    /**
     * Filter Supersede Map List
     * @param msgMap S21ApiMessageMap
     * @param supdToMapList List<Map<String, Object>>
     * @param supdFromMapList List<Map<String, Object>>
     */
    private void filterSupdMapList(S21ApiMessageMap msgMap,
                                     List<Map<String, Object>> supdToMapList,
                                     List<Map<String, Object>> supdFromMapList) {

        NWZC206001PMsg param = (NWZC206001PMsg) msgMap.getPmsg();
        String avalOrdFlg = param.xxAvalOrdFlg.getValue();
        String avalPrchFlg = param.xxAvalPrchFlg.getValue();

        if (ZYPConstant.FLG_ON_Y.equals(avalOrdFlg) && ZYPConstant.FLG_OFF_N.equals(avalPrchFlg)) {

            filterSupdMapListForOrd(supdToMapList, supdFromMapList);

        } else if (ZYPConstant.FLG_OFF_N.equals(avalOrdFlg) && ZYPConstant.FLG_ON_Y.equals(avalPrchFlg)) {

            filterSupdMapListForPO(supdToMapList, supdFromMapList);

        } else if (ZYPConstant.FLG_ON_Y.equals(avalOrdFlg) && ZYPConstant.FLG_ON_Y.equals(avalPrchFlg)) {

            filterSupdMapListForVndDrop(supdToMapList, supdFromMapList);

        }
    }

    /**
     * Filter Supersede Map List For Order
     * @param supdToMapList List<Map<String, Object>>
     * @param supdFromMapList List<Map<String, Object>>
     */
    private void filterSupdMapListForOrd(List<Map<String, Object>> supdToMapList,
                                         List<Map<String, Object>> supdFromMapList) {

        removeInvalidElmntForOrd(supdToMapList);
        removeInvalidElmntForOrd(supdFromMapList);

    }

    /**
     * Filter Supersede Map List For Order
     * @param supdMapList List<Map<String, Object>>
     */
    private void removeInvalidElmntForOrd(List<Map<String, Object>> supdMapList) {
        Iterator<Map<String, Object>> iterSupdMap = supdMapList.iterator();
        while (iterSupdMap.hasNext()) {
            Map<String, Object> supdMap = (Map<String, Object>) iterSupdMap.next();
            String sellHldFlg = (String) supdMap.get("SELL_HLD_FLG");
            String rgtnStsCd = (String) supdMap.get("RGTN_STS_CD");
            if (!ZYPConstant.FLG_OFF_N.equals(sellHldFlg) || !RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
                iterSupdMap.remove();
            }
        }
    }

    /**
     * Filter Supersede Map List For PO
     * @param supdToMapList List<Map<String, Object>>
     * @param supdFromMapList List<Map<String, Object>>
     */
    private void filterSupdMapListForPO(List<Map<String, Object>> supdToMapList,
                                        List<Map<String, Object>> supdFromMapList) {

        removeInvalidElmntForPO(supdToMapList);
        removeInvalidElmntForPO(supdFromMapList);

    }

    /**
     * Filter Supersede Map List For PO
     * @param supdMapList List<Map<String, Object>>
     */
    private void removeInvalidElmntForPO(List<Map<String, Object>> supdMapList) {
        Iterator<Map<String, Object>> iterSupdMap = supdMapList.iterator();
        while (iterSupdMap.hasNext()) {
            Map<String, Object> supdMap = (Map<String, Object>) iterSupdMap.next();
            String dsctnFlg = (String) supdMap.get("DSCTN_FLG");
            String rgtnStsCd = (String) supdMap.get("RGTN_STS_CD");
            if (!ZYPConstant.FLG_OFF_N.equals(dsctnFlg) || !(RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING.equals(rgtnStsCd) || RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd))) {
                iterSupdMap.remove();
            }
        }
    }

    /**
     * Filter Supersede Map List For Order (Vender Drop)
     * @param supdToMapList List<Map<String, Object>>
     * @param supdFromMapList List<Map<String, Object>>
     */
    private void filterSupdMapListForVndDrop(List<Map<String, Object>> supdToMapList,
                                             List<Map<String, Object>> supdFromMapList) {

        removeInvalidElmntForVndDrop(supdToMapList);
        removeInvalidElmntForVndDrop(supdFromMapList);

    }

    /**
     * Filter Supersede Map List For Order (Vender Drop)
     * @param supdMapList List<Map<String, Object>>
     */
    private void removeInvalidElmntForVndDrop(List<Map<String, Object>> supdMapList) {
        Iterator<Map<String, Object>> iterSupdMap = supdMapList.iterator();
        while (iterSupdMap.hasNext()) {
            Map<String, Object> supdMap = (Map<String, Object>) iterSupdMap.next();
            String sellHldFlg = (String) supdMap.get("SELL_HLD_FLG");
            String dsctnFlg = (String) supdMap.get("DSCTN_FLG");
            String rgtnStsCd = (String) supdMap.get("RGTN_STS_CD");
            if (!ZYPConstant.FLG_OFF_N.equals(sellHldFlg) || !ZYPConstant.FLG_OFF_N.equals(dsctnFlg) || !RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
                iterSupdMap.remove();
            }
        }
    }

    /**
     * Edit Output Parameter
     * @param msgMap S21ApiMessageMap
     * @param supdToMapList List<Map<String, Object>>
     * @param supdFromMapList List<Map<String, Object>>
     * @param paramSUPDTMsg SUPDTMsg
     * @param paramMDSETMsg MDSETMsg
     */
    private void editOutputParameter(S21ApiMessageMap msgMap,
                                     List<Map<String, Object>> supdToMapList,
                                     List<Map<String, Object>> supdFromMapList,
                                     SUPDTMsg paramSUPDTMsg,
                                     MDSETMsg paramMDSETMsg) {

        NWZC206001PMsg param = (NWZC206001PMsg) msgMap.getPmsg();
        String mdseCd = param.mdseCd.getValue();
        String modeCd = param.xxModeCd.getValue();

        //Defect#1498 8/6/2013
//        if (SUPD_LATEST_MODE.equals(modeCd)) {
//            int supdToMapListSize = supdToMapList.size();
//            if (supdToMapListSize > 0) {
//                Map<String, Object> supdToMap = (Map<String, Object>) supdToMapList.get(supdToMapListSize - 1);
//                NWZC206001_APMsg aPMsg = param.A.no(0);
//                String latestMdseCd = (String) supdToMap.get("SUPD_TO_MDSE_CD");
//                String incgFlg = (String) supdToMap.get("INCG_FLG");
//                String mdseNm = (String) supdToMap.get("MDSE_NM");
//
//                ZYPEZDItemValueSetter.setValue(aPMsg.mdseCd, latestMdseCd);
//                ZYPEZDItemValueSetter.setValue(aPMsg.incgFlg, incgFlg);
//                ZYPEZDItemValueSetter.setValue(aPMsg.mdseNm, mdseNm);
//
//                param.A.setValidCount(1);
//            }
//        }

//            else if (supdToMapList.size()==0) {
//                int supdFromMapListSize = supdFromMapList.size();
//                if (supdFromMapListSize > 0) {
//
//                    Map<String, Object> supdFromMap = (Map<String, Object>) supdFromMapList.get(0);
//                    NWZC206001_APMsg aPMsg = param.A.no(0);
//
//                    String supdFromMdseCd = (String) supdFromMap.get("SUPD_FROM_MDSE_CD");
//                    
//                    SUPDTMsg supd = getSupdInfo(param.glblCmpyCd.getValue(), supdFromMdseCd);
//                    String supdFromIncgFlg = supd.incgFlg.getValue().toString();
//                    MDSETMsg mdse = getMdse(param.glblCmpyCd.getValue(), supdFromMdseCd);
//                    String supdFromMdseNm = mdse.mdseNm.getValue().toString();
//
//                    ZYPEZDItemValueSetter.setValue(aPMsg.mdseCd, supdFromMdseCd);
//                    ZYPEZDItemValueSetter.setValue(aPMsg.incgFlg, supdFromIncgFlg);
//                    ZYPEZDItemValueSetter.setValue(aPMsg.mdseNm, supdFromMdseNm);
//                    param.A.setValidCount(1);
//                }
//            }
//        } else if (SUPD_LIST_MODE.equals(modeCd)) {
//            int supdFromMapListSize = supdFromMapList.size();
//            int aPMsgIndex = 0;
//            long revnNum = 0;
//            long revnNumTmp = 0;
//            if (supdFromMapListSize > 0) {
//                for (int i = supdFromMapListSize - 1; i >= 0; i--) {
//                    Map<String, Object> supdFromMap = (Map<String, Object>) supdFromMapList.get(i);
//                    NWZC206001_APMsg aPMsg = param.A.no(aPMsgIndex);
//
//                    revnNum++;
//                    String supdFromMdseCd = (String) supdFromMap.get("SUPD_FROM_MDSE_CD");
//                    String supdFromIncgFlg = (String) supdFromMap.get("INCG_FLG");
//                    String supdFromMdseNm = (String) supdFromMap.get("MDSE_NM");
//
//                    ZYPEZDItemValueSetter.setValue(aPMsg.mdseCd, supdFromMdseCd);
//                    ZYPEZDItemValueSetter.setValue(aPMsg.incgFlg, supdFromIncgFlg);
//                    ZYPEZDItemValueSetter.setValue(aPMsg.mdseNm, supdFromMdseNm);
//                    ZYPEZDItemValueSetter.setValue(aPMsg.xxRevnNum, BigDecimal.valueOf(revnNum));
//
//                    aPMsgIndex++;
//                }
//            }
//
//            ZYPEZDItemValueSetter.setValue(param.mdseCd_S1, mdseCd);
//            ZYPEZDItemValueSetter.setValue(param.mdseNm_S1, paramMDSETMsg.mdseNm);
//            ZYPEZDItemValueSetter.setValue(param.incgFlg_S1, paramSUPDTMsg.incgFlg);
//
//            if (supdFromMapList.size() == 0 && supdToMapList.size() == 0) {
//
//                revnNum++;
//                ZYPEZDItemValueSetter.setValue(param.xxRevnNum_S1, BigDecimal.valueOf(revnNum));
//
//                return;
//
//            } else {
//
//                NWZC206001_APMsg aPMsgForParam = param.A.no(aPMsgIndex);
//                String avalOrdFlg = param.xxAvalOrdFlg.getValue();
//                String avalPrchFlg = param.xxAvalPrchFlg.getValue();
//                String sellHldFlg = paramMDSETMsg.sellHldFlg.getValue();
//                String dsctnFlg = paramMDSETMsg.dsctnFlg.getValue();
//                String rgtnStsCd = paramMDSETMsg.rgtnStsCd.getValue();
//                boolean isValidMdse = false;
//
//                if (ZYPConstant.FLG_ON_Y.equals(avalOrdFlg) && ZYPConstant.FLG_OFF_N.equals(avalPrchFlg)) {
//                    if (ZYPConstant.FLG_OFF_N.equals(sellHldFlg) && RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
//                        isValidMdse = true;
//                    }
//                } else if (ZYPConstant.FLG_OFF_N.equals(avalOrdFlg) && ZYPConstant.FLG_ON_Y.equals(avalPrchFlg)) {
//                    if (ZYPConstant.FLG_OFF_N.equals(dsctnFlg) && (RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING.equals(rgtnStsCd) || RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd))) {
//                        isValidMdse = true;
//                    }
//                } else if (ZYPConstant.FLG_ON_Y.equals(avalOrdFlg) && ZYPConstant.FLG_ON_Y.equals(avalPrchFlg)) {
//                    if (ZYPConstant.FLG_OFF_N.equals(sellHldFlg) && ZYPConstant.FLG_OFF_N.equals(dsctnFlg) && RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
//                        isValidMdse = true;
//                    }
//                } else if (ZYPConstant.FLG_OFF_N.equals(avalOrdFlg) && ZYPConstant.FLG_OFF_N.equals(avalPrchFlg)) {
//                    isValidMdse = true;
//                }
//
//                if (isValidMdse) {
//                    revnNum++;
//                    revnNumTmp = revnNum;
//                    ZYPEZDItemValueSetter.setValue(aPMsgForParam.mdseCd, mdseCd);
//                    ZYPEZDItemValueSetter.setValue(aPMsgForParam.incgFlg, paramSUPDTMsg.incgFlg);
//                    ZYPEZDItemValueSetter.setValue(aPMsgForParam.mdseNm, paramMDSETMsg.mdseNm);
//                    ZYPEZDItemValueSetter.setValue(aPMsgForParam.xxRevnNum, BigDecimal.valueOf(revnNum));
//
//                    aPMsgIndex++;
//                }
//
//                for (Map<String, Object> supdToMap : supdToMapList) {
//                    NWZC206001_APMsg aPMsg = param.A.no(aPMsgIndex);
//
//                    revnNum++;
//                    String supdToMdseCd = (String) supdToMap.get("SUPD_TO_MDSE_CD");
//                    String supdToIncgFlg = (String) supdToMap.get("INCG_FLG");
//                    String supdToMdseNm = (String) supdToMap.get("MDSE_NM");
//
//                    ZYPEZDItemValueSetter.setValue(aPMsg.mdseCd, supdToMdseCd);
//                    ZYPEZDItemValueSetter.setValue(aPMsg.incgFlg, supdToIncgFlg);
//                    ZYPEZDItemValueSetter.setValue(aPMsg.mdseNm, supdToMdseNm);
//                    ZYPEZDItemValueSetter.setValue(aPMsg.xxRevnNum, BigDecimal.valueOf(revnNum));
//
//                    aPMsgIndex++;
//                }
//            }
//
//            if (revnNumTmp > 0) {
//                ZYPEZDItemValueSetter.setValue(param.xxRevnNum_S1, BigDecimal.valueOf(revnNumTmp));
//            }
//
//            param.A.setValidCount(aPMsgIndex);
//        }

        //Defect#1498 8/6/2013
        //Create All Supersede List
        List<Map<String, String>> supdAllList = new ArrayList<Map<String, String>>();

        for(int i = supdFromMapList.size() - 1; i >= 0; i--){
            Map<String, Object> supdFromMap = (Map<String, Object>) supdFromMapList.get(i);
            String supdFromMdseCd = (String) supdFromMap.get("SUPD_FROM_MDSE_CD");
            String supdFromIncgFlg = (String) supdFromMap.get("INCG_FLG");
            String supdFromMdseNm = (String) supdFromMap.get("MDSE_NM");
            
            Map<String, String> supdAllMap = new HashMap <String, String>();
            supdAllMap.put("MDSE_CD", supdFromMdseCd);
            supdAllMap.put("INCG_FLG", supdFromIncgFlg);
            supdAllMap.put("MDSE_NM", supdFromMdseNm);
            supdAllList.add(supdAllMap);
        }
        
        String avalOrdFlg = param.xxAvalOrdFlg.getValue();
        String avalPrchFlg = param.xxAvalPrchFlg.getValue();
        String sellHldFlg = paramMDSETMsg.sellHldFlg.getValue();
        String dsctnFlg = paramMDSETMsg.dsctnFlg.getValue();
        String rgtnStsCd = paramMDSETMsg.rgtnStsCd.getValue();
        boolean isValidMdse = false;

        if (ZYPConstant.FLG_ON_Y.equals(avalOrdFlg) && ZYPConstant.FLG_OFF_N.equals(avalPrchFlg)) {
            if (ZYPConstant.FLG_OFF_N.equals(sellHldFlg) && RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
                isValidMdse = true;
            }
        } else if (ZYPConstant.FLG_OFF_N.equals(avalOrdFlg) && ZYPConstant.FLG_ON_Y.equals(avalPrchFlg)) {
            if (ZYPConstant.FLG_OFF_N.equals(dsctnFlg) && (RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING.equals(rgtnStsCd) || RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd))) {
                isValidMdse = true;
            }
        } else if (ZYPConstant.FLG_ON_Y.equals(avalOrdFlg) && ZYPConstant.FLG_ON_Y.equals(avalPrchFlg)) {
            if (ZYPConstant.FLG_OFF_N.equals(sellHldFlg) && ZYPConstant.FLG_OFF_N.equals(dsctnFlg) && RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
                isValidMdse = true;
            }
        } else if (ZYPConstant.FLG_OFF_N.equals(avalOrdFlg) && ZYPConstant.FLG_OFF_N.equals(avalPrchFlg)) {
            isValidMdse = true;
        }
        if (isValidMdse) {
            Map<String, String> supdAllMap = new HashMap <String, String>();
            supdAllMap.put("MDSE_CD", mdseCd);
            supdAllMap.put("INCG_FLG", paramSUPDTMsg.incgFlg.getValue().toString());
            supdAllMap.put("MDSE_NM", paramMDSETMsg.mdseNm.getValue().toString());
            supdAllList.add(supdAllMap);
        }

        for (Map<String, Object> supdToMap : supdToMapList) {
            String supdToMdseCd = (String) supdToMap.get("SUPD_TO_MDSE_CD");
            String supdToIncgFlg = (String) supdToMap.get("INCG_FLG");
            String supdToMdseNm = (String) supdToMap.get("MDSE_NM");

            Map<String, String> supdAllMap = new HashMap <String, String>();
            supdAllMap.put("MDSE_CD", supdToMdseCd);
            supdAllMap.put("INCG_FLG", supdToIncgFlg);
            supdAllMap.put("MDSE_NM", supdToMdseNm);
            supdAllList.add(supdAllMap);
        }

        if (SUPD_LATEST_MODE.equals(modeCd)) {

            int supdToMapListSize = supdAllList.size();
            if (supdToMapListSize > 0) {
                Map<String, String> supdToMap = (Map<String, String>) supdAllList.get(supdToMapListSize - 1);
                NWZC206001_APMsg aPMsg = param.A.no(0);
                String latestMdseCd = supdToMap.get("MDSE_CD");
                String incgFlg = supdToMap.get("INCG_FLG");
                String mdseNm = supdToMap.get("MDSE_NM");

                ZYPEZDItemValueSetter.setValue(aPMsg.mdseCd, latestMdseCd);
                ZYPEZDItemValueSetter.setValue(aPMsg.incgFlg, incgFlg);
                ZYPEZDItemValueSetter.setValue(aPMsg.mdseNm, mdseNm);

                param.A.setValidCount(1);
            }
        }else if (SUPD_LIST_MODE.equals(modeCd)) {

            int supdFromMapListSize = supdAllList.size();
            int aPMsgIndex = 0;
            if (supdFromMapListSize > 0) {
                for (int i = 1; i <= supdFromMapListSize; i++) {
                    Map<String, String> supdFromMap = (Map<String, String>) supdAllList.get(i-1);
                    NWZC206001_APMsg aPMsg = param.A.no(aPMsgIndex);

                    aPMsgIndex++;
                    String supdFromMdseCd = supdFromMap.get("MDSE_CD");
                    String supdFromIncgFlg = supdFromMap.get("INCG_FLG");
                    String supdFromMdseNm = supdFromMap.get("MDSE_NM");

                    ZYPEZDItemValueSetter.setValue(aPMsg.mdseCd, supdFromMdseCd);
                    ZYPEZDItemValueSetter.setValue(aPMsg.incgFlg, supdFromIncgFlg);
                    ZYPEZDItemValueSetter.setValue(aPMsg.mdseNm, supdFromMdseNm);
                    ZYPEZDItemValueSetter.setValue(aPMsg.xxRevnNum, BigDecimal.valueOf(aPMsgIndex));

                    if (supdFromMdseCd.equals(mdseCd)) {
                        ZYPEZDItemValueSetter.setValue(param.xxRevnNum_S1, BigDecimal.valueOf(aPMsgIndex));
                    }
                }
            }
            param.A.setValidCount(aPMsgIndex);
        }
        //Defect#1498 8/6/2013

        ZYPEZDItemValueSetter.setValue(param.mdseCd_S1, mdseCd);
        ZYPEZDItemValueSetter.setValue(param.mdseNm_S1, paramMDSETMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(param.incgFlg_S1, paramSUPDTMsg.incgFlg);
    }

    /**
     * Update Inventory Available Quantity
     * @param msgMap S21ApiMessageMap
     */
    private void updateInvtyAvalQty(S21ApiMessageMap msgMap) {

        NWZC206001PMsg param = (NWZC206001PMsg) msgMap.getPmsg();
        String whCd = param.whCd.getValue();
        String stkStsCd = param.stkStsCd.getValue();

        if (!ZYPCommonFunc.hasValue(whCd) || !ZYPCommonFunc.hasValue(stkStsCd)) {
            return;
        }

        final Set<String> mdseCdList = new LinkedHashSet<String>();

        for (int i = 0; i < param.A.getValidCount(); i++) {
            NWZC206001_APMsg aPMsg = param.A.no(i);
            mdseCdList.add(aPMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(aPMsg.invtyAvalQty, BigDecimal.ZERO);
        }

        if (mdseCdList.size() > 0) {
            List<Map<String, Object>> invtyAvalQtyMapList = (List<Map<String, Object>>) query.getInvtyAvalQty(param, mdseCdList);
            for (Map<String, Object> invtyAvalQtyMap : invtyAvalQtyMapList) {
                String invtyMdseCd = (String) invtyAvalQtyMap.get("MDSE_CD");
                BigDecimal invtyAvalQty = (BigDecimal) invtyAvalQtyMap.get("INVTY_AVAL_QTY");

                for (int i = 0; i < param.A.getValidCount(); i++) {
                    NWZC206001_APMsg aPMsg = param.A.no(i);
                    String supdMdseCd = aPMsg.mdseCd.getValue();
                    if (invtyMdseCd.equals(supdMdseCd)) {
                        ZYPEZDItemValueSetter.setValue(aPMsg.invtyAvalQty, invtyAvalQty);
                    }
                }
            }
        }

    }

    /**
     * Get merchandise Information
     * @param globalCompanyCode Global Company Code
     * @param mdseCd Merchandise Code
     * @return merchandise Name
     */
    private MDSETMsg getMdse(String globalCompanyCode, String mdseCd) {
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);
    }

    /**
     * Get Supersede Information
     * @param globalCompanyCode Global Company Code
     * @param mdseCd Merchandise Code
     * @return SUPDTMsg
     */
    private SUPDTMsg getSupdInfo(String globalCompanyCode, String mdseCd) {
        SUPDTMsg supdTMsg = new SUPDTMsg();
        ZYPEZDItemValueSetter.setValue(supdTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(supdTMsg.mdseCd, mdseCd);
        return (SUPDTMsg) S21ApiTBLAccessor.findByKey(supdTMsg);
    }

    /**
     * Check Merchandise (To) Exists in SUPD_RELN
     * @param globalCompanyCode Global Company Code
     * @param supdFromMdseCd Merchandise Code
     * @return boolean
     */
    private boolean existsSupdRelnTo(String glblCmpyCd, String supdFromMdseCd) {
        SUPD_RELNTMsg supdRelnTMsg = new SUPD_RELNTMsg();
        supdRelnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        supdRelnTMsg.setConditionValue("supdFromMdseCd01", supdFromMdseCd);
        supdRelnTMsg.setSQLID("002");
        SUPD_RELNTMsgArray supdRelnTMsgArray = (SUPD_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(supdRelnTMsg);
        if (supdRelnTMsgArray != null && supdRelnTMsgArray.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Check Merchandise (From) Exists in SUPD_RELN
     * @param globalCompanyCode Global Company Code
     * @param supdToMdseCd Merchandise Code
     * @return boolean
     */
    private boolean existsSupdRelnFrom(String glblCmpyCd, String supdToMdseCd) {
        SUPD_RELNTMsg supdRelnTMsg = new SUPD_RELNTMsg();
        supdRelnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        supdRelnTMsg.setConditionValue("supdToMdseCd01", supdToMdseCd);
        supdRelnTMsg.setSQLID("001");
        SUPD_RELNTMsgArray supdRelnTMsgArray = (SUPD_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(supdRelnTMsg);
        if (supdRelnTMsgArray != null && supdRelnTMsgArray.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Check Supersede Information (From)
     * @param param NWZC206001PMsg
     * @param supdToMdseCd Merchandise Code
     * @return boolean
     */
    private boolean checkSupdInfoFrom(NWZC206001PMsg param, String supdToMdseCd) {
        if (existsSupdRelnFrom(param.glblCmpyCd.getValue(), supdToMdseCd)) {
            if (!existsSupdRelnFromIncgFlgN(param, supdToMdseCd)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check Merchandise (From)  As Interchange Flag is N Exists in SUPD_RELN
     * @param param NWZC206001PMsg
     * @param supdToMdseCd Merchandise Code
     * @return boolean
     */
    private boolean existsSupdRelnFromIncgFlgN(NWZC206001PMsg param, String supdToMdseCd) {
        List<Map<String, Object>> supdRelnFromIncgFlgNResultList = (List<Map<String, Object>>) query.getSupdInfoFromIncgFlgN(param, supdToMdseCd);
        if (supdRelnFromIncgFlgNResultList.size() >= 1) {
            return true;
        }
        return false;
    }
}
