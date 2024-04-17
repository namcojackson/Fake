/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

import static business.servlet.NMAL0170.constant.NMAL0170Constant.EVENT_ITEM_DETAIL_SUPD_TO;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0170.common.NMAL0170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL0170 Supersession Staging Info Inquiry
 * Function Name : Open Return to Item Search Popup(NMAL6800)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/22/2016   CITS            S.Tanikawa      Create          QC#2669
 *</pre>
 */
public class NMAL0170Scrn00_OpenWin_LinkItemDetailSupdTo extends S21CommonHandler {

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

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;

        // set popup parameter
        int selectedNum = getButtonSelectNumber();
        // Set Event Name.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_EV, EVENT_ITEM_DETAIL_SUPD_TO);

        Object[] params = NMAL0170CommonLogic.setMdseInfoParam(scrnMsg, selectedNum);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
