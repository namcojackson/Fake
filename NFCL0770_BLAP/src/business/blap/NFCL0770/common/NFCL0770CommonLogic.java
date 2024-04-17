package business.blap.NFCL0770.common;

import static business.blap.NFCL0770.constant.NFCL0770Constant.AR_ADJ_TP_ACCRUED_SALES_TAX;
import static business.blap.NFCL0770.constant.NFCL0770Constant.AR_ADJ_TP_FREIGHT_OUT;
import static business.blap.NFCL0770.constant.NFCL0770Constant.AR_ADJ_TP_NON_OPERATING_MISC;
import static business.blap.NFCL0770.constant.NFCL0770Constant.AR_RCPT_TRX_TP_CD_ADVANCE;
import static business.blap.NFCL0770.constant.NFCL0770Constant.AR_SUB_SYS_ID;
import static business.blap.NFCL0770.constant.NFCL0770Constant.BIZ_APP_ID;
import static business.blap.NFCL0770.constant.NFCL0770Constant.CASH_DISC_SHCD_MIN;
import static business.blap.NFCL0770.constant.NFCL0770Constant.DETAIL_MODE_ADJUSTMENT;
import static business.blap.NFCL0770.constant.NFCL0770Constant.DETAIL_MODE_DEDUCTION;
import static business.blap.NFCL0770.constant.NFCL0770Constant.DETAIL_MODE_ONACOUNT;
import static business.blap.NFCL0770.constant.NFCL0770Constant.FORMAT_DT;
import static business.blap.NFCL0770.constant.NFCL0770Constant.FORMAT_TM;
import static business.blap.NFCL0770.constant.NFCL0770Constant.KEY_NAME_OF_AR_CASH_APP_ON_BAT_CNT;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MAX_AMOUNT_DISPLAY;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MIN_AMOUNT_DISPLAY;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MODE_CANCEL;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MODE_ENTRY;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MONTH_INDEX;
import static business.blap.NFCL0770.constant.NFCL0770Constant.ONL_BAT_TP_CD;
import static business.blap.NFCL0770.constant.NFCL0770Constant.SCALE_2;
import static business.blap.NFCL0770.constant.NFCL0770Constant.YEAR_INDEX;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL0770.NFCL0770CMsg;
import business.blap.NFCL0770.NFCL0770Query;
import business.blap.NFCL0770.NFCL0770SMsg;
import business.blap.NFCL0770.NFCL0770_ACMsg;
import business.blap.NFCL0770.NFCL0770_ASMsg;
import business.blap.NFCL0770.constant.NFCL0770Constant.RECORD_STS;
import business.blap.NFCL0770.constant.NFCL0770Constant.ROW_STS;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_ADJ_TPTMsg;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_CASH_APPTMsg;
import business.db.AR_CASH_APPTMsgArray;
import business.db.AR_CASH_DISC_SCHDTMsg;
import business.db.AR_CASH_DISC_SCHDTMsgArray;
import business.db.AR_RCPTTMsg;
import business.db.AR_TOL_LVLTMsg;
import business.db.AR_TOL_LVLTMsgArray;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.AR_UPD_CUST_CD_LOCK_WRKTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.HR_TTL_APVL_LIMITTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CRAT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Update          QC#28289
 * 2023/07/03   Hitachi         S.Fujita        Update          QC#60397
 * 2023/08/02   Hitachi         S.Fujita        Update          QC#60397
 *</pre>
 */
/**
 * @author q05163
 *
 */
public class NFCL0770CommonLogic {

    /** CSV HEADER:CSV HEADR */
    private static final String[] CSV_HEADER = new String[] {"<Status>", "<Receipt Number>", "<Receipt Date>", "<Receipt Type Code>", "<Batch Name>", "<SQ Number>", "<Check Number>", "<Bank Account>", "<Bill to/Alt Payer>", "<Receipt Amount>", "<Customer Reference Number/Transaction Number>",
        "<Transaction Type Code>", "<Transaction Date>", "<Invoice Amount>", "<Applied Amount>", "<Balance Remaining>", "<CD Amount>", "<CD %>", "<Payment Term>", "<Adjustment Type Name>", "<Adjustment Amount>", "<Dept Code>", "<Prod Code>", "<Original Gross Amount>",
        "<Apply Gross Amount>", "<Others Amount>", "<Net Sales Amount>", "<Freight Amount>", "<Tax / Ins Amount>", "<Due Date>", "<Bill To Customer>", "<Summary Billing Number>", "<Order Number>", "<Customer PO Number>", "<Payment Method>", "<Payment Condition>",
        "<Transaction Number>", "<Comment>", "<Apply Date>" };

    /**  */
//    public boolean blIni = false;

    /**
     * @param bizMsg NFCL0770CMsg
     * @param inMsg AR_ACCT_DTTMsg
     * @return AR_ACCT_DTTMsg
     */
    public static AR_ACCT_DTTMsg findArAcctDtInfo(NFCL0770CMsg bizMsg, AR_ACCT_DTTMsg inMsg) {
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inMsg.subSysCd.setValue(ZYPCodeDataUtil.getVarCharConstValue(AR_SUB_SYS_ID, bizMsg.glblCmpyCd_H1.getValue()));
        inMsg.onlBatTpCd.setValue(ONL_BAT_TP_CD);

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (null != outMsg) {

            bizMsg.acctYrMth.setValue(outMsg.acctYrMth.getValue());
            bizMsg.acctDt.setValue(outMsg.acctDt.getValue());
        }

        return outMsg;
    }

    /**
     * @param inMsg AR_APPLY_INTFC_WRKTMsg
     * @return AR_APPLY_INTFC_WRKTMsg
     */
    public static AR_APPLY_INTFC_WRKTMsg findArApplyIntfcWrkInfo(AR_APPLY_INTFC_WRKTMsg inMsg) {
        return (AR_APPLY_INTFC_WRKTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param inMsg AR_RCPTTMsg
     * @return AR_RCPTTMsg
     */
    public static AR_RCPTTMsg findArRcptInfo(NFCL0770CMsg bizMsg, AR_RCPTTMsg inMsg) {
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inMsg.rcptNum.setValue(bizMsg.rcptNum.getValue());
        return (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @param inMsg AR_RCPTTMsg
     * @return AR_RCPTTMsg
     */
    public static AR_RCPTTMsg updateArRcptInfoCratMethTpCdToM(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, AR_RCPTTMsg inMsg) {

        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        inMsg.rcptNum.setValue(bizMsg.rcptNum.getValue());

        AR_RCPTTMsg resultMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (resultMsg == null) {
            bizMsg.setMessageInfo("NFCM0080E");
            S21InfoLogOutput.println("updateArRcptInfoCratMethTpCdToM Err");
            // roll back
            bizMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
            return null;
        } else {
            if (AR_CRAT_METH_TP.AUTO_EDI.equals(resultMsg.cratMethTpCd.getValue())) {
                resultMsg.glblCmpyCd.setValue(inMsg.glblCmpyCd.getValue());
                resultMsg.rcptNum.setValue(inMsg.rcptNum.getValue());
                resultMsg.cratMethTpCd.setValue(AR_CRAT_METH_TP.MANUAL_ENTRY);

                EZDTBLAccessor.update(resultMsg);

            }
        }

        return resultMsg;
    }

    /**
     * @param inMsg AR_TRX_BALTMsg
     * @return AR_TRX_BALTMsg
     */
    public static AR_TRX_BALTMsg findArTrxBalInfo(AR_TRX_BALTMsg inMsg) {
        return (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @param inMsg AR_TRX_BALTMsg
     * @return AR_TRX_BALTMsgArray
     */
    public static AR_TRX_BALTMsgArray findArTrxBalList(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, AR_TRX_BALTMsg inMsg) {
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
        inMsg.setConditionValue("arTrxBalPk01", bizMsg.arTrxBalPk_P3.getValue());
        inMsg.setConditionValue("arTrxNum01", bizMsg.arTrxNum_P3.getValue());
        inMsg.setConditionValue("arTrxTpCd01", bizMsg.arTrxTpCd_P3.getValue());
        inMsg.setConditionValue("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
        inMsg.setConditionValue("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
        inMsg.setConditionValue("dealRmngBalGrsAmt01", BigDecimal.ZERO.toString());

        inMsg.setMaxCount(1);
        inMsg.setSQLID("003");

        AR_TRX_BALTMsgArray outMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (1 <= outMsg.length()) {

            int index = globalMsg.A.getValidCount();
            globalMsg.A.no(index).arTrxNum.setValue(outMsg.no(0).arTrxNum.getValue());
            globalMsg.A.no(index).grpInvNum.setValue(outMsg.no(0).grpInvNum.getValue());
            globalMsg.A.no(index).arTrxTpCd.setValue(outMsg.no(0).arTrxTpCd.getValue());
            globalMsg.A.no(index).cpoOrdNum.setValue(outMsg.no(0).cpoOrdNum.getValue());
            globalMsg.A.no(index).invDueDt.setValue(outMsg.no(0).invDueDt.getValue());
            globalMsg.A.no(index).glDt_A1.setValue(outMsg.no(0).glDt.getValue());
            globalMsg.A.no(index).custIssPoNum.setValue(outMsg.no(0).custIssPoNum.getValue());
            globalMsg.A.no(index).arCustRefNum.setValue(outMsg.no(0).arCustRefNum.getValue());
            globalMsg.A.no(index).sellToCustCd.setValue(outMsg.no(0).sellToCustCd.getValue());
            globalMsg.A.no(index).billToCustCd.setValue(outMsg.no(0).billToCustCd.getValue());
            if (ZYPCommonFunc.hasValue(outMsg.no(0).dealOrigGrsAmt)) {
                globalMsg.A.no(index).dealOrigGrsAmt.setValue(outMsg.no(0).dealOrigGrsAmt.getValue());
            }
            if (ZYPCommonFunc.hasValue(outMsg.no(0).dealApplyGrsAmt)) {
                globalMsg.A.no(index).dealApplyGrsAmt.setValue(outMsg.no(0).dealApplyGrsAmt.getValue());
            }
            if (ZYPCommonFunc.hasValue(outMsg.no(0).dealApplyCrAmt)) {
                globalMsg.A.no(index).dealApplyCrAmt.setValue(outMsg.no(0).dealApplyCrAmt.getValue());
            }
            if (ZYPCommonFunc.hasValue(outMsg.no(0).dealNetSlsAmt)) {
                globalMsg.A.no(index).dealNetSlsAmt.setValue(outMsg.no(0).dealNetSlsAmt.getValue());
            }
            if (ZYPCommonFunc.hasValue(outMsg.no(0).dealFrtAmt)) {
                globalMsg.A.no(index).dealFrtAmt.setValue(outMsg.no(0).dealFrtAmt.getValue());
            }
            if (ZYPCommonFunc.hasValue(outMsg.no(0).dealTaxAmt)) {
                globalMsg.A.no(index).dealTaxAmt.setValue(outMsg.no(0).dealTaxAmt.getValue());
            }
            if (ZYPCommonFunc.hasValue(outMsg.no(0).dealRmngBalGrsAmt)) {
                globalMsg.A.no(index).dealRmngBalGrsAmt.setValue(outMsg.no(0).dealRmngBalGrsAmt.getValue());
            }
            if (ZYPCommonFunc.hasValue(outMsg.no(0).dealApplyAdjAmt)) {
                globalMsg.A.no(index).dealApplyAdjAmt_A3.setValue(outMsg.no(0).dealApplyAdjAmt.getValue());
            }
            // Add Start 2018/01/15 S21_NA#20563
            if (ZYPCommonFunc.hasValue(outMsg.no(0).dealApplyAdjAmt)) {
                globalMsg.A.no(index).dealApplyAdjAmt_A3.setValue(outMsg.no(0).dealApplyAdjAmt.getValue());
            } else {
                globalMsg.A.no(index).dealApplyAdjAmt_A3.setValue(BigDecimal.ZERO);
            }
            // Add End 2018/01/15 S21_NA#20563
            globalMsg.A.no(index).arTrxBalPk_BK.setValue(outMsg.no(0).arTrxBalPk.getValue());
            globalMsg.A.no(index).invTrxBalPk_BK.setValue(outMsg.no(0).arTrxBalPk.getValue());
            globalMsg.A.no(index).invTrxBalLastUpdTs_BK.setValue(outMsg.no(0).ezUpTime.getValue());
            globalMsg.A.no(index).invTrxBalTz_BK.setValue(outMsg.no(0).ezUpTimeZone.getValue());
            globalMsg.A.no(index).crNum_BK.setValue(outMsg.no(0).arTrxNum.getValue());
            globalMsg.A.no(index).crTrxBalPk_BK.setValue(outMsg.no(0).arTrxBalPk.getValue());
            globalMsg.A.no(index).crTrxBalLastUpdTs_BK.setValue(outMsg.no(0).ezUpTime.getValue());
            globalMsg.A.no(index).crTrxBalTz_BK.setValue(outMsg.no(0).ezUpTimeZone.getValue());

        }

        return outMsg;
    }

    /**
     * @param inMsg AR_TRX_BALTMsg
     * @return AR_TRX_BALTMsgArray
     */
    public static AR_TRX_BALTMsgArray findArTrxBalListForDeduction(AR_TRX_BALTMsg inMsg) {
        inMsg.setMaxCount(1);
        inMsg.setSQLID("004");
        return (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param inMsg AR_CASH_DISC_SCHDTMsg
     * @return AR_CASH_DISC_SCHDTMsgArray
     */
    public static AR_CASH_DISC_SCHDTMsgArray findArCashDiscSchdList(NFCL0770CMsg bizMsg, AR_CASH_DISC_SCHDTMsg inMsg) {
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
        //inMsg.setConditionValue("cashDiscRngFromDt01", bizMsg.rcptDt.getValue());
        //inMsg.setConditionValue("cashDiscRngThruDt01", bizMsg.rcptDt.getValue());
        inMsg.setConditionValue("cashDiscRngFromDt01", CASH_DISC_SHCD_MIN);
        inMsg.setConditionValue("cashDiscRngThruDt01", CASH_DISC_SHCD_MIN);

        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");
        return (AR_CASH_DISC_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * Set Account Name
     * @param bizMsg Biz Message
     */
    public static void setDsAcctNm(NFCL0770CMsg bizMsg) {
        bizMsg.dsAcctNm.clear();
        if (ZYPCommonFunc.hasValue(bizMsg.glblCmpyCd_H1)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
            ssmParam.put("sellToCustCd", bizMsg.payerCustCd.getValue());
            ssmParam.put("rowNum", String.valueOf(BigDecimal.ONE));
            S21SsmEZDResult dsAcct = NFCL0770Query.getInstance().getDsAcctNm(bizMsg, ssmParam);
            if (dsAcct.isCodeNormal()) {
                String dsAcctNm = (String) dsAcct.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm, dsAcctNm);
            }
        }
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @param inMsg AR_CASH_APPTMsg
     * @return AR_CASH_APPTMsgArray
     */
    public static AR_CASH_APPTMsgArray findArCashAppList(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, AR_CASH_APPTMsg inMsg) {

        inMsg.setMaxCount(0);
        inMsg.setSQLID("001");

        AR_CASH_APPTMsgArray outMsg = (AR_CASH_APPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return outMsg;
    }

    /**
     * @param inMsg AR_CASH_APPTMsg
     * @return AR_CASH_APPTMsg
     */
    public static AR_CASH_APPTMsg updateArCashAppInfoExclusive(AR_CASH_APPTMsg inMsg) {

        AR_CASH_APPTMsg resultMsg = (AR_CASH_APPTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (null == resultMsg) {
            return null;
        }

        //if (!ZYPDateUtil.isSameTimeStamp(inMsg.ezUpTime.getValue(), inMsg.ezUpTimeZone.getValue(), resultMsg.ezUpTime.getValue(), resultMsg.ezUpTimeZone.getValue())) {
        //    return null;
        //} else {
        //    // do nothing
        //}

        resultMsg.glblCmpyCd.setValue(inMsg.glblCmpyCd.getValue());
        resultMsg.arCashAppPk.setValue(inMsg.arCashAppPk.getValue());
        resultMsg.arCashAppSqNum.setValue(inMsg.arCashAppSqNum.getValue());
        resultMsg.arScrCancFlg.setValue(inMsg.arScrCancFlg.getValue());

        EZDTBLAccessor.update(resultMsg);

        return resultMsg;
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param inMsg AR_TOL_LVLTMsg
     * @return int
     */
    public static AR_TOL_LVLTMsgArray findArtolLvlList(NFCL0770CMsg bizMsg, AR_TOL_LVLTMsg inMsg) {

        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
        inMsg.setConditionValue("ccyCd01", bizMsg.ccyCd.getValue());

        inMsg.setMaxCount(0);
        inMsg.setSQLID("003");

        AR_TOL_LVLTMsgArray outMsg = (AR_TOL_LVLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return outMsg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     */
    public static void setPage(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg) {

        int pagenationFrom = getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     */
    public static void setPageJump(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg) {

        int pagenationFrom = getPagenationFrom(bizMsg.xxPageShowFromNum_H1.getValueInt());

        setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
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
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param dataCount int
     */
    public static void setPageData(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg, int dataCount) {

        if (0 == dataCount) {
            ZYPTableUtil.clear(bizMsg.A);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return;
        }

        int page = dataCount / bizMsg.A.length();
        int rest = dataCount % bizMsg.A.length();
        int fromNum = 0;

        if (0 == rest) {
            fromNum = ((page - 1) * bizMsg.A.length());
        } else {
            fromNum = (page * bizMsg.A.length());
        }

        setGlobalMsgToBizMsg(globalMsg, bizMsg, fromNum, fromNum);

        bizMsg.xxPageShowFromNum.setValue(fromNum + 1);
        bizMsg.xxPageShowToNum.setValue(fromNum + bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param index Global area information Start
     * @param pagenationFrom pagenationFrom
     */
    public static void setBizMsgToGlobalMsg(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg, int index, int pagenationFrom) {

        for (int i = index; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i - pagenationFrom).arCustRefNum)) {
                    if (!bizMsg.A.no(i - pagenationFrom).arCustRefNum.getValue().equals(bizMsg.A.no(i - pagenationFrom).arCustRefNum_BK.getValue())) {
                        String arCustRefNum = bizMsg.A.no(i - pagenationFrom).arCustRefNum.getValue();
                        bizMsg.A.no(i - pagenationFrom).clear();
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i - pagenationFrom).xxChkBox, globalMsg.A.no(i).xxChkBox);
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i - pagenationFrom).xxDealApplyAmtNum_A1, globalMsg.A.no(i).xxDealApplyAmtNum_A1);
                        bizMsg.A.no(i - pagenationFrom).xxArCashApplyStsTxt.setValue(globalMsg.A.no(i).xxArCashApplyStsTxt.getValue());
                        bizMsg.A.no(i - pagenationFrom).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
                        bizMsg.A.no(i - pagenationFrom).arCustRefNum.setValue(arCustRefNum);
                    }
                } else {
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i - pagenationFrom).arCustRefNum_BK)) {
                        bizMsg.A.no(i - pagenationFrom).clear();
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i - pagenationFrom).xxChkBox, globalMsg.A.no(i).xxChkBox);
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i - pagenationFrom).xxDealApplyAmtNum_A1, globalMsg.A.no(i).xxDealApplyAmtNum_A1);
                        bizMsg.A.no(i - pagenationFrom).xxArCashApplyStsTxt.setValue(globalMsg.A.no(i).xxArCashApplyStsTxt.getValue());
                        bizMsg.A.no(i - pagenationFrom).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
                    }
                }
                EZDMsg.copy(bizMsg.A.no(i - pagenationFrom), null, globalMsg.A.no(i), null);
            } else {
                break;
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param index Global area information Start
     * @param pagenationFrom pagenationFrom
     */
    public static void deleteBizMsgToGlobalMsg(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg, int index, int pagenationFrom) {

        for (int i = index; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(bizMsg.A.no(i - pagenationFrom), null, globalMsg.A.no(i), null);
            } else {
                break;
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param index Global area information Start
     * @param pagenationFrom pagenationFrom
     */
    public static void setGlobalMsgToBizMsg(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg, int index, int pagenationFrom) {

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
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param blIni init flag
     * @param rowSts Row Status
     */
    public static void setApplyGrsAmt(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg, boolean blIni, String rowSts) {

        // BigDecimal total = BigDecimal.ZERO;
        BigDecimal applyDetail = BigDecimal.ZERO;
        BigDecimal debit = BigDecimal.ZERO;
        BigDecimal credit = BigDecimal.ZERO;
        BigDecimal cashDisc = BigDecimal.ZERO;
        BigDecimal trxAdj = BigDecimal.ZERO;
        BigDecimal rcptAdj = BigDecimal.ZERO;
        BigDecimal onAcc = BigDecimal.ZERO;
        BigDecimal onAccDisp = BigDecimal.ZERO;
        BigDecimal deduction = BigDecimal.ZERO;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            String mode = "";

            if ((rowSts.equals(ROW_STS.CASH_APPLICATION.toString()) || rowSts.equals(ROW_STS.ALL.toString()))
                    && ROW_STS.CASH_APPLICATION.toString().equals(isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()))) {

                mode = globalMsg.A.no(i).xxModeInd_BK.getValue();
                boolean isMode3Type = mode.equals(DETAIL_MODE_ONACOUNT) || mode.equals(DETAIL_MODE_DEDUCTION) || mode.equals(DETAIL_MODE_ADJUSTMENT);
                BigDecimal cashDiscDetail = NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).dealCashDiscAmt_A1.getValue());
                BigDecimal trxAdjDetail = NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.getValue());
                // Apply Auto calculation
                applyDetail = getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()).add(cashDiscDetail).add(trxAdjDetail);
                BigDecimal balanceDetail = getNewScale(globalMsg.A.no(i).dealRmngBalGrsAmt.getValue());

                if (balanceDetail.signum() == -1) {
                    if (balanceDetail.compareTo(applyDetail) > 0) {

                        if (!isMode3Type) {
                            applyDetail = balanceDetail.subtract(cashDiscDetail).subtract(trxAdjDetail);
                            globalMsg.A.no(i).xxDealApplyAmtNum_A1.setValue(applyDetail);
                        }
                    }
                } else {
                    if (balanceDetail.compareTo(applyDetail) < 0) {

                        if (!isMode3Type) {
                            applyDetail = balanceDetail.subtract(cashDiscDetail).subtract(trxAdjDetail);
                            globalMsg.A.no(i).xxDealApplyAmtNum_A1.setValue(applyDetail);
                        }
                    }
                }

                if (mode.equals(DETAIL_MODE_ONACOUNT)) {
                    onAcc = onAcc.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()));
                } else if (mode.equals(DETAIL_MODE_DEDUCTION)) {
                    deduction = deduction.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()));
                } else if (mode.equals(DETAIL_MODE_ADJUSTMENT)) {
                    rcptAdj = rcptAdj.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()));
                } else {
                    String arTrcType = globalMsg.A.no(i).arTrxTpCd.getValue();

                    if (arTrcType.equals(AR_TRX_TP.INVOICE) || arTrcType.equals(AR_TRX_TP.DEDUCTION) || arTrcType.equals(AR_TRX_TP.DEBIT_MEMO)) {
                        debit = debit.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().add(cashDiscDetail).add(trxAdjDetail)));
                    } else {
                        credit = credit.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().add(cashDiscDetail).add(trxAdjDetail)));
                    }
                    cashDisc = cashDisc.add(cashDiscDetail);
                    trxAdj = trxAdj.add(trxAdjDetail);
                }

                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    // total =
                    // total.add(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
                    if (!isMode3Type) {
                        NFCL0770CommonLogic.setDiff(globalMsg.A.no(i), MODE_ENTRY);
                    }

                }

            } else if ((rowSts.equals(ROW_STS.CANCEL.toString()) || rowSts.equals(ROW_STS.ALL.toString()))
                 && ROW_STS.CANCEL.toString().equals(isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()))) {

                mode = globalMsg.A.no(i).xxModeInd_BK.getValue();
                boolean isMode3Type = mode.equals(DETAIL_MODE_ONACOUNT) || mode.equals(DETAIL_MODE_DEDUCTION) || mode.equals(DETAIL_MODE_ADJUSTMENT);
                BigDecimal cashDiscDetail = NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).dealCashDiscAmt_A1.getValue());
                BigDecimal trxAdjDetail = NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.getValue());

                if (!ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox.getValue())) {
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                        if (!isMode3Type) {
                            NFCL0770CommonLogic.setDiff(globalMsg.A.no(i), MODE_CANCEL);
                        }
                    }

                    if (mode.equals(DETAIL_MODE_ONACOUNT)) {
                        onAcc = onAcc.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()));
                    } else if (mode.equals(DETAIL_MODE_DEDUCTION)) {
                        deduction = deduction.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()));
                    } else if (mode.equals(DETAIL_MODE_ADJUSTMENT)) {
                        rcptAdj = rcptAdj.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()));
                    } else {
                        String arTrcType = globalMsg.A.no(i).arTrxTpCd.getValue();

                        if (arTrcType.equals(AR_TRX_TP.INVOICE) || arTrcType.equals(AR_TRX_TP.DEDUCTION) || arTrcType.equals(AR_TRX_TP.DEBIT_MEMO)) {
                            debit = debit.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().add(cashDiscDetail).add(trxAdjDetail)));
                        } else {
                            credit = credit.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().add(cashDiscDetail).add(trxAdjDetail)));
                        }
                        cashDisc = cashDisc.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).dealCashDiscAmt_A1.getValue()));
                        trxAdj = trxAdj.add(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.getValue()));
                    }
                }
            }
        }

        bizMsg.applyAmt_3.setValue(debit);
        bizMsg.applyAmt_2.setValue(credit);
        bizMsg.dealCashDiscAmt.setValue(cashDisc);
        bizMsg.trxApplyAdjAmt.setValue(trxAdj);
        bizMsg.applyAmt_1.setValue(rcptAdj);
        if (ZYPCommonFunc.hasValue(bizMsg.xxDedctAmt_S1.getValue())) {
            onAccDisp = bizMsg.xxDedctAmt_S1.getValue().add(onAcc);
        } else {
            onAccDisp = onAcc;
        }
        bizMsg.xxOnAcctAmt.setValue(onAccDisp);
        bizMsg.xxDedctAmt.setValue(deduction);
        // Trx Apply Gross
        BigDecimal trxApplyGross = debit.add(credit).subtract(cashDisc).subtract(trxAdj);
        bizMsg.trxApplyGrsAmt.setValue(trxApplyGross);
        // Apply Total
        //BigDecimal applyTotal = trxApplyGross.add(rcptAdj).add(onAcc).add(deduction);
        BigDecimal applyTotal = trxApplyGross.add(rcptAdj).add(deduction);
        if (0 < applyTotal.compareTo(MAX_AMOUNT_DISPLAY) || 0 > applyTotal.compareTo(MIN_AMOUNT_DISPLAY)) {
            bizMsg.xxApplyGrsAmt.clear();
            bizMsg.xxBalApplyGrsAmt.clear();
            bizMsg.setMessageInfo("NFCM0114E");
        } else {
            bizMsg.xxApplyGrsAmt.setValue(applyTotal);
            if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {
                bizMsg.xxBalApplyGrsAmt.setValue(bizMsg.dealRcptRmngBalAmt.getValue().subtract(bizMsg.xxApplyGrsAmt.getValue()));
            } else {
                bizMsg.xxBalApplyGrsAmt.setValue(bizMsg.dealRcptRmngBalAmt.getValue().add(bizMsg.xxApplyGrsAmt.getValue()));
            }
        }
        // Rcpt Bal-Apply Gross
        BigDecimal rcptBal = BigDecimal.ZERO;
        rcptBal = bizMsg.dealRcptRmngBalAmt.getValue();

        BigDecimal rcptBalApplyGross;
        if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {
            rcptBalApplyGross = rcptBal.subtract(applyTotal).subtract(onAcc);
        } else {
            rcptBalApplyGross = rcptBal.add(applyTotal).add(onAcc);
        }
        if (blIni) {
            rcptBalApplyGross = rcptBal;
        }
        bizMsg.xxBalApplyGrsAmt.setValue(rcptBalApplyGross);

        bizMsg.xxTotAmt_H0.setValue(bizMsg.dealApplyAmt_H1.getValue().add(applyTotal));
        bizMsg.xxTotAmt_H1.setValue(rcptBalApplyGross);

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param asMsg NFCL0770_ASMsg
     */
    public static void setClearItemBySMsg(NFCL0770_ASMsg asMsg) {

        setClearItem(asMsg.dealOrigGrsAmt);
        setClearItem(asMsg.dealApplyGrsAmt);
        setClearItem(asMsg.dealApplyCrAmt);
        setClearItem(asMsg.dealNetSlsAmt);
        setClearItem(asMsg.dealFrtAmt);
        setClearItem(asMsg.dealTaxAmt);
        setClearItem(asMsg.dealRmngBalGrsAmt);
        setClearItem(asMsg.xxDealApplyAmtNum_A1);
        setClearItem(asMsg.cashDiscPct_A1);
        if (!ZYPCommonFunc.hasValue(asMsg.cashDiscPct_A1)) {
            setClearItem(asMsg.dealCashDiscAmt_A1);
        }
        setClearItem(asMsg.xxDealApplyAdjAmtNum_A1);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param val EZDCBigDecimalItem
     */
    public static void setClearItem(EZDSBigDecimalItem val) {

        if (ZYPCommonFunc.hasValue(val) && isZero(val.getValue())) {
            val.clear();
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param val BigDecimal
     * @return boolean
     */
    public static boolean isZero(BigDecimal val) {

        boolean flg = false;
        if (0 == getNewScale(val).signum()) {
            flg = true;
        }

        return flg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param val BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal getNewScale(BigDecimal val) {
        if (null == val) {
            return BigDecimal.ZERO;
        } else {
            return val.setScale(SCALE_2, BigDecimal.ROUND_DOWN);
        }
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
        yearManth.append(ymd[YEAR_INDEX]);
        yearManth.append(ymd[MONTH_INDEX]);
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
            EZDDebugOutput.println(1, "getBeforeMonth() param:" + param + ", format:" + format, new NFCL0770CommonLogic());
        }
        return retVal;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg NFCL0770SMsg
     * @param bizMsg NFCL0770CMsg
     */
    public static void checkAdjTypeAndAdjAmtSelectOfAdjType(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg) {

        String freightOut = ZYPCodeDataUtil.getVarCharConstValue(AR_ADJ_TP_FREIGHT_OUT, bizMsg.glblCmpyCd_H1.getValue());
        String nonOperatingMiscellaneous = ZYPCodeDataUtil.getVarCharConstValue(AR_ADJ_TP_NON_OPERATING_MISC, bizMsg.glblCmpyCd_H1.getValue());
        String accruedSalesTax = ZYPCodeDataUtil.getVarCharConstValue(AR_ADJ_TP_ACCRUED_SALES_TAX, bizMsg.glblCmpyCd_H1.getValue());

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            BigDecimal dealOrigGrsAmtA1 = getNewScale(globalMsg.A.no(i).dealOrigGrsAmt.getValue());
            BigDecimal xxDealApplyAdjAmtNumA1 = getNewScale(globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.getValue());
            BigDecimal dealFrtAmtA1 = getNewScale(globalMsg.A.no(i).dealFrtAmt.getValue());
            BigDecimal dealTaxAmtA1 = getNewScale(globalMsg.A.no(i).dealTaxAmt.getValue());

            if (freightOut.equals(globalMsg.A.no(i).arAdjTpCd_A1.getValue())) {

                if (BigDecimal.ZERO.compareTo(dealOrigGrsAmtA1) <= 0) {
                    if (BigDecimal.ZERO.compareTo(xxDealApplyAdjAmtNumA1) >= 0 || dealFrtAmtA1.compareTo(xxDealApplyAdjAmtNumA1) < 0) {
                        globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setErrorInfo(1, "NFCM0084E", null);
                        bizMsg.setMessageInfo("ZZM9037E", null);
                    }
                } else {
                    if (BigDecimal.ZERO.compareTo(xxDealApplyAdjAmtNumA1) <= 0 || dealFrtAmtA1.compareTo(xxDealApplyAdjAmtNumA1) > 0) {
                        globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setErrorInfo(1, "NFCM0084E", null);
                        bizMsg.setMessageInfo("ZZM9037E", null);
                    }
                }
            } else if (nonOperatingMiscellaneous.equals(globalMsg.A.no(i).arAdjTpCd_A1.getValue())) {

                String batprocDate = ZYPDateUtil.getBatProcDate(bizMsg.glblCmpyCd_H1.getValue());
                AR_TOL_LVLTMsg inArTolLvlMsg = new AR_TOL_LVLTMsg();
                inArTolLvlMsg.setConditionValue("perFromDt01", batprocDate);
                inArTolLvlMsg.setConditionValue("perThruDt01", batprocDate);
                AR_TOL_LVLTMsgArray outArTolLvlMsg = findArtolLvlList(bizMsg, inArTolLvlMsg);

                if (outArTolLvlMsg.length() > 0) {
                    BigDecimal miscIncInvAmt = getNewScale(outArTolLvlMsg.no(0).miscIncInvAmt.getValue());
                    BigDecimal nonOpsMiscInvAmt = getNewScale(outArTolLvlMsg.no(0).nonOpsMiscInvAmt.getValue());

                    if (BigDecimal.ZERO.compareTo(dealOrigGrsAmtA1) <= 0) {
                        if (BigDecimal.ZERO.compareTo(xxDealApplyAdjAmtNumA1) >= 0 || miscIncInvAmt.compareTo(xxDealApplyAdjAmtNumA1) < 0) {
                            globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setErrorInfo(1, "NFCM0084E", null);
                            bizMsg.setMessageInfo("ZZM9037E", null);
                        }
                    } else {
                        if (BigDecimal.ZERO.compareTo(xxDealApplyAdjAmtNumA1) <= 0 || nonOpsMiscInvAmt.compareTo(xxDealApplyAdjAmtNumA1) > 0) {
                            globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setErrorInfo(1, "NFCM0084E", null);
                            bizMsg.setMessageInfo("ZZM9037E", null);
                        }
                    }
                } else {
                    globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setErrorInfo(1, "NFCM0084E", null);
                    bizMsg.setMessageInfo("ZZM9037E", null);
                }
            } else if (accruedSalesTax.equals(globalMsg.A.no(i).arAdjTpCd_A1.getValue())) {

                if (BigDecimal.ZERO.compareTo(dealOrigGrsAmtA1) <= 0) {

                    if (BigDecimal.ZERO.compareTo(xxDealApplyAdjAmtNumA1) >= 0 || dealTaxAmtA1.compareTo(xxDealApplyAdjAmtNumA1) < 0) {
                        globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setErrorInfo(1, "NFCM0084E", null);
                        bizMsg.setMessageInfo("ZZM9037E", null);
                    }
                } else {

                    if (BigDecimal.ZERO.compareTo(xxDealApplyAdjAmtNumA1) <= 0 || dealTaxAmtA1.compareTo(xxDealApplyAdjAmtNumA1) > 0) {
                        globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setErrorInfo(1, "NFCM0084E", null);
                        bizMsg.setMessageInfo("ZZM9037E", null);
                    }
                }
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg NFCL0770SMsg
     * @return boolean
     */
    public static boolean isDeductionInDetail(NFCL0770SMsg globalMsg) {

        boolean retVal = false;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (DETAIL_MODE_DEDUCTION.equals(globalMsg.A.no(i).xxModeInd_BK.getValue())) {
                retVal = true;
                break;
            }
        }
        return retVal;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg NFCL0770SMsg
     * @param dealRcptAmt BigDecimal
     * @return boolean
     */
    public static boolean isAdjustmentAmt(NFCL0770SMsg globalMsg, BigDecimal dealRcptAmt) {

        boolean retVal = true;

        boolean adjustmentFlg = false;
        BigDecimal calcApplyAmt = BigDecimal.ZERO;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (DETAIL_MODE_ADJUSTMENT.equals(globalMsg.A.no(i).xxModeInd_BK.getValue())) {
                adjustmentFlg = true;
                calcApplyAmt = calcApplyAmt.add(getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()));
            }
        }

        if (adjustmentFlg) {
            if (calcApplyAmt.compareTo(dealRcptAmt) > 0) {
                retVal = false;
            }
        }
        return retVal;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg NFCL0770SMsg
     * @return boolean
     */
    public static boolean isMinusCancelCalcAmt(NFCL0770SMsg globalMsg) {

        boolean retVal = true;

        BigDecimal calcApplyAmt = BigDecimal.ZERO;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox.getValue())) {
                calcApplyAmt = calcApplyAmt.add(getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()));
            }
        }

        if (BigDecimal.ZERO.compareTo(calcApplyAmt) > 0) {
            retVal = false;
        }
        return retVal;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg NFCL0770SMsg
     * @return boolean
     */
    public static boolean isNotCancelDeduction(NFCL0770SMsg globalMsg) {

        boolean retVal = true;

        boolean deductionFlg = false;
        BigDecimal calcApplyAmt = BigDecimal.ZERO;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox.getValue())) {
                calcApplyAmt = calcApplyAmt.add(getNewScale(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()));
            } else {
                if (DETAIL_MODE_DEDUCTION.equals(globalMsg.A.no(i).xxModeInd_BK.getValue())) {
                    deductionFlg = true;
                }
            }
        }

        if (deductionFlg && BigDecimal.ZERO.compareTo(calcApplyAmt) != 0) {
            retVal = false;
        }
        return retVal;
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @return HashMap<String, Object>
     */
    public static HashMap<String, Object> setfindArTrxBal(NFCL0770CMsg bizMsg) {

        HashMap<String, Object> inFindArTrxBalMap = new HashMap<String, Object>();

        if (ZYPCommonFunc.hasValue(bizMsg.grpInvNum_H1.getValue())) {
            inFindArTrxBalMap.put("arTrxBalPk01", null);
            inFindArTrxBalMap.put("arTrxNum01", null);
            inFindArTrxBalMap.put("arTrxTpCd01", null);
            inFindArTrxBalMap.put("grpInvNum", bizMsg.grpInvNum_H1.getValue());

        } else {
            inFindArTrxBalMap.put("arTrxBalPk01", bizMsg.arTrxBalPk_B1);
            inFindArTrxBalMap.put("arTrxNum01", bizMsg.arTrxNum_B1);
            inFindArTrxBalMap.put("arTrxTpCd01", bizMsg.arTrxTpCd_B1);
            inFindArTrxBalMap.put("grpInvNum", null);
        }

        inFindArTrxBalMap.put("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
        inFindArTrxBalMap.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
        inFindArTrxBalMap.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
        inFindArTrxBalMap.put("dealRmngBalGrsAmt01", BigDecimal.ZERO);
        //inFindArTrxBalMap.put("cashDiscRngFromDt01", bizMsg.glDt_H1.getValue());
        //inFindArTrxBalMap.put("cashDiscRngThruDt01", bizMsg.glDt_H1.getValue());
        inFindArTrxBalMap.put("cashDiscRngFromDt01", CASH_DISC_SHCD_MIN);
        inFindArTrxBalMap.put("cashDiscRngThruDt01", CASH_DISC_SHCD_MIN);
        inFindArTrxBalMap.put("subSysCd", ZYPCodeDataUtil.getVarCharConstValue(AR_SUB_SYS_ID, bizMsg.glblCmpyCd_H1.getValue()));
        inFindArTrxBalMap.put("onlBatTpCd", ONL_BAT_TP_CD);

        inFindArTrxBalMap.put("arTrxTpCdAcc", AR_TRX_TP.ON_ACCOUNT);

        return inFindArTrxBalMap;
    }

    /**
     * @param globalMsg NFCL0770SMsg
     * @param datacountA int
     * @param datacountB int
     */
    public static void copyResultMsg(NFCL0770SMsg globalMsg, int datacountA, int datacountB) {

        globalMsg.A.no(datacountA).xxArCashApplyStsTxt.setValue(RECORD_STS.NEW.getValue());
        globalMsg.A.no(datacountA).arTrxNum.setValue(globalMsg.B.no(datacountB).arTrxNum_B2.getValue());
        globalMsg.A.no(datacountA).grpInvNum.setValue(globalMsg.B.no(datacountB).grpInvNum_B2.getValue());
        globalMsg.A.no(datacountA).arTrxTpCd.setValue(globalMsg.B.no(datacountB).arTrxTpCd_B2.getValue());
        globalMsg.A.no(datacountA).cpoOrdNum.setValue(globalMsg.B.no(datacountB).cpoOrdNum_B2.getValue());
        globalMsg.A.no(datacountA).invDueDt.setValue(globalMsg.B.no(datacountB).invDueDt_B2.getValue());
        globalMsg.A.no(datacountA).glDt_A1.setValue(globalMsg.B.no(datacountB).glDt_B2.getValue());
        globalMsg.A.no(datacountA).custIssPoNum.setValue(globalMsg.B.no(datacountB).custIssPoNum_B2.getValue());
        globalMsg.A.no(datacountA).arCustRefNum.setValue(globalMsg.B.no(datacountB).arCustRefNum_B2.getValue());
        globalMsg.A.no(datacountA).billToCustCd.setValue(globalMsg.B.no(datacountB).billToCustCd_B2.getValue());
        globalMsg.A.no(datacountA).sellToCustCd.setValue(globalMsg.B.no(datacountB).sellToCustCd_B2.getValue());
        globalMsg.A.no(datacountA).dealOrigGrsAmt.setValue(globalMsg.B.no(datacountB).dealOrigGrsAmt_B2.getValue());
        globalMsg.A.no(datacountA).dealApplyGrsAmt.setValue(globalMsg.B.no(datacountB).dealApplyGrsAmt_B2.getValue());
        globalMsg.A.no(datacountA).dealApplyCrAmt.setValue(globalMsg.B.no(datacountB).dealApplyCrAmt_B2.getValue());
        globalMsg.A.no(datacountA).dealNetSlsAmt.setValue(globalMsg.B.no(datacountB).dealNetSlsAmt_B2.getValue());
        globalMsg.A.no(datacountA).dealFrtAmt.setValue(globalMsg.B.no(datacountB).dealFrtAmt_B2.getValue());
        globalMsg.A.no(datacountA).dealTaxAmt.setValue(globalMsg.B.no(datacountB).dealTaxAmt_B2.getValue());
        globalMsg.A.no(datacountA).dealRmngBalGrsAmt.setValue(globalMsg.B.no(datacountB).dealRmngBalGrsAmt_B2.getValue());
        globalMsg.A.no(datacountA).dealApplyAdjAmt_B3.setValue(globalMsg.B.no(datacountB).dealApplyAdjAmt_B2.getValue());
        globalMsg.A.no(datacountA).dealApplyAdjRsvdAmt_B3.setValue(globalMsg.B.no(datacountB).dealApplyAdjRsvdAmt_B2.getValue()); //Add 2018/01/15 S21_NA#20563
        globalMsg.A.no(datacountA).dealCashDiscAmt_A1.setValue(globalMsg.B.no(datacountB).dealCashDiscAmt_B2.getValue());
        globalMsg.A.no(datacountA).arCashDiscSchdSqNum_BK.setValue(globalMsg.B.no(datacountB).arCashDiscSchdSqNum_B2.getValue());
        globalMsg.A.no(datacountA).cashDiscPct_A1.setValue(globalMsg.B.no(datacountB).cashDiscPct_B2.getValue());
        globalMsg.A.no(datacountA).arTrxBalPk_BK.setValue(globalMsg.B.no(datacountB).arTrxBalPk_BK.getValue());
        globalMsg.A.no(datacountA).arCashApplyStsCd_BK.setValue(globalMsg.B.no(datacountB).arCashApplyStsCd_B2.getValue());
        globalMsg.A.no(datacountA).acctYrMth_BK.setValue(globalMsg.B.no(datacountB).acctYrMth_B2.getValue());
        globalMsg.A.no(datacountA).subSysCd_BK.setValue(globalMsg.B.no(datacountB).subSysCd_B2.getValue());
        globalMsg.A.no(datacountA).onlBatTpCd_BK.setValue(globalMsg.B.no(datacountB).onlBatTpCd_B2.getValue());
        globalMsg.A.no(datacountA).acctDt_BK.setValue(globalMsg.B.no(datacountB).acctDt_B2.getValue());
        globalMsg.A.no(datacountA).dealApplyAdjAmt_A3.setValue(globalMsg.B.no(datacountB).dealApplyAdjAmt_B2.getValue());
        globalMsg.A.no(datacountA).dealApplyAdjRsvdAmt_A3.setValue(globalMsg.B.no(datacountB).dealApplyAdjRsvdAmt_B2.getValue());
        globalMsg.A.no(datacountA).invTrxBalPk_BK.setValue(globalMsg.B.no(datacountB).arTrxBalPk_BK.getValue());
        globalMsg.A.no(datacountA).invTrxBalLastUpdTs_BK.setValue(globalMsg.B.no(datacountB).ezUpTime_B2.getValue());
        globalMsg.A.no(datacountA).invTrxBalTz_BK.setValue(globalMsg.B.no(datacountB).ezUpTimeZone_B2.getValue());
        globalMsg.A.no(datacountA).crNum_BK.setValue(globalMsg.B.no(datacountB).arTrxNum_B2.getValue());
        globalMsg.A.no(datacountA).crTrxBalPk_BK.setValue(globalMsg.B.no(datacountB).arTrxBalPk_BK.getValue());
        globalMsg.A.no(datacountA).crTrxBalLastUpdTs_BK.setValue(globalMsg.B.no(datacountB).ezUpTime_B2.getValue());
        globalMsg.A.no(datacountA).crTrxBalTz_BK.setValue(globalMsg.B.no(datacountB).ezUpTimeZone_B2.getValue());
        globalMsg.A.no(datacountA).arTrxDt.setValue(globalMsg.B.no(datacountB).arTrxDt_B2.getValue());
        globalMsg.A.no(datacountA).cashDiscTermCd_A1.setValue(globalMsg.B.no(datacountB).cashDiscTermCd_B2.getValue());
        globalMsg.A.no(datacountA).pmtTermCd_A1.setValue(globalMsg.B.no(datacountB).pmtTermCd_B2.getValue());
        globalMsg.A.no(datacountA).pmtTermCashDiscCd_A1.setValue(globalMsg.B.no(datacountB).pmtTermCashDiscCd_B2.getValue());
        globalMsg.A.no(datacountA).invPmtMethCd_A1.setValue(globalMsg.B.no(datacountB).invPmtMethCd_B2.getValue());
        globalMsg.A.no(datacountA).invPmtCondCd_A1.setValue(globalMsg.B.no(datacountB).invPmtCondCd_B2.getValue());
        globalMsg.A.no(datacountA).arExpPmtMethNm_A1.setValue(globalMsg.B.no(datacountB).arExpPmtMethNm_B2.getValue());
        globalMsg.A.no(datacountA).arExpPmtCondNm_A1.setValue(globalMsg.B.no(datacountB).arExpPmtCondNm_B2.getValue());
        globalMsg.A.no(datacountA).dealCcyCd_A1.setValue(globalMsg.B.no(datacountB).dealCcyCd_B2.getValue());
        globalMsg.A.no(datacountA).tocCd_A1.setValue(globalMsg.B.no(datacountB).tocCd_B2.getValue());
        globalMsg.A.no(datacountA).coaProdCd_BK.setValue(globalMsg.B.no(datacountB).coaProdCd_B2.getValue());

        globalMsg.A.no(datacountA).billToCustAcctCd_A1.setValue(globalMsg.B.no(datacountB).billToCustAcctCd_B2.getValue());
        globalMsg.A.no(datacountA).dsAcctNm_A1.setValue(globalMsg.B.no(datacountB).dsAcctNm_B2.getValue());


    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @param index int
     */
    public static void copyCSVDown(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, int index) {

        // Header
        globalMsg.C.no(index).rcptNum_C1.setValue(bizMsg.rcptNum.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.rcptDt.getValue())) {
            // START 2013/10/25 T.Yoshida [Defect#2852 Mod]
            globalMsg.C.no(index).xxDtTxt_C1.setValue(ZYPDateUtil.formatEzd8ToDisp(bizMsg.rcptDt.getValue()));
            // globalMsg.C.no(index).xxDtTxt_C1.setValue(ZYPDateUtil.DateFormatter(bizMsg.rcptDt.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
            // END   2013/10/25 T.Yoshida [Defect#2852 Mod]
        }

        globalMsg.C.no(index).arRcptTpCd_C1.setValue(bizMsg.arRcptTrxTpCd.getValue());
        globalMsg.C.no(index).arBatRcptNm_C1.setValue(bizMsg.arBatRcptNm_H1.getValue());
        globalMsg.C.no(index).rcptBatSqNum_C1.setValue(bizMsg.rcptBatSqNum.getValue());
        globalMsg.C.no(index).rcptChkNum_C1.setValue(bizMsg.rcptChkNum_H1.getValue());
        globalMsg.C.no(index).arBankAcctCd_C1.setValue(bizMsg.arBankAcctCd_H1.getValue());
        globalMsg.C.no(index).payerCustCd_C1.setValue(bizMsg.payerCustCd.getValue());
        globalMsg.C.no(index).dealRcptAmt_C1.setValue(bizMsg.dealRcptAmt.getValue());

        if (ZYPCommonFunc.hasValue(globalMsg.C.no(index).xxDtTxt_C2.getValue())) {
            globalMsg.C.no(index).xxDtTxt_C2.setValue(ZYPDateUtil.formatEzd8ToDisp(globalMsg.C.no(index).xxDtTxt_C2.getValue()));
        }

        if (ZYPCommonFunc.hasValue(globalMsg.C.no(index).xxDtTxt_C3.getValue())) {
            globalMsg.C.no(index).xxDtTxt_C3.setValue(ZYPDateUtil.formatEzd8ToDisp(globalMsg.C.no(index).xxDtTxt_C3.getValue()));
        }

        if (ZYPCommonFunc.hasValue(globalMsg.C.no(index).xxDtTxt_C4.getValue())) {
            globalMsg.C.no(index).xxDtTxt_C4.setValue(ZYPDateUtil.formatEzd8ToDisp(globalMsg.C.no(index).xxDtTxt_C4.getValue()));
        }

        if (ZYPCommonFunc.hasValue(globalMsg.C.no(index).xxDtTxt_C5.getValue())) {
            globalMsg.C.no(index).xxDtTxt_C5.setValue(ZYPDateUtil.formatEzd8ToDisp(globalMsg.C.no(index).xxDtTxt_C5.getValue()));
        }

        if (ZYPCommonFunc.hasValue(globalMsg.C.no(index).xxDtTxt_C6.getValue())) {
            globalMsg.C.no(index).xxDtTxt_C6.setValue(ZYPDateUtil.formatEzd8ToDisp(globalMsg.C.no(index).xxDtTxt_C6.getValue()));
        }

        if (ZYPCommonFunc.hasValue(globalMsg.C.no(index).dealRmngBalGrsAmt_C1) && BigDecimal.ZERO.compareTo(globalMsg.C.no(index).dealRmngBalGrsAmt_C1.getValue()) != 0) {
            globalMsg.C.no(index).xxDtlDiffAmt_C1.setValue(globalMsg.C.no(index).dealRmngBalGrsAmt_C1.getValue().subtract(globalMsg.C.no(index).xxDealApplyAmtNum_C1.getValue()));
        } else {
            globalMsg.C.no(index).xxDtlDiffAmt_C1.setValue(BigDecimal.ZERO);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * @param msg NFCL0770_ASMsg
     * @param mode String
     */
    public static void setDiff(NFCL0770_ASMsg msg, String mode) {

        if (MODE_CANCEL.equals(mode)) {
            msg.xxDtlDiffAmt_A1.setValue(msg.dealRmngBalGrsAmt.getValue());
            return;
        }
        BigDecimal invBal = NFCL0770CommonLogic.getNewScale(msg.dealRmngBalGrsAmt.getValue());
        BigDecimal aplyAmt = NFCL0770CommonLogic.getNewScale(msg.xxDealApplyAmtNum_A1.getValue());
        BigDecimal cashDisc = NFCL0770CommonLogic.getNewScale(msg.dealCashDiscAmt_A1.getValue());
        BigDecimal adjAmt = NFCL0770CommonLogic.getNewScale(msg.xxDealApplyAdjAmtNum_A1.getValue());
        BigDecimal diff = invBal.subtract(aplyAmt).subtract(cashDisc).subtract(adjAmt);
        msg.xxDtlDiffAmt_A1.setValue(diff);
    }

    /**
     * <dd>Start Lock To AR_UPD_CUST_CD_LOCK_WRK
     * @param bizMsg NFCL0770CMsg
     * @param userInfo S21UserInfo
     * @return
     */
    public static void startCustCdLock(NFCL0770CMsg bizMsg, S21UserInfo userInfo) {
//
//        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg.getValue())) {
//            // nothing
//        } else {
//            AR_UPD_CUST_CD_LOCK_WRKTMsg outMsg = chackCustCdLockForUpdate(bizMsg, userInfo);
//            if (outMsg == null) {
//                return;
//            } else {
//                bizMsg.xxPgFlg.setValue(ZYPConstant.FLG_ON_Y);
//            }
//        }
    }

    /**
     * <dd>End unLock To AR_UPD_CUST_CD_LOCK_WRK
     * @param bizMsg NFCL0770CMsg
     * @param userInfo S21UserInfo
     * @return
     */
    public static void endCustCdUnlock(NFCL0770CMsg bizMsg, S21UserInfo userInfo) {

//        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg.getValue())) {
//            AR_UPD_CUST_CD_LOCK_WRKTMsg findMsg = findarUpdCustCdLockWrkInfoForUpdate(bizMsg.glblCmpyCd_H1.getValue(), bizMsg.payerCustCd.getValue());
//
//            if (findMsg == null) {
//                // end
//            } else {
//                endUpdCustCdlock(findMsg);
//                bizMsg.xxPgFlg.setValue(ZYPConstant.FLG_OFF_N);
//            }
//        } else {
//            // nothing
//        }
    }

    /**
     * <dd>Start Lock To AR_UPD_CUST_CD_LOCK_WRK
     * @param bizMsg NFCL0770CMsg
     * @param userInfo S21UserInfo
     * @return boolean
     */
    public static boolean chackCustCdLock(NFCL0770CMsg bizMsg, S21UserInfo userInfo) {

//        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg.getValue())) {
//            return true;
//        } else {
//            AR_UPD_CUST_CD_LOCK_WRKTMsg findMsg = findarUpdCustCdLockWrkInfoForUpdate(bizMsg.glblCmpyCd_H1.getValue(), bizMsg.payerCustCd.getValue());
//
//            if (findMsg == null) {
//                return true;
//            } else {
//                if (ZYPConstant.FLG_ON_Y.equals(findMsg.arLockUpFlg.getValue())) {
//                    return false;
//                } else {
//                    return true;
//                }
//            }
//        }
        return true;

    }

    /**
     * <dd>Start Lock To AR_UPD_CUST_CD_LOCK_WRK
     * @param bizMsg NFCL0770CMsg
     * @param userInfo S21UserInfo
     * @return AR_UPD_CUST_CD_LOCK_WRKTMsg
     */
    private static AR_UPD_CUST_CD_LOCK_WRKTMsg chackCustCdLockForUpdate(NFCL0770CMsg bizMsg, S21UserInfo userInfo) {

        String glblCmpyCd = bizMsg.glblCmpyCd_H1.getValue();
        String sellToCustCd = bizMsg.payerCustCd.getValue();

        AR_UPD_CUST_CD_LOCK_WRKTMsg outMsg = null;
//        AR_UPD_CUST_CD_LOCK_WRKTMsg findMsg = findarUpdCustCdLockWrkInfoForUpdate(glblCmpyCd, sellToCustCd);
//
//        if (findMsg == null) {
//            outMsg = startInsCustCdlock(glblCmpyCd, sellToCustCd, userInfo.getUserId());
//        } else {
//            if (ZYPConstant.FLG_ON_Y.equals(findMsg.arLockUpFlg.getValue())) {
//                bizMsg.setMessageInfo("NFCM0166E", new String[] {findMsg.arLockUsrId.getValue() });
//                bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_N);
//            } else {
//                outMsg = startUpdCustCdlock(findMsg, userInfo.getUserId());
//            }
//
//        }

        return outMsg;

    }

    /**
     * <dd>Start Lock To AR_UPD_CUST_CD_LOCK_WRK
     * @param val
     * @return
     */
    private static AR_UPD_CUST_CD_LOCK_WRKTMsg findarUpdCustCdLockWrkInfoForUpdate(String glblCmpyCd, String sellToCustCd) {

        AR_UPD_CUST_CD_LOCK_WRKTMsg lockTmsg = new AR_UPD_CUST_CD_LOCK_WRKTMsg();

        lockTmsg.glblCmpyCd.setValue(glblCmpyCd);
        lockTmsg.updLockTrgtCustCd.setValue(sellToCustCd);

        AR_UPD_CUST_CD_LOCK_WRKTMsg outMsg = (AR_UPD_CUST_CD_LOCK_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(lockTmsg);

        return outMsg;

    }

    /**
     * <dd>Start Lock To AR_UPD_CUST_CD_LOCK_WRK
     * @param val
     * @return
     */
    private static AR_UPD_CUST_CD_LOCK_WRKTMsg startInsCustCdlock(String glblCmpyCd, String sellToCustCd, String userInfo) {

        AR_UPD_CUST_CD_LOCK_WRKTMsg inMsg = new AR_UPD_CUST_CD_LOCK_WRKTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.updLockTrgtCustCd.setValue(sellToCustCd);
        inMsg.arLockBizAppId.setValue(BIZ_APP_ID);
        inMsg.arLockUsrId.setValue(userInfo);
        inMsg.arLockFromDt.setValue(ZYPDateUtil.getCurrentSystemTime(FORMAT_DT));
        inMsg.arLockFromTm.setValue(ZYPDateUtil.getCurrentSystemTime(FORMAT_TM));
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

        inMsg.arLockBizAppId.setValue(BIZ_APP_ID);
        inMsg.arLockUsrId.setValue(userInfo);
        inMsg.arLockFromDt.setValue(ZYPDateUtil.getCurrentSystemTime(FORMAT_DT));
        inMsg.arLockFromTm.setValue(ZYPDateUtil.getCurrentSystemTime(FORMAT_TM));
        inMsg.arLockThruDt.clear();
        inMsg.arLockThruTm.clear();
        inMsg.arLockUpFlg.setValue(ZYPConstant.FLG_ON_Y);

        EZDTBLAccessor.update(inMsg);

        return inMsg;

    }

    /**
     * <dd>End unLock To AR_UPD_CUST_CD_LOCK_WRK
     * @param val
     * @return
     */
    private static void endUpdCustCdlock(AR_UPD_CUST_CD_LOCK_WRKTMsg inMsg) {

        inMsg.arLockThruDt.setValue(ZYPDateUtil.getCurrentSystemTime(FORMAT_DT));
        inMsg.arLockThruTm.setValue(ZYPDateUtil.getCurrentSystemTime(FORMAT_TM));
        inMsg.arLockUpFlg.setValue(ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.update(inMsg);

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @param cnt int
     * @return boolean
     */
    public static boolean isDepositDateAndTrxDate(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, int cnt) {

        // CCY Check
        if (ZYPCommonFunc.hasValue(globalMsg.A.no(cnt).dealCcyCd_A1)) {
            if (!bizMsg.ccyCd.getValue().equals(globalMsg.A.no(cnt).dealCcyCd_A1.getValue())) {
                globalMsg.A.no(cnt).arCustRefNum.setErrorInfo(1, "NFCM0195E", null);
                bizMsg.setMessageInfo("ZZM9037E", null);
                return false;
            }
        }

        // If data is Advance Receipt then not check relation of
        // DepositDate and TrxDate.
        if (isAdvance(bizMsg)) {
            return true;
        }

        //if(!NFCCmnMethod.isDepositDateAndTrxDate(bizMsg.glDt_H1.getValue(), globalMsg.A.no(cnt).arTrxDt.getValue())) {
        //    globalMsg.A.no(cnt).arCustRefNum.setErrorInfo(1, "NFCM0182E", null);
        //    bizMsg.setMessageInfo("ZZM9037E", null);
        //    return false;
        //}
        return true;
    }

    private static boolean isAdvance(NFCL0770CMsg bizMsg) {
        return (ZYPCommonFunc.hasValue(bizMsg.arRcptTrxTpCd) && AR_RCPT_TRX_TP_CD_ADVANCE.equals(bizMsg.arRcptTrxTpCd.getValue()));
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @param pageFrom int
     */
    public static void setCurrentPageOut(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, int pageFrom) {

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
        bizMsg.xxPageShowFromNum_H1.setValue(pageFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pageFrom + bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());
        clearErrorOtherPage(bizMsg, globalMsg);
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     */
    private static void clearErrorOtherPage(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg) {
        int fromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int thruNum = bizMsg.xxPageShowToNum.getValueInt() - 1;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            // Original Condition: if (fromNum <= i && i <= thruNum) => Do Nothing
            if (i < fromNum || thruNum < i) {
                globalMsg.A.no(i).xxDealApplyAmtNum_A1.clearErrorInfo();
                globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.clearErrorInfo();
                globalMsg.A.no(i).arCustRefNum.clearErrorInfo();
            }
        }
        bizMsg.setCommitSMsg(true);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0770CMsg
     * @param no int
     * @return BigDecimal
     */
    public static BigDecimal getPagenationFrom(NFCL0770CMsg bizMsg, int no) {

        int pageCnt = bizMsg.A.length();
        int pageNum = no / pageCnt;
        int retVal = pageNum * pageCnt + 1;

        return new BigDecimal(retVal);
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param arCustRefNum String
     * @return Map<String, Object>
     */
    public static Map<String, Object> setfindAddLineArTrxBal(NFCL0770CMsg bizMsg, String arCustRefNum) {

        HashMap<String, Object> inFindAddLineArTrxBalMap = new HashMap<String, Object>();

        inFindAddLineArTrxBalMap.put("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
        inFindAddLineArTrxBalMap.put("arTrxNum01", arCustRefNum);
        //inFindAddLineArTrxBalMap.put("cashDiscRngFromDt01", bizMsg.glDt_H1.getValue());
        //inFindAddLineArTrxBalMap.put("cashDiscRngThruDt01", bizMsg.glDt_H1.getValue());
        inFindAddLineArTrxBalMap.put("cashDiscRngFromDt01", CASH_DISC_SHCD_MIN);
        inFindAddLineArTrxBalMap.put("cashDiscRngThruDt01", CASH_DISC_SHCD_MIN);
        inFindAddLineArTrxBalMap.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
        inFindAddLineArTrxBalMap.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
        inFindAddLineArTrxBalMap.put("subSysCd", ZYPCodeDataUtil.getVarCharConstValue(AR_SUB_SYS_ID, bizMsg.glblCmpyCd_H1.getValue()));
        inFindAddLineArTrxBalMap.put("onlBatTpCd", ONL_BAT_TP_CD);

        return inFindAddLineArTrxBalMap;
    }

    /**
     * @param globalMsg NFCL0770SMsg
     * @param arCustRefNum String
     * @param index int
     * @return boolean
     */
    public static boolean checkArCustRefNum(NFCL0770SMsg globalMsg, String arCustRefNum, int index) {

        boolean ret = true;
        int existNo = -1;

        // already setting check
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            // already error exist
            if (globalMsg.A.no(i).arCustRefNum.isError()) {
                ret = false;
                continue;
            }
            if (arCustRefNum.equals(globalMsg.A.no(i).arCustRefNum.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    existNo = i;
                }
            }
        }

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            // already error exist
            if (globalMsg.A.no(i).arCustRefNum.isError()) {
                ret = false;
                continue;
            }
            if (arCustRefNum.equals(globalMsg.A.no(i).arCustRefNum.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    if (existNo < 0) {
                        if (i != index) {
                            globalMsg.A.no(i).arCustRefNum.setErrorInfo(1, "NFCM0180E");
                            ret = false;
                        }
                    } else {
                        globalMsg.A.no(i).arCustRefNum.setErrorInfo(1, "NFCM0180E");
                        ret = false;
                    }
                }
            } else {
                globalMsg.A.no(i).arCustRefNum.clearErrorInfo();
            }
        }

        if (ret) {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (arCustRefNum.equals(globalMsg.A.no(i).arTrxNum.getValue())) {
                    globalMsg.A.no(index).arCustRefNum.setErrorInfo(1, "NFCM0180E");
                    ret = false;
                }
            }
        }

        return ret;
    }

    /**
     * @param globalMsg NFCL0770SMsg
     * @return boolean
     */
    public static boolean checkArCustRefNumAll(NFCL0770SMsg globalMsg) {
        int cnt = 0;
        int errCnt = 0;
        String arCustRefNum = null;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            arCustRefNum = globalMsg.A.no(i).arCustRefNum.getValue();
            if (!ZYPCommonFunc.hasValue(arCustRefNum)) {
                continue;
            }
            if (globalMsg.A.no(i).arCustRefNum.isError()) {
                continue;
            }

            cnt = 0;
            for (int j = 0; j < globalMsg.A.getValidCount(); j++) {
                if (globalMsg.A.no(j).arCustRefNum.isError()) {
                    continue;
                }
                if (arCustRefNum.equals(globalMsg.A.no(j).arCustRefNum.getValue())) {
                    cnt++;
                }
            }
            if (cnt > 1) {
                for (int j = 0; j < globalMsg.A.getValidCount(); j++) {
                    // already error exist
                    if (globalMsg.A.no(j).arCustRefNum.isError()) {
                        continue;
                    }
                    if (arCustRefNum.equals(globalMsg.A.no(j).arCustRefNum.getValue())) {
                        if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(j).xxPgFlg_A1.getValue())) {
                            globalMsg.A.no(j).arCustRefNum.setErrorInfo(1, "NFCM0180E");
                            errCnt++;
                        }
                    } else {
                        globalMsg.A.no(j).arCustRefNum.clearErrorInfo();
                    }
                }
            }
        }
        if (errCnt > 0) {
            return false;
        }
        return true;
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @param arCustRefNum String
     * @return boolean
     */
    public static boolean checkArCustRefNumPage(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, String arCustRefNum) {
        int cnt = 0;
        int flgCnt = 0;
        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());

        for (int i = pagenationFrom; i < pagenationFrom + bizMsg.A.length(); i++) {
            // already error exist
            if (globalMsg.A.no(i).arCustRefNum.isError()) {
                continue;
            }

            if (arCustRefNum.equals(globalMsg.A.no(i).arCustRefNum.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    flgCnt++;
                }
                cnt++;
            }
        }
        flgCnt = flgCnt - cnt;
        if (flgCnt == 0) {
            return true;
        }
        if (cnt > 1) {
            for (int i = pagenationFrom; i < pagenationFrom + bizMsg.A.length(); i++) {
                // already error exist
                if (globalMsg.A.no(i).arCustRefNum.isError()) {
                    continue;
                }
                if (arCustRefNum.equals(globalMsg.A.no(i).arCustRefNum.getValue())) {
                    if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                        globalMsg.A.no(i).arCustRefNum.setErrorInfo(1, "NFCM0180E");
                    }
                } else {
                    globalMsg.A.no(i).arCustRefNum.clearErrorInfo();
                }
            }
            return false;
        }
        return true;
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @return String
     */
    public static boolean checkArCustRefNumAllPage(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg) {
        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        String arCustRefNum = null;

        for (int i = pagenationFrom; i < pagenationFrom + bizMsg.A.length(); i++) {
            arCustRefNum = globalMsg.A.no(i).arCustRefNum.getValue();
            if (!checkArCustRefNumPage(bizMsg, globalMsg, arCustRefNum)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @return boolean
     */
    public static boolean checkDetailErrorPage(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg) {

        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        for (int i = pagenationFrom; i < pagenationFrom + bizMsg.A.length(); i++) {

            if (globalMsg.A.no(i).arCustRefNum.isError()) {
                return false;
            }
            if (globalMsg.A.no(i).xxDealApplyAmtNum_A1.isError()) {
                return false;
            }
            if (globalMsg.A.no(i).xxDealApplyAdjAmtNum_A1.isError()) {
                return false;
            }

        }
        return true;
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @param index int
     */
    public static void setArTrxLine(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, int index) {

        NFCL0770CommonLogic.copyResultMsg(globalMsg, index, 0);

        BigDecimal dealApplyGrsAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(globalMsg.B.no(0).dealApplyGrsAmt_B2)) {
            dealApplyGrsAmt = globalMsg.B.no(0).dealApplyGrsAmt_B2.getValue();
        }

        BigDecimal dealApplyCrAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(globalMsg.B.no(0).dealApplyCrAmt_B2)) {
            dealApplyCrAmt = globalMsg.B.no(0).dealApplyCrAmt_B2.getValue();
        }

        BigDecimal dealApplyAdjAmtA3 = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(globalMsg.B.no(0).dealApplyAdjAmt_B3)) {
            dealApplyAdjAmtA3 = globalMsg.B.no(0).dealApplyAdjAmt_B3.getValue();
        }

        BigDecimal dealOrigGrsAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(globalMsg.B.no(0).dealOrigGrsAmt_B2)) {
            dealOrigGrsAmt = globalMsg.B.no(0).dealOrigGrsAmt_B2.getValue();
        }

        BigDecimal dealRmngBalGrsAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(globalMsg.B.no(0).dealRmngBalGrsAmt_B2)) {
            dealRmngBalGrsAmt = globalMsg.B.no(0).dealRmngBalGrsAmt_B2.getValue();
        }

        String arCashApplyStsCd = "";
        if (ZYPCommonFunc.hasValue(globalMsg.B.no(0).arCashApplyStsCd_B2)) {
            arCashApplyStsCd = globalMsg.B.no(0).arCashApplyStsCd_B2.getValue();
        }

        if (isCashDiscSchd(dealApplyGrsAmt, dealApplyCrAmt, dealApplyAdjAmtA3, dealOrigGrsAmt, dealRmngBalGrsAmt, arCashApplyStsCd)) {

            BigDecimal dealApplyAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(globalMsg.B.no(0).dealCashDiscAmt_B2)) {
                dealApplyAmt = dealApplyAmt.add(dealRmngBalGrsAmt).subtract(globalMsg.B.no(0).dealCashDiscAmt_B2.getValue());
            } else {
                dealApplyAmt = dealApplyAmt.add(dealRmngBalGrsAmt);
            }

            globalMsg.A.no(index).xxDealApplyAmtNum_A1.setValue(dealApplyAmt);

        } else {
            globalMsg.A.no(index).dealCashDiscAmt_A1.clear();
            globalMsg.A.no(index).cashDiscPct_A1.clear();
            globalMsg.A.no(index).xxDealApplyAmtNum_A1.setValue(dealRmngBalGrsAmt);
        }

        BigDecimal others = BigDecimal.ZERO;
        others = others.add(dealApplyCrAmt).add(dealApplyAdjAmtA3);
        globalMsg.A.no(index).dealApplyCrAmt.setValue(others);

        globalMsg.A.no(index).cashAppGlDt_A1.setValue(bizMsg.cashAppGlDt_BK.getValue());

        NFCL0770CommonLogic.setDiff(globalMsg.A.no(index), MODE_ENTRY);
        NFCL0770CommonLogic.setClearItemBySMsg(globalMsg.A.no(index));

        globalMsg.A.no(index).xxPgFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
        globalMsg.A.no(index).xxPgFlg_A2.setValue(ZYPConstant.FLG_OFF_N);
        globalMsg.A.no(index).xxPgFlg_A3.setValue(ZYPConstant.FLG_OFF_N);

    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @return boolean
     */
    public static boolean setArTrxAllLine(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg) {
        boolean ret = true;
        int errCnt = 0;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            // already error exist
            if (globalMsg.A.no(i).arCustRefNum.isError()) {
                continue;
            }

            // Entry from another window
            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A2.getValue())) {
                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A3.getValue())) {
                continue;
            }

            // already setting
            if (globalMsg.A.no(i).arCustRefNum.getValue().equals(globalMsg.A.no(i).arCustRefNum_BK.getValue())) {
                continue;
            }
            String arCustRefNum = globalMsg.A.no(i).arCustRefNum.getValue();
            if (!ZYPCommonFunc.hasValue(arCustRefNum)) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxTpCd)) {
                    globalMsg.A.no(i).clear();
                    globalMsg.A.no(i).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
                } else {
                    globalMsg.A.no(i).clear();
                    globalMsg.A.no(i).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
                    continue;
                }
            }

            if (ROW_STS.DELETE.toString().equals(isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()))) {
                continue;
            }

            ret = NFCL0770CommonLogic.getArTrxBalSearch(bizMsg, globalMsg, i, bizMsg.xxPageShowFromNum.getValueInt());
            if (!ret) {
                errCnt++;
            }
        }
        if (errCnt > 0) {
            return false;
        }
        return true;
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @return boolean
     */
    public static boolean isArCustRefNum(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg) {
        boolean ret = true;

        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        for (int i = pagenationFrom; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A1.getValue())) {
                continue;
            }

            // Entry from another window
            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A2.getValue())) {
                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxPgFlg_A3.getValue())) {
                continue;
            }

            String arCustRefNum = globalMsg.A.no(i).arCustRefNum.getValue();
            if (!ZYPCommonFunc.hasValue(arCustRefNum)) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxTpCd.getValue())) {
                    globalMsg.A.no(i).clear();
                    globalMsg.A.no(i).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
                }
                continue;
            }

            Map<String, Object> inFindAddLineArTrxBalMap = NFCL0770CommonLogic.setfindAddLineArTrxBal(bizMsg, arCustRefNum);
            globalMsg.B.clear();
            S21SsmEZDResult outTrxBalData = NFCL0770Query.getInstance().findAddLineArTrxBal(inFindAddLineArTrxBalMap, globalMsg);

            if (outTrxBalData.isCodeNotFound()) {
                globalMsg.A.no(i).arCustRefNum.setErrorInfo(1, "NFCM0179E");
                bizMsg.setMessageInfo("NFCM0059E", null);
                ret = false;
            }
            if (outTrxBalData.getQueryResultCount() > 1) {
                globalMsg.A.no(i).arCustRefNum.setErrorInfo(1, "NFCM0180E");
                bizMsg.setMessageInfo("NFCM0059E", null);
                ret = false;
            }

        }
        return ret;
    }

    /**
     * @param dealApplyGrsAmt BigDecimal
     * @param dealApplyCrAmt BigDecimal
     * @param dealApplyAdjAmtA3 BigDecimal
     * @param dealOrigGrsAmt BigDecimal
     * @param dealRmngBalGrsAmt BigDecimal
     * @param arCashApplyStsCd String
     * @return boolean
     */
    public static boolean isCashDiscSchd(BigDecimal dealApplyGrsAmt, BigDecimal dealApplyCrAmt, BigDecimal dealApplyAdjAmtA3, BigDecimal dealOrigGrsAmt, BigDecimal dealRmngBalGrsAmt, String arCashApplyStsCd) {

        boolean flg = false;
        if (NFCL0770CommonLogic.isZero(dealApplyGrsAmt) && NFCL0770CommonLogic.isZero(dealApplyCrAmt) && NFCL0770CommonLogic.isZero(dealApplyAdjAmtA3) && AR_CASH_APPLY_STS.UNAPPLIED.equals(arCashApplyStsCd)
                && 0 == dealOrigGrsAmt.compareTo(dealRmngBalGrsAmt)) {
            flg = true;
        }
        return flg;
    }

    /**
     * @param globalMsg NFCL0770SMsg
     * @param bizMsg NFCL0770CMsg
     */
    public static void reSetGlobalMsgToBizMsg(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg) {

        int i = 0;
        int srcNo = 0;
        int dstNo = 0;
        int cnt = 0;

        for (; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxTpCd.getValue())) {
                dstNo = i;
                for (srcNo = i + 1; srcNo < globalMsg.A.getValidCount(); srcNo++) {
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(srcNo).arTrxTpCd.getValue())) {
                        EZDMsg.copy(globalMsg.A.no(srcNo), null, globalMsg.A.no(dstNo), null);
                        globalMsg.A.no(srcNo).clear();
                        break;
                    }
                }
            }
        }

        cnt = 0;
        for (i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxTpCd.getValue())) {
                cnt++;
            } else {
                globalMsg.A.no(i).clear();
            }
        }
        globalMsg.A.setValidCount(cnt);
        setGlobalMsgToBizMsg(globalMsg, bizMsg, 0, 0);
    }

    /**
     * @param globalMsg NFCL0770SMsg
     * @param bizMsg NFCL0770CMsg
     * @param index int
     * @return Map<String, Object>
     */
    public static Map<String, Object> setfindAddLineArTrxBalByTrxNum(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg, int index) {

        HashMap<String, Object> inFindAddLineArTrxBalMap = new HashMap<String, Object>();

        inFindAddLineArTrxBalMap.put("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
        inFindAddLineArTrxBalMap.put("arTrxNum01", globalMsg.A.no(index).arCustRefNum.getValue());
        //inFindAddLineArTrxBalMap.put("cashDiscRngFromDt01", bizMsg.glDt_H1.getValue());
        //inFindAddLineArTrxBalMap.put("cashDiscRngThruDt01", bizMsg.glDt_H1.getValue());
        inFindAddLineArTrxBalMap.put("cashDiscRngFromDt01", CASH_DISC_SHCD_MIN);
        inFindAddLineArTrxBalMap.put("cashDiscRngThruDt01", CASH_DISC_SHCD_MIN);
        inFindAddLineArTrxBalMap.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
        inFindAddLineArTrxBalMap.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
        inFindAddLineArTrxBalMap.put("subSysCd", ZYPCodeDataUtil.getVarCharConstValue(AR_SUB_SYS_ID, bizMsg.glblCmpyCd_H1.getValue()));
        inFindAddLineArTrxBalMap.put("onlBatTpCd", ONL_BAT_TP_CD);
        inFindAddLineArTrxBalMap.put("exptFlg", bizMsg.exptFlg_H1.getValue());

        inFindAddLineArTrxBalMap.put("sellToCustCd", bizMsg.payerCustCd.getValue());
        inFindAddLineArTrxBalMap.put("dsAcctRelnTp1", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        inFindAddLineArTrxBalMap.put("dsAcctRelnTp2", DS_ACCT_RELN_TP.RELATED_ACCOUNT);

        return inFindAddLineArTrxBalMap;
    }

    /**
     * @param globalMsg NFCL0770SMsg
     * @param bizMsg NFCL0770CMsg
     * @param index int
     * @return Map<String, Object>
     */
    public static Map<String, Object> setfindAddLineArTrxBalByCustRefNum(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg, int index) {

        HashMap<String, Object> inFindAddLineArTrxBalMap = new HashMap<String, Object>();

        inFindAddLineArTrxBalMap.put("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
        inFindAddLineArTrxBalMap.put("arCustRefNum01", globalMsg.A.no(index).arCustRefNum.getValue());
        //inFindAddLineArTrxBalMap.put("cashDiscRngFromDt01", bizMsg.glDt_H1.getValue());
        //inFindAddLineArTrxBalMap.put("cashDiscRngThruDt01", bizMsg.glDt_H1.getValue());
        inFindAddLineArTrxBalMap.put("cashDiscRngFromDt01", CASH_DISC_SHCD_MIN);
        inFindAddLineArTrxBalMap.put("cashDiscRngThruDt01", CASH_DISC_SHCD_MIN);
        inFindAddLineArTrxBalMap.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
        inFindAddLineArTrxBalMap.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
        inFindAddLineArTrxBalMap.put("subSysCd", ZYPCodeDataUtil.getVarCharConstValue(AR_SUB_SYS_ID, bizMsg.glblCmpyCd_H1.getValue()));
        inFindAddLineArTrxBalMap.put("onlBatTpCd", ONL_BAT_TP_CD);
        inFindAddLineArTrxBalMap.put("sellToCustCd", bizMsg.payerCustCd.getValue());
        inFindAddLineArTrxBalMap.put("exptFlg", bizMsg.exptFlg_H1.getValue());

        return inFindAddLineArTrxBalMap;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @param index int
     * @param pageShowFromNum int
     * @return void
     */
    public static boolean getArTrxBalSearch(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, int index, int pageShowFromNum) {

        ZYPTableUtil.clear(globalMsg.D);

//        int no = 0;
//        no = index;
        int no = index;

        Map<String, Object> inMap = NFCL0770CommonLogic.setfindAddLineArTrxBalByTrxNum(globalMsg, bizMsg, index);

        S21SsmEZDResult outTrxBalData = NFCL0770Query.getInstance().findAddLineArTrxBalByTrxNum(inMap, globalMsg);

        if (outTrxBalData.isCodeNotFound()) {
            // next for CustRefNum
            inMap = NFCL0770CommonLogic.setfindAddLineArTrxBalByCustRefNum(globalMsg, bizMsg, index);
            outTrxBalData = NFCL0770Query.getInstance().findAddLineArTrxBalByCustRefNum(inMap, globalMsg);
            if (outTrxBalData.isCodeNotFound()) {
                // There is no transaction to match with TRX#/Cust
                // Ref# entered. Please try again or use Search.
                globalMsg.A.no(no).arCustRefNum.setErrorInfo(1, "NFCM0179E");
                return false;
            } else {
                if (outTrxBalData.getQueryResultCount() >= 2) {

                    // Multiple transactions exist to match with the
                    // Cust Ref# entered. Please specify TRX# or use
                    // Search.
                    globalMsg.A.no(no).arCustRefNum.setErrorInfo(1, "NFCM0180E");
                    return false;
                }
            }

        } else {
            if (!bizMsg.ccyCd.getValue().equals(globalMsg.D.no(0).dealCcyCd_D1.getValue())) {
                globalMsg.A.no(no).arCustRefNum.setErrorInfo(1, "NFCM0179E");
                return false;
            }
        }

        // check Input Duplicate
        int dataCount = globalMsg.A.length();

        for (int j = 0; j < dataCount; j++) {
            if (globalMsg.A.no(j).arTrxNum.getValue().equals(globalMsg.D.no(0).arTrxNum_D1.getValue())) {
                // The specified transaction has been already
                // selected.
                globalMsg.A.no(no).arCustRefNum.setErrorInfo(1, "NFCM0181E");
                return false;
            }
        }

        // Add Line Only
        if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(index).xxPgFlg_A1.getValue())) {

            globalMsg.A.no(no).arTrxDt.setValue(globalMsg.D.no(0).arTrxDt_D1.getValue());
            globalMsg.A.no(no).xxArCashApplyStsTxt.setValue(RECORD_STS.NEW.getValue());

            if (!NFCL0770CommonLogic.isDepositDateAndTrxDate(bizMsg, globalMsg, no)) {
                globalMsg.A.no(no).arTrxDt.clear();
                return false;
            }

            // set Data
            NFCL0770CommonLogic.copyResultMsgDtoA(globalMsg, no, 0);

            BigDecimal dealApplyGrsAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(globalMsg.D.no(0).dealApplyGrsAmt_D1)) {
                dealApplyGrsAmt = globalMsg.D.no(0).dealApplyGrsAmt_D1.getValue();
            }

            BigDecimal dealApplyCrAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(globalMsg.D.no(0).dealApplyCrAmt_D1)) {
                dealApplyCrAmt = globalMsg.D.no(0).dealApplyCrAmt_D1.getValue();
            }

            BigDecimal dealApplyAdjAmtA3 = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(globalMsg.D.no(0).dealApplyAdjAmt_D1)) {
                dealApplyAdjAmtA3 = globalMsg.D.no(0).dealApplyAdjAmt_D1.getValue();
            }

            BigDecimal dealOrigGrsAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(globalMsg.D.no(0).dealOrigGrsAmt_D1)) {
                dealOrigGrsAmt = globalMsg.D.no(0).dealOrigGrsAmt_D1.getValue();
            }

            BigDecimal dealRmngBalGrsAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(globalMsg.D.no(0).dealRmngBalGrsAmt_D1)) {
                dealRmngBalGrsAmt = globalMsg.D.no(0).dealRmngBalGrsAmt_D1.getValue();
            }

            String arCashApplyStsCd = "";
            if (ZYPCommonFunc.hasValue(globalMsg.D.no(0).arCashApplyStsCd_D1)) {
                arCashApplyStsCd = globalMsg.D.no(0).arCashApplyStsCd_D1.getValue();
            }

            if (isCashDiscSchd(dealApplyGrsAmt, dealApplyCrAmt, dealApplyAdjAmtA3, dealOrigGrsAmt, dealRmngBalGrsAmt, arCashApplyStsCd)) {

                BigDecimal dealApplyAmt = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(globalMsg.D.no(0).dealCashDiscAmt_D1)) {
                    dealApplyAmt = dealApplyAmt.add(dealRmngBalGrsAmt).subtract(globalMsg.D.no(0).dealCashDiscAmt_D1.getValue());
                } else {
                    dealApplyAmt = dealApplyAmt.add(dealRmngBalGrsAmt);
                }

                globalMsg.A.no(index).xxDealApplyAmtNum_A1.setValue(dealApplyAmt);

            } else {
                globalMsg.A.no(index).dealCashDiscAmt_A1.clear();
                globalMsg.A.no(index).cashDiscPct_A1.clear();
                globalMsg.A.no(index).xxDealApplyAmtNum_A1.setValue(dealRmngBalGrsAmt);
            }

            BigDecimal others = BigDecimal.ZERO;
            others = others.add(dealApplyCrAmt).add(dealApplyAdjAmtA3);
            globalMsg.A.no(index).dealApplyCrAmt.setValue(others);

            globalMsg.A.no(index).cashAppGlDt_A1.setValue(bizMsg.cashAppGlDt_BK.getValue());

            NFCL0770CommonLogic.setDiff(globalMsg.A.no(index), MODE_ENTRY);
            NFCL0770CommonLogic.setClearItemBySMsg(globalMsg.A.no(index));

            globalMsg.A.no(index).xxPgFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
            globalMsg.A.no(index).xxPgFlg_A2.setValue(ZYPConstant.FLG_OFF_N);
            globalMsg.A.no(index).xxPgFlg_A3.setValue(ZYPConstant.FLG_OFF_N);
        }

        ZYPTableUtil.clear(globalMsg.D);
        return true;
    }

    /**
     * @param globalMsg NFCL0770SMsg
     * @param datacountA int
     * @param datacountB int
     */
    public static void copyResultMsgDtoA(NFCL0770SMsg globalMsg, int datacountA, int datacountB) {

        globalMsg.A.no(datacountA).arTrxNum.setValue(globalMsg.D.no(datacountB).arTrxNum_D1.getValue());
        globalMsg.A.no(datacountA).grpInvNum.setValue(globalMsg.D.no(datacountB).grpInvNum_D1.getValue());
        globalMsg.A.no(datacountA).arTrxTpCd.setValue(globalMsg.D.no(datacountB).arTrxTpCd_D1.getValue());
        globalMsg.A.no(datacountA).cpoOrdNum.setValue(globalMsg.D.no(datacountB).cpoOrdNum_D1.getValue());
        globalMsg.A.no(datacountA).invDueDt.setValue(globalMsg.D.no(datacountB).invDueDt_D1.getValue());
        globalMsg.A.no(datacountA).glDt_A1.setValue(globalMsg.D.no(datacountB).glDt_D1.getValue());
        globalMsg.A.no(datacountA).custIssPoNum.setValue(globalMsg.D.no(datacountB).custIssPoNum_D1.getValue());
        globalMsg.A.no(datacountA).arCustRefNum.setValue(globalMsg.D.no(datacountB).arCustRefNum_D1.getValue());
        globalMsg.A.no(datacountA).arCustRefNum_BK.setValue(globalMsg.D.no(datacountB).arCustRefNum_D1.getValue());
        globalMsg.A.no(datacountA).billToCustCd.setValue(globalMsg.D.no(datacountB).billToCustCd_D1.getValue());
        globalMsg.A.no(datacountA).sellToCustCd.setValue(globalMsg.D.no(datacountB).sellToCustCd_D1.getValue());
        globalMsg.A.no(datacountA).dealOrigGrsAmt.setValue(globalMsg.D.no(datacountB).dealOrigGrsAmt_D1.getValue());
        globalMsg.A.no(datacountA).dealApplyGrsAmt.setValue(globalMsg.D.no(datacountB).dealApplyGrsAmt_D1.getValue());
        globalMsg.A.no(datacountA).dealApplyCrAmt.setValue(globalMsg.D.no(datacountB).dealApplyCrAmt_D1.getValue());
        globalMsg.A.no(datacountA).dealNetSlsAmt.setValue(globalMsg.D.no(datacountB).dealNetSlsAmt_D1.getValue());
        globalMsg.A.no(datacountA).dealFrtAmt.setValue(globalMsg.D.no(datacountB).dealFrtAmt_D1.getValue());
        globalMsg.A.no(datacountA).dealTaxAmt.setValue(globalMsg.D.no(datacountB).dealTaxAmt_D1.getValue());
        globalMsg.A.no(datacountA).dealRmngBalGrsAmt.setValue(globalMsg.D.no(datacountB).dealRmngBalGrsAmt_D1.getValue());
        globalMsg.A.no(datacountA).dealApplyAdjAmt_B3.setValue(globalMsg.D.no(datacountB).dealApplyAdjAmt_D1.getValue());
        globalMsg.A.no(datacountA).dealApplyAdjRsvdAmt_B3.setValue(globalMsg.D.no(datacountB).dealApplyAdjRsvdAmt_D1.getValue()); // Add 2018/01/15 S21_NA#20563
        globalMsg.A.no(datacountA).dealCashDiscAmt_A1.setValue(globalMsg.D.no(datacountB).dealCashDiscAmt_D1.getValue());
        globalMsg.A.no(datacountA).arCashDiscSchdSqNum_BK.setValue(globalMsg.D.no(datacountB).arCashDiscSchdSqNum_D1.getValue());
        globalMsg.A.no(datacountA).cashDiscPct_A1.setValue(globalMsg.D.no(datacountB).cashDiscPct_D1.getValue());
        globalMsg.A.no(datacountA).arTrxBalPk_BK.setValue(globalMsg.D.no(datacountB).arTrxBalPk_D1.getValue());
        globalMsg.A.no(datacountA).arCashApplyStsCd_BK.setValue(globalMsg.D.no(datacountB).arCashApplyStsCd_D1.getValue());
        globalMsg.A.no(datacountA).acctYrMth_BK.setValue(globalMsg.D.no(datacountB).acctYrMth_D1.getValue());
        globalMsg.A.no(datacountA).subSysCd_BK.setValue(globalMsg.D.no(datacountB).subSysCd_D1.getValue());
        globalMsg.A.no(datacountA).onlBatTpCd_BK.setValue(globalMsg.D.no(datacountB).onlBatTpCd_D1.getValue());
        globalMsg.A.no(datacountA).acctDt_BK.setValue(globalMsg.D.no(datacountB).acctDt_D1.getValue());
        globalMsg.A.no(datacountA).dealApplyAdjAmt_A3.setValue(globalMsg.D.no(datacountB).dealApplyAdjAmt_D1.getValue());
        globalMsg.A.no(datacountA).dealApplyAdjRsvdAmt_A3.setValue(globalMsg.D.no(datacountB).dealApplyAdjRsvdAmt_D1.getValue()); // Add 2018/01/15 S21_NA#20563
        globalMsg.A.no(datacountA).invTrxBalPk_BK.setValue(globalMsg.D.no(datacountB).arTrxBalPk_D1.getValue());
        globalMsg.A.no(datacountA).invTrxBalLastUpdTs_BK.setValue(globalMsg.D.no(datacountB).ezUpTime_D1.getValue());
        globalMsg.A.no(datacountA).invTrxBalTz_BK.setValue(globalMsg.D.no(datacountB).ezUpTimeZone_D1.getValue());
        globalMsg.A.no(datacountA).crNum_BK.setValue(globalMsg.D.no(datacountB).arTrxNum_D1.getValue());
        globalMsg.A.no(datacountA).crTrxBalPk_BK.setValue(globalMsg.D.no(datacountB).arTrxBalPk_D1.getValue());
        globalMsg.A.no(datacountA).crTrxBalLastUpdTs_BK.setValue(globalMsg.D.no(datacountB).ezUpTime_D1.getValue());
        globalMsg.A.no(datacountA).crTrxBalTz_BK.setValue(globalMsg.D.no(datacountB).ezUpTimeZone_D1.getValue());
        globalMsg.A.no(datacountA).arTrxDt.setValue(globalMsg.D.no(datacountB).arTrxDt_D1.getValue());
        globalMsg.A.no(datacountA).cashDiscTermCd_A1.setValue(globalMsg.D.no(datacountB).cashDiscTermCd_D1.getValue());
        globalMsg.A.no(datacountA).pmtTermCd_A1.setValue(globalMsg.D.no(datacountB).pmtTermCd_D1.getValue());
        globalMsg.A.no(datacountA).pmtTermCashDiscCd_A1.setValue(globalMsg.D.no(datacountB).pmtTermCashDiscCd_D1.getValue());
        globalMsg.A.no(datacountA).tocCd_A1.setValue(globalMsg.D.no(datacountB).tocCd_D1.getValue());
        globalMsg.A.no(datacountA).coaProdCd_BK.setValue(globalMsg.D.no(datacountB).coaProdCd_D1.getValue());
        globalMsg.A.no(datacountA).dealCcyCd_A1.setValue(globalMsg.D.no(datacountB).dealCcyCd_D1.getValue());

        globalMsg.A.no(datacountA).billToCustAcctCd_A1.setValue(globalMsg.D.no(datacountB).billToCustAcctCd_D1.getValue());
        globalMsg.A.no(datacountA).dsAcctNm_A1.setValue(globalMsg.D.no(datacountB).dsAcctNm_D1.getValue());

    }

    /**
     * @return String[]
     */
    public static final String[] getCsvHeader() {
        return CSV_HEADER.clone();
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @return boolean
     */
    public static boolean isTotalAmtHasToBeZeroMode(NFCL0770CMsg bizMsg) {
        //if (BigDecimal.ZERO.compareTo(bizMsg.dealRcptAmt.getValue()) == 0) {
        //    return true;
        //}
        //if (!(ZYPConstant.FLG_ON_Y.equals(bizMsg.exptFlg_H1.getValue()) && AR_RCPT_TRX_TP_CD_ADVANCE.equals(bizMsg.arRcptTrxTpCd.getValue()))) {
        //    return true;
        //}
        return false;
    }

    /**
     * @param glblCmpyCd String
     * @param applyIntfcWrkCnt int
     * @return boolean (true:execute Api by Online/false:execute Api
     * by Batch)
     */
    public static boolean isApiExecuteOnline(String glblCmpyCd, int applyIntfcWrkCnt) {

        BigDecimal limitCnt = ZYPCodeDataUtil.getNumConstValue(KEY_NAME_OF_AR_CASH_APP_ON_BAT_CNT, glblCmpyCd);

        return !(limitCnt.compareTo(new BigDecimal(applyIntfcWrkCnt)) < 0);

    }

    /**
     * @param glblCmpyCd Global Company Code
     * @param globalMsg Global Message
     * @return boolean (true:execute Api by Online/false:execute Api
     * by Batch)
     */
    public static boolean isBalanceCancel(String glblCmpyCd, NFCL0770SMsg globalMsg) {
        //
        return true;
    }

    /**
     * @param chk Check flag
     * @param status status
     * @return String result
     */
    public static String isStatusOfRow(String chk, String status) {
        if (ZYPConstant.FLG_ON_Y.equals(chk)) {
            if (status.equals(RECORD_STS.NEW.getValue())) {
                return ROW_STS.CASH_APPLICATION.toString();
            } else {
                return ROW_STS.NOTHING.toString();
            }
        } else {
            if (status.equals(RECORD_STS.NEW.getValue())) {
                return ROW_STS.DELETE.toString();
            } else if (status.equals(RECORD_STS.APPLIED.getValue())) {
                return ROW_STS.CANCEL.toString();
            } else {
                return ROW_STS.NOTHING.toString();
            }
        }
    }

    /**
     * <pre>
     * @param bizMsg Biz Message
     * @param globalMsg Global Message
     * @return boolean
     * </pre>
     */
    public static boolean isCancelBalanceZero(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg) {

        BigDecimal dmRmngAmt = bizMsg.dealRcptRmngBalAmt.getValue();
        BigDecimal dmApplyAmt = BigDecimal.ZERO;
        boolean blCFlg = false;
        //
        for (int iCnt = 0; iCnt < globalMsg.A.getValidCount(); iCnt++) {
            //
            if (globalMsg.A.no(iCnt).xxArCashApplyStsTxt.getValue().equals(RECORD_STS.APPLIED.toString())) {
                if (ROW_STS.CANCEL.equals(isStatusOfRow(globalMsg.A.no(iCnt).xxChkBox.getValue(), globalMsg.A.no(iCnt).xxArCashApplyStsTxt.getValue()))) {
                    dmApplyAmt = dmApplyAmt.add(globalMsg.A.no(iCnt).xxDealApplyAmtNum_A1.getValue());
                    blCFlg = true;
                }
            }
        }

        //Check Remaining by Cancel
        if (blCFlg && BigDecimal.ZERO.compareTo((dmRmngAmt.add(dmApplyAmt))) == 0) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * </pre
     * @param bizMsg Biz Message
     * @param globalMsg Global Message
     */
    public static void setCalc(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg) {

        NFCL0770CommonLogic.setPage(globalMsg, bizMsg);

        BigDecimal applyBalance = BigDecimal.ZERO;
//        BigDecimal onAccBalance = BigDecimal.ZERO;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).dealRmngBalGrsAmt)) {
                globalMsg.A.no(i).dealRmngBalGrsAmt.setValue(BigDecimal.ZERO);
            }

            // Calculation for Remaining Balance
            if (isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()).equals(ROW_STS.NOTHING.toString())
                    || isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()).equals(ROW_STS.CASH_APPLICATION.toString())) {

                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    applyBalance = applyBalance.add(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
                } else {
                    globalMsg.A.no(i).xxDealApplyAmtNum_A1.setValue(BigDecimal.ZERO);
                }
            }
        }

        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());

        pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL0770CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        //Calculation
        bizMsg.xxTotAmt_H0.setValue(applyBalance);
        bizMsg.xxTotAmt_H1.setValue(bizMsg.dealRcptAmt.getValue().subtract(applyBalance).subtract(bizMsg.dealRfAmt.getValue()));
        bizMsg.xxOnAcctAmt.setValue(BigDecimal.ZERO);
    }

    /**
     * <pre>
     * @param bizMsg Biz Message
     * @param globalMsg Global Message
     * @param listAddRow adding row
     * </pre>
     */
    public static void setCalcFor5050(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, ArrayList<Integer> listAddRow) {

        BigDecimal applyBalance = BigDecimal.ZERO;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).dealRmngBalGrsAmt)) {
                globalMsg.A.no(i).dealRmngBalGrsAmt.setValue(BigDecimal.ZERO);
            }

            // Calculation for Remaining Balance
            if ((isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()).equals(ROW_STS.NOTHING.toString())
                    || isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()).equals(ROW_STS.CASH_APPLICATION.toString()))) {

                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    if (!listAddRow.contains(i)) {
                        // Base Row
                        applyBalance = applyBalance.add(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
                    }
                } else {
                    globalMsg.A.no(i).xxDealApplyAmtNum_A1.setValue(BigDecimal.ZERO);
                }
            }
        }

        //Calculation for Last Row
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (listAddRow.contains(i)) {
                globalMsg.A.no(i).xxDealApplyAmtNum_A1.setValue(globalMsg.A.no(i).dealRmngBalGrsAmt.getValue());
                applyBalance = applyBalance.add(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
            }
        }

        //Calculation
        bizMsg.xxTotAmt_H0.setValue(applyBalance);
        bizMsg.xxTotAmt_H1.setValue(bizMsg.dealRcptAmt.getValue().subtract(applyBalance).subtract(bizMsg.dealRfAmt.getValue()));
    }

    /**
     * @param globalMsg Global Message
     */
    public static void delCancelRecords(NFCL0770SMsg globalMsg) {
        // Delete the Records from Business Message
        for (int i = globalMsg.A.getValidCount() - 1; i == 0; i--) {
            //
            if (isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()).equals(ROW_STS.CANCEL.toString())) {
                globalMsg.A.no(i).clear();
            }
        }
    }

    /**
     * @param globalMsg global message
     * @return list count
     */
    public static int getLastInvRowCnt(NFCL0770SMsg globalMsg) {
        int iLastCnt = 0;
        for (int iCnt = 0; iCnt < globalMsg.A.getValidCount(); iCnt++) {
            if ((isStatusOfRow(globalMsg.A.no(iCnt).xxChkBox.getValue(), globalMsg.A.no(iCnt).xxArCashApplyStsTxt.getValue()).equals(ROW_STS.CASH_APPLICATION.toString())
                    || isStatusOfRow(globalMsg.A.no(iCnt).xxChkBox.getValue(), globalMsg.A.no(iCnt).xxArCashApplyStsTxt.getValue()).equals(ROW_STS.NOTHING.toString()))
                    && ZYPCommonFunc.hasValue(globalMsg.A.no(iCnt).arCustRefNum)
                    && ZYPCommonFunc.hasValue(globalMsg.A.no(iCnt).xxDealApplyAmtNum_A1)) {
                 iLastCnt++;
            }
        }
        return iLastCnt;
    }

    /**
     * @param bizMsg Biz Message
     * @param globalMsg Global Message
     * @param glblCmpyCd Global Company Code
     * @return page count
     */
    public static int chkSubmitCommon(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, String glblCmpyCd) {

        //
        int turnCnt = bizMsg.A.length();
        int iPageCnt = 0;
        int iPageCntPre = 0;
        boolean blErr = true;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            iPageCnt = ((i + 1) / turnCnt) + 1;

            if (iPageCnt != iPageCntPre) {
                return iPageCnt;
            }

            iPageCntPre = iPageCnt;
            if (!(ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxChkBox) && globalMsg.A.no(i).xxChkBox.getValue().equals("Y")
                    && ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxArCashApplyStsTxt) && RECORD_STS.NEW.getValue().equals(globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()))) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxDealApplyAmtNum_A1) && 0 == globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()) {
                globalMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0038E");
                //globalMsg.addCheckItem(globalMsg.A.no(i).xxDealApplyAmtNum_A1);
                bizMsg.setMessageInfo("ZZM9037E", null);
                blErr = false;
            }

            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxModeInd_BK)) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    if (0 < globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()) {
                        if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).dealRmngBalGrsAmt) && 0 > globalMsg.A.no(i).dealRmngBalGrsAmt.getValue().signum()) {
                            globalMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0026E");
                            //globalMsg.addCheckItem(globalMsg.A.no(i).xxDealApplyAmtNum_A1);
                            bizMsg.setMessageInfo("ZZM9037E", null);
                            blErr = false;
                        }
                    } else if (0 > globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()) {
                        if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).dealRmngBalGrsAmt) && 0 < globalMsg.A.no(i).dealRmngBalGrsAmt.getValue().signum()) {
                            globalMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0026E");
                            //globalMsg.addCheckItem(globalMsg.A.no(i).xxDealApplyAmtNum_A1);
                            bizMsg.setMessageInfo("ZZM9037E", null);
                            blErr = false;
                        }
                    }
                }

            } else if (DETAIL_MODE_DEDUCTION.equals(globalMsg.A.no(i).xxModeInd_BK.getValue())) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    if (0 == globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()) {
                        globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0026E");
                        //globalMsg.addCheckItem(globalMsg.A.no(i).xxChkBox);
                        bizMsg.setMessageInfo("ZZM9037E", null);
                        blErr = false;
                    }
                }

            } else if (DETAIL_MODE_ADJUSTMENT.equals(globalMsg.A.no(i).xxModeInd_BK.getValue())) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    if (0 == globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()) {
                        globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0026E");
                        //globalMsg.addCheckItem(globalMsg.A.no(i).xxChkBox);
                        bizMsg.setMessageInfo("ZZM9037E", null);
                        blErr = false;
                    }
                }
            } else if ("1".equals(globalMsg.A.no(i).xxModeInd_BK.getValue())) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxDealApplyAmtNum_A1)) {
                    if (0 == globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue().signum()) {
                        globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0026E");
                        //globalMsg.addCheckItem(globalMsg.A.no(i).xxChkBox);
                        bizMsg.setMessageInfo("ZZM9037E", null);
                        blErr = false;
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).cashAppGlDt_A1)) {

                String outGetBatProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);

                if (0 < ZYPDateUtil.compare(globalMsg.A.no(i).cashAppGlDt_A1.getValue(), outGetBatProcDate)) {
                    globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0040E");
                    //globalMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                    bizMsg.setMessageInfo("ZZM9037E", null);
                    blErr = false;
                }

                if (0 > ZYPDateUtil.compare(globalMsg.A.no(i).cashAppGlDt_A1.getValue(), bizMsg.glDt_H1.getValue())) {
                    globalMsg.A.no(i).xxChkBox.setErrorInfo(1, "NFCM0088E");
                    //globalMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
                    bizMsg.setMessageInfo("ZZM9037E", null);
                    blErr = false;
                }
            }

            if (!isBalanceCheck(globalMsg.A.no(i))) {
                bizMsg.setMessageInfo("NFCM0066E", null);
                blErr = false;
            }
        }

        if (blErr) {
            return 0;
        }

        return iPageCnt;
    }

    /**
     * @param msg NFCL0770_ABMsg
     * @return boolean
     */
    public static boolean isBalanceCheck(NFCL0770_ASMsg msg) {

        boolean flg = true;

        if (ZYPCommonFunc.hasValue(msg.dealRmngBalGrsAmt)) {

            BigDecimal balance = BigDecimal.ZERO;

            if (ZYPCommonFunc.hasValue(msg.xxDealApplyAmtNum_A1)) {
                balance = balance.add(msg.xxDealApplyAmtNum_A1.getValue());
            }

            if (ZYPCommonFunc.hasValue(msg.dealCashDiscAmt_A1)) {
                balance = balance.add(msg.dealCashDiscAmt_A1.getValue());
            }

            if (ZYPCommonFunc.hasValue(msg.xxDealApplyAdjAmtNum_A1)) {
                balance = balance.add(msg.xxDealApplyAdjAmtNum_A1.getValue());
            }

            if (0 < msg.dealRmngBalGrsAmt.getValue().signum()) {
                if (0 > msg.dealRmngBalGrsAmt.getValue().compareTo(balance)) {
                    msg.xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0065E");
                    flg = false;
                }

            } else if (0 > msg.dealRmngBalGrsAmt.getValue().signum()) {
                if (0 < msg.dealRmngBalGrsAmt.getValue().compareTo(balance)) {
                    msg.xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0065E");
                    flg = false;
                }
            }
        }

        return flg;
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     * @param usrId User ID
     */
    public static void chckAdjApvlLimit(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, String usrId) {
        boolean existAdj = false;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (globalMsg.A.no(i).xxChkBox.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && globalMsg.A.no(i).xxModeInd_BK.getValue().equals(DETAIL_MODE_ADJUSTMENT)) {
                existAdj = true;
                break;
            }
        }
        if (existAdj) {
            // START 2023/07/03 S.Fuijta [QC#60397,MOD]
            // AR_ADJ_APVL_LIMITTMsg arAdjApvlLimitTMsg = new AR_ADJ_APVL_LIMITTMsg();
            // arAdjApvlLimitTMsg.setSQLID("001");
            // arAdjApvlLimitTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
            // arAdjApvlLimitTMsg.setConditionValue("arAdjApvlUsrId01", usrId);
            // arAdjApvlLimitTMsg.setConditionValue("arAdjCatgCd01", AR_ADJ_CATG.WRITE_OFF);
            // arAdjApvlLimitTMsg.setConditionValue("apvlLimitActvFlg01", ZYPConstant.FLG_ON_Y);
            HR_TTL_APVL_LIMITTMsg hrTtlApvlLimitTMsg = new HR_TTL_APVL_LIMITTMsg();
            String hrTtlNm = getHrTtlNm(bizMsg.glblCmpyCd_H1.getValue(), usrId);
            // START 2023/08/02 S.Fuijta [QC#60397,ADD]
            if (!ZYPCommonFunc.hasValue(hrTtlNm)) {
                bizMsg.setMessageInfo("NFCM0815E");
                return;
            }
            // END 2023/08/02 S.Fuijta [QC#60397,ADD]
            BigDecimal hrTtlApvlLimitPk = getHrTtlApvlLimitPk(bizMsg.glblCmpyCd_H1.getValue(), hrTtlNm);
            // START 2023/08/02 S.Fuijta [QC#60397,MOD]
            hrTtlApvlLimitTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
            // hrTtlApvlLimitTMsg.hrTtlNm.setValue(hrTtlNm);
            hrTtlApvlLimitTMsg.hrTtlApvlLimitPk.setValue(hrTtlApvlLimitPk);
            // hrTtlApvlLimitTMsg.apvlLimitActvFlg.setValue(ZYPConstant.FLG_ON_Y);
            // END 2023/08/02 S.Fuijta [QC#60397,MOD]
            // AR_ADJ_APVL_LIMITTMsgArray arAdjApvlLimitArray = (AR_ADJ_APVL_LIMITTMsgArray) EZDTBLAccessor.findByCondition(arAdjApvlLimitTMsg);
            hrTtlApvlLimitTMsg = (HR_TTL_APVL_LIMITTMsg) EZDTBLAccessor.findByKey(hrTtlApvlLimitTMsg);
            // if (arAdjApvlLimitArray == null || arAdjApvlLimitArray.getValidCount() == 0) {
            if (hrTtlApvlLimitTMsg == null) {
                // bizMsg.setMessageInfo("NFCM0845E", new String[] {"AR_ADJ_APVL_LIMIT", usrId });
                bizMsg.setMessageInfo("NFCM0845E", new String[] {"HR_TTL_APVL_LIMIT", usrId });
            // END 2023/07/03 S.Fuijta [QC#60397,MOD]
                return;
            }

            boolean errorFlag = false;
            BigDecimal totalAdjAmount = BigDecimal.ZERO;
            // START 2023/07/03 S.Fuijta [QC#60397,ADD]
            BigDecimal totalAdjAmountAbs = BigDecimal.ZERO;
            // END 2023/07/03 S.Fuijta [QC#60397,ADD]
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (globalMsg.A.no(i).xxChkBox.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && globalMsg.A.no(i).xxModeInd_BK.getValue().equals(DETAIL_MODE_ADJUSTMENT)) {
                    BigDecimal adjAmount = globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue();
                    // START 2023/07/03 S.Fuijta [QC#60397,MOD]
                    BigDecimal adjAmountAbs = adjAmount.abs();
                    // if (adjAmount.compareTo(arAdjApvlLimitArray.no(0).apvlLimitFromAmt.getValue()) < 0 || adjAmount.compareTo(arAdjApvlLimitArray.no(0).apvlLimitToAmt.getValue()) > 0) {
                    if (adjAmountAbs.compareTo(hrTtlApvlLimitTMsg.apvlLimitAmt.getValue()) > 0) {
                        globalMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, "NFCM0084E");
                        bizMsg.setMessageInfo("ZZM9037E");
                        errorFlag = true;
                    }
                    totalAdjAmount = totalAdjAmount.add(adjAmount);
                    // START 2023/08/02 S.Fuijta [QC#60397,ADD]
                    totalAdjAmountAbs = totalAdjAmount.abs();
                    // END 2023/08/02 S.Fuijta [QC#60397,ADD]
                }
            }
            if (errorFlag == false) {
                // if (totalAdjAmount.compareTo(arAdjApvlLimitArray.no(0).apvlLimitFromAmt.getValue()) < 0 || totalAdjAmount.compareTo(arAdjApvlLimitArray.no(0).apvlLimitToAmt.getValue()) > 0) {
                if (totalAdjAmountAbs.compareTo(hrTtlApvlLimitTMsg.apvlLimitAmt.getValue()) > 0) {
                    // END 2023/07/03 S.Fuijta [QC#60397,MOD]
                    bizMsg.setMessageInfo("NFCM0084E");
                }
            }
        }
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @param arAdjTpCd String
     * @return String
     */
    public static String getArAdjTpNm(NFCL0770CMsg bizMsg, String arAdjTpCd) {

        AR_ADJ_TPTMsg tMsg = new AR_ADJ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.arAdjTpCd, arAdjTpCd);

        AR_ADJ_TPTMsg outMsg = (AR_ADJ_TPTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (null != outMsg) {
            return outMsg.arAdjTpNm.getValue();
        }
        return null;
    }

    // START 2023/07/03 S.Fujita [QC#60397,ADD]
    /**
     * 
     * @param glblCmpyCd Global Company Code
     * @param usrId User ID
     * @return HR Title Name
     */
    public static String getHrTtlNm(String glblCmpyCd, String usrId) {

        if (ZYPCommonFunc.hasValue(usrId)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("usrId", usrId);
            // START 2023/08/02 S.Fujita [QC#60397,ADD]
            ssmParam.put("catgCd", AR_ADJ_CATG.WRITE_OFF);
            // END 2023/08/02 S.Fujita [QC#60397,ADD]
            String hrTtlNm = NFCL0770Query.getInstance().getHrTtlNm(null, ssmParam);
            if (ZYPCommonFunc.hasValue(hrTtlNm)); {
                return  hrTtlNm;
            }
        }
        return null;
    }

    /**
     * 
     * @param glblCmpyCd Global Company Code
     * @param hrTtlNm HR Title Name
     * @return HR Title Approval Limit PK
     */
    public static BigDecimal getHrTtlApvlLimitPk(String glblCmpyCd, String hrTtlNm) {

        if (ZYPCommonFunc.hasValue(hrTtlNm)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("hrTtlNm", hrTtlNm);
            // START 2023/08/02 S.Fujita [QC#60397,ADD]
            ssmParam.put("catgCd", AR_ADJ_CATG.WRITE_OFF);
            // END 2023/08/02 S.Fujita [QC#60397,ADD]
            BigDecimal hrTtlApvlLimitPk = NFCL0770Query.getInstance().getHrTtlApvlLimitPk(null, ssmParam);
            if (ZYPCommonFunc.hasValue(hrTtlApvlLimitPk)); {
                return  hrTtlApvlLimitPk;
            }
        }
        return null;
    }

    // END 2023/07/03 S.Fujita [QC#60397,ADD]

    /**
     * Is AR_CASH_APP
     * @param glblCmpyCd String
     * @param arTrxNum   String
     * @param rcptNum    String
     * @return boolean value
     */
    public static boolean isArCashApp(String glblCmpyCd, String arTrxNum, String rcptNum) {
        if (ZYPCommonFunc.hasValue(glblCmpyCd) && ZYPCommonFunc.hasValue(arTrxNum) && ZYPCommonFunc.hasValue(rcptNum)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("arTrxNum", arTrxNum);
            ssmParam.put("onAcct", AR_TRX_TP.ON_ACCOUNT);
            ssmParam.put("rcptNum", rcptNum);
            S21SsmEZDResult data = NFCL0770Query.getInstance().isArCachApp(ssmParam);
            if (data.isCodeNormal()) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkApplyingRefund
     * @param bizMsg    NFCL0770CMsg
     * @param globalMsg NFCL0770SMsg
     */
    public static void checkApplyingRefund(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg) {
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ROW_STS.CASH_APPLICATION.toString().equals(isStatusOfRow(globalMsg.A.no(i).xxChkBox.getValue(), globalMsg.A.no(i).xxArCashApplyStsTxt.getValue()))) {
                if (AR_TRX_TP.CREDIT_MEMO.equals(globalMsg.A.no(i).arTrxTpCd.getValue()) || AR_TRX_TP.ON_ACCOUNT.equals(globalMsg.A.no(i).arTrxTpCd.getValue())) {
                    String glblCmpyCd = bizMsg.glblCmpyCd_H1.getValue();
                    String arTrxNum = globalMsg.A.no(i).arTrxNum.getValue();
                    BigDecimal applyingRefundCount = (BigDecimal) NFCL0770Query.getInstance().getApplyingRefundCount(glblCmpyCd, arTrxNum).getResultObject();
                    if (ZYPCommonFunc.hasValue(applyingRefundCount) && BigDecimal.ZERO.compareTo(applyingRefundCount) < 0) {
                        globalMsg.A.no(i).arCustRefNum.setErrorInfo(1, "NFCM0896E");
                        bizMsg.setMessageInfo("ZZM9037E");
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * get Bill To Customer Account
     * @param bizMsg Biz Message
     */
    public static void searchAddressForBillToCustomerAccount(NFCL0770CMsg bizMsg) {

        SELL_TO_CUSTTMsgArray outSellToCustTMsg = findBillToAcctCust(bizMsg);

        if (outSellToCustTMsg.getValidCount() == 0) {
            bizMsg.payerCustCd.setErrorInfo(1, "NFCM0502E", new String[] {"Customer" });
            return;
        }
        bizMsg.dsAcctNm.setValue(outSellToCustTMsg.no(0).dsAcctNm.getValue());
    }

    /**
     * @param bizMsg NFCL0770CMsg
     * @return SELL_TO_CUSTTMsgArray
     */
    public static SELL_TO_CUSTTMsgArray findBillToAcctCust(NFCL0770CMsg bizMsg) {

        SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
        inTMsg.setConditionValue("sellToCustCd01", bizMsg.payerCustCd.getValue());
        inTMsg.setMaxCount(0);
        inTMsg.setSQLID("001");
        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
    }

    /**
     * <pre>
     * Calculation Gross Amount.
     * @param bizMsg Biz Message.
     * @param glblMsg Global Message
     * </pre>
     */
    public static void calcGrossAmount(NFCL0770CMsg bizMsg, NFCL0770SMsg glblMsg) {

        bizMsg.setCommitSMsg(true);

        BigDecimal balance = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NFCL0770_ACMsg bizLineMsg = bizMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(bizLineMsg.dealRmngBalGrsAmt)) {
                if (ZYPCommonFunc.hasValue(bizLineMsg.xxDealApplyAmtNum_A1)) {
                    balance = balance.add(bizLineMsg.xxDealApplyAmtNum_A1.getValue());
                }
            } else {
                bizLineMsg.dealRmngBalGrsAmt.setValue(BigDecimal.ZERO);
            }
        }
        // Calculation
        NFCL0770CommonLogic.setPage(glblMsg, bizMsg);

        for (int iCnt = 0; iCnt < glblMsg.A.getValidCount(); iCnt++) {
            NFCL0770_ASMsg glblLineMsg = glblMsg.A.no(iCnt);
            if (ZYPCommonFunc.hasValue(glblLineMsg.xxChkBox) || glblLineMsg.xxChkBox.getValue().equals("Y")) {
                if (ZYPCommonFunc.hasValue(glblLineMsg.xxDealApplyAmtNum_A1)) {
                    if (glblLineMsg.arTrxTpCd.getValue().equals(NFCConst.CST_AR_TRX_TP_CD_INVOICE)
                           || glblLineMsg.arTrxTpCd.getValue().equals(NFCConst.CST_AR_TRX_TP_CD_DEBITMEMO)
                           || glblLineMsg.arTrxTpCd.getValue().equals(NFCConst.CST_AR_TRX_TP_CD_DEDUCTION)) {
                        balance = balance.subtract(glblLineMsg.xxDealApplyAmtNum_A1.getValue());
                    } else {
                        balance = balance.add(glblLineMsg.xxDealApplyAmtNum_A1.getValue());
                    }

                    if (ZYPCommonFunc.hasValue(glblLineMsg.dealRmngBalGrsAmt)
                            && BigDecimal.ZERO.compareTo(glblLineMsg.dealRmngBalGrsAmt.getValue()) != 0) {
                        glblLineMsg.xxDtlDiffAmt_A1.setValue(glblLineMsg.dealRmngBalGrsAmt.getValue().subtract(glblLineMsg.xxDealApplyAmtNum_A1.getValue()));
                    } else {
                        glblLineMsg.xxDtlDiffAmt_A1.setValue(BigDecimal.ZERO);
                    }
                }
            }
        }

        //Calculation
        if (!ZYPCommonFunc.hasValue(bizMsg.dealRcptAmt)) {
            bizMsg.dealRcptAmt.setValue(BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.dealRfAmt)) {
            bizMsg.dealRfAmt.setValue(BigDecimal.ZERO);
        }
        bizMsg.xxTotAmt_H1.setValue(bizMsg.dealRcptAmt.getValue().subtract(balance).subtract(bizMsg.dealRfAmt.getValue()));

        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());

        pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL0770CommonLogic.setGlobalMsgToBizMsg(glblMsg, bizMsg, pagenationFrom, pagenationFrom);

        NFCL0770CommonLogic.setCalc(bizMsg, glblMsg);
    }

    /**
     * <pre>
     * Get GLBL_CMPYTMsg
     * @param bizMsg Biz. Message (Using only glblCmpyCd_H1
     * @return GLBL_CMPYTMsg
     * </pre>
     */
    public static GLBL_CMPYTMsg getGlblCmpy(NFCL0770CMsg bizMsg) {

        GLBL_CMPYTMsg queryTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(queryTMsg.glblCmpyCd, bizMsg.glblCmpyCd_H1);

        return (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(queryTMsg);
    }
}
