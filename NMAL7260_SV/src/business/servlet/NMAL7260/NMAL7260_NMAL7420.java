/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.BIZ_ID;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7260.NMAL7260CMsg;
import business.servlet.NMAL7260.common.NMAL7260CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/15   Fujitsu         K.Ishizuka      Create          QC#18237(L3#161)
 * 2018/05/25   Fujitsu         T.Noguchi       Update          QC#26031
 *</pre>
 */
public class NMAL7260_NMAL7420 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        NMAL7260CMsg bizMsg = new NMAL7260CMsg();
        NMAL7260CommonLogic.setParamFromPopup(bizMsg, scrnMsg);

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        NMAL7260CMsg bizMsg = (NMAL7260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL7260CommonLogic.initCmnBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.scrnProtect(scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.setBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
        // 2018/05/25 QC#26031 Del Start
//        NMAL7260CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
        // 2018/05/25 QC#26031 Del End
        scrnMsg.addCheckItem(scrnMsg.prcRulePrcdGrpNm_H1);
        scrnMsg.putErrorScreen();
    }
}
