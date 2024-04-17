/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1700Scrn00_OpenWin_ArTran
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/18   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/05   Fujitsu         M.Suzuki        Update          S21_NA#6353
 * 2016/08/04   Fujitsu         R.Nakamura      Update          S21_NA#12143
 *</pre>
 */
public class NWAL1700Scrn00_OpenWin_ArTran extends S21CommonHandler {

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

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;

        // Clear Params
        ZYPTableUtil.clear(scrnMsg.P);

        // Set Params
        Object[] params = new Object[11];
        int i = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_INV_TP");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_INV_TP_CD");
        // Mod Start 2016/08/04 QC#12143
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_INV_TP_DESC_TXT");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_INV_TP_NM");
        // Mod End 2016/08/04 QC#12143
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_INV_TP_SORT_NUM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "AR Transaction Type Popup");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "AR Transaction Type Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "AR Transaction Type Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "AR Transaction Type Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "AR Transaction Type Name");
        scrnMsg.P.setValidCount(i);
        params[9]  = scrnMsg.dsInvTpCd;
        // 2016/04/04 S21_NA#6353 MOD Start ----------
        // Mod Start 2016/08/04 QC#12143
//        params[10] = scrnMsg.dsInvTpDescTxt;
        params[10] = scrnMsg.dsInvTpNm;
        // Mod End 2016/08/04 QC#12143
        // 2016/04/04 S21_NA#6353 MOD End ------------

        for (int j = 0; j < scrnMsg.P.getValidCount(); j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        scrnMsg.xxScrEventNm.setValue("NWAL1700Scrn00_OpenWin_ArTran");

        setArgForSubScreen(params);
    }
}
