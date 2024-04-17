/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2570.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2570.NMAL2570BMsg;
import business.servlet.NMAL2570.constant.NMAL2570Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 *<pre>
 * Resource Search NMAL2570 CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/10   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/09/30   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/03/01   Fujitsu         W.Honda         Update          CSA-QC#4541
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 *</pre>
 */

public class NMAL2570CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2570BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL2570BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);

    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2570BMsg
     */
    public static void initButton(EZDCommonHandler handler, NMAL2570BMsg scrnMsg) {

        handler.setButtonEnabled(NMAL2570Constant.BTN_SEARCH[0], true);

        // QC#7781
        if (NMAL2570Constant.MULT_SEL_MODE_CD.equals(scrnMsg.xxModeCd_H1.getValue())) {
            if (scrnMsg.B.getValidCount() == scrnMsg.B.length()) {
                handler.setButtonEnabled(NMAL2570Constant.BTN_ADD_SEL[0], false);
            } else {
                handler.setButtonEnabled(NMAL2570Constant.BTN_ADD_SEL[0], true);
            }
            if (scrnMsg.B.getValidCount() == 0) {
                handler.setButtonEnabled(NMAL2570Constant.BTN_DEL_SEL[0], false);
            } else {
                handler.setButtonEnabled(NMAL2570Constant.BTN_DEL_SEL[0], true);
            }
        }
    }

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(NMAL2570Constant.BTN_CMN_BTN_CLEAR[0], NMAL2570Constant.BTN_CMN_BTN_CLEAR[1], NMAL2570Constant.BTN_CMN_BTN_CLEAR[2], 1, null);
        handler.setButtonProperties(NMAL2570Constant.BTN_CMN_BTN_RETURN[0], NMAL2570Constant.BTN_CMN_BTN_RETURN[1], NMAL2570Constant.BTN_CMN_BTN_RETURN[2], 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2570BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL2570BMsg scrnMsg) {
        // Del Start W.Honda 2016/03/01 CDB-QC#4541
        //setIniOrgStatDate(scrnMsg);
        // Del End W.Honda 2016/03/01 CDB-QC#4541
        controlScreenDetailFields(handler, scrnMsg);
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2570BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL2570BMsg scrnMsg) {

    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2570BMsg
     */
    // Del Start W.Honda 2016/03/01 CDB-QC#4541
//    private static void setIniOrgStatDate(NMAL2570BMsg scrnMsg) {
//
//        // Get Sales Date
//        final String slsDt = ZYPDateUtil.getSalesDate();
//
//        // Set Start Date
//        scrnMsg.effFromDt_H1.setValue(slsDt);
//    }
    // Del End W.Honda 2016/03/01 CDB-QC#4541

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL2570BMsg
     */
    public static void setInitParamForJobTtlPopup(NMAL2570BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.jobTtlCd_G1.clear();
        scrnMsg.jobTtlNm_G1.clear();

        // job title Code is set to sub screen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, NMAL2570Constant.TBL_NM_FOR_JOB_TTL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, NMAL2570Constant.TBL_CD_COL_NM_FOR_JOB_TTL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, NMAL2570Constant.TBL_NM_COL_NM_FOR_JOB_TTL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, NMAL2570Constant.TBL_SORT_COL_NM_FOR_JOB_TTL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, NMAL2570Constant.SCR_NM_FOR_JOB_TTL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, NMAL2570Constant.HDR_CD_LB_NM_FOR_JOB_TTL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, NMAL2570Constant.HDR_NM_LB_NM_FOR_JOB_TTL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, NMAL2570Constant.DTL_CD_LB_NM_FOR_JOB_TTL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, NMAL2570Constant.DTL_NM_LB_NM_FOR_JOB_TTL);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2570BMsg
     * @return Object[]
     */
    public static Object[] setParamForJobTtlPopup(NMAL2570BMsg scrnMsg) {

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P1;
        params[1] = scrnMsg.xxTblCdColNm_P1;
        params[2] = scrnMsg.xxTblNmColNm_P1;
        params[3] = scrnMsg.xxTblSortColNm_P1;
        params[4] = scrnMsg.xxScrNm_P1;
        params[5] = scrnMsg.xxHdrCdLbNm_P1;
        params[6] = scrnMsg.xxHdrNmLbNm_P1;
        params[7] = scrnMsg.xxDtlCdLbNm_P1;
        params[8] = scrnMsg.xxDtlNmLbNm_P1;
        params[9] = scrnMsg.jobTtlCd_G1;
        params[10] = scrnMsg.jobTtlNm_G1;

        return params;
    }
}
