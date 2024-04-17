/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 *</pre>
 */
public class NSAL0620_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if (!"CMN_Close".equals(getLastGuard())) {

            if ("OpenWin_ServiceProgram".equals(scrEventNm)) {

                String svcPgmMdseCd = scrnMsg.R.no(0).xxComnScrColValTxt.getValue();
                if (ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.svcPgmMdseCd_H, svcPgmMdseCd);
                }
                String mdseNm = scrnMsg.R.no(1).xxComnScrColValTxt.getValue();
                if (ZYPCommonFunc.hasValue(mdseNm)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt_H, mdseNm);
                }

                scrnMsg.setFocusItem(scrnMsg.mdseDescShortTxt_H);
            }
        }
    }
}
