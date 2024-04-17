package com.canon.cusa.s21.api.NWZ.NWZC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.common.S21StringUtil.isEquals;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPDateItem;
import parts.common.EZDPStringItem;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.CNTYTMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.HARD_ALLOCTMsg;
import business.db.HARD_ALLOCTMsgArray;
import business.db.HLDTMsg;
import business.db.HLDTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.PRC_DTLTMsg;
import business.db.PRC_DTLTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.SHPG_PLN_UPD_MODETMsg;
import business.db.SOFT_ALLOCTMsg;
import business.db.SOFT_ALLOCTMsgArray;
import business.db.TRX_SRC_TPTMsg;
import business.db.CUST_NTFY_SHIP_INFOTMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC004001PMsg;
import business.parts.NWZC044001PMsg;
import business.parts.NWZC102001PMsg;
import business.parts.NWZC188001PMsg;
import business.parts.NWZC188001_shpgPlnNumListPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC004001.NWZC004001;
import com.canon.cusa.s21.api.NWZ.NWZC044001.NWZC044001;
import com.canon.cusa.s21.api.NWZ.NWZC102001.NWZC102001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.common.NWX.NWXC002007.NWXC002007BackOrder;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXChildMdseQtyByCpoDtlThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_ARTH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.REV_RECOG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_PLN_UPD_MODE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;

/**
 * <pre>
 * Shipping Plan Update API
 *
 * The data of Shipping Plan is chiefly updated to the following items.
 *  - Shipping Plan Status Code
 *  - Routing Status Code
 *  - SO Close Flag
 *  - Passed parameter
 *  - Item that amount and amount of money calculate again when Shipping Plan is divided
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/23/2009   Fujitsu         S.Yamamoto      Create          N/A
 * 08/19/2009   Fujitsu         S.Yamamoto      Update          N/A
 * 09/10/2009   Fujitsu         S.Yamamoto      Update          N/A
 * 09/11/2009   Fujitsu         S.Yamamoto      Update          N/A
 * 10/02/2009   Fujitsu         S.Yamamoto      Update          397
 * 10/07/2009   Fujitsu         S.Yamamoto      Update          481
 * 10/21/2009   Fujitsu         A.Kobayashi     Update          788
 * 11/16/2009   Fujitsu         K.Sato          Update          1850
 * 11/23/2009   Fujitsu         K.Sato          Update
 * 12/01/2009   Fujitsu         S.Yamamoto      Update          2211
 * 12/02/2009   Fujitsu         S.Yamamoto      Update
 * 12/09/2009   Fujitsu         K.Kato          Update          2473
 * 12/16/2009   Fujitsu         K.Sato          Update          Update (4)Check Status transition                                                                                                                     
 * 12/29/2009   Fujitsu         S.Yamamoto      Update          2916
 * 01/19/2009   Fujitsu         K.Sato          Update
 * 03/17/2010   Fujitsu         S.Sugino        Update          3488,3771
 * 03/26/2010   Fujitsu         S.Yamamoto      Update          3771,5135
 * 04/09/2010   Fujitsu         R.Watanabe      Update          5493
 * 05/12/2010   Fujitsu         A.Suda          Update          5359
 * 05/18/2010   Fujitsu         A.Suda          Update          6445
 * 06/07/2010   Fujitsu         A.Suda          Update          6984
 * 06/24/2010   Fujitsu         S.Yamamoto      Update          7344
 * 06/30/2010   Fujitsu         S.Yamamoto      Update          7150
 * 07/13/2010   Fujitsu         S.Yamamoto      Update          7791
 * 08/02/2010   Fujitsu         S.Yamamoto      Update          158(All2)
 * 08/26/2010   Fujitsu         R.Watanabe      Update          360(All2)
 * 08/31/2010   Fujitsu         S.Yamamoto      Update          360(All2)
 * 09/07/2010   Fujitsu         S.Yamamoto      Update          360(All2)
 * 02/10/2011   Fujitsu         A.Suda          Update          1471
 * 03/10/2011   Fujitsu         S.Yamamoto      Update          1857(PROD)
 * 04/22/2013   Fujitsu         S.Yamamoto      Update          [094]
 * 07/29/2013   Fujitsu         S.Yamamoto      Update          OM053
 * 09/10/2015   Fujitsu         S.Takami        Update          CNA(2.8 Allocation - SO/PO)
 * 09/10/2015   Fujitsu         S.Takami        Update          CNA(2.9 Revenue Recognition)
 * 02/29/2016   Fujitsu         M.Hara          Update          QC#546
 * 03/18/2016   Fujitsu         Y.Taoka         Update          QC#546
 * 05/31/2016   Fujitsu         S.Takami        Update          S21_NA#4321
 * 06/16/2016   Fujitsu         S.Yamamoto      Update          S21_NA#6844
 * 2016/06/20   Fujitsu         S.Takami        Update          S21_NA#594
 * 2016/06/29   Fujitsu         S.Takami        Update          S21_NA#7189
 * 07/11/2016   CSAI            Y.Imazu         Update          QC#10917
 * 2016/07/26   Fujitsu         S.Takami        Update          S21_NA#594-2
 * 2016/09/20   Fujitsu         M.Ohno          Update          S21_NA#14557
 * 09/28/2016   Fujitsu         T.Ishii         Update          S21_NA#14557-2
 * 2016/12/06   Fujitsu         S.Ohki          Update          S21_NA#16293
 * 12/16/2016   CSAI            Y.Imazu         Update          QC#16614
 * 03/08/2017   Fujitsu         H.Nagashima     Update          QC#16639
 * 03/10/2017   CITS            T.Wada          Update          QC#21342
 * 10/27/2017   CITS            T.Kikuhara      Update          QC#21821
 * 2017/11/24   Fujitsu         S.Yamamoto      Update          S21_NA#22643
 * 2017/11/27   Fujitsu         W.Honda         Update          S21_NA#22785
 * 2017/12/07   Fujitsu         T.Aoi           Update          S21_NA#22930
 * 2018/01/25   CITS            T.Tokutomi      Update          QC#23776
 * 2018/04/26   Fujitsu         K.Ishizuka      Update          S21_NA#25784
 * 2018/08/09   Fujitsu         W.Honda         Update          S21_NA#26865
 * 2018/08/25   CITS            K.Ogino         Update          S21_NA#27939
 * 2018/11/19   Fujitsu         K.Kato          Update          S21_NA#29173
 * 2019/03/08   Fujitsu         K.Ishizuka      Update          S21_NA#30550
 * 2019/10/03   Fujitsu         C.Hara          Update          S21_NA#53186
 * 2019/11/12   Fujitsu         C.Hara          Update          QC#54513
 * 2019/12/12   Fujitsu         C.Hara          Update          QC#54474
 * 2023/06/02   Hitachi         H.Watanabe      Update          QC#61383
 *</pre>
 */
public class NWZC003001 extends S21ApiCommonBase {

    /** Data Global Company Code must be entered. */
    public static final String NWZM0011E = "NWZM0011E";

    /** Mode must be entered. */
    public static final String NWZM0012E = "NWZM0012E";

    /** The specified value in Mode is incorrect. */
    public static final String NWZM0013E = "NWZM0013E";

    /** Transaction Source Type Code must be entered. */
    public static final String NWZM0014E = "NWZM0014E";

    /** Shipping Plan Number must be entered. */
    public static final String NWZM0022E = "NWZM0022E";

    /** Transaction Header Number must be entered. */
    public static final String NWZM0027E = "NWZM0027E";

    /** Transaction Line Sub Number must be entered. */
    public static final String NWZM0043E = "NWZM0043E";

    /** Order Quantity must be entered. */
    public static final String NWZM0046E = "NWZM0046E";

    /** Inventory Location Code must be entered. */
    public static final String NWZM0047E = "NWZM0047E";

    /** PSD must be entered. */
    public static final String NWZM0051E = "NWZM0051E";

    /** PDD must be entered. */
    public static final String NWZM0053E = "NWZM0053E";

    /** SO Number must be entered. */
    public static final String NWZM0055E = "NWZM0055E";

    /** Purchase Order Number must be entered. */
    public static final String NWZM0056E = "NWZM0056E";

    /** Bill Of Lading Number must be entered. */
    public static final String NWZM0063E = "NWZM0063E";

    /** Freight Amount must be entered. */
    public static final String NWZM0070E = "NWZM0070E";

    /** Actual Shipment Date must be entered. */
    public static final String NWZM0071E = "NWZM0071E";

    /** Invoice Number must be entered. */
    public static final String NWZM0072E = "NWZM0072E";

    /** The date is not found in the CPO_DTL. */
    public static final String NWZM0074E = "NWZM0074E";

    /** The data is not found in the SHPG_PLN. */
    public static final String NWZM0075E = "NWZM0075E";

    /** Multiple data found in the SHPG_PLN. */
    public static final String NWZM0076E = "NWZM0076E";

    /** SHPG_PLN update failed. */
    public static final String NWZM0078E = "NWZM0078E";

    /** PRC_DTL update failed. */
    public static final String NWZM0079E = "NWZM0079E";

    /** CPO_DTL update failed. */
    public static final String NWZM0081E = "NWZM0081E";

    /** Status transition check error occurred. Update is not allowed. */
    public static final String NWZM0083E = "NWZM0083E";

    /** Transaction Line Number must be entered. */
    public static final String NWZM0089E = "NWZM0089E";

    /** The data is not found in PRC_DTL. */
    public static final String NWZM0202E = "NWZM0202E";

    /** Either 'Cancel if not delivered by' or 'Cancel if not Shipped by' can be entered, but not both. */
    public static final String NWZM0032E = "NWZM0032E";

    /** A past date was entered in 'Cancel if not delivered'. */
    public static final String NWZM0033E = "NWZM0033E";

    /** A past date was entered in 'Cancel if not Shipped by'. */
    public static final String NWZM0034E = "NWZM0034E";

    /** Two or more parameters exist. */
    public static final String NWZM0338E = "NWZM0338E";

    /** There is contradiction in the shipment amount and the shipment instruction amount. */
    public static final String NWZM0339E = "NWZM0339E";

    /** There is contradiction in the order amount and the receipt amount. */
    public static final String NWZM0340E = "NWZM0340E";

    /** The input is indispensable to OnBatch Type. */
    public static final String NWZM0344E = "NWZM0344E";

    /** Sales Date is a compulsory input. */
    public static final String NWZM0346E = "NWZM0346E";

    /** SOFT_ALLOC update failed. */
    public static final String NWZM0662E = "NWZM0662E";

    /** HARD_ALLOC update failed. */
    public static final String NWZM0920E = "NWZM0920E";

// START ADD 2013/04/22 S.Yamamoto [094]
    /** "Date" is not entered. */
    public static final String NWZM0238E = "NWZM0238E";
// END   ADD 2013/04/22 S.Yamamoto [094]

    // QC#546 Add Start
    /** The data specified does not exist in CPO. */
    public static final String NWZM1207E = "NWZM1207E";

    /** No target record for update found in CPO_DTL. */
    public static final String NWAM0547E = "NWAM0547E";

    // QC#546 Add End

    /** There is a discrepancy between Ordered Qty and Shipping Order Qty. */
    public static final String NWZM0946E = "NWZM0946E";

    /** The corresponding data does not exist in "SET_CMPT". */
    public static final String NWZM0222E = "NWZM0222E";

    /** Shipping Mode Code : Hard Allocated(S/O Cancelled) */
    public static final String MODE_HARDALLOCATED_SOCANCELLED = "03";

    /** Shipping Mode Code : Hard Allocated(Routed) */
    public static final String MODE_HARDALLOCATED_ROUTED = "04";

    /** Shipping Mode Code : Printed(S/O Creating) */
    public static final String MODE_PRINTED_SOCREATING = "05";

    /** Shipping Mode Code : Printed(S/O Created) */
    public static final String MODE_PRINTED_SOCREATED = "06";

    /** Shipping Mode Code : PO Printed */
    public static final String MODE_POPRINTED = "07";

    /** Shipping Mode Code : Picked */
    public static final String MODE_PICKED = "08";

    /** Shipping Mode Code : Packed */
    public static final String MODE_PACKED = "09";

    /** Shipping Mode Code : Staged */
    public static final String MODE_STAGED = "10";

    /** Shipping Mode Code : In-Shed */
    public static final String MODE_INSHED = "11";

    /** Shipping Mode Code : Shipped */
    public static final String MODE_SHIPPED = "12";

    /** Shipping Mode Code : Shipped(S/O Close)A-Invoice */
    public static final String MODE_SHIPPED_SOCLOSE_AINVOICE = "13";

    /** Shipping Mode Code : Shipped(S/O Close) */
    public static final String MODE_SHIPPED_SOCLOSE = "14";

    /** Shipping Mode Code : Arrived(S/O Close) */
    public static final String MODE_ARRIVED_SOCLOSE = "15";

    /** Shipping Mode Code : PO Cancel */
    public static final String MODE_POCANCELLED = "16";

    /** Shipping Mode Code : Shipping Request */
    public static final String MODE_SHIPPINGREQUEST = "17";

    /** Shipping Mode Code : Vendor Invoice Received */
    public static final String MODE_VENDERINVOICERECEIVED = "18";

    /** Shipping Mode Code : Forced Shipment */
    public static final String MODE_FORCEDSHIPMENT = "19";

// START ADD/MOD 2013/04/22 S.Yamamoto [094]
    // /** Shipping Mode Code : PSD/PDD Update */
    // public static final String MODE_PSD_PDD_UPDATE = "20";
    /** Shipping Mode Code : PSD/PDD/RSD/RDD Update */
    public static final String MODE_PSD_PDD_RSD_RDD_UPDATE = "20";

    /** Shipping Mode Code : Installed */
    public static final String MODE_INSTALLED = "21";
// END   ADD/MOD 2013/04/22 S.Yamamoto [094]

    // It would be deleted, but if you delete this mode, compile error will be occurred.
    /** Shipping Mode Code : Printed */
    public static final String MODE_PRINTED = "22";

    // It would be deleted, but if you delete this mode, compile error will be occurred.
    /** Shipping Mode Code : Order Driven */
    public static final String MODE_ORDER_DRIVEN = "23";

    // It would be deleted, but if you delete this mode, compile error will be occurred.
    /** Shipping Mode Code : Order Driven PO Cancel */
    public static final String MODE_ORDER_DRIVEN_POCANCELLED = "24";

    /** Shipping Mode Code : Partial Ship */
    public static final String MODE_PARTIAL_SHIP = "25";

    /* 07/11/2016 CSAI Y.Imazu Add QC#10917 START */
    /** Shipping Mode Code : Arrived(Per Shipping Plan Number) */
    public static final String MODE_ARRIVED_SHPG_PLN = "26";
    /* 07/11/2016 CSAI Y.Imazu Add QC#10917 END */

    /** S21ApiTBLAccessor Return Code : Normal Finish */
    private static final String RTNCD_NOMAL = "0000";

    /** S21BusinessProcessLog SubSysID : NWZ */
    private static final String SUBSYSID = "NWZ";

    /** S21BusinessProcessLog ProcID : OM */
    private static final String PROCID = "OM";

    /** S21BusinessProcessLog DocTpID : OM */
    private static final String DOCTPCD = "OM";

    /** S21BusinessProcessLog EventID : Shipping Order Cancel */
    private static final String EVENTID_HARDALLOCATED_SOCANCELLED = "Shipping Order Cancel";

    /** S21BusinessProcessLog EventID : Hard Allocation */
    private static final String EVENTID_HARDALLOCATED_ROUTED = "Hard Allocation";

    /** S21BusinessProcessLog EventID : Shipping Order */
    private static final String EVENTID_PRINTED_SOCREATED = "Shipping Order";

    /** S21BusinessProcessLog EventID : Purchase Order */
    private static final String EVENTID_POPRINTED = "Purchase Order";

    /** S21BusinessProcessLog EventID : Pick */
    private static final String EVENTID_PICKED = "Pick";

    /** S21BusinessProcessLog EventID : Pack */
    private static final String EVENTID_PACKED = "Pack";

    /** S21BusinessProcessLog EventID : Stage */
    private static final String EVENTID_STAGED = "Stage";

    /** S21BusinessProcessLog EventID : InShed */
    private static final String EVENTID_INSHED = "InShed";

    /** S21BusinessProcessLog EventID : Ship */
    private static final String EVENTID_SHIPPED = "Ship";

    /** S21BusinessProcessLog EventID : A-Invoice */
    private static final String EVENTID_SHIPPED_SOCLOSE_AINVOICE = "A-Invoice";

    /** S21BusinessProcessLog EventID : Ship(SO Close) */
    private static final String EVENTID_SHIPPED_SOCLOSE = "Ship(SO Close)";

    /** S21BusinessProcessLog EventID : Arrive */
    private static final String EVENTID_ARRIVED = "Arrive";

    /** S21BusinessProcessLog EventID : Purchase Order Cancel */
    private static final String EVENTID_POCANCELLED = "Purchase Order Cancel";

    /** S21BusinessProcessLog EventID : Purchase Order Receipt */
    private static final String EVENTID_VENDERINVOICERECEIVED = "Purchase Order Receipt";

// START ADD 2013/04/22 S.Yamamoto [094]
    /** S21BusinessProcessLog EventID : Installed */
    private static final String EVENTID_INSTALLED = "Install";
// END   ADD 2013/04/22 S.Yamamoto [094]

    // 2016/06/20 S21_NA#594 Add Start
    /** S21BusinessProcessLog EventID : Partial Ship */
    private static final String EVENTID_PARTIAL_SHIPPED = "Partial Ship";
    // 2016/06/20 S21_NA#594 Add End

    // 2015/09/10 CNA(2.8 Allocation - SO/PO) Add Start
    /** Varchar Const Key: ALLOC_AF_CR_CHKED_FLG */
    private static final String ALLOC_AF_CR_CHKED_FLG = "ALLOC_AF_CR_CHKED_FLG";
// 2015/09/10 CNA(2.8 Allocation - SO/PO) Add End

    // 2016/06/16 S.Yamamoto S21_NA#6844 Start
    /** Varchar Const Key: AUTO_ALLOC_CANC_TRX_SRC_TP */
    private static final String AUTO_ALLOC_CANC_TRX_SRC_TP = "AUTO_ALLOC_CANC_TRX_SRC_TP";
    // 2016/06/16 S.Yamamoto S21_NA#6844 End

    /** SetItem LINE_SUB_NUM 000 */
    private static final String SET_LINE_SUB_NUM = "000";

    /** */
    private static final String SET_AVAL_DIVIDE_QTY = "setAvalDivideQty";

    // 2023/06/02 QC#61383 Add Start
    /** DS_COND_CONST_GRP_ID_01 */
    private static final String DS_COND_CONST_GRP_ID_01 = "CUST_NTFY_SHIP";

    /** ORD_CATG_CTX_TP_CD_01 */
    private static final String ORD_CATG_CTX_TP_CD_01 = "CUST_NTFY_SHIP";

    /** DS_COND_CONST_ASTER */
    private static final String DS_COND_CONST_ASTER = "*";
    // 2023/06/02 QC#61383 Add End

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Ship to customer data in the cache
     */
    private S21LRUMap<String, SHIP_TO_CUSTTMsg> shipToCache = new S21LRUMap<String, SHIP_TO_CUSTTMsg>();

    /**
     * Standard currency digit in the cache
     */
    private S21LRUMap<String, Integer> stdCcyDigitCache = new S21LRUMap<String, Integer>();

    /** Init */
    public NWZC003001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    private void execute(final List<NWZC003001PMsg> params, final NWZC003001PMsg param, final ONBATCH_TYPE onBatchType) {

        debug("execute - start -");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        // Input Check Header Parameter
        SHPG_PLN_UPD_MODETMsg spUpdModeMsg = new SHPG_PLN_UPD_MODETMsg();
        chkInputHeaderParam(spUpdModeMsg, onBatchType, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
            return;
        }

        // Input Check Data Parameter
        chkInputDataParam(params, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
            return;
        }

        // Processing according to mode
        distributeMode(spUpdModeMsg, msgMap, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
            return;
        }

        debug("execute - end -");
    }

    /**
     * <pre>
     * When the division of Shipping Plan is generated, the update and new of Shipping Plan are made. 
     *  - It divides into TransactionSourceType of Shipping Plan that becomes a division origin only for "1" CPOExistenceFlag. 
     * When Shipping Plan is divided, the update and new of Pricing Detail are made. 
     *  - The recalculation of Ship Unit Quantity and each Amount is done and updated based on Order Quantity of divided Shipping Plan. 
     *  - Pricing Detail is newly made based on the data of added Shipping Plan.
     * It updates it to CPO Detail when there is Shipping Plan that does SO Close. 
     *  - Order Quantity of Shipping Plan that does SO Close is added to Ship Quantity of CPO Detail and CPO Ditail is renewed. 
     * The business process log is output by Mode.  
     * It newly makes it for HOLD when changing to the status of S/O Cancel or P/O Cancel. 
     * </pre>
     * @param params List<NWZC003001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC003001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NWZC003001PMsg param : params) {
            execute(params, param, onBatchType);
        }
    }

    private void debug(String str) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, "[NWZC003001] " + str, this);
        }
    }

    private void chkInputHeaderParam(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, ONBATCH_TYPE onBatchType, S21ApiMessageMap msgMap) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0011E);
            return;
        }
        if (null == onBatchType) {
            msgMap.addXxMsgId(NWZM0344E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(param.shpgModeCd)) {
            msgMap.addXxMsgId(NWZM0012E);
            return;
        }

// START ADD/MOD 2013/04/22 S.Yamamoto [094]
        if (!MODE_PSD_PDD_RSD_RDD_UPDATE.equals(param.shpgModeCd.getValue())) {
// END   ADD/MOD 2013/04/22 S.Yamamoto [094]
            SHPG_PLN_UPD_MODETMsg tMsg = new SHPG_PLN_UPD_MODETMsg();
            tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            tMsg.shpgPlnUpdModeCd.setValue(param.shpgModeCd.getValue());
            tMsg = (SHPG_PLN_UPD_MODETMsg) S21CacheTBLAccessor.findByKey(tMsg);
            if (null == tMsg) {
                msgMap.addXxMsgId(NWZM0013E);
                return;
            }
            EZDMsg.copy(tMsg, null, spUpdModeMsg, null);
        }
    }

    private void chkInputDataParam(List<NWZC003001PMsg> params, S21ApiMessageMap msgMap) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        chkShippingPlanNumber(param, param.shpgPlnNum, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkSalesDate(param, param.slsDt, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkPSD(param, param.psdDt, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkShippingOrderNumber(param, param.soNum, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkOrderQuantity(param, param.ordQty, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkPurchaseOrderNumber(param, param.poOrdNum, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkWHCode(param, param.invtyLocCd, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkActualArrivalDate(param, param.actlArvDt, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkFreightAmount(param, param.spTotFuncFrtAmt, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkActualShippmentDate(param, param.actlShipDt, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkBillOfLadingNumber(param, param.bolNum, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkBillOfLadingDate(param, param.bolDt, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkTransactionSourceTypeCode(param, param.trxSrcTpCd, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkTransactionHeaderNumber(param, param.trxHdrNum, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkTransactionLineNumber(param, param.trxLineNum, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkTransactionLineSubNumber(param, param.trxLineSubNum, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkAvailableShippingOrderQuantity(param, param.avalSoQty, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkInvoiceNumber(param, param.invNum, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        chkDataParameterList(params, param, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        chkCPOExistenceFlag(param, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
// START ADD 2013/04/22 S.Yamamoto [094]
        chkDate(param, msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
// END   ADD 2013/04/22 S.Yamamoto [094]
    }

    private void chkShippingPlanNumber(NWZC003001PMsg param, EZDPStringItem item, S21ApiMessageMap msgMap) {

        if (MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue())
                || MODE_HARDALLOCATED_ROUTED.equals(param.shpgModeCd.getValue())
                || MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())
                || MODE_PRINTED_SOCREATED.equals(param.shpgModeCd.getValue())
                || MODE_POPRINTED.equals(param.shpgModeCd.getValue())
                || MODE_PICKED.equals(param.shpgModeCd.getValue())
                || MODE_PACKED.equals(param.shpgModeCd.getValue())
                || MODE_STAGED.equals(param.shpgModeCd.getValue())
                || MODE_INSHED.equals(param.shpgModeCd.getValue())
                || MODE_SHIPPED.equals(param.shpgModeCd.getValue())
                || MODE_SHIPPED_SOCLOSE_AINVOICE.equals(param.shpgModeCd.getValue())
                || MODE_POCANCELLED.equals(param.shpgModeCd.getValue())
                || MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue())
                || MODE_FORCEDSHIPMENT.equals(param.shpgModeCd.getValue())
// START ADD/MOD 2013/04/22 S.Yamamoto [094]
                || MODE_PSD_PDD_RSD_RDD_UPDATE.equals(param.shpgModeCd.getValue())
// END   ADD/MOD 2013/04/22 S.Yamamoto [094]
// 2016/06/20 S21_NA#594 Add Start
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())
// 2016/06/20 S21_NA#594 Add End
                /* 07/11/2016 CSAI Y.Imazu Add QC#10917 START */
                || MODE_ARRIVED_SHPG_PLN.equals(param.shpgModeCd.getValue())) {
                /* 07/11/2016 CSAI Y.Imazu Add QC#10917 END */
            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0022E);
            }
        }
    }

    private void chkSalesDate(NWZC003001PMsg param, EZDPDateItem item, S21ApiMessageMap msgMap) {

        if (MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue()) //
                || MODE_POCANCELLED.equals(param.shpgModeCd.getValue()) //
// 2016/06/20 S21_NA#594 Add Start
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
// 2016/06/20 S21_NA#594 Add End

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0346E);
            }
        }
    }

    private void chkPSD(NWZC003001PMsg param, EZDPDateItem item, S21ApiMessageMap msgMap) {

// START MOD 2013/04/22 S.Yamamoto [094]
        // if (MODE_HARDALLOCATED_ROUTED.equals(param.shpgModeCd.getValue())
        // || MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())
        // || MODE_PSD_PDD_RSD_RDD_UPDATE.equals(param.shpgModeCd.getValue())) {
        if (MODE_HARDALLOCATED_ROUTED.equals(param.shpgModeCd.getValue())
                || MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
// END   MOD 2013/04/22 S.Yamamoto [094]

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0051E);
            }
        }
    }

    private void chkShippingOrderNumber(NWZC003001PMsg param, EZDPStringItem item, S21ApiMessageMap msgMap) {

// START MOD 2013/04/22 S.Yamamoto [094]
        // if (MODE_PRINTED_SOCREATED.equals(param.shpgModeCd.getValue())
        // ||MODE_SHIPPED_SOCLOSE.equals(param.shpgModeCd.getValue())) {
        if (MODE_PRINTED_SOCREATED.equals(param.shpgModeCd.getValue())
                || MODE_SHIPPED_SOCLOSE.equals(param.shpgModeCd.getValue())
                || MODE_INSTALLED.equals(param.shpgModeCd.getValue())) {
// END   MOD 2013/04/22 S.Yamamoto [094]

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0055E);
            }
        }
    }

    private void chkOrderQuantity(NWZC003001PMsg param, EZDPBigDecimalItem item, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPED.equals(param.shpgModeCd.getValue()) //
                || MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue()) //
// 2016/06/20 S21_NA#594 Add Start //
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { //
// 2016/06/20 S21_NA#594 Add End //

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0046E);
            }
        }
    }

    private void chkPurchaseOrderNumber(NWZC003001PMsg param, EZDPStringItem item, S21ApiMessageMap msgMap) {

        if (MODE_POPRINTED.equals(param.shpgModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0056E);
            }
        }
    }

    private void chkWHCode(NWZC003001PMsg param, EZDPStringItem item, S21ApiMessageMap msgMap) {

        if (MODE_ARRIVED_SOCLOSE.equals(param.shpgModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0047E);
            }
        }
    }

    private void chkActualArrivalDate(NWZC003001PMsg param, EZDPDateItem item, S21ApiMessageMap msgMap) {

        if (MODE_ARRIVED_SOCLOSE.equals(param.shpgModeCd.getValue())
                /* 07/11/2016 CSAI Y.Imazu Add QC#10917 START */
                || MODE_ARRIVED_SHPG_PLN.equals(param.shpgModeCd.getValue())) {
                /* 07/11/2016 CSAI Y.Imazu Add QC#10917 END */

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0032E);
            }
        }
    }

    private void chkBillOfLadingNumber(NWZC003001PMsg param, EZDPStringItem item, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPED_SOCLOSE_AINVOICE.equals(param.shpgModeCd.getValue()) //
                || MODE_ARRIVED_SOCLOSE.equals(param.shpgModeCd.getValue())) { //
// 2016/12/06 S21_NA#16293 Del Start
// 2016/06/20 S21_NA#594 Add Start
//                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
// 2016/06/20 S21_NA#594 Add End
// 2016/12/06 S21_NA#16293 Del End

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0063E);
            }
        }
    }

    private void chkFreightAmount(NWZC003001PMsg param, EZDPBigDecimalItem item, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPED.equals(param.shpgModeCd.getValue()) //
// 2016/06/20 S21_NA#594 Add Start
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { //
// 2016/06/20 S21_NA#594 Add End
            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0070E);
            }
        }
    }

    private void chkActualShippmentDate(NWZC003001PMsg param, EZDPDateItem item, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())
// 2016/06/20 S21_NA#594 Add Start
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
// 2016/06/20 S21_NA#594 Add End

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0071E);
            }
        }
    }

    private void chkBillOfLadingDate(NWZC003001PMsg param, EZDPDateItem item, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPED_SOCLOSE_AINVOICE.equals(param.shpgModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0033E);
            }
        }
    }

    private void chkTransactionSourceTypeCode(NWZC003001PMsg param, EZDPStringItem item, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPINGREQUEST.equals(param.shpgModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0014E);
            }
        }
    }

    private void chkTransactionHeaderNumber(NWZC003001PMsg param, EZDPStringItem item, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPINGREQUEST.equals(param.shpgModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0027E);
            }
        }
    }

    private void chkTransactionLineNumber(NWZC003001PMsg param, EZDPStringItem item, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPINGREQUEST.equals(param.shpgModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0089E);
            }
        }
    }

    private void chkTransactionLineSubNumber(NWZC003001PMsg param, EZDPStringItem item, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPINGREQUEST.equals(param.shpgModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0043E);
            }
        }
    }

    private void chkAvailableShippingOrderQuantity(NWZC003001PMsg param, EZDPBigDecimalItem item, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPINGREQUEST.equals(param.shpgModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0034E);
            }
        }
    }

    private void chkInvoiceNumber(NWZC003001PMsg param, EZDPStringItem item, S21ApiMessageMap msgMap) {

        if (MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(item)) {
                msgMap.addXxMsgId(NWZM0072E);
            }
        }
    }

    private void chkDataParameterList(List<NWZC003001PMsg> params, NWZC003001PMsg param, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPED.equals(param.shpgModeCd.getValue()) //
// 2016/06/20 S21_NA#594 Add Start
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
// 2016/06/20 S21_NA#594 Add End

            if (params.size() > 1) {
                msgMap.addXxMsgId(NWZM0338E);
            }
        }
    }

    private void chkCPOExistenceFlag(NWZC003001PMsg param, S21ApiMessageMap msgMap) {

        if (MODE_SHIPPINGREQUEST.equals(param.shpgModeCd.getValue())) {

            if (isCPOExistFlg(param, true, null)) {
                msgMap.addXxMsgId(NWZM0013E);
            }
        }
    }

    private boolean isCPOExistFlg(NWZC003001PMsg param, boolean pattern, SHPG_PLNTMsg shpgPlnMsg) {

        String shpgModeCd = param.shpgModeCd.getValue();
        String cpoExistFlg = null;

        // pattern == true ( use : NWZC003001PMsg param )
        // pattern == false ( use : SHPG_PLNTMsg shpgPlnMsg )

        if (pattern) {

            // Search Key : Shipping Plan Number
            if (MODE_SHIPPED.equals(shpgModeCd)) {
                cpoExistFlg = getCPOExistenceFlagKeyShippingPlanNumber(param);

                // Search Key : Transaction Source Type Code
            } else if (MODE_SHIPPINGREQUEST.equals(shpgModeCd)) {
                cpoExistFlg = getCPOExistenceFlagKeyTransactionSourceTypeCode(param.glblCmpyCd.getValue(), param.trxSrcTpCd.getValue());
            }
        } else {
            cpoExistFlg = getCPOExistenceFlagKeyTransactionSourceTypeCode(param.glblCmpyCd.getValue(), shpgPlnMsg.trxSrcTpCd.getValue());
        }

        if (ZYPConstant.FLG_ON_Y.equals(cpoExistFlg)) {
            return true;
        }
        return false;
    }

    private String getCPOExistenceFlagKeyTransactionSourceTypeCode(String glblCmpyCd, String trxSrcTpCd) {

        TRX_SRC_TPTMsg tMsg = new TRX_SRC_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.trxSrcTpCd.setValue(trxSrcTpCd);
        tMsg = (TRX_SRC_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
        return tMsg.cpoExstFlg.getValue();
    }

    private void distributeMode(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue())) {
            hardAllocatedSoCancelled(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_HARDALLOCATED_ROUTED.equals(param.shpgModeCd.getValue())) {
            hardAllocatedRouted(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
            printedSoCreating(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_PRINTED_SOCREATED.equals(param.shpgModeCd.getValue())) {
            printedSoCreated(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_POPRINTED.equals(param.shpgModeCd.getValue())) {
            poPrinted(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_PICKED.equals(param.shpgModeCd.getValue())) {
            picked(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_PACKED.equals(param.shpgModeCd.getValue())) {
            packed(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_STAGED.equals(param.shpgModeCd.getValue())) {
            staged(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_INSHED.equals(param.shpgModeCd.getValue())) {
            inShed(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())) {
            shipped(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_SHIPPED_SOCLOSE_AINVOICE.equals(param.shpgModeCd.getValue())) {
            shippedSoCloseAInvoice(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_SHIPPED_SOCLOSE.equals(param.shpgModeCd.getValue())) {
            shippedSoClose(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_ARRIVED_SOCLOSE.equals(param.shpgModeCd.getValue())) {
            arrived(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_POCANCELLED.equals(param.shpgModeCd.getValue())) {
            poCancelled(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_SHIPPINGREQUEST.equals(param.shpgModeCd.getValue())) {
            shippingRequest(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue())) {
            vendorInvoiceReceived(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_FORCEDSHIPMENT.equals(param.shpgModeCd.getValue())) {
            forcedShipment(spUpdModeMsg, msgMap, onBatchType);
// START ADD/MOD 2013/04/22 S.Yamamoto [094]
        } else if (MODE_PSD_PDD_RSD_RDD_UPDATE.equals(param.shpgModeCd.getValue())) {
            psdPddRsdRddUpdate(spUpdModeMsg, msgMap, onBatchType);
        } else if (MODE_INSTALLED.equals(param.shpgModeCd.getValue())) {
            installed(spUpdModeMsg, msgMap, onBatchType);
// END   ADD/MOD 2013/04/22 S.Yamamoto [094]
         // 2016/06/20 S21_NA#594 Add Start
        } else if (MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
            partialShip(spUpdModeMsg, msgMap, onBatchType);
        // 2016/06/20 S21_NA#594 Add End
        /* 07/11/2016 CSAI Y.Imazu Add QC#10917 START */
        } else if (MODE_ARRIVED_SHPG_PLN.equals(param.shpgModeCd.getValue())) {
            arrivedShpgPln(spUpdModeMsg, msgMap, onBatchType);
        /* 07/11/2016 CSAI Y.Imazu Add QC#10917 END */
        }
    }

    private void hardAllocatedSoCancelled(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [hardAllocatedSoCancelled] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update ShippingPlan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // ===================================================================
        // none

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_HARDALLOCATED_SOCANCELLED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // ===================================================================
        // QC# 21342 Comment Out Start
//        if (isCPOExistFlg(param, false, shpgPlnMsg)) {
//
//            callAddHoldInfoAPI(shpgPlnMsg, msgMap, onBatchType);
//
//            // Error Judgment
//            if (msgMap.getMsgMgr().isXxMsgId()) {
//                return;
//            }
//        }
        // QC# 21342 Comment Out End

        // ===================================================================
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // ===================================================================
        // none

        // ===================================================================
        // (19)Update Set Shipping Plan
        // ===================================================================
        updSetShpgPlnSts(msgMap, shpgPlnMsg, onBatchType);

        // 2015/09/10 CNA(2.8 Allocation - SO/PO) Add Start
        applyAllocationCancel(msgMap, shpgPlnMsg, onBatchType);
        // 2015/09/10 CNA(2.8 Allocation - SO/PO) Add End
        debug("-- MODE [hardAllocatedSoCancelled] END --");
    }

    private void hardAllocatedRouted(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [hardAllocatedRouted] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // ===================================================================
        // none

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_HARDALLOCATED_ROUTED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // (19)Update Set Shipping Plan
        // ===================================================================
        // none

        debug("-- MODE [hardAllocatedRouted] END --");
    }

    private void printedSoCreating(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [printedSoCreating] START --");

        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        debug(shpgPlnMsg.shpgPlnNum.getValue());

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg divShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, divShpgPlnMsg, null);
        // 2018/08/09 S21_NA#26865 Mod Start
//        dividesShippingplan(divShpgPlnMsg, null, msgMap);
        dividesShippingplan(divShpgPlnMsg, null, msgMap, onBatchType);
        // 2018/08/09 S21_NA#26865 Mod End

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (8)Update Divides Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg divOrigShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, divOrigShpgPlnMsg, null);
        // 2018/08/09 S21_NA#26865 Mod Start
//        updateOriginalShippingplan(divOrigShpgPlnMsg, spUpdModeMsg, msgMap);
        updateOriginalShippingplan(divOrigShpgPlnMsg, spUpdModeMsg, msgMap, onBatchType);
        // 2018/08/09 S21_NA#26865 Mod End

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (9)Check Update Shipping Plan
        // ===================================================================
        // none

        // ===================================================================
        // (10)Divides Pricing Detail
        // ===================================================================
        dividesPricingDetail(shpgPlnMsg, divShpgPlnMsg, divOrigShpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (11)Divides Soft Allocation
        // ===================================================================
        dividesSoftAllocation(shpgPlnMsg, divShpgPlnMsg, divOrigShpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // ===================================================================
        updateSoftAllocQty(shpgPlnMsg, updShpgPlnMsg, divShpgPlnMsg, divOrigShpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (13)Update CPO Detail
        // (14)Business Process Log
        // (15)Process Hold
        // ===================================================================
        // none

        // ===================================================================
        // (16)Update Hold
        // ===================================================================
        updateHold(shpgPlnMsg, divShpgPlnMsg, msgMap);

        // ===================================================================
        // (17)Divides Hard Allocation
        // ===================================================================
        dividesHardAllocation(shpgPlnMsg, divShpgPlnMsg, divOrigShpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (18)Divides SetComponent Divide
        // ===================================================================
        // none

        // ===================================================================
        // (19)Update Set Shipping Plan
        // ===================================================================
        updSetShpgPlnSts(msgMap, shpgPlnMsg, onBatchType);

        debug("-- MODE [printedSoCreating] END --");
    }

    private void printedSoCreated(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [printedSoCreated] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg tempShpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlan(tempShpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        CPOTMsg cpoMsg = new CPOTMsg();
        CPO_DTLTMsg cpoDtlMsg = new CPO_DTLTMsg();
        CPO_DTLTMsg cpoDtlSetItemMsg = null;
        SHPG_PLNTMsg shpgPlnSetItemMsg = null;

        String cpoExistFlg = getCPOExistenceFlagKeyTransactionSourceTypeCode(param.glblCmpyCd.getValue(), tempShpgPlnMsg.trxSrcTpCd.getValue());

        // ===================================================================
        // (2)Search CPO（select for update）
        // ===================================================================
        if (ZYPConstant.FLG_ON_Y.equals(cpoExistFlg)) {
            searchCPOForUpdate(tempShpgPlnMsg, cpoMsg, msgMap);
        }

        boolean isLineDropShip = ZYPConstant.FLG_ON_Y.equals(tempShpgPlnMsg.dropShipFlg.getValue());

        if (!isLineDropShip) {

            if (ZYPConstant.FLG_ON_Y.equals(cpoExistFlg)) {

                // ===================================================================
                // (2)Search CPO Detail Set item（select for update）
                // ===================================================================
                cpoDtlSetItemMsg = searchCPODetailForUpdateSetItem(tempShpgPlnMsg, msgMap);

                // ===================================================================
                // (2)Search CPO Detail（select for update）
                // ===================================================================
                searchCPODetailForUpdate(tempShpgPlnMsg, cpoDtlMsg, msgMap);
            }

            // ===================================================================
            // (3)Search Shipping Plan Set item（select for update）
            // ===================================================================
            shpgPlnSetItemMsg = searchShippingPlanForUpdateSetItem(tempShpgPlnMsg);
        }

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (6)Update Shipping Plan(Set item Ship Data only)
        // ===================================================================
        if (!isLineDropShip) {

            if (shpgPlnSetItemMsg != null) {

                updateShpgPlnShipDataSetItem(shpgPlnSetItemMsg, msgMap);

                // Error Judgment
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            }
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // ===================================================================
        // none

        // ===================================================================
        // (13)Update Ship Data
        // ===================================================================
        if (ZYPConstant.FLG_OFF_N.equals(cpoMsg.addDropShipFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(cpoExistFlg)) {

            // Update CPO
            updateCPOShipData(cpoMsg, msgMap);
        }

        if (!isLineDropShip && ZYPConstant.FLG_ON_Y.equals(cpoExistFlg)) {

            // Update CPO Detail
            updateCPODetailShipData(cpoDtlMsg, msgMap);

            // Update CPO Detail(Set item)
            if (cpoDtlSetItemMsg != null) {
                updateCPODetailShipData(cpoDtlSetItemMsg, msgMap);
            }

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_PRINTED_SOCREATED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // (19)Update Set Shipping Plan
        // ===================================================================
        // none
        debug("-- MODE [printedSoCreated] END --");
    }

    private void poPrinted(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [poPrinted] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg tempShpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlan(tempShpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        CPOTMsg cpoMsg = new CPOTMsg();
        CPO_DTLTMsg cpoDtlMsg = new CPO_DTLTMsg();
        CPO_DTLTMsg cpoDtlSetItemMsg = null;
        SHPG_PLNTMsg shpgPlnSetItemMsg = null;

        String cpoExistFlg = getCPOExistenceFlagKeyTransactionSourceTypeCode(param.glblCmpyCd.getValue(), tempShpgPlnMsg.trxSrcTpCd.getValue());

        // ===================================================================
        // (2)Search CPO（select for update）
        // ===================================================================
        if (ZYPConstant.FLG_ON_Y.equals(cpoExistFlg)) {
            searchCPOForUpdate(tempShpgPlnMsg, cpoMsg, msgMap);
        }

        boolean isLineDropShip = ZYPConstant.FLG_ON_Y.equals(tempShpgPlnMsg.dropShipFlg.getValue());

        if (!isLineDropShip) {

            if (ZYPConstant.FLG_ON_Y.equals(cpoExistFlg)) {

                // ===================================================================
                // (2)Search CPO Detail Set item（select for update）
                // ===================================================================
                cpoDtlSetItemMsg = searchCPODetailForUpdateSetItem(tempShpgPlnMsg, msgMap);

                // ===================================================================
                // (2)Search CPO Detail（select for update）
                // ===================================================================
                searchCPODetailForUpdate(tempShpgPlnMsg, cpoDtlMsg, msgMap);
            }

            // ===================================================================
            // (3)Search Shipping Plan Set item（select for update）
            // ===================================================================
            shpgPlnSetItemMsg = searchShippingPlanForUpdateSetItem(tempShpgPlnMsg);
        }

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (6)Update Shipping Plan(Set item Ship Data only)
        // ===================================================================
        if (!isLineDropShip) {

            if (shpgPlnSetItemMsg != null) {

                updateShpgPlnShipDataSetItem(shpgPlnSetItemMsg, msgMap);

                // Error Judgment
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            }
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg divShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, divShpgPlnMsg, null);
        // 2018/08/09 S21_NA#26865 Mod Start
//        dividesShippingplan(divShpgPlnMsg, null, msgMap);
        dividesShippingplan(divShpgPlnMsg, null, msgMap, onBatchType);
        // 2018/08/09 S21_NA#26865 Mod End

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (8)Update Divides Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg divOrigShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, divOrigShpgPlnMsg, null);
        // 2018/08/09 S21_NA#26865 Mod Start
//        updateOriginalShippingplan(divOrigShpgPlnMsg, spUpdModeMsg, msgMap);
        updateOriginalShippingplan(divOrigShpgPlnMsg, spUpdModeMsg, msgMap, onBatchType);
      // 2018/08/09 S21_NA#26865 Mod End

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (9)Check Update Shipping Plan
        // ===================================================================
        // none

        // ===================================================================
        // (10)Divides Pricing Detail
        // ===================================================================
        dividesPricingDetail(shpgPlnMsg, divShpgPlnMsg, divOrigShpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // ===================================================================
        // none

        // ===================================================================
        // (13)Update Ship Data
        // ===================================================================
        if (ZYPConstant.FLG_OFF_N.equals(cpoMsg.addDropShipFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(cpoExistFlg)) {

            // Update CPO
            updateCPOShipData(cpoMsg, msgMap);
        }

        if (!isLineDropShip && ZYPConstant.FLG_ON_Y.equals(cpoExistFlg)) {

            // Update CPO Detail
            updateCPODetailShipData(cpoDtlMsg, msgMap);

            // Update CPO Detail(Set item)
            if (cpoDtlSetItemMsg != null) {
                updateCPODetailShipData(cpoDtlSetItemMsg, msgMap);
            }

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        // ===================================================================
        // (17)Divides Hard Allocation
        // ===================================================================
        // none

        // ===================================================================
        // (18)Divides SetComponent Divide
        // ===================================================================
        setCompDivideProcess(spUpdModeMsg, msgMap, shpgPlnMsg, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_POPRINTED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // (16)Update Hold
        // (19)Update Set Shipping Plan
        // ===================================================================
        // none

        debug("-- MODE [poPrinted] END --");
    }

    private void picked(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [picked] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // ===================================================================
        // none

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_PICKED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // (19)Update Set Shipping Plan
        // ===================================================================
        // none

        debug("-- MODE [picked] END --");
    }

    private void packed(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [packed] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // ===================================================================
        // none

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_PACKED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // (19)Update Set Shipping Plan
        // ===================================================================
        // none
        debug("-- MODE [packed] END --");
    }

    private void staged(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [staged] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // ===================================================================
        // none

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_STAGED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // (19)Update Set Shipping Plan
        // ===================================================================
        // none
        debug("-- MODE [staged] END --");
    }

    private void inShed(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [inShed] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // ===================================================================
        // none

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_INSHED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // ===================================================================
        // none

        // ===================================================================
        // (19)Update Set Shipping Plan
        // ===================================================================
        updSetShpgPlnSts(msgMap, shpgPlnMsg, onBatchType);

        debug("-- MODE [inShed] END --");
    }

    private void shipped(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [shipped] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        if (SHPG_STS.SHIPPED.equals(shpgPlnMsg.shpgStsCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(param.spTotFuncFrtAmt.getValue())) {
                return;
            } else if (param.spTotFuncFrtAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
                return;
            }
        }
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // ===================================================================
        // none

        // ===================================================================
        // (9)Check Update Shipping Plan
        // ===================================================================
        chkUpdateShippingPlan(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // ===================================================================
        // none

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_SHIPPED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // ===================================================================
        // none

        // ===================================================================
        // (19)Update Set Shipping Plan
        // ===================================================================
        updSetShpgPlnSts(msgMap, shpgPlnMsg, onBatchType);

        // QC#546 Add Start
        // ===================================================================
        // (20)Update Loan Balance Quantity
        // ===================================================================
        updateLoanBalanceQty(shpgPlnMsg, msgMap);
        // QC#546 Add End

        debug("-- MODE [shipped] END --");
    }

    private void shippedSoCloseAInvoice(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [shippedSoCloseAInvoice] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // ===================================================================
        // none

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_SHIPPED_SOCLOSE_AINVOICE, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // (19)Update Set Shipping Plan
        // ===================================================================
        // none
        debug("-- MODE [shippedSoCloseAInvoice] END --");
    }

    private void shippedSoClose(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [shippedSoClose] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // ===================================================================
        SHPG_PLNTMsgArray shpgPlnMsgArray = new SHPG_PLNTMsgArray();
        searchShippingPlan(null, shpgPlnMsgArray, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {

            // ===================================================================
            // (2)Search CPO Detail（select for update）
            // ===================================================================
            CPO_DTLTMsg cpoDtlMsg = new CPO_DTLTMsg();
            searchCPODetailForUpdate((SHPG_PLNTMsg) shpgPlnMsgArray.no(i), cpoDtlMsg, msgMap);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // ===================================================================
            // (3)Search Shipping Plan（select for update）
            // ===================================================================
            searchShippingPlanForUpdate(shpgPlnMsgArray.no(i), null, msgMap);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {

            chkStatus(shpgPlnMsgArray.no(i), msgMap);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {
            // 2016/06/29 S21_NA#7189 Add Start
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, shpgPlnMsgArray.no(i).soCloseFlg.getValue())) {
                continue;
            }
            // 2016/06/29 S21_NA#7189 Add End
            SHPG_PLNTMsg shpgPlnMsg = (SHPG_PLNTMsg) shpgPlnMsgArray.no(i);
            SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
            EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
            updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // ===================================================================
            // (7)Divides Shipping Plan
            // (8)Update Divides Shipping Plan
            // (9)Check Update Shipping Plan
            // (10)Divides Pricing Detail
            // (11)Divides Soft Allocation
            // (12)Update Shipping Plan(Soft Allocation Quantity)
            // ===================================================================
            // none

            // ===================================================================
            // (13)Update CPO Detail
            // ===================================================================
            updateCPODetail(updShpgPlnMsg, msgMap);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // ===================================================================
            // (14)Business Process Log
            // ===================================================================
            outProcessLog(param, EVENTID_SHIPPED_SOCLOSE, shpgPlnMsg);

            // ===================================================================
            // (15)Process Hold
            // (16)Update Hold
            // (17)Divides Hard Allocation
            // (18)Divides SetComponent Divide
            // (19)Update Set Shipping Plan
            // ===================================================================
            // none
        }

        debug("-- MODE [shippedSoClose] END --");
    }

    private void arrived(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [arrived] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsgArray shpgPlnMsgArray = new SHPG_PLNTMsgArray();
        searchShippingPlanForUpdate(null, shpgPlnMsgArray, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {

            chkStatus(shpgPlnMsgArray.no(i), msgMap);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        // 2015/09/10 CNA(2.9 Revenue Recognition) Add Start
        Map<String, Boolean> chkHdrLvl = new HashMap<String, Boolean>();
        NWZC044001 addHldInfoApi = new NWZC044001();
        List<NWZC044001PMsg> addHldInfoApiPMsgList = new ArrayList<NWZC044001PMsg>();
        // 2015/09/10 CNA(2.9 Revenue Recognition) Add End
        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {
            SHPG_PLNTMsg shpgPlnMsg = (SHPG_PLNTMsg) shpgPlnMsgArray.no(i);
            SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
            EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
            updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, i, onBatchType);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // ===================================================================
            // (7)Divides Shipping Plan
            // (8)Update Divides Shipping Plan
            // (9)Check Update Shipping Plan
            // (10)Divides Pricing Detail
            // (11)Divides Soft Allocation
            // (12)Update Shipping Plan(Soft Allocation Quantity)
            // (13)Update CPO Detail
            // ===================================================================
            // none

            // ===================================================================
            // (14)Business Process Log
            // ===================================================================
            outProcessLog(param, EVENTID_ARRIVED, shpgPlnMsg);

            // ===================================================================
            // (15)Process Hold
            // (16)Update Hold
            // (17)Divides Hard Allocation
            // (18)Divides SetComponent Divide
            // (19)Update Set Shipping Plan
            // ===================================================================
            // none
            //QC#16639 add Start
            if (SHPG_STS.INSTALLED.equals(updShpgPlnMsg.shpgStsCd.getValue())
               || SHPG_STS.INVOICED.equals(updShpgPlnMsg.shpgStsCd.getValue())
               || SHPG_STS.CANCELLED.equals(updShpgPlnMsg.shpgStsCd.getValue())
               || SHPG_STS.ARRIVED.equals(shpgPlnMsg.shpgStsCd.getValue()) // 2019/03/08 S21_NA#30550 Add
               ) {
                continue;
            }
            //QC#16639 add End

            // 2015/09/10 CNA(2.9 Revenue Recognition) Add Start
            // Check Header Level: Delivery Basis
            Boolean rsltHdr = chkHdrLvl.get(updShpgPlnMsg.trxHdrNum.getValue());
            if (null == rsltHdr) {
                rsltHdr = chkHeaderLvlDeliveryBasis(updShpgPlnMsg);
                chkHdrLvl.put(updShpgPlnMsg.trxHdrNum.getValue(), rsltHdr);
            }
            Boolean rsltConfig = chkInConfigDeliveryBasis(updShpgPlnMsg);

            if (rsltHdr.booleanValue() && rsltConfig.booleanValue()) {
                // set Billing Hold
                NWZC044001PMsg hldInfoApiPMsg = new NWZC044001PMsg();
                hldInfoApiPMsg.glblCmpyCd.setValue(updShpgPlnMsg.glblCmpyCd.getValue());
                hldInfoApiPMsg.cpoOrdNum.setValue(updShpgPlnMsg.trxHdrNum.getValue());
                // 2016/09/20 S21_NA#14557 M.Ohno Mod Start
                // -> S21_NA#14557-2 cancel start
                hldInfoApiPMsg.cpoDtlLineNum.setValue(updShpgPlnMsg.trxLineNum.getValue());
                hldInfoApiPMsg.cpoDtlLineSubNum.setValue(updShpgPlnMsg.trxLineSubNum.getValue());
                hldInfoApiPMsg.shpgPlnNum.setValue(updShpgPlnMsg.shpgPlnNum.getValue());
                // 2016/09/20 S21_NA#14557 M.Ohno Mod End
                // -> S21_NA#14557-2 cancel end
                hldInfoApiPMsg.hldRsnCd.setValue(HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION);
                hldInfoApiPMsg.slsDt.setValue(param.actlArvDt.getValue());
                //hldInfoApiPMsg.hldUntilDt.clear();
                //hldINfoApiPMsg.hldAplyRsnCd.clear();
                addHldInfoApiPMsgList.add(hldInfoApiPMsg);
            }
            // 2015/09/10 CNA(2.9 Revenue Recognition) Add End
        }
        // 2015/09/10 CNA(2.9 Revenue Recognition) Add Start
        if (!addHldInfoApiPMsgList.isEmpty()) {
            addHldInfoApi.execute(addHldInfoApiPMsgList, onBatchType);
        }
        // 2015/09/10 CNA(2.9 Revenue Recognition) Add End
        // 2019/03/08 S21_NA#30550 Add Start
        for (NWZC044001PMsg pMsg : addHldInfoApiPMsgList) {
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = pMsg.xxMsgIdList.getValidCount() - 1; i >= 0; i--) {
                    final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    if (xxMsgId != null) {
                        if (!"E".equals(getMessageKind(msgMap))) {
                            msgMap.addXxMsgId(xxMsgId);
                        }
                    }
                }
            }
        }
        // 2019/03/08 S21_NA#30550 Add End

        debug("-- MODE [arrived] END --");
    }

    private void poCancelled(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [poCancelled] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // ===================================================================
        // none

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_POCANCELLED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // ===================================================================
        callAddHoldInfoAPI(shpgPlnMsg, msgMap, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // ===================================================================
        // none

        // ===================================================================
        // (19)Update Set Shipping Plan
        // ===================================================================
        updSetShpgPlnSts(msgMap, shpgPlnMsg, onBatchType);

        debug("-- MODE [poCancelled] END --");
    }

    private void shippingRequest(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [shippingRequest] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsgArray shpgPlnMsgArray = new SHPG_PLNTMsgArray();
        searchShippingPlanForUpdate(null, shpgPlnMsgArray, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {

            chkStatus(shpgPlnMsgArray.no(i), msgMap);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // (5-1) Available Shipping Order Quantity Check
        BigDecimal totalQty = BigDecimal.ZERO;
        BigDecimal avaSOQty = BigDecimal.ZERO;

        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {
            totalQty = totalQty.add(shpgPlnMsgArray.no(i).ordQty.getValue());
            avaSOQty = avaSOQty.add(shpgPlnMsgArray.no(i).avalSoQty.getValue());
        }
        if ((totalQty.subtract(avaSOQty)).compareTo(param.avalSoQty.getValue()) < 0) {
            msgMap.addXxMsgId(NWZM0339E);
            return;
        }
        if (BigDecimal.ZERO.compareTo(param.avalSoQty.getValue()) == 0) {
            msgMap.addXxMsgId(NWZM0339E);
            return;
        }

        // (5-3) Available Shipping Order Quantity is set.
        BigDecimal totalAvaSOQty = param.avalSoQty.getValue();
        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {

            if (totalAvaSOQty.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }

            BigDecimal wkAvalSOQty = shpgPlnMsgArray.no(i).ordQty.getValue().subtract(shpgPlnMsgArray.no(i).avalSoQty.getValue());
            if (wkAvalSOQty.compareTo(totalAvaSOQty) > 0) {

                shpgPlnMsgArray.no(i).avalSoQty.setValue(shpgPlnMsgArray.no(i).avalSoQty.getValue().add(totalAvaSOQty));
                break;

            } else {

                shpgPlnMsgArray.no(i).avalSoQty.setValue(shpgPlnMsgArray.no(i).avalSoQty.getValue().add(wkAvalSOQty));
                totalAvaSOQty = totalAvaSOQty.subtract(wkAvalSOQty);
            }
        }

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {
            SHPG_PLNTMsg shpgPlnMsg = (SHPG_PLNTMsg) shpgPlnMsgArray.no(i);
            SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
            EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
            updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, i, onBatchType);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // ===================================================================
            // (7)Divides Shipping Plan
            // (8)Update Divides Shipping Plan
            // (9)Check Update Shipping Plan
            // (10)Divides Pricing Detail
            // (11)Divides Soft Allocation
            // (12)Update Shipping Plan(Soft Allocation Quantity)
            // (13)Update CPO Detail
            // (14)Business Process Log
            // (15)Process Hold
            // (16)Update Hold
            // ===================================================================
            // none

            // ===================================================================
            // (17)Divides Hard Allocation
            // (18)Divides SetComponent Divide
            // (19)Update Set Shipping Plan
            // ===================================================================
            // none
        }

        debug("-- MODE [shippingRequest] END --");
    }

    private void vendorInvoiceReceived(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [vendorInvoiceReceived] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlan(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        CPO_DTLTMsg cpoDtlMsg = new CPO_DTLTMsg();
        searchCPODetailForUpdate(shpgPlnMsg, cpoDtlMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg divShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, divShpgPlnMsg, null);
        // 2018/08/09 S21_NA#26865 Mod Start
//        dividesShippingplan(divShpgPlnMsg, spUpdModeMsg, msgMap);
        dividesShippingplan(divShpgPlnMsg, spUpdModeMsg, msgMap, onBatchType);
        // 2018/08/09 S21_NA#26865 Mod End

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (8)Update Divides Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg divOrigShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, divOrigShpgPlnMsg, null);
        // 2018/08/09 S21_NA#26865 Mod Start
//        updateOriginalShippingplan(divOrigShpgPlnMsg, spUpdModeMsg, msgMap);
        updateOriginalShippingplan(divOrigShpgPlnMsg, spUpdModeMsg, msgMap, onBatchType);
        // 2018/08/09 S21_NA#26865 Mod End

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (9)Check Update Shipping Plan
        // ===================================================================
        chkUpdateShippingPlan(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (10)Divides Pricing Detail
        // ===================================================================
        dividesPricingDetail(shpgPlnMsg, divShpgPlnMsg, divOrigShpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // ===================================================================
        // none

        // ===================================================================
        // (13)Update CPO Detail
        // ===================================================================
        if (shpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) == 0) {
            updateCPODetail(updShpgPlnMsg, msgMap);
        } else {
            updateCPODetail(divShpgPlnMsg, msgMap);
        }

        // ===================================================================
        // (17)Divides Hard Allocation
        // ===================================================================
        // none

        // ===================================================================
        // (18)Divides SetComponent Divide
        // ===================================================================
        setCompDivideProcess(spUpdModeMsg, msgMap, shpgPlnMsg, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_VENDERINVOICERECEIVED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // (16)Update Hold
        // (19)Update Set Shipping Plan
        // ===================================================================
        // none

        // QC#546 Add Start
        // ===================================================================
        // (20)Update Loan Balance Quantity
        // ===================================================================
        updateLoanBalanceQty(shpgPlnMsg, msgMap);
        // QC#546 Add End

        debug("-- MODE [vendorInvoiceReceived] END --");
    }

    private void forcedShipment(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [forcedShipment] START --");

        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // (14)Business Process Log
        // (15)Process Hold
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // (19)Update Set Shipping Plan
        // ===================================================================
        // none
        debug("-- MODE [forcedShipment] END --");
    }

    private void psdPddRsdRddUpdate(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [psdPddRsdRddUpdate] START --");

        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // (14)Business Process Log
        // (15)Process Hold
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // (19)Update Set Shipping Plan
        // ===================================================================
        // none
        debug("-- MODE [psdPddRsdRddUpdate] END --");
    }

    private void searchShippingPlan(SHPG_PLNTMsg shpgPlnMsg, SHPG_PLNTMsgArray shpgPlnMsgArray, S21ApiMessageMap msgMap) {
        debug("-- >>>> [searchShippingPlan] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

// START MOD 2013/04/22 S.Yamamoto [094]
        // if (MODE_SHIPPED_SOCLOSE.equals(param.shpgModeCd.getValue())) {
        if (MODE_SHIPPED_SOCLOSE.equals(param.shpgModeCd.getValue())
                || MODE_INSTALLED.equals(param.shpgModeCd.getValue())) {
// END   MOD 2013/04/22 S.Yamamoto [094]

            /* 12/16/2016 CSAI Y.Imazu Delete QC#16614 START */
//            SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
//            tMsg.setSQLID("017");
//            tMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
//            tMsg.setConditionValue("soNum01", param.soNum.getValue());
//            tMsg.setConditionValue("shpgStsCd01", SHPG_STS.S_OR_O_CANCELLED);
//            SHPG_PLNTMsgArray tMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
//            if (tMsgArray.getValidCount() == 0) {
//                msgMap.addXxMsgId(NWZM0075E);
//                return;
//            }
//
//            shpgPlnMsgArray.setValidCount(tMsgArray.getValidCount());
//            shpgPlnMsgArray.setMsgList(new EZDTMsg[tMsgArray.getValidCount()]);
//            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
//                EZDMsg.copy(tMsgArray.no(i), null, shpgPlnMsgArray.get(i), null);
//            }
            /* 12/16/2016 CSAI Y.Imazu Delete QC#16614 END */

            /* 12/16/2016 CSAI Y.Imazu Add QC#16614 START */
            HashMap<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            queryParam.put("soNum", param.soNum.getValue());
            queryParam.put("shpgStsCd39", SHPG_STS.S_OR_O_CANCELLED);
            queryParam.put("shpgStsCd99", SHPG_STS.CANCELLED);
            /* 01/25/2018 CSAI T.Tokutomi Add QC#23776 START */
            if(MODE_SHIPPED_SOCLOSE.equals(param.shpgModeCd.getValue())){
                queryParam.put("invtyCtrlFlg_Y", ZYPConstant.FLG_ON_Y);
            }
            /* 01/25/2018 CSAI T.Tokutomi Add QC#23776 END */

            List<SHPG_PLNTMsg> openShpgPlnList = (ArrayList<SHPG_PLNTMsg>) ssmBatchClient.queryObjectList("getOpenShpgPlnList", queryParam);

            if (openShpgPlnList == null || openShpgPlnList.isEmpty()) {

                msgMap.addXxMsgId(NWZM0075E);
                return;
            }

            shpgPlnMsgArray.setValidCount(openShpgPlnList.size());
            shpgPlnMsgArray.setMsgList(new EZDTMsg[openShpgPlnList.size()]);

            for (int i = 0; i < openShpgPlnList.size(); i++) {

                EZDMsg.copy(openShpgPlnList.get(i), null, shpgPlnMsgArray.get(i), null);
            }
            /* 12/16/2016 CSAI Y.Imazu Add QC#16614 END */

        } else if (MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue())
                || MODE_PRINTED_SOCREATED.equals(param.shpgModeCd.getValue())
                || MODE_POPRINTED.equals(param.shpgModeCd.getValue())) {

            SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
            tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            tMsg.shpgPlnNum.setValue(param.shpgPlnNum.getValue());
            tMsg = (SHPG_PLNTMsg) S21ApiTBLAccessor.findByKey(tMsg);
            if (null == tMsg) {
                msgMap.addXxMsgId(NWZM0075E);
                return;
            }
            EZDMsg.copy(tMsg, null, shpgPlnMsg, null);
        }
        debug("-- >>>> [searchShippingPlan] END --");
    }

    private void searchShippingPlanForUpdate(SHPG_PLNTMsg shpgPlnMsg, SHPG_PLNTMsgArray shpgPlnMsgArray, S21ApiMessageMap msgMap) {
        debug("-- >>>> [searchShippingPlanForUpdate] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue())
                || MODE_HARDALLOCATED_ROUTED.equals(param.shpgModeCd.getValue())
                || MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())
                || MODE_PICKED.equals(param.shpgModeCd.getValue())
                || MODE_PACKED.equals(param.shpgModeCd.getValue())
                || MODE_STAGED.equals(param.shpgModeCd.getValue())
                || MODE_INSHED.equals(param.shpgModeCd.getValue())
                || MODE_SHIPPED.equals(param.shpgModeCd.getValue())
                || MODE_SHIPPED_SOCLOSE_AINVOICE.equals(param.shpgModeCd.getValue())
                || MODE_POCANCELLED.equals(param.shpgModeCd.getValue())
                || MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue())
// START ADD 2013/04/22 S.Yamamoto [094]
                || MODE_PSD_PDD_RSD_RDD_UPDATE.equals(param.shpgModeCd.getValue())
// END   ADD 2013/04/22 S.Yamamoto [094]
// 2016/06/20 S21_NA#594 Add Start
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())
// 2016/06/20 S21_NA#594 Add End
                /* 07/11/2016 CSAI Y.Imazu Add QC#10917 START */
                || MODE_ARRIVED_SHPG_PLN.equals(param.shpgModeCd.getValue())) {
                /* 07/11/2016 CSAI Y.Imazu Add QC#10917 END */

            SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
            tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            tMsg.shpgPlnNum.setValue(param.shpgPlnNum.getValue());
            tMsg = (SHPG_PLNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
            if (null == tMsg) {
                msgMap.addXxMsgId(NWZM0075E);
                return;
            }
            EZDMsg.copy(tMsg, null, shpgPlnMsg, null);

        } else if (MODE_PRINTED_SOCREATED.equals(param.shpgModeCd.getValue())
                || MODE_FORCEDSHIPMENT.equals(param.shpgModeCd.getValue())) {

            SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
            tMsg.setSQLID("015");
            tMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            tMsg.setConditionValue("shpgPlnNum01", param.shpgPlnNum.getValue());
            SHPG_PLNTMsgArray tMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
            if (tMsgArray.getValidCount() == 0) {
                msgMap.addXxMsgId(NWZM0075E);
                return;
            }
            EZDMsg.copy(tMsgArray.no(0), null, shpgPlnMsg, null);

        } else if (MODE_POPRINTED.equals(param.shpgModeCd.getValue())) {

            SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
            tMsg.setSQLID("028");
            tMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            tMsg.setConditionValue("shpgPlnNum01", param.shpgPlnNum.getValue());
            SHPG_PLNTMsgArray tMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
            if (tMsgArray.getValidCount() == 0) {
                msgMap.addXxMsgId(NWZM0075E);
                return;
            }
            EZDMsg.copy(tMsgArray.no(0), null, shpgPlnMsg, null);

// START MOD 2013/04/22 S.Yamamoto [094]
            // } else if  (MODE_SHIPPED_SOCLOSE.equals(param.shpgModeCd.getValue())) {
        } else if (MODE_SHIPPED_SOCLOSE.equals(param.shpgModeCd.getValue())
                || MODE_INSTALLED.equals(param.shpgModeCd.getValue())) {
// END   MOD 2013/04/22 S.Yamamoto [094]

            SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
            tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            tMsg.shpgPlnNum.setValue(shpgPlnMsg.shpgPlnNum.getValue());
            tMsg = (SHPG_PLNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
            if (null == tMsg) {
                msgMap.addXxMsgId(NWZM0075E);
                return;
            }

        } else if (MODE_ARRIVED_SOCLOSE.equals(param.shpgModeCd.getValue())) {

            SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
            tMsg.setSQLID("016");
            tMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            tMsg.setConditionValue("bolNum01", param.bolNum.getValue());
            tMsg.setConditionValue("invtyLocCd01", param.invtyLocCd.getValue());
            SHPG_PLNTMsgArray tMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
            if (tMsgArray.getValidCount() == 0) {
                msgMap.addXxMsgId(NWZM0075E);
                return;
            }

            shpgPlnMsgArray.setValidCount(tMsgArray.getValidCount());
            shpgPlnMsgArray.setMsgList(new EZDTMsg[tMsgArray.getValidCount()]);
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                EZDMsg.copy(tMsgArray.no(i), null, shpgPlnMsgArray.get(i), null);
            }

        } else if (MODE_SHIPPINGREQUEST.equals(param.shpgModeCd.getValue())) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("glblCmpyCd", param.glblCmpyCd.getValue());
            map.put("trxHdrNum", param.trxHdrNum.getValue());
            map.put("trxLineNum", param.trxLineNum.getValue());
            map.put("trxLineNumSet", SET_LINE_SUB_NUM);
            map.put("trxSrcTpCd", param.trxSrcTpCd.getValue());
            List<String> shpgStsList = new ArrayList<String>(2);
            shpgStsList.add(SHPG_STS.VALIDATED);
            shpgStsList.add(SHPG_STS.HARD_ALLOCATED);
            map.put("shpgStsList", shpgStsList);

            List<String> setShpgPlnNumList = (List) this.ssmBatchClient.queryObjectList("querySetShpgPln", map);
            List<SHPG_PLNTMsg> list = null;

            if (setShpgPlnNumList == null || setShpgPlnNumList.isEmpty()) {

                SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
                tMsg.setSQLID("006");
                tMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
                tMsg.setConditionValue("trxHdrNum01", param.trxHdrNum.getValue());
                tMsg.setConditionValue("trxLineNum01", param.trxLineNum.getValue());
                tMsg.setConditionValue("trxLineSubNum01", param.trxLineSubNum.getValue());
                tMsg.setConditionValue("trxSrcTpCd01", param.trxSrcTpCd.getValue());
                tMsg.setConditionValue("shpgStsCd01", SHPG_STS.HARD_ALLOCATED);
                SHPG_PLNTMsgArray tMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
                if (tMsgArray.getValidCount() == 0) {
                    msgMap.addXxMsgId(NWZM0075E);
                    return;
                }

                list = new ArrayList<SHPG_PLNTMsg>(tMsgArray.getValidCount());
                for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                    list.add(tMsgArray.no(i));
                }

            } else {

                for (String setShpgPlnNum : setShpgPlnNumList) {

                    Map<String, Object> mapComp = new HashMap<String, Object>();
                    mapComp.put("glblCmpyCd", param.glblCmpyCd.getValue());
                    mapComp.put("trxHdrNum", param.trxHdrNum.getValue());
                    mapComp.put("trxLineNum", param.trxLineNum.getValue());
                    mapComp.put("trxLineSubNum", param.trxLineSubNum.getValue());
                    mapComp.put("trxSrcTpCd", param.trxSrcTpCd.getValue());
                    mapComp.put("shpgStsCdHA", SHPG_STS.HARD_ALLOCATED);
                    mapComp.put("setSHpgPlnNum", setShpgPlnNum);

                    List<String> compShpgPlnList = (List) this.ssmBatchClient.queryObjectList("queryCompShpgPln", mapComp);

                    list = new ArrayList<SHPG_PLNTMsg>(compShpgPlnList.size());
                    for (String compShpgPlnNum : compShpgPlnList) {
                        SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
                        tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                        tMsg.shpgPlnNum.setValue(compShpgPlnNum);
                        tMsg = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(tMsg);

                        list.add(tMsg);

                    }
                }
            }

            shpgPlnMsgArray.setValidCount(list.size());
            shpgPlnMsgArray.setMsgList(new EZDTMsg[list.size()]);
            for (int i = 0; i < list.size(); i++) {
                EZDMsg.copy(list.get(i), null, shpgPlnMsgArray.get(i), null);
            }
        }
        debug("-- >>>> [searchShippingPlanForUpdate] END --");
    }

    private SHPG_PLNTMsg searchShippingPlanForUpdateSetItem(SHPG_PLNTMsg shpgPlnMsg) {

        SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", shpgPlnMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("trxHdrNum01", shpgPlnMsg.trxHdrNum.getValue());
        tMsg.setConditionValue("trxLineNum01", shpgPlnMsg.trxLineNum.getValue());
        tMsg.setConditionValue("trxLineSubNum01", "000");

        SHPG_PLNTMsgArray tMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);

        if (tMsgArray.getValidCount() == 0) {
            return null;
        }

        return tMsgArray.no(tMsgArray.getValidCount() - 1);
    }

    private void searchCPODetailForUpdate(SHPG_PLNTMsg shpgPlnMsg, CPO_DTLTMsg cpoDtlMsg, S21ApiMessageMap msgMap) {
        debug("-- >>>> [searchCPODetailForUpdate] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (ZYPConstant.FLG_OFF_N.equals(getCPOExistenceFlagKeyTransactionSourceTypeCode(param.glblCmpyCd.getValue(), shpgPlnMsg.trxSrcTpCd.getValue()))) {
            return;
        }

        CPO_DTLTMsg tMsg = new CPO_DTLTMsg();
        tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        tMsg.cpoOrdNum.setValue(shpgPlnMsg.trxHdrNum.getValue());
        tMsg.cpoDtlLineNum.setValue(shpgPlnMsg.trxLineNum.getValue());
        tMsg.cpoDtlLineSubNum.setValue(shpgPlnMsg.trxLineSubNum.getValue());
        tMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
        if (null == tMsg) {
            msgMap.addXxMsgId(NWZM0074E);
            return;
        }
        EZDMsg.copy(tMsg, null, cpoDtlMsg, null);
        debug("-- >>>> [searchCPODetailForUpdate] END --");
    }

    private void searchCPODetail(SHPG_PLNTMsg shpgPlnMsg, CPO_DTLTMsg cpoDtlMsg, S21ApiMessageMap msgMap) {
        debug("-- >>>> [searchCPODetail] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        CPO_DTLTMsg tMsg = new CPO_DTLTMsg();
        tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        tMsg.cpoOrdNum.setValue(shpgPlnMsg.trxHdrNum.getValue());
        tMsg.cpoDtlLineNum.setValue(shpgPlnMsg.trxLineNum.getValue());
        tMsg.cpoDtlLineSubNum.setValue(shpgPlnMsg.trxLineSubNum.getValue());
        tMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (null == tMsg) {
            msgMap.addXxMsgId(NWZM0074E);
            return;
        }
        EZDMsg.copy(tMsg, null, cpoDtlMsg, null);
        debug("-- >>>> [searchCPODetail] END --");
    }

    private CPO_DTLTMsg searchCPODetailForUpdateSetItem(SHPG_PLNTMsg shpgPlnMsg, S21ApiMessageMap msgMap) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        CPO_DTLTMsg tMsg = new CPO_DTLTMsg();
        tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        tMsg.cpoOrdNum.setValue(shpgPlnMsg.trxHdrNum.getValue());
        tMsg.cpoDtlLineNum.setValue(shpgPlnMsg.trxLineNum.getValue());
        tMsg.cpoDtlLineSubNum.setValue("000");

        return (CPO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
    }

    private void searchCPOForUpdate(SHPG_PLNTMsg shpgPlnMsg, CPOTMsg cpoMsg, S21ApiMessageMap msgMap) {

        debug("-- >>>> [searchCPOForUpdate] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        CPOTMsg tMsg = new CPOTMsg();
        tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        tMsg.cpoOrdNum.setValue(shpgPlnMsg.trxHdrNum.getValue());

        tMsg = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);

        if (null == tMsg) {
            msgMap.addXxMsgId(NWZM0074E);
            return;
        }

        EZDMsg.copy(tMsg, null, cpoMsg, null);

        debug("-- >>>> [searchCPOForUpdate] END --");
    }

    private void chkStatus(SHPG_PLNTMsg shpgPlnMsg, S21ApiMessageMap msgMap) {
        debug("-- >>>> [chkStatus] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        String shpgStsCd = shpgPlnMsg.shpgStsCd.getValue();
        String rteStsCd = shpgPlnMsg.rteStsCd.getValue();
        String soCloseFlg = shpgPlnMsg.soCloseFlg.getValue();

        if (MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.S_OR_O_PRINTED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.PICKED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.PACKED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.STAGED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_HARDALLOCATED_ROUTED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd) && RTE_STS.ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd) && RTE_STS.ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_PRINTED_SOCREATED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.S_OR_O_PRINTED.equals(shpgStsCd) && RTE_STS.SO_CREATING.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_POPRINTED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.VALIDATED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_PICKED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.S_OR_O_PRINTED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_PACKED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.S_OR_O_PRINTED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.PICKED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_STAGED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.S_OR_O_PRINTED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.PICKED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.PACKED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_INSHED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.S_OR_O_PRINTED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.PICKED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.PACKED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.STAGED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue()) // 
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { // 2016/06/20 S21_NA#594 Add

            if (SHPG_STS.S_OR_O_PRINTED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.PICKED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.PACKED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.STAGED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.N_INVOICE_READY.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.N_INVOICE_READY.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.SHIPPED.equals(shpgStsCd)) {
                return;
            }
        } else if (MODE_SHIPPED_SOCLOSE_AINVOICE.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.VALIDATED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.SHIPPED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.SHIPPED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.SHIPPED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.SHIPPED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSHED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSHED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSHED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSHED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_SHIPPED_SOCLOSE.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.SHIPPED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.ARRIVED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.ARRIVED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSHED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            // 2016/06/29 S21_NA#7189 Add Start
            if (SHPG_STS.INSTALLED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSTALLED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSTALLED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSTALLED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INVOICED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INVOICED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INVOICED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INVOICED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            // 2016/06/29 S21_NA#7189 Add End
        } else if (MODE_ARRIVED_SOCLOSE.equals(param.shpgModeCd.getValue())
                /* 07/11/2016 CSAI Y.Imazu Add QC#10917 START */
                || MODE_ARRIVED_SHPG_PLN.equals(param.shpgModeCd.getValue())) {
                /* 07/11/2016 CSAI Y.Imazu Add QC#10917 END */

            if (SHPG_STS.SHIPPED.equals(shpgStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.SHIPPED.equals(shpgStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSHED.equals(shpgStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSHED.equals(shpgStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INVOICED.equals(shpgStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INVOICED.equals(shpgStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            // 2016/05/31 S21_NA#4321 Add Start
            if (SHPG_STS.ARRIVED.equals(shpgStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.ARRIVED.equals(shpgStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSTALLED.equals(shpgStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.INSTALLED.equals(shpgStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            // 2016/05/31 S21_NA#4321 Add Start
        } else if (MODE_POCANCELLED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.P_OR_O_PRINTED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_SHIPPINGREQUEST.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.P_OR_O_PRINTED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
        } else if (MODE_FORCEDSHIPMENT.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
            if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd) && RTE_STS.ROUTED.equals(rteStsCd) && ZYPConstant.FLG_OFF_N.equals(soCloseFlg)) {
                return;
            }
// START ADD 2013/04/22 S.Yamamoto [094]
        } else if (MODE_INSTALLED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.SHIPPED.equals(shpgStsCd) && RTE_STS.SO_CREATED.equals(rteStsCd) && ZYPConstant.FLG_ON_Y.equals(soCloseFlg)) {
                return;
            }
// END   ADD 2013/04/22 S.Yamamoto [094]
        }

        msgMap.addXxMsgId(NWZM0083E);
        debug("-- >>>> [chkStatus] END --");
    }

    private void updateShpgPln(SHPG_PLNTMsg updShpgPlnMsg, SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, int index, ONBATCH_TYPE onBatchType) {
        debug("-- >>>> [updateShpgPln] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        String shpgStsCdBf = updShpgPlnMsg.shpgStsCd.getValue();

// START MOD 2013/04/22 S.Yamamoto [094]
        if (!MODE_PSD_PDD_RSD_RDD_UPDATE.equals(param.shpgModeCd.getValue())) {
// END   MOD 2013/04/22 S.Yamamoto [094]
            if (ZYPConstant.FLG_ON_Y.equals(spUpdModeMsg.shpgStsUpdFlg.getValue())) {
                // 2016/05/31 S21_NA#4321 Add Start
                boolean soCloseAndInvoiced = MODE_ARRIVED_SOCLOSE.equals(param.shpgModeCd.getValue()) && SHPG_STS.INVOICED.equals(updShpgPlnMsg.shpgStsCd.getValue());
                boolean soCloseAndInstalled = MODE_ARRIVED_SOCLOSE.equals(param.shpgModeCd.getValue()) && SHPG_STS.INSTALLED.equals(updShpgPlnMsg.shpgStsCd.getValue());
                boolean soCloseAndArrived = MODE_ARRIVED_SOCLOSE.equals(param.shpgModeCd.getValue()) && SHPG_STS.ARRIVED.equals(updShpgPlnMsg.shpgStsCd.getValue());
                // 2016/05/31 S21_NA#4321 Add Start
                /* 07/11/2016 CSAI Y.Imazu Add QC#10917 START */
                if (MODE_ARRIVED_SHPG_PLN.equals(param.shpgModeCd.getValue())) {
                    soCloseAndInvoiced = SHPG_STS.INVOICED.equals(updShpgPlnMsg.shpgStsCd.getValue());
                    soCloseAndInstalled = SHPG_STS.INSTALLED.equals(updShpgPlnMsg.shpgStsCd.getValue());
                    soCloseAndArrived = SHPG_STS.ARRIVED.equals(updShpgPlnMsg.shpgStsCd.getValue());
                }
                /* 07/11/2016 CSAI Y.Imazu Add QC#10917 END */
                // if (!(MODE_ARRIVED_SOCLOSE.equals(param.shpgModeCd.getValue()) && SHPG_STS.INVOICED.equals(updShpgPlnMsg.shpgStsCd.getValue()))) { 2016/05/31 S21_NA#4321 Mod Condition
                if (!soCloseAndInvoiced && !soCloseAndInstalled && !soCloseAndArrived) {
                    updShpgPlnMsg.shpgStsCd.setValue(spUpdModeMsg.shpgStsCd.getValue());
                }
            }
            if (ZYPConstant.FLG_ON_Y.equals(spUpdModeMsg.rteStsUpdFlg.getValue())) {
                updShpgPlnMsg.rteStsCd.setValue(spUpdModeMsg.rteStsCd.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals(spUpdModeMsg.soCloUpdFlg.getValue())) {
                updShpgPlnMsg.soCloseFlg.setValue(spUpdModeMsg.soCloFlg.getValue());
            }
        }

        if (MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue())) {
            CPO_DTLTMsg cpoDtlMsg = new CPO_DTLTMsg();
            searchCPODetailForUpdate(updShpgPlnMsg, cpoDtlMsg, msgMap);
            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // exists order data.
            if (ZYPCommonFunc.hasValue(cpoDtlMsg.cpoOrdNum)) {
                if (ZYPCommonFunc.hasValue(cpoDtlMsg.rddDt)) {
                    updShpgPlnMsg.rddDt.setValue(cpoDtlMsg.rddDt.getValue());
                } else {
                    updShpgPlnMsg.rddDt.clear();
                }
                if (ZYPCommonFunc.hasValue(cpoDtlMsg.rsdDt)) {
                    updShpgPlnMsg.rsdDt.setValue(cpoDtlMsg.rsdDt.getValue());
                } else {
                    updShpgPlnMsg.rsdDt.clear();
                }
            }

            updShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
            updShpgPlnMsg.psdDt.clear();
            updShpgPlnMsg.pddDt.clear();

        } else if (MODE_HARDALLOCATED_ROUTED.equals(param.shpgModeCd.getValue())) {
            updShpgPlnMsg.psdDt.setValue(param.psdDt.getValue());
            if (ZYPCommonFunc.hasValue(param.pddDt)) {
                updShpgPlnMsg.pddDt.setValue(param.pddDt.getValue());
            } else {
                updShpgPlnMsg.pddDt.clear();
            }
            if (ZYPCommonFunc.hasValue(param.shpgSvcLvlCd)) {
                updShpgPlnMsg.reqShpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());
            }

        } else if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
            updShpgPlnMsg.psdDt.setValue(param.psdDt.getValue());
            if (ZYPCommonFunc.hasValue(param.pddDt)) {
                updShpgPlnMsg.pddDt.setValue(param.pddDt.getValue());
            } else {
                updShpgPlnMsg.pddDt.clear();
            }
            if (ZYPCommonFunc.hasValue(param.shpgSvcLvlCd)) {
                updShpgPlnMsg.reqShpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());
            }

        } else if (MODE_PRINTED_SOCREATED.equals(param.shpgModeCd.getValue())) {
            updShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
            updShpgPlnMsg.soNum.setValue(param.soNum.getValue());
            if (ZYPCommonFunc.hasValue(param.soSlpNum)) {
                updShpgPlnMsg.soSlpNum.setValue(param.soSlpNum.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.psdDt)) {
                updShpgPlnMsg.psdDt.setValue(param.psdDt.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.pddDt)) {
                updShpgPlnMsg.pddDt.setValue(param.pddDt.getValue());
            }
            if (!ZYPCommonFunc.hasValue(param.psdDt)) {
                updShpgPlnMsg.pddDt.clear();
            }
            if (ZYPCommonFunc.hasValue(param.shpgSvcLvlCd)) {
                updShpgPlnMsg.reqShpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());
            }

            if (ZYPConstant.FLG_OFF_N.equals(updShpgPlnMsg.dropShipFlg.getValue())) {
                SHIP_TO_CUSTTMsg shipToCustMsg = getShipToCustData(param, updShpgPlnMsg.shipToCustCd.getValue());
                copyShipToCustData(param, updShpgPlnMsg, shipToCustMsg);
            }

        } else if (MODE_POPRINTED.equals(param.shpgModeCd.getValue())) {
            if (updShpgPlnMsg.avalPoQty.getValue().compareTo(updShpgPlnMsg.ordQty.getValue()) != 0) {
                return;
            }
            updShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
            updShpgPlnMsg.poOrdNum.setValue(param.poOrdNum.getValue());
            // 2018/11/19 S21_NA#29173 Add Start
            if (ZYPCommonFunc.hasValue(param.invtyLocCd.getValue())) {
                updShpgPlnMsg.invtyLocCd.setValue(param.invtyLocCd.getValue());
            }
            // 2018/11/19 S21_NA#29173 Add End

            if (ZYPConstant.FLG_OFF_N.equals(updShpgPlnMsg.dropShipFlg.getValue())) {
                SHIP_TO_CUSTTMsg shipToCustMsg = getShipToCustData(param, updShpgPlnMsg.shipToCustCd.getValue());
                copyShipToCustData(param, updShpgPlnMsg, shipToCustMsg);
            }

        } else if (MODE_PICKED.equals(param.shpgModeCd.getValue())) {

            if (ZYPCommonFunc.hasValue(param.psdDt)) {
                updShpgPlnMsg.psdDt.setValue(param.psdDt.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.pddDt)) {
                updShpgPlnMsg.pddDt.setValue(param.pddDt.getValue());
            }

        } else if (MODE_PACKED.equals(param.shpgModeCd.getValue())) {

            if (ZYPCommonFunc.hasValue(param.psdDt)) {
                updShpgPlnMsg.psdDt.setValue(param.psdDt.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.pddDt)) {
                updShpgPlnMsg.pddDt.setValue(param.pddDt.getValue());
            }

        } else if (MODE_STAGED.equals(param.shpgModeCd.getValue())) {

            if (ZYPCommonFunc.hasValue(param.psdDt)) {
                updShpgPlnMsg.psdDt.setValue(param.psdDt.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.pddDt)) {
                updShpgPlnMsg.pddDt.setValue(param.pddDt.getValue());
            }

        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.SHIPPED.equals(shpgStsCdBf)) {

                // START ADD S.Yamamoto [OM053]
                if (isUpdateFreightAmt(updShpgPlnMsg)) {
                // END   ADD S.Yamamoto [OM053]

                    BigDecimal calSpTotFuncFrtAmt = updShpgPlnMsg.spTotFuncFrtAmt.getValue().add(param.spTotFuncFrtAmt.getValue());
                    updShpgPlnMsg.spTotFuncFrtAmt.setValue(calSpTotFuncFrtAmt);

                    setValue(updShpgPlnMsg.spTotDealFrtAmt, calcDealFrtAmt(updShpgPlnMsg));
                }

            } else {

                Map<String, Object> soNumInfoList = getSoNumInfo(updShpgPlnMsg.glblCmpyCd.getValue(), updShpgPlnMsg.soNum.getValue(), updShpgPlnMsg.shpgPlnNum.getValue());

                String bolNum = null;
                String proNum = null;

                if (soNumInfoList != null) {
                    // S21_NA#27939 Add
                    if (ZYPCommonFunc.hasValue(param.bolNum) && !param.bolNum.getValue().equals((String) soNumInfoList.get("BOL_NUM"))) {
                        bolNum = param.bolNum.getValue();
                    } else {
                        bolNum = (String) soNumInfoList.get("BOL_NUM");
                    }
                    if (ZYPCommonFunc.hasValue(param.proNum) && !param.proNum.getValue().equals((String) soNumInfoList.get("PRO_NUM"))) {
                        proNum = param.proNum.getValue();
                    } else {
                        proNum = (String) soNumInfoList.get("PRO_NUM");
                    }
                    // S21_NA#27939 End
                } else {
                    bolNum = param.bolNum.getValue();
                    proNum = param.proNum.getValue();
                }

                String carrNm = null;
                if (ZYPCommonFunc.hasValue(param.carrCd) && !ZYPCommonFunc.hasValue(param.carrNm)) {
                    carrNm = getCarrierName(param);
                }

                // START ADD S.Yamamoto [OM053]
                if (isUpdateFreightAmt(updShpgPlnMsg)) {
                // END   ADD S.Yamamoto [OM053]
                    updShpgPlnMsg.spTotFuncFrtAmt.setValue(param.spTotFuncFrtAmt.getValue());

                    setValue(updShpgPlnMsg.spTotDealFrtAmt, calcDealFrtAmt(updShpgPlnMsg));
                }

                updShpgPlnMsg.actlShipDt.setValue(param.actlShipDt.getValue());
                if (ZYPCommonFunc.hasValue(param.actlShpgSvcLvlCd)) {
                    updShpgPlnMsg.actlShpgSvcLvlCd.setValue(param.actlShpgSvcLvlCd.getValue());
                }
                if (ZYPCommonFunc.hasValue(param.actlFrtChrgToCd)) {
                    updShpgPlnMsg.actlFrtChrgToCd.setValue(param.actlFrtChrgToCd.getValue());
                }
                if (ZYPCommonFunc.hasValue(param.actlFrtChrgMethCd)) {
                    updShpgPlnMsg.actlFrtChrgMethCd.setValue(param.actlFrtChrgMethCd.getValue());
                }
                if (ZYPCommonFunc.hasValue(param.carrCd)) {
                    updShpgPlnMsg.carrCd.setValue(param.carrCd.getValue());
                }
                if (ZYPCommonFunc.hasValue(param.carrNm)) {
                    updShpgPlnMsg.carrNm.setValue(param.carrNm.getValue());
                }
                if (ZYPCommonFunc.hasValue(carrNm)) {
                    updShpgPlnMsg.carrNm.setValue(carrNm);
                }
                if (ZYPCommonFunc.hasValue(bolNum)) {
                    updShpgPlnMsg.bolNum.setValue(bolNum);
                }
                if (ZYPCommonFunc.hasValue(proNum)) {
                    updShpgPlnMsg.proNum.setValue(proNum);
                }
            }

        } else if (MODE_SHIPPED_SOCLOSE_AINVOICE.equals(param.shpgModeCd.getValue())) {
            updShpgPlnMsg.bolNum.setValue(param.bolNum.getValue());
            updShpgPlnMsg.actlShipDt.setValue(param.bolDt.getValue());
            if (ZYPCommonFunc.hasValue(param.actlArvDt)) {
                updShpgPlnMsg.actlArvDt.setValue(param.actlArvDt.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.etaDt)) {
                updShpgPlnMsg.etaDt.setValue(param.etaDt.getValue());
            }

        } else if (MODE_ARRIVED_SOCLOSE.equals(param.shpgModeCd.getValue())) {
            updShpgPlnMsg.actlArvDt.setValue(param.actlArvDt.getValue());

        } else if (MODE_SHIPPINGREQUEST.equals(param.shpgModeCd.getValue())) {

            if (updShpgPlnMsg.avalSoQty.getValue().compareTo(BigDecimal.ZERO) > 0) {
                updShpgPlnMsg.rteStsCd.setValue(RTE_STS.UN_ROUTED);
                if (ZYPCommonFunc.hasValue(param.shipCmntFirstLineTxt)) {
                    updShpgPlnMsg.shipCmntFirstLineTxt.setValue(param.shipCmntFirstLineTxt.getValue());
                }
                if (ZYPCommonFunc.hasValue(param.shipCmntScdLineTxt)) {
                    updShpgPlnMsg.shipCmntScdLineTxt.setValue(param.shipCmntScdLineTxt.getValue());
                }
                if (ZYPCommonFunc.hasValue(param.shipCmntThirdLineTxt)) {
                    updShpgPlnMsg.shipCmntThirdLineTxt.setValue(param.shipCmntThirdLineTxt.getValue());
                }
                if (ZYPCommonFunc.hasValue(param.shipCmntFrthLineTxt)) {
                    updShpgPlnMsg.shipCmntFrthLineTxt.setValue(param.shipCmntFrthLineTxt.getValue());
                }
            }

        } else if (MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue())) {
            if (updShpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) != 0) {
                return;
            }
            updShpgPlnMsg.thirdPtyInvNum.setValue(param.invNum.getValue());
            if (ZYPCommonFunc.hasValue(param.actlShipDt)) {
                updShpgPlnMsg.actlShipDt.setValue(param.actlShipDt.getValue());
            }

            if (ZYPCommonFunc.hasValue(param.spTotFuncFrtAmt)) {

                // START ADD S.Yamamoto [OM053]
                if (isUpdateFreightAmt(updShpgPlnMsg)) {
                // END   ADD S.Yamamoto [OM053]
                    updShpgPlnMsg.spTotFuncFrtAmt.setValue(param.spTotFuncFrtAmt.getValue());

                    setValue(updShpgPlnMsg.spTotDealFrtAmt, calcDealFrtAmt(updShpgPlnMsg));
                }
            }
            if (ZYPCommonFunc.hasValue(param.bolNum)) {
                updShpgPlnMsg.bolNum.setValue(param.bolNum.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.carrNm)) {
                updShpgPlnMsg.carrNm.setValue(param.carrNm.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.proNum)) {
                updShpgPlnMsg.proNum.setValue(param.proNum.getValue());
            }
            // 2019/10/03 QC#53186 Add Start
            if (ZYPCommonFunc.hasValue(param.carrCd)) {
                updShpgPlnMsg.carrCd.setValue(param.carrCd.getValue());
            }
            // 2019/10/03 QC#53186 Add End

        } else if (MODE_FORCEDSHIPMENT.equals(param.shpgModeCd.getValue())) {
            updShpgPlnMsg.reqShpgSvcLvlCd.setValue(updShpgPlnMsg.origShpgSvcLvlCd.getValue());
            updShpgPlnMsg.psdDt.clear();
            updShpgPlnMsg.pddDt.clear();

// START MOD 2013/04/22 S.Yamamoto [094]
        } else if (MODE_PSD_PDD_RSD_RDD_UPDATE.equals(param.shpgModeCd.getValue())) {
            // updShpgPlnMsg.psdDt.setValue(param.psdDt.getValue());
            if (ZYPCommonFunc.hasValue(param.psdDt)) {
                updShpgPlnMsg.psdDt.setValue(param.psdDt.getValue());
            }
// END   MOD 2013/04/22 S.Yamamoto [094]
            if (ZYPCommonFunc.hasValue(param.pddDt)) {
                updShpgPlnMsg.pddDt.setValue(param.pddDt.getValue());
            }
// START ADD 2013/04/22 S.Yamamoto [094]
            if (ZYPCommonFunc.hasValue(param.rsdDt)) {
                updShpgPlnMsg.rsdDt.setValue(param.rsdDt.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.rddDt)) {
                updShpgPlnMsg.rddDt.setValue(param.rddDt.getValue());
            }
// END   ADD 2013/04/22 S.Yamamoto [094]
        /* 07/11/2016 CSAI Y.Imazu Add QC#10917 START */
        } else if (MODE_ARRIVED_SHPG_PLN.equals(param.shpgModeCd.getValue())) {
            updShpgPlnMsg.actlArvDt.setValue(param.actlArvDt.getValue());
            if (ZYPCommonFunc.hasValue(param.proNum)) {
                updShpgPlnMsg.proNum.setValue(param.proNum.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.carrCd)) {
                updShpgPlnMsg.carrCd.setValue(param.carrCd.getValue());
            }
            /* 07/11/2016 CSAI Y.Imazu Add QC#10917 END */
        }

        // 2023/06/02 QC#61383 Add Start
        insertForCustNtfyShipInfo(msgMap, shpgStsCdBf, updShpgPlnMsg, onBatchType);
        // 2023/06/02 QC#61383 Add End

        S21ApiTBLAccessor.update(updShpgPlnMsg);
        if (!RTNCD_NOMAL.equals(updShpgPlnMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0078E);
        }

        // call Display Order Status Update API
        List<String> shpgPlnNumList = new ArrayList<String>();
        shpgPlnNumList.add(updShpgPlnMsg.shpgPlnNum.getValue());
        callDplyOrdStsUpdApi(msgMap, shpgPlnNumList, onBatchType, updShpgPlnMsg.trxHdrNum.getValue());

        debug("-- >>>> [updateShpgPln] END --");
    }

    private void updateShpgPlnShipDataSetItem(SHPG_PLNTMsg shpgPlnMsg, S21ApiMessageMap msgMap) {

        debug("-- >>>> [updateShpgPlnShipDataSetItem] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        copyShipToCustData(param, shpgPlnMsg, getShipToCustData(param, shpgPlnMsg.shipToCustCd.getValue()));

        S21ApiTBLAccessor.update(shpgPlnMsg);
        if (!RTNCD_NOMAL.equals(shpgPlnMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0081E);
            return;
        }

        debug("-- >>>> [updateShpgPlnShipDataSetItem] END --");
    }

    // 2018/08/09 S21_NA#26865 Mod Start
//    private void dividesShippingplan(SHPG_PLNTMsg divShpgPlnMsg, SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap) {
    private void dividesShippingplan(SHPG_PLNTMsg divShpgPlnMsg, SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
    // 2018/08/09 S21_NA#26865 Mod End
        debug("-- >>>> [dividesShippingplan] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
            if (divShpgPlnMsg.avalSoQty.getValue().compareTo(divShpgPlnMsg.ordQty.getValue()) == 0) {
                return;
            }

            BigDecimal bkQty = divShpgPlnMsg.ordQty.getValue().subtract(divShpgPlnMsg.avalSoQty.getValue());

            divShpgPlnMsg.rteStsCd.setValue(RTE_STS.UN_ROUTED);
            divShpgPlnMsg.shpgPlnNum.setValue(ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqConstant.SHPG_PLN_SQ, 8));
            divShpgPlnMsg.ordQty.setValue(bkQty);
            if (BigDecimal.ZERO.compareTo(divShpgPlnMsg.crChkQty.getValue()) == -1) {
                divShpgPlnMsg.crChkQty.setValue(divShpgPlnMsg.crChkQty.getValue().subtract(divShpgPlnMsg.avalSoQty.getValue()));
            }
            divShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
            divShpgPlnMsg.spTotDealSlsAmt.setValue(divShpgPlnMsg.spDealUnitPrcAmt.getValue().multiply(bkQty));
            divShpgPlnMsg.spTotDealNetAmt.setValue(divShpgPlnMsg.spDealNetUnitPrcAmt.getValue().multiply(bkQty));
            divShpgPlnMsg.spTotFuncSlsAmt.setValue(divShpgPlnMsg.spFuncUnitPrcAmt.getValue().multiply(bkQty));
            divShpgPlnMsg.spTotFuncNetAmt.setValue(divShpgPlnMsg.spFuncNetUnitPrcAmt.getValue().multiply(bkQty));
            divShpgPlnMsg.psdDt.clear();
            divShpgPlnMsg.pddDt.clear();

        } else if (MODE_POPRINTED.equals(param.shpgModeCd.getValue())) {
            if (divShpgPlnMsg.avalPoQty.getValue().compareTo(divShpgPlnMsg.ordQty.getValue()) == 0) {
                return;
            }

            BigDecimal bkQty = divShpgPlnMsg.ordQty.getValue().subtract(divShpgPlnMsg.avalPoQty.getValue());

            divShpgPlnMsg.shpgPlnNum.setValue(ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqConstant.SHPG_PLN_SQ, 8));
            divShpgPlnMsg.ordQty.setValue(bkQty);
            if (BigDecimal.ZERO.compareTo(divShpgPlnMsg.crChkQty.getValue()) == -1) {
                divShpgPlnMsg.crChkQty.setValue(divShpgPlnMsg.crChkQty.getValue().subtract(divShpgPlnMsg.avalPoQty.getValue()));
            }
            divShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
            divShpgPlnMsg.spTotDealSlsAmt.setValue(divShpgPlnMsg.spDealUnitPrcAmt.getValue().multiply(bkQty));
            divShpgPlnMsg.spTotDealNetAmt.setValue(divShpgPlnMsg.spDealNetUnitPrcAmt.getValue().multiply(bkQty));
            divShpgPlnMsg.spTotFuncSlsAmt.setValue(divShpgPlnMsg.spFuncUnitPrcAmt.getValue().multiply(bkQty));
            divShpgPlnMsg.spTotFuncNetAmt.setValue(divShpgPlnMsg.spFuncNetUnitPrcAmt.getValue().multiply(bkQty));

        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue()) // 
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { // 2016/06/20 S21_NA#594 Add
            if (divShpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) != 1) {
                return;
            }
            String carrNm = null;
            if (ZYPCommonFunc.hasValue(param.carrCd) && !ZYPCommonFunc.hasValue(param.carrNm)) {
                carrNm = getCarrierName(param);
            }

            // START ADD S.Yamamoto [OM053]
            boolean isUpdateFreightAmt = isUpdateFreightAmt(divShpgPlnMsg);
            // END   ADD S.Yamamoto [OM053]

            // 2016/07/26 S21_NA#594-2 Modd Start
            // divShpgPlnMsg.shpgPlnNum.setValue(ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqConstant.SHPG_PLN_SQ, 8));
            if (!MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
                divShpgPlnMsg.shpgPlnNum.setValue(ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqConstant.SHPG_PLN_SQ, 8));
            }
            // 2016/07/26 S21_NA#594-2 Modd Start
            divShpgPlnMsg.shpgStsCd.setValue(spUpdModeMsg.shpgStsCd.getValue());
            // 2016/07/26 S21_NA#594-2 Add Start
//            divShpgPlnMsg.shpgPlnDplyLineNum.setValue(getMaxDisplayNumber(param, divShpgPlnMsg));
            if (!MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
                divShpgPlnMsg.shpgPlnDplyLineNum.setValue(getMaxDisplayNumber(param, divShpgPlnMsg));
            }
            // 2016/07/26 S21_NA#594-2 Add End
            divShpgPlnMsg.ordQty.setValue(param.ordQty.getValue());
            divShpgPlnMsg.crChkQty.setValue(param.ordQty.getValue());
            if (ZYPCommonFunc.hasValue(param.actlShpgSvcLvlCd)) {
                divShpgPlnMsg.actlShpgSvcLvlCd.setValue(param.actlShpgSvcLvlCd.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.actlFrtChrgToCd)) {
                divShpgPlnMsg.actlFrtChrgToCd.setValue(param.actlFrtChrgToCd.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.actlFrtChrgMethCd)) {
                divShpgPlnMsg.actlFrtChrgMethCd.setValue(param.actlFrtChrgMethCd.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.carrCd)) {
                divShpgPlnMsg.carrCd.setValue(param.carrCd.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.carrNm)) {
                divShpgPlnMsg.carrNm.setValue(param.carrNm.getValue());
            }
            if (ZYPCommonFunc.hasValue(carrNm)) {
                divShpgPlnMsg.carrNm.setValue(carrNm);
            }
            // 2016/12/06 S21_NA#16293 Mod Start
//            divShpgPlnMsg.bolNum.setValue(param.bolNum.getValue());
            if (ZYPCommonFunc.hasValue(param.bolNum)) {
                divShpgPlnMsg.bolNum.setValue(param.bolNum.getValue());
            }
            // 2016/12/06 S21_NA#16293 Mod End
            if (ZYPCommonFunc.hasValue(param.proNum)) {
                divShpgPlnMsg.proNum.setValue(param.proNum.getValue());
            }
            // START ADD S.Yamamoto [OM053]
            if (isUpdateFreightAmt) {
            // END   ADD S.Yamamoto [OM053]

                divShpgPlnMsg.spTotFuncFrtAmt.setValue(param.spTotFuncFrtAmt.getValue());
                setValue(divShpgPlnMsg.spTotDealFrtAmt, calcDealFrtAmt(divShpgPlnMsg));
            }
            divShpgPlnMsg.actlShipDt.setValue(param.actlShipDt.getValue());
            divShpgPlnMsg.spTotDealSlsAmt.setValue(divShpgPlnMsg.spDealUnitPrcAmt.getValue().multiply(param.ordQty.getValue()));
            divShpgPlnMsg.spTotDealNetAmt.setValue(divShpgPlnMsg.spDealNetUnitPrcAmt.getValue().multiply(param.ordQty.getValue()));
            divShpgPlnMsg.spTotFuncSlsAmt.setValue(divShpgPlnMsg.spFuncUnitPrcAmt.getValue().multiply(param.ordQty.getValue()));
            divShpgPlnMsg.spTotFuncNetAmt.setValue(divShpgPlnMsg.spFuncNetUnitPrcAmt.getValue().multiply(param.ordQty.getValue()));

            // 2016/06/20 S21_NA#594 Add Start
            MDSE_STORE_PKGTMsg mdseStorePkgTMsg = getStorePkg(param.glblCmpyCd.getValue(), divShpgPlnMsg);
            BigDecimal inEachQty = BigDecimal.ONE;
            if (null != mdseStorePkgTMsg && ZYPCommonFunc.hasValue(mdseStorePkgTMsg.inEachQty)) {
                inEachQty = mdseStorePkgTMsg.inEachQty.getValue();
            }
            divShpgPlnMsg.ordUomQty.setValue(getOrdUomQty(param.ordQty.getValue(), inEachQty));
            // 2016/06/20 S21_NA#594 Add End

        } else if (MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue())) {
            if (divShpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) != 1) {
                return;
            }

            // START ADD S.Yamamoto [OM053]
            boolean isUpdateFreightAmt = isUpdateFreightAmt(divShpgPlnMsg);
            // END   ADD S.Yamamoto [OM053]

            divShpgPlnMsg.shpgPlnNum.setValue(ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqConstant.SHPG_PLN_SQ, 8));
            divShpgPlnMsg.shpgStsCd.setValue(spUpdModeMsg.shpgStsCd.getValue());
            divShpgPlnMsg.shpgPlnDplyLineNum.setValue(getMaxDisplayNumber(param, divShpgPlnMsg));
            divShpgPlnMsg.ordQty.setValue(param.ordQty.getValue());
            divShpgPlnMsg.crChkQty.setValue(param.ordQty.getValue());
            if (ZYPCommonFunc.hasValue(param.actlShipDt)) {
                divShpgPlnMsg.actlShipDt.setValue(param.actlShipDt.getValue());
            }
            divShpgPlnMsg.thirdPtyInvNum.setValue(param.invNum.getValue());
            divShpgPlnMsg.soCloseFlg.setValue(spUpdModeMsg.soCloFlg.getValue());

            if (ZYPCommonFunc.hasValue(param.spTotFuncFrtAmt)) {
                // START ADD S.Yamamoto [OM053]
                if (isUpdateFreightAmt) {
                // END   ADD S.Yamamoto [OM053]

                    divShpgPlnMsg.spTotFuncFrtAmt.setValue(param.spTotFuncFrtAmt.getValue());
                    setValue(divShpgPlnMsg.spTotDealFrtAmt, calcDealFrtAmt(divShpgPlnMsg));
                }
            }
            divShpgPlnMsg.spTotDealSlsAmt.setValue(divShpgPlnMsg.spDealUnitPrcAmt.getValue().multiply(param.ordQty.getValue()));
            divShpgPlnMsg.spTotDealNetAmt.setValue(divShpgPlnMsg.spDealNetUnitPrcAmt.getValue().multiply(param.ordQty.getValue()));
            divShpgPlnMsg.spTotFuncSlsAmt.setValue(divShpgPlnMsg.spFuncUnitPrcAmt.getValue().multiply(param.ordQty.getValue()));
            divShpgPlnMsg.spTotFuncNetAmt.setValue(divShpgPlnMsg.spFuncNetUnitPrcAmt.getValue().multiply(param.ordQty.getValue()));
            if (ZYPCommonFunc.hasValue(param.bolNum)) {
                divShpgPlnMsg.bolNum.setValue(param.bolNum.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.carrNm)) {
                divShpgPlnMsg.carrNm.setValue(param.carrNm.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.proNum)) {
                divShpgPlnMsg.proNum.setValue(param.proNum.getValue());
            }
            // 2019/12/12 QC#54474 Add Start
            if (ZYPCommonFunc.hasValue(param.carrCd)) {
                divShpgPlnMsg.carrCd.setValue(param.carrCd.getValue());
            }
            // 2019/12/12 QC#54474 Add End
        } else {
            return;
        }

        // 2016/07/26 S21_NA#594-2 Modd Start
//        S21ApiTBLAccessor.create(divShpgPlnMsg);
        if (MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
            S21ApiTBLAccessor.update(divShpgPlnMsg);
        } else {
            S21ApiTBLAccessor.create(divShpgPlnMsg);
        }
        // 2016/07/26 S21_NA#594-2 Modd End
        if (!RTNCD_NOMAL.equals(divShpgPlnMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0078E);
        }
        // 2018/08/09 S21_NA#26865 Add Start
        // call Display Order Status Update API
        List<String> shpgPlnNumList = new ArrayList<String>();
        shpgPlnNumList.add(divShpgPlnMsg.shpgPlnNum.getValue());
        callDplyOrdStsUpdApi(msgMap, shpgPlnNumList, onBatchType, divShpgPlnMsg.trxHdrNum.getValue());
        // 2018/08/09 S21_NA#26865 Add End
        debug("-- >>>> [dividesShippingplan] END --");
    }

    // 2018/08/09 S21_NA#26865 Add Start
//    private void updateOriginalShippingplan(SHPG_PLNTMsg divOrigShpgPlnMsg, SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap) {
    private void updateOriginalShippingplan(SHPG_PLNTMsg divOrigShpgPlnMsg, SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
    // 2018/08/09 S21_NA#26865 Add End
        debug("-- >>>> [updateOriginalShippingplan] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
            if (divOrigShpgPlnMsg.avalSoQty.getValue().compareTo(divOrigShpgPlnMsg.ordQty.getValue()) == 0) {
                return;
            }
            divOrigShpgPlnMsg.shpgStsCd.setValue(spUpdModeMsg.shpgStsCd.getValue());
            divOrigShpgPlnMsg.rteStsCd.setValue(spUpdModeMsg.rteStsCd.getValue());
            divOrigShpgPlnMsg.shpgPlnDplyLineNum.setValue(getMaxDisplayNumber(param, divOrigShpgPlnMsg));
            divOrigShpgPlnMsg.ordQty.setValue(divOrigShpgPlnMsg.avalSoQty.getValue());
            divOrigShpgPlnMsg.crChkQty.setValue(divOrigShpgPlnMsg.avalSoQty.getValue());
            divOrigShpgPlnMsg.spTotDealSlsAmt.setValue(divOrigShpgPlnMsg.spDealUnitPrcAmt.getValue().multiply(divOrigShpgPlnMsg.avalSoQty.getValue()));
            divOrigShpgPlnMsg.spTotDealNetAmt.setValue(divOrigShpgPlnMsg.spDealNetUnitPrcAmt.getValue().multiply(divOrigShpgPlnMsg.avalSoQty.getValue()));
            divOrigShpgPlnMsg.spTotFuncSlsAmt.setValue(divOrigShpgPlnMsg.spFuncUnitPrcAmt.getValue().multiply(divOrigShpgPlnMsg.avalSoQty.getValue()));
            divOrigShpgPlnMsg.spTotFuncNetAmt.setValue(divOrigShpgPlnMsg.spFuncNetUnitPrcAmt.getValue().multiply(divOrigShpgPlnMsg.avalSoQty.getValue()));
            divOrigShpgPlnMsg.psdDt.setValue(param.psdDt.getValue());
            if (ZYPCommonFunc.hasValue(param.pddDt)) {
                divOrigShpgPlnMsg.pddDt.setValue(param.pddDt.getValue());
            }
            if (ZYPCommonFunc.hasValue(param.shpgSvcLvlCd)) {
                divOrigShpgPlnMsg.reqShpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());
            }
            divOrigShpgPlnMsg.crHldQty.setValue(BigDecimal.ZERO);
            divOrigShpgPlnMsg.shipPlnHldFlg.setValue(ZYPConstant.FLG_OFF_N);

        } else if (MODE_POPRINTED.equals(param.shpgModeCd.getValue())) {
            if (divOrigShpgPlnMsg.avalPoQty.getValue().compareTo(divOrigShpgPlnMsg.ordQty.getValue()) == 0) {
                return;
            }

            BigDecimal bkQty = divOrigShpgPlnMsg.avalPoQty.getValue();

            divOrigShpgPlnMsg.shpgStsCd.setValue(spUpdModeMsg.shpgStsCd.getValue());
            divOrigShpgPlnMsg.shpgPlnDplyLineNum.setValue(getMaxDisplayNumber(param, divOrigShpgPlnMsg));
            divOrigShpgPlnMsg.ordQty.setValue(bkQty);
            divOrigShpgPlnMsg.crChkQty.setValue(divOrigShpgPlnMsg.avalPoQty.getValue());
            divOrigShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
            divOrigShpgPlnMsg.poOrdNum.setValue(param.poOrdNum.getValue());
            divOrigShpgPlnMsg.spTotDealSlsAmt.setValue(divOrigShpgPlnMsg.spDealUnitPrcAmt.getValue().multiply(bkQty));
            divOrigShpgPlnMsg.spTotDealNetAmt.setValue(divOrigShpgPlnMsg.spDealNetUnitPrcAmt.getValue().multiply(bkQty));
            divOrigShpgPlnMsg.spTotFuncSlsAmt.setValue(divOrigShpgPlnMsg.spFuncUnitPrcAmt.getValue().multiply(bkQty));
            divOrigShpgPlnMsg.spTotFuncNetAmt.setValue(divOrigShpgPlnMsg.spFuncNetUnitPrcAmt.getValue().multiply(bkQty));

        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())
                || MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue()) //
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { // 2016/06/20 S21_NA#594 Add
            if (divOrigShpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) != 1) {
                return;
            }

            BigDecimal bkOrdQty = divOrigShpgPlnMsg.ordQty.getValue().subtract(param.ordQty.getValue());
            BigDecimal bkcrChkQty = divOrigShpgPlnMsg.crChkQty.getValue().subtract(param.ordQty.getValue());

            divOrigShpgPlnMsg.ordQty.setValue(bkOrdQty);
            divOrigShpgPlnMsg.crChkQty.setValue(bkcrChkQty);
            divOrigShpgPlnMsg.spTotDealSlsAmt.setValue(divOrigShpgPlnMsg.spDealUnitPrcAmt.getValue().multiply(bkOrdQty));
            divOrigShpgPlnMsg.spTotDealNetAmt.setValue(divOrigShpgPlnMsg.spDealNetUnitPrcAmt.getValue().multiply(bkOrdQty));
            divOrigShpgPlnMsg.spTotFuncSlsAmt.setValue(divOrigShpgPlnMsg.spFuncUnitPrcAmt.getValue().multiply(bkOrdQty));
            divOrigShpgPlnMsg.spTotFuncNetAmt.setValue(divOrigShpgPlnMsg.spFuncNetUnitPrcAmt.getValue().multiply(bkOrdQty));

            // 2016/06/20 S21_NA#594 Add Start
            if (MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
                CPO_DTLTMsg cpoDtlMsg = new CPO_DTLTMsg();
                searchCPODetailForUpdate(divOrigShpgPlnMsg, cpoDtlMsg, msgMap);
                // Error Judgment
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
                // exists order data.
                if (ZYPCommonFunc.hasValue(cpoDtlMsg.cpoOrdNum)) {
                    if (ZYPCommonFunc.hasValue(cpoDtlMsg.rddDt)) {
                        divOrigShpgPlnMsg.rddDt.setValue(cpoDtlMsg.rddDt.getValue());
                    } else {
                        divOrigShpgPlnMsg.rddDt.clear();
                    }
                    if (ZYPCommonFunc.hasValue(cpoDtlMsg.rsdDt)) {
                        divOrigShpgPlnMsg.rsdDt.setValue(cpoDtlMsg.rsdDt.getValue());
                    } else {
                        divOrigShpgPlnMsg.rsdDt.clear();
                    }
                }

                divOrigShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
                divOrigShpgPlnMsg.psdDt.clear();
                divOrigShpgPlnMsg.pddDt.clear();

                ZYPEZDItemValueSetter.setValue(divOrigShpgPlnMsg.shpgStsCd, spUpdModeMsg.shpgStsCd);
                ZYPEZDItemValueSetter.setValue(divOrigShpgPlnMsg.rteStsCd, spUpdModeMsg.rteStsCd);

                MDSE_STORE_PKGTMsg mdseStorePkgTMsg = getStorePkg(param.glblCmpyCd.getValue(), divOrigShpgPlnMsg);
                BigDecimal inEachQty = BigDecimal.ONE;
                if (null != mdseStorePkgTMsg && ZYPCommonFunc.hasValue(mdseStorePkgTMsg.inEachQty)) {
                    inEachQty = mdseStorePkgTMsg.inEachQty.getValue();
                }
                divOrigShpgPlnMsg.ordUomQty.setValue(getOrdUomQty(bkOrdQty, inEachQty));
                divOrigShpgPlnMsg.shpgPlnNum.setValue(ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqConstant.SHPG_PLN_SQ, 8)); // 2016/07/26 S21_NA#594-2 Add
                divOrigShpgPlnMsg.shpgPlnDplyLineNum.setValue(getMaxDisplayNumber(param, divOrigShpgPlnMsg));
            }
            // 2016/06/20 S21_NA#594 Add 

        } else {
            return;
        }

        // 2016/07/26 S21_NA#594-2 Modd Start
//        S21ApiTBLAccessor.update(divOrigShpgPlnMsg);
        if (MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
            S21ApiTBLAccessor.create(divOrigShpgPlnMsg);
        } else {
            S21ApiTBLAccessor.update(divOrigShpgPlnMsg);
        }
        // 2016/07/26 S21_NA#594-2 Modd Start
        if (!RTNCD_NOMAL.equals(divOrigShpgPlnMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0078E);
        }
        // 2018/08/09 S21_NA#26865 Add Start
        // call Display Order Status Update API
        List<String> shpgPlnNumList = new ArrayList<String>();
        shpgPlnNumList.add(divOrigShpgPlnMsg.shpgPlnNum.getValue());
        callDplyOrdStsUpdApi(msgMap, shpgPlnNumList, onBatchType, divOrigShpgPlnMsg.trxHdrNum.getValue());
        // 2018/08/09 S21_NA#26865 Add End
        debug("-- >>>> [updateOriginalShippingplan] END --");
    }

    private void chkUpdateShippingPlan(SHPG_PLNTMsg shpgPlnMsg, S21ApiMessageMap msgMap) {
        debug("-- >>>> [chkUpdateShippingPlan] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())) {
            if (shpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) != -1) {
                return;
            }
            msgMap.addXxMsgId(NWZM0339E);

        } else if (MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue())) {
            if (shpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) != -1) {
                return;
            }
            msgMap.addXxMsgId(NWZM0340E);
        }
        debug("-- >>>> [chkUpdateShippingPlan] END --");
    }

    private void dividesPricingDetail(SHPG_PLNTMsg shpgPlnMsg, SHPG_PLNTMsg divShpgPlnMsg, SHPG_PLNTMsg divOrigShpgPlnMsg, S21ApiMessageMap msgMap) {
        debug("-- >>>> [dividesPricingDetail] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
            if (shpgPlnMsg.avalSoQty.getValue().compareTo(shpgPlnMsg.ordQty.getValue()) == 0) {
                return;
            }
        } else if (MODE_POPRINTED.equals(param.shpgModeCd.getValue())) {
            if (shpgPlnMsg.avalPoQty.getValue().compareTo(shpgPlnMsg.ordQty.getValue()) == 0) {
                return;
            }
        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue()) //
                || MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue()) //
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { // 2016/06/20 S21_NA#594 Add
            if (shpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) != 1) {
                return;
            }
        } else {
            return;
        }

        if (ZYPConstant.FLG_OFF_N.equals(getCPOExistenceFlagKeyTransactionSourceTypeCode(param.glblCmpyCd.getValue(), shpgPlnMsg.trxSrcTpCd.getValue()))) {
            return;
        }

        PRC_DTLTMsg tMsg = new PRC_DTLTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        tMsg.setConditionValue("shpgPlnNum01", shpgPlnMsg.shpgPlnNum.getValue());
        PRC_DTLTMsgArray tMsgArray = (PRC_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
        if (tMsgArray.getValidCount() == 0) {
            if (!TRX_SRC_TP.RETAIL.equals(divShpgPlnMsg.trxSrcTpCd.getValue())) {
                msgMap.addXxMsgId(NWZM0202E);
            }
            return;
        }

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {

            PRC_DTLTMsg divPrcDtlMsg = new PRC_DTLTMsg();
            PRC_DTLTMsg prcDtlMsg = (PRC_DTLTMsg) tMsgArray.no(i);
            EZDMsg.copy(prcDtlMsg, null, divPrcDtlMsg, null);

            if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())
                    || MODE_POPRINTED.equals(param.shpgModeCd.getValue())) {

                BigDecimal shipQty = divPrcDtlMsg.shipUnitQty.getValue().subtract(divOrigShpgPlnMsg.ordQty.getValue());

                divPrcDtlMsg.prcDtlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_DTL_SQ));
                divPrcDtlMsg.shpgPlnNum.setValue(divShpgPlnMsg.shpgPlnNum.getValue());
                divPrcDtlMsg.shipUnitQty.setValue(shipQty);
                divPrcDtlMsg.dealNetAmt.setValue(divPrcDtlMsg.dealLastNetUnitPrcAmt.getValue().multiply(shipQty));
                divPrcDtlMsg.funcNetAmt.setValue(divPrcDtlMsg.funcLastNetUnitPrcAmt.getValue().multiply(shipQty));
                divPrcDtlMsg.dealDiscAmt.setValue(divPrcDtlMsg.dealPerUnitFixAmt.getValue().multiply(shipQty));
                divPrcDtlMsg.funcDiscAmt.setValue(divPrcDtlMsg.funcPerUnitFixAmt.getValue().multiply(shipQty));

            } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue()) //
                    || MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue()) //
                    || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { // 2016/06/20 S21_NA#594 Add

                divPrcDtlMsg.prcDtlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_DTL_SQ));
                divPrcDtlMsg.shpgPlnNum.setValue(divShpgPlnMsg.shpgPlnNum.getValue());
                divPrcDtlMsg.shipUnitQty.setValue(param.ordQty.getValue());
                divPrcDtlMsg.dealNetAmt.setValue(divPrcDtlMsg.dealLastNetUnitPrcAmt.getValue().multiply(param.ordQty.getValue()));
                divPrcDtlMsg.funcNetAmt.setValue(divPrcDtlMsg.funcLastNetUnitPrcAmt.getValue().multiply(param.ordQty.getValue()));
                divPrcDtlMsg.dealDiscAmt.setValue(divPrcDtlMsg.dealPerUnitFixAmt.getValue().multiply(param.ordQty.getValue()));
                divPrcDtlMsg.funcDiscAmt.setValue(divPrcDtlMsg.funcPerUnitFixAmt.getValue().multiply(param.ordQty.getValue()));

            } else {
                return;
            }

            S21ApiTBLAccessor.create(divPrcDtlMsg);
            if (!RTNCD_NOMAL.equals(divPrcDtlMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM0079E);
                return;
            }

            if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())
                    || MODE_POPRINTED.equals(param.shpgModeCd.getValue())) {
                prcDtlMsg.shipUnitQty.setValue(divOrigShpgPlnMsg.ordQty.getValue());
                prcDtlMsg.dealNetAmt.setValue(prcDtlMsg.dealLastNetUnitPrcAmt.getValue().multiply(divOrigShpgPlnMsg.ordQty.getValue()));
                prcDtlMsg.funcNetAmt.setValue(prcDtlMsg.funcLastNetUnitPrcAmt.getValue().multiply(divOrigShpgPlnMsg.ordQty.getValue()));
                prcDtlMsg.dealDiscAmt.setValue(prcDtlMsg.dealPerUnitFixAmt.getValue().multiply(divOrigShpgPlnMsg.ordQty.getValue()));
                prcDtlMsg.funcDiscAmt.setValue(prcDtlMsg.funcPerUnitFixAmt.getValue().multiply(divOrigShpgPlnMsg.ordQty.getValue()));

            } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue()) //
                    || MODE_VENDERINVOICERECEIVED.equals(param.shpgModeCd.getValue()) //
                    || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { // 2016/06/20 S21_NA#594 Add

                BigDecimal shipQty = prcDtlMsg.shipUnitQty.getValue().subtract(param.ordQty.getValue());

                prcDtlMsg.shipUnitQty.setValue(shipQty);
                prcDtlMsg.dealNetAmt.setValue(prcDtlMsg.dealLastNetUnitPrcAmt.getValue().multiply(shipQty));
                prcDtlMsg.funcNetAmt.setValue(prcDtlMsg.funcLastNetUnitPrcAmt.getValue().multiply(shipQty));
                prcDtlMsg.dealDiscAmt.setValue(prcDtlMsg.dealPerUnitFixAmt.getValue().multiply(shipQty));
                prcDtlMsg.funcDiscAmt.setValue(prcDtlMsg.funcPerUnitFixAmt.getValue().multiply(shipQty));

                // 2016/07/26 S21_NA#594-2 Add Start
                if (MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
                    prcDtlMsg.shpgPlnNum.setValue(divOrigShpgPlnMsg.shpgPlnNum.getValue());
                }
                // 2016/07/26 S21_NA#594-2 Add End

            } else {
                return;
            }

            S21ApiTBLAccessor.update(prcDtlMsg);
            if (!RTNCD_NOMAL.equals(prcDtlMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM0079E);
                return;
            }
        }
        debug("-- >>>> [dividesPricingDetail] END --");
    }

    private void dividesHardAllocation(SHPG_PLNTMsg shpgPlnMsg, SHPG_PLNTMsg divShpgPlnMsg, SHPG_PLNTMsg divOrigShpgPlnMsg, S21ApiMessageMap msgMap) {
        debug("-- >>>> [dividesHardAllocation] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
            if (shpgPlnMsg.avalSoQty.getValue().compareTo(shpgPlnMsg.ordQty.getValue()) == 0) {
                return;
            }
        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue()) //
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { // 2016/06/20 S21_NA#594 Add
            if (shpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) != 1) {
                return;
            }
        } else {
            return;
        }

        BigDecimal bkQty = BigDecimal.ZERO;
        if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
            bkQty = divOrigShpgPlnMsg.ordQty.getValue();

        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue()) //
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { // 2016/06/20 S21_NA#594 Add
            bkQty = divShpgPlnMsg.ordQty.getValue();
        }

        HARD_ALLOCTMsg hardAllocMsg = new HARD_ALLOCTMsg();
        hardAllocMsg.setSQLID("009");
        hardAllocMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        hardAllocMsg.setConditionValue("shpgPlnNum01", shpgPlnMsg.shpgPlnNum.getValue());

        HARD_ALLOCTMsgArray msgArray = (HARD_ALLOCTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(hardAllocMsg);

        for (int i = 0; i < msgArray.getValidCount(); i++) {

            HARD_ALLOCTMsg updHardAllocMsg = msgArray.no(i);

            HARD_ALLOCTMsg divHardAllocMsg = new HARD_ALLOCTMsg();
            EZDMsg.copy(updHardAllocMsg, null, divHardAllocMsg, null);

            if (updHardAllocMsg.hardAllocQty.getValue().compareTo(bkQty) <= 0) {

                if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())) {
                    updHardAllocMsg.shpgPlnNum.setValue(divShpgPlnMsg.shpgPlnNum.getValue());

                    S21FastTBLAccessor.update(updHardAllocMsg);
                    if (!RTNCD_NOMAL.equals(updHardAllocMsg.getReturnCode())) {
                        msgMap.addXxMsgId(NWZM0662E);
                        return;
                    }
                }

                bkQty = bkQty.subtract(updHardAllocMsg.hardAllocQty.getValue());

            } else if (updHardAllocMsg.hardAllocQty.getValue().compareTo(bkQty) == 1 && bkQty.compareTo(BigDecimal.ZERO) != 0) {

                divHardAllocMsg.hardAllocPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.HARD_ALLOC_SQ));
                if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
                    divHardAllocMsg.shpgPlnNum.setValue(divShpgPlnMsg.shpgPlnNum.getValue());
                }
                divHardAllocMsg.hardAllocQty.setValue(divHardAllocMsg.hardAllocQty.getValue().subtract(bkQty));

                // 2016/07/26 S21_NA#594-2 Add Start
                if (MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) {
                    divHardAllocMsg.shpgPlnNum.setValue(divOrigShpgPlnMsg.shpgPlnNum.getValue());
                }
                // 2016/07/26 S21_NA#594-2 Add End

                S21FastTBLAccessor.insert(divHardAllocMsg);
                if (!RTNCD_NOMAL.equals(divHardAllocMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0662E);
                    return;
                }

                updHardAllocMsg.hardAllocQty.setValue(bkQty);
                if (MODE_SHIPPED.equals(param.shpgModeCd.getValue()) //
                        || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { // 2016/06/20 S21_NA#594 Add
                    updHardAllocMsg.shpgPlnNum.setValue(divShpgPlnMsg.shpgPlnNum.getValue());
                }

                S21FastTBLAccessor.update(updHardAllocMsg);
                if (!RTNCD_NOMAL.equals(updHardAllocMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0662E);
                    return;
                }

                bkQty = BigDecimal.ZERO;

            } else if (bkQty.compareTo(BigDecimal.ZERO) == 0) {

                if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
                    updHardAllocMsg.shpgPlnNum.setValue(divShpgPlnMsg.shpgPlnNum.getValue());

                    S21FastTBLAccessor.update(updHardAllocMsg);
                    if (!RTNCD_NOMAL.equals(updHardAllocMsg.getReturnCode())) {
                        msgMap.addXxMsgId(NWZM0662E);
                        return;
                    }
                }
            }
        }
        debug("-- >>>> [dividesHardAllocation] END --");
    }

    private void dividesSoftAllocation(SHPG_PLNTMsg shpgPlnMsg, SHPG_PLNTMsg divShpgPlnMsg, SHPG_PLNTMsg divOrigShpgPlnMsg, S21ApiMessageMap msgMap) {
        debug("-- >>>> [dividesSoftAllocation] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
            if (shpgPlnMsg.avalSoQty.getValue().compareTo(shpgPlnMsg.ordQty.getValue()) == 0) {
                return;
            }
        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())) {
            if (shpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) != 1) {
                return;
            }
        } else {
            return;
        }

        List<BigDecimal> softAllocPKList = searchSoftAllocation(param.glblCmpyCd.getValue(), shpgPlnMsg.shpgPlnNum.getValue());
        if (softAllocPKList.isEmpty()) {
            return;
        }

        BigDecimal bkQty = BigDecimal.ZERO;
        if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
            bkQty = divOrigShpgPlnMsg.ordQty.getValue();

        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())) {
            bkQty = divShpgPlnMsg.ordQty.getValue();
        }

        for (int i = 0; i < softAllocPKList.size(); i++) {

            SOFT_ALLOCTMsg softAllocMsg = new SOFT_ALLOCTMsg();
            softAllocMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            softAllocMsg.softAllocPk.setValue((BigDecimal) softAllocPKList.get(i));
            softAllocMsg = (SOFT_ALLOCTMsg) S21ApiTBLAccessor.findByKeyForUpdate(softAllocMsg);
            if (null == softAllocMsg) {
                msgMap.addXxMsgId(NWZM0075E);
                return;
            }

            SOFT_ALLOCTMsg divSoftAllocMsg = new SOFT_ALLOCTMsg();
            EZDMsg.copy(softAllocMsg, null, divSoftAllocMsg, null);

            if (softAllocMsg.softAllocQty.getValue().compareTo(bkQty) <= 0) {

                if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())) {
                    softAllocMsg.shpgPlnNum.setValue(divShpgPlnMsg.shpgPlnNum.getValue());

                    S21ApiTBLAccessor.update(softAllocMsg);
                    if (!RTNCD_NOMAL.equals(softAllocMsg.getReturnCode())) {
                        msgMap.addXxMsgId(NWZM0662E);
                        return;
                    }
                }

                bkQty = bkQty.subtract(softAllocMsg.softAllocQty.getValue());

            } else if (softAllocMsg.softAllocQty.getValue().compareTo(bkQty) == 1 && bkQty.compareTo(BigDecimal.ZERO) != 0) {

                divSoftAllocMsg.softAllocPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SOFT_ALLOC_SQ));
                if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
                    divSoftAllocMsg.shpgPlnNum.setValue(divShpgPlnMsg.shpgPlnNum.getValue());
                }
                divSoftAllocMsg.softAllocQty.setValue(divSoftAllocMsg.softAllocQty.getValue().subtract(bkQty));

                S21ApiTBLAccessor.create(divSoftAllocMsg);
                if (!RTNCD_NOMAL.equals(divSoftAllocMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0662E);
                    return;
                }

                softAllocMsg.softAllocQty.setValue(bkQty);
                if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())) {
                    softAllocMsg.shpgPlnNum.setValue(divShpgPlnMsg.shpgPlnNum.getValue());
                }

                S21ApiTBLAccessor.update(softAllocMsg);
                if (!RTNCD_NOMAL.equals(softAllocMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0662E);
                    return;
                }

                bkQty = BigDecimal.ZERO;

            } else if (bkQty.compareTo(BigDecimal.ZERO) == 0) {

                if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
                    softAllocMsg.shpgPlnNum.setValue(divShpgPlnMsg.shpgPlnNum.getValue());

                    S21ApiTBLAccessor.update(softAllocMsg);
                    if (!RTNCD_NOMAL.equals(softAllocMsg.getReturnCode())) {
                        msgMap.addXxMsgId(NWZM0662E);
                        return;
                    }
                }
            }
        }
        debug("-- >>>> [dividesSoftAllocation] END --");
    }

    private void updateSoftAllocQty(SHPG_PLNTMsg shpgPlnMsg, SHPG_PLNTMsg updShpgPlnMsg, SHPG_PLNTMsg divShpgPlnMsg, SHPG_PLNTMsg divOrigShpgPlnMsg, S21ApiMessageMap msgMap) {
        debug("-- >>>> [updateSoftAllocQty] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        SHPG_PLNTMsg updateMsg = new SHPG_PLNTMsg();
        if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
            if (shpgPlnMsg.avalSoQty.getValue().compareTo(shpgPlnMsg.ordQty.getValue()) == 0) {
                EZDMsg.copy(updShpgPlnMsg, null, updateMsg, null);
            } else {
                EZDMsg.copy(divOrigShpgPlnMsg, null, updateMsg, null);
            }
        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())) {
            if (shpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) != 1) {
                EZDMsg.copy(updShpgPlnMsg, null, updateMsg, null);
            } else {
                EZDMsg.copy(divOrigShpgPlnMsg, null, updateMsg, null);
            }
        }

        // Original
        List<BigDecimal> softAllocPKList = searchSoftAllocation(param.glblCmpyCd.getValue(), updateMsg.shpgPlnNum.getValue());
        if (softAllocPKList.isEmpty()) {
            return;
        }

        BigDecimal softAllocQty = BigDecimal.ZERO;

        for (int i = 0; i < softAllocPKList.size(); i++) {

            SOFT_ALLOCTMsg softAllocMsg = new SOFT_ALLOCTMsg();
            softAllocMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            softAllocMsg.softAllocPk.setValue((BigDecimal) softAllocPKList.get(i));
            softAllocMsg = (SOFT_ALLOCTMsg) S21ApiTBLAccessor.findByKey(softAllocMsg);
            if (null == softAllocMsg) {
                msgMap.addXxMsgId(NWZM0075E);
                return;
            }
            softAllocQty = softAllocQty.add(softAllocMsg.softAllocQty.getValue());
        }

        updateMsg.softAllocQty.setValue(softAllocQty);

        S21ApiTBLAccessor.update(updateMsg);
        if (!RTNCD_NOMAL.equals(updateMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0078E);
            return;
        }

        // Divides
        if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {
            if (shpgPlnMsg.avalSoQty.getValue().compareTo(shpgPlnMsg.ordQty.getValue()) == 0) {
                return;
            }
        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())) {
            if (shpgPlnMsg.ordQty.getValue().compareTo(param.ordQty.getValue()) != 1) {
                return;
            }
        }

        softAllocPKList = searchSoftAllocation(param.glblCmpyCd.getValue(), divShpgPlnMsg.shpgPlnNum.getValue());
        if (softAllocPKList.isEmpty()) {
            return;
        }

        softAllocQty = BigDecimal.ZERO;

        for (int i = 0; i < softAllocPKList.size(); i++) {

            SOFT_ALLOCTMsg softAllocMsg = new SOFT_ALLOCTMsg();
            softAllocMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            softAllocMsg.softAllocPk.setValue((BigDecimal) softAllocPKList.get(i));
            softAllocMsg = (SOFT_ALLOCTMsg) S21ApiTBLAccessor.findByKey(softAllocMsg);
            if (null == softAllocMsg) {
                msgMap.addXxMsgId(NWZM0075E);
                return;
            }
            softAllocQty = softAllocQty.add(softAllocMsg.softAllocQty.getValue());
        }

        divShpgPlnMsg.softAllocQty.setValue(softAllocQty);

        S21ApiTBLAccessor.update(divShpgPlnMsg);
        if (!RTNCD_NOMAL.equals(divShpgPlnMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0078E);
            return;
        }

        debug("-- >>>> [updateSoftAllocQty] END --");
    }

    private void updateCPODetail(SHPG_PLNTMsg updShpgPlnMsg, S21ApiMessageMap msgMap) {
        debug("-- >>>> [updateCPODetail] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (ZYPConstant.FLG_OFF_N.equals(getCPOExistenceFlagKeyTransactionSourceTypeCode(param.glblCmpyCd.getValue(), updShpgPlnMsg.trxSrcTpCd.getValue()))) {
            return;
        }

        CPO_DTLTMsg cpoDtlMsg = new CPO_DTLTMsg();
        searchCPODetail(updShpgPlnMsg, cpoDtlMsg, msgMap);

        cpoDtlMsg.shipQty.setValue(cpoDtlMsg.shipQty.getValue().add(updShpgPlnMsg.ordQty.getValue()));

        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        shpgPlnMsg.setSQLID("019");
        shpgPlnMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        shpgPlnMsg.setConditionValue("trxHdrNum01", updShpgPlnMsg.trxHdrNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineNum01", updShpgPlnMsg.trxLineNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineSubNum01", updShpgPlnMsg.trxLineSubNum.getValue());
        shpgPlnMsg.setConditionValue("trxSrcTpCd01", updShpgPlnMsg.trxSrcTpCd.getValue());
        SHPG_PLNTMsgArray shpgPlnMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByCondition(shpgPlnMsg);
        if (shpgPlnMsgArray.getValidCount() == 0) {
            msgMap.addXxMsgId(NWZM0075E);
            return;
        }

        // 2016/06/29 S21_NA#7189 Add Start
        boolean isDtlClosed = S21StringUtil.isEquals(ORD_LINE_STS.CLOSED, cpoDtlMsg.ordLineStsCd.getValue());
        // 2016/06/29 S21_NA#7189 Add End
        if (!isDtlClosed) { // 2016/06/29 S21_NA#7189 Add
            for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {
    
                if (SHPG_STS.SHIPPED.equals((String) shpgPlnMsgArray.no(i).shpgStsCd.getValue())
                        || SHPG_STS.INSHED.equals((String) shpgPlnMsgArray.no(i).shpgStsCd.getValue())
                        || SHPG_STS.INVOICED.equals((String) shpgPlnMsgArray.no(i).shpgStsCd.getValue())
                        || SHPG_STS.CANCELLED.equals((String) shpgPlnMsgArray.no(i).shpgStsCd.getValue()) //
                        || SHPG_STS.ARRIVED.equals((String) shpgPlnMsgArray.no(i).shpgStsCd.getValue()) // 2016/06/29 S21_NA#7189 Add
                        || SHPG_STS.INSTALLED.equals((String) shpgPlnMsgArray.no(i).shpgStsCd.getValue()) ) { // 2016/06/29 S21_NA#7189 Add
                    cpoDtlMsg.ordLineStsCd.setValue(ORD_LINE_STS.SHIPPED);
    
                } else {
                    cpoDtlMsg.ordLineStsCd.setValue(ORD_LINE_STS.PARTIALLY_SHIPPED);
                    break;
                }
            }
        } // 2016/06/29 S21_NA#7189 Add

        S21ApiTBLAccessor.update(cpoDtlMsg);
        if (!RTNCD_NOMAL.equals(cpoDtlMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0081E);
            return;
        }
        debug("-- >>>> [updateCPODetail] END --");
    }

    private void updateCPODetailShipData(CPO_DTLTMsg cpoDtlMsg, S21ApiMessageMap msgMap) {

        debug("-- >>>> [updateCPODetailShipData] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        copyShipToCustData(param, cpoDtlMsg, getShipToCustData(param, cpoDtlMsg.shipToCustCd.getValue()));

        S21ApiTBLAccessor.update(cpoDtlMsg);
        if (!RTNCD_NOMAL.equals(cpoDtlMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0081E);
            return;
        }

        debug("-- >>>> [updateCPODetailShipData] END --");
    }

    private void updateCPOShipData(CPOTMsg cpoMsg, S21ApiMessageMap msgMap) {

        debug("-- >>>> [updateCPOShipData] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        copyShipToCustData(param, cpoMsg, getShipToCustData(param, cpoMsg.addShipToCustCd.getValue()));

        S21ApiTBLAccessor.update(cpoMsg);
        if (!RTNCD_NOMAL.equals(cpoMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0081E);
            return;
        }

        debug("-- >>>> [updateCPOShipData] END --");
    }

    private void outProcessLog(NWZC003001PMsg param, String eventID, SHPG_PLNTMsg shpgPlnMsg) {
        debug("-- >>>> [outProcessLog] START --");

        if (ZYPConstant.FLG_OFF_N.equals(getCPOExistenceFlagKeyTransactionSourceTypeCode(param.glblCmpyCd.getValue(), shpgPlnMsg.trxSrcTpCd.getValue()))) {
            return;
        }

        S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();
        bizProcLogMsg.setSubSysId(SUBSYSID);
        bizProcLogMsg.setProcId(PROCID);
        bizProcLogMsg.setEventId(eventID);
        bizProcLogMsg.setDocTpCd(DOCTPCD);
        bizProcLogMsg.setDocId(ZYPCommonFunc.concatString(shpgPlnMsg.trxLineNum.getValue(), ".", shpgPlnMsg.trxLineSubNum.getValue()));
        bizProcLogMsg.setPrntDocId(shpgPlnMsg.trxHdrNum.getValue());
        S21BusinessProcessLog.print(bizProcLogMsg);
        debug("-- >>>> [outProcessLog] END --");
    }

    private void callAddHoldInfoAPI(SHPG_PLNTMsg shpgPlnMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- >>>> [callAddHoldInfoAPI] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        NWZC044001PMsg addHoldInfoMsg = new NWZC044001PMsg();
        addHoldInfoMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        addHoldInfoMsg.cpoOrdNum.setValue(shpgPlnMsg.trxHdrNum.getValue());
        addHoldInfoMsg.cpoDtlLineNum.setValue(shpgPlnMsg.trxLineNum.getValue());
        addHoldInfoMsg.cpoDtlLineSubNum.setValue(shpgPlnMsg.trxLineSubNum.getValue());
        addHoldInfoMsg.shpgPlnNum.setValue(shpgPlnMsg.shpgPlnNum.getValue());

        if (MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue()) //
                || MODE_PARTIAL_SHIP.equals(param.shpgModeCd.getValue())) { // 2016/06/20 S21_NA#594 Add
            addHoldInfoMsg.hldRsnCd.setValue(HLD_RSN.S_OR_O_CANCEL);
        } else if (MODE_POCANCELLED.equals(param.shpgModeCd.getValue())) {
            addHoldInfoMsg.hldRsnCd.setValue(HLD_RSN.PURCHASE_ORDER_CANCELL);
        } else {
            return;
        }
        addHoldInfoMsg.slsDt.setValue(param.slsDt.getValue());

        NWZC044001 addHoldInfoApi = new NWZC044001();
        addHoldInfoApi.execute(addHoldInfoMsg, onBatchType);

        if (addHoldInfoMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < addHoldInfoMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId((String) addHoldInfoMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }
        debug("-- >>>> [callAddHoldInfoAPI] END --");
    }

    private void updateHold(SHPG_PLNTMsg shpgPlnMsg, SHPG_PLNTMsg divShpgPlnMsg, S21ApiMessageMap msgMap) {
        debug("-- >>>> [updateHold] START --");

        if (divShpgPlnMsg.avalSoQty.getValue().compareTo(divShpgPlnMsg.ordQty.getValue()) == 0) {
            return;
        }
        if (ZYPConstant.FLG_OFF_N.equals(shpgPlnMsg.shipPlnHldFlg.getValue())) {
            return;
        }

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        HLDTMsg hldMsg = new HLDTMsg();
        hldMsg.setSQLID("003");
        hldMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        hldMsg.setConditionValue("shpgPlnNum01", shpgPlnMsg.shpgPlnNum.getValue());
        HLDTMsgArray hldMsgArray = (HLDTMsgArray) S21ApiTBLAccessor.findByCondition(hldMsg);
        if (hldMsgArray.getValidCount() == 0) {
            return;
        }

        for (int i = 0; i < hldMsgArray.getValidCount(); i++) {
            hldMsg = hldMsgArray.no(i);
            hldMsg.shpgPlnNum.setValue(divShpgPlnMsg.shpgPlnNum.getValue());
            S21ApiTBLAccessor.update(hldMsg);
        }

        debug("-- >>>> [updateHold] END --");
    }

    private String getCPOExistenceFlagKeyShippingPlanNumber(NWZC003001PMsg param) {
        return (String) this.ssmBatchClient.queryObject("getCPOExistenceFlag", param);
    }

    private String getCarrierName(NWZC003001PMsg param) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", param.glblCmpyCd.getValue());
        map.put("vndCd", param.carrCd.getValue());
        map.put("vndTpCd", VND_TP.OUTBOUND_CARRIER);
        return (String) this.ssmBatchClient.queryObject("getCarrierName", map);
    }

    private String getMaxDisplayNumber(NWZC003001PMsg param, SHPG_PLNTMsg shpgPlnMsg) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", param.glblCmpyCd.getValue());
        map.put("trxHdrNum", shpgPlnMsg.trxHdrNum.getValue());
        map.put("trxLineNum", shpgPlnMsg.trxLineNum.getValue());
        map.put("trxLineSubNum", shpgPlnMsg.trxLineSubNum.getValue());
        map.put("trxSrcTpCd", shpgPlnMsg.trxSrcTpCd.getValue());

        String dispNum = (String) this.ssmBatchClient.queryObject("getMaxDisplayNumber", map);
        return ZYPCommonFunc.leftPad(String.valueOf(Integer.parseInt(dispNum) + 1), 3, "0");
    }

    private List<BigDecimal> searchSoftAllocation(String glblCmpyCd, String shpgPlnNum) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", glblCmpyCd);
        map.put("shpgPlnNum", shpgPlnNum);
        return (List) this.ssmBatchClient.queryObjectList("searchSoftAllocation", map);
    }

    private SHIP_TO_CUSTTMsg getShipToCustData(NWZC003001PMsg param, String shipToCustCd) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", param.glblCmpyCd.getValue());
        map.put("shipToCustCd", shipToCustCd);

        StringBuilder cacheKey = new StringBuilder();
        cacheKey.append("glblCmpyCd=").append(param.glblCmpyCd.getValue()).append(",");
        cacheKey.append("shipToCustCd=").append(shipToCustCd).append(",");

        SHIP_TO_CUSTTMsg shipToCustMsg = shipToCache.get(cacheKey.toString());

        if (shipToCustMsg == null) {

            if (TRX_SRC_TP.VENDOR_RETURN.equals(param.trxSrcTpCd.getValue())) {

                shipToCustMsg = (SHIP_TO_CUSTTMsg) this.ssmBatchClient.queryObject("getVndData", map);

            } else {

                shipToCustMsg = (SHIP_TO_CUSTTMsg) this.ssmBatchClient.queryObject("getShipToCustData", map);
            }

            shipToCache.put(cacheKey.toString(), shipToCustMsg);
        }

        return shipToCustMsg;
    }

    private String getCntyNm(String glblCmpyCd, BigDecimal cntyPk) {

        CNTYTMsg cntyMsg = new CNTYTMsg();
        cntyMsg.glblCmpyCd.setValue(glblCmpyCd);
        cntyMsg.cntyPk.setValue(cntyPk);

        cntyMsg = (CNTYTMsg) S21CacheTBLAccessor.findByKey(cntyMsg);

        if (cntyMsg != null) {
            return cntyMsg.cntyNm.getValue();
        }

        return null;
    }

    private void copyShipToCustData(NWZC003001PMsg param, CPOTMsg cpoMsg, SHIP_TO_CUSTTMsg shipToCustMsg) {

        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToLocNm, shipToCustMsg.locNm);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToAddlLocNm, shipToCustMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToFirstLineAddr, shipToCustMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToScdLineAddr, shipToCustMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToThirdLineAddr, shipToCustMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToFrthLineAddr, shipToCustMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipTo01RefCmntTxt, shipToCustMsg.firstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipTo02RefCmntTxt, shipToCustMsg.scdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToCtyAddr, shipToCustMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToStCd, shipToCustMsg.stCd);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToProvNm, shipToCustMsg.provNm);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToCntyNm, getCntyNm(param.glblCmpyCd.getValue(), shipToCustMsg.cntyPk.getValue()));
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToPostCd, shipToCustMsg.postCd);
        ZYPEZDItemValueSetter.setValue(cpoMsg.addShipToCtryCd, shipToCustMsg.ctryCd);
    }

    private void copyShipToCustData(NWZC003001PMsg param, CPO_DTLTMsg cpoDtlMsg, SHIP_TO_CUSTTMsg shipToCustMsg) {

        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToLocNm, shipToCustMsg.locNm);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToAddlLocNm, shipToCustMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToFirstLineAddr, shipToCustMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToScdLineAddr, shipToCustMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToThirdLineAddr, shipToCustMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToFrthLineAddr, shipToCustMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToFirstRefCmntTxt, shipToCustMsg.firstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToScdRefCmntTxt, shipToCustMsg.scdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToCtyAddr, shipToCustMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToStCd, shipToCustMsg.stCd);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToProvNm, shipToCustMsg.provNm);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToCntyNm, getCntyNm(param.glblCmpyCd.getValue(), shipToCustMsg.cntyPk.getValue()));
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToPostCd, shipToCustMsg.postCd);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.shipToCtryCd, shipToCustMsg.ctryCd);
    }

    private void copyShipToCustData(NWZC003001PMsg param, SHPG_PLNTMsg shpgPlnMsg, SHIP_TO_CUSTTMsg shipToCustMsg) {

        if (shipToCustMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToLocNm, shipToCustMsg.locNm);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToAddlLocNm, shipToCustMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToFirstLineAddr, shipToCustMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToScdLineAddr, shipToCustMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToThirdLineAddr, shipToCustMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToFrthLineAddr, shipToCustMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToFirstRefCmntTxt, shipToCustMsg.firstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToScdRefCmntTxt, shipToCustMsg.scdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToCtyAddr, shipToCustMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToStCd, shipToCustMsg.stCd);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToProvAddr, shipToCustMsg.provNm);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToCntyNm, getCntyNm(param.glblCmpyCd.getValue(), shipToCustMsg.cntyPk.getValue()));
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToPostCd, shipToCustMsg.postCd);
        ZYPEZDItemValueSetter.setValue(shpgPlnMsg.shipToCtryCd, shipToCustMsg.ctryCd);
    }

    private void updSetShpgPlnSts(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsg, ONBATCH_TYPE onBatchType) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(shpgPlnMsg.setMdseCd) && !(ZYPCommonFunc.hasValue(shpgPlnMsg.setShpgPlnNum))) {
            return;
        }

        if (!MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue())
                && !MODE_POCANCELLED.equals(param.shpgModeCd.getValue())
                && !MODE_INSHED.equals(param.shpgModeCd.getValue())
                && !MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())
                && !MODE_SHIPPED.equals(param.shpgModeCd.getValue())
                && !MODE_INSTALLED.equals(param.shpgModeCd.getValue())) {

            return;
        }

        SHPG_PLNTMsg setShpgPlnMsg = searchShpgPlnFindByKeyForUpdate(msgMap, shpgPlnMsg.setShpgPlnNum.getValue());

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        if (MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue())
                || MODE_POCANCELLED.equals(param.shpgModeCd.getValue())) {

            if (SHPG_STS.S_OR_O_PRINTED.equals(setShpgPlnMsg.shpgStsCd.getValue())) {

                setShpgPlnMsg.shpgStsCd.setValue(SHPG_STS.S_OR_O_CANCELLED);
                setShpgPlnMsg.pddDt.clear();
                setShpgPlnMsg.psdDt.clear();
            }

        } else if (MODE_INSHED.equals(param.shpgModeCd.getValue())) {

            // -----------------------------------------------
            // Gets SQLQuery by status.
            // -----------------------------------------------
            Map<String, String> sqlQueryKey = getSqlQueryKeyByStatus(setShpgPlnMsg, SHPG_STS.INSHED);

            // -----------------------------------------------
            // The quantity to divide as a set is calculated.
            // -----------------------------------------------
            Map<String, Object> compQtyMap = setPossibleQty(sqlQueryKey, param.glblCmpyCd.getValue(), msgMap);
            if (compQtyMap == null || compQtyMap.isEmpty()) {
                return;
            }

            BigDecimal setAvalDivideQty = (BigDecimal) compQtyMap.get(SET_AVAL_DIVIDE_QTY);
            if (setAvalDivideQty == null || setAvalDivideQty.compareTo(BigDecimal.ZERO) == 0) {
                return;
            }

            if (setAvalDivideQty.compareTo(setShpgPlnMsg.ordQty.getValue()) < 0) {
                return;

            } else {
                // If the status of all the component items is
                // 'Inshed', the status of the set item is updated.

                setShpgPlnMsg.shpgStsCd.setValue(SHPG_STS.INSHED);
            }
        } else if (MODE_PRINTED_SOCREATING.equals(param.shpgModeCd.getValue())) {

            // -----------------------------------------------
            // Gets SQLQuery by status.
            // -----------------------------------------------
            Map<String, String> sqlQueryKey = getSqlQueryKeyByStatus(setShpgPlnMsg, SHPG_STS.S_OR_O_PRINTED);

            // -----------------------------------------------
            // The quantity to divide as a set is calculated.
            // -----------------------------------------------
            Map<String, Object> compQtyMap = setPossibleQty(sqlQueryKey, param.glblCmpyCd.getValue(), msgMap);
            if (compQtyMap == null || compQtyMap.isEmpty()) {
                return;
            }

            BigDecimal setAvalDivideQty = (BigDecimal) compQtyMap.get(SET_AVAL_DIVIDE_QTY);
            if (setAvalDivideQty == null || setAvalDivideQty.compareTo(BigDecimal.ZERO) == 0) {
                return;
            }

            if (setAvalDivideQty.compareTo(setShpgPlnMsg.ordQty.getValue()) < 0) {
                return;

            } else {
                // If the status of all the component items is 'SO
                // Printed', the status of the set item is updated.
                ZYPEZDItemValueSetter.setValue(setShpgPlnMsg.psdDt, getCompPsdDt(setShpgPlnMsg));
                ZYPEZDItemValueSetter.setValue(setShpgPlnMsg.pddDt, getCompPddDt(setShpgPlnMsg));

                setShpgPlnMsg.shpgStsCd.setValue(SHPG_STS.S_OR_O_PRINTED);
            }
        } else if (MODE_SHIPPED.equals(param.shpgModeCd.getValue())) {

            // -----------------------------------------------
            // Gets SQLQuery by status.
            // -----------------------------------------------
            Map<String, String> sqlQueryKey = getSqlQueryKeyByStatus(setShpgPlnMsg, SHPG_STS.SHIPPED);

            // -----------------------------------------------
            // The quantity to divide as a set is calculated.
            // -----------------------------------------------
            Map<String, Object> compQtyMap = setPossibleQty(sqlQueryKey, param.glblCmpyCd.getValue(), msgMap);
            if (compQtyMap == null || compQtyMap.isEmpty()) {
                return;
            }

            BigDecimal setAvalDivideQty = (BigDecimal) compQtyMap.get(SET_AVAL_DIVIDE_QTY);
            if (setAvalDivideQty == null || setAvalDivideQty.compareTo(BigDecimal.ZERO) == 0) {
                return;
            }

            if (setAvalDivideQty.compareTo(setShpgPlnMsg.ordQty.getValue()) < 0) {
                return;

            } else {
                // If the status of all the component items is
                // 'Shipped', the status of the set item is updated.

                setShpgPlnMsg.shpgStsCd.setValue(SHPG_STS.SHIPPED);
            }
        } else if (MODE_INSTALLED.equals(param.shpgModeCd.getValue())) {

            // -----------------------------------------------
            // Gets SQLQuery by status.
            // -----------------------------------------------
            Map<String, String> sqlQueryKey = getSqlQueryKeyByStatus(setShpgPlnMsg, SHPG_STS.INSTALLED);

            // -----------------------------------------------
            // The quantity to divide as a set is calculated.
            // -----------------------------------------------
            Map<String, Object> compQtyMap = setPossibleQty(sqlQueryKey, param.glblCmpyCd.getValue(), msgMap);
            if (compQtyMap == null || compQtyMap.isEmpty()) {
                return;
            }

            BigDecimal setAvalDivideQty = (BigDecimal) compQtyMap.get(SET_AVAL_DIVIDE_QTY);
            if (setAvalDivideQty == null || setAvalDivideQty.compareTo(BigDecimal.ZERO) == 0) {
                return;
            }

            if (setAvalDivideQty.compareTo(setShpgPlnMsg.ordQty.getValue()) < 0) {
                return;

            } else {
                // If the status of all the component items is
                // 'Installed', the status of the set item is updated.

                setShpgPlnMsg.shpgStsCd.setValue(SHPG_STS.INSTALLED);
            }
        } else {
            return;
        }

        updShpgPln(msgMap, setShpgPlnMsg, onBatchType);

    }

    private void setCompDivideProcess(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsg, ONBATCH_TYPE onBatchType) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        // RegularItem none
        if (!ZYPCommonFunc.hasValue(shpgPlnMsg.setMdseCd) && ZYPCommonFunc.hasValue(shpgPlnMsg.setShpgPlnNum)) {
            return;
        }

        String trxSrcTpCd = shpgPlnMsg.trxSrcTpCd.getValue();
        String trxHdrNum = shpgPlnMsg.trxHdrNum.getValue();
        String trxLineNum = shpgPlnMsg.trxLineNum.getValue();
        String setShpgPlnNum = shpgPlnMsg.setShpgPlnNum.getValue();

        // -----------------------------------------------
        // Gets SQLQuery of each mode.
        // -----------------------------------------------
        Map<String, String> sqlQuerykey = getSqlQueryKeyEachMode(param.shpgModeCd.getValue(), param.glblCmpyCd.getValue(), trxSrcTpCd, trxHdrNum, trxLineNum, setShpgPlnNum);

        // -----------------------------------------------
        // The quantity to divide as a set is calculated.
        // -----------------------------------------------
        Map<String, Object> compQtyMap = setPossibleQty(sqlQuerykey, param.glblCmpyCd.getValue(), msgMap);
        if (compQtyMap == null || compQtyMap.isEmpty()) {
            return;
        }

        BigDecimal setAvalDivideQty = (BigDecimal) compQtyMap.get(SET_AVAL_DIVIDE_QTY);
        if (setAvalDivideQty == null || setAvalDivideQty.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }

        // ---------------------------------------------
        // SetItem Divide/Update Process
        // ---------------------------------------------
        // Get SetItem SHPG_PLN
        SHPG_PLNTMsg setShpgPlnMsg = searchShpgPlnFindByKeyForUpdate(msgMap, setShpgPlnNum);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // If status has already been updated, nothing is done.
        if (chkShpgStatus(param.shpgModeCd.getValue(), setShpgPlnMsg.shpgStsCd.getValue())) {
            return;
        }

        String divideSetShpgPlnNum = "";

        if (setAvalDivideQty.compareTo(setShpgPlnMsg.ordQty.getValue()) < 0) {
            // When division is necessary, the data of SetItem is divided.

            // ------------------------
            // Divide SetItem
            // ------------------------
            // SHPG_PLN
            divideSetShpgPlnNum = setCompShpgPlnDivideProcess(spUpdModeMsg, msgMap, setShpgPlnMsg, setAvalDivideQty, null, onBatchType);
            if (divideSetShpgPlnNum == null) {
                return;
            }

            if (!TRX_SRC_TP.RETAIL.equals(setShpgPlnMsg.trxSrcTpCd.getValue())) {
                // PRC_DTL
                setCompDividePrcDtl(msgMap, setShpgPlnMsg.shpgPlnNum.getValue(), divideSetShpgPlnNum, setAvalDivideQty);
            }

            // HLD
            // none

            // SOFT_ALLOC
            setCompDivideSoftAlloc(msgMap, setShpgPlnMsg.shpgPlnNum.getValue(), divideSetShpgPlnNum, setAvalDivideQty);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

        } else {
            // Only status is updated.

            String shpgStatus = getShpgStatusCd(spUpdModeMsg, setShpgPlnMsg);
            if (shpgStatus == null) {
                return;
            }
            setShpgPlnMsg.shpgStsCd.setValue(shpgStatus);

            if (MODE_PRINTED_SOCREATING.equals(spUpdModeMsg.shpgPlnUpdModeCd.getValue())) {
                // S/O Creating -->set PSD/PDD

                ZYPEZDItemValueSetter.setValue(setShpgPlnMsg.psdDt, getCompPsdDt(setShpgPlnMsg));
                ZYPEZDItemValueSetter.setValue(setShpgPlnMsg.pddDt, getCompPddDt(setShpgPlnMsg));
            }

            updShpgPln(msgMap, setShpgPlnMsg, onBatchType);
            return;
        }

        // ---------------------------------------------
        // ComponentItem Divide/Update Process
        // ---------------------------------------------
        String beforelineSubNum = "";
        BigDecimal eachCompTotalQty = BigDecimal.ZERO;

        List<Map<String, Object>> shpgPlnEachCompList = getShpgPlnEachComp(sqlQuerykey);
        for (Map<String, Object> shpgPlnEachComp : shpgPlnEachCompList) {

            String lineSubNum = (String) shpgPlnEachComp.get("TRX_LINE_SUB_NUM");
            String compShpgPlnNum = (String) shpgPlnEachComp.get("SHPG_PLN_NUM");
            BigDecimal ordQty = (BigDecimal) shpgPlnEachComp.get("ORD_QTY");

            if (!beforelineSubNum.equals(lineSubNum)) {
                beforelineSubNum = lineSubNum;
                // ---------------------------------------------
                // eachCompTotalQty = Amount of each component that can be divided.
                // ---------------------------------------------
                BigDecimal eachCompQty = (BigDecimal) compQtyMap.get(lineSubNum);
                if (eachCompQty == null) {
                    return;
                }
                eachCompTotalQty = eachCompQty.multiply(setAvalDivideQty);
            }

            if (BigDecimal.ZERO.compareTo(eachCompTotalQty) == 0) {
                continue;
            }

            SHPG_PLNTMsg origCompShpgPlnMsg = searchShpgPlnFindByKeyForUpdate(msgMap, compShpgPlnNum);

            if (eachCompTotalQty.compareTo(ordQty) < 0) {
                // eachCompTotalQty < OrdQty --> divide

                // SHPG_PLN
                String divideCompShpgPlnNum = setCompShpgPlnDivideProcess(spUpdModeMsg, msgMap, origCompShpgPlnMsg, eachCompTotalQty, divideSetShpgPlnNum, onBatchType);
                if (divideCompShpgPlnNum == null) {
                    return;
                }

                if (!TRX_SRC_TP.RETAIL.equals(setShpgPlnMsg.trxSrcTpCd.getValue())) {
                    // PRC_DTL
                    setCompDividePrcDtl(msgMap, origCompShpgPlnMsg.shpgPlnNum.getValue(), divideCompShpgPlnNum, eachCompTotalQty);
                }

                // Hold
                // none

                // SOFT_ALLOC
                setCompDivideSoftAlloc(msgMap, origCompShpgPlnMsg.shpgPlnNum.getValue(), divideCompShpgPlnNum, eachCompTotalQty);

                // HARD_ALLOC
                setCompDivideHardAlloc(msgMap, origCompShpgPlnMsg.shpgPlnNum.getValue(), divideCompShpgPlnNum, eachCompTotalQty);

                eachCompTotalQty = BigDecimal.ZERO;

            } else {

                origCompShpgPlnMsg.setShpgPlnNum.setValue(divideSetShpgPlnNum);
                S21FastTBLAccessor.update(origCompShpgPlnMsg);

                if (eachCompTotalQty.compareTo(ordQty) == 0) {
                    eachCompTotalQty = BigDecimal.ZERO;
                } else {
                    eachCompTotalQty = eachCompTotalQty.subtract(ordQty);
                }
            }
        }
    }

    private String setCompShpgPlnDivideProcess(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, SHPG_PLNTMsg origShpgPlnMsg, final BigDecimal divideQty, final String divideSetShpgPlnNum, ONBATCH_TYPE onBatchType) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        SHPG_PLNTMsg divideShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(origShpgPlnMsg, null, divideShpgPlnMsg, null);
        BigDecimal calcCrChkQty = BigDecimal.ZERO;
        BigDecimal calcavalSoQty = BigDecimal.ZERO;
        BigDecimal calcavalPoQty = BigDecimal.ZERO;

        // -----------------------------
        // Original SHPG_PLN --> Update
        // -----------------------------

        // Amount
        BigDecimal calcQty = origShpgPlnMsg.ordQty.getValue().subtract(divideQty);
        origShpgPlnMsg.ordQty.setValue(calcQty);
        origShpgPlnMsg.spTotDealSlsAmt.setValue(origShpgPlnMsg.spDealUnitPrcAmt.getValue().multiply(calcQty));
        origShpgPlnMsg.spTotDealNetAmt.setValue(origShpgPlnMsg.spDealNetUnitPrcAmt.getValue().multiply(calcQty));
        origShpgPlnMsg.spTotFuncSlsAmt.setValue(origShpgPlnMsg.spFuncUnitPrcAmt.getValue().multiply(calcQty));
        origShpgPlnMsg.spTotFuncNetAmt.setValue(origShpgPlnMsg.spFuncNetUnitPrcAmt.getValue().multiply(calcQty));

        // softAllocQty
        if (origShpgPlnMsg.softAllocQty.getValue().compareTo(BigDecimal.ZERO) > 0) {

            if (origShpgPlnMsg.softAllocQty.getValue().compareTo(divideQty) == 0) {
                // softAllocQty = AllocatedQty
                divideShpgPlnMsg.softAllocQty.setValue(divideQty);
                origShpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);

            } else if (origShpgPlnMsg.softAllocQty.getValue().compareTo(divideQty) > 0) {
                // softAllocQty > AllocatedQty
                divideShpgPlnMsg.softAllocQty.setValue(divideQty);
                origShpgPlnMsg.softAllocQty.setValue(origShpgPlnMsg.softAllocQty.getValue().subtract(divideQty));

            } else {
                // softAllocQty < AllocatedQty
                divideShpgPlnMsg.softAllocQty.setValue(origShpgPlnMsg.softAllocQty.getValue());
                origShpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);
            }
        }

        // crChkQty
        if (origShpgPlnMsg.crChkQty.getValue().compareTo(BigDecimal.ZERO) == 0) {
            origShpgPlnMsg.crChkQty.setValue(BigDecimal.ZERO);
        } else {

            calcCrChkQty = origShpgPlnMsg.crChkQty.getValue().subtract(divideQty);
            if (BigDecimal.ZERO.compareTo(calcCrChkQty) > 0) {
                origShpgPlnMsg.crChkQty.setValue(BigDecimal.ZERO);
            } else {
                origShpgPlnMsg.crChkQty.setValue(calcCrChkQty);
            }
        }

        // avalSoQty
        if (origShpgPlnMsg.avalSoQty.getValue().compareTo(BigDecimal.ZERO) == 0) {
            origShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
        } else {

            calcavalSoQty = origShpgPlnMsg.avalSoQty.getValue().subtract(divideQty);
            if (BigDecimal.ZERO.compareTo(calcavalSoQty) > 0) {
                origShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
            } else {
                origShpgPlnMsg.avalSoQty.setValue(calcavalSoQty);

            }
        }

        // avalPoQty
        if (origShpgPlnMsg.avalPoQty.getValue().compareTo(BigDecimal.ZERO) == 0) {
            origShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
        } else {

            calcavalPoQty = origShpgPlnMsg.avalPoQty.getValue().subtract(divideQty);
            if (BigDecimal.ZERO.compareTo(calcavalPoQty) > 0) {
                origShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
            } else {
                origShpgPlnMsg.avalPoQty.setValue(calcavalPoQty);

            }
        }

        // **** Update
        updShpgPln(msgMap, origShpgPlnMsg, onBatchType);

        // -----------------------------
        // Divide SHPG_PLN --> Insert
        // -----------------------------
        divideShpgPlnMsg.shpgPlnNum.setValue(ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqConstant.SHPG_PLN_SQ, 8));
        divideShpgPlnMsg.shpgPlnDplyLineNum.setValue(getMaxDisplayNumber(param, divideShpgPlnMsg));

        String shpgStatus = getShpgStatusCd(spUpdModeMsg, divideShpgPlnMsg);
        if (shpgStatus == null) {
            return null;
        }
        divideShpgPlnMsg.shpgStsCd.setValue(shpgStatus);

        // Amount
        divideShpgPlnMsg.ordQty.setValue(divideQty);
        divideShpgPlnMsg.spTotDealSlsAmt.setValue(divideShpgPlnMsg.spDealUnitPrcAmt.getValue().multiply(divideQty));
        divideShpgPlnMsg.spTotDealNetAmt.setValue(divideShpgPlnMsg.spDealNetUnitPrcAmt.getValue().multiply(divideQty));
        divideShpgPlnMsg.spTotFuncSlsAmt.setValue(divideShpgPlnMsg.spFuncUnitPrcAmt.getValue().multiply(divideQty));
        divideShpgPlnMsg.spTotFuncNetAmt.setValue(divideShpgPlnMsg.spFuncNetUnitPrcAmt.getValue().multiply(divideQty));

        // crChkQty
        if (origShpgPlnMsg.crChkQty.getValue().compareTo(BigDecimal.ZERO) == 0) {
            divideShpgPlnMsg.crChkQty.setValue(BigDecimal.ZERO);
        } else {

            if (BigDecimal.ZERO.compareTo(calcCrChkQty) == 0) {
                divideShpgPlnMsg.crChkQty.setValue(BigDecimal.ZERO);
            } else {
                divideShpgPlnMsg.crChkQty.setValue(divideQty);
            }
        }

        // avalSoQty
        if (origShpgPlnMsg.avalSoQty.getValue().compareTo(BigDecimal.ZERO) == 0) {
            divideShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
        } else {

            if (BigDecimal.ZERO.compareTo(calcavalSoQty) == 0) {
                divideShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
            } else {
                divideShpgPlnMsg.avalSoQty.setValue(divideQty);
            }
        }

        // avalPoQty
        if (origShpgPlnMsg.avalPoQty.getValue().compareTo(BigDecimal.ZERO) == 0) {
            divideShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
        } else {

            if (BigDecimal.ZERO.compareTo(calcavalPoQty) == 0) {
                divideShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
            } else {
                divideShpgPlnMsg.avalPoQty.setValue(divideQty);
            }
        }

        if (divideSetShpgPlnNum != null) {
            divideShpgPlnMsg.setShpgPlnNum.setValue(divideSetShpgPlnNum);
        }

        // **** Insert
        insertShpgPln(msgMap, divideShpgPlnMsg);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return null;
        }

        return divideShpgPlnMsg.shpgPlnNum.getValue();

    }

    private void setCompDividePrcDtl(S21ApiMessageMap msgMap, final String origShpgPlnNum, final String divideShpgPlnNum, final BigDecimal divideQty) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        PRC_DTLTMsg tMsg = new PRC_DTLTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        tMsg.setConditionValue("shpgPlnNum01", origShpgPlnNum);
        PRC_DTLTMsgArray tMsgArray = (PRC_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
        if (tMsgArray.getValidCount() == 0) {
            msgMap.addXxMsgId(NWZM0202E);
            return;
        }

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {

            PRC_DTLTMsg divPrcDtlMsg = new PRC_DTLTMsg();
            PRC_DTLTMsg prcDtlMsg = (PRC_DTLTMsg) tMsgArray.no(i);
            EZDMsg.copy(prcDtlMsg, null, divPrcDtlMsg, null);

            divPrcDtlMsg.prcDtlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_DTL_SQ));
            divPrcDtlMsg.shpgPlnNum.setValue(divideShpgPlnNum);
            divPrcDtlMsg.shipUnitQty.setValue(divideQty);
            divPrcDtlMsg.dealNetAmt.setValue(divPrcDtlMsg.dealLastNetUnitPrcAmt.getValue().multiply(divideQty));
            divPrcDtlMsg.funcNetAmt.setValue(divPrcDtlMsg.funcLastNetUnitPrcAmt.getValue().multiply(divideQty));
            divPrcDtlMsg.dealDiscAmt.setValue(divPrcDtlMsg.dealPerUnitFixAmt.getValue().multiply(divideQty));
            divPrcDtlMsg.funcDiscAmt.setValue(divPrcDtlMsg.funcPerUnitFixAmt.getValue().multiply(divideQty));

            S21FastTBLAccessor.insert(divPrcDtlMsg);
            if (!RTNCD_NOMAL.equals(divPrcDtlMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM0079E);
                return;
            }

            BigDecimal shipQty = prcDtlMsg.shipUnitQty.getValue().subtract(divideQty);
            prcDtlMsg.shipUnitQty.setValue(shipQty);
            prcDtlMsg.dealNetAmt.setValue(prcDtlMsg.dealLastNetUnitPrcAmt.getValue().multiply(shipQty));
            prcDtlMsg.funcNetAmt.setValue(prcDtlMsg.funcLastNetUnitPrcAmt.getValue().multiply(shipQty));
            prcDtlMsg.dealDiscAmt.setValue(prcDtlMsg.dealPerUnitFixAmt.getValue().multiply(shipQty));
            prcDtlMsg.funcDiscAmt.setValue(prcDtlMsg.funcPerUnitFixAmt.getValue().multiply(shipQty));

            S21FastTBLAccessor.update(prcDtlMsg);
            if (!RTNCD_NOMAL.equals(prcDtlMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM0079E);
                return;
            }
        }
    }

    private void setCompDivideHardAlloc(S21ApiMessageMap msgMap, final String origShpgPlnNum, final String divideShpgPlnNum, final BigDecimal divideQty) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        HARD_ALLOCTMsg tMsg = new HARD_ALLOCTMsg();
        tMsg.setSQLID("009");
        tMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        tMsg.setConditionValue("shpgPlnNum01", origShpgPlnNum);
        HARD_ALLOCTMsgArray tMsgArray = (HARD_ALLOCTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
        if (tMsgArray.getValidCount() == 0) {
            return;
        }

        BigDecimal hardAllocQty = divideQty;

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {

            if (BigDecimal.ZERO.compareTo(hardAllocQty) == 0) {
                break;
            }

            HARD_ALLOCTMsg hardAllocMsg = tMsgArray.no(i);

            if (hardAllocQty.compareTo(hardAllocMsg.hardAllocQty.getValue()) < 0) {

                // Divide
                HARD_ALLOCTMsg divHardAllocMsg = new HARD_ALLOCTMsg();
                EZDMsg.copy(hardAllocMsg, null, divHardAllocMsg, null);

                hardAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);
                hardAllocMsg.hardAllocQty.setValue(hardAllocQty);
                S21FastTBLAccessor.update(hardAllocMsg);

                if (!RTNCD_NOMAL.equals(hardAllocMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0920E);
                    return;
                }

                // Insert
                divHardAllocMsg.hardAllocQty.setValue(divHardAllocMsg.hardAllocQty.getValue().subtract(hardAllocQty));
                divHardAllocMsg.hardAllocPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.HARD_ALLOC_SQ));
                S21FastTBLAccessor.insert(divHardAllocMsg);

                if (!RTNCD_NOMAL.equals(divHardAllocMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0920E);
                    return;
                }

                break;

            } else {

                // Update
                hardAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);
                hardAllocQty = hardAllocQty.subtract(hardAllocMsg.hardAllocQty.getValue());
                S21FastTBLAccessor.update(hardAllocMsg);
                if (!RTNCD_NOMAL.equals(hardAllocMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0920E);
                    return;
                }
            }
        }
    }

    private void setCompDivideSoftAlloc(S21ApiMessageMap msgMap, final String origShpgPlnNum, final String divideShpgPlnNum, final BigDecimal divideQty) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        List<BigDecimal> softAllocPKList = searchSoftAllocation(param.glblCmpyCd.getValue(), origShpgPlnNum);

        if (softAllocPKList.isEmpty()) {
            return;
        }

        BigDecimal softAllocQty = divideQty;
        for (int i = 0; i < softAllocPKList.size(); i++) {

            if (BigDecimal.ZERO.compareTo(softAllocQty) == 0) {
                break;
            }

            SOFT_ALLOCTMsg softAllocMsg = new SOFT_ALLOCTMsg();
            softAllocMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            softAllocMsg.softAllocPk.setValue((BigDecimal) softAllocPKList.get(i));
            softAllocMsg = (SOFT_ALLOCTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(softAllocMsg);
            if (null == softAllocMsg) {
                msgMap.addXxMsgId(NWZM0662E);
                return;
            }

            if (softAllocQty.compareTo(softAllocMsg.softAllocQty.getValue()) < 0) {

                // Divide
                SOFT_ALLOCTMsg divSoftAllocMsg = new SOFT_ALLOCTMsg();
                EZDMsg.copy(softAllocMsg, null, divSoftAllocMsg, null);

                softAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);
                softAllocMsg.softAllocQty.setValue(softAllocQty);
                S21FastTBLAccessor.update(softAllocMsg);

                if (!RTNCD_NOMAL.equals(softAllocMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0662E);
                    return;
                }

                divSoftAllocMsg.softAllocQty.setValue(divSoftAllocMsg.softAllocQty.getValue().subtract(softAllocQty));
                divSoftAllocMsg.softAllocPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SOFT_ALLOC_SQ));
                S21FastTBLAccessor.insert(divSoftAllocMsg);

                if (!RTNCD_NOMAL.equals(divSoftAllocMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0662E);
                    return;
                }

                break;

            } else {

                softAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);
                softAllocQty = softAllocQty.subtract(softAllocMsg.softAllocQty.getValue());
                S21FastTBLAccessor.update(softAllocMsg);
                if (!RTNCD_NOMAL.equals(softAllocMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0075E);
                    return;
                }
            }
        }
    }

    private boolean chkShpgStatus(final String mode, final String shpgStatusCd) {

        if (MODE_PRINTED_SOCREATING.equals(mode)
                || MODE_POPRINTED.equals(mode)) {
            return SHPG_STS.S_OR_O_PRINTED.equals(shpgStatusCd);
        }

        if (MODE_VENDERINVOICERECEIVED.equals(mode)
                || MODE_SHIPPED.equals(mode)) {
            return SHPG_STS.SHIPPED.equals(shpgStatusCd);
        }

        return true;
    }

    private String getShpgStatusCd(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, SHPG_PLNTMsg shpgPlnMsg) {

        if (!SET_LINE_SUB_NUM.equals(shpgPlnMsg.trxLineSubNum.getValue())) {
            return shpgPlnMsg.shpgStsCd.getValue();
        }

        if (MODE_POPRINTED.equals(spUpdModeMsg.shpgPlnUpdModeCd.getValue())) {
            return SHPG_STS.S_OR_O_PRINTED;

        } else if (MODE_POCANCELLED.equals(spUpdModeMsg.shpgPlnUpdModeCd.getValue())) {
            return SHPG_STS.S_OR_O_CANCELLED;

        } else {
            return spUpdModeMsg.shpgStsCd.getValue();
        }
    }

    private Map<String, Object> setPossibleQty(final Map<String, String> querykey, final String glblCmpyCd, S21ApiMessageMap msgMap) {

        // ---------------------------------------------------------
        // The amount that can be divided as a set is calculated.
        // ---------------------------------------------------------

        Map<String, Object> compQtyList = new HashMap<String, Object>();

        List<Map<String, Object>> eachCompTotalQtyList = getEachCompTotalQty(querykey);

        if ((eachCompTotalQtyList == null || eachCompTotalQtyList.isEmpty())) {
            compQtyList.put(SET_AVAL_DIVIDE_QTY, BigDecimal.ZERO);
            return compQtyList;
        }

        BigDecimal setPossibleQty = null;
        for (Map<String, Object> cpoDtlComp : eachCompTotalQtyList) {

            BigDecimal totalCompQty = (BigDecimal) cpoDtlComp.get("SUM_ORD_QTY");

            if (BigDecimal.ZERO.compareTo(totalCompQty) == 0) {
                compQtyList.put(SET_AVAL_DIVIDE_QTY, BigDecimal.ZERO);
                return compQtyList;
            }

            String lineSubNum = (String) cpoDtlComp.get("CPO_DTL_LINE_SUB_NUM");
            // 2015/09/10 CNA(2.8 Allocation - SO/PO) Delete Start
//            String mdseCd = (String) cpoDtlComp.get("MDSE_CD");
//            String setMdseCd = (String) cpoDtlComp.get("SET_MDSE_CD");
//            String ts = (String) cpoDtlComp.get("CPO_ORD_TS");
            // 2015/09/10 CNA(2.8 Allocation - SO/PO) Delete End
            // 2015/09/10 CNA(2.8 Allocation - SO/PO) Update Start
//            BigDecimal compQty = getCompQty(glblCmpyCd, mdseCd, setMdseCd, ts);
            String cpoOrdNum = (String) cpoDtlComp.get("CPO_ORD_NUM");
            String cpoDtlLineNum = (String) cpoDtlComp.get("CPO_DTL_LINE_NUM");
            BigDecimal compQty = NWXChildMdseQtyByCpoDtlThreadLocalCache.getInstance().getChildMdseQtyFromCpoDtl(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, lineSubNum);
            // 2015/09/10 CNA(2.8 Allocation - SO/PO) Update End

            if (BigDecimal.ZERO.compareTo(compQty) == 0) {
                msgMap.addXxMsgId(NWZM0222E);
                return null;
            }

            BigDecimal calcSetPossibleQty = totalCompQty.divide(compQty, BigDecimal.ROUND_DOWN);

            if (BigDecimal.ZERO.compareTo(calcSetPossibleQty) > 0) {
                // calcSetPossibleQty <=0
                compQtyList.put(SET_AVAL_DIVIDE_QTY, BigDecimal.ZERO);
                return compQtyList;
            } else if (BigDecimal.ZERO.compareTo(calcSetPossibleQty) == 0) {
                compQtyList.put(SET_AVAL_DIVIDE_QTY, BigDecimal.ZERO);
                return compQtyList;
            }

            if (setPossibleQty == null) {
                setPossibleQty = calcSetPossibleQty;
            } else if (setPossibleQty.compareTo(calcSetPossibleQty) > 0) {
                // setPossibleQty < calcSetPossibleQty
                setPossibleQty = calcSetPossibleQty;
            }
            compQtyList.put(lineSubNum, compQty);
        }

        compQtyList.put(SET_AVAL_DIVIDE_QTY, setPossibleQty);

        return compQtyList;

    }
    // 2015/09/10 CNA(2.8 Allocation - SO/PO) Delete Start
//    private BigDecimal getCompQty(final String glblCmpyCd, final String childMdseCd, final String prntMdseCd, final String ordTs) {
//
//        CMPSNTMsg cmpsnMsg = NWXCmpsnTMsgThreadLocalCache.getInstance().get(glblCmpyCd, prntMdseCd, childMdseCd, ordTs, ordTs);
//
//        BigDecimal compQty = ZERO;
//        if (cmpsnMsg != null) {
//            compQty = cmpsnMsg.childMdseQty.getValue();
//        }
//
//        return compQty;
//    }
    // 2015/09/10 CNA(2.8 Allocation - SO/PO) Delete End

    private Map<String, String> getSqlQueryKeyEachMode(final String mode, final String glblCmpyCd, final String trxSrcTpCd, final String trxHdrNum, final String trxLineNum, final String setShpgPlnNum) {

        Map<String, String> querykey = new HashMap<String, String>();

        querykey.put("glblCmpyCd", glblCmpyCd);
        querykey.put("trxHdrNum", trxHdrNum);
        querykey.put("trxLineNum", trxLineNum);
        querykey.put("trxSrcTpCd", trxSrcTpCd);
        querykey.put("trxLineNumSet", SET_LINE_SUB_NUM);
        querykey.put("setShpgPlnNum", setShpgPlnNum);
        querykey.put("shpgPlnStsCd", null);

        if (MODE_PRINTED_SOCREATING.equals(mode)
                || MODE_PRINTED_SOCREATED.equals(mode)
                || MODE_POPRINTED.equals(mode)) {

            querykey.put("shpgStsCdVA", SHPG_STS.VALIDATED);
            querykey.put("on", ZYPConstant.FLG_ON_Y);
            querykey.put("off", ZYPConstant.FLG_OFF_N);
            querykey.put("soPrintFlg", ZYPConstant.FLG_ON_Y);
            querykey.put("shipFlg", ZYPConstant.FLG_OFF_N);

        } else if (MODE_VENDERINVOICERECEIVED.equals(mode)
                || MODE_SHIPPED.equals(mode)) {

            querykey.put("shpgStsCdVA", SHPG_STS.VALIDATED);
            querykey.put("on", ZYPConstant.FLG_ON_Y);
            querykey.put("off", ZYPConstant.FLG_OFF_N);
            querykey.put("soPrintFlg", ZYPConstant.FLG_OFF_N);
            querykey.put("shipFlg", ZYPConstant.FLG_ON_Y);

        }

        return querykey;
    }

    private Map<String, String> getSqlQueryKeyByStatus(SHPG_PLNTMsg shpgPlnMsg, final String shpgPlnStsCd) {

        Map<String, String> querykey = new HashMap<String, String>();

        querykey.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
        querykey.put("trxHdrNum", shpgPlnMsg.trxHdrNum.getValue());
        querykey.put("trxLineNum", shpgPlnMsg.trxLineNum.getValue());
        querykey.put("trxSrcTpCd", shpgPlnMsg.trxSrcTpCd.getValue());
        querykey.put("trxLineNumSet", SET_LINE_SUB_NUM);
        querykey.put("setShpgPlnNum", shpgPlnMsg.shpgPlnNum.getValue());
        querykey.put("shpgPlnStsCd", shpgPlnStsCd);
        querykey.put("shpgStsCdVA", SHPG_STS.VALIDATED);
        querykey.put("off", ZYPConstant.FLG_OFF_N);

        return querykey;
    }

    private List<Map<String, Object>> getEachCompTotalQty(final Map<String, String> querykey) {

        if (null == querykey.get("shpgPlnStsCd")) {

            return (List) this.ssmBatchClient.queryObjectList("queryEachCompTotalQty", querykey);

        } else {

            return (List) this.ssmBatchClient.queryObjectList("queryEachCompTotalQtyByStatus", querykey);

        }
    }

    private List<Map<String, Object>> getShpgPlnEachComp(final Map<String, String> querykey) {

        return (List) this.ssmBatchClient.queryObjectList("queryShpgPlnEachComp", querykey);

    }

    private String getCompPsdDt(SHPG_PLNTMsg shpgPlnMsg) {

        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
        key.put("trxHdrNum", shpgPlnMsg.trxHdrNum.getValue());
        key.put("trxLineNum", shpgPlnMsg.trxLineNum.getValue());
        key.put("trxSrcTpCd", shpgPlnMsg.trxSrcTpCd.getValue());
        key.put("setSHpgPlnNum", shpgPlnMsg.shpgPlnNum.getValue());

        List<String> compPsdDtList = (List) this.ssmBatchClient.queryObjectList("queryPsdDt", key);

        if (compPsdDtList == null || compPsdDtList.isEmpty()) {
            return "";
        }

        return compPsdDtList.get(0);
    }

    private String getCompPddDt(SHPG_PLNTMsg shpgPlnMsg) {

        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
        key.put("trxHdrNum", shpgPlnMsg.trxHdrNum.getValue());
        key.put("trxLineNum", shpgPlnMsg.trxLineNum.getValue());
        key.put("trxSrcTpCd", shpgPlnMsg.trxSrcTpCd.getValue());
        key.put("setSHpgPlnNum", shpgPlnMsg.shpgPlnNum.getValue());

        List<String> compPddDtList = (List) this.ssmBatchClient.queryObjectList("queryPddDt", key);

        if (compPddDtList == null || compPddDtList.isEmpty()) {
            return "";
        }

        return compPddDtList.get(0);
    }

    private Map<String, Object> getSoNumInfo(final String glblCmpyCd, final String soNum, final String shpgPlnNum) {

        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", glblCmpyCd);
        key.put("soNum", soNum);
        key.put("shpgPlnNum", shpgPlnNum);

        List<Map<String, Object>> soNumInfoList = (List) this.ssmBatchClient.queryObjectList("getSoNumInfo", key);

        if (soNumInfoList == null || soNumInfoList.isEmpty()) {
            return null;
        }

        return soNumInfoList.get(0);
    }

    private SHPG_PLNTMsg searchShpgPlnFindByKeyForUpdate(S21ApiMessageMap msgMap, final String shpgPlnNum) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
        tMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        tMsg.shpgPlnNum.setValue(shpgPlnNum);
        tMsg = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(tMsg);

        if (null == tMsg) {
            msgMap.addXxMsgId(NWZM0075E);
            return null;
        }

        return tMsg;

    }

    private void updShpgPln(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsg, ONBATCH_TYPE onBatchType) {

        S21FastTBLAccessor.update(shpgPlnMsg);
        if (!RTNCD_NOMAL.equals(shpgPlnMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0078E);
        }

        // 2017/12/07 QC#22930 Add Start
        // call Display Order Status Update API
        List<String> shpgPlnNumList = new ArrayList<String>();
        shpgPlnNumList.add(shpgPlnMsg.shpgPlnNum.getValue());
        callDplyOrdStsUpdApi(msgMap, shpgPlnNumList, onBatchType, shpgPlnMsg.trxHdrNum.getValue());
        // 2017/12/07 QC#22930 Add End
    }

    private void insertShpgPln(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsg) {

        S21FastTBLAccessor.insert(shpgPlnMsg);
        if (!RTNCD_NOMAL.equals(shpgPlnMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0078E);
        }

    }

    private BigDecimal calcDealFrtAmt(SHPG_PLNTMsg shpgPln) {

        int scale = getStdCcyDigit(shpgPln.glblCmpyCd.getValue(), shpgPln.ccyCd.getValue());
        BigDecimal exchRate = shpgPln.exchRate.getValue();
        String acctArthTpCd = getAcctArthTpCd(shpgPln.glblCmpyCd.getValue(), shpgPln.ccyCd.getValue());

        BigDecimal funcFrtAmt = shpgPln.spTotFuncFrtAmt.getValue();
        BigDecimal dealFrtAmt;

        if (ACCT_ARTH_TP.MULTIPLY.equals(acctArthTpCd)) {
            dealFrtAmt = divide(funcFrtAmt, exchRate, scale, HALF_UP);
        } else {
            dealFrtAmt = multiply(funcFrtAmt, exchRate, scale, HALF_UP);
        }

        return dealFrtAmt;
    }

    private Integer getStdCcyDigit(String glblCmpyCd, String ccyCd) {

        final String cacheKey = ccyCd;

        Integer stdCcyDigit = stdCcyDigitCache.get(cacheKey);

        if (stdCcyDigit == null) {

            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("ccyCd", ccyCd);

            stdCcyDigit = (Integer) this.ssmBatchClient.queryObject("getStdCcyDigit", ssmParam);
            if (stdCcyDigit != null) {
                stdCcyDigitCache.put(ccyCd, stdCcyDigit);
            }
        }

        return stdCcyDigit;
    }

    private String getAcctArthTpCd(String glblCmpyCd, String ccyCd) {

        CCYTMsg tMsg = new CCYTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.ccyCd.setValue(ccyCd);
        tMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.acctArthTpCd.getValue();
        }

        return null;
    }

    private static BigDecimal divide(BigDecimal bc1, BigDecimal bc2, int scale, RoundingMode roundingMode) {

        if (bc1 == null || bc2 == null) {
            return ZERO;
        }
        if (bc1.compareTo(ZERO) == 0 || bc2.compareTo(ZERO) == 0) {
            return ZERO;
        }
        return bc1.divide(bc2, scale, roundingMode);
    }

    private static BigDecimal multiply(BigDecimal bc1, BigDecimal bc2, int scale, RoundingMode roundingMode) {

        if (bc1 == null || bc2 == null) {
            return ZERO;
        }
        return bc1.multiply(bc2).setScale(scale, roundingMode);
    }

// START ADD 2013/04/22 S.Yamamoto [094]
    private void chkDate(NWZC003001PMsg param, S21ApiMessageMap msgMap) {

        if (MODE_PSD_PDD_RSD_RDD_UPDATE.equals(param.shpgModeCd.getValue())) {

            final boolean isInputPsdDate = ZYPCommonFunc.hasValue(param.psdDt);
            final boolean isInputPddDate = ZYPCommonFunc.hasValue(param.pddDt);
            final boolean isInputRsdDate = ZYPCommonFunc.hasValue(param.rsdDt);
            final boolean isInputRddDate = ZYPCommonFunc.hasValue(param.rddDt);

            if (!isInputPsdDate && !isInputPddDate && !isInputRsdDate && !isInputRddDate) {
                msgMap.addXxMsgId(NWZM0238E);
            }
        }
    }

    private void installed(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [installed] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // ===================================================================
        SHPG_PLNTMsgArray shpgPlnMsgArray = new SHPG_PLNTMsgArray();
        searchShippingPlan(null, shpgPlnMsgArray, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        List<Integer> inTangibleIdx = new ArrayList<Integer>();
        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(shpgPlnMsgArray.no(i).invtyLocCd)) {
                inTangibleIdx.add(i);
            }
        }
        if (!inTangibleIdx.isEmpty()) {
            ZYPTableUtil.deleteRows(shpgPlnMsgArray, inTangibleIdx);
        }

        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {

            // ===================================================================
            // (2)Search CPO Detail（select for update）
            // ===================================================================
            // none

            // ===================================================================
            // (3)Search Shipping Plan（select for update）
            // ===================================================================
            searchShippingPlanForUpdate(shpgPlnMsgArray.no(i), null, msgMap);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {

            chkStatus(shpgPlnMsgArray.no(i), msgMap);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {
            SHPG_PLNTMsg shpgPlnMsg = (SHPG_PLNTMsg) shpgPlnMsgArray.no(i);
            SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
            EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
            updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // ===================================================================
            // (7)Divides Shipping Plan
            // (8)Update Divides Shipping Plan
            // (9)Check Update Shipping Plan
            // (10)Divides Pricing Detail
            // (11)Divides Soft Allocation
            // (12)Update Shipping Plan(Soft Allocation Quantity)
            // (13)Update CPO Detail
            // ===================================================================
            // none

            // ===================================================================
            // (14)Business Process Log
            // ===================================================================
            outProcessLog(param, EVENTID_INSTALLED, shpgPlnMsg);

            // ===================================================================
            // (15)Process Hold
            // (16)Update Hold
            // (17)Divides Hard Allocation
            // (18)Divides SetComponent Divide
            // ===================================================================
            // none
        }

        // ===================================================================
        // (19)Update Set Shipping Plan
        // ===================================================================
        updSetShpgPlnSts(msgMap, shpgPlnMsgArray.no(0), onBatchType);

        debug("-- MODE [installed] END --");
    }
// END   ADD 2013/04/22 S.Yamamoto [094]

    // START ADD S.Yamamoto [OM053]
    private boolean isUpdateFreightAmt(SHPG_PLNTMsg updShpgPlnMsg) {

        String actlFrtFlg = (String) this.ssmBatchClient.queryObject("getActualFreightFlag", updShpgPlnMsg);
        if (ZYPConstant.FLG_ON_Y.equals(actlFrtFlg)) {
            return true;
        }

        return false;
    }
    // End ADD S.Yamamoto [OM053]

    // 2015/09/10 CNA(2.8 Allocation - SO/PO) Add Start
    private void applyAllocationCancel(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsg, ONBATCH_TYPE onBatchType) {

        // 2016/06/16 S.Yamamoto S21_NA#6844 Start
        /***************************************************************************
         * Execute auto allocation cancel target Transaction Source Type
         ***************************************************************************/
        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        String autoAllocCancelTrxSrcTp = ZYPCodeDataUtil.getVarCharConstValue(AUTO_ALLOC_CANC_TRX_SRC_TP, param.glblCmpyCd.getValue());
        List<String> autoAllocCancelTrxSrcTpList = S21StringUtil.toList(autoAllocCancelTrxSrcTp);
        if (!autoAllocCancelTrxSrcTpList.contains(shpgPlnMsg.trxSrcTpCd.getValue())) {
            return;
        }
        // 2016/06/16 S.Yamamoto S21_NA#6844 End

        final List<String> delShplgPlnNumList = new ArrayList<String>();
        // update shipping plan validated
        final Map<String, Object> newShpgPlnNum = updateShpgPlnValidated(msgMap, shpgPlnMsg, delShplgPlnNumList, onBatchType);
        if ("E".equals(getMessageKind(msgMap))) {
            return;
        }

        if (newShpgPlnNum == null) {
            return;
        }

        // delete price deteil validated
        updPrcDtl(msgMap, delShplgPlnNumList, newShpgPlnNum, onBatchType);
        if ("E".equals(getMessageKind(msgMap))) {
            return;
        }

        // delete hold
        deleteHld(msgMap, delShplgPlnNumList, onBatchType);

        /***************************************************************************
         * call [NWZC102001] : Allocation API by 'Cancel'
         ***************************************************************************/
        final NWZC102001 allocAPI = new NWZC102001();
        callAllocApiByCancel(allocAPI, msgMap, shpgPlnMsg, onBatchType);
        if ("E".equals(getMessageKind(msgMap))) {
            return;
        }

        // QC#21821 ADD START
        if (MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue())) {
            // 2017/11/27 S21_NA#22785 Mod Start
            // call Display Order Status Update API
            List<String> shpgPlnNumList = new ArrayList<String>();
            shpgPlnNumList.add((String) newShpgPlnNum.get("NEW_SHPG_PLN_NUM"));
            callDplyOrdStsUpdApi(msgMap, shpgPlnNumList, onBatchType, shpgPlnMsg.trxHdrNum.getValue());
            // 2017/11/27 S21_NA#22785 Mod End
            return;
        }
        // QC#21821 ADD END

        /***************************************************************************
         * call [NWZC004001] : Validation Process Manager API by 'Recalculation'
         ***************************************************************************/
        NWZC004001 validProcMgrAPI = new NWZC004001();
        callValidMgrApi(validProcMgrAPI, msgMap, newShpgPlnNum, onBatchType);
        if ("E".equals(getMessageKind(msgMap))) {
            return;
        }
    }
    private Map<String, Object> updateShpgPlnValidated(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsg, List<String> delShplgPlnNumLst, ONBATCH_TYPE onBatchType) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // search shipping plan (H-table)
        List<SHPG_PLNTMsg>getShpgPlnLst = getToBeValidateShpgPln(shpgPlnMsg);
        SHPG_PLNTMsg edtShpgPlnRec = new SHPG_PLNTMsg();
        boolean validatedFlg = false;
        String delShpgPlnNum = null;
        int wkSoftAllocQty = 0;
        int wkOrdQty = 0;
        // START 2013/2/1 A.Suda [WDS Defect# 540]
        BigDecimal wkSpTotDealFrtAmt = BigDecimal.ZERO;
        BigDecimal wkSpTotFuncFrtAmt = BigDecimal.ZERO;
        // END 2013/2/1 A.Suda [WDS Defect# 540]

        // insert info set
        Map<String, Object> wkNewShpgRec = new HashMap<String, Object>();

        Map<String, Object> setShpgPlnNumMap = new HashMap<String, Object>();
        String newSetShpgPlnNum = null;

        if (!getShpgPlnLst.isEmpty()) {

            for (SHPG_PLNTMsg getShpgPln : getShpgPlnLst) {
                String wkShpgStsCd = getShpgPln.shpgStsCd.getValue();

                if (S21StringUtil.isEquals(SHPG_STS.VALIDATED, wkShpgStsCd)) {
                    // copy shpg_pln
                    EZDMsg.copy(getShpgPln, null, edtShpgPlnRec, null);
                    // insert info set
                    wkNewShpgRec.put("DEL_ORIG_SHPG_PLN_NUM", getShpgPln.shpgPlnNum.getValue());
                    delShpgPlnNum = getShpgPln.shpgPlnNum.getValue();

                    // validated flag true
                    validatedFlg = true;

                    // ship_pln lock
                    if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
                        S21FastTBLAccessor.findByKeyForUpdateNoWait(getShpgPln);
                    } else {
                        S21FastTBLAccessor.findByKeyForUpdateAPI(getShpgPln);
                    }

                    // logicalRemove shpg_pln
                    EZDTBLAccessor.logicalRemove(getShpgPln);

                    // SoftAllocQty and ORD QTY save
                    wkSoftAllocQty += getShpgPln.softAllocQty.getValueInt();
                    wkOrdQty += getShpgPln.ordQty.getValueInt();

                    // START 2013/2/1 A.Suda [WDS Defect# 540]
                    wkSpTotDealFrtAmt = wkSpTotDealFrtAmt.add(getShpgPln.spTotDealFrtAmt.getValue());
                    wkSpTotFuncFrtAmt = wkSpTotFuncFrtAmt.add(getShpgPln.spTotFuncFrtAmt.getValue());
                    // END 2013/2/1 A.Suda [WDS Defect# 540]

                    // delete ShpgPlnNum save
                    delShplgPlnNumLst.add(getShpgPln.shpgPlnNum.getValue());
                }

//                if (S21StringUtil.isEquals(SHPG_STS.HARD_ALLOCATED, wkShpgStsCd) || S21StringUtil.isEquals(SHPG_STS.S_OR_O_CANCELLED, wkShpgStsCd)) {
                if (S21StringUtil.isEquals(SHPG_STS.S_OR_O_CANCELLED, wkShpgStsCd)) {

                    if (S21StringUtil.isEquals(getShpgPln.shpgPlnNum.getValue(), shpgPlnMsg.shpgPlnNum.getValue())) {
                        if (validatedFlg == false) {
                            // copy shpg_pln
                            EZDMsg.copy(getShpgPln, null, edtShpgPlnRec, null);

                            // insert info set
                            wkNewShpgRec.put("DEL_ORIG_SHPG_PLN_NUM", getShpgPln.shpgPlnNum.getValue());
                            delShpgPlnNum = getShpgPln.shpgPlnNum.getValue();
                        }
                        // logicalRemove shpg_pln
                        EZDTBLAccessor.logicalRemove(getShpgPln);

                        // SoftAllocQty and ORD QTY save
                        wkSoftAllocQty += getShpgPln.softAllocQty.getValueInt();
                        wkOrdQty += getShpgPln.ordQty.getValueInt();

                        wkSpTotDealFrtAmt = wkSpTotDealFrtAmt.add(getShpgPln.spTotDealFrtAmt.getValue());
                        wkSpTotFuncFrtAmt = wkSpTotFuncFrtAmt.add(getShpgPln.spTotFuncFrtAmt.getValue());

                        // delete ShpgPlnNum save
                        delShplgPlnNumLst.add(getShpgPln.shpgPlnNum.getValue());

                        // set component Process
                        if (hasValue(getShpgPln.setMdseCd) && hasValue(getShpgPln.setShpgPlnNum)) {

                            String setShpgPlnNum = getShpgPln.setShpgPlnNum.getValue();

                            if (setShpgPlnNumMap.containsKey(setShpgPlnNum)) {
                                // none
                                continue;

                            } else {
                                newSetShpgPlnNum = setShpgPlnProcess(msgMap, setShpgPlnNum, onBatchType);
                                if (newSetShpgPlnNum == null) {
                                    return null;
                                }
                                edtShpgPlnRec.setShpgPlnNum.setValue(newSetShpgPlnNum);
                                setShpgPlnNumMap.put(setShpgPlnNum, setShpgPlnNum);
                            }
                        }
                    }
                }
            }

            String wkShpgPlnNum = insertShpgPln(msgMap, shpgPlnMsg, edtShpgPlnRec, wkOrdQty, wkSoftAllocQty, wkSpTotDealFrtAmt, wkSpTotFuncFrtAmt);
            if (wkShpgPlnNum == null) {
                return wkNewShpgRec;
            }

            // set component
            if (hasValue(edtShpgPlnRec.setMdseCd) && hasValue(edtShpgPlnRec.setShpgPlnNum)) {
                //

                List<Map<String, Object>> resultList = checkSetShpgPln(edtShpgPlnRec.glblCmpyCd.getValue(), newSetShpgPlnNum);
                if (resultList != null && !resultList.isEmpty()) {

                    Map map = (Map) resultList.get(0);
                    String invtyDistTp = (String) map.get("INVTY_DIST_TP_CD");
                    BigDecimal setSoftAllocQty = (BigDecimal) map.get("SOFT_ALLOC_QTY");

                    if (S21StringUtil.isEquals(DIST_TP.DISTRIBUTION, invtyDistTp) && ZERO.compareTo(setSoftAllocQty) != 0) {

                        Map<String, BigDecimal> resultSoftAlloc = getSoftAllocTotQty(edtShpgPlnRec);
                        if (resultSoftAlloc != null) {
                            BigDecimal totSoftAllocQty = (BigDecimal) resultSoftAlloc.get("SUM_SOFT_ALLOC_QTY");
                            if (setSoftAllocQty.compareTo(totSoftAllocQty) > 0) {
                                msgMap.addXxMsgId("NWDM0226E");

                            }
                        }
                    }
                }
            }

            // insert info set
            wkNewShpgRec.put("NEW_SHPG_PLN_NUM", wkShpgPlnNum);
            wkNewShpgRec.put("ORD_QTY", new BigDecimal(wkOrdQty));
            // **** Update SOFT_ALLOC
            if (delShpgPlnNum != null) {
                updShpgPlnNumByPartialValue(param.glblCmpyCd.getValue(), delShpgPlnNum, wkShpgPlnNum);
            }

            // re-insert hld
            List<String> hldRsnCdLst = getHldRsnCd(param.glblCmpyCd.getValue());
            if (!hldRsnCdLst.isEmpty()) {
                List<HLDTMsg> hldInfoLst = getHldInfo(param.glblCmpyCd.getValue(), hldRsnCdLst, delShplgPlnNumLst);

                if (!hldInfoLst.isEmpty()) {
                    Set<String> hldRec = new HashSet<String>();

                    for (HLDTMsg hldInfoTMsg : hldInfoLst) {

                        if (hldRec.contains(hldInfoTMsg.hldRsnCd.getValue())) {
                            continue;
                        } else {
                            hldRec.add(hldInfoTMsg.hldRsnCd.getValue());
                        }

                        HLDTMsg insertMsg = new HLDTMsg();

                        EZDMsg.copy((HLDTMsg) hldInfoTMsg, null, insertMsg, null);

                        setValue(insertMsg.hldPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HLD_SQ));
                        setValue(insertMsg.shpgPlnNum, wkShpgPlnNum);
                        setValue(insertMsg.hldQty, edtShpgPlnRec.ordQty);

                        EZDTBLAccessor.insert(insertMsg);
                    }
                }
            }

        } else {
            // illigal case.
            msgMap.addXxMsgId("NWDM0007E");
        }
        return wkNewShpgRec;
    }
    private String setShpgPlnProcess(S21ApiMessageMap msgMap, final String setShpgPlnNum, final ONBATCH_TYPE onBatchType) {
        NWZC003001PMsg pMsg = (NWZC003001PMsg) msgMap.getPmsg();
        // ----------------------------------------------
        // originalShpgPlnMsg -> SetItem to ComponentItem
        //
        // originalShpgPlnMsg.Sts = 'Validated'
        // none
        //
        // originalShpgPlnMsg.Sts <> 'Validated'
        // Search The record of 'Validated'
        // ----------------------------------------------

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        SHPG_PLNTMsg originalSetShpgPlnMsg = getShpgPlnFindByKey(glblCmpyCd, setShpgPlnNum);

        if (originalSetShpgPlnMsg == null) {
            msgMap.addXxMsgId("NWDM0007E");
            return null;
        }

        String createShpgPlnNum = null;

        if (S21StringUtil.isEquals(SHPG_STS.VALIDATED, originalSetShpgPlnMsg.shpgStsCd.getValue())) {

            createShpgPlnNum = setShpgPlnNum;
            // nothing to do

        } else {

            SHPG_PLNTMsgArray vaildSetShpgPlnMsgData = searchShpgPlnValidatedForUpdate(originalSetShpgPlnMsg, onBatchType);

            int setSoftAllocQty = originalSetShpgPlnMsg.softAllocQty.getValueInt();
            int setOrdQty = originalSetShpgPlnMsg.ordQty.getValueInt();

            if (vaildSetShpgPlnMsgData.length() == 0) {
                // Creates it if there is no 'Validaed' status.

                // ----------------------
                // Create Process (Set Item)
                // ----------------------
                createShpgPlnNum = setItemCreateProcess(msgMap, originalSetShpgPlnMsg, setOrdQty, setSoftAllocQty, null, setShpgPlnNum, onBatchType);

                if ("E".equals(getMessageKind(msgMap))) {
                    return null;
                }

            } else if (vaildSetShpgPlnMsgData.length() == 1) {
                // Both are deleted if there are 'Validated' and
                // 'HardAllocated' in the status of the setItem. And
                // Create.

                // ----------------------
                // validSetShplgPln
                // -> It is setItem and status is 'Validated'
                // ----------------------

                SHPG_PLNTMsg validSetShpgPln = vaildSetShpgPlnMsgData.no(0);

                setSoftAllocQty += validSetShpgPln.softAllocQty.getValueInt();
                setOrdQty += validSetShpgPln.ordQty.getValueInt();

                // ----------------------
                // CreateProcess (Set Item)
                // ----------------------
                createShpgPlnNum = setItemCreateProcess(msgMap, validSetShpgPln, setOrdQty, setSoftAllocQty, validSetShpgPln.shpgPlnNum.getValue(), setShpgPlnNum, onBatchType);

                if ("E".equals(getMessageKind(msgMap))) {
                    return null;
                }

                // ----------------------
                // originalSetShpgPlnMsg
                // -> It is setItem and status is 'Hard Allocated'
                // ----------------------

                // ----------------------
                // delete SHPG_PLN
                // ----------------------
                EZDTBLAccessor.logicalRemove(originalSetShpgPlnMsg);

                // ----------------------
                // delete PRC_DTL
                // ----------------------
                deletePrcDtl(msgMap, setShpgPlnNum, onBatchType);

                // ----------------------
                // delete HLD
                // ----------------------
                List<String> delHldShpgPlnList = new ArrayList<String>();
                delHldShpgPlnList.add(setShpgPlnNum);
                deleteHld(msgMap, delHldShpgPlnList, onBatchType);

                // ----------------------
                // update SOFT_ALLOC (Set)
                // ----------------------
                updSetSoftAlloc(msgMap, glblCmpyCd, setShpgPlnNum, createShpgPlnNum, ZYPConstant.FLG_ON_Y, onBatchType);

                if ("E".equals(getMessageKind(msgMap))) {
                    return null;
                }

                // --------------------------
                // update SHPG_PLN
                // (The value of setShpgPlnNum is updated.)
                // --------------------------
                SHPG_PLNTMsgArray shpgPlnMsgRcdList = searchShpgPlnNumForUpdate(originalSetShpgPlnMsg, setShpgPlnNum, onBatchType);

                if (shpgPlnMsgRcdList.getValidCount() > 0) {

                    int updCount = updSetShpgPlnNumByPartialValue(glblCmpyCd, setShpgPlnNum, createShpgPlnNum);

                    if (updCount != shpgPlnMsgRcdList.getValidCount()) {
                        msgMap.addXxMsgId("NWDM0007E");
                        return null;
                    }
                }

            } else {
                msgMap.addXxMsgId("NWDM0007E");
                return null;
            }
        }

        // ReMake
        if (!remakeProcess(msgMap, originalSetShpgPlnMsg, createShpgPlnNum, onBatchType)) {
            return null;
        }

        // update AVAL SO/PO Qty
        // Status is 'Validated' --> 0 is set to SO/PO Qty.
        SHPG_PLNTMsgArray shpgPlnMsgRcdList = searchShpgPlnNumForUpdate(originalSetShpgPlnMsg, createShpgPlnNum, onBatchType);

        if (shpgPlnMsgRcdList.getValidCount() > 0) {

            int updCount = updAvalQtyByPartialValue(glblCmpyCd, createShpgPlnNum);

            if (updCount != shpgPlnMsgRcdList.getValidCount()) {
                msgMap.addXxMsgId("NWDM0007E");
                return null;
            }
        }

        return createShpgPlnNum;

    }
    private String setItemCreateProcess(S21ApiMessageMap msgMap, SHPG_PLNTMsg originalShplgPln, int setOrdQty, int setSoftAllocQty, final String validShpgPlnNum, final String setShpgPlnNum, final ONBATCH_TYPE onBatchType) {

        Map<String, Object> newShplgPlnNum = new HashMap<String, Object>();
        SHPG_PLNTMsg edtShpgPlnRec = new SHPG_PLNTMsg();
        List<String> delShplgPlnNumLst = new ArrayList<String>();
        String newSetShpgPlnNum = null;

        String trxHdrNum = originalShplgPln.trxHdrNum.getValue();
        String trxLineNum = originalShplgPln.trxLineNum.getValue();
        String glblCmpyCd = originalShplgPln.glblCmpyCd.getValue();
        String originalShpgPlnNum = originalShplgPln.shpgPlnNum.getValue();
        // START 2013/2/1 A.Suda [WDS Defect# 540]
        BigDecimal wkSpTotDealFrtAmt = BigDecimal.ZERO;
        BigDecimal wkSpTotFuncFrtAmt = BigDecimal.ZERO;
        // END 2013/2/1 A.Suda [WDS Defect# 540]

        EZDMsg.copy(originalShplgPln, null, edtShpgPlnRec, null);

        // --------------------------
        // logicalRemove SHPG_PLN
        // --------------------------
        EZDTBLAccessor.logicalRemove(originalShplgPln);
        delShplgPlnNumLst.add(originalShpgPlnNum);

        // --------------------------
        // create SHPG_PLN
        // --------------------------
        newSetShpgPlnNum = insertShpgPln(msgMap, originalShplgPln, edtShpgPlnRec, setOrdQty, setSoftAllocQty, wkSpTotDealFrtAmt, wkSpTotFuncFrtAmt);

        if (newSetShpgPlnNum == null) {
            return null;
        }

        newShplgPlnNum.put("DEL_ORIG_SHPG_PLN_NUM", originalShplgPln.shpgPlnNum.getValue());
        newShplgPlnNum.put("NEW_SHPG_PLN_NUM", newSetShpgPlnNum);
        newShplgPlnNum.put("ORD_QTY", new BigDecimal(setOrdQty));

        // --------------------------
        // update PRC_DTL
        // --------------------------
        updPrcDtl(msgMap, delShplgPlnNumLst, newShplgPlnNum, onBatchType);

        // --------------------------
        // update HOLD
        // --------------------------
        updateHoldForCancel(glblCmpyCd, originalShpgPlnNum, newSetShpgPlnNum, setOrdQty, onBatchType);

        // --------------------------
        // update SOFT_ALLOC
        // --------------------------
        updSetSoftAlloc(msgMap, glblCmpyCd, originalShpgPlnNum, newSetShpgPlnNum, null, onBatchType);

        if ("E".equals(getMessageKind(msgMap))) {
            return null;
        }

        // --------------------------
        // CreateProcess VendorDrop (ComponentItem)
        // --------------------------
        compCreateProcess(msgMap, glblCmpyCd, getVendorDrop(glblCmpyCd, trxHdrNum, trxLineNum, setShpgPlnNum), validShpgPlnNum, onBatchType);

        // --------------------------
        // CreateProcess Intangible (ComponentItem)
        // --------------------------
        compCreateProcess(msgMap, glblCmpyCd, getIntangible(glblCmpyCd, trxHdrNum, trxLineNum, setShpgPlnNum), validShpgPlnNum, onBatchType);

        if ("E".equals(getMessageKind(msgMap))) {
            return null;
        }

        // --------------------------
        // update SHPG_PLN
        // (The value of setShpgPlnNum is updated.)
        // --------------------------
        SHPG_PLNTMsgArray shpgPlnMsgRcdList = searchShpgPlnNumForUpdate(edtShpgPlnRec, originalShpgPlnNum, onBatchType);
        if (shpgPlnMsgRcdList.getValidCount() > 0) {

            int updCount = updSetShpgPlnNumByPartialValue(glblCmpyCd, originalShpgPlnNum, newSetShpgPlnNum);

            if (updCount != shpgPlnMsgRcdList.getValidCount()) {
                msgMap.addXxMsgId("NWDM0007E");
                return null;
            }
        }

        return newSetShpgPlnNum;

    }

    private String insertShpgPln(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsg, SHPG_PLNTMsg edtShpgPlnRec, int wkOrdQty, int wkSoftAllocQty, BigDecimal spTotDealFrtAmt, BigDecimal spTotFuncFrtAmt) {
        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // total calculation
        BigDecimal wkSpDealUnitPrcAmt = edtShpgPlnRec.spDealUnitPrcAmt.getValue();
        BigDecimal wkSpDealNetUnitPrcAmt = edtShpgPlnRec.spDealNetUnitPrcAmt.getValue();
        BigDecimal wkSpTotDealSlsAmt = wkSpDealUnitPrcAmt.multiply(new BigDecimal(wkOrdQty));
        BigDecimal wkSpTotDealNetAmt = wkSpDealNetUnitPrcAmt.multiply(new BigDecimal(wkOrdQty));
        BigDecimal wkSpFuncUnitPrcAmt = edtShpgPlnRec.spFuncUnitPrcAmt.getValue();
        BigDecimal wkSpFuncNetUnitPrcAmt = edtShpgPlnRec.spFuncNetUnitPrcAmt.getValue();
        BigDecimal wkSpTotFuncSlsAmt = wkSpFuncUnitPrcAmt.multiply(new BigDecimal(wkOrdQty));
        BigDecimal wkSpTotFuncNetAmt = wkSpFuncNetUnitPrcAmt.multiply(new BigDecimal(wkOrdQty));
        // START 2013/2/1 A.Suda [WDS Defect# 540]
        BigDecimal wkSpTotDealFrtAmt = spTotDealFrtAmt;
        BigDecimal wkSpTotFuncFrtAmt = spTotFuncFrtAmt;
        if (!ZYPCommonFunc.hasValue(wkSpTotDealFrtAmt)) {
            wkSpTotDealFrtAmt = BigDecimal.ZERO;
        }
        if (!ZYPCommonFunc.hasValue(wkSpTotFuncFrtAmt)) {
            wkSpTotFuncFrtAmt = BigDecimal.ZERO;
        }
        // END 2013/2/1 A.Suda [WDS Defect# 540]

        // insert data edit
        String wkShpgPlnNum = this.getShipgPlnSQ();
        setValue(edtShpgPlnRec.glblCmpyCd, param.glblCmpyCd);
        setValue(edtShpgPlnRec.shpgPlnNum, wkShpgPlnNum);

        String shpgPlnDplyLineNum = this.getDplyLineNum(edtShpgPlnRec);
        if (shpgPlnDplyLineNum == null) {
            return null;
        }

        setValue(edtShpgPlnRec.shpgPlnDplyLineNum, shpgPlnDplyLineNum);
        setValue(edtShpgPlnRec.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES);
        setValue(edtShpgPlnRec.shpgStsCd, SHPG_STS.VALIDATED);
        setValue(edtShpgPlnRec.rteStsCd, RTE_STS.UN_ROUTED);
        if (shpgPlnMsg != null) {
            setValue(edtShpgPlnRec.reqShpgSvcLvlCd, shpgPlnMsg.reqShpgSvcLvlCd);
            setValue(edtShpgPlnRec.origShpgSvcLvlCd, shpgPlnMsg.reqShpgSvcLvlCd);
            setValue(edtShpgPlnRec.mdseCd, shpgPlnMsg.mdseCd);
            setValue(edtShpgPlnRec.invtyLocCd, shpgPlnMsg.invtyLocCd);
            // 05/03/2010 Defect 6188 --- add
            setValue(edtShpgPlnRec.reqFrtChrgMethCd, shpgPlnMsg.reqFrtChrgMethCd);
            setValue(edtShpgPlnRec.reqFrtChrgToCd, shpgPlnMsg.reqFrtChrgToCd);
        } else {
            CPO_DTLTMsg cpoDtlMsg = getCpoDtlCache(edtShpgPlnRec);

            if (cpoDtlMsg == null) {
                msgMap.addXxMsgId("NWDM0007E");
                return null;
            }
            setValue(edtShpgPlnRec.reqShpgSvcLvlCd, cpoDtlMsg.shpgSvcLvlCd.getValue());
            setValue(edtShpgPlnRec.origShpgSvcLvlCd, cpoDtlMsg.shpgSvcLvlCd.getValue());
            setValue(edtShpgPlnRec.mdseCd, cpoDtlMsg.mdseCd.getValue());
            setValue(edtShpgPlnRec.invtyLocCd, cpoDtlMsg.invtyLocCd.getValue());
            // 05/03/2010 Defect 6188 --- add
            setValue(edtShpgPlnRec.reqFrtChrgMethCd, cpoDtlMsg.frtChrgMethCd.getValue());
            setValue(edtShpgPlnRec.reqFrtChrgToCd, cpoDtlMsg.frtChrgToCd.getValue());

        }
        setValue(edtShpgPlnRec.spTotDealSlsAmt, wkSpTotDealSlsAmt);
        setValue(edtShpgPlnRec.spTotDealNetAmt, wkSpTotDealNetAmt);
        // START 2013/2/1 A.Suda [WDS Defect# 540]
        setValue(edtShpgPlnRec.spTotDealFrtAmt, wkSpTotDealFrtAmt);
        // END 2013/2/1 A.Suda [WDS Defect# 540]
        setValue(edtShpgPlnRec.spTotFuncSlsAmt, wkSpTotFuncSlsAmt);
        setValue(edtShpgPlnRec.spTotFuncNetAmt, wkSpTotFuncNetAmt);
        // START 2013/2/1 A.Suda [WDS Defect# 540]
        setValue(edtShpgPlnRec.spTotFuncFrtAmt, wkSpTotFuncFrtAmt);
        // END 2013/2/1 A.Suda [WDS Defect# 540]
        setValue(edtShpgPlnRec.avalHardAllocQty, ZERO);
        setValue(edtShpgPlnRec.avalSoftAllocQty, ZERO);
        setValue(edtShpgPlnRec.avalSoQty, ZERO);
        setValue(edtShpgPlnRec.avalPoQty, ZERO);
        edtShpgPlnRec.soNum.clear();
        edtShpgPlnRec.poOrdNum.clear();
        edtShpgPlnRec.thirdPtyInvNum.clear();
        edtShpgPlnRec.actlShpgSvcLvlCd.clear();
        edtShpgPlnRec.actlFrtChrgToCd.clear();
        edtShpgPlnRec.actlFrtChrgMethCd.clear();
        edtShpgPlnRec.carrCd.clear();
        edtShpgPlnRec.carrNm.clear();
        edtShpgPlnRec.bolNum.clear();
        edtShpgPlnRec.proNum.clear();
        edtShpgPlnRec.invNum.clear();
        edtShpgPlnRec.psdDt.clear();
        edtShpgPlnRec.pddDt.clear();
        edtShpgPlnRec.actlShipDt.clear();
        edtShpgPlnRec.actlArvDt.clear();
        edtShpgPlnRec.shipCmntFirstLineTxt.clear();
        edtShpgPlnRec.shipCmntScdLineTxt.clear();
        edtShpgPlnRec.shipCmntThirdLineTxt.clear();
        edtShpgPlnRec.shipCmntFrthLineTxt.clear();
        setValue(edtShpgPlnRec.shipAvalFlg, ZYPConstant.FLG_OFF_N);

        setValue(edtShpgPlnRec.soCloseFlg, ZYPConstant.FLG_OFF_N);
        setValue(edtShpgPlnRec.shipPlnHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(edtShpgPlnRec.shipPlnCancFlg, ZYPConstant.FLG_OFF_N);
        edtShpgPlnRec.shipPlnCancDt.clear();
        setValue(edtShpgPlnRec.softAllocQty, new BigDecimal(wkSoftAllocQty));
        setValue(edtShpgPlnRec.ordQty, new BigDecimal(wkOrdQty));
        setValue(edtShpgPlnRec.crHldQty, ZERO);
        setValue(edtShpgPlnRec.slsHldQty, ZERO);
        // START   ADD M.Fuji [Defect#2745]
        // WDS#110,#117 Modify Start
        //ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.crChkQty, edtShpgPlnRec.ordQty);
        final String allocAfCrChkFlg = ZYPCodeDataUtil.getVarCharConstValue(ALLOC_AF_CR_CHKED_FLG, param.glblCmpyCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(allocAfCrChkFlg)) {
            ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.crChkQty, edtShpgPlnRec.ordQty);
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(edtShpgPlnRec.poReqFlg.getValue()) || !ZYPCommonFunc.hasValue(edtShpgPlnRec.invtyLocCd)) {
                // non allocation target
                if (BigDecimal.ZERO.compareTo(edtShpgPlnRec.crChkQty.getValue()) < 0) {
                    ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.crChkQty, edtShpgPlnRec.ordQty);
                } else {
                    ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.crChkQty, BigDecimal.ZERO);
                }
            } else {
                // 2017/11/24 S21_NA#22643 Mod Start
                //ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.crChkQty, BigDecimal.ZERO);
                if (MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.crChkQty, edtShpgPlnRec.ordQty);
                } else {
                    ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.crChkQty, BigDecimal.ZERO);
                }
                // 2017/11/24 S21_NA#22643 Mod End
            }
        }
        // WDS#110,#117 Modify End
        // END   ADD M.Fuji [Defect#2745]
        setValue(edtShpgPlnRec.setPrcDetGrpSq, ZERO);
        edtShpgPlnRec.actlShpgMethCd.clear();
        edtShpgPlnRec.actlShpgTermCd.clear();
        edtShpgPlnRec.etaDt.clear();
        setValue(edtShpgPlnRec.sendEmlCpltFlg, ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.insert(edtShpgPlnRec);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(edtShpgPlnRec.getReturnCode())) {
            msgMap.addXxMsgId("NWDM0007E");
        }

        return wkShpgPlnNum;
    }
    private String getMessageKind(S21ApiMessageMap msgMap) {
        NWZC003001PMsg pMsg = (NWZC003001PMsg) msgMap.getPmsg();
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = pMsg.xxMsgIdList.getValidCount() - 1; i >= 0; i--) {
                final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (xxMsgId.endsWith("E")) {
                    return "E";
                }
            }
        }
        return "";
    }
    private List<Map<String, Object>> checkSetShpgPln(final String glblCmpyCd, final String setShpgPlnNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("setShpgPlnNum", setShpgPlnNum);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("checkSetShpgPln", ssmParam);
    }
    private Map<String, BigDecimal> getSoftAllocTotQty(SHPG_PLNTMsg shpgPlnMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
        ssmParam.put("setShpgPlnNum", shpgPlnMsg.setShpgPlnNum.getValue());
        ssmParam.put("trxHdrNum", shpgPlnMsg.trxHdrNum.getValue());
        ssmParam.put("trxLineNum", shpgPlnMsg.trxLineNum.getValue());
        ssmParam.put("trxLineSubNum", shpgPlnMsg.trxLineSubNum.getValue());
        ssmParam.put("trxSrcTpCd", shpgPlnMsg.trxSrcTpCd.getValue());

        return (Map<String, BigDecimal>) this.ssmBatchClient.queryObject("softAllocTotQty", ssmParam);
    }

    private int updShpgPlnNumByPartialValue(final String glblCmpyCd, final String shpgPlnNum, final String newShpgPlnNum) {

        SOFT_ALLOCTMsg condSoftAllocMsg = new SOFT_ALLOCTMsg();
        SOFT_ALLOCTMsg inSoftAllocMsg = new SOFT_ALLOCTMsg();

        condSoftAllocMsg.glblCmpyCd.setValue(glblCmpyCd);
        condSoftAllocMsg.shpgPlnNum.setValue(shpgPlnNum);
        inSoftAllocMsg.shpgPlnNum.setValue(newShpgPlnNum);

        return EZDTBLAccessor.updateByPartialValue(condSoftAllocMsg, new String[] {"glblCmpyCd", "shpgPlnNum" }, inSoftAllocMsg, new String[] {"shpgPlnNum" });

    }

    private List<String> getHldRsnCd(String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("hldTpCd", HLD_TP.SALES_HOLD);
        ssmParam.put("hldLvlCd", HLD_LVL.SHIPPING_PLAN_LEVEL);
        ssmParam.put("nightBatFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("dtmBatFlg", ZYPConstant.FLG_OFF_N);

        return (List<String>) this.ssmBatchClient.queryObjectList("getHldRsnCd", ssmParam);
    }
    private List<HLDTMsg> getHldInfo(String glblCmpyCd, List<String>hldRsnCdLst, List<String> delShplgPlnNumLst) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("hldRsnCd", hldRsnCdLst);
        ssmParam.put("delShplgPlnNumLst", delShplgPlnNumLst);
        ssmParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
        return (List<HLDTMsg>) this.ssmBatchClient.queryObjectList("getHldInfo", ssmParam);
    }
    private void updPrcDtl(S21ApiMessageMap msgMap, List<String> delShpgPlnNumLst, Map<String, Object> newShpgPlnNum, ONBATCH_TYPE onBatchType) {
        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // shiping plan number loop
        for (String delShpgPlnNum : delShpgPlnNumLst) {

            // price deteil
            PRC_DTLTMsg prcDtlTMsg = new PRC_DTLTMsg();
            prcDtlTMsg.setSQLID("007");
            prcDtlTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            prcDtlTMsg.setConditionValue("shpgPlnNum01", delShpgPlnNum);
            PRC_DTLTMsgArray prcDtlTMsgArray = null;
            if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
                prcDtlTMsgArray = (PRC_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prcDtlTMsg);
            } else {
                prcDtlTMsgArray = (PRC_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(prcDtlTMsg);
            }

            if (prcDtlTMsgArray.getValidCount() > 0) {
                for (int i = 0; i < prcDtlTMsgArray.getValidCount(); i++) {
                    PRC_DTLTMsg edtPrcDtlRec = prcDtlTMsgArray.no(i);

                    // ***** logicalRemove
                    EZDTBLAccessor.logicalRemove(edtPrcDtlRec);

                    String wkDelShpgPlnNum = (String) newShpgPlnNum.get("DEL_ORIG_SHPG_PLN_NUM");
                    if (wkDelShpgPlnNum.equals(edtPrcDtlRec.shpgPlnNum.getValue())) {
                        BigDecimal wkShipUnitQtyNew = (BigDecimal) newShpgPlnNum.get("ORD_QTY");
                        BigDecimal wkDealLastNetUnitPrcAmt = edtPrcDtlRec.dealLastNetUnitPrcAmt.getValue();
                        BigDecimal wkFuncLastNetUnitPrcAmt = edtPrcDtlRec.funcLastNetUnitPrcAmt.getValue();
                        BigDecimal wkDealNetAmt = wkDealLastNetUnitPrcAmt.multiply(wkShipUnitQtyNew);
                        BigDecimal wkFuncNetAmt = wkFuncLastNetUnitPrcAmt.multiply(wkShipUnitQtyNew);
                        // 2019/11/12 QC#54513 Add Start
                        BigDecimal wkdealPerUnitFixAmt = edtPrcDtlRec.dealPerUnitFixAmt.getValue(); 
                        BigDecimal wkDiscAmt = wkdealPerUnitFixAmt.multiply(wkShipUnitQtyNew);
                        setValue(edtPrcDtlRec.dealDiscAmt, wkDiscAmt);
                        setValue(edtPrcDtlRec.funcDiscAmt, wkDiscAmt);
                        // 2019/11/12 QC#54513 Add End
                        setValue(edtPrcDtlRec.glblCmpyCd, param.glblCmpyCd.getValue());
                        setValue(edtPrcDtlRec.prcDtlPk, getPrcDtlPk());
                        setValue(edtPrcDtlRec.shpgPlnNum, (String) newShpgPlnNum.get("NEW_SHPG_PLN_NUM"));
                        setValue(edtPrcDtlRec.shipUnitQty, (BigDecimal) newShpgPlnNum.get("ORD_QTY"));
                        setValue(edtPrcDtlRec.dealNetAmt, wkDealNetAmt);
                        setValue(edtPrcDtlRec.funcNetAmt, wkFuncNetAmt);
                        // ***** insert
                        EZDTBLAccessor.insert(edtPrcDtlRec);
                    }
                }
            } else {
                msgMap.addXxMsgId("NWDM0007E");
            }
        }
    }
    private BigDecimal getPrcDtlPk() {
        String prcDtlPk;
        prcDtlPk = ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqAccessor.PRC_DTL_SQ, 28);
        return new BigDecimal(prcDtlPk);
    }
    private void deleteHld(S21ApiMessageMap msgMap, List<String> delShpgPlnNumLst, final ONBATCH_TYPE onBatchType) {
        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // shiping plan number loop
        for (String delShpgPlnNum : delShpgPlnNumLst) {

            // [HLD]
            final HLDTMsg hldTMsg = new HLDTMsg();
            hldTMsg.setSQLID("022");
            hldTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            hldTMsg.setConditionValue("shpgPlnNum01", delShpgPlnNum);

             HLDTMsgArray hldTMsgArray = null;
            if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
                hldTMsgArray = (HLDTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(hldTMsg);
            } else {
                hldTMsgArray = (HLDTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(hldTMsg);
            }

            for (int i = 0; i < hldTMsgArray.getValidCount(); i++) {
                // ***** logicalRemove
                EZDTBLAccessor.logicalRemove(hldTMsgArray.no(i));
            }
        }
    }
    private void callAllocApiByCancel(final NWZC102001 allocAPI, S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsg, final ONBATCH_TYPE onBatchType) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        final NWZC102001PMsg pMsg = new NWZC102001PMsg();
        setValue(pMsg.glblCmpyCd,       param.glblCmpyCd);
        setValue(pMsg.xxOrigFuncTpCd,   NWZC102001.ORG_FUNC_CD_HARD_ALLOC);
        setValue(pMsg.xxRqstTpCd,       NWZC102001.REQ_TP_SHIPPING_CANCEL);
        setValue(pMsg.xxAllocTpCd,      NWZC102001.ALLOC_TP_CD_HARD_ALLOC);
        setValue(pMsg.trxSrcTpCd,       TRX_SRC_TP.WHOLE_SALES);
        setValue(pMsg.trxHdrNum,        shpgPlnMsg.trxHdrNum);
        setValue(pMsg.trxLineNum,       shpgPlnMsg.trxLineNum);
        setValue(pMsg.trxLineSubNum,    shpgPlnMsg.trxLineSubNum);
        setValue(pMsg.mdseCd,           shpgPlnMsg.mdseCd);
        setValue(pMsg.invtyLocCd,       shpgPlnMsg.invtyLocCd);
        setValue(pMsg.shpgSvcLvlCd,     shpgPlnMsg.reqShpgSvcLvlCd);
        setValue(pMsg.slsDt,            param.slsDt);

        // [NWZC102001] : Allocation API
        allocAPI.execute(pMsg, onBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = pMsg.xxMsgIdList.getValidCount() - 1; i >= 0; i--) {
                String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (msgId != null) {
                    if (!"E".equals(getMessageKind(msgMap))) {
                        msgMap.addXxMsgId(msgId);
                    }
                }
            }
        }
    }
    private void callValidMgrApi(final NWZC004001 validProcMgrAPI, S21ApiMessageMap msgMap, Map<String, Object> newShpgPlnNum, final ONBATCH_TYPE onBatchType) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        final NWZC004001PMsg pMsg = new NWZC004001PMsg();
        setValue(pMsg.xxRqstTpCd,   NWZC004001.VAL_TP_RC);
        setValue(pMsg.glblCmpyCd,   param.glblCmpyCd);
        setValue(pMsg.slsDt,        param.slsDt);
        setValue(pMsg.shpgPlnNum_I, (String) newShpgPlnNum.get("NEW_SHPG_PLN_NUM"));

        // [NWZC004001] : Validation Process Manager API
        validProcMgrAPI.execute(pMsg, onBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = pMsg.xxMsgIdList.getValidCount() - 1; i >= 0; i--) {
                final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (xxMsgId != null) {
                    if (!"E".equals(getMessageKind(msgMap))) {
                        msgMap.addXxMsgId(xxMsgId);
                    }
                }
            }
        }
    }
    private void deletePrcDtl(final S21ApiMessageMap msgMap, final String delShpgPlnNum, final ONBATCH_TYPE onBatchType) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        PRC_DTLTMsg prcDtlTMsg = new PRC_DTLTMsg();
        prcDtlTMsg.setSQLID("007");
        prcDtlTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        prcDtlTMsg.setConditionValue("shpgPlnNum01", delShpgPlnNum);
        PRC_DTLTMsgArray prcDtlTMsgArray = null;
        if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
            prcDtlTMsgArray = (PRC_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prcDtlTMsg);
        } else {
            prcDtlTMsgArray = (PRC_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(prcDtlTMsg);
        }

        if (prcDtlTMsgArray.getValidCount() > 0) {
            for (int i = 0; i < prcDtlTMsgArray.getValidCount(); i++) {
                PRC_DTLTMsg edtPrcDtlRec = prcDtlTMsgArray.no(i);

                // remove
                EZDTBLAccessor.logicalRemove(edtPrcDtlRec);

            }
        } else {
            msgMap.addXxMsgId("NWDM0007E");
        }
    }
    private void updSetSoftAlloc(S21ApiMessageMap msgMap, final String glblCmpyCd, final String setShpgPlnNum, final String newSetShpgPlnNum, final String margeFlg, final ONBATCH_TYPE onBatchType) {

        SOFT_ALLOCTMsgArray softAllocRcdList = searchSoftAllocForUpdate(glblCmpyCd, setShpgPlnNum, onBatchType);
        if (softAllocRcdList.length() == 0) {
            return;
        }

        if (margeFlg != null) {
            // Marge
            SOFT_ALLOCTMsgArray softAllocValitList = searchSoftAllocForUpdate(glblCmpyCd, newSetShpgPlnNum, onBatchType);

            for (int k = 0; k < softAllocRcdList.length(); k++) {

                boolean updateFlg = true;
                String softAllocTs = softAllocRcdList.no(k).softAllocTs.getValue();
                String distPlnNum = softAllocRcdList.no(k).distPlnNum.getValue();
                BigDecimal distPk = softAllocRcdList.no(k).distPk.getValue();
                BigDecimal distStruSegPk = softAllocRcdList.no(k).distStruSegPk.getValue();
                BigDecimal softAllocPk = softAllocRcdList.no(k).softAllocPk.getValue();
                String distTmFrameNum = softAllocRcdList.no(k).distTmFrameNum.getValue();

                for (int j = 0; j < softAllocValitList.length(); j++) {

                    if (isEquals(softAllocTs, softAllocValitList.no(j).softAllocTs.getValue()) && isEquals(distPlnNum, softAllocValitList.no(j).distPlnNum.getValue())
                            && isEquals(distTmFrameNum, softAllocValitList.no(j).distTmFrameNum.getValue()) && distPk.compareTo(softAllocValitList.no(j).distPk.getValue()) == 0
                            && distStruSegPk.compareTo(softAllocValitList.no(j).distStruSegPk.getValue()) == 0 && softAllocPk.compareTo(softAllocValitList.no(j).softAllocPk.getValue()) != 0) {

                        softAllocValitList.no(j).softAllocQty.setValue(softAllocValitList.no(j).softAllocQty.getValue().add(softAllocRcdList.no(k).softAllocQty.getValue()));
                        softAllocValitList.no(j).hardAllocAvalQty.setValue(softAllocValitList.no(j).hardAllocAvalQty.getValue().add(softAllocRcdList.no(k).hardAllocAvalQty.getValue()));

                        // ------------------
                        // delete SOFT_ALLOC
                        // ------------------
                        EZDTBLAccessor.logicalRemove(softAllocRcdList.no(k));

                        // ------------------
                        // Update SOFT_ALLOC
                        // (The value of SET_SOFT_ALLOC_PK is
                        // updated.)
                        // ------------------
                        updSetSoftAllocPkByPartialValue(glblCmpyCd, softAllocRcdList.no(k).softAllocPk.getValue(), softAllocValitList.no(j).softAllocPk.getValue());

                        updateFlg = false;
                        break;

                    }
                }

                if (updateFlg) {
                    // ***** Update
                    softAllocRcdList.no(k).shpgPlnNum.setValue(newSetShpgPlnNum);
                    if (hasValue(softAllocRcdList.no(k).setSoftAllocPk)) {
                        softAllocRcdList.no(k).setSoftAllocPk.setValue(softAllocRcdList.no(k).softAllocPk.getValue());
                    }

                    SOFT_ALLOCTMsg softAllocRec = updSoftAlloc(softAllocRcdList.no(k));

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(softAllocRec.getReturnCode())) {
                        msgMap.addXxMsgId("NWDM0007E");
                        return;

                    }
                }
            }

            for (int j = 0; j < softAllocValitList.length(); j++) {

                SOFT_ALLOCTMsg softAllocRec = updSoftAlloc(softAllocValitList.no(j));

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(softAllocRec.getReturnCode())) {
                    msgMap.addXxMsgId("NWDM0007E");
                    return;
                }
            }

        } else {

            // Update
            for (int i = 0; i < softAllocRcdList.length(); i++) {

                softAllocRcdList.no(i).shpgPlnNum.setValue(newSetShpgPlnNum);
                if (hasValue(softAllocRcdList.no(i).setSoftAllocPk)) {
                    softAllocRcdList.no(i).setSoftAllocPk.setValue(softAllocRcdList.no(i).softAllocPk.getValue());
                }

                // ***** Update
                SOFT_ALLOCTMsg softAllocRec = updSoftAlloc(softAllocRcdList.no(i));

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(softAllocRec.getReturnCode())) {
                    msgMap.addXxMsgId("NWDM0007E");
                    return;
                }
            }
        }
    }
    private boolean remakeProcess(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsgRcd, final String createShpgPlnNum, final ONBATCH_TYPE onBatchType) {

        // Remake
        String newSetShpgPlnNum = null;
        BigDecimal newSetSoftAllocPk = null;
        String glblCmpyCd = shpgPlnMsgRcd.glblCmpyCd.getValue();

        // -----------------------------------------------
        // The quantity to divide as a set is calculated.
        // -----------------------------------------------
        Map<String, Object> compQtyMap = NWXC002007BackOrder.setPossibleQty(glblCmpyCd, shpgPlnMsgRcd.trxSrcTpCd.getValue(), shpgPlnMsgRcd.trxHdrNum.getValue(), shpgPlnMsgRcd.trxLineNum.getValue(), createShpgPlnNum);

        if (compQtyMap == null || compQtyMap.isEmpty()) {
            return true;

        } else {

            BigDecimal setPossibleQty = (BigDecimal) compQtyMap.get(NWXC002007BackOrder.SET_AVAL_QTY);

            if (setPossibleQty == null) {
                return true;

            } else if (ZERO.compareTo(setPossibleQty) == 0) {
                return true;

            }

            // SHPG_PLN information on each ComponentItem.
            List<Map<String, Object>> shpgPlnEachCompList = queryShpgPlnEachCompForCancel(glblCmpyCd, shpgPlnMsgRcd.trxSrcTpCd.getValue(), shpgPlnMsgRcd.trxHdrNum.getValue(), shpgPlnMsgRcd.trxLineNum.getValue(), createShpgPlnNum);

            if (shpgPlnEachCompList != null && !shpgPlnEachCompList.isEmpty()) {

                // ---------------------------------------------
                // SetItem Divide/Update Process
                // ---------------------------------------------
                SHPG_PLNTMsg origSetShpgPlnMsg = getShpgPlnFindByKey(glblCmpyCd, createShpgPlnNum);

                if (origSetShpgPlnMsg == null || !isEquals(SHPG_STS.VALIDATED, origSetShpgPlnMsg.shpgStsCd.getValue())) {
                    msgMap.addXxMsgId("NWDM0007E");
                    return false;
                }

                if (setPossibleQty.compareTo(origSetShpgPlnMsg.ordQty.getValue()) < 0) {
                    // setPossibleQty < OrdQty --> Divide

                    // SHPG_PLN
                    // PRC_DTL
                    // HLD
                    String divideSetShpgPlnNum = divideProcess(origSetShpgPlnMsg, setPossibleQty, null, SHPG_STS.HARD_ALLOCATED, onBatchType);

                    if (divideSetShpgPlnNum == null) {
                        return false;
                    }
                    // SOFT_ALLOC
                    newSetSoftAllocPk = setSoftAllocDivideProcess(glblCmpyCd, setPossibleQty, divideSetShpgPlnNum, origSetShpgPlnMsg.shpgPlnNum.getValue(), onBatchType);

                    newSetShpgPlnNum = divideSetShpgPlnNum;

                }

                // ---------------------------------------------
                // ComponentItem Divide/Update Process
                // ---------------------------------------------
                String beforelineSubNum = "";
                BigDecimal eachCompTotalQty = ZERO;

                for (Map<String, Object> shpgPlnEachComp : shpgPlnEachCompList) {

                    String lineSubNum = (String) shpgPlnEachComp.get("TRX_LINE_SUB_NUM");
                    String compShpgPlnNum = (String) shpgPlnEachComp.get("SHPG_PLN_NUM");
                    BigDecimal ordQty = (BigDecimal) shpgPlnEachComp.get("ORD_QTY");
                    String mdseCd = (String) shpgPlnEachComp.get("MDSE_CD");
                    String poReqFlg = (String) shpgPlnEachComp.get("PO_REQ_FLG");

                    if (!isEquals(beforelineSubNum, lineSubNum)) {
                        // ---------------------------------------------
                        // eachCompTotalQty = Amount of each component
                        // that can be divided.
                        // ---------------------------------------------
                        beforelineSubNum = lineSubNum;
                        BigDecimal eachCompQty = (BigDecimal) compQtyMap.get(lineSubNum);

                        if (eachCompQty == null) {
                            return false;
                        }
                        eachCompTotalQty = multiply(eachCompQty, setPossibleQty);
                    }

                    if (ZERO.compareTo(eachCompTotalQty) == 0) {
                        continue;
                    }

                    // Original SHPG_PLN
                    SHPG_PLNTMsg origCompShpgPlnMsg = getShpgPlnFindByKey(glblCmpyCd, compShpgPlnNum);

                    if (eachCompTotalQty.compareTo(ordQty) < 0) {
                        // -------------------------------
                        // eachCompTotalQty < OrdQty -->Divide
                        // -------------------------------

                        BigDecimal divideQty = subtract(ordQty, eachCompTotalQty);

                        String sts = getSts(glblCmpyCd, mdseCd, poReqFlg);

                        if (sts == null) {
                            return false;
                        }

                        BigDecimal updOrdQty = subtract(origCompShpgPlnMsg.ordQty.getValue(), divideQty);

                        // SHPG_PLN
                        // PRC_DTL
                        // HLD
                        String divideCompShpgPlnNum = divideProcess(origCompShpgPlnMsg, divideQty, newSetShpgPlnNum, sts, onBatchType);

                        // SOFT_ALLOC
                        SOFT_ALLOCTMsg softAllocMsg = softAllocDivideProcess(glblCmpyCd, divideQty, divideCompShpgPlnNum, origCompShpgPlnMsg.shpgPlnNum.getValue(), newSetSoftAllocPk, onBatchType);

                        // HARD_ALLOC
                        hardAllocDivideProcess(msgMap, glblCmpyCd, origCompShpgPlnMsg.shpgPlnNum.getValue(), divideCompShpgPlnNum, updOrdQty, divideQty, softAllocMsg, onBatchType);

                        eachCompTotalQty = ZERO;

                    } else {
                        // -------------------------------
                        // eachCompTotalQty >= OrdQty -->Update
                        // -------------------------------

                        origCompShpgPlnMsg.setShpgPlnNum.setValue(newSetShpgPlnNum);
                        EZDTBLAccessor.update(origCompShpgPlnMsg);

                        // SOFT_ALLOC
                        updSoftAllocPk(msgMap, glblCmpyCd, origCompShpgPlnMsg.shpgPlnNum.getValue(), newSetSoftAllocPk, onBatchType);

                        if (eachCompTotalQty.compareTo(ordQty) == 0) {
                            eachCompTotalQty = ZERO;
                        } else {
                            eachCompTotalQty = subtract(eachCompTotalQty, ordQty);
                        }
                    }
                }
            }
        }

        return true;
    }
    private String getSts(final String glblCmpyCd, final String mdseCd, final String poReqFlg) {

        String sts = "";

        MDSETMsg mdseMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);

        if (mdseMsg == null) {
            return null;
        }

        if (ZYPConstant.FLG_ON_Y.equals(poReqFlg)) {
            sts = SHPG_STS.VALIDATED;
        } else if (isEquals(ZYPConstant.FLG_OFF_N, mdseMsg.invtyCtrlFlg.getValue())) {
            sts = SHPG_STS.VALIDATED;
        } else {
            sts = SHPG_STS.HARD_ALLOCATED;
        }

        return sts;

    }
    private String divideProcess(final SHPG_PLNTMsg originalShpgPlnMsg, final BigDecimal divideQty, final String newSetShpgPlnNum, final String sts, final ONBATCH_TYPE onBatchType) {

        String glblCmpyCd = originalShpgPlnMsg.glblCmpyCd.getValue();
        String originalShpgPlnNum = originalShpgPlnMsg.shpgPlnNum.getValue();
        String trxHdrNum = originalShpgPlnMsg.trxHdrNum.getValue();
        String trxLineNum = originalShpgPlnMsg.trxLineNum.getValue();
        String trxLineSubNum = originalShpgPlnMsg.trxLineSubNum.getValue();
        String shpgPlnNumHA;
        PRC_DTLTMsgArray prcDtlData = null;
        SHPG_PLNTMsg haShpgPlnMsg = new SHPG_PLNTMsg();

        EZDMsg.copy(originalShpgPlnMsg, null, haShpgPlnMsg, null);

        // ---------------------------
        // Update SHPG_PLN
        // ---------------------------

        this.setTotalAmount(originalShpgPlnMsg, subtract(originalShpgPlnMsg.ordQty.getValue(), divideQty));

        if (originalShpgPlnMsg.softAllocQty.getValue().compareTo(ZERO) > 0) {

            if (originalShpgPlnMsg.softAllocQty.getValue().compareTo(divideQty) == 0) {
                // softAllocQty = AllocatedQty
                haShpgPlnMsg.softAllocQty.setValue(divideQty);
                originalShpgPlnMsg.softAllocQty.setValue(ZERO);

            } else if (originalShpgPlnMsg.softAllocQty.getValue().compareTo(divideQty) > 0) {
                // softAllocQty > AllocatedQty
                haShpgPlnMsg.softAllocQty.setValue(divideQty);
                originalShpgPlnMsg.softAllocQty.setValue(subtract(originalShpgPlnMsg.softAllocQty.getValue(), divideQty));

            } else {
                // softAllocQty < AllocatedQty
                haShpgPlnMsg.softAllocQty.setValue(originalShpgPlnMsg.softAllocQty.getValue());
                originalShpgPlnMsg.softAllocQty.setValue(ZERO);
            }
        }

        if (originalShpgPlnMsg.slsHldQty.getValue().compareTo(ZERO) == 0) {
            originalShpgPlnMsg.slsHldQty.setValue(ZERO);
        } else {
            originalShpgPlnMsg.slsHldQty.setValue(subtract(haShpgPlnMsg.slsHldQty.getValue(), divideQty));
        }

        if (originalShpgPlnMsg.crHldQty.getValue().compareTo(ZERO) == 0) {
            originalShpgPlnMsg.crHldQty.setValue(ZERO);
        } else {
            originalShpgPlnMsg.crHldQty.setValue(subtract(haShpgPlnMsg.crHldQty.getValue(), divideQty));
        }

        BigDecimal calcCrChkQty = ZERO;
        if (originalShpgPlnMsg.crChkQty.getValue().compareTo(ZERO) == 0) {
            originalShpgPlnMsg.crChkQty.setValue(ZERO);
        } else {

            calcCrChkQty = subtract(haShpgPlnMsg.crChkQty.getValue(), divideQty);
            if (ZERO.compareTo(calcCrChkQty) > 0) {
                originalShpgPlnMsg.crChkQty.setValue(ZERO);
            } else {
                originalShpgPlnMsg.crChkQty.setValue(calcCrChkQty);
            }
        }

        BigDecimal calcavalSoQty = ZERO;
        if (originalShpgPlnMsg.avalSoQty.getValue().compareTo(ZERO) == 0) {
            originalShpgPlnMsg.avalSoQty.setValue(ZERO);
        } else {

            calcavalSoQty = subtract(haShpgPlnMsg.avalSoQty.getValue(), divideQty);
            if (ZERO.compareTo(calcavalSoQty) > 0) {
                originalShpgPlnMsg.avalSoQty.setValue(ZERO);
            } else {
                originalShpgPlnMsg.avalSoQty.setValue(calcavalSoQty);
            }
        }

        BigDecimal calcavalPoQty = ZERO;
        if (originalShpgPlnMsg.avalPoQty.getValue().compareTo(ZERO) == 0) {
            originalShpgPlnMsg.avalPoQty.setValue(ZERO);
        } else {

            calcavalPoQty = subtract(haShpgPlnMsg.avalPoQty.getValue(), divideQty);
            if (ZERO.compareTo(calcavalPoQty) > 0) {
                originalShpgPlnMsg.avalPoQty.setValue(ZERO);
            } else {
                originalShpgPlnMsg.avalPoQty.setValue(calcavalPoQty);
            }
        }

        if (newSetShpgPlnNum != null) {
            originalShpgPlnMsg.setShpgPlnNum.setValue(newSetShpgPlnNum);
        }

        // **** update
        EZDTBLAccessor.update(originalShpgPlnMsg);

        // ---------------------------
        // Update PRC_DTL
        // ---------------------------
        if (TRX_SRC_TP.WHOLE_SALES.equals(originalShpgPlnMsg.trxSrcTpCd.getValue())) {
            prcDtlData = this.updatePrcDtlFromshpgPlnNum(glblCmpyCd, originalShpgPlnNum, multiply(divideQty, BigDecimal.valueOf(-1)), onBatchType);
            if (prcDtlData == null) {
                return null;
            }
        }

        // ---------------------------
        // Update HLD
        // ---------------------------
        HLDTMsgArray hldData = this.updateHldFromshpgPlnNum(glblCmpyCd, originalShpgPlnNum, trxHdrNum, trxLineNum, trxLineSubNum, multiply(divideQty, BigDecimal.valueOf(-1)), onBatchType);

        // ---------------------------
        // Insert SHPG_PLN
        // (Shipping Plan Status 'Hard Allocated' Data)
        // ---------------------------

        haShpgPlnMsg.shpgPlnNum.setValue(getShipgPlnSQ());
        String shpgPlnDplyLineNum = this.getDplyLineNum(haShpgPlnMsg);
        if (shpgPlnDplyLineNum == null) {
            return null;
        }

        haShpgPlnMsg.shpgPlnDplyLineNum.setValue(shpgPlnDplyLineNum);
        haShpgPlnMsg.shpgStsCd.setValue(sts);
        haShpgPlnMsg.ordQty.setValue(divideQty);

        this.setTotalAmount(haShpgPlnMsg, divideQty);

        if (originalShpgPlnMsg.slsHldQty.getValue().compareTo(ZERO) == 0) {
            haShpgPlnMsg.slsHldQty.setValue(ZERO);
        } else {
            haShpgPlnMsg.slsHldQty.setValue(divideQty);
        }

        if (originalShpgPlnMsg.crHldQty.getValue().compareTo(ZERO) == 0) {
            haShpgPlnMsg.crHldQty.setValue(ZERO);
        } else {
            haShpgPlnMsg.crHldQty.setValue(divideQty);
        }

        if (originalShpgPlnMsg.crChkQty.getValue().compareTo(ZERO) == 0) {
            haShpgPlnMsg.crChkQty.setValue(ZERO);
        } else {

            if (ZERO.compareTo(calcCrChkQty) == 0) {
                haShpgPlnMsg.crChkQty.setValue(ZERO);
            } else {
                haShpgPlnMsg.crChkQty.setValue(divideQty);
            }
        }

        if (originalShpgPlnMsg.avalSoQty.getValue().compareTo(ZERO) == 0) {
            haShpgPlnMsg.avalSoQty.setValue(ZERO);
        } else {

            if (ZERO.compareTo(calcavalSoQty) == 0) {
                haShpgPlnMsg.avalSoQty.setValue(ZERO);
            } else {
                haShpgPlnMsg.avalSoQty.setValue(divideQty);
            }
        }

        if (originalShpgPlnMsg.avalPoQty.getValue().compareTo(ZERO) == 0) {
            haShpgPlnMsg.avalPoQty.setValue(ZERO);
        } else {

            if (ZERO.compareTo(calcavalPoQty) == 0) {
                haShpgPlnMsg.avalPoQty.setValue(ZERO);
            } else {
                haShpgPlnMsg.avalPoQty.setValue(divideQty);
            }
        }

        // **** Insert
        EZDTBLAccessor.insert(haShpgPlnMsg);

        shpgPlnNumHA = haShpgPlnMsg.shpgPlnNum.getValue();

        // ---------------------------
        // Insert PRC_DTL
        // ---------------------------
        if (isEquals(TRX_SRC_TP.WHOLE_SALES, originalShpgPlnMsg.trxSrcTpCd.getValue())) {
            this.insertPrcDtlFromshpgPlnNum(prcDtlData, glblCmpyCd, shpgPlnNumHA, divideQty);
        }

        // ---------------------------
        // Insert HLD
        // ---------------------------
        if (hldData.length() > 0) {
            this.insertHldFromshpgPlnNum(hldData, glblCmpyCd, shpgPlnNumHA, divideQty);
        }

        return shpgPlnNumHA;
    }
    private void setTotalAmount(SHPG_PLNTMsg shpgPlnMsg, final BigDecimal setOrdQty) {

        shpgPlnMsg.ordQty.setValue(setOrdQty);
        shpgPlnMsg.spTotDealSlsAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spDealUnitPrcAmt.getValue(), setOrdQty)));
        shpgPlnMsg.spTotDealNetAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spDealNetUnitPrcAmt.getValue(), setOrdQty)));
        shpgPlnMsg.spTotFuncSlsAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spFuncUnitPrcAmt.getValue(), setOrdQty)));
        shpgPlnMsg.spTotFuncNetAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spFuncNetUnitPrcAmt.getValue(), setOrdQty)));

    }
    private PRC_DTLTMsgArray updatePrcDtlFromshpgPlnNum(final String glblCmpyCd, final String shpgPlnNum, final BigDecimal qty, final ONBATCH_TYPE onBatchType) {

        PRC_DTLTMsgArray updPrcDtlData = searchPrcDtlForUpdate(glblCmpyCd, shpgPlnNum, onBatchType);

        if (updPrcDtlData.length() == 0) {
            return null;
        }

        for (int i = 0; i < updPrcDtlData.length(); i++) {
            BigDecimal updShipUnitQty = ZERO;

            updShipUnitQty = add(updPrcDtlData.no(i).shipUnitQty.getValue(), qty);
            updPrcDtlData.no(i).shipUnitQty.setValue(updShipUnitQty);
            updPrcDtlData.no(i).dealNetAmt.setValue(roundHalfUp(multiply(updPrcDtlData.no(i).dealLastNetUnitPrcAmt.getValue(), updPrcDtlData.no(i).shipUnitQty.getValue())));
            updPrcDtlData.no(i).funcNetAmt.setValue(roundHalfUp(multiply(updPrcDtlData.no(i).funcLastNetUnitPrcAmt.getValue(), updPrcDtlData.no(i).shipUnitQty.getValue())));
            if (hasValue(updPrcDtlData.no(i).dealPerUnitFixAmt)) {
                updPrcDtlData.no(i).dealDiscAmt.setValue(roundHalfUp(multiply(updPrcDtlData.no(i).dealPerUnitFixAmt.getValue(), updPrcDtlData.no(i).shipUnitQty.getValue())));
            }
            if (hasValue(updPrcDtlData.no(i).funcPerUnitFixAmt)) {
                updPrcDtlData.no(i).funcDiscAmt.setValue(roundHalfUp(multiply(updPrcDtlData.no(i).funcPerUnitFixAmt.getValue(), updPrcDtlData.no(i).shipUnitQty.getValue())));
            }

            if (updShipUnitQty.compareTo(ZERO) == 0) {

                // remove
                EZDTBLAccessor.logicalRemove(updPrcDtlData.no(i));

            } else {

                EZDTBLAccessor.update(updPrcDtlData.no(i));

            }
        }
        return updPrcDtlData;
    }
    private HLDTMsgArray updateHldFromshpgPlnNum(final String glblCmpyCd, final String shpgPlnNum, final String cpoOrdNum, final String cpoLineMum, final String cpoLineSubMum, final BigDecimal qty, final ONBATCH_TYPE onBatchType) {

        HLDTMsgArray updHld = this.searchHldForUpdate(glblCmpyCd, shpgPlnNum, cpoOrdNum, cpoLineMum, cpoLineSubMum, onBatchType);

        if (updHld.length() == 0) {
            return updHld;

        } else {

            int updHldSize = updHld.length();
            for (int i = 0; i < updHldSize; i++) {

                BigDecimal hldQty = ZERO;
                hldQty = add(updHld.no(i).hldQty.getValue(), qty);
                updHld.no(i).hldQty.setValue(hldQty);

                if (hldQty.compareTo(ZERO) == 0) {
                    // remove
                    EZDTBLAccessor.logicalRemove(updHld.no(i));

                } else {
                    EZDTBLAccessor.update(updHld.no(i));
                }
            }
        }

        return updHld;

    }
    private BigDecimal setSoftAllocDivideProcess(final String glblCmpyCd, final BigDecimal divideQty, final String divideShpgPlnNum, final String origShpgPlnNum, final ONBATCH_TYPE onBatchType) {

        BigDecimal calcQty = ZERO;
        calcQty = divideQty;
        BigDecimal newSoftAllocPk = null;

        SOFT_ALLOCTMsgArray softAllocMsgArray = searchSoftAllocForUpdate(glblCmpyCd, origShpgPlnNum, onBatchType);

        int softAllocSize = softAllocMsgArray.getValidCount();
        for (int i = 0; i < softAllocSize; i++) {

            if (ZERO.compareTo(calcQty) == 0) {
                break;
            }

            if (calcQty.compareTo(softAllocMsgArray.no(i).softAllocQty.getValue()) == 0) {
                // update

                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                updsoftAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);
                updsoftAllocMsg.setSoftAllocPk.setValue(updsoftAllocMsg.softAllocPk.getValue());

                // **** Update
                EZDTBLAccessor.update(updsoftAllocMsg);
                break;

            } else if (calcQty.compareTo(softAllocMsgArray.no(i).softAllocQty.getValue()) < 0) {
                // Divide

                BigDecimal calcSoftAllocQty = ZERO;
                SOFT_ALLOCTMsg newsoftAllocMsg = new SOFT_ALLOCTMsg();
                EZDMsg.copy(softAllocMsgArray.no(i), null, newsoftAllocMsg, null);

                newSoftAllocPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SOFT_ALLOC_SQ);
                newsoftAllocMsg.softAllocPk.setValue(newSoftAllocPk);
                newsoftAllocMsg.hardAllocAvalQty.setValue(ZERO);
                newsoftAllocMsg.softAllocQty.setValue(calcQty);
                newsoftAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);
                newsoftAllocMsg.setSoftAllocPk.setValue(newSoftAllocPk);

                // **** Insert
                EZDTBLAccessor.insert(newsoftAllocMsg);

                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                calcSoftAllocQty = subtract(softAllocMsgArray.no(i).softAllocQty.getValue(), calcQty);
                updsoftAllocMsg.softAllocQty.setValue(calcSoftAllocQty);
                updsoftAllocMsg.setSoftAllocPk.setValue(updsoftAllocMsg.softAllocPk.getValue());

                // **** Update
                EZDTBLAccessor.update(updsoftAllocMsg);

                break;

            } else {
                // none
                calcQty = subtract(calcQty, softAllocMsgArray.no(i).softAllocQty.getValue());
                continue;

            }
        }
        return newSoftAllocPk;
    }
    private void hardAllocDivideProcess(S21ApiMessageMap msgMap, final String glblCmpyCd, final String origShpgPlnNum, final String newShpgPlnNum, final BigDecimal divideQty, final BigDecimal newQty, final SOFT_ALLOCTMsg softAllocMsg, final ONBATCH_TYPE onBatchType) {

        HARD_ALLOCTMsgArray hardAllocMsgData = searchHardAlloc(glblCmpyCd, origShpgPlnNum, onBatchType);

        if (hardAllocMsgData.getValidCount() > 0) {

            // Update
            HARD_ALLOCTMsg originalHardAllocMsg = hardAllocMsgData.no(0);
            HARD_ALLOCTMsg newHardAllocMsg = new HARD_ALLOCTMsg();
            EZDMsg.copy(originalHardAllocMsg, null, newHardAllocMsg, null);

            originalHardAllocMsg.hardAllocQty.setValue(divideQty);
            originalHardAllocMsg.shpgPlnNum.setValue(origShpgPlnNum);
            if (softAllocMsg != null && isEquals(softAllocMsg.shpgPlnNum.getValue(), origShpgPlnNum)) {
                originalHardAllocMsg.softAllocPk.setValue(softAllocMsg.softAllocPk.getValue());
            }

            updateHardAlloc(msgMap, originalHardAllocMsg);

            newHardAllocMsg.hardAllocQty.setValue(newQty);
            newHardAllocMsg.shpgPlnNum.setValue(newShpgPlnNum);
            newHardAllocMsg.hardAllocPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HARD_ALLOC_SQ));
            if (softAllocMsg != null && isEquals(softAllocMsg.shpgPlnNum.getValue(), newShpgPlnNum)) {
                newHardAllocMsg.softAllocPk.setValue(softAllocMsg.softAllocPk.getValue());
            }

            // 
            insertHardAlloc(msgMap, newHardAllocMsg);

        }
    }

    private void updateHardAlloc(S21ApiMessageMap msgMap, HARD_ALLOCTMsg hardAllocMsg) {

        EZDTBLAccessor.update(hardAllocMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hardAllocMsg.getReturnCode())) {
            msgMap.addXxMsgId("NWDM0007E");
        }

    }

    private void insertHardAlloc(S21ApiMessageMap msgMap, HARD_ALLOCTMsg hardAllocMsg) {

        EZDTBLAccessor.insert(hardAllocMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hardAllocMsg.getReturnCode())) {
            msgMap.addXxMsgId("NWDM0007E");
        }
    }

    private SOFT_ALLOCTMsg softAllocDivideProcess(final String glblCmpyCd, final BigDecimal divideQty, final String divideShpgPlnNum, final String origShpgPlnNum, final BigDecimal newSetSoftAllocPk, final ONBATCH_TYPE onBatchType) {

        if (ZERO.compareTo(divideQty) == 0) {
            return null;
        }

        SOFT_ALLOCTMsgArray softAllocMsgArray = this.searchSoftAllocForUpdate(glblCmpyCd, origShpgPlnNum, onBatchType);

        BigDecimal calcQty = ZERO;
        calcQty = divideQty;
        SOFT_ALLOCTMsg softAllocMsg = new SOFT_ALLOCTMsg();

        // divideQty
        int softAllocSize = softAllocMsgArray.getValidCount();
        for (int i = 0; i < softAllocSize; i++) {

            if (calcQty.compareTo(softAllocMsgArray.no(i).softAllocQty.getValue()) < 0) {

                // Devide
                BigDecimal calcSoftAllocQty = ZERO;
                SOFT_ALLOCTMsg newsoftAllocMsg = new SOFT_ALLOCTMsg();
                EZDMsg.copy(softAllocMsgArray.no(i), null, newsoftAllocMsg, null);

                calcSoftAllocQty = subtract(softAllocMsgArray.no(i).softAllocQty.getValue(), calcQty);

                newsoftAllocMsg.softAllocPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SOFT_ALLOC_SQ));
                newsoftAllocMsg.hardAllocAvalQty.setValue(ZERO);
                newsoftAllocMsg.softAllocQty.setValue(calcQty);
                newsoftAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);
                if (hasValue(newSetSoftAllocPk)) {
                    newsoftAllocMsg.setSoftAllocPk.setValue(newSetSoftAllocPk);
                }

                // **** Insert
                EZDTBLAccessor.insert(newsoftAllocMsg);

                // update
                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                updsoftAllocMsg.softAllocQty.setValue(calcSoftAllocQty);
                if (calcSoftAllocQty.compareTo(updsoftAllocMsg.hardAllocAvalQty.getValue()) < 0) {
                    updsoftAllocMsg.hardAllocAvalQty.setValue(calcSoftAllocQty);
                }

                // **** Update
                EZDTBLAccessor.update(updsoftAllocMsg);

                calcQty = ZERO;
                softAllocMsg = newsoftAllocMsg;
                break;

            } else {

                // update
                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                calcQty = subtract(calcQty, softAllocMsgArray.no(i).softAllocQty.getValue());
                updsoftAllocMsg.hardAllocAvalQty.setValue(ZERO);
                updsoftAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);
                if (hasValue(newSetSoftAllocPk)) {
                    updsoftAllocMsg.setSoftAllocPk.setValue(newSetSoftAllocPk);
                }

                // **** Update
                EZDTBLAccessor.update(updsoftAllocMsg);

                if (ZERO.compareTo(calcQty) == 0) {
                    softAllocMsg = updsoftAllocMsg;
                    break;
                }

            }
        }

        return softAllocMsg;
    }

    private void updateHoldForCancel(String glblCmpyCd, String originalShpgPlnNum, String newShpgPlnNum, int ordQty, final ONBATCH_TYPE onBatchType) {

        List<BigDecimal> hldPkList = getShpgLvlSalesHold(glblCmpyCd, originalShpgPlnNum);

        if (hldPkList != null && !hldPkList.isEmpty()) {

            for (BigDecimal hldPk : hldPkList) {

                HLDTMsg hldMsg = new HLDTMsg();
                HLDTMsg rsltHldMsg = null;
                hldMsg.glblCmpyCd.setValue(glblCmpyCd);
                hldMsg.hldPk.setValue(hldPk);
                if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
                    rsltHldMsg = (HLDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(hldMsg);
                } else {
                    rsltHldMsg = (HLDTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(hldMsg);
                }

                if (rsltHldMsg != null) {
                    rsltHldMsg.shpgPlnNum.setValue(newShpgPlnNum);
                    rsltHldMsg.hldQty.setValue(new BigDecimal(ordQty));
                    EZDTBLAccessor.update(rsltHldMsg);
                }
            }
        }
    }

    private void updSoftAllocPk(S21ApiMessageMap msgMap, final String glblCmpyCd, final String shpgPlnNum, final BigDecimal setSoftAllocPk, final ONBATCH_TYPE onBatchType) {

        SOFT_ALLOCTMsgArray softAllocRcdList = searchSoftAllocForUpdate(glblCmpyCd, shpgPlnNum, onBatchType);

        for (int i = 0; i < softAllocRcdList.length(); i++) {

            if (hasValue(setSoftAllocPk) && hasValue(softAllocRcdList.no(i).setSoftAllocPk)) {
                softAllocRcdList.no(i).setSoftAllocPk.setValue(setSoftAllocPk);
            }

            // ***** Update
            SOFT_ALLOCTMsg softAllocRec = updSoftAlloc(softAllocRcdList.no(i));

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(softAllocRec.getReturnCode())) {
                msgMap.addXxMsgId("NWDM0007E");
                return;

            }
        }
    }

    private void compCreateProcess(S21ApiMessageMap msgMap, final String glblCmpyCd, final List<Map<String, Object>> getCompLst, final String setShpgPlnNum, final ONBATCH_TYPE onBatchType) {

        int ordQty = 0;
        int softAllocQty = 0;
        String newSetShpgPlnNum = null;
        String lineSubNum = "";
        SHPG_PLNTMsg edtShpgPlnRec = new SHPG_PLNTMsg();
        Map<String, Object> newShplgPlnNum = new HashMap<String, Object>();
        // START 2013/2/1 A.Suda [WDS Defect# 540]
        BigDecimal wkSpTotDealFrtAmt = BigDecimal.ZERO;
        BigDecimal wkSpTotFuncFrtAmt = BigDecimal.ZERO;
        // END 2013/2/1 A.Suda [WDS Defect# 540]

        if (getCompLst != null && !getCompLst.isEmpty()) {

            for (Map<String, Object> comp : getCompLst) {

                List<String> delShplgPlnNumLst = new ArrayList<String>();

                String lineSubNumComp = (String) comp.get("TRX_LINE_SUB_NUM");
                String deleteShpgPlnNum = (String) comp.get("SHPG_PLN_NUM");

                if (lineSubNum.equals(lineSubNumComp)) {
                    continue;
                }

                lineSubNum = lineSubNumComp;

                SHPG_PLNTMsg originalShpgPlnMsg = getShpgPlnFindByKey(glblCmpyCd, deleteShpgPlnNum);

                ordQty = originalShpgPlnMsg.ordQty.getValueInt();
                wkSpTotDealFrtAmt = wkSpTotDealFrtAmt.add(originalShpgPlnMsg.spTotDealFrtAmt.getValue());
                wkSpTotFuncFrtAmt = wkSpTotFuncFrtAmt.add(originalShpgPlnMsg.spTotFuncFrtAmt.getValue());

                // --------------------
                // logicalRemove SHPG_PLN
                // --------------------
                EZDTBLAccessor.logicalRemove(originalShpgPlnMsg);
                delShplgPlnNumLst.add(deleteShpgPlnNum);

                if (setShpgPlnNum == null) {

                    EZDMsg.copy(originalShpgPlnMsg, null, edtShpgPlnRec, null);

                    // --------------------
                    // create SHPG_PLN
                    // --------------------
                    newSetShpgPlnNum = insertShpgPln(msgMap, edtShpgPlnRec, null, ordQty, softAllocQty, wkSpTotDealFrtAmt, wkSpTotFuncFrtAmt);

                    newShplgPlnNum.put("DEL_ORIG_SHPG_PLN_NUM", deleteShpgPlnNum);
                    newShplgPlnNum.put("NEW_SHPG_PLN_NUM", newSetShpgPlnNum);
                    newShplgPlnNum.put("ORD_QTY", new BigDecimal(ordQty));

                // For Separated SHPG_PLN
                } else {

                    SHPG_PLNTMsgArray shpgPlnMsgArray = getSetShpgPlnFindByCondition(originalShpgPlnMsg, setShpgPlnNum, lineSubNum, onBatchType);

                    if (shpgPlnMsgArray.getValidCount() > 0) {

                        EZDMsg.copy(shpgPlnMsgArray.no(0), null, edtShpgPlnRec, null);

                        //Marge Qty and Amt
                        ordQty += shpgPlnMsgArray.no(0).ordQty.getValueInt();
                        wkSpTotDealFrtAmt = wkSpTotDealFrtAmt.add(originalShpgPlnMsg.spTotDealFrtAmt.getValue());
                        wkSpTotFuncFrtAmt = wkSpTotFuncFrtAmt.add(originalShpgPlnMsg.spTotFuncFrtAmt.getValue());

                        // --------------------
                        // logicalRemove SHPG_PLN
                        // --------------------
                        EZDTBLAccessor.logicalRemove(shpgPlnMsgArray.no(0));
                        delShplgPlnNumLst.add(shpgPlnMsgArray.no(0).shpgPlnNum.getValue());

                        // --------------------
                        // create SHPG_PLN
                        // --------------------
                        newSetShpgPlnNum = insertShpgPln(msgMap, edtShpgPlnRec, null, ordQty, softAllocQty, wkSpTotDealFrtAmt, wkSpTotFuncFrtAmt);

                        newShplgPlnNum.put("DEL_ORIG_SHPG_PLN_NUM", shpgPlnMsgArray.no(0).shpgPlnNum.getValue());
                        newShplgPlnNum.put("NEW_SHPG_PLN_NUM", newSetShpgPlnNum);
                        newShplgPlnNum.put("ORD_QTY", new BigDecimal(ordQty));

                    } else {

                        EZDMsg.copy(originalShpgPlnMsg, null, edtShpgPlnRec, null);

                        // --------------------
                        // create SHPG_PLN
                        // --------------------
                        newSetShpgPlnNum = insertShpgPln(msgMap, edtShpgPlnRec, null, ordQty, softAllocQty, wkSpTotDealFrtAmt, wkSpTotFuncFrtAmt);

                        newShplgPlnNum.put("DEL_ORIG_SHPG_PLN_NUM", deleteShpgPlnNum);
                        newShplgPlnNum.put("NEW_SHPG_PLN_NUM", newSetShpgPlnNum);
                        newShplgPlnNum.put("ORD_QTY", new BigDecimal(ordQty));
                    }
                }

                if (newSetShpgPlnNum == null) {
                    return;
                }

                // --------------------
                // update PRC_DTL
                // --------------------
                updPrcDtl(msgMap, delShplgPlnNumLst, newShplgPlnNum, onBatchType);

                // --------------------
                // update Sales HOLD
                // --------------------
                updateHoldForCancel(glblCmpyCd, originalShpgPlnMsg.shpgPlnNum.getValue(), newSetShpgPlnNum, ordQty, onBatchType);

                // --------------------------
                // logicalRemove OLD HOLD
                // --------------------------
                deleteHld(msgMap, delShplgPlnNumLst, onBatchType);

                if ("E".equals(getMessageKind(msgMap))) {
                    return;
                }
            }
        }
    }

    // QC#546 Add Start

    private void updateLoanBalanceQty(SHPG_PLNTMsg shpgPlnMsg, S21ApiMessageMap msgMap) {

        debug("-- >>>> [updateLoanBalanceQty] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        if (Arrays.asList(MODE_SHIPPED, MODE_VENDERINVOICERECEIVED).contains(param.shpgModeCd.getValue())) {

            // ===================================================================
            // Derive CPO
            // ===================================================================
            CPOTMsg cpoMsg = new CPOTMsg();

            ZYPEZDItemValueSetter.setValue(cpoMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoMsg.cpoOrdNum, shpgPlnMsg.trxHdrNum);
            cpoMsg = (CPOTMsg) S21CacheTBLAccessor.findByKey(cpoMsg);
            if (cpoMsg == null) {
//                msgMap.addXxMsgId(NWZM1207E);//QC#546 Del 2016/03/18
                return;
            }

            // ===================================================================
            // Category Context Type is LoanOrder.
            // ===================================================================
            if (existOrdCatgBizCtx(cpoMsg) > 0) {
                // ===================================================================
                // Derive CPO_DTL Load Balance Qty
                // ===================================================================
                BigDecimal loadBalQty = getLoanBalanceQty(shpgPlnMsg, cpoMsg);
                if (loadBalQty == null) {
//                    msgMap.addXxMsgId(NWAM0547E);//QC#546 Del 2016/03/18
                    return;
                }

                CPO_DTLTMsg cpoDtlMsg = new CPO_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(cpoDtlMsg.glblCmpyCd, param.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cpoDtlMsg.cpoOrdNum, shpgPlnMsg.trxHdrNum);
                ZYPEZDItemValueSetter.setValue(cpoDtlMsg.cpoDtlLineNum, shpgPlnMsg.trxLineNum);
                ZYPEZDItemValueSetter.setValue(cpoDtlMsg.cpoDtlLineSubNum, shpgPlnMsg.trxLineSubNum);

                cpoDtlMsg = (CPO_DTLTMsg) S21CacheTBLAccessor.findByKey(cpoDtlMsg);
                if (cpoDtlMsg == null) {
                    msgMap.addXxMsgId(NWAM0547E);
                    return;
                }

                loadBalQty = cpoDtlMsg.loanBalQty.getValue().add(param.ordQty.getValue());
                ZYPEZDItemValueSetter.setValue(cpoDtlMsg.loanBalQty, loadBalQty);

                S21FastTBLAccessor.update(cpoDtlMsg);
                if (!RTNCD_NOMAL.equals(cpoDtlMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0662E);
                    return;
                }
            }
        }

        debug("-- >>>> [updateLoanBalanceQty] END --");
    }


    private Integer existOrdCatgBizCtx(CPOTMsg cpoMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoMsg.glblCmpyCd.getValue());
        ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.LOAN_ORDER_VALUE_SET);
        ssmParam.put("dsOrdCatgCd", cpoMsg.dsOrdCatgCd.getValue());
        ssmParam.put("dsOrdTpCd", cpoMsg.dsOrdTpCd.getValue());
        ssmParam.put("dsOrdRsnCd", cpoMsg.dsOrdRsnCd.getValue());

        return (Integer) this.ssmBatchClient.queryObject("existOrdCatgBizCtx", ssmParam);
    }

    private BigDecimal getLoanBalanceQty(SHPG_PLNTMsg shpgPlnMsg, CPOTMsg cpoMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", shpgPlnMsg.trxHdrNum.getValue());
        ssmParam.put("cpoDtlLineNum", shpgPlnMsg.trxLineNum.getValue());
        ssmParam.put("cpoDtlLineSubNum", shpgPlnMsg.trxLineSubNum.getValue());
        ssmParam.put("dsOrdTpCd", cpoMsg.dsOrdTpCd.getValue());

        return (BigDecimal) this.ssmBatchClient.queryObject("getLoanBalanceQty", ssmParam);
    }

    // QC#546 Add End


    // 2015/09/10 CNA(2.9 Revenue Recognition) Add Start
    // Check Header Level: Delivery Basis
    private Boolean chkHeaderLvlDeliveryBasis(SHPG_PLNTMsg updShpgPlnMsg) {
        Boolean rslt = Boolean.FALSE;

        Map<String, String> queryKey = new HashMap<String, String>();
        queryKey.put("glblCmpyCd", updShpgPlnMsg.glblCmpyCd.getValue());
        queryKey.put("cpoOrdNum", updShpgPlnMsg.trxHdrNum.getValue());
        queryKey.put("revRecogMethCdPOD", REV_RECOG_METH.POD);

        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getPodOrder", queryKey);
        if (null != recCnt && recCnt.compareTo(BigDecimal.ZERO) > 0) {
            rslt = Boolean.TRUE;
        }
        return rslt;
    }
    private Boolean chkInConfigDeliveryBasis(SHPG_PLNTMsg updShpgPlnMsg) {
        Boolean rslt = Boolean.FALSE;

        Map<String, String> queryKey = new HashMap<String, String>();
        queryKey.put("glblCmpyCd", updShpgPlnMsg.glblCmpyCd.getValue());
        queryKey.put("cpoOrdNum", updShpgPlnMsg.trxHdrNum.getValue());
        queryKey.put("cpoDtlLineNum", updShpgPlnMsg.trxLineNum.getValue());
        queryKey.put("cpoDtlLineSubNum", updShpgPlnMsg.trxLineSubNum.getValue());
        queryKey.put("flgOnY", ZYPConstant.FLG_ON_Y);

        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getDeliveryBasicInConfig", queryKey);
        if (null != recCnt && recCnt.compareTo(BigDecimal.ZERO) > 0) {
            rslt = Boolean.TRUE;
        }
        return rslt;
    }
    // 2015/09/10 CNA(2.9 Revenue Recognition) Add End
    private List<SHPG_PLNTMsg> getToBeValidateShpgPln(SHPG_PLNTMsg shpgPlnMsg) {
        List<String> shpgStsLst = new ArrayList<String>();
//        shpgStsLst.add(SHPG_STS.HARD_ALLOCATED);
        shpgStsLst.add(SHPG_STS.VALIDATED);
        shpgStsLst.add(SHPG_STS.S_OR_O_CANCELLED);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
        ssmParam.put("trx_src_tp", TRX_SRC_TP.WHOLE_SALES);
        ssmParam.put("TrxHdrNum", shpgPlnMsg.trxHdrNum.getValue());
        ssmParam.put("TrxLineNum", shpgPlnMsg.trxLineNum.getValue());
        ssmParam.put("TrxLineSubNum", shpgPlnMsg.trxLineSubNum.getValue());
        ssmParam.put("shpg_sts", shpgStsLst);

        return (List<SHPG_PLNTMsg>) this.ssmBatchClient.queryObjectList("getToBeValidateShpgPln", ssmParam);
    }
    private SHPG_PLNTMsg getShpgPlnFindByKey(final String glblCmpyCd, final String setShpgPlnNum) {

        // Get SetItem SHPG_PLN
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        shpgPlnMsg.glblCmpyCd.setValue(glblCmpyCd);
        shpgPlnMsg.shpgPlnNum.setValue(setShpgPlnNum);

        return (SHPG_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(shpgPlnMsg);
    }
    private SHPG_PLNTMsgArray searchShpgPlnValidatedForUpdate(SHPG_PLNTMsg edtShpgPlnRec, ONBATCH_TYPE onBatchType) {

        SHPG_PLNTMsg vaShpgPlnMsg = new SHPG_PLNTMsg();
        vaShpgPlnMsg.setSQLID("020");
        vaShpgPlnMsg.setConditionValue("glblCmpyCd01", edtShpgPlnRec.glblCmpyCd.getValue());
        vaShpgPlnMsg.setConditionValue("trxHdrNum01", edtShpgPlnRec.trxHdrNum.getValue());
        vaShpgPlnMsg.setConditionValue("trxLineNum01", edtShpgPlnRec.trxLineNum.getValue());
        vaShpgPlnMsg.setConditionValue("trxLineSubNum01", edtShpgPlnRec.trxLineSubNum.getValue());
        vaShpgPlnMsg.setConditionValue("trxSrcTpCd01", edtShpgPlnRec.trxSrcTpCd.getValue());
        vaShpgPlnMsg.setConditionValue("shpgStsCd01", SHPG_STS.VALIDATED);
        vaShpgPlnMsg.setMaxCount(2);

        SHPG_PLNTMsgArray shpgPlnMsgRcd = null;
        if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
            shpgPlnMsgRcd = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(vaShpgPlnMsg);
        } else {
            shpgPlnMsgRcd = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(vaShpgPlnMsg);
        }

        return shpgPlnMsgRcd;
    }
    private String getShipgPlnSQ() {
        String shpgPlnCd;
        shpgPlnCd = ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqAccessor.SHPG_PLN_SQ, 8);
        return shpgPlnCd;
    }
    private String getDplyLineNum(SHPG_PLNTMsg edtShpgPlnRec) {
        Integer resNum = getShpgPlnDplyLineMum(edtShpgPlnRec);
        if (resNum == 0) {
            return null;
        } else {
            return String.format("%0" + 3 + "d", resNum + 1);
        }
    }
    private CPO_DTLTMsg getCpoDtlCache(SHPG_PLNTMsg edtShpgPlnRec) {

        // Get CPO_DTL
        CPO_DTLTMsg setCpoDtlMsg = new CPO_DTLTMsg();

        setCpoDtlMsg.glblCmpyCd.setValue(edtShpgPlnRec.glblCmpyCd.getValue());
        setCpoDtlMsg.cpoOrdNum.setValue(edtShpgPlnRec.trxHdrNum.getValue());
        setCpoDtlMsg.cpoDtlLineNum.setValue(edtShpgPlnRec.trxLineNum.getValue());
        setCpoDtlMsg.cpoDtlLineSubNum.setValue(edtShpgPlnRec.trxLineSubNum.getValue());

        CPO_DTLTMsg cpoDtlMsgRcd = (CPO_DTLTMsg) S21CacheTBLAccessor.findByKey(setCpoDtlMsg);

        return cpoDtlMsgRcd;

    }
    private SHPG_PLNTMsgArray searchShpgPlnNumForUpdate(SHPG_PLNTMsg edtShpgPlnRec, final String setShpgPlnNum, final ONBATCH_TYPE onBatchType) {

        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        shpgPlnMsg.setSQLID("033");
        shpgPlnMsg.setConditionValue("glblCmpyCd01", edtShpgPlnRec.glblCmpyCd.getValue());
        shpgPlnMsg.setConditionValue("trxHdrNum01", edtShpgPlnRec.trxHdrNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineNum01", edtShpgPlnRec.trxLineNum.getValue());
        shpgPlnMsg.setConditionValue("trxSrcTpCd01", edtShpgPlnRec.trxSrcTpCd.getValue());
        shpgPlnMsg.setConditionValue("setShpgPlnNum01", setShpgPlnNum);

        SHPG_PLNTMsgArray shpgPlnMsgRcd = null;
        if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
            shpgPlnMsgRcd = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(shpgPlnMsg);
        } else {
            shpgPlnMsgRcd = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(shpgPlnMsg);
        }

        return shpgPlnMsgRcd;
    }
    private int updSetShpgPlnNumByPartialValue(final String glblCmpyCd, final String setShpgPlnNum, final String newSetShpgPlnNum) {

        SHPG_PLNTMsg condShpgPlnMsg = new SHPG_PLNTMsg();
        SHPG_PLNTMsg inShpgPlnMsg = new SHPG_PLNTMsg();

        condShpgPlnMsg.glblCmpyCd.setValue(glblCmpyCd);
        condShpgPlnMsg.setShpgPlnNum.setValue(setShpgPlnNum);
        inShpgPlnMsg.setShpgPlnNum.setValue(newSetShpgPlnNum);

        return EZDTBLAccessor.updateByPartialValue(condShpgPlnMsg, new String[] {"glblCmpyCd", "setShpgPlnNum" }, inShpgPlnMsg, new String[] {"setShpgPlnNum" });

    }
    private int updAvalQtyByPartialValue(final String glblCmpyCd, final String setShpgPlnNum) {

        SHPG_PLNTMsg condShpgPlnMsg = new SHPG_PLNTMsg();
        SHPG_PLNTMsg inShpgPlnMsg = new SHPG_PLNTMsg();

        condShpgPlnMsg.glblCmpyCd.setValue(glblCmpyCd);
        condShpgPlnMsg.setShpgPlnNum.setValue(setShpgPlnNum);
        inShpgPlnMsg.avalSoQty.setValue(ZERO);
        inShpgPlnMsg.avalPoQty.setValue(ZERO);

        return EZDTBLAccessor.updateByPartialValue(condShpgPlnMsg, new String[] {"glblCmpyCd", "setShpgPlnNum" }, inShpgPlnMsg, new String[] {"avalSoQty", "avalPoQty" });

    }
    private SOFT_ALLOCTMsgArray searchSoftAllocForUpdate(final String glblCmpyCd, final String setShpgPlnNum, final ONBATCH_TYPE onBatchType) {

        SOFT_ALLOCTMsg setSoftAllocMsg = new SOFT_ALLOCTMsg();
        setSoftAllocMsg.setSQLID("003");
        setSoftAllocMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        setSoftAllocMsg.setConditionValue("shpgPlnNum01", setShpgPlnNum);

        SOFT_ALLOCTMsgArray softAllocRcd = null;
        if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
            softAllocRcd = (SOFT_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(setSoftAllocMsg);
        } else {
            softAllocRcd = (SOFT_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(setSoftAllocMsg);
        }

        return softAllocRcd;
    }
    private List<BigDecimal> getShpgLvlSalesHold(final String glblCmpyCd, final String shpgPlnNum) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("shpgPlnNum", shpgPlnNum);
        param.put("hldLvlCd", HLD_LVL.SHIPPING_PLAN_LEVEL);
        param.put("hldTpCd", HLD_TP.SALES_HOLD);

        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getShpgLvlSalesHold", param);

    }
    private List<Map<String, Object>> getVendorDrop(String glblCmpyCd, String trxHdrNum, String trxLineNum, String setShpgPlnNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("setShpgPlnNum", setShpgPlnNum);
        ssmParam.put("shpgStsCdVA", SHPG_STS.VALIDATED);
        ssmParam.put("on", ZYPConstant.FLG_ON_Y);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getVendorDrop", ssmParam);
    }

    private List<Map<String, Object>> getIntangible(String glblCmpyCd, String trxHdrNum, String trxLineNum, String setShpgPlnNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("setShpgPlnNum", setShpgPlnNum);
        ssmParam.put("shpgStsCdVA", SHPG_STS.VALIDATED);
        ssmParam.put("off", ZYPConstant.FLG_OFF_N);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getIntangible", ssmParam);
    }

    private int updSetSoftAllocPkByPartialValue(final String glblCmpyCd, final BigDecimal setSoftAllocPk, final BigDecimal newSetSoftAllocPk) {

        SOFT_ALLOCTMsg condSoftAllocMsg = new SOFT_ALLOCTMsg();
        SOFT_ALLOCTMsg inSoftAllocMsg = new SOFT_ALLOCTMsg();

        condSoftAllocMsg.glblCmpyCd.setValue(glblCmpyCd);
        condSoftAllocMsg.setSoftAllocPk.setValue(setSoftAllocPk);
        inSoftAllocMsg.setSoftAllocPk.setValue(newSetSoftAllocPk);

        return EZDTBLAccessor.updateByPartialValue(condSoftAllocMsg, new String[] {"glblCmpyCd", "setSoftAllocPk" }, inSoftAllocMsg, new String[] {"setSoftAllocPk" });
    }
    private SOFT_ALLOCTMsg updSoftAlloc(SOFT_ALLOCTMsg softAllocMsg) {

        EZDTBLAccessor.update(softAllocMsg);

        return softAllocMsg;
    }
    private PRC_DTLTMsgArray searchPrcDtlForUpdate(final String glblCmpyCd, final String shpgPlnNum, final ONBATCH_TYPE onBatchType) {

        PRC_DTLTMsg inMsg = new PRC_DTLTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shpgPlnNum01", shpgPlnNum);

        PRC_DTLTMsgArray retMsg = null;
        if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
            retMsg = (PRC_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
        } else {
            retMsg = (PRC_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);
        }

        return retMsg;
    }
    private HLDTMsgArray searchHldForUpdate(final String glblCmpyCd, final String shpgPlnNum, final String cpoOrdNum, final String cpoLineMum, final String cpoLineSubMum, final ONBATCH_TYPE onBatchType) {

        HLDTMsg inMsg = new HLDTMsg();
        inMsg.setSQLID("017");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shpgPlnNum01", shpgPlnNum);
        inMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        inMsg.setConditionValue("cpoDtlLineNum01", cpoLineMum);
        inMsg.setConditionValue("cpoDtlLineSubNum01", cpoLineSubMum);

        HLDTMsgArray retMsg = null;
        if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
            retMsg = (HLDTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
        } else {
            retMsg = (HLDTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);
        }

        return retMsg;
    }
    private Integer getShpgPlnDplyLineMum(SHPG_PLNTMsg shpgPlnMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
        ssmParam.put("trxHdrNum", shpgPlnMsg.trxHdrNum.getValue());
        ssmParam.put("trxLineNum", shpgPlnMsg.trxLineNum.getValue());
        ssmParam.put("trxLineSubNum", shpgPlnMsg.trxLineSubNum.getValue());

        return (Integer) this.ssmBatchClient.queryObject("getShpgPlnDplyLineMum", ssmParam);
    }
    private List<Map<String, Object>> queryShpgPlnEachCompForCancel(final String glblCmpyCd, final String trxSrcTpCd, final String trxHdrNum, final String trxLineNum, final String setShpgPlnNum) {

        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", glblCmpyCd);
        key.put("trxHdrNum", trxHdrNum);
        key.put("trxLineNum", trxLineNum);
        key.put("trxSrcTpCd", trxSrcTpCd);
        key.put("setShpgPlnNum", setShpgPlnNum);
        key.put("shpgStsCdHA", SHPG_STS.HARD_ALLOCATED);
        key.put("shpgStsCdSO", SHPG_STS.S_OR_O_CANCELLED);
        key.put("shpgStsCdPO", SHPG_STS.P_OR_O_CANCELLED);
        key.put("on", ZYPConstant.FLG_ON_Y);
        key.put("trxLineNumSet", "000");
        key.put("off", ZYPConstant.FLG_OFF_N);
        key.put("shpgStsCdVA", SHPG_STS.VALIDATED);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("queryShpgPlnEachCompForCancel", key);

    }
    private SHPG_PLNTMsgArray getSetShpgPlnFindByCondition(SHPG_PLNTMsg shpgPlnMsgRcd, final String setShpgPlnNum, final String trxLineSubNum, final ONBATCH_TYPE onBatchType) {

        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        shpgPlnMsg.setSQLID("035");
        shpgPlnMsg.setConditionValue("glblCmpyCd01", shpgPlnMsgRcd.glblCmpyCd.getValue());
        shpgPlnMsg.setConditionValue("trxHdrNum01", shpgPlnMsgRcd.trxHdrNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineNum01", shpgPlnMsgRcd.trxLineNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineSubNum01", trxLineSubNum);
        shpgPlnMsg.setConditionValue("trxSrcTpCd01", shpgPlnMsgRcd.trxSrcTpCd.getValue());
        shpgPlnMsg.setConditionValue("setShpgPlnNum01", setShpgPlnNum);

        SHPG_PLNTMsgArray shpgPlnMsgArray = null;
        if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
            shpgPlnMsgArray = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(shpgPlnMsg);
        } else {
            shpgPlnMsgArray = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(shpgPlnMsg);
        }

        return shpgPlnMsgArray;

    }
    private void insertPrcDtlFromshpgPlnNum(PRC_DTLTMsgArray prcDtlData, final String glblCmpyCd, final String shpgPlnNum, final BigDecimal updQty) {

        for (int i = 0; i < prcDtlData.length(); i++) {
            prcDtlData.no(i).glblCmpyCd.setValue(glblCmpyCd);
            prcDtlData.no(i).prcDtlPk.setValue(getPrcDtlPk());
            prcDtlData.no(i).shpgPlnNum.setValue(shpgPlnNum);
            prcDtlData.no(i).shipUnitQty.setValue(updQty);
            prcDtlData.no(i).dealNetAmt.setValue(multiply(prcDtlData.no(i).dealLastNetUnitPrcAmt.getValue(), updQty));
            prcDtlData.no(i).funcNetAmt.setValue(multiply(prcDtlData.no(i).funcLastNetUnitPrcAmt.getValue(), updQty));
            if (hasValue(prcDtlData.no(i).dealPerUnitFixAmt)) {
                prcDtlData.no(i).dealDiscAmt.setValue(multiply(prcDtlData.no(i).dealPerUnitFixAmt.getValue(), updQty));
            }
            if (hasValue(prcDtlData.no(i).funcPerUnitFixAmt)) {
                prcDtlData.no(i).funcDiscAmt.setValue(multiply(prcDtlData.no(i).funcPerUnitFixAmt.getValue(), updQty));
            }
            EZDTBLAccessor.insert(prcDtlData.no(i));

        }
    }
    private void insertHldFromshpgPlnNum(HLDTMsgArray hldData, final String glblCmpyCd, final String shpgPlnNum, final BigDecimal qty) {

        for (int i = 0; i < hldData.length(); i++) {
            hldData.no(i).glblCmpyCd.setValue(glblCmpyCd);
            hldData.no(i).hldPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HLD_SQ));
            hldData.no(i).shpgPlnNum.setValue(shpgPlnNum);
            hldData.no(i).hldQty.setValue(qty);

            EZDTBLAccessor.insert(hldData.no(i));

        }
    }
    private HARD_ALLOCTMsgArray searchHardAlloc(final String glblCmpyCd, final String shpgPlnNum, final ONBATCH_TYPE onBatchType) {

        HARD_ALLOCTMsg msg = new HARD_ALLOCTMsg();
        msg.setSQLID("009");
        msg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        msg.setConditionValue("shpgPlnNum01", shpgPlnNum);

        HARD_ALLOCTMsgArray msgArray = null;
        if (onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
            msgArray = (HARD_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(msg);
        } else {
            msgArray = (HARD_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(msg);
        }
        return msgArray;
    }
    private static BigDecimal add(BigDecimal big1, BigDecimal big2) {

        BigDecimal b1 = big1;
        BigDecimal b2 = big2;

        if (!hasValue(b1)) {
            b1 = ZERO;
        }
        if (!hasValue(b2)) {
            b2 = ZERO;
        }
        return b1.add(b2);
    }
    private static BigDecimal multiply(BigDecimal big1, BigDecimal big2) {

        BigDecimal b1 = big1;
        BigDecimal b2 = big2;

        if (!hasValue(b1)) {
            b1 = ZERO;
        }
        if (!hasValue(b2)) {
            b2 = ZERO;
        }
        return b1.multiply(b2);
    }

    private static BigDecimal roundHalfUp(BigDecimal big) {

        return big.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal subtract(BigDecimal big1, BigDecimal big2) {

        BigDecimal b1 = big1;
        BigDecimal b2 = big2;

        if (!hasValue(b1)) {
            b1 = ZERO;
        }
        if (!hasValue(b2)) {
            b2 = ZERO;
        }
        return b1.subtract(b2);
    }
    // 2015/09/10 CNA(2.8 Allocation - SO/PO) Add End

    // 2016/06/20 S21_NA#594 Add Start
    private void partialShip(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        debug("-- MODE [partialShip] START --");

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        // ===================================================================
        // (2) a) Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (2) b) Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (2) c) Order Qty Check
        // ===================================================================
        BigDecimal dbOrdQty = shpgPlnMsg.ordQty.getValue();
        BigDecimal paramOrdQty = param.ordQty.getValue();
        if (paramOrdQty.compareTo(dbOrdQty) >= 0) {
            msgMap.addXxMsgId(NWZM0339E);
            msgMap.flush();
            return;
        }

        // ===================================================================
        // (3) b) Shipping Plan Update Mode for S/O Cancel
        // ===================================================================
        SHPG_PLN_UPD_MODETMsg shpgPlnUpdModeTMsg4SoCanc = new SHPG_PLN_UPD_MODETMsg();
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdModeTMsg4SoCanc.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdModeTMsg4SoCanc.shpgPlnUpdModeCd, SHPG_PLN_UPD_MODE.HARD_ALLOCATED_S_OR_O_CANCELLED);
        shpgPlnUpdModeTMsg4SoCanc = (SHPG_PLN_UPD_MODETMsg) S21CacheTBLAccessor.findByKey(shpgPlnUpdModeTMsg4SoCanc);
        // ===================================================================
        // (3) c) i) Insert new SHPG_PLN
        // ===================================================================
        // SHPG_PLNTMsg divShpgPlnMsg = getPartialDivShpgPln(spUpdModeMsg, msgMap, shpgPlnMsg, inEachQty);
        SHPG_PLNTMsg divShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, divShpgPlnMsg, null);
        // 2018/08/09 S21_NA#26865 Mod Start
//        dividesShippingplan(divShpgPlnMsg, spUpdModeMsg, msgMap);
        dividesShippingplan(divShpgPlnMsg, spUpdModeMsg, msgMap, onBatchType);
        // 2018/08/09 S21_NA#26865 Mod End

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (3) c) ii) Update Original  SHPG_PLN
        // ===================================================================
        SHPG_PLNTMsg divOrigShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, divOrigShpgPlnMsg, null);
        // 2018/08/09 S21_NA#26865 Mod Start
//        updateOriginalShippingplan(divOrigShpgPlnMsg, shpgPlnUpdModeTMsg4SoCanc, msgMap);
        updateOriginalShippingplan(divOrigShpgPlnMsg, shpgPlnUpdModeTMsg4SoCanc, msgMap, onBatchType);
        // 2018/08/09 S21_NA#26865 Mod End
        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4) Devide Pricing Detail
        // ===================================================================
        boolean isCpoExistanceSrcTp = ZYPConstant.FLG_ON_Y.equals(getCPOExistenceFlagKeyTransactionSourceTypeCode(param.glblCmpyCd.getValue(), shpgPlnMsg.trxSrcTpCd.getValue()));
        if (isCpoExistanceSrcTp) {
            // dividesPricingDetail(shpgPlnMsg, divShpgPlnMsg, null, msgMap); 2016/07/26 S21_NA#594-2 Del
            dividesPricingDetail(shpgPlnMsg, divShpgPlnMsg, divOrigShpgPlnMsg, msgMap); // 2016/07/26 S21_NA#594-2 Add

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        // ===================================================================
        // (5) HOLD Apply
        // ===================================================================
        if (isCpoExistanceSrcTp) {
            // callAddHoldInfoAPI(shpgPlnMsg, msgMap, onBatchType); 2016/07/26 S21_NA#594-2 Del Start
            callAddHoldInfoAPI(divOrigShpgPlnMsg, msgMap, onBatchType); // 2016/07/26 S21_NA#594-2 Add

            // Error Judgment
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        // ===================================================================
        // (6) Devide Hard Allocation
        // ===================================================================
        // dividesHardAllocation(shpgPlnMsg, divShpgPlnMsg, null, msgMap); 2016/07/26 S21_NA#594-2 Del
        dividesHardAllocation(shpgPlnMsg, divShpgPlnMsg, divOrigShpgPlnMsg, msgMap); // 2016/07/26 S21_NA#594-2 Add
        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7) Cancel Hard Allocation
        // ===================================================================
        applyAllocationCancel(msgMap, divOrigShpgPlnMsg, onBatchType);

        // ===================================================================
        // (8)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_PARTIAL_SHIPPED, shpgPlnMsg);

        debug("-- MODE [partialShip] END --");
    }

    /* 07/11/2016 CSAI Y.Imazu Add QC#10917 START */
    /**
     * arrivedShpgPln
     * @param spUpdModeMsg SHPG_PLN_UPD_MODETMsg
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void arrivedShpgPln(SHPG_PLN_UPD_MODETMsg spUpdModeMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();
        // ===================================================================
        // (1)Search Shipping Plan
        // (2)Search CPO Detail（select for update）
        // ===================================================================
        // none

        // ===================================================================
        // (3)Search Shipping Plan（select for update）
        // ===================================================================
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        searchShippingPlanForUpdate(shpgPlnMsg, null, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (4)Check Status transition
        // ===================================================================
        chkStatus(shpgPlnMsg, msgMap);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (5)Check Indispensability, Value
        // ===================================================================
        // none

        // ===================================================================
        // (6)Update Shipping Plan
        // ===================================================================
        Map<String, Boolean> chkHdrLvl = new HashMap<String, Boolean>();
        NWZC044001 addHldInfoApi = new NWZC044001();
        List<NWZC044001PMsg> addHldInfoApiPMsgList = new ArrayList<NWZC044001PMsg>();

        SHPG_PLNTMsg updShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(shpgPlnMsg, null, updShpgPlnMsg, null);
        updateShpgPln(updShpgPlnMsg, spUpdModeMsg, msgMap, 0, onBatchType);

        // Error Judgment
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ===================================================================
        // (7)Divides Shipping Plan
        // (8)Update Divides Shipping Plan
        // (9)Check Update Shipping Plan
        // (10)Divides Pricing Detail
        // (11)Divides Soft Allocation
        // (12)Update Shipping Plan(Soft Allocation Quantity)
        // (13)Update CPO Detail
        // ===================================================================
        // none

        // ===================================================================
        // (14)Business Process Log
        // ===================================================================
        outProcessLog(param, EVENTID_ARRIVED, shpgPlnMsg);

        // ===================================================================
        // (15)Process Hold
        // (16)Update Hold
        // (17)Divides Hard Allocation
        // (18)Divides SetComponent Divide
        // (19)Update Set Shipping Plan
        // ===================================================================
        // none
        
        // 2018/04/26 S21_NA#25784 Add Start
        if (SHPG_STS.INSTALLED.equals(updShpgPlnMsg.shpgStsCd.getValue()) //
                || SHPG_STS.INVOICED.equals(updShpgPlnMsg.shpgStsCd.getValue())//
                || SHPG_STS.CANCELLED.equals(updShpgPlnMsg.shpgStsCd.getValue())
                || SHPG_STS.ARRIVED.equals(shpgPlnMsg.shpgStsCd.getValue()) // 2019/03/08 S21_NA#30550 Add
        ) {
            return;
        }
        // 2018/04/26 S21_NA#25784 Add End

        // Check Header Level: Delivery Basis
        Boolean rsltHdr = chkHdrLvl.get(updShpgPlnMsg.trxHdrNum.getValue());
        if (null == rsltHdr) {
            rsltHdr = chkHeaderLvlDeliveryBasis(updShpgPlnMsg);
            chkHdrLvl.put(updShpgPlnMsg.trxHdrNum.getValue(), rsltHdr);
        }
        Boolean rsltConfig = chkInConfigDeliveryBasis(updShpgPlnMsg);

        if (rsltHdr.booleanValue() && rsltConfig.booleanValue()) {
            // set Billing Hold
            NWZC044001PMsg hldInfoApiPMsg = new NWZC044001PMsg();
            hldInfoApiPMsg.glblCmpyCd.setValue(updShpgPlnMsg.glblCmpyCd.getValue());
            hldInfoApiPMsg.cpoOrdNum.setValue(updShpgPlnMsg.trxHdrNum.getValue());
            hldInfoApiPMsg.cpoDtlLineNum.setValue(updShpgPlnMsg.trxLineNum.getValue());
            hldInfoApiPMsg.cpoDtlLineSubNum.setValue(updShpgPlnMsg.trxLineSubNum.getValue());
            hldInfoApiPMsg.shpgPlnNum.setValue(updShpgPlnMsg.shpgPlnNum.getValue());
            hldInfoApiPMsg.hldRsnCd.setValue(HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION);
            hldInfoApiPMsg.slsDt.setValue(param.actlArvDt.getValue());
            //hldInfoApiPMsg.hldUntilDt.clear();
            //hldINfoApiPMsg.hldAplyRsnCd.clear();
            addHldInfoApiPMsgList.add(hldInfoApiPMsg);
        }

        if (!addHldInfoApiPMsgList.isEmpty()) {
            addHldInfoApi.execute(addHldInfoApiPMsgList, onBatchType);
        }
        
        // 2019/03/08 S21_NA#30550 Add Start
        for (NWZC044001PMsg pMsg : addHldInfoApiPMsgList) {
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = pMsg.xxMsgIdList.getValidCount() - 1; i >= 0; i--) {
                    final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    if (xxMsgId != null) {
                        if (!"E".equals(getMessageKind(msgMap))) {
                            msgMap.addXxMsgId(xxMsgId);
                        }
                    }
                }
            }
        }
        // 2019/03/08 S21_NA#30550 Add End
    }
    /* 07/11/2016 CSAI Y.Imazu Add QC#10917 END */

    private MDSE_STORE_PKGTMsg getStorePkg(String glblCmpyCd, SHPG_PLNTMsg shpgPlnMsg) {

        MDSE_STORE_PKGTMsg mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();

        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.pkgUomCd, shpgPlnMsg.custUomCd);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, shpgPlnMsg.mdseCd);

        return (MDSE_STORE_PKGTMsg) S21CacheTBLAccessor.findByKey(mdseStorePkgTMsg);
    }

    private BigDecimal getOrdUomQty(BigDecimal ordQty, BigDecimal inEachQty) {

        return ordQty.divide(inEachQty, 0, BigDecimal.ROUND_UP);
    }
    // 2016/06/20 S21_NA#594 Add End

    private void callDplyOrdStsUpdApi(S21ApiMessageMap msgMap, List<String> shpgPlnNumList, final ONBATCH_TYPE onBatchType, String trxHdrNum) {

        NWZC003001PMsg param = (NWZC003001PMsg) msgMap.getPmsg();

        CPOTMsg cpoTMsg = new CPOTMsg();
        setValue(cpoTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        setValue(cpoTMsg.cpoOrdNum, trxHdrNum);
        cpoTMsg = (CPOTMsg) EZDTBLAccessor.findByKey(cpoTMsg);

        if (cpoTMsg == null) {
            return;
        }

        NWZC188001 dplyOrdStsUpdApi = new NWZC188001();
        final NWZC188001PMsg pMsg = new NWZC188001PMsg();

        setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        setValue(pMsg.cpoOrdNum, trxHdrNum);

        // QC#21821 ADD START
        if (MODE_HARDALLOCATED_SOCANCELLED.equals(param.shpgModeCd.getValue())) {
            setValue(pMsg.cancFlg, ZYPConstant.FLG_ON_Y);
        }
        // QC#21821 ADD END

        for (int i = 0; i < shpgPlnNumList.size(); i++) {
            NWZC188001_shpgPlnNumListPMsg shpgPlnNumListPMsg = pMsg.shpgPlnNumList.no(i);
            ZYPEZDItemValueSetter.setValue(shpgPlnNumListPMsg.shpgPlnNum, shpgPlnNumList.get(i));
        }
        pMsg.shpgPlnNumList.setValidCount(shpgPlnNumList.size());

        dplyOrdStsUpdApi.execute(pMsg, onBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }

        for (int i = 0; i < pMsg.shpgPlnNumList.getValidCount(); i++) {
            NWZC188001_shpgPlnNumListPMsg shpgPlnNumListPMsg = pMsg.shpgPlnNumList.no(i);

            if (ZYPCommonFunc.hasValue(shpgPlnNumListPMsg.xxMsgId)) {
                msgMap.addXxMsgId(shpgPlnNumListPMsg.xxMsgId.getValue());
            }
        }
    }

    // 2023/06/02 QC#61383 Add Start
    private void insertForCustNtfyShipInfo(S21ApiMessageMap msgMap, String originShpgStsCd, SHPG_PLNTMsg shpgPlnMsg, ONBATCH_TYPE onBatchType) {
        Map<String, Object> rsultMap = getShpgSts(shpgPlnMsg.glblCmpyCd.getValue(), shpgPlnMsg.shpgPlnNum.getValue());

        if (rsultMap == null) {
            return;
        }
        if (!isTargetOrdCatgBizCtx(rsultMap, shpgPlnMsg)) {
            return;
        }
        if (!isTargetShpgStsUpdOrder(originShpgStsCd, shpgPlnMsg)) {
            return;
        }
        if (!isRegisterCustNtfyShipInfo(shpgPlnMsg.glblCmpyCd.getValue(), rsultMap.get("CPO_ORD_NUM").toString(), shpgPlnMsg.shpgPlnNum.getValue())) {
            return;
        }

        CUST_NTFY_SHIP_INFOTMsg custNtfyShipInfoTMsg = new CUST_NTFY_SHIP_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(custNtfyShipInfoTMsg.glblCmpyCd, shpgPlnMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(custNtfyShipInfoTMsg.custNtfyShipInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CUST_NTFY_SHIP_INFO_SQ));
        ZYPEZDItemValueSetter.setValue(custNtfyShipInfoTMsg.cpoOrdNum, rsultMap.get("CPO_ORD_NUM").toString());
        ZYPEZDItemValueSetter.setValue(custNtfyShipInfoTMsg.shpgPlnNum, shpgPlnMsg.shpgPlnNum.getValue());
        ZYPEZDItemValueSetter.setValue(custNtfyShipInfoTMsg.procStsCd, PROC_STS.IN_COMPLETED);

        S21FastTBLAccessor.insert(custNtfyShipInfoTMsg);
    }

    private Map<String, Object> getShpgSts(String glblCmpyCd, String shpgPlnNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shpgPlnNum", shpgPlnNum);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getShpgSts", ssmParam);
    }

    private boolean isTargetOrdCatgBizCtx(Map<String, Object> rsultMap, SHPG_PLNTMsg shpgPlnMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
        ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP_CD_01);
        ssmParam.put("dsOrdCatgCd", rsultMap.get("DS_ORD_CATG_CD").toString());
        if (rsultMap.get("DS_ORD_TP_CD") == null) {
            ssmParam.put("dsOrdTpCd", null);
        } else {
            ssmParam.put("dsOrdTpCd", rsultMap.get("DS_ORD_TP_CD").toString());
        }
        if (rsultMap.get("DS_ORD_RSN_CD") == null) {
            ssmParam.put("dsOrdRsnCd", null);
        } else {
            ssmParam.put("dsOrdRsnCd", rsultMap.get("DS_ORD_RSN_CD").toString());
        }

        BigDecimal returnCd = (BigDecimal) this.ssmBatchClient.queryObject("getTargetOrdCatgBizCtx", ssmParam);
        if (BigDecimal.ZERO.equals(returnCd)) {
            return false;
        }
        return true;
    }

    private boolean isTargetShpgStsUpdOrder(String originShpgStsCd, SHPG_PLNTMsg shpgPlnMsg) {
        String afterShpgStsCd = shpgPlnMsg.shpgStsCd.getValue();
        if (!ZYPCommonFunc.hasValue(originShpgStsCd) || !ZYPCommonFunc.hasValue(afterShpgStsCd)) {
            return false;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
        ssmParam.put("dsCondConstGrpId", DS_COND_CONST_GRP_ID_01);
        ssmParam.put("dsCondConstValTxt01", afterShpgStsCd);

        String dsCondConstValTxt02 = (String) this.ssmBatchClient.queryObject("getDsCondConst", ssmParam);
        if (!ZYPCommonFunc.hasValue(dsCondConstValTxt02)) {
            return false;
        }
        if (DS_COND_CONST_ASTER.equals(dsCondConstValTxt02)) {
            if (!afterShpgStsCd.equals(originShpgStsCd)) {
                return true;
            } else {
                return false;
            }
        }
        if (!originShpgStsCd.equals(dsCondConstValTxt02)) {
            return true;
        }
        return false;
    }

    private boolean isRegisterCustNtfyShipInfo(String glblCmpyCd, String cpoOrdNum, String shpgPlnNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("shpgPlnNum", shpgPlnNum);

        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("getCustNtfyShipInfoCount", ssmParam);
        if (BigDecimal.ZERO.equals(count)) {
            return true;
        }
        return false;
    }
    // 2023/06/02 QC#61383 Add End
}
