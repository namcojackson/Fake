/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0730;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2019/01/10   Hitachi         K.Kitachi       Update          QC#26928
 *</pre>
 */
public class NSAL0730_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;

        String custPoNum = scrnMsg.C.no(1).xxComnScrColValTxt.getValue();
        // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//        String poDt = scrnMsg.C.no(2).xxComnScrColValTxt.getValue();
        String fromDt = scrnMsg.C.no(2).xxComnScrColValTxt.getValue();
        String thruDt = scrnMsg.C.no(3).xxComnScrColValTxt.getValue();
        // END 2019/01/10 K.Kitachi [QC#26928, MOD]
        if (custPoNum.isEmpty()) {
            return;
        }

        int index = getButtonSelectNumber();
        if (index < 0) {
            setValue(scrnMsg.custPoNum, custPoNum);
            // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//            setValue(scrnMsg.poDt, poDt);
            setValue(scrnMsg.poFromDt, fromDt);
            setValue(scrnMsg.poDt, thruDt);
            // END 2019/01/10 K.Kitachi [QC#26928, MOD]
            scrnMsg.setFocusItem(scrnMsg.custPoNum);
        } else {
            // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//            setValue(scrnMsg.A.no(index).custPoNum_D2, custPoNum);
//            setValue(scrnMsg.A.no(index).poDt_D2, poDt);
//            scrnMsg.setFocusItem(scrnMsg.A.no(index).custPoNum_D2);
            setValue(scrnMsg.A.no(index).custPoNum_A, custPoNum);
            setValue(scrnMsg.A.no(index).poFromDt_A, fromDt);
            setValue(scrnMsg.A.no(index).poDt_A, thruDt);
            scrnMsg.setFocusItem(scrnMsg.A.no(index).custPoNum_A);
            // END 2019/01/10 K.Kitachi [QC#26928, MOD]
        }
    }
}
