/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB172001;

import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_CRAT_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_LAST_INVTY_RCV_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_MAIL;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_MAX_INVTY_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_MRP_ENBL_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_MRP_INFO_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_ORD_TAKE_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_ROP_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_SUPV_MAIL;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_TECH_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_TECH_TOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.COL_THIS_MTH_TOT_STD_COST_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.DT_SUBSTR_END;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.DT_SUBSTR_START;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.EMPTY;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.FROM_BIZID;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.LIKE_PREFIX;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.MMDDYYYY;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.MMDDYYYYHHMM;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.DB_YYYYMMDD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.MSG_ID_NPAM1172E;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.MSG_ID_NPAM1171E;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.MSG_ID_ZZM9010E;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_CURRENT_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_LOC_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_MESSAGE;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_ORD_TAKE_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_PRCH_REQ_REC_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_PSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_STK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_TECH_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_TECH_SWH;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_TECH_WH;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.PARAM_DTFORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.STR_DT_SUBSTR_END;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.STR_DT_SUBSTR_START;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.STR_LIKE_PREFIX;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.TABLE_MIN_MAX_SHUT_OFF_LOG;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.TABLE_MRP_INFO;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.WHITESPACE;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.WIDTH_CRATS_BY_PSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.WIDTH_DATE_ITEM_LAST_RECEIVED;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.WIDTH_ITEM_COST;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.WIDTH_ITEM_DESC;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.WIDTH_ITEM_NUMBER;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.WIDTH_MAX_VALUE;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.WIDTH_MIN_VALUE;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.WIDTH_ONHAND_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.WIDTH_PROGRAM_RUN_BY;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.WIDTH_SHUTOFF_DATE_TIME;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.YYYYMMDD;
import static com.canon.cusa.s21.batch.NPA.NPAB172001.constant.NPAB172001Constant.YYYYMMDDHHMMSSSSS;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.MIN_MAX_SHUT_OFF_LOGTMsg;
import business.db.MRP_INFOTMsg;

import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Min Max Planning Shut-Off - Technicians
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 07/13/2016   CITS            S.Endo          Create          
 * 01/08/2020   CITS            T.Wada          Update          QC#55359
 * 
 *</pre>
 */
public class NPAB172001 extends S21BatchMain {

    // -- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd;

    // -- Count of processing -------------------
    /** The number of cases : Select */
    private int selectCount;

    /** The number of cases : Insert */
    private int insertCount = 0;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Termination code */
    private TERM_CD termCd;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /**
     * @param args execution parameters
     */
    public static void main(String[] args) {
        new NPAB172001().executeBatch(NPAB172001.class.getSimpleName());

    }

    @Override
    protected void initRoutine() {
        // Initialization of variable
        selectCount = 0;
        termCd = TERM_CD.NORMAL_END;
        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();
        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();
    }

    @Override
    protected void mainRoutine() {
        List<Map<String, Object>> minMaxPlanningList = getMinMaxPlanningInfo();
        selectCount = minMaxPlanningList.size();
        String prevTechTocCode = "";
        Boolean firstLoop = true;
        List<Map<String, Object>> mailContentList = new ArrayList<Map<String, Object>>(selectCount);
        for (Map<String, Object> planningInfo : minMaxPlanningList) {
            if (!firstLoop) {
                if (!prevTechTocCode.equals((String) planningInfo.get(COL_TECH_TOC_CD))) {
                    doProcess(mailContentList);
                    mailContentList = new ArrayList<Map<String, Object>>(selectCount);
                }
            }
            mailContentList.add(planningInfo);
            prevTechTocCode = (String) planningInfo.get(COL_TECH_TOC_CD);
            firstLoop = false;
        }
        if (mailContentList.size() > 0) {
            doProcess(mailContentList);
        }
    }

    /**
     * main process to update DB and create Log,send mail group by
     * technician.
     * @param mailContentList
     */
    private void doProcess(List<Map<String, Object>> mailContentList) {
        List<MIN_MAX_SHUT_OFF_LOGTMsg> minMaxShutOffLogList = new ArrayList<MIN_MAX_SHUT_OFF_LOGTMsg>();
        String psnCd = EMPTY;
        String techNm = EMPTY;
        String whCd = EMPTY;
        String swhCd = EMPTY;
        for (Map<String, Object> planningInfo : mailContentList) {

            // get InventoryInfo
            BigDecimal invtryQty = getInventoryInfo(planningInfo);
            // create Log
            minMaxShutOffLogList.add(createShutOffLog(invtryQty, planningInfo));
            // update MRPInfo
            updateMRPInfo(planningInfo);
            psnCd = (String) planningInfo.get(COL_TECH_TOC_CD);
            techNm = (String) planningInfo.get(COL_TECH_NM);
            if (whCd.equals(EMPTY)) {
                whCd = (String) planningInfo.get(COL_RTL_WH_CD);
            }
            if (swhCd.equals(EMPTY)) {
                swhCd = (String) planningInfo.get(COL_RTL_SWH_CD);
            }
        }
        sendMail(createMailContents(minMaxShutOffLogList), psnCd, techNm, whCd, swhCd);
        commit();
        insertCount = insertCount + mailContentList.size();
    }

    @Override
    protected void termRoutine() {
        // Setting of termination code
        setTermState(termCd, selectCount, selectCount - insertCount, insertCount);
    }

    /**
     * get minMaxPlanning info
     * @return minMaxPlanning info list
     */
    private List<Map<String, Object>> getMinMaxPlanningInfo() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(STR_DT_SUBSTR_START, DT_SUBSTR_START);
        queryParam.put(STR_DT_SUBSTR_END, DT_SUBSTR_END);
        queryParam.put(PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_STS.OPEN);
        queryParam.put(STR_LIKE_PREFIX, LIKE_PREFIX);
        queryParam.put(PARAM_DTFORMAT,DB_YYYYMMDD);
        queryParam.put(PARAM_CURRENT_DT, ZYPDateUtil.getBatProcDate(glblCmpyCd));
        List<Map<String, Object>> result = this.ssmBatchClient.queryObjectList("getMinMaxPlanningInfo", queryParam);
        return result;
    }

    /**
     * get inventory info.
     * @param planningInfo
     * @return SUM(INVTY_QTY)
     */
    private BigDecimal getInventoryInfo(Map<String, Object> planningInfo) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(PARAM_INVTY_LOC_CD, (String) planningInfo.get(COL_INVTY_LOC_CD));
        queryParam.put(PARAM_MDSE_CD, (String) planningInfo.get(COL_MDSE_CD));
        queryParam.put(PARAM_ORD_TAKE_MDSE_CD, (String) planningInfo.get(COL_ORD_TAKE_MDSE_CD));
        if (null != planningInfo.get(COL_ORD_TAKE_MDSE_CD)) {
            queryParam.put(PARAM_ORD_TAKE_MDSE_CD, ZYPConstant.FLG_ON_Y);
        }
        queryParam.put(STR_LIKE_PREFIX, LIKE_PREFIX);
        queryParam.put(PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
        queryParam.put(PARAM_STK_STS_CD, STK_STS.GOOD);
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getInventory", queryParam);
        return result;
    }

    /**
     * create shutOff Log object and insert to MIN_MAX_SHUT_OFF_LOG.
     * @param invtryQty
     * @param planningInfo
     * @return shutOff Log object
     */
    private MIN_MAX_SHUT_OFF_LOGTMsg createShutOffLog(BigDecimal invtryQty, Map<String, Object> planningInfo) {
        MIN_MAX_SHUT_OFF_LOGTMsg tMsg = new MIN_MAX_SHUT_OFF_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.minMaxShutOffLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MIN_MAX_SHUT_OFF_LOG_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoPk, (BigDecimal) planningInfo.get(COL_MRP_INFO_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.techTocCd, (String) planningInfo.get(COL_TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.techNm, (String) planningInfo.get(COL_TECH_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String) planningInfo.get(COL_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, (String) planningInfo.get(COL_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, (String) planningInfo.get(COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescShortTxt, (String) planningInfo.get(COL_MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.ropQty, (BigDecimal) planningInfo.get(COL_ROP_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, (BigDecimal) planningInfo.get(COL_MAX_INVTY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.invtyQty, invtryQty);
        ZYPEZDItemValueSetter.setValue(tMsg.lastInvtyRcvDt, (String) planningInfo.get(COL_LAST_INVTY_RCV_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.tmthTotStdCostAmt, (BigDecimal) planningInfo.get(COL_THIS_MTH_TOT_STD_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.cratTs, ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMMSSSSS));
        String userInfo = profileService.getContextUserInfo().getUserId();
        if (userInfo.length() > WIDTH_CRATS_BY_PSN_CD) {
            ZYPEZDItemValueSetter.setValue(tMsg.cratByPsnCd, profileService.getContextUserInfo().getUserId().substring(0, WIDTH_CRATS_BY_PSN_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.cratByPsnCd, profileService.getContextUserInfo().getUserId());
        }
        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(MSG_ID_NPAM1172E, new String[] {TABLE_MIN_MAX_SHUT_OFF_LOG });
        }
        return tMsg;
    }

    /**
     * update MRP_Info to shutoff status.
     * @param planningInfo
     */
    private void updateMRPInfo(Map<String, Object> planningInfo) {
        MRP_INFOTMsg tMsg = new MRP_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoPk, (BigDecimal) planningInfo.get(COL_MRP_INFO_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
        tMsg = (MRP_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.mrpEnblFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.ropQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.ovrdMaxInvtyQty, BigDecimal.ZERO);
            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(MSG_ID_NPAM1171E, new String[] {TABLE_MRP_INFO });
            }
        }
    }

    /**
     * create mail contents from Log data group by whCd/swhCd.
     * @param minMaxShutOffLogList shutoff log object
     * @return
     */
    private String createMailContents(List<MIN_MAX_SHUT_OFF_LOGTMsg> minMaxShutOffLogList) {
        StringBuffer sb = new StringBuffer();
        String prevCode = "";

        Boolean firstLoop = true;
        for (MIN_MAX_SHUT_OFF_LOGTMsg tMsg : minMaxShutOffLogList) {
            if (!firstLoop) {
                if (!prevCode.equals(tMsg.rtlWhCd.getValue().concat(tMsg.rtlSwhCd.getValue()))) {
                    sb.append(S21Mail.LINE_SEPARATOR);
                    sb.append(S21Mail.LINE_SEPARATOR);
                    sb.append("Technician Warehouse     :   ").append(tMsg.rtlWhCd.getValue()).append(S21Mail.LINE_SEPARATOR);
                    sb.append("Technician Sub Warehouse :   ").append(tMsg.rtlSwhCd.getValue()).append(S21Mail.LINE_SEPARATOR);
                    sb.append(S21Mail.LINE_SEPARATOR);
                    sb.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------").append(
                            S21Mail.LINE_SEPARATOR);
                    sb.append("Item             Item                           Min        Max        On-Hand    Date Item      Item       Shut-Off         Program").append(S21Mail.LINE_SEPARATOR);
                    sb.append("Number           Description                    Value      Value      Quantity   Last Received  Cost       Date/Time        Run By").append(S21Mail.LINE_SEPARATOR);
                    sb.append("======================================================================================================================================================================================").append(
                            S21Mail.LINE_SEPARATOR);
                }
            }
            // Item Number
            sb.append(ZYPCommonFunc.rightPad(tMsg.mdseCd.getValue(), WIDTH_ITEM_NUMBER, WHITESPACE));
            sb.append(WHITESPACE);
            // Item Description
            if (tMsg.mdseDescShortTxt.getValue().length() >= WIDTH_ITEM_DESC) {
                sb.append(ZYPCommonFunc.rightPad(tMsg.mdseDescShortTxt.getValue().substring(0, WIDTH_ITEM_DESC), WIDTH_ITEM_DESC, WHITESPACE));
            } else {
                sb.append(ZYPCommonFunc.rightPad(tMsg.mdseDescShortTxt.getValue(), WIDTH_ITEM_DESC, WHITESPACE));
            }

            sb.append(WHITESPACE);
            // Min Value
            sb.append(ZYPCommonFunc.leftPad(replaceSpaceIsNull(tMsg.ropQty.getValue()), WIDTH_MIN_VALUE, WHITESPACE));
            sb.append(WHITESPACE);
            // Max Value
            sb.append(ZYPCommonFunc.leftPad(replaceSpaceIsNull(tMsg.maxInvtyQty.getValue()), WIDTH_MAX_VALUE, WHITESPACE));
            sb.append(WHITESPACE);
            // On-Hand Qty
            sb.append(ZYPCommonFunc.leftPad(replaceSpaceIsNull(tMsg.invtyQty.getValue()), WIDTH_ONHAND_QTY, WHITESPACE));
            sb.append(WHITESPACE);
            // Date Item Last Received
            SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
            sb.append(ZYPCommonFunc.rightPad(ZYPDateUtil.convertFormat(tMsg.lastInvtyRcvDt.getValue(), YYYYMMDD, MMDDYYYY, '/'), WIDTH_DATE_ITEM_LAST_RECEIVED, WHITESPACE));
            sb.append(WHITESPACE);
            // Item Cost
            sb.append(ZYPCommonFunc.leftPad(replaceSpaceIsNull(tMsg.tmthTotStdCostAmt.getValue()), WIDTH_ITEM_COST, WHITESPACE));
            sb.append(WHITESPACE);
            // Shut Off Date/Time
            Date dt = null;
            sdf = new SimpleDateFormat(YYYYMMDDHHMMSSSSS);
            try {
                dt = sdf.parse(tMsg.cratTs.getValue());
            } catch (ParseException e) {
                throw new S21AbendException(MSG_ID_ZZM9010E, new String[] {COL_CRAT_TS });
            }
            sdf = new SimpleDateFormat(MMDDYYYYHHMM);
            // ZYPDateUtil.convertFormat(tMsg.cratTs.getValue(),
            // YYYYMMDDHHMMSSSSS, MMDDYYYYHHMM, '/');
            sb.append(ZYPCommonFunc.rightPad(sdf.format(dt), WIDTH_SHUTOFF_DATE_TIME, WHITESPACE));
            sb.append(WHITESPACE);
            // Program Run By
            sb.append(ZYPCommonFunc.rightPad(tMsg.cratByPsnCd.getValue(), WIDTH_PROGRAM_RUN_BY, WHITESPACE));
            sb.append(S21Mail.LINE_SEPARATOR);
            prevCode = tMsg.rtlWhCd.getValue().concat(tMsg.rtlSwhCd.getValue());
            firstLoop = false;
        }
        return sb.toString();

    }

    /**
     * search mail address by personCd and send mail to
     * technician/supervisor
     * @param message mailbody
     * @param psnCd personCd to find mailAddress
     * @param techNm technician name
     * @param whCd warehouse cd
     * @param swhCd subwarehouse vcode
     */
    private void sendMail(String message, String psnCd, String techNm, String whCd, String swhCd) {
        // get mail Info
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(PARAM_PSN_CD, psnCd);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getMailInfo", queryParam);

        // QC#55359 Add Start
        if (result == null) {
            // Send To IT
            String msgId = "NPAM1648E";
            String[] msgParam = {replaceSpaceIsNull(psnCd), replaceSpaceIsNull(techNm), replaceSpaceIsNull(whCd), replaceSpaceIsNull(swhCd)};
            sendMailToIt(msgId, msgParam);
            return;
        }
        // QC#55359 Add End
        
        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, FROM_BIZID);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();
        S21Mail mail = new S21Mail(glblCmpyCd);
        if (fromAddrList.size() > 0) {
            mail.setFromAddress(fromAddrList.get(0));
            List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress>();
            if (null != result.get(COL_MAIL)) {
                toAddrList.add(new S21MailAddress(glblCmpyCd, (String) result.get(COL_MAIL)));
            }
            if (null != result.get(COL_SUPV_MAIL)) {
                toAddrList.add(new S21MailAddress(glblCmpyCd, (String) result.get(COL_SUPV_MAIL)));
            }
            if (!toAddrList.isEmpty()) {
                mail.setToAddressList(toAddrList);
                S21MailTemplate tmpl = new S21MailTemplate(glblCmpyCd, TEMPLATE_ID);
                if (ZYPCommonFunc.hasValue(tmpl.getSubject())) {
                    tmpl.setTemplateParameter(PARAM_TECH_NM, techNm);
                    tmpl.setTemplateParameter(PARAM_TECH_WH, whCd);
                    tmpl.setTemplateParameter(PARAM_TECH_SWH, swhCd);
                    tmpl.setTemplateParameter(PARAM_MESSAGE, message);
                    mail.setMailTemplate(tmpl);
                    mail.postMail();
                }
            }
        }
    }

    /**
     * replace if param is null, then return whitespace(" "), else
     * return param.toPlainString().
     * @param obj
     * @return
     */
    private String replaceSpaceIsNull(BigDecimal obj) {
        if (obj == null) {
            return WHITESPACE;
        } else {
            return obj.toPlainString();
        }
    }
    // QC#55359 Add Start
    /**
     * sendMailToIt
     * @param msgId
     * @param msgParam
     */
    private void sendMailToIt(String msgId, String[] msgParam) {
        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();
        errMsgList.add(mailParam);

        NLXMailSend mail = new NLXMailSend(glblCmpyCd);
        mail.send("NLAB1720", errMsgList);

    }
    /**
     * replaceSpaceIsNull
     * @param obj
     * @return
     */
    private String replaceSpaceIsNull(String obj) {
        if (obj == null) {
            return "";
        } else {
            return obj;
        }
    }
    // QC#55359 Add End
}
