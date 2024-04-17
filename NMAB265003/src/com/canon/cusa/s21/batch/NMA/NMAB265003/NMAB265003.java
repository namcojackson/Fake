package com.canon.cusa.s21.batch.NMA.NMAB265003;

import static com.canon.cusa.s21.batch.NMA.NMAB265003.Constant.NMAB265003Constant.TABLE_ACCT_TRTY_ROLE_ASG;
import static com.canon.cusa.s21.batch.NMA.NMAB265003.Constant.NMAB265003Constant.TABLE_ACCT_TRTY_ROLE_ASG_LOG;
import static com.canon.cusa.s21.batch.NMA.NMAB265003.Constant.NMAB265003Constant.TABLE_PROS_TRTY_ROLE_ASG;
import static com.canon.cusa.s21.batch.NMA.NMAB265003.Constant.NMAB265003Constant.TABLE_PROS_TRTY_ROLE_ASG_LOG;
import static com.canon.cusa.s21.batch.NMA.NMAB265003.Constant.NMAB265003Constant.ZZZM9026E;

import static com.canon.cusa.s21.batch.NMA.NMAB265003.Constant.NMAB265003Constant.TABLE_ACCT_TRTY_RESRC_ASG;
import static com.canon.cusa.s21.batch.NMA.NMAB265003.Constant.NMAB265003Constant.TABLE_PROS_TRTY_RESRC_ASG;

import static com.canon.cusa.s21.batch.NMA.NMAB265003.Constant.NMAB265003Constant.TABLE_ACCT_TRTY_RESRC_LOG;
import static com.canon.cusa.s21.batch.NMA.NMAB265003.Constant.NMAB265003Constant.TABLE_PROS_TRTY_RESRC_LOG;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * NMAB2650 Sales Territory Visibility Defaulting Processor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         K.Minamide      Create          N/A
 * 2016/03/16   Fujitsu         H.Ito           Update          QC#4443
 * 2016/03/29   Fujitsu         N.Sugiura       Update          QC#5909
 * 2016/07/05   Fujitsu         R.Nakamura      Update          QC#5909
 * 2016/07/20   Fujitsu         R.Nakamura      Update          QC#12113
 * 2016/07/28   SRAA            Y.Chen          Update          QC#12571
 * 2016/08/24   Fujitsu         C.Yokoi         Update          QC#12063, 13277
 * 2017/05/09   Fujitsu         k.Fujita        Update          RS#8275
 *</pre>
 */
public class NMAB265003 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Commit Transaction Count */
    private int commitTransactionCount = 0;

    // DEL START 2016/03/29 QC#5909
    // /** Process Multiplicity Number */
    // private int processMultiplicityNumber = 0;
    //
    // /** Process Control Number */
    // private int processControlNumber = 0;
    //
    // /** Limited Process Count */
    // private int limitedProcessCount = 0;
    // DEL END 2016/03/29 QC#5909

    /** Direct Sales Account Type */
    private String directSalesAccountType = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

//    /** S21SsmBatchClient */
//    private S21SsmBatchClient ssmBatchClient = null;

    // Add Start 2016/07/05 QC#5909
    /** SQL access parts */
    private S21SsmBatchClientCustom ssmBatchClientCustom = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // Add End 2016/07/05 QC#5909

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = super.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.commitTransactionCount = super.getCommitCount();
        if (this.commitTransactionCount < 0) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Commit Transaction Count" });
        }
        // DEL START 2016/03/29 QC#5909
        // try {
        // this.processMultiplicityNumber =
        // Integer.valueOf(super.getUserVariable1());
        // } catch (NumberFormatException e) {
        // throw new S21AbendException(ZZZM9026E, new String[]
        // {"Multiplicity Number" });
        // }
        //
        // try {
        // this.processControlNumber =
        // Integer.valueOf(super.getUserVariable2());
        // } catch (NumberFormatException e) {
        // throw new S21AbendException(ZZZM9026E, new String[]
        // {"Process Control Number" });
        // }
        // DEL END 2016/03/29 QC#5909

        // MOD START 2016/03/16 QC#4443
        // try {
        // this.limitedProcessCount =
        // Integer.valueOf(super.getUserVariable3());
        // } catch (NumberFormatException e) {
        // throw new S21AbendException(ZZZM9026E, new String[]
        // {"Limited Process Count" });
        // }

        try {
            this.directSalesAccountType = super.getUserVariable3();
            if (!ZYPCommonFunc.hasValue(this.directSalesAccountType)) {
                throw new S21AbendException(ZZZM9026E, new String[] {"Direct Sales Account Type" });
            }
        } catch (NumberFormatException e) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Direct Sales Account Type" });
        }
        // MOD END 2016/03/16 QC#4443

//        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Add Start 2016/07/05 QC#5909
        this.ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // Add End 2016/07/05 QC#5909
    }

    // MOD START 2016/03/16 QC#4443
    @Override
    protected void mainRoutine() {

        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
            procCustomer();
        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
            procProspect();
        } else {
            return;
        }

        // Mod Start 2016/07/05 QC#5909
        // ACCT_TRTY_RESRC_LOGTMsg deleteResrcLogTMsg = new
        // ACCT_TRTY_RESRC_LOGTMsg();
        // ZYPEZDItemValueSetter.setValue(deleteResrcLogTMsg.glblCmpyCd,
        // this.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(deleteResrcLogTMsg.dsAcctTpCd,
        // this.directSalesAccountType);
        // S21FastTBLAccessor.removeByPartialValue(deleteResrcLogTMsg,
        // new String[] {"glblCmpyCd", "dsAcctTpCd" });

//        ResultSet rs = null;
//        PreparedStatement stmtSelect = null;
//        Map<String, String> paramsATRL = new HashMap<String, String>();
//        paramsATRL.put("glblCmpyCd", this.glblCmpyCd);
//        paramsATRL.put("dsAcctTpCd", this.directSalesAccountType);
//
//        try {
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("getDeleteATRLData", paramsATRL, execParam);
//            rs = stmtSelect.executeQuery();
//
//            int deleteCount = 0;
//            List<ACCT_TRTY_RESRC_LOGTMsg> deleteTMsgArray = new ArrayList<ACCT_TRTY_RESRC_LOGTMsg>();
//
//            while (rs.next()) {
//                ACCT_TRTY_RESRC_LOGTMsg deleteTMsg = new ACCT_TRTY_RESRC_LOGTMsg();
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.locNum, rs.getString("LOC_NUM"));
//                deleteTMsgArray.add(deleteTMsg);
//                deleteCount++;
//
//                if (deleteCount >= FETCH_SIZE) {
//                    S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new ACCT_TRTY_RESRC_LOGTMsg[deleteCount]));
//                    // Add Start 2016/07/20 QC#12113
//                    commit();
//                    deleteTMsgArray.clear();
//                    // Add End 2016/07/20 QC#12113
//                    deleteCount = 0;
//                }
//            }
//
//            if (deleteCount > 0) {
//                S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new ACCT_TRTY_RESRC_LOGTMsg[deleteCount]));
//                // Add Start 2016/07/20 QC#12113
//                commit();
//                deleteTMsgArray.clear();
//                // Add End 2016/07/20 QC#12113
//            }
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteATRLData", paramsATRL);
//            int deleteCount = stmtSelect.executeUpdate();
//            S21InfoLogOutput.println("ACCT_TRTY_RESRC_LOG delete count: [" + deleteCount + "], dsAcctTpCd = [" + this.directSalesAccountType + "]" );
//            commit();
//
//        } catch (SQLException e) {
//            throw new S21AbendException(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
//        }
        // Mod End 2016/07/05 QC#5909

        // Mod Start 2016/07/06 QC#5909
        // ACCT_TRTY_RESRC_ASGTMsg searchCondResrc = new
        // ACCT_TRTY_RESRC_ASGTMsg();
        // searchCondResrc.setSQLID("002");
        // searchCondResrc.setConditionValue("glblCmpyCd01",
        // this.glblCmpyCd);
        // searchCondResrc.setConditionValue("dsAcctTpCd01",
        // this.directSalesAccountType);
        // ACCT_TRTY_RESRC_ASGTMsgArray selectResrcTMsgs =
        // (ACCT_TRTY_RESRC_ASGTMsgArray)
        // EZDTBLAccessor.findByCondition(searchCondResrc);

        // MOD START 2016/03/29 QC#5909
        // ACCT_TRTY_RESRC_LOGTMsg[] insertResrcTMsgs = new
        // ACCT_TRTY_RESRC_LOGTMsg[selectResrcTMsgs.getValidCount()];
        // ACCT_TRTY_RESRC_LOGTMsg insertResrcTMsg = new
        // ACCT_TRTY_RESRC_LOGTMsg();
        // int commitCount = 0;
        // // MOD END 2016/03/29 QC#5909
        // for (int i = 0; i < selectResrcTMsgs.getValidCount(); i++)
        // {
        // // ADD START 2016/03/29 QC#5909
        // totalCount++;
        // // ADD END 2016/03/29 QC#5909
        // ACCT_TRTY_RESRC_ASGTMsg selectedResrcTMsg =
        // (ACCT_TRTY_RESRC_ASGTMsg) selectResrcTMsgs.get(i);
        // // MOD START 2016/03/29 QC#5909
        // // ACCT_TRTY_RESRC_LOGTMsg insertResrcTMsg = new
        // ACCT_TRTY_RESRC_LOGTMsg();
        // insertResrcTMsg.clear();
        // // MOD END 2016/03/29 QC#5909
        //
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezTableID,
        // selectedResrcTMsg.ezTableID);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezCancelFlag,
        // selectedResrcTMsg.ezCancelFlag);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInTime,
        // selectedResrcTMsg.ezInTime);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInTimeZone,
        // selectedResrcTMsg.ezInTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInCompanyCode,
        // selectedResrcTMsg.ezInCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInUserID,
        // selectedResrcTMsg.ezInUserID);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpAplID,
        // selectedResrcTMsg.ezUpAplID);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpTime,
        // selectedResrcTMsg.ezUpTime);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpTimeZone,
        // selectedResrcTMsg.ezUpTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpCompanyCode,
        // selectedResrcTMsg.ezUpCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpUserID,
        // selectedResrcTMsg.ezUpUserID);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpAplID,
        // selectedResrcTMsg.ezUpAplID);
        //
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.glblCmpyCd,
        // selectedResrcTMsg.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNum,
        // selectedResrcTMsg.dsAcctNum);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNm,
        // selectedResrcTMsg.dsAcctNm);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.locNum,
        // selectedResrcTMsg.locNum);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctTpCd,
        // selectedResrcTMsg.dsAcctTpCd);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.glblCmpyCd,
        // selectedResrcTMsg.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNum,
        // selectedResrcTMsg.dsAcctNum);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNm,
        // selectedResrcTMsg.dsAcctNm);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.locNum,
        // selectedResrcTMsg.locNum);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctTpCd,
        // selectedResrcTMsg.dsAcctTpCd);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_01,
        // selectedResrcTMsg.acctTrtyOrgCd_01);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_01,
        // selectedResrcTMsg.acctTrtyPsnCd_01);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_01,
        // selectedResrcTMsg.acctTrtyTocCd_01);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_02,
        // selectedResrcTMsg.acctTrtyOrgCd_02);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_02,
        // selectedResrcTMsg.acctTrtyPsnCd_02);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_02,
        // selectedResrcTMsg.acctTrtyTocCd_02);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_03,
        // selectedResrcTMsg.acctTrtyOrgCd_03);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_03,
        // selectedResrcTMsg.acctTrtyPsnCd_03);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_03,
        // selectedResrcTMsg.acctTrtyTocCd_03);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_04,
        // selectedResrcTMsg.acctTrtyOrgCd_04);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_04,
        // selectedResrcTMsg.acctTrtyPsnCd_04);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_04,
        // selectedResrcTMsg.acctTrtyTocCd_04);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_05,
        // selectedResrcTMsg.acctTrtyOrgCd_05);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_05,
        // selectedResrcTMsg.acctTrtyPsnCd_05);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_05,
        // selectedResrcTMsg.acctTrtyTocCd_05);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_06,
        // selectedResrcTMsg.acctTrtyOrgCd_06);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_06,
        // selectedResrcTMsg.acctTrtyPsnCd_06);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_06,
        // selectedResrcTMsg.acctTrtyTocCd_06);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_07,
        // selectedResrcTMsg.acctTrtyOrgCd_07);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_07,
        // selectedResrcTMsg.acctTrtyPsnCd_07);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_07,
        // selectedResrcTMsg.acctTrtyTocCd_07);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_08,
        // selectedResrcTMsg.acctTrtyOrgCd_08);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_08,
        // selectedResrcTMsg.acctTrtyPsnCd_08);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_08,
        // selectedResrcTMsg.acctTrtyTocCd_08);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_09,
        // selectedResrcTMsg.acctTrtyOrgCd_09);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_09,
        // selectedResrcTMsg.acctTrtyPsnCd_09);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_09,
        // selectedResrcTMsg.acctTrtyTocCd_09);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_10,
        // selectedResrcTMsg.acctTrtyOrgCd_10);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_10,
        // selectedResrcTMsg.acctTrtyPsnCd_10);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_10,
        // selectedResrcTMsg.acctTrtyTocCd_10);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_11,
        // selectedResrcTMsg.acctTrtyOrgCd_11);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_11,
        // selectedResrcTMsg.acctTrtyPsnCd_11);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_11,
        // selectedResrcTMsg.acctTrtyTocCd_11);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_12,
        // selectedResrcTMsg.acctTrtyOrgCd_12);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_12,
        // selectedResrcTMsg.acctTrtyPsnCd_12);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_12,
        // selectedResrcTMsg.acctTrtyTocCd_12);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_13,
        // selectedResrcTMsg.acctTrtyOrgCd_13);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_13,
        // selectedResrcTMsg.acctTrtyPsnCd_13);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_13,
        // selectedResrcTMsg.acctTrtyTocCd_13);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_14,
        // selectedResrcTMsg.acctTrtyOrgCd_14);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_14,
        // selectedResrcTMsg.acctTrtyPsnCd_14);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_14,
        // selectedResrcTMsg.acctTrtyTocCd_14);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_15,
        // selectedResrcTMsg.acctTrtyOrgCd_15);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_15,
        // selectedResrcTMsg.acctTrtyPsnCd_15);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_15,
        // selectedResrcTMsg.acctTrtyTocCd_15);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_16,
        // selectedResrcTMsg.acctTrtyOrgCd_16);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_16,
        // selectedResrcTMsg.acctTrtyPsnCd_16);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_16,
        // selectedResrcTMsg.acctTrtyTocCd_16);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_17,
        // selectedResrcTMsg.acctTrtyOrgCd_17);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_17,
        // selectedResrcTMsg.acctTrtyPsnCd_17);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_17,
        // selectedResrcTMsg.acctTrtyTocCd_17);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_18,
        // selectedResrcTMsg.acctTrtyOrgCd_18);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_18,
        // selectedResrcTMsg.acctTrtyPsnCd_18);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_18,
        // selectedResrcTMsg.acctTrtyTocCd_18);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_19,
        // selectedResrcTMsg.acctTrtyOrgCd_19);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_19,
        // selectedResrcTMsg.acctTrtyPsnCd_19);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_19,
        // selectedResrcTMsg.acctTrtyTocCd_19);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_20,
        // selectedResrcTMsg.acctTrtyOrgCd_20);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_20,
        // selectedResrcTMsg.acctTrtyPsnCd_20);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_20,
        // selectedResrcTMsg.acctTrtyTocCd_20);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_01,
        // selectedResrcTMsg.lineBizRoleTpCd_01);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_02,
        // selectedResrcTMsg.lineBizRoleTpCd_02);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_03,
        // selectedResrcTMsg.lineBizRoleTpCd_03);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_04,
        // selectedResrcTMsg.lineBizRoleTpCd_04);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_05,
        // selectedResrcTMsg.lineBizRoleTpCd_05);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_06,
        // selectedResrcTMsg.lineBizRoleTpCd_06);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_07,
        // selectedResrcTMsg.lineBizRoleTpCd_07);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_08,
        // selectedResrcTMsg.lineBizRoleTpCd_08);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_09,
        // selectedResrcTMsg.lineBizRoleTpCd_09);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_10,
        // selectedResrcTMsg.lineBizRoleTpCd_10);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_11,
        // selectedResrcTMsg.lineBizRoleTpCd_11);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_12,
        // selectedResrcTMsg.lineBizRoleTpCd_12);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_13,
        // selectedResrcTMsg.lineBizRoleTpCd_13);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_14,
        // selectedResrcTMsg.lineBizRoleTpCd_14);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_15,
        // selectedResrcTMsg.lineBizRoleTpCd_15);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_16,
        // selectedResrcTMsg.lineBizRoleTpCd_16);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_17,
        // selectedResrcTMsg.lineBizRoleTpCd_17);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_18,
        // selectedResrcTMsg.lineBizRoleTpCd_18);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_19,
        // selectedResrcTMsg.lineBizRoleTpCd_19);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_20,
        // selectedResrcTMsg.lineBizRoleTpCd_20);
        //
        // // MOD START 2016/03/29 QC#5909
        // // insertResrcTMsgs[i] = insertResrcTMsg;
        // S21FastTBLAccessor.insert(insertResrcTMsg);
        // if
        // (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertResrcTMsg.getReturnCode()))
        // {
        // throw new S21AbendException("MMAM0003E", new String[]
        // {"ACCT_TRTY_RESRC_LOG"});
        // }
        // if (commitCount >= this.commitTransactionCount) {
        // commit();
        // totalNmlCount = totalNmlCount + commitCount;
        // commitCount = 0;
        //
        // }
        // commitCount++;
        // // MOD END 2016/03/29 QC#5909
        // }

// Del Start 2017/05/09 RS#8275
//        rs = null;
//        stmtSelect = null;
//        Map<String, String> insertParamsATRA = new HashMap<String, String>();
//        insertParamsATRA.put("glblCmpyCd", this.glblCmpyCd);
//        insertParamsATRA.put("dsAcctTpCd", this.directSalesAccountType);
//
//        try {
//// QC#12571
////            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
////            execParam.setFetchSize(FETCH_SIZE);
////            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
////            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
////            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
////
////            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertATRA", insertParamsATRA, execParam);
////            rs = stmtSelect.executeQuery();
////
////            List<ACCT_TRTY_RESRC_LOGTMsg> insertResrcTMsgList = new ArrayList<ACCT_TRTY_RESRC_LOGTMsg>();
////
////            while (rs.next()) {
////                totalCount++;
////                ACCT_TRTY_RESRC_LOGTMsg insertResrcTMsg = new ACCT_TRTY_RESRC_LOGTMsg();
////
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezTableID, rs.getString("EZTABLEID"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezCancelFlag, rs.getString("EZCANCELFLAG"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInTime, rs.getString("EZINTIME"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInTimeZone, rs.getString("EZINTIMEZONE"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInCompanyCode, rs.getString("EZINCOMPANYCODE"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInUserID, rs.getString("EZINUSERID"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpAplID, rs.getString("EZINAPLID"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpTime, rs.getString("EZUPTIME"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpTimeZone, rs.getString("EZUPTIMEZONE"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpCompanyCode, rs.getString("EZUPCOMPANYCODE"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpUserID, rs.getString("EZUPUSERID"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpAplID, rs.getString("EZUPAPLID"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.locNum, rs.getString("LOC_NUM"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctTpCd, rs.getString("DS_ACCT_TP_CD"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_01, rs.getString("ACCT_TRTY_ORG_CD_01"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_01, rs.getString("ACCT_TRTY_PSN_CD_01"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_01, rs.getString("ACCT_TRTY_TOC_CD_01"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_02, rs.getString("ACCT_TRTY_ORG_CD_02"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_02, rs.getString("ACCT_TRTY_PSN_CD_02"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_02, rs.getString("ACCT_TRTY_TOC_CD_02"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_03, rs.getString("ACCT_TRTY_ORG_CD_03"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_03, rs.getString("ACCT_TRTY_PSN_CD_03"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_03, rs.getString("ACCT_TRTY_TOC_CD_03"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_04, rs.getString("ACCT_TRTY_ORG_CD_04"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_04, rs.getString("ACCT_TRTY_PSN_CD_04"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_04, rs.getString("ACCT_TRTY_TOC_CD_04"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_05, rs.getString("ACCT_TRTY_ORG_CD_05"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_05, rs.getString("ACCT_TRTY_PSN_CD_05"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_05, rs.getString("ACCT_TRTY_TOC_CD_05"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_06, rs.getString("ACCT_TRTY_ORG_CD_06"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_06, rs.getString("ACCT_TRTY_PSN_CD_06"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_06, rs.getString("ACCT_TRTY_TOC_CD_06"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_07, rs.getString("ACCT_TRTY_ORG_CD_07"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_07, rs.getString("ACCT_TRTY_PSN_CD_07"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_07, rs.getString("ACCT_TRTY_TOC_CD_07"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_08, rs.getString("ACCT_TRTY_ORG_CD_08"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_08, rs.getString("ACCT_TRTY_PSN_CD_08"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_08, rs.getString("ACCT_TRTY_TOC_CD_08"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_09, rs.getString("ACCT_TRTY_ORG_CD_09"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_09, rs.getString("ACCT_TRTY_PSN_CD_09"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_09, rs.getString("ACCT_TRTY_TOC_CD_09"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_10, rs.getString("ACCT_TRTY_ORG_CD_10"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_10, rs.getString("ACCT_TRTY_PSN_CD_10"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_10, rs.getString("ACCT_TRTY_TOC_CD_10"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_11, rs.getString("ACCT_TRTY_ORG_CD_11"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_11, rs.getString("ACCT_TRTY_PSN_CD_11"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_11, rs.getString("ACCT_TRTY_TOC_CD_11"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_12, rs.getString("ACCT_TRTY_ORG_CD_12"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_12, rs.getString("ACCT_TRTY_PSN_CD_12"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_12, rs.getString("ACCT_TRTY_TOC_CD_12"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_13, rs.getString("ACCT_TRTY_ORG_CD_13"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_13, rs.getString("ACCT_TRTY_PSN_CD_13"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_13, rs.getString("ACCT_TRTY_TOC_CD_13"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_14, rs.getString("ACCT_TRTY_ORG_CD_14"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_14, rs.getString("ACCT_TRTY_PSN_CD_14"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_14, rs.getString("ACCT_TRTY_TOC_CD_14"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_15, rs.getString("ACCT_TRTY_ORG_CD_15"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_15, rs.getString("ACCT_TRTY_PSN_CD_15"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_15, rs.getString("ACCT_TRTY_TOC_CD_15"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_16, rs.getString("ACCT_TRTY_ORG_CD_16"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_16, rs.getString("ACCT_TRTY_PSN_CD_16"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_16, rs.getString("ACCT_TRTY_TOC_CD_16"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_17, rs.getString("ACCT_TRTY_ORG_CD_17"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_17, rs.getString("ACCT_TRTY_PSN_CD_17"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_17, rs.getString("ACCT_TRTY_TOC_CD_17"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_18, rs.getString("ACCT_TRTY_ORG_CD_18"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_18, rs.getString("ACCT_TRTY_PSN_CD_18"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_18, rs.getString("ACCT_TRTY_TOC_CD_18"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_19, rs.getString("ACCT_TRTY_ORG_CD_19"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_19, rs.getString("ACCT_TRTY_PSN_CD_19"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_19, rs.getString("ACCT_TRTY_TOC_CD_19"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_20, rs.getString("ACCT_TRTY_ORG_CD_20"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_20, rs.getString("ACCT_TRTY_PSN_CD_20"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_20, rs.getString("ACCT_TRTY_TOC_CD_20"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_01, rs.getString("LINE_BIZ_ROLE_TP_CD_01"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_02, rs.getString("LINE_BIZ_ROLE_TP_CD_02"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_03, rs.getString("LINE_BIZ_ROLE_TP_CD_03"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_04, rs.getString("LINE_BIZ_ROLE_TP_CD_04"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_05, rs.getString("LINE_BIZ_ROLE_TP_CD_05"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_06, rs.getString("LINE_BIZ_ROLE_TP_CD_06"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_07, rs.getString("LINE_BIZ_ROLE_TP_CD_07"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_08, rs.getString("LINE_BIZ_ROLE_TP_CD_08"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_09, rs.getString("LINE_BIZ_ROLE_TP_CD_09"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_10, rs.getString("LINE_BIZ_ROLE_TP_CD_10"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_11, rs.getString("LINE_BIZ_ROLE_TP_CD_11"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_12, rs.getString("LINE_BIZ_ROLE_TP_CD_12"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_13, rs.getString("LINE_BIZ_ROLE_TP_CD_13"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_14, rs.getString("LINE_BIZ_ROLE_TP_CD_14"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_15, rs.getString("LINE_BIZ_ROLE_TP_CD_15"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_16, rs.getString("LINE_BIZ_ROLE_TP_CD_16"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_17, rs.getString("LINE_BIZ_ROLE_TP_CD_17"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_18, rs.getString("LINE_BIZ_ROLE_TP_CD_18"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_19, rs.getString("LINE_BIZ_ROLE_TP_CD_19"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_20, rs.getString("LINE_BIZ_ROLE_TP_CD_20"));
////
////                insertResrcTMsgList.add(insertResrcTMsg);
////
////                if (insertResrcTMsgList.size() >= this.commitTransactionCount) {
////                    int resCnt = S21FastTBLAccessor.insert(insertResrcTMsgList.toArray(new ACCT_TRTY_RESRC_LOGTMsg[0]));
////                    if (resCnt != insertResrcTMsgList.size()) {
////                        rollback();
////                        throw new S21AbendException("MMAM0003E", new String[] {"ACCT_TRTY_RESRC_LOG" });
////                    } else {
////                        commit();
////                        this.totalNmlCount += insertResrcTMsgList.size();
////                    }
////                    insertResrcTMsgList.clear();
////                }
////            }
//
//            String dateTime = EZDDBCICarrier.getStartDateTime();
//            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
//            String upPgId = EZDDBCICarrier.getUppgID();
//            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
//            String userId = EZDDBCICarrier.getUserID();
//
//            insertParamsATRA.put("ezintime", dateTime);
//            insertParamsATRA.put("ezintimezone", upTimeZone);
//            insertParamsATRA.put("ezincompanycode", upCmpyCd);
//            insertParamsATRA.put("ezinuserid", userId);
//            insertParamsATRA.put("ezinaplid", upPgId);
//
//            insertParamsATRA.put("ezuptime", dateTime);
//            insertParamsATRA.put("ezuptimezone", upTimeZone);
//            insertParamsATRA.put("ezupcompanycode", upCmpyCd);
//            insertParamsATRA.put("ezupuserid", userId);
//            insertParamsATRA.put("ezupaplid", upPgId);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertATRA", insertParamsATRA);
//            int count = stmtSelect.executeUpdate();
//            this.totalNmlCount += count;
//            this.totalCount += count;
//            commit();
//
//        } catch (SQLException e) {
//            throw new S21AbendException(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
//        }
//        // Mod End 2016/07/06 QC#5909
//        // MOD START 2016/03/29 QC#5909
//        // if (insertResrcTMsgs.length > 0) {
//        // int insCnt = S21FastTBLAccessor.insert(insertResrcTMsgs);
//        // this.totalCount += insCnt;
//        // this.totalNmlCount += insCnt;
//        // }
//        commit();
//        // totalNmlCount = totalNmlCount + commitCount;
//        // MOD END 2016/03/29 QC#5909

//        // Mod Start 2016/07/20 QC#12113
//        rs = null;
//        stmtSelect = null;
//
//        try {
//            Map<String, String> ssmGetRoleNonExistsParam = new HashMap<String, String>();
//            ssmGetRoleNonExistsParam.put("glblCmpyCd", this.glblCmpyCd);
//            ssmGetRoleNonExistsParam.put("dsAcctTpCd", this.directSalesAccountType);
//// QC#12571
//////            List<Map<String, String>> deleteResrcList = this.ssmBatchClient.queryObjectList("getRoleNonExists", ssmGetRoleNonExistsParam);
////            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
////            execParam.setFetchSize(FETCH_SIZE);
////            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
////            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
////            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
////
////            stmtSelect = this.ssmLLClient.createPreparedStatement("getRoleNonExists", ssmGetRoleNonExistsParam, execParam);
////            rs = stmtSelect.executeQuery();
////
//////            if (deleteResrcList.size() > 0) {
//////                ACCT_TRTY_RESRC_ASGTMsg[] deleteResrcArray = new ACCT_TRTY_RESRC_ASGTMsg[deleteResrcList.size()];
//////                for (int i = 0, n = deleteResrcList.size(); i < n; i++) {
//////                    Map<String, String> deleteResrcMap = deleteResrcList.get(i);
//////                    ACCT_TRTY_RESRC_ASGTMsg deleteResrcTmsg = new ACCT_TRTY_RESRC_ASGTMsg();
//////                    ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.glblCmpyCd, deleteResrcMap.get("GLBL_CMPY_CD"));
//////                    ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.dsAcctNum, deleteResrcMap.get("DS_ACCT_NUM"));
//////                    ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.dsAcctNm, deleteResrcMap.get("DS_ACCT_NM"));
//////                    ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.locNum, deleteResrcMap.get("LOC_NUM"));
//////                    deleteResrcArray[i] = deleteResrcTmsg;
//////                }
//////                S21FastTBLAccessor.removePhysical(deleteResrcArray);
//////            }
////
////            List<ACCT_TRTY_RESRC_ASGTMsg> deleteResrcList = new ArrayList<ACCT_TRTY_RESRC_ASGTMsg>();
////            int deleteCount = 0;
////            while(rs.next()) {
////                ACCT_TRTY_RESRC_ASGTMsg deleteResrcTmsg = new ACCT_TRTY_RESRC_ASGTMsg();
////
////                ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
////                ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
////                ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
////                ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.locNum, rs.getString("LOC_NUM"));
////
////                deleteResrcList.add(deleteResrcTmsg);
////                deleteCount++;
////
////                if (deleteCount >= FETCH_SIZE) {
////                    S21FastTBLAccessor.removePhysical(deleteResrcList.toArray(new ACCT_TRTY_RESRC_ASGTMsg[deleteCount]));
////                    commit();
////                    deleteResrcList.clear();
////                    deleteCount = 0;
////                }
////            }
////
////            if (deleteCount > 0) {
////                S21FastTBLAccessor.removePhysical(deleteResrcList.toArray(new ACCT_TRTY_RESRC_ASGTMsg[deleteCount]));
////                commit();
////                deleteResrcList.clear();
////                deleteCount = 0;
////            }
//
//            // 2016/08/24 CSA-QC#12063, 13277 Mod Start
////            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteRoleNonExists", ssmGetRoleNonExistsParam);
//            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteAcctTrtyResrcAsg", ssmGetRoleNonExistsParam);
//            // 2016/08/24 CSA-QC#12063, 13277 Mod End
//            int deleteCount = stmtSelect.executeUpdate();
//            S21InfoLogOutput.println("ACCT_TRTY_RESRC_ASG delete count: [" + deleteCount + "]");
//            commit();
//
//        } catch (SQLException e) {
//            throw new S21AbendException(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
//        }
        // Del End 2017/05/09 RS#8275
    }

    /**
     * Process for Direct Sales Account Type : Customer.
     */
    private void procCustomer() {
        // Mod Start 2016/07/05 QC#5909
        // ACCT_TRTY_ROLE_ASG_LOGTMsg deleteLogTMsg = new
        // ACCT_TRTY_ROLE_ASG_LOGTMsg();
        // ZYPEZDItemValueSetter.setValue(deleteLogTMsg.glblCmpyCd,
        // this.glblCmpyCd);
        // S21FastTBLAccessor.removeByPartialValue(deleteLogTMsg, new
        // String[] {"glblCmpyCd" });

        Map<String, String> paramsATRAL = new HashMap<String, String>();
        paramsATRAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsATRAL.put("tableNameATRAL", TABLE_ACCT_TRTY_ROLE_ASG_LOG);
        ssmBatchClientCustom.delete("truncateTable", paramsATRAL);
        // Mod End 2016/07/05 QC#5909

        // Mod Start 2016/07/06 QC#5909
        // ACCT_TRTY_ROLE_ASGTMsg searchCond = new
        // ACCT_TRTY_ROLE_ASGTMsg();
        // searchCond.setSQLID("001");
        // searchCond.setConditionValue("glblCmpyCd01",
        // this.glblCmpyCd);
        // ACCT_TRTY_ROLE_ASGTMsgArray selectTMsgs =
        // (ACCT_TRTY_ROLE_ASGTMsgArray)
        // EZDTBLAccessor.findByCondition(searchCond);
        // // MOD START 2016/03/29 QC#5909
        // // ACCT_TRTY_ROLE_ASG_LOGTMsg[] insertTMsgs = new
        // ACCT_TRTY_ROLE_ASG_LOGTMsg[selectTMsgs.getValidCount()];
        // ACCT_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new
        // ACCT_TRTY_ROLE_ASG_LOGTMsg();
        // int commitCount = 0;
        // // MOD END 2016/03/29 QC#5909
        // for (int i = 0; i < selectTMsgs.getValidCount(); i++) {
        // // ADD START 2016/03/29 QC#5909
        // totalCount++;
        // // ADD END 2016/03/29 QC#5909
        // ACCT_TRTY_ROLE_ASGTMsg selectedTMsg =
        // (ACCT_TRTY_ROLE_ASGTMsg) selectTMsgs.get(i);
        // // MOD START 2016/03/29 QC#5909
        // // ACCT_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new
        // ACCT_TRTY_ROLE_ASG_LOGTMsg();
        // insertTMsg.clear();
        // // MOD END 2016/03/29 QC#5909
        //
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID,
        // selectedTMsg.ezTableID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag,
        // selectedTMsg.ezCancelFlag);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime,
        // selectedTMsg.ezInTime);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTimeZone,
        // selectedTMsg.ezInTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode,
        // selectedTMsg.ezInCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID,
        // selectedTMsg.ezInUserID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID,
        // selectedTMsg.ezUpAplID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime,
        // selectedTMsg.ezUpTime);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone,
        // selectedTMsg.ezUpTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode,
        // selectedTMsg.ezUpCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID,
        // selectedTMsg.ezUpUserID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID,
        // selectedTMsg.ezUpAplID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd,
        // selectedTMsg.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgLogPk,
        // selectedTMsg.acctTrtyRoleAsgPk);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum,
        // selectedTMsg.dsAcctNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.locNum,
        // selectedTMsg.locNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd,
        // selectedTMsg.orgCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd,
        // selectedTMsg.lineBizRoleTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm,
        // selectedTMsg.dsAcctNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd,
        // selectedTMsg.billToCustCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd,
        // selectedTMsg.shipToCustCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd,
        // selectedTMsg.dsAcctTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd,
        // selectedTMsg.dsAcctClsCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd,
        // selectedTMsg.firstDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd,
        // selectedTMsg.scdDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd,
        // selectedTMsg.thirdDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd,
        // selectedTMsg.frthDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd,
        // selectedTMsg.fifthDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd,
        // selectedTMsg.indyTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd,
        // selectedTMsg.dsCustSicCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr,
        // selectedTMsg.firstLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr,
        // selectedTMsg.scdLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr,
        // selectedTMsg.thirdLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr,
        // selectedTMsg.frthLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr,
        // selectedTMsg.ctyAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.stCd,
        // selectedTMsg.stCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.provNm,
        // selectedTMsg.provNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm,
        // selectedTMsg.cntyNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.postCd,
        // selectedTMsg.postCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm,
        // selectedTMsg.orgNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd,
        // selectedTMsg.trtyGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum,
        // selectedTMsg.orgRankNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd,
        // selectedTMsg.tocCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd,
        // selectedTMsg.psnCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg,
        // selectedTMsg.manEntryFlg);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd,
        // selectedTMsg.trtyTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd,
        // selectedTMsg.asgTrtyAttrbCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg,
        // selectedTMsg.nonSlsRepFlg);
        // // MOD START 2016/03/29 QC#5909
        // // insertTMsgs[i] = insertTMsg;
        // S21FastTBLAccessor.insert(insertTMsg);
        // if
        // (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode()))
        // {
        // throw new S21AbendException("MMAM0003E", new String[]
        // {"ACCT_TRTY_ROLE_ASG_LOG"});
        // }
        // if (commitCount >= this.commitTransactionCount) {
        // commit();
        // totalNmlCount = totalNmlCount + commitCount;
        // commitCount = 0;
        //
        // }
        // commitCount++;
        // // MOD END 2016/03/29 QC#5909
        // }

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        Map<String, String> insertParamsATRA = new HashMap<String, String>();
        insertParamsATRA.put("glblCmpyCd", this.glblCmpyCd);
        insertParamsATRA.put("tableNameATRA", TABLE_ACCT_TRTY_ROLE_ASG);

        try {
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBL", insertParamsATRA, execParam);
//            rs = stmtSelect.executeQuery();
//
//            List<ACCT_TRTY_ROLE_ASG_LOGTMsg> insertTMsgList = new ArrayList<ACCT_TRTY_ROLE_ASG_LOGTMsg>();
//
//            while (rs.next()) {
//                totalCount++;
//                ACCT_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new ACCT_TRTY_ROLE_ASG_LOGTMsg();
//
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID, rs.getString("EZTABLEID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag, rs.getString("EZCANCELFLAG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime, rs.getString("EZINTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTimeZone, rs.getString("EZINTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode, rs.getString("EZINCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID, rs.getString("EZINUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZINAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime, rs.getString("EZUPTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone, rs.getString("EZUPTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode, rs.getString("EZUPCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID, rs.getString("EZUPUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZUPAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgLogPk, rs.getBigDecimal("ACCT_TRTY_ROLE_ASG_PK"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, rs.getString("LOC_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, rs.getString("ORG_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd, rs.getString("LINE_BIZ_ROLE_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd, rs.getString("SHIP_TO_CUST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, rs.getString("DS_ACCT_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd, rs.getString("DS_ACCT_CLS_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd, rs.getString("FIRST_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd, rs.getString("SCD_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd, rs.getString("THIRD_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd, rs.getString("FRTH_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd, rs.getString("FIFTH_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd, rs.getString("INDY_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr, rs.getString("CTY_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.stCd, rs.getString("ST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.provNm, rs.getString("PROV_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm, rs.getString("CNTY_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.postCd, rs.getString("POST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm, rs.getString("ORG_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, rs.getString("TRTY_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum, rs.getString("ORG_RANK_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd, rs.getString("TOC_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd, rs.getString("PSN_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, rs.getString("MAN_ENTRY_FLG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, rs.getString("TRTY_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd, rs.getString("ASG_TRTY_ATTRB_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg, rs.getString("NON_SLS_REP_FLG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizTpCd, rs.getString("LINE_BIZ_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
//
//                insertTMsgList.add(insertTMsg);
//
//                if (insertTMsgList.size() >= this.commitTransactionCount) {
//                    int resCnt = S21FastTBLAccessor.insert(insertTMsgList.toArray(new ACCT_TRTY_ROLE_ASG_LOGTMsg[0]));
//                    if (resCnt != insertTMsgList.size()) {
//                        rollback();
//                        throw new S21AbendException("MMAM0003E", new String[] {"ACCT_TRTY_ROLE_ASG_LOG" });
//                    } else {
//                        commit();
//                        this.totalNmlCount += insertTMsgList.size();
//                    }
//                    insertTMsgList.clear();
//                }
//            }

            String dateTime = EZDDBCICarrier.getStartDateTime();
            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String upPgId = EZDDBCICarrier.getUppgID();
            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();

            insertParamsATRA.put("ezintime", dateTime);
            insertParamsATRA.put("ezintimezone", upTimeZone);
            insertParamsATRA.put("ezincompanycode", upCmpyCd);
            insertParamsATRA.put("ezinuserid", userId);
            insertParamsATRA.put("ezinaplid", upPgId);

            insertParamsATRA.put("ezuptime", dateTime);
            insertParamsATRA.put("ezuptimezone", upTimeZone);
            insertParamsATRA.put("ezupcompanycode", upCmpyCd);
            insertParamsATRA.put("ezupuserid", userId);
            insertParamsATRA.put("ezupaplid", upPgId);

            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBL", insertParamsATRA);
            int count = stmtSelect.executeUpdate();
            this.totalNmlCount += count;
            this.totalCount += count;
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // Mod End 2016/07/06 QC#5909

        // MOD START 2016/03/29 QC#5909
        // if (insertTMsgs.length > 0) {
        // int insCnt = S21FastTBLAccessor.insert(insertTMsgs);
        // this.totalCount += insCnt;
        // this.totalNmlCount += insCnt;
        // }
        // MOD END 2016/03/29 QC#5909

        // Mod Start 2016/07/05 QC#5909
        // ACCT_TRTY_ROLE_ASGTMsg deleteTMsg = new
        // ACCT_TRTY_ROLE_ASGTMsg();
        // ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd,
        // this.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(deleteTMsg.manEntryFlg,
        // ZYPConstant.FLG_OFF_N);
        // S21FastTBLAccessor.removeByPartialValue(deleteTMsg, new
        // String[] {"glblCmpyCd", "manEntryFlg" });

        rs = null;
        stmtSelect = null;
        Map<String, String> paramsATRA = new HashMap<String, String>();
        paramsATRA.put("glblCmpyCd", this.glblCmpyCd);
        paramsATRA.put("manEntryFlg", ZYPConstant.FLG_OFF_N);
        paramsATRA.put("tableNameATRA", TABLE_ACCT_TRTY_ROLE_ASG);

        try {
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("getDeleteTableData", paramsATRA, execParam);
//            rs = stmtSelect.executeQuery();
//
//            int deleteCount = 0;
//            List<ACCT_TRTY_ROLE_ASGTMsg> deleteTMsgArray = new ArrayList<ACCT_TRTY_ROLE_ASGTMsg>();
//
//            while (rs.next()) {
//                ACCT_TRTY_ROLE_ASGTMsg deleteTMsg = new ACCT_TRTY_ROLE_ASGTMsg();
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.acctTrtyRoleAsgPk, rs.getBigDecimal("ACCT_TRTY_ROLE_ASG_PK"));
//                deleteTMsgArray.add(deleteTMsg);
//                deleteCount++;
//
//                if (deleteCount >= FETCH_SIZE) {
//                    S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new ACCT_TRTY_ROLE_ASGTMsg[deleteCount]));
//                    // Add Start 2016/07/20 QC#12113
//                    commit();
//                    deleteTMsgArray.clear();
//                    // Add End 2016/07/20 QC#12113
//                    deleteCount = 0;
//                }
//            }
//
//            if (deleteCount > 0) {
//                S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new ACCT_TRTY_ROLE_ASGTMsg[deleteCount]));
//                // Add Start 2016/07/20 QC#12113
//                commit();
//                deleteTMsgArray.clear();
//                // Add End 2016/07/20 QC#12113
//            }
            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteTableData", paramsATRA);
            int deleteCount = stmtSelect.executeUpdate();
            S21InfoLogOutput.println("ACCT_TRTY_ROLE_ASG delete count: [" + deleteCount + "]");
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // Mod End 2016/07/05 QC#5909


        // Add Start 2017/05/09 RS#8275
        Map<String, String> paramsATSAL = new HashMap<String, String>();
        paramsATSAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsATSAL.put("tableNameATSAL", TABLE_ACCT_TRTY_RESRC_LOG);
        ssmBatchClientCustom.delete("truncateTable", paramsATSAL);

        rs = null;
        stmtSelect = null;
        Map<String, String> insertParamsATRAL = new HashMap<String, String>();
        insertParamsATRAL.put("glblCmpyCd", this.glblCmpyCd);

        try {
            String dateTime = EZDDBCICarrier.getStartDateTime();
            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String upPgId = EZDDBCICarrier.getUppgID();
            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();

            insertParamsATRAL.put("ezintime", dateTime);
            insertParamsATRAL.put("ezintimezone", upTimeZone);
            insertParamsATRAL.put("ezincompanycode", upCmpyCd);
            insertParamsATRAL.put("ezinuserid", userId);
            insertParamsATRAL.put("ezinaplid", upPgId);

            insertParamsATRAL.put("ezuptime", dateTime);
            insertParamsATRAL.put("ezuptimezone", upTimeZone);
            insertParamsATRAL.put("ezupcompanycode", upCmpyCd);
            insertParamsATRAL.put("ezupuserid", userId);
            insertParamsATRAL.put("ezupaplid", upPgId);

            insertParamsATRAL.put("tableNameATRA", TABLE_ACCT_TRTY_RESRC_ASG);

            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertRsc", insertParamsATRAL);
            int count = stmtSelect.executeUpdate();
            this.totalNmlCount += count;
            this.totalCount += count;
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        Map<String, String> paramsATRSA = new HashMap<String, String>();
        paramsATRSA.put("glblCmpyCd", this.glblCmpyCd);
        paramsATRSA.put("tableNameATSA", TABLE_ACCT_TRTY_RESRC_ASG);
        ssmBatchClientCustom.delete("truncateTable", paramsATRSA);
        // Add End 2017/05/09 RS#8275

        // ADD START 2016/03/29 QC#5909
        commit();
        // totalNmlCount = totalNmlCount + commitCount;
        // ADD END 2016/03/29 QC#5909
    }

    /**
     * Process for Direct Sales Account Type : Prospect.
     */
    private void procProspect() {
        // Mod Start 2016/07/05 QC#5909
        // PROS_TRTY_ROLE_ASG_LOGTMsg deleteLogTMsg = new
        // PROS_TRTY_ROLE_ASG_LOGTMsg();
        // ZYPEZDItemValueSetter.setValue(deleteLogTMsg.glblCmpyCd,
        // this.glblCmpyCd);
        // S21FastTBLAccessor.removeByPartialValue(deleteLogTMsg, new
        // String[] {"glblCmpyCd" });
        Map<String, String> paramsPTRAL = new HashMap<String, String>();
        paramsPTRAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTRAL.put("tableNamePTRAL", TABLE_PROS_TRTY_ROLE_ASG_LOG);
        ssmBatchClientCustom.delete("truncateTable", paramsPTRAL);
        // Mod End 2016/07/05 QC#5909

        // Mod Start 2016/07/06 QC#5909
        // PROS_TRTY_ROLE_ASGTMsg searchCond = new
        // PROS_TRTY_ROLE_ASGTMsg();
        // searchCond.setSQLID("001");
        // searchCond.setConditionValue("glblCmpyCd01",
        // this.glblCmpyCd);
        // PROS_TRTY_ROLE_ASGTMsgArray selectTMsgs =
        // (PROS_TRTY_ROLE_ASGTMsgArray)
        // EZDTBLAccessor.findByCondition(searchCond);
        // // MOD START 2016/03/29 QC#5909
        // // PROS_TRTY_ROLE_ASG_LOGTMsg[] insertTMsgs = new
        // PROS_TRTY_ROLE_ASG_LOGTMsg[selectTMsgs.getValidCount()];
        // PROS_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new
        // PROS_TRTY_ROLE_ASG_LOGTMsg();
        // int commitCount = 0;
        // // MOD END 2016/03/29 QC#5909
        // for (int i = 0; i < selectTMsgs.getValidCount(); i++) {
        // // ADD START 2016/03/29 QC#5909
        // totalCount++;
        // // ADD END 2016/03/29 QC#5909
        // PROS_TRTY_ROLE_ASGTMsg selectedTMsg =
        // (PROS_TRTY_ROLE_ASGTMsg) selectTMsgs.get(i);
        // // MOD START 2016/03/29 QC#5909
        // // PROS_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new
        // PROS_TRTY_ROLE_ASG_LOGTMsg();
        // insertTMsg.clear();
        // // MOD END 2016/03/29 QC#5909
        //
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID,
        // selectedTMsg.ezTableID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag,
        // selectedTMsg.ezCancelFlag);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime,
        // selectedTMsg.ezInTime);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTimeZone,
        // selectedTMsg.ezInTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode,
        // selectedTMsg.ezInCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID,
        // selectedTMsg.ezInUserID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID,
        // selectedTMsg.ezUpAplID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime,
        // selectedTMsg.ezUpTime);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone,
        // selectedTMsg.ezUpTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode,
        // selectedTMsg.ezUpCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID,
        // selectedTMsg.ezUpUserID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID,
        // selectedTMsg.ezUpAplID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd,
        // selectedTMsg.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgLogPk,
        // selectedTMsg.acctTrtyRoleAsgPk);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum,
        // selectedTMsg.dsAcctNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.locNum,
        // selectedTMsg.locNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd,
        // selectedTMsg.orgCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd,
        // selectedTMsg.lineBizRoleTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm,
        // selectedTMsg.dsAcctNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd,
        // selectedTMsg.billToCustCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd,
        // selectedTMsg.shipToCustCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd,
        // selectedTMsg.dsAcctTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd,
        // selectedTMsg.dsAcctClsCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd,
        // selectedTMsg.firstDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd,
        // selectedTMsg.scdDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd,
        // selectedTMsg.thirdDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd,
        // selectedTMsg.frthDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd,
        // selectedTMsg.fifthDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd,
        // selectedTMsg.indyTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd,
        // selectedTMsg.dsCustSicCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr,
        // selectedTMsg.firstLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr,
        // selectedTMsg.scdLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr,
        // selectedTMsg.thirdLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr,
        // selectedTMsg.frthLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr,
        // selectedTMsg.ctyAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.stCd,
        // selectedTMsg.stCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.provNm,
        // selectedTMsg.provNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm,
        // selectedTMsg.cntyNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.postCd,
        // selectedTMsg.postCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm,
        // selectedTMsg.orgNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd,
        // selectedTMsg.trtyGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum,
        // selectedTMsg.orgRankNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd,
        // selectedTMsg.tocCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd,
        // selectedTMsg.psnCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg,
        // selectedTMsg.manEntryFlg);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd,
        // selectedTMsg.trtyTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd,
        // selectedTMsg.asgTrtyAttrbCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg,
        // selectedTMsg.nonSlsRepFlg);
        //
        // // MOD START 2016/03/29 QC#5909
        // // insertTMsgs[i] = insertTMsg;
        // S21FastTBLAccessor.insert(insertTMsg);
        // if
        // (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode()))
        // {
        // throw new S21AbendException("MMAM0003E", new String[]
        // {"TRTY_RULE_LOC_RELN_LOG"});
        // }
        // if (commitCount >= this.commitTransactionCount) {
        // commit();
        // totalNmlCount = totalNmlCount + commitCount;
        // commitCount = 0;
        //
        // }
        // commitCount++;
        // // MOD END 2016/03/29 QC#5909
        // }

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        Map<String, String> insertParamsPTRA = new HashMap<String, String>();
        insertParamsPTRA.put("glblCmpyCd", this.glblCmpyCd);
        insertParamsPTRA.put("tableNamePTRA", TABLE_PROS_TRTY_ROLE_ASG);
        int commitCount = 0;

        try {
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBL", insertParamsPTRA, execParam);
//            rs = stmtSelect.executeQuery();
//
//            PROS_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new PROS_TRTY_ROLE_ASG_LOGTMsg();
//
//            while (rs.next()) {
//                totalCount++;
//                insertTMsg.clear();
//
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID, rs.getString("EZTABLEID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag, rs.getString("EZCANCELFLAG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime, rs.getString("EZINTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTimeZone, rs.getString("EZINTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode, rs.getString("EZINCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID, rs.getString("EZINUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZINAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime, rs.getString("EZUPTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone, rs.getString("EZUPTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode, rs.getString("EZUPCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID, rs.getString("EZUPUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZUPAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgLogPk, rs.getBigDecimal("ACCT_TRTY_ROLE_ASG_PK"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, rs.getString("LOC_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, rs.getString("ORG_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd, rs.getString("LINE_BIZ_ROLE_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd, rs.getString("SHIP_TO_CUST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, rs.getString("DS_ACCT_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd, rs.getString("DS_ACCT_CLS_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd, rs.getString("FIRST_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd, rs.getString("SCD_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd, rs.getString("THIRD_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd, rs.getString("FRTH_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd, rs.getString("FIFTH_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd, rs.getString("INDY_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr, rs.getString("CTY_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.stCd, rs.getString("ST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.provNm, rs.getString("PROV_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm, rs.getString("CNTY_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.postCd, rs.getString("POST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm, rs.getString("ORG_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, rs.getString("TRTY_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum, rs.getString("ORG_RANK_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd, rs.getString("TOC_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd, rs.getString("PSN_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, rs.getString("MAN_ENTRY_FLG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, rs.getString("TRTY_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd, rs.getString("ASG_TRTY_ATTRB_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg, rs.getString("NON_SLS_REP_FLG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizTpCd, rs.getString("LINE_BIZ_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
//
//                S21FastTBLAccessor.insert(insertTMsg);
//                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
//                    throw new S21AbendException("MMAM0003E", new String[] {"TRTY_RULE_LOC_RELN_LOG" });
//                }
//                if (commitCount >= this.commitTransactionCount) {
//                    commit();
//                    totalNmlCount = totalNmlCount + commitCount;
//                    commitCount = 0;
//
//                }
//                commitCount++;
//            }
            String dateTime = EZDDBCICarrier.getStartDateTime();
            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String upPgId = EZDDBCICarrier.getUppgID();
            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();

            insertParamsPTRA.put("ezintime", dateTime);
            insertParamsPTRA.put("ezintimezone", upTimeZone);
            insertParamsPTRA.put("ezincompanycode", upCmpyCd);
            insertParamsPTRA.put("ezinuserid", userId);
            insertParamsPTRA.put("ezinaplid", upPgId);

            insertParamsPTRA.put("ezuptime", dateTime);
            insertParamsPTRA.put("ezuptimezone", upTimeZone);
            insertParamsPTRA.put("ezupcompanycode", upCmpyCd);
            insertParamsPTRA.put("ezupuserid", userId);
            insertParamsPTRA.put("ezupaplid", upPgId);

            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBL", insertParamsPTRA);
            int count = stmtSelect.executeUpdate();
            this.totalNmlCount += count;
            this.totalCount += count;
            commit();
            
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // Mod End 2016/07/06 QC#5909

        // DEL START 2016/03/29 QC#5909
        // if (insertTMsgs.length > 0) {
        // int insCnt = S21FastTBLAccessor.insert(insertTMsgs);
        // this.totalCount += insCnt;
        // this.totalNmlCount += insCnt;
        // }
        // DEL END 2016/03/29 QC#5909

        // Mod Start 2016/07/05 QC#5909
        // PROS_TRTY_ROLE_ASGTMsg deleteTMsg = new
        // PROS_TRTY_ROLE_ASGTMsg();
        // ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd,
        // this.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(deleteTMsg.manEntryFlg,
        // ZYPConstant.FLG_OFF_N);
        // S21FastTBLAccessor.removeByPartialValue(deleteTMsg, new
        // String[] {"glblCmpyCd", "manEntryFlg" });
        rs = null;
        stmtSelect = null;
        Map<String, String> paramsPTRA = new HashMap<String, String>();
        paramsPTRA.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTRA.put("manEntryFlg", ZYPConstant.FLG_OFF_N);
        paramsPTRA.put("tableNamePTRA", TABLE_PROS_TRTY_ROLE_ASG);
        try {
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("getDeleteTableData", paramsPTRA, execParam);
//            rs = stmtSelect.executeQuery();
//
//            int deleteCount = 0;
//            List<PROS_TRTY_ROLE_ASGTMsg> deleteTMsgArray = new ArrayList<PROS_TRTY_ROLE_ASGTMsg>();
//
//            while (rs.next()) {
//                PROS_TRTY_ROLE_ASGTMsg deleteTMsg = new PROS_TRTY_ROLE_ASGTMsg();
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.acctTrtyRoleAsgPk, rs.getBigDecimal("ACCT_TRTY_ROLE_ASG_PK"));
//                deleteTMsgArray.add(deleteTMsg);
//                deleteCount++;
//
//                if (deleteCount >= FETCH_SIZE) {
//                    S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new PROS_TRTY_ROLE_ASGTMsg[deleteCount]));
//                    // Add Start 2016/07/20 QC#12113
//                    commit();
//                    deleteTMsgArray.clear();
//                    // Add End 2016/07/20 QC#12113
//                    deleteCount = 0;
//                }
//            }
//
//            if (deleteCount > 0) {
//                S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new PROS_TRTY_ROLE_ASGTMsg[deleteCount]));
//                // Add Start 2016/07/20 QC#12113
//                commit();
//                deleteTMsgArray.clear();
//                // Add End 2016/07/20 QC#12113
//            }
            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteTableData", paramsPTRA);
            int deleteCount = stmtSelect.executeUpdate();
            S21InfoLogOutput.println("PROS_TRTY_ROLE_ASG delete count: [" + deleteCount + "]");
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // Mod End 2016/07/05 QC#5909

        // Add Start 2017/05/09 RS#8275
        Map<String, String> paramsPTSAL = new HashMap<String, String>();
        paramsPTSAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTSAL.put("tableNamePTSAL", TABLE_PROS_TRTY_RESRC_LOG);
        ssmBatchClientCustom.delete("truncateTable", paramsPTSAL);

        rs = null;
        stmtSelect = null;
        Map<String, String> insertParamsPTRAL = new HashMap<String, String>();
        insertParamsPTRAL.put("glblCmpyCd", this.glblCmpyCd);

        try {
            String dateTime = EZDDBCICarrier.getStartDateTime();
            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String upPgId = EZDDBCICarrier.getUppgID();
            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();

            insertParamsPTRAL.put("ezintime", dateTime);
            insertParamsPTRAL.put("ezintimezone", upTimeZone);
            insertParamsPTRAL.put("ezincompanycode", upCmpyCd);
            insertParamsPTRAL.put("ezinuserid", userId);
            insertParamsPTRAL.put("ezinaplid", upPgId);

            insertParamsPTRAL.put("ezuptime", dateTime);
            insertParamsPTRAL.put("ezuptimezone", upTimeZone);
            insertParamsPTRAL.put("ezupcompanycode", upCmpyCd);
            insertParamsPTRAL.put("ezupuserid", userId);
            insertParamsPTRAL.put("ezupaplid", upPgId);

            insertParamsPTRAL.put("tableNamePTRA", TABLE_PROS_TRTY_RESRC_ASG);

            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertRsc", insertParamsPTRAL);
            int count = stmtSelect.executeUpdate();
            this.totalNmlCount += count;
            this.totalCount += count;
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        Map<String, String> paramsPTRSA = new HashMap<String, String>();
        paramsPTRSA.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTRSA.put("tableNamePTSA", TABLE_PROS_TRTY_RESRC_ASG);
        ssmBatchClientCustom.delete("truncateTable", paramsPTRSA);
        // Add End 2017/05/09 RS#8275

        // ADD START 2016/03/29 QC#5909
        commit();
        totalNmlCount = totalNmlCount + commitCount;
        // ADD END 2016/03/29 QC#5909
    }

    // MOD END 2016/03/16 QC#4443

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
        new NMAB265003().executeBatch(NMAB265003.class.getSimpleName());
    }
}
