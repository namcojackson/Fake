
package business.servlet.NMAL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0100.common.NMAL0100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/10/18   Fujitsu         C.Hara          Create          QC#53815
 *</pre>
 */
public class NMAL0100Scrn00_Manufacturer_Link extends S21CommonHandler   {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(0).xxPopPrm.setValue("MDSE_ITEM_MNF");
        scrnMsg.P.no(1).xxPopPrm.setValue("MDSE_ITEM_MNF_CD");
        scrnMsg.P.no(2).xxPopPrm.setValue("MDSE_ITEM_MNF_NM");
        scrnMsg.P.no(3).xxPopPrm.setValue("MDSE_ITEM_MNF_CD");
        scrnMsg.P.no(4).xxPopPrm.setValue("Look Up Manufacturer");
        scrnMsg.P.no(5).xxPopPrm.setValue("Manufacturer Code");
        scrnMsg.P.no(6).xxPopPrm.setValue("Manufacturer Name");
        scrnMsg.P.no(7).xxPopPrm.setValue("Manufacturer Code");
        scrnMsg.P.no(8).xxPopPrm.setValue("Manufacturer Name");
        scrnMsg.P.no(9).xxPopPrm.setValue("");
        scrnMsg.P.no(10).xxPopPrm.setValue(scrnMsg.mdseItemMnfNm_H1.getValue());
        scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0100Scrn00_Manufacturer_Link");
        Object[] params = NMAL0100CommonLogic.toArray_popup(scrnMsg.P, 11);
        setArgForSubScreen(params);

    }

}
