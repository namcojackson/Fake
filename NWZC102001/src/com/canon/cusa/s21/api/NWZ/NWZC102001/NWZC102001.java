/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * Allocation API(NWZC102001) 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/24   Fujitsu         A.Suda          Create          N/A
 * 2009/11/05   Fujitsu         R.Watanabe      Update          1529
 * 2009/12/01   Fujitsu         N.Yamamoto      Update          2165
 * 2010/04/26   Fujitsu         N.Yamamoto      Update          5989
 * 05/20/2010   Fujitsu         A.Suda          Update          5902
 * 07/01/2010   Fujitsu         A.Suda          Update          7500
 * 08/27/2010   Fujitsu         A.Suda          Update          203(All2)
 * 11/08/2010   Fujitsu         K.Tajima        Update          Performance tuning (when call by Order Entry Screen and Hard Allocation New Process.)
 * 12/07/2010   CSAI            T.Gotoda        Update          782(All2)
 * 04/18/2011   CSAI            T.Gotoda        Update          1763
 * 2017/04/12   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.1
 * 2017/07/25   Fujitsu         T.Murai         Update          S21_NA#5872(L3#001)
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC102001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByConditionForUpdate;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.insert;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.update;
import static com.canon.cusa.s21.framework.common.S21StringUtil.isEquals;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdateAPI;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSETMsg;
import business.db.MDSE_WH_CONDTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.SOFT_ALLOCTMsg;
import business.db.TRX_SRC_TPTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.parts.NWZC102001PMsg;
//import business.parts.NWZC103001PMsg;
//import business.parts.NWZC103002PMsg;
import business.parts.NWZC104001PMsg;
import business.parts.NWZC104002PMsg;

//import com.canon.cusa.s21.api.NWZ.NWZC103001.NWZC103001;
import com.canon.cusa.s21.api.NWZ.NWZC104001.NWZC104001;
import com.canon.cusa.s21.common.NWX.NWXC002007.NWXC002007BackOrder;
import com.canon.cusa.s21.common.NWX.NWXC002007.NWXC002007BackOrderParam;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXOrdTakeMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_PLN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SOFT_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWZC102001 extends S21ApiCommonBase {

    // **********************
    // Parameter
    // **********************
    /** Request Type 1: New Allocation */
    public static final String REQ_TP_NEW             = "1";
    /** Request Type 2: Order Detail Cancel */
    public static final String REQ_TP_ORD_CANCEL      = "2";
    /** Request Type 3: Shipping Plan Level Cancel */
    public static final String REQ_TP_SHIPPING_CANCEL = "3";

    /** OrigFuncTpCd 1 : Order Entry */
    public static final String ORG_FUNC_CD_ORDER_ENTRY  = "1";
    /** OrigFuncTpCd 2 : Soft Allocation */
    public static final String ORG_FUNC_CD_SOFT_ALLOC   = "2";
    /** OrigFuncTpCd 3 : Hard Allocation */
    public static final String ORG_FUNC_CD_HARD_ALLOC   = "3";
    /** OrigFuncTpCd 4 : Distribution */
    public static final String ORG_FUNC_CD_DISTRIBUTION = "4";

    /** AllocTypeCd 1 : Soft Alloction only */
    public static final String ALLOC_TP_CD_SOFT_ALLOC      = "1";
    /** AllocTypeCd 2 : Hard Alloction only */
    public static final String ALLOC_TP_CD_HARD_ALLOC      = "2";
    /** AllocTypeCd 3 : Soft/Hard Allocation */
    public static final String ALLOC_TP_CD_SOFT_HARD_ALLOC = "3";

    // **********************
    // Message
    // **********************
    /** MessageID */
    public static final String NWZM0188E = "NWZM0188E";
    public static final String NWZM0189E = "NWZM0189E";
    public static final String NWZM0014E = "NWZM0014E";
    public static final String NWZM0027E = "NWZM0027E";
    public static final String NWZM0089E = "NWZM0089E";
    public static final String NWZM0043E = "NWZM0043E";
    public static final String NWZM0346E = "NWZM0346E";
    public static final String NWZM0021E = "NWZM0021E";
    public static final String NWZM0364E = "NWZM0364E";
    public static final String NWZM0047E = "NWZM0047E";
    public static final String NWZM0447I = "NWZM0447I";
    public static final String NWZM0366I = "NWZM0366I";
    public static final String NWZM0402E = "NWZM0402E";
    public static final String NWZM0170E = "NWZM0170E";
    public static final String NWZM0210E = "NWZM0210E";
    public static final String NWZM0171E = "NWZM0171E";
    public static final String NWZM0211E = "NWZM0211E";
    public static final String NWZM0172E = "NWZM0172E";
    public static final String NWZM0212E = "NWZM0212E";
    public static final String NWZM0196E = "NWZM0196E";
    public static final String NWZM0178E = "NWZM0178E";
    public static final String NWZM0358E = "NWZM0358E";
    public static final String NWZM0175E = "NWZM0175E";
    public static final String NWZM0049E = "NWZM0049E";
    public static final String NWZM0228E = "NWZM0228E";
    public static final String NWZM0184E = "NWZM0184E";
    public static final String NWZM0197E = "NWZM0197E";
    public static final String NWZM0194E = "NWZM0194E";
    public static final String NWZM0179E = "NWZM0179E";
    public static final String NWZM0180E = "NWZM0180E";
    public static final String NWZM0181E = "NWZM0181E";
    public static final String NWZM0227E = "NWZM0227E";
    public static final String NWZM0185E = "NWZM0185E";
    public static final String NWZM0208E = "NWZM0208E";
    public static final String NWZM0359E = "NWZM0359E";
    public static final String NWZM0191E = "NWZM0191E";
    public static final String NWZM0192E = "NWZM0192E";
    public static final String NWZM0360E = "NWZM0360E";
    public static final String NWZM0176E = "NWZM0176E";
    public static final String NWZM0226E = "NWZM0226E";
    public static final String NWZM0362E = "NWZM0362E";
    public static final String NWZM0213E = "NWZM0213E";
    public static final String NWZM0190E = "NWZM0190E";
    public static final String NWZM0214E = "NWZM0214E";
    public static final String NWZM0209E = "NWZM0209E";
    public static final String NWZM0075E = "NWZM0075E";
    public static final String NWZM0200E = "NWZM0200E";
    public static final String NWZM0737E = "NWZM0737E";
    public static final String NWZM0446E = "NWZM0446E";
    public static final String NWZM0167E = "NWZM0167E";
    public static final String NWZM0168E = "NWZM0168E";
    public static final String NWZM0169E = "NWZM0169E";
    public static final String NWZM0174E = "NWZM0174E";
    public static final String NWZM0662E = "NWZM0662E";
    public static final String NWZM0673E = "NWZM0673E";
    public static final String NWZM0948E = "NWZM0948E";
    public static final String NWZM0949E = "NWZM0949E";
    public static final String NWZM0950E = "NWZM0950E";

    /** PRTL_ACPT_FLG minimum value */
    public static final int PRTL_ACPT_FLG_MIN = 0;
    /** PRTL_ACPT_FLG max value */
    public static final int PRTL_ACPT_FLG_MAX = 1;

    /** WH_FLIP_ACPT_FLG minimum value */
    public static final int WH_FLIP_ACPT_FLG_MIN = 0;
    /** WH_FLIP_ACPT_FLG max value */
    public static final int WH_FLIP_ACPT_FLG_MAX = 1;

    /** RQST_TP_CD minimum value */
    public static final int RQST_TP_CD_MIN = 1;
    /** RQST_TP_CD max value */
    public static final int RQST_TP_CD_MAX = 3;

    /** ITEM_FLIP_ACPT_FLG minimum value */
    public static final int ITEM_FLIP_ACPT_FLG_MIN = 0;
    /** ITEM_FLIP_ACPT_FLG max value */
    public static final int ITEM_FLIP_ACPT_FLG_MAX = 1;

    /** ALLOC_TP_CD minimum value */
    public static final int ALLOC_TP_CD_MIN = 1;
    /** ALLOC_TP_CD max value */
    public static final int ALLOC_TP_CD_MAX = 3;
    
    /** ORIG_FUNC_TP_CD max value */
    public static final int ORIG_FUNC_TP_CD_MAX = 4;
    /** ORIG_FUNC_TP_CD minimum value */
    public static final int ORIG_FUNC_TP_CD_MIN = 1;

    /** PRTL_ACPT_FLG */
    public static final String PRTL_ACPT_FLG_ON = "1";

    /** OrdTakeMdseFlg 0: 8Digit OrderTake */
    private static final String ORD_TAKE_MDSE_CD_FLG = "0";

    /** OrdTakeMdseFlg 1: 10Digit Item */
    private static final String MDSE_CD_FLG = "1";

    /** TRX_LINE_SUB_NUM_SET */
    private static final String TRX_LINE_SUB_NUM_SET = "000";

    /** Normal end */
    private static final String RTNCD_NORMAL = EZDTBLAccessor.RTNCD_NORMAL;

    private static final String Y = ZYPConstant.FLG_ON_Y;
    private static final String N = ZYPConstant.FLG_OFF_N;
    
    /**
     * digit of 'ORD_TAKE_MDSE_CD'.
     */
    private static final int ORD_TAKE_MDSE_CD_DIGIT = new ORD_TAKE_MDSETMsg().getAttr("ordTakeMdseCd").getDigit();

    private static final List<String> hlbLvlCdList;

    static {
        hlbLvlCdList = new ArrayList<String>();
        hlbLvlCdList.add(HLD_LVL.CPO_DETAIL_LEVEL);
        hlbLvlCdList.add(HLD_LVL.SHIPPING_PLAN_LEVEL);
    }

    /** Soft Allocation API */
//    private final NWZC103001 softAllocAPI = new NWZC103001();
    /** Hard Allocation API */
    private final NWZC104001 hardAllocAPI = new NWZC104001();

    private final S21LRUMap<String, VNDTMsgArray> vendorDropTMsgCache = new S21LRUMap<String, VNDTMsgArray>();

    private final S21SsmBatchClient ssmClient;
    
    public NWZC102001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * NWZC102001 Allocation API
     * 
     * <pre>
     * The Soft/HardAllocation execution is controlled for the Allocating execution demand. 
     * </pre>
     * 
     * @param param NWZC102001PMsg Interface
     * @param onBatchType onBatchType
     */

    public void execute(final NWZC102001PMsg param, final ONBATCH_TYPE onBatchType) {
        final String methodNm = "execute";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {
            
            final S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
    
            this.doProcess(msgMap, onBatchType);
            
            msgMap.flush();
            
        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /**
     * NWZC102001 Allocation API (Changeable list type)
     * @param params NWZC102001PMsg Interface
     * @param onBatchType onBatchType
     */
    public void execute(final List<NWZC102001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NWZC102001PMsg param : params) {
            this.execute(param, onBatchType);
            if (S21ApiUtil.isXxMsgId(param)) {
                return;
            }
        }
    }

    protected void doProcess(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
            
        final NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();

        chkCommonInputParam(msgMap);
        if (hasMessage(msgMap)) {
            return;
        }

        chkTrxSrcTp(msgMap);
        if (hasMessage(msgMap)) {
            return;
        }

        /**************************************************
         * New Allocation
         **************************************************/
        if (REQ_TP_NEW.equals(param.xxRqstTpCd.getValue())) {

            final MdseData mdseInfo = this.initProcess(msgMap);
            if (mdseInfo == null || hasMessage(msgMap)) {
                return;
            }

            chkInputParamNew(msgMap, mdseInfo);
            if (hasMessage(msgMap)) {
                return;
            }

            this.newProcess(msgMap, mdseInfo, onBatchType);

        /**************************************************
         * Cancel Allocation
         **************************************************/
        } else {

            chkInputParamCancel(msgMap);
            if (hasMessage(msgMap)) {
                return;
            }

            this.cancelProcess(msgMap, onBatchType);
        }
    }

    void cancelProcess(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
        final String methodNm = "cancelProcess";
        writePerformanceProfilingLogStart(getClass(), methodNm);
        
        try {
            
            this.execHardAllocCancel(msgMap, onBatchType);
    
            if (hasMessage(msgMap)) {
                return;
            }

            // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//            this.execSoftAllocCancel(msgMap, onBatchType);
            // 2017/04/12 S21_NA#Review structure Lv.1 Del End

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    void newProcess(S21ApiMessageMap msgMap, final MdseData mdseInfo, final ONBATCH_TYPE onBatchType) {
        final String methodNm = "newProcess";
        writePerformanceProfilingLogStart(getClass(), methodNm);
        
        try {
            
            // Lock SHPG_PLN for 'Validated'
            final SHPG_PLNTMsg shpgPlnMsg = lockShpgPln(msgMap, SHPG_STS.VALIDATED);
            if (hasMessage(msgMap)) {
                return;
            }
    
            // Soft Allocation API
            final boolean hardAllocExecFlg = this.softAllocNewProcess(msgMap, mdseInfo, onBatchType, shpgPlnMsg);
            if (hasMessage(msgMap)) {
                return;
            }
    
            // Hard Allocation API
            if (hardAllocExecFlg) {
    
                this.hardAllocNewProcess(msgMap, mdseInfo, onBatchType, shpgPlnMsg);
                if (hasMessage(msgMap)) {
                    return;
                }
            }
            
        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private BigDecimal calcTotalDistAvalQty(final List<NWZC103002PMsg> distributionList) {
//
//        BigDecimal totalDistAvalQty = ZERO;
//
//        for (NWZC103002PMsg pmsg : distributionList) {
//            totalDistAvalQty = totalDistAvalQty.add(pmsg.avalQty.getValue());
//        }
//
//        return totalDistAvalQty;
//
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    @SuppressWarnings("unchecked")
    private boolean chkDistStruSegMdseAsg(S21ApiMessageMap msgMap) {

        NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",         param.glblCmpyCd.getValue());
        ssmParam.put("mdseCd",             param.mdseCd.getValue());
        ssmParam.put("trxSrcTpCd",         param.trxSrcTpCd.getValue());
        ssmParam.put("trxHdrNum",          param.trxHdrNum.getValue());
        ssmParam.put("trxLineNum",         param.trxLineNum.getValue());
        ssmParam.put("trxLineSubNum",      param.trxLineSubNum.getValue());
        ssmParam.put("softAllocTpCd",      SOFT_ALLOC_TP.NOT_NEED_TO_APPROVE);
        ssmParam.put("invtySoftAllocTpCd", SOFT_ALLOC_TP.MANUAL);

        final List<String> distStruSegMdseAsgList = (List<String>) ssmClient.queryObjectList("queryDistStruSegMdseAsg", ssmParam);

        if (distStruSegMdseAsgList != null && !distStruSegMdseAsgList.isEmpty()) {
            return FALSE;
        } else {
            return TRUE;
        }
    }

    private boolean chkSoftAllocBackOrder(S21ApiMessageMap msgMap) {

        NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();

        final Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        if (param.mdseCd.getValue().length() > ORD_TAKE_MDSE_CD_DIGIT) {
            ssmParam.put("mdseCd", param.mdseCd.getValue().substring(0, ORD_TAKE_MDSE_CD_DIGIT));
        } else {
            ssmParam.put("mdseCd", param.mdseCd.getValue());
        }
        ssmParam.put("trxSrcTpCd",           param.trxSrcTpCd.getValue());
        ssmParam.put("trxHdrNum",            param.trxHdrNum.getValue());
        ssmParam.put("trxLineNum",           param.trxLineNum.getValue());
        ssmParam.put("trxLineSubNum",        param.trxLineSubNum.getValue());
        ssmParam.put("Validated",            SHPG_STS.VALIDATED);
        ssmParam.put("cpoOrdTs",             param.cpoOrdTs.getValue());
        ssmParam.put("stkStsCdGood",         STK_STS.GOOD);
        ssmParam.put("trxLineSubNumSetMdse", TRX_LINE_SUB_NUM_SET);
        ssmParam.put("distTpNone",           DIST_TP.NONE);
        ssmParam.put("relPntSoftAllocFlg",   Y);
        ssmParam.put("hldLvlCd1",            HLD_LVL.CPO_LEVEL);
        ssmParam.put("hlbLvlCdList",         hlbLvlCdList);

        final String shpgPlnNum = (String) ssmClient.queryObject("checkSoftAllocBackOrder", ssmParam);

        if (!hasValue(shpgPlnNum)) {
            return FALSE;
        }
        return TRUE;

    }

    private boolean chkSoftAllocHold(S21ApiMessageMap msgMap) {

        NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",           param.glblCmpyCd.getValue());
        ssmParam.put("trxSrcTpCd",           param.trxSrcTpCd.getValue());
        ssmParam.put("trxHdrNum",            param.trxHdrNum.getValue());
        ssmParam.put("trxLineNum",           param.trxLineNum.getValue());
        ssmParam.put("trxLinesSubNum",       param.trxLineSubNum.getValue());
        ssmParam.put("relPntSoftAllocFlg",   Y);
        ssmParam.put("hldLvlCd1",            HLD_LVL.CPO_LEVEL);
        ssmParam.put("hlbLvlCdList",         hlbLvlCdList);
        ssmParam.put("trxLineSubNumSetMdse", TRX_LINE_SUB_NUM_SET);

        final String glblCmpyCd = (String) ssmClient.queryObject("querySoftAllocHold", ssmParam);

        if (!hasValue(glblCmpyCd)) {
            return FALSE;
        }
        return TRUE;

    }

    private VNDTMsgArray chkVendorDrop(S21ApiMessageMap msgMap) {

        final NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();

        if (!hasValue(param.invtyLocCd)) {
            msgMap.addXxMsgId(NWZM0047E);
            return null;
        }

        // cache.
        final StringBuilder sb = new StringBuilder();
        sb.append(param.glblCmpyCd.getValue()).append(",");
        sb.append(param.invtyLocCd.getValue());

        final String cacheKey = sb.toString();

        VNDTMsgArray vndTMsgArray = vendorDropTMsgCache.get(cacheKey);
        if (vndTMsgArray == null) {

            final VNDTMsg reqVndTMsg = new VNDTMsg();
            reqVndTMsg.setSQLID("002");
            reqVndTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            reqVndTMsg.setConditionValue("vndCd01",      param.invtyLocCd.getValue());

            vndTMsgArray = (VNDTMsgArray) findByCondition(reqVndTMsg);
            if (vndTMsgArray != null) {
                vendorDropTMsgCache.put(cacheKey, vndTMsgArray);
            }
        }

        return vndTMsgArray;
    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private void execAllocSegumentCreate(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
//
//        NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();
//
//        final NWZC103001PMsg softAllocPMsg = new NWZC103001PMsg();
//        setValue(softAllocPMsg.glblCmpyCd,        param.glblCmpyCd);
//        setValue(softAllocPMsg.xxRqstTpCd,        NWZC103001.REQT_TP_SEGMENT_CREATE);
//        setValue(softAllocPMsg.xxAllocSegRemkFlg, N);
//        setValue(softAllocPMsg.trxSrcTpCd,        param.trxSrcTpCd);
//        setValue(softAllocPMsg.trxHdrNum,         param.trxHdrNum);
//        setValue(softAllocPMsg.trxLineNum,        param.trxLineNum);
//        setValue(softAllocPMsg.trxLineSubNum,     param.trxLineSubNum);
//        setValue(softAllocPMsg.mdseCd,            param.mdseCd);
//        setValue(softAllocPMsg.invtyLocCd,        param.invtyLocCd);
//        setValue(softAllocPMsg.tocCd,             param.slsRepTocCd);
//        setValue(softAllocPMsg.billToCustCd,      param.billToCustCd);
//        setValue(softAllocPMsg.sellToCustCd,      param.sellToCustCd);
//        setValue(softAllocPMsg.shipToCustCd,      param.shipToCustCd);
//        setValue(softAllocPMsg.slsDt,             param.slsDt);
//
//        // --------------------------------------------------
//        // call NWZC103001 : Soft Allocation API
//        // --------------------------------------------------
//        this.softAllocAPI.execute(softAllocPMsg, new ArrayList<NWZC103002PMsg>(), onBatchType);
//
//        for (int i = 0; i < softAllocPMsg.xxMsgIdList.getValidCount(); i++) {
//            msgMap.addXxMsgId(softAllocPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
//        }
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    private void execHardAllocCancel(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
        final String methodNm = "execHardAllocCancel";
        writePerformanceProfilingLogStart(getClass(), methodNm);
        
        try {
            
            NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();
    
            if (ALLOC_TP_CD_SOFT_ALLOC.equals(param.xxAllocTpCd.getValue())) {
                return;
            }
    
            NWZC104001PMsg awzc104001pmsg = new NWZC104001PMsg();
            List<NWZC104002PMsg> awzc104002pmsgList = new ArrayList<NWZC104002PMsg>();
    
            awzc104001pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            awzc104001pmsg.xxRqstTpCd.setValue(param.xxRqstTpCd.getValue());
            awzc104001pmsg.trxSrcTpCd.setValue(param.trxSrcTpCd.getValue());
            awzc104001pmsg.trxHdrNum.setValue(param.trxHdrNum.getValue());
            awzc104001pmsg.trxLineNum.setValue(param.trxLineNum.getValue());
            awzc104001pmsg.trxLineSubNum.setValue(param.trxLineSubNum.getValue());
            awzc104001pmsg.slsDt.setValue(param.slsDt.getValue());
    
            if (REQ_TP_SHIPPING_CANCEL.equals(param.xxRqstTpCd.getValue())) {
    
                setValue(awzc104001pmsg.mdseCd, param.mdseCd);
                setValue(awzc104001pmsg.invtyLocCd, param.invtyLocCd);
                setValue(awzc104001pmsg.shpgSvcLvlCd, param.shpgSvcLvlCd);
                setValue(awzc104001pmsg.softAllocPk, param.softAllocPk);
                setValue(awzc104001pmsg.mdseCd_SA, param.mdseCd_SA);
                setValue(awzc104001pmsg.invtyLocCd_SA, param.invtyLocCd_SA);
                setValue(awzc104001pmsg.shpgSvcLvlCd_SA, param.shpgSvcLvlCd_SA);
    
            }
    
            this.hardAllocAPI.execute(awzc104001pmsg, awzc104002pmsgList, onBatchType);
    
            for (int i = 0; i < awzc104001pmsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(awzc104001pmsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private BigDecimal execHardAllocNew(S21ApiMessageMap msgMap, final NWZC104001PMsg hardAllocPMsg, final List<NWZC104002PMsg> awzc104002pmsgList, final ONBATCH_TYPE onBatchType) {

        // --------------------------------------------------
        // call NWZC104001 : Hard Allocation API
        // --------------------------------------------------
        this.hardAllocAPI.execute(hardAllocPMsg, awzc104002pmsgList, onBatchType);

        for (int i = 0; i < hardAllocPMsg.xxMsgIdList.getValidCount(); i++) {
            msgMap.addXxMsgId(hardAllocPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
        }

        BigDecimal hardAllocQty = ZERO;
        for (int i = 0; i < hardAllocPMsg.AllocationInfo.getValidCount(); i++) {
            hardAllocQty = hardAllocQty.add(hardAllocPMsg.AllocationInfo.no(i).hardAllocQty.getValue());
        }
        return hardAllocQty;
    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private void execSoftAllocCancel(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
//        final String methodNm = "execSoftAllocCancel";
//        writePerformanceProfilingLogStart(getClass(), methodNm);
//        
//        try {
//            
//            NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();
//    
//            if (ALLOC_TP_CD_HARD_ALLOC.equals(param.xxAllocTpCd.getValue())) {
//                return;
//            }
//    
//            NWZC103001PMsg awzc103001pmsg = new NWZC103001PMsg();
//            List<NWZC103002PMsg> awzc103002pmsgList = new ArrayList<NWZC103002PMsg>();
//    
//            awzc103001pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
//    
//            if (ORG_FUNC_CD_DISTRIBUTION.equals(param.xxOrigFuncTpCd.getValue())) {
//    
//                awzc103001pmsg.xxRqstTpCd.setValue(NWZC103001.REQT_TP_CANCEL_DIST);
//    
//            } else {
//    
//                awzc103001pmsg.xxRqstTpCd.setValue(param.xxRqstTpCd.getValue());
//            }
//    
//            awzc103001pmsg.trxSrcTpCd.setValue(param.trxSrcTpCd.getValue());
//            awzc103001pmsg.trxHdrNum.setValue(param.trxHdrNum.getValue());
//            awzc103001pmsg.trxLineNum.setValue(param.trxLineNum.getValue());
//            awzc103001pmsg.trxLineSubNum.setValue(param.trxLineSubNum.getValue());
//            awzc103001pmsg.slsDt.setValue(param.slsDt.getValue());
//    
//            if (REQ_TP_SHIPPING_CANCEL.equals(param.xxRqstTpCd.getValue())) {
//    
//                setValue(awzc103001pmsg.mdseCd,          param.mdseCd);
//                setValue(awzc103001pmsg.invtyLocCd,      param.invtyLocCd);
//                setValue(awzc103001pmsg.shpgSvcLvlCd,    param.shpgSvcLvlCd);
//                setValue(awzc103001pmsg.distStruSegPk,   param.distStruSegPk);
//                setValue(awzc103001pmsg.distPk,          param.distPk);
//                setValue(awzc103001pmsg.distTmFrameNum,  param.distTmFrameNum);
//                setValue(awzc103001pmsg.distPlnNum,      param.distPlnNum);
//                setValue(awzc103001pmsg.softAllocPk,     param.softAllocPk);
//                setValue(awzc103001pmsg.mdseCd_SA,       param.mdseCd_SA);
//                setValue(awzc103001pmsg.invtyLocCd_SA,   param.invtyLocCd_SA);
//                setValue(awzc103001pmsg.shpgSvcLvlCd_SA, param.shpgSvcLvlCd_SA);
//    
//            }
//    
//            this.softAllocAPI.execute(awzc103001pmsg, awzc103002pmsgList, onBatchType);
//    
//            for (int i = 0; i < awzc103001pmsg.xxMsgIdList.getValidCount(); i++) {
//                msgMap.addXxMsgId(awzc103001pmsg.xxMsgIdList.no(i).xxMsgId.getValue());
//            }
//
//        } finally {
//            writePerformanceProfilingLogEnd(getClass(), methodNm);
//        }
//    }
//
//    private boolean execSoftAllocNew(S21ApiMessageMap msgMap, final List<NWZC103002PMsg> awzc103002pmsgList, final MdseData mdseInfo, final ONBATCH_TYPE onBatchType) {
//
//        NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();
//
//        NWZC103001PMsg awzc103001pmsg = new NWZC103001PMsg();
//
//        awzc103001pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
//        awzc103001pmsg.xxRqstTpCd.setValue(NWZC103001.REQT_TP_NEW);
//        awzc103001pmsg.mdseTpCd.setValue(mdseInfo.getMdseTpCd());
//        awzc103001pmsg.xxRqstQty.setValue(param.xxRqstQty.getValue());
//
//        if (ORG_FUNC_CD_SOFT_ALLOC.equals(param.xxOrigFuncTpCd.getValue())) {
//            awzc103001pmsg.softAllocAutoFlg.setValue(N);
//        } else {
//            awzc103001pmsg.softAllocAutoFlg.setValue(Y);
//        }
//        awzc103001pmsg.trxSrcTpCd.setValue(param.trxSrcTpCd.getValue());
//        awzc103001pmsg.trxHdrNum.setValue(param.trxHdrNum.getValue());
//        awzc103001pmsg.trxLineNum.setValue(param.trxLineNum.getValue());
//        awzc103001pmsg.trxLineSubNum.setValue(param.trxLineSubNum.getValue());
//
//        awzc103001pmsg.mdseCd.setValue(param.mdseCd.getValue());
//        awzc103001pmsg.invtyLocCd.setValue(param.invtyLocCd.getValue());
//        awzc103001pmsg.expdShipDt.setValue(param.expdShipDt.getValue());
//        awzc103001pmsg.tocCd.setValue(param.slsRepTocCd.getValue());
//        awzc103001pmsg.shpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());
//        awzc103001pmsg.billToCustCd.setValue(param.billToCustCd.getValue());
//        awzc103001pmsg.sellToCustCd.setValue(param.sellToCustCd.getValue());
//        awzc103001pmsg.shipToCustCd.setValue(param.shipToCustCd.getValue());
//        awzc103001pmsg.uomCpltFlg.setValue(param.uomCpltFlg.getValue());
//        awzc103001pmsg.uomCd.setValue(param.uomCd.getValue());
//
//        setValue(awzc103001pmsg.distStruSegPk, param.distStruSegPk);
//        setValue(awzc103001pmsg.distPk, param.distPk);
//        setValue(awzc103001pmsg.distTmFrameNum, param.distTmFrameNum);
//        setValue(awzc103001pmsg.distPlnNum, param.distPlnNum);
//        setValue(awzc103001pmsg.softAllocPk, param.softAllocPk);
//        setValue(awzc103001pmsg.mdseCd_SA, param.mdseCd_SA);
//        setValue(awzc103001pmsg.invtyLocCd_SA, param.invtyLocCd_SA);
//        setValue(awzc103001pmsg.shpgSvcLvlCd_SA, param.shpgSvcLvlCd_SA);
//
//        awzc103001pmsg.slsDt.setValue(param.slsDt.getValue());
//
//        this.softAllocAPI.execute(awzc103001pmsg, awzc103002pmsgList, onBatchType);
//
//        for (int i = 0; i < awzc103001pmsg.xxMsgIdList.getValidCount(); i++) {
//            msgMap.addXxMsgId(awzc103001pmsg.xxMsgIdList.no(i).xxMsgId.getValue());
//        }
//
//        if (hasMessage(msgMap)) {
//            return FALSE;
//        }
//
//        BigDecimal totalSoftAllocQty = ZERO;
//
//        for (int i = 0; i < awzc103001pmsg.xxAllocInfo.getValidCount(); i++) {
//            totalSoftAllocQty = totalSoftAllocQty.add(awzc103001pmsg.xxAllocInfo.no(i).softAllocQty.getValue());
//        }
//
//        if (ALLOC_TP_CD_SOFT_HARD_ALLOC.equals(param.xxAllocTpCd.getValue())) {
//
//            if (totalSoftAllocQty.compareTo(ZERO) == 0) {
//                return FALSE;
//            }
//        }
//
//        if (ORG_FUNC_CD_SOFT_ALLOC.equals(param.xxOrigFuncTpCd.getValue())) {
//
//            if (!totalSoftAllocQty.equals(param.xxRqstQty.getValue())) {
//                msgMap.addXxMsgId(NWZM0200E);
//                return FALSE;
//            }
//        }
//
//        return TRUE;
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    private boolean existsBO(NWZC102001PMsg param, MdseData mdseInfo, final boolean distItemFlg) {
        final String methodNm = "existsBO";
        writePerformanceProfilingLogStart(getClass(), methodNm);
        
        try {
            
            final NWXC002007BackOrderParam backOrderParam = new NWXC002007BackOrderParam();
            backOrderParam.setSlsDt(param.slsDt.getValue());
            backOrderParam.setGlblCmpyCd(param.glblCmpyCd.getValue());
            backOrderParam.setTrxHdrNum(param.trxHdrNum.getValue());
            backOrderParam.setTrxLineNum(param.trxLineNum.getValue());
            backOrderParam.setTrxLineSubNum(param.trxLineSubNum.getValue());
            backOrderParam.setTrxSrcTpCd(param.trxSrcTpCd.getValue());
            backOrderParam.setTs(param.cpoOrdTs.getValue());
            // Defect 5902
            backOrderParam.setInvtyLocCd(param.invtyLocCd.getValue());
    
            final String odrTakeMdseCd = disitMdseCd(mdseInfo.getMdseCd());
            
            final ORD_TAKE_MDSETMsg ordTakeMdseTMsg = getOdrTakeMdse(mdseInfo.getGlblCmpyCd(), odrTakeMdseCd);
            if (ordTakeMdseTMsg == null) {
                // 10 Digit Item
                backOrderParam.setMdseCd(mdseInfo.getMdseCd());
            } else {
                // 8 Digit Item
                backOrderParam.setMdseCd(odrTakeMdseCd);
            }
    
            if (!distItemFlg) {
                backOrderParam.setDaysPriAllocNum(mdseInfo.getDaysPriAllocNum());
                backOrderParam.setCheckTp(NWXC002007BackOrder.BACKORDER_CHECK_TYPE_NONE);
            } else {
                backOrderParam.setCheckTp(NWXC002007BackOrder.BACKORDER_CHECK_TYPE_DIST);
            }
    
            // exists?
            return NWXC002007BackOrder.isExist(backOrderParam);
            
        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private boolean existsHardAllocHlds(NWZC102001PMsg param) {
        final String methodNm = "existsHardAllocHlds";
        writePerformanceProfilingLogStart(getClass(), methodNm);
        
        try {

            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",           param.glblCmpyCd.getValue());
            ssmParam.put("trxSrcTpCd",           param.trxSrcTpCd.getValue());
            ssmParam.put("trxHdrNum",            param.trxHdrNum.getValue());
            ssmParam.put("trxLineNum",           param.trxLineNum.getValue());
            ssmParam.put("trxLinesSubNum",       param.trxLineSubNum.getValue());
            ssmParam.put("hldLvlCd1",            HLD_LVL.CPO_LEVEL);
            ssmParam.put("hlbLvlCdList",         hlbLvlCdList);
            ssmParam.put("trxLineSubNumSetMdse", TRX_LINE_SUB_NUM_SET);
    
            return ((BigDecimal)ssmClient.queryObject("cntHardAllocHlds", ssmParam)).intValue() > 0;
            
        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    @SuppressWarnings("unchecked")
//    private List<NWZC103002PMsg> getDistributionInfo(S21ApiMessageMap msgMap, final MdseData mdseInfo) {
//
//        NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();
//        List<NWZC103002PMsg> distributionList;
//
//        Map<String, Object> key = new HashMap<String, Object>();
//        key.put("glblCmpyCd", param.glblCmpyCd.getValue());
//        key.put("distPlnStsCd", DIST_PLN_STS.SUBMITTED);
//        key.put("trxSrcTpCd", param.trxSrcTpCd.getValue());
//        key.put("trxHdrNum", param.trxHdrNum.getValue());
//        key.put("trxLineNum", param.trxLineNum.getValue());
//        key.put("trxLineSubNum", param.trxLineSubNum.getValue());
//
//        if (ORG_FUNC_CD_SOFT_ALLOC.equals(param.xxOrigFuncTpCd.getValue())) {
//            key.put("mdseCd", param.mdseCd.getValue());
//            key.put("distPlnNum", param.distPlnNum.getValue());
//            key.put("distPk", param.distPk.getValue());
//            key.put("distTmFrameNum", param.distTmFrameNum.getValue());
//            key.put("distStruSegPk", param.distStruSegPk.getValue());
//            distributionList = (List<NWZC103002PMsg>) ssmClient.queryObjectList("queryDistributionInfo1", key);
//
//        } else {
//
//            String odrTakeMdseCd = disitMdseCd(mdseInfo.getMdseCd());
//            ORD_TAKE_MDSETMsg resOrdTakeMdseTMsg = getOdrTakeMdse(param.glblCmpyCd.getValue(), odrTakeMdseCd);
//
//            if (resOrdTakeMdseTMsg == null) {
//                // 10 Digit Item
//                key.put("ordTakeMdseFlg", MDSE_CD_FLG);
//                key.put("mdseCd", param.mdseCd.getValue());
//            } else {
//                // 8 Digit Item
//                key.put("ordTakeMdseFlg", ORD_TAKE_MDSE_CD_FLG);
//                key.put("mdseCd", odrTakeMdseCd);
//            }
//
//            key.put("slsDt", param.slsDt.getValue());
//
//            distributionList = (List<NWZC103002PMsg>) ssmClient.queryObjectList("queryDistributionInfo2", key);
//        }
//
//        return distributionList;
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    @SuppressWarnings("unchecked")
    private List<NWZC104002PMsg> getInvtyList(NWZC102001PMsg param, final MdseData mdseInfo) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",     param.glblCmpyCd.getValue());
        ssmParam.put("mdseCd",         param.mdseCd.getValue());
        ssmParam.put("locStsCd",       LOC_STS.DC_STOCK);
        ssmParam.put("invtyLocCd",     param.invtyLocCd.getValue());
        ssmParam.put("stkStsCd",       param.stkStsCd.getValue());
        ssmParam.put("ordTakeMdseFlg", mdseInfo.getOrdTakeMdseFlg());

        // Def#1763
        if (param.mdseCd.getValue().length() > ORD_TAKE_MDSE_CD_DIGIT) {
            ssmParam.put("thrOrdTakeMdseCd", param.mdseCd.getValue().substring(0, ORD_TAKE_MDSE_CD_DIGIT));
        } else {
            ssmParam.put("thrOrdTakeMdseCd", param.mdseCd.getValue());
        }

        return (List<NWZC104002PMsg>) ssmClient.queryObjectList("queryInvty", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<NWZC104001PMsg> getSoftAllocInfo(NWZC102001PMsg param, final boolean distSetItemFlg, final String shpgPlnNum) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",    param.glblCmpyCd.getValue());
        ssmParam.put("trxSrcTpCd",    param.trxSrcTpCd.getValue());
        ssmParam.put("trxHdrNum",     param.trxHdrNum.getValue());
        ssmParam.put("trxLineNum",    param.trxLineNum.getValue());
        ssmParam.put("trxLineSubNum", param.trxLineSubNum.getValue());
        ssmParam.put("shpgStsCd",     SHPG_STS.VALIDATED);
        ssmParam.put("slsDt",         param.slsDt.getValue());
        
        if (isEquals(ORG_FUNC_CD_ORDER_ENTRY, param.xxOrigFuncTpCd.getValue())) {
            ssmParam.put("origFuncTpCd", param.xxOrigFuncTpCd.getValue());

        } else if (isEquals(ORG_FUNC_CD_HARD_ALLOC, param.xxOrigFuncTpCd.getValue())) {
            ssmParam.put("origFuncTpCd", param.xxOrigFuncTpCd.getValue());
            ssmParam.put("shpgPlnNum",   shpgPlnNum);
            
        } else {
            ssmParam.put("origFuncTpCd", ORG_FUNC_CD_ORDER_ENTRY);
        }

        if (distSetItemFlg) {
            ssmParam.put("settrxLineSubNum", TRX_LINE_SUB_NUM_SET);
        } else {
            ssmParam.put("settrxLineSubNum", param.trxLineSubNum.getValue());
        }

        return (List<NWZC104001PMsg>) ssmClient.queryObjectList("getSoftAllocInfo", ssmParam);
    }

    private void hardAllocNewProcess(S21ApiMessageMap msgMap, final MdseData mdseInfo, final ONBATCH_TYPE onBatchType, SHPG_PLNTMsg shpgPlnMsg) {
        final String methodNm = "hardAllocNewProcess";
        writePerformanceProfilingLogStart(getClass(), methodNm);
        
        try {
            
            final NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();
    
            // execute Soft Allocation API only
            if (ALLOC_TP_CD_SOFT_ALLOC.equals(param.xxAllocTpCd.getValue())) {
                return;
            }
    
            // check SetItem
            if (chkSetItem(mdseInfo)) {
                return;
            }

            
            final List<NWZC104001PMsg> hardAllocPMsgList = new ArrayList<NWZC104001PMsg>();

            // --------------------------------
            // **CALL** Hard Allocation Screen
            // --------------------------------
            if (ORG_FUNC_CD_HARD_ALLOC.equals(param.xxOrigFuncTpCd.getValue())) {
    
//                if (chkAutoItem(mdseInfo) && !hasValue(param.mdseCd_SA)) {
//                    // check AllocStartedDay
//                    String errAllocStartedDay = NWXC002007BackOrder.chkAllocStartedDay(mdseInfo.getDaysPriAllocNum(), param.glblCmpyCd.getValue(), param.invtyLocCd.getValue(), param.slsDt.getValue(), param.expdShipDt.getValue(), onBatchType);
//    
//                    if (hasValue(errAllocStartedDay)) {
//                        if (NWZM0950E.equals(errAllocStartedDay)) {
//                            if (ORG_FUNC_CD_HARD_ALLOC.equals(param.xxOrigFuncTpCd.getValue())) {
//                                msgMap.addXxMsgId(NWZM0950E);
//                            }
//    
//                        } else {
//                            msgMap.addXxMsgId(errAllocStartedDay);
//                        }
//                        return;
//                    }
//    
//                }
    
                // get Invty information
                List<NWZC104002PMsg> invtyList = this.getInvtyList(param, mdseInfo);
    
                if (invtyList == null || invtyList.isEmpty()) {
                    return;
                }
    
                BigDecimal totalHardAllocQty = ZERO;
    
                // 07/01/2010 Defect 7500 --- add start
                if (hasValue(param.mdseCd_SA)) {
                    // --------------------------------
                    // Distribution Item...
                    // --------------------------------
    
                    // check DistributionSetItem
                    boolean chkDistSetItemFlg = chkSetComponentDistItem(msgMap, shpgPlnMsg);
                    if (hasMessage(msgMap)) {
                        return;
                    }
    
                    // get Distribution Information
                    List<NWZC104001PMsg> softAllocList = getSoftAllocInfo(param, chkDistSetItemFlg, shpgPlnMsg.shpgPlnNum.getValue());
                    if (softAllocList == null || softAllocList.isEmpty()) {
                        msgMap.addXxMsgId(NWZM0200E);
                        return;
                    }
    
                    BigDecimal totQty = param.xxRqstQty.getValue();
    
                    for (NWZC104001PMsg awzc104001pmsg : softAllocList) {
    
                        BigDecimal softAllocQty = awzc104001pmsg.xxRqstQty.getValue();
    
                        if (totQty.compareTo(ZERO) == 0) {
                            break;
    
                        } else if (totQty.compareTo(ZERO) > 0 && softAllocQty.compareTo(ZERO) > 0) {
    
                            if (totQty.compareTo(softAllocQty) < 0) {
                                // Divide
                                if (hasValue(awzc104001pmsg.softAllocPk)) {
    
                                    SOFT_ALLOCTMsg softAllocMsg = new SOFT_ALLOCTMsg();
                                    softAllocMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                                    softAllocMsg.softAllocPk.setValue(awzc104001pmsg.softAllocPk.getValue());
    
                                    SOFT_ALLOCTMsg softAllocOrigMsg = (SOFT_ALLOCTMsg) findByKeyForUpdateAPI(softAllocMsg);
                                    SOFT_ALLOCTMsg softAllocNewMsg = new SOFT_ALLOCTMsg();
    
                                    EZDMsg.copy(softAllocOrigMsg, null, softAllocNewMsg, null);
    
                                    // ****Update
                                    softAllocOrigMsg.softAllocQty.setValue(totQty);
                                    softAllocOrigMsg.hardAllocAvalQty.setValue(ZERO);
    
                                    updateSoftAlloc(msgMap, softAllocOrigMsg);
    
                                    // ****Insert
                                    softAllocNewMsg.softAllocPk.setValue(getSoftAllocSQ());
                                    softAllocNewMsg.softAllocQty.setValue(softAllocNewMsg.softAllocQty.getValue().subtract(totQty));
                                    softAllocNewMsg.hardAllocAvalQty.setValue(softAllocNewMsg.hardAllocAvalQty.getValue().subtract(totQty));
    
                                    insertSoftAlloc(msgMap, softAllocNewMsg);
    
                                    if (hasMessage(msgMap)) {
                                        return;
                                    }
    
                                    // --------------------------------
                                    // **Execute** Hard Allocation API
                                    // --------------------------------
                                    setValue(awzc104001pmsg.mdseCd_SA, param.mdseCd);
                                    setValue(awzc104001pmsg.invtyLocCd_SA, param.invtyLocCd);
                                    setValue(awzc104001pmsg.shpgSvcLvlCd_SA, param.shpgSvcLvlCd);
                                    awzc104001pmsg.distTpCd.setValue(DIST_TP.DISTRIBUTION);
                                    awzc104001pmsg.hardAllocTpCd.setValue(HARD_ALLOC_TP.MANUAL);
                                    awzc104001pmsg.xxOrdTakeMdseFlg_SA.setValue(mdseInfo.getOrdTakeMdseFlg());
                                    awzc104001pmsg.xxOrdTakeMdseFlg.setValue(mdseInfo.getOrdTakeMdseFlg());
                                    awzc104001pmsg.xxRqstQty.setValue(totQty);
                                    this.setHardAllocReqPMsg(param, awzc104001pmsg);
    
                                    BigDecimal hardAllocQty = this.execHardAllocNew(msgMap, awzc104001pmsg, invtyList, onBatchType);
                                    if (hasMessage(msgMap)) {
                                        return;
                                    }
    
                                    totalHardAllocQty = totalHardAllocQty.add(hardAllocQty);
                                    totQty = ZERO;
                                }
                                // 07/01/2010 Defect 7500 --- add end
    
                            } else {
                                // tottalQty > SoftAllocQty
    
                                // --------------------------------
                                // **Execute** Hard Allocation API
                                // --------------------------------
                                setValue(awzc104001pmsg.mdseCd_SA, param.mdseCd);
                                setValue(awzc104001pmsg.invtyLocCd_SA, param.invtyLocCd);
                                setValue(awzc104001pmsg.shpgSvcLvlCd_SA, param.shpgSvcLvlCd);
                                awzc104001pmsg.distTpCd.setValue(DIST_TP.DISTRIBUTION);
                                awzc104001pmsg.hardAllocTpCd.setValue(HARD_ALLOC_TP.MANUAL);
                                awzc104001pmsg.xxOrdTakeMdseFlg_SA.setValue(mdseInfo.getOrdTakeMdseFlg());
                                awzc104001pmsg.xxOrdTakeMdseFlg.setValue(mdseInfo.getOrdTakeMdseFlg());
                                this.setHardAllocReqPMsg(param, awzc104001pmsg);
    
                                BigDecimal hardAllocQty = this.execHardAllocNew(msgMap, awzc104001pmsg, invtyList, onBatchType);
                                if (hasMessage(msgMap)) {
                                    return;
                                }
    
                                totalHardAllocQty = totalHardAllocQty.add(hardAllocQty);
                                totQty = totQty.subtract(hardAllocQty);
                            }
    
                        } else {
                            continue;
                        }
                    }
    
                } else {
                    // --------------------------------
                    // None Item...
                    // --------------------------------
    
                    // --------------------------------
                    // **Execute** Hard Allocation API
                    // --------------------------------
                    final NWZC104001PMsg awzc104001pmsg = new NWZC104001PMsg();
                    awzc104001pmsg.distTpCd.setValue(DIST_TP.NONE);
                    awzc104001pmsg.hardAllocTpCd.setValue(HARD_ALLOC_TP.MANUAL);
                    awzc104001pmsg.xxOrdTakeMdseFlg.setValue(mdseInfo.getOrdTakeMdseFlg());
                    awzc104001pmsg.xxRqstQty.setValue(param.xxRqstQty.getValue());
                    this.setHardAllocReqPMsg(param, awzc104001pmsg);
    
                    BigDecimal hardAllocQty = this.execHardAllocNew(msgMap, awzc104001pmsg, invtyList, onBatchType);
                    if (hasMessage(msgMap)) {
                        return;
                    }
    
                    totalHardAllocQty = totalHardAllocQty.add(hardAllocQty);
    
                }
    
                if (param.xxRqstQty.getValue().compareTo(totalHardAllocQty) > 0) {
                    // param.xxRqstQty.getValue() > totalHardAllocQty
                    msgMap.addXxMsgId(NWZM0200E);
                }
    
            } else {
                // --------------------------------
                // **CALL**
                // Order Entry
                // Soft Allocation Screen
                // --------------------------------
    
                // check StkSts
                if (chkItemforStkSts(mdseInfo.getInvtyDistTpCd(), param.stkStsCd.getValue())) {
                    return;
                }
    
                // check HardAllocationTypeCode
                if (ORG_FUNC_CD_ORDER_ENTRY.equals(param.xxOrigFuncTpCd.getValue()) && HARD_ALLOC_TP.MANUAL.equals(mdseInfo.getInvtyHardAllocTpCd())) {
                    return;
                }
    
                // 07/01/2010 Defect 7500 add
                if (ORG_FUNC_CD_SOFT_ALLOC.equals(param.xxOrigFuncTpCd.getValue()) && HARD_ALLOC_TP.MANUAL.equals(mdseInfo.getInvtyHardAllocTpCd())) {
                    return;
                }
                
                // check DistributionSetItem
                final boolean chkDistSetItemFlg = chkSetComponentDistItem(msgMap, shpgPlnMsg);
                if (hasMessage(msgMap)) {
                    return;
                }
    
                // check DistributionItem
                final boolean chkDistItemFlg = chkDistributionItem(mdseInfo.getInvtyDistTpCd());
                if (hasMessage(msgMap)) {
                    return;
                }
    
                final boolean distItemFlg = chkDistSetItemFlg || chkDistItemFlg;
    
                // Other
                // exists BO(Back-Order)?
                if (this.existsBO(param, mdseInfo, distItemFlg)) {
                    return;
                }
    
                if (this.existsHardAllocHlds(param)) {
                    return;
                }
    
                final String errAllocStartedDay = NWXC002007BackOrder.chkAllocStartedDay(
                                                        mdseInfo.getDaysPriAllocNum(), 
                                                        param.glblCmpyCd.getValue(), 
                                                        param.invtyLocCd.getValue(), 
                                                        param.slsDt.getValue(), 
                                                        // Mod Start S21_NA#5872
                                                        // param.expdShipDt.getValue(), 
                                                        param.rddDt.getValue(),
                                                        // Mod End S21_NA#5872
                                                        onBatchType
                                                  );
                // check AllocStartedDay
                if (hasValue(errAllocStartedDay)) {
                    if (!NWZM0950E.equals(errAllocStartedDay)) {
                        msgMap.addXxMsgId(errAllocStartedDay);
                    }
                    return;
                }
    
                // get Invty information
                final List<NWZC104002PMsg> invtyList = this.getInvtyList(param, mdseInfo);
                if (isEmpty(invtyList)) {
                    return;
                }
    
                if (distItemFlg) {
    
                    // get Distribution Information
                    final List<NWZC104001PMsg> softAllocList = this.getSoftAllocInfo(param, chkDistSetItemFlg, null);
                    if (isEmpty(softAllocList)) {
                        return;
                    }
    
                    for (NWZC104001PMsg hardAllocPMsg : softAllocList) {
    
                        // **CALL** Saft Allocation Screen
                        if (ORG_FUNC_CD_SOFT_ALLOC.equals(param.xxOrigFuncTpCd.getValue())) {
                            
                            setValue(hardAllocPMsg.mdseCd_SA,           param.mdseCd);
                            setValue(hardAllocPMsg.invtyLocCd_SA,       param.invtyLocCd);
                            setValue(hardAllocPMsg.shpgSvcLvlCd_SA,     param.shpgSvcLvlCd);
                            setValue(hardAllocPMsg.distTpCd,            DIST_TP.DISTRIBUTION);
                            setValue(hardAllocPMsg.hardAllocTpCd,       HARD_ALLOC_TP.AUTO);
                            setValue(hardAllocPMsg.xxOrdTakeMdseFlg_SA, mdseInfo.getOrdTakeMdseFlg());
                            setValue(hardAllocPMsg.xxOrdTakeMdseFlg,    mdseInfo.getOrdTakeMdseFlg());
    
                        } else {
    
                            setValue(hardAllocPMsg.distTpCd,            DIST_TP.DISTRIBUTION);
                            setValue(hardAllocPMsg.hardAllocTpCd,       HARD_ALLOC_TP.AUTO);
                            setValue(hardAllocPMsg.xxOrdTakeMdseFlg,    mdseInfo.getOrdTakeMdseFlg());
    
                            final ORD_TAKE_MDSETMsg ordTakeMdseTMsg = getOdrTakeMdse(param.glblCmpyCd.getValue(), hardAllocPMsg.mdseCd_SA.getValue());
                            if (ordTakeMdseTMsg == null) {
                                setValue(hardAllocPMsg.xxOrdTakeMdseFlg_SA, MDSE_CD_FLG);
                            } else {
                                setValue(hardAllocPMsg.xxOrdTakeMdseFlg_SA, ORD_TAKE_MDSE_CD_FLG);
                            }
    
                        }
                        
                        this.setHardAllocReqPMsg(param, hardAllocPMsg);
                        hardAllocPMsgList.add(hardAllocPMsg);
                    }
    
                } else {
    
                    final NWZC104001PMsg hardAllocPMsg = new NWZC104001PMsg();
                    setValue(hardAllocPMsg.distTpCd,         DIST_TP.NONE);
                    setValue(hardAllocPMsg.hardAllocTpCd,    HARD_ALLOC_TP.AUTO);
                    setValue(hardAllocPMsg.xxRqstQty,        param.xxRqstQty.getValue());
                    setValue(hardAllocPMsg.xxOrdTakeMdseFlg, mdseInfo.getOrdTakeMdseFlg());
    
                    this.setHardAllocReqPMsg(param, hardAllocPMsg);
                    hardAllocPMsgList.add(hardAllocPMsg);
                }
    
                lockShpgPlnArray(param, SHPG_STS.HARD_ALLOCATED);
    
                // --------------------------------------------------
                // call NWZC104001 : Hard Allocation API
                // --------------------------------------------------
                for (NWZC104001PMsg hardAllocPMsg : hardAllocPMsgList) {
                    this.execHardAllocNew(msgMap, hardAllocPMsg, invtyList, onBatchType);
                    if (hasMessage(msgMap)) {
                        return;
                    }
                }
            }
            
        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private MdseData initProcess(S21ApiMessageMap msgMap) {
        
        final NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();

        final MdseData mdseInfo = getMdseInfo(msgMap, param.mdseCd.getValue());
        if (hasMessage(msgMap)) {
            return null;
        }

        // Add Start S21_NA#5872
        // Set Hard Allocation Type
        setInvtyHardAllocTpCd(param, mdseInfo);
        // Add End S21_NA#5872        
        
        if (!MDSE_TP.SET.equals(mdseInfo.getMdseTpCd())) {

            if (N.equals(mdseInfo.getInvtyCtrlFlg())) {
                return null;
            }

            final VNDTMsgArray vndMsgArray = this.chkVendorDrop(msgMap);
            if (hasMessage(msgMap) || vndMsgArray.getValidCount() > 0) {
                return null;
            }

            if (!hasValue(param.expdShipDt)) {
                msgMap.addXxMsgId(NWZM0197E);
                return null;
            }

        }

        return mdseInfo;
    }

    private void setHardAllocReqPMsg(NWZC102001PMsg param, final NWZC104001PMsg hardAllocPMsg) {

        setValue(hardAllocPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        setValue(hardAllocPMsg.xxRqstTpCd, REQ_TP_NEW);
        
        if (ORG_FUNC_CD_SOFT_ALLOC.equals(param.xxOrigFuncTpCd.getValue())) {
            setValue(hardAllocPMsg.xxPrtlAcptFlg, PRTL_ACPT_FLG_ON);
        } else {
            setValue(hardAllocPMsg.xxPrtlAcptFlg, param.xxPrtlAcptFlg.getValue());
        }
        
        setValue(hardAllocPMsg.xxItemFlipAcptFlg,  param.xxItemFlipAcptFlg.getValue());
        setValue(hardAllocPMsg.xxWhFlipAcptFlg,    param.xxWhFlipAcptFlg.getValue());
        setValue(hardAllocPMsg.trxSrcTpCd,         param.trxSrcTpCd.getValue());
        setValue(hardAllocPMsg.trxHdrNum,          param.trxHdrNum.getValue());
        setValue(hardAllocPMsg.trxLineNum,         param.trxLineNum.getValue());
        setValue(hardAllocPMsg.trxLineSubNum,      param.trxLineSubNum.getValue());
        setValue(hardAllocPMsg.mdseCd,             param.mdseCd.getValue());
        setValue(hardAllocPMsg.invtyLocCd,         param.invtyLocCd.getValue());
        setValue(hardAllocPMsg.locStsCd,           LOC_STS.DC_STOCK);
        setValue(hardAllocPMsg.stkStsCd,           param.stkStsCd.getValue());
        setValue(hardAllocPMsg.reqFrtChrgMethCd,   param.frtChrgMethCd.getValue());
        setValue(hardAllocPMsg.reqFrtChrgToCd,     param.frtChrgToCd.getValue());
        setValue(hardAllocPMsg.shpgSvcLvlCd,       param.shpgSvcLvlCd.getValue());
        setValue(hardAllocPMsg.rddDt,              param.rddDt.getValue());
        setValue(hardAllocPMsg.rsdDt,              param.rsdDt.getValue());
        setValue(hardAllocPMsg.billToCustCd,       param.billToCustCd.getValue());
        setValue(hardAllocPMsg.sellToCustCd,       param.sellToCustCd.getValue());
        setValue(hardAllocPMsg.shipToCustCd,       param.shipToCustCd.getValue());
        setValue(hardAllocPMsg.shipToStCd,         param.shipToStCd.getValue());
        setValue(hardAllocPMsg.shipToPostCd,       param.shipToPostCd.getValue());
        setValue(hardAllocPMsg.shipCpltCd,         param.shipCpltCd.getValue());
        setValue(hardAllocPMsg.setItemShipCpltFlg, param.setItemShipCpltFlg.getValue());
        setValue(hardAllocPMsg.uomCpltFlg,         param.uomCpltFlg.getValue());
        setValue(hardAllocPMsg.custUomCd,          param.uomCd.getValue());
        setValue(hardAllocPMsg.slsDt,              param.slsDt.getValue());
    }

    private boolean softAllocNewProcess(S21ApiMessageMap msgMap, final MdseData mdseInfo, final ONBATCH_TYPE onBatchType, SHPG_PLNTMsg shpgPlnMsg) {
        final String methodNm = "softAllocNewProcess";
        writePerformanceProfilingLogStart(getClass(), methodNm);
        
        try {
            
            final NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();
    
            if (ALLOC_TP_CD_HARD_ALLOC.equals(param.xxAllocTpCd.getValue())) {
                return TRUE;
            }
    
            // Soft Allocation Screen
            if (ORG_FUNC_CD_SOFT_ALLOC.equals(param.xxOrigFuncTpCd.getValue())) {
    
                if (chkSetComponentDistItem(msgMap, shpgPlnMsg)) {
                    // none
                    return FALSE;
    
                } else {
    
                    // check StkSts
                    if (chkItemforStkSts(mdseInfo.getInvtyDistTpCd(), param.stkStsCd.getValue())) {
                        return FALSE;
                    }
    
                    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//                    // Create AllocSegumen
//                    this.execAllocSegumentCreate(msgMap, onBatchType);
//                    if (hasMessage(msgMap)) {
//                        return FALSE;
//                    }
//    
//                    // get Distribution information
//                    List<NWZC103002PMsg> distributionList = this.getDistributionInfo(msgMap, mdseInfo);
                    // 2017/04/12 S21_NA#Review structure Lv.1 Del End
    
                    if (hasMessage(msgMap)) {
                        return FALSE;
                    }

                    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//                    if (distributionList == null || distributionList.isEmpty()) {
//                        return FALSE;
//    
//                    }
                    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

                    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//                    // calc avalAllocQty
//                    BigDecimal totalDistAvalQty = this.calcTotalDistAvalQty(distributionList);
//    
//                    if (param.xxRqstQty.getValue().compareTo(totalDistAvalQty) > 0) {
//                        // xxRqstQty > totalDistAvalQty
//                        msgMap.addXxMsgId(NWZM0200E);
//                        return FALSE;
//                    }
//    
//                    // execute Soft Allocation API
//                    if (!this.execSoftAllocNew(msgMap, distributionList, mdseInfo, onBatchType)) {
//                        return FALSE;
//                    }
                    // 2017/04/12 S21_NA#Review structure Lv.1 Del End
                }
    
            } else {
    
                // check Component
                if (chkSetComponentDistItem(msgMap, shpgPlnMsg)) {
                    return TRUE;
                }
    
                // check noneItem
                if (chkNoneItem(mdseInfo.getInvtyDistTpCd())) {
                    return TRUE;
                }
    
                // check StkSts
                if (chkItemforStkSts(mdseInfo.getInvtyDistTpCd(), param.stkStsCd.getValue())) {
                    return FALSE;
                }

                // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//                // Create AllocSegument
//                this.execAllocSegumentCreate(msgMap, onBatchType);
//                if (hasMessage(msgMap)) {
//                    return FALSE;
//                }
                // 2017/04/12 S21_NA#Review structure Lv.1 Del End
    
                if (this.chkSoftAllocHold(msgMap)) {
                    return FALSE;
                }
    
                // check backOrder
                if (this.chkSoftAllocBackOrder(msgMap)) {
                    return FALSE;
                }
    
                // get DistStruSegMdseAsg
                if (ORG_FUNC_CD_ORDER_ENTRY.equals(param.xxOrigFuncTpCd.getValue())) {
                    if (!this.chkDistStruSegMdseAsg(msgMap)) {
                        return FALSE;
                    }
                }

                // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//                // get Distribution information
//                final List<NWZC103002PMsg> distributionList = this.getDistributionInfo(msgMap, mdseInfo);
                // 2017/04/12 S21_NA#Review structure Lv.1 Del End
    
                if (hasMessage(msgMap)) {
                    return FALSE;
                }

                // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//                if (distributionList == null || distributionList.isEmpty()) {
//                    return FALSE;
//    
//                }
//    
//                // execute Soft Allocation API
//                if (!this.execSoftAllocNew(msgMap, distributionList, mdseInfo, onBatchType)) {
//                    return FALSE;
//                }
                // 2017/04/12 S21_NA#Review structure Lv.1 Del End
            }
    
            return TRUE;

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

//    private static boolean chkAutoItem(final MdseData mdseInfo) {
//
//        if (chkNoneItem(mdseInfo.getInvtyDistTpCd()) && HARD_ALLOC_TP.AUTO.equals(mdseInfo.getInvtyHardAllocTpCd())) {
//            return TRUE;
//        }
//
//        return FALSE;
//    }

    private static void chkCommonInputParam(S21ApiMessageMap msgMap) {
            
        NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();

        if (!hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
            return;
        }

        if (!hasValue(param.xxRqstTpCd)) {
            msgMap.addXxMsgId(NWZM0189E);
            return;
        } else {
            final int rqstTpCd = Integer.parseInt(param.xxRqstTpCd.getValue());
            if (rqstTpCd < RQST_TP_CD_MIN || rqstTpCd > RQST_TP_CD_MAX) {
                msgMap.addXxMsgId(NWZM0209E);
            }
        }

        if (!hasValue(param.trxSrcTpCd)) {
            msgMap.addXxMsgId(NWZM0014E);
            return;
        }

        if (!hasValue(param.trxHdrNum)) {
            msgMap.addXxMsgId(NWZM0167E);
            return;
        }

        if (!hasValue(param.trxLineNum)) {
            msgMap.addXxMsgId(NWZM0168E);
            return;
        }

        if (!hasValue(param.trxLineSubNum)) {
            msgMap.addXxMsgId(NWZM0169E);
            return;
        }

        if (!hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZM0346E);
            return;
        }

        if (!hasValue(param.xxOrigFuncTpCd)) {
            msgMap.addXxMsgId(NWZM0226E);
            return;
        } else {
            final int origFuncTpCd = Integer.parseInt(param.xxOrigFuncTpCd.getValue());
            if (origFuncTpCd > ORIG_FUNC_TP_CD_MAX || origFuncTpCd < ORIG_FUNC_TP_CD_MIN) {
                msgMap.addXxMsgId(NWZM0213E);
            }
        }

        if (!hasValue(param.xxAllocTpCd)) {
            msgMap.addXxMsgId(NWZM0190E);
            return;
        } else {
            final int allocTpCd = Integer.parseInt(param.xxAllocTpCd.getValue());
            if (allocTpCd > ALLOC_TP_CD_MAX || allocTpCd < ALLOC_TP_CD_MIN) {
                msgMap.addXxMsgId(NWZM0214E);
            }
        }
    }

    private static boolean chkComponentItem(final SHPG_PLNTMsg shpgPlnMsg) {

        if (!hasValue(shpgPlnMsg.setMdseCd)) {
            return FALSE;
        }

        return TRUE;
    }

    private static boolean chkDistributionItem(final String distTpCd) {

        if (DIST_TP.DISTRIBUTION.equals(distTpCd)) {
            return TRUE;
        }

        return FALSE;
    }

    private static void chkInputParamCancel(S21ApiMessageMap msgMap) {

        NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();

        if (ORG_FUNC_CD_SOFT_ALLOC.equals(param.xxOrigFuncTpCd.getValue()) || ORG_FUNC_CD_DISTRIBUTION.equals(param.xxOrigFuncTpCd.getValue())) {

            if (!hasValue(param.softAllocPk)) {
                msgMap.addXxMsgId(NWZM0402E);
                return;
            }
        }
    }

    private static void chkInputParamNew(S21ApiMessageMap msgMap, final MdseData mdseInfo) {
            
        final NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();

        if (!hasValue(param.xxPrtlAcptFlg)) {
            msgMap.addXxMsgId(NWZM0170E);
            return;
        } else {
            final int prtlAcptFlg = Integer.parseInt(param.xxPrtlAcptFlg.getValue());
            if (prtlAcptFlg < PRTL_ACPT_FLG_MIN || prtlAcptFlg > PRTL_ACPT_FLG_MAX) {
                msgMap.addXxMsgId(NWZM0210E);
            }
        }

        if (!hasValue(param.xxItemFlipAcptFlg)) {
            msgMap.addXxMsgId(NWZM0171E);
            return;
        } else {
            final int itemFlipAcptFlg = Integer.parseInt(param.xxItemFlipAcptFlg.getValue());
            if (itemFlipAcptFlg < ITEM_FLIP_ACPT_FLG_MIN || itemFlipAcptFlg > ITEM_FLIP_ACPT_FLG_MAX) {
                msgMap.addXxMsgId(NWZM0211E);
            }
        }

        if (!hasValue(param.xxWhFlipAcptFlg)) {
            msgMap.addXxMsgId(NWZM0172E);
            return;
        } else {
            final int whFlipAcptFlg = Integer.parseInt(param.xxWhFlipAcptFlg.getValue());
            if (whFlipAcptFlg < WH_FLIP_ACPT_FLG_MIN || whFlipAcptFlg > WH_FLIP_ACPT_FLG_MAX) {
                msgMap.addXxMsgId(NWZM0212E);
            }
        }

        if (!hasValue(param.cpoOrdTs)) {
            msgMap.addXxMsgId(NWZM0196E);
            return;
        }

        if (!MDSE_TP.SET.equals(mdseInfo.getMdseTpCd())) {
            
            if (!hasValue(param.rddDt) && !hasValue(param.rsdDt)) {
                msgMap.addXxMsgId(NWZM0178E);
                return;
            }

            if (!hasValue(param.stkStsCd)) {
                msgMap.addXxMsgId(NWZM0174E);
                return;
            }

            if (!hasValue(param.frtChrgMethCd)) {
                msgMap.addXxMsgId(NWZM0358E);
                return;
            }

            if (!hasValue(param.frtChrgToCd)) {
                msgMap.addXxMsgId(NWZM0175E);
                return;
            }

            if (!hasValue(param.shpgSvcLvlCd)) {
                msgMap.addXxMsgId(NWZM0049E);
                return;
            }

            if (!hasValue(param.uomCpltFlg)) {
                msgMap.addXxMsgId(NWZM0228E);
                return;
            }

            if (!hasValue(param.uomCd)) {
                msgMap.addXxMsgId(NWZM0184E);
                return;
            }

        }

        if (!hasValue(param.slsRepTocCd)) {
            msgMap.addXxMsgId(NWZM0194E);
            return;
        }

        if (!hasValue(param.billToCustCd)) {
            msgMap.addXxMsgId(NWZM0179E);
            return;
        }

        if (!hasValue(param.sellToCustCd)) {
            msgMap.addXxMsgId(NWZM0180E);
            return;
        }

        if (!hasValue(param.shipToCustCd)) {
            msgMap.addXxMsgId(NWZM0181E);
            return;
        }

        if (!hasValue(param.setItemShipCpltFlg)) {
            msgMap.addXxMsgId(NWZM0227E);
            return;
        }

        if (!hasValue(param.xxRqstQty)) {
            msgMap.addXxMsgId(NWZM0185E);
            return;
        } else {
            if (param.xxRqstQty.getValue().compareTo(ZERO) <= 0) {
                msgMap.addXxMsgId(NWZM0208E);
                return;
            }
        }

        if (!ORG_FUNC_CD_ORDER_ENTRY.equals(param.xxOrigFuncTpCd.getValue()) && !MDSE_TP.SET.equals(mdseInfo.getMdseTpCd())) {
            if (!hasValue(param.invtyLocCd)) {
                msgMap.addXxMsgId(NWZM0047E);
                return;
            }
        }

        if (ORG_FUNC_CD_SOFT_ALLOC.equals(param.xxOrigFuncTpCd.getValue())) {

            if (!hasValue(param.distPk)) {
                msgMap.addXxMsgId(NWZM0359E);
                return;
            }

            if (!hasValue(param.distPlnNum)) {
                msgMap.addXxMsgId(NWZM0191E);
                return;
            }

            if (!hasValue(param.distTmFrameNum)) {
                msgMap.addXxMsgId(NWZM0192E);
                return;
            }

            if (!hasValue(param.distStruSegPk)) {
                msgMap.addXxMsgId(NWZM0360E);
                return;
            }

            if (!hasValue(param.mdseCd_SA)) {
                msgMap.addXxMsgId(NWZM0176E);
                return;
            }

            if (!MDSE_TP.SET.equals(mdseInfo.getMdseTpCd())) {
                if (!hasValue(param.shpgSvcLvlCd_SA)) {
                    msgMap.addXxMsgId(NWZM0362E);
                    return;
                }
            }
        }
    }

    private static boolean chkItemforStkSts(final String distTpCd, final String stkStsCd) {

        if (chkDistributionItem(distTpCd) && !STK_STS.GOOD.equals(stkStsCd)) {
            return TRUE;
        }

        return FALSE;
    }

    private static boolean chkNoneItem(final String distTpCd) {

        if (DIST_TP.NONE.equals(distTpCd)) {
            return TRUE;
        }

        return FALSE;
    }

    private static boolean chkSetComponentDistItem(S21ApiMessageMap msgMap, final SHPG_PLNTMsg shpgPlnMsg) {

        if (!chkComponentItem(shpgPlnMsg)) {
            return FALSE;
        }

        final MDSETMsg setMdseMsg = getMdse(shpgPlnMsg.glblCmpyCd.getValue(), shpgPlnMsg.setMdseCd.getValue());
        if (null == setMdseMsg) {
            msgMap.addXxMsgId(NWZM0364E);
            return FALSE;
        }

        final boolean itemTp = chkDistributionItem(setMdseMsg.invtyDistTpCd.getValue());
        if (itemTp) {
            return TRUE;
        }

        return FALSE;
    }

    private static boolean chkSetItem(final MdseData mdseInfo) {

        if (MDSE_TP.SET.equals(mdseInfo.getMdseTpCd())) {
            return TRUE;
        }
        return FALSE;
    }

    private static void chkTrxSrcTp(S21ApiMessageMap msgMap) {
            
        final NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();

        TRX_SRC_TPTMsg trxSrcTpTMsg = new TRX_SRC_TPTMsg();
        setValue(trxSrcTpTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(trxSrcTpTMsg.trxSrcTpCd, param.trxSrcTpCd);
        trxSrcTpTMsg = (TRX_SRC_TPTMsg) findByKeyWithCache(trxSrcTpTMsg);
        
        final boolean checkRes = (trxSrcTpTMsg != null && !N.equals(trxSrcTpTMsg.wsAllocFlg.getValue()));

        if (!checkRes) {
            msgMap.addXxMsgId(NWZM0446E);
        }
    }

    private static String disitMdseCd(String mdseCd) {

        if (ORD_TAKE_MDSE_CD_DIGIT < mdseCd.length()) {
            return mdseCd.substring(0, ORD_TAKE_MDSE_CD_DIGIT);
        } else {
            return mdseCd;
        }
    }

    private static EZDTMsg findByKeyWithCache(EZDTMsg reqTMsg) {
        
        return S21CacheTBLAccessor.findByKey(reqTMsg);
    }

    private static MDSETMsg getMdse(final String glblCmpyCd, final String mdseCd) {

        return NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }

    private static MdseData getMdseInfo(S21ApiMessageMap msgMap, final String mdseCd) {

        NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();

        if (!hasValue(mdseCd)) {
            msgMap.addXxMsgId(NWZM0021E);
            return null;
        }

        final MDSETMsg mdseTMsg = getMdse(param.glblCmpyCd.getValue(), mdseCd);

        if (mdseTMsg == null) {
            msgMap.addXxMsgId(NWZM0364E);
            return null;
        }

        final MdseData mdseInfo = new MdseData();

        if (getOdrTakeMdse(param.glblCmpyCd.getValue(), mdseCd) == null) {
            // 10 Digit Item
            setMdseInfo(mdseTMsg, mdseInfo, mdseCd, MDSE_CD_FLG);
        } else {
            // 8 Digit Item
            setMdseInfo(mdseTMsg, mdseInfo, mdseCd, ORD_TAKE_MDSE_CD_FLG);
        }
        // Del Start S21_NA#5872
        // if (hasValue(param.invtyLocCd)) {
        //     setInvtyHardAllocTpCd(msgMap, mdseTMsg, mdseInfo);
        // }
        // Del End S21_NA#5872

        return mdseInfo;

    }
 // DEL Start S21_NA#5872
//    private static MDSE_WH_CONDTMsg getMdseWHCond(S21ApiMessageMap msgMap, final String mdseCd) {
//
//        NWZC102001PMsg param = (NWZC102001PMsg) msgMap.getPmsg();
//
//        final MDSE_WH_CONDTMsg mdseWhCondMsg = new MDSE_WH_CONDTMsg();
//        setValue(mdseWhCondMsg.glblCmpyCd, param.glblCmpyCd);
//        setValue(mdseWhCondMsg.mdseCd,     mdseCd);
//        setValue(mdseWhCondMsg.whCd,       param.invtyLocCd);
//
//        return (MDSE_WH_CONDTMsg) findByKeyWithCache(mdseWhCondMsg);
//    }
 // DEL END S21_NA#5872

    private static ORD_TAKE_MDSETMsg getOdrTakeMdse(final String glblCmpyCd, final String mdseCd) {

        return NWXOrdTakeMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }

    private static BigDecimal getSoftAllocSQ() {

        return ZYPOracleSeqAccessor.getNumberBigDecimal("SOFT_ALLOC_SQ");
    }

    private static boolean hasMessage(S21ApiMessageMap msgMap) {

        if (!msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private static void insertSoftAlloc(S21ApiMessageMap msgMap, SOFT_ALLOCTMsg softAllocMsg) {

//        S21FastTBLAccessor.insert(softAllocMsg);
        insert(softAllocMsg);

        if (!RTNCD_NORMAL.equals(softAllocMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0662E);
        }
    }

    private static boolean isEmpty(Collection col) {
        return col == null || col.isEmpty();
    }

    private static SHPG_PLNTMsg lockShpgPln(S21ApiMessageMap msgMap, final String shpgStsCd) {
            
        final SHPG_PLNTMsgArray shpgPlnTMsgArray = lockShpgPlnArray((NWZC102001PMsg)msgMap.getPmsg(), shpgStsCd);

        if (shpgPlnTMsgArray.getValidCount() == 0) {
            msgMap.addXxMsgId(NWZM0075E);
            return null;
        }

        return shpgPlnTMsgArray.no(0);
    }

    private static SHPG_PLNTMsgArray lockShpgPlnArray(NWZC102001PMsg param, final String shpgStsCd) {

        final SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        shpgPlnTMsg.setSQLID("020");
        shpgPlnTMsg.setConditionValue("glblCmpyCd01",    param.glblCmpyCd.getValue());
        shpgPlnTMsg.setConditionValue("trxHdrNum01",     param.trxHdrNum.getValue());
        shpgPlnTMsg.setConditionValue("trxLineNum01",    param.trxLineNum.getValue());
        shpgPlnTMsg.setConditionValue("trxLineSubNum01", param.trxLineSubNum.getValue());
        shpgPlnTMsg.setConditionValue("trxSrcTpCd01",    param.trxSrcTpCd.getValue());
        shpgPlnTMsg.setConditionValue("shpgStsCd01",     shpgStsCd);

        return (SHPG_PLNTMsgArray) findByConditionForUpdate(shpgPlnTMsg);
    }

    // DEL Start S21_NA#5872
//    private static void setInvtyHardAllocTpCd(S21ApiMessageMap msgMap, final MDSETMsg mdseMsg, MdseData mdseInfo) {
//
//        if (!MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue())) {
//
//            final MDSE_WH_CONDTMsg mdseWhCondMsg = getMdseWHCond(msgMap, mdseMsg.mdseCd.getValue());
//
//            if (mdseWhCondMsg != null) {
//                mdseInfo.setInvtyHardAllocTpCd((mdseWhCondMsg.invtyHardAllocTpCd.getValue()));
//            }
//        }
//    }
    // DEL END S21_NA#5872

    private static void setMdseInfo(final MDSETMsg mdseMsg, MdseData mdseData, final String mdseCd, final String ordTakeMdseType) {

        mdseData.setGlblCmpyCd(mdseMsg.glblCmpyCd.getValue());
        mdseData.setMdseCd(mdseCd);
        mdseData.setMdseTpCd(mdseMsg.mdseTpCd.getValue());
        mdseData.setInvtyCtrlFlg(mdseMsg.invtyCtrlFlg.getValue());
        mdseData.setInvtyDistTpCd(mdseMsg.invtyDistTpCd.getValue());
        mdseData.setInvtyHardAllocTpCd(mdseMsg.invtyHardAllocTpCd.getValue());
        mdseData.setInvtySoftAllocTpCd(mdseMsg.invtySoftAllocTpCd.getValue());
        // Mod Start S21_NA#5872
        // mdseData.setDaysPriAllocNum(mdseMsg.daysPriAllocNum.getValue());
        mdseData.setDaysPriAllocNum(BigDecimal.ZERO);
        // Mod Start S21_NA#5872
        mdseData.setOrdTakeMdseFlg(ordTakeMdseType);

    }

    private static void updateSoftAlloc(S21ApiMessageMap msgMap, SOFT_ALLOCTMsg softAllocMsg) {

//        S21FastTBLAccessor.update(softAllocMsg);
        update(softAllocMsg);

        if (!RTNCD_NORMAL.equals(softAllocMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0662E);
        }
    }

    private static void writePerformanceProfilingLogEnd(Class clazz, String methodNm) {
        final StringBuilder sb = new StringBuilder();
        sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [E N D] ").append(System.currentTimeMillis());
        System.out.println(sb.toString());
    }
    
    private static void writePerformanceProfilingLogStart(Class clazz, String methodNm) {
        final StringBuilder sb = new StringBuilder();
        sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [START] ").append(System.currentTimeMillis());
        System.out.println(sb.toString());
    }

    // Add Start S21_NA#5872
    /**
     * set INVTY_HARD_ALLOC_TP_CD MdseInfo
     */
    private void setInvtyHardAllocTpCd(NWZC102001PMsg param, final MdseData mdseInfo) {

        String allocTpCd = "";
        Map<String, Object> resultMap = null;

        allocTpCd = getAllocTpFromWH_MDSE(param);
        if (ZYPCommonFunc.hasValue(allocTpCd)) {
            mdseInfo.setInvtyHardAllocTpCd(allocTpCd);

        } else {
            resultMap = getAllocTpFromBU_WH_CATG(param);
            if (resultMap != null) {
                mdseInfo.setInvtyHardAllocTpCd((String) resultMap.get("INVTY_HARD_ALLOC_TP_CD"));
                mdseInfo.setDaysPriAllocNum((BigDecimal) resultMap.get("TM_FENCE_DAYS_AOT"));
            }
        }

    }

    /**
     * Get INVTY_HARD_ALLOC_TP_CD BY WH･SWH/MDSE
     * @param param NWZC102001PMsg
     * @return String
     */
    private String getAllocTpFromWH_MDSE(NWZC102001PMsg param) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", param.mdseCd.getValue());
        ssmParam.put("invtyLocCd", param.invtyLocCd.getValue());

        String allocTpCd = (String) ssmClient.queryObject("getAllocTpFromWH_MDSE", ssmParam);
        if (!ZYPCommonFunc.hasValue(allocTpCd)) {
            allocTpCd = "";
        }
        return allocTpCd;
    }

    /**
     * Get INVTY_HARD_ALLOC_TP_CD and TM_FENCE_DAYS_AOT by LOB/WH･SWH/OrderCategory
     * @param param NWZC102001PMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getAllocTpFromBU_WH_CATG(NWZC102001PMsg param) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", param.trxHdrNum.getValue());
        ssmParam.put("cpoDtlLineNum", param.trxLineNum.getValue());
        ssmParam.put("cpoDtlLineSubNum", param.trxLineSubNum.getValue());

        List<Map<String, Object>> resultList = //
        (List<Map<String, Object>>) ssmClient.queryObjectList("getAllocTpFromBU_WH_CATG", ssmParam);

        if (resultList.size() > 0) {
            return (Map<String, Object>) resultList.get(0);
        }
        return null;
    }
    // Add End S21_NA#5872
}
