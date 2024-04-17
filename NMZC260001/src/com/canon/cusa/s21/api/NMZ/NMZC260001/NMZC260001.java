/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC260001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC260001.constant.NMZC260001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 * <pre>
 * NMZC260001 Default Rep API
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 2015/07/27   Fujitsu         N.Sugiura       Create          Create
 * 2016/07/25   Fujitsu         N.Sugiura       Update          QC#11812
 * 2017/03/08   Fujitsu         W.Honda         Update          QC#16855
 * 2017/10/31   Fujitsu         H.Sugawara      Update          QC#20146(Sol#092)
 * 2018/04/02   Fujitsu         K.Ishizuka      Update          QC#24860
 * 2018/04/03   Fujitsu         K.Ishizuka      Update          QC#24860-2
 * 2018/04/11   Fujitsu         K.Ishizuka      Update          QC#25418
 * 2018/09/18   Fujitsu         Mz.Takahashi    Update          QC#28244
 * 2018/11/09   Fujitsu         M.Ohno          Update          QC#29010
 * 2020/04/23   CITS            K.Ogino         Update          QC#56638
 *</pre>
 */
public class NMZC260001 extends S21ApiCommonBase {

    /**
     * NMZC260001Query
     */
    private NMZC260001Query query = new NMZC260001Query();

    // QC#24860 Add Start
    private boolean dmmySlsRepFlg = false;
    // QC#24860 Add End

    // QC#56638 Add Start
    private boolean isShipBase = true;
    // QC#56638 Add End

    /**
     * Constructor
     */
    public NMZC260001() {
        super();
    }

    /**
     * <pre>
     * Supersede API
     * </pre>
     * @param param NMZC260001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NMZC260001PMsg param, final ONBATCH_TYPE onBatchType) {

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

        NMZC260001PMsg param = (NMZC260001PMsg) msgMap.getPmsg();
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NMZC260001Constant.NMZM0001E);
            return false;
        }

        // Add 2020/04/24 QC#56638 Start
        if (ZYPCommonFunc.hasValue(param.dsOrdTpCd)) {

            isShipBase = true;
            if (ZYPCommonFunc.hasValue(param.dsOrdTpCd)) {
                DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, param.dsOrdTpCd);
                tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);

                if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.baseLocTxt)) {
                    if ("Ship To Location".equals(tMsg.baseLocTxt.getValue())) {
                        isShipBase = true;
                    } else if ("Sold To Location".equals(tMsg.baseLocTxt.getValue())) {
                        isShipBase = false;
                    } else {
                        isShipBase = true;
                    }
                }
            }

            if (isShipBase && !ZYPCommonFunc.hasValue(param.shipToCustCd)) {
                String[] messageParam = {"Ship To Customer Code" };
                msgMap.addXxMsgIdWithPrm(NMZC260001Constant.NMAM8203W, messageParam);
                return false;
            }

            if (!isShipBase && !ZYPCommonFunc.hasValue(param.billToCustCd)) {
                String[] messageParam = {"Bill To Customer Code" };
                msgMap.addXxMsgIdWithPrm(NMZC260001Constant.NMAM8203W, messageParam);
                return false;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(param.shipToCustCd)) {
                String[] messageParam = {"Ship To Customer Code"};
                msgMap.addXxMsgIdWithPrm(NMZC260001Constant.NMAM8203W, messageParam);
                return false;
            }
        }
        // Add 2020/04/24 QC#56638 End

        // Add Start 2017/10/31 QC#20146(Sol#092)
        if (NMZC260001Constant.XX_MODE_CD_EMSD.equals(param.xxModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(param.shipToCustAcctCd)) {
                msgMap.addXxMsgId(NMZC260001Constant.NMZM0350E);
                return false;
            }
        }
        // Add End 2017/10/31 QC#20146(Sol#092)

        return true;
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {

        // Setting variables
        List<Map<String, Object>> slsRepList = new ArrayList<Map<String, Object>>();

        // get default Sales Rep
        if (!getSlsRep(msgMap, slsRepList)) {
            return;
        }

        // Set Output Parameter
        setOutputParameter(msgMap, slsRepList);

        // 2018/04/03 QC#24860-2 Del Start
//        // 2016/07/08 QC#11812 Add Start
//        if (!getPrimRep(msgMap)) {
//            return;
//        }
//        // 2016/07/08 QC#11812 Add End
        // 2018/04/03 QC#24860-2 Del End
        // 2018/04/18 QC#25418 Del Start
        // 2018/04/03 QC#24860-2 Add Start
        //NMZC260001PMsg pMsg = (NMZC260001PMsg) msgMap.getPmsg();
        //boolean doPrimeRep = this.dmmySlsRepFlg && ZYPCommonFunc.hasValue(pMsg.dsOrdCatgCd) && ZYPCommonFunc.hasValue(pMsg.dsOrdTpCd);
        //boolean doIsWebLogic = !this.dmmySlsRepFlg && !ZYPCommonFunc.hasValue(pMsg.dsOrdCatgCd) && ZYPCommonFunc.hasValue(pMsg.dsOrdTpCd);
        //if (doPrimeRep || doIsWebLogic) {
        //    if (!getPrimRep(msgMap)) {
        //        return;
        //    }
        //}
        // 2018/04/03 QC#24860-2 Add End
        // 2018/04/18 QC#25418 Del End
        
        // 2018/04/18 QC#25418 Add Start
        if (!getPrimRep(msgMap)) {
            return;
        }
        // 2018/04/18 QC#25418 Add End

        // Add Start 2017/10/31 QC#20146(Sol#092)
        getFMDummyRep(msgMap);
        // Add End 2017/10/31 QC#20146(Sol#092)
                

    }

    /**
     * Create Sales Rep List
     * @param msgMap S21ApiMessageMap
     * @param slsRepList List<Map<String, Object>>
     */
    private boolean getSlsRep(S21ApiMessageMap msgMap, List<Map<String, Object>> slsRepList) {

        String lineBizTpCd = "";
        String lineBizRoleTpCd = "";
        String tocCd = "";
        String orgCd = "";
        String psnCd = "";
        NMZC260001PMsg param = (NMZC260001PMsg) msgMap.getPmsg();
        
        // 2018/04/02 S21_NA#24860 Add Start
        this.dmmySlsRepFlg = query.isExistOrdCatg(param);
        if (ZYPCommonFunc.hasValue(param.dsOrdTpCd) && ZYPCommonFunc.hasValue(param.dsOrdCatgCd) //
                && this.dmmySlsRepFlg) {
            return true;
        }
        // 2018/04/02 S21_NA#24860 Add End

        // Mod 2020/04/24 QC#56638 Start
        List<Map<String, Object>> getSlsRepList = (List<Map<String, Object>>) query.getDefSlsRep(param, isShipBase);
        // Mod 2020/04/24 QC#56638 End

        // 2016/07/08 QC#11812 Mod Start
        if (getSlsRepList != null && getSlsRepList.size() > 0) {
            for (int i = 0; i < getSlsRepList.size(); i++) {
                Map<String, Object> slsRepMap = getSlsRepList.get(i);
                lineBizTpCd = (String) slsRepMap.get("LINE_BIZ_TP_CD");
                lineBizRoleTpCd = (String) slsRepMap.get("LINE_BIZ_ROLE_TP_CD");
                orgCd = (String) slsRepMap.get("ORG_CD");
                tocCd = (String) slsRepMap.get("TOC_CD");
                psnCd = (String) slsRepMap.get("PSN_CD");

                if (!ZYPCommonFunc.hasValue(lineBizTpCd)) {
                    // 2016/07/08 QC#11812 Mod Start
                    // String[] msgPrms = {NMZC260001Constant.LINE_BIZ_TP_CD };
                    // msgMap.addXxMsgIdWithPrm(NMZC260001Constant.NMAM8203W, msgPrms);
                    // return false;
                    continue;
                    // 2016/07/08 QC#11812 Mod End
                }
                if (!ZYPCommonFunc.hasValue(lineBizRoleTpCd)) {
                    // 2016/07/08 QC#11812 Mod Start
                    // String[] msgPrms = {NMZC260001Constant.LINE_BIZ_ROLE_TP_CD };
                    // msgMap.addXxMsgIdWithPrm(NMZC260001Constant.NMAM8203W, msgPrms);
                    // return false;
                    continue;
                    // 2016/07/08 QC#11812 Mod End
                }
                if (!ZYPCommonFunc.hasValue(orgCd)) {
                    // 2016/07/08 QC#11812 Mod Start
                    // String[] msgPrms = {NMZC260001Constant.ORG_CD };
                    // msgMap.addXxMsgIdWithPrm(NMZC260001Constant.NMAM8203W, msgPrms);
                    // return false;
                    continue;
                    // 2016/07/08 QC#11812 Mod End
                }
                if (!ZYPCommonFunc.hasValue(tocCd)) {
                    // 2016/07/08 QC#11812 Mod Start
                    // String[] msgPrms = {NMZC260001Constant.TOC_CD };
                    // msgMap.addXxMsgIdWithPrm(NMZC260001Constant.NMAM8203W, msgPrms);
                    // return false;
                    continue;
                    // 2016/07/08 QC#11812 Mod End
                }
                if (!ZYPCommonFunc.hasValue(psnCd)) {
                    // 2016/07/08 QC#11812 Mod Start
                    // String[] msgPrms = {NMZC260001Constant.PSN_CD };
                    // msgMap.addXxMsgIdWithPrm(NMZC260001Constant.NMAM8203W, msgPrms);
                    // return false;
                    continue;
                    // 2016/07/08 QC#11812 Mod End
                }
                slsRepList.add(slsRepMap);
            }
        }
        // 2016/07/08 QC#11812 Mod End

        return true;
    }

    /**
     * Edit Output Parameter
     * @param msgMap S21ApiMessageMap
     * @param slsRepList List<Map<String, Object>>
     */
    private void setOutputParameter(S21ApiMessageMap msgMap,
                                     List<Map<String, Object>> slsRepList) {

        NMZC260001PMsg param = (NMZC260001PMsg) msgMap.getPmsg();

        int aPMsgIndex = 0;
        if (slsRepList.size() > 0) {
            for (int i = 0; i <= slsRepList.size() - 1; i++) {
                Map<String, Object> slsRepMap = (Map<String, Object>) slsRepList.get(i);
                NMZC260001_defSlsRepListPMsg aPMsg = param.defSlsRepList.no(aPMsgIndex);

                aPMsgIndex++;
                String lineBizTpCd = (String) slsRepMap.get("LINE_BIZ_TP_CD");
                String lineBizRoleTpCd = (String) slsRepMap.get("LINE_BIZ_ROLE_TP_CD");
                String orgCd = (String) slsRepMap.get("ORG_CD");
                String tocCd = (String) slsRepMap.get("TOC_CD");
                String psnCd = (String) slsRepMap.get("PSN_CD");
                String orgRankNum = (String) slsRepMap.get("ORG_RANK_NUM");
                String shipToCustCd = (String) slsRepMap.get("SHIP_TO_CUST_CD");
                String billToCustCd = (String) slsRepMap.get("BILL_TO_CUST_CD");
                // Add Start 2017/03/08 QC#16855
                String trtyGrpTpCd = (String) slsRepMap.get("TRTY_GRP_TP_CD");
                // Add End 2017/03/08 QC#16855

                ZYPEZDItemValueSetter.setValue(aPMsg.lineBizTpCd, lineBizTpCd);
                ZYPEZDItemValueSetter.setValue(aPMsg.lineBizRoleTpCd, lineBizRoleTpCd);
                ZYPEZDItemValueSetter.setValue(aPMsg.orgCd, orgCd);
                ZYPEZDItemValueSetter.setValue(aPMsg.tocCd, tocCd);
                ZYPEZDItemValueSetter.setValue(aPMsg.psnCd, psnCd);
                ZYPEZDItemValueSetter.setValue(aPMsg.orgRankNum, orgRankNum);
                ZYPEZDItemValueSetter.setValue(aPMsg.shipToCustCd, shipToCustCd);
                ZYPEZDItemValueSetter.setValue(aPMsg.billToCustCd, billToCustCd);
                // Add Start 2017/03/08 QC#16855
                ZYPEZDItemValueSetter.setValue(aPMsg.trtyGrpTpCd, trtyGrpTpCd);
                // Add End 2017/03/08 QC#16855
                // Add Start 2017/10/31 QC#20146(Sol#092)
                ZYPEZDItemValueSetter.setValue(aPMsg.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);
                // Add End 2017/10/31 QC#20146(Sol#092)

            }
        }
        param.defSlsRepList.setValidCount(aPMsgIndex);
    }
    // 2016/07/08 QC#11812 Add Start
    /**
     * Get Primary Sales Rep
     * @param msgMap S21ApiMessageMap
     */
    private boolean getPrimRep(S21ApiMessageMap msgMap) {
        NMZC260001PMsg param = (NMZC260001PMsg) msgMap.getPmsg();
        Set<String> checkLineBizRoleTpCd = new HashSet<String>(param.defSlsRepList.getValidCount());
        int aPMsgIndex = 0;
        if (ZYPCommonFunc.hasValue(param.dsOrdTpCd)) {
            // 2018/11/09 S21_NA#29010 Add Start
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, param.dsOrdTpCd);
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);

            List<String> trtyGrpTpList = null;
            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.trtyGrpTpTxt)) {
                trtyGrpTpList = Arrays.asList(tMsg.trtyGrpTpTxt.getValue().split(","));
            }
            // 2018/11/09 S21_NA#29010 Add End

            // 2018/11/09 S21_NA#29010 Mod Start
//            List<Map<String, Object>> getSlsRepListForDsOrderType = (List<Map<String, Object>>) query.getDefSlsRepForDsOrderType(param);
            List<Map<String, Object>> getSlsRepListForDsOrderType = (List<Map<String, Object>>) query.getDefSlsRepForDsOrderType(param, trtyGrpTpList);
            // 2018/11/09 S21_NA#29010 Mod End
            if (getSlsRepListForDsOrderType != null && getSlsRepListForDsOrderType.size() > 0) {
                // 2018/09/18 S21_NA#28244 Add Start
                String tocCd = (String) getSlsRepListForDsOrderType.get(0).get("DEF_SLS_REP_TOC_CD");

                if (ZYPCommonFunc.hasValue(tocCd)) {
                // 2018/09/18 S21_NA#28244 Add End

                    for (int i = 0; i < param.defSlsRepList.getValidCount(); i++) {
                        checkLineBizRoleTpCd.add(param.defSlsRepList.no(i).lineBizRoleTpCd.getValue());
                    }

                    String lineBizTpCd = (String) getSlsRepListForDsOrderType.get(0).get("LINE_BIZ_TP_CD");
                    // 2018/09/18 S21_NA#28244 Del Start
                    // String tocCd = (String)getSlsRepListForDsOrderType.get(0).get("DEF_SLS_REP_TOC_CD");
                    // 2018/09/18 S21_NA#28244 Del End
                    String lineBizRoleTpCd = "";
                    // 2018/04/11 S21_NA#29010 Add Start
                    String trtyGrpTpCd = (String) getSlsRepListForDsOrderType.get(0).get("TRTY_GRP_TP_CD"); // 2018/04/11 S21_NA#25418 Add
                    if (trtyGrpTpCd != null) {
                        String[] trtyGrpTpCdArray = tMsg.trtyGrpTpTxt.getValue().split(",");
                        trtyGrpTpCd = trtyGrpTpCdArray[0];
                    }
                    // 2018/04/11 S21_NA#29010 Add End

                    Set<String> checkLineForDefSlsRep = new HashSet<String>(getSlsRepListForDsOrderType.size());
                    for (int i = 0; i < getSlsRepListForDsOrderType.size(); i++) {
                        Map<String, Object> slsRepMap = getSlsRepListForDsOrderType.get(i);
                        lineBizRoleTpCd = (String) slsRepMap.get("LINE_BIZ_ROLE_TP_CD");
                        checkLineForDefSlsRep.add(lineBizRoleTpCd);
                    }
                    if (checkLineBizRoleTpCd == null || !checkLineBizRoleTpCd.containsAll(checkLineForDefSlsRep)) {
                        // get getSlsRep for PRIM_REP
                        String primRep = (String) query.getPrimRep(param.glblCmpyCd.getValue(), lineBizTpCd);
                        if (ZYPCommonFunc.hasValue(primRep)) {
                            NMZC260001_defSlsRepListPMsg aPMsg = param.defSlsRepList.no(param.defSlsRepList.getValidCount());
                            ZYPEZDItemValueSetter.setValue(aPMsg.lineBizTpCd, lineBizTpCd);
                            // 2018/09/18 S21_NA#29010 Mod Start
//                            ZYPEZDItemValueSetter.setValue(aPMsg.lineBizRoleTpCd, lineBizRoleTpCd);
                            ZYPEZDItemValueSetter.setValue(aPMsg.lineBizRoleTpCd, (String) getSlsRepListForDsOrderType.get(0).get("LINE_BIZ_ROLE_TP_CD"));
                            // 2018/09/18 S21_NA#29010 Mod End
                            ZYPEZDItemValueSetter.setValue(aPMsg.tocCd, tocCd);
                            // Add Start 2017/10/31 QC#20146(Sol#092)
                            ZYPEZDItemValueSetter.setValue(aPMsg.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);
                            // Add End 2017/10/31 QC#20146(Sol#092)
                            ZYPEZDItemValueSetter.setValue(aPMsg.trtyGrpTpCd, trtyGrpTpCd); // 2018/04/11 S21_NA#25418 Add
                            ZYPEZDItemValueSetter.setValue(aPMsg.xxRsltFlg, ZYPConstant.FLG_ON_Y); // 2018/04/11 S21_NA#25418 Add
                            aPMsgIndex = param.defSlsRepList.getValidCount() + 1;
                            param.defSlsRepList.setValidCount(aPMsgIndex);
                        }
                    }
                }
            }
        }

        if (param.defSlsRepList.getValidCount() < 1) {
            // QC#56638 Add Start
            if (isShipBase) {
                msgMap.addXxMsgIdWithPrm(NMZC260001Constant.NWAM0757W, null);
            } else {
                msgMap.addXxMsgIdWithPrm(NMZC260001Constant.NWAM0981W, null);
            }
            // QC#56638 Add End
//            String key = "[shipToCustCd]";
//            String val = S21StringUtil.concatStrings("[", param.shipToCustCd.getValue(), "]");
//            String[] msgPrms3 = {"ACCT_TRTY_ROLE_ASG", key, val};
//            msgMap.addXxMsgIdWithPrm(NMZC260001Constant.NMAM8140W, msgPrms3);

            // Del Start 2017/10/31 QC#20146(Sol#092)
            //return false;
            // Del End 2017/10/31 QC#20146(Sol#092)
        }
        return true;
    }
    // 2016/07/08 QC#11812 Add End

    // Add Start 2017/10/31 QC#20146(Sol#092)
    /**
     * get FM Dummy Rep
     * @param msgMap S21ApiMessageMap
     */
    private void getFMDummyRep(S21ApiMessageMap msgMap) {
        NMZC260001PMsg param = (NMZC260001PMsg) msgMap.getPmsg();

        if (!NMZC260001Constant.XX_MODE_CD_EMSD.equals(param.xxModeCd.getValue())) {
            return;
        }

        List<Map<String, Object>> fMDummyRepList = (List<Map<String, Object>>) query.getFMDummyRep(param);

        if (fMDummyRepList != null) {
            if (fMDummyRepList.size() < 1) {
                msgMap.addXxMsgId(NMZC260001Constant.NMZM0351W);
            } else if (fMDummyRepList.size() == 1) {
                Map<String, Object> fMDummyRepMap = fMDummyRepList.get(0);
                String tocCd = (String) fMDummyRepMap.get("TOC_CD");

                int aPMsgIndex = param.defSlsRepList.getValidCount();

                NMZC260001_defSlsRepListPMsg aPMsg = param.defSlsRepList.no(aPMsgIndex);
                ZYPEZDItemValueSetter.setValue(aPMsg.lineBizTpCd, getLineBizTpCd(param.glblCmpyCd.getValue(), LINE_BIZ_ROLE_TP.EMSD));
                ZYPEZDItemValueSetter.setValue(aPMsg.lineBizRoleTpCd, LINE_BIZ_ROLE_TP.EMSD);
                ZYPEZDItemValueSetter.setValue(aPMsg.tocCd, tocCd);
                ZYPEZDItemValueSetter.setValue(aPMsg.slsCrQuotFlg, ZYPConstant.FLG_OFF_N);

                aPMsgIndex++;
                param.defSlsRepList.setValidCount(aPMsgIndex);
            } else {
                msgMap.addXxMsgId(NMZC260001Constant.NMZM0352W);
            }
        }
    }

    /**
     * getLineBizTpCd
     * @param glblCmpyCd
     * @param lineBizRoleTpCd
     * @return String
     */
    private String getLineBizTpCd(String glblCmpyCd, String lineBizRoleTpCd) {
        LINE_BIZ_ROLE_TPTMsg tmsg = new LINE_BIZ_ROLE_TPTMsg();

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.lineBizRoleTpCd, lineBizRoleTpCd);

        tmsg = (LINE_BIZ_ROLE_TPTMsg) EZDTBLAccessor.findByKey(tmsg);

        return tmsg.lineBizTpCd.getValue();

    }
    // Add End 2017/10/31 QC#20146(Sol#092)

}
