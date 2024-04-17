/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCRN_MODE_CANCEL;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCRN_STATUS_N;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0770.NFCL0770CMsg;
import business.servlet.NFCL0770.common.NFCL0770CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 * 2022/09/02   Hitachi         A.Kohinata      Update          QC#60403
 *</pre>
 */
public class NFCL0770Scrn00_CalcGrossAmount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        // add start 2022/09/02 QC#60403
        scrnMsg.xxListNum_LY.setValue(0);
        // add end 2022/09/02 QC#60403

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

        NFCL0770CMsg bizMsg = null;

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        bizMsg = NFCL0770CommonLogic.setRequestData_NFCL0770Scrn00_CalcGrossAmount(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // del start 2022/09/02 QC#60403
        //scrnMsg.putErrorScreen();
        // del end 2022/09/02 QC#60403

        NFCL0770CommonLogic.transMsgCheck(scrnMsg);

        NFCL0770CommonLogic.initialize(this, scrnMsg);

        NFCL0770CommonLogic.commonBtnControl_NFCL0770Scrn00_CalcGrossAmount(this, scrnMsg);

        if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL0770CommonLogic.setCancelScreen_NFCL0770(this, scrnMsg);

        } else {
            NFCL0770CommonLogic.setEntryScreen_NFCL0770(this, scrnMsg);
        }

        if (!SCRN_STATUS_N.equals(scrnMsg.xxRsltStsCd_H1.getValue())) {
            setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);
        }

        NFCL0770CommonLogic.setRowBg(scrnMsg);
        NFCL0770CommonLogic.setCheckAllBtn(this, scrnMsg);
        if (!SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL0770CommonLogic.protectAddDetailLine(scrnMsg, this);
        }
        NFCL0770CommonLogic.protectModeOne(scrnMsg, this);
        scrnMsg.putErrorScreen();

    }
}
