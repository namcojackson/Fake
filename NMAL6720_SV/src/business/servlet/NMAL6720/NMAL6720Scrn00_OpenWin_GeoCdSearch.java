/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/12   Fujitsu         C.Yokoi         Create          CSA-QC#4096
 * 2016/10/12   Fujitsu         C.Yokoi         Update          CSA-QC#4096
 *</pre>
 */
public class NMAL6720Scrn00_OpenWin_GeoCdSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/10/25 Delete CSA-QC#4096 Start
        // NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        // if (!ZYPCommonFunc.hasValue(scrnMsg.ctyAddr_H1) && !ZYPCommonFunc.hasValue(scrnMsg.stCd_P1) && !ZYPCommonFunc.hasValue(scrnMsg.cntyNm_H1) && !ZYPCommonFunc.hasValue(scrnMsg.postCd_H1)) {
        //    scrnMsg.setMessageInfo(NMAM8385E, new String[] {MSG_PARAM_ADDRESS, MSG_PARAM_GEOCODE_SRCH });
        //    throw new EZDFieldErrorException();
        // }
        // 2016/10/25 Delete CSA-QC#4096 End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        Object[] params = new Object[5];

        scrnMsg.P.clear();

        if (ZYPCommonFunc.hasValue(scrnMsg.ctyAddr_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P, scrnMsg.ctyAddr_H1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.stCd_P1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, scrnMsg.stCd_P1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.postCd_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm_P, scrnMsg.postCd_H1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.cntyNm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm_P, scrnMsg.cntyNm_H1);
        }
        scrnMsg.P.setValidCount(5);

        // City Address
        params[0] = scrnMsg.P.no(0).xxPopPrm_P;
        // State
        params[1] = scrnMsg.P.no(1).xxPopPrm_P;
        // Post
        params[2] = scrnMsg.P.no(2).xxPopPrm_P;
        // County Name
        params[3] = scrnMsg.P.no(3).xxPopPrm_P;
        // Geo Code
        params[4] = scrnMsg.P.no(4).xxPopPrm_P;

        setArgForSubScreen(params);
    }
}
