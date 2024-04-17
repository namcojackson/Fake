/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0790;

import static business.servlet.NSAL0790.constant.NSAL0790Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0790.NSAL0790CMsg;
import business.servlet.NSAL0790.common.NSAL0790CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Fleet Rollup Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Hitachi         A.Kohinata      Create          N/A
 * 2017/03/10   Hitachi         K.Kitachi       Update          QC#17752
 *</pre>
 */
public class NSAL0790_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0790BMsg scrnMsg = (NSAL0790BMsg) bMsg;
        NSAL0790CMsg bizMsg = new NSAL0790CMsg();

        // START 2017/03/10 K.Kitachi [QC#17752, MOD]
        ZYPTableUtil.clear(scrnMsg.P);
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length > 0) {
                EZDMsg.copy(((EZDBMsgArray) params[0]), null, scrnMsg.P, null);
            }
        }
        // END 2017/03/10 K.Kitachi [QC#17752, MOD]

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0790BMsg scrnMsg = (NSAL0790BMsg) bMsg;
        NSAL0790CMsg bizMsg = (NSAL0790CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0790CommonLogic.initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
    }
}
