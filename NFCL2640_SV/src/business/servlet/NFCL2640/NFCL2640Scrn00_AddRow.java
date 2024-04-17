/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2640;

import static business.servlet.NFCL2640.constant.NFCL2640Constant.BIZ_ID;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.NFCM0580E;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.NFCM0797E;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.NFCM0800E;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2640.NFCL2640CMsg;
import business.servlet.NFCL2640.common.NFCL2640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Statement Schedule Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         M.Ohno          Create          N/A
 * 2016/12/22   Fujitsu         H.Ikeda         Update          QC#12865
 * 2019/02/26   Fujitsu         H.Ikeda         Update          QC#30302
 *</pre>
 */
public class NFCL2640Scrn00_AddRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2640BMsg scrnMsg = (NFCL2640BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_BC);
        scrnMsg.addCheckItem(scrnMsg.arStmtDt_AD);
        scrnMsg.addCheckItem(scrnMsg.lateFeeCalcFlg);

        if (scrnMsg.xxChkBox_BC.isClear()) {

            if (!ZYPCommonFunc.hasValue(scrnMsg.arStmtDt_AD)) {
                String[] msgParams = new String[] { //
                        scrnMsg.xxChkBox_BC.getNameForMessage(), //
                        scrnMsg.arStmtDt_AD.getNameForMessage()};
                scrnMsg.arStmtDt_AD.setErrorInfo(1, NFCM0797E, msgParams);

            } else {

                String arStmtDay = scrnMsg.arStmtDt_AD.getValue().substring(6, 8);
                // Start 2019/2/26 H.Ikeda [QC#30302, MOD]
                //if (!arStmtDay.equals(scrnMsg.arStmtIssCycleCd.getValue())) {
                if (!arStmtDay.equals(NFCL2640CommonLogic.getArStmtIssCycleCd(scrnMsg.arStmtIssCycleCd.getValue(), scrnMsg.arStmtDt_AD.getValue()))) {
                // END   2019/2/26 H.Ikeda [QC#30302, MOD]
                    String[] msgParams = new String[] { //
                            scrnMsg.arStmtDt_AD.getNameForMessage(), //
                            scrnMsg.arStmtIssCycleCd.getNameForMessage()};
                    scrnMsg.arStmtDt_AD.setErrorInfo(1, NFCM0800E, msgParams);
                }

                String[] msgParams = new String[] {scrnMsg.arStmtDt_AD.getNameForMessage()};
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    NFCL2640_ABMsg scrnMsgA = scrnMsg.A.no(i);
                    if (scrnMsg.arStmtDt_AD.getValue().equals(scrnMsgA.arStmtDt_A1.getValue())) {
                        scrnMsg.arStmtDt_AD.setErrorInfo(1, NFCM0580E, msgParams);
                        scrnMsgA.arStmtDt_A1.setErrorInfo(1, NFCM0580E, msgParams);
                        break;
                    }
                }
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2640BMsg scrnMsg = (NFCL2640BMsg) bMsg;

        NFCL2640CMsg bizMsg = new NFCL2640CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2640BMsg scrnMsg = (NFCL2640BMsg) bMsg;
        NFCL2640CMsg bizMsg = (NFCL2640CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.arStmtDt_AD);
        scrnMsg.putErrorScreen();

        if (bizMsg.A.getValidCount() > 0) {
            NFCL2640CommonLogic.addScreenField(scrnMsg);
            // Start 2016/12/22 H.Ikeda [QC#12865,MOD]
            NFCL2640CommonLogic.activeSubmitButton(this, scrnMsg);
            // End   2016/12/22 H.Ikeda [QC#12865,MOD]
        }
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A);
    }
}
