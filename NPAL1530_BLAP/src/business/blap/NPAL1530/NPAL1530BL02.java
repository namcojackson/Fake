/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1530;

import static business.blap.NPAL1530.constant.NPAL1530Constant.EVENT_NM_NPAL1530_CMN_CLEAR;
import static business.blap.NPAL1530.constant.NPAL1530Constant.EVENT_NM_NPAL1530_INIT;
import static business.blap.NPAL1530.constant.NPAL1530Constant.EVENT_NM_NPAL1530_NWAL1130;
import static business.blap.NPAL1530.constant.NPAL1530Constant.EVENT_NM_NPAL1530_SET_SUB_WAREHOUSENAME;
import static business.blap.NPAL1530.constant.NPAL1530Constant.EVENT_NM_NPAL1530_SET_WAREHOUSENAME;
import static business.blap.NPAL1530.constant.NPAL1530Constant.NPAL1530_DMND_CTOFF_DAYS_AOT;
import static business.blap.NPAL1530.constant.NPAL1530Constant.NPAL1530_SPLY_CTOFF_DAYS_AOT;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1530.common.NPAL1530CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NPAL1530 Min-Max Planning Report Run Screen
 * Function Name : Search Business Process
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/23/2016   CITS            Keiichi Masaki  Create          N/A
 * 01/16/2018   CITS            T.Tokutomi      Update          QC#21879
 *</pre>
 */
public class NPAL1530BL02 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1530_INIT.equals(screenAplID) || EVENT_NM_NPAL1530_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NPAL1530_INIT((NPAL1530CMsg) cMsg, (NPAL1530SMsg) sMsg);
            } else if (EVENT_NM_NPAL1530_SET_WAREHOUSENAME.equals(screenAplID)) {
                doProcess_NPAL1530_SetWarehouseName((NPAL1530CMsg) cMsg, (NPAL1530SMsg) sMsg);
            } else if (EVENT_NM_NPAL1530_SET_SUB_WAREHOUSENAME.equals(screenAplID)) {
                doProcess_NPAL1530_SetSubWarehouseName((NPAL1530CMsg) cMsg, (NPAL1530SMsg) sMsg);
            } else if (EVENT_NM_NPAL1530_NWAL1130.equals(screenAplID)) {
                // QC#21879 Add Event.
                doProcess_NPAL1530_NWAL1130((NPAL1530CMsg) cMsg, (NPAL1530SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPAL1530CMsg
     * @param sMsg NPAL1530SMsg
     */
    private void doProcess_NPAL1530_INIT(NPAL1530CMsg cMsg, NPAL1530SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        // get NUM_CONST data
        ZYPEZDItemValueSetter.setValue(cMsg.dmndCtoffDaysAot, ZYPCodeDataUtil.getNumConstValue(NPAL1530_DMND_CTOFF_DAYS_AOT, cMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(cMsg.splyCtoffDaysAot, ZYPCodeDataUtil.getNumConstValue(NPAL1530_SPLY_CTOFF_DAYS_AOT, cMsg.glblCmpyCd.getValue()));
        // Planning Level list
        ZYPEZDItemValueSetter.setValue(cMsg.xxCmntTxt_CD.no(0), "01");
        ZYPEZDItemValueSetter.setValue(cMsg.xxCmntTxt_NM.no(0), "Plan Name");
        ZYPEZDItemValueSetter.setValue(cMsg.xxCmntTxt_CD.no(1), "02");
        ZYPEZDItemValueSetter.setValue(cMsg.xxCmntTxt_NM.no(1), "Sub-Warehouse");
    }

    /**
     * Set Warehouse Name
     * @param cMsg NPAL1530CMsg
     * @param sMsg NPAL1530SMsg
     */
    private void doProcess_NPAL1530_SetWarehouseName(NPAL1530CMsg cMsg, NPAL1530SMsg sMsg) {

        NPAL1530CommonLogic.setWarehouseName(cMsg, sMsg);

    }

    /**
     * Set Sub Warehouse Name
     * @param cMsg NPAL1530CMsg
     * @param sMsg NPAL1530SMsg
     */
    private void doProcess_NPAL1530_SetSubWarehouseName(NPAL1530CMsg cMsg, NPAL1530SMsg sMsg) {

        NPAL1530CommonLogic.setSubWarehouseName(cMsg, sMsg);

    }

    /**
     * Set Warehouse Name From pop up screen QC#21879 Add method.
     * @param cMsg NPAL1530CMsg
     * @param sMsg NPAL1530SMsg
     */
    private void doProcess_NPAL1530_NWAL1130(NPAL1530CMsg cMsg, NPAL1530SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
            NPAL1530CommonLogic.setWarehouseName(cMsg, sMsg);
        }
    }
}
