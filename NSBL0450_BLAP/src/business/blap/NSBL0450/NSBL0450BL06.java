/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0450;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0450.common.NSBL0450CommonLogic;
import business.db.SVC_SUPPL_TASKTMsg;
import business.parts.NSZC058001PMsg;
import static business.blap.NSBL0450.constant.NSBL0450Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC058001.constant.NSZC058001Constant.TIME_FORMAT;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.api.NSZ.NSZC058001.NSZC058001;
import com.canon.cusa.s21.api.NSZ.NSZC058001.constant.NSZC058001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 * 2015/12/10   Hitachi         T.Iwamoto         Update          QC#1796
 * 2017/03/01   Hitachi         A.Kohinata        Update          QC#17608
 *</pre>
 */
public class NSBL0450BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSBL0450Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0450Scrn00_CMN_Submit((NSBL0450CMsg) cMsg, (NSBL0450SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Submit)
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    private void doProcess_NSBL0450Scrn00_CMN_Submit(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {

        NSBL0450CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        // checkInput
        if (!checkInput(sMsg)) {
            cMsg.setMessageInfo(NSBM0133E);
            return;
        }

        updateSupplementalTask(cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    private boolean checkInput(NSBL0450SMsg sMsg) {
        if (sMsg.A.getValidCount() == 0) {
            return false;
        }
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).submtFlg_A.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * updateSupplementalTask
     * @param sMsg NSBL0450SMsg
     * @return Change Line : true
     */
    private void updateSupplementalTask(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).submtFlg_A.getValue())) {
                SVC_SUPPL_TASKTMsg tMsg = geSupplementalTask(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).svcSupplTaskNum_A.getValue());
                if (tMsg == null) {
                    cMsg.setMessageInfo(NSBM0075E, new String[] {TBL_SUPPL, FIELD_SUPPL, sMsg.A.no(i).svcSupplTaskNum_A.getValue() });
                    return;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime.getValue(), sMsg.A.no(i).ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(NSBM0075E, new String[] {TBL_SUPPL, FIELD_SUPPL, sMsg.A.no(i).svcSupplTaskNum_A.getValue() });
                        return;
                    }

                    NSZC058001PMsg apiPMsg = new NSZC058001PMsg();
                    ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.xxProcMd, NSZC058001Constant.MODE_UPDATE);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.svcSupplTaskNum, sMsg.A.no(i).svcSupplTaskNum_A);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.svcSupplTaskStsCd, sMsg.A.no(i).svcSupplTaskStsCd_A);
                    // [QC#1796,ADD]START
                    ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskNum, sMsg.A.no(i).svcTaskNum_A);
                    // [QC#1796,ADD]END
                    ZYPEZDItemValueSetter.setValue(apiPMsg.svcSupplFromDt, sMsg.A.no(i).svcSupplFromDt_A);
                    String svcSupplFromTm = ZYPCommonFunc.concatString(ZYPCommonFunc.subByteString(sMsg.A.no(i).xxDtTm_A1.getValue(), SUB_2), ZYPCommonFunc.subByteString(sMsg.A.no(i).xxDtTm_A1.getValue(), SUB_3, SUB_5), SECOND_00);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.svcSupplFromTm, svcSupplFromTm);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.svcSupplToDt, sMsg.A.no(i).svcSupplToDt_A);
                    if (ZYPCommonFunc.hasValue((sMsg.A.no(i).xxDtTm_A2))) {
                        String svcSupplToTm = ZYPCommonFunc.concatString(ZYPCommonFunc.subByteString(sMsg.A.no(i).xxDtTm_A2.getValue(), SUB_2), ZYPCommonFunc.subByteString(sMsg.A.no(i).xxDtTm_A2.getValue(), SUB_3, SUB_5), SECOND_00);
                        ZYPEZDItemValueSetter.setValue(apiPMsg.svcSupplToTm, svcSupplToTm);
                    }

                    ZYPEZDItemValueSetter.setValue(apiPMsg.svcTrvlTmNum, convertTime(sMsg.A.no(i).xxDtTm_A3.getValue()));
                    ZYPEZDItemValueSetter.setValue(apiPMsg.svcSupplTmNum, convertTime(sMsg.A.no(i).xxDtTm_A4.getValue()));
                    ZYPEZDItemValueSetter.setValue(apiPMsg.durnTmNum, convertTime(sMsg.A.no(i).xxDtTm_A5.getValue()));
                    ZYPEZDItemValueSetter.setValue(apiPMsg.updPsnCd, getUserProfileService().getContextUserInfo().getUserId());
                    ZYPEZDItemValueSetter.setValue(apiPMsg.updDt, cMsg.slsDt);
                    String procTm = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.updTm, procTm);

                    // add start 2017/03/01 CSA Defect#17608
                    if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_S.getValue())) {
                        convertTimeUpdateData(apiPMsg, sMsg.A.no(i).techCd_A.getValue());
                    }
                    // add end 2017/03/01 CSA Defect#17608

                    NSZC058001 callSupplementalUpdateApi = new NSZC058001();
                    callSupplementalUpdateApi.execute(apiPMsg, ONBATCH_TYPE.ONLINE);
                    if (S21ApiUtil.isXxMsgId(apiPMsg)) {
                        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
                        S21ApiMessage msg = msgList.get(0);
                        String msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();
                        cMsg.setMessageInfo(NSBM0132E, new String[] {sMsg.A.no(i).svcSupplTaskNum_A.getValue(), getRtnMsg(msgId, msgPrms) });
                        return;
                    }
                }
            }
        }
    }

    /**
     * get geSupplementalTask By Primary Key
     * @param glblCmpyCd String
     * @param svcTaskNum String
     * @return SVC_SUPPL_TASKTMsg
     */
    private SVC_SUPPL_TASKTMsg geSupplementalTask(String glblCmpyCd, String svcTaskNum) {
        SVC_SUPPL_TASKTMsg prmTMsg = new SVC_SUPPL_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcSupplTaskNum, svcTaskNum);
        return (SVC_SUPPL_TASKTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * convertTime
     * @param svcTaskNum String
     * @return minute
     */
    private BigDecimal convertTime(String time) {
        if (!ZYPCommonFunc.hasValue(time)) {
            return null;
        }
        if (!timeFormatCheck(time)) {
            return null;
        }

        int minute = Integer.parseInt(ZYPCommonFunc.subByteString(time, SUB_2)) * MINUTE + Integer.parseInt(ZYPCommonFunc.subByteString(time, SUB_3, SUB_5));
        return BigDecimal.valueOf(minute);
    }

    /**
     * Time Formata Check
     * @param checkTarget String
     * @return
     */
    private static boolean timeFormatCheck(String checkTarget) {
        if (checkTarget.length() != LENGTH_5) {
            return false;
        }
        if (checkTarget.matches(CHECK_TIME_FORMAT)) {
            return true;
        }
        return false;
    }

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
     */
    public static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (ZYPCommonFunc.hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    // add start 2017/03/01 CSA Defect#17608
    private void convertTimeUpdateData(NSZC058001PMsg apiPMsg, String techCd) {

        String[] convertTime = NSBL0450CommonLogic.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, apiPMsg.svcSupplFromDt.getValue(), apiPMsg.svcSupplFromTm.getValue(), techCd);
        if (convertTime != null) {
            setValue(apiPMsg.svcSupplFromDt, convertTime[0]);
            setValue(apiPMsg.svcSupplFromTm, convertTime[1]);
        } else {
            apiPMsg.svcSupplFromDt.clear();
            apiPMsg.svcSupplFromTm.clear();
        }

        convertTime = NSBL0450CommonLogic.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, apiPMsg.svcSupplToDt.getValue(), apiPMsg.svcSupplToTm.getValue(), techCd);
        if (convertTime != null) {
            setValue(apiPMsg.svcSupplToDt, convertTime[0]);
            setValue(apiPMsg.svcSupplToTm, convertTime[1]);
        } else {
            apiPMsg.svcSupplToDt.clear();
            apiPMsg.svcSupplToTm.clear();
        }
    }
    // add end 2017/03/01 CSA Defect#17608
}
