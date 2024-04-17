/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC169001;

import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.BILL_TO_ADDR_01_LEN;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.COMMA_FMT;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.COMMA_FMT_SQUARE_FEET;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.CPO_WRK_LINE_NUM_LEN;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.DLR;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.KEY_NWZC1690_GLBL_CMPY_NM;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.KEY_OUTPUT_CONTACT_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.LINE_FEED_CODE_CR;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.LINE_FEED_CODE_CRLF;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.LINE_FEED_CODE_LF;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.NWZM0789E;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.NWZM1782E;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.NWZM1783E;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.NWZM1784E;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.NWZM1785E;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.NWZM1787E;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.NWZM1788E;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.NWZM1789E;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.NWZM1790E;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.NWZM1792E;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.NWZM1793E;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.PCT;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.PCT_100;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.SCALE_2;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.SCALE_3;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.SELL_TO_ADDR_01_LEN;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.SHIP_TO_ADDR_01_LEN;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.SHPG_CMNT_TXT_LEN;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.SHPG_CMNT_TXT_MAX_LEN;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.SPACE;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.TOTAL_AMOUNT;
import static com.canon.cusa.s21.api.NWZ.NWZC169001.constant.NWZC169001Constant.ZERO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import business.db.MDSETMsg;
import business.db.ORD_CONF_RPT_WRKTMsg;
import business.parts.NWZC169001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EASY_PACK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_RPT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;

/**
 *<pre>
 * NWZC169001
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/07   CUSA            T.Murai         Create          N/A
 * 2016/08/26   Fujitsu         Y.Kanefusa      Update          S21_NA#13483
 * 2016/10/18   Fujitsu         W.Honda         Update          S21_NA#15076
 * 2017/07/05   Fujitsu         M.Yamada        Update          S21_NA#19675
 * 2017/08/02   Fujitsu         S.Takami        Update          S21_NA#20331
 * 2017/10/23   Hitachi         J.Kim           Update          QC#21172
 * 2017/10/26   Hitachi         J.Kim           Update          QC#21117
 * 2018/05/09   Fujitsu         W.Honda         Update          QC#22139
 * 2018/05/22   Fujitsu         T.Aoi           Update          QC#22139-2
 * 2018/05/22   Fujitsu         T.Aoi           Update          QC#21841
 * 2018/08/03   Fujitsu         Y.Kanefusa      Update          S21_NA#27479
 * 2018/08/14   Fujitsu         M.Ishii         Update          QC#26466
 * 2018/09/26   Fujitsu         Hd.Sugawara     Update          QC#28486
 * 2018/11/13   Fujitsu         S.Kosaka        Update          QC#29006
 * 2018/11/15   Fujitsu         S.Kosaka        Update          QC#29269
 * 2018/12/06   Fujitsu         C.Hara          Update          QC#27362
 * 2019/02/28   Fujitsu         K.Ishizuka      Update          QC#30290
 * 2019/05/15   Fujitsu         S.Kosaka        Update          QC#50048
 * 2023/07/28   Hitachi         R.Takau         Update          QC#60676
 *</pre>
 */
public class NWZC169001 extends S21ApiCommonBase {

    /** Supply Quote Report Type Code (Mode:Quote) */
    public static final String QUOTE_CD = "1";

    /** Supply Quote Report Type Code (Mode:Order Confirmation) */
    public static final String ORDER_CONF_CD = "2";

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NWZC169001() {

        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Supply Quote Report Creation API
     * @param param NWZC169001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC169001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        doProcess(msgMap);

        msgMap.flush();
    }

    /**
     * Supply Quote Report Creation API.execute(List)
     * @param params List<NWZC169001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC169001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NWZC169001PMsg param : params) {
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

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        if (ZYPConstant.FLG_ON_Y.equals(param.delFlg.getValue())) {
            deletePrintWorkTable(msgMap);
        }

        String outputCtacTpCd = ZYPCodeDataUtil.getVarCharConstValue(KEY_OUTPUT_CONTACT_CD, param.glblCmpyCd.getValue());

        List<Map<String, Object>> detailList = null;
        if (QUOTE_CD.equals(param.splyQuoteRptTpCd.getValue())) {
            detailList = getQuoteHeader(msgMap, outputCtacTpCd);
            if (detailList == null || detailList.size() <= 0) {
                msgMap.addXxMsgId(NWZM1788E);
                return;
            }
        } else {
            detailList = getOrderConfHeader(msgMap, outputCtacTpCd);
            if (detailList == null || detailList.size() <= 0) {
                msgMap.addXxMsgId(NWZM1789E);
                return;
            }
            // QC#29006 2016/11/13 Add Start
            detailList = makeOrderConfHeader(detailList);
            // QC#29006 2016/11/13 Add End
        }

        List<Map<String, Object>> chargeList = null;
        if (QUOTE_CD.equals(param.splyQuoteRptTpCd.getValue())) {
            chargeList = getQuoteCharge(msgMap);
            if (chargeList == null || chargeList.size() <= 0) {
                chargeList = new ArrayList<Map<String, Object>>();
            }
        } else {
            chargeList = getOrderConfCharge(msgMap);
            if (chargeList == null || chargeList.size() <= 0) {
                chargeList = new ArrayList<Map<String, Object>>();
            }
        }

        List<Map<String, Object>> taxList = null;
        if (QUOTE_CD.equals(param.splyQuoteRptTpCd.getValue())) {
            taxList = getQuoteTax(msgMap);
        } else {
            taxList = getOrderConfTax(msgMap);
        }

        if (insertDetail(detailList, msgMap)) {
            return;
        }

        if (insertCharge(detailList, chargeList, msgMap)) {
            return;
        }

        if (insertTax(detailList, taxList, msgMap)) {
            return;
        }

        if (insertTotal(detailList, chargeList, taxList, msgMap)) {
            return;
        }

        if (insertComment(detailList, msgMap)) {
            return;
        }
    }

    /**
     * paramCheck
     * @param msgMap S21ApiMessageMap
     */
    private boolean paramCheck(S21ApiMessageMap msgMap) {

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0789E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.splyQuoteRptTpCd)) {
            msgMap.addXxMsgId(NWZM1792E);
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

        if (param.splyQuoteRptTpCd.getValue().equals(QUOTE_CD)) {
            if (!ZYPCommonFunc.hasValue(param.splyQuoteNum)) {
                msgMap.addXxMsgId(NWZM1784E);
                return false;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(param.cpoOrdNum)) {
                msgMap.addXxMsgId(NWZM1785E);
                return false;
            }
        }

        // Default
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

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("bizAppId", param.bizAppId.getValue());
        mapParam.put("otptOpCd", param.otptOpCd.getValue());
        if (QUOTE_CD.equals(param.splyQuoteRptTpCd.getValue())) {
            mapParam.put("trxHdrNum", param.splyQuoteNum.getValue());
        } else {
            mapParam.put("trxHdrNum", param.cpoOrdNum.getValue());
        }

        List<Map<String, Object>> delList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDeleteWrk", mapParam);

        if (delList != null && delList.size() > 0) {
            for (Map<String, Object> map : delList) {
                if (map.get("ORD_CONF_RPT_WRK_PK") != null) {
                    ORD_CONF_RPT_WRKTMsg inTMsg = new ORD_CONF_RPT_WRKTMsg();
                    inTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    inTMsg.ordConfRptWrkPk.setValue((java.math.BigDecimal) map.get("ORD_CONF_RPT_WRK_PK"));
                    ORD_CONF_RPT_WRKTMsg outTMsg = (ORD_CONF_RPT_WRKTMsg) S21ApiTBLAccessor.findByKey(inTMsg);

                    if (outTMsg != null) {
                        S21ApiTBLAccessor.remove(outTMsg);
                    }
                }
            }
        }
    }

    /**
     * getQuoteHeader
     * @param msgMap S21ApiMessageMap
     * @param outputCtacTpCd Output Contact Type Code
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getQuoteHeader(S21ApiMessageMap msgMap, String outputCtacTpCd) {

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("splyQuoteNum", param.splyQuoteNum.getValue());
        mapParam.put("cancelSts", SPLY_QUOTE_STS.CANCELLED);
        mapParam.put("splyQuoteRptTpCd", SPLY_QUOTE_RPT_TP.QUOTE);
        mapParam.put("glblCmpyNm", KEY_NWZC1690_GLBL_CMPY_NM);
        mapParam.put("outputCtacTpCd", outputCtacTpCd);
        // START 2017/10/23 J.Kim [QC#21172,ADD]
        mapParam.put("frtCondCd", FRT_COND.COLLECT);
        // END 2017/10/23 J.Kim [QC#21172,ADD]
        // START 2018/05/11 W.Honda [QC#22139,ADD]
        mapParam.put("flgY", ZYPConstant.FLG_ON_Y);
        mapParam.put("flgN", ZYPConstant.FLG_OFF_N);
        mapParam.put("ordCatgCtxTpCdeasyPack", ORD_CATG_CTX_TP.EASY_PAC1);
        // END 2018/05/11 W.Honda [QC#22139,ADD]

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getQuoteHeader", mapParam);
    }

    /**
     * getQuoteCharge
     * @param msgMap S21ApiMessageMap
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getQuoteCharge(S21ApiMessageMap msgMap) {

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("splyQuoteNum", param.splyQuoteNum.getValue());
        mapParam.put("cancelSts", SPLY_QUOTE_STS.CANCELLED);
        // QC#27479 2018/08/03 Add Start
        // QC#13483 2016/08/26 Mod Start
        // mapParam.put("prcDtlGrpCd", PRC_DTL_GRP.FREIGHT);
        //mapParam.put("prcDtlGrpFrt", PRC_DTL_GRP.FREIGHT);
        //mapParam.put("prcDtlGrpRnd1", PRC_DTL_GRP.ROUNDING_FACTOR_1);
        // QC#13483 2016/08/26 Mod End
        // 2018/05/22 QC#21841 Add Start
        //mapParam.put("prcDtlGrpHndf", PRC_DTL_GRP.HANDLING_FEE);
        //mapParam.put("prcDtlGrpFsc", PRC_DTL_GRP.FUEL_SURCHARGE);
        //mapParam.put("prcDtlGrpShpf", PRC_DTL_GRP.SHIPPING_FEE);
        //mapParam.put("prcDtlGrpRnd2", PRC_DTL_GRP.ROUNDING_FACTOR_2);
        // 2018/05/22 QC#21841 Add End
        List<String> prcDtlGrpCdList = new ArrayList<String>();
        prcDtlGrpCdList.add(PRC_DTL_GRP.FREIGHT);
        prcDtlGrpCdList.add(PRC_DTL_GRP.ROUNDING_FACTOR_1);
        prcDtlGrpCdList.add(PRC_DTL_GRP.HANDLING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.FUEL_SURCHARGE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.SHIPPING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.RESTOCKING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.ROUNDING_FACTOR_2);
        mapParam.put("prcDtlGrpCdList",     prcDtlGrpCdList);
        // QC#27479 2018/08/03 Add End

        // QC#27479 2018/08/03 Add End
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getQuoteCharge", mapParam);
    }

    /**
     * getQuoteTax
     * @param msgMap S21ApiMessageMap
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getQuoteTax(S21ApiMessageMap msgMap) {

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("splyQuoteNum", param.splyQuoteNum.getValue());
        mapParam.put("cancelSts", SPLY_QUOTE_STS.CANCELLED);
        mapParam.put("prcDtlGrpCd", PRC_DTL_GRP.TAX);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getQuoteTax", mapParam);
    }

    // START 2018/05/11 W.Honda [QC#22139,ADD]
    /**
     * getQuoteEasyPack
     * @param msgMap S21ApiMessageMap
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getQuoteEasyPacMlyComitQty(S21ApiMessageMap msgMap) {

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("splyQuoteNum", param.splyQuoteNum.getValue());
        mapParam.put("slsDt", ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue()));

        return (Map<String, Object>) ssmBatchClient.queryObject("getQuoteEasyPacMlyComitQty", mapParam);
    }
    // END 2018/05/11 W.Honda [QC#22139,ADD]

    /**
     * getOrderConfHeader
     * @param msgMap S21ApiMessageMap
     * @param outputCtacTpCd Output Contact Type Code
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getOrderConfHeader(S21ApiMessageMap msgMap, String outputCtacTpCd) {

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("cpoOrdNum", param.cpoOrdNum.getValue());
        mapParam.put("splyQuoteRptTpCd", SPLY_QUOTE_RPT_TP.ORDER_CONF);
        mapParam.put("glblCmpyNm", KEY_NWZC1690_GLBL_CMPY_NM);
        mapParam.put("outputCtacTpCd", outputCtacTpCd);
        // Del Start 2018/09/26 QC#28486
        //mapParam.put("txtTpCd1", TXT_TP.ORDER_REMARKS);
        //mapParam.put("txtTpCd2", TXT_TP.S_OR_O_COMMENT);
        //mapParam.put("txtTpCd3", TXT_TP.INVOICE_COMMENT);
        //mapParam.put("txtTpCd4", TXT_TP.CASEMARK_COMMENT);
        // Del End 2018/09/26 QC#28486
        // START 2017/10/23 J.Kim [QC#21172,ADD]
        mapParam.put("frtCondCd", FRT_COND.COLLECT);
        // END 2017/10/23 J.Kim [QC#21172,ADD]
        // START 2018/05/11 W.Honda [QC#22139,ADD]
        mapParam.put("flgY", ZYPConstant.FLG_ON_Y);
        mapParam.put("flgN", ZYPConstant.FLG_OFF_N);
        mapParam.put("ordCatgCtxTpCdeasyPack", ORD_CATG_CTX_TP.EASY_PAC1);
        // END 2018/05/11 W.Honda [QC#22139,ADD]

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOrderConfHeader", mapParam);
    }

    /**
     * getOrderConfCharge
     * @param msgMap S21ApiMessageMap
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getOrderConfCharge(S21ApiMessageMap msgMap) {

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("cpoOrdNum", param.cpoOrdNum.getValue());
        // QC#13483 2016/08/26 Mod Start
        //mapParam.put("prcDtlGrpCd", PRC_DTL_GRP.FREIGHT);
        mapParam.put("prcDtlGrpFrt", PRC_DTL_GRP.FREIGHT);
        mapParam.put("prcDtlGrpRnd1", PRC_DTL_GRP.ROUNDING_FACTOR_1);
        // QC#13483 2016/08/26 Mod End
        // 2018/05/22 QC#21841 Add Start
        mapParam.put("prcDtlGrpHndf", PRC_DTL_GRP.HANDLING_FEE);
        mapParam.put("prcDtlGrpFsc", PRC_DTL_GRP.FUEL_SURCHARGE);
        mapParam.put("prcDtlGrpShpf", PRC_DTL_GRP.SHIPPING_FEE);
        mapParam.put("prcDtlGrpRnd2", PRC_DTL_GRP.ROUNDING_FACTOR_2);
        // 2018/05/22 QC#21841 Add End

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOrderConfCharge", mapParam);
    }

    /**
     * getOrderConfTax
     * @param msgMap S21ApiMessageMap
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getOrderConfTax(S21ApiMessageMap msgMap) {

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("cpoOrdNum", param.cpoOrdNum.getValue());
        mapParam.put("prcDtlGrpCd", PRC_DTL_GRP.TAX);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOrderConfTax", mapParam);
    }

    // START 2018/05/11 W.Honda [QC#22139,ADD]
    /**
     * getOrderEasyPacMlyComitQty
     * @param msgMap S21ApiMessageMap
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getOrderEasyPacMlyComitQty(S21ApiMessageMap msgMap) {

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("cpoOrdNum", param.cpoOrdNum.getValue());
        mapParam.put("slsDt", ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue()));

        return (Map<String, Object>) ssmBatchClient.queryObject("getOrderEasyPacMlyComitQty", mapParam);
    }
    // END 2018/05/11 W.Honda [QC#22139,ADD]

    /**
     * insertDetail
     * @param detailList List<Map< ? , ? >>
     * @param msgMap S21ApiMessageMap
     */
    private boolean insertDetail(List<Map<String, Object>> detailList, S21ApiMessageMap msgMap) {

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();

        for (Map<String, Object> detail : detailList) {
            ORD_CONF_RPT_WRKTMsg inTMsg = new ORD_CONF_RPT_WRKTMsg();
            setHeader(inTMsg, param, detail);
            BigDecimal prcListPrcAmt = (BigDecimal) detail.get("PRC_LIST_PRC_AMT");
            BigDecimal netUnitPrcAmt = (BigDecimal) detail.get("NET_UNIT_PRC_AMT");
            BigDecimal discAmt = prcListPrcAmt.subtract(netUnitPrcAmt);
            // QC#26466 2018/08/14 Add Start
            String mdseCd = convertMdseCd(param.glblCmpyCd.getValue(), (String) detail.get("MDSE_CD"));
            // QC#26466 2018/08/14 Add End

            // 2019/05/15 QC#50048 Mod Start
            //ZYPEZDItemValueSetter.setValue(inTMsg.cpoWrkLineNum, (String) detail.get("CPO_WRK_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoWrkLineNum, cutEndString((String) detail.get("CPO_WRK_LINE_NUM"), CPO_WRK_LINE_NUM_LEN));
            // 2019/05/15 QC#50048 Mod End
            // QC#15076 2016/10/18 Mod Start
//            ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, (String) detail.get("MDSE_CD"));
            // QC#26466 2018/08/14 Mod Start
//            ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, (String) detail.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, mdseCd);
            // QC#26466 2018/08/14 Mod End
            ZYPEZDItemValueSetter.setValue(inTMsg.mnfItemCd, (String) detail.get("MNF_ITEM_CD"));
            // QC#15076 2016/10/18 Mod End
            ZYPEZDItemValueSetter.setValue(inTMsg.mdseDescShortTxt, (String) detail.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.pkgUomCd, (String) detail.get("PKG_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(inTMsg.ordQtyTxt, (String) detail.get("ORD_QTY_TXT"));
            ZYPEZDItemValueSetter.setValue(inTMsg.prcListPrcAmtTxt, convertCommaPrc(prcListPrcAmt, null));
            ZYPEZDItemValueSetter.setValue(inTMsg.discRatePrintTxt, calcDiscPct(prcListPrcAmt, discAmt));
            ZYPEZDItemValueSetter.setValue(inTMsg.netUnitPrcAmtTxt, convertCommaPrc(netUnitPrcAmt, null));
            ZYPEZDItemValueSetter.setValue(inTMsg.dtlDealNetAmtTxt, calcSubTotalForTxt(netUnitPrcAmt, (BigDecimal) detail.get("ORD_QTY")));

            // START 2018/05/11 W.Honda [QC#22139,ADD]
            BigDecimal easyPacQty = (BigDecimal) detail.get("EASY_PAC_QTY");
            if (ZYPCommonFunc.hasValue(easyPacQty)) {
                // 2018/05/22 QC#22139 Mod Start
                //ZYPEZDItemValueSetter.setValue(inTMsg.easyPacQtyTxt, easyPacQty.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.easyPacQtyTxt, convertCommaSquareFeet(easyPacQty, null));
                // 2018/05/22 QC#22139 Mod End
            }
            // END 2018/05/11 W.Honda [QC#22139,ADD]

            S21ApiTBLAccessor.insert(inTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM1787E);
                return true;
            }
        }

        return false;
    }

    /**
     * insertCharge
     * @param detailList List<Map<String, Object>>
     * @param chargeList List<Map<String, String>>
     * @param msgMap S21ApiMessageMap
     */
    private boolean insertCharge(List<Map<String, Object>> detailList, List<Map<String, Object>> chargeList, S21ApiMessageMap msgMap) {

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();
        Map<String, Object> detail = detailList.get(0);

        for (Map<String, Object> charge : chargeList) {
            ORD_CONF_RPT_WRKTMsg inTMsg = new ORD_CONF_RPT_WRKTMsg();
            setHeader(inTMsg, param, detail);
            ZYPEZDItemValueSetter.setValue(inTMsg.prcRuleDtlChrgNm, (String) charge.get("PRC_RULE_DTL_CHRG_NM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.taxChrgAmtPrintTxt, convertCommaPrc((BigDecimal) charge.get("CHRG_AMT"), null));

            S21ApiTBLAccessor.insert(inTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM1787E);
                return true;
            }
        }

        return false;
    }

    /**
     * insertTax
     * @param detailList List<Map<String, Object>>
     * @param taxList List<Map<String, Object>>
     * @param msgMap S21ApiMessageMap
     */
    private boolean insertTax(List<Map<String, Object>> detailList, List<Map<String, Object>> taxList, S21ApiMessageMap msgMap) {

        ORD_CONF_RPT_WRKTMsg inTMsg = new ORD_CONF_RPT_WRKTMsg();
        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();
        Map<String, Object> detail = detailList.get(0);

        setHeader(inTMsg, param, detail);

        // Tax
        if (taxList != null && taxList.size() > 0) {
            Map<String, Object> tax = taxList.get(0);
            ZYPEZDItemValueSetter.setValue(inTMsg.prcRuleDtlChrgNm, (String) tax.get("PRC_RULE_DTL_CHRG_NM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.taxChrgAmtPrintTxt, convertCommaPrc((BigDecimal) tax.get("TAX_AMT"), null));
            ZYPEZDItemValueSetter.setValue(inTMsg.taxChrgRatePrintTxt, convertCommaPrc((BigDecimal) tax.get("TAX_RATE"), null));
        }

        S21ApiTBLAccessor.insert(inTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1787E);
            return true;
        }

        return false;
    }

    /**
     * insertComment
     * @param detailList List<Map<String, Object>>
     * @param msgMap S21ApiMessageMap
     */
    private boolean insertComment(List<Map<String, Object>> detailList, S21ApiMessageMap msgMap) {

        ORD_CONF_RPT_WRKTMsg inTMsg = new ORD_CONF_RPT_WRKTMsg();
        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();
        Map<String, Object> detail = detailList.get(0);
        setHeader(inTMsg, param, detail);

        // Comment
        // START 2017/10/26 J.Kim [QC#21117,DEL]
        // ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteCmntTxt, (String) detail.get("SPLY_QUOTE_CMNT_TXT"));
        // END 2017/10/26 J.Kim [QC#21117,DEL]
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteRptNoteTxt_01, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_01"));
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteRptNoteTxt_02, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_02"));
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteRptNoteTxt_03, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_03"));
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteRptNoteTxt_04, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_04"));
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteRptNoteTxt_05, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_05"));
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteRptNoteTxt_06, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_06"));
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteRptNoteTxt_07, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_07"));
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteRptNoteTxt_08, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_08"));
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteRptNoteTxt_09, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_09"));
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteRptNoteTxt_10, (String) detail.get("SPLY_QUOTE_RPT_NOTE_TXT_10"));

        // START 2018/05/11 W.Honda [QC#22139,ADD]
        String easyPackFlg = (String) detailList.get(0).get("EASY_PACK_FLG");
        if (ZYPConstant.FLG_ON_Y.equals(easyPackFlg)) {
            // Total Square Feet This Order
            BigDecimal total = new BigDecimal(0);
            for (Map<String, Object> detailForEasyPac : detailList) {
                BigDecimal easyPackQty = (BigDecimal) detailForEasyPac.get("EASY_PAC_QTY");
                if (ZYPCommonFunc.hasValue(easyPackQty)) {
                    total = total.add(easyPackQty);
                }
            }

            // 2018/05/22 QC#22139-2 Mod Start
            //ZYPEZDItemValueSetter.setValue(inTMsg.easyPacTotQtyTxt, total.toString());
            ZYPEZDItemValueSetter.setValue(inTMsg.easyPacTotQtyTxt, convertCommaSquareFeet(total, null));
            // 2018/05/22 QC#22139-2 Mod End

            // Monthly Quota Commitment
            Map<String, Object> easyPacMap = null;
            if (QUOTE_CD.equals(param.splyQuoteRptTpCd.getValue())) {
                easyPacMap = getQuoteEasyPacMlyComitQty(msgMap);
            } else {
                easyPacMap = getOrderEasyPacMlyComitQty(msgMap);
            }

            BigDecimal easyPacMlyComitQty = (BigDecimal) easyPacMap.get("SPLY_PGM_MLY_QUOT_QTY");
            String billToCustAcctCd = (String) easyPacMap.get("BILL_TO_CUST_ACCT_CD");
            if (ZYPCommonFunc.hasValue(easyPacMlyComitQty)) {
                // 2018/05/22 QC#22139-2 Mod Start
                //ZYPEZDItemValueSetter.setValue(inTMsg.easyPacMlyComitQtyTxt, easyPacMlyComitQty.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.easyPacMlyComitQtyTxt, convertCommaSquareFeet(easyPacMlyComitQty, null));
                // 2018/05/22 QC#22139-2 Mod End
            } else {
                ZYPEZDItemValueSetter.setValue(inTMsg.easyPacMlyComitQtyTxt, BigDecimal.ZERO.toString());
            }

            // Amount Shipped MTD
            Map<String, Object> mapParam = new HashMap<String, Object>();
            mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            StringBuilder slsDtLike = new StringBuilder();
            slsDtLike.append(ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue()).substring(0, 6)).append("%");
            mapParam.put("slsDtLike", slsDtLike.toString());
            mapParam.put("slsDt", ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue()));
            mapParam.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
            mapParam.put("easyPackTpCd", EASY_PACK_TP.EASYPAC);
            mapParam.put("billToCustAcctCd", billToCustAcctCd);

            List<String> ordCatgCtxTpCdList = new ArrayList<String>();
            ordCatgCtxTpCdList.add(ORD_CATG_CTX_TP.EASY_PAC1);
            ordCatgCtxTpCdList.add(ORD_CATG_CTX_TP.EASY_PAC1_RETURN);
            mapParam.put("ordCatgCtxTpCdList", ordCatgCtxTpCdList);
            mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            mapParam.put("cpoOrdNum", param.cpoOrdNum.getValue());
            mapParam.put("slsDt", ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue()));

            PreparedStatement stmt = null;
            ResultSet rsSet = null;

            BigDecimal itemSumAmt = BigDecimal.ZERO;
            BigDecimal itemRtnSumAmt = BigDecimal.ZERO;
            BigDecimal totalAmt = BigDecimal.ZERO;

            try {
                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
                execParam.setFetchSize(1000);
                execParam.setMaxRows(0);
                execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
                execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
                execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

                S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
                stmt = ssmLLClient.createPreparedStatement("getEasyPacShipQty", mapParam, execParam);
                rsSet = stmt.executeQuery();

                while (rsSet.next()) {
                    String invTpCd = rsSet.getString("INV_TP_CD");
                    BigDecimal ordQty = rsSet.getBigDecimal("ORD_QTY");
                    BigDecimal areaOfPaperNum = rsSet.getBigDecimal("AREA_OF_PAPER_NUM");
                    if (areaOfPaperNum == null) {
                        areaOfPaperNum = BigDecimal.ZERO;
                    }
                    BigDecimal splyUseQty = ordQty.multiply(areaOfPaperNum);

                    if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
                        itemRtnSumAmt = itemRtnSumAmt.add(splyUseQty);
                    } else {
                        itemSumAmt = itemSumAmt.add(splyUseQty);
                    }

                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
            }

            totalAmt = totalAmt.add(itemSumAmt).subtract(itemRtnSumAmt);
            if (ZYPCommonFunc.hasValue(totalAmt)) {
                // 2018/05/22 QC#22139-2 Mod Start
                //ZYPEZDItemValueSetter.setValue(inTMsg.easyPacShipSumQtyTxt, totalAmt.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.easyPacShipSumQtyTxt, convertCommaSquareFeet(totalAmt, null));
                // 2018/05/22 QC#22139-2 Mod End
            } else {
                ZYPEZDItemValueSetter.setValue(inTMsg.easyPacShipSumQtyTxt, BigDecimal.ZERO.toString());
            }
        }
        // END 2018/05/11 W.Honda [QC#22139,ADD]

        S21ApiTBLAccessor.insert(inTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1787E);
            return true;
        }

        return false;
    }

    /**
     * insertTotal
     * @param detailList List<Map<String, Object>>
     * @param chargeList List<Map<String, Object>>
     * @param taxList List<Map<String, Object>>
     * @param msgMap S21ApiMessageMap
     */
    private boolean insertTotal(List<Map<String, Object>> detailList, List<Map<String, Object>> chargeList, List<Map<String, Object>> taxList, S21ApiMessageMap msgMap) {

        BigDecimal total = new BigDecimal(0);
        for (Map<String, Object> detail : detailList) {
            BigDecimal netUnitPrcAmt = (BigDecimal) detail.get("NET_UNIT_PRC_AMT");
            BigDecimal ordQty = (BigDecimal) detail.get("ORD_QTY");
            BigDecimal subTotal = ordQty.multiply(netUnitPrcAmt);
            if (ZYPCommonFunc.hasValue(subTotal)) {
                total = total.add(subTotal);
            }
        }

        for (Map<String, Object> charge : chargeList) {
            BigDecimal chrgAmt = (BigDecimal) charge.get("CHRG_AMT");
            if (ZYPCommonFunc.hasValue(chrgAmt)) {
                total = total.add(chrgAmt);
            }
        }

        if (taxList != null && taxList.size() > 0) {
            BigDecimal taxAmt = (BigDecimal) taxList.get(0).get("TAX_AMT");
            if (ZYPCommonFunc.hasValue(taxAmt)) {
                total = total.add(taxAmt);
            }
        }

        NWZC169001PMsg param = (NWZC169001PMsg) msgMap.getPmsg();
        ORD_CONF_RPT_WRKTMsg inTMsg = new ORD_CONF_RPT_WRKTMsg();
        Map<String, Object> detail = detailList.get(0);

        setHeader(inTMsg, param, detail);
        ZYPEZDItemValueSetter.setValue(inTMsg.totAmtPrintNm, TOTAL_AMOUNT);
        ZYPEZDItemValueSetter.setValue(inTMsg.totAmtPrintTxt, convertCommaPrc(total, DLR));

        S21ApiTBLAccessor.insert(inTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1787E);
            return true;
        }

        return false;
    }

    /**
     * setHeader
     * @param inTMsg ORD_CONF_RPT_WRKTMsg
     * @param param NWZC169001PMsg
     * @param detail Map<String, Object>
     */
    private void setHeader(ORD_CONF_RPT_WRKTMsg inTMsg, NWZC169001PMsg param, Map<String, Object> detail) {

        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.ordConfRptWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_CONF_RPT_WRK_SQ));
        ZYPEZDItemValueSetter.setValue(inTMsg.bizAppId, param.bizAppId);
        ZYPEZDItemValueSetter.setValue(inTMsg.otptOpCd, param.otptOpCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteRptTpCd, param.splyQuoteRptTpCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.lineBizTpCd, param.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyNm, (String) detail.get("GLBL_CMPY_NM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.glblFullCmpyAddr, (String) detail.get("GLBL_FULL_CMPY_ADDR"));
        ZYPEZDItemValueSetter.setValue(inTMsg.rptTtlTxt, (String) detail.get("RPT_TTL_TXT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.trxHdrNum, (String) detail.get("TRX_HDR_NUM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.trxDtTxt, (String) detail.get("TRX_DT_TXT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.splyQuoteVldThruDtTxt, (String) detail.get("SPLY_QUOTE_VLD_THRU_DT_TXT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.custIssPoNum, (String) detail.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.pmtTermCashDiscDescTxt, (String) detail.get("PMT_TERM_CASH_DISC_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.fullAdminPsnNm, (String) detail.get("FULL_ADMIN_PSN_NM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.frtCondNm, (String) detail.get("FRT_COND_NM"));
        // START 2017/10/23 J.Kim [QC#21172,MOD]
        // ZYPEZDItemValueSetter.setValue(inTMsg.shpgSvcLvlNm, (String) detail.get("SHPG_SVC_LVL_NM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.svcLvlRptDescTxt, (String) detail.get("SVC_LVL_RPT_NM"));
        // END 2017/10/23 J.Kim [QC#21172,ADD]
        // START 2017/10/23 J.Kim [QC#21117,MOD]
        // ZYPEZDItemValueSetter.setValue(inTMsg.shipTo01RefCmntTxt, (String) detail.get("SHIP_TO_01_REF_CMNT_TXT"));
        // ZYPEZDItemValueSetter.setValue(inTMsg.shipTo02RefCmntTxt, (String) detail.get("SHIP_TO_02_REF_CMNT_TXT"));
        Map<String, String> shpgCmntTxMapt = editShpgCmntTxt(param, (String) detail.get("SHPG_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.shpgToRefCmntTxt_01, shpgCmntTxMapt.get("SHPG_TO_REF_CMNT_TXT_01"));
        ZYPEZDItemValueSetter.setValue(inTMsg.shpgToRefCmntTxt_02, shpgCmntTxMapt.get("SHPG_TO_REF_CMNT_TXT_02"));
        ZYPEZDItemValueSetter.setValue(inTMsg.shpgToRefCmntTxt_03, shpgCmntTxMapt.get("SHPG_TO_REF_CMNT_TXT_03"));
        // END 2017/10/23 J.Kim [QC#21117,MOD]
        ZYPEZDItemValueSetter.setValue(inTMsg.sellToCustCd, (String) detail.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.sellToLocNm, (String) detail.get("SELL_TO_LOC_NM"));
        // 2019/05/15 QC#50048 Mod Start
        //ZYPEZDItemValueSetter.setValue(inTMsg.sellToAddr_01, (String) detail.get("SELL_TO_ADDR_01"));
        ZYPEZDItemValueSetter.setValue(inTMsg.sellToAddr_01, cutEndString((String) detail.get("SELL_TO_ADDR_01"), SELL_TO_ADDR_01_LEN));
        // 2019/05/15 QC#50048 Mod End
        ZYPEZDItemValueSetter.setValue(inTMsg.sellToAddr_02, (String) detail.get("SELL_TO_ADDR_02"));
        ZYPEZDItemValueSetter.setValue(inTMsg.fullCtacPsnNm, (String) detail.get("FULL_CTAC_PSN_NM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.ctacRptTtlTxt, (String) detail.get("CTAC_RPT_TTL_TXT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.dsCtacPntValTxt, (String) detail.get("DS_CTAC_PNT_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.billToCustCd, (String) detail.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.billToLocNm, (String) detail.get("BILL_TO_LOC_NM"));
        // 2019/05/15 QC#50048 Mod Start
        //ZYPEZDItemValueSetter.setValue(inTMsg.billToAddr_01, (String) detail.get("BILL_TO_ADDR_01"));
        ZYPEZDItemValueSetter.setValue(inTMsg.billToAddr_01, cutEndString((String) detail.get("BILL_TO_ADDR_01"), BILL_TO_ADDR_01_LEN));
        // 2019/05/15 QC#50048 Mod End
        ZYPEZDItemValueSetter.setValue(inTMsg.billToAddr_02, (String) detail.get("BILL_TO_ADDR_02"));
        ZYPEZDItemValueSetter.setValue(inTMsg.shipToCustCd, (String) detail.get("SHIP_TO_CUST_CD"));
        // 2017/08/02 S21_NA#20331 Mod Start
//        ZYPEZDItemValueSetter.setValue(inTMsg.shipToLocNm, (String) detail.get("SHIP_TO_LOC_NM"));
        String shipToLocNm = (String) detail.get("SHIP_TO_LOC_NM");
        if (ZYPCommonFunc.hasValue(shipToLocNm)) {
            int maxLen = inTMsg.getAttr("shipToLocNm").getDigit();
            if (shipToLocNm.length() > maxLen) {
                shipToLocNm = shipToLocNm.substring(0, maxLen);
            }
            ZYPEZDItemValueSetter.setValue(inTMsg.shipToLocNm, shipToLocNm);
        }
        // 2017/08/02 S21_NA#20331 Mod End
        // 2019/05/15 QC#50048 Mod Start
        //ZYPEZDItemValueSetter.setValue(inTMsg.shipToAddr_01, (String) detail.get("SHIP_TO_ADDR_01"));
        ZYPEZDItemValueSetter.setValue(inTMsg.shipToAddr_01, cutEndString((String) detail.get("SHIP_TO_ADDR_01"), SHIP_TO_ADDR_01_LEN));
        // 2019/05/15 QC#50048 Mod End
        ZYPEZDItemValueSetter.setValue(inTMsg.shipToAddr_02, (String) detail.get("SHIP_TO_ADDR_02"));

        // START 2018/05/11 W.Honda [QC#22139,ADD]
        ZYPEZDItemValueSetter.setValue(inTMsg.easyPacFlg, (String) detail.get("EASY_PACK_FLG"));
        if (QUOTE_CD.equals(param.splyQuoteRptTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(inTMsg.quotePrintCmntTxt, (String) detail.get("PRINT_CMNT_TXT"));
        } else {
            ZYPEZDItemValueSetter.setValue(inTMsg.ordPrintCmntTxt, (String) detail.get("PRINT_CMNT_TXT"));
        }
        ZYPEZDItemValueSetter.setValue(inTMsg.shpgCmntPrintCd, (String) detail.get("SHPG_CMNT_PRINT_CD"));
        // END 2018/05/11 W.Honda [QC#22139,ADD]

        // 2018/12/06 QC#27362 Add Start
        ZYPEZDItemValueSetter.setValue(inTMsg.sellToFirstRefCmntTxt, (String) detail.get("SELL_TO_FIRST_REF_CMNT_TXT"));
        // 2018/12/06 QC#27362 Add End

        // START 2023/07/28 R.Takau [QC#60676,ADD]
        ZYPEZDItemValueSetter.setValue(inTMsg.spclHdlgTpDescTxt, (String) detail.get("SPCL_HDLG_TP_DESC_TXT"));
        // END 2023/07/28 R.Takau [QC#60676,ADD]
    }

    /**
     * Calculate Discount Percentage
     * @param dealPrcListPrcAmt BigDecimal
     * @param discAmt BigDecimal
     * @return Discount Percentage
     */
    private String calcDiscPct(BigDecimal dealPrcListPrcAmt, BigDecimal discAmt) {

        if (discAmt == null || BigDecimal.ZERO.compareTo(discAmt) == 0 //
                || dealPrcListPrcAmt == null || BigDecimal.ZERO.compareTo(dealPrcListPrcAmt) == 0) { // QC#19675
            return ZERO + PCT;
        }

        BigDecimal discPrcAmt = dealPrcListPrcAmt.subtract(discAmt);
        BigDecimal discPrcAmtPct = discPrcAmt.divide(dealPrcListPrcAmt, SCALE_3, BigDecimal.ROUND_HALF_UP);
        discPrcAmtPct = PCT_100.multiply(discPrcAmtPct);
        discPrcAmtPct = PCT_100.subtract(discPrcAmtPct);
        discPrcAmtPct = discPrcAmtPct.setScale(SCALE_2, BigDecimal.ROUND_HALF_UP);

        return discPrcAmtPct.toString() + PCT;
    }

    /**
     * Convert Comma Price
     * @param targetPrc Target Price
     * @return Comma Price
     */
    private String convertCommaPrc(BigDecimal targetPrc, String addValue) {

        DecimalFormat commaFmt = new DecimalFormat(COMMA_FMT);

        if (ZYPCommonFunc.hasValue(addValue)) {
            return addValue + commaFmt.format(targetPrc);
        }

        return commaFmt.format(targetPrc);
    }

    // 2018/05/22 QC#22139-2 Add Start
    /**
     * Convert Comma Square Feet
     * @param targetPrc Target Price
     * @return Comma Price
     */
    private String convertCommaSquareFeet(BigDecimal targetQtyTxt, String addValue) {

        DecimalFormat commaFmt = new DecimalFormat(COMMA_FMT_SQUARE_FEET);

        if (ZYPCommonFunc.hasValue(addValue)) {
            return addValue + commaFmt.format(targetQtyTxt);
        }

        return commaFmt.format(targetQtyTxt);
    }
    // 2018/05/22 QC#22139-2 Add End

    /**
     * Calculate Sub Total
     * @param netUnitPrcAmt BigDecimal
     * @param ordQty Order Qty
     * @return Sub Total (String)
     */
    private String calcSubTotalForTxt(BigDecimal netUnitPrcAmt, BigDecimal ordQty) {

        BigDecimal subTotal = ordQty.multiply(netUnitPrcAmt);
        return convertCommaPrc(subTotal, null);
    }

    // START 2017/10/23 J.Kim [QC#21117,ADD]
    /**
     * Edit Shipping Comment Text
     * @param param NWZC169001PMsg
     * @param cmntTxt String
     * @return Map
     */
    private Map<String, String> editShpgCmntTxt(NWZC169001PMsg param, String cmntTxt) {

        Map<String, String> shpgCmntTxt = new HashMap<String, String>();
        String shpgTorefCmntTxt01 = "";
        String shpgTorefCmntTxt02 = "";
        String shpgTorefCmntTxt03 = "";

        if (ZYPCommonFunc.hasValue(cmntTxt)) {
            // String shpgCmntxt = ZYPCommonFunc.replaceAll(cmntTxt, LINE_FEED_CODE, SPACE).trim();
            String shpgCmntxt = replaceShpgCmtTxt(cmntTxt); // 2019/02/28 S21_NA#30290 Mod

            int beginIndexCurrent = 0;
            int endIndexCurrent = SHPG_CMNT_TXT_LEN;
            int endIndex = shpgCmntxt.length();

            if (SHPG_CMNT_TXT_MAX_LEN < endIndex) {
                endIndex = SHPG_CMNT_TXT_MAX_LEN;
            }

            if (endIndex < endIndexCurrent) {
                endIndexCurrent = endIndex;
            }
            shpgTorefCmntTxt01 = shpgCmntxt.substring(beginIndexCurrent, endIndexCurrent);

            if (endIndexCurrent < endIndex) {
                beginIndexCurrent = beginIndexCurrent + SHPG_CMNT_TXT_LEN;
                endIndexCurrent = endIndexCurrent + SHPG_CMNT_TXT_LEN;
                if (endIndex < endIndexCurrent) {
                    endIndexCurrent = endIndex;
                }
                shpgTorefCmntTxt02 = shpgCmntxt.substring(beginIndexCurrent, endIndexCurrent);
            }

            if (endIndexCurrent < endIndex) {
                beginIndexCurrent = beginIndexCurrent + SHPG_CMNT_TXT_LEN;
                endIndexCurrent = endIndex;
                shpgTorefCmntTxt03 = shpgCmntxt.substring(beginIndexCurrent, endIndexCurrent);
            }
        }

        shpgCmntTxt.put("SHPG_TO_REF_CMNT_TXT_01", shpgTorefCmntTxt01);
        shpgCmntTxt.put("SHPG_TO_REF_CMNT_TXT_02", shpgTorefCmntTxt02);
        shpgCmntTxt.put("SHPG_TO_REF_CMNT_TXT_03", shpgTorefCmntTxt03);

        return shpgCmntTxt;
    }
    // END 2017/10/23 J.Kim [QC#21117,ADD]
    
    // QC#26466 2018/08/14 Add Start
    private String convertMdseCd(String glblCmpyCd, String mdseCd) {
        if (mdseCd.length() > 7) {
            String eightDigitMdseCd = mdseCd.substring(0, 8);
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, eightDigitMdseCd);
            if (mdseTMsg != null) {
                return eightDigitMdseCd;
            }
        }
        return mdseCd;
    }
    // QC#26466 2018/08/14 Add End

    // QC#29006 2016/11/13 Add Start
    /**
     * Make order configuration header.
     * @param inputList Get all list from DB
     * @return Maked order configuration header
     */
    private List<Map<String, Object>> makeOrderConfHeader(List<Map<String, Object>> inputList) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        BigDecimal firstCpoConfigPk = (BigDecimal) inputList.get(0).get("DS_CPO_CONFIG_PK");
        BigDecimal cpoConfigPk = null;
        String activeShippingComment = null;
        String shippingComment = null;

        // QC#29269 2016/11/15 Add Start
        if (!ZYPCommonFunc.hasValue(firstCpoConfigPk)) {
            return inputList;
        }
        // QC#29269 2016/11/15 Add End

        // Search active shipping comment.
        for (Map<String, Object> detail : inputList) {
            shippingComment = (String) detail.get("SHPG_CMNT_TXT");
            if (ZYPCommonFunc.hasValue(shippingComment)) {
                activeShippingComment = shippingComment;
                break;
            }
        }

        // Set OrderConfHeader.
        for (Map<String, Object> detail : inputList) {
            cpoConfigPk = (BigDecimal) detail.get("DS_CPO_CONFIG_PK");
            if (firstCpoConfigPk.compareTo(cpoConfigPk) != 0) {
                break;
            }
            detail.put("SHPG_CMNT_TXT", activeShippingComment);
            resultList.add(detail);
        }

        return resultList;
    }
    // QC#29006 2016/11/13 Add End

    // 2019/2/28 S21_NA#30290 Add Start
    /**
     * replace value with regex
     * @param value
     * @param regex 
     * @param replacement 
     * @return replaced value
     */
    private static String replaceAllRegex(String value, String regex, String replacement) {
        if (value == null || value.length() == 0 || regex == null || regex.length() == 0 || replacement == null)
            return "";
        return Pattern.compile(regex).matcher(value).replaceAll(replacement);
    }

    /**
     * replace shipping comment text
     * @param shpgCmtTxt
     * @return replaced shipping comment text
     */
    private static String replaceShpgCmtTxt(String shpgCmtTxt){
        return replaceAllRegex(replaceAllRegex(replaceAllRegex(shpgCmtTxt, LINE_FEED_CODE_CRLF, SPACE), LINE_FEED_CODE_CR, SPACE), LINE_FEED_CODE_LF, SPACE);
    }
    // 2019/2/28 S21_NA#30290 Add End
    // 2019/05/15 QC#50048 Mod Start
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
    // 2019/05/15 QC#50048 Mod End
}
