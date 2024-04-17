/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410;

import static business.servlet.NPAL1410.constant.NPAL1410Constant.CONFIG_EXST_MODE_CD;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_CONF_POPUP_CO;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.MACH_ALLOC_MODE_CODE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 4/05/2016   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1410Scrn00_OpenWin_Serial_CO extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;

        scrnMsg.eventNm.setValue(EVENT_NAME_CONF_POPUP_CO);
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.O);
        ZYPTableUtil.clear(scrnMsg.I);
        Object[] param = new Object[31];
        // CONFIG_EXST_MODE_CD
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, CONFIG_EXST_MODE_CD);
        param[0] = scrnMsg.P.no(0);
        // SVC_MACH_MSTR_STS_CD
        ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(0).svcMachMstrStsCd_I, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(1).svcMachMstrStsCd_I, SVC_MACH_MSTR_STS.REMOVED);
        scrnMsg.I.setValidCount(2);
        param[9] = scrnMsg.I;
        // MACH_ALLOC_MODE_CODE
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm, MACH_ALLOC_MODE_CODE);
        param[11] = scrnMsg.P.no(11);
        // MAIN_UNIT_FLG
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(12).xxPopPrm, ZYPConstant.FLG_OFF_N);
        param[12] = scrnMsg.P.no(12);
        // WH_CD
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(14).xxPopPrm, scrnMsg.rmnfRtlWhCd_H1);
        param[14] = scrnMsg.P.no(14);
        // SUB_WH_CD
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(15).xxPopPrm, scrnMsg.rmnfRtlSwhCd_H1);
        param[15] = scrnMsg.P.no(15);

        // output
        ZYPTableUtil.clear(scrnMsg.O);
        param[30] = scrnMsg.O;

        setArgForSubScreen(param);
    }
}