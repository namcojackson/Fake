/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0020.NSAL0020CMsg;
//import business.servlet.NSAL0020.constant.NSAL0020Constant;

import business.servlet.NSAL0020.constant.NSAL0020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Create          NA
 * 2016/05/13   Hitachi         T.Tomita        Update          QC#4578
 *</pre>
 */
public class NSAL0020Scrn00_Disp_NMAL6720_Add_New_Location_Screen_Bill extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;

        Object[] params = new Object[3];

        int index = getButtonSelectNumber();

        scrnMsg.xxScrEventNm.setValue(NSAL0020Constant.SCRN_EVENT_EDIT);
        // START 2016/05/13 T.Tomita [QC#4578, MOD]
        params[0] = scrnMsg.xxScrEventNm;
        params[1] = scrnMsg.A.no(index).billToAcctNum_A0;
        params[2] = scrnMsg.A.no(index).indBillToLocNum_A0;
        // END 2016/05/13 T.Tomita [QC#4578, MOD]

        setArgForSubScreen(params);

    }
}
