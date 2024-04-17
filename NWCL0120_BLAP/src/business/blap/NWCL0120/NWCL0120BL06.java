/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0120;

import static business.blap.NWCL0120.constant.NWCL0120Constant.NWAM0242E;
import static business.blap.NWCL0120.constant.NWCL0120Constant.NWBM0024I;
import static business.blap.NWCL0120.constant.NWCL0120Constant.NWCM0118W;
import static business.blap.NWCL0120.constant.NWCL0120Constant.NWZM0634E;
import static business.blap.NWCL0120.constant.NWCL0120Constant.ZZZM9013E;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWCL0120.common.NWCL0120CommonLogic;
import business.db.INV_PRT_CTRLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWCL0120BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWCL0120BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWCL0120CMsg bizMsg = (NWCL0120CMsg) cMsg;
            NWCL0120SMsg glblMsg = (NWCL0120SMsg) sMsg;

            if ("NWCL0120Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NWCL0120CMsg) cMsg, (NWCL0120SMsg) sMsg);
            } else if ("NWCL0120Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NWCL0120CMsg) cMsg, (NWCL0120SMsg) sMsg);
            } else if ("NWCL0120Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWCL0120Scrn00_CMN_Submit(bizMsg, glblMsg);
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
    private void doProcess_NWCL0120Scrn00_CMN_Submit(NWCL0120CMsg bizMsg, NWCL0120SMsg glblMsg) {

        NWCL0120CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        boolean checkErrFlg = true;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A0.getValue())) {
                checkErrFlg = false;
            }
        }
        if (checkErrFlg) {
            bizMsg.setMessageInfo(NWZM0634E);
            return;
        }

        // Confirm Invoice Print Control Update
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue())) {
            bizMsg.setMessageInfo(NWCM0118W);
            return;
        }

        // Invoice Print Control Update
        INV_PRT_CTRLTMsg invPrtCtrlTMsg = null;
        List<INV_PRT_CTRLTMsg> updInvPrtCtrlTMsgAry = new ArrayList<INV_PRT_CTRLTMsg>();
        List<INV_PRT_CTRLTMsg> prvInvPrtCtrlTMsgAry = new ArrayList<INV_PRT_CTRLTMsg>();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A0.getValue())) {

                String invNum = glblMsg.A.no(i).invNum_A0.getValue();
                for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                    if (invNum != null && invNum.equals(glblMsg.B.no(j).invNum_B0.getValue())) {
                        invPrtCtrlTMsg = new INV_PRT_CTRLTMsg();
                        invPrtCtrlTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
                        invPrtCtrlTMsg.invPrtCtrlPk.setValue(glblMsg.B.no(j).invPrtCtrlPk_B0.getValue());
                        invPrtCtrlTMsg.ezUpTime.setValue(glblMsg.B.no(j).ezUpTime_B0.getValue());
                        invPrtCtrlTMsg.ezUpTimeZone.setValue(glblMsg.B.no(j).ezUpTimeZone_B0.getValue());
                        updInvPrtCtrlTMsgAry.add(invPrtCtrlTMsg);
                    }
                }

            }
        }

        if (updInvPrtCtrlTMsgAry.size() > 0) {

            for (INV_PRT_CTRLTMsg curInvPrtCtrlTMsg : updInvPrtCtrlTMsgAry) {

                INV_PRT_CTRLTMsg prvInvPrtCtrlTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(curInvPrtCtrlTMsg);

                if (!ZYPDateUtil.isSameTimeStamp(prvInvPrtCtrlTMsg.ezUpTime.getValue(), prvInvPrtCtrlTMsg.ezUpTimeZone.getValue(), curInvPrtCtrlTMsg.ezUpTime.getValue(), curInvPrtCtrlTMsg.ezUpTimeZone.getValue())) {

                    bizMsg.setMessageInfo(NWAM0242E);
                    return;
                }
                prvInvPrtCtrlTMsg.splyPgmInvRvwFlg.setValue(ZYPConstant.FLG_ON_Y);
                prvInvPrtCtrlTMsgAry.add(prvInvPrtCtrlTMsg);
            }

            int res = S21FastTBLAccessor.update((INV_PRT_CTRLTMsg[]) prvInvPrtCtrlTMsgAry.toArray(new INV_PRT_CTRLTMsg[0]));
            if (res != prvInvPrtCtrlTMsgAry.size()) {
                bizMsg.setMessageInfo(ZZZM9013E, new String[] {String.valueOf(res) });
                return;
            }
        }

        bizMsg.setMessageInfo(NWBM0024I);
    }

}
