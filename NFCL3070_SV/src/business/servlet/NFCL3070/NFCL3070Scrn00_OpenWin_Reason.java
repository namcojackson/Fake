/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3070;

import java.util.List;

import static business.servlet.NFCL3070.constant.NFCL3070Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NFCL3070.common.NFCL3070CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL3070Scrn00_OpenWin_Reason
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 *</pre>
 */
public class NFCL3070Scrn00_OpenWin_Reason extends S21CommonHandler {

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
        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;

        Object[] params = new Object[7];

        List<Object[]> whereList = NFCL3070CommonLogic.setReasonWhereList(scrnMsg);
        List<Object[]> tblColumnList = NFCL3070CommonLogic.setReasonTblColumnList(scrnMsg);
        List<Object[]> sortCondList = NFCL3070CommonLogic.setReasonSortList(scrnMsg);

        params[0] = "";                     //0.Suffix
        params[1] = POPUP_RSN_SEARCH_LB;    //1.Screen Title
        params[2] = AR_CR_REBIL_RSN_TBL;    //2.Table Name
        params[3] = whereList;              //3.Search Criteria(H)
        params[4] = tblColumnList;          //4.Column (C)
        params[5] = sortCondList;           //5.Sort Order(S)
        params[6] = scrnMsg.P;              //6.Output(R)

        setArgForSubScreen(params);
    }
}
