/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1760;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static business.blap.NWAL1760.constant.NWAL1760Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.parts.NWZC157001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Price List Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/25   Fujitsu         A.Suda          Create          N/A
 * 2015/11/25   Fujitsu         T.Ishii         Update          S21_NA#939
 *</pre>
 */
public class NWAL1760BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1760CMsg bizMsg = (NWAL1760CMsg) cMsg;

            if ("NWAL1760_INIT".equals(screenAplID)) {
                doProcess_NWAL1760_INIT(bizMsg);

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
     */
    private void doProcess_NWAL1760_INIT(NWAL1760CMsg bizMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = null;
        String lineOfBusinessCd = "";

        ssmResult = NWAL1760Query.getInstance().getLineOfBusinessCd(bizMsg);

        if (!ssmResult.isCodeNotFound()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {
                lineOfBusinessCd = (String) resList.get(0).get("LINE_BIZ_TP_CD");
            }
        }

        ssmResult = NWAL1760Query.getInstance().getDsAccountNm(bizMsg);

        if (!ssmResult.isCodeNotFound()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {
                setValue(bizMsg.dsAcctNm, (String) resList.get(0).get("DS_ACCT_NM"));
            }
        }

        ssmResult = NWAL1760Query.getInstance().getOrderCategoryNm(bizMsg);

        if (!ssmResult.isCodeNotFound()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {
                setValue(bizMsg.dsOrdCatgDescTxt, (String) resList.get(0).get("DS_ORD_CATG_DESC_TXT"));
            }
        }

        ssmResult = NWAL1760Query.getInstance().getOrderReasonNm(bizMsg);

        if (!ssmResult.isCodeNotFound()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {
                setValue(bizMsg.dsOrdTpDescTxt, (String) resList.get(0).get("DS_ORD_TP_DESC_TXT"));
            }
        }

        /**************************************************
         * call [NWZC157001] : Pricing API
         **************************************************/
        final NWZC157001 pricAPI = new NWZC157001();

        final NWZC157001PMsg pMsg = new NWZC157001PMsg();
        setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(pMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        setValue(pMsg.prcBaseDt, bizMsg.prcBaseDt);
        setValue(pMsg.orgRqstDelyDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        setValue(pMsg.prcCtxTpCd, bizMsg.prcCtxTpCd);
        setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        setValue(pMsg.lineBizTpCd, lineOfBusinessCd);
        setValue(pMsg.dsAcctNum, bizMsg.custAcctNum);
        setValue(pMsg.csmpNum, bizMsg.csmpNum);
        setValue(pMsg.dlrRefNum, bizMsg.dlrRefNum);
        setValue(pMsg.prcContrNum, bizMsg.prcContrNum);
        setValue(pMsg.coaBrCd, bizMsg.coaBrCd);
        // S21_NA#939 add start
        setValue(pMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_ON_Y);
        // S21_NA#939 add end

        // [NWZC157001] : Pricing API
        pricAPI.execute(pMsg, ONBATCH_TYPE.ONLINE);

        // has API errors.
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return;
            }
        }

        int validCount = 0;
        // get Price Category Name.
        if (pMsg.xxPrcList.getValidCount() > 0) {
            for (int j = 0; j < pMsg.xxPrcList.getValidCount(); j++) {
                if (j >= bizMsg.A.length()) {
                    bizMsg.setMessageInfo(NZZM0001W);
                    break;
                }
                String prcCatgCd = pMsg.xxPrcList.no(j).prcCatgCd.getValue();

                if (hasValue(prcCatgCd)) {

                    S21SsmEZDResult ssmResult1 //
                    = NWAL1760Query.getInstance().getPriceCategoryNm(prcCatgCd, bizMsg.prcCatgNm.getValue());

                    if (!ssmResult1.isCodeNotFound()) {
                        @SuppressWarnings("unchecked")
                        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult1.getResultObject();
                        if (resList != null && !resList.isEmpty()) {

                            setValue(bizMsg.A.no(validCount).prcListTpCd_A, (String) resList.get(0).get("PRC_LIST_TP_CD"));
                            setValue(bizMsg.A.no(validCount).prcListTpDescTxt_A, (String) resList.get(0).get("PRC_LIST_TP_DESC_TXT"));
                            setValue(bizMsg.A.no(validCount).prcCatgCd_A, prcCatgCd);
                            setValue(bizMsg.A.no(validCount).prcCatgNm_A, (String) resList.get(0).get("PRC_CATG_NM"));
                            setValue(bizMsg.A.no(validCount).prcListTpNm_A, (String) resList.get(0).get("PRC_LIST_TP_NM"));
                            validCount++;

                        }
                    }
                }
            }
        }
        if (validCount == 0) {
            bizMsg.setMessageInfo(NZZM0000E);
        }

        bizMsg.A.setValidCount(validCount);

    }

}
