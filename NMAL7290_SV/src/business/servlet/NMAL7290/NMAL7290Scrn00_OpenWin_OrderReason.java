/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290;

import static business.servlet.NMAL7290.constant.NMAL7290Constant.SCR_EVENT_NM_RSN;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7290Scrn00_OpenWin_OrderReason
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL7290Scrn00_OpenWin_OrderReason extends S21CommonHandler {

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

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(SCR_EVENT_NM_RSN);

        scrnMsg.P.clear();

        scrnMsg.P.no(0).xxPopPrm_P.setValue("DS_ORD_TP");
        scrnMsg.P.no(1).xxPopPrm_P.setValue("DS_ORD_TP_CD");
        scrnMsg.P.no(2).xxPopPrm_P.setValue("DS_ORD_TP_NM");
        scrnMsg.P.no(3).xxPopPrm_P.setValue("DS_ORD_TP_SORT_NUM");
        scrnMsg.P.no(4).xxPopPrm_P.setValue("Order Reason Popup");
        scrnMsg.P.no(5).xxPopPrm_P.setValue("Order Reason Code");
        scrnMsg.P.no(6).xxPopPrm_P.setValue("Order Reason Name");
        scrnMsg.P.no(7).xxPopPrm_P.setValue("Order Reason Code");
        scrnMsg.P.no(8).xxPopPrm_P.setValue("Order Reason Name");
        scrnMsg.P.no(9).xxPopPrm_P.setValue(scrnMsg.dsOrdTpCd.getValue());

        Object[] params = new Object[11];
        params[0] = scrnMsg.P.no(0).xxPopPrm_P;
        params[1] = scrnMsg.P.no(1).xxPopPrm_P;
        params[2] = scrnMsg.P.no(2).xxPopPrm_P;
        params[3] = scrnMsg.P.no(3).xxPopPrm_P;
        params[4] = scrnMsg.P.no(4).xxPopPrm_P;
        params[5] = scrnMsg.P.no(5).xxPopPrm_P;
        params[6] = scrnMsg.P.no(6).xxPopPrm_P;
        params[7] = scrnMsg.P.no(7).xxPopPrm_P;
        params[8] = scrnMsg.P.no(8).xxPopPrm_P;
        params[9] = scrnMsg.P.no(9).xxPopPrm_P;
        params[10] = scrnMsg.P.no(10).xxPopPrm_P;

        setArgForSubScreen(params);
    }
}
