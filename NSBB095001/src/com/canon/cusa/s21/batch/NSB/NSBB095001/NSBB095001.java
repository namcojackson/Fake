/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB095001;

import static com.canon.cusa.s21.batch.NSB.NSBB095001.NSBB095001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.FSR_CPLT_LTR_MOD_WRKTMsg;
import business.db.FSR_CPLT_LTR_MOD_WRKTMsgArray;
import business.db.FSR_CPLT_LTR_MTR_WRKTMsg;
import business.db.FSR_CPLT_LTR_MTR_WRKTMsgArray;
import business.db.FSR_CPLT_LTR_PRT_WRKTMsg;
import business.db.FSR_CPLT_LTR_PRT_WRKTMsgArray;
import business.db.FSR_CPLT_LTR_WRKTMsg;
import business.db.FSR_CPLT_LTR_WRKTMsgArray;
import business.db.SVC_TASKTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TEST_COPY_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TASK_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.S21PrintingUtil;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21EmailOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 *<pre>
 * FSR Completion Letter Creation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/04   Hitachi         T.Tomita        Create          QC#18713(Solution#138)
 * 2018/06/21   Hitachi         K.Kojima        Update          QC#26669
 * 2018/07/11   Hitachi         K.Kitachi       Update          QC#26312
 * 2018/08/13   Hitachi         K.Kim           Update          QC#26312-1
 * 2018/10/24   Fujitsu         M.Yamada        Update          QC#28884
 * 2019/03/01   Hitachi         A.Kohinata      Update          QC#30592
 * 2019/03/28   Hitachi         K.Fujimoto      Update          QC#30970
 * 2019/10/04   Hitachi         K.Fujimoto      Update          QC#53389
 * 2019/11/08   Hitachi         A.Kohinata      Update          QC#54522
 * 2019/12/26   Hitachi         A.Kohinata      Update          QC#55142
 * 2022/03/07   CITS            L.Mandanas      Update          QC#59790
 *</pre>
 **/
public class NSBB095001 extends S21BatchMain {

    /** Total Counter */
    private int totCnt;

    /** Error Counter */
    private int errCnt;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Term Code */
    private TERM_CD termCd = null;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** CCY */
    private CCYTMsg ccyTMsg;

    // add start 2019/03/01 QC#30592
    /** Service Memo Type List */
    private List<String> svcMemoTpList = null;
    // add end 2019/03/01 QC#30592

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB095001().executeBatch(NSBB095001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Global Company Code" });
        }
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BATCH_PROGRAM_ID);
        if (!hasValue(this.slsDt)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Sales Date" });
        }
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.totCnt = 0;
        this.errCnt = 0;

        // add start 2019/03/01 QC#30592
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NSBB0950_SVC_MEMO_TP, glblCmpyCd);
        if (hasValue(constVal)) {
            svcMemoTpList = Arrays.asList(constVal.split(DELIMITER));
        }
        // add end 2019/03/01 QC#30592
    }

    @Override
    protected void mainRoutine() {
        // Delete Work Table
        // START 2018/07/11 K.Kitachi [QC#26312, DEL]
//        deleteWorkTbl();
        // END 2018/07/11 K.Kitachi [QC#26312, DEL]

        // Entry Work Table
        entryWorkTbl();

        // Create Report
        createFsrCpltRpt();
    }

    @Override
    protected void termRoutine() {
        if (this.errCnt > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totCnt - this.errCnt, this.errCnt, this.totCnt);
    }

    // START 2018/07/11 K.Kitachi [QC#26312, DEL]
//    private void deleteWorkTbl() {
//        // Delete FSR_CPLT_LTR_WORK
//        deleteFsrCpltLtrWork();
//        // Delete FSR_CPLT_LTR_MTR_WORK
//        deleteFsrCpltLtrMtrWork();
//        // Delete FSR_CPLT_LTR_PRT_WORK
//        deleteFsrCpltLtrPrtWork();
//        // Delete FSR_CPLT_LTR_MOD_WORK
//        deleteFsrCpltLtrModWork();
//        // Commit
//        commit();
//    }
    // END 2018/07/11 K.Kitachi [QC#26312, DEL]

    private void entryWorkTbl() {
        List<String> svcTaskNumList = getTargetTask();
        this.totCnt = svcTaskNumList.size();
        BigDecimal fsrCpltLtrWorkPk;
        String invCcyCd;
        Map<String, Object> fsrCpltLtrHdr;
        List<Map<String, Object>> fsrCpltLtrMtrList;
        List<Map<String, Object>> fsrCpltLtrPrtList;
        List<Map<String, Object>> fsrCpltLtrModList;
        for (String svcTaskNum : svcTaskNumList) {
            // START 2018/07/11 K.Kitachi [QC#26312, ADD]
            deleteWorkTbl(svcTaskNum);
            // END 2018/07/11 K.Kitachi [QC#26312, ADD]
            // Get FSR_CPLT_LTR_WORK
            fsrCpltLtrHdr = getFsrCpltLtrHdr(svcTaskNum);
            if (fsrCpltLtrHdr.isEmpty()) { // QC#28884
                continue;
            }
            // Get FSR_CPLT_LTR_MTR_WORK
            fsrCpltLtrMtrList = getFsrCpltLtrMtr(svcTaskNum);
            // Get FSR_CPLT_LTR_PRT_WORK
            fsrCpltLtrPrtList = getFsrCpltLtrPrt(svcTaskNum);
            // Get FSR_CPLT_LTR_MOD_WORK
            fsrCpltLtrModList = getFsrCpltLtrMod(svcTaskNum);

            fsrCpltLtrWorkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CPLT_LTR_WRK_SQ);
            setCcy((String) fsrCpltLtrHdr.get("INV_CCY_CD"));

            // Entry FSR_CPLT_LTR_WORK
            if (!entryFsrCpltLtrWork(fsrCpltLtrWorkPk, fsrCpltLtrHdr, fsrCpltLtrMtrList, fsrCpltLtrPrtList)) {
                rollback();
                S21InfoLogOutput.println(NSBM0121E, new String[] {"FSR_CPLT_LTR_WORK", "Serial#" + svcTaskNum });
                updTaskPrintSts(svcTaskNum, TASK_PRINT_STS.ERROR);
                errCnt++;
                commit();
                continue;
            }

            // Entry FSR_CPLT_LTR_MTR_WORK
            if (!entryFsrCpltLtrMtrWork(fsrCpltLtrWorkPk, fsrCpltLtrMtrList)) {
                rollback();
                S21InfoLogOutput.println(NSBM0121E, new String[] {"FSR_CPLT_LTR_MTR_WORK", "Serial#" + svcTaskNum });
                updTaskPrintSts(svcTaskNum, TASK_PRINT_STS.ERROR);
                errCnt++;
                commit();
                continue;
            }

            // Entry FSR_CPLT_LTR_PRT_WORK
            if (!entryFsrCpltLtrPrtWork(fsrCpltLtrWorkPk, fsrCpltLtrPrtList)) {
                rollback();
                S21InfoLogOutput.println(NSBM0121E, new String[] {"FSR_CPLT_LTR_PRT_WORK", "Serial#" + svcTaskNum });
                updTaskPrintSts(svcTaskNum, TASK_PRINT_STS.ERROR);
                errCnt++;
                commit();
                continue;
            }

            // Entry FSR_CPLT_LTR_MOD_WORK
            if (!entryFsrCpltLtrModWork(fsrCpltLtrWorkPk, fsrCpltLtrModList)) {
                rollback();
                S21InfoLogOutput.println(NSBM0121E, new String[] {"FSR_CPLT_LTR_MOD_WORK", "Serial#" + svcTaskNum });
                updTaskPrintSts(svcTaskNum, TASK_PRINT_STS.ERROR);
                errCnt++;
                commit();
                continue;
            }

            commit();
        }
    }

    private void createFsrCpltRpt() {
        List<Map<String, Object>> targetList = getTargetWork();

        S21MailTemplate template = getTemplate();
        if (template == null) {
            this.errCnt = this.totCnt;
            S21InfoLogOutput.println(NSBM0077E, new String[] {NSBB0950M001 });
            return;
        }

        S21EIPPrintingService service = new S21EIPPrintingService();

        S21ReportRequestBean requestBean;
        S21InputParameter inParam;
        S21EmailOutputParameter mailOutParam;
        BigDecimal fsrCpltLtrWrkPk;
        String svcTaskNum;
        String custEmlAddr;
        String[] custEmlAddrList;
        String sysTs;
        String rptTtlNm;
        // ADD START 2019/03/28 K.Fujimoto QC#30970
        String serNum;
        String techCd;
        String DsAcctNm;
        // ADD END 2019/03/28 K.Fujimoto QC#30970
        long rptRqstPk;
        for (Map<String, Object> target : targetList) {
            fsrCpltLtrWrkPk = (BigDecimal) target.get("FSR_CPLT_LTR_WRK_PK");
            svcTaskNum = (String) target.get("SVC_TASK_NUM");
            custEmlAddr = (String) target.get("CUST_EML_ADDR");
            // ADD START 2019/03/28 K.Fujimoto QC#30970
            DsAcctNm = (String) target.get("DS_ACCT_NM");
            serNum = (String) target.get("SER_NUM");
            techCd = (String) target.get("TECH_CD");
            // ADD END 2019/03/28 K.Fujimoto QC#30970
            // DEL START 2019/03/28 K.Fujimoto QC#30970
//            if (!hasValue(custEmlAddr)) {
//                S21InfoLogOutput.println(NSBM0032E, new String[] {"Costomer Email Address" });
//                updTaskPrintSts(svcTaskNum, TASK_PRINT_STS.ERROR);
//                errCnt++;
//                commit();
//                continue;
//            }
            // DEL END 2019/03/28 K.Fujimoto QC#30970
//            custEmlAddrList = custEmlAddr.split(",");
            sysTs = ZYPDateUtil.getCurrentSystemTime(TS_FORMAT);
            rptTtlNm = RPT_NM_SUFIX + svcTaskNum + "_" + sysTs;

//            template.setTemplateParameter(MAIL_PARAM_CTAC_PSN_NM, (String) target.get("SVC_CUST_CLLR_TXT"));

            requestBean = new S21ReportRequestBean(RPT_ID);
            requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
            requestBean.setRptArcFlg(true);
            requestBean.setRptTtlNm(rptTtlNm);
            requestBean.setRptJobId(BATCH_PROGRAM_ID);

            // Input Parameter
            inParam = requestBean.getInputParamBeanInstance();
            // MOD START 2019/03/28 K.Fujimoto QC#30970
            inParam.addReportParameter("GLBL_CMPY_CD", glblCmpyCd);
            inParam.addReportParameter("INTL_LANG_VAL_COL_NM", inParam.getSystemDefaultLanguage());
            inParam.addReportParameter("FSR_CPLT_LTR_WRK_PK", fsrCpltLtrWrkPk);
            inParam.addReportParameter("SVC_TASK_NUM", svcTaskNum);
            inParam.addReportParameter("DS_ACCT_NM", DsAcctNm);
            inParam.addReportParameter("SER_NUM", serNum);
            inParam.addReportParameter("TECH_CD", techCd);
            requestBean.setInputParamBean(inParam);

            if (hasValue(custEmlAddr)) {
                // Output Parameter
                // START 2022/03/07 L.Mandanas [QC#59790, ADD]
                custEmlAddr = custEmlAddr.replaceAll(" ", "");
                // END 2022/03/07 L.Mandanas [QC#59790, ADD]
                custEmlAddrList = custEmlAddr.split(",");
                template.setTemplateParameter(MAIL_PARAM_CTAC_PSN_NM, (String) target.get("SVC_CUST_CLLR_TXT"));
                mailOutParam = requestBean.getEmailOutParamInstance();
                // mod start 2019/11/08 QC#54522
                for (int i = 0; i < custEmlAddrList.length; i++) {
                    if (checkEmailFormat(custEmlAddrList[i])) {
                        mailOutParam.addToAddress(custEmlAddrList[i]);
                    }
                }
                if (mailOutParam.getToAddressList() != null && mailOutParam.getToAddressList().size() > 0) {
                    // Online:00, EML:01
                    // mailOutParam.setBranchNo("00");
                    mailOutParam.setBranchNo("01");
                    mailOutParam.setAttachementFlag(true);
                    mailOutParam.setAttachFileName(rptTtlNm + FILE_EXTN);
                    mailOutParam.setSubject(template.getSubject());
                    mailOutParam.setBodyText(template.getBody());
                    // From Address
                    String fromAddress = getFromAddress();
                    if (hasValue(fromAddress)) {
                        mailOutParam.setSenderAddress(fromAddress);
                    }
                    requestBean.setEmailOutParamBean(mailOutParam);
                }
                // mod end 2019/11/08 QC#54522
            }
            // MOD END 2019/03/28 K.Fujimoto QC#30970

            // Create Report
            rptRqstPk = service.createReportByAsync(requestBean);
            if (rptRqstPk != 0) {
                updTaskPrintSts(svcTaskNum, TASK_PRINT_STS.PRINTED);
            } else {
                updTaskPrintSts(svcTaskNum, TASK_PRINT_STS.ERROR);
                errCnt++;
            }
            commit();
        }
        if (totCnt != 0 && errCnt != totCnt) {
            service.activateAsyncReportJob();
            commit();
        }

    }

    // START 2018/07/11 K.Kitachi [QC#26312, DEL]
//    private void deleteFsrCpltLtrWork() {
//        FSR_CPLT_LTR_WRKTMsg inMsg = new FSR_CPLT_LTR_WRKTMsg();
//        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//        EZDTBLAccessor.removeByPartialKey(inMsg);
//    }
//
//    private void deleteFsrCpltLtrMtrWork() {
//        FSR_CPLT_LTR_MTR_WRKTMsg inMsg = new FSR_CPLT_LTR_MTR_WRKTMsg();
//        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//        EZDTBLAccessor.removeByPartialKey(inMsg);
//    }
//
//    private void deleteFsrCpltLtrPrtWork() {
//        FSR_CPLT_LTR_PRT_WRKTMsg inMsg = new FSR_CPLT_LTR_PRT_WRKTMsg();
//        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//        EZDTBLAccessor.removeByPartialKey(inMsg);
//    }
//
//    private void deleteFsrCpltLtrModWork() {
//        FSR_CPLT_LTR_MOD_WRKTMsg inMsg = new FSR_CPLT_LTR_MOD_WRKTMsg();
//        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//        EZDTBLAccessor.removeByPartialKey(inMsg);
//    }
    // END 2018/07/11 K.Kitachi [QC#26312, DEL]

    private List<String> getTargetTask() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        List<String> taskPrintStsCdList = new ArrayList<String>();
        taskPrintStsCdList.add(TASK_PRINT_STS.CREATED);
        taskPrintStsCdList.add(TASK_PRINT_STS.ERROR);
        param.put("taskPrintStsCdList", taskPrintStsCdList);
        // add start 2019/10/04 K.Fujimoto QC#53389
        param.put("debriefErr", SVC_TASK_STS.DEBRIEF_ERROR);
        String debriefErrSndblFlg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NSBB0950_DBRF_ERR_SENDBL_FLG, glblCmpyCd);
        if (!hasValue(debriefErrSndblFlg)) {
            debriefErrSndblFlg = ZYPConstant.FLG_ON_Y;
        }
        param.put("debriefErrSndblFlg", debriefErrSndblFlg);
        // add end   2019/10/04 K.Fujimoto QC#53389

        return (List<String>) ssmBatchClient.queryObjectList("getTargetTask", param);
    }

    private Map<String, Object> getFsrCpltLtrHdr(String svcTaskNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcTaskNum", svcTaskNum);
        param.put("svcMemoCatgCd", SVC_MEMO_CATG.DISPATCH_MEMO);
        // add start 2019/03/01 QC#30592
        param.put("svcMemoTpList", svcMemoTpList);
        // add end 2019/03/01 QC#30592

        return (Map<String, Object>) ssmBatchClient.queryObject("getFsrCpltLtrHdr", param);
    }

    private List<Map<String, Object>> getFsrCpltLtrMtr(String svcTaskNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcTaskNum", svcTaskNum);
        param.put("dsTestCopyClsCd", DS_TEST_COPY_CLS.TEST_COPY_OUT);

        // mod start 2019/12/26 QC#55142
        //return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFsrCpltLtrMtr", param);
        param.put("techReadMndFlg", ZYPConstant.FLG_ON_Y);
        param.put("slsDt", this.slsDt);
        List<Map<String, Object>> fsrCpltLtrMtrList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFsrCpltLtrMtr", param);
        if (fsrCpltLtrMtrList.isEmpty()) {
            param.put("techReadMndFlg", ZYPConstant.FLG_OFF_N);
            fsrCpltLtrMtrList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFsrCpltLtrMtr", param);
        }
        return fsrCpltLtrMtrList;
        // mod end 2019/12/26 QC#55142
    }

    private List<Map<String, Object>> getFsrCpltLtrPrt(String svcTaskNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcTaskNum", svcTaskNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFsrCpltLtrPrt", param);
    }

    private List<Map<String, Object>> getFsrCpltLtrMod(String svcTaskNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcTaskNum", svcTaskNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFsrCpltLtrMod", param);
    }

    private void setCcy(String ccyCd) {
        CCYTMsg inMsg = new CCYTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        if (!hasValue(ccyCd)) {
            setValue(inMsg.ccyCd, DEF_CCY_CD);
        } else {
            setValue(inMsg.ccyCd, ccyCd);
        }
        this.ccyTMsg = (CCYTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private Map<String, Object> getShipToInfo(String svcTaskNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcTaskNum", svcTaskNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getShipToInfo", param);
    }

    private List<Map<String, Object>> getTargetWork() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        // START 2018/08/13 K.Kim [QC#26312-1, ADD]
        List<String> taskPrintStsCdList = new ArrayList<String>();
        taskPrintStsCdList.add(TASK_PRINT_STS.CREATED);
        taskPrintStsCdList.add(TASK_PRINT_STS.ERROR);
        param.put("taskPrintStsCdList", taskPrintStsCdList);
        // END 2018/08/13 K.Kim [QC#26312-1, ADD]
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTargetWork", param);
    }
    
    private boolean entryFsrCpltLtrWork(BigDecimal fsrCpltLtrWorkPk, Map<String, Object> fsrCpltLtrHdr, List<Map<String, Object>> fsrCpltLtrMtrList, List<Map<String, Object>> fsrCpltLtrPrtList) {
        FSR_CPLT_LTR_WRKTMsg inMsg = new FSR_CPLT_LTR_WRKTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.fsrCpltLtrWrkPk, fsrCpltLtrWorkPk);
        setValue(inMsg.procDtTxt, formatDate(this.slsDt, DATE_FORMAT));
        setValue(inMsg.techCd, (String) fsrCpltLtrHdr.get("TECH_CD"));
        setValue(inMsg.svcTaskNum, (String) fsrCpltLtrHdr.get("SVC_TASK_NUM"));
        setValue(inMsg.mdlNm, (String) fsrCpltLtrHdr.get("MDL_NM"));
        setValue(inMsg.serNum, (String) fsrCpltLtrHdr.get("SER_NUM"));
        setValue(inMsg.dsAcctNm, (String) fsrCpltLtrHdr.get("DS_ACCT_NM"));
        setValue(inMsg.shipToLineAddr, (String) fsrCpltLtrHdr.get("SHIP_TO_LINE_ADDR"));
        setValue(inMsg.shipToCtyAddr, (String) fsrCpltLtrHdr.get("SHIP_TO_CTY_ADDR"));
        setValue(inMsg.shipToStCd, (String) fsrCpltLtrHdr.get("SHIP_TO_ST_CD"));
        setValue(inMsg.svcCustCllrTxt, (String) fsrCpltLtrHdr.get("SVC_CUST_CLLR_TXT"));
        setValue(inMsg.shipToLocNm, (String) fsrCpltLtrHdr.get("SHIP_TO_LOC_NM"));
        setValue(inMsg.custTelNum, (String) fsrCpltLtrHdr.get("CUST_TEL_NUM"));
        setValue(inMsg.shipToPostCd, (String) fsrCpltLtrHdr.get("SHIP_TO_POST_CD"));
        setValue(inMsg.dsSvcCallTpCd, (String) fsrCpltLtrHdr.get("DS_SVC_CALL_TP_CD"));
        setValue(inMsg.dsSvcCallTpDescTxt, (String) fsrCpltLtrHdr.get("DS_SVC_CALL_TP_DESC_TXT"));
        setValue(inMsg.svcBillTpCd, (String) fsrCpltLtrHdr.get("SVC_BILL_TP_CD"));
        setValue(inMsg.svcBillTpDescTxt, (String) fsrCpltLtrHdr.get("SVC_BILL_TP_DESC_TXT"));
        setValue(inMsg.svcPblmTpCd, (String) fsrCpltLtrHdr.get("SVC_PBLM_TP_CD"));
        setValue(inMsg.svcPblmTpDescTxt, (String) fsrCpltLtrHdr.get("SVC_PBLM_TP_DESC_TXT"));
        setValue(inMsg.openSvcPblmTpCd, (String) fsrCpltLtrHdr.get("OPEN_SVC_PBLM_TP_CD"));
        setValue(inMsg.openSvcPblmTpDescTxt, (String) fsrCpltLtrHdr.get("OPEN_SVC_PBLM_TP_DESC_TXT"));
        setValue(inMsg.svcPblmLocTpCd, (String) fsrCpltLtrHdr.get("SVC_PBLM_LOC_TP_CD"));
        setValue(inMsg.svcPblmLocTpDescTxt, (String) fsrCpltLtrHdr.get("SVC_PBLM_LOC_TP_DESC_TXT"));
        setValue(inMsg.svcPblmRsnTpCd, (String) fsrCpltLtrHdr.get("SVC_PBLM_RSN_TP_CD"));
        setValue(inMsg.svcPblmRsnTpDescTxt, (String) fsrCpltLtrHdr.get("SVC_PBLM_RSN_TP_DESC_TXT"));
        setValue(inMsg.svcPblmCrctTpCd, (String) fsrCpltLtrHdr.get("SVC_PBLM_CRCT_TP_CD"));
        setValue(inMsg.svcPblmCrctTpDescTxt, (String) fsrCpltLtrHdr.get("SVC_PBLM_CRCT_TP_DESC_TXT"));

        String convCallStartDtTm = convCallDtTm(inMsg.svcTaskNum.getValue(), (String) fsrCpltLtrHdr.get("CALL_START_DT_TM_TXT"));
        setValue(inMsg.callStartDtTmTxt, formatDateTime(convCallStartDtTm, DATE_TIME_FORMAT));

        String convCallCpltDtTmTxt = convCallDtTm(inMsg.svcTaskNum.getValue(), (String) fsrCpltLtrHdr.get("CALL_CPLT_DT_TM_TXT"));
        setValue(inMsg.callCpltDtTmTxt, formatDateTime(convCallCpltDtTmTxt, DATE_TIME_FORMAT));

        BigDecimal lborTmAmt = (BigDecimal) fsrCpltLtrHdr.get("LBOR_TM_AMT");
        setValue(inMsg.lborTmAmtTxt, ZYPFormatUtil.formatNumToSysDisp(lborTmAmt));

        BigDecimal totTestMtrCnt = getTotTestMtrCnt(fsrCpltLtrMtrList);
        setValue(inMsg.testMtrCntValTxt, ZYPFormatUtil.formatNumToSysDisp(totTestMtrCnt));

        BigDecimal totPrtPrcAmt = getTotPrtPrcAmt(fsrCpltLtrPrtList);
        setValue(inMsg.prtTotPrcAmtTxt, formatDispPrcAmt(totPrtPrcAmt));

        BigDecimal svcLborTotAmt = (BigDecimal) fsrCpltLtrHdr.get("SVC_LBOR_TOT_AMT");
        setValue(inMsg.svcLborTotAmtTxt, formatDispPrcAmt(svcLborTotAmt));

        BigDecimal svcMinChrgHrsAmt = (BigDecimal) fsrCpltLtrHdr.get("SVC_MIN_CHRG_HRS_AMT");
        setValue(inMsg.svcMinChrgHrsAmtTxt, formatSvcMinChrgHrsAmtTxt(svcMinChrgHrsAmt));

        // QC#28884
        BigDecimal totPrcAmt = svcLborTotAmt;
        if (hasValue(totPrtPrcAmt)) {
            totPrcAmt = totPrcAmt.add(totPrtPrcAmt);
        }
        setValue(inMsg.totPrcAmtTxt, formatDispPrcAmt(totPrcAmt));

        setValue(inMsg.svcCmntTxt, (String) fsrCpltLtrHdr.get("SVC_CMNT_TXT"));
        // START 2018/06/21 K.Kojima [QC#26669,MOD]
        // setValue(inMsg.signaFilePathTxt, (String) fsrCpltLtrHdr.get("SIGNA_FILE_PATH_TXT"));
        setValue(inMsg.signaFilePathTxt, getSignaFilePathTxt((String) fsrCpltLtrHdr.get("SIGNA_FILE_PATH_TXT")));
        // END 2018/06/21 K.Kojima [QC#26669,MOD]
        setValue(inMsg.custEmlAddr, (String) fsrCpltLtrHdr.get("CUST_EML_ADDR"));

        EZDTBLAccessor.create(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private boolean entryFsrCpltLtrMtrWork(BigDecimal fsrCpltLtrWorkPk, List<Map<String, Object>> fsrCpltLtrMtrList) {
        FSR_CPLT_LTR_MTR_WRKTMsg inMsg;
        BigDecimal fsrCpltLtrMtrWrkPk;
        for (Map<String, Object> fsrCpltLtrMtr : fsrCpltLtrMtrList) {
            inMsg = new FSR_CPLT_LTR_MTR_WRKTMsg();
            fsrCpltLtrMtrWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CPLT_LTR_MTR_WRK_SQ);
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.fsrCpltLtrMtrWrkPk, fsrCpltLtrMtrWrkPk);
            setValue(inMsg.fsrCpltLtrWrkPk, fsrCpltLtrWorkPk);
            setValue(inMsg.mtrLbCd, (String) fsrCpltLtrMtr.get("MTR_LB_CD"));
            setValue(inMsg.mtrLbDescTxt, (String) fsrCpltLtrMtr.get("MTR_LB_DESC_TXT"));
            setValue(inMsg.readMtrCntValTxt, ZYPFormatUtil.formatNumToSysDisp((BigDecimal) fsrCpltLtrMtr.get("READ_MTR_CNT")));
            setValue(inMsg.testMtrCntValTxt, ZYPFormatUtil.formatNumToSysDisp((BigDecimal) fsrCpltLtrMtr.get("TEST_MTR_CNT")));
            setValue(inMsg.mtrSortNum, (BigDecimal) fsrCpltLtrMtr.get("MTR_SORT_NUM"));
            EZDTBLAccessor.create(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    private boolean entryFsrCpltLtrPrtWork(BigDecimal fsrCpltLtrWorkPk, List<Map<String, Object>> fsrCpltLtrPrtList) {
        FSR_CPLT_LTR_PRT_WRKTMsg inMsg;
        BigDecimal fsrCpltLtrPrtWrkPk;
        BigDecimal prtTotPrcAmt;
        for (Map<String, Object> fsrCpltLtrPrt : fsrCpltLtrPrtList) {
            inMsg = new FSR_CPLT_LTR_PRT_WRKTMsg();
            fsrCpltLtrPrtWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CPLT_LTR_PRT_WRK_SQ);
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.fsrCpltLtrPrtWrkPk, fsrCpltLtrPrtWrkPk);
            setValue(inMsg.fsrCpltLtrWrkPk, fsrCpltLtrWorkPk);
            setValue(inMsg.mdseCd, (String) fsrCpltLtrPrt.get("MDSE_CD"));
            setValue(inMsg.mdseDescShortTxt, (String) fsrCpltLtrPrt.get("MDSE_DESC_SHORT_TXT"));
            setValue(inMsg.svcPrtQtyTxt, ZYPFormatUtil.formatNumToSysDisp((BigDecimal) fsrCpltLtrPrt.get("SVC_PRT_QTY")));
            prtTotPrcAmt = (BigDecimal) fsrCpltLtrPrt.get("PRT_TOT_PRC_AMT");
            setValue(inMsg.prtTotPrcAmtTxt, formatDispPrcAmt(prtTotPrcAmt));
            setValue(inMsg.prtSortNum, (BigDecimal) fsrCpltLtrPrt.get("PRT_SORT_NUM"));
            EZDTBLAccessor.create(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                return false;
            }
        }
        if (fsrCpltLtrPrtList.size() == 0) {
            inMsg = new FSR_CPLT_LTR_PRT_WRKTMsg();
            fsrCpltLtrPrtWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CPLT_LTR_PRT_WRK_SQ);
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.fsrCpltLtrPrtWrkPk, fsrCpltLtrPrtWrkPk);
            setValue(inMsg.fsrCpltLtrWrkPk, fsrCpltLtrWorkPk);
            setValue(inMsg.mdseDescShortTxt, NO_PRT_CMT);
            setValue(inMsg.prtSortNum, BigDecimal.ONE);
            EZDTBLAccessor.create(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    private boolean entryFsrCpltLtrModWork(BigDecimal fsrCpltLtrWorkPk, List<Map<String, Object>> fsrCpltLtrModList) {
        FSR_CPLT_LTR_MOD_WRKTMsg inMsg;
        BigDecimal fsrCpltLtrModWrkPk;
        for (Map<String, Object> fsrCpltLtrMod : fsrCpltLtrModList) {
            inMsg = new FSR_CPLT_LTR_MOD_WRKTMsg();
            fsrCpltLtrModWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CPLT_LTR_MOD_WRK_SQ);
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.fsrCpltLtrModWrkPk, fsrCpltLtrModWrkPk);
            setValue(inMsg.fsrCpltLtrWrkPk, fsrCpltLtrWorkPk);
            setValue(inMsg.mdseCd, (String) fsrCpltLtrMod.get("MDSE_CD"));
            setValue(inMsg.mdseDescShortTxt, (String) fsrCpltLtrMod.get("MDSE_DESC_SHORT_TXT"));
            setValue(inMsg.svcModQtyTxt, ZYPFormatUtil.formatNumToSysDisp((BigDecimal) fsrCpltLtrMod.get("SVC_MOD_QTY")));
            setValue(inMsg.modPrtSortNum, (BigDecimal) fsrCpltLtrMod.get("MOD_PRT_SORT_NUM"));
            EZDTBLAccessor.create(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    private void updTaskPrintSts(String svcTaskNum, String taskPrintStsCd) {
        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcTaskNum, svcTaskNum);
        inMsg = (SVC_TASKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        if (inMsg == null) {
            return;
        }

        setValue(inMsg.taskPrintStsCd, taskPrintStsCd);
        EZDTBLAccessor.update(inMsg);
    }

    private String convCallDtTm(String svcTaskNum, String callDtTm) {
        Map<String, Object> shipToInfoMap = getShipToInfo(svcTaskNum);
        if (shipToInfoMap == null) {
            return callDtTm;
        }
        String ctryCd = (String) shipToInfoMap.get("CTRY_CD");
        String postCd = (String) shipToInfoMap.get("POST_CD");
        SvcTimeZoneInfo svcTzInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, callDtTm, ctryCd, postCd);
        if (svcTzInfo == null) {
            return callDtTm;
        }
        return svcTzInfo.getDateTime();
    }

    private BigDecimal getTotTestMtrCnt(List<Map<String, Object>> fsrCpltLtrMtrList) {
        BigDecimal totTestMtrCnt = BigDecimal.ZERO;
        boolean hasValueTestMtrCnt = false;
        for (Map<String, Object> fsrCpltLtrMtr : fsrCpltLtrMtrList) {
            if (hasValue((BigDecimal) fsrCpltLtrMtr.get("TEST_MTR_CNT"))) { // QC#28884
                hasValueTestMtrCnt = true; // QC#28884
                totTestMtrCnt = totTestMtrCnt.add((BigDecimal) fsrCpltLtrMtr.get("TEST_MTR_CNT"));
            }
        }
        if (!hasValueTestMtrCnt) { // QC#28884
            return null;
        }
        return totTestMtrCnt;
    }

    private BigDecimal getTotPrtPrcAmt(List<Map<String, Object>> fsrCpltLtrPrtList) {
        if (fsrCpltLtrPrtList == null) { // QC#28884
            return null;
        }
        BigDecimal totPrtPrcAmt = BigDecimal.ZERO;
        boolean hasValuePrtTotPrcAmt = false;
        for (Map<String, Object> fsrCpltLtrPrt : fsrCpltLtrPrtList) {
            BigDecimal prtTotPrcAmt = (BigDecimal) fsrCpltLtrPrt.get("PRT_TOT_PRC_AMT"); // QC#28884
            if (hasValue(prtTotPrcAmt)) { // QC#28884
                hasValuePrtTotPrcAmt = true; // QC#28884
                totPrtPrcAmt = totPrtPrcAmt.add(prtTotPrcAmt);
            }
        }
        if (!hasValuePrtTotPrcAmt) { // QC#28884
            return null;
        }
        return totPrtPrcAmt;
    }

    private String formatDate(String date, String format) {
        if (!hasValue(date) || !hasValue(format)) {
            return null;
        }
        return ZYPDateUtil.DateFormatter(date, "yyyyMMdd", format);
    }

    private String formatDateTime(String dateTime, String format) {
        if (!hasValue(dateTime) || !hasValue(format)) {
            return null;
        }
        return ZYPDateUtil.DateFormatter(dateTime, "yyyyMMddHHmmssSSS", format);
    }

    private String formatDispPrcAmt(BigDecimal prcAmt) {
        if (!hasValue(prcAmt)) {
            return null;
        }
        return this.ccyTMsg.ccySgnTxt.getValue() + ZYPFormatUtil.formatNumToSysDisp(prcAmt.setScale(this.ccyTMsg.aftDeclPntDigitNum.getValueInt()));
    }
    
    private String formatSvcMinChrgHrsAmtTxt(BigDecimal svcMinChrgHrsAmt) {
        if (!hasValue(svcMinChrgHrsAmt)) {
            return null;
        }
        return PRE_SVC_MIN_CHRG_HRS_AMT + svcMinChrgHrsAmt.toPlainString() + POST_SVC_MIN_CHRG_HRS_AMT;
    }

    private S21MailTemplate getTemplate() {
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, NSBB0950M001);
        if (template == null || !hasValue(template.getBody()) || !hasValue(template.getSubject())) {
            return null;
        }

        return template;
    }

    
    private String getFromAddress() {

        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, FROM0013);
        groupFrom.setMailKey1("From");
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList != null && !addrFromList.isEmpty()) {
            return addrFromList.get(0).getAddress();
        }

        return null;
    }

    // START 2018/06/21 K.Kojima [QC#26669,ADD]
    private String getSignaFilePathTxt(String signaFilePathTxt) {
        if (!hasValue(signaFilePathTxt)) {
            return signaFilePathTxt;
        }
        return S21PrintingUtil.copyImageFileToEipSharedLocation(signaFilePathTxt);
    }
    // END 2018/06/21 K.Kojima [QC#26669,ADD]

    // START 2018/07/11 K.Kitachi [QC#26312, ADD]
    private void deleteWorkTbl(String svcTaskNum) {
        FSR_CPLT_LTR_WRKTMsgArray fsrCpltLtrWrkList = getFsrCpltLtrWrkForUpdate(svcTaskNum);
        for (int i = 0; i < fsrCpltLtrWrkList.getValidCount(); i++) {
            BigDecimal fsrCpltLtrWrkPk = fsrCpltLtrWrkList.no(i).fsrCpltLtrWrkPk.getValue();
            FSR_CPLT_LTR_MTR_WRKTMsgArray fsrCpltLtrMtrWrkList = getFsrCpltLtrMtrWrkForUpdate(fsrCpltLtrWrkPk);
            for (int j = 0; j < fsrCpltLtrMtrWrkList.getValidCount(); j++) {
                EZDTBLAccessor.remove(fsrCpltLtrMtrWrkList.no(j));
            }
            FSR_CPLT_LTR_PRT_WRKTMsgArray fsrCpltLtrPrtWrkList = getFsrCpltLtrPrtWrkForUpdate(fsrCpltLtrWrkPk);
            for (int j = 0; j < fsrCpltLtrPrtWrkList.getValidCount(); j++) {
                EZDTBLAccessor.remove(fsrCpltLtrPrtWrkList.no(j));
            }
            FSR_CPLT_LTR_MOD_WRKTMsgArray fsrCpltLtrModWrkList = getFsrCpltLtrModWrkForUpdate(fsrCpltLtrWrkPk);
            for (int j = 0; j < fsrCpltLtrModWrkList.getValidCount(); j++) {
                EZDTBLAccessor.remove(fsrCpltLtrModWrkList.no(j));
            }
            EZDTBLAccessor.remove(fsrCpltLtrWrkList.no(i));
        }
    }

    private FSR_CPLT_LTR_WRKTMsgArray getFsrCpltLtrWrkForUpdate(String svcTaskNum) {
        FSR_CPLT_LTR_WRKTMsg inMsg = new FSR_CPLT_LTR_WRKTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        return (FSR_CPLT_LTR_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
    }

    private FSR_CPLT_LTR_MTR_WRKTMsgArray getFsrCpltLtrMtrWrkForUpdate(BigDecimal fsrCpltLtrWrkPk) {
        FSR_CPLT_LTR_MTR_WRKTMsg inMsg = new FSR_CPLT_LTR_MTR_WRKTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("fsrCpltLtrWrkPk01", fsrCpltLtrWrkPk);
        return (FSR_CPLT_LTR_MTR_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
    }

    private FSR_CPLT_LTR_PRT_WRKTMsgArray getFsrCpltLtrPrtWrkForUpdate(BigDecimal fsrCpltLtrWrkPk) {
        FSR_CPLT_LTR_PRT_WRKTMsg inMsg = new FSR_CPLT_LTR_PRT_WRKTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("fsrCpltLtrWrkPk01", fsrCpltLtrWrkPk);
        return (FSR_CPLT_LTR_PRT_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
    }

    private FSR_CPLT_LTR_MOD_WRKTMsgArray getFsrCpltLtrModWrkForUpdate(BigDecimal fsrCpltLtrWrkPk) {
        FSR_CPLT_LTR_MOD_WRKTMsg inMsg = new FSR_CPLT_LTR_MOD_WRKTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("fsrCpltLtrWrkPk01", fsrCpltLtrWrkPk);
        return (FSR_CPLT_LTR_MOD_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
    }
    // END 2018/07/11 K.Kitachi [QC#26312, ADD]

    // add start 2019/11/08 QC#54522
    private static boolean checkEmailFormat(String emlAddr) {
        boolean ret = true;
        try {
            InternetAddress addr = new InternetAddress(emlAddr);
            addr.validate();
        } catch (AddressException e) {
            ret = false;
        }
        return ret;
    }
    // add end 2019/11/08 QC#54522
}
