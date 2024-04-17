/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/01/2009   Fujitsu         FXS)BY.Bao          Create          N/A
 *</pre>
 */
package business.servlet.NLCL0180;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0180.NLCL0180CMsg;
import business.servlet.NLCL0180.common.NLCL0180CommonLogic;
import business.servlet.NLCL0180.constant.NLCL0180Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class NLCL0180Scrn00_CMN_Submit extends S21CommonHandler implements NLCL0180Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;

        NLCL0180CommonLogic.checkInputSubmit(scrnMsg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0180CMsg bizMsg = null;

        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;

        bizMsg = NLCL0180CommonLogic.setRequestData_NLCL0180Scrn00_CMN_Submit();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0180CMsg bizMsg = (NLCL0180CMsg) cMsg;
        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController("NLCL0180Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        NLCL0180CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        } else {
        }

        NLCL0180CommonLogic.initialize(this, scrnMsg);

        NLCL0180CommonLogic.commonBtnControl_NLCL0180Scrn00_CMN_Submit(this);

        NLCL0180CommonLogic.busBtnControl_NLCL0180Scrn00_CMN_Submit(this);

        NLCL0180CommonLogic.scrnItemControl_NLCL0180Scrn00_CMN_Submit(scrnMsg);

        scrnMsg.A.setInputProtected(true);
    }
}
