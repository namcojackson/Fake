/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7070;

import static business.servlet.NMAL7070.constant.NMAL7070Constant.NZZM0002I;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BIZ_ID;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.NMAM0185E;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7070.NMAL7070CMsg;
import business.servlet.NMAL7070.common.NMAL7070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7070Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {

            if (ZYPDateUtil.compare(scrnMsg.effThruDt.getValue(), scrnMsg.effFromDt.getValue()) < 0) {
                scrnMsg.effFromDt.setErrorInfo(1, NMAM0185E);
                scrnMsg.effThruDt.setErrorInfo(1, NMAM0185E);
            }
        }
        NMAL7070CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;

        NMAL7070CMsg bizMsg = new NMAL7070CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;
        NMAL7070CMsg bizMsg = (NMAL7070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        NMAL7070CommonLogic.searchControlScreen(this, scrnMsg);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }

    }
}
