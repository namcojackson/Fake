/**
 * <Pre>
 * 
 * Copyright (c) 2013 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NLGL0050;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_UPCHG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FUEL_UPCHG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import business.blap.NLGL0050.common.NLGL0050CommonLogic;
import business.blap.NLGL0050.constant.NLGL0050Constant;

/**
 * <pre>
 * Ship Via Mapping from WMS to HOST
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            Y.Miyauchi      Create          MW Replace Initial
 * 2018/03/09   CITS            M.Naito         Update          QC#24696
 *</pre>
 */
public class NLGL0050BL02 extends S21BusinessHandler implements NLGL0050Constant {

    /**
     * this is a method of the execution after the SV
     * enent(setRequestData).
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLGL0050_INIT.equals(screenAplID)) {
                // Initalize Logic
                doProcess_NLGL0050_INIT((NLGL0050CMsg) cMsg, (NLGL0050SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_LIST_TAB.equals(screenAplID)) {
                // Carr code list tab Logic
                doProcess_NLGL0050Scrn00_OnClick_CodeListTab((NLGL0050CMsg) cMsg, (NLGL0050SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_EDIT_TAB.equals(screenAplID)) {
                // Carr code edit tab Logic
                doProcess_NLGL0050Scrn00_OnClick_CodeEditTab((NLGL0050CMsg) cMsg, (NLGL0050SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_SEARCH.equals(screenAplID)) {
                // Search Logic
                doProcess_NLGL0050Scrn00_OnClick_Search((NLGL0050CMsg) cMsg, (NLGL0050SMsg) sMsg);
            } else if (EVENT_NM_PAGE_PRE.equals(screenAplID)) {
                // NLGL0050Scrn00_PagePrev
                doProcess_NLGL0050Scrn00_PagePrev((NLGL0050CMsg) cMsg, (NLGL0050SMsg) sMsg);
            } else if (EVENT_NM_PAGE_NEXT.equals(screenAplID)) {
                // NLGL0050Scrn00_PageNext
                doProcess_NLGL0050Scrn00_PageNext((NLGL0050CMsg) cMsg, (NLGL0050SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_NEW.equals(screenAplID)) {
                // NLGL0050Scrn00_PageNext
                doProcess_NLGL0050Scrn00_OnClick_New((NLGL0050CMsg) cMsg, (NLGL0050SMsg) sMsg);
            } else if (EVENT_NM_CMN_RESET.equals(screenAplID)) {
                // same initialize
                doProcess_NLGL0050Scrn00_CMN_Reset((NLGL0050CMsg) cMsg, (NLGL0050SMsg) sMsg);
            } else if (EVENT_NM_CMN_CLEAR.equals(screenAplID)) {
                // no process
            } else if (EVENT_NM_CMN_SUBMIT.equals(screenAplID)) {
                // no process
            } else if (EVENT_NM_CMN_DELTE.equals(screenAplID)) {
                doProcess_NLGL0050Scrn00_OnClick_CodeListTab((NLGL0050CMsg) cMsg, (NLGL0050SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[INIT] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0050_INIT(NLGL0050CMsg cMsg, NLGL0050SMsg sMsg) {

        NLGL0050CMsg bizMsg = (NLGL0050CMsg) cMsg;

        NLGL0050CommonLogic.setPullDownList_HostSystem(bizMsg);
        NLGL0050CommonLogic.setPullDownList_TransportMode(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.wmsOrgHostId_B1, bizMsg.wmsOrgHostId_P1);
        ZYPEZDItemValueSetter.setValue(bizMsg.wmsTrnspTpCd_B1, bizMsg.wmsTrnspTpCd_P1);
        // START 2018/03/09 M.Naito [QC#24696,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.wmsCarrCd_B1, bizMsg.wmsCarrCd_T1);
        // END 2018/03/09 M.Naito [QC#24696,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.carrScacCd_B1, bizMsg.carrScacCd_T1);

    }

    /**
     * The method explanation: It is a method of the execution when
     * the carrier code date is searched.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0050Scrn00_OnClick_CodeListTab(NLGL0050CMsg bizMsg, NLGL0050SMsg globalMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        NLGL0050CommonLogic.setSearchItemsOnEvent(bizMsg);
        NLGL0050CommonLogic.getCarrCdList(bizMsg, globalMsg);

    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Search Click] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0050Scrn00_OnClick_Search(NLGL0050CMsg bizMsg, NLGL0050SMsg globalMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.wmsOrgHostId_B1, bizMsg.wmsOrgHostId_P1);
        ZYPEZDItemValueSetter.setValue(bizMsg.wmsTrnspTpCd_B1, bizMsg.wmsTrnspTpCd_P1);
        // START 2018/03/09 M.Naito [QC#24696,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.wmsCarrCd_B1, bizMsg.wmsCarrCd_T1);
        // END 2018/03/09 M.Naito [QC#24696,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.carrScacCd_B1, bizMsg.carrScacCd_T1);
        NLGL0050CommonLogic.getCarrCdList(bizMsg, globalMsg);

    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[EDIT TAB Click] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0050Scrn00_OnClick_CodeEditTab(NLGL0050CMsg bizMsg, NLGL0050SMsg globalMsg) {

        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, FIELD_NAME_CHKBOX_A1, ZYPConstant.CHKBOX_ON_Y);

        if (outGetSelectedRows.isEmpty()) {
            bizMsg.setMessageInfo(NLGM0036E);
            return;
        }

        if (outGetSelectedRows.size() > 1) {
            bizMsg.setMessageInfo(NLGM0035E);
            return;
        }

        NLGL0050CommonLogic.setSearchItemsOnEvent(bizMsg);
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A1.getValue())) {

                Map<String, Object> ssmParam = new HashMap<String, Object>();

                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
                ssmParam.put(DB_PARAM_WMS_CARR_CD, bizMsg.A.no(i).wmsCarrCd_A1.getValue());
                ssmParam.put(DB_PARAM_WMS_ORG_HOST_ID, bizMsg.wmsOrgHostId_P1.getValue());

                NLGL0050CommonLogic.getCarrCdEdit(bizMsg, globalMsg, ssmParam);
                break;
            }
        }

        // create edit transportation mode pull down
        NLGL0050CommonLogic.setPullDownList_TransportModeEdit(bizMsg);
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, bizMsg.shpgSvcLvlCd_T2, bizMsg.xxEdtCdNm_T5, PULL_DOWN_DELIMITER);
        ZYPCodeDataUtil.createPulldownList(FUEL_UPCHG_TP.class, bizMsg.fuelUpchgTpCd_T2, bizMsg.xxEdtCdNm_T3, PULL_DOWN_DELIMITER);
        ZYPCodeDataUtil.createPulldownList(ADDL_UPCHG_TP.class, bizMsg.addlUpchgTpCd_T2, bizMsg.xxEdtCdNm_T4, PULL_DOWN_DELIMITER);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[PAGE PREV Click] is generated. PagePrev Event
     * @param cMsg NLGL0050CMsg
     * @param sMsg NLGL0050SMsg
     */
    private void doProcess_NLGL0050Scrn00_PagePrev(NLGL0050CMsg cMsg, NLGL0050SMsg sMsg) {

        // set values to items of pageNation for previous page
        // pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_A1.setValue(cMsg.xxPageShowFromNum_A1.getValueInt() - cMsg.A.length());
        cMsg.xxPageShowToNum_A1.clear();
        NLGL0050CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[PAGE NEXT Click] is generated. PageNext Event
     * @param cMsg NLGL0050CMsg
     * @param sMsg NLGL0050SMsg
     */
    private void doProcess_NLGL0050Scrn00_PageNext(NLGL0050CMsg cMsg, NLGL0050SMsg sMsg) {

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_A1.setValue(cMsg.xxPageShowToNum_A1.getValueInt() + 1);
        cMsg.xxPageShowToNum_A1.clear();
        NLGL0050CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[INSERT ROW Click] is generated. PageNext Event
     * @param cMsg NLGL0050CMsg
     * @param sMsg NLGL0050SMsg
     */
    private void doProcess_NLGL0050Scrn00_OnClick_New(NLGL0050CMsg bizMsg, NLGL0050SMsg globalMsg) {

        globalMsg.wmsCarrCd_T2.clear();

        // set pull down of Transport Mode
        NLGL0050CommonLogic.setPullDownList_TransportModeEdit(bizMsg);
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, bizMsg.shpgSvcLvlCd_T2, bizMsg.xxEdtCdNm_T5, PULL_DOWN_DELIMITER);
        ZYPCodeDataUtil.createPulldownList(FUEL_UPCHG_TP.class, bizMsg.fuelUpchgTpCd_T2, bizMsg.xxEdtCdNm_T3, PULL_DOWN_DELIMITER);
        ZYPCodeDataUtil.createPulldownList(ADDL_UPCHG_TP.class, bizMsg.addlUpchgTpCd_T2, bizMsg.xxEdtCdNm_T4, PULL_DOWN_DELIMITER);
        NLGL0050CommonLogic.setSearchItemsOnEvent(bizMsg);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[RESET] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0050Scrn00_CMN_Reset(NLGL0050CMsg bizMsg, NLGL0050SMsg globalMsg) {

        if (TAB_ID_LIST.equals(bizMsg.xxDplyTab.getValue())) {
            ZYPTableUtil.clear(bizMsg.A);
            ZYPTableUtil.clear(globalMsg.A);

            NLGL0050CommonLogic.setPullDownList_HostSystem(bizMsg);
            NLGL0050CommonLogic.setPullDownList_TransportMode(bizMsg);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
            ssmParam.put(DB_PARAM_WMS_CARR_CD, bizMsg.wmsCarrCd_T2.getValue());
            ssmParam.put(DB_PARAM_WMS_ORG_HOST_ID, bizMsg.wmsOrgHostId_P1.getValue());

            NLGL0050CommonLogic.getCarrCdEdit(bizMsg, globalMsg, ssmParam);
        }
    }
}
