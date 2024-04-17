/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/6/23   CUSA            Fujitsu         Update          N/A
 * 2009/10/29  CUSA            K.Hasegawa      Update
 *</pre>
 */
package business.servlet.NWAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL0140.common.NWAL0140CommonLogic;
import business.servlet.NWAL0140.constant.NWAL0140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWAL0140Scrn00_Ship_To_Edit extends S21CommonHandler implements NWAL0140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

        NWAL0140CommonLogic.unprotectedInput(scrnMsg);
        NWAL0140CommonLogic.editCommonButton(this);

        scrnMsg.setFocusItem(scrnMsg.locNm);

    }
}