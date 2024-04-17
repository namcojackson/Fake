/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610;

import static business.servlet.NWAL1610.constant.NWAL1610Constant.OPENWIN_WH;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.WH_SCRN_TITLE;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1610.common.NWAL1610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1610Scrn00_OpenWin_Warehouse
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         Yokoi           Create          N/A
 *</pre>
 */
public class NWAL1610Scrn00_OpenWin_Warehouse extends S21CommonHandler {

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
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;
        int index = getButtonSelectNumber();
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.S);
        scrnMsg.xxScrEventNm.setValue(OPENWIN_WH);

        Object[] params = new Object[7];

        String selectStr = NWAL1610CommonLogic.getSlctTblNmForWh(scrnMsg);
        List<Object[]> whereList = NWAL1610CommonLogic.setWhWhereList(scrnMsg, index);
        List<Object[]> tblColumnList = NWAL1610CommonLogic.setWhColumnList(scrnMsg);
        List<Object[]> sortCondList = NWAL1610CommonLogic.setWhSortList(scrnMsg);

        params[0] = "S"; // 0.Suffix
        params[1] = WH_SCRN_TITLE; // 1.Screen Title
        params[2] = selectStr; // 2.Table Name
        params[3] = whereList; // 3.Search Criteria
        params[4] = tblColumnList; // 4.Column
        params[5] = sortCondList; // 5.Sort Order
        params[6] = scrnMsg.S; // 6.Output(S)

        setArgForSubScreen(params);
    }
}
