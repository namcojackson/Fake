/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2330;

import static business.servlet.NWAL2330.constant.NWAL2330Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2330Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NWAL2330Scrn00_CMN_Clear extends S21CommonHandler {

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

        NWAL2330BMsg scrnMsg = (NWAL2330BMsg) bMsg;

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        scrnMsg.cpoOrdNum_H1.clear();
        scrnMsg.cpoOrdNum_BK.clear();
        scrnMsg.dsAcctNm_H1.clear();
        scrnMsg.sellToCustCd_H1.clear();
        scrnMsg.cpoSrcTpCd_H1.clear();
        scrnMsg.soNum_H1.clear();
        scrnMsg.dsAcctNm_H2.clear();
        scrnMsg.billToCustAcctCd_H1.clear();
        scrnMsg.dsOrdCatgCd_H1.clear();
        scrnMsg.invNum_H1.clear();
        scrnMsg.dsAcctNm_H3.clear();
        scrnMsg.shipToCustAcctCd_H1.clear();
        scrnMsg.dsOrdTpCd_H1.clear();
        scrnMsg.ordDt_H1.clear();
        scrnMsg.ordDt_H2.clear();
        scrnMsg.invDt_H1.clear();
        scrnMsg.invDt_H2.clear();

        ZYPTableUtil.clear(scrnMsg.A);

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_H1);
    }
}
