/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7240;

import static business.servlet.NMAL7240.constant.NMAL7240Constant.BIZ_ID;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7240.NMAL7240CMsg;
import business.servlet.NMAL7240.common.NMAL7240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7240Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7240Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;

        NMAL7240CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7240CommonLogic.addCheckItemForDetail(scrnMsg);

        scrnMsg.putErrorScreen();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A1)
                    && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A1)
                    && ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A1.getValue(), scrnMsg.A.no(i).effThruDt_A1.getValue()) > 0) {
                scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.A.no(i).effFromDt_A1.getNameForMessage(), scrnMsg.A.no(i).effThruDt_A1.getNameForMessage()});
                scrnMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM0042E, new String[] {scrnMsg.A.no(i).effThruDt_A1.getNameForMessage(), scrnMsg.A.no(i).effFromDt_A1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;

        NMAL7240CMsg bizMsg = new NMAL7240CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;
        NMAL7240CMsg bizMsg = (NMAL7240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            NMAL7240CommonLogic.addCheckItemForHeader(scrnMsg);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).lineBizTpDescTxt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).frtZoneNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).fromSclQty_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).qtyUnitTpCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).shpgFrtRate_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).frtRateCcyCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).frtRatePerNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).perUnitTpCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            }
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        NMAL7240CommonLogic.controlScreen(this, scrnMsg);
        NMAL7240CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        if (0 < scrnMsg.A.getValidCount()) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).lineBizTpDescTxt_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.lineBizTpCd);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}
