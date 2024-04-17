/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0500;


import static business.servlet.NSAL0500.constant.NSAL0500Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class NSAL0500Scrn00_FileAttach extends S21CommonHandler {

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

        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;

        if (!hasValue(scrnMsg.attFileNm)) {
            String attFileNm = getContextUserInfo().getUserId() + UNDERSCORE + ZYPDateUtil.getCurrentSystemTime(TS_STORE_PATTERN);
            setValue(scrnMsg.attFileNm, attFileNm);
        }
        Object[] params = new Object[IDX_6];
        params[0] = ATTACH_MODE_MODIFICATION;
        params[1] = BUSINESS_ID;
        params[2] = scrnMsg.attFileNm.getValue();
        params[IDX_3] = "";
        params[IDX_4] = "";
        params[IDX_5] = "";
        setArgForSubScreen(params);
    }
}
