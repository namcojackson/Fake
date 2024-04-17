/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7210;

import static business.servlet.NMAL7210.constant.NMAL7210Constant.BIZ_ID;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.NMAM0288E;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.NMAM0185E;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7210.common.NMAL7210CommonLogic;

import business.blap.NMAL7210.NMAL7210CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7210Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7210Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7210BMsg scrnMsg = (NMAL7210BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.prcFmlaNm_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcFmlaDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcFmlaTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.actvFlg_H1);

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcFmlaNm_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.prcFmlaDescTxt_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.prcFmlaTpCd_H1)) {
            scrnMsg.setMessageInfo(NMAM0288E);
            throw new  EZDFieldErrorException();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H1)) {
                if (scrnMsg.effFromDt_H1.getValue().compareTo(scrnMsg.effThruDt_H1.getValue()) > 0) {
                    scrnMsg.effFromDt_H1.setErrorInfo(1, NMAM0185E, new String[] {scrnMsg.effFromDt_H1.getNameForMessage()});
                    scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
                }
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7210BMsg scrnMsg = (NMAL7210BMsg) bMsg;

        NMAL7210CMsg bizMsg = new NMAL7210CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7210BMsg scrnMsg = (NMAL7210BMsg) bMsg;
        NMAL7210CMsg bizMsg  = (NMAL7210CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL7210CommonLogic.setBtnProp(this, scrnMsg);
        NMAL7210CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NMAL7210CommonLogic.scrnProtect(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.prcFmlaNm_H1);
    }
}
