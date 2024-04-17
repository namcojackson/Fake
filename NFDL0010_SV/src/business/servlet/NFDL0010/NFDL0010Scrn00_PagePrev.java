/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0010.NFDL0010CMsg;
import business.servlet.NFDL0010.common.NFDL0010CommonLogic;
import business.servlet.NFDL0010.constant.NFDL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFDL0010Scrn00_PagePrev extends S21CommonHandler implements NFDL0010Constant{

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

        if (scrnMsg.A.getValidCount() == 0) {

            return null;

        } else {

            NFDL0010CMsg bizMsg = null;

            NFDL0010CommonLogic.controlTablePrev_NFDL0010Scrn00_A(scrnMsg);

            bizMsg = NFDL0010CommonLogic.setRequestData_NFDL0010Scrn00_PagePrev();

            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            NFDL0010CommonLogic.controlTableBegin_NFDL0010Scrn00_A(scrnMsg);

            return bizMsg;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0010CMsg bizMsg = (NFDL0010CMsg) cMsg;
        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

        if (bizMsg == null) {
            return;
        }

        S21TableColorController tblColor = new S21TableColorController("NFDL0010Scrn00", scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);

        //NFDL0010CommonLogic.scrnItemControl_NFDL0010(scrnMsg);

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //NFDL0010CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NFDL0010CommonLogic.initialize(this, scrnMsg);

        NFDL0010CommonLogic.commonBtnControl_NFDL0010(this);

        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);

       //NFDL0010CommonLogic.setScrCondition(scrnMsg);
    }

}
