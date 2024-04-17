/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1200;

import static business.servlet.NSAL1200.constant.NSAL1200Constant.BUSINESS_ID;
import static business.servlet.NSAL1200.constant.NSAL1200Constant.FUNCTION_SEARCH;
import static business.servlet.NSAL1200.constant.NSAL1200Constant.NSAL1290_TAB_NM;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1200.NSAL1200CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   Hitachi         M.Gotou         Create          N/A
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#11813
 *</pre>
 */
public class NSAL1200Scrn00_OpenWin_NSAL1290 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // mod start 2016/07/13 QC#11813
        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
        int rowIndex = getButtonSelectNumber();
        setValue(scrnMsg.xxRowNum, BigDecimal.valueOf(rowIndex));

        NSAL1200CMsg bizMsg = new NSAL1200CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // mod end 2016/07/13 QC#11813
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // add start 2016/07/13 QC#11813
        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
        NSAL1200CMsg bizMsg = (NSAL1200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        Object[] params = new Object[1];
        params[0] = scrnMsg.mtrLbNm;
        setArgForSubScreen(params);
        // add end 2016/07/13 QC#11813
        openMultiModeScreen(NSAL1290_TAB_NM);
    }
}
