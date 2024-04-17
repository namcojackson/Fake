/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2360;

import static business.servlet.NWAL2360.constant.NWAL2360Constant.BIZ_APP_ID;
import static business.servlet.NWAL2360.constant.NWAL2360Constant.FUNCTION_CD_SEARCH;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2360.NWAL2360CMsg;
import business.servlet.NWAL2360.common.NWAL2360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * SOM Approval Detail INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NWAL2360_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2360BMsg scrnMsg = (NWAL2360BMsg) bMsg;

        NWAL2360CMsg bizMsg = new NWAL2360CMsg();

        Object[] params = (Object[]) getArgForSubScreen();
        if (params instanceof Object[]) {
            EZDBStringItem param0 = (EZDBStringItem) params[0];

            ZYPEZDItemValueSetter.setValue(scrnMsg.dsImptOrdPk, new BigDecimal(param0.getValue()));

            ZYPTableUtil.clear(scrnMsg.A);
        }

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2360BMsg scrnMsg = (NWAL2360BMsg) bMsg;
        NWAL2360CMsg bizMsg = (NWAL2360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2360CommonLogic.cntrlScrnItemDispInit(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NWAL2360CommonLogic.setNameForMessage((NWAL2360BMsg) arg0);
    }

}
