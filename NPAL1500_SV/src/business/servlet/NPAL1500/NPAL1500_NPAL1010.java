/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.*;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1500 PO Entry
 * Function Name : Return Action from NPAL1010(Location Popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 *</pre>
 */
public class NPAL1500_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P6)) {
            if (BTN_OPENWIN_LOCATION_FROM_SWH_EVENT_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                if (!scrnMsg.xxPopPrm_P6.getValue().equals(scrnMsg.srcRtlWhCd_SC.getValue())) {
                    scrnMsg.srcRtlWhCd_SC.setErrorInfo(1, NPAM1363E, new String[] {RETAIL_WH_CD, SOURCE_WH_CD});
                    scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd_SC);
                    scrnMsg.putErrorScreen();
                }

            } else if (BTN_OPENWIN_LOCATION_TO_SWH_EVENT_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                if (!scrnMsg.xxPopPrm_P6.getValue().equals(scrnMsg.destRtlWhCd_DS.getValue())) {
                    scrnMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM1363E, new String[] {RETAIL_WH_CD, DEST_WH_CD});
                    scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_DS);
                    scrnMsg.putErrorScreen();
                }
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500CMsg bizMsg = null;
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        if ((!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard()))) {
            if (BTN_OPENWIN_LOCATION_FROM_WH_EVENT_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                return null;

            } else if (BTN_OPENWIN_LOCATION_TO_WH_EVENT_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlWhCd_DS, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_DS, scrnMsg.xxPopPrm_P7);
                ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlSwhCd_DS, scrnMsg.xxPopPrm_P8);
                if (!ZYPCommonFunc.hasValue(scrnMsg.carrCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, scrnMsg.xxPopPrm_P6);
                }

            } else if (BTN_OPENWIN_LOCATION_FROM_DTL_SWH_EVENT_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                int selectIdx = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).srcRtlSwhCd_A1, scrnMsg.xxPopPrm_P8);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(selectIdx));

            } else if (BTN_OPENWIN_LOCATION_TO_DTL_SWH_EVENT_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                int selectIdx = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).destRtlSwhCd_A1, scrnMsg.xxPopPrm_P8);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(selectIdx));
            }

            bizMsg = new NPAL1500CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
        }

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;

        if (!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())) {
            if (BTN_OPENWIN_LOCATION_FROM_WH_EVENT_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P6)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.srcRtlWhCd_SC, scrnMsg.xxPopPrm_P6);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_SC, scrnMsg.xxPopPrm_P7);
                }
                scrnMsg.setFocusItem(scrnMsg.srcRtlWhCd_SC);

            } else if (BTN_OPENWIN_LOCATION_TO_WH_EVENT_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P6)) {
                    EZDMsg.copy(bizMsg, null, scrnMsg, null);
                }
                scrnMsg.setFocusItem(scrnMsg.destRtlWhCd_DS);

            } else if (BTN_OPENWIN_LOCATION_FROM_DTL_SWH_EVENT_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P6)) {
                    EZDMsg.copy(bizMsg, null, scrnMsg, null);
                }
                int selectIdx = getButtonSelectNumber();
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).srcRtlSwhCd_A1);

            } else if (BTN_OPENWIN_LOCATION_TO_DTL_SWH_EVENT_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P6)) {
                    EZDMsg.copy(bizMsg, null, scrnMsg, null);
                }
                int selectIdx = getButtonSelectNumber();
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).destRtlSwhCd_A1);
            }
        }
    }
}
