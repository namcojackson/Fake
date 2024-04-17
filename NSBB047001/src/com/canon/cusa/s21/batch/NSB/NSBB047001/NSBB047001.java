/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB047001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import parts.common.EZDMessageInfo;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;

import static com.canon.cusa.s21.batch.NSB.NSBB047001.NSBB047001Constant.*;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.wrapper.ClickSoftwareOptimizationService;

import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.BaseObjectWrapper;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSABusinessReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.DistrictReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.Engineer;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperationsResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.SkillReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.W6RequestedProperties;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.Engineer.CSAAccessPermits;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.Engineer.Skills;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.Engineer.Skills.EngineerSkill;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAAccessPermitReference;

/**
 *<pre>
 *  Send Technician to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/06   Fujitsu         N.Sugiura       Create          N/A
 * 2015/09/16   Hitachi         K.Yamada        Update          BugFix
 * 01/12/2016   Fujitsu         S.Nakai         Update          QC#2475
 * 02/26/2016   Hitachi         T.Iwamoto       Update          QC#4608
 * 04/14/2016   Hitachi         T.Iwamoto       Update          QC#6405
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 02/09/2017   Hitachi         K.Ochiai        Update          QC#17426
 * 06/22/2018   Hitachi         U.Kim           Update          QC#24961
 * 2018/07/24   Hitachi         K.Kitachi       Update          QC#27266
 * 2022/09/13   Hitachi         K.Kim           Update          QC#60566
 * 2023/05/25   CITS            M.Okamura       Update          QC#61544
 * 2023/06/12   CITS            T.Kimura        Update          QC#61544
 *</pre>
 */
public class NSBB047001 extends S21BatchMain {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    /** Last Process Timestamp */
    private Map<String, Object> lastProcTsMap = null;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Batch Mode */
    private String batchMode = "";

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** global company code */
    private String glblCmpyCd = "";

    /** Sales Date */
    private String slsDt = "";

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Error Messages */
    private Map<String, EZDMessageInfo> errorMessages = new LinkedHashMap<String, EZDMessageInfo>();

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** Skill List map */
    private Map<String, String> skillListMap = null;

    /** Svc Accs Pmit Num*/
    private Map<String, String> svcAccsPmitNumMap = null;

    /** clicksoft proxy */
    private ClickSoftwareOptimizationService proxy = new ClickSoftwareOptimizationService();

    /** clicksoft object factory */
    private ObjectFactory objFactory = new ObjectFactory();

    // START 2018/06/22 U.Kim [QC#24961, ADD]
    /** Organization Tier Code */
    private String orgTierCd = "";
    // END 2018/06/22 U.Kim [QC#24961, ADD]

    // START 2022/09/13 [QC#60566, ADD]
    private boolean isOfsSend = false;
    // END 2022/09/13 [QC#60566, ADD]

    /**
     * @param args parameter
     */
    public static void main(String[] args) {

        new NSBB047001().executeBatch(NSBB047001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // blank check(Global Company Code)
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Global Company Code" });
        }
        // blank check(Sales Date)
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZXM0015E, new String[] {"S", glblCmpyCd });
        }

        String strBatchMode = getUserVariable1();
        if (ZYPCommonFunc.hasValue(strBatchMode)) {
            this.batchMode = strBatchMode;
        }

        // START 2022/09/13 [QC#60566, ADD]
        String strOfsSend = ZYPCodeDataUtil.getVarCharConstValue(NSBB0470_OFS_SEND, glblCmpyCd);
        isOfsSend = ZYPConstant.FLG_ON_Y.equals(strOfsSend);
        // END 2022/09/13 [QC#60566, ADD]

        // initialize
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        // get Last Process Time and Current System Time
        this.lastProcTsMap = this.getLastProcTs();
        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);
        // START 2018/06/22 U.Kim [QC#24961, ADD]
        this.orgTierCd = this.getOrgTierCd();
        // END 2018/06/22 U.Kim [QC#24961, ADD]

        // Send Technician to Click Software
        sendTechInfo();

        // insert or update DS_BIZ_PROC_LOG
        this.insOrUpdDsBizProcLog();
        super.commit();

        // Send Mail if error or warning occurred.
        if (!errorMessages.isEmpty()) {
            termCd = TERM_CD.WARNING_END;
            insertErrorData();
            sendErrorMail();
        }
    }


    @Override
    protected void termRoutine() {

        setRecordCount(totalNmlCount, totalErrCount, (totalNmlCount + totalErrCount));
        setTermState(termCd);
    }

    /**
     * Send Technician Information.
     */
    private void sendTechInfo() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            // Send Technician Information.
            stmt = ssmLLClient.createPreparedStatement("sendTechInfo", getTechInfoParam());
            rs = stmt.executeQuery();

            sendTechInfoApi(rs);

        } catch (SQLException e) {
            super.rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }
    /**
     * Get Parameter to Query Technician Information.
     * @return Query Parameter
     */
    private Map<String, Object> getTechInfoParam() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("slsDt", slsDt);
        // del start 2016/04/14 CSA Defect#6405
        // START 2018/06/22 U.Kim [QC#24961, MOD]
        //params.put("bizAreaOrgCd", BIZ_AREA_ORG.SERVICE);
        params.put("orgTierCd", this.orgTierCd);
        // END 2018/06/22 U.Kim [QC#24961, MOD]
        // del end 2016/04/14 CSA Defect#6425
        // del start 2016/02/26 CSA Defect#6405
//        params.put("orgFuncRoleTpCd", ORG_FUNC_ROLE_TP.TECHNICIAN);
        // del end 2016/02/26 CSA Defect#4608
        params.put("procPgmId", PGM_ID);
     // START 2017/02/09 K.Ochiai [QC#17426, ADD]
        params.put("psnTpCd", PSN_TP.EMPLOYEE);
     // END   2017/02/09 K.Ochiai [QC#17426, ADD]
        // START 2018/07/24 K.Kitachi [QC#27266, ADD]
        params.put("bizAreaOrgCd", BIZ_AREA_ORG.SERVICE);
        // END 2018/07/24 K.Kitachi [QC#27266, ADD]
        if (batchMode.equals(ZYPConstant.FLG_ON_1)) {
            params.put("batchMode", ZYPConstant.FLG_OFF_N);
        } else if (batchMode.equals(ZYPConstant.FLG_OFF_0)
                && this.lastProcTsMap != null && !this.lastProcTsMap.isEmpty()) {
            params.put("batchMode", ZYPConstant.FLG_ON_Y);
            //START 2018/06/20 [QC#24961, ADD]
            params.put("lastProcTs", this.lastProcTsMap.get(DS_BIZ_LAST_PROC_TS));
            //END 2018/06/20 [QC#24961, ADD]
        } else {
            params.put("batchMode", ZYPConstant.FLG_OFF_N);
        }

        return params;
    }
    /**
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    private void sendTechInfoApi(ResultSet rs) throws SQLException {

        String befTechCd = "";
        String curTechCd = "";
        skillListMap = new HashMap<String, String>();
        svcAccsPmitNumMap = new HashMap<String, String>();
        Engineer engInfo = objFactory.createEngineer();
        CSAAccessPermits accessPmt = objFactory.createEngineerCSAAccessPermits();
        // START 2023/06/05 [QC#61544, ADD]
        String lineBizTpCd = "";
        String[] ofsNonAlgn = new String[0];
        boolean ofsNonAlgnFlg = false;
        // START 2023/06/12 [QC#61544, MOD]
        String ofsNonAlgnTmp = ZYPCodeDataUtil.getVarCharConstValue(NSBB047001_OFS_NON_ALGN, this.glblCmpyCd);
        // END 2023/06/12 [QC#61544, MOD]
        if (ofsNonAlgnTmp != null && !ofsNonAlgnTmp.isEmpty()) {
            ofsNonAlgn = ofsNonAlgnTmp.split(",");
        }
        // END 2023/06/05 [QC#61544, ADD]
        // Skills
        Skills skills = objFactory.createEngineerSkills();
        while (rs.next()) {
            curTechCd = rs.getString(TECH_TOC_CD);
            if (!befTechCd.equals(curTechCd)) {
                if (ZYPCommonFunc.hasValue(befTechCd)) {
                    // Call WMB API
                    engInfo.setSkills(skills);
                    engInfo.setCSAAccessPermits(accessPmt);
                    // START 2023/06/05 [QC#61544, ADD]
                    ofsNonAlgnFlg = false;
                    for (int i = 0; i < ofsNonAlgn.length; i++) {
                        if (ofsNonAlgn[i].equals(lineBizTpCd)) {
                            ofsNonAlgnFlg = true;
                            break;
                        }
                    }
                    if (!ofsNonAlgnFlg) {
                        callWmbApi(engInfo);
                    }
                    // END 2023/06/05 [QC#61544, ADD]

                    // Initialize skillListMap
                    skillListMap = new HashMap<String, String>();
                    svcAccsPmitNumMap = new HashMap<String, String>();

                    engInfo = objFactory.createEngineer();
                    skills = objFactory.createEngineerSkills();
                    accessPmt = objFactory.createEngineerCSAAccessPermits();

                }

                //set technician information.
                // START 2022/09/13 [QC#60566, MOD]
                // engInfo.setName(rs.getString(TECH_PSN_NM));
                if (isOfsSend) {
                    engInfo.setName(rs.getString(PSN_FIRST_NM) + ' ' + rs.getString(PSN_LAST_NM));
                } else {
                    engInfo.setName(rs.getString(TECH_PSN_NM));
                }
                // END 2022/09/13 [QC#60566, MOD]
                engInfo.setID(rs.getString(TECH_TOC_CD));
                engInfo.setLoginName(rs.getString(TECH_TOC_CD));
                // mod start 2016/04/14 CSA Defect#6405
                if (ZYPCommonFunc.hasValue(rs.getString(DISTRICT))) {

//                    StringBuilder sb = new StringBuilder();
//                    sb.append(rs.getString(COA_BR_CD));
//                    sb.append("-");
//                    sb.append(rs.getString(COA_BR_NM));

                    DistrictReference district = objFactory.createDistrictReference();
//                    district.getContent().add(sb.toString());
                    district.getContent().add(rs.getString(DISTRICT));
                    engInfo.setDistrict(district);
                }
                // mod end 2016/04/14 CSA Defect#6405

                if (rs.getString(ACTV_FLG).equals(ZYPConstant.FLG_ON_Y)) {
                    engInfo.setActive(true);
                } else {
                    engInfo.setActive(false);
                }

//                CSALineOfBusinessReference busRef = objFactory.createCSALineOfBusinessReference();
//                String lineBizTpCd = rs.getString(LINE_BIZ_TP_CD);
//                busRef.setDisplayString(lineBizTpCd);
//                busRef.getContent().add(lineBizTpCd);
//
//                engInfo.setCSALineOfBusiness(busRef);

              CSABusinessReference busRef = objFactory.createCSABusinessReference();
              // START 2023/05/25 [QC#61544, MOD]
              lineBizTpCd = rs.getString(LINE_BIZ_TP_CD);
              // END 2023/05/25 [QC#61544, MOD]
              busRef.setDisplayString(lineBizTpCd);
              busRef.getContent().add(lineBizTpCd);

              engInfo.setCSABusiness(busRef);

                engInfo.setMobilePhone(rs.getString(CELL_TEL_NUM));
                boolean physInvtyFlg = false;
                if (ZYPCommonFunc.hasValue(rs.getString(PHYS_INVTY_FLG))) {
                    if (ZYPConstant.CHKBOX_ON_Y.equals(rs.getString(PHYS_INVTY_FLG))) {
                        physInvtyFlg = true;
                    }
                }
                engInfo.setCSAPhysicalInventory(physInvtyFlg);

                //set skills and access permits.
                setSkillList(rs, skills);
                setSvcAccsPmitNum(rs, accessPmt);
                befTechCd = curTechCd;
            } else {

                //set skills and access permits.
                setSkillList(rs, skills);
                setSvcAccsPmitNum(rs, accessPmt);
                befTechCd = curTechCd;
            }
        }

        // Call WMB API
        if (ZYPCommonFunc.hasValue(engInfo.getID())) {
            engInfo.setSkills(skills);
            engInfo.setCSAAccessPermits(accessPmt);
            // START 2023/06/05 [QC#61544, ADD]
            ofsNonAlgnFlg = false;
            for (int j = 0; j < ofsNonAlgn.length; j++) {
                if (ofsNonAlgn[j].equals(lineBizTpCd)) {
                    ofsNonAlgnFlg = true;
                    break;
                }
            }
            if (!ofsNonAlgnFlg) {
                callWmbApi(engInfo);
            }
            // END 2023/06/05 [QC#61544, ADD]
        }

    }

    /**
     * Add Error Message.
     * @param tecCd tecCd
     * @param code Message Code
     * @param param Message Parameter
     */
    private void addMessage(String tecCd, String code, String... param) {
        termCd = TERM_CD.WARNING_END;
        errorMessages.put(tecCd, new EZDMessageInfo(code, param));
        S21InfoLogOutput.println(code, param);
    }
    /**
     * Send Error Mail.
     */
    private void sendErrorMail() {
        String errDate = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        S21MailAddress fromAddress;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID_01 });
        }

        fromAddress = addrFromList.get(0);

        S21Mail mail = new S21Mail(glblCmpyCd);
        // Set From Mail Address.
        mail.setFromAddress(fromAddress);
        // Set To Mail Address.
        mail.setToAddressList(addrToList);

        // Set Parameter
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, BATCH_PROGRAM_ID);
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, errDate);
        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, formatMessageToMail(errorMessages));

        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject());
        mail.postMail();
    }
    /**
     * get Last Process TimeStamp
     * @return Last Process TimeStamp Info Map
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getLastProcTs() {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("procPgmId", PGM_ID);
        List<Map<String, Object>> lastProcTsList = this.ssmClient.queryObjectList("getLastProcTs", ssmParam);

        if (lastProcTsList == null || lastProcTsList.isEmpty()) {
            return null;
        }

        return lastProcTsList.get(0);
    }
    // START 2018/06/22 U.Kim [QC#24961, ADD]
    /**
     * get Organization Tier Code
     * @return Organization Tier Code
     */
    private String getOrgTierCd() {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("bizAreaOrgCd", BIZ_AREA_ORG.SERVICE);
        String orgTierCd = (String) this.ssmClient.queryObject("getOrgTierCd", ssmParam);

        if (orgTierCd == null) {
            orgTierCd = "";
        }
        return orgTierCd;
    }
    // START 2018/06/22 U.Kim [QC#24961, ADD]
    /**
     * insert or update DS_BIZ_PROC_LOG
     */
    private void insOrUpdDsBizProcLog() {

        DS_BIZ_PROC_LOGTMsg tmsg = new DS_BIZ_PROC_LOGTMsg();

        if (this.lastProcTsMap == null) {
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
            ZYPEZDItemValueSetter.setValue(tmsg.procPgmId, PGM_ID);
            ZYPEZDItemValueSetter.setValue(tmsg.dsBizProcDt, this.currentSystemTs.substring(0, DT_LEN));

            ZYPEZDItemValueSetter.setValue(tmsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tmsg.dsBizLastProcTs, this.currentSystemTs);
            ZYPEZDItemValueSetter.setValue(tmsg.dsBizLastUpdTs, this.currentSystemTs);
            S21FastTBLAccessor.insert(tmsg);

        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.dsBizProcLogPk, (BigDecimal) this.lastProcTsMap.get("DS_BIZ_PROC_LOG_PK"));
            DS_BIZ_PROC_LOGTMsg updTMsg = (DS_BIZ_PROC_LOGTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            if (updTMsg != null) {
                ZYPEZDItemValueSetter.setValue(updTMsg.dsBizProcDt, this.currentSystemTs.substring(0, DT_LEN));
                ZYPEZDItemValueSetter.setValue(updTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(updTMsg.dsBizLastProcTs, this.currentSystemTs);
                ZYPEZDItemValueSetter.setValue(updTMsg.dsBizLastUpdTs, this.currentSystemTs);
                S21FastTBLAccessor.update(updTMsg);
            }
        }
    }
    private void setSkillList(ResultSet rs, Skills skills) throws SQLException {

        String svcSkillNm = rs.getString("SVC_SKILL_NM");

        if (!ZYPCommonFunc.hasValue(svcSkillNm)) {
            return;
        }

        if (!skillListMap.containsKey(svcSkillNm)) {
            skillListMap.put(svcSkillNm, svcSkillNm);

            EngineerSkill engSkill = objFactory.createEngineerSkillsEngineerSkill();
            SkillReference skillRef = objFactory.createSkillReference();

            skillRef.setDisplayString(svcSkillNm);
            skillRef.getContent().add(svcSkillNm);

            engSkill.setKey(skillRef);
            skills.getEngineerSkill().add(engSkill);

        }
    }
    private void setSvcAccsPmitNum(ResultSet rs, CSAAccessPermits accessPmt) throws SQLException {

        String svcAccsPmitNum = rs.getString(SVC_ACCS_PMIT_NUM);
        String svcAccsPmitDescTxt = rs.getString(SVC_ACCS_PMIT_DESC_TXT);

        if (!ZYPCommonFunc.hasValue(svcAccsPmitNum)) {
            return;
        }

        if (!svcAccsPmitNumMap.containsKey(svcAccsPmitNum)) {
            svcAccsPmitNumMap.put(svcAccsPmitNum, svcAccsPmitNum);

            CSAAccessPermitReference accessPmtRef = objFactory.createCSAAccessPermitReference();
            StringBuilder sb = new StringBuilder();
            sb.append(svcAccsPmitNum);
            sb.append("-");
            sb.append(svcAccsPmitDescTxt);
            accessPmtRef.getContent().add(sb.toString());

            accessPmt.getCSAAccessPermit().add(accessPmtRef);

        }
    }
    /**
     * Format Message Text to Mail.
     * @param messages Messages
     * @return Message Text
     */
    private String formatMessageToMail(Map<String, EZDMessageInfo> messages) {

        StringBuilder text = new StringBuilder();
        for (Map.Entry<String, EZDMessageInfo> entry : messages.entrySet()) {
            EZDMessageInfo ezdMessageInfo = entry.getValue();
            text.append(ezdMessageInfo.getCode());
            text.append(" ");
            text.append(ezdMessageInfo.getMessage());
            text.append(MAIL_LINE_SEPARATOR);
        }
        return text.toString();
    }
    private void insertErrorData() {
        List<SVC_BAT_ERR_LOGTMsg> inTMsgList = new ArrayList<SVC_BAT_ERR_LOGTMsg>();

        for (Map.Entry<String, EZDMessageInfo> entry : errorMessages.entrySet()) {
            inTMsgList.add(setCreateErrorValue(entry));
        }

        SVC_BAT_ERR_LOGTMsg[] inMsgArray;
        inMsgArray = new SVC_BAT_ERR_LOGTMsg[inTMsgList.size()];
        S21FastTBLAccessor.insert(inTMsgList.toArray(inMsgArray));
    }
    private SVC_BAT_ERR_LOGTMsg setCreateErrorValue(Map.Entry<String, EZDMessageInfo> entry) {
        SVC_BAT_ERR_LOGTMsg inParam = new SVC_BAT_ERR_LOGTMsg();
        EZDMessageInfo ezdMessageInfo = entry.getValue();

        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogPk, getSvcBatErrLogPk());
        ZYPEZDItemValueSetter.setValue(inParam.bizAppId, BATCH_PROGRAM_ID);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogTs, this.currentSystemTs);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_01, entry.getKey());
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_02, ezdMessageInfo.getCode());
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_01, "TECH_TOC_CD");
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_02, "XX_MSG_ID");
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrMsgTxt, ezdMessageInfo.getMessage());

        return inParam;
    }
    /**
     * This method will return SVC_BAT_ERR_LOG_SQ for
     * SVC_BAT_ERR_LOG_PK.
     * @return BigDecimal
     */
    private BigDecimal getSvcBatErrLogPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ");
    }

    private void callWmbApi(Engineer engInfo) throws SQLException {

        ExecuteMultipleOperations emo = objFactory.createExecuteMultipleOperations();
        emo.setOneTransaction(true);
        emo.setContinueOnError(true);

        StandardOperations standardOperations = objFactory.createStandardOperations();
        StandardOperation standardOperation = objFactory.createStandardOperation();
        standardOperation.setAction("UpdateOrCreate");
        W6RequestedProperties props = objFactory.createW6RequestedProperties();
        standardOperation.setRequestedProperties(props);

        BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
        bow.setObject(engInfo);

        standardOperation.setObject(bow);
        standardOperations.getOperation().add(standardOperation);
        emo.setOperations(standardOperations);

        try {
            // START 2017/01/04 K.Kitachi [QC#16316, MOD]
//            ExecuteMultipleOperationsResponse response = this.proxy.executeMultipleOperations(emo);
            ExecuteMultipleOperationsResponse response = this.proxy.executeMultipleOperations(INTERFACE_ID, emo);
            // END 2017/01/04 K.Kitachi [QC#16316, MOD]
            if (response != null) {
                this.totalNmlCount++;
            } else {
                this.totalErrCount++;
                addMessage(engInfo.getID(), NSBM0129E, engInfo.getID());
            }
        } catch (Exception e) {
            this.totalErrCount++;
            addMessage(engInfo.getID(), NSBM0129E, engInfo.getID());
            e.printStackTrace();
            return;
        } catch (Throwable t) {
            this.totalErrCount++;
            addMessage(engInfo.getID(), NSBM0129E, engInfo.getID());
            t.printStackTrace();
            return;
        }
    }

}
