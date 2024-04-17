/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.common.NFCL2760CommonLogic;
import business.servlet.NFCL2760.constant.NFCL2760Constant;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2019/09/02   Fujitsu         H.Ikeda         Update          QC#53138
 * 2022/01/06   CITS            G.Delgado       Update          QC#59329
 * 2022/01/26   Hitachi         A.Kohinata      Update          QC#59641
 *</pre>
 */
public class NFCL2760Scrn00_PageJump extends S21CommonHandler implements NFCL2760Constant {

	@Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        // START 2022/01/06 G.Delgado [QC#59329, ADD]
        // Details position Initialize
        scrnMsg.xxListNum_LY.setValue(0);
        // END 2022/01/06 G.Delgado [QC#59329, ADD]

        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_H1, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);

        if (null != scrnMsg.A && 0 != scrnMsg.A.getValidCount()) {
            //if (SCRN_MODE_ENTRY.equals(scrnMsg.xxModeInd_H1.getValue())) {
                // mod start 2022/01/26 QC#59641
                //NFCL2760CommonLogic.commonInputCheck(scrnMsg);
                NFCL2760CommonLogic.commonInputCheck(scrnMsg, true);
                // mod end 2022/01/26 QC#59641
            //}
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760CMsg bizMsg = null;

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        bizMsg = NFCL2760CommonLogic.setRequestData_NFCL2760Scrn00_PageNext(scrnMsg);

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

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL2760CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NFCL2760CommonLogic.initialize(this, scrnMsg);

        // Details position Initialize
        scrnMsg.xxListNum_LX.setValue(0);

        NFCL2760CommonLogic.commonBtnControl_NFCL2760Scrn00_PageNext(this, scrnMsg);

        if (SCRN_STATUS_N.equals(scrnMsg.xxRsltStsCd_H2.getValue())) {
            NFCL2760CommonLogic.setErrorScreen_NFCL2760(this, scrnMsg);
            setButtonConfirmMsg("CMN_Return", null, null, 1);
            //if (SCRN_MODE_SUBMIT.equals(scrnMsg.xxModeInd_H1.getValue())) {
                this.setButtonProperties("OpenWin_MailEntry", "OpenWin_MailEntry", "Mail Entry", 1, null);
            //} else {
                // do nothing
            //}

        } else {

            //if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
                NFCL2760CommonLogic.setCancelScreen_NFCL2760(this, scrnMsg);
                NFCL2760CommonLogic.setCheckAllBtn(this, scrnMsg);
            //} else {
                NFCL2760CommonLogic.setEntryScreen_NFCL2760(this, scrnMsg);
            //}

            setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);
        }

        NFCL2760CommonLogic.setRowBg(scrnMsg);

        //if (!SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL2760CommonLogic.protectAddDetailLine(scrnMsg, this);
        //}
        // START 2019/09/03 H.Ikeda [QC#53138, DEL]
        //scrnMsg.putErrorScreen();
        // END   2019/09/03 H.Ikeda [QC#53138, DEL]
        if (null != bizMsg) {
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            } else {
                // do nothing
            }
        } else {
            // do nothing
        }

        NFCL2760CommonLogic.protectModeOne(scrnMsg, this);
        NFCL2760CommonLogic.setAppFracDigit(scrnMsg);
        // START 2019/09/03 H.Ikeda [QC#53138, ADD]
        scrnMsg.putErrorScreen();
        // END   2016/09/03 H.Ikeda [QC#53138, ADD]
    }
}
