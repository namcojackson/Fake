/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/07   Fujitsu         C.Yokoi         Create          N/A
 * 2018/04/13   Fujitsu         M.Ohno          Create          QC#25485
 *</pre>
 */
public class NMAL2700_NWAL1130 extends S21CommonHandler {

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
        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        if (!"CMN_Close".equals(getLastGuard())) {
            // 2018/04/13 QC#25485 mod start
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).crmSlsPrflNm_A, scrnMsg.P.no(1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).crmSlsPrflNm_A, scrnMsg.P.no(2).xxComnScrColValTxt);
            // 2018/04/13 QC#25485 mod end
        }

        scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).xxChkBox_A);
    }
}
