/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7150;

import static business.servlet.NMAL7150.constant.NMAL7150Constant.MESSAGE_KIND_ERR;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.BIZ_ID;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.NMAM0043E;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.NZZM0002I;
import static business.servlet.NMAL7150.constant.NMAL7150Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7150.NMAL7150CMsg;
import business.servlet.NMAL7150.common.NMAL7150CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * CSMP Contract Synchronization  Errors Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7150Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7150BMsg scrnMsg = (NMAL7150BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_E) //
                && !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_R) //
                && !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_D)) {
            scrnMsg.xxChkBox_D.setErrorInfo(1, ZZM9000E, new String[] {"The Process Flag" });
            scrnMsg.xxChkBox_E.setErrorInfo(1, ZZM9000E, new String[] {"The Process Flag" });
            scrnMsg.xxChkBox_R.setErrorInfo(1, ZZM9000E, new String[] {"The Process Flag" });
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_TD) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt_TD)) {
            if (ZYPDateUtil.compare(scrnMsg.effThruDt_TD.getValue(), scrnMsg.effFromDt_TD.getValue()) < 0) {
                scrnMsg.effFromDt_TD.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.effFromDt_TD.getNameForMessage(), scrnMsg.effThruDt_TD.getNameForMessage() });
                scrnMsg.effThruDt_TD.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.effFromDt_TD.getNameForMessage(), scrnMsg.effThruDt_TD.getNameForMessage() });
            }
        }
        NMAL7150CommonLogic.headerAddCheckItem(scrnMsg);
        NMAL7150CommonLogic.detailAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7150BMsg scrnMsg = (NMAL7150BMsg) bMsg;

        NMAL7150CMsg bizMsg = new NMAL7150CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7150BMsg scrnMsg = (NMAL7150BMsg) bMsg;
        NMAL7150CMsg bizMsg = (NMAL7150CMsg) cMsg;
        NMAL7150CommonLogic.detailAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if (MESSAGE_KIND_ERR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.setFocusItem(scrnMsg.effFromDt_TD);
        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }

    }
}
