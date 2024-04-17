/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC014001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.COA_BRTMsg;
import business.db.COA_EXTNTMsg;
import business.db.CPO_SRC_TPTMsg;
import business.db.CTRYTMsg;
import business.db.DS_ORD_CATGTMsg;
import business.db.DS_ORD_LINE_CATGTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.DS_PMT_METHTMsg;
import business.db.FRT_CHRG_METHTMsg;
import business.db.FRT_CHRG_TOTMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDSE_TP_VAL_SETTMsg;
import business.db.MDSE_TP_VAL_SETTMsgArray;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.ORD_LINE_VAL_SETTMsg;
import business.db.ORD_LINE_VAL_SETTMsgArray;
import business.db.PRC_CALC_EXCL_SWHTMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_CONTRTMsg;
import business.db.PRC_CONTRTMsgArray;
import business.db.PRC_RULE_TRX_CATGTMsg;
import business.db.RTRN_RSNTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.STTMsg;
import business.db.SVC_CALL_TPTMsg;
import business.parts.NWXC010001PMsg;
import business.parts.NWXC010002PMsg;
import business.parts.NWXC011001PMsg;
import business.parts.NWXC011002PMsg;
import business.parts.NWXC012001PMsg;
import business.parts.NWXC012002PMsg;
import business.parts.NWXC013001PMsg;
import business.parts.NWXC013002PMsg;
import business.parts.NWXC014001PMsg;
import business.parts.NWXC014002PMsg;
import business.parts.NWXC014003PMsg;
import business.parts.NWXC014004PMsg;
import business.parts.NWZC170001PMsg;

import com.canon.cusa.s21.api.NWX.NWXC010001.NWXC010001;
import com.canon.cusa.s21.api.NWX.NWXC011001.NWXC011001;
import com.canon.cusa.s21.api.NWX.NWXC012001.NWXC012001;
import com.canon.cusa.s21.api.NWX.NWXC013001.NWXC013001;
import com.canon.cusa.s21.api.NWX.NWXC014001.cache.DataCacheForSSM;
import com.canon.cusa.s21.api.NWX.NWXC014001.cache.DataCacheForTBLAccessor;
import com.canon.cusa.s21.api.NWX.NWXC014001.cache.FindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC170001.NWZC170001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FUNC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRCD_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_MOD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_OP_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Price Rule Derivation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/10   Fujitsu         Y.Kanefusa      Create          N/A
 * 2015/12/09   Fujitsu         Y.Kanefusa      Update          S21_NA#1713
 * 2015/12/14   Fujitsu         Y.Kanefusa      Update          S21_NA#385
 * 2016/06/10   Fujitsu         Y.Kanefusa      Update          S21_NA#9482
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/07/12   Fujitsu         T.Yoshida       Update          S21_NA#11618
 * 2016/08/04   Fujitsu         T.Yoshida       Update          S21_NA#12895
 * 2016/08/23   Fujitsu         Y.Kanefusa      Update          S21_NA#6098
 * 2016/09/14   Fujitsu         Y.Kanefusa      Update          S21_NA#14256
 * 2016/09/23   Fujitsu         Y.Kanefusa      Update          S21_NA#13520
 * 2016/10/25   Fujitsu         Y.Kanefusa      Update          S21_NA#15318
 * 2016/10/26   Fujitsu         T.Yoshida       Update          S21_NA#11618-17
 * 2016/10/27   Fujitsu         T.Yoshida       Update          S21_NA#11618-18
 * 2016/11/01   Fujitsu         Y.Kanefusa      Update          S21_NA#15666
 * 2017/06/19   Fujitsu         Y.Kanefusa      Update          S21_NA#19319
 * 2017/07/14   Fujitsu         A.Kosai         Update          S21_NA#18191
 * 2017/08/17   Fujitsu         M.Ohno          Update          S21_NA#18789(L3)
 * 2017/08/09   Fujitsu         Y.Kanefusa      Update          S21_NA#20249
 * 2017/09/28   Fujitsu         Y.Kanefusa      Update          S21_NA#21390
 * 2017/11/02   Fujitsu         Y.Kanefusa      Update          S21_NA#22241
 * 2017/12/06   Fujitsu         Y.Kanefusa      Update          S21_NA#22933
 * 2017/12/26   Fujitsu         Y.Kanefusa      Update          S21_NA#22371
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/04/24   Fujitsu         H.Tomimatsu     Update          S21_NA#22569
 * 2018/05/13   Fujitsu         Y.Kanefusa      Update          S21_NA#25422
 * 2018/05/21   Fujitsu         Y.Kanefusa      Update          S21_NA#21841
 * 2018/07/05   Fujitsu         R.Nakamura      Update          S21_NA#26197
 * 2018/08/22   Fujitsu         Y.Kanefusa      Update          S21_NA#27713
 * 2018/08/27   Fujitsu         Y.Kanefusa      Update          S21_NA#27792
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * 2018/10/09   Fujitsu         Y.Kanefusa      Update          S21_NA#28701
 * 2018/11/09   Fujitsu         Y.Kanefusa      Update          S21_NA#28995
 * 2018/12/12   Fujitsu         Y.Kanefusa      Update          S21_NA#29316
 * 2018/12/22   Fujitsu         Y.Kanefusa      Update          S21_NA#29410
 * 2018/12/28   Fujitsu         Y.Kanefusa      Update          S21_NA#29565
 * 2019/10/04   Fujitsu         Y.Kanefusa      Update          S21_NA#53586
 * 2023/10/26   Hitachi         T.Fukuta        Update          CSA-QC#61619
 * </pre>
 */
public class NWXC014001 extends S21ApiCommonBase {

    /** PROGRAM_ID */
    private static final String PROGRAM_ID = "NWXC014001";

    /** Data Global Company Code is not entered. */
    private static final String NWZM0163E = "NWZM0163E";

    /** Price Based Date of the parameter is not set. */
    private static final String NWZM1155E = "NWZM1155E";

    /** The parameter's "Line Business Type Code" is not set. */
    private static final String NWZM1783E = "NWZM1783E";

    /** The entered "DS Order Category Code" does not exist in the Master. */
    private static final String NWZM1415E = "NWZM1415E";

    /** The entered "DS Order Type Code" does not exist in the Master. */
    private static final String NWZM1815E = "NWZM1815E";

    /** Sell To Customer Code does not exist in master. */
    private static final String NWZM1133E = "NWZM1133E";

    /** Invalid value is set for process mode. */
    private static final String NWZM0977E = "NWZM0977E";

    /** The {@} parameter's "{@}" is not set. */
    private static final String NWZM1325E = "NWZM1325E";

    /** Parameter {@}={@} does not exists in {@}. */
    private static final String NWZM1326E = "NWZM1326E";

    /** Parameter {@} is invalid value. */
    private static final String NWZM1400E = "NWZM1400E";

    /** The entered "Bill to Account Code" does not exist in the Master. */
    private static final String NWZM1416E = "NWZM1416E";

    /** The entered "Ship to Account Code" does not exist in the Master. */
    private static final String NWZM1417E = "NWZM1417E";

    /** Pricing Rule Setting Error, Rule Precedence is incorrect */
    private static final String NWZM1498E = "NWZM1498E";

    /** "Merchandise Code" does not exist in the Master. */
    private static final String NWZM0293E = "NWZM0293E";

    /** Multiple Price Group PK (QtyBreak) are obtained. Please check Master data. */
    private static final String NWZM2238E = "NWZM2238E";

    /** PROCESS_MODE_AUTO */
    public static final String PROCESS_MODE_AUTO = "01";

    /** PROCESS_MODE_MANUAL */
    public static final String PROCESS_MODE_MANUAL = "02";

    /** PROCESS_LVL_HEADER */
    public static final String PROCESS_LVL_HEADER = "01";

    /** PROCESS_LVL_LINE */
    public static final String PROCESS_LVL_LINE = "02";

    /** PROCESS_LVL_ALL */
    public static final String PROCESS_LVL_ALL = "03";

    /** MODIFY_TYPE_CHARGE */
    public static final String MODIFY_TYPE_CHARGE = "01";

    /** MODIFY_TYPE_SELL_PRICE */
    public static final String MODIFY_TYPE_SELL_PRICE = "02";

    /** MODIFY_TYPE_ALL */
    public static final String MODIFY_TYPE_ALL = "03";

    /** DS_ORD_LINE_DRCTN_CD_I */
    private static final String DS_ORD_LINE_DRCTN_CD_I = "I";

    /** DS_ORD_LINE_DRCTN_CD_O */
    private static final String DS_ORD_LINE_DRCTN_CD_O = "O";

    /** NEGO_PRC_ELIGIBLE */
    private static final String NEGO_PRC_ELIGIBLE = "NEGO_PRC_ELIGIBLE";

    /** STRING_EMPTY */
    private static final String STRING_EMPTY = "";

    /** WILDCARD */
    private static final String WILDCARD = "*";

    /** dsOrdLineDrctnCd */
    private String dsOrdLineDrctnCd = null;

    /** negoFlg */
    private boolean negoFlg = false;

    /** negoBean */
    private NWXC014001prcRuleWrkBean negoBean = null;

    /** negativeValue */
    private static final BigDecimal MINUS = new BigDecimal(-1);

    /** RATE_DIGIT */
    private static final int RATE_DIGIT = 6;

    // For Performance QC#11618 Mod Start
    /** Price Grouping Cache : Material */
    private Map<String, List<String>> materialPrcGrpCache = new HashMap<String, List<String>>();

    /** Price Grouping Cache : Ship To */
    private Map<String, List<String>> customerPrcGrpSHCache = new HashMap<String, List<String>>();

    /** Price Grouping Cache : Bill To */
    private Map<String, List<String>> customerPrcGrpBLCache = new HashMap<String, List<String>>();

    /** Price Grouping Cache : Transaction */
    private Map<String, List<String>> transactionPrcGrpCache = new HashMap<String, List<String>>();

    /** Price Grouping Cache : Sold To */
    private Map<String, List<String>> customerPrcGrpCache = new HashMap<String, List<String>>();

    /** Price Grouping Cache : Freight Zone */
    private Map<String, List<String>> frtZoneCache = new HashMap<String, List<String>>();
    // For Performance QC#11618 Mod End

    // QC#20249 2017/08/09 Add Start
    /** Price Grouping Cache : Material */
    private Map<String, List<String>> materialPrcGrpQtyBreakCache = new HashMap<String, List<String>>();
    // QC#20249 2017/08/09 Add End

    /** Calculate Result Cache */
    private Map<List<Object>, Map<List<String>, List<NWXC014003PMsg>>> resultCache = new HashMap<List<Object>, Map<List<String>, List<NWXC014003PMsg>>>();

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmBatchClient;

    // S21_NA#11618-17 Add Start
    /** SSM Param For Material */
    private Map<String, Object> paramForMaterial = null;

    /** SSM Param For Transaction */
    private Map<String, Object> paramForTransaction = null;

    /** SSM Param For CustomerPrc */
    private Map<String, Object> paramForCustomerPrc = null;

    /** SSM Param For CustomerPrc SH */
    private Map<String, Object> paramForCustomerPrcSH = null;

    /** SSM Param For CustomerPrc BL */
    private Map<String, Object> paramForCustomerPrcBL = null;

    /** SSM Param For Freight Zone */
    private Map<String, Object> paramForFrtZone = null;

    /** SSM Param For Price Rule */
    private Map<String, Object> paramForPrcRule = null;
    // S21_NA#11618-17 Add End
    // QC#20249 2017/08/09 Add Start
    /** Price Grouping Cache : Material (Qty Break)*/
    private Map<String, Object> paramForMaterialQtyBreak = null;

    /** */
    Map<String, String> materilGroupQtyBreakPool = null;

    /** */
    Map<String, BigDecimal> materialGroupQty = null;

    // QC#20249 2017/08/09 Add End

    /** Constructor */
    public NWXC014001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute 
     * @param param NWXC014001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NWXC014001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (paramCheck(msgMap)) {
            msgMap.flush();
            return;
        }

        List<NWXC014001prcRuleWrkBean> list = null;
        List<NWXC014001prcRuleWrkBean> sellList = new ArrayList<NWXC014001prcRuleWrkBean>();
        List<NWXC014001prcRuleWrkBean> chargeList = new ArrayList<NWXC014001prcRuleWrkBean>();
        List<NWXC014001prcRuleWrkBean> sellListAll = new ArrayList<NWXC014001prcRuleWrkBean>();
        List<NWXC014001prcRuleWrkBean> chargeListAll = new ArrayList<NWXC014001prcRuleWrkBean>();
        List<NWXC014001prcRuleWrkBean> qualifiedList = new ArrayList<NWXC014001prcRuleWrkBean>(); // QC#9700  2018/09/03 Add
        NWXC014002PMsg line = null;

        // S21_NA#11618-17 Add Start
        paramForMaterial = getSsmParamForMaterial(param);
        paramForTransaction = getSsmParamForTransaction(param);
        paramForCustomerPrc = getSsmParamForCustomerPrc(param);
        paramForCustomerPrcSH = getSsmParamForCustomerPrcSH(param);
        paramForCustomerPrcBL = getSsmParamForCustomerPrcBL(param);
        paramForFrtZone = getSsmParamForFrtZone(param);
        paramForPrcRule = getSsmParamForPrcRule(param);
        // S21_NA#11618-17 Add End

        // Qty Break
        // QC#20249 2017/08/09 Add Start
        paramForMaterialQtyBreak = getSsmParamForMaterialQtyBreak(param);

        materilGroupQtyBreakPool = new HashMap<String, String>();
        materialGroupQty = new HashMap<String, BigDecimal>();
        BigDecimal qty = null;
        for (int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++) {
            line = param.NWXC014002PMsg.no(i);
            List<String> materialPrcGrpQtyBreak = getMaterialPrcGrpQtyBreak(param, line);
            if (materialPrcGrpQtyBreak == null) {
                continue;
            } else if (materialPrcGrpQtyBreak.size() > 1) {
                materilGroupQtyBreakPool.put(line.trxLineNum.getValue(), null);
                S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
                lineMap.addXxMsgIdWithPrm(NWZM2238E, null);
                lineMap.flush();
            } else {
                String prcGrpPk = materialPrcGrpQtyBreak.get(0);
                materilGroupQtyBreakPool.put(line.trxLineNum.getValue(), prcGrpPk);
                qty = materialGroupQty.get(prcGrpPk);
                if (qty == null) {
                    materialGroupQty.put(prcGrpPk, line.ordQty.getValue());
                } else {
                    materialGroupQty.put(prcGrpPk, qty.add(line.ordQty.getValue()));
                }
            }
        }
        // QC#20249 2017/08/09 Add End

        // QC#22241 2017/11/02 Mod Start
        //for (int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++) {
        //    line = param.NWXC014002PMsg.no(i);
        //    list = getPrcRule(param, line);
        //    sellListAll.addAll(editPrcRuleWrkBean(param, getPrcRuleWrkBean(param, list, PRC_RULE_MOD_TP.SELL_PRICE)));
        //    chargeListAll.addAll(editPrcRuleWrkBean(param, getPrcRuleWrkBean(param, list, PRC_RULE_MOD_TP.CHARGES)));
        //}
        //if (PROCESS_MODE_MANUAL.equals(param.xxModeCd_PM.getValue())) {
        //    editReturnParam(param, sellListAll, chargeListAll);
        //} else {
        //    sellListAll = calculatePrcRule(param, sellListAll, PRC_RULE_MOD_TP.SELL_PRICE, onBatchType);
        //    chargeListAll = calculatePrcRule(param, chargeListAll, PRC_RULE_MOD_TP.CHARGES, onBatchType);
        //    editReturnParam(param, sellListAll, chargeListAll);
        //}

        if (PROCESS_MODE_MANUAL.equals(param.xxModeCd_PM.getValue())) {
            for (int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++) {
                line = param.NWXC014002PMsg.no(i);
                list = getPrcRule(param, line, null);
                sellListAll.addAll(editPrcRuleWrkBean(param, getPrcRuleWrkBean(param, list, PRC_RULE_MOD_TP.SELL_PRICE), qualifiedList));
                chargeListAll.addAll(editPrcRuleWrkBean(param, getPrcRuleWrkBean(param, list, PRC_RULE_MOD_TP.CHARGES), qualifiedList));
            }
            editReturnParam(param, sellListAll, chargeListAll);
        } else {
            for (int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++) {
                line = param.NWXC014002PMsg.no(i);
                list = getPrcRule(param, line, PRC_RULE_MOD_TP.SELL_PRICE);
                sellList.addAll(editPrcRuleWrkBean(param, getPrcRuleWrkBean(param, list, PRC_RULE_MOD_TP.SELL_PRICE), qualifiedList));
            }
            sellListAll = calculatePrcRule(param, sellList, PRC_RULE_MOD_TP.SELL_PRICE, onBatchType, qualifiedList);
            for (int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++) {
                line = param.NWXC014002PMsg.no(i);
                list = getPrcRule(param, line, PRC_RULE_MOD_TP.CHARGES);
                chargeList.addAll(editPrcRuleWrkBean(param, getPrcRuleWrkBean(param, list, PRC_RULE_MOD_TP.CHARGES), qualifiedList));
            }
            // START 2023/10/26 T.Fukuta [CSA-QC#61619,ADD]
            reconstructRuleListForQtyBasedFeeApi(param, chargeList);
            // END 2023/10/26 T.Fukuta [CSA-QC#61619,ADD]
            chargeListAll = calculatePrcRule(param, chargeList, PRC_RULE_MOD_TP.CHARGES, onBatchType, qualifiedList);
            setQuplifiedCalcBase(param, qualifiedList); // QC#9700  2018/09/03 Add
            editReturnParam(param, sellListAll, chargeListAll);
        }
        // QC#22241 2017/11/02 Mod End
        return;
    }

    /**
     * execute
     * @param params List<NWXC014001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWXC014001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NWXC014001PMsg param : params) {
            execute(param, onBatchType);
        }
    }

    private boolean paramCheck(S21ApiMessageMap msgMap) {
        NWXC014001PMsg param = (NWXC014001PMsg) msgMap.getPmsg();
        // Global Company Code
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgIdWithPrm(NWZM0163E, null);
            msgMap.flush();
            return true;
        }
        // Process Mode
        if (!PROCESS_MODE_AUTO.equals(param.xxModeCd_PM.getValue()) && !PROCESS_MODE_MANUAL.equals(param.xxModeCd_PM.getValue())) {
            msgMap.addXxMsgIdWithPrm(NWZM0977E, null);
            msgMap.flush();
            return true;
        }
        // Process Level
        if (!PROCESS_LVL_HEADER.equals(param.xxModeCd_PM.getValue()) && !PROCESS_LVL_LINE.equals(param.xxModeCd_PM.getValue()) && !PROCESS_LVL_ALL.equals(param.xxModeCd_PM.getValue())) {
            msgMap.addXxMsgIdWithPrm(NWZM1400E, new String[] {"Process Level" });
            msgMap.flush();
            return true;
        }
        // Modify Type Code
        if (!MODIFY_TYPE_CHARGE.equals(param.xxModeCd_MT.getValue()) && !MODIFY_TYPE_SELL_PRICE.equals(param.xxModeCd_MT.getValue()) && !MODIFY_TYPE_ALL.equals(param.xxModeCd_MT.getValue())) {
            msgMap.addXxMsgIdWithPrm(NWZM1400E, new String[] {"Modify Type Code" });
            msgMap.flush();
            return true;
        }
        // Price Based Date
        if (!ZYPCommonFunc.hasValue(param.prcBaseDt)) {
            msgMap.addXxMsgIdWithPrm(NWZM1155E, null);
            msgMap.flush();
            return true;
        }
        // Price Rule Transaction Category Code
        if (!ZYPCommonFunc.hasValue(param.prcRuleTrxCatgCd)) {
            msgMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Price Rule Transaction Category Code" });
            msgMap.flush();
            return true;
        } else {
            PRC_RULE_TRX_CATGTMsg prcRuleTrxCatgTMsg = getPrcRuleTrxCatg(param.glblCmpyCd.getValue(), param.prcRuleTrxCatgCd.getValue());
            if (prcRuleTrxCatgTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Price Rule Transaction Category Code", param.prcRuleTrxCatgCd.getValue(), "PRC_RULE_TRX_CATG" });
                msgMap.flush();
                return true;
            }
        }
        // DS Order Category Code
        if (ZYPCommonFunc.hasValue(param.dsOrdCatgCd)) {
            DS_ORD_CATGTMsg dsOrdCatgTMsg = getDsOrdCatg(param.glblCmpyCd.getValue(), param.dsOrdCatgCd.getValue());
            if (dsOrdCatgTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1415E, null);
                msgMap.flush();
                return true;
            }
        }
        // DS Order Type Code
        if (ZYPCommonFunc.hasValue(param.dsOrdTpCd)) {
            DS_ORD_TPTMsg dsOrdTpTMsg = getDsOrdTp(param.glblCmpyCd.getValue(), param.dsOrdTpCd.getValue());
            if (dsOrdTpTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1815E, null);
                msgMap.flush();
                return true;
            }
        }
        // Line Business Type Code
        if (!ZYPCommonFunc.hasValue(param.lineBizTpCd)) {
            msgMap.addXxMsgIdWithPrm(NWZM1783E, null);
            msgMap.flush();
            return true;
        } else {
            LINE_BIZ_TPTMsg lineBizTpTMsg = getLineBizTp(param.glblCmpyCd.getValue(), param.lineBizTpCd.getValue());
            if (lineBizTpTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Line Business Type Code", param.lineBizTpCd.getValue(), "LINE_BIZ_TP" });
                msgMap.flush();
                return true;
            }
        }

        // Sold to Account Number
        if (ZYPCommonFunc.hasValue(param.dsAcctNum)) {
            Map<String, String> dsAcctCustMap = (Map<String, String>) getDsAcctCust(param.glblCmpyCd.getValue(), param.dsAcctNum.getValue(), param.prcBaseDt.getValue());
            if (dsAcctCustMap == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1133E, null);
                msgMap.flush();
                return true;
            } else {
                ZYPEZDItemValueSetter.setValue(param.dsAcctNm, dsAcctCustMap.get("DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(param.dsCustSicCd, dsAcctCustMap.get("DS_CUST_SIC_CD"));
                ZYPEZDItemValueSetter.setValue(param.locNum, dsAcctCustMap.get("LOC_NUM"));
                ZYPEZDItemValueSetter.setValue(param.dsAcctClsCd, dsAcctCustMap.get("DS_ACCT_CLS_CD"));
                ZYPEZDItemValueSetter.setValue(param.dsAcctTpCd, dsAcctCustMap.get("DS_ACCT_TP_CD"));
                ZYPEZDItemValueSetter.setValue(param.dsAcctDlrCd, dsAcctCustMap.get("DS_ACCT_DLR_CD"));
                ZYPEZDItemValueSetter.setValue(param.dsAcctGrpCd, dsAcctCustMap.get("DS_ACCT_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(param.coaChCd, dsAcctCustMap.get("COA_CH_CD"));
            }
        }

        // CPO Source Type
        if (ZYPCommonFunc.hasValue(param.cpoSrcTpCd)) {
            CPO_SRC_TPTMsg cpoSrcTpTMsg = getCpoSrcTp(param.glblCmpyCd.getValue(), param.cpoSrcTpCd.getValue());
            if (cpoSrcTpTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"CPO Source Type", param.cpoSrcTpCd.getValue(), "CPO_SRC_TP" });
                msgMap.flush();
                return true;
            }
        }

        // Payment Method
        if (ZYPCommonFunc.hasValue(param.dsPmtMethCd)) {
            DS_PMT_METHTMsg dsPmtMethCdTMsg = getDsPmtMeth(param.glblCmpyCd.getValue(), param.dsPmtMethCd.getValue());
            if (dsPmtMethCdTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Payment Method", param.dsPmtMethCd.getValue(), "DS_PMT_METH" });
                msgMap.flush();
                return true;
            }
        }

        // Level-2
        for (int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++) {
            NWXC014002PMsg line = param.NWXC014002PMsg.no(i);
            S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
            String glblCmpyCd = param.glblCmpyCd.getValue();

            if (!ZYPCommonFunc.hasValue(line.prcBaseDt)) {
                ZYPEZDItemValueSetter.setValue(line.prcBaseDt, param.prcBaseDt);
            }
            // Ship to Customer Code
            if (ZYPCommonFunc.hasValue(line.shipToCustCd)) {
                SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCust(glblCmpyCd, line.shipToCustCd.getValue());
                if (shipToCustTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Ship to Customer Code", line.dsAcctNm_SH.getValue(), "SHIP_TO_CUST" });
                    lineMap.flush();
                    return true;
                }
            }

            if (ZYPCommonFunc.hasValue(line.dsAcctNum_SH)) {
                Map<String, String> dsAcctCustMap = (Map<String, String>) getDsAcctCust(param.glblCmpyCd.getValue(), line.dsAcctNum_SH.getValue(), param.prcBaseDt.getValue());
                if (dsAcctCustMap == null) {
                    msgMap.addXxMsgIdWithPrm(NWZM1417E, new String[] {"Ship to Account Number", line.dsAcctNum_SH.getValue(), "SELL_TO_CUST" });
                    msgMap.flush();
                    return true;
                } else {
                    ZYPEZDItemValueSetter.setValue(line.dsAcctNm_SH, dsAcctCustMap.get("DS_ACCT_NM"));
                    ZYPEZDItemValueSetter.setValue(line.dsCustSicCd_SH, dsAcctCustMap.get("DS_CUST_SIC_CD"));
                    ZYPEZDItemValueSetter.setValue(line.locNum_SH, dsAcctCustMap.get("LOC_NUM"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctClsCd_SH, dsAcctCustMap.get("DS_ACCT_CLS_CD"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctTpCd_SH, dsAcctCustMap.get("DS_ACCT_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctDlrCd_SH, dsAcctCustMap.get("DS_ACCT_DLR_CD"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctGrpCd_SH, dsAcctCustMap.get("DS_ACCT_GRP_CD"));
                    ZYPEZDItemValueSetter.setValue(line.coaChCd_SH, dsAcctCustMap.get("COA_CH_CD"));
                }
            }
            if (ZYPCommonFunc.hasValue(line.dsAcctNum_BL)) {
                Map<String, String> dsAcctCustMap = (Map<String, String>) getDsAcctCust(param.glblCmpyCd.getValue(), line.dsAcctNum_BL.getValue(), param.prcBaseDt.getValue());
                if (dsAcctCustMap == null) {
                    msgMap.addXxMsgIdWithPrm(NWZM1416E, new String[] {"Bill to Account Number", line.dsAcctNum_BL.getValue(), "SELL_TO_CUST" });
                    msgMap.flush();
                    return true;
                } else {
                    ZYPEZDItemValueSetter.setValue(line.dsAcctNm_BL, dsAcctCustMap.get("DS_ACCT_NM"));
                    ZYPEZDItemValueSetter.setValue(line.dsCustSicCd_BL, dsAcctCustMap.get("DS_CUST_SIC_CD"));
                    ZYPEZDItemValueSetter.setValue(line.locNum_BL, dsAcctCustMap.get("LOC_NUM"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctClsCd_BL, dsAcctCustMap.get("DS_ACCT_CLS_CD"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctTpCd_BL, dsAcctCustMap.get("DS_ACCT_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctDlrCd_BL, dsAcctCustMap.get("DS_ACCT_DLR_CD"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctGrpCd_BL, dsAcctCustMap.get("DS_ACCT_GRP_CD"));
                    ZYPEZDItemValueSetter.setValue(line.coaChCd_BL, dsAcctCustMap.get("COA_CH_CD"));
                }
            }
            // Price Category
            if (ZYPCommonFunc.hasValue(line.prcCatgCd)) {
                PRC_CATGTMsg prcCatgTMsg = getPrcCatg(glblCmpyCd, line.prcCatgCd.getValue());
                if (prcCatgTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Price Category", line.prcCatgCd.getValue(), "PRC_CATG" });
                    lineMap.flush();
                    return true;
                }
            }
            // CSA Price Contract Number
            if (ZYPCommonFunc.hasValue(line.prcContrNum)) {
                PRC_CONTRTMsg prcContrTMsg = getPrcContr(glblCmpyCd, line.prcContrNum.getValue());
                if (prcContrTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"CSA Price Contract Number", line.prcContrNum.getValue(), "PRC_CONTR" });
                    lineMap.flush();
                    return true;
                }
            }
            // Branch
            if (ZYPCommonFunc.hasValue(line.coaBrCd)) {
                COA_BRTMsg coaBrTMsg = getCoaBr(glblCmpyCd, line.coaBrCd.getValue());
                if (coaBrTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Branch", line.coaBrCd.getValue(), "COA_BR" });
                    lineMap.flush();
                    return true;
                }
            }
            // Merchandise Code
            if (!ZYPCommonFunc.hasValue(line.mdseCd)) {
                if (!PROCESS_MODE_MANUAL.equals(param.xxModeCd_PM.getValue())) { // QC#22965 2018/04/11 Add
                    msgMap.addXxMsgIdWithPrm(NWZM0293E, new String[] {PROGRAM_ID, "Merchandise Code" });
                    msgMap.flush();
                    return true;
                }
            } else {
                Map<String, String> map = (Map<String, String>) getMdse(glblCmpyCd, line.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(line.ordTakeMdseCd, map.get("ORD_TAKE_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(line.mdseNm, map.get("MDSE_NM"));
                ZYPEZDItemValueSetter.setValue(line.zerothProdCtrlCd, map.get("ZEROTH_PROD_CTRL_CD"));
                ZYPEZDItemValueSetter.setValue(line.firstProdCtrlCd,  map.get("FIRST_PROD_CTRL_CD"));
                ZYPEZDItemValueSetter.setValue(line.scdProdCtrlCd, map.get("SCD_PROD_CTRL_CD"));
                ZYPEZDItemValueSetter.setValue(line.thirdProdCtrlCd, map.get("THIRD_PROD_CTRL_CD"));
                ZYPEZDItemValueSetter.setValue(line.frthProdCtrlCd, map.get("FRTH_PROD_CTRL_CD"));
                ZYPEZDItemValueSetter.setValue(line.fifthProdCtrlCd, map.get("FIFTH_PROD_CTRL_CD"));
                // QC#22569 2018/04/24 Add Start
                ZYPEZDItemValueSetter.setValue(line.slsMatGrpCd_01, map.get("SLS_MAT_GRP_CD_01"));
                ZYPEZDItemValueSetter.setValue(line.slsMatGrpCd_02, map.get("SLS_MAT_GRP_CD_02"));
                ZYPEZDItemValueSetter.setValue(line.slsMatGrpCd_03, map.get("SLS_MAT_GRP_CD_03"));
                // QC#22569 2018/04/24 Add End
                ZYPEZDItemValueSetter.setValue(line.mdseItemTpCd, map.get("MDSE_ITEM_TP_CD"));
                ZYPEZDItemValueSetter.setValue(line.mdseItemClsTpCd, map.get("MDSE_ITEM_CLS_TP_CD"));
                ZYPEZDItemValueSetter.setValue(line.coaMdseTpCd, map.get("COA_MDSE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(line.coaProdCd, map.get("COA_PROD_CD"));
                ZYPEZDItemValueSetter.setValue(line.mktMdlCd, map.get("MKT_MDL_CD"));
                ZYPEZDItemValueSetter.setValue(line.mktMdlSegCd, map.get("MKT_MDL_SEG_CD"));
                if (!ZYPCommonFunc.hasValue(line.pkgUomCd)) {
                    ZYPEZDItemValueSetter.setValue(line.pkgUomCd, map.get("PKG_UOM_CD"));
                }
            }
            // Line Category (Line Type)
            if (ZYPCommonFunc.hasValue(line.dsOrdLineCatgCd)) {
                DS_ORD_LINE_CATGTMsg dsOrdLineCatgTMsg = getDsOrdLineCatg(glblCmpyCd, line.dsOrdLineCatgCd.getValue());
                if (dsOrdLineCatgTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Line Category (Line Type)", line.dsOrdLineCatgCd.getValue(), "DS_ORD_LINE_CATG" });
                    lineMap.flush();
                    return true;
                } else {
                    dsOrdLineDrctnCd = dsOrdLineCatgTMsg.dsOrdLineDrctnCd.getValue();
                }
            }
            // Return Reason Code
            if (ZYPCommonFunc.hasValue(line.rtrnRsnCd)) {
                RTRN_RSNTMsg rtrnRsnTMsg = getRtrnRsn(glblCmpyCd, line.rtrnRsnCd.getValue());
                if (rtrnRsnTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Return Reason Code", line.rtrnRsnCd.getValue(), "RTRN_RSN" });
                    lineMap.flush();
                    return true;
                }
            }
            // Model
            if (ZYPCommonFunc.hasValue(line.mdlId)) {
                MDL_NMTMsg mdlNmTMsg = getMdlNm(glblCmpyCd, line.mdlId.getValue());
                if (mdlNmTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Model", line.mdlId.getValue().toString(), "MDL_NM" });
                    lineMap.flush();
                    return true;
                } else {
                    ZYPEZDItemValueSetter.setValue(line.mdlNm, mdlNmTMsg.t_MdlNm.getValue());
                }
            }

            // Service Call Type
            if (ZYPCommonFunc.hasValue(line.svcCallTpCd)) {
                SVC_CALL_TPTMsg svcCallTpTMsg = getSvcCallTp(glblCmpyCd, line.svcCallTpCd.getValue());
                if (svcCallTpTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Service Call Type", line.svcCallTpCd.getValue(), "SVC_CALL_TP" });
                    lineMap.flush();
                    return true;
                }
            }

            // Ship-To State Code
            if (ZYPCommonFunc.hasValue(line.stCd)) {
                STTMsg stTMsg = getSt(glblCmpyCd, line.stCd.getValue());
                if (stTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Ship-To State Code", line.stCd.getValue(), "ST" });
                    lineMap.flush();
                    return true;
                }
            }

            // Ship-To Country Code
            if (ZYPCommonFunc.hasValue(line.ctryCd)) {
                CTRYTMsg ctryTMsg = getCtry(glblCmpyCd, line.ctryCd.getValue());
                if (ctryTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Ship-To Country Code", line.ctryCd.getValue(), "CTRY" });
                    lineMap.flush();
                    return true;
                }
            }

            // Shipping Service Level Code
            if (ZYPCommonFunc.hasValue(line.shpgSvcLvlCd)) {
                SHPG_SVC_LVLTMsg shpgSvcLvlTMsg = getShpgSvcLvl(glblCmpyCd, line.shpgSvcLvlCd.getValue());
                if (shpgSvcLvlTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Line Category (Line Type)", line.shpgSvcLvlCd.getValue(), STRING_EMPTY });
                    lineMap.flush();
                    return true;
                }
            }

            // Freight Charge To Code
            if (ZYPCommonFunc.hasValue(line.frtChrgToCd)) {
                FRT_CHRG_TOTMsg frtChrgToCdTMsg = getFrtChrgToCd(glblCmpyCd, line.frtChrgToCd.getValue());
                if (frtChrgToCdTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Freight Charge To Code", line.frtChrgToCd.getValue(), "FRT_CHRG_TO" });
                    lineMap.flush();
                    return true;
                }
            }

            // Freight Charge Method Code
            if (ZYPCommonFunc.hasValue(line.frtChrgMethCd)) {
                FRT_CHRG_METHTMsg frtChrgMethTMsg = getFrtChrgMeth(glblCmpyCd, line.frtChrgMethCd.getValue());
                if (frtChrgMethTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Freight Charge Method Code", line.frtChrgMethCd.getValue(), "FRT_CHRG_METH" });
                    lineMap.flush();
                    return true;
                }
            }

            // COA Extension Code (Business Unit)
            if (ZYPCommonFunc.hasValue(line.coaExtnCd)) {
                COA_EXTNTMsg coaExtnTMsg = getCoaExtn(glblCmpyCd, line.coaExtnCd.getValue());
                if (coaExtnTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"COA Extension Code (Business Unit)", line.coaExtnCd.getValue(), "COA_EXTN" });
                    lineMap.flush();
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // QC#22241 2017/11/02 Add Start
    //private List<NWXC014001prcRuleWrkBean> getPrcRule(NWXC014001PMsg pMsg, NWXC014002PMsg line) {
    private List<NWXC014001prcRuleWrkBean> getPrcRule(NWXC014001PMsg pMsg, NWXC014002PMsg line, String prcRuleModTpCd) {
    // QC#22241 2017/11/02 Add End

        List<String> materialPrcGrp = getMaterialPrcGrp(pMsg, line);
        List<String> transactionPrcGrp = getTransactionPrcGrp(pMsg, line);
        List<String> customerPrcGrp = getCustomerPrcGrp(pMsg, line);
        // QC#29410 2018/12/22 Del Start
        // List<String> customerPrcGrpSH = getCustomerPrcGrpSH(pMsg, line);
        // List<String> customerPrcGrpBL = getCustomerPrcGrpBL(pMsg, line);
        // QC#29410 2018/12/22 Del End
        List<String> frtZone = getFrtZone(pMsg, line);

        // Set Price Rule SSM Param For Line Data
        // QC#22241 2017/11/02 Add Start
        if (prcRuleModTpCd == null) {
            paramForPrcRule.remove("prcRuleModTpCd");
        } else {
            paramForPrcRule.put("prcRuleModTpCd", prcRuleModTpCd);
        }
        paramForPrcRule.put("xxTotAmt", pMsg.xxTotAmt.getValue());
        // QC#22241 2017/11/02 Add End
        paramForPrcRule.put("prcBaseDt", line.prcBaseDt.getValue());
        paramForPrcRule.put("coaChCdSH", line.coaChCd_SH.getValue());
        // paramForPrcRule.put("customerPrcGrpSH", customerPrcGrpSH);  // QC#29410 2018/12/22 Del
        paramForPrcRule.put("dsAcctNumSH", line.dsAcctNum_SH.getValue());
        paramForPrcRule.put("csmpNum", line.csmpNum.getValue());
        paramForPrcRule.put("materialPrcGrp", materialPrcGrp);
        paramForPrcRule.put("zerothProdCtrlCd", line.zerothProdCtrlCd.getValue());
        paramForPrcRule.put("firstProdCtrlCd", line.firstProdCtrlCd.getValue());
        paramForPrcRule.put("coaMdseTpCd", line.coaMdseTpCd.getValue());
        paramForPrcRule.put("coaProdCd", line.coaProdCd.getValue());
        if (ZYPCommonFunc.hasValue(line.ordTakeMdseCd)) {
            paramForPrcRule.put("ordTakeMdseCd", line.ordTakeMdseCd.getValue());
            paramForPrcRule.remove("mdseCd");
        } else {
            paramForPrcRule.put("mdseCd", line.mdseCd.getValue());
            paramForPrcRule.remove("ordTakeMdseCd");
        }
        paramForPrcRule.put("transactionPrcGrp", transactionPrcGrp);
        paramForPrcRule.put("dsAcctNumBL", line.dsAcctNum_BL.getValue());
        paramForPrcRule.put("coaChCdBL", line.coaChCd_BL.getValue());
        paramForPrcRule.put("dsAcctClsCdBL", line.dsAcctClsCd_BL.getValue());
        paramForPrcRule.put("coaBrCd", line.coaBrCd.getValue());
        paramForPrcRule.put("svcCallTpCd", line.svcCallTpCd.getValue());
        paramForPrcRule.put("svcCallIncdtDt", line.svcCallIncdtDt.getValue());
        // paramForPrcRule.put("customerPrcGrpBL", customerPrcGrpBL); // QC#29410 2018/12/22 Del
        paramForPrcRule.put("entCpoDtlDealSlsAmt", line.entCpoDtlDealSlsAmt.getValue());
        paramForPrcRule.put("dsOrdLineCatgCd", line.dsOrdLineCatgCd.getValue());
        // QC#29316 2018/11/29 Add Start
        // paramForPrcRule.put("lineQty", line.ordQty.getValue());
        paramForPrcRule.put("lineQty", abs(line.ordQty.getValue()));
        // QC#29316 2018/11/29 Add End
        paramForPrcRule.put("mktMdlCd", line.mktMdlCd.getValue());  // QC#19319 2017/06/19
        paramForPrcRule.put("mktMdlSegCd", line.mktMdlSegCd.getValue());
        paramForPrcRule.put("prcCatgCd", line.prcCatgCd.getValue());
        paramForPrcRule.put("prcBaseDt", line.prcBaseDt.getValue());
        paramForPrcRule.put("scdProdCtrlCd", line.scdProdCtrlCd.getValue());
        paramForPrcRule.put("thirdProdCtrlCd", line.thirdProdCtrlCd.getValue());
        paramForPrcRule.put("frthProdCtrlCd", line.frthProdCtrlCd.getValue());
        paramForPrcRule.put("rtrnRsnCd", line.rtrnRsnCd.getValue());
        paramForPrcRule.put("shpgSvcLvlCd", line.shpgSvcLvlCd.getValue());
        paramForPrcRule.put("mdlId", line.mdlId.getValue());
        paramForPrcRule.put("prcSvcZoneCd", line.prcSvcZoneCd.getValue());
        paramForPrcRule.put("dsAcctClsCdSH", line.dsAcctClsCd_SH.getValue());
        paramForPrcRule.put("coaExtnCd", line.coaExtnCd.getValue());
        paramForPrcRule.put("frtTerm", line.frtCondCd.getValue());
        paramForPrcRule.put("frtZone", frtZone);
        paramForPrcRule.put("customerPrcGrp", customerPrcGrp);
        // QC#20249 2017/08/09 Add Start
        String prcGrpPk = materilGroupQtyBreakPool.get(line.trxLineNum.getValue());
        paramForPrcRule.put("materialPrcGrpqtyBreak", prcGrpPk);
        // QC#29316 2018/11/29 Add Start
        // paramForPrcRule.put("lineQtyQtyBleak", materialGroupQty.get(prcGrpPk));
        paramForPrcRule.put("lineQtyQtyBleak", abs(materialGroupQty.get(prcGrpPk)));
        // QC#29316 2018/11/29 Add End
        // QC#20249 2017/08/09 Add End
        
        // QC#22569 2018/04/24 Add Start
        paramForPrcRule.put("slsMatGrpCd_01" , line.slsMatGrpCd_01.getValue());
        paramForPrcRule.put("slsMatGrpCd_02" , line.slsMatGrpCd_02.getValue());
        paramForPrcRule.put("slsMatGrpCd_03" , line.slsMatGrpCd_03.getValue());
        // QC#22569 2018/04/24 Add End

        paramForPrcRule.put("modFlgFlg", ZYPConstant.FLG_ON_Y);
        paramForPrcRule.put("wildCard", WILDCARD);
        List<Map<String, Object>> ssmResList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("selectPrcRule", paramForPrcRule);
        if (ssmResList == null) {
            return null;
        }
        List<NWXC014001prcRuleWrkBean> list = new ArrayList<NWXC014001prcRuleWrkBean>();
        for (Map<String, Object> map : ssmResList) {
            NWXC014001prcRuleWrkBean bean = new NWXC014001prcRuleWrkBean();
            bean.setTrxLineNum(line.trxLineNum.getValue());
            bean.setTrxLineSubNum(line.trxLineSubNum.getValue());
            bean.setConfigCatgCd(line.configCatgCd.getValue());
            bean.setPrcRuleCatgCd((String) map.get("PRC_RULE_CATG_CD"));
            bean.setPrcRuleHdrPk((BigDecimal) map.get("PRC_RULE_HDR_PK"));
            bean.setPrcRuleDtlPk((BigDecimal) map.get("PRC_RULE_DTL_PK"));
            bean.setPrcRuleCondGrpCd((String) map.get("PRC_RULE_COND_GRP_CD"));
            bean.setPrcRuleDtlChrgNm((String) map.get("PRC_RULE_DTL_CHRG_NM"));
            bean.setPrcRuleModTpCd((String) map.get("PRC_RULE_MOD_TP_CD"));
            bean.setPrcRuleAdjTpCd((String) map.get("PRC_RULE_ADJ_TP_CD"));
            bean.setPrcRuleAdjLvlCd((String) map.get("PRC_RULE_ADJ_LVL_CD"));
            bean.setPrcFmlaPk((BigDecimal) map.get("PRC_FMLA_PK"));
            bean.setPrcRuleDtlRate((BigDecimal) map.get("PRC_RULE_DTL_RATE"));
            bean.setPrcRuleDtlTxt((String) map.get("PRC_RULE_DTL_TXT"));
            bean.setPrcRuleAttrbCd((String) map.get("PRC_RULE_ATTRB_CD"));
            bean.setSpecCondPrcPk((BigDecimal) map.get("SPEC_COND_PRC_PK"));
            bean.setPrcFmlaTpCd((String) map.get("PRC_FMLA_TP_CD"));
            bean.setPrcFmlaNum((BigDecimal) map.get("PRC_FMLA_NUM"));
            bean.setPrcFuncTpCd((String) map.get("PRC_FUNC_TP_CD"));
            bean.setDefRulePrcdNum((BigDecimal) map.get("DEF_RULE_PRCD_NUM"));

            //bean.setPrcRulePrcdGrpNum(BigDecimal.ZERO); QC#9700 2018/09/03 Del
            bean.setPrcPrcdActTpCd(PRC_PRCD_ACT_TP.ALL);
            bean.setPrcRulePrcdSqNum(BigDecimal.ZERO); // QC#28701 2018/10/09 Add
            list.add(bean);
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    private List<String> getCustomerPrcGrpSH(NWXC014001PMsg pMsg, NWXC014002PMsg line) {
        // For Performance QC#11618 Mod Start
        String key = createCustomerPrcGrpSHKey(line);
        List<String> cacheList = customerPrcGrpSHCache.get(key);
        if (cacheList != null) {
            if (cacheList.size() > 0) {
                return cacheList;
            }
            return null;
        }
        // For Performance QC#11618 Mod End

        List<String> rtnList = new ArrayList<String>();

        // Set SSM Param For Line Data
        paramForCustomerPrcSH.put("prcBaseDt", line.prcBaseDt.getValue());
        paramForCustomerPrcSH.put("dsAcctNum", line.dsAcctNum_SH.getValue());
        paramForCustomerPrcSH.put("dsAcctNm", line.dsAcctNm_SH.getValue());
        paramForCustomerPrcSH.put("dsCustSicCd", line.dsCustSicCd_SH.getValue());
        paramForCustomerPrcSH.put("locNum", line.locNum_SH.getValue());
        paramForCustomerPrcSH.put("dsAcctClsCd", line.dsAcctClsCd_SH.getValue());
        paramForCustomerPrcSH.put("dsAcctTpCd", line.dsAcctTpCd_SH.getValue());
        paramForCustomerPrcSH.put("dsAcctDlrCd", line.dsAcctDlrCd_SH.getValue());
        paramForCustomerPrcSH.put("csmpNum", line.csmpNum.getValue());
        paramForCustomerPrcSH.put("dlrRefNum", line.dlrRefNum.getValue());
        paramForCustomerPrcSH.put("prcContrNum", line.prcContrNum.getValue());
        // S21_NA#18789 add start
        paramForCustomerPrcSH.put("coaChCd", line.coaChCd_SH.getValue());
        // S21_NA#18789 add end

        List<BigDecimal> ssmResList = (List<BigDecimal>) ssmBatchClient.queryObjectList("selectPrcGrp", paramForCustomerPrcSH);
        if (ssmResList == null || ssmResList.isEmpty()) {
            customerPrcGrpSHCache.put(key, rtnList); // For Performance QC#11618 Add
            return null;
        }
        for (BigDecimal prcGrpPk : ssmResList) {
            rtnList.add(prcGrpPk.toString());
        }
        customerPrcGrpSHCache.put(key, rtnList);
        return rtnList;
    }

    private String createCustomerPrcGrpSHKey(NWXC014002PMsg line) {
        StringBuilder key = new StringBuilder(line.prcBaseDt.getValue());
        key.append(",").append(line.dsAcctNum_SH.getValue());
        key.append(",").append(line.dsAcctNm_SH.getValue());
        key.append(",").append(line.dsCustSicCd_SH.getValue());
        key.append(",").append(line.locNum_SH.getValue());
        key.append(",").append(line.dsAcctClsCd_SH.getValue());
        key.append(",").append(line.dsAcctTpCd_SH.getValue());
        key.append(",").append(line.csmpNum.getValue());
        key.append(",").append(line.dlrRefNum.getValue());
        key.append(",").append(line.prcContrNum.getValue());
        return key.toString();
    }

    @SuppressWarnings("unchecked")
    private List<String> getCustomerPrcGrpBL(NWXC014001PMsg pMsg, NWXC014002PMsg line) {
        String key = createCustomerPrcGrpBLKey(line);
        List<String> cacheList = customerPrcGrpBLCache.get(key);
        if (cacheList != null) {
            if (cacheList.size() > 0) {
                return cacheList;
            }
            return null;
        }

        List<String> rtnList = new ArrayList<String>();

        // Set SSM Param For Line Data
        paramForCustomerPrcBL.put("prcBaseDt", line.prcBaseDt.getValue());
        paramForCustomerPrcBL.put("dsAcctNum", line.dsAcctNum_BL.getValue());
        paramForCustomerPrcBL.put("dsAcctNm", line.dsAcctNm_BL.getValue());
        paramForCustomerPrcBL.put("dsCustSicCd", line.dsCustSicCd_BL.getValue());
        paramForCustomerPrcBL.put("locNum", line.locNum_BL.getValue());
        paramForCustomerPrcBL.put("dsAcctClsCd", line.dsAcctClsCd_BL.getValue());
        paramForCustomerPrcBL.put("dsAcctTpCd", line.dsAcctTpCd_BL.getValue());
        paramForCustomerPrcBL.put("dsAcctDlrCd", line.dsAcctDlrCd_BL.getValue());
        paramForCustomerPrcBL.put("csmpNum", line.csmpNum.getValue());
        paramForCustomerPrcBL.put("dlrRefNum", line.dlrRefNum.getValue());
        paramForCustomerPrcBL.put("prcContrNum", line.prcContrNum.getValue());
        // S21_NA#18789 add start
        paramForCustomerPrcSH.put("coaChCd", line.coaChCd_BL.getValue());
        // S21_NA#18789 add end

        List<BigDecimal> ssmResList = (List<BigDecimal>) ssmBatchClient.queryObjectList("selectPrcGrp", paramForCustomerPrcBL);
        if (ssmResList == null || ssmResList.isEmpty()) {
            customerPrcGrpBLCache.put(key, rtnList); // For Performance QC#11618 Add
            return null;
        }
        for (BigDecimal prcGrpPk : ssmResList) {
            rtnList.add(prcGrpPk.toString());
        }
        customerPrcGrpBLCache.put(key, rtnList);
        return rtnList;
    }

    private String createCustomerPrcGrpBLKey(NWXC014002PMsg line) {
        StringBuilder key = new StringBuilder(line.prcBaseDt.getValue());
        key.append(",").append(line.dsAcctNum_BL.getValue());
        key.append(",").append(line.dsAcctNm_BL.getValue());
        key.append(",").append(line.dsCustSicCd_BL.getValue());
        key.append(",").append(line.locNum_BL.getValue());
        key.append(",").append(line.dsAcctClsCd_BL.getValue());
        key.append(",").append(line.dsAcctTpCd_BL.getValue());
        key.append(",").append(line.dsAcctDlrCd_BL.getValue());
        key.append(",").append(line.csmpNum.getValue());
        key.append(",").append(line.dlrRefNum.getValue());
        key.append(",").append(line.prcContrNum.getValue());
        return key.toString();
    }

    @SuppressWarnings("unchecked")
    private List<String> getMaterialPrcGrp(NWXC014001PMsg pMsg, NWXC014002PMsg line) {
        String key = createMaterialPrcGrpKey(line);
        List<String> cacheList = materialPrcGrpCache.get(key);
        if (cacheList != null) {
            if (cacheList.size() > 0) {
                return cacheList;
            }
            return null;
        }

        List<String> rtnList = new ArrayList<String>();

        // Set SSM Param For Line Data
        paramForMaterial.put("prcBaseDt", line.prcBaseDt.getValue());
        if (ZYPCommonFunc.hasValue(line.ordTakeMdseCd)) {
            paramForMaterial.put("mdseCd", line.ordTakeMdseCd.getValue());
        } else {
            paramForMaterial.put("mdseCd", line.mdseCd.getValue());
        }
        paramForMaterial.put("mdseItemTpCd", line.mdseItemTpCd.getValue());
        paramForMaterial.put("mdseItemClsTpCd", line.mdseItemClsTpCd.getValue());
        paramForMaterial.put("coaMdseTpCd", line.coaMdseTpCd.getValue());
        paramForMaterial.put("coaProdCd", line.coaProdCd.getValue());
        paramForMaterial.put("mktMdlCd", line.mktMdlCd.getValue());
        paramForMaterial.put("zerothProdCtrlCd", line.zerothProdCtrlCd.getValue());
        paramForMaterial.put("firstProdCtrlCd", line.firstProdCtrlCd.getValue());
        paramForMaterial.put("scdProdCtrlCd", line.scdProdCtrlCd.getValue());
        paramForMaterial.put("thirdProdCtrlCd", line.thirdProdCtrlCd.getValue());
        paramForMaterial.put("frthProdCtrlCd", line.frthProdCtrlCd.getValue());

        List<BigDecimal> ssmResList = (List<BigDecimal>) ssmBatchClient.queryObjectList("selectPrcGrp", paramForMaterial);
        if (ssmResList == null || ssmResList.isEmpty()) {
            materialPrcGrpCache.put(key, rtnList); // For Performance QC#11618 Add
            return null;
        }
        for (BigDecimal prcGrpPk : ssmResList) {
            rtnList.add(prcGrpPk.toString());
        }
        materialPrcGrpCache.put(key, rtnList); // For Performance QC#11618 Add
        return rtnList;
    }
    // QC#20249 2017/08/09 Add Start
    @SuppressWarnings("unchecked")
    private List<String> getMaterialPrcGrpQtyBreak(NWXC014001PMsg pMsg, NWXC014002PMsg line) {
        String key = createMaterialPrcGrpKey(line);
        List<String> cacheList = materialPrcGrpQtyBreakCache.get(key);
        if (cacheList != null) {
            if (cacheList.size() > 0) {
                return cacheList;
            }
            return null;
        }

        List<String> rtnList = new ArrayList<String>();

        // Set SSM Param For Line Data
        paramForMaterialQtyBreak.put("prcBaseDt", line.prcBaseDt.getValue());
        if (ZYPCommonFunc.hasValue(line.ordTakeMdseCd)) {
            paramForMaterialQtyBreak.put("mdseCd", line.ordTakeMdseCd.getValue());
        } else {
            paramForMaterialQtyBreak.put("mdseCd", line.mdseCd.getValue());
        }
        paramForMaterialQtyBreak.put("mdseItemTpCd", line.mdseItemTpCd.getValue());
        paramForMaterialQtyBreak.put("mdseItemClsTpCd", line.mdseItemClsTpCd.getValue());
        paramForMaterialQtyBreak.put("coaMdseTpCd", line.coaMdseTpCd.getValue());
        paramForMaterialQtyBreak.put("coaProdCd", line.coaProdCd.getValue());
        paramForMaterialQtyBreak.put("mktMdlCd", line.mktMdlCd.getValue());
        paramForMaterialQtyBreak.put("zerothProdCtrlCd", line.zerothProdCtrlCd.getValue());
        paramForMaterialQtyBreak.put("firstProdCtrlCd", line.firstProdCtrlCd.getValue());
        paramForMaterialQtyBreak.put("scdProdCtrlCd", line.scdProdCtrlCd.getValue());
        paramForMaterialQtyBreak.put("thirdProdCtrlCd", line.thirdProdCtrlCd.getValue());
        paramForMaterialQtyBreak.put("frthProdCtrlCd", line.frthProdCtrlCd.getValue());
        // Add Start 2018/07/05 QC#26197
        paramForMaterialQtyBreak.put("prcRuleAttrb57", PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK);
        // Add End 2018/07/05 QC#26197

        List<BigDecimal> ssmResList = (List<BigDecimal>) ssmBatchClient.queryObjectList("selectPrcGrp", paramForMaterialQtyBreak);
        if (ssmResList == null || ssmResList.isEmpty()) {
            materialPrcGrpQtyBreakCache.put(key, rtnList);
            return null;
        }
        for (BigDecimal prcGrpPk : ssmResList) {
            rtnList.add(prcGrpPk.toString());
        }
        materialPrcGrpQtyBreakCache.put(key, rtnList);
        return rtnList;
    }
    // QC#20249 2017/08/09 Add End

    private String createMaterialPrcGrpKey(NWXC014002PMsg line) {
        StringBuilder key = new StringBuilder(line.prcBaseDt.getValue());
        key.append(",").append(line.ordTakeMdseCd.getValue());
        key.append(",").append(line.mdseCd.getValue());
        key.append(",").append(line.mdseItemTpCd.getValue());
        key.append(",").append(line.mdseItemClsTpCd.getValue());
        key.append(",").append(line.coaMdseTpCd.getValue());
        key.append(",").append(line.coaProdCd.getValue());
        key.append(",").append(line.mktMdlCd.getValue());
        key.append(",").append(line.zerothProdCtrlCd.getValue());
        key.append(",").append(line.firstProdCtrlCd.getValue());
        key.append(",").append(line.scdProdCtrlCd.getValue());
        key.append(",").append(line.thirdProdCtrlCd.getValue());
        key.append(",").append(line.frthProdCtrlCd.getValue());
        return key.toString();
    }

    @SuppressWarnings("unchecked")
    private List<String> getTransactionPrcGrp(NWXC014001PMsg pMsg, NWXC014002PMsg line) {
        // For Performance QC#11618 Mod Start
        String key = createTransactionPrcGrpKey(line);
        List<String> cacheList = transactionPrcGrpCache.get(key);
        if (cacheList != null) {
            if (cacheList.size() > 0) {
                return cacheList;
            }
            return null;
        }
        // For Performance QC#11618 Mod End

        List<String> rtnList = new ArrayList<String>();

        // Set SSM Param For Line Data
        paramForTransaction.put("prcBaseDt", line.prcBaseDt.getValue());
        paramForTransaction.put("dsOrdLineCatgCd", line.dsOrdLineCatgCd.getValue());

        List<BigDecimal> ssmResList = (List<BigDecimal>) ssmBatchClient.queryObjectList("selectPrcGrp", paramForTransaction);
        if (ssmResList == null || ssmResList.isEmpty()) {
            transactionPrcGrpCache.put(key, rtnList); // For Performance QC#11618 Add
            return null;
        }
        for (BigDecimal prcGrpPk : ssmResList) {
            rtnList.add(prcGrpPk.toString());
        }
        transactionPrcGrpCache.put(key, rtnList);
        return rtnList;
    }

    private String createTransactionPrcGrpKey(NWXC014002PMsg line) {
        StringBuilder key = new StringBuilder(line.prcBaseDt.getValue());
        key.append(",").append(line.dsOrdLineCatgCd.getValue());
        return key.toString();
    }

    @SuppressWarnings("unchecked")
    private List<String> getCustomerPrcGrp(NWXC014001PMsg pMsg, NWXC014002PMsg line) {
        String key = createCustomerPrcGrpKey(line);
        List<String> cacheList = customerPrcGrpCache.get(key);
        if (cacheList != null) {
            if (cacheList.size() > 0) {
                return cacheList;
            }
            return null;
        }

        List<String> rtnList = new ArrayList<String>();

        // Set SSM Param For Line Data
        paramForCustomerPrc.put("prcBaseDt", line.prcBaseDt.getValue());
        paramForCustomerPrc.put("csmpNum", line.csmpNum.getValue());
        paramForCustomerPrc.put("dlrRefNum", line.dlrRefNum.getValue());
        paramForCustomerPrc.put("prcContrNum", line.prcContrNum.getValue());

        List<BigDecimal> ssmResList = (List<BigDecimal>) ssmBatchClient.queryObjectList("selectPrcGrp", paramForCustomerPrc);
        if (ssmResList == null || ssmResList.isEmpty()) {
            customerPrcGrpCache.put(key, rtnList);
            return null;
        }
        for (BigDecimal prcGrpPk : ssmResList) {
            rtnList.add(prcGrpPk.toString());
        }
        customerPrcGrpCache.put(key, rtnList);
        return rtnList;
    }

    private String createCustomerPrcGrpKey(NWXC014002PMsg line) {
        StringBuilder key = new StringBuilder(line.prcBaseDt.getValue());
        key.append(",").append(line.csmpNum.getValue());
        key.append(",").append(line.dlrRefNum.getValue());
        key.append(",").append(line.prcContrNum.getValue());
        return key.toString();
    }

    @SuppressWarnings("unchecked")
    private List<String> getFrtZone(NWXC014001PMsg pMsg, NWXC014002PMsg line) {
        String key = createFrtZoneKey(line);
        List<String> cacheList = frtZoneCache.get(key);
        if (cacheList != null) {
            if (cacheList.size() > 0) {
                return cacheList;
            }
            return null;
        }

        List<String> rtnList = new ArrayList<String>();

        // Set SSM Param For Line Data
        paramForFrtZone.put("shipAcctNum", line.dsAcctNum_SH.getValue());
        paramForFrtZone.put("shipToStCd", line.stCd.getValue());
        paramForFrtZone.put("shipToCtryCd", line.ctryCd.getValue());
        paramForFrtZone.put("shipToPostCd", line.postCd.getValue());
        paramForFrtZone.put("prcBaseDt", line.prcBaseDt.getValue());
        paramForFrtZone.put("dsAcctNum", line.dsAcctNum_SH.getValue());
        paramForFrtZone.put("dsAcctNm", line.dsAcctNm_SH.getValue());
        paramForFrtZone.put("dsCustSicCd", line.dsCustSicCd_SH.getValue());
        paramForFrtZone.put("locNum", line.locNum_SH.getValue());
        paramForFrtZone.put("dsAcctClsCd", line.dsAcctClsCd_SH.getValue());
        paramForFrtZone.put("dsAcctTpCd", line.dsAcctTpCd_SH.getValue());
        paramForFrtZone.put("dsAcctDlrCd", line.dsAcctDlrCd_SH.getValue());
        paramForFrtZone.put("csmpNum", line.csmpNum.getValue());
        paramForFrtZone.put("dlrRefNum", line.dlrRefNum.getValue());
        paramForFrtZone.put("prcContrNum", line.prcContrNum.getValue());
        // S21_NA#18789 add start
        paramForFrtZone.put("coaChCd", line.coaChCd_SH.getValue());
        // S21_NA#18789 add end

        List<BigDecimal> ssmResList = (List<BigDecimal>) ssmBatchClient.queryObjectList("selectFrtZone", paramForFrtZone);
        if (ssmResList == null || ssmResList.isEmpty()) {
            frtZoneCache.put(key, rtnList); // For Performance QC#11618 Add
            return null;
        }
        for (BigDecimal frtZoneNum : ssmResList) {
            rtnList.add(frtZoneNum.toString());
        }
        frtZoneCache.put(key, rtnList);
        return rtnList;
    }

    private String createFrtZoneKey(NWXC014002PMsg line) {
        StringBuilder key = new StringBuilder(line.dsAcctNum_SH.getValue());
        key.append(",").append(line.stCd.getValue());
        key.append(",").append(line.ctryCd.getValue());
        key.append(",").append(line.postCd.getValue());
        key.append(",").append(line.prcBaseDt.getValue());
        key.append(",").append(line.dsAcctNum_SH.getValue());
        key.append(",").append(line.dsAcctNm_SH.getValue());
        key.append(",").append(line.dsCustSicCd_SH.getValue());
        key.append(",").append(line.locNum_SH.getValue());
        key.append(",").append(line.dsAcctClsCd_SH.getValue());
        key.append(",").append(line.dsAcctTpCd_SH.getValue());
        key.append(",").append(line.dsAcctDlrCd_SH.getValue());
        key.append(",").append(line.csmpNum.getValue());
        key.append(",").append(line.dlrRefNum.getValue());
        key.append(",").append(line.prcContrNum.getValue());
        return key.toString();
    }



    private List<NWXC014001prcRuleWrkBean> getPrcRuleWrkBean(NWXC014001PMsg pMsg, List<NWXC014001prcRuleWrkBean> list, String prcRuleModTpCd) {
        List<NWXC014001prcRuleWrkBean> rtnList = new ArrayList<NWXC014001prcRuleWrkBean>();
        if (list == null || list.isEmpty()) {
            return rtnList;
        }

        for (NWXC014001prcRuleWrkBean bean : list) {
            if (prcRuleModTpCd.equals(bean.getPrcRuleModTpCd())) {
                rtnList.add(bean);
            }
        }
        return rtnList;
    }

    private List<NWXC014001prcRuleWrkBean> editPrcRuleWrkBean(NWXC014001PMsg pMsg, List<NWXC014001prcRuleWrkBean> list, List<NWXC014001prcRuleWrkBean> qualifiedList) {
        List<List<NWXC014001prcRuleWrkBean>> listH = new ArrayList<List<NWXC014001prcRuleWrkBean>>();
        List<NWXC014001prcRuleWrkBean> listB = new ArrayList<NWXC014001prcRuleWrkBean>();
        List<NWXC014001prcRuleWrkBean> rtnList = new ArrayList<NWXC014001prcRuleWrkBean>();

        if (list == null || list.size() == 0) {
            return rtnList;
        }

        String wCatgCd = list.get(0).getPrcRuleCatgCd();
        for (NWXC014001prcRuleWrkBean bean : list) {
            if (!wCatgCd.equals(bean.getPrcRuleCatgCd())) {
                listH.add(listB);
                listB = new ArrayList<NWXC014001prcRuleWrkBean>();
                wCatgCd = bean.getPrcRuleCatgCd();
            }
            listB.add(bean);
        }
        if (listB.size() > 0) {
            listH.add(listB);
        }

        for (List<NWXC014001prcRuleWrkBean> catglist : listH) {
            rtnList.addAll(getPrcRuleWrkBean(pMsg, catglist, qualifiedList));
        }

        return rtnList;
    }

    private List<NWXC014001prcRuleWrkBean> getPrcRuleWrkBean(NWXC014001PMsg pMsg,  List<NWXC014001prcRuleWrkBean> list, List<NWXC014001prcRuleWrkBean> qualifiedList) {
        S21ApiMessageMap pMsgMap = new S21ApiMessageMap(pMsg);
        List<String> prcRuleHdrPkList = new ArrayList<String>();
        BigDecimal defRulePrcdNum = null;
        List<NWXC014001prcRuleWrkBean> targetList = new ArrayList<NWXC014001prcRuleWrkBean>();
        List<NWXC014001prcRuleWrkBean> wQualifiedList = new ArrayList<NWXC014001prcRuleWrkBean>();

        if (PROCESS_MODE_MANUAL.equals(pMsg.xxModeCd_PM.getValue())) {
            return list;
        }

        for (NWXC014001prcRuleWrkBean bean : list) {
            //if (!prcRuleHdrPkList.contains(bean.getPrcRuleHdrPk().toString())) {
            //    prcRuleHdrPkList.add(bean.getPrcRuleHdrPk().toString());
            //}
            //default precedence
            if (defRulePrcdNum == null) {
                defRulePrcdNum = bean.getDefRulePrcdNum();
            } else if (defRulePrcdNum.compareTo(bean.getDefRulePrcdNum()) > 0) {
                defRulePrcdNum = bean.getDefRulePrcdNum();
            }
        }
        // Get High Priority Precedence
        for (NWXC014001prcRuleWrkBean bean : list) {
            if (defRulePrcdNum.compareTo(bean.getDefRulePrcdNum()) == 0) {
                if (!prcRuleHdrPkList.contains(bean.getPrcRuleHdrPk().toString())) {
                    prcRuleHdrPkList.add(bean.getPrcRuleHdrPk().toString());
                }
            // QC#9700  2018/09/03 Add Start
            } else {
                wQualifiedList.add(bean);
            // QC#9700  2018/09/03 Add End
            }
        }
        // if (prcRuleHdrPkList.size() == 1) {
        //     return list;
        // }
        targetList = getRuleList(list, defRulePrcdNum);
        if (checkDefPrcd(list, defRulePrcdNum)) {
            // QC#9700  2018/09/03 Add Start
            qualifiedList.addAll(wQualifiedList);
            // QC#9700  2018/09/03 Add End
            return targetList;
        }

        BigDecimal prcRulePrcdPk = null;
        int idx = 0;
        List<Map<String, Object>> prcdList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> data = (List<Map<String, Object>>) getPrcRulePrcdDtlAry(pMsg.glblCmpyCd.getValue(), prcRuleHdrPkList);
        if(data != null){
            prcRulePrcdPk = (BigDecimal) data.get(0).get("PRC_RULE_PRCD_PK");
            for (Map<String, Object> map : data) {
                if (prcRulePrcdPk.compareTo((BigDecimal) data.get(idx).get("PRC_RULE_PRCD_PK")) != 0) {
                    if (comparePrcd(prcRuleHdrPkList, prcdList)) {
                        break;
                    } else {
                        prcdList.clear();
                    }
                }
                prcdList.add(map);
                prcRulePrcdPk = (BigDecimal) data.get(idx).get("PRC_RULE_PRCD_PK");
                idx++;
            }
            if (comparePrcd(prcRuleHdrPkList, prcdList)) {
                // convert to HashMap
                Map<String, Map<String, Object>> prcdMap = new HashMap<String, Map<String, Object>>();
                for (Map<String, Object> map : prcdList) {
                    prcdMap.put(map.get("PRC_RULE_HDR_PK").toString(), map);
                }
                // edit Precedence Value
                Map<String, Object> map = null;
                for (NWXC014001prcRuleWrkBean bean : targetList) {
                    map = prcdMap.get(bean.getPrcRuleHdrPk().toString());
                    bean.setPrcRulePrcdGrpNum((BigDecimal) map.get("PRC_RULE_PRCD_PK"));
                    bean.setPrcPrcdActTpCd((String) map.get("PRC_PRCD_ACT_TP_CD"));
                    bean.setPrcRulePrcdSqNum((BigDecimal) map.get("PRC_RULE_PRCD_SQ_NUM"));
                }
                return targetList;
            }
        }
        // default precedence
        //if (checkDefPrcd(list, defRulePrcdNum)) {
        //    return getRuleList(list, defRulePrcdNum);
        //} else {
        //    pMsgMap.addXxMsgIdWithPrm(NWZM1498E, null);
        //    pMsgMap.flush();
        //}
        pMsgMap.addXxMsgIdWithPrm(NWZM1498E, null);
        pMsgMap.flush();
        for (NWXC014001prcRuleWrkBean bean : list) {
            StringBuffer sb = new StringBuffer();
            sb.append(" PrcRuleCatgCd = ").append(bean.getPrcRuleCatgCd());
            sb.append(" PrcRuleHdrPk =  ").append(bean.getPrcRuleHdrPk());
            log("NWZM1498E", bean.getTrxLineNum(), sb.toString());
        }
        return list;
    }

    private boolean checkDefPrcd(List<NWXC014001prcRuleWrkBean> list, BigDecimal defRulePrcdNum) {
        List<String> prcRuleHdrPkListForDef = new ArrayList<String>();
        if (defRulePrcdNum == null) {
            return false;
        }
        for (NWXC014001prcRuleWrkBean bean : list) {
            if (defRulePrcdNum.compareTo(bean.getDefRulePrcdNum()) == 0) {
                if (!prcRuleHdrPkListForDef.contains(bean.getPrcRuleHdrPk().toString())) {
                    prcRuleHdrPkListForDef.add(bean.getPrcRuleHdrPk().toString());
                }
            }
        }
        if (prcRuleHdrPkListForDef.size() == 1) {
            return true;
        }
        return false;
    }

    private List<NWXC014001prcRuleWrkBean> getRuleList(List<NWXC014001prcRuleWrkBean> list, BigDecimal defRulePrcdNum) {
        List<NWXC014001prcRuleWrkBean> rtnList = new ArrayList<NWXC014001prcRuleWrkBean>();
        for (NWXC014001prcRuleWrkBean bean : list) {
            if (defRulePrcdNum.compareTo(bean.getDefRulePrcdNum()) == 0) {
                rtnList.add(bean);
            }
        }
        return rtnList;
    }

    private boolean comparePrcd(List<String> prcRuleHdrPkList, List<Map<String, Object>> prcdList) {
        String[] rule = prcRuleHdrPkList.toArray(new String[prcRuleHdrPkList.size()]);
        String[] prcd = new String[prcdList.size()];
        int i = 0;
        for (Map<String, Object> map : prcdList) {
            prcd[i] = map.get("PRC_RULE_HDR_PK").toString();
            i++;
        }
        Arrays.sort(rule);
        Arrays.sort(prcd);
        return Arrays.equals(rule, prcd);
    }

    private void editReturnParam(NWXC014001PMsg pMsg, List<NWXC014001prcRuleWrkBean> sellList, List<NWXC014001prcRuleWrkBean> chargeList) {
        List<NWXC014001prcRuleWrkBean> hdrList = new ArrayList<NWXC014001prcRuleWrkBean>();
        List<NWXC014001prcRuleWrkBean> lineList = new ArrayList<NWXC014001prcRuleWrkBean>();
        List<NWXC014001prcRuleWrkBean> hdrListWrk = new ArrayList<NWXC014001prcRuleWrkBean>();
        List<NWXC014001prcRuleWrkBean> allList = new ArrayList<NWXC014001prcRuleWrkBean>();
        List<NWXC014001prcRuleWrkBean> outList = new ArrayList<NWXC014001prcRuleWrkBean>();
        List<BigDecimal> keys = new ArrayList<BigDecimal>();
        NWXC014001prcRuleWrkBean bean;

        ZYPTableUtil.clear(pMsg.NWXC014004PMsg);
        NWXC014004PMsg prcMsg;

        allList.addAll(sellList);
        allList.addAll(chargeList);


        for (NWXC014001prcRuleWrkBean listdata : allList) {
            if (PRC_RULE_ADJ_LVL.HEADER.equals(listdata.getPrcRuleAdjLvlCd())) {
                hdrListWrk.add(listdata);
            } else {
                lineList.add(listdata);
            }
        }
        //duplicate key check
        for (NWXC014001prcRuleWrkBean listdata : hdrListWrk) {
            if (!keys.contains(listdata.getPrcRuleDtlPk())) {
                hdrList.add(listdata);
                keys.add(listdata.getPrcRuleDtlPk());
            }
        }
        outList.addAll(hdrList);
        outList.addAll(lineList);
        int i = 0;
        for (; i < outList.size(); i++) {
            bean = outList.get(i);
            prcMsg = pMsg.NWXC014004PMsg.no(i);
            ZYPEZDItemValueSetter.setValue(prcMsg.trxLineNum, bean.getTrxLineNum());
            ZYPEZDItemValueSetter.setValue(prcMsg.trxLineSubNum, bean.getTrxLineSubNum());
            ZYPEZDItemValueSetter.setValue(prcMsg.configCatgCd, bean.getConfigCatgCd());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcRuleCatgCd, bean.getPrcRuleCatgCd());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcRulePrcdSqNum, bean.getPrcRulePrcdSqNum());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcRuleHdrPk, bean.getPrcRuleHdrPk());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcRuleCondGrpCd, bean.getPrcRuleCondGrpCd());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcRuleDtlPk, bean.getPrcRuleDtlPk());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcRuleDtlChrgNm, bean.getPrcRuleDtlChrgNm());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcRuleModTpCd, bean.getPrcRuleModTpCd());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcRuleAdjTpCd, bean.getPrcRuleAdjTpCd());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcRuleAdjLvlCd, bean.getPrcRuleAdjLvlCd());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcFmlaPk, bean.getPrcFmlaPk());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcFmlaTpCd, bean.getPrcFmlaTpCd());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcFmlaNum, bean.getPrcFmlaNum());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcFuncTpCd, bean.getPrcFuncTpCd());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcRuleDtlRate, bean.getPrcRuleDtlRate());
            ZYPEZDItemValueSetter.setValue(prcMsg.prcRuleDtlTxt, bean.getPrcRuleDtlTxt());
            ZYPEZDItemValueSetter.setValue(prcMsg.specCondPrcPk, bean.getSpecCondPrcPk());
            ZYPEZDItemValueSetter.setValue(prcMsg.unitPrcAmt, bean.getUnitPrcAmt());
            ZYPEZDItemValueSetter.setValue(prcMsg.xxCalcPrcAmt, bean.getCalcPrcAmt());
        }
        pMsg.NWXC014004PMsg.setValidCount(i);
    }

    private List<NWXC014001prcRuleWrkBean> calculatePrcRule(NWXC014001PMsg param, List<NWXC014001prcRuleWrkBean> ruleList, String prcRuleModTpCd, final ONBATCH_TYPE onBatchType, List<NWXC014001prcRuleWrkBean> qualifiedList) {

        if(ruleList.size() == 0 ){
            return ruleList;
        }
        List<NWXC014001prcRuleWrkBean> rtnList = new ArrayList<NWXC014001prcRuleWrkBean>();
        String wkPrcRuleAdjTpCd = null;
        String wkPrcRuleCatgCd = null; // QC#22933 2017/12/06 Add
        String wkConfigCatgCd = null;
        String wkTrxLineNum = null;
        String wkTrxLineSubNum = null;
        BigDecimal wkPrcRuleHdrPk = BigDecimal.ZERO;
        String wkPrcPrcdActTpCd = null;

        Map<List<String>, List<NWXC014003PMsg>> calcBaseList = new HashMap<List<String>, List<NWXC014003PMsg>>();
        Map<BigDecimal, Map<List<String>, List<NWXC014003PMsg>>> prcdPool = new HashMap<BigDecimal, Map<List<String>, List<NWXC014003PMsg>>>();
        List<NWXC014001prcRuleWrkBean> list = new ArrayList<NWXC014001prcRuleWrkBean>();
        Map<List<String>, List<NWXC014003PMsg>> calcBaseListPrcd = new HashMap<List<String>, List<NWXC014003PMsg>>();

        Collections.sort(ruleList, new NWXC014001BeanComparator());

        wkPrcRuleAdjTpCd = ruleList.get(0).getPrcRuleAdjTpCd();
        wkPrcRuleCatgCd = ruleList.get(0).getPrcRuleCatgCd(); // QC#22933 2017/12/06 Add
        wkConfigCatgCd = ruleList.get(0).getConfigCatgCd();
        wkTrxLineNum = ruleList.get(0).getTrxLineNum();
        wkTrxLineSubNum = ruleList.get(0).getTrxLineSubNum();
        wkPrcRuleHdrPk = ruleList.get(0).getPrcRuleHdrPk();
        wkPrcPrcdActTpCd = ruleList.get(0).getPrcPrcdActTpCd();

        calcBaseList = getCalcBaseList(param);

        for (NWXC014001prcRuleWrkBean bean : ruleList) {
            // QC#22933 2017/12/06 Mod Start
            //if (S21StringUtil.isEquals(bean.getPrcRuleAdjTpCd(), wkPrcRuleAdjTpCd) 
            if (S21StringUtil.isEquals(bean.getPrcRuleCatgCd(), wkPrcRuleCatgCd) 
            // QC#22933 2017/12/06 Mod End
                    && S21StringUtil.isEquals(bean.getConfigCatgCd(), wkConfigCatgCd) 
                    && S21StringUtil.isEquals(bean.getTrxLineNum(), wkTrxLineNum)
                    && S21StringUtil.isEquals(bean.getTrxLineSubNum(), wkTrxLineSubNum)) {
                if (PRC_PRCD_ACT_TP.ALL.equals(wkPrcPrcdActTpCd)) {
                    list.add(bean);
                } else {
                    if (isEquals(bean.getPrcRuleHdrPk(), wkPrcRuleHdrPk)) {
                        list.add(bean);
                    } else {
                        calcBaseListPrcd = calculatePrcAmt(param, ruleList, list, calcBaseList, onBatchType);
                        prcdPool.put(wkPrcRuleHdrPk, calcBaseListPrcd);
                        list.clear();

                        list.add(bean);
                    }
                }
                wkPrcRuleHdrPk = bean.getPrcRuleHdrPk();
            } else {
                if (PRC_PRCD_ACT_TP.ALL.equals(wkPrcPrcdActTpCd)) {
                    calcBaseListPrcd = calculatePrcAmt(param, ruleList, list, calcBaseList, onBatchType);
                    prcdPool.put(wkPrcRuleHdrPk, calcBaseListPrcd);
                    calcBaseList = adoptPrecedence(calcBaseList, prcdPool, wkPrcRuleHdrPk, wkTrxLineNum, wkTrxLineSubNum, wkConfigCatgCd);
                    rtnList.addAll(list);
                } else {
                    calcBaseListPrcd = calculatePrcAmt(param, ruleList, list, calcBaseList, onBatchType);
                    prcdPool.put(wkPrcRuleHdrPk, calcBaseListPrcd);

                    BigDecimal prcRuleHdrPk = jdgPrecedence(prcRuleModTpCd, wkTrxLineNum, wkTrxLineSubNum, wkConfigCatgCd, wkPrcPrcdActTpCd, prcdPool);
                    calcBaseList = adoptPrecedence(calcBaseList, prcdPool, prcRuleHdrPk, wkTrxLineNum, wkTrxLineSubNum, wkConfigCatgCd);
                    for(NWXC014001prcRuleWrkBean beans : list){
                        if(isEquals(prcRuleHdrPk, beans.getPrcRuleHdrPk())){
                            rtnList.add(beans);
                        // QC#9700  2018/09/03 Add Start
                        } else {
                            qualifiedList.add(bean);
                        // QC#9700  2018/09/03 Add End
                        }
                    }
                }
                list.clear();

                wkPrcRuleCatgCd = bean.getPrcRuleCatgCd();
                wkPrcRuleAdjTpCd = bean.getPrcRuleAdjTpCd();
                wkConfigCatgCd = bean.getConfigCatgCd();
                wkTrxLineNum = bean.getTrxLineNum();
                wkTrxLineSubNum = bean.getTrxLineSubNum();

                wkPrcPrcdActTpCd = bean.getPrcPrcdActTpCd();
                wkPrcRuleHdrPk = bean.getPrcRuleHdrPk();
                prcdPool.clear();
                list.add(bean);
            }
        }

        if (list.size() != 0) {
            if (PRC_PRCD_ACT_TP.ALL.equals(wkPrcPrcdActTpCd)) {
                calcBaseListPrcd = calculatePrcAmt(param, ruleList, list, calcBaseList, onBatchType);
                prcdPool.put(wkPrcRuleHdrPk, calcBaseListPrcd);
                calcBaseList = adoptPrecedence(calcBaseList, prcdPool, wkPrcRuleHdrPk, wkTrxLineNum, wkTrxLineSubNum, wkConfigCatgCd);
                rtnList.addAll(list);
                if (isNegotiation(param, negoBean)) {
                    calcBaseList = callNegotiationAPI(param, negoBean, calcBaseList, onBatchType);
                }
                negoBean = null;
            } else {
                calcBaseListPrcd = calculatePrcAmt(param, ruleList, list, calcBaseList, onBatchType);
                prcdPool.put(wkPrcRuleHdrPk, calcBaseListPrcd);

                BigDecimal prcRuleHdrPk = jdgPrecedence(prcRuleModTpCd, wkTrxLineNum, wkTrxLineSubNum, wkConfigCatgCd, wkPrcPrcdActTpCd, prcdPool);
                // calcBaseList = prcdPool.get(prcRuleHdrPk);
                calcBaseList = adoptPrecedence(calcBaseList, prcdPool, prcRuleHdrPk, wkTrxLineNum, wkTrxLineSubNum, wkConfigCatgCd);
                for (NWXC014001prcRuleWrkBean bean : ruleList) {
                    if (isEquals(prcRuleHdrPk, bean.getPrcRuleHdrPk())) {
                        rtnList.add(bean);
                    // QC#9700  2018/09/03 Add Start
                    } else {
                        qualifiedList.add(bean);
                    // QC#9700  2018/09/03 Add End
                    }
                }
            }
        }

        setCalcBase(param, calcBaseList);
        return rtnList;
    }

    private Map<List<String>, List<NWXC014003PMsg>> getCalcBaseList(NWXC014001PMsg param) {
        Map<List<String>, List<NWXC014003PMsg>> calcBaseList = new HashMap<List<String>, List<NWXC014003PMsg>>();
        for (int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++) {
            NWXC014002PMsg line = param.NWXC014002PMsg.no(i);
            List<NWXC014003PMsg> lineList = new ArrayList<NWXC014003PMsg>();
            List<String> keys = new ArrayList<String>();
            keys.add(line.trxLineNum.getValue());
            keys.add(line.trxLineSubNum.getValue());
            keys.add(line.configCatgCd.getValue());
            for (int j = 0; j < line.NWXC014003PMsg.getValidCount(); j++) {
                NWXC014003PMsg calcBase = new NWXC014003PMsg();
                EZDMsg.copy(line.NWXC014003PMsg.no(j), null, calcBase, null);
                lineList.add(calcBase);
            }
            calcBaseList.put(keys, lineList);
        }
        return calcBaseList;
    }

    private Map<List<String>, List<NWXC014003PMsg>> calculatePrcAmt(NWXC014001PMsg param, 
                                                                     List<NWXC014001prcRuleWrkBean> ruleList, 
                                                                     List<NWXC014001prcRuleWrkBean> list,
                                                                     Map<List<String>, List<NWXC014003PMsg>> calcBaseList, 
                                                                     final ONBATCH_TYPE onBatchType) {

        
        Map<List<String>, List<NWXC014003PMsg>> cacheList = new HashMap<List<String>, List<NWXC014003PMsg>>();

        // Deep Copy HashMap
        Map<List<String>, List<NWXC014003PMsg>> calcBaseListCopy = copyMap(calcBaseList);

        for (NWXC014001prcRuleWrkBean bean : list) {
            cacheList = getCalculateResultCache(bean);
            if(cacheList == null){
                if (PRC_RULE_ADJ_LVL.HEADER.equals(bean.getPrcRuleAdjLvlCd())) {
                    if (PRC_RULE_ADJ_TP.ADD_CHARGE.equals(bean.getPrcRuleAdjTpCd())
                            && PRC_FMLA_TP.CUSTOM_PRICE.equals(bean.getPrcFmlaTpCd())
                            && PRC_FUNC_TP.GET_FREIGHT_API.equals(bean.getPrcFuncTpCd())) {
                        calcBaseListCopy = callGetFreightApi(param, bean, ruleList, calcBaseListCopy, onBatchType);
                        setCalculateResultCache(bean, calcBaseListCopy); // QC#15318 2016/10/25 Add
                    } else if (PRC_RULE_ADJ_TP.SET_PRICE.equals(bean.getPrcRuleAdjTpCd())
                            && PRC_FMLA_TP.CUSTOM_PRICE.equals(bean.getPrcFmlaTpCd())
                            && PRC_FUNC_TP.GET_PROMOTION_API.equals(bean.getPrcFuncTpCd())) {
                        calcBaseListCopy = (callPromoAmtApi(param, bean, calcBaseListCopy, onBatchType));
                        setCalculateResultCache(bean, calcBaseListCopy); // QC#15318 2016/10/25 Add
                    } else if (PRC_RULE_ADJ_TP.SET_PRICE.equals(bean.getPrcRuleAdjTpCd())
                            && PRC_FMLA_TP.CUSTOM_PRICE.equals(bean.getPrcFmlaTpCd())
                            && PRC_FUNC_TP.NEGOTIATED_DEAL_API.equals(bean.getPrcFuncTpCd())) {
                        negoBean = bean;
                    // START 2023/10/26 T.Fukuta [CSA-QC#61619,ADD]
                    } else if (PRC_RULE_ADJ_TP.ADD_CHARGE.equals(bean.getPrcRuleAdjTpCd())
                            && PRC_FMLA_TP.CUSTOM_PRICE.equals(bean.getPrcFmlaTpCd())
                            && PRC_FUNC_TP.GET_QTY_BASED_FEE_API.equals(bean.getPrcFuncTpCd())) {
                        calcBaseListCopy = callGetQtyBasedFeeApi(param, bean, ruleList, calcBaseListCopy, onBatchType);
                        setCalculateResultCache(bean, calcBaseListCopy);
                    // END 2023/10/26 T.Fukuta [CSA-QC#61619,ADD]
                    } else {
                        calcBaseListCopy = editHdrRuleAdjust(param, ruleList, bean, calcBaseListCopy, onBatchType);
                        setCalculateResultCache(bean, calcBaseListCopy); // QC#15318 2016/10/25 Add
                    }
                } else {
                    if (PRC_RULE_ADJ_TP.ADD_CHARGE.equals(bean.getPrcRuleAdjTpCd())
                            && PRC_FMLA_TP.CUSTOM_PRICE.equals(bean.getPrcFmlaTpCd())
                            && PRC_FUNC_TP.GET_FREIGHT_API.equals(bean.getPrcFuncTpCd())) {
                        calcBaseListCopy = callGetFreightLineApi(param, bean, calcBaseListCopy, onBatchType);
                    } else {
                        calcBaseListCopy = editLineRuleAdjust(param, bean, calcBaseListCopy, onBatchType);
                    }
                }
            } else {
                calcBaseListCopy =  cacheList;
            }
        }
        return calcBaseListCopy;
    }

    private void setCalcBase(NWXC014001PMsg param, Map<List<String>, List<NWXC014003PMsg>> calcBaseList){
        if(calcBaseList == null){
            return;
        }
        NWXC014002PMsg line = null;
        List<NWXC014003PMsg> list = null;
        BigDecimal subTotal = BigDecimal.ZERO;
        for(int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++){
            line = param.NWXC014002PMsg.no(i);
            if (isFrozen(line)) {
                // QC#22241 2017/11/02 Add Start
                list = getCalcBaseList(line, calcBaseList);
               // QC#53586 2019/10/04 Mod Start
               // ZYPEZDItemValueSetter.setValue(line.entCpoDtlDealSlsAmt, getNetAmount(list));
                ZYPEZDItemValueSetter.setValue(line.entCpoDtlDealSlsAmt, getNetAmount(list, false));
               // QC#53586 2019/10/04 Mod End
                subTotal = subTotal.add(line.entCpoDtlDealSlsAmt.getValue());
                // QC#22241 2017/11/02 Add End
                continue;
            }
            list = getCalcBaseList(line, calcBaseList);
            ZYPTableUtil.clear(line.NWXC014003PMsg);
            int idx = 0;
            for(NWXC014003PMsg calcBase : list){
                EZDMsg.copy(calcBase, null, line.NWXC014003PMsg.no(idx), null);
                idx++;
            }
            line.NWXC014003PMsg.setValidCount(idx);
            // QC#22241 2017/11/02 Add Start
            ZYPEZDItemValueSetter.setValue(line.entCpoDtlDealSlsAmt, getNetAmount(list, false));
            subTotal = subTotal.add(line.entCpoDtlDealSlsAmt.getValue());
            // QC#22241 2017/11/02 Add End
        }
        // QC#22241 2017/11/02 Add Start
        ZYPEZDItemValueSetter.setValue(param.xxTotAmt, subTotal);
        // QC#22241 2017/11/02 Add End
    }

    private Map<List<String>, List<NWXC014003PMsg>> getCalculateResultCache(NWXC014001prcRuleWrkBean bean) {
        List<Object> keys = new ArrayList<Object>();
        keys.add(bean.getConfigCatgCd());
        keys.add(bean.getPrcRuleDtlPk());
        return resultCache.get(keys);
    }

    private void setCalculateResultCache(NWXC014001prcRuleWrkBean bean, Map<List<String>, List<NWXC014003PMsg>> calcBaseList) {
        if(calcBaseList == null ){
            return;
        }
        if (PRC_RULE_ADJ_LVL.HEADER.equals(bean.getPrcRuleAdjLvlCd())) {
            List<Object> keys = new ArrayList<Object>();
            keys.add(bean.getConfigCatgCd());
            keys.add(bean.getPrcRuleDtlPk());
            resultCache.put(keys, calcBaseList);
        }
    }

    private BigDecimal jdgPrecedence(String prcRuleModTpCd,
                                        String trxLineNum, 
                                        String trxLineSubNum, 
                                        String configCatgCd, 
                                        String prcPrcdActTpCd, 
                                        Map<BigDecimal, Map<List<String>, List<NWXC014003PMsg>>> prcdPool){
        Map<List<String>, List<NWXC014003PMsg>> map = null;
        Set<BigDecimal> keys = prcdPool.keySet();
        BigDecimal highestKey = null;
        BigDecimal lowestKey = null;
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal highest = BigDecimal.ZERO;
        BigDecimal lowest = BigDecimal.ZERO;
        for (BigDecimal key : keys) {
            map = prcdPool.get(key);
            List<String> lineKey = new ArrayList<String>();
            lineKey.add(trxLineNum);
            lineKey.add(trxLineSubNum);
            lineKey.add(configCatgCd);

            List<NWXC014003PMsg> calcBaseList = map.get(lineKey);
            BigDecimal netPrice = BigDecimal.ZERO;
            BigDecimal charges = BigDecimal.ZERO;
            for (NWXC014003PMsg calcBase : calcBaseList) {
                if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    netPrice = add(netPrice, calcBase.unitPrcAmt.getValue());
                } else if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())) {
                    netPrice = subtract(netPrice, calcBase.unitPrcAmt.getValue());
                } else if (PRC_DTL_GRP.FREIGHT.equals(calcBase.prcDtlGrpCd.getValue())) {
                    charges = add(charges, calcBase.unitPrcAmt.getValue());
                // QC#21841 2018/05/21 Add Start
                } else if (PRC_DTL_GRP.HANDLING_FEE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    charges = add(charges, calcBase.unitPrcAmt.getValue());
                } else if (PRC_DTL_GRP.FUEL_SURCHARGE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    charges = add(charges, calcBase.unitPrcAmt.getValue());
                } else if (PRC_DTL_GRP.SHIPPING_FEE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    charges = add(charges, calcBase.unitPrcAmt.getValue());
                // QC#21841 2018/05/21 Add End
                }
            }
            if (PRC_RULE_MOD_TP.SELL_PRICE.equals(prcRuleModTpCd)) {
                price = netPrice.abs();
            } else {
                price = charges.abs();
            }
            if (highestKey == null) {
                highestKey = key;
                lowestKey = key;
                highest = price;
                lowest = price;
            } else {
                if (highest.compareTo(price) < 0) {
                    highestKey = key;
                    highest = price;
                }
                if (lowest.compareTo(price) > 0) {
                    lowestKey = key;
                    lowest = price;
                }

            }
        }
        if (PRC_PRCD_ACT_TP.HIGHEST.equals(prcPrcdActTpCd)) {
            return highestKey;
        }

        return lowestKey;
    }
    
    private Map<List<String>, List<NWXC014003PMsg>> adoptPrecedence(Map<List<String>, List<NWXC014003PMsg>> calcBaseList,
                                                                     Map<BigDecimal, Map<List<String>, List<NWXC014003PMsg>>> prcdPool,
                                                                     BigDecimal prcRuleHdrPk,
                                                                     String trxLineNum, 
                                                                     String trxLineSubNum, 
                                                                     String configCatgCd) {
        Map<List<String>, List<NWXC014003PMsg>> calcBaseListPrcd = prcdPool.get(prcRuleHdrPk);
        List<String> lineKey = new ArrayList<String>();
        lineKey.add(trxLineNum);
        lineKey.add(trxLineSubNum);
        lineKey.add(configCatgCd);
        calcBaseList.put(lineKey, calcBaseListPrcd.get(lineKey));

        return calcBaseList;
    }
    private Map<List<String>, List<NWXC014003PMsg>> editHdrRuleAdjust(NWXC014001PMsg param, 
                                                                       List<NWXC014001prcRuleWrkBean> ruleList, 
                                                                       NWXC014001prcRuleWrkBean bean,
                                                                       Map<List<String>, List<NWXC014003PMsg>> calcBaseList,
                                                                       final ONBATCH_TYPE onBatchType) {

        boolean frozenFlg = false;
        String glblCmpyCd = param.glblCmpyCd.getValue();
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal frozenAmount = BigDecimal.ZERO;
        BigDecimal frozeTargetAmt = BigDecimal.ZERO;
        // QC#29565 2018/12/28 Add Start
        BigDecimal qty = BigDecimal.ZERO;
        BigDecimal frozenQty = BigDecimal.ZERO;
        BigDecimal totalQty = BigDecimal.ZERO;
        // QC#29565 2018/12/28 Add End
        BigDecimal prcAmt = BigDecimal.ZERO;
        BigDecimal numerAmt = BigDecimal.ZERO;
        BigDecimal denomAmt = BigDecimal.ZERO;
        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal ratio = BigDecimal.ZERO;
        BigDecimal linePrice = BigDecimal.ZERO;
        BigDecimal unitCalcPrice = BigDecimal.ZERO;
        BigDecimal amtTotal = BigDecimal.ZERO;
        String prcDtlGrpCd = null;
        String ccy = null;
        List<NWXC014003PMsg> targetCalcBaseList = new ArrayList<NWXC014003PMsg>();
        BigDecimal calcPrcAmt = BigDecimal.ZERO;

        NWXC014002PMsg targetLine = getLine(param, bean.getTrxLineNum(), bean.getTrxLineSubNum(), bean.getConfigCatgCd());
        if (targetLine == null) {
            return calcBaseList;
        }
        ccy = targetLine.ccyCd.getValue();

        List<NWXC014002PMsg> targetLineList = getTargetRuleLineList(param, ruleList, bean);

        for (NWXC014002PMsg line : targetLineList) {
            targetCalcBaseList = getCalcBaseList(line, calcBaseList);
            frozenFlg = isFrozen(line);
            // QC#53586 2019/10/04 Mod Start
            // amount = getNetAmount(targetCalcBaseList);
            amount = getNetAmount(targetCalcBaseList, PRC_RULE_MOD_TP.CHARGES.equals(bean.getPrcRuleModTpCd()));
            // QC#53586 2019/10/04 Mod End
            qty = line.ordQty.getValue(); // QC#29565 2018/12/28 Add
            totalAmount = add(totalAmount, amount);
            totalQty = add(totalQty, qty);
            if (frozenFlg) {
                frozenQty = add(frozenQty, qty); // QC#29565 2018/12/28 Add
                frozenAmount = add(frozenAmount, amount);
            }
            for (NWXC014003PMsg calcBase : targetCalcBaseList) {
                if (isEquals(calcBase.specCondPrcPk.getValue(), bean.getSpecCondPrcPk())) {
                    if (frozenFlg || ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManEntryFlg.getValue()))
                        frozeTargetAmt = add(frozeTargetAmt, calcBase.calcPrcAmtRate.getValue());
                }
            }
        }
        if (PRC_RULE_ATTRB.PERCENT.equals(bean.getPrcRuleAttrbCd())) {
            prcAmt = multiply(glblCmpyCd, totalAmount, bean.getPrcRuleDtlRate(), ccy);
        } else if (PRC_RULE_ATTRB.VALUE.equals(bean.getPrcRuleAttrbCd())) {
            prcAmt = round(glblCmpyCd, new BigDecimal(bean.getPrcRuleDtlTxt()), ccy);
        // QC#27713 2018/08/30 Add Start
            if(CONFIG_CATG.INBOUND.equals(bean.getConfigCatgCd())){
                prcAmt = prcAmt.negate();
            }
        // QC#27713 2018/08/30 Add End
        } else if (PRC_RULE_ATTRB.FORMULA.equals(bean.getPrcRuleAttrbCd())) {
            prcAmt = callFmlaApi(param, targetLine, bean, totalAmount, ccy, onBatchType);
        }

        if (PRC_RULE_ADJ_TP.SET_PRICE.equals(bean.getPrcRuleAdjTpCd())) {
            calcPrcAmt = subtract(totalAmount, prcAmt);
        } else if (PRC_RULE_ADJ_TP.SURCHARGE.equals(bean.getPrcRuleAdjTpCd())) {
            calcPrcAmt = prcAmt.multiply(MINUS);
        }else{
            calcPrcAmt = prcAmt;
        }
        // QC#29565 2018/12/28 Mod Start
        // denomAmt = totalAmount.subtract(frozenAmount);
        if (totalAmount.compareTo(BigDecimal.ZERO) == 0) {
            denomAmt = totalQty.subtract(frozenQty);
        } else {
            denomAmt = totalAmount.subtract(frozenAmount);
        }
        // QC#29565 2018/12/28 Mod End
        if (denomAmt.compareTo(BigDecimal.ZERO) == 0) {
            return calcBaseList;
        }
        calcPrcAmt = subtract(calcPrcAmt, frozeTargetAmt);

        if (PRC_RULE_MOD_TP.CHARGES.equals(bean.getPrcRuleModTpCd())) {
            // QC#21841 2018/05/21 Mod Start
            // prcDtlGrpCd = PRC_DTL_GRP.FREIGHT;
            if (PRC_RULE_ADJ_TP.FUEL_SURCHARGE.equals(bean.getPrcRuleAdjTpCd())) {
                prcDtlGrpCd = PRC_DTL_GRP.FUEL_SURCHARGE;
            } else if (PRC_RULE_ADJ_TP.HANDLING_FEE.equals(bean.getPrcRuleAdjTpCd())) {
                prcDtlGrpCd = PRC_DTL_GRP.HANDLING_FEE;
            } else if (PRC_RULE_ADJ_TP.SHIPPING_FEE.equals(bean.getPrcRuleAdjTpCd())) {
                prcDtlGrpCd = PRC_DTL_GRP.SHIPPING_FEE;
            } else if (PRC_RULE_ADJ_TP.RESTOCKING_FEE.equals(bean.getPrcRuleAdjTpCd())) { // QC#27479 2018/08/03 Add
                prcDtlGrpCd = PRC_DTL_GRP.RESTOCKING_FEE;
            } else {
                prcDtlGrpCd = PRC_DTL_GRP.FREIGHT;
            }
            // QC#21841 2018/05/21 Mod End
        } else {
            prcDtlGrpCd = PRC_DTL_GRP.DISCOUNT;
        }
        for (NWXC014002PMsg line : targetLineList) {
            // QC#28995 2018/11/09 Add Start
            if(isFrozen(line)){
                continue;
            }
            // QC#28995 2018/11/09 Add End
            targetCalcBaseList = getCalcBaseList(line, calcBaseList);

            if (PRC_RULE_ATTRB.PERCENT.equals(bean.getPrcRuleAttrbCd())) {
               // QC#53586 2019/10/04 Mod Start
               // linePrice = getNetPrice(targetCalcBaseList);
                linePrice = getNetPrice(targetCalcBaseList, PRC_RULE_MOD_TP.CHARGES.equals(bean.getPrcRuleModTpCd()));
               // QC#53586 2019/10/04 Mod End
                rate = bean.getPrcRuleDtlRate();
                if (PRC_RULE_ADJ_TP.SET_PRICE.equals(bean.getPrcRuleAdjTpCd())) {
                    rate = subtract(BigDecimal.ONE, rate);
                } else if (PRC_RULE_ADJ_TP.SURCHARGE.equals(bean.getPrcRuleAdjTpCd())) {
                    rate = rate.multiply(MINUS);
                }
                unitCalcPrice = multiply(glblCmpyCd, linePrice, rate, ccy);
            } else {
                // QC#29565 2018/12/28 Mod Start
                // numerAmt = getNetAmount(targetCalcBaseList);
                // ratio = numerAmt.divide(denomAmt, RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                if (totalAmount.compareTo(BigDecimal.ZERO) == 0) {
                    numerAmt = line.ordQty.getValue();
                    ratio = numerAmt.divide(denomAmt, RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                } else {
                    numerAmt = getNetAmount(targetCalcBaseList, PRC_RULE_MOD_TP.CHARGES.equals(bean.getPrcRuleModTpCd()));
                    ratio = numerAmt.divide(denomAmt, RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                }
                // QC#29565 2018/12/28 Mod End
                unitCalcPrice = divide(glblCmpyCd, calcPrcAmt.multiply(ratio), line.ordQty.getValue(), ccy);
            }
            NWXC014003PMsg editCalcBase = editCalcBase(param, line, prcDtlGrpCd, rate, unitCalcPrice, bean);
            calcBaseList = setPriceList(line, calcBaseList, editCalcBase);
            amtTotal = amtTotal.add(editCalcBase.calcPrcAmtRate.getValue());
        }
        return adjustFraction(param, targetLineList, calcBaseList, prcDtlGrpCd, bean.getSpecCondPrcPk(), amtTotal, calcPrcAmt);
    }

    private Map<List<String>, List<NWXC014003PMsg>> setPriceList(NWXC014002PMsg line, 
                                                                  Map<List<String>, List<NWXC014003PMsg>> calcBaseList,
                                                                  NWXC014003PMsg newData){
        NWXC014003PMsg data = null;
        boolean isBaseManual =  false;
        if (isFrozen(line)) {
            return calcBaseList;
        }
        List<NWXC014003PMsg> targetCalcBaseList = getCalcBaseList(line, calcBaseList);

        for (NWXC014003PMsg calcBase : targetCalcBaseList) {
            if (isEquals(calcBase.specCondPrcPk.getValue(), newData.specCondPrcPk.getValue())) {
                data = calcBase;
            }
            // QC#25422 2018/05/13 Add Start
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManEntryFlg.getValue())) {
                    isBaseManual = true;
                }
            }
            if (PRC_COND_TP.MANUAL_PRICE.equals(calcBase.prcCondTpCd.getValue())) {
                isBaseManual = true;
            }
            // QC#25422 2018/05/13 Add End
        }
        // QC#25422 2018/05/13 Add Start
        if (isBaseManual && PRC_DTL_GRP.DISCOUNT.equals(newData.prcDtlGrpCd.getValue())) {
            return calcBaseList;
        }
        // QC#25422 2018/05/13 Add End
        if (data == null) {
            targetCalcBaseList.add(newData);
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(data.prcCondManEntryFlg.getValue())) {
                return calcBaseList;
            } else {
                data = newData;
            }
        }

        return calcBaseList;
    }

    private Map<List<String>, List<NWXC014003PMsg>> editLineRuleAdjust(NWXC014001PMsg param, 
                                                                        NWXC014001prcRuleWrkBean bean, Map<List<String>, 
                                                                        List<NWXC014003PMsg>> calcBaseList, 
                                                                        final ONBATCH_TYPE onBatchType) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        BigDecimal linePrice = BigDecimal.ZERO;
        BigDecimal unitCalcPrice = BigDecimal.ZERO;
        String prcDtlGrpCd = null;
        String ccy = null;
        BigDecimal rate = BigDecimal.ZERO;
        Map<List<String>, List<NWXC014003PMsg>> rtnList = new HashMap<List<String>, List<NWXC014003PMsg>>();

        NWXC014002PMsg targetLine = getLine(param, bean.getTrxLineNum(), bean.getTrxLineSubNum(), bean.getConfigCatgCd());
        if (targetLine == null) {
            return calcBaseList;
        }
        List<NWXC014003PMsg> targetCalcBaseList = getCalcBaseList(targetLine, calcBaseList);
        for (NWXC014003PMsg calcBase : targetCalcBaseList) {
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                ccy = calcBase.autoPrcCcyCd.getValue();
            }
        }
        if (ccy == null) {
            return calcBaseList;
        }
        // QC#53586 2019/10/04 Mod Start
        // linePrice = getNetPrice(targetCalcBaseList);
        linePrice = getNetPrice(targetCalcBaseList, PRC_RULE_MOD_TP.CHARGES.equals(bean.getPrcRuleModTpCd()));
        // QC#53586 2019/10/04 Mod End
        if (PRC_RULE_ATTRB.PERCENT.equals(bean.getPrcRuleAttrbCd())) {
            rate = bean.getPrcRuleDtlRate();
            if (PRC_RULE_ADJ_TP.SET_PRICE.equals(bean.getPrcRuleAdjTpCd())) {
                rate = subtract(BigDecimal.ONE, rate);
            } else if (PRC_RULE_ADJ_TP.SURCHARGE.equals(bean.getPrcRuleAdjTpCd())) {
                rate = rate.multiply(MINUS);
            }
            unitCalcPrice = multiply(glblCmpyCd, linePrice, rate, ccy);
        } else if (PRC_RULE_ATTRB.VALUE.equals(bean.getPrcRuleAttrbCd())) {
            unitCalcPrice = round(glblCmpyCd, new BigDecimal(bean.getPrcRuleDtlTxt()), ccy);
            if (PRC_RULE_ADJ_TP.SET_PRICE.equals(bean.getPrcRuleAdjTpCd())) {
                unitCalcPrice = subtract(linePrice, unitCalcPrice);
            } else if (PRC_RULE_ADJ_TP.SURCHARGE.equals(bean.getPrcRuleAdjTpCd())) {
                unitCalcPrice = unitCalcPrice.multiply(MINUS);
            }
        } else if (PRC_RULE_ATTRB.FORMULA.equals(bean.getPrcRuleAttrbCd())) {
            unitCalcPrice = callFmlaApi(param, targetLine, bean, linePrice, ccy, onBatchType);
            if (PRC_RULE_ADJ_TP.SET_PRICE.equals(bean.getPrcRuleAdjTpCd())) {
                unitCalcPrice = subtract(linePrice, unitCalcPrice);
            } else if (PRC_RULE_ADJ_TP.SURCHARGE.equals(bean.getPrcRuleAdjTpCd())) {
                unitCalcPrice = unitCalcPrice.multiply(MINUS);
            }
        }

        if (PRC_RULE_MOD_TP.CHARGES.equals(bean.getPrcRuleModTpCd())) {
            // QC#21841 2018/05/21 Mod Start
            //prcDtlGrpCd = PRC_DTL_GRP.FREIGHT;
            if (PRC_RULE_ADJ_TP.FUEL_SURCHARGE.equals(bean.getPrcRuleAdjTpCd())) {
                prcDtlGrpCd = PRC_DTL_GRP.FUEL_SURCHARGE;
            } else if (PRC_RULE_ADJ_TP.HANDLING_FEE.equals(bean.getPrcRuleAdjTpCd())) {
                prcDtlGrpCd = PRC_DTL_GRP.HANDLING_FEE;
            } else if (PRC_RULE_ADJ_TP.SHIPPING_FEE.equals(bean.getPrcRuleAdjTpCd())) {
                prcDtlGrpCd = PRC_DTL_GRP.SHIPPING_FEE;
            } else if (PRC_RULE_ADJ_TP.RESTOCKING_FEE.equals(bean.getPrcRuleAdjTpCd())) { // QC#27479 2018/08/03 Add
                prcDtlGrpCd = PRC_DTL_GRP.RESTOCKING_FEE;
            } else {
                prcDtlGrpCd = PRC_DTL_GRP.FREIGHT;
            }
            // QC#21841 2018/05/21 Mod End
        } else {
            prcDtlGrpCd = PRC_DTL_GRP.DISCOUNT;
        }

        NWXC014003PMsg editCalcBase = editCalcBase(param, targetLine, prcDtlGrpCd, rate, unitCalcPrice, bean);
        rtnList = setPriceList(targetLine, calcBaseList, editCalcBase);
        return rtnList;
    }

    private NWXC014003PMsg editCalcBase(NWXC014001PMsg param, 
                                         NWXC014002PMsg line,
                                         String prcDtlGrpCd,
                                         BigDecimal rate,
                                         BigDecimal unitCalcPrice,
                                         NWXC014001prcRuleWrkBean bean) {
        NWXC014003PMsg calcBase = new NWXC014003PMsg();

        ZYPEZDItemValueSetter.setValue(calcBase.trxLineNum, line.trxLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.trxLineSubNum, line.trxLineSubNum.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.configCatgCd, line.configCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.prcDtlGrpCd, prcDtlGrpCd);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCatgCd, line.prcCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(calcBase.pkgUomCd, line.pkgUomCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.autoPrcCcyCd, line.ccyCd.getValue());
        if (PRC_RULE_ATTRB.PERCENT.equals(bean.getPrcRuleAttrbCd())) {
            ZYPEZDItemValueSetter.setValue(calcBase.prcCondUnitCd, PRC_COND_UNIT.PCT);
            ZYPEZDItemValueSetter.setValue(calcBase.autoPrcAmtRate, rate);
            ZYPEZDItemValueSetter.setValue(calcBase.unitPrcAmt, unitCalcPrice);
            ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, unitCalcPrice.multiply(line.ordQty.getValue()));
        } else {
            ZYPEZDItemValueSetter.setValue(calcBase.prcCondUnitCd, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(calcBase.autoPrcAmtRate, unitCalcPrice);
            ZYPEZDItemValueSetter.setValue(calcBase.unitPrcAmt, unitCalcPrice);
            ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, unitCalcPrice.multiply(line.ordQty.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(calcBase.specCondPrcPk, bean.getSpecCondPrcPk());
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(calcBase.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(calcBase.prcRulePrcdPk, bean.getPrcRulePrcdGrpNum());
        // QC#9700  2018/09/03 Add End
        return calcBase;
    }
    private Map<List<String>, List<NWXC014003PMsg>> adjustFraction(NWXC014001PMsg param, 
                                                                   List<NWXC014002PMsg> lineList, 
                                                                   Map<List<String>, List<NWXC014003PMsg>> calcBaseList,
                                                                   String prcDtlGrpCd, 
                                                                   BigDecimal specCondPrcPk, 
                                                                   BigDecimal totalAmt,
                                                                   BigDecimal targetAmt) {

        if (totalAmt.compareTo(targetAmt) == 0) {
            return calcBaseList;
        }

        // find Last Row
        NWXC014002PMsg targetline = null;
        // QC#21390 2017/09/28 Mod Start
        // for (NWXC014002PMsg line : lineList) {
        //     if (!isFrozen(line)) {
        //         targetline = line;
        //     }
        // }
        BigDecimal maxValue = null;
        for (NWXC014002PMsg line : lineList) {
            // QC#27792 2018/08/27 Mod End
            //if (!isFrozen(line) && !isManual(line, specCondPrcPk, calcBaseList)) {
            //    targetline = line;
            //}
            if (!isFrozen(line)) {
                NWXC014003PMsg data = null;
                List<NWXC014003PMsg> targetCalcBaseList = getCalcBaseList(line, calcBaseList);
                for (NWXC014003PMsg calcBase : targetCalcBaseList) {
                    if (isEquals(calcBase.specCondPrcPk.getValue(), specCondPrcPk)) {
                        data = calcBase;
                    }
                }
                if (data != null) {
                    if (maxValue == null) {
                        targetline = line;
                        maxValue = data.calcPrcAmtRate.getValue().abs();
                    } else if (data.calcPrcAmtRate.getValue().abs().compareTo(maxValue) > 0) {
                        targetline = line;
                        maxValue = data.calcPrcAmtRate.getValue().abs();
                    }
                }
            }
            // QC#27792 2018/08/27 Mod End
        }
        // QC#21390 2017/09/28 Mod End
        if (targetline == null) {
            return calcBaseList;
        }

        List<NWXC014003PMsg> targetCalcBaseList = getCalcBaseList(targetline, calcBaseList);

        BigDecimal fraction = targetAmt.subtract(totalAmt);
        NWXC014003PMsg calcBase = new NWXC014003PMsg();

        String newPrcDtlGrpCd = null;
        if (PRC_DTL_GRP.DISCOUNT.equals(prcDtlGrpCd)) {
            newPrcDtlGrpCd = PRC_DTL_GRP.ROUNDING_FACTOR_0;
        // QC#21841 2018/05/21 Mod Start
        //} else {
        //    newPrcDtlGrpCd = PRC_DTL_GRP.ROUNDING_FACTOR_1;
        } else if (PRC_DTL_GRP.FREIGHT.equals(prcDtlGrpCd)) {
            newPrcDtlGrpCd = PRC_DTL_GRP.ROUNDING_FACTOR_1;
        } else {
            newPrcDtlGrpCd = PRC_DTL_GRP.ROUNDING_FACTOR_2;
        // QC#21841 2018/05/21 Mod End
        }
        ZYPEZDItemValueSetter.setValue(calcBase.trxLineNum, targetline.trxLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.trxLineSubNum, targetline.trxLineSubNum.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.configCatgCd, targetline.configCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.prcDtlGrpCd, newPrcDtlGrpCd);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCatgCd, targetline.prcCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(calcBase.pkgUomCd, targetline.pkgUomCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondUnitCd, PRC_COND_UNIT.AMT);
        ZYPEZDItemValueSetter.setValue(calcBase.autoPrcCcyCd, targetline.ccyCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.autoPrcAmtRate, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, fraction);
        ZYPEZDItemValueSetter.setValue(calcBase.unitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(calcBase.specCondPrcPk, specCondPrcPk);
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(calcBase.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        calcBase.prcRulePrcdPk.clear();
        // QC#9700  2018/09/03 Add End

        targetCalcBaseList.add(calcBase);
        return calcBaseList;
    }

    private BigDecimal callFmlaApi(NWXC014001PMsg param, NWXC014002PMsg line, NWXC014001prcRuleWrkBean bean, BigDecimal baseAmt, String ccy, final ONBATCH_TYPE onBatchType) {
        NWZC170001PMsg pmsg = new NWZC170001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        // 2017/07/14 S21_NA#18191 Mod Start
//        ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NWZC170001.MODE_BASE);
        ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NWZC170001.MODE_NO_BASE);
        // 2017/07/14 S21_NA#18191 Mod End
        ZYPEZDItemValueSetter.setValue(pmsg.prcBaseDt, line.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.prcFmlaPk, bean.getPrcFmlaPk());
        ZYPEZDItemValueSetter.setValue(pmsg.ccyCd, ccy);
        // 2017/07/14 S21_NA#18191 Mod Start
//        ZYPEZDItemValueSetter.setValue(pmsg.basePrcAmt, baseAmt);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, line.mdseCd);
        ZYPEZDItemValueSetter.setValue(pmsg.ordTakeMdseCd, line.ordTakeMdseCd);
        ZYPEZDItemValueSetter.setValue(pmsg.ordCustUomQty, line.ordQty);
        ZYPEZDItemValueSetter.setValue(pmsg.pkgUomCd, line.pkgUomCd);
        // 2017/07/14 S21_NA#18191 Mod End
        new NWZC170001().execute(pmsg, onBatchType);
        if (S21ApiUtil.isXxMsgId(pmsg)) {
            S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pmsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                lineMap.addXxMsgIdWithPrm(msgId, msgPrms);
                lineMap.flush();
            }
        }
        return pmsg.xxCalcPrcAmt.getValue();
    }

    private Map<List<String>, List<NWXC014003PMsg>> callPromoAmtApi(NWXC014001PMsg param, 
                                                                     NWXC014001prcRuleWrkBean bean, 
                                                                     Map<List<String>, List<NWXC014003PMsg>> calcBaseList, 
                                                                     final ONBATCH_TYPE onBatchType) {
        int validCount = 0;
        int validCalcBaseCount = 0;

        NWXC012001PMsg pmsg = new NWXC012001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.prcBaseDt, param.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.lineBizTpCd, param.lineBizTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.dsAcctNum, param.dsAcctNum.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.dsAcctNm, param.dsAcctNm.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.dsCustSicCd, param.dsCustSicCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.locNum, param.locNum.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.dsAcctClsCd, param.dsAcctClsCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.dsAcctTpCd, param.dsAcctTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.dsAcctDlrCd, param.dsAcctDlrCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.dsAcctGrpCd, param.dsAcctGrpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.coaChCd, param.coaChCd.getValue());
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(pmsg.prcRulePrcdPk, bean.getPrcRulePrcdGrpNum());
        // QC#9700  2018/09/03 Add End
        for (int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++) {
            NWXC014002PMsg ruleline = param.NWXC014002PMsg.no(i);
            NWXC012002PMsg promoLine = pmsg.NWXC012002PMsg.no(validCount);
            if (!CONFIG_CATG.INBOUND.equals(ruleline.configCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(promoLine.dsOrdPosnNum, ruleline.dsOrdPosnNum.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.trxLineNum, ruleline.trxLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.trxLineSubNum, ruleline.trxLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.configCatgCd, ruleline.configCatgCd.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.prcBaseDt, ruleline.prcBaseDt.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.ordQty, ruleline.ordQty.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.invQty, ruleline.invQty.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.prcCatgCd, ruleline.prcCatgCd.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctNum_SH, ruleline.dsAcctNum_SH.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctNm_SH, ruleline.dsAcctNm_SH.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsCustSicCd_SH, ruleline.dsCustSicCd_SH.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.locNum_SH, ruleline.locNum_SH.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctClsCd_SH, ruleline.dsAcctClsCd_SH.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctTpCd_SH, ruleline.dsAcctTpCd_SH.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctDlrCd_SH, ruleline.dsAcctDlrCd_SH.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctGrpCd_SH, ruleline.dsAcctGrpCd_SH.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.coaChCd_SH, ruleline.coaChCd_SH.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctNum_BL, ruleline.dsAcctNum_BL.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctNm_BL, ruleline.dsAcctNm_BL.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsCustSicCd_BL, ruleline.dsCustSicCd_BL.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.locNum_BL, ruleline.locNum_BL.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctClsCd_BL, ruleline.dsAcctClsCd_BL.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctTpCd_BL, ruleline.dsAcctTpCd_BL.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctDlrCd_BL, ruleline.dsAcctDlrCd_BL.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dsAcctGrpCd_BL, ruleline.dsAcctGrpCd_BL.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.coaChCd_BL, ruleline.coaChCd_BL.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.csmpNum, ruleline.csmpNum.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.dlrRefNum, ruleline.dlrRefNum.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.prcContrNum, ruleline.prcContrNum.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.coaBrCd, ruleline.coaBrCd.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.mdseCd, ruleline.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.ordTakeMdseCd, ruleline.ordTakeMdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.mdlId, ruleline.mdlId.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.mdlNm, ruleline.mdlNm.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.xxPrcCloFlg, ruleline.xxPrcCloFlg.getValue());
                ZYPEZDItemValueSetter.setValue(promoLine.specCondPrcPk, bean.getSpecCondPrcPk());
                List<NWXC014003PMsg> targetCalcBaseList = getCalcBaseList(ruleline, calcBaseList);
                validCalcBaseCount = 0;
                for(NWXC014003PMsg calcBase : targetCalcBaseList){
                  EZDMsg.copy(calcBase, null, promoLine.NWXC012003PMsg.no(validCalcBaseCount), null);
                  validCalcBaseCount++;
                }
                pmsg.NWXC012002PMsg.no(i).NWXC012003PMsg.setValidCount(validCalcBaseCount);
                validCount++;
            }
        }
        pmsg.NWXC012002PMsg.setValidCount(validCount);

        new NWXC012001().execute(pmsg, onBatchType);
        if (S21ApiUtil.isXxMsgId(pmsg)) {
            S21ApiMessageMap mesMap = new S21ApiMessageMap(param);
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pmsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                mesMap.addXxMsgIdWithPrm(msgId, msgPrms);
                mesMap.flush();
            }
        }
        for (int i = 0; i < pmsg.NWXC012002PMsg.getValidCount(); i++) {
            NWXC012002PMsg promoLine = pmsg.NWXC012002PMsg.no(i);
            List<NWXC014003PMsg> lineList = new ArrayList<NWXC014003PMsg>();
            List<String> keys = new ArrayList<String>();
            keys.add(promoLine.trxLineNum.getValue());
            keys.add(promoLine.trxLineSubNum.getValue());
            keys.add(promoLine.configCatgCd.getValue());
            for (int j = 0; j < promoLine.NWXC012003PMsg.getValidCount(); j++) {
                NWXC014003PMsg data = new NWXC014003PMsg();
                EZDMsg.copy(promoLine.NWXC012003PMsg.no(j), null, data, null);
                lineList.add(data);
            }
            calcBaseList.put(keys, lineList);

            if (S21ApiUtil.isXxMsgId(promoLine)) {
                NWXC014002PMsg targetLine = getLine(param, promoLine.trxLineNum.getValue(), promoLine.trxLineSubNum.getValue(), promoLine.configCatgCd.getValue());
                S21ApiMessageMap lineMap = new S21ApiMessageMap(targetLine);
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(promoLine);
                for (int k = 0; k < msgList.size(); k++) {
                    S21ApiMessage msg = msgList.get(k);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    lineMap.addXxMsgIdWithPrm(msgId, msgPrms);
                    lineMap.flush();
                }
            }
            
        }
        return calcBaseList;
    }

    private Map<List<String>, List<NWXC014003PMsg>> callGetFreightApi(NWXC014001PMsg param, 
                                                                       NWXC014001prcRuleWrkBean bean, 
                                                                       List<NWXC014001prcRuleWrkBean> ruleList, 
                                                                       Map<List<String>, List<NWXC014003PMsg>> calcBaseList, 
                                                                       final ONBATCH_TYPE onBatchType) {
        int validCount = 0;
        int validCalcBaseCount = 0;

        NWXC011001PMsg pmsg = new NWXC011001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.prcBaseDt, param.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.lineBizTpCd, param.lineBizTpCd.getValue());
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(pmsg.prcRulePrcdPk, bean.getPrcRulePrcdGrpNum());
        // QC#9700  2018/09/03 Add End
        NWXC014002PMsg line = getLine(param, bean.getTrxLineNum(), bean.getTrxLineSubNum(), bean.getConfigCatgCd());
        List<NWXC014002PMsg> targetLineList = getTargetRuleLineList(param, ruleList, bean);

        for (NWXC014002PMsg ruleline : targetLineList) {
            NWXC011002PMsg frtLine = pmsg.NWXC011002PMsg.no(validCount);
            //if (!CONFIG_CATG.INBOUND.equals(ruleline.configCatgCd.getValue())) {  // QC#27713 2018/08/22 Del
            ZYPEZDItemValueSetter.setValue(frtLine.trxLineNum, ruleline.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.trxLineSubNum, ruleline.trxLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.configCatgCd, ruleline.configCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.prcBaseDt, ruleline.prcBaseDt.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.shipToCustCd, ruleline.shipToCustCd.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.dsAcctNum, ruleline.dsAcctNum_SH.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.dsAcctNm, ruleline.dsAcctNm_SH.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.dsCustSicCd, ruleline.dsCustSicCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.locNum, ruleline.locNum_SH.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.dsAcctClsCd, ruleline.dsAcctClsCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.dsAcctTpCd, ruleline.dsAcctTpCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.dsAcctDlrCd, ruleline.dsAcctDlrCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.dsAcctGrpCd, ruleline.dsAcctGrpCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.coaChCd, ruleline.coaChCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.csmpNum, ruleline.csmpNum.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.dlrRefNum, ruleline.dlrRefNum.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.prcContrNum, ruleline.prcContrNum.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.frtCondCd, ruleline.frtCondCd.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.stCd, ruleline.stCd.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.ctryCd, ruleline.ctryCd.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.postCd, ruleline.postCd.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.shpgSvcLvlCd, ruleline.shpgSvcLvlCd.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.frtChrgToCd, ruleline.frtChrgToCd.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.frtChrgMethCd, ruleline.frtChrgMethCd.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.unitNetWt, ruleline.unitNetWt.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.ordQty, ruleline.ordQty.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.invQty, ruleline.invQty.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.xxPrcCloFlg, ruleline.xxPrcCloFlg.getValue());
            ZYPEZDItemValueSetter.setValue(frtLine.specCondPrcPk, bean.getSpecCondPrcPk());
            ZYPEZDItemValueSetter.setValue(frtLine.ccyCd, line.ccyCd.getValue());
            List<NWXC014003PMsg> targetCalcBaseList = getCalcBaseList(ruleline, calcBaseList);
            validCalcBaseCount = 0;
            for(NWXC014003PMsg calcBase : targetCalcBaseList){
              EZDMsg.copy(calcBase, null, frtLine.NWXC011003PMsg.no(validCalcBaseCount), null);
              validCalcBaseCount++;
            }
            frtLine.NWXC011003PMsg.setValidCount(validCalcBaseCount);
            validCount++;
            //}
            pmsg.NWXC011002PMsg.setValidCount(validCount);
        }

        new NWXC011001().execute(pmsg, onBatchType);
        if (S21ApiUtil.isXxMsgId(pmsg)) {
            S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pmsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                lineMap.addXxMsgIdWithPrm(msgId, msgPrms);
                lineMap.flush();
            }
        }
        for (int i = 0; i < pmsg.NWXC011002PMsg.getValidCount(); i++) {
            NWXC011002PMsg frtLine = pmsg.NWXC011002PMsg.no(i);
            List<NWXC014003PMsg> lineList = new ArrayList<NWXC014003PMsg>();
            List<String> keys = new ArrayList<String>();
            keys.add(frtLine.trxLineNum.getValue());
            keys.add(frtLine.trxLineSubNum.getValue());
            keys.add(frtLine.configCatgCd.getValue());
            for (int j = 0; j < frtLine.NWXC011003PMsg.getValidCount(); j++) {
                NWXC014003PMsg data = new NWXC014003PMsg();
                EZDMsg.copy(frtLine.NWXC011003PMsg.no(j), null, data, null);
                lineList.add(data);
            }
            calcBaseList.put(keys, lineList);
            if (S21ApiUtil.isXxMsgId(frtLine)) {
                NWXC014002PMsg ruleLine = getLine(param, frtLine.trxLineNum.getValue(), frtLine.trxLineSubNum.getValue(), frtLine.configCatgCd.getValue());
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(frtLine);
                S21ApiMessageMap lineMap = new S21ApiMessageMap(ruleLine);
                for (int k = 0; k < msgList.size(); k++) {
                    S21ApiMessage msg = msgList.get(k);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    lineMap.addXxMsgIdWithPrm(msgId, msgPrms);
                    lineMap.flush();
                }
            }
        }
        return calcBaseList;
    }

    private Map<List<String>, List<NWXC014003PMsg>> callGetFreightLineApi(NWXC014001PMsg param, 
                                              NWXC014001prcRuleWrkBean bean, 
                                              Map<List<String>, List<NWXC014003PMsg>> calcBaseList, 
                                              final ONBATCH_TYPE onBatchType) {
        int validCalcBaseCount = 0;
        NWXC014002PMsg line = getLine(param, bean.getTrxLineNum(), bean.getTrxLineSubNum(), bean.getConfigCatgCd());

        NWXC011001PMsg pmsg = new NWXC011001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.prcBaseDt, param.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.lineBizTpCd, param.lineBizTpCd.getValue());
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(pmsg.prcRulePrcdPk, bean.getPrcRulePrcdGrpNum());
        // QC#9700  2018/09/03 Add End

        NWXC011002PMsg frtLine = pmsg.NWXC011002PMsg.no(0);
        ZYPEZDItemValueSetter.setValue(frtLine.trxLineNum, line.trxLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.trxLineSubNum, line.trxLineSubNum.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.configCatgCd, line.configCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.prcBaseDt, line.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.shipToCustCd, line.shipToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.dsAcctNum, line.dsAcctNum_SH.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.dsAcctNm, line.dsAcctNm_SH.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.dsCustSicCd, line.dsCustSicCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.locNum, line.locNum_SH.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.dsAcctClsCd, line.dsAcctClsCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.dsAcctTpCd, line.dsAcctTpCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.dsAcctDlrCd, line.dsAcctDlrCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.dsAcctGrpCd, line.dsAcctGrpCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.coaChCd, line.coaChCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.csmpNum, line.csmpNum.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.dlrRefNum, line.dlrRefNum.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.prcContrNum, line.prcContrNum.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.frtCondCd, line.frtCondCd.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.stCd, line.stCd.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.ctryCd, line.ctryCd.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.postCd, line.postCd.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.shpgSvcLvlCd, line.shpgSvcLvlCd.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.frtChrgToCd, line.frtChrgToCd.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.frtChrgMethCd, line.frtChrgMethCd.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.unitNetWt, line.unitNetWt.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.ordQty, line.ordQty.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.invQty, line.invQty.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.xxPrcCloFlg, line.xxPrcCloFlg.getValue());
        ZYPEZDItemValueSetter.setValue(frtLine.specCondPrcPk, bean.getSpecCondPrcPk());
        ZYPEZDItemValueSetter.setValue(frtLine.ccyCd, line.ccyCd.getValue());
        List<NWXC014003PMsg> targetCalcBaseList = getCalcBaseList(line, calcBaseList);
        validCalcBaseCount = 0;
        for(NWXC014003PMsg calcBase : targetCalcBaseList){
          EZDMsg.copy(calcBase, null, frtLine.NWXC011003PMsg.no(validCalcBaseCount), null);
          validCalcBaseCount++;
        }
        pmsg.NWXC011002PMsg.no(0).NWXC011003PMsg.setValidCount(validCalcBaseCount);
        pmsg.NWXC011002PMsg.setValidCount(1);

        new NWXC011001().execute(pmsg, onBatchType);
        if (S21ApiUtil.isXxMsgId(pmsg)) {
            S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pmsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                lineMap.addXxMsgIdWithPrm(msgId, msgPrms);
                lineMap.flush();
            }
        }
        for (int i = 0; i < pmsg.NWXC011002PMsg.getValidCount(); i++) {
            frtLine = pmsg.NWXC011002PMsg.no(i);
            List<NWXC014003PMsg> lineList = new ArrayList<NWXC014003PMsg>();
            List<String> keys = new ArrayList<String>();
            keys.add(frtLine.trxLineNum.getValue());
            keys.add(frtLine.trxLineSubNum.getValue());
            keys.add(frtLine.configCatgCd.getValue());
            for (int j = 0; j < frtLine.NWXC011003PMsg.getValidCount(); j++) {
                NWXC014003PMsg data = new NWXC014003PMsg();
                EZDMsg.copy(frtLine.NWXC011003PMsg.no(j), null, data, null);
                lineList.add(data);
            }
            calcBaseList.put(keys, lineList);
            if (S21ApiUtil.isXxMsgId(frtLine)) {
                NWXC014002PMsg ruleLine = getLine(param, frtLine.trxLineNum.getValue(), frtLine.trxLineSubNum.getValue(), frtLine.configCatgCd.getValue());
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(frtLine);
                S21ApiMessageMap lineMap = new S21ApiMessageMap(ruleLine);
                for (int k = 0; k < msgList.size(); k++) {
                    S21ApiMessage msg = msgList.get(k);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    lineMap.addXxMsgIdWithPrm(msgId, msgPrms);
                    lineMap.flush();
                }
            }
        }
        return calcBaseList;
    }

    private boolean isNegotiation(NWXC014001PMsg pMsg, NWXC014001prcRuleWrkBean bean) {
        if (bean == null) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.negoDealAmt.getValue())) {
            return false;
        }
        ORD_CATG_BIZ_CTXTMsg ordCatgBizCtx = getOrdCatgBizCtx(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue());
        if (ordCatgBizCtx == null) {
            return false;
        }
        return true;
    }

    private Map<List<String>, List<NWXC014003PMsg>> callNegotiationAPI(NWXC014001PMsg param, NWXC014001prcRuleWrkBean bean, Map<List<String>, List<NWXC014003PMsg>> calcBaseList, final ONBATCH_TYPE onBatchType) {

        NWXC010002PMsg lineMsg = null;
        List<NWXC014003PMsg> targetCalcBaseList = null;
        BigDecimal basePriceUnitPriceAmount = BigDecimal.ZERO;
        MDSE_TP_VAL_SETTMsg mdseTpValSetTMsg = null;
        ORD_LINE_VAL_SETTMsg ordLineValSetTMsg = null;
        PRC_CALC_EXCL_SWHTMsg prcCalcExclSwhTMsg = null;
        int validCalcBaseCount = 0;
        BigDecimal specCondPrcPk = bean.getSpecCondPrcPk();

        NWXC010001PMsg nWXC010001PMsg = new NWXC010001PMsg();
        ZYPEZDItemValueSetter.setValue(nWXC010001PMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(nWXC010001PMsg.prcBaseDt, param.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(nWXC010001PMsg.trxHdrNum, param.trxHdrNum.getValue());
        ZYPEZDItemValueSetter.setValue(nWXC010001PMsg.negoDealAmt, param.negoDealAmt.getValue());
        ZYPEZDItemValueSetter.setValue(nWXC010001PMsg.prcRulePrcdPk, bean.getPrcRulePrcdGrpNum());
        for (int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++) {
            NWXC014002PMsg line = param.NWXC014002PMsg.no(i);
            lineMsg = nWXC010001PMsg.NWXC010002PMsg.no(i);

            targetCalcBaseList = getCalcBaseList(line, calcBaseList);
            // QC#53586 2019/10/04 Mod Start
            // basePriceUnitPriceAmount = getNetAmount(targetCalcBaseList);
            basePriceUnitPriceAmount = getNetAmount(targetCalcBaseList, false);
            // QC#53586 2019/10/04 Mod End

            ZYPEZDItemValueSetter.setValue(lineMsg.trxLineNum, line.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.trxLineSubNum, line.trxLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.configCatgCd, line.configCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.ordQty, line.ordQty.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.invQty, line.invQty.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.basePrcAmt, basePriceUnitPriceAmount);
            ZYPEZDItemValueSetter.setValue(lineMsg.xxPrcCloFlg, line.xxPrcCloFlg.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.specCondPrcPk, specCondPrcPk);
            ZYPEZDItemValueSetter.setValue(lineMsg.xxNegoFlg, ZYPConstant.FLG_ON_Y);
            if (!DS_ORD_LINE_DRCTN_CD_I.equals(dsOrdLineDrctnCd) && !DS_ORD_LINE_DRCTN_CD_O.equals(dsOrdLineDrctnCd)) {
                ZYPEZDItemValueSetter.setValue(lineMsg.xxNegoFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPConstant.FLG_ON_Y.equals(line.xxPrcCloFlg)) {
                ZYPEZDItemValueSetter.setValue(lineMsg.xxNegoFlg, ZYPConstant.FLG_OFF_N);
            }
            mdseTpValSetTMsg = getMdseTpValSet(param.glblCmpyCd.getValue(), NEGO_PRC_ELIGIBLE, line.coaMdseTpCd.getValue());
            if (mdseTpValSetTMsg == null) {
                ZYPEZDItemValueSetter.setValue(lineMsg.xxNegoFlg, ZYPConstant.FLG_OFF_N);
            }
            ordLineValSetTMsg = getOrdLineValSet(param.glblCmpyCd.getValue(), NEGO_PRC_ELIGIBLE, line.dsOrdLineCatgCd.getValue());
            if (ordLineValSetTMsg == null) {
                ZYPEZDItemValueSetter.setValue(lineMsg.xxNegoFlg, ZYPConstant.FLG_OFF_N);
            }
            // QC#22371 2017/12/26 Add Start
            prcCalcExclSwhTMsg = getPrcCalcSwh(param.glblCmpyCd.getValue(), line.rtlSwhCd.getValue());
            if (prcCalcExclSwhTMsg != null) {
                if (ZYPConstant.FLG_ON_Y.equals(prcCalcExclSwhTMsg.negoExclFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.xxNegoFlg, ZYPConstant.FLG_OFF_N);
                }
            }
            // QC#22371 2017/12/26 Add End
            if (CONFIG_CATG.INBOUND.equals(line.configCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(lineMsg.xxNegoFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxNegoFlg.getValue())) {
                if (!negoFlg) {
                    negoFlg = true;
                }
            }
            validCalcBaseCount = 0;
            for (NWXC014003PMsg calcBase : targetCalcBaseList) {
                EZDMsg.copy(calcBase, null, lineMsg.NWXC010003PMsg.no(validCalcBaseCount), null);
                validCalcBaseCount++;
            }
            lineMsg.NWXC010003PMsg.setValidCount(validCalcBaseCount);
        }
        nWXC010001PMsg.NWXC010002PMsg.setValidCount(param.NWXC014002PMsg.getValidCount());
        if (negoFlg == false) {
            return calcBaseList;
        }
        new NWXC010001().execute(nWXC010001PMsg, onBatchType);
        if (S21ApiUtil.isXxMsgId(nWXC010001PMsg)) {
            S21ApiMessageMap pMap = new S21ApiMessageMap(param);
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nWXC010001PMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                pMap.addXxMsgIdWithPrm(msgId, msgPrms);
                pMap.flush();
            }
        }
        for (int i = 0; i < nWXC010001PMsg.NWXC010002PMsg.getValidCount(); i++) {
            lineMsg = nWXC010001PMsg.NWXC010002PMsg.no(i);
            List<NWXC014003PMsg> lineList = new ArrayList<NWXC014003PMsg>();
            List<String> keys = new ArrayList<String>();
            keys.add(lineMsg.trxLineNum.getValue());
            keys.add(lineMsg.trxLineSubNum.getValue());
            keys.add(lineMsg.configCatgCd.getValue());
            for (int j = 0; j < lineMsg.NWXC010003PMsg.getValidCount(); j++) {
                NWXC014003PMsg data = new NWXC014003PMsg();
                EZDMsg.copy(lineMsg.NWXC010003PMsg.no(j), null, data, null);
                lineList.add(data);
            }
            calcBaseList.put(keys, lineList);
            if (S21ApiUtil.isXxMsgId(lineMsg)) {
                NWXC014002PMsg ruleLine = getLine(param, lineMsg.trxLineNum.getValue(), lineMsg.trxLineSubNum.getValue(), lineMsg.configCatgCd.getValue());
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(lineMsg);
                S21ApiMessageMap lineMap = new S21ApiMessageMap(ruleLine);
                for (int k = 0; k < msgList.size(); k++) {
                    S21ApiMessage msg = msgList.get(k);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    lineMap.addXxMsgIdWithPrm(msgId, msgPrms);
                    lineMap.flush();
                }
            }
        }
        return calcBaseList;
    }

    private boolean isFrozen(NWXC014002PMsg line) {
        if (BigDecimal.ZERO.compareTo(line.invQty.getValue()) != 0 || ZYPConstant.FLG_ON_Y.equals(line.xxPrcCloFlg.getValue())) {
            return true;
        }
        return false;
    }
    
    // QC#21390 2017/09/28 Add Start
    private boolean isManual(NWXC014002PMsg line, BigDecimal specCondPrcPk, Map<List<String>, List<NWXC014003PMsg>> calcBaseList) {
        NWXC014003PMsg data = null;
        List<NWXC014003PMsg> targetCalcBaseList = getCalcBaseList(line, calcBaseList);
        for (NWXC014003PMsg calcBase : targetCalcBaseList) {
            if (isEquals(calcBase.specCondPrcPk.getValue(), specCondPrcPk)) {
                data = calcBase;
            }
        }
        if (data == null) {
            return false;
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(data.prcCondManEntryFlg.getValue())) {
                return true;
            }
        }
        return false;
    }
    // QC#21390 2017/09/28 Add End
    // QC#9700  2018/09/03 Add Start
    private void setQuplifiedCalcBase(NWXC014001PMsg param, List<NWXC014001prcRuleWrkBean> qualifiedList) {
        String prcDtlGrpCd = null;
        if (qualifiedList.size() == 0) {
            return;
        }
        for (NWXC014001prcRuleWrkBean bean : qualifiedList) {

            NWXC014002PMsg line = getLine(param, bean.getTrxLineNum(), bean.getTrxLineSubNum(), bean.getConfigCatgCd());
            NWXC014003PMsg calcBase = line.NWXC014003PMsg.no(line.NWXC014003PMsg.getValidCount());
            if(PRC_FUNC_TP.GET_PROMOTION_API.equals(bean.getPrcFuncTpCd())){
                continue;
            }
            if (isFrozen(line)) {
                continue;
            }

            if (PRC_RULE_MOD_TP.CHARGES.equals(bean.getPrcRuleModTpCd())) {
                if (PRC_RULE_ADJ_TP.FUEL_SURCHARGE.equals(bean.getPrcRuleAdjTpCd())) {
                    prcDtlGrpCd = PRC_DTL_GRP.FUEL_SURCHARGE;
                } else if (PRC_RULE_ADJ_TP.HANDLING_FEE.equals(bean.getPrcRuleAdjTpCd())) {
                    prcDtlGrpCd = PRC_DTL_GRP.HANDLING_FEE;
                } else if (PRC_RULE_ADJ_TP.SHIPPING_FEE.equals(bean.getPrcRuleAdjTpCd())) {
                    prcDtlGrpCd = PRC_DTL_GRP.SHIPPING_FEE;
                } else if (PRC_RULE_ADJ_TP.RESTOCKING_FEE.equals(bean.getPrcRuleAdjTpCd())) {
                    prcDtlGrpCd = PRC_DTL_GRP.RESTOCKING_FEE;
                } else {
                    prcDtlGrpCd = PRC_DTL_GRP.FREIGHT;
                }
            } else {
                prcDtlGrpCd = PRC_DTL_GRP.DISCOUNT;
            }

            ZYPEZDItemValueSetter.setValue(calcBase.trxLineNum, line.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(calcBase.trxLineSubNum, line.trxLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(calcBase.configCatgCd, line.configCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(calcBase.prcDtlGrpCd, prcDtlGrpCd);
            ZYPEZDItemValueSetter.setValue(calcBase.prcCatgCd, line.prcCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(calcBase.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(calcBase.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(calcBase.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(calcBase.pkgUomCd, line.pkgUomCd.getValue());
            ZYPEZDItemValueSetter.setValue(calcBase.autoPrcCcyCd, line.ccyCd.getValue());
            if (PRC_RULE_ATTRB.PERCENT.equals(bean.getPrcRuleAttrbCd())) {
                ZYPEZDItemValueSetter.setValue(calcBase.prcCondUnitCd, PRC_COND_UNIT.PCT);
            } else {
                ZYPEZDItemValueSetter.setValue(calcBase.prcCondUnitCd, PRC_COND_UNIT.AMT);
            }
            ZYPEZDItemValueSetter.setValue(calcBase.autoPrcAmtRate, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(calcBase.unitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(calcBase.specCondPrcPk, bean.getSpecCondPrcPk());
            ZYPEZDItemValueSetter.setValue(calcBase.prcRuleApplyFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(calcBase.prcRulePrcdPk, bean.getPrcRulePrcdGrpNum());

            line.NWXC014003PMsg.setValidCount(line.NWXC014003PMsg.getValidCount() + 1);
        }
    }
    private void print(NWXC014001prcRuleWrkBean bean){
        System.out.println("TrxLineNum = " + bean.getTrxLineNum() + " getTrxLineSubNum = " + bean.getTrxLineSubNum() + " SpecCondPrcPk = " + bean.getSpecCondPrcPk());
    }
    // QC#9700  2018/09/03 Add End

    private BigDecimal round(String glblCmpyCd, BigDecimal unitPrice, String dealCcyCd) {
        return unitPrice.setScale(getDealCcyDigit(glblCmpyCd, dealCcyCd), BigDecimal.ROUND_HALF_UP);
    }

    private int getDealCcyDigit(String glblCmpyCd, String dealCcyCd) {
        CCYTMsg ccyTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, dealCcyCd);
        ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);
        if (ccyTMsg != null) {
            return ccyTMsg.aftDeclPntDigitNum.getValueInt();
        }
        return 0;
    }


    private boolean isEquals(BigDecimal num1, BigDecimal num2) {

        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }

        if (num1.compareTo(num2) == 0) {
            return true;
        }

        return false;
    }

    private BigDecimal round(BigDecimal num, int digit) {
        if (num == null) {
            num = BigDecimal.ZERO;
        }
        return num.setScale(digit, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal add(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        return num1.add(num2);
    }

    private BigDecimal subtract(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        return num1.subtract(num2);
    }

    private BigDecimal multiply(String glblCmpyCd, BigDecimal num1, BigDecimal num2, String dealCcyCd) {
        if (num1 == null || num2 == null) {
            return BigDecimal.ZERO;
        }
        int scale = getDealCcyDigit(glblCmpyCd, dealCcyCd);
        return round(num1.multiply(num2), scale);
    }

    private BigDecimal divide(String glblCmpyCd, BigDecimal num1, BigDecimal num2, String dealCcyCd) {
        if (num1 == null || num2 == null) {
            return BigDecimal.ZERO;
        }
        if(BigDecimal.ZERO.compareTo(num2) == 0){
            return BigDecimal.ZERO;
        }
        int scale = getDealCcyDigit(glblCmpyCd, dealCcyCd);
        return num1.divide(num2, scale, BigDecimal.ROUND_HALF_UP);
    }


    // Data Obtain Method
    private DS_ORD_CATGTMsg getDsOrdCatg(String glblCmpyCd, String dsOrdCatgCd) {
        DS_ORD_CATGTMsg inTMsg = new DS_ORD_CATGTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.dsOrdCatgCd.setValue(dsOrdCatgCd);
        DS_ORD_CATGTMsg outTMsg = (DS_ORD_CATGTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private DS_ORD_TPTMsg getDsOrdTp(String glblCmpyCd, String dsOrdTpCd) {
        DS_ORD_TPTMsg inTMsg = new DS_ORD_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.dsOrdTpCd.setValue(dsOrdTpCd);
        DS_ORD_TPTMsg outTMsg = (DS_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private Map<String, String> getDsAcctCust(String glblCmpyCd, String dsAcctCd, String prcBaseDt) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("dsAcctNum", dsAcctCd);
        mapParam.put("prcBaseDt", prcBaseDt);

        return DataCacheForSSM.getInstance().getDsAcctCust(mapParam, ssmBatchClient);
    }

    private CPO_SRC_TPTMsg getCpoSrcTp(String glblCmpyCd, String cpoSrcTpCd) {
        CPO_SRC_TPTMsg inTMsg = new CPO_SRC_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.cpoSrcTpCd.setValue(cpoSrcTpCd);
        CPO_SRC_TPTMsg outTMsg = (CPO_SRC_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private PRC_RULE_TRX_CATGTMsg getPrcRuleTrxCatg(String glblCmpyCd, String prcRuleTrxCatg) {
        PRC_RULE_TRX_CATGTMsg inTMsg = new PRC_RULE_TRX_CATGTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcRuleTrxCatgCd.setValue(prcRuleTrxCatg);
        PRC_RULE_TRX_CATGTMsg outTMsg = (PRC_RULE_TRX_CATGTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private DS_PMT_METHTMsg getDsPmtMeth(String glblCmpyCd, String dsPmtMethCd) {
        DS_PMT_METHTMsg inTMsg = new DS_PMT_METHTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.dsPmtMethCd.setValue(dsPmtMethCd);
        DS_PMT_METHTMsg outTMsg = (DS_PMT_METHTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private SHIP_TO_CUSTTMsg getShipToCust(String glblCmpyCd, String shipToCust) {
        final FindCondition condition = new FindCondition("004");
        condition.addCondition("glblCmpyCd01", glblCmpyCd);
        condition.addCondition("shipToCustCd01", shipToCust);
        SHIP_TO_CUSTTMsgArray tMsgArry = DataCacheForTBLAccessor.getInstance().getShipToCustTMsgArray(condition);

        if (tMsgArry.length() == 0) {
            return null;
        }
        return (SHIP_TO_CUSTTMsg) tMsgArry.get(0);
    }

    private PRC_CATGTMsg getPrcCatg(String glblCmpyCd, String prcCatgCd) {
        PRC_CATGTMsg inTMsg = new PRC_CATGTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcCatgCd.setValue(prcCatgCd);
        PRC_CATGTMsg outTMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private LINE_BIZ_TPTMsg getLineBizTp(String glblCmpyCd, String lineBizTpCd) {
        LINE_BIZ_TPTMsg inTMsg = new LINE_BIZ_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.lineBizTpCd.setValue(lineBizTpCd);
        LINE_BIZ_TPTMsg outTMsg = (LINE_BIZ_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private PRC_CONTRTMsg getPrcContr(String glblCmpyCd, String prcContrNum) {
        final FindCondition condition = new FindCondition("001");
        condition.addCondition("glblCmpyCd01", glblCmpyCd);
        condition.addCondition("prcContrNum01", prcContrNum);
        PRC_CONTRTMsgArray array = DataCacheForTBLAccessor.getInstance().getPrcContrTMsgArray(condition);

        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0);
    }

    private COA_BRTMsg getCoaBr(String glblCmpyCd, String coaBrCd) {
        COA_BRTMsg inTMsg = new COA_BRTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.coaBrCd.setValue(coaBrCd);
        COA_BRTMsg outTMsg = (COA_BRTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private Map<String, String> getMdse(String glblCmpyCd, String mdseCd) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("mdseCd", mdseCd);
        mapParam.put("primPkgUomFlg", ZYPConstant.FLG_ON_Y);

        return DataCacheForSSM.getInstance().getMdse(mapParam, ssmBatchClient);
    }

    private DS_ORD_LINE_CATGTMsg getDsOrdLineCatg(String glblCmpyCd, String dsOrdLineCatgCd) {
        DS_ORD_LINE_CATGTMsg inTMsg = new DS_ORD_LINE_CATGTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.dsOrdLineCatgCd.setValue(dsOrdLineCatgCd);
        DS_ORD_LINE_CATGTMsg outTMsg = (DS_ORD_LINE_CATGTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private RTRN_RSNTMsg getRtrnRsn(String glblCmpyCd, String rtrnRsnCd) {
        RTRN_RSNTMsg inTMsg = new RTRN_RSNTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.rtrnRsnCd.setValue(rtrnRsnCd);
        RTRN_RSNTMsg outTMsg = (RTRN_RSNTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private MDL_NMTMsg getMdlNm(String glblCmpyCd, BigDecimal mdlId) {
        MDL_NMTMsg inTMsg = new MDL_NMTMsg();
        inTMsg.t_GlblCmpyCd.setValue(glblCmpyCd);
        inTMsg.t_MdlId.setValue(mdlId);
        MDL_NMTMsg outTMsg = (MDL_NMTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private SVC_CALL_TPTMsg getSvcCallTp(String glblCmpyCd, String svcCallTpCd) {
        SVC_CALL_TPTMsg inTMsg = new SVC_CALL_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.svcCallTpCd.setValue(svcCallTpCd);
        SVC_CALL_TPTMsg outTMsg = (SVC_CALL_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private STTMsg getSt(String glblCmpyCd, String stCd) {
        STTMsg inTMsg = new STTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.stCd.setValue(stCd);
        STTMsg outTMsg = (STTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private CTRYTMsg getCtry(String glblCmpyCd, String ctryCd) {
        CTRYTMsg inTMsg = new CTRYTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.ctryCd.setValue(ctryCd);
        CTRYTMsg outTMsg = (CTRYTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private SHPG_SVC_LVLTMsg getShpgSvcLvl(String glblCmpyCd, String shpgSvcLvlCd) {
        SHPG_SVC_LVLTMsg inTMsg = new SHPG_SVC_LVLTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.shpgSvcLvlCd.setValue(shpgSvcLvlCd);
        SHPG_SVC_LVLTMsg outTMsg = (SHPG_SVC_LVLTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private FRT_CHRG_TOTMsg getFrtChrgToCd(String glblCmpyCd, String frtChrgToCd) {
        FRT_CHRG_TOTMsg inTMsg = new FRT_CHRG_TOTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.frtChrgToCd.setValue(frtChrgToCd);
        FRT_CHRG_TOTMsg outTMsg = (FRT_CHRG_TOTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private FRT_CHRG_METHTMsg getFrtChrgMeth(String glblCmpyCd, String frtChrgMethCd) {
        FRT_CHRG_METHTMsg inTMsg = new FRT_CHRG_METHTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.frtChrgMethCd.setValue(frtChrgMethCd);
        FRT_CHRG_METHTMsg outTMsg = (FRT_CHRG_METHTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private COA_EXTNTMsg getCoaExtn(String glblCmpyCd, String coaExtnCd) {
        COA_EXTNTMsg inTMsg = new COA_EXTNTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.coaExtnCd.setValue(coaExtnCd);
        COA_EXTNTMsg outTMsg = (COA_EXTNTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private MDSE_TP_VAL_SETTMsg getMdseTpValSet(String glblCmpyCd, String mdseTpCtxTpCd, String coaMdseTpCd) {
        final FindCondition condition = new FindCondition("001");
        condition.addCondition("glblCmpyCd01", glblCmpyCd);
        condition.addCondition("mdseTpCtxTpCd01", mdseTpCtxTpCd);
        condition.addCondition("coaMdseTpCd01", coaMdseTpCd);
        MDSE_TP_VAL_SETTMsgArray array = DataCacheForTBLAccessor.getInstance().getMdseTpValSetTMsgArray(condition);

        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0);
    }

    private ORD_LINE_VAL_SETTMsg getOrdLineValSet(String glblCmpyCd, String ordLineCtxTpCd, String dsOrdLineCatgCd) {
        final FindCondition condition = new FindCondition("001");
        condition.addCondition("glblCmpyCd01", glblCmpyCd);
        condition.addCondition("ordLineCtxTpCd01", ordLineCtxTpCd);
        condition.addCondition("dsOrdLineCatgCd01", dsOrdLineCatgCd);
        ORD_LINE_VAL_SETTMsgArray array = DataCacheForTBLAccessor.getInstance().getOrdLineValSetTMsgArray(condition);

        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0);
    }

    // QC#22371 2017/12/26 Add Start
    private PRC_CALC_EXCL_SWHTMsg getPrcCalcSwh(String glblCmpyCd, String rtlSwhCd){
        PRC_CALC_EXCL_SWHTMsg inTMsg = new PRC_CALC_EXCL_SWHTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.rtlSwhCd.setValue(rtlSwhCd);
        PRC_CALC_EXCL_SWHTMsg outTMsg = (PRC_CALC_EXCL_SWHTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if(outTMsg == null){
            return null;
        }
        return outTMsg;
    }
    // QC#22371 2017/12/26 Add End

    private ORD_CATG_BIZ_CTXTMsg getOrdCatgBizCtx(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {
        ORD_CATG_BIZ_CTXTMsg inTMsg = new ORD_CATG_BIZ_CTXTMsg();
        inTMsg.setSQLID("004");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("ordCatgCtxTpCd01", ORD_CATG_CTX_TP.NEGOTIATION_PRICE_ELIGIBLE);
        inTMsg.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        inTMsg.setConditionValue("dsOrdTpCd01", dsOrdTpCd);
        ORD_CATG_BIZ_CTXTMsgArray array = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getPrcRulePrcdDtlAry(String glblCmpyCd, List<String> list) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("prcRuleHdrPkList", list);
        List<Map<String, Object>> ssmResList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("selectPrcRulePrcd", mapParam);
        if (ssmResList == null || ssmResList.isEmpty()) {
            return null;
        }
        return ssmResList;
    }

    // common method
    private NWXC014002PMsg getLine(NWXC014001PMsg param, String trxLineNum, String trxLineSubNum, String configCatgCd) {
        for (int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++) {
            NWXC014002PMsg line = param.NWXC014002PMsg.no(i);
            if (S21StringUtil.isEquals(line.trxLineNum.getValue(), trxLineNum)
                    && S21StringUtil.isEquals(line.trxLineSubNum.getValue(), trxLineSubNum)
                    && S21StringUtil.isEquals(line.configCatgCd.getValue(), configCatgCd)) {
                return line;
            }
        }
        return null;
    }

    private List<NWXC014003PMsg> getCalcBaseList(NWXC014002PMsg line, Map<List<String>, List<NWXC014003PMsg>> calcBaseList){
        List<NWXC014003PMsg> rtnList = new ArrayList<NWXC014003PMsg>();
        List<String> keys = new ArrayList<String>();
        keys.add(line.trxLineNum.getValue());
        keys.add(line.trxLineSubNum.getValue());
        keys.add(line.configCatgCd.getValue());
        rtnList = calcBaseList.get(keys);
        if(rtnList == null){
            return new ArrayList<NWXC014003PMsg>();
        }
        return rtnList;
    }

    private List<NWXC014002PMsg> getTargetRuleLineList(NWXC014001PMsg param, List<NWXC014001prcRuleWrkBean> ruleList, NWXC014001prcRuleWrkBean bean) {
        List<NWXC014002PMsg> rtnList = new ArrayList<NWXC014002PMsg>();
        for (NWXC014001prcRuleWrkBean listItem : ruleList) {
            if (S21StringUtil.isEquals(listItem.getConfigCatgCd(), bean.getConfigCatgCd()) 
                    && isEquals(listItem.getSpecCondPrcPk(), bean.getSpecCondPrcPk())) {
                NWXC014002PMsg line = getLine(param, listItem.getTrxLineNum(), listItem.getTrxLineSubNum(), listItem.getConfigCatgCd());
                if (line != null) {
                    rtnList.add(line);
                    line = null;
                }
            }
        }
        return rtnList;
    }

    // QC#53586 2019/10/04 Mod Start
    //    private BigDecimal getNetPrice(List<NWXC014003PMsg> targetCalcBaseList) {
    //        BigDecimal rtnVal = BigDecimal.ZERO;
    //        for (NWXC014003PMsg calcBase : targetCalcBaseList) {
    //            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
    //                rtnVal = add(rtnVal, calcBase.unitPrcAmt.getValue());
    //            } else if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())) {
    //                rtnVal = subtract(rtnVal, calcBase.unitPrcAmt.getValue());
    //            }
    //        }
    //        return rtnVal;
    //    }
    //
    //    private BigDecimal getNetAmount(List<NWXC014003PMsg> targetCalcBaseList) {
    //        BigDecimal rtnVal = BigDecimal.ZERO;
    //        for (NWXC014003PMsg calcBase : targetCalcBaseList) {
    //            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
    //                rtnVal = add(rtnVal, calcBase.calcPrcAmtRate.getValue());
    //            } else if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())) {
    //                if (ZYPConstant.FLG_OFF_N.equals(calcBase.prcCondManEntryFlg.getValue())) {
    //                    rtnVal = subtract(rtnVal, calcBase.calcPrcAmtRate.getValue());
    //                }
    //            } else if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(calcBase.prcDtlGrpCd.getValue())) {
    //                if (ZYPConstant.FLG_OFF_N.equals(calcBase.prcCondManEntryFlg.getValue())) {
    //                    rtnVal = subtract(rtnVal, calcBase.calcPrcAmtRate.getValue());
    //                }
    //            }
    //        }
    //        return rtnVal;
    //    }

    private BigDecimal getNetPrice(List<NWXC014003PMsg> targetCalcBaseList, boolean flg) {
        BigDecimal rtnVal = BigDecimal.ZERO;
        for (NWXC014003PMsg calcBase : targetCalcBaseList) {
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                rtnVal = add(rtnVal, calcBase.unitPrcAmt.getValue());
            } else if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())) {
                if (flg) {
                    rtnVal = subtract(rtnVal, calcBase.unitPrcAmt.getValue());
                } else {
                    if (ZYPConstant.FLG_OFF_N.equals(calcBase.prcCondManEntryFlg.getValue())) {
                        rtnVal = subtract(rtnVal, calcBase.unitPrcAmt.getValue());
                    }

                }
            }
        }
        return rtnVal;
    }

    private BigDecimal getNetAmount(List<NWXC014003PMsg> targetCalcBaseList, boolean flg ) {
        BigDecimal rtnVal = BigDecimal.ZERO;
        for (NWXC014003PMsg calcBase : targetCalcBaseList) {
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                rtnVal = add(rtnVal, calcBase.calcPrcAmtRate.getValue());
            } else if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())) {
                if (flg) {
                    rtnVal = subtract(rtnVal, calcBase.calcPrcAmtRate.getValue());
                } else {
                    if (ZYPConstant.FLG_OFF_N.equals(calcBase.prcCondManEntryFlg.getValue())) {
                        rtnVal = subtract(rtnVal, calcBase.calcPrcAmtRate.getValue());
                    }
                }
            } else if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(calcBase.prcDtlGrpCd.getValue())) {
                if (flg) {
                    rtnVal = subtract(rtnVal, calcBase.calcPrcAmtRate.getValue());
                } else {
                    if (ZYPConstant.FLG_OFF_N.equals(calcBase.prcCondManEntryFlg.getValue())) {
                        rtnVal = subtract(rtnVal, calcBase.calcPrcAmtRate.getValue());
                    }

                }
            }
        }
        return rtnVal;
    }
    // QC#53586 2019/10/04 Mod End

    private Map<List<String>, List<NWXC014003PMsg>> copyMap(Map<List<String>, List<NWXC014003PMsg>> calcBaseList){
        Map<List<String>, List<NWXC014003PMsg>> calcBaseListCopy = new HashMap<List<String>, List<NWXC014003PMsg>>();
        List<NWXC014003PMsg> list = null;
        Set<List<String>> keys = calcBaseList.keySet();
        for(List<String> key : keys){
            list = calcBaseList.get(key);
            List<NWXC014003PMsg> listCopy = new ArrayList<NWXC014003PMsg>();
            for(NWXC014003PMsg data : list){
                NWXC014003PMsg calcBase = new NWXC014003PMsg();
                EZDMsg.copy(data, null, calcBase, null);
                listCopy.add(calcBase);
            }
            calcBaseListCopy.put(key, listCopy);
        }
        return calcBaseListCopy;
    }

    /**
     * log
     * @param no String
     * @param lineNum String
     * @param msg String
     */
    public void log(String no, String lineNum, String msg) {
        S21LoggerFactory.getLogger(NWXC014001.class).info("****************************>[" + no + "]"+ " line : " + lineNum + " -> " + msg);
    }

    // S21_NA#11618-17 Add Start
    private Map<String, Object> getSsmParamForHdrBase(NWXC014001PMsg pMsg) {

        Map<String, Object> ssmParamForHdrBase = new HashMap<String, Object>();
        ssmParamForHdrBase.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParamForHdrBase.put("actvFlg", ZYPConstant.FLG_ON_Y);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_01", PRC_GRP_TRGT_ATTRB.ITEM_NUMBER);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_02", PRC_GRP_TRGT_ATTRB.ITEM_TYPE);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_03", PRC_GRP_TRGT_ATTRB.ITEM_CLASSIFICATION);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_04", PRC_GRP_TRGT_ATTRB.MERCHANDISE_TYPE);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_05", PRC_GRP_TRGT_ATTRB.PRODUCT_CODE);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_06", PRC_GRP_TRGT_ATTRB.ITEM_MARKETING_MODEL);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_07", PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_1);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_08", PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_2);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_09", PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_3);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_10", PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_4);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_11", PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_5);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_12", PRC_GRP_TRGT_ATTRB.MODEL_NAME);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_13", PRC_GRP_TRGT_ATTRB.MODEL_SERIES_NAME);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_14", PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_15", PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_16", PRC_GRP_TRGT_ATTRB.SIC_CODE);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_17", PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_18", PRC_GRP_TRGT_ATTRB.CLASSIFICATION);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_19", PRC_GRP_TRGT_ATTRB.CATEGORY);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_20", PRC_GRP_TRGT_ATTRB.DEALER_CODE);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_21", PRC_GRP_TRGT_ATTRB.CSMP_NUMBER);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_22", PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_24", PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_25", PRC_GRP_TRGT_ATTRB.ORDER_CATEGORY);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_26", PRC_GRP_TRGT_ATTRB.ORDER_TYPE);
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_27", PRC_GRP_TRGT_ATTRB.LINE_CATEGORY_TYPE);
        // S21_NA#18789 add start
        ssmParamForHdrBase.put("prcGrpTrgtAttrbCd_28", PRC_GRP_TRGT_ATTRB.CHANNEL);
        // S21_NA#18789 add end
        ssmParamForHdrBase.put("prcGrpOpCd01", PRC_GRP_OP.EQ);
        ssmParamForHdrBase.put("prcGrpOpCd02", PRC_GRP_OP.NOT_EQ);
        ssmParamForHdrBase.put("prcGrpOpCd03", PRC_GRP_OP.BETWEEN);
        ssmParamForHdrBase.put("prcGrpOpCd04", PRC_GRP_OP.LIKE);

        return ssmParamForHdrBase;
    }

    private Map<String, Object> getSsmParamForMaterial(NWXC014001PMsg pMsg) {

        Map<String, Object> ssmParamForMaterial = getSsmParamForHdrBase(pMsg);
        ssmParamForMaterial.put("prcGrpTpCd", PRC_GRP_TP.MATERIAL_GROUP);
        return ssmParamForMaterial;
    }

    // QC#20249 2017/08/09 Add Start
    private Map<String, Object> getSsmParamForMaterialQtyBreak(NWXC014001PMsg pMsg) {

        Map<String, Object> ssmParamForMaterialQtyBreak = getSsmParamForHdrBase(pMsg);
        ssmParamForMaterialQtyBreak.put("prcGrpTpCd", PRC_GRP_TP.MATERIAL_PRICING_GROUP_QTY_BREAKS);
        return ssmParamForMaterialQtyBreak;
    }
    // QC#20249 2017/08/09 Add End

    private Map<String, Object> getSsmParamForTransaction(NWXC014001PMsg pMsg) {

        Map<String, Object> ssmParamForTransaction = getSsmParamForHdrBase(pMsg);
        ssmParamForTransaction.put("prcGrpTpCd", PRC_GRP_TP.ORDER_CATEGORY_OR_REASON);
        ssmParamForTransaction.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
        ssmParamForTransaction.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        return ssmParamForTransaction;
    }

    private Map<String, Object> getSsmParamForCustomerPrc(NWXC014001PMsg pMsg) {

        Map<String, Object> ssmParamForCustomerPrc = getSsmParamForHdrBase(pMsg);
        ssmParamForCustomerPrc.put("prcGrpTpCd", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        ssmParamForCustomerPrc.put("dsAcctNum", pMsg.dsAcctNum.getValue());
        ssmParamForCustomerPrc.put("dsAcctNm", pMsg.dsAcctNm.getValue());
        ssmParamForCustomerPrc.put("dsCustSicCd", pMsg.dsCustSicCd.getValue());
        ssmParamForCustomerPrc.put("locNum", pMsg.locNum.getValue());
        ssmParamForCustomerPrc.put("dsAcctClsCd", pMsg.dsAcctClsCd.getValue());
        ssmParamForCustomerPrc.put("dsAcctTpCd", pMsg.dsAcctTpCd.getValue());
        ssmParamForCustomerPrc.put("dsAcctDlrCd", pMsg.dsAcctDlrCd.getValue());
        // S21_NA#18789 add start
        ssmParamForCustomerPrc.put("coaChCd", pMsg.coaChCd.getValue());
        // S21_NA#18789 add end
        return ssmParamForCustomerPrc;
    }

    private Map<String, Object> getSsmParamForCustomerPrcSH(NWXC014001PMsg pMsg) {

        Map<String, Object> ssmParamForCustomerPrcSH = getSsmParamForHdrBase(pMsg);
        ssmParamForCustomerPrcSH.put("prcGrpTpCd", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        return ssmParamForCustomerPrcSH;
    }

    private Map<String, Object> getSsmParamForCustomerPrcBL(NWXC014001PMsg pMsg) {

        Map<String, Object> ssmParamForCustomerPrcBL = getSsmParamForHdrBase(pMsg);
        ssmParamForCustomerPrcBL.put("prcGrpTpCd", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        return ssmParamForCustomerPrcBL;
    }

    private Map<String, Object> getSsmParamForFrtZone(NWXC014001PMsg pMsg) {

        Map<String, Object> ssmParamForFrtZone = new HashMap<String, Object>();
        ssmParamForFrtZone.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParamForFrtZone.put("prcGrpTpCd", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        ssmParamForFrtZone.put("actvFlg", ZYPConstant.FLG_ON_Y);
        ssmParamForFrtZone.put("lineBizTpCd", pMsg.lineBizTpCd.getValue());
        if (S21StringUtil.isEquals(pMsg.lineBizTpCd.getValue(), LINE_BIZ_TP.ALL)) {
            ssmParamForFrtZone.put("lineBizTpCdAll", pMsg.lineBizTpCd.getValue());
        }
        ssmParamForFrtZone.put("prcGrpTrgtAttrbCd_14", PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER);
        ssmParamForFrtZone.put("prcGrpTrgtAttrbCd_15", PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME);
        ssmParamForFrtZone.put("prcGrpTrgtAttrbCd_16", PRC_GRP_TRGT_ATTRB.SIC_CODE);
        ssmParamForFrtZone.put("prcGrpTrgtAttrbCd_17", PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER);
        ssmParamForFrtZone.put("prcGrpTrgtAttrbCd_18", PRC_GRP_TRGT_ATTRB.CLASSIFICATION);
        ssmParamForFrtZone.put("prcGrpTrgtAttrbCd_19", PRC_GRP_TRGT_ATTRB.CATEGORY);
        ssmParamForFrtZone.put("prcGrpTrgtAttrbCd_20", PRC_GRP_TRGT_ATTRB.DEALER_CODE);
        ssmParamForFrtZone.put("prcGrpTrgtAttrbCd_21", PRC_GRP_TRGT_ATTRB.CSMP_NUMBER);
        ssmParamForFrtZone.put("prcGrpTrgtAttrbCd_22", PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER);
        ssmParamForFrtZone.put("prcGrpTrgtAttrbCd_24", PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER);
        // S21_NA#18789 add start
        ssmParamForFrtZone.put("prcGrpTrgtAttrbCd_28", PRC_GRP_TRGT_ATTRB.CHANNEL);
        // S21_NA#18789 add end
        ssmParamForFrtZone.put("prcGrpOpCd01", PRC_GRP_OP.EQ);
        ssmParamForFrtZone.put("prcGrpOpCd02", PRC_GRP_OP.NOT_EQ);
        ssmParamForFrtZone.put("prcGrpOpCd03", PRC_GRP_OP.BETWEEN);
        ssmParamForFrtZone.put("prcGrpOpCd04", PRC_GRP_OP.LIKE);
        return ssmParamForFrtZone;
    }

    private Map<String, Object> getSsmParamForPrcRule(NWXC014001PMsg pMsg) {

        Map<String, Object> ssmParamForPrcRule = new HashMap<String, Object>();
        ssmParamForPrcRule.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParamForPrcRule.put("prcRuleTrxCatgCd", pMsg.prcRuleTrxCatgCd.getValue());

        if (PROCESS_MODE_AUTO.equals(pMsg.xxModeCd_PM.getValue())) {
            ssmParamForPrcRule.put("applyAutoFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParamForPrcRule.put("applyAutoFlg", ZYPConstant.FLG_OFF_N);
        }
        ssmParamForPrcRule.put("delFlg", ZYPConstant.FLG_OFF_N);
        ssmParamForPrcRule.put("activeFlg", ZYPConstant.FLG_ON_Y);
        ssmParamForPrcRule.put("prcRuleAdjLvlCd", pMsg.xxModeCd_PL.getValue());
        ssmParamForPrcRule.put("prcRuleOpTpAnd", PRC_RULE_OP_TP.AND);
        ssmParamForPrcRule.put("prcRuleOpTpOr", PRC_RULE_OP_TP.OR);
        ssmParamForPrcRule.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
        ssmParamForPrcRule.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        ssmParamForPrcRule.put("lineBizTpCd", pMsg.lineBizTpCd.getValue());
        if (S21StringUtil.isEquals(pMsg.lineBizTpCd.getValue(), LINE_BIZ_TP.ALL)) {
            ssmParamForPrcRule.put("lineBizTpCdAll", pMsg.lineBizTpCd.getValue());
        }
        ssmParamForPrcRule.put("xxTotUnitNetWt", pMsg.xxTotUnitNetWt.getValue().abs());
        ssmParamForPrcRule.put("custIssPoNum", pMsg.custIssPoNum.getValue());
        ssmParamForPrcRule.put("cpoSrcTpCd", pMsg.cpoSrcTpCd.getValue());
        //ssmParamForPrcRule.put("xxTotAmt", pMsg.xxTotAmt.getValue()); // QC#22241 2017/11/02 Del
        ssmParamForPrcRule.put("dsPmtMethCd", pMsg.dsPmtMethCd.getValue());
        ssmParamForPrcRule.put("orgRqstDelyDt", pMsg.orgRqstDelyDt.getValue());
        ssmParamForPrcRule.put("spclHdlgTpCd", pMsg.spclHdlgTpCd.getValue());
        // QC#29316 2018/11/29 Add Start
        // ssmParamForPrcRule.put("totQty", pMsg.totQty.getValue());
        ssmParamForPrcRule.put("totQty", abs(pMsg.totQty.getValue()));
        // QC#29316 2018/11/29 Add End
        ssmParamForPrcRule.put("dsAcctNum", pMsg.dsAcctNum.getValue());
        ssmParamForPrcRule.put("coaChCd", pMsg.coaChCd.getValue());
        ssmParamForPrcRule.put("dsAcctClsCd", pMsg.dsAcctClsCd.getValue());

        // Set PRC_RULE_ATTRB Param
        for (String ruleAttrbCd : getRuleAttrbCdList(pMsg)) {
            if (PRC_RULE_ATTRB.CUSTOMER_CHANNEL_SHIP_TO.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb01", PRC_RULE_ATTRB.CUSTOMER_CHANNEL_SHIP_TO);
            } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb02", PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO);
            } else if (PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb03", PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO);
            } else if (PRC_RULE_ATTRB.CSMP_NUM.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb04", PRC_RULE_ATTRB.CSMP_NUM);
            } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb05", PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP);
            } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb06", PRC_RULE_ATTRB.PROD_CTRL_1_BU);
            } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb07", PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES);
            } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb08", PRC_RULE_ATTRB.MDSE_TYPE);
            } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb09", PRC_RULE_ATTRB.PRODUCT_CODE);
            } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb10", PRC_RULE_ATTRB.ITEM_CODE);
            } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb11", PRC_RULE_ATTRB.ORDER_CATEGORY);
            } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb12", PRC_RULE_ATTRB.ORDER_REASON);
            } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb13", PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS);
            } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb14", PRC_RULE_ATTRB.TRANSACTION_GROUP);
            } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb15", PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT);
            } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb16", PRC_RULE_ATTRB.BILL_TO_ACCT_NUM);
            } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb17", PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL);
            } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb18", PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION);
            } else if (PRC_RULE_ATTRB.BRANCH.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb19", PRC_RULE_ATTRB.BRANCH);
            } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb20", PRC_RULE_ATTRB.CALL_TYPE);
            } else if (PRC_RULE_ATTRB.CALL_DATE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb21", PRC_RULE_ATTRB.CALL_DATE);
            } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb22", PRC_RULE_ATTRB.CUSTOMER_PO);
            } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb23", PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO);
            } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb24", PRC_RULE_ATTRB.LINE_AMOUNT);
            } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb25", PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE);
            } else if (PRC_RULE_ATTRB.LINE_QTY.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb26", PRC_RULE_ATTRB.LINE_QTY);
            } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb27", PRC_RULE_ATTRB.MARKETING_MODEL_NAME);
            } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb28", PRC_RULE_ATTRB.MODEL_SEGMENT);
            } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb29", PRC_RULE_ATTRB.ORDER_SOURCE);
            } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb30", PRC_RULE_ATTRB.ORDER_SUBTOTAL);
            } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb31", PRC_RULE_ATTRB.PAYMENT_TYPE);
            } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb32", PRC_RULE_ATTRB.PRICE_LIST);
            } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb33", PRC_RULE_ATTRB.PRICING_DATE);
            } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb34", PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT);
            } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb35", PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP);
            } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb36", PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE);
            } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb37", PRC_RULE_ATTRB.REQUEST_DATE);
            } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb38", PRC_RULE_ATTRB.RETURN_REASON_CODE);
            } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb39", PRC_RULE_ATTRB.SERVICE_LEVEL);
            } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb40", PRC_RULE_ATTRB.SERVICE_MODEL);
            } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb41", PRC_RULE_ATTRB.SERVICE_ZONE);
            } else if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb42", PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION);
            } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb44", PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE);
            } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb45", PRC_RULE_ATTRB.TOTAL_QRY);
            } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb46", PRC_RULE_ATTRB.BUSINESS_UNIT);
            } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb48", PRC_RULE_ATTRB.FREIGHT_TERM);
            } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb49", PRC_RULE_ATTRB.FREIGHT_ZONE);
            } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb53", PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO);
            } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb54", PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM);
            } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb55", PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL);
            } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(ruleAttrbCd)) {
                ssmParamForPrcRule.put("prcRuleAttrb56", PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION);
            } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(ruleAttrbCd)) { // QC#20249 2017/08/09 Add
                ssmParamForPrcRule.put("prcRuleAttrb57", PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK);
            } else if (PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(ruleAttrbCd)) {             // QC#20249 2017/08/09 Add
                ssmParamForPrcRule.put("prcRuleAttrb58", PRC_RULE_ATTRB.LINE_QTY_QTYBREAK);
            }
        }

        return ssmParamForPrcRule;
    }

    @SuppressWarnings("unchecked")
    private List<String> getRuleAttrbCdList(NWXC014001PMsg pMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("prcRuleTrxCatgCd", pMsg.prcRuleTrxCatgCd.getValue());
        return (List<String>) ssmBatchClient.queryObjectList("getRuleAttrbCdList", ssmParam);
    }
    // QC#29316 2018/11/29 Add Start
    private BigDecimal abs(BigDecimal val) {
        if (ZYPCommonFunc.hasValue(val)) {
            val = val.abs();
        }
        return val;
    }
    // QC#29316 2018/11/29 Add End

    // START 2023/10/26 T.Fukuta [CSA-QC#61619,ADD]
    /**
     * Call Get Qty Based Fee API
     * @param param NWXC014001PMsg
     * @param bean NWXC014001prcRuleWrkBean
     * @param ruleList List&lt;NWXC014001prcRuleWrkBean&gt;
     * @param calcBaseList Map&lt;List&lt;String&gt;, List&lt;NWXC014003PMsg&gt;&gt;
     */
    private Map<List<String>, List<NWXC014003PMsg>> callGetQtyBasedFeeApi(
            NWXC014001PMsg param,
            NWXC014001prcRuleWrkBean bean,
            List<NWXC014001prcRuleWrkBean> ruleList,
            Map<List<String>, List<NWXC014003PMsg>> calcBaseList,
            final ONBATCH_TYPE onBatchType) {

        int validCount = 0;
        int validCalcBaseCount = 0;

        NWXC013001PMsg pmsg = new NWXC013001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.prcBaseDt, param.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.lineBizTpCd, param.lineBizTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.prcRulePrcdPk, bean.getPrcRulePrcdGrpNum());
        ZYPEZDItemValueSetter.setValue(pmsg.prcRuleHdrPk, bean.getPrcRuleHdrPk());
        NWXC014002PMsg line = getLine(param, bean.getTrxLineNum(), bean.getTrxLineSubNum(), bean.getConfigCatgCd());
        List<NWXC014002PMsg> targetLineList = getTargetRuleLineList(param, ruleList, bean);

        for (NWXC014002PMsg ruleline : targetLineList) {
            NWXC013002PMsg qtyBasedLine = pmsg.NWXC013002PMsg.no(validCount);
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.trxLineNum, ruleline.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.trxLineSubNum, ruleline.trxLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.configCatgCd, ruleline.configCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.prcBaseDt, ruleline.prcBaseDt.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.shipToCustCd, ruleline.shipToCustCd.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.dsAcctNum, ruleline.dsAcctNum_SH.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.dsAcctNm, ruleline.dsAcctNm_SH.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.dsCustSicCd, ruleline.dsCustSicCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.locNum, ruleline.locNum_SH.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.dsAcctClsCd, ruleline.dsAcctClsCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.dsAcctTpCd, ruleline.dsAcctTpCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.dsAcctDlrCd, ruleline.dsAcctDlrCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.dsAcctGrpCd, ruleline.dsAcctGrpCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.coaChCd, ruleline.coaChCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.csmpNum, ruleline.csmpNum.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.dlrRefNum, ruleline.dlrRefNum.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.prcContrNum, ruleline.prcContrNum.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.frtCondCd, ruleline.frtCondCd.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.stCd, ruleline.stCd.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.ctryCd, ruleline.ctryCd.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.postCd, ruleline.postCd.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.shpgSvcLvlCd, ruleline.shpgSvcLvlCd.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.frtChrgToCd, ruleline.frtChrgToCd.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.frtChrgMethCd, ruleline.frtChrgMethCd.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.unitNetWt, ruleline.unitNetWt.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.ordQty, ruleline.ordQty.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.invQty, ruleline.invQty.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.xxPrcCloFlg, ruleline.xxPrcCloFlg.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.specCondPrcPk, bean.getSpecCondPrcPk());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.ccyCd, line.ccyCd.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.mdseCd, ruleline.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(qtyBasedLine.ordTakeMdseCd, ruleline.ordTakeMdseCd.getValue());
            List<NWXC014003PMsg> targetCalcBaseList = getCalcBaseList(ruleline, calcBaseList);
            validCalcBaseCount = 0;
            for (NWXC014003PMsg calcBase : targetCalcBaseList) {
                EZDMsg.copy(calcBase, null, qtyBasedLine.NWXC013003PMsg.no(validCalcBaseCount), null);
                validCalcBaseCount++;
            }
            qtyBasedLine.NWXC013003PMsg.setValidCount(validCalcBaseCount);
            validCount++;
            pmsg.NWXC013002PMsg.setValidCount(validCount);
        }

        new NWXC013001().execute(pmsg, onBatchType);

        if (S21ApiUtil.isXxMsgId(pmsg)) {
            S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pmsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                lineMap.addXxMsgIdWithPrm(msgId, msgPrms);
                lineMap.flush();
            }
        }

        for (int i = 0; i < pmsg.NWXC013002PMsg.getValidCount(); i++) {
            NWXC013002PMsg qtyBasedLine = pmsg.NWXC013002PMsg.no(i);
            List<NWXC014003PMsg> lineList = new ArrayList<NWXC014003PMsg>();
            List<String> keys = new ArrayList<String>();
            keys.add(qtyBasedLine.trxLineNum.getValue());
            keys.add(qtyBasedLine.trxLineSubNum.getValue());
            keys.add(qtyBasedLine.configCatgCd.getValue());
            for (int j = 0; j < qtyBasedLine.NWXC013003PMsg.getValidCount(); j++) {
                NWXC014003PMsg data = new NWXC014003PMsg();
                EZDMsg.copy(qtyBasedLine.NWXC013003PMsg.no(j), null, data, null);
                lineList.add(data);
            }
            calcBaseList.put(keys, lineList);
            if (S21ApiUtil.isXxMsgId(qtyBasedLine)) {
                NWXC014002PMsg ruleLine = getLine(param, qtyBasedLine.trxLineNum.getValue(), qtyBasedLine.trxLineSubNum.getValue(), qtyBasedLine.configCatgCd.getValue());
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(qtyBasedLine);
                S21ApiMessageMap lineMap = new S21ApiMessageMap(ruleLine);
                for (int k = 0; k < msgList.size(); k++) {
                    S21ApiMessage msg = msgList.get(k);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    lineMap.addXxMsgIdWithPrm(msgId, msgPrms);
                    lineMap.flush();
                }
            }
        }

        return calcBaseList;
    }

    /**
     * Reconstrct rule list for Qty Based Fee API
     * @param param NWXC014001PMsg
     * @param ruleList List&lt;NWXC014001prcRuleWrkBean&gt;
     */
    private void reconstructRuleListForQtyBasedFeeApi(
        NWXC014001PMsg param,
        List<NWXC014001prcRuleWrkBean> ruleList) {

        NWXC014001prcRuleWrkBean bean = null;
        for (NWXC014001prcRuleWrkBean rule : ruleList) {
            String prcFmlaTpCd = rule.getPrcFmlaTpCd();
            String prcFuncTpCd = rule.getPrcFuncTpCd();
            if (PRC_FMLA_TP.CUSTOM_PRICE.equals(prcFmlaTpCd) && PRC_FUNC_TP.GET_QTY_BASED_FEE_API.equals(prcFuncTpCd)) {
                bean = rule;
                break;
            }
        }
        if (bean == null) {
            return;
        }

        BigDecimal specCondPrcPk = bean.getSpecCondPrcPk();

        // Extract non-target rule entries
        List<NWXC014001prcRuleWrkBean> reRuleList = new ArrayList<NWXC014001prcRuleWrkBean>();
        for (NWXC014001prcRuleWrkBean ruleEntry : ruleList) {
            if (!specCondPrcPk.equals(ruleEntry.getSpecCondPrcPk())) {
                reRuleList.add(ruleEntry);
            }
        }

        // Constract and Add all lines
        for (int i = 0; i < param.NWXC014002PMsg.getValidCount(); i++) {
            NWXC014002PMsg line = param.NWXC014002PMsg.no(i);
            NWXC014001prcRuleWrkBean ruleEntry = new NWXC014001prcRuleWrkBean();
            ruleEntry.setTrxLineNum(line.trxLineNum.getValue());
            ruleEntry.setTrxLineSubNum(line.trxLineSubNum.getValue());
            ruleEntry.setConfigCatgCd(line.configCatgCd.getValue());
            ruleEntry.setPrcRuleCatgCd(bean.getPrcRuleCatgCd());
            ruleEntry.setPrcRulePrcdGrpNum(bean.getPrcRulePrcdGrpNum());
            ruleEntry.setPrcRulePrcdSqNum(bean.getPrcRulePrcdSqNum());
            ruleEntry.setPrcPrcdActTpCd(bean.getPrcPrcdActTpCd());
            ruleEntry.setPrcRuleHdrPk(bean.getPrcRuleHdrPk());
            ruleEntry.setPrcRuleDtlPk(bean.getPrcRuleDtlPk());
            ruleEntry.setPrcRuleCondGrpCd(bean.getPrcRuleCondGrpCd());
            ruleEntry.setPrcRuleDtlChrgNm(bean.getPrcRuleDtlChrgNm());
            ruleEntry.setPrcRuleModTpCd(bean.getPrcRuleModTpCd());
            ruleEntry.setPrcRuleAdjTpCd(bean.getPrcRuleAdjTpCd());
            ruleEntry.setPrcRuleAdjLvlCd(bean.getPrcRuleAdjLvlCd());
            ruleEntry.setPrcFmlaPk(bean.getPrcFmlaPk());
            ruleEntry.setPrcRuleDtlRate(bean.getPrcRuleDtlRate());
            ruleEntry.setPrcRuleDtlTxt(bean.getPrcRuleDtlTxt());
            ruleEntry.setPrcRuleAttrbCd(bean.getPrcRuleAttrbCd());
            ruleEntry.setSpecCondPrcPk(bean.getSpecCondPrcPk());
            ruleEntry.setPrcFmlaTpCd(bean.getPrcFmlaTpCd());
            ruleEntry.setPrcFmlaNum(bean.getPrcFmlaNum());
            ruleEntry.setPrcFuncTpCd(bean.getPrcFuncTpCd());
            ruleEntry.setUnitPrcAmt(bean.getUnitPrcAmt());
            ruleEntry.setCalcPrcAmt(bean.getCalcPrcAmt());
            ruleEntry.setDefRulePrcdNum(bean.getDefRulePrcdNum());

            reRuleList.add(ruleEntry);
        }

        ruleList.clear();
        ruleList.addAll(reRuleList);
    }
    // END 2023/10/26 T.Fukuta [CSA-QC#61619,ADD]
}
