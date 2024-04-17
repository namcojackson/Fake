/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1420;

import static business.servlet.NPAL1420.constant.NPAL1420Constant.IDX_10;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1420 Reman Task Entry
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 5/17/2016   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1420Scrn00_OpenWin_Item extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.A.no(getButtonSelectNumber()).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, "10");

        int paramCount = 0;
        Object[] params = new Object[IDX_10];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        setArgForSubScreen(params);
    }
}
