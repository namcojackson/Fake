/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0620.common.NLCL0620CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0620 Tech PI Entry
 * Function Name : Open Return to Technician Search Popup(NWAL1130)
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/08/2016   CITS            Makoto Okigami  Create          N/A
 * 12/11/2018   Fujitsu         S.Ohki          Update          QC#28755
 *</pre>
 */
public class NLCL0620Scrn00_OpenWin_Technician extends S21CommonHandler {

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

        NLCL0620BMsg scrnMsg = (NLCL0620BMsg) bMsg;

        // START 2018/12/11 S.Ohki [QC#28755,ADD]
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        // END 2018/12/11 S.Ohki [QC#28755,ADD]

        // set popup parameter
        Object[] params = NLCL0620CommonLogic.setTechnicianSearchParam(scrnMsg, getGlobalCompanyCode());

        // send popup parameter
        setArgForSubScreen(params);

    }
}
