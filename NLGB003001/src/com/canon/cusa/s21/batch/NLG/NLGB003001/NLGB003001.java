/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB003001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.ABC_ANLS_RSLTTMsg;
import business.db.WMS_INBD_ITEM_SER_WRKTMsg;
import business.db.WMS_INBD_ITEM_UPC_WRKTMsg;
import business.db.WMS_INBD_ITEM_WRKTMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.api.NLG.NLGC002001.NLGC002001;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ITEM_DNLD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmIntegerResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * MDSE to WMS
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/14/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 * 08/24/2016   CITS            T.Wada          Update          QC#13753
 * 07/09/2018   CITS            T.Hakodate      Update          QC#22577
 * 01/11/2023   Hitachi         T.Kuroda        Update          QC#60692
 *</pre>
 */
public class NLGB003001 extends S21BatchMain implements NLGB003001Constant {

    /** Total Commit Count */
    private int totalCommitCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Terminate Cord */
    private TERM_CD termCd = null;

    /** User Profile */
    private S21UserProfileService profile = null;

    /** SQL Access Parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Execute Parameter (VAR_USER2) : Execute Mode (1:Daily 2:Night) */
    private String mode = null;

    /** Error Message List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** Date Time Pattern */
    private static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";
    
    /** Mode: Night **/
    private static final String NIGHT_MODE = "2";
    
    // QC#22577 ADD START
    /** Var Char Constt **/
    private String[] dsHistRptTblNmList;
    // QC#22577 ADD END
    


    @Override
    protected void initRoutine() {

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        totalCommitCount = 0;
        totalErrCount = 0;
        termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();
        profile = S21UserProfileServiceFactory.getInstance().getService();
        mode = S21BatchUtil.getUserVariable2();
    }

    @Override
    protected void mainRoutine() {

        glblCmpyCd = profile.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_GLBL_CMPY_CD });
        }

        String whGpCd = S21BatchUtil.getUserVariable1();
        if (!ZYPCommonFunc.hasValue(whGpCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_WH_GRP_CD });
        }
        // QC#22577 ADD START
        dsHistRptTblNmList = ZYPCodeDataUtil.getVarCharConstValue("WMS_SEND_COL_MDSE_HIST_TBL", glblCmpyCd).split(",");
        // QC#22577 ADD END
        try {
            String[] trgtWhCdAry = NLGC001001.getTrgtWhCd(glblCmpyCd, whGpCd);
            if (trgtWhCdAry != null) {
                // For MDSE
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
                queryParam.put("wmsItemDnldStsCd", WMS_ITEM_DNLD_STS.COMPLEATED);
                queryParam.put("mode", mode);

                Integer result = (Integer) ssmBatchClient.queryObject("getMdseHistInfo", queryParam, new MdseToItemWrk());
                totalCommitCount = result.intValue();
            } else {
                outputErr(NLGM0047E, new String[] {whGpCd });
            }
        } finally {
            if (!errMsgList.isEmpty()) {
                NLXMailSend mailSender = new NLXMailSend(glblCmpyCd);
                mailSender.send(BUSINESS_ID, errMsgList);
                commit();
                termCd = TERM_CD.WARNING_END;
            }
        }
    }

    @Override
    protected void termRoutine() {

        int totalCount = totalCommitCount + totalErrCount;
        setTermState(termCd, totalCommitCount, totalErrCount, totalCount);
    }

    /**
     * This main method is called from batch shell
     * @param args arguments
     */
    public static void main(String[] args) {

        // S21BatchMain format
        new NLGB003001().executeBatch(NLGB003001.class.getSimpleName());
    }

    /**
     * MDSE to Item Work Tables.
     */
    private class MdseToItemWrk extends S21SsmIntegerResultSetHandlerSupport {

        /**
         * Default Constructor.
         */
        public MdseToItemWrk() {
            // no process.
        }

        /**
         * Do process MDSE to WMS.
         * @param rs ResultSet
         * @throws SQLException
         * @return result
         */
        public Integer doProcessQueryResult(ResultSet rs) throws SQLException {

            // Commit Count
            int rtrnCommitCount = 0;
            int commitCount = 0;

            String lastMdseCd = ""; // For key break.
            WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT = null;
            List<WMS_INBD_ITEM_UPC_WRKTMsg> wmsInbdItemUpcWrkList = new ArrayList<WMS_INBD_ITEM_UPC_WRKTMsg>();
            List<WMS_INBD_ITEM_SER_WRKTMsg> wmsInbdItemSerWrkList = new ArrayList<WMS_INBD_ITEM_SER_WRKTMsg>();

            while (rs.next()) {
                String whCd = rs.getString(NLGC002001.COL_WML_WH_CD);
                String mdseCd = rs.getString(NLGC002001.COL_WML_MDSE_CD);
                String ezuptime = rs.getString(NLGC002001.COL_WML_EZUPTIME);

                String invtyCatgCountCd = null;
                BigDecimal cycleCntFreqNum = null;

                // QC#22577 ADD START
                // check DS_HIST_RPT_INFO
                if (!needToSendWms(glblCmpyCd, mdseCd, ezuptime, whCd)) {

                    // Clear data.
                    lastMdseCd = mdseCd;
                    wmsInbdItemWrkT = null;
                    wmsInbdItemUpcWrkList.clear();
                    wmsInbdItemSerWrkList.clear();

                    continue;
                }
                // QC#22577 ADD END

                // If lastMdseCd = mdseCd then use previous data.
                if (!lastMdseCd.equals(mdseCd)) {
                    // Data check
                    if (!ZYPCommonFunc.hasValue(rs.getString(NLGC002001.COL_M_MDSE_CD))) {
                        outputErr(NLGM0044E, new String[] {NLGC002001.TBL_MDSE, NLGC002001.COL_MDSE_CD, mdseCd });
                        continue;
                    }
                    if (!ZYPCommonFunc.hasValue(rs.getString(NLGC002001.COL_IPC_INTG_PROD_CATG_CD))) {
                        outputErr(NLGM0044E, new String[] {NLGC002001.TBL_INTG_PROD_CATG, NLGC002001.COL_INTG_PROD_CATG_CD, rs.getString(NLGC002001.COL_IPC_INTG_PROD_CATG_CD) });
                        continue;
                    }
                    if (ZYPCommonFunc.hasValue(rs.getString(NLGC002001.COL_M_FRT_CLS_CD)) && !ZYPCommonFunc.hasValue(rs.getString(NLGC002001.COL_FC_FRT_CLS_CD))) {
                        outputErr(NLGM0044E, new String[] {NLGC002001.TBL_FRT_CLS, NLGC002001.COL_FRT_CLS_CD, rs.getString(NLGC002001.COL_M_FRT_CLS_CD) });
                        continue;
                    }

                    // Clear data.
                    lastMdseCd = mdseCd;
                    wmsInbdItemWrkT = null;
                    wmsInbdItemUpcWrkList.clear();
                    wmsInbdItemSerWrkList.clear();

                    NLGC002001 algc002001 = new NLGC002001(glblCmpyCd);
                    wmsInbdItemWrkT = algc002001.createWmsInbdItemInfo(rs, wmsInbdItemUpcWrkList, wmsInbdItemSerWrkList, NLGC002001.VAL_INTFC_REC_ACT_CD_CHNG);
                }

                BigDecimal abcNewAnlsRsltPk = getPrimaryKeyForNewAbcAnlsRslt(glblCmpyCd, mdseCd, whCd);

                if (compareAbcAnlsRslt(abcNewAnlsRsltPk, ezuptime)) {
                    // No change
                    commitCount++;
                    continue;
                } else {
                    if (abcNewAnlsRsltPk != null) {
                        // New ABC Result
                        ABC_ANLS_RSLTTMsg abcAnlsRslt = new ABC_ANLS_RSLTTMsg();
                        ZYPEZDItemValueSetter.setValue(abcAnlsRslt.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(abcAnlsRslt.abcAnlsRsltPk, abcNewAnlsRsltPk);

                        abcAnlsRslt = (ABC_ANLS_RSLTTMsg) EZDTBLAccessor.findByKey(abcAnlsRslt);

                        invtyCatgCountCd = abcAnlsRslt.abcAnlsClsTagCd.getValue();
                        cycleCntFreqNum = abcAnlsRslt.cycleCntFreqDaysAot.getValue();
                    } else {
                        // Latest ABC Result
                        Map<String, Object> result = getLatestAbcClassTagAndCycleCount(glblCmpyCd, mdseCd, whCd);

                        if (result != null) {
                            invtyCatgCountCd = (String) result.get("ABC_ANLS_CLS_TAG_CD");
                            cycleCntFreqNum = (BigDecimal) result.get("CYCLE_CNT_FREQ_DAYS_AOT");
                        }
                    }
                }

                
                // Register to tables.
                // QC#22798 ADD START
                if (wmsInbdItemWrkT != null) {
                    boolean isRegistWrkTbl = insertItemWrkTbl(wmsInbdItemWrkT, wmsInbdItemUpcWrkList, wmsInbdItemSerWrkList, whCd, invtyCatgCountCd, cycleCntFreqNum);
                    boolean updateAbcAnlsRslt = updateAbcAnlsRsltTbl(glblCmpyCd, abcNewAnlsRsltPk);
                    String updateWmsMdseErrMsgCd = null;
                    if (isRegistWrkTbl && updateAbcAnlsRslt) {
                        updateWmsMdseErrMsgCd = NLGC002001.updateWmsMdseList(glblCmpyCd, whCd, mdseCd, WMS_ITEM_DNLD_STS.COMPLEATED, ezuptime);
                    }

                    if (isRegistWrkTbl && updateAbcAnlsRslt && !ZYPCommonFunc.hasValue(updateWmsMdseErrMsgCd)) {
                        // commit
                        commitCount++;
                        if (commitCount >= getCommitCount()) {
                            commit();
                            rtrnCommitCount += commitCount;
                            commitCount = 0;
                        }
                    } else {
                        // rollback
                        rollback();
                        commitCount = 0;
                        totalErrCount++;

                        if (NLGM0044E.equals(updateWmsMdseErrMsgCd)) {
                            throw new S21AbendException(NLGM0044E, new String[] {NLGC002001.TBL_WMS_MDSE_LIST //
                                    , NLXCMsgHelper.toListedString(NLGC002001.COL_GLBL_CMPY_CD, NLGC002001.COL_WH_CD, NLGC002001.COL_WMS_MDSE_CD) //
                                    , NLXCMsgHelper.toListedString(glblCmpyCd, whCd, mdseCd) });
                        } else if (NLGM0046E.equals(updateWmsMdseErrMsgCd)) {
                            throw new S21AbendException(NLGM0046E, new String[] {NLGC002001.TBL_WMS_MDSE_LIST //
                                    , NLXCMsgHelper.toListedString(NLGC002001.COL_GLBL_CMPY_CD, NLGC002001.COL_WH_CD, NLGC002001.COL_WMS_MDSE_CD) //
                                    , NLXCMsgHelper.toListedString(glblCmpyCd, whCd, mdseCd) });
                        }
                    }
                }
                // QC#22798 ADD END
            }

            commit();
            rtrnCommitCount += commitCount;
            return rtrnCommitCount;
        }

        /**
         * Insert into following tables. WMS_INBD_ITEM_WRK,
         * WMS_INBD_ITEM_UPC_WRK, WMS_INBD_ITEM_SER_WRK.
         * @param wmsInbdItemWrkT WMS_INBD_ITEM_WRK
         * @param wmsInbdItemUpcWrkList WMS_INBD_ITEM_UPC_WRK Data
         * List
         * @param wmsInbdItemSerWrkList WMS_INBD_ITEM_SER_WRK Data
         * List
         * @param whCd WarehouseCode
         * @return regist result
         */
        private boolean insertItemWrkTbl(WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT, List<WMS_INBD_ITEM_UPC_WRKTMsg> wmsInbdItemUpcWrkList, 
                List<WMS_INBD_ITEM_SER_WRKTMsg> wmsInbdItemSerWrkList, String whCd, String invtyCatgCountCd, BigDecimal cycleCntFreqNum) {

            // WMS_INBD_ITEM_WRK
            // Set common data.
            BigDecimal toWmsDataIfSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsSqNum, toWmsDataIfSq);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.whCd, whCd);
            // Get Primary Sequence
            BigDecimal wmsInbdItemWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_ITEM_WRK_SQ);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsInbdItemWrkPk, wmsInbdItemWrkPk);

            //set AbcInfo
            if (ZYPCommonFunc.hasValue(invtyCatgCountCd)) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.invtyCatgCountCd, invtyCatgCountCd);
            }

            if (ZYPCommonFunc.hasValue(cycleCntFreqNum)) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.cycleCntFreqNum, cycleCntFreqNum);
            }

            EZDTBLAccessor.insert(wmsInbdItemWrkT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdItemWrkT.getReturnCode())) {
                throw new S21AbendException(NLGM0007E, new String[] {NLGC002001.TBL_WMS_INBD_ITEM_WRK //
                        , NLXCMsgHelper.toListedString(NLGC002001.COL_GLBL_CMPY_CD, NLGC002001.COL_WH_CD, NLGC002001.COL_WMS_ITEM_CD) //
                        , NLXCMsgHelper.toListedString(wmsInbdItemWrkT.glblCmpyCd, wmsInbdItemWrkT.whCd, wmsInbdItemWrkT.wmsItemCd) });
            }

            // WMS_INBD_ITEM_UPC_WRK
            for (WMS_INBD_ITEM_UPC_WRKTMsg wmsInbdItemUpcWrkT : wmsInbdItemUpcWrkList) {
                // Set common data.
                ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.whCd, whCd);
                ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsSqNum, toWmsDataIfSq);
                // Get Primary Sequence
                BigDecimal wmsInbdItemUpcWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_ITEM_UPC_WRK_SQ);
                ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsInbdItemUpcWrkPk, wmsInbdItemUpcWrkPk);

                EZDTBLAccessor.insert(wmsInbdItemUpcWrkT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdItemUpcWrkT.getReturnCode())) {
                    throw new S21AbendException(NLGM0007E, new String[] {NLGC002001.TBL_WMS_INBD_ITEM_UPC_WRK //
                            , NLXCMsgHelper.toListedString(NLGC002001.COL_GLBL_CMPY_CD, NLGC002001.COL_WH_CD, NLGC002001.COL_WMS_ITEM_CD, NLGC002001.COL_WMS_UPC_CD) //
                            , NLXCMsgHelper.toListedString(wmsInbdItemUpcWrkT.glblCmpyCd, wmsInbdItemUpcWrkT.whCd, wmsInbdItemUpcWrkT.wmsItemCd, wmsInbdItemUpcWrkT.wmsUpcCd) });
                }
            }

            // WMS_INBD_ITEM_SER_WRK
            for (WMS_INBD_ITEM_SER_WRKTMsg wmsInbdItemSerWrkT : wmsInbdItemSerWrkList) {
                // Set common data.
                ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.whCd, whCd);
                ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.wmsSqNum, toWmsDataIfSq);
                // Get Primary Sequence
                BigDecimal wmsInbdItemSerWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_ITEM_SER_WRK_SQ);
                ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.wmsInbdItemSerWrkPk, wmsInbdItemSerWrkPk);

                EZDTBLAccessor.insert(wmsInbdItemSerWrkT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdItemSerWrkT.getReturnCode())) {
                    throw new S21AbendException(NLGM0007E, new String[] {NLGC002001.TBL_WMS_INBD_ITEM_SER_WRK //
                            , NLXCMsgHelper.toListedString(NLGC002001.COL_GLBL_CMPY_CD, NLGC002001.COL_WH_CD, NLGC002001.COL_WMS_ITEM_CD) //
                            , NLXCMsgHelper.toListedString(wmsInbdItemSerWrkT.glblCmpyCd, wmsInbdItemSerWrkT.whCd, wmsInbdItemSerWrkT.wmsItemCd) });
                }
            }
            return true;
        }


        /**
         * updateAbcAnlsRsltTbl
         * @param glblCmpyCd
         * @param abcAnlsRsltPk
         * @return true:Success(abcAnlsRsltPk is null.Skip.), false:Error
         */
        private boolean updateAbcAnlsRsltTbl(String glblCmpyCd, BigDecimal abcAnlsRsltPk) {
            if (abcAnlsRsltPk != null) {
                ABC_ANLS_RSLTTMsg abcAnlsRslt = new ABC_ANLS_RSLTTMsg();
                ZYPEZDItemValueSetter.setValue(abcAnlsRslt.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(abcAnlsRslt.abcAnlsRsltPk, abcAnlsRsltPk);

                abcAnlsRslt = (ABC_ANLS_RSLTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(abcAnlsRslt);

                if (abcAnlsRslt != null) {
                    ZYPEZDItemValueSetter.setValue(abcAnlsRslt.intfcUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
                    EZDTBLAccessor.update(abcAnlsRslt);
                } else {
                    // Error
                    throw new S21AbendException(NLGM0007E, new String[] {"ABC_ANLS_RSLT", "ABC_ANLS_RSLT_PK", abcAnlsRsltPk.toPlainString() });
                }
            }

            return true;
        }

        /**
         * compareAbcAnlsRslt
         * @param abcAnlsRsltPk
         * @return true: same false: not same
         */
        private boolean compareAbcAnlsRslt(BigDecimal abcAnlsRsltPk, String ezuptime) {

            if(abcAnlsRsltPk == null){
                return false;
            }

            ABC_ANLS_RSLTTMsg abcAnlsRslt = new ABC_ANLS_RSLTTMsg();
            ZYPEZDItemValueSetter.setValue(abcAnlsRslt.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(abcAnlsRslt.abcAnlsRsltPk, abcAnlsRsltPk);

            abcAnlsRslt = (ABC_ANLS_RSLTTMsg) EZDTBLAccessor.findByKey(abcAnlsRslt);

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", abcAnlsRslt.glblCmpyCd.getValue());
            queryParam.put("mdseCd", abcAnlsRslt.mdseCd.getValue());
            queryParam.put("rtlWhCd", abcAnlsRslt.rtlWhCd.getValue());

            Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getCompareMaxDate", queryParam);

            if (result != null) {
                String abcAnlsClsTagCd = (String) result.get("ABC_ANLS_CLS_TAG_CD");
                BigDecimal cycleCntFreqAot = (BigDecimal) result.get("CYCLE_CNT_FREQ_DAYS_AOT");
                String chkCntFreqDayAot = null;

                if (ZYPCommonFunc.hasValue(cycleCntFreqAot)) {
                    chkCntFreqDayAot = cycleCntFreqAot.toPlainString();
                }

                if (equalsString(abcAnlsRslt.abcAnlsClsTagCd.getValue(), abcAnlsClsTagCd) //
                        && equalsString(abcAnlsRslt.cycleCntFreqDaysAot.getValue().toPlainString(), chkCntFreqDayAot) //
                        && equalsString(abcAnlsRslt.ezUpTime.getValue(), ezuptime)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        /**
         * equalsString
         * @param a
         * @param b
         * @return true:same or null / false: not same
         */
        private boolean equalsString(String a, String b){
            if(a != null && b != null){
                return a.equals(b);
            } else{
                return true;
            }
        }

        /**
         * getLatestAbcClassTagAndCycleCount
         * @param glblCmpyCd
         * @param mdseCd
         * @param whCd
         * @return Map<String, Object>
         */
        private Map<String, Object> getLatestAbcClassTagAndCycleCount(String glblCmpyCd, String mdseCd, String whCd) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("mdseCd", mdseCd);
            queryParam.put("whCd", whCd + "%");
            queryParam.put("flag", ZYPConstant.FLG_ON_Y);

            Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getLatestAbcAnlsRslt", queryParam);

            if (result == null) {
                result = new HashMap<String, Object>();
            }
            return result;
        }

        private BigDecimal getPrimaryKeyForNewAbcAnlsRslt(String glblCmpyCd, String mdseCd, String whCd) {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("mdseCd", mdseCd);
            queryParam.put("whCd", whCd+"%");
            queryParam.put("flag", ZYPConstant.FLG_ON_Y);

            return (BigDecimal) ssmBatchClient.queryObject("getPrimaryKeyForAbcAnlsRslt", queryParam);

        }

        // QC#22577 ADD START
        /**
         * @param glblCmpyCd
         * @param mdseCd
         * @param ezuptime
         * @return
         */
        private boolean needToSendWms(String glblCmpyCd, String mdseCd, String ezUptime, String whCd) {

            boolean needToSendWms = false;
            boolean fromSerNum = false;
            boolean thruSerNum = false;
            boolean pkgUom = false;
            String  column = "";

            Map<String, Object> queryParam = new HashMap<String, Object>();

            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
            queryParam.put("dsHistRptTblNm", dsHistRptTblNmList);
            queryParam.put("ezUptime", ezUptime);
            queryParam.put("whCd", whCd);
            queryParam.put("mdseCd", mdseCd);

            List<Map<String, Object>> needToSendWmsColumnList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("needToSendWmsColumn", queryParam);

            for (Map<String, Object> sendColumn : needToSendWmsColumnList) {

                column = (String) sendColumn.get("DS_HIST_RPT_COL_NM");

                if (column.equals("FROM_SER_NUM")) {
                    fromSerNum = true;
                }
                if (column.equals("THRU_SER_NUM")) {
                    thruSerNum = true;
                }
                if (column.equals("PKG_UOM_CD")) {
                    pkgUom = true;
                }
            }

            // search tables.
            // MDSE_HIST
            List<Map<String, Object>> mdseHistList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdseHistList", queryParam);

            if (mdseHistList.size() > 1) {
                needToSendWms = needToSendWmsColumnCheck(mdseHistList, needToSendWmsColumnList);
            } else if (mdseHistList.size() == 1) {
                needToSendWms = true;
            }

            // search tables.
            // MDSE_SER_NUM_RNG_HIST
            if (needToSendWms) {
                return needToSendWms;
            } else {
                List<Map<String, Object>> mdseSerNumRngHistPkList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdseSerNumRngHistPkList", queryParam);

                for (Map<String, Object> hist : mdseSerNumRngHistPkList) {

                    queryParam.put("mdseSerNumRngPk", hist.get("MDSE_SER_NUM_RNG_PK"));

                    List<Map<String, Object>> mdseSerNumRngHistList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdseSerNumRngHistList", queryParam);

                    if (mdseSerNumRngHistList.size() > 1) {
                        needToSendWms = needToSendWmsColumnCheck(mdseSerNumRngHistList, needToSendWmsColumnList);
                    } else if (mdseSerNumRngHistList.size() == 1 && (fromSerNum || thruSerNum)) {
                        // new serial.
                        needToSendWms = true;
                    }
                    if (needToSendWms) {
                        return needToSendWms;
                    }
                }
            }

            // search tables.
            // MDSE_SFTY_INFO_HIST
            if (needToSendWms) {
                return needToSendWms;
            } else {
                // QC#60692 MOD START
                BigDecimal mdseSftyInfoHistCount = (BigDecimal) ssmBatchClient.queryObject("getMdseSftyInfoHistCountNew", queryParam);
                if (BigDecimal.ZERO.compareTo(mdseSftyInfoHistCount) < 0) {
                    List<Map<String, Object>> mdseSftyInfoHistList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdseSftyInfoHistList", queryParam);
                    if (mdseSftyInfoHistList.size() > 1) {
                        needToSendWms = needToSendWmsColumnCheck(mdseSftyInfoHistList, needToSendWmsColumnList);
                    } else if (mdseSftyInfoHistList.size() == 1) {
                        needToSendWms = true;
                    }
                }
                // QC#60692 MOD END
            }

            // search tables.
            // MDSE_STORE_PKG_HIST
            if (needToSendWms) {
                return needToSendWms;
            } else {
                List<Map<String, Object>> mdseStorePkgHistUomList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdseStorePkgHistUomList", queryParam);

                for (Map<String, Object> hist : mdseStorePkgHistUomList) {

                    queryParam.put("pkgUomCd", hist.get("PKG_UOM_CD"));

                    List<Map<String, Object>> mdseStorePkgHistList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdseStorePkgHistList", queryParam);

                    if (mdseStorePkgHistList.size() > 1) {
                        needToSendWms = needToSendWmsColumnCheck(mdseStorePkgHistList, needToSendWmsColumnList);
                        // new package uom code.
                    } else if (mdseStorePkgHistList.size() == 1 && pkgUom) {
                        needToSendWms = true;
                    }
                    if (needToSendWms) {
                        return needToSendWms;
                    }
                }
            }

            // search tables.
            // ABC_ANLS_RSLT mode 2
            if (NIGHT_MODE.equals(mode)) {
                if (needToSendWms) {
                    return needToSendWms;
                } else {
                    List<Map<String, Object>> abcAnlsRslt = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getAbcAnlsRslt", queryParam);
                    if (abcAnlsRslt.size() > 1) {
                        needToSendWms = needToSendWmsColumnCheck(abcAnlsRslt, needToSendWmsColumnList);
                    } else if (abcAnlsRslt.size() == 1) {
                        needToSendWms = true;
                    }
                }
            }

            return needToSendWms;
        }
    }

    /**
     * needToSendWmsColumnCheck
     * @param histList
     * @param colList
     * @return
     */
    private boolean needToSendWmsColumnCheck(List<Map<String, Object>> histList, List<Map<String, Object>> colList) {

        for (Map<String, Object> needToSendWmsColumn : colList) {

            String dsHistRptColNm = (String) needToSendWmsColumn.get("DS_HIST_RPT_COL_NM");

            String prevColumn = "";
            String oldColumn = "";
            Object val = "";

            for (Map<String, Object> hist : histList) {

                val = hist.get(dsHistRptColNm);

                if (val == null) {
                    continue;
                } else if (val instanceof BigDecimal) {
                    prevColumn = ((BigDecimal) val).toPlainString();
                } else {
                    prevColumn = val.toString();
                }

                if (ZYPCommonFunc.hasValue(oldColumn) && !prevColumn.equals(oldColumn)) {
                    return true;
                }

                oldColumn = prevColumn;
            }
        }

        return false;

    }

    // QC#22577 ADD END

    /**
     * Output Error Message.
     * @param msgId MessageId
     * @param msgParam MessageParameters
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }
    
    
    
    
    /**
     * getAbcInfo
     * @param glblCmpyCd
     * @param wmsMdseCd
     * @param whCd
     * @param lastIntfcMdseUpdTs
     * @return
     */
//    private Map<String, Object> getAbcInfo(String wmsMdseCd, String whCd) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("wmsMdseCd", wmsMdseCd);
//        queryParam.put("whCd", whCd);
//        Map<String, Object> poDetailInfo = (Map<String, Object>) ssmBatchClient.queryObject("getAbcInfo", queryParam);
//        return poDetailInfo;
//    }
}
