/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0770.NFCL0770CMsg;
import business.servlet.NFCL0770.common.NFCL0770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 * 2022/09/02   Hitachi         A.Kohinata      Update          QC#60403
 *</pre>
 */
public class NFCL0770Scrn00_AddOneLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        // add start 2022/09/02 QC#60403
        scrnMsg.xxListNum_LY.setValue(0);
        // add end 2022/09/02 QC#60403

        NFCL0770CommonLogic.chkSubmit(scrnMsg, "NFCL0770Scrn00_AddOneLine");
        scrnMsg.putErrorScreen();

        //if (null != scrnMsg.A && 0 != scrnMsg.A.getValidCount()) {
        //    if (SCRN_MODE_ENTRY.equals(scrnMsg.xxModeInd_H1.getValue())) {
        //        NFCL0770CommonLogic.commonInputCheck(scrnMsg);
        //    } else {
        //        // do nothing
        //    }
        //} else {
            // do nothing
        //}

        if (null != scrnMsg.A && 0 != scrnMsg.A.getValidCount()) {
            //if (SCRN_MODE_ENTRY.equals(scrnMsg.xxModeInd_H1.getValue())) {
                NFCL0770CommonLogic.commonInputCheck(scrnMsg, true);
            //}
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;
        NFCL0770CMsg bizMsg = null;

        bizMsg = NFCL0770CommonLogic.setRequestData_NFCL0770Scrn00_AddOneLine();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx_H1, BigDecimal.valueOf(scrnMsg.A.getValidCount() - 1));
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        bizMsg.xxPageShowFromNum_H1.setValue(scrnMsg.xxPageShowFromNum_H1.getValueInt());
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;
        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL0770CommonLogic.initialize(this, scrnMsg);

        // Details position Initialize
        scrnMsg.xxListNum_LX.setValue(0);

        NFCL0770CommonLogic.commonBtnControl_NFCL0770Scrn00_AddOneLine(this);

        NFCL0770CommonLogic.setRowBg(scrnMsg);

        //NFCL0770CommonLogic.protectAddDetailLine(scrnMsg, this);

        NFCL0770CommonLogic.transMsgCheck(scrnMsg);
        // del start 2022/09/02 QC#60403
        //scrnMsg.putErrorScreen();
        // del end 2022/09/02 QC#60403

        NFCL0770CommonLogic.protectModeOne(scrnMsg, this);
        NFCL0770CommonLogic.setAppFracDigit(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).arCustRefNum);
        // add start 2022/09/02 QC#60403
        scrnMsg.putErrorScreen();
        // add end 2022/09/02 QC#60403
    }
}
