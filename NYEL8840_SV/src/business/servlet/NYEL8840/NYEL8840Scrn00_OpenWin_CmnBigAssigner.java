/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8840;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8840Scrn00_OpenWin_CmnBigAssignee
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/07   Fujitsu         M.Ugaki        Create          N/A
 *</pre>
 */
public class NYEL8840Scrn00_OpenWin_CmnBigAssigner extends S21CommonHandler {

    /** parameter count of Common BIG Popup */
    private static final int PRM_CNT = 5;

    /** line separator for SQL */
    private static final String NEWLINE = System.getProperty("line.separator");

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        // 0 : Lv1 : Workflow Biz App  (String) Input
        // 1 : Lv1 : Assign From User ID (String) Input
        // 2 : Lv1 : Assign From User ID  (String) Output
        // 3 : Lv1 : Assign From User Last Name (String) Output
        // 4 : Lv1 : Assign From User First Name (String) Output
        int i = 0;
        Object[] params = new Object[PRM_CNT];

        //input parameters
        params[i++] = scrnMsg.wfBizAppId.getValue();
        params[i++] = scrnMsg.xxWfAsgCd_F.getValue();

        //output parameters
        params[i++] = scrnMsg.usrNm_FR;
        params[i++] = scrnMsg.lastNm_FR;
        params[i] = scrnMsg.firstNm_FR;

        setArgForSubScreen(params);
    }

}
