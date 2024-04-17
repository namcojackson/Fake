/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package com.canon.cusa.s21.batch.NWD.NWDB006002;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.HARD_ALLOC_BAT_WRKTMsg;
import business.db.INVTYTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_WH_CONDTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SOFT_ALLOCTMsg;
import business.db.VAR_CHAR_CONSTTMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC104001PMsg;
import business.parts.NWZC104002PMsg;
import business.parts.NWZC188001PMsg;
import business.parts.NWZC188001_shpgPlnNumListPMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NWZ.NWZC104001.NWZC104001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BACK_ORD_IMPCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.calendar.S21DateManagement;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Perform stock mortgage for order information stored away in worktable.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/01/23   Fujitsu         A.Suda          Create          N/A
 * 2009/08/07   Fujitsu         R.Watanabe      Update          N/A
 * 2009/10/29   Fujitsu         R.Watanabe      Update          1232
 * 2009/12/28   Fujitsu         R.Watanabe      Update          2815
 * 2010/01/07   Fujitsu         A.Suda          Update          2815 
 * 2010/02/11   Fujitsu         A.Suda          Update          2815  
 * 2010/04/01   Fujitsu         A.Suda          Update          5358
 * 2010/04/26   Fujitsu         N.Yamamoto      Update          5989
 * 2010/06/10   Fujitsu         A.Suda          Update          3181 
 * 2011/01/25   Fujitsu         A.Suda          Update          1150 
 * 2015/09/15   Fujitsu         A.Suda          Update          CNA(2.8 Allocation - SO/PO)
 * 2016/01/13   Fujitsu         A.Suda          Update          2441
 * 2016/06/28   Fujitsu         S.Yamamoto      Update          S21_NA#10530
 * 2016/11/14   Fujitsu         S.Takami        Update          S21_NA#9903
 * 2016/11/30   Fujitsu         M.Ohno          Update          S21_NA#14035
 * 2017/06/14   Fujitsu         T.Aoi           Update          S21_NA#18979
 * 2017/06/16   Fujitsu         M.Yamada        Update          S21_NA#18187
 * 2017/07/16   Fujitsu         T.Murai         Update          S21_NA#5872
 * 2017/12/25   Fujitsu         Mz.Takahashi    Update          S21_NA#23008
 * 2018/03/19   CITS            K.Ogino         Update          S21_NA#23206
 * 2019/02/22   Fujitsu         R.Nakamura      Update          S21_NA#30374
 * 2019/09/06   Fujitsu         T.Noguchi       Update          S21_NA#53087
 * 2022/08/09   Hitachi         T.NEMA          Update          QC#60319
 *</pre>
 */
public class NWDB006002 extends S21BatchMain {

    /** MessageID : Subject to process: [@] items. */
    private static final String NWDM0004I = "NWDM0004I";

    /** MessageID : Normally Ended: [@]items. */
    private static final String NWDM0008I = "NWDM0008I";

    /** MessageID : Error occurred:  [@] items. */
    private static final String NWDM0009I = "NWDM0009I";

    /** MessageID : Process skipped:  [@] items. */
    private static final String NWDM0011I = "NWDM0011I";

    /** MessageID : Not subject to process: [@] items. */
    private static final String NWDM0012I = "NWDM0012I";

    /** MessageID : An input parameter "@"  has not been set. */
    private static final String NWDM0001E = "NWDM0001E";

    /** MessageID : For input parameter @, only @ or a larger value can be registered. */
    private static final String NWDM0002E = "NWDM0002E";

    /** MessageID : Since the input parameter @ is invalid, process will be terminated. */
    private static final String NWDM0174E = "NWDM0174E";

    /** MessageID : Error occurred at the API called upon. @ */
    private static final String NWDM0176E = "NWDM0176E";

    /** MessageID : It failed to register Mail. */
    private static final String NWCM0060E = "NWCM0060E";

    // Del Start 2019/02/22 QC#30374
//    /** MessageID : The entered "Serial Number" dose not exist.Key[@] */
//    private static final String NWDM0238E = "NWDM0238E";
    // Del End 2019/02/22 QC#30374

//    /** MessageID : The entered "Serial Number" is duplicated.Key[@] */
//    private static final String NWDM0239E = "NWDM0239E";

    /** Program ID */
    private static final String PROGRAM_ID = NWDB006002.class.getSimpleName();

    /** DEBUG_MSG_LVL */
    private static final int DEBUG_MSG_LVL = 1;

    /** mailTemplateId */
    private static final String MAIL_TEMPLATE_ID = "NWDB0060M001";

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Term cd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Processing Count */
    private int procCnt = 0;

    /** Untreated Count */
    private int unProcCnt = 0;

    /** Not applicable Count */
    private int chkOffCnt = 0;

    /** normal Count */
    private int normalCnt = 0;

    /** error Count */
    private int errorCnt = 0;

    /** error List */
    private List<String>  errorList =  new ArrayList<String>();

    /** Max List Size */
    private int MAX_LIST_SIZE_999 = 999;

    /** sales date */
    private String slsDt = null;

    /** Machine Master Update API mode: ALLOCATION_ON */
    private static final String MODE_ALLOCATION_ON = "18";

    /** Dummy WH CD **/
    private String VAR_CHAR_CONST_NM_CR_AND_BILL_ONLY_DUMMY_WH_CD = "CR_AND_BILL_ONLY_DUMMY_WH_CD";

    /** Shipping Plan Number List */
    private List<String> shpgPlnNumList = new ArrayList<String>();

    /** Set CPO Order Number */
    private String setCpoOrdNum = "";

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {

        new NWDB006002().executeBatch(PROGRAM_ID);
    }

    @Override
    protected void initRoutine() {

        if (!isEnvParameter()) {
            setMainRoutineEnd();
            this.termCd = TERM_CD.ABNORMAL_END;
            return;
        }

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        String glblCmpyCd = getGlobalCompanyCode();
        String batGrpCd = S21BatchUtil.getUserVariable1();
        this.slsDt = S21DateManagement.getBatProcDate(glblCmpyCd);

        // ========================================
        // 1. getInformation : OrderInformation(Hard Allocation Batch
        // Work)
        // ========================================
        List<Map<String, Object>> workList = getWorkList(glblCmpyCd, batGrpCd);

        if (isEmpty(workList)) {
            return;
        }

        // ========================================
        // 2. getInformation : ordCatgCtxTpCd = [RETAIL_EQUIP_ORDER]
        // ========================================

        List<Map<String, Object>> ordCatgCtxInfoList = getOrdCatgCtxInfo();

        String checkedCpoOrdNum = "";
        String checkedConfigKey = "";
        boolean allocCheckByConfigFlg = false;
        boolean skipFlg = false;
        boolean specialLineFlg = false; //CriticalLine OR EssentialLine
        boolean nomalLineFlg = false;

        List<Map<String, Object>> logicalRemoveWorkList = new ArrayList<Map<String, Object>>(0); // 2016/11/14 S21_NA#9903 Add
        List<Map<String, Object>> dplyOrdStsUpdWorkList = new ArrayList<Map<String, Object>>(0); // 2017/06/14 S21_NA#18979 Add

        for (int i = 0; i < workList.size(); i++) {

            this.procCnt++;

            Map<String, Object> hardAllocInfo = workList.get(i);

            String cpoOrdNum = (String) hardAllocInfo.get("CPO_ORD_NUM");
            String currentConfigKey = (String) hardAllocInfo.get("ORD_CONFIG_KEY");
            String holdRsnCd = (String) hardAllocInfo.get("HLD_RSN_CD");
            String impctTpCd = (String) hardAllocInfo.get("BACK_ORD_IMPCT_TP_CD");

            // ========================================
            // 3. check : RETAIL_EQUIP_ORDER
            // ========================================
            // If key exists in ORD_CATG_BIZ_CTX ("RETAIL_EQUIP_ORDER"), 
            // check allocation by configuration.
            // allocCheckByConfigFlg = true

            if (!checkedCpoOrdNum.equals(cpoOrdNum)) {
                allocCheckByConfigFlg = checkOrdCatgCtx(hardAllocInfo, ordCatgCtxInfoList);
            }

            // Mod Start 2016/11/30 M.Ohno S21_NA#14035
            // specialLineFlg = (BACK_ORD_IMPCT_TP.CRITICAL.equals(impctTpCd) || BACK_ORD_IMPCT_TP.ESSENTIAL.equals(impctTpCd)) && allocCheckByConfigFlg;
            specialLineFlg = BACK_ORD_IMPCT_TP.CRITICAL.equals(impctTpCd) && allocCheckByConfigFlg;
            // nomalLineFlg = !BACK_ORD_IMPCT_TP.CRITICAL.equals(impctTpCd) && !BACK_ORD_IMPCT_TP.ESSENTIAL.equals(impctTpCd);
            nomalLineFlg = !BACK_ORD_IMPCT_TP.CRITICAL.equals(impctTpCd);
            // Mod End   2016/11/30 M.Ohno S21_NA#14035

            // ========================================
            // 4. check : Commit/Rollback
            // ========================================
            if (hasValue(checkedConfigKey)) {
                if (checkedConfigKey.equals(currentConfigKey)) {
                    if (skipFlg) {
                        this.unProcCnt++;
                        this.normalCnt++;
                        continue;
                    } else if (!skipFlg && nomalLineFlg) {
                        commit();
                    }
                } else {
                    if (!skipFlg) {
                        commit();
                    }
                    skipFlg = false;
                }
            }

            checkedConfigKey = currentConfigKey;
            checkedCpoOrdNum = cpoOrdNum;

            // ========================================
            // 5. check : has hold
            // ========================================
            if (hasValue(holdRsnCd)) {
                if (specialLineFlg) {
                    skipFlg = true;
                    setRemoveAllocBatchForRtlEqpmntOrd(workList, hardAllocInfo, logicalRemoveWorkList); // 2016/11/14 S21_NA#9903 Add
                }
                // 2017/06/14 S21_NA#18979 Add Start
                dplyOrdStsUpdWorkList.add(hardAllocInfo);
                // 2017/06/14 S21_NA#18979 Add End
                rollback();
                this.normalCnt++;
                this.chkOffCnt++;
                continue;
            }

            // Add Start S21_NA#5872
            // ========================================
            // 5. check2 : has not critical merchandise in HARD_ALLOC_BAT_WRK
            // ========================================
            if (!checkCriticalMDSE(hardAllocInfo)) {
                skipFlg = true;
                setRemoveAllocBatchByOrdConfiKey(workList, hardAllocInfo, logicalRemoveWorkList);

                rollback();
                this.normalCnt++;
                this.chkOffCnt++;
                continue;
            }
            // Add End S21_NA#5872

            // ========================================
            // 6. execute : MachineMasterAllocation
            // ========================================
            if (allocCheckByConfigFlg) {
                boolean machMstrResultFlg = execSvcMachMstrAllocation(hardAllocInfo);
                if (!machMstrResultFlg) {
                    if (specialLineFlg) {
                        skipFlg = true;
                        setRemoveAllocBatchForRtlEqpmntOrd(workList, hardAllocInfo, logicalRemoveWorkList); // 2016/11/14 S21_NA#9903 Add
                    }
                    // 2017/06/14 S21_NA#18979 Add Start
                    dplyOrdStsUpdWorkList.add(hardAllocInfo);
                    // 2017/06/14 S21_NA#18979 Add End
                    rollback();
                    this.errorCnt++;
                    continue;
                }
            }

            // ========================================
            // 7. execute : Allocation
            // ========================================

            // 7-1 getInformation : Inventory
            List<NWZC104002PMsg> inventoryInfoList = getInventoryInfoList(hardAllocInfo);
            if (isEmpty(inventoryInfoList)) {
                if (specialLineFlg) {
                    setRemoveAllocBatchForRtlEqpmntOrd(workList, hardAllocInfo, logicalRemoveWorkList); // 2016/11/14 S21_NA#9903 Add
                    skipFlg = true;
                }
                // 2017/06/14 S21_NA#18979 Add Start
                dplyOrdStsUpdWorkList.add(hardAllocInfo);
                // 2017/06/14 S21_NA#18979 Add End
                rollback();
                this.normalCnt++;
                this.chkOffCnt++;
                continue;
            }

            // 7-2 execute : HardAllocation
            boolean allocResultFlg = callHardAllocationAPI(hardAllocInfo, inventoryInfoList, specialLineFlg);
            if (!allocResultFlg) {
                if (specialLineFlg) {
                    skipFlg = true;
                    setRemoveAllocBatchForRtlEqpmntOrd(workList, hardAllocInfo, logicalRemoveWorkList); // 2016/11/14 S21_NA#9903 Add
                }
                // 2017/06/14 S21_NA#18979 Add Start
                dplyOrdStsUpdWorkList.add(hardAllocInfo);
                // 2017/06/14 S21_NA#18979 Add End 
                rollback();
                // 2016/11/14 S21_NA#9903 Add Start
                continue;
            }

            this.normalCnt++;

        }

        if (!skipFlg) {
            if (this.shpgPlnNumList.size() > 0) {
                executeDplyOrdStsUpdApi(this.setCpoOrdNum);
            }
            commit();
        } else { // 2016/11/14 S21_NA#9903 Add Start
            rollback();
        }  // 2016/11/14 S21_NA#9903 Add End

        // 2016/11/14 S21_NA#9903 Add Start
        if (!logicalRemoveWorkList.isEmpty()) {
            logicalRemoveAllocBatch(logicalRemoveWorkList);
            commit();
        }
        // 2016/11/14 S21_NA#9903 Add End

        // 2017/06/14 S21_NA#18979 Add Start
        if (!dplyOrdStsUpdWorkList.isEmpty()) {
            //Call Display Order Status Update API
            callDplyOrdStsUpdApiList(dplyOrdStsUpdWorkList);
        }
    }

    @Override
    protected void termRoutine() {

        // Send Error Mail
        if (!isEmpty(this.errorList)) {

            if (!postErrorMail()) {
                this.termCd = TERM_CD.ABNORMAL_END;
                throw new S21AbendException(NWCM0060E);
            }
            this.termCd = TERM_CD.WARNING_END;
        }

        S21InfoLogOutput.println(NWDM0004I, new String[] {String.valueOf(this.procCnt) });

        S21InfoLogOutput.println(NWDM0009I, new String[] {String.valueOf(this.errorCnt) });

        S21InfoLogOutput.println(NWDM0008I, new String[] {String.valueOf(this.normalCnt) });

        S21InfoLogOutput.println(NWDM0011I, new String[] {String.valueOf(this.unProcCnt) });

        S21InfoLogOutput.println(NWDM0012I, new String[] {String.valueOf(this.chkOffCnt) });

        setTermState(this.termCd, this.normalCnt, this.errorCnt, this.procCnt);
    }

    /**
     * <pre>
     * get Order Category Business Context Information.
     * </pre>
     * @return search results.
     */
    private List<Map<String, Object>> getOrdCatgCtxInfo() {

        String glblCmpyCd = getGlobalCompanyCode();

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);

        return ssmBatchClient.queryObjectList("getOrderCategoryBusinessContext", param);
    }

    /**
     * <pre>
     * check DS Order Category Code.
     * </pre>
     * @param hardAllocationInfo Map<String, Object>
     * @param ordCatgCtxInfoList List<Map<String, Object>>
     * @return true/AllocationCheck by configuration . false/NOT
     * AllocationCheck.
     */
    private boolean checkOrdCatgCtx(Map<String, Object> hardAllocationInfo, List<Map<String, Object>> ordCatgCtxInfoList) {

        String ordCatgCd = (String) hardAllocationInfo.get("DS_ORD_CATG_CD");
        String ordTpCd = (String) hardAllocationInfo.get("DS_ORD_TP_CD");
        String ordRsnCd = (String) hardAllocationInfo.get("DS_ORD_RSN_CD");

        for (int i = 0; i < ordCatgCtxInfoList.size(); i++) {

            Map<String, Object> ordCatgCtxInfo = ordCatgCtxInfoList.get(i);

            String mstrOrdCatgCd = (String) ordCatgCtxInfo.get("DS_ORD_CATG_CD");
            String mstrOrdTpCd = (String) ordCatgCtxInfo.get("DS_ORD_TP_CD");
            String mstrOrdRsnCd = (String) ordCatgCtxInfo.get("DS_ORD_RSN_CD");

            if (!hasValue(mstrOrdCatgCd) && !hasValue(mstrOrdTpCd) && !hasValue(mstrOrdRsnCd)) {
                continue;
            }

            if (hasValue(mstrOrdCatgCd) && hasValue(mstrOrdTpCd) && hasValue(mstrOrdRsnCd)) {

                if (mstrOrdCatgCd.equals(ordCatgCd) && mstrOrdTpCd.equals(ordTpCd) && mstrOrdRsnCd.equals(ordRsnCd)) {
                    return true;
                }

            }
            if (hasValue(mstrOrdCatgCd) && hasValue(mstrOrdTpCd) && !hasValue(mstrOrdRsnCd)) {

                if (mstrOrdCatgCd.equals(ordCatgCd) && mstrOrdTpCd.equals(ordTpCd)) {
                    return true;
                }

            }
            if (hasValue(mstrOrdCatgCd) && !hasValue(mstrOrdTpCd) && !hasValue(mstrOrdRsnCd)) {

                if (mstrOrdCatgCd.equals(ordCatgCd)) {
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * <pre>
     * Acquire a parameter of the Hard Allocation API practice.
     * </pre>
     * @param hardAllocationInfo Map<String, Object>
     * @return parameter of the Hard Allocation API
     */
    private List<NWZC104002PMsg> getInventoryInfoList(Map<String, Object> hardAllocationInfo) {

        // 2019/09/06 S21_NA#53087 Mod Start
        //String glblCmpyCd = (String) hardAllocationInfo.get("GLBL_CMPY_CD");
        //String spMdseCd = (String) hardAllocationInfo.get("SP_MDSE_CD");
        //String whCd = (String) hardAllocationInfo.get("WH_CD");
        //
        //List<INVTYTMsg> invtyMsgList = getInvtyList(glblCmpyCd, spMdseCd, whCd, null, );
        List<INVTYTMsg> invtyMsgList = getInvtyList(hardAllocationInfo, null);
        // 2019/09/06 S21_NA#53087 Mod End

        List<NWZC104002PMsg> inventoryInfoList = new ArrayList<NWZC104002PMsg>();

        for (INVTYTMsg invtyMsg : invtyMsgList) {
            if (BigDecimal.ZERO.compareTo(invtyMsg.invtyAvalQty.getValue()) < 0) {

                NWZC104002PMsg inventoryInfo = new NWZC104002PMsg();
                inventoryInfo.mdseCd.setValue(invtyMsg.mdseCd.getValue());
                inventoryInfo.invtyLocCd.setValue(invtyMsg.invtyLocCd.getValue());
                inventoryInfo.locStsCd.setValue(invtyMsg.locStsCd.getValue());
                inventoryInfo.stkStsCd.setValue(invtyMsg.stkStsCd.getValue());
                inventoryInfo.invtyAvalQty.setValue(invtyMsg.invtyAvalQty.getValue());
                inventoryInfoList.add(inventoryInfo);
            }
        }

        return inventoryInfoList;
    }

    /**
     * <pre>
     * Call Machine Master Update API NSZC001001.
     * </pre>
     * @param hardAllocationInfo Map<String, Object>
     * @return true/normal. false/Abnormality.
     */
    private boolean execSvcMachMstrAllocation(Map<String, Object> hardAllocationInfo) {

        BigDecimal machMstrPk = (BigDecimal) hardAllocationInfo.get("SVC_MACH_MSTR_PK");
        String serNum = (String) hardAllocationInfo.get("SER_NUM");

        // 2016/06/28 S21_NA#10530 Mod Start
//        if (!hasValue(machMstrPk)) {
//            return true;
//        }
        if (!hasValue(machMstrPk) && !hasValue(serNum)) {
            return true;
        }
        // 2016/06/28 S21_NA#10530 Mod End

        String glblCmpyCd = (String) hardAllocationInfo.get("GLBL_CMPY_CD");
        String spMdseCd = (String) hardAllocationInfo.get("SP_MDSE_CD");
        String invtyLocCd = (String) hardAllocationInfo.get("INVTY_LOC_CD");
        String shpgPlnNum = (String) hardAllocationInfo.get("SHPG_PLN_NUM");
        String trxHdrNum = (String) hardAllocationInfo.get("TRX_HDR_NUM");
        String trxLineNum = (String) hardAllocationInfo.get("TRX_LINE_NUM");
        String trxLineSubNum = (String) hardAllocationInfo.get("TRX_LINE_SUB_NUM");
        // QC#23206
        String stkStsCd      = (String) hardAllocationInfo.get("STK_STS_CD");

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("serNum", serNum); // 2016/06/28 S21_NA#10530
        param.put("mdseCd", spMdseCd);
        param.put("machMstrPk", machMstrPk);
        param.put("invtyLocCd", invtyLocCd);
        // QC#23206
        param.put("stkStsCd", stkStsCd);

        List<Map<String, Object>> svcMachMstrList = ssmBatchClient.queryObjectList("getSvcMachMstrStatus", param);

        if (svcMachMstrList.isEmpty()) {
            // Del Start 2019/02/22 QC#30374
//            StringBuilder sb = new StringBuilder();
//            sb.append("Serial Number = ").append(serNum).append(" , Merchandise Code = ").append(spMdseCd).append(" , Inventory Location Code = ").append(invtyLocCd).append(" , Stock Status Code = ").append(stkStsCd);
//            setErrList(NWDM0238E, sb);
            // Del End 2019/02/22 QC#30374
            return false;
        }

        // QC#18187
//        if (svcMachMstrList.size() > 1) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("Serial Number = ").append(serNum).append(" , Merchandise Code = ").append(spMdseCd).append(" , Inventory Location Code = ").append(invtyLocCd);
//            setErrList(NWDM0239E, sb);
//            return false;
//        }

        Map<String, Object> svcMachMstrInfo = svcMachMstrList.get(0);
        hardAllocationInfo.put("SP_MDSE_CD", (String) svcMachMstrInfo.get("MDSE_CD")); // QC#18187
        hardAllocationInfo.remove("SHPG_PLN_ORD_TAKE_MDSE_CD"); // QC#18187

        //        String avalFlg = (String) svcMachMstrInfo.get("SVC_MACH_MAINT_AVAL_FLG"); // QC#18187
        machMstrPk = (BigDecimal) svcMachMstrInfo.get("SVC_MACH_MSTR_PK"); // 2016/06/28 S21_NA#10530

//        if (ZYPConstant.FLG_ON_Y.equals(avalFlg)) { // QC#18187

        NSZC001001PMsg inNSZC001001PMsg = new NSZC001001PMsg();

        ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.xxModeCd, MODE_ALLOCATION_ON);
        ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.svcMachMstrPk, machMstrPk);
        ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.serNum, serNum);
        // ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.cpoOrdNum,
        // trxHdrNum);
        // ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.cpoDtlLineNum,
        // trxLineNum);
        // ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.cpoDtlLineSubNum,
        // trxLineSubNum);
        ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.trxHdrNum, trxHdrNum);
        ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.trxLineNum, trxLineNum);
        ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.trxLineSubNum, trxLineSubNum);

        ZYPEZDItemValueSetter.setValue(inNSZC001001PMsg.trxLineSubNum, trxLineSubNum);
        NSZC001001 machMstrUpdApi = new NSZC001001();
        machMstrUpdApi.execute(inNSZC001001PMsg, ONBATCH_TYPE.BATCH);

        boolean nomalEndFlg = true;
        for (int i = 0; i < inNSZC001001PMsg.xxMsgIdList.getValidCount(); i++) {
            if (inNSZC001001PMsg.xxMsgIdList.no(i).xxMsgId.getValue().endsWith("E")) {
                nomalEndFlg = false;
                StringBuilder sb = new StringBuilder();
                sb.append("Shipping Plan Number = ").append(shpgPlnNum);
                setErrList(inNSZC001001PMsg.xxMsgIdList.no(i).xxMsgId.getValue(), sb);
                S21InfoLogOutput.println(NWDM0176E, new String[] {sb.toString() });
            }
        }
        return nomalEndFlg;

            // QC#18187
//        } else {
//            return false;
//        }

    }

    /**
     * <pre>
     * set Error List.
     * </pre>
     * @param errorMsg String
     * @param errorKey StringBuilder
     */
    private void setErrList(String errorMsg, StringBuilder errorKey) {

        S21InfoLogOutput.println(errorMsg, new String[] {errorKey.toString() });
        this.errorList.add("  Error MessageID : " + errorMsg + "(" + errorKey.toString() + " )");

    }

    /**
     * <pre>
     * Call HardAllocationAPI NWZC104001.
     * </pre>
     * @param hardAllocationInfo Map<String, Object>
     * @param inventoryInfoList List<NWZC104002PMsg>
     * @param notPartialAllocation boolean
     * @return true/normal. false/Abnormality.
     */
    private boolean callHardAllocationAPI(Map<String, Object> hardAllocationInfo, List<NWZC104002PMsg> inventoryInfoList, boolean notPartialAllocation) {

        String glblCmpyCd = (String) hardAllocationInfo.get("GLBL_CMPY_CD");
        String shpgPlnNum = (String) hardAllocationInfo.get("SHPG_PLN_NUM");
        BigDecimal softAllocPk = (BigDecimal) hardAllocationInfo.get("SOFT_ALLOC_PK");

        SHPG_PLNTMsg shpgPlnMsg = findShpgPlnByKey(glblCmpyCd, shpgPlnNum);

        if (shpgPlnMsg == null) {
            this.normalCnt++;
            this.chkOffCnt++;
            return false;
        }

        // CPO
        findCpoByKeyForUpdate(glblCmpyCd, shpgPlnMsg.trxHdrNum.getValue());
        // CPO_DTL
        findCpoDtlByKeyForUpdate(glblCmpyCd, shpgPlnMsg.trxHdrNum.getValue(), shpgPlnMsg.trxLineNum.getValue(), shpgPlnMsg.trxLineSubNum.getValue());
        // SHPG_PLN
        findShpgPlnByKeyForUpdate(glblCmpyCd, shpgPlnNum);

        SOFT_ALLOCTMsg softAllocMsg = getSoftAlloc(glblCmpyCd, softAllocPk);

        MDSETMsg mdseMsg = findMdseByKey(glblCmpyCd, shpgPlnMsg.mdseCd.getValue());

        NWZC104001PMsg inNWZC104001PMsg = createHardAllocationParam(hardAllocationInfo, shpgPlnMsg, mdseMsg, softAllocMsg);

        return executeHardAllocationAPI(shpgPlnNum, inNWZC104001PMsg, hardAllocationInfo, inventoryInfoList, notPartialAllocation);
    }

    /**
     * <pre>
     * Make a parameter of the Hard Allocation API practice.
     * </pre>
     * @param glblCmpyCd String
     * @param workMsg HARD_ALLOC_BAT_WRKTMsg
     * @param shpgPlnMsg SHPG_PLNTMsg
     * @param mdseMsg MDSETMsg
     * @param softAllocMsg SOFT_ALLOCTMsg
     * @return parameter of the Hard Allocation API
     */
    private NWZC104001PMsg createHardAllocationParam(Map<String, Object> hardAllocationInfo, SHPG_PLNTMsg shpgPlnMsg, MDSETMsg mdseMsg, SOFT_ALLOCTMsg softAllocMsg) {

        String glblCmpyCd = (String) hardAllocationInfo.get("GLBL_CMPY_CD");
        String shpgPlnOrdTakeMdseCd = (String) hardAllocationInfo.get("SHPG_PLN_ORD_TAKE_MDSE_CD");
        String softAllocOrdTakeMdseCd = (String) hardAllocationInfo.get("SOFT_ALLOC_ORD_TAKE_MDSE_CD");
        BigDecimal softAllocPk = (BigDecimal) hardAllocationInfo.get("SOFT_ALLOC_PK");

        NWZC104001PMsg inNWZC104001PMsg = new NWZC104001PMsg();

        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxRqstTpCd, NWZC104001.REQ_TP_NEW);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxPrtlAcptFlg, ZYPConstant.FLG_ON_1);

        if (ZYPCommonFunc.hasValue(shpgPlnOrdTakeMdseCd)) {
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxItemFlipAcptFlg, ZYPConstant.FLG_ON_1);
        } else {
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxItemFlipAcptFlg, ZYPConstant.FLG_OFF_0);
        }

        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxWhFlipAcptFlg, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.trxSrcTpCd, shpgPlnMsg.trxSrcTpCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.trxHdrNum, shpgPlnMsg.trxHdrNum);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.trxLineNum, shpgPlnMsg.trxLineNum);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.trxLineSubNum, shpgPlnMsg.trxLineSubNum);

        if (ZYPCommonFunc.hasValue(shpgPlnOrdTakeMdseCd)) {
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxOrdTakeMdseFlg, ZYPConstant.FLG_OFF_0);
        } else {
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxOrdTakeMdseFlg, ZYPConstant.FLG_ON_1);
        }

//        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.mdseCd, shpgPlnMsg.mdseCd); // QC#18187
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.mdseCd, (String) hardAllocationInfo.get("SP_MDSE_CD")); // QC#18187
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.invtyLocCd, shpgPlnMsg.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.stkStsCd, shpgPlnMsg.stkStsCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.distTpCd, mdseMsg.invtyDistTpCd);
        // Mod Start QC#5872
        // ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.hardAllocTpCd, getInvtyHardAllocTpCd(glblCmpyCd, shpgPlnMsg.invtyLocCd.getValue(), mdseMsg));
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.hardAllocTpCd, HARD_ALLOC_TP.AUTO);
        // Mod End QC#5872

        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.reqFrtChrgMethCd, shpgPlnMsg.reqFrtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.reqFrtChrgToCd, shpgPlnMsg.reqFrtChrgToCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.shpgSvcLvlCd, shpgPlnMsg.reqShpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.rddDt, shpgPlnMsg.rddDt);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.rsdDt, shpgPlnMsg.rsdDt);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.billToCustCd, shpgPlnMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.sellToCustCd, shpgPlnMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.shipToCustCd, shpgPlnMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.shipToStCd, shpgPlnMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.shipToPostCd, shpgPlnMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.shipCpltCd, shpgPlnMsg.shipCpltCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.setItemShipCpltFlg, shpgPlnMsg.setItemShipCpltFlg);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.uomCpltFlg, shpgPlnMsg.uomCpltFlg);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.custUomCd, shpgPlnMsg.custUomCd);
        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.softAllocPk, softAllocPk);

        if (ZYPCommonFunc.hasValue(softAllocOrdTakeMdseCd)) {
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxOrdTakeMdseFlg_SA, ZYPConstant.FLG_OFF_0);
        } else {
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxOrdTakeMdseFlg_SA, ZYPConstant.FLG_ON_1);
        }

        if (softAllocMsg == null) {
            inNWZC104001PMsg.mdseCd_SA.clear();
            inNWZC104001PMsg.invtyLocCd_SA.clear();
            inNWZC104001PMsg.shpgSvcLvlCd_SA.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.mdseCd_SA, softAllocMsg.hardAllocMdseCd);
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.invtyLocCd_SA, softAllocMsg.hardAllocInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.shpgSvcLvlCd_SA, softAllocMsg.hardAllocShpgSvcLvlCd);
        }

        if (softAllocMsg == null) {
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxRqstQty, shpgPlnMsg.ordQty);
        } else {
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxRqstQty, softAllocMsg.softAllocQty);
        }

        ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.slsDt, this.slsDt);

        // 2019/09/06 S21_NA#53087 Add Start
        if (!isMdse8digitOrder(hardAllocationInfo)) {
            ZYPEZDItemValueSetter.setValue(inNWZC104001PMsg.xxRsltFlg_M, ZYPConstant.FLG_ON_Y);
        }
        // 2019/09/06 S21_NA#53087 Add End

        return inNWZC104001PMsg;
    }

    /**
     * <pre>
     * Execute HardAllocationAPI NWZC104001.
     * </pre>
     * @param shpgPlnNum String
     * @param inNWZC104001PMsg NWZC104001PMsg
     * @param hardAllocationInfo Map<String, Object>
     * @param inventoryInfoList List<NWZC104002PMsg>
     * @param notPartialAllocation boolean
     * @return true/normal. false/Abnormality.
     */
    private boolean executeHardAllocationAPI(String shpgPlnNum, NWZC104001PMsg inNWZC104001PMsg, Map<String, Object> hardAllocationInfo, List<NWZC104002PMsg> inventoryInfoList, boolean notPartialAllocation) {

        NWZC104001 hardAllocationApi = new NWZC104001();
        hardAllocationApi.execute(inNWZC104001PMsg, inventoryInfoList, ONBATCH_TYPE.BATCH);

        boolean isErrorFlg = false;

        for (int i = 0; i < inNWZC104001PMsg.xxMsgIdList.getValidCount(); i++) {
            if (inNWZC104001PMsg.xxMsgIdList.no(i).xxMsgId.getValue().endsWith("E")) {
                isErrorFlg = true;
                S21InfoLogOutput.println(inNWZC104001PMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                S21InfoLogOutput.println(NWDM0176E, new String[] {"Shipping Plan Number = " + shpgPlnNum });
                this.errorList.add("  Error MessageID : " + inNWZC104001PMsg.xxMsgIdList.no(i).xxMsgId.getValue() + "(" + " Shipping Plan Number = " + shpgPlnNum + " )");
            }
        }

        if (isErrorFlg) {
            this.errorCnt++;
            return false; // NG
        } else {
            if (notPartialAllocation) {
                BigDecimal totalHardAllocQty = ZERO;
                BigDecimal reqAllocQty = inNWZC104001PMsg.xxRqstQty.getValue();

                for (int i = 0; i < inNWZC104001PMsg.AllocationInfo.getValidCount(); i++) {
                    totalHardAllocQty = totalHardAllocQty.add(inNWZC104001PMsg.AllocationInfo.no(i).hardAllocQty.getValue());
                }
                if (totalHardAllocQty.compareTo(reqAllocQty) == 0) {
                    return true; // OK
                } else {
                    shpgPlnNumList.add((String) hardAllocationInfo.get("SHPG_PLN_NUM")); // QC#18187

                    this.normalCnt++;
                    this.chkOffCnt++;
                    return false; // NG (partial allocation)
                }

            } else {
                BigDecimal totalHardAllocQty = ZERO;

                for (int i = 0; i < inNWZC104001PMsg.AllocationInfo.getValidCount(); i++) {
                    totalHardAllocQty = totalHardAllocQty.add(inNWZC104001PMsg.AllocationInfo.no(i).hardAllocQty.getValue());
                }
                // QC#18187
                if (totalHardAllocQty.compareTo(BigDecimal.ZERO) == 0) {
                    this.normalCnt++;
                    this.chkOffCnt++;
                    return false;
                }
                return true; // OK
            }
        }
    }

    // 2017/06/14 S21_NA#18979 Add Start
    /**
     * <pre>
     * Display Order Status Update API List
     * </pre>
     * @param dplyOrdStsUpdWorkList List<Map<String, Object>>
     */
    private void callDplyOrdStsUpdApiList(List<Map<String, Object>> dplyOrdStsUpdWorkList) {

        int index;
        for (index  = 0; index < dplyOrdStsUpdWorkList.size(); index++) {
            Map<String, Object> hardAllocationInfo = dplyOrdStsUpdWorkList.get(index);
            String cpoOrdNum = (String) hardAllocationInfo.get("CPO_ORD_NUM");

            if (shpgPlnNumList.size() >= MAX_LIST_SIZE_999
                    || (ZYPCommonFunc.hasValue(setCpoOrdNum) && !setCpoOrdNum.equals(cpoOrdNum))) {

                executeDplyOrdStsUpdApi(setCpoOrdNum);
            }

            //Set Shipping Plan List
            shpgPlnNumList.add((String) hardAllocationInfo.get("SHPG_PLN_NUM"));

            setCpoOrdNum = cpoOrdNum;

            if (index == dplyOrdStsUpdWorkList.size() - 1) {
                executeDplyOrdStsUpdApi(setCpoOrdNum);
            }
        }
    }
    // 2017/06/14 S21_NA#18979 Add End

    /**
     * <pre>
     * Execute Display Order Status Update API
     * </pre>
     * @param cpoOrdNum String
     */
    private boolean executeDplyOrdStsUpdApi(String cpoOrdNum) {

        NWZC188001PMsg pMsg = new NWZC188001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoOrdNum);

        for (int i = 0; i < shpgPlnNumList.size(); i++) {
            NWZC188001_shpgPlnNumListPMsg shpgPlnNumListPMsg = pMsg.shpgPlnNumList.no(i);
            ZYPEZDItemValueSetter.setValue(shpgPlnNumListPMsg.shpgPlnNum, shpgPlnNumList.get(i));
            pMsg.shpgPlnNumList.setValidCount(i + 1);
        }

        new NWZC188001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                return false;
            }
        }

        for (int i = 0; i < pMsg.shpgPlnNumList.getValidCount(); i++) {
            NWZC188001_shpgPlnNumListPMsg shpgPlnNumListPMsg = pMsg.shpgPlnNumList.no(i);

            if (ZYPCommonFunc.hasValue(shpgPlnNumListPMsg.xxMsgId)) {
                S21InfoLogOutput.println(shpgPlnNumListPMsg.xxMsgId.getValue());
                return false;
            }
        }
        shpgPlnNumList.clear();
        return true;
    }

    /**
     * <pre>
     * Check parameter.
     * </pre>
     * @return true/normal. false/Abnormality.
     */
    private boolean isEnvParameter() {

        String chkVarUser1 = S21BatchUtil.getUserVariable1();

        if (!ZYPCommonFunc.hasValue(chkVarUser1)) {
            S21InfoLogOutput.println(NWDM0001E, new String[] {"Batch Group Code" });
            S21InfoLogOutput.println(NWDM0174E, new String[] {"Batch Group Code" });
            return false;
        }

        if (!isBatchGroupCode(chkVarUser1)) {
            S21InfoLogOutput.println(NWDM0002E, new String[] {"Batch Group Code", "1" });
            S21InfoLogOutput.println(NWDM0174E, new String[] {"Batch Group Code" });
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Perform the check that is Batch Group Code.
     * </pre>
     * @param chkVarUser1 String
     * @return true/normal. false/abnormality.
     */
    private boolean isBatchGroupCode(String chkVarUser1) {

        int batGrpCd = 0;
        try {
            batGrpCd = Integer.parseInt(chkVarUser1);
        } catch (NumberFormatException e) {
            return false;
        }

        return batGrpCd > 0;
    }

    /**
     * <pre>
     * Perform the check that is ORD_TAKE_MDSE_CD.
     * </pre>
     * @param mdseCd String
     * @return true/applicable. false/not applicable
     */
    private boolean isOrdTakeMdseCd(String mdseCd) {

        return ZYPCommonFunc.hasValue(mdseCd) && mdseCd.length() <= getOrdTaleMdseCdDigit();
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
     * When SOFT_ALLOC_PK is appointed, repay search results.
     * </pre>
     * @param glblCmpyCd String
     * @param softAllocPk BigDecimal
     * @return search results.
     */
    private SOFT_ALLOCTMsg getSoftAlloc(String glblCmpyCd, BigDecimal softAllocPk) {

        SOFT_ALLOCTMsg softAllocMsg = null;

        if (ZYPCommonFunc.hasValue(softAllocPk)) {
            softAllocMsg = findSoftAllocByKeyForUpdate(glblCmpyCd, softAllocPk);
        }

        return softAllocMsg;
    }

    /**
     * <pre>
     * Acquire INVTY_HARD_ALLOC_TP_CD.
     * </pre>
     * @param glblCmpyCd String
     * @param whCd String
     * @param mdseMsg MDSETMsg
     * @return INVTY_HARD_ALLOC_TP_CD.
     */
//    private String getInvtyHardAllocTpCd(String glblCmpyCd, String whCd, MDSETMsg mdseMsg) {
//
//        MDSE_WH_CONDTMsg mdseWhCondMsg = findMdseWhCondByKey(glblCmpyCd, mdseMsg.mdseCd.getValue(), whCd);
//
//        if (mdseWhCondMsg == null) {
//            return mdseMsg.invtyHardAllocTpCd.getValue();
//        } else {
//            return mdseWhCondMsg.invtyHardAllocTpCd.getValue();
//        }
//    }

    /**
     * <pre>
     * Repay length of ORD_TAKE_MDSE_CD.
     * </pre>
     * @return length of ORD_TAKE_MDSE_CD.
     */
    private int getOrdTaleMdseCdDigit() {

        ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
        return ordTakeMdseMsg.getAttr("ordTakeMdseCd").getDigit();
    }

    /**
     * <pre>
     * Acquire information to perform HardAllocation API.
     * </pre>
     * @param glblCmpyCd String
     * @param batGrpCd String
     * @return search results.
     */
    private List<Map<String, Object>> getWorkList(String glblCmpyCd, String batGrpCd) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("batGrpCd", batGrpCd);
        param.put("hldLvlCpo", HLD_LVL.CPO_LEVEL);
        param.put("hldLvlCpoDtl", HLD_LVL.CPO_DETAIL_LEVEL);
        param.put("hldLvlShpg", HLD_LVL.SHIPPING_PLAN_LEVEL);
        param.put("trxLineSubNum", "000");
        // START 2022/08/09 T.NEMA [QC#60319, MOD]
        param.put("mdseItemTp", MDSE_ITEM_TP.SET);
        // END   2022/08/09 T.NEMA [QC#60319, MOD]
        return ssmBatchClient.queryObjectList("getWorkList", param);
    }

    /**
     * <pre>
     * Acquire INVTY information.
     * </pre>
     * @param hardAllocInfo Back Order Batch Work data Map
     * @param stkStsCd String
     * @return search results.
     */
    // 2019/09/06 S21_NA#53087 Mod Start
    //private List<INVTYTMsg> getInvtyList(String glblCmpyCd, String mdseCd, String invtyLocCd, String stkStsCd) {
    private List<INVTYTMsg> getInvtyList(Map<String, Object> hardAllocationInfo, String stkStsCd) {
    // 2019/09/06 S21_NA#53087 Mod End

        // 2019/09/06 S21_NA#53087 Add Start
        String glblCmpyCd = (String) hardAllocationInfo.get("GLBL_CMPY_CD");
        String mdseCd = (String) hardAllocationInfo.get("SP_MDSE_CD");
        // 2019/09/06 S21_NA#53087 Add End

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);

        //if (isOrdTakeMdseCd(mdseCd)) {
        if (isOrdTakeMdseCd(mdseCd) || !isMdse8digitOrder(hardAllocationInfo)) {
        // 2019/09/06 S21_NA#53087 Mod End
            param.put("mdseCd", mdseCd + "%");
        } else {
            param.put("mdseCd", subByteString(mdseCd, 0, getOrdTaleMdseCdDigit()) + "%");
        }

        param.put("locStsCd", LOC_STS.DC_STOCK);
        param.put("stkStsCd", stkStsCd);

        return ssmBatchClient.queryObjectList("getInvtyList", param);
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

    /**
     * <pre>
     * Perform a MDSE table key search.
     * </pre>
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return search results.
     */
    private MDSETMsg findMdseByKey(String glblCmpyCd, String mdseCd) {

        return NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }

    /**
     * <pre>
     * Perform a MDSE_WH_COND table key search.
     * </pre>
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param whCd String
     * @return search results.
     */
//    private MDSE_WH_CONDTMsg findMdseWhCondByKey(String glblCmpyCd, String mdseCd, String whCd) {
//
//        MDSE_WH_CONDTMsg inMsg = new MDSE_WH_CONDTMsg();
//
//        inMsg.glblCmpyCd.setValue(glblCmpyCd);
//        inMsg.mdseCd.setValue(mdseCd);
//        inMsg.whCd.setValue(whCd);
//
//        // return (MDSE_WH_CONDTMsg) EZDTBLAccessor.findByKey(inMsg);
//        return (MDSE_WH_CONDTMsg) S21FastTBLAccessor.findByKey(inMsg);
//    }

    /**
     * <pre>
     * Perform the exclusive control of the SHPG_PLN table key search.
     * </pre>
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @return search results.
     */
    private SHPG_PLNTMsg findShpgPlnByKeyForUpdate(String glblCmpyCd, String shpgPlnNum) {

        SHPG_PLNTMsg inMsg = new SHPG_PLNTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.shpgPlnNum.setValue(shpgPlnNum);

        // return (SHPG_PLNTMsg)
        // EZDTBLAccessor.findByKeyForUpdate(inMsg);
        return (SHPG_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * <pre>
     * Perform the exclusive control of the CPO table key search.
     * </pre>
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return search results.
     */
    private void findCpoByKeyForUpdate(String glblCmpyCd, String cpoOrdNum) {

        CPOTMsg inMsg = new CPOTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.cpoOrdNum.setValue(cpoOrdNum);

        // EZDTBLAccessor.findByKeyForUpdate(inMsg);
        S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * <pre>
     * Perform the exclusive control of the CPO_DTL table key search.
     * </pre>
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum String
     * @return search results.
     */
    private void findCpoDtlByKeyForUpdate(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg inMsg = new CPO_DTLTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.cpoOrdNum.setValue(cpoOrdNum);
        inMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
        inMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);

        // EZDTBLAccessor.findByKeyForUpdate(inMsg);
        S21FastTBLAccessor.findByKeyForUpdate(inMsg);

    }

    /**
     * <pre>
     * Perform the exclusive control of the SHPG_PLN table key search.
     * </pre>
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @return search results.
     */
    private SHPG_PLNTMsg findShpgPlnByKey(String glblCmpyCd, String shpgPlnNum) {

        SHPG_PLNTMsg inMsg = new SHPG_PLNTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.shpgPlnNum.setValue(shpgPlnNum);

        // return (SHPG_PLNTMsg) EZDTBLAccessor.findByKey(inMsg);
        return (SHPG_PLNTMsg) S21FastTBLAccessor.findByKey(inMsg);

    }

    /**
     * <pre>
     * Perform the exclusive control of the SOFT_ALLOC table key search.
     * </pre>
     * @param glblCmpyCd String
     * @param softAllocPk BigDecimal
     * @return search results.
     */
    private SOFT_ALLOCTMsg findSoftAllocByKeyForUpdate(String glblCmpyCd, BigDecimal softAllocPk) {

        SOFT_ALLOCTMsg inMsg = new SOFT_ALLOCTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.softAllocPk.setValue(softAllocPk);

        // return (SOFT_ALLOCTMsg)
        // EZDTBLAccessor.findByKeyForUpdate(inMsg);
        return (SOFT_ALLOCTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    private boolean postErrorMail() {

        final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();

        NWXC001001MailSubstituteString sbsStr;
        String glblCmpyCd = getGlobalCompanyCode();

        sbsStr = new NWXC001001MailSubstituteString();
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

        boolean isNormalEnd = new NWXC001001Mail().postMail(glblCmpyCd, "NWDB0060", MAIL_TEMPLATE_ID, sbsStrList);

        return isNormalEnd;

    }

//    /**
//     * <pre>
//     * Print debug log.
//     * </pre>
//     * 
//     * @param logmsg String
//     */
//    private void debug(String logmsg) {
//        if (EZDDebugOutput.isDebug()) {
//            EZDDebugOutput.println(DEBUG_MSG_LVL, logmsg, this);
//        }
//    }
//
//    /**
//     * <pre>
//     * Print debug log.
//     * </pre>
//     * 
//     * @param inNWZC104001PMsg NWZC104001PMsg
//     */
//    private void debug(NWZC104001PMsg inNWZC104001PMsg) {
//        if (EZDDebugOutput.isDebug()) {
//            debug(" Global Company Code             : " + inNWZC104001PMsg.glblCmpyCd.getValue());
//            debug(" Request Type                    : " + inNWZC104001PMsg.xxRqstTpCd.getValue());
//            debug(" Partial Acceptable Flag         : " + inNWZC104001PMsg.xxPrtlAcptFlg.getValue());
//            debug(" Item Flip Acceptable Flag       : " + inNWZC104001PMsg.xxItemFlipAcptFlg.getValue());
//            debug(" WH Flip Acceptable Flag         : " + inNWZC104001PMsg.xxWhFlipAcptFlg.getValue());
//            debug(" Transaction Source Type Code    : " + inNWZC104001PMsg.trxSrcTpCd.getValue());
//            debug(" Transaction Header Number       : " + inNWZC104001PMsg.trxHdrNum.getValue());
//            debug(" Transaction Line Number         : " + inNWZC104001PMsg.trxLineNum.getValue());
//            debug(" Transaction Line Sub Number     : " + inNWZC104001PMsg.trxLineSubNum.getValue());
//            debug(" Order Taken Merchandise Flag    : " + inNWZC104001PMsg.xxOrdTakeMdseFlg.getValue());
//            debug(" Merchandise Code                : " + inNWZC104001PMsg.mdseCd.getValue());
//            debug(" Inventory  Location Code        : " + inNWZC104001PMsg.invtyLocCd.getValue());
//            debug(" Location Status Code            : " + inNWZC104001PMsg.locStsCd.getValue());
//            debug(" Stock Status Code               : " + inNWZC104001PMsg.stkStsCd.getValue());
//            debug(" Distribution Type               : " + inNWZC104001PMsg.distTpCd.getValue());
//            debug(" Hard Allocation Type            : " + inNWZC104001PMsg.hardAllocTpCd.getValue());
//            debug(" Soft Allocation Primary Key     : " + inNWZC104001PMsg.softAllocPk.getValue());
//            debug(" Freight Charge Method Code      : " + inNWZC104001PMsg.reqFrtChrgMethCd.getValue());
//            debug(" Freight Charge To Code          : " + inNWZC104001PMsg.reqFrtChrgToCd.getValue());
//            debug(" Shipping Service Level Code     : " + inNWZC104001PMsg.shpgSvcLvlCd.getValue());
//            debug(" RDD DT                          : " + inNWZC104001PMsg.rddDt.getValue());
//            debug(" RSD DT                          : " + inNWZC104001PMsg.rsdDt.getValue());
//            debug(" Bill To Customer Code           : " + inNWZC104001PMsg.billToCustCd.getValue());
//            debug(" Sell To Customer Code           : " + inNWZC104001PMsg.sellToCustCd.getValue());
//            debug(" Ship to Customer Code           : " + inNWZC104001PMsg.shipToCustCd.getValue());
//            debug(" Ship to State Code              : " + inNWZC104001PMsg.shipToStCd.getValue());
//            debug(" Ship To Post Code               : " + inNWZC104001PMsg.shipToPostCd.getValue());
//            debug(" Ship Complete Code              : " + inNWZC104001PMsg.shipCpltCd.getValue());
//            debug(" Set Item Ship Complete Flag     : " + inNWZC104001PMsg.setItemShipCpltFlg.getValue());
//            debug(" UOM Complete Flag               : " + inNWZC104001PMsg.uomCpltFlg.getValue());
//            debug(" Unit of Measure Code            : " + inNWZC104001PMsg.custUomCd.getValue());
//            debug(" Order Taken Merchandise Flag_SA : " + inNWZC104001PMsg.xxOrdTakeMdseFlg_SA.getValue());
//            debug(" Merchandise Code_SA             : " + inNWZC104001PMsg.mdseCd_SA.getValue());
//            debug(" Inventory  Location Code_SA     : " + inNWZC104001PMsg.invtyLocCd_SA.getValue());
//            debug(" Shipping Service Level Code_SA  : " + inNWZC104001PMsg.shpgSvcLvlCd_SA.getValue());
//            debug(" Request Quantity                : " + inNWZC104001PMsg.xxRqstQty.getValue());
//            debug(" Sales Date                      : " + inNWZC104001PMsg.slsDt.getValue());
//        }
//    }
//
//    /**
//     * <pre>
//     * Print debug log.
//     * </pre>
//     * 
//     * @param inventoryInfoList List<NWZC104002PMsg>
//     */
//    private void debug(List<NWZC104002PMsg> inventoryInfoList) {
//        if (EZDDebugOutput.isDebug()) {
//            for (int i = 0; i < inventoryInfoList.size(); i++) {
//                NWZC104002PMsg inNWZC104002PMsg = inventoryInfoList.get(i);
//                debug("NWZC104002PMsg[" + i + "]");
//                debug(" Merchandise Code             : " + inNWZC104002PMsg.mdseCd.getValue());
//                debug(" Location Code                : " + inNWZC104002PMsg.invtyLocCd.getValue());
//                debug(" Location Status Code         : " + inNWZC104002PMsg.locStsCd.getValue());
//                debug(" Stock Status Code            : " + inNWZC104002PMsg.stkStsCd.getValue());
//                debug(" Inventory Available Quantity : " + inNWZC104002PMsg.invtyAvalQty.getValue());
//            }
//        }
//    }

    // 2016/11/14 S21_NA#9903 Add Start
    /**
     * <pre>
     * Add Logical Remove Data to List
     * @param workList Hard Allocation Batch Work Data List
     * @param hardAllocInfo Back Order Batch Work data Map
     * @param logicalRemoveWorkList Logical Remove Data List (before calling this method, user must new List.)
     * </pre>
     */
    private void setRemoveAllocBatchForRtlEqpmntOrd(List<Map<String, Object>> workList, Map<String, Object> hardAllocInfo, List<Map<String, Object>> logicalRemoveWorkList) {

        String ordConfigKey = (String) hardAllocInfo.get("ORD_CONFIG_KEY");
        for (Map<String, Object> curHardAllocInfo : workList) {
            if (S21StringUtil.isEquals((String) hardAllocInfo.get("GLBL_CMPY_CD"), (String) curHardAllocInfo.get("GLBL_CMPY_CD")) //
                    && S21StringUtil.isEquals((String) hardAllocInfo.get("BAT_GRP_CD"), (String) curHardAllocInfo.get("BAT_GRP_CD")) //
                    && S21StringUtil.isEquals((String) hardAllocInfo.get("PROC_GRP_NUM"), (String) curHardAllocInfo.get("PROC_GRP_NUM")) //
                    && S21StringUtil.isEquals((String) hardAllocInfo.get("PROC_SQ_NUM"), (String) curHardAllocInfo.get("PROC_SQ_NUM"))) {
                continue;
            }
            String curOrdConfigKey = (String) curHardAllocInfo.get("ORD_CONFIG_KEY");
            if (S21StringUtil.isEquals(ordConfigKey, curOrdConfigKey)) {
                logicalRemoveWorkList.add(curHardAllocInfo);
            }
        }
    }
    /**
     * <pre>
     * Logical Remove from HARD_ALLOC_BAT_WRK table
     * @param hardAllocInfo HARD_ALLOC_BAT_WRK table information.
     * </pre> 
     */
    private void logicalRemoveAllocBatch(List<Map<String, Object>> workList) {

        for (Map<String, Object> hardAllocInfo : workList) {
            HARD_ALLOC_BAT_WRKTMsg hardAllocBatWrkTMsg = new HARD_ALLOC_BAT_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(hardAllocBatWrkTMsg.glblCmpyCd, (String) hardAllocInfo.get("GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(hardAllocBatWrkTMsg.batGrpCd, (String) hardAllocInfo.get("BAT_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(hardAllocBatWrkTMsg.procGrpNum, (String) hardAllocInfo.get("PROC_GRP_NUM"));
            ZYPEZDItemValueSetter.setValue(hardAllocBatWrkTMsg.procSqNum, (String) hardAllocInfo.get("PROC_SQ_NUM"));

            hardAllocBatWrkTMsg = (HARD_ALLOC_BAT_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(hardAllocBatWrkTMsg);
            if (hardAllocBatWrkTMsg != null) {
                EZDTBLAccessor.logicalRemove(hardAllocBatWrkTMsg);
            }
        }
    }
    // 2016/11/14 S21_NA#9903 Add End

    // Add Start S21_NA#5872
    /**
     * Check : has Critical Merchandise in HARD_ALLOC_BAT_WRK
     * @param Map<String, Object> hardAllocInfo
     */
    private boolean checkCriticalMDSE(Map<String, Object> hardAllocInfo) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", (String) hardAllocInfo.get("GLBL_CMPY_CD"));
        param.put("dsCpoConfigPk", (BigDecimal) hardAllocInfo.get("DS_CPO_CONFIG_PK"));
        param.put("shpgStsValidated", SHPG_STS.VALIDATED);
        param.put("backOrderImpctC", BACK_ORD_IMPCT_TP.CRITICAL);
        // 2017/12/25 S21_NA#23008 Add Start
        param.put("rtlWhLst", getDummyWhCd());
        // 2017/12/25 S21_NA#23008 Add End
        // START 2022/08/09 T.NEMA [QC#60319, MOD]
        param.put("mdseItemTp", MDSE_ITEM_TP.SET);
        // END   2022/08/09 T.NEMA [QC#60319, MOD]

        Integer cnt = (Integer) ssmBatchClient.queryObject("checkCriticalMDSE", param);

        if (cnt > 0) {
            return false;
        }
        return true;
    }

    // 2017/12/25 S21_NA#23008 Add Start
    /**
     * Get Dummy WH Code
     * @return
     */
    private List<String> getDummyWhCd(){
        VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
        setValue(varCharConstTMsg.glblCmpyCd,     this.getGlobalCompanyCode());
        setValue(varCharConstTMsg.varCharConstNm, VAR_CHAR_CONST_NM_CR_AND_BILL_ONLY_DUMMY_WH_CD);
        varCharConstTMsg = (VAR_CHAR_CONSTTMsg)S21FastTBLAccessor.findByKey(varCharConstTMsg);

        List<String> rtlWhLst = null;
        if (varCharConstTMsg != null) {
            final String varCharConstVal = varCharConstTMsg.varCharConstVal.getValue();
            rtlWhLst =asList(varCharConstVal.split(","));
        }

        return rtlWhLst;
    }
    // 2017/12/25 S21_NA#23008 Add End

    /**
     * <pre>
     * Add Logical Remove Data to List
     * @param workList Hard Allocation Batch Work Data List
     * @param hardAllocInfo Back Order Batch Work data Map
     * @param logicalRemoveWorkList Logical Remove Data List (before calling this method, user must new List.)
     * </pre>
     */
    private void setRemoveAllocBatchByOrdConfiKey(List<Map<String, Object>> workList, Map<String, Object> hardAllocInfo, List<Map<String, Object>> logicalRemoveWorkList) {

        String ordConfigKey = (String) hardAllocInfo.get("ORD_CONFIG_KEY");
        for (Map<String, Object> curHardAllocInfo : workList) {
            String curOrdConfigKey = (String) curHardAllocInfo.get("ORD_CONFIG_KEY");
            if (S21StringUtil.isEquals(ordConfigKey, curOrdConfigKey)) {
                logicalRemoveWorkList.add(curHardAllocInfo);
            }
        }
    }
    // Add End S21_NA#5872

    // 2019/09/06 S21_NA#53087 Add Start
    /**
     * <pre>
     * Check Order for Mdse8digit
     * @param hardAllocInfo Back Order Batch Work data Map
     * </pre>
     */
    private boolean isMdse8digitOrder(Map<String, Object> hardAllocInfo) {

        BigDecimal machMstrPk = (BigDecimal) hardAllocInfo.get("SVC_MACH_MSTR_PK");
        String serNum = (String) hardAllocInfo.get("SER_NUM");
        String cpoSrcTpCd = (String) hardAllocInfo.get("CPO_SRC_TP_CD");

        if (hasValue(machMstrPk) || hasValue(serNum)) {
            return false;
        }

        if (CPO_SRC_TP.INVENTORY_REQUEST_ENTRY.equals(cpoSrcTpCd)) {
            return false;
        }
        
        return true;
    }
    // 2019/09/06 S21_NA#53087 Add End
}
