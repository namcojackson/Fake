/**
 * <pre>
 * Insert Biz Proc Log
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/11   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/06/13   Fujitsu         S.Takami        Update          S21_NA#18869-2
 * 2017/10/31   Fujitsu         S.Ohki          Update          S21_NA#22138
 * 2018/08/02   Fujitsu         S.Takami        Update          S21_NA#25404
 * 2019/05/22   Fujitsu         K.Kato          Update          S21_NA#50400
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.BIZ_APP_ID_CREDIT_REBILL_ENTRY;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.BIZ_PROC_LOGTMsg;
import business.db.CHNG_RSN_TPTMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CHNG_RSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;

public class NWZC150001CpouInsBizProcLog {

    // 2017/06/13 S21_NA#18869-2 Del Start
//    /** Singleton Instance */
//    private static final NWZC150001CpouInsBizProcLog MY_INSTANCE = new NWZC150001CpouInsBizProcLog();
    // 2017/06/13 S21_NA#18869-2 Mod End

    private S21SsmBatchClient ssmClient = null;

    public NWZC150001CpouInsBizProcLog() {
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    // 2017/06/13 S21_NA#18869-2 Del Start
//    public static NWZC150001CpouInsBizProcLog getInstance() {
//        return MY_INSTANCE;
//    }
    // 2017/06/13 S21_NA#18869-2 Del End

    public void assortBizPrcessLogMsgs(NWZC150001CpouBean cpoBean, List<S21BusinessProcessLogMsg> saveBizLogMsgList, List<S21BusinessProcessLogMsg> submitBizLogMsgList, List<S21BusinessProcessLogMsg> modifyBizLogMsgList, List<S21BusinessProcessLogMsg> cancelBizLogMsgList,
            List<S21BusinessProcessLogMsg> closeBizLogMsgList) {

        final String methodNm = "assortBizPrcessLogMsgs";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final String chngRsnTpCd = cpoBean.getChngRsnTpCd();

            // BIZ_PROC_CMNT_TXT
            String bizProcCmntTxt_01;
            String bizProcCmntTxt_02;

            // BIZ_PROC_CMNT_TXT_01
            if (!hasValue(chngRsnTpCd)) {
                bizProcCmntTxt_01 = "";

            } else {
                CHNG_RSN_TPTMsg chngRsnTpTMsg = new CHNG_RSN_TPTMsg();
                chngRsnTpTMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
                chngRsnTpTMsg.chngRsnTpCd.setValue(chngRsnTpCd);
                chngRsnTpTMsg = (CHNG_RSN_TPTMsg) NWZC150001CpouLocalCache.findByKeyWithCache(chngRsnTpTMsg);
                if (chngRsnTpTMsg != null) {
                    bizProcCmntTxt_01 = chngRsnTpTMsg.chngRsnTpNm.getValue();
                } else {
                    bizProcCmntTxt_01 = "";
                }
            }

            // BIZ_PROC_CMNT_TXT_02
            bizProcCmntTxt_02 = cpoBean.getBizProcCmntTxt();

            // 2018/08/02 S21_NA#25404 Add Start
            List<String> prtlCancRqstList = getFirstPartialCancelRsn(cpoBean);
            boolean prtlCancFlg = false;
            boolean rtnPrtlCancFlg = false;
            if (!prtlCancRqstList.isEmpty() //
                    && !ZYPCommonFunc.hasValue(bizProcCmntTxt_01) //
                    && !ZYPCommonFunc.hasValue(bizProcCmntTxt_02)) {
                bizProcCmntTxt_01 = prtlCancRqstList.get(0);
                bizProcCmntTxt_02 = prtlCancRqstList.get(1);
                prtlCancFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(bizProcCmntTxt_01) //
                    && ZYPCommonFunc.hasValue(cpoBean.getRtnDtlChngRsnTpCd())) {
                CHNG_RSN_TPTMsg chngRsnTpTMsg = getChngRsnTpTMsg(cpoBean.getGlblCmpyCd(), cpoBean.getRtnDtlChngRsnTpCd());
                if (chngRsnTpTMsg != null) {
                    bizProcCmntTxt_01 = chngRsnTpTMsg.chngRsnTpNm.getValue();
                } else {
                    bizProcCmntTxt_01 = "";
                }
                bizProcCmntTxt_02 = cpoBean.getRtnDtlbizProcCmntTxt();
                rtnPrtlCancFlg = true;
            }
            // 2018/08/02 S21_NA#25404 Add End

            final String hdrRqstTpCd = cpoBean.getRqstTpCd();

            // 2016/02/23 S21_NA#3479 Add Start
            if (!ZYPConstant.FLG_ON_Y.equals(cpoBean.getCpoRtrnDtlUpdFlg())) { // 2016/03/01 S21_NA#3328 Add 
                final S21BusinessProcessLogMsg headBizLogMsg = new S21BusinessProcessLogMsg();
                String headEventId = null;
                if (NWZC150001CpouConstant.CPO_SAVE.equals(hdrRqstTpCd) || NWZC150001CpouConstant.CPO_SUBMIT.equals(hdrRqstTpCd)) {
                    if (!ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum())) {
                        headEventId = NWZC150001CpouConstant.EVENT_ID_CREATE;
                    } else {
                        headEventId = NWZC150001CpouConstant.EVENT_ID_SAVE;
                    }
                    saveBizLogMsgList.add(headBizLogMsg);

                } else if (NWZC150001CpouConstant.CPO_MODIFY.equals(hdrRqstTpCd) && !prtlCancFlg && !rtnPrtlCancFlg) { // 2018/08/02 S21_NA#25404 Add Condition !prtlCancFlg, !rtnPrtlCancFlg
                    headEventId = NWZC150001CpouConstant.EVENT_ID_MODIFY;
                    saveBizLogMsgList.add(headBizLogMsg);

                } else if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(hdrRqstTpCd) || prtlCancFlg || rtnPrtlCancFlg) { // 2018/08/02 S21_NA#25404 Add Condition prtlCancFlg, rtnPrtlCancFlg
                    headEventId = NWZC150001CpouConstant.EVENT_ID_CANCEL;
                    cancelBizLogMsgList.add(headBizLogMsg);
                }
                headBizLogMsg.setSubSysId(NWZC150001CpouConstant.SUB_SYS_ID);
                headBizLogMsg.setProcId(NWZC150001CpouConstant.PROCESS_ID);
                headBizLogMsg.setDocTpCd(NWZC150001CpouConstant.DOCUMENT_TYPE);
                headBizLogMsg.setEventId(headEventId);
                headBizLogMsg.setPrntDocId(NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
                headBizLogMsg.setBizProcCmntTxt_01(bizProcCmntTxt_01);
                headBizLogMsg.setBizProcCmntTxt_02(bizProcCmntTxt_02);
            }
            // 2016/02/23 S21_NA#3479 Add End

            for (NWZC150001CpouDetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {

                final String dtlRqstTpCd = cpoDtlBean.getDtlRqstTpCd();
                final S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
                String eventId = null;

                if (NWZC150001CpouConstant.CPO_SAVE.equals(hdrRqstTpCd) || NWZC150001CpouConstant.CPO_SUBMIT.equals(hdrRqstTpCd)) {
                    if (!ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum())) {
                        eventId = NWZC150001CpouConstant.EVENT_ID_CREATE;
                    } else if (NWZC150001CpouConstant.CPO_DTL_MODIFY.equals(dtlRqstTpCd)) {
                        eventId = NWZC150001CpouConstant.EVENT_ID_MODIFY;
                    } else {
                        eventId = NWZC150001CpouConstant.EVENT_ID_SAVE;
                    }
                    saveBizLogMsgList.add(bizLogMsg);

                } else if (NWZC150001CpouConstant.CPO_MODIFY.equals(hdrRqstTpCd)) {
                    if (NWZC150001CpouConstant.CPO_DTL_SAVE.equals(dtlRqstTpCd) || NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(dtlRqstTpCd)) {
                        eventId = NWZC150001CpouConstant.EVENT_ID_CREATE;
                    } else if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(dtlRqstTpCd)) {
                        eventId = NWZC150001CpouConstant.EVENT_ID_CANCEL;
                    } else {
                        eventId = NWZC150001CpouConstant.EVENT_ID_MODIFY;
                    }
                    // 2018/08/02 S21_NA#25404 Add Start
                    if (prtlCancFlg) {
                        eventId = NWZC150001CpouConstant.EVENT_ID_CANCEL;
                    }
                    final String chngRsnTpCd4Dtl = cpoDtlBean.getChngRsnTpCd();
                    if (!hasValue(chngRsnTpCd4Dtl)) {
                        bizProcCmntTxt_01 = "";

                    } else {
                        CHNG_RSN_TPTMsg chngRsnTpTMsg = getChngRsnTpTMsg(cpoBean.getGlblCmpyCd(), chngRsnTpCd4Dtl);
                        if (chngRsnTpTMsg != null) {
                            bizProcCmntTxt_01 = chngRsnTpTMsg.chngRsnTpNm.getValue();
                        } else {
                            bizProcCmntTxt_01 = "";
                        }
                    }
                    bizProcCmntTxt_02 = cpoDtlBean.getBizProcCmntTxt();
                    if (!ZYPCommonFunc.hasValue(bizProcCmntTxt_02)) {
                        bizProcCmntTxt_02 = "";
                    }
                    bizLogMsg.setBizProcCmntTxt_01(bizProcCmntTxt_01);
                    bizLogMsg.setBizProcCmntTxt_02(bizProcCmntTxt_02);
                    // 2018/08/02 S21_NA#25404 Add End
                    saveBizLogMsgList.add(bizLogMsg);

                } else if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(dtlRqstTpCd)) {
                    // CANCEL
                    if (NWZC150001Common.isCancelled(cpoDtlBean)) {
                        eventId = NWZC150001CpouConstant.EVENT_ID_CANCEL;
                        // 2017/10/31 S21_NA#22138 Add Start
                        if (BIZ_APP_ID_CREDIT_REBILL_ENTRY.equals(cpoBean.getBizAppId())) {
                        	bizProcCmntTxt_01 = ZYPCodeDataUtil.getName(CHNG_RSN_TP.class, cpoBean.getGlblCmpyCd(), CHNG_RSN_TP.OPEN_LINE_CANCELLED);
                        }
                        // 2017/10/31 S21_NA#22138 Add End
                        cancelBizLogMsgList.add(bizLogMsg);
                    // MODIFY
                    } else {
                        eventId = NWZC150001CpouConstant.EVENT_ID_MODIFY;
                        modifyBizLogMsgList.add(bizLogMsg);
                    }

                } else {
                    continue;
                }

                bizLogMsg.setSubSysId(NWZC150001CpouConstant.SUB_SYS_ID);
                bizLogMsg.setProcId(NWZC150001CpouConstant.PROCESS_ID);
                bizLogMsg.setDocTpCd(NWZC150001CpouConstant.DOCUMENT_TYPE);
                bizLogMsg.setEventId(eventId);
                bizLogMsg.setDocId(NWZC150001Common.getCpoDtlLineNumFromBean(cpoDtlBean) + '.' + NWZC150001Common.getCpoDtlLineSubNumFromBean(cpoDtlBean));
                bizLogMsg.setPrntDocId(NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
                bizLogMsg.setBizProcCmntTxt_01(bizProcCmntTxt_01);
                bizLogMsg.setBizProcCmntTxt_02(bizProcCmntTxt_02);

                // 2019/05/22 S21_NA#50400 Del Start
//                if (NWZC150001CpouConstant.EVENT_ID_CANCEL.equals(eventId) && !prtlCancFlg && !rtnPrtlCancFlg) { // 2018/08/02 S21_NA#25404 Add Condition !prtlCancFlg, !rtnPrtlCancFlg
//
//                    // CLOSE
//                    final S21BusinessProcessLogMsg closeBizLogMsg = new S21BusinessProcessLogMsg();
//                    closeBizLogMsgList.add(closeBizLogMsg);
//
//                    closeBizLogMsg.setSubSysId(NWZC150001CpouConstant.SUB_SYS_ID);
//                    closeBizLogMsg.setProcId(NWZC150001CpouConstant.PROCESS_ID);
//                    closeBizLogMsg.setDocTpCd(NWZC150001CpouConstant.DOCUMENT_TYPE);
//                    closeBizLogMsg.setEventId(NWZC150001CpouConstant.EVENT_ID_CLOSE); // CLOSE
//                    closeBizLogMsg.setDocId(NWZC150001Common.getCpoDtlLineNumFromBean(cpoDtlBean) + '.' + NWZC150001Common.getCpoDtlLineSubNumFromBean(cpoDtlBean));
//                    closeBizLogMsg.setPrntDocId(NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
//                    closeBizLogMsg.setBizProcCmntTxt_01("");
//                    closeBizLogMsg.setBizProcCmntTxt_02("");
//                }
                // 2019/05/22 S21_NA#50400 Del End
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }


    public void printBizProcessLog(List<S21BusinessProcessLogMsg>... bizLogMsgListArray) {
        final String methodNm = "printBizProcessLog";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {
            // For Performance QC#10321 Mod Start
            List<BIZ_PROC_LOGTMsg> insBizProcLogTMsgAry = new ArrayList<BIZ_PROC_LOGTMsg>();

            for (List<S21BusinessProcessLogMsg> bizLogMsgList : bizLogMsgListArray) {
                for (S21BusinessProcessLogMsg bizLogMsg : bizLogMsgList) {
                    // S21BusinessProcessLog.print(bizLogMsg);
                    insBizProcLogTMsgAry.add(S21BusinessProcessLog.getBizProcLog(bizLogMsg));
                }
            }

            if (!insBizProcLogTMsgAry.isEmpty()) {
                int cnt = S21FastTBLAccessor.insert(insBizProcLogTMsgAry.toArray(new BIZ_PROC_LOGTMsg[0]));
                if (cnt != insBizProcLogTMsgAry.size()) {
                    throw new S21AbendException("printBizProcessLog : BIZ_PROC_LOG Insert Error");
                }
            }
            // For Performance QC#10321 Mod End

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }


    public void printBizProcessLogForCloseCPO(NWZC150001CpouBean cpoBean) {
        final String methodNm = "printBizProcessLogForCloseCPO";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            Map<String, String> map = new HashMap<String, String>();
            map.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
            map.put("cpoOrdNum", NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
            map.put("stsClose", ORD_LINE_STS.CLOSED);
            map.put("stsCancel", ORD_LINE_STS.CANCELLED);

            int cnt = (Integer) ssmClient.queryObject("cntOpenOrder", map);

            // 2018/08/02 S21_NA#25404 Add Start
            Map<String, String> rtnMap = new HashMap<String, String>();
            rtnMap.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
            rtnMap.put("cpoOrdNum", NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
            rtnMap.put("stsClose", RTRN_LINE_STS.CLOSED);
            rtnMap.put("stsCancel", RTRN_LINE_STS.CANCELLED);

            int rtnCnt = (Integer) ssmClient.queryObject("cntRtnOpenOrder", map);
            // 2018/08/02 S21_NA#25404 Add End

            if (cnt == 0 && rtnCnt == 0) { // 2018/08/02 S21_NA#25404 Add "&& rtnCnt == 0"

                final S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
                bizLogMsg.setSubSysId(NWZC150001CpouConstant.SUB_SYS_ID);
                bizLogMsg.setProcId(NWZC150001CpouConstant.PROCESS_ID);
                bizLogMsg.setDocTpCd(NWZC150001CpouConstant.DOCUMENT_TYPE);
                bizLogMsg.setEventId(NWZC150001CpouConstant.EVENT_ID_CLOSE); // CLOSE
                bizLogMsg.setDocId("");
                bizLogMsg.setPrntDocId(NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
                bizLogMsg.setBizProcCmntTxt_01("");
                bizLogMsg.setBizProcCmntTxt_02("");

                // ***** print Biz Log.
                S21BusinessProcessLog.print(bizLogMsg);
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    // 2018/08/02 S21_NA#25404 Add Start
    private List<String> getFirstPartialCancelRsn(NWZC150001CpouBean cpoBean) {

        List<String> rslt = new ArrayList<String>(0);
        if (ZYPCommonFunc.hasValue(cpoBean.getChngRsnTpCd())) {
            return rslt;
        }
        for (NWZC150001CpouDetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {
            String dtlChngRsnTpCd = cpoDtlBean.getChngRsnTpCd();
            String dtlBizProcCmntTxt = cpoDtlBean.getBizProcCmntTxt();
            if (ZYPCommonFunc.hasValue(dtlChngRsnTpCd) //
                    || ZYPCommonFunc.hasValue(dtlBizProcCmntTxt)) {
                if (ZYPCommonFunc.hasValue(dtlChngRsnTpCd)) {
                    CHNG_RSN_TPTMsg chngRsnTpTMsg = getChngRsnTpTMsg(cpoBean.getGlblCmpyCd(), dtlChngRsnTpCd);
                    if (chngRsnTpTMsg != null) {
                        rslt.add(chngRsnTpTMsg.chngRsnTpNm.getValue());
                    } else {
                        rslt.add("");
                    }
                } else {
                    rslt.add("");
                }
                if (ZYPCommonFunc.hasValue(dtlBizProcCmntTxt)) {
                    rslt.add(dtlBizProcCmntTxt);
                } else {
                    rslt.add("");
                }
                return rslt;
            }
        }
        return rslt;
    }

    private CHNG_RSN_TPTMsg getChngRsnTpTMsg(String glblCmpyCd, String chngRsnTpCd) {

        CHNG_RSN_TPTMsg chngRsnTpTMsg = new CHNG_RSN_TPTMsg();
        chngRsnTpTMsg.glblCmpyCd.setValue(glblCmpyCd);
        chngRsnTpTMsg.chngRsnTpCd.setValue(chngRsnTpCd);
        return (CHNG_RSN_TPTMsg) NWZC150001CpouLocalCache.findByKeyWithCache(chngRsnTpTMsg);
    }
    // 2018/08/02 S21_NA#25404 Add End
}
