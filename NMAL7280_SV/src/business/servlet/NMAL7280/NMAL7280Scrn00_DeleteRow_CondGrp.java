/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7280;

import static business.servlet.NMAL7280.constant.NMAL7280Constant.BIZ_ID;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.NMAM8054E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7280.NMAL7280CMsg;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7280Scrn00_DeleteRow_CondGrp
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7280Scrn00_DeleteRow_CondGrp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;
        boolean checkFlag = false;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_A)) {
                checkFlag = true;
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
        }

        if (!checkFlag) {
            scrnMsg.setMessageInfo(NMAM8054E);
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;
        NMAL7280CMsg bizMsg = new NMAL7280CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;
        NMAL7280CMsg bizMsg  = (NMAL7280CMsg) cMsg;
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
