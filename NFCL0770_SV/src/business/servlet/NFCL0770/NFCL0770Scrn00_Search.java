/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFCL0770.NFCL0770CMsg;
import business.servlet.NFCL0770.common.NFCL0770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/09/02   Hitachi         A.Kohinata      Create          QC#60403
 *</pre>
 */
public class NFCL0770Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;
        scrnMsg.xxListNum_LY.setValue(0);

        if (!ZYPCommonFunc.hasValue(scrnMsg.arCustRefNum_H1)) {
            scrnMsg.arCustRefNum_H1.setErrorInfo(1, "NFCM0911E");
            scrnMsg.setMessageInfo("ZZM9037E");
        }
        scrnMsg.addCheckItem(scrnMsg.arCustRefNum_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770CMsg bizMsg = null;
        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        bizMsg = NFCL0770CommonLogic.setRequestData_NFCL0770Scrn00_Search();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL0770CommonLogic.initialize(this, scrnMsg);
        NFCL0770CommonLogic.commonBtnControl_NFCL0770Scrn00_SearchTrxLine(this);
        NFCL0770CommonLogic.setRowBg(scrnMsg);
        NFCL0770CommonLogic.protectModeOne(scrnMsg, this);
        NFCL0770CommonLogic.transMsgCheck(scrnMsg);
        if (scrnMsg.arCustRefNum_H1.isError()) {
            scrnMsg.setFocusItem(scrnMsg.arCustRefNum_H1);
            scrnMsg.putErrorScreen();
        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxCellIdx_H1)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx_H1.getValueInt()).arCustRefNum);
        } else {
            scrnMsg.setFocusItem(scrnMsg.arCustRefNum_H1);
            scrnMsg.putErrorScreen();
        }
    }
}
