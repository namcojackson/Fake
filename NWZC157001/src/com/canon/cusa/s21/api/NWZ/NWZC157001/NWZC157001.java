/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC157001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.CCYTMsg;
import business.db.COA_BRTMsg;
import business.db.COA_EXTNTMsg;
import business.db.CPO_SRC_TPTMsg;
import business.db.CTRYTMsg;
import business.db.DS_ORD_CATGTMsg;
import business.db.DS_ORD_LINE_CATGTMsg;
import business.db.DS_PMT_METHTMsg;
import business.db.FRT_CONDTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDSE_TP_VAL_SETTMsg;
import business.db.MDSE_TP_VAL_SETTMsgArray;
import business.db.MTR_LBTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.ORD_LINE_VAL_SETTMsg;
import business.db.ORD_LINE_VAL_SETTMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.PRC_COND_TPTMsg;
import business.db.PRC_CONTRTMsg;
import business.db.PRC_CONTRTMsgArray;
import business.db.PRC_CTX_TPTMsg;
import business.db.PRC_DTL_GRPTMsg;
import business.db.PRC_DTL_GRPTMsgArray;
import business.db.PRC_LIST_BANDTMsg;
import business.db.PRC_MTR_PKGTMsg;
import business.db.PRC_RATE_TPTMsg;
import business.db.PRC_SVC_CONTR_TPTMsg;
import business.db.PRC_SVC_PLN_TPTMsg;
import business.db.PRC_SVC_TIER_TPTMsg;
import business.db.PRC_SVC_ZONETMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTRN_RSNTMsg;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.SPCL_HDLG_TPTMsg;
import business.db.SPEC_COND_PRCTMsgArray;
import business.db.STTMsg;
import business.db.SVC_CALL_TPTMsg;
import business.parts.NWXC010001PMsg;
import business.parts.NWXC014001PMsg;
import business.parts.NWXC014002PMsg;
import business.parts.NWXC014004PMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateInputLinePMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC170001PMsg;

import com.canon.cusa.s21.api.NWX.NWXC014001.NWXC014001;
import com.canon.cusa.s21.api.NWZ.NWZC036101.NWZC036101;
import com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.cache.DataCacheForSSM;
import com.canon.cusa.s21.api.NWZ.NWZC157001.cache.DataCacheForTBLAccessor;
import com.canon.cusa.s21.api.NWZ.NWZC157001.cache.FindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC170001.NWZC170001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LEASE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_MOD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TERM_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Pricing API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/16   Fujitsu         Y.Kanefusa      Create          N/A
 * 2015/11/19   Fujitsu         S.Takami        Update          QC#854
 * 2015/12/04   Fujitsu         Y.Kanefusa      Update          QC#1364
 * 2015/12/09   Fujitsu         Y.Kanefusa      Update          QC#1713
 * 2015/12/15   Fujitsu         Y.Kanefusa      Update          QC#1995
 * 2015/12/21   Fujitsu         Y.Kanefusa      Update          QC#2252
 * 2016/01/04   Fujitsu         Y.Kanefusa      Update          QC#2555
 * 2016/01/27   Fujitsu         Y.Kanefusa      Update          QC#3448
 * 2016/02/15   Fujitsu         Y.Kanefusa      Update          QC#4222
 * 2016/02/16   Fujitsu         Y.Kanefusa      Update          QC#4224
 * 2016/03/18   Fujitsu         Y.Kanefusa      Update          QC#5624
 * 2016/03/25   Fujitsu         Y.Kanefusa      Update          QC#5387
 * 2016/03/31   Fujitsu         Y.Kanefusa      Update          QC#6145
 * 2016/05/11   Fujitsu         Y.Kanefusa      Update          QC#7395
 * 2016/06/10   Fujitsu         Y.Kanefusa      Update          QC#9482
 * 2016/06/20   Fujitsu         T.Yoshida       Update          QC#10321
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          QC#9437
 * 2016/06/24   Fujitsu         Y.Kanefusa      Update          QC#10678
 * 2016/06/28   Fujitsu         Y.Kanefusa      Update          QC#10959
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          QC#9694
 * 2016/07/29   Fujitsu         Y.Kanefusa      Update          QC#10372
 * 2016/08/01   Hitachi         Y.Takeno        Update          QC#9192
 * 2016/08/23   Fujitsu         Y.Kanefusa      Update          QC#12926
 * 2016/08/31   Fujitsu         Y.Kanefusa      Update          QC#13601
 * 2016/09/14   Fujitsu         Y.Kanefusa      Update          QC#14256
 * 2016/09/23   Fujitsu         T.Yoshida       Update          QC#11618
 * 2016/10/11   Fujitsu         Y.Kanefusa      Update          QC#14739
 * 2016/10/12   Fujitsu         Y.Kanefusa      Update          QC#13900
 * 2016/10/21   Fujitsu         M.Ohno          Update          QC#13762
 * 2016/10/21   Fujitsu         T.Ishii         Update          S21_NA#16027
 * 2016/11/25   Fujitsu         R.Nakamura      Update          QC#15438
 * 2017/01/16   Fujitsu         M.Ohno          Update          QC#17037
 * 2017/02/08   Fujitsu         Y.Kanefusa      Update          S21_NA#17505
 * 2017/05/30   Fujitsu         M.Yamada        Update          QC#18663
 * 2017/07/18   Fujitsu         Y.Kanefusa      Update          S21_NA#19945
 * 2017/07/04   Fujitsu         Y.Kanefusa      Update          S21_NA#19244
 * 2017/08/16   Fujitsu         M.Ohno          Update          QC#18789(L3)
 * 2017/09/16   Fujitsu         Y.Kanefusa      Update          S21_NA#21106
 * 2017/10/17   Fujitsu         Y.Kanefusa      Update          S21_NA#21814
 * 2017/10/20   Fujitsu         Y.Kanefusa      Update          S21_NA#21690
 * 2017/12/26   Fujitsu         Y.Kanefusa      Update          S21_NA#22371
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/04/24   Fujitsu         H.Tomimatsu     Update          S21_NA#22569
 * 2018/05/21   Fujitsu         Y.Kanefusa      Update          S21_NA#21841
 * 2018/08/02   Fujitsu         M.Ohno          Update          S21_NA#26665
 * 2018/08/03   Fujitsu         Y.Kanefusa      Update          S21_NA#27479
 * 2018/08/21   Fujitsu         Y.Kanefusa      Update          S21_NA#27520
 * 2018/08/25   Fujitsu         M.Yamada        Update          S21_NA#27883
 * 2018/08/27   Fujitsu         Y.Kanefusa      Update          S21_NA#27792
 * 2018/08/29   Fujitsu         Y.Kanefusa      Update          S21_NA#28013
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * 2018/11/09   Fujitsu         Y.Kanefusa      Update          S21_NA#28995
 * 2018/12/28   Fujitsu         Y.Kanefusa      Update          S21_NA#29565
 * 2019/02/22   Fujitsu         Mz.Takahashi    Update          QC#30437
 * 2019/05/15   Fujitsu         Y.Kanefusa      Update          S21_NA#50130
 * 2019/05/28   Fujitsu         Y.Kanefusa      Update          S21_NA#50548
 * </pre>
 */
public class NWZC157001 extends S21ApiCommonBase {

    /** GET_PRICE_LIST */
    public static final String GET_PRICE_LIST = "01";

    /** GET_BASE_PRICE */
    public static final String GET_BASE_PRICE = "02";

    /** GET_LINE_PRICE */
    public static final String GET_LINE_PRICE = "03";

    /** GET_ORDER_ALL */
    public static final String GET_ORDER_ALL = "04";

    /** RECALC */
    public static final String RECALC = "05";

    /** RECALC */
    public static final String MANUAL_PRICE = "06";

    /** PROGRAM_ID */
    private static final String PROGRAM_ID = "NWZC157001";

    /** Data Global Company Code is not entered. */
    private static final String NWZM0163E = "NWZM0163E";

    /** Process mode is not set. */
    private static final String NWZM0976E = "NWZM0976E";

    /** Invalid value is set for process mode. */
    private static final String NWZM0977E = "NWZM0977E";

    /** Price Based Date of the parameter is not set. */
    private static final String NWZM1155E = "NWZM1155E";

    /** The parameter's "Line Business Type Code" is not set. */
    private static final String NWZM1783E = "NWZM1783E";

    /** "Sold To Customer Code" is required. */
    private static final String NWZM1403E = "NWZM1403E";

    /** "Tax Flag" is required. */
    private static final String NWZM0353E = "NWZM0353E";

    /** The parameter's "Transaction Line Number" is not set. */
    private static final String NWZM0803E = "NWZM0803E";

    /** The parameter's "Transaction Line Sub Number" is not set. */
    private static final String NWZM0804E = "NWZM0804E";

    /** "Bill To Customer Code" for the entered parameter is not set. */
    private static final String NWZM0988E = "NWZM0988E";

    /** "Ship To Customer Code" for the entered parameter is not set. */
    private static final String NWZM1000E = "NWZM1000E";

    /** Ship To Customer Account Code is required. */
    private static final String NWZM1379E = "NWZM1379E";

    /** Bill To Customer Account Code is required. */
    private static final String NWZM1377E = "NWZM1377E";

    /** "Ship To Country Code" is not set. */
    private static final String NWZM0515E = "NWZM0515E";

    /** "Merchandise Code" for the entered parameter is not set. */
    private static final String NWZM0996E = "NWZM0996E";

    /** Unit of Measure Code of the parameter is not set. */
    public static final String NWZM1168E = "NWZM1168E";

    /** "Order Quantity" is required. */
    public static final String NWZM0046E = "NWZM0046E";

    /** "Data Global Company Code" does not exist in the Master. */
    private static final String NWZM0650E = "NWZM0650E";

    /** The entered "DS Order Category Code" does not exist in the Master. */
    private static final String NWZM1415E = "NWZM1415E";

    /** The entered "DS Order Type Code" does not exist in the Master. */
    private static final String NWZM1815E = "NWZM1815E";

    /** Sell To Customer Code does not exist in master. */
    private static final String NWZM1133E = "NWZM1133E";

    /** The entered Branch Code does not exist in Master. */
    private static final String NWZM0890E = "NWZM0890E";

    /** The "CPO Source Type Code" you entered cannot be found in the master. */
    private static final String NWZM0110E = "NWZM0110E";

    /** Entered 'Payment Method Code' does not exist in the Master.*/
    private static final String NWAM0204E = "NWAM0204E";

    /** The entered "Special Handling Type Code" does not exist in the Master. */
    private static final String NWZM1428E = "NWZM1428E";

    /** The entered "Bill to Location Code" does not exist in the Master. */
    private static final String NWZM1444E = "NWZM1444E";

    /** The entered "Ship to Location Code" does not exist in the Master. */
    private static final String NWZM1445E = "NWZM1445E";

    /** The entered "Bill to Account Code" does not exist in the Master. */
    private static final String NWZM1416E = "NWZM1416E";

    /** The entered "Ship to Account Code" does not exist in the Master. */
    private static final String NWZM1417E = "NWZM1417E";

    /** The entered "Price Category Code" does not exist in the Master. */
    private static final String NWZM1419E = "NWZM1419E";

    /** "Merchandise Code" does not exist in the Master. */
    private static final String NWZM0293E = "NWZM0293E";

    /** The entered "Model ID" does not exist in the Master. */
    private static final String NWZM1443E = "NWZM1443E";

    /** The entered "Ship to Country Code" does not exist in the Master. */
    private static final String NWZM1448E = "NWZM1448E";

    /** The entered "Ship to State Code" does not exist in the Master. */
    private static final String NWZM1446E = "NWZM1446E";

    /** "Ship To City Address" is not set. */
    private static final String NWZM0512E = "NWZM0512E";

    /** "Ship to State Code" is not entered. */
    private static final String NWZM0331E = "NWZM0331E";

    /** The entered "Freight Condition Code" does not exist in the Master. */
    private static final String NWZM1426E = "NWZM1426E";

    /** The {@} parameter's "{@}" is not set. */
    private static final String NWZM1325E = "NWZM1325E";

    /** Price List could not be obtained. */
    private static final String NWZM0422E = "NWZM0422E";

    /** Parameter {@}={@} does not exists in {@}. */
    private static final String NWZM1326E = "NWZM1326E";

    /** NWZM1328W */
    private static final String NWZM1328E = "NWZM1328E";

    /** NWZM1329W */
    private static final String NWZM1329E = "NWZM1329E";

    /** All merchandise code in same config# {@} are not entried. */
    private static final String NWZM1334W = "NWZM1334W";

    /** The each price amount has decimal fraction. */
    private static final String NWZM1533E = "NWZM1533E";

    /** There is leftover amount in {@}. Please confirm it */
    private static final String NWZM1355W = "NWZM1355W";

    /** Base Price could not be obtained. Please input Price Config# . */
    private static final String NWZM1916W = "NWZM1916W";

    /** When Lease Type in Price List Master {@} is "Fixed", Please enter the Lease Term value.  */
    private static final String NWZM1932E = "NWZM1932E";

    /** It failed to get Tax information from Vertex */
    private static final String NWZM2007W = "NWZM2007W";

    /** DS_ORD_LINE_DRCTN_CD_I */
    private static final String DS_ORD_LINE_DRCTN_CD_I = "I";

    /** DS_ORD_LINE_DRCTN_CD_O */
    private static final String DS_ORD_LINE_DRCTN_CD_O = "O";

    /** NEGO_PRC_ELIGIBLE */
    private static final String NEGO_PRC_ELIGIBLE = "NEGO_PRC_ELIGIBLE";

    // DEL START 2015/12/09 #1713
    // /** SVC_PRC_ITEMS */
    // private static final String SVC_PRC_ITEMS = "SVC_PRC_ITEMS";
    // DEL END 2015/12/09 #1713

    /** VAR_CHAR_CONST : TAX_FRT_ITEM_CD */
    private static final String TAX_FRT_ITEM_CD = "TAX_FRT_ITEM_CD";

    /** VAR_CHAR_CONST : TAX_FRT_ITEM_CD */
    private static final String DEFAULT_TAX_TRX_TP = "DEFAULT_TAX_TRX_TP";

    /** SCALE DB */
    private static final int SCALE_DB = 4;

    /** SCALE AMT */
    private static final int SCALE_AMT = 2;

    /** RATE_DIGIT */
    private static final int RATE_DIGIT = 6;

    /** MINUS */
    private static final BigDecimal MINUS = new BigDecimal(-1);

    /** PERCENT */
    private static final BigDecimal PERCENT = new BigDecimal(100);

    /** PRC_TERM_AOT_MAX_VALUE */
    private static final BigDecimal PRC_TERM_AOT_MAX_VALUE = new BigDecimal(99999999);

    /** isExpt */
    private boolean isExpt = false;

    /** dsOrdLineDrctnCd */
    private String dsOrdLineDrctnCd = null;

    /** prcRuleTrxCatgCd */
    private String prcRuleTrxCatgCd = null;

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmBatchClient;

    /** Negotiation API Interface*/
    private NWXC010001PMsg nWXC010001PMsg = new NWXC010001PMsg();

    /** Negotiation Adopt Flag */
    boolean negoFlg = false;

    /** Constructor */
    public NWZC157001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NWZC157001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NWZC157001PMsg param, final ONBATCH_TYPE onBatchType) {

        boolean errFlg = false;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        List<NWZC157003PMsg> manualList = new ArrayList<NWZC157003PMsg>();

        if (paramCheck(msgMap)) {
            return;
        }
        if (paramDBCheck(msgMap)) {
            return;
        }
        if (GET_PRICE_LIST.equals(param.xxModeCd.getValue())) {
            hdrPriceListDeriversion(msgMap);
        } else if (GET_BASE_PRICE.equals(param.xxModeCd.getValue())) {
            manualList = deleteCalcBase(msgMap);
            NWZC157002PMsg line = null;
            for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
                line = param.NWZC157002PMsg.no(i);
                if (linePriceListDeriversion(msgMap, line)) {
                    errFlg = true;
                } else {
                    if (basePriceDeriversion(msgMap, line, onBatchType)) {
                        errFlg = true;
                    }
                }
            }
            if (!errFlg) {  // QC#22965 2018/04/11 Add
                divideCalcBase(msgMap, manualList, ZYPConstant.FLG_OFF_N); // QC#27520 2018/08/21 Add
                divideCalcBase(msgMap, manualList, ZYPConstant.FLG_ON_Y);
            }
            summarize(msgMap, onBatchType);
        } else if (GET_LINE_PRICE.equals(param.xxModeCd.getValue())) {
            manualList = deleteCalcBase(msgMap);
            NWZC157002PMsg line = null;
            for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
                line = param.NWZC157002PMsg.no(i);
                if (linePriceListDeriversion(msgMap, line)) {
                    errFlg = true;
                } else {
                    if (basePriceDeriversion(msgMap, line, onBatchType)) {
                        errFlg = true;
                    }
                }
            }
            // QC#27520 2018/08/21 Del Start
            //if (!errFlg) { // QC#22965 2018/04/11 Add
            //}
            // QC#27520 2018/08/21 Del End
            if (!errFlg) {
                rulePriceDeriversion(msgMap, onBatchType);
            }
            if (!errFlg) { // QC#22965 2018/04/11 Add
                divideCalcBase(msgMap, manualList, ZYPConstant.FLG_OFF_N); // QC#27520 2018/08/21 Add
                divideCalcBase(msgMap, manualList, ZYPConstant.FLG_ON_Y);
            }
            summarize(msgMap, onBatchType);
        } else if (GET_ORDER_ALL.equals(param.xxModeCd.getValue())) {
            manualList = deleteCalcBase(msgMap);
            //deleteCalcBase(msgMap); // QC#28995 2018/11/09 Del
            NWZC157002PMsg line = null;
            for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
                line = param.NWZC157002PMsg.no(i);
                if (linePriceListDeriversion(msgMap, line)) {
                    errFlg = true;
                } else {
                    if (basePriceDeriversion(msgMap, line, onBatchType)) {
                        errFlg = true;
                    }
                }
            }
            // QC#27520 2018/08/21 Del Start
            //if (!errFlg) { // QC#22965 2018/04/11 Add
            //}
            // QC#27520 2018/08/21 Del End
            if (!errFlg) {
                rulePriceDeriversion(msgMap, onBatchType);
            }
            if (!errFlg) { // QC#22965 2018/04/11 Add
                divideCalcBase(msgMap, manualList, ZYPConstant.FLG_OFF_N); // QC#27520 2018/08/21 Add
                divideCalcBase(msgMap, manualList, ZYPConstant.FLG_ON_Y);
            }
            summarize(msgMap, onBatchType);
        } else if (RECALC.equals(param.xxModeCd.getValue())) {
            summarize(msgMap,  onBatchType);
        // QC#22965 2018/04/11 Add Start
        } else if (MANUAL_PRICE.equals(param.xxModeCd.getValue())) {
            List<NWZC157003PMsg> autoApplyList = new ArrayList<NWZC157003PMsg>();
            manualList = deleteCalcBaseForManual(msgMap, autoApplyList);
            // QC#27520 2018/08/21 Del Start
            // divideCalcBase(msgMap, manualList, ZYPConstant.FLG_OFF_N);
            // QC#27520 2018/08/21 Del End
            resetCalcBase(msgMap, autoApplyList);
            divideCalcBase(msgMap, manualList, ZYPConstant.FLG_OFF_N); // QC#27520 2018/08/21 Add
            divideCalcBase(msgMap, manualList, ZYPConstant.FLG_ON_Y);
            summarize(msgMap, onBatchType);
        // QC#22965 2018/04/11 Add End
        }

        return;
    }

    //
    private boolean paramCheck(S21ApiMessageMap msgMap) {
        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();

        // Global Company Code
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgIdWithPrm(NWZM0163E, null);
            msgMap.flush();
            return true;
        }

        if (!ZYPCommonFunc.hasValue(param.xxModeCd.getValue())) {
            msgMap.addXxMsgIdWithPrm(NWZM0976E, null);
            msgMap.flush();
            return true;
        }

        if (!S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_PRICE_LIST)
                && !S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_BASE_PRICE)
                && !S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_LINE_PRICE)
                && !S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_ORDER_ALL)
                && !S21StringUtil.isEquals(param.xxModeCd.getValue(), RECALC)
                && !S21StringUtil.isEquals(param.xxModeCd.getValue(), MANUAL_PRICE)) { // QC#22965 2018/04/11 Add
            msgMap.addXxMsgIdWithPrm(NWZM0977E, null);
            msgMap.flush();
            return true;
        }

        // 2018/08/02 S21_NA#26665 del start
        // S21_NA#16027 add start
//        if (S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_LINE_PRICE) || S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_ORDER_ALL)) {
//
//            if (ZYPCommonFunc.hasValue(param.cpoSrcTpCd)) {
//
//                CPO_SRC_TPTMsg dsCpoSrcTpTMsg = getDsCpoSrcTp(param.glblCmpyCd.getValue(), param.cpoSrcTpCd.getValue());
//                if (dsCpoSrcTpTMsg != null && S21StringUtil.isEquals(dsCpoSrcTpTMsg.prcFrzFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
//                    // ** price freeze base price only + TAX(optional) not apply pricing rules.
//                    ZYPEZDItemValueSetter.setValue(param.xxModeCd, GET_BASE_PRICE);
//                }
//            }
//        }
        // S21_NA#16027 add start
        // 2018/08/02 S21_NA#26665 del end

        if (!ZYPCommonFunc.hasValue(param.prcBaseDt.getValue())) {
            msgMap.addXxMsgIdWithPrm(NWZM1155E, null);
            msgMap.flush();
            return true;
        }

        if (!ZYPCommonFunc.hasValue(param.prcCtxTpCd.getValue())) {
            msgMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Price Context Type Code" });
            msgMap.flush();
            return true;
        }

        if (S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_PRICE_LIST)) {
            if (!ZYPCommonFunc.hasValue(param.csmpNum.getValue()) && !ZYPCommonFunc.hasValue(param.dlrRefNum.getValue())) {
                if (!ZYPCommonFunc.hasValue(param.lineBizTpCd.getValue())) {
                    msgMap.addXxMsgIdWithPrm(NWZM1783E, null);
                    msgMap.flush();
                    return true;
                }
                if (!ZYPCommonFunc.hasValue(param.dsAcctNum.getValue())) {
                    msgMap.addXxMsgIdWithPrm(NWZM1403E, null);
                    msgMap.flush();
                    return true;
                }
            }
        } else {
            if (!ZYPCommonFunc.hasValue(param.lineBizTpCd.getValue())) {
                msgMap.addXxMsgIdWithPrm(NWZM1783E, null);
                msgMap.flush();
                return true;
            }
            if (!ZYPCommonFunc.hasValue(param.dsAcctNum.getValue())) {
                msgMap.addXxMsgIdWithPrm(NWZM1403E, null);
                msgMap.flush();
                return true;
            }
            if (!ZYPCommonFunc.hasValue(param.taxCalcFlg.getValue())) {
                msgMap.addXxMsgIdWithPrm(NWZM0353E, null);
                msgMap.flush();
                return true;
            }
        }

        if (!GET_PRICE_LIST.equals(param.xxModeCd.getValue())) {
            // Level-2
            for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
                NWZC157002PMsg line = param.NWZC157002PMsg.no(i);
                S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
                if (!S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_PRICE_LIST)) {
                    if (!ZYPCommonFunc.hasValue(line.trxLineNum.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM0803E, null);
                        lineMap.flush();
                        return true;
                    }
                    if (!ZYPCommonFunc.hasValue(line.trxLineSubNum.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM0804E, null);
                        lineMap.flush();
                        return true;
                    }
                    if (!ZYPCommonFunc.hasValue(line.prcBaseDt)) {
                        ZYPEZDItemValueSetter.setValue(line.prcBaseDt, param.prcBaseDt);
                    }
                    if (!ZYPCommonFunc.hasValue(line.billToCustCd.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM0988E, null);
                        lineMap.flush();
                        return true;
                    }
                    if (!ZYPCommonFunc.hasValue(line.shipToCustCd.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM1000E, null);
                        lineMap.flush();
                        return true;
                    }
                    if (!ZYPCommonFunc.hasValue(line.dsAcctNum_SH.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM1379E, null);
                        lineMap.flush();
                        return true;
                    }
                    if (!ZYPCommonFunc.hasValue(line.dsAcctNum_BL.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM1377E, null);
                        lineMap.flush();
                        return true;
                    }
                    if (!ZYPCommonFunc.hasValue(line.mdseCd.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM0996E, null);
                        lineMap.flush();
                        return true;
                    }
                    if (!ZYPCommonFunc.hasValue(line.pkgUomCd.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM1168E, null);
                        lineMap.flush();
                        return true;
                    }
                    if (!ZYPCommonFunc.hasValue(line.ordQty.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM0046E, null);
                        lineMap.flush();
                        return true;
                    }
                    if (!ZYPCommonFunc.hasValue(line.ordCustUomQty.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Ordered Customer Uom Quantity" });
                        lineMap.flush();
                        return true;
                    }
                    if (!ZYPCommonFunc.hasValue(line.invQty)) {
                        ZYPEZDItemValueSetter.setValue(line.invQty, BigDecimal.ZERO);
                    }
                    if (!ZYPCommonFunc.hasValue(line.invQty.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Invoiced Qty" });
                        lineMap.flush();
                        return true;
                    }
                    if (!ZYPCommonFunc.hasValue(line.xxPrcCloFlg.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Line Close Flag" });
                        lineMap.flush();
                        return true;
                    }
                }
                if (S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_LINE_PRICE)
                        || S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_ORDER_ALL)
                        || S21StringUtil.isEquals(param.xxModeCd.getValue(), RECALC)) {
                    // Ship-To City Address
                    // Ship-To Country Code
                    if (!ZYPCommonFunc.hasValue(line.ctryCd_SH.getValue())) {
                        lineMap.addXxMsgIdWithPrm(NWZM0515E, null);
                        lineMap.flush();
                        return true;
                    }
                }
                if (!ZYPCommonFunc.hasValue(line.configCatgCd)) {
                    line.configCatgCd.setValue(CONFIG_CATG.OUTBOUND);
                    for (int n = 0; n < line.NWZC157003PMsg.getValidCount(); n++) {
                        line.NWZC157003PMsg.no(n).configCatgCd.setValue(CONFIG_CATG.OUTBOUND);
                    }
                }
            }
        }

        return false;
    }

    private boolean paramDBCheck(S21ApiMessageMap msgMap) {
        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();

        String glblCmpyCd = param.glblCmpyCd.getValue();

        // Global Company Code
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        if (glblCmpyTMsg == null) {
            msgMap.addXxMsgIdWithPrm(NWZM0650E, null);
            msgMap.flush();
            return true;
        }
        // DS Order Category Code
        if (ZYPCommonFunc.hasValue(param.dsOrdCatgCd)) {
            DS_ORD_CATGTMsg dsOrdCatgTMsg = getDsOrdCatg(glblCmpyCd, param.dsOrdCatgCd.getValue());
            if (dsOrdCatgTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1415E, null);
                msgMap.flush();
                return true;
            }
        }

        // DS Order Type Code
        if (ZYPCommonFunc.hasValue(param.dsOrdTpCd)) {
            Map<String, String> dsOrdTpRow = getDsOrdTp(glblCmpyCd, param.dsOrdTpCd.getValue());
            if (dsOrdTpRow == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1815E, null);
                msgMap.flush();
                return true;
            } else {
                // QC#21690 2017/10/20 Mod Start
                // ZYPEZDItemValueSetter.setValue(param.defPrcCatgCd, dsOrdTpRow.get("DEF_PRC_CATG_CD"));
                if(PRC_CTX_TP.SERVICE_PRICING.equals(param.prcCtxTpCd.getValue())){
                    ZYPEZDItemValueSetter.setValue(param.defPrcCatgCd, dsOrdTpRow.get("DEF_MAINT_PRC_CATG_CD"));
                } else{
                    ZYPEZDItemValueSetter.setValue(param.defPrcCatgCd, dsOrdTpRow.get("DEF_PRC_CATG_CD"));
                }
                // QC#21690 2017/10/20 Mod End
                ZYPEZDItemValueSetter.setValue(param.ccyCd, dsOrdTpRow.get("CCY_CD"));
                ZYPEZDItemValueSetter.setValue(param.invTpCd, dsOrdTpRow.get("INV_TP_CD"));
                ZYPEZDItemValueSetter.setValue(param.taxFlg, dsOrdTpRow.get("TAX_CALC_FLG"));
                ZYPEZDItemValueSetter.setValue(param.taxExemFlg, dsOrdTpRow.get("TAX_EXEM_FLG"));
                ZYPEZDItemValueSetter.setValue(param.taxExemRsnCd, dsOrdTpRow.get("TAX_EXEM_RSN_CD"));
            }
        }

        if (ZYPCommonFunc.hasValue(param.lineBizTpCd)) {
            LINE_BIZ_TPTMsg lineBizTpTMsg = getLineBizTp(glblCmpyCd, param.lineBizTpCd.getValue());
            if (lineBizTpTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Line Business Type Code", param.lineBizTpCd.getValue(), "LINE_BIZ_TP" });
                msgMap.flush();
                return true;
            }
        }

        if (ZYPCommonFunc.hasValue(param.dsAcctNum)) {
            Map<String, String> dsAcctCustMap = getDsAcctCust(glblCmpyCd, param.dsAcctNum.getValue(), param.prcBaseDt.getValue());
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

        // CSA Price Contract Number
        if (ZYPCommonFunc.hasValue(param.prcContrNum)) {
            PRC_CONTRTMsg prcContrTMsg = getPrcContr(glblCmpyCd, param.prcContrNum.getValue());
            if (prcContrTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"CSA Price Contract Number", param.prcContrNum.getValue(), "PRC_CONTR" });
                msgMap.flush();
                return true;
            }
        }
        // Branch
        if (ZYPCommonFunc.hasValue(param.coaBrCd)) {
            COA_BRTMsg coaBrTMsg = getCoaBr(glblCmpyCd, param.coaBrCd.getValue());
            if (coaBrTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NWZM0890E, null);
                msgMap.flush();
                return true;
            }
        }
        // Price Context Type Code
        PRC_CTX_TPTMsg prcCtxTpTMsg = getPrcCtxTp(param.glblCmpyCd.getValue(), param.prcCtxTpCd.getValue());
        if (prcCtxTpTMsg == null) {
            msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Price Context Type Code", param.cpoSrcTpCd.getValue(), "PRC_CTX" });
            msgMap.flush();
            return true;
        } else {
            prcRuleTrxCatgCd = prcCtxTpTMsg.prcRuleTrxCatgCd.getValue();
        }

        if (!GET_PRICE_LIST.equals(param.xxModeCd.getValue())) { // QC#11618 Add
            // CPO Source Type
            if (ZYPCommonFunc.hasValue(param.cpoSrcTpCd)) {
                CPO_SRC_TPTMsg cpoSrcTpTMsg = getCpoSrcTp(param.glblCmpyCd.getValue(), param.cpoSrcTpCd.getValue());
                if (cpoSrcTpTMsg == null) {
                    msgMap.addXxMsgIdWithPrm(NWZM0110E, null);
                    msgMap.flush();
                    return true;
                }
            }
            // Payment Method
            if (ZYPCommonFunc.hasValue(param.dsPmtMethCd)) {
                DS_PMT_METHTMsg dsPmtMethCdTMsg = getDsPmtMeth(param.glblCmpyCd.getValue(), param.dsPmtMethCd.getValue());
                if (dsPmtMethCdTMsg == null) {
                    msgMap.addXxMsgIdWithPrm(NWAM0204E, null);
                    msgMap.flush();
                    return true;
                }
            }
            // Special Handling Type Code
            if (ZYPCommonFunc.hasValue(param.spclHdlgTpCd)) {
                SPCL_HDLG_TPTMsg getSpclHdlgTp = getSpclHdlgTp(param.glblCmpyCd.getValue(), param.spclHdlgTpCd.getValue());
                if (getSpclHdlgTp == null) {
                    msgMap.addXxMsgIdWithPrm(NWZM1428E, null);
                    msgMap.flush();
                    return true;
                }
            }
        }

        if (!GET_PRICE_LIST.equals(param.xxModeCd.getValue())) { // QC#11618 Add
            // Level-2
            for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
                NWZC157002PMsg line = param.NWZC157002PMsg.no(i);
                S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
                // Bill to Customer Code
                if (ZYPCommonFunc.hasValue(line.billToCustCd)) {
                    Map<String, String> billToCustMap = getBillToCust(glblCmpyCd, line.billToCustCd.getValue(), param.prcBaseDt.getValue());
                    if (billToCustMap == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1444E, null);
                        lineMap.flush();
                        return true;
                    } else {
                        ZYPEZDItemValueSetter.setValue(line.dsTaxGrpExemCd_B, billToCustMap.get("DS_TAX_GRP_EXEM_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsTaxGrpExemReslFlg_B, billToCustMap.get("DS_TAX_GRP_EXEM_RESL_FLG"));
                    }
                }
                // Ship to Customer Code
                if (ZYPCommonFunc.hasValue(line.shipToCustCd)) {
                    Map<String, String> shipToCustMap = getShipToCust(glblCmpyCd, line.shipToCustCd.getValue(), param.prcBaseDt.getValue());
                    if (shipToCustMap == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1445E, null);
                        lineMap.flush();
                        return true;
                    } else {
                        ZYPEZDItemValueSetter.setValue(line.dsTaxGrpExemCd_S, shipToCustMap.get("DS_TAX_GRP_EXEM_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsTaxGrpExemReslFlg_S, shipToCustMap.get("DS_TAX_GRP_EXEM_RESL_FLG"));
                        ZYPEZDItemValueSetter.setValue(line.taxAreaId_SH, shipToCustMap.get("GEO_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsInsdCtyLimitFlg_SH, shipToCustMap.get("DS_INSD_CTY_LIMIT_FLG"));
                    }
                }
                // Ship to Account Number
                if (ZYPCommonFunc.hasValue(line.dsAcctNum_SH)) {
                    Map<String, String> dsAcctCustShMap = getDsAcctCust(glblCmpyCd, line.dsAcctNum_SH.getValue(), param.prcBaseDt.getValue());
                    if (dsAcctCustShMap == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1417E, null);
                        lineMap.flush();
                        return true;
                    } else {
                        ZYPEZDItemValueSetter.setValue(line.dsAcctNm_SH, dsAcctCustShMap.get("DS_ACCT_NM"));
                        ZYPEZDItemValueSetter.setValue(line.dsCustSicCd_SH, dsAcctCustShMap.get("DS_CUST_SIC_CD"));
                        ZYPEZDItemValueSetter.setValue(line.locNum_SH, dsAcctCustShMap.get("LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(line.dsAcctClsCd_SH, dsAcctCustShMap.get("DS_ACCT_CLS_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsAcctTpCd_SH, dsAcctCustShMap.get("DS_ACCT_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsAcctDlrCd_SH, dsAcctCustShMap.get("DS_ACCT_DLR_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsAcctGrpCd_SH, dsAcctCustShMap.get("DS_ACCT_GRP_CD"));
                        ZYPEZDItemValueSetter.setValue(line.coaChCd_SH, dsAcctCustShMap.get("COA_CH_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsTaxGrpExemCd_SH, dsAcctCustShMap.get("DS_TAX_GRP_EXEM_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsTaxGrpExemReslFlg_SH, dsAcctCustShMap.get("DS_TAX_GRP_EXEM_RESL_FLG"));
                    }
                }
                // Bill to Account Number
                if (ZYPCommonFunc.hasValue(line.dsAcctNum_BL)) {
                    Map<String, String> dsAcctCustBlMap = getDsAcctCust(glblCmpyCd, line.dsAcctNum_BL.getValue(), param.prcBaseDt.getValue());
                    if (dsAcctCustBlMap == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1416E, null);
                        lineMap.flush();
                        return true;
                    } else {
                        ZYPEZDItemValueSetter.setValue(line.dsAcctNm_BL, dsAcctCustBlMap.get("DS_ACCT_NM"));
                        ZYPEZDItemValueSetter.setValue(line.dsCustSicCd_BL, dsAcctCustBlMap.get("DS_CUST_SIC_CD"));
                        ZYPEZDItemValueSetter.setValue(line.locNum_BL, dsAcctCustBlMap.get("LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(line.dsAcctClsCd_BL, dsAcctCustBlMap.get("DS_ACCT_CLS_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsAcctTpCd_BL, dsAcctCustBlMap.get("DS_ACCT_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsAcctDlrCd_BL, dsAcctCustBlMap.get("DS_ACCT_DLR_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsAcctGrpCd_BL, dsAcctCustBlMap.get("DS_ACCT_GRP_CD"));
                        ZYPEZDItemValueSetter.setValue(line.coaChCd_BL, dsAcctCustBlMap.get("COA_CH_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsTaxGrpExemCd_BL, dsAcctCustBlMap.get("DS_TAX_GRP_EXEM_CD"));
                        ZYPEZDItemValueSetter.setValue(line.dsTaxGrpExemReslFlg_BL, dsAcctCustBlMap.get("DS_TAX_GRP_EXEM_RESL_FLG"));
                    }
                }
                // Price Category
                if (ZYPCommonFunc.hasValue(line.prcCatgCd)) {
                    PRC_CATGTMsg prcCatgTMsg = getPrcCatg(glblCmpyCd, line.prcCatgCd.getValue());
                    if (prcCatgTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1419E, null);
                        lineMap.flush();
                        return true;
                    } else {
                        ZYPEZDItemValueSetter.setValue(line.ccyCd, (String) prcCatgTMsg.ccyCd.getValue());
                        // TEST LOGIC
                        if (!ZYPCommonFunc.hasValue(line.ccyCd_PC)) {
                            line.ccyCd_PC.setValue(line.ccyCd.getValue());
                        }
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
                        lineMap.addXxMsgIdWithPrm(NWZM0890E, null);
                        lineMap.flush();
                        return true;
                    }
                }
                // Rate Type Code
                if (ZYPCommonFunc.hasValue(line.prcRateTpCd)) {
                    PRC_RATE_TPTMsg coaBrTMsg = getPrcRateTp(glblCmpyCd, line.prcRateTpCd.getValue());
                    if (coaBrTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Rate Type Code", line.prcRateTpCd.getValue(), "PRC_RATE_TP" });
                        lineMap.flush();
                        return true;
                    }
                }

                // Price Meter Package PK
                if (ZYPCommonFunc.hasValue(line.prcMtrPkgPk)) {
                    PRC_MTR_PKGTMsg coaBrTMsg = getPrcMtrPkg(glblCmpyCd, line.prcMtrPkgPk.getValue());
                    if (coaBrTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Price Meter Package PK", line.prcMtrPkgPk.getValue().toString(), "PRC_MTR_PKG" });
                        lineMap.flush();
                        return true;
                    }
                }
                // Price Service Plan Type Code
                if (ZYPCommonFunc.hasValue(line.prcSvcPlnTpCd)) {
                    PRC_SVC_PLN_TPTMsg prcSvcPlnTpTMsg = getPrcSvcPlnTp(glblCmpyCd, line.prcSvcPlnTpCd.getValue());
                    if (prcSvcPlnTpTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Price Service Plan Type Code", line.prcSvcPlnTpCd.getValue(), "PRC_SVC_PLAN_TP" });
                        lineMap.flush();
                        return true;
                    }
                }

                // Price Service Contract Type Code
                if (ZYPCommonFunc.hasValue(line.prcSvcContrTpCd)) {
                    PRC_SVC_CONTR_TPTMsg prcSvcContrTpTMsg = getPrcSvcContrTp(glblCmpyCd, line.prcSvcContrTpCd.getValue());
                    if (prcSvcContrTpTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Price Service Contract Type Code", line.prcSvcContrTpCd.getValue(), "PRC_SVC_CONTR_TP" });
                        lineMap.flush();
                        return true;
                    }
                }
                // Meter Label Code
                if (ZYPCommonFunc.hasValue(line.mtrLbCd)) {
                    MTR_LBTMsg mtrLbTMsg = getMtrLb(glblCmpyCd, line.mtrLbCd.getValue());
                    if (mtrLbTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Meter Label Code", line.mtrLbCd.getValue(), "MTR_LB" });
                        lineMap.flush();
                        return true;
                    }
                }
                // Price List Band Code
                if (ZYPCommonFunc.hasValue(line.prcListBandCd)) {
                    PRC_LIST_BANDTMsg prcListBandTMsg = getPrcListBand(glblCmpyCd, line.prcListBandCd.getValue());
                    if (prcListBandTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Price List Band Code", line.prcListBandCd.getValue(), "PRC_LIST_BAND" });
                        lineMap.flush();
                        return true;
                    }
                }
                // Price Service Zone Code
                if (ZYPCommonFunc.hasValue(line.prcSvcZoneCd)) {
                    PRC_SVC_ZONETMsg prcSvcZoneTMsg = getPrcSvcZone(glblCmpyCd, line.prcSvcZoneCd.getValue());
                    if (prcSvcZoneTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Price Service Zone Code", line.prcSvcZoneCd.getValue(), "PRC_SVC_ZONE" });
                        lineMap.flush();
                        return true;
                    }
                }
                // Price Service Tier Type Code
                if (ZYPCommonFunc.hasValue(line.prcSvcTierTpCd)) {
                    PRC_SVC_TIER_TPTMsg prcSvcTierTpTMsg = getPrcSvcTierTp(glblCmpyCd, line.prcSvcTierTpCd.getValue());
                    if (prcSvcTierTpTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Price Service Tier Type Code", line.prcSvcTierTpCd.getValue(), "PRC_SVC_TIER_TP" });
                        lineMap.flush();
                        return true;
                    }
                }
                // Merchandise Code
                if (ZYPCommonFunc.hasValue(line.mdseCd)) {
                    Map<String, Object> mdseMap = getMdse(glblCmpyCd, line.mdseCd.getValue(), line.pkgUomCd.getValue());
                    if (mdseMap == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM0293E, null);
                        lineMap.flush();
                        return true;
                    } else {
                        ZYPEZDItemValueSetter.setValue(line.ordTakeMdseCd, (String) mdseMap.get("ORD_TAKE_MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(line.mdseNm, (String) mdseMap.get("MDSE_NM"));
                        ZYPEZDItemValueSetter.setValue(line.zerothProdCtrlCd, (String) mdseMap.get("ZEROTH_PROD_CTRL_CD"));
                        ZYPEZDItemValueSetter.setValue(line.firstProdCtrlCd, (String) mdseMap.get("FIRST_PROD_CTRL_CD"));
                        ZYPEZDItemValueSetter.setValue(line.scdProdCtrlCd, (String) mdseMap.get("SCD_PROD_CTRL_CD"));
                        ZYPEZDItemValueSetter.setValue(line.thirdProdCtrlCd, (String) mdseMap.get("THIRD_PROD_CTRL_CD"));
                        ZYPEZDItemValueSetter.setValue(line.frthProdCtrlCd, (String) mdseMap.get("FRTH_PROD_CTRL_CD"));
                        ZYPEZDItemValueSetter.setValue(line.fifthProdCtrlCd, (String) mdseMap.get("FIFTH_PROD_CTRL_CD"));
                        // QC#22569 2018/04/24 Add Start
                        ZYPEZDItemValueSetter.setValue(line.slsMatGrpCd_01, (String) mdseMap.get("SLS_MAT_GRP_CD_01"));
                        ZYPEZDItemValueSetter.setValue(line.slsMatGrpCd_02, (String) mdseMap.get("SLS_MAT_GRP_CD_02"));
                        ZYPEZDItemValueSetter.setValue(line.slsMatGrpCd_03, (String) mdseMap.get("SLS_MAT_GRP_CD_03"));
                        // QC#22569 2018/04/24 Add End
                        ZYPEZDItemValueSetter.setValue(line.mdseItemTpCd, (String) mdseMap.get("MDSE_ITEM_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(line.mdseItemClsTpCd, (String) mdseMap.get("MDSE_ITEM_CLS_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(line.coaMdseTpCd, (String) mdseMap.get("COA_MDSE_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(line.coaProdCd, (String) mdseMap.get("COA_PROD_CD"));
                        ZYPEZDItemValueSetter.setValue(line.mktMdlCd, (String) mdseMap.get("MKT_MDL_CD"));
                        ZYPEZDItemValueSetter.setValue(line.mktMdlSegCd, (String) mdseMap.get("MKT_MDL_SEG_CD"));
                        ZYPEZDItemValueSetter.setValue(line.svcAllocTpCd, (String) mdseMap.get("SVC_ALLOC_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(line.svcAllocTrxTpNm, (String) mdseMap.get("SVC_ALLOC_TRX_TP_NM"));
                        ZYPEZDItemValueSetter.setValue(line.taxExemTpCd, (String) mdseMap.get("TAX_EXEM_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(line.unitNetWt, (BigDecimal) mdseMap.get("IN_POUND_WT"));
                        ZYPEZDItemValueSetter.setValue(line.basePkgUomCd, (String) mdseMap.get("PKG_UOM_CD"));
                        ZYPEZDItemValueSetter.setValue(line.inEachQty, (BigDecimal) mdseMap.get("IN_EACH_QTY"));
                        if (S21StringUtil.isEquals(MDSE_TP.SET, (String) mdseMap.get("MDSE_TP_CD"))) {
                            BigDecimal unitNetWt = getChildMdseWt(glblCmpyCd, param.prcBaseDt.getValue(), (String) mdseMap.get("MDSE_CD"));
                            ZYPEZDItemValueSetter.setValue(line.unitNetWt, unitNetWt);
                        }
                        // QC#21814 2017/10/17 Add Start
                        ZYPEZDItemValueSetter.setValue(line.invtyCtrlFlg, (String) mdseMap.get("INVTY_CTRL_FLG"));
                        // QC#21814 2017/10/17 Add End
                    }
                }
                // DS ORD Line Catg Code
                if (ZYPCommonFunc.hasValue(line.dsOrdLineCatgCd)) {
                    DS_ORD_LINE_CATGTMsg dsOrdLineCatgTMsg = getDsOrdLineCatg(glblCmpyCd, line.dsOrdLineCatgCd.getValue());
                    if (dsOrdLineCatgTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"DS ORD Line Catg Code", line.dsOrdLineCatgCd.getValue(), "DS_ORD_LINE_CATG" });
                        lineMap.flush();
                        return true;
                    } else {
                        dsOrdLineDrctnCd = dsOrdLineCatgTMsg.dsOrdLineDrctnCd.getValue();
                    }
                }
                // Return Reason Code
                if (ZYPCommonFunc.hasValue(line.rtrnRsnCd)) {
                    RTRN_RSNTMsg rtrnRsnTMsg = getRtrnRsnCd(glblCmpyCd, line.rtrnRsnCd.getValue());
                    if (rtrnRsnTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Return Reason Code", line.rtrnRsnCd.getValue(), "RTRN_RSN" });
                        lineMap.flush();
                        return true;
                    }
                }
                // Model ID
                if (ZYPCommonFunc.hasValue(line.mdlId)) {
                    MDL_NMTMsg mdlNmTMsg = getMtrLbCd(glblCmpyCd, line.mdlId.getValue());
                    if (mdlNmTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1443E, null);
                        lineMap.flush();
                        return true;
                    }
                }
                // Service Call Type Code
                if (ZYPCommonFunc.hasValue(line.svcCallTpCd)) {
                    SVC_CALL_TPTMsg svcCallTpTMsg = getSvcCallTpCd(glblCmpyCd, line.svcCallTpCd.getValue());
                    if (svcCallTpTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Service Call Type Code", line.svcCallTpCd.getValue(), "SVC_CALL_TP" });
                        lineMap.flush();
                        return true;
                    }
                }
                // Ship-To Country Code
                if (ZYPCommonFunc.hasValue(line.ctryCd_SH)) {
                    CTRYTMsg ctryTMsg = getCtry(glblCmpyCd, line.ctryCd_SH.getValue());
                    if (ctryTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1448E, null);
                        lineMap.flush();
                        return true;
                    }
                }
                if (!glblCmpyTMsg.ctryCd.getValue().equals(line.ctryCd_SH.getValue())) {
                    isExpt = true;
                }
                // Ship-To City Address
                if (!ZYPCommonFunc.hasValue(line.ctyAddr_SH)) {
                    if (S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_LINE_PRICE)
                            || S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_ORDER_ALL)
                            || S21StringUtil.isEquals(param.xxModeCd.getValue(), RECALC)) {
                        if (!isExpt) {
                            lineMap.addXxMsgIdWithPrm(NWZM0512E, null);
                            lineMap.flush();
                            return true;
                        }
                    }
                }

                // Ship-To State Code
                if (ZYPCommonFunc.hasValue(line.stCd_SH)) {
                    STTMsg stTMsg = getSt(glblCmpyCd, line.stCd_SH.getValue());
                    if (stTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1446E, null);
                        lineMap.flush();
                        return true;
                    }
                } else {
                    if (S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_LINE_PRICE)
                            || S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_ORDER_ALL)
                            || S21StringUtil.isEquals(param.xxModeCd.getValue(), RECALC)) {
                        if (!isExpt) {
                            msgMap.addXxMsgIdWithPrm(NWZM0331E, null);
                            lineMap.flush();
                            return true;
                        }
                    }
                }

                // Freight Condition Code
                if (ZYPCommonFunc.hasValue(line.frtCondCd)) {
                    FRT_CONDTMsg frtCondTMsg = getFrtCond(glblCmpyCd, line.frtCondCd.getValue());
                    if (frtCondTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1426E, null);
                        lineMap.flush();
                        return true;
                    } else {
                        ZYPEZDItemValueSetter.setValue(line.frtChrgToCd, frtCondTMsg.frtChrgToCd.getValue());
                        ZYPEZDItemValueSetter.setValue(line.frtChrgMethCd, frtCondTMsg.frtChrgMethCd.getValue());
                    }
                }
                // Shipping Service Level Code
                if (ZYPCommonFunc.hasValue(line.shpgSvcLvlCd)) {
                    SHPG_SVC_LVLTMsg prcSvcPlnTpTMsg = getShpgSvcLvl(glblCmpyCd, line.shpgSvcLvlCd.getValue());
                    if (prcSvcPlnTpTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Shipping Service Level Code", line.shpgSvcLvlCd.getValue(), "SHPG_SVC_LVL" });
                        lineMap.flush();
                        return true;
                    }
                }
                // COA Extension Code (Business Unit)
                if (ZYPCommonFunc.hasValue(line.coaExtnCd)) {
                    COA_EXTNTMsg coaExtnTMsg = getCoaExtn(glblCmpyCd, line.coaExtnCd.getValue());
                    if (coaExtnTMsg == null) {
                        lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Business Unit Code", line.coaExtnCd.getValue(), "COA_EXTN" });
                        lineMap.flush();
                        return true;
                    }
                }
                    String taxAreaId = getTaxAreaId(glblCmpyCd, line.cntyNm_SH.getValue(), line.ctyAddr_SH.getValue(), line.stCd_SH.getValue());
                    if (ZYPCommonFunc.hasValue(taxAreaId)) {
                        ZYPEZDItemValueSetter.setValue(line.taxAreaId_SP, taxAreaId);
                    }
                // Retail Warehouse Code -> Tax Area ID
                if (ZYPCommonFunc.hasValue(line.rtlWhCd)) {
                    Map<String, String> rtlWhTMap = getRtlWh(glblCmpyCd, line.rtlWhCd.getValue());
                    if (rtlWhTMap != null) {
                        ZYPEZDItemValueSetter.setValue(line.taxAreaId_SF, rtlWhTMap.get("GEO_CD"));
                        ZYPEZDItemValueSetter.setValue(line.firstLineAddr_SF, rtlWhTMap.get("FIRST_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(line.scdLineAddr_SF, rtlWhTMap.get("SCD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(line.ctyAddr_SF, rtlWhTMap.get("CTY_ADDR"));
                        ZYPEZDItemValueSetter.setValue(line.stCd_SF, rtlWhTMap.get("ST_CD"));
                        ZYPEZDItemValueSetter.setValue(line.postCd_SF, rtlWhTMap.get("POST_CD"));
                        ZYPEZDItemValueSetter.setValue(line.ctryCd_SF, rtlWhTMap.get("CTRY_CD"));
                        ZYPEZDItemValueSetter.setValue(line.cntyNm_SF, rtlWhTMap.get("CNTY_NM"));
                    }
                }
                // Sales Rep or Sales Team TOC Code -> Tax Area ID
                if (ZYPCommonFunc.hasValue(line.slsRepOrSlsTeamTocCd)) {
                    Map<String, String> tocMap = getToc(glblCmpyCd, param.prcBaseDt.getValue(), line.slsRepOrSlsTeamTocCd.getValue());
                    if (tocMap != null) {
                        ZYPEZDItemValueSetter.setValue(line.taxAreaId_SR, tocMap.get("GEO_CD"));
                        // QC#19945 2017/07/18 Add Start
                        ZYPEZDItemValueSetter.setValue(line.dsInsdCtyLimitFlg_SR, tocMap.get("DS_INSD_CTY_LIMIT_FLG")); 
                        // QC#19945 2017/07/18 Add End
                        ZYPEZDItemValueSetter.setValue(line.firstLineAddr_SR, tocMap.get("FIRST_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(line.scdLineAddr_SR, tocMap.get("SCD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(line.ctyAddr_SR, tocMap.get("CTY_ADDR"));
                        ZYPEZDItemValueSetter.setValue(line.stCd_SR, tocMap.get("ST_CD"));
                        ZYPEZDItemValueSetter.setValue(line.postCd_SR, tocMap.get("POST_CD"));
                        ZYPEZDItemValueSetter.setValue(line.ctryCd_SR, tocMap.get("CTRY_CD"));
                        ZYPEZDItemValueSetter.setValue(line.cntyNm_SR, tocMap.get("CNTY_NM"));
                    }
                }
            }
        }

        return false;
    }

    // QC#22965 2018/04/11 Mod Start
    //private List<NWZC157003PMsg> deleteCalcBase(S21ApiMessageMap msgMap) {
    //    List<NWZC157003PMsg> manualEntryCalcBaseList = new ArrayList<NWZC157003PMsg>();
    //    List<Integer> delList = new ArrayList<Integer>();
    //    NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();
    //    NWZC157002PMsg line = null;
    //    for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
    //        line = param.NWZC157002PMsg.no(i);
    //        if (!isFrozen(line)) {
    //            for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
    //                NWZC157003PMsg calcBase = line.NWZC157003PMsg.no(j);
    //                if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManAddFlg.getValue())) {
    //                    NWZC157003PMsg calcBaseData = new NWZC157003PMsg();
    //                    EZDMsg.copy(calcBase, null, calcBaseData, null);
    //                    manualEntryCalcBaseList.add(calcBaseData);
    //                    delList.add(j);
    //                }
    //                if (!ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManEntryFlg.getValue())) {
    //                    delList.add(j);
    //                }
    //            }
    //            ZYPTableUtil.deleteRows(line.NWZC157003PMsg, delList);
    //            delList.clear();
    //        }
    //    }
    //    return manualEntryCalcBaseList;
    //}
    private List<NWZC157003PMsg> deleteCalcBase(S21ApiMessageMap msgMap) {
        List<NWZC157003PMsg> manualEntryCalcBaseList = new ArrayList<NWZC157003PMsg>();
        List<Integer> delList = new ArrayList<Integer>();
        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();
        NWZC157002PMsg line = null;
        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
            line = param.NWZC157002PMsg.no(i);
            if (!isFrozen(line)) {
                for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                    NWZC157003PMsg calcBase = line.NWZC157003PMsg.no(j);
                    if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManAddFlg.getValue())) {
                        NWZC157003PMsg calcBaseData = new NWZC157003PMsg();
                        EZDMsg.copy(calcBase, null, calcBaseData, null);
                        manualEntryCalcBaseList.add(calcBaseData);
                        delList.add(j);
                    } else if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManEntryFlg.getValue())) {
                        if(PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())){
                            continue;
                        }
                        NWZC157003PMsg calcBaseData = new NWZC157003PMsg();
                        EZDMsg.copy(calcBase, null, calcBaseData, null);
                        manualEntryCalcBaseList.add(calcBaseData);
                        //if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(calcBaseData.prcDtlGrpCd.getValue()) 
                        //        || PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(calcBaseData.prcDtlGrpCd.getValue())
                        //        || PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(calcBaseData.prcDtlGrpCd.getValue())) { // QC#21841 2018/05/21 Add
                        //    delList.add(j);
                        //}
                    }
                    if (!ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManEntryFlg.getValue())) {
                        delList.add(j);
                    }
                }
                ZYPTableUtil.deleteRows(line.NWZC157003PMsg, delList);
                delList.clear();
            }
        }
        return manualEntryCalcBaseList;
    }
    // QC#22965 2018/04/11 Mod End

	// QC#22965 2018/04/11 Add Start
    private List<NWZC157003PMsg> deleteCalcBaseForManual(S21ApiMessageMap msgMap, List<NWZC157003PMsg> autoApplyList) {
        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();
        List<NWZC157003PMsg> manualEntryCalcBaseList = new ArrayList<NWZC157003PMsg>();
        List<Integer> delList = new ArrayList<Integer>();
        NWZC157002PMsg line = null;
        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
            line = param.NWZC157002PMsg.no(i);
            if (!isFrozen(line)) {
                for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                    NWZC157003PMsg calcBase = line.NWZC157003PMsg.no(j);
                    if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManAddFlg.getValue())) {
                        NWZC157003PMsg calcBaseData = new NWZC157003PMsg();
                        EZDMsg.copy(calcBase, null, calcBaseData, null);
                        manualEntryCalcBaseList.add(calcBaseData);
                        delList.add(j);
                    } else if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManEntryFlg.getValue())) {
                        if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                            continue;
                        }
                        NWZC157003PMsg calcBaseData = new NWZC157003PMsg();
                        EZDMsg.copy(calcBase, null, calcBaseData, null);
                        manualEntryCalcBaseList.add(calcBaseData);
                        if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(calcBaseData.prcDtlGrpCd.getValue()) 
                                || PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(calcBaseData.prcDtlGrpCd.getValue())
                                || PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(calcBaseData.prcDtlGrpCd.getValue())) {
                            delList.add(j);
                        }
                    } else {
                        if (!PRC_COND_TP.BASE_PRICE.equals(calcBase.prcCondTpCd.getValue()) 
                                && !PRC_COND_TP.MANUAL_PRICE.equals(calcBase.prcCondTpCd.getValue())) {
                            NWZC157003PMsg calcBaseData = new NWZC157003PMsg();
                            EZDMsg.copy(calcBase, null, calcBaseData, null);
                            autoApplyList.add(calcBaseData);
                            delList.add(j);
                        }
                    }
                }
                ZYPTableUtil.deleteRows(line.NWZC157003PMsg, delList);
                delList.clear();
            }
        }
        return manualEntryCalcBaseList;
    }
	// QC#22965 2018/04/11 Add End

    private boolean hdrPriceListDeriversion(S21ApiMessageMap msgMap) {
        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();
        List<Map<String, String>> ssmResList = new ArrayList<Map<String, String>>();

        Map<String, String> mapParam = new HashMap<String, String>();
        // Add #8136 2016/05/13 Start
        if (PRC_CTX_TP.CSMP_CREDIT.equals(param.prcCtxTpCd.getValue())) {
            if (ZYPCommonFunc.hasValue(param.csmpNum) || ZYPCommonFunc.hasValue(param.dlrRefNum)) {
                // set query parameter
                mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
                mapParam.put("dsAcctNum", param.dsAcctNum.getValue());
                if (ZYPCommonFunc.hasValue(param.csmpNum)) {
                    mapParam.put("csmpNum", param.csmpNum.getValue());
                } else {
                    mapParam.put("dlrRefNum", param.dlrRefNum.getValue());
                }
                mapParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
                mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
                mapParam.put("prcBaseDt", param.prcBaseDt.getValue());
                mapParam.put("prcCtxTpCd", param.prcCtxTpCd.getValue());

                // execute query
                ssmResList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("selectPriceListForCSMPCredit", mapParam);
            }
        } else {
            // set query parameter
            mapParam.put("PrcGrpTrgtAttrbCd_14", PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER);
            mapParam.put("PrcGrpTrgtAttrbCd_15", PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME);
            mapParam.put("PrcGrpTrgtAttrbCd_16", PRC_GRP_TRGT_ATTRB.SIC_CODE);
            mapParam.put("PrcGrpTrgtAttrbCd_17", PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER);
            mapParam.put("PrcGrpTrgtAttrbCd_18", PRC_GRP_TRGT_ATTRB.CLASSIFICATION);
            mapParam.put("PrcGrpTrgtAttrbCd_19", PRC_GRP_TRGT_ATTRB.CATEGORY);
            mapParam.put("PrcGrpTrgtAttrbCd_20", PRC_GRP_TRGT_ATTRB.DEALER_CODE);
            mapParam.put("PrcGrpTrgtAttrbCd_21", PRC_GRP_TRGT_ATTRB.CSMP_NUMBER);
            mapParam.put("PrcGrpTrgtAttrbCd_22", PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER);
            mapParam.put("PrcGrpTrgtAttrbCd_24", PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER);
            mapParam.put("PrcGrpTrgtAttrbCd_25", PRC_GRP_TRGT_ATTRB.ORDER_CATEGORY);
            mapParam.put("PrcGrpTrgtAttrbCd_26", PRC_GRP_TRGT_ATTRB.ORDER_TYPE);
            mapParam.put("PrcGrpTrgtAttrbCd_27", PRC_GRP_TRGT_ATTRB.LINE_CATEGORY_TYPE);
            // QC#18789 add start
            mapParam.put("PrcGrpTrgtAttrbCd_28", PRC_GRP_TRGT_ATTRB.CHANNEL);
            // QC#18789 add end

            mapParam.put("prcGrpOpCd01", PRC_GRP_OP.EQ);
            mapParam.put("prcGrpOpCd02", PRC_GRP_OP.NOT_EQ);
            mapParam.put("prcGrpOpCd03", PRC_GRP_OP.BETWEEN);
            mapParam.put("prcGrpOpCd04", PRC_GRP_OP.LIKE);

            mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            mapParam.put("prcGrpTpCdCust", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
            mapParam.put("prcGrpTpCdTran", PRC_GRP_TP.ORDER_CATEGORY_OR_REASON);
            mapParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
            mapParam.put("prcBaseDt", param.prcBaseDt.getValue());
            mapParam.put("prcCtxTpCd", param.prcCtxTpCd.getValue());
            mapParam.put("ccyCd", param.ccyCd.getValue());
            mapParam.put("defPrcCatgCd", param.defPrcCatgCd.getValue());
            mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
            mapParam.put("pubFlg_Y", ZYPConstant.FLG_ON_Y);
            mapParam.put("pubFlg_N", ZYPConstant.FLG_OFF_N);
            if (!S21StringUtil.isEquals(param.lineBizTpCd.getValue(), LINE_BIZ_TP.ALL)) {
                mapParam.put("lineBizTpCd", param.lineBizTpCd.getValue());
            }
            mapParam.put("coaChCd01", param.coaChCd.getValue());
            mapParam.put("coaBrCd", param.coaBrCd.getValue());

            mapParam.put("dsAcctNum", param.dsAcctNum.getValue());
            mapParam.put("dsAcctNm", param.dsAcctNm.getValue());
            mapParam.put("dsCustSicCd", param.dsCustSicCd.getValue());
            mapParam.put("locNum", param.locNum.getValue());
            mapParam.put("dsAcctClsCd", param.dsAcctClsCd.getValue());
            mapParam.put("dsAcctTpCd", param.dsAcctTpCd.getValue());
            mapParam.put("dsAcctDlrCd", param.dsAcctDlrCd.getValue());
            mapParam.put("dsAcctGrpCd", param.dsAcctGrpCd.getValue());
            mapParam.put("csmpNum", param.csmpNum.getValue());
            mapParam.put("dlrRefNum", param.dlrRefNum.getValue());
            mapParam.put("prcContrNum", param.prcContrNum.getValue());

            mapParam.put("dsOrdCatgCd", param.dsOrdCatgCd.getValue());
            mapParam.put("dsOrdTpCd", param.dsOrdTpCd.getValue());

            // execute query
            ssmResList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("selectPriceList", mapParam);
        }

        // get price list
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String prtyNum = null;

        // Error Handling
        if (ssmResList.isEmpty()) {
            msgMap.addXxMsgIdWithPrm(NWZM0422E, null);
            msgMap.flush();
            return true;
        } else if (ssmResList.size() > param.xxPrcList.length()) {
            msgMap.addXxMsgIdWithPrm(NWZM0422E, null);
            msgMap.flush();
            return true;
        }

        // Set PriceList
        int vldCnt = 0;
        if (ZYPConstant.FLG_ON_Y.equals(param.xxPrcCatgOpFlg.getValue())) {
            List<String> setList = new ArrayList<String>();
            String prcCatgCd = null;
            for (Map<String, String> map : ssmResList) {
                prcCatgCd = (String) map.get("PRC_CATG_CD");
                if (!setList.contains(prcCatgCd)) {
                    ZYPEZDItemValueSetter.setValue(param.xxPrcList.no(vldCnt).prcCatgCd, prcCatgCd);
                    ZYPEZDItemValueSetter.setValue(param.xxPrcList.no(vldCnt).prtyNum, map.get("PRTY_NUM")); // QC#25104
                    setList.add(prcCatgCd);
                    vldCnt++;
                }
            }
            param.xxPrcList.setValidCount(vldCnt);
        } else {
            for (Map<String, String> map : ssmResList) {
                if (prtyNum == null) {
                    prtyNum = map.get("PRTY_NUM");
                }
                if (prtyNum.equals(map.get("PRTY_NUM"))) {
                    list.add(map);
                }
            }
            Map<String, String> map = getDefaultPriceList(list);
            if (map == null) {
                ZYPEZDItemValueSetter.setValue(param.xxPrcList.no(vldCnt).prcCatgCd, param.defPrcCatgCd);
            } else {
                ZYPEZDItemValueSetter.setValue(param.xxPrcList.no(vldCnt).prcCatgCd, (String) map.get("PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(param.xxPrcList.no(vldCnt).prtyNum, map.get("PRTY_NUM")); // QC#25104
            }
            vldCnt++;
            param.xxPrcList.setValidCount(vldCnt);
        }
        return false;
    }

    private boolean linePriceListDeriversion(S21ApiMessageMap msgMap, NWZC157002PMsg line) {

        S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
        if (ZYPCommonFunc.hasValue(line.prcCatgCd)) {
            return false;
        }
        if (isFrozen(line)) {
            return false;
        }

        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();
        List<Map<String, String>> list = getLinePriceList(param, line);

        if (list == null || list.isEmpty()) {
            lineMap.addXxMsgIdWithPrm(NWZM0422E, null);
            lineMap.flush();
            return true;
        } else {
            Map<String, String> map = getDefaultPriceList(list);
            if (map == null) {
                ZYPEZDItemValueSetter.setValue(line.prcCatgCd, param.defPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(line.ccyCd_PC, param.ccyCd);
                if (!ZYPCommonFunc.hasValue(line.ccyCd)) {
                    ZYPEZDItemValueSetter.setValue(line.ccyCd, param.ccyCd);
                }
            } else {
                //editLinePriceList(line, map);
                ZYPEZDItemValueSetter.setValue(line.prcCatgCd, map.get("PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(line.ccyCd_PC, map.get("CCY_CD"));
                if (!ZYPCommonFunc.hasValue(line.ccyCd)) {
                    ZYPEZDItemValueSetter.setValue(line.ccyCd, map.get("CCY_CD"));
                }
            }
        }
        return false;
    }

    private List<Map<String, String>> getLinePriceList(NWZC157001PMsg param, NWZC157002PMsg line) {
        // set query parameter
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("PrcGrpTrgtAttrbCd_14", PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER);
        mapParam.put("PrcGrpTrgtAttrbCd_15", PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME);
        mapParam.put("PrcGrpTrgtAttrbCd_16", PRC_GRP_TRGT_ATTRB.SIC_CODE);
        mapParam.put("PrcGrpTrgtAttrbCd_17", PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER);
        mapParam.put("PrcGrpTrgtAttrbCd_18", PRC_GRP_TRGT_ATTRB.CLASSIFICATION);
        mapParam.put("PrcGrpTrgtAttrbCd_19", PRC_GRP_TRGT_ATTRB.CATEGORY);
        mapParam.put("PrcGrpTrgtAttrbCd_20", PRC_GRP_TRGT_ATTRB.DEALER_CODE);
        mapParam.put("PrcGrpTrgtAttrbCd_21", PRC_GRP_TRGT_ATTRB.CSMP_NUMBER);
        mapParam.put("PrcGrpTrgtAttrbCd_22", PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER);
        mapParam.put("PrcGrpTrgtAttrbCd_24", PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER);
        mapParam.put("PrcGrpTrgtAttrbCd_25", PRC_GRP_TRGT_ATTRB.ORDER_CATEGORY);
        mapParam.put("PrcGrpTrgtAttrbCd_26", PRC_GRP_TRGT_ATTRB.ORDER_TYPE);
        mapParam.put("PrcGrpTrgtAttrbCd_27", PRC_GRP_TRGT_ATTRB.LINE_CATEGORY_TYPE);
        // QC#18789 add start
        mapParam.put("PrcGrpTrgtAttrbCd_28", PRC_GRP_TRGT_ATTRB.CHANNEL);
        // QC#18789 add end

        mapParam.put("prcGrpOpCd01", PRC_GRP_OP.EQ);
        mapParam.put("prcGrpOpCd02", PRC_GRP_OP.NOT_EQ);
        mapParam.put("prcGrpOpCd03", PRC_GRP_OP.BETWEEN);
        mapParam.put("prcGrpOpCd04", PRC_GRP_OP.LIKE);

        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("prcGrpTpCdCust", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        mapParam.put("prcGrpTpCdTran", PRC_GRP_TP.ORDER_CATEGORY_OR_REASON);
        mapParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("prcBaseDt", param.prcBaseDt.getValue());
        mapParam.put("prcCtxTpCd", param.prcCtxTpCd.getValue());
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("pubFlg_Y", ZYPConstant.FLG_ON_Y);
        mapParam.put("pubFlg_N", ZYPConstant.FLG_OFF_N);
        if (!S21StringUtil.isEquals(param.lineBizTpCd.getValue(), LINE_BIZ_TP.ALL)) {
            mapParam.put("lineBizTpCd", param.lineBizTpCd.getValue());
        }
        mapParam.put("coaChCd01", param.coaChCd.getValue());
        mapParam.put("coaBrCd", line.coaBrCd.getValue());
        mapParam.put("ccyCd", param.ccyCd.getValue());
        mapParam.put("defPrcCatgCd", param.defPrcCatgCd.getValue());

        mapParam.put("dsAcctNum", param.dsAcctNum.getValue());
        mapParam.put("dsAcctNm", param.dsAcctNm.getValue());
        mapParam.put("dsCustSicCd", param.dsCustSicCd.getValue());
        mapParam.put("locNum", param.locNum.getValue());
        mapParam.put("dsAcctClsCd", param.dsAcctClsCd.getValue());
        mapParam.put("dsAcctTpCd", param.dsAcctTpCd.getValue());
        mapParam.put("dsAcctDlrCd", param.dsAcctDlrCd.getValue());
        mapParam.put("dsAcctGrpCd", param.dsAcctGrpCd.getValue());
        mapParam.put("csmpNum", line.csmpNum.getValue());
        mapParam.put("dlrRefNum", line.dlrRefNum.getValue());
        mapParam.put("prcContrNum", line.prcContrNum.getValue());
        mapParam.put("dsAcctNum_BL", line.dsAcctNum_BL.getValue());
        mapParam.put("dsAcctNm_BL", line.dsAcctNm_BL.getValue());
        mapParam.put("dsCustSicCd_BL", line.dsCustSicCd_BL.getValue());
        mapParam.put("locNum_BL", line.locNum_BL.getValue());
        mapParam.put("dsAcctClsCd_BL", line.dsAcctClsCd_BL.getValue());
        mapParam.put("dsAcctTpCd_BL", line.dsAcctTpCd_BL.getValue());
        mapParam.put("dsAcctDlrCd_BL", line.dsAcctDlrCd_BL.getValue());
        mapParam.put("dsAcctNum_SH", line.dsAcctNum_SH.getValue());
        mapParam.put("dsAcctNm_SH", line.dsAcctNm_SH.getValue());
        mapParam.put("dsCustSicCd_SH", line.dsCustSicCd_SH.getValue());
        mapParam.put("locNum_SH", line.locNum_SH.getValue());
        mapParam.put("dsAcctClsCd_SH", line.dsAcctClsCd_SH.getValue());
        mapParam.put("dsAcctTpCd_SH", line.dsAcctTpCd_SH.getValue());
        mapParam.put("dsAcctDlrCd_SH", line.dsAcctDlrCd_SH.getValue());
        // QC#18789 add start
        mapParam.put("coaChCd_BL", line.coaChCd_BL.getValue());
        mapParam.put("coaChCd_SH", line.coaChCd_SH.getValue());
        // QC#18789 add end

        mapParam.put("dsOrdCatgCd", param.dsOrdCatgCd.getValue());
        mapParam.put("dsOrdTpCd", param.dsOrdTpCd.getValue());

        List<Map<String, String>> ssmResList =  DataCacheForSSM.getInstance().getLinePrcList(mapParam, ssmBatchClient);

        // get price list
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String prtyNum = null;
        for (Map<String, String> map : ssmResList) {
            if (prtyNum == null) {
                prtyNum = map.get("PRTY_NUM");
            }
            if (prtyNum.equals(map.get("PRTY_NUM"))) {
                list.add(map);
            }
        }
        return list;
    }

    private Map<String, String> getDefaultPriceList(List<Map<String, String>> list) {
        if (list.size() == 1) {
            return list.get(0);
        }

        List<Map<String, String>> wrkList = new ArrayList<Map<String, String>>();
        for (Map<String, String> map : list) {
            if (map.get("PRC_TRX_AUDC_PK") != null) {
                wrkList.add(map);
            }
        }
        if (wrkList.size() == 1) {
            return wrkList.get(0);
        }
        for (Map<String, String> map : list) {
            if (ZYPConstant.FLG_ON_Y.equals(map.get("DEF_PRC_TP_FLG"))) {
                wrkList.add(map);
            }
        }
        if (wrkList.size() == 1) {
            return wrkList.get(0);
        }
        return null;
    }


    private boolean configMatching(NWZC157001PMsg param, NWZC157002PMsg line) {
        List<Map<String, String>> prcListEquipList = getPrcListConfig(param, line);

        if (prcListEquipList == null) {
            return true;
        }
        boolean rtnFlg = isNotMatchPrcListEquipList(param, line, prcListEquipList);
        if (rtnFlg) {
            return true;
        }
        return false;
    }

    private boolean isNotMatchPrcListEquipList(NWZC157001PMsg param, NWZC157002PMsg line, List<Map<String, String>> list) {
        for (Map<String, String> map : list) {
            boolean hitflg = false;
            for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
                line = param.NWZC157002PMsg.no(i);
                if (isExistMdse(map, line)) {
                        hitflg = true;
                        break;
                }
            }
            if (!hitflg) {
                return true;
            }
        }
        return false;
    }

    private boolean isExistMdse(Map<String, String> map, NWZC157002PMsg line) {
        if (ZYPCommonFunc.hasValue(map.get("MDSE_CD"))) {
            if (S21StringUtil.isEquals(line.ordTakeMdseCd.getValue(), map.get("PRC_QLFY_VAL_TXT"))
                    && S21StringUtil.isEquals(line.pkgUomCd.getValue(), map.get("PKG_UOM_CD"))) {
                return true;
            }
        } else {
            if (S21StringUtil.isEquals(line.mdseCd.getValue(), map.get("PRC_QLFY_VAL_TXT"))
                    && S21StringUtil.isEquals(line.pkgUomCd.getValue(), map.get("PKG_UOM_CD"))) {
                return true;
            }
        }
        return false;
    }

    private boolean basePriceDeriversion(S21ApiMessageMap msgMap, NWZC157002PMsg line, ONBATCH_TYPE onBatchType) {

        if (isFrozen(line)) {
            return false;
        }

        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();
        S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
        boolean prcListEquipConfigNumFlg = true;

        String prcListStruTpCd = getPrcListStruTp(param.glblCmpyCd.getValue(), line.prcBaseDt.getValue(), line.prcCatgCd.getValue());
        if (S21StringUtil.isEquals(param.xxModeCd.getValue(), GET_ORDER_ALL)
                && ZYPCommonFunc.hasValue(line.prcListEquipConfigNum)) {
            if (configMatching(param, line)) {
                msgMap.addXxMsgIdWithPrm(NWZM1334W, new String[]{line.prcListEquipConfigNum.getValue().toString()});
                msgMap.flush();
                prcListEquipConfigNumFlg = false;
            }
        } else {
            prcListEquipConfigNumFlg = false;
        }

        List<String> prcCatgList = new ArrayList<String>();
        prcCatgList.add(line.prcCatgCd.getValue());
        List<Map<String, Object>> list = getListPrice(param, line, prcCatgList, prcListStruTpCd, prcListEquipConfigNumFlg, true);
        if (list == null) {
            line.xxErrFlg.setValue(ZYPConstant.FLG_ON_Y);
            return true;
        }
        if (list.isEmpty()) {
            line.xxErrFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (PRC_LIST_STRU_TP.EQUIPMENT.equals(prcListStruTpCd) && !prcListEquipConfigNumFlg) {
                list = getListPrice(param, line, prcCatgList, prcListStruTpCd, prcListEquipConfigNumFlg, false);
                if (list.isEmpty()) {
                    lineMap.addXxMsgIdWithPrm(NWZM1328E, new String[] {line.prcCatgCd.getValue(), line.mdseCd.getValue() });
                    lineMap.flush();
                    return true;
                } else {
                    lineMap.addXxMsgIdWithPrm(NWZM1916W, null);
                    lineMap.flush();
                    return true;
                }
            } else {
                lineMap.addXxMsgIdWithPrm(NWZM1328E, new String[] {line.prcCatgCd.getValue(), line.mdseCd.getValue() });
                lineMap.flush();
                return true;
            }
        } else if (list.size() > 1) {
            line.xxErrFlg.setValue(ZYPConstant.FLG_ON_Y);
            lineMap.addXxMsgIdWithPrm(NWZM1329E, new String[] {line.prcCatgCd.getValue(), line.mdseCd.getValue() });
            lineMap.flush();
            return true;
        }
        if (editBasePriceCalcBase(param, line, list, prcListStruTpCd, onBatchType)) {
            return true;
        }
        return false;
    }

    private List<Map<String, Object>> getListPrice(NWZC157001PMsg param, NWZC157002PMsg line, List<String> prcCatgList, String prcListStruTpCd, boolean prcListEquipConfigNumFlg, boolean configUseFlg) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<String> prcCatgSubList = new ArrayList<String>();
        if (prcCatgList == null || prcCatgList.size() == 0) {
            return list;
        }
        for (String prcCatgCd : prcCatgList) {
            if (PRC_LIST_STRU_TP.EQUIPMENT.equals(prcListStruTpCd)) {
                //Lease Type Check
                if (checkLeaseTp(param, line, prcCatgCd)) {
                    S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
                    lineMap.addXxMsgIdWithPrm(NWZM1932E, new String[] {prcCatgCd });
                    lineMap.flush();
                    return null;
                }
                list = getBasePriceEquipment(param, line, prcCatgCd, line.pkgUomCd.getValue(), prcListEquipConfigNumFlg, configUseFlg);
                if (list.size() == 0) {
                    list = getBasePriceEquipment(param, line, prcCatgCd, line.basePkgUomCd.getValue(), prcListEquipConfigNumFlg, configUseFlg);
                }
            } else if (PRC_LIST_STRU_TP.SERVICE.equals(prcListStruTpCd)) {
                list = getBasePriceService(param, line, prcCatgCd);
            } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTpCd)) {
                list = getBasePriceServiceTier(param, line, prcCatgCd);
            } else if (PRC_LIST_STRU_TP.SERVICE_SPECIAL_CHARGES.equals(prcListStruTpCd)) {
                list = getBasePriceAddCharge(param, line, prcCatgCd);
            } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTpCd)) {
                list = getBasePriceSupplyProgram(param, line, prcCatgCd);
            } else if (PRC_LIST_STRU_TP.QTY_DISCOUNT.equals(prcListStruTpCd)) {
                list = getBasePriceQtyDiscount(param, line, prcCatgCd, line.pkgUomCd.getValue());
                if (list.size() == 0) {
                    list = getBasePriceQtyDiscount(param, line, prcCatgCd, line.basePkgUomCd.getValue());
                }
            }
            if (list.size() == 0) {
                List<String> result = getSubPriceList(param, line, prcCatgCd);
                if (result != null) {
                    prcCatgSubList.addAll(result);
                }
            } else {
                if (configUseFlg) {
                    ZYPEZDItemValueSetter.setValue(line.prcCatgCd, prcCatgCd);
                }
                return list;
            }
        }
        return getListPrice(param, line, prcCatgSubList, prcListStruTpCd, prcListEquipConfigNumFlg, configUseFlg);
    }

    private boolean checkLeaseTp(NWZC157001PMsg param, NWZC157002PMsg line, String prcCatgCd) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("prcCatgCd", prcCatgCd);
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("prcBaseDt", line.prcBaseDt.getValue());

        String prcLeaseTpCd = DataCacheForSSM.getInstance().getPrcLeaseTp(mapParam, ssmBatchClient);

        if (PRC_LEASE_TP.FIXED.equals(prcLeaseTpCd)) {
            if (!ZYPCommonFunc.hasValue(param.prcTermAot)) {
                return true;
            }
        }
        return false;
    }

    private List<Map<String, Object>> getBasePriceEquipment(NWZC157001PMsg param, NWZC157002PMsg line, String prcCatgCd, String pkgUomCd, boolean prcListEquipConfigNumFlg, boolean configUseFlg) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("prcCatgCd", prcCatgCd);
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("prcBaseDt", line.prcBaseDt.getValue());
        mapParam.put("prcQlfyTp01", PRC_QLFY_TP.ITEM_CODE);
        mapParam.put("prcQlfyTp02", PRC_QLFY_TP.MERCHANDISE_TYPE);
        mapParam.put("prcQlfyTp03", PRC_QLFY_TP.PRODUCT_HIERARCHY_1);
        mapParam.put("prcQlfyTp04", PRC_QLFY_TP.PRODUCT_HIERARCHY_2);
        mapParam.put("prcQlfyTp05", PRC_QLFY_TP.PRODUCT_HIERARCHY_3);
        mapParam.put("prcQlfyTp06", PRC_QLFY_TP.PRODUCT_HIERARCHY_4);
        mapParam.put("prcQlfyTp07", PRC_QLFY_TP.PRODUCT_HIERARCHY_5);
        mapParam.put("mdseCd", line.mdseCd.getValue());
        mapParam.put("ordTakeMdseCd", line.ordTakeMdseCd.getValue());
        mapParam.put("coaMdseTpCd", line.coaMdseTpCd.getValue());
        mapParam.put("zerothProdCtrlCd", line.zerothProdCtrlCd.getValue());
        mapParam.put("firstProdCtrlCd", line.firstProdCtrlCd.getValue());
        mapParam.put("scdProdCtrlCd", line.scdProdCtrlCd.getValue());
        mapParam.put("thirdProdCtrlCd", line.thirdProdCtrlCd.getValue());
        mapParam.put("frthProdCtrlCd", line.frthProdCtrlCd.getValue());
        if (configUseFlg) {
            mapParam.put("configUseFlg", ZYPConstant.FLG_ON_Y);
        }
        if (prcListEquipConfigNumFlg) {
            mapParam.put("prcListEquipConfigNum", line.prcListEquipConfigNum.getValue());
        }
        mapParam.put("pkgUomCd", pkgUomCd);
        mapParam.put("prcLeaseTp", PRC_LEASE_TP.FIXED);
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, param.xxRqstFlg_RN.getValue())) {
            mapParam.put("rentalFlg", ZYPConstant.FLG_ON_Y);
        }
        mapParam.put("prcTeamUom", PRC_TERM_UOM.MTH);
        if (ZYPCommonFunc.hasValue(param.prcTermAot)) {
            mapParam.put("prcTermAot", param.prcTermAot.getValue());
        } else {
            mapParam.put("prcTermAot", PRC_TERM_AOT_MAX_VALUE);
        }
        if (CONFIG_CATG.INBOUND.equals(line.configCatgCd.getValue())) {
            mapParam.put("qtyDiscQty", line.ordCustUomQty.getValue().multiply(MINUS));
        } else {
            mapParam.put("qtyDiscQty", line.ordCustUomQty.getValue());
        }

        return DataCacheForSSM.getInstance().getBasePrcEquipment(mapParam, ssmBatchClient);
    }

    private List<Map<String, Object>> getBasePriceService(NWZC157001PMsg param, NWZC157002PMsg line, String prcCatgCd) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("prcCatgCd", prcCatgCd);
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("prcBaseDt", line.prcBaseDt.getValue());
        mapParam.put("prcRateTpCd", line.prcRateTpCd.getValue());
        mapParam.put("mdlId", line.mdlId.getValue());
        mapParam.put("prcMtrPkgPk", line.prcMtrPkgPk.getValue());
        mapParam.put("prcSvcPlnTpCd", line.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", line.prcSvcContrTpCd.getValue());
        mapParam.put("mtrLbCd", line.mtrLbCd.getValue());
        mapParam.put("prcListBandCd", line.prcListBandCd.getValue());
        mapParam.put("mdseCd", line.mdseCd.getValue());
        mapParam.put("ordTakeMdseCd", line.ordTakeMdseCd.getValue());
        mapParam.put("termFromMthAot", line.termFromMthAot.getValue());
        mapParam.put("termThruMthAot", line.termThruMthAot.getValue());

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBasePriceService", mapParam);
    }

    private List<Map<String, Object>> getBasePriceServiceTier(NWZC157001PMsg param, NWZC157002PMsg line, String prcCatgCd) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("prcCatgCd", prcCatgCd);
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("prcBaseDt", line.prcBaseDt.getValue());
        mapParam.put("mdlId", line.mdlId.getValue());
        mapParam.put("prcMtrPkgPk", line.prcMtrPkgPk.getValue());
        mapParam.put("prcSvcPlnTpCd", line.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", line.prcSvcContrTpCd.getValue());
        mapParam.put("mtrLbCd", line.mtrLbCd.getValue());
        mapParam.put("prcListBandCd", line.prcListBandCd.getValue());
        mapParam.put("mdseCd", line.mdseCd.getValue());
        mapParam.put("ordTakeMdseCd", line.ordTakeMdseCd.getValue());
        mapParam.put("termFromMthAot", line.termFromMthAot.getValue());
        mapParam.put("termThruMthAot", line.termThruMthAot.getValue());

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBasePriceServiceTier", mapParam);
    }

    private List<Map<String, Object>> getBasePriceAddCharge(NWZC157001PMsg param, NWZC157002PMsg line, String prcCatgCd) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("prcCatgCd", prcCatgCd);
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("prcBaseDt", line.prcBaseDt.getValue());
        mapParam.put("mdseCd", line.mdseCd.getValue());
        mapParam.put("ordTakeMdseCd", line.ordTakeMdseCd.getValue());
        mapParam.put("mdlId", line.mdlId.getValue());

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBasePriceAddCharge", mapParam);
    }

    private List<Map<String, Object>> getBasePriceSupplyProgram(NWZC157001PMsg param, NWZC157002PMsg line, String prcCatgCd) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("prcCatgCd", prcCatgCd);
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("prcBaseDt", line.prcBaseDt.getValue());
        mapParam.put("mdlId", line.mdlId.getValue());
        mapParam.put("prcMtrPkgPk", line.prcMtrPkgPk.getValue());
        mapParam.put("prcSvcPlnTpCd", line.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", line.prcSvcContrTpCd.getValue());
        mapParam.put("mtrLbCd", line.mtrLbCd.getValue());
        mapParam.put("prcListBandCd", line.prcListBandCd.getValue());
        mapParam.put("mdseCd", line.mdseCd.getValue());
        mapParam.put("ordTakeMdseCd", line.ordTakeMdseCd.getValue());
        mapParam.put("termFromMthAot", line.termFromMthAot.getValue());
        mapParam.put("termThruMthAot", line.termThruMthAot.getValue());

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBasePriceSupplyProgram", mapParam);
    }

    private List<Map<String, Object>> getBasePriceQtyDiscount(NWZC157001PMsg param, NWZC157002PMsg line, String prcCatgCd, String pkgUomCd) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("prcCatgCd", prcCatgCd);
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("prcBaseDt", line.prcBaseDt.getValue());
        mapParam.put("mdseCd", line.mdseCd.getValue());
        mapParam.put("ordTakeMdseCd", line.ordTakeMdseCd.getValue());
        if (CONFIG_CATG.INBOUND.equals(line.configCatgCd.getValue())) {
            mapParam.put("qtyDiscQty", line.ordCustUomQty.getValue().multiply(MINUS));
        } else {
            mapParam.put("qtyDiscQty", line.ordCustUomQty.getValue());
        }
        mapParam.put("pkgUomCd", pkgUomCd);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBaseQtyDiscount", mapParam);
    }

    private boolean editBasePriceCalcBase(NWZC157001PMsg param, NWZC157002PMsg line, List<Map<String, Object>> list, String prcListStruTpCd, ONBATCH_TYPE onBatchType) {
        BigDecimal localBasePrice = null;
        BigDecimal localMinPrice = null;
        BigDecimal localMaxPrice = null;
        Map<String, Object> map = new HashMap<String, Object>();
        S21ApiMessageMap lineMap = new S21ApiMessageMap(line);

        if (list != null && list.size() > 0) {
            map = list.get(0);
        }
        BigDecimal num = null;
        if (PRC_LIST_STRU_TP.EQUIPMENT.equals(prcListStruTpCd)) {
            if (map.get("PRC_FMLA_PK") != null) {
                // call FormulaAPI
                NWZC170001PMsg pMsg = new NWZC170001PMsg();
                pMsg = callFromulaApi(param, line, map, onBatchType);
                if (S21ApiUtil.isXxMsgId(pMsg)) {
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                    S21ApiMessage msg = msgList.get(0);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    lineMap.addXxMsgIdWithPrm(msgId, msgPrms);
                    lineMap.flush();
                    return true;
                }
                num = pMsg.xxCalcPrcAmt.getValue();
            } else {
                num = (BigDecimal) map.get("PRC_LIST_EQUIP_PRC_AMT");
            }
            if (ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_RN.getValue())
                    && ZYPCommonFunc.hasValue((BigDecimal) map.get("MLY_PMT_AMT"))) {
                num = (BigDecimal) map.get("MLY_PMT_AMT");
            }
            if (S21StringUtil.isEquals(line.pkgUomCd.getValue(), (String) map.get("PKG_UOM_CD"))) {
                if (!ZYPCommonFunc.hasValue(line.inEachQty) || BigDecimal.ZERO.compareTo(line.inEachQty.getValue()) == 0) {
                    lineMap.addXxMsgIdWithPrm(NWZM1533E, null);
                    lineMap.flush();
                    return true;
                }
                num = num.divide(line.inEachQty.getValue(), SCALE_DB, BigDecimal.ROUND_HALF_UP);
                localBasePrice = round(num, getDealCcyDigit(param.glblCmpyCd.getValue(), line.ccyCd.getValue()));
                if (num.compareTo(localBasePrice) != 0) {
                    lineMap.addXxMsgIdWithPrm(NWZM1533E, null);
                    lineMap.flush();
                    return true;
                }
            } else {
                localBasePrice = round(num, getDealCcyDigit(param.glblCmpyCd.getValue(), line.ccyCd.getValue()));
            }
            localMinPrice = (BigDecimal) map.get("MIN_PRC_AMT");
            localMaxPrice = (BigDecimal) map.get("MAX_PRC_AMT");
        } else if (PRC_LIST_STRU_TP.SERVICE.equals(prcListStruTpCd)) {
            localBasePrice = (BigDecimal) map.get("BASE_AMT");
            localMinPrice = (BigDecimal) map.get("MIN_BASE_AMT");
            localMaxPrice = (BigDecimal) map.get("MAX_BASE_AMT");
            ZYPEZDItemValueSetter.setValue(line.minCopyVolCnt, (BigDecimal) map.get("MIN_COPY_VOL_CNT"));
            ZYPEZDItemValueSetter.setValue(line.maxCopyVolCnt, (BigDecimal) map.get("MAX_COPY_VOL_CNT"));
            ZYPEZDItemValueSetter.setValue(line.cpcAmtRate, (BigDecimal) map.get("CPC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(line.minCpcAmtRate, (BigDecimal) map.get("MIN_CPC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(line.maxCpcAmtRate, (BigDecimal) map.get("MAX_CPC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(line.mtrMultRate, (BigDecimal) map.get("MTR_MULT_RATE"));
        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTpCd)) {
            localBasePrice = (BigDecimal) map.get("BASE_AMT");
            localMinPrice = (BigDecimal) map.get("MIN_BASE_AMT");
            localMaxPrice = (BigDecimal) map.get("MAX_BASE_AMT");
            ZYPEZDItemValueSetter.setValue(line.minCopyVolCnt, (BigDecimal) map.get("MIN_COPY_VOL_CNT"));
            ZYPEZDItemValueSetter.setValue(line.maxCopyVolCnt, (BigDecimal) map.get("MAX_COPY_VOL_CNT"));
            ZYPEZDItemValueSetter.setValue(line.cpcAmtRate, (BigDecimal) map.get("CPC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(line.minCpcAmtRate, (BigDecimal) map.get("MIN_CPC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(line.maxCpcAmtRate, (BigDecimal) map.get("MAX_CPC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(line.mtrMultRate, (BigDecimal) map.get("MTR_MULT_RATE"));
        } else if (PRC_LIST_STRU_TP.SERVICE_SPECIAL_CHARGES.equals(prcListStruTpCd)) {
            localBasePrice = (BigDecimal) map.get("ADDL_CHRG_PRC_AMT");
        } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTpCd)) {
            localBasePrice = (BigDecimal) map.get("TOT_BASE_AMT");
            ZYPEZDItemValueSetter.setValue(line.minCopyVolCnt, (BigDecimal) map.get("MIN_COPY_VOL_CNT"));
            ZYPEZDItemValueSetter.setValue(line.maxCopyVolCnt, (BigDecimal) map.get("MAX_COPY_VOL_CNT"));
            ZYPEZDItemValueSetter.setValue(line.cpcAmtRate, (BigDecimal) map.get("CPC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(line.minCpcAmtRate, (BigDecimal) map.get("MIN_CPC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(line.maxCpcAmtRate, (BigDecimal) map.get("MAX_CPC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(line.splyBaseAmt, (BigDecimal) map.get("SPLY_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(line.splyAgmtPlnPk, (BigDecimal) map.get("SPLY_AGMT_PLN_PK"));
        } else if (PRC_LIST_STRU_TP.QTY_DISCOUNT.equals(prcListStruTpCd)) {
            num = (BigDecimal) map.get("QTY_DISC_PRC_AMT");
            num = num.divide(line.inEachQty.getValue(), SCALE_DB, BigDecimal.ROUND_HALF_UP);
            localBasePrice = round(num, SCALE_AMT);

            if (num.compareTo(localBasePrice) != 0) {
                lineMap.addXxMsgIdWithPrm(NWZM1533E, null);
                lineMap.flush();
                return true;
            }
        }
//        if (!S21StringUtil.isEquals(line.ccyCd.getValue(), line.ccyCd_PC.getValue())) {
//            NWXC100001EditPriceAmountByPmtTermInfo editPrcAmtByPmtTermInfo = callNWXC100001EditPriceAmountByPmtTerm(param, line, localBasePrice);
//            List<String> errMsgIdList = editPrcAmtByPmtTermInfo.getXxMsgIdList();
//            if (errMsgIdList != null && !errMsgIdList.isEmpty()) {
//                lineMap.addXxMsgIdWithPrm(errMsgIdList.get(0), null);
//                lineMap.flush();
//                return true;
//            }
//            localBasePrice = editPrcAmtByPmtTermInfo.getUnitPrcData().getDealNetUnitPrcAmt();
//            if (localBasePrice == null) {
//                localBasePrice = BigDecimal.ZERO;
//            }
//        }
        NWZC157003PMsg base = getBasePrice(line);
        if (base == null) {
            base = line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount());
            line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        }
        if (!ZYPConstant.FLG_ON_Y.equals(base.prcCondManEntryFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(base.trxLineNum, line.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(base.trxLineSubNum, line.trxLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(base.configCatgCd, line.configCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(base.prcCondTpCd, PRC_COND_TP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(base.prcCondTpDescTxt, getPrcCondTpDescTxt(param.glblCmpyCd.getValue(), PRC_COND_TP.BASE_PRICE));
            ZYPEZDItemValueSetter.setValue(base.prcDtlGrpCd, PRC_DTL_GRP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(base.prcCatgCd, line.prcCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(base.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(base.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(base.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(base.pkgUomCd, line.pkgUomCd.getValue());
            ZYPEZDItemValueSetter.setValue(base.prcCondUnitCd, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(base.autoPrcCcyCd, line.ccyCd.getValue());
            ZYPEZDItemValueSetter.setValue(base.autoPrcAmtRate, localBasePrice);
            ZYPEZDItemValueSetter.setValue(base.maxPrcAmtRate, localMaxPrice);
            ZYPEZDItemValueSetter.setValue(base.minPrcAmtRate, localMinPrice);
            ZYPEZDItemValueSetter.setValue(base.calcPrcAmtRate, multiply(param.glblCmpyCd.getValue(), localBasePrice, line.ordQty.getValue(), line.ccyCd.getValue()));
            ZYPEZDItemValueSetter.setValue(base.unitPrcAmt, localBasePrice);
            ZYPEZDItemValueSetter.setValue(base.specCondPrcPk, getSpecCondPrcPk(param.glblCmpyCd.getValue(), base.prcCondTpCd.getValue()));
            // QC#9700  2018/09/03 Add Start
            ZYPEZDItemValueSetter.setValue(base.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
            base.prcRulePrcdPk.clear();
            // QC#9700  2018/09/03 Add End
            base.frtPerWtPk.clear();
        } else {
            BigDecimal amountRate = getAmountRate(base);
            ZYPEZDItemValueSetter.setValue(base.calcPrcAmtRate, multiply(param.glblCmpyCd.getValue(), amountRate, line.ordQty.getValue(), line.ccyCd.getValue()));
            ZYPEZDItemValueSetter.setValue(base.unitPrcAmt, amountRate);
            // QC#9700  2018/09/03 Add Start
            ZYPEZDItemValueSetter.setValue(base.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
            base.prcRulePrcdPk.clear();
            // QC#9700  2018/09/03 Add End
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(line.prcCondManEntryFlg.getValue()) && ZYPCommonFunc.hasValue(line.xxUnitPrc)) {
            BigDecimal discAmt = localBasePrice.subtract(line.xxUnitPrc.getValue());
            if (BigDecimal.ZERO.compareTo(discAmt) != 0) {
                NWZC157003PMsg calcBase = line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount());
                ZYPEZDItemValueSetter.setValue(calcBase.trxLineNum, line.trxLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(calcBase.trxLineSubNum, line.trxLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(calcBase.configCatgCd, line.configCatgCd.getValue());
                ZYPEZDItemValueSetter.setValue(calcBase.prcCondTpCd, PRC_COND_TP.MANUAL_PRICE);
                ZYPEZDItemValueSetter.setValue(calcBase.prcCondTpDescTxt, getPrcCondTpDescTxt(param.glblCmpyCd.getValue(), PRC_COND_TP.MANUAL_PRICE));
                ZYPEZDItemValueSetter.setValue(calcBase.prcDtlGrpCd, PRC_DTL_GRP.DISCOUNT);
                ZYPEZDItemValueSetter.setValue(calcBase.prcCatgCd, line.prcCatgCd.getValue());
                ZYPEZDItemValueSetter.setValue(calcBase.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(calcBase.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(calcBase.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(calcBase.pkgUomCd, line.pkgUomCd.getValue());
                ZYPEZDItemValueSetter.setValue(calcBase.prcCondUnitCd, PRC_COND_UNIT.AMT);
                ZYPEZDItemValueSetter.setValue(calcBase.autoPrcCcyCd, line.ccyCd.getValue());
                ZYPEZDItemValueSetter.setValue(calcBase.autoPrcAmtRate, discAmt);
                ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, discAmt.multiply(line.ordQty.getValue()));
                ZYPEZDItemValueSetter.setValue(calcBase.unitPrcAmt, discAmt);
                ZYPEZDItemValueSetter.setValue(calcBase.specCondPrcPk, getSpecCondPrcPk(param.glblCmpyCd.getValue(), PRC_COND_TP.MANUAL_PRICE));
                // QC#9700  2018/09/03 Add Start
                ZYPEZDItemValueSetter.setValue(calcBase.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
                calcBase.prcRulePrcdPk.clear();
                // QC#9700  2018/09/03 Add End
                line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
            }
        }

        return false;
    }

    private boolean rulePriceDeriversion(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        //boolean negoFlg = false;
        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();
        NWXC014001PMsg pMsg = callRuleDerivationApi(param, onBatchType);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
            msgMap.flush();
            return true;
        }
        for (int i = 0; i < pMsg.NWXC014002PMsg.getValidCount(); i++) {
            NWXC014002PMsg lineMsg = pMsg.NWXC014002PMsg.no(i);
            NWZC157002PMsg line = param.NWZC157002PMsg.no(i);
            if (S21ApiUtil.isXxMsgId(lineMsg)) {
                S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(lineMsg);
                S21ApiMessage msg = msgList.get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                lineMap.addXxMsgIdWithPrm(msgId, msgPrms);
                lineMap.flush();
                return true;
            }
            ZYPTableUtil.clear(line.NWZC157003PMsg);
            for (int j = 0; j < lineMsg.NWXC014003PMsg.getValidCount(); j++) {
                EZDMsg.copy(lineMsg.NWXC014003PMsg.no(j), null, line.NWZC157003PMsg.no(j), null);
            }
            line.NWZC157003PMsg.setValidCount(lineMsg.NWXC014003PMsg.getValidCount());
        }

        NWXC014004PMsg ruleLine = null;
        return false;
    }

//    private void editHdrRuleAdjust(S21ApiMessageMap msgMap, NWXC014004PMsg ruleLine) {
//        String prcDtlGrpCd = null;
//        boolean frozenFlg = false;
//        BigDecimal amount = BigDecimal.ZERO;
//
//        BigDecimal specCondPrcPk = BigDecimal.ZERO;
//        BigDecimal rate = BigDecimal.ZERO;
//        BigDecimal totalBaseAmt = BigDecimal.ZERO;
//        BigDecimal frozeBaseAmt = BigDecimal.ZERO;
//        BigDecimal frozeTargetAmt = BigDecimal.ZERO;
//        BigDecimal numerAmt = BigDecimal.ZERO;
//        BigDecimal denomAmt = BigDecimal.ZERO;
//        BigDecimal calcPrcAmt = BigDecimal.ZERO;
//        BigDecimal basePrcAmt = BigDecimal.ZERO;
//        BigDecimal unitCalcPrice = BigDecimal.ZERO;
//        BigDecimal amt = BigDecimal.ZERO;
//        BigDecimal amtTotal = BigDecimal.ZERO;
//
//        NWZC157002PMsg line = null;
//        NWZC157003PMsg calcBase = null;
//
//        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();
//        specCondPrcPk = ruleLine.specCondPrcPk.getValue();
//
//        if (PRC_RULE_MOD_TP.CHARGES.equals(ruleLine.prcRuleModTpCd.getValue())) {
//            prcDtlGrpCd = PRC_DTL_GRP.FREIGHT;
//        } else {
//            prcDtlGrpCd = PRC_DTL_GRP.DISCOUNT;
//        }
//        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
//            line = param.NWZC157002PMsg.no(i);
//            frozenFlg = isFrozen(line);
//            amount = getBasePriceAmount(param.glblCmpyCd.getValue(), line);
//            totalBaseAmt = add(totalBaseAmt, amount);
//            if (frozenFlg) {
//                frozeBaseAmt = add(frozeBaseAmt, amount);
//            }
//
//            for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
//                calcBase = line.NWZC157003PMsg.no(j);
//                if (frozenFlg && prcDtlGrpCd.equals(calcBase.prcDtlGrpCd.getValue()) && isEquals(calcBase.specCondPrcPk.getValue(), specCondPrcPk)) {
//                    frozeTargetAmt = add(frozeTargetAmt, multiply(param.glblCmpyCd.getValue(), calcBase.unitPrcAmt.getValue(), line.ordQty.getValue(), line.ccyCd.getValue()));
//                }
//            }
//        }
//
//        denomAmt = totalBaseAmt.subtract(frozeBaseAmt);
//        if (denomAmt.compareTo(BigDecimal.ZERO) == 0) {
//            return;
//        }
//
//        calcPrcAmt = ruleLine.xxCalcPrcAmt.getValue().subtract(frozeTargetAmt);
//
//        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
//            line = param.NWZC157002PMsg.no(i);
//            if (isFrozen(line)) {
//                continue;
//            }
//            basePrcAmt = getBasePriceAmount(param.glblCmpyCd.getValue(), line);
//            numerAmt = round(basePrcAmt, getDealCcyDigit(param.glblCmpyCd.getValue(), line.ccyCd.getValue()));
//            rate = numerAmt.divide(denomAmt, RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
//            unitCalcPrice = divide(param.glblCmpyCd.getValue(), calcPrcAmt.multiply(rate), line.ordQty.getValue(), line.ccyCd.getValue());
////            if (!S21StringUtil.isEquals(line.ccyCd.getValue(), line.ccyCd_PC.getValue())) {
////                NWXC100001EditPriceAmountByPmtTermInfo editPrcAmtByPmtTermInfo = callNWXC100001EditPriceAmountByPmtTerm(param, line, unitCalcPrice);
////                List<String> errMsgIdList = editPrcAmtByPmtTermInfo.getXxMsgIdList();
////                if (errMsgIdList != null && !errMsgIdList.isEmpty()) {
////                    S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
////                    lineMap.addXxMsgIdWithPrm(errMsgIdList.get(0), null);
////                    lineMap.flush();
////                    return;
////                }
////                unitCalcPrice = editPrcAmtByPmtTermInfo.getUnitPrcData().getDealNetUnitPrcAmt();
////            }
//            amt = editCalcBase(param, line, prcDtlGrpCd, unitCalcPrice, specCondPrcPk);
//            amtTotal = amtTotal.add(amt);
//        }
//        if (!ajustFraction(param, prcDtlGrpCd, specCondPrcPk, amtTotal, calcPrcAmt)) {
//            msgMap.addXxMsgIdWithPrm(NWZM1355W, new String[] {"Pricing Calcuration" });
//            msgMap.flush();
//        }
//
//        return;
//    }

//    private boolean ajustFraction(NWZC157001PMsg param, String prcDtlGrpCd, BigDecimal specCondPrcPk, BigDecimal totalAmt, BigDecimal targetAmt) {
//        if (totalAmt.compareTo(targetAmt) != 0) {
//            BigDecimal fraction = targetAmt.subtract(totalAmt);
//
//            for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
//                NWZC157002PMsg line = param.NWZC157002PMsg.no(i);
//                if (!isFrozen(line) && line.ordQty.getValue().abs().compareTo(BigDecimal.ONE) == 0) {
//                    ajustFractionEdit(line, prcDtlGrpCd, specCondPrcPk, fraction);
//                    fraction = BigDecimal.ZERO;
//                    break;
//                }
//            }
//            if (fraction.compareTo(BigDecimal.ZERO) != 0) {
//                return false;
//            }
//        }
//        return true;
//    }

//    private void ajustFractionEdit(NWZC157002PMsg line, String prcDtlGrpCd, BigDecimal specCondPrcPk, BigDecimal fraction) {
//        NWZC157003PMsg calcBase = null;
//        // find discount row
//        for (int i = 0; i < line.NWZC157003PMsg.getValidCount(); i++) {
//            calcBase = line.NWZC157003PMsg.no(i);
//            if (prcDtlGrpCd.equals(calcBase.prcDtlGrpCd.getValue()) && isEquals(specCondPrcPk, calcBase.specCondPrcPk.getValue())) {
//                ZYPEZDItemValueSetter.setValue(calcBase.autoPrcAmtRate, calcBase.autoPrcAmtRate.getValue().add(fraction));
//                ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, calcBase.calcPrcAmtRate.getValue().add(fraction));
//                ZYPEZDItemValueSetter.setValue(calcBase.unitPrcAmt, calcBase.unitPrcAmt.getValue().add(fraction));
//                break;
//            }
//        }
//
//    }


//    private BigDecimal editCalcBase(NWZC157001PMsg param, NWZC157002PMsg line, String prcDtlGrpCd, BigDecimal unitCalcPrice, BigDecimal specCondPrcPk) {
//        NWZC157003PMsg calcBase = null;
//        NWZC157003PMsg base = null;
//        for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
//            base = line.NWZC157003PMsg.no(j);
//            if (prcDtlGrpCd.equals(base.prcDtlGrpCd.getValue())
//                    && isEquals(base.specCondPrcPk.getValue(), specCondPrcPk)) {
//                calcBase = base;
//            }
//        }
//        if (calcBase == null) {
//            calcBase = line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount());
//            line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
//        }
//        ZYPEZDItemValueSetter.setValue(calcBase.trxLineNum, line.trxLineNum.getValue());
//        ZYPEZDItemValueSetter.setValue(calcBase.trxLineSubNum, line.trxLineSubNum.getValue());
//        ZYPEZDItemValueSetter.setValue(calcBase.configCatgCd, line.configCatgCd.getValue());
//        ZYPEZDItemValueSetter.setValue(calcBase.prcDtlGrpCd, prcDtlGrpCd);
//        ZYPEZDItemValueSetter.setValue(calcBase.prcCatgCd, line.prcCatgCd.getValue());
//        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(calcBase.pkgUomCd, line.pkgUomCd.getValue());
//        ZYPEZDItemValueSetter.setValue(calcBase.prcCondUnitCd, PRC_COND_UNIT.AMT);
//        ZYPEZDItemValueSetter.setValue(calcBase.autoPrcCcyCd, line.ccyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(calcBase.autoPrcAmtRate, unitCalcPrice);
//        ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, unitCalcPrice);
//        ZYPEZDItemValueSetter.setValue(calcBase.unitPrcAmt, unitCalcPrice.multiply(line.ordQty.getValue()));
//        ZYPEZDItemValueSetter.setValue(calcBase.specCondPrcPk, specCondPrcPk);
//        return calcBase.unitPrcAmt.getValue();
//    }
    private boolean summarize(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        BigDecimal amountRate = BigDecimal.ZERO;
        BigDecimal basePrice = BigDecimal.ZERO;
        BigDecimal baseAmt = BigDecimal.ZERO;
        BigDecimal discPrice = BigDecimal.ZERO;
        BigDecimal discAmt = BigDecimal.ZERO;
        BigDecimal totFrtAmt = BigDecimal.ZERO;
        BigDecimal totFrtSubTotAmt = BigDecimal.ZERO;
        BigDecimal unitFrtAmt = BigDecimal.ZERO;
        BigDecimal totTaxAmt = BigDecimal.ZERO;
        String ccy = null;
        NWZC157002PMsg line = null;
        NWZC157003PMsg calcBase = null;
        NWZC157004PMsg sumLine = null;

        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();
        ZYPTableUtil.clear(param.NWZC157004PMsg);
        String frtMdseCd = ZYPCodeDataUtil.getVarCharConstValue(TAX_FRT_ITEM_CD, param.glblCmpyCd.getValue());
        String svcAllocTrxTpNm = ZYPCodeDataUtil.getVarCharConstValue(DEFAULT_TAX_TRX_TP, param.glblCmpyCd.getValue()); // For Performance QC#11618 Add

        // Set Manual Data
        // for (NWZC157003PMsg data : manualList) {
        //     line = getLine(param, data.trxLineNum.getValue(), data.trxLineSubNum.getValue(), data.configCatgCd.getValue());
        //     if (line == null) {
        //         continue;
        //     }
        //     calcBase = line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount());
        //     EZDMsg.copy(data, null, calcBase, null);
        //     line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        // }

        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
            line = param.NWZC157002PMsg.no(i);
            sumLine = param.NWZC157004PMsg.no(i);
            ccy = line.ccyCd.getValue();
            baseAmt = BigDecimal.ZERO;
            basePrice = BigDecimal.ZERO;
            BigDecimal unitPriceAmount = BigDecimal.ZERO;
            discPrice = BigDecimal.ZERO;
            discAmt = BigDecimal.ZERO;
            totFrtAmt = BigDecimal.ZERO;
            totFrtSubTotAmt = BigDecimal.ZERO;
            unitFrtAmt = BigDecimal.ZERO;
            totTaxAmt = BigDecimal.ZERO;

            for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                calcBase = line.NWZC157003PMsg.no(j);
                amountRate = getAmountRate(calcBase);
                if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManDelFlg.getValue())) {
                    continue;
                }
                //Base Price
                if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    basePrice = add(basePrice, calcBase.unitPrcAmt.getValue());
                    ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, multiply(param.glblCmpyCd.getValue(), calcBase.unitPrcAmt.getValue(), line.ordQty.getValue(), line.ccyCd.getValue()));
                    baseAmt = add(baseAmt, calcBase.calcPrcAmtRate.getValue());
                }
            }

            for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                calcBase = line.NWZC157003PMsg.no(j);
                amountRate = getAmountRate(calcBase);
                if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManDelFlg.getValue())) {
                    continue;
                }
                // Discount
                if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())) {
                    if (!ZYPCommonFunc.hasValue(calcBase.prcCondTpCd)) {
                        discPrice = add(discPrice, calcBase.unitPrcAmt.getValue());
                        ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, multiply(param.glblCmpyCd.getValue(), calcBase.unitPrcAmt.getValue(), line.ordQty.getValue(), line.ccyCd.getValue()));
                        discAmt = add(discAmt, calcBase.calcPrcAmtRate.getValue());
                    } else {
                        basePrice = subtract(basePrice, calcBase.unitPrcAmt.getValue());
                        ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, multiply(param.glblCmpyCd.getValue(), calcBase.unitPrcAmt.getValue(), line.ordQty.getValue(), line.ccyCd.getValue()));
                        baseAmt = subtract(baseAmt, calcBase.calcPrcAmtRate.getValue());
                    }
                }
                if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(calcBase.prcDtlGrpCd.getValue())) {
                    discAmt = add(discAmt, calcBase.calcPrcAmtRate.getValue());
                }
                // Freight
                if (PRC_DTL_GRP.FREIGHT.equals(calcBase.prcDtlGrpCd.getValue())) {
                    unitFrtAmt = add(unitFrtAmt, calcBase.unitPrcAmt.getValue());
                    ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, multiply(param.glblCmpyCd.getValue(), calcBase.unitPrcAmt.getValue(), line.ordQty.getValue(), line.ccyCd.getValue()));
                    totFrtAmt = add(totFrtAmt, calcBase.calcPrcAmtRate.getValue());
                }
                if (PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(calcBase.prcDtlGrpCd.getValue())) {
                    unitFrtAmt = add(unitFrtAmt, calcBase.unitPrcAmt.getValue());
                    totFrtAmt = add(totFrtAmt, calcBase.calcPrcAmtRate.getValue());
                }
                // QC#21841 2018/05/21 Add Start
                if (PRC_DTL_GRP.HANDLING_FEE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    unitFrtAmt = add(unitFrtAmt, calcBase.unitPrcAmt.getValue());
                    ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, multiply(param.glblCmpyCd.getValue(), calcBase.unitPrcAmt.getValue(), line.ordQty.getValue(), line.ccyCd.getValue()));
                    totFrtAmt = add(totFrtAmt, calcBase.calcPrcAmtRate.getValue());
                }
                if (PRC_DTL_GRP.FUEL_SURCHARGE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    unitFrtAmt = add(unitFrtAmt, calcBase.unitPrcAmt.getValue());
                    totFrtAmt = add(totFrtAmt, calcBase.calcPrcAmtRate.getValue());
                }
                if (PRC_DTL_GRP.SHIPPING_FEE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    unitFrtAmt = add(unitFrtAmt, calcBase.unitPrcAmt.getValue());
                    ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, multiply(param.glblCmpyCd.getValue(), calcBase.unitPrcAmt.getValue(), line.ordQty.getValue(), line.ccyCd.getValue()));
                    totFrtAmt = add(totFrtAmt, calcBase.calcPrcAmtRate.getValue());
                }
                // QC#27479 2018/08/03 Add Start
                if (PRC_DTL_GRP.RESTOCKING_FEE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    unitFrtAmt = add(unitFrtAmt, calcBase.unitPrcAmt.getValue());
                    ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, multiply(param.glblCmpyCd.getValue(), calcBase.unitPrcAmt.getValue(), line.ordQty.getValue(), line.ccyCd.getValue()));
                    totFrtAmt = add(totFrtAmt, calcBase.calcPrcAmtRate.getValue());
                }
                // QC#27479 2018/08/03 Add End
                if (PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(calcBase.prcDtlGrpCd.getValue())) {
                    unitFrtAmt = add(unitFrtAmt, calcBase.unitPrcAmt.getValue());
                    totFrtAmt = add(totFrtAmt, calcBase.calcPrcAmtRate.getValue());
                }
                // QC#21841 2018/05/21 Add End
            }

            ZYPEZDItemValueSetter.setValue(sumLine.trxLineNum, line.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(sumLine.trxLineSubNum, line.trxLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(sumLine.configCatgCd, line.configCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(sumLine.xxTotBaseAmt, baseAmt);
            ZYPEZDItemValueSetter.setValue(sumLine.xxTotDiscAmt, discAmt);
            sumLine.xxTotSpclPrcAmt.clear();
            sumLine.xxTotNetDiscAmt.clear();
            ZYPEZDItemValueSetter.setValue(sumLine.xxUnitNetPrcAmt, subtract(basePrice, discPrice));
            ZYPEZDItemValueSetter.setValue(sumLine.xxUnitGrsPrcAmt, basePrice);
            ZYPEZDItemValueSetter.setValue(sumLine.xxTotNetPrcAmt, subtract(baseAmt, discAmt));
            ZYPEZDItemValueSetter.setValue(sumLine.xxGrsAmt, baseAmt);
            ZYPEZDItemValueSetter.setValue(sumLine.xxUnitFrtAmt, unitFrtAmt);
            ZYPEZDItemValueSetter.setValue(sumLine.xxTotFrtAmt, totFrtAmt);
            sumLine.xxUnitSpclChrgAmt.clear();
            sumLine.xxTotSpclChrgAmt.clear();
            totFrtSubTotAmt = add(totFrtSubTotAmt, totFrtAmt);
            if (totFrtAmt.compareTo(BigDecimal.ZERO) == 0) {
                totFrtSubTotAmt = null;
            }
            ZYPEZDItemValueSetter.setValue(sumLine.xxTotFrtSubAmt, totFrtSubTotAmt);
            ZYPEZDItemValueSetter.setValue(sumLine.xxUnitRestkFeeAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(sumLine.xxTotRestkFeeAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(sumLine.xxSlsAmt, add(sumLine.xxTotNetPrcAmt.getValue(), totFrtAmt));

            // QC#19244 2017/07/04 Del Start
            //NWZC036101PMsg itemPMsg = null;
            deleteTaxCalcBase(line);
            //if (!isExpt && ZYPConstant.FLG_ON_Y.equals(param.taxCalcFlg.getValue())) {
            //    if (BigDecimal.ZERO.compareTo(sumLine.xxTotNetPrcAmt.getValue()) != 0) {
            //        itemPMsg = callTaxCalcurationApi(param,
            //                                       line,
            //                                       sumLine.xxUnitNetPrcAmt.getValue(),
            //                                       sumLine.xxTotNetPrcAmt.getValue(),
            //                                       null,
            //                                       svcAllocTrxTpNm,
            //                                       onBatchType);
            //      editCalcBaseForTax(param, line, itemPMsg, null);
            //    }
            //}
            // QC#19244 2017/07/04 Del End
        }
        param.NWZC157004PMsg.setValidCount(param.NWZC157002PMsg.getValidCount());

        //tax calculation
        // QC#19244 2017/07/04 Mod Start
        //Map<List<String>, List<Integer>> taxGrpMap = groupMappingLineForTaxCalclation(param);
        //Set<List<String>> keys = taxGrpMap.keySet();
        //
        //BigDecimal t_unitFrtAmt = BigDecimal.ZERO;
        //BigDecimal t_totFrtAmt = BigDecimal.ZERO;
        // 
        //for (List<String> key : keys) {
        //    List<Integer> idxList = taxGrpMap.get(key);
        //    if(idxList == null){
        //        continue;
        //    }
        //    t_unitFrtAmt = BigDecimal.ZERO;
        //    t_totFrtAmt = BigDecimal.ZERO;
        //
        //    for (Integer idx : idxList) {
        //        sumLine = param.NWZC157004PMsg.no(idx.intValue());
        //        t_unitFrtAmt = add(t_unitFrtAmt, sumLine.xxUnitFrtAmt.getValue());
        //        t_totFrtAmt = add(t_totFrtAmt, sumLine.xxTotFrtAmt.getValue());
        //    }
        //    NWZC036101PMsg itemPMsg = null;
        //    NWZC036101PMsg frtPMsg = null;
        //    if (!isExpt && ZYPConstant.FLG_ON_Y.equals(param.taxCalcFlg.getValue())) {
        //
        //        if(BigDecimal.ZERO.compareTo(t_unitFrtAmt) != 0){// QC#13900 2016/10/12 Add
        //            frtPMsg = callTaxCalcurationApi(param, 
        //                                            param.NWZC157002PMsg.no(idxList.get(0).intValue()),
        //                                            t_unitFrtAmt,
        //                                            t_totFrtAmt,
        //                                            frtMdseCd,
        //                                            svcAllocTrxTpNm,
        //                                            onBatchType);
        //        }
        //        editCalcBaseForTax(param, idxList, itemPMsg, frtPMsg);
        //    }
        //}
        if (!isExpt && ZYPConstant.FLG_ON_Y.equals(param.taxCalcFlg.getValue())) {
            Map<List<String>, List<Integer>> taxGrpMap = null;
            Set<List<String>> keys = null;
            NWZC036101PMsg itemPMsg = null;
            //NWZC036101PMsg frtPMsg = null;

            // QC#21106 2017/09/16 Add Start
            // taxGrpMap = groupMappingLineForTaxCalclation(param, false);
            taxGrpMap = groupMappingLineForTaxCalclation(param, true);
            // QC#21106 2017/09/16 Add End
            keys = taxGrpMap.keySet();
            for (List<String> key : keys) {
                List<Integer> idxList = taxGrpMap.get(key);

                // QC#21106 2017/09/16 Mod Start
                // itemPMsg = editTaxCalcurationApiIntfc(param, idxList, svcAllocTrxTpNm);
                itemPMsg = editTaxCalcurationApiIntfc(param, idxList, frtMdseCd, svcAllocTrxTpNm);
                // QC#21106 2017/09/16 Mod End
                new NWZC036101().execute(itemPMsg, onBatchType);
                if (S21ApiUtil.isXxMsgId(itemPMsg)) {
                    S21ApiMessageMap map = new S21ApiMessageMap(param);
                    map.addXxMsgIdWithPrm(NWZM2007W, null);
                    map.flush();
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(itemPMsg); // QC#20545 2017/08/09 Mod
                    S21LoggerFactory.getLogger(NWZC157001.class).info("Tax Calculation API has Error. Message = " + msgList.get(0).getXxMsgid());
                }
                // QC#21106 2017/09/16 Mod Start
                //editCalcBaseForTax(param, idxList, itemPMsg, null);
                editCalcBaseForTax(param, idxList, itemPMsg); 
                // QC#21106 2017/09/16 Mod End

            }

            // QC#21106 2017/09/16 Del Start
            // tax calculation
            //taxGrpMap = groupMappingLineForTaxCalclation(param, true);
            //keys = taxGrpMap.keySet();

            //BigDecimal t_unitFrtAmt = BigDecimal.ZERO;
            //BigDecimal t_totFrtAmt = BigDecimal.ZERO;

            //for (List<String> key : keys) {
            //    List<Integer> idxList = taxGrpMap.get(key);
            //    if (idxList == null) {
            //        continue;
            //    }
            //    t_unitFrtAmt = BigDecimal.ZERO;
            //    t_totFrtAmt = BigDecimal.ZERO;

            //    for (Integer idx : idxList) {
            //        sumLine = param.NWZC157004PMsg.no(idx.intValue());
            //        t_unitFrtAmt = add(t_unitFrtAmt, sumLine.xxUnitFrtAmt.getValue());
            //        t_totFrtAmt = add(t_totFrtAmt, sumLine.xxTotFrtAmt.getValue());
            //    }
            //    if (BigDecimal.ZERO.compareTo(t_unitFrtAmt) != 0) {// QC#13900 2016/10/12b Add
            //        frtPMsg = editTaxCalcurationApiIntfcForFrt(param, 
            //                                        param.NWZC157002PMsg.no(idxList.get(0).intValue()), 
            //                                        t_unitFrtAmt, 
            //                                        t_totFrtAmt, 
            //                                        frtMdseCd, 
            //                                        svcAllocTrxTpNm);
            //        new NWZC036101().execute(frtPMsg, onBatchType);
            //        if (S21ApiUtil.isXxMsgId(frtPMsg)) {
            //            S21ApiMessageMap map = new S21ApiMessageMap(param);
            //            map.addXxMsgIdWithPrm(NWZM2007W, null);
            //            map.flush();
            //            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(frtPMsg);
            //            S21LoggerFactory.getLogger(NWZC157001.class).info("Tax Calculation API has Error. Message = " + msgList.get(0).getXxMsgid());
            //        }
            //        editCalcBaseForTax(param, idxList, null, frtPMsg);
            //    }
            //}
            // QC#21106 2017/09/16 Del End
       }
        // QC#19244 2017/07/04 Mod End

        //total amount calclation
        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
            line = param.NWZC157002PMsg.no(i);
            sumLine = param.NWZC157004PMsg.no(i);
            totTaxAmt = BigDecimal.ZERO;
            for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                calcBase = line.NWZC157003PMsg.no(j);
                if (PRC_DTL_GRP.TAX.equals(calcBase.prcDtlGrpCd.getValue())) {
                    totTaxAmt = add(totTaxAmt, calcBase.calcPrcAmtRate.getValue());
                }
                log("line = " + calcBase.trxLineNum.getValue() + ", PRC_DTL_GRP = " + calcBase.prcDtlGrpCd.getValue() + ", Amount=" + calcBase.calcPrcAmtRate.getValue() + ", specCondPrcPk = " + calcBase.specCondPrcPk.getValue());
            }
            ZYPEZDItemValueSetter.setValue(sumLine.xxTotTaxAmt, totTaxAmt);
            ZYPEZDItemValueSetter.setValue(sumLine.xxTotAmt, add(sumLine.xxSlsAmt.getValue(), totTaxAmt));
        }
        return false;
    }
    // QC#22965 2018/04/11 Add Start
    private void resetCalcBase(S21ApiMessageMap msgMap, List<NWZC157003PMsg> deleteList){
        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();
        String trxLineNum = null;
        String trxLineSubNum = null;
        String configCatgCd = null;
        NWZC157002PMsg line = null;
        NWZC157003PMsg calcBaseData = null;
        for (NWZC157003PMsg calcBase : deleteList) {

            if (S21StringUtil.isEquals(trxLineNum, calcBase.trxLineNum.getValue()) 
                    && S21StringUtil.isEquals(trxLineSubNum, calcBase.trxLineSubNum.getValue()) 
                    && S21StringUtil.isEquals(configCatgCd, calcBase.configCatgCd.getValue())) {
            } else {
                line = getLine(param, calcBase.trxLineNum.getValue(), calcBase.trxLineSubNum.getValue(), calcBase.configCatgCd.getValue());

                trxLineNum = calcBase.trxLineNum.getValue();
                trxLineSubNum = calcBase.trxLineSubNum.getValue();
                configCatgCd = calcBase.configCatgCd.getValue();
            }
            if(line == null){
                continue;
            }
            calcBaseData = line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount());
            EZDMsg.copy(calcBase, null, calcBaseData, null);
            line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        }
    }
    private void divideCalcBase(S21ApiMessageMap msgMap, List<NWZC157003PMsg> manualList, String prcCondManAddFlg){

        NWZC157001PMsg param = (NWZC157001PMsg) msgMap.getPmsg();
        Map<List<String>, List<NWZC157003PMsg>> keyMap = new HashMap<List<String>, List<NWZC157003PMsg>>();
        for (NWZC157003PMsg row : manualList) {
            if (prcCondManAddFlg.equals(row.prcCondManAddFlg.getValue())) {
                List<String> key = new ArrayList<String>();
                key.add(row.configCatgCd.getValue());
                if (ZYPCommonFunc.hasValue(row.specCondPrcPk)) {
                    key.add(row.specCondPrcPk.getValue().toString());
                } else {
                    key.add(null);
                }
                List<NWZC157003PMsg> data = keyMap.get(key);
                if(data == null){
                    List<NWZC157003PMsg> value = new ArrayList<NWZC157003PMsg>();
                    value.add(row);
                    keyMap.put(key, value);
                }else{
                    data.add(row);
                }
            }
        }

        String  configCatgCd = null;
        BigDecimal specCondPrcPK = null;
        String prcRuleAdjLvlCd = null;
        for (Map.Entry<List<String>, List<NWZC157003PMsg>> entry : keyMap.entrySet()) {
            List<String> key = entry.getKey();
            configCatgCd = key.get(0);
            if (ZYPCommonFunc.hasValue(key.get(1))) {
                specCondPrcPK = new BigDecimal(key.get(1));
            } else {
                continue;
            }
            
            Map<String, Object> mapParam = new HashMap<String, Object>();
            mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            mapParam.put("specCondPrcPK", specCondPrcPK);
            Map<String, Object> rule = DataCacheForSSM.getInstance().getPriceRule(mapParam, ssmBatchClient);
            if (rule == null) {
                continue;
            }
            prcRuleAdjLvlCd = (String) rule.get("PRC_RULE_ADJ_LVL_CD");

            if (PRC_RULE_ADJ_LVL.HEADER.equals(prcRuleAdjLvlCd)) {
                editHdrRuleAdjust(param, configCatgCd, specCondPrcPK, entry.getValue(), rule, prcCondManAddFlg);
            } else if (PRC_RULE_ADJ_LVL.LINE.equals(prcRuleAdjLvlCd)) {
                editLineRuleAdjust(param, configCatgCd, specCondPrcPK, entry.getValue(), rule, prcCondManAddFlg);
            }
        }
    }
    
    private void editHdrRuleAdjust(NWZC157001PMsg param,
                                    String  configCatgCd,
                                    BigDecimal specCondPrcPk,
                                    List<NWZC157003PMsg> list,
                                    Map<String, Object> rule,
                                    String prcCondManAddFlg){

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String prcCondManDelFlg = ZYPConstant.FLG_OFF_N;
        BigDecimal prcRulePrcdPk = null;
        boolean frozenFlg = false;
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal frozenAmount = BigDecimal.ZERO;
        BigDecimal frozeTargetAmt = BigDecimal.ZERO;
        // QC#29565 2018/12/28 Add Start
        BigDecimal qty = BigDecimal.ZERO;
        BigDecimal frozenQty = BigDecimal.ZERO;
        BigDecimal totalQty = BigDecimal.ZERO;
        // QC#29565 2018/12/28 Add End
        BigDecimal rate  = null;
        BigDecimal ratio  = BigDecimal.ZERO;
        BigDecimal numerAmt = BigDecimal.ZERO;
        BigDecimal denomAmt = BigDecimal.ZERO;
        BigDecimal unitCalcPrice = BigDecimal.ZERO;
        String prcDtlGrpCd = null;
        String ccy = null;
        BigDecimal calcPrcAmt = BigDecimal.ZERO;
        BigDecimal amtTotal = BigDecimal.ZERO;

        String prcRuleModTpCd = (String) rule.get("PRC_RULE_MOD_TP_CD");
        String prcRuleAdjTpCd = (String) rule.get("PRC_RULE_ADJ_TP_CD");
        String prcRuleAttrbCd = (String) rule.get("PRC_RULE_ATTRB_CD");
        ccy = param.NWZC157002PMsg.no(0).ccyCd.getValue();

        List<NWZC157002PMsg> targetLines = new ArrayList<NWZC157002PMsg>();
        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg line = param.NWZC157002PMsg.no(i);
            if (!S21StringUtil.isEquals(configCatgCd, line.configCatgCd.getValue())) {
                continue;
            }
            frozenFlg = isFrozen(line);
            amount = getNetAmount(line, specCondPrcPk);
            qty = line.ordQty.getValue(); // QC#29565 2018/12/28 Add
            totalAmount = add(totalAmount, amount);
            totalQty = add(totalQty, qty);
            targetLines.add(line);
            if (frozenFlg) {
                frozenAmount = add(frozenAmount, amount);
                frozenQty = add(frozenQty, qty); // QC#29565 2018/12/28 Add
                for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                    NWZC157003PMsg calcBase = line.NWZC157003PMsg.no(j);
                    if (isEquals(calcBase.specCondPrcPk.getValue(), specCondPrcPk)) {
                        frozeTargetAmt = add(frozeTargetAmt, calcBase.calcPrcAmtRate.getValue());
                    }
                }
            } else {
                List<Integer> delList = new ArrayList<Integer>();
                for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                    NWZC157003PMsg calcBase = line.NWZC157003PMsg.no(j);
                    if (isEquals(calcBase.specCondPrcPk.getValue(), specCondPrcPk)) {
                        delList.add(j);
                    }
                }
                if (delList.size() > 0) {
                    ZYPTableUtil.deleteRows(line.NWZC157003PMsg, delList);
                    delList.clear();
                }
            }
        }

        for (NWZC157003PMsg calcBase : list) {
            if(prcRulePrcdPk == null){
                prcRulePrcdPk = calcBase.prcRulePrcdPk.getValue();
            }
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, calcBase.prcCondManDelFlg.getValue())) {
                prcCondManDelFlg = ZYPConstant.FLG_ON_Y;
            }
            if (PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd)) {
                rate = calcBase.manPrcAmtRate.getValue();
                if (PRC_RULE_ADJ_TP.SET_PRICE.equals(prcRuleAdjTpCd)) {
                    rate = subtract(BigDecimal.ONE, rate);
                } else if (PRC_RULE_ADJ_TP.SURCHARGE.equals(prcRuleAdjTpCd)) {
                    rate = rate.multiply(MINUS);
                }
                calcPrcAmt = multiply(glblCmpyCd, totalAmount, rate, ccy);
                calcPrcAmt = calcPrcAmt.subtract(frozeTargetAmt);
                break;
            } else {
                calcPrcAmt = calcPrcAmt.add(calcBase.calcPrcAmtRate.getValue());
                calcPrcAmt = calcPrcAmt.subtract(frozeTargetAmt);
            }
        }
        
        // QC#29565 2018/12/28 Mod Start
        //denomAmt = totalAmount.subtract(frozenAmount);
        if (totalAmount.compareTo(BigDecimal.ZERO) == 0) {
            denomAmt = totalQty.subtract(frozenQty);
        } else {
            denomAmt = totalAmount.subtract(frozenAmount);
        }
        // QC#29565 2018/12/28 Mod End
        if (denomAmt.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }
        
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, prcCondManDelFlg) 
                && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, prcCondManAddFlg)) {
            return;
        }

        if (PRC_RULE_MOD_TP.CHARGES.equals(prcRuleModTpCd)) {
            //prcDtlGrpCd = PRC_DTL_GRP.FREIGHT;
            if (PRC_RULE_ADJ_TP.FUEL_SURCHARGE.equals(prcRuleAdjTpCd)) {
                prcDtlGrpCd = PRC_DTL_GRP.FUEL_SURCHARGE;
            } else if (PRC_RULE_ADJ_TP.HANDLING_FEE.equals(prcRuleAdjTpCd)) {
                prcDtlGrpCd = PRC_DTL_GRP.HANDLING_FEE;
            } else if (PRC_RULE_ADJ_TP.SHIPPING_FEE.equals(prcRuleAdjTpCd)) {
                prcDtlGrpCd = PRC_DTL_GRP.SHIPPING_FEE;
            } else if (PRC_RULE_ADJ_TP.RESTOCKING_FEE.equals(prcRuleAdjTpCd)) { // QC#27479 2018/08/03 Add
                prcDtlGrpCd = PRC_DTL_GRP.RESTOCKING_FEE;
            } else {
                prcDtlGrpCd = PRC_DTL_GRP.FREIGHT;
            }
        } else {
            prcDtlGrpCd = PRC_DTL_GRP.DISCOUNT;
        }

        for (NWZC157002PMsg line : targetLines) {
            if (!isFrozen(line)) {
                // QC#29565 2018/12/28 Mod Start
                //numerAmt = getNetAmount(line, specCondPrcPk);
                //ratio = numerAmt.divide(denomAmt, RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                if (totalAmount.compareTo(BigDecimal.ZERO) == 0) {
                    numerAmt = line.ordQty.getValue();
                    ratio = numerAmt.divide(denomAmt, RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                } else {
                    numerAmt = getNetAmount(line, specCondPrcPk);
                    ratio = numerAmt.divide(denomAmt, RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                }
                // QC#29565 2018/12/28 Mod End
                unitCalcPrice = divide(glblCmpyCd, calcPrcAmt.multiply(ratio), line.ordQty.getValue(), ccy);
                amtTotal = amtTotal.add(editCalcBaseForManual(param, line, prcDtlGrpCd, specCondPrcPk, rate, unitCalcPrice, prcCondManAddFlg, prcCondManDelFlg, prcRulePrcdPk));
            }
        }
        adjustFraction(param, configCatgCd, prcDtlGrpCd, specCondPrcPk, amtTotal, calcPrcAmt, prcCondManAddFlg, prcCondManDelFlg, prcRulePrcdPk);
    }
    private void editLineRuleAdjust(NWZC157001PMsg param,
                                     String  configCatgCd,
                                     BigDecimal specCondPrcPk,
                                     List<NWZC157003PMsg> list,
                                     Map<String, Object> rule,
                                     String prcCondManAddFlg
                                     ) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String prcCondManDelFlg = ZYPConstant.FLG_OFF_N;
        BigDecimal prcRulePrcdPk = null;
        BigDecimal rate  = null;
        BigDecimal unitCalcPrice = BigDecimal.ZERO;
        String prcDtlGrpCd = null;
        String ccy = null;
        NWZC157003PMsg base = null;

        String prcRuleModTpCd = (String) rule.get("PRC_RULE_MOD_TP_CD");
        String prcRuleAdjTpCd = (String) rule.get("PRC_RULE_ADJ_TP_CD");
        String prcRuleAttrbCd = (String) rule.get("PRC_RULE_ATTRB_CD");

        for (NWZC157003PMsg data : list) {
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, data.prcCondManDelFlg.getValue())) {
                prcCondManDelFlg = ZYPConstant.FLG_ON_Y;
            // QC#50548 2019/05/28 Add Start
            } else {
                prcCondManDelFlg = ZYPConstant.FLG_OFF_N;
            // QC#50548 2019/05/28 Add End
            }
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, prcCondManDelFlg) 
                    && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, prcCondManAddFlg)) {
                return;
            }
            NWZC157002PMsg line = getLine(param, data.trxLineNum.getValue(), data.trxLineSubNum.getValue(), data.configCatgCd.getValue());
            if (line == null) {
                return;
            }
            // QC#28013 2018/08/29 Add Start
            List<Integer> delList = new ArrayList<Integer>();

            if (!isFrozen(line)) {
                //for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                //    base = line.NWZC157003PMsg.no(j);
                //    if (isEquals(base.specCondPrcPk.getValue(), specCondPrcPk)) {
                //        delList.add(j);
                //    }
                //}
                //if (delList.size() > 0) {
                //    ZYPTableUtil.deleteRows(line.NWZC157003PMsg, delList);
                //    delList.clear();
                //}
                // QC#28013 2018/08/29 Add End
                ccy = line.ccyCd.getValue();
                BigDecimal netPrice = getNetPrice(glblCmpyCd, line, specCondPrcPk);
                if (PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd)) {
                    rate = data.manPrcAmtRate.getValue();
                    unitCalcPrice = multiply(glblCmpyCd, netPrice, rate, ccy);
                } else {
                    unitCalcPrice = data.manPrcAmtRate.getValue();
                }
                if (PRC_RULE_MOD_TP.CHARGES.equals(prcRuleModTpCd)) {
                    // prcDtlGrpCd = PRC_DTL_GRP.FREIGHT;
                    if (PRC_RULE_ADJ_TP.FUEL_SURCHARGE.equals(prcRuleAdjTpCd)) {
                        prcDtlGrpCd = PRC_DTL_GRP.FUEL_SURCHARGE;
                    } else if (PRC_RULE_ADJ_TP.HANDLING_FEE.equals(prcRuleAdjTpCd)) {
                        prcDtlGrpCd = PRC_DTL_GRP.HANDLING_FEE;
                    } else if (PRC_RULE_ADJ_TP.SHIPPING_FEE.equals(prcRuleAdjTpCd)) {
                        prcDtlGrpCd = PRC_DTL_GRP.SHIPPING_FEE;
                    } else if (PRC_RULE_ADJ_TP.RESTOCKING_FEE.equals(prcRuleAdjTpCd)) {
                        prcDtlGrpCd = PRC_DTL_GRP.RESTOCKING_FEE;
                    } else {
                        prcDtlGrpCd = PRC_DTL_GRP.FREIGHT;
                    }
                } else {
                    prcDtlGrpCd = PRC_DTL_GRP.DISCOUNT;
                }
                // QC#50130 2019/05/15 Add Start
                if(isRoundFactor(data.prcDtlGrpCd.getValue())){
                    prcDtlGrpCd = data.prcDtlGrpCd.getValue();
                    unitCalcPrice = data.calcPrcAmtRate.getValue();
                }
                // QC#50130 2019/05/15 Add End
                prcRulePrcdPk = data.prcRulePrcdPk.getValue();
                editCalcBaseForManual(param, line, prcDtlGrpCd, specCondPrcPk, rate, unitCalcPrice, prcCondManAddFlg, prcCondManDelFlg, prcRulePrcdPk);
            }
        }
    }
    private BigDecimal editCalcBaseForManual(NWZC157001PMsg param, 
                                              NWZC157002PMsg line, 
                                              String prcDtlGrpCd, 
                                              BigDecimal specCondPrcPk,
                                              BigDecimal rate,
                                              BigDecimal unitCalcPrice,
                                              String prcCondManAddFlg,
                                              String prcCondManDelFlg,
                                              BigDecimal prcRulePrcdPk
                                              ) {
        NWZC157003PMsg calcBase = null;
        NWZC157003PMsg base = null;
        NWZC157003PMsg orgbase = null;
        for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
            base = line.NWZC157003PMsg.no(j);
            if (prcDtlGrpCd.equals(base.prcDtlGrpCd.getValue())
                    && isEquals(base.specCondPrcPk.getValue(), specCondPrcPk)) {
                calcBase = base;
            }
        }
        if (calcBase == null) {
            calcBase = line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount());
            line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        }
        ZYPEZDItemValueSetter.setValue(calcBase.trxLineNum, line.trxLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.trxLineSubNum, line.trxLineSubNum.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.configCatgCd, line.configCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.prcDtlGrpCd, prcDtlGrpCd);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCatgCd, line.prcCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManAddFlg, prcCondManAddFlg);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManDelFlg, prcCondManDelFlg);
        ZYPEZDItemValueSetter.setValue(calcBase.pkgUomCd, line.pkgUomCd.getValue());
        if (rate == null) {
            ZYPEZDItemValueSetter.setValue(calcBase.prcCondUnitCd, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(calcBase.manPrcAmtRate, unitCalcPrice);
        } else {
            ZYPEZDItemValueSetter.setValue(calcBase.prcCondUnitCd, PRC_COND_UNIT.PCT);
            ZYPEZDItemValueSetter.setValue(calcBase.manPrcAmtRate, rate);
        }
        ZYPEZDItemValueSetter.setValue(calcBase.autoPrcCcyCd, line.ccyCd.getValue());
        calcBase.autoPrcAmtRate.clear();
        ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, unitCalcPrice.multiply(line.ordQty.getValue()));
        ZYPEZDItemValueSetter.setValue(calcBase.unitPrcAmt, unitCalcPrice);
        ZYPEZDItemValueSetter.setValue(calcBase.specCondPrcPk, specCondPrcPk);
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(calcBase.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(calcBase.prcRulePrcdPk, prcRulePrcdPk);
        // QC#9700  2018/09/03 Add End
        // QC#50130 2019/05/15 Add Start
        if (isRoundFactor(prcDtlGrpCd)) {
            ZYPEZDItemValueSetter.setValue(calcBase.prcCondUnitCd, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(calcBase.autoPrcAmtRate, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, unitCalcPrice);
            ZYPEZDItemValueSetter.setValue(calcBase.unitPrcAmt, BigDecimal.ZERO);
            for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                base = line.NWZC157003PMsg.no(j);
                // search original calcbase...
                if (!prcDtlGrpCd.equals(base.prcDtlGrpCd.getValue())
                        && isEquals(base.specCondPrcPk.getValue(), specCondPrcPk)) {
                    orgbase = base;
                    break;
                }
            }
            if (orgbase == null) {
                ZYPEZDItemValueSetter.setValue(calcBase.prcCondManDelFlg, ZYPConstant.FLG_ON_Y);
            }else{
                ZYPEZDItemValueSetter.setValue(calcBase.prcCondManDelFlg, orgbase.prcCondManDelFlg);
            }
        }
        // QC#50130 2019/05/15 Add End
        return calcBase.calcPrcAmtRate.getValue();
    }
    private void adjustFraction(NWZC157001PMsg param, 
                                String configCatgCd, 
                                String prcDtlGrpCd, 
                                BigDecimal specCondPrcPk, 
                                BigDecimal totalAmt, 
                                BigDecimal targetAmt, 
                                String prcCondManAddFlg, 
                                String prcCondManDelFlg,
                                BigDecimal prcRulePrcdPk
                                ) {
        NWZC157002PMsg targetline = null;
        NWZC157003PMsg base = null;
        String newPrcDtlGrpCd = null;
        BigDecimal maxValue = null;
        BigDecimal fraction = targetAmt.subtract(totalAmt);
        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg line = param.NWZC157002PMsg.no(i);
            if (!isFrozen(line)) {
                for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                    base = line.NWZC157003PMsg.no(j);
                    if (prcDtlGrpCd.equals(base.prcDtlGrpCd.getValue()) && isEquals(base.specCondPrcPk.getValue(), specCondPrcPk)) {
                        // QC#27792 2018/08/27 Mod End
                        // targetline = line;
                        if (maxValue == null) {
                            targetline = line;
                            maxValue = base.calcPrcAmtRate.getValue().abs();
                        } else if (base.calcPrcAmtRate.getValue().abs().compareTo(maxValue) > 0) {
                            targetline = line;
                            maxValue = base.calcPrcAmtRate.getValue().abs();
                        }
                        // QC#27792 2018/08/27 Mod End
                    }
                }
            }
        }
        if (BigDecimal.ZERO.compareTo(fraction) == 0) {
            return;
        }
        if (PRC_DTL_GRP.DISCOUNT.equals(prcDtlGrpCd)) {
            newPrcDtlGrpCd = PRC_DTL_GRP.ROUNDING_FACTOR_0;
        // QC#21841 2018/05/21 Add Start
        } else if (PRC_DTL_GRP.HANDLING_FEE.equals(prcDtlGrpCd)) {
            newPrcDtlGrpCd = PRC_DTL_GRP.ROUNDING_FACTOR_2;
        } else if (PRC_DTL_GRP.FUEL_SURCHARGE.equals(prcDtlGrpCd)) {
            newPrcDtlGrpCd = PRC_DTL_GRP.ROUNDING_FACTOR_2;
        } else if (PRC_DTL_GRP.SHIPPING_FEE.equals(prcDtlGrpCd)) {
            newPrcDtlGrpCd = PRC_DTL_GRP.ROUNDING_FACTOR_2;
        // QC#21841 2018/05/21 Add End
        // QC#27479 2018/08/03 Add Start
        } else if (PRC_DTL_GRP.RESTOCKING_FEE.equals(prcDtlGrpCd)) {
            newPrcDtlGrpCd = PRC_DTL_GRP.ROUNDING_FACTOR_2;
        // QC#27479 2018/08/03 Add End
        } else {
            newPrcDtlGrpCd = PRC_DTL_GRP.ROUNDING_FACTOR_1;
        }
        if (targetline == null) {
            return;
        }
        NWZC157003PMsg calcBase = targetline.NWZC157003PMsg.no(targetline.NWZC157003PMsg.getValidCount());
        ZYPEZDItemValueSetter.setValue(calcBase.trxLineNum, targetline.trxLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.trxLineSubNum, targetline.trxLineSubNum.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.configCatgCd, targetline.configCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.prcDtlGrpCd, newPrcDtlGrpCd);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCatgCd, targetline.prcCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManAddFlg, prcCondManAddFlg);
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondManDelFlg, prcCondManDelFlg);
        ZYPEZDItemValueSetter.setValue(calcBase.pkgUomCd, targetline.pkgUomCd.getValue());
        ZYPEZDItemValueSetter.setValue(calcBase.prcCondUnitCd, PRC_COND_UNIT.AMT);
        ZYPEZDItemValueSetter.setValue(calcBase.autoPrcCcyCd, targetline.ccyCd.getValue());
        calcBase.autoPrcAmtRate.clear();
        ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, fraction);
        ZYPEZDItemValueSetter.setValue(calcBase.unitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(calcBase.specCondPrcPk, specCondPrcPk);
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(calcBase.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(calcBase.prcRulePrcdPk, prcRulePrcdPk);
        // QC#9700  2018/09/03 Add End
        targetline.NWZC157003PMsg.setValidCount(targetline.NWZC157003PMsg.getValidCount() + 1);

        return;
    }
    // QC#22965 2018/04/11 Add End

    private Map<List<String>, List<Integer>> groupMappingLineForTaxCalclation(NWZC157001PMsg param, boolean isFrt) {
        NWZC157002PMsg line = null;
        Map<List<String>, List<Integer>> taxGrpMap = new HashMap<List<String>, List<Integer>>();
        List<Integer> taxGrpList = null;
        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
            line = param.NWZC157002PMsg.no(i);
            List<String> key = new ArrayList<String>();
            // QC#19244 2017/07/04 Add Start
            // key.add(line.configCatgCd.getValue());
            // key.add(line.billToCustCd.getValue());
            // key.add(line.shipToCustCd.getValue());
            // key.add(line.rtlWhCd.getValue());
            // key.add(line.ctyAddr_SH.getValue());
            // key.add(line.stCd_SH.getValue());
            // key.add(line.cntyNm_SH.getValue());
            // key.add(line.postCd_SH.getValue());
            // key.add(line.ctryCd_SH.getValue());
            // key.add(line.slsRepOrSlsTeamTocCd.getValue());
            if (isFrt) {
                key.add(line.configCatgCd.getValue());
                key.add(line.dsOrdPosnNum.getValue());
                key.add(line.billToCustCd.getValue());
                key.add(line.shipToCustCd.getValue());
                key.add(line.rtlWhCd.getValue());
                key.add(line.ctyAddr_SH.getValue());
                key.add(line.stCd_SH.getValue());
                key.add(line.cntyNm_SH.getValue());
                key.add(line.postCd_SH.getValue());
                key.add(line.ctryCd_SH.getValue());
                key.add(line.slsRepOrSlsTeamTocCd.getValue());
            } else {
                key.add(line.configCatgCd.getValue());
                key.add(line.billToCustCd.getValue());
                key.add(line.shipToCustCd.getValue());
                key.add(line.ctyAddr_SH.getValue());
                key.add(line.stCd_SH.getValue());
                key.add(line.cntyNm_SH.getValue());
                key.add(line.postCd_SH.getValue());
                key.add(line.ctryCd_SH.getValue());
                key.add(line.slsRepOrSlsTeamTocCd.getValue());
            }
            // QC#19244 2017/07/04 Add End
            //key.add(line.prcBaseDt.getValue());   // QC#12926 2016/08/23 Del
            taxGrpList = taxGrpMap.get(key);
            if (taxGrpList == null) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(new Integer(i));
                taxGrpMap.put(key, list);
            } else {
                taxGrpList.add(new Integer(i));
            }
        }
        return taxGrpMap;
    }

//    private void editCalcBaseForTax(NWZC157001PMsg param, List<Integer> idxList, NWZC036101PMsg itemPMsg, NWZC036101PMsg frtPMsg) {
//        NWZC157002PMsg line = null;
//        NWZC157003PMsg calcBase = null;
//        NWZC157004PMsg sumLine = null;
//        NWZC157003PMsg item1 = null;
//        NWZC157003PMsg item2 = null;
//        NWZC157003PMsg item3 = null;
//        NWZC157003PMsg frt1 = null;
//        NWZC157003PMsg frt2 = null;
//        NWZC157003PMsg frt3 = null;
//
//        BigDecimal totAmtItem01 = BigDecimal.ZERO;
//        BigDecimal totAmtItem02 = BigDecimal.ZERO;
//        BigDecimal totAmtItem03 = BigDecimal.ZERO;
//        BigDecimal totAmtfrt01 = BigDecimal.ZERO;
//        BigDecimal totAmtfrt02 = BigDecimal.ZERO;
//        BigDecimal totAmtfrt03 = BigDecimal.ZERO;
//
//        BigDecimal maxItemAmt = BigDecimal.ZERO;
//        Integer maxItemIndex = 0;
//        BigDecimal maxFrtAmt = BigDecimal.ZERO;
//        Integer maxFrtIndex = 0;
//        BigDecimal sumAmt = BigDecimal.ZERO;
//        BigDecimal fraction = BigDecimal.ZERO;
//        // Create calcBase
//        int i = 0;             // QC#19244 2017/07/04 Add
//
//        for (Integer idx : idxList) {
//            line = param.NWZC157002PMsg.no(idx.intValue());
//            sumLine = param.NWZC157004PMsg.no(idx.intValue());
//
//            if (ZYPConstant.FLG_ON_Y.equals(param.xxSmryLineFlg.getValue())) {
//                if (itemPMsg != null) {
//                    item1 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.ITEM_TAX1);  // QC#19244 2017/07/04 Mod
//                    if (BigDecimal.ZERO.compareTo(item1.calcPrcAmtRate.getValue()) != 0) {
//                        EZDMsg.copy(item1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
//                        totAmtItem01 = add(totAmtItem01, item1.calcPrcAmtRate.getValue());
//                        line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
//                    }
//                }
//                if (frtPMsg != null) {
//                    frt1 = setTaxRows(param, line, sumLine, frtPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.FREIGHT_TAX1);
//                    if (BigDecimal.ZERO.compareTo(frt1.calcPrcAmtRate.getValue()) != 0) {
//                        EZDMsg.copy(frt1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
//                        totAmtfrt01 = add(totAmtfrt01, frt1.calcPrcAmtRate.getValue());
//                        line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
//                    }
//                }
//            } else {
//                if (itemPMsg != null) {
//                    item1 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.ITEM_TAX1);  // QC#19244 2017/07/04 Mod
//                    EZDMsg.copy(item1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
//                    totAmtItem01 = add(totAmtItem01, item1.calcPrcAmtRate.getValue());
//                    line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
//
//                    item2 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.ITEM_TAX2);  // QC#19244 2017/07/04 Mod
//                    EZDMsg.copy(item2, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
//                    totAmtItem02 = add(totAmtItem02, item2.calcPrcAmtRate.getValue());
//                    line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
//
//                    item3 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.ITEM_TAX3);  // QC#19244 2017/07/04 Mod
//                    EZDMsg.copy(item3, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
//                    totAmtItem03 = add(totAmtItem03, item3.calcPrcAmtRate.getValue());
//                    line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
//                }
//                if (frtPMsg != null) {
//                    frt1 = setTaxRows(param, line, sumLine, frtPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.FREIGHT_TAX1);
//                    EZDMsg.copy(frt1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
//                    totAmtfrt01 = add(totAmtfrt01, frt1.calcPrcAmtRate.getValue());
//                    line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
//
//                    frt2 = setTaxRows(param, line, sumLine, frtPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.FREIGHT_TAX2);
//                    EZDMsg.copy(frt2, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
//                    totAmtfrt02 = add(totAmtfrt02, frt2.calcPrcAmtRate.getValue());
//                    line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
//
//                    frt3 = setTaxRows(param, line, sumLine, frtPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.FREIGHT_TAX3);
//                    EZDMsg.copy(frt3, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
//                    totAmtfrt03 = add(totAmtfrt03, frt3.calcPrcAmtRate.getValue());
//                    line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
//                }
//                i++;
//            }
//            // QC#19244 2017/07/04 Del Start
//            //if (maxItemAmt.compareTo(sumLine.xxTotNetPrcAmt.getValue().abs()) < 0) { 
//            //    maxItemAmt = sumLine.xxTotNetPrcAmt.getValue().abs();
//            //    maxItemIndex = idx;
//            //}
//            // QC#19244 2017/07/04 Del End
//            if (maxFrtAmt.compareTo(sumLine.xxTotFrtAmt.getValue().abs()) < 0) {
//                maxFrtAmt = sumLine.xxTotFrtAmt.getValue().abs();
//                maxFrtIndex = idx;
//            }
//        }
//
//        // adjust fraction amount
//        // QC#19244 2017/07/04 Del Start
//        //item1 = null;
//        //item2 = null;
//        //item3 = null;
//        // QC#19244 2017/07/04 Del End
//        frt1 = null;
//        frt2 = null;
//        frt3 = null;
//        line = param.NWZC157002PMsg.no(maxItemIndex.intValue());
//        // QC#19244 2017/07/04 Del Start
//        //for (int i = 0; i < line.NWZC157003PMsg.getValidCount(); i++) {
//        //    calcBase = line.NWZC157003PMsg.no(i);
//        //    if (PRC_DTL_GRP.TAX.equals(calcBase.prcDtlGrpCd.getValue()) && PRC_COND_TP.ITEM_TAX1.equals(calcBase.prcCondTpCd.getValue())) {
//        //        item1 = calcBase;
//        //    }
//        //    if (PRC_DTL_GRP.TAX.equals(calcBase.prcDtlGrpCd.getValue()) && PRC_COND_TP.ITEM_TAX2.equals(calcBase.prcCondTpCd.getValue())) {
//        //        item2 = calcBase;
//        //    }
//        //    if (PRC_DTL_GRP.TAX.equals(calcBase.prcDtlGrpCd.getValue()) && PRC_COND_TP.ITEM_TAX3.equals(calcBase.prcCondTpCd.getValue())) {
//        //        item3 = calcBase;
//        //    }
//        //}
//        // QC#19244 2017/07/04 Del End
//        line = param.NWZC157002PMsg.no(maxFrtIndex.intValue());
//        for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
//            calcBase = line.NWZC157003PMsg.no(j);
//            if (PRC_DTL_GRP.TAX.equals(calcBase.prcDtlGrpCd.getValue()) && PRC_COND_TP.FREIGHT_TAX1.equals(calcBase.prcCondTpCd.getValue())) {
//                frt1 = calcBase;
//            }
//            if (PRC_DTL_GRP.TAX.equals(calcBase.prcDtlGrpCd.getValue()) && PRC_COND_TP.FREIGHT_TAX2.equals(calcBase.prcCondTpCd.getValue())) {
//                frt2 = calcBase;
//            }
//            if (PRC_DTL_GRP.TAX.equals(calcBase.prcDtlGrpCd.getValue()) && PRC_COND_TP.FREIGHT_TAX3.equals(calcBase.prcCondTpCd.getValue())) {
//                frt3 = calcBase;
//            }
//        }
//        if (ZYPConstant.FLG_ON_Y.equals(param.xxSmryLineFlg.getValue())) {
//            // QC#19244 2017/07/04 Del Start
//            //if (itemPMsg != null) {
//            //    sumAmt = BigDecimal.ZERO;
//            //    sumAmt = add(sumAmt, itemPMsg.taxCalculateOutputLine.no(0).taxAmt_01.getValue());
//            //    sumAmt = add(sumAmt, itemPMsg.taxCalculateOutputLine.no(0).taxAmt_02.getValue());
//            //    sumAmt = add(sumAmt, itemPMsg.taxCalculateOutputLine.no(0).taxAmt_03.getValue());
//            //    if (!isEquals(totAmtItem01, sumAmt)) {
//            //        fraction = subtract(sumAmt, totAmtItem01);
//            //        ZYPEZDItemValueSetter.setValue(item1.calcPrcAmtRate, add(item1.calcPrcAmtRate.getValue(), fraction));
//            //    }
//            //}
//            // QC#19244 2017/07/04 Del End
//            if (frtPMsg != null) {
//                sumAmt = BigDecimal.ZERO;
//                sumAmt = add(sumAmt, frtPMsg.taxCalculateOutputLine.no(0).taxAmt_01.getValue());
//                sumAmt = add(sumAmt, frtPMsg.taxCalculateOutputLine.no(0).taxAmt_02.getValue());
//                sumAmt = add(sumAmt, frtPMsg.taxCalculateOutputLine.no(0).taxAmt_03.getValue());
//                if (!isEquals(totAmtfrt01, sumAmt)) {
//                    fraction = subtract(sumAmt, totAmtfrt01);
//                    ZYPEZDItemValueSetter.setValue(frt1.calcPrcAmtRate, add(frt1.calcPrcAmtRate.getValue(), fraction));
//                }
//            }
//        } else {
//            // QC#19244 2017/07/04 Del Start
//            //if (itemPMsg != null) {
//            //    sumAmt = itemPMsg.taxCalculateOutputLine.no(0).taxAmt_01.getValue();
//            //    if (!isEquals(totAmtItem01, sumAmt)) {
//            //        fraction = subtract(sumAmt, totAmtItem01);
//            //        ZYPEZDItemValueSetter.setValue(item1.calcPrcAmtRate, add(item1.calcPrcAmtRate.getValue(), fraction));
//            //    }
//            //    sumAmt = itemPMsg.taxCalculateOutputLine.no(0).taxAmt_02.getValue();
//            //    if (!isEquals(totAmtItem02, sumAmt)) {
//            //        fraction = subtract(sumAmt, totAmtItem02);
//            //        ZYPEZDItemValueSetter.setValue(item2.calcPrcAmtRate, add(item2.calcPrcAmtRate.getValue(), fraction));
//            //    }
//            //    sumAmt = itemPMsg.taxCalculateOutputLine.no(0).taxAmt_03.getValue();
//            //    if (!isEquals(totAmtItem03, sumAmt)) {
//            //        fraction = subtract(sumAmt, totAmtItem03);
//            //        ZYPEZDItemValueSetter.setValue(item3.calcPrcAmtRate, add(item3.calcPrcAmtRate.getValue(), fraction));
//            //    }
//            //}
//            // QC#19244 2017/07/04 Del End
//            if (frtPMsg != null) {
//                sumAmt = frtPMsg.taxCalculateOutputLine.no(0).taxAmt_01.getValue();
//                if (!isEquals(totAmtfrt01, sumAmt)) {
//                    fraction = subtract(sumAmt, totAmtfrt01);
//                    ZYPEZDItemValueSetter.setValue(frt1.calcPrcAmtRate, add(frt1.calcPrcAmtRate.getValue(), fraction));
//                }
//                sumAmt = frtPMsg.taxCalculateOutputLine.no(0).taxAmt_02.getValue();
//                if (!isEquals(totAmtfrt02, sumAmt)) {
//                    fraction = subtract(sumAmt, totAmtfrt02);
//                    ZYPEZDItemValueSetter.setValue(frt2.calcPrcAmtRate, add(frt2.calcPrcAmtRate.getValue(), fraction));
//                }
//                sumAmt = frtPMsg.taxCalculateOutputLine.no(0).taxAmt_03.getValue();
//                if (!isEquals(totAmtfrt03, sumAmt)) {
//                    fraction = subtract(sumAmt, totAmtfrt03);
//                    ZYPEZDItemValueSetter.setValue(frt3.calcPrcAmtRate, add(frt3.calcPrcAmtRate.getValue(), fraction));
//                }
//            }
//        }
//    }
    // QC#21841 2018/05/21 Add Start
    private void editCalcBaseForTax(NWZC157001PMsg param, List<Integer> idxList, NWZC036101PMsg itemPMsg) {
        // QC#21841 2018/05/21 Mod Start
        NWZC157002PMsg line = null;
        //NWZC157004PMsg sumLine = null;
        //NWZC157003PMsg item1 = null;
        //NWZC157003PMsg item2 = null;
        //NWZC157003PMsg item3 = null;
        //NWZC157003PMsg frt1 = null;
        //NWZC157003PMsg frt2 = null;
        //NWZC157003PMsg frt3 = null;
        //BigDecimal totAmtItem01 = BigDecimal.ZERO;
        //BigDecimal totAmtItem02 = BigDecimal.ZERO;
        //BigDecimal totAmtItem03 = BigDecimal.ZERO;
        //BigDecimal totAmtfrt01 = BigDecimal.ZERO;
        //BigDecimal totAmtfrt02 = BigDecimal.ZERO;
        //BigDecimal totAmtfrt03 = BigDecimal.ZERO;
        //int i = 0;
        ///
        //for (Integer idx : idxList) {
        //    line = param.NWZC157002PMsg.no(idx.intValue());
        //    sumLine = param.NWZC157004PMsg.no(idx.intValue());
        //
        //if (ZYPConstant.FLG_ON_Y.equals(param.xxSmryLineFlg.getValue())) {
        //    item1 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.ITEM_TAX1);
        //    if (BigDecimal.ZERO.compareTo(item1.calcPrcAmtRate.getValue()) != 0) {
        //        EZDMsg.copy(item1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
        //        totAmtItem01 = add(totAmtItem01, item1.calcPrcAmtRate.getValue());
        //        line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        //    }
        //    i++;
        //    frt1 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.FREIGHT_TAX1);
        //    if (BigDecimal.ZERO.compareTo(frt1.calcPrcAmtRate.getValue()) != 0) {
        //        EZDMsg.copy(frt1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
        //        totAmtfrt01 = add(totAmtfrt01, frt1.calcPrcAmtRate.getValue());
        //        line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        //    }
        //    i++;
        //} else {
        //    item1 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.ITEM_TAX1);
        //    EZDMsg.copy(item1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
        //    totAmtItem01 = add(totAmtItem01, item1.calcPrcAmtRate.getValue());
        //    line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        //
        //    item2 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.ITEM_TAX2);
        //    EZDMsg.copy(item2, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
        //    totAmtItem02 = add(totAmtItem02, item2.calcPrcAmtRate.getValue());
        //    line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        //
        //    item3 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.ITEM_TAX3);
        //    EZDMsg.copy(item3, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
        //    totAmtItem03 = add(totAmtItem03, item3.calcPrcAmtRate.getValue());
        //    line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        //
        //    i++;
        //
        //    if (BigDecimal.ZERO.compareTo(sumLine.xxUnitFrtAmt.getValue()) != 0) {
        //        frt1 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.FREIGHT_TAX1);
        //        EZDMsg.copy(frt1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
        //        totAmtfrt01 = add(totAmtfrt01, frt1.calcPrcAmtRate.getValue());
        //        line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        //
        //        frt2 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.FREIGHT_TAX2);
        //        EZDMsg.copy(frt2, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
        //        totAmtfrt02 = add(totAmtfrt02, frt2.calcPrcAmtRate.getValue());
        //        line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        //
        //        frt3 = setTaxRows(param, line, sumLine, itemPMsg.taxCalculateOutputLine.no(i), PRC_COND_TP.FREIGHT_TAX3);
        //        EZDMsg.copy(frt3, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
        //        totAmtfrt03 = add(totAmtfrt03, frt3.calcPrcAmtRate.getValue());
        //        line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
        //    }
        //    i++;
        //}
        //
        //}
        NWZC036101_taxCalculateOutputLinePMsg value = null;
        Map<List<String>, NWZC036101_taxCalculateOutputLinePMsg> map = new HashMap<List<String>, NWZC036101_taxCalculateOutputLinePMsg>();
        for(int i = 0; i < itemPMsg.taxCalculateOutputLine.getValidCount(); i++){
            NWZC036101_taxCalculateInputLinePMsg inputLine = itemPMsg.taxCalculateInputLine.no(i);
            NWZC036101_taxCalculateOutputLinePMsg outputLine = itemPMsg.taxCalculateOutputLine.no(i);
            List<String> key = new ArrayList<String>();
            key.add(inputLine.trxLineNum.getValue());
            key.add(inputLine.prcCondTpCd.getValue());
            value = map.get(key);
            if (value == null) {
                map.put(key, outputLine);
            } else {
                ZYPEZDItemValueSetter.setValue(value.taxAmt_01, add(value.taxAmt_01.getValue(), outputLine.taxAmt_01.getValue()));
                ZYPEZDItemValueSetter.setValue(value.taxAmt_02, add(value.taxAmt_02.getValue(), outputLine.taxAmt_02.getValue()));
                ZYPEZDItemValueSetter.setValue(value.taxAmt_03, add(value.taxAmt_03.getValue(), outputLine.taxAmt_03.getValue()));
                if (compareTo(outputLine.taxPct_01.getValue(), value.taxPct_01.getValue()) > 0) {
                    ZYPEZDItemValueSetter.setValue(value.taxPct_01, outputLine.taxPct_01);
                }
                if (compareTo(outputLine.taxPct_02.getValue(), value.taxPct_02.getValue()) > 0) {
                    ZYPEZDItemValueSetter.setValue(value.taxPct_02, outputLine.taxPct_02);
                }
                if (compareTo(outputLine.taxPct_03.getValue(), value.taxPct_03.getValue()) > 0) {
                    ZYPEZDItemValueSetter.setValue(value.taxPct_03, outputLine.taxPct_03);
                }
            }
        }
        String trxLineNum = null;
        String prcCondTpCd = null;
        for (Map.Entry<List<String>, NWZC036101_taxCalculateOutputLinePMsg> entry : map.entrySet()) {
            List<String> key = entry.getKey();
            trxLineNum = key.get(0);
            prcCondTpCd = key.get(1);
            NWZC036101_taxCalculateOutputLinePMsg pMsg = entry.getValue();
            Integer idx = getLineIndex(param, trxLineNum, idxList);
            if (idx == null) {
                continue;
            }
            line = param.NWZC157002PMsg.no(idx.intValue());
            BigDecimal autoPrcAmtRate = BigDecimal.ZERO;
            BigDecimal calcPrcAmtRate = BigDecimal.ZERO;
            if (ZYPConstant.FLG_ON_Y.equals(param.xxSmryLineFlg.getValue())) {
                if (ZYPCommonFunc.hasValue(pMsg.taxPct_01)) {
                    autoPrcAmtRate = add(autoPrcAmtRate, pMsg.taxPct_01.getValue());
                }
                if (ZYPCommonFunc.hasValue(pMsg.taxPct_02)) {
                    autoPrcAmtRate = add(autoPrcAmtRate, pMsg.taxPct_02.getValue());
                }
                if (ZYPCommonFunc.hasValue(pMsg.taxPct_03)) {
                    autoPrcAmtRate = add(autoPrcAmtRate, pMsg.taxPct_03.getValue());
                }
                if (ZYPCommonFunc.hasValue(pMsg.taxAmt_01)) {
                    calcPrcAmtRate = add(calcPrcAmtRate, pMsg.taxAmt_01.getValue());
                }
                if (ZYPCommonFunc.hasValue(pMsg.taxAmt_02)) {
                    calcPrcAmtRate = add(calcPrcAmtRate, pMsg.taxAmt_02.getValue());
                }
                if (ZYPCommonFunc.hasValue(pMsg.taxAmt_03)) {
                    calcPrcAmtRate = add(calcPrcAmtRate, pMsg.taxAmt_03.getValue());
                }
                setTaxCalcBase(param, line, prcCondTpCd, autoPrcAmtRate, calcPrcAmtRate);
            } else {
                if (PRC_COND_TP.ITEM_TAX1.equals(prcCondTpCd)) {
                    setTaxCalcBase(param, line, PRC_COND_TP.ITEM_TAX1, pMsg.taxPct_01.getValue(), pMsg.taxAmt_01.getValue());
                    setTaxCalcBase(param, line, PRC_COND_TP.ITEM_TAX2, pMsg.taxPct_02.getValue(), pMsg.taxAmt_02.getValue());
                    setTaxCalcBase(param, line, PRC_COND_TP.ITEM_TAX3, pMsg.taxPct_03.getValue(), pMsg.taxAmt_03.getValue());
                } else if (PRC_COND_TP.FREIGHT_TAX1.equals(prcCondTpCd)) {
                    setTaxCalcBase(param, line, PRC_COND_TP.FREIGHT_TAX1, pMsg.taxPct_01.getValue(), pMsg.taxAmt_01.getValue());
                    setTaxCalcBase(param, line, PRC_COND_TP.FREIGHT_TAX2, pMsg.taxPct_02.getValue(), pMsg.taxAmt_02.getValue());
                    setTaxCalcBase(param, line, PRC_COND_TP.FREIGHT_TAX3, pMsg.taxPct_03.getValue(), pMsg.taxAmt_03.getValue());
                } else if (PRC_COND_TP.CHARGE_TAX1.equals(prcCondTpCd)) {
                    setTaxCalcBase(param, line, PRC_COND_TP.CHARGE_TAX1, pMsg.taxPct_01.getValue(), pMsg.taxAmt_01.getValue());
                    setTaxCalcBase(param, line, PRC_COND_TP.CHARGE_TAX2, pMsg.taxPct_02.getValue(), pMsg.taxAmt_02.getValue());
                    setTaxCalcBase(param, line, PRC_COND_TP.CHARGE_TAX3, pMsg.taxPct_03.getValue(), pMsg.taxAmt_03.getValue());
                }
            }
        }

        // QC#21841 2018/05/21 Mod End
    }
    // QC#21841 2018/05/21 Add Start
    private Integer getLineIndex(NWZC157001PMsg param, String trxLineNum, List<Integer> idxList) {
        if(!ZYPCommonFunc.hasValue(trxLineNum)){
            return null;
        }
        NWZC157002PMsg line = null;
        for (Integer idx : idxList) {
            line = param.NWZC157002PMsg.no(idx.intValue());
            if (line.trxLineNum.getValue().equals(trxLineNum)) {
                return idx;
            }
        }
        return null;
    }
    private void setTaxCalcBase(NWZC157001PMsg param, NWZC157002PMsg line, String prcCondTpCd, BigDecimal autoPrcAmtRate, BigDecimal calcPrcAmtRate) {
        if (calcPrcAmtRate == null || BigDecimal.ZERO.compareTo(calcPrcAmtRate) == 0) {
            return;
        }
        int cnt = line.NWZC157003PMsg.getValidCount();
        NWZC157003PMsg base = line.NWZC157003PMsg.no(cnt);
        ZYPEZDItemValueSetter.setValue(base.trxLineNum, line.trxLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(base.trxLineSubNum, line.trxLineSubNum.getValue());
        ZYPEZDItemValueSetter.setValue(base.configCatgCd, line.configCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(base.prcCondTpCd, prcCondTpCd);
        ZYPEZDItemValueSetter.setValue(base.prcCondTpDescTxt, getPrcCondTpDescTxt(param.glblCmpyCd.getValue(), prcCondTpCd));
        ZYPEZDItemValueSetter.setValue(base.prcDtlGrpCd, PRC_DTL_GRP.TAX);
        base.prcJrnlGrpCd.clear();
        base.prcCatgCd.clear();
        ZYPEZDItemValueSetter.setValue(base.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(base.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(base.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        base.pkgUomCd.clear();
        ZYPEZDItemValueSetter.setValue(base.prcCondUnitCd, PRC_COND_UNIT.PCT);
        base.prcCalcMethCd.clear();
        ZYPEZDItemValueSetter.setValue(base.autoPrcCcyCd, line.ccyCd.getValue());
        ZYPEZDItemValueSetter.setValue(base.autoPrcAmtRate, autoPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(base.calcPrcAmtRate, calcPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(base.unitPrcAmt, divide(param.glblCmpyCd.getValue(), calcPrcAmtRate, line.ordQty.getValue(), line.ccyCd.getValue()));

        base.maxPrcAmtRate.clear();
        base.minPrcAmtRate.clear();
        base.manPrcAmtRate.clear();
        base.dsMdsePrcPk.clear();
        ZYPEZDItemValueSetter.setValue(base.specCondPrcPk, getSpecCondPrcPk(param.glblCmpyCd.getValue(), base.prcCondTpCd.getValue()));
        base.frtPerWtPk.clear();
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(base.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        base.prcRulePrcdPk.clear();
        // QC#9700  2018/09/03 Add End
        line.NWZC157003PMsg.setValidCount(cnt + 1);
        return;
    }
    // QC#21841 2018/05/21 Add Start

    private NWZC157003PMsg setTaxRows(NWZC157001PMsg param, NWZC157002PMsg line, NWZC157004PMsg sumLine, NWZC036101_taxCalculateOutputLinePMsg pMsg, String prcCondTpCd) {
        NWZC157003PMsg base = new NWZC157003PMsg();
        ZYPEZDItemValueSetter.setValue(base.trxLineNum, line.trxLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(base.trxLineSubNum, line.trxLineSubNum.getValue());
        ZYPEZDItemValueSetter.setValue(base.configCatgCd, line.configCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(base.prcCondTpCd, prcCondTpCd);
        ZYPEZDItemValueSetter.setValue(base.prcCondTpDescTxt, getPrcCondTpDescTxt(param.glblCmpyCd.getValue(), prcCondTpCd));
        ZYPEZDItemValueSetter.setValue(base.prcDtlGrpCd, PRC_DTL_GRP.TAX);
        base.prcJrnlGrpCd.clear();
        base.prcCatgCd.clear();
        ZYPEZDItemValueSetter.setValue(base.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(base.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(base.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        base.pkgUomCd.clear();
        ZYPEZDItemValueSetter.setValue(base.prcCondUnitCd, PRC_COND_UNIT.PCT);
        base.prcCalcMethCd.clear();
        ZYPEZDItemValueSetter.setValue(base.autoPrcCcyCd, line.ccyCd.getValue());

        BigDecimal autoPrcAmtRate = BigDecimal.ZERO;
        BigDecimal calcPrcAmtRate = BigDecimal.ZERO;
        if (ZYPConstant.FLG_ON_Y.equals(param.xxSmryLineFlg.getValue())) {
            if (ZYPCommonFunc.hasValue(pMsg.taxPct_01)) {
                autoPrcAmtRate = add(autoPrcAmtRate, pMsg.taxPct_01.getValue());
            }
            if (ZYPCommonFunc.hasValue(pMsg.taxPct_02)) {
                autoPrcAmtRate = add(autoPrcAmtRate, pMsg.taxPct_02.getValue());
            }
            if (ZYPCommonFunc.hasValue(pMsg.taxPct_03)) {
                autoPrcAmtRate = add(autoPrcAmtRate, pMsg.taxPct_03.getValue());
            }
            if (ZYPCommonFunc.hasValue(pMsg.taxAmt_01)) {
                calcPrcAmtRate = add(calcPrcAmtRate, pMsg.taxAmt_01.getValue());
            }
            if (ZYPCommonFunc.hasValue(pMsg.taxAmt_02)) {
                calcPrcAmtRate = add(calcPrcAmtRate, pMsg.taxAmt_02.getValue());
            }
            if (ZYPCommonFunc.hasValue(pMsg.taxAmt_03)) {
                calcPrcAmtRate = add(calcPrcAmtRate, pMsg.taxAmt_03.getValue());
            }
        } else {
          if (PRC_COND_TP.ITEM_TAX1.equals(prcCondTpCd) || PRC_COND_TP.FREIGHT_TAX1.equals(prcCondTpCd)) {
              if (ZYPCommonFunc.hasValue(pMsg.taxPct_01)) {
                  autoPrcAmtRate = pMsg.taxPct_01.getValue();
                  calcPrcAmtRate = pMsg.taxAmt_01.getValue();
              }
            } else if (PRC_COND_TP.ITEM_TAX2.equals(prcCondTpCd) || PRC_COND_TP.FREIGHT_TAX2.equals(prcCondTpCd)) {
                if (ZYPCommonFunc.hasValue(pMsg.taxPct_02)) {
                    autoPrcAmtRate = pMsg.taxPct_02.getValue();
                    calcPrcAmtRate = pMsg.taxAmt_02.getValue();
                }
            } else if (PRC_COND_TP.ITEM_TAX3.equals(prcCondTpCd) || PRC_COND_TP.FREIGHT_TAX3.equals(prcCondTpCd)) {
                if (ZYPCommonFunc.hasValue(pMsg.taxPct_03)) {
                    autoPrcAmtRate = pMsg.taxPct_03.getValue();
                    calcPrcAmtRate = pMsg.taxAmt_03.getValue();
                }
            } else if (PRC_COND_TP.FREIGHT_TAX1.equals(prcCondTpCd)) {
                if (ZYPCommonFunc.hasValue(pMsg.taxPct_01)) {
                    autoPrcAmtRate = pMsg.taxPct_01.getValue();
                    calcPrcAmtRate = multiply(param.glblCmpyCd.getValue(), autoPrcAmtRate.divide(PERCENT), sumLine.xxTotFrtAmt.getValue(), line.ccyCd.getValue());
                }
            } else if (PRC_COND_TP.FREIGHT_TAX2.equals(prcCondTpCd)) {
                if (ZYPCommonFunc.hasValue(pMsg.taxPct_02)) {
                    autoPrcAmtRate = pMsg.taxPct_02.getValue();
                    calcPrcAmtRate = multiply(param.glblCmpyCd.getValue(), autoPrcAmtRate.divide(PERCENT), sumLine.xxTotFrtAmt.getValue(), line.ccyCd.getValue());
                }
            } else if (PRC_COND_TP.FREIGHT_TAX3.equals(prcCondTpCd)) {
                if (ZYPCommonFunc.hasValue(pMsg.taxPct_03)) {
                    autoPrcAmtRate = pMsg.taxPct_03.getValue();
                    calcPrcAmtRate = multiply(param.glblCmpyCd.getValue(), autoPrcAmtRate.divide(PERCENT), sumLine.xxTotFrtAmt.getValue(), line.ccyCd.getValue());
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(base.autoPrcAmtRate, autoPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(base.calcPrcAmtRate, calcPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(base.unitPrcAmt, divide(param.glblCmpyCd.getValue(), calcPrcAmtRate, line.ordQty.getValue(), line.ccyCd.getValue()));

        base.maxPrcAmtRate.clear();
        base.minPrcAmtRate.clear();
        base.manPrcAmtRate.clear();
        base.dsMdsePrcPk.clear();
        ZYPEZDItemValueSetter.setValue(base.specCondPrcPk, getSpecCondPrcPk(param.glblCmpyCd.getValue(), base.prcCondTpCd.getValue()));
        base.frtPerWtPk.clear();
        return base;
    }

    private void deleteTaxCalcBase(NWZC157002PMsg line) {
        NWZC157003PMsg calcBase= null;
        List<Integer> deleteList =  new ArrayList<Integer>();
        for (int i = 0; i < line.NWZC157003PMsg.getValidCount(); i++) {
            calcBase = line.NWZC157003PMsg.no(i);
            if (PRC_DTL_GRP.TAX.equals(calcBase.prcDtlGrpCd.getValue())) {
                deleteList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(line.NWZC157003PMsg, deleteList);
    }

    private void editCalcBaseForTax(NWZC157001PMsg param,
                                      NWZC157002PMsg line,
                                      NWZC036101PMsg itemPMsg,
                                      NWZC036101PMsg frtPMsg) {

        // NWZC157003PMsg calcBase = new NWZC157003PMsg();
        NWZC157003PMsg item1 = null;
        NWZC157003PMsg item2 = null;
        NWZC157003PMsg item3 = null;
        NWZC157003PMsg frt1 = null;
        NWZC157003PMsg frt2 = null;
        NWZC157003PMsg frt3 = null;

        if (ZYPConstant.FLG_ON_Y.equals(param.xxSmryLineFlg.getValue())) {
            if (itemPMsg != null) {
                item1 = setTaxRows(param, line, itemPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.ITEM_TAX1);
                if (BigDecimal.ZERO.compareTo(item1.calcPrcAmtRate.getValue()) != 0) {
                    EZDMsg.copy(item1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
                    line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
                }
            }
            if (frtPMsg != null) {
                frt1 = setTaxRows(param, line, frtPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.FREIGHT_TAX1);
                if (BigDecimal.ZERO.compareTo(frt1.calcPrcAmtRate.getValue()) != 0) {
                    EZDMsg.copy(frt1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
                    line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
                }
            }
        } else {
            if (itemPMsg != null) {
                item1 = setTaxRows(param, line, itemPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.ITEM_TAX1);
                EZDMsg.copy(item1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
                line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);

                item2 = setTaxRows(param, line, itemPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.ITEM_TAX2);
                EZDMsg.copy(item2, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
                line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);

                item3 = setTaxRows(param, line, itemPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.ITEM_TAX3);
                EZDMsg.copy(item3, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
                line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
            }
            if (frtPMsg != null) {
                frt1 = setTaxRows(param, line, frtPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.FREIGHT_TAX1);
                EZDMsg.copy(frt1, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
                line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);

                frt2 = setTaxRows(param, line, frtPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.FREIGHT_TAX2);
                EZDMsg.copy(frt2, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
                line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);

                frt3 = setTaxRows(param, line, frtPMsg.taxCalculateOutputLine.no(0), PRC_COND_TP.FREIGHT_TAX3);
                EZDMsg.copy(frt3, null, line.NWZC157003PMsg.no(line.NWZC157003PMsg.getValidCount()), null);
                line.NWZC157003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount() + 1);
            }

        }
    }

    private NWZC157003PMsg setTaxRows(NWZC157001PMsg param, NWZC157002PMsg line, NWZC036101_taxCalculateOutputLinePMsg pMsg, String prcCondTpCd) {
        NWZC157003PMsg base = new NWZC157003PMsg();
        ZYPEZDItemValueSetter.setValue(base.trxLineNum, line.trxLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(base.trxLineSubNum, line.trxLineSubNum.getValue());
        ZYPEZDItemValueSetter.setValue(base.configCatgCd, line.configCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(base.prcCondTpCd, prcCondTpCd);
        ZYPEZDItemValueSetter.setValue(base.prcCondTpDescTxt, getPrcCondTpDescTxt(param.glblCmpyCd.getValue(), prcCondTpCd));
        ZYPEZDItemValueSetter.setValue(base.prcDtlGrpCd, PRC_DTL_GRP.TAX);
        base.prcJrnlGrpCd.clear();
        base.prcCatgCd.clear();
        ZYPEZDItemValueSetter.setValue(base.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(base.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(base.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        base.pkgUomCd.clear();
        ZYPEZDItemValueSetter.setValue(base.prcCondUnitCd, PRC_COND_UNIT.PCT);
        base.prcCalcMethCd.clear();
        ZYPEZDItemValueSetter.setValue(base.autoPrcCcyCd, line.ccyCd.getValue());

        BigDecimal autoPrcAmtRate = round(BigDecimal.ZERO, RATE_DIGIT);
        BigDecimal unitPrcAmt = round(BigDecimal.ZERO, getDealCcyDigit(param.glblCmpyCd.getValue(), line.ccyCd.getValue()));
        if (ZYPConstant.FLG_ON_Y.equals(param.xxSmryLineFlg.getValue())) {

            if (ZYPCommonFunc.hasValue(pMsg.taxPct_01)) {
                autoPrcAmtRate = add(autoPrcAmtRate, pMsg.taxPct_01.getValue());
            }
            if (ZYPCommonFunc.hasValue(pMsg.taxPct_02)) {
                autoPrcAmtRate = add(autoPrcAmtRate, pMsg.taxPct_02.getValue());
            }
            if (ZYPCommonFunc.hasValue(pMsg.taxPct_03)) {
                autoPrcAmtRate = add(autoPrcAmtRate, pMsg.taxPct_03.getValue());
            }
            if (ZYPCommonFunc.hasValue(pMsg.taxAmt_01)) {
                unitPrcAmt = add(unitPrcAmt, pMsg.taxAmt_01.getValue());
            }
            if (ZYPCommonFunc.hasValue(pMsg.taxAmt_02)) {
                unitPrcAmt = add(unitPrcAmt, pMsg.taxAmt_02.getValue());
            }
            if (ZYPCommonFunc.hasValue(pMsg.taxAmt_03)) {
                unitPrcAmt = add(unitPrcAmt, pMsg.taxAmt_03.getValue());
            }
        } else {
          if (PRC_COND_TP.ITEM_TAX1.equals(prcCondTpCd) || PRC_COND_TP.FREIGHT_TAX1.equals(prcCondTpCd)) {
              if (ZYPCommonFunc.hasValue(pMsg.taxPct_01)) {
                  autoPrcAmtRate = pMsg.taxPct_01.getValue();
                  unitPrcAmt = pMsg.taxAmt_01.getValue();
              }
            } else if (PRC_COND_TP.ITEM_TAX2.equals(prcCondTpCd) || PRC_COND_TP.FREIGHT_TAX2.equals(prcCondTpCd)) {
                if (ZYPCommonFunc.hasValue(pMsg.taxPct_02)) {
                    autoPrcAmtRate = pMsg.taxPct_02.getValue();
                    unitPrcAmt = pMsg.taxAmt_02.getValue();
                }
            } else if (PRC_COND_TP.ITEM_TAX3.equals(prcCondTpCd) || PRC_COND_TP.FREIGHT_TAX3.equals(prcCondTpCd)) {
                if (ZYPCommonFunc.hasValue(pMsg.taxPct_03)) {
                    autoPrcAmtRate = pMsg.taxPct_03.getValue();
                    unitPrcAmt = pMsg.taxAmt_03.getValue();
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(base.autoPrcAmtRate, autoPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(base.calcPrcAmtRate, unitPrcAmt);
        ZYPEZDItemValueSetter.setValue(base.unitPrcAmt, divide(param.glblCmpyCd.getValue(), unitPrcAmt, line.ordQty.getValue(), line.ccyCd.getValue()));

        base.maxPrcAmtRate.clear();
        base.minPrcAmtRate.clear();
        base.manPrcAmtRate.clear();
        base.dsMdsePrcPk.clear();
        ZYPEZDItemValueSetter.setValue(base.specCondPrcPk, getSpecCondPrcPk(param.glblCmpyCd.getValue(), base.prcCondTpCd.getValue()));
        base.frtPerWtPk.clear();
        return base;
    }

    // API Call
    private NWZC170001PMsg callFromulaApi(NWZC157001PMsg param, NWZC157002PMsg line, Map<String, Object> map, ONBATCH_TYPE onBatchType) {
        NWZC170001PMsg pMsg = new NWZC170001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC170001.MODE_NO_BASE);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, line.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.prcFmlaPk, (BigDecimal) map.get("PRC_FMLA_PK"));
        ZYPEZDItemValueSetter.setValue(pMsg.ccyCd, line.ccyCd.getValue());

        String prcQlfyTpCd = (String) map.get("PRC_QLFY_TP_CD");
        String prcQlfyValTxt = (String) map.get("PRC_QLFY_VAL_TXT");

        if (PRC_QLFY_TP.ITEM_CODE.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, line.mdseCd);
            ZYPEZDItemValueSetter.setValue(pMsg.ordTakeMdseCd, line.ordTakeMdseCd);
        } else if (PRC_QLFY_TP.MERCHANDISE_TYPE.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.coaMdseTpCd, prcQlfyValTxt);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.firstProdCtrlCd, prcQlfyValTxt);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.scdProdCtrlCd, prcQlfyValTxt);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.thirdProdCtrlCd, prcQlfyValTxt);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.frthProdCtrlCd, prcQlfyValTxt);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_5.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.fifthProdCtrlCd, prcQlfyValTxt);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.ordCustUomQty, line.ordCustUomQty.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.pkgUomCd, (String) map.get("PKG_UOM_CD"));

        new NWZC170001().execute(pMsg, onBatchType);
        return pMsg;
    }

//    private NWXC100001EditPriceAmountByPmtTermInfo callNWXC100001EditPriceAmountByPmtTerm(NWZC157001PMsg param, NWZC157002PMsg line, BigDecimal amt) {
//        NWXC100001EditPriceAmountByPmtTermInfo editPrcAmtByPmtTermInfo = new NWXC100001EditPriceAmountByPmtTermInfo();
//        editPrcAmtByPmtTermInfo.setGlblCmpyCd(param.glblCmpyCd.getValue());
//        editPrcAmtByPmtTermInfo.setMdseCd(line.mdseCd.getValue());
//        editPrcAmtByPmtTermInfo.setSlsDt(line.prcBaseDt.getValue());
//        editPrcAmtByPmtTermInfo.setCcyCd(line.ccyCd_PC.getValue());
//        editPrcAmtByPmtTermInfo.setToCcyCd(line.ccyCd.getValue());
//        editPrcAmtByPmtTermInfo.setDealNetPrcAmt(amt);
//        editPrcAmtByPmtTermInfo.setDealGrsPrcAmt(amt);
//        editPrcAmtByPmtTermInfo.setPmtTermCashDiscCd(line.pmtTermCashDiscCd.getValue());
//
//        // Exchange Currency (ccyCd -> toCcyCd)
//        return NWXC100001EditPriceAmountByPmtTerm.exchPrcAmt(editPrcAmtByPmtTermInfo);
//    }


    private NWXC014001PMsg callRuleDerivationApi(NWZC157001PMsg param, ONBATCH_TYPE onBatchType) {
        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal totalWeight = BigDecimal.ZERO;
        BigDecimal totalQty = BigDecimal.ZERO;
        BigDecimal basePriceUnitPriceAmount = BigDecimal.ZERO;

        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg line = param.NWZC157002PMsg.no(i);
            subTotal = subTotal.add(getBasePriceAmount(param.glblCmpyCd.getValue(), line));
            totalWeight = totalWeight.add(line.unitNetWt.getValue().multiply(line.ordQty.getValue()));
            totalQty = totalQty.add(line.ordQty.getValue());
        }

        NWXC014001PMsg pMsg = new NWXC014001PMsg();
        // Header
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd_PM, NWXC014001.PROCESS_MODE_AUTO);
        if (GET_ORDER_ALL.equals(param.xxModeCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd_PL, NWXC014001.PROCESS_LVL_ALL);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd_PL, NWXC014001.PROCESS_LVL_LINE);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd_MT, NWXC014001.MODIFY_TYPE_ALL);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, param.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.orgRqstDelyDt, param.orgRqstDelyDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.prcRuleTrxCatgCd, prcRuleTrxCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, param.dsOrdCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, param.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, param.lineBizTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, param.dsAcctNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, param.trxHdrNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, param.custIssPoNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, param.cpoSrcTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxTotAmt, subTotal);
        ZYPEZDItemValueSetter.setValue(pMsg.xxTotUnitNetWt, totalWeight);
        ZYPEZDItemValueSetter.setValue(pMsg.totQty, totalQty);
        ZYPEZDItemValueSetter.setValue(pMsg.dsPmtMethCd, param.dsPmtMethCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.negoDealAmt, param.negoDealAmt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.spclHdlgTpCd, param.spclHdlgTpCd.getValue());

        // line
        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg line = param.NWZC157002PMsg.no(i);
            NWXC014002PMsg lineMsg = pMsg.NWXC014002PMsg.no(i);
            basePriceUnitPriceAmount = getBasePriceAmount(pMsg.glblCmpyCd.getValue(), line);

            ZYPEZDItemValueSetter.setValue(lineMsg.dsOrdPosnNum, line.dsOrdPosnNum.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.trxLineNum, line.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.trxLineSubNum, line.trxLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.configCatgCd, line.configCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.prcBaseDt, line.prcBaseDt.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.shipToCustCd, line.shipToCustCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.dsAcctNum_SH, line.dsAcctNum_SH.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.dsAcctNum_BL, line.dsAcctNum_BL.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgCd, line.prcCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.ccyCd, line.ccyCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.csmpNum, line.csmpNum.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.dlrRefNum, line.dlrRefNum.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.prcContrNum, line.prcContrNum.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.coaBrCd, line.coaBrCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.mdseCd, line.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.pkgUomCd, line.pkgUomCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.entCpoDtlDealSlsAmt, basePriceUnitPriceAmount);
            ZYPEZDItemValueSetter.setValue(lineMsg.entDealNetUnitPrcAmt, basePriceUnitPriceAmount);
            ZYPEZDItemValueSetter.setValue(lineMsg.dsOrdLineCatgCd, line.dsOrdLineCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.ordQty, line.ordQty.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.invQty, line.invQty.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.unitNetWt, line.unitNetWt.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.rtrnRsnCd, line.rtrnRsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.mdlId, line.mdlId.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.prcSvcZoneCd, line.prcSvcZoneCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.svcCallTpCd, line.svcCallTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.svcCallIncdtDt, line.svcCallIncdtDt.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.frtCondCd, line.frtCondCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.stCd, line.stCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.ctryCd, line.ctryCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.postCd, line.postCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.shpgSvcLvlCd, line.shpgSvcLvlCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.frtChrgToCd, line.frtChrgToCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.frtChrgMethCd, line.frtChrgMethCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.xxPrcCloFlg, line.xxPrcCloFlg.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.coaExtnCd, line.coaExtnCd.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhCd, line.rtlSwhCd.getValue()); // QC#22371 2017/12/26 Add

            // calcBase
            for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg calcBase = line.NWZC157003PMsg.no(j);
                EZDMsg.copy(calcBase, null, lineMsg.NWXC014003PMsg.no(j), null);
            }
            lineMsg.NWXC014003PMsg.setValidCount(line.NWZC157003PMsg.getValidCount());
        }
        pMsg.NWXC014002PMsg.setValidCount(param.NWZC157002PMsg.getValidCount());

        new NWXC014001().execute(pMsg, onBatchType);
        return pMsg;
    }

    // QC#19244 2017/07/04 Del Start
//    private NWZC036101PMsg callTaxCalcurationApi(NWZC157001PMsg param, NWZC157002PMsg line, NWZC157004PMsg sumLine, String mdseCd, String svcAllocTrxTpNm, boolean flg, ONBATCH_TYPE onBatchType) {
//        NWZC036101PMsg pMsg = new NWZC036101PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, line.prcBaseDt.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC036101Constant.PROC_MODE_QUOTATION);
//        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_SE, param.dsAcctNum.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.taxCalcFlg, param.taxFlg.getValue());
//
//        ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, line.dsAcctNum_BL.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.billToLocNum, line.locNum_BL.getValue());
//        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_BL)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_B, line.dsTaxGrpExemCd_BL.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_B, line.dsTaxGrpExemCd_B.getValue());
//        }
//        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemReslFlg_BL)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B, line.dsTaxGrpExemReslFlg_BL.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B, line.dsTaxGrpExemReslFlg_B.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_ST, line.dsAcctNum_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNum, line.locNum_SH.getValue());
//        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_SH)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_ST, line.dsTaxGrpExemCd_SH.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_ST, line.dsTaxGrpExemCd_S.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(pMsg.taxExemFlg, param.taxExemFlg.getValue());
//        if (ZYPConstant.FLG_ON_Y.equals(param.taxExemFlg.getValue())) {
//            ZYPEZDItemValueSetter.setValue(pMsg.taxExemRsnCd, param.taxExemRsnCd.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(pMsg.leasePrchOptCd, param.leasePrchOptCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.trxDt, line.prcBaseDt.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.xxTaxCalcHdrNum, param.trxHdrNum.getValue());
//
//        ZYPEZDItemValueSetter.setValue(pMsg.geoCd_ST, line.taxAreaId_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.dsInsdCtyLimitFlg_ST, line.dsInsdCtyLimitFlg_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.taxAreaId_ST, line.taxAreaId_SP.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_ST, line.firstLineAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_ST, line.scdLineAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_ST, line.ctyAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.stCd_ST, line.stCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_ST, line.cntyNm_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.postCd_ST, line.postCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_ST, line.ctryCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.geoCd_SR, line.taxAreaId_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.dsInsdCtyLimitFlg_SR, line.dsInsdCtyLimitFlg_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_SR, line.firstLineAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_SR, line.scdLineAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_SR, line.ctyAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.stCd_SR, line.stCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_SR, line.cntyNm_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.postCd_SR, line.postCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_SR, line.ctryCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.geoCd_SF, line.taxAreaId_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.whCd_SF, line.rtlWhCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_SF, line.firstLineAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_SF, line.scdLineAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_SF, line.ctyAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.stCd_SF, line.stCd_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_SF, line.cntyNm_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.postCd_SF, line.postCd_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_SF, line.ctryCd_SF.getValue());
//
//        NWZC036101_taxCalculateInputLinePMsg linePMsg = pMsg.taxCalculateInputLine.no(0);
//        ZYPEZDItemValueSetter.setValue(linePMsg.xxTaxCalcLineNum_A, line.trxLineNum.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.mdseCd_A, mdseCd);
//        ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTpCd_A, line.svcAllocTpCd.getValue());
//        if (ZYPCommonFunc.hasValue(line.svcAllocTrxTpNm.getValue())) {
//            ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTrxTpNm_A, line.svcAllocTrxTpNm.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
//        }
//        ZYPEZDItemValueSetter.setValue(linePMsg.taxExemTpCd_A, line.taxExemTpCd.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.shipQty_A, line.ordQty.getValue());
//        if (flg) {
//            ZYPEZDItemValueSetter.setValue(linePMsg.funcNetUnitPrcAmt_A, sumLine.xxTotBaseAmt.getValue());
//            ZYPEZDItemValueSetter.setValue(linePMsg.slsAmt_A, sumLine.xxTotNetPrcAmt.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(linePMsg.funcNetUnitPrcAmt_A, sumLine.xxUnitFrtAmt.getValue());
//            ZYPEZDItemValueSetter.setValue(linePMsg.slsAmt_A, sumLine.xxTotFrtAmt.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(linePMsg.billToAcctNum_A, line.dsAcctNum_BL.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.billToLocNum_A, line.locNum_BL.getValue());
//        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_BL)) {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AB, line.dsTaxGrpExemCd_BL.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AB, line.dsTaxGrpExemCd_B.getValue());
//        }
//        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemReslFlg_BL)) {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemReslFlg_AB, line.dsTaxGrpExemReslFlg_BL.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemReslFlg_AB, line.dsTaxGrpExemReslFlg_B.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(linePMsg.dsAcctNum_AT, line.dsAcctNum_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.shipToLocNum_A, line.locNum_SH.getValue());
//        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_SH)) {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AT, line.dsTaxGrpExemCd_SH.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AT, line.dsTaxGrpExemCd_S.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AT, line.taxAreaId_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.dsInsdCtyLimitFlg_AT, line.dsInsdCtyLimitFlg_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.taxAreaId_AT, line.taxAreaId_SP.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AT, line.firstLineAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AT, line.scdLineAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AT, line.ctyAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AT, line.stCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AT, line.cntyNm_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AT, line.postCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AT, line.ctryCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AR, line.taxAreaId_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.dsInsdCtyLimitFlg_AR, line.dsInsdCtyLimitFlg_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AR, line.firstLineAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AR, line.scdLineAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AR, line.ctyAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AR, line.stCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AR, line.cntyNm_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AR, line.postCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AR, line.ctryCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AF, line.taxAreaId_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.whCd_AF, line.rtlWhCd.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AF, line.firstLineAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AF, line.scdLineAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AF, line.ctyAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AF, line.stCd_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AF, line.cntyNm_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AF, line.postCd_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AF, line.ctryCd_SF.getValue());
//        pMsg.taxCalculateInputLine.setValidCount(1);
//
//        new NWZC036101().execute(pMsg, onBatchType);
//
//        return pMsg;
//    }
//    private NWZC036101PMsg callTaxCalcurationApi(NWZC157001PMsg param,
//                                                  NWZC157002PMsg line,
//                                                  BigDecimal unitNetPrice,
//                                                  BigDecimal netAmount,
//                                                  String mdseCd,
//                                                  String svcAllocTrxTpNm,
//                                                  ONBATCH_TYPE onBatchType) {
//        NWZC036101PMsg pMsg = new NWZC036101PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.prcBaseDt.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC036101Constant.PROC_MODE_QUOTATION);
//        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_SE, param.dsAcctNum.getValue());
//
//        if (ZYPCommonFunc.hasValue(param.taxFlg.getValue())) {
//            ZYPEZDItemValueSetter.setValue(pMsg.taxCalcFlg, param.taxFlg.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
//        }
//
//        ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, line.dsAcctNum_BL.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.billToLocNum, line.billToCustCd.getValue()); // QC#18663
//        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_BL)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_B, line.dsTaxGrpExemCd_BL.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B, line.dsTaxGrpExemReslFlg_BL.getValue()); // QC#18663
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_B, line.dsTaxGrpExemCd_B.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B, line.dsTaxGrpExemReslFlg_B.getValue()); // QC#18663
//        }
//        // QC#18663
////        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemReslFlg_BL)) {
////            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B, line.dsTaxGrpExemReslFlg_BL.getValue());
////        } else {
////            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B, line.dsTaxGrpExemReslFlg_B.getValue());
////        }
//        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_ST, line.dsAcctNum_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNum, line.shipToCustCd.getValue()); // QC#18663
//        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_SH)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_ST, line.dsTaxGrpExemCd_SH.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_ST, line.dsTaxGrpExemCd_S.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(pMsg.taxExemFlg, param.taxExemFlg.getValue());
//        if (ZYPConstant.FLG_ON_Y.equals(param.taxExemFlg.getValue())) {
//            ZYPEZDItemValueSetter.setValue(pMsg.taxExemRsnCd, param.taxExemRsnCd.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(pMsg.leasePrchOptCd, param.leasePrchOptCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.trxDt, param.prcBaseDt.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.xxTaxCalcHdrNum, param.trxHdrNum.getValue());
//
//        ZYPEZDItemValueSetter.setValue(pMsg.geoCd_ST, line.taxAreaId_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.dsInsdCtyLimitFlg_ST, line.dsInsdCtyLimitFlg_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.taxAreaId_ST, line.taxAreaId_SP.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_ST, line.firstLineAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_ST, line.scdLineAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_ST, line.ctyAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.stCd_ST, line.stCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_ST, line.cntyNm_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.postCd_ST, line.postCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_ST, line.ctryCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.geoCd_SR, line.taxAreaId_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.dsInsdCtyLimitFlg_SR, line.dsInsdCtyLimitFlg_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_SR, line.firstLineAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_SR, line.scdLineAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_SR, line.ctyAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.stCd_SR, line.stCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_SR, line.cntyNm_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.postCd_SR, line.postCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_SR, line.ctryCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.geoCd_SF, line.taxAreaId_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.whCd_SF, line.rtlWhCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_SF, line.firstLineAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_SF, line.scdLineAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_SF, line.ctyAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.stCd_SF, line.stCd_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_SF, line.cntyNm_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.postCd_SF, line.postCd_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_SF, line.ctryCd_SF.getValue());
//
//        NWZC036101_taxCalculateInputLinePMsg linePMsg = pMsg.taxCalculateInputLine.no(0);
//        ZYPEZDItemValueSetter.setValue(linePMsg.xxTaxCalcLineNum_A, line.trxLineNum.getValue());
//        if (ZYPCommonFunc.hasValue(mdseCd)) {
//            ZYPEZDItemValueSetter.setValue(linePMsg.mdseCd_A, mdseCd);
//        } else {
//            ZYPEZDItemValueSetter.setValue(linePMsg.mdseCd_A, line.mdseCd.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTpCd_A, line.svcAllocTpCd.getValue());
//        if (ZYPCommonFunc.hasValue(line.svcAllocTrxTpNm.getValue())) {
//            ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTrxTpNm_A, line.svcAllocTrxTpNm.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
//        }
//        // QC#18755 2017/05/30 Add Start
//        // ZYPEZDItemValueSetter.setValue(linePMsg.taxExemTpCd_A, line.taxExemTpCd.getValue());
//        if (ZYPCommonFunc.hasValue(mdseCd)) {
//            ALL_MDSE_VTMsg tMsg = getAllMdseV(pMsg.glblCmpyCd.getValue(), mdseCd);
//            ZYPEZDItemValueSetter.setValue(linePMsg.taxExemTpCd_A, tMsg.taxExemTpCd.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(linePMsg.taxExemTpCd_A, line.taxExemTpCd.getValue());
//        }
//        // QC#18755 2017/05/30 Add End
//        ZYPEZDItemValueSetter.setValue(linePMsg.shipQty_A, line.ordQty.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.funcNetUnitPrcAmt_A, unitNetPrice);
//        ZYPEZDItemValueSetter.setValue(linePMsg.slsAmt_A, netAmount);
//        ZYPEZDItemValueSetter.setValue(linePMsg.billToAcctNum_A, line.dsAcctNum_BL.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.billToLocNum_A, line.billToCustCd.getValue()); // QC#18663
//        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_BL)) {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AB, line.dsTaxGrpExemCd_BL.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AB, line.dsTaxGrpExemCd_B.getValue());
//        }
//        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemReslFlg_BL)) {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemReslFlg_AB, line.dsTaxGrpExemReslFlg_BL.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemReslFlg_AB, line.dsTaxGrpExemReslFlg_B.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(linePMsg.dsAcctNum_AT, line.dsAcctNum_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.shipToLocNum_A, line.shipToCustCd.getValue()); // QC#18663
//        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_SH)) {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AT, line.dsTaxGrpExemCd_SH.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AT, line.dsTaxGrpExemCd_S.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AT, line.taxAreaId_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.dsInsdCtyLimitFlg_AT, line.dsInsdCtyLimitFlg_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.taxAreaId_AT, line.taxAreaId_SP.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AT, line.firstLineAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AT, line.scdLineAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AT, line.ctyAddr_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AT, line.stCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AT, line.cntyNm_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AT, line.postCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AT, line.ctryCd_SH.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AR, line.taxAreaId_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.dsInsdCtyLimitFlg_AR, line.dsInsdCtyLimitFlg_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AR, line.firstLineAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AR, line.scdLineAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AR, line.ctyAddr_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AR, line.stCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AR, line.cntyNm_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AR, line.postCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AR, line.ctryCd_SR.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AF, line.taxAreaId_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.whCd_AF, line.rtlWhCd.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AF, line.firstLineAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AF, line.scdLineAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AF, line.ctyAddr_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AF, line.stCd_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AF, line.cntyNm_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AF, line.postCd_SF.getValue());
//        ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AF, line.ctryCd_SF.getValue());
//        pMsg.taxCalculateInputLine.setValidCount(1);
//
//        new NWZC036101().execute(pMsg, onBatchType);
//        if (S21ApiUtil.isXxMsgId(pMsg)) {
//            S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
//            lineMap.addXxMsgIdWithPrm(NWZM2007W, null);
//            lineMap.flush();
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//            S21LoggerFactory.getLogger(NWZC157001.class).info("Tax Calculation API has Error. Message = " + msgList.get(0).getXxMsgid());
//            return null;
//        }
//
//        return pMsg;
//    }
    // QC#19244 2017/07/04 Del End
    private NWZC036101PMsg editTaxCalcurationApiIntfc(NWZC157001PMsg param, List<Integer> idxList, String mdseCd, String svcAllocTrxTpNm) {

        NWZC036101PMsg pMsg = new NWZC036101PMsg();
        Map<String, String> frtMdseMap = getChgMdse(param.glblCmpyCd.getValue());
        int index = 0;
        for(int i  = 0; i <  idxList.size(); i++){
            NWZC157002PMsg ln = param.NWZC157002PMsg.no(idxList.get(i));
            if(ZYPCommonFunc.hasValue(ln.rtlWhCd.getValue())){
                index = i;
                break;
            }
        }
        NWZC157002PMsg line = param.NWZC157002PMsg.no(idxList.get(index));
        NWZC157004PMsg sumLine = param.NWZC157004PMsg.no(idxList.get(index));

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC036101Constant.PROC_MODE_QUOTATION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_SE, param.dsAcctNum.getValue());

        if (ZYPCommonFunc.hasValue(param.taxFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.taxCalcFlg, param.taxFlg.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, line.dsAcctNum_BL.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.billToLocNum, line.billToCustCd.getValue()); // QC#18663
        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_BL)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_B, line.dsTaxGrpExemCd_BL.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B, line.dsTaxGrpExemReslFlg_BL.getValue()); // QC#18663
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_B, line.dsTaxGrpExemCd_B.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B, line.dsTaxGrpExemReslFlg_B.getValue()); // QC#18663
        }
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_ST, line.dsAcctNum_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNum, line.shipToCustCd.getValue()); // QC#18663
        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_SH)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_ST, line.dsTaxGrpExemCd_SH.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_ST, line.dsTaxGrpExemCd_S.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.taxExemFlg, param.taxExemFlg.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(param.taxExemFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.taxExemRsnCd, param.taxExemRsnCd.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.leasePrchOptCd, param.leasePrchOptCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.trxDt, param.prcBaseDt.getValue()); // QC#27883
        ZYPEZDItemValueSetter.setValue(pMsg.trxDt, line.prcBaseDt.getValue()); // QC#27883
        ZYPEZDItemValueSetter.setValue(pMsg.xxTaxCalcHdrNum, param.trxHdrNum.getValue());

        ZYPEZDItemValueSetter.setValue(pMsg.geoCd_ST, line.taxAreaId_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsInsdCtyLimitFlg_ST, line.dsInsdCtyLimitFlg_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.taxAreaId_ST, line.taxAreaId_SP.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_ST, line.firstLineAddr_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_ST, line.scdLineAddr_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_ST, line.ctyAddr_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.stCd_ST, line.stCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_ST, line.cntyNm_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.postCd_ST, line.postCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_ST, line.ctryCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.geoCd_SR, line.taxAreaId_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsInsdCtyLimitFlg_SR, line.dsInsdCtyLimitFlg_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_SR, line.firstLineAddr_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_SR, line.scdLineAddr_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_SR, line.ctyAddr_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.stCd_SR, line.stCd_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_SR, line.cntyNm_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.postCd_SR, line.postCd_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_SR, line.ctryCd_SR.getValue());
        // QC#21847 2017/10/17 Del Start
        //ZYPEZDItemValueSetter.setValue(pMsg.geoCd_SF, line.taxAreaId_SF.getValue());
        //ZYPEZDItemValueSetter.setValue(pMsg.whCd_SF, line.rtlWhCd.getValue());
        //ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_SF, line.firstLineAddr_SF.getValue());
        //ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_SF, line.scdLineAddr_SF.getValue());
        //ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_SF, line.ctyAddr_SF.getValue());
        //ZYPEZDItemValueSetter.setValue(pMsg.stCd_SF, line.stCd_SF.getValue());
        //ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_SF, line.cntyNm_SF.getValue());
        //ZYPEZDItemValueSetter.setValue(pMsg.postCd_SF, line.postCd_SF.getValue());
        //ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_SF, line.ctryCd_SF.getValue());
        // QC#21847 2017/10/17 Del End

        int i = 0;
        NWZC036101_taxCalculateInputLinePMsg linePMsg;
        for(int idx : idxList){
            line = param.NWZC157002PMsg.no(idx);
            sumLine = param.NWZC157004PMsg.no(idx);
            linePMsg = pMsg.taxCalculateInputLine.no(i);

            // QC#21106 2017/09/16 Mod Start
            // ZYPEZDItemValueSetter.setValue(linePMsg.xxTaxCalcLineNum_A, line.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.xxTaxCalcLineNum_A, Integer.toString(i + 1));
            // QC#21106 2017/09/16 Mod E n d 
            ZYPEZDItemValueSetter.setValue(linePMsg.mdseCd_A, line.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTpCd_A, line.svcAllocTpCd.getValue());
            if (ZYPCommonFunc.hasValue(line.svcAllocTrxTpNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTrxTpNm_A, line.svcAllocTrxTpNm.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
            }
            ZYPEZDItemValueSetter.setValue(linePMsg.taxExemTpCd_A, line.taxExemTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.shipQty_A, line.ordQty.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.funcNetUnitPrcAmt_A, sumLine.xxUnitNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(linePMsg.slsAmt_A, sumLine.xxTotNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(linePMsg.billToAcctNum_A, line.dsAcctNum_BL.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.billToLocNum_A, line.billToCustCd.getValue()); // QC#18663
            if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_BL)) {
                ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AB, line.dsTaxGrpExemCd_BL.getValue());
                ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemReslFlg_AB, line.dsTaxGrpExemReslFlg_BL.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AB, line.dsTaxGrpExemCd_B.getValue());
                ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemReslFlg_AB, line.dsTaxGrpExemReslFlg_B.getValue());
            }
            ZYPEZDItemValueSetter.setValue(linePMsg.dsAcctNum_AT, line.dsAcctNum_SH.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.shipToLocNum_A, line.shipToCustCd.getValue()); // QC#18663
            if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_SH)) {
                ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AT, line.dsTaxGrpExemCd_SH.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AT, line.dsTaxGrpExemCd_S.getValue());
            }
            ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AT, line.taxAreaId_SH.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.dsInsdCtyLimitFlg_AT, line.dsInsdCtyLimitFlg_SH.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.taxAreaId_AT, line.taxAreaId_SP.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AT, line.firstLineAddr_SH.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AT, line.scdLineAddr_SH.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AT, line.ctyAddr_SH.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AT, line.stCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AT, line.cntyNm_SH.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AT, line.postCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AT, line.ctryCd_SH.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AR, line.taxAreaId_SR.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.dsInsdCtyLimitFlg_AR, line.dsInsdCtyLimitFlg_SR.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AR, line.firstLineAddr_SR.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AR, line.scdLineAddr_SR.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AR, line.ctyAddr_SR.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AR, line.stCd_SR.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AR, line.cntyNm_SR.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AR, line.postCd_SR.getValue());
            ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AR, line.ctryCd_SR.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(line.invtyCtrlFlg.getValue())) { // QC#21814 2017/10/17 Add
                ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AF, line.taxAreaId_SF.getValue());
                ZYPEZDItemValueSetter.setValue(linePMsg.whCd_AF, line.rtlWhCd.getValue());
                ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AF, line.firstLineAddr_SF.getValue());
                ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AF, line.scdLineAddr_SF.getValue());
                ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AF, line.ctyAddr_SF.getValue());
                ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AF, line.stCd_SF.getValue());
                ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AF, line.cntyNm_SF.getValue());
                ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AF, line.postCd_SF.getValue());
                ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AF, line.ctryCd_SF.getValue());
            }
            // QC#21841 2018/05/21 Add Start
            ZYPEZDItemValueSetter.setValue(linePMsg.trxLineNum, line.trxLineNum);
            ZYPEZDItemValueSetter.setValue(linePMsg.prcCondTpCd, PRC_COND_TP.ITEM_TAX1);
            // QC#21841 2018/05/21 Add End
            i++;
            // QC#21841 2018/05/21 Del Start
            // QC#21106 2017/09/16 Add Start
            //for Freight
            //linePMsg = pMsg.taxCalculateInputLine.no(i);
            //ZYPEZDItemValueSetter.setValue(linePMsg.xxTaxCalcLineNum_A, Integer.toString(i + 1));
            //ZYPEZDItemValueSetter.setValue(linePMsg.mdseCd_A, mdseCd);
            //ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTpCd_A, line.svcAllocTpCd.getValue());
            //if (ZYPCommonFunc.hasValue(line.svcAllocTrxTpNm.getValue())) {
            //    ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTrxTpNm_A, line.svcAllocTrxTpNm.getValue());
            //} else {
            //    ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
            //}
            //ZYPEZDItemValueSetter.setValue(linePMsg.taxExemTpCd_A, line.taxExemTpCd.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.shipQty_A, line.ordQty.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.funcNetUnitPrcAmt_A, sumLine.xxUnitFrtAmt);
            //ZYPEZDItemValueSetter.setValue(linePMsg.slsAmt_A, sumLine.xxTotFrtAmt);
            //ZYPEZDItemValueSetter.setValue(linePMsg.billToAcctNum_A, line.dsAcctNum_BL.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.billToLocNum_A, line.billToCustCd.getValue());
            //if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_BL)) {
            //    ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AB, line.dsTaxGrpExemCd_BL.getValue());
            //    ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemReslFlg_AB, line.dsTaxGrpExemReslFlg_BL.getValue());
            //} else {
            //    ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AB, line.dsTaxGrpExemCd_B.getValue());
            //    ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemReslFlg_AB, line.dsTaxGrpExemReslFlg_B.getValue());
            //}
            //ZYPEZDItemValueSetter.setValue(linePMsg.dsAcctNum_AT, line.dsAcctNum_SH.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.shipToLocNum_A, line.shipToCustCd.getValue());
            //if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_SH)) {
            //    ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AT, line.dsTaxGrpExemCd_SH.getValue());
            //} else {
            //    ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AT, line.dsTaxGrpExemCd_S.getValue());
            //}
            //ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AT, line.taxAreaId_SH.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.dsInsdCtyLimitFlg_AT, line.dsInsdCtyLimitFlg_SH.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.taxAreaId_AT, line.taxAreaId_SP.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AT, line.firstLineAddr_SH.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AT, line.scdLineAddr_SH.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AT, line.ctyAddr_SH.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AT, line.stCd_SH.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AT, line.cntyNm_SH.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AT, line.postCd_SH.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AT, line.ctryCd_SH.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AR, line.taxAreaId_SR.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.dsInsdCtyLimitFlg_AR, line.dsInsdCtyLimitFlg_SR.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AR, line.firstLineAddr_SR.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AR, line.scdLineAddr_SR.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AR, line.ctyAddr_SR.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AR, line.stCd_SR.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AR, line.cntyNm_SR.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AR, line.postCd_SR.getValue());
            //ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AR, line.ctryCd_SR.getValue());
            //if (ZYPConstant.FLG_ON_Y.equals(line.invtyCtrlFlg.getValue())) { // QC#21814 2017/10/17 Add
            //    ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AF, line.taxAreaId_SF.getValue());
            //    ZYPEZDItemValueSetter.setValue(linePMsg.whCd_AF, line.rtlWhCd.getValue());
            //    ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AF, line.firstLineAddr_SF.getValue());
            //    ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AF, line.scdLineAddr_SF.getValue());
            //    ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AF, line.ctyAddr_SF.getValue());
            //    ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AF, line.stCd_SF.getValue());
            //    ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AF, line.cntyNm_SF.getValue());
            //    ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AF, line.postCd_SF.getValue());
            //    ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AF, line.ctryCd_SF.getValue());
            //}
            //i++;
            // QC#21106 2017/09/16 Add End
            // QC#21841 2018/05/21 Del End
            // QC#21841 2018/05/21 Add Start
            Map<BigDecimal, NWZC157003PMsg> map = new HashMap<BigDecimal, NWZC157003PMsg>();
            NWZC157003PMsg target = null;
            for(int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++){
                NWZC157003PMsg calcBase = new NWZC157003PMsg();
                EZDMsg.copy(line.NWZC157003PMsg.no(j), null, calcBase, null);
                if (PRC_DTL_GRP.FREIGHT.equals(calcBase.prcDtlGrpCd.getValue())) {
                    map.put(calcBase.specCondPrcPk.getValue(), calcBase);
                } else if (PRC_DTL_GRP.SHIPPING_FEE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    map.put(calcBase.specCondPrcPk.getValue(), calcBase);
                } else if (PRC_DTL_GRP.FUEL_SURCHARGE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    map.put(calcBase.specCondPrcPk.getValue(), calcBase);
                } else if (PRC_DTL_GRP.HANDLING_FEE.equals(calcBase.prcDtlGrpCd.getValue())) {
                    map.put(calcBase.specCondPrcPk.getValue(), calcBase);
                } else if (PRC_DTL_GRP.RESTOCKING_FEE.equals(calcBase.prcDtlGrpCd.getValue())) {  // QC#27479 2018/08/03 Add
                    map.put(calcBase.specCondPrcPk.getValue(), calcBase);
                }
            }
            //Round Data
            for (int j = 0; j < line.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg calcBase = line.NWZC157003PMsg.no(j);
                if (PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(calcBase.prcDtlGrpCd.getValue()) || PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(calcBase.prcDtlGrpCd.getValue())) {
                    target = map.get(calcBase.specCondPrcPk.getValue());
                    if (target != null) {
                        ZYPEZDItemValueSetter.setValue(target.calcPrcAmtRate, target.calcPrcAmtRate.getValue().add(calcBase.calcPrcAmtRate.getValue()));
                    }
                }
            }
            for (Map.Entry<BigDecimal, NWZC157003PMsg> entry : map.entrySet()) {
                NWZC157003PMsg calcBase = entry.getValue();
                String chgMdseCd = frtMdseMap.get(calcBase.prcDtlGrpCd.getValue());
                if (!ZYPCommonFunc.hasValue(chgMdseCd)) {
                    continue;
                }
                NWZC036101_taxCalculateInputLinePMsg linePMsgCH = pMsg.taxCalculateInputLine.no(i);
                EZDMsg.copy(linePMsg, null, linePMsgCH, null);
                ZYPEZDItemValueSetter.setValue(linePMsg.xxTaxCalcLineNum_A, Integer.toString(i + 1));
                ZYPEZDItemValueSetter.setValue(linePMsg.mdseCd_A, chgMdseCd);
                ZYPEZDItemValueSetter.setValue(linePMsg.trxLineNum, line.trxLineNum);
                if (PRC_DTL_GRP.FREIGHT.equals(calcBase.prcDtlGrpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(linePMsg.prcCondTpCd, PRC_COND_TP.FREIGHT_TAX1);
                } else {
                    ZYPEZDItemValueSetter.setValue(linePMsg.prcCondTpCd, PRC_COND_TP.CHARGE_TAX1);
                }
                ZYPEZDItemValueSetter.setValue(linePMsg.funcNetUnitPrcAmt_A, calcBase.unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(linePMsg.slsAmt_A, calcBase.calcPrcAmtRate);

                // 2019/02/22 QC#30437 add start
                ALL_MDSE_VTMsg tMsg = getAllMdseV(pMsg.glblCmpyCd.getValue(), chgMdseCd);

                if ((tMsg != null) && (ZYPCommonFunc.hasValue(tMsg.taxExemTpCd))) {
                    ZYPEZDItemValueSetter.setValue(linePMsg.taxExemTpCd_A, tMsg.taxExemTpCd.getValue());
                } else {
                    S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
                    lineMap.addXxMsgIdWithPrm(NWZM0293E, null);
                    lineMap.flush();
                }
                // 2019/02/22 QC#30437 add end

                i++;
            }
            pMsg.taxCalculateInputLine.setValidCount(i);
            // QC#21841 2018/05/21 Add End
        }
        for (int n = 0; n < pMsg.taxCalculateInputLine.getValidCount(); n++) {
            NWZC036101_taxCalculateInputLinePMsg x = pMsg.taxCalculateInputLine.no(n);
            System.out.println("trxLineNum : " + x.trxLineNum.getValue() + " prcCondTpCd : " + x.prcCondTpCd.getValue() + " UnitPrc : " + x.funcNetUnitPrcAmt_A.getValue() + " Amt : " + x.slsAmt_A.getValue());
        }
        return pMsg;
    }

    private NWZC036101PMsg editTaxCalcurationApiIntfcForFrt(NWZC157001PMsg param, NWZC157002PMsg line, BigDecimal unitNetPrice, BigDecimal netAmount, String mdseCd, String svcAllocTrxTpNm) {
        NWZC036101PMsg pMsg = new NWZC036101PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC036101Constant.PROC_MODE_QUOTATION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_SE, param.dsAcctNum.getValue());

        if (ZYPCommonFunc.hasValue(param.taxFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.taxCalcFlg, param.taxFlg.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, line.dsAcctNum_BL.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.billToLocNum, line.billToCustCd.getValue()); // QC#18663
        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_BL)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_B, line.dsTaxGrpExemCd_BL.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B, line.dsTaxGrpExemReslFlg_BL.getValue()); // QC#18663
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_B, line.dsTaxGrpExemCd_B.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B, line.dsTaxGrpExemReslFlg_B.getValue()); // QC#18663
        }
        // QC#18663
        // if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemReslFlg_BL)) {
        // ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B,
        // line.dsTaxGrpExemReslFlg_BL.getValue());
        // } else {
        // ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemReslFlg_B,
        // line.dsTaxGrpExemReslFlg_B.getValue());
        // }
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_ST, line.dsAcctNum_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNum, line.shipToCustCd.getValue()); // QC#18663
        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_SH)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_ST, line.dsTaxGrpExemCd_SH.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.dsTaxGrpExemCd_ST, line.dsTaxGrpExemCd_S.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.taxExemFlg, param.taxExemFlg.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(param.taxExemFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.taxExemRsnCd, param.taxExemRsnCd.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.leasePrchOptCd, param.leasePrchOptCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.trxDt, param.prcBaseDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxTaxCalcHdrNum, param.trxHdrNum.getValue());

        ZYPEZDItemValueSetter.setValue(pMsg.geoCd_ST, line.taxAreaId_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsInsdCtyLimitFlg_ST, line.dsInsdCtyLimitFlg_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.taxAreaId_ST, line.taxAreaId_SP.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_ST, line.firstLineAddr_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_ST, line.scdLineAddr_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_ST, line.ctyAddr_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.stCd_ST, line.stCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_ST, line.cntyNm_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.postCd_ST, line.postCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_ST, line.ctryCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.geoCd_SR, line.taxAreaId_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsInsdCtyLimitFlg_SR, line.dsInsdCtyLimitFlg_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_SR, line.firstLineAddr_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_SR, line.scdLineAddr_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_SR, line.ctyAddr_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.stCd_SR, line.stCd_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_SR, line.cntyNm_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.postCd_SR, line.postCd_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_SR, line.ctryCd_SR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.geoCd_SF, line.taxAreaId_SF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.whCd_SF, line.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr_SF, line.firstLineAddr_SF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr_SF, line.scdLineAddr_SF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr_SF, line.ctyAddr_SF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.stCd_SF, line.stCd_SF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm_SF, line.cntyNm_SF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.postCd_SF, line.postCd_SF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd_SF, line.ctryCd_SF.getValue());

        NWZC036101_taxCalculateInputLinePMsg linePMsg = pMsg.taxCalculateInputLine.no(0);
        ZYPEZDItemValueSetter.setValue(linePMsg.xxTaxCalcLineNum_A, line.trxLineNum.getValue());
        if (ZYPCommonFunc.hasValue(mdseCd)) {
            ZYPEZDItemValueSetter.setValue(linePMsg.mdseCd_A, mdseCd);
        } else {
            ZYPEZDItemValueSetter.setValue(linePMsg.mdseCd_A, line.mdseCd.getValue());
        }
        ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTpCd_A, line.svcAllocTpCd.getValue());
        if (ZYPCommonFunc.hasValue(line.svcAllocTrxTpNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTrxTpNm_A, line.svcAllocTrxTpNm.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(linePMsg.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
        }
        // QC#18755 2017/05/30 Add Start
        // ZYPEZDItemValueSetter.setValue(linePMsg.taxExemTpCd_A,
        // line.taxExemTpCd.getValue());
        if (ZYPCommonFunc.hasValue(mdseCd)) {
            ALL_MDSE_VTMsg tMsg = getAllMdseV(pMsg.glblCmpyCd.getValue(), mdseCd);
            ZYPEZDItemValueSetter.setValue(linePMsg.taxExemTpCd_A, tMsg.taxExemTpCd.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(linePMsg.taxExemTpCd_A, line.taxExemTpCd.getValue());
        }
        // QC#18755 2017/05/30 Add End
        ZYPEZDItemValueSetter.setValue(linePMsg.shipQty_A, line.ordQty.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.funcNetUnitPrcAmt_A, unitNetPrice);
        ZYPEZDItemValueSetter.setValue(linePMsg.slsAmt_A, netAmount);
        ZYPEZDItemValueSetter.setValue(linePMsg.billToAcctNum_A, line.dsAcctNum_BL.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.billToLocNum_A, line.billToCustCd.getValue()); // QC#18663
        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_BL)) {
            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AB, line.dsTaxGrpExemCd_BL.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AB, line.dsTaxGrpExemCd_B.getValue());
        }
        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemReslFlg_BL)) {
            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemReslFlg_AB, line.dsTaxGrpExemReslFlg_BL.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemReslFlg_AB, line.dsTaxGrpExemReslFlg_B.getValue());
        }
        ZYPEZDItemValueSetter.setValue(linePMsg.dsAcctNum_AT, line.dsAcctNum_SH.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.shipToLocNum_A, line.shipToCustCd.getValue()); // QC#18663
        if (ZYPCommonFunc.hasValue(line.dsTaxGrpExemCd_SH)) {
            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AT, line.dsTaxGrpExemCd_SH.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(linePMsg.dsTaxGrpExemCd_AT, line.dsTaxGrpExemCd_S.getValue());
        }
        ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AT, line.taxAreaId_SH.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.dsInsdCtyLimitFlg_AT, line.dsInsdCtyLimitFlg_SH.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.taxAreaId_AT, line.taxAreaId_SP.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AT, line.firstLineAddr_SH.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AT, line.scdLineAddr_SH.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AT, line.ctyAddr_SH.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AT, line.stCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AT, line.cntyNm_SH.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AT, line.postCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AT, line.ctryCd_SH.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AR, line.taxAreaId_SR.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.dsInsdCtyLimitFlg_AR, line.dsInsdCtyLimitFlg_SR.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AR, line.firstLineAddr_SR.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AR, line.scdLineAddr_SR.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AR, line.ctyAddr_SR.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AR, line.stCd_SR.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AR, line.cntyNm_SR.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AR, line.postCd_SR.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AR, line.ctryCd_SR.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.geoCd_AF, line.taxAreaId_SF.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.whCd_AF, line.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.firstLineAddr_AF, line.firstLineAddr_SF.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.scdLineAddr_AF, line.scdLineAddr_SF.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.ctyAddr_AF, line.ctyAddr_SF.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.stCd_AF, line.stCd_SF.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.cntyNm_AF, line.cntyNm_SF.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.postCd_AF, line.postCd_SF.getValue());
        ZYPEZDItemValueSetter.setValue(linePMsg.ctryCd_AF, line.ctryCd_SF.getValue());
        pMsg.taxCalculateInputLine.setValidCount(1);

        return pMsg;
    }

    // Common Method
    private NWZC157002PMsg getLine(NWZC157001PMsg param, String trxLineNum, String trxLineSubNum, String configCatgCd) {
        for (int i = 0; i < param.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg line = param.NWZC157002PMsg.no(i);
            if (S21StringUtil.isEquals(line.trxLineNum.getValue(), trxLineNum)
                    && S21StringUtil.isEquals(line.trxLineSubNum.getValue(), trxLineSubNum)
                    && S21StringUtil.isEquals(line.configCatgCd.getValue(), configCatgCd)) {
                return line;
            }
        }
        return null;
    }

    private boolean isFrozen(NWZC157002PMsg line) {
        if (BigDecimal.ZERO.compareTo(line.invQty.getValue()) != 0 || ZYPConstant.FLG_ON_Y.equals(line.xxPrcCloFlg.getValue())) {
            return true;
        }
        return false;
    }

    private BigDecimal getBasePriceAmount(String glblCmpyCd, NWZC157002PMsg line) {
        BigDecimal price = BigDecimal.ZERO;
        for (int i = 0; i < line.NWZC157003PMsg.getValidCount(); i++) {
            NWZC157003PMsg calcBase = line.NWZC157003PMsg.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManDelFlg.getValue())) {
                continue;
            }
            if (PRC_COND_TP.BASE_PRICE.equals(calcBase.prcCondTpCd.getValue())) {
                price = add(price, calcBase.unitPrcAmt.getValue());
            }
            if (PRC_COND_TP.MANUAL_PRICE.equals(calcBase.prcCondTpCd.getValue())) {
                price = subtract(price, calcBase.unitPrcAmt.getValue());
            }
        }
        return multiply(glblCmpyCd, price, line.ordQty.getValue(), line.ccyCd.getValue());
    }

    //private BigDecimal getNetPrice(String glblCmpyCd, NWZC157002PMsg line) { // QC#50548 2019/05/28 Mod
    private BigDecimal getNetPrice(String glblCmpyCd, NWZC157002PMsg line, BigDecimal specCondPrcPk) {
        BigDecimal price = BigDecimal.ZERO;
        for (int i = 0; i < line.NWZC157003PMsg.getValidCount(); i++) {
            NWZC157003PMsg calcBase = line.NWZC157003PMsg.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManDelFlg.getValue())) {
                continue;
            }
            // QC#50548 2019/05/28 Add Start
            if (isEquals(calcBase.specCondPrcPk.getValue(), specCondPrcPk)) {
                continue;
            }
            // QC#50548 2019/05/28 Add End
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                price = add(price, calcBase.unitPrcAmt.getValue());
            }
            if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())) {
                price = subtract(price, calcBase.unitPrcAmt.getValue());
            }
        }
        return price;
    }

    private BigDecimal getNetAmount(NWZC157002PMsg line, BigDecimal specCondPrcPk) {
        BigDecimal rtnVal = BigDecimal.ZERO;
        NWZC157003PMsg calcBase;
        for (int i = 0; i < line.NWZC157003PMsg.getValidCount(); i++) {
            calcBase = line.NWZC157003PMsg.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManDelFlg.getValue())) {
                continue;
            }
            if (isEquals(calcBase.specCondPrcPk.getValue(), specCondPrcPk)) {
                continue;
            }
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                rtnVal = add(rtnVal, calcBase.calcPrcAmtRate.getValue());
            } else if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManAddFlg.getValue())) {
                    rtnVal = subtract(rtnVal, calcBase.calcPrcAmtRate.getValue());
                }
            } else if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(calcBase.prcDtlGrpCd.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManAddFlg.getValue())) {
                    rtnVal = subtract(rtnVal, calcBase.calcPrcAmtRate.getValue());
                }
            }
        }
        return rtnVal;
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
        if (BigDecimal.ZERO.compareTo(num2) == 0) {
            return BigDecimal.ZERO;
        }
        int scale = getDealCcyDigit(glblCmpyCd, dealCcyCd);
        num1 = round(num1, scale);
        return round(num1.divide(num2, scale, BigDecimal.ROUND_HALF_UP), scale);
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
    private int compareTo(BigDecimal num1, BigDecimal num2) {

        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        return num1.compareTo(num2);
    }

    private NWZC157003PMsg getBasePrice(NWZC157002PMsg line) {
        NWZC157003PMsg base = null;
        for (int i = 0; i < line.NWZC157003PMsg.getValidCount(); i++) {
            NWZC157003PMsg calcBase = line.NWZC157003PMsg.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                base = calcBase;
                break;
            }
        }
        return base;
    }

    private BigDecimal getAmountRate(NWZC157003PMsg calcBase) {
        if (ZYPCommonFunc.hasValue(calcBase.manPrcAmtRate)) {
            return calcBase.manPrcAmtRate.getValue();
        }
        return calcBase.autoPrcAmtRate.getValue();
    }

    // Data Access Method
    private GLBL_CMPYTMsg getGlblCmpy(String glblCmpyCd) {
        GLBL_CMPYTMsg inTMsg = new GLBL_CMPYTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        GLBL_CMPYTMsg outTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

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

    private Map<String, String> getDsOrdTp(String glblCmpyCd, String dsOrdTpCd) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("dsOrdTpCd", dsOrdTpCd);

        return DataCacheForSSM.getInstance().getDsOrdTpInfo(mapParam, ssmBatchClient);
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

    private Map<String, String> getDsAcctCust(String glblCmpyCd, String dsAcctCd, String prcBaseDt) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("dsAcctNum", dsAcctCd);
        mapParam.put("prcBaseDt", prcBaseDt);


        return DataCacheForSSM.getInstance().getDsAcctCustInfo(mapParam, ssmBatchClient);
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

    private PRC_RATE_TPTMsg getPrcRateTp(String glblCmpyCd, String prcRateTpCd) {
        PRC_RATE_TPTMsg inTMsg = new PRC_RATE_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcRateTpCd.setValue(prcRateTpCd);
        PRC_RATE_TPTMsg outTMsg = (PRC_RATE_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
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

    private PRC_CTX_TPTMsg getPrcCtxTp(String glblCmpyCd, String prcCtxTp) {
        PRC_CTX_TPTMsg inTMsg = new PRC_CTX_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcCtxTpCd.setValue(prcCtxTp);
        PRC_CTX_TPTMsg outTMsg = (PRC_CTX_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
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

    private SPCL_HDLG_TPTMsg getSpclHdlgTp(String glblCmpyCd, String spclHdlgTpCd) {
        SPCL_HDLG_TPTMsg inTMsg = new SPCL_HDLG_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.spclHdlgTpCd.setValue(spclHdlgTpCd);
        SPCL_HDLG_TPTMsg outTMsg = (SPCL_HDLG_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private Map<String, String> getBillToCust(String glblCmpyCd, String billToCustCd, String prcBaseDt) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("billToCustCd", billToCustCd);
        mapParam.put("prcBaseDt", prcBaseDt);

        return DataCacheForSSM.getInstance().getBillToCustInfo(mapParam, ssmBatchClient);
    }

    private Map<String, String> getShipToCust(String glblCmpyCd, String shipToCustCd, String prcBaseDt) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("shipToCustCd", shipToCustCd);
        mapParam.put("prcBaseDt", prcBaseDt);

        return DataCacheForSSM.getInstance().getShipToCustInfo(mapParam, ssmBatchClient);
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

    private PRC_MTR_PKGTMsg getPrcMtrPkg(String glblCmpyCd, BigDecimal prcMtrPkgPk) {
        PRC_MTR_PKGTMsg inTMsg = new PRC_MTR_PKGTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcMtrPkgPk.setValue(prcMtrPkgPk);
        PRC_MTR_PKGTMsg outTMsg = (PRC_MTR_PKGTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private PRC_SVC_PLN_TPTMsg getPrcSvcPlnTp(String glblCmpyCd, String prcSvcPlnTpCd) {
        PRC_SVC_PLN_TPTMsg inTMsg = new PRC_SVC_PLN_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcSvcPlnTpCd.setValue(prcSvcPlnTpCd);
        PRC_SVC_PLN_TPTMsg outTMsg = (PRC_SVC_PLN_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private PRC_SVC_CONTR_TPTMsg getPrcSvcContrTp(String glblCmpyCd, String prcSvcContrTpCd) {
        PRC_SVC_CONTR_TPTMsg inTMsg = new PRC_SVC_CONTR_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcSvcContrTpCd.setValue(prcSvcContrTpCd);
        PRC_SVC_CONTR_TPTMsg outTMsg = (PRC_SVC_CONTR_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private MTR_LBTMsg getMtrLb(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg inTMsg = new MTR_LBTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.mtrLbCd.setValue(mtrLbCd);
        MTR_LBTMsg outTMsg = (MTR_LBTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private PRC_LIST_BANDTMsg getPrcListBand(String glblCmpyCd, String prcListBandCd) {
        PRC_LIST_BANDTMsg inTMsg = new PRC_LIST_BANDTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcListBandCd.setValue(prcListBandCd);
        PRC_LIST_BANDTMsg outTMsg = (PRC_LIST_BANDTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private PRC_SVC_ZONETMsg getPrcSvcZone(String glblCmpyCd, String prcSvcZoneCd) {
        PRC_SVC_ZONETMsg inTMsg = new PRC_SVC_ZONETMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcSvcZoneCd.setValue(prcSvcZoneCd);
        PRC_SVC_ZONETMsg outTMsg = (PRC_SVC_ZONETMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private PRC_SVC_TIER_TPTMsg getPrcSvcTierTp(String glblCmpyCd, String prcSvcTierTpCd) {
        PRC_SVC_TIER_TPTMsg inTMsg = new PRC_SVC_TIER_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcSvcTierTpCd.setValue(prcSvcTierTpCd);
        PRC_SVC_TIER_TPTMsg outTMsg = (PRC_SVC_TIER_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private Map<String, Object> getMdse(String glblCmpyCd, String mdseCd, String pkgUomCd) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("mdseCd", mdseCd);
        mapParam.put("pkgUomCd", pkgUomCd);

        return DataCacheForSSM.getInstance().getMdseInfo(mapParam, ssmBatchClient);
    }
    private BigDecimal getChildMdseWt(String glblCmpyCd, String prcBaseDt, String mdseCd) {
        BigDecimal inPoundWt = BigDecimal.ZERO;
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("mdseCd", mdseCd);
        mapParam.put("prcBaseDt", prcBaseDt);
        List<Map<String, Object>> childMdseList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getChildMdse", mapParam);
        for (Map<String, Object> map : childMdseList) {
            if (S21StringUtil.isEquals(MDSE_TP.SET, (String) map.get("MDSE_TP_CD"))) {
                Map<String, String> mapParam2 = new HashMap<String, String>();
                mapParam2.put("glblCmpyCd", glblCmpyCd);
                mapParam2.put("mdseCd", (String) map.get("MDSE_CD"));
                mapParam2.put("prcBaseDt", prcBaseDt);
                List<Map<String, Object>> grandChildMdseList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getChildMdse", mapParam2);
                for (Map<String, Object> map2 : grandChildMdseList) {
                    inPoundWt = add(inPoundWt, (BigDecimal) map2.get("IN_POUND_WT"));
                }
            } else {
                inPoundWt = add(inPoundWt, (BigDecimal) map.get("IN_POUND_WT"));
            }
        }
        return inPoundWt;
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

    private RTRN_RSNTMsg getRtrnRsnCd(String glblCmpyCd, String rtrnRsnCd) {
        RTRN_RSNTMsg inTMsg = new RTRN_RSNTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.rtrnRsnCd.setValue(rtrnRsnCd);
        RTRN_RSNTMsg outTMsg = (RTRN_RSNTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private MDL_NMTMsg getMtrLbCd(String glblCmpyCd, BigDecimal mdlId) {
        MDL_NMTMsg inTMsg = new MDL_NMTMsg();
        inTMsg.t_GlblCmpyCd.setValue(glblCmpyCd);
        inTMsg.t_MdlId.setValue(mdlId);
        MDL_NMTMsg outTMsg = (MDL_NMTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private SVC_CALL_TPTMsg getSvcCallTpCd(String glblCmpyCd, String svcCallTpCd) {
        SVC_CALL_TPTMsg inTMsg = new SVC_CALL_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.svcCallTpCd.setValue(svcCallTpCd);
        SVC_CALL_TPTMsg outTMsg = (SVC_CALL_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
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

    private FRT_CONDTMsg getFrtCond(String glblCmpyCd, String frtCondCd) {
        FRT_CONDTMsg inTMsg = new FRT_CONDTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.frtCondCd.setValue(frtCondCd);
        FRT_CONDTMsg outTMsg = (FRT_CONDTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
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

    private String getTaxAreaId(String glblCmpyCd, String cntyNm, String ctyAddr, String stCd) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("cntyNm", cntyNm);
        mapParam.put("ctyAddr", ctyAddr);
        mapParam.put("stCd", stCd);

        String taxAreaId = DataCacheForSSM.getInstance().getTaxAreaId(mapParam, ssmBatchClient);
        if (taxAreaId == null) {
            mapParam.put("ctyAddr", null);
            taxAreaId = DataCacheForSSM.getInstance().getTaxAreaId(mapParam, ssmBatchClient);
        }
        if (taxAreaId == null) {
            mapParam.put("cntyNm", null);
            taxAreaId = DataCacheForSSM.getInstance().getTaxAreaId(mapParam, ssmBatchClient);
        }
        return taxAreaId;
    }

    private Map<String, String> getRtlWh(String glblCmpyCd, String rtlWhCd) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("rtlWhCd", rtlWhCd);

        return DataCacheForSSM.getInstance().getRtlWhInfo(mapParam, ssmBatchClient);
    }

    // QC#22371 2017/12/26 Add Start
    private RTL_SWHTMsg getRtlSwh(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {
        RTL_SWHTMsg inTMsg = new RTL_SWHTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.rtlWhCd.setValue(rtlWhCd);
        inTMsg.rtlSwhCd.setValue(rtlSwhCd);
        RTL_SWHTMsg outTMsg = (RTL_SWHTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }
    // QC#22371 2017/12/26 Add End

    private Map<String, String> getToc(String glblCmpyCd, String prcBaseDt, String slsRepOrSlsTeamTocCd) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("prcBaseDt", prcBaseDt);
        mapParam.put("slsRepOrSlsTeamTocCd", slsRepOrSlsTeamTocCd);

        return DataCacheForSSM.getInstance().getTocInfo(mapParam, ssmBatchClient);
    }

    private List<Map<String, String>> getPrcListConfig(NWZC157001PMsg param, NWZC157002PMsg line) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("prcCatgCd", line.prcCatgCd.getValue());
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("prcBaseDt", line.prcBaseDt.getValue());
        mapParam.put("prcListEquipConfigNum", line.prcListEquipConfigNum.getValue());
        mapParam.put("prcqlfyTpItem", PRC_QLFY_TP.ITEM_CODE);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);

        return DataCacheForSSM.getInstance().getPrcListConfig(mapParam, ssmBatchClient);
    }

    private String getPrcListStruTp(String glblCmpyCd, String prcBaseDt, String prcCatgCd) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("prcBaseDt", prcBaseDt);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("prcCatgCd", prcCatgCd);

        return DataCacheForSSM.getInstance().getPrcListStruTp(mapParam, ssmBatchClient);
    }

    private List<String> getSubPriceList(NWZC157001PMsg param, NWZC157002PMsg line, String prcCatgCd) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        mapParam.put("prcBaseDt", line.prcBaseDt.getValue());
        mapParam.put("prcCatgCd", prcCatgCd);

        return DataCacheForSSM.getInstance().getSubPrcList(mapParam, ssmBatchClient);
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
        // For Performance QC#10321 Mod End

        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0);
    }

    private String getPrcCondTpDescTxt(String glblCmpyCd, String prcCondTpCd) {
        PRC_COND_TPTMsg inTMsg = new PRC_COND_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcCondTpCd.setValue(prcCondTpCd);
        PRC_COND_TPTMsg outTMsg = (PRC_COND_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg.prcCondTpDescTxt.getValue();
    }

    private BigDecimal getSpecCondPrcPk(String glblCmpyCd, String prcCondTpCd) {
        final FindCondition condition = new FindCondition("004");
        condition.addCondition("glblCmpyCd01", glblCmpyCd);
        condition.addCondition("prcCondTpCd01", prcCondTpCd);
        SPEC_COND_PRCTMsgArray array = DataCacheForTBLAccessor.getInstance().getSpecCondPrcTMsgArray(condition);
        // For Performance QC#10321 Mod End

        if (array == null || array.length() == 0) {
            return BigDecimal.ZERO;
        }
        return array.no(0).specCondPrcPk.getValue();
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

    private CPO_SRC_TPTMsg getDsCpoSrcTp(String glblCmpyCd, String cpoSrcTpCd) {

        CPO_SRC_TPTMsg inTMsg = new CPO_SRC_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.cpoSrcTpCd.setValue(cpoSrcTpCd);
        CPO_SRC_TPTMsg outTMsg = (CPO_SRC_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    // QC#18755 2017/05/30 Add Start
    private ALL_MDSE_VTMsg getAllMdseV(String glblCmpyCd, String mdseCd) {
        ALL_MDSE_VTMsg tmsg = new ALL_MDSE_VTMsg();
        tmsg.setSQLID("003");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("mdseCd01", mdseCd);

        ALL_MDSE_VTMsgArray tmsgArray = (ALL_MDSE_VTMsgArray) S21ApiTBLAccessor.findByCondition(tmsg);
        if (tmsgArray.length() == 0) {
            return null;
        }
        return (ALL_MDSE_VTMsg) tmsgArray.get(0);
    }
    // QC#18755 2017/05/30 Add End
    // QC#21841 2018/05/21 Add Start
    private Map<String, String> getChgMdse(String glblCmpyCd) {
        Map<String, String> rtnValue = new HashMap<String, String>();
        PRC_DTL_GRPTMsg tmsg = new PRC_DTL_GRPTMsg();
        tmsg.setSQLID("001");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        PRC_DTL_GRPTMsgArray tmsgArray = (PRC_DTL_GRPTMsgArray) S21ApiTBLAccessor.findByCondition(tmsg);
        for (int i = 0; i < tmsgArray.length(); i++) {
            tmsg = tmsgArray.no(i);
            if (ZYPCommonFunc.hasValue(tmsg.chrgMdseCd.getValue())) {
                rtnValue.put(tmsg.prcDtlGrpCd.getValue(), tmsg.chrgMdseCd.getValue());
            }
        }
        return rtnValue;
    }
    // QC#21841 2018/05/21 Add End
    private boolean isRoundFactor(String prcDtlGrpCd){
        if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(prcDtlGrpCd) 
                || PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(prcDtlGrpCd)
                || PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(prcDtlGrpCd)) {
            return true;
        }
        return false;
    }

    private void log(String s) {
        EZDDebugOutput.println(1, "[NWZC1570] " + s, this);
    }
}
