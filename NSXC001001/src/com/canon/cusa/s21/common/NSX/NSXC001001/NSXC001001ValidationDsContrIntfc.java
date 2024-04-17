/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.CTAC_PSNTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.DS_ACTL_CNT_INTFCTMsg;
import business.db.DS_ADDL_CHRG_INTFCTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_MDLTMsg;
import business.db.DS_XS_COPY_INTFCTMsg;
import business.db.MDSETMsg;
import business.db.MTR_LBTMsg;
import business.db.PRC_ALLOC_INTFCTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.TOCTMsg;
import business.parts.NSXC001001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PGM_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Validation DS_CONTR_INTFC
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         Y.Tsuchimoto    Create          N/A(Template only)
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/02/04   Hitachi         T.Iwamoto       Update          QC#3995
 * 2016/03/11   Hitachi         T.Iwamoto       Update          QC#5298
 * 2016/03/28   Hitachi         Y.Tsuchimoto    Update          QC#5822
 * 2016/04/08   Hitachi         T.Iwamoto       Update          QC#5822
 * 2016/05/17   Hitachi         T.Iwamoto       Update          QC#3771
 * 2016/05/25   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/06/13   Hitachi         T.Iwamoto       Update          QC#477
 * 2016/11/14   Hitachi         A.Kohinata      Update          QC#14934
 * 2017/02/28   Hitachi         K.Kitachi       Update          QC#17769
 * 2017/05/18   Hitachi         K.Kitachi       Update          QC#18343
 * 2017/06/02   Hitachi         K.Kitachi       Update          QC#18568
 * 2018/09/25   Hitachi         T.Tomita        Update          QC#28360
 * 2019/05/31   Hitachi         K.Fujimoto      Update          QC#50613
 *</pre>
 */
public class NSXC001001ValidationDsContrIntfc {

    /** This data has been updated by another user. */
    private static final String ZZZM9004E = "ZZZM9004E";

    /** [Source Type] field is mandatory. */
    private static final String NSXM0012E = "NSXM0012E";

    /** [Source Ref#] field is mandatory. */
    private static final String NSXM0013E = "NSXM0013E";

    /** [Contract#] field is mandatory. */
    private static final String NSXM0014E = "NSXM0014E";

    /** [Action] field is mandatory. */
    private static final String NSXM0015E = "NSXM0015E";

    /** [Serial#] field is mandatory. */
    private static final String NSXM0016E = "NSXM0016E";

    /** [IB ID] field is mandatory. */
    private static final String NSXM0017E = "NSXM0017E";

    /** [Line Type] field is mandatory. */
    private static final String NSXM0018E = "NSXM0018E";

    /** [Cust Acct Num] field is mandatory. */
    private static final String NSXM0019E = "NSXM0019E";

    /** [Bill To Customer Number] field is mandatory. */
    private static final String NSXM0020E = "NSXM0020E";

    /** [Lease Acct Num] field is mandatory. */
    private static final String NSXM0021E = "NSXM0021E";

    /** [Contract Branch] field is mandatory. */
    private static final String NSXM0022E = "NSXM0022E";

    /** [Contract Rep] field is mandatory. */
    private static final String NSXM0023E = "NSXM0023E";

    /** [PO Exp Date] field is mandatory. */
    private static final String NSXM0024E = "NSXM0024E";

    /** [CC Exp Date] field is mandatory. */
    private static final String NSXM0025E = "NSXM0025E";

    /** [Auto Estimation Code] field is mandatory. */
    private static final String NSXM0026E = "NSXM0026E";

    /** [Service Program] field is mandatory. */
    private static final String NSXM0027E = "NSXM0027E";

    /** [Mdse Code] field is mandatory. */
    private static final String NSXM0028E = "NSXM0028E";

    /** [Start Date] field is mandatory. */
    private static final String NSXM0029E = "NSXM0029E";

    /** [End Date] field is mandatory. */
    private static final String NSXM0030E = "NSXM0030E";

    /** [Frequency] field is mandatory. */
    private static final String NSXM0031E = "NSXM0031E";

    /** [Price Per Period] field is mandatory. */
    private static final String NSXM0034E = "NSXM0034E";

    /** [Period End Date Ctrl] field is mandatory. */
    private static final String NSXM0035E = "NSXM0035E";

    /** [Invoice Date Ctrl] field is mandatory. */
    private static final String NSXM0036E = "NSXM0036E";

    /** [Overage Counter Name] field is mandatory. */
    private static final String NSXM0037E = "NSXM0037E";

    /** [Contract Category] field is mandatory. */
    private static final String NSXM0039E = "NSXM0039E";

    /** [Contract Close Date] field is mandatory. */
    private static final String NSXM0040E = "NSXM0040E";

    /** [Contract#] doesn't exist in the table [DS Contract]. */
    private static final String NSXM0041E = "NSXM0041E";

    /** [IB ID] doesn't exist in the table [Machine Master]. */
    private static final String NSXM0042E = "NSXM0042E";

    /** [Cust Acct Num] doesn't exist in the table [Customer Account]. */
    private static final String NSXM0043E = "NSXM0043E";

    /** [Bill To Customer Number] doesn't exist in the table [Bill To Cust Master]. */
    private static final String NSXM0044E = "NSXM0044E";

    /** [Lease Company Num] doesn't exist in the table [Bill To Cust Master]. */
    private static final String NSXM0045E = "NSXM0045E";

    /** [Contract Rep] doesn't exist in master. */
    private static final String NSXM0046E = "NSXM0046E";

    /** [Mdse Code] doesn't exist in the table [Merchandise Master]. */
    private static final String NSXM0047E = "NSXM0047E";

    /** [Model ID] doesn't exist in the table [DS Model]. */
    private static final String NSXM0048E = "NSXM0048E";

    /** [Overage Counter Code] doesn't exist in the table [Meter Label]. */
    private static final String NSXM0049E = "NSXM0049E";

    /** [Contact Person ID] doesn't exist in the table [Contact Person]. */
    private static final String NSXM0050E = "NSXM0050E";

    /** Specified Contract cannot be deleted in order to Contract Status is not 'Entered'. */
    private static final String NSXM0051E = "NSXM0051E";

    /** Invalid Date. Date cannot be a future date. */
    private static final String NSXM0052E = "NSXM0052E";

    /** The chronological sequence between the dates is wrong. */
    private static final String NSXM0053E = "NSXM0053E";

    /** Fleet and Aggregate Contract cannot include FAX products. */
    private static final String NSXM0054E = "NSXM0054E";

    /** [Contract#] should be match each other in the same [Source Ref#]. */
    private static final String NSXM0055E = "NSXM0055E";

    /** [Action Code] should be match each other in the same [Source Ref#]. */
    private static final String NSXM0056E = "NSXM0056E";

    /** [Contract Category] should be match each other in the same [Source Ref#]. */
    private static final String NSXM0057E = "NSXM0057E";

    /** [Contract Branch] should be match each other in the same [Source Ref#].# */
    private static final String NSXM0058E = "NSXM0058E";

    /** [Contract Rep] should be match each other in the same [Source  Ref#]. */
    private static final String NSXM0059E = "NSXM0059E";

    /** [Mdse Code] should be match each other in the same [IB ID]. */
    private static final String NSXM0060E = "NSXM0060E";

    /** [Serial#] should be match each other in the same [IB ID]. */
    private static final String NSXM0061E = "NSXM0061E";

    /** [Model ID] should be match each other in the same [IB ID]. */
    private static final String NSXM0062E = "NSXM0062E";

    /** [Start Date] should be match each other in the same [IB ID]. */
    private static final String NSXM0063E = "NSXM0063E";

    /** [End Date] should be match each other in the same [IB ID]. */
    private static final String NSXM0064E = "NSXM0064E";

    /** [Service Program] should be match each other in the same [IB ID]. */
    private static final String NSXM0065E = "NSXM0065E";

    /** Multiple target data exist.KeyName:[Base Line Type]. */
    private static final String NSXM0066E = "NSXM0066E";

    /** [Renewal Type] should be match each other in the same [Line Type]. */
    private static final String NSXM0067E = "NSXM0067E";

    /** [Renewal Method] should be match each other in the same [Line Type]. */
    private static final String NSXM0068E = "NSXM0068E";

    /** [# Days Before] should be match each other in the same [Line Type]. */
    private static final String NSXM0069E = "NSXM0069E";

    /** [Renewal %] should be match each other in the same [Line Type]. */
    private static final String NSXM0070E = "NSXM0070E";

    /** [Annual Escalation Type] should be match each other in the same [Line Type]. */
    private static final String NSXM0071E = "NSXM0071E";

    /** [Escalation Method] should be match each other in the same [Line Type]. */
    private static final String NSXM0072E = "NSXM0072E";

    /** [Escalation Percent] should be match each other in the same [Line Type]. */
    private static final String NSXM0073E = "NSXM0073E";

    /** Failed to update "DS_CONTR_INTFC". */
    private static final String NSXM0074E = "NSXM0074E";

    // 2016/04/08 START [QC#5822, ADD]
    /** You can not set the Usage information on Non Metered Machine. */
    private static final String NSXM0100E = "NSXM0100E";
    // 2016/04/08 END   [QC#5822, ADD]

    // START 2016/05/25 [QC#4061, ADD]
    /** If you set check "Manual Override". Should not Usage line exists. */
    private static final String NSXM0109E = "NSXM0109E";

    /** You cannot set check "Manual Override". Because it's accessory. */
    private static final String NSXM0110E = "NSXM0110E";

    /** If you set check "Manual Override". Should not [DS Actual Count Interface] exists. */
    private static final String NSXM0111E = "NSXM0111E";

    /** If you set check "Manual Override". Should not [DS Excess Copy Interface] exists. */
    private static final String NSXM0112E = "NSXM0112E";

    /** If you set check "Manual Override". Should not [DS Additional Charge Interface] exists. */
    private static final String NSXM0113E = "NSXM0113E";

    /** If you set check "Manual Override". Should not [Price Allocation Interface] exists. */
    private static final String NSXM0114E = "NSXM0114E";

    /** If you set check "Manual Override". You can not select "Action" to Update. */
    private static final String NSXM0115E = "NSXM0115E";

    /** Machine/Serial Number flagged as End of Life. No Contracts can be created against this machine/serial number. */
    public static final String NSAM0479E = "NSAM0479E";

    // add start 2016/11/14 CSA Defect#14934
    /** The status of specified Contract cannot be [Add To Contract]. */
    public static final String NSXM0122E = "NSXM0122E";
    // add end 2016/11/14 CSA Defect#14934

    // START 2017/06/02 K.Kitachi [QC#18568, ADD]
    /** Aggregate Line does not exist. */
    public static final String NSXM0123E = "NSXM0123E";

    /** Invoice Date does not match with aggregate line. */
    public static final String NSXM0124E = "NSXM0124E";

    /** Period End Date does not match with aggregate line. */
    public static final String NSXM0125E = "NSXM0125E";

    /** Billing Counter does not exist in aggregate line. */
    public static final String NSXM0126E = "NSXM0126E";
    // END 2017/06/02 K.Kitachi [QC#18568, ADD]

    /** S21SsmBatchClient */
    static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001ValidationDsContrIntfc.class);
    // END   2016/05/25 [QC#4061, ADD]

    /** SPCL_FLT_MDSE_CD */
    private static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    /**
     * <pre>
     * validationDsContrIntfc
     * </pre>
     * @param prmPmsg NSXC001001PMsg
     * @param tMsgList List<DS_CONTR_INTFCTMsg>
     */
    public static void validationDsContrIntfc(NSXC001001PMsg prmPmsg, List<DS_CONTR_INTFCTMsg> tMsgList) {
        int errorCount = 0;
        int errorTotalCount = 0;
        for (DS_CONTR_INTFCTMsg tMsg : tMsgList) {
            // Error clear
            setValue(tMsg.intfcErrMsgTxt, (String) null);
        }
        for (DS_CONTR_INTFCTMsg tMsg : tMsgList) {

            // Input check
            errorCount = checkInput(prmPmsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }

            // Master check
            errorCount = masterCheck(prmPmsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }
            // Integrity check
            errorCount = integrityCheck(prmPmsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }

            // add start 2016/06/13 CSA Defect#477
            // End Of Life Check
            errorCount = endOfLifeCheck(prmPmsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }
            // add end 2016/06/13 CSA Defect#477

            // START 2017/06/02 K.Kitachi [QC#18568, ADD]
            errorCount = addToContrCheck(prmPmsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }
            // END 2017/06/02 K.Kitachi [QC#18568, ADD]
        }
        // Manual Override validation
        errorCount = manualOverrideCheck(prmPmsg, tMsgList, errorCount);

        // Contract Header validation
        errorCount = headerCheck(prmPmsg, tMsgList, errorCount);
        errorTotalCount += errorCount;
        if (errorCount == 0) {
            // Contract Detail validation
            errorCount = detailCheck(prmPmsg, tMsgList, errorCount);
            errorTotalCount += errorCount;
            if (errorCount == 0) {
                // Renewal Escalation validation
                errorCount = renewalEscalationCheck(prmPmsg, tMsgList, errorCount);
                errorTotalCount += errorCount;
            }
        }

        prmPmsg.xxMsgIdList.setValidCount(errorTotalCount);
    }

    private static int checkInput(NSXC001001PMsg prmPmsg, DS_CONTR_INTFCTMsg tMsg, int errorTotalCount) {
        int errorCount = 0;

        if (!hasValue(tMsg.contrIntfcSrcTpCd)) {
            setErrorMessage(prmPmsg, NSXM0012E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0012E);
            errorCount++;
        }
        if (!hasValue(tMsg.dsContrSrcRefNum)) {
            setErrorMessage(prmPmsg, NSXM0013E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0013E);
            errorCount++;
        }
        if (!DS_CONTR_INTFC_ACT.CREATE.equals(tMsg.dsContrIntfcActCd.getValue())) {
            if (!hasValue(tMsg.dsContrNum)) {
                setErrorMessage(prmPmsg, NSXM0014E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0014E);
                errorCount++;
            }
        }
        if (!hasValue(tMsg.dsContrIntfcActCd)) {
            setErrorMessage(prmPmsg, NSXM0015E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0015E);
            errorCount++;
        }
        if (isCheckExec(tMsg.glblCmpyCd.getValue(), tMsg.mdseCd.getValue())) {
            if (!hasValue(tMsg.serNum)) {
                setErrorMessage(prmPmsg, NSXM0016E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0016E);
                errorCount++;
            }
        }
        if (!hasValue(tMsg.contrIntfcDtlTpCd)) {
            setErrorMessage(prmPmsg, NSXM0018E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0018E);
            errorCount++;
        }
        if (!hasValue(tMsg.dsContrCatgCd)) {
            setErrorMessage(prmPmsg, NSXM0039E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0039E);
            errorCount++;
        }
        // 2016/03/15 START [QC#4114, MOD]
        if (!isFltLine(tMsg) && !isUnderAgg(tMsg)) {
            if (!hasValue(tMsg.svcMachMstrPk)) {
                setErrorMessage(prmPmsg, NSXM0017E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0017E);
                errorCount++;
            }
        }
        if (isReg(tMsg) || isFltLine(tMsg) || isUnderAgg(tMsg)) {
            if (!hasValue(tMsg.dsAcctNum)) {
                setErrorMessage(prmPmsg, NSXM0019E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0019E);
                errorCount++;
            }
            if (!hasValue(tMsg.billToCustCd)) {
                setErrorMessage(prmPmsg, NSXM0020E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0020E);
                errorCount++;
            }
            // START 2017/02/28 K.Kitachi [QC#17769, MOD]
            if (CONTR_INTFC_DTL_TP.BASE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(tMsg.baseChrgToLeaseCmpyFlg.getValue())) {
                    if (!hasValue(tMsg.leaseCmpyCd)) {
                        setErrorMessage(prmPmsg, NSXM0021E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(tMsg, NSXM0021E);
                        errorCount++;
                    }
                }
            }
            if (CONTR_INTFC_DTL_TP.USAGE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(tMsg.usgChrgToLeaseCmpyFlg.getValue())) {
                    if (!hasValue(tMsg.leaseCmpyCd)) {
                        setErrorMessage(prmPmsg, NSXM0021E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(tMsg, NSXM0021E);
                        errorCount++;
                    }
                }
            }
            // END 2017/02/28 K.Kitachi [QC#17769, MOD]
            if (!hasValue(tMsg.svcContrBrCd)) {
                setErrorMessage(prmPmsg, NSXM0022E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0022E);
                errorCount++;
            }
            if (!hasValue(tMsg.tocCd)) {
                setErrorMessage(prmPmsg, NSXM0023E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0023E);
                errorCount++;
            }
            if (!hasValue(tMsg.mtrEstTpCd)) {
                setErrorMessage(prmPmsg, NSXM0026E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0026E);
                errorCount++;
            }
            if (!hasValue(tMsg.svcPgmMdseCd)) {
                setErrorMessage(prmPmsg, NSXM0027E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0027E);
                errorCount++;
            }
            if (!hasValue(tMsg.bllgCycleCd)) {
                setErrorMessage(prmPmsg, NSXM0031E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0031E);
                errorCount++;
            }
            // 2016/03/28 START [QC#5822, MOD]
            // 2016/05/25 START [QC#4061, MOD]
            if (!isManContrOvrd(tMsg)) {
                if (isCheckBasePrcDealAmt(tMsg)) {
                    if (CONTR_INTFC_DTL_TP.BASE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
                        if (!hasValue(tMsg.basePrcDealAmt)) {
                            setErrorMessage(prmPmsg, NSXM0034E, errorTotalCount + errorCount);
                            setErrorStatusAndMessage(tMsg, NSXM0034E);
                            errorCount++;
                        }
                    }
                }
            }
            // 2016/05/25 END   [QC#4061, MOD]
            // 2016/03/28 END   [QC#5822, MOD]
            if (!hasValue(tMsg.contrCloDay)) {
                setErrorMessage(prmPmsg, NSXM0035E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0035E);
                errorCount++;
            }
            if (!hasValue(tMsg.contrBllgDay)) {
                setErrorMessage(prmPmsg, NSXM0036E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0036E);
                errorCount++;
            }
            if (CONTR_INTFC_DTL_TP.USAGE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
                if (!hasValue(tMsg.bllgMtrLbCd)) {
                    setErrorMessage(prmPmsg, NSXM0037E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0037E);
                    errorCount++;
                }
            }
        }
        // 2016/03/15 END [QC#4114, MOD]

        if (hasValue(tMsg.custPoNum)) {
            if (!hasValue(tMsg.poDt)) {
                setErrorMessage(prmPmsg, NSXM0024E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0024E);
                errorCount++;
            }
        }
        if (hasValue(tMsg.crCardCustRefNum)) {
            if (!hasValue(tMsg.crCardExprYrMth)) {
                setErrorMessage(prmPmsg, NSXM0025E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0025E);
                errorCount++;
            }
        }

        // 2016/03/11 START [QC#5298, MOD]
        if (!hasValue(tMsg.mdseCd) && hasValue(tMsg.svcMachMstrPk)) {
            setErrorMessage(prmPmsg, NSXM0028E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0028E);
            errorCount++;
        }
        // 2016/03/11 END [QC#5298, MOD]
        if (DS_CONTR_INTFC_ACT.CREATE.equals(tMsg.dsContrIntfcActCd.getValue())) {
            if (!hasValue(tMsg.contrFromDt)) {
                setErrorMessage(prmPmsg, NSXM0029E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0029E);
                errorCount++;
            }
        }
        if (DS_CONTR_INTFC_ACT.CREATE.equals(tMsg.dsContrIntfcActCd.getValue())) {
            if (!hasValue(tMsg.contrThruDt)) {
                setErrorMessage(prmPmsg, NSXM0030E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0030E);
                errorCount++;
            }
        }
        // START 2016/03/28 [QC#5822 DEL]
//        if (DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue())) {
//            if (!ZYPConstant.FLG_ON_Y.equals(tMsg.prcAllocByMachQtyFlg.getValue())) {
//                setErrorMessage(prmPmsg, NSXM0032E, errorTotalCount + errorCount);
//                setErrorStatusAndMessage(tMsg, NSXM0032E);
//                errorCount++;
//            }
//        }
        // END   2016/03/28 [QC#5822 DEL]

        // 2016/03/15 START [QC#4114, DEL]
//        if (CONTR_INTFC_DTL_TP.USAGE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
//            if (!hasValue(tMsg.mtrReadMethCd)) {
//                setErrorMessage(prmPmsg, NSXM0033E, errCnt);
//                setErrorStatusAndMessage(tMsg, NSXM0033E);
//                errCnt++;
//                okFlg = false;
//            }
//        }
//        if (CONTR_INTFC_DTL_TP.USAGE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
//            if (!hasValue(tMsg.startMtrCnt)) {
//                setErrorMessage(prmPmsg, NSXM0038E, errCnt);
//                setErrorStatusAndMessage(tMsg, NSXM0038E);
//                errCnt++;
//                okFlg = false;
//            }
//        }
        // 2016/03/15 END [QC#4114, DEL]

        if (DS_CONTR_INTFC_ACT.TERMINATE.equals(tMsg.dsContrIntfcActCd.getValue())) {
            if (!hasValue(tMsg.contrCloDt)) {
                setErrorMessage(prmPmsg, NSXM0040E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0040E);
                errorCount++;
            }
        }
        return errorCount;
    }

    private static boolean isReg(DS_CONTR_INTFCTMsg tMsg) {
        if (DS_CONTR_CATG.REGULAR.equals(tMsg.dsContrCatgCd.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean isFltLine(DS_CONTR_INTFCTMsg tMsg) {
        if (DS_CONTR_CATG.FLEET.equals(tMsg.dsContrCatgCd.getValue())) {
            if (!hasValue(tMsg.svcMachMstrPk)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isUnderAgg(DS_CONTR_INTFCTMsg tMsg) {
        if (DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue())) {
            // 2016/03/28 START [QC#5822, MOD]
            if (!hasValue(tMsg.svcMachMstrPk)) {
                return true;
            }
            // 2016/03/28 END   [QC#5822, MOD]
        }
        return false;
    }

    private static boolean isCheckExec(String glblCmpyCd, String mdseCd) {
        MDSETMsg tMsg = getMdse(glblCmpyCd, mdseCd);
        if (tMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(tMsg.rcvSerTakeFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(tMsg.shpgSerTakeFlg.getValue())) {
                return true;
            }
        }
        return false;
    }

    private static int masterCheck(NSXC001001PMsg prmPmsg, DS_CONTR_INTFCTMsg tMsg, int errorTotalCount) {
        int errorCount = 0;
        // QC#3995:Added "hasValue" in this method.
        // Contract#(DS_CONTR)
        // 2016/04/08 START [QC#5822, MOD]
        if (hasValue(tMsg.dsContrNum) && !CONTR_INTFC_SRC_TP.COPY_CONTRACT.equals(tMsg.contrIntfcSrcTpCd.getValue()) && !checkDsContr(tMsg.glblCmpyCd.getValue(), tMsg.dsContrNum.getValue())) {
            setErrorMessage(prmPmsg, NSXM0041E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0041E);
            errorCount++;
        }
        // 2016/04/08 END   [QC#5822, MOD]
        // IB ID(SVC_MACH_MSTR)
        if (hasValue(tMsg.svcMachMstrPk) && !checkIbId(tMsg.glblCmpyCd.getValue(), tMsg.svcMachMstrPk.getValue())) {
            setErrorMessage(prmPmsg, NSXM0042E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0042E);
            errorCount++;
        }
        // Sell To Cust Cd(SELL_TO_CUST)
        if (hasValue(tMsg.dsAcctNum) && !checkSellToCust(tMsg.glblCmpyCd.getValue(), tMsg.dsAcctNum.getValue())) {
            setErrorMessage(prmPmsg, NSXM0043E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0043E);
            errorCount++;
        }
        // Bill To Customer Number(BILL_TO_CUST)
        if (hasValue(tMsg.billToCustCd) && !checkBillToCust(tMsg.glblCmpyCd.getValue(), tMsg.billToCustCd.getValue())) {
            setErrorMessage(prmPmsg, NSXM0044E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0044E);
            errorCount++;
        }
        // Lease Acct Num(BILL_TO_CUST)
        if (hasValue(tMsg.leaseCmpyCd) && !checkBillToCust(tMsg.glblCmpyCd.getValue(), tMsg.leaseCmpyCd.getValue())) {
            setErrorMessage(prmPmsg, NSXM0045E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0045E);
            errorCount++;
        }
        // Contract Rep(TOC)
        if (hasValue(tMsg.tocCd) && !checkToc(tMsg.glblCmpyCd.getValue(), tMsg.tocCd.getValue())) {
            setErrorMessage(prmPmsg, NSXM0046E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0046E);
            errorCount++;
        }
        // Mdse Cd(MDSE_CD)
        if (hasValue(tMsg.mdseCd) && !checkMdse(tMsg.glblCmpyCd.getValue(), tMsg.mdseCd.getValue())) {
            setErrorMessage(prmPmsg, NSXM0047E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0047E);
            errorCount++;
        }
        // Model ID(DS_MDL)
        if (hasValue(tMsg.mdlId) && !checkModel(tMsg.glblCmpyCd.getValue(), tMsg.mdlId.getValue())) {
            setErrorMessage(prmPmsg, NSXM0048E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0048E);
            errorCount++;
        }
        // Overage Counter Code(MTR_LB)
        if (hasValue(tMsg.bllgMtrLbCd) && !checkMeter(tMsg.glblCmpyCd.getValue(), tMsg.bllgMtrLbCd.getValue())) {
            setErrorMessage(prmPmsg, NSXM0049E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0049E);
            errorCount++;
        }
        // Contact Person ID(CTAC_PSN)
        if (hasValue(tMsg.ctacPsnPk) && !checkPerson(tMsg.glblCmpyCd.getValue(), tMsg.ctacPsnPk.getValue())) {
            setErrorMessage(prmPmsg, NSXM0050E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0050E);
            errorCount++;
        }
        // 2016/04/08 START [QC#5822, ADD]
        // Model ID(DS_MDL) MeterRequired
        if (hasValue(tMsg.mdlId) && CONTR_INTFC_DTL_TP.USAGE.equals(tMsg.contrIntfcDtlTpCd.getValue()) && !checkModelMeterRequired(tMsg.glblCmpyCd.getValue(), tMsg.mdlId.getValue())) {
            setErrorMessage(prmPmsg, NSXM0100E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0100E);
            errorCount++;
        }
        // 2016/04/08 END   [QC#5822, ADD]

        return errorCount;
    }

    private static boolean checkDsContr(String glblCmpyCd, String dsContrNum) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrNum01", dsContrNum);
        if ((int) EZDTBLAccessor.count(inMsg) > 0) {
            return true;
        }
        return false;
    }

    private static boolean checkSellToCust(String glblCmpyCd, String sellToCustCd) {
        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        if ((int) EZDTBLAccessor.count(inMsg) > 0) {
            return true;
        }
        return false;
    }

    private static boolean checkBillToCust(String glblCmpyCd, String billToCustCd) {
        BILL_TO_CUSTTMsg inMsg = new BILL_TO_CUSTTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("billToCustCd01", billToCustCd);
        if ((int) EZDTBLAccessor.count(inMsg) > 0) {
            return true;
        }
        return false;
    }

    private static boolean checkIbId(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("svcMachMstrPk", svcMachMstrPk);
        SVC_MACH_MSTRTMsg tMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg != null) {
            return true;
        }
        return false;
    }

    private static boolean checkToc(String glblCmpyCd, String tocCd) {
        TOCTMsg inMsg = new TOCTMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("tocCd", tocCd);
        TOCTMsg tMsg = (TOCTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg != null) {
            return true;
        }
        return false;
    }

    private static boolean checkMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg tMsg = getMdse(glblCmpyCd, mdseCd);
        if (tMsg != null) {
            return true;
        }
        // 2016/03/11 START [QC#5298, MOD]
        String fltMdseCd = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, glblCmpyCd);
        if (mdseCd.equals(fltMdseCd)) {
            return true;
        }
        // 2016/03/11 END [QC#5298, MOD]
        return false;
    }
    private static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("mdseCd", mdseCd);
        return (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private static boolean checkModel(String glblCmpyCd, BigDecimal mdlId) {
        DS_MDLTMsg inMsg = new DS_MDLTMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("mdlId", mdlId);
        DS_MDLTMsg tMsg = (DS_MDLTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg != null) {
            return true;
        }
        return false;
    }

    // 2016/04/08 START [QC#5822, ADD]
    private static boolean checkModelMeterRequired(String glblCmpyCd, BigDecimal mdlId) {
        DS_MDLTMsg inMsg = new DS_MDLTMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("mdlId", mdlId);
        DS_MDLTMsg tMsg = (DS_MDLTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg == null) {
            return true;
        }
        return ZYPConstant.FLG_ON_Y.equals(tMsg.mtrReqMdlFlg.getValue());
    }
    // 2016/04/08 END   [QC#5822, ADD]

    private static boolean checkMeter(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("mtrLbCd", mtrLbCd);
        MTR_LBTMsg tMsg = (MTR_LBTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg != null) {
            return true;
        }
        return false;
    }

    private static boolean checkPerson(String glblCmpyCd, BigDecimal ctacPsnPk) {
        CTAC_PSNTMsg inMsg = new CTAC_PSNTMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("ctacPsnPk", ctacPsnPk);
        CTAC_PSNTMsg tMsg = (CTAC_PSNTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg != null) {
            return true;
        }
        return false;
    }

    private static int integrityCheck(NSXC001001PMsg prmPmsg, DS_CONTR_INTFCTMsg tMsg, int errorTotalCount) {
        int errorCount = 0;

        if (DS_CONTR_INTFC_ACT.DELETE.equals(tMsg.dsContrIntfcActCd.getValue())) {
            if (DS_CONTR_STS.ENTERED.equals(tMsg.dsContrStsCd.getValue())) {
                setErrorMessage(prmPmsg, NSXM0051E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0051E);
                errorCount++;
            }
        }

        // del start 2016/05/17 CSA Defect#3771
        // if (hasValue(tMsg.bllgThruDt) && tMsg.bllgThruDt.getValue().compareTo(ZYPDateUtil.getSalesDate(tMsg.glblCmpyCd.getValue())) > 0) {
        //     setErrorMessage(prmPmsg, NSXM0052E, errorTotalCount + errorCount);
        //     setErrorStatusAndMessage(tMsg, NSXM0052E);
        //     errorCount++;
        // }
        // del start 2016/05/17 CSA Defect#3771

        if (hasValue(tMsg.contrFromDt) && hasValue(tMsg.contrThruDt) && tMsg.contrFromDt.getValue().compareTo(tMsg.contrThruDt.getValue()) > 0) {
            setErrorMessage(prmPmsg, NSXM0053E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0053E);
            errorCount++;
        }

        // START 2016/03/28 [QC#5822 MOD]
        //if (MTR_READ_METH.FAX.equals(tMsg.mtrReadMethCd.getValue()) && (DS_CONTR_CATG.FLEET.equals(tMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue()))) {
        //    setErrorMessage(prmPmsg, NSXM0054E, errorTotalCount + errorCount);
        //    setErrorStatusAndMessage(tMsg, NSXM0054E);
        //    errorCount++;
        //}
        if (DS_CONTR_CATG.FLEET.equals(tMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue())) {
            if (hasValue(tMsg.mdseCd)) {
                MDSETMsg mdseTmsg = getMdse(tMsg.glblCmpyCd.getValue(), tMsg.mdseCd.getValue());
                if (mdseTmsg != null) {
                    if ((COA_PROD.IMAGE_FILING_SYSTEM.equals(mdseTmsg.coaProdCd.getValue()) || COA_PROD.FACSIMILE.equals(mdseTmsg.coaProdCd.getValue()))) {
                        setErrorMessage(prmPmsg, NSXM0054E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(tMsg, NSXM0054E);
                        errorCount++;
                    }
                }
            }
        }
        // END   2016/03/28 [QC#5822 MOD]

        // add start 2016/11/14 CSA Defect#14934
        if (DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(tMsg.dsContrIntfcActCd.getValue())) {
            Map<String, Object> dsContrStsMap = getDsContrSts(tMsg);
            if (dsContrStsMap != null) {
                int count = getDsContrExclStsCount(dsContrStsMap);
                if (count == 0) {
                    setErrorMessage(prmPmsg, NSXM0122E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0122E);
                    errorCount++;
                }
            } else {
                setErrorMessage(prmPmsg, NSXM0122E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0122E);
                errorCount++;
            }
        }
        // add end 2016/11/14 CSA Defect#14934

        return errorCount;
    }

    // START 2016/05/24 [QC#4061, ADD]
    private static int manualOverrideCheck(NSXC001001PMsg prmPmsg, List<DS_CONTR_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        Set<String> manContrOvrdKeySet = new HashSet<String>();
        Set<String> usageExistsKeySet = new HashSet<String>();
        for (int i = 0; i < tMsgList.size(); i++) {
            DS_CONTR_INTFCTMsg tMsg = tMsgList.get(i);

            // Usage Line exist check
            String key = tMsg.contrIntfcSrcTpCd.getValue() + "," + tMsg.dsContrSrcRefNum.getValue();
            if (isManContrOvrd(tMsg)) {
                manContrOvrdKeySet.add(key);
            }
            if (isUsageLine(tMsg)) {
                usageExistsKeySet.add(key);
            }

            // Action selected check
            if (isManContrOvrd(tMsg) && DS_CONTR_INTFC_ACT.UPDATE.equals(tMsg.dsContrIntfcActCd.getValue())) {
                setErrorMessage(prmPmsg, NSXM0115E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0115E);
                errorCount++;
            }

            // Accessory Check
            if (isManContrOvrd(tMsg)) {
                SVC_MACH_MSTRTMsg svcMachTmsg = getSvcMachMstr(tMsg.glblCmpyCd.getValue(), tMsg.svcMachMstrPk.getValue());
                if (svcMachTmsg != null && SVC_MACH_TP.ACCESSORY.equals(svcMachTmsg.svcMachTpCd.getValue())) {
                    setErrorMessage(prmPmsg, NSXM0110E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0110E);
                    errorCount++;
                }
            }

            // Interface table exist check
            if (isManContrOvrd(tMsg) && isExistsDsActlCntIntfc(tMsg)) {
                setErrorMessage(prmPmsg, NSXM0111E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0111E);
                errorCount++;
            }
            if (isManContrOvrd(tMsg) && isExistsDsXsCopyIntfc(tMsg)) {
                setErrorMessage(prmPmsg, NSXM0112E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0112E);
                errorCount++;
            }
            if (isManContrOvrd(tMsg) && isExistsDsAddlChrgIntfc(tMsg)) {
                setErrorMessage(prmPmsg, NSXM0113E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0113E);
                errorCount++;
            }
            if (isManContrOvrd(tMsg) && isExistsPrcAllocIntfc(tMsg)) {
                setErrorMessage(prmPmsg, NSXM0114E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0114E);
                errorCount++;
            }
        }

        // Usage Line exist check message set
        for (int i = 0; i < tMsgList.size(); i++) {
            DS_CONTR_INTFCTMsg tMsg = tMsgList.get(i);
            String key = tMsg.contrIntfcSrcTpCd.getValue() + "," + tMsg.dsContrSrcRefNum.getValue();
            if (manContrOvrdKeySet.contains(key) && usageExistsKeySet.contains(key)) {
                setErrorMessage(prmPmsg, NSXM0109E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0109E);
                errorCount++;
            }
        }
        return errorCount;
    }

    private static boolean isUsageLine(DS_CONTR_INTFCTMsg tMsg) {
        if (tMsg == null) {
            return false;
        }
        if (CONTR_INTFC_DTL_TP.USAGE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean isManContrOvrd(DS_CONTR_INTFCTMsg tMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(tMsg.manContrOvrdFlg.getValue())) {
            return true;
        }
        return false;
    }

    private static SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        tMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        tMsg.setConditionValue("svcMachMstrPk", svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    private static boolean isExistsDsActlCntIntfc(DS_CONTR_INTFCTMsg tMsg) {
        List<DS_ACTL_CNT_INTFCTMsg> list = getDsActlCntIntfc(tMsg);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }

    private static boolean isExistsDsXsCopyIntfc(DS_CONTR_INTFCTMsg tMsg) {
        List<DS_XS_COPY_INTFCTMsg> list = getDsXsCopyIntfc(tMsg);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }

    private static boolean isExistsDsAddlChrgIntfc(DS_CONTR_INTFCTMsg tMsg) {
        List<DS_ADDL_CHRG_INTFCTMsg> list = getDsAddlChrgIntfc(tMsg);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }

    private static boolean isExistsPrcAllocIntfc(DS_CONTR_INTFCTMsg tMsg) {
        List<PRC_ALLOC_INTFCTMsg> list = getPrcAllocIntfc(tMsg);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }

    private static List<DS_ACTL_CNT_INTFCTMsg> getDsActlCntIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tmsg.glblCmpyCd.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        if (CONTR_INTFC_SRC_TP.ORDER.equals(tmsg.contrIntfcSrcTpCd.getValue())) {
            param.put("cpoSvcDtlPk", tmsg.cpoSvcDtlPk.getValue());
        }

        return (List<DS_ACTL_CNT_INTFCTMsg>) SSM_CLIENT.queryObjectList("getActlCntIntfc", param);
    }

    private static List<DS_XS_COPY_INTFCTMsg> getDsXsCopyIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tmsg.glblCmpyCd.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        if (CONTR_INTFC_SRC_TP.ORDER.equals(tmsg.contrIntfcSrcTpCd.getValue())) {
            param.put("cpoSvcDtlPk", tmsg.cpoSvcDtlPk.getValue());
        }

        return (List<DS_XS_COPY_INTFCTMsg>) SSM_CLIENT.queryObjectList("getDsXsCopyIntfc", param);
    }

    private static List<DS_ADDL_CHRG_INTFCTMsg> getDsAddlChrgIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tmsg.glblCmpyCd.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        if (CONTR_INTFC_SRC_TP.ORDER.equals(tmsg.contrIntfcSrcTpCd.getValue())) {
            param.put("cpoSvcDtlPk", tmsg.cpoSvcDtlPk.getValue());
        }

        return (List<DS_ADDL_CHRG_INTFCTMsg>) SSM_CLIENT.queryObjectList("getDsAddlChrgIntfc", param);
    }

    private static List<PRC_ALLOC_INTFCTMsg> getPrcAllocIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tmsg.glblCmpyCd.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        if (CONTR_INTFC_SRC_TP.ORDER.equals(tmsg.contrIntfcSrcTpCd.getValue())) {
            param.put("cpoSvcDtlPk", tmsg.cpoSvcDtlPk.getValue());
        }

        return (List<PRC_ALLOC_INTFCTMsg>) SSM_CLIENT.queryObjectList("getPrcAllocIntfc", param);
    }
    // End   2016/05/24 [QC#4061, ADD]

    // add start 2016/11/14 CSA Defect#14934
    private static Map<String, Object> getDsContrSts(DS_CONTR_INTFCTMsg tMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        param.put("dsContrNum", tMsg.dsContrNum.getValue());
        param.put("svcMachMstrPk", tMsg.svcMachMstrPk.getValue());

        return (Map<String, Object>) SSM_CLIENT.queryObject("getDsContrSts", param);
    }

    private static Integer getDsContrExclStsCount(Map<String, Object> dsContrStsMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", (String) dsContrStsMap.get("GLBL_CMPY_CD"));
        param.put("dsContrStsCd", (String) dsContrStsMap.get("DS_CONTR_STS_CD"));
        param.put("dsContrDtlStsCd", (String) dsContrStsMap.get("DS_CONTR_DTL_STS_CD"));

        return (Integer) SSM_CLIENT.queryObject("getDsContrExclStsCount", param);
    }
    // add end 2016/11/14 CSA Defect#14934

    private static int headerCheck(NSXC001001PMsg prmPmsg, List<DS_CONTR_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        List<DS_CONTR_INTFCTMsg> sortList = getSortListForHeader(tMsgList);
        String masterKey = null;
        String masterDsContrNum = null;
        String masterDsContrIntfcActCd = null;
        String masterDsContrCatgCd = null;
        String masterSvcContrBrCd = null;
        String masterTocCd = null;
        for (int i = 0; i < sortList.size(); i++) {
            if (i == 0) {
                masterKey = sortList.get(i).contrIntfcSrcTpCd.getValue() + "," + sortList.get(i).dsContrSrcRefNum.getValue();
                masterDsContrNum = sortList.get(i).dsContrNum.getValue();
                masterDsContrIntfcActCd = sortList.get(i).dsContrIntfcActCd.getValue();
                masterDsContrCatgCd = sortList.get(i).dsContrCatgCd.getValue();
                masterSvcContrBrCd = sortList.get(i).svcContrBrCd.getValue();
                masterTocCd = sortList.get(i).tocCd.getValue();
            }
            if (i > 0) {
                String targetKey = sortList.get(i).contrIntfcSrcTpCd.getValue() + "," + sortList.get(i).dsContrSrcRefNum.getValue();
                String targetDsContrNum = sortList.get(i).dsContrNum.getValue();
                String targetDsContrIntfcActCd = sortList.get(i).dsContrIntfcActCd.getValue();
                String targetDsContrCatgCd = sortList.get(i).dsContrCatgCd.getValue();
                String targetSvcContrBrCd = sortList.get(i).svcContrBrCd.getValue();
                String targetTocCd = sortList.get(i).tocCd.getValue();
                if (targetKey != null && targetKey.equals(masterKey)) {
                    if (masterDsContrNum != null && !masterDsContrNum.equals(targetDsContrNum)) {
                        setErrorMessage(prmPmsg, NSXM0055E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0055E);
                        errorCount++;
                    }
                    if (masterDsContrIntfcActCd != null && !masterDsContrIntfcActCd.equals(targetDsContrIntfcActCd)) {
                        setErrorMessage(prmPmsg, NSXM0056E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0056E);
                        errorCount++;
                    }
                    if (masterDsContrCatgCd != null && !masterDsContrCatgCd.equals(targetDsContrCatgCd)) {
                        setErrorMessage(prmPmsg, NSXM0057E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0057E);
                        errorCount++;
                    }
                    // 2016/03/28 START [QC#5822, MOD]
                    if (isCheckSvcContrBrCd(sortList.get(i))) {
                        if (masterSvcContrBrCd != null && !masterSvcContrBrCd.equals(targetSvcContrBrCd)) {
                            setErrorMessage(prmPmsg, NSXM0058E, errorTotalCount + errorCount);
                            setErrorStatusAndMessage(sortList.get(i), NSXM0058E);
                            errorCount++;
                        }
                    }
                    // 2016/03/28 END   [QC#5822, MOD]

                    // 2016/03/28 START [QC#5822, MOD]
                    if (isCheckTocCd(sortList.get(i))) {
                        if (masterTocCd != null && !masterTocCd.equals(targetTocCd)) {
                            setErrorMessage(prmPmsg, NSXM0059E, errorTotalCount + errorCount);
                            setErrorStatusAndMessage(sortList.get(i), NSXM0059E);
                            errorCount++;
                        }
                    }
                    // 2016/03/28 END   [QC#5822, MOD]
                } else {
                    masterKey = sortList.get(i).contrIntfcSrcTpCd.getValue() + "," + sortList.get(i).dsContrSrcRefNum.getValue();
                    masterDsContrNum = sortList.get(i).dsContrNum.getValue();
                    masterDsContrIntfcActCd = sortList.get(i).dsContrIntfcActCd.getValue();
                    masterDsContrCatgCd = sortList.get(i).dsContrCatgCd.getValue();
                    masterSvcContrBrCd = sortList.get(i).svcContrBrCd.getValue();
                    masterTocCd = sortList.get(i).tocCd.getValue();
                }
            }
        }
        return errorCount;
    }

    private static int detailCheck(NSXC001001PMsg prmPmsg, List<DS_CONTR_INTFCTMsg> tMsgList, int errorTotalCount) {

        int errorCount = 0;

        List<DS_CONTR_INTFCTMsg> sortList = getSortListForDetail(tMsgList);
        String masterKey = null;
        String masterMdseCd = null;
        String masterSerNum = null;
        BigDecimal masterMdlId = null;
        String masterContrFromDt = null;
        String masterContrThruDt = null;
        String masterSvcPgmMdseCd = null;

        int baseCnt = 0;
        for (int i = 0; i < sortList.size(); i++) {
            if (i == 0) {
                if (!hasValue(sortList.get(i).svcMachMstrPk)) {
                    continue;
                }
                masterKey = sortList.get(i).contrIntfcSrcTpCd.getValue() + "," + sortList.get(i).dsContrSrcRefNum.getValue() + "," + sortList.get(i).svcMachMstrPk.getValue().toString();
                masterMdseCd = sortList.get(i).mdseCd.getValue();
                masterSerNum = sortList.get(i).serNum.getValue();
                masterMdlId = sortList.get(i).mdlId.getValue();
                masterContrFromDt = sortList.get(i).contrFromDt.getValue();
                masterContrThruDt = sortList.get(i).contrThruDt.getValue();
                masterSvcPgmMdseCd = sortList.get(i).svcPgmMdseCd.getValue();
                if (CONTR_INTFC_DTL_TP.BASE.equals(sortList.get(i).contrIntfcDtlTpCd.getValue())) {
                    baseCnt++;
                }
            }
            if (i > 0) {
                if (!hasValue(sortList.get(i).svcMachMstrPk)) {
                    continue;
                }
                String targetKey = sortList.get(i).contrIntfcSrcTpCd.getValue() + "," + sortList.get(i).dsContrSrcRefNum.getValue() + "," + sortList.get(i).svcMachMstrPk.getValue().toString();
                String targetMdseCd = sortList.get(i).mdseCd.getValue();
                String targetSerNum = sortList.get(i).serNum.getValue();
                BigDecimal targetMdlId = sortList.get(i).mdlId.getValue();
                String targetContrFromDt = sortList.get(i).contrFromDt.getValue();
                String targetContrThruDt = sortList.get(i).contrThruDt.getValue();
                String targetSvcPgmMdseCd = sortList.get(i).svcPgmMdseCd.getValue();
                if (targetKey != null && targetKey.equals(masterKey)) {
                    if (masterMdseCd != null && !masterMdseCd.equals(targetMdseCd)) {
                        setErrorMessage(prmPmsg, NSXM0060E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0060E);
                        errorCount++;
                    }
                    if (masterSerNum != null && !masterSerNum.equals(targetSerNum)) {
                        setErrorMessage(prmPmsg, NSXM0061E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0061E);
                        errorCount++;
                    }
                    if (masterMdlId != null && !masterMdlId.equals(targetMdlId)) {
                        setErrorMessage(prmPmsg, NSXM0062E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0062E);
                        errorCount++;
                    }
                    if (masterContrFromDt != null && !masterContrFromDt.equals(targetContrFromDt)) {
                        setErrorMessage(prmPmsg, NSXM0063E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0063E);
                        errorCount++;
                    }
                    if (masterContrThruDt != null && !masterContrThruDt.equals(targetContrThruDt)) {
                        setErrorMessage(prmPmsg, NSXM0064E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0064E);
                        errorCount++;
                    }
                    if (masterSvcPgmMdseCd != null && !masterSvcPgmMdseCd.equals(targetSvcPgmMdseCd)) {
                        setErrorMessage(prmPmsg, NSXM0065E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0065E);
                        errorCount++;
                    }
                    if (CONTR_INTFC_DTL_TP.BASE.equals(sortList.get(i).contrIntfcDtlTpCd.getValue())) {
                        baseCnt++;
                    }
                    if (baseCnt > 1) {
                        setErrorMessage(prmPmsg, NSXM0066E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0066E);
                        errorCount++;
                    }
                } else {
                    if (!hasValue(sortList.get(i).svcMachMstrPk)) {
                        continue;
                    }
                    masterKey = sortList.get(i).contrIntfcSrcTpCd.getValue() + "," + sortList.get(i).dsContrSrcRefNum.getValue() + "," + sortList.get(i).svcMachMstrPk.getValue().toString();
                    masterMdseCd = sortList.get(i).mdseCd.getValue();
                    masterSerNum = sortList.get(i).serNum.getValue();
                    masterMdlId = sortList.get(i).mdlId.getValue();
                    masterContrFromDt = sortList.get(i).contrFromDt.getValue();
                    masterContrThruDt = sortList.get(i).contrThruDt.getValue();
                    // Add Start 2018/09/25 QC#28360
                    masterSvcPgmMdseCd = sortList.get(i).svcPgmMdseCd.getValue();
                    // Add End 2018/09/25 QC#28360
                    baseCnt = 0;
                }
            }
        }
        return errorCount;
    }

    private static int renewalEscalationCheck(NSXC001001PMsg prmPmsg, List<DS_CONTR_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        List<DS_CONTR_INTFCTMsg> sortList = getSortListForRenewalEscalation(tMsgList);
        String masterKey = null;
        String masterContrAutoRnwTpCd = null;
        String masterRnwPrcMethCd = null;
        BigDecimal masterBefEndRnwDaysAot = null;
        BigDecimal masterRnwPrcUpRatiot = null;
        String masterContrUplftTpCd = null;
        String masterUplftPrcMethCd = null;
        BigDecimal masterUplftPrcUpRatio = null;

        for (int i = 0; i < sortList.size(); i++) {
            if (!CONTR_INTFC_DTL_TP.USAGE.equals(sortList.get(i).contrIntfcDtlTpCd.getValue())) {
                continue;
            }
            if (i == 0 || !hasValue(masterKey)) {
                if (!hasValue(sortList.get(i).svcMachMstrPk)) {
                    continue;
                }
                masterKey = sortList.get(i).contrIntfcSrcTpCd.getValue() + "," + sortList.get(i).dsContrSrcRefNum.getValue() + "," + sortList.get(i).svcMachMstrPk.getValue().toString() + "," + sortList.get(i).contrIntfcDtlTpCd.getValue();
                masterContrAutoRnwTpCd = sortList.get(i).contrAutoRnwTpCd.getValue();
                masterRnwPrcMethCd = sortList.get(i).rnwPrcMethCd.getValue();
                masterBefEndRnwDaysAot = sortList.get(i).befEndRnwDaysAot.getValue();
                masterRnwPrcUpRatiot = sortList.get(i).rnwPrcUpRatio.getValue();
                masterContrUplftTpCd = sortList.get(i).contrUplftTpCd.getValue();
                masterUplftPrcMethCd = sortList.get(i).uplftPrcMethCd.getValue();
                masterUplftPrcUpRatio = sortList.get(i).uplftPrcUpRatio.getValue();
            }
            if (i > 0) {
                if (!hasValue(sortList.get(i).svcMachMstrPk)) {
                    continue;
                }
                String targetKey = sortList.get(i).contrIntfcSrcTpCd.getValue() + "," + sortList.get(i).dsContrSrcRefNum.getValue() + "," + sortList.get(i).svcMachMstrPk.getValue().toString() + "," + sortList.get(i).contrIntfcDtlTpCd.getValue();
                String targetContrAutoRnwTpCd = sortList.get(i).contrAutoRnwTpCd.getValue();
                String targetRnwPrcMethCd = sortList.get(i).rnwPrcMethCd.getValue();
                BigDecimal targetBefEndRnwDaysAot = sortList.get(i).befEndRnwDaysAot.getValue();
                BigDecimal targetRnwPrcUpRatiot = sortList.get(i).rnwPrcUpRatio.getValue();
                String targetContrUplftTpCd = sortList.get(i).contrUplftTpCd.getValue();
                String targetUplftPrcMethCd = sortList.get(i).uplftPrcMethCd.getValue();
                BigDecimal targetUplftPrcUpRatio = sortList.get(i).uplftPrcUpRatio.getValue();

                if (targetKey != null && targetKey.equals(masterKey)) {
                    if (masterContrAutoRnwTpCd != null && !masterContrAutoRnwTpCd.equals(targetContrAutoRnwTpCd)) {
                        setErrorMessage(prmPmsg, NSXM0067E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0067E);
                        errorCount++;
                    }
                    if (masterRnwPrcMethCd != null && !masterRnwPrcMethCd.equals(targetRnwPrcMethCd)) {
                        setErrorMessage(prmPmsg, NSXM0068E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0068E);
                        errorCount++;
                    }
                    if (masterBefEndRnwDaysAot != null && !masterBefEndRnwDaysAot.equals(targetBefEndRnwDaysAot)) {
                        setErrorMessage(prmPmsg, NSXM0069E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0069E);
                        errorCount++;
                    }
                    if (masterRnwPrcUpRatiot != null && !masterRnwPrcUpRatiot.equals(targetRnwPrcUpRatiot)) {
                        setErrorMessage(prmPmsg, NSXM0070E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0070E);
                        errorCount++;
                    }
                    if (masterContrUplftTpCd != null && !masterContrUplftTpCd.equals(targetContrUplftTpCd)) {
                        setErrorMessage(prmPmsg, NSXM0071E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0071E);
                        errorCount++;
                    }
                    if (masterUplftPrcMethCd != null && !masterUplftPrcMethCd.equals(targetUplftPrcMethCd)) {
                        setErrorMessage(prmPmsg, NSXM0072E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0072E);
                        errorCount++;
                    }
                    if (masterUplftPrcUpRatio != null && !masterUplftPrcUpRatio.equals(targetUplftPrcUpRatio)) {
                        setErrorMessage(prmPmsg, NSXM0073E, errorTotalCount + errorCount);
                        setErrorStatusAndMessage(sortList.get(i), NSXM0073E);
                        errorCount++;
                    }

                } else {
                    if (!hasValue(sortList.get(i).svcMachMstrPk)) {
                        continue;
                    }
                    masterKey = sortList.get(i).contrIntfcSrcTpCd.getValue() + "," + sortList.get(i).dsContrSrcRefNum.getValue() + "," + sortList.get(i).svcMachMstrPk.getValue().toString() + "," + sortList.get(i).contrIntfcDtlTpCd.getValue();
                    masterContrAutoRnwTpCd = sortList.get(i).contrAutoRnwTpCd.getValue();
                    masterRnwPrcMethCd  = sortList.get(i).rnwPrcMethCd.getValue();
                    masterBefEndRnwDaysAot = sortList.get(i).befEndRnwDaysAot.getValue();
                    masterRnwPrcUpRatiot = sortList.get(i).rnwPrcUpRatio.getValue();
                    masterContrUplftTpCd  = sortList.get(i).contrUplftTpCd.getValue();
                    masterUplftPrcMethCd = sortList.get(i).uplftPrcMethCd.getValue();
                    masterUplftPrcUpRatio = sortList.get(i).uplftPrcUpRatio.getValue();
                }
            }
        }
        return errorCount;
    }

    private static List<DS_CONTR_INTFCTMsg> getSortListForHeader(List<DS_CONTR_INTFCTMsg> tMsgList) {

        List<DS_CONTR_INTFCTMsg> sortList = new ArrayList<DS_CONTR_INTFCTMsg>();
        for (int i = 0; i < tMsgList.size(); i++) {
            sortList.add(tMsgList.get(i));
        }

        Collections.sort(sortList, new Comparator<DS_CONTR_INTFCTMsg>() {

            public int compare(DS_CONTR_INTFCTMsg record0, DS_CONTR_INTFCTMsg record1) {

                String contrIntfcSrcTpCd0 = record0.contrIntfcSrcTpCd.getValue();
                String contrIntfcSrcTpCd1 = record1.contrIntfcSrcTpCd.getValue();
                String dsContrSrcRefNum0 = record0.dsContrSrcRefNum.getValue();
                String dsContrSrcRefNum1 = record1.dsContrSrcRefNum.getValue();
                String key0 = contrIntfcSrcTpCd0 + "," + dsContrSrcRefNum0;
                String key1 = contrIntfcSrcTpCd1 + "," + dsContrSrcRefNum1;

                return (key0).compareTo(key1);
            }
        });

        return sortList;
    }

    private static List<DS_CONTR_INTFCTMsg> getSortListForDetail(List<DS_CONTR_INTFCTMsg> tMsgList) {

        List<DS_CONTR_INTFCTMsg> sortList = new ArrayList<DS_CONTR_INTFCTMsg>();
        for (int i = 0; i < tMsgList.size(); i++) {
            // 2019/05/31 START K.Fujimoto [QC#50613, MOD]
            DS_CONTR_INTFCTMsg tMsg = tMsgList.get(i);
            if (isRentalSplit(tMsg.glblCmpyCd.getValue(), tMsg.svcPgmMdseCd.getValue())) {
                continue;
            }
            // sortList.add(tMsgList.get(i));
            sortList.add(tMsg);
            // 2019/05/31 END   K.Fujimoto [QC#50613, MOD]
        }

        Collections.sort(sortList, new Comparator<DS_CONTR_INTFCTMsg>() {

            public int compare(DS_CONTR_INTFCTMsg record0, DS_CONTR_INTFCTMsg record1) {

                String contrIntfcSrcTpCd0 = record0.contrIntfcSrcTpCd.getValue();
                String contrIntfcSrcTpCd1 = record1.contrIntfcSrcTpCd.getValue();
                String dsContrSrcRefNum0 = record0.dsContrSrcRefNum.getValue();
                String dsContrSrcRefNum1 = record1.dsContrSrcRefNum.getValue();
                BigDecimal svcMachMstrPk0 = record0.svcMachMstrPk.getValue();
                BigDecimal svcMachMstrPk1 = record1.svcMachMstrPk.getValue();
                String strSvcMachMstrPk0 = null;
                String strSvcMachMstrPk1 = null;
                if (hasValue(record0.svcMachMstrPk)) {
                    strSvcMachMstrPk0 = svcMachMstrPk0.toString();
                }
                if (hasValue(record1.svcMachMstrPk)) {
                    strSvcMachMstrPk1 = svcMachMstrPk1.toString();
                }
                String key0 = contrIntfcSrcTpCd0 + "," + dsContrSrcRefNum0 + "," + strSvcMachMstrPk0;
                String key1 = contrIntfcSrcTpCd1 + "," + dsContrSrcRefNum1 + "," + strSvcMachMstrPk1;

                return (key0).compareTo(key1);
            }
        });

        return sortList;
    }

    private static List<DS_CONTR_INTFCTMsg> getSortListForRenewalEscalation(List<DS_CONTR_INTFCTMsg> tMsgList) {

        List<DS_CONTR_INTFCTMsg> sortList = new ArrayList<DS_CONTR_INTFCTMsg>();
        for (int i = 0; i < tMsgList.size(); i++) {
            sortList.add(tMsgList.get(i));
        }

        Collections.sort(sortList, new Comparator<DS_CONTR_INTFCTMsg>() {

            public int compare(DS_CONTR_INTFCTMsg record0, DS_CONTR_INTFCTMsg record1) {

                String contrIntfcSrcTpCd0 = record0.contrIntfcSrcTpCd.getValue();
                String contrIntfcSrcTpCd1 = record1.contrIntfcSrcTpCd.getValue();
                String dsContrSrcRefNum0 = record0.dsContrSrcRefNum.getValue();
                String dsContrSrcRefNum1 = record1.dsContrSrcRefNum.getValue();
                BigDecimal svcMachMstrPk0 = record0.svcMachMstrPk.getValue();
                BigDecimal svcMachMstrPk1 = record1.svcMachMstrPk.getValue();
                String strSvcMachMstrPk0 = null;
                String strSvcMachMstrPk1 = null;
                String contrIntfcDtlTpCd0 = record0.contrIntfcDtlTpCd.getValue();
                String contrIntfcDtlTpCd1 = record1.contrIntfcDtlTpCd.getValue();
                if (hasValue(record0.svcMachMstrPk)) {
                    strSvcMachMstrPk0 = svcMachMstrPk0.toString();
                }
                if (hasValue(record1.svcMachMstrPk)) {
                    strSvcMachMstrPk1 = svcMachMstrPk1.toString();
                }
                String key0 = contrIntfcSrcTpCd0 + "," + dsContrSrcRefNum0 + "," + strSvcMachMstrPk0 + "," + contrIntfcDtlTpCd0;
                String key1 = contrIntfcSrcTpCd1 + "," + dsContrSrcRefNum1 + "," + strSvcMachMstrPk1 + "," + contrIntfcDtlTpCd1;

                return (key0).compareTo(key1);
            }
        });

        return sortList;
    }

    private static void setErrorMessage(NSXC001001PMsg prmPmsg, String errorMsgId, int errCnt) {
        if (prmPmsg == null || errorMsgId == null) {
            return;
        }
        if (errCnt >= prmPmsg.xxMsgIdList.length()) {
            return;
        }
        setValue(prmPmsg.xxMsgIdList.no(errCnt).xxMsgId, errorMsgId);
    }

    private static void setErrorStatusAndMessage(DS_CONTR_INTFCTMsg tMsg, String errorMsgId) {
        if (tMsg == null || errorMsgId == null) {
            return;
        }
        if (!hasValue(tMsg.intfcErrMsgTxt)) {
            setValue(tMsg.intfcErrMsgTxt, S21MessageFunc.clspGetMessage(errorMsgId));
        }
    }

    /**
     * <pre>
     * updateValidationResult
     * </pre>
     * @param prmPmsg NSXC001001PMsg
     * @param rstMsgList List<DS_CONTR_INTFCTMsg>
     */
    public static void updateValidationResult(NSXC001001PMsg prmPmsg, List<DS_CONTR_INTFCTMsg> rstMsgList) {
        int errCnt = 0;
        for (int i = 0; i < rstMsgList.size(); i++) {
            DS_CONTR_INTFCTMsg rstMsg = rstMsgList.get(i);
            if (hasValue(rstMsg.dsContrIntfcPk)) {
                // for update
                DS_CONTR_INTFCTMsg tMsg = getContrIntfc(rstMsg.glblCmpyCd.getValue(), rstMsg.dsContrIntfcPk.getValue());
                if (tMsg == null) {
                    setErrorMessage(prmPmsg, ZZZM9004E, errCnt);
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(rstMsg.ezUpTime.getValue(), rstMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    setErrorMessage(prmPmsg, ZZZM9004E, errCnt);
                    return;
                }
                if (hasValue(rstMsg.intfcErrMsgTxt)) {
                    setValue(tMsg.intfcErrMsgTxt, rstMsg.intfcErrMsgTxt);
                    setValue(tMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                } else {
                    setValue(tMsg.intfcErrMsgTxt, (String) null);
                    setValue(tMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    setErrorMessage(prmPmsg, NSXM0074E, errCnt);
                    setErrorStatusAndMessage(tMsg, NSXM0074E);

                    return;
                }
            }
        }
    }

    private static DS_CONTR_INTFCTMsg getContrIntfc(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        DS_CONTR_INTFCTMsg prmTMsg = new DS_CONTR_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrIntfcPk, dsContrIntfcPk);
        return (DS_CONTR_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    // 2016/03/28 START [QC#5822, ADD]
    private static boolean isCheckBasePrcDealAmt(DS_CONTR_INTFCTMsg tMsg) {
        if (DS_CONTR_CATG.WARRANTY.equals(tMsg.dsContrCatgCd.getValue())) {
            return false;
        } else if (DS_CONTR_CATG.REGULAR.equals(tMsg.dsContrCatgCd.getValue())) {
            if (CONTR_INTFC_DTL_TP.BASE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
                return true;
            }
        } else if (DS_CONTR_CATG.FLEET.equals(tMsg.dsContrCatgCd.getValue())) {
            if (!hasValue(tMsg.svcMachMstrPk)) {
                if (CONTR_INTFC_DTL_TP.BASE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
                    return true;
                }
            }
        } else if (DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue())) {
            if (hasValue(tMsg.svcMachMstrPk)) {
                if (CONTR_INTFC_DTL_TP.BASE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCheckTocCd(DS_CONTR_INTFCTMsg tMsg) {
        if (DS_CONTR_CATG.FLEET.equals(tMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue())) {
            if (hasValue(tMsg.svcMachMstrPk)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCheckSvcContrBrCd(DS_CONTR_INTFCTMsg tMsg) {
        if (DS_CONTR_CATG.FLEET.equals(tMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue())) {
            if (hasValue(tMsg.svcMachMstrPk)) {
                return false;
            }
        }
        return true;
    }
    // 2016/03/28 END   [QC#5822, ADD]

    private static int endOfLifeCheck(NSXC001001PMsg prmPmsg, DS_CONTR_INTFCTMsg tMsg, int errorTotalCount) {
        int errorCount = 0;

        // START 2017/05/18 K.Kitachi [QC#18343, MOD]
//      if (tMsg == null || !hasValue(tMsg.svcMachMstrPk)) {
        if (tMsg == null || !hasValue(tMsg.svcMachMstrPk) || DS_CONTR_INTFC_ACT.DELETE.equals(tMsg.dsContrIntfcActCd.getValue()) || DS_CONTR_INTFC_ACT.TERMINATE.equals(tMsg.dsContrIntfcActCd.getValue())) {
        // END 2017/05/18 K.Kitachi [QC#18343, MOD]
            return errorCount;
        }

        // set parameter
        EndOfLifeBean bean = new EndOfLifeBean();
        bean.setGlblCmpyCd(tMsg.glblCmpyCd.getValue());
        bean.setSvcMachMstrPk(tMsg.svcMachMstrPk.getValue());

        String salesDate = ZYPDateUtil.getSalesDate(tMsg.glblCmpyCd.getValue());
        if (salesDate.compareTo(tMsg.contrFromDt.getValue()) >= 0) {
            bean.setEolDt(salesDate);
        } else {
            bean.setEolDt(tMsg.contrFromDt.getValue());
        }

        if (NSXC001001GetEndOfLifeInfo.getEndOfLifeInfo(bean)) {
            if (ZYPConstant.FLG_OFF_N.equals(bean.getContrAvalFlg())) {
                setErrorMessage(prmPmsg, NSAM0479E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSAM0479E);
                errorCount++;
            }
        } else {
            if (prmPmsg.xxMsgIdList.getValidCount() < prmPmsg.xxMsgIdList.length()) {
                setErrorMessage(prmPmsg, bean.getXxMsgId(), errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, bean.getXxMsgId());
                errorCount++;
            }
        }

        return errorCount;
    }

    // START 2017/06/02 K.Kitachi [QC#18568, ADD]
    private static int addToContrCheck(NSXC001001PMsg prmPmsg, DS_CONTR_INTFCTMsg tMsg, int errorTotalCount) {
        int errorCount = 0;
        if (!DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(tMsg.dsContrIntfcActCd.getValue())) {
            return errorCount;
        }
        if (!DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue())) {
            return errorCount;
        }
        Map<String, Object> aggLine = getAggLine(tMsg);
        if (aggLine == null) {
            setErrorMessage(prmPmsg, NSXM0123E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0123E);
            errorCount++;
            return errorCount;
        }
        if (!((String) aggLine.get("CONTR_BLLG_DAY")).equals(tMsg.contrBllgDay.getValue())) {
            setErrorMessage(prmPmsg, NSXM0124E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0124E);
            errorCount++;
        }
        if (CONTR_INTFC_DTL_TP.BASE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
            if (!((String) aggLine.get("BASE_DPLY_PER_END_DAY")).equals(tMsg.contrCloDay.getValue())) {
                setErrorMessage(prmPmsg, NSXM0125E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0125E);
                errorCount++;
            }
        }
        if (CONTR_INTFC_DTL_TP.USAGE.equals(tMsg.contrIntfcDtlTpCd.getValue())) {
            if (!((String) aggLine.get("MTR_DPLY_PER_END_DAY")).equals(tMsg.contrCloDay.getValue())) {
                setErrorMessage(prmPmsg, NSXM0125E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0125E);
                errorCount++;
            }
            if (!isExistsAggLineBllgMtrLb(tMsg)) {
                setErrorMessage(prmPmsg, NSXM0126E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0126E);
                errorCount++;
            }
        }
        return errorCount;
    }

    private static Map<String, Object> getAggLine(DS_CONTR_INTFCTMsg tMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        param.put("dsContrNum", tMsg.dsContrNum.getValue());
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);

        return (Map<String, Object>) SSM_CLIENT.queryObject("getAggLine", param);
    }

    private static boolean isExistsAggLineBllgMtrLb(DS_CONTR_INTFCTMsg tMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        param.put("dsContrNum", tMsg.dsContrNum.getValue());
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        param.put("bllgMtrLbCd", tMsg.bllgMtrLbCd.getValue());

        BigDecimal count = (BigDecimal) SSM_CLIENT.queryObject("getAggLineBllgMtrLbCount", param);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }
    // END 2017/06/02 K.Kitachi [QC#18568, ADD]

    // 2019/05/31 START K.Fujimoto [QC#50613, ADD]
    private static boolean isRentalSplit(String glblCmpyCd, String mdseCd) {
        MDSETMsg tMsg = getMdse(glblCmpyCd, mdseCd);
        if (tMsg != null) {
            if (tMsg.svcPgmTpCd.getValue().equals(SVC_PGM_TP.RENTALSPLIT)) {
                return true;
            }
        }
        return false;
    }
    // 2019/05/31 END   K.Fujimoto [QC#50613, ADD]
}
