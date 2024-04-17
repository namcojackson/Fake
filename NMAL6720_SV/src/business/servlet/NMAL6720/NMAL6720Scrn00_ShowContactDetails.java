/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.MAX_ROW_NUM;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NMAM0050E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/29   Fujitsu         C.Tanaka        Update          QC#2824
 *</pre>
 */
public class NMAL6720Scrn00_ShowContactDetails extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        if (scrnMsg.xxPageShowOfNum_C.getValueInt() == MAX_ROW_NUM) {
            scrnMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(MAX_ROW_NUM) });
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        int index = getButtonSelectNumber();

        Object[] params = new Object[3];
        params[2] = scrnMsg.locNum_H1;
        if (0 <= index) {
            params[0] = scrnMsg.C.no(index).ctacPsnNum_C1;
        }

        setArgForSubScreen(params);
    }
}
