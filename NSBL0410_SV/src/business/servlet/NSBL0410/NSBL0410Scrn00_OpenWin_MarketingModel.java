/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0410;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSBL0410.common.NSBL0410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/24   Hitachi         U.Kim           Create          QC#22393
 *</pre>
 */
public class NSBL0410Scrn00_OpenWin_MarketingModel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(0).xxPopPrm.setValue("MKT_MDL");
        scrnMsg.P.no(1).xxPopPrm.setValue("MKT_MDL_CD");
        scrnMsg.P.no(2).xxPopPrm.setValue("MKT_MDL_NM");
        scrnMsg.P.no(3).xxPopPrm.setValue("MKT_MDL_CD");
        scrnMsg.P.no(4).xxPopPrm.setValue("Look Up Marketing Model Code");
        scrnMsg.P.no(5).xxPopPrm.setValue("Marketing Model");
        scrnMsg.P.no(6).xxPopPrm.setValue("Marketing Model Name");
        scrnMsg.P.no(7).xxPopPrm.setValue("Marketing Model");
        scrnMsg.P.no(8).xxPopPrm.setValue("Marketing Model Name");
        scrnMsg.P.no(9).xxPopPrm.setValue(scrnMsg.mktMdlCd.getValue());
        scrnMsg.P.no(10).xxPopPrm.setValue(scrnMsg.mktMdlDescTxt.getValue());
        Object[] params = NSBL0410CommonLogic.toArray_popup(scrnMsg.P, 11);
        setArgForSubScreen(params);

    }
}
