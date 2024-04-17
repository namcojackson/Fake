/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC044001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.FSRTMsg;
import business.db.SVC_CALL_CPLT_TPTMsg;
import business.db.SVC_PBLM_CRCT_TPTMsg;
import business.db.SVC_PBLM_LOC_TPTMsg;
import business.db.SVC_PBLM_RSN_TPTMsg;
import business.db.SVC_PBLM_TPTMsg;
import business.parts.NSZC005001PMsg;
import business.parts.NSZC005001_xxVisitTaskListPMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC044001PMsg;
import business.parts.NSZC044001_xxVisitTaskListPMsg;
import business.parts.NSZC045001PMsg;
import business.parts.NSZC107001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC005001.NSZC005001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC044001.constant.NSZC044001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.api.NSZ.NSZC107001.NSZC107001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_AVOID;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_CPLT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Call Avoidance API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/04/2015   Fujitsu         Y.Kamide        Create
 * 08/10/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 09/10/2015   Hitachi         T.Tsuchida      Update          NA#ASCC CLICK
 * 09/11/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 10/14/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 10/28/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 11/20/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 12/21/2015   Hitachi         T.Tsuchida      Update          CSA QC#1924
 * 04/27/2016   Hitachi         T.Iwamoto       Update          CSA QC#7641
 * 05/18/2016   Hitachi         T.Iwamoto       Update          CSA QC#8426
 * 05/24/2016   Hitachi         T.Iwamoto       Update          CSA QC#8809
 * 06/16/2016   Hitachi         T.Iwamoto       Update          CSA QC#9677
 * 07/01/2016   Hitachi         T.Iwamoto       Update          CSA QC#9677
 * 10/21/2016   Hitachi         Y.Takeno        Update          CSA QC#14682
 * 09/15/2017   Hitachi         K.Kim           Update          QC#20897
 * 06/28/2018   CITS            M.Naito         Update          QC#26858
 * 09/26/2018   CITS            T.Wada          Update          QC#28262
 * 09/26/2018   Fujitsu         W.Honda         Update          QC#28381
 * 2018/10/04   Hitachi         K.Kojima        Update          QC#28545
 * </pre>
 */
public class NSZC044001 extends S21ApiCommonBase implements NSZC044001Constant {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** onBatTp */
    private ONBATCH_TYPE onBatType = null;

    /** Batch client */
    private S21SsmBatchClient ssmBatClnt;

    /**
     * Constructor
     */
    public NSZC044001() {
        super();
        ssmBatClnt = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * Call Avoidance API
     * @param pMsg {@link NSZC044001PMsg}
     * @param onBatTp Online Batch Type
     */
    public void execute(NSZC044001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        init(pMsg, onBatTp);

        if (!inputCheck(pMsg)) {
            return;
        }

        int size = pMsg.taskList.getValidCount();
        if (size < 1) {
            setErrMsg(pMsg, NSZM0164E);
            return;
        }

        String mode = pMsg.xxModeCd.getValue();
        if (MODE_CALL_RESOLVE.equals(mode)) {
            execCallResolve(pMsg);

        } else if (MODE_DISPATCH_TECH.equals(mode)) {
            execDispatchTech(pMsg);

        } else if (MODE_NEED_MORE_TIME.equals(mode)) {
            execNeedMoreTime(pMsg);
        }


        // add start 2016/06/16 CSA Defect#9677
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return;
        }
        // Call Send Task Info API To Click
        callSendTaskInfoApi(pMsg);
        // add end 2016/06/16 CSA Defect#9677
    }

    private void init(NSZC044001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        msgMap = new S21ApiMessageMap(pMsg);

        this.onBatType = onBatTp;

    }

    private boolean inputCheck(NSZC044001PMsg pMsg) {
        if (!mandatoryCheck(pMsg)) {
            return false;
        }

        return true;
    }

    private boolean mandatoryCheck(NSZC044001PMsg pMsg) {
        boolean retFlg = true;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NSZM0001E);
            retFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.xxModeCd)) {
            setErrMsg(pMsg, NSZM0003E);
            retFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.userId)) {
            setErrMsg(pMsg, NSZM0163E);
            retFlg = false;
        }

//NA#ASCC CLICK Del Start
//        if (!ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
//            setErrMsg(pMsg, NSZM0017E);
//            retFlg = false;
//        }
//NA#ASCC CLICK Del End

        if (!ZYPCommonFunc.hasValue(pMsg.dsSvcCallTpCd)) {
            setErrMsg(pMsg, NSZM0049E);
            retFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvDt)) {
            setErrMsg(pMsg, NSZM0053E);
            retFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTm)) {
            setErrMsg(pMsg, NSZM0054E);
            retFlg = false;
        }

//NA#ASCC CLICK Del Start
//        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskTpCd)) {
//            setErrMsg(pMsg, NSZM0561E);
//            retFlg = false;
//        }
//NA#ASCC CLICK Del End

        // del 2016/04/27 CSA Defect#7641
        //if (!ZYPCommonFunc.hasValue(pMsg.svcCallRqstOwnrTocCd)) {
        //    setErrMsg(pMsg, NSZM0546E);
        //    retFlg = false;
        //}
        // del 2016/04/27 CSA Defect#7641

        if (!ZYPCommonFunc.hasValue(pMsg.svcPblmTpCd)) {
            setErrMsg(pMsg, NSZM0243E);
            retFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.svcCallSrcTpCd)) {
            setErrMsg(pMsg, NSZM0544E);
            retFlg = false;
        }

//START 09/10/2015 T.Tsuchida [NA#ASCC CLICK,DEL]
//        if (!ZYPCommonFunc.hasValue(pMsg.svcCallAvoidCd)) {
//            setErrMsg(pMsg, NSZM0545E);
//            retFlg = false;
//        }
//END 09/10/2015 T.Tsuchida [NA#ASCC CLICK,DEL]

//NA#ASCC CLICK Del Start
//        if (!ZYPCommonFunc.hasValue(pMsg.billToCustCd)) {
//            setErrMsg(pMsg, NSZM0015E);
//            retFlg = false;
//        }
//NA#ASCC CLICK Del Start

        int size = pMsg.taskList.getValidCount();
        if (size < 1) {
            setErrMsg(pMsg, NSZM0164E);
            return false;
        }

        if (MODE_CALL_RESOLVE.equals(pMsg.xxModeCd.getValue()) || MODE_DISPATCH_TECH.equals(pMsg.xxModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
                setErrMsg(pMsg, NSZM0580E);
                retFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.svcCallCpltTpCd)) {
                setErrMsg(pMsg, NSZM0466E);
                retFlg = false;
            }

            for (int i = 0; i < pMsg.xxVisitTaskList.getValidCount(); i++) {

                NSZC044001_xxVisitTaskListPMsg visitListPMsg = pMsg.xxVisitTaskList.no(i);

                if (!ZYPCommonFunc.hasValue(visitListPMsg.svcPblmTpCd)) {
                    setErrMsg(pMsg, NSZM0243E, i);
                    retFlg = false;

                } else {

                    SVC_PBLM_TPTMsg chkMstrTMsg = new SVC_PBLM_TPTMsg();
                    chkMstrTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                    chkMstrTMsg.svcPblmTpCd.setValue(visitListPMsg.svcPblmTpCd.getValue());
                    ZYPEZDItemValueSetter.setValue(chkMstrTMsg.firstProdCtrlCd, pMsg.firstProdCtrlCd.getValue());

                    SVC_PBLM_TPTMsg chkRsltTMsg = (SVC_PBLM_TPTMsg) S21CacheTBLAccessor.findByKey(chkMstrTMsg);

                    if (chkRsltTMsg == null) {

                        chkMstrTMsg.firstProdCtrlCd.setValue(MULTIPLE);
                        chkRsltTMsg = (SVC_PBLM_TPTMsg) S21CacheTBLAccessor.findByKey(chkMstrTMsg);

                        if (chkRsltTMsg == null) {

                            setErrMsg(pMsg, NSZM0550E, i);
                            retFlg = false;
                        }
                    }
                }

                if (!ZYPCommonFunc.hasValue(visitListPMsg.svcPblmLocTpCd)) {
// del start 2016/10/21 CSA Defect#14682
//                    setErrMsg(pMsg, NSZM0244E, i);
//                    retFlg = false;
// del end 2016/10/21 CSA Defect#14682

                } else {

                    SVC_PBLM_LOC_TPTMsg chkMstrTMsg = new SVC_PBLM_LOC_TPTMsg();
                    chkMstrTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                    chkMstrTMsg.svcPblmLocTpCd.setValue(visitListPMsg.svcPblmLocTpCd.getValue());
                    ZYPEZDItemValueSetter.setValue(chkMstrTMsg.firstProdCtrlCd, pMsg.firstProdCtrlCd.getValue());

                    SVC_PBLM_LOC_TPTMsg chkRsltTMsg = (SVC_PBLM_LOC_TPTMsg) S21CacheTBLAccessor.findByKey(chkMstrTMsg);

                    if (chkRsltTMsg == null) {

                        chkMstrTMsg.firstProdCtrlCd.setValue(MULTIPLE);
                        chkRsltTMsg = (SVC_PBLM_LOC_TPTMsg) S21CacheTBLAccessor.findByKey(chkMstrTMsg);

                        if (chkRsltTMsg == null) {

                            setErrMsg(pMsg, NSZM0729E, i);
                            retFlg = false;
                        }
                    }
                }

                if (!ZYPCommonFunc.hasValue(visitListPMsg.svcPblmRsnTpCd)) {
// del start 2016/10/21 CSA Defect#14682
//                  setErrMsg(pMsg, NSZM0245E, i);
//                  retFlg = false;
// del end 2016/10/21 CSA Defect#14682

                } else {

                    SVC_PBLM_RSN_TPTMsg chkMstrTMsg = new SVC_PBLM_RSN_TPTMsg();
                    chkMstrTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                    chkMstrTMsg.svcPblmRsnTpCd.setValue(visitListPMsg.svcPblmRsnTpCd.getValue());
                    ZYPEZDItemValueSetter.setValue(chkMstrTMsg.firstProdCtrlCd, pMsg.firstProdCtrlCd.getValue());

                    SVC_PBLM_RSN_TPTMsg chkRsltTMsg = (SVC_PBLM_RSN_TPTMsg) S21CacheTBLAccessor.findByKey(chkMstrTMsg);

                    if (chkRsltTMsg == null) {

                        chkMstrTMsg.firstProdCtrlCd.setValue(MULTIPLE);
                        chkRsltTMsg = (SVC_PBLM_RSN_TPTMsg) S21CacheTBLAccessor.findByKey(chkMstrTMsg);

                        if (chkRsltTMsg == null) {

                            setErrMsg(pMsg, NSZM0730E, i);
                            retFlg = false;
                        }
                    }
                }

                if (!ZYPCommonFunc.hasValue(visitListPMsg.svcPblmCrctTpCd)) {
// del start 2016/10/21 CSA Defect#14682
//                    setErrMsg(pMsg, NSZM0246E, i);
//                    retFlg = false;
// del end 2016/10/21 CSA Defect#14682

                } else {

                    SVC_PBLM_CRCT_TPTMsg chkMstrTMsg = new SVC_PBLM_CRCT_TPTMsg();
                    chkMstrTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                    chkMstrTMsg.svcPblmCrctTpCd.setValue(visitListPMsg.svcPblmCrctTpCd.getValue());
                    ZYPEZDItemValueSetter.setValue(chkMstrTMsg.firstProdCtrlCd, pMsg.firstProdCtrlCd.getValue());

                    SVC_PBLM_CRCT_TPTMsg chkRsltTMsg = (SVC_PBLM_CRCT_TPTMsg) S21CacheTBLAccessor.findByKey(chkMstrTMsg);

                    if (chkRsltTMsg == null) {

                        chkMstrTMsg.firstProdCtrlCd.setValue(MULTIPLE);
                        chkRsltTMsg = (SVC_PBLM_CRCT_TPTMsg) S21CacheTBLAccessor.findByKey(chkMstrTMsg);

                        if (chkRsltTMsg == null) {

                            setErrMsg(pMsg, NSZM0731E, i);
                            retFlg = false;
                        }
                    }
                }
            }
        }
//NA#ASCC CLICK BugFix Del Start
//        for (int i = 0; i < size; i++) {
//            NSZC044001_taskListPMsg taskListPMsg = pMsg.taskList.no(i);
//
//            if (!ZYPCommonFunc.hasValue(taskListPMsg.svcCallPrtyCd)) {
//                setErrMsg(pMsg, NSZM0043E, i);
//                retFlg = false;
//            }
//        }
//NA#ASCC CLICK BugFix Del End

        return retFlg;
    }

    /**
     * execCallResolve
     * @param pMsg NSZC044001PMsg
     */
    private void execCallResolve(NSZC044001PMsg pMsg) {

        NSZC043001PMsg nszc043001PMsg = createNSZC043001PMsg(pMsg);
        NSZC043001 nszc043001API = new NSZC043001();
        nszc043001API.execute(nszc043001PMsg, this.onBatType);
        if (hasError(pMsg, nszc043001PMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, nszc043001PMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.fsrVisitNum, nszc043001PMsg.fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(pMsg.invCcyCd, nszc043001PMsg.invCcyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ccyExchRate, nszc043001PMsg.ccyExchRate);
        ZYPEZDItemValueSetter.setValue(pMsg.pmtTermCashDiscCd, nszc043001PMsg.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).svcTaskNum, nszc043001PMsg.taskList.no(0).svcTaskNum);

        NSZC005001PMsg nszc005001PMsg = createNSZC005001PMsg(pMsg, nszc043001PMsg);
        NSZC005001 nszc005001API = new NSZC005001();
        nszc005001API.execute(nszc005001PMsg, this.onBatType);
        if (hasError(pMsg, nszc005001PMsg)) {
            return;
        }
    }

    /**
     * execDispatchTech
     * @param pMsg NSZC044001PMsg
     */
    private void execDispatchTech(NSZC044001PMsg pMsg) {

        NSZC043001PMsg nszc043001PMsg = createNSZC043001PMsg(pMsg);
        NSZC043001 nszc043001API = new NSZC043001();
        nszc043001API.execute(nszc043001PMsg, this.onBatType);
        if (hasError(pMsg, nszc043001PMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, nszc043001PMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.fsrVisitNum, nszc043001PMsg.fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(pMsg.invCcyCd, nszc043001PMsg.invCcyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ccyExchRate, nszc043001PMsg.ccyExchRate);
        ZYPEZDItemValueSetter.setValue(pMsg.pmtTermCashDiscCd, nszc043001PMsg.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).svcTaskNum, nszc043001PMsg.taskList.no(0).svcTaskNum);

        NSZC045001PMsg nszc045001PMsg = createNSZC045001PMsg(pMsg, nszc043001PMsg);
        NSZC045001 nszc045001API = new NSZC045001();
        nszc045001API.execute(nszc045001PMsg, this.onBatType);
        if (hasError(pMsg, nszc045001PMsg)) {
            return;
        }

        NSZC005001PMsg nszc005001PMsg = createNSZC005001PMsg(pMsg, nszc043001PMsg);
        NSZC005001 nszc005001API = new NSZC005001();
        nszc005001API.execute(nszc005001PMsg, this.onBatType);
        if (hasError(pMsg, nszc005001PMsg)) {
            return;
        }

    }

    /**
     * execNeedMoreTime
     * @param pMsg NSZC044001PMsg
     */
    private void execNeedMoreTime(NSZC044001PMsg pMsg) {

        NSZC043001PMsg nszc043001PMsg = createNSZC043001PMsg(pMsg);
        NSZC043001 nszc043001API = new NSZC043001();
        nszc043001API.execute(nszc043001PMsg, this.onBatType);
        if (hasError(pMsg, nszc043001PMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, nszc043001PMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.fsrVisitNum, nszc043001PMsg.fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(pMsg.invCcyCd, nszc043001PMsg.invCcyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ccyExchRate, nszc043001PMsg.ccyExchRate);
        ZYPEZDItemValueSetter.setValue(pMsg.pmtTermCashDiscCd, nszc043001PMsg.pmtTermCashDiscCd);
    }

    private NSZC043001PMsg createNSZC043001PMsg(NSZC044001PMsg pMsg) {
        String mode = pMsg.xxModeCd.getValue();

        NSZC043001PMsg nszc043001PMsg = new NSZC043001PMsg();

        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.xxModeCd, NSZC043001Constant.MODE_CREATE_FSR);
        // mod start 2016/05/24 CSA Defect#8809
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.slsDt, pMsg.slsDt);
        // mod end 2016/05/24 CSA Defect#8809
        if (MODE_CALL_RESOLVE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(nszc043001PMsg.fsrNum, pMsg.fsrNum);
            ZYPEZDItemValueSetter.setValue(nszc043001PMsg.fsrVisitNum, pMsg.fsrVisitNum);
        }

        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.userId, pMsg.userId);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.bypsPrfTechFlg, pMsg.bypsPrfTechFlg);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.soNum, pMsg.soNum);
        if (MODE_CALL_RESOLVE.equals(mode) || MODE_DISPATCH_TECH.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(nszc043001PMsg.firstProdCtrlCd, pMsg.firstProdCtrlCd);
        }
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.custMachCtrlNum, pMsg.custMachCtrlNum);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.serNum, pMsg.serNum);
//NA#ASCC CLICK Del Start
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.shipToCustCd, pMsg.shipToCustCd);
//NA#ASCC CLICK Del End
        // START 2017/09/15 K.Kim [QC#20897, MOD]
        // ZYPEZDItemValueSetter.setValue(nszc043001PMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);
//START 09/26/2018 W.Honda [QC#28381 ,MOD]
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.dsSvcCallTpCd, DS_SVC_CALL_TP.PHONE_FIX_DISPATCHER);
        DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = (DS_SVC_CALL_TPTMsg) ZYPCodeDataUtil.findByCode(DS_SVC_CALL_TP.class, pMsg.glblCmpyCd.getValue(), pMsg.dsSvcCallTpCd.getValue());
        if (dsSvcCallTpTMsg != null && ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.aftHrsFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(nszc043001PMsg.dsSvcCallTpCd, DS_SVC_CALL_TP.AHS_PHONE_FIX_DISPATCHER);
        } else {
            ZYPEZDItemValueSetter.setValue(nszc043001PMsg.dsSvcCallTpCd, DS_SVC_CALL_TP.PHONE_FIX_DISPATCHER);
        }
//EMD 09/26/2018 W.Honda [QC#28381 ,MOD]
        // END 2017/09/15 K.Kim [QC#20897, MOD]
        // START 2018/10/04 K.Kojima [QC#28545,MOD]
        // ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcBillTpCd, pMsg.svcBillTpCd);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcBillTpCd, SVC_BILL_TP.NO_CHRGE);
        // END 2018/10/04 K.Kojima [QC#28545,MOD]
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcTaskRcvDt, pMsg.svcTaskRcvDt);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcTaskRcvTm, pMsg.svcTaskRcvTm);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.machDownFlg, pMsg.machDownFlg);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.prtChrgFlg, pMsg.prtChrgFlg);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcLborChrgFlg, pMsg.svcLborChrgFlg);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.pmtTermCashDiscCd, pMsg.pmtTermCashDiscCd);
//NA#ASCC CLICK Del Start
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcTaskTpCd, pMsg.svcTaskTpCd);
//NA#ASCC CLICK Del End
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.firstSvcTaskFlg, pMsg.firstSvcTaskFlg);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcCallRqstOwnrTocCd, pMsg.svcCallRqstOwnrTocCd);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcPblmTpCd, pMsg.svcPblmTpCd);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcCallSrcTpCd, pMsg.svcCallSrcTpCd);
//START 09/10/2015 T.Tsuchida [NA#ASCC CLICK,MOD]
//        if (MODE_NEED_MORE_TIME.equals(mode)) {
        if (MODE_NEED_MORE_TIME.equals(mode) || !ZYPCommonFunc.hasValue(pMsg.svcCallAvoidCd)) {
//            ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcCallAvoidCd, SVC_CALL_AVOID.CALL_AVOIDANCE_ACCEPTED);
            ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcCallAvoidCd, SVC_CALL_AVOID.AVOIDANCE_SUCCEEDED);
//END 09/10/2015 T.Tsuchida [NA#ASCC CLICK,MOD]
        } else {
            ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcCallAvoidCd, pMsg.svcCallAvoidCd);
        }
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.custCaseNum, pMsg.custCaseNum);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.ittNum, pMsg.ittNum);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.billToCustCd, pMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.billToCustUpdFlg, pMsg.billToCustUpdFlg);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.shipToCustUpdFlg, pMsg.shipToCustUpdFlg);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.locNm, pMsg.locNm);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.addlLocNm, pMsg.addlLocNm);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.firstLineAddr, pMsg.firstLineAddr);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.scdLineAddr, pMsg.scdLineAddr);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.thirdLineAddr, pMsg.thirdLineAddr);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.frthLineAddr, pMsg.frthLineAddr);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.ctyAddr, pMsg.ctyAddr);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.stCd, pMsg.stCd);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.provNm, pMsg.provNm);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.cntyNm, pMsg.cntyNm);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.postCd, pMsg.postCd);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.ctryCd, pMsg.ctryCd);
//        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcMachFlNm, pMsg.svcMachFlNm);

        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.xxModeCd_AD, mode);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcCustCllrTxt, pMsg.svcCustCllrTxt);

        // mod start 2016/05/18 CSA Defect#8426
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.custTelNum, pMsg.custTelNum);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.custTelExtnNum, pMsg.custTelExtnNum);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.svcCustAttnTxt, pMsg.svcCustAttnTxt);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.custEmlAddr, pMsg.custEmlAddr);
        // mod end 2016/05/18 CSA Defect#8426
        // add start 2016/07/01 CSA Defect#9677
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // add end 2016/07/01 CSA Defect#9677

        // QC#28262 Add Start
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.crCardCustRefNum, pMsg.crCardCustRefNum);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.crCardHldNm, pMsg.crCardHldNm);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.crCardAuthAmt, pMsg.crCardAuthAmt);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.crCardTpCd, pMsg.crCardTpCd);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.crCardExprYrMth, pMsg.crCardExprYrMth);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.crCardAuthRefNum, pMsg.crCardAuthRefNum);
        ZYPEZDItemValueSetter.setValue(nszc043001PMsg.crCardLastDigitNum, pMsg.crCardLastDigitNum);
        // QC#28262 Add End
        
        setSvcTaskList(pMsg, nszc043001PMsg);

        setSvcMemoList(pMsg, nszc043001PMsg);

        setAttachedFileList(pMsg, nszc043001PMsg);

        return nszc043001PMsg;
    }

    private NSZC005001PMsg createNSZC005001PMsg(NSZC044001PMsg pMsg, NSZC043001PMsg nszc043001PMsg) {

        String mode = pMsg.xxModeCd.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = nszc043001PMsg.fsrNum.getValue();
        FSRTMsg fsrTMsg = getFsr(glblCmpyCd, fsrNum);

        NSZC005001PMsg nszc005001PMsg = new NSZC005001PMsg();
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.glblCmpyCd, glblCmpyCd);
        // add start 2015/12/21 CSA Defect#1924
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.xxModeCd_AD, ZYPConstant.FLG_ON_1);
        // add end 2015/12/21 CSA Defect#1924
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.userId, pMsg.userId);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.fsrNum, fsrNum);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.fsrVisitNum, nszc043001PMsg.fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.serNum, pMsg.serNum);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.fsrVisitArvDt, fsrTMsg.fsrCratDt);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.fsrVisitArvTm, fsrTMsg.fsrCratTm);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.fsrVisitCpltDt, fsrTMsg.fsrCratDt);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.fsrVisitCpltTm, fsrTMsg.fsrCratTm);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcTrvlTmNum, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborDealAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborFuncAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborDealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborFuncTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborDealDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborFuncDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborInvDealAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborInvFuncAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborInvDealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborInvFuncTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborInvDealDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborInvFuncDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcTrvlUnitHrsAot, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcTrvlUnitAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcTrvlDealAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcTrvlFuncAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcTrvlTaxRate, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcTrvlDealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcTrvlFuncTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcTrvlDiscRate, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcTrvlDealDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcTrvlFuncDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcPrtDealAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcPrtFuncAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcPrtDealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcPrtFuncTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcPrtDealDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcPrtFuncDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.invCcyCd, nszc043001PMsg.invCcyCd);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.ccyExchRate, nszc043001PMsg.ccyExchRate);

        SVC_CALL_CPLT_TPTMsg svcCallCpltTpTMsg = (SVC_CALL_CPLT_TPTMsg) ZYPCodeDataUtil.findByCode(SVC_CALL_CPLT_TP.class, glblCmpyCd, SVC_CALL_CPLT_TP.USUAL_COMPLETE);
        if (svcCallCpltTpTMsg == null) {
            setErrMsg(pMsg, NSZM0212E);
            return null;
        }

        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcChrgContFlg, svcCallCpltTpTMsg.nextFsrVisitCratFlg);
        if (MODE_CALL_RESOLVE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcCallCpltTpCd, SVC_CALL_CPLT_TP.USUAL_COMPLETE);
        } else if (MODE_DISPATCH_TECH.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcCallCpltTpCd, SVC_CALL_CPLT_TP.WAITING_HIGH_LEVEL_TECHNICIAN);
        }

        DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = (DS_SVC_CALL_TPTMsg) ZYPCodeDataUtil.findByCode(DS_SVC_CALL_TP.class, glblCmpyCd, pMsg.dsSvcCallTpCd.getValue());
        if (dsSvcCallTpTMsg == null) {
            setErrMsg(pMsg, NSZM0549E);
            return null;
        }

        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcBillTpCd, dsSvcCallTpTMsg.svcBillTpCd);
        ZYPEZDItemValueSetter.setValue(nszc005001PMsg.pmtTermCashDiscCd, nszc043001PMsg.pmtTermCashDiscCd);
        // TODO svcLborTpCd parameter is not exists in nszc043001PMsg.
        // ZYPEZDItemValueSetter.setValue(nszc005001PMsg.svcLborTpCd,
        // nszc043001PMsg.svcLborTpCd);

        int cnt = nszc043001PMsg.taskList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NSZC005001_xxVisitTaskListPMsg xxVisitTaskListPMsg = nszc005001PMsg.xxVisitTaskList.no(0);

            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcTaskNum, nszc043001PMsg.taskList.no(0).svcTaskNum);
            // TODO SVC_PBLM_TP_CD
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcPblmTpCd, nszc043001PMsg.svcPblmTpCd);
            // START 2018/06/28 M.Naito [QC#26858,MOD]
            // SVC_PBLM_LOC_TP_CD
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcPblmLocTpCd, SVC_PBLM_LOC_TP_NO_PROBLEM_FOUND);
            // SVC_PBLM_RSN_TP_CD
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcPblmRsnTpCd, SVC_PBLM_RSN_TP_OTHER);
            // SVC_PBLM_CRCT_TP_CD
            if (MODE_CALL_RESOLVE.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcPblmCrctTpCd, SVC_PBLM_CRCT_TP_DISPATCH_PHONE_FIX);
            } else if (MODE_DISPATCH_TECH.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcPblmCrctTpCd, SVC_PBLM_CRCT_TP_UNSUCCESSFUL_PHONE_FIX);
            }
            // END 2018/06/28 M.Naito [QC#26858,MOD]
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborTmNum, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborUnitHrsAot, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborUnitAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborDealAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborFuncAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborTaxRate, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborDealTaxAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborFuncTaxAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborDiscRate, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborDealDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborFuncDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborInvDealAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborInvFuncAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborInvDealTaxAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborInvFuncTaxAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborInvDealDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.svcLborInvFuncDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(xxVisitTaskListPMsg.xxVisitTaskCpltFlg, ZYPConstant.FLG_ON_Y);
        }
        nszc005001PMsg.xxVisitTaskList.setValidCount(cnt);

        return nszc005001PMsg;
    }

    private NSZC045001PMsg createNSZC045001PMsg(NSZC044001PMsg pMsg, NSZC043001PMsg nszc043001PMsg) {

        NSZC045001PMsg nszc045001PMsg = new NSZC045001PMsg();
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.xxModeCd, NSZC045001.PROCESS_MODE_CALLAVOID_DISPTTECH);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.userId, pMsg.userId);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.fsrNum, nszc043001PMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.fsrVisitNum, nszc043001PMsg.fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.bypsPrfTechFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.soNum, pMsg.soNum);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.firstProdCtrlCd, pMsg.firstProdCtrlCd);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.custMachCtrlNum, pMsg.custMachCtrlNum);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.serNum, pMsg.serNum);
//NA#ASCC CLICK Del Start
//        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.shipToCustCd, pMsg.shipToCustCd);
//NA#ASCC CLICK Del End
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.custTelNum, pMsg.custTelNum);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.custTelExtnNum, pMsg.custTelExtnNum);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.svcCustAttnTxt, pMsg.svcCustAttnTxt);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.custEmlAddr, pMsg.custEmlAddr);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.custPoNum, pMsg.custPoNum);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.custPoDt, pMsg.custPoDt);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.svcBillTpCd, pMsg.svcBillTpCd);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.svcTaskRcvDt, pMsg.svcTaskRcvDt);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.svcTaskRcvTm, pMsg.svcTaskRcvTm);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.machDownFlg, pMsg.machDownFlg);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.prtChrgFlg, pMsg.prtChrgFlg);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.svcLborChrgFlg, pMsg.svcLborChrgFlg);
//NA#ASCC CLICK Del Start
//        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.svcTaskTpCd, pMsg.svcTaskTpCd);
//NA#ASCC CLICK Del End
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.firstSvcTaskFlg, pMsg.firstSvcTaskFlg);
//NA#ASCC CLICK Del Start
//        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.svcCallRqstOwnrTocCd, pMsg.svcCallRqstOwnrTocCd);
//NA#ASCC CLICK Del End
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.svcChrgContFlg, pMsg.svcChrgContFlg);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.svcCustCllrTxt, pMsg.svcCustCllrTxt);
        // add start 2016/07/01 CSA Defect#9677
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // add end 2016/07/01 CSA Defect#9677

        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.xxSvcTaskList.no(0).svcTaskNum, pMsg.taskList.no(0).svcTaskNum);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.xxSvcTaskList.no(0).svcCallPrtyCd, pMsg.taskList.no(0).svcCallPrtyCd);
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.xxSvcTaskList.no(0).techCd, pMsg.taskList.no(0).techCd.getValue());
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.xxSvcTaskList.no(0).schdDisptEmlFlg, pMsg.taskList.no(0).schdDisptEmlFlg);

        // QC#28262 Add Start
        ZYPEZDItemValueSetter.setValue(nszc045001PMsg.xxSvcTaskList.no(0).cellPhoNum, pMsg.taskList.no(0).cellPhoNum);
        // QC#28262 Add End

        nszc045001PMsg.xxSvcTaskList.setValidCount(1);

        setSvcMemoList(pMsg, nszc045001PMsg);

        setAttachedFileList(pMsg, nszc045001PMsg);

        return nszc045001PMsg;
    }

    private void setAttachedFileList(NSZC044001PMsg pMsg, NSZC043001PMsg nszc043001PMsg) {
        int cnt = pMsg.attachedFileList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg.copy(pMsg.attachedFileList.no(i), null, nszc043001PMsg.attachedFileList.no(i), null);
        }
        nszc043001PMsg.attachedFileList.setValidCount(cnt);
    }

    private void setSvcMemoList(NSZC044001PMsg pMsg, NSZC043001PMsg nszc043001PMsg) {
        int cnt = pMsg.svcMemoList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg.copy(pMsg.svcMemoList.no(i), null, nszc043001PMsg.svcMemoList.no(i), null);
        }
        nszc043001PMsg.svcMemoList.setValidCount(cnt);
    }

    private void setSvcTaskList(NSZC044001PMsg pMsg, NSZC043001PMsg nszc043001PMsg) {

        int cnt = pMsg.taskList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg.copy(pMsg.taskList.no(i), null, nszc043001PMsg.taskList.no(i), null);
        }

        nszc043001PMsg.taskList.setValidCount(cnt);
    }

    private void setAttachedFileList(NSZC044001PMsg pMsg, NSZC045001PMsg nszc045001PMsg) {
        int cnt = pMsg.attachedFileList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg.copy(pMsg.attachedFileList.no(i), null, nszc045001PMsg.xxAttDataList.no(i), null);
        }
        nszc045001PMsg.xxAttDataList.setValidCount(cnt);
    }

    private void setSvcMemoList(NSZC044001PMsg pMsg, NSZC045001PMsg nszc045001PMsg) {
        int cnt = pMsg.svcMemoList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg.copy(pMsg.svcMemoList.no(i), null, nszc045001PMsg.xxSvcMemoList.no(i), null);
        }
        nszc045001PMsg.xxSvcMemoList.setValidCount(cnt);
    }

    private FSRTMsg getFsr(String glblCmpyCd, String fsrNum) {
        FSRTMsg paramTMsg = new FSRTMsg();
        ZYPEZDItemValueSetter.setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramTMsg.fsrNum, fsrNum);
        return (FSRTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private boolean hasError(NSZC044001PMsg pMsg, EZDPMsg callApiPMsg) {
        boolean result = false;
        List<String> errList = S21ApiUtil.getXxMsgIdList(callApiPMsg);
        if (errList.size() > 0) {
            result = true;
            for (String msgId : errList) {
                setErrMsg(pMsg, msgId);
            }
        }
        return result;
    }

    private void setErrMsg(NSZC044001PMsg pMsg, String msgId) {
        msgMap.addXxMsgId(msgId);
        msgMap.flush();
    }

    private void setErrMsg(NSZC044001PMsg pMsg, String msgId, int idx) {
        if (!ZYPCommonFunc.hasValue(pMsg.taskList.no(idx).xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(idx).xxMsgId, msgId);
        }
        setErrMsg(pMsg, msgId);
    }


    // add start 2016/06/16 CSA Defect#9677
    private void callSendTaskInfoApi(NSZC044001PMsg pMsg) {
        List<NSZC107001PMsg> inPMsgList = new ArrayList<NSZC107001PMsg>();
        List<String> taskList = getSendTaskList(pMsg);
        for (String svcTaskNum : taskList) {
            NSZC107001PMsg inPMsg = new NSZC107001PMsg();
            ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inPMsg.slsDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(inPMsg.svcTaskNum, svcTaskNum);
            inPMsgList.add(inPMsg);
        }

        NSZC107001 nszc107001API = new NSZC107001();
        nszc107001API.execute(inPMsgList, this.onBatType);
        for (NSZC107001PMsg inPMsg : inPMsgList) {
            if (S21ApiUtil.isXxMsgId(inPMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(inPMsg);
                for (S21ApiMessage msg : msgList) {
                    if (ZYPCommonFunc.hasValue(msg.getXxMsgid()) && msg.getXxMsgid().endsWith("E")) {
                        msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    }
                }
            }
        }
        msgMap.flush();
    }
    // add end 2016/06/16 CSA Defect#9677

    // add start 2016/06/16 CSA Defect#9677
    private List<String> getSendTaskList(NSZC044001PMsg pMsg) {
        List<String> taskList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(pMsg.taskList.no(0).svcTaskNum)) {
            taskList.add(pMsg.taskList.no(0).svcTaskNum.getValue());
        }

        if (MODE_CALL_RESOLVE.equals(pMsg.xxModeCd.getValue()) || MODE_DISPATCH_TECH.equals(pMsg.xxModeCd.getValue())) {
            String follUpTaskNum = getFollUpTask(pMsg);
            if (ZYPCommonFunc.hasValue(follUpTaskNum)) {
                taskList.add(follUpTaskNum);
            }
        }
        return taskList;
    }
    // add end 2016/06/16 CSA Defect#9677

    // add start 2016/06/16 CSA Defect#9677
    private String getFollUpTask(NSZC044001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcTaskNum", pMsg.taskList.no(0).svcTaskNum.getValue());
        return (String) ssmBatClnt.queryObject("getFollUpTask", paramMap);
    }
    // add end 2016/06/16 CSA Defect#9677
}
