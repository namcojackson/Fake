/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0060;

import static business.blap.NWWL0060.constant.NWWL0060Constant.NWWM0020E;
import static business.blap.NWWL0060.constant.NWWL0060Constant.NWWM0021E;
import static business.blap.NWWL0060.constant.NWWL0060Constant.NZZM0003E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWWL0060.common.NWWL0060CommonLogic;
import business.db.NTFY_SBSCRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWWL0060BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0060BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWWL0060CMsg bizMsg = (NWWL0060CMsg) cMsg;
            NWWL0060SMsg glblMsg = (NWWL0060SMsg) sMsg;

            if ("NWWL0060Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWWL0060Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0060Scrn00_CMN_Submit(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg) {

        NWWL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWWL0060_ASMsg glblAMsg = glblMsg.A.no(i);

            if (!ZYPCommonFunc.hasValue(glblAMsg.ntfySbscrPk_A.getValue())) {

                // insert mode
                NTFY_SBSCRTMsg inTMsg = new NTFY_SBSCRTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inTMsg.ntfySbscrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.NTFY_SBSCR_SQ));

                if (ZYPCommonFunc.hasValue(glblAMsg.ntfySbscrFlg_A)) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.ntfySbscrFlg, ZYPConstant.FLG_ON_Y);

                } else {
                    ZYPEZDItemValueSetter.setValue(inTMsg.ntfySbscrFlg, ZYPConstant.FLG_OFF_N);

                }

                ZYPEZDItemValueSetter.setValue(inTMsg.ntfyHdrPk, glblAMsg.ntfyHdrPk_A.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.ntfySbscrPsnCd, glblMsg.psnCd.getValue());

                EZDTBLAccessor.insert(inTMsg);

                if (!RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                    glblAMsg.ntfySbscrFlg_A.setErrorInfo(1, NWWM0021E, new String[] {"NTFY_SBSCR" });
                    return;
                }

            } else {

                for (int j = 0; j < glblMsg.B.getValidCount(); j++) {

                    if (ZYPCommonFunc.hasValue(glblMsg.B.no(j).ntfySbscrPk_A.getValue()) //
                            && glblMsg.B.no(j).ntfySbscrPk_A.getValue().compareTo(glblAMsg.ntfySbscrPk_A.getValue()) == 0) {

                        if (NWWL0060CommonLogic.checkChangeValue(glblMsg.B.no(j).ntfySbscrFlg_A.getValue(), glblAMsg.ntfySbscrFlg_A.getValue())) {
                            // update
                            NTFY_SBSCRTMsg inTMsg = new NTFY_SBSCRTMsg();
                            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
                            ZYPEZDItemValueSetter.setValue(inTMsg.ntfySbscrPk, glblAMsg.ntfySbscrPk_A.getValue());
                            inTMsg = (NTFY_SBSCRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);

                            // Check time stamp
                            if (!ZYPDateUtil.isSameTimeStamp(glblAMsg.ezUpTime_A.getValue(), glblAMsg.ezUpTimeZone_A.getValue(), inTMsg.ezUpTime.getValue(), inTMsg.ezUpTimeZone.getValue())) {
                                glblAMsg.ntfySbscrFlg_A.setErrorInfo(1, NZZM0003E);
                                return;
                            }

                            if (ZYPCommonFunc.hasValue(glblAMsg.ntfySbscrFlg_A)) {
                                ZYPEZDItemValueSetter.setValue(inTMsg.ntfySbscrFlg, ZYPConstant.FLG_ON_Y);

                            } else {
                                ZYPEZDItemValueSetter.setValue(inTMsg.ntfySbscrFlg, ZYPConstant.FLG_OFF_N);

                            }

                            EZDTBLAccessor.update(inTMsg);

                            if (!RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                                glblAMsg.ntfySbscrFlg_A.setErrorInfo(1, NWWM0020E, new String[] {"NTFY_SBSCR" });
                                return;
                            }
                        }

                        break;

                    }
                }
            }
        }
    }

}
