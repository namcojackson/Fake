/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2510.common.NMAL2510CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/12   Fujitsu         C.Yokoi         Create          CSA-QC#4096
 * 2016/10/25   Fujitsu         C.Yokoi         Update          CSA-QC#4096
 *</pre>
 */
public class NMAL2510Scrn00_OpenWin_GeoCdLookup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/10/25 Delete CSA-QC#4096 Start
        // NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        // if (!ZYPCommonFunc.hasValue(scrnMsg.ctyAddr_H1) && !ZYPCommonFunc.hasValue(scrnMsg.stCd_H1)
        //         && !ZYPCommonFunc.hasValue(scrnMsg.cntyNm_H1) && !ZYPCommonFunc.hasValue(scrnMsg.postCd_H1)) {
        //     scrnMsg.setMessageInfo(NMAL2510Constant.NMAM8385E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_PHYSICAL_LOC, NMAL2510Constant.NAME_FOR_MESSAGE_GEOCODE_SRCH});
        //     throw new EZDFieldErrorException();
        // }
        // 2016/10/25 Delete CSA-QC#4096 End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        Object[] params = NMAL2510CommonLogic.setParamForGeoCodeSearchPopup(scrnMsg);

        // Sub screen transition
        setArgForSubScreen(params);
    }
}
