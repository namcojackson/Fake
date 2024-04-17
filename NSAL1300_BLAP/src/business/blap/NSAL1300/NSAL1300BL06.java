/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1300;

import static business.blap.NSAL1300.constant.NSAL1300Constant.NSAM0031E;
import static business.blap.NSAL1300.constant.NSAL1300Constant.NSAM0607E;
import static business.blap.NSAL1300.constant.NSAL1300Constant.NZZM0002I;
import static business.blap.NSAL1300.constant.NSAL1300Constant.ZZM9000E;
import static business.blap.NSAL1300.constant.NSAL1300Constant.ZZZM9001E;
import static business.blap.NSAL1300.constant.NSAL1300Constant.NSZM1312E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1300.common.NSAL1300CommonLogic;
import business.parts.NSZC010001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Usage Meter Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/01   Hitachi         N.Arai          Create          N/A
 * 2018/03/07   Hitachi         K.Kojima        Update          QC#24671
 * 2018/03/26   Hitachi         K.Kojima        Update          QC#24772
 * 2018/05/14   Hitachi         K.Kojima        Update          QC#24817
 * 2020/03/03   Hitachi         A.Kohinata      Update          QC#56123
 *</pre>
 */
public class NSAL1300BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1300CMsg cMsg = (NSAL1300CMsg) arg0;
        NSAL1300SMsg sMsg = (NSAL1300SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1300Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            } else if ("NSAL1300Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NSAL1300Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1300Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1300Scrn00_CMN_Submit(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {

        boolean hasErr = false;
        int errPageFrom = 0;

        cMsg.setCommitSMsg(true);

        NSAL1300CommonLogic.setPagenation(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt() - 1);

        if (sMsg.B.getValidCount() == 0) {
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }

        hasErr = checkNewEndRead(sMsg);
        if (hasErr) {
            // START 2018/03/26 K.Kojima [QC#24772,MOD]
            // cMsg.setMessageInfo(NSAM0607E);
            errPageFrom = NSAL1300CommonLogic.getErrPageFromNum(cMsg, sMsg);
            NSAL1300CommonLogic.pagenation(cMsg, sMsg, errPageFrom);
            // END 2018/03/26 K.Kojima [QC#24772,MOD]
            return;
        }

        hasErr = checkComments(sMsg);
        if (hasErr) {
            errPageFrom = NSAL1300CommonLogic.getErrPageFromNum(cMsg, sMsg);
            NSAL1300CommonLogic.pagenation(cMsg, sMsg, errPageFrom);
            return;
        }

        boolean resMtrUpdate = false;
        resMtrUpdate = meterUpdate(cMsg, sMsg);
        if (resMtrUpdate) {
            cMsg.setMessageInfo(NZZM0002I);
        }

   }

    private boolean checkNewEndRead(NSAL1300SMsg sMsg) {
        // mod start 2020/03/03 QC#56123
        boolean hasErr = true;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.B.no(i).readMtrCnt_BN)) {
                // return false;
                hasErr = false;
            }
        }
        if (hasErr) {
            // START 2018/03/26 K.Kojima [QC#24772,ADD]
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                sMsg.B.no(i).readMtrCnt_BN.setErrorInfo(1, NSAM0607E);
            }
            // END 2018/03/26 K.Kojima [QC#24772,ADD]
            return hasErr;
        }

        // Higher Read for Billing Check
        String glblCmpyCd = this.getGlobalCompanyCode();
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL1300_BSMsg bsMsg = sMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(bsMsg.readMtrCnt_BN)) {
                continue;
            }
            // next < current -> error
            SvcPhysMtrReadInfoBean bean = NSXC003001SvcPhysMtrRead.getNextMtrReadInfoByMtrReadTpGrp(glblCmpyCd, bsMsg.mtrReadDt_B.getValue(), bsMsg.svcPhysMtrPk_B.getValue(), dsMtrReadTpGrpCdList);
            if (bean != null) {
                if (bean.getReadMtrCnt().compareTo(bsMsg.readMtrCnt_BN.getValue()) < 0) {
                    bsMsg.readMtrCnt_BN.setErrorInfo(1, NSZM1312E);
                    hasErr = true;
                }
            }
        }
        return hasErr;
        // mod end 2020/03/03 QC#56123
    }

    private boolean checkComments(NSAL1300SMsg sMsg) {

        boolean commentsChk = false;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).readMtrCnt_BN)
                    && ZYPCommonFunc.hasValue(sMsg.B.no(i).mtrEntryCmntTxt_BN)) {

                commentsChk = true;
                sMsg.B.no(i).readMtrCnt_BN.setErrorInfo(1, ZZM9000E, new String[]{"New End Read"});

            } else if (ZYPCommonFunc.hasValue(sMsg.B.no(i).readMtrCnt_BN)
                    && !ZYPCommonFunc.hasValue(sMsg.B.no(i).mtrEntryCmntTxt_BN)) {

                commentsChk = true;
                sMsg.B.no(i).mtrEntryCmntTxt_BN.setErrorInfo(1, ZZM9000E, new String[]{"Comment"});
            }
        }
        return commentsChk;

    }

    private boolean meterUpdate(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {

        String cmpSerial = "";
        int bMsgNo = 0;
        boolean hasNewEndRead = false;
        List<NSAL1300_BSMsg> serialList = new ArrayList<NSAL1300_BSMsg>();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (i == 0) {
                hasNewEndRead = false;
                cmpSerial = sMsg.B.no(i).serNum_B.getValue();
                bMsgNo = i;
                serialList.add(sMsg.B.no(i));
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).readMtrCnt_BN)) {
                    hasNewEndRead = true;
                }
            } else {
                if (!cmpSerial.equals(sMsg.B.no(i).serNum_B.getValue())) {

                    if (hasNewEndRead) {
                        if (!registerProcess(cMsg, sMsg, bMsgNo, serialList)) {
                            return false;
                        }
                    }

                    hasNewEndRead = false;
                    cmpSerial = sMsg.B.no(i).serNum_B.getValue();
                    bMsgNo = i;
                    serialList.clear();

                    serialList.add(sMsg.B.no(i));
                    if(ZYPCommonFunc.hasValue(sMsg.B.no(i).readMtrCnt_BN.getValue())) {
                        hasNewEndRead = true;
                    }
                } else {

                    serialList.add(sMsg.B.no(i));
                    if (ZYPCommonFunc.hasValue(sMsg.B.no(i).readMtrCnt_BN)) {
                        hasNewEndRead = true;
                    }
                }
            }
        }
        if (hasNewEndRead) {
            if (!registerProcess(cMsg, sMsg, bMsgNo, serialList)) {
                return false;
            }
        }

        return true;
    }

    private boolean registerProcess(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg, int sMsgBNo, List<NSAL1300_BSMsg> bSMsgList) {

        if (!cancelMeterEntry(cMsg, bSMsgList)) {
            return  false;
        }
        // START 2018/03/07 K.Kojima [QC#24671,MOD]
        // NSZC010001PMsg pMsg = setValueNSZC010001PMsg(sMsg, bSMsgList);
        NSZC010001PMsg pMsg = setValueNSZC010001PMsg(sMsg, bSMsgList, cMsg.xxWrnSkipFlg.getValue());
        // END 2018/03/07 K.Kojima [QC#24671,MOD]
        if (!callMeterUpdateApi(cMsg, sMsg, pMsg, sMsgBNo)) {
            int errPageFromNum  = NSAL1300CommonLogic.getErrPageFromNum(cMsg, sMsg);
            NSAL1300CommonLogic.pagenation(cMsg, sMsg, errPageFromNum);
            return false;
        }
        return true;
    }

    private boolean cancelMeterEntry(NSAL1300CMsg cMsg, List<NSAL1300_BSMsg> bSMsgList) {

        // mod start 2020/03/03 QC#56123
//        for (NSAL1300_BSMsg bSMsg : bSMsgList) {
//            if (!ZYPCommonFunc.hasValue(bSMsg.readMtrCnt_BN)) {
//                continue;
//            }
//            S21SsmEZDResult ssmCancelMtr = NSAL1300Query.getInstance().getSvcPhysMtrReadGrpSq(bSMsg);
//            if (ssmCancelMtr == null || ssmCancelMtr.getQueryResultCount() == 0) {
//                continue;
//            }
//
//            List<Map<String, Object>> cancelList = (List<Map<String, Object>>) ssmCancelMtr.getResultObject();
//            for (Map<String, Object> cancelMap : cancelList) {
//                S21SsmEZDResult ssmUpdateVldMtrFlg
//                    = NSAL1300Query.getInstance().getSvcPhysMtrRead((BigDecimal)cancelMap.get("SVC_PHYS_MTR_READ_GRP_SQ"));
//
//                if (ssmUpdateVldMtrFlg == null || ssmUpdateVldMtrFlg.getQueryResultCount() == 0) {
//                    continue;
//                }
//                List<Map<String, Object>> updateList = (List<Map<String, Object>>) ssmUpdateVldMtrFlg.getResultObject();
//                if (!updateSvcPhysMtrRead(cMsg, updateList)) {
//                    return false;
//                }
//            }
//        }
        S21SsmEZDResult ssmUpdateVldMtrFlg = NSAL1300Query.getInstance().getSvcPhysMtrRead(bSMsgList.get(0).svcPhysMtrReadGrpSq_B.getValue());
        if (ssmUpdateVldMtrFlg == null || ssmUpdateVldMtrFlg.getQueryResultCount() == 0) {
            return true;
        }
        List<Map<String, Object>> updateList = (List<Map<String, Object>>) ssmUpdateVldMtrFlg.getResultObject();
        if (!updateSvcPhysMtrRead(cMsg, updateList)) {
            return false;
        }
        return true;
        // mod end 2020/03/03 QC#56123
    }

    private boolean updateSvcPhysMtrRead(NSAL1300CMsg cMsg, List<Map<String, Object>> updateList) {
        for (Map<String, Object> updateMap : updateList) {
            String rtnUpdateCd = NSAL1300Query.getInstance().updateSvcPhysMtrReadQuery((BigDecimal)updateMap.get("SVC_PHYS_MTR_READ_PK"));
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnUpdateCd)) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"SVC_PHYS_MTR_READ"});
                return false;
            }
        }
        return true;
    }

    // START 2018/03/07 K.Kojima [QC#24671,MOD]
    // private NSZC010001PMsg setValueNSZC010001PMsg(NSAL1300SMsg sMsg, List<NSAL1300_BSMsg> bSMsg) {
    private NSZC010001PMsg setValueNSZC010001PMsg(NSAL1300SMsg sMsg, List<NSAL1300_BSMsg> bSMsg, String xxWrnSkipFlg) {
    // END 2018/03/07 K.Kojima [QC#24671,MOD]

        // START 2018/05/14 K.Kojima [QC#24817,MOD]
        // S21SsmEZDResult ssmShortageMtr = NSAL1300Query.getInstance().getSvcPhysMtrRead(bSMsg.get(0).svcPhysMtrReadGrpSq_B.getValue());
        S21SsmEZDResult ssmShortageMtr = NSAL1300Query.getInstance().getSvcPhysMtrReadForMeterEntry(bSMsg.get(0).svcPhysMtrReadGrpSq_B.getValue());
        // END 2018/05/14 K.Kojima [QC#24817,MOD]

        List<Map<String, Object>> ssmMapList = (List<Map<String, Object>>) ssmShortageMtr.getResultObject();

        String glblCmpyCd = this.getGlobalCompanyCode();
        String userId = this.getContextUserInfo().getUserId();

        NSZC010001PMsg pMsg = new NSZC010001PMsg();
        // NSZC0100 Meter Update API
        pMsg.A.setValidCount(ssmMapList.size());
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
        // START 2018/03/26 K.Kojima [QC#24772,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
        ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ADJUSTMENT);
        // END 2018/03/26 K.Kojima [QC#24772,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, bSMsg.get(0).svcMachMstrPk_B);
        ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);
        // START 2018/03/07 K.Kojima [QC#24671,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.xxWrnSkipFlg, xxWrnSkipFlg);
        // END 2018/03/07 K.Kojima [QC#24671,ADD]

        for (int i = 0; i < ssmMapList.size(); i++) {

            BigDecimal svcPhysMtrPk = (BigDecimal)ssmMapList.get(i).get("SVC_PHYS_MTR_PK");

            ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).mtrReadDt, ssmMapList.get(i).get("MTR_READ_DT").toString());
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).rgtnUsrId, userId);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).estFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).invProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).svcPhysMtrPk, svcPhysMtrPk);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).mdlMtrLbCd, ssmMapList.get(i).get("MTR_LB_CD").toString());
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).readMtrCnt, (BigDecimal)ssmMapList.get(i).get("READ_MTR_CNT"));
            for (int j = 0; j < bSMsg.size(); j++) {
                if (svcPhysMtrPk.compareTo(bSMsg.get(j).svcPhysMtrPk_B.getValue()) == 0) {
                    if (ZYPCommonFunc.hasValue(bSMsg.get(j).readMtrCnt_BN)){
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).readMtrCnt, bSMsg.get(j).readMtrCnt_BN);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).mtrEntryCmntTxt, bSMsg.get(j).mtrEntryCmntTxt_BN);
                    }
                    break;
                }
            }
        }
        return pMsg;
    }

    private boolean callMeterUpdateApi(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg, NSZC010001PMsg pMsg, int sMsgBNo) {

        boolean resultApi = true;
        new NSZC010001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        String chkSerial = sMsg.B.no(sMsgBNo).serNum_B.getValue();

        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            resultApi = false;
            String msgId = msgList.get(0).getXxMsgid();
            String[] prm = msgList.get(0).getXxMsgPrmArray();
            for (int i = sMsgBNo; i < sMsg.B.getValidCount(); i++) {
                if (chkSerial.equals(sMsg.B.no(i).serNum_B.getValue())) {
                    // START 2018/03/07 K.Kojima [QC#24671,MOD]
                    // sMsg.B.no(i).readMtrCnt_BN.setErrorInfo(1, msgId, prm);
                    if (msgId.endsWith("E")) {
                        sMsg.B.no(i).readMtrCnt_BN.setErrorInfo(1, msgId, prm);
                        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
                    } else if (msgId.endsWith("W")) {
                        sMsg.B.no(i).readMtrCnt_BN.setErrorInfo(2, msgId, prm);
                        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                        cMsg.setMessageInfo("NATM0001W");
                    }
                    // END 2018/03/07 K.Kojima [QC#24671,MOD]
                } else {
                    break;
                }
            }
            // START 2018/03/07 K.Kojima [QC#24671,ADD]
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
            // END 2018/03/07 K.Kojima [QC#24671,ADD]
        }
        return resultApi;
    }

}
