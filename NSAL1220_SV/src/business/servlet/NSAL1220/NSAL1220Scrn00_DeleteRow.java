/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1220;

import static business.servlet.NSAL1220.constant.NSAL1220Constant.NSAM0015E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract Branch Revenue Distribution
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL1220Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1220BMsg scrnMsg = (NSAL1220BMsg) bMsg;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            scrnMsg.setMessageInfo(NSAM0015E);
        } else {
            ZYPTableUtil.deleteRows(scrnMsg.A, selectedRows);
        }
    }
}
