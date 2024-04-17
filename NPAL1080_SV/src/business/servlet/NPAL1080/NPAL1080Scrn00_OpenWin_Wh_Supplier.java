/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1080Scrn00_OpenWin_Wh_Supplier extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        NPAL1080CMsg bizMsg = null;
        if (PROCR_TP.WAREHOUSE.equals(scrnMsg.A.no(selectIdx).procrTpCd_SE.getValue())) {

            bizMsg = new NPAL1080CMsg();
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
            bizMsg.setFunctionCode("20");
            bizMsg.xxNum_D1.setValue(selectIdx);
        }
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        if (PROCR_TP.WAREHOUSE.equals(scrnMsg.A.no(selectIdx).procrTpCd_SE.getValue())) {
            NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            Object[] params = NPAL1080CommonLogic.setParamForLocationPopup(scrnMsg, selectIdx);
            setArgForSubScreen(params);
            setResult("Warehouse");
        } else {
            Object[] params = NPAL1080CommonLogic.setParamForSupplierPopup(scrnMsg, selectIdx);
            setArgForSubScreen(params);
            setResult("Supplier");
        }
    }
}
