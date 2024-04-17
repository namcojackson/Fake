/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB016001;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import business.db.MTR_EST_SELTMsg;
import business.db.SVC_ADCV_BY_MDLTMsg;
import business.db.SVC_ADCV_BY_SERTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;
import business.parts.NSZC010001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CNTR_RESET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_EST_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_EST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * Auto Estimation.
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 * 2016/02/02   Hitachi         K.Kishimoto     Update          QC#3791
 * 2017/03/13   Hitachi         K.Kitachi       Update          QC#15163
 * 2017/04/27   Hitachi         K.Yamada        Update          CSA QC#18465
 * 2017/06/13   Hitachi         Y.Osawa         Update          QC#18561
 * 2017/09/06   Hitachi         T.Kanasaka      Update          QC#15134
 * 2017/09/27   Hitachi         T.Kanasaka      Update          QC#21450
 * 2018/06/29   Hitachi         K.Kim           Update          QC#23311
 * 2018/07/11   Hitachi         K.Kojima        Update          QC#23925
 * 2018/07/23   Hitachi         K.Kitachi       Update          QC#23586
 * 2018/08/27   Hitachi         A.Kohinata      Update          QC#27553
 * 2018/05/27   Hitachi         A.Kohinata      Update          QC#50518
 * 2019/11/08   Hitachi         K.Kim           Update          QC#54306
 * 2020/01/08   Hitachi         Y.Takeno        Update          QC#55302
 * 2020/01/28   Hitachi         A.Kohinata      Update          QC#55495
 * 2020/09/04   Hitachi         K.Kitachi       Update          QC#57649
 * 2021/02/04   CITS            R.Cabral        Update          QC#58365
 * 2021/02/10   CITS            R.Cabral        Update          QC#58365
 * 2021/02/24   CITS            R.Cabral        Update          QC#58365
 * 2021/07/15   CITS            R.Cabral        Update          QC#58887
 * 2021/07/23   CITS            R.Cabral        Update          QC#58531
 * 2022/02/21   CITS            R.Cabral        Update          QC#59741
 * 2022/08/12   Hitachi         N.Takatsu       Update          QC#60186
 * 2023/02/03   CITS            E.Sanchez       Update          QC#61138
 * </pre>
 */
public class NSAB016001 extends S21BatchMain {

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * Parameter "@" is not set.
     */
    public static final String NSAM0131E = "NSAM0131E";

    /**
     * Hard counter information has not been set up. Serial#[@]
     */
    private static final String NSAM0361E = "NSAM0361E";

    //Add Start 02/02/2016 <QC#3791>
    /**
     * Failed to get the start meter read count.Serial#[@]
     */
    private static final String NSAM0423E = "NSAM0423E";

    /**
     * Failed to get the average meter read count.Serial#[@]
     */
    private static final String NSAM0424E = "NSAM0424E";
    //Add End   02/02/2016 <QC#3791>

    // add start 2019/05/27 QC#50518
    /**
     * The process could not estimate Meter Read because of the occurrence of Meter Rollover. Please register it from Meter Entry.Serial#[@]
     */
    private static final String NSAM0748E = "NSAM0748E";
    // add end 2019/05/27 QC#50518

    // START 2021/07/15 R.Cabral [QC#58887,ADD]
    /**
     * The Process could not estimate Meter Read because of the occurrence of negative ADCV value. Please register it from Meter Entry. Serial#[@]
     */
    private static final String NSAM0757E = "NSAM0757E";
    // END 2021/07/15 R.Cabral [QC#58887,ADD]

    // START 2018/07/23 K.Kitachi [QC#23586, ADD]
    /** int: 4 */
    private static final int INT_4 = 4;

    /** int: 6 */
    private static final int INT_6 = 6;
    // END 2018/07/23 K.Kitachi [QC#23586, ADD]

    /**
     * Low level coding client
     */
    private S21SsmLowLevelCodingClient ssmLlcClnt = null;

    /**
     * Batch client
     */
    private S21SsmBatchClient ssmBatClnt = null;

    /**
     * Termination code
     */
    private TERM_CD termCd = null;

    /**
     * Global company code
     */
    private String glblCmpyCd = null;

    /**
     * Normal record count
     */
    private int normRecCnt = 0;

    /**
     * Error record count
     */
    private int errRecCnt = 0;

    /**
     * Error message list
     */
    private List<String> errMsgList;

    /**
     * Error TMsg list
     */
    private List<EZDTMsg> errTMsgList;

    /**
     * System time stamp
     */
    private String sysTs = null;

    /**
     * Sales date
     */
    private String slsDt = null;

    /**
     * Meter Estimation Selection Group Number
     */
    private BigDecimal mtrEstSelGrpNum = null;

    // START 2020/09/04 K.Kitachi [QC#57649, ADD]
    /**
     * Meter Read Type Group List
     */
    private List<String> dsMtrReadTpGrpCdList;
    // END 2020/09/04 K.Kitachi [QC#57649, ADD]

    // START 2021/07/23 R.Cabral [QC#58531,ADD]
    /**
     * Contract Status Code List
     */
    private static final String [] DS_CONTR_CTRL_STS_CD_LIST = {
        DS_CONTR_CTRL_STS.DRAFT,
        DS_CONTR_CTRL_STS.ENTERED,
        DS_CONTR_CTRL_STS.SINGED,
        DS_CONTR_CTRL_STS.TERMINATED,
        DS_CONTR_CTRL_STS.EXPIRED,
        DS_CONTR_CTRL_STS.CANCELLED
    };
    // END 2021/07/23 R.Cabral [QC#58531,ADD]

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        new NSAB016001().executeBatch(NSAB016001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        ssmLlcClnt = S21SsmLowLevelCodingClient.getClient(NSAB016001.class);
        ssmBatClnt = S21SsmBatchClient.getClient(NSAB016001.class);
        termCd = TERM_CD.NORMAL_END;
        glblCmpyCd = getGlobalCompanyCode();
        String usrVal1 = getUserVariable1();
        if (ZYPCommonFunc.hasValue(usrVal1)) {
            mtrEstSelGrpNum = new BigDecimal(usrVal1);
        } else {
            throw new S21AbendException(NSAM0131E, new String[] {"VAR_USER1" });
        }
        errMsgList = new ArrayList<String>();
        errTMsgList = new ArrayList<EZDTMsg>();
        sysTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, NSAB016001.class.getSimpleName());
        // START 2020/09/04 K.Kitachi [QC#57649, ADD]
        dsMtrReadTpGrpCdList = new ArrayList<String>();
        dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
        dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
        // END 2020/09/04 K.Kitachi [QC#57649, ADD]
    }

    @Override
    protected void mainRoutine() {

        estMtrCnt();

        cratErrLog();
    }

    @Override
    protected void termRoutine() {
        sendEmail();
        setTermState(termCd, normRecCnt, errRecCnt);
    }

    private void estMtrCnt() {
        int normRecCnt = 0;
        int errRecCnt = 0;
        PreparedStatement prepStmt = null;
        ResultSet rsltSet = null;
        try {
            prepStmt = getPrepStmt();
            rsltSet = prepStmt.executeQuery();
            while (rsltSet.next()) {

                BigDecimal mtrEstSelPk = rsltSet.getBigDecimal("MTR_EST_SEL_PK");
                BigDecimal svcMachMstrPk = rsltSet.getBigDecimal("SVC_MACH_MSTR_PK");
                String serNum = rsltSet.getString("SER_NUM");
                BigDecimal dsContrPk = rsltSet.getBigDecimal("DS_CONTR_PK");
                BigDecimal dsContrDtlPk = rsltSet.getBigDecimal("DS_CONTR_DTL_PK");
                String dsContrNum = rsltSet.getString("DS_CONTR_NUM");
                List<Map<String, Object>> svcPhysMtrList = getSvcPhysMtrInfo(svcMachMstrPk);
                if (svcPhysMtrList == null || svcPhysMtrList.isEmpty()) {
                    // START 2020/01/08 [QC#55302, MOD]
                    // normRecCnt++;
                    errRecCnt++;
                    // END   2020/01/08 [QC#55302, MOD]
                    addMsg(mtrEstSelPk, dsContrNum, dsContrPk, dsContrDtlPk, svcMachMstrPk, null, NSAM0361E, serNum);
                    // START 2020/01/08 [QC#55302, ADD]
                    updateMtrEstSts(mtrEstSelPk, MTR_EST_STS.ERROR);
                    commit();
                    // END   2020/01/08 [QC#55302, ADD]
                    continue;
                }

                boolean skipThisSerNum = false;
                
                //Add Start 02/02/2016 <QC#3791>
                // START 2018/07/23 K.Kitachi [QC#23586, MOD]
//                List <String> bllgThruDtList = getBllgThruDtList(rsltSet.getBigDecimal("DS_CONTR_DTL_PK"));
                // START 2021/07/23 R.Cabral [QC#58531,MOD]
                // List<String> bllgThruDtList = getBllgThruDtList(rsltSet.getBigDecimal("DS_CONTR_DTL_PK"), rsltSet.getString("MTR_EST_TP_CD"));
                List<String> bllgThruDtList = getBllgThruDtList(svcMachMstrPk, rsltSet.getString("MTR_EST_TP_CD"));
                // END 2021/07/23 R.Cabral [QC#58531,MOD]
                // END 2018/07/23 K.Kitachi [QC#23586, MOD]
                //Add End   02/02/2016 <QC#3791>

                TreeMap<String, List<SvcPhysMtrReadInfoBean>> mtrReadMap = new TreeMap<String, List<SvcPhysMtrReadInfoBean>>();

                // START 2022/08/12 N.Takatsu[QC#60186, ADD]
                String commentTxt = null;
                // END 2022/08/12 N.Takatsu[QC#60186, ADD]
                SVC_PHYS_MTR: for (Map<String, Object> svcPhysMtrInfo : svcPhysMtrList) {
                    BigDecimal svcPhysMtrPk = (BigDecimal) svcPhysMtrInfo.get("SVC_PHYS_MTR_PK");
                    String mdlMtrLbCd = (String) svcPhysMtrInfo.get("MDL_MTR_LB_CD");
                    String mdlNm = (String) svcPhysMtrInfo.get("MDL_NM");
                    String istlDt = (String) svcPhysMtrInfo.get("ISTL_DT");
                    // TODO
                    String effThruDt = (String) svcPhysMtrInfo.get("EFF_THRU_DT");

                   //Mod Start 02/02/2016 <QC#3791>
                   // List<Map<String, Object>> bllgSchdList = getDsContrBllgSchd(svcPhysMtrPk);
                    for (String bllgThruDt : bllgThruDtList) {

                        boolean estByAdcv = false;

                        String bllgSchdThruDt = bllgThruDt;
                   //Mod End   02/02/2016 <QC#3791>
                        // del start 2017/04/27 CSA Defect#18465
                        //String nextBllgDt = (String) svcPhysMtrInfo.get("NEXT_BLLG_DT");
                        // del end 2017/04/27 CSA Defect#18465

                        SvcPhysMtrReadInfoBean mtrReadInfo1 = null;

                        // START 2018/06/29 K.Kim [QC#23311,MOD]
                        // Initial Read
                        // SvcPhysMtrReadInfoBean initMtrReadInfo = getInitMtrRead(svcPhysMtrPk, istlDt, effThruDt);
                        // START 2021/07/23 R.Cabral [QC#58531,MOD]
                        // SvcPhysMtrReadInfoBean initMtrReadInfo = getInitMtrRead(svcPhysMtrPk, dsContrDtlPk);
                        // START 2022/02/21 R.Cabral [QC#59741, MOD]
//                        SvcPhysMtrReadInfoBean initMtrReadInfo = getInitMtrRead(svcPhysMtrPk);
                        SvcPhysMtrReadInfoBean initMtrReadInfo = getInitMtrRead(svcPhysMtrPk, bllgSchdThruDt);
                        // END   2022/02/21 R.Cabral [QC#59741, MOD]
                        // END 2021/07/23 R.Cabral [QC#58531,MOD]
                        // END 2018/06/29 K.Kim [QC#23311,MOD]
                        if (initMtrReadInfo == null) {
                            // mod start 2020/01/28 QC#55495
                            //addMsg(mtrEstSelPk, dsContrNum, dsContrPk, dsContrDtlPk, svcMachMstrPk, mdlMtrLbCd, NSAM0423E, serNum);
                            //skipThisSerNum = true;
                            //break SVC_PHYS_MTR;
                            continue SVC_PHYS_MTR;
                            // mod end 2020/01/28 QC#55495
                        } else {
                            // 1st Read (newer read)
                            String initMtrReadDt = initMtrReadInfo.getMtrReadDt();
                            mtrReadInfo1 = getMtrRead1(svcPhysMtrPk, initMtrReadDt);
                            if (mtrReadInfo1 == null) {
                                estByAdcv = true;
                            } else {
                                // 2nd Read (older read)
                                String mtrReadDt1 = mtrReadInfo1.getMtrReadDt();
                                SvcPhysMtrReadInfoBean mtrReadInfo2 = getMtrRead2(svcPhysMtrPk, mtrReadDt1);
                                if (mtrReadInfo2 == null) {
                                    estByAdcv = true;
                                } else {
                                    // START 2021/07/15 R.Cabral [QC#58887,ADD]
                                    BigDecimal adcv = NSXC003001SvcPhysMtrRead.calcAvgDlyCopyVol(glblCmpyCd, Arrays.asList(mtrReadInfo2, mtrReadInfo1));
                                    if (ZYPCommonFunc.hasValue(adcv) && (adcv.compareTo(BigDecimal.ZERO) < 0)) {
                                        addMsg(mtrEstSelPk, dsContrNum, dsContrPk, dsContrDtlPk, svcMachMstrPk, null, NSAM0757E, serNum);
                                        skipThisSerNum = true;
                                        break SVC_PHYS_MTR;
                                    }
                                    // END 2021/07/15 R.Cabral [QC#58887,ADD]
                                    // START 2017/09/06 T.Kanasaka [QC#15134,MOD]
//                                    BigDecimal estMtrCnt = NSXC003001SvcPhysMtrRead.estMtrCnt(bllgSchdThruDt, mtrReadInfo2, mtrReadInfo1);
                                    BigDecimal estMtrCnt = NSXC003001SvcPhysMtrRead.estMtrCnt(glblCmpyCd, bllgSchdThruDt, mtrReadInfo2, mtrReadInfo1);
                                    // END 2017/09/06 T.Kanasaka [QC#15134,MOD]
                                    EZDDebugOutput.println(5, String.format("estimated: dt=%s estMtrCnt=%s prm=[%s, %s]", bllgSchdThruDt, estMtrCnt, mtrReadInfo2, mtrReadInfo1), NSAB016001.class);
                                    // START 2022/08/12 N.Takatsu[QC#60186, ADD]
                                    String newDt = ZYPDateUtil.formatEzd8ToDisp(mtrReadInfo1.getMtrReadDt());
                                    String oldDt = ZYPDateUtil.formatEzd8ToDisp(mtrReadInfo2.getMtrReadDt());
                                    commentTxt = String.format("[%s/%s to %s/%s]", newDt, mtrReadInfo1.getReadMtrCnt(), oldDt, mtrReadInfo2.getReadMtrCnt());
                                    // END 2022/08/12 N.Takatsu[QC#60186, ADD]
                                    // START 2021/02/10 R.Cabral [QC#58365,MOD]
                                    // addEstMtr(mtrReadMap, svcPhysMtrPk, bllgSchdThruDt, estMtrCnt);
                                    // START 2021/02/24 R. Cabral [QC#58365,MOD]
                                    // START 2021/07/15 R. Cabral [QC#58887,MOD]
                                    if (ZYPCommonFunc.hasValue(estMtrCnt) && estMtrCnt.compareTo(mtrReadInfo1.getReadMtrCnt()) >= 0) {
                                    // if (ZYPCommonFunc.hasValue(estMtrCnt) && (estMtrCnt.compareTo(mtrReadInfo1.getReadMtrCnt()) >= 0
                                    //         || mtrReadInfo1.getReadMtrCnt().compareTo(mtrReadInfo2.getReadMtrCnt()) < 0)) {
                                    // END 2021/07/15 R.Cabral [QC#58887,MOD]
                                    // END 2021/02/24 R.Cabral [QC#58365,MOD] 
                                        // START 2022/08/12 N.Takatsu[QC#60186, MOD]
                                        // addEstMtr(mtrReadMap, svcPhysMtrPk, bllgSchdThruDt, estMtrCnt);
                                        addEstMtr(mtrReadMap, svcPhysMtrPk, bllgSchdThruDt, estMtrCnt, commentTxt);
                                        // END 2022/08/12 N.Takatsu[QC#60186, MOD]
                                    } else {
                                         addMsg(mtrEstSelPk, dsContrNum, dsContrPk, dsContrDtlPk, svcMachMstrPk, null, NSAM0748E, serNum);
                                         skipThisSerNum = true;
                                         break SVC_PHYS_MTR;
                                    }
                                    // END 2021/02/10 R.Cabral [QC#58365,MOD]
                                }
                            }
                        }
                        if (estByAdcv) {
                            BigDecimal avgMtrReadCnt = getAdcvByMach(svcMachMstrPk, serNum, mdlMtrLbCd);
                            if (avgMtrReadCnt == null) {
                                avgMtrReadCnt = getAdcvByMdl(mdlNm, mdlMtrLbCd);
                            }
                            if (avgMtrReadCnt == null) {
                                // mod start 2020/01/28 QC#55495
                                //addMsg(mtrEstSelPk, dsContrNum, dsContrPk, dsContrDtlPk, svcMachMstrPk, mdlMtrLbCd, NSAM0424E, serNum);
                                //skipThisSerNum = true;
                                //break SVC_PHYS_MTR;
                                continue SVC_PHYS_MTR;
                               // mod end 2020/01/28 QC#55495
                            }
                            SvcPhysMtrReadInfoBean tmpMtrReadInfo = mtrReadInfo1;
                            if (tmpMtrReadInfo == null) {
                                tmpMtrReadInfo = getLtstMtrRead(svcPhysMtrPk, initMtrReadInfo.getSvcPhysMtrReadPk(), istlDt, effThruDt);
                            }
                            if (tmpMtrReadInfo == null) {
                                tmpMtrReadInfo = initMtrReadInfo;
                            }
                            if (tmpMtrReadInfo == null) {
                                // mod start 2020/01/28 QC#55495
                                //addMsg(mtrEstSelPk, dsContrNum, dsContrPk, dsContrDtlPk, svcMachMstrPk, mdlMtrLbCd, NSAM0423E, serNum);
                                //skipThisSerNum = true;
                                //break SVC_PHYS_MTR;
                                continue SVC_PHYS_MTR;
                                // mod end 2020/01/28 QC#55495
                            }
                            // START 2022/08/12 N.Takatsu[QC#60186, ADD]
                            String oldDt = ZYPDateUtil.formatEzd8ToDisp(tmpMtrReadInfo.getMtrReadDt());
                            commentTxt = String.format("[%s/%s Calculate By ADCV %s]", oldDt, tmpMtrReadInfo.getReadMtrCnt().toPlainString(), avgMtrReadCnt.toPlainString());
                            // END 2022/08/12 N.Takatsu[QC#60186, ADD]
                            // mod start 2017/04/27 CSA Defect#18465
                            int days = ZYPDateUtil.getDiffDays(bllgSchdThruDt, tmpMtrReadInfo.getMtrReadDt());
                            BigDecimal estMtrCnt = avgMtrReadCnt.multiply(BigDecimal.valueOf(days)).add(tmpMtrReadInfo.getReadMtrCnt()).setScale(0, RoundingMode.HALF_UP);

                            // START 2017/09/06 T.Kanasaka [QC#15134,ADD]
                            BigDecimal cntrDigitNum = (BigDecimal) svcPhysMtrInfo.get("CNTR_DIGIT_NUM");
                            if (ZYPCommonFunc.hasValue(cntrDigitNum)) {
                                BigDecimal powNum = BigDecimal.TEN.pow(cntrDigitNum.intValue());
                                if (powNum.compareTo(estMtrCnt) <= 0) {
                                    // START 2021/02/04 R.Cabral [QC#58365,MOD]
                                    // estMtrCnt = estMtrCnt.subtract(powNum);
                                    addMsg(mtrEstSelPk, dsContrNum, dsContrPk, dsContrDtlPk, svcMachMstrPk, null, NSAM0748E, serNum);
                                    skipThisSerNum = true;
                                    break SVC_PHYS_MTR;
                                    // END 2021/02/04 R.Cabral [QC#58365,MOD]
                                }
                            }
                            // END 2017/09/06 T.Kanasaka [QC#15134,ADD]

                            EZDDebugOutput.println(5, String.format("estimated: dt=%s estMtrCnt=%s prm=[%s]", bllgSchdThruDt, estMtrCnt, tmpMtrReadInfo), NSAB016001.class);
                            // START 2022/08/12 N.Takatsu[QC#60186, MOD] 
                            //addEstMtr(mtrReadMap, svcPhysMtrPk, bllgSchdThruDt, estMtrCnt);
                            addEstMtr(mtrReadMap, svcPhysMtrPk, bllgSchdThruDt, estMtrCnt, commentTxt);
                            // END 2022/08/12 N.Takatsu[QC#60186, MOD]
                            // mod end 2017/04/27 CSA Defect#18465
                        }
                    }
                }

                if (skipThisSerNum) {
                    errRecCnt++;
                    rollback();
                    // START 2019/11/08 [QC#54306, ADD]
                    updateMtrEstSts(mtrEstSelPk, MTR_EST_STS.ERROR);
                    commit();
                    // END 2019/11/08 [QC#54306, ADD]
                    continue;
                }


                // add start 2020/01/28 QC#55495
                if (mtrReadMap.isEmpty()) {
                    errRecCnt++;
                    rollback();
                    addMsg(mtrEstSelPk, dsContrNum, dsContrPk, dsContrDtlPk, svcMachMstrPk, null, NSAM0424E, serNum);
                    updateMtrEstSts(mtrEstSelPk, MTR_EST_STS.ERROR);
                    commit();
                    continue;
                }
                // add end 2020/01/28 QC#55495

                // assuming that mtrReadMap.values() are sorted in
                // chronological order
                List<List<SvcPhysMtrReadInfoBean>> mtrReadGrpList = new ArrayList<List<SvcPhysMtrReadInfoBean>>(mtrReadMap.values());

                for (List<SvcPhysMtrReadInfoBean> mtrReadGrp : mtrReadGrpList) {
                    NSZC010001PMsg pMsg = buildPMsg(svcMachMstrPk);
                    // START 2017/03/13 K.Kitachi [QC#15163, MOD]
//                    BigDecimal svcPhysMtrReadGrpSq = ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_PHYS_MTR_READ_GRP_SQ");
                    for (SvcPhysMtrReadInfoBean mtrReadInfo : mtrReadGrp) {
                     // START 2022/08/12 N.Takatsu [QC#60186, MOD]
                        // addEstMtr(pMsg, svcPhysMtrReadGrpSq, mtrReadInfo.getSvcPhysMtrPk(), mtrReadInfo.getMtrReadDt(), mtrReadInfo.getReadMtrCnt());
                        // addEstMtr(pMsg, mtrReadInfo.getSvcPhysMtrPk(), mtrReadInfo.getMtrReadDt(), mtrReadInfo.getReadMtrCnt());
                        addEstMtr(pMsg, mtrReadInfo.getSvcPhysMtrPk(), mtrReadInfo.getMtrReadDt(), mtrReadInfo.getReadMtrCnt(), mtrReadInfo.getMtrReadComment());
                     // END 2022/08/12 N.Takatsu [QC#60186, MOD]
                    }
                    // END 2017/03/13 K.Kitachi [QC#15163, MOD]
                    if (pMsg.A.getValidCount() > 0) {
                        new NSZC010001().execute(pMsg, ONBATCH_TYPE.BATCH);
                        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                        for (S21ApiMessage msg : msgList) {
                            String msgId = msg.getXxMsgid();
                            String[] prm = msg.getXxMsgPrmArray();
                            if (msgId.endsWith("E")) {
                                addMsg(mtrEstSelPk, dsContrNum, dsContrPk, dsContrDtlPk, svcMachMstrPk, null, msgId, prm);
                                skipThisSerNum = true;
                                break;
                            }
                        }
                        // add start 2019/05/27 QC#50518
                        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                            if (CNTR_RESET_TP.METER_ROLLOVER.equals(pMsg.A.no(i).cntrResetTpCd.getValue())) {
                                addMsg(mtrEstSelPk, dsContrNum, dsContrPk, dsContrDtlPk, svcMachMstrPk, null, NSAM0748E, serNum);
                                skipThisSerNum = true;
                                break;
                            }
                        }
                        // add end 2019/05/27 QC#50518
                    }

                }

                if (skipThisSerNum) {
                    errRecCnt++;
                    rollback();
                    // START 2019/11/08 [QC#54306, ADD]
                    updateMtrEstSts(mtrEstSelPk, MTR_EST_STS.ERROR);
                    commit();
                    // END 2019/11/08 [QC#54306, ADD]
                } else {
                    normRecCnt++;
                    // START 2019/11/08 [QC#54306, ADD]
                    updateMtrEstSts(mtrEstSelPk, MTR_EST_STS.CLOSE);
                    // END 2019/11/08 [QC#54306, ADD]
                    commit();
                }
            }
            this.normRecCnt = normRecCnt;
            this.errRecCnt = errRecCnt;
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prepStmt, rsltSet);
        }
    }

    private PreparedStatement getPrepStmt() throws SQLException {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setFetchSize(1000);
        execPrm.setMaxRows(0);
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("mtrEstSelGrpNum", mtrEstSelGrpNum);
        prm.put("mtrEstStsCd", MTR_EST_STS.ACTIVE);
        // START 2022/08/12 N.Takatsu [QC#60186, ADD]
        prm.put("endMonthTpCd", MTR_EST_TP.END_OF_CURRENT_MONTH);
        // END 2022/08/12 N.Takatsu [QC#60186, ADD]
        return ssmLlcClnt.createPreparedStatement("getMtrEstSel", prm, execPrm);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSvcPhysMtrInfo(BigDecimal svcMachMstrPk) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcMachMstrPk", svcMachMstrPk);
        return ssmBatClnt.queryObjectList("getSvcPhysMtrInfo", prm);
    }

    //Add Start 02/02/2016 <QC#3791>
    @SuppressWarnings("unchecked")
    // START 2018/07/23 K.Kitachi [QC#23586, MOD]
//    private List<String> getBllgThruDtList(BigDecimal dsContrDtlPk) {
    // START 2021/07/23 R.Cabral [QC#58531,MOD]
    // private List<String> getBllgThruDtList(BigDecimal dsContrDtlPk, String mtrEstTpCd)
    private List<String> getBllgThruDtList(BigDecimal svcMachMstrPk, String mtrEstTpCd) {
    // END 2021/07/23 R.Cabral [QC#58531,MOD]
    // END 2018/07/23 K.Kitachi [QC#23586, MOD]
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        // START 2021/07/23 R.Cabral [QC#58531,MOD]
        // prm.put("dsContrDtlPk", dsContrDtlPk);
        prm.put("svcMachMstrPk", svcMachMstrPk);
        prm.put("mtrEstAvalFlg", ZYPConstant.FLG_ON_Y);
        // END 2021/07/23 R.Cabral [QC#58531,MOD]
        // START 2018/07/23 K.Kitachi [QC#23586, MOD]
//        prm.put("slsDt", slsDt);
        // mod start 2018/08/27 QC#27553
        //String trgtDt = this.slsDt;
        //if (MTR_EST_TP._10TH_OF_FOLLOWING_MONTH.equals(mtrEstTpCd) || MTR_EST_TP._20TH_OF_FOLLOWING_MONTH.equals(mtrEstTpCd)) {
        //    trgtDt = getFirstMonth(trgtDt);
        //}
        //prm.put("slsDt", trgtDt);
        String trgtDt = getTargetDate(mtrEstTpCd);
        prm.put("trgtDt", trgtDt);
        prm.put("slsDt", this.slsDt);
        // mod end 2018/08/27 QC#27553
        // END 2018/07/23 K.Kitachi [QC#23586, MOD]
        // START 2023/02/03 E.Sanchez [QC#61138, ADD]
        prm.put("skipRecovTpCd", SKIP_RECOV_TP.SKIP);
        // END 2023/02/03 E.Sanchez [QC#61138, ADD]
        return ssmBatClnt.queryObjectList("getBllgThruDtList", prm);
    }
    //Add End   02/02/2016 <QC#3791>

    // START 2018/07/11 K.Kojima [QC#23925,DEL]
    // @SuppressWarnings("unchecked")
    // private List<Map<String, Object>> getDsContrBllgSchd(BigDecimal svcPhysMtrPk) {
    //     Map<String, Object> prm = new HashMap<String, Object>();
    //     prm.put("glblCmpyCd", glblCmpyCd);
    //     prm.put("svcPhysMtrPk", svcPhysMtrPk);
    //     prm.put("slsDt", slsDt);
    //     return ssmBatClnt.queryObjectList("getDsContrBllgSchd", prm);
    // }
    // END 2018/07/11 K.Kojima [QC#23925,DEL]

    // START 2018/06/29 K.Kim [QC#23311,MOD]
    @SuppressWarnings("unchecked")
    // private SvcPhysMtrReadInfoBean getInitMtrRead(BigDecimal svcPhysMtrPk, String fromDt, String thruDt) {
    // START 2021/07/23 R.Cabral [QC#58531,MOD]
    // private SvcPhysMtrReadInfoBean getInitMtrRead(BigDecimal svcPhysMtrPk, BigDecimal dsContrDtlPk) {
    // START 2022/02/21 R.Cabral [QC#59741, MOD]
//    private SvcPhysMtrReadInfoBean getInitMtrRead(BigDecimal svcPhysMtrPk) {
    private SvcPhysMtrReadInfoBean getInitMtrRead(BigDecimal svcPhysMtrPk, String bllgThruDt) {
    // END   2022/02/21 R.Cabral [QC#59741, MOD]
    // END 2021/07/23 R.Cabral [QC#58531,MOD]
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        // prm.put("fromDt", fromDt);
        // prm.put("thruDt", thruDt);
        // START 2021/07/23 R.Cabral [QC#58531,MOD]
        // prm.put("dsContrDtlPk", dsContrDtlPk);
        prm.put("bllblFlg", ZYPConstant.FLG_ON_Y);
        prm.put("dsContrCtrlStsCdList", DS_CONTR_CTRL_STS_CD_LIST);
        // END 2021/07/23 R.Cabral [QC#58531,MOD]
        // START 2022/02/21 R.Cabral [QC#59741, ADD]
        prm.put("bllgThruDt", bllgThruDt);
        // END   2022/02/21 R.Cabral [QC#59741, ADD]
        Map<String, Object> rsltMap = (Map<String, Object>) ssmBatClnt.queryObject("getInitMtrRead", prm);
        if (rsltMap == null) {
            return null;
        } else {
            return toBean(rsltMap);
        }
    }
    // END 2018/06/29 K.Kim [QC#23311,MOD]

    @SuppressWarnings("unchecked")
    private SvcPhysMtrReadInfoBean getMtrRead1(BigDecimal svcPhysMtrPk, String mtrReadDt) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("mtrReadDt", mtrReadDt);
        prm.put("intvlDays", "14");
        // START 2020/09/04 K.Kitachi [QC#57649, ADD]
        prm.put("dsMtrReadTpGrpCdList", this.dsMtrReadTpGrpCdList);
        // END 2020/09/04 K.Kitachi [QC#57649, ADD]
        Map<String, Object> rsltMap = (Map<String, Object>) ssmBatClnt.queryObject("getMtrRead1", prm);
        if (rsltMap == null) {
            return null;
        } else {
            return toBean(rsltMap);
        }
    }

    @SuppressWarnings("unchecked")
    private SvcPhysMtrReadInfoBean getMtrRead2(BigDecimal svcPhysMtrPk, String mtrReadDt) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("mtrReadDt", mtrReadDt);
        prm.put("intvlMth", 12);
        // START 2020/09/04 K.Kitachi [QC#57649, ADD]
        prm.put("dsMtrReadTpGrpCdList", this.dsMtrReadTpGrpCdList);
        // END 2020/09/04 K.Kitachi [QC#57649, ADD]
        Map<String, Object> rsltMap = (Map<String, Object>) ssmBatClnt.queryObject("getMtrRead2", prm);
        if (rsltMap == null) {
            return null;
        } else {
            return toBean(rsltMap);
        }
    }

    @SuppressWarnings("unchecked")
    private SvcPhysMtrReadInfoBean getLtstMtrRead(BigDecimal svcPhysMtrPk, BigDecimal svcPhysMtrReadPk, String fromDt, String thruDt) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("svcPhysMtrReadPk", svcPhysMtrReadPk);
        prm.put("fromDt", fromDt);
        prm.put("thruDt", thruDt);
        // START 2020/09/04 K.Kitachi [QC#57649, ADD]
        prm.put("dsMtrReadTpGrpCdList", this.dsMtrReadTpGrpCdList);
        // END 2020/09/04 K.Kitachi [QC#57649, ADD]
        Map<String, Object> rsltMap = (Map<String, Object>) ssmBatClnt.queryObject("getLtstMtrRead", prm);
        if (rsltMap == null) {
            return null;
        } else {
            return toBean(rsltMap);
        }
    }

    private BigDecimal getAdcvByMach(BigDecimal svcMachMstrPk, String serNum, String mtrLbCd) {
        SVC_ADCV_BY_SERTMsg prmTMsg = new SVC_ADCV_BY_SERTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(prmTMsg.serNum, serNum);
        ZYPEZDItemValueSetter.setValue(prmTMsg.mtrLbCd, mtrLbCd);
        SVC_ADCV_BY_SERTMsg tMsg = (SVC_ADCV_BY_SERTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
        if (tMsg == null) {
            return null;
        } else {
            return tMsg.avgMtrReadCnt.getValue();
        }
    }

    private BigDecimal getAdcvByMdl(String mdlNm, String mtrLbCd) {
        SVC_ADCV_BY_MDLTMsg prmTMsg = new SVC_ADCV_BY_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.mdlNm, mdlNm);
        ZYPEZDItemValueSetter.setValue(prmTMsg.mtrLbCd, mtrLbCd);
        SVC_ADCV_BY_MDLTMsg tMsg = (SVC_ADCV_BY_MDLTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
        if (tMsg == null) {
            return null;
        } else {
            return tMsg.avgMtrReadCnt.getValue();
        }
    }

    private static SvcPhysMtrReadInfoBean toBean(Map<String, Object> rsltMap) {
        SvcPhysMtrReadInfoBean bean = new SvcPhysMtrReadInfoBean();
        bean.setMtrReadDt((String) rsltMap.get("MTR_READ_DT"));
        bean.setReadMtrCnt((BigDecimal) rsltMap.get("READ_MTR_CNT"));
        bean.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
        bean.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        return bean;
    }

    private NSZC010001PMsg buildPMsg(BigDecimal svcMachMstrPk) {
        NSZC010001PMsg pMsg = new NSZC010001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
        //Mod Start 02/02/2016 <QC#3791>
//        ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.PERIODIC_METER_READING);
        // START 2017/06/13 [QC#18561, MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
        ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ESTIMATED);
        // END   2017/06/13 [QC#18561, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);
        //Mod END 02/02/2016 <QC#3791>
        // START 2017/09/27 T.Kanasaka [QC#21450,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        // END 2017/09/27 T.Kanasaka [QC#21450,ADD]
        pMsg.fsrNum.clear();
        pMsg.fsrVisitNum.clear();
        return pMsg;
    }

    // START 2022/08/12 N.Takatsu[QC#60186, MOD]
    // private void addEstMtr(TreeMap<String, List<SvcPhysMtrReadInfoBean>> mtrReadMap, BigDecimal svcPhysMtrPk, String mtrReadDt, BigDecimal readMtrCnt) {
    private void addEstMtr(TreeMap<String, List<SvcPhysMtrReadInfoBean>> mtrReadMap, BigDecimal svcPhysMtrPk, String mtrReadDt, BigDecimal readMtrCnt, String commentTxt) {
        List<SvcPhysMtrReadInfoBean> mtrReadGrp = null;
        if (mtrReadMap.containsKey(mtrReadDt)) {
            mtrReadGrp = mtrReadMap.get(mtrReadDt);
        } else {
            mtrReadGrp = new ArrayList<SvcPhysMtrReadInfoBean>();
            mtrReadMap.put(mtrReadDt, mtrReadGrp);
        }
        SvcPhysMtrReadInfoBean mtrReadInfo = new SvcPhysMtrReadInfoBean();
        mtrReadInfo.setSvcPhysMtrPk(svcPhysMtrPk);
        mtrReadInfo.setMtrReadDt(mtrReadDt);
        mtrReadInfo.setReadMtrCnt(readMtrCnt);
        mtrReadInfo.setMtrReadComment(commentTxt);
        mtrReadGrp.add(mtrReadInfo);
    }
    // END 2022/08/12 N.Takatsu[QC#60186, MOD]

    // START 2022/08/12 N.Takatsu [QC#60186, MOD]
    // START 2017/03/13 K.Kitachi [QC#15163, MOD]
//    private void addEstMtr(NSZC010001PMsg pMsg, BigDecimal svcPhysMtrReadGrpSq, BigDecimal svcPhysMtrPk, String mtrReadDt, BigDecimal readMtrCnt) {
    // private void addEstMtr(NSZC010001PMsg pMsg, BigDecimal svcPhysMtrPk, String mtrReadDt, BigDecimal readMtrCnt) {
    private void addEstMtr(NSZC010001PMsg pMsg, BigDecimal svcPhysMtrPk, String mtrReadDt, BigDecimal readMtrCnt, String mtrEntryCmntTxt) {
        int i = pMsg.A.getValidCount();
        pMsg.A.no(i).physMtrId.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).mtrReadDt, mtrReadDt);
        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).rgtnMtrDt, slsDt);
        pMsg.A.no(i).svcPhysMtrReadPk.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).readMtrCnt, readMtrCnt);
        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).testMtrCnt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).rgtnUsrId, NSAB016001.class.getSimpleName());
        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).estFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).invProcFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).svcPhysMtrPk, svcPhysMtrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).mtrEntryCmntTxt, mtrEntryCmntTxt);
        // pMsg.A.no(i).mtrEntryCmntTxt.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_ON_Y);
        pMsg.A.setValidCount(i + 1);
    }
    // END 2017/03/13 K.Kitachi [QC#15163, MOD]
    // END 2022/08/12 N.Takatsu [QC#60186, MOD]

    private void addMsg(BigDecimal mtrEstSelPk, String dsContrNum, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk, String mtrLbCd, String errCd, String... prm) {
        // Message
        if (!ZYPCommonFunc.hasValue(errCd)) {
            return;
        }
        String errMsg = S21MessageFunc.clspGetMessage(errCd, prm);
        errMsgList.add(errMsg);

        // Log
        SVC_BAT_ERR_LOGTMsg tMsg = new SVC_BAT_ERR_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        ZYPEZDItemValueSetter.setValue(tMsg.bizAppId, "NSAB0160");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogTs, sysTs);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_01, "MTR_EST_SEL_PK");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_01, numToStr(mtrEstSelPk));
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_02, "SVC_MACH_MSTR_PK");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_02, numToStr(svcMachMstrPk));
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_03, "DS_CONTR_PK");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_03, numToStr(dsContrPk));
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_04, "DS_CONTR_DTL_PK");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_04, numToStr(dsContrDtlPk));
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_05, "DS_CONTR_NUM");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_05, dsContrNum);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_06, "MTR_LB_CD");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_06, mtrLbCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrMsgTxt, errMsg);
        errTMsgList.add(tMsg);
    }

    private boolean hasMsg() {
        return errMsgList.size() > 0;
    }

    private void sendEmail() {
        if (hasMsg()) {
            NSXC001001SendMailForErrorInfo mailer = new NSXC001001SendMailForErrorInfo();
            mailer.addErrMsgList(errMsgList);
            String rtnCd = mailer.sendMail(glblCmpyCd, NSAB016001.class.getSimpleName());
            if (ZYPCommonFunc.hasValue(rtnCd)) {
                S21InfoLogOutput.println(rtnCd);
            }
        }
    }

    private void cratErrLog() {
        if (hasMsg()) {
            int cnt = S21FastTBLAccessor.insert(errTMsgList.toArray(new SVC_BAT_ERR_LOGTMsg[errTMsgList.size()]));
            if (errTMsgList.size() != cnt) {
                S21InfoLogOutput.println(NSAM0032E, new String[] {"SVC_BAT_ERR_LOG" });
            }
        }
    }

    private static String numToStr(BigDecimal num) {
        if (ZYPCommonFunc.hasValue(num)) {
            return num.toPlainString();
        } else {
            return null;
        }
    }

    // del start 2018/08/27 QC#27553
    // START 2018/07/23 K.Kitachi [QC#23586, ADD]
//    private String getFirstMonth(String date) {
//        String year = date.substring(0, INT_4);
//        String month = date.substring(INT_4, INT_6);
//
//        Calendar firstCal = Calendar.getInstance();
//        firstCal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
//
//        SimpleDateFormat sf = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
//        return sf.format(firstCal.getTimeInMillis());
//    }
    // END 2018/07/23 K.Kitachi [QC#23586, ADD]
    // del end 2018/08/27 QC#27553

    // add start 2018/08/27 QC#27553
    private String getTargetDate(String mtrEstTpCd) {
        if (!ZYPCommonFunc.hasValue(mtrEstTpCd) || (!MTR_EST_TP.END_OF_CURRENT_MONTH.equals(mtrEstTpCd) && !MTR_EST_TP._10TH_OF_FOLLOWING_MONTH.equals(mtrEstTpCd) && !MTR_EST_TP._20TH_OF_FOLLOWING_MONTH.equals(mtrEstTpCd))) {
            return this.slsDt;
        }

        String year = this.slsDt.substring(0, INT_4);
        String month = this.slsDt.substring(INT_4, INT_6);

        Calendar cal = Calendar.getInstance();
        // first day of current month
        cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
        if (MTR_EST_TP.END_OF_CURRENT_MONTH.equals(mtrEstTpCd)) {
            // first day of next month
            cal.add(Calendar.MONTH, 1);
        }
        cal.add(Calendar.DAY_OF_MONTH, -1);

        SimpleDateFormat sf = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
        return sf.format(cal.getTimeInMillis());
    }
    // add end 2018/08/27 QC#27553
    // START 2019/11/08 [QC#54306, ADD]
    private void updateMtrEstSts(BigDecimal mtrEstSelPk, String mtrEstStsCd) {
        MTR_EST_SELTMsg tMsg = getMtrEstSel(mtrEstSelPk);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrEstStsCd, mtrEstStsCd);
        S21FastTBLAccessor.update(tMsg);
    }

    private MTR_EST_SELTMsg getMtrEstSel(BigDecimal mtrEstSelPk) {
        MTR_EST_SELTMsg tMsg = new MTR_EST_SELTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrEstSelPk, mtrEstSelPk);
        return (MTR_EST_SELTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
    }
    // END 2019/11/08 [QC#54306, ADD]
}
