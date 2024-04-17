/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB036001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.batch.NSA.NSAB036001.constant.NSAB036001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonConst;
import parts.dbcommon.EZDTBLAccessor;

import business.db.BLLG_CYCLETMsg;
import business.db.CFS_CONTR_PRC_UPLFTTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_PRC_CHNG_RECTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.MDL_NMTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;
import business.parts.NSZC047005PMsg;
import business.parts.NSZC047011PMsg;
import business.parts.NSZC048001PMsg;
import business.parts.NSZC100001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC100001.NSZC100001;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.common.NSX.NSXC001001.EndOfLifeBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetEndOfLifeInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcTermAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcTermAmt;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
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
 * Service Contact Annual Escalation Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Hitachi         K.Kasai         Create          N/A
 * 2016/02/01   Hitachi         K.Kasai         Create          QC#3956,2969
 * 2016/02/02   Hitachi         K.Kasai         Create          QC#3996
 * 2016/02/03   Hitachi         K.Kasai         Create          QC#4115
 * 2016/04/18   Hitachi         T.Kanasaka      Update          QC#7163
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#7381
 * 2016/05/27   Hitachi         K.Kasai         Update          QC#447
 * 2016/06/06   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 2016/07/13   Hitachi         K.Kasai         Update          QC#11818
 * 2017/02/10   Hitachi         K.Kitachi       Update          QC#17410
 * 2017/06/19   Hitachi         Y.Osawa         Update          QC#19256
 * 2017/08/24   Hitachi         E.Kameishi      Update          QC#8661
 * 2017/08/28   Hitachi         K.Kitachi       Update          QC#20529
 * 2017/10/03   Hitachi         E.Kameishi      Update          QC#21504
 * 2017/10/06   Hitachi         T.Tomita        Update          QC#21437
 * 2017/11/01   Hitachi         K.Kojima        Update          QC#21859
 * 2017/11/10   Hitachi         K.Kojima        Update          QC#21439
 * 2017/11/27   Hitachi         K.Kojima        Update          QC#22660
 * 2017/12/06   Hitachi         K.Yamada        Update          QC#22891
 * 2017/12/08   Hitachi         K.Kojima        Update          QC#22985
 * 2018/05/14   Hitachi         K.Kojima        Update          QC#25640
 * 2018/11/19   Hitachi         T.Tomita        Update          QC#28638
 * 2018/12/03   Hitachi         T.Tomita        Update          QC#28638
 * 2019/07/25   Hitachi         A.Kohinata      Update          QC#52070
 * 2019/08/15   Hitachi         A.Kohinata      Update          QC#52413
 * 2021/02/02   CITS            R.Mercado       Update          QC#58313
 * 2021/08/26   CITS            L.Mandanas      Update          QC#59077
 * 2022/12/05   CITS            E.Sanchez       Update          QC#60807
 * 2023/01/06   CITS            E.Sanchez       Update          QC#60807
 * 2023/04/21   CITS            R.Jin           Update          QC#59586
 * 2023/06/20   CITS            R.Jin           Update          QC#59586-2
 *</pre>
 */
public class NSAB036001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    // -- Other Internal Variable ---------------
    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** default Model Update Rule For Base */
    private BigDecimal defModelUpdateRuleBase = null;

    /** default Model Update Rule For Usage */
    private BigDecimal defModelUpdateRuleUsage = null;

    // add start 2016/05/27 CSA Defect#447
    /** VAR_CHAR_CONST:SVC_CONTR_ANN_ESCL_CHK_EOL_FLG */
    private String svcContrAnnEsclChkEolFlg;

    /** System TimeStamp */
    private String sysTime = null;
    // add end 2016/05/27 CSA Defect#447

    /** total search count */
    private int searchCnt = 0;

    /** Dc Contract success count */
    private int infoSccessCnt = 0;

    // START 2017/02/08 K.Kitachi [QC#17410, ADD]
    /** DS Contract Modification Text */
    private String dsContrModTxt = null;

    /** CFS Dealer Code */
    private String cfsDlrCd = null;

    /** Special Fleet Merchandise Code */
    private String spclFltMdseCd = null;

    /** Model Name TMsg */
    private MDL_NMTMsg mdlNmTMsg;
    // END 2017/02/08 K.Kitachi [QC#17410, ADD]
    
    // START 2023/04/21 R.Jin [QC#59586, ADD]
    /** target rowCnt */
    private int rowCnt = 0;

    /** proces Curren rowCnt */
    private int curRowCnt = 0;
    // END   2023/04/21 R.Jin [QC#59586, ADD]

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NSAB036001().executeBatch(NSAB036001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        ssmBatchClient = S21SsmBatchClient.getClient(getClass());
        termCd = TERM_CD.NORMAL_END;

        // Get the Global Company Code.
        // If an error occurs, throw Exception.
        glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code"});
        }

        // default Model Update Rule
        this.defModelUpdateRuleBase = ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_BASE, glblCmpyCd);
        this.defModelUpdateRuleUsage = ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_USAGE, glblCmpyCd);

        // Get the Sales Date.
        // If an error occurs, throw Exception.
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BATCH_ID);
        if (!hasValue(slsDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Sales Date"});
        }

        // add start 2016/05/27 CSA Defect#447
        //Get VarCharConst
        this.svcContrAnnEsclChkEolFlg = ZYPCodeDataUtil.getVarCharConstValue(SVC_CONTR_ANN_ESCL_CHK_EOL_FLG, this.glblCmpyCd);
        if (!hasValue(this.svcContrAnnEsclChkEolFlg)) {
            this.svcContrAnnEsclChkEolFlg = ZYPConstant.FLG_OFF_N;
        }

        // get sysTime
        this.sysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);
        // add end 2016/05/27 CSA Defect#447

        // START 2017/02/10 K.Kitachi [QC#17410, ADD]
        this.dsContrModTxt = ZYPDateUtil.getCurrentSystemTime(FORMAT_DS_CONTR_MOD_TXT).toUpperCase();
        this.cfsDlrCd = ZYPCodeDataUtil.getVarCharConstValue(CFS_DLR_CD, this.glblCmpyCd);
        this.spclFltMdseCd = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, this.glblCmpyCd);
        this.mdlNmTMsg = getMdlNmTMsg();
        // END 2017/02/10 K.Kitachi [QC#17410, ADD]

        excParam = new S21SsmExecutionParameter();
        excParam.setFetchSize(FETCH_SIZE_MAX);
        excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    protected void mainRoutine() {

        doProcess();
    }

    @Override
    protected void termRoutine() {

        // Set term code and total count.
        setTermState(this.termCd, infoSccessCnt, searchCnt - infoSccessCnt, searchCnt);
    }

    /**
     * doProcess
     */
    private void doProcess() {
        PreparedStatement annEsclList = null;
        ResultSet rsAnnEscl = null;
        BigDecimal preDsContrPk = BigDecimal.ZERO;
        BigDecimal curDsContrPk = BigDecimal.ZERO;
        BigDecimal curDsContrDtlPk = BigDecimal.ZERO;
        String contrUplftTpCd = null;
        String effFromDt = null;
        String effThruDt = null;
        String rnwPrcMethCd = null;
        String dsContrCatgCd = null;
        BigDecimal curBasePrcDealAmt = null;
        BigDecimal curBasePrcUpRatio = null;
        BigDecimal newBasePrcDealAmt = null;
        BigDecimal newXsMtrAmtRate = null;
        BigDecimal newCalPrcAmtRate = null;
        BigDecimal curMtrPrcUpRatio = null;
        BigDecimal curXsMtrCopyQty = null;
        BigDecimal aftDeclPntDigitNum = new BigDecimal(DEF_AFT_DECL_PNT_DIGIT_NUM);
        BigDecimal dsContrBllgMtrPk = BigDecimal.ZERO;
        String contrCloDay = null;
        String dsContrDtlTpCd = null;
        Map<String, Object> dsContrOrcEff = null;
        NSZC047005PMsg pMsg = new NSZC047005PMsg();
        boolean errFlg = false;
        boolean procFlg = false;
        // add start 2019/07/25 QC#52070
        boolean trgtContr = false;
        boolean trgtContrDtl = false;
        // add end 2019/07/25 QC#52070
        // add start 2019/08/16 QC#52413
        boolean chngPrcFlg = false;
        // add end 2019/08/16 QC#52413
        List<String> errList = new ArrayList<String>();
        // add start 2016/05/23 CSA Defect#7381
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        // add end 2016/05/23 CSA Defect#7381
        // add start 2016/05/27 CSA Defect#447
        List<SVC_BAT_ERR_LOGTMsg> errLogTMsgList = new ArrayList<SVC_BAT_ERR_LOGTMsg>();
        // add end 2016/05/27 CSA Defect#447
        // START 2022/12/05 E.Sanchez [QC#60807, ADD]
        List<String> pcyDtErrList = new ArrayList<String>();
        // END 2022/12/05 E.Sanchez [QC#60807, ADD]
        // START 2023/01/06 E.Sanchez [QC#60807, ADD]
        boolean contrPcyDtErrFlg = false;
        // END 2023/01/06 E.Sanchez [QC#60807, ADD]

        //1.get Annual Escalation Target
        try {
            annEsclList = ssmLLClient.createPreparedStatement("getAnnualEscalationList", setParamForAnnEscl(), excParam);
            rsAnnEscl = annEsclList.executeQuery();

            while (rsAnnEscl.next()) {
                if (!procFlg) {
                    procFlg = true;
                }
                // START 2023/04/21 R.Jin [QC#59586, ADD]
                this.curRowCnt++;
                this.rowCnt = rsAnnEscl.getInt("ROWCNT");
                // END 2023/04/21 R.Jin [QC#59586, ADD]
                // add start 2019/07/25 QC#52070
                trgtContrDtl = false;
                // add end 2019/07/25 QC#52070
                // add start 2019/08/16 QC#52413
                chngPrcFlg = false;
                // add end 2019/08/16 QC#52413
                curDsContrPk = rsAnnEscl.getBigDecimal(DS_CONTR_PK);
                curDsContrDtlPk = rsAnnEscl.getBigDecimal(DS_CONTR_DTL_PK);
                dsContrCatgCd = rsAnnEscl.getString(DS_CONTR_CATG_CD);
                dsContrDtlTpCd = rsAnnEscl.getString(DS_CONTR_DTL_TP_CD);
                contrCloDay = rsAnnEscl.getString(CONTR_CLO_DAY);
                if (!rsAnnEscl.isFirst() && curDsContrPk.compareTo(preDsContrPk) != 0) {
                    // START 2023/01/06 E.Sanchez [QC#60807, ADD]
                    if (contrPcyDtErrFlg) {
                        errFlg = true;
                    }
                    // END 2023/01/06 E.Sanchez [QC#60807, ADD]
                    // START 2018/05/14 K.Kojima [QC#25640,ADD]
                    // mod start 2019/07/25 QC#52070
                    if (!errFlg && trgtContr) {
                    // mod end 2019/07/25 QC#52070
                        if (!callSumAggregateAPI(errList, preDsContrPk)) {
                            errFlg = true;
                        }
                    }
                    // END 2018/05/14 K.Kojima [QC#25640,ADD]
                    searchCnt++;
                    if (!errFlg) {
                        infoSccessCnt++;
                        commit();
                    } else {
                        rollback();
                        // del start 2016/05/27 CSA Defect#447
//                        sendErrMail(errList);
                        // del start 2016/05/27 CSA Defect#447
                    }
                    errFlg = false;
                    // add start 2019/07/25 QC#52070
                    trgtContr = false;
                    // add end 2019/07/25 QC#52070
                    // START 2023/01/06 E.Sanchez [QC#60807, ADD]
                    contrPcyDtErrFlg = false;
                    // END 2023/01/06 E.Sanchez [QC#60807, ADD]
                    // START 2023/06/20 R.Jin [QC#59586-2, ADD]
                    this.curRowCnt = 1;
                    // END 2023/06/20 R.Jin [QC#59586-2, ADD]
                } else {
                    if (errFlg) {
                        preDsContrPk = curDsContrPk;
                        continue;
                    }
                }
                if (hasValue(rsAnnEscl.getString(EFF_FROM_DT))) {
                    effFromDt = rsAnnEscl.getString(EFF_FROM_DT);
                    // START 2017/11/27 K.Kojima [QC#22660,ADD]
                } else {
                    continue;
                    // END 2017/11/27 K.Kojima [QC#22660,ADD]
                }
                if (hasValue(rsAnnEscl.getString(CONTR_EFF_THRU_DT))) {
                    effThruDt = rsAnnEscl.getString(CONTR_EFF_THRU_DT);
                    // START 2017/11/27 K.Kojima [QC#22660,ADD]
                } else {
                    continue;
                    // END 2017/11/27 K.Kojima [QC#22660,ADD]
                }
                if (hasValue(rsAnnEscl.getString(CONTR_UPLFT_TP_CD))) {
                    contrUplftTpCd = rsAnnEscl.getString(CONTR_UPLFT_TP_CD);
                    // START 2017/11/27 K.Kojima [QC#22660,ADD]
                } else {
                    continue;
                    // END 2017/11/27 K.Kojima [QC#22660,ADD]
                }
                // add start 2016/05/27 CSA Defect#447
                if (ZYPConstant.FLG_ON_Y.equals(this.svcContrAnnEsclChkEolFlg) && (rsAnnEscl.getBigDecimal(SVC_MACH_MSTR_PK) != null)) {
                    EndOfLifeBean infoBean = new EndOfLifeBean();
                    infoBean.setGlblCmpyCd(this.glblCmpyCd);
                    infoBean.setSvcMachMstrPk(rsAnnEscl.getBigDecimal(SVC_MACH_MSTR_PK));
                    infoBean.setEolDt(effFromDt);
                    String errMsg = null;
                    if (!NSXC001001GetEndOfLifeInfo.getEndOfLifeInfo(infoBean)) {
                        errMsg = S21MessageFunc.clspGetMessage(NSAM0481E, new String[]{String.valueOf(rsAnnEscl.getBigDecimal(SVC_MACH_MSTR_PK))});
                        errList.add(errMsg);
                        setSvcBatErrLog(errLogTMsgList, curDsContrPk, curDsContrDtlPk, rsAnnEscl);
                        errFlg = true;
                        continue;
                    } else {
                        if (ZYPConstant.FLG_OFF_N.equals(infoBean.getContrAvalFlg())) {
                            errMsg = S21MessageFunc.clspGetMessage(NSAM0481E, new String[]{String.valueOf(rsAnnEscl.getBigDecimal(SVC_MACH_MSTR_PK))});
                            errList.add(S21MessageFunc.clspGetMessage(errMsg));
                            setSvcBatErrLog(errLogTMsgList, curDsContrPk, curDsContrDtlPk, rsAnnEscl);
                            errFlg = true;
                            continue;
                        }
                    }
                }
                // add end 2016/05/27 CSA Defect#447
                // Add Start 2017/10/06 QC#21437
                String baseFlg = rsAnnEscl.getString(BASE_FLG);
                String usageFlg = rsAnnEscl.getString(USAGE_FLG);
                // Add End 2017/10/06 QC#21437
                // Add Start 2018/11/19 QC#28638
                BigDecimal maxPrcUpRatio;
                // Add End 2018/11/19 QC#28638
                // START 2017/11/10 K.Kojima [QC#21439,ADD]
                // START 2017/12/08 K.Kojima [QC#22985,DEL]
                // if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                //     baseFlg = ZYPConstant.FLG_ON_Y;
                //     usageFlg = ZYPConstant.FLG_ON_Y;
                // }
                // END 2017/12/08 K.Kojima [QC#22985,DEL]
                // END 2017/11/10 K.Kojima [QC#21439,ADD]
                //2.1. get DS_CONTR_DTL(Base)
                // Mod Start 2017/10/06 QC#21437
                if (hasValue(baseFlg) && ZYPConstant.FLG_ON_Y.equals(baseFlg)
                        && (CONTR_UPLFT_TP.UPLIFT_BASE_ONLY.equals(contrUplftTpCd) || CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(contrUplftTpCd))) {
                // Mod End 2017/10/06 QC#21437

                    // add start 2019/07/25 QC#52070
                    if (!hasInvoicedSchd(curDsContrDtlPk, effFromDt, baseFlg, null)) {
                        trgtContr = true;
                        trgtContrDtl = true;
                    // add end 2019/07/25 QC#52070

                    // Add Start 2018/11/21 QC#28638
                    maxPrcUpRatio = getMaxPrcUpRato(curDsContrPk, curDsContrDtlPk, DS_CONTR_BASE_USG_NM_B);
                    // Add End 2018/11/21 QC#28638
                    List<Map<String, Object>> dsContrDtlBaseList = new ArrayList<Map<String, Object>>();
                    if (!isAggregateLine(dsContrCatgCd, dsContrDtlTpCd)) {
                        dsContrDtlBaseList = getDsContrDtlBase(curDsContrPk, curDsContrDtlPk);
                        if (dsContrDtlBaseList != null && dsContrDtlBaseList.size() > 0) {
                            DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = getDsContrBasePrcEff(curDsContrDtlPk);
                            if (dsContrPrcEffTMsg == null) {
                                errList.add(S21MessageFunc.clspGetMessage(NSAM0370E));
                                errFlg = true;
                                preDsContrPk = curDsContrPk;
                                continue;
                            }
                            rnwPrcMethCd = getRnwPrcMethCd(dsContrDtlBaseList);
                            curBasePrcDealAmt = dsContrPrcEffTMsg.basePrcDealAmt.getValue();
                            aftDeclPntDigitNum = (BigDecimal) dsContrDtlBaseList.get(0).get(AFT_DECL_PNT_DIGIT_NUM);
                            if (!hasValue(aftDeclPntDigitNum)) {
                                aftDeclPntDigitNum = new BigDecimal(DEF_AFT_DECL_PNT_DIGIT_NUM);
                            }
                            if (RNW_PRC_METH.MARKUP_PERCENT.equals(rnwPrcMethCd)) {
                                curBasePrcUpRatio = getBasePrcUpRatio(dsContrDtlBaseList);
                                // mod start 2016/02/02 CSA Defect#3996
                                // Mod Start 2018/11/19 QC#28638
                                newBasePrcDealAmt = calcNewBasePrcDealAmt(curBasePrcDealAmt, curBasePrcUpRatio, aftDeclPntDigitNum.intValue(), maxPrcUpRatio);
                                // Mod End 2018/11/19 QC#28638
                                // mod end 2016/02/03 CSA Defect#3996
                            } else if (RNW_PRC_METH.MODEL_PERCENT.equals(rnwPrcMethCd)) {
                                curBasePrcUpRatio = getModelUpdateRuleBase(curDsContrDtlPk, effFromDt, dsContrDtlTpCd);
                                if (curBasePrcUpRatio == null) {
                                    errList.add(S21MessageFunc.clspGetMessage(NSZM0396E, new String[]{"Setup Information for Escalation rule", "Table = DS_MDL_ESCL_RULE"}));
                                    errFlg = true;
                                    preDsContrPk = curDsContrPk;
                                    continue;
                                }
                                // Mod Start 2018/11/19 QC#28638
                                newBasePrcDealAmt = calcNewBasePrcDealAmt(curBasePrcDealAmt, curBasePrcUpRatio, aftDeclPntDigitNum.intValue(), maxPrcUpRatio);
                                // Mod End 2018/11/19 QC#28638
                            }
                            //Calculate Term Amount
                            // mod start 2016/04/18 CSA Defect#7163
//                            newCalPrcAmtRate = getCalPrcAmtRate(effFromDt, newBasePrcDealAmt, dsContrDtlBaseList).setScale(IDX_6, BigDecimal.ROUND_HALF_UP);
                            // START 2017/11/01 K.Kojima [QC#21859,MOD]
                            // newCalPrcAmtRate = getCalPrcAmtRate(effFromDt, newBasePrcDealAmt, dsContrDtlBaseList);
                            int bllgCycleCnt = calcBllgCycleCntFromDuration(glblCmpyCd, effFromDt, (String) dsContrDtlBaseList.get(0).get("CONTR_EFF_THRU_DT"), (String) dsContrDtlBaseList.get(0).get("BLLG_CYCLE_CD"));
                            if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(newBasePrcDealAmt)) {
                                newCalPrcAmtRate = newBasePrcDealAmt.multiply(BigDecimal.valueOf(bllgCycleCnt));
                            } else {
                                newCalPrcAmtRate = getCalPrcAmtRate(effFromDt, newBasePrcDealAmt, dsContrDtlBaseList);
                            }
                            // END 2017/11/01 K.Kojima [QC#21859,MOD]
                            // mod end 2016/04/18 CSA Defect#7163
                            //set Billing Schedule API
                            setParamForHdr(pMsg, curDsContrDtlPk, effFromDt, effThruDt);
                            setParamForBase(pMsg, newBasePrcDealAmt, newCalPrcAmtRate);
                            //call Service Billing Schedule API(Base)
                            if (!callApi(pMsg, errList)) {
                                errFlg = true;
                                preDsContrPk = curDsContrPk;
                                continue;
                            }
                            // add start 2019/08/16 QC#52413
                            if (ZYPCommonFunc.hasValue(curBasePrcDealAmt) && ZYPCommonFunc.hasValue(newBasePrcDealAmt) && curBasePrcDealAmt.compareTo(newBasePrcDealAmt) != 0) {
                                chngPrcFlg = true;
                            }
                            // add end 2019/08/16 QC#52413
                        }
                    } else {
                        // START 2017/08/28 K.Kitachi [QC#20529, MOD]
//                        DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = getDsContrBasePrcEff(curDsContrDtlPk);
                        Map<String, Object> totBasePrc = getTotBasePrcForAggLine(curDsContrPk, effFromDt);
//                        if (dsContrPrcEffTMsg == null) {
                        if (totBasePrc == null) {
                            errList.add(S21MessageFunc.clspGetMessage(NSAM0370E));
                            errFlg = true;
                            preDsContrPk = curDsContrPk;
                            continue;
                        }
                        //set Billing Schedule API
                        setParamForHdr(pMsg, curDsContrDtlPk, effFromDt, effThruDt);
//                        setParamForBase(pMsg, dsContrPrcEffTMsg.basePrcDealAmt.getValue(), dsContrPrcEffTMsg.basePrcTermDealAmtRate.getValue());
                        setParamForBase(pMsg, (BigDecimal) totBasePrc.get("BASE_PRC_DEAL_AMT"), (BigDecimal) totBasePrc.get("BASE_PRC_TERM_DEAL_AMT_RATE"));
                        // END 2017/08/28 K.Kitachi [QC#20529, MOD]
                        //call Service Billing Schedule API(Base)
                        if (!callApi(pMsg, errList)) {
                            errFlg = true;
                            preDsContrPk = curDsContrPk;
                            continue;
                        }
                    }
                    // add start 2019/07/25 QC#52070
                    // START 2022/12/05 E.Sanchez [QC#60807, ADD]
                    } else {
                        // START 2023/01/06 E.Sanchez [QC#60807, MOD]
                        //errFlg = true;
                        contrPcyDtErrFlg = true;
                        preDsContrPk = curDsContrPk;
                        //pcyDtErrList.add(createPolicyErrMsgDetails(rsAnnEscl.getString("DS_CONTR_NUM"), rsAnnEscl.getBigDecimal(SVC_MACH_MSTR_PK),
                        //                                           rsAnnEscl.getString("DS_CONTR_BASE_USG_NM"), effFromDt));
                        pcyDtErrList.add(createPolicyErrMsgDetails(rsAnnEscl.getString("DS_CONTR_NUM"), rsAnnEscl.getBigDecimal(SVC_MACH_MSTR_PK),
                                                                   DS_CONTR_BASE_USG_NM_B, effFromDt));
                        // END 2023/01/06 E.Sanchez [QC#60807, MOD]
                        continue;
                    }
                    // END 2022/12/05 E.Sanchez [QC#60807, ADD]
                    // add end 2019/07/25 QC#52070
                }
                //2.2. get DS_CONTR_DTL(Usage)
                // Mod Start 2017/10/06 QC#21437
                if (hasValue(usageFlg) && ZYPConstant.FLG_ON_Y.equals(usageFlg)
                        && (CONTR_UPLFT_TP.UPLIFT_OVERAGE_ONLY.equals(contrUplftTpCd) || CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(contrUplftTpCd))) {
                // Mod End 2017/10/06 QC#21437

                    // add start 2019/07/25 QC#52070
                    if (!hasInvoicedSchd(curDsContrDtlPk, effFromDt, null, usageFlg)) {
                        trgtContr = true;
                        trgtContrDtl = true;
                    // add end 2019/07/25 QC#52070

                    // Add Start 2018/11/21 QC#28638
                    maxPrcUpRatio = getMaxPrcUpRato(curDsContrPk, curDsContrDtlPk, DS_CONTR_BASE_USG_NM_U);
                    // Add End 2018/11/21 QC#28638
                    List<Map<String, Object>> dsContrDtlUsgList = new ArrayList<Map<String, Object>>();
                    if (isAggregateLine(dsContrCatgCd, dsContrDtlTpCd)) {
                        dsContrDtlUsgList = getDsContrDtlUsage(curDsContrPk, curDsContrDtlPk);
                        aftDeclPntDigitNum = (BigDecimal) dsContrDtlUsgList.get(0).get(AFT_DECL_PNT_DIGIT_NUM);
                        if (!hasValue(aftDeclPntDigitNum)) {
                            aftDeclPntDigitNum = new BigDecimal(DEF_AFT_DECL_PNT_DIGIT_NUM);
                        }
                        List<Map<String, Object>> dsContrPrcEffList = getUsagePrcEff(curDsContrDtlPk);
                        int j = 0;
                        for (int i = 0; i < dsContrPrcEffList.size(); i++) {
                            dsContrOrcEff = dsContrPrcEffList.get(i);
                            dsContrBllgMtrPk = (BigDecimal) dsContrOrcEff.get(DS_CONTR_BLLG_MTR_PK);
                            rnwPrcMethCd = getRnwPrcMethCd(dsContrDtlUsgList);
                            if (RNW_PRC_METH.MARKUP_PERCENT.equals(rnwPrcMethCd)) {
                                curXsMtrCopyQty = (BigDecimal) dsContrOrcEff.get(XS_MTR_COPY_QTY);
                                curMtrPrcUpRatio = getMtrPrcUpRatio(dsContrDtlUsgList);
                                // Mod Start 2018/11/19 QC#28638
                                newXsMtrAmtRate = calcNewXsMtrAmtRate((BigDecimal) dsContrOrcEff.get(XS_MTR_AMT_RATE), curMtrPrcUpRatio, IDX_6, maxPrcUpRatio);
                                // Mod End 2018/11/19 QC#28638
                            } else if (RNW_PRC_METH.MODEL_PERCENT.equals(rnwPrcMethCd)) {
                                curXsMtrCopyQty = (BigDecimal) dsContrOrcEff.get(XS_MTR_COPY_QTY);
                                curMtrPrcUpRatio = getModelUpdateRuleMtr(curDsContrDtlPk, effFromDt, dsContrDtlTpCd, dsContrBllgMtrPk, dsContrCatgCd);
                                if (curMtrPrcUpRatio == null) {
                                    errList.add(S21MessageFunc.clspGetMessage(NSZM0396E, new String[]{"Setup Information for Escalation rule", "Table = DS_MDL_ESCL_RULE, DS_MDL_ESCL_BLLG_MTR"}));
                                    errFlg = true;
                                    preDsContrPk = curDsContrPk;
                                    break;
                                }
                                // Mod Start 2018/11/19 QC#28638
                                newXsMtrAmtRate = calcNewXsMtrAmtRate((BigDecimal) dsContrOrcEff.get(XS_MTR_AMT_RATE), curMtrPrcUpRatio, IDX_6, maxPrcUpRatio);
                                // Mod End 2018/11/19 QC#28638
                            }
                            setParamForMtr(pMsg, j, newXsMtrAmtRate, curXsMtrCopyQty, dsContrOrcEff);
                            j++;
                            if (i == dsContrPrcEffList.size() - 1 || dsContrBllgMtrPk.compareTo((BigDecimal) dsContrPrcEffList.get(i + 1).get(DS_CONTR_BLLG_MTR_PK)) != 0) {
                                setParamForHdr(pMsg, curDsContrDtlPk, effFromDt, effThruDt);
                                //call Service Billing Schedule API(Usage)
                                if (!callApi(pMsg, errList)) {
                                    errFlg = true;
                                    preDsContrPk = curDsContrPk;
                                    break;
                                }
                                j = 0;
                            }
                            // add start 2019/08/16 QC#52413
                            BigDecimal curXsMtrAmtRate = (BigDecimal) dsContrOrcEff.get(XS_MTR_AMT_RATE);
                            if (ZYPCommonFunc.hasValue(curXsMtrAmtRate) && ZYPCommonFunc.hasValue(newXsMtrAmtRate) && curXsMtrAmtRate.compareTo(newXsMtrAmtRate) != 0) {
                                chngPrcFlg = true;
                            }
                            // add end 2019/08/16 QC#52413
                        }
                        if (errFlg) {
                            continue;
                        }
                    } else {
                        // Mod Start 2018/12/03 QC#28638
                        // dsContrDtlUsgList = getDsContrDtlUsage(curDsContrPk, curDsContrDtlPk);
                        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                            // Aggregate
                            BigDecimal aggLineContrDtlPk = getAggLineDsContrDtlPk(curDsContrPk);
                            if (!hasValue(aggLineContrDtlPk)) {
                                errList.add(S21MessageFunc.clspGetMessage(NSBM0006E));
                                preDsContrPk = curDsContrPk;
                                continue;
                            }
                            maxPrcUpRatio = getMaxPrcUpRato(curDsContrPk, aggLineContrDtlPk, DS_CONTR_BASE_USG_NM_U);
                            dsContrDtlUsgList = getDsContrDtlUsage(curDsContrPk, aggLineContrDtlPk);
                            rnwPrcMethCd = getRnwPrcMethCd(dsContrDtlUsgList);
                            curMtrPrcUpRatio = getMtrPrcUpRatio(dsContrDtlUsgList);
                            if (RNW_PRC_METH.MARKUP_PERCENT.equals(rnwPrcMethCd) && !hasValue(curMtrPrcUpRatio)) {
                                // Not Uplift
                                preDsContrPk = curDsContrPk;
                                continue;
                            }
                        } else {
                            dsContrDtlUsgList = getDsContrDtlUsage(curDsContrPk, curDsContrDtlPk);
                        }
                        // Mod End 2018/12/03 QC#28638
                        if (dsContrDtlUsgList.size() > 0) {
                            List<Map<String, Object>> dsContrPrcEffList = getUsagePrcEff(curDsContrDtlPk);
                            int j = 0;
                            for (int i = 0; i < dsContrPrcEffList.size(); i++) {
                                dsContrOrcEff = dsContrPrcEffList.get(i);
                                dsContrBllgMtrPk = (BigDecimal) dsContrOrcEff.get(DS_CONTR_BLLG_MTR_PK);
                                rnwPrcMethCd = getRnwPrcMethCd(dsContrDtlUsgList);
                                if (RNW_PRC_METH.MARKUP_PERCENT.equals(rnwPrcMethCd)) {
                                    curXsMtrCopyQty = (BigDecimal) dsContrOrcEff.get(XS_MTR_COPY_QTY);
                                    curMtrPrcUpRatio = getMtrPrcUpRatio(dsContrDtlUsgList);
                                    // Mod Start 2018/11/19 QC#28638
                                    newXsMtrAmtRate = calcNewXsMtrAmtRate((BigDecimal) dsContrOrcEff.get(XS_MTR_AMT_RATE), curMtrPrcUpRatio, IDX_6, maxPrcUpRatio);
                                    // Mod End 2018/11/19 QC#28638
                                } else if (RNW_PRC_METH.MODEL_PERCENT.equals(rnwPrcMethCd)) {
                                    curXsMtrCopyQty = (BigDecimal) dsContrOrcEff.get(XS_MTR_COPY_QTY);
                                    curMtrPrcUpRatio = getModelUpdateRuleMtr(curDsContrDtlPk, effFromDt, dsContrDtlTpCd, dsContrBllgMtrPk, dsContrCatgCd);
                                    if (curMtrPrcUpRatio == null) {
                                        errList.add(S21MessageFunc.clspGetMessage(NSZM0396E, new String[]{"Setup Information for Escalation rule", "Table = DS_MDL_ESCL_RULE, DS_MDL_ESCL_BLLG_MTR"}));
                                        errFlg = true;
                                        preDsContrPk = curDsContrPk;
                                        break;
                                    }
                                    // Mod Start 2018/11/19 QC#28638
                                    newXsMtrAmtRate = calcNewXsMtrAmtRate((BigDecimal) dsContrOrcEff.get(XS_MTR_AMT_RATE), curMtrPrcUpRatio, IDX_6, maxPrcUpRatio);
                                    // Mod End 2018/11/19 QC#28638
                                }
                                setParamForMtr(pMsg, j, newXsMtrAmtRate, curXsMtrCopyQty, dsContrOrcEff);
                                j++;
                                if (i == dsContrPrcEffList.size() - 1 || dsContrBllgMtrPk.compareTo((BigDecimal) dsContrPrcEffList.get(i + 1).get(DS_CONTR_BLLG_MTR_PK)) != 0) {
                                    setParamForHdr(pMsg, curDsContrDtlPk, effFromDt, effThruDt);
                                    //call Service Billing Schedule API(Usage)
                                    if (!callApi(pMsg, errList)) {
                                        errFlg = true;
                                        preDsContrPk = curDsContrPk;
                                        break;
                                    }
                                    j = 0;
                                }
                                // add start 2019/08/16 QC#52413
                                BigDecimal curXsMtrAmtRate = (BigDecimal) dsContrOrcEff.get(XS_MTR_AMT_RATE);
                                if (ZYPCommonFunc.hasValue(curXsMtrAmtRate) && ZYPCommonFunc.hasValue(newXsMtrAmtRate) && curXsMtrAmtRate.compareTo(newXsMtrAmtRate) != 0) {
                                    chngPrcFlg = true;
                                }
                                // add end 2019/08/16 QC#52413
                            }
                            if (errFlg) {
                                continue;
                            }
                        }
                    }
                    // add start 2019/07/25 QC#52070
                    // START 2022/12/05 E.Sanchez [QC#60807, ADD]
                    } else {
                        // START 2023/01/06 E.Sanchez [QC#60807, MOD]
                        //errFlg = true;
                        contrPcyDtErrFlg = true;
                        preDsContrPk = curDsContrPk;
                        //pcyDtErrList.add(createPolicyErrMsgDetails(rsAnnEscl.getString("DS_CONTR_NUM"), rsAnnEscl.getBigDecimal(SVC_MACH_MSTR_PK),
                        //                                           rsAnnEscl.getString("DS_CONTR_BASE_USG_NM"), effFromDt));
                        pcyDtErrList.add(createPolicyErrMsgDetails(rsAnnEscl.getString("DS_CONTR_NUM"), rsAnnEscl.getBigDecimal(SVC_MACH_MSTR_PK),
                                                                   DS_CONTR_BASE_USG_NM_U, effFromDt));
                        // END 2023/01/06 E.Sanchez [QC#60807, MOD]
                        continue;
                    }
                    // END 2022/12/05 E.Sanchez [QC#60807, ADD]
                    // add end 2019/07/25 QC#52070
                }
                // Del Start 2017/10/06 QC#21437
//                //2.3.get DS_CONTR Base(no-edit)
//                if (ZYPConstant.FLG_OFF_N.equals(rsAnnEscl.getString(BASE_FLG))) {
//                    DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = getDsContrBasePrcEff(curDsContrDtlPk);
//                    if (dsContrPrcEffTMsg == null) {
//                        preDsContrPk = curDsContrPk;
//                        continue;
//                    }
//                    //set Billing Schedule API
//                    setParamForHdr(pMsg, curDsContrDtlPk, effFromDt, effThruDt);
//                    //Calculate Term Amount
//                    newCalPrcAmtRate = getCalPrcAmtRate(effFromDt, contrCloDay, dsContrPrcEffTMsg);
//                    setParamForBase(pMsg, dsContrPrcEffTMsg.basePrcDealAmt.getValue(), newCalPrcAmtRate);
//                    //call Service Billing Schedule API(Base)
//                    if (!callApi(pMsg, errList)) {
//                        errFlg = true;
//                        preDsContrPk = curDsContrPk;
//                        continue;
//                    }
//                }
//                //2.4.get DS_CONTR Usage(no-edit)
//                if (ZYPConstant.FLG_OFF_N.equals(rsAnnEscl.getString(BASE_FLG))) {
//                    DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = getDsContrBasePrcEff(curDsContrDtlPk);
//                    if (dsContrPrcEffTMsg == null) {
//                        preDsContrPk = curDsContrPk;
//                        continue;
//                    }
//                    List<Map<String, Object>> dsContrPrcEffList = getUsagePrcEff(curDsContrDtlPk);
//                    int j = 0;
//                    for (int i = 0; i < dsContrPrcEffList.size(); i++) {
//                        dsContrOrcEff = dsContrPrcEffList.get(i);
//                        dsContrBllgMtrPk = (BigDecimal) dsContrOrcEff.get(DS_CONTR_BLLG_MTR_PK);
//                        setParamForMtr(pMsg, j, (BigDecimal) dsContrOrcEff.get(XS_MTR_AMT_RATE), (BigDecimal) dsContrOrcEff.get(XS_MTR_COPY_QTY), dsContrOrcEff);
//                        j++;
//                        if (i == dsContrPrcEffList.size() - 1 || dsContrBllgMtrPk.compareTo((BigDecimal) dsContrPrcEffList.get(i + 1).get(DS_CONTR_BLLG_MTR_PK)) != 0) {
//                            setParamForHdr(pMsg, curDsContrDtlPk, effFromDt, effThruDt);
//                            //call Service Billing Schedule API(Usage)
//                            if (!callApi(pMsg, errList)) {
//                                errFlg = true;
//                                preDsContrPk = curDsContrPk;
//                                break;
//                            }
//                            j = 0;
//                        }
//                    }
//                    if (errFlg) {
//                        continue;
//                    }
//                }
                // Del End 2017/10/06 QC#21437

                // add start 2019/07/25 QC#52070
                if (!trgtContrDtl) {
                    if (!updateDsContrDtl(curDsContrDtlPk, effFromDt)) {
                        errFlg = true;
                        errList.add(S21MessageFunc.clspGetMessage(NSBM0006E));
                    }
                    // add start 2019/08/15 QC#52413
                    if (!updateDsContrRnwEscl(curDsContrPk, curDsContrDtlPk, contrUplftTpCd, effFromDt, baseFlg, usageFlg)) {
                        errFlg = true;
                        errList.add(S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {"DS_CONTR_RNW_ESCL" }));
                    }
                    // add end 2019/08/15 QC#52413
                    preDsContrPk = curDsContrPk;
                    continue;
                }
                // add end 2019/07/25 QC#52070

                if (!updateDsContrDtl(curDsContrDtlPk, effFromDt, dsContrPkList)) {
                    errFlg = true;
                    errList.add(S21MessageFunc.clspGetMessage(NSBM0006E));
                    preDsContrPk = curDsContrPk;
                    continue;
                }

                // add start 2019/08/15 QC#52413
                if (!updateDsContrRnwEscl(curDsContrPk, curDsContrDtlPk, contrUplftTpCd, effFromDt, baseFlg, usageFlg)) {
                    errFlg = true;
                    errList.add(S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {"DS_CONTR_RNW_ESCL" }));
                }
                // add end 2019/08/15 QC#52413

                // START 2017/08/24 E.Kameishi [QC#8661,ADD]
                // mod start 2019/08/15 QC#52413
                //if (getAnnLtrCtrlInfo(curDsContrDtlPk)) {
                if (getAnnLtrCtrlInfo(curDsContrDtlPk)) {// removed checking for chngPrcFlg [QC#58313]
                // mod end 2019/08/15 QC#52413
                    if (!createDsContrPrcChngRec(curDsContrDtlPk, curDsContrPk, effFromDt, dsContrCatgCd)) {
                        errFlg = true;
                        errList.add(S21MessageFunc.clspGetMessage(NSAM0032E, new String[]{"DS_CONTR_PRC_CHNG_REC"}));
                        
                        preDsContrPk = curDsContrPk;
                        continue;
                    }
                }
                // END 2017/08/24 E.Kameishi [QC#8661,ADD]
                // START 2017/02/10 K.Kitachi [QC#17410, ADD]
                if (!createCfsContrPrcUplft(curDsContrPk, curDsContrDtlPk, contrUplftTpCd, errList)) {
                    errFlg = true;
                    preDsContrPk = curDsContrPk;
                    continue;
                }
                // END 2017/02/10 K.Kitachi [QC#17410, ADD]
                preDsContrPk = curDsContrPk;
            }
            // add start 2016/05/23 CSA Defect#7381
            updateBaseTermAmtForAggLine(dsContrPkList);
            // add end 2016/05/23 CSA Defect#7381

            // add start 2016/06/06 CSA Defect#1523, 4624
            callContrTrk(dsContrPkList, errList);
            // add end 2016/06/06 CSA Defect#1523, 4624

            if (procFlg) {
                // START 2023/01/06 E.Sanchez [QC#60807, ADD]
                if (contrPcyDtErrFlg) {
                    errFlg = true;
                }
                // END 2023/01/06 E.Sanchez [QC#60807, ADD]
                // START 2018/05/14 K.Kojima [QC#25640,ADD]
                // mod start 2019/07/25 QC#52070
                if (!errFlg && trgtContr) {
                // mod end 2019/07/25 QC#52070
                    if (!callSumAggregateAPI(errList, preDsContrPk)) {
                        errFlg = true;
                    }
                }
                // END 2018/05/14 K.Kojima [QC#25640,ADD]
                searchCnt++;
                if (!errFlg) {
                    infoSccessCnt++;
                    commit();
                } else {
                    rollback();
                    // del start 2016/05/27 CSA Defect#447
//                    sendErrMail(errList);
                    // del end 2016/05/27 CSA Defect#447
                }
            }

            // START 2017/10/03 E.Kameishi [QC#21504,MOD]
            // START 2017/08/24 E.Kameishi [QC#8661,ADD]
            createAnlEscrWrk(errList);
            // END 2017/08/24 E.Kameishi [QC#8661,ADD]
            // END 2017/10/03 E.Kameishi [QC#21504,MOD]

            // add start 2016/05/27 CSA Defect#447
            // mod start 2016/07/13 CSA Defect#11818
            if (!errList.isEmpty()) {
            // mod end 2016/07/13 CSA Defect#11818
                sendErrMail(errList);
                for (SVC_BAT_ERR_LOGTMsg inTMsg : errLogTMsgList) {
                    S21FastTBLAccessor.insert(inTMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                        // mod start 2016/07/13 CSA Defect#11818
                        S21InfoLogOutput.println(NSAM0032E, new String[] {"SVC_BAT_ERR_LOG" });
                        // mod end 2016/07/13 CSA Defect#11818
                    }
                }
            }
            // add end 2016/05/27 CSA Defect#447

            // START 2022/12/05 E.Sanchez [QC#60807, ADD]
            if(!pcyDtErrList.isEmpty()){
                sendPolicyDateErrMail(pcyDtErrList);
            }
            // END 2022/12/05 E.Sanchez [QC#60807, ADD]
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(annEsclList, rsAnnEscl);
        }
    }

    // add start 2016/05/27 CSA Defect#447
    private void setSvcBatErrLog(List<SVC_BAT_ERR_LOGTMsg> errLogTMsgList, BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk, ResultSet rsAnnEscl) throws SQLException {
        SVC_BAT_ERR_LOGTMsg errLogTMsg = new SVC_BAT_ERR_LOGTMsg();
        setValue(errLogTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(errLogTMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_BAT_ERR_LOG_SQ));
        setValue(errLogTMsg.bizAppId, BIZ_APP_ID);
        setValue(errLogTMsg.svcBatErrLogTs, this.sysTime);
        if (hasValue(curDsContrPk)) {
            setValue(errLogTMsg.svcBatErrKeyNum_01, String.valueOf(curDsContrPk));
        }
        if (hasValue(curDsContrDtlPk)) {
            setValue(errLogTMsg.svcBatErrKeyNum_02, String.valueOf(curDsContrDtlPk));
        }

        if (hasValue(rsAnnEscl.getBigDecimal(SVC_MACH_MSTR_PK))) {
            setValue(errLogTMsg.svcBatErrKeyNum_03, String.valueOf(rsAnnEscl.getBigDecimal(SVC_MACH_MSTR_PK)));
        }
        setValue(errLogTMsg.svcBatErrKeyNm_01, LOGICAL_NAME_DS_CONTR_PK);
        setValue(errLogTMsg.svcBatErrKeyNm_02, LOGICAL_NAME_DS_CONTR_DTL_PK);
        setValue(errLogTMsg.svcBatErrKeyNm_03, LOGICAL_NAME_SVC_MACH_MSTR_PK);
        setValue(errLogTMsg.svcBatErrMsgTxt, S21MessageFunc.clspGetMessage(NSAM0481E, new String[]{String.valueOf(rsAnnEscl.getBigDecimal(SVC_MACH_MSTR_PK))}));
        errLogTMsgList.add(errLogTMsg);
    }
    // add end 2016/05/27 CSA Defect#447

    /**
     * @param pMsg
     * @param errFlg
     * @param errList
     * @return
     */
    private boolean callApi(NSZC047005PMsg pMsg, List<String> errList) {
        new NSZC047001().execute(pMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);
        // START 2021/08/26 L.Mandanas [QC#59077, DEL]
//        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
//            S21InfoLogOutput.printlnv(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
//            errList.add(S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue()));
//            pMsg.clear();
//            return false;
//        }
        // END 2021/08/26 L.Mandanas [QC#59077, DEL]
        // START 2021/08/26 L.Mandanas [QC#59077, ADD]
        String msgId = null;
        List < String > msgPrms = null;
        List < S21ApiMessage > msgs = S21ApiUtil.getXxMsgList(pMsg);
        for (S21ApiMessage msg : msgs) {
            msgId = msg.getXxMsgid();
            msgPrms = msg.getXxMsgPrmList();
            if (msgPrms.equals(null)) {
                msgPrms = Collections.emptyList();
            }
            S21InfoLogOutput.printlnv(S21MessageFunc.clspGetMessage(
                      msgId, msgPrms.toArray(new String[msgPrms.size()])));
            errList.add(S21MessageFunc.clspGetMessage(msgId, msgPrms.toArray(
                    new String[msgPrms.size()])));
            pMsg.clear();
            return false;
        // END 2021/08/26 L.Mandanas [QC#59077, ADD]
        }
        pMsg.clear();
        return true;
    }

    // mod end 2016/05/23 CSA Defect#7381
    /**
     * @param curDsContrDtlPk
     * @return
     */
    private boolean updateDsContrDtl(BigDecimal curDsContrDtlPk, String effFromDt, List<BigDecimal> dsContrPkList) {
        DS_CONTR_DTLTMsg inTMsg = new DS_CONTR_DTLTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.dsContrDtlPk, curDsContrDtlPk);
        DS_CONTR_DTLTMsg outTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
        if (outTMsg == null) {
            S21InfoLogOutput.printlnv(NSBM0006E);
            return false;
        }
        setValue(outTMsg.uplftEffFromDt, effFromDt);
        setValue(outTMsg.basePrcTermDealAmtRate, getBasePrcTermDealAmtRate(curDsContrDtlPk));
        EZDTBLAccessor.update(outTMsg);
        if (!dsContrPkList.contains(outTMsg.dsContrPk.getValue())) {
            dsContrPkList.add(outTMsg.dsContrPk.getValue());
        }
        return true;
    }
    // mod end 2016/05/23 CSA Defect#7381

    // add start 2016/05/23 CSA Defect#7381
    private BigDecimal getBasePrcTermDealAmtRate(BigDecimal dsContrDtlPk) {
        BigDecimal rtnVal = BigDecimal.ZERO;
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        inMsg.setSQLID("006");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        DS_CONTR_PRC_EFFTMsgArray resultArray = (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        for (int i = 0; i < resultArray.getValidCount(); i++) {
            if (!hasValue(resultArray.no(i).basePrcTermDealAmtRate)) {
                continue;
            }
            rtnVal = rtnVal.add(resultArray.no(i).basePrcTermDealAmtRate.getValue());
        }
        return rtnVal;
    }

    // START 2017/08/28 K.Kitachi [QC#20529, MOD]
    private void updateBaseTermAmtForAggLine(List<BigDecimal> dsContrPkList) {
//        BigDecimal basePrcTermDealAmtRate;
        Map<String, Object> map;
        for (BigDecimal dsContrPk : dsContrPkList) {
//            basePrcTermDealAmtRate = getBaseTermAmtForAggLine(dsContrPk);
//            if (!hasValue(basePrcTermDealAmtRate)) {
//                continue;
//            }
            map = getBaseTermAmtForAggLine(dsContrPk);
            if (map == null) {
                continue;
            }

            DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
            inMsg.setSQLID("006");
            inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            inMsg.setConditionValue("dsContrPk01", dsContrPk);
            inMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.AGGREGATE);
            DS_CONTR_DTLTMsgArray outTMsgArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);
            if (outTMsgArray.getValidCount() != 1) {
                continue;
            }
//            setValue(outTMsgArray.no(0).basePrcTermDealAmtRate, basePrcTermDealAmtRate);
            setValue(outTMsgArray.no(0).basePrcTermDealAmtRate, (BigDecimal) map.get("BASE_PRC_TERM_DEAL_AMT_RATE"));
            setValue(outTMsgArray.no(0).basePrcDealAmt, (BigDecimal) map.get("BASE_PRC_DEAL_AMT"));
            EZDTBLAccessor.update(outTMsgArray.no(0));
        }
    }

//    private BigDecimal getBaseTermAmtForAggLine(BigDecimal dsContrPk) {
    private Map<String, Object> getBaseTermAmtForAggLine(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        // START 2017/06/19 Y.Osawa [QC#19256,ADD]
        param.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/19 Y.Osawa [QC#19256,ADD]
//        return (BigDecimal) ssmBatchClient.queryObject("getBaseTermAmtForAggLine", param);
        return (Map<String, Object>) ssmBatchClient.queryObject("getBaseTermAmtForAggLine", param);
    }
    // END 2017/08/28 K.Kitachi [QC#20529, MOD]
    // add end 2016/05/23 CSA Defect#7381

    /**
     * @param pMsg
     * @param curDsContrDtlPk
     * @param effFromDt
     * @param effThruDt
     */
    private void setParamForHdr(NSZC047005PMsg pMsg, BigDecimal curDsContrDtlPk, String effFromDt, String effThruDt) {
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, PARAM_ESCL);
        setValue(pMsg.slsDt, this.slsDt);
        setValue(pMsg.dsContrDtlPk, curDsContrDtlPk);
        setValue(pMsg.effFromDt, effFromDt);
        setValue(pMsg.effThruDt, effThruDt);
    }

    /**
     * @param pMsg
     * @param newBasePrcDealAmt
     * @param newCalPrcAmtRate
     */
    private void setParamForBase(NSZC047005PMsg pMsg, BigDecimal newBasePrcDealAmt, BigDecimal newCalPrcAmtRate) {
        setValue(pMsg.baseChrgFlg, FLG_ON_Y);
        setValue(pMsg.usgChrgFlg, FLG_OFF_N);
        setValue(pMsg.basePrcDealAmt, newBasePrcDealAmt);
        setValue(pMsg.basePrcTermDealAmtRate, newCalPrcAmtRate);
    }

    /**
     * @param pMsg
     * @param i
     * @param newXsMtrAmtRate
     * @param curXsMtrCopyQty
     * @param dsContrOrcEff
     */
    private void setParamForMtr(NSZC047005PMsg pMsg, int i, BigDecimal newXsMtrAmtRate, BigDecimal curXsMtrCopyQty, Map<String, Object> dsContrOrcEff) {
        setValue(pMsg.baseChrgFlg, FLG_OFF_N);
        setValue(pMsg.usgChrgFlg, FLG_ON_Y);
        setValue(pMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML, (BigDecimal) dsContrOrcEff.get(DS_CONTR_BLLG_MTR_PK));
        setValue(pMsg.xxMtrLineList.no(i).contrXsCopyPk_ML, (BigDecimal) dsContrOrcEff.get(CONTR_XS_COPY_PK));
        setValue(pMsg.xxMtrLineList.no(i).xsMtrCopyQty_ML, curXsMtrCopyQty);
        setValue(pMsg.xxMtrLineList.no(i).xsMtrAmtRate_ML, newXsMtrAmtRate);
        setValue(pMsg.xxMtrLineList.no(i).xsMtrFirstFlg_ML, (String) dsContrOrcEff.get(XS_MTR_FIRST_FLG));
        pMsg.xxMtrLineList.setValidCount(i + 1);
    }

    /**
     * @param effFromDt
     * @param newBasePrcDealAmt
     * @param dsContrDtlBaseList
     */
    private BigDecimal getCalPrcAmtRate(String effFromDt, BigDecimal newBasePrcDealAmt, List<Map<String, Object>> dsContrDtlBaseList) {
        CalcTermAmtBean inBean = new CalcTermAmtBean();
        inBean.setGlblCmpyCd(this.glblCmpyCd);
        inBean.setXxModeCd(MODE_ANN_ESCL);
        inBean.setSlsDt(this.slsDt);
        inBean.setCcyCd((String) dsContrDtlBaseList.get(0).get(CCY_CD));
        inBean.setContrPrcEffFromDt(effFromDt);
        inBean.setContrPrcEffThruDt((String) dsContrDtlBaseList.get(0).get(CONTR_EFF_THRU_DT));
        inBean.setContrCloDay((String) dsContrDtlBaseList.get(0).get(CONTR_CLO_DAY));
        inBean.setBllgCycleCd((String) dsContrDtlBaseList.get(0).get(BLLG_CYCLE_CD));
        inBean.setBasePrcDealAmt(newBasePrcDealAmt);
        return NSXC003001CalcTermAmt.calcTermAmt(inBean);
    }

    // START 2017/11/01 K.Kojima [QC#21859,DEL]
    // /**
    //  * @param effFromDt
    //  * @param newBasePrcDealAmt
    //  * @param dsContrDtlBaseList
    //  */
    // private BigDecimal getCalPrcAmtRate(String effFromDt, String contrCloDay, DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg) {
    //     CalcTermAmtBean inBean = new CalcTermAmtBean();
    //     inBean.setGlblCmpyCd(this.glblCmpyCd);
    //     inBean.setXxModeCd(MODE_ANN_ESCL);
    //     inBean.setSlsDt(this.slsDt);
    //     inBean.setCcyCd(dsContrPrcEffTMsg.ccyCd.getValue());
    //     inBean.setContrPrcEffFromDt(effFromDt);
    //     inBean.setContrPrcEffThruDt(dsContrPrcEffTMsg.contrPrcEffThruDt.getValue());
    //     inBean.setContrCloDay(contrCloDay);
    //     inBean.setBllgCycleCd(dsContrPrcEffTMsg.bllgCycleCd.getValue());
    //     inBean.setBasePrcDealAmt(dsContrPrcEffTMsg.basePrcDealAmt.getValue());
    //     return NSXC003001CalcTermAmt.calcTermAmt(inBean);
    // }
    // END 2017/11/01 K.Kojima [QC#21859,DEL]

    // Mod Start 2018/11/19 QC#28638
    /**
     * @param dsContrDtlBaseList
     */
    private BigDecimal calcNewBasePrcDealAmt(BigDecimal curBasePrcDealAmt, BigDecimal curBasePrcUpRatio, int aftDeclPntDigitNum, BigDecimal maxPrcUpRato) {
        BigDecimal hundred = new BigDecimal("100");
        if (!hasValue(curBasePrcUpRatio)) {
            return BigDecimal.ZERO;
        }
//        // mod start 2016/02/02 CSA Defect#3996
//        return curBasePrcDealAmt.multiply((hundred.add(curBasePrcUpRatio)).divide(hundred)).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
//        // mod end 2016/02/02 CSA Defect#3996
        BigDecimal newBasePrcDealAmt = curBasePrcDealAmt.multiply((hundred.add(curBasePrcUpRatio)).divide(hundred)).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
        if (hasValue(maxPrcUpRato)) {
            BigDecimal maxBasePrcDealAmt = curBasePrcDealAmt.multiply((hundred.add(maxPrcUpRato)).divide(hundred)).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
            if (newBasePrcDealAmt.compareTo(maxBasePrcDealAmt) > 0) {
                return maxBasePrcDealAmt;
            }
        }
        return newBasePrcDealAmt;
    }
    // Mod End 2018/11/19 QC#28638
    // Mod Start 2018/11/19 QC#28638
    /**
     * @param dsContrDtlBaseList
     */
    private BigDecimal calcNewXsMtrAmtRate(BigDecimal curXsMtrAmtRate, BigDecimal curPrcUpRatio, int aftDeclPntDigitNum, BigDecimal maxPrcUpRato) {
        BigDecimal hundred = new BigDecimal("100");
        if (!hasValue(curPrcUpRatio)) {
            return BigDecimal.ZERO;
        }
//        // mod start 2016/02/02 CSA Defect#3996
//        return curXsMtrAmtRate.multiply((hundred.add(curPrcUpRatio)).divide(hundred)).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
//        // mod end 2016/02/02 CSA Defect#3996
        BigDecimal newXsMtrAmtRate = curXsMtrAmtRate.multiply((hundred.add(curPrcUpRatio)).divide(hundred)).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
        if (hasValue(maxPrcUpRato)) {
            BigDecimal maxXsMtrAmtRate = curXsMtrAmtRate.multiply((hundred.add(maxPrcUpRato)).divide(hundred)).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
            if (newXsMtrAmtRate.compareTo(maxXsMtrAmtRate) > 0) {
                return maxXsMtrAmtRate;
            }
        }
        return newXsMtrAmtRate;
    }
    // Mod End 2018/11/19 QC#28638
    /**
     * @param dsContrDtlBaseList
     */
    private String getRnwPrcMethCd(List<Map<String, Object>> dsContrDtlBaseList) {
        if (dsContrDtlBaseList.get(0).get("REB_UPLFT_PRC_METH_CD") != null) {
            return (String) dsContrDtlBaseList.get(0).get("REB_UPLFT_PRC_METH_CD");
        } else if (dsContrDtlBaseList.get(0).get("DTL_UPLFT_PRC_METH_CD") != null) {
            return (String) dsContrDtlBaseList.get(0).get("DTL_UPLFT_PRC_METH_CD");
        } else {
            return (String) dsContrDtlBaseList.get(0).get("HDR_UPLFT_PRC_METH_CD");
        }
    }

    /**
     * @param dsContrDtlBaseList
     */
    @SuppressWarnings("unused")
    private String getcontrUpliftTpCd(List<Map<String, Object>> dsContrDtlBaseList) {
        if (dsContrDtlBaseList.get(0).get("REB_CONTR_UPLFT_TP_CD") != null) {
            return (String) dsContrDtlBaseList.get(0).get("REB_CONTR_UPLFT_TP_CD");
        } else if (dsContrDtlBaseList.get(0).get("DTL_CONTR_UPLFT_TP_CD") != null) {
            return (String) dsContrDtlBaseList.get(0).get("DTL_CONTR_UPLFT_TP_CD");
        } else {
            return (String) dsContrDtlBaseList.get(0).get("HDR_CONTR_UPLFT_TP_CD");
        }
    }

    /**
     * @param dsContrDtlBaseList
     */
    private BigDecimal getBasePrcUpRatio(List<Map<String, Object>> dsContrDtlBaseList) {
        if (dsContrDtlBaseList.get(0).get("REB_BASE_PRC_UP_RATIO") != null) {
            //mod start 2017/09/27 QC#21437
            //return (BigDecimal) dsContrDtlBaseList.get(0).get("REB_MTR_PRC_UP_RATIO");
            return (BigDecimal) dsContrDtlBaseList.get(0).get("REB_BASE_PRC_UP_RATIO");
            //mod end 2017/09/27 QC#21437
        } else if (dsContrDtlBaseList.get(0).get("DTL_BASE_PRC_UP_RATIO") != null) {
            return (BigDecimal) dsContrDtlBaseList.get(0).get("DTL_BASE_PRC_UP_RATIO");
        } else {
            return (BigDecimal) dsContrDtlBaseList.get(0).get("HDR_BASE_PRC_UP_RATIO");
        }
    }

    /**
     * @param dsContrDtlUsgList
     */
    private BigDecimal getMtrPrcUpRatio(List<Map<String, Object>> dsContrDtlUsgList) {
        if (dsContrDtlUsgList.get(0).get("REB_MTR_PRC_UP_RATIO") != null) {
            return (BigDecimal) dsContrDtlUsgList.get(0).get("REB_MTR_PRC_UP_RATIO");
        } else if (dsContrDtlUsgList.get(0).get("DTL_MTR_PRC_UP_RATIO") != null) {
            return (BigDecimal) dsContrDtlUsgList.get(0).get("DTL_MTR_PRC_UP_RATIO");
        } else {
            return (BigDecimal) dsContrDtlUsgList.get(0).get("HDR_MTR_PRC_UP_RATIO");
        }
    }

    /**
     * @param curDsContrPk
     * @param curDsContrDtlPk
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getDsContrDtlBase(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk) {
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDsContrDtlBase", setParamForDsContrDtlBase(curDsContrPk, curDsContrDtlPk));
    }

    /**
     * @param curDsContrPk
     * @param curDsContrDtlPk
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getDsContrDtlUsage(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk) {
        return ssmBatchClient.queryObjectList("getDsContrDtlUsage", setParamForDsContrDtlUsage(curDsContrPk, curDsContrDtlPk));
    }

    /**
     * @return
     */
    private Map<String, Object> setParamForDsContrDtlBase(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrPk", curDsContrPk);
        paramMap.put("dsContrDtlPk", curDsContrDtlPk);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNmB", DS_CONTR_BASE_USG_NM_B);
        paramMap.put("uplftBaseFlg", FLG_ON_Y);
        paramMap.put("dsContrDtlTpF", DS_CONTR_DTL_TP.FLEET);
        paramMap.put("dsContrDtlTpA", DS_CONTR_DTL_TP.AGGREGATE);
        paramMap.put("dsContrCatgF", DS_CONTR_CATG.FLEET);
        paramMap.put("dsContrCatgA", DS_CONTR_CATG.AGGREGATE);
        return paramMap;
    }

    /**
     * @return
     */
    private Map<String, Object> setParamForDsContrDtlUsage(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrPk", curDsContrPk);
        paramMap.put("dsContrDtlPk", curDsContrDtlPk);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNmU", DS_CONTR_BASE_USG_NM_U);
        paramMap.put("uplftUsgFlg", FLG_ON_Y);
        paramMap.put("dsContrDtlTpF", DS_CONTR_DTL_TP.FLEET);
        paramMap.put("dsContrDtlTpA", DS_CONTR_DTL_TP.AGGREGATE);
        paramMap.put("dsContrCatgF", DS_CONTR_CATG.FLEET);
        paramMap.put("dsContrCatgA", DS_CONTR_CATG.AGGREGATE);
        return paramMap;
    }

    /**
     * @return
     */
    private Map<String, Object> setParamForUsagePrcEff(BigDecimal curDsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", curDsContrDtlPk);
        return paramMap;
    }

    /**
     * @param curDsContrPk
     * @param curDsContrDtlPk
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getUsagePrcEff(BigDecimal curDsContrDtlPk) {
        return ssmBatchClient.queryObjectList("getUsagePrcEff", setParamForUsagePrcEff(curDsContrDtlPk));
    }

    /**
     * @param curDsContrPk
     * @param curDsContrDtlPk
     */
    private DS_CONTR_PRC_EFFTMsg getDsContrBasePrcEff(BigDecimal curDsContrDtlPk) {
        DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();
        inTMsg.setSQLID("007");
        inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inTMsg.setConditionValue("dsContrDtlPk01", curDsContrDtlPk);
        inTMsg.setConditionValue("dsContrPrcEffStsCd01", DS_CONTR_DTL_STS.CANCELLED);
        DS_CONTR_PRC_EFFTMsgArray outTmsgArray = (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outTmsgArray.getValidCount() == 0) {
            return null;
        }
        return outTmsgArray.no(0);
    }

    /**
     * @return
     */
    private Map<String, Object> setParamForModelUpdateRule(BigDecimal curDsContrDtlPk, String effFromDt, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", curDsContrDtlPk);
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        paramMap.put("effFromDt", effFromDt);
        // add start 2016/01/27 CSA Defect#3787
        paramMap.put("maxDt", MAX_DATE);
        // add end 2016/01/27 CSA Defect#3787
        String[] dsContrDtlTpList = new String[]{DS_CONTR_DTL_TP.FLEET, DS_CONTR_DTL_TP.AGGREGATE};
        paramMap.put("dsContrDtlTpList", dsContrDtlTpList);
        return paramMap;
    }

    /**
     * getModelUpdateRuleBase
     * @param curDsContrDtlPk
     * @param effFromDt
     * @param dsContrDtlTpCd
     * @return
     */
    @SuppressWarnings("unchecked")
    private BigDecimal getModelUpdateRuleBase(BigDecimal curDsContrDtlPk, String effFromDt, String dsContrDtlTpCd) {
        List<BigDecimal> rsltList = new ArrayList<BigDecimal>();
        // START 2017/12/06 [QC#22891,MOD]
        //if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
        //    rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleBaseForLine", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, null));
        //} else {
        //    rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleBase", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, null));
        //}
        if (!DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
            rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleBase", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, null));
        }
        // END 2017/12/06 [QC#22891,MOD]
        if (rsltList.size() == 0) {
            return this.defModelUpdateRuleBase;
        }
        return rsltList.get(0);
    }

    /**
     * @param curDsContrDtlPk
     * @param effFromDt
     * @param dsContrBllgMtrPk
     */
    @SuppressWarnings("unchecked")
    private BigDecimal getModelUpdateRuleMtr(BigDecimal curDsContrDtlPk, String effFromDt, String dsContrDtlTpCd, BigDecimal dsContrBllgMtrPk, String dsContrCatgCd) {
        List<BigDecimal> rsltList = new ArrayList<BigDecimal>();
        // START 2017/12/06 [QC#22891,MOD]
        //if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
        //    rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleMtrForLine", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, dsContrBllgMtrPk));
        //} else {
        //    rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleMtr", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, dsContrBllgMtrPk));
        //}
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleMtr", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, dsContrBllgMtrPk));
        }
        // END 2017/12/06 [QC#22891,MOD]
        if (rsltList.size() == 0) {
            return this.defModelUpdateRuleUsage;
        }
        return rsltList.get(0);
    }

    /**
     * @return
     */
    private Map<String, Object> setParamForAnnEscl() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.slsDt);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNmBase", DS_CONTR_BASE_USG_NM_B);
        paramMap.put("dsContrBaseUsgNmUsage", DS_CONTR_BASE_USG_NM_U);
        paramMap.put("dsContrCatgReg", DS_CONTR_CATG.REGULAR);
        paramMap.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        paramMap.put("dsContrCatgAgg", DS_CONTR_CATG.AGGREGATE);
        paramMap.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        paramMap.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);
        paramMap.put("flgOffN", ZYPConstant.FLG_OFF_N);
        String[] dsContrCatgList = new String[] {DS_CONTR_CATG.FLEET, DS_CONTR_CATG.AGGREGATE };
        paramMap.put("dsContrCatgList", dsContrCatgList);
        // START 2017/06/19 Y.Osawa [QC#19256,ADD]
        paramMap.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/19 Y.Osawa [QC#19256,ADD]
        // START 2017/11/27 K.Kojima [QC#22660,ADD]
        paramMap.put("doNotUplift", CONTR_UPLFT_TP.DO_NOT_UPLIFT);
        // END 2017/11/27 K.Kojima [QC#22660,ADD]
        return paramMap;
    }

    private void sendErrMail(List<String> errList) {
        NSXC001001SendMailForErrorInfo errorInfo = new NSXC001001SendMailForErrorInfo();
        errorInfo.addErrMsgList(errList);
        String errMsg = errorInfo.sendMail(this.glblCmpyCd, BATCH_ID);
        if (errMsg != null) {
            S21InfoLogOutput.println(errMsg);
        }
    }

    /**
     * isAggregateLine
     * @param dsContrCatgCd
     * @param dsContrDtlTpCd
     * @return
     */
    private boolean isAggregateLine(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            return true;
        }
        return false;
    }

    // add start 2016/06/06 CSA Defect#1523, 4624
    private void callContrTrk(List<BigDecimal> dsContrPkList, List<String> errList) {
        for (BigDecimal dsContrPk : dsContrPkList) {
            callContrTrkAPI(dsContrPk, errList);
        }
    }

    private void callContrTrkAPI(BigDecimal dsContrPk, List<String> errList) {
        String userId = BATCH_ID;
        if (!NSXC001001ContractTracking.callContrTrk(this.glblCmpyCd, ContrTrkProcMode.ANNUAL_ESCALATION.code, dsContrPk, userId, this.slsDt, null, null, ONBATCH_TYPE.BATCH)) {
            DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.dsContrPk, dsContrPk);
            tMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                addErrList(errList, S21MessageFunc.clspGetMessage(NSXC001001ContractTracking.ERR_MSG_ID), tMsg.dsContrNum.getValue());
            }
        }
    }

    private void addErrList(List<String> errList, String msgTxt, String dsContrNum) {
        errList.add(ZYPCommonFunc.concatString(msgTxt, " :Contract#: ", dsContrNum));
    }
    // add end 2016/06/06 CSA Defect#1523, 4624

    // START 2017/02/10 K.Kitachi [QC#17410, ADD]
    private MDL_NMTMsg getMdlNmTMsg() {
        if (!ZYPCommonFunc.hasValue(this.spclFltMdseCd)) {
            return null;
        }
        NSZC048001 svcMdlApi = new NSZC048001();
        NSZC048001PMsg svcMdlPMsg = new NSZC048001PMsg();
        setValue(svcMdlPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcMdlPMsg.slsDt, this.slsDt);
        setValue(svcMdlPMsg.prntMdseCd, this.spclFltMdseCd);
        svcMdlApi.execute(svcMdlPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(svcMdlPMsg)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(svcMdlPMsg.mdlId)) {
            return null;
        }
        MDL_NMTMsg inMsg = new MDL_NMTMsg();
        setValue(inMsg.t_GlblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.t_MdlId, svcMdlPMsg.mdlId);
        return (MDL_NMTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }

    private boolean createCfsContrPrcUplft(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String contrUplftTpCd, List<String> errList) {
        CFS_CONTR_PRC_UPLFTTMsg inMsg;
        DS_CONTRTMsg dsContrTMsg = getDsContrTMsg(dsContrPk);
        if (dsContrTMsg == null) {
            return false;
        }
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlTMsg(dsContrDtlPk);
        if (dsContrDtlTMsg == null) {
            return false;
        }
        // Base
        if (CONTR_UPLFT_TP.UPLIFT_BASE_ONLY.equals(contrUplftTpCd) || CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(contrUplftTpCd)) {
            if (isCfsDlr(dsContrDtlTMsg.baseBillToCustCd.getValue())) {
                inMsg = new CFS_CONTR_PRC_UPLFTTMsg();
                setCommonParam(inMsg, dsContrTMsg, dsContrDtlTMsg);
                if (isRegOrAgg(dsContrTMsg.dsContrCatgCd.getValue(), dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                    setRegAndAggBaseParam(inMsg, dsContrDtlTMsg);
                    if (!insertCfsContrPrcUplft(inMsg, errList)) {
                        return false;
                    }
                }
                if (isFltLine(dsContrTMsg.dsContrCatgCd.getValue(), dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                    setFltBaseParam(inMsg, dsContrDtlTMsg);
                    if (!insertCfsContrPrcUplft(inMsg, errList)) {
                        return false;
                    }
                }
            }
        }
        // Usage
        if (CONTR_UPLFT_TP.UPLIFT_OVERAGE_ONLY.equals(contrUplftTpCd) || CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(contrUplftTpCd)) {
            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg;
            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = getDsContrBllgMtrTMsgArray(dsContrDtlPk);
            for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
                if (isCfsDlr(dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                    inMsg = new CFS_CONTR_PRC_UPLFTTMsg();
                    setCommonParam(inMsg, dsContrTMsg, dsContrDtlTMsg);
                    if (isRegOrAgg(dsContrTMsg.dsContrCatgCd.getValue(), dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                        setRegAndAggUsgParam(inMsg, dsContrDtlTMsg, dsContrBllgMtrTMsg);
                        if (!insertCfsContrPrcUplft(inMsg, errList)) {
                            return false;
                        }
                    }
                    if (isFltLine(dsContrTMsg.dsContrCatgCd.getValue(), dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                        setFltUsgParam(inMsg, dsContrDtlTMsg, dsContrBllgMtrTMsg);
                        if (!insertCfsContrPrcUplft(inMsg, errList)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isCfsDlr(String billToCustCd) {
        BigDecimal count = countCfsDlr(billToCustCd);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean isRegOrAgg(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (dsContrCatgCd.equals(DS_CONTR_CATG.REGULAR) || (dsContrCatgCd.equals(DS_CONTR_CATG.AGGREGATE) && !dsContrDtlTpCd.equals(DS_CONTR_DTL_TP.AGGREGATE))) {
            return true;
        }
        return false;
    }

    private boolean isFltLine(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (dsContrCatgCd.equals(DS_CONTR_CATG.FLEET) && dsContrDtlTpCd.equals(DS_CONTR_DTL_TP.FLEET)) {
            return true;
        }
        return false;
    }

    private void setCommonParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal cfsContrPrcUplftPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_CONTR_PRC_UPLFT_SQ);
        setValue(inMsg.cfsContrPrcUplftPk, cfsContrPrcUplftPk);
        setValue(inMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(inMsg.dsContrNum, dsContrTMsg.dsContrNum);
        setValue(inMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
        setValue(inMsg.dsContrModTxt, this.dsContrModTxt);
        setValue(inMsg.dsContrCatgCd, dsContrTMsg.dsContrCatgCd);
        setValue(inMsg.cfsProcStsCd, CFS_PROC_STS.IN_COMPLETED);
    }

    private void setRegAndAggBaseParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        Map<String, Object> map = getRegAndAggBase(dsContrDtlTMsg.dsContrDtlPk.getValue());
        if (map != null) {
            setValue(inMsg.serNum, (String) map.get("SER_NUM"));
            setValue(inMsg.mdlId, (BigDecimal) map.get("MDL_ID"));
            setValue(inMsg.mdlNm, (String) map.get("MDL_NM"));
            setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
        }
        setValue(inMsg.uplftEffFromDt, dsContrDtlTMsg.uplftEffFromDt);
        setValue(inMsg.dsContrDtlTpCd, dsContrDtlTMsg.dsContrDtlTpCd);
        setValue(inMsg.svcMachMstrPk, dsContrDtlTMsg.svcMachMstrPk);
        setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
        setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
    }

    private void setRegAndAggUsgParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg) {
        Map<String, Object> map = getRegAndAggUsg(dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
        if (map != null) {
            setValue(inMsg.serNum, (String) map.get("SER_NUM"));
            setValue(inMsg.mdlId, (BigDecimal) map.get("MDL_ID"));
            setValue(inMsg.mdlNm, (String) map.get("MDL_NM"));
            setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
        }
        setValue(inMsg.uplftEffFromDt, dsContrDtlTMsg.uplftEffFromDt);
        setValue(inMsg.dsContrDtlTpCd, dsContrDtlTMsg.dsContrDtlTpCd);
        setValue(inMsg.svcMachMstrPk, dsContrDtlTMsg.svcMachMstrPk);
        setValue(inMsg.dsContrBllgMtrPk, dsContrBllgMtrTMsg.dsContrBllgMtrPk);
        setValue(inMsg.mtrLbCd, dsContrBllgMtrTMsg.bllgMtrLbCd);
        setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);
    }

    private void setFltBaseParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        Map<String, Object> map = getFltBase(dsContrDtlTMsg.dsContrDtlPk.getValue());
        if (map != null) {
            setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
        }
        if (this.mdlNmTMsg != null) {
            setValue(inMsg.mdlId, this.mdlNmTMsg.t_MdlId);
            setValue(inMsg.mdlNm, this.mdlNmTMsg.t_MdlNm);
        }
        setValue(inMsg.uplftEffFromDt, dsContrDtlTMsg.uplftEffFromDt);
        setValue(inMsg.dsContrDtlTpCd, dsContrDtlTMsg.dsContrDtlTpCd);
        String fleetSerNum = SFX_FLT_SER_NUM + inMsg.dsContrNum.getValue();
        setValue(inMsg.fleetSerNum, fleetSerNum);
        setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
        setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
    }

    private void setFltUsgParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg) {
        Map<String, Object> map = getFltUsg(dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
        if (map != null) {
            setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
        }
        if (this.mdlNmTMsg != null) {
            setValue(inMsg.mdlId, this.mdlNmTMsg.t_MdlId);
            setValue(inMsg.mdlNm, this.mdlNmTMsg.t_MdlNm);
        }
        setValue(inMsg.uplftEffFromDt, dsContrDtlTMsg.uplftEffFromDt);
        setValue(inMsg.dsContrDtlTpCd, dsContrDtlTMsg.dsContrDtlTpCd);
        setValue(inMsg.dsContrBllgMtrPk, dsContrBllgMtrTMsg.dsContrBllgMtrPk);
        setValue(inMsg.mtrLbCd, dsContrBllgMtrTMsg.bllgMtrLbCd);
        String fleetSerNum = SFX_FLT_SER_NUM + inMsg.dsContrNum.getValue();
        setValue(inMsg.fleetSerNum, fleetSerNum);
        setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);
    }

    private BigDecimal countCfsDlr(String billToCustCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("billToCustCd", billToCustCd);
        paramMap.put("cfsDlrCd", this.cfsDlrCd);
        return (BigDecimal) this.ssmBatchClient.queryObject("countCfsDlr", paramMap);
    }

    private Map<String, Object> getRegAndAggBase(BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", DS_CONTR_BASE_USG_NM_B);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getRegAndAggBase", paramMap);
    }

    private Map<String, Object> getRegAndAggUsg(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", DS_CONTR_BASE_USG_NM_U);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getRegAndAggUsg", paramMap);
    }

    private Map<String, Object> getFltBase(BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", DS_CONTR_BASE_USG_NM_B);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getFltBase", paramMap);
    }

    private Map<String, Object> getFltUsg(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", DS_CONTR_BASE_USG_NM_U);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getFltUsg", paramMap);
    }

    private boolean insertCfsContrPrcUplft(CFS_CONTR_PRC_UPLFTTMsg inMsg, List<String> errList) {
        S21FastTBLAccessor.insert(inMsg);
        String rtnCd = inMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            addErrList(errList, S21MessageFunc.clspGetMessage(NSAM0032E, new String[] {MSG_INFO_CFS_CONTR_PRC_UPLFT }), inMsg.dsContrNum.getValue());
            return false;
        }
        return true;
    }

    private DS_CONTRTMsg getDsContrTMsg(BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }

    private DS_CONTR_DTLTMsg getDsContrDtlTMsg(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }

    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrTMsgArray(BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // END 2017/02/10 K.Kitachi [QC#17410, ADD]

    // START 2017/08/24 E.Kameishi [QC#8661,ADD]
    /**
     * getAnnLtrCtrlInfo
     * @param dsContrPk BigDecimal
     * @return boolean
     */
    private boolean getAnnLtrCtrlInfo(BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        BigDecimal annLtrCtrlInfo = (BigDecimal) this.ssmBatchClient.queryObject("getAnnLtrCtrlInfo", ssmParam);
        if (BigDecimal.ZERO.equals(annLtrCtrlInfo)) {
            return false;
        }
        return true;
    }
    private boolean createDsContrPrcChngRec(BigDecimal dsContrDtlPk, BigDecimal dsContrPk, String effFromDt, String dsContrCatgCd) {
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            DS_CONTR_DTLTMsg param = new DS_CONTR_DTLTMsg();
            param.setSQLID("001");
            param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            param.setConditionValue("dsContrPk01", dsContrPk);
            DS_CONTR_DTLTMsgArray rtnList  =  (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(param);

            if (rtnList.length() == 0) {
                return false;
            }

            for (int i = 0; i < rtnList.getValidCount(); i++) {
                DS_CONTR_DTLTMsg dsContrDtlTMsg = (DS_CONTR_DTLTMsg) rtnList.get(i);
                if (!DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL.equals((String) dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {                    
                    DS_CONTR_PRC_CHNG_RECTMsg tMsg = new DS_CONTR_PRC_CHNG_RECTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrPrcChngRecPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_CHNG_REC_SQ));
                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, (BigDecimal) dsContrDtlTMsg.dsContrDtlPk.getValue());
                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.uplftEffFromDt, effFromDt);
                    S21FastTBLAccessor.insert(tMsg);
                    
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        return false;
                    }
                }
            }
            return true;
        }
        // insert DS_CONTR_PRC_CHNG_REC
        DS_CONTR_PRC_CHNG_RECTMsg tMsg = new DS_CONTR_PRC_CHNG_RECTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPrcChngRecPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_CHNG_REC_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.uplftEffFromDt, effFromDt);
        S21FastTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }
        return true;
    }
    /**
     * createRenewalLetterWork
     * @param errList
     */
    private void createAnlEscrWrk(List<String> errList) {
        NSZC100001PMsg letterForNewPMsg = new NSZC100001PMsg();
        BigDecimal preDsContrPk = BigDecimal.ZERO;
        BigDecimal curDsContrPk = BigDecimal.ZERO;
        String dsContrNum = null;
        String preDsContrNum = null;
        String printOldPrcFlg = null;
        BigDecimal curDsContrDtlPk = BigDecimal.ZERO;
        PreparedStatement letterListForNew = null;
        ResultSet rsLetterForNew = null;
        PreparedStatement letterListForRevise = null;
        ResultSet rsLetterForRevise = null;
        boolean errFlg = false;
        // START 2017/10/03 E.Kameishi [QC#21504,ADD]
        boolean procFlg = false;
        // END 2017/10/03 E.Kameishi [QC#21504,ADD]

        try {
            // *******************************************
            // Annual Escalation Letter Work request
            // *******************************************
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("slsDt", this.slsDt);
            
            letterListForNew = ssmLLClient.createPreparedStatement("getAnnEscLtrWrkList", paramMap, excParam);
            rsLetterForNew = letterListForNew.executeQuery();
            while (rsLetterForNew.next()) {
                // START 2017/10/03 E.Kameishi [QC#21504,ADD]
                procFlg = true;
                // END 2017/10/03 E.Kameishi [QC#21504,ADD]
                curDsContrPk = rsLetterForNew.getBigDecimal("DS_CONTR_PK");
                dsContrNum = rsLetterForNew.getString("DS_CONTR_NUM");
                curDsContrDtlPk = rsLetterForNew.getBigDecimal("DS_CONTR_DTL_PK");
                printOldPrcFlg = rsLetterForNew.getString("PRINT_OLD_PRC_FLG");
                if (!rsLetterForNew.isFirst() && curDsContrPk.compareTo(preDsContrPk) != 0) {
                    searchCnt++;
                    // Call Contract Agreement Letter Creation API
                    if (!callLetterApi(letterForNewPMsg, errList, preDsContrNum)) {
                        errFlg = true;
                    }
                    letterForNewPMsg = new NSZC100001PMsg();

                    if (!errFlg) {
                        infoSccessCnt++;
                        commit();
                    } else {
                        rollback();
                    }
                    errFlg = false;
                }
                // set api param
                setLetterApiParam(letterForNewPMsg, curDsContrPk, curDsContrDtlPk, printOldPrcFlg);
                preDsContrPk = curDsContrPk;
                preDsContrNum = dsContrNum;

            }
            // START 2017/10/03 E.Kameishi [QC#21504,ADD]
            // Last Row
            //if (rsLetterForNew.isAfterLast()) {
            if (procFlg) {
            // END 2017/10/03 E.Kameishi [QC#21504,ADD]
                searchCnt++;

                // Call Contract Agreement Letter Creation API
                if (!callLetterApi(letterForNewPMsg, errList, preDsContrNum)) {
                    errFlg = true;
                }

                if (!errFlg) {
                    infoSccessCnt++;
                    commit();
                } else {
                    rollback();
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(letterListForNew, rsLetterForNew);
            S21SsmLowLevelCodingClient.closeResource(letterListForRevise, rsLetterForRevise);
        }
    }

    /**
     * setLetterApiParam
     * @param letterForNewPMsg
     * @param dsContrPk
     * @param dsContrDtlPk
     */
    private void setLetterApiParam(NSZC100001PMsg letterForNewPMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String printOldPrcFlg) {
        setValue(letterForNewPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(letterForNewPMsg.slsDt, this.slsDt);
        setValue(letterForNewPMsg.bizAppId, BIZ_APP_ID);
        setValue(letterForNewPMsg.otptOpCd, OTPT_OP_CD);
        setValue(letterForNewPMsg.dsContrPk, dsContrPk);
        if (ZYPConstant.FLG_ON_Y.equals(printOldPrcFlg)) {
            setValue(letterForNewPMsg.rptId, RPT_ID_NSAF0030);
        } else {
            setValue(letterForNewPMsg.rptId, RPT_ID_NSAF0040);
        }
        int cnt = letterForNewPMsg.dsContrDtlPkList.getValidCount();
        setValue(letterForNewPMsg.dsContrDtlPkList.no(cnt).dsContrDtlPk, dsContrDtlPk);
        letterForNewPMsg.dsContrDtlPkList.setValidCount(cnt + 1);
    }

    /**
     * callLetterApi
     * @param pMsg
     * @param errList
     * @param dsContrNum
     * @return
     */
    private boolean callLetterApi(NSZC100001PMsg pMsg, List<String> errList, String dsContrNum) {
        new NSZC100001().execute(pMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            addErrList(errList, S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue()), dsContrNum);
            return false;
        }
        return true;
    }
    // END 2017/08/24 E.Kameishi [QC#8661,ADD]

    // START 2017/08/28 K.Kitachi [QC#20529, ADD]
    private Map<String, Object> getTotBasePrcForAggLine(BigDecimal dsContrPk, String effFromDt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrPk", dsContrPk);
        paramMap.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        paramMap.put("effFromDt", effFromDt);
        paramMap.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.CANCELLED);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getTotBasePrcForAggLine", paramMap);
    }
    // END 2017/08/28 K.Kitachi [QC#20529, ADD]

    // START 2017/11/01 K.Kojima [QC#21859,ADD]
    private int calcBllgCycleCntFromDuration(String glblCmpyCd, String contrEffFromDt, String contrEffThruDt, String baseBllgCycleCd) {
        // calculate duration
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
            startDt = df.parse(contrEffFromDt);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        String paramEndDate = contrEffThruDt;
        Calendar cal = Calendar.getInstance();
        String calcEndDate = "";
        BigDecimal durnCnt = BigDecimal.ZERO;

        while (paramEndDate.compareTo(calcEndDate) > 0) {
            cal.setTime(startDt);
            durnCnt = durnCnt.add(BigDecimal.ONE);

            cal.add(Calendar.MONTH, durnCnt.intValue());
            cal.add(Calendar.DATE, -1);

            calcEndDate = df.format(cal.getTime());
        }

        if (paramEndDate.compareTo(calcEndDate) != 0) {
            return 0;
        }

        BLLG_CYCLETMsg bcTMsg = new BLLG_CYCLETMsg();
        setValue(bcTMsg.glblCmpyCd, glblCmpyCd);
        setValue(bcTMsg.bllgCycleCd, baseBllgCycleCd);
        bcTMsg = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bcTMsg);

        if (bcTMsg == null || durnCnt.intValue() % bcTMsg.bllgCycleMthAot.getValueInt() != 0) {
            return 0;
        }
        return durnCnt.intValue() / bcTMsg.bllgCycleMthAot.getValueInt();
    }
    // END 2017/11/01 K.Kojima [QC#21859,ADD]

    // START 2018/05/14 K.Kojima [QC#25640,ADD]
    /**
     * callQaValidationApi
     * @param pMsg
     * @param errList
     * @return
     */
    private boolean callSumAggregateAPI(List<String> errList, BigDecimal dsContrPk) {
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
            addErrList(errList, S21MessageFunc.clspGetMessage(NSBM0006E), dsContrTMsg.dsContrNum.getValue());
            return false;
        }
        dsContrDtlTMsg = outTmsgArray.no(0);

        NSZC047011PMsg pMsg = new NSZC047011PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "11");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk.getValue());
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            addErrList(errList, S21MessageFunc.clspGetMessage(NSBM0006E), dsContrTMsg.dsContrNum.getValue());
            return false;
        }
        return true;
    }
    // END 2018/05/14 K.Kojima [QC#25640,ADD]
    // Add Start 2018/11/21 QC#28638
    private BigDecimal getMaxPrcUpRato(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String dsContrBaseUsgNm) {
        if (!hasValue(dsContrPk) || !hasValue(dsContrDtlPk)) {
            return null;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrPk", dsContrPk);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrMachLvlNum1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvlNum2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvlNum3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", dsContrBaseUsgNm);
        paramMap.put("slsDt", this.slsDt);
        return (BigDecimal) this.ssmBatchClient.queryObject("getMaxPrcUpRato", paramMap);
    }
    // Add End 2018/11/21 QC#28638
    // Add Start 2018/12/03 QC#28638
    private BigDecimal getAggLineDsContrDtlPk(BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        dsContrDtlTMsg.setSQLID("006");
        dsContrDtlTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsContrDtlTMsg.setConditionValue("dsContrPk01", dsContrPk);
        dsContrDtlTMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.AGGREGATE);
        DS_CONTR_DTLTMsgArray resultList = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(dsContrDtlTMsg);
        if (resultList.getValidCount() == 0) {
            return null;
        }
        return resultList.no(0).dsContrDtlPk.getValue();
    }
    // Add End 2018/12/03 QC#28638

    // add start 2019/07/25 QC#52070
    private boolean hasInvoicedSchd(BigDecimal dsContrDtlPk, String effFromDt, String baseChrgFlg, String usgChrgFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("effFromDt", effFromDt);
        ssmParam.put("baseChrgFlg", baseChrgFlg);
        ssmParam.put("usgChrgFlg", usgChrgFlg);
        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("getInvoicedSchdCount", ssmParam);
        if (BigDecimal.ZERO.compareTo(count) == 0) {
            return false;
        }
        return true;
    }

    private boolean updateDsContrDtl(BigDecimal curDsContrDtlPk, String effFromDt) {
        DS_CONTR_DTLTMsg inTMsg = new DS_CONTR_DTLTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.dsContrDtlPk, curDsContrDtlPk);
        DS_CONTR_DTLTMsg outTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
        if (outTMsg == null) {
            S21InfoLogOutput.printlnv(NSBM0006E);
            return false;
        }
        setValue(outTMsg.uplftEffFromDt, effFromDt);
        EZDTBLAccessor.update(outTMsg);
        return true;
    }
    // add end 2019/07/25 QC#52070

    // add start 2019/08/15 QC#52413
    private boolean updateDsContrRnwEscl(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String contrUplftTpCd, String effFromDt, String baseFlg, String usageFlg) {
        if (ZYPConstant.FLG_ON_Y.equals(baseFlg)) {
            List<Map<String, Object>> dsContrRnwEsclList = getDsContrRnwEsclList(dsContrPk, dsContrDtlPk, contrUplftTpCd, effFromDt, DS_CONTR_BASE_USG_NM_B);
            for (int i = 0; i < dsContrRnwEsclList.size(); i++) {
                DS_CONTR_RNW_ESCLTMsg tMsg = new DS_CONTR_RNW_ESCLTMsg();
                setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(tMsg.dsContrRnwEsclPk, (BigDecimal) dsContrRnwEsclList.get(i).get("DS_CONTR_RNW_ESCL_PK"));
                tMsg = (DS_CONTR_RNW_ESCLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
                if (tMsg != null) {
                    setValue(tMsg.uplftPcyDt, (String) dsContrRnwEsclList.get(i).get("NEXT_UPLFT_PCY_DT"));
                    EZDTBLAccessor.update(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        return false;
                    }
                }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(usageFlg)) {
            List<Map<String, Object>> dsContrRnwEsclList = getDsContrRnwEsclList(dsContrPk, dsContrDtlPk, contrUplftTpCd, effFromDt, DS_CONTR_BASE_USG_NM_U);
            for (int i = 0; i < dsContrRnwEsclList.size(); i++) {
                DS_CONTR_RNW_ESCLTMsg tMsg = new DS_CONTR_RNW_ESCLTMsg();
                setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(tMsg.dsContrRnwEsclPk, (BigDecimal) dsContrRnwEsclList.get(i).get("DS_CONTR_RNW_ESCL_PK"));
                tMsg = (DS_CONTR_RNW_ESCLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
                if (tMsg != null) {
                    setValue(tMsg.uplftPcyDt, (String) dsContrRnwEsclList.get(i).get("NEXT_UPLFT_PCY_DT"));
                    EZDTBLAccessor.update(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        return false;
                    }
                }
            }
        }
        // START 2023/06/20 R.Jin [QC#59586-2, DEL]
        // START 2023/04/21 R.Jin [QC#59586, MOD]
//        if (this.rowCnt == this.curRowCnt) {
//            this.rowCnt = 0;
//            this.curRowCnt = 0;
//        }
        // END 2023/04/21 R.Jin [QC#59586, MOD]
        // END 2023/06/20 R.Jin [QC#59586-2, DEL]

        return true;
    }

    private List<Map<String, Object>> getDsContrRnwEsclList(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String contrUplftTpCd, String effFromDt, String dsContrBaseUsgNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("contrUplftTpCd", contrUplftTpCd);
        ssmParam.put("uplftPcyDt", effFromDt);
        ssmParam.put("dsContrBaseUsgNm", dsContrBaseUsgNm);
        ssmParam.put("dsContrMachLvlNum1", DS_CONTR_MACH_LVL_NUM_1);
        ssmParam.put("dsContrMachLvlNum2", DS_CONTR_MACH_LVL_NUM_2);
        ssmParam.put("dsContrMachLvlNum3", DS_CONTR_MACH_LVL_NUM_3);
        // START 2023/04/21 R.Jin [QC#59586, MOD]
        if (this.rowCnt == this.curRowCnt) {
            ssmParam.put("lastRowFlg", ZYPConstant.FLG_ON_1);
        } else  {
            ssmParam.put("lastRowFlg", ZYPConstant.FLG_OFF_0);
        }
        // END 2023/04/21 R.Jin [QC#59586, MOD]
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrRnwEsclList", ssmParam);
    }
    // add end 2019/08/15 QC#52413

    // START 2022/12/05 E.Sanchez [QC#60807, ADD]
    /**
     * createPolicyErrMsgDetails
     * @param contrNum String
     * @param svcMachMstrPk BigDecimal
     * @param dsContrBaseUsgNm String
     * @param effFromDt String
     * @return String
     */
    private String createPolicyErrMsgDetails(String contrNum, BigDecimal svcMachMstrPk, String dsContrBaseUsgNm, String effFromDt) {
        StringBuilder msgBuf = new StringBuilder();
        // Contract Number
        msgBuf.append(MAIL_CONTRACT_NUM + contrNum);
        msgBuf.append(LINE_FEED_CODE);
        // START 2023/01/06 E.Sanchez [QC#60807, ADD]
        // IB ID
        msgBuf.append(MAIL_IB_ID);
        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            msgBuf.append(svcMachMstrPk.toString());
        }
        msgBuf.append(LINE_FEED_CODE);
        // END 2023/01/06 E.Sanchez [QC#60807, ADD]
        // Serial Number
        msgBuf.append(MAIL_SERIAL_NUM + getMachSerNum(svcMachMstrPk));
        msgBuf.append(LINE_FEED_CODE);
        // Base/Overage
        msgBuf.append(MAIL_BASE_OVERAGE);
        if (ZYPCommonFunc.hasValue(dsContrBaseUsgNm)) {
            msgBuf.append(dsContrBaseUsgNm);
        }
        msgBuf.append(LINE_FEED_CODE);
        // Policy Date
        msgBuf.append(MAIL_POLICY_DATE + effFromDt);
        msgBuf.append(LINE_FEED_CODE);

        return msgBuf.toString();
    }

    /**
     * getMachSerNum
     * @param svcMachMstrPk BigDecimal
     * @return String
     */
    @SuppressWarnings("unchecked")
    private String getMachSerNum(BigDecimal svcMachMstrPk) {

        if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return EMPTY_STRING;
        }

        List<String> rsltList = new ArrayList<String>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        rsltList = (List<String>) ssmBatchClient.queryObjectList("getMachSerNum", paramMap);

        if (rsltList != null && !rsltList.isEmpty()) {
            return rsltList.get(0);
        }

        return EMPTY_STRING;
    }

    /**
     * sendPolicyDateErrMail
     * @param pcyDtErrList List<String>
     */
    private void sendPolicyDateErrMail(List<String> pcyDtErrList) {
        String errDate = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            return;
        }

        // 2. Get To Address
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            return;
        }

        // 3. Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            return;
        }

        // 4. Create message for Body
        S21MailAddress fromAddress = addrFromList.get(0);
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        StringBuilder msgBuf = new StringBuilder();
        for (String pcyDtErrDetail : pcyDtErrList) {
            msgBuf.append(pcyDtErrDetail);
            msgBuf.append(LINE_FEED_CODE);
        }
        String message = msgBuf.toString();

        // 5. Create Subject and Body
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(addrToList);
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, BIZ_APP_ID);
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, errDate);
        template.setTemplateParameter(MAIL_ITEM_ERR_MSG,  message);

        // 6. Call Mail API
        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }
    // END 2022/12/05 E.Sanchez [QC#60807, ADD]
}
