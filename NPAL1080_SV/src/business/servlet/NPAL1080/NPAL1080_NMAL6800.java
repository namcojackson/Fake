/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.EVENT_NM_CMN_CLOSE;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1080.NPAL1080CMsg;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : Return Action from NMAL6800(Item Search Popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura       Create          N/A
 * 12/17/2018   CITS       Takeshi Tokutomi     Update          QC#29299
 *</pre>
 */
public class NPAL1080_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        NPAL1080CMsg bizMsg = null;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            int idx = this.getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_D1, new BigDecimal(idx));

            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).mdseCd_D1, scrnMsg.xxPopPrm_P0);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).mdseDescShortTxt_D1, scrnMsg.xxPopPrm_P1);

            bizMsg = new NPAL1080CMsg();
            bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

        }
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
            NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            // check error.
            int idx = this.getButtonSelectNumber();

            scrnMsg.addCheckItem(scrnMsg.A.no(idx).mdseCd_D1);
            scrnMsg.putErrorScreen();

            scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).mdseCd_D1);
        }
    }
}
