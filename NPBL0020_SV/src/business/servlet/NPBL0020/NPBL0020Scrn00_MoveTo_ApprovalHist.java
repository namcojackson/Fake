/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_0;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_1;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_2;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Open PO/Inventory Approval Hisotory Popup(NPAL1210)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            Makoto Okigami  Create          N/A
 * 08/10/2016   CITS            K.Ogino         Update          QC#9058
 *</pre>
 */
public class NPBL0020Scrn00_MoveTo_ApprovalHist extends S21CommonHandler {

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

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        scrnMsg.P.clear();
        Object[] params = new Object[IDX_2];

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_0).xxPopPrm, APVL_HIST_SRC_TP.INVENTORY_REQUEST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_1).xxPopPrm, scrnMsg.prchReqNum_HD);

        params[IDX_0] = scrnMsg.P.no(0).xxPopPrm;
        params[IDX_1] = scrnMsg.P.no(1).xxPopPrm;

        setArgForSubScreen(params);

    }
}
