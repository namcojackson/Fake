/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.common.NFCL2760CommonLogic;
import business.servlet.NFCL2760.constant.NFCL2760Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2019/09/02   Fujitsu         H.Ikeda         Update          QC#53138
 * 2022/01/06   CITS            G.Delgado       Update          QC#59329
 *</pre>
 */
public class NFCL2760Scrn00_CalcGrossAmount extends S21CommonHandler implements NFCL2760Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        // START 2022/01/06 G.Delgado [QC#59329, ADD]
        // Details position Initialize
        scrnMsg.xxListNum_LY.setValue(0);
        // END 2022/01/06 G.Delgado [QC#59329, ADD]

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).xxChkBox.getValue().equals("Y")) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).cashAppGlDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).arAdjTpCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1);
            }
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760CMsg bizMsg = null;

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        bizMsg = NFCL2760CommonLogic.setRequestData_NFCL2760Scrn00_CalcGrossAmount(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2019/09/03 H.Ikeda [QC#53138, DEL]
        //scrnMsg.putErrorScreen();
        // START 2019/09/03 H.Ikeda [QC#53138, DEL]
        NFCL2760CommonLogic.transMsgCheck(scrnMsg);

        NFCL2760CommonLogic.initialize(this, scrnMsg);

        NFCL2760CommonLogic.commonBtnControl_NFCL2760Scrn00_CalcGrossAmount(this, scrnMsg);

        if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL2760CommonLogic.setCancelScreen_NFCL2760(this, scrnMsg);

        } else {
            NFCL2760CommonLogic.setEntryScreen_NFCL2760(this, scrnMsg);
        }

        if (!SCRN_STATUS_N.equals(scrnMsg.xxRsltStsCd_H1.getValue())) {
            setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);
        } else {
            // do nothing
        }

        NFCL2760CommonLogic.setRowBg(scrnMsg);
        NFCL2760CommonLogic.setCheckAllBtn(this, scrnMsg);
        if (!SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL2760CommonLogic.protectAddDetailLine(scrnMsg, this);
        }
        NFCL2760CommonLogic.protectModeOne(scrnMsg, this);
        scrnMsg.putErrorScreen();

    }
}
