/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Hitachi         T.Kanasaka      Create          N/A
 * 2016/02/18   Hitachi         T.Aoyagi        Update          QC3700
 *</pre>
 */
public class NSAL0300Scrn00_ChangeSummaryDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        String summaryDetailMode = scrnMsg.xxModeCd_SD.getValue();
        if (DETAIL_MODE.equals(summaryDetailMode)) {
            summaryDetailMode = SUMMARY_MODE;
        } else {
            summaryDetailMode = DETAIL_MODE;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_SD, summaryDetailMode);
        // START 2016/02/18 T.Aoyagi [QC3700, ADD]
        NSAL0300CommonLogic.changeSummaryDetailBtnNm(this, scrnMsg);
        // END 2016/02/18 T.Aoyagi [QC3700, ADD]
        scrnMsg.setFocusItem(scrnMsg.dsContrNum);
    }
}
