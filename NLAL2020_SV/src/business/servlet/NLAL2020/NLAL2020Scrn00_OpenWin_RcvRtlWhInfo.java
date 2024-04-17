/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL2020.constant.NLAL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2020Scrn00_OpenWin_RcvRtlWhInfo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/03/2016   CSAI            Y.Imazu         Create          QC#7981
 *</pre>
 */
public class NLAL2020Scrn00_OpenWin_RcvRtlWhInfo extends S21CommonHandler {

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

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;

        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, NLAL2020Constant.EVENT_NM_OPEN_WIN_RCV_RTL_WH_INFO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(index));

        int i = 0;
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, LOC_ROLE_TP.TECHNICIAN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.A.no(index).rtlWhCd_A1);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ZYPConstant.FLG_ON_Y);
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[11];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }
        setArgForSubScreen(params);
    }
}
