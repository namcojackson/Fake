/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410;

import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_ITEM_CC_POPUP;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_ITEM_CO_POPUP;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_ITEM_P_POPUP;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.BUSINESS_APPLICATION_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1410.NPAL1410CMsg;
import business.servlet.NPAL1410.common.NPAL1410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
public class NPAL1410_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        NPAL1410CMsg bizMsg = new NPAL1410CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P0, scrnMsg.xxPopPrm_P0);
        bizMsg.xxNum.setValue(getButtonSelectNumber());
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        if (EVENT_NAME_ITEM_CO_POPUP.equals(scrnMsg.eventNm.getValue())) {
            if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).mdseCd_A1, scrnMsg.xxPopPrm_P0);
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).mdseCd_A1);
        } else if (EVENT_NAME_ITEM_CC_POPUP.equals(scrnMsg.eventNm.getValue())) {
            if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).rmnfToCmptMdseCd_A1, scrnMsg.xxPopPrm_P0);
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).rmnfToCmptMdseCd_A1);
        } else if (EVENT_NAME_ITEM_P_POPUP.equals(scrnMsg.eventNm.getValue())) {
            if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                NPAL1410CMsg bizMsg = (NPAL1410CMsg) cMsg;
                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
                NPAL1410CommonLogic.commonInit(scrnMsg);
                NPAL1410CommonLogic.control(this, scrnMsg, funcList);
            }
            scrnMsg.setFocusItem(scrnMsg.C.no(getButtonSelectNumber()).mdseCd_C1);
        }
    }
}