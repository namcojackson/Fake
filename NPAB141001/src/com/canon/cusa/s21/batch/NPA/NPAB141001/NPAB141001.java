/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB141001;

import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.ASN_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.ASN_SO_SLP_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.BR_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.COMMA;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.DATE_TIME_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.DATE_TIME_FORMAT_14;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.DATE_TIME_PATTERN_FOR_MAIL;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.DB_PARAM_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.DB_PARAM_PURGE_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.DB_PARAM_RPT_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.DB_PARAM_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.DEST_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.EDI_ASN_HDR_WRK;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.EDI_ASN_SER_NUM_WRK;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.ELAN_RPT_PRINT_RQST_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.ELAN_RPT_WRK_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.ERROR_MESSAGE;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.INTL_LANG_VAL_COL_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.INTNT_CONN_SW_CTRL_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.MAIL_SPACE;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.NPAF0060_DIRECT_PRINT;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.NPAF0060_PURGE_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.REPORT_TITLE;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.RPT_ITEM_CTRL;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.RPT_PRINT_CTRL;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SHIP_TO_CTY_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SHIP_TO_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SHIP_TO_FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SHIP_TO_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SHIP_TO_POST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SHIP_TO_SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SHIP_TO_ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SHIP_TO_THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SPACE;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.SW_LIC_CTRL_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.NPAF0060_SENDMAIL_LIMIT_DAYS;
import static com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant.EZINTIME;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;
import business.db.EDI_ASN_HDR_WRKTMsg;
import business.db.ELAN_RPT_WRKTMsg;

import com.canon.cusa.s21.batch.NPA.NPAB141001.constant.NPAB141001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASN_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ELAN_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SW_LIC_CTRL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
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
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21PrinterOutputParameter;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NPAB1410:ELAN Printing Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/08/2016   CITS            M.Naito         Create          N/A
 * 10/23/2018   CITS            T.Wada          Update          QC#28852
 *</pre>
 */
public class NPAB141001 extends S21BatchMain {
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** Purge Date */
    private BigDecimal purgeDt = null;

    // QC#28852 Add
    private BigDecimal sendMailLimitDays = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** commit count */
    private int totalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** S21EIPPrintingService */
    private S21EIPPrintingService service = new S21EIPPrintingService();

    /** Printing Service count */
    private int printCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** message list */
    private List<String> msgList = new ArrayList<String>();

    /** message Set for rptId */
    private Set<String> setRptId = new HashSet<String>();

    /** message Set for rptBrNum */
    private Set<String> setRptBrNum = new HashSet<String>();

    /** message Set for serNum */
    private Set<String> setSerNum = new HashSet<String>();

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NPAB141001().executeBatch(NPAB141001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        profileService = S21UserProfileServiceFactory.getInstance().getService();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NPAB141001Constant.NPAM1479E);
        }

        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPAB141001Constant.NPAM1480E);
        }

        purgeDt = ZYPCodeDataUtil.getNumConstValue(NPAF0060_PURGE_DT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(purgeDt)) {
            throw new S21AbendException(NPAB141001Constant.NPAM0078E, new String[] {NPAF0060_PURGE_DT});
        }
        // QC#28852 Add Start
        // NUMBER OF DAYS TO KEEP SENDING ERROR MAIL
        sendMailLimitDays = ZYPCodeDataUtil.getNumConstValue(NPAF0060_SENDMAIL_LIMIT_DAYS, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(sendMailLimitDays)) {
            throw new S21AbendException(NPAB141001Constant.NPAM0078E, new String[] {NPAF0060_SENDMAIL_LIMIT_DAYS});
        }
    }

    /**
     * Main Process.
     * @param rs ResultSet
     */
    protected void mainRoutine() {

        // Remove Target Date Recored
        removeElanRptWrkRec();
        commit();

        // Get Main Data For ELAN_RPT_WRK
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB141001Constant.DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(NPAB141001Constant.DB_PARAM_EDI_PROC_STS_CD, ASN_EDI_PROC_STS.PROCESSED);
            paramMap.put(NPAB141001Constant.DB_PARAM_INITIAL, ELAN_PRINT_STS.INITIAL);
            paramMap.put(NPAB141001Constant.DB_PARAM_PRINT_ERROR, ELAN_PRINT_STS.PRINT_ERROR);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getAsnInfo", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            String poOrdNum = "";
            String asnSoNum = "";
            String mdseCd = "";
            String rtlWhCd = "";
            String serNum = "";
            String asnSoSlpNum = "";
            String swLicCtrl = "";
            String intntConnFlg = "";
            BigDecimal elanRptWrkSq = null;
            BigDecimal elanRptPrntRqstSq = null;
            int procCnt = 0;
            int totalProcCnt = 0;

            // QC#28852
            String ezintime = "";

            List<ELAN_RPT_WRKTMsg> tMsgList = new ArrayList<ELAN_RPT_WRKTMsg>();

            String rptId = null;
            String rptBrNum = null;
            boolean dropShipFlg = false;
            boolean soErrorFlg = false;
            boolean chkSerNumFlg = false;
            boolean printFlg = false;

            // Loop for Main data
            while (rs.next()) {
                totalCount++;

                if (!poOrdNum.equals(rs.getString(PO_ORD_NUM))) {
                    if (totalCount != 1) {
                        if (!chkSerNumFlg && !soErrorFlg && printFlg && !dropShipFlg) {
                            // Insert ELAN_RPT_WRK
                            totalProcCnt += procCnt;
                            insertElanRptWrkRec(tMsgList, totalProcCnt);
                        } else {
                            // update ELAN_PRINT_STS_CD
                            
                            // QC#28852 Mod Start
                            //updateElnPrntStsBySoNum(dropShipFlg, printFlg, soErrorFlg, chkSerNumFlg, asnSoNum);
                            updateElnPrntStsBySoNum(dropShipFlg, printFlg, soErrorFlg, chkSerNumFlg, asnSoNum, ezintime);
                            // QC#28852 Mod Start
                        }

                        if (totalProcCnt != 0) {
                            // Call EIP Common function
                            if (writeReport(poOrdNum, elanRptPrntRqstSq, rptId, rptBrNum, totalProcCnt)) {
                                //Update ELAN_PRINT_STS_CD
                                updateElnPrntSts(asnSoNum, ELAN_PRINT_STS.PRINTED_REPORT);
                            } else {
                                rollback();
                            }
                            commit();
                        }

                        tMsgList.clear();
                        soErrorFlg = false;
                        chkSerNumFlg = false;
                        printFlg = false;
                        dropShipFlg = false;
                        elanRptPrntRqstSq = null;

                        totalProcCnt = 0;
                        procCnt = 0;
                    }
                } else {
                    if (!asnSoNum.equals(rs.getString(ASN_SO_NUM))) {
                        if (!chkSerNumFlg && !soErrorFlg && printFlg && !dropShipFlg) {
                            // Insert ELAN_RPT_WRK
                            totalProcCnt += procCnt;
                            insertElanRptWrkRec(tMsgList, totalProcCnt);
                        } else {
                            // update ELAN_PRINT_STS_CD
                            // QC#28852 Mod Start
//                            updateElnPrntStsBySoNum(dropShipFlg, printFlg, soErrorFlg, chkSerNumFlg, asnSoNum);
                            updateElnPrntStsBySoNum(dropShipFlg, printFlg, soErrorFlg, chkSerNumFlg, asnSoNum, ezintime);
                            // QC#28852 Mod End
                        }
                        procCnt = 0;
                        tMsgList.clear();
                        soErrorFlg = false;
                        chkSerNumFlg = false;
                        printFlg = false;
                        dropShipFlg = false;
                    }
                }

                poOrdNum = rs.getString(PO_ORD_NUM);
                asnSoNum = rs.getString(ASN_SO_NUM);
                mdseCd = rs.getString(MDSE_CD);
                serNum = rs.getString(SER_NUM);
                asnSoSlpNum = rs.getString(ASN_SO_SLP_NUM);
                rtlWhCd = rs.getString(DEST_RTL_WH_CD);
                intntConnFlg = rs.getString(INTNT_CONN_SW_CTRL_FLG);
                // QC#28852 Add Start
                ezintime = rs.getString(EZINTIME);

                // Check DropShip Data
                if (ZYPConstant.FLG_ON_Y.equals(rs.getString(BR_FLG))) {
                    dropShipFlg = true;
                    continue;
                }
                // Check License Control ELAN Contract
                swLicCtrl = rs.getString(SW_LIC_CTRL_TP_CD);
                if (((SW_LIC_CTRL_TP.YES_ITEM).equals(swLicCtrl) || SW_LIC_CTRL_TP.YEA_SEAT.equals(swLicCtrl)) || ZYPConstant.FLG_ON_Y.equals(intntConnFlg)) {
                    printFlg = true;
                } else {
                    continue;
                }

                // Check WARNING/ERROR
                // SER_NUM
                if (!ZYPCommonFunc.hasValue(serNum)) {
                    chkSerNumFlg = true;
                    //Set ErrorMail
                    if (!setSerNum.contains(poOrdNum + mdseCd + asnSoNum + asnSoSlpNum)) {
                        setSerNum.add(poOrdNum + mdseCd + asnSoNum + asnSoSlpNum);
                        setErrorInfo(poOrdNum, mdseCd, NPAB141001Constant.NPAM1593E, new String[] {asnSoNum, asnSoSlpNum, EDI_ASN_SER_NUM_WRK});
                    }
                }
                // Report Item Control
                rptId = getRptId(poOrdNum, asnSoNum, mdseCd);
                if (!ZYPCommonFunc.hasValue(rptId)) {
                    soErrorFlg = true;
                }
                //RPT_BR_ID
                if (ZYPCommonFunc.hasValue(rptId)) {
                    rptBrNum = getRptBrNum(poOrdNum, mdseCd, rptId, rtlWhCd);
                    if (!ZYPCommonFunc.hasValue(rptBrNum)) {
                        soErrorFlg = true;
                    }
                }
                if (!chkSerNumFlg && !soErrorFlg) {
                    //Get SQ
                    elanRptWrkSq =  ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ELAN_RPT_WRK_SQ);
                    if (!ZYPCommonFunc.hasValue(elanRptPrntRqstSq)) {
                        elanRptPrntRqstSq = elanRptWrkSq;
                    }
                    // Map Result Set Data To ELAN_RPT_WRK
                    tMsgList.add(setReportData(rs, elanRptWrkSq, elanRptPrntRqstSq));
                    procCnt++;
                } else {
                    errorCount++;
                }
            }

            if (totalCount != 0) {
                if (!chkSerNumFlg && !soErrorFlg && printFlg && !dropShipFlg) {
                    // Insert ELAN_RPT_WRK
                    totalProcCnt += procCnt;
                    insertElanRptWrkRec(tMsgList, totalProcCnt);
                } else {
                    // update ELAN_PRINT_STS_CD
                    // QC#28852 Mod Start
//                    updateElnPrntStsBySoNum(dropShipFlg, printFlg, soErrorFlg, chkSerNumFlg, asnSoNum);
                    updateElnPrntStsBySoNum(dropShipFlg, printFlg, soErrorFlg, chkSerNumFlg, asnSoNum, ezintime);
                    // QC#28852 Mod End
                }
                if (totalProcCnt != 0) {
                    // Call EIP Common function
                    if (writeReport(poOrdNum, elanRptPrntRqstSq, rptId, rptBrNum, totalProcCnt)) {
                        //Update ELAN_PRINT_STS_CD
                        updateElnPrntSts(asnSoNum, ELAN_PRINT_STS.PRINTED_REPORT);
                    } else {
                        rollback();
                    }
                    commit();
                }
            }
            commit();

        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);

        }
    }

    @Override
    protected void termRoutine() {
        if (printCount > 0) {
            // If success, activate Create Report Processing.
            this.service.activateAsyncReportJob();
        }
        // Set EndCode and CommitCount
        if (0 < errorCount) {
            termCd = TERM_CD.WARNING_END;
        }
        sendEmail();
        setTermState(termCd, totalCount - errorCount, errorCount, totalCount);
    }

    /**
     * insertElanRptWrkRec
     * @param tMsgList List<ELAN_RPT_WRKTMsg>
     * @param totalProcCnt int
     * @param serNum String
     */
    private void insertElanRptWrkRec(List<ELAN_RPT_WRKTMsg> tMsgList, int totalProcCnt) {

        for (ELAN_RPT_WRKTMsg data : tMsgList) {
            //Set TOT_CNT_CERT_TXT
            ZYPEZDItemValueSetter.setValue(data.totCntCertTxt, String.valueOf(totalProcCnt));
            // Insert ELAN_RPT_WRK
            EZDTBLAccessor.insert(data);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(data.getReturnCode())) {
                S21InfoLogOutput.println(NPAB141001Constant.NPAM1172E, new String[] {data.getTableName()});
            }
        }
    }

    /**
     * setReportData
     * @param rs ResultSet
     * @param elanRptWrkPk BigDecimal
     * @param prntSq BigDecimal
     * @return ELAN_RPT_WRKTMsg
     */
    private ELAN_RPT_WRKTMsg setReportData(ResultSet rs, BigDecimal elanRptWrkPk, BigDecimal prntSq) throws SQLException {

        ELAN_RPT_WRKTMsg tMsg = new ELAN_RPT_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.elanRptWrkPk, elanRptWrkPk);
        ZYPEZDItemValueSetter.setValue(tMsg.poOrdNum, rs.getString(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescShortTxt, rs.getString(MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(SER_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, rs.getString(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToLocNm, rs.getString(SHIP_TO_LOC_NM));
        String firstOthLineAddr = setFirstOthLineAddr(rs.getString(SHIP_TO_FIRST_LINE_ADDR), rs.getString(SHIP_TO_SCD_LINE_ADDR), rs.getString(SHIP_TO_THIRD_LINE_ADDR), rs.getString(SHIP_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.firstOthLineAddr, firstOthLineAddr);
        String scdThirdFrthLineAddr = setScdThirdFrthLineAddr(rs.getString(SHIP_TO_SCD_LINE_ADDR), rs.getString(SHIP_TO_THIRD_LINE_ADDR), rs.getString(SHIP_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.scdThirdFrthLineAddr, scdThirdFrthLineAddr);
        String fifthLineAddr = rs.getString(SHIP_TO_CTY_ADDR) + COMMA + SPACE + rs.getString(SHIP_TO_ST_CD) + SPACE + rs.getString(SHIP_TO_POST_CD);
        ZYPEZDItemValueSetter.setValue(tMsg.fifthLineAddr, fifthLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.elanRptPrintDtTxt, ZYPDateUtil.DateFormatter(salesDate, ZYPDateUtil.TYPE_YYYYMMDD, DATE_TIME_FORMAT));
        ZYPEZDItemValueSetter.setValue(tMsg.elanRptPrintYr, ZYPDateUtil.DateFormatter(salesDate, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_YYYY));
        ZYPEZDItemValueSetter.setValue(tMsg.elanRptPrintRqstSq, prntSq);

        return tMsg;
    }

    private String setFirstOthLineAddr(String firstLineAddr, String scdLineAddr, String thirdLineAddr, String frthLineAddr) {

        if (ZYPCommonFunc.hasValue(firstLineAddr)) {
            return firstLineAddr;
        }
        if (ZYPCommonFunc.hasValue(scdLineAddr)) {
            return scdLineAddr;
        }
        if (ZYPCommonFunc.hasValue(thirdLineAddr)) {
            return thirdLineAddr;
        }
        if (ZYPCommonFunc.hasValue(frthLineAddr)) {
            return frthLineAddr;
        }
        return firstLineAddr;
    }

    private String setScdThirdFrthLineAddr(String scdLineAddr, String thirdLineAddr, String frthLineAddr) {

        String scdThirdFrthLineAddr = null;
        if (ZYPCommonFunc.hasValue(scdLineAddr)) {
            scdThirdFrthLineAddr = scdLineAddr;
        }
        if (ZYPCommonFunc.hasValue(thirdLineAddr)) {
            scdThirdFrthLineAddr = scdThirdFrthLineAddr + thirdLineAddr;
        }
        if (ZYPCommonFunc.hasValue(frthLineAddr)) {
            if (ZYPCommonFunc.hasValue(thirdLineAddr)) {
                scdThirdFrthLineAddr = scdThirdFrthLineAddr + SPACE + frthLineAddr;
            }
        }
        return scdThirdFrthLineAddr;
    }

    /**
     * writeReport
     * @param elanRptPrintRqstSq BigDecimal
     * @param rptId String
     * @param rptBrNum String
     * @param totalProcCnt int
     * @return boolean true:success/false:faild
     */
    private boolean writeReport(String poOrdNum, BigDecimal elanRptPrintRqstSq, String rptId, String rptBrNum, int totalProcCnt) {

        long rptRqstPk = 0;
        // Generate S21ReportRequestBean
        S21ReportRequestBean requestBean = new S21ReportRequestBean(rptId);
        requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);

        // Set Report Input Parameter
        S21InputParameter inputParam = requestBean.getInputParamBeanInstance();
        inputParam.addReportParameter(GLBL_CMPY_CD, this.glblCmpyCd);
        inputParam.addReportParameter(ELAN_RPT_PRINT_RQST_SQ, elanRptPrintRqstSq);
        inputParam.addReportParameter(INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter(PO_ORD_NUM, poOrdNum);
        requestBean.setInputParamBean(inputParam);
        String prntTpFlg = ZYPCodeDataUtil.getVarCharConstValue(NPAF0060_DIRECT_PRINT, glblCmpyCd);

        requestBean.setRptTtlNm(poOrdNum + REPORT_TITLE);
        // Case Printer
        if (ZYPConstant.FLG_ON_Y.equals(prntTpFlg)) {

            S21PrinterOutputParameter outputParam = new S21PrinterOutputParameter();
            //Set Output Parameter
            outputParam.setBranchNo(rptBrNum);
            requestBean.setPrintOutParamBean(outputParam);
            S21EIPPrintingService eipPrintingService = new S21EIPPrintingService();

            rptRqstPk = eipPrintingService.createReportByAsync(requestBean);

        // Case PDF
        } else if (ZYPConstant.FLG_OFF_N.equals(prntTpFlg)) {
            rptRqstPk = service.createReportByAsync(requestBean);
        }
        if (rptRqstPk == 0) {
            return false;
        }
        printCount++;
        return true;
    }

    /**
     * Remove Purge Record
     */
    private void removeElanRptWrkRec() {

        //Check Target Date Record
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        S21SsmLowLevelCodingClient ssmLLClient  = S21SsmLowLevelCodingClient.getClient(this.getClass());
        String purgeDtTm = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_FORMAT_14);
        purgeDtTm = ZYPDateUtil.addDays(purgeDtTm, purgeDt.negate().intValue());
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(DB_PARAM_PURGE_DATE, purgeDtTm);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setMaxRows(0);
            preparedStatement = ssmLLClient.createPreparedStatement("getRemoveRec", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ELAN_RPT_WRKTMsg tMsg = new ELAN_RPT_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.elanRptWrkPk, rs.getBigDecimal(ELAN_RPT_WRK_PK));

                // Delete Target Date Record
                EZDTBLAccessor.remove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    throw new S21AbendException(NPAB141001Constant.NPAM1342E, new String[] {tMsg.getTableName()});
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }
    }

    private String getRptId(String poOrdNum, String asnSoNum, String mdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(DB_PARAM_MDSE_CD, mdseCd);
        String rptId = (String) ssmBatchClient.queryObject("getRptId", queryParam);
        if (!ZYPCommonFunc.hasValue(rptId)) {
          //Set ErrorMail
            if (!setRptId.contains(poOrdNum + mdseCd + mdseCd)) {
                setRptId.add(poOrdNum + mdseCd + mdseCd);
                setErrorInfo(poOrdNum, mdseCd, NPAB141001Constant.NPAM1591E, new String[] {mdseCd, RPT_ITEM_CTRL});
            }
        }
        return rptId;
    }

    private String getRptBrNum(String poOrdNum, String mdseCd, String rptId, String rtlWhCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(DB_PARAM_RPT_ID, rptId);
        queryParam.put(DB_PARAM_RTL_WH_CD, rtlWhCd);
        String rptBrNum = (String) ssmBatchClient.queryObject("getRptBrNum", queryParam);
        if (!ZYPCommonFunc.hasValue(rptBrNum)) {
            //Set ErrorMail
            if (!setRptBrNum.contains(poOrdNum + mdseCd + rtlWhCd + rptId)) {
                setRptBrNum.add(poOrdNum + mdseCd + rtlWhCd + rptId);
                setErrorInfo(poOrdNum, mdseCd, NPAB141001Constant.NPAM1592E, new String[] {rtlWhCd, rptId, RPT_PRINT_CTRL});
            }
        }
        return rptBrNum;
    }

    /**
     * updateElnPrntSts
     * @param elanPrintSts String
     * @param asnSoNum String
     */
    private void updateElnPrntSts(String asnSoNum, String elanPrintSts) {

        EDI_ASN_HDR_WRKTMsg tMsg = new EDI_ASN_HDR_WRKTMsg();
        tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        tMsg.asnSoNum.setValue(asnSoNum);

        tMsg = (EDI_ASN_HDR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
        tMsg.elanPrintStsCd.setValue(elanPrintSts);

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println(NPAB141001Constant.NPAM1171E, new String[] {EDI_ASN_HDR_WRK});
        }
    }

    /**
     * updateElnPrntStsBySoNum
     * @param prntFlg boolean
     * @param errorFlg boolean
     * @param serNumFlg boolean
     * @param asnSoNum String
     */
    private void updateElnPrntStsBySoNum(boolean drpShpFlg, boolean prntFlg, boolean errorFlg, boolean serNumFlg, String asnSoNum, String ezintime) {

        String elnPrntSts = null;
        if (drpShpFlg) {
            elnPrntSts = ELAN_PRINT_STS.NOT_PRINTING;
        } else if (!prntFlg) {
            elnPrntSts = ELAN_PRINT_STS.NOT_PRINTING;
        } else if (errorFlg) {
            // QC#28852 Add Start
            if (isOverSendMailLimitDt(ezintime)) {
                elnPrntSts = ELAN_PRINT_STS.WARNING;
            } else {
                elnPrntSts = ELAN_PRINT_STS.PRINT_ERROR;
            }
            // QC#28852 Add End
        } else if (serNumFlg) {
            elnPrntSts = ELAN_PRINT_STS.WARNING;
        }
        updateElnPrntSts(asnSoNum, elnPrntSts);
    }
    // QC#28852 Add Start
    private boolean isOverSendMailLimitDt(String ezintime) {
        String sendMailLimitTm = ZYPDateUtil.addDays(ezintime, sendMailLimitDays.intValue());
        String sendMailLimitDt = sendMailLimitTm.substring(0, 8);
        if (ZYPDateUtil.compare(sendMailLimitDt, salesDate) <= 0 ) {
            return true;
        }
        return false;
    }
    // QC#28852 Add End

    private void setErrorInfo(String poOrdNum, String mdseCd, String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
        String msg = String.format("%8s   %-16s  %s",
                poOrdNum,
                mdseCd,
                S21MessageFunc.clspGetMessage(msgId, params));
        this.msgList.add(msg);
    }

    private void sendEmail() {

        if (msgList.isEmpty()) {
            return;
        }

        S21MailGroup fromGrp = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();

        S21Mail mail = new S21Mail(this.glblCmpyCd);

        if (fromAddrList.size() > 0) {

            mail.setFromAddress(fromAddrList.get(0));

            S21MailGroup toGrp = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
            List<S21MailAddress> toAddrList = toGrp.getMailAddress();
            if (!toAddrList.isEmpty()) {

                mail.setToAddressList(toAddrList);

                S21MailTemplate tmpl = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);

                if (ZYPCommonFunc.hasValue(tmpl.getSubject())) {

                    String newLine = System.getProperty("line.separator");

                    StringBuilder msgBuf = new StringBuilder();
                    //Set Header
                    msgBuf.append(PO_ORD_NUM + SPACE + MDSE_CD + MAIL_SPACE + ERROR_MESSAGE);
                    msgBuf.append(newLine);
                    for (String msg : msgList) {
                        msgBuf.append(msg);
                        msgBuf.append(newLine);
                    }

                    SimpleDateFormat errTmFmt = new SimpleDateFormat(DATE_TIME_PATTERN_FOR_MAIL);

                    tmpl.setTemplateParameter("batchId", BATCH_ID);
                    tmpl.setTemplateParameter("errDate", errTmFmt.format(new Date()));
                    tmpl.setTemplateParameter("message", msgBuf.toString());

                    mail.setMailTemplate(tmpl);
                    mail.postMail();

                    // Error Info Insert commit
                    commit();
                }
            }
        }
    }

}
