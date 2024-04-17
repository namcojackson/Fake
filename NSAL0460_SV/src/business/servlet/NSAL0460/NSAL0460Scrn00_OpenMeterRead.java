/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0460;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NSAL0460.constant.NSAL0460Constant.*;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Start Read Capture
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         T.Iwamoto       Create          N/A
 * 2015/11/27   Hitachi         T.Iwamoto       Update          QC#1235
 * 2015/12/01   Hitachi         T.Iwamoto       Update          QC#1261
 * 2019/11/05   Hitachi         K.Kitachi       Update          QC#54164
 *</pre>
 */
public class NSAL0460Scrn00_OpenMeterRead extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0460BMsg scrnMsg = (NSAL0460BMsg) bMsg;
        int index = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk_OP, scrnMsg.A.no(index).svcMachMstrPk);
        // [QC#1261,MOD]START
        int befDay = -1 * (scrnMsg.xxNum.getValueInt() + 1);
        String startDt = ZYPDateUtil.addDays(scrnMsg.A.no(index).contrEffFromDt_P.getValue(), befDay);
        ZYPEZDItemValueSetter.setValue(scrnMsg.startDt_OP, startDt);
        // [QC#1261,MOD]END
        // START 2019/11/05 K.Kitachi [QC#54164, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk_OP, scrnMsg.A.no(index).dsContrDtlPk);
        // END 2019/11/05 K.Kitachi [QC#54164, ADD]
        scrnMsg.Q.clear();
        Object[] params = new Object[NSAL1060_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.svcMachMstrPk_OP;
        params[i++] = scrnMsg.startDt_OP;
        params[i++] = scrnMsg.Q;
        // START 2019/11/05 K.Kitachi [QC#54164, ADD]
        params[i++] = scrnMsg.dsContrDtlPk_OP;
        // END 2019/11/05 K.Kitachi [QC#54164, ADD]

        setArgForSubScreen(params);

    }
}
