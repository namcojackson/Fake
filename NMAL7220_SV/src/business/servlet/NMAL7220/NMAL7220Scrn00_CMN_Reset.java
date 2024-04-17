/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7220;

import static business.servlet.NMAL7220.constant.NMAL7220Constant.BIZ_ID;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7220.NMAL7220CMsg;
import business.servlet.NMAL7220.common.NMAL7220CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7220Scrn00_CMN_Reset
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7220Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7220BMsg scrnMsg = (NMAL7220BMsg) bMsg;

        NMAL7220CMsg bizMsg = new NMAL7220CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7220BMsg scrnMsg = (NMAL7220BMsg) bMsg;
        NMAL7220CMsg bizMsg = (NMAL7220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            return;
        }

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);

        boolean authorityFlg = NMAL7220CommonLogic.createAuthorityFlg(scrnMsg.xxYesNoCd.getValue());

        NMAL7220CommonLogic.initCmnBtnProp(this, authorityFlg);
        NMAL7220CommonLogic.scrnProtect(scrnMsg, false);
        NMAL7220CommonLogic.setBtnProp(this, scrnMsg, authorityFlg);

        scrnMsg.setFocusItem(scrnMsg.prcFmlaPk_H1);
    }
}
