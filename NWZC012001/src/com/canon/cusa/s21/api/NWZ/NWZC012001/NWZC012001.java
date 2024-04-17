/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * Payer's billing date (Last Invoice Date) checks whether to have passed a
 * certain period compared with the sales date.
 * When the sales date has passed a certain period, Hold information is returned.
 * 
 * The period (days) that becomes a check standard must be defined by NUM_CONST.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/17/2009   Fujitsu         S.Sugino        Create          N/A
 * 10/27/2009   Fujitsu         S.Sugino        Update          N/A
 * 11/19/2009   Fujitsu         T.Kaneda        Update          N/A
 * 12/08/2009   Fujitsu         S.Sugino        Update          N/A Cache of CUST_CR_PRFL.
 * 02/08/2010   Fujitsu         T.Nagase        Update          N/A (Dealer profile)
 * 09/20/2010   Fujitsu         S.Yamamoto      Update          25(All2)
 * 11/30/2010   Fujitsu         K.Tajima        Update          Def# 645 [Performance] NWAL0010 Order Entry (case of Auto Allocation)
 * 12/02/2010   Fujitsu         S.Yamamoto      Update          540(All2)
 * </pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC012001;

import static com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ContractInfo.chkContractInfo;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDTMsg;
import business.db.CPOTMsg;
import business.db.CUST_CR_PRFLTMsgArray;
import business.db.HLDTMsg;
import business.db.HLD_RSNTMsg;
import business.db.NUM_CONSTTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXCustCrPrflTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXHldRsnTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;

public class NWZC012001 extends S21ApiCommonBase implements HLD_RSN {

    public static final String NWZM0001E = "NWZM0001E";
    public static final String NWZM0002E = "NWZM0002E";
    public static final String NWZM0003E = "NWZM0003E";
    public static final String NWZM0004E = "NWZM0004E";
    public static final String NWZM0250E = "NWZM0250E";
    public static final String NWZM0073E = "NWZM0073E";
    public static final String NWZM0241E = "NWZM0241E";
    public static final String NWZM0344E = "NWZM0344E";
    public static final String NWZM0445E = "NWZM0445E";

    private static final String Y = ZYPConstant.FLG_ON_Y;
    private static final String N = ZYPConstant.FLG_OFF_N;
    
    private static final String SET_ITEM_PARENT_NUM = "000";
    
    /**
     * It is a key to get the check period from the NUM_CONST table.
     */
    private static final String NUM_CONST_HOLD_LAST_INV_AOT = "HOLD_LAST_INV_AOT";

    private final LocalCache localCache = new LocalCache();
    
    public NWZC012001() {
        super();
    }

    /**
     * <pre>
     * Refer to the class comment.
     * </pre>
     * 
     * @param param Interface Msg of Last Invoice Date API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(param.getInputPMsg());

        doProcess(param, msgMap, onBatchType);

        msgMap.flush();
    }

    /**
     * <pre>
     * Refer to the class comment.
     * One Msg in List is taken out, and the execute(NWXC005001ValidationBean, ONBATCH_TYPE) is executed.
     * </pre>
     * 
     * @see #execute(NWXC005001ValidationBean,
     * com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE)
     * @param params Interface Msg list of Last Invoice Date API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWXC005001ValidationBean> params, final ONBATCH_TYPE onBatchType) {
        for (NWXC005001ValidationBean param : params) {
            execute(param, onBatchType);
        }
    }

    private void doProcess(NWXC005001ValidationBean param, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        if (!checkInput(msgMap, onBatchType)) {
            return;
        }

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();

        String glblCmpyCd = inMsg.glblCmpyCd.getValue();
        String shpgPlnNum = inMsg.shpgPlnNum_I.getValue();

        CPOTMsg cpoTMsg = param.getCTMsg();
        SHPG_PLNTMsg shpgPlnTMsg = param.getSpTMsg();

        if (SET_ITEM_PARENT_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue())) {
            return;
        }

        String payerCustCd = cpoTMsg.payerCustCd.getValue();
        CUST_CR_PRFLTMsgArray custCrPrflMsgList = findCreditProfile(glblCmpyCd, payerCustCd);
        if (custCrPrflMsgList.getValidCount() == 0) {
            msgMap.addXxMsgId(NWZM0241E);
            return;
        }

        // When Invoice Date is not checked, processing is ended.
        String invDtChkReqFlg = custCrPrflMsgList.no(0).invDtChkReqFlg.getValue();
        if (N.equals(invDtChkReqFlg)) {
            return;
        }

        // When the date when checkDate was added to Last Invoice Date
        // exceeds the sales date,
        // Hold is executed.
        int checkDate = findNumConstVal(glblCmpyCd);
        String slsDt = inMsg.slsDt.getValue();
        String lastInvDt = custCrPrflMsgList.no(0).lastInvDt.getValue();

        if (!checkLastInvoiceDate(slsDt, lastInvDt, checkDate)) {
            return;
        }

        // Whether Hold of Last Invoice Date exists is checked.
        if (existLastInvoiceDateHold(glblCmpyCd, shpgPlnNum)) {
            return;
        }

        final HLD_RSNTMsg hldRsnTMsg = NWXHldRsnTMsgThreadLocalCache.getInstance().get(glblCmpyCd, LAST_INVOICE_DATE);
        if (Y.equals(hldRsnTMsg.exReqFlg.getValue())) {
            // Hold is executed when there is no Hold data of Last Invoice Date in the exception table.
            if (existCustExToOrdRst(glblCmpyCd, payerCustCd)) {
                return;
            }
        }

        setValue(inMsg.cpoOrdNum_O,        cpoTMsg.cpoOrdNum);
        setValue(inMsg.cpoDtlLineNum_O,    shpgPlnTMsg.trxLineNum);
        setValue(inMsg.cpoDtlLineSubNum_O, shpgPlnTMsg.trxLineSubNum);
        setValue(inMsg.shpgPlnNum_O,       shpgPlnTMsg.shpgPlnNum);
        setValue(inMsg.hldRsnCd,           LAST_INVOICE_DATE);
    }

    private boolean existCustExToOrdRst(String glblCmpyCd, String payerCustCd) {

        // cache
        final StringBuilder cacheKeySb = new StringBuilder();
        cacheKeySb.append(glblCmpyCd).append(",");
        cacheKeySb.append(payerCustCd);
        
        final String cacheKey = cacheKeySb.toString();
        
        Boolean contractInfo = this.localCache.contractInfoCache.get(cacheKey);
        if (contractInfo == null) {
            contractInfo = chkContractInfo(glblCmpyCd, null, null, null, null, LAST_INVOICE_DATE, payerCustCd);
            this.localCache.contractInfoCache.put(cacheKey, contractInfo);
        }
            
        return contractInfo;
    }

    private static boolean checkInput(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();

        if (onBatchType == null) {
            msgMap.addXxMsgId(NWZM0344E);
            return false;
        }

        if (!hasValue(inMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0001E);
            return false;
        }

        if (!hasValue(inMsg.cpoOrdNum_I)) {
            msgMap.addXxMsgId(NWZM0002E);
            return false;
        }

        if (!hasValue(inMsg.cpoDtlLineNum_I)) {
            msgMap.addXxMsgId(NWZM0003E);
            return false;
        }

        if (!hasValue(inMsg.cpoDtlLineSubNum_I)) {
            msgMap.addXxMsgId(NWZM0004E);
            return false;
        }

        if (!hasValue(inMsg.shpgPlnNum_I)) {
            msgMap.addXxMsgId(NWZM0250E);
            return false;
        }

        if (!hasValue(inMsg.slsDt)) {
            msgMap.addXxMsgId(NWZM0445E);
            return false;
        }

        return true;
    }

    private static boolean checkLastInvoiceDate(String slsDt, String lastInvDt, int checkDate) {

        if (!hasValue(lastInvDt)) {
            return false;
        }

        lastInvDt = ZYPDateUtil.addDays(lastInvDt, checkDate);
        int compare = ZYPDateUtil.compare(slsDt, lastInvDt);
        return compare > 0;
    }

    private static boolean existLastInvoiceDateHold(String glblCmpyCd, String shpgPlnNum) {

        final HLDTMsg hldTMsg = new HLDTMsg();
        hldTMsg.setSQLID("020");
        hldTMsg.setMaxCount(1);
        hldTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        hldTMsg.setConditionValue("shpgPlnNum01", shpgPlnNum);
        hldTMsg.setConditionValue("hldRsnCd01",   LAST_INVOICE_DATE);

        return S21ApiTBLAccessor.count(hldTMsg) != 0;
    }

    private static final EZDTMsg findByKeyWithCache(EZDTMsg tMsg) {
        
        return S21CacheTBLAccessor.findByKey(tMsg);
    }

    private static CUST_CR_PRFLTMsgArray findCreditProfile(String glblCmpyCd, String payerCustCd) {
        
        return NWXCustCrPrflTMsgThreadLocalCache.getInstance().get(glblCmpyCd, payerCustCd);
    }
    
    private static int findNumConstVal(String glblCmpyCd) {

        int numConstValNum = 0;
        
        // [NUM_CONS]
        NUM_CONSTTMsg numConstTMsg = new NUM_CONSTTMsg();
        setValue(numConstTMsg.glblCmpyCd, glblCmpyCd);
        setValue(numConstTMsg.numConstNm, NUM_CONST_HOLD_LAST_INV_AOT);
        numConstTMsg = (NUM_CONSTTMsg)findByKeyWithCache(numConstTMsg);
        
        if (numConstTMsg != null) {
            final BigDecimal numConstVal = numConstTMsg.numConstVal.getValue();
            if (numConstVal != null) {
                numConstValNum = numConstVal.intValue();
            }
        }
        
        return numConstValNum;
    }

    
    /**
     * Local Data Cache.
     * 
     * @author K.Tajima
     */
    private static final class LocalCache {
        
        final S21LRUMap<String, Boolean> contractInfoCache = new S21LRUMap<String, Boolean>();
    }
    
}
