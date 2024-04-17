/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0780;

import static business.servlet.NSAL0780.constant.NSAL0780Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0780.NSAL0780CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Fleet Rollup Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Hitachi         A.Kohinata      Create          N/A
 * 2017/03/10   Hitachi         K.Kitachi       Update          QC#17752
 *</pre>
 */
public class NSAL0780Scrn00_Detail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2017/03/10 K.Kitachi [QC#17752, MOD]
        NSAL0780BMsg scrnMsg = (NSAL0780BMsg) bMsg;
        NSAL0780CMsg bizMsg = new NSAL0780CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        setValue(bizMsg.xxRowNum_H, BigDecimal.valueOf(getButtonSelectNumber()));
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END 2017/03/10 K.Kitachi [QC#17752, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2017/03/10 K.Kitachi [QC#17752, MOD]
        NSAL0780BMsg scrnMsg = (NSAL0780BMsg) bMsg;
        NSAL0780CMsg bizMsg = (NSAL0780CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        Object[] params = new Object[1];
        params[0] = scrnMsg.P;

        setArgForSubScreen(params);
        // END 2017/03/10 K.Kitachi [QC#17752, MOD]
    }
}
