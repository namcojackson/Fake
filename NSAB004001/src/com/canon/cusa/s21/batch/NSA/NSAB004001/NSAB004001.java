/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB004001;

import static com.canon.cusa.s21.batch.NSA.NSAB004001.constant.NSAB004001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsgArray;
import business.db.DS_SUB_CONTRTMsg;
import business.db.DS_SUB_CONTRTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC052001PMsg;
import business.parts.NSZC047011PMsg;
import business.parts.NSZC077001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC052001.NSZC052001;
import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC077001.NSZC077001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001UpdateDsContrPrcAlloc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetNotArvMachCntForFlt;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPBLOBAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * Contract mode change / auto approval Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/06/2015   Hitachi         T.Harada        Create          N/A
 * 02/26/2016   Hitachi         T.Aoyagi        Update          QC2305
 * 06/06/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 09/28/2016   Hitachi         K.Yamada        Update          QC#14674
 * 08/01/2017   Hitachi         E.Kameishi      Update          QC#18882
 * 08/21/2017   Hitachi         M.Kidokoro      Update          QC#20057
 * 09/04/2017   Hitachi         K.Yamada        Update          QC#20919
 * 2017/09/11   Hitachi         K.Kojima        Update          QC#18435
 * 09/14/2017   CITS            M.Naito         Update          QC#18534
 * 2017/10/03   Hitachi         K.Kojima        Update          QC#18417
 * 2017/10/06   Hitachi         K.Kojima        Update          QC#18534-1
 * 2017/10/27   Hitachi         U.Kim           Update          QC#21613
 * 2017/11/16   Hitachi         K.Kojima        Update          QC#21886
 * 2018/01/25   Hitachi         K.Kojima        Update          QC#23767
 * 2018/04/02   Hitachi         K.Kojima        Update          QC#25199
 * 2018/04/23   Hitachi         K.Kishimoto     Update          QC#23378
 * 2018/05/14   Hitachi         U.Kim           Update          QC#24854
 * 2018/05/15   CITS            M.Naito         Update          QC#21679
 * 2018/06/13   CITS            T.Wada          Update          QC#26592
 * 2018/06/13   Hitachi         K.Kojima        Update          QC#26211
 * 2019/05/15   Hitachi         K.Fujimoto      Update          QC#50279
 * 2019/05/22   Hitachi         K.Kim           Update          QC#50212
 * 2022/06/21   CITS            R.Jin           Update          QC#60216
 * 2022/07/12   CITS            R.Jin           Update          QC#60276
 * 2023/02/13   CITS            R.Jin           Update          QC#61172
 * 2024/01/11   Hitachi         S.Moriai        Update          QC#62117
 * 2024/03/21   Hitachi         T.Nagae         Update          QC#62117
 * </pre>
 */
public class NSAB004001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Number */
    private int commitNumber;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Sales Date */
    private String salesDate;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Execution Parameter */
    private S21SsmExecutionParameter ssmExecParam = null;

    // START 2022/06/21 R.jin [QC#60216,ADD]
    /**  cfsUplft */
    private String cfsUplft;
    // END 2022/06/21 R.jin [QC#60216,ADD]

    // START 2024/01/11 S.Moriai [QC#62117,ADD]
    /** System TimeStamp */
    private String sysTime = null;

    /** Mail Message*/
    private List<String> mailMsgList;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = null;
    // END 2024/01/11 S.Moriai [QC#62117,ADD]

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NSAB004001().executeBatch(NSAB004001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSZM0589E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NSZM0589E, new String[] {MSG_TXT_SALES_DATE });
        }

        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchSize(this.commitNumber);
        ssmExecParam.setMaxRows(0);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;

        // START 2022/06/21 R.jin [QC#60216,ADD]
        this.cfsUplft = ZYPCodeDataUtil.getVarCharConstValue(CFS_CONTR_PRC_UPLFT_FLG, this.glblCmpyCd);
        // END 2022/06/21 R.jin [QC#60216,ADD]

        // START 2024/01/11 S.Moriai [QC#62117,ADD]
        this.sysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.mailMsgList = new ArrayList<String>();
        // END 2024/01/11 S.Moriai [QC#62117,ADD]

    }

    @Override
    protected void mainRoutine() {
        // START 2024/01/11 S.Moriai [QC#62117,ADD]
        updateStartRead(DS_CONTR_CTRL_STS.ACTIVE);
        // END 2024/01/11 S.Moriai [QC#62117,ADD]
        updateContratStatus(DS_CONTR_CTRL_STS.ACTIVE);
        // START 2017/10/27 U.Kim [QC#21613,MOD]
        // updateContratStatus(DS_CONTR_CTRL_STS.EXPIRED);
        // updateContratStatus(DS_CONTR_CTRL_STS.TERMINATED);
        updateContratStatus(DS_CONTR_CTRL_STS.TERMINATED);
        updateContratStatus(DS_CONTR_CTRL_STS.EXPIRED);
        // END 2017/10/27 U.Kim [QC#21613,MOD]
        // START 2022/06/21 R.jin [QC#60216,ADD]
        updateBasePrcDealAmt();
        updateXsMtrAmtRate();
        // END 2022/06/21 R.jin [QC#60216,ADD]
        // START 2017/10/03 K.Kojima [QC#18417,ADD]
        updateContratStatusForDetail(DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO);
        updateContratStatusForDetail(DS_CONTR_CTRL_STS.RENEWAL_HOLD);
        // END 2017/10/03 K.Kojima [QC#18417,ADD]
        // START 2017/11/16 K.Kojima [QC#21886,ADD]
        updateContratStatusForDetail(DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO_SYSTEM_HOLD);
        updateContratStatusForDetail(DS_CONTR_CTRL_STS.RENEWAL_HOLD_SYSTEM_HOLD);
        // END 2017/11/16 K.Kojima [QC#21886,ADD]
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    private void updateContratStatus(String afterSts) {
        S21InfoLogOutput.println("updateContratStatus Method " + afterSts + " Mode Start");

        List<DsContrKeyBean> dsContrPkList;
        List<DS_CONTRTMsg> dsContrTMsgList = new ArrayList<DS_CONTRTMsg>();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<DS_CONTR_PRC_EFFTMsg> dsContrPrcEffTMsgList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
        // START 2016/02/26 T.Aoyagi [QC2305, ADD]
        List<CONTR_XS_COPYTMsg> delXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
        List<CONTR_XS_COPYTMsg> insXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
        List<CONTR_XS_COPYTMsg> updXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
        // END 2016/02/26 T.Aoyagi [QC2305, ADD]
        // Add start 09/04/2017 QC#20919
        List<DS_CONTR_PRC_EFF_MTRTMsg> updDsContrPrcEffMtrList = new ArrayList<DS_CONTR_PRC_EFF_MTRTMsg>();
        // Add end 09/04/2017 QC#20919
        // START 2017/10/27 U.Kim [QC#21613,ADD]
        List<BigDecimal> dsContrPkListForTerminateAndTracking = new ArrayList<BigDecimal>();
        // END 2017/10/27 U.Kim [QC#21613,ADD]

        /** Target Data */
        dsContrPkList = getDsContrPkList(afterSts);

        int totalCount = 0;
        int commitCount = 0;
        int updateCount = 0;
        int errCount = 0;
        int retCount = 0;
        String errFlg = ZYPConstant.FLG_OFF_0;

        for (DsContrKeyBean dsContrPkData : dsContrPkList) {
            totalCount++;
            commitCount++;
            BigDecimal dsContrPk = dsContrPkData.getDsContrPk();

            /** Set Update Parameter */
            if (dsContrPkData.getDsContrExistFlg().equals(ZYPConstant.FLG_ON_Y) && !afterSts.equals(DS_CONTR_CTRL_STS.TERMINATED)) {
                dsContrTMsgList = setDsContrTMsgList(afterSts, dsContrPk, dsContrTMsgList);
            }
            if (dsContrPkData.getDsContrDtlExistFlg().equals(ZYPConstant.FLG_ON_Y)) {
                dsContrDtlTMsgList = setDsContrDtlTMsgList(afterSts, dsContrPk, dsContrDtlTMsgList);
            }
            if (dsContrPkData.getDsContrBllgMtrExistFlg().equals(ZYPConstant.FLG_ON_Y)) {
                dsContrBllgMtrTMsgList = setDsContrBllgMtrTMsgList(afterSts, dsContrPk, dsContrBllgMtrTMsgList);
            }
            if (dsContrPkData.getDsContrPrcEffExistFlg().equals(ZYPConstant.FLG_ON_Y)) {
                dsContrPrcEffTMsgList = setDsContrPrcEffTMsgList(afterSts, dsContrPk, dsContrPrcEffTMsgList);
            }

            // START 2017/10/27 U.Kim [QC#21613,ADD]
            if (!dsContrPkListForTerminateAndTracking.contains(dsContrPk)) {
                dsContrPkListForTerminateAndTracking.add(dsContrPk);
            }
            // END 2017/10/27 U.Kim [QC#21613,ADD]

            /** Execute Update */
            if (commitCount == this.commitNumber || totalCount == dsContrPkList.size()) {
                // START 2016/02/26 T.Aoyagi [QC2305, ADD]
                if (dsContrPrcEffTMsgList.size() > 0 && afterSts.equals(DS_CONTR_CTRL_STS.ACTIVE)) {
                    updateDsContrDtlTMsgList(dsContrPrcEffTMsgList, dsContrDtlTMsgList);
                    // START 2017/08/21 M.Kidokoro [QC#20057, ADD]
                    updateDsContrBllgMtrTMsgList(dsContrPrcEffTMsgList, dsContrBllgMtrTMsgList);
                    // END 2017/08/21 M.Kidokoro [QC#20057, ADD]
                    setDelXsCopyList(dsContrPrcEffTMsgList, delXsCopyList);
                    // Mod start 09/04/2017 QC#20919
                    setInsAndUpdXsCopyList(dsContrPrcEffTMsgList, insXsCopyList, updXsCopyList, updDsContrPrcEffMtrList);
                    // Mod end 09/04/2017 QC#20919
                }
                // END 2016/02/26 T.Aoyagi [QC2305, ADD]

                if (dsContrTMsgList.size() > 0) {
                    DS_CONTRTMsg[] inMsgArray = new DS_CONTRTMsg[dsContrTMsgList.size()];
                    retCount = S21FastTBLAccessor.update(dsContrTMsgList.toArray(inMsgArray));
                    if (retCount != inMsgArray.length) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                    dsContrTMsgList = new ArrayList<DS_CONTRTMsg>();
                }
                if (dsContrDtlTMsgList.size() > 0 && errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                    DS_CONTR_DTLTMsg[] inMsgArray = new DS_CONTR_DTLTMsg[dsContrDtlTMsgList.size()];
                    retCount = S21FastTBLAccessor.update(dsContrDtlTMsgList.toArray(inMsgArray));
                    if (retCount != inMsgArray.length) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                    // Add Start 04/23/2018 <QC#23378>
                    if (UpdDsContrPrcAlloc(dsContrDtlTMsgList)) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                    // Add End   04/23/2018 <QC#23378>
                    // START 2019/05/22 [QC#50212,ADD]
                    if (DS_CONTR_CTRL_STS.TERMINATED.equals(afterSts)) {
                        if (!terminateDsSubContr(dsContrDtlTMsgList)) {
                            errFlg = ZYPConstant.FLG_ON_1;
                        }
                    }
                    // END 2019/05/22 [QC#50212,ADD]
                    dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
                }
                if (dsContrBllgMtrTMsgList.size() > 0 && errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                    DS_CONTR_BLLG_MTRTMsg[] inMsgArray = new DS_CONTR_BLLG_MTRTMsg[dsContrBllgMtrTMsgList.size()];
                    retCount = S21FastTBLAccessor.update(dsContrBllgMtrTMsgList.toArray(inMsgArray));
                    if (retCount != inMsgArray.length) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                    dsContrBllgMtrTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
                }
                if (dsContrPrcEffTMsgList.size() > 0 && errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                    DS_CONTR_PRC_EFFTMsg[] inMsgArray = new DS_CONTR_PRC_EFFTMsg[dsContrPrcEffTMsgList.size()];
                    retCount = S21FastTBLAccessor.update(dsContrPrcEffTMsgList.toArray(inMsgArray));
                    if (retCount != inMsgArray.length) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                    dsContrPrcEffTMsgList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
                }
                // START 2016/02/26 T.Aoyagi [QC2305, ADD]
                if (delXsCopyList.size() > 0 && errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                    CONTR_XS_COPYTMsg[] inMsgArray = new CONTR_XS_COPYTMsg[delXsCopyList.size()];
                    retCount = S21FastTBLAccessor.removePhysical(delXsCopyList.toArray(inMsgArray));
                    if (retCount != inMsgArray.length) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                    delXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
                }
                if (insXsCopyList.size() > 0 && errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                    CONTR_XS_COPYTMsg[] inMsgArray = new CONTR_XS_COPYTMsg[insXsCopyList.size()];
                    retCount = S21FastTBLAccessor.insert(insXsCopyList.toArray(inMsgArray));
                    if (retCount != inMsgArray.length) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                    insXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
                }
                if (updXsCopyList.size() > 0 && errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                    CONTR_XS_COPYTMsg[] inMsgArray = new CONTR_XS_COPYTMsg[updXsCopyList.size()];
                    retCount = S21FastTBLAccessor.update(updXsCopyList.toArray(inMsgArray));
                    if (retCount != inMsgArray.length) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                    updXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
                }
                // END 2016/02/26 T.Aoyagi [QC2305, ADD]
                // Add start 09/04/2017 QC#20919
                if (updDsContrPrcEffMtrList.size() > 0 && errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                    DS_CONTR_PRC_EFF_MTRTMsg[] inMsgArray = new DS_CONTR_PRC_EFF_MTRTMsg[updDsContrPrcEffMtrList.size()];
                    retCount = S21FastTBLAccessor.update(updDsContrPrcEffMtrList.toArray(inMsgArray));
                    if (retCount != inMsgArray.length) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                    updDsContrPrcEffMtrList = new ArrayList<DS_CONTR_PRC_EFF_MTRTMsg>();
                }
                // Add end 09/04/2017 QC#20919

                // add start 2016/09/28 CSA Defect#14674
                if (DS_CONTR_CTRL_STS.TERMINATED.equals(afterSts)) {
                    // START 2017/10/27 U.Kim [QC#21613,MOD]
                    // List<BigDecimal> effDsContrDtlList = getEffectiveDsContrDtl(dsContrPk);
                    // if (effDsContrDtlList == null || effDsContrDtlList.isEmpty()) {
                    //     if (!terminateDsContr(dsContrPk)) {
                    //         errFlg = ZYPConstant.FLG_ON_1;
                    //     }
                    // }
                    for (BigDecimal dsContrPkForTerminate : dsContrPkListForTerminateAndTracking) {
                        List<BigDecimal> effDsContrDtlList = getEffectiveDsContrDtl(dsContrPkForTerminate);
                        if (effDsContrDtlList == null || effDsContrDtlList.isEmpty()) {
                            if (!terminateDsContr(dsContrPkForTerminate)) {
                                errFlg = ZYPConstant.FLG_ON_1;
                            }
                        }
                        // START 2019/05/22 [QC#50212,DEL]
                        // // START 2018/05/15 M.Naito [QC#21679, ADD]
                        // List<DS_SUB_CONTRTMsg> dsSubContrList = getDsSubContrList(dsContrPkForTerminate);
                        // for (DS_SUB_CONTRTMsg dsSubContrTMsg : dsSubContrList) {
                        //     // Call NSZC0520:Sub Contract Update API
                        //     NSZC052001PMsg nszc052001PMsg = new NSZC052001PMsg();
                        //     setParamForNSZC052001(nszc052001PMsg, dsSubContrTMsg);
                        //     NSZC052001 apiNSZC052001 = new NSZC052001();
                        //     apiNSZC052001.execute(nszc052001PMsg, ONBATCH_TYPE.BATCH);
                        // }
                        // // END 2018/05/15 M.Naito [QC#21679, ADD]
                        // END 2019/05/22 [QC#50212,DEL]
                    }
                    // END 2017/10/27 U.Kim [QC#21613,MOD]
                }
                // add end 2016/09/28 CSA Defect#14674
                // START 2018/05/14 U.Kim [QC#24854, ADD]
                if(!callSumAggregateAPI(dsContrPk)){
                    errFlg = ZYPConstant.FLG_ON_1;
                }
                // END 2018/05/14 U.Kim [QC#24854, ADD]

                // add start 2016/06/06 CSA Defect#1523, 4624
                // START 2017/10/27 U.Kim [QC#21613, MOD]
                // callContrTrkAPI(dsContrPk, afterSts);
                for (BigDecimal dsContrPkForTracking : dsContrPkListForTerminateAndTracking) {
                    callContrTrkAPI(dsContrPkForTracking, afterSts);
                }
                // END 2017/10/27 U.Kim [QC#21613, MOD]
                // add end 2016/06/06 CSA Defect#1523, 4624

                // START 2018/01/25 K.Kojima [QC#23767,ADD]
                dsContrPkListForTerminateAndTracking = new ArrayList<BigDecimal>();
                // END 2018/01/25 K.Kojima [QC#23767,ADD]

                /** Result */
                if (errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                    this.normalCount += commitCount;
                    updateCount += commitCount;
                    commit();
                } else {
                    this.errorCount += commitCount;
                    errorCount += commitCount;
                    rollback();
                }
                commitCount = 0;
                // START 2018/04/02 K.Kojima [QC#25199,ADD]
                errFlg = ZYPConstant.FLG_OFF_0;
                if (dsContrDtlTMsgList.size() > 0) {
                    dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
                }
                if (dsContrBllgMtrTMsgList.size() > 0) {
                    dsContrBllgMtrTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
                }
                if (dsContrPrcEffTMsgList.size() > 0) {
                    dsContrPrcEffTMsgList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
                }
                if (delXsCopyList.size() > 0) {
                    delXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
                }
                if (insXsCopyList.size() > 0) {
                    insXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
                }
                if (updXsCopyList.size() > 0) {
                    updXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
                }
                if (updDsContrPrcEffMtrList.size() > 0) {
                    updDsContrPrcEffMtrList = new ArrayList<DS_CONTR_PRC_EFF_MTRTMsg>();
                }
                // END 2018/04/02 K.Kojima [QC#25199,ADD]
            }
        }
        S21InfoLogOutput.println("update count is " + updateCount + ".");
        S21InfoLogOutput.println("error  count is " + errCount + ".");
        S21InfoLogOutput.println("updateContratStatus Method " + afterSts + " Mode End");
    }

    private List<DsContrKeyBean> getDsContrPkList(String afterSts) {
        List<DsContrKeyBean> keyList = new ArrayList<DsContrKeyBean>();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String stmtId = null;
            boolean installedFlg = true;
            Map<String, String> param = new HashMap<String, String>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("slsDt", this.salesDate);
            if (afterSts.equals(DS_CONTR_CTRL_STS.ACTIVE)) {
                param.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.SINGED);
                // START 2022/06/21 R.jin [QC#60216,ADD]
                param.put("dsContrStsCd", DS_CONTR_STS.APPROVED);
                param.put("cfsUplft", this.cfsUplft);
                // END 2022/06/21 R.jin [QC#60216,ADD]
                stmtId = DS_CONTR_ACTIVE;
            } else if (afterSts.equals(DS_CONTR_CTRL_STS.EXPIRED)) {
                param.put("dsContrStsCd", DS_CONTR_STS.EFFECTIVE);
                param.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.ACTIVE);
                stmtId = DS_CONTR_EXPIRED;
            } else if (afterSts.equals(DS_CONTR_CTRL_STS.TERMINATED)) {
                param.put("dsContrCtrlStsCd1", DS_CONTR_CTRL_STS.SINGED);
                param.put("dsContrCtrlStsCd2", DS_CONTR_CTRL_STS.ACTIVE);
                stmtId = DS_CONTR_TERMINATED;
            }
            ps = this.ssmLLClient.createPreparedStatement(stmtId, param, ssmExecParam);
            rs = ps.executeQuery();
            while (rs.next()) {
// START 2018/06/13 T.Wada QC#26592 Comment Out Start
//                // START 2017/10/06 K.Kojima [QC#18534-1,ADD]
//                installedFlg = true;
//                if (afterSts.equals(DS_CONTR_CTRL_STS.ACTIVE)) {
//                // END 2017/10/06 K.Kojima [QC#18534-1,ADD]
//                    // START 2017/09/14 M.Naito [QC#18534,MOD]
//                    // get SVC_MACH_MSTR_PK
//                    DS_CONTR_DTLTMsg inTMsg = new DS_CONTR_DTLTMsg();
//                    inTMsg.setSQLID("001");
//                    inTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
//                    inTMsg.setConditionValue("dsContrPk01", rs.getBigDecimal("DS_CONTR_PK"));
//
//                    DS_CONTR_DTLTMsgArray outDtlTMsgArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
//
//                    for (int i = 0; i < outDtlTMsgArray.getValidCount(); i++) {
//                        DS_CONTR_DTLTMsg dsContrDtlTMsg = outDtlTMsgArray.no(i);
//                        // check Not Installed Machine in Contract
//                        if (ZYPCommonFunc.hasValue(dsContrDtlTMsg.svcMachMstrPk) && !NSXC001001ContrValidation.checkMachineInstalled(getGlobalCompanyCode(), dsContrDtlTMsg.svcMachMstrPk.getValue())) {
//                            installedFlg = false;
//                        }
//                    }
//                    if (!installedFlg) {
//                        continue;
//                    }
//                    // END 2017/09/14 M.Naito [QC#18534,MOD]
//                // START 2017/10/06 K.Kojima [QC#18534-1,ADD]
//                }
//                // END 2017/10/06 K.Kojima [QC#18534-1,ADD]
// END 2018/06/13 T.Wada QC#26592 Comment Out End

                //START 2017/08/01 E.Kameishi [QC#18882,ADD]
                // check Not Arrived Machine in Fleet Contract
                BigDecimal notArvMachCnt = NSXC001001GetNotArvMachCntForFlt.getNotArvMachCnt(getGlobalCompanyCode(), rs.getBigDecimal("DS_CONTR_PK"));
                if (BigDecimal.ZERO.compareTo(notArvMachCnt) >= 0) {
                    DsContrKeyBean dsContrKeyLine = new DsContrKeyBean();
                    dsContrKeyLine.setGlblCmpyCd(rs.getString("GLBL_CMPY_CD"));
                    dsContrKeyLine.setDsContrPk(rs.getBigDecimal("DS_CONTR_PK"));
                    dsContrKeyLine.setDsContrExistFlg(rs.getString("DS_CONTR_EXIST_FLG"));
                    dsContrKeyLine.setDsContrDtlExistFlg(rs.getString("DS_CONTR_DTL_EXIST_FLG"));
                    dsContrKeyLine.setDsContrBllgMtrExistFlg(rs.getString("DS_CONTR_BLLG_MTR_EXIST_FLG"));
                    dsContrKeyLine.setDsContrPrcEffExistFlg(rs.getString("DS_CONTR_PRC_EFF_EXIST_FLG"));
                    keyList.add(dsContrKeyLine);
                }
                //END 2017/08/01 E.Kameishi [QC#18882,ADD]
            }
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return keyList;
    }

    private List<DS_CONTRTMsg> setDsContrTMsgList(String afterSts, BigDecimal dsContrPk, List<DS_CONTRTMsg> dsContrList) {
        DS_CONTRTMsg dsContrTmsg;
        dsContrTmsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTmsg.dsContrPk, dsContrPk);
        dsContrTmsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsContrTmsg);

        if (afterSts.equals(DS_CONTR_CTRL_STS.ACTIVE)) {
            ZYPEZDItemValueSetter.setValue(dsContrTmsg.dsContrStsCd, DS_CONTR_STS.EFFECTIVE);
        } else if (afterSts.equals(DS_CONTR_CTRL_STS.EXPIRED)) {
            ZYPEZDItemValueSetter.setValue(dsContrTmsg.dsContrStsCd, DS_CONTR_STS.EXPIRED);
        }

        dsContrList.add(dsContrTmsg);
        return dsContrList;
    }

    // START 2017/10/03 K.Kojima [QC#18417,ADD]
    private List<DS_CONTR_DTLTMsg> setDsContrDtlTMsgListForRenewalHold(String afterSts, BigDecimal dsContrDtlPk, List<DS_CONTR_DTLTMsg> dsContrDtlList) {
        DS_CONTR_DTLTMsg dsContrDtlTmsg;
        dsContrDtlTmsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.dsContrDtlPk, dsContrDtlPk);
        dsContrDtlTmsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsContrDtlTmsg);

        if (afterSts.equals(DS_CONTR_CTRL_STS.RENEWAL_HOLD)) {
            ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD);
        } else if (afterSts.equals(DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO)) {
            ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
        // START 2017/11/16 K.Kojima [QC#21886,ADD]
        } else if (afterSts.equals(DS_CONTR_CTRL_STS.RENEWAL_HOLD_SYSTEM_HOLD)) {
            ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD);
        } else if (afterSts.equals(DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO_SYSTEM_HOLD)) {
            ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
        // END 2017/11/16 K.Kojima [QC#21886,ADD]
        }

        dsContrDtlList.add(dsContrDtlTmsg);
        return dsContrDtlList;
    }

    // END 2017/10/03 K.Kojima [QC#18417,ADD]

    private List<DS_CONTR_DTLTMsg> setDsContrDtlTMsgList(String afterSts, BigDecimal dsContrPk, List<DS_CONTR_DTLTMsg> dsContrDtlList) {

        DS_CONTR_DTLTMsg dsContrDtlTmsg;
        String stmtId = null;

        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsContrPk", dsContrPk);
            param.put("slsDt", this.salesDate);

            if (afterSts.equals(DS_CONTR_CTRL_STS.ACTIVE)) {
                stmtId = DS_CONTR_DTL_ACTIVE;
                param.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.SINGED);
            } else if (afterSts.equals(DS_CONTR_CTRL_STS.EXPIRED)) {
                stmtId = DS_CONTR_DTL_EXPIRED;
                param.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.ACTIVE);
            } else if (afterSts.equals(DS_CONTR_CTRL_STS.TERMINATED)) {
                stmtId = DS_CONTR_DTL_TERMINATED;
                param.put("dsContrCtrlStsCd1", DS_CONTR_CTRL_STS.SINGED);
                param.put("dsContrCtrlStsCd2", DS_CONTR_CTRL_STS.ACTIVE);
            }

            ps = this.ssmLLClient.createPreparedStatement(stmtId, param, ssmExecParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                dsContrDtlTmsg = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
                dsContrDtlTmsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsContrDtlTmsg);

                if (afterSts.equals(DS_CONTR_CTRL_STS.ACTIVE)) {
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.dsContrDtlStsCd, DS_CONTR_CTRL_STS.ACTIVE);
                } else if (afterSts.equals(DS_CONTR_CTRL_STS.EXPIRED)) {
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.dsContrDtlStsCd, DS_CONTR_CTRL_STS.EXPIRED);
                } else if (afterSts.equals(DS_CONTR_CTRL_STS.TERMINATED)) {
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.dsContrDtlStsCd, DS_CONTR_CTRL_STS.TERMINATED);
                }
                dsContrDtlList.add(dsContrDtlTmsg);
            }
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return dsContrDtlList;
    }

    private List<DS_CONTR_BLLG_MTRTMsg> setDsContrBllgMtrTMsgList(String afterSts, BigDecimal dsContrPk, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList) {

        DS_CONTR_BLLG_MTRTMsg dsContrBllgMgrTmsg;
        String stmtId = null;

        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsContrPk", dsContrPk);
            param.put("slsDt", this.salesDate);

            if (afterSts.equals(DS_CONTR_CTRL_STS.ACTIVE)) {
                stmtId = DS_CONTR_BLLG_MTR_ACTIVE;
                param.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.SINGED);
            } else if (afterSts.equals(DS_CONTR_CTRL_STS.EXPIRED)) {
                stmtId = DS_CONTR_BLLG_MTR_EXPIRED;
                param.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.ACTIVE);
            } else if (afterSts.equals(DS_CONTR_CTRL_STS.TERMINATED)) {
                stmtId = DS_CONTR_BLLG_MTR_TERMINATED;
                param.put("dsContrCtrlStsCd1", DS_CONTR_CTRL_STS.SINGED);
                param.put("dsContrCtrlStsCd2", DS_CONTR_CTRL_STS.ACTIVE);
            }

            ps = this.ssmLLClient.createPreparedStatement(stmtId, param, ssmExecParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                dsContrBllgMgrTmsg = new DS_CONTR_BLLG_MTRTMsg();
                ZYPEZDItemValueSetter.setValue(dsContrBllgMgrTmsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(dsContrBllgMgrTmsg.dsContrBllgMtrPk, rs.getBigDecimal("DS_CONTR_BLLG_MTR_PK"));
                dsContrBllgMgrTmsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsContrBllgMgrTmsg);

                if (afterSts.equals(DS_CONTR_CTRL_STS.ACTIVE)) {
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMgrTmsg.dsContrBllgMtrStsCd, DS_CONTR_CTRL_STS.ACTIVE);
                } else if (afterSts.equals(DS_CONTR_CTRL_STS.EXPIRED)) {
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMgrTmsg.dsContrBllgMtrStsCd, DS_CONTR_CTRL_STS.EXPIRED);
                } else if (afterSts.equals(DS_CONTR_CTRL_STS.TERMINATED)) {
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMgrTmsg.dsContrBllgMtrStsCd, DS_CONTR_CTRL_STS.TERMINATED);
                }

                dsContrBllgMtrList.add(dsContrBllgMgrTmsg);
            }
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return dsContrBllgMtrList;
    }

    private List<DS_CONTR_PRC_EFFTMsg> setDsContrPrcEffTMsgList(String afterSts, BigDecimal dsContrPk, List<DS_CONTR_PRC_EFFTMsg> dsContrPrcEffList) {

        DS_CONTR_PRC_EFFTMsg dsContrPrcEffTmsg;
        String stmtId = null;

        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsContrPk", dsContrPk);
            param.put("slsDt", this.salesDate);

            if (afterSts.equals(DS_CONTR_CTRL_STS.ACTIVE)) {
                stmtId = DS_CONTR_PRC_EFF_ACTIVE;
                param.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.SINGED);
                // START 2022/06/21 R.jin [QC#60216,ADD]
                param.put("cfsUplft", this.cfsUplft);
                // END 2022/06/21 R.jin [QC#60216,ADD]
            } else if (afterSts.equals(DS_CONTR_CTRL_STS.EXPIRED)) {
                stmtId = DS_CONTR_PRC_EFF_EXPIRED;
                param.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.ACTIVE);
            } else if (afterSts.equals(DS_CONTR_CTRL_STS.TERMINATED)) {
                stmtId = DS_CONTR_PRC_EFF_TERMINATED;
                param.put("dsContrCtrlStsCd1", DS_CONTR_CTRL_STS.SINGED);
                param.put("dsContrCtrlStsCd2", DS_CONTR_CTRL_STS.ACTIVE);
            }

            ps = this.ssmLLClient.createPreparedStatement(stmtId, param, ssmExecParam);
            rs = ps.executeQuery();
            while (rs.next()) {
                dsContrPrcEffTmsg = new DS_CONTR_PRC_EFFTMsg();
                ZYPEZDItemValueSetter.setValue(dsContrPrcEffTmsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(dsContrPrcEffTmsg.dsContrPrcEffPk, rs.getBigDecimal("DS_CONTR_PRC_EFF_PK"));
                dsContrPrcEffTmsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsContrPrcEffTmsg);

                if (afterSts.equals(DS_CONTR_CTRL_STS.ACTIVE)) {
                    ZYPEZDItemValueSetter.setValue(dsContrPrcEffTmsg.dsContrPrcEffStsCd, DS_CONTR_CTRL_STS.ACTIVE);
                } else if (afterSts.equals(DS_CONTR_CTRL_STS.EXPIRED)) {
                    ZYPEZDItemValueSetter.setValue(dsContrPrcEffTmsg.dsContrPrcEffStsCd, DS_CONTR_CTRL_STS.EXPIRED);
                } else if (afterSts.equals(DS_CONTR_CTRL_STS.TERMINATED)) {
                    ZYPEZDItemValueSetter.setValue(dsContrPrcEffTmsg.dsContrPrcEffStsCd, DS_CONTR_CTRL_STS.TERMINATED);
                }

                dsContrPrcEffList.add(dsContrPrcEffTmsg);
            }
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return dsContrPrcEffList;
    }

    // START 2016/02/26 T.Aoyagi [QC2305, ADD]
    private void updateDsContrDtlTMsgList(List<DS_CONTR_PRC_EFFTMsg> dsContrPrcEffTMsgList, List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList) {

        DS_CONTR_DTLTMsg dsContrDtlTmsg;

        for (DS_CONTR_PRC_EFFTMsg prcEffTMsg : dsContrPrcEffTMsgList) {

            if (!ZYPConstant.FLG_ON_Y.equals(prcEffTMsg.baseChrgFlg.getValue())) {
                continue;
            }

            int idx = getContrDtlIdx(dsContrDtlTMsgList, prcEffTMsg.dsContrDtlPk.getValue());
            if (idx > -1) {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgList.get(idx).basePrcDealAmt, prcEffTMsg.basePrcDealAmt);
                // START 2018/06/13 K.Kojima [QC#26211,DEL]
                // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgList.get(idx).basePrcTermDealAmtRate, prcEffTMsg.basePrcTermDealAmtRate);
                // END 2018/06/13 K.Kojima [QC#26211,DEL]
                // START 2017/08/21 M.Kidokoro [QC#20057, ADD]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgList.get(idx).baseBllgCycleCd, prcEffTMsg.bllgCycleCd.getValue());
                // END 2017/08/21 M.Kidokoro [QC#20057, ADD]
            } else {
                // not found
                dsContrDtlTmsg = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.glblCmpyCd, prcEffTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.dsContrDtlPk, prcEffTMsg.dsContrDtlPk);
                dsContrDtlTmsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsContrDtlTmsg);

                ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.basePrcDealAmt, prcEffTMsg.basePrcDealAmt);
                // START 2018/06/13 K.Kojima [QC#26211,DEL]
                // ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.basePrcTermDealAmtRate, prcEffTMsg.basePrcTermDealAmtRate);
                // END 2018/06/13 K.Kojima [QC#26211,DEL]
                // START 2017/08/21 M.Kidokoro [QC#20057, ADD]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.baseBllgCycleCd, prcEffTMsg.bllgCycleCd.getValue());
                // END 2017/08/21 M.Kidokoro [QC#20057, ADD]
                dsContrDtlTMsgList.add(dsContrDtlTmsg);
            }
        }
    }

    private int getContrDtlIdx(List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList, BigDecimal dsContrDtlPk) {

        for (int i = 0; i < dsContrDtlTMsgList.size(); i++) {
            if (dsContrDtlTMsgList.get(i).dsContrDtlPk.getValue().compareTo(dsContrDtlPk) == 0) {
                return i;
            }
        }
        // not found
        return -1;
    }

    private void setDelXsCopyList(List<DS_CONTR_PRC_EFFTMsg> dsContrPrcEffTMsgList, List<CONTR_XS_COPYTMsg> delXsCopyList) {

        CONTR_XS_COPYTMsg delXsCopyTMsg;

        for (DS_CONTR_PRC_EFFTMsg prcEffTMsg : dsContrPrcEffTMsgList) {

            if (!ZYPConstant.FLG_ON_Y.equals(prcEffTMsg.usgChrgFlg.getValue())) {
                continue;
            }
            DS_CONTR_PRC_EFF_MTRTMsgArray prcEffMtrAry = getPrcEffTMsgArray(prcEffTMsg.dsContrPrcEffPk.getValue(), prcEffTMsg.dsContrBllgMtrPk.getValue());
            CONTR_XS_COPYTMsgArray xsCopyAry = getXsCopyTMsgArray(prcEffTMsg.dsContrBllgMtrPk.getValue());

            for (int i = 0; i < xsCopyAry.getValidCount(); i++) {
                boolean isExist = false;
                for (int j = 0; j < prcEffMtrAry.getValidCount(); j++) {

                    // Add start 09/04/2017 QC#20919
                    if (!ZYPCommonFunc.hasValue(prcEffMtrAry.no(j).contrXsCopyPk)) {
                        continue;
                    }
                    // Add end 09/04/2017 QC#20919
                    if (xsCopyAry.no(i).contrXsCopyPk.getValue().compareTo(prcEffMtrAry.no(j).contrXsCopyPk.getValue()) == 0) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    delXsCopyTMsg = new CONTR_XS_COPYTMsg();
                    EZDMsg.copy(xsCopyAry.no(i), null, delXsCopyTMsg, null);
                    delXsCopyList.add(delXsCopyTMsg);
                }
            }
        }
    }

    // Mod start 09/04/2017 QC#20919
    private void setInsAndUpdXsCopyList(List<DS_CONTR_PRC_EFFTMsg> dsContrPrcEffTMsgList, List<CONTR_XS_COPYTMsg> insXsCopyList, List<CONTR_XS_COPYTMsg> updXsCopyList, List<DS_CONTR_PRC_EFF_MTRTMsg> updDsContrPrcEffMtrList) {
    // Mod end 09/04/2017 QC#20919

        CONTR_XS_COPYTMsg xsCopyTMsg;

        for (DS_CONTR_PRC_EFFTMsg prcEffTMsg : dsContrPrcEffTMsgList) {

            if (!ZYPConstant.FLG_ON_Y.equals(prcEffTMsg.usgChrgFlg.getValue())) {
                continue;
            }
            DS_CONTR_PRC_EFF_MTRTMsgArray prcEffMtrAry = getPrcEffTMsgArray(prcEffTMsg.dsContrPrcEffPk.getValue(), prcEffTMsg.dsContrBllgMtrPk.getValue());
            CONTR_XS_COPYTMsgArray xsCopyAry = getXsCopyTMsgArray(prcEffTMsg.dsContrBllgMtrPk.getValue());

            for (int i = 0; i < prcEffMtrAry.getValidCount(); i++) {
                boolean isExist = false;
                DS_CONTR_PRC_EFF_MTRTMsg prcEffMtr = prcEffMtrAry.no(i);

                for (int j = 0; j < xsCopyAry.getValidCount(); j++) {
                    CONTR_XS_COPYTMsg xsCopy = xsCopyAry.no(j);

                    // Add start 09/04/2017 QC#20919
                    if (!ZYPCommonFunc.hasValue(prcEffMtr.contrXsCopyPk)) {
                        continue;
                    }
                    // Add end 09/04/2017 QC#20919

                    if (xsCopy.contrXsCopyPk.getValue().compareTo(prcEffMtr.contrXsCopyPk.getValue()) == 0) {

                        if (xsCopy.xsMtrCopyQty.getValue().compareTo(prcEffMtr.xsMtrCopyQty.getValue()) != 0
                                || xsCopy.xsMtrAmtRate.getValue().compareTo(prcEffMtr.xsMtrAmtRate.getValue()) != 0) {

                            xsCopyTMsg = new CONTR_XS_COPYTMsg();
                            ZYPEZDItemValueSetter.setValue(xsCopyTMsg.glblCmpyCd, prcEffMtr.glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(xsCopyTMsg.contrXsCopyPk, prcEffMtr.contrXsCopyPk);
                            xsCopyTMsg = (CONTR_XS_COPYTMsg) S21FastTBLAccessor.findByKeyForUpdate(xsCopyTMsg);

                            ZYPEZDItemValueSetter.setValue(xsCopyTMsg.xsMtrCopyQty, prcEffMtr.xsMtrCopyQty);
                            ZYPEZDItemValueSetter.setValue(xsCopyTMsg.xsMtrAmtRate, prcEffMtr.xsMtrAmtRate);
                            updXsCopyList.add(xsCopyTMsg);
                        }
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    xsCopyTMsg = new CONTR_XS_COPYTMsg();
                    EZDMsg.copy(prcEffMtr, null, xsCopyTMsg, null);
                    // Add start 09/04/2017 QC#20919
                    if (!ZYPCommonFunc.hasValue(xsCopyTMsg.contrXsCopyPk)) {
                        ZYPEZDItemValueSetter.setValue(xsCopyTMsg.contrXsCopyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_XS_COPY_SQ));

                        ZYPEZDItemValueSetter.setValue(prcEffMtr.contrXsCopyPk, xsCopyTMsg.contrXsCopyPk);
                        updDsContrPrcEffMtrList.add(prcEffMtr);
                    }
                    // Add end 09/04/2017 QC#20919
                    insXsCopyList.add(xsCopyTMsg);
                }
            }
        }
    }

    private DS_CONTR_PRC_EFF_MTRTMsgArray getPrcEffTMsgArray(BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_PRC_EFF_MTRTMsg inMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrPrcEffPk01", dsContrPrcEffPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setSQLID("002");
        return (DS_CONTR_PRC_EFF_MTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private CONTR_XS_COPYTMsgArray getXsCopyTMsgArray(BigDecimal dsContrBllgMtrPk) {
        CONTR_XS_COPYTMsg inMsg = new CONTR_XS_COPYTMsg();
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setSQLID("002");
        return (CONTR_XS_COPYTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // END 2016/02/26 T.Aoyagi [QC2305, ADD]

    // add end 2016/06/06 CSA Defect#1523, 4624
    private void callContrTrkAPI(BigDecimal dsContrPk, String dsContrCtrlStsCd) {
        String userId = this.getClass().getSimpleName();
        String xxModeCode = ContrTrkProcMode.CONTRACT_MODE_CHANGE.code;
        if (DS_CONTR_CTRL_STS.ACTIVE.equals(dsContrCtrlStsCd)) {
            xxModeCode = ContrTrkProcMode.AUTO_APPROVAL.code;
        }
        // START 2017/09/11 K.Kojima [QC#18435,MOD]
        // if (!NSXC001001ContractTracking.callContrTrk(this.glblCmpyCd, xxModeCode, dsContrPk, userId, this.salesDate, null, null, ONBATCH_TYPE.BATCH)) {
        String slsDate = this.salesDate;
        if (DS_CONTR_CTRL_STS.EXPIRED.equals(dsContrCtrlStsCd)) {
            slsDate = null;
        }
        if (!NSXC001001ContractTracking.callContrTrk(this.glblCmpyCd, xxModeCode, dsContrPk, userId, slsDate, null, null, ONBATCH_TYPE.BATCH)) {
        // END 2017/09/11 K.Kojima [QC#18435,MOD]
            DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
            tMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                String msgTxt = S21MessageFunc.clspGetMessage(NSXC001001ContractTracking.ERR_MSG_ID);
                S21InfoLogOutput.println(ZYPCommonFunc.concatString(msgTxt, " :Contract#: ", tMsg.dsContrNum.getValue()));
            }
        }
    }
    // add end 2016/06/06 CSA Defect#1523, 4624
    // add start 2016/09/28 CSA Defect#14674
    private List<BigDecimal> getEffectiveDsContrDtl(BigDecimal dsContrPk) {

        List<BigDecimal> effDsContrDtlList = new ArrayList<BigDecimal>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsContrPk", dsContrPk);
            param.put("cancelled", DS_CONTR_CTRL_STS.CANCELLED);
            param.put("terminated", DS_CONTR_CTRL_STS.TERMINATED);
            param.put("expired", DS_CONTR_CTRL_STS.EXPIRED);
            // START 2022/07/12 R.Jin [QC#60276, DEL]
            // add start 2019/05/15 K.Fujimoto CSAQC#50279
//            param.put("terminatedHold", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
            // add End 2019/05/15 K.Fujimoto CSAQC#50279
         // START 2022/07/12 R.Jin [QC#60276, DEL]
            // START 2023/02/13 R.Jin [QC#61172, ADD]
            param.put("shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
            // END 2022/07/12 R.Jin [QC#61172, ADD]
            ps = this.ssmLLClient.createPreparedStatement("getEffectiveDsContrDtl", param, ssmExecParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                effDsContrDtlList.add(rs.getBigDecimal("DS_CONTR_DTL_PK"));
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        return effDsContrDtlList;
    }

    private boolean terminateDsContr(BigDecimal dsContrPk) {
        DS_CONTRTMsg dsContrTmsg;
        dsContrTmsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTmsg.dsContrPk, dsContrPk);
        dsContrTmsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsContrTmsg);

        ZYPEZDItemValueSetter.setValue(dsContrTmsg.dsContrStsCd, DS_CONTR_STS.TERMINATED);
        // START 2022/07/12 R.Jin [QC#60276, DEL]
//        ZYPEZDItemValueSetter.setValue(dsContrTmsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(dsContrTmsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(dsContrTmsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(dsContrTmsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
        // END 2022/07/12 R.Jin [QC#60276, DEL]
        S21FastTBLAccessor.update(dsContrTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrTmsg.getReturnCode())) {
            return false;
        }
        return true;
    }
    // add end 2016/09/28 CSA Defect#14674

    // START 2017/08/21 M.Kidokoro [QC#20057, ADD]
    /**
     * updateDsContrBllgMtrTMsgList
     * 
     * @param dsContrPrcEffTMsgList List<DS_CONTR_PRC_EFFTMsg>
     * @param dsContrBllgMtrTMsgList List<DS_CONTR_BLLG_MTRTMsg>
     */
    private void updateDsContrBllgMtrTMsgList(List<DS_CONTR_PRC_EFFTMsg> dsContrPrcEffTMsgList, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgList) {

        DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTmsg;

        for (DS_CONTR_PRC_EFFTMsg prcEffTMsg : dsContrPrcEffTMsgList) {

            if (!ZYPConstant.FLG_ON_Y.equals(prcEffTMsg.usgChrgFlg.getValue())) {
                continue;
            }

            int idx = getContrBllgMtrIdx(dsContrBllgMtrTMsgList, prcEffTMsg.dsContrDtlPk.getValue());
            if (idx > -1) {
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsgList.get(idx).bllgMtrBllgCycleCd, prcEffTMsg.bllgCycleCd);
            } else {
                // not found
                dsContrBllgMtrTmsg = new DS_CONTR_BLLG_MTRTMsg();
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTmsg.glblCmpyCd, prcEffTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTmsg.dsContrBllgMtrPk, prcEffTMsg.dsContrBllgMtrPk);
                dsContrBllgMtrTmsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsContrBllgMtrTmsg);

                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTmsg.bllgMtrBllgCycleCd, prcEffTMsg.bllgCycleCd);
                dsContrBllgMtrTMsgList.add(dsContrBllgMtrTmsg);
            }
        }
    }

    /**
     * getContrBllgMtrIdx
     * 
     * @param dsContrBllgMtrTMsgList List<DS_CONTR_BLLG_MTRTMsg>
     * @param dsContrDtlPk BigDecimal
     * @return int
     */
    private int getContrBllgMtrIdx(List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgList, BigDecimal dsContrDtlPk) {

        for (int i = 0; i < dsContrBllgMtrTMsgList.size(); i++) {
            if (dsContrBllgMtrTMsgList.get(i).dsContrDtlPk.getValue().compareTo(dsContrDtlPk) == 0) {
                return i;
            }
        }
        // not found
        return -1;
    }
    // END 2017/08/21 M.Kidokoro [QC#20057, ADD]

    // START 2017/10/03 K.Kojima [QC#18417,ADD]
    private void updateContratStatusForDetail(String targetSts) {
        S21InfoLogOutput.println("updateContratStatusForDetail Method " + targetSts + " Mode Start");

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();

        int totalCount = 0;
        int commitCount = 0;
        int updateCount = 0;
        int errCount = 0;
        int retCount = 0;
        String errFlg = ZYPConstant.FLG_OFF_0;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("slsDt", this.salesDate);
            param.put("targetSts", targetSts);
            param.put("dsContrStsCdActive", DS_CONTR_STS.EFFECTIVE);
            param.put("dsContrDtlStsCdActive", DS_CONTR_DTL_STS.ACTIVE);
            param.put("dsContrDtlTpCdFleet", DS_CONTR_DTL_TP.FLEET);
            ps = this.ssmLLClient.createPreparedStatement("getDsContrDtlPKForRenewalHold", param, ssmExecParam);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalCount++;
                commitCount++;
                BigDecimal dsContrDtlPk = rs.getBigDecimal("DS_CONTR_DTL_PK");
                dsContrDtlTMsgList = setDsContrDtlTMsgListForRenewalHold(targetSts, dsContrDtlPk, dsContrDtlTMsgList);

                /** Execute Update */
                if (commitCount == this.commitNumber || totalCount == dsContrDtlTMsgList.size()) {
                    DS_CONTR_DTLTMsg[] inMsgArray = new DS_CONTR_DTLTMsg[dsContrDtlTMsgList.size()];
                    retCount = S21FastTBLAccessor.update(dsContrDtlTMsgList.toArray(inMsgArray));
                    if (retCount != inMsgArray.length) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                    for (int i = 0; i < dsContrDtlTMsgList.size(); i++) {
                        if (!callContractTrackingAPIforRenewalHold(dsContrDtlTMsgList.get(i).dsContrPk.getValue(), dsContrDtlTMsgList.get(i).dsContrDtlPk.getValue())) {
                            errFlg = ZYPConstant.FLG_ON_1;
                        }
                    }
                    dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
                    /** Result */
                    if (errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                        this.normalCount += commitCount;
                        updateCount += commitCount;
                        commit();
                    } else {
                        this.errorCount += commitCount;
                        errorCount += commitCount;
                        rollback();
                    }
                    commitCount = 0;
                }
            }
            if (dsContrDtlTMsgList.size() > 0) {
                DS_CONTR_DTLTMsg[] inMsgArray = new DS_CONTR_DTLTMsg[dsContrDtlTMsgList.size()];
                retCount = S21FastTBLAccessor.update(dsContrDtlTMsgList.toArray(inMsgArray));
                if (retCount != inMsgArray.length) {
                    errFlg = ZYPConstant.FLG_ON_1;
                }
                for (int i = 0; i < dsContrDtlTMsgList.size(); i++) {
                    if (!callContractTrackingAPIforRenewalHold(dsContrDtlTMsgList.get(i).dsContrPk.getValue(), dsContrDtlTMsgList.get(i).dsContrDtlPk.getValue())) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                }
                dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
                if (errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                    this.normalCount += commitCount;
                    updateCount += commitCount;
                    commit();
                } else {
                    this.errorCount += commitCount;
                    errorCount += commitCount;
                    rollback();
                }
                commitCount = 0;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        S21InfoLogOutput.println("update count is " + updateCount + ".");
        S21InfoLogOutput.println("error  count is " + errCount + ".");
        S21InfoLogOutput.println("updateContratStatusForDetail Method " + targetSts + " Mode End");
    }

    public boolean callContractTrackingAPIforRenewalHold(BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ContrTrkProcMode.CONTRACT_MODE_CHANGE.code);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);

        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }

        return true;
    }

    // END 2017/10/03 K.Kojima [QC#18417,ADD]
    // Add Start 04/23/2018 <QC#23378>
    boolean UpdDsContrPrcAlloc(List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList) {
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgList) {
            BigDecimal basePrcDealAmt = dsContrDtlTMsg.basePrcDealAmt.getValue();
            if (!ZYPCommonFunc.hasValue(basePrcDealAmt) || BigDecimal.ZERO.compareTo(basePrcDealAmt) == 0) {
                continue;
            }

            try {
                PreparedStatement ps = null;
                ResultSet rs = null;
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("glblCmpyCd", this.glblCmpyCd);
                BigDecimal dsContrPk = dsContrDtlTMsg.dsContrPk.getValue();
                param.put("dsContrPk", dsContrPk);
                BigDecimal dsContrDtlPk  = dsContrDtlTMsg.dsContrDtlPk.getValue();
                param.put("dsContrDtlPk", dsContrDtlPk);
                param.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);


                ps = this.ssmLLClient.createPreparedStatement("getdsContrPrcAllocSum", param, ssmExecParam);
                rs = ps.executeQuery();

                while (rs.next()) {
                    if (BigDecimal.ZERO.compareTo(rs.getBigDecimal("REC_CNT")) == 0) {
                        break;
                    }
                    BigDecimal sumAllocAmt = rs.getBigDecimal("PRC_ALLOC_AMT");
                    if (!ZYPCommonFunc.hasValue(sumAllocAmt) || BigDecimal.ZERO.compareTo(sumAllocAmt) == 0) {
                        break;
                    }
                    NSXC001001UpdateDsContrPrcAlloc.setDsContrPrcAlloc(this.glblCmpyCd, this.salesDate, dsContrPk, dsContrDtlPk, basePrcDealAmt, SVC_INV_CHRG_TP.BASE_CHARGE, "NSAB0040", ONBATCH_TYPE.BATCH);
                }
                S21SsmLowLevelCodingClient.closeResource(ps, rs);
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            }       }
        return false;
    }
    // Add End   04/23/2018 <QC#23378>
    // START 2018/05/14 U.Kim [QC#24854, ADD]
    boolean callSumAggregateAPI(BigDecimal dsContrPk){
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, dsContrPk);
        dsContrTMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(dsContrTMsg);
        if (dsContrTMsg == null) {
            return false;
        }
        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            return true;
        }

        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        dsContrDtlTMsg.setSQLID("006");
        dsContrDtlTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsContrDtlTMsg.setConditionValue("dsContrPk01", dsContrPk);
        dsContrDtlTMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.AGGREGATE);
        DS_CONTR_DTLTMsgArray outTmsgArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(dsContrDtlTMsg);
        if (outTmsgArray.getValidCount() == 0) {
            return false;
        }
        dsContrDtlTMsg = outTmsgArray.no(0);

        NSZC047011PMsg pMsg = new NSZC047011PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "11");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk.getValue());
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return false;
        }
        return true;
    }
    // END 2018/05/14 U.Kim [QC#24854, ADD]

// START 2019/05/22 [QC#50212,DEL]
//     // START 2018/05/15 M.Naito [QC#21679, ADD]
//    private List<DS_SUB_CONTRTMsg> getDsSubContrList(BigDecimal dsContrPk) {
//
//        List<DS_SUB_CONTRTMsg> dsSubContrList = new ArrayList<DS_SUB_CONTRTMsg>();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("glblCmpyCd", this.glblCmpyCd);
//            param.put("dsContrPk", dsContrPk);
//
//            ps = this.ssmLLClient.createPreparedStatement("getDsSubContrList", param, ssmExecParam);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                DS_SUB_CONTRTMsg dsSubContrTmsg = new DS_SUB_CONTRTMsg();
//                ZYPEZDItemValueSetter.setValue(dsSubContrTmsg.glblCmpyCd, this.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(dsSubContrTmsg.dsSubContrPk, rs.getBigDecimal("DS_SUB_CONTR_PK"));
//                dsSubContrTmsg = (DS_SUB_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsSubContrTmsg);
//
//                dsSubContrList.add(dsSubContrTmsg);
//            }
//
//        } catch (SQLException e) {
//            sqlExceptionHandler(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(ps, rs);
//        }
//        return dsSubContrList;
//    }
//
//    private void setParamForNSZC052001(NSZC052001PMsg pMsg, DS_SUB_CONTRTMsg dsSubContrTMsg) {
//
//        if (dsSubContrTMsg == null) {
//            return;
//        }
//
//        try {
//            PreparedStatement ps = null;
//            ResultSet rs = null;
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("glblCmpyCd", this.glblCmpyCd);
//            param.put("dsSubContrPk", dsSubContrTMsg.dsSubContrPk.getValue());
//
//            ps = this.ssmLLClient.createPreparedStatement("getDsSubContrInfo", param, ssmExecParam);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//                setValue(pMsg.slsDt, this.salesDate);
//                setValue(pMsg.serNum, rs.getString("SER_NUM"));
//                setValue(pMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
//
//                pMsg.xxSubContrList.getValidCount();
//                setValue(pMsg.xxSubContrList.no(0).vndCd, dsSubContrTMsg.vndCd);
//                setValue(pMsg.xxSubContrList.no(0).techCd, dsSubContrTMsg.techTocCd);
//                setValue(pMsg.xxSubContrList.no(0).effFromDt, dsSubContrTMsg.effFromDt);
//                setValue(pMsg.xxSubContrList.no(0).effThruDt, dsSubContrTMsg.effThruDt);
//                setValue(pMsg.xxSubContrList.no(0).contrTrmnFlg, ZYPConstant.FLG_ON_Y);
//                setValue(pMsg.xxSubContrList.no(0).basePrcDealAmt, dsSubContrTMsg.basePrcDealAmt);
//                setValue(pMsg.xxSubContrList.no(0).adminPrcDealAmt, dsSubContrTMsg.adminPrcDealAmt);
//                setValue(pMsg.xxSubContrList.no(0).prepdMaintFlg, dsSubContrTMsg.prepdMaintFlg);
//                setValue(pMsg.xxSubContrList.no(0).bwMtrAlwncCnt, dsSubContrTMsg.bwMtrAlwncCnt);
//                setValue(pMsg.xxSubContrList.no(0).colorMtrAlwncCnt, dsSubContrTMsg.colorMtrAlwncCnt);
//                setValue(pMsg.xxSubContrList.no(0).bwMtrPrcAmtRate, dsSubContrTMsg.bwMtrPrcAmtRate);
//                setValue(pMsg.xxSubContrList.no(0).colorMtrPrcAmtRate, dsSubContrTMsg.colorMtrPrcAmtRate);
//                setValue(pMsg.xxSubContrList.no(0).splyInclFlg, dsSubContrTMsg.splyInclFlg);
//                setValue(pMsg.xxSubContrList.no(0).bllgCycleCd, dsSubContrTMsg.bllgCycleCd);
//                setValue(pMsg.xxSubContrList.no(0).dlrFleetFlg, dsSubContrTMsg.dlrFleetFlg);
//                setValue(pMsg.xxSubContrList.no(0).dlrFleetNum, dsSubContrTMsg.dlrFleetNum);
//
//                pMsg.xxSubContrList.setValidCount(1);
//            }
//            S21SsmLowLevelCodingClient.closeResource(ps, rs);
//        } catch (SQLException e) {
//            sqlExceptionHandler(e);
//        }
//    }
//     // END 2018/05/15 M.Naito [QC#21679, ADD]
// END 2019/05/22 [QC#50212,DEL]

    // START 2019/05/22 [QC#50212,ADD]
    private boolean terminateDsSubContr(List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList) {
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgList) {

            DS_SUB_CONTRTMsg inMsg = new DS_SUB_CONTRTMsg();
            inMsg.setSQLID("001");
            inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlTMsg.dsContrDtlPk.getValue());
            inMsg.setConditionValue("contrTrmnFlg01", ZYPConstant.FLG_OFF_N);
            DS_SUB_CONTRTMsgArray dsSubContrArray = (DS_SUB_CONTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
            if (dsSubContrArray == null || dsSubContrArray.getValidCount() == 0) {
                continue;
            }

            for (int i = 0; i < dsSubContrArray.getValidCount(); i++) {
                DS_SUB_CONTRTMsg tmsg = dsSubContrArray.no(i);
                ZYPEZDItemValueSetter.setValue(tmsg.contrTrmnFlg, ZYPConstant.FLG_ON_Y);
                S21ApiTBLAccessor.update(tmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                    return false;
                }
            }
        }
        return true;
    }
    // END 2019/05/22 [QC#50212,ADD]
    // START 2022/06/21 R.jin [QC#60216,ADD]
    private void updateBasePrcDealAmt() {
        S21InfoLogOutput.println("updateBasePrcDealAmt Method  Start");
        int updateTargetCnt = 0;
        int updateCount = 0;
        int errCount = 0;
        String errFlg = ZYPConstant.FLG_OFF_0;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("slsDt", this.salesDate);
            param.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ACTIVE);
            ps = this.ssmLLClient.createPreparedStatement(UPDATE_BASE_PRC_DEALAMT_TARGET, param, ssmExecParam);
            rs = ps.executeQuery();
            while (rs.next()) {
                updateTargetCnt++;
                DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
                DS_CONTR_DTLTMsg tMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
                if (tMsg != null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.basePrcDealAmt, rs.getBigDecimal("EFF_BASE_PRC_DEAL_AMT"));
                    S21FastTBLAccessor.update(tMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                } else {
                    errFlg = ZYPConstant.FLG_ON_1;
                }
                /** Result */
                if (errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                    this.normalCount++;
                    updateCount++;
                    commit();
                } else {
                    errorCount++;
                    this.errorCount++;
                    rollback();
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        S21InfoLogOutput.println("updateTarget count is " + updateTargetCnt + ".");
        S21InfoLogOutput.println("update count is " + updateCount + ".");
        S21InfoLogOutput.println("error  count is " + errCount + ".");
        S21InfoLogOutput.println("updateBasePrcDealAmt Method  End");
    }

    private void updateXsMtrAmtRate() {
        S21InfoLogOutput.println("updateXsMtrAmtRate Method  Start");
        int updateTargetCnt = 0;
        int updateCount = 0;
        int errCount = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String errFlg = ZYPConstant.FLG_OFF_0;

        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("slsDt", this.salesDate);
            param.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ACTIVE);
            ps = this.ssmLLClient.createPreparedStatement(UPDATE_XS_MTRAMT_RATE_TARGET, param, ssmExecParam);
            rs = ps.executeQuery();
            while (rs.next()) {
                updateTargetCnt++;
                CONTR_XS_COPYTMsg inMsg = new CONTR_XS_COPYTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.contrXsCopyPk, rs.getBigDecimal("CONTR_XS_COPY_PK_FOR_XS_COPY"));
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                CONTR_XS_COPYTMsg tMsg = (CONTR_XS_COPYTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
                if (tMsg != null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.xsMtrAmtRate, rs.getBigDecimal("XS_MTR_AMT_RATE_FOR_EFF_MTR"));
                    ZYPEZDItemValueSetter.setValue(tMsg.xsMtrCopyQty, rs.getBigDecimal("XS_MTR_COPY_QTY_FOR_EFF_MTR"));
                    S21FastTBLAccessor.update(tMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                } else {
                    errFlg = ZYPConstant.FLG_ON_1;
                }
                if (rs.getBigDecimal("CONTR_XS_COPY_PK_FOR_EFF_MTR") == null || rs.getBigDecimal("CONTR_XS_COPY_PK_FOR_XS_COPY").compareTo(rs.getBigDecimal("CONTR_XS_COPY_PK_FOR_EFF_MTR")) != 0) {
                    DS_CONTR_PRC_EFF_MTRTMsg inMsg2 = new DS_CONTR_PRC_EFF_MTRTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg2.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inMsg2.dsContrPrcEffMtrPk, rs.getBigDecimal("DS_CONTR_PRC_EFF_MTR_PK"));
                    DS_CONTR_PRC_EFF_MTRTMsg tMsg2 = (DS_CONTR_PRC_EFF_MTRTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg2);
                    if (tMsg2 != null) {
                        ZYPEZDItemValueSetter.setValue(tMsg2.contrXsCopyPk, rs.getBigDecimal("CONTR_XS_COPY_PK_FOR_XS_COPY"));
                        S21FastTBLAccessor.update(tMsg2);
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg2.getReturnCode())) {
                            errFlg = ZYPConstant.FLG_ON_1;
                        }
                    } else {
                        errFlg = ZYPConstant.FLG_ON_1;
                    }
                }
                /** Result */
                if (errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                    this.normalCount++;
                    updateCount++;
                    commit();
                } else {
                    this.errorCount++;
                    errorCount++;
                    rollback();
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        S21InfoLogOutput.println("updateTarget count is " + updateTargetCnt + ".");
        S21InfoLogOutput.println("update count is " + updateCount + ".");
        S21InfoLogOutput.println("error  count is " + errCount + ".");
        S21InfoLogOutput.println("updateXsMtrAmtRate Method  End");
    }
    // END 2022/06/21 R.jin [QC#60216,ADD]

    // START 2024/01/11 S.Moriai [QC#62117,ADD]
    /**
     * updateStartRead
     */
    private void updateStartRead(String afterSts) {
        S21InfoLogOutput.println("updateStartRead Method " + afterSts + " Mode Start");

        List<DsContrKeyBean> dsContrPkList;
        List<DsContrKeyBean> dsContrPkTagetList;
        List<DS_CONTRTMsg> dsContrTMsgList = new ArrayList<DS_CONTRTMsg>();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();

        /** Target Data */
        dsContrPkTagetList = getDsContrPkList(afterSts);

        /** Remove Duplication Data**/
        dsContrPkList = removeDuplication(dsContrPkTagetList);

        int totalCount = 0;
        int commitCount = 0;
        int updateCount = 0;
        int errCount = 0;
        boolean inSvcMemoFlg = false;
        boolean updateQaHoldFlg = false;
        boolean doUpdateMeterReadFlg = false;

        String errFlg = ZYPConstant.FLG_OFF_0;

        for (DsContrKeyBean dsContrPkData : dsContrPkList) {
            totalCount++;
            commitCount++;
            BigDecimal dsContrPk = dsContrPkData.getDsContrPk();

            // get DS_CONTR_NUM
            String dsContrNum = "";
            DS_CONTRTMsg dsContrTMsg = getDsContr(this.glblCmpyCd, dsContrPk);
            if (dsContrTMsg != null) {
                dsContrNum = dsContrTMsg.dsContrNum.getValue();
            }

            if (dsContrPkData.getDsContrExistFlg().equals(ZYPConstant.FLG_ON_Y)) {
                dsContrTMsgList = setDsContrTMsgList(afterSts, dsContrPk, dsContrTMsgList);
            }
            if (dsContrPkData.getDsContrDtlExistFlg().equals(ZYPConstant.FLG_ON_Y)) {
                dsContrDtlTMsgList = setDsContrDtlTMsgList(afterSts, dsContrPk, dsContrDtlTMsgList);
            }
            for (int i = 0; i < dsContrDtlTMsgList.size(); i++) {
                if (!DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTMsgList.get(i).dsContrDtlTpCd.getValue())) {
                    continue;
                }
                SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray = getSvcPhysMtrPk(dsContrDtlTMsgList.get(i).dsContrDtlPk.getValue(), dsContrDtlTMsgList.get(i).svcMachMstrPk.getValue());
                if (svcPhysMtrReadTMsgArray == null || !isExistsStartMtrRead(this.glblCmpyCd, dsContrDtlTMsgList.get(i).dsContrDtlPk.getValue(), svcPhysMtrReadTMsgArray.no(0).svcPhysMtrPk.getValue())) {
                    List<Map<String, Object>> billingReadMapList = getBillingRead(dsContrPk, dsContrDtlTMsgList.get(i));
                     if (billingReadMapList.isEmpty()) {
                         inSvcMemoFlg = insertSvcMemo(dsContrPk, dsContrDtlTMsgList.get(i).dsContrDtlPk.getValue(), QA_HOLD_REASON_MSG);
                         // QA Hold
                         updateQaHoldFlg = updateQaHold(dsContrPk);
                         if (inSvcMemoFlg == true || updateQaHoldFlg == true) {
                             errFlg =ZYPConstant.FLG_ON_1;
                         }
                         SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMsrt(this.glblCmpyCd, dsContrDtlTMsgList.get(i).svcMachMstrPk.getValue());
                         // set errMsgList
                         String mailMsg = dsContrNum + ", " + svcMachMstrTMsg.serNum.getValue() + ", " + getDate(dsContrDtlTMsgList.get(i).contrEffFromDt.getValue());
                         this.mailMsgList.add(mailMsg);
                     } else {
                         doUpdateMeterReadFlg = doUpdateMeterRead(billingReadMapList, dsContrDtlTMsgList.get(i).contrEffFromDt.getValue(), dsContrNum);
                         if (doUpdateMeterReadFlg) {
                             errFlg =ZYPConstant.FLG_ON_1;
                         }
                     }

                   
                }
            }
            if (errFlg.equals(ZYPConstant.FLG_OFF_0)) {
                this.normalCount += commitCount;
                updateCount += commitCount;
                commit();
            } else {
                this.errorCount += commitCount;
                errorCount += commitCount;
                rollback();
            }
            inSvcMemoFlg = false;
            updateQaHoldFlg = false;
            doUpdateMeterReadFlg = false;
            errFlg = ZYPConstant.FLG_OFF_0;
            if (dsContrDtlTMsgList.size() > 0) {
                dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
            }
        }

        // Send Mail
        if (this.mailMsgList.size() > 0) {
            sendMail();
        }

        S21InfoLogOutput.println("update count is " + updateCount + ".");
        S21InfoLogOutput.println("error  count is " + errCount + ".");
        S21InfoLogOutput.println("updateContratStatus Method " + afterSts + " Mode End");
    }

    /**
     * isExistsStartMtrRead
     * 
     * @param glblCmpyCd
     * @param dsContrDtlPk
     * @param svcPhysMtrPk
     * @return Ture: exist StarRead, false: Doesn't exist StartRead
     */
    private boolean isExistsStartMtrRead(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcPhysMtrPk) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(dsContrDtlPk) || !ZYPCommonFunc.hasValue(svcPhysMtrPk)) {
            return false;
        }

        BigDecimal svcPhysMtrReadGrpSq = NSXC003001SvcPhysMtrRead.getStartMeterSvcPhysMtrReadGrpSq(glblCmpyCd, dsContrDtlPk, svcPhysMtrPk);
        if (!ZYPCommonFunc.hasValue(svcPhysMtrReadGrpSq)) {
            return false;
        }
        return true;
    }

    /**
     * getBillingRead
     * 
     * @param dsContrPk
     * @param dsContrDtlTMsg
     * @return
     */
    private List<Map<String, Object>> getBillingRead(BigDecimal dsContrPk, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("slsDt", this.salesDate);
        param.put("svcMachMstrPk", dsContrDtlTMsg.svcMachMstrPk.getValue());
        int befDays = ZYPCodeDataUtil.getNumConstValue(START_MTR_READ_WINDOW_BEF_DAYS, glblCmpyCd).intValue();
        String newMtrDt = ZYPDateUtil.addDays(this.salesDate, -befDays);
        param.put("mtrReadDt", newMtrDt);
        param.put("dsContrPk", dsContrPk);
        param.put("dsMtrReadTpGrpCd", DS_MTR_READ_TP_GRP.BILLABLE_READS);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getBillingRead", param, this.ssmExecParam);
    }

    /**
     * doUpdateMeterRead
     * 
     * @param billingReadMap
     * @param contrEffFromDt
     * @param dsContrNum 
     * @return
     */
    private boolean doUpdateMeterRead(List<Map<String, Object>> billingReadMap, String contrEffFromDt, String dsContrNum) {
        boolean inSvcMemoFlg = false;
        boolean updateQaHoldFlg = false;
        boolean apiFlg = false;
        boolean ngRowFlg = false;
        BigDecimal svcMachMstrPk = null;
        BigDecimal svcPhysMtrPk = null;

        BigDecimal dsContrPk = (BigDecimal) billingReadMap.get(0).get("DS_CONTR_PK");
        NSZC010001PMsg apiPMsg = new NSZC010001PMsg();

        for (int i = 0; i < billingReadMap.size(); i++) {
            if (NG_ROW.equals((String) billingReadMap.get(i).get("NG_ROW"))) {
                ngRowFlg = true;
                svcMachMstrPk = (BigDecimal) billingReadMap.get(i).get("SVC_MACH_MSTR_PK");
                svcPhysMtrPk = (BigDecimal) billingReadMap.get(i).get("SVC_PHYS_MTR_PK");
                break;
            }
            if (i == 0) {
                apiPMsg = new NSZC010001PMsg();
                ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
                ZYPEZDItemValueSetter.setValue(apiPMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
                ZYPEZDItemValueSetter.setValue(apiPMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);
                ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, this.salesDate);
                ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, (BigDecimal) billingReadMap.get(i).get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(apiPMsg.xxStartReadFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(apiPMsg.dsContrDtlPk, (BigDecimal) billingReadMap.get(i).get("DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(apiPMsg.contrEffFromDt, this.salesDate);
            }
            ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(i).svcPhysMtrPk, (BigDecimal) billingReadMap.get(i).get("SVC_PHYS_MTR_PK"));
            ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(i).mtrReadDt, (String) billingReadMap.get(i).get("MTR_READ_DT"));
            ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(i).rgtnMtrDt, this.salesDate);
            ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(i).readMtrCnt, (BigDecimal) billingReadMap.get(i).get("READ_MTR_CNT"));
            ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(i).testMtrCnt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(i).rgtnUsrId, BATCH_ID);
            ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(i).estFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(i).invProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_ON_Y);
            apiPMsg.A.setValidCount(i + 1);
        }

        if (!ngRowFlg) {
            NSZC010001 api = new NSZC010001();
            api.execute(apiPMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
        } else {
            inSvcMemoFlg = insertSvcMemo(dsContrPk, svcPhysMtrPk, QA_HOLD_REASON_MSG);
            // QA Hold
            updateQaHoldFlg = updateQaHold(dsContrPk);
            if (inSvcMemoFlg == true || updateQaHoldFlg == true) {
                apiFlg = true;
            }
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMsrt(this.glblCmpyCd, svcMachMstrPk);
            // set errMsgList
            String mailMsg = dsContrNum + ", " + svcMachMstrTMsg.serNum.getValue() + ", " + getDate(contrEffFromDt);
            this.mailMsgList.add(mailMsg);
        }
        

        if (apiPMsg.xxMsgIdList.getValidCount() != 0) {
            for (int j = 0; j < apiPMsg.xxMsgIdList.getValidCount(); j++) {
                String message = S21MessageFunc.clspGetMessage(apiPMsg.xxMsgIdList.no(j).xxMsgId.getValue());
                inSvcMemoFlg = insertSvcMemo(dsContrPk, apiPMsg.dsContrDtlPk.getValue(), message);
                if (inSvcMemoFlg) {
                    apiFlg = true;
                    break;
                }
            }
            // QA Hold
            updateQaHoldFlg = updateQaHold(dsContrPk);
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMsrt(this.glblCmpyCd, apiPMsg.svcMachMstrPk.getValue());
            // set errMsgList
            String mailMsg = dsContrNum + ", " + svcMachMstrTMsg.serNum.getValue() + ", " + getDate(contrEffFromDt);
            this.mailMsgList.add(mailMsg);
            if (updateQaHoldFlg) {
                apiFlg = true;
            }
        }
        if (apiFlg) {
            return true;
        }
        return false;
    }

    /**
     * insertSvcMemo
     * 
     * @param curDsContrPk
     * @param curDsContrDtlPk
     * @param messageCode
     * @return True: fail, false: success
     */
    private boolean insertSvcMemo(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk, String messageCode) {
        // insert SVC_MEMO
        SVC_MEMOTMsg memoTMsg = new SVC_MEMOTMsg();
        setValue(memoTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(memoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(memoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(memoTMsg.svcMemoTpCd, SVC_MEMO_TP.CONTRACT_AUDIT);
        // START 2024/03/21 T.Nagae [QC#62117, ADD]
        setValue(memoTMsg.svcMemoRsnCd, SVC_MEMO_RSN.MISSING_METER_READ);
        // END 2024/03/21 T.Nagae [QC#62117, ADD]
        setValue(memoTMsg.svcCmntTxt, messageCode);
        setValue(memoTMsg.dsContrPk, curDsContrPk);
        setValue(memoTMsg.dsContrDtlPk, curDsContrDtlPk);
        setValue(memoTMsg.lastUpdUsrId, BATCH_ID);
        setValue(memoTMsg.lastUpdTs, this.sysTime);
        EZDTBLAccessor.insert(memoTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(memoTMsg.getReturnCode())) {
            return true;
        }
        return false;
    }

    /**
     * updateQaHold
     * 
     * @param dsContrPk
     * @return True: fail, false: success
     */
    private boolean updateQaHold(BigDecimal dsContrPk) {

        // Update DS Contract
        DS_CONTRTMsg dsContrTMsg = getDsContr(this.glblCmpyCd, dsContrPk);
        if (dsContrTMsg == null) {
            return true;
        }

        ZYPEZDItemValueSetter.setValue(dsContrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
        S21FastTBLAccessor.update(dsContrTMsg);
        String rtnCd = dsContrTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            return true;
        }
        if (!callContractTrackingAPI(this.glblCmpyCd, dsContrTMsg.dsContrPk.getValue(), null, null, null, null, null, null)) {
            return true;
        }

        // Update DS Contract Detail
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = getDsContrDtlList(this.glblCmpyCd, dsContrPk);
        if (dsContrDtlTMsgArray == null) {
            return true;
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            if (DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            if (DS_CONTR_DTL_STS.ORDER.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
            S21FastTBLAccessor.update(dsContrDtlTMsg);
            rtnCd = dsContrDtlTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                return true;
            }
            if (!callContractTrackingAPI(this.glblCmpyCd, dsContrTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), null, null, null, null, null)) {
                return true;
            }

            // Update DS Contract Billing Meter
            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = getDsContrBllgMtrList(this.glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (dsContrBllgMtrTMsgArray != null) {
                for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
                    S21FastTBLAccessor.update(dsContrBllgMtrTMsg);
                    rtnCd = dsContrBllgMtrTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        return true;
                    }
                    if (!callContractTrackingAPI(this.glblCmpyCd, dsContrTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), null, null, null, null)) {
                        return true;
                    }
                }
            }

            // Update DS Contract Price Effective
            DS_CONTR_PRC_EFFTMsgArray dsContrPrcEffTMsgArray = getDsContrPrcEffList(this.glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (dsContrPrcEffTMsgArray != null) {
                for (int j = 0; j < dsContrPrcEffTMsgArray.getValidCount(); j++) {
                    DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = dsContrPrcEffTMsgArray.no(j);
                    ZYPEZDItemValueSetter.setValue(dsContrPrcEffTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
                    S21FastTBLAccessor.update(dsContrPrcEffTMsg);
                    rtnCd = dsContrPrcEffTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        return true;
                    }
                    if (!callContractTrackingAPI(this.glblCmpyCd, dsContrTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrPrcEffTMsg.dsContrBllgMtrPk.getValue(), dsContrPrcEffTMsg.dsContrPrcEffPk
                            .getValue(), dsContrPrcEffTMsg.contrPrcEffFromDt.getValue(), dsContrPrcEffTMsg.contrPrcEffThruDt.getValue(), dsContrPrcEffTMsg.baseChrgFlg.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * getSvcPhysMtrPk
     * 
     * @param dsContrDtlPk
     * @param svcMachMstrPk
     * @return
     */
    private SVC_PHYS_MTR_READTMsgArray getSvcPhysMtrPk(BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk) {
        SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = new SVC_PHYS_MTR_READTMsg();
        svcPhysMtrReadTMsg.setSQLID("009");
        svcPhysMtrReadTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        svcPhysMtrReadTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        svcPhysMtrReadTMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(svcPhysMtrReadTMsg);
        if (svcPhysMtrReadTMsgArray == null || svcPhysMtrReadTMsgArray.getValidCount() == 0) {
            return null;
        }
        return svcPhysMtrReadTMsgArray;
    }

    /**
     * getSvcMachMsrt
     * 
     * @param glblCmpyCd
     * @param svcMachMstrPk
     * @return
     */
    private SVC_MACH_MSTRTMsg getSvcMachMsrt(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * 
     * 
     * @param glblCmpyCd
     * @param dsContrPk
     * @return
     */
    private DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg prmTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * getDsContrDtlList
     * 
     * @param glblCmpyCd
     * @param dsContrPk
     * @return
     */
    private DS_CONTR_DTLTMsgArray getDsContrDtlList(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        return (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(prmTMsg);
    }

    /**
     * getDsContrBllgMtrList
     * 
     * @param glblCmpyCd
     * @param dsContrDtlPk
     * @return
     */
    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(prmTMsg);
    }

    /**
     * getDsContrPrcEffList
     * 
     * @param glblCmpyCd
     * @param dsContrDtlPk
     * @return
     */
    private DS_CONTR_PRC_EFFTMsgArray getDsContrPrcEffList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_PRC_EFFTMsg prmTMsg = new DS_CONTR_PRC_EFFTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(prmTMsg);
    }

    /**
     * callContractTrackingAPI
     * 
     * @param glblCmpyCd
     * @param dsContrPk
     * @param dsContrDtlPk
     * @param dsContrBllgMtrPk
     * @param dsContrPrcEffPk
     * @param contrPrcEffFromDt
     * @param contrPrcEffThruDt
     * @param baseChrgFlg
     * @return True: fail, false: success
     */
    private boolean callContractTrackingAPI(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk, String contrPrcEffFromDt, String contrPrcEffThruDt, String baseChrgFlg) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);

        if (ZYPCommonFunc.hasValue(dsContrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrPk);
        }

        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        }

        if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        }

        if (ZYPCommonFunc.hasValue(dsContrPrcEffPk)) {
            if (ZYPConstant.FLG_ON_Y.equals(baseChrgFlg)) {
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPrcEffPk, dsContrPrcEffPk);
            ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffFromDt, contrPrcEffFromDt);
            ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffThruDt, contrPrcEffThruDt);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.stsMemoUpdPsnCd, "NSAB0040");

        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {

            return false;
        }

        return true;
    }

    /**
     * getDate
     * 
     * @param dateBefore
     * @return yyyyMMdd change to yyyy/MM/dd
     */
    private String getDate(String dateBefore) {
        String dateAfter = "";
        SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getDateInstance();
        formatter.applyPattern("yyyyMMdd");
        try {
            Date date = formatter.parse(dateBefore);
            formatter.applyPattern("yyyy/MM/dd");
            dateAfter = formatter.format(date);
            System.out.println(dateAfter);
            
        } catch (ParseException ex) {
            return dateAfter;
        }
        return dateAfter;

    }

    /**
     * sendMail
     * 
     */
    private Object[] sendMail() {
        // Get From Address
        S21MailGroup fromGrp = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        fromGrp.setMailKey1("NS");
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();
        if (fromAddrList == null || fromAddrList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"From mail-address.", MAIL_GROUP_ID_FROM });
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();
        if (toAddrList == null || toAddrList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Error Mail Template
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mail Template", MAIL_TEMPLATE_ID });
        }

        // Set Template Parameter
        template.setTemplateParameter(MAIL_TEMPLATE_SET_KEY_DATE, getDate(this.salesDate));

        StringBuilder msgBuilder = new StringBuilder();
        for (int i = 0; i < this.mailMsgList.size(); i++) {
            msgBuilder.append(this.mailMsgList.get(i));
            msgBuilder.append("\n");
            msgBuilder.length();
        }

        template.setTemplateParameter(MAIL_TEMPLATE_SET_KEY_MESSAGE, msgBuilder.toString());

        // Send Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(fromAddrList.get(0));
        mail.setToAddressList(toAddrList);
        mail.setMailTemplate(template);
        mail.postMail();

        return null;
    }

    /**
     * removeDuplication
     * 
     * @param dsContrPkTagetList
     * @return
     */
    private List<DsContrKeyBean> removeDuplication(List<DsContrKeyBean> dsContrPkTagetList) {
        List<DsContrKeyBean> dsContrPkList = new ArrayList<DsContrKeyBean>();
        for (int i = 0; i < dsContrPkTagetList.size(); i++) {
            if (dsContrPkList.isEmpty()) {
                dsContrPkList.add(dsContrPkTagetList.get(i));
                continue;
            }
            int j = 0;
            for (; j < dsContrPkList.size(); j++) {
                if (dsContrPkList.get(j).getDsContrPk().equals((dsContrPkTagetList.get(i).getDsContrPk()))) {
                    break;
                }
            }
            if (j == dsContrPkList.size()) {
                dsContrPkList.add(dsContrPkTagetList.get(i));
            }
        }
        return dsContrPkList;
        
    }
    // END 2024/01/11 S.Moriai [QC#62117,ADD]
}
