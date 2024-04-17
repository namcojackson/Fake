/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610;

import static business.servlet.NWAL1610.constant.NWAL1610Constant.OPENWIN_PRC_LIST;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1610Scrn00_OpenWin_PriceList
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         Yokoi           Create          N/A
 *</pre>
 */
public class NWAL1610Scrn00_OpenWin_PriceList extends S21CommonHandler {

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
        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.xxScrEventNm.setValue(OPENWIN_PRC_LIST);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm_P, scrnMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm_P, scrnMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm_P, scrnMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm_P, scrnMsg.prcContrNum);

        Object[] param = new Object[13];
        if (ZYPCommonFunc.hasValue(scrnMsg.prcBaseDt)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcBaseDt_AW, scrnMsg.prcBaseDt);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcBaseDt_AW, ZYPDateUtil.getSalesDate());
        }
        param[0] = scrnMsg.prcBaseDt_AW;
        param[1] = scrnMsg.P.no(1).xxPopPrm_P;
        param[2] = scrnMsg.P.no(2).xxPopPrm_P;
        param[3] = scrnMsg.P.no(3).xxPopPrm_P;
        param[4] = scrnMsg.P.no(4).xxPopPrm_P;
        param[5] = scrnMsg.P.no(5).xxPopPrm_P;
        param[6] = scrnMsg.P.no(6).xxPopPrm_P;
        param[7] = scrnMsg.P.no(7).xxPopPrm_P;
        param[8] = scrnMsg.P.no(8).xxPopPrm_P;
        param[9] = scrnMsg.P.no(9).xxPopPrm_P;
        param[10] = scrnMsg.P.no(10).xxPopPrm_P;
        param[11] = scrnMsg.P.no(11).xxPopPrm_P;
        param[12] = scrnMsg.P.no(12).xxPopPrm_P;

        setArgForSubScreen(param);
    }
}
