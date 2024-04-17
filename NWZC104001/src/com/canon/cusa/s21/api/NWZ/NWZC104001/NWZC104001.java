/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * Hard Allocation API(NWZC1040)
 * The order is Allocated for the inventory information. 
 * It puts it into the state that the order can be shipped. 
 * The processing executed with Hard Allocation API is two following processing. 
 *  - New Process 
 *  - Cancel Process
 * </pre>
 * 
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/09     Fujitsu         A.Suda          Create          N/A
 * 09/14/09     Fujitsu         A.Suda          Update
 * 10/12/09     Fujitsu         A.Suda          Update   
 * 11/05/09     Fujitsu         A.Suda          Update   
 * 11/17/09     Fujitsu         A.Suda          Update    
 * 12/09/09     Fujitsu         A.Suda          Update          
 * 12/18/09     Fujitsu         K.Tajima        Update          N/A (refactoring to get high-performance)
 * 02/05/10     Fujitsu         S.Yamamoto      Update          3348
 * 03/13/10     Fujitsu         A.Suda          Update          4785
 * 03/25/10     Fujitsu         A.Suda          Update          5190
 * 04/01/10     Fujitsu         A.Suda          Update          5358
 * 04/29/10     Fujitsu         A.Suda          Update          5359
 * 05/25/10     Fujitsu         A.Suda          Update          5359 (modify)
 * 07/02/10     Fujitsu         A.Suda          Update          7493
 * 11/08/10     Fujitsu         K.Tajima        Update          Performance tuning
 * 11/18/10     CSAI            T.Gotoda        Update           694
 * 12/16/10     Fujitsu         A.Suda          Update           948
 * 04/18/11     CSAI            T.Gotoda        Update          1763
 * 11/13/12     Fujitsu         T.Ishii         Update          WDS (WS Bug Fix)
 * 02/01/13     Fujitsu         A.Suda          Update          WDS Defect#539
 * 10/04/13     Fujitsu         S.Yamamoto      Update          WDS#2484
 * 11/11/13     Fujitsu         S.Yamamoto      Update          WDS#2745
 * 11/14/13     Fujitsu         M.Fuji          Update          WDS#2745
 * 03/13/17     Fujitsu         T.Aoi           Update          S21_NA#16987
 * 09/06/19     Fujitsu         T.Noguchi       Update          S21_NA#53087
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC104001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_0;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_1;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByConditionForUpdate;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.insert;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.logicalRemove;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.update;
import static com.canon.cusa.s21.framework.common.S21StringUtil.isEquals;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKey;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdateAPI;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ALLOC_THRHDTMsg;
import business.db.CPOTMsg;
import business.db.HARD_ALLOCTMsg;
import business.db.HARD_ALLOCTMsgArray;
import business.db.HLDTMsg;
import business.db.HLDTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRC_DTLTMsg;
import business.db.PRC_DTLTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.SOFT_ALLOCTMsg;
import business.db.SOFT_ALLOCTMsgArray;
import business.db.VAR_CHAR_CONSTTMsg;
import business.parts.NLZC001001PMsg;
import business.parts.NWZC004001PMsg;
import business.parts.NWZC104001PMsg;
import business.parts.NWZC104001_AllocationInfoPMsg;
import business.parts.NWZC104002PMsg;
import business.parts.NWZC188001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC001001.NLZC001001;
import com.canon.cusa.s21.api.NWZ.NWZC004001.NWZC004001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.common.NWX.NWXC002007.NWXC002007BackOrder;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmListResultSetHandlerSupport;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;

public class NWZC104001 extends S21ApiCommonBase {

    /** Request Type - New Allocation */
    public static final String REQ_TP_NEW            = "1";
    /** Request Type - Order Detail Cancel */
    public static final String REQ_TP_ORDER_CANCEL   = "2";
    /** Request Type - Shipping level Cancel */
    public static final String REQ_TP_SHIPPNG_CANCEL = "3";

    public static final String NWZM0189E = "NWZM0189E";
    public static final String NWZM0209E = "NWZM0209E";
    public static final String NWZM0011E = "NWZM0011E";
    public static final String NWZM0014E = "NWZM0014E";
    public static final String NWZM0027E = "NWZM0027E";
    public static final String NWZM0089E = "NWZM0089E";
    public static final String NWZM0043E = "NWZM0043E";
    public static final String NWZM0170E = "NWZM0170E";
    public static final String NWZM0175E = "NWZM0175E";
    public static final String NWZM0049E = "NWZM0049E";
    public static final String NWZM0178E = "NWZM0178E";
    public static final String NWZM0179E = "NWZM0179E";
    public static final String NWZM0180E = "NWZM0180E";
    public static final String NWZM0181E = "NWZM0181E";
    public static final String NWZM0182E = "NWZM0182E";
    public static final String NWZM0183E = "NWZM0183E";
    public static final String NWZM0227E = "NWZM0227E";
    public static final String NWZM0228E = "NWZM0228E";
    public static final String NWZM0184E = "NWZM0184E";
    public static final String NWZM0092E = "NWZM0092E";
    public static final String NWZM0093E = "NWZM0093E";
    public static final String NWZM0094E = "NWZM0094E";
    public static final String NWZM0095E = "NWZM0095E";
    public static final String NWZM0096E = "NWZM0096E";
    public static final String NWZM0185E = "NWZM0185E";
    public static final String NWZM0208E = "NWZM0208E";
    public static final String NWZM0171E = "NWZM0171E";
    public static final String NWZM0172E = "NWZM0172E";
    public static final String NWZM0249E = "NWZM0249E";
    public static final String NWZM0279E = "NWZM0279E";
    public static final String NWZM0280E = "NWZM0280E";
    public static final String NWZM0281E = "NWZM0281E";
    public static final String NWZM0282E = "NWZM0282E";
    public static final String NWZM0283E = "NWZM0283E";
    public static final String NWZM0284E = "NWZM0284E";
    public static final String NWZM0285E = "NWZM0285E";
    public static final String NWZM0075E = "NWZM0075E";
    public static final String NWZM0076E = "NWZM0076E";
    public static final String NWZM0202E = "NWZM0202E";
    public static final String NWZM0083E = "NWZM0083E";
    public static final String NWZM0364E = "NWZM0364E";
    public static final String NWZM0399E = "NWZM0399E";
    public static final String NWZM0400E = "NWZM0400E";
    public static final String NWZM0346E = "NWZM0346E";
    public static final String NWZM0358E = "NWZM0358E";
    public static final String NWZM0401E = "NWZM0401E";
    public static final String NWZM0362E = "NWZM0362E";
    public static final String NWZM0402E = "NWZM0402E";
    public static final String NWZM0253E = "NWZM0253E";
    public static final String NWZM0398E = "NWZM0398E";
    public static final String NWZM0021E = "NWZM0021E";
    public static final String NWZM0047E = "NWZM0047E";
    public static final String NWZM0174E = "NWZM0174E";
    public static final String NWZM0198E = "NWZM0198E";
    public static final String NWZM0091E = "NWZM0091E";
    public static final String NWZM0176E = "NWZM0176E";
    public static final String NWZM0177E = "NWZM0177E";
    public static final String NWZM0678E = "NWZM0678E";
    public static final String NWZM0679E = "NWZM0679E";
    public static final String NWZM0680E = "NWZM0680E";
    public static final String NLZM0010E = "NLZM0010E";
    public static final String NWZM0078E = "NWZM0078E";
    public static final String NWZM0079E = "NWZM0079E";
    public static final String NWZM0662E = "NWZM0662E";
    public static final String NWZM0200E = "NWZM0200E";
    public static final String NWZM0165E = "NWZM0165E";
    public static final String NWZM0920E = "NWZM0920E";
    public static final String NWZM0751E = "NWZM0751E";
    public static final String NWZM0944E = "NWZM0944E";

    /** Item Type - Distribution Item */
    private static final String DISTRIBUTION_ITEM = "Dist Item";

    /** Item Type - None Item */
    private static final String NONE_ITEM = "None Item";

    /** Normal end */
    private static final String RTNCD_NORMAL = EZDTBLAccessor.RTNCD_NORMAL;

    /** Shpg Plan Number MAX_NUM */
    private static final int SHPG_PLN_MAX_NUM = 8;

    /** Shpg Plan Dply Number MAX_NUM */
    private static final int SHPG_PLN_DPLY_MAX_NUM = 3;

    /** Date Format */
    private static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** TRX_LINE_SUB_NUM_SET */
    private static final String TRX_LINE_SUB_NUM_SET = "000";

    /** PROC_TP_CD - Allocation */
    private static final String PROC_TP_CD_ALLOC = "1";

    /** PROC_TP_CD - Cancel Allocation */
    private static final String PROC_TP_CD_CANCEL = "2";

    /** ALLOC_OPT - Allocation */
    private static final String ALLOC_OPT_ALLOC = "0";

    private static final String Y = ZYPConstant.FLG_ON_Y;
    private static final String N = ZYPConstant.FLG_OFF_N;

    /** CASE_UOM_CD - Var Char CONST_VAL */
    private static final String CASE_UOM_CD = "CASE_UOM_CD";

    // START ADD S.Yamamoto [#2745]
    /** ALLOC_AF_CR_CHKED_FLG - Var Char CONST_VAL */
    private static final String ALLOC_AF_CR_CHKED_FLG = "ALLOC_AF_CR_CHKED_FLG";
    // END   ADD S.Yamamoto [#2745]

    /** digit of 'ORD_TAKE_MDSE_CD' */
    private static final int ORD_TAKE_MDSE_CD_DIGIT = new ORD_TAKE_MDSETMsg().getAttr("ordTakeMdseCd").getDigit();

    boolean exeBizProcLogFlg = true;
    Map<String, Object> compQtyList;

    /** Shipping Plan Number List */
    List<String> shpgPlnNumList;

    private S21SsmBatchClient ssmClient;
    private ONBATCH_TYPE onBatchType;
    private String setShpgPlnNum;
    private String originalShpgPlnNum;
    private String glblCmpyCd;

    /**
     * Validation Process Manager Process API
     */
    private NWZC004001 validProcMgrAPI;

    /**
     * Inventory Allocation API
     */
    private NLZC001001 invtyAllocAPI;

    public NWZC104001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute Hard Allocation API
     * @param param1 NWZC104001PMsg Interface
     * @param params2 NWZC104002PMsg<List>
     * @param onBatchType onBatchType
     */
    public void execute(final NWZC104001PMsg param1, final List<NWZC104002PMsg> params2, final ONBATCH_TYPE onBatchType) {
        final String methodNm = "execute";
        writePerformanceProfilingLogStart(getClass(), methodNm);
        
        try {
            
            final S21ApiMessageMap msgMap = new S21ApiMessageMap(param1);
    
            this.doProcess(msgMap, params2, onBatchType);
    
            msgMap.flush();
            
        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /**
     * @see com.canon.cusa.s21.api.NWZ.NWZC104001.execute
     * @param params1 NWZC104001PMsg<List>
     * @param params2 NWZC104002PMsg<List>
     * @param onBatchType onBatchType
     */
    public void execute(final List<NWZC104001PMsg> params1, final List<NWZC104002PMsg> params2, final ONBATCH_TYPE onBatchType) {

        for (NWZC104001PMsg param1 : params1) {
            this.execute(param1, params2, onBatchType);
        }
    }

    /**
     * Process Type
     * 
     * <pre>
     *  Processing is executed according to a set value of ReqestType Cord(RqstTpCd) of the input parameter. 
     *  Value of ReqestType Cord
     *  1:NewAllocation Process
     *  2:Cancel Process
     *  3:Cancel Process
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param params2 NWZC104002PMsg<List>
     */
    protected void doProcess(S21ApiMessageMap msgMap, final List<NWZC104002PMsg> params2, final ONBATCH_TYPE onBatchType) {

        // initialize global variables.
        this.onBatchType        = onBatchType;
        this.setShpgPlnNum      = null;
        this.originalShpgPlnNum = null;
        this.glblCmpyCd         = null;
        this.exeBizProcLogFlg   = true;
        this.compQtyList        = new HashMap<String, Object>();

        
        chkParameter(msgMap, params2);
        if (isError(msgMap)) {
            return;
        }

        final NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();
        this.glblCmpyCd = param1.glblCmpyCd.getValue();
        this.shpgPlnNumList = new ArrayList<String>();
        
        final String xxRqstTpCd = param1.xxRqstTpCd.getValue();

        if (isEquals(REQ_TP_NEW, xxRqstTpCd)) {
            this.newProcess(msgMap, params2, onBatchType);

        } else if (isEquals(REQ_TP_ORDER_CANCEL, xxRqstTpCd)) {
            this.cancelProcess(msgMap, onBatchType);

        } else if (isEquals(REQ_TP_SHIPPNG_CANCEL, xxRqstTpCd)) {
            this.cancelProcess(msgMap, onBatchType);

        } else {
            msgMap.addXxMsgId(NWZM0209E);
        }

        // call Display Order Status Update API
        this.executeNWZC188001(msgMap, param1);
    }

    /**
     * Execute New Process
     * 
     * <pre>
     * 1.ItemFlip(true/false)
     * 2.Set Allocation target Item Information 
     * 3.Calcurate Allocation Avalavble QTY
     * 4.Update INVTY Master(Execute Invty Allocation API)
     * 5.Update SHPG_PLN,PRC_DTL,HLD,SOFT_ALLOC,HARD_ALLLOC
     * 6.Execute Bussiness Process Log
     * 7.Execute Validation Process Manager API
     * 8.Update Input Parameter
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param params2 NWZC104002PMsg<List>
     * @param onBatchType ONBATCH_TYPE
     */
    void newProcess(S21ApiMessageMap msgMap, final List<NWZC104002PMsg> params2, final ONBATCH_TYPE onBatchType) {
        final String methodNm = "newProcess";
        writePerformanceProfilingLogStart(getClass(), methodNm);
        
        try {
            
            final NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();
    

            /*******************************************************
             * Item Set
             *******************************************************/
            final String itemType = chkItemType(param1);
            if (isError(msgMap)) {
                return;
            }
    
            List<AllocationItemData> itemDataList = new ArrayList<AllocationItemData>();
            
            final boolean itemFlipFlg = chkItemFlip(param1);
    
            if (!itemFlipFlg) {
                setCommonData(param1, itemDataList, itemType, itemFlipFlg);
    
            } else {
                
                final List<AllocationItemData> itemFlipList = this.getItemFlipList(msgMap);
                if (isError(msgMap)) {
                    return;
                }
    
                setCommonData(param1, itemFlipList, itemType, itemFlipFlg);
    
                itemDataList = sortItemDataList(msgMap, itemFlipList);
                if (isError(msgMap)) {
                    return;
                }
            }
    
            final boolean avalInvtyInfoFlg = getInvtyInfo(msgMap, itemDataList, params2);
            if (!avalInvtyInfoFlg || isError(msgMap)) {
                return;
            }

            
            /*******************************************************
             * Calcurate Allocation Avalavble QTY.
             * Update INVTY Master(Execute Invty Allocation API).
             *******************************************************/
            setInEachQty(msgMap, itemDataList);
            if (isError(msgMap)) {
                return;
            }
    
            final boolean allocationFlg = this.invtyUpdateProcess(msgMap, itemDataList, onBatchType);
            if (!allocationFlg || isError(msgMap)) {
                return;
            }
    
            
            /*******************************************************
             * DB Update.
             *  - SHPG_PLN
             *  - PRC_DTL
             *  - HLD
             *  - SOFT_ALLOC
             *  - HARD_ALLOC
             *  - ALLOC_THRHD
             *  
             * Execute Bussiness Process Log
             * Execute Validation Process Manager API
             *******************************************************/
            // START ADD S.Yamamoto [#2745]
            final String allocAfCrChkFlg = ZYPCodeDataUtil.getVarCharConstValue(ALLOC_AF_CR_CHKED_FLG, glblCmpyCd);
            // END   ADD S.Yamamoto [#2745]
            for (AllocationItemData itemData : itemDataList) {
    
                if (itemData.isTargetFlg()) {
    
                    // START MODIFY S.Yamamoto [#2745]
                    // START MODIFY S.Yamamoto [QC#2484]
//                    this.transactionProcess(msgMap, itemData);
                    final boolean transactionProcessFlg = this.transactionProcess(msgMap, itemData, allocAfCrChkFlg);
                    // END   MODIFY S.Yamamoto [QC#2484]
                    // END   MODIFY S.Yamamoto [#2745]
    
                    // START MODIFY S.Yamamoto [QC#2484]
//                    if (isError(msgMap)) {
                    if (!transactionProcessFlg || isError(msgMap)) {
                    // END   MODIFY S.Yamamoto [QC#2484]
                        return;
                    }
    
                    if (Y.equals(itemData.getFisrtFlipFlag())) {
                        
                        // [ALLOC_THRHD]
                        final ALLOC_THRHDTMsg allocThrhdMsg = lockAllocThrhd(itemData);
                        setValue(allocThrhdMsg.firstFlipFlg, Y);
                        
                        // ***** update
                        updateAllocThrhd(msgMap, allocThrhdMsg);
                    }
    
                } else {
                    continue;
                }
            }
    
            
            /*******************************************************
             * Set result PMsg datas.
             *******************************************************/
            setResPMsg(itemDataList, param1);
            setResPMsg(itemDataList, params2);
        
        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private BigDecimal calcAvailableQty(S21ApiMessageMap msgMap, List<AllocationItemData> itemList) {

        final NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();
        BigDecimal xxRqstQty = param1.xxRqstQty.getValue();

        for (int i = 0; i < itemList.size(); i++) {
            
            // It calculates in consideration of thresholdQty.
            final AllocationItemData itemData = itemList.get(i);
            BigDecimal eachQty               = itemData.getInEachQty();
            BigDecimal thresholdQty          = itemData.getThresholdQty();
            BigDecimal invtyAvailableQty     = itemData.getInvtyAvailableQty();
            BigDecimal calcQty               = ZERO;
            BigDecimal calcInvtyAvailableQty = ZERO;
            BigDecimal remaindNum            = ZERO;
            BigDecimal subInvtyAvailableQty  = ZERO;

            if (thresholdQty == null || thresholdQty.compareTo(ZERO) < 0) {
                continue;
            }

            if (thresholdQty.compareTo(invtyAvailableQty) >= 0) {
                continue;
            }

            if (eachQty.compareTo(xxRqstQty) > 0) {
                continue;
            }

            if (eachQty.compareTo(invtyAvailableQty) > 0) {
                continue;
            }

            // subInvtyAvailableQty = invtyAvailableQty - thresholdQty
            // remaindNum = subInvtyAvailableQty * eachQty
            // calcInvtyAvailableQty = subInvtyAvailableQty -
            // remaindNum

            subInvtyAvailableQty = subtract(invtyAvailableQty, thresholdQty);
            remaindNum = remainder(subInvtyAvailableQty, eachQty);
            calcInvtyAvailableQty = subtract(subInvtyAvailableQty, remaindNum);

            if (calcInvtyAvailableQty.compareTo(xxRqstQty) > 0) {

                // remaindRqstNum = rqstQty * eachQty
                // calcQty(calcAllocationAvailableQty) = rqstQty -
                // remaindRqstNum

                BigDecimal remaindRqstNum = ZERO;
                remaindRqstNum = remainder(xxRqstQty, eachQty);
                calcQty = subtract(xxRqstQty, remaindRqstNum);
                itemData.setAvailableQty(calcQty);
                itemData.setTargetFlg(true);

            } else {
                calcQty = calcInvtyAvailableQty;
                itemData.setAvailableQty(calcInvtyAvailableQty);
                itemData.setTargetFlg(true);

            }

            xxRqstQty = subtract(xxRqstQty, itemData.getAvailableQty());

            if (xxRqstQty.compareTo(ZERO) <= 0) {
                // Update ALLOC_THRHD

                for (int j = i; j > -1; j--) {
                    if (itemData.getFisrtFlipFlag() == null) {
                        continue;
                    }

                    final ALLOC_THRHDTMsg allocThrhdMsg = searchThresHold(itemList.get(i));

                    if (allocThrhdMsg == null) {
                        continue;
                    } else {
                        itemData.setFisrtFlipFlag(Y);
                        break;
                    }
                }

                return xxRqstQty;
            }
        }

        xxRqstQty = param1.xxRqstQty.getValue();
        initAllocationItemData(itemList);
        int cnt = 0;

        for (int i = 0; i < itemList.size(); i++) {
            // It calculates without considering thresholdQty.

            cnt = i;
            AllocationItemData itemData = itemList.get(i);
            BigDecimal calcQty = ZERO;
            BigDecimal eachQty = itemData.getInEachQty();
            BigDecimal invtyAvailableQty = itemData.getInvtyAvailableQty();
            BigDecimal calcInvtyAvailableQty = ZERO;

            if (eachQty.compareTo(xxRqstQty) > 0) {
                continue;

            }
            if (eachQty.compareTo(invtyAvailableQty) > 0) {
                continue;
            }

            BigDecimal remaindInvtyNum = ZERO;
            remaindInvtyNum = remainder(invtyAvailableQty, eachQty);
            calcInvtyAvailableQty = subtract(invtyAvailableQty, remaindInvtyNum);

            if (calcInvtyAvailableQty.compareTo(xxRqstQty) > 0) {

                BigDecimal remaindRqstNum = ZERO;
                remaindRqstNum = remainder(xxRqstQty, eachQty);
                calcQty = subtract(xxRqstQty, remaindRqstNum);
                itemData.setAvailableQty(calcQty);
                itemData.setTargetFlg(true);

            } else {
                calcQty = calcInvtyAvailableQty;
                itemData.setAvailableQty(calcInvtyAvailableQty);
                itemData.setTargetFlg(true);

            }
            xxRqstQty = subtract(xxRqstQty, itemData.getAvailableQty());

            if (xxRqstQty.compareTo(ZERO) == 0) {
                break;
            }

        }

        for (int j = cnt; j > -1; j--) {
            if (itemList.get(j).isTargetFlg()) {
                if (itemList.get(j).getFisrtFlipFlag() == null) {
                    continue;
                }
                ALLOC_THRHDTMsg allocThrhdTMsg = searchThresHold(itemList.get(j));
                if (allocThrhdTMsg == null) {
                    continue;
                } else {
                    itemList.get(j).setFisrtFlipFlag(Y);
                    break;
                }
            }
        }

        return xxRqstQty;

    }

    private void callInvtyAllocAPI(NLZC001001PMsg pMsg, ONBATCH_TYPE onBatchType) {
        
        if (invtyAllocAPI == null) {
            invtyAllocAPI = new NLZC001001();
        }
        invtyAllocAPI.execute(pMsg, onBatchType);
    }

    private void callValidProcMgrAPI(S21ApiMessageMap msgMap, final String shpgPlnNumForHA, AllocationItemData itemData) {

        final NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        final NWZC004001PMsg validProcMgrMsg = new NWZC004001PMsg();
        setValue(validProcMgrMsg.xxRqstTpCd,   NWZC004001.VAL_TP_SV);
        setValue(validProcMgrMsg.glblCmpyCd,   param1.glblCmpyCd.getValue());
        setValue(validProcMgrMsg.shpgPlnNum_I, shpgPlnNumForHA);
        setValue(validProcMgrMsg.slsDt,        param1.slsDt.getValue());

        // --------------------------------------------------
        // call NWZC004001 : Validation Process Manager API
        // --------------------------------------------------
        if (validProcMgrAPI == null) {
            validProcMgrAPI = new NWZC004001();
        }
        validProcMgrAPI.execute(validProcMgrMsg, onBatchType);

        if (validProcMgrMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < validProcMgrMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(validProcMgrMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        } else {
            itemData.setHldFlg(validProcMgrMsg.xxHldFlg.getValue());
        }
    }

    /**
     * Execute Cancel Process
     * 
     * <pre>
     * 1.Delete HArd_ALLOC
     * 2.Calcurate Cancel Avalavble QTY
     * 3.Update SOFT_ALLOC
     * 4.Execute Bussiness Process Log
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void cancelProcess(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        // get HARD_ALLOC
        List<HARD_ALLOCTMsg> cancelHardAllocList = this.getDeleteHardAlloc(param1);

        if (!isEmpty(cancelHardAllocList)) {
            return;
        }

        // Retuen to INVTY
        this.invtyCancelProcess(msgMap, cancelHardAllocList, onBatchType);

        if (isError(msgMap)) {
            return;
        }

        // Retuen to SOFT_ALLOC
        this.softAllocCancelProcess(msgMap, cancelHardAllocList);

        if (isError(msgMap)) {
            return;

        }

        bizProcLogMsg(param1);
    }

    // START DELETE M.Fuji [#2745]
//    private boolean chkCreditLimitHld(S21ApiMessageMap msgMap, final String hldRsnCd) {
//
//        HLD_RSNTMsg hldRsnMsg = this.getHldRsn(hldRsnCd);
//
//        if (hldRsnMsg == null) {
//            msgMap.addXxMsgId(NWZM0079E);
//            return false;
//        }
//
//        if (isEquals(HLD_TP.CREDIT_HOLD, hldRsnMsg.hldTpCd.getValue())) {
//            return true;
//        }
//
//        return false;
//    }
    // END DELETE M.Fuji [#2745]

    private void deleteHardAlloc(HARD_ALLOCTMsg deleteHardAllocMsg) {

        logicalRemoveHardAlloc(deleteHardAllocMsg.hardAllocPk.getValue());

    }

    private String divideProcess(S21ApiMessageMap msgMap, final AllocationItemData itemData, final SHPG_PLNTMsg originalShpgPlnMsg, final BigDecimal allocatedQty, final String newSetShpgPlnNum, final String sts, final boolean remakeFlg) {

        NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        String originalShpgPlnNum = originalShpgPlnMsg.shpgPlnNum.getValue();
        String trxHdrNum = originalShpgPlnMsg.trxHdrNum.getValue();
        String trxLineNum = originalShpgPlnMsg.trxLineNum.getValue();
        String trxLineSubNum = originalShpgPlnMsg.trxLineSubNum.getValue();
        String trxSrcTpCd = originalShpgPlnMsg.trxSrcTpCd.getValue();
        String shpgPlnNumHA;
        BigDecimal calcCrChkQty = ZERO;
        BigDecimal calcavalSoQty = ZERO;
        BigDecimal calcavalPoQty = ZERO;
        PRC_DTLTMsgArray prcDtlData = null;
        SHPG_PLNTMsg haShpgPlnMsg = new SHPG_PLNTMsg();
        EZDMsg.copy(originalShpgPlnMsg, null, haShpgPlnMsg, null);

        // ---------------------------------------------
        // Update SHPG_PLN
        // ---------------------------------------------

        // Amount
        setTotAmt(originalShpgPlnMsg, subtract(originalShpgPlnMsg.ordQty.getValue(), allocatedQty));

        if (originalShpgPlnMsg.softAllocQty.getValue().compareTo(ZERO) > 0) {

            if (originalShpgPlnMsg.softAllocQty.getValue().compareTo(allocatedQty) == 0) {
                // softAllocQty = AllocatedQty
                haShpgPlnMsg.softAllocQty.setValue(allocatedQty);
                originalShpgPlnMsg.softAllocQty.setValue(ZERO);

            } else if (originalShpgPlnMsg.softAllocQty.getValue().compareTo(allocatedQty) > 0) {
                // softAllocQty > AllocatedQty
                haShpgPlnMsg.softAllocQty.setValue(allocatedQty);
                originalShpgPlnMsg.softAllocQty.setValue(subtract(originalShpgPlnMsg.softAllocQty.getValue(), allocatedQty));

            } else {
                // softAllocQty < AllocatedQty
                haShpgPlnMsg.softAllocQty.setValue(originalShpgPlnMsg.softAllocQty.getValue());
                originalShpgPlnMsg.softAllocQty.setValue(ZERO);
            }
        }

        if (originalShpgPlnMsg.slsHldQty.getValue().compareTo(ZERO) == 0) {
            originalShpgPlnMsg.slsHldQty.setValue(ZERO);
        } else {
            originalShpgPlnMsg.slsHldQty.setValue(subtract(haShpgPlnMsg.slsHldQty.getValue(), allocatedQty));
        }

        if (originalShpgPlnMsg.crHldQty.getValue().compareTo(ZERO) == 0) {
            originalShpgPlnMsg.crHldQty.setValue(ZERO);
        } else {
            originalShpgPlnMsg.crHldQty.setValue(subtract(haShpgPlnMsg.crHldQty.getValue(), allocatedQty));
        }

        if (originalShpgPlnMsg.crChkQty.getValue().compareTo(ZERO) == 0) {
            originalShpgPlnMsg.crChkQty.setValue(ZERO);
        } else {

            calcCrChkQty = subtract(haShpgPlnMsg.crChkQty.getValue(), allocatedQty);
            if (ZERO.compareTo(calcCrChkQty) > 0) {
                originalShpgPlnMsg.crChkQty.setValue(ZERO);
            } else {
                originalShpgPlnMsg.crChkQty.setValue(calcCrChkQty);
            }
        }

        if (originalShpgPlnMsg.avalSoQty.getValue().compareTo(ZERO) == 0) {
            originalShpgPlnMsg.avalSoQty.setValue(ZERO);
        } else {

            calcavalSoQty = subtract(haShpgPlnMsg.avalSoQty.getValue(), allocatedQty);
            if (ZERO.compareTo(calcavalSoQty) > 0) {
                originalShpgPlnMsg.avalSoQty.setValue(ZERO);
            } else {
                originalShpgPlnMsg.avalSoQty.setValue(calcavalSoQty);
            }
        }

        if (originalShpgPlnMsg.avalPoQty.getValue().compareTo(ZERO) == 0) {
            originalShpgPlnMsg.avalPoQty.setValue(ZERO);
        } else {

            calcavalPoQty = subtract(haShpgPlnMsg.avalPoQty.getValue(), allocatedQty);
            if (ZERO.compareTo(calcavalPoQty) > 0) {
                originalShpgPlnMsg.avalPoQty.setValue(ZERO);
            } else {
                originalShpgPlnMsg.avalPoQty.setValue(calcavalPoQty);
            }
        }

        // START 2013/2/1 A.Suda [WDS Defect# 539]
        originalShpgPlnMsg.spTotDealFrtAmt.setValue(ZERO);
        originalShpgPlnMsg.spTotFuncFrtAmt.setValue(ZERO);
        // END 2013/2/1 A.Suda [WDS Defect# 539]

        if (newSetShpgPlnNum != null) {
            originalShpgPlnMsg.setShpgPlnNum.setValue(newSetShpgPlnNum);
        }

        // ***** update
        updateShpgPln(msgMap, originalShpgPlnMsg);
        // Add Shipping Plan Num
        this.shpgPlnNumList.add(originalShpgPlnMsg.shpgPlnNum.getValue());

        // ---------------------------------------------
        // Update PRC_DTL
        // ---------------------------------------------
        if (TRX_SRC_TP.WHOLE_SALES.equals(param1.trxSrcTpCd.getValue())) {
            prcDtlData = this.updatePrcDtlFromshpgPlnNum(msgMap, originalShpgPlnNum, multiply(allocatedQty, BigDecimal.valueOf(-1)));
        }

        // ---------------------------------------------
        // Update HLD
        // ---------------------------------------------
        HLDTMsgArray hldData = this.updateHldFromshpgPlnNum(msgMap, originalShpgPlnNum, trxHdrNum, trxLineNum, trxLineSubNum, multiply(allocatedQty, BigDecimal.valueOf(-1)), remakeFlg);

        // ---------------------------------------------
        // Insert SHPG_PLN
        // (Shipping Plan Status 'Hard Allocated' Data)
        // ---------------------------------------------

        haShpgPlnMsg.shpgPlnNum.setValue(getShipgPlnSQ());
        haShpgPlnMsg.shpgPlnDplyLineNum.setValue(getMaxDplyNum(trxHdrNum, trxLineNum, trxLineSubNum, trxSrcTpCd));
        haShpgPlnMsg.shpgStsCd.setValue(sts);
        haShpgPlnMsg.ordQty.setValue(allocatedQty);

        if (originalShpgPlnMsg.slsHldQty.getValue().compareTo(ZERO) == 0) {
            haShpgPlnMsg.slsHldQty.setValue(ZERO);
        } else {
            haShpgPlnMsg.slsHldQty.setValue(allocatedQty);
        }

        if (itemData != null) {
            // START UPDATE M.Fuji [#2745]
//            haShpgPlnMsg.crHldQty.setValue(ZERO);
            if (originalShpgPlnMsg.crHldQty.getValue().compareTo(ZERO) == 0) {
                haShpgPlnMsg.crHldQty.setValue(ZERO);
            } else {
                haShpgPlnMsg.crHldQty.setValue(allocatedQty);
            }
            // END UPDATE M.Fuji [#2745]
            haShpgPlnMsg.mdseCd.setValue(itemData.getMdseCd());
            haShpgPlnMsg.invtyLocCd.setValue(itemData.getInvtyLocCd());
            haShpgPlnMsg.reqShpgSvcLvlCd.setValue(itemData.getShpgSvcLvlCd());
            haShpgPlnMsg.origShpgSvcLvlCd.setValue(itemData.getShpgSvcLvlCd());
            // START UPDATE M.Fuji [#2745]
            // WDS (WS Bug Fix) Start
            //haShpgPlnMsg.crChkQty.setValue(allocatedQty);
            if (originalShpgPlnMsg.crChkQty.getValue().compareTo(ZERO) == 0) {
                haShpgPlnMsg.crChkQty.setValue(ZERO);
            } else {
                haShpgPlnMsg.crChkQty.setValue(allocatedQty);
            }
            // WDS (WS Bug Fix) End
            // END UPDATE S.M.Fuji [#2745]

        } else {

            if (originalShpgPlnMsg.crHldQty.getValue().compareTo(ZERO) == 0) {
                haShpgPlnMsg.crHldQty.setValue(ZERO);
            } else {
                haShpgPlnMsg.crHldQty.setValue(allocatedQty);
            }

            if (originalShpgPlnMsg.crChkQty.getValue().compareTo(ZERO) == 0) {
                haShpgPlnMsg.crChkQty.setValue(ZERO);
            } else {
                if (ZERO.compareTo(calcCrChkQty) == 0) {
                    haShpgPlnMsg.crChkQty.setValue(ZERO);
                } else {
                    haShpgPlnMsg.crChkQty.setValue(allocatedQty);
                }
            }

            if (originalShpgPlnMsg.avalSoQty.getValue().compareTo(ZERO) == 0) {
                haShpgPlnMsg.avalSoQty.setValue(ZERO);
            } else {
                if (ZERO.compareTo(calcavalSoQty) == 0) {
                    haShpgPlnMsg.avalSoQty.setValue(ZERO);
                } else {
                    haShpgPlnMsg.avalSoQty.setValue(allocatedQty);
                }
            }

            if (originalShpgPlnMsg.avalPoQty.getValue().compareTo(ZERO) == 0) {
                haShpgPlnMsg.avalPoQty.setValue(ZERO);
            } else {
                if (ZERO.compareTo(calcavalPoQty) == 0) {
                    haShpgPlnMsg.avalPoQty.setValue(ZERO);
                } else {
                    haShpgPlnMsg.avalPoQty.setValue(allocatedQty);
                }
            }
        }

        // Amount
        setTotAmt(haShpgPlnMsg, allocatedQty);

        // ***** insert
        insertShpgPln(msgMap, haShpgPlnMsg);

        if (this.originalShpgPlnNum == null) {
            this.originalShpgPlnNum = haShpgPlnMsg.shpgPlnNum.getValue();
        }

        shpgPlnNumHA = haShpgPlnMsg.shpgPlnNum.getValue();
        // Add Shipping Plan Num
        this.shpgPlnNumList.add(shpgPlnNumHA);

        // ---------------------------------------------
        // Insert PRC_DTL
        // ---------------------------------------------
        if (TRX_SRC_TP.WHOLE_SALES.equals(param1.trxSrcTpCd.getValue())) {
            this.insertPrcDtl(msgMap, prcDtlData, shpgPlnNumHA, allocatedQty);
        }

        // ---------------------------------------------
        // Insert HLD
        // ---------------------------------------------
        if (hldData.length() > 0) {
            this.insertHld(msgMap, hldData, shpgPlnNumHA, allocatedQty);
        }

        if (isError(msgMap)) {
            return null;
        }

        return shpgPlnNumHA;

    }

    private void exeCancelNLZC001001(S21ApiMessageMap msgMap, final String locStsCd, final String mdseCd, final String invtyLocCd, final String stkStsCd, final BigDecimal cancelQty, final ONBATCH_TYPE onBatchType) {

        final NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        final NLZC001001PMsg invtyAllocPMsg = new NLZC001001PMsg();
        setValue(invtyAllocPMsg.glblCmpyCd, param1.glblCmpyCd);
        setValue(invtyAllocPMsg.locStsCd,   locStsCd);
        setValue(invtyAllocPMsg.mdseCd,     mdseCd);
        setValue(invtyAllocPMsg.invtyLocCd, invtyLocCd);
        setValue(invtyAllocPMsg.xxProcTpCd, PROC_TP_CD_CANCEL);
        setValue(invtyAllocPMsg.stkStsCd,   stkStsCd);
        setValue(invtyAllocPMsg.xxRqstQty,  cancelQty);
        setValue(invtyAllocPMsg.xxAllocOpt, ALLOC_OPT_ALLOC);

        // --------------------------------------------------
        // call NLZC001001 : Inventory Allocation API
        // --------------------------------------------------
        callInvtyAllocAPI(invtyAllocPMsg, onBatchType);

        if (invtyAllocPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < invtyAllocPMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(invtyAllocPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }
    }
    
    private void exeNewNLZC00101(S21ApiMessageMap msgMap, AllocationItemData itemData, final ONBATCH_TYPE onBatchType) {

        final NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        final NLZC001001PMsg invtyAllocPMsg = new NLZC001001PMsg();
        setValue(invtyAllocPMsg.glblCmpyCd, param1.glblCmpyCd);
        setValue(invtyAllocPMsg.locStsCd,   param1.locStsCd);
        setValue(invtyAllocPMsg.mdseCd,     itemData.getMdseCd());
        setValue(invtyAllocPMsg.invtyLocCd, itemData.getInvtyLocCd());
        setValue(invtyAllocPMsg.xxProcTpCd, PROC_TP_CD_ALLOC);
        setValue(invtyAllocPMsg.stkStsCd,   param1.stkStsCd);
        setValue(invtyAllocPMsg.xxRqstQty,  itemData.getAvailableQty());
        setValue(invtyAllocPMsg.xxAllocOpt, ALLOC_OPT_ALLOC);

        // --------------------------------------------------
        // call NLZC001001 : Inventory Allocation API
        // --------------------------------------------------
        callInvtyAllocAPI(invtyAllocPMsg, onBatchType);

        if (invtyAllocPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < invtyAllocPMsg.xxMsgIdList.getValidCount(); i++) {
                
                final String xxMsgId = invtyAllocPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                
                if (isEquals(NLZM0010E, xxMsgId)) {
                    // Re calculate

                    itemData.setInvtyAvailableQty(invtyAllocPMsg.invtyAvalQty_PR.getValue());
                    BigDecimal eachQty               = itemData.getInEachQty();
                    BigDecimal invtyAvailableQty     = itemData.getInvtyAvailableQty();
                    BigDecimal calcInvtyAvailableQty = ZERO;

                    if (eachQty.compareTo(invtyAvailableQty) > 0) {
                        itemData.setTargetFlg(false);
                        continue;
                    }

                    BigDecimal remaindInvtyNum = ZERO;
                    remaindInvtyNum = remainder(invtyAvailableQty, eachQty);
                    calcInvtyAvailableQty = subtract(invtyAvailableQty, remaindInvtyNum);
                    itemData.setAvailableQty(calcInvtyAvailableQty);

                    this.exeNewNLZC00101(msgMap, itemData, onBatchType);
                    if (isError(msgMap)) {
                        return;

                    }
                } else {
                    // The value of the INVTY master is set.
                    itemData.setInvtyAllocAPIQty(invtyAllocPMsg.invtyAvalQty.getValue());
                    msgMap.addXxMsgId(xxMsgId);
                    return;
                }

            }
        } else {
            // The value of the INVTY master is set.
            itemData.setInvtyAllocAPIQty(invtyAllocPMsg.invtyAvalQty.getValue());
        }
    }

    private void executeNWZC188001(S21ApiMessageMap msgMap, final NWZC104001PMsg param) {

        final NWZC188001PMsg dspOdrStsUpdtPMsg = new NWZC188001PMsg();

        if (this.shpgPlnNumList.size() == 0) {
            return;
        }

        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, param.trxHdrNum.getValue());
        cpoTMsg = (CPOTMsg) EZDTBLAccessor.findByKey(cpoTMsg);

        if (null == cpoTMsg) {
            return;
        }

        setValue(dspOdrStsUpdtPMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(dspOdrStsUpdtPMsg.cpoOrdNum, param.trxHdrNum);
        for (int i = 0; i < this.shpgPlnNumList.size(); i++) {
            dspOdrStsUpdtPMsg.shpgPlnNumList.no(i).shpgPlnNum.setValue(shpgPlnNumList.get(i));
        }
        dspOdrStsUpdtPMsg.shpgPlnNumList.setValidCount(shpgPlnNumList.size());

        NWZC188001 dspOdrStsUpdtAPI = new NWZC188001();
        dspOdrStsUpdtAPI.execute(dspOdrStsUpdtPMsg, this.onBatchType);

        if (dspOdrStsUpdtPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < dspOdrStsUpdtPMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(dspOdrStsUpdtPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private List<String> getCtryCdList(final NWZC104001PMsg param1) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",   param1.glblCmpyCd.getValue());
        ssmParam.put("sellToCustCd", param1.sellToCustCd.getValue());
        ssmParam.put("billToCustCd", param1.billToCustCd.getValue());

        final List<String> ctryCdList = this.ssmClient.queryObjectList("getCtryCd", ssmParam);

        if (ctryCdList.isEmpty()) {
            return null;
        } else {
            return ctryCdList;
        }
    }

    @SuppressWarnings("unchecked")
    private List<HARD_ALLOCTMsg> getDeleteHardAlloc(final NWZC104001PMsg param1) {

        Map<String, Object> key = new HashMap<String, Object>();

        key.put("glblCmpyCd", param1.glblCmpyCd.getValue());
        key.put("trxHdrNum", param1.trxHdrNum.getValue());
        key.put("trxLineNum", param1.trxLineNum.getValue());
        key.put("trxLineSubNum", param1.trxLineSubNum.getValue());
        key.put("trxSrcTpCd", param1.trxSrcTpCd.getValue());
        List<String> shpgStsList = new ArrayList<String>();
        shpgStsList.add(SHPG_STS.VALIDATED);
        shpgStsList.add(SHPG_STS.CANCELLED);
        key.put("shpgStsList", shpgStsList);

        List<Map<String, Object>> notCancelShpgPlnList = (List) this.ssmClient.queryObjectList("queryCancelShpgPln", key);

        Map<String, Object> key2 = new HashMap<String, Object>();
        List<String> shpgPlnNumList = new ArrayList<String>();
        String allShpgPlnNumFlg = Y;
        for (Map<String, Object> shpgPln : notCancelShpgPlnList) {

            allShpgPlnNumFlg = N;
            shpgPlnNumList.add((String) shpgPln.get("SHPG_PLN_NUM"));

        }

        key2.put("glblCmpyCd", param1.glblCmpyCd.getValue());
        key2.put("trxHdrNum", param1.trxHdrNum.getValue());
        key2.put("trxLineNum", param1.trxLineNum.getValue());
        key2.put("trxLineSubNum", param1.trxLineSubNum.getValue());
        key2.put("trxSrcTpCd", param1.trxSrcTpCd.getValue());
        key2.put("allShpgPlnNumFlg", allShpgPlnNumFlg);
        key2.put("shpgPlnNumList", shpgPlnNumList);
        if (hasValue(param1.softAllocPk)) {
            key2.put("softAllocPk", param1.softAllocPk.getValue());
        }

        List<Map<String, Object>> cancelHardAllocList = this.ssmClient.queryObjectList("queryCancelHardAlloc", key2);

        if (!isEmpty(cancelHardAllocList)) {

            return null;
        }

        List<HARD_ALLOCTMsg> hardAllocList = new ArrayList<HARD_ALLOCTMsg>();

        for (Map<String, Object> hardAlloc : cancelHardAllocList) {

            HARD_ALLOCTMsg hardAllocMsg = new HARD_ALLOCTMsg();
            hardAllocMsg.glblCmpyCd.setValue(param1.glblCmpyCd.getValue());
            hardAllocMsg.hardAllocPk.setValue((BigDecimal) hardAlloc.get("HARD_ALLOC_PK"));

            HARD_ALLOCTMsg hardAllocRcd = searchHardAllocFindByKey(hardAllocMsg);

            if (hardAllocRcd == null) {
                continue;

            } else {
                hardAllocList.add(hardAllocRcd);

            }

        }

        return hardAllocList;

    }

//    /**
//     * <pre>
//     * Check Soft Alloction Time Frame.
//     * </pre>
//     */
//    @SuppressWarnings("unchecked")
//    private String getFutrueRsdDt(final SHPG_PLNTMsg shpgPlnTMsg) {
//
//        // 2010/07/02 Defect 7493 --- add start
//        // If Time Frame are all dates of the future, RSD_DT is
//        // updated.
//
//        boolean rsdDtUpdFlg = true;
//        if (isEquals(SHPG_STS.VALIDATED, shpgPlnTMsg.shpgStsCd.getValue()) && shpgPlnTMsg.softAllocQty.getValue().compareTo(ZERO) > 0 && shpgPlnTMsg.softAllocQty.getValue().compareTo(shpgPlnTMsg.ordQty.getValue()) == 0) {
//
//            int nextMonth = getNextYyyyMm();
//
//            List<Map<String, Object>> tmList = searchSoftAllocationTM(shpgPlnTMsg);
//            if (!isEmpty(tmList)) {
//                // RSD_DT is not updated.
//                return null;
//            }
//
//            for (Map<String, Object> tmMap : tmList) {
//
//                String tm = (String) tmMap.get("TM_FRAME_FROM_DT");
//
//                if (tm != null && tm.length() > 5) {
//
//                    String subTm = tm.substring(0, 6);
//                    int intTm = Integer.parseInt(subTm);
//                    if (nextMonth > intTm) {
//                        // RSD_DT is not updated.
//                        rsdDtUpdFlg = false;
//                        break;
//                    }
//                } else {
//                    // RSD_DT is not updated.
//                    rsdDtUpdFlg = false;
//                    break;
//                }
//            }
//
//            if (rsdDtUpdFlg) {
//                return (String) tmList.get(0).get("TM_FRAME_FROM_DT");
//            }
//        }
//
//        return null;
//    }

    // START DELETE M.Fuji [#2745]
//    private HLD_RSNTMsg getHldRsn(final String hldRsnCd) {
//
//        return NWXHldRsnTMsgThreadLocalCache.getInstance().get(this.glblCmpyCd, hldRsnCd);
//    }
    // END DELETE M.Fuji [#2745]

    private List<AllocationItemData> getItemFlipList(final S21ApiMessageMap msgMap) {

        final NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        final List<AllocationItemData> itemFlipList = this.getMdse(param1, this.getCtryCdList(param1));
        if (itemFlipList.isEmpty()) {
            msgMap.addXxMsgId(NWZM0165E);
        }
        return itemFlipList;
    }

    private String getMaxDplyNum(final String trxHdrNum, final String trxLineNum, final String trxLineSubNum, final String trxSrcTpCd) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("trxHdrNum", trxHdrNum);
        param.put("trxLineNum", trxLineNum);
        param.put("trxLineSubNum", trxLineSubNum);
        param.put("trxSrcTpCd", trxSrcTpCd);

        String strMaxDplyNum = (String) ssmClient.queryObject("getShpgPlnLineNumber", param);

        if (strMaxDplyNum == null) {
            strMaxDplyNum = "1";
        }

        return ZYPCommonFunc.leftPad(String.valueOf(Integer.parseInt(strMaxDplyNum) + 1), SHPG_PLN_DPLY_MAX_NUM, "0");

    }

    @SuppressWarnings("unchecked")
    private List<AllocationItemData> getMdse(final NWZC104001PMsg param1, final List<String> ctryCdList) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param1.glblCmpyCd.getValue());
        ssmParam.put("ctryCdList", ctryCdList);
        ssmParam.put("rgtnStsCd",  RGTN_STS.READY_FOR_ORDER_TAKING);

        String mdseCd;

        if (hasValue(param1.mdseCd_SA)) {
            mdseCd = param1.mdseCd_SA.getValue();
        } else {
            mdseCd = param1.mdseCd.getValue();
        }

        // 2019/09/06 S21_NA#53087 Mod Start
        //if (mdseCd.length() > ORD_TAKE_MDSE_CD_DIGIT) {
        if (mdseCd.length() > ORD_TAKE_MDSE_CD_DIGIT && !ZYPConstant.FLG_ON_Y.equals(param1.xxRsltFlg_M.getValue())) {
        // 2019/09/06 S21_NA#53087 Mod End
            ssmParam.put("mdseCd", subByteString(mdseCd, 0, ORD_TAKE_MDSE_CD_DIGIT) + "%");
        } else {
            ssmParam.put("mdseCd", mdseCd + "%");
        }

        if (hasValue(param1.invtyLocCd_SA)) {
            ssmParam.put("invtyLocCd", param1.invtyLocCd_SA.getValue());
        } else {
            ssmParam.put("invtyLocCd", param1.invtyLocCd.getValue());
        }
        ssmParam.put("sellHldFlg", N);
        // 2017/03/13 S21_NA#16987 Add Start
        ssmParam.put("custOrdEnblFlg", Y);
        // 2017/03/13 S21_NA#16987 Add End
        // Mod Start S21_NA#5872
        ssmParam.put("hardAllocTpCd", param1.hardAllocTpCd.getValue());
        // Mod End S21_NA#5872

        return ssmClient.queryObjectList("getMDSE", ssmParam, new GetMdseList());
    }

    private MDSETMsg getMdse(final String mdseCd) {

        return NWXMdseTMsgThreadLocalCache.getInstance().get(this.glblCmpyCd, mdseCd);
    }
    
    private BigDecimal getSetPossibleQty(NWZC104001PMsg param1) {

        this.compQtyList = new HashMap<String, Object>();

        final Map<String, Object> compQtyMap = NWXC002007BackOrder.setPossibleQty(
                                                    param1.glblCmpyCd.getValue(), 
                                                    param1.trxSrcTpCd.getValue(), 
                                                    param1.trxHdrNum.getValue(), 
                                                    param1.trxLineNum.getValue(), 
                                                    this.setShpgPlnNum
                                                );

        if (compQtyMap == null || compQtyMap.isEmpty()) {
            return ZERO;

        } else {
            this.compQtyList = compQtyMap;

            BigDecimal setPossibleQty = (BigDecimal) compQtyMap.get(NWXC002007BackOrder.SET_AVAL_QTY);
            if (setPossibleQty == null) {
                return ZERO;
            }
            return setPossibleQty;
        }
    }

    // SHPG_PLN information on each ComponentItem.
    @SuppressWarnings("unchecked")
    private List<ShpgPlnCompBean> getShpgPlnEachComp(NWZC104001PMsg param1) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",    param1.glblCmpyCd.getValue());
        ssmParam.put("trxHdrNum",     param1.trxHdrNum.getValue());
        ssmParam.put("trxLineNum",    param1.trxLineNum.getValue());
        ssmParam.put("trxSrcTpCd",    param1.trxSrcTpCd.getValue());
        ssmParam.put("setShpgPlnNum", this.setShpgPlnNum);
        ssmParam.put("shpgStsCdHA",   SHPG_STS.HARD_ALLOCATED);
        ssmParam.put("shpgStsCdSO",   SHPG_STS.S_OR_O_CANCELLED);
        ssmParam.put("shpgStsCdPO",   SHPG_STS.P_OR_O_CANCELLED);
        ssmParam.put("trxLineNumSet", TRX_LINE_SUB_NUM_SET);
        ssmParam.put("shpgStsCdVA",   SHPG_STS.VALIDATED);
        ssmParam.put("on",            Y);
        ssmParam.put("off",           N);

        return this.ssmClient.queryObjectList("queryShpgPlnEachComp", ssmParam);
    }

    private String getSts(final ShpgPlnCompBean shpgPlnComp) {

        String sts = "";

        String vendorDropFlg = shpgPlnComp.getPoReqFlg();

        MDSETMsg mdseMsg = getMdse(shpgPlnComp.getMdseCd());

        if (mdseMsg == null) {
            return null;
        }

        if (isEquals(Y, vendorDropFlg)) {
            sts = SHPG_STS.VALIDATED;

        } else if (isEquals(N, mdseMsg.invtyCtrlFlg.getValue())) {
            sts = SHPG_STS.VALIDATED;

        } else if (!isEquals(SHPG_STS.HARD_ALLOCATED, shpgPlnComp.getShpgStsCd()) || !isEquals(SHPG_STS.VALIDATED, shpgPlnComp.getShpgStsCd())) {
            sts = shpgPlnComp.getShpgStsCd();

        } else {

            sts = SHPG_STS.HARD_ALLOCATED;
        }

        return sts;

    }

    private void hardAllocDivideProcess(S21ApiMessageMap msgMap, final String origShpgPlnNum, final String newShpgPlnNum, final BigDecimal divideQty, final BigDecimal newQty, final SOFT_ALLOCTMsg softAllocMsg) {

        HARD_ALLOCTMsgArray hardAllocMsgData = searchHardAlloc(origShpgPlnNum);
        if (hardAllocMsgData.getValidCount() > 0) {

            // Divide
            HARD_ALLOCTMsg originalHardAllocMsg = hardAllocMsgData.no(0);
            HARD_ALLOCTMsg newHardAllocMsg = new HARD_ALLOCTMsg();
            EZDMsg.copy(originalHardAllocMsg, null, newHardAllocMsg, null);

            // update
            originalHardAllocMsg.hardAllocQty.setValue(divideQty);
            originalHardAllocMsg.shpgPlnNum.setValue(origShpgPlnNum);
            if (softAllocMsg != null && isEquals(softAllocMsg.shpgPlnNum.getValue(), origShpgPlnNum)) {
                originalHardAllocMsg.softAllocPk.setValue(softAllocMsg.softAllocPk.getValue());
            }
            // ***** update
            updateHardAlloc(msgMap, originalHardAllocMsg);

            // insert
            newHardAllocMsg.hardAllocQty.setValue(newQty);
            newHardAllocMsg.shpgPlnNum.setValue(newShpgPlnNum);
            newHardAllocMsg.hardAllocPk.setValue(getHardAllocSQ());
            if (softAllocMsg != null && isEquals(softAllocMsg.shpgPlnNum.getValue(), newShpgPlnNum)) {
                newHardAllocMsg.softAllocPk.setValue(softAllocMsg.softAllocPk.getValue());
            }
            // ****** insert
            insertHardAlloc(msgMap, newHardAllocMsg);
        }

    }

    private void insertHld(S21ApiMessageMap msgMap, HLDTMsgArray hldTMsgArray, final String shpgPlnNum, final BigDecimal qty) {

        for (int i = 0; i < hldTMsgArray.length(); i++) {

            final HLDTMsg hldTMsg = hldTMsgArray.no(i);
            setValue(hldTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(hldTMsg.hldPk,      getHldSQ());
            setValue(hldTMsg.shpgPlnNum, shpgPlnNum);
            setValue(hldTMsg.hldQty,     qty);

            // ***** insert
            insertHld(msgMap, hldTMsgArray.no(i));

            if (isError(msgMap)) {
                return;
            }
        }
    }

    private void insertPrcDtl(S21ApiMessageMap msgMap, PRC_DTLTMsgArray prcDtlTMsgArray, final String shpgPlnNum, final BigDecimal updQty) {

        for (int i = 0; i < prcDtlTMsgArray.length(); i++) {
            
            final PRC_DTLTMsg prcDtlTMsg = prcDtlTMsgArray.no(i);
            
            setValue(prcDtlTMsg.glblCmpyCd,  this.glblCmpyCd);
            setValue(prcDtlTMsg.prcDtlPk,    getPrcDtlSQ());
            setValue(prcDtlTMsg.shpgPlnNum,  shpgPlnNum);
            setValue(prcDtlTMsg.shipUnitQty, updQty);
            setValue(prcDtlTMsg.dealNetAmt,  multiply(prcDtlTMsg.dealLastNetUnitPrcAmt.getValue(), updQty));
            setValue(prcDtlTMsg.funcNetAmt,  multiply(prcDtlTMsg.funcLastNetUnitPrcAmt.getValue(), updQty));

            if (hasValue(prcDtlTMsg.dealPerUnitFixAmt)) {
                setValue(prcDtlTMsg.dealDiscAmt, multiply(prcDtlTMsg.dealPerUnitFixAmt.getValue(), updQty));
            }
            
            if (hasValue(prcDtlTMsg.funcPerUnitFixAmt)) {
                setValue(prcDtlTMsg.funcDiscAmt, multiply(prcDtlTMsg.funcPerUnitFixAmt.getValue(), updQty));
            }

            // ***** insert
            insertPrcDtl(msgMap, prcDtlTMsg);

            if (isError(msgMap)) {
                return;
            }
        }
    }

    private void invtyCancelProcess(S21ApiMessageMap msgMap, final List<HARD_ALLOCTMsg> cancelHardAllocList, final ONBATCH_TYPE onBatchType) {

        boolean firstFlg = true;
        String curentMdseCd = "";
        String curentInvtyLocCd = "";
        String curentShpgSvcLvlCd = "";
        String curentStkStsCd = "";
        String curentLocStsCd = "";
        BigDecimal cancelQtyTotal = ZERO;

        for (HARD_ALLOCTMsg cancelHardAllocMsg : cancelHardAllocList) {

            if (firstFlg) {

                curentMdseCd = cancelHardAllocMsg.mdseCd.getValue();
                curentInvtyLocCd = cancelHardAllocMsg.invtyLocCd.getValue();
                curentShpgSvcLvlCd = cancelHardAllocMsg.shpgSvcLvlCd.getValue();
                curentStkStsCd = cancelHardAllocMsg.stkStsCd.getValue();
                curentLocStsCd = cancelHardAllocMsg.locStsCd.getValue();
                cancelQtyTotal = add(cancelQtyTotal, cancelHardAllocMsg.hardAllocQty.getValue());
                firstFlg = false;

            } else {

                if (!curentMdseCd.equals(cancelHardAllocMsg.mdseCd.getValue()) || !curentInvtyLocCd.equals(cancelHardAllocMsg.invtyLocCd.getValue()) || !curentShpgSvcLvlCd.equals(cancelHardAllocMsg.shpgSvcLvlCd.getValue())) {

                    exeCancelNLZC001001(msgMap, curentLocStsCd, curentMdseCd, curentInvtyLocCd, curentStkStsCd, cancelQtyTotal, onBatchType);
                    if (isError(msgMap)) {
                        return;
                    }

                    // initialize.
                    curentMdseCd = cancelHardAllocMsg.mdseCd.getValue();
                    curentInvtyLocCd = cancelHardAllocMsg.invtyLocCd.getValue();
                    curentShpgSvcLvlCd = cancelHardAllocMsg.shpgSvcLvlCd.getValue();
                    curentStkStsCd = cancelHardAllocMsg.stkStsCd.getValue();
                    curentLocStsCd = cancelHardAllocMsg.locStsCd.getValue();
                    cancelQtyTotal = ZERO;
                    cancelQtyTotal = add(cancelQtyTotal, cancelHardAllocMsg.hardAllocQty.getValue());

                } else {
                    cancelQtyTotal = add(cancelQtyTotal, cancelHardAllocMsg.hardAllocQty.getValue());

                }

            }

            // Delete HARD_ALLOC
            deleteHardAlloc(cancelHardAllocMsg);

        }

        if (ZERO.compareTo(cancelQtyTotal) < 0) {
            exeCancelNLZC001001(msgMap, curentLocStsCd, curentMdseCd, curentInvtyLocCd, curentStkStsCd, cancelQtyTotal, onBatchType);
        }
    }

    private boolean invtyUpdateProcess(S21ApiMessageMap msgMap, List<AllocationItemData> itemDataList, final ONBATCH_TYPE onBatchType) {

        final NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        // Calculation Allocation Avalable QTY
        final BigDecimal remaindQty = this.calcAvailableQty(msgMap, itemDataList);
        final BigDecimal rqstQty    = param1.xxRqstQty.getValue();

        if (remaindQty.compareTo(rqstQty) == 0) {
            return false;

        }

        BigDecimal totalAllocQty = ZERO;
        
        for (AllocationItemData itemData : itemDataList) {

            if (itemData.isTargetFlg()) {
                this.exeNewNLZC00101(msgMap, itemData, onBatchType);
                if (isError(msgMap)) {
                    return false;

                }
                totalAllocQty = add(totalAllocQty, itemData.getAvailableQty());
            }
        }

        return true;
    }

    private SOFT_ALLOCTMsg lockSoftAlloc(BigDecimal softAllocPK) {

        final SOFT_ALLOCTMsg softAllocTMsg = new SOFT_ALLOCTMsg();
        setValue(softAllocTMsg.glblCmpyCd,  this.glblCmpyCd);
        setValue(softAllocTMsg.softAllocPk, softAllocPK);

        return (SOFT_ALLOCTMsg) findByKeyForUpdateAPI(softAllocTMsg);
    }

    private SOFT_ALLOCTMsgArray lockSoftAllocArray(final String shpgPlnNum) {

        final SOFT_ALLOCTMsg softAllocTMsg = new SOFT_ALLOCTMsg();
        softAllocTMsg.setSQLID(SqlId.SQL_ID_SOFT_ALLOC_003.getKey());
        softAllocTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        softAllocTMsg.setConditionValue("shpgPlnNum01", shpgPlnNum);

        return (SOFT_ALLOCTMsgArray) findByConditionForUpdate(softAllocTMsg);
    }

    private void logicalRemoveHardAlloc(final BigDecimal hardAllocPK) {

        final HARD_ALLOCTMsg tMsg = new HARD_ALLOCTMsg();
        setValue(tMsg.glblCmpyCd,  this.glblCmpyCd);
        setValue(tMsg.hardAllocPk, hardAllocPK);

        logicalRemove(tMsg);
    }

    private void logicalRemoveHld(BigDecimal hldPk) {

        final HLDTMsg tMsg = new HLDTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.hldPk,      hldPk);

        logicalRemove(tMsg);
    }

    private void logicalRemovePrcDtl(final BigDecimal prcDtlPk) {

        final PRC_DTLTMsg tMsg = new PRC_DTLTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.prcDtlPk,   prcDtlPk);

        logicalRemove(tMsg);
    }

    private void logicalRemoveSoftAlloc(BigDecimal softAllocPk) {

        final SOFT_ALLOCTMsg tMsg = new SOFT_ALLOCTMsg();
        setValue(tMsg.glblCmpyCd,  this.glblCmpyCd);
        setValue(tMsg.softAllocPk, softAllocPk);

        logicalRemove(tMsg);
    }

    private HARD_ALLOCTMsgArray searchHardAlloc(final String shpgPlnNum) {

        final HARD_ALLOCTMsg hardAllocTMsg = new HARD_ALLOCTMsg();
        hardAllocTMsg.setSQLID(SqlId.SQL_ID_HARD_ALLOC_009.getKey());
        hardAllocTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        hardAllocTMsg.setConditionValue("shpgPlnNum01", shpgPlnNum);

        return hardAllocFindByCondition(hardAllocTMsg);
    }

    private HLDTMsgArray searchHldForUpdate(final String shpgPlnNum, final String cpoOrdNum, final String cpoLineMum, final String cpoLineSubMum) {

        final HLDTMsg hldRsnTMsg = new HLDTMsg();
        hldRsnTMsg.setSQLID(SqlId.SQL_ID_HLD_017.getKey());
        hldRsnTMsg.setConditionValue("glblCmpyCd01",       this.glblCmpyCd);
        hldRsnTMsg.setConditionValue("shpgPlnNum01",       shpgPlnNum);
        hldRsnTMsg.setConditionValue("cpoOrdNum01",        cpoOrdNum);
        hldRsnTMsg.setConditionValue("cpoDtlLineNum01",    cpoLineMum);
        hldRsnTMsg.setConditionValue("cpoDtlLineSubNum01", cpoLineSubMum);

        return (HLDTMsgArray) findByConditionForUpdate(hldRsnTMsg);
    }

    private PRC_DTLTMsgArray searchPrcDtlForUpdate(final String shpgPlnNum) {

        final PRC_DTLTMsg prcDtlTMsg = new PRC_DTLTMsg();
        prcDtlTMsg.setSQLID(SqlId.SQL_ID_PRC_DTL_001.getKey());
        prcDtlTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prcDtlTMsg.setConditionValue("shpgPlnNum01", shpgPlnNum);

        return (PRC_DTLTMsgArray) findByConditionForUpdate(prcDtlTMsg);
    }

    private SOFT_ALLOCTMsgArray searchSetSoftAllocFindByConditionForUpdate(final String glblCmpyCd, final String shpgPlnNum, final BigDecimal setSoftAllocPk) {

        final SOFT_ALLOCTMsg softAllocTMsg = new SOFT_ALLOCTMsg();
        softAllocTMsg.setSQLID(SqlId.SQL_ID_SOFT_ALLOC_006.getKey());
        softAllocTMsg.setConditionValue("glblCmpyCd01",     glblCmpyCd);
        softAllocTMsg.setConditionValue("shpgPlnNum01",     shpgPlnNum);
        softAllocTMsg.setConditionValue("setSoftAllocPk01", setSoftAllocPk);

        return (SOFT_ALLOCTMsgArray) findByConditionForUpdate(softAllocTMsg);
    }

    private SHPG_PLNTMsg searchSHPGPLNshipStsForValidated(S21ApiMessageMap msgMap, final NWZC104001PMsg param1) {

        final SHPG_PLNTMsgArray shpgPlnTMsgArray = searchSHPGPLNshipStsForValidated(param1);

        if (shpgPlnTMsgArray.length() == 0) {
            msgMap.addXxMsgId(NWZM0075E);
            return null;

        } else if (shpgPlnTMsgArray.length() == 1) {
            
            final SHPG_PLNTMsg shpgPlnTMsg = (SHPG_PLNTMsg) shpgPlnTMsgArray.get(0);

            if (isErrStatus(shpgPlnTMsg)) {
                msgMap.addXxMsgId(NWZM0083E);
                return null;
            }

            return shpgPlnTMsg;

        } else {
            msgMap.addXxMsgId(NWZM0076E);
            return null;
        }
    }

//    /**
//     * <pre>
//     * Search Soft Alloction Time Frame.
//     * </pre>
//     */
//    @SuppressWarnings("unchecked")
//    private List<Map<String, Object>> searchSoftAllocationTM(final SHPG_PLNTMsg shpgPlnTMsg) {
//
//        final Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd",    shpgPlnTMsg.glblCmpyCd.getValue());
//        ssmParam.put("shpgPlnNum",    shpgPlnTMsg.shpgPlnNum.getValue());
//        ssmParam.put("trxSrcTpCd",    shpgPlnTMsg.trxSrcTpCd.getValue());
//        ssmParam.put("trxHdrNum",     shpgPlnTMsg.trxHdrNum.getValue());
//        ssmParam.put("trxLineNum",    shpgPlnTMsg.trxLineNum.getValue());
//        ssmParam.put("trxLineSubNum", shpgPlnTMsg.trxLineSubNum.getValue());
//
//        return (List<Map<String, Object>>) this.ssmClient.queryObjectList("getSoftAllocationTM", ssmParam);
//    }

    private boolean setCompDivideProcess(S21ApiMessageMap msgMap, final AllocationItemData itemData, final String shpgPlnNumVA, SOFT_ALLOCTMsg softAllocMsg) {

        NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();
        String divideSetShpgPlnNum;
        BigDecimal divideSetSoftAllocPk = null;

        // ---------------------------------------------
        // The quantity to divide as a set is calculated.
        // ---------------------------------------------
        BigDecimal setPossibleQty = getSetPossibleQty(param1);

        if (ZERO.compareTo(setPossibleQty) == 0) {
            return true;
        }

        // ---------------------------------------------
        // SHPG_PLN information on each ComponentItem.
        // ---------------------------------------------
        List<ShpgPlnCompBean> shpgPlnEachCompList = getShpgPlnEachComp(param1);

        if (!isEmpty(shpgPlnEachCompList)) {
            return true;
        }

        // ------------------------------------
        // SetItem Process
        // - Transaction Data divide
        // - 'Status' Update
        // ------------------------------------

        SHPG_PLNTMsg setShpgPlnMsg = new SHPG_PLNTMsg();
        setShpgPlnMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        setShpgPlnMsg.shpgPlnNum.setValue(this.setShpgPlnNum);
        SHPG_PLNTMsg origSetShpgPlnMsg = shpgPlnFindByKeyForUpdate(setShpgPlnMsg);

        if (origSetShpgPlnMsg == null || !isEquals(SHPG_STS.VALIDATED, origSetShpgPlnMsg.shpgStsCd.getValue())) {
            msgMap.addXxMsgId(NWZM0075E);
            return false;
        }

        if (setPossibleQty.compareTo(origSetShpgPlnMsg.ordQty.getValue()) < 0) {
            // ---------------------------
            // setPossibleQty < OrdQty
            // --> Divide
            // ---------------------------

            divideSetShpgPlnNum = divideProcess(msgMap, null, origSetShpgPlnMsg, setPossibleQty, null, SHPG_STS.HARD_ALLOCATED, true);

            if (isError(msgMap)) {
                return false;
            }

            // ---------------------------------------------
            // Update SOFT_ALLOC
            // ---------------------------------------------
            divideSetSoftAllocPk = setSoftAllocDivideProcess(msgMap, setPossibleQty, divideSetShpgPlnNum, origSetShpgPlnMsg.shpgPlnNum.getValue(), softAllocMsg);

            if (isError(msgMap)) {
                return false;
            }

            // 2010/07/02 Defect 7493 --- add
            // RsdDt
         //   String futrueTm = getFutrueRsdDt(origSetShpgPlnMsg);
          //  if (hasValue(futrueTm)) {
          //      setValue(origSetShpgPlnMsg.rsdDt, futrueTm);
          //      // ***** update
          //      updateShpgPln(msgMap, origSetShpgPlnMsg);
          //  }

        } else {
            // ---------------------------
            // setPossibleQty >= OrdQty
            // --> Update
            // ---------------------------
            origSetShpgPlnMsg.shpgStsCd.setValue(SHPG_STS.HARD_ALLOCATED);
            // origSetShpgPlnMsg.shpgPlnDplyLineNum.setValue(getMaxDplyNum(origSetShpgPlnMsg.glblCmpyCd.getValue(),
            // origSetShpgPlnMsg.trxHdrNum.getValue(),
            // origSetShpgPlnMsg.trxLineNum.getValue(),
            // origSetShpgPlnMsg.trxLineSubNum.getValue(),
            // origSetShpgPlnMsg.trxSrcTpCd.getValue()));
            // ---------------------------------------------
            // Update SHPG_PLN
            // ---------------------------------------------
            updateShpgPln(msgMap, origSetShpgPlnMsg);
            // Add Shipping Plan Num
            this.shpgPlnNumList.add(origSetShpgPlnMsg.shpgPlnNum.getValue());

            return true;
        }

        // ------------------------------------
        // ComponentItem Process
        // - Transaction Data divide
        // - 'setShpgPlnNum' Update
        // ------------------------------------
        String lineSubNum = "";
        BigDecimal eachCompTotalQty = ZERO;
        List<String> validationExeList = new ArrayList<String>();

        for (ShpgPlnCompBean shpgPlnComp : shpgPlnEachCompList) {

            // get Component SHPG_PLN
            SHPG_PLNTMsg compShpgPlnMsg = new SHPG_PLNTMsg();
            compShpgPlnMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            compShpgPlnMsg.shpgPlnNum.setValue(shpgPlnComp.getShpgPlnNum());
            SHPG_PLNTMsg origCompShpgPlnMsg = shpgPlnFindByKeyForUpdate(compShpgPlnMsg);

            if (!isEquals(lineSubNum, shpgPlnComp.getTrxLineSubNum())) {

                // ---------------------------------------------
                // eachCompTotalQty = Amount of each component that
                // can be divided.
                // ---------------------------------------------
                lineSubNum = shpgPlnComp.getTrxLineSubNum();

                BigDecimal eachCompQty = (BigDecimal) this.compQtyList.get(lineSubNum);
                if (eachCompQty == null) {
                    eachCompQty = ZERO;
                }
                eachCompTotalQty = multiply(eachCompQty, setPossibleQty);
            }

            if (ZERO.compareTo(eachCompTotalQty) == 0) {
                continue;
            }

            if (eachCompTotalQty.compareTo(shpgPlnComp.getOrdQty()) < 0) {
                // ---------------------------
                // eachCompTotalQty < OrdQty
                // --> Divide
                // ---------------------------

                BigDecimal divideCompQty = subtract(shpgPlnComp.getOrdQty(), eachCompTotalQty);
                boolean remakeFlg = isEquals(this.originalShpgPlnNum, shpgPlnComp.getShpgPlnNum());
                BigDecimal updOrdQty = subtract(origCompShpgPlnMsg.ordQty.getValue(), divideCompQty);
                String sts = getSts(shpgPlnComp);

                if (sts == null) {
                    msgMap.addXxMsgId(NWZM0364E);
                    return false;
                }

                String divideCompShpgPlnNum = divideProcess(msgMap, null, origCompShpgPlnMsg, divideCompQty, divideSetShpgPlnNum, sts, !remakeFlg);

                if (isError(msgMap)) {
                    return false;
                }

                // ---------------------------------------------
                // Update SOFT_ALLOC
                // ---------------------------------------------
                SOFT_ALLOCTMsg divideSoftAllocMsg = softAllocDivideProcess(msgMap, divideCompQty, divideCompShpgPlnNum, origCompShpgPlnMsg.shpgPlnNum.getValue(), divideSetSoftAllocPk);

                if (isEquals(this.originalShpgPlnNum, shpgPlnComp.getShpgPlnNum())) {
                    // If it is a record allocated of this time, it
                    // registers in HARD_ALLOC and Validation Process
                    // Manager API is executed.

                    if (isEquals(SHPG_STS.HARD_ALLOCATED, shpgPlnComp.getShpgStsCd())) {

                        // ---------------------------------------------
                        // Insert HARD_ALLOC
                        // ---------------------------------------------
                        // originalShpgPlnNum
                        insertHardAlloc(msgMap, shpgPlnComp.getShpgPlnNum(), eachCompTotalQty, itemData, softAllocMsg);

                        // divideCompShpgPlnNum
                        insertHardAlloc(msgMap, divideCompShpgPlnNum, divideCompQty, itemData, divideSoftAllocMsg);

                        if (isEquals(TRX_SRC_TP.WHOLE_SALES, param1.trxSrcTpCd.getValue())) {
                            validationExeList.add(shpgPlnComp.getShpgPlnNum());
                            validationExeList.add(divideCompShpgPlnNum);
                        }
                    }
                } else {
                    // not VendorDrop, not Intangible
                    if (!isEquals(SHPG_STS.VALIDATED, shpgPlnComp.getShpgStsCd())) {

                        // ---------------------------------------------
                        // Insert HARD_ALLOC
                        // ---------------------------------------------
                        hardAllocDivideProcess(msgMap, origCompShpgPlnMsg.shpgPlnNum.getValue(), divideCompShpgPlnNum, updOrdQty, divideCompQty, divideSoftAllocMsg);
                    }
                }

                eachCompTotalQty = ZERO;

            } else {
                // ---------------------------
                // eachCompTotalQty >= OrdQty
                // --> Update
                // --------------------------

                // ---------------------------------------------
                // Update SHPG_PLN
                // ---------------------------------------------
                origCompShpgPlnMsg.setShpgPlnNum.setValue(divideSetShpgPlnNum);
                updateShpgPln(msgMap, origCompShpgPlnMsg);

                // ---------------------------------------------
                // Update SOFT_ALLOC
                // ---------------------------------------------
                updSoftAllocPk(msgMap, origCompShpgPlnMsg.shpgPlnNum.getValue(), divideSetSoftAllocPk);

                if (isEquals(this.originalShpgPlnNum, shpgPlnComp.getShpgPlnNum())) {
                    // If it is a record allocated of this time, it
                    // registers in HARD_ALLOC and Validation Process
                    // Manager API is executed.

                    if (isEquals(SHPG_STS.HARD_ALLOCATED, shpgPlnComp.getShpgStsCd())) {

                        // ---------------------------------------------
                        // Insert HARD_ALLOC
                        // ---------------------------------------------
                        // originalShpgPlnNum
                        insertHardAlloc(msgMap, shpgPlnComp.getShpgPlnNum(), origCompShpgPlnMsg.ordQty.getValue(), itemData, softAllocMsg);

                        if (isEquals(TRX_SRC_TP.WHOLE_SALES, param1.trxSrcTpCd.getValue())) {
                            validationExeList.add(shpgPlnComp.getShpgPlnNum());
                        }
                    }
                }

                if (eachCompTotalQty.compareTo(shpgPlnComp.getOrdQty()) == 0) {
                    eachCompTotalQty = ZERO;
                } else {
                    eachCompTotalQty = subtract(eachCompTotalQty, shpgPlnComp.getOrdQty());
                }
            }

            if (isError(msgMap)) {
                return false;
            }
        }

        for (String ShpgPlnNum : validationExeList) {

            // Execute Validation Process Manager API
            callValidProcMgrAPI(msgMap, ShpgPlnNum, itemData);

        }

        return false;
    }

    private BigDecimal setSoftAllocDivideProcess(S21ApiMessageMap msgMap, final BigDecimal divideQty, final String shpgPlnNumHA, final String shpgPlnNumVA, SOFT_ALLOCTMsg softAllocMsg) {

        NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        BigDecimal calcQty = ZERO;
        calcQty = divideQty;
        int softAllocSize = 0;
        BigDecimal divideSetSoftAllocPk = null;
        SOFT_ALLOCTMsgArray softAllocMsgArray = new SOFT_ALLOCTMsgArray();

        if (softAllocMsg != null && hasValue(softAllocMsg.setSoftAllocPk)) {
            softAllocMsgArray = searchSetSoftAllocFindByConditionForUpdate(param1.glblCmpyCd.getValue(), shpgPlnNumVA, softAllocMsg.setSoftAllocPk.getValue());
            softAllocSize = softAllocMsgArray.getValidCount();
        }

        if (softAllocSize == 0) {
            softAllocMsgArray = this.lockSoftAllocArray(shpgPlnNumVA);
            softAllocSize = softAllocMsgArray.getValidCount();
        }

        for (int i = 0; i < softAllocSize; i++) {

            if (ZERO.compareTo(calcQty) == 0) {
                break;
            }

            if (calcQty.compareTo(softAllocMsgArray.no(i).softAllocQty.getValue()) == 0) {
                // Update
                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                updsoftAllocMsg.shpgPlnNum.setValue(shpgPlnNumHA);

                // ***** update
                updateSoftAlloc(msgMap, updsoftAllocMsg);
                break;

            } else if (calcQty.compareTo(softAllocMsgArray.no(i).softAllocQty.getValue()) < 0) {
                // Divide
                SOFT_ALLOCTMsg newsoftAllocMsg = new SOFT_ALLOCTMsg();
                EZDMsg.copy(softAllocMsgArray.no(i), null, newsoftAllocMsg, null);

                divideSetSoftAllocPk = getSoftAllocSQ();

                newsoftAllocMsg.softAllocPk.setValue(divideSetSoftAllocPk);
                newsoftAllocMsg.hardAllocAvalQty.setValue(ZERO);
                newsoftAllocMsg.softAllocQty.setValue(calcQty);
                newsoftAllocMsg.shpgPlnNum.setValue(shpgPlnNumHA);
                // 06/07/2010 Defect add
                newsoftAllocMsg.setSoftAllocPk.setValue(divideSetSoftAllocPk);

                // ***** insert
                insertSoftAlloc(msgMap, newsoftAllocMsg);

                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                BigDecimal calcSoftAllocQty = subtract(softAllocMsgArray.no(i).softAllocQty.getValue(), calcQty);
                updsoftAllocMsg.softAllocQty.setValue(calcSoftAllocQty);

                // ***** update
                updateSoftAlloc(msgMap, updsoftAllocMsg);
                break;

            } else {
                // nothing to do
                calcQty = subtract(calcQty, softAllocMsgArray.no(i).softAllocQty.getValue());
                continue;
            }
        }

        return divideSetSoftAllocPk;
    }

    private void softAllocCancelProcess(S21ApiMessageMap msgMap, final List<HARD_ALLOCTMsg> cancelHardAllocList) {

        NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();
        boolean firstFlg = true;
        BigDecimal curentSoftAllocPk = null;
        BigDecimal cancelSoftAllocQty = ZERO;

        // SHPG_PLN ('Validated')
        String shpgPlnNum;
        SHPG_PLNTMsgArray shpgPlnMsgData = searchSHPGPLNshipStsForValidated(param1);

        if (shpgPlnMsgData.length() == 0) {
            shpgPlnNum = "";

        } else if (shpgPlnMsgData.length() == 1) {
            shpgPlnNum = shpgPlnMsgData.no(0).shpgPlnNum.getValue();
        } else {
            msgMap.addXxMsgId(NWZM0076E);
            return;
        }

        for (HARD_ALLOCTMsg cancelHardALlocMsg : cancelHardAllocList) {

            if (hasValue(cancelHardALlocMsg.softAllocPk)) {
                boolean updSoftAllocFlg = true;

                if (firstFlg) {

                    curentSoftAllocPk = (BigDecimal) cancelHardALlocMsg.softAllocPk.getValue();
                    cancelSoftAllocQty = add(cancelSoftAllocQty, (BigDecimal) cancelHardALlocMsg.hardAllocQty.getValue());

                } else {

                    if (curentSoftAllocPk.compareTo(cancelHardALlocMsg.softAllocPk.getValue()) == 0) {

                        cancelSoftAllocQty = add(cancelSoftAllocQty, (BigDecimal) cancelHardALlocMsg.hardAllocQty.getValue());

                    } else {

                        SOFT_ALLOCTMsg softAllocMsg = this.lockSoftAlloc(curentSoftAllocPk);

                        if (softAllocMsg == null) {
                            continue;

                        } else {
                            // Marge
                            SOFT_ALLOCTMsgArray softAllocMsgArray = lockSoftAllocArray(shpgPlnNum);

                            int validSoftAllocSize = softAllocMsgArray.length();
                            String softAllocTs = softAllocMsg.softAllocTs.getValue();
                            String distPlnNum = softAllocMsg.distPlnNum.getValue();
                            BigDecimal distPk = softAllocMsg.distPk.getValue();
                            BigDecimal distStruSegPk = softAllocMsg.distStruSegPk.getValue();
                            String distTmFrameNum = softAllocMsg.distTmFrameNum.getValue();

                            for (int i = 0; i < validSoftAllocSize; i++) {

                                if (isEquals(softAllocTs, softAllocMsgArray.no(i).softAllocTs.getValue()) && isEquals(distPlnNum, softAllocMsgArray.no(i).distPlnNum.getValue())
                                        && isEquals(distTmFrameNum, softAllocMsgArray.no(i).distTmFrameNum.getValue()) && distPk.compareTo(softAllocMsgArray.no(i).distPk.getValue()) == 0
                                        && distStruSegPk.compareTo(softAllocMsgArray.no(i).distStruSegPk.getValue()) == 0 && curentSoftAllocPk.compareTo(softAllocMsgArray.no(i).softAllocPk.getValue()) != 0) {

                                    BigDecimal totHardAllocAvalQty = add(softAllocMsgArray.no(i).hardAllocAvalQty.getValue(), cancelSoftAllocQty);
                                    BigDecimal totSoftAllocQty = add(softAllocMsgArray.no(i).softAllocQty.getValue(), cancelSoftAllocQty);

                                    if (totHardAllocAvalQty.compareTo(totSoftAllocQty) > 0) {
                                        softAllocMsgArray.no(i).hardAllocAvalQty.setValue(totSoftAllocQty);
                                    } else {
                                        softAllocMsgArray.no(i).hardAllocAvalQty.setValue(totHardAllocAvalQty);
                                    }

                                    softAllocMsgArray.no(i).softAllocQty.setValue(totSoftAllocQty);

                                    // **** Update
                                    updateSoftAlloc(msgMap, softAllocMsgArray.no(i));

                                    // **** Delete
                                    logicalRemoveSoftAlloc(softAllocMsg.softAllocPk.getValue());

                                    updSoftAllocFlg = false;
                                    break;

                                }
                            }

                            if (updSoftAllocFlg) {
                                // Update
                                if (cancelSoftAllocQty.compareTo(softAllocMsg.softAllocQty.getValue()) > 0) {
                                    softAllocMsg.hardAllocAvalQty.setValue(softAllocMsg.softAllocQty.getValue());
                                } else {
                                    softAllocMsg.hardAllocAvalQty.setValue(cancelSoftAllocQty);
                                }

                                if (hasValue(shpgPlnNum)) {
                                    softAllocMsg.shpgPlnNum.setValue(shpgPlnNum);
                                }

                                updateSoftAlloc(msgMap, softAllocMsg);
                                if (isError(msgMap)) {
                                    return;
                                }
                            }
                        }

                        curentSoftAllocPk = (BigDecimal) cancelHardALlocMsg.softAllocPk.getValue();
                        cancelSoftAllocQty = ZERO;
                        cancelSoftAllocQty = add(cancelSoftAllocQty, (BigDecimal) cancelHardALlocMsg.hardAllocQty.getValue());

                    }
                }
            }
        }

        if (cancelSoftAllocQty.compareTo(ZERO) > 0) {

            SOFT_ALLOCTMsg softAllocMsg = this.lockSoftAlloc(curentSoftAllocPk);
            if (softAllocMsg == null) {
                return;
            } else {
                // Marge
                // Marge Key
                //  softAllocTs
                //  distPlnNum
                //  distPk
                //  distStruSegPk
                //  distTmFrameNum

                boolean updSoftAllocFlg = true;

                SOFT_ALLOCTMsgArray softAllocMsgArray = lockSoftAllocArray(shpgPlnNum);

                int validSoftAllocSize = softAllocMsgArray.length();
                String softAllocTs = softAllocMsg.softAllocTs.getValue();
                String distPlnNum = softAllocMsg.distPlnNum.getValue();
                BigDecimal distPk = softAllocMsg.distPk.getValue();
                BigDecimal distStruSegPk = softAllocMsg.distStruSegPk.getValue();
                String distTmFrameNum = softAllocMsg.distTmFrameNum.getValue();

                for (int i = 0; i < validSoftAllocSize; i++) {

                    if (isEquals(softAllocTs, softAllocMsgArray.no(i).softAllocTs.getValue()) && isEquals(distPlnNum, softAllocMsgArray.no(i).distPlnNum.getValue())
                            && isEquals(distTmFrameNum, softAllocMsgArray.no(i).distTmFrameNum.getValue()) && distPk.compareTo(softAllocMsgArray.no(i).distPk.getValue()) == 0
                            && distStruSegPk.compareTo(softAllocMsgArray.no(i).distStruSegPk.getValue()) == 0 && curentSoftAllocPk.compareTo(softAllocMsgArray.no(i).softAllocPk.getValue()) != 0) {

                        BigDecimal totHardAllocAvalQty = add(softAllocMsgArray.no(i).hardAllocAvalQty.getValue(), cancelSoftAllocQty);
                        BigDecimal totSoftAllocQty = add(softAllocMsgArray.no(i).softAllocQty.getValue(), cancelSoftAllocQty);

                        if (totHardAllocAvalQty.compareTo(totSoftAllocQty) > 0) {
                            softAllocMsgArray.no(i).hardAllocAvalQty.setValue(totSoftAllocQty);
                        } else {
                            softAllocMsgArray.no(i).hardAllocAvalQty.setValue(totHardAllocAvalQty);
                        }

                        softAllocMsgArray.no(i).softAllocQty.setValue(totSoftAllocQty);

                        // **** Update
                        updateSoftAlloc(msgMap, softAllocMsgArray.no(i));

                        // **** Delete
                        logicalRemoveSoftAlloc(softAllocMsg.softAllocPk.getValue());

                        updSoftAllocFlg = false;
                        break;

                    }
                }

                if (updSoftAllocFlg) {
                    // Update
                    if (cancelSoftAllocQty.compareTo(softAllocMsg.softAllocQty.getValue()) > 0) {
                        softAllocMsg.hardAllocAvalQty.setValue(softAllocMsg.softAllocQty.getValue());
                    } else {
                        softAllocMsg.hardAllocAvalQty.setValue(cancelSoftAllocQty);
                    }

                    if (hasValue(shpgPlnNum)) {
                        softAllocMsg.shpgPlnNum.setValue(shpgPlnNum);
                    }

                    // **** Update
                    updateSoftAlloc(msgMap, softAllocMsg);
                }
            }
        }
    }

    private SOFT_ALLOCTMsg softAllocDivideProcess(S21ApiMessageMap msgMap, final BigDecimal divideQty, final String shpgPlnNumHA, final String shpgPlnNumVA, final BigDecimal divideSetSoftAllocPk) {

        if (ZERO.compareTo(divideQty) == 0) {
            return null;
        }

        SOFT_ALLOCTMsgArray softAllocMsgArray = this.lockSoftAllocArray(shpgPlnNumVA);

        BigDecimal calcQty = divideQty;
        int softAllocSize = softAllocMsgArray.getValidCount();
        SOFT_ALLOCTMsg softAllocMsg = new SOFT_ALLOCTMsg();

        for (int i = 0; i < softAllocSize; i++) {

            if (calcQty.compareTo(softAllocMsgArray.no(i).softAllocQty.getValue()) < 0) {

                // Divide
                SOFT_ALLOCTMsg newsoftAllocMsg = new SOFT_ALLOCTMsg();
                EZDMsg.copy(softAllocMsgArray.no(i), null, newsoftAllocMsg, null);

                BigDecimal calcSoftAllocQty = subtract(softAllocMsgArray.no(i).softAllocQty.getValue(), calcQty);

                newsoftAllocMsg.softAllocPk.setValue(getSoftAllocSQ());
                newsoftAllocMsg.hardAllocAvalQty.setValue(ZERO);
                newsoftAllocMsg.softAllocQty.setValue(calcQty);
                newsoftAllocMsg.shpgPlnNum.setValue(shpgPlnNumHA);
                if (divideSetSoftAllocPk != null) {
                    newsoftAllocMsg.setSoftAllocPk.setValue(divideSetSoftAllocPk);
                }

                // ***** insert
                insertSoftAlloc(msgMap, newsoftAllocMsg);

                // update
                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                updsoftAllocMsg.softAllocQty.setValue(calcSoftAllocQty);
                if (calcSoftAllocQty.compareTo(updsoftAllocMsg.hardAllocAvalQty.getValue()) < 0) {
                    updsoftAllocMsg.hardAllocAvalQty.setValue(calcSoftAllocQty);
                }

                // ***** update
                updateSoftAlloc(msgMap, updsoftAllocMsg);

                softAllocMsg = newsoftAllocMsg;
                break;

            } else {

                // Update
                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                updsoftAllocMsg.hardAllocAvalQty.setValue(ZERO);
                updsoftAllocMsg.shpgPlnNum.setValue(shpgPlnNumHA);

                // ***** update
                updateSoftAlloc(msgMap, updsoftAllocMsg);

                calcQty = subtract(calcQty, softAllocMsgArray.no(i).softAllocQty.getValue());
                if (ZERO.compareTo(calcQty) == 0 || softAllocSize - 1 == i) {
                    softAllocMsg = updsoftAllocMsg;
                    break;
                }
            }
        }

        return softAllocMsg;
    }

    private SOFT_ALLOCTMsg softAllocNewProcess(S21ApiMessageMap msgMap, SOFT_ALLOCTMsg softAllocMsg, final BigDecimal updQty) {

        if (softAllocMsg.hardAllocAvalQty.getValue().compareTo(updQty) < 0) {
            // hardAllocAvalQty < AllocatedQty

            softAllocMsg.hardAllocAvalQty.setValue(ZERO);
            softAllocMsg.softAllocQty.setValue(updQty);
            softAllocMsg.shpgPlnNum.setValue(this.originalShpgPlnNum);

            updateSoftAlloc(msgMap, softAllocMsg);

            return softAllocMsg;
        } else if (softAllocMsg.hardAllocAvalQty.getValue().compareTo(updQty) == 0) {
            // hardAllocAvalQty == AllocatedQty

            softAllocMsg.hardAllocAvalQty.setValue(ZERO);
            softAllocMsg.softAllocQty.setValue(updQty);
            softAllocMsg.shpgPlnNum.setValue(this.originalShpgPlnNum);

            updateSoftAlloc(msgMap, softAllocMsg);

            return softAllocMsg;

        } else {
            // hardAllocAvalQty > AllocatedQty

            SOFT_ALLOCTMsg newsoftAllocMsg = new SOFT_ALLOCTMsg();
            EZDMsg.copy(softAllocMsg, null, newsoftAllocMsg, null);

            newsoftAllocMsg.softAllocPk.setValue(getSoftAllocSQ());
            newsoftAllocMsg.hardAllocAvalQty.setValue(ZERO);
            newsoftAllocMsg.softAllocQty.setValue(updQty);
            newsoftAllocMsg.shpgPlnNum.setValue(this.originalShpgPlnNum);

            insertSoftAlloc(msgMap, newsoftAllocMsg);

            softAllocMsg.hardAllocAvalQty.setValue(subtract(softAllocMsg.hardAllocAvalQty.getValue(), updQty));
            softAllocMsg.softAllocQty.setValue(subtract(softAllocMsg.softAllocQty.getValue(), updQty));
            updateSoftAlloc(msgMap, softAllocMsg);

            return newsoftAllocMsg;
        }
    }

    // START MODIFY S.Yamamoto [QC#2745]
    // START MODIFY S.Yamamoto [QC#2484]
    private boolean transactionProcess(S21ApiMessageMap msgMap, final AllocationItemData itemData, final String allocAfCrChkFlg) {
//    private void transactionProcess(S21ApiMessageMap msgMap, final AllocationItemData itemData) {
    // END   MODIFY S.Yamamoto [QC#2484]
    // END   MODIFY S.Yamamoto [QC#2745]

        NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        // Search Shipping Plan Status 'Validated'
        SHPG_PLNTMsg validShpgPlnMsg = this.searchSHPGPLNshipStsForValidated(msgMap, param1);

        if (isError(msgMap)) {
            return false;
        }
        if (hasValue(validShpgPlnMsg.setMdseCd) && !hasValue(validShpgPlnMsg.setShpgPlnNum)) {
            msgMap.addXxMsgId(NWZM0944E);
            return false;
        }

        // START ADD S.Yamamoto [QC#2745]
        if (Y.equals(allocAfCrChkFlg)) {
            // START ADD S.Yamamoto [QC#2484]
            if (TRX_SRC_TP.WHOLE_SALES.equals(validShpgPlnMsg.trxSrcTpCd.getValue())) {
                if (validShpgPlnMsg.crChkQty.getValueInt() == 0) {
                    return false;
                }
            }
            // END   ADD S.Yamamoto [QC#2484]
        }
        // END   ADD S.Yamamoto [QC#2745]

        String shpgPlnNumVA = validShpgPlnMsg.shpgPlnNum.getValue();
        this.originalShpgPlnNum = null;
        boolean compFlg = hasValue(validShpgPlnMsg.setMdseCd);
        this.setShpgPlnNum = validShpgPlnMsg.setShpgPlnNum.getValue();
        BigDecimal vaSoftAllocQty = validShpgPlnMsg.softAllocQty.getValue();
        boolean normalProcessFlg = true;
        SOFT_ALLOCTMsg softAllocMsg = null;

        // --------------------------
        // Update SHPG_PLN
        // --------------------------

        if (itemData.getAvailableQty().compareTo(validShpgPlnMsg.ordQty.getValue()) == 0) {

            validShpgPlnMsg.mdseCd.setValue(itemData.getMdseCd());
            validShpgPlnMsg.invtyLocCd.setValue(itemData.getInvtyLocCd());
            validShpgPlnMsg.reqShpgSvcLvlCd.setValue(itemData.getShpgSvcLvlCd());
            validShpgPlnMsg.origShpgSvcLvlCd.setValue(itemData.getShpgSvcLvlCd());
            validShpgPlnMsg.shpgStsCd.setValue(SHPG_STS.HARD_ALLOCATED);
            // 2010/05/20 Defect 5359 modify
            // vaShpgPlnMsg.shpgPlnDplyLineNum.setValue(getMaxDplyNum(param1.glblCmpyCd.getValue(),
            // param1.trxHdrNum.getValue(),
            // param1.trxLineNum.getValue(),
            // param1.trxLineSubNum.getValue(),
            // param1.trxSrcTpCd.getValue()));
            // vaShpgPlnMsg.avalHardAllocQty.setValue(BigDecimal.ZERO);

            // ***** update
            updateShpgPln(msgMap, validShpgPlnMsg);

            if (isError(msgMap)) {
                return false;
            }

            this.originalShpgPlnNum = validShpgPlnMsg.shpgPlnNum.getValue();
            // Add Shipping Plan Num
            this.shpgPlnNumList.add(this.originalShpgPlnNum);

        } else {

            divideProcess(msgMap, itemData, validShpgPlnMsg, itemData.getAvailableQty(), null, SHPG_STS.HARD_ALLOCATED, false);
        }

        // --------------------------
        // Update SOFT_ALLOC
        // --------------------------
        if (vaSoftAllocQty.compareTo(ZERO) > 0 || hasValue(param1.softAllocPk)) {

            softAllocMsg = updateSoftAlloc(msgMap, itemData.getAvailableQty(), shpgPlnNumVA);

            // 2010/07/02 Defect 7493 --- add
           // String futrueTm = getFutrueRsdDt(validShpgPlnMsg);

           // if (hasValue(futrueTm)) {
           //     setValue(validShpgPlnMsg.rsdDt, futrueTm);
           //     // ***** update
           //     updateShpgPln(msgMap, validShpgPlnMsg);
           // }
        }

        if (isError(msgMap)) {
            return false;
        }

        // --------------------------
        // Execute BizProcLog
        // --------------------------
        if (this.exeBizProcLogFlg) {
            bizProcLogMsg(param1);
            this.exeBizProcLogFlg = false;
        }

        // --------------------------
        // Update SetComponentItem
        // --------------------------
        if (compFlg) {
            // Component --> SetCompDivideProcess
            normalProcessFlg = setCompDivideProcess(msgMap, itemData, shpgPlnNumVA, softAllocMsg);
        }

        if (normalProcessFlg) {
            // --------------------------------------
            // Insert HARD_ALLOC
            // Execute Validation Process Manager API
            // --------------------------------------

            // Insert HARD_ALLOC
            insertHardAlloc(msgMap, this.originalShpgPlnNum, itemData.getAvailableQty(), itemData, softAllocMsg);

            if (isError(msgMap)) {
                return false;
            }

            // Execute Validation Process Manager API
            if (isEquals(TRX_SRC_TP.WHOLE_SALES, param1.trxSrcTpCd.getValue())) {
                callValidProcMgrAPI(msgMap, this.originalShpgPlnNum, itemData);
            }
        }
        return true;
    }

    private HLDTMsgArray updateHldFromshpgPlnNum(S21ApiMessageMap msgMap, final String shpgPlnNum, final String cpoOrdNum, final String cpoLineMum, final String cpoLineSubMum, final BigDecimal qty, final boolean remakeFlg) {

        HLDTMsgArray updHld = this.searchHldForUpdate(shpgPlnNum, cpoOrdNum, cpoLineMum, cpoLineSubMum);

        if (updHld.length() == 0) {
            return updHld;

        } else {

            int updHldSize = updHld.length();
            for (int i = 0; i < updHldSize; i++) {

                BigDecimal hldQty = ZERO;
                hldQty = add(updHld.no(i).hldQty.getValue(), qty);
                updHld.no(i).hldQty.setValue(hldQty);

                if (hldQty.compareTo(ZERO) == 0) {

                    // ***** logicalRemove
                    this.logicalRemoveHld(updHld.no(i).hldPk.getValue());
                    
                } else {

                    if (remakeFlg) {
                        // ***** update
                        updateHld(msgMap, updHld.no(i));
                        continue;
                    }

                    // START DELETE M.Fuji [#2745]
                    // creditLimitHldCheck
//                    boolean creditLimitHldFlg = this.chkCreditLimitHld(msgMap, updHld.no(i).hldRsnCd.getValue());
//                    if (isError(msgMap)) {
//                        return null;
//                    }
//
//                    if (creditLimitHldFlg) {
//                        continue;
//                    } else {
                    // END DELETE M.Fuji [#2745]
                        // ***** update
                        updateHld(msgMap, updHld.no(i));
                    // START DELETE M.Fuji [#2745]
//                    }
                    // END DELETE M.Fuji [#2745]
                }
                if (isError(msgMap)) {
                    return null;
                }
            }
        }

        return updHld;

    }

    private PRC_DTLTMsgArray updatePrcDtlFromshpgPlnNum(S21ApiMessageMap msgMap, final String shpgPlnNum, final BigDecimal qty) {

        PRC_DTLTMsgArray updPrcDtlData = searchPrcDtlForUpdate(shpgPlnNum);

        if (updPrcDtlData.length() == 0) {
            msgMap.addXxMsgId(NWZM0202E);
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
                this.logicalRemovePrcDtl(updPrcDtlData.no(i).prcDtlPk.getValue());

            } else {
                updatePrcDtl(msgMap, updPrcDtlData.no(i));
            }

            if (isError(msgMap)) {
                return null;
            }

        }
        return updPrcDtlData;
    }

    private SOFT_ALLOCTMsg updateSoftAlloc(S21ApiMessageMap msgMap, final BigDecimal allocedQty, final String shpgPlnNumVA) {

        SOFT_ALLOCTMsg softAllocMsg = null;
        NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        if (hasValue(param1.softAllocPk)) {

            SOFT_ALLOCTMsg softAllocRcd = this.lockSoftAlloc(param1.softAllocPk.getValue());

            if (softAllocRcd == null) {
                msgMap.addXxMsgId(NWZM0401E);
                return null;
            }

            softAllocMsg = softAllocNewProcess(msgMap, softAllocRcd, allocedQty);

        } else {

            softAllocMsg = softAllocDivideProcess(msgMap, allocedQty, this.originalShpgPlnNum, shpgPlnNumVA, null);
        }

        return softAllocMsg;
    }

    /**
     * update SOFT_ALLOC
     */
    private void updSoftAllocPk(S21ApiMessageMap msgMap, final String shpgPlnNum, final BigDecimal setSoftAllocPk) {

        SOFT_ALLOCTMsgArray softAllocRcdList = lockSoftAllocArray(shpgPlnNum);

        for (int i = 0; i < softAllocRcdList.length(); i++) {

            if (hasValue(setSoftAllocPk) && hasValue(softAllocRcdList.no(i).setSoftAllocPk)) {
                softAllocRcdList.no(i).setSoftAllocPk.setValue(setSoftAllocPk);
                // ***** Update
                updateSoftAlloc(msgMap, softAllocRcdList.no(i));
                if (isError(msgMap)) {
                    return;
                }
            }
        }
    }

    private static BigDecimal add(BigDecimal bd1, BigDecimal bd2) {

        if (!hasValue(bd1)) {
            bd1 = ZERO;
        }
        if (!hasValue(bd2)) {
            bd2 = ZERO;
        }
        
        return bd1.add(bd2);
    }

    private static void bizProcLogMsg(final NWZC104001PMsg param1) {

        final S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();

        bizProcLogMsg.setSubSysId(BizProcLogMsg.SUB_SYS_ID_NWD.getKey());
        bizProcLogMsg.setProcId(BizProcLogMsg.PROC_ID_OM.getKey());

        if (isEquals(REQ_TP_NEW, param1.xxRqstTpCd.getValue())) {
            bizProcLogMsg.setEventId(BizProcLogMsg.HARD_ALLOC.getKey());
        } else {
            bizProcLogMsg.setEventId(BizProcLogMsg.HARD_ALLOC_CANCEL.getKey());
        }

        bizProcLogMsg.setDocTpCd(BizProcLogMsg.DOC_TP_OM.getKey());
        bizProcLogMsg.setDocId(param1.trxLineNum.getValue() + '.' + param1.trxLineSubNum.getValue());
        bizProcLogMsg.setPrntDocId(param1.trxHdrNum.getValue());
        bizProcLogMsg.setBizProcCmntTxt_01(null);
        bizProcLogMsg.setBizProcCmntTxt_02(null);
        bizProcLogMsg.setBizProcCmntTxt_03(null);
        
        // ***** print
        S21BusinessProcessLog.print(bizProcLogMsg);
    }

    private static void chkCommonInputParam(S21ApiMessageMap msgMap) {

        NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        if (!hasValue(param1.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0011E);
            return;
        }

        if (!hasValue(param1.xxRqstTpCd)) {
            msgMap.addXxMsgId(NWZM0189E);
            return;
        }

        if (!hasValue(param1.trxSrcTpCd)) {
            msgMap.addXxMsgId(NWZM0014E);
            return;
        }

        if (!hasValue(param1.trxHdrNum)) {
            msgMap.addXxMsgId(NWZM0027E);
            return;
        }

        if (!hasValue(param1.trxLineNum)) {
            msgMap.addXxMsgId(NWZM0089E);
            return;
        }

        if (!hasValue(param1.trxLineSubNum)) {
            msgMap.addXxMsgId(NWZM0043E);
            return;
        }

        if (!hasValue(param1.slsDt)) {
            msgMap.addXxMsgId(NWZM0346E);
            return;
        }
    }

    private static void chkInvtyInputParam(S21ApiMessageMap msgMap, final NWZC104001PMsg param1, final List<NWZC104002PMsg> params2) {

        if (!isEquals(REQ_TP_NEW, param1.xxRqstTpCd.getValue())) {
            return;
        }

        for (NWZC104002PMsg param2 : params2) {

            if (!hasValue(param2.mdseCd)) {
                msgMap.addXxMsgId(NWZM0092E);
                return;
            }

            if (!hasValue(param2.invtyLocCd)) {
                msgMap.addXxMsgId(NWZM0093E);
                return;
            }

            if (!hasValue(param2.stkStsCd)) {
                msgMap.addXxMsgId(NWZM0095E);
                return;
            }

            if (!hasValue(param2.locStsCd)) {
                msgMap.addXxMsgId(NWZM0094E);
                return;
            }

            if (!hasValue(param2.invtyAvalQty)) {
                msgMap.addXxMsgId(NWZM0096E);
                return;
            }
        }
    }

    private static boolean chkItemFlip(NWZC104001PMsg param) {

        final String xxItemFlipAcptFlg  = param.xxItemFlipAcptFlg.getValue();

        if (isEquals(FLG_ON_1, xxItemFlipAcptFlg)) {
// Def#1763
//            final String xxOrdTakeMdseFlg_SA = param.xxOrdTakeMdseFlg_SA.getValue();
//            final String xxOrdTakeMdseFlg    = param.xxOrdTakeMdseFlg.getValue();
//
//            if (hasValue(xxOrdTakeMdseFlg_SA) && hasValue(param.mdseCd_SA)) {
//
//                if (isEquals(FLG_OFF_0, xxOrdTakeMdseFlg_SA)) {
//                    return true;
//                }
//
//            } else if (isEquals(FLG_OFF_0, xxOrdTakeMdseFlg)) {
//                return true;
//            }
            return true;

        }

        return false;
    }

    private static String chkItemType(NWZC104001PMsg param) {

        final String itemType;
        if (hasValue(param.softAllocPk)) {
            itemType = DISTRIBUTION_ITEM;
        } else {
            itemType = NONE_ITEM;
        }
        
        return itemType;
    }

    private static void chkNewInputParam(S21ApiMessageMap msgMap) {

        NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        if (!isEquals(REQ_TP_NEW, param1.xxRqstTpCd.getValue())) {
            return;
        }

        if (!hasValue(param1.xxPrtlAcptFlg)) {
            msgMap.addXxMsgId(NWZM0170E);
            return;
        }

        if (!hasValue(param1.xxWhFlipAcptFlg)) {
            msgMap.addXxMsgId(NWZM0172E);
            return;
        }

        if (!hasValue(param1.xxOrdTakeMdseFlg)) {
            msgMap.addXxMsgId(NWZM0398E);
            return;
        }

        if (!hasValue(param1.mdseCd)) {
            msgMap.addXxMsgId(NWZM0021E);
            return;
        }

        if (!hasValue(param1.locStsCd)) {
            msgMap.addXxMsgId(NWZM0400E);
            return;
        }

        if (!hasValue(param1.invtyLocCd)) {
            msgMap.addXxMsgId(NWZM0047E);
            return;
        }

        if (!hasValue(param1.stkStsCd)) {
            msgMap.addXxMsgId(NWZM0174E);
            return;
        }

        if (!hasValue(param1.distTpCd)) {
            msgMap.addXxMsgId(NWZM0198E);
            return;
        }

        if (!hasValue(param1.hardAllocTpCd)) {
            msgMap.addXxMsgId(NWZM0091E);
            return;
        }

        if (!hasValue(param1.reqFrtChrgMethCd)) {
            msgMap.addXxMsgId(NWZM0358E);
            return;
        }

        if (!hasValue(param1.reqFrtChrgToCd)) {
            msgMap.addXxMsgId(NWZM0175E);
            return;
        }

        if (!hasValue(param1.shpgSvcLvlCd)) {
            msgMap.addXxMsgId(NWZM0049E);
            return;
        }

        if (!hasValue(param1.rddDt) && !hasValue(param1.rsdDt)) {
            msgMap.addXxMsgId(NWZM0178E);
            return;
        }

        if (!hasValue(param1.xxRqstQty)) {
            msgMap.addXxMsgId(NWZM0185E);
            return;
        } else {
            if (param1.xxRqstQty.getValue().compareTo(ZERO) <= 0) {
                msgMap.addXxMsgId(NWZM0208E);
                return;
            }
        }

        if (!hasValue(param1.xxItemFlipAcptFlg)) {
            msgMap.addXxMsgId(NWZM0171E);
            return;
        } else if (isEquals(FLG_OFF_0, param1.xxItemFlipAcptFlg.getValue())) {
            return;
        } else if (isEquals(FLG_ON_1, param1.xxItemFlipAcptFlg.getValue())) {
            return;
        } else {
            msgMap.addXxMsgId(NWZM0678E);
        }
    }

    private static void chkParameter(S21ApiMessageMap msgMap, final List<NWZC104002PMsg> params2) {

        chkCommonInputParam(msgMap);
        if (isError(msgMap)) {
            return;
        }

        chkNewInputParam(msgMap);
        if (isError(msgMap)) {
            return;
        }

        final NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        chkShippingCancelInputParam(msgMap, param1);
        if (isError(msgMap)) {
            return;
        }

        chkInvtyInputParam(msgMap, param1, params2);
    }

    private static void chkShippingCancelInputParam(S21ApiMessageMap msgMap, final NWZC104001PMsg param1) {

        if (!isEquals(REQ_TP_SHIPPNG_CANCEL, param1.xxRqstTpCd.getValue())) {
            return;
        }

        if (!hasValue(param1.mdseCd)) {
            msgMap.addXxMsgId(NWZM0021E);
            return;
        }

        if (!hasValue(param1.invtyLocCd)) {
            msgMap.addXxMsgId(NWZM0047E);
            return;
        }

        if (!hasValue(param1.shpgSvcLvlCd)) {
            msgMap.addXxMsgId(NWZM0049E);
            return;
        }
    }

    private static EZDTMsg findByKeyWithCache(EZDTMsg tMsg) {
        
        return S21CacheTBLAccessor.findByKey(tMsg);
    }

    private static BigDecimal getHardAllocSQ() {

        return ZYPOracleSeqAccessor.getNumberBigDecimal(Sq.HARD_ALLOC_SQ.getKey());
    }

    private static BigDecimal getHldSQ() {

        return ZYPOracleSeqAccessor.getNumberBigDecimal(Sq.HLD_SQ.getKey());
    }

    private static BigDecimal getInEachQty(S21ApiMessageMap msgMap, final String mdseCd) {

        NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        String uomCd = getUomCd(param1.glblCmpyCd.getValue(), CASE_UOM_CD);

        if (!hasValue(uomCd)) {
            return ONE;
        } else {

            MDSE_STORE_PKGTMsg mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
            setValue(mdseStorePkgTMsg.glblCmpyCd, param1.glblCmpyCd);
            setValue(mdseStorePkgTMsg.mdseCd,     mdseCd);
            setValue(mdseStorePkgTMsg.pkgUomCd,   uomCd);

            mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) findByKeyWithCache(mdseStorePkgTMsg);

            if (mdseStorePkgTMsg == null) {
                return ONE;

            } else {

                if (!hasValue(mdseStorePkgTMsg.inEachQty)) {
                    return ONE;
                } else {
                    return mdseStorePkgTMsg.inEachQty.getValue();
                }
            }
        }
    }

    private static boolean getInvtyInfo(S21ApiMessageMap msgMap, final AllocationItemData itemData, final List<NWZC104002PMsg> params2) {

        NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        for (NWZC104002PMsg param2 : params2) {

            if (isEquals(itemData.getMdseCd(), param2.mdseCd.getValue()) 
                    && isEquals(itemData.getInvtyLocCd(), param2.invtyLocCd.getValue()) 
                    && isEquals(param1.stkStsCd.getValue(), param2.stkStsCd.getValue())
                    && isEquals(param1.locStsCd.getValue(), param2.locStsCd.getValue())) {

                if (param2.invtyAvalQty.getValue().compareTo(ZERO) > 0) {

                    itemData.setInvtyAvailableQty(param2.invtyAvalQty.getValue());
                    return true;

                } else {
                    break;
                }
            }
        }

        return false;

    }

    private static boolean getInvtyInfo(S21ApiMessageMap msgMap, List<AllocationItemData> itemList, final List<NWZC104002PMsg> params2) {

        final List<AllocationItemData> itemTmpList = new ArrayList<AllocationItemData>();

        boolean invtryInfoFlg = false;

        for (AllocationItemData targetItem : itemList) {
            if (getInvtyInfo(msgMap, targetItem, params2)) {
                invtryInfoFlg = true;
                itemTmpList.add(targetItem);
            }
        }
        
        if (!invtryInfoFlg) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * get NextMonth
     * </pre>
     */
    private static int getNextYyyyMm() {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat inSdf = new SimpleDateFormat();
        inSdf.applyPattern(S21CalendarUtilConstants.TYPE_YYYYMMDD);

        try {
            cal.setTime(inSdf.parse(ZYPDateUtil.getSalesDate()));
        } catch (ParseException e) {
            return 0;
        }

        cal.add(Calendar.MONTH, 1);

        SimpleDateFormat outSdf = new SimpleDateFormat();
        outSdf.applyPattern("yyyyMM");

        return Integer.parseInt(outSdf.format(cal.getTime()));

    }

    private static BigDecimal getPrcDtlSQ() {

        return ZYPOracleSeqAccessor.getNumberBigDecimal(Sq.PRC_DTL_SQ.getKey());
    }

    private static String getShipgPlnSQ() {

        return ZYPOracleSeqAccessor.getNumberString(Sq.SHPG_PLN_SQ.getKey(), SHPG_PLN_MAX_NUM);
    }

    private static BigDecimal getSoftAllocSQ() {

        return ZYPOracleSeqAccessor.getNumberBigDecimal(Sq.SOFT_ALLOC_SQ.getKey());
    }

    private static String getSystemTime() {

        return ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT);
    }

    private static HARD_ALLOCTMsgArray hardAllocFindByCondition(HARD_ALLOCTMsg msg) {

        return (HARD_ALLOCTMsgArray) findByCondition(msg);
    }

    private static void initAllocationItemData(List<AllocationItemData> itemDataList) {

        for (AllocationItemData itemData : itemDataList) {
            itemData.setTargetFlg(false);
            itemData.setFisrtFlipFlag(N);
            itemData.setAvailableQty(ZERO);
        }
    }

    private static void insertHardAlloc(S21ApiMessageMap msgMap, HARD_ALLOCTMsg hardAllocTMsg) {

//        S21FastTBLAccessor.insert(hardAllocMsg);
        insert(hardAllocTMsg);

        if (!RTNCD_NORMAL.equals(hardAllocTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0920E);
        }
    }

    private static void insertHardAlloc(S21ApiMessageMap msgMap, final String shpgPlnNumHA, final BigDecimal allocedQty, final AllocationItemData itemData, final SOFT_ALLOCTMsg softAllocMsg) {

        final NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();
        
        // [HARD_ALLOC]
        final HARD_ALLOCTMsg hardAllocTMsg = new HARD_ALLOCTMsg();
        setValue(hardAllocTMsg.glblCmpyCd,  param1.glblCmpyCd);
        setValue(hardAllocTMsg.hardAllocPk, getHardAllocSQ());
        if (softAllocMsg != null) {
            setValue(hardAllocTMsg.softAllocPk, softAllocMsg.softAllocPk);
        }
        setValue(hardAllocTMsg.mdseCd,        itemData.getMdseCd());
        setValue(hardAllocTMsg.stkStsCd,      param1.stkStsCd);
        setValue(hardAllocTMsg.locStsCd,      param1.locStsCd);
        setValue(hardAllocTMsg.trxSrcTpCd,    param1.trxSrcTpCd);
        setValue(hardAllocTMsg.trxHdrNum,     param1.trxHdrNum);
        setValue(hardAllocTMsg.trxLineNum,    param1.trxLineNum);
        setValue(hardAllocTMsg.trxLineSubNum, param1.trxLineSubNum);
        setValue(hardAllocTMsg.shpgPlnNum,    shpgPlnNumHA);
        setValue(hardAllocTMsg.hardAllocTs,   getSystemTime());
        setValue(hardAllocTMsg.shpgSvcLvlCd,  itemData.getShpgSvcLvlCd());
        setValue(hardAllocTMsg.hardAllocQty,  allocedQty);
        setValue(hardAllocTMsg.invtyLocCd,    itemData.getInvtyLocCd());
        setValue(hardAllocTMsg.cancFlg,       N);

        // ***** insert
        insertHardAlloc(msgMap, hardAllocTMsg);
    }

    private static void insertHld(S21ApiMessageMap msgMap, HLDTMsg hldTMsg) {

//        S21FastTBLAccessor.insert(inhldMsg);
        insert(hldTMsg);

        if (!RTNCD_NORMAL.equals(hldTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0079E);
        }
    }

    private static void insertPrcDtl(S21ApiMessageMap msgMap, PRC_DTLTMsg prcDtlTMsg) {

//        S21FastTBLAccessor.insert(inprcDtlMsg);
        insert(prcDtlTMsg);

        if (!RTNCD_NORMAL.equals(prcDtlTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0079E);
        }
    }

    private static void insertShpgPln(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnTMsg) {

//        S21FastTBLAccessor.insert(shpgPlnTMsg);
        insert(shpgPlnTMsg);

        if (!RTNCD_NORMAL.equals(shpgPlnTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0078E);
            return;
        }
    }

    private static void insertSoftAlloc(S21ApiMessageMap msgMap, SOFT_ALLOCTMsg softAllocTMsg) {

//        S21FastTBLAccessor.insert(softAllocTMsg);
        insert(softAllocTMsg);

        if (!RTNCD_NORMAL.equals(softAllocTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0662E);
        }
    }

    private static boolean isEmpty(Collection<?> list) {

        if (list == null || list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isError(S21ApiMessageMap msgMap) {

        if (!msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private static boolean isErrStatus(SHPG_PLNTMsg shpgPlnMsg) {

        if (isEquals(SHPG_STS.VALIDATED, shpgPlnMsg.shpgStsCd.getValue()) && isEquals(RTE_STS.UN_ROUTED, shpgPlnMsg.rteStsCd.getValue()) && isEquals(N, shpgPlnMsg.soCloseFlg.getValue())) {
            return false;
        }

        return true;
    }

    private static ALLOC_THRHDTMsg lockAllocThrhd(final AllocationItemData itemData) {

        final ALLOC_THRHDTMsg allocThrhdTMsg = new ALLOC_THRHDTMsg();
        setValue(allocThrhdTMsg.glblCmpyCd, itemData.getGlblCmpyCd());
        setValue(allocThrhdTMsg.invtyLocCd, itemData.getInvtyLocCd());
        setValue(allocThrhdTMsg.mdseCd,     itemData.getMdseCd());

        return (ALLOC_THRHDTMsg) findByKeyForUpdateAPI(allocThrhdTMsg);
    }

    private static BigDecimal multiply(BigDecimal bd1, BigDecimal bd2) {
        
        if (!hasValue(bd1)) {
            bd1 = ZERO;
        }
        if (!hasValue(bd2)) {
            bd2 = ZERO;
        }
        
        return bd1.multiply(bd2);
    }

    private static BigDecimal remainder(BigDecimal bd1, BigDecimal bd2) {
        
        if (!hasValue(bd1)) {
            bd1 = ZERO;
        }
        if (!hasValue(bd2)) {
            bd2 = ZERO;
        }
        
        return bd1.remainder(bd2);
    }

    private static BigDecimal roundHalfUp(BigDecimal bd) {

        return bd.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private static HARD_ALLOCTMsg searchHardAllocFindByKey(HARD_ALLOCTMsg hardAllocMsg) {

        return (HARD_ALLOCTMsg) findByKeyForUpdateAPI(hardAllocMsg);
    }

    private static SHPG_PLNTMsgArray searchSHPGPLNshipStsForValidated(final NWZC104001PMsg param1) {

        final SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        shpgPlnTMsg.setSQLID(SqlId.SQL_ID_SHPG_PLN_020.getKey());
        shpgPlnTMsg.setConditionValue("glblCmpyCd01",    param1.glblCmpyCd.getValue());
        shpgPlnTMsg.setConditionValue("trxHdrNum01",     param1.trxHdrNum.getValue());
        shpgPlnTMsg.setConditionValue("trxLineNum01",    param1.trxLineNum.getValue());
        shpgPlnTMsg.setConditionValue("trxLineSubNum01", param1.trxLineSubNum.getValue());
        shpgPlnTMsg.setConditionValue("trxSrcTpCd01",    param1.trxSrcTpCd.getValue());
        shpgPlnTMsg.setConditionValue("shpgStsCd01",     SHPG_STS.VALIDATED);
        shpgPlnTMsg.setMaxCount(2);

        return shpgPlnFindByConditionForUpdate(shpgPlnTMsg);
    }

    private static ALLOC_THRHDTMsg searchThresHold(final AllocationItemData itemData) {

        final ALLOC_THRHDTMsg allocThrhdTMsg = new ALLOC_THRHDTMsg();
        setValue(allocThrhdTMsg.glblCmpyCd, itemData.getGlblCmpyCd());
        setValue(allocThrhdTMsg.invtyLocCd, itemData.getInvtyLocCd());
        setValue(allocThrhdTMsg.mdseCd,     itemData.getMdseCd());

        return (ALLOC_THRHDTMsg) findByKey(allocThrhdTMsg);
    }

    private static void setCommonData(final NWZC104001PMsg param1, List<AllocationItemData> itemDataList, final String itemType, final boolean itemFlipFlg) {

        if (!itemFlipFlg) {

            final AllocationItemData itemData = new AllocationItemData();

            if (hasValue(param1.mdseCd_SA)) {
                itemData.setMdseCd(param1.mdseCd_SA.getValue());
            } else {
                itemData.setMdseCd(param1.mdseCd.getValue());
            }

            itemDataList.add(itemData);
        }
        
        for (AllocationItemData itemData : itemDataList) {

            itemData.setItemType(itemType);
            itemData.setGlblCmpyCd(param1.glblCmpyCd.getValue());
            itemData.setLocStsCd(param1.locStsCd.getValue());
            itemData.setStkStsCd(param1.stkStsCd.getValue());

            if (hasValue(param1.invtyLocCd_SA)) {
                itemData.setInvtyLocCd(param1.invtyLocCd_SA.getValue());
            } else {
                itemData.setInvtyLocCd(param1.invtyLocCd.getValue());
            }

            if (hasValue(param1.shpgSvcLvlCd_SA)) {
                itemData.setShpgSvcLvlCd(param1.shpgSvcLvlCd_SA.getValue());
            } else {
                itemData.setShpgSvcLvlCd(param1.shpgSvcLvlCd.getValue());
            }
        }
    }

    private static void setInEachQty(S21ApiMessageMap msgMap, List<AllocationItemData> itemDataList) {

        final NWZC104001PMsg param1 = (NWZC104001PMsg) msgMap.getPmsg();

        for (AllocationItemData itemData : itemDataList) {
            
            if (!hasValue(param1.uomCpltFlg) || !hasValue(param1.custUomCd)) {
                itemData.setInEachQty(ONE);

            } else if (!hasValue(param1.shipCpltCd) && Y.equals(param1.uomCpltFlg.getValue())) {
                itemData.setInEachQty(getInEachQty(msgMap, itemData.getMdseCd()));

            } else {
                itemData.setInEachQty(ONE);
            }
        }
    }

    private static void setResPMsg(final List<AllocationItemData> itemDataList, NWZC104001PMsg param1) {

        int cnt = 0;

        for (AllocationItemData itemData : itemDataList) {
            
            if (itemData.isTargetFlg()) {
                
                final NWZC104001_AllocationInfoPMsg allocPMsg = param1.AllocationInfo.no(cnt);
                setValue(allocPMsg.hardAllocQty, itemData.getAvailableQty());
                setValue(allocPMsg.invtyLocCd,   itemData.getInvtyLocCd());
                setValue(allocPMsg.mdseCd,       itemData.getMdseCd());
                setValue(allocPMsg.xxHldFlg,     itemData.getHldFlg());
                
                cnt++;
            }
        }
        
        param1.AllocationInfo.setValidCount(cnt);
    }

    private static void setResPMsg(final List<AllocationItemData> itemDataList, List<NWZC104002PMsg> params2) {

        for (AllocationItemData itemData : itemDataList) {

            for (NWZC104002PMsg param2 : params2) {

                if (    isEquals(itemData.getMdseCd(),     param2.mdseCd.getValue()) 
                     && isEquals(itemData.getInvtyLocCd(), param2.invtyLocCd.getValue()) 
                     && isEquals(itemData.getLocStsCd(),   param2.locStsCd.getValue())
                     && isEquals(itemData.getStkStsCd(),   param2.stkStsCd.getValue())) {

                    if (itemData.isTargetFlg()) {
                        setValue(param2.invtyAvalQty, itemData.getInvtyAllocAPIQty());
                    } else {
                        break;
                    }

                } else {
                    continue;
                }
            }
        }
    }

    private static void setTotAmt(SHPG_PLNTMsg shpgPlnMsg, final BigDecimal setOrdQty) {

        setValue(shpgPlnMsg.ordQty,          setOrdQty);
        setValue(shpgPlnMsg.spTotDealSlsAmt, roundHalfUp(multiply(shpgPlnMsg.spDealUnitPrcAmt.getValue(),    setOrdQty)));
        setValue(shpgPlnMsg.spTotDealNetAmt, roundHalfUp(multiply(shpgPlnMsg.spDealNetUnitPrcAmt.getValue(), setOrdQty)));
        setValue(shpgPlnMsg.spTotFuncSlsAmt, roundHalfUp(multiply(shpgPlnMsg.spFuncUnitPrcAmt.getValue(),    setOrdQty)));
        setValue(shpgPlnMsg.spTotFuncNetAmt, roundHalfUp(multiply(shpgPlnMsg.spFuncNetUnitPrcAmt.getValue(), setOrdQty)));
    }

    private static SHPG_PLNTMsgArray shpgPlnFindByConditionForUpdate(SHPG_PLNTMsg seShpgPlnMsg) {

        return (SHPG_PLNTMsgArray) findByConditionForUpdate(seShpgPlnMsg);
    }

    private static SHPG_PLNTMsg shpgPlnFindByKeyForUpdate(SHPG_PLNTMsg seShpgPlnMsg) {

//        return (SHPG_PLNTMsg) S21FastTBLAccessor.findByKey(seShpgPlnMsg);
        return (SHPG_PLNTMsg) findByKeyForUpdateAPI(seShpgPlnMsg);
    }

    private static List<AllocationItemData> sortItemDataList(S21ApiMessageMap msgMap, List<AllocationItemData> itemDataList) {

        if (itemDataList.size() == 1) {
            return itemDataList;
        }

        int newHead = 0;
        boolean firstFlipFlg = false;

        for (int i = 0; i < itemDataList.size(); i++) {
            
            final AllocationItemData itemData = itemDataList.get(i);

            if (Y.equals(itemData.getFisrtFlipFlag())) {

                newHead      = i;
                firstFlipFlg = true;
                itemData.setFisrtFlipFlag(N);

                // [ALLOC_THRHD]
                final ALLOC_THRHDTMsg allocThrhdTMsg = lockAllocThrhd(itemDataList.get(i));
                if (allocThrhdTMsg == null) {
                    msgMap.addXxMsgId(NWZM0279E);
                    return null;
                }

                // ***** update
                allocThrhdTMsg.firstFlipFlg.setValue(N);
                updateAllocThrhd(msgMap, allocThrhdTMsg);
                break;
            }
        }

        if (!firstFlipFlg) {
            return itemDataList;

        } else {

            final List<AllocationItemData> itemFlipSortList = new ArrayList<AllocationItemData>();

            for (int i = newHead; i < itemDataList.size(); i++) {
                itemFlipSortList.add(itemDataList.get(i));
            }

            for (int j = 0; j < newHead; j++) {
                itemFlipSortList.add(itemDataList.get(j));
            }

            return itemFlipSortList;
        }
    }

    private static BigDecimal subtract(BigDecimal bd1, BigDecimal bd2) {
        
        if (!hasValue(bd1)) {
            bd1 = ZERO;
        }
        if (!hasValue(bd2)) {
            bd2 = ZERO;
        }
        
        return bd1.subtract(bd2);
    }

    private static void updateAllocThrhd(S21ApiMessageMap msgMap, ALLOC_THRHDTMsg allocThrhdTMsg) {

//        S21FastTBLAccessor.update(allocThrhdMsg);
        update(allocThrhdTMsg);

        if (!RTNCD_NORMAL.equals(allocThrhdTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0279E);
        }
    }

    private static void updateHardAlloc(S21ApiMessageMap msgMap, HARD_ALLOCTMsg hardAllocTMsg) {

//        S21FastTBLAccessor.update(hardAllocMsg);
        update(hardAllocTMsg);

        if (!RTNCD_NORMAL.equals(hardAllocTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0920E);
        }
    }

    private static void updateHld(S21ApiMessageMap msgMap, HLDTMsg updHldTMsg) {

//        S21FastTBLAccessor.update(updHldMsg);
        update(updHldTMsg);

        if (!RTNCD_NORMAL.equals(updHldTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0079E);
        }
    }

    private static void updatePrcDtl(S21ApiMessageMap msgMap, PRC_DTLTMsg prcDtlTMsg) {

//        S21FastTBLAccessor.update(prcDtlTMsg);
        update(prcDtlTMsg);

        if (!RTNCD_NORMAL.equals(prcDtlTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0079E);
        }
    }

    private static void updateShpgPln(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnTMsg) {

//        S21FastTBLAccessor.update(upShpgPlnMsg);
        update(shpgPlnTMsg);

        if (!RTNCD_NORMAL.equals(shpgPlnTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0078E);
        }
    }

    private static void updateSoftAlloc(S21ApiMessageMap msgMap, SOFT_ALLOCTMsg softAllocTMsg) {

//        S21FastTBLAccessor.update(softAllocMsg);
        update(softAllocTMsg);

        if (!RTNCD_NORMAL.equals(softAllocTMsg.getReturnCode())) {
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

    /**
     * Value for Business Process Log
     */
    private static enum BizProcLogMsg {

        SUB_SYS_ID_NWD("NWD"), PROC_ID_OM("OM"), HARD_ALLOC("Hard Allocation"), HARD_ALLOC_CANCEL("Hard Allocation Cancel"), DOC_TP_OM("OM");

        private String key;

        private BizProcLogMsg(String key) {
            this.key = key;
        }

        private String getKey() {
            return key;
        }
    }

    /**
     * get Item Flip Date
     */
    private static class GetMdseList extends S21SsmListResultSetHandlerSupport {
        public List<AllocationItemData> doProcessQueryResult(ResultSet rs) throws SQLException {

            List<AllocationItemData> itemList = new ArrayList<AllocationItemData>();

            while (rs.next()) {

                AllocationItemData targetItemData = new AllocationItemData();
                targetItemData.setFisrtFlipFlag(rs.getString("FIRST_FLIP_FLG"));
                targetItemData.setMdseCd(rs.getString("MDSE_CD"));
                targetItemData.setThresholdQty(rs.getBigDecimal("THRHD_QTY"));
                itemList.add(targetItemData);
            }
            return itemList;
        }
    }

    /**
     * SQ
     */
    private static enum Sq {

        SHPG_PLN_SQ("SHPG_PLN_SQ"), 
        PRC_DTL_SQ("PRC_DTL_SQ"), 
        HLD_SQ("HLD_SQ"), 
        SOFT_ALLOC_SQ("SOFT_ALLOC_SQ"), 
        HARD_ALLOC_SQ("HARD_ALLOC_SQ");

        private String key;

        private Sq(String key) {
            this.key = key;
        }

        private String getKey() {
            return key;
        }
    }
    
    /**
     * SQL ID
     */
    private static enum SqlId {

        /** SQL ID SHPG_PLN shpg sts cd - Hard Allocated */
        SQL_ID_SHPG_PLN_021("021"),
        /** SQL ID SHPG_PLN shpg sts cd - Validated */
        SQL_ID_SHPG_PLN_020("020"), 
        SQL_ID_HLD_017("017"), 
        SQL_ID_HARD_ALLOC_009("009"), 
        SQL_ID_PRC_DTL_001("001"), 
        SQL_ID_SOFT_ALLOC_003("003"), 
        SQL_ID_SOFT_ALLOC_006("006");

        private String key;

        private SqlId(String key) {
            this.key = key;
        }

        private String getKey() {
            return key;
        }
    }

    public static final String getUomCd(String glblCmpyCd, String varCharConstNm) {
        
        VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
        setValue(varCharConstTMsg.glblCmpyCd,     glblCmpyCd);
        setValue(varCharConstTMsg.varCharConstNm, varCharConstNm);
        
        varCharConstTMsg = (VAR_CHAR_CONSTTMsg)findByKeyWithCache(varCharConstTMsg);
        
        if (varCharConstTMsg == null) {
            return null;
        } else {
            return varCharConstTMsg.varCharConstVal.getValue();
        }
    }

    /**
     * <pre>
     * A partial character string of each number of bytes is acquired.
     * </pre>
     * 
     * @param str String
     * @param beginIndex int
     * @param endIndex int
     * @return The substring that was appointed.
     */
    private String subByteString(String str, int beginIndex, int endIndex) {

        String subStr = ZYPCommonFunc.subByteString(str, beginIndex, endIndex);

        if (ZYPCommonFunc.hasValue(subStr)) {
            return subStr;
        } else {
            return str;
        }
    }
}


