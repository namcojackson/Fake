/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 12/20/2018   CITS            T.Tokutomi      Update          QC#29214
 *</pre>
 */
public class NPAL1130Scrn00_OpenWin_SupdInvtySearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        int rowNo = getButtonSelectNumber();

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(rowNo).supdFlg_A0.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(rowNo).mdseCd_A0);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.S.no(0).stkStsCd_S0.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, scrnMsg.rtlWhNm);
            // QC#29214 Update.
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, scrnMsg.rtlSwhNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, ZYPConstant.FLG_ON_Y);

            Object[] params = new Object[6];
            params[0] = scrnMsg.P.no(0).xxPopPrm;
            params[1] = scrnMsg.P.no(1).xxPopPrm;
            params[2] = scrnMsg.P.no(2).xxPopPrm;
            params[3] = scrnMsg.P.no(3).xxPopPrm;
            params[4] = scrnMsg.P.no(4).xxPopPrm;
            params[5] = scrnMsg.P.no(5).xxPopPrm;

            setArgForSubScreen(params);
        }
    }
}
