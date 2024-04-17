/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC036101;

import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.DEFAULT_VERTEX_SLEEP_MSEC;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.DEFAULT_VERTEX_TRY_CNT;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.DIV_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.MAX_SHIP_TO_NAME_LENGTH_FOR_VERTEX;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZC0361_VERTEX_SLEEP_MSEC;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZC0361_VERTEX_TRY_CNT;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM0011E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM0012E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM0021E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM0346E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM0350E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM0368E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM0461E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM0650E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM1154E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM1183E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM1313E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM1314E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM1320E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM1322E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM1324E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM1342E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM1534E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM2231E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.NWZM2232E;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.PERCENT_SCALE;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.PROC_MODE_DISTRIBUTE_TAX;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.PROC_MODE_INVOICE;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.PROC_MODE_QUOTATION;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.TO_PERCENT_VALUE;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.VERTEX_CMPY_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant.VERTEX_ISO_CTRY_CD;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import parts.common.EZDDebugOutput;
import business.db.GLBL_CMPYTMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateInputLinePMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;

import com.canon.cusa.s21.api.NWZ.NWZC036101.cache.DataCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LEASE_PRCH_OPT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ST;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.usa.s21.integration.service.S21ibVertexCalculateTaxService.S21ibVertexCalculateTaxServiceProxy;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.AmountType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.ApplicationData;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.CurrencyType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.CustomerCodeType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.CustomerType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.DistributeTaxRequestType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.DistributeTaxResponseType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.FlexibleCodeField;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.FlexibleFields;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.InvoiceRequestType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.InvoiceResponseType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.JurisdictionLevelCodeType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LineItemDTSIType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LineItemDTSOType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LineItemISIType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LineItemISOType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LineItemQSIType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LineItemQSOType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LocationType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LogEntryType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.MeasureType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.NewOrderRequestElement;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.NewOrderResponseElement;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.Product;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.QuotationRequestType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.QuotationResponseType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.SaleTransactionType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.SellerType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.TaxResultCodeType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.TaxesType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.VertexEnvelopeType;

/**
 * <pre>
 * Tax Calculation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/25   Fujitsu         H.Nakajima      Create          N/A
 * 2015/09/03   Fujitsu         C.Yokoi         Update          N/A
 * 2016/02/24   SRAA            K.Aratani       Update          QC4491
 * 2016/02/26   Fujitsu         H.Nagashima     Update          QC4190
 * 2016/05/19   Fujitsu         Y.Kanefusa      Update          QC#8285
 * 2016/06/06   Fujitsu         Y.Kanefusa      Update          QC#8545
 * 2016/06/06   Fujitsu         Y.Kanefusa      Update          QC#8664
 * 2016/07/11   Fujitsu         T.Yoshida       Update          QC#11618
 * 2016/08/30   Fujitsu         Y.Kanefusa      Update          S21_NA#13877
 * 2017/02/27   Fujitsu         H.Nagashima     Update          QC#16531
 * 2017/05/11   Fujitsu         S.Iidaka        Update          QC#18347
 * 2017/05/26   Fujitsu         M.Yamada        Update          QC#18663
 * 2017/06/26   Fujitsu         M.Yamada        Update          QC#18701
 * 2017/07/18   Hitachi         Y.Takeno        Update          QC#19885
 * 2017/07/24   Fujitsu         Y.Kanefusa      Update          S21_NA#20083
 * 2017/09/19   Fujitsu         Y.Kanefusa      Update          S21_NA#21106
 * 2017/10/17   Fujitsu         Y.Kanefusa      Update          S21_NA#21847
 * 2017/12/19   Fujitsu         Y.Kanefusa      Update          S21_NA#22169
 * 2017/12/22   Fujitsu         M.Ohno          Update          S21_NA#22407
 * 2018/08/31   Fujitsu         Mz.Takahashi    Update          QC#27970
 * 2019/04/10   Fujitsu         Hd.Sugawara     Update          QC#31046
 * 2019/09/21   Fujitsu         S.Takami        Update          QC#53650
 * 2019/10/10   Fujitsu         S.Takami        Update          QC#54078
 * 2020/03/25   Fujitsu         H.Mizukami      Update          QC#56158
 *</pre>
 */
public class NWZC036101 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** Global Company. */
    private GLBL_CMPYTMsg glblCmpy = null;

    /** Date Format */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    // QC#8285 2016/05/23 Add Start
    /** Company Code */
    private String CMPY_CD = null;

    /** ISO 3166 code indicating the country */
    private String ISO_CTRY_CD = null;
    // QC#8285 2016/05/23 Add End

    // QC#21106 2017/09/19 Add Start
    private HashMap<String, String> jurisdictionLevelPool = new HashMap<String, String>();
    // QC#27970 2018/08/31 Del Start
    //private String jurisdictionLevelData[][] = {
    //         {JurisdictionLevelCodeType._APO, JurisdictionLevelCodeType._COUNTY },
    //         {JurisdictionLevelCodeType._FPO, JurisdictionLevelCodeType._COUNTY },
    //         {JurisdictionLevelCodeType._LOCAL_IMPROVEMENT_DISTRICT, JurisdictionLevelCodeType._COUNTY },
    //         {JurisdictionLevelCodeType._PARISH, JurisdictionLevelCodeType._COUNTY },
    //         {JurisdictionLevelCodeType._SPECIAL_PURPOSE_DISTRICT, JurisdictionLevelCodeType._COUNTY },
    //         {JurisdictionLevelCodeType._DISTRICT, JurisdictionLevelCodeType._COUNTY },
    //         {JurisdictionLevelCodeType._TRANSIT_DISTRICT, JurisdictionLevelCodeType._COUNTY }};
    // QC#27970 2018/08/31 Del End
    // QC#21106 2017/09/19 Add End
    // QC#27970 2018/08/31 Add Start
    private String jurisdictionLevelData[][] = {
            {JurisdictionLevelCodeType._APO, JurisdictionLevelCodeType._CITY },
            {JurisdictionLevelCodeType._FPO, JurisdictionLevelCodeType._CITY },
            {JurisdictionLevelCodeType._LOCAL_IMPROVEMENT_DISTRICT, JurisdictionLevelCodeType._CITY },
            {JurisdictionLevelCodeType._PARISH, JurisdictionLevelCodeType._COUNTY },
            {JurisdictionLevelCodeType._SPECIAL_PURPOSE_DISTRICT, JurisdictionLevelCodeType._CITY },
            {JurisdictionLevelCodeType._DISTRICT, JurisdictionLevelCodeType._CITY },
            {JurisdictionLevelCodeType._TRANSIT_DISTRICT, JurisdictionLevelCodeType._CITY },
            {JurisdictionLevelCodeType._TERRITORY, JurisdictionLevelCodeType._STATE },
            {JurisdictionLevelCodeType._BOROUGH, JurisdictionLevelCodeType._CITY },
            {JurisdictionLevelCodeType._PROVINCE, JurisdictionLevelCodeType._CITY },
            {JurisdictionLevelCodeType._TOWNSHIP, JurisdictionLevelCodeType._CITY },
            {JurisdictionLevelCodeType._TRADE_BLOCK, JurisdictionLevelCodeType._CITY }};
    // QC#27970 2018/08/31 Add End

    /**
     * constructor
     */
    public NWZC036101() {
        super();

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Tax Calculation API
     * 
     * <pre>
     * VertexProxy passes Vertex order information by using the unit of ZIP code, state, 
     * the county, the city, the details amount, and the functional currency construction and amounts of money, etc.
     * by the unit of the earnings recognition (unit of BOL), and TaxRate and TaxAmount are acquired. 
     * </pre>
     * @param param NWZC036101PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC036101PMsg param, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(param);

        // QC#11618 2016/07/11 Mod Start
        // CMPY_CD = ZYPCodeDataUtil.getVarCharConstValue(VERTEX_CMPY_CD, param.glblCmpyCd.getValue());
        // ISO_CTRY_CD = ZYPCodeDataUtil.getVarCharConstValue(VERTEX_ISO_CTRY_CD, param.glblCmpyCd.getValue());
        CMPY_CD = DataCache.getInstance().getVarCharConstValueFromCache(param.glblCmpyCd.getValue(), VERTEX_CMPY_CD);
        ISO_CTRY_CD = DataCache.getInstance().getVarCharConstValueFromCache(param.glblCmpyCd.getValue(), VERTEX_ISO_CTRY_CD);
        // QC#11618 2016/07/11 Mod End
        // QC#21106 2017/09/19 Add Start
        for(int i = 0; i < jurisdictionLevelData.length; i++){
            jurisdictionLevelPool.put(jurisdictionLevelData[i][0], jurisdictionLevelData[i][1]);
        }
        // QC#21106 2017/09/19 Add End

        doProcess(param, onBatchType);
        msgMap.flush();
    }

    /**
     * Tax Calculation API
     * @param param NWZC036101PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    private void doProcess(final NWZC036101PMsg param, final ONBATCH_TYPE onBatchType) {
        inputParameterCheck(param);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // 2017/12/22 S21_NA#22407 add start
        negateInputAmt(param);
        // 2017/12/22 S21_NA#22407 add end

        if (ZYPConstant.FLG_OFF_N.equals(param.taxCalcFlg.getValue()) || (!CTRY.UNITED_STATES_OF_AMERICA.equals(param.ctryCd_ST.getValue()) && !CTRY.CANADA.equals(param.ctryCd_ST.getValue()))) {
            setOutputParameterNoVertexCall(param);
            return;
        }

        masterDataSearch(param);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // Call Vertex Proxy
        NewOrderRequestElement vtxRequest = setVartexProxyAPI(param);
        // Mod Start 2019/04/10 QC#31046
        //NewOrderResponseElement vtxResponse = callVertexProxyAPI(vtxRequest);
        NewOrderResponseElement vtxResponse = callVertexProxyAPI(vtxRequest, param);
        // Mod End 2019/04/10 QC#31046
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        setOutputParameter(vtxResponse);
    }

    /**
     * inputParameterCheck.
     * @param param NWZC036101PMsg
     */
    private void inputParameterCheck(final NWZC036101PMsg param) {

        boolean hasHeaderAddr = mandatoryHeaderCheck(param);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        boolean hasLineAddr = mandatoryLineCheck(param);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        if (!hasHeaderAddr && !hasLineAddr) {
            msgMap.addXxMsgId(NWZM1534E);
        }

        numberFormatCheck(param); // QC#18701
    }

    /**
     * QC#18701<br>
     * number format check for TaxAreaId and GeoCd
     * @param param NWZC036101PMsg
     */
    private void numberFormatCheck(NWZC036101PMsg param) {
        java.util.regex.Pattern ptn = java.util.regex.Pattern.compile("^[0-9]*$");
        if(ZYPCommonFunc.hasValue(param.taxAreaId_ST)){
            java.util.regex.Matcher matcher = ptn.matcher(param.taxAreaId_ST.getValue());
            if(!matcher.matches()){
                msgMap.addXxMsgId(NWZM2231E);
            }
        }
        if(ZYPCommonFunc.hasValue(param.geoCd_SF)){
            java.util.regex.Matcher matcher = ptn.matcher(param.geoCd_SF.getValue());
            if(!matcher.matches()){
                msgMap.addXxMsgId(NWZM2232E);
            }
        }
        if(ZYPCommonFunc.hasValue(param.geoCd_SR)){
            java.util.regex.Matcher matcher = ptn.matcher(param.geoCd_SR.getValue());
            if(!matcher.matches()){
                msgMap.addXxMsgId(NWZM2232E);
            }
        }
        if(ZYPCommonFunc.hasValue(param.geoCd_ST)){
            java.util.regex.Matcher matcher = ptn.matcher(param.geoCd_ST.getValue());
            if(!matcher.matches()){
                msgMap.addXxMsgId(NWZM2232E);
            }
        }
    }

    /**
     * mandatoryHeaderCheck.
     * @param param NWZC036101PMsg
     */
    private boolean mandatoryHeaderCheck(final NWZC036101PMsg param) {
        String mode = param.xxModeCd.getValue();

        if (!ZYPCommonFunc.hasValue(mode)) {
            msgMap.addXxMsgId(NWZM0012E);
        } else {
            if (!PROC_MODE_DISTRIBUTE_TAX.equals(mode)
                    && !PROC_MODE_QUOTATION.equals(mode) && !PROC_MODE_INVOICE.equals(mode)) {
                msgMap.addXxMsgId(NWZM1154E);
            } else if (PROC_MODE_QUOTATION.equals(mode) || PROC_MODE_INVOICE.equals(mode)) {
                if (!ZYPCommonFunc.hasValue(param.dsAcctNum_SE)) {
                    msgMap.addXxMsgId(NWZM1314E);
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0011E);
        }
        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZM0346E);
        }
        if (ZYPConstant.FLG_ON_Y.equals(param.taxExemFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(param.taxExemRsnCd)) {
                msgMap.addXxMsgId(NWZM1342E);
            }
        }
        if (!ZYPCommonFunc.hasValue(param.ctyAddr_ST)
                // START 2017/07/18 [QC#19885,MOD]
                // || !ZYPCommonFunc.hasValue(param.stCd_ST) || !ZYPCommonFunc.hasValue(param.ctryCd_ST)) {
                || (!ZYPCommonFunc.hasValue(param.stCd_ST) && CTRY.UNITED_STATES_OF_AMERICA.equals(param.ctryCd_ST)) || !ZYPCommonFunc.hasValue(param.ctryCd_ST)) {
                // END   2017/07/18 [QC#19885,MOD]
            return false;
        }
        return true;
    }

    /**
     * mandatoryLineCheck
     * @param param NWZC036101PMsg
     */
    private boolean mandatoryLineCheck(final NWZC036101PMsg param) {
        String mode = param.xxModeCd.getValue();

        if (param.taxCalculateInputLine.getValidCount() == 0) {
            msgMap.addXxMsgId(NWZM1324E);
            return false;
        }

        boolean hasShipToAddr = true;
        for (int i = 0; i < param.taxCalculateInputLine.getValidCount(); i++) {
            NWZC036101_taxCalculateInputLinePMsg lineParam = param.taxCalculateInputLine.no(i);

            if (!ZYPCommonFunc.hasValue(lineParam.xxTaxCalcLineNum_A)) {
                msgMap.addXxMsgId(NWZM0350E);
            }
            if (!ZYPCommonFunc.hasValue(lineParam.mdseCd_A)) {
                msgMap.addXxMsgId(NWZM0021E);
            }
            if (!ZYPCommonFunc.hasValue(lineParam.svcAllocTrxTpNm_A)) {
                msgMap.addXxMsgId(NWZM1320E);
            }
            if (!ZYPCommonFunc.hasValue(lineParam.slsAmt_A)) {
                msgMap.addXxMsgId(NWZM0461E);
            }
            if (PROC_MODE_DISTRIBUTE_TAX.equals(mode)) {
                if (!ZYPCommonFunc.hasValue(lineParam.taxAmt_A)) {
                    msgMap.addXxMsgId(NWZM1322E);
                }
            }
            if (hasShipToAddr) {
                if (!ZYPCommonFunc.hasValue(lineParam.ctyAddr_AT)
                        // START 2017/07/18 [QC#19885, MOD]
                        // || (!ZYPCommonFunc.hasValue(lineParam.stCd_AT)) || (!ZYPCommonFunc.hasValue(lineParam.ctryCd_AT))) {
                        || (!ZYPCommonFunc.hasValue(lineParam.stCd_AT) && CTRY.UNITED_STATES_OF_AMERICA.equals(lineParam.ctryCd_AT)) || (!ZYPCommonFunc.hasValue(lineParam.ctryCd_AT))) {
                        // END   2017/07/18 [QC#19885, MOD]
                    hasShipToAddr = false;
                }
            }
        }
        return hasShipToAddr;
    }

    /**
     * masterDataSearch
     * @param param NWZC036101PMsg
     * @return boolean
     */
    private void masterDataSearch(final NWZC036101PMsg param) {
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();
        tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        this.glblCmpy = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (this.glblCmpy == null) {
            msgMap.addXxMsgId(NWZM0650E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(this.glblCmpy.stdCcyCd.getValue())) {
            msgMap.addXxMsgId(NWZM1183E);
            return;
        }
    }

    /**
     * setVartexProxyAPI.
     * @param param NWZC036101PMsg
     * @return NewOrderRequestElement
     */
    private NewOrderRequestElement setVartexProxyAPI(final NWZC036101PMsg param) {
        NewOrderRequestElement vtxReqest = new NewOrderRequestElement();
        VertexEnvelopeType envelope = new VertexEnvelopeType();

        if (PROC_MODE_QUOTATION.equals(param.xxModeCd.getValue())) {
            QuotationRequestType request = createQuotationRequestType(param);
            envelope.setQuotationRequest(request);

        } else if (PROC_MODE_INVOICE.equals(param.xxModeCd.getValue())) {
            InvoiceRequestType request = createInvoiceRequestType(param);
            envelope.setInvoiceRequest(request);

        } else {
            DistributeTaxRequestType request = createDistributeTaxRequestType(param);
            envelope.setDistributeTaxRequest(request);

        }
        vtxReqest.setVertexEnvelope(envelope);

        return vtxReqest;
    }

    private QuotationRequestType createQuotationRequestType(NWZC036101PMsg param) {
        QuotationRequestType request = new QuotationRequestType();

        request.setSeller(this.createSellerType(param));
        request.setCurrency(this.createCurrencyType());
        request.setCustomer(this.createCustomerType(param));
        request.setDocumentDate(this.getDocumentDate(param.trxDt.getValue()));
        request.setPostingDate(this.getDocumentDate(param.slsDt.getValue()));
//        request.setDocumentNumber(param.xxTaxCalcHdrNum.getValue());
//        request.setTransactionId(param.xxTaxCalcHdrNum.getValue());
        if (ZYPCommonFunc.hasValue(param.xxTaxCalcHdrNum)) {
            request.setDocumentNumber(param.xxTaxCalcHdrNum.getValue());
            request.setTransactionId(param.xxTaxCalcHdrNum.getValue());
        }
        request.setTransactionType(SaleTransactionType.SALE);
        // QC#4190
//        request.setLocationCode(param.whCd_SF.getValue());
        if (ZYPCommonFunc.hasValue(param.whCd_SF)) {
            request.setLocationCode(param.whCd_SF.getValue());
        }

        List<LineItemQSIType> lineList = new ArrayList<LineItemQSIType>();
        for (int i = 0; i < param.taxCalculateInputLine.getValidCount(); i++) {
            lineList.add(createLineItemForQuotationMode(param.taxCalculateInputLine.no(i), param));
        }
        request.setLineItem((LineItemQSIType[]) lineList.toArray(new LineItemQSIType[0]));

        return request;
    }

    /**
     * Create a Line Item Request Parameter for Quotation Mode.
     * @param linePMsg NWZC036101_xxTaxCalculateInputLinePMsg
     * @return lineItem LineItemQSIType
     */
    private LineItemQSIType createLineItemForQuotationMode(NWZC036101_taxCalculateInputLinePMsg lineParam, NWZC036101PMsg param) {
        LineItemQSIType line = new LineItemQSIType();

        line.setLineItemNumber(new BigInteger(lineParam.xxTaxCalcLineNum_A.getValue()));
        line.setLineItemId(lineParam.mdseCd_A.getValue());
        line.setMaterialCode(lineParam.mdseCd_A.getValue());
        line.setProduct(this.createLineProduct(lineParam));
        line.setQuantity(this.createLineQty(lineParam));
        line.setUnitPrice(this.createLineUnitPrice(lineParam));
        line.setExtendedPrice(this.createLineExtendedPrice(lineParam));
        line.setCustomer(this.createLineCustomerType(lineParam, param));
        line.setSeller(this.createLineSellerType(lineParam, param));
        line.setPostingDate(this.getDocumentDate(param.slsDt.getValue()));

        // QC#4190 mod Start
//        line.setLocationCode(lineParam.whCd_AF.getValue());
        if (ZYPCommonFunc.hasValue(lineParam.whCd_AF)) {
            line.setLocationCode(lineParam.whCd_AF.getValue());
        }
//        line.setTaxDate(this.getDocumentDate(lineParam.trxDt_A.getValue()));
        if (ZYPCommonFunc.hasValue(lineParam.trxDt_A)) {
            line.setTaxDate(this.getDocumentDate(lineParam.trxDt_A.getValue()));
        }
        // QC#4190 mod End
        line.setCountryOfOriginISOCode(ISO_CTRY_CD);

        if (ZYPCommonFunc.hasValue(lineParam.trxDt_A.getValue())) {
            line.setTaxDate(this.getDocumentDate(lineParam.trxDt_A.getValue()));
        } else {
            line.setTaxDate(this.getDocumentDate(param.trxDt.getValue()));
        }
        if (ZYPCommonFunc.hasValue(lineParam.svcAllocTrxTpNm_A)) {
            line.setTransactionType(SaleTransactionType.fromString(lineParam.svcAllocTrxTpNm_A.getValue()));
        }

        // START 2019/09/21 S.Takami [QC#53650,ADD]
        FlexibleFields flexibleFields = getFlexibleFieldsForShipToCustName(param, lineParam);
        if (flexibleFields != null) {
            line.setFlexibleFields(flexibleFields);
        }
        // END 2019/09/21 S.Takami [QC#53650,ADD]

        return line;
    }

    private InvoiceRequestType createInvoiceRequestType(NWZC036101PMsg param) {
        InvoiceRequestType request = new InvoiceRequestType();

        request.setSeller(this.createSellerType(param));
        request.setCurrency(this.createCurrencyType());
        request.setCustomer(this.createCustomerType(param));
        request.setDocumentDate(this.getDocumentDate(param.trxDt.getValue()));
        request.setDocumentNumber(param.xxTaxCalcHdrNum.getValue());
        request.setPostingDate(this.getDocumentDate(param.slsDt.getValue()));
        request.setTransactionId(param.xxTaxCalcHdrNum.getValue());
        request.setTransactionType(SaleTransactionType.SALE);
        // QC#4190
//        request.setLocationCode(param.whCd_SF.getValue());
        if (ZYPCommonFunc.hasValue(param.whCd_SF)) {
            request.setLocationCode(param.whCd_SF.getValue());
        }

        List<LineItemISIType> lineList = new ArrayList<LineItemISIType>();
        for (int i = 0; i < param.taxCalculateInputLine.getValidCount(); i++) {
            lineList.add(createLineItemForInvoiceMode(param.taxCalculateInputLine.no(i), param));
        }

        request.setLineItem((LineItemISIType[]) lineList.toArray(new LineItemISIType[0]));

        return request;
    }

    /**
     * Create a Line Item Request Parameter for Invoice Mode.
     * @param linePMsg NWZC036101_taxCalculateInputLinePMsg
     * @param param NWZC036101PMsg
     * @return lineItem LineItemISIType
     */
    private LineItemISIType createLineItemForInvoiceMode(NWZC036101_taxCalculateInputLinePMsg lineParam, NWZC036101PMsg param) {
        LineItemISIType line = new LineItemISIType();

        line.setLineItemNumber(new BigInteger(lineParam.xxTaxCalcLineNum_A.getValue()));
        line.setLineItemId(lineParam.mdseCd_A.getValue());
        line.setMaterialCode(lineParam.mdseCd_A.getValue());
        line.setProduct(this.createLineProduct(lineParam));
        line.setQuantity(this.createLineQty(lineParam));
        line.setUnitPrice(this.createLineUnitPrice(lineParam));
        line.setExtendedPrice(this.createLineExtendedPrice(lineParam));
        line.setCustomer(this.createLineCustomerType(lineParam, param));
        line.setSeller(this.createLineSellerType(lineParam, param));
        line.setPostingDate(this.getDocumentDate(param.slsDt.getValue()));
        line.setCountryOfOriginISOCode(ISO_CTRY_CD);
        // QC#4190
//        line.setLocationCode(lineParam.whCd_AF.getValue());
        if (ZYPCommonFunc.hasValue(lineParam.whCd_AF)) {
            line.setLocationCode(lineParam.whCd_AF.getValue());
        }

        if (ZYPCommonFunc.hasValue(lineParam.trxDt_A.getValue())) {
            line.setTaxDate(this.getDocumentDate(lineParam.trxDt_A.getValue()));
        } else {
            line.setTaxDate(this.getDocumentDate(param.trxDt.getValue()));
        }
        if (ZYPCommonFunc.hasValue(lineParam.svcAllocTrxTpNm_A)) {
            line.setTransactionType(SaleTransactionType.fromString(lineParam.svcAllocTrxTpNm_A.getValue()));
        }
        // START 2019/09/21 S.Takami [QC#53650,ADD]
        FlexibleFields flexibleFields = getFlexibleFieldsForShipToCustName(param, lineParam);
        if (flexibleFields != null) {
            line.setFlexibleFields(flexibleFields);
        }
        // END 2019/09/21 S.Takami [QC#53650,ADD]

        return line;
    }

    private DistributeTaxRequestType createDistributeTaxRequestType(NWZC036101PMsg param) {
        DistributeTaxRequestType request = new DistributeTaxRequestType();

        request.setSeller(this.createSellerType(param));
        request.setCurrency(this.createCurrencyType());
        request.setCustomer(this.createCustomerType(param));
        request.setDocumentDate(this.getDocumentDate(param.trxDt.getValue()));
        // QC#4190
//        request.setDocumentNumber(param.xxTaxCalcHdrNum.getValue());
//        request.setTransactionId(param.xxTaxCalcHdrNum.getValue());
        if (ZYPCommonFunc.hasValue(param.xxTaxCalcHdrNum)) {
            request.setDocumentNumber(param.xxTaxCalcHdrNum.getValue());
            request.setTransactionId(param.xxTaxCalcHdrNum.getValue());
        }
        request.setPostingDate(this.getDocumentDate(param.slsDt.getValue()));
        request.setTransactionType(SaleTransactionType.SALE);
        // QC#4190
//        request.setLocationCode(param.whCd_SF.getValue());
        if (ZYPCommonFunc.hasValue(param.whCd_SF)) {
            request.setLocationCode(param.whCd_SF.getValue());
        }

        request.setIsTaxOnlyAdjustmentIndicator(Boolean.TRUE.toString());

        List<LineItemDTSIType> lineList = new ArrayList<LineItemDTSIType>();
        for (int i = 0; i < param.taxCalculateInputLine.getValidCount(); i++) {
            lineList.add(createLineItemForDistributeTaxMode(param.taxCalculateInputLine.no(i), param));
        }

        request.setLineItem((LineItemDTSIType[]) lineList.toArray(new LineItemDTSIType[0]));

        return request;
    }

    /**
     * Create a Line Item Request Parameter for Distribute Tax Mode.
     * @param linePMsg NWZC036101_xxTaxCalculateInputLinePMsg
     * @return lineItem LineItemDTSIType
     */
    private LineItemDTSIType createLineItemForDistributeTaxMode(NWZC036101_taxCalculateInputLinePMsg lineParam, NWZC036101PMsg param) {
        LineItemDTSIType line = new LineItemDTSIType();

        line.setLineItemNumber(new BigInteger(lineParam.xxTaxCalcLineNum_A.getValue()));
        line.setLineItemId(lineParam.mdseCd_A.getValue());
        line.setMaterialCode(lineParam.mdseCd_A.getValue());
        line.setProduct(this.createLineProduct(lineParam));
        // 2020/03/27  START [QC#56158,MOD]
        //line.setQuantity(this.createLineQty(lineParam));
        //line.setUnitPrice(this.createLineUnitPrice(lineParam));
        //line.setExtendedPrice(this.createLineExtendedPrice(lineParam));
        line.setCustomer(this.createLineCustomerType(lineParam, param));
        line.setSeller(this.createLineSellerType(lineParam, param));
        line.setInputTotalTax(this.createLineInputTotaxTax(lineParam));
        // 2020/03/27  END   [QC#56158,MOD]
        line.setPostingDate(this.getDocumentDate(param.slsDt.getValue()));
        // QC#4190
//        line.setLocationCode(lineParam.whCd_AF.getValue());
        if (ZYPCommonFunc.hasValue(lineParam.whCd_AF)) {
            line.setLocationCode(lineParam.whCd_AF.getValue());
        }

        if (ZYPCommonFunc.hasValue(lineParam.trxDt_A.getValue())) {
            line.setTaxDate(this.getDocumentDate(lineParam.trxDt_A.getValue()));
        } else {
            line.setTaxDate(this.getDocumentDate(param.trxDt.getValue()));
        }
        if (ZYPCommonFunc.hasValue(lineParam.svcAllocTrxTpNm_A)) {
            line.setTransactionType(SaleTransactionType.fromString(lineParam.svcAllocTrxTpNm_A.getValue()));
        }

        // START 2019/09/21 S.Takami [QC#53650,ADD]
        FlexibleFields flexibleFields = getFlexibleFieldsForShipToCustName(param, lineParam);
        if (flexibleFields != null) {
            line.setFlexibleFields(flexibleFields);
        }
        // END 2019/09/21 S.Takami [QC#53650,ADD]

        return line;
    }

    private SellerType createSellerType(NWZC036101PMsg param) {
        SellerType seller = new SellerType();

        seller.setCompany(CMPY_CD);
        seller.setDivision(DIV_CD);

        LocationType adminOrigin = new LocationType();

        adminOrigin = setHdrAdminOrigin(adminOrigin, param);
        // QC#4190
//        seller.setAdministrativeOrigin(adminOrigin);
        if (adminOrigin != null) {
            seller.setAdministrativeOrigin(adminOrigin);
        }

        LocationType physicalOrigin = new LocationType();

        physicalOrigin = setHdrPhysicalOrigin(physicalOrigin, param);
        // QC#4190
//        seller.setPhysicalOrigin(physicalOrigin);
        if (physicalOrigin != null) {
            seller.setPhysicalOrigin(physicalOrigin);
        }

        return seller;
    }

    private LocationType setHdrDestinateOrigin(LocationType destination, NWZC036101PMsg param) {

        if (ZYPConstant.FLG_ON_Y.equals(param.dsInsdCtyLimitFlg_ST.getValue()) && ZYPCommonFunc.hasValue(param.geoCd_ST)) {
            destination.setTaxAreaId(new BigInteger(param.geoCd_ST.getValue()));
        }
        // QC#4190 mod Start
//        destination.setStreetAddress1(param.firstLineAddr_ST.getValue());
        if (ZYPCommonFunc.hasValue(param.firstLineAddr_ST)) {
            destination.setStreetAddress1(param.firstLineAddr_ST.getValue());
        }
//        destination.setStreetAddress2(param.scdLineAddr_ST.getValue());
        if (ZYPCommonFunc.hasValue(param.scdLineAddr_ST)) {
            destination.setStreetAddress2(param.scdLineAddr_ST.getValue());
        }
//        destination.setCity(param.ctyAddr_ST.getValue());
        if (ZYPCommonFunc.hasValue(param.ctyAddr_ST)) {
            destination.setCity(param.ctyAddr_ST.getValue());
        }
//        destination.setMainDivision(param.stCd_ST.getValue());
        if (ZYPCommonFunc.hasValue(param.stCd_ST)) {
            destination.setMainDivision(param.stCd_ST.getValue());
        }
//        destination.setSubDivision(param.cntyNm_ST.getValue());
        if (ZYPCommonFunc.hasValue(param.cntyNm_ST)) {
            destination.setSubDivision(param.cntyNm_ST.getValue());
        }
//        destination.setPostalCode(param.postCd_ST.getValue());
        if (ZYPCommonFunc.hasValue(param.postCd_ST)) {
            destination.setPostalCode(param.postCd_ST.getValue());
        }
//        destination.setCountry(param.ctryCd_ST.getValue());
        if (ZYPCommonFunc.hasValue(param.ctryCd_ST)) {
            destination.setCountry(param.ctryCd_ST.getValue());
        }
        // QC#4190 mod End

        // QC#4190 add Start
        if (isAllNull(destination.getTaxAreaId()
                    , destination.getStreetAddress1()
                    , destination.getStreetAddress2()
                    , destination.getCity()
                    , destination.getMainDivision()
                    , destination.getSubDivision()
                    , destination.getPostalCode()
                    , destination.getCountry())
        ) {
            destination = null;
        }
        // QC#4190 add End

        return destination;
    }

    private LocationType setHdrAdminOrigin(LocationType adminOrigin, NWZC036101PMsg param) {

        if (ZYPCommonFunc.hasValue(param.taxAreaId_ST)) {
            adminOrigin.setTaxAreaId(new BigInteger(param.taxAreaId_ST.getValue()));
        } else if (ZYPCommonFunc.hasValue(param.geoCd_SR) && ZYPConstant.FLG_ON_Y.equals(param.dsInsdCtyLimitFlg_SR.getValue())) {
            adminOrigin.setTaxAreaId(new BigInteger(param.geoCd_SR.getValue()));
        }
        // QC#4190 mod Start
//        adminOrigin.setStreetAddress1(param.firstLineAddr_SR.getValue());
        if (ZYPCommonFunc.hasValue(param.firstLineAddr_SR)) {
            adminOrigin.setStreetAddress1(param.firstLineAddr_SR.getValue());
        }
//        adminOrigin.setStreetAddress2(param.scdLineAddr_SR.getValue());
        if (ZYPCommonFunc.hasValue(param.scdLineAddr_SR)) {
            adminOrigin.setStreetAddress2(param.scdLineAddr_SR.getValue());
        }
//        adminOrigin.setCity(param.ctryCd_SR.getValue());
        if (ZYPCommonFunc.hasValue(param.ctyAddr_SR)) {
            adminOrigin.setCity(param.ctyAddr_SR.getValue());
        }
//        adminOrigin.setMainDivision(param.stCd_SR.getValue());
        if (ZYPCommonFunc.hasValue(param.stCd_SR)) {
            adminOrigin.setMainDivision(param.stCd_SR.getValue());
        }
//        adminOrigin.setSubDivision(param.cntyNm_SR.getValue());
        if (ZYPCommonFunc.hasValue(param.cntyNm_SR)) {
            adminOrigin.setSubDivision(param.cntyNm_SR.getValue());
        }
//        adminOrigin.setPostalCode(param.postCd_SR.getValue());
        if (ZYPCommonFunc.hasValue(param.postCd_SR)) {
            adminOrigin.setPostalCode(param.postCd_SR.getValue());
        }
//        adminOrigin.setCountry(param.ctryCd_SR.getValue());
        if (ZYPCommonFunc.hasValue(param.ctryCd_SR)) {
            adminOrigin.setCountry(param.ctryCd_SR.getValue());
        }
        // QC#4190 mod End

        // QC#4190 add Start
        if (isAllNull(adminOrigin.getTaxAreaId()
                    , adminOrigin.getStreetAddress1()
                    , adminOrigin.getStreetAddress2()
                    , adminOrigin.getCity()
                    , adminOrigin.getMainDivision()
                    , adminOrigin.getSubDivision()
                    , adminOrigin.getPostalCode()
                    , adminOrigin.getCountry())
        ) {
            adminOrigin = null;
        }
        // QC#4190 add End

        return adminOrigin;
    }

    private LocationType setHdrPhysicalOrigin(LocationType physicalOrigin, NWZC036101PMsg param) {

        if (ZYPCommonFunc.hasValue(param.taxAreaId_ST)) {
            physicalOrigin.setTaxAreaId(new BigInteger(param.taxAreaId_ST.getValue()));
        } else if (ZYPCommonFunc.hasValue(param.geoCd_SF)) {
            physicalOrigin.setTaxAreaId(new BigInteger(param.geoCd_SF.getValue()));
        }

        // QC#4190 mod Start
//        physicalOrigin.setStreetAddress1(param.firstLineAddr_SF.getValue());
        if (ZYPCommonFunc.hasValue(param.firstLineAddr_SF)) {
            physicalOrigin.setStreetAddress1(param.firstLineAddr_SF.getValue());
        }
//        physicalOrigin.setStreetAddress2(param.scdLineAddr_SF.getValue());
        if (ZYPCommonFunc.hasValue(param.scdLineAddr_SF)) {
            physicalOrigin.setStreetAddress2(param.scdLineAddr_SF.getValue());
        }
//        physicalOrigin.setCity(param.ctryCd_SF.getValue());
        // QC#13877 2016/08/30 Mod Start
//        if (ZYPCommonFunc.hasValue(param.ctryCd_SF)) {
//            physicalOrigin.setCity(param.ctryCd_SF.getValue());
//        }
        if (ZYPCommonFunc.hasValue(param.ctyAddr_SF)) {
            physicalOrigin.setCity(param.ctyAddr_SF.getValue());
        }
        // QC#13877 2016/08/30 Mod End
//        physicalOrigin.setMainDivision(param.stCd_SF.getValue());
        if (ZYPCommonFunc.hasValue(param.stCd_SF)) {
            physicalOrigin.setMainDivision(param.stCd_SF.getValue());
        }
//        physicalOrigin.setSubDivision(param.cntyNm_SF.getValue());
        if (ZYPCommonFunc.hasValue(param.cntyNm_SF)) {
            physicalOrigin.setSubDivision(param.cntyNm_SF.getValue());
        }
//        physicalOrigin.setPostalCode(param.postCd_SF.getValue());
        if (ZYPCommonFunc.hasValue(param.postCd_SF)) {
            physicalOrigin.setPostalCode(param.postCd_SF.getValue());
        }
//        physicalOrigin.setCountry(param.ctryCd_SF.getValue());
        if (ZYPCommonFunc.hasValue(param.ctryCd_SF)) {
            physicalOrigin.setCountry(param.ctryCd_SF.getValue());
        }
        // QC#4190 mod End

        // QC#4190 add Start
        if (isAllNull(physicalOrigin.getTaxAreaId()
                    , physicalOrigin.getStreetAddress1()
                    , physicalOrigin.getStreetAddress2()
                    , physicalOrigin.getCity()
                    , physicalOrigin.getMainDivision()
                    , physicalOrigin.getSubDivision()
                    , physicalOrigin.getPostalCode()
                    , physicalOrigin.getCountry())
        ) {
            physicalOrigin = null;
        }
        // QC#4190 add End

        return physicalOrigin;
    }

    private SellerType createLineSellerType(NWZC036101_taxCalculateInputLinePMsg lineParam, NWZC036101PMsg param) {
        SellerType seller = new SellerType();

        LocationType adminOrigin = new LocationType();

        if (ZYPCommonFunc.hasValue(lineParam.ctyAddr_AR)) {
            // QC#4190 mod Start
            if (ZYPCommonFunc.hasValue(lineParam.taxAreaId_AT)) {
                adminOrigin.setTaxAreaId(new BigInteger(lineParam.taxAreaId_AT.getValue()));
            } else if (ZYPCommonFunc.hasValue(lineParam.geoCd_AR) && ZYPConstant.FLG_ON_Y.equals(lineParam.dsInsdCtyLimitFlg_AR.getValue())) {
                adminOrigin.setTaxAreaId(new BigInteger(lineParam.geoCd_AR.getValue()));
            }
//            adminOrigin.setStreetAddress1(lineParam.firstLineAddr_AR.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.firstLineAddr_AR)) {
                adminOrigin.setStreetAddress1(lineParam.firstLineAddr_AR.getValue());
            }
//            adminOrigin.setStreetAddress2(lineParam.scdLineAddr_AR.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.scdLineAddr_AR)) {
                adminOrigin.setStreetAddress2(lineParam.scdLineAddr_AR.getValue());
            }
//            adminOrigin.setCity(lineParam.ctyAddr_AR.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.ctyAddr_AR)) {
                adminOrigin.setCity(lineParam.ctyAddr_AR.getValue());
            }
//            adminOrigin.setMainDivision(lineParam.stCd_AR.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.stCd_AR)) {
                adminOrigin.setMainDivision(lineParam.stCd_AR.getValue());
            }
//            adminOrigin.setSubDivision(lineParam.cntyNm_AR.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.cntyNm_AR)) {
                adminOrigin.setSubDivision(lineParam.cntyNm_AR.getValue());
            }
//            adminOrigin.setPostalCode(lineParam.postCd_AR.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.postCd_AR)) {
                adminOrigin.setPostalCode(lineParam.postCd_AR.getValue());
            }
//            adminOrigin.setCountry(lineParam.ctryCd_AR.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.ctryCd_AR)) {
                adminOrigin.setCountry(lineParam.ctryCd_AR.getValue());
            }
            // QC#4190 mod End

        } else {
            adminOrigin = setHdrAdminOrigin(adminOrigin, param);
        }

        // QC#4190 add Start
        if (adminOrigin != null
            && isAllNull(adminOrigin.getTaxAreaId()
                        , adminOrigin.getStreetAddress1()
                        , adminOrigin.getStreetAddress2()
                        , adminOrigin.getCity()
                        , adminOrigin.getMainDivision()
                        , adminOrigin.getSubDivision()
                        , adminOrigin.getPostalCode()
                        , adminOrigin.getCountry())
        ) {
            adminOrigin = null;
        }
        // QC#4190 add End
        seller.setAdministrativeOrigin(adminOrigin);

        LocationType physicalOrigin = new LocationType();

        if (ZYPCommonFunc.hasValue(lineParam.ctyAddr_AF)) {
            if (ZYPCommonFunc.hasValue(lineParam.taxAreaId_AT)) {
                physicalOrigin.setTaxAreaId(new BigInteger(lineParam.taxAreaId_AT.getValue()));
            } else if (ZYPCommonFunc.hasValue(lineParam.geoCd_AF)) {
                physicalOrigin.setTaxAreaId(new BigInteger(lineParam.geoCd_AF.getValue()));
            }
            // QC#4190 mod Start
//            physicalOrigin.setStreetAddress1(lineParam.firstLineAddr_AF.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.firstLineAddr_AF)) {
                physicalOrigin.setStreetAddress1(lineParam.firstLineAddr_AF.getValue());
            }
//            physicalOrigin.setStreetAddress2(lineParam.scdLineAddr_AF.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.scdLineAddr_AF)) {
                physicalOrigin.setStreetAddress2(lineParam.scdLineAddr_AF.getValue());
            }
//            physicalOrigin.setCity(lineParam.ctyAddr_AF.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.ctyAddr_AF)) {
                physicalOrigin.setCity(lineParam.ctyAddr_AF.getValue());
            }
//            physicalOrigin.setMainDivision(lineParam.stCd_AF.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.stCd_AF)) {
                physicalOrigin.setMainDivision(lineParam.stCd_AF.getValue());
            }
//            physicalOrigin.setSubDivision(lineParam.cntyNm_AF.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.cntyNm_AF)) {
                physicalOrigin.setSubDivision(lineParam.cntyNm_AF.getValue());
            }
//            physicalOrigin.setPostalCode(lineParam.postCd_AF.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.postCd_AF)) {
                physicalOrigin.setPostalCode(lineParam.postCd_AF.getValue());
            }
//            physicalOrigin.setCountry(lineParam.ctryCd_AF.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.ctryCd_AF)) {
                physicalOrigin.setCountry(lineParam.ctryCd_AF.getValue());
            }
            // QC#4190 mod End
        } else {
            physicalOrigin = setHdrPhysicalOrigin(physicalOrigin, param);
        }
        // QC#4190 add Start
        if (physicalOrigin != null
            && isAllNull(physicalOrigin.getTaxAreaId()
                        , physicalOrigin.getStreetAddress1()
                        , physicalOrigin.getStreetAddress2()
                        , physicalOrigin.getCity()
                        , physicalOrigin.getMainDivision()
                        , physicalOrigin.getSubDivision()
                        , physicalOrigin.getPostalCode()
                        , physicalOrigin.getCountry())
        ) {
            physicalOrigin = null;
        }
        // QC#4190 add End
        seller.setPhysicalOrigin(physicalOrigin);

        // QC#4190 add Start
        if (seller.getAdministrativeOrigin() == null
                && seller.getPhysicalOrigin() == null) {
            seller = null;
        }
        // QC#4190 add End

        return seller;
    }

    private CurrencyType createCurrencyType() {
        CurrencyType currency = new CurrencyType();
        currency.setIsoCurrencyCodeAlpha(this.glblCmpy.stdCcyCd.getValue());

        return currency;
    }

    private CustomerType createCustomerType(NWZC036101PMsg param) {
        CustomerType customer = new CustomerType();

        LocationType destination = new LocationType();
        destination = setHdrDestinateOrigin(destination, param);
        customer.setDestination(destination);

        CustomerCodeType customerCodeType = new CustomerCodeType();
        // QC#21847 2017/10/17 Mod Start
        // customerCodeType.set_value(param.dsAcctNum_ST.getValue());
        customerCodeType.set_value(param.billToAcctNum.getValue());
        // QC#21847 2017/10/17 Mod End

        String classCode = param.dsTaxGrpExemCd_ST.getValue();

        if (ZYPConstant.FLG_ON_Y.equals(param.dsTaxGrpExemReslFlg_B.getValue())) {
            if (ST.ILLINOIS.equals(param.stCd_ST.getValue()) && ST.ILLINOIS.equals(param.stCd_SR.getValue())) {
                // QC#20083 Temp 2017/07/24 Add Start
                // if ("FMV".equals(param.leasePrchOptCd.getValue())) {
                if (LEASE_PRCH_OPT.FMV.equals(param.leasePrchOptCd.getValue())) {
                // QC#20083 Temp 2017/07/24 Add End
                    classCode = param.dsTaxGrpExemCd_ST.getValue();
                    //QC#22169
                    customerCodeType.set_value(param.dsAcctNum_ST.getValue());
                // QC#22169 2017/12/19 Add Start
                } else {
                    classCode = param.dsTaxGrpExemCd_B.getValue();
                // QC#22169 2017/12/19 Add End
                }
            } else {
                // if (param.billToLocNum.getValue().equals(param.shipToLocNum.getValue())) { //QC#8664
                // if (!param.billToLocNum.getValue().equals(param.shipToLocNum.getValue())) {
                // QC#18663
                if (!S21StringUtil.isEquals(//
                    convCustCdToLocNum(param, LOC_ROLE_TP.BILL_TO), convCustCdToLocNum(param, LOC_ROLE_TP.SHIP_TO))) {
                    classCode = param.dsTaxGrpExemCd_B.getValue();
                }
            }
        }

        //QC#4190 mod Start
        // customerCodeType.setClassCode(classCode);
        if (ZYPCommonFunc.hasValue(classCode)) {
            customerCodeType.setClassCode(classCode);
        }
        //QC#4190 mod End

        customer.setCustomerCode(customerCodeType);

        // QC#4190 mod Start
        // if (ZYPCommonFunc.hasValue(param.taxExemFlg)) {
        //     customer.setIsTaxExempt(param.taxExemFlg.getValue());
        // } else {
        //     customer.setIsTaxExempt(ZYPConstant.FLG_OFF_N);
        // }
        if (ZYPConstant.FLG_ON_Y.equals(param.taxExemFlg.getValue())) {
            customer.setIsTaxExempt(Boolean.TRUE.toString());
        } else {
            customer.setIsTaxExempt(Boolean.FALSE.toString());
        }

//        customer.setExemptionReasonCode(param.taxExemRsnCd.getValue());
        if (ZYPCommonFunc.hasValue(param.taxExemRsnCd)) {
            customer.setExemptionReasonCode(param.taxExemRsnCd.getValue());
        }
        // QC#4190 mod End

        // QC#4190 add Start
        if (isAllNull(customer.getDestination()
                    , customer.getCustomerCode()
                    , customer.getExemptionReasonCode())) {
            customer = null;
        }
        // QC#4190 add End

        return customer;
    }

    // QC#18663
    private String convCustCdToLocNum(NWZC036101PMsg param, String locRoleTp) {

        return DataCache.getInstance().convCustCdToLocNum(param, locRoleTp, ssmBatchClient);
    }

    private CustomerType createLineCustomerType(NWZC036101_taxCalculateInputLinePMsg lineParam, NWZC036101PMsg param) {
        CustomerType customer = new CustomerType();

        LocationType destination = new LocationType();

        if (ZYPCommonFunc.hasValue(lineParam.ctyAddr_AT)) {
//            if (ZYPCommonFunc.hasValue(lineParam.geoCd_AT)) {
//            if (ZYPCommonFunc.hasValue(lineParam.geoCd_AT) && ZYPConstant.FLG_ON_Y.equals(lineParam.dsInsdCtyLimitFlg_AR.getValue())) { // QC#8545 2016/06/06
            if (ZYPCommonFunc.hasValue(lineParam.geoCd_AT) && ZYPConstant.FLG_ON_Y.equals(lineParam.dsInsdCtyLimitFlg_AT.getValue())) {
                destination.setTaxAreaId(new BigInteger(lineParam.geoCd_AT.getValue()));
            }
//            destination.setStreetAddress1(lineParam.firstLineAddr_AT.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.firstLineAddr_AT)) {
                destination.setStreetAddress1(lineParam.firstLineAddr_AT.getValue());
            }
//            destination.setStreetAddress2(lineParam.scdLineAddr_AT.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.scdLineAddr_AT)) {
                destination.setStreetAddress2(lineParam.scdLineAddr_AT.getValue());
            }
//            destination.setCity(lineParam.ctyAddr_AT.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.ctyAddr_AT)) {
                destination.setCity(lineParam.ctyAddr_AT.getValue());
            }
//            destination.setMainDivision(lineParam.stCd_AT.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.stCd_AT)) {
                destination.setMainDivision(lineParam.stCd_AT.getValue());
            }
//            destination.setSubDivision(lineParam.cntyNm_AT.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.cntyNm_AT)) {
                destination.setSubDivision(lineParam.cntyNm_AT.getValue());
            }
//            destination.setPostalCode(lineParam.postCd_AT.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.postCd_AT)) {
                destination.setPostalCode(lineParam.postCd_AT.getValue());
            }
//            destination.setCountry(lineParam.ctryCd_AT.getValue());
            if (ZYPCommonFunc.hasValue(lineParam.ctryCd_AT)) {
                destination.setCountry(lineParam.ctryCd_AT.getValue());
            }
        } else {
            destination = setHdrDestinateOrigin(destination, param);
        }
        // QC#4190 add Start
        if (destination != null
             && isAllNull(destination.getTaxAreaId()
                        , destination.getStreetAddress1()
                        , destination.getStreetAddress2()
                        , destination.getCity()
                        , destination.getMainDivision()
                        , destination.getSubDivision()
                        , destination.getPostalCode()
                        , destination.getCountry())
        ) {
            destination = null;
        }
        // QC#4190 add End

        customer.setDestination(destination);

        // QC#4190 add Start
        if (customer.getDestination() == null) {
            customer = null;
        }
        // QC#4190 add End

        return customer;
    }

    private boolean isAllNull(Object...values) {

        for (Object val : values) {
            if (val != null) {
                return false;
            }
        }

        return true;
    }

    private Calendar getDocumentDate(String paramDate) {
        try {
            if (!ZYPCommonFunc.hasValue(paramDate)) {
                return null;
            }

            Date date = dateFormat.parse(paramDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            return calendar;

        } catch (ParseException e) {
            // do nothing
            EZDDebugOutput.printStackTrace(e, this);
        }

        return null;
    }

    private Product createLineProduct(NWZC036101_taxCalculateInputLinePMsg lineParam) {
        Product product = new Product();
        product.set_value(lineParam.mdseCd_A.getValue());

        product.setProductClass(lineParam.taxExemTpCd_A.getValue());

        return product;
    }

    private MeasureType createLineQty(NWZC036101_taxCalculateInputLinePMsg lineParam) {
        MeasureType lineQty = new MeasureType();
        lineQty.set_value(lineParam.shipQty_A.getValue());

        return lineQty;
    }

    private AmountType createLineUnitPrice(NWZC036101_taxCalculateInputLinePMsg lineParam) {
        AmountType unitPrice = new AmountType();
        unitPrice.set_value(lineParam.funcNetUnitPrcAmt_A.getValue());

        return unitPrice;
    }

    private AmountType createLineExtendedPrice(NWZC036101_taxCalculateInputLinePMsg lineParam) {
        AmountType extPrice = new AmountType();
        extPrice.set_value(lineParam.slsAmt_A.getValue());

        return extPrice;
    }

    private AmountType createLineInputTotaxTax(NWZC036101_taxCalculateInputLinePMsg lineParam) {
        AmountType inputTotaxTax = new AmountType();
        inputTotaxTax.set_value(lineParam.taxAmt_A.getValue());

        return inputTotaxTax;
    }

    // Mod Start 2019/04/10 QC#31046
    ///**
    // * callVertexProxyAPI.
    // * @param vtxRequest NewOrderRequestElement
    // * @return TaxCalcType
    // */
    //private NewOrderResponseElement callVertexProxyAPI(NewOrderRequestElement vtxRequest) {
    /**
     * callVertexProxyAPI.
     * @param vtxRequest NewOrderRequestElement
     * @param param NWZC036101PMsg
     * @return TaxCalcType
     */
    private NewOrderResponseElement callVertexProxyAPI(NewOrderRequestElement vtxRequest, NWZC036101PMsg param) {
        // Mod End 2019/04/10 QC#31046

        // Del Start 2019/04/10 QC#31046
        //try {
        //    S21ibVertexCalculateTaxServiceProxy vtxProxy = new S21ibVertexCalculateTaxServiceProxy();
        //
        //    return vtxProxy.newOrder(vtxRequest);
        //
        //} catch (RemoteException e) {
        //    e.printStackTrace();
        //    msgMap.addXxMsgId(NWZM1313E);
        //} catch (Throwable e) {
        //    e.printStackTrace();
        //    msgMap.addXxMsgId(NWZM1313E);
        //}
        // Del End 2019/04/10 QC#31046

        // Add Start 2019/04/10 QC#31046
        BigDecimal countBigDecimal = ZYPCodeDataUtil.getNumConstValue(NWZC0361_VERTEX_TRY_CNT, param.glblCmpyCd.getValue());
        int count = 0;
        if (countBigDecimal == null) {
            count = DEFAULT_VERTEX_TRY_CNT;
        } else {
            count = countBigDecimal.intValue();
        }

        BigDecimal sleepBigDecimal = ZYPCodeDataUtil.getNumConstValue(NWZC0361_VERTEX_SLEEP_MSEC, param.glblCmpyCd.getValue());
        long sleepTime = 0;
        if (sleepBigDecimal == null) {
            sleepTime = DEFAULT_VERTEX_SLEEP_MSEC;
        } else {
            sleepTime = sleepBigDecimal.longValue();
        }

        for (int i = 0; i < count; i++) {
            try {
                S21ibVertexCalculateTaxServiceProxy vtxProxy = new S21ibVertexCalculateTaxServiceProxy();

                NewOrderResponseElement result = vtxProxy.newOrder(vtxRequest);
                return result;

            } catch (Exception e) {
                S21InfoLogOutput.println("Vertex Try:Exception Occured(" + (i + 1) + ")");
                if (i == (count - 1)) {
                    e.printStackTrace();
                    msgMap.addXxMsgId(NWZM1313E);
                } else {
                    sleep(sleepTime);
                }
            } catch (Throwable e) {
                e.printStackTrace();
                msgMap.addXxMsgId(NWZM1313E);
                break;
            }
        }
        // Add End 2019/04/10 QC#31046

        return null;
    }

    /**
     * setOutputParameterNoVertexCall
     * @param param NWZC036101PMsg
     */
    private void setOutputParameterNoVertexCall(NWZC036101PMsg param) {
        for (int index = 0; index == param.taxCalculateInputLine.getValidCount(); index++) {
            NWZC036101_taxCalculateOutputLinePMsg line = param.taxCalculateOutputLine.no(index++);

            ZYPEZDItemValueSetter.setValue(line.invLineFuncTaxAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(line.xxTaxCalcLineTaxPct, BigDecimal.ZERO);
        }

        msgMap.setPmsg(param);
    }

    /**
     * setOutputParameter
     * @param msgMap S21ApiMessageMap
     * @param vtxResponse NewOrderResponseElement
     */
    private void setOutputParameter(NewOrderResponseElement vtxResponse) {

        VertexEnvelopeType envelope = vtxResponse.getVertexEnvelope();

        NWZC036101PMsg param = (NWZC036101PMsg) msgMap.getPmsg();

        // QC#11618 2016/07/11 Mod Start
        // Integer scale = (Integer) ssmBatchClient.queryObject("getScale", param);
        Integer scale = DataCache.getInstance().getScaleFromCache(param, ssmBatchClient);
        // QC#11618 2016/07/11 Mod End

        if (scale == null) {
            msgMap.addXxMsgId(NWZM0368E);
            return;
        }

        param.taxCalculateOutputLine.clear();

        String mode = param.xxModeCd.getValue();

        if (PROC_MODE_QUOTATION.equals(mode)) {
            setOutputParameterForQuotationMode(param, envelope, scale);

        } else if (PROC_MODE_INVOICE.equals(mode)) {
            setOutputParameterForInvoiceMode(param, envelope, scale);

        } else {
            setOutputParameterForDistributeTaxMode(param, envelope, scale);

        }

        // 2017/12/22 S21_NA#22407 add start
        negateOutputAmt(param);
        // 2017/12/22 S21_NA#22407 add end

        ApplicationData applicationData = envelope.getApplicationData();

        if (applicationData != null) {
            LogEntryType[] logEntries = applicationData.getLogEntry();

            if (logEntries != null) {

                for (int i = 0; i < logEntries.length; i++) {
                    LogEntryType logEntry = logEntries[i];
                    String errCd = logEntry.getException();
                    String errMsg = logEntry.getMessage();

                    ZYPEZDItemValueSetter.setValue(param.taxCalculateOutputLine.no(i).xxTaxCalcLineErrCd, errCd);
                    ZYPEZDItemValueSetter.setValue(param.taxCalculateOutputLine.no(i).xxTaxCalcLineErrMsgTxt, errMsg);
                }
            }
        }

        msgMap.setPmsg(param);
    }

    private void setOutputParameterForQuotationMode(NWZC036101PMsg param, VertexEnvelopeType envelope, int scale) {

        QuotationResponseType response = envelope.getQuotationResponse();

        int count = 0;

        //QC#16531 add Start
        CustomerType customer = response.getCustomer();
        LocationType destination = null;
        BigInteger taxAreaId = null;
        if (customer != null) {
            destination = customer.getDestination();

            if (destination != null) {
                taxAreaId = destination.getTaxAreaId();
            }
        }
        //QC#16531 add End
        for (LineItemQSOType lineResponse : response.getLineItem()) {
            NWZC036101_taxCalculateOutputLinePMsg line = param.taxCalculateOutputLine.no(count++);

            if (lineResponse.getTotalTax() != null) {
                ZYPEZDItemValueSetter.setValue(line.invLineFuncTaxAmt, roundAmount(lineResponse.getTotalTax().get_value(), scale));
            }

            //QC#16531 del Start
//            CustomerType customer = lineResponse.getCustomer();
//
//            if (customer != null) {
//                LocationType destination = customer.getDestination();
//
//                if (destination != null) {
//                    BigInteger taxAreaId = destination.getTaxAreaId();
            //QC#16531 del End

                    if (taxAreaId != null) {
                        ZYPEZDItemValueSetter.setValue(line.taxAreaId, taxAreaId.toString());
                    }
//                }
//            }

            setTaxValues(line, lineResponse.getTaxes(), scale);
        }

        param.taxCalculateOutputLine.setValidCount(count);
    }

    private void setOutputParameterForInvoiceMode(NWZC036101PMsg param, VertexEnvelopeType envelope, int scale) {

        InvoiceResponseType response = envelope.getInvoiceResponse();

        int count = 0;

        //QC#16531 add Start
        CustomerType customer = response.getCustomer();
        LocationType destination = null;
        BigInteger taxAreaId = null;
        if (customer != null) {
            destination = customer.getDestination();

            if (destination != null) {
                taxAreaId = destination.getTaxAreaId();
            }
        }
        //QC#16531 add End
        for (LineItemISOType lineResponse : response.getLineItem()) {
            NWZC036101_taxCalculateOutputLinePMsg line = param.taxCalculateOutputLine.no(count++);

            if (lineResponse.getTotalTax() != null) {
                ZYPEZDItemValueSetter.setValue(line.invLineFuncTaxAmt, roundAmount(lineResponse.getTotalTax().get_value(), scale));
            }

            //QC#16531 del Start
//            CustomerType customer = lineResponse.getCustomer();
//
//            if (customer != null) {
//                LocationType destination = customer.getDestination();
//
//                if (destination != null) {
//                    BigInteger taxAreaId = destination.getTaxAreaId();
//
            //QC#16531 del End
                    if (taxAreaId != null) {
                        ZYPEZDItemValueSetter.setValue(line.taxAreaId, taxAreaId.toString());
                    }
//                }
//            }

            setTaxValues(line, lineResponse.getTaxes(), scale);
        }

        param.taxCalculateOutputLine.setValidCount(count);
    }

    private void setOutputParameterForDistributeTaxMode(NWZC036101PMsg param, VertexEnvelopeType envelope, int scale) {

        DistributeTaxResponseType response = envelope.getDistributeTaxResponse();

        int count = 0;

        //QC#16531 add Start
        CustomerType customer = response.getCustomer();
        LocationType destination = null;
        BigInteger taxAreaId = null;
        if (customer != null) {
            destination = customer.getDestination();

            if (destination != null) {
                taxAreaId = destination.getTaxAreaId();
            }
        }
        //QC#16531 add End
        for (LineItemDTSOType lineResponse : response.getLineItem()) {
            NWZC036101_taxCalculateOutputLinePMsg line = param.taxCalculateOutputLine.no(count++);

            if (lineResponse.getTotalTax() != null) {
                ZYPEZDItemValueSetter.setValue(line.invLineFuncTaxAmt, roundAmount(lineResponse.getTotalTax().get_value(), scale));
            }

            //QC#16531 del Start
//            CustomerType customer = lineResponse.getCustomer();
//
//            if (customer != null) {
//                LocationType destination = customer.getDestination();
//
//                if (destination != null) {
//                    BigInteger taxAreaId = destination.getTaxAreaId();
            //QC#16531 del End

                    if (taxAreaId != null) {
                        ZYPEZDItemValueSetter.setValue(line.taxAreaId, taxAreaId.toString());
                    }
//                }
//            }

            setTaxValues(line, lineResponse.getTaxes(), scale);
        }

        param.taxCalculateOutputLine.setValidCount(count);
    }

    private void setTaxValues(NWZC036101_taxCalculateOutputLinePMsg line, TaxesType[] taxes, int scale) {
        BigDecimal lineTaxPct = toPercentValue(new BigDecimal(0));

        // QC#18347 Add Start
        BigDecimal stateTaxAmt = BigDecimal.ZERO;
        BigDecimal stateTaxPct = BigDecimal.ZERO;
        BigDecimal countyTaxAmt = BigDecimal.ZERO;
        BigDecimal countyTaxPct = BigDecimal.ZERO;
        // QC#18347 Add End

//add K.Aratani QC4491 02/24/2016 Start --
        BigDecimal cityTaxAmt = BigDecimal.ZERO;
        BigDecimal cityTaxPct = BigDecimal.ZERO;
//add K.Aratani QC4491 02/24/2016 End --
        for (TaxesType tax : taxes) {
            String jurisdictionLevel = tax.getJurisdiction().getJurisdictionLevel().getValue();
            // QC#21106 2017/09/19 Add Start
            String convJurisdictionLevel = jurisdictionLevelPool.get(jurisdictionLevel);
            if (convJurisdictionLevel != null) {
                jurisdictionLevel = convJurisdictionLevel;
            //}
            // QC#21106 2017/09/19 Add End
            // QC#27970 2018/08/31 Add Start
            } else if (!(JurisdictionLevelCodeType._STATE.equals(jurisdictionLevel) || JurisdictionLevelCodeType._COUNTY.equals(jurisdictionLevel) || JurisdictionLevelCodeType._CITY.equals(jurisdictionLevel))) {
                jurisdictionLevel = JurisdictionLevelCodeType._CITY;
            }
            // QC#27970 2018/08/31 Add End

            BigDecimal calculatedTax = roundAmount(tax.getCalculatedTax().get_value(), scale);
            BigDecimal effectiveRate = toPercentValue(tax.getEffectiveRate());
            String taxResult = null;

            TaxResultCodeType taxResultCode = tax.getTaxResult();

            if (taxResultCode != null) {
                taxResult = taxResultCode.getValue();
            }
            // QC#18347 Mod Start
            if (JurisdictionLevelCodeType._STATE.equals(jurisdictionLevel)) {
                if (ZYPCommonFunc.hasValue(line.taxAmt_01)) {
                    stateTaxAmt = line.taxAmt_01.getValue().add(calculatedTax);
                } else {
                    stateTaxAmt = calculatedTax;
                }
                ZYPEZDItemValueSetter.setValue(line.taxAmt_01, stateTaxAmt);

                if (ZYPCommonFunc.hasValue(line.taxPct_01)) {
                    stateTaxPct = line.taxPct_01.getValue().add(effectiveRate);
                } else {
                    stateTaxPct = effectiveRate;
                }
                ZYPEZDItemValueSetter.setValue(line.taxPct_01, stateTaxPct);

                if (!ZYPCommonFunc.hasValue(line.xxVtxRsltCd_01) && ZYPCommonFunc.hasValue(taxResult)) {
                    ZYPEZDItemValueSetter.setValue(line.xxVtxRsltCd_01, taxResult);
                }
            } else if (JurisdictionLevelCodeType._COUNTY.equals(jurisdictionLevel) || JurisdictionLevelCodeType._DISTRICT.equals(jurisdictionLevel)) {
                if (ZYPCommonFunc.hasValue(line.taxAmt_02)) {
                    countyTaxAmt = line.taxAmt_02.getValue().add(calculatedTax);
                } else {
                    countyTaxAmt = calculatedTax;
                }
                ZYPEZDItemValueSetter.setValue(line.taxAmt_02, countyTaxAmt);

                if (ZYPCommonFunc.hasValue(line.taxPct_02)) {
                    countyTaxPct = line.taxPct_02.getValue().add(effectiveRate);
                } else {
                    countyTaxPct = effectiveRate;
                }
                ZYPEZDItemValueSetter.setValue(line.taxPct_02, countyTaxPct);

                if (!ZYPCommonFunc.hasValue(line.xxVtxRsltCd_02) && ZYPCommonFunc.hasValue(taxResult)) {
                    ZYPEZDItemValueSetter.setValue(line.xxVtxRsltCd_02, taxResult);
                }
                // QC#18347 Mod End
            } else if (JurisdictionLevelCodeType._CITY.equals(jurisdictionLevel)) {
                // mod K.Aratani QC4491 02/24/2016 Start --
                if (ZYPCommonFunc.hasValue(line.taxAmt_03)) {
                    cityTaxAmt = line.taxAmt_03.getValue().add(calculatedTax);
                } else {
                    cityTaxAmt = calculatedTax;
                }

                ZYPEZDItemValueSetter.setValue(line.taxAmt_03, cityTaxAmt);

                if (ZYPCommonFunc.hasValue(line.taxPct_03)) {
                    cityTaxPct = line.taxPct_03.getValue().add(effectiveRate);
                } else {
                    cityTaxPct = effectiveRate;
                }
                ZYPEZDItemValueSetter.setValue(line.taxPct_03, cityTaxPct);

                if (!ZYPCommonFunc.hasValue(line.xxVtxRsltCd_03) && ZYPCommonFunc.hasValue(taxResult)) {
                    ZYPEZDItemValueSetter.setValue(line.xxVtxRsltCd_03, taxResult);
                }
                // mod K.Aratani QC4491 02/24/2016 End --
                // add K.Aratani QC4491 02/24/2016 End --

                // QC#18347 Del End
            }

            lineTaxPct = lineTaxPct.add(effectiveRate);
        }

        ZYPEZDItemValueSetter.setValue(line.xxTaxCalcLineTaxPct, lineTaxPct);
    }

    /**
     * to Percent Value
     * @param BigDecimal rate
     * @return BigDecimal
     */
    private BigDecimal toPercentValue(BigDecimal rate) {
        if (rate == null) {
            return BigDecimal.ZERO;
        }
        return rate.multiply(new BigDecimal(TO_PERCENT_VALUE)).setScale(PERCENT_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * round Amount
     * @param BigDecimal amount
     * @param Integer scale
     * @return BigDecimal
     */
    private BigDecimal roundAmount(BigDecimal amount, Integer scale) {
        if (amount == null) {
            return BigDecimal.ZERO;
        }
        return amount.setScale(scale, RoundingMode.HALF_UP);
    }

    // 201/12/22 S21_NA#22407 add start
    private void negateInputAmt (NWZC036101PMsg param) {
        if (ZYPCommonFunc.hasValue(param.invTpCd) && INV_TP.CREDIT_MEMO.equals(param.invTpCd.getValue()) && PROC_MODE_INVOICE.equals(param.xxModeCd.getValue())) {
            for (int i = 0; i < param.taxCalculateInputLine.getValidCount();i++) {
                NWZC036101_taxCalculateInputLinePMsg dtlParam = param.taxCalculateInputLine.no(i);
                if (ZYPCommonFunc.hasValue(dtlParam.funcNetUnitPrcAmt_A)) {
                    ZYPEZDItemValueSetter.setValue(dtlParam.funcNetUnitPrcAmt_A, dtlParam.funcNetUnitPrcAmt_A.getValue().negate());
                }

                if (ZYPCommonFunc.hasValue(dtlParam.slsAmt_A)) {
                    ZYPEZDItemValueSetter.setValue(dtlParam.slsAmt_A, dtlParam.slsAmt_A.getValue().negate());
                }

                if (ZYPCommonFunc.hasValue(dtlParam.taxAmt_A)) {
                    ZYPEZDItemValueSetter.setValue(dtlParam.taxAmt_A, dtlParam.taxAmt_A.getValue().negate());
                }
            }
        }
    }

    private void negateOutputAmt (NWZC036101PMsg param) {
        if (ZYPCommonFunc.hasValue(param.invTpCd) && INV_TP.CREDIT_MEMO.equals(param.invTpCd.getValue()) && PROC_MODE_INVOICE.equals(param.xxModeCd.getValue())) {
            for (int i = 0; i < param.taxCalculateOutputLine.getValidCount();i++) {
                NWZC036101_taxCalculateOutputLinePMsg dtlParam = param.taxCalculateOutputLine.no(i);
                if (ZYPCommonFunc.hasValue(dtlParam.invLineFuncTaxAmt)) {
                    ZYPEZDItemValueSetter.setValue(dtlParam.invLineFuncTaxAmt, dtlParam.invLineFuncTaxAmt.getValue().negate());
                }

                if (ZYPCommonFunc.hasValue(dtlParam.taxAmt_01)) {
                    ZYPEZDItemValueSetter.setValue(dtlParam.taxAmt_01, dtlParam.taxAmt_01.getValue().negate());
                }

                if (ZYPCommonFunc.hasValue(dtlParam.taxAmt_02)) {
                    ZYPEZDItemValueSetter.setValue(dtlParam.taxAmt_02, dtlParam.taxAmt_02.getValue().negate());
                }

                if (ZYPCommonFunc.hasValue(dtlParam.taxAmt_03)) {
                    ZYPEZDItemValueSetter.setValue(dtlParam.taxAmt_03, dtlParam.taxAmt_03.getValue().negate());
                }

                if (ZYPCommonFunc.hasValue(dtlParam.taxAmt_04)) {
                    ZYPEZDItemValueSetter.setValue(dtlParam.taxAmt_04, dtlParam.taxAmt_04.getValue().negate());
                }

                if (ZYPCommonFunc.hasValue(dtlParam.taxAmt_05)) {
                    ZYPEZDItemValueSetter.setValue(dtlParam.taxAmt_05, dtlParam.taxAmt_05.getValue().negate());
                }

                if (ZYPCommonFunc.hasValue(dtlParam.taxAmt_06)) {
                    ZYPEZDItemValueSetter.setValue(dtlParam.taxAmt_06, dtlParam.taxAmt_06.getValue().negate());
                }
            }
        }
    }
    // 201/12/22 S21_NA#22407 add start

    // Add Start 2019/04/10 QC#31046
    /**
     * Sleep
     * @param millSec
     */
    private void sleep(long millSec) {
        try {
            Thread.sleep(millSec);
        } catch (InterruptedException ie) {
        }
    }
    // Add End 2019/04/10 QC#31046

    // START 2019/09/21 S.Takami [QC#53650,ADD]
    private FlexibleFields getFlexibleFieldsForShipToCustName(NWZC036101PMsg param, NWZC036101_taxCalculateInputLinePMsg lineParam) {

        String shipToCustCd = lineParam.shipToLocNum_A.getValue();
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            shipToCustCd = param.shipToLocNum.getValue();
        }
        // START 2019/10/10 S.Takami [QC#54078,ADD]
        long id = 1L;

        FlexibleFields flexibleFields = new FlexibleFields();
        List<FlexibleCodeField> flexibleCodeFieldList = new ArrayList<FlexibleCodeField>(0);

        String shipToAcctCd = lineParam.dsAcctNum_AT.getValue();
        if (!ZYPCommonFunc.hasValue(shipToAcctCd)) {
            shipToAcctCd = param.dsAcctNum_ST.getValue();
            if (!ZYPCommonFunc.hasValue(shipToAcctCd) && ZYPCommonFunc.hasValue(shipToCustCd)) {
                shipToAcctCd = DataCache.getInstance().getLocNameFromCustCd(param, shipToCustCd,  LOC_ROLE_TP.SHIP_TO, "ACCT_CD", ssmBatchClient);
            }
        }
        if (ZYPCommonFunc.hasValue(shipToAcctCd)) {
            FlexibleCodeField shipToCustCdFlexibleCodeFiled = new FlexibleCodeField();

            shipToCustCdFlexibleCodeFiled.setFieldId(BigInteger.valueOf(id));
            shipToCustCdFlexibleCodeFiled.set_value(shipToAcctCd);
            flexibleCodeFieldList.add(shipToCustCdFlexibleCodeFiled);
        }
        id += 1L;
        // END 2019/10/10 S.Takami [QC#54078,ADD]
        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            String shipToCustNm = DataCache.getInstance().getLocNameFromCustCd(param, shipToCustCd,  LOC_ROLE_TP.SHIP_TO, "LOC_NM", ssmBatchClient);
            if (shipToCustNm.length() > MAX_SHIP_TO_NAME_LENGTH_FOR_VERTEX) {
                shipToCustNm = shipToCustNm.substring(0, MAX_SHIP_TO_NAME_LENGTH_FOR_VERTEX);
            }
            if (ZYPCommonFunc.hasValue(shipToCustNm)) {
                // START 2019/10/10 S.Takami [QC#54078,DEL]
//                FlexibleFields flexibleFields = new FlexibleFields();
//                List<FlexibleCodeField> flexibleCodeFieldList = new ArrayList<FlexibleCodeField>(0);
                // END 2019/10/10 S.Takami [QC#54078,DEL]

                FlexibleCodeField flexibleCodeFiled = new FlexibleCodeField();

                // START 2019/10/10 S.Takami [QC#54078,MOD]
//                flexibleCodeFiled.setFieldId(FLEX_FLD_ID_SHIP_TO_CUST_NM);
                flexibleCodeFiled.setFieldId(BigInteger.valueOf(id));
                // END 2019/10/10 S.Takami [QC#54078,MOD]
                flexibleCodeFiled.set_value(shipToCustNm);
                flexibleCodeFieldList.add(flexibleCodeFiled);

                // START 2019/10/10 S.Takami [QC#54078,DEL]
//                flexibleFields.setFlexibleCodeField(flexibleCodeFieldList.toArray(new FlexibleCodeField[flexibleCodeFieldList.size()]));
//                return flexibleFields;
                // END 2019/10/10 S.Takami [QC#54078,DEL]
            }
        }
        // Start 2019/10/10 S.Takami [QC#54078,ADD]
        if (flexibleCodeFieldList.size() > 0) {
            flexibleFields.setFlexibleCodeField(flexibleCodeFieldList.toArray(new FlexibleCodeField[flexibleCodeFieldList.size()]));
            return flexibleFields;
        }
        // END 2019/10/10 S.Takami [QC#54078,ADD]
        return null;
    }
    // END 2019/09/21 S.Takami [QC#53650,ADD]
}
