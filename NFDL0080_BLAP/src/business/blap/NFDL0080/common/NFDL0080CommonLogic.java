/**
 * <Pre>
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * </Pre>
 */
package business.blap.NFDL0080.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0080.NFDL0080CMsg;
import business.blap.NFDL0080.NFDL0080SMsg;
import business.blap.NFDL0080.constant.NFDL0080Constant;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.AR_UPD_CUST_CD_LOCK_WRKTMsg;
import business.db.GLBL_CMPYTMsg;

/**
 * <pre>
 * NFDL0080CommonLogic.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/20   Fujitsu         M.Nakamura      Create          N/A
 * 2016/12/02   Fujitsu         H.Ikeda         Update          QC#15823
 * 2022/03/14   CITS            K.Suzuki        Update          QC#59660-2
 * </pre>
 */
public class NFDL0080CommonLogic {

    /**
     * @param bizMsg NFDL0080CMsg
     * @param inMsg AR_ACCT_DTTMsg
     * @return AR_ACCT_DTTMsg
     */
    public static AR_ACCT_DTTMsg findArAcctDtInfo(NFDL0080CMsg bizMsg, AR_ACCT_DTTMsg inMsg) {

        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inMsg.onlBatTpCd.setValue("1");

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * @param bizMsg NFDL0080CMsg
     * @param inMsg GLBL_CMPYTMsg
     * @return GLBL_CMPYTMsg
     */
    public static GLBL_CMPYTMsg findGlblCmpyInfo(NFDL0080CMsg bizMsg, GLBL_CMPYTMsg inMsg) {

        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());

        GLBL_CMPYTMsg outMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * @param bizMsg NFDL0080CMsg
     * @param inMsg AR_RCPTTMsg
     * @return AR_RCPTTMsg
     */
    public static AR_RCPTTMsg findArRcptInfo(NFDL0080CMsg bizMsg, AR_RCPTTMsg inMsg) {

        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());

        AR_RCPTTMsg outMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * @param inMsg AR_RCPTTMsg
     * @return AR_RCPTTMsg
     */
    public static AR_RCPTTMsg createArRcptForNewReceipt(AR_RCPTTMsg inMsg) {

        EZDTBLAccessor.create(inMsg);
        return inMsg;
    }

    /**
     * @param inMsg AR_RCPT_DTLTMsg
     * @return AR_RCPT_DTLTMsg
     */
    public static AR_RCPT_DTLTMsg createArRcptDtlForNewReceipt(AR_RCPT_DTLTMsg inMsg) {

        EZDTBLAccessor.create(inMsg);
        return inMsg;
    }

    /**
     * @param bizMsg NFDL0080CMsg
     * @param inMsg AR_TRX_BALTMsg
     * @return AR_TRX_BALTMsg
     */
    public static AR_TRX_BALTMsg findArTrxBalInfo(NFDL0080CMsg bizMsg, AR_TRX_BALTMsg inMsg) {

        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());

        AR_TRX_BALTMsg outMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * @param bizMsg NFDL0080CMsg
     * @param inMsg AR_TRX_BALTMsg
     * @return AR_TRX_BALTMsg
     */
    public static AR_TRX_BALTMsg createArTrxBalNewReceipt(NFDL0080CMsg bizMsg, AR_TRX_BALTMsg inMsg) {

        EZDTBLAccessor.create(inMsg);
        return inMsg;
    }

    /**
     * @param inMsg AR_APPLY_INTFC_WRKTMsg
     * @return AR_APPLY_INTFC_WRKTMsg
     */
    public static AR_APPLY_INTFC_WRKTMsg createArApplyIntfcWrk(AR_APPLY_INTFC_WRKTMsg inMsg) {

        if (!ZYPCommonFunc.hasValue(inMsg.dealApplyAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAmt, BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(inMsg.cashDiscPct)) {
            ZYPEZDItemValueSetter.setValue(inMsg.cashDiscPct, BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(inMsg.dealCashDiscAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealCashDiscAmt, BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(inMsg.dealOnAcctCashAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(inMsg.dealApplyAdjAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(inMsg.dealApplyAdjRsvdAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
        }

        EZDTBLAccessor.create(inMsg);
        return inMsg;
    }

    /**
     * @param bizMsg NFDL0080CMsg
     * @param inMsg AR_APPLY_INTFC_WRKTMsg
     * @return AR_APPLY_INTFC_WRKTMsg
     */
    public static AR_APPLY_INTFC_WRKTMsg findArApplyIntfcWrkInfo(NFDL0080CMsg bizMsg, AR_APPLY_INTFC_WRKTMsg inMsg) {

        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());

        AR_APPLY_INTFC_WRKTMsg outMsg = (AR_APPLY_INTFC_WRKTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param no int
     * @return BigDecimal
     */
    public static BigDecimal getPagenationFrom(NFDL0080CMsg bizMsg, int no) {

        int pageCnt = bizMsg.A.length();
        int pageNum = no / pageCnt;
        int retVal = 0;
        retVal = pageNum * pageCnt;

        return new BigDecimal(retVal);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param globalMsg NFDL0080SMsg
     * @param pageFrom int
     */
    public static void setCurrentPageIn(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg, int pageFrom) {

        int cnt = pageFrom;

        for (; cnt < pageFrom + bizMsg.A.length(); cnt++) {

            if (cnt < pageFrom + bizMsg.A.getValidCount()) {

                EZDMsg.copy(bizMsg.A.no(cnt - pageFrom), null, globalMsg.A.no(cnt), null);

            } else {
                break;
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param globalMsg NFDL0080SMsg
     * @param pageFrom int
     */
    public static void setCurrentPageOut(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg, int pageFrom) {

        if (pageFrom < 0) {
            pageFrom = 0;
        }

        int cnt = pageFrom;
        bizMsg.A.clear();

        for (; cnt < pageFrom + bizMsg.A.length(); cnt++) {
            if (cnt < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(cnt), null, bizMsg.A.no(cnt - pageFrom), null);
            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(cnt - pageFrom);
        bizMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pageFrom + bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());
        clearErrorOtherPage(bizMsg, globalMsg);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param param BigDecimal
     * @param scale int
     * @return BigDecimal
     */
    public static BigDecimal convertZero(BigDecimal param, int scale) {

        BigDecimal retVal = null;

        // param == null
        if (!ZYPCommonFunc.hasValue(param)) {
            retVal = BigDecimal.ZERO;
        } else {
            retVal = getRoundDown(param, scale);
        }
        return retVal;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param param BigDecimal
     * @param scale int
     * @return BigDecimal
     */
    public static BigDecimal getRoundDown(BigDecimal param, int scale) {

        BigDecimal retVal = param;

        if (ZYPCommonFunc.hasValue(param)) {
            retVal = param.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
        }
        return retVal;
    }

    /**
     * @param bizMsg NFDL0080CMsg
     * @param globalMsg NFDL0080SMsg
     * @return void
     */
    private static void clearErrorOtherPage(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg) {
        int fromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int thruNum = bizMsg.xxPageShowToNum.getValueInt() - 1;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (fromNum <= i && i <= thruNum) {
                continue;
            } else {
                globalMsg.A.no(i).xxChkBox_A1.clearErrorInfo();
                globalMsg.A.no(i).xxDealApplyAmtNum_A1.clearErrorInfo();
            }
        }
        bizMsg.setCommitSMsg(true);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param globalMsg NFDL0080SMsg
     * @param cnt int
     * @return boolean
     */
    public static boolean chkDtl(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg, int cnt) {

        boolean retVal = true;

        // All Detail
        if (!isApplyAndBalance(bizMsg, globalMsg, cnt)) {

            retVal = false;

        }

        return retVal;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param globalMsg NFDL0080SMsg
     * @param cnt int
     * @return boolean
     */
    public static boolean isApplyAndBalance(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg, int cnt) {

        boolean retVal = true;

        BigDecimal xxDealApplyAmtNumA1 = NFDL0080CommonLogic.convertZero(globalMsg.A.no(cnt).xxDealApplyAmtNum_A1.getValue(), NFDL0080Constant.VIEW_SCALE);
        BigDecimal dealRmngBalGrsAmtA1 = NFDL0080CommonLogic.convertZero(globalMsg.A.no(cnt).dealRmngBalGrsAmt_A1.getValue(), NFDL0080Constant.VIEW_SCALE);


        if ((BigDecimal.ZERO.compareTo(xxDealApplyAmtNumA1) <= 0 && BigDecimal.ZERO.compareTo(dealRmngBalGrsAmtA1) > 0) || (BigDecimal.ZERO.compareTo(xxDealApplyAmtNumA1) > 0 && BigDecimal.ZERO.compareTo(dealRmngBalGrsAmtA1) <= 0)) {

            globalMsg.A.no(cnt).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFDM0006E", null);
            retVal = false;
        }

        if (BigDecimal.ZERO.equals(xxDealApplyAmtNumA1)) {
            globalMsg.A.no(cnt).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFDM0006E", null);
            retVal = false;
        }

        return retVal;
    }

    /**
     * <dd>Start Lock To AR_UPD_CUST_CD_LOCK_WRK
     * @param bizMsg NFDL0080CMsg
     * @param userInfo S21UserInfo
     * @return
     */
    public static void startCustCdLock(NFDL0080CMsg bizMsg, S21UserInfo userInfo) {

        AR_UPD_CUST_CD_LOCK_WRKTMsg outMsg = checkCustCdLockForUpdate(bizMsg, userInfo);
        if (outMsg == null) {
            return;
        }

    }

    /**
     * <dd>End unLock To AR_UPD_CUST_CD_LOCK_WRK
     * @param bizMsg NFDL0080CMsg
     * @param userInfo S21UserInfo
     * @return
     */
    public static void endCustCdUnlock(NFDL0080CMsg bizMsg, S21UserInfo userInfo) {

        AR_UPD_CUST_CD_LOCK_WRKTMsg findMsg = findarUpdCustCdLockWrkInfoForUpdate(bizMsg.glblCmpyCd_H1.getValue(), bizMsg.dsAcctNum_H1.getValue());

        if (findMsg != null) {
            endUpdCustCdlock(findMsg);
            // START 2016/12/02 H.Ikeda [QC#15823,ADD]
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(findMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFDM0004E", new String[] {"AR_UPD_CUST_CD_LOCK_WRK"});
            }
            // END   2016/12/01 H.Ikeda [QC#15823,ADD]
        }
    }

    /**
     * <dd>Start Lock To AR_UPD_CUST_CD_LOCK_WRK
     * @param bizMsg NFDL0080CMsg
     * @param userInfo S21UserInfo
     * @return boolean
     */
    public static boolean checkCustCdLock(NFDL0080CMsg bizMsg, S21UserInfo userInfo) {

        AR_UPD_CUST_CD_LOCK_WRKTMsg findMsg = findarUpdCustCdLockWrkInfoForUpdate(bizMsg.glblCmpyCd_H1.getValue(), bizMsg.dsAcctNum_H1.getValue());

        if (findMsg == null) {
            return true;
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(findMsg.arLockUpFlg.getValue())) {
                bizMsg.dsAcctNum_H1.setErrorInfo(1, "NFDM0009E", new String[] {findMsg.arLockUsrId.getValue() });
                return false;
            } else {
                return true;
            }
        }

    }

    /**
     * <dd>Start Lock To AR_UPD_CUST_CD_LOCK_WRK
     * @param val
     * @return
     */
    private static AR_UPD_CUST_CD_LOCK_WRKTMsg checkCustCdLockForUpdate(NFDL0080CMsg bizMsg, S21UserInfo userInfo) {

        String glblCmpyCd = bizMsg.glblCmpyCd_H1.getValue();
        String billToCustCd = bizMsg.dsAcctNum_H1.getValue();

        AR_UPD_CUST_CD_LOCK_WRKTMsg outMsg = null;
        AR_UPD_CUST_CD_LOCK_WRKTMsg findMsg = findarUpdCustCdLockWrkInfoForUpdate(glblCmpyCd, billToCustCd);

        if (findMsg == null) {
            outMsg = startInsCustCdlock(glblCmpyCd, billToCustCd, userInfo.getUserId());
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(findMsg.arLockUpFlg.getValue())) {
                bizMsg.setMessageInfo("NFDM0009E", new String[] {findMsg.arLockUsrId.getValue() });
                return null;
            } else {
                outMsg = startUpdCustCdlock(findMsg, userInfo.getUserId());
            }
        }

        return outMsg;

    }

    /**
     * <dd>Start Lock To AR_UPD_CUST_CD_LOCK_WRK
     * @param val
     * @return
     */
    private static AR_UPD_CUST_CD_LOCK_WRKTMsg findarUpdCustCdLockWrkInfoForUpdate(String glblCmpyCd, String billToCustCd) {

        AR_UPD_CUST_CD_LOCK_WRKTMsg lockTmsg = new AR_UPD_CUST_CD_LOCK_WRKTMsg();

        lockTmsg.glblCmpyCd.setValue(glblCmpyCd);
        lockTmsg.updLockTrgtCustCd.setValue(billToCustCd);

        AR_UPD_CUST_CD_LOCK_WRKTMsg outMsg = (AR_UPD_CUST_CD_LOCK_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(lockTmsg);

        return outMsg;

    }

    /**
     * <dd>Start Lock To AR_UPD_CUST_CD_LOCK_WRK
     * @param val
     * @return
     */
    private static AR_UPD_CUST_CD_LOCK_WRKTMsg startInsCustCdlock(String glblCmpyCd, String billToCustCd, String userInfo) {

        AR_UPD_CUST_CD_LOCK_WRKTMsg inMsg = new AR_UPD_CUST_CD_LOCK_WRKTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.updLockTrgtCustCd.setValue(billToCustCd);
        inMsg.arLockBizAppId.setValue(NFDL0080Constant.BIZ_APP_ID);
        inMsg.arLockUsrId.setValue(userInfo);
        inMsg.arLockFromDt.setValue(ZYPDateUtil.getCurrentSystemTime(NFDL0080Constant.FORMAT_DT));
        inMsg.arLockFromTm.setValue(ZYPDateUtil.getCurrentSystemTime(NFDL0080Constant.FORMAT_TM));
        inMsg.arLockThruDt.clear();
        inMsg.arLockThruTm.clear();
        inMsg.arLockUpFlg.setValue(ZYPConstant.FLG_ON_Y);

        EZDTBLAccessor.create(inMsg);

        return inMsg;
    }

    /**
     * <dd>Start Lock To AR_UPD_CUST_CD_LOCK_WRK
     * @param val
     * @return
     */
    private static AR_UPD_CUST_CD_LOCK_WRKTMsg startUpdCustCdlock(AR_UPD_CUST_CD_LOCK_WRKTMsg inMsg, String userInfo) {

        inMsg.arLockBizAppId.setValue(NFDL0080Constant.BIZ_APP_ID);
        inMsg.arLockUsrId.setValue(userInfo);
        inMsg.arLockFromDt.setValue(ZYPDateUtil.getCurrentSystemTime(NFDL0080Constant.FORMAT_DT));
        inMsg.arLockFromTm.setValue(ZYPDateUtil.getCurrentSystemTime(NFDL0080Constant.FORMAT_TM));
        inMsg.arLockThruDt.clear();
        inMsg.arLockThruTm.clear();
        inMsg.arLockUpFlg.setValue(ZYPConstant.FLG_ON_Y);

        EZDTBLAccessor.update(inMsg);

        EZDConnectionMgr.getInstance().commit();
        return inMsg;

    }

    /**
     * <dd>End unLock To AR_UPD_CUST_CD_LOCK_WRK
     * @param val
     * @return
     */
    private static void endUpdCustCdlock(AR_UPD_CUST_CD_LOCK_WRKTMsg inMsg) {

        inMsg.arLockThruDt.setValue(ZYPDateUtil.getCurrentSystemTime(NFDL0080Constant.FORMAT_DT));
        inMsg.arLockThruTm.setValue(ZYPDateUtil.getCurrentSystemTime(NFDL0080Constant.FORMAT_TM));
        inMsg.arLockUpFlg.setValue(ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.update(inMsg);

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param pageFromNum int
     * @return int
     */
    public static int getPagenationFrom(int pageFromNum) {

        int pagenationFrom = pageFromNum;

        if (0 != pagenationFrom) {
            pagenationFrom = pagenationFrom - 1;
        }
        return pagenationFrom;
    }

    /**
     * @param globalMsg NFDL0080SMsg
     * @param bizMsg NFDL0080CMsg
     */
    public static void reSetGlobalMsgToBizMsg(NFDL0080SMsg globalMsg, NFDL0080CMsg bizMsg) {

        int i = 0;
        int srcNo = 0;
        int dstNo = 0;
        int cnt = 0;

        for (; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxTpCd_A1.getValue())) {
                dstNo = i;
                for (srcNo = i + 1; srcNo < globalMsg.A.getValidCount(); srcNo++) {
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(srcNo).arTrxTpCd_A1.getValue())) {
                        EZDMsg.copy(globalMsg.A.no(srcNo), null, globalMsg.A.no(dstNo), null);
                        globalMsg.A.no(srcNo).clear();
                        break;
                    }
                }
            }
        }

        cnt = 0;
        for (i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxTpCd_A1.getValue())) {
                cnt++;
            } else {
                globalMsg.A.no(i).clear();
            }
        }
        globalMsg.A.setValidCount(cnt);
        setGlobalMsgToBizMsg(globalMsg, bizMsg, 0, 0);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param index Global area information Start
     * @param pagenationFrom pagenationFrom
     */
    public static void setGlobalMsgToBizMsg(NFDL0080SMsg globalMsg, NFDL0080CMsg bizMsg, int index, int pagenationFrom) {

        ZYPTableUtil.clear(bizMsg.A);
        int i = index;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(i - pagenationFrom);
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowFromNum_H1.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());
    }

    /**
     * @param inMsg AR_APPLY_INTFC_WRKTMsg
     * @return AR_APPLY_INTFC_WRKTMsg
     */
    public static AR_APPLY_INTFC_WRKTMsg createArApplyIntfcWrkInfo(AR_APPLY_INTFC_WRKTMsg inMsg) {
        EZDTBLAccessor.create(inMsg);
        return inMsg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param param String
     * @return String
     */
    public static String getYearMonth(String param) {

        StringBuilder yearManth = new StringBuilder();
        String yymmdd = ZYPDateUtil.convertFormat(param, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_YYMMDD, null);
        String[] ymd = ZYPDateUtil.getSplitDay(yymmdd);
        yearManth.append(ymd[0]);
        yearManth.append(ymd[1]);
        return yearManth.toString();
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param param String
     * @param format String
     * @return String
     */
    public static String getBeforeMonth(String param, String format) {

        String retVal = "";

        SimpleDateFormat ft = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        try {

            cal.setTime(ft.parse(param));
            cal.add(Calendar.MONTH, -1);
            retVal = ft.format(cal.getTime());

        } catch (ParseException pe) {
            EZDDebugOutput.println(1, "getBeforeMonth() param:" + param + ", format:" + format, new NFDL0080CommonLogic());
        }
        return retVal;
    }

    // START 2022/03/14 K.Suzuki [QC#59660-2,ADD]
    /**
     * @param bizMsg NFDL0080CMsg
     * @param arTrxNum String
     * @return findArTrxBalArray
     */
    public static AR_TRX_BALTMsgArray findArTrxBalArray(NFDL0080CMsg bizMsg, String arTrxNum) {

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        inArTrxBalMsg.setSQLID("001");
        inArTrxBalMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
        inArTrxBalMsg.setConditionValue("arTrxNum01", arTrxNum);
        AR_TRX_BALTMsgArray outArTrxBalMsgArray = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inArTrxBalMsg);

        return outArTrxBalMsgArray;
    }
    // START 2022/03/14 K.Suzuki [QC#59660-2,ADD]
}
