/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0160;

import static business.servlet.NFDL0160.constant.NFDL0160Constant.BIZ_ID;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_SUB;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0160.NFDL0160CMsg;
import business.servlet.NFDL0160.common.NFDL0160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2018/08/03   Fujitsu         T.Ogura         Update          QC#25899
 *</pre>
 */
public class NFDL0160Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        // for inActiveForm error clear
        NFDL0160CommonLogic.inActiveFormErrorClear(scrnMsg);

        // Check Error
        // START 2018/08/03 T.Ogura [QC#25899,ADD]
        scrnMsg.addCheckItem(scrnMsg.cltPtfoCd);
        // END   2018/08/03 T.Ogura [QC#25899,ADD]
        scrnMsg.addCheckItem(scrnMsg.cltPtfoNm);
        scrnMsg.addCheckItem(scrnMsg.cltPsnNm);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        NFDL0160CMsg bizMsg = new NFDL0160CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;
        NFDL0160CMsg bizMsg = (NFDL0160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // setForcus
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).cltPtfoNm_A);
        } else {
            // START 2018/08/03 T.Ogura [QC#25899,MOD]
//            scrnMsg.setFocusItem(scrnMsg.cltPtfoNm);
            scrnMsg.setFocusItem(scrnMsg.cltPtfoCd);
            // END   2018/08/03 T.Ogura [QC#25899,MOD]
        }

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.A.setInputProtected(false);
        NFDL0160CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NFDL0160CommonLogic.setCmnBtnProp(this, BTN_CMN_SUB, 1);
        NFDL0160CommonLogic.controlScreenFields(this, scrnMsg, ctx.getEventName());
    }
}
