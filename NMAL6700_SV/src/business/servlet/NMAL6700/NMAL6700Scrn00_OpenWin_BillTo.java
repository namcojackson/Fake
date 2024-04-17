/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         N.Sugiura       Create          N/A
 * 2016/04/13   SRAA            Y.Chen          Update          QC#6011
 * 2017/09/08   Hitachi         J.Kim           Update          QC#20359
 *</pre>
 */
public class NMAL6700Scrn00_OpenWin_BillTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        scrnMsg.P.clear();

        scrnMsg.dsAcctNm_P.clear();

        scrnMsg.P.no(0).xxPopPrm.setValue(scrnMsg.dsAcctNum_H1.getValue());
        // START 2017/09/08 J.Kim [QC#20359,DEL]
        // int index = getButtonSelectNumber();
        // if (0 <= index) {
        //    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(15).xxPopPrm, scrnMsg.F.no(index).dsDefBillToCd_F1.getValue());
        // }
        // END 2017/09/08 J.Kim [QC#20359,DEL]
        // START 2017/09/08 J.Kim [QC#20359,MOD]
        // scrnMsg.P.no(12).xxPopPrm.setValue("02"); // Bill To Only
        scrnMsg.P.no(8).xxPopPrm.setValue(NMAL6700Constant.DISP_RELN_ACCT_CD_BILL);  // Display Related Accts:Bill To's Only
        scrnMsg.P.no(12).xxPopPrm.setValue(NMAL6700Constant.DISP_HRCH_ACCT_CD_BILL); // Display Hierarchy Account:Bill To Only
        // END 2017/09/08 J.Kim [QC#20359,MOD]
        scrnMsg.P.no(24).xxPopPrm.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(25).xxPopPrm.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(33).xxPopPrm.setValue(ZYPConstant.FLG_ON_Y); // Category: Active

        Object[] params = NMAL6700CommonLogic.toArray_popup(scrnMsg.P, 35);
        params[1] = scrnMsg.dsAcctNm_P;

        setArgForSubScreen(params);
    }
}
