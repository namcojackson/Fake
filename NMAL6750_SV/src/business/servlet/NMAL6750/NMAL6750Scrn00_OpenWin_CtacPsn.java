/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/10   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
public class NMAL6750Scrn00_OpenWin_CtacPsn extends S21CommonHandler {

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

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;
        scrnMsg.P.clear();
        List<EZDBItem> params = new ArrayList<EZDBItem>();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm_P, scrnMsg.A.no(0).dsAcctNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm_P, scrnMsg.ctacPsnFirstNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm_P, scrnMsg.ctacPsnLastNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm_P, ZYPConstant.FLG_ON_Y);  // Active
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(21).xxPopPrm_P, ZYPConstant.FLG_ON_Y);  // Disable Account Number

        params.add(scrnMsg.ctacPsnNum_H1);
        params.add(scrnMsg.P.no(1).xxPopPrm_P);
        params.add(scrnMsg.P.no(2).xxPopPrm_P);
        params.add(scrnMsg.P.no(3).xxPopPrm_P);
        params.add(scrnMsg.P.no(4).xxPopPrm_P);
        params.add(scrnMsg.P.no(5).xxPopPrm_P);
        params.add(scrnMsg.P.no(6).xxPopPrm_P);
        params.add(scrnMsg.P.no(7).xxPopPrm_P);
        params.add(scrnMsg.P.no(8).xxPopPrm_P);
        params.add(scrnMsg.P.no(9).xxPopPrm_P);
        params.add(scrnMsg.P.no(10).xxPopPrm_P);
        params.add(scrnMsg.P.no(11).xxPopPrm_P);
        params.add(scrnMsg.P.no(12).xxPopPrm_P);
        params.add(scrnMsg.P.no(13).xxPopPrm_P);
        params.add(scrnMsg.P.no(14).xxPopPrm_P);
        params.add(scrnMsg.ctacPsnPk_H1);
        params.add(scrnMsg.ctacPsnPk_H1);
        params.add(scrnMsg.ctacPsnPk_H1);
        params.add(scrnMsg.ctacPsnPk_H1);
        params.add(scrnMsg.ctacPsnPk_H1);
        params.add(scrnMsg.P.no(20).xxPopPrm_P);
        params.add(scrnMsg.P.no(21).xxPopPrm_P);

        setArgForSubScreen(params.toArray(new EZDBItem[0]));
    }
}
