/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_REQ_TECH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL6720Scrn00_OpenWin_ReqTech extends S21CommonHandler {

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

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        scrnMsg.P.clear();

        scrnMsg.xxScrEventNm_P.setValue(EVENT_REQ_TECH);

        scrnMsg.P.no(0).xxPopPrm_P.setValue("TECH_MSTR");
        scrnMsg.P.no(1).xxPopPrm_P.setValue("TECH_TOC_CD");
        scrnMsg.P.no(2).xxPopPrm_P.setValue("TECH_NM");
        scrnMsg.P.no(3).xxPopPrm_P.setValue("TECH_TOC_CD");
        scrnMsg.P.no(4).xxPopPrm_P.setValue("Lookup Technician Code");
        scrnMsg.P.no(5).xxPopPrm_P.setValue("Technician Code");
        scrnMsg.P.no(6).xxPopPrm_P.setValue("Technician Name");
        scrnMsg.P.no(7).xxPopPrm_P.setValue("Technician Code");
        scrnMsg.P.no(8).xxPopPrm_P.setValue("Technician Name");

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm_P, scrnMsg.reqTechCd_SA);

        Object[] param = new Object[11];
        param[0] = scrnMsg.P.no(0).xxPopPrm_P;
        param[1] = scrnMsg.P.no(1).xxPopPrm_P;
        param[2] = scrnMsg.P.no(2).xxPopPrm_P;
        param[3] = scrnMsg.P.no(3).xxPopPrm_P;
        param[4] = scrnMsg.P.no(4).xxPopPrm_P;
        param[5] = scrnMsg.P.no(5).xxPopPrm_P;
        param[6] = scrnMsg.P.no(6).xxPopPrm_P;
        param[7] = scrnMsg.P.no(7).xxPopPrm_P;
        param[8] = scrnMsg.P.no(8).xxPopPrm_P;
        param[9] = scrnMsg.P.no(9).xxPopPrm_P;
        param[10] = scrnMsg.P.no(10).xxPopPrm_P;

        setArgForSubScreen(param);
    }
}
