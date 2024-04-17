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
 * 06/24/2009   Fujitsu         FXS)Z.Wang      Create          N/A
 * 11/17/2009   Fujitsu         FAP)K.Sakano    Update          DefID:1740
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 * 10/13/2010   Fujitsu         T.Tanaka        Update          Merge R2->R3
 *</pre>
 */
package business.servlet.NFCL5050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5050.NFCL5050CMsg;
import business.servlet.NFCL5050.common.NFCL5050CommonLogic;
import business.servlet.NFCL5050.constant.NFCL5050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * NFCL5050Scrn00_CMN_Clear.
 */
public class NFCL5050Scrn00_CMN_Clear extends S21CommonHandler implements NFCL5050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5050CMsg bizMsg = null;

        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

        bizMsg = NFCL5050CommonLogic.setRequestData_NFCL5050Scrn00_CMN_Clear(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;

        if (bizMsg != null) {
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }

        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL5050CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NFCL5050CommonLogic.initialize(this, scrnMsg);

        NFCL5050CommonLogic.setScrnItemValue_NFCL5050Scrn00_CMN_Clear(scrnMsg);

        NFCL5050CommonLogic.busBtnControl_NFCL5050Scrn00_CMN_Clear(this, scrnMsg);

        NFCL5050CommonLogic.scrnItemControl_NFCL5050Scrn00_CMN_Clear(scrnMsg);

        NFCL5050CommonLogic.setRowBg(scrnMsg);

        NFCL5050CommonLogic.scrnItemControl_NFCL5050_INIT(scrnMsg);

        S21TableColorController tblColor = new S21TableColorController("NFCL5050Scrn00", scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
//        tblColor.clearRowsBG("A1", scrnMsg.A);
//        tblColor.clearRowsBG("A2", scrnMsg.A);

    }
}
