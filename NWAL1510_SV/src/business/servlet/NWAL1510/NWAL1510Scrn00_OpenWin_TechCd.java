/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1510.common.NWAL1510CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1510Scrn00_OpenWin_TechCd
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Fujitsu         S.Ohki          Create          N/A
 * 2016/11/04   Fujitsu         M.Ohno          Update          S21_NA#15686
 *</pre>
 */
public class NWAL1510Scrn00_OpenWin_TechCd extends S21CommonHandler {

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
        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        // Add Start 2016/11/04 M.Ohno S21_NA#15686
//        scrnMsg.techNm_DI.clear(); // QC#19610
        Object[] params = NWAL1510CommonLogic.getParamNWAL1130ForTech(scrnMsg, getGlobalCompanyCode());
        // Add End   2016/11/04 M.Ohno S21_NA#15686

        // Del Start 2016/11/04 M.Ohno S21_NA#15686
        //        scrnMsg.techNm_DI.clear();
        //
        //        ZYPTableUtil.clear(scrnMsg.P);
        //        scrnMsg.P.no(POP_PAR_0).xxPopPrm.setValue("TECH_MSTR");
        //        scrnMsg.P.no(POP_PAR_1).xxPopPrm.setValue("TECH_TOC_CD");
        //        scrnMsg.P.no(POP_PAR_2).xxPopPrm.setValue("TECH_NM");
        //        scrnMsg.P.no(POP_PAR_3).xxPopPrm.setValue("TECH_TOC_CD");
        //        scrnMsg.P.no(POP_PAR_4).xxPopPrm.setValue("Lookup Technician Code");
        //        scrnMsg.P.no(POP_PAR_5).xxPopPrm.setValue("Technician Code");
        //        scrnMsg.P.no(POP_PAR_6).xxPopPrm.setValue("Technician name");
        //        scrnMsg.P.no(POP_PAR_7).xxPopPrm.setValue("Technician Code");
        //        scrnMsg.P.no(POP_PAR_8).xxPopPrm.setValue("Technician name");
        //        scrnMsg.P.no(POP_PAR_9).xxPopPrm.setValue(scrnMsg.istlTechCd_DI.getValue());
        //        scrnMsg.P.no(POP_PAR_10).xxPopPrm.clear();
        //        Object[] params = toArray_popup(scrnMsg.P, POP_PAR_11);
        // Del End   2016/11/04 M.Ohno S21_NA#15686

        setArgForSubScreen(params);
    }
}
