/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1520;

import static business.blap.NWAL1520.constant.NWAL1520Constant.HLD_LVL_DESC_TXT_RMA_SUFFIX;
import static business.blap.NWAL1520.constant.NWAL1520Constant.ITEM_NM_HLD_UNTL_DATE;
import static business.blap.NWAL1520.constant.NWAL1520Constant.LINE_SUB_NUM_COUNT;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NWAL1520_HDR_HLD_ONLY;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NWAM0504E;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NWAM0505E;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NWAM0675E;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NWAM0676E;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NWAM0941E;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NWAM0942E;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NWAM0971E;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NZZM0000E;
import static business.blap.NWAL1520.constant.NWAL1520Constant.ZZM8100I;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.DS_CPO_RTRN_LINE_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.DS_CPO_RTRN_LINE_SUB_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.SHPG_PLN_NUM;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil.getSelectedRows;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdateNoWait;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1520.common.NWAL1520CommonLogic;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.HLDTMsg;
import business.parts.NWZC033001PMsg;
import business.parts.NWZC044001PMsg;
import business.parts.NWZC188001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC033001.NWZC033001;
import com.canon.cusa.s21.api.NWZ.NWZC044001.NWZC044001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1520BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         A.Suda          Create          N/A
 * 2015/12/24   Fujitsu         T.Ishii         Update          S21_NA#2151
 * 2016/02/01   Fujitsu         S.Yamamoto      Update          S21_NA#3960
 * 2016/02/26   Fujitsu         T.Ishii         Update          S21_NA#3960
 * 2016/04/26   Fujitsu         M.Yamada        Update          S21_NA#7534
 * 2017/08/21   Fujitsu         H.Sugawara      Update          QC#19914
 * 2017/08/21   Fujitsu         H.Sugawara      Update          QC#19952
 * 2017/09/19   Fujitsu         H.Sugawara      Update          QC#19918
 * 2019/05/31   Fujitsu         R.Nakamura      Update          S21_NA#50553
 * 2019/09/10   Fujitsu         R.Nakamura      Update          S21_NA#51929
 * 2019/09/20   Fujitsu         S.Kosaka        Update          QC#53184
 *</pre>
 */
public class NWAL1520BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1520CMsg bizMsg = (NWAL1520CMsg) cMsg;
            NWAL1520SMsg glblMsg = (NWAL1520SMsg) sMsg;

            if ("NWAL1520Scrn00_Apply_Hold".equals(screenAplID)) {
                doProcess_NWAL1520Scrn00_Apply_Hold(bizMsg, glblMsg);
            } else if ("NWAL1520Scrn00_Release_Hold".equals(screenAplID)) {
                doProcess_NWAL1520Scrn00_Release_Hold(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Release_Hold Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1520Scrn00_Release_Hold(NWAL1520CMsg bizMsg, NWAL1520SMsg glblMsg) {

        NWAL1520CommonLogic.clearErrorInfo(bizMsg, glblMsg);

        // Del Start 2017/08/21 QC#19952
        //if (!hasValue(bizMsg.hldRelRsnDescTxt_RH)) {
        //    bizMsg.setMessageInfo(NWAM0298E, new String[] {"Released Reason" });
        //    return;
        //}
        // Del End 2017/08/21 QC#19952

        // select row
        final List<Integer> selectedRows = getSelectedRows(bizMsg.L, "xxChkBox", CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            bizMsg.setMessageInfo(NWAM0504E);
            return;
        }

        // Add Start 2017/09/19 QC#19918
        // check set item
        boolean selectedSetItemError = false;
        selectedSetItemError = isSelectedSetItemError(bizMsg);
        if (selectedSetItemError) {
            return;
        }
        // Add End 2017/09/19 QC#19918
        // 2019/09/20 QC#53184 Add Start
        boolean headerOnlyFlag = true;
        NWZC188001PMsg stsUpdApiParam = new NWZC188001PMsg();
        stsUpdApiParam.glblCmpyCd.setValue(getGlobalCompanyCode());
        stsUpdApiParam.cpoOrdNum.setValue(bizMsg.cpoOrdNum_A.getValue());
        int i = 0;
        // 2019/09/20 QC#53184 Add End

        // Hold Release API
        for (int row : selectedRows) {
            NWAL1520_LCMsg lineMsg = bizMsg.L.no(row);

            final NWZC033001PMsg pMsg = new NWZC033001PMsg();

            setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            // setValue(pMsg.hldPk, lineMsg.hldPk); //S21_NA#2151
            // setValue(pMsg.cpoOrdNum, lineMsg.cpoOrdNum); //S21_NA#2151
            setValue(pMsg.hldRelRsnCd, bizMsg.hldRelRsnCd_RH);
            setValue(pMsg.relMemoTxt, bizMsg.relMemoTxt_RH); // S21_NA#7534
            // setValue(pMsg.relMemoTxt, lineMsg.relMemoTxt_RE); // S21_NA#7534
            //S21_NA#2151 start
            HLDTMsg hld = new HLDTMsg();
            setValue(hld.glblCmpyCd, getGlobalCompanyCode());
            setValue(hld.hldPk, lineMsg.hldPk);
            hld = (HLDTMsg) S21FastTBLAccessor.findByKey(hld);
            if (hld != null) {
                setValue(pMsg.hldPk, lineMsg.hldPk);
                setValue(pMsg.cpoOrdNum, hld.cpoOrdNum);
                setValue(pMsg.cpoDtlLineNum, hld.cpoDtlLineNum);
                setValue(pMsg.cpoDtlLineSubNum, hld.cpoDtlLineSubNum);
                setValue(pMsg.shpgPlnNum, hld.shpgPlnNum);
                // 2019/09/20 QC#53184 Add Start
                if (hasValue(hld.cpoDtlLineNum) || hasValue(hld.cpoDtlLineSubNum) || hasValue(hld.shpgPlnNum)) {
                    headerOnlyFlag = false;
                }
                if (TRX_SRC_TP.WHOLE_SALES_RETURN.equals(hld.trxSrcTpCd.getValue())) {
                    setValue(stsUpdApiParam.rmaLineList.no(i).dsCpoRtrnLineNum, hld.cpoDtlLineNum);
                    setValue(stsUpdApiParam.rmaLineList.no(i).dsCpoRtrnLineSubNum, hld.cpoDtlLineSubNum);
                    i++;
                    stsUpdApiParam.rmaLineList.setValidCount(i);
                }
                // 2019/09/20 QC#53184 Add End
            }
            //S21_NA#2151 end

            NWZC033001 relHoldApi = new NWZC033001();
            relHoldApi.execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int j = 0; j < pMsg.xxMsgIdList.getValidCount(); j++) {
                    bizMsg.setMessageInfo((String) pMsg.xxMsgIdList.no(j).xxMsgId.getValue());
                    return;
                }
            }
        }
        // 2019/09/20 QC#53184 Add Start
        if (headerOnlyFlag) {
            callOrderDisplayStatusUpdAPIAllLine(bizMsg);
        }
        if (stsUpdApiParam.rmaLineList.getValidCount() > 0) {
            new NWZC188001().execute(stsUpdApiParam, ONBATCH_TYPE.ONLINE);

            if (stsUpdApiParam.xxMsgIdList.getValidCount() > 0) {
                for (int j = 0; j < stsUpdApiParam.xxMsgIdList.getValidCount(); j++) {
                    bizMsg.setMessageInfo((String) stsUpdApiParam.xxMsgIdList.no(j).xxMsgId.getValue());
                    return;
                }
            }
        }
        // 2019/09/20 QC#53184 Add End

        // Add Start 2017/08/21 QC#19914
        bizMsg.hldRelRsnDescTxt_RH.clear();
        bizMsg.hldRelRsnCd_RH.clear();
        bizMsg.relMemoTxt_RH.clear();
        // Add End 2017/08/21 QC#19914

        bizMsg.setMessageInfo(ZZM8100I);

    }

    // Add Start 2017/09/19 QC#19918
    /**
     * isSelectedSetItemError
     * @param bizMsg NWAL1520CMsg
     * @return boolean
     */
    private boolean isSelectedSetItemError(NWAL1520CMsg bizMsg) {
        boolean result = false;

        String hdrOnly = ZYPCodeDataUtil.getVarCharConstValue(NWAL1520_HDR_HLD_ONLY, getGlobalCompanyCode());
        boolean isHdrOnly = false;
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, hdrOnly)) {
            isHdrOnly = true;
        }

        Map<String, ArrayList<NWAL1520_LCMsg>> cMsgListMap = new HashMap<String, ArrayList<NWAL1520_LCMsg>>();
        for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
            // grouping each position and line number
            NWAL1520_LCMsg currentCMsg = bizMsg.L.no(i);

            String hashKey = getHashKey(currentCMsg, isHdrOnly);

            if (!hasValue(hashKey)) {
                continue;
            }

            ArrayList<NWAL1520_LCMsg> cMsgList = cMsgListMap.get(hashKey);
            if (cMsgList == null) {
                cMsgList = new ArrayList<NWAL1520_LCMsg>(1);
            }

            cMsgList.add(currentCMsg);
            cMsgListMap.put(hashKey, cMsgList);
        }

        for (String hashKey : cMsgListMap.keySet()) {
            // check selected all components of set item / no one
            // selected.
            ArrayList<NWAL1520_LCMsg> cMsgList = cMsgListMap.get(hashKey);

            if (cMsgList.size() <= 1) {
                continue;
            }

            boolean checkComponentsResult = isSelectedPartsOfComponents(cMsgList);
            if (checkComponentsResult) {
                result = true;
            }
        }

        return result;
    }

    /**
     * getHashKey
     * @param currentCMsg NWAL1520_LCMsg
     * @param isHdrOnly boolean
     * @return String
     */
    private String getHashKey(NWAL1520_LCMsg currentCMsg, boolean isHdrOnly) {
        String xxLineNum = currentCMsg.xxLineNum.getValue();

        if (!hasValue(xxLineNum)) {
            return null;
        }

        String hashKey = null;
        String[] splitLine = xxLineNum.split("\\.");

        if (splitLine.length == LINE_SUB_NUM_COUNT) {
            // If xxLineNum has line sub number, get position and line
            // number for hash key.
            StringBuffer hashKeySb = new StringBuffer();
            hashKeySb.append(splitLine[0]).append(".").append(splitLine[1]);
            hashKey = hashKeySb.toString();
        } else {
            hashKey = xxLineNum;
        }

        if (!isHdrOnly) {
            String hldLvlDescTxt = currentCMsg.hldLvlDescTxt.getValue();
            if (hasValue(hldLvlDescTxt) && hldLvlDescTxt.endsWith(HLD_LVL_DESC_TXT_RMA_SUFFIX)) {
                // If NOT header only mode, append RMA suffix to hash
                // key.
                StringBuffer hashKeySb = new StringBuffer();
                hashKeySb.append(hashKey).append("_").append(HLD_LVL_DESC_TXT_RMA_SUFFIX);
                hashKey = hashKeySb.toString();
            }
        }

        return hashKey;
    }

    /**
     * isSelectedPartsOfComponents
     * @param cMsgList ArrayList<NWAL1520_LCMsg>
     * @return boolean
     */
    private boolean isSelectedPartsOfComponents(ArrayList<NWAL1520_LCMsg> cMsgList) {
        boolean result = false;
        boolean hasParent = false;

        int checkCount = 0;
        for (NWAL1520_LCMsg currentCMsg : cMsgList) {
            // count selected check box.
            if (CHKBOX_ON_Y.equals(currentCMsg.xxChkBox.getValue())) {
                checkCount++;
            }

            String xxLineNum = currentCMsg.xxLineNum.getValue();
            String[] splitLine = xxLineNum.split("\\.");

            if (splitLine.length < LINE_SUB_NUM_COUNT) {
                hasParent = true;
            }
        }

        if (checkCount != 0 && checkCount != cMsgList.size()) {
            result = true;

            for (NWAL1520_LCMsg currentCMsg : cMsgList) {
                if (hasParent) {
                    currentCMsg.xxChkBox.setErrorInfo(1, NWAM0942E);
                } else {
                    currentCMsg.xxChkBox.setErrorInfo(1, NWAM0941E);
                }
            }
        }

        return result;
    }
    // Add End 2017/09/19 QC#19918

    /**
     * lock CPO.
     * @param bizMsg NWAL1520CMsg
     */
    private boolean lockCpo(NWAL1520CMsg bizMsg) {

        // [CPO]
        CPOTMsg cpoTMsg = new CPOTMsg();
        setValue(cpoTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum_A);

        cpoTMsg = (CPOTMsg) findByKeyForUpdateNoWait(cpoTMsg);

        if (cpoTMsg == null) {
            bizMsg.setMessageInfo(NWAM0505E, new String[] {"Order" });
            return false;
        }

        return true;
    }

    /**
     * lock CPO_DTL.
     * @param bizMsg NWAL1520CMsg
     * @param String lineNum
     * @param String lineSubNum
     */
    private boolean lockCpoDtl(NWAL1520CMsg bizMsg, String lineNum, String lineSubNum) {

        // [CPO_DTL]
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        setValue(cpoDtlTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cpoDtlTMsg.cpoOrdNum, bizMsg.cpoOrdNum_A);
        setValue(cpoDtlTMsg.cpoDtlLineNum, lineNum);
        setValue(cpoDtlTMsg.cpoDtlLineSubNum, lineSubNum);

        cpoDtlTMsg = (CPO_DTLTMsg) findByKeyForUpdateNoWait(cpoDtlTMsg);

        if (cpoDtlTMsg == null) {
            bizMsg.setMessageInfo(NWAM0505E, new String[] {"Line" });
            return false;
        }

        return true;
    }

    /**
     * lock DS_CPO_RTRN_DTL.
     * @param bizMsg NWAL1520CMsg
     * @param String lineNum
     * @param String lineSubNum
     */
    private boolean lockCpoRtrnDtl(NWAL1520CMsg bizMsg, String lineNum, String lineSubNum) {

        // [DS_CPO_RTRN_DTL]
        DS_CPO_RTRN_DTLTMsg cpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
        setValue(cpoRtrnDtlTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cpoRtrnDtlTMsg.cpoOrdNum, bizMsg.cpoOrdNum_A);
        setValue(cpoRtrnDtlTMsg.dsCpoRtrnLineNum, lineNum);
        setValue(cpoRtrnDtlTMsg.dsCpoRtrnLineSubNum, lineSubNum);

        cpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) findByKeyForUpdateNoWait(cpoRtrnDtlTMsg);

        if (cpoRtrnDtlTMsg == null) {
            bizMsg.setMessageInfo(NWAM0505E, new String[] {"Line" });
            return false;
        }

        return true;
    }

    /**
     * Apply_Hold Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1520Scrn00_Apply_Hold(NWAL1520CMsg bizMsg, NWAL1520SMsg glblMsg) {

        S21SsmEZDResult ssmResult = null;
        List resultList = null;
        NWAL1520CommonLogic.clearErrorInfo(bizMsg, glblMsg);
        bizMsg.hldRsnCd_A.clear();
        bizMsg.hldApplyRsnCd_A.clear();

        // Order Number
        if (hasValue(bizMsg.cpoOrdNum_A)) {
            ssmResult = NWAL1520Query.getInstance().getcpoOrdNum(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.cpoOrdNum_A.setErrorInfo(1, NZZM0000E);
                return;
            }
            resultList = (List) ssmResult.getResultObject();
            Map map = (Map) resultList.get(0);
            if (ORD_HDR_STS.CANCELLED.equals((String) map.get("ORD_HDR_STS_CD")) || ORD_HDR_STS.CLOSED.equals((String) map.get("ORD_HDR_STS_CD"))) {
                bizMsg.cpoOrdNum_A.setErrorInfo(1, NWAM0675E, new String[] {"Order", "Order" });
                return;
            }

            // Add Start 2019/09/10 QC#51929
            BigDecimal rteStsRoutedCnt = NWAL1520Query.getInstance().getRteStsRoutedCnt(bizMsg);
            if (rteStsRoutedCnt.compareTo(BigDecimal.valueOf(0)) > 0) {
                bizMsg.setMessageInfo(NWAM0971E);
                return;
            }
            // Add End 2019/09/10 QC#51929
        }

        // Hold Name
        if (hasValue(bizMsg.hldRsnDescTxt_A)) {
            ssmResult = NWAL1520Query.getInstance().getHldReasonList(bizMsg.hldRsnDescTxt_A.getValue());
            if (ssmResult.isCodeNotFound()) {
                bizMsg.hldRsnDescTxt_A.setErrorInfo(1, NZZM0000E);
                return;
            }
            resultList = (List) ssmResult.getResultObject();

            if (resultList.size() > 1) {
                bizMsg.xxErrFlg_HN.setValue(FLG_ON_Y);
                return;
            } else if (resultList.size() == 1){
                Map map = (Map) resultList.get(0);
                setValue(bizMsg.hldRsnCd_A, (String) map.get("HLD_RSN_CD"));
            }
        }

        // Hold Reason
        if (hasValue(bizMsg.hldApplyRsnDescTxt_A)) {
            ssmResult = NWAL1520Query.getInstance().getHldApplyReasonList(bizMsg.hldApplyRsnDescTxt_A.getValue());
            if (ssmResult.isCodeNotFound()) {
                bizMsg.hldApplyRsnDescTxt_A.setErrorInfo(1, NZZM0000E);
                return;
            }
            resultList = (List) ssmResult.getResultObject();
            if (resultList.size() > 1) {
                bizMsg.xxErrFlg_HR.setValue(FLG_ON_Y);
                return;
            } else if (resultList.size() == 1) {
                Map map = (Map) resultList.get(0);
                setValue(bizMsg.hldApplyRsnCd_A, (String) map.get("HLD_APPLY_RSN_CD"));
            }
        }

        // Hold Until Date
        if (hasValue(bizMsg.hldUntilDt_A)) {
            if (ZYPDateUtil.compare(bizMsg.hldUntilDt_A.getValue(), ZYPDateUtil.getSalesDate(getGlobalCompanyCode())) <= 0) {
                // hldUntilDt < SalesDate
                // Mod Start 2019/05/31 QC#50553
//                bizMsg.hldUntilDt_A.setErrorInfo(1, NWAM0676E);
                bizMsg.hldUntilDt_A.setErrorInfo(1, NWAM0676E, new String[] {ITEM_NM_HLD_UNTL_DATE });
                // Mod End 2019/05/31 QC#50553
                return;
            }
        }

        if (!lockCpo(bizMsg)) {
            return;
        }

        // S21_NA#2444 ssmResult =
        // NWAL1520Query.getInstance().getOrderInfo(bizMsg);

        if (hasValue(bizMsg.condSqlTxt_A)) {

            // ---------------------------------------------
            // Line Level
            // ---------------------------------------------

            ssmResult = NWAL1520Query.getInstance().getOrderInfo(bizMsg); // S21_NA#2444
            if (ssmResult.isCodeNotFound()) {
                bizMsg.condSqlTxt_A.setErrorInfo(1, NZZM0000E);
                return;
            }
            // S21_NA#2444 }
            // 2019/09/20 QC#53184 Add Start
            NWZC188001PMsg stsUpdApiParam = new NWZC188001PMsg();
            stsUpdApiParam.glblCmpyCd.setValue(getGlobalCompanyCode());
            stsUpdApiParam.cpoOrdNum.setValue(bizMsg.cpoOrdNum_A.getValue());
            int k = 0;
            // 2019/09/20 QC#53184 Add End
            resultList = (List) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                // Line Number
                if (hasValue(bizMsg.condSqlTxt_A)) {
                    if (ORD_HDR_STS.CANCELLED.equals((String) map.get("LINE_STS_CD")) || ORD_HDR_STS.CLOSED.equals((String) map.get("LINE_STS_CD"))) {
                        bizMsg.condSqlTxt_A.setErrorInfo(1, NWAM0675E, new String[] {"Line", "Line" });
                        return;
                    }
                }

                if (CONFIG_CATG.OUTBOUND.equals((String) map.get("CONFIG_CATG_CD"))) {
                    if (!lockCpoDtl(bizMsg, (String) map.get("CPO_DTL_LINE_NUM"), (String) map.get("CPO_DTL_LINE_SUB_NUM"))) {
                        return;
                    }
                } else {
                    if (!lockCpoRtrnDtl(bizMsg, (String) map.get("CPO_DTL_LINE_NUM"), (String) map.get("CPO_DTL_LINE_SUB_NUM"))) {
                        return;
                    }
                }

                NWZC044001PMsg addHoldInfoMsg = new NWZC044001PMsg();

                // S21_NA#2444 if (hasValue(bizMsg.condSqlTxt_A)) {
                if (CONFIG_CATG.OUTBOUND.equals(bizMsg.configCatgCd_A.getValue())) {
                    setValue(addHoldInfoMsg.cpoDtlLineNum, (String) map.get("CPO_DTL_LINE_NUM"));
                    setValue(addHoldInfoMsg.cpoDtlLineSubNum, (String) map.get("CPO_DTL_LINE_SUB_NUM"));
                } else {
                    setValue(addHoldInfoMsg.cpoDtlLineNum, (String) map.get("CPO_DTL_LINE_NUM"));
                    setValue(addHoldInfoMsg.cpoDtlLineSubNum, (String) map.get("CPO_DTL_LINE_SUB_NUM"));
                    setValue(addHoldInfoMsg.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES_RETURN);
                    // 2019/09/20 QC#53184 Add Start
                    setValue(stsUpdApiParam.rmaLineList.no(k).dsCpoRtrnLineNum, (String) map.get("CPO_DTL_LINE_NUM"));
                    setValue(stsUpdApiParam.rmaLineList.no(k).dsCpoRtrnLineSubNum, (String) map.get("CPO_DTL_LINE_SUB_NUM"));
                    k++;
                    stsUpdApiParam.rmaLineList.setValidCount(k);
                    // 2019/09/20 QC#53184 Add End
                }
                // S21_NA#2444 }
                setValue(addHoldInfoMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(addHoldInfoMsg.cpoOrdNum, bizMsg.cpoOrdNum_A);
                setValue(addHoldInfoMsg.hldRsnCd, bizMsg.hldRsnCd_A);
                setValue(addHoldInfoMsg.hldDtlTxt, bizMsg.hldDtlTxt_A);
                setValue(addHoldInfoMsg.slsDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
                setValue(addHoldInfoMsg.hldUntilDt, bizMsg.hldUntilDt_A);
                setValue(addHoldInfoMsg.hldApplyRsnCd, bizMsg.hldApplyRsnCd_A);

                NWZC044001 addHoldInfoApi = new NWZC044001();

                addHoldInfoApi.execute(addHoldInfoMsg, ONBATCH_TYPE.ONLINE);

                if (addHoldInfoMsg.xxMsgIdList.getValidCount() > 0) {
                    for (int j = 0; j < addHoldInfoMsg.xxMsgIdList.getValidCount(); j++) {
                        bizMsg.setMessageInfo((String) addHoldInfoMsg.xxMsgIdList.no(j).xxMsgId.getValue());
                        return;
                    }
                }
            }
            // 2019/09/20 QC#53184 Add Start
            if (stsUpdApiParam.rmaLineList.getValidCount() > 0) {
                new NWZC188001().execute(stsUpdApiParam, ONBATCH_TYPE.ONLINE);

                if (stsUpdApiParam.xxMsgIdList.getValidCount() > 0) {
                    for (int j = 0; j < stsUpdApiParam.xxMsgIdList.getValidCount(); j++) {
                        bizMsg.setMessageInfo((String) stsUpdApiParam.xxMsgIdList.no(j).xxMsgId.getValue());
                        return;
                    }
                }
            }
            // 2019/09/20 QC#53184 Add End
        } else {

            // ---------------------------------------------
            // CPO Level
            // ---------------------------------------------
            NWZC044001PMsg addHoldInfoMsg = new NWZC044001PMsg();

            setValue(addHoldInfoMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(addHoldInfoMsg.cpoOrdNum, bizMsg.cpoOrdNum_A);
            setValue(addHoldInfoMsg.hldRsnCd, bizMsg.hldRsnCd_A);
            setValue(addHoldInfoMsg.hldDtlTxt, bizMsg.hldDtlTxt_A);
            setValue(addHoldInfoMsg.slsDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            setValue(addHoldInfoMsg.hldUntilDt, bizMsg.hldUntilDt_A);
            setValue(addHoldInfoMsg.hldApplyRsnCd, bizMsg.hldApplyRsnCd_A);

            NWZC044001 addHoldInfoApi = new NWZC044001();

            addHoldInfoApi.execute(addHoldInfoMsg, ONBATCH_TYPE.ONLINE);

            if (addHoldInfoMsg.xxMsgIdList.getValidCount() > 0) {
                for (int j = 0; j < addHoldInfoMsg.xxMsgIdList.getValidCount(); j++) {
                    bizMsg.setMessageInfo((String) addHoldInfoMsg.xxMsgIdList.no(j).xxMsgId.getValue());
                    return;
                }
            }
            // 2019/09/20 QC#53184 Add Start
            callOrderDisplayStatusUpdAPIAllLine(bizMsg);
            // 2019/09/20 QC#53184 Add End
        } // S21_NA#2444
        bizMsg.setMessageInfo(ZZM8100I);

    }
    // 2019/09/20 QC#53184 Add Start
    private void callOrderDisplayStatusUpdAPIAllLine(NWAL1520CMsg bizMsg) {

        NWZC188001PMsg stsUpdApiParam = new NWZC188001PMsg();
        stsUpdApiParam.glblCmpyCd.setValue(getGlobalCompanyCode());
        stsUpdApiParam.cpoOrdNum.setValue(bizMsg.cpoOrdNum_A.getValue());

        List<Map<String, Object>> cpoShpgPlnList = NWAL1520Query.getInstance().getShipPlanNumFromOrdNum(stsUpdApiParam);
        if (cpoShpgPlnList != null) {
            int i = 0;
            for (Map<String, Object> cpoShpgPln : cpoShpgPlnList) {
                ZYPEZDItemValueSetter.setValue(stsUpdApiParam.shpgPlnNumList.no(i).shpgPlnNum, (String)cpoShpgPln.get(SHPG_PLN_NUM));
                i++;
            }
            stsUpdApiParam.shpgPlnNumList.setValidCount(i);
        }
        List<Map<String, Object>> rmaDtlList = NWAL1520Query.getInstance().getRmaDtlNumFromOrdNum(stsUpdApiParam);
        if (rmaDtlList != null) {
            int i = 0;
            for (Map<String, Object> rmaDtl : rmaDtlList) {
                ZYPEZDItemValueSetter.setValue(stsUpdApiParam.rmaLineList.no(i).dsCpoRtrnLineNum, (String)rmaDtl.get(DS_CPO_RTRN_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(stsUpdApiParam.rmaLineList.no(i).dsCpoRtrnLineSubNum, (String)rmaDtl.get(DS_CPO_RTRN_LINE_SUB_NUM));
                i++;
            }
            stsUpdApiParam.rmaLineList.setValidCount(i);
        }

        new NWZC188001().execute(stsUpdApiParam, ONBATCH_TYPE.ONLINE);

        if (stsUpdApiParam.xxMsgIdList.getValidCount() > 0) {
            for (int j = 0; j < stsUpdApiParam.xxMsgIdList.getValidCount(); j++) {
                bizMsg.setMessageInfo((String) stsUpdApiParam.xxMsgIdList.no(j).xxMsgId.getValue());
                return;
            }
        }
    }
    // 2019/09/20 QC#53184 Add End
}
