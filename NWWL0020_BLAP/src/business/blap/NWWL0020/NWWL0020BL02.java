/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0020;

import static business.blap.NWWL0020.constant.NWWL0020Constant.NTFY_DIST_LIST_NM_LINK_NM;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0014E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0017E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0025I;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NZZM0002I;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TAB_NAME_ACTION_DETAIL;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TAB_NAME_HEADER;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TAB_NAME_SQL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWWL0020.common.NWWL0020CommonLogic;
import business.db.NTFY_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_FREQ_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NWWL0020BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWWL0020CMsg bizMsg = (NWWL0020CMsg) cMsg;
            NWWL0020SMsg glblMsg = (NWWL0020SMsg) sMsg;

            if ("NWWL0020Scrn00_Add_Line".equals(screenAplID)) {
                doProcess_NWWL0020Scrn00_Add_Line(bizMsg, glblMsg);

            } else if ("NWWL0020_INIT".equals(screenAplID)) {
                doProcess_NWWL0020_INIT(bizMsg, glblMsg);

            } else if ("NWWL0020Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWWL0020Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NWWL0020Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWWL0020Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWWL0020Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWWL0020Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWWL0020Scrn00_Del_Line".equals(screenAplID)) {
                doProcess_NWWL0020Scrn00_Del_Line(bizMsg, glblMsg);

            } else if ("NWWL0020Scrn00_OnChange_BizArea".equals(screenAplID)) {
                doProcess_NWWL0020Scrn00_OnChange_BizArea(bizMsg, glblMsg);

            } else if ("NWWL0020Scrn00_Run".equals(screenAplID)) {
                doProcess_NWWL0020Scrn00_Run(bizMsg, glblMsg);

            } else if ("NWWL0020Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWWL0020Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NWWL0020Scrn00_Validate".equals(screenAplID)) {
                doProcess_NWWL0020Scrn00_Validate(bizMsg, glblMsg);

            } else if ("NWWL0020Scrn00_ViewActionDetail".equals(screenAplID)) {
                doProcess_NWWL0020Scrn00_ViewActionDetail(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Add_Line Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0020Scrn00_Add_Line(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {
        if (NWWL0020CommonLogic.isContainsBlankActionList(bizMsg)) {
            bizMsg.setMessageInfo(NWWM0017E);
        } else {

            if (NWWL0020CommonLogic.setChangedWarning(bizMsg, glblMsg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
            } else {

                bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);

                NWWL0020CommonLogic.clearActionDetailData(bizMsg, glblMsg);

                bizMsg.ntfyActDtlPk.clear();
                bizMsg.xxRadioBtn_A0.setValue(bizMsg.A.getValidCount() - 1);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
            }
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0020_INIT(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        String ntfyHdrId = bizMsg.ntfyHdrId_H0.getValue();
        NWWL0020CommonLogic.clearNtfyData(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyHdrId_H0, ntfyHdrId);

        String glblCmpyCd = getGlobalCompanyCode();
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyDistListNmListTxt_LK, NTFY_DIST_LIST_NM_LINK_NM);

        NWWL0020CommonLogic.createPulldownList(bizMsg, glblMsg);

        if (ZYPCommonFunc.hasValue(bizMsg.ntfyHdrId_H0)) {
            NWWL0020CommonLogic.searchNotification(bizMsg, glblMsg);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
            NWWL0020CommonLogic.searchNotificationActionList(bizMsg, glblMsg);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyHdrActvFlg_H0, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyFreqTpCd_SL, NTFY_FREQ_TP.DAILY);
        }
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0020Scrn00_CMN_Clear(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {
        doProcess_NWWL0020_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0020Scrn00_CMN_Reset(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {
        doProcess_NWWL0020_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0020Scrn00_CMN_Submit(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        if (TAB_NAME_HEADER.equals(bizMsg.xxDplyTab.getValue()) || TAB_NAME_SQL.equals(bizMsg.xxDplyTab.getValue())) {

            NWWL0020CommonLogic.searchNotification(bizMsg, glblMsg);

        } else if (TAB_NAME_ACTION_DETAIL.equals(bizMsg.xxDplyTab.getValue())) {

            // Search Action
            NWWL0020CommonLogic.searchNotificationActionList(bizMsg, glblMsg);

            int selNum = 0;
            if (ZYPCommonFunc.hasValue(bizMsg.ntfyActDtlPk)) {
                BigDecimal ntfyActDtlPk = bizMsg.ntfyActDtlPk.getValue();
                for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).ntfyActDtlPk_A0.getValue()) && ntfyActDtlPk.compareTo(bizMsg.A.no(i).ntfyActDtlPk_A0.getValue()) == 0) {
                        selNum = i;
                        break;
                    }
                }
            }

            bizMsg.xxRadioBtn_A0.setValue(selNum);

            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDtlProtFlg.getValue())) {
                NWWL0020CommonLogic.searchNotificationActionDetail(bizMsg, glblMsg);
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
    }

    /**
     * Del_Line Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0020Scrn00_Del_Line(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {
        if (NWWL0020CommonLogic.setChangedWarning(bizMsg, glblMsg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        } else {

            List<Integer> selectedRows = new ArrayList<Integer>();
            int selectNum = bizMsg.xxRadioBtn_A0.getValueInt();
            selectedRows.add(selectNum);

            ZYPTableUtil.deleteRows(bizMsg.A, selectedRows);

            NWWL0020CommonLogic.clearActionDetailData(bizMsg, glblMsg);
            bizMsg.xxRadioBtn_A0.setValue(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * OnChange_BizArea Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0020Scrn00_OnChange_BizArea(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {
        NWWL0020CommonLogic.createNtfySubAreaPulldownList(bizMsg);
    }

    /**
     * Run Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0020Scrn00_Run(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        if (NWWL0020CommonLogic.checkProhibitedChar(bizMsg)) {

            if (NWWL0020CommonLogic.checkSqlStatement(bizMsg.xxNtfySqlTxt)) {

                final NTFY_HDRTMsg ntfyHdrTMsg = new NTFY_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyHdrId, bizMsg.ntfyHdrId_H0);
                ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyHdrNm, bizMsg.ntfyHdrNm_H0);
                ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyHdrDescTxt, bizMsg.ntfyHdrDescTxt_H0);

                final boolean isNormalEnd = NWWL0020Query.getInstance().createCsvFile(bizMsg.xxFileData, ntfyHdrTMsg, bizMsg.xxNtfySqlTxt.getValue());
                if (!isNormalEnd) {
                    bizMsg.setMessageInfo(NWWM0014E);
                    return;
                }

                bizMsg.setMessageInfo(NZZM0002I);
            }
        }
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0020Scrn00_TBLColumnSort(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {
            S21SortTarget sortTarget = new S21SortTarget(bizMsg.A, bizMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, bizMsg.A.getValidCount());
            bizMsg.xxRadioBtn_A0.setValue(0);
        }
    }

    /**
     * Validate Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0020Scrn00_Validate(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {
        if (NWWL0020CommonLogic.checkProhibitedChar(bizMsg)) {
            if (NWWL0020CommonLogic.checkSqlStatement(bizMsg.xxNtfySqlTxt)) {
                bizMsg.setMessageInfo(NWWM0025I);
            }
        }
    }

    /**
     * ViewActionDetail Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0020Scrn00_ViewActionDetail(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        if (NWWL0020CommonLogic.setChangedWarning(bizMsg, glblMsg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        } else {

            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyActDtlPk, bizMsg.ntfyActDtlPk_PR);

            NWWL0020CommonLogic.searchNotificationActionDetail(bizMsg, glblMsg);

            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
        }
    }
}
