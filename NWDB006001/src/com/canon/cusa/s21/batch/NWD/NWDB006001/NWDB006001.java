/*
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package com.canon.cusa.s21.batch.NWD.NWDB006001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.CAL_RELNTMsg;
import business.db.CAL_RELNTMsgArray;
import business.db.HARD_ALLOC_BAT_WRKTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.calendar.S21DateManagement;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;


/**
 * <pre>
 * Acquire the order details information that is hard allocation possibility in the 
 * order details of the B/O state and output acquisition object data to a worktable.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/01/23   Fujitsu         A.Suda          Create          N/A
 * 2009/08/07   Fujitsu         R.Watanabe      Update          N/A
 * 2009/10/26   Fujitsu         R.Watanabe      Update          773
 * 2010/04/01   Fujitsu         A.Suda          Update          5358
 * 2010/04/26   Fujitsu         N.Yamamoto      Update          5989
 * 2010/05/20   Fujitsu         A.Suda          Update          5902 
 * 2010/06/30   Fujitsu         A.Suda          Update          7532
 * 2010/08/30   Fujitsu         A.Suda          Update          203 
 * 2011/01/25   Fujitsu         A.Suda          Update          1150
 * 2011/04/18   CSAI            T.Gotoda        Update          1763
 * 2015/09/15   Fujitsu         A.Suda          Update          CSA(2.8 Allocation - SO/PO)
 * 2016/01/13   Fujitsu         A.Suda          Update          S21_NA#2435
 * 2016/06/28   Fujitsu         S.Yamamoto      Update          S21_NA#8872
 * 2016/11/29   Fujitsu         K.Sato          Update          S21_NA#9903
 * 2016/12/20   Fujitsu         T.Yoshida       Update          S21_NA#16578
 * 2017/07/24   Fujitsu         T.Murai         Update          S21_NA#5872(L3#001)
 * 2017/09/22   CITS            S.Endo          Update          QC#21077
 * 2022/08/12   Hitachi         K.Kim           Update          QC#60319
 * 2023/10/03   Hitachi         M.Nakajima      Update          QC#61274
 *</pre>
 */
public class NWDB006001 extends S21BatchMain {

    /** MessageID */
    private static final String ZZBM0009I = "ZZBM0009I";

    /** MessageID : An input parameter "@"  has not been set. */
    private static final String NWDM0001E = "NWDM0001E";

    /** MessageID : Set '@'  in the input parameter's  '@' and continue the process. */
    private static final String NWDM0003I = "NWDM0003I";

    /** MessageID : Entered @ does not exist in the Master. */
    private static final String NWDM0051E = "NWDM0051E";

    /** MessageID */
    private static final String ZZMM0001E = "ZZMM0001E";

    /** MessageID : It failed to get the sales date.  Process will ABEND. */
//    private static final String NWDM0210E = "NWDM0210E";

    /** MessageID : Cannot Allocation since start date has not passed. */
//    private static final String NWZM0950E = "NWZM0950E";

    /** MessageID : It failed to register Mail. */
    private static final String NWCM0060E = "NWCM0060E";

    /** MessageID : CalendarType is not found. Please confirm the Master Data. */
    private static final String NWZM0949E = "NWZM0949E"; 

    /** MessageID : Multiple Warehouse Calendars are obtained.  Please check Master data. */
    private static final String NWZM0673E = "NWZM0673E"; 

    /** MessageID : No record exists in the Calendar table. Please contact system administrators. */
    private static final String NWZM0948E = "NWZM0948E"; 

    /** Program ID */
    private static final String PROGRAM_ID = NWDB006001.class.getSimpleName();

    /** Normal end */
//    private static final String RTNCD_NORMAL_END = "0000";

    /** WorkTableID */
    private static final String WORK_TABLE_ID = "Hard Allocation Batch Work";

    /** Multiple Default Number */
    private static final int MULTIPLE_NUM = 1;

    /** Multiple min */
    private static final int MULTIPLE_MIN = 0;

    /** Multiple max */
    private static final int MULTIPLE_MAX = 1000;

    /** DEBUG_MSG_LVL */
    private static final int DEBUG_MSG_LVL = 1;

    /** bulk Insert Max Count */
    private static final int BULK_INSERT_MAX_COUNT = 5000;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Term cd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Multiple Total */
    private int multipleTotal = 1;

    /** Processing Count */
    private int procCnt = 0;

    /** normal Count */
    private int normalCnt = 0;

    /** error Count */
    private int errorCnt = 0;

    /** mailTemplateId */
    private static final String MAIL_TEMPLATE_ID = "NWDB0060M001";

    /** error List */
    private List<String> errorList = new ArrayList<String>();

    /** sales date */
    private String slsDt = null;
 
    private String VAR_CHAR_CONST_NM_NOT_ALLOC_WH_CD = "NOT_HARD_ALLOC_WH_CD";

    private String VAR_CHAR_CONST_NM_CR_AND_BILL_ONLY_DUMMY_WH_CD = "CR_AND_BILL_ONLY_DUMMY_WH_CD"; // 2016/06/28 S21_NA#8872

    private Map<String, CAL_RELNTMsgArray> cacheMap = null; // S21_NA#16578 Add

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {

        new NWDB006001().executeBatch(PROGRAM_ID);
    }

    @Override
    protected void initRoutine() {

        this.getEnvParameter();

        if (!isGlobalCompanyCode()) {

            S21InfoLogOutput.println(NWDM0051E, new String[] {"Global Company Code" });
            setMainRoutineEnd();
            this.termCd = TERM_CD.ABNORMAL_END;
            return;
        }

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        cacheMap = new HashMap<String, CAL_RELNTMsgArray>(); // S21_NA#16578 Add
    }

    @Override
    protected void mainRoutine() {

        this.slsDt = S21DateManagement.getBatProcDate(getGlobalCompanyCode());

        // ========================================
        // 1. remove : Hard Allocation Batch Work
        // ========================================
        removeHardAllocBatWrk();

        // ========================================
        // 2. getInformation : OrderInformation
        // ========================================
        List<Map<String, Object>> hardAllocInfoList = getHardAllocationInfo();
        if (isEmpty(hardAllocInfoList)) {
            return;
        }

        //DEL START S21_NA#5872
//        // ========================================
//        // 3. getInformation : ordCatgCtxTpCd = [DAYS_PRI_ALLOC_CTRL]
//        // ========================================
//        List<Map<String, Object>> ordCatgCtxInfoList = getOrdCatgCtxInfo();
        //DEL END S21_NA#5872

        List<Map<String, Object>> targetHardAllocInfoList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> configurationList = new ArrayList<Map<String, Object>>();
        String checkedConfigKey = "";
        //DEL START S21_NA#5872
//        String checkedCpoOrdNum = "";
//        boolean rddDtCheckByConfigFlg = false;
        //DEL END S21_NA#5872

        for (int i = 0; i < hardAllocInfoList.size(); i++) {

            Map<String, Object> hardAllocInfo = hardAllocInfoList.get(i);

            String currentConfigKey = (String) hardAllocInfo.get("ORD_CONFIG_KEY");
            String cpoOrdNum = (String) hardAllocInfo.get("CPO_ORD_NUM");

            if (!hasValue(checkedConfigKey)) {
                checkedConfigKey = currentConfigKey;
            }

            // ========================================
            // 4. Check : DAYS_PRI_ALLOC_CTRL
            // ========================================
            // If key exists in ORD_CATG_BIZ_CTX
            // ("DAYS_PRI_ALLOC_CTRL"), check rddDt by configuration.
            // rddDtCheckByConfigFlg = true

            // DEL START S21_NA#5872
//            if (!checkedCpoOrdNum.equals(cpoOrdNum)) {
//                rddDtCheckByConfigFlg = checkOrdCatgCtx(hardAllocInfo, ordCatgCtxInfoList);
//            }
//
//            if (rddDtCheckByConfigFlg) {
            // DEL END S21_NA#5872

                if (checkedConfigKey.equals(currentConfigKey)) {
                    configurationList.add(hardAllocInfo);
                } else {
                    // Check Future RDD
                    if (checkAllocStartedDay(configurationList)) {
                        targetHardAllocInfoList.addAll(configurationList);
                    }
                    configurationList = new ArrayList<Map<String, Object>>();
                    configurationList.add(hardAllocInfo);

                }
                // DEL START S21_NA#5872
//            } else {
//                targetHardAllocInfoList.add(hardAllocInfo);
//            }

//            checkedCpoOrdNum = cpoOrdNum;
                // DEL END S21_NA#5872

            checkedConfigKey = currentConfigKey;
        }

        // Mod START S21_NA#5872
//        if (!configurationList.isEmpty() && rddDtCheckByConfigFlg) {
        if (!configurationList.isEmpty()) {
        // Mod END S21_NA#5872
            // Check Future RDD
            if (checkAllocStartedDay(configurationList)) {
                targetHardAllocInfoList.addAll(configurationList);
            }
        }

        // ========================================
        // 5. Grouping : BatchGroupCode
        // ========================================
        Map<String, Integer> tranGroupMap = createBatchGroupCdList(targetHardAllocInfoList);

        // ========================================
        // 6. Create : Hard Allocation Batch Work
        // ========================================
        createHardAllocBatWrk(targetHardAllocInfoList, tranGroupMap);
    }

// DEL START S21_NA#5872
//    /**
//     * <pre>
//   * check DS Order Category Code.
//   * </pre>
//     * @param hardAllocationInfo Map<String, Object>
//     * @param ordCatgCtxInfoList List<Map<String, Object>>
//     * @return true/RDDFutureCheck by configuration . false/NOT
//     * RDDFutureCheck.
//     */
//    private boolean checkOrdCatgCtx(Map<String, Object> hardAllocationInfo, List<Map<String, Object>> ordCatgCtxInfoList) {
//
//        String ordCatgCd = (String) hardAllocationInfo.get("DS_ORD_CATG_CD");
//        String ordTpCd = (String) hardAllocationInfo.get("DS_ORD_TP_CD");
//        String ordRsnCd = (String) hardAllocationInfo.get("DS_ORD_RSN_CD");
//
//        for (int i = 0; i < ordCatgCtxInfoList.size(); i++) {
//
//            Map<String, Object> ordCatgCtxInfo = ordCatgCtxInfoList.get(i);
//
//            String mstrOrdCatgCd = (String) ordCatgCtxInfo.get("DS_ORD_CATG_CD");
//            String mstrOrdTpCd = (String) ordCatgCtxInfo.get("DS_ORD_TP_CD");
//            String mstrOrdRsnCd = (String) ordCatgCtxInfo.get("DS_ORD_RSN_CD");
//
//            if (!hasValue(mstrOrdCatgCd) && !hasValue(mstrOrdTpCd) && !hasValue(mstrOrdRsnCd)) {
//                continue;
//            }
//
//            if (hasValue(mstrOrdCatgCd) && hasValue(mstrOrdTpCd) && hasValue(mstrOrdRsnCd)) {
//
//                if (mstrOrdCatgCd.equals(ordCatgCd) && mstrOrdTpCd.equals(ordTpCd) && mstrOrdRsnCd.equals(ordRsnCd)) {
//                    return true;
//                }
//
//            }
//            if (hasValue(mstrOrdCatgCd) && hasValue(mstrOrdTpCd) && !hasValue(mstrOrdRsnCd)) {
//
//                if (mstrOrdCatgCd.equals(ordCatgCd) && mstrOrdTpCd.equals(ordTpCd)) {
//                    return true;
//                }
//
//            }
//            if (hasValue(mstrOrdCatgCd) && !hasValue(mstrOrdTpCd) && !hasValue(mstrOrdRsnCd)) {
//
//                if (mstrOrdCatgCd.equals(ordCatgCd)) {
//                    return true;
//                }
//
//            }
//        }
//        return false;
//    }
 // DEL END S21_NA#5872

    /**
     * <pre>
     * Group it in number of partitions set as a parameter and register myself with a HARD_ALLOC_WRK table.
     * </pre>
     * @param targetHardAllocInfoList List<Map<String, Object>>
     * @param tranGroupMap Map<String, Integer>
     */
    private void createHardAllocBatWrk(List<Map<String, Object>> targetHardAllocInfoList, Map<String, Integer> tranGroupMap) {

        int batGrpCd = 0;
        int procGrpNum = 0;
        int procSqNum = 0;
        String procGrpKey = "";
        List<HARD_ALLOC_BAT_WRKTMsg> hardAllocBatList = new ArrayList<HARD_ALLOC_BAT_WRKTMsg>();
        Map<Integer, Integer> batchProcGroupMap = new HashMap<Integer, Integer>();
        this.procCnt = targetHardAllocInfoList.size();

        for (int i = 0; i < targetHardAllocInfoList.size(); i++) {

            Map<String, Object> hardAllocationInfo = targetHardAllocInfoList.get(i);

            String ordConfigKey = (String) hardAllocationInfo.get("ORD_CONFIG_KEY");
            procSqNum++;

            batGrpCd = tranGroupMap.get(ordConfigKey);
            if (!procGrpKey.equals(ordConfigKey)) {
                procSqNum = 1;
                if (batchProcGroupMap.containsKey(batGrpCd)) {
                    procGrpNum = batchProcGroupMap.get(batGrpCd);
                    procGrpNum++;
                } else {
                    procGrpNum = 1;
                }
                batchProcGroupMap.put(batGrpCd, procGrpNum);
            }

            hardAllocBatList.add(createHardAllocBatWrkMsg(batGrpCd, procGrpNum, procSqNum, hardAllocationInfo));

            if (hardAllocBatList.size() >= BULK_INSERT_MAX_COUNT) {

                insertHardAllocBatWrk(hardAllocBatList.toArray(new HARD_ALLOC_BAT_WRKTMsg[0]));

                hardAllocBatList.clear();
            }

            procGrpKey = ordConfigKey;
        }

        if (!isEmpty(hardAllocBatList)) {
            insertHardAllocBatWrk(hardAllocBatList.toArray(new HARD_ALLOC_BAT_WRKTMsg[0]));
        }
    }

    /**
     * <pre>
     * Create Batch Group Code List(Grouping).
     * </pre>
     * @param targetHardAllocInfoList List<Map<String, Object>>
     * @return BatchGroupCdList
     */
    private Map<String, Integer> createBatchGroupCdList(List<Map<String, Object>> targetHardAllocInfoList) {

        int batGrpCd = 0;

        Map<String, Integer> whMap = new HashMap<String, Integer>();
        Map<String, Integer> tranGroupMap = new HashMap<String, Integer>();

        for (int i = 0; i < targetHardAllocInfoList.size(); i++) {

            Map<String, Object> hardAllocInfo = targetHardAllocInfoList.get(i);

            String ordConfigKey = (String) hardAllocInfo.get("ORD_CONFIG_KEY");
            String whCd = (String) hardAllocInfo.get("WH_CD");

            if (tranGroupMap.containsKey(ordConfigKey)) {

                if (whMap.containsKey(whCd)) {
                    continue;
                } else {
                    whMap.put(whCd, tranGroupMap.get(ordConfigKey));
                }

            } else {
                int setBatGrpCd = 0;

                if (whMap.containsKey(whCd)) {
                    setBatGrpCd = whMap.get(whCd);
                } else {

                    if (batGrpCd == this.multipleTotal) {
                        batGrpCd = 1;
                    } else {
                        batGrpCd++;
                    }

                    setBatGrpCd = batGrpCd;
                    whMap.put(whCd, setBatGrpCd);
                }

                tranGroupMap.put(ordConfigKey, setBatGrpCd);

                List<String> sameBatchGroupTranList = new ArrayList<String>();

                for (int j = i + 1; j < targetHardAllocInfoList.size(); j++) {

                    Map<String, Object> checkHardAllocInfo = targetHardAllocInfoList.get(j);

                    String checkwhCd = (String) checkHardAllocInfo.get("WH_CD");
                    String checkordConfigKey = (String) checkHardAllocInfo.get("ORD_CONFIG_KEY");

                    if (ordConfigKey.equals(checkordConfigKey) && whCd.equals(checkwhCd)) {
                        continue;
                    }

                    if (whCd.equals(checkwhCd) && !sameBatchGroupTranList.contains(checkordConfigKey)) {
                        sameBatchGroupTranList.add(checkordConfigKey);
                    }
                }

                if (!sameBatchGroupTranList.isEmpty()) {

                    for (int k = 0; k < targetHardAllocInfoList.size(); k++) {

                        Map<String, Object> checkHardAllocInfo = targetHardAllocInfoList.get(k);

                        String checkwhCd = (String) checkHardAllocInfo.get("WH_CD");
                        String checkordConfigKey = (String) checkHardAllocInfo.get("ORD_CONFIG_KEY");

                        if (sameBatchGroupTranList.contains(checkordConfigKey)) {

                            if (!tranGroupMap.containsKey(checkordConfigKey)) {
                                tranGroupMap.put(checkordConfigKey, setBatGrpCd);
                            }

                            if (!whMap.containsKey(checkwhCd)) {
                                whMap.put(checkwhCd, setBatGrpCd);
                            }
                        }
                    }
                }
            }
        }

        return tranGroupMap;

    }

    @Override
    protected void termRoutine() {

        // Send Error Mail
        if (!isEmpty(this.errorList)) {
            debug("error information :" + this.errorList.toString());

            if (!postErrorMail()) {
                this.termCd = TERM_CD.ABNORMAL_END;
                throw new S21AbendException(NWCM0060E);
            }
            this.termCd = TERM_CD.WARNING_END;
        }

        S21InfoLogOutput.println(ZZBM0009I, new String[] {WORK_TABLE_ID, String.valueOf(procCnt), "INSERT" });

        setTermState(this.termCd, this.normalCnt, this.errorCnt, this.procCnt);
    }

    /**
     * <pre>
     * Perform the check that is GLBL_CMPY_CD.
     * </pre>
     * @return true/normal. false/Abnormality.
     */
    private boolean isGlobalCompanyCode() {

        String glblCmpyCd = getGlobalCompanyCode();
        return ZYPCodeDataUtil.hasCodeValue(GLBL_CMPY.class, glblCmpyCd, glblCmpyCd);
    }

    /**
     * <pre>
     * Check empty list.
     * </pre>
     * @param list List
     * @return true/empty. false/not empty.
     */
    private boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * <pre>
     * Acquire the parameter.
     * </pre>
     */
    private void getEnvParameter() {

        try {

            int multiple = Integer.parseInt(S21BatchUtil.getUserVariable1());

            if (multiple > MULTIPLE_MIN && multiple < MULTIPLE_MAX) {
                this.multipleTotal = multiple;
            } else {
                this.multipleTotal = MULTIPLE_NUM;
                S21InfoLogOutput.println(NWDM0003I, new String[] {"Multiple Total", Integer.toString(MULTIPLE_NUM) });
            }
        } catch (NumberFormatException e) {
            this.multipleTotal = MULTIPLE_NUM;
            S21InfoLogOutput.println(NWDM0001E, new String[] {"Multiple Total" });
            S21InfoLogOutput.println(NWDM0003I, new String[] {"Multiple Total", Integer.toString(MULTIPLE_NUM) });
        }
    }

    /**
     * <pre>
     * Repay length of ORD_TAKE_MDSE_CD.
     * </pre>
     * @return length of ORD_TAKE_MDSE_CD.
     */
    private int getOrdTakeMdseCdDigit() {

        ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
        return ordTakeMdseMsg.getAttr("ordTakeMdseCd").getDigit();
    }

    /**
     * <pre>
     * Check whether the allocation start date has come.
     * </pre>
     * @return true/Start date has already come. false/Not come yet
     * start date.
     */
    private boolean checkAllocStartedDay(List<Map<String, Object>> cofigurationList) {

        String minDt = "";

        for (int i = 0; i < cofigurationList.size(); i++) {

            Map<String, Object> orderInfo = cofigurationList.get(i);

            String whCd = (String) orderInfo.get("WH_CD");
            String rddDt = (String) orderInfo.get("RDD_DT");
            String shpgPlnNum = (String) orderInfo.get("SHPG_PLN_NUM");
            BigDecimal daysPriAllocNum = (BigDecimal) orderInfo.get("DAYS_PRI_ALLOC_NUM");

            
            if(!hasValue(rddDt)){
                return true;              
            }

            String calcDay = calcAllocStartedDay(daysPriAllocNum, whCd, rddDt, shpgPlnNum);
            if (calcDay == null) {
                return false;
            }

            if (!hasValue(minDt)) {
                minDt = calcDay;
            } else {
                if (ZYPDateUtil.compare(calcDay, minDt) < 0) {
                    minDt = calcDay;
                }
            }
        }

        if (ZYPDateUtil.compare(this.slsDt, minDt) < 0) {
            // startDay > slsDt
            return false;
        } else {
            return true;
        }

    }

    /**
     * <pre>
     * Calculate the allocation start date.
     * </pre>
     * @param Days Priority Allocation Number BigDecimal
     * @param Warehouse Code String
     * @param RDD Date String
     * @param shpgPlnNum String
     * @return allocation start date
     */
    private String calcAllocStartedDay(BigDecimal daysPriAllocNum, final String whCd, final String rddDt, final String shpgPlnNum) {

        int days = daysPriAllocNum.negate().intValue();
        String startDay = ZYPDateUtil.addDays(rddDt, days);
        String allocStartedDay = null;

        String calTpCd = getCalTpCd(whCd);

        if (!hasValue(calTpCd) || calTpCd.equals(NWZM0949E) || calTpCd.equals(NWZM0673E)) {
            if (!hasValue(calTpCd)) {
                calTpCd = NWZM0949E;
            }
            this.errorList.add("  Error MessageID : " + calTpCd + "(" + " Shipping Plan Number = " + shpgPlnNum + " )");
            return null;

        }

        allocStartedDay = getBusinessDay(startDay, calTpCd);
        if (allocStartedDay == null) {
            this.errorList.add("  Error MessageID : " + NWZM0948E + "(" + " Shipping Plan Number = " + shpgPlnNum + " )");
            return null;
        }

        return allocStartedDay;

    }

    /**
     * <pre>
     * Get Calendar Type Code
     * </pre>
     * @param Inventory Location Code String
     * @return Calendar Type Code
     */
    private String getCalTpCd(final String invtyLocCd) {

        CAL_RELNTMsg calRelnTMsg = null;
        String calTpCd = null;
        String glblCmpyCd = getGlobalCompanyCode();

        if (hasValue(invtyLocCd)) {
            calRelnTMsg = new CAL_RELNTMsg();
            calRelnTMsg.glblCmpyCd.setValue(glblCmpyCd);
            calRelnTMsg.calSubTpCd.setValue(CAL_SUB_TP.WAREHOUSE_CALENDAR);
            calRelnTMsg.calMultCd.setValue(invtyLocCd);
            calRelnTMsg = (CAL_RELNTMsg) S21CacheTBLAccessor.findByKey(calRelnTMsg);
        }

        if (calRelnTMsg != null) {
            calTpCd = calRelnTMsg.calTpCd.getValue();
        } else {
            // S21_NA#16578 Mod Start
            CAL_RELNTMsgArray calRelnTRcd = cacheMap.get(CAL_SUB_TP.COMPANY_CALENDAR);
            if (calRelnTRcd == null) {
                CAL_RELNTMsg calRelnTKey = new CAL_RELNTMsg();
                calRelnTKey.setSQLID("001");
                calRelnTKey.setConditionValue("glblCmpyCd01", glblCmpyCd);
                calRelnTKey.setConditionValue("calSubTpCd01", CAL_SUB_TP.COMPANY_CALENDAR);
                calRelnTRcd = (CAL_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(calRelnTKey);
                cacheMap.put(CAL_SUB_TP.COMPANY_CALENDAR, calRelnTRcd);
            }
            // S21_NA#16578 Mod End

            if (calRelnTRcd == null || calRelnTRcd.length() == 0) {
                return NWZM0949E;

            } else if (calRelnTRcd.length() > 1) {
                // When acquire more than two cases,
                // process is finished as master setting error.
                return NWZM0673E;
            } else {
                calTpCd = calRelnTRcd.no(0).calTpCd.getValue();
            }
        }

        return calTpCd;
    }

    /**
     * <pre>
     * Get Business Date
     * </pre>
     * @param Allocation start date String
     * @param Calendar Type Code String
     * @return Business Date
     */
    private String getBusinessDay(final String startDay, final String calTpCd) {

        String businessDay = startDay;
        boolean isBusinessDay = false;

        isBusinessDay = ZYPDateUtil.isBusinessDayEx(getGlobalCompanyCode(), calTpCd, startDay);

        if (!isBusinessDay) {
            businessDay = ZYPDateUtil.addBusinessDay(calTpCd, startDay, -1);
        }

        return businessDay;
    }

    /**
     * <pre>
     * Acquire information to perform HardAllocation.
     * </pre>
     * @return search results.
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getHardAllocationInfo() {

        String glblCmpyCd = getGlobalCompanyCode();

        VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
        setValue(varCharConstTMsg.glblCmpyCd,     glblCmpyCd);
        setValue(varCharConstTMsg.varCharConstNm, VAR_CHAR_CONST_NM_NOT_ALLOC_WH_CD);
        varCharConstTMsg = (VAR_CHAR_CONSTTMsg)S21FastTBLAccessor.findByKey(varCharConstTMsg);

        List<String> whLst = null;
        if (varCharConstTMsg != null) {
            final String varCharConstVal = varCharConstTMsg.varCharConstVal.getValue();
            whLst =asList(varCharConstVal.split(","));
        }

        // 2016/06/28 S21_NA#8872 Add Start
        varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
        setValue(varCharConstTMsg.glblCmpyCd,     glblCmpyCd);
        setValue(varCharConstTMsg.varCharConstNm, VAR_CHAR_CONST_NM_CR_AND_BILL_ONLY_DUMMY_WH_CD);
        varCharConstTMsg = (VAR_CHAR_CONSTTMsg)S21FastTBLAccessor.findByKey(varCharConstTMsg);

        List<String> rtlWhLst = null;
        if (varCharConstTMsg != null) {
            final String varCharConstVal = varCharConstTMsg.varCharConstVal.getValue();
            rtlWhLst =asList(varCharConstVal.split(","));
        }
        // 2016/06/28 S21_NA#8872 Add End

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("shpgSts", SHPG_STS.VALIDATED);
        param.put("stkSts", STK_STS.GOOD);
        param.put("trxSrcTpWholeSales", TRX_SRC_TP.WHOLE_SALES);
        param.put("trxSrcTpRetail", TRX_SRC_TP.RETAIL);
        param.put("hardAllocTp", HARD_ALLOC_TP.AUTO);
        param.put("expdShipDt", this.slsDt);
        param.put("trxLineSubNum", "000");
        param.put("invtyDistTpNone", DIST_TP.NONE);
        param.put("invtyDistTpDist", DIST_TP.DISTRIBUTION);
        param.put("hldLvlCpo", HLD_LVL.CPO_LEVEL);
        param.put("hldLvlCpoDtl", HLD_LVL.CPO_DETAIL_LEVEL);
        param.put("hldLvlShpg", HLD_LVL.SHIPPING_PLAN_LEVEL);
        param.put("hardAllocTpManual", HARD_ALLOC_TP.MANUAL);
        param.put("whLst", whLst);
        param.put("ordTakeMdseCdDigit", String.valueOf(getOrdTakeMdseCdDigit()));
        param.put("rtlWhLst", rtlWhLst);// 2016/06/28 S21_NA#8872
        // START 2022/08/12 [QC#60319, ADD]
        param.put("mdseItemTp", MDSE_ITEM_TP.SET);
        // END 2022/08/12 [QC#60319, ADD]

        // 2017/09/22 S.Endo QC#21077 ADD START
        param.put("defTmFenceDaysAot", ZYPCodeDataUtil.getNumConstValue("DEF_TM_FENCE_DAYS_AOT", glblCmpyCd));
        // 2017/09/22 S.Endo QC#21077 ADD END

        // START 2023/10/3 [QC#61274, ADD]
        String avalRtlWhCd = ZYPCodeDataUtil.getVarCharConstValue("NLBL2020_AUTO_RTL_WH_CD", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(avalRtlWhCd)) {
            String[] avalRtlWhCdArray = avalRtlWhCd.split(",");
            param.put("avalRtlWhCdArray", avalRtlWhCdArray);
        }
        param.put("poSts", PO_STS.CLOSED);
        // END 2023/10/3 [QC#61274, ADD]

        return ssmBatchClient.queryObjectList("getHardAllocationInfo", param);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getOrdCatgCtxInfo() {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.DAYS_PRIOR_ALLOCATION_CONTROL_ORDERS);

        return ssmBatchClient.queryObjectList("getOrderCategoryBusinessContext", param);
    }

    /**
     * <pre>
     * Create Hard Allocation Batch Work Msg
     * </pre>
     * @param batGrpCd int
     * @param procGrpNum int
     * @param procSqNum int
     * @param hardAllocationInfo Map<String, Object>
     * @return Hard Allocation Batch Work Msg
     */
    private HARD_ALLOC_BAT_WRKTMsg createHardAllocBatWrkMsg(int batGrpCd, int procGrpNum, int procSqNum, Map<String, Object> hardAllocationInfo) {

        HARD_ALLOC_BAT_WRKTMsg batchWorkMsg = new HARD_ALLOC_BAT_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(batchWorkMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(batchWorkMsg.batGrpCd, Integer.toString(batGrpCd));
        ZYPEZDItemValueSetter.setValue(batchWorkMsg.procGrpNum, Integer.toString(procGrpNum));
        ZYPEZDItemValueSetter.setValue(batchWorkMsg.procSqNum, Integer.toString(procSqNum));
        ZYPEZDItemValueSetter.setValue(batchWorkMsg.shpgPlnNum, (String) hardAllocationInfo.get("SHPG_PLN_NUM"));
        ZYPEZDItemValueSetter.setValue(batchWorkMsg.distTpCd, (String) hardAllocationInfo.get("INVTY_DIST_TP_CD"));
        ZYPEZDItemValueSetter.setValue(batchWorkMsg.softAllocPk, (BigDecimal) hardAllocationInfo.get("SOFT_ALLOC_PK"));
        ZYPEZDItemValueSetter.setValue(batchWorkMsg.hardAllocTpCd, HARD_ALLOC_TP.AUTO);

        if (ZYPConstant.FLG_ON_Y.equals((String) hardAllocationInfo.get("IS_ORD_TAKE_MDSE_FLG"))) {

            String mdseCd = (String) hardAllocationInfo.get("MDSE_CD");

            if (mdseCd.length() > getOrdTakeMdseCdDigit()) {
                ZYPEZDItemValueSetter.setValue(batchWorkMsg.shpgPlnOrdTakeMdseCd, subByteString(mdseCd, 0, getOrdTakeMdseCdDigit()));
            } else {
                ZYPEZDItemValueSetter.setValue(batchWorkMsg.shpgPlnOrdTakeMdseCd, mdseCd);
            }
        }

        return batchWorkMsg;
    }

    /**
     * <pre>
     * Insert Hard Allocation Batch Work
     * </pre>
     * @param hardAllocBatWrkMsgList HARD_ALLOC_BAT_WRKTMsg[]
     */
    private void insertHardAllocBatWrk(HARD_ALLOC_BAT_WRKTMsg[] hardAllocBatWrkMsgList) {

        int insertCount = S21FastTBLAccessor.insert(hardAllocBatWrkMsgList);

        if (insertCount == hardAllocBatWrkMsgList.length) {

            this.normalCnt += hardAllocBatWrkMsgList.length;

        } else {

            this.errorCnt += hardAllocBatWrkMsgList.length;
            throw new S21AbendException(ZZMM0001E, new String[] {WORK_TABLE_ID, "SHPG_PLN_NUM", "bulk Insert" });
        }
    }

    private boolean postErrorMail() {

        final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();

        NWXC001001MailSubstituteString sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchId");
        sbsStr.setSbstStr(this.getClass().getSimpleName());
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchNm");
        sbsStr.setSbstStr("Hard Allocation");
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("ErrorInfo");
        sbsStr.setSbstStr(this.errorList.toString());
        sbsStrList.add(sbsStr);

        return new NWXC001001Mail().postMail(getGlobalCompanyCode(), "NWDB0060", MAIL_TEMPLATE_ID, sbsStrList);
    }

    /**
     * <pre>
     * Deletes all the records of the HARD_ALLOC_WRK table.
     * </pre>
     */
    private void removeHardAllocBatWrk() {

        HARD_ALLOC_BAT_WRKTMsg inMsg = new HARD_ALLOC_BAT_WRKTMsg();

        inMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        // S21_NA#9903 MOD Start
//      EZDTBLAccessor.removeByPartialKey(inMsg);
        S21FastTBLAccessor.removeByPartialValue(inMsg, new String[]{"glblCmpyCd"});
        // S21_NA#9903 MOD End
    }

    /**
     * <pre>
     * Print debug log.
     * </pre>
     * @param logmsg String
     */
    private void debug(String logmsg) {

        EZDDebugOutput.println(DEBUG_MSG_LVL, logmsg, this);
    }

    /**
     * <pre>
     * A partial character string of each number of bytes is acquired.
     * </pre>
     * @param str String
     * @param beginIndex int
     * @param endIndex int
     * @return The substring that was appointed.
     */
    private String subByteString(String str, int beginIndex, int endIndex) {

        String subStr = ZYPCommonFunc.subByteString(str, beginIndex, endIndex);

        if (ZYPCommonFunc.hasValue(subStr)) {
            return subStr;
        } else {
            return str;
        }
    }
}
