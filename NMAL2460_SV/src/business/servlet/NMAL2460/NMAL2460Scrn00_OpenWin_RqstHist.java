/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2460.common.NMAL2460CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NMAL2460Scrn00_OpenWin_RqstHist extends S21CommonHandler {

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
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        NMAL2460CommonLogic.clearParam(scrnMsg);

        // Mod Start 2016/07/13 QC#11069
        String[] params = new String[1];
//        String selectStr = NMAL2460CommonLogic.setRqstHstSelectStr(getGlobalCompanyCode());
//        List<Object[]> whereList = NMAL2460CommonLogic.setRqstHstWhereList();
//        List<Object[]> tblColumnList = NMAL2460CommonLogic.setRqstHstColumnList();
//        List<Object[]> sortCondList = NMAL2460CommonLogic.setRqstHstSortList();
//
//        params[0] = ""; // 0.Suffix
//        params[1] = SCRN_TITLE; // 1.Screen Title
//        params[2] = selectStr; // 2.Table Name
//        params[3] = whereList; // 3.Search Criteria
//        params[4] = tblColumnList; // 4.Column
//        params[5] = sortCondList; // 5.Sort Order
//        params[6] = ""; // 6.Output(S)
        params[0] = BIZ_ID;
        // Mod End 2016/07/13 QC#11069

        setArgForSubScreen(params);
    }
}
