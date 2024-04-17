/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0230;

import static business.servlet.NMAL0230.constant.NMAL0230Constant.BOM_PROD_POPUP;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL0230Scrn00_OpenWin_BomProdCd extends S21CommonHandler {

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

        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.O);
        scrnMsg.O.no(0).xxPopPrm.setValue("COA_PROD");
        scrnMsg.O.no(1).xxPopPrm.setValue("COA_PROD_CD");
        scrnMsg.O.no(2).xxPopPrm.setValue("COA_PROD_NM");
        scrnMsg.O.no(3).xxPopPrm.setValue("COA_PROD_CD");
        scrnMsg.O.no(4).xxPopPrm.setValue("Look Up COA Product Code");
        scrnMsg.O.no(5).xxPopPrm.setValue("COA Product");
        scrnMsg.O.no(6).xxPopPrm.setValue("COA Product Name");
        scrnMsg.O.no(7).xxPopPrm.setValue("COA Product");
        scrnMsg.O.no(8).xxPopPrm.setValue("COA Product Name");
        scrnMsg.O.no(9).xxPopPrm.setValue(scrnMsg.coaProdCd_BO.getValue());
        scrnMsg.O.no(10).xxPopPrm.setValue(scrnMsg.coaProdNm_BO.getValue());

        int size = scrnMsg.O.length();
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = scrnMsg.O.no(i).xxPopPrm;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, BOM_PROD_POPUP);
        setArgForSubScreen(param);

    }
}
