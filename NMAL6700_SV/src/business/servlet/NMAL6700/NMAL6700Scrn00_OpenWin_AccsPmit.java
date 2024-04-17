/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6700.NMAL6700CMsg;
//import business.servlet.NMAL6700.constant.NMAL6700Constant;

import business.servlet.NMAL6700.common.NMAL6700CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/04/06   Fujitsu         C.Yokoi         Update          CSA-QC#6633
 *</pre>
 */
public class NMAL6700Scrn00_OpenWin_AccsPmit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(0).xxPopPrm.setValue("SVC_ACCS_PMIT_VAL");
        scrnMsg.P.no(1).xxPopPrm.setValue("SVC_ACCS_PMIT_NUM");
        scrnMsg.P.no(2).xxPopPrm.setValue("SVC_ACCS_PMIT_DESC_TXT");
        scrnMsg.P.no(3).xxPopPrm.setValue("SVC_ACCS_PMIT_NUM");
        scrnMsg.P.no(4).xxPopPrm.setValue("Service Access Permit Popup");
        scrnMsg.P.no(5).xxPopPrm.setValue("Service Access Permit #");
        scrnMsg.P.no(6).xxPopPrm.setValue("Service Access Permit Desc");
        scrnMsg.P.no(7).xxPopPrm.setValue("Service Access Permit #");
        scrnMsg.P.no(8).xxPopPrm.setValue("Service Access Permit Desc");
        int selectedRow = getButtonSelectNumber();
        scrnMsg.P.no(9).xxPopPrm.setValue(((NMAL6700_NBMsg) scrnMsg.N.get(selectedRow)).svcAccsPmitNum_N1.getValue());
        scrnMsg.P.no(10).xxPopPrm.setValue("");
        Object[] params = NMAL6700CommonLogic.toArray_popup(scrnMsg.P, 11);

        setArgForSubScreen(params);
    }
}
