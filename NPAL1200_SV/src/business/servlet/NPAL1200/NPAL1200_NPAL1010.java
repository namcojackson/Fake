/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1200;

import static business.servlet.NPAL1200.constant.NPAL1200Constant.BUSINESS_APPLICATION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1200.NPAL1200CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NPAL1200_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1200CMsg bizMsg = null;
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;
        if ((!"CMN_Close".equals(getLastGuard())) && (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P6))) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, scrnMsg.xxPopPrm_P6);
            bizMsg = new NPAL1200CMsg();
            bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;
        NPAL1200CMsg bizMsg = (NPAL1200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if ("OpenWinFromWarehouseH".equals(scrnMsg.eventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P6)) {
                if (scrnMsg.rtlWhCd_HF.getErrorCode() != 0) {
                    scrnMsg.addCheckItem(scrnMsg.rtlWhCd_HF);
                    scrnMsg.putErrorScreen();
                }
            }
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd_HF);
        } else if ("OpenWinToWarehouseH".equals(scrnMsg.eventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P6)) {
                if (scrnMsg.rtlWhCd_HT.getErrorCode() != 0) {
                    scrnMsg.addCheckItem(scrnMsg.rtlWhCd_HT);
                    scrnMsg.putErrorScreen();
                }
            }
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd_HT);
        } else if ("OpenWinFromWarehouseD".equals(scrnMsg.eventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P6)) {
                if (scrnMsg.rtlWhCd_DF.getErrorCode() != 0) {
                    scrnMsg.addCheckItem(scrnMsg.rtlWhCd_DF);
                    scrnMsg.putErrorScreen();
                }
            }
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd_DF);
        } else if ("OpenWinToWarehouseD".equals(scrnMsg.eventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P6)) {
                if (scrnMsg.rtlWhCd_DT.getErrorCode() != 0) {
                    scrnMsg.addCheckItem(scrnMsg.rtlWhCd_DT);
                    scrnMsg.putErrorScreen();
                }
            }
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd_DT);
        }
    }
}
