/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1520;

import static business.blap.NWAL1520.constant.NWAL1520Constant.NWAM0006I;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NWAM0007W;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NZZM0000E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1520.common.NWAL1520CommonLogic;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1520BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         A.Suda          Create          N/A
 * 2016/02/16   Fujitsu         M.Suzuki        Update          S21_NA#3832
 * 2016/04/26   Fujitsu         M.Yamada        Update          S21_NA#7534
 * 2016/04/27   Fujitsu         M.Yamada        Update          S21_NA#7159
 * 2017/08/22   Fujitsu         H.Sugawara      Update          QC#19915
 *</pre>
 */
public class NWAL1520BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1520CMsg bizMsg = (NWAL1520CMsg) cMsg;
            NWAL1520SMsg glblMsg = (NWAL1520SMsg) sMsg;

            if ("NWAL1520_INIT".equals(screenAplID)) {
                doProcess_NWAL1520_INIT(bizMsg, glblMsg);

            } else if ("NWAL1520Scrn00_Apply_Hold".equals(screenAplID)) {
                doProcess_NWAL1520Scrn00_Apply_Hold(bizMsg, glblMsg);

            } else if ("NWAL1520Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL1520Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWAL1520Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1520Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NWAL1520Scrn00_Release_Hold".equals(screenAplID)) {
                doProcess_NWAL1520Scrn00_Release_Hold(bizMsg, glblMsg);

            } else if ("NWAL1520Scrn00_Search_Hold".equals(screenAplID)) {
                doProcess_NWAL1520Scrn00_Search_Hold(bizMsg, glblMsg);

            } else if ("NWAL1520Scrn00_Select_All".equals(screenAplID)) {
                doProcess_NWAL1520Scrn00_Select_All(bizMsg, glblMsg);

            } else if ("NWAL1520Scrn00_Un_Select_All".equals(screenAplID)) {
                doProcess_NWAL1520Scrn00_Un_Select_All(bizMsg, glblMsg);

            } else if ("NWAL1520_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL1520_NWAL1130(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1520_INIT(NWAL1520CMsg bizMsg, NWAL1520SMsg glblMsg) {

        NWAL1520CommonLogic.clearErrorInfo(bizMsg, glblMsg);

        S21SsmEZDResult ssmResult = NWAL1520Query.getInstance().getCategoryTypeList();
        if (ssmResult.isCodeNotFound()) {
            // Illegal case.
            return;
        }

        List resultList = (List) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size(); i++) {
            Map map = (Map) resultList.get(i);
            bizMsg.configCatgCd_L1.no(i).setValue((String) map.get("CONFIG_CATG_CD"));
            bizMsg.configCatgCd_L2.no(i).setValue((String) map.get("CONFIG_CATG_CD"));
            bizMsg.configCatgDescTxt_L1.no(i).setValue((String) map.get("CONFIG_CATG_DESC_TXT"));
            bizMsg.configCatgDescTxt_L2.no(i).setValue((String) map.get("CONFIG_CATG_DESC_TXT"));
        }

        if (hasValue(bizMsg.cpoOrdNum_V)) {
            setValue(bizMsg.xxRadioBtn_V, BigDecimal.ONE);
            doProcess_NWAL1520Scrn00_Search_Hold(bizMsg, glblMsg);
        }

    }


    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1520Scrn00_CMN_Reset(NWAL1520CMsg bizMsg, NWAL1520SMsg glblMsg) {

        NWAL1520CommonLogic.initMsg(bizMsg, glblMsg);
        NWAL1520CommonLogic.clearErrorInfo(bizMsg, glblMsg);
        NWAL1520CommonLogic.initTblMsg(bizMsg, glblMsg);
        setValue(bizMsg.xxRadioBtn_V, BigDecimal.ONE);

        setValue(bizMsg.cpoOrdNum_V,    bizMsg.cpoOrdNum_BK);
        setValue(bizMsg.condSqlTxt_V,   bizMsg.condSqlTxt_BK);
        setValue(bizMsg.configCatgCd_V, bizMsg.configCatgCd_BK);
        setValue(bizMsg.cpoOrdNum_A,    bizMsg.cpoOrdNum_BK);
        setValue(bizMsg.condSqlTxt_A,   bizMsg.condSqlTxt_BK);
        setValue(bizMsg.configCatgCd_A, bizMsg.configCatgCd_BK);

        doProcess_NWAL1520_INIT(bizMsg, glblMsg);

    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1520Scrn00_CMN_Clear(NWAL1520CMsg bizMsg, NWAL1520SMsg glblMsg) {

        NWAL1520CommonLogic.initMsg(bizMsg, glblMsg);
        setValue(bizMsg.xxRadioBtn_V, BigDecimal.ONE);

        doProcess_NWAL1520_INIT(bizMsg, glblMsg);

    }

    /**
     * Release_Hold Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1520Scrn00_Release_Hold(NWAL1520CMsg bizMsg, NWAL1520SMsg glblMsg) {
        doProcess_NWAL1520Scrn00_Search_Hold(bizMsg, glblMsg);
    }

    /**
     * Search_Hold Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1520Scrn00_Search_Hold(NWAL1520CMsg bizMsg, NWAL1520SMsg glblMsg) {

        NWAL1520CommonLogic.clearErrorInfo(bizMsg, glblMsg);
        NWAL1520CommonLogic.initTblMsg(bizMsg, glblMsg);

        S21SsmEZDResult ssmResult = null;
        List resultList = null;
        bizMsg.hldRsnCd_V.clear();
        bizMsg.hldApplyRsnCd_V.clear();

        // Hold Name
        if (hasValue(bizMsg.hldRsnDescTxt_V)) {
            ssmResult = NWAL1520Query.getInstance().getHldReasonList(bizMsg.hldRsnDescTxt_V.getValue());
            if (ssmResult.isCodeNotFound()) {
                bizMsg.hldRsnDescTxt_V.setErrorInfo(1, NZZM0000E);
                return;
            }
            resultList = (List) ssmResult.getResultObject();
            if (resultList.size() > 1) {
                bizMsg.xxErrFlg_HN.setValue(FLG_ON_Y);
                return;

            } else if (resultList.size() == 1) {
                Map map = (Map) resultList.get(0);
                setValue(bizMsg.hldRsnCd_V, (String) map.get("HLD_RSN_CD"));
            }
        }

        // Hold Reason
        if (hasValue(bizMsg.hldApplyRsnDescTxt_V)) {
            ssmResult = NWAL1520Query.getInstance().getHldApplyReasonList(bizMsg.hldApplyRsnDescTxt_V.getValue());
            if (ssmResult.isCodeNotFound()) {
                bizMsg.hldApplyRsnDescTxt_V.setErrorInfo(1, NZZM0000E);
                return;
            }
            resultList = (List) ssmResult.getResultObject();
            if (resultList.size() > 1) {
                bizMsg.xxErrFlg_HR.setValue(FLG_ON_Y);
                return;
            } else if (resultList.size() == 1) {
                Map map = (Map) resultList.get(0);
                setValue(bizMsg.hldApplyRsnCd_V, (String) map.get("HLD_APPLY_RSN_CD"));
            }
        }

        // Hold Info
        ssmResult = NWAL1520Query.getInstance().getHldList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NWAM0006I);
            return;
        }

//        if (ssmResult.getQueryResultCount() > bizMsg.L.length()) {
//            bizMsg.setMessageInfo(NWAM0007W);
//            // 2016/02/19 S21_NA#3832 Mod Start ------------
//            //bizMsg.L.setValidCount(bizMsg.L.length() - 1);
//            bizMsg.L.setValidCount(bizMsg.L.length());
//            // 2016/02/19 S21_NA#3832 Mod Start ------------
//        } else {
//            bizMsg.L.setValidCount(ssmResult.getQueryResultCount());
//        }

        Map<String, Integer> keyMap = new HashMap<String, Integer>();

        List hldInfoList = (List) ssmResult.getResultObject();

        int ixL = 0;
        for (int i = 0; i < hldInfoList.size(); i++) {
//            // 2016/02/19 S21_NA#3832 Add Start ------------
//            if (i == bizMsg.L.length()) {
//                break;
//            }
//            // 2016/02/19 S21_NA#3832 Add End --------------
            Map map = (Map) hldInfoList.get(i);

            String relFuncTpCd = (String) map.get("REL_FUNC_TP_CD");
            boolean isViewOnlyFunc = NWAL1520CommonLogic.isViewOnlyFunc(bizMsg, relFuncTpCd);
            boolean isReleaseFunc = NWAL1520CommonLogic.isReleaseFunc(bizMsg, relFuncTpCd);
            if (!isReleaseFunc && !isViewOnlyFunc) {
                continue;
            }

            if (ixL >= bizMsg.L.length()) {
                ixL++;
                bizMsg.setMessageInfo(NWAM0007W);
                break;
            }

            String cntKey = (String) map.get("CNT_KEY");
            int cntNum = 0;
            if (keyMap.containsKey(cntKey)) {
                cntNum = keyMap.get(cntKey);
            }
            cntNum++;
            keyMap.put(cntKey, cntNum);

            NWAL1520_LCMsg lBizMsg = bizMsg.L.no(ixL);
            lBizMsg.xxNewRowNum.setValue(ixL + 1);
            setValue(lBizMsg.relFlg,                (String) map.get("REL_FLG"));
            setValue(lBizMsg.hldLvlDescTxt,         (String) map.get("HLD_LVL_DESC_TXT"));
            setValue(lBizMsg.hldRsnDescTxt,         (String) map.get("HLD_RSN_DESC_TXT"));
            setValue(lBizMsg.hldDt_AP,              (String) map.get("HLD_DT"));
            setValue(lBizMsg.hldUntilDt_AP,         (String) map.get("HLD_UNTIL_DT"));
            setValue(lBizMsg.hldApplyRsnDescTxt_AP, (String) map.get("HLD_APPLY_RSN_DESC_TXT"));
            setValue(lBizMsg.hldDtlTxt_AP,          (String) map.get("HLD_DTL_TXT"));
            if(ZYPCommonFunc.hasValue(lBizMsg.hldDtlTxt_AP)){
                setValue(lBizMsg.hldDtlTxt_AP, lBizMsg.hldDtlTxt_AP.getValue().replaceAll(String.format("%n"), " "));
            }
            setValue(lBizMsg.relDt_RE,              (String) map.get("REL_DT"));
            setValue(lBizMsg.hldRelRsnDescTxt_RE,   (String) map.get("HLD_REL_RSN_DESC_TXT"));
            setValue(lBizMsg.relMemoTxt_RE,         (String) map.get("REL_MEMO_TXT"));
            if(ZYPCommonFunc.hasValue(lBizMsg.relMemoTxt_RE)){
                setValue(lBizMsg.relMemoTxt_RE, lBizMsg.relMemoTxt_RE.getValue().replaceAll(String.format("%n"), " "));
            }
            setValue(lBizMsg.hldPk,                 (BigDecimal) map.get("HLD_PK"));
            setValue(lBizMsg.relFuncTpCd,           (String) map.get("REL_FUNC_TP_CD"));

            // S21_NA#2033
            setValue(lBizMsg.cpoOrdNum, (String) map.get("CPO_ORD_NUM"));
            setValue(lBizMsg.xxLineNum, NWXC150001DsCheck.editDtlLineNum((String) map.get("DS_ORD_POSN_NUM"), (BigDecimal) map.get("DS_CPO_LINE_NUM"), (BigDecimal) map.get("DS_CPO_LINE_SUB_NUM")));
            
            // if (hasValue((BigDecimal) map.get("DS_CPO_LINE_NUM"))
            // && hasValue((String) map.get("LINE_NUM"))) {
            // setValue(bizMsg.L.no(i).xxLineNum, (String)
            // map.get("LINE_NUM"));
            // } else if (hasValue((BigDecimal)
            // map.get("RTN_DS_CPO_LINE_NUM")) && hasValue((String)
            // map.get("RTN_LINE_NUM"))) {
            // setValue(bizMsg.L.no(i).xxLineNum, (String)
            // map.get("RTN_LINE_NUM"));
            // }

            // if (hasValue((String) map.get("CPO_ORD_NUM"))) {
            // setValue(bizMsg.L.no(i).cpoOrdNum, (String)
            // map.get("CPO_ORD_NUM"));
            // } else {
            // setValue(bizMsg.L.no(i).cpoOrdNum, (String)
            // map.get("RTN_CPO_ORD_NUM"));
            // }

            if (hasValue((String) map.get("APP_PSN_CD"))) {
                setValue(lBizMsg.xxPsnNm_AP,        (String) map.get("APP_BY"));
            }

            if (hasValue((String) map.get("REL_PSN_CD"))) {
                setValue(lBizMsg.xxPsnNm_RE,        (String) map.get("REL_BY"));
            }

            ixL++;
        }
        bizMsg.L.setValidCount(ixL);

        int getValidCount = 0;

        Object[] keys = keyMap.keySet().toArray();
        Arrays.sort(keys);

        for (int i = 0; i < keys.length; i++) {
            Integer val = (Integer) keyMap.get(keys[i]);
            String[] keyList = keys[i].toString().split("-", 0);

            bizMsg.V.no(getValidCount).xxRefTblNum_VH.setValue(val);
            // Mod Start 2017/08/22 QC#19915
            //bizMsg.V.no(getValidCount).hldLvlDescTxt_VH.setValue(keyList[1]);
            //bizMsg.V.no(getValidCount).hldRsnDescTxt_VH.setValue(keyList[2]);
            bizMsg.V.no(getValidCount).hldLvlDescTxt_VH.setValue(keyList[2]);
            bizMsg.V.no(getValidCount).hldRsnDescTxt_VH.setValue(keyList[3]);
            // Mod End 2017/08/22 QC#19915
            getValidCount++;
        }

        bizMsg.V.setValidCount(getValidCount);
    }

    /**
     * Select_All Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1520Scrn00_Select_All(NWAL1520CMsg bizMsg, NWAL1520SMsg glblMsg) {
        //
    }

    /**
     * Un_Select_All Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1520Scrn00_Un_Select_All(NWAL1520CMsg bizMsg, NWAL1520SMsg glblMsg) {
        //
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1520_NWAL1130(NWAL1520CMsg bizMsg, NWAL1520SMsg glblMsg) {
        //
    }

    /**
     * Apply_Hold Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1520Scrn00_Apply_Hold(NWAL1520CMsg bizMsg, NWAL1520SMsg glblMsg) {
        doProcess_NWAL1520Scrn00_Search_Hold(bizMsg, glblMsg);
    }

}
