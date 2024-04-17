/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NLBL3130;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLBL3130.common.NLBL3130CommonLogic;
import business.blap.NLBL3130.constant.NLBL3130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DELY_TRNSP_OPT;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Delivery Instruction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2015   CITS            M.Ito           Create          N/A
 * 04/13/2016   CSAI            Y.Imazu         Update          QC#5868
 * 01/12/2018   CITS            T.Tokutomi      Update          QC#18460_Sol#087
 * 01/24/2018   CITS            K.Ogino         Update          QC#23044
 *</pre>
 */
public class NLBL3130BL02 extends S21BusinessHandler implements NLBL3130Constant {

    /**
     * Screen Application ID
     */
    private String screenAplID;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLBL3130_INIT.equals(screenAplID)) {
                doProcess_NLBL3130_INIT((NLBL3130CMsg) cMsg);

            } else if (EVENT_NM_NLBL3130SCRN00_CMN_RESET.equals(screenAplID)) {
                doProcess_NLBL3130Scrn00_Search((NLBL3130CMsg) cMsg);

            } else if (EVENT_NM_NLBL3130SCRN00_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NLBL3130Scrn00_Clear((NLBL3130CMsg) cMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3130_INIT
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLBL3130_INIT(NLBL3130CMsg cMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd_G1, getGlobalCompanyCode());

        // QC#18460_Sol#087 create pull_down.
        createPulldown(cMsg);

        // QC#18460_Sol#087 set time zone.
        setTimeZone(cMsg);

        // QC#23044
        if (ZYPCommonFunc.hasValue(cMsg.soNum_H1) || ZYPCommonFunc.hasValue(cMsg.rwsNum_H1)) {

            doProcess_NLBL3130Scrn00_Search(cMsg);

        } else {

            NLBL3130CommonLogic.clearMsg(cMsg);
            cMsg.setMessageInfo(NLBM1063E);
        }
    }

    /**
     * createPulldown
     * QC#18460_Sol#087 Add method.
     * @param cMsg NLBL3130CMsg
     */
    private void createPulldown(NLBL3130CMsg cMsg) {
        // set AMPM
        ZYPEZDItemValueSetter.setValue(cMsg.xxTmFrameTxt_CD.no(0), TIME_AM);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTmFrameTxt_CD.no(1), TIME_PM);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTmFrameTxt_VL.no(0), TIME_AM);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTmFrameTxt_VL.no(1), TIME_PM);
        // Transport Option
        ZYPCodeDataUtil.createPulldownList(DELY_TRNSP_OPT.class, cMsg.delyTrnspOptCd_CD, cMsg.delyTrnspOptDescTxt_VL);
    }

    /**
     * setTimeZone
     * QC#18460_Sol#087 Add method.
     * @param cMsg NLBL3130CMsg
     */
    private void setTimeZone(NLBL3130CMsg cMsg) {

        // QC#23044
        if (ZYPCommonFunc.hasValue(cMsg.soNum_H1) || ZYPCommonFunc.hasValue(cMsg.rwsNum_H1)) {
            S21SsmEZDResult result = NLBL3130Query.getInstance().getShipToPost(cMsg);

            if (result.isCodeNormal()) {
                Map resultMap = (Map) result.getResultObject();
                String ctryCd = (String) resultMap.get("CTRY_CD");
                String postCd = (String) resultMap.get("POST_CD");

                ZYPEZDItemValueSetter.setValue(cMsg.tmZoneCd_H1, NLBL3130CommonLogic.getTimeZoneCd(ctryCd, postCd));
                ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_S1, ctryCd);
                ZYPEZDItemValueSetter.setValue(cMsg.postCd_S1, postCd);
            } else {
                // System time zone.
                ZYPEZDItemValueSetter.setValue(cMsg.tmZoneCd_H1, NLBL3130CommonLogic.getSystemTimeZoneCd());
            }
        } else {
            // System time zone.
            ZYPEZDItemValueSetter.setValue(cMsg.tmZoneCd_H1, NLBL3130CommonLogic.getSystemTimeZoneCd());
        }
    }

    /**
     * doProcess_NLBL3130Scrn00_Search
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLBL3130Scrn00_Search(NLBL3130CMsg cMsg) {

        // Search Shipping Order Delivery Instruction
        S21SsmEZDResult ssmResult = NLBL3130Query.getInstance().getDelyInstnInfo(cMsg, "getDelyInstnInfo");

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> delyInstnInfoMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

            if (delyInstnInfoMapList != null && !delyInstnInfoMapList.isEmpty()) {

                NLBL3130CommonLogic.setScrnItem(cMsg, delyInstnInfoMapList.get(0));
                cMsg.setMessageInfo(NZZM0002I);
                return;
            }
        }

        // Search CPO Configuration Information
        ssmResult = NLBL3130Query.getInstance().getDelyInstnInfo(cMsg, "getDelyInstnConfigLvlInfo");

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> delyInstnInfoMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

            if (delyInstnInfoMapList != null && !delyInstnInfoMapList.isEmpty()) {

                if (delyInstnInfoMapList.size() == 1) {

                    NLBL3130CommonLogic.setScrnItem(cMsg, delyInstnInfoMapList.get(0));
                    cMsg.setMessageInfo(NZZM0002I);
                    return;

                } else {

                    // Search SO Information
                    ssmResult = NLBL3130Query.getInstance().getDelyInstnInfo(cMsg, "getDelyInstnSOHeaderLvlInfo");

                    if (ssmResult.isCodeNormal()) {

                        delyInstnInfoMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

                        if (delyInstnInfoMapList != null && !delyInstnInfoMapList.isEmpty()) {

                            NLBL3130CommonLogic.setScrnItem(cMsg, delyInstnInfoMapList.get(0));
                            cMsg.setMessageInfo(NLBM2442W);
                            return;
                        }
                    }
                }
            }
        }

        // Search CPO Information
        ssmResult = NLBL3130Query.getInstance().getDelyInstnInfo(cMsg, "getDelyInstnCPOHeaderLvlInfo");

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> delyInstnInfoMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

            if (delyInstnInfoMapList != null && !delyInstnInfoMapList.isEmpty()) {

                if (delyInstnInfoMapList.size() == 1) {

                    NLBL3130CommonLogic.setScrnItem(cMsg, delyInstnInfoMapList.get(0));
                    cMsg.setMessageInfo(NZZM0002I);
                    return;

                } else {

                    // Search SO Information
                    ssmResult = NLBL3130Query.getInstance().getDelyInstnInfo(cMsg, "getDelyInstnSOHeaderLvlInfo");

                    if (ssmResult.isCodeNormal()) {

                        delyInstnInfoMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

                        if (delyInstnInfoMapList != null && !delyInstnInfoMapList.isEmpty()) {

                            NLBL3130CommonLogic.setScrnItem(cMsg, delyInstnInfoMapList.get(0));
                            cMsg.setMessageInfo(NLBM2442W);
                            return;
                        }
                    }
                }
            }
        }

        // Search SO Information
        ssmResult = NLBL3130Query.getInstance().getDelyInstnInfo(cMsg, "getDelyInstnSOHeaderLvlInfo");

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> delyInstnInfoMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

            if (delyInstnInfoMapList != null && !delyInstnInfoMapList.isEmpty()) {

                NLBL3130CommonLogic.setScrnItem(cMsg, delyInstnInfoMapList.get(0));
                cMsg.setMessageInfo(NZZM0002I);
                return;
            }
        }

        cMsg.setMessageInfo(NZZM0000E);
    }

    /**
     * doProcess_NLBL3130Scrn00_Clear
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLBL3130Scrn00_Clear(NLBL3130CMsg cMsg) {

        NLBL3130CommonLogic.clearMsg(cMsg);
    }
}
