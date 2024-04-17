/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0050;

import static business.blap.NWWL0050.constant.NWWL0050Constant.BIZ_AREA_TP_TBL_NM;
import static business.blap.NWWL0050.constant.NWWL0050Constant.NZZM0000E;
import static business.blap.NWWL0050.constant.NWWL0050Constant.NZZM0001W;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWWL0050.common.NWWL0050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_DIST_QLFY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NWWL0050BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0050BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWWL0050CMsg bizMsg = (NWWL0050CMsg) cMsg;
            NWWL0050SMsg glblMsg = (NWWL0050SMsg) sMsg;

            if ("NWWL0050_INIT".equals(screenAplID)) {
                doProcess_NWWL0050_INIT(bizMsg, glblMsg);

            } else if ("NWWL0050Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWWL0050Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWWL0050Scrn00_Add_Line".equals(screenAplID)) {
                doProcess_NWWL0050Scrn00_Add_Line(bizMsg, glblMsg);

            } else if ("NWWL0050Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWWL0050Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWWL0050Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWWL0050Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NWWL0050Scrn00_OnChange_BizArea".equals(screenAplID)) {
                doProcess_NWWL0050Scrn00_OnChange_BizArea(bizMsg, glblMsg);

            } else if ("NWWL0050Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWWL0050Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0050Scrn00_CMN_Clear(NWWL0050CMsg bizMsg, NWWL0050SMsg glblMsg) {
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);

        doProcess_NWWL0050_INIT(bizMsg, glblMsg);

    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0050_INIT(NWWL0050CMsg bizMsg, NWWL0050SMsg glblMsg) {
        // init Search
        if (ZYPCommonFunc.hasValue(bizMsg.ntfyDistListId)) {
            search(bizMsg, glblMsg);

        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyDistListActvFlg, ZYPConstant.FLG_ON_Y);
        }

        ZYPCodeDataUtil.createPulldownList(BIZ_AREA_TP_TBL_NM, bizMsg.ntfyBizAreaTpCd_P, bizMsg.ntfyBizAreaTpDescTxt_P);
        NWWL0050CommonLogic.createNtfySubAreaPulldownList(bizMsg, getGlobalCompanyCode());
        ZYPCodeDataUtil.createPulldownList(NTFY_DIST_QLFY.class, bizMsg.ntfyDistQlfyCd_P, bizMsg.ntfyDistQlfyDescTxt_P);

    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0050Scrn00_CMN_Reset(NWWL0050CMsg bizMsg, NWWL0050SMsg glblMsg) {
        doProcess_NWWL0050_INIT(bizMsg, glblMsg);

    }

    /**
     * Add_Line Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0050Scrn00_Add_Line(NWWL0050CMsg bizMsg, NWWL0050SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).ntfyDistQlfyCd_A, NTFY_DIST_QLFY.EMAIL);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).ntfyDistListAsgActvFlg_A, ZYPConstant.FLG_ON_Y);
        bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0050Scrn00_CMN_Submit(NWWL0050CMsg bizMsg, NWWL0050SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * OnChange_BizArea Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0050Scrn00_OnChange_BizArea(NWWL0050CMsg bizMsg, NWWL0050SMsg glblMsg) {
        NWWL0050CommonLogic.createNtfySubAreaPulldownList(bizMsg, getGlobalCompanyCode());
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0050Scrn00_TBLColumnSort(NWWL0050CMsg bizMsg, NWWL0050SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(bizMsg.A, bizMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, bizMsg.A.getValidCount());

            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, BigDecimal.ZERO);

        } else if ("B".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(bizMsg.B, bizMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, bizMsg.B.getValidCount());

        }
    }

    private void search(NWWL0050CMsg bizMsg, NWWL0050SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);

        // seach Dist List
        S21SsmEZDResult ssmResult = NWWL0050Query.getInstance().getNtfyDistList(bizMsg);
        Map<String, Object> distList = (Map<String, Object>) ssmResult.getResultObject();

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        NWWL0050CommonLogic.setDistList(bizMsg, distList, glblMsg);

        // seach Dist List Asg
        S21SsmEZDResult ssmResultAsg = NWWL0050Query.getInstance().getNtfyDistListAsg(bizMsg);
        List<Map<String, Object>> distListAsgList = (List<Map<String, Object>>) ssmResultAsg.getResultObject();

        if (ssmResultAsg.isCodeNormal()) {
            NWWL0050CommonLogic.setDistListAsg(bizMsg, distListAsgList, glblMsg);
        }

        // seach Ntfy Hdr
        S21SsmEZDResult ssmResultNtfy = NWWL0050Query.getInstance().getNtfyHdr(bizMsg);
        List<Map<String, String>> ntfyHdrList = (List<Map<String, String>>) ssmResultNtfy.getResultObject();

        if (ssmResultNtfy.isCodeNormal()) {
            if (ssmResultNtfy.getQueryResultCount() > bizMsg.B.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                bizMsg.B.setValidCount(bizMsg.B.length());
            } else {
                bizMsg.B.setValidCount(ssmResultNtfy.getQueryResultCount());
            }

            NWWL0050CommonLogic.setNtfyHdr(bizMsg, ntfyHdrList, glblMsg);
        }

    }

}
