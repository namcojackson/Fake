/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC106001;

import static com.canon.cusa.s21.api.NSZ.NSZC106001.constant.NSZC10600Constant.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_ACTL_CNT_INTFCTMsg;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_CONTR_INTFC_DEF_RULETMsg;
import business.db.DS_CONTR_INTFC_DEF_RULETMsgArray;
import business.db.DS_SUB_CONTR_INTFCTMsg;
import business.db.DS_XS_COPY_INTFCTMsg;
import business.db.ROSS_INTFC_CONTRTMsg;
import business.db.ROSS_INTFC_CONTR_COMPTMsg;
import business.parts.NSZC106001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACTL_CNT_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_BASE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_USG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_EDI;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRCF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_PLN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_COPY_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * NSZC1060: CUSA Retail Contract Interface API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/17/2016   CITS            M.Okigami       Create          N/A
 * 01/11/2017   CITS            T.Kikuhara      Update          QC#15484
 * 02/20/2017   Hitachi         K.Kitachi       Update          QC#17604
 * 03/06/2017   Hitachi         K.Ochiai        Update          QC#17747
 * 08/18/2017   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/09/01   Hitachi         A.Kohinata      Update          QC#20882
 * 2017/10/18   CITS            M.Naito         Update          QC#20246
 * 2017/12/19   Hitachi         T.Tomita        Update          QC#18362
 * 2017/12/28   Hitachi         U.Kim           Update          QC#22735
 * 2018/01/15   Hitachi         U.Kim           Update          QC#22920, QC#23435
 * 2018/05/22   Fujitsu         H.Nagashima     Update          QC#15410
 *</pre>
 */
public class NSZC106001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap glMsgMap = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient glSsmBatchClient = null;

    /** NSZC106001PMsg */
    private NSZC106001PMsg glWkPmsg = null;

    /** BLLG_CYCLE(Monthly Data) */
    private Map<String, Object> glMonthlyBllgCycle = null;

    /** MTR_LB_NM Map */
    private Map<String, String> glMtrLbNm = null;

    /** Default Meter Count Map */
    private Map<String, BigDecimal> glDefaultMeterCount = null;

    /** INTG_MDSE_CD Map */
    private Map<String, String> glIntgMdseCd = null;

    /** BLLG_MTR_LVL_NUM Map */
    private Map<String, String> glBllgMtrLvlNum = null;

    /** NSZC1060_BLLG_MTR_LB_CD_SM Value */
    private String glBllgMtrLbCdSm = null;

    /** NSZC1060_BLLG_MTR_LB_CD_BW Value */
    private String glBllgMtrLbCdBw = null;

    /** NSZC1060_BLLG_MTR_LB_CD_CLR Value */
    private String glBllgMtrLbCdClr = null;

    /** NSZC1060_MTR_EST_TP_CD Value */
    private String glMtrEstTpCd = null;

    /** NSZC1060_CONTR_UPLFT_TP_CD Value */
    private String glContrUplftTpCd = null;

    /** NSZC1060_PRINT_DTL_FLG Value */
    private String glPrintDtlFlg = null;

    /** CUSA_GLBL_CMPY_CD Value */
    private String glCusaGlblCmpyCd = null;

    /**
     * Constructor
     */
    public NSZC106001() {
        super();
    }

    /**
     * CUSA Retail Contract Interface API
     * @param param NSZC106001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NSZC106001PMsg param, final ONBATCH_TYPE onBatchType) {

        this.glWkPmsg = param;
        this.glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.glMsgMap = new S21ApiMessageMap(param);

        // check Parameter
        if (!checkInput()) {
            this.glMsgMap.flush();
            return;
        }

        if (!init()) {
            this.glMsgMap.flush();
            return;
        }

        if (!checkContractInterface()) {
            this.glMsgMap.flush();
            return;
        }

        if (!createDSContractInterfaceBatch1()) {
            this.glMsgMap.flush();
            return;
        }

        if (!createDSContractInterfaceBatch2()) {
            this.glMsgMap.flush();
            return;
        }

        if (!createCompensesion()) {
            this.glMsgMap.flush();
            return;
        }

        if (!updateProcessedContractInterface()) {
            this.glMsgMap.flush();
            return;
        }
    }

    private boolean checkInput() {

        if (!ZYPCommonFunc.hasValue(glWkPmsg.glblCmpyCd)) {
            this.glMsgMap.addXxMsgId(NSZM0001E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(glWkPmsg.slsDt)) {
            this.glMsgMap.addXxMsgId(NSZM0002E);
            return false;
        }

        if (glWkPmsg.prntBllgMstrInfo.getValidCount() == 0) {
            this.glMsgMap.addXxMsgId(NSZM1025E);
            return false;
        }

        return true;
    }

    private boolean init() {

        this.glBllgMtrLbCdSm = ZYPCodeDataUtil.getVarCharConstValue(NSZC1060_BLLG_MTR_LB_CD_SM, this.glWkPmsg.glblCmpyCd.getValue());
        this.glBllgMtrLbCdBw = ZYPCodeDataUtil.getVarCharConstValue(NSZC1060_BLLG_MTR_LB_CD_BW, this.glWkPmsg.glblCmpyCd.getValue());
        this.glBllgMtrLbCdClr = ZYPCodeDataUtil.getVarCharConstValue(NSZC1060_BLLG_MTR_LB_CD_CLR, this.glWkPmsg.glblCmpyCd.getValue());
        this.glMtrEstTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSZC1060_MTR_EST_TP_CD, this.glWkPmsg.glblCmpyCd.getValue());
        this.glContrUplftTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSZC1060_CONTR_UPLFT_TP_CD, this.glWkPmsg.glblCmpyCd.getValue());
        this.glPrintDtlFlg = ZYPCodeDataUtil.getVarCharConstValue(NSZC1060_PRINT_DTL_FLG, this.glWkPmsg.glblCmpyCd.getValue());
        this.glCusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(CUSA_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());

        this.glMonthlyBllgCycle = getBllgCycle(BLLG_CYCLE.MONTHLY);
        getMtrLb();

        return true;
    }

    private boolean checkContractInterface() {

        for (int i = 0; i < this.glWkPmsg.prntBllgMstrInfo.getValidCount(); i++) {
            BigDecimal prntBllgMstrId = this.glWkPmsg.prntBllgMstrInfo.no(i).prntBllgMstrId.getValue();

            if (isExistProcCodeError(prntBllgMstrId)) {
                continue;
            }

            if (!isExistDealer(prntBllgMstrId)) {
                if (!updateProcCode(prntBllgMstrId, ROSS_INTFC_CONTR_PROC_ERROR, ROSS_INTFC_CONTR_PROC_TXT_DEALER_NOT_EXIST)) {
                    return false;
                }
                continue;
            }

            if (isEndOfLife(prntBllgMstrId)) {
                if (!updateProcCode(prntBllgMstrId, ROSS_INTFC_CONTR_PROC_ERROR, ROSS_INTFC_CONTR_PROC_TXT_END_OF_LIFE)) {
                    return false;
                }
                continue;
            }

            // start 2017/11/11 [QC#22182,DEL]
            //if (isEntryToday(prntBllgMstrId)) {
            //    if (!updateProcCode(prntBllgMstrId, ROSS_INTFC_CONTR_PROC_ERROR, ROSS_INTFC_CONTR_PROC_TXT_24_HOUR_WINDOW)) {
            //        return false;
            //    }
            //    continue;
            //}
            // end 2017/11/11 [QC#22182,DEL]
        }

        return true;
    }

    private boolean createDSContractInterfaceBatch1() {

        BigDecimal nextBatchSqNum = getNextBatchSqNum();
        String batchNum = createBatchNum(nextBatchSqNum);
        Map<String, Object> defaultingRule = getDefaultingRule();

        for (int i = 0; i < this.glWkPmsg.prntBllgMstrInfo.getValidCount(); i++) {
            BigDecimal prntBllgMstrId = this.glWkPmsg.prntBllgMstrInfo.no(i).prntBllgMstrId.getValue();

            if (isExistProcCodeError(prntBllgMstrId)) {
                continue;
            }

            Map<String, Object> baseContrIntfc = getBaseContractInterface(prntBllgMstrId);
            if (baseContrIntfc == null) {
                if (!updateProcCode(prntBllgMstrId, ROSS_INTFC_CONTR_PROC_ERROR, ROSS_INTFC_CONTR_PROC_TXT_SERIAL_NOT_EXIST)) {
                    return false;
                }
                continue;
            }

            if (!ROSS_INTFC_PROC_MODE_TERMINATE_CREATE.equals(getDBColumnValue(baseContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                continue;
            }

            Map<String, Object> dsContr = getDSContract(prntBllgMstrId);
            Map<String, Object> fedexDealer = getFedexDealer(prntBllgMstrId);
            Map<String, Object> customer = getCustomer(prntBllgMstrId);
            Map<String, Object> branchRep = getBranchRep(prntBllgMstrId);
            Map<String, Object> serviceProgram = getServiceProgram(prntBllgMstrId);

            String actionCode = getActionCode(prntBllgMstrId, ROSS_INTFC_PROC_MODE_TERMINATE);

            // Create Base DS Contract Interface
            if (!createBaseDCContractInterface(prntBllgMstrId, batchNum, nextBatchSqNum, defaultingRule, dsContr, baseContrIntfc,
                    customer, branchRep, serviceProgram, actionCode)) {
                return false;
            }

            List<Map<String, Object>> usageContrIntfcList = getUsageContractInterface(prntBllgMstrId);

            // Create Usage DS Contract Interface
            if (fedexDealer != null) {
                if (!createUsageDCContractInterface(prntBllgMstrId, batchNum, nextBatchSqNum, defaultingRule, dsContr, usageContrIntfcList,
                        customer, branchRep, serviceProgram, actionCode)) {
                    return false;
                }
            }

            // Create Accessory DS Contract Interface
            if (!createAccessoryDCContractInterface(prntBllgMstrId, batchNum, nextBatchSqNum, defaultingRule, dsContr, actionCode)) {
                return false;
            }

            // Create DS Actual Counter Interface
            if (fedexDealer != null) {
                if (!createDCActualCounterInterface(prntBllgMstrId, batchNum, usageContrIntfcList, actionCode)) {
                    return false;
                }
            }

            // Create DS Excess Copy Interface
            if (fedexDealer != null) {
                if (!createDSExcessCopyInterface(prntBllgMstrId, batchNum, usageContrIntfcList, actionCode)) {
                    return false;
                }
            }

            // Create Sub Contract
            // START 2017/03/06 K.Ochiai [QC#17747, MOD]
//            if (!createSubContract(prntBllgMstrId, batchNum, dsContr, baseContrIntfc, usageContrIntfcList, actionCode)) {
            if (!createSubContract(prntBllgMstrId, batchNum, dsContr, baseContrIntfc, usageContrIntfcList, actionCode, customer)) {
            // END 2017/03/06 K.Ochiai [QC#17747, MOD]
                return false;
            }
        }

        return true;
    }

    private boolean createDSContractInterfaceBatch2() {

        BigDecimal nextBatchSqNum = getNextBatchSqNum();
        String batchNum = createBatchNum(nextBatchSqNum);
        Map<String, Object> defaultingRule = getDefaultingRule();

        for (int i = 0; i < this.glWkPmsg.prntBllgMstrInfo.getValidCount(); i++) {
            BigDecimal prntBllgMstrId = this.glWkPmsg.prntBllgMstrInfo.no(i).prntBllgMstrId.getValue();

            if (isExistProcCodeError(prntBllgMstrId)) {
                continue;
            }

            Map<String, Object> baseContrIntfc = getBaseContractInterface(prntBllgMstrId);
            if (baseContrIntfc == null) {
                if (!updateProcCode(prntBllgMstrId, ROSS_INTFC_CONTR_PROC_ERROR, ROSS_INTFC_CONTR_PROC_TXT_SERIAL_NOT_EXIST)) {
                    return false;
                }
                continue;
            }

            if (ROSS_INTFC_PROC_MODE_SKIP.equals(getDBColumnValue(baseContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                continue;
            }

            Map<String, Object> dsContr = getDSContract(prntBllgMstrId);
            Map<String, Object> fedexDealer = getFedexDealer(prntBllgMstrId);
            Map<String, Object> customer = getCustomer(prntBllgMstrId);
            Map<String, Object> branchRep = getBranchRep(prntBllgMstrId);
            Map<String, Object> serviceProgram = getServiceProgram(prntBllgMstrId);

            String actionCode = null;
            if (ROSS_INTFC_PROC_MODE_TERMINATE_CREATE.equals(getDBColumnValue(baseContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                actionCode = getActionCode(prntBllgMstrId, ROSS_INTFC_PROC_MODE_CREATE);
            } else {
                actionCode = getActionCode(prntBllgMstrId, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD));
            }

            // Create Base DS Contract Interface
            if (!createBaseDCContractInterface(prntBllgMstrId, batchNum, nextBatchSqNum, defaultingRule, dsContr, baseContrIntfc,
                    customer, branchRep, serviceProgram, actionCode)) {
                return false;
            }

            List<Map<String, Object>> usageContrIntfcList = getUsageContractInterface(prntBllgMstrId);

            // Create Usage DS Contract Interface
            if (fedexDealer != null) {
                if (!createUsageDCContractInterface(prntBllgMstrId, batchNum, nextBatchSqNum, defaultingRule, dsContr, usageContrIntfcList,
                        customer, branchRep, serviceProgram, actionCode)) {
                    return false;
                }
            }

            // Create Accessory DS Contract Interface
            if (!createAccessoryDCContractInterface(prntBllgMstrId, batchNum, nextBatchSqNum, defaultingRule, dsContr, actionCode)) {
                return false;
            }

            // Create DS Actual Counter Interface
            if (fedexDealer != null) {
                if (!createDCActualCounterInterface(prntBllgMstrId, batchNum, usageContrIntfcList, actionCode)) {
                    return false;
                }
            }

            // Create DS Excess Copy Interface
            if (fedexDealer != null) {
                if (!createDSExcessCopyInterface(prntBllgMstrId, batchNum, usageContrIntfcList, actionCode)) {
                    return false;
                }
            }

            // Create Sub Contract
            // START 2017/03/06 K.Ochiai [QC#17747, MOD]
//            if (!createSubContract(prntBllgMstrId, batchNum, dsContr, baseContrIntfc, usageContrIntfcList, actionCode)) {
            if (!createSubContract(prntBllgMstrId, batchNum, dsContr, baseContrIntfc, usageContrIntfcList, actionCode, customer)) {
            // END 2017/03/06 K.Ochiai [QC#17747, MOD]
                return false;
            }
        }

        return true;
    }

    private boolean createCompensesion() {

        for (int i = 0; i < this.glWkPmsg.prntBllgMstrInfo.getValidCount(); i++) {
            BigDecimal prntBllgMstrId = this.glWkPmsg.prntBllgMstrInfo.no(i).prntBllgMstrId.getValue();

            if (isExistProcCodeError(prntBllgMstrId)) {
                continue;
            }

            if (isExistProcModeSkip(prntBllgMstrId)) {
                continue;
            }

            List<Map<String, Object>> unprocessedContrIntfcList = getUnprocessedContractInterface(prntBllgMstrId);

            for (Map<String, Object> unprocessedContrIntfc : unprocessedContrIntfcList) {

                Map<String, Object> preProcessedContrIntfc = getPreProcessedContractInterface((BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_BLLG_MSTR_PK));
                if (preProcessedContrIntfc == null) {

                    ROSS_INTFC_CONTR_COMPTMsg inMsg = new ROSS_INTFC_CONTR_COMPTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inMsg.rossIntfcContrCompPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ROSS_INTFC_CONTR_COMP_SQ));
                    ZYPEZDItemValueSetter.setValue(inMsg.rossIntfcContrPk, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ROSS_INTFC_CONTR_PK));
                    ZYPEZDItemValueSetter.setValue(inMsg.bllgMstrPk, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_BLLG_MSTR_PK));
                    ZYPEZDItemValueSetter.setValue(inMsg.serNum, (String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_SER_NUM));
                    ZYPEZDItemValueSetter.setValue(inMsg.svcDlrCd, (String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_SVC_DLR_CD));
                    ZYPEZDItemValueSetter.setValue(inMsg.fndgDlrCd, (String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_FNDG_DLR_CD));
                    ZYPEZDItemValueSetter.setValue(inMsg.origDlrCd, (String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ORIG_DLR_CD));
                    ZYPEZDItemValueSetter.setValue(inMsg.adminDlrCd, (String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ADMIN_DLR_CD));
                    ZYPEZDItemValueSetter.setValue(inMsg.istlCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ISTL_COMP_AMT));
                    ZYPEZDItemValueSetter.setValue(inMsg.svcDlrCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_SVC_DLR_COMP_AMT));
                    ZYPEZDItemValueSetter.setValue(inMsg.extWtyCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_EXT_WTY_COMP_AMT));
                    ZYPEZDItemValueSetter.setValue(inMsg.fndgDlrCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_FNDG_DLR_COMP_AMT));
                    ZYPEZDItemValueSetter.setValue(inMsg.origDlrCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ORIG_DLR_COMP_AMT));
                    ZYPEZDItemValueSetter.setValue(inMsg.mlyAdminCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_MLY_ADMIN_COMP_AMT));
                    ZYPEZDItemValueSetter.setValue(inMsg.rnwCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_RNW_COMP_AMT));
                    ZYPEZDItemValueSetter.setValue(inMsg.compCratDt, this.glWkPmsg.slsDt);
                    ZYPEZDItemValueSetter.setValue(inMsg.compLastUpdDt, this.glWkPmsg.slsDt);

                    S21ApiTBLAccessor.insert(inMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                        this.glMsgMap.addXxMsgId(NSZM0978E);
                        return false;
                    }

                } else {

                    if (!equalValue((String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_SVC_DLR_CD), (String) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_SVC_DLR_CD))
                            || !equalValue((String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_FNDG_DLR_CD), (String) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_FNDG_DLR_CD))
                            || !equalValue((String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ORIG_DLR_CD), (String) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_ORIG_DLR_CD))
                            || !equalValue((String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ADMIN_DLR_CD), (String) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_ADMIN_DLR_CD))
                            || !equalValue((BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ISTL_COMP_AMT), (BigDecimal) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_ISTL_COMP_AMT))
                            || !equalValue((BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_SVC_DLR_COMP_AMT), (BigDecimal) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_SVC_DLR_COMP_AMT))
                            || !equalValue((BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_EXT_WTY_COMP_AMT), (BigDecimal) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_EXT_WTY_COMP_AMT))
                            || !equalValue((BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_FNDG_DLR_COMP_AMT), (BigDecimal) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_FNDG_DLR_COMP_AMT))
                            || !equalValue((BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ORIG_DLR_COMP_AMT), (BigDecimal) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_ORIG_DLR_COMP_AMT))
                            || !equalValue((BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_MLY_ADMIN_COMP_AMT), (BigDecimal) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_MLY_ADMIN_COMP_AMT))
                            || !equalValue((BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_RNW_COMP_AMT), (BigDecimal) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_RNW_COMP_AMT))) {

                        ROSS_INTFC_CONTR_COMPTMsg inMsg = new ROSS_INTFC_CONTR_COMPTMsg();
                        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(inMsg.rossIntfcContrCompPk, (BigDecimal) getDBColumnValue(preProcessedContrIntfc, DB_COLUMN_ROSS_INTFC_CONTR_COMP_PK));

                        ROSS_INTFC_CONTR_COMPTMsg outMsg = (ROSS_INTFC_CONTR_COMPTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
                        if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                            this.glMsgMap.addXxMsgId(NSZM0979E);
                            return false;
                        }

                        ZYPEZDItemValueSetter.setValue(outMsg.rossIntfcContrPk, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ROSS_INTFC_CONTR_PK));
                        ZYPEZDItemValueSetter.setValue(outMsg.bllgMstrPk, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_BLLG_MSTR_PK));
                        ZYPEZDItemValueSetter.setValue(outMsg.serNum, (String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_SER_NUM));
                        ZYPEZDItemValueSetter.setValue(outMsg.svcDlrCd, (String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_SVC_DLR_CD));
                        ZYPEZDItemValueSetter.setValue(outMsg.fndgDlrCd, (String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_FNDG_DLR_CD));
                        ZYPEZDItemValueSetter.setValue(outMsg.origDlrCd, (String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ORIG_DLR_CD));
                        ZYPEZDItemValueSetter.setValue(outMsg.adminDlrCd, (String) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ADMIN_DLR_CD));
                        ZYPEZDItemValueSetter.setValue(outMsg.istlCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ISTL_COMP_AMT));
                        ZYPEZDItemValueSetter.setValue(outMsg.svcDlrCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_SVC_DLR_COMP_AMT));
                        ZYPEZDItemValueSetter.setValue(outMsg.extWtyCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_EXT_WTY_COMP_AMT));
                        ZYPEZDItemValueSetter.setValue(outMsg.fndgDlrCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_FNDG_DLR_COMP_AMT));
                        ZYPEZDItemValueSetter.setValue(outMsg.origDlrCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ORIG_DLR_COMP_AMT));
                        ZYPEZDItemValueSetter.setValue(outMsg.mlyAdminCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_MLY_ADMIN_COMP_AMT));
                        ZYPEZDItemValueSetter.setValue(outMsg.rnwCompAmt, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_RNW_COMP_AMT));
                        ZYPEZDItemValueSetter.setValue(outMsg.compLastUpdDt, this.glWkPmsg.slsDt);
                        S21ApiTBLAccessor.update(outMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                            this.glMsgMap.addXxMsgId(NSZM0979E);
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean updateProcessedContractInterface() {

        for (int i = 0; i < this.glWkPmsg.prntBllgMstrInfo.getValidCount(); i++) {
            BigDecimal prntBllgMstrId = this.glWkPmsg.prntBllgMstrInfo.no(i).prntBllgMstrId.getValue();

            if (isExistProcCodeError(prntBllgMstrId)) {
                continue;
            }

            if (isExistProcModeSkip(prntBllgMstrId)) {
                continue;
            }

            List<Map<String, Object>> unprocessedContrIntfcList = getUnprocessedContractInterface(prntBllgMstrId);

            for (Map<String, Object> unprocessedContrIntfc : unprocessedContrIntfcList) {

                ROSS_INTFC_CONTRTMsg inMsg = new ROSS_INTFC_CONTRTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inMsg.rossIntfcContrPk, (BigDecimal) getDBColumnValue(unprocessedContrIntfc, DB_COLUMN_ROSS_INTFC_CONTR_PK));

                ROSS_INTFC_CONTRTMsg outMsg = (ROSS_INTFC_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
                if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                    this.glMsgMap.addXxMsgId(NSZM0971E);
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(outMsg.rossIntfcContrProcCd, ROSS_INTFC_CONTR_PROC_INTERFACED);
                S21ApiTBLAccessor.update(outMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                    this.glMsgMap.addXxMsgId(NSZM0971E);
                    return false;
                }
            }
        }

        return true;
    }

    private boolean createBaseDCContractInterface(BigDecimal prntBllgMstrId,
                                                    String batchNum,
                                                    BigDecimal batchSq,
                                                    Map<String, Object> defaultingRule,
                                                    Map<String, Object> dsContr,
                                                    Map<String, Object> baseContrIntfc,
                                                    Map<String, Object> customer,
                                                    Map<String, Object> branchRep,
                                                    Map<String, Object> serviceProgram,
                                                    String actionCode) {

        Map<String, Object> meterReadMethod = getMeterReadMethod(prntBllgMstrId);

        // add start 2017/08/18 QC#18799
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule((String) getDBColumnValue(baseContrIntfc, DB_COLUMN_RTL_DIV_CD));
        // add end 2017/08/18 QC#18799

        DS_CONTR_INTFCTMsg inMsg = new DS_CONTR_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_INTFC_SQ));
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcBatNum, batchNum);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrSrcRefNum, ((BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_BLLG_MSTR_PK)).toPlainString());
        ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.CUSA);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcActCd, actionCode);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.IN_PROCESS);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
        // mod start 2017/11/11 QC#22175
        //if (DS_CONTR_INTFC_ACT.UPDATE.equals(actionCode) || DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(actionCode)) {
        if (!DS_CONTR_INTFC_ACT.CREATE.equals(actionCode)) {
        // mod end 2017/11/11 QC#22175    
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrNum, (String) getDBColumnValue(dsContr, DB_COLUMN_DS_CONTR_NUM));
        }
        ZYPEZDItemValueSetter.setValue(inMsg.serNum, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_SER_NUM));
        ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, (BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_SVC_MACH_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcDtlTpCd, CONTR_INTFC_DTL_TP.BASE);
        ZYPEZDItemValueSetter.setValue(inMsg.dsAcctNum, (String) getDBColumnValue(customer, DB_COLUMN_SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(inMsg.billToCustCd, (String) getDBColumnValue(customer, DB_COLUMN_BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(inMsg.svcContrBrCd, (String) getDBColumnValue(branchRep, DB_COLUMN_SVC_CONTR_BR_CD));
        ZYPEZDItemValueSetter.setValue(inMsg.tocCd, (String) getDBColumnValue(branchRep, DB_COLUMN_TOC_CD));
        ZYPEZDItemValueSetter.setValue(inMsg.mtrEstTpCd, (String) getDBColumnValue(defaultingRule, DB_COLUMN_MTR_EST_TP_CD));
        if (!ZYPCommonFunc.hasValue(inMsg.mtrEstTpCd)) {
            ZYPEZDItemValueSetter.setValue(inMsg.mtrEstTpCd, this.glMtrEstTpCd);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.svcPgmMdseCd, (String) getDBColumnValue(serviceProgram, DB_COLUMN_SVC_PGM_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(inMsg.mdlId, (BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_MDL_ID));
        ZYPEZDItemValueSetter.setValue(inMsg.mdlNm, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_T_MDL_NM));
        if (DS_CONTR_INTFC_ACT.CREATE.equals(actionCode) || DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(actionCode)) {
            if (ROSS_INTFC_PROC_MODE_TERMINATE_CREATE.equals(getDBColumnValue(baseContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                if (ZYPDateUtil.compare((String) getDBColumnValue(baseContrIntfc, DB_COLUMN_CONTR_START_DT),  //
                                         this.glWkPmsg.slsDt.getValue()) > 0) {                               //
                    ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_CONTR_START_DT));
                } else {
                    ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, this.glWkPmsg.slsDt);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_CONTR_START_DT));
            }
        } else {
            ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_CONTR_START_DT));
        }
        ZYPEZDItemValueSetter.setValue(inMsg.contrThruDt, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_CONTR_END_DT));
        // START 2017/03/06 K.Ochiai [QC#17747, MOD]
//        ZYPEZDItemValueSetter.setValue(inMsg.bllgCycleCd, BLLG_CYCLE.MONTHLY);
        String billCycleCd = null;
        billCycleCd = (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_BLLG_CYCLE_CD);
        if (!ZYPCommonFunc.hasValue(billCycleCd)) {
            billCycleCd = (String) getDBColumnValue(customer, DB_COLUMN_DEF_BASE_CYCLE_CD);
        }
        if (!ZYPCommonFunc.hasValue(billCycleCd)) {
            // del start 2017/08/18 QC#18799
//            DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule();
            // del end 2017/08/18 QC#18799
            if (dsContrIntfcDefRuleTMsg != null) {
                billCycleCd = dsContrIntfcDefRuleTMsg.baseBllgCycleCd.getValue();
            }
        }
        ZYPEZDItemValueSetter.setValue(inMsg.bllgCycleCd, billCycleCd);
        // END 2017/03/06 K.Ochiai [QC#17747, MOD]
        ZYPEZDItemValueSetter.setValue(inMsg.prcAllocByMachQtyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.contrAutoRnwTpCd, CONTR_AUTO_RNW_TP.DO_NOT_RENEW);
        ZYPEZDItemValueSetter.setValue(inMsg.befEndRnwDaysAot, (BigDecimal) getDBColumnValue(defaultingRule, DB_COLUMN_BEF_END_RNW_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(inMsg.contrUplftTpCd, (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_UPLFT_TP_CD));
        if (!ZYPCommonFunc.hasValue(inMsg.contrUplftTpCd)) {
            ZYPEZDItemValueSetter.setValue(inMsg.contrUplftTpCd, this.glContrUplftTpCd);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.uplftPrcMethCd, (String) getDBColumnValue(defaultingRule, DB_COLUMN_UPLFT_PRC_METH_CD));
        ZYPEZDItemValueSetter.setValue(inMsg.uplftPrcUpRatio, (BigDecimal) getDBColumnValue(defaultingRule, DB_COLUMN_UPLFT_BASE_PRC_UP_RATIO));
        ZYPEZDItemValueSetter.setValue(inMsg.mtrReadMethCd, (String) getDBColumnValue(meterReadMethod, DB_COLUMN_MTR_READ_METH_CD));
        ZYPEZDItemValueSetter.setValue(inMsg.basePrcDealAmt, (BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_MLY_BASE_COMP_AMT));
        ZYPEZDItemValueSetter.setValue(inMsg.contrCloDay, (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_CLO_DAY));
        // START 2017/12/28 U.Kim [QC#22735,MOD]
        // ZYPEZDItemValueSetter.setValue(inMsg.contrBllgDay, (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_BLLG_DAY));
        String ifBllgDay = (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_BLLG_DAY);
        String bllgDay = null;
        if (ZYPCommonFunc.hasValue(ifBllgDay)) {
            bllgDay = ifBllgDay;
        } else {
            bllgDay = (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_BLLG_DAY);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.contrBllgDay, bllgDay);
        // END 2017/12/28 U.Kim [QC#22735,MOD]
        ZYPEZDItemValueSetter.setValue(inMsg.bllgThruDt, (String) getDBColumnValue(defaultingRule, DB_COLUMN_BLLG_THRU_DT));
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrCatgCd, DS_CONTR_CATG.REGULAR);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrStsCd, DS_CONTR_STS.DRAFT);
        ZYPEZDItemValueSetter.setValue(inMsg.xsChrgTpCd, XS_CHRG_TP.RANGE);
        ZYPEZDItemValueSetter.setValue(inMsg.basePrcTermDealAmtRate,
                                       ((BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_MLY_BASE_COMP_AMT)).multiply(
                                               (BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_PMT_TERM_AOT)).divide(
                                                       (BigDecimal) getDBColumnValue(this.glMonthlyBllgCycle, DB_COLUMN_BLLG_CYCLE_MTH_AOT), 0, BigDecimal.ROUND_DOWN)
                                       );
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrClsCd, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_RTL_DIV_CD));
        ZYPEZDItemValueSetter.setValue(inMsg.ctacPsnPk, (BigDecimal) getDBColumnValue(customer, DB_COLUMN_CTAC_PSN_PK));
        ZYPEZDItemValueSetter.setValue(inMsg.ctacPsnNm,
                                       ZYPCommonFunc.concatString((String) getDBColumnValue(customer, DB_COLUMN_CTAC_PSN_FIRST_NM),
                                                                  " ",
                                                                  (String) getDBColumnValue(customer, DB_COLUMN_CTAC_PSN_LAST_NM)));
        // mod start 2017/08/18 QC#18799
//        ZYPEZDItemValueSetter.setValue(inMsg.invSeptBaseUsgFlg, (String) getDBColumnValue(customer, DB_COLUMN_DS_BILL_TGTR_FLG));
//        if (!ZYPCommonFunc.hasValue(inMsg.invSeptBaseUsgFlg)) {
//            ZYPEZDItemValueSetter.setValue(inMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_OFF_N);
//        }
        ZYPEZDItemValueSetter.setValue(inMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(dsContrIntfcDefRuleTMsg, (String) getDBColumnValue(customer, DB_COLUMN_DS_BILL_TGTR_FLG)));
        // mod end 2017/08/18 QC#18799
        ZYPEZDItemValueSetter.setValue(inMsg.contrDurnAot, (BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_PMT_TERM_AOT));
        ZYPEZDItemValueSetter.setValue(inMsg.pmtTermCashDiscCd, (String) getDBColumnValue(customer, DB_COLUMN_PMT_TERM_CASH_DISC_CD));
        ZYPEZDItemValueSetter.setValue(inMsg.svcLineBizCd, SVC_LINE_BIZ_CD_ESS);
        // START 2017/03/06 K.Ochiai [QC#17747, MOD]
//        ZYPEZDItemValueSetter.setValue(inMsg.bllgTmgTpCd, (String) getDBColumnValue(customer, DB_COLUMN_DEF_BASE_TP_CD));
        String ifBllgTmgTpCd = (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_BLLG_TMG_TP_CD);
        String bllgTmgTpCd = null;
        if (ZYPCommonFunc.hasValue(ifBllgTmgTpCd)) {
            bllgTmgTpCd = getBllgTmgTpCd(ifBllgTmgTpCd);
        }
        if (!ZYPCommonFunc.hasValue(bllgTmgTpCd)) {
            bllgTmgTpCd = DEF_BASE_TP.ADVANCE;
        }
        ZYPEZDItemValueSetter.setValue(inMsg.bllgTmgTpCd, bllgTmgTpCd);
        // END 2017/03/06 K.Ochiai [QC#17747, MOD]
        // START 2017/02/20 K.Kitachi [QC#17604, MOD]
//        ZYPEZDItemValueSetter.setValue(inMsg.dsContrEdiCd, DS_CONTR_EDI.OTHER);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrEdiCd, convertMrcfTpCdToDsContrEdi((String) getDBColumnValue(baseContrIntfc, DB_COLUMN_MRCF_TP_CD)));
        // END 2017/02/20 K.Kitachi [QC#17604, MOD]
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcDt, this.glWkPmsg.slsDt);
        ZYPEZDItemValueSetter.setValue(inMsg.baseChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.usgChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcBatSq, batchSq);
        ZYPEZDItemValueSetter.setValue(inMsg.prcSvcPlnTpCd, PRC_SVC_PLN_TP.FIXED);
        ZYPEZDItemValueSetter.setValue(inMsg.printDtlFlg, (String) getDBColumnValue(defaultingRule, DB_COLUMN_PRINT_DTL_FLG));
        if (!ZYPCommonFunc.hasValue(inMsg.printDtlFlg)) {
            ZYPEZDItemValueSetter.setValue(inMsg.printDtlFlg, this.glPrintDtlFlg);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        // Mod Start 2017/12/18 QC#18362
        ZYPEZDItemValueSetter.setValue(inMsg.contrAdminPsnCd, getContrAdminPsnCd(inMsg, branchRep));
        // Mod End 2017/12/18 QC#18362

        // add start 2017/11/11 QC#22175,22179
        if (DS_CONTR_INTFC_ACT.TERMINATE.equals(actionCode)) {
            if (ROSS_INTFC_PROC_MODE_TERMINATE_CREATE.equals(getDBColumnValue(baseContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                ZYPEZDItemValueSetter.setValue(inMsg.contrCloDt, this.glWkPmsg.slsDt);
            } else {
                ZYPEZDItemValueSetter.setValue(inMsg.contrCloDt, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_TRMN_DT));
            }
        }
        // add end 2017/11/11 QC#22175,22179

        S21ApiTBLAccessor.insert(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            this.glMsgMap.addXxMsgId(NSZM0972E);
            return false;
        }

        return true;
    }

    private boolean createUsageDCContractInterface(BigDecimal prntBllgMstrId,
                                                     String batchNum,
                                                     BigDecimal batchSq,
                                                     Map<String, Object> defaultingRule,
                                                     Map<String, Object> dsContr,
                                                     List<Map<String, Object>> usageContrIntfcList,
                                                     Map<String, Object> customer,
                                                     Map<String, Object> branchRep,
                                                     Map<String, Object> serviceProgram,
                                                     String actionCode) {

        for (int i = 0; i < usageContrIntfcList.size(); i++) {

            Map<String, Object> usageContrIntfc = usageContrIntfcList.get(i);

            // add start 2017/08/18 QC#18799
            DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule((String) getDBColumnValue(usageContrIntfc, DB_COLUMN_RTL_DIV_CD));
            // add end 2017/08/18 QC#18799

            DS_CONTR_INTFCTMsg inMsg = new DS_CONTR_INTFCTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_INTFC_SQ));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcBatNum, batchNum);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrSrcRefNum, ((BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_BLLG_MSTR_PK)).toPlainString());
            ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.CUSA);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcActCd, actionCode);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.IN_PROCESS);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
            // mod start 2017/11/11 QC#22175
            //if (DS_CONTR_INTFC_ACT.UPDATE.equals(actionCode) || DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(actionCode)) {
            if (!DS_CONTR_INTFC_ACT.CREATE.equals(actionCode)) {
            // mod end 2017/11/11 QC#22175    
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrNum, (String) getDBColumnValue(dsContr, DB_COLUMN_DS_CONTR_NUM));
            }
            ZYPEZDItemValueSetter.setValue(inMsg.serNum, (String) getDBColumnValue(usageContrIntfc, DB_COLUMN_SER_NUM));
            ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_SVC_MACH_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcDtlTpCd, CONTR_INTFC_DTL_TP.USAGE);
            ZYPEZDItemValueSetter.setValue(inMsg.dsAcctNum, (String) getDBColumnValue(customer, DB_COLUMN_SELL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.billToCustCd, (String) getDBColumnValue(customer, DB_COLUMN_BILL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.svcContrBrCd, (String) getDBColumnValue(branchRep, DB_COLUMN_SVC_CONTR_BR_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.tocCd, (String) getDBColumnValue(branchRep, DB_COLUMN_TOC_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.mtrEstTpCd, (String) getDBColumnValue(defaultingRule, DB_COLUMN_MTR_EST_TP_CD));
            if (!ZYPCommonFunc.hasValue(inMsg.mtrEstTpCd)) {
                ZYPEZDItemValueSetter.setValue(inMsg.mtrEstTpCd, this.glMtrEstTpCd);
            }
            ZYPEZDItemValueSetter.setValue(inMsg.svcPgmMdseCd, (String) getDBColumnValue(serviceProgram, DB_COLUMN_SVC_PGM_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, (String) getDBColumnValue(usageContrIntfc, DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.mdlId, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_MDL_ID));
            ZYPEZDItemValueSetter.setValue(inMsg.mdlNm, (String) getDBColumnValue(usageContrIntfc, DB_COLUMN_T_MDL_NM));
            if (DS_CONTR_INTFC_ACT.CREATE.equals(actionCode) || DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(actionCode)) {
                if (ROSS_INTFC_PROC_MODE_TERMINATE_CREATE.equals(getDBColumnValue(usageContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                    if (ZYPDateUtil.compare((String) getDBColumnValue(usageContrIntfc, DB_COLUMN_CONTR_START_DT),  //
                                             this.glWkPmsg.slsDt.getValue()) > 0) {                                //
                        ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, (String) getDBColumnValue(usageContrIntfc, DB_COLUMN_CONTR_START_DT));
                    } else {
                        ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, this.glWkPmsg.slsDt);
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, (String) getDBColumnValue(usageContrIntfc, DB_COLUMN_CONTR_START_DT));
                }
            } else {
                ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, (String) getDBColumnValue(usageContrIntfc, DB_COLUMN_CONTR_START_DT));
            }
            ZYPEZDItemValueSetter.setValue(inMsg.contrThruDt, (String) getDBColumnValue(usageContrIntfc, DB_COLUMN_CONTR_END_DT));
            // START 2017/03/06 K.Ochiai [QC#17747, MOD]
//            ZYPEZDItemValueSetter.setValue(inMsg.bllgCycleCd, BLLG_CYCLE.MONTHLY);
            String billCycleCd = null;
            billCycleCd = (String) getDBColumnValue(usageContrIntfc, DB_COLUMN_BLLG_CYCLE_CD);
            if (!ZYPCommonFunc.hasValue(billCycleCd)) {
                billCycleCd = (String) getDBColumnValue(customer, DB_COLUMN_DEF_USG_CYCLE_CD);
            }
            if (!ZYPCommonFunc.hasValue(billCycleCd)) {
                // del start 2017/08/18 QC#18799
//                DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule();
                // del end 2017/08/18 QC#18799
                if (dsContrIntfcDefRuleTMsg != null) {
                    billCycleCd = dsContrIntfcDefRuleTMsg.mtrBllgCycleCd.getValue();
                }
            }
            ZYPEZDItemValueSetter.setValue(inMsg.bllgCycleCd, billCycleCd);
            // END 2017/03/06 K.Ochiai [QC#17747, MOD]
            ZYPEZDItemValueSetter.setValue(inMsg.prcAllocByMachQtyFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.contrAutoRnwTpCd, CONTR_AUTO_RNW_TP.DO_NOT_RENEW);
            ZYPEZDItemValueSetter.setValue(inMsg.befEndRnwDaysAot, (BigDecimal) getDBColumnValue(defaultingRule, DB_COLUMN_BEF_END_RNW_DAYS_AOT));
            ZYPEZDItemValueSetter.setValue(inMsg.contrUplftTpCd, (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_UPLFT_TP_CD));
            if (!ZYPCommonFunc.hasValue(inMsg.contrUplftTpCd)) {
                ZYPEZDItemValueSetter.setValue(inMsg.contrUplftTpCd, this.glContrUplftTpCd);
            }
            ZYPEZDItemValueSetter.setValue(inMsg.uplftPrcMethCd, (String) getDBColumnValue(defaultingRule, DB_COLUMN_UPLFT_PRC_METH_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.uplftPrcUpRatio, (BigDecimal) getDBColumnValue(defaultingRule, DB_COLUMN_UPLFT_MTR_PRC_UP_RATIO));
            ZYPEZDItemValueSetter.setValue(inMsg.contrCloDay, (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_CLO_DAY));
            // START 2017/12/28 U.Kim [QC#22735,MOD]
            // ZYPEZDItemValueSetter.setValue(inMsg.contrBllgDay, (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_BLLG_DAY));
            String ifBllgDay = (String) getDBColumnValue(usageContrIntfc, DB_COLUMN_BLLG_DAY);
            String bllgDay = null;
            if (ZYPCommonFunc.hasValue(ifBllgDay)) {
                bllgDay = ifBllgDay;
            } else {
                bllgDay = (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_BLLG_DAY);
            }
            ZYPEZDItemValueSetter.setValue(inMsg.contrBllgDay, bllgDay);
            // END 2017/12/28 U.Kim [QC#22735,MOD]
            ZYPEZDItemValueSetter.setValue(inMsg.bllgThruDt, (String) getDBColumnValue(defaultingRule, DB_COLUMN_BLLG_THRU_DT));
            if (usageContrIntfcList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbCd, this.glBllgMtrLbCdSm);
            } else {
                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbCd, this.glBllgMtrLbCdBw);
                } else {
                    ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbCd, this.glBllgMtrLbCdClr);
                }
            }
            ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbNm, this.glMtrLbNm.get(inMsg.bllgMtrLbCd.getValue()));
            if (DS_CONTR_INTFC_ACT.CREATE.equals(actionCode) || DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(actionCode)) {
                if (ROSS_INTFC_PROC_MODE_CREATE.equals(getDBColumnValue(usageContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))
                        || ROSS_INTFC_PROC_MODE_TERMINATE_CREATE.equals(getDBColumnValue(usageContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                    ZYPEZDItemValueSetter.setValue(inMsg.startMtrCnt, this.glDefaultMeterCount.get(inMsg.bllgMtrLbCd.getValue()));
                }
            }
            ZYPEZDItemValueSetter.setValue(inMsg.bllgRollOverRatio, (BigDecimal) getDBColumnValue(defaultingRule, DB_COLUMN_BLLG_ROLL_OVER_RATIO));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrCatgCd, DS_CONTR_CATG.REGULAR);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrStsCd, DS_CONTR_STS.DRAFT);
            ZYPEZDItemValueSetter.setValue(inMsg.xsChrgTpCd, XS_CHRG_TP.RANGE);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrClsCd, (String) getDBColumnValue(usageContrIntfc, DB_COLUMN_RTL_DIV_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.ctacPsnPk, (BigDecimal) getDBColumnValue(customer, DB_COLUMN_CTAC_PSN_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.ctacPsnNm,
                                           ZYPCommonFunc.concatString((String) getDBColumnValue(customer, DB_COLUMN_CTAC_PSN_FIRST_NM),
                                                                      " ",
                                                                      (String) getDBColumnValue(customer, DB_COLUMN_CTAC_PSN_LAST_NM)));
            // mod start 2017/08/18 QC#18799
//            ZYPEZDItemValueSetter.setValue(inMsg.invSeptBaseUsgFlg, (String) getDBColumnValue(customer, DB_COLUMN_DS_BILL_TGTR_FLG));
//            if (!ZYPCommonFunc.hasValue(inMsg.invSeptBaseUsgFlg)) {
//                ZYPEZDItemValueSetter.setValue(inMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_OFF_N);
//            }
            ZYPEZDItemValueSetter.setValue(inMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(dsContrIntfcDefRuleTMsg, (String) getDBColumnValue(customer, DB_COLUMN_DS_BILL_TGTR_FLG)));
            // mod end 2017/08/18 QC#18799
            ZYPEZDItemValueSetter.setValue(inMsg.contrDurnAot, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_PMT_TERM_AOT));
            ZYPEZDItemValueSetter.setValue(inMsg.pmtTermCashDiscCd, (String) getDBColumnValue(customer, DB_COLUMN_PMT_TERM_CASH_DISC_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.svcLineBizCd, SVC_LINE_BIZ_CD_ESS);
            // START 2017/03/06 K.Ochiai [QC#17747, MOD]
//            ZYPEZDItemValueSetter.setValue(inMsg.bllgTmgTpCd, (String) getDBColumnValue(customer, DB_COLUMN_DEF_BASE_TP_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.bllgTmgTpCd, DEF_USG_TP.ARREARS);
            // END 2017/03/06 K.Ochiai [QC#17747, MOD]
            // START 2017/02/20 K.Kitachi [QC#17604, MOD]
//            ZYPEZDItemValueSetter.setValue(inMsg.dsContrEdiCd, DS_CONTR_EDI.OTHER);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrEdiCd, convertMrcfTpCdToDsContrEdi((String) getDBColumnValue(usageContrIntfc, DB_COLUMN_MRCF_TP_CD)));
            // END 2017/02/20 K.Kitachi [QC#17604, MOD]
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcDt, this.glWkPmsg.slsDt);
            ZYPEZDItemValueSetter.setValue(inMsg.baseChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.usgChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.intgMdseCd, this.glIntgMdseCd.get(inMsg.bllgMtrLbCd.getValue()));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcBatSq, batchSq);
            ZYPEZDItemValueSetter.setValue(inMsg.prcSvcPlnTpCd, PRC_SVC_PLN_TP.FIXED);
            ZYPEZDItemValueSetter.setValue(inMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
            // Mod Start 2017/12/18 QC#18362
            ZYPEZDItemValueSetter.setValue(inMsg.contrAdminPsnCd, getContrAdminPsnCd(inMsg, branchRep));
            // Mod End 2017/12/18 QC#18362

            // add start 2017/11/11 QC#22175,22179
            if (DS_CONTR_INTFC_ACT.TERMINATE.equals(actionCode)) {
                if (ROSS_INTFC_PROC_MODE_TERMINATE_CREATE.equals(getDBColumnValue(usageContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                    ZYPEZDItemValueSetter.setValue(inMsg.contrCloDt, this.glWkPmsg.slsDt);
                } else {
                    ZYPEZDItemValueSetter.setValue(inMsg.contrCloDt, (String) getDBColumnValue(usageContrIntfc, DB_COLUMN_TRMN_DT));
                }
            }
            // add end 2017/11/11 QC#22175,22179

            S21ApiTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                this.glMsgMap.addXxMsgId(NSZM0972E);
                return false;
            }
        }

        return true;
    }

    private boolean createAccessoryDCContractInterface(BigDecimal prntBllgMstrId,
                                                         String batchNum,
                                                         BigDecimal batchSq,
                                                         Map<String, Object> defaultingRule,
                                                         Map<String, Object> dsContr,
                                                         String actionCode) {

        List<Map<String, Object>> accessoryContrIntfcList = getAccessoryContractInterface(prntBllgMstrId);

        for (Map<String, Object> accessoryContrIntfc : accessoryContrIntfcList) {

            BigDecimal bllgMstrPk = (BigDecimal) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_BLLG_MSTR_PK);
            Map<String, Object> customer = getCustomer(bllgMstrPk);
            Map<String, Object> branchRep = getBranchRep(bllgMstrPk);
            Map<String, Object> serviceProgram = getServiceProgram(bllgMstrPk);

            // add start 2017/08/18 QC#18799
            DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule((String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_RTL_DIV_CD));
            // add end 2017/08/18 QC#18799

            DS_CONTR_INTFCTMsg inMsg = new DS_CONTR_INTFCTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_INTFC_SQ));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcBatNum, batchNum);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrSrcRefNum, ((BigDecimal) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_BLLG_MSTR_PK)).toPlainString());
            ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.CUSA);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcActCd, actionCode);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.IN_PROCESS);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
            // mod start 2017/11/11 QC#22175
            //if (DS_CONTR_INTFC_ACT.UPDATE.equals(actionCode) || DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(actionCode)) {
            if (!DS_CONTR_INTFC_ACT.CREATE.equals(actionCode)) {
            // mod end 2017/11/11 QC#22175    
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrNum, (String) getDBColumnValue(dsContr, DB_COLUMN_DS_CONTR_NUM));
            }
            ZYPEZDItemValueSetter.setValue(inMsg.serNum, (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_SER_NUM));
            ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, (BigDecimal) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_SVC_MACH_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcDtlTpCd, CONTR_INTFC_DTL_TP.BASE);
            ZYPEZDItemValueSetter.setValue(inMsg.dsAcctNum, (String) getDBColumnValue(customer, DB_COLUMN_SELL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.billToCustCd, (String) getDBColumnValue(customer, DB_COLUMN_BILL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.svcContrBrCd, (String) getDBColumnValue(branchRep, DB_COLUMN_SVC_CONTR_BR_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.tocCd, (String) getDBColumnValue(branchRep, DB_COLUMN_TOC_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.mtrEstTpCd, (String) getDBColumnValue(defaultingRule, DB_COLUMN_MTR_EST_TP_CD));
            if (!ZYPCommonFunc.hasValue(inMsg.mtrEstTpCd)) {
                ZYPEZDItemValueSetter.setValue(inMsg.mtrEstTpCd, this.glMtrEstTpCd);
            }
            ZYPEZDItemValueSetter.setValue(inMsg.svcPgmMdseCd, (String) getDBColumnValue(serviceProgram, DB_COLUMN_SVC_PGM_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.mdlId, (BigDecimal) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_MDL_ID));
            ZYPEZDItemValueSetter.setValue(inMsg.mdlNm, (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_T_MDL_NM));
            if (DS_CONTR_INTFC_ACT.CREATE.equals(actionCode) || DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(actionCode)) {
                if (ROSS_INTFC_PROC_MODE_TERMINATE_CREATE.equals(getDBColumnValue(accessoryContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                    if (ZYPDateUtil.compare((String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_CONTR_START_DT),  //
                                             this.glWkPmsg.slsDt.getValue()) > 0) {                                    //
                        ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_CONTR_START_DT));
                    } else {
                        ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, this.glWkPmsg.slsDt);
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_CONTR_START_DT));
                }
            } else {
                ZYPEZDItemValueSetter.setValue(inMsg.contrFromDt, (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_CONTR_START_DT));
            }
            ZYPEZDItemValueSetter.setValue(inMsg.contrThruDt, (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_CONTR_END_DT));
            // START 2017/03/06 K.Ochiai [QC#17747, MOD]
//            ZYPEZDItemValueSetter.setValue(inMsg.bllgCycleCd, BLLG_CYCLE.MONTHLY);
            String billCycleCd = null;
            billCycleCd = (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_BLLG_CYCLE_CD);
            if (!ZYPCommonFunc.hasValue(billCycleCd)) {
                billCycleCd = (String) getDBColumnValue(customer, DB_COLUMN_DEF_BASE_CYCLE_CD);
            }
            if (!ZYPCommonFunc.hasValue(billCycleCd)) {
                // del start 2017/08/18 QC#18799
//                DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule();
                // del end 2017/08/18 QC#18799
                if (dsContrIntfcDefRuleTMsg != null) {
                    billCycleCd = dsContrIntfcDefRuleTMsg.baseBllgCycleCd.getValue();
                }
            }
            ZYPEZDItemValueSetter.setValue(inMsg.bllgCycleCd, billCycleCd);
            // END 2017/03/06 K.Ochiai [QC#17747, MOD]
            ZYPEZDItemValueSetter.setValue(inMsg.prcAllocByMachQtyFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.contrAutoRnwTpCd, CONTR_AUTO_RNW_TP.DO_NOT_RENEW);
            ZYPEZDItemValueSetter.setValue(inMsg.befEndRnwDaysAot, (BigDecimal) getDBColumnValue(defaultingRule, DB_COLUMN_BEF_END_RNW_DAYS_AOT));
            ZYPEZDItemValueSetter.setValue(inMsg.contrUplftTpCd, (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_UPLFT_TP_CD));
            if (!ZYPCommonFunc.hasValue(inMsg.contrUplftTpCd)) {
                ZYPEZDItemValueSetter.setValue(inMsg.contrUplftTpCd, this.glContrUplftTpCd);
            }
            ZYPEZDItemValueSetter.setValue(inMsg.uplftPrcMethCd, (String) getDBColumnValue(defaultingRule, DB_COLUMN_UPLFT_PRC_METH_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.uplftPrcUpRatio, (BigDecimal) getDBColumnValue(defaultingRule, DB_COLUMN_UPLFT_BASE_PRC_UP_RATIO));
            ZYPEZDItemValueSetter.setValue(inMsg.basePrcDealAmt, (BigDecimal) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_MLY_BASE_COMP_AMT));
            ZYPEZDItemValueSetter.setValue(inMsg.contrCloDay, (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_CLO_DAY));
            // START 2017/12/28 U.Kim [QC#22735,MOD]
            // ZYPEZDItemValueSetter.setValue(inMsg.contrBllgDay, (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_BLLG_DAY));
            String ifBllgDay = (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_BLLG_DAY);
            String bllgDay = null;
            if (ZYPCommonFunc.hasValue(ifBllgDay)) {
                bllgDay = ifBllgDay;
            } else {
                bllgDay = (String) getDBColumnValue(defaultingRule, DB_COLUMN_CONTR_BLLG_DAY);
            }
            ZYPEZDItemValueSetter.setValue(inMsg.contrBllgDay, bllgDay);
            // END 2017/12/28 U.Kim [QC#22735,MOD]
            ZYPEZDItemValueSetter.setValue(inMsg.bllgThruDt, (String) getDBColumnValue(defaultingRule, DB_COLUMN_BLLG_THRU_DT));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrCatgCd, DS_CONTR_CATG.REGULAR);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrStsCd, DS_CONTR_STS.DRAFT);
            ZYPEZDItemValueSetter.setValue(inMsg.xsChrgTpCd, XS_CHRG_TP.RANGE);
            ZYPEZDItemValueSetter.setValue(inMsg.basePrcTermDealAmtRate,
                                           ((BigDecimal) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_MLY_BASE_COMP_AMT)).multiply(
                                                   (BigDecimal) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_PMT_TERM_AOT)).divide(
                                                           (BigDecimal) getDBColumnValue(this.glMonthlyBllgCycle, DB_COLUMN_BLLG_CYCLE_MTH_AOT), 0, BigDecimal.ROUND_DOWN)
                                           );
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrClsCd, (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_RTL_DIV_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.ctacPsnPk, (BigDecimal) getDBColumnValue(customer, DB_COLUMN_CTAC_PSN_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.ctacPsnNm,
                                           ZYPCommonFunc.concatString((String) getDBColumnValue(customer, DB_COLUMN_CTAC_PSN_FIRST_NM),
                                                                      " ",
                                                                      (String) getDBColumnValue(customer, DB_COLUMN_CTAC_PSN_LAST_NM)));
            // mod start 2017/08/18 QC#18799
//            ZYPEZDItemValueSetter.setValue(inMsg.invSeptBaseUsgFlg, (String) getDBColumnValue(customer, DB_COLUMN_DS_BILL_TGTR_FLG));
//            if (!ZYPCommonFunc.hasValue(inMsg.invSeptBaseUsgFlg)) {
//                ZYPEZDItemValueSetter.setValue(inMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_OFF_N);
//            }
            ZYPEZDItemValueSetter.setValue(inMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(dsContrIntfcDefRuleTMsg, (String) getDBColumnValue(customer, DB_COLUMN_DS_BILL_TGTR_FLG)));
            // mod end 2017/08/18 QC#18799
            ZYPEZDItemValueSetter.setValue(inMsg.contrDurnAot, (BigDecimal) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_PMT_TERM_AOT));
            ZYPEZDItemValueSetter.setValue(inMsg.pmtTermCashDiscCd, (String) getDBColumnValue(customer, DB_COLUMN_PMT_TERM_CASH_DISC_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.svcLineBizCd, SVC_LINE_BIZ_CD_ESS);
            // START 2017/03/06 K.Ochiai [QC#17747, MOD]
//            ZYPEZDItemValueSetter.setValue(inMsg.bllgTmgTpCd, (String) getDBColumnValue(customer, DB_COLUMN_DEF_BASE_TP_CD));
            String ifBllgTmgTpCd = (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_BLLG_TMG_TP_CD);
            String bllgTmgTpCd = null;
            if (ZYPCommonFunc.hasValue(ifBllgTmgTpCd)) {
                bllgTmgTpCd = getBllgTmgTpCd(ifBllgTmgTpCd);
            }
            if (!ZYPCommonFunc.hasValue(bllgTmgTpCd)) {
                bllgTmgTpCd = DEF_BASE_TP.ADVANCE;
            }
            ZYPEZDItemValueSetter.setValue(inMsg.bllgTmgTpCd, bllgTmgTpCd);
            // END 2017/03/06 K.Ochiai [QC#17747, MOD]
            // START 2017/02/20 K.Kitachi [QC#17604, MOD]
//            ZYPEZDItemValueSetter.setValue(inMsg.dsContrEdiCd, DS_CONTR_EDI.OTHER);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrEdiCd, convertMrcfTpCdToDsContrEdi((String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_MRCF_TP_CD)));
            // END 2017/02/20 K.Kitachi [QC#17604, MOD]
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcDt, this.glWkPmsg.slsDt);
            ZYPEZDItemValueSetter.setValue(inMsg.baseChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.usgChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcBatSq, batchSq);
            ZYPEZDItemValueSetter.setValue(inMsg.prcSvcPlnTpCd, PRC_SVC_PLN_TP.FIXED);
            ZYPEZDItemValueSetter.setValue(inMsg.printDtlFlg, (String) getDBColumnValue(defaultingRule, DB_COLUMN_PRINT_DTL_FLG));
            if (!ZYPCommonFunc.hasValue(inMsg.printDtlFlg)) {
                ZYPEZDItemValueSetter.setValue(inMsg.printDtlFlg, this.glPrintDtlFlg);
            }
            ZYPEZDItemValueSetter.setValue(inMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
            // Mod Start 2017/12/18 QC#18362
            ZYPEZDItemValueSetter.setValue(inMsg.contrAdminPsnCd, getContrAdminPsnCd(inMsg, branchRep));
            // Mod End 2017/12/18 QC#18362

            // add start 2017/11/11 QC#22175,22179
            if (DS_CONTR_INTFC_ACT.TERMINATE.equals(actionCode)) {
                if (ROSS_INTFC_PROC_MODE_TERMINATE_CREATE.equals(getDBColumnValue(accessoryContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                    ZYPEZDItemValueSetter.setValue(inMsg.contrCloDt, this.glWkPmsg.slsDt);
                } else {
                    ZYPEZDItemValueSetter.setValue(inMsg.contrCloDt, (String) getDBColumnValue(accessoryContrIntfc, DB_COLUMN_TRMN_DT));
                }
            }
            // add end 2017/11/11 QC#22175,22179

            S21ApiTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                this.glMsgMap.addXxMsgId(NSZM0972E);
                return false;
            }
        }

        return true;
    }

    private boolean createDCActualCounterInterface(BigDecimal prntBllgMstrId,
                                                     String batchNum,
                                                     List<Map<String, Object>> usageContrIntfcList,
                                                     String actionCode) {

        List<Map<String, Object>> actualCounterContrIntfcList = getActualCounterContractInterface(prntBllgMstrId);

        for (Map<String, Object> actualCounterContrIntfc : actualCounterContrIntfcList) {

            DS_ACTL_CNT_INTFCTMsg inMsg = new DS_ACTL_CNT_INTFCTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsActlCntIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ACTL_CNT_INTFC_SQ));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcBatNum, batchNum);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrSrcRefNum, ((BigDecimal) getDBColumnValue(actualCounterContrIntfc, DB_COLUMN_BLLG_MSTR_PK)).toPlainString());
            ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.CUSA);
            ZYPEZDItemValueSetter.setValue(inMsg.actlCntIntfcStsCd, ACTL_CNT_INTFC_STS.NORMAL);
            ZYPEZDItemValueSetter.setValue(inMsg.serNum, (String) getDBColumnValue(actualCounterContrIntfc, DB_COLUMN_SER_NUM));
            ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, (BigDecimal) getDBColumnValue(actualCounterContrIntfc, DB_COLUMN_SVC_MACH_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, (String) getDBColumnValue(actualCounterContrIntfc, DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.physMtrLbCd, (String) getDBColumnValue(actualCounterContrIntfc, DB_COLUMN_MDL_MTR_LB_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.physMtrLbNm, (String) getDBColumnValue(actualCounterContrIntfc, DB_COLUMN_MTR_LB_NM));
            ZYPEZDItemValueSetter.setValue(inMsg.bllblFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(inMsg.contrMtrMultRate, BigDecimal.ONE);
            if (usageContrIntfcList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbCd, this.glBllgMtrLbCdSm);
            } else if (usageContrIntfcList.size() == 2) {
                if (ZYPConstant.FLG_ON_Y.equals((String) getDBColumnValue(actualCounterContrIntfc, DB_COLUMN_BW_MTR_FLG))) {
                    ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbCd, this.glBllgMtrLbCdBw);
                } else {
                    ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbCd, this.glBllgMtrLbCdClr);
                }
            }
            ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbNm, this.glMtrLbNm.get(inMsg.bllgMtrLbCd.getValue()));
            ZYPEZDItemValueSetter.setValue(inMsg.intgMdseCd, this.glIntgMdseCd.get(inMsg.bllgMtrLbCd.getValue()));

            S21ApiTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                this.glMsgMap.addXxMsgId(NSZM0974E);
                return false;
            }
        }

        return true;
    }

    private boolean createDSExcessCopyInterface(BigDecimal prntBllgMstrId,
                                                  String batchNum,
                                                  List<Map<String, Object>> usageContrIntfcList,
                                                  String actionCode) {

        List<Map<String, Object>> tierContrIntfcList = getTierContractInterface(prntBllgMstrId);

        BigDecimal preBllgMstrBllgMtrPk = null;
        BigDecimal preXsMtrCompCopyQty = null;
        for (Map<String, Object> tierContrIntfc : tierContrIntfcList) {

            DS_XS_COPY_INTFCTMsg inMsg = new DS_XS_COPY_INTFCTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsXsCopyIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_XS_COPY_INTFC_SQ));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcBatNum, batchNum);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrSrcRefNum, ((BigDecimal) getDBColumnValue(tierContrIntfc, DB_COLUMN_BLLG_MSTR_PK)).toPlainString());
            ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.CUSA);
            ZYPEZDItemValueSetter.setValue(inMsg.xsCopyIntfcStsCd, XS_COPY_INTFC_STS.NORMAL);
            ZYPEZDItemValueSetter.setValue(inMsg.serNum, (String) getDBColumnValue(tierContrIntfc, DB_COLUMN_SER_NUM));
            ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, (BigDecimal) getDBColumnValue(tierContrIntfc, DB_COLUMN_SVC_MACH_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, (String) getDBColumnValue(tierContrIntfc, DB_COLUMN_MDSE_CD));
            if (usageContrIntfcList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbCd, this.glBllgMtrLbCdSm);
            } else if (usageContrIntfcList.size() == 2) {
                if (getDBColumnValue(tierContrIntfc, DB_COLUMN_BLLG_MSTR_BLLG_MTR_PK) != null) {
                    Map<String, Object> usageContrIntfc = usageContrIntfcList.get(0);
                    if (((BigDecimal) getDBColumnValue(tierContrIntfc, DB_COLUMN_BLLG_MSTR_BLLG_MTR_PK)).compareTo(
                            (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_BLLG_MSTR_BLLG_MTR_PK)) == 0) {
                        ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbCd, this.glBllgMtrLbCdBw);
                    } else {
                        ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbCd, this.glBllgMtrLbCdClr);
                    }
                }
            }
            ZYPEZDItemValueSetter.setValue(inMsg.bllgMtrLbNm, this.glMtrLbNm.get(inMsg.bllgMtrLbCd.getValue()));
            if (preBllgMstrBllgMtrPk == null
                    || preBllgMstrBllgMtrPk.compareTo((BigDecimal) getDBColumnValue(tierContrIntfc, DB_COLUMN_BLLG_MSTR_BLLG_MTR_PK)) != 0) {
                ZYPEZDItemValueSetter.setValue(inMsg.xsMtrCopyQty, (BigDecimal) getDBColumnValue(tierContrIntfc, DB_COLUMN_MLY_COPY_INCL_COMP_QTY));
                preBllgMstrBllgMtrPk = (BigDecimal) getDBColumnValue(tierContrIntfc, DB_COLUMN_BLLG_MSTR_BLLG_MTR_PK);
                preXsMtrCompCopyQty = (BigDecimal) getDBColumnValue(tierContrIntfc, DB_COLUMN_XS_MTR_COMP_COPY_QTY);
            } else {
                ZYPEZDItemValueSetter.setValue(inMsg.xsMtrCopyQty, preXsMtrCompCopyQty);
            }
            ZYPEZDItemValueSetter.setValue(inMsg.xsMtrAmtRate, (BigDecimal) getDBColumnValue(tierContrIntfc, DB_COLUMN_XS_MTR_COMP_AMT_RATE));
            ZYPEZDItemValueSetter.setValue(inMsg.xsMtrLvlNum, this.glBllgMtrLvlNum.get(inMsg.bllgMtrLbCd.getValue()));

            S21ApiTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                this.glMsgMap.addXxMsgId(NSZM0975E);
                return false;
            }
        }

        return true;
    }

    // START 2017/03/06 K.Ochiai [QC#17747, MOD]
//    private boolean createSubContract(BigDecimal prntBllgMstrId,
//                                        String batchNum,
//                                        Map<String, Object> dsContr,
//                                        Map<String, Object> baseContrIntfc,
//                                        List<Map<String, Object>> usageContrIntfcList,
//                                        String actionCode) {
    private boolean createSubContract(BigDecimal prntBllgMstrId,
                                        String batchNum,
                                        Map<String, Object> dsContr,
                                        Map<String, Object> baseContrIntfc,
                                        List<Map<String, Object>> usageContrIntfcList,
                                        String actionCode,
                                        Map<String, Object> customer) {
    // END 2017/03/06 K.Ochiai [QC#17747, MOD]

        if (!isExistCsaFedexSvcDlr(prntBllgMstrId)) {
            return true;
        }

        if (DS_CONTR_INTFC_ACT.TERMINATE.equals(actionCode)) {

            //QC#15410 mod Start
//            Map<String, Object> contrIntfc = getContractInterfaceForTerminateSubContract(baseContrIntfc);
//            // START 2018/01/15 U.Kim [QC#23435,ADD]
//            if (contrIntfc == null) {
//                return true;
//            }
//            // END 2018/01/15 U.Kim [QC#23435,ADD]
            List<Map<String, Object>> contrIntfcList = getContractInterfaceForTerminateSubContract(baseContrIntfc);
            //QC#15410 mod End

            for (Map<String, Object> contrIntfc : contrIntfcList) {     //QC#15410 add
                DS_SUB_CONTR_INTFCTMsg inMsg = new DS_SUB_CONTR_INTFCTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inMsg.dsSubContrIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SUB_CONTR_INTFC_SQ));
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcBatNum, batchNum);
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrSrcRefNum, ((BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_BLLG_MSTR_PK)).toPlainString());
                ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.CUSA);
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcActCd, actionCode);
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.IN_PROCESS);
                ZYPEZDItemValueSetter.setValue(inMsg.subContrIntfcStsCd, SUB_CONTR_INTFC_STS_NORMAL);
                ZYPEZDItemValueSetter.setValue(inMsg.serNum, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_SER_NUM));
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrNum, (String) getDBColumnValue(contrIntfc, DB_COLUMN_DS_CONTR_NUM));
                ZYPEZDItemValueSetter.setValue(inMsg.vndCd, (String) getDBColumnValue(contrIntfc, DB_COLUMN_VND_CD));
                ZYPEZDItemValueSetter.setValue(inMsg.techCd, (String) getDBColumnValue(contrIntfc, DB_COLUMN_TECH_TOC_CD));
                ZYPEZDItemValueSetter.setValue(inMsg.effFromDt, (String) getDBColumnValue(contrIntfc, DB_COLUMN_EFF_FROM_DT));
                ZYPEZDItemValueSetter.setValue(inMsg.effThruDt, (String) getDBColumnValue(contrIntfc, DB_COLUMN_EFF_THRU_DT));
                ZYPEZDItemValueSetter.setValue(inMsg.contrTrmnFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(inMsg.basePrcDealAmt, (BigDecimal) getDBColumnValue(contrIntfc, DB_COLUMN_BASE_PRC_DEAL_AMT));
                ZYPEZDItemValueSetter.setValue(inMsg.adminPrcDealAmt, (BigDecimal) getDBColumnValue(contrIntfc, DB_COLUMN_ADMIN_PRC_DEAL_AMT));
                ZYPEZDItemValueSetter.setValue(inMsg.prepdMaintFlg, (String) getDBColumnValue(contrIntfc, DB_COLUMN_PREPD_MAINT_FLG));
                ZYPEZDItemValueSetter.setValue(inMsg.bwMtrAlwncCnt, (BigDecimal) getDBColumnValue(contrIntfc, DB_COLUMN_BW_MTR_ALWNC_CNT));
                ZYPEZDItemValueSetter.setValue(inMsg.colorMtrAlwncCnt, (BigDecimal) getDBColumnValue(contrIntfc, DB_COLUMN_COLOR_MTR_ALWNC_CNT));
                ZYPEZDItemValueSetter.setValue(inMsg.bwMtrPrcAmtRate, (BigDecimal) getDBColumnValue(contrIntfc, DB_COLUMN_BW_MTR_PRC_AMT_RATE));
                ZYPEZDItemValueSetter.setValue(inMsg.colorMtrPrcAmtRate, (BigDecimal) getDBColumnValue(contrIntfc, DB_COLUMN_COLOR_MTR_PRC_AMT_RATE));
                ZYPEZDItemValueSetter.setValue(inMsg.splyInclFlg, (String) getDBColumnValue(contrIntfc, DB_COLUMN_SPLY_INCL_FLG));
                ZYPEZDItemValueSetter.setValue(inMsg.bllgCycleCd, (String) getDBColumnValue(contrIntfc, DB_COLUMN_BLLG_CYCLE_CD));
                ZYPEZDItemValueSetter.setValue(inMsg.dlrFleetFlg, (String) getDBColumnValue(contrIntfc, DB_COLUMN_DLR_FLEET_FLG));
                ZYPEZDItemValueSetter.setValue(inMsg.dlrFleetNum, (String) getDBColumnValue(contrIntfc, DB_COLUMN_DLR_FLEET_NUM));
                
                S21ApiTBLAccessor.insert(inMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    this.glMsgMap.addXxMsgId(NSZM0977E);
                    return false;
                }
            }

        } else if (DS_CONTR_INTFC_ACT.CREATE.equals(actionCode) || DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(actionCode)) {

            Map<String, Object> contrIntfc = getContractInterfaceForCreateSubContract(baseContrIntfc);

            // add start 2017/08/18 QC#18799
            DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule((String) getDBColumnValue(contrIntfc, DB_COLUMN_RTL_DIV_CD));
            // add end 2017/08/18 QC#18799

            DS_SUB_CONTR_INTFCTMsg inMsg = new DS_SUB_CONTR_INTFCTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsSubContrIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SUB_CONTR_INTFC_SQ));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcBatNum, batchNum);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrSrcRefNum, ((BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_BLLG_MSTR_PK)).toPlainString());
            ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.CUSA);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcActCd, actionCode);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.IN_PROCESS);
            ZYPEZDItemValueSetter.setValue(inMsg.subContrIntfcStsCd, SUB_CONTR_INTFC_STS_NORMAL);
            ZYPEZDItemValueSetter.setValue(inMsg.serNum, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_SER_NUM));
            if (DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(actionCode)) {
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrNum, (String) getDBColumnValue(dsContr, DB_COLUMN_DS_CONTR_NUM));
            }
            ZYPEZDItemValueSetter.setValue(inMsg.vndCd, (String) getDBColumnValue(contrIntfc, DB_COLUMN_VND_CD));
            if (getDBColumnValue(baseContrIntfc, DB_COLUMN_PRF_TECH_CD) != null) {
                ZYPEZDItemValueSetter.setValue(inMsg.techCd, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_PRF_TECH_CD));
            } else {
                ZYPEZDItemValueSetter.setValue(inMsg.techCd, (String) getDBColumnValue(contrIntfc, DB_COLUMN_PSN_CD));
            }
            if (ROSS_INTFC_PROC_MODE_CREATE.equals(getDBColumnValue(baseContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                ZYPEZDItemValueSetter.setValue(inMsg.effFromDt, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_CONTR_START_DT));
            } else if (ROSS_INTFC_PROC_MODE_TERMINATE_CREATE.equals(getDBColumnValue(baseContrIntfc, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))) {
                if (ZYPDateUtil.compare((String) getDBColumnValue(baseContrIntfc, DB_COLUMN_CONTR_START_DT),
                                         this.glWkPmsg.slsDt.getValue()) > 0) {
                    ZYPEZDItemValueSetter.setValue(inMsg.effFromDt, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_CONTR_START_DT));
                } else {
                    ZYPEZDItemValueSetter.setValue(inMsg.effFromDt, this.glWkPmsg.slsDt);
                }
            }
            ZYPEZDItemValueSetter.setValue(inMsg.effThruDt, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_CONTR_END_DT));
            ZYPEZDItemValueSetter.setValue(inMsg.contrTrmnFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.basePrcDealAmt, (BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_MLY_BASE_COMP_AMT));
            ZYPEZDItemValueSetter.setValue(inMsg.adminPrcDealAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inMsg.prepdMaintFlg, ZYPConstant.FLG_OFF_N);
            if (usageContrIntfcList.size() == 1) {
                Map<String, Object> usageContrIntfc = usageContrIntfcList.get(0);
                ZYPEZDItemValueSetter.setValue(inMsg.bwMtrAlwncCnt, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_MLY_COPY_INCL_COMP_QTY));
                ZYPEZDItemValueSetter.setValue(inMsg.bwMtrPrcAmtRate, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_XS_MTR_COMP_AMT_RATE));
            } else if (usageContrIntfcList.size() == 2) {
                Map<String, Object> usageContrIntfc = usageContrIntfcList.get(0);
                ZYPEZDItemValueSetter.setValue(inMsg.bwMtrAlwncCnt, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_MLY_COPY_INCL_COMP_QTY));
                ZYPEZDItemValueSetter.setValue(inMsg.bwMtrPrcAmtRate, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_XS_MTR_COMP_AMT_RATE));
                usageContrIntfc = usageContrIntfcList.get(1);
                ZYPEZDItemValueSetter.setValue(inMsg.colorMtrAlwncCnt, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_MLY_COPY_INCL_COMP_QTY));
                ZYPEZDItemValueSetter.setValue(inMsg.colorMtrPrcAmtRate, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_XS_MTR_COMP_AMT_RATE));
            }
            ZYPEZDItemValueSetter.setValue(inMsg.splyInclFlg, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_SPLY_INCL_FLG));
            // START 2017/03/06 K.Ochiai [QC#17747, MOD]
            String billCycleCd = null;
            billCycleCd = (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_BLLG_CYCLE_CD);
            if (!ZYPCommonFunc.hasValue(billCycleCd)) {
                billCycleCd = (String) getDBColumnValue(customer, DB_COLUMN_DEF_BASE_CYCLE_CD);
            }
            if (!ZYPCommonFunc.hasValue(billCycleCd)) {
                // del start 2017/08/18 QC#18799
//                DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule();
                // del end 2017/08/18 QC#18799
                if (dsContrIntfcDefRuleTMsg != null) {
                    billCycleCd = dsContrIntfcDefRuleTMsg.baseBllgCycleCd.getValue();
                }
            }
            ZYPEZDItemValueSetter.setValue(inMsg.bllgCycleCd, billCycleCd);
            // END 2017/03/06 K.Ochiai [QC#17747, MOD]
            ZYPEZDItemValueSetter.setValue(inMsg.dlrFleetFlg, ZYPConstant.FLG_OFF_N);

            S21ApiTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                this.glMsgMap.addXxMsgId(NSZM0977E);
                return false;
            }

        } else if (DS_CONTR_INTFC_ACT.UPDATE.equals(actionCode)) {

            //QC#15410 mod Start
//            Map<String, Object> contrIntfc = getContractInterfaceForUpdateSubContract(baseContrIntfc);
//            // START 2018/01/15 U.Kim [QC#23435,ADD]
//            if (contrIntfc == null) {
//                return true;
//            }
//            // END 2018/01/15 U.Kim [QC#23435,ADD]
            List<Map<String, Object>> contrIntfcList = getContractInterfaceForUpdateSubContract(baseContrIntfc);
            //QC#15410 mod End

            for (Map<String, Object> contrIntfc : contrIntfcList) { //QC#15410 add

                DS_SUB_CONTR_INTFCTMsg inMsg = new DS_SUB_CONTR_INTFCTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inMsg.dsSubContrIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SUB_CONTR_INTFC_SQ));
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcBatNum, batchNum);
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrSrcRefNum, ((BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_BLLG_MSTR_PK)).toPlainString());
                ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.CUSA);
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrIntfcActCd, actionCode);
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.IN_PROCESS);
                ZYPEZDItemValueSetter.setValue(inMsg.subContrIntfcStsCd, SUB_CONTR_INTFC_STS_NORMAL);
                ZYPEZDItemValueSetter.setValue(inMsg.serNum, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_SER_NUM));
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrNum, (String) getDBColumnValue(dsContr, DB_COLUMN_DS_CONTR_NUM));
                ZYPEZDItemValueSetter.setValue(inMsg.vndCd, (String) getDBColumnValue(contrIntfc, DB_COLUMN_VND_CD));
                if (getDBColumnValue(baseContrIntfc, DB_COLUMN_PRF_TECH_CD) != null) {
                    ZYPEZDItemValueSetter.setValue(inMsg.techCd, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_PRF_TECH_CD));
                } else {
                    ZYPEZDItemValueSetter.setValue(inMsg.techCd, (String) getDBColumnValue(contrIntfc, DB_COLUMN_PSN_CD));
                }
                ZYPEZDItemValueSetter.setValue(inMsg.effFromDt, (String) getDBColumnValue(contrIntfc, DB_COLUMN_EFF_FROM_DT));
                ZYPEZDItemValueSetter.setValue(inMsg.effThruDt, (String) getDBColumnValue(contrIntfc, DB_COLUMN_EFF_THRU_DT));
                ZYPEZDItemValueSetter.setValue(inMsg.contrTrmnFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(inMsg.basePrcDealAmt, (BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_MLY_BASE_COMP_AMT));
                ZYPEZDItemValueSetter.setValue(inMsg.adminPrcDealAmt, (BigDecimal) getDBColumnValue(contrIntfc, DB_COLUMN_ADMIN_PRC_DEAL_AMT));
                ZYPEZDItemValueSetter.setValue(inMsg.prepdMaintFlg, (String) getDBColumnValue(contrIntfc, DB_COLUMN_PREPD_MAINT_FLG));
                if (usageContrIntfcList.size() == 1) {
                    Map<String, Object> usageContrIntfc = usageContrIntfcList.get(0);
                    ZYPEZDItemValueSetter.setValue(inMsg.bwMtrAlwncCnt, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_MLY_COPY_INCL_COMP_QTY));
                    ZYPEZDItemValueSetter.setValue(inMsg.bwMtrPrcAmtRate, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_XS_MTR_COMP_AMT_RATE));
                } else if (usageContrIntfcList.size() == 2) {
                    Map<String, Object> usageContrIntfc = usageContrIntfcList.get(0);
                    ZYPEZDItemValueSetter.setValue(inMsg.bwMtrAlwncCnt, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_MLY_COPY_INCL_COMP_QTY));
                    ZYPEZDItemValueSetter.setValue(inMsg.bwMtrPrcAmtRate, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_XS_MTR_COMP_AMT_RATE));
                    usageContrIntfc = usageContrIntfcList.get(1);
                    ZYPEZDItemValueSetter.setValue(inMsg.colorMtrAlwncCnt, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_MLY_COPY_INCL_COMP_QTY));
                    ZYPEZDItemValueSetter.setValue(inMsg.colorMtrPrcAmtRate, (BigDecimal) getDBColumnValue(usageContrIntfc, DB_COLUMN_XS_MTR_COMP_AMT_RATE));
                }
                ZYPEZDItemValueSetter.setValue(inMsg.splyInclFlg, (String) getDBColumnValue(baseContrIntfc, DB_COLUMN_SPLY_INCL_FLG));
                ZYPEZDItemValueSetter.setValue(inMsg.bllgCycleCd, (String) getDBColumnValue(contrIntfc, DB_COLUMN_BLLG_CYCLE_CD));
                ZYPEZDItemValueSetter.setValue(inMsg.dlrFleetFlg, (String) getDBColumnValue(contrIntfc, DB_COLUMN_DLR_FLEET_FLG));
                ZYPEZDItemValueSetter.setValue(inMsg.dlrFleetNum, (String) getDBColumnValue(contrIntfc, DB_COLUMN_DLR_FLEET_NUM));
                
                S21ApiTBLAccessor.insert(inMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    this.glMsgMap.addXxMsgId(NSZM0977E);
                    return false;
                }

            }

        }

        return true;
    }

    private boolean isExistProcCodeError(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_PRNT_BLLG_MSTR_ID, prntBllgMstrId);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_ERROR);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        BigDecimal result = (BigDecimal) this.glSsmBatchClient.queryObject("countProcCodeError", queryParam);
        return (result.intValue() > 0);
    }

    private boolean isExistDealer(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParamDealer = new HashMap<String, Object>();
        queryParamDealer.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParamDealer.put(DB_PARAM_BLLG_MSTR_PK, prntBllgMstrId);
        queryParamDealer.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParamDealer.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);
        queryParamDealer.put(DB_PARAM_SALES_DATE, this.glWkPmsg.slsDt.getValue());

        BigDecimal dealerCount = (BigDecimal) this.glSsmBatchClient.queryObject("countDealer", queryParamDealer);

        if (dealerCount.intValue() > 0) {
            return true;
        }

        Map<String, Object> queryParamRossIntfc = new HashMap<String, Object>();
        queryParamRossIntfc.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParamRossIntfc.put(DB_PARAM_BLLG_MSTR_PK, prntBllgMstrId);
        queryParamRossIntfc.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParamRossIntfc.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        Map<String, Object> dlrCdAndSerNum = (Map<String, Object>) this.glSsmBatchClient.queryObject("getDlrCdAndSerNum", queryParamRossIntfc);
        if (dlrCdAndSerNum == null) {
            return false;
        }

        Map<String, Object> queryParamFedex = new HashMap<String, Object>();
        queryParamFedex.put(DB_PARAM_GLBL_CMPY_CD_ABR, this.glCusaGlblCmpyCd);
        queryParamFedex.put(DB_PARAM_RTL_DLR_CD, dlrCdAndSerNum.get(DB_COLUMN_SVC_DLR_CD));

        BigDecimal fedexCount = (BigDecimal) this.glSsmBatchClient.queryObject("countFedex", queryParamFedex);

        Map<String, Object> queryParamSerial = new HashMap<String, Object>();
        queryParamSerial.put(DB_PARAM_GLBL_CMPY_CD_ABR, this.glCusaGlblCmpyCd);
        queryParamSerial.put(DB_PARAM_SER_NUM, dlrCdAndSerNum.get(DB_COLUMN_SER_NUM));

        BigDecimal serialCount = (BigDecimal) this.glSsmBatchClient.queryObject("countSerial", queryParamSerial);

        return (fedexCount.intValue() > 0 && serialCount.intValue() > 0);
    }

    private boolean isEndOfLife(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, prntBllgMstrId);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);
        queryParam.put(DB_PARAM_SALES_DATE, this.glWkPmsg.slsDt.getValue());
        queryParam.put(DB_PARAM_EOL_CONTR_FLG, ZYPConstant.FLG_ON_Y);

        BigDecimal result = (BigDecimal) this.glSsmBatchClient.queryObject("countEndOfLife", queryParam);
        return (result.intValue() > 0);
    }

    private boolean isEntryToday(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_PRNT_BLLG_MSTR_ID, prntBllgMstrId.toPlainString());
        queryParam.put(DB_PARAM_SALES_DATE, this.glWkPmsg.slsDt.getValue());

        BigDecimal result = (BigDecimal) this.glSsmBatchClient.queryObject("countEntryToday", queryParam);
        return (result.intValue() > 0);
    }

    private boolean isExistCsaFedexSvcDlr(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, prntBllgMstrId);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);
        queryParam.put(DB_PARAM_GLBL_CMPY_CD_ABR, this.glCusaGlblCmpyCd);

        BigDecimal result = (BigDecimal) this.glSsmBatchClient.queryObject("countCsaFedexSvcDlr", queryParam);
        return (result.intValue() > 0);
    }

    private boolean isExistProcModeSkip(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, prntBllgMstrId);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_MODE_CD, ROSS_INTFC_PROC_MODE_SKIP);

        BigDecimal result = (BigDecimal) this.glSsmBatchClient.queryObject("countProcModeSkip", queryParam);
        return (result.intValue() > 0);
    }

    private boolean updateProcCode(BigDecimal prntBllgMstrId, String procCd, String procTxt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_PRNT_BLLG_MSTR_ID, prntBllgMstrId);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getContractInterfaceFromPrntBllgMstrID", queryParam);
        if (resultList == null) {
            return true;
        }

        for (Map<String, Object> record : resultList) {

            ROSS_INTFC_CONTRTMsg inMsg = new ROSS_INTFC_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glWkPmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.rossIntfcContrPk, (BigDecimal) record.get(DB_COLUMN_ROSS_INTFC_CONTR_PK));

            ROSS_INTFC_CONTRTMsg outMsg = (ROSS_INTFC_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                this.glMsgMap.addXxMsgId(NSZM0971E);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(outMsg.rossIntfcContrProcCd, procCd);
            ZYPEZDItemValueSetter.setValue(outMsg.rossIntfcContrProcTxt, procTxt);
            S21ApiTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                this.glMsgMap.addXxMsgId(NSZM0971E);
                return false;
            }
        }

        return true;
    }

    private Map<String, Object> getBaseContractInterface(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, prntBllgMstrId);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getBaseContractInterface", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    private List<Map<String, Object>> getUsageContractInterface(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, prntBllgMstrId);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        return (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getUsageContractInterface", queryParam);
    }

    private List<Map<String, Object>> getAccessoryContractInterface(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, prntBllgMstrId);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        return (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getAccessoryContractInterface", queryParam);
    }

    private List<Map<String, Object>> getActualCounterContractInterface(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, prntBllgMstrId);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        return (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getActualCounterContractInterface", queryParam);
    }

    private List<Map<String, Object>> getTierContractInterface(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, prntBllgMstrId);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        return (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getTierContractInterface", queryParam);
    }

//    private Map<String, Object> getContractInterfaceForTerminateSubContract(Map<String, Object> baseContrIntfc) {
    private List<Map<String, Object>> getContractInterfaceForTerminateSubContract(Map<String, Object> baseContrIntfc) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PK, (BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_ROSS_INTFC_CONTR_PK));
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);
        queryParam.put(DB_PARAM_SALES_DATE, this.glWkPmsg.slsDt.getValue());
        queryParam.put(DB_PARAM_CONTR_TRMN_FLG, ZYPConstant.FLG_OFF_N);

        List<Map<String, Object>> resultList = this.glSsmBatchClient.queryObjectList("getContractInterfaceForTerminateSubContract", queryParam);
//QC#15410 mod Start
//        if (resultList != null && resultList.size() > 0) {
//            return resultList.get(0);
//        } else {
//            return null;
//        }
        return resultList;
//QC#15410 mod End
    }

    private Map<String, Object> getContractInterfaceForCreateSubContract(Map<String, Object> baseContrIntfc) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PK, (BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_ROSS_INTFC_CONTR_PK));
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);
        queryParam.put(DB_PARAM_SALES_DATE, this.glWkPmsg.slsDt.getValue());
        // START 2017/10/18 M.Naito [QC#20246, MOD]
//        queryParam.put(DB_PARAM_CTAC_TP_CD, CTAC_TP.IB_CONTACT);
        // START 2018/01/15 U.Kim [QC#22920, MOD]
        // queryParam.put(DB_PARAM_CTAC_TP_CD, CTAC_TP.DELIVERY_INSTALL);
        queryParam.put(DB_PARAM_SPLY_IND_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(DB_PARAM_ACTV_FLG, ZYPConstant.FLG_ON_Y);
        // END 2018/01/15 U.Kim [QC#22920, MOD]
        // END 2017/10/18 M.Naito [QC#20246, MOD]
        queryParam.put(DB_PARAM_CTAC_PSN_ACTV_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(DB_PARAM_DEL_FLG, ZYPConstant.FLG_OFF_N);

        List<Map<String, Object>> resultList = this.glSsmBatchClient.queryObjectList("getContractInterfaceForCreateSubContract", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

//    private Map<String, Object> getContractInterfaceForUpdateSubContract(Map<String, Object> baseContrIntfc) {
    private List<Map<String, Object>> getContractInterfaceForUpdateSubContract(Map<String, Object> baseContrIntfc) {    //QC#15410 mod

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PK, (BigDecimal) getDBColumnValue(baseContrIntfc, DB_COLUMN_ROSS_INTFC_CONTR_PK));
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);
        queryParam.put(DB_PARAM_SALES_DATE, this.glWkPmsg.slsDt.getValue());
        queryParam.put(DB_PARAM_CONTR_TRMN_FLG, ZYPConstant.FLG_OFF_N);
        // START 2017/10/18 M.Naito [QC#20246, MOD]
//        queryParam.put(DB_PARAM_CTAC_TP_CD, CTAC_TP.IB_CONTACT);
        // START 2018/01/15 U.Kim [QC#22920, MOD]
        // queryParam.put(DB_PARAM_CTAC_TP_CD, CTAC_TP.DELIVERY_INSTALL);
        queryParam.put(DB_PARAM_SPLY_IND_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(DB_PARAM_ACTV_FLG, ZYPConstant.FLG_ON_Y);
        // END 2018/01/15 U.Kim [QC#22920, MOD]
        // END 2017/10/18 M.Naito [QC#20246, MOD]
        queryParam.put(DB_PARAM_CTAC_PSN_ACTV_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(DB_PARAM_DEL_FLG, ZYPConstant.FLG_OFF_N);

        List<Map<String, Object>> resultList = this.glSsmBatchClient.queryObjectList("getContractInterfaceForUpdateSubContract", queryParam);
      //QC#15410 mod Start
//        if (resultList != null && resultList.size() > 0) {
//            return resultList.get(0);
//        } else {
//            return null;
//        }
        return resultList;
      //QC#15410 mod Start
    }

    private List<Map<String, Object>> getUnprocessedContractInterface(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_PRNT_BLLG_MSTR_ID, prntBllgMstrId);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        return (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getUnprocessedContractInterface", queryParam);
    }

    private Map<String, Object> getPreProcessedContractInterface(BigDecimal bllgMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, bllgMstrPk);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_PROCESSED);

        List<Map<String, Object>> resultList = this.glSsmBatchClient.queryObjectList("getPreProcessedContractInterface", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    private String getActionCode(BigDecimal prntBllgMstrId, String procModeCd) {

        if (ROSS_INTFC_PROC_MODE_CREATE.equals(procModeCd)) {
            //start 2017/11/11 [QC#22182, mod]
            //Map<String, Object> dsContr = getDSContract(prntBllgMstrId);
            //if (dsContr != null) {
            //    return DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT;
            //} else {
            //    return DS_CONTR_INTFC_ACT.CREATE;
            //}
            return DS_CONTR_INTFC_ACT.CREATE;
            //end 2017/11/11 [QC#22182, mod]
        } else if (ROSS_INTFC_PROC_MODE_TERMINATE_CREATE.equals(procModeCd)) {
            return null;
        } else if (ROSS_INTFC_PROC_MODE_UPDATE.equals(procModeCd)) {
            return DS_CONTR_INTFC_ACT.UPDATE;
        } else if (ROSS_INTFC_PROC_MODE_TERMINATE.equals(procModeCd)) {
            return DS_CONTR_INTFC_ACT.TERMINATE;
        } else if (ROSS_INTFC_PROC_MODE_SKIP.equals(procModeCd)) {
            return null;
        } else {
            return null;
        }
    }

    private Map<String, Object> getDSContract(BigDecimal prntBllgMstrId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_PRNT_BLLG_MSTR_ID, prntBllgMstrId.toPlainString());
        queryParam.put(DB_PARAM_DS_CONTR_SRC_TP_CD, DS_CONTR_SRC_TP.TPC05);
        queryParam.put(DB_PARAM_DS_CONTR_CTRL_STS_CD, new String[] {DS_CONTR_CTRL_STS_CD_TRMD, DS_CONTR_CTRL_STS_CD_EXPD, DS_CONTR_CTRL_STS_CD_CANC});

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getDSContract", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    private BigDecimal getNextBatchSqNum() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_SALES_DATE, this.glWkPmsg.slsDt.getValue());

        return (BigDecimal) this.glSsmBatchClient.queryObject("getNextBatchSqNum", queryParam);
    }

    private String createBatchNum(BigDecimal batchSqNum) {

        DecimalFormat df = new DecimalFormat("#");
        df.setMinimumIntegerDigits(5);
        df.setMaximumIntegerDigits(5);
        return ZYPCommonFunc.concatString(this.glWkPmsg.slsDt.getValue(), "R", df.format(batchSqNum));
    }

    private Map<String, Object> getDefaultingRule() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_CONTR_INTFC_SRC_TP_CD, CONTR_INTFC_SRC_TP.CUSA);
        queryParam.put(DB_PARAM_SALES_DATE, this.glWkPmsg.slsDt.getValue());
        queryParam.put(DB_PARAM_ENBL_FLG, ZYPConstant.FLG_ON_Y);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getDefaultingRule", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    private Map<String, Object> getCustomer(BigDecimal bllgMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, bllgMstrPk);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);
        queryParam.put(DB_PARAM_SALES_DATE, this.glWkPmsg.slsDt.getValue());

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getCustomer", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    private Map<String, Object> getBranchRep(BigDecimal bllgMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, bllgMstrPk);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getBranchRep", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    private Map<String, Object> getServiceProgram(BigDecimal bllgMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, bllgMstrPk);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getServiceProgram", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    private Map<String, Object> getMeterReadMethod(BigDecimal bllgMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, bllgMstrPk);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getMeterReadMethod", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    private Map<String, Object> getFedexDealer(BigDecimal bllgMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, bllgMstrPk);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
        queryParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);
        queryParam.put(DB_PARAM_GLBL_CMPY_CD_ABR, this.glCusaGlblCmpyCd);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getFedexDealer", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    private Map<String, Object> getBllgCycle(String bllgCycleCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_BLLG_CYCLE_CD, bllgCycleCd);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getBllgCycle", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    private void getMtrLb() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_MTR_LB_CD, new String[] {this.glBllgMtrLbCdSm, this.glBllgMtrLbCdBw, this.glBllgMtrLbCdClr});

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getMtrLb", queryParam);

        this.glMtrLbNm = new HashMap<String, String>();
        this.glDefaultMeterCount = new HashMap<String, BigDecimal>();
        this.glIntgMdseCd = new HashMap<String, String>();
        this.glBllgMtrLvlNum = new HashMap<String, String>();
        for (Map<String, Object> record : resultList) {
            this.glMtrLbNm.put((String) record.get(DB_COLUMN_MTR_LB_CD), (String) record.get(DB_COLUMN_MTR_LB_NM));
            this.glDefaultMeterCount.put((String) record.get(DB_COLUMN_MTR_LB_CD), (BigDecimal) record.get(DB_COLUMN_DEF_START_MTR_CNT));
            this.glIntgMdseCd.put((String) record.get(DB_COLUMN_MTR_LB_CD), (String) record.get(DB_COLUMN_INTG_MDSE_CD));
            this.glBllgMtrLvlNum.put((String) record.get(DB_COLUMN_MTR_LB_CD), (String) record.get(DB_COLUMN_BLLG_MTR_LVL_NUM));
        }
    }

    private Object getDBColumnValue(Map<String, Object> record, String columnName) {
        if (record != null) {
            return record.get(columnName);
        } else {
            return null;
        }
    }

    private boolean equalValue(String a, String b) {
        if (a == null) {
            return (b == null);
        } else {
            return a.equals(b);
        }
    }

    private boolean equalValue(BigDecimal a, BigDecimal b) {
        if (a == null) {
            return (b == null);
        } else {
            return (a.compareTo(b) == 0);
        }
    }

    // START 2017/02/20 K.Kitachi [QC#17604, ADD]
    private String convertMrcfTpCdToDsContrEdi(String mrcfTpCd) {
        if (!ZYPCommonFunc.hasValue(mrcfTpCd)) {
            return DS_CONTR_EDI.OTHER;
        }
        if (mrcfTpCd.equals(MRCF_TP.NOTHING)) {
            return DS_CONTR_EDI.CUSA;
        }
        if (mrcfTpCd.equals(MRCF_TP.CARD_FORMAT_TO_USER_LOCATION)) {
            return DS_CONTR_EDI.OTHER;
        }
        if (mrcfTpCd.equals(MRCF_TP.STANDARD_FORMAT_TO_USER_LOCATION)) {
            return DS_CONTR_EDI.OTHER;
        }
        if (mrcfTpCd.equals(MRCF_TP.METER_READING_REQUEST_TO_CUSTOMER_CONTACT)) {
            return DS_CONTR_EDI.OTHER;
        }
        if (mrcfTpCd.equals(MRCF_TP.STANDARD_FORMAT_TO_SERVICE_DEALER)) {
            return DS_CONTR_EDI.CUSA;
        }
        if (mrcfTpCd.equals(MRCF_TP.WEB_METER_ENTRY_USER)) {
            return DS_CONTR_EDI.OTHER;
        }
        if (mrcfTpCd.equals(MRCF_TP.WEB_METER_ENTRY_DEALER)) {
            return DS_CONTR_EDI.CUSA;
        }
        if (mrcfTpCd.equals(MRCF_TP.E_MAINTENANCE_OR_IMAGEWARE_REMOTE)) {
            return DS_CONTR_EDI.OTHER;
        }
        return null;
    }
    // END 2017/02/20 K.Kitachi [QC#17604, ADD]

    // START 2017/03/06 K.Ochiai [QC#17747, ADD]
    private String getBllgTmgTpCd(String ifBllgTmgTpCd) {

        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glWkPmsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsCondConstGrpId01", DB_PARAM_DS_COND_CONST_GRP_ID);
        inMsg.setConditionValue("dsCondConstCd01", ifBllgTmgTpCd);
        DS_COND_CONSTTMsgArray outArray = (DS_COND_CONSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0).dsCondConstValTxt_01.getValue();
    }

    // mod start 2017/08/18 QC#18799
    private DS_CONTR_INTFC_DEF_RULETMsg getDsContrIntfcDefRule(String dsContrClsCd) {
//        DS_CONTR_INTFC_DEF_RULETMsg msg = new DS_CONTR_INTFC_DEF_RULETMsg();
//        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, this.glWkPmsg.glblCmpyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(msg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.CUSA);
//
//        return (DS_CONTR_INTFC_DEF_RULETMsg) S21CacheTBLAccessor.findByKey(msg);

        DS_CONTR_INTFC_DEF_RULETMsg inMsg = new DS_CONTR_INTFC_DEF_RULETMsg();
        inMsg.setSQLID("202");
        inMsg.setConditionValue("glblCmpyCd01", this.glWkPmsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("contrIntfcSrcTpCd01", CONTR_INTFC_SRC_TP.CUSA);
        inMsg.setConditionValue("dsContrClsCd01", dsContrClsCd);
        inMsg.setConditionValue("svcLineBizCd01", SVC_LINE_BIZ_CD_ESS);
        inMsg.setConditionValue("effFromDt01", this.glWkPmsg.slsDt.getValue());
        inMsg.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);
        DS_CONTR_INTFC_DEF_RULETMsgArray outArray = (DS_CONTR_INTFC_DEF_RULETMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return (DS_CONTR_INTFC_DEF_RULETMsg) outArray.get(0);
    }
    // mod end 2017/08/18 QC#18799
    // END 2017/03/06 K.Ochiai [QC#17747, ADD]

    // add start 2017/08/18 QC#18799
    private String getInvSeptBaseUsgFlg(DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg, String dsBillTgtrFlg) {
        if (dsContrIntfcDefRuleTMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(dsContrIntfcDefRuleTMsg.invSeptBaseUsgMstrFlg.getValue())) {
                if (ZYPCommonFunc.hasValue(dsBillTgtrFlg)) {
                    // mod start 2017/09/01 QC#20882
                    if (ZYPConstant.FLG_ON_Y.equals(dsBillTgtrFlg)) {
                        return ZYPConstant.FLG_OFF_N;
                    } else {
                        return ZYPConstant.FLG_ON_Y;
                    }
                    // mod end 2017/09/01 QC#20882
                }
            } else {
                return dsContrIntfcDefRuleTMsg.invSeptBaseUsgFlg.getValue();
            }
        }
        return ZYPConstant.FLG_OFF_N;
    }
    // add end 2017/08/18 QC#18799

    // Add Start 2017/12/18 QC#18362
    private String getContrAdminPsnCd(DS_CONTR_INTFCTMsg inMsg, Map<String, Object> branchRep) {
        String contrAdminPsnCd = (String) getDBColumnValue(branchRep, DB_COLUMN_CONTR_ADMIN_PSN_CD);

        String acctContrAdminPsnCd = getAcctContrRep(inMsg);
        if (ZYPCommonFunc.hasValue(acctContrAdminPsnCd)) {
            contrAdminPsnCd = acctContrAdminPsnCd;
        }
        return contrAdminPsnCd;
    }

    private String getAcctContrRep(DS_CONTR_INTFCTMsg inMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, inMsg.glblCmpyCd.getValue());
        queryParam.put(DB_PARAM_SVC_LINE_BIZ_CD, inMsg.svcLineBizCd.getValue());
        queryParam.put(DB_PARAM_DS_ACCT_NUM, inMsg.dsAcctNum.getValue());
        queryParam.put(DB_PARAM_EFF_FROM_DT, inMsg.contrFromDt.getValue());
        return (String) this.glSsmBatchClient.queryObject("getAcctContrRep", queryParam);
    }
    // Add End 2017/12/18 QC#18362
}
