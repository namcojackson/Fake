/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0530;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0530.common.NSAL0530CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Solution Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 * 2016/11/22   Hitachi         K.Kojima        Update          QC#16168
 *</pre>
 */
public class NSAL0530Scrn00_OpenWin_Serial extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        NSAL0530CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        // START 2016/11/22 K.Kojima [QC#16168,DEL]
        // scrnMsg.xxModeCd_P.clear();
        // setValue(scrnMsg.serNum_P, scrnMsg.serNum_H.getValue());
        // scrnMsg.custMachCtrlNum_P.clear();
        // scrnMsg.mdlNm_P.clear();
        // scrnMsg.soNum_P.clear();
        // scrnMsg.contrNum_P.clear();
        // scrnMsg.shipToCustCd_P.clear();
        // scrnMsg.locNm_P.clear();
        // scrnMsg.svcMachMstrStsCd_P.clear();
        // scrnMsg.ctacPsnTelNum_P.clear();
        // scrnMsg.rcvRptNum_P.clear();
        // scrnMsg.mdseCd_P.clear();
        // scrnMsg.soNum_PO.clear();
        // scrnMsg.svcMachMstrPk_PO.clear();
        // scrnMsg.serNum_PO.clear();
        // scrnMsg.mdseCd_PO.clear();
        // scrnMsg.rcvRptNum_PO.clear();
        //
        // Object[] params = new Object[NSAL0030_PRM_LENGTH];
        // int i = 0;
        // params[i++] = scrnMsg.xxModeCd_P;
        // params[i++] = scrnMsg.serNum_P;
        // params[i++] = scrnMsg.custMachCtrlNum_P;
        // params[i++] = scrnMsg.mdlNm_P;
        // params[i++] = scrnMsg.soNum_P;
        // params[i++] = scrnMsg.contrNum_P;
        // params[i++] = scrnMsg.shipToCustCd_P;
        // params[i++] = scrnMsg.locNm_P;
        // params[i++] = scrnMsg.svcMachMstrStsCd_P;
        // params[i++] = scrnMsg.ctacPsnTelNum_P;
        // params[i++] = scrnMsg.rcvRptNum_P;
        // params[i++] = scrnMsg.mdseCd_P;
        //
        // params[i++] = scrnMsg.svcMachMstrPk_PO;
        // params[i++] = scrnMsg.soNum_PO;
        // params[i++] = scrnMsg.serNum_PO;
        // params[i++] = scrnMsg.mdseCd_PO;
        // params[i++] = scrnMsg.rcvRptNum_PO;
        // END 2016/11/22 K.Kojima [QC#16168,DEL]

        // START 2016/11/22 K.Kojima [QC#16168,ADD]
        ZYPTableUtil.clear(scrnMsg.O);
        Object[] params = new Object[31];
        params[2] = scrnMsg.serNum_H;
        params[30] = scrnMsg.O;
        // END 2016/11/22 K.Kojima [QC#16168,ADD]

        setArgForSubScreen(params);
    }
}
