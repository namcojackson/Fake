/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/08/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0120;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZOL0120.common.ZZOL0120CommonLogic;
import business.blap.ZZOL0120.constant.ZZOL0120Constant;
import business.db.MENU_PROCTMsg;
import business.db.MENU_PROC_GRPTMsg;
import business.db.MENU_PROC_RELNTMsg;
import business.db.UP_TABTMsg;
import business.db.UP_TAB_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZOL0120BL06 extends S21BusinessHandler implements ZZOL0120Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZOL0120Scrn00_Add".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn00_Add((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn00_Upd".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn00_Upd((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn00_CMN_Delete((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn01_Add".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn01_Add((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn01_Upd".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn01_Upd((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn01_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn01_CMN_Delete((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn02_Add".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn02_Add((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn02_Upd".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn02_Upd((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn02_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn02_CMN_Delete((ZZOL0120CMsg) cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_ZZOL0120Scrn00_Add(ZZOL0120CMsg cMsg) {
        
        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();
        if (ZZOL0120CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }
        if (cMsg.A.getValidCount() >= cMsg.A.length()) {
            String max = Integer.toString(cMsg.A.length() + 1);
            cMsg.setMessageInfo("ZZZM9011E", new String[] {max});
            return;
        }

        // create MENU_PROC_GRP
//        String menuProcGrpCd = ZZOL0120CommonLogic.getProcGroupMax(sGlobalCpyCd);
        MENU_PROC_GRPTMsg tMsg = new MENU_PROC_GRPTMsg();

        tMsg.glblCmpyCd.setValue(sGlobalCpyCd);
        tMsg.menuProcGrpCd.setValue(cMsg.menuProcGrpCd.getValue());
        tMsg.menuProcGrpNm.setValue(cMsg.menuProcGrpNm.getValue());
        tMsg.menuProcGrpSortNum.setValue(cMsg.menuProcGrpSortNum.getValue());
        tMsg.menuProcGrpDescTxt.setValue(cMsg.menuProcGrpDescTxt.getValue());
        tMsg.wfAppNm.setValue(cMsg.wfAppNm.getValue());
        tMsg.wfPrmTxt.setValue("");
        tMsg.wfUsbleFlg.setValue("N");
        EZDTBLAccessor.create(tMsg);
        String sReturnCode = tMsg.getReturnCode();
        if (!sReturnCode.equals("0000")) {
            cMsg.setMessageInfo("ZZZM9012E", new String[] {sReturnCode});
            return;
        }

        ZZOL0120CommonLogic.doClear00(sGlobalCpyCd, cMsg);
        if (ZZOL0120CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == true) {
            ZZOL0120CommonLogic.getProcGroupList(sGlobalCpyCd, cMsg);
        }

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Add"});
    }

    private void doProcess_ZZOL0120Scrn00_Upd(ZZOL0120CMsg cMsg) {
        
        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();
        if (ZZOL0120CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }
        // update MENU_PROC_GRP
        MENU_PROC_GRPTMsg tMsg = new MENU_PROC_GRPTMsg();

        tMsg.glblCmpyCd.setValue(sGlobalCpyCd);
        tMsg.menuProcGrpCd.setValue(cMsg.menuProcGrpCd.getValue());
        tMsg = (MENU_PROC_GRPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        if (tMsg == null) {
            cMsg.setMessageInfo("ZZZM9004E");
            return;
        }
        String timeBefore     = cMsg.ezUpTime.getValue();
        String timeZoneBefore = cMsg.ezUpTimeZone.getValue();
        String time     = tMsg.ezUpTime.getValue();
        String timeZone = tMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
            cMsg.setMessageInfo("ZZZM9004E");
            return;
        }

        tMsg.menuProcGrpNm.setValue(cMsg.menuProcGrpNm.getValue());
        tMsg.menuProcGrpSortNum.setValue(cMsg.menuProcGrpSortNum.getValue());
        tMsg.menuProcGrpDescTxt.setValue(cMsg.menuProcGrpDescTxt.getValue());
        tMsg.wfAppNm.setValue(cMsg.wfAppNm.getValue());

        EZDTBLAccessor.update(tMsg);
        String sReturnCode = tMsg.getReturnCode();
        if (!sReturnCode.equals("0000")) {
            cMsg.setMessageInfo("ZZZM9013E", new String[] {sReturnCode});
            return;
        }

        ZZOL0120CommonLogic.doClear00(sGlobalCpyCd, cMsg);
        if (ZZOL0120CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == true) {
            ZZOL0120CommonLogic.getProcGroupList(sGlobalCpyCd, cMsg);
        }

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update"});
    }

    private void doProcess_ZZOL0120Scrn00_CMN_Delete(ZZOL0120CMsg cMsg) {

        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();
        if (ZZOL0120CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }
        // Not input Check
        if (chkNoInput00(cMsg) == true) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {"CHECK BOX"});
            return;
        }
        // Check Box
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            String sCheckBox = cMsg.A.no(i).xxChkBox_A2.getValue();
            if (sCheckBox.equals("Y")) {

                // logicalRemove MENU_PROC_GRP
                MENU_PROC_GRPTMsg tMsg = new MENU_PROC_GRPTMsg();

                tMsg.glblCmpyCd.setValue(sGlobalCpyCd);
                tMsg.menuProcGrpCd.setValue(cMsg.A.no(i).menuProcGrpCd_A2.getValue());
                tMsg = (MENU_PROC_GRPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (tMsg == null) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
                String timeBefore     = cMsg.A.no(i).ezUpTime_A2.getValue();
                String timeZoneBefore = cMsg.A.no(i).ezUpTimeZone_A2.getValue();
                String time     = tMsg.ezUpTime.getValue();
                String timeZone = tMsg.ezUpTimeZone.getValue();
                if (!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                String sReturnCode = tMsg.getReturnCode();
                if (!sReturnCode.equals("0000")) {
                    cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                    return;
                }

                String menuProcGrpCd = cMsg.A.no(i).menuProcGrpCd_A2.getValue();
                sReturnCode = doMenuProcRemove(sGlobalCpyCd, menuProcGrpCd, cMsg);
                if (!sReturnCode.equals("0000")) {
                    cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                    return;
                }
            }
        }

        ZZOL0120CommonLogic.doClear00(sGlobalCpyCd, cMsg);
        if (ZZOL0120CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == true) {
            ZZOL0120CommonLogic.getProcGroupList(sGlobalCpyCd, cMsg);
        }

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete"});

    }

    private void doProcess_ZZOL0120Scrn01_Add(ZZOL0120CMsg cMsg) {

        if (cMsg.B.getValidCount() >= cMsg.B.length()) {
            String max = Integer.toString(cMsg.B.length() + 1);
            cMsg.setMessageInfo("ZZZM9011E", new String[] {max});
            return;
        }
        
        String groupCode = cMsg.menuProcGrpCd_B1.getValue();

        // create MENU_PROC
        String glblCmpyCd = cMsg.glblCmpyCd_BK.getValue();
        String sMenuProcId = ZZOL0120CommonLogic.getProcMax(glblCmpyCd, groupCode);
        MENU_PROCTMsg tMsg = new MENU_PROCTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_BK.getValue());
        tMsg.menuProcId.setValue(sMenuProcId);
        tMsg.menuProcNm.setValue(cMsg.menuProcNm_B1.getValue());
        tMsg.othSysFlg.setValue(chgCheckBox(cMsg.xxChkBox_B1.getValue()));
        tMsg.othSysUrl.setValue(cMsg.othSysUrl_B1.getValue());

        EZDTBLAccessor.create(tMsg);
        String sReturnCode = tMsg.getReturnCode();
        if (!sReturnCode.equals("0000")) {
            cMsg.setMessageInfo("ZZZM9012E", new String[] {sReturnCode});
            return;
        }

        // create MENU_PROC_RELN
        MENU_PROC_RELNTMsg tMsgReln = new MENU_PROC_RELNTMsg();

        tMsgReln.glblCmpyCd_G0.setValue(cMsg.glblCmpyCd_BK.getValue());
        tMsgReln.menuProcGrpCd.setValue(cMsg.menuProcGrpCd_B1.getValue());
        tMsgReln.glblCmpyCd_P0.setValue(cMsg.glblCmpyCd_BK.getValue());
        tMsgReln.menuProcId.setValue(sMenuProcId);

        EZDTBLAccessor.create(tMsgReln);
        sReturnCode = tMsgReln.getReturnCode();
        if (!sReturnCode.equals("0000")) {
            cMsg.setMessageInfo("ZZZM9012E", new String[] {sReturnCode});
            return;
        }

        ZZOL0120CommonLogic.doClear01(cMsg);

        String menuProcGrpCd = cMsg.menuProcGrpCd_B1.getValue();
        ZZOL0120CommonLogic.getProcList(glblCmpyCd, menuProcGrpCd, cMsg);

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Add"});
    }

    private void doProcess_ZZOL0120Scrn01_Upd(ZZOL0120CMsg cMsg) {

        // update MENU_PROC
        MENU_PROCTMsg tMsg = new MENU_PROCTMsg();
        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_BK.getValue());
        tMsg.menuProcId.setValue(cMsg.menuProcId_B1.getValue());
        tMsg = (MENU_PROCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        if (tMsg == null) {
            cMsg.setMessageInfo("ZZZM9004E");
            return;
        }
        String timeBefore     = cMsg.ezUpTime_B1.getValue();
        String timeZoneBefore = cMsg.ezUpTimeZone_B1.getValue();
        String time     = tMsg.ezUpTime.getValue();
        String timeZone = tMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
            cMsg.setMessageInfo("ZZZM9004E");
            return;
        }

        tMsg.menuProcNm.setValue(cMsg.menuProcNm_B1.getValue());
        tMsg.othSysFlg.setValue(chgCheckBox(cMsg.xxChkBox_B1.getValue()));
        tMsg.othSysUrl.setValue(cMsg.othSysUrl_B1.getValue());

        EZDTBLAccessor.update(tMsg);
        String sReturnCode = tMsg.getReturnCode();
        if (!sReturnCode.equals("0000")) {
            cMsg.setMessageInfo("ZZZM9013E", new String[] {sReturnCode});
            return;
        }
        ZZOL0120CommonLogic.doClear01(cMsg);

        String glblCmpyCd = cMsg.glblCmpyCd_BK.getValue();
        String menuProcGrpCd = cMsg.menuProcGrpCd_B1.getValue();
        ZZOL0120CommonLogic.getProcList(glblCmpyCd, menuProcGrpCd, cMsg);

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update"});
    }

    private void doProcess_ZZOL0120Scrn01_CMN_Delete(ZZOL0120CMsg cMsg) {

        // Not input Check
        if (chkNoInput01(cMsg) == true) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {"CHECK BOX"});
            return;
        }
        // Check Box
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String sCheckBox = cMsg.B.no(i).xxChkBox_B2.getValue();
            if (sCheckBox.equals("Y")) {

                // logicalRemove MENU_PROC
                MENU_PROCTMsg tMsg = new MENU_PROCTMsg();

                tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_BK.getValue());
                tMsg.menuProcId.setValue(cMsg.B.no(i).menuProcId_B2.getValue());
                tMsg = (MENU_PROCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (tMsg == null) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
                String timeBefore     = cMsg.B.no(i).ezUpTime_B2.getValue();
                String timeZoneBefore = cMsg.B.no(i).ezUpTimeZone_B2.getValue();
                String time     = tMsg.ezUpTime.getValue();
                String timeZone = tMsg.ezUpTimeZone.getValue();
                if (!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                String sReturnCode = tMsg.getReturnCode();
                if (!sReturnCode.equals("0000")) {
                    cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                    return;
                }

                // logicalRemove MENU_PROC_RELN
                MENU_PROC_RELNTMsg tMsgReln = new MENU_PROC_RELNTMsg();

                tMsgReln.glblCmpyCd_G0.setValue(cMsg.glblCmpyCd_BK.getValue());
                tMsgReln.menuProcGrpCd.setValue(cMsg.menuProcGrpCd_B1.getValue());
                tMsgReln.glblCmpyCd_P0.setValue(cMsg.glblCmpyCd_BK.getValue());
                tMsgReln.menuProcId.setValue(cMsg.B.no(i).menuProcId_B2.getValue());
                EZDTBLAccessor.logicalRemove(tMsgReln);
                sReturnCode = tMsgReln.getReturnCode();
                if (!sReturnCode.equals("0000")) {
                    cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                    return;
                }

                // logicalRemove UP_TAB, UP_TAB_RELN
                String glblCmpyCd = cMsg.glblCmpyCd_BK.getValue();
                String menuProcId = cMsg.B.no(i).menuProcId_B2.getValue();
                sReturnCode = doUpTabRemove(glblCmpyCd, menuProcId, cMsg);
                if (!sReturnCode.equals("0000")) {
                    cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                    return;
                }
            }
        }

        ZZOL0120CommonLogic.doClear01(cMsg);

        String glblCmpyCd = cMsg.glblCmpyCd_BK.getValue();
        String menuProcGrpCd = cMsg.menuProcGrpCd_B1.getValue();
        ZZOL0120CommonLogic.getProcList(glblCmpyCd, menuProcGrpCd, cMsg);

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete"});

    }

    private void doProcess_ZZOL0120Scrn02_Add(ZZOL0120CMsg cMsg) {

        if (cMsg.C.getValidCount() >= cMsg.C.length()) {
            String max = Integer.toString(cMsg.C.length() + 1);
            cMsg.setMessageInfo("ZZZM9011E", new String[] {max});
            return;
        }

        // Create UP_TAB
        String glblCmpyCd = cMsg.glblCmpyCd_BK.getValue();
        String sUpTabCd = ZZOL0120CommonLogic.getUpTabMax(glblCmpyCd);
        UP_TABTMsg tMsg = new UP_TABTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_BK.getValue());
        tMsg.upTabCd.setValue(sUpTabCd);
        tMsg.upTabNm.setValue(cMsg.upTabNm_C1.getValue());
        tMsg.bizAppId.setValue(cMsg.bizAppId_C1.getValue());
        tMsg.bizAppNm.setValue(cMsg.bizAppNm_C1.getValue());
        tMsg.myProcUsbleFlg.setValue(chgCheckBox(cMsg.myProcUsbleFlg_C1.getValue()));

        EZDTBLAccessor.create(tMsg);
        String sReturnCode = tMsg.getReturnCode();
        if (!sReturnCode.equals("0000")) {
            cMsg.setMessageInfo("ZZZM9012E", new String[] {sReturnCode});
            return;
        }

        // Create UP_TAB_RELN
        UP_TAB_RELNTMsg tMsgReln = new UP_TAB_RELNTMsg();

        tMsgReln.glblCmpyCd_P0.setValue(cMsg.glblCmpyCd_BK.getValue());
        tMsgReln.menuProcId.setValue(cMsg.menuProcId_C1.getValue());
        tMsgReln.glblCmpyCd_T0.setValue(cMsg.glblCmpyCd_BK.getValue());
        tMsgReln.upTabCd.setValue(sUpTabCd);
        tMsgReln.upTabSortNum.setValue(cMsg.upTabSortNum_C1.getValue());
        tMsgReln.upTabUsbleFlg.setValue(chgCheckBox(cMsg.upTabUsbleFlg_C1.getValue()));
        EZDTBLAccessor.create(tMsgReln);
        sReturnCode = tMsgReln.getReturnCode();
        if (!sReturnCode.equals("0000")) {
            cMsg.setMessageInfo("ZZZM9012E", new String[] {sReturnCode});
            return;
        }

        ZZOL0120CommonLogic.doClear02(cMsg);

        String menuProcId = cMsg.menuProcId_C1.getValue();
        ZZOL0120CommonLogic.getUpTabList(glblCmpyCd, menuProcId, cMsg);

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Add"});
    }

    private void doProcess_ZZOL0120Scrn02_Upd(ZZOL0120CMsg cMsg) {

        // update UP_TAB
        UP_TABTMsg tMsg = new UP_TABTMsg();
        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_BK.getValue());
        tMsg.upTabCd.setValue(cMsg.upTabCd_C1.getValue());
        tMsg = (UP_TABTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        if (tMsg == null) {
            cMsg.setMessageInfo("ZZZM9004E");
            return;
        }
        String timeBefore     = cMsg.ezUpTime_C1.getValue();
        String timeZoneBefore = cMsg.ezUpTimeZone_C1.getValue();
        String time     = tMsg.ezUpTime.getValue();
        String timeZone = tMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
            cMsg.setMessageInfo("ZZZM9004E");
            return;
        }

        tMsg.upTabNm.setValue(cMsg.upTabNm_C1.getValue());
        tMsg.bizAppId.setValue(cMsg.bizAppId_C1.getValue());
        tMsg.bizAppNm.setValue(cMsg.bizAppNm_C1.getValue());
        tMsg.myProcUsbleFlg.setValue(chgCheckBox(cMsg.myProcUsbleFlg_C1.getValue()));

        EZDTBLAccessor.update(tMsg);
        String sReturnCode = tMsg.getReturnCode();
        if (!sReturnCode.equals("0000")) {
            cMsg.setMessageInfo("ZZZM9013E", new String[] {sReturnCode});
            return;
        }

        // update UP_TAB_RELN
        UP_TAB_RELNTMsg tMsgReln = new UP_TAB_RELNTMsg();

        tMsgReln.glblCmpyCd_P0.setValue(cMsg.glblCmpyCd_BK.getValue());
        tMsgReln.menuProcId.setValue(cMsg.menuProcId_C1.getValue());
        tMsgReln.glblCmpyCd_T0.setValue(cMsg.glblCmpyCd_BK.getValue());
        tMsgReln.upTabCd.setValue(cMsg.upTabCd_C1.getValue());
        tMsgReln = (UP_TAB_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsgReln);

        if (tMsgReln == null) {
            cMsg.setMessageInfo("ZZZM9004E");
            return;
        }
        tMsgReln.upTabSortNum.setValue(cMsg.upTabSortNum_C1.getValue());
        tMsgReln.upTabUsbleFlg.setValue(chgCheckBox(cMsg.upTabUsbleFlg_C1.getValue()));
        EZDTBLAccessor.update(tMsgReln);
        sReturnCode = tMsgReln.getReturnCode();
        if (!sReturnCode.equals("0000")) {
            cMsg.setMessageInfo("ZZZM9013E", new String[] {sReturnCode});
            return;
        }

        ZZOL0120CommonLogic.doClear02(cMsg);

        String glblCmpyCd = cMsg.glblCmpyCd_BK.getValue();
        String menuProcId = cMsg.menuProcId_C1.getValue();
        ZZOL0120CommonLogic.getUpTabList(glblCmpyCd, menuProcId, cMsg);

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update"});
    }

    private void doProcess_ZZOL0120Scrn02_CMN_Delete(ZZOL0120CMsg cMsg) {

        // Not input Check
        if (chkNoInput02(cMsg) == true) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {"CHECK BOX"});
            return;
        }
        // Check Box
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            String sCheckBox = cMsg.C.no(i).xxChkBox_C2.getValue();
            if (sCheckBox.equals("Y")) {

                // logicalRemove UP_TAB
                UP_TABTMsg tMsg = new UP_TABTMsg();

                tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_BK.getValue());
                tMsg.upTabCd.setValue(cMsg.C.no(i).upTabCd_C2.getValue());
                tMsg = (UP_TABTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (tMsg == null) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
                String timeBefore     = cMsg.C.no(i).ezUpTime_C2.getValue();
                String timeZoneBefore = cMsg.C.no(i).ezUpTimeZone_C2.getValue();
                String time     = tMsg.ezUpTime.getValue();
                String timeZone = tMsg.ezUpTimeZone.getValue();
                if (!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                String sReturnCode = tMsg.getReturnCode();
                if (!sReturnCode.equals("0000")) {
                    cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                    return;
                }

                // logicalRemove UP_TAB_RELN
                UP_TAB_RELNTMsg tMsgReln = new UP_TAB_RELNTMsg();

                tMsgReln.glblCmpyCd_P0.setValue(cMsg.glblCmpyCd_BK.getValue());
                tMsgReln.menuProcId.setValue(cMsg.menuProcId_C1.getValue());
                tMsgReln.glblCmpyCd_T0.setValue(cMsg.glblCmpyCd_BK.getValue());
                tMsgReln.upTabCd.setValue(cMsg.C.no(i).upTabCd_C2.getValue());
                EZDTBLAccessor.logicalRemove(tMsgReln);
                sReturnCode = tMsgReln.getReturnCode();
                if (!sReturnCode.equals("0000")) {
                    cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                    return;
                }
            }
        }
        ZZOL0120CommonLogic.doClear02(cMsg);

        String glblCmpyCd = cMsg.glblCmpyCd_BK.getValue();
        String menuProcId = cMsg.menuProcId_C1.getValue();
        ZZOL0120CommonLogic.getUpTabList(glblCmpyCd, menuProcId, cMsg);

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete"});

    }

    /**
     * Method name: doMenuProcRemove
     * <dd>The method explanation: No Input check. 
     * <dd>Remarks:
     * @param String glblCmpyCd
     * @param String menuProcGrpCd
     * @param cMsg ZZOL0120CMsg
     * @return String returnCode
     */
    private String doMenuProcRemove(String glblCmpyCd, String menuProcGrpCd, ZZOL0120CMsg cMsg) {

        String returnCode = "0000";

        ZZOL0120CommonLogic.getProcList(glblCmpyCd, menuProcGrpCd, cMsg);
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {

            // logicalRemove MENU_PROC
            MENU_PROCTMsg tMsg = new MENU_PROCTMsg();

            tMsg.glblCmpyCd.setValue(glblCmpyCd);
            tMsg.menuProcId.setValue(cMsg.B.no(i).menuProcId_B2.getValue());
            EZDTBLAccessor.logicalRemove(tMsg);
            String sReturnCode = tMsg.getReturnCode();
            if (!sReturnCode.equals("0000")) {
                return returnCode;
            }

            // logicalRemove MENU_PROC_RELN
            MENU_PROC_RELNTMsg tMsgReln = new MENU_PROC_RELNTMsg();

            tMsgReln.glblCmpyCd_G0.setValue(glblCmpyCd);
            tMsgReln.menuProcGrpCd.setValue(menuProcGrpCd);
            tMsgReln.glblCmpyCd_P0.setValue(glblCmpyCd);
            tMsgReln.menuProcId.setValue(cMsg.B.no(i).menuProcId_B2.getValue());
            EZDTBLAccessor.logicalRemove(tMsgReln);
            sReturnCode = tMsgReln.getReturnCode();
            if (!sReturnCode.equals("0000")) {
                return returnCode;
            }

            sReturnCode = doUpTabRemove(glblCmpyCd, cMsg.B.no(i).menuProcId_B2.getValue(), cMsg);
            if (!sReturnCode.equals("0000")) {
                return returnCode;
            }
        }
        return returnCode;
    }

    /**
     * Method name: doUpTabRemove
     * <dd>The method explanation: No Input check. 
     * <dd>Remarks:
     * @param String glblCmpyCd
     * @param String menuProcId
     * @param cMsg ZZOL0120CMsg
     * @return String returnCode
     */
    private String doUpTabRemove(String glblCmpyCd, String menuProcId, ZZOL0120CMsg cMsg) {

        String returnCode = "0000";

        ZZOL0120CommonLogic.getUpTabList(glblCmpyCd, menuProcId, cMsg);

        for (int i = 0; i < cMsg.C.getValidCount(); i++) {

            // logicalRemove UP_TAB
            UP_TABTMsg tMsg = new UP_TABTMsg();

            tMsg.glblCmpyCd.setValue(glblCmpyCd);
            tMsg.upTabCd.setValue(cMsg.C.no(i).upTabCd_C2.getValue());
            EZDTBLAccessor.logicalRemove(tMsg);
            String sReturnCode = tMsg.getReturnCode();
            if (!sReturnCode.equals("0000")) {
                return returnCode;
            }

            // logicalRemove UP_TAB_RELN
            UP_TAB_RELNTMsg tMsgReln = new UP_TAB_RELNTMsg();

            tMsgReln.glblCmpyCd_P0.setValue(glblCmpyCd);
            tMsgReln.menuProcId.setValue(menuProcId);
            tMsgReln.glblCmpyCd_T0.setValue(glblCmpyCd);
            tMsgReln.upTabCd.setValue(cMsg.C.no(i).upTabCd_C2.getValue());
            EZDTBLAccessor.logicalRemove(tMsgReln);
            sReturnCode = tMsgReln.getReturnCode();
            if (!sReturnCode.equals("0000")) {
                cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                return returnCode;
            }
        }

        return returnCode;
    }

    /**
     * Method name: chkNoInput00
     * <dd>The method explanation: No Input check. 
     * <dd>Remarks:
     * @param cMsg ZZOL0120CMsg
     * @return boolean true or false
     */
    private boolean chkNoInput00(ZZOL0120CMsg cMsg) {

        boolean errFlg = true;

        // Check Box
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            String sCheckBox = cMsg.A.no(i).xxChkBox_A2.getValue();
            if (sCheckBox.equals("Y")) {
                errFlg = false;
                break;
            }
        }

        return errFlg;
    }

    /**
     * Method name: chkNoInput01
     * <dd>The method explanation: No Input check. 
     * <dd>Remarks:
     * @param cMsg ZZOL0120CMsg
     * @return boolean true or false
     */
    private boolean chkNoInput01(ZZOL0120CMsg cMsg) {

        boolean errFlg = true;

        // Check Box
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String sCheckBox = cMsg.B.no(i).xxChkBox_B2.getValue();
            if (sCheckBox.equals("Y")) {
                errFlg = false;
                break;
            }
        }

        return errFlg;
    }

    /**
     * Method name: chkNoInput02
     * <dd>The method explanation: No Input check. 
     * <dd>Remarks:
     * @param cMsg ZZOL0120CMsg
     * @return boolean true or false
     */
    private boolean chkNoInput02(ZZOL0120CMsg cMsg) {

        boolean errFlg = true;

        // Check Box
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            String sCheckBox = cMsg.C.no(i).xxChkBox_C2.getValue();
            if (sCheckBox.equals("Y")) {
                errFlg = false;
                break;
            }
        }

        return errFlg;
    }
    /**
     * Method name: check
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param check String
     * @return String
     */
    public static String chgCheckBox(String check) {

        if (check == null || check.length() == 0) {
            return "N";
        }
        if (check.equals("Y")) {
            return check;
        } else {
            return "N";
        }

    }

}
