/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC610001;

import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.CONST_NMZC6100_RELATED_BILL_SHIP_CNT;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.MAX_DATE_VALUE;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0009E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0011E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0024E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0054E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0055E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0056E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0063E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0350E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0363E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0364E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0365E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0366E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.NMZM0367E;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.ORD_CATG_CTX_TP_CD_EQUIPMENT;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.ORD_CATG_CTX_TP_CD_SUPPLIES;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.PROCESS_MODE_INSTRUCTION;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.PROCESS_MODE_INSTRUCTION_FOR_INV_PRINT;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.PROCESS_MODE_INVOICE;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.PROCESS_MODE_RELATED_BILL_SHIP;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.PROCESS_MODE_SHIPPING_DEFAULT_INFORMATION;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.PROCESS_MODE_SPECIAL_HANDLING;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.PROCESS_MODE_TRANSACTION;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.RELATED_BILL_SHIP_ALL_RGTN_STS_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.SEARCH_LVL_ACCT;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.SEARCH_LVL_LOC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_CustRefListPMsg;
import business.parts.NMZC610001_DefaultBillShipListPMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NMZC610001_InstructionListPMsg;
import business.parts.NMZC610001_InvoiceRuleListPMsg;
import business.parts.NMZC610001_RelatedBillShipListPMsg;
import business.parts.NMZC610001_ShippingDefaultInfoListPMsg;
import business.parts.NMZC610001_SpecialHandlingListPMsg;
import business.parts.NMZC610001_TransactionRuleListPMsg;
import business.parts.NMZC610002PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_EFF_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Customer Information Get API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/15   Fujitsu         N.Sugiura       Create          N/A
 * 2016/05/19   Fujitsu         N.Sugiura       Update          QC#8372
 * 2016/05/27   Fujitsu         M.Suzuki        Update          QC#9073
 * 2016/06/13   SRAA            Y.Chen          Update          QC#7781
 * 2016/07/08   Fujitsu         T.Ogura         Update          QC#8659
 * 2016/08/12   SRAA            K.Aratani       Update          QC#13426
 * 2016/09/09   SRAA            Y.Chen          Update          QC#10534
 * 2017/01/06   Fujitsu         R.Nakamura      Update          QC#16602
 * 2017/08/22   Fujitsu         S.Takami        Update          S21_NA#20183
 * 2017/08/28   Fujitsu         S.Takami        Update          S21_NA#20183-2
 * 2017/11/16   Fujitsu         H.Sugawara      Update          QC#17322(Sol#174)
 * 2018/02/07   Fujitsu         Hd.Sugawara     Update          QC#20297(Sol#379)
 * 2018/03/20   Fujitsu         Hd.Sugawara     Update          QC#25000
 * 2018/08/24   Fujitsu         W,Honda         Update          QC#27425
 * 2018/10/23   Fujitsu         K.Ishizuka      Update          QC#28721
 * 2018/12/10   Fujitsu         H.Kumagai       Update          QC#29315
 * 2019/01/31   Fujitsu         Hd.Sugawara     Update          QC#30097
 * 2019/02/14   Fujitsu         K.Kato          Update          QC#30308
 * 2019/03/27   Fujitsu         Hd.Sugawara     Update          QC#30955
 * 2019/08/28   Fujitsu         S.Kosaka        Update          QC#53021
 * 2020/06/26   CITS            K.Ogino         Update          QC#57239
 * 2022/11/25   Hitachi         H.Watanabe      Update          QC#60398
 * 2023/07/18   Hitachi         T.Doi           Update          QC#61421
 *</pre>
 */
public class NMZC610001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Account Number */
    private String acctNum = "";

    /** Location Number */
    private String locNum = "";

    /**
     * Constructor
     */
    public NMZC610001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }
    /**
     * <pre>
     * Customer Information Get API (List)
     * </pre>
     * @param params      Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(List<NMZC610001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NMZC610001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }
    /**
     * <pre>
     * Customer Information Get API
     * </pre>
     * @param param      Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(final NMZC610001PMsg param, final ONBATCH_TYPE onBatchType) {

        // Checking Input value
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (!checkInput(msgMap)) {
            msgMap.flush();
            return;
        }

        // main
        doProcess(msgMap);
        // end
        msgMap.flush();

    }
    /**
     * Input parameter check.
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean checkInput(S21ApiMessageMap msgMap) {

        boolean returnValue = true;

        NMZC610001PMsg param = (NMZC610001PMsg) msgMap.getPmsg();

        // Common mandatory check
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NMZM0009E);
            returnValue = false;
        }

        if (!ZYPCommonFunc.hasValue(param.xxModeCd)) {
            msgMap.addXxMsgId(NMZM0054E);
            returnValue = false;
        } else {
            if (!PROCESS_MODE_TRANSACTION.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_INVOICE.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_INSTRUCTION.equals(param.xxModeCd.getValue())
                    && !PROCESS_DEFAULT_BILL_SHIP.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_SPECIAL_HANDLING.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_ELIGIBLE_CHECK.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_RELATED_BILL_SHIP.equals(param.xxModeCd.getValue())
                    // Mod Start 2018/02/07 QC#20297(Sol#379)
                    //&& !PROCESS_MODE_INSTRUCTION_FOR_INV_PRINT.equals(param.xxModeCd.getValue())) {
                    && !PROCESS_MODE_INSTRUCTION_FOR_INV_PRINT.equals(param.xxModeCd.getValue()) //
                    && !PROCESS_MODE_SHIPPING_DEFAULT_INFORMATION.equals(param.xxModeCd.getValue())) {
                // Mod End 2018/02/07 QC#20297(Sol#379)
                msgMap.addXxMsgId(NMZM0055E);
                returnValue = false;
            }
        }

        // Mandatory check for mode 01
        if (PROCESS_MODE_TRANSACTION.equals(param.xxModeCd.getValue())) {
            // Transaction Type
            if (!ZYPCommonFunc.hasValue(param.dsTrxRuleTpCd)) {
                msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Transaction Type"});
                returnValue = false;
            }
            // Bll to Cust Code or Ship to Cust Code or Account Number
            if (!chkAcctBillShip(msgMap)) {
                returnValue = false;
            }
        }
        // Mandatory check for mode 02
        if (PROCESS_MODE_INVOICE.equals(param.xxModeCd.getValue())) {
            // Invoice Type
            if (!ZYPCommonFunc.hasValue(param.custInvSrcCd)) {
                msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Invoice Type"});
                returnValue = false;
            }
            // Bll to Cust Code or Ship to Cust Code or Account Number
            if (!chkAcctBillShip(msgMap)) {
                returnValue = false;
            }
        }
        // Mandatory check for mode 03
        if (PROCESS_MODE_INSTRUCTION.equals(param.xxModeCd.getValue())) {
            // Line of Business Type
            if (!ZYPCommonFunc.hasValue(param.lineBizTpCd)) {
                msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Line of Business Type"});
                returnValue = false;
            }
            // Business Area
            if (!ZYPCommonFunc.hasValue(param.dsBizAreaCd)) {
                msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Business Area"});
                returnValue = false;
            }
             // Message Type
            if (!ZYPCommonFunc.hasValue(param.dsCustMsgTpCd)) {
                msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Message Type"});
                returnValue = false;
            }
            // Bll to Cust Code or Ship to Cust Code or Account Number
            if (!chkAcctBillShip(msgMap)) {
                returnValue = false;
            }
            // Sales Date
            if (!chkSlsDt(msgMap, param.slsDt.getValue())) {
                returnValue = false;
            }
        }
        // Mandatory check for mode 04
        if (PROCESS_DEFAULT_BILL_SHIP.equals(param.xxModeCd.getValue())) {
            // Account Number
            if (!chkAcctNum(msgMap, param.dsAcctNum_I1.getValue())) {
                returnValue = false;
            }
            // Add Start 2019/01/31 QC#30097
            if (!(ZYPCommonFunc.hasValue(param.xxSelFlg) && ZYPConstant.FLG_ON_Y.equals(param.xxSelFlg.getValue()))) {
                // xxSelFlg Y:Select own account and related account. / other(default):Select own account only.
                ZYPEZDItemValueSetter.setValue(param.xxSelFlg, ZYPConstant.FLG_OFF_N);
            }
            // Add End 2019/01/31 QC#30097
        }
        // Mandatory check for mode 05
        if (PROCESS_MODE_SPECIAL_HANDLING.equals(param.xxModeCd.getValue())) {
            // Special Instruction Type
            if (!ZYPCommonFunc.hasValue(param.dsSpclHdlgTpCd)) {
                msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Special Instruction Type"});
                returnValue = false;
            }
            // Account Number
            if (!chkAcctNum(msgMap, param.dsAcctNum_I1.getValue())) {
                returnValue = false;
            }
        }
        // Mandatory check for mode 06
        if (PROCESS_MODE_ELIGIBLE_CHECK.equals(param.xxModeCd.getValue())) {
            // Original Account Code
            if (!ZYPCommonFunc.hasValue(param.dsAcctNum_I2)) {
                msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Original Account Code"});
                returnValue = false;
            }
            // Bll to Cust Code or Ship to Cust Code or Account Number
            if (!chkAcctBillShip(msgMap)) {
                returnValue = false;
            }
        }
        // Mandatory check for mode 07
        if (PROCESS_MODE_RELATED_BILL_SHIP.equals(param.xxModeCd.getValue())) {
            // Add Start 2018/03/20 QC#25000
            // Sub Mode Code
            if (ZYPCommonFunc.hasValue(param.xxModeCd_SB)) {
                if (!RELATED_BILL_SHIP_ALL_RGTN_STS_CD.equals(param.xxModeCd_SB.getValue())) {
                    msgMap.addXxMsgId(NMZM0024E);
                    returnValue = false;
                }
            }
            // Add End 2018/03/20 QC#25000

            // Account Number
            if (!chkAcctNum(msgMap, param.dsAcctNum_I1.getValue())) {
                returnValue = false;
            }
        }
        // Mandatory check for mode for Invoice Print 08
        if (PROCESS_MODE_INSTRUCTION_FOR_INV_PRINT.equals(param.xxModeCd.getValue())) {
            // Line of Business Type
            if (!ZYPCommonFunc.hasValue(param.lineBizTpCd)) {
                msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Line of Business Type"});
                returnValue = false;
            }
            // Business Area
            // Mod Start 2017/11/16 QC#17322(Sol#174)
            //if (param.inDsBizAreaCdList == null && param.inDsBizAreaCdList.getValidCount() == 0) {
            if (param.inDsBizAreaCdList == null || param.inDsBizAreaCdList.getValidCount() == 0) {
                // Mod End 2017/11/16 QC#17322(Sol#174)
                msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Business Area"});
                returnValue = false;
            }
             // Message Type
            // Mod Start 2017/11/16 QC#17322(Sol#174)
            //if (param.inDsCustMsgTpCdList == null && param.inDsCustMsgTpCdList.getValidCount() == 0) {
            if (param.inDsCustMsgTpCdList == null || param.inDsCustMsgTpCdList.getValidCount() == 0) {
                // Mod End 2017/11/16 QC#17322(Sol#174)
                msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Message Type"});
                returnValue = false;
            }
            // Bll to Cust Code or Ship to Cust Code or Account Number
            if (!chkAcctBillShip(msgMap)) {
                returnValue = false;
            }
            // Sales Date
            if (!chkSlsDt(msgMap, param.slsDt.getValue())) {
                returnValue = false;
            }
        }

        // Add Start 2018/02/07 QC#20297(Sol#379)
        // Mandatory check for mode for Shipping Default Information
        // Mode : 09
        if (PROCESS_MODE_SHIPPING_DEFAULT_INFORMATION.equals(param.xxModeCd.getValue())) {
            // Sales Date
            if (!chkSlsDt(msgMap, param.slsDt.getValue())) {
                returnValue = false;
            }
            // DS Order Category Code
            if (!ZYPCommonFunc.hasValue(param.dsOrdCatgCd)) {
                msgMap.addXxMsgId(NMZM0363E);
                returnValue = false;
            }
            // DS Order Type Code
            if (!ZYPCommonFunc.hasValue(param.dsOrdTpCd)) {
                msgMap.addXxMsgId(NMZM0364E);
                returnValue = false;
            }
            // Ship To Account Number
            if (!ZYPCommonFunc.hasValue(param.dsAcctNum_I1)) {
                msgMap.addXxMsgId(NMZM0350E);
                returnValue = false;
            }
            // Ship To Cust Code
            if (!ZYPCommonFunc.hasValue(param.shipToCustCd)) {
                msgMap.addXxMsgId(NMZM0365E);
                returnValue = false;
            }
        }
        // Add End 2018/02/07 QC#20297(Sol#379)

        return returnValue;
    }
    /**
     * Mandatory check Bll to Cust Code or Ship to Cust Code or Account Number.
     * @param msgMap S21ApiMessageMap
     * @return returnValue
     */
    private boolean chkAcctBillShip(S21ApiMessageMap msgMap) {

        NMZC610001PMsg param = (NMZC610001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.dsAcctNum_I1)
                && !ZYPCommonFunc.hasValue(param.billToCustCd)
                && !ZYPCommonFunc.hasValue(param.shipToCustCd)) {
            msgMap.addXxMsgIdWithPrm(NMZM0063E, new String[] {"Account Number", "Bill to Cust Code", "Ship to Cust Code"});
            return false;
        }
        return true;
    }
    /**
     * Mandatory check Sales Date.
     * @param msgMap S21ApiMessageMap
     * @param slsDt String
     * @return returnValue
     */
    private boolean chkSlsDt(S21ApiMessageMap msgMap, String slsDt) {

        if (!ZYPCommonFunc.hasValue(slsDt)) {
            msgMap.addXxMsgId(NMZM0011E);
            return false;
        }
        return true;
    }
    /**
     * Mandatory check Account Number.
     * @param msgMap S21ApiMessageMap
     * @param dsAcctNum String
     * @return returnValue
     */
    private boolean chkAcctNum(S21ApiMessageMap msgMap, String dsAcctNum) {

        if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
            msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Account Number"});
            return false;
        }
        return true;
    }
    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {

        // If the Bill To or Ship To being input, to get the Account Number and Location Number.
        getLocNum(msgMap);
        // Get customer information
        if (!getCustInfo(msgMap)) {
            return;
        }

    }
    /**
     * Get customer information
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    private boolean getCustInfo(S21ApiMessageMap msgMap) {

        NMZC610001PMsg param = (NMZC610001PMsg) msgMap.getPmsg();

        // mode 01
        if (PROCESS_MODE_TRANSACTION.equals(param.xxModeCd.getValue())) {
            getCustInfoForTransaction(msgMap, param);
        }
        // mode 02
        if (PROCESS_MODE_INVOICE.equals(param.xxModeCd.getValue())) {
            getCustInfoForInvoice(msgMap, param);
        }
        // mode 03
        if (PROCESS_MODE_INSTRUCTION.equals(param.xxModeCd.getValue())) {
            getCustInfoForInstruction(msgMap, param);
        }
        // mode 04
        if (PROCESS_DEFAULT_BILL_SHIP.equals(param.xxModeCd.getValue())) {
            getCustInfoForBillShip(msgMap, param);
        }
        // mode 05
        if (PROCESS_MODE_SPECIAL_HANDLING.equals(param.xxModeCd.getValue())) {
            getCustInfoForSpecialHandling(msgMap, param);
        }
        // mode 06
        if (PROCESS_MODE_ELIGIBLE_CHECK.equals(param.xxModeCd.getValue())) {
            getCustInfoForEligibleCheck(msgMap, param);
        }
        // mode 07
        if (PROCESS_MODE_RELATED_BILL_SHIP.equals(param.xxModeCd.getValue())) {
            getCustInfoForRelatedBillShip(msgMap, param);
        }
        // mode 08
        if (PROCESS_MODE_INSTRUCTION_FOR_INV_PRINT.equals(param.xxModeCd.getValue())) {
            getCustInfoForInstruction(msgMap, param);
        }
        // Add Start 2018/02/07 QC#20297(Sol#379)
        // mode 09
        if (PROCESS_MODE_SHIPPING_DEFAULT_INFORMATION.equals(param.xxModeCd.getValue())) {
            if (!getShippingDefaultInformation(msgMap, param)) {
                return false;
            }
        }
        // Add End 2018/02/07 QC#20297(Sol#379)

        return true;
    }
    /**
     * Get customer information for Transaction (Mode : 01)
     * @param msgMap S21ApiMessageMap
     * @param param NMZC610001PMsg
     */
    private void getCustInfoForTransaction(S21ApiMessageMap msgMap, NMZC610001PMsg param) {
        String searchLvl = "";
        List<CustInfoBean> transactionRuleList = null;
        // Get transaction information from Location Number.
        searchLvl = SEARCH_LVL_LOC;
        if (ZYPCommonFunc.hasValue(param.shipToCustCd)
                || ZYPCommonFunc.hasValue(param.billToCustCd)) {
            transactionRuleList = getTransactionRule(
                                    param.dsTrxRuleTpCd.getValue(), param.glblCmpyCd.getValue(), searchLvl);

        }
        // Get transaction information from Account Number.
        searchLvl = SEARCH_LVL_ACCT;
        if (transactionRuleList == null || transactionRuleList.size() <= 0) {
            transactionRuleList = getTransactionRule(
                    param.dsTrxRuleTpCd.getValue(), param.glblCmpyCd.getValue(), searchLvl);
        }
        // Get transaction information from Relation Account Number.
        if (transactionRuleList == null || transactionRuleList.size() <= 0) {
            transactionRuleList = getRelnTransactionRule(
                    param.dsTrxRuleTpCd.getValue(), param.glblCmpyCd.getValue());
        }
        if (transactionRuleList != null && transactionRuleList.size() > 0) {
            setOutputParamAcctLoc(param, searchLvl, transactionRuleList.get(0).getDsAcctNum(), transactionRuleList.get(0).getLocNum());

            int idx = 0;
            for (CustInfoBean transactionRuleParam : transactionRuleList) {

                if (param.TransactionRuleList.length() - 1 >= idx) {
                    NMZC610001_TransactionRuleListPMsg outPut = param.TransactionRuleList.no(idx);
                    ZYPEZDItemValueSetter.setValue(outPut.dsPoReqFlg, transactionRuleParam.getDsPoReqFlg());
                    // 2022/11/25 QC#60398 Add Start
                    ZYPEZDItemValueSetter.setValue(outPut.hardCopyReqFlg, transactionRuleParam.getHardCopyReqFlg());
                    // 2022/11/25 QC#60398 Add End
                    // 2019/01/30 QC#30308 Mod Start
                    //ZYPEZDItemValueSetter.setValue(outPut.dsBlktPoNum, transactionRuleParam.getDsBlktPoNum());
                    if (!ZYPCommonFunc.hasValue(param.slsDt)) {
                        ZYPEZDItemValueSetter.setValue(outPut.dsBlktPoNum, transactionRuleParam.getDsBlktPoNum());
                    } else {
                        if (!ZYPCommonFunc.hasValue(transactionRuleParam.getDsPoExprDt()) || ZYPDateUtil.compare(param.slsDt.getValue(), transactionRuleParam.getDsPoExprDt()) <= 0) {
                            ZYPEZDItemValueSetter.setValue(outPut.dsBlktPoNum, transactionRuleParam.getDsBlktPoNum());
                        }
                    }
                    // 2019/01/30 QC#30308 Mod End
                    ZYPEZDItemValueSetter.setValue(outPut.dsPoExprDt, transactionRuleParam.getDsPoExprDt());
                    ZYPEZDItemValueSetter.setValue(outPut.custEffLvlCd, transactionRuleParam.getCustEffLvlCd());
                    ZYPEZDItemValueSetter.setValue(outPut.dsCrCardReqFlg, transactionRuleParam.getDsCrCardReqFlg());
                    ZYPEZDItemValueSetter.setValue(outPut.dsOvngtAllwFlg, transactionRuleParam.getDsOvngtAllwFlg());
                    ZYPEZDItemValueSetter.setValue(outPut.dsCustTrxRulePk, transactionRuleParam.getDsCustTrxRulePk());

                    idx++;
                }
            }
            param.TransactionRuleList.setValidCount(idx);
        }

    }
    /**
     * Get customer information for Invoice (Mode : 02)
     * @param msgMap S21ApiMessageMap
     * @param param NMZC610001PMsg
     */
    private void getCustInfoForInvoice(S21ApiMessageMap msgMap, NMZC610001PMsg param) {
        String searchLvl = "";
        // Add Start 2016/01/06 QC#16602
        String searchLvlInv = "";
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String custInvSrcCd = param.custInvSrcCd.getValue();
        List<CustInfoBean> custRefList = null;
        // Add End 2016/01/06 QC#16602
        List<CustInfoBean> invoiceRuleList = null;

        // Get transaction information from Location Number.
        searchLvl = SEARCH_LVL_LOC;
        searchLvlInv = SEARCH_LVL_LOC;
        if (ZYPCommonFunc.hasValue(param.shipToCustCd)
                || ZYPCommonFunc.hasValue(param.billToCustCd)) {
            // Invoice Rule
            // Mod Start 2016/01/06 QC#16602
            invoiceRuleList = getInvoiceRule(custInvSrcCd, glblCmpyCd, searchLvlInv);
            // Mod End 2016/01/06 QC#16602
            // Add Start 2016/01/06 QC#16602
            // Customer Reference
            custRefList = getCustRef(glblCmpyCd, searchLvl);
            // Add End 2016/01/06 QC#16602

        }
        // Get transaction information from Account Number.
        // Del Start 2017/01/06 QC#16602
//        searchLvl = SEARCH_LVL_ACCT;
        // Del End 2017/01/06 QC#16602
        // Invoice Rule
        if (invoiceRuleList == null || invoiceRuleList.size() <= 0) {
            // Mod Start 2016/01/06 QC#16602
            searchLvlInv = SEARCH_LVL_ACCT;
            invoiceRuleList = getInvoiceRule(custInvSrcCd, glblCmpyCd, searchLvlInv);
            // Mod End 2016/01/06 QC#16602
        }
        // Add Start 20107/01/06 QC#16602
        // Customer Reference
        if (custRefList == null || custRefList.size() <= 0) {
            searchLvl = SEARCH_LVL_ACCT;
            custRefList = getCustRef(glblCmpyCd, searchLvl);
        }
        // Get transaction information from Relation Account Number.
        // Invoice Rule
        if (invoiceRuleList == null || invoiceRuleList.size() <= 0) {
            // Mod Start 2016/01/06 QC#16602
            invoiceRuleList = getRelnInvoiceRule(custInvSrcCd, glblCmpyCd);
            // Mod End 2016/01/06 QC#16602
        }
        // Customer Reference
        if (custRefList == null || custRefList.size() <= 0) {
            custRefList = getRelnCustRef(glblCmpyCd);
        }
        // Invoice Rule
        if (invoiceRuleList != null && invoiceRuleList.size() > 0) {
            // Del Start 2017/01/06 QC#16602
//            setOutputParamAcctLoc(param, searchLvl, invoiceRuleList.get(0).getDsAcctNum(), invoiceRuleList.get(0).getLocNum());
            // Del End 2017/01/06 QC#16602

            int idx = 0;
            for (CustInfoBean invoiceRuleParam : invoiceRuleList) {
                if (param.InvoiceRuleList.length() - 1 >= idx) {
                    NMZC610001_InvoiceRuleListPMsg outPut = param.InvoiceRuleList.no(idx);
                    ZYPEZDItemValueSetter.setValue(outPut.custBllgTpCd, invoiceRuleParam.getCustBllgTpCd());
                    ZYPEZDItemValueSetter.setValue(outPut.custConslTermCd, invoiceRuleParam.getCustConslTermCd());
                    ZYPEZDItemValueSetter.setValue(outPut.custBllgVcleCd, invoiceRuleParam.getCustBllgVcleCd());
                    // QC#7781
                    // ZYPEZDItemValueSetter.setValue(outPut.itrlRvwPsnCd, invoiceRuleParam.getItrlRvwPsnCd());
                    // ZYPEZDItemValueSetter.setValue(outPut.ctacPsnPk, invoiceRuleParam.getCtacPsnPk());
                    getCustInfoForInvoiceInternalEmailReview(msgMap, param, outPut.NMZC610002PMsg, invoiceRuleParam.getDsCustInvRulePk());
                    getCustInfoForInvoiceExternalEmailContact(msgMap, param, outPut.NMZC610002PMsg, invoiceRuleParam.getDsCustInvRulePk());
                    ZYPEZDItemValueSetter.setValue(outPut.custEmlMsgTxt, invoiceRuleParam.getCustEmlMsgTxt());
                    ZYPEZDItemValueSetter.setValue(outPut.defInvGrpCd, invoiceRuleParam.getDefInvGrpCd());
                    ZYPEZDItemValueSetter.setValue(outPut.invGrpNum, invoiceRuleParam.getInvGrpNum());
                    ZYPEZDItemValueSetter.setValue(outPut.dsCustInvRulePk, invoiceRuleParam.getDsCustInvRulePk());
                    // Del Start 2017/01/06 QC#16602
//                    ZYPEZDItemValueSetter.setValue(outPut.dsAcctRefAttrbNum_RF, invoiceRuleParam.getDsAcctRefAttrbNum());
//                    ZYPEZDItemValueSetter.setValue(outPut.bllgAttrbNm_RF, invoiceRuleParam.getBllgAttrbNm());
//                    ZYPEZDItemValueSetter.setValue(outPut.bllgAttrbValTxt_RF, invoiceRuleParam.getBllgAttrbValTxt());
//                    ZYPEZDItemValueSetter.setValue(outPut.bllgAttrbEnblFlg_RF, invoiceRuleParam.getBllgAttrbEnblFlg());
//                    ZYPEZDItemValueSetter.setValue(outPut.bllgAttrbReqFlg_RF, invoiceRuleParam.getBllgAttrbReqFlg());
//                    ZYPEZDItemValueSetter.setValue(outPut.dsAcctRefAttrbPk_RF, invoiceRuleParam.getDsAcctRefAttrbPk());
                    // Del End 2017/01/06 QC#16602
                    idx++;
                }

            }
            param.InvoiceRuleList.setValidCount(idx);
        }
        // Customer Reference
        if (custRefList != null && custRefList.size() > 0) {
            setOutputParamAcctLoc(param, searchLvl, custRefList.get(0).getDsAcctNum(), custRefList.get(0).getLocNum());

            int idx = 0;
            for (CustInfoBean custRefParam : custRefList) {
                if (param.CustRefList.length() - 1 >= idx) {
                    NMZC610001_CustRefListPMsg outPut = param.CustRefList.no(idx);
                    ZYPEZDItemValueSetter.setValue(outPut.dsAcctRefAttrbNum_RF, custRefParam.getDsAcctRefAttrbNum());
                    ZYPEZDItemValueSetter.setValue(outPut.bllgAttrbNm_RF, custRefParam.getBllgAttrbNm());
                    ZYPEZDItemValueSetter.setValue(outPut.bllgAttrbValTxt_RF, custRefParam.getBllgAttrbValTxt());
                    ZYPEZDItemValueSetter.setValue(outPut.bllgAttrbEnblFlg_RF, custRefParam.getBllgAttrbEnblFlg());
                    ZYPEZDItemValueSetter.setValue(outPut.bllgAttrbReqFlg_RF, custRefParam.getBllgAttrbReqFlg());
                    ZYPEZDItemValueSetter.setValue(outPut.dsAcctRefAttrbPk_RF, custRefParam.getDsAcctRefAttrbPk());
                    idx++;
                }
            }
            param.CustRefList.setValidCount(idx);
        }

    }
    
    // QC#7781
    private void getCustInfoForInvoiceInternalEmailReview(S21ApiMessageMap msgMap, NMZC610001PMsg param, NMZC610002PMsg subOutput, BigDecimal dsCustInvRulePk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("dsCustInvRulePk", dsCustInvRulePk);
        // ssmParam.put("invRuleRcpntTp_Internal", INV_RULE_RCPNT_TP.INTERNAL);
        ssmParam.put("invRuleRcpntTp_Internal", "I");
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvoiceSourceListInternalReview", ssmParam);
        for (int i = 0; i < listMap.size(); i++) {
            Map<String, Object> map = listMap.get(i);
            if (i < subOutput.A.length()) {
                subOutput.A.no(i).itrlRvwPsnCd.setValue((String) map.get("PSN_CD"));
                subOutput.A.setValidCount(i + 1);
            } else {
                break;
            }
        }
    }
    
    private void getCustInfoForInvoiceExternalEmailContact(S21ApiMessageMap msgMap, NMZC610001PMsg param, NMZC610002PMsg subOutput, BigDecimal dsCustInvRulePk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("dsCustInvRulePk", dsCustInvRulePk);
        // ssmParam.put("invRuleRcpntTp_External", INV_RULE_RCPNT_TP.EXTERNAL);
        ssmParam.put("invRuleRcpntTp_External", "E");
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvoiceSourceListExternalContact", ssmParam);
        for (int i = 0; i < listMap.size(); i++) {
            Map<String, Object> map = listMap.get(i);
            if (i < subOutput.B.length()) {
                subOutput.B.no(i).ctacPsnPk.setValue((BigDecimal) map.get("CTAC_PSN_PK"));
                subOutput.B.setValidCount(i + 1);
            } else {
                break;
            }
        }
    }
    
    /**
     * Get customer information for Instruction (Mode : 03)
     * @param msgMap S21ApiMessageMap
     * @param param NMZC610001PMsg
     */
    private void getCustInfoForInstruction(S21ApiMessageMap msgMap, NMZC610001PMsg param) {
        String searchLvl = "";
        List<CustInfoBean> instructionList = null;
        // Get instructionList information from Location Number.
        searchLvl = SEARCH_LVL_LOC;
        if (ZYPCommonFunc.hasValue(param.shipToCustCd)
                || ZYPCommonFunc.hasValue(param.billToCustCd)) {
            instructionList = getInstruction(param, searchLvl);

        }
        // Get instructionList information from Account Number.
        searchLvl = SEARCH_LVL_ACCT;
        if (instructionList == null || instructionList.size() <= 0) {
            instructionList = getInstruction(param, searchLvl);
        }
        // Get instructionList information from Relation Account Number.
        if (instructionList == null || instructionList.size() <= 0) {
            instructionList = getRelnInstruction(param);
        }

        // QC#10534
        instructionList = applyFilterLineBizTpAll(param, instructionList);

        if (instructionList != null && instructionList.size() > 0) {
            setOutputParamAcctLoc(param, searchLvl, instructionList.get(0).getDsAcctNum(), instructionList.get(0).getLocNum());

            int idx = 0;
            for (CustInfoBean instructionParam : instructionList) {
                if (param.InstructionList.length() - 1 >= idx) {
                    NMZC610001_InstructionListPMsg outPut = param.InstructionList.no(idx);
                    ZYPEZDItemValueSetter.setValue(outPut.dsCustMsgTxt, instructionParam.getDsCustMsgTxt());
                    ZYPEZDItemValueSetter.setValue(outPut.dsPrintOnInvFlg, instructionParam.getDsPrintOnInvFlg());
                    ZYPEZDItemValueSetter.setValue(outPut.custEffLvlCd, instructionParam.getSpclCustEffLvlCd());
                    ZYPEZDItemValueSetter.setValue(outPut.effThruDt, instructionParam.getEffThruDt());
                    ZYPEZDItemValueSetter.setValue(outPut.dsCustSpclMsgPk, instructionParam.getDsCustSpclMsgPk());
                    
                    ZYPEZDItemValueSetter.setValue(outPut.lineBizTpCd, instructionParam.getLineBizTpCd());
                    ZYPEZDItemValueSetter.setValue(outPut.dsBizAreaCd, instructionParam.getDsBizAreaCd());
                    ZYPEZDItemValueSetter.setValue(outPut.dsCustMsgTpCd, instructionParam.getDsCustMsgTpCd());

                    idx++;
                }
            }
            param.InstructionList.setValidCount(idx);
        }

    }
    
    // QC#10534
    private List<CustInfoBean> applyFilterLineBizTpAll(NMZC610001PMsg param, List<CustInfoBean> instructionList) {
        if (!LINE_BIZ_TP.ALL.equals(param.lineBizTpCd.getValue())) {
            List<CustInfoBean> list = new ArrayList<CustInfoBean>();
            for (CustInfoBean bean : instructionList) {
                if (!LINE_BIZ_TP.ALL.equals(bean.getLineBizTpCd())) {
                    list.add(bean);
                }
            }
            if (list.size() > 0) {
                return list;
            }
        }
        return instructionList;
    }
    
    /**
     * Get customer information for Bill To and Ship To (Mode : 04)
     * @param msgMap S21ApiMessageMap
     * @param param NMZC610001PMsg
     */
    private void getCustInfoForBillShip(S21ApiMessageMap msgMap, NMZC610001PMsg param) {

        CustInfoBean billShipBean = null;
        // Mod Start QC#8659
//        String billToCustCd = "";
//        String shipToCustCd = "";
        // Mod Start 2019/01/31 QC#30097
        //String billToCustCd = param.billToCustCd.getValue();
        //String shipToCustCd = param.shipToCustCd.getValue();
        String billToCustCd = "";
        String shipToCustCd = "";
        // Mod End 2019/01/31 QC#30097
        // Mod End QC#8659

        // ADD Start QC#8659
        // Get instructionList information from Customer Transaction Rule.(Location level)
        if (ZYPCommonFunc.hasValue(param.shipToCustCd) || ZYPCommonFunc.hasValue(param.billToCustCd)) {
            if (ZYPCommonFunc.hasValue(param.dsTrxRuleTpCd) && ZYPCommonFunc.hasValue(this.locNum)) {
                billShipBean = getBillShipFromLoc(param);
                if (billShipBean != null) {
                    if (!ZYPCommonFunc.hasValue(billToCustCd)) {
                        // Mod Start 2019/01/31 QC#30097
                        //billToCustCd = billShipBean.getBillToCustCd();
                        billToCustCd = getBillToCustCd(param, billShipBean);
                        // Mod End 2019/01/31 QC#30097
                    }
                    if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
                        // Mod Start 2019/01/31 QC#30097
                        //shipToCustCd = billShipBean.getShipToCustCd();
                        shipToCustCd = getShipToCustCd(param, billShipBean);
                        // Mod End 2019/01/31 QC#30097
                    }
                }
            }
        }
        // ADD End QC#8659

        // Get instructionList information from Customer Transaction Rule.(Account level)
        if (!ZYPCommonFunc.hasValue(billToCustCd) || !ZYPCommonFunc.hasValue(shipToCustCd)) {
            if (ZYPCommonFunc.hasValue(param.dsTrxRuleTpCd)) {
                billShipBean = getBillShip(param);
                if (billShipBean != null) {
                    // Mod Start QC#8659
//                  billToCustCd = billShipBean.getBillToCustCd();
//                  shipToCustCd = billShipBean.getShipToCustCd();
                    if (!ZYPCommonFunc.hasValue(billToCustCd)) {
                        // Mod Start 2019/01/31 QC#30097
                        //billToCustCd = billShipBean.getBillToCustCd();
                        billToCustCd = getBillToCustCd(param, billShipBean);
                        // Mod End 2019/01/31 QC#30097
                    }
                    if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
                        // Mod Start 2019/01/31 QC#30097
                        //shipToCustCd = billShipBean.getShipToCustCd();
                        shipToCustCd = getShipToCustCd(param, billShipBean);
                        // Mod End 2019/01/31 QC#30097
                    }
                    // Mod End QC#8659
                }
            }
        }

        // ADD Start QC#8659
        // Get Bill To Customer Code from Ship To  Customer Code.
        if (ZYPCommonFunc.hasValue(param.shipToCustCd)) {
            if (!ZYPCommonFunc.hasValue(billToCustCd)) {
                billToCustCd = getBillToFromShipTo(param);
            }
        }
        // ADD End QC#8659

        // Get Primary Bill To Customer Code.
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            billToCustCd = getPrimBillTo(param);
        }
        // Get Primary Ship To Customer Code.
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            shipToCustCd = getPrimShipTo(param);
        }

        // 2017/08/22 S21_NA#20183 Add Start
        // Get Bill To and Ship To From Primary Location
        if (!ZYPCommonFunc.hasValue(billToCustCd) || !ZYPCommonFunc.hasValue(shipToCustCd)) {
            if (ZYPCommonFunc.hasValue(this.acctNum)) {
                // 2018/08/24 S21_NA#20183 Mod Start
//                Map<String, String> rsltMap = getBillToShipToFromPrimLoc(param);
                Map<String, Object> rsltMap = getBillToShipToFromPrimLoc(param);
                if (rsltMap != null) { // 2017/08/28 S21_NA#20183-2 Add Condition
//                    if (!ZYPCommonFunc.hasValue(billToCustCd) && ZYPCommonFunc.hasValue(rsltMap.get("BILL_TO_CUST_CD"))) {
//                        billToCustCd = rsltMap.get("BILL_TO_CUST_CD");
//                    }
                    if (!ZYPCommonFunc.hasValue(billToCustCd) && ZYPCommonFunc.hasValue((String) rsltMap.get("BILL_TO_CUST_CD"))) {
                        billToCustCd = (String) rsltMap.get("BILL_TO_CUST_CD");
                    }
//                    if (!ZYPCommonFunc.hasValue(shipToCustCd) && ZYPCommonFunc.hasValue(rsltMap.get("SHIP_TO_CUST_CD"))) {
//                        shipToCustCd = rsltMap.get("SHIP_TO_CUST_CD");
//                    }
                    if (!ZYPCommonFunc.hasValue(shipToCustCd) && ZYPCommonFunc.hasValue((String) rsltMap.get("SHIP_TO_CUST_CD"))) {
                        shipToCustCd = (String) rsltMap.get("SHIP_TO_CUST_CD");
                    }
                }
                // 2018/08/24 S21_NA#20183 Mod End
            } // 2017/08/28 S21_NA#20183-2 Add Condition
        }
        // 2017/08/22 S21_NA#20183 Add End

        // Add Start 2019/01/31 QC#30097
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            billToCustCd = param.billToCustCd.getValue();
        }
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            shipToCustCd = param.shipToCustCd.getValue();
        }
        // Add End 2019/01/31 QC#30097

        if (ZYPCommonFunc.hasValue(billToCustCd) || ZYPCommonFunc.hasValue(shipToCustCd)) {
            int idx = 0;
            ZYPEZDItemValueSetter.setValue(param.dsAcctNum_O, acctNum);
            NMZC610001_DefaultBillShipListPMsg outPut = param.DefaultBillShipList.no(idx);
            ZYPEZDItemValueSetter.setValue(outPut.billToCustCd, billToCustCd);
            ZYPEZDItemValueSetter.setValue(outPut.shipToCustCd, shipToCustCd);
            idx++;
            param.DefaultBillShipList.setValidCount(idx);
        }

    }
    /**
     * Get customer information for Special Handling (Mode : 05)
     * @param msgMap S21ApiMessageMap
     * @param param NMZC610001PMsg
     */
    private void getCustInfoForSpecialHandling(S21ApiMessageMap msgMap, NMZC610001PMsg param) {
        List<CustInfoBean> specialHandlingList = null;
        // Get SpecialHandling information from Account Number.
        specialHandlingList = getSpecialHandling(param);
        // Get SpecialHandling information from Relation Account Number.
        if (specialHandlingList == null || specialHandlingList.size() <= 0) {
            specialHandlingList = getRelnSpecialHandling(param);
        }
        if (specialHandlingList != null && specialHandlingList.size() > 0) {
            ZYPEZDItemValueSetter.setValue(param.dsAcctNum_O, specialHandlingList.get(0).getDsAcctNum());
            int idx = 0;
            for (CustInfoBean specialHandlingParam : specialHandlingList) {

                if (param.SpecialHandlingList.length() - 1 >= idx) {
                    NMZC610001_SpecialHandlingListPMsg outPut = param.SpecialHandlingList.no(idx);
                    ZYPEZDItemValueSetter.setValue(outPut.dsSpclHdlgTpValCd, specialHandlingParam.getDsSpclHdlgTpValCd());
                    ZYPEZDItemValueSetter.setValue(outPut.effFromDt, specialHandlingParam.getHdlgEffFromDt());
                    ZYPEZDItemValueSetter.setValue(outPut.effThruDt, specialHandlingParam.getHdlgEffThruDt());
                    ZYPEZDItemValueSetter.setValue(outPut.custEffLvlCd, specialHandlingParam.getHdlgCustEffLvlCd());
                    ZYPEZDItemValueSetter.setValue(outPut.dsCustSpclHdlgPk, specialHandlingParam.getDsCustSpclHdlgPk());

                    idx++;
                }
            }
            param.SpecialHandlingList.setValidCount(idx);
        }

    }
    /**
     * Get customer information for Eligible Check (Mode : 06)
     * @param msgMap S21ApiMessageMap
     * @param param NMZC610001PMsg
     */
    private void getCustInfoForEligibleCheck(S21ApiMessageMap msgMap, NMZC610001PMsg param) {

        CustInfoBean eligibleCheckBean = null;
        int idx = 0;
        NMZC610001_EligibleCheckListPMsg outPut = param.EligibleCheckList.no(idx);

        // Get Account Number from SHIP_TO_CUST
        String dsAcctRelnShipToFlg = "";
        String shipToRelnDsAcctNum = "";
        if (ZYPCommonFunc.hasValue(param.shipToCustCd)) {
            eligibleCheckBean = getEligibleCheckFromShip(param);
            if (eligibleCheckBean != null) {
                dsAcctRelnShipToFlg = ZYPConstant.FLG_ON_Y;
                shipToRelnDsAcctNum = eligibleCheckBean.getShipToRelnDsAcctNum();

            } else {
                eligibleCheckBean = getRelnEligibleCheckFromShip(param);
                if (eligibleCheckBean != null) {
                    dsAcctRelnShipToFlg = eligibleCheckBean.getDsAcctRelnShipToFlg();
                    shipToRelnDsAcctNum = eligibleCheckBean.getShipToRelnDsAcctNum();

                }
            }

        }

        // Get Account Number from BILL_TO_CUST
        String dsAcctRelnBillToFlg = "";
        String billToRelnDsAcctNum = "";
        if (ZYPCommonFunc.hasValue(param.billToCustCd)) {
            eligibleCheckBean = getEligibleCheckFromBill(param);
            if (eligibleCheckBean != null) {
                dsAcctRelnBillToFlg = ZYPConstant.FLG_ON_Y;
                billToRelnDsAcctNum = eligibleCheckBean.getBillToRelnDsAcctNum();

            } else {
                eligibleCheckBean = getRelnEligibleCheckFromBill(param);
                if (eligibleCheckBean != null) {
                    dsAcctRelnBillToFlg = eligibleCheckBean.getDsAcctRelnBillToFlg();
                    billToRelnDsAcctNum = eligibleCheckBean.getBillToRelnDsAcctNum();

                }
            }

        }

        // Get Account Number from DS_ACCT_RELN
        String dsAcctRelnRecipFlg = "";
        String relnDsAcctNum = "";
        if (ZYPCommonFunc.hasValue(param.dsAcctNum_I1)) {
            // 2016/05/19 QC#8372 Add Start
            if (param.dsAcctNum_I1.getValue().equals(param.dsAcctNum_I2.getValue())) {
                dsAcctRelnRecipFlg = ZYPConstant.FLG_ON_Y;
                relnDsAcctNum = param.dsAcctNum_I1.getValue();
            // 2016/05/19 QC#8372 Add End
            } else {
                eligibleCheckBean = getRelnEligibleCheckFromAcct(param);
                if (eligibleCheckBean != null) {
                    dsAcctRelnRecipFlg = ZYPConstant.FLG_ON_Y;
                    relnDsAcctNum = eligibleCheckBean.getRelnDsAcctNum();
                // 2016/05/19 QC#8372 Add Start
                } else {
                    dsAcctRelnRecipFlg = ZYPConstant.FLG_OFF_N;
                }
                // 2016/05/19 QC#8372 Add End
            }

        }
        ZYPEZDItemValueSetter.setValue(outPut.dsAcctRelnShipToFlg_S, dsAcctRelnShipToFlg);
        ZYPEZDItemValueSetter.setValue(outPut.relnDsAcctNum_S, shipToRelnDsAcctNum);
        ZYPEZDItemValueSetter.setValue(outPut.dsAcctRelnBillToFlg_B, dsAcctRelnBillToFlg);
        ZYPEZDItemValueSetter.setValue(outPut.relnDsAcctNum_B, billToRelnDsAcctNum);
        ZYPEZDItemValueSetter.setValue(outPut.dsAcctRelnRecipFlg, dsAcctRelnRecipFlg);
        ZYPEZDItemValueSetter.setValue(outPut.relnDsAcctNum, relnDsAcctNum);
        idx++;
        param.EligibleCheckList.setValidCount(idx);

    }
    /**
     * Get customer information for Related Bill To and Ship To (Mode : 07)
     * @param msgMap S21ApiMessageMap
     * @param param NMZC610001PMsg
     */
    private void getCustInfoForRelatedBillShip(S21ApiMessageMap msgMap, NMZC610001PMsg param) {
        List<CustInfoBean> relatedBillShipList = null;
        BigDecimal constRelatedBillShipCnt = ZYPCodeDataUtil.getNumConstValue(CONST_NMZC6100_RELATED_BILL_SHIP_CNT, param.glblCmpyCd.getValue());

        int relatedBillShipCnt = param.RelatedBillShipList.length();
        if (ZYPCommonFunc.hasValue(constRelatedBillShipCnt) && constRelatedBillShipCnt.intValue() < param.RelatedBillShipList.length()) {
            relatedBillShipCnt = constRelatedBillShipCnt.intValue();
        }

        int idx = 0;
        NMZC610001_RelatedBillShipListPMsg outPut = null;
        // Get Related BIll Ship information from child relation.
        if (ZYPConstant.FLG_ON_Y.equals(param.xxChildRelnFlg.getValue())) {
            relatedBillShipList = getRelatedBillShipFromChildReln(param, relatedBillShipCnt);
            if (relatedBillShipList != null && relatedBillShipList.size() > 0) {
                for (CustInfoBean relatedBillShipParam : relatedBillShipList) {
                    outPut = param.RelatedBillShipList.no(idx);
                    setOutParamForRelatedBillShip(outPut, relatedBillShipParam);
                    idx++;
                }
            }
        }
        relatedBillShipCnt = relatedBillShipCnt - idx;
        if (relatedBillShipCnt > 0) {
            // Get Related BIll Ship information from current.
            relatedBillShipList = getRelatedBillShipFromCurrent(param, relatedBillShipCnt);
            if (relatedBillShipList != null && relatedBillShipList.size() > 0) {
                for (CustInfoBean relatedBillShipParam : relatedBillShipList) {
                    outPut = param.RelatedBillShipList.no(idx);
                    setOutParamForRelatedBillShip(outPut, relatedBillShipParam);
                    idx++;
                }
            }
        }

        relatedBillShipCnt = relatedBillShipCnt - idx;
        if (relatedBillShipCnt > 0) {
            // Get Related BIll Ship information from parent relation.
            relatedBillShipList = getRelatedBillShipFromParentReln(param, relatedBillShipCnt);
            if (relatedBillShipList != null && relatedBillShipList.size() > 0) {
                for (CustInfoBean relatedBillShipParam : relatedBillShipList) {
                    outPut = param.RelatedBillShipList.no(idx);
                    setOutParamForRelatedBillShip(outPut, relatedBillShipParam);
                    idx++;
                }
            }
        }
        param.RelatedBillShipList.setValidCount(idx);

    }
    /**
     * Set output parameter for Related Bill Ship
     * @param msgMap S21ApiMessageMap
     * @return int
     */
    private void setOutParamForRelatedBillShip(NMZC610001_RelatedBillShipListPMsg outPut, CustInfoBean relatedBillShipParam) {
        ZYPEZDItemValueSetter.setValue(outPut.dsAcctRelnTpCd, relatedBillShipParam.getRelatedDsAcctRelnTpCd());
        ZYPEZDItemValueSetter.setValue(outPut.dsAcctNum, relatedBillShipParam.getRelatedDsAcctNum());
        ZYPEZDItemValueSetter.setValue(outPut.relnDsAcctNum, relatedBillShipParam.getRelatedRelnDsAcctNum());
        ZYPEZDItemValueSetter.setValue(outPut.billToCustCd, relatedBillShipParam.getRelatedBillToCustCd());
        ZYPEZDItemValueSetter.setValue(outPut.shipToCustCd, relatedBillShipParam.getRelatedShipToCustCd());
        ZYPEZDItemValueSetter.setValue(outPut.dsAcctRelnBillToFlg, relatedBillShipParam.getRelatedDsAcctRelnBillToFlg());
        ZYPEZDItemValueSetter.setValue(outPut.dsAcctRelnShipToFlg, relatedBillShipParam.getRelatedDsAcctRelnShipToFlg());

    }

    // Add Start 2018/02/07 QC#20297(Sol#379)
    /**
     * Get Shipping Default Information (Mode : 09)
     * @param msgMap S21ApiMessageMap
     * @param param NMZC610001PMsg
     */
    private boolean getShippingDefaultInformation(S21ApiMessageMap msgMap, NMZC610001PMsg param) {
        String lineBizTpCd = null;
        String dsBizAreaCd = null;
        boolean errorFlag = false;

        // Get Line Of Business Code
        lineBizTpCd = getLineOfBusinessTypeCode(param);
        if (!ZYPCommonFunc.hasValue(lineBizTpCd)) {
            msgMap.addXxMsgId(NMZM0366E);
            errorFlag = true;
        }

        // Get Business Area
        dsBizAreaCd = getBusinessAreaCode(param);
        if (!ZYPCommonFunc.hasValue(dsBizAreaCd)) {
            msgMap.addXxMsgId(NMZM0367E);
            errorFlag = true;
        }

        if (errorFlag) {
            return false;
        } else {
            getShippingDefaultInformationResult(param, lineBizTpCd, dsBizAreaCd);
        }

        return true;
    }

    /**
     * Get Shipping Default Information Result
     * @param pMsg NMZC610001PMsg
     * @param lineBizTpCd String
     * @param dsBizAreaCd String
     */
    private void getShippingDefaultInformationResult(NMZC610001PMsg pMsg, String lineBizTpCd, String dsBizAreaCd) {

        // QC#57239
        if (ZYPCommonFunc.hasValue(this.locNum)) {
            List<Map<String, Object>> rsltListLocNum = getShippingDefaultInfoFromLocNum(pMsg, lineBizTpCd, dsBizAreaCd);

            if (rsltListLocNum != null && rsltListLocNum.size() > 0) {
                int rsltSize = 0;
                NMZC610001_ShippingDefaultInfoListPMsg outPut = null;

                for (Map<String, Object> rsltMap : rsltListLocNum) {
                    outPut = pMsg.ShippingDefaultInfoList.no(rsltSize);
                    setOutParamForShippingDefaultInfo(outPut, rsltMap);
                    rsltSize++;
                }

                pMsg.ShippingDefaultInfoList.setValidCount(rsltSize);
                return;
            }
        }

        // QC#57239
        if (ZYPCommonFunc.hasValue(pMsg.dsAcctNum_I1)) {
            List<Map<String, Object>> rsltListDsAcctNum = getShippingDefaultInfoFromDsAcctNum(pMsg, lineBizTpCd, dsBizAreaCd);

            if (rsltListDsAcctNum != null && rsltListDsAcctNum.size() > 0) {
                int rsltSize = 0;
                NMZC610001_ShippingDefaultInfoListPMsg outPut = null;

                for (Map<String, Object> rsltMap : rsltListDsAcctNum) {
                    outPut = pMsg.ShippingDefaultInfoList.no(rsltSize);
                    setOutParamForShippingDefaultInfo(outPut, rsltMap);
                    rsltSize++;
                }

                pMsg.ShippingDefaultInfoList.setValidCount(rsltSize);
                return;
            }
        }

        // return blank
        NMZC610001_ShippingDefaultInfoListPMsg outPut = pMsg.ShippingDefaultInfoList.no(0);
        outPut.frtCondCd.setValue("");
        outPut.shpgSvcLvlCd.setValue("");
        pMsg.ShippingDefaultInfoList.setValidCount(1);

        return;
    }

    /**
     * Set output parameter for Related Bill Ship
     * @param msgMap S21ApiMessageMap
     * @return int
     */
    private void setOutParamForShippingDefaultInfo(NMZC610001_ShippingDefaultInfoListPMsg outPut, Map<String, Object> rsltMap) {
        ZYPEZDItemValueSetter.setValue(outPut.frtCondCd, (String) rsltMap.get("FRT_COND_CD"));
        ZYPEZDItemValueSetter.setValue(outPut.shpgSvcLvlCd, (String) rsltMap.get("SHPG_SVC_LVL_CD"));
    }

    /**
     * Get Line Of Business Type Code
     * @param pMsg NMZC610001PMsg
     * @return String
     */
    private String getLineOfBusinessTypeCode(NMZC610001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        param.put("actvFlg", ZYPConstant.FLG_ON_Y);
        param.put("slsDt", pMsg.slsDt.getValue());

        return (String) ssmBatchClient.queryObject("getLineOfBusinessTypeCode", param);
    }

    /**
     * Get Business Area Code
     * @param pMsg NMZC610001PMsg
     * @return String
     */
    private String getBusinessAreaCode(NMZC610001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("ordCatgCtxTpCdEquipment", ORD_CATG_CTX_TP_CD_EQUIPMENT);
        param.put("ordCatgCtxTpCdSupplies", ORD_CATG_CTX_TP_CD_SUPPLIES);
        param.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
        // Add Start 2019/03/27 QC#30955
        param.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        // Add End 2019/03/27 QC#30955

        return (String) ssmBatchClient.queryObject("getBusinessAreaCode", param);
    }

    /**
     * Get Shipping Default Information From Location Number
     * @param pMsg NMZC610001PMsg
     * @param lineBizTpCd
     * @param dsBizAreaCd
     * @return Map<String, Object>
     */
    private List<Map<String, Object>> getShippingDefaultInfoFromLocNum(NMZC610001PMsg pMsg, String lineBizTpCd, String dsBizAreaCd) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("locNum", this.locNum);
        param.put("lineBizTpCd", lineBizTpCd);
        param.put("dsBizAreaCd", dsBizAreaCd);
        param.put("slsDt", pMsg.slsDt.getValue());
        // 2018/12/10 Add Start QC#29315
        param.put("defFlg", ZYPConstant.FLG_ON_Y);
        // 2018/12/10 Add End QC#29315

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShippingDefaultInfo", param);
    }

    /**
     * Get Shipping Default Information From Ship To Account Number
     * @param pMsg NMZC610001PMsg
     * @param lineBizTpCd
     * @param dsBizAreaCd
     * @return Map<String, Object>
     */
    private List<Map<String, Object>> getShippingDefaultInfoFromDsAcctNum(NMZC610001PMsg pMsg, String lineBizTpCd, String dsBizAreaCd) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsAcctNum", pMsg.dsAcctNum_I1.getValue());
        param.put("lineBizTpCd", lineBizTpCd);
        param.put("dsBizAreaCd", dsBizAreaCd);
        param.put("slsDt", pMsg.slsDt.getValue());
        // 2018/12/10 Add Start QC#29315
        param.put("defFlg", ZYPConstant.FLG_ON_Y);
        // 2018/12/10 Add End QC#29315

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShippingDefaultInfo", param);
    }
    // Add End 2018/02/07 QC#20297(Sol#379)

    // Add Start 2019/01/31 QC#30097
    /**
     * getBillToCustCd
     * @param param NMZC610001PMsg
     * @param billShipBean CustInfoBean
     * @return String
     */
    private String getBillToCustCd(NMZC610001PMsg param, CustInfoBean billShipBean) {
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String billToCustCd = billShipBean.getBillToCustCd();
        String result = null;
        Map<String, String> locNumMap = null;

        if (ZYPCommonFunc.hasValue(param.xxSelFlg) && ZYPConstant.FLG_ON_Y.equals(param.xxSelFlg.getValue())) {
            // Own account and related account.
            result = billToCustCd;
        } else {
            // Own account only.
            if (ZYPCommonFunc.hasValue(billToCustCd)) {
                // Get Account Number and Location Number from Bill To
                locNumMap = getLocNumFromBill(billToCustCd, glblCmpyCd);

                String sellToCustCd = null;
                if (locNumMap != null) {
                    sellToCustCd = locNumMap.get("SELL_TO_CUST_CD");
                }

                if (ZYPCommonFunc.hasValue(this.acctNum) && this.acctNum.equals(sellToCustCd)) {
                    result = billToCustCd;
                } else {
                    result = "";
                }
            } else {
                result = billToCustCd;
            }
        }

        return result;
    }

    /**
     * getShipToCustCd
     * @param param NMZC610001PMsg
     * @param billShipBean CustInfoBean
     * @return String
     */
    private String getShipToCustCd(NMZC610001PMsg param, CustInfoBean billShipBean) {
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String shipToCustCd = billShipBean.getShipToCustCd();
        String result = null;
        Map<String, String> locNumMap = null;

        if (ZYPCommonFunc.hasValue(param.xxSelFlg) && ZYPConstant.FLG_ON_Y.equals(param.xxSelFlg.getValue())) {
            // Own account and related account.
            result = shipToCustCd;
        } else {
            // Own account only.
            if (ZYPCommonFunc.hasValue(shipToCustCd)) {
                // Get Account Number and Location Number from Ship To
                locNumMap = getLocNumFromShip(shipToCustCd, glblCmpyCd);

                String sellToCustCd = null;
                if (locNumMap != null) {
                    sellToCustCd = locNumMap.get("SELL_TO_CUST_CD");
                }

                if (ZYPCommonFunc.hasValue(this.acctNum) && this.acctNum.equals(sellToCustCd)) {
                    result = shipToCustCd;
                } else {
                    result = "";
                }
            } else {
                result = shipToCustCd;
            }
        }

        return result;
    }
    // Add End 2019/01/31 QC#30097

    /**
     * Get Account Number and Location Number
     * @param msgMap S21ApiMessageMap
     * @return String
     */
    private void getLocNum(S21ApiMessageMap msgMap) {

        NMZC610001PMsg param = (NMZC610001PMsg) msgMap.getPmsg();

        String glblCmpyCd = param.glblCmpyCd.getValue();
        Map<String, String> locNumMap = null;
        if (ZYPCommonFunc.hasValue(param.shipToCustCd)) {
            // Get Account Number and Location Number from Ship To
            locNumMap = getLocNumFromShip(param.shipToCustCd.getValue(), glblCmpyCd);
        } else if (ZYPCommonFunc.hasValue(param.billToCustCd)) {
            // Get Account Number and Location Number from Bill To
            locNumMap = getLocNumFromBill(param.billToCustCd.getValue(), glblCmpyCd);
        }
        if (locNumMap != null) {
            this.acctNum = locNumMap.get("SELL_TO_CUST_CD");
            this.locNum = locNumMap.get("LOC_NUM");
        }
        if (ZYPCommonFunc.hasValue(param.dsAcctNum_I1)) {
            this.acctNum = param.dsAcctNum_I1.getValue();
        }

    }
    /**
     * @param shipToCustCd String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    private Map<String, String> getLocNumFromShip(String shipToCustCd, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("shipToCustCd", shipToCustCd);

        return (Map<String, String>) this.ssmBatchClient.queryObject("getLocNumFromShipTo", param);

    }
    /**
     * @param billToCustCd String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    private Map<String, String> getLocNumFromBill(String billToCustCd, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("billToCustCd", billToCustCd);

        return (Map<String, String>) this.ssmBatchClient.queryObject("getLocNumFromBillTo", param);

    }
    /**
     * @param trxRuleTpCd String
     * @param glblCmpyCd String
     * @param searchLvl String
     * @return List<CustInfoBean>
     */
    private List<CustInfoBean> getTransactionRule(String trxRuleTpCd, String glblCmpyCd, String searchLvl) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("searchLvl", searchLvl);
        param.put("acctNum", this.acctNum);
        param.put("locNum", this.locNum);
        param.put("trxRuleTpCd", trxRuleTpCd);

        return (List<CustInfoBean>) ssmBatchClient.queryObjectList("getCustInfoForTransaction", param);

    }
    /**
     * @param param NMZC610001PMsg
     * @param searchLvl String
     * @return List<CustInfoBean>
     */
    private List<CustInfoBean> getInstruction(NMZC610001PMsg pMsg, String searchLvl) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("searchLvl", searchLvl);
        param.put("acctNum", this.acctNum);
        param.put("locNum", this.locNum);
        param.put("lineBizTpCd", pMsg.lineBizTpCd.getValue());
        param.put("lineBizTpCdALL", LINE_BIZ_TP.ALL);
        List<String> dsBizAreaCdList = new ArrayList<String>();
        if (PROCESS_MODE_INSTRUCTION.equals(pMsg.xxModeCd.getValue())) {
            dsBizAreaCdList.add(pMsg.dsBizAreaCd.getValue());
        } else {
            for (int i = 0; i < pMsg.inDsBizAreaCdList.getValidCount(); i++) {
                dsBizAreaCdList.add(pMsg.inDsBizAreaCdList.no(i).dsBizAreaCd.getValue());
            }
        }
        // add start 2023/07/18 QC#61421
        if (!dsBizAreaCdList.contains(DS_BIZ_AREA.ALL)) {
            dsBizAreaCdList.add(DS_BIZ_AREA.ALL);
        }
        // add end 2023/07/18 QC#61421
        param.put("dsBizAreaCdList", dsBizAreaCdList);
        
        List<String> dsCustMsgTpCdList = new ArrayList<String>();
        if (PROCESS_MODE_INSTRUCTION.equals(pMsg.xxModeCd.getValue())) {
        	dsCustMsgTpCdList.add(pMsg.dsCustMsgTpCd.getValue());
        } else {
            for (int i = 0; i < pMsg.inDsCustMsgTpCdList.getValidCount(); i++) {
            	dsCustMsgTpCdList.add(pMsg.inDsCustMsgTpCdList.no(i).dsCustMsgTpCd.getValue());
            }
        }
        param.put("dsCustMsgTpCdList", dsCustMsgTpCdList);
        param.put("slsDt", pMsg.slsDt.getValue());

        List<CustInfoBean> instructionList = null;
        if (LINE_BIZ_TP.ALL.equals(pMsg.lineBizTpCd.getValue())) {
            instructionList = (List<CustInfoBean>) ssmBatchClient.queryObjectList("getCustInfoForInstructionAll", param);
        } else {
            instructionList = (List<CustInfoBean>) ssmBatchClient.queryObjectList("getCustInfoForInstruction", param);
        }

        return instructionList;

    }
    /**
     * @param param NMZC610001PMsg
     * @return CustInfoBean
     */
    private CustInfoBean getBillShip(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", this.acctNum);
        param.put("trxRuleTpCd", pMsg.dsTrxRuleTpCd.getValue());
        // 2019/08/28 QC#53021 Add Start
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // 2019/08/28 QC#53021 Add End

        return (CustInfoBean) ssmBatchClient.queryObject("getCustInfoForBillShip", param);

    }
    // ADD Start QC#8659
    /**
     * @param param NMZC610001PMsg
     * @return CustInfoBean
     */
    private CustInfoBean getBillShipFromLoc(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("locNum", this.locNum);
        param.put("trxRuleTpCd", pMsg.dsTrxRuleTpCd.getValue());
        // 2019/08/28 QC#53021 Add Start
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // 2019/08/28 QC#53021 Add End

        return (CustInfoBean) ssmBatchClient.queryObject("getCustInfoForBillShipFromLoc", param);
    }
    /**
     * @param param NMZC610001PMsg
     * @return String
     */
    private String getBillToFromShipTo(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("shipToCustCd", pMsg.shipToCustCd);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (String) ssmBatchClient.queryObject("getBillToFromShipTo", param);
    }
    // ADD End QC#8659
    /**
     * @param param NMZC610001PMsg
     * @return String
     */
    private String getPrimBillTo(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", this.acctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (String) ssmBatchClient.queryObject("getCustInfoForPrimBill", param);

    }
    /**
     * @param pMsg NMZC610001PMsg
     * @return String
     */
    private String getPrimShipTo(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", this.acctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (String) ssmBatchClient.queryObject("getCustInfoForPrimShip", param);

    }
    /**
     * @param pMsg NMZC610001PMsg
     * @return List<CustInfoBean>
     */
    private List<CustInfoBean> getSpecialHandling(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", this.acctNum);
        param.put("dsSpclHdlgTpCd", pMsg.dsSpclHdlgTpCd.getValue());
        param.put("slsDt", pMsg.slsDt.getValue());
        //-- Add Start QC#9073 2016/05/27 -----
        param.put("maxDate", MAX_DATE_VALUE);
        //-- Add End   QC#9073 2016/05/27 -----

        return (List<CustInfoBean>) ssmBatchClient.queryObjectList("getCustInfoForSpecialHandling", param);

    }
    /**
     * @param pMsg NMZC610001PMsg
     * @return List<CustInfoBean>
     */
    private List<CustInfoBean> getRelnSpecialHandling(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", this.acctNum);
        param.put("dsSpclHdlgTpCd", pMsg.dsSpclHdlgTpCd.getValue());
        param.put("slsDt", pMsg.slsDt.getValue());
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        param.put("custEffLvlCd", CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        //-- Add Start QC#9073 2016/05/27 -----
        param.put("maxDate", MAX_DATE_VALUE);
        //-- Add End   QC#9073 2016/05/27 -----

        return (List<CustInfoBean>) ssmBatchClient.queryObjectList("getRelnCustInfoForSpecialHandling", param);

    }
    /**
     * @param trxRuleTpCd String
     * @param glblCmpyCd String
     * @return List<CustInfoBean>
     */
    private List<CustInfoBean> getRelnTransactionRule(String trxRuleTpCd, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("acctNum", this.acctNum);
        param.put("trxRuleTpCd", trxRuleTpCd);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        param.put("custEffLvlCd", CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<CustInfoBean>) ssmBatchClient.queryObjectList("getRelnCustInfoForTransaction", param);

    }
    /**
     * @param custInvSrcCd String
     * @param glblCmpyCd String
     * @param searchLvl String
     * @return List<CustInfoBean>
     */
    private List<CustInfoBean> getInvoiceRule(String custInvSrcCd, String glblCmpyCd, String searchLvl) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("searchLvl", searchLvl);
        param.put("acctNum", this.acctNum);
        param.put("locNum", this.locNum);
        param.put("custInvSrcCd", custInvSrcCd);
        // Add Start 2017/01/06 QC#16602
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // Add End 2017/01/06 QC#16602

        return (List<CustInfoBean>) ssmBatchClient.queryObjectList("getCustInfoForInvoice", param);

    }
    /**
     * @param custInvSrcCd String
     * @param glblCmpyCd String
     * @return List<CustInfoBean>
     */
    private List<CustInfoBean> getRelnInvoiceRule(String custInvSrcCd, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("acctNum", this.acctNum);
        param.put("custInvSrcCd", custInvSrcCd);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        param.put("custEffLvlCd", CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<CustInfoBean>) ssmBatchClient.queryObjectList("getRelnCustInfoForInvoice", param);

    }
    // Add Start 2017/01/06 QC#16602
    /**
     * @param glblCmpyCd String
     * @param searchLvl String
     * @return List<CustInfoBean>
     */
    private List<CustInfoBean> getCustRef(String glblCmpyCd, String searchLvl) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("searchLvl", searchLvl);
        param.put("acctNum", this.acctNum);
        param.put("locNum", this.locNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<CustInfoBean>) ssmBatchClient.queryObjectList("getCustInfoForCustRef", param);

    }
    /**
     * @param glblCmpyCd String
     * @return List<CustInfoBean>
     */
    private List<CustInfoBean> getRelnCustRef(String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("acctNum", this.acctNum);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        param.put("custEffLvlCd", CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<CustInfoBean>) ssmBatchClient.queryObjectList("getRelnCustInfoForCustRef", param);

    }
    // Add End 2017/01/06 QC#16602
    /**
     * getRelnInstruction
     * @param pMsg NMZC610001PMsg
     * @return List<CustInfoBean>
     */
    private List<CustInfoBean> getRelnInstruction(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", this.acctNum);
        param.put("lineBizTpCd", pMsg.lineBizTpCd.getValue());
        List<String> dsBizAreaCdList = new ArrayList<String>();
        if (PROCESS_MODE_INSTRUCTION.equals(pMsg.xxModeCd.getValue())) {
            dsBizAreaCdList.add(pMsg.dsBizAreaCd.getValue());
        } else {
            for (int i = 0; i < pMsg.inDsBizAreaCdList.getValidCount(); i++) {
                dsBizAreaCdList.add(pMsg.inDsBizAreaCdList.no(i).dsBizAreaCd.getValue());
            }
        }
        // add start 2023/07/18 QC#61421
        if (!dsBizAreaCdList.contains(DS_BIZ_AREA.ALL)) {
            dsBizAreaCdList.add(DS_BIZ_AREA.ALL);
        }
        // add end 2023/07/18 QC#61421
        param.put("dsBizAreaCdList", dsBizAreaCdList);
        List<String> dsCustMsgTpCdList = new ArrayList<String>();
        if (PROCESS_MODE_INSTRUCTION.equals(pMsg.xxModeCd.getValue())) {
            dsCustMsgTpCdList.add(pMsg.dsCustMsgTpCd.getValue());
        } else {
            for (int i = 0; i < pMsg.inDsCustMsgTpCdList.getValidCount(); i++) {
                dsCustMsgTpCdList.add(pMsg.inDsCustMsgTpCdList.no(i).dsCustMsgTpCd.getValue());
            }
        }
        param.put("dsCustMsgTpCdList", dsCustMsgTpCdList);
        param.put("slsDt", pMsg.slsDt.getValue());
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        param.put("custEffLvlCd", CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // QC#10534
        param.put("lineBizTpCdALL", LINE_BIZ_TP.ALL);

        List<CustInfoBean> instructionList = null;
        if (LINE_BIZ_TP.ALL.equals(pMsg.lineBizTpCd.getValue())) {
            instructionList = (List<CustInfoBean>) ssmBatchClient.queryObjectList("getRelnCustInfoForInstructionAll", param);
        } else {
            instructionList = (List<CustInfoBean>) ssmBatchClient.queryObjectList("getRelnCustInfoForInstruction", param);
        }

        return instructionList;
    }
    /**
     * @param param NMZC610001PMsg
     * @return CustInfoBean
     */
    private CustInfoBean getEligibleCheckFromShip(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", pMsg.dsAcctNum_I2.getValue());
        param.put("shipToCustCd", pMsg.shipToCustCd.getValue());
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (CustInfoBean) ssmBatchClient.queryObject("getCustInfoForEligShip", param);

    }
    /**
     * @param param NMZC610001PMsg
     * @return CustInfoBean
     */
    private CustInfoBean getRelnEligibleCheckFromShip(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", pMsg.dsAcctNum_I2.getValue());
        param.put("shipToCustCd", pMsg.shipToCustCd.getValue());
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        // Add Start 2017/11/16 QC#17322(Sol#174)
        param.put("dsAcctRelnTpCd_Exclusion", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);
        // Add End 2017/11/16 QC#17322(Sol#174)

        return (CustInfoBean) ssmBatchClient.queryObject("getRelnCustInfoForEligShip", param);

    }
    /**
     * @param param NMZC610001PMsg
     * @return CustInfoBean
     */
    private CustInfoBean getEligibleCheckFromBill(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", pMsg.dsAcctNum_I2.getValue());
        param.put("billToCustCd", pMsg.billToCustCd.getValue());
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (CustInfoBean) ssmBatchClient.queryObject("getCustInfoForEligBill", param);

    }
    /**
     * @param param NMZC610001PMsg
     * @return CustInfoBean
     */
    private CustInfoBean getRelnEligibleCheckFromBill(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", pMsg.dsAcctNum_I2.getValue());
        param.put("billToCustCd", pMsg.billToCustCd.getValue());
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        // Add Start 2017/11/16 QC#17322(Sol#174)
        param.put("dsAcctRelnTpCd_Exclusion", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);
        // Add End 2017/11/16 QC#17322(Sol#174)

        return (CustInfoBean) ssmBatchClient.queryObject("getRelnCustInfoForEligBill", param);

    }
    /**
     * @param param NMZC610001PMsg
     * @return CustInfoBean
     */
    private CustInfoBean getRelnEligibleCheckFromAcct(NMZC610001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", pMsg.dsAcctNum_I2.getValue());
        param.put("relnAcctNum", this.acctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        // Add Start 2017/11/16 QC#17322(Sol#174)
        param.put("dsAcctRelnTpCd_Exclusion", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);
        // Add End 2017/11/16 QC#17322(Sol#174)

        return (CustInfoBean) ssmBatchClient.queryObject("getRelnCustInfoForEligAcct", param);

    }
    /**
     * @param param NMZC610001PMsg
     * @param rownum int
     * @return CustInfoBean
     */
    private List<CustInfoBean> getRelatedBillShipFromChildReln(NMZC610001PMsg pMsg, int rownum) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", this.acctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        // Add Start 2018/03/20 QC#25000
        param.put("subModeCd", pMsg.xxModeCd_SB.getValue());
        // Add End 2018/03/20 QC#25000
        param.put("rownum", rownum + 1);

        return (List<CustInfoBean>) ssmBatchClient.queryObjectList("getCustInfoForRelatedChildReln", param);

    }
    /**
     * @param param NMZC610001PMsg
     * @param rownum int
     * @return CustInfoBean
     */
    private List<CustInfoBean> getRelatedBillShipFromCurrent(NMZC610001PMsg pMsg, int rownum) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", this.acctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("rownum", rownum + 1);

        return (List<CustInfoBean>) ssmBatchClient.queryObjectList("getCustInfoForRelatedCurrent", param);

    }
    /**
     * @param param NMZC610001PMsg
     * @param rownum int
     * @return CustInfoBean
     */
    private List<CustInfoBean> getRelatedBillShipFromParentReln(NMZC610001PMsg pMsg, int rownum) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", this.acctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        // Add Start 2017/11/16 QC#17322(Sol#174)
        param.put("dsAcctRelnTpCd_Exclusion", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);
        // Add End 2017/11/16 QC#17322(Sol#174)
        // 2018/10/23 S21_NA#28721 Add Start
        if(ZYPCommonFunc.hasValue(pMsg.xxResFltrFlg)){
            ZYPEZDItemValueSetter.setValue(pMsg.xxRelnTrgtFlg, ZYPConstant.FLG_ON_Y);
        }
        param.put("resFltr", pMsg.xxResFltrFlg.getValue());
        // 2018/10/23 S21_NA#28721 Add End
        param.put("relnTrgtFlg", pMsg.xxRelnTrgtFlg.getValue());
        // Add Start 2018/03/20 QC#25000
        param.put("subModeCd", pMsg.xxModeCd_SB.getValue());
        // Add End 2018/03/20 QC#25000
        param.put("rownum", rownum + 1);

        return (List<CustInfoBean>) ssmBatchClient.queryObjectList("getCustInfoForRelatedParentReln", param);

    }
    /**
     * @param param NMZC610001PMsg
     * @param searchLvl String
     */
    private void setOutputParamAcctLoc(NMZC610001PMsg param, String searchLvl, String outAcctNum, String outLocNum) {

        if (SEARCH_LVL_ACCT.equals(searchLvl)) {
            ZYPEZDItemValueSetter.setValue(param.dsAcctNum_O, outAcctNum);
        } else if (SEARCH_LVL_LOC.equals(searchLvl)) {
            ZYPEZDItemValueSetter.setValue(param.locNum, outLocNum);
        }

    }

    // 2017/08/22 S21_NA#20183 Add Start
    /**
     * @param pMsg NMZC610001PMsg
     * @return Map<String, String> KEY: BILL_TO_CUST_CD, SHIP_TO_CUST_CD
     */
    // 2018/08/24 S21_NA#20183 Mod Start
//    private Map<String, String> getBillToShipToFromPrimLoc(NMZC610001PMsg pMsg) {
    private Map<String, Object> getBillToShipToFromPrimLoc(NMZC610001PMsg pMsg) {
    // 2018/08/24 S21_NA#20183 Mod End

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("acctNum", this.acctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        // 2018/08/24 S21_NA#20183 Mod Start
        param.put("rownum", 2);

//        return (Map<String, String>) ssmBatchClient.queryObject("getBillToShipToFromPrimLoc", param);
        List<Map<String, Object>> rsBillShipList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBillToShipToFromPrimLoc", param);

        if (rsBillShipList != null
                && rsBillShipList.size() == 1) {
            return rsBillShipList.get(0);
        } else if (rsBillShipList != null
                && rsBillShipList.size() == 2){
            for (Map<String, Object> rsBillShip : rsBillShipList) {
                if (ZYPCommonFunc.hasValue((BigDecimal) rsBillShip.get("PRIN_CUST_PK"))) {
                    return rsBillShip;
                }
            }
        }

        return null;
        // 2018/08/24 S21_NA#20183 Mod End

    }
    // 2017/08/22 S21_NA#20183 Add End
}
