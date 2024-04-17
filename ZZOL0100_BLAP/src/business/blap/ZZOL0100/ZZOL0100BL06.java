/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/28/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0100;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZOL0100.common.ZZOL0100CommonLogic;
import business.blap.ZZOL0100.constant.ZZOL0100Constant;
import business.db.MENU_INFOTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZOL0100BL06 extends S21BusinessHandler implements ZZOL0100Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZOL0100Scrn00_Add".equals(screenAplID)) {
                doProcess_ZZOL0100Scrn00_Add((ZZOL0100CMsg) cMsg, (ZZOL0100SMsg) sMsg);
            } else if ("ZZOL0100Scrn00_Upd".equals(screenAplID)) {
                doProcess_ZZOL0100Scrn00_Upd((ZZOL0100CMsg) cMsg, (ZZOL0100SMsg) sMsg);
            } else if ("ZZOL0100Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZOL0100Scrn00_CMN_Delete((ZZOL0100CMsg) cMsg, (ZZOL0100SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_ZZOL0100Scrn00_Add(ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {
        
        if (ZZOL0100CommonLogic.getGlbCmpNm(cMsg.glblCmpyCd.getValue(), cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }

        MENU_INFOTMsg tMsg = new MENU_INFOTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.menuInfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.MENU_INFO_SQ));
        tMsg.menuInfoTxt.setValue(cMsg.menuInfoTxt.getValue());
        tMsg.menuInfoVwbleFlg.setValue(chgCheckBox(cMsg.xxChkBox.getValue()));
        tMsg.menuEffFromDt.setValue(cMsg.menuEffFromDt.getValue());
        tMsg.menuEffFromTm.setValue(cMsg.menuEffFromTm_F3.getValue());
        tMsg.menuEffThruDt.setValue(cMsg.menuEffThruDt.getValue());
        tMsg.menuEffThruTm.setValue(cMsg.menuEffThruTm_T3.getValue());
        tMsg.menuInfoSortNum.setValue(cMsg.menuInfoSortNum.getValue());
        EZDTBLAccessor.create(tMsg);
        String sReturnCode = tMsg.getReturnCode();
        if (!sReturnCode.equals("0000")){
            cMsg.setMessageInfo("ZZZM9012E", new String[] {sReturnCode});
            return;
        }

        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();
        ZZOL0100CommonLogic.doClear(sGlobalCpyCd, cMsg, sMsg);

        if (ZZOL0100CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == true) {
            // information
            ZZOL0100CommonLogic.getInformationList(sGlobalCpyCd, cMsg, sMsg);   
        }

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Add"});          
    }

    private void doProcess_ZZOL0100Scrn00_Upd(ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {
        
        if (ZZOL0100CommonLogic.getGlbCmpNm(cMsg.glblCmpyCd.getValue(), cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }

        // Search MENU_INFO table by Primary Key
        MENU_INFOTMsg tMsg = new MENU_INFOTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.menuInfoPk.setValue(cMsg.menuInfoPk.getValue());
        tMsg = (MENU_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        if (tMsg == null) {
            cMsg.setMessageInfo("ZZZM9004E");
            return;
        }
        String timeBefore     = cMsg.ezUpTime.getValue();
        String timeZoneBefore = cMsg.ezUpTimeZone.getValue();
        String time     = tMsg.ezUpTime.getValue();
        String timeZone = tMsg.ezUpTimeZone.getValue();
        if(!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
            cMsg.setMessageInfo("ZZZM9004E");
            return;
        }

        tMsg.menuInfoTxt.setValue(cMsg.menuInfoTxt.getValue());
        tMsg.menuInfoVwbleFlg.setValue(chgCheckBox(cMsg.xxChkBox.getValue()));
        tMsg.menuEffFromDt.setValue(cMsg.menuEffFromDt.getValue());
        tMsg.menuEffFromTm.setValue(cMsg.menuEffFromTm_F3.getValue());
        tMsg.menuEffThruDt.setValue(cMsg.menuEffThruDt.getValue());
        tMsg.menuEffThruTm.setValue(cMsg.menuEffThruTm_T3.getValue());
        tMsg.menuInfoSortNum.setValue(cMsg.menuInfoSortNum.getValue());
        EZDTBLAccessor.update(tMsg);
        String sReturnCode = tMsg.getReturnCode();
        if (!sReturnCode.equals("0000")){
            cMsg.setMessageInfo("ZZZM9013E", new String[] {sReturnCode});
            return;
        }
        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();
        ZZOL0100CommonLogic.doClear(sGlobalCpyCd, cMsg, sMsg);
        if (ZZOL0100CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == true) {
            // information
            ZZOL0100CommonLogic.getInformationList(sGlobalCpyCd, cMsg, sMsg);   
        }

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update"});        
    }

    private void doProcess_ZZOL0100Scrn00_CMN_Delete(ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {

        copyCmsgToSmsg(cMsg, sMsg);
        
        if (ZZOL0100CommonLogic.getGlbCmpNm(cMsg.glblCmpyCd.getValue(), cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }

        // Not input Check
        if (chkNoInput(sMsg) == true) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {"CHECK BOX"});
            return;
        }
        // Check Box
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String sCheckBox = sMsg.A.no(i).xxChkBox_A1.getValue();
            if (sCheckBox.equals("Y")) {

                // Search MENU_INFO table by Primary Key
                MENU_INFOTMsg tMsg = new MENU_INFOTMsg();

                tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                tMsg.menuInfoPk.setValue(sMsg.A.no(i).menuInfoPk_A1.getValue());
                tMsg = (MENU_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (tMsg == null) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
                String timeBefore     = sMsg.A.no(i).ezUpTime_A1.getValue();
                String timeZoneBefore = sMsg.A.no(i).ezUpTimeZone_A1.getValue();
                String time     = tMsg.ezUpTime.getValue();
                String timeZone = tMsg.ezUpTimeZone.getValue();
                if(!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                String sReturnCode = tMsg.getReturnCode();
                if (!sReturnCode.equals("0000")){
                    cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                    return;
                }
            }
        }

        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();
        ZZOL0100CommonLogic.doClear(sGlobalCpyCd, cMsg, sMsg);
        if (ZZOL0100CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == true) {
            // information
            ZZOL0100CommonLogic.getInformationList(sGlobalCpyCd, cMsg, sMsg);   
        }

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete"});
        
    }

    /**
     * Method name: copyCmsgToSmsg
     * <dd>The method explanation: copy Check Box from CMsg onto SMsg
     * <dd>Remarks:
     * @param cMsg ZZOL0100CMsg
     * @param sMsg ZZOL0100SMsg
     */
    private void copyCmsgToSmsg(ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {

        // copy Check Box from CMsg onto SMsg
        int pagenation = cMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {
                sMsg.A.no(pagenation + i).xxChkBox_A1.setValue(cMsg.A.no(i).xxChkBox_A1.getValue());
            }
        }
    }

    /**
     * Method name: chkNoInput
     * <dd>The method explanation: Active Process No Input check. 
     * <dd>Remarks:
     * @param sMsg ZZOL0100SMsg
     * @return boolean true or false
     */
    private boolean chkNoInput(ZZOL0100SMsg sMsg) {

        boolean errFlg = true;

        // Check Box
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String sCheckBox = sMsg.A.no(i).xxChkBox_A1.getValue();
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
     * @param hms String
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
