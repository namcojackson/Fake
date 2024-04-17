/**
 *  <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB236001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NWZC033001PMsg;
import business.parts.NWZC188001PMsg;
import business.parts.NWZC188001_shpgPlnNumListPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC033001.NWZC033001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.batch.NWA.NWAB236001.constant.NWAB236001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_REL_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.REV_RECOG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * NWAB236001:Install Status Update Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2013/08/23   Fujitsu         A.Shinohara     Create          R-OM057
 * 2013/09/11   Fujitsu         A.Shinohara     Update          Defect#2226,2242
 * 2015/09/17   Fujitsu         H.Nagashima     Update          CSA
 * 2016/09/28   Fujitsu         T.Ishii         Update          S21_NA#14557-2
 * 2017/03/22   Fujitsu         H.Nagashima     Update          QC#16639
 * 2017/06/15   Fujitsu         T.Aoi           Update          QC#18854
 * 2019/09/30   Fujitsu         R.Nakamura      Update          QC#53587
 * 2019/11/11   Fujitsu         S.Kosaka        Update          QC#54200
 *</pre>
 */
public class NWAB236001 extends S21BatchMain {
    /* Input Parameters */
    /** Global Company Code */
    private String glblCmpyCd;
    /* Internal Variables */
    /** Sales Date */
    private String salesDate;
    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;
    /** total correct count */
    private int totalCorrectCount = 0;
    /** total error count */
    private int totalErrorCount = 0;
    /** total Execute count */
    private int totalExecuteCount = 0;

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Message Map for Mail */
    private Map<String, List<String>> msgMap = new LinkedHashMap<String, List<String>>(NWAB236001Constant.SIZE_100);
    /** Mail Message Information */
    private StringBuilder mailMsgInfo = new StringBuilder();

    /** Shipping Number List */
    private List<String> shpgNumList = new ArrayList<String>(NWAB236001Constant.MAX_1000);

    @Override
    protected void initRoutine() {
        // Check GlobalCompanyCode
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NWAB236001Constant.NWZM0473E);
        }

        // Check Sales Date
        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NWAB236001Constant.NWAM0446E);
        }

        // Initialization of SSM
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        execUpdateConvOrdInfo();
        if (this.totalErrorCount > 0) {
            setTermCd(TERM_CD.WARNING_END);
        }

        // Send Mail
        if (!msgMap.isEmpty()) {
            postMail();
        }

        commit();
    }

    @Override
    protected void termRoutine() {
        totalExecuteCount = totalCorrectCount + totalErrorCount;
        setTermState(termCd, totalCorrectCount, totalErrorCount, totalExecuteCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB236001().executeBatch(NWAB236001.class.getSimpleName());
    }

    /**
     * Set TERM_CD.
     * @param ptermCd TERM_CD
     */
    private void setTermCd(TERM_CD ptermCd) {
        if (TERM_CD.NORMAL_END.equals(termCd)
                || (TERM_CD.WARNING_END.equals(termCd)
                && TERM_CD.ABNORMAL_END.equals(ptermCd))) {
            termCd = ptermCd;
        }
    }

    /**
     * Execute Install Status Update
     * @return Boolean
     */
    private Boolean execUpdateConvOrdInfo() {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        // Set SQL Parameters
        paramMap.put(NWAB236001Constant.PARAM_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(NWAB236001Constant.PARAM_TRX_SRC_TP_CD, TRX_SRC_TP.WHOLE_SALES);
        paramMap.put(NWAB236001Constant.PARAM_SHPG_STS_SHIPPED, SHPG_STS.SHIPPED);
        paramMap.put(NWAB236001Constant.PARAM_SHPG_STS_ARRIVED, SHPG_STS.ARRIVED);
        paramMap.put(NWAB236001Constant.PARAM_REV_RECOG_FLG, ZYPConstant.FLG_OFF_N);
        paramMap.put(NWAB236001Constant.PARAM_TRX_LINE_SUB_NUM, NWAB236001Constant.SET_LINE_SUB_NUM);
        paramMap.put(NWAB236001Constant.PARAM_MACH_MSTR_CRAT_FLG, ZYPConstant.FLG_ON_Y);
        paramMap.put(NWAB236001Constant.PARAM_REV_RECOG_METH_CD, REV_RECOG_METH.BOL);

        Boolean ssmRes = (Boolean) ssmBatchClient.queryObject(NWAB236001Constant.SQL_GET_SHPG_PLN_INFO, paramMap, new IstlStsUpd());

        return ssmRes;
    }

    /**
     * IstlStsUpd
     */
    protected class IstlStsUpd extends S21SsmBooleanResultSetHandlerSupport {
        /** Map for Stock Removed Shipping Plan Number per Line# */
        Map<String, String> removedShpgPlnNumMap = new HashMap<String, String>(NWAB236001Constant.SIZE_10);

        /** Set for Stock NoChange or Installed Shipping Plan Number per Line# */
        Set<String> noneOrInstalledShpgPlnNumSet = new HashSet<String>(NWAB236001Constant.SIZE_10);

        @Override
        /**
         * doProcessQueryResult
         */
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            Set<String> errOrdNumSet = new HashSet<String>(NWAB236001Constant.SIZE_50);
            boolean rsltFlg = false;
            boolean removedExistFlg = false;
            String procOrdNum = null;
            String procDtlLineNum = null;

            // Has no result data
            if (!rs.next()) {
                return true;
            }

            do {
                rsltFlg = false;

                // Close CPO_DTL
                if ((ZYPCommonFunc.hasValue(procOrdNum) && !procOrdNum.equals(rs.getString(NWAB236001Constant.DB_TRX_HDR_NUM)))
                        || (ZYPCommonFunc.hasValue(procDtlLineNum) && !procDtlLineNum.equals(rs.getString(NWAB236001Constant.DB_TRX_LINE_NUM)))) {
                    if (!errOrdNumSet.contains(procOrdNum) && !removedShpgPlnNumMap.isEmpty()) {
                        rsltFlg = updCpoDtl(procOrdNum, procDtlLineNum);
                        if (!rsltFlg) {
                            errOrdNumSet.add(procOrdNum);
                        }
                    }

                    if (!removedShpgPlnNumMap.isEmpty()) {
                        removedExistFlg = true;
                    }
                    removedShpgPlnNumMap.clear();
                    noneOrInstalledShpgPlnNumSet.clear();
                }

                // Close CPO
                if (ZYPCommonFunc.hasValue(procOrdNum) && !procOrdNum.equals(rs.getString(NWAB236001Constant.DB_TRX_HDR_NUM))) {
                    if (!errOrdNumSet.contains(procOrdNum) && removedExistFlg) {
                        rsltFlg = updCpo(procOrdNum);
                        if (!rsltFlg) {
                            errOrdNumSet.add(procOrdNum);
                        }
                    }

                    if (!callStsUpdApi(procOrdNum)) {
                        if (!errOrdNumSet.contains(procOrdNum)) {
                            errOrdNumSet.add(procOrdNum);
                        }
                    }
                    removedExistFlg = false;

                    if (!errOrdNumSet.contains(procOrdNum)) {
                        commit();
                        totalCorrectCount++;
                    } else {
                        String[] msgConvStr = new String[] {procOrdNum};
                        logOutputAndStkMsg(procOrdNum, NWAB236001Constant.NWAM0635E, msgConvStr);
                        rollback();
                        totalErrorCount++;
                    }
                }

                procOrdNum = rs.getString(NWAB236001Constant.DB_TRX_HDR_NUM);
                procDtlLineNum = rs.getString(NWAB236001Constant.DB_TRX_LINE_NUM);

                if (errOrdNumSet.contains(procOrdNum)) {
                    continue;
                }

                // Update SHPG_PLN
                if (isMachMstrItem(rs)) {
                    rsltFlg = updShpgPln(rs);
                } else {
                    rsltFlg = updShpgPlnByMainUnit(rs);
                }

                if (!rsltFlg) {
                    errOrdNumSet.add(procOrdNum);
                    continue;
                }

            } while (rs.next());

            // Close CPO_DTL
            if (!errOrdNumSet.contains(procOrdNum) && !removedShpgPlnNumMap.isEmpty()) {
                rsltFlg = updCpoDtl(procOrdNum, procDtlLineNum);
                if (!rsltFlg) {
                    errOrdNumSet.add(procOrdNum);
                }
            }

            if (!removedShpgPlnNumMap.isEmpty()) {
                removedExistFlg = true;
            }

            // Close CPO
            if (!errOrdNumSet.contains(procOrdNum) && removedExistFlg) {
                rsltFlg = updCpo(procOrdNum);
                if (!rsltFlg) {
                    errOrdNumSet.add(procOrdNum);
                }
                // 2017/06/15 QC#18854 Del Start
                //if (!callStsUpdApi(procOrdNum)) {
                //    if (!errOrdNumSet.contains(procOrdNum)) {
                //        errOrdNumSet.add(procOrdNum);
                //    }
                //}
                // 2017/06/15 QC#18854 Del End
            }

            // 2017/06/15 QC#18854 Add Start
            if (!callStsUpdApi(procOrdNum)) {
                if (!errOrdNumSet.contains(procOrdNum)) {
                    errOrdNumSet.add(procOrdNum);
                }
            }
            // 2017/06/15 QC#18854 Add End

            removedExistFlg = false;

            if (!errOrdNumSet.contains(procOrdNum)) {
                commit();
                totalCorrectCount++;
            } else {
                String[] msgConvStr = new String[] {procOrdNum};
                logOutputAndStkMsg(procOrdNum, NWAB236001Constant.NWAM0635E, msgConvStr);
                rollback();
                totalErrorCount++;
            }

            return true;
        }

        /**
         * Check Item is for Creating Machine Master
         * @param rs ResultSet
         * @return boolean
         * @throws SQLException
         */
        private boolean isMachMstrItem(ResultSet rs) throws SQLException {
            String instlBaseCtrlFlg = rs.getString(NWAB236001Constant.DB_INSTL_BASE_CTRL_FLG);
            String mdseTpCd = rs.getString(NWAB236001Constant.DB_MDSE_TP_CD);
            return ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)
                        && !MDSE_TP.SALES_BOM.equals(mdseTpCd);
        }

        /**
         * Update Shipping Plan (Item is for Creating Machine Master)
         * @param rs ResultSet
         * @return boolean
         * @throws SQLException
         */
        private boolean updShpgPln(ResultSet rs) throws SQLException {
            String shpgPlnNum = rs.getString(NWAB236001Constant.DB_SHPG_PLN_NUM);
            List<SVC_MACH_MSTRTMsg> machMstrList = getMachMstr(shpgPlnNum, ZYPConstant.FLG_OFF_N);

            if (machMstrList == null || machMstrList.isEmpty()
                    || rs.getBigDecimal(NWAB236001Constant.DB_ORD_QTY).compareTo(new BigDecimal(machMstrList.size())) != 0) {
                return true;
            }

            String shpgPlnUpdMode = getShpgPlnUpdMode(machMstrList);

            if (NWAB236001Constant.MODE_NONE.equals(shpgPlnUpdMode)) {
                noneOrInstalledShpgPlnNumSet.add(shpgPlnNum);
                return true;
            } else if (NWAB236001Constant.MODE_INSTALLED.equals(shpgPlnUpdMode)) {
                noneOrInstalledShpgPlnNumSet.add(shpgPlnNum);
                String ordNum = rs.getString(NWAB236001Constant.DB_TRX_HDR_NUM);
                String lineNum = rs.getString(NWAB236001Constant.DB_TRX_LINE_NUM);
                String lineSubNum = rs.getString(NWAB236001Constant.DB_TRX_LINE_SUB_NUM);
                BigDecimal dsCpoConfigPk = rs.getBigDecimal(NWAB236001Constant.DB_DS_CPO_CONFIG_PK);
                BigDecimal svcConfigMstrPk = rs.getBigDecimal(NWAB236001Constant.DB_SVC_CONFIG_MSTR_PK);
                boolean isSuccess = releaseBillingHold(ordNum, dsCpoConfigPk, svcConfigMstrPk);

                if (!isSuccess) {
                    return false;
                }

                // 2019/11/11 QC#54200 Add Start
                if (!checkExistArriveWaitInstlHld(ordNum, shpgPlnNum, dsCpoConfigPk, svcConfigMstrPk)) {
                    return false;
                }
                // 2019/11/11 QC#54200 Add End

                return updShpgPlnSts(shpgPlnNum, SHPG_STS.INSTALLED, ordNum, lineNum, lineSubNum);
            } else {
                removedShpgPlnNumMap.put(shpgPlnNum, rs.getString(NWAB236001Constant.DB_TRX_LINE_SUB_NUM));
                return true;
            }
        }

        /**
         * Update Shipping Plan (Item is NOT for Creating Machine Master)
         * @param rs ResultSet
         * @return boolean
         * @throws SQLException
         */
        private boolean updShpgPlnByMainUnit(ResultSet rs) throws SQLException {
            String shpgPlnNum = rs.getString(NWAB236001Constant.DB_SHPG_PLN_NUM);
            List<SVC_MACH_MSTRTMsg> machMstrList = getMachMstr(shpgPlnNum, ZYPConstant.FLG_ON_Y);

            if (machMstrList != null && !machMstrList.isEmpty()) {
                String ordNum = rs.getString(NWAB236001Constant.DB_TRX_HDR_NUM);
                String lineNum = rs.getString(NWAB236001Constant.DB_TRX_LINE_NUM);
                String lineSubNum = rs.getString(NWAB236001Constant.DB_TRX_LINE_SUB_NUM);
                String key = getKeyStr(ordNum, lineNum, lineSubNum);
                String[] msgConvStr = new String[] {shpgPlnNum};
                logOutputAndStkMsg(key, NWAB236001Constant.NWAM0650E, msgConvStr);
                return false;
            }

            String shpgPlnUpdMode = null;
            Map<String, Object> mainUnitInfo = getMainUnitInfo(rs);
            if (mainUnitInfo == null || mainUnitInfo.isEmpty()) {
                shpgPlnUpdMode = NWAB236001Constant.MODE_INSTALLED;
            } else if (!ZYPCommonFunc.hasValue((BigDecimal) mainUnitInfo.get(NWAB236001Constant.DB_SVC_MACH_MSTR_PK))) {
                shpgPlnUpdMode = NWAB236001Constant.MODE_NONE;
            } else {
                String svcMachMstrStsCd = (String) mainUnitInfo.get(NWAB236001Constant.DB_SVC_MACH_MSTR_STS_CD);
                String istlDt = (String) mainUnitInfo.get(NWAB236001Constant.DB_ISTL_DT);
                shpgPlnUpdMode = chkShpgPlnUpdMode(svcMachMstrStsCd, istlDt);
            }

            if (NWAB236001Constant.MODE_NONE.equals(shpgPlnUpdMode)) {
                noneOrInstalledShpgPlnNumSet.add(shpgPlnNum);
                return true;
            } else if (NWAB236001Constant.MODE_INSTALLED.equals(shpgPlnUpdMode)) {
                noneOrInstalledShpgPlnNumSet.add(shpgPlnNum);
                String ordNum = rs.getString(NWAB236001Constant.DB_TRX_HDR_NUM);
                String lineNum = rs.getString(NWAB236001Constant.DB_TRX_LINE_NUM);
                String lineSubNum = rs.getString(NWAB236001Constant.DB_TRX_LINE_SUB_NUM);
                BigDecimal dsCpoConfigPk = rs.getBigDecimal(NWAB236001Constant.DB_DS_CPO_CONFIG_PK);
                BigDecimal svcConfigMstrPk = rs.getBigDecimal(NWAB236001Constant.DB_SVC_CONFIG_MSTR_PK);
                boolean isSuccess = releaseBillingHold(ordNum, dsCpoConfigPk, svcConfigMstrPk);

                if (!isSuccess) {
                    return false;
                }

                // 2019/11/11 QC#54200 Add Start
                if (!checkExistArriveWaitInstlHld(ordNum, shpgPlnNum, dsCpoConfigPk, svcConfigMstrPk)) {
                    return false;
                }
                // 2019/11/11 QC#54200 Add End

                return updShpgPlnSts(shpgPlnNum, SHPG_STS.INSTALLED, ordNum, lineNum, lineSubNum);
            } else {
                removedShpgPlnNumMap.put(shpgPlnNum, rs.getString(NWAB236001Constant.DB_TRX_LINE_SUB_NUM));
                return true;
            }
        }

        /**
         * Update CPO Detail
         * @param ordNum String
         * @param lineNum String
         * @return boolean
         */
        private boolean updCpoDtl(String ordNum, String lineNum) {
            Map<String, BigDecimal> cpoQtyMap = new HashMap<String, BigDecimal>(NWAB236001Constant.SIZE_10);
            Map<String, BigDecimal> cancQtyMap = new HashMap<String, BigDecimal>(NWAB236001Constant.SIZE_10);
            BigDecimal shpgPlnTotalQty = BigDecimal.ZERO;
            Map<String, Boolean> setPrntMap = new HashMap<String, Boolean>(NWAB236001Constant.SIZE_10);
            Map<String, Boolean> detailMap = new HashMap<String, Boolean>(NWAB236001Constant.SIZE_10);
            boolean setExistFlg = false;

            List<Map<String, Object>> fnshShpgPlnList = getFnshShpgPlnInfo(ordNum, lineNum);
            for (Map<String, Object> fnshShpgPlnInfo : fnshShpgPlnList) {
                String shpgPlnNum = (String) fnshShpgPlnInfo.get(NWAB236001Constant.DB_SHPG_PLN_NUM);
                String lineSubNum = (String) fnshShpgPlnInfo.get(NWAB236001Constant.DB_TRX_LINE_SUB_NUM);
                String shpgSts = (String) fnshShpgPlnInfo.get(NWAB236001Constant.DB_SHPG_STS_CD);
                String setPrntShpgPlnNum = (String) fnshShpgPlnInfo.get(NWAB236001Constant.DB_SET_SHPG_PLN_NUM);
                String revRecogFlg = (String) fnshShpgPlnInfo.get(NWAB236001Constant.DB_REV_RECOG_FLG);
                BigDecimal cpoQty = (BigDecimal) fnshShpgPlnInfo.get(NWAB236001Constant.DB_ORD_QTY_CD);
                BigDecimal shpgPlnQty = (BigDecimal) fnshShpgPlnInfo.get(NWAB236001Constant.DB_ORD_QTY_SP);

                // Check Shipping Plan Update Mode
                boolean isClo = isShpgStsAsClo(shpgPlnNum, shpgSts, revRecogFlg);

                // Stock Set Parent Shipping Plan Number and Update Mode
                if (ZYPCommonFunc.hasValue(setPrntShpgPlnNum)) {
                    if (!setPrntMap.containsKey(setPrntShpgPlnNum)) {
                        setPrntMap.put(setPrntShpgPlnNum, isClo);
                    } else {
                        if (!setPrntMap.get(setPrntShpgPlnNum) && isClo) {
                            setPrntMap.put(setPrntShpgPlnNum, isClo);
                        }
                    }
                    setExistFlg = true;
                }

                // Stock Order Quantity for Each Detail
                if (!cpoQtyMap.containsKey(lineSubNum)) {
                    cpoQtyMap.put(lineSubNum, cpoQty);
                }
                // Stock Shipping Plan Total Quantity
                shpgPlnTotalQty = shpgPlnTotalQty.add(shpgPlnQty);
                // Stock Cancel Shipping Plan Quantity
                if (SHPG_STS.CANCELLED.equals(shpgSts) || removedShpgPlnNumMap.containsKey(shpgPlnNum)) {
                    if (!cancQtyMap.containsKey(lineSubNum)) {
                        cancQtyMap.put(lineSubNum, shpgPlnQty);
                    } else {
                        BigDecimal cancQty = cancQtyMap.get(lineSubNum).add(shpgPlnQty);
                        cancQtyMap.put(lineSubNum, cancQty);
                    }
                }

                // Stock Detail Update Mode
                if (!detailMap.containsKey(lineSubNum)) {
                    detailMap.put(lineSubNum, isClo);
                } else {
                    if (!detailMap.get(lineSubNum) && isClo) {
                        detailMap.put(lineSubNum, isClo);
                    }
                }
            }

            // Judge Quantity
            BigDecimal cpoQty = BigDecimal.ZERO;
            for (Map.Entry<String, BigDecimal> entry : cpoQtyMap.entrySet()) {
                cpoQty = cpoQty.add(entry.getValue());
            }
            if (cpoQty.compareTo(shpgPlnTotalQty) != 0) {
                return true;
            }

            // Update Removed Shipping Plan
            for (Map.Entry<String, String> entry : removedShpgPlnNumMap.entrySet()) {
                if (!updShpgPlnSts(entry.getKey(), SHPG_STS.CANCELLED, ordNum, lineNum, entry.getValue())) {
                    return false;
                }
            }

            // Update Set Parent
            boolean setDtlCancFlg = true;
            for (Map.Entry<String, Boolean> entry : setPrntMap.entrySet()) {
                SHPG_PLNTMsg setPrntShpgPlnTMsg = getSetPrntShpgPln(entry.getKey());
                String shpgStsCd = setPrntShpgPlnTMsg.shpgStsCd.getValue();
                String revRecogFlg = setPrntShpgPlnTMsg.revRecogFlg.getValue();
                if (isShpgStsAsClo(entry.getKey(), shpgStsCd, revRecogFlg)) {
                    setDtlCancFlg = false;
                    continue;
                }

                if (entry.getValue()) {
                    setDtlCancFlg = false;
                    if (!updShpgPlnSts(entry.getKey(), SHPG_STS.INVOICED, ordNum, lineNum, NWAB236001Constant.SET_LINE_SUB_NUM)) {
                        return false;
                    }
                } else {
                    BigDecimal setPrntOrdQty = setPrntShpgPlnTMsg.ordQty.getValue();
                    if (!cancQtyMap.containsKey(NWAB236001Constant.SET_LINE_SUB_NUM)) {
                        cancQtyMap.put(NWAB236001Constant.SET_LINE_SUB_NUM, setPrntOrdQty);
                    } else {
                        BigDecimal cancQty = cancQtyMap.get(NWAB236001Constant.SET_LINE_SUB_NUM).add(setPrntOrdQty);
                        cancQtyMap.put(NWAB236001Constant.SET_LINE_SUB_NUM, cancQty);
                    }
                    if (!updShpgPlnSts(entry.getKey(), SHPG_STS.CANCELLED, ordNum, lineNum, NWAB236001Constant.SET_LINE_SUB_NUM)) {
                        return false;
                    }
                }
            }
            if (setExistFlg) {
                detailMap.put(NWAB236001Constant.SET_LINE_SUB_NUM, !setDtlCancFlg);
            }

            // Update CPO Detail
            for (Map.Entry<String, Boolean> entry : detailMap.entrySet()) {
                if (entry.getValue()) {
                    if (!updCpoDtlSts(ordNum, lineNum, entry.getKey(), ORD_LINE_STS.CLOSED, null)) {
                        return false;
                    }
                } else {
                    if (!updCpoDtlSts(ordNum, lineNum, entry.getKey(), ORD_LINE_STS.CANCELLED, cancQtyMap.get(entry.getKey()))) {
                        return false;
                    }
                }
            }

            return true;
        }

        /**
         * Update CPO
         * @param ordNum String
         * @return boolean
         */
        private boolean updCpo(String ordNum) {
            Map<String, Object> paramMap = new HashMap<String, Object>();

            // Set SQL Parameters
            paramMap.put(NWAB236001Constant.PARAM_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(NWAB236001Constant.PARAM_CPO_ORD_NUM, ordNum);

            List<String> cpoDtlStsList = (List<String>) ssmBatchClient.queryObjectList(NWAB236001Constant.SQL_GET_CPO_DTL_STS, paramMap);

            boolean cloFlg = false;

            for (String cpoDtlSts : cpoDtlStsList) {
                if (!ORD_LINE_STS.CLOSED.equals(cpoDtlSts) && !ORD_LINE_STS.CANCELLED.equals(cpoDtlSts)) {
                    return true;
                } else if (ORD_LINE_STS.CLOSED.equals(cpoDtlSts)) {
                    cloFlg = true;
                }
            }

            if (cloFlg) {
                return updCpoSts(ordNum, ORD_HDR_STS.CLOSED);
            } else {
                return updCpoSts(ordNum, ORD_HDR_STS.CANCELLED);
            }
        }

        /**
         * Get Service Machine Master
         * @param shpgPlnNum String
         * @param rowNumFlg String
         * @return List&lt;SVC_MACH_MSTRTMsg&gt;
         */
        private List<SVC_MACH_MSTRTMsg> getMachMstr(String shpgPlnNum, String rowNumFlg) {
            Map<String, Object> paramMap = new HashMap<String, Object>();

            // Set SQL Parameters
            paramMap.put(NWAB236001Constant.PARAM_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(NWAB236001Constant.PARAM_SHPG_PLN_NUM, shpgPlnNum);
            paramMap.put(NWAB236001Constant.PARAM_ROW_NUM_FLG, rowNumFlg);

            return (List<SVC_MACH_MSTRTMsg>) ssmBatchClient.queryObjectList(NWAB236001Constant.SQL_GET_MACH_MSTR, paramMap);
        }

        /**
         * Get Shipping Plan Update Mode
         * @param machMstrList List&lt;SVC_MACH_MSTRTMsg&gt;
         * @return String
         */
        private String getShpgPlnUpdMode(List<SVC_MACH_MSTRTMsg> machMstrList) {
            String svcMachMstrStsCd = null;
            for (SVC_MACH_MSTRTMsg svcMachMstrTMsg : machMstrList) {
                if (!ZYPCommonFunc.hasValue(svcMachMstrStsCd)) {
                    svcMachMstrStsCd = svcMachMstrTMsg.svcMachMstrStsCd.getValue();
                    continue;
                }
                if (!svcMachMstrStsCd.equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())) {
                    return NWAB236001Constant.MODE_NONE;
                }
            }

            String istlDt = machMstrList.get(0).istlDt.getValue();

            return chkShpgPlnUpdMode(svcMachMstrStsCd, istlDt);
        }

        /**
         * Decide Shipping Plan Update Mode (MODE_NONE, MODE_INSTALLED, MODE_REMOVED)
         * @param svcMachMstrStsCd String
         * @param istlDt String
         * @return String
         */
        private String chkShpgPlnUpdMode(String svcMachMstrStsCd, String istlDt) {
            if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrStsCd)) {
                return NWAB236001Constant.MODE_NONE;
            } else if (SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd)) {
                return NWAB236001Constant.MODE_INSTALLED;
            } else if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {
                if (ZYPCommonFunc.hasValue(istlDt)) {
                    return NWAB236001Constant.MODE_INSTALLED;
                } else {
                    return NWAB236001Constant.MODE_NONE;
                }
            } else if (SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {
                if (ZYPCommonFunc.hasValue(istlDt)) {
                    return NWAB236001Constant.MODE_INSTALLED;
                } else {
                    return NWAB236001Constant.MODE_REMOVED;
                }
            }
            return NWAB236001Constant.MODE_NONE;
        }

        /**
         * Get Main Unit Information
         * @param rs ResultSet
         * @return Map&lt;String, String&gt;
         * @throws SQLException
         */
        private Map<String, Object> getMainUnitInfo(ResultSet rs) throws SQLException {
            Map<String, Object> paramMap = new HashMap<String, Object>();

            // Set SQL Parameters
            paramMap.put(NWAB236001Constant.PARAM_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(NWAB236001Constant.PARAM_TRX_SRC_TP_CD, TRX_SRC_TP.WHOLE_SALES);
            paramMap.put(NWAB236001Constant.PARAM_TRX_HDR_NUM, rs.getString(NWAB236001Constant.DB_TRX_HDR_NUM));
            paramMap.put(NWAB236001Constant.PARAM_MDSE_TP_CD, rs.getString(NWAB236001Constant.DB_MDSE_TP_CD));
            paramMap.put(NWAB236001Constant.PARAM_SO_NUM, nullToBlank(rs.getString(NWAB236001Constant.DB_SO_NUM)));
            paramMap.put(NWAB236001Constant.PARAM_DS_ORD_POSN_NUM, nullToBlank(rs.getString(NWAB236001Constant.DB_DS_ORD_POSN_NUM)));
            paramMap.put(NWAB236001Constant.PARAM_BASE_CMPT_FLG, ZYPConstant.FLG_ON_Y);

            return (Map<String, Object>) ssmBatchClient.queryObject(NWAB236001Constant.SQL_GET_MAIN_UNIT_INFO, paramMap);
        }

        /**
         * Get Additional Shipping Plan Information
         * @param ordNum String
         * @param lineNum String
         * @return List&lt;Map&lt;String, Object&gt;&gt;
         */
        private List<Map<String, Object>> getFnshShpgPlnInfo(String ordNum, String lineNum) {
            Map<String, Object> paramMap = new HashMap<String, Object>();

            // Set SQL Parameters
            paramMap.put(NWAB236001Constant.PARAM_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(NWAB236001Constant.PARAM_TRX_SRC_TP_CD, TRX_SRC_TP.WHOLE_SALES);
            paramMap.put(NWAB236001Constant.PARAM_SHPG_STS_INVOICED, SHPG_STS.INVOICED);
            paramMap.put(NWAB236001Constant.PARAM_SHPG_STS_CANCELLED, SHPG_STS.CANCELLED);
            paramMap.put(NWAB236001Constant.PARAM_SHPG_STS_SHIPPED, SHPG_STS.SHIPPED);
            paramMap.put(NWAB236001Constant.PARAM_SHPG_STS_ARRIVED, SHPG_STS.ARRIVED);
            paramMap.put(NWAB236001Constant.PARAM_SHPG_STS_INSTALLED, SHPG_STS.INSTALLED);
            paramMap.put(NWAB236001Constant.PARAM_REV_RECOG_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(NWAB236001Constant.PARAM_TRX_HDR_NUM, ordNum);
            paramMap.put(NWAB236001Constant.PARAM_TRX_LINE_NUM, lineNum);
            paramMap.put(NWAB236001Constant.PARAM_TRX_LINE_SUB_NUM, NWAB236001Constant.SET_LINE_SUB_NUM);
            List<String> shpgPlnNumListNotTarget = new ArrayList<String>();
            for (String noneOrInstalledShpgPlnNum : noneOrInstalledShpgPlnNumSet) {
                shpgPlnNumListNotTarget.add(noneOrInstalledShpgPlnNum);
            }
            paramMap.put(NWAB236001Constant.PARAM_SHPG_PLN_NUM_LIST_NOT_TARGET, shpgPlnNumListNotTarget);
            List<String> shpgPlnNumListTarget = new ArrayList<String>();
            for (Map.Entry<String, String> entry : removedShpgPlnNumMap.entrySet()) {
                shpgPlnNumListTarget.add(entry.getKey());
            }
            paramMap.put(NWAB236001Constant.PARAM_SHPG_PLN_NUM_LIST_TARGET, shpgPlnNumListTarget);

            return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(NWAB236001Constant.SQL_GET_FNSH_SHPG_PLN_INFO, paramMap);
        }

        /**
         * Check Shipping Status Code for SHPG_PLN/CPO_DTL Close
         * @param shpgPlnNum String
         * @param shpgSts String
         * @param revRecogFlg String
         * @return boolean
         */
        private boolean isShpgStsAsClo(String shpgPlnNum, String shpgSts, String revRecogFlg) {
            if (removedShpgPlnNumMap.containsKey(shpgPlnNum)) {
                return false;
            }

            Set<String> cloStsSet = new HashSet<String>(Arrays.asList(SHPG_STS.SHIPPED, SHPG_STS.ARRIVED, SHPG_STS.INSTALLED));
            if (SHPG_STS.INVOICED.equals(shpgSts)
                    || (cloStsSet.contains(shpgSts) && ZYPConstant.FLG_ON_Y.equals(revRecogFlg))) {
                return true;
            }
            return false;
        }

        /**
         * Get Order Quantity on Shipping Plan of Set Parent
         * @param shpgPlnNum String
         * @return SHPG_PLNTMsg
         */
        private SHPG_PLNTMsg getSetPrntShpgPln(String shpgPlnNum) {
            Map<String, Object> paramMap = new HashMap<String, Object>();

            // Set SQL Parameters
            paramMap.put(NWAB236001Constant.PARAM_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(NWAB236001Constant.PARAM_SHPG_PLN_NUM, shpgPlnNum);

            return (SHPG_PLNTMsg) ssmBatchClient.queryObject(NWAB236001Constant.SQL_GET_PRNT_SHPG_PLN, paramMap);
        }

        /**
         * Update SHPG_PLN
         * @param shpgPlnNum String
         * @param updShpgStsCd String
         * @return boolean
         */
        private boolean updShpgPlnSts(String shpgPlnNum, String updShpgStsCd, String ordNum, String lineNum, String lineSubNum) {
            SHPG_PLNTMsg srchShpgPlnTMsg = new SHPG_PLNTMsg();
            srchShpgPlnTMsg.glblCmpyCd.setValue(glblCmpyCd);
            srchShpgPlnTMsg.shpgPlnNum.setValue(shpgPlnNum);

            SHPG_PLNTMsg updShpgPlnTMsg = (SHPG_PLNTMsg) EZDTBLAccessor.findByKeyForUpdateWait(srchShpgPlnTMsg);
            if (updShpgPlnTMsg == null) {
                String key = getKeyStr(ordNum, lineNum, lineSubNum);
                String[] msgConvStr = new String[] {NWAB236001Constant.TBL_SHPG_PLN, shpgPlnNum};
                logOutputAndStkMsg(key, NWAB236001Constant.NWAM0373E, msgConvStr);
                return false;
            }

            // Set Value
            updShpgPlnTMsg.shpgStsCd.setValue(updShpgStsCd);

            // Execute Update
            EZDTBLAccessor.update(updShpgPlnTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updShpgPlnTMsg.getReturnCode())) {
                String key = getKeyStr(ordNum, lineNum, lineSubNum);
                String[] msgConvStr = new String[] {NWAB236001Constant.TBL_SHPG_PLN, shpgPlnNum};
                logOutputAndStkMsg(key, NWAB236001Constant.NWZM1024E, msgConvStr);
                return false;
            }

            // set ShpgNumber for Status
            shpgNumList.add(shpgPlnNum);

            if (shpgNumList.size() == NWAB236001Constant.MAX_1000) {
                if (!callStsUpdApi(ordNum)) {
                    return false;
                }
            }

            String eventId = null;
            if (SHPG_STS.INSTALLED.equals(updShpgStsCd)) {
                eventId = NWAB236001Constant.EVENT_ID_INSL;
            } else if (SHPG_STS.INVOICED.equals(updShpgStsCd)) {
                eventId = NWAB236001Constant.EVENT_ID_INV;
            } else {
                eventId = NWAB236001Constant.EVENT_ID_CANC;
            }
            printBizProcLog(eventId, ordNum, lineNum, lineSubNum);
            return true;
        }

        /**
         * Update CPO_DTL
         * @param ordNum String
         * @param lineNum String
         * @param lineSubNum String
         * @param updOrdLineStsCd String
         * @param updCancQty BigDecimal
         * @return boolean
         */
        private boolean updCpoDtlSts(String ordNum, String lineNum, String lineSubNum, String updOrdLineStsCd, BigDecimal updCancQty) {
            CPO_DTLTMsg srchCpoDtlTMsg = new CPO_DTLTMsg();
            srchCpoDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
            srchCpoDtlTMsg.cpoOrdNum.setValue(ordNum);
            srchCpoDtlTMsg.cpoDtlLineNum.setValue(lineNum);
            srchCpoDtlTMsg.cpoDtlLineSubNum.setValue(lineSubNum);

            CPO_DTLTMsg updCpoDtlTMsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(srchCpoDtlTMsg);
            if (updCpoDtlTMsg == null) {
                String key = getKeyStr(ordNum, lineNum, lineSubNum);
                String[] msgConvStr = new String[] {NWAB236001Constant.TBL_CPO_DTL, key};
                logOutputAndStkMsg(key, NWAB236001Constant.NWAM0373E, msgConvStr);
                return false;
            }

            // Set Value
            updCpoDtlTMsg.ordLineStsCd.setValue(updOrdLineStsCd);
            updCpoDtlTMsg.openFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (ORD_LINE_STS.CANCELLED.equals(updOrdLineStsCd)) {
                ZYPEZDItemValueSetter.setValue(updCpoDtlTMsg.cancQty, updCancQty);
            }

            // Execute Update
            EZDTBLAccessor.update(updCpoDtlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updCpoDtlTMsg.getReturnCode())) {
                String key = getKeyStr(ordNum, lineNum, lineSubNum);
                String[] msgConvStr = new String[] {NWAB236001Constant.TBL_CPO_DTL, key};
                logOutputAndStkMsg(key, NWAB236001Constant.NWZM1024E, msgConvStr);
                return false;
            }

            String eventId = null;
            if (ORD_LINE_STS.CLOSED.equals(updOrdLineStsCd)) {
                eventId = NWAB236001Constant.EVENT_ID_INSL;
            } else {
                eventId = NWAB236001Constant.EVENT_ID_CANC;
            }
            printBizProcLog(eventId, ordNum, lineNum, lineSubNum);
            return true;
        }

        /**
         * Update CPO
         * @param ordNum String
         * @param updOrdHdrStsCd String
         * @return boolean
         */
        private boolean updCpoSts(String ordNum, String updOrdHdrStsCd) {
            CPOTMsg srchCpoTMsg = new CPOTMsg();
            srchCpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
            srchCpoTMsg.cpoOrdNum.setValue(ordNum);

            CPOTMsg updCpoTMsg = (CPOTMsg) EZDTBLAccessor.findByKeyForUpdateWait(srchCpoTMsg);
            if (updCpoTMsg == null) {
                String[] msgConvStr = new String[] {NWAB236001Constant.TBL_CPO, ordNum};
                logOutputAndStkMsg(ordNum, NWAB236001Constant.NWAM0373E, msgConvStr);
                return false;
            }

            // Set Value
            updCpoTMsg.ordHdrStsCd.setValue(updOrdHdrStsCd);
            if (ORD_HDR_STS.CANCELLED.equals(updOrdHdrStsCd)) {
                updCpoTMsg.cpoCancFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
            updCpoTMsg.openFlg.setValue(ZYPConstant.FLG_OFF_N);

            // Execute Update
            EZDTBLAccessor.update(updCpoTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updCpoTMsg.getReturnCode())) {
                String[] msgConvStr = new String[] {NWAB236001Constant.TBL_CPO, ordNum};
                logOutputAndStkMsg(ordNum, NWAB236001Constant.NWZM1024E, msgConvStr);
                return false;
            }

            String eventId = null;
            if (ORD_HDR_STS.CLOSED.equals(updOrdHdrStsCd)) {
                eventId = NWAB236001Constant.EVENT_ID_INSL;
            } else {
                eventId = NWAB236001Constant.EVENT_ID_CANC;
            }
            printBizProcLog(eventId, ordNum, null, null);
            return true;
        }

        /**
         * Print S21BusinessProcessLogMsg
         * @param eventId String
         * @param ordNum String
         * @param lineNum String
         * @param lineSubNum String
         */
        private void printBizProcLog(String eventId, String ordNum, String lineNum, String lineSubNum) {
            S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();

            bizProcLogMsg.setSubSysId(NWAB236001Constant.SUB_SYS_ID);
            bizProcLogMsg.setProcId(NWAB236001Constant.PROCESS_ID);
            bizProcLogMsg.setDocTpCd(NWAB236001Constant.DOCUMENT_TYPE);
            bizProcLogMsg.setEventId(eventId);
            if (ZYPCommonFunc.hasValue(lineNum) && ZYPCommonFunc.hasValue(lineSubNum)) {
                bizProcLogMsg.setDocId(S21StringUtil.concatStrings(lineNum, ".", lineSubNum));
            }
            bizProcLogMsg.setPrntDocId(ordNum);

            S21BusinessProcessLog.print(bizProcLogMsg);
        }

        /**
         * Output Log and Stock Error Message ID for Mail
         * @param keyStr String
         * @param xxMsgId String
         * @param msgConvStr String[]
         */
        private void logOutputAndStkMsg(String keyStr, String xxMsgId, String[] msgConvStr) {
            List<String> msgList;
            if (msgMap.containsKey(keyStr)) {
                msgList = msgMap.get(keyStr);
            } else {
                msgList = new ArrayList<String>(NWAB236001Constant.SIZE_10);
            }

            msgList.add(S21MessageFunc.clspGetMessage(xxMsgId, msgConvStr));
            msgMap.put(keyStr, msgList);

            S21InfoLogOutput.println(xxMsgId, msgConvStr);
        }

        /**
         * Get Key String for msgMap Key
         * @param ordNum String
         * @param lineNum String
         * @param lineSubNum String
         * @return String
         */
        private String getKeyStr(String ordNum, String lineNum, String lineSubNum) {
            return S21StringUtil.concatStrings(ordNum, NWAB236001Constant.COMMA, lineNum, NWAB236001Constant.COMMA, lineSubNum);
        }

        /**
         * Convert Null to Blank
         * @param val String
         * @return String
         */
        private String nullToBlank(String val) {
            if (!ZYPCommonFunc.hasValue(val)) {
                return "";
            }
            return val;
        }
        private boolean releaseBillingHold(String ordNum, BigDecimal dsCpoConfigPk, BigDecimal svcConfigMstrPk) {

            //judge Hold Release
            if (svcConfigMstrPk == null) {
                return true;
            }
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            // Mod Start 2019/10/01 QC#53587
//            queryParam.put("cpoOrdNum",  ordNum);
//            queryParam.put("dsCpoConfigPk",  dsCpoConfigPk);
            queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
//            queryParam.put("svcMachMstrStsCd", SVC_MACH_MSTR_STS.INSTALLED);
            queryParam.put("stsIntaled", SVC_MACH_MSTR_STS.INSTALLED);
            queryParam.put("stsW4R", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
            queryParam.put("stsRemoved", SVC_MACH_MSTR_STS.REMOVED);
            queryParam.put("stsTerminated", SVC_MACH_MSTR_STS.TERMINATED);
            // Mod End 2019/10/01 QC#53587
            Integer result = (Integer) ssmBatchClient.queryObject("countNotInstalledMachMstr", queryParam);
            if (result == null || result.intValue() > 0) {
                return true;
            }

            // Search Hold Data
            queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd",        glblCmpyCd);
            // Mod Start 2019/10/01 QC#53587
//            queryParam.put("cpoOrdNum",         ordNum);
//            queryParam.put("dsCpoConfigPk",     dsCpoConfigPk);
            queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
            // Mod End 2019/10/01 QC#53587
            queryParam.put("hldRsnS03",         HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION);
            queryParam.put("relflgN",           ZYPConstant.FLG_OFF_N);
            List<Map<String, Object>> holdList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("searchHoldReleaseTarget", queryParam);

            for (Map<String, Object> hldMap : holdList) {
                NWZC033001PMsg pmsg = new NWZC033001PMsg();
                ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pmsg.hldPk, (BigDecimal) hldMap.get("HLD_PK"));
                ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, (String) hldMap.get("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, (String) hldMap.get("CPO_DTL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, (String) hldMap.get("CPO_DTL_LINE_SUB_NUM"));
                ZYPEZDItemValueSetter.setValue(pmsg.shpgPlnNum, (String) hldMap.get("SHPG_PLN_NUM"));
                ZYPEZDItemValueSetter.setValue(pmsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(pmsg.hldRelRsnCd, HLD_REL_RSN.AUTO);

                new NWZC033001().execute(pmsg, ONBATCH_TYPE.BATCH);

                if (pmsg.xxMsgIdList.getValidCount() > 0) {
                    for (int i = 0; i < pmsg.xxMsgIdList.getValidCount(); i++) {
                        S21InfoLogOutput.println(pmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    }
                    return false;
                }
            }
            return true;
        }

        /**
         * Call NWZC188001 Display Order Line Status Update API
         * @param cpoOrdNum String
         * @return boolean 
         */
        private boolean callStsUpdApi(String cpoOrdNum) {

            if (shpgNumList.size() == 0) {
                return true;
            }
            NWZC188001PMsg pMsg = new NWZC188001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoOrdNum);

            for (int i = 0; i < shpgNumList.size(); i++) {
                NWZC188001_shpgPlnNumListPMsg shpgNumMsg = pMsg.shpgPlnNumList.no(i);
                ZYPEZDItemValueSetter.setValue(shpgNumMsg.shpgPlnNum, shpgNumList.get(i));
                pMsg.shpgPlnNumList.setValidCount(i + 1);
            }
            shpgNumList.clear();

            new NWZC188001().execute(pMsg, ONBATCH_TYPE.BATCH);

            // Error Check
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                return false;
            }
            for (int i = 0; i < pMsg.shpgPlnNumList.getValidCount(); i++) {
                // shpg List: Error check
                NWZC188001_shpgPlnNumListPMsg shpgListPmsg = pMsg.shpgPlnNumList.no(i);

                if (ZYPCommonFunc.hasValue(shpgListPmsg.xxMsgId)) {
                    S21InfoLogOutput.println(shpgListPmsg.xxMsgId.getValue());
                    return false;
                }
            }
            return true;
        }
        // 2019/11/11 QC#54200 Add Start
        private boolean checkExistArriveWaitInstlHld(String ordNum, String targetShpgPln, BigDecimal dsCpoConfigPk, BigDecimal svcConfigMstrPk) {

            if (svcConfigMstrPk == null) {
                return true;
            }
            // Check exists S03 Hold
            Map<String, Object> queryParamExistsHold = new HashMap<String, Object>();
            queryParamExistsHold = new HashMap<String, Object>();
            queryParamExistsHold.put(NWAB236001Constant.PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParamExistsHold.put(NWAB236001Constant.PARAM_SVC_CONFIG_MSTR_PK, svcConfigMstrPk);
            queryParamExistsHold.put(NWAB236001Constant.PARAM_HLD_RSN_S03, HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION);
            queryParamExistsHold.put(NWAB236001Constant.PARAM_REL_FLG_N, ZYPConstant.FLG_OFF_N);
            List<Map<String, Object>> holdList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList(NWAB236001Constant.SQL_SRCH_HOLDS, queryParamExistsHold);
            if (holdList == null || holdList.size() == 0) {
                return true;
            }

            // Check Shipping Plan Status
            Map<String, Object> queryParamShpgPln = new HashMap<String, Object>();
            queryParamShpgPln.put(NWAB236001Constant.PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParamShpgPln.put(NWAB236001Constant.PARAM_SVC_CONFIG_MSTR_PK, svcConfigMstrPk);
            List<Map<String, Object>> shippingStatusList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList(NWAB236001Constant.SQL_GET_SHPG_STS_IN_CONFIG, queryParamShpgPln);
            for (Map<String, Object> shippingStatusMap : shippingStatusList) {
                // When all Shipping Status is INSTALLED without target line, return false.
                if (S21StringUtil.isEquals(targetShpgPln, (String) shippingStatusMap.get(NWAB236001Constant.DB_SHPG_PLN_NUM))) {
                    continue;
                }
                if (!SHPG_STS.INSTALLED.equals((String) shippingStatusMap.get(NWAB236001Constant.DB_SHPG_STS_CD))) {
                    return true;
                }
            }

            return false;
        }
        // 2019/11/11 QC#54200 Add End
    }

    /**
     * Send error Mail
     */
    private void postMail() {
        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, NWAB236001Constant.ML_GRP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            String[] msgConvStr = new String[] {NWAB236001Constant.FROM, NWAB236001Constant.ML_GRP_ID_FROM, NWAB236001Constant.HYPHEN};
            throw new S21AbendException(NWAB236001Constant.NWAM0637E, msgConvStr);
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, NWAB236001Constant.ML_GRP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            String[] msgConvStr = new String[] {NWAB236001Constant.TO, NWAB236001Constant.ML_GRP_ID_TO, NWAB236001Constant.HYPHEN};
            throw new S21AbendException(NWAB236001Constant.NWAM0637E, msgConvStr);
        }

        // Get Mail Template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, NWAB236001Constant.ML_TMPL_ID);
        if (template == null) {
            throw new S21AbendException(NWAB236001Constant.NWAM0319E, new String[] {NWAB236001Constant.ML_TMPL_ID});
        }
        cratMailMsg();
        String currentTime = ZYPDateUtil.getCurrentSystemTime(NWAB236001Constant.ML_DT_TM_FMT);
        template.setTemplateParameter(NWAB236001Constant.ML_TMPL_KEY_ID, NWAB236001Constant.BIZ_ID);
        template.setTemplateParameter(NWAB236001Constant.ML_TMPL_KEY_DT, currentTime);
        template.setTemplateParameter(NWAB236001Constant.ML_TMPL_KEY_MSG, mailMsgInfo.toString());

        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(addrFromList.get(0));
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject(NWAB236001Constant.ML_LANG), NWAB236001Constant.ML_LANG_CD);
        mail.postMail();
    }

    /**
     * Create Error Mail Message
     */
    private void cratMailMsg() {
        String ttl;
        String msg;
        for (Map.Entry<String, List<String>> entry : msgMap.entrySet()) {
            ttl = S21StringUtil.concatStrings(entry.getKey(), NWAB236001Constant.LINE_FEED_CODE);
            mailMsgInfo.append(ttl);
            for (String value : entry.getValue()) {
                msg = S21StringUtil.concatStrings(NWAB236001Constant.TAB, value, NWAB236001Constant.LINE_FEED_CODE);
                mailMsgInfo.append(msg);
            }
        }
    }
}
