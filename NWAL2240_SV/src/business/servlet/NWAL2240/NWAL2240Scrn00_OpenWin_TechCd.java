/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2240;

import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.toArray_popup;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_0;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_1;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_10;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_11;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_2;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_3;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_4;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_5;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_6;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_7;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_8;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_9;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL2240Scrn00_OpenWin_TechCd extends S21CommonHandler {

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

        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;

        scrnMsg.techNm_DI.clear();

        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(POP_PAR_0).xxPopPrm.setValue("TECH_MSTR");
        scrnMsg.P.no(POP_PAR_1).xxPopPrm.setValue("TECH_TOC_CD");
        scrnMsg.P.no(POP_PAR_2).xxPopPrm.setValue("TECH_NM");
        scrnMsg.P.no(POP_PAR_3).xxPopPrm.setValue("TECH_TOC_CD");
        scrnMsg.P.no(POP_PAR_4).xxPopPrm.setValue("Lookup Technician Code");
        scrnMsg.P.no(POP_PAR_5).xxPopPrm.setValue("Technician Code");
        scrnMsg.P.no(POP_PAR_6).xxPopPrm.setValue("Technician name");
        scrnMsg.P.no(POP_PAR_7).xxPopPrm.setValue("Technician Code");
        scrnMsg.P.no(POP_PAR_8).xxPopPrm.setValue("Technician name");
        scrnMsg.P.no(POP_PAR_9).xxPopPrm.setValue(scrnMsg.istlTechCd_DI.getValue());
        scrnMsg.P.no(POP_PAR_10).xxPopPrm.clear();
        Object[] params = toArray_popup(scrnMsg.P, POP_PAR_11);
        setArgForSubScreen(params);
    }
}
