/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCRN_STATUS_N;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0770.NFCL0770CMsg;
import business.servlet.NFCL0770.common.NFCL0770CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 * 2022/09/02   Hitachi         A.Kohinata      Update          QC#60403
 *</pre>
 */
public class NFCL0770Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;
        // add start 2022/09/02 QC#60403
        scrnMsg.xxListNum_LY.setValue(0);
        // add end 2022/09/02 QC#60403
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_H1, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);

        if (null != scrnMsg.A && 0 != scrnMsg.A.getValidCount()) {
            //if (SCRN_MODE_ENTRY.equals(scrnMsg.xxModeInd_H1.getValue())) {
                // mod start 2022/09/02 QC#60403
                //NFCL0770CommonLogic.commonInputCheck(scrnMsg);
                NFCL0770CommonLogic.commonInputCheck(scrnMsg, true);
                // mod end 2022/09/02 QC#60403
            //}
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770CMsg bizMsg = null;

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        bizMsg = NFCL0770CommonLogic.setRequestData_NFCL0770Scrn00_PageNext(scrnMsg);

        int pagenationFrom = scrnMsg.xxPageShowFromNum_H1.getValueInt();
        scrnMsg.xxPageShowFromNum_H1.setValue(pagenationFrom);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        S21BatchSearchPagenationScrnSupport.setRequestDataForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_H1, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);

        bizMsg.xxPageShowFromNum_H1.setValue(scrnMsg.xxPageShowFromNum_H1.getValueInt());
        bizMsg.xxPageShowToNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
        bizMsg.xxPageShowOfNum.setValue(scrnMsg.xxPageShowOfNum.getValueInt());
        bizMsg.xxPageShowCurNum.setValue(scrnMsg.xxPageShowCurNum.getValueInt());
        bizMsg.xxPageShowTotNum.setValue(scrnMsg.xxPageShowTotNum.getValueInt());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL0770CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NFCL0770CommonLogic.initialize(this, scrnMsg);

        // Details position Initialize
        scrnMsg.xxListNum_LX.setValue(0);

        NFCL0770CommonLogic.commonBtnControl_NFCL0770Scrn00_PageNext(this, scrnMsg);

        if (SCRN_STATUS_N.equals(scrnMsg.xxRsltStsCd_H2.getValue())) {
            NFCL0770CommonLogic.setErrorScreen_NFCL0770(this, scrnMsg);
            setButtonConfirmMsg("CMN_Return", null, null, 1);
            //if (SCRN_MODE_SUBMIT.equals(scrnMsg.xxModeInd_H1.getValue())) {
                this.setButtonProperties("OpenWin_MailEntry", "OpenWin_MailEntry", "Mail Entry", 1, null);
            //} else {
                // do nothing
            //}

        } else {

            //if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
                NFCL0770CommonLogic.setCancelScreen_NFCL0770(this, scrnMsg);
                NFCL0770CommonLogic.setCheckAllBtn(this, scrnMsg);
            //} else {
                NFCL0770CommonLogic.setEntryScreen_NFCL0770(this, scrnMsg);
            //}

            setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);
        }

        NFCL0770CommonLogic.setRowBg(scrnMsg);

        //if (!SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL0770CommonLogic.protectAddDetailLine(scrnMsg, this);
        //}

        // del start 2022/09/02 QC#60403
        //scrnMsg.putErrorScreen();
        // del end 2022/09/02 QC#60403

        if (null != bizMsg) {
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }

        NFCL0770CommonLogic.protectModeOne(scrnMsg, this);
        NFCL0770CommonLogic.setAppFracDigit(scrnMsg);
        // add start 2022/09/02 QC#60403
        scrnMsg.putErrorScreen();
        // add end 2022/09/02 QC#60403
    }
}
