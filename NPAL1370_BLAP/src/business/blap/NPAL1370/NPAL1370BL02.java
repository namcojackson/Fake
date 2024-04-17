/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1370;

import static business.blap.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_FRM_SWH;
import static business.blap.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_FRM_WH;
import static business.blap.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_TO_SWH;
import static business.blap.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_TO_WH;
import static business.blap.NPAL1370.constant.NPAL1370Constant.EVENT_CMN_CLOSE;
import static business.blap.NPAL1370.constant.NPAL1370Constant.EVENT_SET_CPY_FRM_SWH_NM;
import static business.blap.NPAL1370.constant.NPAL1370Constant.EVENT_SET_CPY_FRM_WH_NM;
import static business.blap.NPAL1370.constant.NPAL1370Constant.EVENT_SET_CPY_TO_SWH_NM;
import static business.blap.NPAL1370.constant.NPAL1370Constant.EVENT_SET_CPY_TO_WH_NM;
import static business.blap.NPAL1370.constant.NPAL1370Constant.NPAM0076E;
import static business.blap.NPAL1370.constant.NPAL1370Constant.NZZM0010E;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1370.common.NPAL1370CommonLogic;
import business.db.RTL_SWHTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1370 Min Max Planning Copy Popup
 * Function Name : Search Business Process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2015   CITS       Takeshi Tokutomi     Create          N/A
 *</pre>
 */
public class NPAL1370BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NPAL1370CMsg c = (NPAL1370CMsg) cMsg;
            NPAL1370SMsg s = (NPAL1370SMsg) sMsg;

            if (EVENT_SET_CPY_FRM_WH_NM.equals(screenAplID)) {
                doProcessSetCopyFromWHName(c, s);
            } else if (EVENT_SET_CPY_FRM_SWH_NM.equals(screenAplID)) {
                doProcessSetCopyFromSWHName(c, s);
            } else if (EVENT_SET_CPY_TO_WH_NM.equals(screenAplID)) {
                doProcessSetCopyToWHName(c, s);
            } else if (EVENT_SET_CPY_TO_SWH_NM.equals(screenAplID)) {
                doProcessSetCopyToSWHName(c, s);
            } else if (EVENT_CMN_CLOSE.equals(screenAplID)) {
                doProcessCommonClose(c, s);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * set copy from warehouse name
     * @param cMsg NPAL1370CMsg
     * @param sMsg NPAL1370SMsg
     */
    private void doProcessSetCopyFromWHName(NPAL1370CMsg cMsg, NPAL1370SMsg sMsg) {
        EZDDebugOutput.println(1, "NPAL1370 start[doProcessSetCopyFromWHName]", null);

        String whNm = NPAL1370CommonLogic.getWHName(cMsg.glblCmpyCd.getValue(), cMsg.rtlWhCd_FR.getValue());

        if (ZYPCommonFunc.hasValue(whNm)) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_FR, whNm);
        } else {
            cMsg.rtlWhCd_FR.setErrorInfo(1, NZZM0010E, new String[] {cMsg.rtlWhCd_FR.getValue() });
        }

        EZDDebugOutput.println(1, "NPAL1370 end[doProcessSetCopyFromWHName]", null);
    }

    /**
     * set copy from sub warehouse name
     * @param cMsg NPAL1370CMsg
     * @param sMsg NPAL1370SMsg
     */
    private void doProcessSetCopyFromSWHName(NPAL1370CMsg cMsg, NPAL1370SMsg sMsg) {
        EZDDebugOutput.println(1, "NPAL1370 start[doProcessSetCopyFromSWHName]", null);

        String swhNm = NPAL1370CommonLogic.getSWHName(cMsg.glblCmpyCd.getValue(), cMsg.rtlSwhCd_FR.getValue());

        if (ZYPCommonFunc.hasValue(swhNm)) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_FR, swhNm);
        } else {
            cMsg.rtlSwhCd_FR.setErrorInfo(1, NZZM0010E, new String[] {cMsg.rtlSwhCd_FR.getValue() });
        }

        EZDDebugOutput.println(1, "NPAL1370 end[doProcessSetCopyFromSWHName]", null);
    }

    /**
     * set copy from warehouse name
     * @param cMsg NPAL1370CMsg
     * @param sMsg NPAL1370SMsg
     */
    private void doProcessSetCopyToWHName(NPAL1370CMsg cMsg, NPAL1370SMsg sMsg) {
        EZDDebugOutput.println(1, "NPAL1370 start[doProcessSetCopyToWHName]", null);

        String whNm = NPAL1370CommonLogic.getWHName(cMsg.glblCmpyCd.getValue(), cMsg.rtlWhCd_TO.getValue());

        if (ZYPCommonFunc.hasValue(whNm)) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_TO, whNm);
        } else {
            cMsg.rtlWhCd_TO.setErrorInfo(1, NZZM0010E, new String[] {cMsg.rtlWhCd_TO.getValue() });
        }

        EZDDebugOutput.println(1, "NPAL1370 end[doProcessSetCopyToWHName]", null);
    }

    /**
     * set copy from sub warehouse name
     * @param cMsg NPAL1370CMsg
     * @param sMsg NPAL1370SMsg
     */
    private void doProcessSetCopyToSWHName(NPAL1370CMsg cMsg, NPAL1370SMsg sMsg) {
        EZDDebugOutput.println(1, "NPAL1370 start[doProcessSetCopyToSWHName]", null);

        String swhNm = NPAL1370CommonLogic.getSWHName(cMsg.glblCmpyCd.getValue(), cMsg.rtlSwhCd_TO.getValue());

        if (ZYPCommonFunc.hasValue(swhNm)) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_TO, swhNm);
        } else {
            cMsg.rtlSwhCd_TO.setErrorInfo(1, NZZM0010E, new String[] {cMsg.rtlSwhCd_TO.getValue() });
        }

        EZDDebugOutput.println(1, "NPAL1370 end[doProcessSetCopyToSWHName]", null);
    }

    /**
     * Common Close
     * @param cMsg NPAL1370CMsg
     * @param sMsg NPAL1370SMsg
     */
    private void doProcessCommonClose(NPAL1370CMsg cMsg, NPAL1370SMsg sMsg) {
        EZDDebugOutput.println(1, "NPAL1370 start[doProcessCommonClose]", null);
        // Check Copy From WH / SWH
        if (!checkRtlSwh(cMsg.glblCmpyCd.getValue(), cMsg.rtlWhCd_FR.getValue(), cMsg.rtlSwhCd_FR.getValue())) {
            cMsg.rtlWhCd_FR.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_NM_CPY_FRM_WH});
            cMsg.rtlSwhCd_FR.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_NM_CPY_FRM_SWH});
        }
        // Check Copy To WH / SWH
        if (!checkRtlSwh(cMsg.glblCmpyCd.getValue(), cMsg.rtlWhCd_TO.getValue(), cMsg.rtlSwhCd_TO.getValue())) {
            cMsg.rtlWhCd_TO.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_NM_CPY_TO_WH});
            cMsg.rtlSwhCd_TO.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_NM_CPY_TO_SWH});
        }
        EZDDebugOutput.println(1, "NPAL1370 end[doProcessCommonClose]", null);
    }

    /**
     * Check RTL_SWH
     * @param String glblCmpyCd
     * @param String rtlWhCd
     * @param String rtlSwhCd
     * @return boolean
     */
    public static boolean checkRtlSwh(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {

        RTL_SWHTMsg tMsg = new RTL_SWHTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.rtlWhCd.setValue(rtlWhCd);
        tMsg.rtlSwhCd.setValue(rtlSwhCd);

        RTL_SWHTMsg rtlSwhTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (rtlSwhTMsg == null) {
            return false;
        } else {
            return true;
        }
    }
}
