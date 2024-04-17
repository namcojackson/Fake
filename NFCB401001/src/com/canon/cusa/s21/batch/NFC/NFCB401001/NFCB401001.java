/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB401001;

import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.COMP_DIV_CD_TDS;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.COMP_PROC_STS_COMPLETE;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.DEF_SER_NUM;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.LEVEL_LINE;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.MAX_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.MODE_ACCRUAL;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.MODE_NORMAL;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.NFCM0531E;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.PERCENT;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.SQL_STATEMENT_GET_COMP_INFO_ACCRUAL;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.SQL_STATEMENT_GET_COMP_INFO_NORMAL;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.SQL_STATEMENT_GET_COMP_INFO_NORMAL_RMA;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.STR_01;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.STR_02;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.STR_03;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.VAR_CHAR_CONST_KEY_COMP_IS_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.VAR_CHAR_CONST_KEY_COMP_LFS_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.VAR_CHAR_CONST_KEY_NFCB4010_LFS_COMM_MDSE_ITEM;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.VAR_CHAR_CONST_KEY_SEND_COMP_CPLT_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB401001.constant.NFCB401001Constant.VAR_CHAR_CONST_KEY_WRITER_ORG_CD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.COMP_ACRL_CTRLTMsg;
import business.db.COMP_ACRL_PRMTMsg;
import business.db.INV_LINETMsg;
import business.db.LFS_COMP_ORDTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.SVC_COV_FEAT;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_AFFL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Compensation IF(LFS)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Hitachi         A.Kohinata      Create          N/A
 * 2018/01/29   Fujitsu         T.Murai         Update          S21_NA#22002
 * 2018/05/25   Fujitsu         Hd.Sugawara     Update          QC#21841(Sol#495)
 * 2018/08/20   Fujitsu         W.Honda         Update          S21_NA#25938
 * 2018/09/11   Fujitsu         T.Noguchi       Update          QC#28118,28123
 * 2018/09/21   Fujitsu         T.Noguchi       Update          QC#28232
 * 2018/09/25   Fujitsu         T.Noguchi       Update          QC#28231
 * 2018/10/17   Fujitsu         Hd.Sugawara     Update          QC#28842
 * 2018/10/24   Fujitsu         Hd.Sugawara     Update          QC#28844
 * 2018/10/30   Fujitsu         T.Noguchi       Update          QC#29033
 * 2018/11/09   Fujitsu         Mz.Takahashi    Update          QC#29131
 * 2019/08/15   Fujitsu         S.Yamamoto      Update          QC#52707
 * 2019/09/13   Fujitsu         Hd.Sugawara     Update          QC#52943
 * 2020/01/31   Fujitsu         Y.Kanefusa      Update          S21_NA#55658
 * 2020/12/01   CITS            K.Ogino         Update          QC#58045
 * </pre>
 */

public class NFCB401001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Commit Number */
    private int commitNumber = 0;

    /** User Variable1 */
    private String varUser1 = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Send Compensation Complete Code */
    private String sendCompCpltCd = null;

    /** Send Compensation LFS Business Type Code */
    private String compLfsBizTpCd = null;

    // Add Start 2018/08/20 S21_NA#25938
    /** Send Compensation IS Business Type Code */
    private String compIsBizTpCd = null;
    // Add End 2018/08/20 S21_NA#25938

    /** Writer Org Code */
    private String writerOrgCd = null;

    /** LFS Commissions MDSE List */
    private List<String> lfsCommMdses = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {"Global Company Code" });
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        // Mod Start 2018/10/17 QC#28842
        //if (!hasValue(this.glblCmpyCd)) {
        if (!hasValue(this.slsDt)) {
            // Mod End 2018/10/17 QC#28842
            throw new S21AbendException(NFCM0531E, new String[] {"Sales Date" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get User Variable1
        this.varUser1 = getUserVariable1();
        if (!hasValue(this.varUser1)) {
            throw new S21AbendException(NFCM0531E, new String[] {"User Variable1" });
        }

        // Get Var Char Const (Send Compensation Complete Code)
        this.sendCompCpltCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SEND_COMP_CPLT_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.sendCompCpltCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {VAR_CHAR_CONST_KEY_SEND_COMP_CPLT_CD });
        }

        // Get Var Char Const (Compensation LFS Business Type Code)
        this.compLfsBizTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_COMP_LFS_BIZ_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.compLfsBizTpCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {VAR_CHAR_CONST_KEY_COMP_LFS_BIZ_TP_CD });
        }

        // Add Start 2018/08/20 S21_NA#25938
        // Get Var Char Const (Compensation IS Business Type Code)
        this.compIsBizTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_COMP_IS_BIZ_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.compIsBizTpCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {VAR_CHAR_CONST_KEY_COMP_IS_BIZ_TP_CD });
        }
        // Add End 2018/08/20 S21_NA#25938

        // Get Var Char Const (Writer Org Code)
        this.writerOrgCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_WRITER_ORG_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.writerOrgCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {VAR_CHAR_CONST_KEY_WRITER_ORG_CD });
        }

        // QC#58045
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_NFCB4010_LFS_COMM_MDSE_ITEM, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(constVal)) {
            throw new S21AbendException(NFCM0531E, new String[] {VAR_CHAR_CONST_KEY_NFCB4010_LFS_COMM_MDSE_ITEM });
        } else {
            lfsCommMdses = Arrays.asList(constVal.split(","));
        }

        // Initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        if (MODE_NORMAL.equals(this.varUser1)) {
            // 2018/09/25 QC#28231 Mod Start
            //doNormalMode();
            doNormalMode(SQL_STATEMENT_GET_COMP_INFO_NORMAL);
            doNormalMode(SQL_STATEMENT_GET_COMP_INFO_NORMAL_RMA);
            // 2018/09/25 QC#28231 Mod End
        } else if (MODE_ACCRUAL.equals(this.varUser1)) {
            // 2018/09/25 QC#28231 Mod Start
            //doAccrualMode();
            doAccrualMode(SQL_STATEMENT_GET_COMP_INFO_ACCRUAL);
            // QC#55658 2020/01/31 Del Start
            //doAccrualMode(SQL_STATEMENT_GET_COMP_INFO_ACCRUAL_RMA);
            // QC#55658 2020/01/31 Del End
            // 2018/09/25 QC#28231 Mod End
        }

        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB401001().executeBatch(NFCB401001.class.getSimpleName());

    }

    // 2018/09/25 QC#28231 Mod Start
    //private void doNormalMode() {
    private void doNormalMode(String statementId) {
    // 2018/09/25 QC#28231 Mod End

        List<LFS_COMP_ORDTMsg> lfsCompOrdTMsgList = new ArrayList<LFS_COMP_ORDTMsg>();
        List<INV_LINETMsg> invLineTMsgList = new ArrayList<INV_LINETMsg>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> preInvMap = new HashMap<String, Object>();
        Map<String, Object> errInvMap = new HashMap<String, Object>();

        try {
            // 2018/09/25 QC#28231 Mod Start
            //stmt = this.ssmLLClient.createPreparedStatement("getCompInfoNormal", getParamMapNormal(), getExecParam());
            stmt = this.ssmLLClient.createPreparedStatement(statementId, getParamMapNormal(), getExecParam());
            // 2018/09/25 QC#28231 Mod End
            rs = stmt.executeQuery();

            while (rs.next()) {
                LFS_COMP_ORDTMsg lfsCompOrdTMsg = setLfsCompOrd(rs);
                lfsCompOrdTMsgList.add(lfsCompOrdTMsg);

                if (changeInv(rs, preInvMap) && !isErrInv(preInvMap, errInvMap)) {
                    INV_LINETMsg invLineTMsg = setInvLine(preInvMap);
                    invLineTMsgList.add(invLineTMsg);
                    errInvMap = new HashMap<String, Object>();
                }

                if (lfsCompOrdTMsgList.size() >= this.commitNumber) {
                    int insertCount = insertLfsCompOrd(lfsCompOrdTMsgList);
                    if (insertCount == lfsCompOrdTMsgList.size()) {
                        if (updateInvLine(invLineTMsgList)) {
                            this.normalCount += insertCount;
                            commit();
                        } else {
                            this.errorCount += lfsCompOrdTMsgList.size();
                            rollback();
                            errInvMap = setInvMap(rs);
                        }
                    } else {
                        this.errorCount += lfsCompOrdTMsgList.size();
                        rollback();
                        errInvMap = setInvMap(rs);
                    }
                    lfsCompOrdTMsgList.clear();
                    invLineTMsgList.clear();
                    insertCount = 0;

                }
                preInvMap = setInvMap(rs);
            }

            if (!preInvMap.isEmpty() && !isErrInv(preInvMap, errInvMap)) {
                INV_LINETMsg invLineTMsg = setInvLine(preInvMap);
                invLineTMsgList.add(invLineTMsg);
            }

            if (lfsCompOrdTMsgList.size() > 0) {
                int insertCount = insertLfsCompOrd(lfsCompOrdTMsgList);
                if (insertCount == lfsCompOrdTMsgList.size()) {
                    if (updateInvLine(invLineTMsgList)) {
                        this.normalCount += insertCount;
                        commit();
                    } else {
                        this.errorCount += lfsCompOrdTMsgList.size();
                        rollback();
                    }
                } else {
                    this.errorCount += lfsCompOrdTMsgList.size();
                    rollback();
                }
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    // 2018/09/25 QC#28231 Mod Start
    //private void doAccrualMode() {
    private void doAccrualMode(String statementId) {
    // 2018/09/25 QC#28231 Mod End

        List<LFS_COMP_ORDTMsg> lfsCompOrdTMsgList = new ArrayList<LFS_COMP_ORDTMsg>();
        List<COMP_ACRL_CTRLTMsg> compAcrlCtrlTMsgList = new ArrayList<COMP_ACRL_CTRLTMsg>();
        List<COMP_ACRL_PRMTMsg> compAcrlPrmTMsgList = new ArrayList<COMP_ACRL_PRMTMsg>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> preCpoMap = new HashMap<String, Object>();
        Map<String, Object> errCpoMap = new HashMap<String, Object>();

        try {
            // 2018/09/25 QC#28231 Mod Start
            //stmt = this.ssmLLClient.createPreparedStatement("getCompInfoAccrual", getParamMapAccrual(), getExecParam());
            stmt = this.ssmLLClient.createPreparedStatement(statementId, getParamMapAccrual(), getExecParam());
            // 2018/09/25 QC#28231 Mod End
            rs = stmt.executeQuery();

            while (rs.next()) {
                LFS_COMP_ORDTMsg lfsCompOrdTMsg = setLfsCompOrd(rs);
                lfsCompOrdTMsgList.add(lfsCompOrdTMsg);

                // QC#55658 2020/01/31 Del Start
                // if (changeCpo(rs, preCpoMap, LEVEL_LINE) && !isErrCpo(preCpoMap, errCpoMap, LEVEL_LINE)) {
                //    COMP_ACRL_CTRLTMsg compAcrlCtrlTMsg = setCompAcrlCtrl(preCpoMap);
                //    compAcrlCtrlTMsgList.add(compAcrlCtrlTMsg);
                // }
                // QC#55658 2020/01/31 Del End
                if (changeCpo(rs, preCpoMap, null) && !isErrCpo(preCpoMap, errCpoMap, null)) {
                    COMP_ACRL_PRMTMsg compAcrlPrmTMsg = setCompAcrlPrm(preCpoMap);
                    compAcrlPrmTMsgList.add(compAcrlPrmTMsg);
                }

                if (lfsCompOrdTMsgList.size() >= this.commitNumber) {
                    int insertCount = insertLfsCompOrd(lfsCompOrdTMsgList);
                    if (insertCount == lfsCompOrdTMsgList.size()) {
                        if (insertCompAcrlCtrl(compAcrlCtrlTMsgList) && updateCompAcrlPrm(compAcrlPrmTMsgList)) {
                            this.normalCount += insertCount;
                            commit();
                        } else {
                            this.errorCount += lfsCompOrdTMsgList.size();
                            rollback();
                            errCpoMap = setCpoMap(rs);
                        }
                    } else {
                        this.errorCount += lfsCompOrdTMsgList.size();
                        rollback();
                        errCpoMap = setCpoMap(rs);
                    }
                    lfsCompOrdTMsgList.clear();
                    compAcrlCtrlTMsgList.clear();
                    compAcrlPrmTMsgList.clear();
                }
                preCpoMap = setCpoMap(rs);
            }

            if (!preCpoMap.isEmpty()) {
                // QC#55658 2020/01/31 Del Start
                // if (!isErrCpo(preCpoMap, errCpoMap, LEVEL_LINE)) {
                //     COMP_ACRL_CTRLTMsg compAcrlCtrlTMsg = setCompAcrlCtrl(preCpoMap);
                //     compAcrlCtrlTMsgList.add(compAcrlCtrlTMsg);
                // }
                // QC#55658 2020/01/31 Del End
                if (!isErrCpo(preCpoMap, errCpoMap, null)) {
                    COMP_ACRL_PRMTMsg compAcrlPrmTMsg = setCompAcrlPrm(preCpoMap);
                    compAcrlPrmTMsgList.add(compAcrlPrmTMsg);
                }
            }

            // 2018/09/21 QC#28232 Mod Start
            //if (lfsCompOrdTMsgList.size() > 0) {
            if (lfsCompOrdTMsgList.size() > 0 || compAcrlCtrlTMsgList.size() > 0) {
            // 2018/09/21 QC#28232 Mod End
                int insertCount = insertLfsCompOrd(lfsCompOrdTMsgList);
                if (insertCount == lfsCompOrdTMsgList.size()) {
                    if (insertCompAcrlCtrl(compAcrlCtrlTMsgList) && updateCompAcrlPrm(compAcrlPrmTMsgList)) {
                        this.normalCount += insertCount;
                        commit();
                    } else {
                        this.errorCount += lfsCompOrdTMsgList.size();
                        rollback();
                    }
                } else {
                    this.errorCount += lfsCompOrdTMsgList.size();
                    rollback();
                }
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private Map<String, Object> getParamMapNormal() throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.slsDt);
        paramMap.put("distChCommercial", STR_01);
        paramMap.put("distChDealer", STR_02);
        paramMap.put("distChIntraCompany", STR_03);
        paramMap.put("coaAfflNonAffl", COA_AFFL.NON_AFFILIATED_COMPANY);
        paramMap.put("ordCatgCtxTpDealer", ORD_CATG_CTX_TP.DEALER_SALES_ORDER_VALUE_SET);
//        paramMap.put("orgCd", this.writerOrgCd); // Del 2018/01/29 S21_NA#22002
        paramMap.put("coaMdseTpMachine", COA_PROJ.MACHINE);
        List<String> sendCompCpltCdList = S21StringUtil.toList(this.sendCompCpltCd);
        paramMap.put("compProcStsCdList", sendCompCpltCdList);
        paramMap.put("lineBizTpCd", this.compLfsBizTpCd);
        paramMap.put("defSerNum", DEF_SER_NUM);
        String[] dsContrDtlStsCdList = new String[] {DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.SAVED, DS_CONTR_DTL_STS.SUBMITED, DS_CONTR_DTL_STS.SIGNED, DS_CONTR_DTL_STS.ORDER };
        paramMap.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        paramMap.put("svcCovFeatSplyIncl", SVC_COV_FEAT.SPLY_INCL);
        // Add Start 2018/01/29 S21_NA#22002
        paramMap.put("trtyGrpTpCdTD", TRTY_GRP_TP.TD);
        paramMap.put("convDivCdTDS", COMP_DIV_CD_TDS);
        // Add eND 2018/01/29 S21_NA#22002
        // Add Start 2018/05/25 QC#21841(Sol#495)
        paramMap.put("invLineCatgCd", INV_LINE_CATG.ITEM);
        // Add End 2018/05/25 QC#21841(Sol#495)
        // Add Start 2018/08/20 S21_NA#25938
        paramMap.put("lineBizTpCdForIs", PERCENT + this.compIsBizTpCd + PERCENT);
        // Add End 2018/08/20 S21_NA#25938
        // 2018/09/11 QC#28118,28123 Add Start
        paramMap.put("crCdCredit", CR_REBIL.CREDIT);
        paramMap.put("crCdRebill", CR_REBIL.REBILL);
        // 2018/09/11 QC#28118,28123 Add End
        // 2018/09/25 QC#28231 Add Start
        paramMap.put("invLineCatgCharge", INV_LINE_CATG.CHARGE);
        paramMap.put("invLineCatgFreight", INV_LINE_CATG.FREIGHT);
        // 2018/09/25 QC#28231 Add End
        // 2018/10/30 QC#29033 Add Start
        paramMap.put("invTrxSrcTpWS", TRX_SRC_TP.WHOLE_SALES);
        paramMap.put("invTrxSrcTpWSReturn", TRX_SRC_TP.WHOLE_SALES_RETURN);
        // 2018/10/30 QC#29033 Add End

        // 2018/11/09 QC#29131 Add End
        paramMap.put("creditMemo", INV_TP.CREDIT_MEMO);
        // 2018/11/09 QC#29131 Add End

        // 2019/08/15 QC#52707 Add Start
        paramMap.put("rwsStsCancel", RWS_STS.CANCELED);
        // 2019/08/15 QC#52707 Add End
        // Add Start 2019/09/13 QC#52943
        paramMap.put("flgOnY", ZYPConstant.FLG_ON_Y);
        // Add End 2019/09/13 QC#52943

        return paramMap;
    }

    private Map<String, Object> getParamMapAccrual() throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.slsDt);
        paramMap.put("distChCommercial", STR_01);
        paramMap.put("distChDealer", STR_02);
        paramMap.put("distChIntraCompany", STR_03);
        paramMap.put("coaAfflNonAffl", COA_AFFL.NON_AFFILIATED_COMPANY);
        paramMap.put("ordCatgCtxTpDealer", ORD_CATG_CTX_TP.DEALER_SALES_ORDER_VALUE_SET);
//        paramMap.put("orgCd", this.writerOrgCd); // Del 2018/01/29 S21_NA#22002
        paramMap.put("coaMdseTpMachine", COA_PROJ.MACHINE);
        List<String> sendCompCpltCdList = S21StringUtil.toList(this.sendCompCpltCd);
        paramMap.put("compProcStsCdList", sendCompCpltCdList);
        paramMap.put("lineBizTpCd", this.compLfsBizTpCd);
        paramMap.put("defSerNum", DEF_SER_NUM);
        String[] dsContrDtlStsCdList = new String[] {DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.SAVED, DS_CONTR_DTL_STS.SUBMITED, DS_CONTR_DTL_STS.SIGNED, DS_CONTR_DTL_STS.ORDER };
        paramMap.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        paramMap.put("svcCovFeatSplyIncl", SVC_COV_FEAT.SPLY_INCL);
        // Add Start 2018/01/29 S21_NA#22002
        paramMap.put("trtyGrpTpCdTD", TRTY_GRP_TP.TD);
        paramMap.put("convDivCdTDS", COMP_DIV_CD_TDS);
        // Add eND 2018/01/29 S21_NA#22002)
        // Add Start 2018/08/20 S21_NA#25938
        paramMap.put("lineBizTpCdForIs", PERCENT + this.compIsBizTpCd + PERCENT);
        // Add End 2018/08/20 S21_NA#25938

        // 2019/08/15 QC#52707 Add Start
        paramMap.put("rwsStsCancel", RWS_STS.CANCELED);
        // 2019/08/15 QC#52707 Add End
        // QC#55658 2020/01/31 Add Start
        paramMap.put("ordHdrStsCancel", ORD_HDR_STS.CANCELLED);

        List<String> ordLineStsCdList = new ArrayList<String>();
        ordLineStsCdList.add(ORD_LINE_STS.SAVED);
        ordLineStsCdList.add(RTRN_LINE_STS.CANCELLED);
        paramMap.put("ordLineStsCdList", ordLineStsCdList);

        List<String> rtrnLineStsCdList = new ArrayList<String>();
        rtrnLineStsCdList.add(RTRN_LINE_STS.ENTERED);
        rtrnLineStsCdList.add(RTRN_LINE_STS.CANCELLED);
        paramMap.put("rtrnLineStsCdList", rtrnLineStsCdList);
        // QC#55658 2020/01/31 Add End

        return paramMap;
    }

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> setInvMap(ResultSet rs) throws SQLException {
        Map<String, Object> invMap = new HashMap<String, Object>();
        invMap.put("INV_NUM", rs.getString("INV_NUM"));
        invMap.put("INV_BOL_LINE_NUM", rs.getString("INV_BOL_LINE_NUM"));
        invMap.put("INV_LINE_NUM", rs.getString("INV_LINE_NUM"));
        invMap.put("INV_LINE_SUB_NUM", rs.getString("INV_LINE_SUB_NUM"));
        invMap.put("INV_LINE_SUB_TRX_NUM", rs.getString("INV_LINE_SUB_TRX_NUM"));
        return invMap;
    }

    private Map<String, Object> setCpoMap(ResultSet rs) throws SQLException {
        Map<String, Object> cpoMap = new HashMap<String, Object>();
        cpoMap.put("CPO_ORD_NUM", rs.getString("CPO_ORD_NUM"));
        cpoMap.put("CPO_DTL_LINE_NUM", rs.getString("CPO_DTL_LINE_NUM"));
        cpoMap.put("CPO_DTL_LINE_SUB_NUM", rs.getString("CPO_DTL_LINE_SUB_NUM"));
        return cpoMap;
    }

    private boolean changeInv(ResultSet rs, Map<String, Object> invMap) throws SQLException {
        if (invMap.isEmpty()) {
            return false;
        }
        if (!rs.getString("INV_NUM").equals((String) invMap.get("INV_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_BOL_LINE_NUM").equals((String) invMap.get("INV_BOL_LINE_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_LINE_NUM").equals((String) invMap.get("INV_LINE_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_LINE_SUB_NUM").equals((String) invMap.get("INV_LINE_SUB_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_LINE_SUB_TRX_NUM").equals((String) invMap.get("INV_LINE_SUB_TRX_NUM"))) {
            return true;
        }
        return false;
    }

    private boolean changeCpo(ResultSet rs, Map<String, Object> cpoMap, String level) throws SQLException {
        if (cpoMap.isEmpty()) {
            return false;
        }
        if (!rs.getString("CPO_ORD_NUM").equals((String) cpoMap.get("CPO_ORD_NUM"))) {
            return true;
        }
        if (LEVEL_LINE.equals(level) && !rs.getString("CPO_DTL_LINE_NUM").equals((String) cpoMap.get("CPO_DTL_LINE_NUM"))) {
            return true;
        }
        if (LEVEL_LINE.equals(level) && !rs.getString("CPO_DTL_LINE_SUB_NUM").equals((String) cpoMap.get("CPO_DTL_LINE_SUB_NUM"))) {
            return true;
        }
        return false;
    }

    private boolean isErrInv(Map<String, Object> invMap, Map<String, Object> errInvMap) throws SQLException {
        if (errInvMap.isEmpty()) {
            return false;
        }
        if (!((String) invMap.get("INV_NUM")).equals((String) errInvMap.get("INV_NUM"))) {
            return false;
        }
        if (!((String) invMap.get("INV_BOL_LINE_NUM")).equals((String) errInvMap.get("INV_BOL_LINE_NUM"))) {
            return false;
        }
        if (!((String) invMap.get("INV_LINE_NUM")).equals((String) errInvMap.get("INV_LINE_NUM"))) {
            return false;
        }
        if (!((String) invMap.get("INV_LINE_SUB_NUM")).equals((String) errInvMap.get("INV_LINE_SUB_NUM"))) {
            return false;
        }
        if (!((String) invMap.get("INV_LINE_SUB_TRX_NUM")).equals((String) errInvMap.get("INV_LINE_SUB_TRX_NUM"))) {
            return false;
        }
        return true;
    }

    private boolean isErrCpo(Map<String, Object> cpoMap, Map<String, Object> errCpoMap, String level) throws SQLException {
        if (errCpoMap.isEmpty()) {
            return false;
        }
        if (!((String) cpoMap.get("CPO_ORD_NUM")).equals((String) errCpoMap.get("CPO_ORD_NUM"))) {
            return false;
        }
        if (LEVEL_LINE.equals(level) && !((String) cpoMap.get("CPO_DTL_LINE_NUM")).equals((String) errCpoMap.get("CPO_DTL_LINE_NUM"))) {
            return false;
        }
        if (LEVEL_LINE.equals(level) && !((String) cpoMap.get("CPO_DTL_LINE_SUB_NUM")).equals((String) errCpoMap.get("CPO_DTL_LINE_SUB_NUM"))) {
            return false;
        }
        return true;
    }

    private LFS_COMP_ORDTMsg setLfsCompOrd(ResultSet rs) throws SQLException {
        LFS_COMP_ORDTMsg tMsg = new LFS_COMP_ORDTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.lfsCompOrdPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.LFS_COMP_ORD_SQ));
        setValue(tMsg.distChCd, rs.getString("DIST_CH_CD"));
        setValue(tMsg.compDivCd, rs.getString("COMP_DIV_CD"));
        setValue(tMsg.cpoOrdNum, rs.getString("CPO_ORD_NUM"));
        setValue(tMsg.dsOrdPosnNum, rs.getString("DS_ORD_POSN_NUM"));
        // 2018/09/21 QC#28232 Mod Start
        //setValue(tMsg.cpoDtlLineNum, rs.getString("CPO_DTL_LINE_NUM"));
        //setValue(tMsg.cpoDtlLineSubNum, rs.getString("CPO_DTL_LINE_SUB_NUM"));
        setValue(tMsg.cpoDtlLineNum, rs.getString("DS_CPO_LINE_NUM"));
        // Mod Start 2018/10/24 QC#28844
        //setValue(tMsg.cpoDtlLineSubNum, rs.getString("DS_CPO_LINE_SUB_NUM"));
        setValue(tMsg.cpoDtlLineSubNum, rs.getString("CPO_DTL_LINE_SUB_NUM"));
        // Mod End 2018/10/24 QC#28844
        // 2018/09/21 QC#28232 Mod End
        setValue(tMsg.dsOrdCatgCd, rs.getString("DS_ORD_CATG_CD"));
        setValue(tMsg.dsOrdTpCd, rs.getString("DS_ORD_TP_CD"));
        setValue(tMsg.ordCratDt, rs.getString("ORD_CRAT_DT"));
        setValue(tMsg.t_MdlNm, rs.getString("T_MDL_NM"));
        setValue(tMsg.mdseCd, rs.getString("MDSE_CD"));
        setValue(tMsg.sellToCustCd, rs.getString("SELL_TO_CUST_CD"));
        setValue(tMsg.sellToCustAcctNm, rs.getString("SELL_TO_CUST_ACCT_NM"));
        setValue(tMsg.slsRepPsnCd, rs.getString("SLS_REP_PSN_CD"));
        setValue(tMsg.istlRepPsnCd, rs.getString("ISTL_REP_PSN_CD"));
        setValue(tMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        setValue(tMsg.serNum, rs.getString("SER_NUM"));
        setValue(tMsg.compOrdQty, rs.getBigDecimal("COMP_ORD_QTY"));
        setValue(tMsg.compValAmt, rs.getBigDecimal("COMP_VAL_AMT"));
        setValue(tMsg.invNum, rs.getString("INV_NUM"));
        setValue(tMsg.invLineNum, rs.getString("INV_LINE_NUM"));
        setValue(tMsg.invLineSubNum, rs.getString("INV_LINE_SUB_NUM"));
        setValue(tMsg.invDt, rs.getString("INV_DT"));
        setValue(tMsg.contrSldFlg, rs.getString("CONTR_SLD_FLG"));
        setValue(tMsg.compPrtrFlg, rs.getString("COMP_PRTR_FLG"));
        setValue(tMsg.acrlOrdFlg, rs.getString("ACRL_ORD_FLG"));
        setValue(tMsg.crRebilRsnCd, rs.getString("CR_REBIL_RSN_CD"));
        setValue(tMsg.dsOrdRsnCd, rs.getString("DS_ORD_RSN_CD"));
        // Add Start 2018/10/17 QC#28842
        setValue(tMsg.invBolLineNum, rs.getString("INV_BOL_LINE_NUM"));
        // Add End 2018/10/17 QC#28842

        // QC#58045
        Map<String, Object> retMap = getBandandCoveredImages(tMsg.cpoOrdNum.getValue(), tMsg.dsOrdPosnNum.getValue());
        if (retMap != null && !retMap.isEmpty() && this.lfsCommMdses.contains(tMsg.mdseCd.getValue())) {
            setValue(tMsg.prcListBandDescTxt, (String) retMap.get("PRC_LIST_BAND_DESC_TXT"));
            setValue(tMsg.xsMtrCopyQty, (BigDecimal) retMap.get("XS_MTR_COPY_QTY"));
        }

        return tMsg;
    }

    private INV_LINETMsg setInvLine(Map<String, Object> invMap) {
        INV_LINETMsg tMsg = new INV_LINETMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.invNum, (String) invMap.get("INV_NUM"));
        setValue(tMsg.invBolLineNum, (String) invMap.get("INV_BOL_LINE_NUM"));
        setValue(tMsg.invLineNum, (String) invMap.get("INV_LINE_NUM"));
        setValue(tMsg.invLineSubNum, (String) invMap.get("INV_LINE_SUB_NUM"));
        setValue(tMsg.invLineSubTrxNum, (String) invMap.get("INV_LINE_SUB_TRX_NUM"));
        return tMsg;
    }

    private COMP_ACRL_CTRLTMsg setCompAcrlCtrl(Map<String, Object> cpoMap) {
        COMP_ACRL_CTRLTMsg tMsg = new COMP_ACRL_CTRLTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.cpoOrdNum, (String) cpoMap.get("CPO_ORD_NUM"));
        setValue(tMsg.cpoDtlLineNum, (String) cpoMap.get("CPO_DTL_LINE_NUM"));
        setValue(tMsg.cpoDtlLineSubNum, (String) cpoMap.get("CPO_DTL_LINE_SUB_NUM"));
        return tMsg;
    }

    private COMP_ACRL_PRMTMsg setCompAcrlPrm(Map<String, Object> cpoMap) {
        COMP_ACRL_PRMTMsg tMsg = new COMP_ACRL_PRMTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.cpoOrdNum, (String) cpoMap.get("CPO_ORD_NUM"));
        return tMsg;
    }

    private int insertLfsCompOrd(List<LFS_COMP_ORDTMsg> tMsgList) {
        // 2018/09/21 QC#28232 Add Start
        if (tMsgList.size() == 0) {
            return 0;
        }
        // 2018/09/21 QC#28232 Add End

        int insertCount = S21FastTBLAccessor.insert(tMsgList.toArray(new LFS_COMP_ORDTMsg[tMsgList.size()]));
        return insertCount;
    }

    private boolean insertCompAcrlCtrl(List<COMP_ACRL_CTRLTMsg> tMsgList) {
        if (tMsgList.size() == 0) {
            return true;
        }

        int insertCount = S21FastTBLAccessor.insert(tMsgList.toArray(new COMP_ACRL_CTRLTMsg[tMsgList.size()]));
        if (insertCount != tMsgList.size()) {
            return false;
        }
        return true;
    }

    private boolean updateInvLine(List<INV_LINETMsg> tMsgList) {
        if (tMsgList.size() == 0) {
            return true;
        }

        List<INV_LINETMsg> updTMsgList = new ArrayList<INV_LINETMsg>(tMsgList.size());
        for (INV_LINETMsg tMsg : tMsgList) {
            tMsg = (INV_LINETMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg == null) {
                continue;
            }
            setValue(tMsg.compProcStsCd, COMP_PROC_STS_COMPLETE);
            updTMsgList.add(tMsg);
        }

        int updateCount = S21FastTBLAccessor.update(updTMsgList.toArray(new INV_LINETMsg[updTMsgList.size()]));
        if (updateCount != updTMsgList.size()) {
            return false;
        }
        return true;
    }

    private boolean updateCompAcrlPrm(List<COMP_ACRL_PRMTMsg> tMsgList) {
        if (tMsgList.size() == 0) {
            return true;
        }

        List<COMP_ACRL_PRMTMsg> updTMsgList = new ArrayList<COMP_ACRL_PRMTMsg>(tMsgList.size());
        for (COMP_ACRL_PRMTMsg tMsg : tMsgList) {
            tMsg = (COMP_ACRL_PRMTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg == null) {
                continue;
            }
            setValue(tMsg.compProcStsCd, COMP_PROC_STS_COMPLETE);
            updTMsgList.add(tMsg);
        }

        int updateCount = S21FastTBLAccessor.update(updTMsgList.toArray(new COMP_ACRL_PRMTMsg[updTMsgList.size()]));
        if (updateCount != updTMsgList.size()) {
            return false;
        }
        return true;
    }

    private Map<String, Object> getBandandCoveredImages(String cpoOrdNum, String dsOrdPosnNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("dsOrdPosnNum", dsOrdPosnNum);
        List<String> dsContrDtlTpCdList = new ArrayList<String>();
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.BASE_ONLY);
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.USAGE_ONLY);
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.BASE_AND_USAGE);
        queryParam.put("dsContrDtlTpCdList", dsContrDtlTpCdList);
        queryParam.put("dsContrCratTpCd", DS_CONTR_CRAT_TP.SHELL);
        queryParam.put("dsOrdPosnNum", dsOrdPosnNum);
        queryParam.put("ordLineStsCd", ORD_LINE_STS.CANCELLED);
        queryParam.put("lfsCommMdses", lfsCommMdses);

        Map<String, Object> retMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getBandandCoveredImages", queryParam);

        return retMap;
    }
}
