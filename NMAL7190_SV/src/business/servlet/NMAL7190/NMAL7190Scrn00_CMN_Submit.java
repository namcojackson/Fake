/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.BIZ_ID;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NMAM0042E;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NMAM0043E;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NMAM0192E;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NZZM0002I;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7190.NMAL7190CMsg;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7190Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7190Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7190CommonLogic.addCheckItemForDetail(scrnMsg);
        scrnMsg.putErrorScreen();
        boolean isErr = false;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcGrpNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpTpCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.effFromDt)) {
            scrnMsg.prcGrpNm.setErrorInfo(1, NMAM0192E);
            scrnMsg.prcGrpTpCd.setErrorInfo(1, NMAM0192E);
            scrnMsg.effFromDt.setErrorInfo(1, NMAM0192E);
            scrnMsg.addCheckItem(scrnMsg.prcGrpNm);
            scrnMsg.addCheckItem(scrnMsg.prcGrpTpCd);
            scrnMsg.addCheckItem(scrnMsg.effFromDt);
            isErr = true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt)
                && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)
                && ZYPDateUtil.compare(scrnMsg.effFromDt.getValue(), scrnMsg.effThruDt.getValue()) > 0) {
            scrnMsg.effFromDt.setErrorInfo(1, NMAM0043E, new String[]{scrnMsg.effFromDt.getNameForMessage(), scrnMsg.effThruDt.getNameForMessage()});
            scrnMsg.effThruDt.setErrorInfo(1, NMAM0042E, new String[]{scrnMsg.effThruDt.getNameForMessage(), scrnMsg.effFromDt.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.effFromDt);
            scrnMsg.addCheckItem(scrnMsg.effThruDt);
            isErr = true;
        }

        if (isErr) {
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CMsg bizMsg = new NMAL7190CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        NMAL7190CMsg bizMsg = (NMAL7190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            NMAL7190CommonLogic.addCheckItemForHeader(scrnMsg);
            NMAL7190CommonLogic.addCheckItemForDetail(scrnMsg);
            scrnMsg.putErrorScreen();
        }

        NMAL7190CommonLogic.controlScreen(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.prcGrpNm);

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}
