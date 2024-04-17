/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6820.common.NMAL6820CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/14   Fujitsu         C.Yokoi         Create          CSA-QC#4096
 * 2016/10/25   Fujitsu         C.Yokoi         Update          CSA-QC#4096
 *</pre>
 */
public class NMAL6820Scrn00_OpenWin_GeoCdSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/10/25 Delete CSA-QC#4096 Start
        // NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        // if (!ZYPCommonFunc.hasValue(scrnMsg.ctyAddr_S1) && !ZYPCommonFunc.hasValue(scrnMsg.stCd_S1)
        //         && !ZYPCommonFunc.hasValue(scrnMsg.cntyNm_S1) && !ZYPCommonFunc.hasValue(scrnMsg.postCd_S1)) {
        //     scrnMsg.setMessageInfo(NMAM8385E, new String[] {MESSAGE_PARAM_SHIP_TO_LOC, MESSAGE_PARAM_GEOCODE_SRCH});
        //     throw new EZDFieldErrorException();
        // }
        // 2016/10/25 Delete CSA-QC#4096 Start
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        Object[] params = NMAL6820CommonLogic.setParamForGeoCodeSearchPopup(scrnMsg);

        // Sub screen transition
        setArgForSubScreen(params);
    }
}
