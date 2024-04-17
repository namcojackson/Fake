/**
 * <pre>
 * Insert CPO_REC, CPO_DTL_REC, ORD_PRC_CALC_BASE_REC Table Record
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/10   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/06/06   Fujitsu         S.Takami        Update          S21_NA#18869
 * 2017/06/13   Fujitsu         S.Takami        Update          S21_NA#18869-2
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * 2018/09/27   Fujitsu         Hd.Sugawara     Update          S21_NA#9700
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTL_RECTMsg;
import business.db.CPO_RECTMsg;
import business.db.ORD_PRC_CALC_BASETMsg;
import business.db.ORD_PRC_CALC_BASE_RECTMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * Insert CPO_REC, CPO_DTL_REC etc, REC Record
 * @author q05163
 *
 */
public class NWZC150001CpouInsRecRecord {

    /** Ssm */
    private S21SsmBatchClient ssmClient = null;

    /** Rec Target CPO_DTL List */
    private List<CPO_DTLTMsg> cpoDtlList = null;

    /** Rec Target ORD_PRC_CALC_BASE List */
    private List<ORD_PRC_CALC_BASETMsg> ordPrcCalcBaseList = null;

    /** SSM Client*/
    public NWZC150001CpouInsRecRecord() {

        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Add CPO_DTL
     * </pre>
     * @param origCpoDtlTMsg cpo_dtl 
     */
    public void addCpoDtl(CPO_DTLTMsg origCpoDtlTMsg) {

        if (this.cpoDtlList == null) {
            this.cpoDtlList = new ArrayList<CPO_DTLTMsg>(0);
        }
        this.cpoDtlList.add(origCpoDtlTMsg);
    }

    /**
     * <pre>
     * add cpo dtl List
     * </pre>
     * @param origCpoDtlTMsgList cpo_dtl List
     */
    public void addCpoDtl(List<CPO_DTLTMsg> origCpoDtlTMsgList) {

        if (this.cpoDtlList == null) {
            this.cpoDtlList = new ArrayList<CPO_DTLTMsg>(0);
        }
        for (CPO_DTLTMsg origCpoDtlTMsg : origCpoDtlTMsgList) {
            this.addCpoDtl(origCpoDtlTMsg);
        }
    }


    /**
     * <pre>
     * add ord_prc_calc_base
     * </pre>
     * @param origOrdPrcCalcBaseTMsg ord_prc_calc_base
     */
    public void addOrdPrcCalcBase(ORD_PRC_CALC_BASETMsg origOrdPrcCalcBaseTMsg) {

        if (this.ordPrcCalcBaseList == null) {
            this.ordPrcCalcBaseList = new ArrayList<ORD_PRC_CALC_BASETMsg>(0);
        }
        this.ordPrcCalcBaseList.add(origOrdPrcCalcBaseTMsg);
    }

    /**
     * <pre>
     * add ord_prc_calc_base list
     * </pre>
     * @param origOrdPrcCalcBaseTMsgList ord_prc_calc_base list
     */
    public void addOrdPrcCalcBase(List<ORD_PRC_CALC_BASETMsg> origOrdPrcCalcBaseTMsgList) {

        if (this.ordPrcCalcBaseList == null) {
            this.ordPrcCalcBaseList = new ArrayList<ORD_PRC_CALC_BASETMsg>(0);
        }
        for (ORD_PRC_CALC_BASETMsg origOrdPrcCalcBaseTMsg : origOrdPrcCalcBaseTMsgList) {
            this.addOrdPrcCalcBase(origOrdPrcCalcBaseTMsg);
        }
    }

    /**
     * <pre>
     * Insert: CPO_REC, CPO_DTL_REC, ORD_PRC_CALC_BASE_REC
     * </pre>
     * @param cpouBean Cpo update api bean
     */
    public void insertCpoRecord(NWZC150001CpouBean cpouBean) {
        final String methodNm = "insertCpoRecord";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            CPOTMsg reqCpoTMsg = new CPOTMsg();
//            CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();

            Map<String, Object> bizLogParam = new HashMap<String, Object>();

            List<CPO_RECTMsg> cpoRecTMsgAry = new ArrayList<CPO_RECTMsg>();
            List<CPO_DTL_RECTMsg> cpoDtlRecTMsgAry = new ArrayList<CPO_DTL_RECTMsg>();

            // 2015/08/27 CSA Add Start
            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            DS_CPOTMsg reqDsCpoTMsg = new DS_CPOTMsg();
//            DS_CPO_DTLTMsg reqDsCpoDtlTMsg = new DS_CPO_DTLTMsg();
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
//            ORD_PRC_CALC_BASETMsg reqOrdPrcCalcBaseTMsg = new ORD_PRC_CALC_BASETMsg();

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            List<DS_CPO_RECTMsg> dsCpoRecTMsgAry = new ArrayList<DS_CPO_RECTMsg>();
//            List<DS_CPO_DTL_RECTMsg> dsCpoDtlRecTMsgAry = new ArrayList<DS_CPO_DTL_RECTMsg>();
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            List<ORD_PRC_CALC_BASE_RECTMsg> ordPrcCalcBaseRecTMsgAry = new ArrayList<ORD_PRC_CALC_BASE_RECTMsg>();
            // 2015/08/27 CSA Add End

            // For Performance QC#10321 Add Start
            String ezUpUserID = null;
            String ezUpTimeZone = null;
            String ezUpTime = null;
            CPOTMsg resCpoTMsg = null;
            // For Performance QC#10321 Add End

            boolean isCreateFlg = false;

            // 2017/04/10 S21_NA#Review structure Lv.2 Add Start
            // 2016/02/23 S21_NA#3479 Add Start
            final String hdrRqstTpCd = cpouBean.getRqstTpCd(); // 2015/08/27 CSA Add
            String headEventId = null;
            if (NWZC150001CpouConstant.CPO_SAVE.equals(hdrRqstTpCd) || NWZC150001CpouConstant.CPO_SUBMIT.equals(hdrRqstTpCd)) {
                if (!ZYPCommonFunc.hasValue(cpouBean.getCpoOrdNum())) {
                    headEventId = NWZC150001CpouConstant.EVENT_ID_CREATE;
                    // Add Start 2018/09/27 QC#9700
                    isCreateFlg = true;
                    // Add End 2018/09/27 QC#9700
                } else {
                    headEventId = NWZC150001CpouConstant.EVENT_ID_SAVE;
                }
            } else if (NWZC150001CpouConstant.CPO_MODIFY.equals(hdrRqstTpCd)) {
                headEventId = NWZC150001CpouConstant.EVENT_ID_MODIFY;
            } else if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(hdrRqstTpCd)) {
                headEventId = NWZC150001CpouConstant.EVENT_ID_CANCEL;
            }
            // 2016/02/23 S21_NA#3479 Add End

            final String glblCmpyCd = cpouBean.getGlblCmpyCd();
            final String cpoOrdNum = NWZC150001Common.getCpoOrdNumFromBean(cpouBean);

            reqCpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqCpoTMsg.cpoOrdNum.setValue(cpoOrdNum);
            // 2017/06/06 S21_NA#18869 Mod Start, delete static import of "findByKey"
//            resCpoTMsg = (CPOTMsg) findByKey(reqCpoTMsg);
            resCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(reqCpoTMsg);
            // 2017/06/13 S21_NA#18869-2 Add Start
            if (resCpoTMsg == null) {
                reqCpoTMsg = new CPOTMsg();

                reqCpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
                reqCpoTMsg.cpoOrdNum.setValue(cpoOrdNum);

                resCpoTMsg = (CPOTMsg) EZDTBLAccessor.findByKey(reqCpoTMsg);
            }
            // 2017/06/13 S21_NA#18869-2 Add End
            // 2017/06/06 S21_NA#18869 Mod End, delete static import of "findByKey"
            // 2017/06/13 S21_NA#18869-2 Mod Start
//            ezUpUserID = resCpoTMsg.ezUpUserID.getValue();
//            ezUpTimeZone = resCpoTMsg.ezUpTimeZone.getValue();
//            ezUpTime = resCpoTMsg.ezUpTime.getValue();
            boolean isSetCpo = true;
            if (resCpoTMsg != null) {
                ezUpUserID = resCpoTMsg.ezUpUserID.getValue();
                ezUpTimeZone = resCpoTMsg.ezUpTimeZone.getValue();
                ezUpTime = resCpoTMsg.ezUpTime.getValue();
            } else {
                isSetCpo = false;
            }
            // 2017/06/13 S21_NA#18869-2 Mod End

            // 2016/02/23 S21_NA#3479 Add Start
            bizLogParam.put("subSysId", NWZC150001CpouConstant.SUB_SYS_ID);
            bizLogParam.put("procId", NWZC150001CpouConstant.PROCESS_ID);
            bizLogParam.put("docTpCd", NWZC150001CpouConstant.DOCUMENT_TYPE);
            bizLogParam.put("eventId", headEventId);
            bizLogParam.put("prntDocId", cpoOrdNum);

            // For Performance QC#10321 Mod Start
            // bizLogParam.put("ezUpUserID", resCpoTMsg.ezUpUserID.getValue());
            // bizLogParam.put("ezUpTimeZone", resCpoTMsg.ezUpTimeZone.getValue());
            // bizLogParam.put("ezUpTime", resCpoTMsg.ezUpTime.getValue());
            // 2017/06/13 S21_NA#18869-2 Mod Start
//            bizLogParam.put("ezUpUserID", ezUpUserID);
//            bizLogParam.put("ezUpTimeZone", ezUpTimeZone);
//            bizLogParam.put("ezUpTime", ezUpTime);
            if (isSetCpo) {
                bizLogParam.put("ezUpUserID", ezUpUserID);
                bizLogParam.put("ezUpTimeZone", ezUpTimeZone);
                bizLogParam.put("ezUpTime", ezUpTime);
            } else {
                bizLogParam.remove("ezUpUserID");
                bizLogParam.remove("ezUpTimeZone");
                bizLogParam.remove("ezUpTime");
            }
            // 2017/06/13 S21_NA#18869-2 Mod End
            // For Performance QC#10321 Mod End
            cpouBean.setCpoOrdNum(cpoOrdNum);

            final List<Map> headBizLogPkList = (List<Map>) ssmClient.queryObjectList("getBizProcLogPk", bizLogParam);
            if (headBizLogPkList != null && !headBizLogPkList.isEmpty()) {
                Map bizLogMap = headBizLogPkList.get(0);
                final BigDecimal bizLogPk = (BigDecimal) bizLogMap.get("BIZ_PROC_LOG_PK");

                CPO_RECTMsg cpoRecTMsg = new CPO_RECTMsg();
                EZDMsg.copy(resCpoTMsg, null, cpoRecTMsg, null);
                cpoRecTMsg.bizProcLogPk.setValue(bizLogPk);
                cpoRecTMsgAry.add(cpoRecTMsg);
            }

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            // For Performance QC#10321 Add Start
//            reqDsCpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
//            reqDsCpoTMsg.cpoOrdNum.setValue(cpoOrdNum);
//            final DS_CPOTMsg resDsCpoTMsg = (DS_CPOTMsg) findByKey(reqDsCpoTMsg);
//            // For Performance QC#10321 Add End

//            DS_CPO_RECTMsg dsCpoRecTMsg = new DS_CPO_RECTMsg();
//            EZDMsg.copy(resDsCpoTMsg, null, dsCpoRecTMsg, null);
//            ZYPEZDItemValueSetter.setValue(dsCpoRecTMsg.bizProcLogPk, bizLogPk);
//            dsCpoRecTMsgAry.add(dsCpoRecTMsg);
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End

            NWZC150001CpouLogWriter.writePerformanceProfilingLog(getClass(), "#Header Record Processed.");
            // 2016/02/23 S21_NA#3479 Add End
            // 2017/04/10 S21_NA#Review structure Lv.2 Add End

            for (NWZC150001CpouDetailBean cpouDetailBean : cpouBean.getDtlBeanList()) {

                final String dtlRqstTpCd = cpouDetailBean.getDtlRqstTpCd();
                String eventId = null;

                // 2015/08/27 CSA Mod Start
                if (NWZC150001CpouConstant.CPO_SAVE.equals(hdrRqstTpCd) || NWZC150001CpouConstant.CPO_SUBMIT.equals(hdrRqstTpCd)) {
                    if (!ZYPCommonFunc.hasValue(cpouBean.getCpoOrdNum()) || isCreateFlg) {
                        eventId = NWZC150001CpouConstant.EVENT_ID_CREATE;
                        isCreateFlg = true;
                    } else if (NWZC150001CpouConstant.CPO_DTL_MODIFY.equals(dtlRqstTpCd)) {
                        eventId = NWZC150001CpouConstant.EVENT_ID_MODIFY;
                    } else {
                        eventId = NWZC150001CpouConstant.EVENT_ID_SAVE;
                    }

                } else if (NWZC150001CpouConstant.CPO_MODIFY.equals(hdrRqstTpCd)) {
                    if (NWZC150001CpouConstant.CPO_DTL_SAVE.equals(dtlRqstTpCd) || NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(dtlRqstTpCd)) {
                        eventId = NWZC150001CpouConstant.EVENT_ID_CREATE;
                    } else if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(dtlRqstTpCd)) {
                        eventId = NWZC150001CpouConstant.EVENT_ID_CANCEL;
                    } else {
                        eventId = NWZC150001CpouConstant.EVENT_ID_MODIFY;
                    }

                } else if (NWZC150001CpouConstant.CPO_CANCEL.equals(hdrRqstTpCd)) {
                    eventId = NWZC150001CpouConstant.EVENT_ID_CANCEL;

                } else {
                    continue;
                }
                //                if (CPO_DTL_SUBMIT.equals(dtlRqstTpCd)) {
                //                    // SUBMIT
                //                                        eventId = EVENT_ID_SUBMIT;
                //                    if (ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum())) {
                //                        eventId = NWZC150001CpouConstant.EVENT_ID_SAVE;
                //                    } else {
                //                        eventId = NWZC150001CpouConstant.EVENT_ID_CREATE;
                //                    }
                //
                //                } else if (NWZC150001CpouConstant.CPO_DTL_MODIFY.equals(dtlRqstTpCd)) {
                //                    // MODIFY
                //                    eventId = NWZC150001CpouConstant.EVENT_ID_MODIFY;
                //
                //                } else if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(dtlRqstTpCd)) {
                //                    // CANCEL
                //                    if (isCancelled(cpoDtlBean)) {
                //                        continue;
                //                        // MODIFY
                //                    } else {
                //                        eventId = NWZC150001CpouConstant.EVENT_ID_MODIFY;
                //                    }
                //
                //                } else {
                //                    continue;
                //                }
                // 2015/08/27 CSA Mod End

                final String cpoDtlLineNum = NWZC150001Common.getCpoDtlLineNumFromBean(cpouDetailBean);
                final String cpoDtlLineSubNum = NWZC150001Common.getCpoDtlLineSubNumFromBean(cpouDetailBean);

//                reqCpoDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
//                reqCpoDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
//                reqCpoDtlTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
//                reqCpoDtlTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);
//                final CPO_DTLTMsg resCpoDtlTMsg = (CPO_DTLTMsg) findByKey(reqCpoDtlTMsg);
                final CPO_DTLTMsg resCpoDtlTMsg = getCpoDtlMsg(cpouDetailBean);
                // 2016/02/05 S21_NA#3255 Add Start
                if (null == resCpoDtlTMsg) {
                    continue;
                }
                // 2016/02/05 S21_NA#3255 Add Start

                // 2015/08/27 CSA Add Start

                // For Performance QC#10321 Del Start
                // reqDsCpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
                // reqDsCpoTMsg.cpoOrdNum.setValue(cpoOrdNum);
                // final DS_CPOTMsg resDsCpoTMsg = (DS_CPOTMsg) findByKey(reqDsCpoTMsg);
                // For Performance QC#10321 Del End

                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                reqDsCpoDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
//                reqDsCpoDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
//                reqDsCpoDtlTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
//                reqDsCpoDtlTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);
//                final DS_CPO_DTLTMsg resDsCpoDtlTMsg = (DS_CPO_DTLTMsg) findByKey(reqDsCpoDtlTMsg);
                // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                // 2015/08/27 CSA Add End
                final List<ORD_PRC_CALC_BASETMsg> ordPrcCalcBaseTMsgList = getOrdPrcCalcBaseList(cpouDetailBean);

                // 2016/02/23 S21_NA#3479 Del Start
//                bizLogParam.put("subSysId", NWZC150001CpouConstant.SUB_SYS_ID);
//                bizLogParam.put("procId", NWZC150001CpouConstant.PROCESS_ID);
//                bizLogParam.put("docTpCd", NWZC150001CpouConstant.DOCUMENT_TYPE);
//                bizLogParam.put("prntDocId", cpoOrdNum);
//                bizLogParam.put("ezUpUserID", resCpoTMsg.ezUpUserID.getValue());
//                bizLogParam.put("ezUpTimeZone", resCpoTMsg.ezUpTimeZone.getValue());
//                bizLogParam.put("ezUpTime", resCpoTMsg.ezUpTime.getValue());
                // 2016/02/23 S21_NA#3479 Del End
                bizLogParam.put("eventId", eventId);
                bizLogParam.put("docId", cpoDtlLineNum + '.' + cpoDtlLineSubNum);

                final List<Map> bizLogPkList = (List<Map>) ssmClient.queryObjectList("getBizProcLogPk", bizLogParam);

                for (Map dtlBizLogMap : bizLogPkList) {

                    final BigDecimal dtlBizLogPk = (BigDecimal) dtlBizLogMap.get("BIZ_PROC_LOG_PK");
                    // final String docId = (String) dtlBizLogMap.get("DOC_ID");

                    // 2016/02/23 S21_NA#3479 Del Start
//                    if (!ZYPConstant.FLG_ON_Y.equals(cpoBean.getCpoRtrnDtlUpdFlg()) && !isHdrRegisteredFlg) { // 2015/08/27 CSA Add
//
//                        CPO_RECTMsg cpoRecTMsg = new CPO_RECTMsg();
//                        EZDMsg.copy(resCpoTMsg, null, cpoRecTMsg, null);
//                        cpoRecTMsg.bizProcLogPk.setValue(bizLogPk);
//                        cpoRecTMsgAry.add(cpoRecTMsg);
//
//                        // 2015/08/27 CSA Add Start
//                        DS_CPO_RECTMsg dsCpoRecTMsg = new DS_CPO_RECTMsg();
//                        EZDMsg.copy(resDsCpoTMsg, null, dsCpoRecTMsg, null);
//                        ZYPEZDItemValueSetter.setValue(dsCpoRecTMsg.bizProcLogPk, bizLogPk);
//                        dsCpoRecTMsgAry.add(dsCpoRecTMsg);
//
//                        isHdrRegisteredFlg = true;
//                        NWZC150001CpouLogWriter.writePerformanceProfilingLog(getClass(), "#Header Record Processed.");
//                        // 2015/08/27 CSA Add End
//                    }
//
//                    if (!hasValue(docId)) {
//                        continue;
//                    }
                    // 2016/02/23 S21_NA#3479 Del End

                    CPO_DTL_RECTMsg cpoDtlRecTMsg = new CPO_DTL_RECTMsg();
                    EZDMsg.copy(resCpoDtlTMsg, null, cpoDtlRecTMsg, null);
                    cpoDtlRecTMsg.bizProcLogPk.setValue(dtlBizLogPk);
                    cpoDtlRecTMsgAry.add(cpoDtlRecTMsg);

                    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                    // 2015/08/27 CSA Add Start
//                    DS_CPO_DTL_RECTMsg dsCpoDtlRecTMsg = new DS_CPO_DTL_RECTMsg();
//                    EZDMsg.copy(resDsCpoDtlTMsg, null, dsCpoDtlRecTMsg, null);
//                    dsCpoDtlRecTMsg.bizProcLogPk.setValue(bizLogPk);
//                    dsCpoDtlRecTMsgAry.add(dsCpoDtlRecTMsg);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

//                    for (int i = 0; i < ordPrcCalcBaseTMsgArray.getValidCount(); i++) {
//                        ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg = ordPrcCalcBaseTMsgArray.no(i);
//                        ORD_PRC_CALC_BASE_RECTMsg tMsg = new ORD_PRC_CALC_BASE_RECTMsg();
//                        EZDMsg.copy(ordPrcCalcBaseTMsg, null, tMsg, null);
//                        tMsg.bizProcLogPk.setValue(bizLogPk);
//                        ordPrcCalcBaseRecTMsgAry.add(tMsg);
//                    }
                    for (ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg : ordPrcCalcBaseTMsgList) {
                        // QC#9700  2018/09/03 Add Start
                        if(!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, ordPrcCalcBaseTMsg.prcRuleApplyFlg.getValue())){
                            continue;
                        }
                        // QC#9700  2018/09/03 Add End
                        ORD_PRC_CALC_BASE_RECTMsg tMsg = new ORD_PRC_CALC_BASE_RECTMsg();
                        EZDMsg.copy(ordPrcCalcBaseTMsg, null, tMsg, null);
                        tMsg.bizProcLogPk.setValue(dtlBizLogPk);
                        ordPrcCalcBaseRecTMsgAry.add(tMsg);
                    }

                    NWZC150001CpouLogWriter.writePerformanceProfilingLog(getClass(), "#Detail Record Processed.");
                    // 2015/08/27 CSA Add End
                }

                bizLogParam.remove("eventId");
                bizLogParam.remove("docId");
            }

            if (!cpoRecTMsgAry.isEmpty()) {
                S21FastTBLAccessor.insert(cpoRecTMsgAry.toArray(new CPO_RECTMsg[0]));
            }
            if (!cpoDtlRecTMsgAry.isEmpty()) {
                S21FastTBLAccessor.insert(cpoDtlRecTMsgAry.toArray(new CPO_DTL_RECTMsg[0]));
            }
//          // 2015/08/27 CSA Add Start
            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            if (!dsCpoRecTMsgAry.isEmpty()) {
//                S21FastTBLAccessor.insert(dsCpoRecTMsgAry.toArray(new DS_CPO_RECTMsg[0]));
//            }
//            if (!dsCpoDtlRecTMsgAry.isEmpty()) {
//                S21FastTBLAccessor.insert(dsCpoDtlRecTMsgAry.toArray(new DS_CPO_DTL_RECTMsg[0]));
//            }
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            if (!ordPrcCalcBaseRecTMsgAry.isEmpty()) {
                S21FastTBLAccessor.insert(ordPrcCalcBaseRecTMsgAry.toArray(new ORD_PRC_CALC_BASE_RECTMsg[0]));
            }
//            // 2015/08/27 CSA Add End

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private CPO_DTLTMsg getCpoDtlMsg(NWZC150001CpouDetailBean cpouDetailBean) {

        for (CPO_DTLTMsg cpoDtlTMsg : this.cpoDtlList) {
            if (S21StringUtil.isEquals(cpoDtlTMsg.cpoDtlLineNum.getValue(), cpouDetailBean.getCpoDtlLineNum()) //
                    && S21StringUtil.isEquals(cpoDtlTMsg.cpoDtlLineSubNum.getValue(), cpouDetailBean.getCpoDtlLineSubNum())) {
                return cpoDtlTMsg;
            }
        }
        return null;
    }

    private List<ORD_PRC_CALC_BASETMsg> getOrdPrcCalcBaseList(NWZC150001CpouDetailBean cpouDetailBean) {

        List<ORD_PRC_CALC_BASETMsg> ordPrcCalcBaseListForDtl = new ArrayList<ORD_PRC_CALC_BASETMsg>();

        for (ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg : this.ordPrcCalcBaseList) {
            if (S21StringUtil.isEquals(ordPrcCalcBaseTMsg.cpoDtlLineNum.getValue(), cpouDetailBean.getCpoDtlLineNum()) //
                    && S21StringUtil.isEquals(ordPrcCalcBaseTMsg.cpoDtlLineSubNum.getValue(), cpouDetailBean.getCpoDtlLineSubNum())) {
                ORD_PRC_CALC_BASETMsg newOrdPrcCalcBaseTMsg = new ORD_PRC_CALC_BASETMsg();
                EZDMsg.copy(ordPrcCalcBaseTMsg, null, newOrdPrcCalcBaseTMsg, null);
                ordPrcCalcBaseListForDtl.add(newOrdPrcCalcBaseTMsg);
            }
        }
        return ordPrcCalcBaseListForDtl;
    }
}
