/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2700;

import static business.servlet.NMAL2700.constant.NMAL2700Constant.SCRN_TITLE;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2700.common.NMAL2700CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/07   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NMAL2700Scrn00_OpenWin_SFDCProfile extends S21CommonHandler {

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
        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        Object[] params = new Object[7];
        String selectStr = NMAL2700CommonLogic.setRqstHstSelectStr(getGlobalCompanyCode());
        List<Object[]> whereList = NMAL2700CommonLogic.setRqstHstWhereList(scrnMsg.A.no(selectIdx).crmSlsPrflNm_A.getValue());
        List<Object[]> tblColumnList = NMAL2700CommonLogic.setRqstHstColumnList();
        List<Object[]> sortCondList = NMAL2700CommonLogic.setRqstHstSortList();

        params[0] = ""; // 0.Suffix
        params[1] = SCRN_TITLE; // 1.Screen Title
        params[2] = selectStr; // 2.Table Name
        params[3] = whereList; // 3.Search Criteria
        params[4] = tblColumnList; // 4.Column
        params[5] = sortCondList; // 5.Sort Order
        params[6] = scrnMsg.P; // 6.Output(S)

        setArgForSubScreen(params);
    }
}
