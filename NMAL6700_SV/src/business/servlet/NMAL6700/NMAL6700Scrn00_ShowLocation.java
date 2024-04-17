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
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/20   Fujitsu         C.Yokoi         Update          QC#12060
 *</pre>
 */
public class NMAL6700Scrn00_ShowLocation extends S21CommonHandler {

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

        int selectIdx = getButtonSelectNumber();
        NMAL6700_ABMsg abMsg = scrnMsg.A.no(selectIdx);

        scrnMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_OFF_N);
        ZYPTableUtil.clear(scrnMsg.P);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, "01");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.dsAcctNum_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, abMsg.locNum_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, NMAL6700Constant.SCREEN_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, scrnMsg.xxValUpdFlg);

        Object[] params = NMAL6700CommonLogic.toArray_popup(scrnMsg.P, 5);

        setArgForSubScreen(params);
    }
}
