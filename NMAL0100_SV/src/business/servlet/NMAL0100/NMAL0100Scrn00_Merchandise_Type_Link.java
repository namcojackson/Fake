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
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 * 2020/03/24   Fujitsu         C.Hara          Update          QC#56245
 *</pre>
 */
public class NMAL0100Scrn00_Merchandise_Type_Link extends S21CommonHandler {

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
        scrnMsg.P.no(0).xxPopPrm.setValue("COA_PROJ");
        scrnMsg.P.no(1).xxPopPrm.setValue("COA_PROJ_CD");
        // 2020/03/24 QC#56245 Mod Start
        // scrnMsg.P.no(2).xxPopPrm.setValue("COA_PROJ_NM");
        scrnMsg.P.no(2).xxPopPrm.setValue("COA_PROJ_DESC_TXT");
        // 2020/03/24 QC#56245 Mod End
        scrnMsg.P.no(3).xxPopPrm.setValue("COA_PROJ_CD");
        scrnMsg.P.no(4).xxPopPrm.setValue("Look Up Merchandise Type");
        scrnMsg.P.no(5).xxPopPrm.setValue("Merchandise Type");
        scrnMsg.P.no(6).xxPopPrm.setValue("Description");
        scrnMsg.P.no(7).xxPopPrm.setValue("Merchandise Type");
        scrnMsg.P.no(8).xxPopPrm.setValue("Description");
        scrnMsg.P.no(9).xxPopPrm.setValue(scrnMsg.coaMdseTpCd_H1.getValue());
        scrnMsg.P.no(10).xxPopPrm.setValue(scrnMsg.coaProjDescTxt_H1.getValue());
        scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0100Scrn00_Merchandise_Type_Link");
        Object[] params = NMAL0100CommonLogic.toArray_popup(scrnMsg.P, 11);
        setArgForSubScreen(params);

    }

}
