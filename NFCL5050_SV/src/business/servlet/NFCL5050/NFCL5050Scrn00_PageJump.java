/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/04/2010   Fujitsu         FAP)D.Kato      Create          N/A
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 *</pre>
 */
package business.servlet.NFCL5050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5050.NFCL5050CMsg;
import business.servlet.NFCL5050.common.NFCL5050CommonLogic;
import business.servlet.NFCL5050.constant.NFCL5050Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * NFCL5050Scrn00_PageJump.
 */
public class NFCL5050Scrn00_PageJump extends S21CommonHandler implements NFCL5050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5050CMsg bizMsg = null;

        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

        bizMsg = NFCL5050CommonLogic.setRequestData_NFCL5050Scrn00_PageNext(scrnMsg);

        int pagenationFrom = scrnMsg.xxPageShowFromNum.getValueInt();
        scrnMsg.xxPageShowFromNum_H1.setValue(pagenationFrom);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        S21BatchSearchPagenationScrnSupport.setRequestDataForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);

        bizMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt());
        bizMsg.xxPageShowToNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
        bizMsg.xxPageShowOfNum.setValue(scrnMsg.xxPageShowOfNum.getValueInt());
        bizMsg.xxPageShowCurNum.setValue(scrnMsg.xxPageShowCurNum.getValueInt());
        bizMsg.xxPageShowTotNum.setValue(scrnMsg.xxPageShowTotNum.getValueInt());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

        S21TableColorController tblColor = new S21TableColorController("NFCL5050Scrn00", scrnMsg);
        ZYPTableUtil.clear(scrnMsg.A);
        tblColor.clearRowsBG("A", scrnMsg.A);
//        tblColor.clearRowsBG("A1", scrnMsg.A);
//        tblColor.clearRowsBG("A2", scrnMsg.A);

        if (bizMsg != null) {
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL5050CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NFCL5050CommonLogic.initialize(this, scrnMsg);

        if (SUMMARY_STATUS_Y.equals(scrnMsg.xxRsltStsCd.getValue())) {
            NFCL5050CommonLogic.scrnItemControl_NFCL5050Scrn00_SearchInvoice(scrnMsg);
        }

        NFCL5050CommonLogic.setRowBg(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
//        tblColor.setAlternateRowsBG("A1", scrnMsg.A);
//        tblColor.setAlternateRowsBG("A2", scrnMsg.A);

    }
}
