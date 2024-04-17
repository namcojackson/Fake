package com.canon.cusa.s21.batch.NLC.NLCB004001;

import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.BIND_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.BIND_LOC_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.BIND_PHYS_INVTY_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.BIND_PHYS_INVTY_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.BLANK;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.COMMA;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.CRLF;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.DATE_FORMAT_TS_DB_INPUT;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.DB_COLUMN_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.DB_COLUMN_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.DB_COLUMN_PHYS_INVTY_CTRL_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.DB_COLUMN_PHYS_INVTY_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.DB_COLUMN_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.INDENT;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MAIL_DATE_FORMAT_MDY;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MAIL_DETAIL_HEADER_FROM;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MAIL_DETAIL_HEADER_PI_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MAIL_DETAIL_HEADER_RWH;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MAIL_DETAIL_HEADER_THRU;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MAIL_FORMAT_SIZE_10;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MAIL_FORMAT_SIZE_15;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MAIL_FORMAT_SIZE_20;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MSG_ID_NLCM0174W;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MSG_ID_NLGM0044E;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MSG_ID_NLGM0046E;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MSG_ID_NLGM0048E;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MSG_ID_ZZM9000E;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MSG_STR_COMP_CODE;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.MSG_STR_WH_PI_LOC_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.SPACE;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.SQL_STID_GET_PI_LIST;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.TBL_ID_PHYS_INVTY_CTRL;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.VAR_CHAR_CONST_WH_PI_LOC_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.ZERO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.PHYS_INVTY_CTRLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Batch Program Class for PI(Physical Inventory) Start Job
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 02/29/2016   CITS            N.Akaishi       Create          n/a
 * 10/25/2016   CITS            T.hakodate      Update          QC#14632
 * 02/21/2018   CITS            K.Ogino         Update          QC#24046
 * 09/21/2018   CITS            K.Ogino         Update          QC#28191
 * 
 *</pre>
 */
public class NLCB004001 extends S21BatchMain {

    // -- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd;

    // -- Count of processing -------------------
    /** The number of cases : Select Count */
    private int selectCount;

    /** The number of cases : Normal Record Count */
    private int normalRecCount;

    /** The number of case : Error Record Count */
    private int errorRecCount;

    // -- Other Internal Variable ---------------
    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Termination code */
    private TERM_CD termCd;

    /** Batch Proc Date */
    private String batProcDate;

    /** Error Mail */
    private NLCB004001Mail errMail;

    /** whPilocTpCd */
    private String whPilocTpCd;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    @Override
    protected void initRoutine() {

        // Initialization of variable
        termCd = TERM_CD.ABNORMAL_END;
        selectCount = 0;
        errorRecCount = 0;
        normalRecCount = 0;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // Check on input parameter
        checkParameter();

        this.errMail = new NLCB004001Mail(this.glblCmpyCd);
        this.setMailHeaderLine();
    }

    @Override
    protected void mainRoutine() {
        // Get operation date.
        // QC#24046
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        batProcDate = ZYPDateUtil.addDays(salesDate, 1);

        PreparedStatement stmtPiList = null;
        ResultSet rsPiList = null;

        try {
            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
            queryParam.put(BIND_PHYS_INVTY_DT, batProcDate);
            queryParam.put(BIND_PHYS_INVTY_STS_CD, PHYS_INVTY_STS.SCHEDULED);

            String[] whLocTpCdArray = whPilocTpCd.split(COMMA);
            List<String> whLocTpCdList = new ArrayList<String>(whLocTpCdArray.length);
            for (String whLocTpCd : whLocTpCdArray) {
                whLocTpCdList.add(whLocTpCd);
            }
            queryParam.put(BIND_LOC_TP_CD, whLocTpCdList);

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            // Get PI List
            stmtPiList = this.ssmLLClient.createPreparedStatement(SQL_STID_GET_PI_LIST, queryParam, ssmEexcParam);
            rsPiList = stmtPiList.executeQuery();

            // Loop By PI Control PK
            int cnt = 0;
            List<String> errorRtlWhCdList = new ArrayList<String>();

            PHYS_INVTY_CTRLTMsg keyParam = null;

            while (rsPiList.next()) {

                cnt++;
                selectCount++;

                boolean existsOpenPi = checkOpenPi(rsPiList);

                keyParam = new PHYS_INVTY_CTRLTMsg();
                ZYPEZDItemValueSetter.setValue(keyParam.physInvtyCtrlPk, rsPiList.getBigDecimal(DB_COLUMN_PHYS_INVTY_CTRL_PK));
                ZYPEZDItemValueSetter.setValue(keyParam.glblCmpyCd, glblCmpyCd);

                if ((batProcDate.compareTo(rsPiList.getString(DB_COLUMN_EFF_FROM_DT)) >= ZERO && batProcDate.compareTo(rsPiList.getString(DB_COLUMN_EFF_THRU_DT)) <= ZERO) && !existsOpenPi) {
                    normalRecCount++;

                    // Update PI Control (Open)
                    PHYS_INVTY_CTRLTMsg target = findPhysInvtyCtrl(keyParam);
                    ZYPEZDItemValueSetter.setValue(target.physInvtyStsCd, PHYS_INVTY_STS.OPEN);
                    ZYPEZDItemValueSetter.setValue(target.physInvtyStsNm, ZYPCodeDataUtil.getName(PHYS_INVTY_STS.class, glblCmpyCd, PHYS_INVTY_STS.OPEN));
                    ZYPEZDItemValueSetter.setValue(target.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.COUNTING);
                    ZYPEZDItemValueSetter.setValue(target.physInvtyStartTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT_TS_DB_INPUT));
                    updatePhysInvtyCtrl(target);

                } else { // contains PI Error
                    errorRecCount++;

                    String rtlWhCd = rsPiList.getString(DB_COLUMN_RTL_WH_CD);
                    if (!errorRtlWhCdList.contains(rtlWhCd)) {
                        errorRtlWhCdList.add(rsPiList.getString(DB_COLUMN_RTL_WH_CD));
                        // set Error PI info
                        this.setMailDetailLine(rsPiList.getString(DB_COLUMN_PHYS_INVTY_NUM), rsPiList.getString(DB_COLUMN_RTL_WH_CD), rsPiList.getString(DB_COLUMN_EFF_FROM_DT), rsPiList.getString(DB_COLUMN_EFF_THRU_DT));
                    }

                    // Update PI Control (Cancel)
                    PHYS_INVTY_CTRLTMsg target = findPhysInvtyCtrl(keyParam);
                    // QC#28191
                    ZYPEZDItemValueSetter.setValue(target.physInvtyStsCd, PHYS_INVTY_STS.CANCELLED);
                    ZYPEZDItemValueSetter.setValue(target.physInvtyStsNm, ZYPCodeDataUtil.getName(PHYS_INVTY_STS.class, glblCmpyCd, PHYS_INVTY_STS.CANCELLED));
                    ZYPEZDItemValueSetter.setValue(target.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.PI_CANCELED);
                    ZYPEZDItemValueSetter.setValue(target.adjSubmtTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT_TS_DB_INPUT));
                    updatePhysInvtyCtrl(target);

                    S21InfoLogOutput.println(MSG_ID_NLCM0174W, new String[] {rsPiList.getString(DB_COLUMN_PHYS_INVTY_NUM), rsPiList.getString(DB_COLUMN_RTL_WH_CD), rsPiList.getString(DB_COLUMN_EFF_FROM_DT),
                            rsPiList.getString(DB_COLUMN_EFF_THRU_DT) });
                }
            }

            if (ZERO < errorRecCount) {
                // send err mail
                this.errMail.send();
            }
            commit();
            termCd = TERM_CD.NORMAL_END;

        } catch (SQLException e) {
            sqlExceptionHandler(e);
            rollback();
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtPiList, rsPiList);
        }
    }

    /**
     * @param rsPiList
     * @return
     * @throws SQLException
     */
    private boolean checkOpenPi(ResultSet rsPiList) throws SQLException {

        boolean isExists = false;

        HashMap<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rtlWhCd", rsPiList.getString("RTL_WH_CD"));
        ssmParam.put("rtlSwhCd", rsPiList.getString("RTL_SWH_CD"));
        ssmParam.put("physInvtyCtrlPk", rsPiList.getString("PHYS_INVTY_CTRL_PK"));
        ssmParam.put("physInvtyStsCd", PHYS_INVTY_STS.OPEN);

        if ((String) ssmBatchClient.queryObject("checkOpenPi", ssmParam) == null) {

            isExists = false;

        } else {

            isExists = true;
        }

        return isExists;

    }

    /**
     * <pre>
     * find PHYS_INVTY_CTRL for Update
     * @param keyParam PHYS_INVTY_CTRLTMsg Search Key
     * @return PHYS_INVTY_CTRLTMsg search result
     * </pre>
     **/
    private PHYS_INVTY_CTRLTMsg findPhysInvtyCtrl(PHYS_INVTY_CTRLTMsg keyParam) {
        PHYS_INVTY_CTRLTMsg target = (PHYS_INVTY_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(keyParam);
        if (target == null) {
            throw new S21AbendException(MSG_ID_NLGM0044E, new String[] {TBL_ID_PHYS_INVTY_CTRL, DB_COLUMN_PHYS_INVTY_CTRL_PK, keyParam.physInvtyCtrlPk.getValue().toPlainString() });
        } else {
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(target.getReturnCode())) {
                throw new S21AbendException(MSG_ID_NLGM0048E, new String[] {TBL_ID_PHYS_INVTY_CTRL, DB_COLUMN_PHYS_INVTY_CTRL_PK, keyParam.physInvtyCtrlPk.getValue().toPlainString() });
            }
        }
        return target;
    }

    /**
     * <pre>
     * update  PHYS_INVTY_CTRL
     * @param updateParam PHYS_INVTY_CTRLTMsg Search Key
     * </pre>
     **/
    private void updatePhysInvtyCtrl(PHYS_INVTY_CTRLTMsg updateParam) {
        EZDTBLAccessor.update(updateParam);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateParam.getReturnCode())) {
            throw new S21AbendException(MSG_ID_NLGM0046E, new String[] {TBL_ID_PHYS_INVTY_CTRL, DB_COLUMN_PHYS_INVTY_CTRL_PK, updateParam.physInvtyCtrlPk.getValue().toPlainString() });
        }
    }

    @Override
    protected void termRoutine() {

        // Setting of termination code
        setTermState(termCd, normalRecCount, errorRecCount, selectCount);

    }

    /**
     * <pre>
     * Check processing of input parameters.
     * </pre>
     */
    private void checkParameter() {

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_STR_COMP_CODE });
        }

        // Warehouse LocTpCd
        whPilocTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_WH_PI_LOC_TP_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(whPilocTpCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_STR_WH_PI_LOC_TP_CD });
        }
    }

    /**
     * <pre>
     * This main method is called from batch shell
     * @param args arguments
     * </pre>
     */
    public static void main(String[] args) {
        // Initialization of S21BatchMain
        new NLCB004001().executeBatch(NLCB004001.class.getSimpleName());
    }

    /**
     * <pre>
     * Set Mail Header Line
     * @param piNum String PI#
     * @param rwhCd String Retail Warehouse Code
     * @param effFromDt String 
     * @param effThruDt String 
     * </pre>
     */
    private void setMailHeaderLine() {
        // " PI# RWH FROM THRU
        StringBuilder errDetailMap = new StringBuilder(BLANK);
        errDetailMap.append(INDENT);
        errDetailMap.append(errMail.formatString(MAIL_DETAIL_HEADER_PI_NUM, MAIL_FORMAT_SIZE_15));
        errDetailMap.append(SPACE);
        errDetailMap.append(errMail.formatString(MAIL_DETAIL_HEADER_RWH, MAIL_FORMAT_SIZE_20));
        errDetailMap.append(SPACE);
        errDetailMap.append(errMail.formatString(MAIL_DETAIL_HEADER_FROM, MAIL_FORMAT_SIZE_10));
        errDetailMap.append(SPACE);
        errDetailMap.append(errMail.formatString(MAIL_DETAIL_HEADER_THRU, MAIL_FORMAT_SIZE_10));
        errDetailMap.append(CRLF);
        this.errMail.setErrDetailMap(errDetailMap);
    }

    /**
     * <pre>
     * Set Mail Detail Line
     * @param piNum String PI#
     * @param rwhCd String Retail Warehouse Code
     * @param effFromDt String 
     * @param effThruDt String 
     * </pre>
     */
    private void setMailDetailLine(String piNum, String rwhCd, String effFromDt, String effThruDt) {
        // add Error Message Line
        // "    PI#:000000000000000 Reason:xxxxxxx"
        // " [PI#] [RWH] [FROM] [THRU]
        StringBuilder errDetailMap = new StringBuilder(BLANK);
        errDetailMap.append(INDENT);
        errDetailMap.append(errMail.formatString(piNum, MAIL_FORMAT_SIZE_15));
        errDetailMap.append(SPACE);
        errDetailMap.append(errMail.formatString(rwhCd, MAIL_FORMAT_SIZE_20));
        errDetailMap.append(SPACE);
        errDetailMap.append(errMail.formatString(dateFormat(effFromDt, ZYPDateUtil.TYPE_YYYYMMDD, MAIL_DATE_FORMAT_MDY), MAIL_FORMAT_SIZE_10));
        errDetailMap.append(SPACE);
        errDetailMap.append(errMail.formatString(dateFormat(effThruDt, ZYPDateUtil.TYPE_YYYYMMDD, MAIL_DATE_FORMAT_MDY), MAIL_FORMAT_SIZE_10));
        errDetailMap.append(CRLF);
        this.errMail.setErrDetailMap(errDetailMap);
    }

    /**
     * Date format.
     * @param date target string
     * @param inFormat input format
     * @param outFormat output format
     * @return formatted string
     */
    private static String dateFormat(String date, String inFormat, String outFormat) {

        if (!ZYPCommonFunc.hasValue(date)) {
            return null;
        }
        return ZYPDateUtil.DateFormatter(date, inFormat, outFormat);
    }
}
