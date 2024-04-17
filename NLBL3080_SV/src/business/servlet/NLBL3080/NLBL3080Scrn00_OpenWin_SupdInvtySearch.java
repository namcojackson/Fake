/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3080;

import java.math.BigDecimal;

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
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 2016/03/22   CITS            T.Tokutomi      Update          QC#4596
 *</pre>
 */
public class NLBL3080Scrn00_OpenWin_SupdInvtySearch extends S21CommonHandler {

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

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        int i = 0;
        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(index));
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.B.no(index).mdseCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.B.no(index).stkStsCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.B.no(index).rtlWhNm_B1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.B.no(index).rtlSwhCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.B.no(index).supdLockFlg_B1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ZYPConstant.FLG_ON_Y);

        // Set Popup param
        Object[] param = new Object[6];

        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        param[2] = scrnMsg.P.no(2).xxPopPrm;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;

        setArgForSubScreen(param);

    }
}
