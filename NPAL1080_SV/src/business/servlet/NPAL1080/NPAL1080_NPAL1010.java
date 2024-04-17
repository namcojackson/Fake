/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TECH_SWH_DEFALUT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request Entry
 * Function Name : Return Action from NPAL1010(Location Popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1080_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        NPAL1080CMsg bizMsg = null;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            int selectIdx = getButtonSelectNumber();
            if (selectIdx < 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlWhCd_H1, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, scrnMsg.xxPopPrm_P7);

                // QC#7386
                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P8)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlSwhCd_H1, scrnMsg.xxPopPrm_P8);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlSwhCd_H1, TECH_SWH_DEFALUT);
                }

                bizMsg = new NPAL1080CMsg();
                bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
            }
        }

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            int selectIdx = getButtonSelectNumber();
            if (selectIdx < 0) {
                NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).prntVndCd_D1, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).prntVndNm_D1, scrnMsg.xxPopPrm_P7);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).locNm_D1, scrnMsg.xxPopPrm_P8);
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).prntVndNm_D1);
            }
        }
    }
}
