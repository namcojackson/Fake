/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB230001;

import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.DATA_SRC_CD_EMP;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.DATA_SRC_CD_NON_EMP;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.DEFAULT_COMMIT_UNIT;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.EMPL_CNSLT_TP_EMP;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.HR_PSN_INTFC_EMP_STS_CD_ACTIVE;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.HR_PSN_INTFC_EMP_STS_CD_TERMINATE;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.MAIL_BOX_EMP_ID_PREFIX;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.MAX_EMP_ID_LEN;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.MMAM0003E;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.MMAM0004E;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.MMAM0005E;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.NMAL2300_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.REHIRE;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.TECH_NM_MAX_LENGTH;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.TERMINATE;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.UPDATE;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.ADB_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.BIZ_AREA_ORG_CD_SALES;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.GRACE_PERIOD;
import static com.canon.cusa.s21.batch.NMA.NMAB230001.Constant.NMAB230001Constant.INITIAL_GRACE_PERIOD;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDTDateItem;
import parts.common.EZDTMsg;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import parts.dbcommon.EZDTBLAccessorForBatch;
import business.db.DS_ORG_RESRC_RELNTMsg;
import business.db.DS_ORG_RESRC_REVTMsg;
import business.db.DS_ORG_RESRC_REVTMsgArray;
import business.db.HR_PSN_INTFCTMsg;
import business.db.NMAI1800_01TMsg;
import business.db.ORG_FUNC_ASGTMsg;
import business.db.ORG_TOC_CHNG_RQSTTMsg;
import business.db.ORG_TOC_CHNG_RQSTTMsgArray;
import business.db.PTY_LOC_WRKTMsg;
import business.db.S21_PSNTMsg;
import business.db.TOC_ORG_STRU_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_XREF_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.REV_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * HR Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/15   Fujitsu         K.Minamide      Create          CSA
 * 2016/03/07   SRAA            Y.Chen          Update          QC#5075
 * 2016/03/31   Fujitsu         N.Sugiura       Update          QC#6325
 * 2016/04/11   Fujitsu         C.Yokoi         Update          QC#6297
 * 2016/03/07   SRAA            Y.Chen          Update          QC#1988
 * 2016/08/08   Fujitsu         C.Yokoi         Update          QC#13093
 * 2016/03/07   SRAA            Y.Chen          Update          QC#10364
 * 2016/08/29   Fujitsu         C.Yokoi         Update          QC#13093
 * 2016/09/09   SRAA            Y.Chen          Update          QC#13205
 * 2016/11/01   Fujitsu         C.Yokoi         Update          QC#14786
 * 2016/11/14   Fujitsu         C.Yokoi         Update          QC#15937
 * 2017/02/06   Fujitsu         M.Ohno          Update          QC#16336
 * 2017/08/07   Fujitsu         H.Ikeda         Update          QC#20223
 * 2017/10/13   Fujitsu         K.Ishizuka      Update          QC#20924
 * 2019/02/18   Fujitsu         Hd.Sugawara     Update          QC#29668
 * 2020/06/19   Fujitsu         M.Ohno          Update          QC#56181 
 * 2023/04/14   Hitachi         T.Usui          Update          QC#61266
 *</pre>
 */
public class NMAB230001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface Company Code */
    private String hrPsnIntfcCmpyCd = null;

    /** commitUnit */
    private int commitUnit = 0;

    /** sales date time */
    private String slsDt = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // QC#5075
    /** Interface ID */
    private String interfaceId = null;

    // QC#10364
    private Map<String, Object> cacheHrLocAddr = new HashMap<String, Object>(); 

    // QC#61266 2023/04/14 Add Start
    private BigDecimal gracePeriod = new BigDecimal(INITIAL_GRACE_PERIOD);
    // QC#61266 2023/04/14 Add End

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = super.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.hrPsnIntfcCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(NMAL2300_GLBL_CMPY_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.hrPsnIntfcCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"HR Person Interface Company Code" });
        }

        this.commitUnit = getCommitCount();
        if (this.commitUnit <= 0 || DEFAULT_COMMIT_UNIT < this.commitUnit) {
            this.commitUnit = DEFAULT_COMMIT_UNIT;
        }

        this.slsDt = ZYPDateUtil.getSalesDate();
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Sales Date" });
        }

        // QC#5075
        this.interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.interfaceId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Interface ID" });
        }

        // QC#61266 2023/04/14 Add Start
        BigDecimal getGracePeriod = ZYPCodeDataUtil.getNumConstValue(GRACE_PERIOD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(getGracePeriod)) {
            if (getGracePeriod.compareTo(BigDecimal.ZERO) >= 0) {
                this.gracePeriod = getGracePeriod;
            }
        }
        // QC#61266 2023/04/14 Add End

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        // QC#5075
        this.refreshInterfaceData();
        this.insertPeople();
        this.terminatePeople();
        this.updatePeople();
        this.rehirePeople();
    }

    // QC#5075
    private void refreshInterfaceData() {
        S21TransactionTableAccessor s21tta = new S21TransactionTableAccessor();
        BigDecimal[] transactionIdList = (BigDecimal[]) s21tta.getIntegrationRecordInitAsc(this.interfaceId);
        for (int idx = 0; idx < transactionIdList.length; idx++) {
            BigDecimal transactionId = transactionIdList[idx];
            S21InfoLogOutput.println("Process IF transacation ID: [" + transactionId + "]");

            if (idx == transactionIdList.length - 1) {

                removeHrPsnIntfc();
                super.commit();
                S21InfoLogOutput.println("HR_PSN_INTFC data removed.");

                createHrPsnIntfc(transactionId);
            }
            s21tta.endIntegrationProcess(this.interfaceId, transactionId);
        }
        super.commit();
    }

    private void createHrPsnIntfc(BigDecimal transactionId) {
        // QC#1988
        NMAI1800_01TMsg ifTMsgCond = new NMAI1800_01TMsg();
        ifTMsgCond.setSQLID("001");
        ifTMsgCond.setConditionValue("interfaceId01", this.interfaceId);
        ifTMsgCond.setConditionValue("transactionId01", transactionId);
        EZDTBLAccessorForBatch accessorIf = new EZDTBLAccessorForBatch(ifTMsgCond);

        try {
            List<HR_PSN_INTFCTMsg> insHrPnsIntfcList = new ArrayList<HR_PSN_INTFCTMsg>();
            NMAI1800_01TMsg ifTMsg = (NMAI1800_01TMsg) accessorIf.next();
            while (ifTMsg != null) {
                if (checkHrIf(ifTMsg)) {
                    HR_PSN_INTFCTMsg hrTMsg = new HR_PSN_INTFCTMsg();
                    setHrPsnIntfc(ifTMsg, hrTMsg);
                    insHrPnsIntfcList.add(hrTMsg);

                    if (insHrPnsIntfcList.size() >= this.commitUnit) {
                        commitHrPsnIntfc(insHrPnsIntfcList);
                        insHrPnsIntfcList.clear();
                    }
                }
                ifTMsg = (NMAI1800_01TMsg) accessorIf.next();
            }

            if (insHrPnsIntfcList.size() > 0) {
                commitHrPsnIntfc(insHrPnsIntfcList);
                insHrPnsIntfcList.clear();
            }
        } finally {
            accessorIf.releaseResource();
        }
    }

    private void commitHrPsnIntfc(List<HR_PSN_INTFCTMsg> insHrPnsIntfcList) {
        int cnt = S21FastTBLAccessor.insert(insHrPnsIntfcList.toArray(new HR_PSN_INTFCTMsg[] {}));
        if (cnt != insHrPnsIntfcList.size()) {
            S21InfoLogOutput.println("Fast Insert HR_PSN_INTFC failed. List Count: [" + insHrPnsIntfcList.size() + "], Return Count: [" + cnt + "]");
            super.rollback();
            throw new S21AbendException(MMAM0003E, new String[] {"HR_PSN_INTFC" });
        }
        super.commit();
        S21InfoLogOutput.println("Insert HR_PSN_INTFC commit. Count: [" + insHrPnsIntfcList.size() + "]");
    }

    private void removeHrPsnIntfc() {
        HR_PSN_INTFCTMsg hrTMsgCond = new HR_PSN_INTFCTMsg();
        hrTMsgCond.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        EZDTBLAccessor.removeByPartialKey(hrTMsgCond);
        EZDTBLAccessor.RTNCD_NORMAL.equals(hrTMsgCond.getReturnCode());
    }

    private boolean checkHrIf(NMAI1800_01TMsg ifTMsg) {
        if (!ZYPCommonFunc.hasValue(ifTMsg.hrEmpId)) {
            return false;
        }
        if (ifTMsg.hrEmpId.getValue().length() > MAX_EMP_ID_LEN) {
            return false;
        }
        return true;
    }

    private void setHrPsnIntfc(NMAI1800_01TMsg ifTMsg, HR_PSN_INTFCTMsg hrTMsg) {
        hrTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        setValue(hrTMsg, hrTMsg.hrPsnEeo4FuncTpCd, "hrPsnEeo4FuncTpCd", ifTMsg.hrEeo4FuncCd.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcCcNm, "hrPsnIntfcCcNm", ifTMsg.hrCostCtrNm.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcCellTelNum, "hrPsnIntfcCellTelNum", ifTMsg.hrCCellPhoNum.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcCmpyCd, "hrPsnIntfcCmpyCd", ifTMsg.hrCmpyNm.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcCoaAcctCd, "hrPsnIntfcCoaAcctCd", ifTMsg.hrCostCtrCd.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcDeptId, "hrPsnIntfcDeptId", ifTMsg.hrDeptId.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcDeptNm, "hrPsnIntfcDeptNm", ifTMsg.hrDeptNm.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcDivCd, "hrPsnIntfcDivCd", ifTMsg.hrDivCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcDivNm, "hrPsnIntfcDivNm", ifTMsg.hrDescDivNm.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcEmlAddr, "hrPsnIntfcEmlAddr", ifTMsg.hrEmailId.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcEmpId, "hrPsnIntfcEmpId", ifTMsg.hrEmpId.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcEmpStsCd, "hrPsnIntfcEmpStsCd", ifTMsg.hrEmpStsCd.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcExpatInd, "hrPsnIntfcExpatInd", ifTMsg.hrPayExpatIndCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcFaxNum, "hrPsnIntfcFaxNum", ifTMsg.hrEmpFaxNum.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcFcBldgCd, "hrPsnIntfcFcBldgCd", ifTMsg.hrCBldgCd.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcGrpCd, "hrPsnIntfcGrpCd", ifTMsg.hrGrpCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcGrpNm, "hrPsnIntfcGrpNm", ifTMsg.hrGrpCdDescTxt.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcHireDt, "hrPsnIntfcHireDt", ifTMsg.hrHireDtTxt.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcJobCd, "hrPsnIntfcJobCd", ifTMsg.hrJobCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcJobGrpTpCd, "hrPsnIntfcJobGrpTpCd", ifTMsg.hrJobFmyCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcJobTtlNm, "hrPsnIntfcJobTtlNm", ifTMsg.hrJobTtlNm.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcLocCd, "hrPsnIntfcLocCd", ifTMsg.hrLocNm.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcLocCtyNm, "hrPsnIntfcLocCtyNm", ifTMsg.hrLocCityNm.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcLocDescTxt, "hrPsnIntfcLocDescTxt", ifTMsg.hrLocDescTxt.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcLocStCd, "hrPsnIntfcLocStCd", ifTMsg.hrLocStNm.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcMgrLvlNum, "hrPsnIntfcMgrLvlNum", ifTMsg.hrMgrLvlCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcMgrLvlTxt, "hrPsnIntfcMgrLvlTxt", ifTMsg.hrMgrLvlDescTxt.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcPsnFirstNm, "hrPsnIntfcPsnFirstNm", ifTMsg.hrFirstNm.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcPsnLastNm, "hrPsnIntfcPsnLastNm", ifTMsg.hrLastNm.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcPsnMidNm, "hrPsnIntfcPsnMidNm", ifTMsg.hrMidNm.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcPsnNm, "hrPsnIntfcPsnNm", ifTMsg.hrFullNm.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcRegTempTpCd, "hrPsnIntfcRegTempTpCd", ifTMsg.hrRegTempTpCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcRgEstbId, "hrPsnIntfcRgEstbId", ifTMsg.hrEstbId.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcRgEstbTxt, "hrPsnIntfcRgEstbTxt", ifTMsg.hrEstbDescTxt.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcRunDt, "hrPsnIntfcRunDt", null);
        setValue(hrTMsg, hrTMsg.hrPsnIntfcSlsTpCd, "hrPsnIntfcSlsTpCd", ifTMsg.hrIsSlsCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcSsnLast4Num, "hrPsnIntfcSsnLast4Num", null);

        setValue(hrTMsg, hrTMsg.hrPsnIntfcSubDivCd, "hrPsnIntfcSubDivCd", ifTMsg.hrSubDivCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcSubDivNm, "hrPsnIntfcSubDivNm", ifTMsg.hrCSubDivDescTxt.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcSubGrpCd, "hrPsnIntfcSubGrpCd", ifTMsg.hrSubGrpCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcSubGrpNm, "hrPsnIntfcSubGrpNm", ifTMsg.hrCSubGrpDescTxt.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcSupvEmlAddr, "hrPsnIntfcSupvEmlAddr", ifTMsg.hrMgrEmailAddr.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcSupvId, "hrPsnIntfcSupvId", ifTMsg.hrMgrId.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnIntfcSupvNm, "hrPsnIntfcSupvNm", ifTMsg.hrMgrFullNm.getValue());

        setValue(hrTMsg, hrTMsg.hrPsnIntfcWrkTmTpCd, "hrPsnIntfcWrkTmTpCd", ifTMsg.hrFullPartTmTxt.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnWrkTelNum, "hrPsnWrkTelNum", ifTMsg.hrEmpTelNum.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnWrkTelExtNum, "hrPsnWrkTelExtNum", ifTMsg.hrEmpTelExtNum.getValue());

        // QC#1988
        setValue(hrTMsg, hrTMsg.hrPsnArcsCoaCmpyCd, "hrPsnArcsCoaCmpyCd", ifTMsg.hrArcsCoaCmpyCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnArcsCoaExtnCd, "hrPsnArcsCoaExtnCd", ifTMsg.hrArcsCoaExtnCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnArcsCoaBrCd, "hrPsnArcsCoaBrCd", ifTMsg.hrArcsCoaBrCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnArcsCoaCcCd, "hrPsnArcsCoaCcCd", ifTMsg.hrArcsCoaCcCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnArcsCoaChCd, "hrPsnArcsCoaChCd", ifTMsg.hrArcsCoaChCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnArcsCoaAfflCd, "hrPsnArcsCoaAfflCd", ifTMsg.hrArcsCoaAfflCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnArcsCoaProdCd, "hrPsnArcsCoaProdCd", ifTMsg.hrArcsCoaProdCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnArcsCoaAcctCd, "hrPsnArcsCoaAcctCd", ifTMsg.hrArcsCoaAcctCd.getValue());
        setValue(hrTMsg, hrTMsg.hrPsnArcsCoaProjCd, "hrPsnArcsCoaProjCd", ifTMsg.hrArcsCoaProjCd.getValue());

        // hrPsnEmpCnsltTpCd
        if (EMPL_CNSLT_TP_EMP.equals(ifTMsg.hrUsrTpCd.getValue())) {
            hrTMsg.hrPsnEmpCnsltTpCd.setValue(EMPL_CNSLT_TP_EMP);
        } else {
            hrTMsg.hrPsnEmpCnsltTpCd.clear();
        }

        // hrPsnIntfcCmpyNm
        if (EMPL_CNSLT_TP_EMP.equals(ifTMsg.hrUsrTpCd.getValue())) {
            setValue(hrTMsg, hrTMsg.hrPsnIntfcCmpyNm, "hrPsnIntfcCmpyNm", ifTMsg.hrCmpyDescTxt.getValue());
        } else {
            setValue(hrTMsg, hrTMsg.hrPsnIntfcCmpyNm, "hrPsnIntfcCmpyNm", ifTMsg.hrCnsltFirmTxt.getValue());
        }

        // hrPsnIntfcDataSrcCd
        if (EMPL_CNSLT_TP_EMP.equals(ifTMsg.hrUsrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(hrTMsg.hrPsnIntfcDataSrcCd, DATA_SRC_CD_EMP);
        } else {
            ZYPEZDItemValueSetter.setValue(hrTMsg.hrPsnIntfcDataSrcCd, DATA_SRC_CD_NON_EMP);
        }

        // hrPsnIntfcStsDescTxt
        if (!ZYPCommonFunc.hasValue(ifTMsg.hrEmpStsDescTxt)) {
            if (HR_PSN_INTFC_EMP_STS_CD_ACTIVE.equals(ifTMsg.hrEmpStsCd.getValue())) {
                setValue(hrTMsg, hrTMsg.hrPsnIntfcStsDescTxt, "hrPsnIntfcStsDescTxt", "Active");

            } else if (HR_PSN_INTFC_EMP_STS_CD_TERMINATE.equals(ifTMsg.hrEmpStsCd.getValue())) {
                setValue(hrTMsg, hrTMsg.hrPsnIntfcStsDescTxt, "hrPsnIntfcStsDescTxt", "Terminated");
            }
        } else {
            setValue(hrTMsg, hrTMsg.hrPsnIntfcStsDescTxt, "hrPsnIntfcStsDescTxt", ifTMsg.hrEmpStsDescTxt.getValue());
        }

        // hrPsnIntfcWrkEndDt
        if (HR_PSN_INTFC_EMP_STS_CD_TERMINATE.equals(ifTMsg.hrEmpStsCd.getValue())) {
            if (EMPL_CNSLT_TP_EMP.equals(ifTMsg.hrUsrTpCd.getValue())) {
                setValue(hrTMsg, hrTMsg.hrPsnIntfcWrkEndDt, "hrPsnIntfcWrkEndDt", ifTMsg.hrLastWrkDtTxt.getValue());
            } else {
                setValue(hrTMsg, hrTMsg.hrPsnIntfcWrkEndDt, "hrPsnIntfcWrkEndDt", ifTMsg.hrUpdDtTxt.getValue());
            }
        } else {
            hrTMsg.hrPsnIntfcWrkEndDt.clear();
        }
    }

    private void setValue(EZDTMsg hrTmsg, EZDTStringItem item, String itemName, String value) {
        if (!ZYPCommonFunc.hasValue(value)) {
            item.clear();
        } else {
            int maxLength = hrTmsg.getAttr(itemName).getDigit();
            item.setValue(ZYPCommonFunc.subByteString(value, maxLength));
        }
    }

    private void setValue(HR_PSN_INTFCTMsg hrTmsg, EZDTDateItem item, String itemName, String value) {
        if (!ZYPCommonFunc.hasValue(value)) {
            item.clear();
        } else {
            int maxLength = hrTmsg.getAttr(itemName).getDigit();
            if (value.length() > maxLength) {
                item.clear();
            } else {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                fmt.setLenient(false);
                try {
                    fmt.parse(value);
                    if (ZYPDateUtil.checkDate(value)) {
                        item.setValue(value);
                    }
                } catch (ParseException e) {
                    item.clear();
                }
            }
        }
    }

    /**
     * 
     */
    private void insertPeople() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("hrPsnIntfcCmpyCd", this.hrPsnIntfcCmpyCd);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("hrPsnIntfcEmpStsCd", HR_PSN_INTFC_EMP_STS_CD_ACTIVE);
        // QC#1988
        ssmParam.put("mailBoxEmpIdPrefix", MAIL_BOX_EMP_ID_PREFIX);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getPersonForInsert", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            List<S21_PSNTMsg> insS21PsnList = new ArrayList<S21_PSNTMsg>(this.commitUnit);
            List<DS_ORG_RESRC_REVTMsg> insDsOrgResrcRevList = new ArrayList<DS_ORG_RESRC_REVTMsg>(this.commitUnit);

            while (rsSet.next()) {

                this.totalCount++;

                HR_PSN_INTFCTMsg hrPsnIntfc = this.makeHrPsnIntfc(rsSet);

                S21_PSNTMsg s21Psn = this.makeS21PsnForInsert(hrPsnIntfc);

                if (s21Psn != null) {
                    insS21PsnList.add(s21Psn);
                }

                DS_ORG_RESRC_REVTMsg dsOrgResrcRev = this.makeDsOrgResrcRevForInsert(hrPsnIntfc);

                if (dsOrgResrcRev != null) {
                    insDsOrgResrcRevList.add(dsOrgResrcRev);
                }

                if (insS21PsnList.size() >= this.commitUnit) {

                    int commitCount = insS21PsnList.size();
                    this.executeBulkInsS21Psn(insS21PsnList);
                    if (insDsOrgResrcRevList.size() > 0) {
                        this.executeBulkInsDsOrgResrcRev(insDsOrgResrcRevList);
                    }
                    insS21PsnList.clear();
                    insDsOrgResrcRevList.clear();
                    super.commit();
                    this.totalNmlCount += commitCount;
                }
            }

            if (insS21PsnList.size() > 0 || insDsOrgResrcRevList.size() > 0) {
                int commitCount = insS21PsnList.size();
                this.executeBulkInsS21Psn(insS21PsnList);
                this.executeBulkInsDsOrgResrcRev(insDsOrgResrcRevList);
                insS21PsnList.clear();
                insDsOrgResrcRevList.clear();
                super.commit();
                this.totalNmlCount += commitCount;
            }

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);

        }
    }

    /**
     * @param rsSet
     * @return
     * @throws SQLException
     */
    private HR_PSN_INTFCTMsg makeHrPsnIntfc(ResultSet rsSet) throws SQLException {
        HR_PSN_INTFCTMsg hrPsnIntfc = new HR_PSN_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.glblCmpyCd, rsSet.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcEmpId, rsSet.getString("HR_PSN_INTFC_EMP_ID"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcPsnNm, rsSet.getString("HR_PSN_INTFC_PSN_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnEmpCnsltTpCd, rsSet.getString("HR_PSN_EMP_CNSLT_TP_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcEmpStsCd, rsSet.getString("HR_PSN_INTFC_EMP_STS_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcStsDescTxt, rsSet.getString("HR_PSN_INTFC_STS_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcCmpyCd, rsSet.getString("HR_PSN_INTFC_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcCmpyNm, rsSet.getString("HR_PSN_INTFC_CMPY_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcDivCd, rsSet.getString("HR_PSN_INTFC_DIV_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcDivNm, rsSet.getString("HR_PSN_INTFC_DIV_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcGrpCd, rsSet.getString("HR_PSN_INTFC_GRP_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcGrpNm, rsSet.getString("HR_PSN_INTFC_GRP_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcJobCd, rsSet.getString("HR_PSN_INTFC_JOB_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcJobTtlNm, rsSet.getString("HR_PSN_INTFC_JOB_TTL_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcRgEstbId, rsSet.getString("HR_PSN_INTFC_RG_ESTB_ID"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnWrkTelNum, rsSet.getString("HR_PSN_WRK_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcEmlAddr, rsSet.getString("HR_PSN_INTFC_EML_ADDR"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcMgrLvlNum, rsSet.getString("HR_PSN_INTFC_MGR_LVL_NUM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcRegTempTpCd, rsSet.getString("HR_PSN_INTFC_REG_TEMP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcDeptId, rsSet.getString("HR_PSN_INTFC_DEPT_ID"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcDeptNm, rsSet.getString("HR_PSN_INTFC_DEPT_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcCoaAcctCd, rsSet.getString("HR_PSN_INTFC_COA_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcCcNm, rsSet.getString("HR_PSN_INTFC_CC_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcSupvId, rsSet.getString("HR_PSN_INTFC_SUPV_ID"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcSupvNm, rsSet.getString("HR_PSN_INTFC_SUPV_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcSlsTpCd, rsSet.getString("HR_PSN_INTFC_SLS_TP_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcSupvEmlAddr, rsSet.getString("HR_PSN_INTFC_SUPV_EML_ADDR"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcPsnFirstNm, rsSet.getString("HR_PSN_INTFC_PSN_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcPsnLastNm, rsSet.getString("HR_PSN_INTFC_PSN_LAST_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcRunDt, rsSet.getString("HR_PSN_INTFC_RUN_DT"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnWrkTelExtNum, rsSet.getString("HR_PSN_WRK_TEL_EXT_NUM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcFaxNum, rsSet.getString("HR_PSN_INTFC_FAX_NUM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcCellTelNum, rsSet.getString("HR_PSN_INTFC_CELL_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcPsnMidNm, rsSet.getString("HR_PSN_INTFC_PSN_MID_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcMgrLvlTxt, rsSet.getString("HR_PSN_INTFC_MGR_LVL_TXT"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcHireDt, rsSet.getString("HR_PSN_INTFC_HIRE_DT"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcSsnLast4Num, rsSet.getString("HR_PSN_INTFC_SSN_LAST4_NUM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcRgEstbTxt, rsSet.getString("HR_PSN_INTFC_RG_ESTB_TXT"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcLocCd, rsSet.getString("HR_PSN_INTFC_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcLocDescTxt, rsSet.getString("HR_PSN_INTFC_LOC_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcExpatInd, rsSet.getString("HR_PSN_INTFC_EXPAT_IND"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnEeo4FuncTpCd, rsSet.getString("HR_PSN_EEO4_FUNC_TP_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcFcBldgCd, rsSet.getString("HR_PSN_INTFC_FC_BLDG_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcJobGrpTpCd, rsSet.getString("HR_PSN_INTFC_JOB_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcWrkTmTpCd, rsSet.getString("HR_PSN_INTFC_WRK_TM_TP_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcLocCtyNm, rsSet.getString("HR_PSN_INTFC_LOC_CTY_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcLocStCd, rsSet.getString("HR_PSN_INTFC_LOC_ST_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcSubDivCd, rsSet.getString("HR_PSN_INTFC_SUB_DIV_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcSubDivNm, rsSet.getString("HR_PSN_INTFC_SUB_DIV_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcSubGrpCd, rsSet.getString("HR_PSN_INTFC_SUB_GRP_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcSubGrpNm, rsSet.getString("HR_PSN_INTFC_SUB_GRP_NM"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcDataSrcCd, rsSet.getString("HR_PSN_INTFC_DATA_SRC_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnIntfcWrkEndDt, rsSet.getString("HR_PSN_INTFC_WRK_END_DT"));
        // QC#1988
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnArcsCoaCmpyCd, rsSet.getString("HR_PSN_ARCS_COA_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnArcsCoaExtnCd, rsSet.getString("HR_PSN_ARCS_COA_EXTN_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnArcsCoaBrCd, rsSet.getString("HR_PSN_ARCS_COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(hrPsnIntfc.hrPsnArcsCoaCcCd, rsSet.getString("HR_PSN_ARCS_COA_CC_CD"));
        return hrPsnIntfc;
    }

    /**
     * @param hrPsnIntfc
     * @return
     */
    private S21_PSNTMsg makeS21PsnForInsert(HR_PSN_INTFCTMsg hrPsnIntfc) {
        S21_PSNTMsg s21Psn = new S21_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(s21Psn.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(s21Psn.psnCd, hrPsnIntfc.hrPsnIntfcEmpId);

        if (S21FastTBLAccessor.findByKey(s21Psn) != null) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(s21Psn.psnFirstNm, hrPsnIntfc.hrPsnIntfcPsnFirstNm);
        ZYPEZDItemValueSetter.setValue(s21Psn.psnMidNm, hrPsnIntfc.hrPsnIntfcPsnMidNm);
        ZYPEZDItemValueSetter.setValue(s21Psn.psnLastNm, hrPsnIntfc.hrPsnIntfcPsnLastNm);
        ZYPEZDItemValueSetter.setValue(s21Psn.hrTtlNm, hrPsnIntfc.hrPsnIntfcJobTtlNm);
        ZYPEZDItemValueSetter.setValue(s21Psn.psnTpCd, PSN_TP.EMPLOYEE);
        ZYPEZDItemValueSetter.setValue(s21Psn.emlAddr, hrPsnIntfc.hrPsnIntfcEmlAddr);
        // Add Start 2017/01/10 M.Ohno S21_NA#16336
        if (ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnIntfcHireDt)) {
            ZYPEZDItemValueSetter.setValue(s21Psn.effFromDt, hrPsnIntfc.hrPsnIntfcHireDt);
        } else {
            ZYPEZDItemValueSetter.setValue(s21Psn.effFromDt, ZYPDateUtil.addDays(this.slsDt, 1));
        }
        // Add End   2017/01/10 M.Ohno S21_NA#16336
        // ZYPEZDItemValueSetter.setValue(s21Psn.effFromDt, ZYPDateUtil.addDays(this.slsDt, 1)); // Del 2017/01/10 M.Ohno S21_NA#16336
        ZYPEZDItemValueSetter.setValue(s21Psn.effThruDt, hrPsnIntfc.hrPsnIntfcWrkEndDt);
        ZYPEZDItemValueSetter.setValue(s21Psn.delFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(s21Psn.hrCoaAcctCd, hrPsnIntfc.hrPsnIntfcCoaAcctCd);
        ZYPEZDItemValueSetter.setValue(s21Psn.faxNum, hrPsnIntfc.hrPsnIntfcFaxNum);
        ZYPEZDItemValueSetter.setValue(s21Psn.cellTelNum, hrPsnIntfc.hrPsnIntfcCellTelNum);
        ZYPEZDItemValueSetter.setValue(s21Psn.workTelNum, hrPsnIntfc.hrPsnWrkTelNum);
        ZYPEZDItemValueSetter.setValue(s21Psn.ofcTelNum, hrPsnIntfc.hrPsnWrkTelNum);
        ZYPEZDItemValueSetter.setValue(s21Psn.psnNum, hrPsnIntfc.hrPsnIntfcEmpId);
        ZYPEZDItemValueSetter.setValue(s21Psn.hrSupvId, hrPsnIntfc.hrPsnIntfcSupvId);
        ZYPEZDItemValueSetter.setValue(s21Psn.hrSupvNm, hrPsnIntfc.hrPsnIntfcSupvNm);
        ZYPEZDItemValueSetter.setValue(s21Psn.hrPsnCmpyCd, hrPsnIntfc.hrPsnIntfcCmpyCd);
        ZYPEZDItemValueSetter.setValue(s21Psn.hrPsnCmpyNm, hrPsnIntfc.hrPsnIntfcCmpyNm);
        ZYPEZDItemValueSetter.setValue(s21Psn.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);

        // QC#10364
        String hrLocCd = hrPsnIntfc.hrPsnIntfcLocCd.getValue();
        if (ZYPCommonFunc.hasValue(hrLocCd)) {
            PTY_LOC_WRKTMsg ptyLocWrkTMsg = getAdressByHrLocation(hrLocCd);
            if (ptyLocWrkTMsg != null) {
                setS21PersonAddress(s21Psn, ptyLocWrkTMsg, hrLocCd);
            }
        }

        return s21Psn;
    }

    /**
     * @param hrPsnIntfc
     */
    private DS_ORG_RESRC_REVTMsg makeDsOrgResrcRevForInsert(HR_PSN_INTFCTMsg hrPsnIntfc) {

        DS_ORG_RESRC_REVTMsg dsOrgResrcRev = new DS_ORG_RESRC_REVTMsg();

        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.psnCd, hrPsnIntfc.hrPsnIntfcEmpId);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.revAcctTpCd, REV_ACCT_TP.REVENUE);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.effFromDt, ZYPDateUtil.addDays(this.slsDt, 1));

        if (S21FastTBLAccessor.findByKey(dsOrgResrcRev) != null) {
            return null;
        }

// QC#1988
//        String hrPsnIntfcCoaAcctCd = hrPsnIntfc.hrPsnIntfcCoaAcctCd.getValue();
//        int strlen = hrPsnIntfcCoaAcctCd.length();
//
//        if (strlen > COA_CMPY_CD_INDEX) {
//            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.coaCmpyCd, hrPsnIntfcCoaAcctCd.substring(COA_CMPY_CD_INDEX, Math.min(COA_EXTN_CD_INDEX, hrPsnIntfcCoaAcctCd.length() - 1)));
//        }
//
//        if (strlen > COA_EXTN_CD_INDEX) {
//            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.coaExtnCd, hrPsnIntfcCoaAcctCd.substring(COA_EXTN_CD_INDEX, Math.min(COA_BR_CD_INDEX, hrPsnIntfcCoaAcctCd.length() - 1)));
//        }
//
//        if (strlen > COA_BR_CD_INDEX) {
//            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.coaBrCd, hrPsnIntfcCoaAcctCd.substring(COA_BR_CD_INDEX, Math.min(COA_CC_CD_INDEX, hrPsnIntfcCoaAcctCd.length() - 1)));
//        }
//
//        if (strlen > COA_CC_CD_INDEX) {
//            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.coaCcCd, hrPsnIntfcCoaAcctCd.substring(COA_CC_CD_INDEX));
//        }

        if(ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaCmpyCd) 
                && ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaExtnCd)
                && ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaBrCd)
                && ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaCcCd)
                ){
            setValue(dsOrgResrcRev, dsOrgResrcRev.coaCmpyCd, "coaCmpyCd", hrPsnIntfc.hrPsnArcsCoaCmpyCd.getValue());
            setValue(dsOrgResrcRev, dsOrgResrcRev.coaExtnCd, "coaExtnCd", hrPsnIntfc.hrPsnArcsCoaExtnCd.getValue());
            setValue(dsOrgResrcRev, dsOrgResrcRev.coaBrCd, "coaBrCd", hrPsnIntfc.hrPsnArcsCoaBrCd.getValue());
            setValue(dsOrgResrcRev, dsOrgResrcRev.coaCcCd, "coaCcCd", hrPsnIntfc.hrPsnArcsCoaCcCd.getValue());
            // 2017/08/09 CSA-QC#20223 Add Start
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.coaCmpyCd, ADB_GLBL_CMPY_CD);
            // 2017/08/08 CSA-QC#20223 Add End
        }

        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.effThruDt, hrPsnIntfc.hrPsnIntfcWrkEndDt);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.manEntryFlg, ZYPConstant.FLG_OFF_N);
        // Add Start 2019/02/18 QC#29668
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.tocRgtnReqFlg, ZYPConstant.FLG_ON_Y);
        // Add End 2019/02/18 QC#29668

        return dsOrgResrcRev;
    }

    /**
     * execute BulkInsert S21_PSN
     * @param insDataList
     */
    private void executeBulkInsS21Psn(List<S21_PSNTMsg> insDataList) {
        // BulkInsert
        int insCount = S21FastTBLAccessor.insert(insDataList.toArray(new S21_PSNTMsg[insDataList.size()]));
        if (insCount != insDataList.size()) {
            this.totalErrCount += insDataList.size() - insCount;
            super.rollback();
            throw new S21AbendException(MMAM0003E, new String[] {"S21_PSN" });
        }
    }

    /**
     * @param insDsOrgResrcRevList
     */
    private void executeBulkInsDsOrgResrcRev(List<DS_ORG_RESRC_REVTMsg> insDataList) {
        // BulkInsert
        int insCount = S21FastTBLAccessor.insert(insDataList.toArray(new DS_ORG_RESRC_REVTMsg[insDataList.size()]));
        if (insCount != insDataList.size()) {
            super.rollback();
            throw new S21AbendException(MMAM0003E, new String[] {"DS_ORG_RESRC_REV" });
        }
    }

    /**
     * 
     */
    private void terminatePeople() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("hrPsnIntfcCmpyCd", this.hrPsnIntfcCmpyCd);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("hrPsnIntfcEmpStsCd", HR_PSN_INTFC_EMP_STS_CD_TERMINATE);
        // QC#1988
        ssmParam.put("mailBoxEmpIdPrefix", MAIL_BOX_EMP_ID_PREFIX);
        // QC#61266 2023/04/14 Add Start
        ssmParam.put("bizAreaOrgCd", BIZ_AREA_ORG_CD_SALES);
        ssmParam.put("slsDt", this.slsDt);
        ssmParam.put("gracePeriod", this.gracePeriod);
        // QC#61266 2023/04/14 Add End

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getPersonForUpDateOrTerminate", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            List<S21_PSNTMsg> terminateS21PsnList = new ArrayList<S21_PSNTMsg>(this.commitUnit);
            List<DS_ORG_RESRC_REVTMsg> terminateDsOrgResrcRevList = new ArrayList<DS_ORG_RESRC_REVTMsg>(this.commitUnit);
            List<ORG_FUNC_ASGTMsg> terminateOrgFuncAsgList = new ArrayList<ORG_FUNC_ASGTMsg>(this.commitUnit);
            List<DS_ORG_RESRC_RELNTMsg> terminateDsOrgResrcRelnList = new ArrayList<DS_ORG_RESRC_RELNTMsg>(this.commitUnit);
            List<TOC_ORG_STRU_RELNTMsg> terminateTocOrgStruRelnList = new ArrayList<TOC_ORG_STRU_RELNTMsg>(this.commitUnit);
            // 2016/08/08 CSA-QC#13093 Mod Start
            List<ORG_TOC_CHNG_RQSTTMsg> terminateOrgTocChngRqstList = new ArrayList<ORG_TOC_CHNG_RQSTTMsg>(this.commitUnit);
            // List<TECH_MSTRTMsg> terminateTechMstrList = new ArrayList<TECH_MSTRTMsg>(this.commitUnit);
            // 2016/08/08 CSA-QC#13093 Mod End
            // S21_NA ADD START QC#20924
            List<ORG_FUNC_ASGTMsg> removeOrgFuncAsgList = new ArrayList<ORG_FUNC_ASGTMsg>(this.commitUnit);
            List<TOC_ORG_STRU_RELNTMsg> removeTocOrgStruRelnList = new ArrayList<TOC_ORG_STRU_RELNTMsg>(this.commitUnit);
            List<DS_ORG_RESRC_RELNTMsg> removeDsOrgResrcRelnList = new ArrayList<DS_ORG_RESRC_RELNTMsg>(this.commitUnit);
            List<DS_ORG_RESRC_REVTMsg> removeDsOrgResrcRevList = new ArrayList<DS_ORG_RESRC_REVTMsg>(this.commitUnit);
            // S21_NA ADD END QC#20924

            while (rsSet.next()) {

                HR_PSN_INTFCTMsg hrPsnIntfc = this.makeHrPsnIntfc(rsSet);

                S21_PSNTMsg s21Psn = this.makeS21Psn(hrPsnIntfc, TERMINATE);
                if (s21Psn != null) {
                    terminateS21PsnList.add(s21Psn);
                    this.totalCount++;
                }

                List<DS_ORG_RESRC_REVTMsg> terminateDsOrgResrcRevListTemp = this.makeDsOrgResrcRevForTerminate(hrPsnIntfc, removeDsOrgResrcRevList, TERMINATE);
                if (terminateDsOrgResrcRevListTemp.size() > 0) {
                    terminateDsOrgResrcRevList.addAll(terminateDsOrgResrcRevListTemp);
                }

                if (s21Psn != null) {
                    // MOD START 2016/03/31 QC#6325
                    // List<ORG_FUNC_ASGTMsg> terminateOrgFuncAsgListTemp = this.makeOrgFuncAsg(hrPsnIntfc, s21Psn);
                    List<ORG_FUNC_ASGTMsg> terminateOrgFuncAsgListTemp = this.makeOrgFuncAsg(hrPsnIntfc, s21Psn, removeOrgFuncAsgList, TERMINATE); // S21_NA MOD QC#20924
                    // MOD END 2016/03/31 QC#6325
                    if (terminateOrgFuncAsgListTemp.size() > 0) {
                        terminateOrgFuncAsgList.addAll(terminateOrgFuncAsgListTemp);
                        
                        //S21_NA ADD START QC#20924
                        if(removeOrgFuncAsgList.size() > 0){
                            terminateOrgFuncAsgListTemp.addAll(removeOrgFuncAsgList);
                        }
                        //S21_NA ADD START QC#20924
                        
                        Set<String> tocCdSet = this.makeTocCdSet(terminateOrgFuncAsgListTemp);

                        // 2016/08/08 CSA-QC#13093 Add Start
                        List<ORG_TOC_CHNG_RQSTTMsg> terminateOrgTocChngRqstListTemp = this.makeOrgTocChngRqst(hrPsnIntfc, tocCdSet, TERMINATE);
                        if (terminateOrgTocChngRqstListTemp.size() > 0) {
                            terminateOrgTocChngRqstList.addAll(terminateOrgTocChngRqstListTemp);
                        }
                        // 2016/08/08 CSA-QC#13093 Add Start

                        List<TOC_ORG_STRU_RELNTMsg> terminateTocOrgStruRelnListTemp = this.makeTocOrgStruReln(hrPsnIntfc, tocCdSet, removeTocOrgStruRelnList, TERMINATE);// S21_NA MOD QC#20924
                        if (terminateTocOrgStruRelnListTemp.size() > 0) {
                            terminateTocOrgStruRelnList.addAll(terminateTocOrgStruRelnListTemp);
                        }
                    }
                    // MOD START 2016/03/31 QC#6325
                    // List<DS_ORG_RESRC_RELNTMsg> terminateDsOrgResrcRelnListTemp = this.makeDsOrgResrcReln(hrPsnIntfc, s21Psn);
                    List<DS_ORG_RESRC_RELNTMsg> terminateDsOrgResrcRelnListTemp = this.makeDsOrgResrcReln(hrPsnIntfc, s21Psn, removeDsOrgResrcRelnList, TERMINATE); // S21_NA MOD QC#21949
                    // MOD END 2016/03/31 QC#6325
                    if (terminateDsOrgResrcRelnListTemp.size() > 0) {
                        terminateDsOrgResrcRelnList.addAll(terminateDsOrgResrcRelnListTemp);
                    }

                    // 2016/08/08 CSA-QC#13093 Delete Start
                    // TECH_MSTRTMsg techMstr = this.makeTechMstr(hrPsnIntfc, s21Psn);
                    // if (techMstr != null) {
                    //    terminateTechMstrList.add(techMstr);
                    // }
                    // 2016/08/08 CSA-QC#13093 Delete End
                }

                if (terminateS21PsnList.size() >= this.commitUnit) {
                    // 2016/08/08 CSA-QC#13093 Mod Start
                    // this.executeBulkUpd(TERMINATE, terminateS21PsnList, terminateDsOrgResrcRevList, terminateOrgFuncAsgList, terminateDsOrgResrcRelnList, terminateTocOrgStruRelnList, terminateTechMstrList);
                    this.executeBulkUpd(TERMINATE, terminateS21PsnList, terminateDsOrgResrcRevList, terminateOrgFuncAsgList, terminateDsOrgResrcRelnList, terminateTocOrgStruRelnList, terminateOrgTocChngRqstList);
                    // 2016/08/08 CSA-QC#13093 Mod End
                    //S21_NA ADD START QC#20924
                    this.executeBulkDel(removeOrgFuncAsgList, removeTocOrgStruRelnList, removeDsOrgResrcRelnList, removeDsOrgResrcRevList);
                    removeOrgFuncAsgList.clear();
                    removeTocOrgStruRelnList.clear();
                    removeDsOrgResrcRelnList.clear();
                    removeDsOrgResrcRevList.clear();
                    //S21_NA ADD END QC#20924
                    terminateS21PsnList.clear();
                    terminateDsOrgResrcRevList.clear();
                    terminateOrgFuncAsgList.clear();
                    terminateDsOrgResrcRelnList.clear();
                    terminateTocOrgStruRelnList.clear();
                    terminateOrgTocChngRqstList.clear();
                    // terminateTechMstrList.clear();
                    super.commit();
                }
            }

            // 2016/08/08 CSA-QC#13093 Mod Start
            // if (terminateS21PsnList.size() > 0 || terminateDsOrgResrcRevList.size() > 0 || terminateOrgFuncAsgList.size() > 0 || terminateDsOrgResrcRelnList.size() > 0 || terminateTocOrgStruRelnList.size() > 0
            //      || terminateTechMstrList.size() > 0) {
            //     this.executeBulkUpd(TERMINATE, terminateS21PsnList, terminateDsOrgResrcRevList, terminateOrgFuncAsgList, terminateDsOrgResrcRelnList, terminateTocOrgStruRelnList, terminateTechMstrList);
            if (terminateS21PsnList.size() > 0 || terminateDsOrgResrcRevList.size() > 0 || terminateOrgFuncAsgList.size() > 0 || terminateDsOrgResrcRelnList.size() > 0 || terminateTocOrgStruRelnList.size() > 0
                    || terminateOrgTocChngRqstList.size() > 0) {
                this.executeBulkUpd(TERMINATE, terminateS21PsnList, terminateDsOrgResrcRevList, terminateOrgFuncAsgList, terminateDsOrgResrcRelnList, terminateTocOrgStruRelnList, terminateOrgTocChngRqstList);
            // 2016/08/08 CSA-QC#13093 Mod End
                //S21_NA ADD START QC#20924
                this.executeBulkDel(removeOrgFuncAsgList, removeTocOrgStruRelnList, removeDsOrgResrcRelnList, removeDsOrgResrcRevList);
                removeOrgFuncAsgList.clear();
                removeTocOrgStruRelnList.clear();
                removeDsOrgResrcRelnList.clear();
                removeDsOrgResrcRevList.clear();
                //S21_NA ADD END QC#20924
                terminateS21PsnList.clear();
                terminateDsOrgResrcRevList.clear();
                terminateOrgFuncAsgList.clear();
                terminateDsOrgResrcRelnList.clear();
                terminateTocOrgStruRelnList.clear();
                terminateOrgTocChngRqstList.clear();
                // terminateTechMstrList.clear();
                super.commit();
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * @param orgFuncAsgListTemp
     * @return
     */
    private Set<String> makeTocCdSet(List<ORG_FUNC_ASGTMsg> orgFuncAsgListTemp) {
        Set<String> tocCdSet = new HashSet<String>();

        for (ORG_FUNC_ASGTMsg orgFuncAsg : orgFuncAsgListTemp) {
            String tocCd = orgFuncAsg.tocCd.getValue();
            if (tocCd != null) {
                tocCdSet.add(tocCd);
            }
        }
        return tocCdSet;
    }

    /**
     * @param hrPsnIntfc
     * @param mode
     * @return S21_PSNTMsg null:not exist or not change
     */
    private S21_PSNTMsg makeS21Psn(HR_PSN_INTFCTMsg hrPsnIntfc, String mode) {
        S21_PSNTMsg s21Psn = new S21_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(s21Psn.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(s21Psn.psnCd, hrPsnIntfc.hrPsnIntfcEmpId);

        s21Psn = (S21_PSNTMsg) S21FastTBLAccessor.findByKeyForUpdate(s21Psn);
        if (s21Psn == null) {
            return null;
        }
        S21_PSNTMsg prevS21Psn = (S21_PSNTMsg) s21Psn.clone();

        if (UPDATE.equals(mode) || REHIRE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(s21Psn.psnFirstNm, hrPsnIntfc.hrPsnIntfcPsnFirstNm);
            ZYPEZDItemValueSetter.setValue(s21Psn.psnMidNm, hrPsnIntfc.hrPsnIntfcPsnMidNm);
            ZYPEZDItemValueSetter.setValue(s21Psn.psnLastNm, hrPsnIntfc.hrPsnIntfcPsnLastNm);
            ZYPEZDItemValueSetter.setValue(s21Psn.hrTtlNm, hrPsnIntfc.hrPsnIntfcJobTtlNm);
        }

        ZYPEZDItemValueSetter.setValue(s21Psn.emlAddr, hrPsnIntfc.hrPsnIntfcEmlAddr);
        if (TERMINATE.equals(mode) || UPDATE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(s21Psn.effThruDt, hrPsnIntfc.hrPsnIntfcWrkEndDt);
        }
        if (REHIRE.equals(mode)) {
            s21Psn.effThruDt.clear();
        }

        if (TERMINATE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(s21Psn.delFlg, ZYPConstant.FLG_ON_Y);
        }

        if (REHIRE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(s21Psn.delFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(s21Psn.hrCoaAcctCd, hrPsnIntfc.hrPsnIntfcCoaAcctCd);
        ZYPEZDItemValueSetter.setValue(s21Psn.faxNum, hrPsnIntfc.hrPsnIntfcFaxNum);
        ZYPEZDItemValueSetter.setValue(s21Psn.cellTelNum, hrPsnIntfc.hrPsnIntfcCellTelNum);
        ZYPEZDItemValueSetter.setValue(s21Psn.workTelNum, hrPsnIntfc.hrPsnWrkTelNum);
        ZYPEZDItemValueSetter.setValue(s21Psn.ofcTelNum, hrPsnIntfc.hrPsnWrkTelNum);
        ZYPEZDItemValueSetter.setValue(s21Psn.hrSupvId, hrPsnIntfc.hrPsnIntfcSupvId);
        ZYPEZDItemValueSetter.setValue(s21Psn.hrSupvNm, hrPsnIntfc.hrPsnIntfcSupvNm);
        ZYPEZDItemValueSetter.setValue(s21Psn.hrPsnCmpyCd, hrPsnIntfc.hrPsnIntfcCmpyCd);
        ZYPEZDItemValueSetter.setValue(s21Psn.hrPsnCmpyNm, hrPsnIntfc.hrPsnIntfcCmpyNm);
        ZYPEZDItemValueSetter.setValue(s21Psn.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
        // 2016/08/29 CSA-QC#13093 Add Start
        ZYPEZDItemValueSetter.setValue(s21Psn.jobTtlCd, hrPsnIntfc.hrPsnIntfcJobCd);
        ZYPEZDItemValueSetter.setValue(s21Psn.emlAddr, hrPsnIntfc.hrPsnIntfcEmlAddr);
        // 2016/08/29 CSA-QC#13093 Add End

        // QC#10364
        boolean isAddressUpdated = false;
        String hrLocCd = hrPsnIntfc.hrPsnIntfcLocCd.getValue();
        if (!equalsNullOk(hrLocCd, prevS21Psn.hrPsnIntfcLocCd.getValue())) {
            if (ZYPCommonFunc.hasValue(hrLocCd)) {
                PTY_LOC_WRKTMsg ptyLocWrkTMsg = getAdressByHrLocation(hrLocCd);
                if (ptyLocWrkTMsg != null) {
                    setS21PersonAddress(s21Psn, ptyLocWrkTMsg, hrLocCd);
                    isAddressUpdated = true;
                }
            }
        }

        // Add Start 2017/01/10 M.Ohno S21_NA#16336
        if (ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnIntfcHireDt) //
                && !hrPsnIntfc.hrPsnIntfcHireDt.getValue().equals(s21Psn.effFromDt.getValue())) {
            ZYPEZDItemValueSetter.setValue(s21Psn.effFromDt, hrPsnIntfc.hrPsnIntfcHireDt);
        }
        // Add End 2017/01/10 M.Ohno S21_NA#16336

        if (equalsNullOk(s21Psn.psnFirstNm.getValue(), prevS21Psn.psnFirstNm.getValue()) && equalsNullOk(s21Psn.psnMidNm.getValue(), prevS21Psn.psnMidNm.getValue())
                && equalsNullOk(s21Psn.psnLastNm.getValue(), prevS21Psn.psnLastNm.getValue()) && equalsNullOk(s21Psn.hrTtlNm.getValue(), prevS21Psn.hrTtlNm.getValue())
                && equalsNullOk(s21Psn.emlAddr.getValue(), prevS21Psn.emlAddr.getValue()) && equalsNullOk(s21Psn.effThruDt.getValue(), prevS21Psn.effThruDt.getValue()) && equalsNullOk(s21Psn.delFlg.getValue(), prevS21Psn.delFlg.getValue())
                && equalsNullOk(s21Psn.hrCoaAcctCd.getValue(), prevS21Psn.hrCoaAcctCd.getValue()) && equalsNullOk(s21Psn.faxNum.getValue(), prevS21Psn.faxNum.getValue())
                && equalsNullOk(s21Psn.cellTelNum.getValue(), prevS21Psn.cellTelNum.getValue()) && equalsNullOk(s21Psn.workTelNum.getValue(), prevS21Psn.workTelNum.getValue())
                && equalsNullOk(s21Psn.ofcTelNum.getValue(), prevS21Psn.ofcTelNum.getValue()) && equalsNullOk(s21Psn.hrSupvId.getValue(), prevS21Psn.hrSupvId.getValue())
                && equalsNullOk(s21Psn.hrSupvNm.getValue(), prevS21Psn.hrSupvNm.getValue()) && equalsNullOk(s21Psn.hrPsnCmpyCd.getValue(), prevS21Psn.hrPsnCmpyCd.getValue())
                && equalsNullOk(s21Psn.hrPsnCmpyNm.getValue(), prevS21Psn.hrPsnCmpyNm.getValue()) && equalsNullOk(s21Psn.dsInsdCtyLimitFlg.getValue(), prevS21Psn.dsInsdCtyLimitFlg.getValue())
                && equalsNullOk(s21Psn.jobTtlCd.getValue(), prevS21Psn.jobTtlCd.getValue()) && equalsNullOk(s21Psn.emlAddr.getValue(), prevS21Psn.emlAddr.getValue())
                // QC#10364
                && !isAddressUpdated
                && equalsNullOk(s21Psn.effFromDt.getValue(), prevS21Psn.effFromDt.getValue()) // Add 2017/01/10 M.Ohno S21_NA#16336
                ) {
            return null;
        }

        return s21Psn;
    }

    /**
     * @param hrPsnIntfc
     * @param mode
     * @return
     */
    private List<DS_ORG_RESRC_REVTMsg> makeDsOrgResrcRevForTerminate(HR_PSN_INTFCTMsg hrPsnIntfc, List<DS_ORG_RESRC_REVTMsg> removeDsOrgResrcRevList, String mode) {

        List<DS_ORG_RESRC_REVTMsg> dsOrgResrcRevList = new ArrayList<DS_ORG_RESRC_REVTMsg>(this.commitUnit);
        Map<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("psnCd", hrPsnIntfc.hrPsnIntfcEmpId.getValue());
        // 2016/11/14 CSA-QC#15937 Add Start
        ssmParam.put("maxEffThruDt", "99991231");
        ssmParam.put("slsDt", ZYPDateUtil.addDays(this.slsDt, 1));
        // 2016/11/14 CSA-QC#15937 Add End

        List<Map<String, Object>> dsOrgResrcRevMapList = this.ssmBatchClient.queryObjectList("getDsOrgResrcRev", ssmParam);
        for (Map<String, Object> dsOrgResrcRevMap : dsOrgResrcRevMapList) {
            DS_ORG_RESRC_REVTMsg dsOrgResrcRev = new DS_ORG_RESRC_REVTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.psnCd, (String) dsOrgResrcRevMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.revAcctTpCd, (String) dsOrgResrcRevMap.get("REV_ACCT_TP_CD"));
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.effFromDt, (String) dsOrgResrcRevMap.get("EFF_FROM_DT"));

            dsOrgResrcRev = (DS_ORG_RESRC_REVTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsOrgResrcRev);

            if (dsOrgResrcRev == null) {
                continue;
            }
            //S21_NA ADD START QC#20924
            if(removeDsOrgResrcRevList != null && dsOrgResrcRev.effFromDt.getValue().compareTo(hrPsnIntfc.hrPsnIntfcWrkEndDt.getValue()) > 0){
                removeDsOrgResrcRevList.add(dsOrgResrcRev);
                continue;
            }
            //S21_NA ADD END QC#20924

            // 2016/04/11 CSA-QC#6297 Mod Start
            DS_ORG_RESRC_REVTMsg prevDsOrgResrcRev = (DS_ORG_RESRC_REVTMsg) dsOrgResrcRev.clone();

            dsOrgResrcRev = setDsOrgResrcRev(hrPsnIntfc, dsOrgResrcRev, prevDsOrgResrcRev, mode);// S21_NA MOD QC#20924
            if (dsOrgResrcRev == null) {
                continue;
            }
            // 2016/04/11 CSA-QC#6297 Mod End

            dsOrgResrcRevList.add(dsOrgResrcRev);

        }
        return dsOrgResrcRevList;
    }

    /**
     * @param hrPsnIntfc
     * @param mode
     * @return
     */
    private List<DS_ORG_RESRC_REVTMsg> makeDsOrgResrcRevForUpdate(HR_PSN_INTFCTMsg hrPsnIntfc, String mode) { //S21_NA ADD QC#20924
        // 2016/11/01 CSA-QC#14786 Add Start
        if(!ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaCmpyCd) 
                || !ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaExtnCd)
                || !ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaBrCd)
                || !ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaCcCd)
                ){
            return null;
        }
        // 2016/11/01 CSA-QC#14786 Add End

        // 2016/04/11 CSA-QC#6297 Add Start
        List<DS_ORG_RESRC_REVTMsg> dsOrgResrcRevList = new ArrayList<DS_ORG_RESRC_REVTMsg>(this.commitUnit);
        DS_ORG_RESRC_REVTMsg dsOrgResrcRev = new DS_ORG_RESRC_REVTMsg();

        dsOrgResrcRev.setSQLID("001");
        dsOrgResrcRev.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsOrgResrcRev.setConditionValue("psnCd01", hrPsnIntfc.hrPsnIntfcEmpId.getValue());
        dsOrgResrcRev.setConditionValue("revAcctTpCd01", REV_ACCT_TP.REVENUE);
        dsOrgResrcRev.setConditionValue("effThruDt02", this.slsDt);

        DS_ORG_RESRC_REVTMsgArray dsOrgResrcRevArry = (DS_ORG_RESRC_REVTMsgArray) EZDTBLAccessor.findByConditionForUpdate(dsOrgResrcRev);

        if (dsOrgResrcRevArry == null || dsOrgResrcRevArry.getValidCount() != 1) {
            return dsOrgResrcRevList;
        }

        if (ZYPDateUtil.addDays(this.slsDt, 1).compareTo(dsOrgResrcRevArry.no(0).effFromDt.getValue()) == 0 || ZYPConstant.FLG_ON_Y.equals((String) dsOrgResrcRevArry.no(0).manEntryFlg.getValue())) {
            return dsOrgResrcRevList;
        }

        dsOrgResrcRev = new DS_ORG_RESRC_REVTMsg();
        dsOrgResrcRev.glblCmpyCd.setValue(this.glblCmpyCd);
        dsOrgResrcRev.psnCd.setValue(hrPsnIntfc.hrPsnIntfcEmpId.getValue());
        dsOrgResrcRev.revAcctTpCd.setValue(REV_ACCT_TP.REVENUE);
        dsOrgResrcRev.effFromDt.setValue(ZYPDateUtil.addDays(this.slsDt, 1));

        dsOrgResrcRev = setDsOrgResrcRev(hrPsnIntfc, dsOrgResrcRev, dsOrgResrcRevArry.no(0), mode);// S21_NA MOD QC#20924
        if (dsOrgResrcRev != null) {
            dsOrgResrcRevList.add(dsOrgResrcRev);
        }

        return dsOrgResrcRevList;
        // 2016/04/11 CSA-QC#6297 Add End
    }

    /**
     * @param hrPsnIntfc
     * @param mode
     * @return
     */
    private List<DS_ORG_RESRC_REVTMsg> expireDsOrgResrcRev(List<DS_ORG_RESRC_REVTMsg> dsOrgResrcRevList, List<DS_ORG_RESRC_REVTMsg> terminateDsOrgResrcRevList) {
        // 2016/04/11 CSA-QC#6297 Add Start
        for (DS_ORG_RESRC_REVTMsg dsOrgResrcRevTMsg : dsOrgResrcRevList) {
            DS_ORG_RESRC_REVTMsg dsOrgResrcRev = new DS_ORG_RESRC_REVTMsg();
            dsOrgResrcRev.setSQLID("001");
            dsOrgResrcRev.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsOrgResrcRev.setConditionValue("psnCd01", dsOrgResrcRevTMsg.psnCd.getValue());
            dsOrgResrcRev.setConditionValue("revAcctTpCd01", REV_ACCT_TP.REVENUE);
            dsOrgResrcRev.setConditionValue("effThruDt02", this.slsDt);

            DS_ORG_RESRC_REVTMsgArray dsOrgResrcRevArry = (DS_ORG_RESRC_REVTMsgArray) EZDTBLAccessor.findByConditionForUpdate(dsOrgResrcRev);
            if (dsOrgResrcRevArry == null || dsOrgResrcRevArry.length() == 0) {
                continue;
            }

            for (int i = 0; i < dsOrgResrcRevArry.getValidCount(); i++) {
                dsOrgResrcRev = dsOrgResrcRevArry.no(i);
                if (dsOrgResrcRev == null) {
                    continue;
                }

                if (!ZYPCommonFunc.hasValue(dsOrgResrcRev.effThruDt) || dsOrgResrcRev.effThruDt.getValue().compareTo(this.slsDt) > 0) {
                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.effThruDt, this.slsDt);
                    terminateDsOrgResrcRevList.add(dsOrgResrcRev);
                }
            }

        }

        return terminateDsOrgResrcRevList;
        // 2016/04/11 CSA-QC#6297 Add End
    }

    /**
     * setDsOrgResrcRev
     * @param hrPsnIntfc HR_PSN_INTFCTMsg
     * @param dsOrgResrcRev DS_ORG_RESRC_REVTMsg
     * @return DS_ORG_RESRC_REVTMsg
     */
    private DS_ORG_RESRC_REVTMsg setDsOrgResrcRev(HR_PSN_INTFCTMsg hrPsnIntfc, DS_ORG_RESRC_REVTMsg dsOrgResrcRev, DS_ORG_RESRC_REVTMsg prevDsOrgResrcRev, String mode) {
// QC#1988
//        String hrPsnIntfcCoaAcctCd = hrPsnIntfc.hrPsnIntfcCoaAcctCd.getValue();
//        int strlen = hrPsnIntfcCoaAcctCd.length();
//
//        if (strlen > COA_CMPY_CD_INDEX) {
//            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.coaCmpyCd, hrPsnIntfcCoaAcctCd.substring(COA_CMPY_CD_INDEX, Math.min(COA_EXTN_CD_INDEX, hrPsnIntfcCoaAcctCd.length() - 1)));
//        }
//
//        if (strlen > COA_EXTN_CD_INDEX) {
//            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.coaExtnCd, hrPsnIntfcCoaAcctCd.substring(COA_EXTN_CD_INDEX, Math.min(COA_BR_CD_INDEX, hrPsnIntfcCoaAcctCd.length() - 1)));
//        }
//
//        if (strlen > COA_BR_CD_INDEX) {
//            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.coaBrCd, hrPsnIntfcCoaAcctCd.substring(COA_BR_CD_INDEX, Math.min(COA_CC_CD_INDEX, hrPsnIntfcCoaAcctCd.length() - 1)));
//        }
//
//        if (strlen > COA_CC_CD_INDEX) {
//            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.coaCcCd, hrPsnIntfcCoaAcctCd.substring(COA_CC_CD_INDEX));
//        }
        if(ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaCmpyCd) 
                && ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaExtnCd)
                && ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaBrCd)
                && ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaCcCd)
                ){
            setValue(dsOrgResrcRev, dsOrgResrcRev.coaCmpyCd, "coaCmpyCd", hrPsnIntfc.hrPsnArcsCoaCmpyCd.getValue());
            setValue(dsOrgResrcRev, dsOrgResrcRev.coaExtnCd, "coaExtnCd", hrPsnIntfc.hrPsnArcsCoaExtnCd.getValue());
            setValue(dsOrgResrcRev, dsOrgResrcRev.coaBrCd, "coaBrCd", hrPsnIntfc.hrPsnArcsCoaBrCd.getValue());
            setValue(dsOrgResrcRev, dsOrgResrcRev.coaCcCd, "coaCcCd", hrPsnIntfc.hrPsnArcsCoaCcCd.getValue());
            // 2017/08/09 CSA-QC#20223 Add Start
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.coaCmpyCd, ADB_GLBL_CMPY_CD);
            // 2017/08/08 CSA-QC#20223 Add End
        }
        // S21_NA ADD START QC#20924
        if (TERMINATE.equals(mode) || REHIRE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.effThruDt, hrPsnIntfc.hrPsnIntfcWrkEndDt);
        }
        // S21_NA ADD END QC#20924  
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.manEntryFlg, ZYPConstant.FLG_OFF_N);

        // Add Start 2019/02/18 QC#29668
        if (!TERMINATE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.tocRgtnReqFlg, ZYPConstant.FLG_ON_Y);
        }
        // Add End 2019/02/18 QC#29668

        if (prevDsOrgResrcRev != null) {
            if (equalsNullOk(prevDsOrgResrcRev.coaCmpyCd.getValue(), dsOrgResrcRev.coaCmpyCd.getValue()) && equalsNullOk(prevDsOrgResrcRev.coaExtnCd.getValue(), dsOrgResrcRev.coaExtnCd.getValue())
                    && equalsNullOk(prevDsOrgResrcRev.coaBrCd.getValue(), dsOrgResrcRev.coaBrCd.getValue()) && equalsNullOk(prevDsOrgResrcRev.coaCcCd.getValue(), dsOrgResrcRev.coaCcCd.getValue())
                    && equalsNullOk(prevDsOrgResrcRev.effThruDt.getValue(), dsOrgResrcRev.effThruDt.getValue()) && equalsNullOk(prevDsOrgResrcRev.manEntryFlg.getValue(), dsOrgResrcRev.manEntryFlg.getValue())) {
                return null;
            }
        }
        return dsOrgResrcRev;
    }

    /**
     * @param hrPsnIntfc
     * @return
     */
    private List<ORG_FUNC_ASGTMsg> makeOrgFuncAsg(HR_PSN_INTFCTMsg hrPsnIntfc, S21_PSNTMsg s21Psn, List<ORG_FUNC_ASGTMsg> removeOrgFuncAsgList, String mode) { // S21_NA MOD QC#20924
        List<ORG_FUNC_ASGTMsg> dsOrgFuncAsgList = new ArrayList<ORG_FUNC_ASGTMsg>(this.commitUnit);
        Map<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("psnCd", s21Psn.psnCd.getValue());
        List<Map<String, Object>> dsOrgFuncAsgMapList = this.ssmBatchClient.queryObjectList("getOrgFuncAsg", ssmParam);
        for (Map<String, Object> dsOrgFuncAsgMap : dsOrgFuncAsgMapList) {
            ORG_FUNC_ASGTMsg dsOrgFuncAsg = new ORG_FUNC_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrgFuncAsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrgFuncAsg.psnCd, s21Psn.psnCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsOrgFuncAsg.tocCd, (String) dsOrgFuncAsgMap.get("TOC_CD"));
            ZYPEZDItemValueSetter.setValue(dsOrgFuncAsg.effFromDt, (String) dsOrgFuncAsgMap.get("EFF_FROM_DT"));

            dsOrgFuncAsg = (ORG_FUNC_ASGTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsOrgFuncAsg);
            if (dsOrgFuncAsg == null) {
                continue;
            }
            // S21_NA ADD START QC#21949
            if(removeOrgFuncAsgList != null && dsOrgFuncAsg.effFromDt.getValue().compareTo(hrPsnIntfc.hrPsnIntfcWrkEndDt.getValue()) > 0){
                removeOrgFuncAsgList.add(dsOrgFuncAsg);
                continue;
            }
            // S21_NA ADD END QC#21949
            ORG_FUNC_ASGTMsg prevDsOrgFuncAsg = (ORG_FUNC_ASGTMsg) dsOrgFuncAsg.clone();

            //ZYPEZDItemValueSetter.setValue(dsOrgFuncAsg.effThruDt, s21Psn.effThruDt); // S21_NA MOD QC#21949
            // MOD START 2016/03/31 QC#6325
            // ZYPEZDItemValueSetter.setValue(dsOrgFuncAsg.gnrnTpCd,
            // GNRN_TP.CURRENT);
            if (TERMINATE.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(dsOrgFuncAsg.gnrnTpCd, GNRN_TP.CURRENT);
                ZYPEZDItemValueSetter.setValue(dsOrgFuncAsg.effThruDt, s21Psn.effThruDt); // S21_NA MOD QC#21949
            } else if (UPDATE.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(dsOrgFuncAsg.gnrnTpCd, GNRN_TP.FUTURE);
            }
            // MOD END 2016/03/31 QC#6325
            if (equalsNullOk(prevDsOrgFuncAsg.effThruDt.getValue(), dsOrgFuncAsg.effThruDt.getValue()) && equalsNullOk(prevDsOrgFuncAsg.gnrnTpCd.getValue(), dsOrgFuncAsg.gnrnTpCd.getValue())) {
                continue;
            }
            dsOrgFuncAsgList.add(dsOrgFuncAsg);
        }
        return dsOrgFuncAsgList;
    }

    /**
     * @param hrPsnIntfc
     * @param tocCdSet
     * @return
     */
    private List<TOC_ORG_STRU_RELNTMsg> makeTocOrgStruReln(HR_PSN_INTFCTMsg hrPsnIntfc, Set<String> tocCdSet, List<TOC_ORG_STRU_RELNTMsg> removeTocOrgStruRelnList, String mode) { // S21_NA MOD QC#20924
        List<TOC_ORG_STRU_RELNTMsg> tocOrgStruRelnList = new ArrayList<TOC_ORG_STRU_RELNTMsg>(this.commitUnit);

        for (String tocCd : tocCdSet) {
            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("tocCd", tocCd);
            ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
            List<Map<String, Object>> tocOrgStruRelnMapList = this.ssmBatchClient.queryObjectList("getTocOrgStruReln", ssmParam);

            for (Map<String, Object> tocOrgStruRelnMap : tocOrgStruRelnMapList) {
                TOC_ORG_STRU_RELNTMsg tocOrgStruReln = new TOC_ORG_STRU_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(tocOrgStruReln.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tocOrgStruReln.tocCd, tocCd);
                ZYPEZDItemValueSetter.setValue(tocOrgStruReln.orgCd, (String) tocOrgStruRelnMap.get("ORG_CD"));
                ZYPEZDItemValueSetter.setValue(tocOrgStruReln.orgStruTpCd, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

                tocOrgStruReln = (TOC_ORG_STRU_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdate(tocOrgStruReln);

                if (tocOrgStruReln == null) {
                    continue;
                }
                // S21_NA ADD START QC#21949
                if(removeTocOrgStruRelnList != null && tocOrgStruReln.effFromDt.getValue().compareTo(hrPsnIntfc.hrPsnIntfcWrkEndDt.getValue()) > 0){
                    removeTocOrgStruRelnList.add(tocOrgStruReln);
                    continue;
                }
                // S21_NA ADD END QC#21949
                TOC_ORG_STRU_RELNTMsg prevTocOrgStruReln = (TOC_ORG_STRU_RELNTMsg) tocOrgStruReln.clone();

                // S21_NA MOD START QC#20924
                if (TERMINATE.equals(mode)) {
                    ZYPEZDItemValueSetter.setValue(tocOrgStruReln.effThruDt, hrPsnIntfc.hrPsnIntfcWrkEndDt);
                }
                // S21_NA MOD END QC#20924

                if (equalsNullOk(prevTocOrgStruReln.effThruDt.getValue(), tocOrgStruReln.effThruDt.getValue())) {
                    continue;
                }

                tocOrgStruRelnList.add(tocOrgStruReln);
            }
        }
        return tocOrgStruRelnList;
    }

    /**
     * @param hrPsnIntfc
     * @param psn
     * @return
     */
    private List<ORG_TOC_CHNG_RQSTTMsg> makeOrgTocChngRqst(HR_PSN_INTFCTMsg hrPsnIntfc, Set<String> tocCdSet, String mode) {
        List<ORG_TOC_CHNG_RQSTTMsg> orgTocChngRqstList = new ArrayList<ORG_TOC_CHNG_RQSTTMsg>(this.commitUnit);

        for (String tocCd : tocCdSet) {
            // QC#13205
            ORG_TOC_CHNG_RQSTTMsg tMsg = new ORG_TOC_CHNG_RQSTTMsg();
            tMsg.setSQLID("107");
            tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            tMsg.setConditionValue("tocCd01", tocCd);
            ORG_TOC_CHNG_RQSTTMsgArray tMsgArray = (ORG_TOC_CHNG_RQSTTMsgArray) EZDTBLAccessor.findByConditionForUpdate(tMsg);
            
            for(int i=0; i<tMsgArray.length(); i++){
//                ORG_TOC_CHNG_RQSTTMsg orgTocChngRqst = new ORG_TOC_CHNG_RQSTTMsg();
//                ZYPEZDItemValueSetter.setValue(orgTocChngRqst.glblCmpyCd, this.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(orgTocChngRqst.tocCd, tocCd);
//    
//                orgTocChngRqst = (ORG_TOC_CHNG_RQSTTMsg) S21FastTBLAccessor.findByKeyForUpdate(orgTocChngRqst);
//    
//                if (orgTocChngRqst == null) {
//                    continue;
//                }
                ORG_TOC_CHNG_RQSTTMsg orgTocChngRqst = tMsgArray.no(i);
                
                ORG_TOC_CHNG_RQSTTMsg prevOrgTocChngRqstMstr = (ORG_TOC_CHNG_RQSTTMsg) orgTocChngRqst.clone();
    
                String firstName = nullToBlank(hrPsnIntfc.hrPsnIntfcPsnFirstNm.getValue());
                String lastName = nullToBlank(hrPsnIntfc.hrPsnIntfcPsnLastNm.getValue());
    
                String techNm = firstName + " " + lastName;
                ZYPEZDItemValueSetter.setValue(orgTocChngRqst.tocNm, (firstName + " " + lastName).substring(0, Math.min(techNm.length(), TECH_NM_MAX_LENGTH)));
                if (TERMINATE.equals(mode)) {
                    ZYPEZDItemValueSetter.setValue(orgTocChngRqst.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                } else if (UPDATE.equals(mode)) {
                    ZYPEZDItemValueSetter.setValue(orgTocChngRqst.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                }
    
                if (equalsNullOk(prevOrgTocChngRqstMstr.tocNm.getValue(), orgTocChngRqst.tocNm.getValue())
                        && equalsNullOk(prevOrgTocChngRqstMstr.rgtnStsCd.getValue(), orgTocChngRqst.rgtnStsCd.getValue())) {
                    continue;
                }

                // Add Start 2019/02/18 QC#29668
                if (TERMINATE.equals(mode)) {
                    // Only TERMINATE mode.
                    // If UPDATE mode and changed coaCmpyCd / coaExtnCd / coaBrCd / coaCcCd,
                    // regist new TOC_CD by Effective Batch(NMAB240001).
                    // Not change coaCmpyCd / coaExtnCd / coaBrCd / coaCcCd of old TOC_CD.
                // Add End 2019/02/18 QC#29668

                // 2017/08/07 CSA-QC#20223 Add Start
                if (ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaCmpyCd)) {
                    ZYPEZDItemValueSetter.setValue(orgTocChngRqst.coaCmpyCd, ADB_GLBL_CMPY_CD);
                }
                if (ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaExtnCd)) {
                    ZYPEZDItemValueSetter.setValue(orgTocChngRqst.coaExtnCd, hrPsnIntfc.hrPsnArcsCoaExtnCd);
                }
                if (ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaBrCd)) {
                    ZYPEZDItemValueSetter.setValue(orgTocChngRqst.coaBrCd, hrPsnIntfc.hrPsnArcsCoaBrCd);
                }
                if (ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaCcCd)) {
                    ZYPEZDItemValueSetter.setValue(orgTocChngRqst.coaCcCd, hrPsnIntfc.hrPsnArcsCoaCcCd);
                }
                // 2017/08/07 CSA-QC#20223 Add End

                // Add Start 2019/02/18 QC#29668
                }
                // Add End 2019/02/18 QC#29668

                orgTocChngRqstList.add(orgTocChngRqst);
            }
        }

        return orgTocChngRqstList;
    }

    /**
     * @param hrPsnIntfc
     * @param psn
     * @return
     */
    // 2016/08/08 CSA-QC#13093 Delete Start
    //    private TECH_MSTRTMsg makeTechMstr(HR_PSN_INTFCTMsg hrPsnIntfc, S21_PSNTMsg s21Psn) {
    //        TECH_MSTRTMsg techMstr = new TECH_MSTRTMsg();
    //        ZYPEZDItemValueSetter.setValue(techMstr.glblCmpyCd, this.glblCmpyCd);
    //        ZYPEZDItemValueSetter.setValue(techMstr.techTocCd, s21Psn.psnCd);
    //
    //        techMstr = (TECH_MSTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(techMstr);
    //
    //        if (techMstr == null) {
    //            return null;
    //        }
    //        TECH_MSTRTMsg prevTechMstr = (TECH_MSTRTMsg) techMstr.clone();
    //
    //        String firstName = nullToBlank(hrPsnIntfc.hrPsnIntfcPsnFirstNm.getValue());
    //        String lastName = nullToBlank(hrPsnIntfc.hrPsnIntfcPsnLastNm.getValue());
    //
    //        String techNm = firstName + " " + lastName;
    //        ZYPEZDItemValueSetter.setValue(techMstr.techNm, (firstName + " " + lastName).substring(0, Math.min(techNm.length(), TECH_NM_MAX_LENGTH)));
    //        ZYPEZDItemValueSetter.setValue(techMstr.fldSvcMgrCd, hrPsnIntfc.hrPsnIntfcSupvId);
    //
    //        if (equalsNullOk(prevTechMstr.techNm.getValue(), techMstr.techNm.getValue()) && equalsNullOk(prevTechMstr.fldSvcMgrCd.getValue(), techMstr.fldSvcMgrCd.getValue())) {
    //            return null;
    //        }
    //
    //        return techMstr;
    //    }
    // 2016/08/08 CSA-QC#13093 Delete End

    /**
     * @param hrPsnIntfc
     * @param s21Psn
     * @param mode
     * @return
     */
    private List<DS_ORG_RESRC_RELNTMsg> makeDsOrgResrcReln(HR_PSN_INTFCTMsg hrPsnIntfc, S21_PSNTMsg s21Psn, List<DS_ORG_RESRC_RELNTMsg> removeDsOrgResrcRelnList, String mode) { // S21_NA MOD QC#20924
        List<DS_ORG_RESRC_RELNTMsg> dsOrgResrcRelnList = new ArrayList<DS_ORG_RESRC_RELNTMsg>(this.commitUnit);
        Map<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("psnCd", hrPsnIntfc.hrPsnIntfcEmpId.getValue());
        List<Map<String, Object>> dsOrgResrcRelnMapList = this.ssmBatchClient.queryObjectList("getDsOrgResrcReln", ssmParam);
        for (Map<String, Object> dsOrgResrcRelnMap : dsOrgResrcRelnMapList) {
            DS_ORG_RESRC_RELNTMsg dsOrgResrcReln = new DS_ORG_RESRC_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.psnCd, hrPsnIntfc.hrPsnIntfcEmpId);
            ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.orgCd, (String) dsOrgResrcRelnMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.orgStruTpCd, (String) dsOrgResrcRelnMap.get("ORG_STRU_TP_CD"));
            ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.orgFuncRoleTpCd, (String) dsOrgResrcRelnMap.get("ORG_FUNC_ROLE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.effFromDt, (String) dsOrgResrcRelnMap.get("EFF_FROM_DT"));

            dsOrgResrcReln = (DS_ORG_RESRC_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsOrgResrcReln);

            if (dsOrgResrcReln == null) {
                continue;
            }
            // S21_NA ADD START QC#21949
            if(removeDsOrgResrcRelnList != null && dsOrgResrcReln.effFromDt.getValue().compareTo(hrPsnIntfc.hrPsnIntfcWrkEndDt.getValue()) > 0){
                removeDsOrgResrcRelnList.add(dsOrgResrcReln);
                continue;
            }
            // S21_NA ADD START QC#21949
            DS_ORG_RESRC_RELNTMsg prevDsOrgResrcReln = (DS_ORG_RESRC_RELNTMsg) dsOrgResrcReln.clone();

// QC#1988
//            String hrPsnIntfcCoaAcctCd = hrPsnIntfc.hrPsnIntfcCoaAcctCd.getValue();
//            int strlen = hrPsnIntfcCoaAcctCd.length();
//
//            if (strlen > COA_BR_CD_INDEX) {
//                ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.coaBrCd, hrPsnIntfcCoaAcctCd.substring(COA_BR_CD_INDEX, Math.min(COA_CC_CD_INDEX, hrPsnIntfcCoaAcctCd.length() - 1)));
//            }
//
//            if (strlen > COA_CC_CD_INDEX) {
//                ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.coaCcCd, hrPsnIntfcCoaAcctCd.substring(COA_CC_CD_INDEX));
//            }

            //ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.effThruDt, hrPsnIntfc.hrPsnIntfcWrkEndDt);// S21_NA MOD QC#21949
            // ADD START 2016/03/31 QC#6325
            if (TERMINATE.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.gnrnTpCd, GNRN_TP.CURRENT);
                ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.effThruDt, hrPsnIntfc.hrPsnIntfcWrkEndDt); // S21_NA MOD QC#21949
            } else if (UPDATE.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(dsOrgResrcReln.gnrnTpCd, GNRN_TP.FUTURE);
            }
            // ADD END 2016/03/31 QC#6325

            if (equalsNullOk(prevDsOrgResrcReln.coaBrCd.getValue(), dsOrgResrcReln.coaBrCd.getValue()) && equalsNullOk(prevDsOrgResrcReln.coaCcCd.getValue(), dsOrgResrcReln.coaCcCd.getValue())
                    && equalsNullOk(prevDsOrgResrcReln.effThruDt.getValue(), dsOrgResrcReln.effThruDt.getValue()) && equalsNullOk(prevDsOrgResrcReln.gnrnTpCd.getValue(), dsOrgResrcReln.gnrnTpCd.getValue())) {
                continue;
            }

            dsOrgResrcRelnList.add(dsOrgResrcReln);
        }
        return dsOrgResrcRelnList;
    }

    /**
     * 
     */
    private void updatePeople() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("hrPsnIntfcCmpyCd", this.hrPsnIntfcCmpyCd);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("hrPsnIntfcEmpStsCd", HR_PSN_INTFC_EMP_STS_CD_ACTIVE);
        // QC#1988
        ssmParam.put("mailBoxEmpIdPrefix", MAIL_BOX_EMP_ID_PREFIX);
        // QC#61266 2023/04/14 Add Start
        ssmParam.put("bizAreaOrgCd", BIZ_AREA_ORG_CD_SALES);
        ssmParam.put("slsDt", this.slsDt);
        ssmParam.put("gracePeriod", this.gracePeriod);
        // QC#61266 2023/04/14 Add End

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPersonForUpDateOrTerminate", ssmParam, execParam);

            rsSet = stmt.executeQuery();

            List<S21_PSNTMsg> updateS21PsnList = new ArrayList<S21_PSNTMsg>(this.commitUnit);
            List<DS_ORG_RESRC_REVTMsg> updateDsOrgResrcRevList = new ArrayList<DS_ORG_RESRC_REVTMsg>(this.commitUnit);
            List<ORG_FUNC_ASGTMsg> updateOrgFuncAsgList = new ArrayList<ORG_FUNC_ASGTMsg>(this.commitUnit);
            List<DS_ORG_RESRC_RELNTMsg> updateDsOrgResrcRelnList = new ArrayList<DS_ORG_RESRC_RELNTMsg>(this.commitUnit);
            List<TOC_ORG_STRU_RELNTMsg> updateTocOrgStruRelnList = new ArrayList<TOC_ORG_STRU_RELNTMsg>(this.commitUnit);
            List<ORG_TOC_CHNG_RQSTTMsg> updateOrgTocChngRqstList = new ArrayList<ORG_TOC_CHNG_RQSTTMsg>(this.commitUnit);
            List<DS_ORG_RESRC_REVTMsg> insDsOrgResrcRevList = new ArrayList<DS_ORG_RESRC_REVTMsg>(this.commitUnit);
         // 2016/08/08 CSA-QC#13093 Delete end
         // List<TECH_MSTRTMsg> updateTechMstrList = new ArrayList<TECH_MSTRTMsg>(this.commitUnit);
         // 2016/08/08 CSA-QC#13093 Delete end

            while (rsSet.next()) {

                HR_PSN_INTFCTMsg hrPsnIntfc = this.makeHrPsnIntfc(rsSet);

                S21_PSNTMsg s21Psn = this.makeS21Psn(hrPsnIntfc, UPDATE);
                if (s21Psn != null) {
                    updateS21PsnList.add(s21Psn);
                    this.totalCount++;
                }

                List<DS_ORG_RESRC_REVTMsg> updateDsOrgResrcRevListTemp = this.makeDsOrgResrcRevForUpdate(hrPsnIntfc, UPDATE);
                // 2016/11/01 CSA-QC#14786 Mod Start
                if (updateDsOrgResrcRevListTemp != null && updateDsOrgResrcRevListTemp.size() > 0) {
                // if (updateDsOrgResrcRevListTemp.size() > 0) {
                // 2016/11/01 CSA-QC#14786 Mod End
                    updateDsOrgResrcRevList.addAll(updateDsOrgResrcRevListTemp);
                }

                // 2016/11/01 CSA-QC#14786 Add Start
                DS_ORG_RESRC_REVTMsg dsOrgResrcRev = insertDsOrgResrcRev(hrPsnIntfc);
                if (dsOrgResrcRev != null) {
                    insDsOrgResrcRevList.add(dsOrgResrcRev);
                }
                // 2016/11/01 CSA-QC#14786 Add End

                if (s21Psn != null) {
                    // MOD START 2016/03/31 QC#6325
                    // List<ORG_FUNC_ASGTMsg> updateOrgFuncAsgListTemp
                    // = this.makeOrgFuncAsg(hrPsnIntfc, s21Psn);
                    List<ORG_FUNC_ASGTMsg> updateOrgFuncAsgListTemp = this.makeOrgFuncAsg(hrPsnIntfc, s21Psn, null, UPDATE);// S21_NA MOD QC#20924
                    // MOD END 2016/03/31 QC#6325
                    if (updateOrgFuncAsgListTemp.size() > 0) {
                        updateOrgFuncAsgList.addAll(updateOrgFuncAsgListTemp);

                        Set<String> tocCdSet = this.makeTocCdSet(updateOrgFuncAsgListTemp);

                        // 2016/08/08 CSA-QC#13093 Add Start
                        List<ORG_TOC_CHNG_RQSTTMsg> updateOrgTocChngRqstTemp = this.makeOrgTocChngRqst(hrPsnIntfc, tocCdSet, UPDATE);
                        if (updateOrgTocChngRqstTemp.size() > 0) {
                            updateOrgTocChngRqstList.addAll(updateOrgTocChngRqstTemp);
                        }
                        // 2016/08/08 CSA-QC#13093 Add End

                        List<TOC_ORG_STRU_RELNTMsg> terminateTocOrgStruRelnListTemp = this.makeTocOrgStruReln(hrPsnIntfc, tocCdSet, null, UPDATE);// S21_NA MOD QC#20924
                        if (terminateTocOrgStruRelnListTemp.size() > 0) {
                            updateTocOrgStruRelnList.addAll(terminateTocOrgStruRelnListTemp);
                        }
                    }

                    // MOD START 2016/03/31 QC#6325
                    // List<DS_ORG_RESRC_RELNTMsg>
                    // updateDsOrgResrcRelnListTemp =
                    // this.makeDsOrgResrcReln(hrPsnIntfc, s21Psn);
                    List<DS_ORG_RESRC_RELNTMsg> updateDsOrgResrcRelnListTemp = this.makeDsOrgResrcReln(hrPsnIntfc, s21Psn, null, UPDATE);
                    // MOD END 2016/03/31 QC#6325
                    if (updateDsOrgResrcRelnListTemp.size() > 0) {
                        updateDsOrgResrcRelnList.addAll(updateDsOrgResrcRelnListTemp);
                    }

                 // 2016/08/08 CSA-QC#13093 Delete Start
                 // TECH_MSTRTMsg techMstr = this.makeTechMstr(hrPsnIntfc, s21Psn);
                 // if (techMstr != null) {
                 //      updateTechMstrList.add(techMstr);
                 // }
                 // 2016/08/08 CSA-QC#13093 Delete Start
                }

                if (updateS21PsnList.size() >= this.commitUnit) {
                 // 2016/08/08 CSA-QC#13093 Mod Start
                 // this.executeBulkUpd(UPDATE, updateS21PsnList, updateDsOrgResrcRevList, updateOrgFuncAsgList, updateDsOrgResrcRelnList, updateTocOrgStruRelnList, updateTechMstrList);
                    this.executeBulkUpd(UPDATE, updateS21PsnList, updateDsOrgResrcRevList, updateOrgFuncAsgList, updateDsOrgResrcRelnList, updateTocOrgStruRelnList, updateOrgTocChngRqstList);
                 // 2016/08/08 CSA-QC#13093 Mod end
                    updateS21PsnList.clear();
                    updateDsOrgResrcRevList.clear();
                    updateOrgFuncAsgList.clear();
                    updateDsOrgResrcRelnList.clear();
                    updateTocOrgStruRelnList.clear();
                 // updateTechMstrList.clear();
                }
                // 2016/11/01 CSA-QC#14786 Add Start
                if (insDsOrgResrcRevList.size() > this.commitUnit) {
                    this.executeBulkInsDsOrgResrcRev(insDsOrgResrcRevList);
                    insDsOrgResrcRevList.clear();
                }
                // 2016/11/01 CSA-QC#14786 Add End
                super.commit();
            }

            // 2016/08/08 CSA-QC#13093 Mod Start
            // if (updateS21PsnList.size() > 0 || updateDsOrgResrcRevList.size() > 0 || updateOrgFuncAsgList.size() > 0 || updateDsOrgResrcRelnList.size() > 0 || updateTocOrgStruRelnList.size() > 0 || updateTechMstrList.size() > 0) {
                // this.executeBulkUpd(UPDATE, updateS21PsnList, updateDsOrgResrcRevList, updateOrgFuncAsgList, updateDsOrgResrcRelnList, updateTocOrgStruRelnList, updateTechMstrList);
            if (updateS21PsnList.size() > 0 || updateDsOrgResrcRevList.size() > 0 || updateOrgFuncAsgList.size() > 0 || updateDsOrgResrcRelnList.size() > 0 || updateTocOrgStruRelnList.size() > 0 || updateOrgTocChngRqstList.size() > 0) {
                this.executeBulkUpd(UPDATE, updateS21PsnList, updateDsOrgResrcRevList, updateOrgFuncAsgList, updateDsOrgResrcRelnList, updateTocOrgStruRelnList, updateOrgTocChngRqstList);
            // 2016/08/08 CSA-QC#13093 Mod End
                updateS21PsnList.clear();
                updateDsOrgResrcRevList.clear();
                updateOrgFuncAsgList.clear();
                updateDsOrgResrcRelnList.clear();
                updateTocOrgStruRelnList.clear();
             // updateTechMstrList.clear();
            }
            // 2016/11/01 CSA-QC#14786 Add Start
            if (insDsOrgResrcRevList.size() > 0) {
                this.executeBulkInsDsOrgResrcRev(insDsOrgResrcRevList);
                insDsOrgResrcRevList.clear();
            }
            super.commit();
            // 2016/11/01 CSA-QC#14786 Add End
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * 
     */
    private void rehirePeople() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("hrPsnIntfcCmpyCd", this.hrPsnIntfcCmpyCd);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("hrPsnIntfcEmpStsCd", HR_PSN_INTFC_EMP_STS_CD_ACTIVE);
        // QC#1988
        ssmParam.put("mailBoxEmpIdPrefix", MAIL_BOX_EMP_ID_PREFIX);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPersonForRehire", ssmParam, execParam);

            rsSet = stmt.executeQuery();

            List<S21_PSNTMsg> rehireS21PsnList = new ArrayList<S21_PSNTMsg>(this.commitUnit);
            List<DS_ORG_RESRC_REVTMsg> rehireDsOrgResrcRevList = new ArrayList<DS_ORG_RESRC_REVTMsg>(this.commitUnit);

            while (rsSet.next()) {

                HR_PSN_INTFCTMsg hrPsnIntfc = this.makeHrPsnIntfc(rsSet);

                S21_PSNTMsg s21Psn = this.makeS21Psn(hrPsnIntfc, REHIRE);
                if (s21Psn != null) {
                    rehireS21PsnList.add(s21Psn);
                    this.totalCount++;
                }

                DS_ORG_RESRC_REVTMsg rehireDsOrgResrcRev = this.makeDsOrgResrcRevForRehire(hrPsnIntfc);
                if (rehireDsOrgResrcRev != null) {
                    rehireDsOrgResrcRevList.add(rehireDsOrgResrcRev);
                }

                if (rehireS21PsnList.size() >= this.commitUnit) {
                // 2016/08/08 CSA-QC#13093 Mod Start
                // this.executeBulkUpd(REHIRE, rehireS21PsnList, rehireDsOrgResrcRevList, Collections.<ORG_FUNC_ASGTMsg> emptyList(), Collections.<DS_ORG_RESRC_RELNTMsg> emptyList(), Collections.<TOC_ORG_STRU_RELNTMsg> emptyList(),
                //      Collections.<TECH_MSTRTMsg> emptyList());
                    this.executeBulkUpd(REHIRE, rehireS21PsnList, rehireDsOrgResrcRevList, Collections.<ORG_FUNC_ASGTMsg> emptyList(), Collections.<DS_ORG_RESRC_RELNTMsg> emptyList(), Collections.<TOC_ORG_STRU_RELNTMsg> emptyList(),
                         Collections.<ORG_TOC_CHNG_RQSTTMsg> emptyList());
                // 2016/08/08 CSA-QC#13093 Mod end
                    rehireS21PsnList.clear();
                    rehireDsOrgResrcRevList.clear();

                    super.commit();
                }
            }

            if (rehireS21PsnList.size() > 0 || rehireDsOrgResrcRevList.size() > 0) {
             // 2016/08/08 CSA-QC#13093 Mod Start
             // this.executeBulkUpd(REHIRE, rehireS21PsnList, rehireDsOrgResrcRevList, Collections.<ORG_FUNC_ASGTMsg> emptyList(), Collections.<DS_ORG_RESRC_RELNTMsg> emptyList(), Collections.<TOC_ORG_STRU_RELNTMsg> emptyList(),
             //         Collections.<TECH_MSTRTMsg> emptyList());
                this.executeBulkUpd(REHIRE, rehireS21PsnList, rehireDsOrgResrcRevList, Collections.<ORG_FUNC_ASGTMsg> emptyList(), Collections.<DS_ORG_RESRC_RELNTMsg> emptyList(), Collections.<TOC_ORG_STRU_RELNTMsg> emptyList(),
                        Collections.<ORG_TOC_CHNG_RQSTTMsg> emptyList());
             // 2016/08/08 CSA-QC#13093 Mod end
                rehireS21PsnList.clear();
                rehireDsOrgResrcRevList.clear();
                super.commit();
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * @param hrPsnIntfc
     */
    private DS_ORG_RESRC_REVTMsg makeDsOrgResrcRevForRehire(HR_PSN_INTFCTMsg hrPsnIntfc) {
        // 2016/04/11 CSA-QC#6297 Mod Start
        DS_ORG_RESRC_REVTMsg dsOrgResrcRev = new DS_ORG_RESRC_REVTMsg();

        dsOrgResrcRev.setSQLID("001");
        dsOrgResrcRev.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsOrgResrcRev.setConditionValue("psnCd01", hrPsnIntfc.hrPsnIntfcEmpId.getValue());
        dsOrgResrcRev.setConditionValue("revAcctTpCd01", REV_ACCT_TP.REVENUE);
        dsOrgResrcRev.setConditionValue("effThruDt02", this.slsDt);

        DS_ORG_RESRC_REVTMsgArray dsOrgResrcRevArry = (DS_ORG_RESRC_REVTMsgArray) EZDTBLAccessor.findByCondition(dsOrgResrcRev);

        if (dsOrgResrcRevArry.getValidCount() > 1) {
            return null;
        }

        dsOrgResrcRev = new DS_ORG_RESRC_REVTMsg();
        dsOrgResrcRev.glblCmpyCd.setValue(this.glblCmpyCd);
        dsOrgResrcRev.psnCd.setValue(hrPsnIntfc.hrPsnIntfcEmpId.getValue());
        dsOrgResrcRev.revAcctTpCd.setValue(REV_ACCT_TP.REVENUE);
        dsOrgResrcRev.effFromDt.setValue(ZYPDateUtil.addDays(this.slsDt, 1));

        if (dsOrgResrcRevArry.getValidCount() == 1) {
            dsOrgResrcRev = setDsOrgResrcRev(hrPsnIntfc, dsOrgResrcRev, dsOrgResrcRevArry.no(0), REHIRE);// S21_NA MOD QC#20924
        } else {
            dsOrgResrcRev = setDsOrgResrcRev(hrPsnIntfc, dsOrgResrcRev, null, REHIRE);// S21_NA MOD QC#20924
        }
        if (dsOrgResrcRev == null) {
            return null;
        }

        return dsOrgResrcRev;
        // 2016/04/11 CSA-QC#6297 Mod End
    }

    /**
     * @param hrPsnIntfc
     */
    private DS_ORG_RESRC_REVTMsg insertDsOrgResrcRev(HR_PSN_INTFCTMsg hrPsnIntfc) {
        if(!ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaCmpyCd) 
                || !ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaExtnCd)
                || !ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaBrCd)
                || !ZYPCommonFunc.hasValue(hrPsnIntfc.hrPsnArcsCoaCcCd)){
            return null;
        }

        if (isExsitsDsOrgRsrcRev(hrPsnIntfc.hrPsnIntfcEmpId.getValue())) {
            return null;
        }

        S21_PSNTMsg s21Psn = new S21_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(s21Psn.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(s21Psn.psnCd, hrPsnIntfc.hrPsnIntfcEmpId);

        s21Psn = (S21_PSNTMsg) S21FastTBLAccessor.findByKey(s21Psn);
        if (s21Psn == null) {
            return null;
        }

        DS_ORG_RESRC_REVTMsg dsOrgResrcRev = new DS_ORG_RESRC_REVTMsg();

        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.psnCd, hrPsnIntfc.hrPsnIntfcEmpId);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.revAcctTpCd, REV_ACCT_TP.REVENUE);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.effFromDt, ZYPDateUtil.addDays(this.slsDt, 1));

        setValue(dsOrgResrcRev, dsOrgResrcRev.coaCmpyCd, "coaCmpyCd", hrPsnIntfc.hrPsnArcsCoaCmpyCd.getValue());
        setValue(dsOrgResrcRev, dsOrgResrcRev.coaExtnCd, "coaExtnCd", hrPsnIntfc.hrPsnArcsCoaExtnCd.getValue());
        setValue(dsOrgResrcRev, dsOrgResrcRev.coaBrCd, "coaBrCd", hrPsnIntfc.hrPsnArcsCoaBrCd.getValue());
        setValue(dsOrgResrcRev, dsOrgResrcRev.coaCcCd, "coaCcCd", hrPsnIntfc.hrPsnArcsCoaCcCd.getValue());
        // 2017/08/09 CSA-QC#20223 Add Start
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.coaCmpyCd, ADB_GLBL_CMPY_CD);
        // 2017/08/08 CSA-QC#20223 Add End
        
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.effThruDt, hrPsnIntfc.hrPsnIntfcWrkEndDt);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.manEntryFlg, ZYPConstant.FLG_OFF_N);
        // Add Start 2019/02/18 QC#29668
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRev.tocRgtnReqFlg, ZYPConstant.FLG_ON_Y);
        // Add End 2019/02/18 QC#29668

        return dsOrgResrcRev;
    }

    /**
     * @param updS21PsnList
     * @param updDsOrgResrcRevList
     * @param updOrgFuncAsgList
     * @param updDsOrgResrcRelnList
     * @param updTocOrgStruRelnList
     * @param updTechMstrList
     */
    private void executeBulkUpd(String mode, List<S21_PSNTMsg> updS21PsnList, List<DS_ORG_RESRC_REVTMsg> updDsOrgResrcRevList, List<ORG_FUNC_ASGTMsg> updOrgFuncAsgList, List<DS_ORG_RESRC_RELNTMsg> updDsOrgResrcRelnList,
            List<TOC_ORG_STRU_RELNTMsg> updTocOrgStruRelnList, List<ORG_TOC_CHNG_RQSTTMsg> updateOrgTocChngRqstList) {

        // BulkUpdate
        int updS21PsnDataListSize = updS21PsnList.size();
        if (updS21PsnDataListSize > 0) {
            int updS21PsnCount = S21FastTBLAccessor.update(updS21PsnList.toArray(new S21_PSNTMsg[updS21PsnDataListSize]));

            if (updS21PsnCount != updS21PsnDataListSize) {
                this.totalErrCount += updS21PsnDataListSize - updS21PsnCount;
                super.rollback();
                throw new S21AbendException(MMAM0004E, new String[] {"S21_PSN" });
            }
        }

        // 2016/04/11 CSA-QC#6297 Mod Start
        if (TERMINATE.equals(mode)) {
            int updDsOrgResrcRevDataListSize = updDsOrgResrcRevList.size();
            if (updDsOrgResrcRevDataListSize > 0) {
                int updDsOrgResrcRevCount = S21FastTBLAccessor.update(updDsOrgResrcRevList.toArray(new DS_ORG_RESRC_REVTMsg[updDsOrgResrcRevDataListSize]));

                if (updDsOrgResrcRevCount != updDsOrgResrcRevDataListSize) {
                    super.rollback();
                    throw new S21AbendException(MMAM0004E, new String[] {"DS_ORG_RESRC_REV" });
                }
            }
        } else {
            // expiring latest DS_ORG_RESRC_REV
            List<DS_ORG_RESRC_REVTMsg> expireDsOrgResrcRevList = new ArrayList<DS_ORG_RESRC_REVTMsg>(this.commitUnit);
            expireDsOrgResrcRevList = expireDsOrgResrcRev(updDsOrgResrcRevList, expireDsOrgResrcRevList);
            int expireDsOrgResrcRevDataListSize = expireDsOrgResrcRevList.size();
            if (expireDsOrgResrcRevDataListSize > 0) {
                int expireDsOrgResrcRevCount = S21FastTBLAccessor.update(expireDsOrgResrcRevList.toArray(new DS_ORG_RESRC_REVTMsg[expireDsOrgResrcRevDataListSize]));

                if (expireDsOrgResrcRevCount != expireDsOrgResrcRevDataListSize) {
                    super.rollback();
                    throw new S21AbendException(MMAM0004E, new String[] {"DS_ORG_RESRC_REV" });
                }
            }

            int updDsOrgResrcRevDataListSize = updDsOrgResrcRevList.size();
            // insert interfaced DS_ORG_RESRC_REV
            if (updDsOrgResrcRevDataListSize > 0) {
                int updDsOrgResrcRevCount = S21FastTBLAccessor.insert(updDsOrgResrcRevList.toArray(new DS_ORG_RESRC_REVTMsg[updDsOrgResrcRevDataListSize]));

                if (updDsOrgResrcRevCount != updDsOrgResrcRevDataListSize) {
                    super.rollback();
                    throw new S21AbendException(MMAM0004E, new String[] {"DS_ORG_RESRC_REV" });
                }
            }
        }
        // 2016/04/11 CSA-QC#6297 Mod End

        int updOrgFuncAsgDataListSize = updOrgFuncAsgList.size();
        if (updOrgFuncAsgDataListSize > 0) {
            int updOrgFuncAsgCount = S21FastTBLAccessor.update(updOrgFuncAsgList.toArray(new ORG_FUNC_ASGTMsg[updOrgFuncAsgDataListSize]));

            if (updOrgFuncAsgCount != updOrgFuncAsgDataListSize) {
                super.rollback();
                throw new S21AbendException(MMAM0004E, new String[] {"ORG_FUNC_ASG" });
            }
        }

        int updDsOrgResrcRelnDataListSize = updDsOrgResrcRelnList.size();
        if (updDsOrgResrcRelnDataListSize > 0) {
            int updDsOrgResrcRelnCount = S21FastTBLAccessor.update(updDsOrgResrcRelnList.toArray(new DS_ORG_RESRC_RELNTMsg[updDsOrgResrcRelnDataListSize]));

            if (updDsOrgResrcRelnCount != updDsOrgResrcRelnDataListSize) {
                super.rollback();
                throw new S21AbendException(MMAM0004E, new String[] {"DS_ORG_RESRC_RELN" });
            }
        }

        int updTocOrgStruRelnDataListSize = updTocOrgStruRelnList.size();
        if (updTocOrgStruRelnDataListSize > 0) {
            int updTocOrgStruRelnCount = S21FastTBLAccessor.update(updTocOrgStruRelnList.toArray(new TOC_ORG_STRU_RELNTMsg[updTocOrgStruRelnDataListSize]));

            if (updTocOrgStruRelnCount != updTocOrgStruRelnDataListSize) {
                super.rollback();
                throw new S21AbendException(MMAM0004E, new String[] {"TOC_ORG_STRU_RELN" });
            }
        }

        // 2016/08/08 CSA-QC#13093 Add Start
        int updateOrgTocChngRqstListSize = updateOrgTocChngRqstList.size();
        if (updateOrgTocChngRqstListSize > 0) {
            int updOrgTocChngRqstCount = S21FastTBLAccessor.update(updateOrgTocChngRqstList.toArray(new ORG_TOC_CHNG_RQSTTMsg[updateOrgTocChngRqstListSize]));

            if (updOrgTocChngRqstCount != updateOrgTocChngRqstListSize) {
                super.rollback();
                throw new S21AbendException(MMAM0004E, new String[] {"ORG_TOC_CHNG_RQST" });
            }
        }
        // 2016/08/08 CSA-QC#13093 Add Start

       // 2016/08/08 CSA-QC#13093 Delete Start
       // int updTechMstrDataListSize = updTechMstrList.size();
       // if (updTechMstrDataListSize > 0) {
       //    int updTechMstrCount = S21FastTBLAccessor.update(updTechMstrList.toArray(new TECH_MSTRTMsg[updTechMstrDataListSize]));

       // if (updTechMstrCount != updTechMstrDataListSize) {
       //      super.rollback();
       //         throw new S21AbendException(MMAM0004E, new String[] {"TECH_MSTR" });
       //     }
       // }
       // 2016/08/08 CSA-QC#13093 Delete End

        this.totalNmlCount += updS21PsnDataListSize;
    }
    

    //S21_NA ADD START QC#20924
    /**
     * bulk delete
     * @param removeOrgFuncAsgList
     * @param removeTocOrgStruRelnList
     * @param removeDsOrgResrcRelnList
     * @param removeDsOrgResrcRevList
     */
    private void executeBulkDel(List<ORG_FUNC_ASGTMsg> removeOrgFuncAsgList, List<TOC_ORG_STRU_RELNTMsg> removeTocOrgStruRelnList, List<DS_ORG_RESRC_RELNTMsg> removeDsOrgResrcRelnList, List<DS_ORG_RESRC_REVTMsg> removeDsOrgResrcRevList) {
 
        int removeOrgFuncAsgListSize = removeOrgFuncAsgList.size();
        if (removeOrgFuncAsgListSize > 0) {
            // 2020/06/19 QC#56181 Mod Start
//            int removeOrgFuncAsgCount = S21FastTBLAccessor.removePhysical(removeOrgFuncAsgList.toArray(new ORG_FUNC_ASGTMsg[removeOrgFuncAsgListSize]));
            for (int i = 0; i < removeOrgFuncAsgListSize; i++) { 
                ZYPEZDItemValueSetter.setValue(removeOrgFuncAsgList.get(i).gnrnTpCd, GNRN_TP.DELETE);
            }

            int removeOrgFuncAsgCount = S21FastTBLAccessor.update(removeOrgFuncAsgList.toArray(new ORG_FUNC_ASGTMsg[removeOrgFuncAsgListSize]));
            // 2020/06/19 QC#56181 Mod End
            if (removeOrgFuncAsgCount != removeOrgFuncAsgListSize) {
                this.totalErrCount += removeOrgFuncAsgListSize - removeOrgFuncAsgCount;
                super.rollback();
                throw new S21AbendException(MMAM0005E, new String[] {"ORG_FUNC_ASG" });
            }
        }

        // 2020/06/19 QC#56181 Del Start
//        int removeTocOrgStruRelnListSize = removeTocOrgStruRelnList.size();
//        if (removeTocOrgStruRelnListSize > 0) {
//            int removeTocOrgStruRelnCount = S21FastTBLAccessor.removePhysical(removeTocOrgStruRelnList.toArray(new TOC_ORG_STRU_RELNTMsg[removeTocOrgStruRelnListSize]));
//
//            if (removeTocOrgStruRelnCount != removeTocOrgStruRelnListSize) {
//                this.totalErrCount += removeTocOrgStruRelnListSize - removeTocOrgStruRelnCount;
//                super.rollback();
//                throw new S21AbendException(MMAM0005E, new String[] {"TOC_ORG_STRU_RELN" });
//            }
//        }
        // 2020/06/19 QC#56181 Del End

        int removeDsOrgResrcRelnListSize = removeDsOrgResrcRelnList.size();
        if (removeDsOrgResrcRelnListSize > 0) {
            // 2020/06/19 QC#56181 Mod Start
//            int removeDsOrgResrcRelnCount = S21FastTBLAccessor.removePhysical(removeDsOrgResrcRelnList.toArray(new DS_ORG_RESRC_RELNTMsg[removeDsOrgResrcRelnListSize]));
            for (int i = 0; i < removeDsOrgResrcRelnListSize; i++) { 
                ZYPEZDItemValueSetter.setValue(removeDsOrgResrcRelnList.get(i).gnrnTpCd, GNRN_TP.DELETE);
            }

            int removeDsOrgResrcRelnCount = S21FastTBLAccessor.update(removeDsOrgResrcRelnList.toArray(new DS_ORG_RESRC_RELNTMsg[removeDsOrgResrcRelnListSize]));
            // 2020/06/19 QC#56181 Mod End
            if (removeDsOrgResrcRelnCount != removeDsOrgResrcRelnListSize) {
                this.totalErrCount += removeDsOrgResrcRelnListSize - removeDsOrgResrcRelnCount;
                super.rollback();
                throw new S21AbendException(MMAM0005E, new String[] {"DS_ORG_RESRC_RELN" });
            }
        }

        // 2020/06/19 QC#56181 Del Start
//        int removeDsOrgResrcRevListSize = removeDsOrgResrcRevList.size();
//        if (removeDsOrgResrcRevListSize > 0) {
//            int removeDsOrgResrcRevCount = S21FastTBLAccessor.removePhysical(removeDsOrgResrcRevList.toArray(new DS_ORG_RESRC_REVTMsg[removeDsOrgResrcRevListSize]));
//
//            if (removeDsOrgResrcRevCount != removeDsOrgResrcRevListSize) {
//                this.totalErrCount += removeDsOrgResrcRevListSize - removeDsOrgResrcRevCount;
//                super.rollback();
//                throw new S21AbendException(MMAM0005E, new String[] {"DS_ORG_RESRC_REV" });
//            }
//        }
        // 2020/06/19 QC#56181 Del End
    }
    //S21_NA ADD END QC#20924

    /**
     * @param str1
     * @param str2
     * @return
     */
    private static boolean equalsNullOk(String str1, String str2) {
        return nullToBlank(str1).equals(nullToBlank(str2));
    }

    /**
     * @param str
     * @return
     */
    private static String nullToBlank(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB230001().executeBatch(NMAB230001.class.getSimpleName());
    }
    
    // QC#10364
    private PTY_LOC_WRKTMsg getAdressByHrLocation(String hrLocCd) {
        if (cacheHrLocAddr.containsKey(hrLocCd)) {
            Object value = cacheHrLocAddr.get(hrLocCd);
            if (value instanceof PTY_LOC_WRKTMsg) {
                return (PTY_LOC_WRKTMsg) value;
            } else {
                return null;
            }
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("hrLocCd", hrLocCd);
        ssmParam.put("dsXrefAcctTp_CSA", DS_XREF_ACCT_TP.CSA);
        List<Map<String, Object>> listMap = this.ssmBatchClient.queryObjectList("getAdressByHrLocation", ssmParam);
        for (Map<String, Object> map : listMap) {
            PTY_LOC_WRKTMsg tMsg = new PTY_LOC_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, (String) map.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, (String) map.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, (String) map.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, (String) map.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, (String) map.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.cntyPk, (BigDecimal) map.get("CNTY_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.provNm, (String) map.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(tMsg.stCd, (String) map.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.postCd, (String) map.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, (String) map.get("CTRY_CD"));
            cacheHrLocAddr.put(hrLocCd, tMsg);
            return tMsg;
        }
        cacheHrLocAddr.put(hrLocCd, "");
        return null;
    }

    private boolean isExsitsDsOrgRsrcRev(String psnCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("psnCd", psnCd);
        ssmParam.put("revAcctTp_REV", REV_ACCT_TP.REVENUE);
        int revCnt = (Integer) this.ssmBatchClient.queryObject("checkExistsRevenue", ssmParam);
        if (revCnt > 0) {
            return true;
        }
        return false;
    }

    private void setS21PersonAddress(S21_PSNTMsg s21Psn, PTY_LOC_WRKTMsg ptyLocWrkTMsg, String hrLocCd) {
        ZYPEZDItemValueSetter.setValue(s21Psn.firstLineAddr, ptyLocWrkTMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(s21Psn.scdLineAddr, ptyLocWrkTMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(s21Psn.thirdLineAddr, ptyLocWrkTMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(s21Psn.frthLineAddr, ptyLocWrkTMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(s21Psn.ctyAddr, ptyLocWrkTMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(s21Psn.cntyPk, ptyLocWrkTMsg.cntyPk);
        ZYPEZDItemValueSetter.setValue(s21Psn.provNm, ptyLocWrkTMsg.provNm);
        ZYPEZDItemValueSetter.setValue(s21Psn.stCd, ptyLocWrkTMsg.stCd);
        ZYPEZDItemValueSetter.setValue(s21Psn.postCd, ptyLocWrkTMsg.postCd);
        ZYPEZDItemValueSetter.setValue(s21Psn.ctryCd, ptyLocWrkTMsg.ctryCd);
        ZYPEZDItemValueSetter.setValue(s21Psn.hrPsnIntfcLocCd, hrLocCd);
    }
}
