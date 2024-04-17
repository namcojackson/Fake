/*
 * <Pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC228001;

import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.CPO_WRK_LINE_NUM_LEN;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.DATE_TIME_PATTERN;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.KEY_NWZC1690_GLBL_CMPY_NM;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.KEY_OUTPUT_CONTACT_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.NWZM0789E;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.NWZM1782E;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.NWZM1783E;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.NWZM1785E;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.NWZM1790E;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.NWZM1793E;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.NWZM2260E;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.RTRN_BY_CUST_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.RTRN_TO_ADDR_01_LEN;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.SELL_TO_ADDR_01_LEN;
import static com.canon.cusa.s21.api.NWZ.NWZC228001.constant.NWZC228001Constant.SHIP_TO_ADDR_01_LEN;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.RTRN_AUTH_RPT_WRKTMsg;
import business.parts.NWZC228001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_RPT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * NWZC228001
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/14   Fujitsu         W.Honda         Create          QC#22139
 * 2018/05/21   Fujitsu         T.Aoi           Update          QC#22139-2
 * 2018/12/06   Fujitsu         C.Hara          Update          QC#28694
 * 2019/05/16   Fujitsu         S.Kosaka        Update          QC#50048
 */
public class NWZC228001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    public NWZC228001() {

        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Supply Quote Report Creation API
     * @param param NWZC169001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC228001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        doProcess(msgMap);

        msgMap.flush();
    }

    /**
     * Supply Quote Report Creation API.execute(List)
     * @param params List<NWZC169001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC228001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NWZC228001PMsg param : params) {
            execute(param, onBatchType);
        }
    }

    /**
     * doProcess
     * @param msgMap S21ApiMessageMap
     */
    private void doProcess(S21ApiMessageMap msgMap) {

        if (!paramCheck(msgMap)) {
            return;
        }

        NWZC228001PMsg param = (NWZC228001PMsg) msgMap.getPmsg();

        if (ZYPConstant.FLG_ON_Y.equals(param.delFlg.getValue())) {
            deletePrintWorkTable(msgMap);
        }

        String outputCtacTpCd = ZYPCodeDataUtil.getVarCharConstValue(KEY_OUTPUT_CONTACT_CD, param.glblCmpyCd.getValue());

        List<Map<String, Object>> detailList = getRtrnData(msgMap, outputCtacTpCd);

        if (detailList == null || detailList.size() <= 0) {
            msgMap.addXxMsgId("NWZM2261E");
            return;
        }

        if (insertDetail(detailList, msgMap)) {
            return;
        }
    }

    /**
     * paramCheck
     * @param msgMap S21ApiMessageMap
     */
    private boolean paramCheck(S21ApiMessageMap msgMap) {

        NWZC228001PMsg param = (NWZC228001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0789E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZM1790E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.bizAppId)) {
            msgMap.addXxMsgId(NWZM1793E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.otptOpCd)) {
            msgMap.addXxMsgId(NWZM1782E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.lineBizTpCd)) {
            msgMap.addXxMsgId(NWZM1783E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.cpoOrdNum)) {
            msgMap.addXxMsgId(NWZM1785E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.rmaRptTpCd)) {
            msgMap.addXxMsgId(NWZM2260E);
            return false;
        }


        if (!ZYPCommonFunc.hasValue(param.delFlg)) {
            param.delFlg.setValue(ZYPConstant.FLG_ON_Y);
        }

        return true;
    }

    /**
     * deletePrintWorkTable
     * @param msgMap S21ApiMessageMap
     */
    @SuppressWarnings("unchecked")
    private void deletePrintWorkTable(S21ApiMessageMap msgMap) {

        NWZC228001PMsg param = (NWZC228001PMsg) msgMap.getPmsg();

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("bizAppId", param.bizAppId.getValue());
        mapParam.put("otptOpCd", param.otptOpCd.getValue());
        mapParam.put("trxHdrNum", param.cpoOrdNum.getValue());

        List<Map<String, Object>> delList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDeleteWrk", mapParam);

        if (delList != null && delList.size() > 0) {
            for (Map<String, Object> map : delList) {
                if (map.get("RTRN_AUTH_RPT_WRK_PK") != null) {
                    RTRN_AUTH_RPT_WRKTMsg inTMsg = new RTRN_AUTH_RPT_WRKTMsg();
                    inTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    inTMsg.rtrnAuthRptWrkPk.setValue((java.math.BigDecimal) map.get("RTRN_AUTH_RPT_WRK_PK"));
                    RTRN_AUTH_RPT_WRKTMsg outTMsg = (RTRN_AUTH_RPT_WRKTMsg) S21ApiTBLAccessor.findByKey(inTMsg);

                    if (outTMsg != null) {
                        S21ApiTBLAccessor.remove(outTMsg);
                    }
                }
            }
        }
    }

    /**
     * getOrderConfHeader
     * @param msgMap S21ApiMessageMap
     * @param outputCtacTpCd Output Contact Type Code
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getRtrnData(S21ApiMessageMap msgMap, String outputCtacTpCd) {

        NWZC228001PMsg param = (NWZC228001PMsg) msgMap.getPmsg();

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("cpoOrdNum", param.cpoOrdNum.getValue());
        mapParam.put("splyQuoteRptTpCd", SPLY_QUOTE_RPT_TP.RETURN_AUTH);
        mapParam.put("glblCmpyNm", KEY_NWZC1690_GLBL_CMPY_NM);
        mapParam.put("outputCtacTpCd", outputCtacTpCd);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRtrnData", mapParam);
    }

    /**
     * insertDetail
     * @param detailList List<Map< ? , ? >>
     * @param msgMap S21ApiMessageMap
     */
    private boolean insertDetail(List<Map<String, Object>> detailList, S21ApiMessageMap msgMap) {

        NWZC228001PMsg param = (NWZC228001PMsg) msgMap.getPmsg();

        for (Map<String, Object> detail : detailList) {
            RTRN_AUTH_RPT_WRKTMsg inTMsg = new RTRN_AUTH_RPT_WRKTMsg();

            // common Value
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthRptWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_CONF_RPT_WRK_SQ));
            ZYPEZDItemValueSetter.setValue(inTMsg.bizAppId, param.bizAppId);
            ZYPEZDItemValueSetter.setValue(inTMsg.otptOpCd, param.otptOpCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.lineBizTpCd, param.lineBizTpCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.rmaRptTpCd, param.rmaRptTpCd);
            String slsDt = ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue());
            String systemTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN);
            StringBuilder tssb = new StringBuilder();
            tssb.append(slsDt).append(systemTime.substring(8, 17));
            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthPrintOtptTs, tssb.toString());

            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyNm, (String) detail.get("GLBL_CMPY_NM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.glblFullCmpyAddr, (String) detail.get("GLBL_FULL_CMPY_ADDR"));
            ZYPEZDItemValueSetter.setValue(inTMsg.rptTtlTxt, (String) detail.get("RPT_TTL_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.trxHdrNum, (String) detail.get("TRX_HDR_NUM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.trxDtTxt, (String) detail.get("TRX_DT_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.custIssPoNum, (String) detail.get("CUST_ISS_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.sellToCustCd, (String) detail.get("SELL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(inTMsg.sellToLocNm, (String) detail.get("SELL_TO_LOC_NM"));
            // 2019/05/16 QC#50048 Mod Start
            //ZYPEZDItemValueSetter.setValue(inTMsg.sellToAddr_01, (String) detail.get("SELL_TO_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(inTMsg.sellToAddr_01, cutEndString((String) detail.get("SELL_TO_ADDR_01"), SELL_TO_ADDR_01_LEN));
            // 2019/05/16 QC#50048 Mod End
            ZYPEZDItemValueSetter.setValue(inTMsg.sellToAddr_02, (String) detail.get("SELL_TO_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(inTMsg.fullCtacPsnNm, (String) detail.get("FULL_CTAC_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.dsCtacPntValTxt, (String) detail.get("DS_CTAC_PNT_VAL_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.shipToCustCd, (String) detail.get("SHIP_TO_CUST_CD"));
            String shipToLocNm = (String) detail.get("SHIP_TO_LOC_NM");
            if (ZYPCommonFunc.hasValue(shipToLocNm)) {
                int maxLen = inTMsg.getAttr("shipToLocNm").getDigit();
                if (shipToLocNm.length() > maxLen) {
                    shipToLocNm = shipToLocNm.substring(0, maxLen);
                }
                ZYPEZDItemValueSetter.setValue(inTMsg.shipToLocNm, shipToLocNm);
            }
            // 2019/05/16 QC#50048 Mod Start
            //ZYPEZDItemValueSetter.setValue(inTMsg.shipToAddr_01, (String) detail.get("SHIP_TO_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(inTMsg.shipToAddr_01, cutEndString((String) detail.get("SHIP_TO_ADDR_01"), SHIP_TO_ADDR_01_LEN));
            // 2019/05/16 QC#50048 Mod End
            ZYPEZDItemValueSetter.setValue(inTMsg.shipToAddr_02, (String) detail.get("SHIP_TO_ADDR_02"));

            String trxDtTxt = (String) detail.get("TRX_DT_TXT");
            BigDecimal rmaVldDaysAot = (BigDecimal) detail.get("RMA_VLD_DAYS_AOT");
            if (ZYPCommonFunc.hasValue(trxDtTxt)
                    && ZYPCommonFunc.hasValue(rmaVldDaysAot)) {
                String trxDt = ZYPDateUtil.DateFormatter(trxDtTxt, "MM/dd/yyyy", "yyyyMMdd");
                String rtrnExprDtTxt = ZYPDateUtil.addDays(trxDt, rmaVldDaysAot.intValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.rtrnExprDtTxt, ZYPDateUtil.DateFormatter(rtrnExprDtTxt, "yyyyMMdd", "MM/dd/yyyy"));

            } else if (ZYPCommonFunc.hasValue(trxDtTxt)
                    && !ZYPCommonFunc.hasValue(rmaVldDaysAot)) {
                ZYPEZDItemValueSetter.setValue(inTMsg.rtrnExprDtTxt, trxDtTxt);

            }

            // Comment Value
            String noteTxt01 = (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_01");
            String rtrnAuthRptNoteTxt = new String();
            StringBuilder sb = new StringBuilder();
            if (ZYPCommonFunc.hasValue(rmaVldDaysAot)
                    && ZYPCommonFunc.hasValue(noteTxt01)) {
                // 2018/05/21 QC#22139-2 Mod Start
                //sb.append(rmaVldDaysAot).append(" ").append(noteTxt01);
                sb.append("(").append(rmaVldDaysAot).append(" ").append(noteTxt01).append(")");
                // 2018/05/21 QC#22139-2 Mod End
                int digitOfNoteTxt01 = inTMsg.getAttr("rtrnAuthRptNoteTxt_01").getDigit();
                if (sb.length() > digitOfNoteTxt01) {
                    rtrnAuthRptNoteTxt = sb.substring(0, digitOfNoteTxt01);
                } else {
                    rtrnAuthRptNoteTxt = sb.toString();
                }

            } else if (ZYPCommonFunc.hasValue(rmaVldDaysAot)) {
                rtrnAuthRptNoteTxt = rmaVldDaysAot.toString();

            } else if (ZYPCommonFunc.hasValue(noteTxt01)){
                rtrnAuthRptNoteTxt = noteTxt01;

            }

            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthCtacTelNum, (String) detail.get("RTRN_AUTH_CTAC_TEL_NUM"));

            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthRptNoteTxt_01, rtrnAuthRptNoteTxt);

            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthRptNoteTxt_02, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_02"));
            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthRptNoteTxt_03, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_03"));
            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthRptNoteTxt_04, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_04"));
            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthRptNoteTxt_05, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_05"));
            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthRptNoteTxt_06, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_06"));
            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthRptNoteTxt_07, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_07"));
            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthRptNoteTxt_08, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_08"));
            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthRptNoteTxt_09, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_09"));

            if (RTRN_BY_CUST_CD.equals(param.rmaRptTpCd.getValue())) {
                // 2018/12/06 QC#28694 Mod Start
                //ZYPEZDItemValueSetter.setValue(inTMsg.rtrnToLocNm, (String) detail.get("RTRN_TO_LOC_NM"));
                String rtrnToLocNm = ZYPCodeDataUtil.getVarCharConstValue("PRINT_RPT_RTRN_TO", param.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.rtrnToLocNm, rtrnToLocNm);
                // 2018/12/06 QC#28694 Mod End
                // 2019/05/16 QC#50048 Mod Start
                //ZYPEZDItemValueSetter.setValue(inTMsg.rtrnToAddr_01, (String) detail.get("RTRN_TO_ADDR_01"));
                ZYPEZDItemValueSetter.setValue(inTMsg.rtrnToAddr_01, cutEndString((String) detail.get("RTRN_TO_ADDR_01"), RTRN_TO_ADDR_01_LEN));
                // 2019/05/16 QC#50048 Mod End
                ZYPEZDItemValueSetter.setValue(inTMsg.rtrnToAddr_02, (String) detail.get("RTRN_TO_ADDR_02"));
            } else {
                ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthRptNoteTxt_10, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_10"));
            }

            ZYPEZDItemValueSetter.setValue(inTMsg.rtrnAuthCmntTxt_01, param.rtrnAuthCmntTxt);

            // line Value
            // 2019/05/16 QC#50048 Mod Start
            //ZYPEZDItemValueSetter.setValue(inTMsg.cpoWrkLineNum, (String) detail.get("CPO_WRK_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoWrkLineNum, cutEndString((String) detail.get("CPO_WRK_LINE_NUM"), CPO_WRK_LINE_NUM_LEN));
            // 2019/05/16 QC#50048 Mod End
            ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, (String) detail.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(inTMsg.mnfItemCd, (String) detail.get("MNF_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(inTMsg.mdseNm, (String) detail.get("MDSE_NM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.mdseDescShortTxt, (String) detail.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.pkgUomCd, (String) detail.get("PKG_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(inTMsg.ordQtyTxt, (String) detail.get("ORD_QTY_TXT"));

            S21ApiTBLAccessor.insert(inTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                msgMap.addXxMsgId("");
                return true;
            }
        }

        return false;
    }

    // 2019/05/16 QC#50048 Mod Start
    /**
     * String cutter(Set maxsize, after cut!)
     * @param inputText
     * @param max size
     * @return cutted String
     */
    private static String cutEndString(String inputText, int length) {
        if (inputText == null || inputText.length() <= length) {
            return inputText;
        } else {
            return inputText.substring(0, length);
        }
    }
    // 2019/05/16 QC#50048 Mod End
}
