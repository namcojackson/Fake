/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0140;

import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_ORD_CATG_D;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWCL0140.common.NWCL0140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NWCL0140 CFS Lease Package Maintenance Screen
 * Function Name : NWCL0140Scrn00_OpenWin_OrderCategoryD
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 */
public class NWCL0140Scrn00_OpenWin_OrderCategoryD extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(EVENT_NM_NWCL0140_OPEN_WIN_ORD_CATG_D);
        Object[] params = NWCL0140CommonLogic.getParamNWAL1130ForOrdCatg(getUserProfileService().getGlobalCompanyCode(), scrnMsg, scrnMsg.B.no(getButtonSelectNumber()).dsOrdCatgDescTxt_EX);
        if (params != null) {
            setArgForSubScreen(params);
        }
    }
}
