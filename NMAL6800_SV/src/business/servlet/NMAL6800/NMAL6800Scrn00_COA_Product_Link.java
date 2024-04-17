/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6800;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6800.common.NMAL6800CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6800Scrn00_COA_Product_Link
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         E.Yoshitake     Create          N/A
 *</pre>
 */
public class NMAL6800Scrn00_COA_Product_Link extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        return null;
        
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6800BMsg scrnMsg = (NMAL6800BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(0).xxPopPrm.setValue("COA_PROD");
        scrnMsg.P.no(1).xxPopPrm.setValue("COA_PROD_CD");
        scrnMsg.P.no(2).xxPopPrm.setValue("COA_PROD_NM");
        scrnMsg.P.no(3).xxPopPrm.setValue("COA_PROD_CD");
        scrnMsg.P.no(4).xxPopPrm.setValue("Look Up Product Code");
        scrnMsg.P.no(5).xxPopPrm.setValue("Product Code");
        scrnMsg.P.no(6).xxPopPrm.setValue("Product Name");
        scrnMsg.P.no(7).xxPopPrm.setValue("Product Code");
        scrnMsg.P.no(8).xxPopPrm.setValue("Product Name");
        scrnMsg.P.no(9).xxPopPrm.clear();
        scrnMsg.P.no(10).xxPopPrm.clear();
        scrnMsg.P.no(29).xxPopPrm.setValue("NMAL6800Scrn00_COA_Product_Link");
        Object[] params = NMAL6800CommonLogic.toArray_popup(scrnMsg.P, 11);
        setArgForSubScreen(params);

    }

}
