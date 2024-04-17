package business.blap.NWDL0260.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;
import static parts.common.EZDDebugOutput.isDebug;

import java.math.BigDecimal;
import java.util.Arrays;

import parts.common.EZDDebugOutput;
import business.blap.NWDL0260.NWDL0260CMsg;
import business.blap.NWDL0260.NWDL0260Query;
import business.blap.NWDL0260.NWDL0260_ACMsg;
import business.blap.NWDL0260.constant.NWDL0260Constant;
import business.db.INVTYTMsg;
import business.db.MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class NWDL0260CommonLogic implements NWDL0260Constant {

    public static void backupSearchCriteria(NWDL0260CMsg cMsg) {
        setValue(cMsg.mdseCd_HD,          cMsg.mdseCd);
        setValue(cMsg.firstProdCtrlCd_HD, cMsg.firstProdCtrlCd);
        setValue(cMsg.invtyLocCd_HD,      cMsg.invtyLocCd);
    }

    public static void clearAllocationQty(NWDL0260CMsg cMsg) {
        cMsg.invtyAdvcOnHandQty_AL.clear();
        cMsg.invtyAdvcInTrnstQty_AL.clear();
    }

    public static void clearAvailableQty(NWDL0260CMsg cMsg) {
        cMsg.invtyAdvcOnHandQty_AV.clear();
        cMsg.invtyAdvcInTrnstQty_AV.clear();
    }

    public static void clearMemo(NWDL0260CMsg cMsg) {
        cMsg.hidInvtyMemoTxt.clear();
    }
    
    public static void clearSearchCriteria(NWDL0260CMsg cMsg) {
        cMsg.mdseCd.clear();
        cMsg.firstProdCtrlCd.clear();
        cMsg.invtyLocCd.clear();
    }

    public static void debug(S21BusinessHandler caller, Object... debugInfos) {
        if (isDebug()) {
            final StringBuilder sb = new StringBuilder();
            sb.append("*****[Debug]");
            for (Object debugInfo : debugInfos) {
                sb.append(debugInfo);
            }
            EZDDebugOutput.println(1, sb.toString(), caller);
        }
    }

    public static void debugMethodEnd(S21BusinessHandler caller, String methodNm) {
        if (isDebug()) {
            debug(caller, "[E N D]", methodNm, "**************************************************");
        }
    }

    public static void debugMethodStart(S21BusinessHandler caller, String methodNm) {
        if (isDebug()) {
            debug(caller, "[START]", methodNm, "**************************************************");
        }
    }

    public static void getBackuppedSearchCriteria(NWDL0260CMsg cMsg) {
        setValue(cMsg.mdseCd,          cMsg.mdseCd_HD);
        setValue(cMsg.firstProdCtrlCd, cMsg.firstProdCtrlCd_HD);
        setValue(cMsg.invtyLocCd,      cMsg.invtyLocCd_HD);
    }

    public static INVTYTMsg getInTrnstInvty(NWDL0260CMsg cMsg) {
        return getInvtyByKey(craeteInvtyKey(getInTrnstLocStsCd(), cMsg));
    }

    public static INVTYTMsg getInTrnstInvtyWithLock(NWDL0260_ACMsg lineMsg) {
        return getInvtyByKeyWithLock(craeteInvtyKey(getInTrnstLocStsCd(), lineMsg));
    }

    public static INVTYTMsg getInTrnstInvtyWithLock(NWDL0260CMsg cMsg) {
        return getInvtyByKeyWithLock(craeteInvtyKey(getInTrnstLocStsCd(), cMsg));
    }

    public static String getInTrnstLocStsCd() {
        return IN_TRANSIT;
    }

    public static MDSETMsg getMdse(NWDL0260CMsg cMsg) {
        
        final MDSETMsg mdse = new MDSETMsg();
        setValue(mdse.glblCmpyCd, S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        setValue(mdse.mdseCd,     cMsg.mdseCd);
        
        MDSETMsg resMdse = (MDSETMsg) S21FastTBLAccessor.findByKey(mdse);
        
        if (resMdse != null) {
            // Product Line
            String firstProdCtrlCd = cMsg.firstProdCtrlCd.getValue();
            if (hasValue(firstProdCtrlCd)) {
                if (!resMdse.firstProdCtrlCd.getValue().equals(firstProdCtrlCd)) {
                    resMdse = null; 
                }
            }
        }
        
        return resMdse;
    }

    public static INVTYTMsg getOnHandInvty(NWDL0260CMsg cMsg) {
        return getInvtyByKey(craeteInvtyKey(getOnHandLocStsCd(), cMsg));
    }

    public static INVTYTMsg getOnHandInvtyWithLock(NWDL0260_ACMsg lineMsg) {
        return getInvtyByKeyWithLock(craeteInvtyKey(getOnHandLocStsCd(), lineMsg));
    }

    public static INVTYTMsg getOnHandInvtyWithLock(NWDL0260CMsg cMsg) {
        return getInvtyByKeyWithLock(craeteInvtyKey(getOnHandLocStsCd(), cMsg));
    }
    
    public static String getOnHandLocStsCd() {
        return DC_STOCK;
    }

    public static NWDL0260Query getQuery() {
        return NWDL0260Query.getInstance();
    }

    public static String getStkStsCd() {
        return STK_STS.GOOD;
    }

    public static boolean hasError(NWDL0260CMsg cMsg) {
        return cMsg == null || "E".equals(cMsg.getMessageKind());
    }

    public static boolean isScreen00(NWDL0260CMsg cMsg) {
        return cMsg.getScreenAplID().toLowerCase().contains("scrn00");
    }

    public static BigDecimal nullToZero(BigDecimal val) {
        if (val == null) {
            val = ZERO;
        }
        return val;
    }

    public static String[] toArray(String... strs) {
        return Arrays.asList(strs).toArray(new String[0]);
    }

    public static BigDecimal toBigDecimal(int intValue) {
        return BigDecimal.valueOf(intValue);
    }
    
    private static INVTYTMsg craeteInvtyKey(String locStsCd, NWDL0260_ACMsg lineMsg) {
        
        final INVTYTMsg invty = new INVTYTMsg();
        setValue(invty.glblCmpyCd, S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        setValue(invty.mdseCd,     lineMsg.mdseCd_A1);
        setValue(invty.invtyLocCd, lineMsg.invtyLocCd_A1);
        setValue(invty.stkStsCd,   lineMsg.stkStsCd_A1);
        setValue(invty.locStsCd,   locStsCd);

        return invty;
    }

    private static INVTYTMsg craeteInvtyKey(String locStsCd, NWDL0260CMsg cMsg) {

        final INVTYTMsg invty = new INVTYTMsg();
        setValue(invty.glblCmpyCd, S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        setValue(invty.mdseCd,     cMsg.mdseCd);
        setValue(invty.invtyLocCd, cMsg.invtyLocCd);
        setValue(invty.stkStsCd,   getStkStsCd());
        setValue(invty.locStsCd,   locStsCd);

        return invty;
    }

    private static INVTYTMsg getInvtyByKey(INVTYTMsg invty) {
        return (INVTYTMsg) S21FastTBLAccessor.findByKey(invty);
    }

    private static INVTYTMsg getInvtyByKeyWithLock(INVTYTMsg invty) {
        return (INVTYTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(invty);
    }
}
