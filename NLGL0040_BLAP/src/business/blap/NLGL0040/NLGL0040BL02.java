/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLGL0040;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLGL0040.common.NLGL0040CommonLogic;
import business.blap.NLGL0040.constant.NLGL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MD_BREAK_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Ship Via Mapping from HOST to WMS
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            N.Sekine          Create             MW Replace Initial
 *</pre>
 */
public class NLGL0040BL02 extends S21BusinessHandler implements NLGL0040Constant {
    /**
     * this is a method of the execution after the SV enent(setRequestData).
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLGL0040SCRN00_INIT.equals(screenAplID)) {
                doProcess_NLGL0040_INIT((NLGL0040CMsg) cMsg, (NLGL0040SMsg) sMsg);
            } else if (EVENT_NM_NLGL0040SCRN00_ONCLICK_SEARCH.equals(screenAplID)) {
                doProcess_NLGL0040_SEARCH((NLGL0040CMsg) cMsg, (NLGL0040SMsg) sMsg);
            } else if (EVENT_NM_NLGL0040SCRN00_ONCLICK_TAB_LIST.equals(screenAplID)) {
                doProcess_NLGL0040_Onclick_Ship_Via_List_Tab((NLGL0040CMsg) cMsg, (NLGL0040SMsg) sMsg);
            } else if (EVENT_NM_NLGL0040SCRN00_ONCLICK_TAB_EDIT.equals(screenAplID)) {
                doProcess_NLGL0040Scrn00_OnClick_Ship_Via_Edit_Tab((NLGL0040CMsg) cMsg, (NLGL0040SMsg) sMsg);
            } else if (EVENT_NM_NLGL0040SCRN00_ONCLICK_NEW.equals(screenAplID)) {
                doProcess_NLGL0040_New((NLGL0040CMsg) cMsg, (NLGL0040SMsg) sMsg);
            } else if (EVENT_NM_NLGL0040SCRN00_PAGEPREV.equals(screenAplID)) {
                doProcess_NLGL0040Scrn00_CMN_Prev((NLGL0040CMsg) cMsg, (NLGL0040SMsg) sMsg);
            } else if (EVENT_NM_NLGL0040SCRN00_PAGENEXT.equals(screenAplID)) {
                doProcess_NLGL0040Scrn00_CMN_Next((NLGL0040CMsg) cMsg, (NLGL0040SMsg) sMsg);
            } else if (EVENT_NM_NLGL0040SCRN00_CMN_DELETE.equals(screenAplID)) {
                doProcess_NLGL0040_Onclick_Ship_Via_List_Tab((NLGL0040CMsg) cMsg, (NLGL0040SMsg) sMsg);
            } else if (EVENT_NM_NLGL0040SCRN00_CMN_RESET.equals(screenAplID)) {
                doProcess_NLGL0040Scrn00_CMN_Reset((NLGL0040CMsg) cMsg, (NLGL0040SMsg) sMsg);
            } else if (EVENT_NM_NLGL0040SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                ;// Do Nothing.
            } else if (EVENT_NM_NLGL0040SCRN00_CMN_CLEAR.equals(screenAplID)) {
                ;// Do Nothing.
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * this is a method of the execution when the event[INIT] is
     * generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0040_INIT(NLGL0040CMsg cMsg, NLGL0040SMsg sMsg) {

        NLGL0040CMsg bizMsg = (NLGL0040CMsg) cMsg;
        createWHPulldownList(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.whCd_B1, bizMsg.whCd_H1);
    }

    private void createWHPulldownList(NLGL0040CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        S21SsmEZDResult dsWH = NLGL0040Query.getInstance().getWHPullDownList(bizMsg, ssmParam);

        if (dsWH.isCodeNormal()) {
            List<Map> dsPoTpOutList = (List<Map>) dsWH.getResultObject();
            bizMsg.whCd_L1.clear();
            bizMsg.xxEdtCdNm_L1.clear();
            int cntdel = 0;

            for (int i = 0; i < dsPoTpOutList.size(); i++) {
                Map pullDownData = dsPoTpOutList.get(i);

                if ((pullDownData.get(DB_WH_CD)).equals(WH_ALL_VALUE)) {
                    cntdel = cntdel + 1;
                    continue;
                }
                bizMsg.whCd_L1.no(i - cntdel).setValue((String) pullDownData.get(DB_WH_CD));
                bizMsg.xxEdtCdNm_L1.no(i - cntdel).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_WH_CD), LIST_BOX_DELIMITER, (String) pullDownData.get(DB_WMS_DESC_NM)));
            }
        }
    }

    /**
     * It is a method of the execution when the event[SEARCH] is
     * generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0040_SEARCH(NLGL0040CMsg bizMsg, NLGL0040SMsg globalMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        NLGL0040CommonLogic.getShipViaList(bizMsg, globalMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.whCd_B1, bizMsg.whCd_H1);
    }

    /**
     * It is a method of the execution when the event[LIST TAB Click] is
     * generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0040_Onclick_Ship_Via_List_Tab(NLGL0040CMsg bizMsg, NLGL0040SMsg globalMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.whCd_H1, bizMsg.whCd_B1);
        NLGL0040CommonLogic.getShipViaList(bizMsg, globalMsg);
    }

    /**
     * It is a method of the execution when the event[EDIT TAB Click]
     * is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0040Scrn00_OnClick_Ship_Via_Edit_Tab(NLGL0040CMsg bizMsg, NLGL0040SMsg globalMsg) {

        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, FIELD_NAME_XXCHKBOX_D1, ZYPConstant.CHKBOX_ON_Y);

        if (outGetSelectedRows.isEmpty()) {
            bizMsg.setMessageInfo(NLGM0036E);
            return;
        }

        if (outGetSelectedRows.size() > 1) {
            bizMsg.setMessageInfo(NLGM0035E);
            return;
        }

        ZYPCodeDataUtil.createPulldownList(MD_BREAK_TP.class, bizMsg.mdBreakTpCd_D2, bizMsg.xxEdtCdNm_D2, LIST_BOX_DELIMITER);
        ZYPEZDItemValueSetter.setValue(bizMsg.whCd_H1, bizMsg.whCd_B1);
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_D1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
                ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_H1.getValue());
                ssmParam.put(DB_PARAM_WMS_SHIP_VIA_TP_CD, bizMsg.A.no(i).wmsShipViaTpCd_D1.getValue());
                NLGL0040CommonLogic.getShipViaEdit(bizMsg, globalMsg, ssmParam);
            }
        }
    }

    /**
     * It is a method of the execution when the event[NEW] is
     * generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0040_New(NLGL0040CMsg bizMsg, NLGL0040SMsg globalMsg) {

        globalMsg.wmsShipViaTpCd_D2.clear();
        ZYPCodeDataUtil.createPulldownList(MD_BREAK_TP.class, bizMsg.mdBreakTpCd_D2, bizMsg.xxEdtCdNm_D2, LIST_BOX_DELIMITER);
        ZYPEZDItemValueSetter.setValue(globalMsg.whCd_H1, bizMsg.whCd_B1);
    }

    /**
     * PagePrev Event
     * @param cMsg NLGL0040CMsg
     * @param sMsg NLGL0040SMsg
     */
    private void doProcess_NLGL0040Scrn00_CMN_Prev(NLGL0040CMsg cMsg, NLGL0040SMsg sMsg) {

        // set values to items of pageNation for previous page
        // pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_D1.setValue(cMsg.xxPageShowFromNum_D1.getValueInt() - cMsg.A.length());
        cMsg.xxPageShowToNum_D1.clear();
        NLGL0040CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * PageNext Event
     * @param cMsg NLGL0040CMsg
     * @param sMsg NLGL0040SMsg
     */
    private void doProcess_NLGL0040Scrn00_CMN_Next(NLGL0040CMsg cMsg, NLGL0040SMsg sMsg) {

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_D1.setValue(cMsg.xxPageShowToNum_D1.getValueInt() + 1);
        cMsg.xxPageShowToNum_D1.clear();
        NLGL0040CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * It is a method of the execution when the event[RESET] is
     * generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0040Scrn00_CMN_Reset(NLGL0040CMsg bizMsg, NLGL0040SMsg globalMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_H1.getValue());
        ssmParam.put(DB_PARAM_WMS_SHIP_VIA_TP_CD, bizMsg.wmsShipViaTpCd_D2.getValue());
        NLGL0040CommonLogic.getShipViaEdit(bizMsg, globalMsg, ssmParam);
    }
}
