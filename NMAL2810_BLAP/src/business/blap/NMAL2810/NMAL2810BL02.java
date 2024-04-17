/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2810;

import java.util.List;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.common.EZDSMsg;
import business.blap.NMAL2810.constant.NMAL2810Constant;
import business.parts.NMZC003001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2810BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2810BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2810CMsg bizMsg = (NMAL2810CMsg) cMsg;
            NMAL2810SMsg glblMsg = (NMAL2810SMsg) sMsg;

            if ("NMAL2810Scrn00_AddressValidation".equals(screenAplID)) {
                doProcess_NMAL2810Scrn00_AddressValidation(bizMsg, glblMsg);

            } else if ("NMAL2810_INIT".equals(screenAplID)) {
                doProcess_NMAL2810_INIT(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2810_INIT(NMAL2810CMsg bizMsg, NMAL2810SMsg glblMsg) {

        S21SsmEZDResult ssmResult;

        if (NMAL2810Constant.MODE_REF_LOC.equals(bizMsg.xxModeCd.getValue()) || NMAL2810Constant.MODE_SET_LOC.equals(bizMsg.xxModeCd.getValue())) {
            // Mode : For Merge to Location ID
            ssmResult = NMAL2810Query.getInstance().getDetailLocationInfo(bizMsg, glblMsg);

            if (ssmResult.isCodeNormal()) {
                EZDMsg.copy(bizMsg, "S", bizMsg, "R");
                EZDMsg.copy(bizMsg, "SC", bizMsg, "RC");
            }
        } else {
            // Mode : For Merge to Prospect#(For SFDC# / OASIS)
            ssmResult = NMAL2810Query.getInstance().getDetailProspectInfo(bizMsg, glblMsg);

            if (ssmResult.isCodeNormal()) {

                if (!ZYPCommonFunc.hasValue(bizMsg.firstLineAddr_A) && !ZYPCommonFunc.hasValue(bizMsg.ctyAddr_A) && !ZYPCommonFunc.hasValue(bizMsg.stCd_A) && !ZYPCommonFunc.hasValue(bizMsg.postCd_A)) {
                    // set Before
                    EZDMsg.copy(bizMsg, "B", bizMsg, "R");
                    EZDMsg.copy(bizMsg, "BC", bizMsg, "RC");
                } else {
                    // set After
                    EZDMsg.copy(bizMsg, "A", bizMsg, "R");
                    EZDMsg.copy(bizMsg, "AC", bizMsg, "RC");
                }
            }
        }
    }

    /**
     * AddressValidation Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2810Scrn00_AddressValidation(NMAL2810CMsg bizMsg, NMAL2810SMsg glblMsg) {

        boolean isError = false;
        boolean isWarning = false;

        NMZC003001PMsg param = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, getGlobalCompanyCode());
        setPMsgValue(param.firstLineAddr, bizMsg.xxRadioBtn_1, bizMsg.firstLineAddr_L, bizMsg.firstLineAddr_R);
        setPMsgValue(param.scdLineAddr, bizMsg.xxRadioBtn_2, bizMsg.scdLineAddr_L, bizMsg.scdLineAddr_R);
        setPMsgValue(param.ctyAddr, bizMsg.xxRadioBtn_5, bizMsg.ctyAddr_L, bizMsg.ctyAddr_R);
        setPMsgValue(param.stCd, bizMsg.xxRadioBtn_6, bizMsg.stCd_L, bizMsg.stCd_R);
        setPMsgValue(param.postCd, bizMsg.xxRadioBtn_7, bizMsg.postCd_L, bizMsg.postCd_R);
        ZYPEZDItemValueSetter.setValue(param.ctryCd, NMAL2810Constant.NMZC003001_CTRY_CD);

        new NMZC003001().execute(param, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(param)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(param);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                isError = true;
                break;
            }
        } else {
            if (NMZC003001Constant.RTRN_CD_ERROR.equals(param.xxVldStsCd_01.getValue())) {
                bizMsg.xxVldStsCd_1.setValue(NMAL2810Constant.NMZC003001_RTRN_E);
                isError = true;
            } else if (NMZC003001Constant.RTRN_CD_SUGGEST.equals(param.xxVldStsCd_01.getValue())) {
                bizMsg.xxVldStsCd_1.setValue(NMAL2810Constant.NMZC003001_RTRN_S);
                isWarning = true;
            }

            if (NMZC003001Constant.RTRN_CD_ERROR.equals(param.xxVldStsCd_02.getValue())) {
                bizMsg.xxVldStsCd_2.setValue(NMAL2810Constant.NMZC003001_RTRN_E);
                isError = true;
            } else if (NMZC003001Constant.RTRN_CD_SUGGEST.equals(param.xxVldStsCd_02.getValue())) {
                bizMsg.xxVldStsCd_2.setValue(NMAL2810Constant.NMZC003001_RTRN_S);
                isWarning = true;
            }

            if (NMZC003001Constant.RTRN_CD_ERROR.equals(param.xxVldStsCd_03.getValue())) {
                bizMsg.xxVldStsCd_3.setValue(NMAL2810Constant.NMZC003001_RTRN_E);
                isError = true;
            } else if (NMZC003001Constant.RTRN_CD_SUGGEST.equals(param.xxVldStsCd_03.getValue())) {
                bizMsg.xxVldStsCd_3.setValue(NMAL2810Constant.NMZC003001_RTRN_S);
                isWarning = true;
            }

            if (NMZC003001Constant.RTRN_CD_ERROR.equals(param.xxVldStsCd_04.getValue())) {
                bizMsg.xxVldStsCd_4.setValue(NMAL2810Constant.NMZC003001_RTRN_E);
                isError = true;
            } else if (NMZC003001Constant.RTRN_CD_SUGGEST.equals(param.xxVldStsCd_04.getValue())) {
                bizMsg.xxVldStsCd_4.setValue(NMAL2810Constant.NMZC003001_RTRN_S);
                isWarning = true;
            }

            if (NMZC003001Constant.RTRN_CD_ERROR.equals(param.xxVldStsCd_05.getValue())) {
                bizMsg.xxVldStsCd_5.setValue(NMAL2810Constant.NMZC003001_RTRN_E);
                isError = true;
            } else if (NMZC003001Constant.RTRN_CD_SUGGEST.equals(param.xxVldStsCd_05.getValue())) {
                bizMsg.xxVldStsCd_5.setValue(NMAL2810Constant.NMZC003001_RTRN_S);
                isWarning = true;
            }
        }

        if (isError) {
            bizMsg.xxRsltCd.setValue(NMAL2810Constant.NMZC003001_RTRN_E);
        } else if (isWarning) {
            bizMsg.xxRsltCd.setValue(NMAL2810Constant.NMZC003001_RTRN_S);
        }
    }

    /**
     * setItemOutValue
     * @param param EZDBBigDecimalItem
     * @param radioItem EZDBBigDecimalItem
     * @param leftItem EZDBBigDecimalItem
     * @param rightItem EZDBBigDecimalItem
     */
    private static final void setPMsgValue(EZDPStringItem param, EZDCBigDecimalItem radioItem, EZDCStringItem leftItem, EZDCStringItem rightItem) {

        if (NMAL2810Constant.RADIO_PROS.compareTo(radioItem.getValue()) == 0) {
            // set Prospect param
            if (ZYPCommonFunc.hasValue(leftItem)) {
                ZYPEZDItemValueSetter.setValue(param, leftItem);
            }
        } else {
            // set Merge to param
            if (ZYPCommonFunc.hasValue(rightItem)) {
                param.setValue(rightItem.getValue());
            }
        }
    }
}
