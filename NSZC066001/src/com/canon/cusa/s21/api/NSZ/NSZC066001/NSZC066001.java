/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC066001;

import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.CONST_TRGT_IMG_SPLY_TP;
import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.NSZM0001E;
import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.NSZM0271E;
import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.NSZM0399E;
import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.NSZM0505E;
import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.NSZM0608E;
import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.NSZM0615E;
import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.NSZM0617E;
import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.TERM_COND_CAP_BW_ORG_ATTRB_NM;
import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.TERM_COND_CAP_CLR_ORG_ATTRB_NM;
import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.TERM_COND_CAP_LENGTH;
import static com.canon.cusa.s21.api.NSZ.NSZC066001.constant.NSZC066001Constant.TERM_COND_CAP_TOT_ORG_ATTRB_NM;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.HLDTMsg;
import business.db.SVC_SPLY_ABUSE_STAGETMsg;
import business.parts.NSZC078001PMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC078001.NSZC078001;
import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_COLOR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Supply Enforcement Validation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Hitachi         J.Kim           Create
 * 03/15/2016   Hitachi         K.Kasai         Update          QC#5282
 * 03/16/2016   Hitachi         A.Kohinata      Update          QC#5540
 * 03/17/2016   Hitachi         A.Kohinata      Update          QC#5647
 * 03/28/2016   Hitachi         K.Kasai         Update          QC#6113
 * 03/30/2016   Hitachi         K.Kasai         Update          QC#6272
 * 04/07/2016   Hitachi         K.Kasai         Update          QC#6593
 * 09/28/2016   Hitachi         A.Kohinata      Update          QC#12898
 * 10/19/2016   Hitachi         A.Kohinata      Update          QC#15344
 * 02/20/2018   CITS            M.Naito         Update          QC#23497
 * 2018/08/14   Hitachi         K.Kojima        Update          QC#27604
 * 08/16/2018   CITS            M.Naito         Update          QC#27250
 * 2018/10/01   Hitachi         T.Tomita        Update          QC#28478
 * 2018/10/31   Fujitsu         M.Yamada        Update          QC#28954
 * 2018/11/21   Hitachi         K.Kitachi       Update          QC#29197
 * 2019/02/18   Hitachi         S.Kitamura      Update          QC#30339
 * 2019/02/20   Hitachi         S.Kitamura      Update          QC#30431
 * </pre>
 */
public class NSZC066001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /** onBatchType */
    private ONBATCH_TYPE onBatchTp = null;

    /**
     * Constructor
     */
    public NSZC066001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NWXC005001ValidationBean
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;

        NWXC005001PMsg pMsg = param.getInputPMsg();

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        if (checkParameter(msgMap, param)) {
            doProcess(msgMap, param);
        }
        msgMap.flush();
    }

    /**
     * checkParameter
     * @param msgMap S21ApiMessageMap
     * @param param NWXC005001ValidationBean
     * @return
     */
    private boolean checkParameter(S21ApiMessageMap msgMap, NWXC005001ValidationBean param) {
        NWXC005001PMsg pMsg = (NWXC005001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
            return false;
        }

        HLDTMsg condition = new HLDTMsg();
        condition.setSQLID("015");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("hldRsnCd01", HLD_RSN.SUPPLY_ENFORCEMENT_HOLD);
        condition.setConditionValue("cpoOrdNum01", param.getInputPMsg().cpoOrdNum_I.getValue());
        condition.setConditionValue("cpoDtlLineNum01", param.getInputPMsg().cpoDtlLineNum_I.getValue());
        condition.setConditionValue("cpoDtlLineSubNum01", param.getInputPMsg().cpoDtlLineSubNum_I.getValue());
        condition.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);
        int count = EZDTBLAccessor.count(condition);
        if (count > 0) {
            return false;
        }

        // CPO_DTLTMsg
        CPO_DTLTMsg cpoDtlTMsg = param.getCdTMsg();
        // START 2016/09/28 A.Kohinata [QC#12898, MOD]
        if (cpoDtlTMsg == null) {
            msgMap.addXxMsgId(NSZM0608E);
            return false;
        }
        if (!hasValue(cpoDtlTMsg.dsContrNum)) {
            msgMap.addXxMsgId(NSZM0271E);
            return false;
        }
        if (isFleetContract(pMsg.glblCmpyCd.getValue(), cpoDtlTMsg.dsContrNum.getValue())) {
            return false;
        }
        if (!hasValue(cpoDtlTMsg.svcMachMstrPk)) {
            msgMap.addXxMsgId(NSZM0615E);
            return false;
        }
        // END 2016/09/28 A.Kohinata [QC#12898, MOD]
        return true;
    }

    /**
     * doProcess
     * @param msgMap S21ApiMessageMap
     * @param param NWXC005001ValidationBean
     */
    private void doProcess(S21ApiMessageMap msgMap, NWXC005001ValidationBean param) {

        NWXC005001PMsg pMsg = (NWXC005001PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();

        CPOTMsg cpoTMsg = param.getCTMsg();
        String cpoOrTd = cpoTMsg.cpoOrdTs.getValue();

        CPO_DTLTMsg cpoDtlTMsg = param.getCdTMsg();
//        String mdseCd = cpoDtlTMsg.mdseCd.getValue();
//        BigDecimal ordQty = cpoDtlTMsg.ordQty.getValue();
        BigDecimal svcMachMstrPk = cpoDtlTMsg.svcMachMstrPk.getValue();

        // Search DS_CONTR, DS_CONTR_DTL
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", glblCmpyCd);
        inParam.put("svcMachMstrPk", svcMachMstrPk);
        inParam.put("cpoOrdTs", cpoOrTd);
        // add start 2016/10/19 CSA Defect#15344
        inParam.put("dsContrCatgWty", DS_CONTR_CATG.WARRANTY);
        // add end 2016/10/19 CSA Defect#15344

        // START 2018/08/16 M.Naito [QC#27250, MOD]
//        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getDsContrDtlInfo", inParam);
        List<Map<String, Object>> contrDtlList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDsContrDtlInfo", inParam);
//        if (result == null) {
        if (contrDtlList == null) {
            msgMap.addXxMsgId(NSZM0505E);
            return;
        }

        // check contract supply inclusive
        // START 2019/02/20 S.Kitamura [QC#30431,ADD]
        List<Map<String, Object>> suplInclContrList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> capDsContrDtlKeyList = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> nonCapDsContrDtlKeyList = new ArrayList<Map<String,Object>>();
        // END 2019/02/20 S.Kitamura [QC#30431,ADD]
        for (Map<String, Object> contrDtlInfo : contrDtlList) {
            NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
            CovTmplInfo tmplInfo = new CovTmplInfo();
            tmplInfo.setGlblCmpyCd(glblCmpyCd);
            tmplInfo.setSlsDt(slsDt);
            tmplInfo.setSvcPgmMdseCd((String) contrDtlInfo.get("SVC_PGM_MDSE_CD"));
            tmplInfo.setDsContrPk((BigDecimal) contrDtlInfo.get("DS_CONTR_PK")); // QC#28954
            boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);
            if (isSuplIncl) {
            // START 2019/02/20 S.Kitamura [QC#30431,ADD]
                // suplInclContrList.add(contrDtlInfo);
                if (setCapToner(glblCmpyCd, contrDtlInfo, slsDt)){
                    capDsContrDtlKeyList.add(contrDtlInfo);
                } else {
                    nonCapDsContrDtlKeyList.add(contrDtlInfo);
                }
            }
        }
        suplInclContrList = capDsContrDtlKeyList;
        suplInclContrList.addAll(nonCapDsContrDtlKeyList);
        // if (suplInclContrList == null) {
        if (suplInclContrList.size() == 0) {
        // END 2019/02/20 S.Kitamura [QC#30431,ADD]
            msgMap.addXxMsgId(NSZM0617E);
            return;
        }
        // END 2018/08/16 M.Naito [QC#27250, MOD]

        // START 2018/08/14 K.Kojima [QC#27604,ADD]
        List<Map<String, Object>> cpoTonerOrdQtyList = getCpoTonerOrdQty(glblCmpyCd, cpoTMsg.cpoOrdNum.getValue(), svcMachMstrPk);
        BigDecimal bwOrdQty = getBwOrdQty(cpoTonerOrdQtyList);
        BigDecimal clrOrdQty = getClrOrdQty(cpoTonerOrdQtyList);
        BigDecimal totOrdQty = getTotOrdQty(cpoTonerOrdQtyList);
        // END 2018/08/14 K.Kojima [QC#27604,ADD]

        //get MDSE info
//        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);

        // START 2018/08/16 M.Naito [QC#27250, MOD]
        // mod start 2016/03/30 CSA Defect#6272
        boolean outSideHoldFlg = true;
        boolean allOutSideHoldFlg = false;
//        String colorTpCd = mdseTMsg.imgSplyColorTpCd.getValue();
        // START 2018/11/21 K.Kitachi [QC#29197, ADD]
        int skipCount = 0;
        // END 2018/11/21 K.Kitachi [QC#29197, ADD]

        for (Map<String, Object> result : suplInclContrList) {
            // START 2018/11/21 K.Kitachi [QC#29197, ADD]
            if (!ZYPConstant.FLG_ON_Y.equals((String) result.get("USG_CHRG_FLG"))) {
                skipCount++;
                continue;
            }
            // END 2018/11/21 K.Kitachi [QC#29197, ADD]
            // Supply Order Check
            BigDecimal resultCnt = supplyOrderCheck(msgMap, param, result);
            if (resultCnt.compareTo(BigDecimal.ZERO) == 0) {
                Map<String, Object> ssasResult = getSvcSplyAbuseStage(glblCmpyCd, result);
                if (ssasResult == null) {
                    insertSvcSplyAbuseStage(msgMap, glblCmpyCd, slsDt, svcMachMstrPk, (BigDecimal) result.get("DS_CONTR_PK")); // QC#28954
                }
                outSideHoldFlg = true;
                // Term & Condition
                if (setCapToner(glblCmpyCd, result, slsDt)) {
                    Map<String, Object> stcaResult = setCapTonerUpperLimit(glblCmpyCd, result, slsDt);
                    Map<String, Object> ssmuResult = getSvcSplyMachUsed(glblCmpyCd, result, svcMachMstrPk);

                    // Check Cap Toner UpperLimit
                    if (stcaResult == null) {
                        outSideHoldFlg = false;
                    }

                    // mod start 2018/02/20 CSA Defect#23497
//                    outSideHoldFlg = checkCapTonerRunning(stcaResult, ssmuResult, ordQty, colorTpCd);
                    // START 2018/08/15 K.Kojima [QC#27604,MOD]
                    // outSideHoldFlg = checkCapTonerOriginal(stcaResult, ssmuResult, ordQty, colorTpCd);
                    outSideHoldFlg = checkCapTonerOriginal(stcaResult, ssmuResult, bwOrdQty, clrOrdQty, totOrdQty);
                    // END 2018/08/15 K.Kojima [QC#27604,MOD]
                    // mod end 2018/02/20 CSA Defect#23497
                    if (!outSideHoldFlg) {
                        Map<String, Object> soResult = getSvcSplyAbuseStage(glblCmpyCd, result);
                        // START 2019/02/18 S.Kitamura [QC#30339,MOD]
                        if (soResult != null && hasValue((BigDecimal) soResult.get("SVC_SPLY_ABUSE_STAGE_PK"))) {
                            updateSvcSplyAbuseStage(msgMap, glblCmpyCd, (BigDecimal) soResult.get("SVC_SPLY_ABUSE_STAGE_PK"));
                        }
                        // END 2019/02/18 S.Kitamura [QC#30339,MOD]
                        outSideHoldFlg = false;
                    }

                }
            } else {
                Map<String, Object> ssasResult = getSvcSplyAbuseStage(glblCmpyCd, result);

                if (ssasResult == null) {
                    insertSvcSplyAbuseStage(msgMap, glblCmpyCd, slsDt, svcMachMstrPk, (BigDecimal) result.get("DS_CONTR_PK")); // QC#28954
                    Map<String, Object> soResult = getSvcSplyAbuseStage(glblCmpyCd, result);
                    // START 2019/02/18 S.Kitamura [QC#30339,MOD]
                    if (soResult != null && hasValue((BigDecimal) soResult.get("SVC_SPLY_ABUSE_STAGE_PK"))) {
                        updateSvcSplyAbuseStage(msgMap, glblCmpyCd, (BigDecimal) soResult.get("SVC_SPLY_ABUSE_STAGE_PK"));
                    }
                    // END 2019/02/18 S.Kitamura [QC#30339,MOD]
                    outSideHoldFlg = false;
                } else {

                    String abuseFlg = (String) ssasResult.get("ABUSE_FLG");
                    // add start 2016/03/28 CSA Defect#6113
                    String ovwrtAbuseFlg = (String) ssasResult.get("OVWRT_ABUSE_FLG");
                    // add end 2016/03/28 CSA Defect#6113
                    BigDecimal svcSplyAbuseStagePk = (BigDecimal) ssasResult.get("SVC_SPLY_ABUSE_STAGE_PK");

                    // Check Watch List(ABUSE_FLG)
                    // mod start 2016/03/28 CSA Defect#6113
                    if (ZYPConstant.FLG_ON_Y.equals(ovwrtAbuseFlg)) {
                        outSideHoldFlg = true;
                    } else if (ZYPConstant.FLG_ON_Y.equals(abuseFlg)) {
                    // mod end 2016/03/28 CSA Defect#6113
                        outSideHoldFlg = false;
                    // mod start 2016/03/28 CSA Defect#6113
                    } else if (ZYPConstant.FLG_OFF_N.equals(abuseFlg)) {
                    // mod end 2016/03/28 CSA Defect#6113
                        // Term & Condition
                        if (setCapToner(glblCmpyCd, result, slsDt)) {
                            Map<String, Object> stcaResult = setCapTonerUpperLimit(glblCmpyCd, result, slsDt);
                            Map<String, Object> ssmuResult = getSvcSplyMachUsed(glblCmpyCd, result, svcMachMstrPk);

                            // Check Cap Toner UpperLimit
                            // mod start 2016/04/01 CSA Defect#6272
                            if (stcaResult == null) {
                                outSideHoldFlg = false;
                            }
                            // mod end 2016/04/01 CSA Defect#6272

                            // mod start 2018/02/20 CSA Defect#23497
//                            outSideHoldFlg = checkCapTonerRunning(stcaResult, ssmuResult, ordQty, colorTpCd);
                            // START 2018/08/15 K.Kojima [QC#27604,MOD]
                            // outSideHoldFlg = checkCapTonerOriginal(stcaResult, ssmuResult, ordQty, colorTpCd);
                            outSideHoldFlg = checkCapTonerOriginal(stcaResult, ssmuResult, bwOrdQty, clrOrdQty, totOrdQty);
                            // END 2018/08/15 K.Kojima [QC#27604,MOD]
                            // mod end 2018/02/20 CSA Defect#23497
                            if (!outSideHoldFlg) {
                                updateSvcSplyAbuseStage(msgMap, glblCmpyCd, svcSplyAbuseStagePk);
                            }

                        } else {

                            // Check Watch List
                            // Mod Start 2018/10/01 QC#28478
                            // String abResult = reCalculationBucket(glblCmpyCd, colorTpCd, result, svcMachMstrPk, cpoDtlTMsg);
                            String abResult = reCalculationBucket(glblCmpyCd, totOrdQty, result, svcMachMstrPk, cpoDtlTMsg);
                            // Mod End 2018/10/01 QC#28478
                            if (ZYPCommonFunc.hasValue(abResult)) {
//                            if (abResult == null) {
//                                return;
//                            }

                                if (ZYPConstant.FLG_ON_Y.equals(abResult)) {
                                    updateSvcSplyAbuseStage(msgMap, glblCmpyCd, svcSplyAbuseStagePk);
                                    outSideHoldFlg = false;
                                } else if (ZYPConstant.FLG_OFF_N.equals(abResult)) {
                                    outSideHoldFlg = true;
                                }
                            }
                        }
                    }
                }
            }
            // mod end 2016/03/30 CSA Defect#6272
            if (outSideHoldFlg) {
                allOutSideHoldFlg = true;
                break;
            }
        }

        // if (outSideHoldFlg) {
        if (allOutSideHoldFlg) {
            setOutputOutSideHoldInfo(param);
        // START 2018/11/21 K.Kitachi [QC#29197, ADD]
        } else if (skipCount > 0 && skipCount == suplInclContrList.size()) {
            setOutputOutSideHoldInfo(param);
        // END 2018/11/21 K.Kitachi [QC#29197, ADD]
        } else {
            setOutputHoldInfo(param);
        }
        // END 2018/08/16 M.Naito [QC#27250, MOD]
    }

    // mod start 2018/02/20 CSA Defect#23497
//    private boolean checkCapTonerRunning(Map<String, Object> stcaResult, Map<String, Object> ssmuResult, BigDecimal ordQty, String colorTpCd) {
    // START 2018/08/14 K.Kojima [QC#27604,MOD]
    // private boolean checkCapTonerOriginal(Map<String, Object> stcaResult, Map<String, Object> ssmuResult, BigDecimal ordQty, String colorTpCd) {
    private boolean checkCapTonerOriginal(Map<String, Object> stcaResult, Map<String, Object> ssmuResult, BigDecimal bwOrdQty, BigDecimal clrOrdQty, BigDecimal totOrdQty) {
    // END 2018/08/14 K.Kojima [QC#27604,MOD]

        // mod start 2016/04/01 CSA Defect#6272
        BigDecimal bwUsedQty = BigDecimal.ZERO;
        BigDecimal colorUsedQty = BigDecimal.ZERO;
        if (ssmuResult != null) {
            bwUsedQty = (BigDecimal) ssmuResult.get("BW_USED_QTY");
            colorUsedQty = (BigDecimal) ssmuResult.get("COLOR_USED_QTY");
        }
        // mod end 2016/04/01 CSA Defect#6272

//        boolean outSideHoldFlg = false;
        boolean outSideHoldFlg = true;
        // Cap - Total Toner Original
//        if (stcaResult.get(TERM_COND_CAP_TOT_RUN_ATTRB_NM) != null) {
//            if ((ordQty.add(bwUsedQty).add(colorUsedQty)).compareTo((BigDecimal) stcaResult.get(TERM_COND_CAP_TOT_RUN_ATTRB_NM)) > 0) {
        if (stcaResult.get(TERM_COND_CAP_TOT_ORG_ATTRB_NM) != null) {
            // START 2018/08/14 K.Kojima [QC#27604,MOD]
            // if ((ordQty.add(bwUsedQty).add(colorUsedQty)).compareTo((BigDecimal) stcaResult.get(TERM_COND_CAP_TOT_ORG_ATTRB_NM)) > 0) {
            if ((totOrdQty.add(bwUsedQty).add(colorUsedQty)).compareTo((BigDecimal) stcaResult.get(TERM_COND_CAP_TOT_ORG_ATTRB_NM)) > 0) {
            // END 2018/08/14 K.Kojima [QC#27604,MOD]
                outSideHoldFlg = false;
//            } else {
//                outSideHoldFlg = true;
            }
        } else {

//            // Cap - B/W Toner Running
//            boolean checkFlg = false;
//            if (stcaResult.get(TERM_COND_CAP_BW_RUN_ATTRB_NM) != null) {
//                if (IMG_SPLY_COLOR_TP.BLACK.equals(colorTpCd)) {
//                    if ((bwUsedQty.add(ordQty)).compareTo((BigDecimal) stcaResult.get(TERM_COND_CAP_BW_RUN_ATTRB_NM)) > 0) {
//                        outSideHoldFlg = false;
//                    } else {
//                        checkFlg = true;
//                    }
//                } else {
//                    checkFlg = true;
//                }
//            }
//
//            // Cap - Color Toner Running
//            if (checkFlg || (!checkFlg && stcaResult.get(TERM_COND_CAP_CLR_RUN_ATTRB_NM) != null)) {
//                if (!IMG_SPLY_COLOR_TP.BLACK.equals(colorTpCd) && !getColor(colorTpCd)) {
//                    outSideHoldFlg = true;
//                } else {
//                    if ((colorUsedQty.add(ordQty)).compareTo((BigDecimal) stcaResult.get(TERM_COND_CAP_CLR_RUN_ATTRB_NM)) > 0) {
//                        outSideHoldFlg = false;
//                    } else {
//                        outSideHoldFlg = true;
//                    }
//                }
//            }

            // Cap - B/W Toner Original
            // START 2018/08/15 K.Kojima [QC#27604,MOD]
            // if (stcaResult.get(TERM_COND_CAP_BW_ORG_ATTRB_NM) != null && IMG_SPLY_COLOR_TP.BLACK.equals(colorTpCd)) {
            if (stcaResult.get(TERM_COND_CAP_BW_ORG_ATTRB_NM) != null && bwOrdQty.compareTo(BigDecimal.ZERO) > 0) {
            // END 2018/08/15 K.Kojima [QC#27604,MOD]
                // START 2018/08/15 K.Kojima [QC#27604,MOD]
                // if ((bwUsedQty.add(ordQty)).compareTo((BigDecimal) stcaResult.get(TERM_COND_CAP_BW_ORG_ATTRB_NM)) > 0) {
                if ((bwUsedQty.add(bwOrdQty)).compareTo((BigDecimal) stcaResult.get(TERM_COND_CAP_BW_ORG_ATTRB_NM)) > 0) {
                // END 2018/08/15 K.Kojima [QC#27604,MOD]
                    outSideHoldFlg = false;
                }
            }

            // Cap - Color Toner Original
            // START 2018/08/15 K.Kojima [QC#27604,MOD]
            // if (stcaResult.get(TERM_COND_CAP_CLR_ORG_ATTRB_NM) != null && !IMG_SPLY_COLOR_TP.BLACK.equals(colorTpCd) && getColor(colorTpCd)) {
            if (stcaResult.get(TERM_COND_CAP_CLR_ORG_ATTRB_NM) != null && clrOrdQty.compareTo(BigDecimal.ZERO) > 0) {
            // END 2018/08/15 K.Kojima [QC#27604,MOD]
                // START 2018/08/15 K.Kojima [QC#27604,MOD]
                // if ((colorUsedQty.add(ordQty)).compareTo((BigDecimal) stcaResult.get(TERM_COND_CAP_CLR_ORG_ATTRB_NM)) > 0) {
                if ((colorUsedQty.add(clrOrdQty)).compareTo((BigDecimal) stcaResult.get(TERM_COND_CAP_CLR_ORG_ATTRB_NM)) > 0) {
                // END 2018/08/15 K.Kojima [QC#27604,MOD]
                    outSideHoldFlg = false;
                }
            }
        }
        return outSideHoldFlg;
    }
    // mod end 2018/02/20 CSA Defect#23497

    private Map<String, Object> getSvcSplyAbuseStage(String glblCmpyCd, Map<String, Object> result) {

        Map<String, Object> soInParam = new HashMap<String, Object>();
        soInParam.put("glblCmpyCd", glblCmpyCd);
        soInParam.put("dsContrPk", (BigDecimal) result.get("DS_CONTR_PK"));
        soInParam.put("svcPgmMdseCd", (String) result.get("SVC_PGM_MDSE_CD"));

        return (Map<String, Object>) ssmBatchClient.queryObject("getSvcSplyAbuseStage", soInParam);
    }

    private Map<String, Object> getSvcSplyMachUsed(String glblCmpyCd, Map<String, Object> result, BigDecimal stcaAttrbPk) {

        Map<String, Object> ssmuInParam = new HashMap<String, Object>();
        ssmuInParam.put("glblCmpyCd", glblCmpyCd);
        ssmuInParam.put("dsContrPk", (BigDecimal) result.get("DS_CONTR_PK"));
        ssmuInParam.put("svcPgmMdseCd", (String) result.get("SVC_PGM_MDSE_CD"));
        ssmuInParam.put("svcMachMstrPk", stcaAttrbPk);

        return (Map<String, Object>) ssmBatchClient.queryObject("getSvcSplyMachUsedInfo", ssmuInParam);
    }

    // Add Start 2018/10/01 QC#28478
    private Map<String, Object> getSvcSplyMachAllw(String glblCmpyCd, Map<String, Object> result, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmuInParam = new HashMap<String, Object>();
        ssmuInParam.put("glblCmpyCd", glblCmpyCd);
        ssmuInParam.put("dsContrPk", (BigDecimal) result.get("DS_CONTR_PK"));
        ssmuInParam.put("svcPgmMdseCd", (String) result.get("SVC_PGM_MDSE_CD"));
        ssmuInParam.put("svcMachMstrPk", svcMachMstrPk);

        return (Map<String, Object>) ssmBatchClient.queryObject("getSvcSplyMachAllw", ssmuInParam);
    }
    // Add End 2018/10/01 QC#28478

    // Mod Start 2018/10/01 QC#28478
//    private String reCalculationBucket(String glblCmpyCd, String colorTpCd, Map<String, Object> result, BigDecimal stcaAttrbPk, CPO_DTLTMsg cpoDtlTMsg) {
    private String reCalculationBucket(String glblCmpyCd, BigDecimal totOrdQty, Map<String, Object> result, BigDecimal stcaAttrbPk, CPO_DTLTMsg cpoDtlTMsg) {
    // Mod End 2018/10/01 QC#28478
        // SVC_SPLY_MACH_USED
        Map<String, Object> ssmuResult = getSvcSplyMachUsed(glblCmpyCd, result, stcaAttrbPk);
        if (ssmuResult == null) {
            return null;
        }

        // Mod Start 2018/10/01 QC#28478
        // Re-Calculation QTY
        BigDecimal qtyTotal = totOrdQty.add((BigDecimal) ssmuResult.get("BW_USED_QTY")).add((BigDecimal) ssmuResult.get("COLOR_USED_QTY"));
//        BigDecimal ordQty = cpoDtlTMsg.ordQty.getValue();
//        if (IMG_SPLY_COLOR_TP.BLACK.equals(colorTpCd)) {
//            qtyTotal = ordQty.add((BigDecimal) ssmuResult.get("BW_USED_QTY"));
//        } else {
//            qtyTotal = ordQty.add((BigDecimal) ssmuResult.get("COLOR_USED_QTY"));
//        }

        BigDecimal allwQty = BigDecimal.ZERO;
        Map<String, Object> allwMap = getSvcSplyMachAllw(glblCmpyCd, result, stcaAttrbPk);
        if (allwMap != null) {
            allwQty = (BigDecimal) allwMap.get("BW_ALLW_QTY");
            allwQty = allwQty.add((BigDecimal) allwMap.get("COLOR_ALLW_QTY"));
        }

        BigDecimal qtyPct = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(allwQty) && BigDecimal.ZERO.compareTo(allwQty) < 0) {
            qtyPct = qtyTotal.multiply(new BigDecimal(100)).divide(allwQty, 5, BigDecimal.ROUND_UP);
        }
        // Mod End 2018/10/01 QC#28478

        // Check Bucket ABUSE_FLG
        Map<String, Object> abInParam = new HashMap<String, Object>();
        abInParam.put("glblCmpyCd", glblCmpyCd);
        // Mod Start 2018/10/01 QC#28478
//        abInParam.put("bcktFromQty", qtyTotal);
//        abInParam.put("bcktToQty", qtyTotal);
        abInParam.put("bcktFromQty", qtyPct);
        abInParam.put("bcktToQty", qtyPct);
        // Mod End 2018/10/01 QC#28478

        return (String) ssmBatchClient.queryObject("getAbuseBcktInfo", abInParam);
    }

    private boolean getColor(String colorTpCd) {

        if (IMG_SPLY_COLOR_TP.YELLOW.equals(colorTpCd) || IMG_SPLY_COLOR_TP.MAGENTA.equals(colorTpCd) || IMG_SPLY_COLOR_TP.CYAN.equals(colorTpCd) || IMG_SPLY_COLOR_TP.CLEAR.equals(colorTpCd)) {
            return true;
        }
        return false;
    }

    private Map<String, Object> setCapTonerUpperLimit(String glblCmpyCd, Map<String, Object> result, String slsDt) {

        // mod start 2018/02/20 CSA Defect#23497
        String[] tonerOriginalNm = new String[TERM_COND_CAP_LENGTH];
//        // Cap - Cap - Total Toner Running
//        tonerRunningNm[0] = TERM_COND_CAP_TOT_RUN_ATTRB_NM;
//        // Cap - B/W Toner Running
//        tonerRunningNm[1] = TERM_COND_CAP_BW_RUN_ATTRB_NM;
//        // Cap - Color Toner Running
//        tonerRunningNm[2] = TERM_COND_CAP_CLR_RUN_ATTRB_NM;
        // Cap - Cap - Total Toner Original
        tonerOriginalNm[0] = TERM_COND_CAP_TOT_ORG_ATTRB_NM;
        // Cap - B/W Toner Original
        tonerOriginalNm[1] = TERM_COND_CAP_BW_ORG_ATTRB_NM;
        // Cap - Color Toner Original
        tonerOriginalNm[2] = TERM_COND_CAP_CLR_ORG_ATTRB_NM;

        Map<String, Object> termRunAttrbNm = new HashMap<String, Object>();
//        for (int i = 0; i < tonerRunningNm.length; i++) {
        for (int i = 0; i < tonerOriginalNm.length; i++) {
            // add start 2016/03/28 CSA Defect#6113
//            String capRunningAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(tonerRunningNm[i], glblCmpyCd);
            String capOriginalAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(tonerOriginalNm[i], glblCmpyCd);
            // add end 2016/03/28 CSA Defect#6113
            // SVC_TERM_COND_ATTRB
            Map<String, Object> stcaInParam = new HashMap<String, Object>();
            stcaInParam.put("glblCmpyCd", glblCmpyCd);
            // mod start 2016/03/28 CSA Defect#6113
//            stcaInParam.put("capBwOrgAttrbNm", tonerRunningNm[i]);
//            stcaInParam.put("capBwOrgAttrbNm", capRunningAttrbNm);
            stcaInParam.put("capBwOrgAttrbNm", capOriginalAttrbNm);
            // mod end 2016/03/28 CSA Defect#6113
            stcaInParam.put("salseDate", slsDt);

            Map<String, Object> stcaResult = (Map<String, Object>) ssmBatchClient.queryObject("geSvcTermCondAttrbInfo", stcaInParam);
            if (stcaResult == null) {
                continue;
            }

            // SVC_TERM_COND
            Map<String, Object> stcInParam = new HashMap<String, Object>();
            stcInParam.put("glblCmpyCd", glblCmpyCd);
            stcInParam.put("dsContrPk", (BigDecimal) result.get("DS_CONTR_PK"));
            stcInParam.put("dsContrDtlPk", (BigDecimal) result.get("DS_CONTR_DTL_PK"));
            stcInParam.put("svcPgmMdseCd", (BigDecimal) stcaResult.get("SVC_TERM_COND_ATTRB_PK"));

            String stcResult = (String) ssmBatchClient.queryObject("getSvcTermCondInfo", stcInParam);
            if (stcResult == null) {
                continue;
            }

            if (i == 0) {
//                termRunAttrbNm.put(TERM_COND_CAP_TOT_RUN_ATTRB_NM, new BigDecimal(stcResult));
                termRunAttrbNm.put(TERM_COND_CAP_TOT_ORG_ATTRB_NM, new BigDecimal(stcResult));
            } else if (i == 1) {
//                termRunAttrbNm.put(TERM_COND_CAP_BW_RUN_ATTRB_NM, new BigDecimal(stcResult));
                termRunAttrbNm.put(TERM_COND_CAP_BW_ORG_ATTRB_NM, new BigDecimal(stcResult));
            } else {
//                termRunAttrbNm.put(TERM_COND_CAP_CLR_RUN_ATTRB_NM, new BigDecimal(stcResult));
                termRunAttrbNm.put(TERM_COND_CAP_CLR_ORG_ATTRB_NM, new BigDecimal(stcResult));
            }
        }
        return termRunAttrbNm;
        // mod end 2018/02/20 CSA Defect#23497
    }

    private boolean setCapToner(String glblCmpyCd, Map<String, Object> result, String slsDt) {

        String[] tonerOriginal = new String[TERM_COND_CAP_LENGTH];
        // Cap - B/W Toner Original
        tonerOriginal[0] = TERM_COND_CAP_BW_ORG_ATTRB_NM;
        // Cap - Color Toner Original
        tonerOriginal[1] = TERM_COND_CAP_CLR_ORG_ATTRB_NM;
        // Cap - Total Toner Original
        tonerOriginal[2] = TERM_COND_CAP_TOT_ORG_ATTRB_NM;

        boolean existFlg = false;
        for (int i = 0; i < tonerOriginal.length; i++) {
            String capBwOrgAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(tonerOriginal[i], glblCmpyCd);
            // SVC_TERM_COND_ATTRB
            Map<String, Object> stcaInParam = new HashMap<String, Object>();
            stcaInParam.put("glblCmpyCd", glblCmpyCd);
            stcaInParam.put("capBwOrgAttrbNm", capBwOrgAttrbNm);
            stcaInParam.put("salseDate", slsDt);

            Map<String, Object> stcaResult = (Map<String, Object>) ssmBatchClient.queryObject("geSvcTermCondAttrbInfo", stcaInParam);
            if (stcaResult == null) {
                continue;
            }

            // SVC_TERM_COND
            Map<String, Object> stcInParam = new HashMap<String, Object>();
            stcInParam.put("glblCmpyCd", glblCmpyCd);
            stcInParam.put("dsContrPk", (BigDecimal) result.get("DS_CONTR_PK"));
            stcInParam.put("dsContrDtlPk", (BigDecimal) result.get("DS_CONTR_DTL_PK"));
            stcInParam.put("svcPgmMdseCd", (BigDecimal) stcaResult.get("SVC_TERM_COND_ATTRB_PK"));

            String stcResult = (String) ssmBatchClient.queryObject("getSvcTermCondInfo", stcInParam);
            if (stcResult != null) {
                existFlg = true;
            }
        }
        return existFlg;
    }

    /**
     * Check Supply Order
     * @param msgMap S21ApiMessageMap
     * @param param NWXC005001ValidationBean
     * @param result Map<String, Object>
     * @return BigDecimal
     */
    private BigDecimal supplyOrderCheck(S21ApiMessageMap msgMap, NWXC005001ValidationBean param, Map<String, Object> result) {

        NWXC005001PMsg pMsg = (NWXC005001PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String cpoOrdTpNum = pMsg.cpoOrdNum_I.getValue();

        CPO_DTLTMsg cpoDtlTMsg = param.getCdTMsg();
        BigDecimal svcMachMstrPk = cpoDtlTMsg.svcMachMstrPk.getValue();

        Map<String, Object> ssasInParam = new HashMap<String, Object>();
        ssasInParam.put("glblCmpyCd", glblCmpyCd);
        ssasInParam.put("cpoOrdNum", cpoOrdTpNum);
        ssasInParam.put("cpoOrdTsFrom", (String) result.get("CONTR_VRSN_EFF_FROM_DT"));
        ssasInParam.put("cpoOrdTsThru", (String) result.get("CONTR_VRSN_EFF_THRU_DT"));
        ssasInParam.put("svcMachMstrPk", svcMachMstrPk);
        // add start 2016/04/07 CSA Defect#6593
        ssasInParam.put("ordHdrSts", ORD_HDR_STS.CANCELLED);
        ssasInParam.put("ordLineSts", ORD_LINE_STS.CANCELLED);
        // add end 2016/04/07 CSA Defect#6593

        BigDecimal resultCnt = (BigDecimal) ssmBatchClient.queryObject("getSupplyOrderInfo", ssasInParam);

        return resultCnt;
    }

    private void insertSvcSplyAbuseStage(S21ApiMessageMap msgMap, String glblCmpyCd, String slsDt, BigDecimal svcMachMstrPk, BigDecimal dsContrPk) {

        NSZC078001PMsg pMsg = new NSZC078001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPkList.no(0).svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPkList.no(0).dsContrPk, dsContrPk); // QC#28954
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(0).xxMsgId, "");
        pMsg.svcMachMstrPkList.setValidCount(1);

        // Call NSZC078001 Supply Abuse Update API
        new NSZC078001().execute(pMsg, this.onBatchTp);

        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            msgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
        }
        return;
    }

    private void setOutputHoldInfo(NWXC005001ValidationBean param) {

        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoOrdNum_O, param.getInputPMsg().cpoOrdNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoDtlLineNum_O, param.getInputPMsg().cpoDtlLineNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoDtlLineSubNum_O, param.getInputPMsg().cpoDtlLineSubNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().hldRsnCd, HLD_RSN.SUPPLY_ENFORCEMENT_HOLD);
    }

    private void updateSvcSplyAbuseStage(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal svcSplyAbuseStagePk) {

        SVC_SPLY_ABUSE_STAGETMsg inTMsg = new SVC_SPLY_ABUSE_STAGETMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.svcSplyAbuseStagePk, svcSplyAbuseStagePk);

        SVC_SPLY_ABUSE_STAGETMsg outTMsg = (SVC_SPLY_ABUSE_STAGETMsg) S21FastTBLAccessor.findByKey(inTMsg);
        ZYPEZDItemValueSetter.setValue(inTMsg.abuseFlg, ZYPConstant.FLG_ON_Y);
        S21FastTBLAccessor.update(outTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSZM0399E, new String[] {"SVC_SPLY_ABUSE_STAGE" });
        }
    }

    private void setOutputOutSideHoldInfo(NWXC005001ValidationBean param) {

        NWXC005001PMsg pMsg = param.getInputPMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum_O, (String) null);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum_O, (String) null);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum_O, (String) null);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum_O, (String) null);
        ZYPEZDItemValueSetter.setValue(pMsg.hldRsnCd, (String) null);
    }

    // START 2016/09/28 A.Kohinata [QC#12898, ADD]
    private boolean isFleetContract(String glblCmpyCd, String dsContrNum) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        dsContrTMsg.setSQLID("003");
        dsContrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsContrTMsg.setConditionValue("dsContrNum01", dsContrNum);
        DS_CONTRTMsgArray dsContrTMsgArray = (DS_CONTRTMsgArray) S21ApiTBLAccessor.findByCondition(dsContrTMsg);
        if (dsContrTMsgArray.getValidCount() > 0 && DS_CONTR_CATG.FLEET.equals(dsContrTMsgArray.no(0).dsContrCatgCd.getValue())) {
            return true;
        }
        return false;
    }
    // END 2016/09/28 A.Kohinata [QC#12898, ADD]

    // START 2018/08/14 K.Kojima [QC#27604,ADD]
    private List<Map<String, Object>> getCpoTonerOrdQty(String glblCmpyCd, String cpoOrdNum, BigDecimal svcMachMstrPk) {
        List<String> trgtImgSplyTpCdList = new ArrayList<String>();
        String trgtImgSplyTpCdCsv = ZYPCodeDataUtil.getVarCharConstValue(CONST_TRGT_IMG_SPLY_TP, glblCmpyCd);
        if (trgtImgSplyTpCdCsv != null) {
            trgtImgSplyTpCdList = Arrays.asList(trgtImgSplyTpCdCsv.split(","));
        }

        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", glblCmpyCd);
        inParam.put("cpoOrdNum", cpoOrdNum);
        inParam.put("svcMachMstrPk", svcMachMstrPk);
        inParam.put("trgtImgSplyTpCdList", trgtImgSplyTpCdList);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getCpoTonerOrdQty", inParam);
    }

    private BigDecimal getBwOrdQty(List<Map<String, Object>> cpoTonerOrdQtyList) {
        BigDecimal bwOrdQty = BigDecimal.ZERO;
        for (Map<String, Object> cpoTonerOrdQty : cpoTonerOrdQtyList) {
            BigDecimal ordQty = (BigDecimal) cpoTonerOrdQty.get("ORD_QTY");
            String blackColorFlg = (String) cpoTonerOrdQty.get("BLACK_COLOR_FLG");
            if (!ZYPCommonFunc.hasValue(ordQty) || !ZYPCommonFunc.hasValue(blackColorFlg)) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(blackColorFlg)) {
                bwOrdQty = bwOrdQty.add(ordQty);
            }
        }
        return bwOrdQty;
    }

    private BigDecimal getClrOrdQty(List<Map<String, Object>> cpoTonerOrdQtyList) {
        BigDecimal clrOrdQty = BigDecimal.ZERO;
        for (Map<String, Object> cpoTonerOrdQty : cpoTonerOrdQtyList) {
            BigDecimal ordQty = (BigDecimal) cpoTonerOrdQty.get("ORD_QTY");
            String blackColorFlg = (String) cpoTonerOrdQty.get("BLACK_COLOR_FLG");
            if (!ZYPCommonFunc.hasValue(ordQty) || !ZYPCommonFunc.hasValue(blackColorFlg)) {
                continue;
            }
            if (ZYPConstant.FLG_OFF_N.equals(blackColorFlg)) {
                clrOrdQty = clrOrdQty.add(ordQty);
            }
        }
        return clrOrdQty;
    }

    private BigDecimal getTotOrdQty(List<Map<String, Object>> cpoTonerOrdQtyList) {
        BigDecimal totOrdQty = BigDecimal.ZERO;
        for (Map<String, Object> cpoTonerOrdQty : cpoTonerOrdQtyList) {
            BigDecimal ordQty = (BigDecimal) cpoTonerOrdQty.get("ORD_QTY");
            if (!ZYPCommonFunc.hasValue(ordQty)) {
                continue;
            }
            totOrdQty = totOrdQty.add(ordQty);
        }
        return totOrdQty;
    }
    // END 2018/08/14 K.Kojima [QC#27604,ADD]
}
