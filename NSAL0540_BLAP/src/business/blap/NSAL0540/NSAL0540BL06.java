/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0540;

import static business.blap.NSAL0540.constant.NSAL0540Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0540.common.NSAL0540CommonLogic;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.parts.NSZC036001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC036001.NSZC036001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Solution Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * 2016/02/19   Hitachi         K.Kasai         Update          QC#3689
 *</pre>
 */
public class NSAL0540BL06 extends S21BusinessHandler implements ZYPConstant {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0540CMsg cMsg = (NSAL0540CMsg) arg0;
        NSAL0540SMsg sMsg = (NSAL0540SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0540Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0540Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NSAL0540Scrn00_CMN_Submit(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {

        if (sMsg.A.getValidCount() == 0 && sMsg.B.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0020E);
            return;
        }

        exclusiveCheck(cMsg, sMsg);
        if (NSAL0540CommonLogic.isError(cMsg)) {
            return;
        }

        // DB Registration
        if (!callNSZC036001Api(cMsg, sMsg)) {
            return;
        }
        if (!createUpdateSvcMemo(cMsg)) {
            return;
        }
        setValue(cMsg.svcSlnSq, cMsg.svcSlnSq_NW);
        setValue(cMsg.svcSlnSq_BK, cMsg.svcSlnSq_NW);

        cMsg.setMessageInfo(NSAM0200I);
    }

    private boolean callNSZC036001Api(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {

        NSZC036001PMsg pMsg = getNSZC036001PMsg(cMsg, sMsg);
        NSZC036001 api = new NSZC036001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
            }
        }

        if (NSAL0540CommonLogic.isError(cMsg)) {
            return false;
        }
        setValue(cMsg.svcSlnSq_NW, pMsg.svcSlnSq);
        return true;
    }

    private void exclusiveCheck(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {

        // SVC_MEMO
        if (hasValue(cMsg.svcMemoPk)) {
            SVC_MEMOTMsg svcMemoTMsg = NSAL0540Query.getInstance().getSvcMemoForUpdate(cMsg);
            if (svcMemoTMsg == null) {
                cMsg.setMessageInfo(NZZM0003E);
            }
            if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime.getValue(), cMsg.ezUpTimeZone.getValue(),
                    svcMemoTMsg.ezUpTime.getValue(), svcMemoTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NZZM0003E);
            }
        }

        // SVC_CONFIG_MSTR
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0540_BSMsg bSMsg = sMsg.B.no(i);

            SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = NSAL0540Query.getInstance().getSvcConfigMstrForUpdate(cMsg, bSMsg.svcConfigMstrPk_B0.getValue());

            if (svcConfigMstrTMsg == null) {
                cMsg.setMessageInfo(NZZM0003E);
            }
            if (!ZYPDateUtil.isSameTimeStamp(bSMsg.ezUpTime_B0.getValue(), bSMsg.ezUpTimeZone_B0.getValue(),
                    svcConfigMstrTMsg.ezUpTime.getValue(), svcConfigMstrTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NZZM0003E);
            }
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0540_ASMsg aSMsg = sMsg.A.no(i);

            if (!aSMsg.svcMachMstrPk_A0.getValue().equals(aSMsg.svcMachMstrPk_AP.getValue())) {
                // Accessory
                continue;
            }
            SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = NSAL0540Query.getInstance().getSvcConfigMstrForUpdate(cMsg, aSMsg.svcConfigMstrPk_A0.getValue());

            if (svcConfigMstrTMsg == null) {
                cMsg.setMessageInfo(NZZM0003E);
            }
            if (!ZYPDateUtil.isSameTimeStamp(aSMsg.ezUpTime_A0.getValue(), aSMsg.ezUpTimeZone_A0.getValue(),
                    svcConfigMstrTMsg.ezUpTime.getValue(), svcConfigMstrTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NZZM0003E);
            }
        }
    }

    private NSZC036001PMsg getNSZC036001PMsg(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {

        NSZC036001PMsg pMsg = new NSZC036001PMsg();

        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.svcSlnSq, cMsg.svcSlnSq);
        setValue(pMsg.svcSlnNm, cMsg.svcSlnNm);
        setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        setValue(pMsg.svcSlnUpdPsnCd, getContextUserInfo().getUserId());

        int apiLineCnt = 0;

        // delete Service Configuration
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0540_BSMsg bSMsg = sMsg.B.no(i);
            setValue(pMsg.xxSvcConfigList.no(apiLineCnt).svcConfigMstrPk, bSMsg.svcConfigMstrPk_B0);
            setValue(pMsg.xxSvcConfigList.no(apiLineCnt).delFlg, FLG_ON_Y);
            apiLineCnt++;
        }

        // insert update Service Configuration
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0540_ASMsg aSMsg = sMsg.A.no(i);
            // Main Unit
            if (aSMsg.svcMachMstrPk_A0.getValue().equals(aSMsg.svcMachMstrPk_AP.getValue())) {
                setValue(pMsg.xxSvcConfigList.no(apiLineCnt).svcConfigMstrPk, aSMsg.svcConfigMstrPk_A0);
                setValue(pMsg.xxSvcConfigList.no(apiLineCnt).delFlg, FLG_OFF_N);
                apiLineCnt++;
            }
        }

        pMsg.xxSvcConfigList.setValidCount(apiLineCnt);

        return pMsg;
    }

    private boolean createUpdateSvcMemo(NSAL0540CMsg cMsg) {

      if (hasValue(cMsg.svcMemoPk)) {
          updateSvcMemo(cMsg);
      } else {
          if (hasValue(cMsg.svcCmntTxt)) {
              createSvcMemo(cMsg);
          }
      }
      if (NSAL0540CommonLogic.isError(cMsg)) {
          return false;
      }
      return true;
    }

    private void updateSvcMemo(NSAL0540CMsg cMsg) {

        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
        setValue(svcMemoTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(svcMemoTMsg.svcMemoPk, cMsg.svcMemoPk);
        svcMemoTMsg = (SVC_MEMOTMsg) EZDTBLAccessor.findByKey(svcMemoTMsg);

        if (svcMemoTMsg != null) {
            setValue(svcMemoTMsg.svcCmntTxt, cMsg.svcCmntTxt);
            setValue(svcMemoTMsg.lastUpdUsrId, getContextUserInfo().getUserId());
            setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(TS_STORE_PATTERN));

            EZDTBLAccessor.update(svcMemoTMsg);
            if (!RTNCD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0031E , new String[]{SVC_MEMO});
            }
        }
    }

    private void createSvcMemo(NSAL0540CMsg cMsg) {

        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
        BigDecimal svcMemoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ);
        setValue(svcMemoTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(svcMemoTMsg.svcMemoPk, svcMemoPk);
        setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.SOLUTION_MEMO);
        setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.SOLUTION);
        // add start 2016/02/22 CSA Defect#3689
        String svcMemoRsnCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_MEMO_RSN_FOR_SLN, cMsg.glblCmpyCd.getValue());
        setValue(svcMemoTMsg.svcMemoRsnCd, svcMemoRsnCd);
        // add end 2016/02/22 CSA Defect#3689
        setValue(svcMemoTMsg.svcCmntTxt, cMsg.svcCmntTxt);
        setValue(svcMemoTMsg.lastUpdUsrId, getContextUserInfo().getUserId());
        setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(TS_STORE_PATTERN));
        setValue(svcMemoTMsg.svcMemoTrxNum, cMsg.svcSlnSq_NW.getValue().toString());
        setValue(svcMemoTMsg.svcMemoTrxNm, SVC_SLN_SQ);

        EZDTBLAccessor.insert(svcMemoTMsg);
        if (!RTNCD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0032E , new String[]{SVC_MEMO});
        }
    }
}
