/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0760;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0760.common.NSAL0760CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Contract Status History
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NSAL0760BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0760CMsg cMsg = (NSAL0760CMsg) arg0;
        NSAL0760SMsg sMsg = (NSAL0760SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0760_INIT".equals(screenAplID)) {
                doProcess_NSAL0760_INIT(cMsg, sMsg);
            } else if ("NSAL0760Scrn00_ChargeDisp".equals(screenAplID)) {
                doProcess_NSAL0760Scrn00_ChargeDisp(cMsg, sMsg);
            } else if ("NSAL0760Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NSAL0760Scrn00_CMN_Close(cMsg, sMsg);
            } else if ("NSAL0760Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0760Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL0760Scrn00_EffDisp".equals(screenAplID)) {
                doProcess_NSAL0760Scrn00_EffDisp(cMsg, sMsg);
            } else if ("NSAL0760Scrn00_OpenWin_AuditAtail".equals(screenAplID)) {
                doProcess_NSAL0760Scrn00_OpenWin_AuditAtail(cMsg, sMsg);
            } else if ("NSAL0760Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0760Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0760Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0760Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0760Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0760Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL0760_NSAL0770".equals(screenAplID)) {
                doProcess_NSAL0760_NSAL0770(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0760_INIT(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {

        NSAL0760CommonLogic.clearMsg(cMsg, sMsg);

        NSAL0760CommonLogic.setInitParams(cMsg, sMsg);

        doProcess_NSAL0760Scrn00_Search(cMsg, sMsg);
    }

    private void doProcess_NSAL0760Scrn00_CMN_Clear(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {

        NSAL0760CommonLogic.clearMsg(cMsg, sMsg);

        NSAL0760CommonLogic.setInitParams(cMsg, sMsg);

        doProcess_NSAL0760Scrn00_Search(cMsg, sMsg);
    }

    private void doProcess_NSAL0760Scrn00_ChargeDisp(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {
        int selRow = cMsg.xxRowNum.getValueInt();
        int targetRow = cMsg.A.no(selRow).xxRowNum_A1.getValueInt() - 1;
        setValue(sMsg.B.no(targetRow).xxBtnFlg_A1, cMsg.A.no(selRow).xxBtnFlg_A1);
        NSAL0760CommonLogic.setViewData(sMsg);

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0760CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
    }

    private void doProcess_NSAL0760Scrn00_CMN_Close(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {
        return;
    }

    private void doProcess_NSAL0760Scrn00_EffDisp(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {
        int selRow = cMsg.xxRowNum.getValueInt();
        int targetRow = cMsg.A.no(selRow).xxRowNum_A1.getValueInt() - 1;
        setValue(sMsg.B.no(targetRow).xxBtnFlg_A2, cMsg.A.no(selRow).xxBtnFlg_A2);
        NSAL0760CommonLogic.setViewData(sMsg);

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0760CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
    }

    private void doProcess_NSAL0760Scrn00_OpenWin_AuditAtail(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {

    }

    private void doProcess_NSAL0760Scrn00_PageNext(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0760CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0760CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0760Scrn00_PagePrev(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0760CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0760CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0760Scrn00_Search(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {

        if (NSAL0760CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }

        NSAL0760CommonLogic.getSearchData(cMsg, sMsg);

        NSAL0760CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0760_NSAL0770(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {

    }

}
