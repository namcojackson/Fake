/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1600;

import static business.servlet.NWAL1600.constant.NWAL1600Constant.SCRN_TITLE;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1600.common.NWAL1600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL1600Scrn00_OpenWin_SlsRep extends S21CommonHandler {

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

        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        int index = getButtonSelectNumber();
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(index);
        ZYPTableUtil.clear(scrnMsg.P);

        Object[] params = new Object[7];

        String selectStr = NWAL1600CommonLogic.setSlsrepSelectStr(getGlobalCompanyCode(), index);
        List<Object[]> whereList = NWAL1600CommonLogic.setSlsrepWhereList(scrnMsg, null, scrnMsg.A.no(index).tocNm_A.getValue());
        List<Object[]> tblColumnList = NWAL1600CommonLogic.setSlsrepTblColumnList(scrnMsg);
        List<Object[]> sortCondList = NWAL1600CommonLogic.setSlsrepSortList(scrnMsg);

        params[0] = "S"; // 0.Suffix
        params[1] = SCRN_TITLE; // 1.Screen Title
        params[2] = selectStr; // 2.Table Name
        params[3] = whereList; // 3.Search Criteria
        params[4] = tblColumnList; // 4.Column
        params[5] = sortCondList; // 5.Sort Order
        params[6] = scrnMsg.S; // 6.Output(S)

        setArgForSubScreen(params);
    }
}
