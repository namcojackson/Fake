/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC181001;

import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.NWZM0473E;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.NWZM0482E;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.NWZM0580E;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.NWZM0977E;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.NWZM1253E;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.NWZM1286E;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.NWZM1415E;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.NWZM1443E;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.NWZM1567E;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.NWZM1568E;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.NWZM1948E;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.PROC_MD_ODR_ENT;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.PROC_MD_ODR_IMP;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_MDL_SPLY_RELNTMsg;
import business.db.DS_MDL_SPLY_RELNTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.parts.NWZC181001PMsg;
import business.parts.NWZC181002PMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Auto Add Supply API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         E.Yoshitake     Create          N/A
 * 2016/02/08   Fujitsu         Y.Taoka         Update          QC#1867
 * 2016/03/03   Fujitsu         S.Takami        Update          S21_NA#4661
 * 2016/06/20   Fujitsu         H.Nagashima     Update          QC#10386
 * 2018/02/09   Fujitsu         Y.Kanefusa      Update          S21_NA#23192
 *</pre>
 */
public class NWZC181001 extends S21ApiCommonBase {

    /**
     * Constructor.
     */
    public NWZC181001() {
        super();
    }

    /**
     * Auto Add Supply
     * @param params List<NWZC301001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWZC181001PMsg> params, ONBATCH_TYPE onBatchType) {

        for (NWZC181001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * Execute
     * @param param NWZC181001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC181001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        try {
            // Checking Input value
            if (!checkInput(msgMap)) {
                msgMap.flush();
                return;
            }

            // main
            doProcess(msgMap);

        } finally {
            msgMap.flush();
        }
    }

    /**
     * Check the input parameters. If an error occurs, add a message to the Message Map.
     * @param msgMap Message Map
     * @return Results of the check.(false:error)
     */
    private boolean checkInput(S21ApiMessageMap msgMap) {

        NWZC181001PMsg param = (NWZC181001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0473E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZM0482E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.xxProcMd)) {
            msgMap.addXxMsgId(NWZM0580E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.dsOrdCatgCd)) {
            msgMap.addXxMsgId(NWZM1568E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.dsOrdTpCd)) {
            msgMap.addXxMsgId(NWZM1253E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.mdlId)) {
            msgMap.addXxMsgId(NWZM1567E);
            return false;
        }

        if (!PROC_MD_ODR_ENT.equals(param.xxProcMd.getValue()) && !PROC_MD_ODR_IMP.equals(param.xxProcMd.getValue())) {
            msgMap.addXxMsgId(NWZM0977E);
            return false;
        }

        if (NWZC181001Query.getInstance().getDsOrdCatg(param) == null) {
            msgMap.addXxMsgId(NWZM1415E);
            return false;
        }

        if (NWZC181001Query.getInstance().getDsOrdTp(param) == null) {
            msgMap.addXxMsgId(NWZM1286E);
            return false;
        }

        if (NWZC181001Query.getInstance().getMdlNm(param) == null) {
            msgMap.addXxMsgId(NWZM1443E);
            return false;
        }

        return true;
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {

        NWZC181001PMsg pMsg = (NWZC181001PMsg) msgMap.getPmsg();
        // QC#23192 2018/02/09 Add Start
        List<NWZC181002PMsg> list1 = new ArrayList<NWZC181002PMsg>();
        List<NWZC181002PMsg> list2 = new ArrayList<NWZC181002PMsg>();
        List<NWZC181002PMsg> list3 = new ArrayList<NWZC181002PMsg>();
        // QC#23192 2018/02/09 Add End

        if (!NWZC181001Query.getInstance().isAutoSplyInfo(pMsg)) {
            return;
        }

        DS_MDL_SPLY_RELNTMsgArray dsMdlSplyRelnTMsgArray = NWZC181001Query.getInstance().getSupplyInfo(pMsg);
        if (dsMdlSplyRelnTMsgArray == null) {
            return;
// QC#10386 add Start
        } else if (dsMdlSplyRelnTMsgArray.getValidCount() == 0) {
            msgMap.addXxMsgId(NWZM1948E);
            return;
// QC#10386 add End
        }
        

        int idx = 0;
        for (int count = 0; count < dsMdlSplyRelnTMsgArray.length(); count++) {
            DS_MDL_SPLY_RELNTMsg dsMdlSplyRelnTMsg = dsMdlSplyRelnTMsgArray.no(count);
            String contrTpCd = null;

            if (PROC_MD_ODR_ENT.equals(pMsg.xxProcMd.getValue())) {
                if (ZYPCommonFunc.hasValue(pMsg.dsCpoConfigPk)) {
                    contrTpCd = NWZC181001Query.getInstance().getContractTypeForOdrEnt(pMsg);
                }

                if (ZYPCommonFunc.hasValue(contrTpCd)) {
                    if (dsMdlSplyRelnTMsg.splyInitQty.getValueInt() > 0) {
                        // QC#23192 2018/02/09 Mod Start
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).mdseCd, getSplyItemCd(dsMdlSplyRelnTMsg));
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).dsOrdLineCatgCd, NWZC181001Query.getInstance().getLineCatgForInitSply(pMsg));
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).ordQty, dsMdlSplyRelnTMsg.splyInitQty);
                        //
                        // idx++;
                        // pMsg.NWZC181002PMsg.setValidCount(idx);
                        NWZC181002PMsg line = new NWZC181002PMsg();
                        ZYPEZDItemValueSetter.setValue(line.mdseCd, getSplyItemCd(dsMdlSplyRelnTMsg));
                        ZYPEZDItemValueSetter.setValue(line.dsOrdLineCatgCd, NWZC181001Query.getInstance().getLineCatgForInitSply(pMsg));
                        ZYPEZDItemValueSetter.setValue(line.ordQty, dsMdlSplyRelnTMsg.splyInitQty);
                        list1.add(line);
                        // QC#23192 2018/02/09 Mod End
                    }

                    if (dsMdlSplyRelnTMsg.splyContrQty.getValueInt() > 0) {
                        // 2016/03/03 S21_NA#4661 Mod Start
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).mdseCd, getSplyItemCd(dsMdlSplyRelnTMsg));
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).dsOrdLineCatgCd, NWZC181001Query.getInstance().getLineCatg(pMsg, contrTpCd));
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).ordQty, dsMdlSplyRelnTMsg.splyContrQty);

                        // idx++;
                        // pMsg.NWZC181002PMsg.setValidCount(idx);
                        String dsOrdLineCatgCd = NWZC181001Query.getInstance().getLineCatg(pMsg, contrTpCd);
                        if (null != dsOrdLineCatgCd) {
                            // QC#23192 2018/02/09 Mod Start
                            // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).mdseCd, getSplyItemCd(dsMdlSplyRelnTMsg));
                            // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).dsOrdLineCatgCd, dsOrdLineCatgCd);
                            // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).ordQty, dsMdlSplyRelnTMsg.splyContrQty);
                            //
                            // idx++;
                            // pMsg.NWZC181002PMsg.setValidCount(idx);
                            NWZC181002PMsg line = new NWZC181002PMsg();
                            ZYPEZDItemValueSetter.setValue(line.mdseCd, getSplyItemCd(dsMdlSplyRelnTMsg));
                            ZYPEZDItemValueSetter.setValue(line.dsOrdLineCatgCd, dsOrdLineCatgCd);
                            ZYPEZDItemValueSetter.setValue(line.ordQty, dsMdlSplyRelnTMsg.splyContrQty);
                            list1.add(line);
                            // QC#23192 2018/02/09 Mod End
                        }
                        // 2016/03/03 S21_NA#4661 Mod End
                    }
                } else {
                    if (dsMdlSplyRelnTMsg.splyInitQty.getValueInt() > 0) {
                        // QC#23192 2018/02/09 Mod Start
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).mdseCd, getSplyItemCd(dsMdlSplyRelnTMsg));
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).dsOrdLineCatgCd, NWZC181001Query.getInstance().getLineCatgForInitSply(pMsg));
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).ordQty, dsMdlSplyRelnTMsg.splyInitQty);
                        //
                        // idx++;
                        // pMsg.NWZC181002PMsg.setValidCount(idx);
                        NWZC181002PMsg line = new NWZC181002PMsg();
                        ZYPEZDItemValueSetter.setValue(line.mdseCd, getSplyItemCd(dsMdlSplyRelnTMsg));
                        ZYPEZDItemValueSetter.setValue(line.dsOrdLineCatgCd, NWZC181001Query.getInstance().getLineCatgForInitSply(pMsg));
                        ZYPEZDItemValueSetter.setValue(line.ordQty, dsMdlSplyRelnTMsg.splyInitQty);
                        list1.add(line);
                        // QC#23192 2018/02/09 Mod End
                    }
                }
                // QC#23192 2018/02/09 Add Start
                MDSETMsg mdse = null;
                for (NWZC181002PMsg line : list1) {
                    list2.add(line);
                    mdse = getMdse(pMsg.glblCmpyCd.getValue(), line.mdseCd.getValue());
                    if (mdse != null && S21StringUtil.isEquals(mdse.mdseTpCd.getValue(), MDSE_TP.SET)) {
                        List<Map<String, Object>> childMdseList = getChildMdse(pMsg, mdse.mdseCd.getValue());
                        if (childMdseList.size() > 0) {
                            for (Map<String, Object> childMdse : childMdseList) {
                                NWZC181002PMsg childline = new NWZC181002PMsg();
                                ZYPEZDItemValueSetter.setValue(childline.mdseCd, (String) childMdse.get("MDSE_CD"));
                                ZYPEZDItemValueSetter.setValue(childline.prntMdseCd, line.mdseCd);
                                ZYPEZDItemValueSetter.setValue(childline.dsOrdLineCatgCd, line.dsOrdLineCatgCd);
                                BigDecimal lineQty = line.ordQty.getValue();
                                ZYPEZDItemValueSetter.setValue(childline.ordQty, lineQty.multiply((BigDecimal) childMdse.get("CHILD_MDSE_QTY")));
                                ZYPEZDItemValueSetter.setValue(childline.childMdseQty, (BigDecimal) childMdse.get("CHILD_MDSE_QTY"));
                                list2.add(childline);
                            }
                        }
                    }
                }
                list1.clear();
                for (NWZC181002PMsg line : list2) {
                    if (ZYPCommonFunc.hasValue(line.prntMdseCd)) {
                        mdse = getMdse(pMsg.glblCmpyCd.getValue(), line.mdseCd.getValue());
                        if (mdse != null && S21StringUtil.isEquals(mdse.mdseTpCd.getValue(), MDSE_TP.SET)) {
                            List<Map<String, Object>> childMdseList = getChildMdse(pMsg, mdse.mdseCd.getValue());
                            if (childMdseList.size() > 0) {
                                for (Map<String, Object> childMdse : childMdseList) {
                                    NWZC181002PMsg childline = new NWZC181002PMsg();
                                    ZYPEZDItemValueSetter.setValue(childline.mdseCd, (String) childMdse.get("MDSE_CD"));
                                    ZYPEZDItemValueSetter.setValue(childline.prntMdseCd, line.prntMdseCd);
                                    ZYPEZDItemValueSetter.setValue(childline.dsOrdLineCatgCd, line.dsOrdLineCatgCd);
                                    ZYPEZDItemValueSetter.setValue(childline.ordQty, line.ordQty.getValue().multiply((BigDecimal) childMdse.get("CHILD_MDSE_QTY")));
                                    ZYPEZDItemValueSetter.setValue(childline.childMdseQty, line.childMdseQty.getValue().multiply((BigDecimal) childMdse.get("CHILD_MDSE_QTY")));
                                    list3.add(line);
                                }
                            }
                        } else {
                            list3.add(line);
                        }
                    } else {
                        list3.add(line);
                    }
                }
                list2.clear();
                for (NWZC181002PMsg line : list3) {
                    EZDMsg.copy(line, null, pMsg.NWZC181002PMsg.no(idx), null);
                    idx++;
                }
                pMsg.NWZC181002PMsg.setValidCount(idx);
                list3.clear();
                // QC#23192 2018/02/09 Add End
            } else {
                if (ZYPCommonFunc.hasValue(pMsg.dsImptOrdConfigPk)) {
                    contrTpCd = NWZC181001Query.getInstance().getContractTypeForOdrImp(pMsg);
                }

                if (ZYPCommonFunc.hasValue(contrTpCd)) {
                    if (dsMdlSplyRelnTMsg.splyInitQty.getValueInt() > 0) {
                        ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).mdseCd, getSplyItemCd(dsMdlSplyRelnTMsg));
                        ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).dsOrdLineCatgCd, NWZC181001Query.getInstance().getLineCatgForInitSply(pMsg));
                        ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).ordQty, dsMdlSplyRelnTMsg.splyInitQty);
                        idx++;
                        pMsg.NWZC181002PMsg.setValidCount(idx);
                    }

                    if (dsMdlSplyRelnTMsg.splyContrQty.getValueInt() > 0) {
                        // 2016/03/03 S21_NA#4661 Mod Start
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).mdseCd, getSplyItemCd(dsMdlSplyRelnTMsg));
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).dsOrdLineCatgCd, NWZC181001Query.getInstance().getLineCatg(pMsg, contrTpCd));
                        // ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).ordQty, dsMdlSplyRelnTMsg.splyContrQty);

                        // idx++;
                        // pMsg.NWZC181002PMsg.setValidCount(idx);
                        String dsOrdLineCatgCd = NWZC181001Query.getInstance().getLineCatg(pMsg, contrTpCd);
                        if (null != dsOrdLineCatgCd) {
                            ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).mdseCd, getSplyItemCd(dsMdlSplyRelnTMsg));
                            ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).dsOrdLineCatgCd, dsOrdLineCatgCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).ordQty, dsMdlSplyRelnTMsg.splyContrQty);
                            idx++;
                            pMsg.NWZC181002PMsg.setValidCount(idx);
                        }
                        // 2016/03/03 S21_NA#4661 Mod End
                    }
                } else {
                    if (dsMdlSplyRelnTMsg.splyInitQty.getValueInt() > 0) {
                        ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).mdseCd, getSplyItemCd(dsMdlSplyRelnTMsg));
                        ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).dsOrdLineCatgCd, NWZC181001Query.getInstance().getLineCatgForInitSply(pMsg));
                        ZYPEZDItemValueSetter.setValue(pMsg.NWZC181002PMsg.no(idx).ordQty, dsMdlSplyRelnTMsg.splyInitQty);
                        idx++;
                        pMsg.NWZC181002PMsg.setValidCount(idx);
                    }
                }
            }
        }
    }

    /**
     * Get Supply Item Cd
     * @param tMsg DS_MDL_SPLY_RELNTMsg
     * @return Supply Item Cd
     */
    private String getSplyItemCd(DS_MDL_SPLY_RELNTMsg tMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(tMsg.ordTakeMdseFlg.getValue())) {
            return tMsg.ordTakeMdseCd.getValue();
        }

        return tMsg.mdseCd.getValue();
    }

    // QC#23192 2018/02/09 Add Start
    public static List<Map<String, Object>> getChildMdse(NWZC181001PMsg pMsg, String parentMdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("prntMdseCd", parentMdseCd);
        queryParam.put("slsDt", pMsg.slsDt.getValue());
        S21SsmEZDResult ssmResult = NWZC181001Query.getInstance().getChildMdseList(queryParam);
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    public static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            MDSETMsg queryMdseTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, mdseCd);

            mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
            if (mdseTMsg == null) {

                ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
                ordTakeMdseMsg.setSQLID("002");
                ordTakeMdseMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                ordTakeMdseMsg.setConditionValue("ordTakeMdseCd01", mdseCd);

                ORD_TAKE_MDSETMsgArray ordTakeMdseMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseMsg);
                if (ordTakeMdseMsgArray != null && ordTakeMdseMsgArray.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, ordTakeMdseMsgArray.no(0).mdseCd);

                    mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
                }
            }
        }
        return mdseTMsg;
    }
    // QC#23192 2018/02/09 Add E n d

}
