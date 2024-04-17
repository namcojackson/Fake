/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * This class no use.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi        T.Yonekura         Create          N/A
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          N/A(No Mark up comment)
 *</pre>
 */
public class NSAL0020Scrn00_OpenWin_Serial_Number_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
//        scrnMsg.addCheckItem(scrnMsg.serNum);
//        scrnMsg.addCheckItem(scrnMsg.custMachCtrlNum);
//        scrnMsg.addCheckItem(scrnMsg.mdlNm);
//        scrnMsg.addCheckItem(scrnMsg.soNum);
//        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
//        scrnMsg.addCheckItem(scrnMsg.ctacPsnTelNum);
//        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;

//        scrnMsg.xxModeCd_P1.setValue(NSAL0020Constant.MODE_ALL_SERIALIZED_ITEMS);
//
//        Object[] params = new Object[17];
//
//        params[0] = scrnMsg.xxModeCd_P1;
//        params[1] = scrnMsg.serNum;
//        params[2] = scrnMsg.custMachCtrlNum;
//        params[3] = scrnMsg.mdlNm;
//        params[4] = scrnMsg.soNum;
//        params[5] = scrnMsg.dsContrNum;
//        params[6] = scrnMsg.shipToCustCd_P1;
//        params[7] = scrnMsg.locNm;
//        params[8] = scrnMsg.svcMachMstrStsCd_P1;
//        params[9] = scrnMsg.ctacPsnTelNum;
//        params[10] = scrnMsg.rcvRptNum;
//        params[11] = scrnMsg.mdseCd;
//        params[12] = scrnMsg.svcMachMstrPk_P1;
//        params[13] = scrnMsg.soNum;
//        params[14] = scrnMsg.serNum;
//        params[15] = scrnMsg.mdseCd;
//        params[16] = scrnMsg.rcvRptNum;
//
//        setArgForSubScreen(params);

    }
}
