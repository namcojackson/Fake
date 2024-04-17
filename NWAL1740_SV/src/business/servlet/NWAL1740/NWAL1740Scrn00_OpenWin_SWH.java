/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1740;

import static business.servlet.NWAL1740.constant.NWAL1740Constant.PARAM_SIZE_7;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.POP_TITLE_WH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1740.common.NWAL1740CommonLogic;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1740Scrn00_OpenWin_SWH
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1740Scrn00_OpenWin_SWH extends S21CommonHandler {

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

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);
        int index = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(index);

        Object[] params = new Object[PARAM_SIZE_7];
        params[0] = "";
        params[1] = POP_TITLE_WH;
        params[2] = NWAL1740CommonLogic.getSql(getGlobalCompanyCode());
        params[3] = NWAL1740CommonLogic.getSearchConditionSetting(scrnMsg, index);
        params[4] = NWAL1740CommonLogic.getDisplayColumnSetting();
        params[5] = NWAL1740CommonLogic.getSortSetting();
        params[6] = scrnMsg.P;
        setArgForSubScreen(params);
    }
}
