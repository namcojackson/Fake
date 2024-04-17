/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0540;

import static business.servlet.NSAL0540.constant.NSAL0540Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0540.NSAL0540CMsg;
import business.servlet.NSAL0540.common.NSAL0540CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Solution Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class NSAL0540_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0540BMsg scrnMsg = (NSAL0540BMsg) bMsg;
        NSAL0540CMsg bizMsg = NSAL0540CommonLogic.createCMsgForSearch();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length > 0) {
                EZDBBigDecimalItem svcSlnSq = (EZDBBigDecimalItem) params[0];
                setValue(scrnMsg.svcSlnSq, svcSlnSq);
                // keep argument
                setValue(scrnMsg.svcSlnSq_BK, svcSlnSq);
            }
        } else {
            scrnMsg.svcSlnSq.clear();
            scrnMsg.svcSlnSq_BK.clear();
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0540BMsg scrnMsg = (NSAL0540BMsg) bMsg;
        NSAL0540CMsg bizMsg = (NSAL0540CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0540CommonLogic.screenControlProcess(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.svcSlnNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0540BMsg scrnMsg = (NSAL0540BMsg) bMsg;
        scrnMsg.svcSlnNm.setNameForMessage("Solution Name");
        scrnMsg.svcCmntTxt.setNameForMessage("Solution Note");
    }
}
