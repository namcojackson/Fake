/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/24   Fujitsu         W.Honda         Create          N/A
 * 01/21/2016   CSAI            Y.Imazu         Update          QC#2048
 *</pre>
 */
public class NLBL3050Scrn00_OpenWin_LocInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;

        int i = 0;

        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(i++).xxComnScrColValTxt.clear();
        scrnMsg.P.no(i++).xxComnScrColValTxt.clear();
        scrnMsg.P.no(i++).xxComnScrColValTxt.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxComnScrColValTxt, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxComnScrColValTxt, ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(i++).xxComnScrColValTxt.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxComnScrColValTxt, scrnMsg.xxRtlWhSrchTxt_H);
        scrnMsg.P.no(i++).xxComnScrColValTxt.clear();
        scrnMsg.P.no(i++).xxComnScrColValTxt.clear();
        scrnMsg.P.no(i++).xxComnScrColValTxt.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxComnScrColValTxt, ZYPConstant.FLG_ON_Y);
        scrnMsg.P.no(i++).xxComnScrColValTxt.clear();
        scrnMsg.P.no(i++).xxComnScrColValTxt.clear();
        scrnMsg.P.no(i++).xxComnScrColValTxt.clear();
        scrnMsg.P.no(i++).xxComnScrColValTxt.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxComnScrColValTxt, ZYPConstant.FLG_ON_Y);
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[i];

        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {

            params[j] = scrnMsg.P.no(j).xxComnScrColValTxt;
        }

        setArgForSubScreen(params);
    }
}
