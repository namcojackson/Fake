/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC093001;

import static com.canon.cusa.s21.api.NSZ.NSZC093001.constant.NSZC093001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.CTAC_PSNTMsg;
import business.db.DS_CTAC_PNTTMsg;
import business.db.DS_CTAC_PNT_TPTMsg;
import business.db.DS_CTAC_PSN_RELNTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_CTAC_TPTMsg;
import business.db.SVC_MACH_CTAC_PSNTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NSZC093001PMsg;
import business.parts.NSZC093001_dsContactPointListPMsg;
import business.parts.NSZC093001_svcMachineContactPointListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.ZYP.message.ZYPEZDMessageInfoUtil;

/**
 * <pre>
 * ONA Meter Reads Contact API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/04   Hitachi         A.Kohinata      Create          N/A
 * 05/03/2016   Fujitsu         S.Nakai         Update          CSA QC#7800
 * 2016/05/12   Hitachi         M.Gotou         Update          QC#7795
 * 2016/07/14   Hitachi         T.Tomita        Update          QC#9529
 * 2016/07/28   Hitachi         T.Tomita        Update          QC#12490
 * 2016/08/09   Hitachi         A.Kohinata      Update          QC#12591
 * 2017/07/27   Hitachi         U.Kim           Update          QC#19953
 * 2017/10/19   Hitachi         M.Kidokoro      Update          QC#20246
 * 2018/09/25   Hitachi         K.Kitachi       Update          QC#27788
 * 2019/01/16   Fujitsu         S.Kosaka        Update          QC#29642
 *</pre>
 */
public class NSZC093001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Online Batch Type */
    private ONBATCH_TYPE onBatTp = null;

    /**
     * Constructor
     */
    public NSZC093001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC093001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC093001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC093001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute
     * @param pMsg NSZC093001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC093001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
      //START 2017/07/27 U.Kim [QC#19953, ADD]
        pMsg.xxMsgIdList.clear();
        pMsg.xxMsgIdList.setValidCount(0);
      //END 2017/07/27 U.Kim [QC#19953, ADD]
        
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        this.onBatTp = onBatchType;

        checkParamCommon(msgMap);

        if (hasValue(pMsg.xxModeCd)) {
            if (MODE_REGISTRATION.equals(pMsg.xxModeCd.getValue())) {
                execRegistrationMode(msgMap);
            } else if (MODE_CREATE.equals(pMsg.xxModeCd.getValue())) {
                execCreateMode(msgMap);
            } else if (MODE_UPDATE.equals(pMsg.xxModeCd.getValue())) {
                execUpdateMode(msgMap);
            } else if (MODE_DELETE.equals(pMsg.xxModeCd.getValue())) {
                execDeleteMode(msgMap);
            } else {
                msgMap.addXxMsgId(NSZM0039E);
            }
        } else {
            msgMap.addXxMsgId(NSZM0003E);
        }
        msgMap.flush();

        // add start 2016/05/12 CSA Defect#7795
        setMessageTxt((NSZC093001PMsg) msgMap.getPmsg());
        // add end 2016/05/12 CSA Defect#7795
    }

    private void execRegistrationMode(S21ApiMessageMap msgMap) {
        NSZC093001PMsg pMsg = (NSZC093001PMsg) msgMap.getPmsg();

        if (!checkParamRegistrationMode(msgMap, pMsg)) {
            return;
        }

        NMZC002001PMsg apiPMsg = new NMZC002001PMsg();
        if (!callContactUpdateApiCreateMode(msgMap, pMsg, apiPMsg)) {
            return;
        }

        insertSvcMachCtacPsn(msgMap, pMsg, apiPMsg);
    }

    private void execCreateMode(S21ApiMessageMap msgMap) {
        NSZC093001PMsg pMsg = (NSZC093001PMsg) msgMap.getPmsg();

        if (!checkParamCreateMode(msgMap, pMsg)) {
            return;
        }

        NMZC002001PMsg apiPMsg = new NMZC002001PMsg();
        if (!callContactUpdateApiUpdateMode(msgMap, pMsg, apiPMsg)) {
            return;
        }

        insertSvcMachCtacPsn(msgMap, pMsg, apiPMsg);
    }

    private void execUpdateMode(S21ApiMessageMap msgMap) {
        NSZC093001PMsg pMsg = (NSZC093001PMsg) msgMap.getPmsg();

        if (!checkParamUpdateMode(msgMap, pMsg)) {
            return;
        }

        NMZC002001PMsg apiPMsg = new NMZC002001PMsg();
        if (!callContactUpdateApiUpdateMode(msgMap, pMsg, apiPMsg)) {
            return;
        }

        updateSvcMachCtacPsnUpdateMode(msgMap, pMsg);
    }

    private void execDeleteMode(S21ApiMessageMap msgMap) {
        NSZC093001PMsg pMsg = (NSZC093001PMsg) msgMap.getPmsg();

        if (!checkParamDeleteMode(msgMap, pMsg)) {
            return;
        }

        updateSvcMachCtacPsnDeleteMode(msgMap, pMsg);
    }

    private void checkParamCommon(S21ApiMessageMap msgMap) {
        NSZC093001PMsg pMsg = (NSZC093001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            setValue(pMsg.glblCmpyCd, GLBL_CMPY_CD);
        }
        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, (String) ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue(), PROGRAM_ID));
        }
    }

    private boolean checkParamRegistrationMode(S21ApiMessageMap msgMap, NSZC093001PMsg pMsg) {
        // Mandatory check
        checkMandatory(msgMap, pMsg.svcMachMstrPk, NSZM0074E);
        checkMandatory(msgMap, pMsg.ctacPsnFirstNm, NSZM0928E);
        checkMandatory(msgMap, pMsg.ctacPsnLastNm, NSZM0929E);

        if (pMsg.dsContactPointList.getValidCount() == 0) {
            msgMap.addXxMsgId(NSZM0930E);
        }
        for (int i = 0; i < pMsg.dsContactPointList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.dsContactPointList.no(i).dsCtacPntTpCd, NSZM0932E);
            checkMandatory(msgMap, pMsg.dsContactPointList.no(i).dsCtacPntValTxt, NSZM0933E);
        }

        for (int i = 0; i < pMsg.svcMachineContactPointList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).svcCtacTpCd, NSZM0936E);
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).lastUpdUsrId, NSZM0937E);
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).lastUpdDt, NSZM0938E);
        }

        // Master check
        if (!existsDsCtacPntTp(pMsg)) {
            msgMap.addXxMsgId(NSZM0939E);
        }

        if (!existsSvcCtacTp(pMsg)) {
            msgMap.addXxMsgId(NSZM0940E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private boolean checkParamCreateMode(S21ApiMessageMap msgMap, NSZC093001PMsg pMsg) {
        // Mandatory check
        checkMandatory(msgMap, pMsg.svcMachMstrPk, NSZM0074E);
        checkMandatory(msgMap, pMsg.ctacPsnPk, NSZM0927E);

        // START 2016/07/14 T.Tomita [QC#9529, ADD]
        if (pMsg.dsContactPointList.getValidCount() == 0) {
            msgMap.addXxMsgId(NSZM0930E);
        }
        for (int i = 0; i < pMsg.dsContactPointList.getValidCount(); i++) {
            if (!hasValue(pMsg.dsContactPointList.no(i).dsCtacPntPk)) {
                checkMandatory(msgMap, pMsg.dsContactPointList.no(i).dsCtacPntTpCd, NSZM0932E);
                checkMandatory(msgMap, pMsg.dsContactPointList.no(i).dsCtacPntValTxt, NSZM0933E);
            }
        }
        // END 2016/07/14 T.Tomita [QC#9529, ADD]

        for (int i = 0; i < pMsg.svcMachineContactPointList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).svcCtacTpCd, NSZM0936E);
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).lastUpdUsrId, NSZM0937E);
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).lastUpdDt, NSZM0938E);
        }

        // Master check
        if (!existsDsCtacPntTp(pMsg)) {
            msgMap.addXxMsgId(NSZM0939E);
        }

        if (!existsSvcCtacTp(pMsg)) {
            msgMap.addXxMsgId(NSZM0940E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private boolean checkParamUpdateMode(S21ApiMessageMap msgMap, NSZC093001PMsg pMsg) {
        // Mandatory check
        checkMandatory(msgMap, pMsg.svcMachMstrPk, NSZM0074E);
        checkMandatory(msgMap, pMsg.ctacPsnPk, NSZM0927E);

        if (pMsg.dsContactPointList.getValidCount() == 0) {
            msgMap.addXxMsgId(NSZM0930E);
        }
        for (int i = 0; i < pMsg.dsContactPointList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.dsContactPointList.no(i).dsCtacPntPk, NSZM0931E);
        }

        if (pMsg.svcMachineContactPointList.getValidCount() == 0) {
            msgMap.addXxMsgId(NSZM0934E);
        }
        for (int i = 0; i < pMsg.svcMachineContactPointList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).svcMachCtacPsnPk, NSZM0935E);
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).svcCtacTpCd, NSZM0936E);
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).lastUpdUsrId, NSZM0937E);
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).lastUpdDt, NSZM0938E);
        }

        // Master check
        if (!existsDsCtacPntTp(pMsg)) {
            msgMap.addXxMsgId(NSZM0939E);
        }

        if (!existsSvcCtacTp(pMsg)) {
            msgMap.addXxMsgId(NSZM0940E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private boolean checkParamDeleteMode(S21ApiMessageMap msgMap, NSZC093001PMsg pMsg) {
        // Mandatory check
        checkMandatory(msgMap, pMsg.svcMachMstrPk, NSZM0074E);

        if (pMsg.svcMachineContactPointList.getValidCount() == 0) {
            msgMap.addXxMsgId(NSZM0934E);
        }
        for (int i = 0; i < pMsg.svcMachineContactPointList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).svcMachCtacPsnPk, NSZM0935E);
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).lastUpdUsrId, NSZM0937E);
            checkMandatory(msgMap, pMsg.svcMachineContactPointList.no(i).lastUpdDt, NSZM0938E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void checkMandatory(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private boolean existsDsCtacPntTp(NSZC093001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        for (int i = 0; i < pMsg.dsContactPointList.getValidCount(); i++) {
            if (hasValue(pMsg.dsContactPointList.no(i).dsCtacPntTpCd)) {
                DS_CTAC_PNT_TPTMsg tMsg = getDsCtacPntTp(glblCmpyCd, pMsg.dsContactPointList.no(i).dsCtacPntTpCd.getValue());
                if (tMsg == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean existsSvcCtacTp(NSZC093001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        for (int i = 0; i < pMsg.svcMachineContactPointList.getValidCount(); i++) {
            if (hasValue(pMsg.svcMachineContactPointList.no(i).svcCtacTpCd)) {
                SVC_CTAC_TPTMsg tMsg = getSvcCtacTp(glblCmpyCd, pMsg.svcMachineContactPointList.no(i).svcCtacTpCd.getValue());
                if (tMsg == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean callContactUpdateApiCreateMode(S21ApiMessageMap msgMap, NSZC093001PMsg pMsg, NMZC002001PMsg apiPMsg) {
        String locNum = null;
        SVC_MACH_MSTRTMsg tMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (tMsg != null) {
            SHIP_TO_CUSTTMsg shipToCustMsg = getShipToCust(pMsg.glblCmpyCd.getValue(), tMsg.shipToCustCd.getValue());
            if (shipToCustMsg != null) {
                locNum = shipToCustMsg.locNum.getValue();
            }
        }
        // add start 2016/08/09 CSA Defect#12591
        if (!hasValue(locNum)) {
            msgMap.addXxMsgIdWithPrm(NSZM0713E, new String[] {"Location Number" });
            return false;
        }
        // add end 2016/08/09 CSA Defect#12591

        setValue(apiPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.locNum, locNum);
        setValue(apiPMsg.ctacPsnFirstNm, pMsg.ctacPsnFirstNm);
        setValue(apiPMsg.ctacPsnLastNm, pMsg.ctacPsnLastNm);
        // START 2017/10/19 M.Kidokoro [QC#20246, MOD]
//        setValue(apiPMsg.ctacTpCd, CTAC_TP.IB_CONTACT);
        setValue(apiPMsg.ctacTpCd, CTAC_TP.DELIVERY_INSTALL);
        // END 2017/10/19 M.Kidokoro [QC#20246, MOD]
        setValue(apiPMsg.ctacPsnCmntTxt, pMsg.ctacPsnCmntTxt);

        for (int i = 0; i < pMsg.dsContactPointList.getValidCount(); i++) {
            NSZC093001_dsContactPointListPMsg dtlPMsg = pMsg.dsContactPointList.no(i);
            setValue(apiPMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            setValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, dtlPMsg.dsCtacPntTpCd);
            setValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, dtlPMsg.dsCtacPntValTxt);
        }
        apiPMsg.ContactPointInfoList.setValidCount(pMsg.dsContactPointList.getValidCount());

        new NMZC002001().execute(apiPMsg, this.onBatTp);
        return checkErrMsg(msgMap, apiPMsg);
    }

    private boolean callContactUpdateApiUpdateMode(S21ApiMessageMap msgMap, NSZC093001PMsg pMsg, NMZC002001PMsg apiPMsg) {
        DS_CTAC_PSN_RELNTMsg dsCtacPsnRelnTMsg = getDsCtacPsnReln(pMsg.glblCmpyCd.getValue(), getDsCtacPsnRelnPk(pMsg.glblCmpyCd.getValue(), pMsg.ctacPsnPk.getValue()));
        if (dsCtacPsnRelnTMsg == null) {
            msgMap.addXxMsgIdWithPrm(NSZM0962E, new String[] {"DS_CTAC_PSN_RELN" });
            return false;
        }
        CTAC_PSNTMsg ctacPsnTMsg = getCtacPsn(pMsg.glblCmpyCd.getValue(), pMsg.ctacPsnPk.getValue());
        if (ctacPsnTMsg == null) {
            msgMap.addXxMsgIdWithPrm(NSZM0962E, new String[] {"CTAC_PSN" });
            return false;
        }

        setValue(apiPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.slsDt, pMsg.slsDt);

        setValue(apiPMsg.ctacPsnPk, pMsg.ctacPsnPk);
        setValue(apiPMsg.dsAcctNum, dsCtacPsnRelnTMsg.dsAcctNum);
        setValue(apiPMsg.locNum, dsCtacPsnRelnTMsg.locNum);
        //setValue(apiPMsg.effFromDt, dsCtacPsnRelnTMsg.effFromDt);
        setValue(apiPMsg.effThruDt, dsCtacPsnRelnTMsg.effThruDt);
        setValue(apiPMsg.dsPrimLocFlg, dsCtacPsnRelnTMsg.dsPrimLocFlg);

        setValue(apiPMsg.ctacPsnFirstNm, getValue(pMsg.ctacPsnFirstNm.getValue(), ctacPsnTMsg.ctacPsnFirstNm.getValue()));
        setValue(apiPMsg.ctacPsnLastNm, getValue(pMsg.ctacPsnLastNm.getValue(), ctacPsnTMsg.ctacPsnLastNm.getValue()));
//        setValue(apiPMsg.ctacTpCd, ctacPsnTMsg.ctacTpCd);
        setValue(apiPMsg.ctacPsnCmntTxt, getValue(pMsg.ctacPsnCmntTxt.getValue(), ctacPsnTMsg.ctacPsnCmntTxt.getValue()));

        setValue(apiPMsg.ctacPsnActvFlg, ctacPsnTMsg.ctacPsnActvFlg);
        setValue(apiPMsg.dsCtacPsnSaltCd, ctacPsnTMsg.dsCtacPsnSaltCd);
        setValue(apiPMsg.dsCtacPsnDeptCd, ctacPsnTMsg.dsCtacPsnDeptCd);
        setValue(apiPMsg.dsCtacPsnTtlCd, ctacPsnTMsg.dsCtacPsnTtlCd);
        //setValue(apiPMsg.dsPrimCtacTpCd, dsCtacPsnTMsg.dsPrimCtacTpCd);

        for (int i = 0; i < pMsg.dsContactPointList.getValidCount(); i++) {
            NSZC093001_dsContactPointListPMsg dtlPMsg = pMsg.dsContactPointList.no(i);
            if (!hasValue(pMsg.dsContactPointList.no(i).dsCtacPntPk)) {
                setValue(apiPMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
                setValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, dtlPMsg.dsCtacPntTpCd);
                setValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, dtlPMsg.dsCtacPntValTxt);
            } else {
                DS_CTAC_PNTTMsg dsCtacPntTMsg = getDsCtacPnt(pMsg.glblCmpyCd.getValue(), dtlPMsg.dsCtacPntPk.getValue());
                if (dsCtacPntTMsg == null) {
                    msgMap.addXxMsgIdWithPrm(NSZM0962E, new String[] {"DS_CTAC_PNT" });
                    return false;
                }

                setValue(apiPMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
                setValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPntPk, dtlPMsg.dsCtacPntPk);
                setValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, getValue(dtlPMsg.dsCtacPntTpCd.getValue(), dsCtacPntTMsg.dsCtacPntTpCd.getValue()));
                setValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, getValue(dtlPMsg.dsCtacPntValTxt.getValue(), dsCtacPntTMsg.dsCtacPntValTxt.getValue()));
                setValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPsnExtnNum, dsCtacPntTMsg.dsCtacPsnExtnNum);
                setValue(apiPMsg.ContactPointInfoList.no(i).dsOpsOutFlg, dsCtacPntTMsg.dsOpsOutFlg);
                setValue(apiPMsg.ContactPointInfoList.no(i).dsCtacPntActvFlg, dsCtacPntTMsg.dsCtacPntActvFlg);
                // 2019/01/16 QC#29642 Add Start
                setValue(apiPMsg.ContactPointInfoList.no(i).updCtrlFlg, ZYPConstant.FLG_ON_Y);
                // 2019/01/16 QC#29642 Add End
            }
        }
        apiPMsg.ContactPointInfoList.setValidCount(pMsg.dsContactPointList.getValidCount());

        new NMZC002001().execute(apiPMsg, this.onBatTp);
        return checkErrMsg(msgMap, apiPMsg);
    }

    private String getValue(String item1, String item2) {
        if (hasValue(item1)) {
            return item1;
        }
        return item2;
    }

    private boolean checkErrMsg(S21ApiMessageMap msgMap, EZDPMsg apiPMsg) {
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            for (S21ApiMessage msg : msgList) {
                msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            }
            return false;
        }
        return true;
    }

    private void insertSvcMachCtacPsn(S21ApiMessageMap msgMap, NSZC093001PMsg pMsg, NMZC002001PMsg apiPMsg) {
        for (int i = 0; i < apiPMsg.ContactPointInfoList.getValidCount(); i++) {
            BigDecimal dsCtacPntPk = apiPMsg.ContactPointInfoList.no(i).dsCtacPntPk.getValue();
            if (dsCtacPntPk == null) {
                continue;
            }

            for (int j = 0; j < pMsg.svcMachineContactPointList.getValidCount(); j++) {
                // START 2016/07/28 T.Tomita [QC#12490, ADD]
                BigDecimal count = countSvcMachCtacPsn(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), dsCtacPntPk);
                if (count.compareTo(BigDecimal.ZERO) > 0) {
                    msgMap.addXxMsgId(NSZM1037E);
                    return;
                }
                // END 2016/07/28 T.Tomita [QC#12490, ADD]
                NSZC093001_svcMachineContactPointListPMsg dtlPMsg = pMsg.svcMachineContactPointList.no(j);
                SVC_MACH_CTAC_PSNTMsg svcMachCtacPsnTMsg = new SVC_MACH_CTAC_PSNTMsg();
                setValue(svcMachCtacPsnTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                setValue(svcMachCtacPsnTMsg.svcMachCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_CTAC_PSN_SQ));
                setValue(svcMachCtacPsnTMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
                setValue(svcMachCtacPsnTMsg.dsCtacPntPk, dsCtacPntPk);
                setValue(svcMachCtacPsnTMsg.svcCtacTpCd, dtlPMsg.svcCtacTpCd);
                if (hasValue(dtlPMsg.effFromDt)) {
                    setValue(svcMachCtacPsnTMsg.effFromDt, dtlPMsg.effFromDt);
                } else {
                    setValue(svcMachCtacPsnTMsg.effFromDt, pMsg.slsDt);
                }
                if (hasValue(dtlPMsg.effThruDt)) {
                    setValue(svcMachCtacPsnTMsg.effThruDt, dtlPMsg.effThruDt);
                }
                setValue(svcMachCtacPsnTMsg.lastUpdUsrId, dtlPMsg.lastUpdUsrId);
                setValue(svcMachCtacPsnTMsg.lastUpdDt, dtlPMsg.lastUpdDt);
                // START 2018/09/25 K.Kitachi [QC#27788, ADD]
                setValue(svcMachCtacPsnTMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
                // END 2018/09/25 K.Kitachi [QC#27788, ADD]

                S21ApiTBLAccessor.create(svcMachCtacPsnTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcMachCtacPsnTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NSZM0941E);
                    return;
                }
            }
        }
    }

    private void updateSvcMachCtacPsnUpdateMode(S21ApiMessageMap msgMap, NSZC093001PMsg pMsg) {
        for (int i = 0; i < pMsg.svcMachineContactPointList.getValidCount(); i++) {
            NSZC093001_svcMachineContactPointListPMsg dtlPMsg = pMsg.svcMachineContactPointList.no(i);
            SVC_MACH_CTAC_PSNTMsg svcMachCtacPsnTMsg = getSvcMachCtacPsn(pMsg.glblCmpyCd.getValue(), dtlPMsg.svcMachCtacPsnPk.getValue());
            if (svcMachCtacPsnTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NSZM0962E, new String[] {"SVC_MACH_CTAC_PSN" });
                return;
            }

            setValue(svcMachCtacPsnTMsg.svcCtacTpCd, dtlPMsg.svcCtacTpCd);
            setValue(svcMachCtacPsnTMsg.effFromDt, getValue(dtlPMsg.effFromDt.getValue(), svcMachCtacPsnTMsg.effFromDt.getValue()));
            setValue(svcMachCtacPsnTMsg.effThruDt, getValue(dtlPMsg.effThruDt.getValue(), svcMachCtacPsnTMsg.effThruDt.getValue()));
            setValue(svcMachCtacPsnTMsg.lastUpdUsrId, dtlPMsg.lastUpdUsrId);
            setValue(svcMachCtacPsnTMsg.lastUpdDt, dtlPMsg.lastUpdDt);

            S21ApiTBLAccessor.update(svcMachCtacPsnTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcMachCtacPsnTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0942E);
                return;
            }
        }
    }

    private void updateSvcMachCtacPsnDeleteMode(S21ApiMessageMap msgMap, NSZC093001PMsg pMsg) {
        for (int i = 0; i < pMsg.svcMachineContactPointList.getValidCount(); i++) {
            NSZC093001_svcMachineContactPointListPMsg dtlPMsg = pMsg.svcMachineContactPointList.no(i);
            SVC_MACH_CTAC_PSNTMsg tMsg = getSvcMachCtacPsn(pMsg.glblCmpyCd.getValue(), dtlPMsg.svcMachCtacPsnPk.getValue());
            if (tMsg == null) {
                msgMap.addXxMsgIdWithPrm(NSZM0962E, new String[] {"SVC_MACH_CTAC_PSN" });
                return;
            }

            // mod start 2016/08/09 CSA Defect#12591
            //setValue(tMsg.effThruDt, pMsg.slsDt);
            //setValue(tMsg.lastUpdUsrId, dtlPMsg.lastUpdUsrId);
            //setValue(tMsg.lastUpdDt, dtlPMsg.lastUpdDt);

            //S21ApiTBLAccessor.update(tMsg);
            S21ApiTBLAccessor.logicalRemove(tMsg);
            // mod end 2016/08/09 CSA Defect#12591
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0942E);
                return;
            }
        }
    }

    private DS_CTAC_PNT_TPTMsg getDsCtacPntTp(String glblCmpyCd, String dsCtacPntTpCd) {
        DS_CTAC_PNT_TPTMsg tMsg = new DS_CTAC_PNT_TPTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsCtacPntTpCd, dsCtacPntTpCd);
        return (DS_CTAC_PNT_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private SVC_CTAC_TPTMsg getSvcCtacTp(String glblCmpyCd, String svcCtacTpCd) {
        SVC_CTAC_TPTMsg tMsg = new SVC_CTAC_TPTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcCtacTpCd, svcCtacTpCd);
        return (SVC_CTAC_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }
    private SHIP_TO_CUSTTMsg getShipToCust(String glblCmpyCd, String shipToCustCd) {
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }
    private BigDecimal getDsCtacPsnRelnPk(String glblCmpyCd, BigDecimal ctacPsnPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("ctacPsnPk", ctacPsnPk);
        return (BigDecimal) this.ssmBatchClient.queryObject("getDsCtacPsnRelnPk", param);
    }

    private DS_CTAC_PSN_RELNTMsg getDsCtacPsnReln(String glblCmpyCd, BigDecimal dsCtacPsnRelnPk) {
        DS_CTAC_PSN_RELNTMsg tMsg = new DS_CTAC_PSN_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCtacPsnRelnPk, dsCtacPsnRelnPk);
        return (DS_CTAC_PSN_RELNTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    private CTAC_PSNTMsg getCtacPsn(String glblCmpyCd, BigDecimal ctacPsnPk) {
        CTAC_PSNTMsg tMsg = new CTAC_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnPk, ctacPsnPk);
        return (CTAC_PSNTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    private DS_CTAC_PNTTMsg getDsCtacPnt(String glblCmpyCd, BigDecimal dsCtacPntPk) {
        DS_CTAC_PNTTMsg tMsg = new DS_CTAC_PNTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCtacPntPk, dsCtacPntPk);
        return (DS_CTAC_PNTTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    private SVC_MACH_CTAC_PSNTMsg getSvcMachCtacPsn(String glblCmpyCd, BigDecimal svcMachCtacPsnPk) {
        SVC_MACH_CTAC_PSNTMsg tMsg = new SVC_MACH_CTAC_PSNTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcMachCtacPsnPk, svcMachCtacPsnPk);
        return (SVC_MACH_CTAC_PSNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
    }

 // add start 2016/05/12 CSA Defect#7795
     /**
      * set Message Text
      * @param pMsg NSZC093001PMsg
      */
     private void setMessageTxt(NSZC093001PMsg pMsg) {
         ZYPEZDMessageInfoUtil util = new ZYPEZDMessageInfoUtil();
         for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            // mod start 2016/08/09 CSA Defect#12591
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(i).xxMsgTxt, util.getI18nMessage(null, pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), new String[] {pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue() }));
            // mod end 2016/08/09 CSA Defect#12591
        }
     }
 // add end 2016/05/12 CSA Defect#7795

    // START 2016/07/28 T.Tomita [QC#12490, ADD]
    private BigDecimal countSvcMachCtacPsn(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal dsCtacPntPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("dsCtacPntPk", dsCtacPntPk);
        return (BigDecimal) this.ssmBatchClient.queryObject("countSvcMachCtacPsn", param);
    }
    // END 2016/07/28 T.Tomita [QC#12490, ADD]
}
