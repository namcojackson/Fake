/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.MSG_PARAM_LINE;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.NWAM0667E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/24   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NWAL1840Scrn00_OpenWin_SchdLineAssist extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);

        if (selectedRows.size() == 0) {
            scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_LINE });
            return;
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        Object[] params = NWAL1840CommonLogic.getParamNWAL1860(scrnMsg);
        setArgForSubScreen(params);
    }
}
