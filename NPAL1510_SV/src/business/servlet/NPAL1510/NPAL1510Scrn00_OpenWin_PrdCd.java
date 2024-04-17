/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1510.common.NPAL1510CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/30   CITS            K.Mishiro       Create          QC#22521
 *</pre>
 */
public class NPAL1510Scrn00_OpenWin_PrdCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.Q);
        scrnMsg.Q.no(0).xxPopPrm.setValue("COA_PROD");
        scrnMsg.Q.no(1).xxPopPrm.setValue("COA_PROD_CD");
        scrnMsg.Q.no(2).xxPopPrm.setValue("COA_PROD_NM");
        scrnMsg.Q.no(3).xxPopPrm.setValue("COA_PROD_CD");
        scrnMsg.Q.no(4).xxPopPrm.setValue("Look Up Product Code");
        scrnMsg.Q.no(5).xxPopPrm.setValue("Product Code");
        scrnMsg.Q.no(6).xxPopPrm.setValue("Product Name");
        scrnMsg.Q.no(7).xxPopPrm.setValue("Product Code");
        scrnMsg.Q.no(8).xxPopPrm.setValue("Product Name");
        scrnMsg.Q.no(9).xxPopPrm.setValue(scrnMsg.coaProdCd.getValue());
        scrnMsg.Q.no(10).xxPopPrm.setValue(scrnMsg.coaProdNm.getValue());
        scrnMsg.Q.no(29).xxPopPrm.setValue("NPAL1510Scrn00_COA_Product_Link");
        Object[] params = NPAL1510CommonLogic.toArray_popup(scrnMsg.Q, 11);
        setArgForSubScreen(params);

    }
}
