/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410;

import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_SWH_POPUP;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_WH_POPUP;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
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
 * 10/18/2016  CITS       T.Wada               Update          QC#14002
 * 12/15/2017   CITS            K.Ogino         Update          QC#22836
 * 05/29/2018   CITS            S.Katsuma       Update          QC#26128
 *</pre>
 */
public class NPAL1410_NPAL1010 extends S21CommonHandler {

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

        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        // QC#22836
        int idx = getButtonSelectNumber();
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            // START 2018/05/29 S.Katsuma [QC#26128,DEL]
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).srcRtlSwhCd_A1, scrnMsg.xxPopPrm_P6);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, scrnMsg.xxPopPrm_P7);
            // END 2018/05/29 S.Katsuma [QC#26128,DEL]


            if (EVENT_NAME_WH_POPUP.equals(scrnMsg.eventNm.getValue())) {
                // START 2018/05/29 S.Katsuma [QC#26128,ADD]
                ZYPEZDItemValueSetter.setValue(scrnMsg.rmnfRtlWhCd_H1, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, scrnMsg.xxPopPrm_P7);
                // END 2018/05/29 S.Katsuma [QC#26128,ADD]
                //QC#14002
                if (!ZYPCommonFunc.hasValue(scrnMsg.rmnfRtlSwhCd_H1)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rmnfRtlSwhCd_H1, scrnMsg.xxPopPrm_P8);
                }
                scrnMsg.setFocusItem(scrnMsg.rmnfRtlWhCd_H1);
            } else if (EVENT_NAME_SWH_POPUP.equals(scrnMsg.eventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).srcRtlSwhCd_A1, scrnMsg.xxPopPrm_P8);
                scrnMsg.setFocusItem(scrnMsg.A.no(idx).srcRtlSwhCd_A1);
            }
        }
    }
}
