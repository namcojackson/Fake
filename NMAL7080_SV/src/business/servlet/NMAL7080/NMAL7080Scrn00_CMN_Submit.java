/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7080;

import static business.servlet.NMAL7080.constant.NMAL7080Constant.BIZ_ID;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.NMAM0043E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7080.NMAL7080CMsg;
import business.servlet.NMAL7080.common.NMAL7080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7080Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {

            if (ZYPDateUtil.compare(scrnMsg.effThruDt.getValue(), scrnMsg.effFromDt.getValue()) < 0) {
                scrnMsg.effFromDt.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.effThruDt.getNameForMessage(), scrnMsg.effFromDt.getNameForMessage() });
                scrnMsg.effThruDt.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.effThruDt.getNameForMessage(), scrnMsg.effFromDt.getNameForMessage() });
            }
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A)) {
                if (ZYPDateUtil.compare(scrnMsg.A.no(i).effThruDt_A.getValue(), scrnMsg.A.no(i).effFromDt_A.getValue()) < 0) {
                    scrnMsg.A.no(i).effFromDt_A.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.A.no(i).effThruDt_A.getNameForMessage(), scrnMsg.A.no(i).effFromDt_A.getNameForMessage() });
                    scrnMsg.A.no(i).effThruDt_A.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.A.no(i).effThruDt_A.getNameForMessage(), scrnMsg.A.no(i).effFromDt_A.getNameForMessage() });
                }
            }
        }
        NMAL7080CommonLogic.headerAddCheckItem(scrnMsg);
        NMAL7080CommonLogic.detailAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;

        NMAL7080CMsg bizMsg = new NMAL7080CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;
        NMAL7080CMsg bizMsg = (NMAL7080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7080CommonLogic.detailAddCheckItem(scrnMsg);
        NMAL7080CommonLogic.headerAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.A.getValidCount() <= 0) {
            NMAL7080CommonLogic.zeroDetailScreen(this, scrnMsg);
        } else {
            NMAL7080CommonLogic.searchScreen(this, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.splyAgmtPlnShortNm);
        }
    }
}
