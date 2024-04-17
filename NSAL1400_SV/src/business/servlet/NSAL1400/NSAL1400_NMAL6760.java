/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1400;

import static business.servlet.NSAL1400.constant.NSAL1400Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;


import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * NSAL1400 Named Customer Resource setup
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         T.Tomita        Create          QC#18362
 *</pre>
 */
public class NSAL1400_NMAL6760 extends S21CommonHandler {

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
        NSAL1400BMsg scrnMsg = (NSAL1400BMsg) bMsg;
        getArgForSubScreen();
        if ("CMN_Close".equals(getLastGuard())) {
            if (BTN_OPEN_CUST_LINE.equals(scrnMsg.eventNm_P.getValue())) {
                if (!hasValue(scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).dsAcctNum_A)) {
                    scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).dsAcctNm_A.clear();
                }
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).dsAcctNum_A);
            }
        } else {
            if (BTN_OPEN_CUST.equals(scrnMsg.eventNm_P.getValue())) {
                setValue(scrnMsg.dsAcctNm_H, scrnMsg.xxPopPrm_01);
                scrnMsg.setFocusItem(scrnMsg.dsAcctNm_H);
            } else if (BTN_OPEN_CUST_LINE.equals(scrnMsg.eventNm_P.getValue())) {
                setValue(scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).dsAcctNum_A, scrnMsg.xxPopPrm_00);
                setValue(scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).dsAcctNm_A, scrnMsg.xxPopPrm_01);
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).contrAdminPsnCd_A);
            }
        }
        scrnMsg.eventNm_P.clear();
    }
}
