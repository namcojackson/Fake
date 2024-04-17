/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC095001;

import static com.canon.cusa.s21.api.NSZ.NSZC095001.constant.NSZC095001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDPItem;
import parts.common.EZDPMsg;

import business.db.DS_MTR_READ_TPTMsg;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC010001_APMsg;
import business.parts.NSZC095001PMsg;
import business.parts.NSZC095001_svcPhysMtrListPMsg;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.ZYP.message.ZYPEZDMessageInfoUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 * <pre>
 * ONA Meter Reads MRWEBR API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/06   Hitachi         A.Kohinata      Create          N/A
 * 2016/05/25   Hitachi         M.Gotou         Update          QC#8927
 * 2016/06/15   Hitachi         T.Mizuki        Update          QC#9896
 * 2019/09/01   Hitachi         K.Yamada        Update          QC#53166
 * 2020/03/03   Fujitsu         S.Kosaka        Update          QC#55962
 * 2020/03/03   Hitachi         A.Kohinata      Update          QC#56123
 *</pre>
 */
public class NSZC095001 extends S21ApiCommonBase {

    /** Online Batch Type */
    private ONBATCH_TYPE onBatTp = null;

    /**
     * Constructor
     */
    public NSZC095001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC095001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC095001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC095001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute
     * @param pMsg NSZC095001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC095001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        this.onBatTp = onBatchType;

        if (checkParam(msgMap)) {
            callMeterUpdateApi(msgMap);
        }
        msgMap.flush();

        // add start 2016/05/25 CSA Defect#8927
        setMessageTxt((NSZC095001PMsg) msgMap.getPmsg());
        // add end 2016/05/25 CSA Defect#8927

    }

    private boolean checkParam(S21ApiMessageMap msgMap) {
        NSZC095001PMsg pMsg = (NSZC095001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            setValue(pMsg.glblCmpyCd, GLBL_CMPY_CD);
        }
        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, (String) ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue(), PROGRAM_ID));
        }

        // Mandatory check
        checkMandatory(msgMap, pMsg.svcMachMstrPk, NSZM0074E);
        checkMandatory(msgMap, pMsg.dsMtrReadTpCd, NSZM0356E);

        if (pMsg.svcPhysMtrList.getValidCount() == 0) {
            msgMap.addXxMsgId(NSZM0357E);
        }
        for (int i = 0; i < pMsg.svcPhysMtrList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.svcPhysMtrList.no(i).readMtrCnt, NSZM0266E);
            checkMandatory(msgMap, pMsg.svcPhysMtrList.no(i).svcPhysMtrPk, NSZM0958E);
        }

        // Master check
        if (!existsDsMtrReadTp(pMsg)) {
            msgMap.addXxMsgId(NSZM0359E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }

        // add start 2020/03/03 QC#56123
        // Higher/Lower Read for Billing check
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);

        for (int i = 0; i < pMsg.svcPhysMtrList.getValidCount(); i++) {
            NSZC095001_svcPhysMtrListPMsg dtlPMsg = pMsg.svcPhysMtrList.no(i);
            String mtrReadDt = pMsg.slsDt.getValue();
            if (hasValue(dtlPMsg.mtrReadDt)) {
                mtrReadDt = dtlPMsg.mtrReadDt.getValue();
            }
            // prev > current -> error
            SvcPhysMtrReadInfoBean bean = NSXC003001SvcPhysMtrRead.getPrevMtrReadInfoByMtrReadTpGrp(pMsg.glblCmpyCd.getValue(), mtrReadDt, dtlPMsg.svcPhysMtrPk.getValue(), dsMtrReadTpGrpCdList);
            if (bean != null) {
                if (bean.getReadMtrCnt().compareTo(dtlPMsg.readMtrCnt.getValue()) > 0) {
                    msgMap.addXxMsgId(NSZM0541E);
                }
            }
            // next < current -> error
            bean = NSXC003001SvcPhysMtrRead.getNextMtrReadInfoByMtrReadTpGrp(pMsg.glblCmpyCd.getValue(), mtrReadDt, dtlPMsg.svcPhysMtrPk.getValue(), dsMtrReadTpGrpCdList);
            if (bean != null) {
                if (bean.getReadMtrCnt().compareTo(dtlPMsg.readMtrCnt.getValue()) < 0) {
                    msgMap.addXxMsgId(NSZM1312E);
                }
            }
        }
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        // add end 2020/03/03 QC#56123
        return true;
    }

    private void checkMandatory(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private boolean existsDsMtrReadTp(NSZC095001PMsg pMsg) {
        if (!hasValue(pMsg.dsMtrReadTpCd)) {
            return true;
        }
        DS_MTR_READ_TPTMsg tMsg = new DS_MTR_READ_TPTMsg();
        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(tMsg.dsMtrReadTpCd, pMsg.dsMtrReadTpCd.getValue());
        tMsg = (DS_MTR_READ_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    private boolean callMeterUpdateApi(S21ApiMessageMap msgMap) {
        NSZC095001PMsg pMsg = (NSZC095001PMsg) msgMap.getPmsg();

        // Call Meter Update API
        NSZC010001PMsg apiPMsg = new NSZC010001PMsg();
        // Set Header
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.mtrReadSrcTpCd, ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NSZC0950_MTR_READ_SRC_TP, pMsg.glblCmpyCd.getValue()));
        setValue(apiPMsg.dsMtrReadTpCd, pMsg.dsMtrReadTpCd);
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        //mod start 2019/09/01 QC#53166
        if (hasValue(pMsg.xxWrnSkipFlg)) {
            setValue(apiPMsg.xxWrnSkipFlg, pMsg.xxWrnSkipFlg);
        } else {
            setValue(apiPMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        }
        //mod end 2019/09/01 QC#53166
        setValue(apiPMsg.xxStartReadFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);

        // 2020/03/03 QC#55962 Add Start
        ArrayList<BigDecimal> svcPhysMtrPkList = new ArrayList<BigDecimal>();
        // 2020/03/03 QC#55962 Add Start
        // Set Detail
        for (int i = 0; i < pMsg.svcPhysMtrList.getValidCount(); i++) {
            NSZC095001_svcPhysMtrListPMsg dtlPMsg = pMsg.svcPhysMtrList.no(i);
            if (hasValue(dtlPMsg.mtrReadDt)) {
                setValue(apiPMsg.A.no(i).mtrReadDt, dtlPMsg.mtrReadDt);
            } else {
                setValue(apiPMsg.A.no(i).mtrReadDt, pMsg.slsDt);
            }
            setValue(apiPMsg.A.no(i).rgtnMtrDt, pMsg.slsDt);
            setValue(apiPMsg.A.no(i).readMtrCnt, dtlPMsg.readMtrCnt);
            setValue(apiPMsg.A.no(i).testMtrCnt, dtlPMsg.testMtrCnt);
            setValue(apiPMsg.A.no(i).rgtnUsrId, dtlPMsg.rgtnUsrId);
            setValue(apiPMsg.A.no(i).estFlg, ZYPConstant.FLG_OFF_N);
            setValue(apiPMsg.A.no(i).invProcFlg, ZYPConstant.FLG_OFF_N);
            setValue(apiPMsg.A.no(i).svcPhysMtrPk, dtlPMsg.svcPhysMtrPk);
            setValue(apiPMsg.A.no(i).mtrEntryCmntTxt, dtlPMsg.mtrEntryCmntTxt);
// mod start 2016/06/15 CSA Defect#9896
            setValue(apiPMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_ON_Y);
// mod end 2016/06/15 CSA Defect#9896
            // 2020/03/03 QC#55962 Add Start
            svcPhysMtrPkList.add(dtlPMsg.svcPhysMtrPk.getValue());
            // 2020/03/03 QC#55962 Add End
        }
        apiPMsg.A.setValidCount(pMsg.svcPhysMtrList.getValidCount());

        new NSZC010001().execute(apiPMsg, this.onBatTp);
        // 2020/03/03 QC#55962 Mod Start
        //return checkErrMsg(msgMap, apiPMsg);
        boolean result = checkErrMsg(msgMap, apiPMsg);
        if (result) {
            for (int j = 0; j < apiPMsg.A.getValidCount(); j++) {
                NSZC010001_APMsg apMsg = apiPMsg.A.no(j);
                if (hasValue(apMsg.xxMsgId)
                    && (MESSAGE_TYPE_ERROR.equals(apMsg.xxMsgId.getValue().substring(apMsg.xxMsgId.getValue().length() - 1))
                        || (MESSAGE_TYPE_WARNING.equals(apMsg.xxMsgId.getValue().substring(apMsg.xxMsgId.getValue().length() - 1)))
                        && svcPhysMtrPkList.contains(apMsg.svcPhysMtrPk.getValue()))) {
                    msgMap.addXxMsgId(apMsg.xxMsgId.getValue());
                    result = false;
                }
            }
        }
        return result;
        // 2020/03/03 QC#55962 Mod End
    }

    private boolean checkErrMsg(S21ApiMessageMap msgMap, EZDPMsg apiPMsg) {
        // 2020/03/03 QC#55962 Mod Start
        boolean result = true;
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            for (S21ApiMessage msg : msgList) {
                //msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                if (MESSAGE_TYPE_ERROR.equals(msg.getXxMsgid().substring(msg.getXxMsgid().length() - 1))) {
                    msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    result = false;
                }
            }
            //return false;
        }
        //return true;
        return result;
        // 2020/03/03 QC#55962 Mod End
    }

// add start 2016/05/25 CSA Defect#8927
    /**
     * set Message Text
     * @param pMsg NSZC095001PMsg
     */
    private void setMessageTxt(NSZC095001PMsg pMsg) {
        ZYPEZDMessageInfoUtil util = new ZYPEZDMessageInfoUtil();
        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(i).xxMsgTxt, util.getI18nMessage(null, pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), null));
        }
    }
// add end 2016/05/25 CSA Defect#8927
}
