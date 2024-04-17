/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB017001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import business.db.INTFC_SLS_MTH_MGTTMsg;
import business.db.NLCI0160_01TMsg;
import business.db.NLCI0170_01TMsg;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INTFC_VND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * SCM/DB Daily Inventory Result to Canon,Inc. (WE125)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2010   Fujitsu         M.Yamada        Create          N/A
 * 03/19/2010   Fujitsu         M.Yamada        Update          5007
 * 04/06/2010   Fujitsu         S.Yoshida       Update          RQ1318
 * 02/15/2011   Fujitsu         M.Yamada        Update          S21_PRD#1349
 * 05/23/2011   CSAI            M.Takahashi     Update          S21_PRD#2249
 * 09/27/2012   CSAI            M.Takahashi     Update          S21_PRD#410759
 * 10/08/2012   CSAI            M.Takahashi     Update          PROD#416428
 * 09/19/2013   Fujitsu         S.Yoshida       Update          CMEX localize
 * 10/29/2013   Fujitsu         S.Yoshida       Update          Def.2928
 * 11/19/2013   Fujitsu         S.Yoshida       Update          Def.3149
 * 12/10/2013   Fujitsu         T.Nakamura      Update          Def.2957
 * 01/07/2014   CSAI            K.Lee           Update          Def.3365
 * 03/14/2014   CSAI            Y.Imazu         UPdate          ITG#508447
 * 12/19/2014   Hitachi         T.Kanasaka      UPdate          CCH-QC200
 * 07/06/2016   CITS            T.Kikuhara      UPdate          Ver2.0
 * 08/16/2018   CITS            T.Hakodate      Update          QC#26585
 * 12/17/2019   CITS            K.Ogino         Update          QC#55094
 * </pre>
 */
public class NLCB017001 extends S21BatchMain implements NLCB017001Constant {

    /** S21UserProfileService */
    private S21UserProfileService profile = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // counters
    /** The number of cases : Select */
    private int selectCount = 0;

    /** Commit Count */
    private int commitCount = 0;

    /** The number of cases : Insert to NLCI0160_01 */
    private int insertCountAlci0160 = 0;

//START DELETE S.Yoshida [CMEX localize]
//    /** The number of cases : Insert to NLCI0090_01 */
//    private int insertCountAlci0090 = 0;
//END   DELETE S.Yoshida [CMEX localize]

    /** The number of cases : Insert to NLCI0170_01 */
    private int insertCountAlci0170 = 0;

//START DELETE S.Yoshida [CMEX localize]
//    /** The number of cases : Insert to NLCI0100_01 */
//    private int insertCountAlci0100 = 0;
//
//    /** The number of cases : Insert to NLCI0060_01 */
//    private int insertCountAlci0060 = 0;
//
//    /** The number of cases : Insert to NLCI0430_01 */
//    private int insertCountAlci0430 = 0;
//
//    /** The number of cases : Insert to NLCI0070_01 */
//    private int insertCountAlci0070 = 0;
//END   DELETE S.Yoshida [CMEX localize]

    /** Termination code */
    private TERM_CD termCd = null;

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess = null;

    // Transaction ID
    /** Transaction ID : NLCI0160 */
    private BigDecimal transactionIdAlci0160 = null;

//START DELETE S.Yoshida [CMEX localize]
//    /** Transaction ID : NLCI0090 */
//    private BigDecimal transactionIdAlci0090 = null;
//END   DELETE S.Yoshida [CMEX localize]

    /** Transaction ID : NLCI0170 */
    private BigDecimal transactionIdAlci0170 = null;

//START DELETE S.Yoshida [CMEX localize]
//    /** Transaction ID : NLCI0100 */
//    private BigDecimal transactionIdAlci0100 = null;
//
//    /** Transaction ID : NLCI0060 */
//    private BigDecimal transactionIdAlci0060 = null;
//
//    /** Transaction ID : NLCI0430 */
//    private BigDecimal transactionIdAlci0430 = null;
//
//    /** Transaction ID : NLCI0070 */
//    private BigDecimal transactionIdAlci0070 = null;
//END   DELETE S.Yoshida [CMEX localize]

    // Parameters
    /** Global Company Code */
    private String glblCmpyCd = "";

    /** Batch Proc Date */
    private String batProcDate = "";

    /** Interface Sales Year Month */
    private String intfcSlsYrMth = "";

    /** Process code */
    private String procCd = "";

    /** Interface ID List */
    private List<String> interfaceIdList = null;

//START ADD S.Yoshida [Def.2928]
    /**
     * Location Role Map
     *  key  :WH Code
     *  value:Location Role List
     */
    private Map<String, List<String>> locRoleTpMap = null;
//END   ADD S.Yoshida [Def.2928]

//START ADD S.Yoshida [Def.3149]
    /** Merchandise Include mdseInclTechInvtyCincFlg */
    private String inclTechInvtyFlg = null;
//END   ADD S.Yoshida [Def.3149]

    // START ADD Y.Imazu [ITG.508447]
    /** Direct Ship Warehouse Code */
    private String drctShipWhCd = null;

    /** Non-ship Warehouse Code */
    private String nonShipWhCd = null;
    // END   ADD Y.Imazu [ITG.508447]

    // START 2014/12/19 T.Kanasaka [CCH-QC200 ADD]
    /** CINC Vendor Code */
    private String cincVndCd = null;
    // END 2014/12/19 T.Kanasaka [CCH-QC200 ADD]

    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialization of variable
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profile = S21UserProfileServiceFactory.getInstance().getService();

        // The Transaction ID is obtained
        trxAccess = new S21TransactionTableAccessor();

        // Check on input parameter
        checkParameter();

//START ADD S.Yoshida [Def.2928]
        // Location Role Type Map
        locRoleTpMap = new HashMap<String, List<String>>();
//END   ADD S.Yoshida [Def.2928]
    }

    @Override
    protected void mainRoutine() {

        // Get operation date
        batProcDate = ZYPDateUtil.getBatProcDate(profile.getGlobalCompanyCode().trim());

        // Get Interface Sales Year Month
        intfcSlsYrMth = getIntfcSlsYrMth();

        insertInterfaceRecords();

        insertIntegrationRacord();
    }

    @Override
    protected void termRoutine() {

        // The number of cases : Select is output
        String[] tmp0 = {TABLE_ID_INVTY_SNAP_SHOT, "Read", Integer.toString(selectCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp0);

        // The number of cases : Insert is output
        if (hasInterfaceId(INTERFACE_ID_NLCI0160, interfaceIdList)) {
            String[] tmp1 = {TABLE_ID_NLCI0160_01, "Insert", Integer.toString(insertCountAlci0160) };
            S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp1);
        }
//START DELETE S.Yoshida [CMEX localize]
//        if (hasInterfaceId(INTERFACE_ID_NLCI0090, interfaceIdList)) {
//            String[] tmp2 = {TABLE_ID_NLCI0090_01, "Insert", Integer.toString(insertCountAlci0090) };
//            S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp2);
//        }
//END   DELETE S.Yoshida [CMEX localize]
        if (hasInterfaceId(INTERFACE_ID_NLCI0170, interfaceIdList)) {
            String[] tmp3 = {TABLE_ID_NLCI0170_01, "Insert", Integer.toString(insertCountAlci0170) };
            S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp3);
        }
//START DELETE S.Yoshida [CMEX localize]
//        if (hasInterfaceId(INTERFACE_ID_NLCI0100, interfaceIdList)) {
//            String[] tmp4 = {TABLE_ID_NLCI0100_01, "Insert", Integer.toString(insertCountAlci0100) };
//            S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp4);
//        }
//        if (hasInterfaceId(INTERFACE_ID_NLCI0430, interfaceIdList)) {
//            String[] tmp6 = {TABLE_ID_NLCI0430_01, "Insert", Integer.toString(insertCountAlci0430) };
//            S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp6);
//        }
//        if (hasInterfaceId(INTERFACE_ID_NLCI0060, interfaceIdList)) {
//            String[] tmp5 = {TABLE_ID_NLCI0060_01, "Insert", Integer.toString(insertCountAlci0060) };
//            S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp5);
//        }
//        if (hasInterfaceId(INTERFACE_ID_NLCI0070, interfaceIdList)) {
//            String[] tmp7 = {TABLE_ID_NLCI0070_01, "Insert", Integer.toString(insertCountAlci0070) };
//            S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp7);
//        }
//END   DELETE S.Yoshida [CMEX localize]

        // Setting of termination code
//START MODIFY S.Yoshida [CMEX localize]
//        setTermState(termCd, selectCount, 0, insertCountAlci0160 + insertCountAlci0090
//                + insertCountAlci0170 + insertCountAlci0100 + insertCountAlci0060 + insertCountAlci0430 + insertCountAlci0070);
        setTermState(termCd, selectCount, 0, insertCountAlci0160 + insertCountAlci0170);
//END   MODIFY S.Yoshida [CMEX localize]
    }

    /**
     * @param args args
     */
    public static void main(String[] args) {

        // Initialization of S21BatchMain
        new NLCB017001().executeBatch(NLCB017001.class.getSimpleName());
    }

    /**
     * Check processing of input parameter If error occurs ,then throw
     * S21AbendException
     */
    private void checkParameter() {

        glblCmpyCd = profile.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            String[] tmp = {MSG_STR_COMP_CODE };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }

        procCd = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(procCd)) {
            String[] tmp = {MSG_STR_PROC_CODE };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }

        String strList = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(strList)) {
            String[] tmp = {MSG_STR_INTERFACE_ID };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }
        interfaceIdList = S21StringUtil.toList(strList, DELIMITER);
        checkInterfaceId(procCd, interfaceIdList);

        String str = getUserVariable3();
        if (!ZYPCommonFunc.hasValue(str)) {
            String[] tmp = {MSG_STR_COMMIT_COUNT };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }
        if (str.matches("[0-9]+")) {
            commitCount = Integer.valueOf(str).intValue();
        } else {
            throw new S21AbendException(MSG_ID_ZZM9004E, new String[] {MSG_STR_COMMIT_COUNT + "(" + str + ")" });
        }

//START ADD S.Yoshida [Def.3149]
        inclTechInvtyFlg =
            ZYPCodeDataUtil.getVarCharConstValue(KEY_INCL_TECH_INVTY_FLG, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(inclTechInvtyFlg)) {
            String[] tmp = {MSG_INCL_TECH_INVTY_FLG};
            throw new S21AbendException(MSG_ID_ZZZM9025E, tmp);
        }
        if (!ZYPConstant.FLG_ON_Y.equals(inclTechInvtyFlg)
                && !ZYPConstant.FLG_OFF_N.equals(inclTechInvtyFlg)) {
            String[] tmp = {"\"" + MSG_INCL_TECH_INVTY_FLG + "\""};
            throw new S21AbendException(MSG_ID_NLCM0065E, tmp);
        }
//END   ADD S.Yoshida [Def.3149]

        // START ADD Y.Imazu [ITG.508447]
        drctShipWhCd = ZYPCodeDataUtil.getVarCharConstValue(DRCT_SHIP_WH_CD, glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(drctShipWhCd)) {

            String[] tmp = {DRCT_SHIP_WH_CD};
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }

        nonShipWhCd = ZYPCodeDataUtil.getVarCharConstValue(NON_SHIP_WH_CD, glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(nonShipWhCd)) {

            String[] tmp = {NON_SHIP_WH_CD};
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }
        // END   ADD Y.Imazu [ITG.508447]

        // START 2016/07/01 T.Kikuhara V2.0
        cincVndCd = INTFC_VND.CANON_INC;
        // END 2016/07/01 T.Kikuhara V2.0
    }

    /**
     * Check the interface Id and get Trsansaction Id - process code
     * each - If error occurs ,then throw S21AbendException
     * @param pc Process Code
     * @param ifList Interface Id List
     */
    private void checkInterfaceId(String pc, List<String> ifList) {

        for (String interfaceId : ifList) {
            if (PROC_CD_WE125.equals(pc)) {
                if (INTERFACE_ID_NLCI0160.equals(interfaceId)) {
                    transactionIdAlci0160 = trxAccess.getNextTransactionId();
//START DELETE S.Yoshida [CMEX localize]
//                } else if (INTERFACE_ID_NLCI0090.equals(interfaceId)) {
//                    transactionIdAlci0090 = trxAccess.getNextTransactionId();
//END   DELETE S.Yoshida [CMEX localize]
                } else {
                    S21InfoLogOutput.println("++ Error:WE125 mismatch Interface ID(" + interfaceId + ")");
                    throw new S21AbendException(MSG_ID_NLCM0063E, new String[] {"WE125 * Interface ID" });
                }
            } else if (PROC_CD_WE125M.equals(pc)) {
                if (INTERFACE_ID_NLCI0170.equals(interfaceId)) {
                    transactionIdAlci0170 = trxAccess.getNextTransactionId();
//START DELETE S.Yoshida [CMEX localize]
//                } else if (INTERFACE_ID_NLCI0100.equals(interfaceId)) {
//                    transactionIdAlci0100 = trxAccess.getNextTransactionId();
//END   DELETE S.Yoshida [CMEX localize]
                } else {
                    S21InfoLogOutput.println("++ Error:WE125M mismatch Interface ID(" + interfaceId + ")");
                    throw new S21AbendException(MSG_ID_NLCM0063E, new String[] {"WE125M * Interface ID" });
                }
//START DELETE S.Yoshida [CMEX localize]
//            } else if (PROC_CD_CAS125.equals(pc)) {
//                if (INTERFACE_ID_NLCI0060.equals(interfaceId)) {
//                    transactionIdAlci0060 = trxAccess.getNextTransactionId();
//                } else if (INTERFACE_ID_NLCI0430.equals(interfaceId)) {
//                    transactionIdAlci0430 = trxAccess.getNextTransactionId();
//                } else {
//                    S21InfoLogOutput.println("++ Error:CAS125 mismatch Interface ID(" + interfaceId + ")");
//                    throw new S21AbendException(MSG_ID_NLCM0063E, new String[] {"CAS125 * Interface ID" });
//                }
//            } else if (PROC_CD_CAS125M.equals(pc)) {
//                if (INTERFACE_ID_NLCI0070.equals(interfaceId)) {
//                    transactionIdAlci0070 = trxAccess.getNextTransactionId();
//                } else {
//                    S21InfoLogOutput.println("++ Error:CAS125M mismatch Interface ID(" + interfaceId + ")");
//                    throw new S21AbendException(MSG_ID_NLCM0063E, new String[] {"CAS125M * Interface ID" });
//                }
//END   DELETE S.Yoshida [CMEX localize]
            } else {
                S21InfoLogOutput.println("++ Error:Invalid Process Code(VER_USER1:" + pc + ")");
                throw new S21AbendException(MSG_ID_NLCM0063E, new String[] {"Process Code(VER_USER1)" });
            }
        } // end for
    }

////START DELETE S.Yoshida [CMEX localize]
//    /**
//     * Is Interface process to CAS125 or CAS125M?
//     * @param ifList Interface Id List
//     */
//    private boolean isCas125(List<String> ifList) {
//
//        for (String interfaceId : ifList) {
//            if (INTERFACE_ID_NLCI0060.equals(interfaceId) || INTERFACE_ID_NLCI0070.equals(interfaceId)) {
//                return true;
//            }
//        }
//        return false;
//    }
//END   DELETE S.Yoshida [CMEX localize]

    /**
     * Insert Interface Records from INVTY_SNAP_SHOT. Set to the ssm
     * parameters. And then call method for insert data to interface
     * table.
     */
    private void insertInterfaceRecords() {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("invtySnapShotDt", batProcDate);

        params.put("locStsCd01", LOC_STS.IN_TRANSIT);
        params.put("locStsCd02", LOC_STS.IN_SHED);
        params.put("locStsCd03", LOC_STS.DC_STOCK);
        params.put("locStsCd04", LOC_STS.LOSS_ON_RECEIVING);
        params.put("locStsCd05", LOC_STS.LOSS_ON_SHIPMENT);
        params.put("locStsCd06", LOC_STS.INSURANCE_CLAIM_DAMAGED);

        params.put("locStsCd07", LOC_STS.WORK_IN_PROCESS_COMPONENT);
        params.put("locStsCd08", LOC_STS.WORK_IN_PROCESS_KIT);
        params.put("locStsCd09", LOC_STS.WORK_IN_PROCESS_ORIGINAL);
        params.put("locStsCd10", LOC_STS.WORK_IN_PROCESS_REFURBISHED);

        params.put("locStsCd11", LOC_STS.TRIAL_USE);
        params.put("locStsCd12", LOC_STS.TRIAL_SALE);
        params.put("locStsCd13", LOC_STS.LOAN);
        params.put("locStsCd15", LOC_STS.WAITING_FOR_INSTALLATION);
//START ADD S.Yoshida [CMEX localize]
        params.put("locStsCd14", LOC_STS.RENTAL);
//END   ADD S.Yoshida [CMEX localize]
        params.put("locStsCd16", LOC_STS.IN_TRANSIT_WH);

        params.put("locTpCd01", LOC_TP.WAREHOUSE);
        params.put("locTpCd03", LOC_TP.VENDOR);
//START ADD S.Yoshida [CMEX localize]
        params.put("locTpCd04", LOC_TP.CUSTOMER);
//END   ADD S.Yoshida [CMEX localize]
        params.put("locTpCd05", LOC_TP.INBOUND_CONSIGNEE);
//START ADD S.Yoshida [Def.3149]
        params.put("locTpCd06", LOC_TP.TECHNICIAN);
//END   ADD S.Yoshida [Def.3149]

//START ADD S.Yoshida [CMEX localize]
        params.put("stkSts1", STK_STS.GOOD);
        params.put("svcMachMstrSnapShotDt", batProcDate);
        params.put("svcMachMstrSts_W4I", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        params.put("svcMachMstrSts_INSTL", SVC_MACH_MSTR_STS.INSTALLED);
//END   ADD S.Yoshida [CMEX localize]
//START ADD S.Yoshida [Def.2928]
        params.put("svcMachMstrSts_W4R", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
//END   ADD S.Yoshida [Def.2928]
//START ADD S.Yoshida [CMEX localize]
        params.put("cpoOrdTpCd_Rental", CPO_ORD_TP.RENTAL_DS);
        params.put("cpoOrdTpCd_Loan", CPO_ORD_TP.LOAN);
        params.put("rgtnTpCd1", MDSE_RGTN_TP.MERCURY);
//END   ADD S.Yoshida [CMEX localize]

        params.put("dummyLocCdBA20", DUMMY_LOC_CD_BA20);
        params.put("dummyLocCdBB20", DUMMY_LOC_CD_BB20);
        params.put("dummyLocCdBA22", DUMMY_LOC_CD_BA22);
        params.put("dummyLocCdBA21", DUMMY_LOC_CD_BA21);
        params.put("dummyLocCdBA24", DUMMY_LOC_CD_BA24);
//START DELETE S.Yoshida [CMEX localize]
//        params.put("dummyLocCdZA21", DUMMY_LOC_CD_ZA21);
//END   DELETE S.Yoshida [CMEX localize]

        params.put("vndCdOther", VND_CD_OTHER);

        params.put("rwsCanceled", RWS_STS.CANCELED);
        params.put("rwsReceived", RWS_STS.RECEIVED);

        params.put("invStatusClosed", IMPT_INV_STS.CLOSED);
        params.put("invStatusReceived", IMPT_INV_STS.RCVD);

        // START ADD Y.Imazu [ITG.508447]
        params.put("drctShipWh", drctShipWhCd);
        params.put("nonShipWh", nonShipWhCd);
        params.put("trxSrcWholeSales", TRX_SRC_TP.WHOLE_SALES);
        params.put("trxSalas", TRX.SALES);
        params.put("trxExpense", TRX.EXPENSE_SHIPMENT);
        params.put("shpgStsShipped", SHPG_STS.SHIPPED);
        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        params.put("shpgStsInstalled", SHPG_STS.INSTALLED);
        params.put("flgOnY", ZYPConstant.FLG_ON_Y);
        params.put("flgOffN", ZYPConstant.FLG_OFF_N);
        // END   ADD Y.Imazu [ITG.508447]

        // START 2016/07/01 T.Kikuhara V2.0
        params.put("trxRental", TRX.RENTAL_SHIPMENT);
        params.put("rentalAsset", ASSET_TP.RENTAL_ASSET);
        params.put("pending", ASSET_STS.PENDING);
        params.put("dummyLocCdBB21", DUMMY_LOC_CD_BB21);
        params.put("dummyLocCdZA20", DUMMY_LOC_CD_ZA20);
        params.put("inventoryRequest", PRCH_REQ_REC_TP.INVENTORY_REQUEST);
        params.put("subcontract", PRCH_REQ_TP.SUBCONTRACT);
        params.put("closed", PRCH_REQ_STS.CLOSED);
        params.put("poOpen", PO_LINE_STS.OPEN);
        params.put("poOpenForReceipt", PO_LINE_STS.OPEN_FOR_RECEIPT);
        params.put("costPct100", COST_PCT_100);
        // END 2016/07/01 T.Kikuhara V2.0

        // QC#26585 ADD START
        params.put("locStsCd21", LOC_STS.WORK_IN_PROCESS_SUBCONTRACT);
        params.put("dummyLocCdBB21", "BB21");
        // QC#26585 ADD END
        // QC#55094
        params.put("sceOrdTpCdDT", SCE_ORD_TP.DC_TRANSFER);

        if (PROC_CD_WE125.equals(procCd) || PROC_CD_WE125M.equals(procCd)) {
            // insertInterface("getInterfaceWE", params);
            params.put("InterfaceWE", ZYPConstant.FLG_ON_Y);
            // } else {
            // insertInterface("getInterfaceCAS", params);
        }

//START ADD S.Yoshida [Def.3149]
        if (ZYPConstant.CHKBOX_ON_Y.equals(inclTechInvtyFlg)) {
            params.put("inclTechInvtyFlg", inclTechInvtyFlg);
        }
//END   ADD S.Yoshida [Def.3149]

        insertInterface(params);
    }

    /**
     * <pre>
     * Insert data to the Interface Table.
     * </pre>
     * 
     * @param key SSM key.
     * @param params SSM parameter.
     */
    private void insertInterface(Map<String, Object> params) {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            NLCB017001InterfaceWork ifWork = new NLCB017001InterfaceWork();
            List<NLCB017001InterfaceWork> ifWorkList = new ArrayList<NLCB017001InterfaceWork>();

//START DELETE S.Yoshida [CMEX localize]
//            NLCB017001InterfaceWork ifWorkCas = new NLCB017001InterfaceWork();
//            List<NLCB017001InterfaceWork> ifWorkCasList = new ArrayList<NLCB017001InterfaceWork>();
//END   DELETE S.Yoshida [CMEX localize]

            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            // Execute search for delete query.
            stmt = ssmLLClient.createPreparedStatement("getInventoryData", params, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                selectCount++;
                if (!hasInterfaceWorkKeyValue(ifWork)) {
                    setInterfaceWorkKeyValue(rs, ifWork);
                }
                if (isInterfaceWorkKeyChange(rs, ifWork)) {
                    NLCB017001InterfaceWork ifW = new NLCB017001InterfaceWork(ifWork);
                    ifWorkList.add(ifW);
                    setInterfaceWorkKeyValue(rs, ifWork);
                }
                addQtyInterfaceWork(rs, ifWork);

//START DELETE S.Yoshida [CMEX localize]
//                if (isCas125(interfaceIdList)) {
//                    if (!hasInterfaceWorkKeyValue(ifWorkCas)) {
//                        setInterfaceWorkKeyValue(rs, ifWorkCas);
//                    }
//                    if (isInterfaceWorkKeyChange(rs, ifWorkCas, isCas125(interfaceIdList))) {
//
//                        NLCB017001InterfaceWork ifWC = new NLCB017001InterfaceWork(ifWorkCas);
//                        ifWorkCasList.add(ifWC);
//
//                        setInterfaceWorkKeyValue(rs, ifWorkCas);
//                    }
//                    addQtyInterfaceWork(rs, ifWorkCas);
//
//                }
//END   DELETE S.Yoshida [CMEX localize]
                if (ifWorkList.size() >= commitCount) {
                    procInterface(ifWorkList);
                    commit();
                    ifWorkList.clear();
                }
//START DELETE S.Yoshida [CMEX localize]
//                if (ifWorkCasList.size() >= commitCount) {
//                    procCasInterface(ifWorkCasList);
//                    commit();
//                    ifWorkCasList.clear();
//                }
//END   DELETE S.Yoshida [CMEX localize]
            } // end while

            if (hasInterfaceWorkKeyValue(ifWork)) {
                NLCB017001InterfaceWork ifW = new NLCB017001InterfaceWork(ifWork);
                ifWorkList.add(ifW);
            }
////START DELETE S.Yoshida [CMEX localize]
//            if (isCas125(interfaceIdList)) {
//                if (hasInterfaceWorkKeyValue(ifWorkCas)) {
//
//                    NLCB017001InterfaceWork ifWC = new NLCB017001InterfaceWork(ifWorkCas);
//                    ifWorkCasList.add(ifWC);
//                }
//            }
//END   DELETE S.Yoshida [CMEX localize]
            if (!ifWorkList.isEmpty()) {
                procInterface(ifWorkList);
                commit();
            }
//START DELETE S.Yoshida [CMEX localize]
//            if (!ifWorkCasList.isEmpty()) {
//                procCasInterface(ifWorkCasList);
//                commit();
//            }
//END   DELETE S.Yoshida [CMEX localize]

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Has interfaceWork key value ?
     * @param ifWork
     */
    private boolean hasInterfaceWorkKeyValue(NLCB017001InterfaceWork ifWork) {

        return ZYPCommonFunc.hasValue(ifWork.getGlblCmpy3Cd());
    }

    /**
     * Set interfaceWork key value.
     * @param rs Result Set
     * @param ifWork NLCB017001InterfaceWork
     */
    private void setInterfaceWorkKeyValue(ResultSet rs, NLCB017001InterfaceWork ifWork) throws SQLException {

        ifWork.clearNLCB017001InterfaceWork();

        ifWork.setGlblCmpy3Cd(rs.getString(COL_NM_GLBL_CMPY_CD));
        ifWork.setMdse12Cd(rs.getString(COL_NM_MDSE_CD));
        ifWork.setLocalMdseCd(rs.getString(COL_NM_MDSE_CD));

//START DELETE S.Yoshida [CMEX localize]
//        if (PROC_CD_CAS125.equals(procCd) || PROC_CD_CAS125M.equals(procCd)) {
//            ifWork.setIntfcWhCd(rs.getString(COL_NM_INVTY_LOC_CD));
//        }
//END   DELETE S.Yoshida [CMEX localize]

        ifWork.setAdminYrMth(intfcSlsYrMth);
        ifWork.setAdminDt(batProcDate);

//START DEL S.Yoshida [CMEX localize]
//        ifWork.setDefIntfcWhCd(getDefWhWorkKeyValue(rs));
//END   DEL S.Yoshida [CMEX localize]
    }

//START DEL S.Yoshida [CMEX localize]
//    /**
//     * Get Default Interface Warehouse key value.
//     * @param rs Result Set
//     */
//    private String getDefWhWorkKeyValue(ResultSet rs) throws SQLException {
//
//        //System.out.println("COL_NM_MDSE_CD:"+rs.getString(COL_NM_MDSE_CD));
//        //System.out.println("COL_NM_INVTY_LOC_CD:"+rs.getString(COL_NM_INVTY_LOC_CD));
//        //System.out.println("COL_NM_LOC_TP_CD:"+rs.getString(COL_NM_LOC_TP_CD));
//
//        if (LOC_TP.WAREHOUSE.equals(rs.getString(COL_NM_LOC_TP_CD))) {
//
//            if (LOC_STS.IN_TRANSIT.equals(rs.getString(COL_NM_LOC_STS_CD)) //
//                    || LOC_STS.IN_SHED.equals(rs.getString(COL_NM_LOC_STS_CD)) //
//                    || LOC_STS.DC_STOCK.equals(rs.getString(COL_NM_LOC_STS_CD)) //
//                    || LOC_STS.LOSS_ON_RECEIVING.equals(rs.getString(COL_NM_LOC_STS_CD)) //
//                    || LOC_STS.LOSS_ON_SHIPMENT.equals(rs.getString(COL_NM_LOC_STS_CD)) //
//                    || LOC_STS.INSURANCE_CLAIM_DAMAGED.equals(rs.getString(COL_NM_LOC_STS_CD))) {
//
//                return rs.getString(COL_NM_INVTY_LOC_CD);
//            }
//        }
//
//        if (LOC_STS.IN_TRANSIT.equals(rs.getString(COL_NM_LOC_STS_CD))) {
//
//            if (LOC_TP.VENDOR.equals(rs.getString(COL_NM_LOC_TP_CD))//
//                    || LOC_TP.INBOUND_CONSIGNEE.equals(rs.getString(COL_NM_LOC_TP_CD))) {
//
//                return rs.getString(COL_NM_INVTY_LOC_CD);
//            }
//        }
//
//        if (LOC_STS.IN_TRANSIT_WH.equals(rs.getString(COL_NM_LOC_STS_CD))) {
//
//            return rs.getString(COL_NM_INVTY_LOC_CD);
//        }
//
//        return DUMMY_LOC_CD_ZZ;
//    }
//END   DEL S.Yoshida [CMEX localize]

    /**
     * Is interfaceWork key changed?
     * @param rs Result Set
     * @param ifWork NLCB017001InterfaceWork
     */
    private boolean isInterfaceWorkKeyChange(ResultSet rs, NLCB017001InterfaceWork ifWork) throws SQLException {

        if (!ifWork.getGlblCmpy3Cd().equals(rs.getString(COL_NM_GLBL_CMPY_CD))) {
            return true;
        }

        if (!ifWork.getMdse12Cd().equals(rs.getString(COL_NM_MDSE_CD))) {
            return true;
        }

//START DELETE S.Yoshida [CMEX localize]
//        if (PROC_CD_CAS125.equals(procCd) || PROC_CD_CAS125M.equals(procCd)) {
//            if (!ifWork.getIntfcWhCd().equals(rs.getString(COL_NM_INVTY_LOC_CD))) {
//                return true;
//            }
//        }
//END   DELETE S.Yoshida [CMEX localize]
        return false;
    }

//START DELETE S.Yoshida [CMEX localize]
//    /**
//     * Is interfaceWork key changed?
//     * @param rs Result Set
//     * @param ifWork NLCB017001InterfaceWork
//     * @param isCas if procCd is CAS125 or CAS125M then true else
//     * false.
//     */
//    private boolean isInterfaceWorkKeyChange(ResultSet rs, NLCB017001InterfaceWork ifWork, boolean isCas) throws SQLException {
//
//        if (!isCas) {
//            return isInterfaceWorkKeyChange(rs, ifWork);
//        }
//
//        if (!ifWork.getGlblCmpy3Cd().equals(rs.getString(COL_NM_GLBL_CMPY_CD))) {
//            return true;
//        }
//        if (!ifWork.getMdse12Cd().equals(rs.getString(COL_NM_MDSE_CD))) {
//            return true;
//        }
//
//        if (LOC_STS.IN_TRANSIT.equals(rs.getString(COL_NM_LOC_STS_CD))) {
//            if (LOC_TP.VENDOR.equals(rs.getString(COL_NM_LOC_TP_CD)) || LOC_TP.INBOUND_CONSIGNEE.equals(rs.getString(COL_NM_LOC_TP_CD))) {
//                if (!ifWork.getDefIntfcWhCd().equals(rs.getString(COL_NM_INVTY_LOC_CD))) {
//                    return true;
//                }
//            }
//        }
//        if (!LOC_TP.WAREHOUSE.equals(rs.getString(COL_NM_LOC_TP_CD))) {
//            if (!isDummyLocCd(ifWork.getDefIntfcWhCd())) {
//                return true;
//            }
//        } else {
//            if (!ifWork.getDefIntfcWhCd().equals(rs.getString(COL_NM_INVTY_LOC_CD))) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * Is location code dummy?
//     * @param defIntfcWhCd defIntfcWhCd
//     */
//    private static boolean isDummyLocCd(String defIntfcWhCd) {
//
//        if (DUMMY_LOC_CD_ZZ.equals(defIntfcWhCd)) {
//            return true;
//        }
//        return false;
//    }
//END   DELETE S.Yoshida [CMEX localize]

    /**
     * Add Qty to Interface Work
     * @param rs ResultSet
     * @param ifWork NLCB017001InterfaceWork
     * @exception SQLException
     */
    private void addQtyInterfaceWork(ResultSet rs, NLCB017001InterfaceWork ifWork) throws SQLException {

        String locSts = rs.getString(COL_NM_LOC_STS_CD);
        String locTp = rs.getString(COL_NM_LOC_TP_CD);
        String stkSts = rs.getString(COL_NM_STK_STS_CD);
        BigDecimal invtyQty = rs.getBigDecimal(COL_NM_INVTY_QTY);
        BigDecimal invtyAvalQty = rs.getBigDecimal(COL_NM_INVTY_AVAL_QTY);
        String vndCd = rs.getString(COL_NM_IMPT_VND_CD);
        String invtyLocCd = rs.getString(COL_NM_INVTY_LOC_CD);

        // Renewal START 2016/07/01 T.Kikuhara V2.0
        String whCatgCd = rs.getString(COL_NM_RTL_WH_CATG_CD);
        BigDecimal cosPct = rs.getBigDecimal(COL_NM_MDSE_INVTY_COST_PCT);

        // refurbish_recycle_stock_qty
        if (DUMMY_LOC_CD_BB20.equals(invtyLocCd) && !RTL_WH_CATG.SHOWROOM.equals(whCatgCd)) {
            ifWork.addScmDbQty08(invtyQty);
            if (STK_STS.GOOD.equals(stkSts) && (COST_PCT_100.compareTo(cosPct) == 0)) {
                // good_stock_qty minus refurbish_recycle_stock_qty
                ifWork.addScmDbQty05(invtyQty.negate());
            } else if (STK_STS.GOOD.equals(stkSts) || STK_STS.RANK_B.equals(stkSts) || STK_STS.WAITING_FOR_INSPECTION.equals(stkSts)) {
                // good_other_stock_qty minus refurbish_recycle_stock_qty
                ifWork.addScmDbQty06(invtyQty.negate());
            } else if (STK_STS.DEFECTIVE.equals(stkSts)) {
                // defective_stock_qty minus refurbish_recycle_stock_qty
                ifWork.addScmDbQty07(invtyQty.negate());
            }
            return;
        }

        // rework_stock_qty
        if (DUMMY_LOC_CD_BB21.equals(invtyLocCd)) {
            ifWork.addScmDbQty09(invtyQty);
            // transit_other_stock_qty minus rework_stock_qty
            ifWork.addScmDbQty04(invtyQty.negate());
            return;
        }

        // waiting_install_stock_qty
        if (DUMMY_LOC_CD_BA24.equals(invtyLocCd) && !RTL_WH_CATG.SHOWROOM.equals(whCatgCd)) {
            ifWork.addScmDbQty13(invtyQty);
            return;
        }

        // rental_stock_qty
        if (DUMMY_LOC_CD_ZA20.equals(invtyLocCd)) {
            ifWork.addScmDbQty15(invtyQty);
            return;
        }

        if (LOC_STS.IN_TRANSIT.equals(locSts) && LOC_TP.WAREHOUSE.equals(locTp)) {
            if (cincVndCd.equals(vndCd)) {
                ifWork.addScmDbQty01(invtyQty);
            } else {
                ifWork.addScmDbQty04(invtyQty);
            }
            return;
        }

        if (LOC_STS.IN_TRANSIT.equals(locSts) && LOC_TP.VENDOR.equals(locTp)) {
            if (cincVndCd.equals(vndCd)) {
                ifWork.addScmDbQty01(invtyQty);
            } else {
                ifWork.addScmDbQty04(invtyQty);
            }
            return;
        }

        if (LOC_STS.IN_TRANSIT.equals(locSts) && LOC_TP.INBOUND_CONSIGNEE.equals(locTp)) {
            if (cincVndCd.equals(vndCd)) {
                ifWork.addScmDbQty01(invtyQty);
            } else {
                ifWork.addScmDbQty04(invtyQty);
            }
            return;
        }

        if (LOC_STS.IN_TRANSIT.equals(locSts) && LOC_TP.TECHNICIAN.equals(locTp)) {
            if (cincVndCd.equals(vndCd)) {
                ifWork.addScmDbQty01(invtyQty);
            } else {
                ifWork.addScmDbQty04(invtyQty);
            }
            return;
        }

        if (LOC_STS.IN_TRANSIT_WH.equals(locSts) && LOC_TP.WAREHOUSE.equals(locTp)) {
            if (RTL_WH_CATG.SHOWROOM.equals(whCatgCd)) {
                ifWork.addScmDbQty12(invtyQty);
            }
            if (isRgrlStkWh(locTp, invtyLocCd) && !RTL_WH_CATG.SHOWROOM.equals(whCatgCd)) {
                if (STK_STS.GOOD.equals(stkSts) && (COST_PCT_100.compareTo(cosPct) == 0)) {
                    ifWork.addScmDbQty05(invtyQty);
                } else if (STK_STS.GOOD.equals(stkSts) || STK_STS.RANK_B.equals(stkSts) || STK_STS.WAITING_FOR_INSPECTION.equals(stkSts)) {
                    ifWork.addScmDbQty06(invtyQty);
                } else if (STK_STS.DEFECTIVE.equals(stkSts)) {
                    ifWork.addScmDbQty07(invtyQty);
                }
            }
            return;
        }

        if (LOC_STS.IN_TRANSIT_WH.equals(locSts) && LOC_TP.TECHNICIAN.equals(locTp) && !RTL_WH_CATG.SHOWROOM.equals(whCatgCd)) {
            if (STK_STS.GOOD.equals(stkSts) && (COST_PCT_100.compareTo(cosPct) == 0)) {
                ifWork.addScmDbQty05(invtyQty);
            } else if (STK_STS.GOOD.equals(stkSts) || STK_STS.RANK_B.equals(stkSts) || STK_STS.WAITING_FOR_INSPECTION.equals(stkSts)) {
                ifWork.addScmDbQty06(invtyQty);
            } else if (STK_STS.DEFECTIVE.equals(stkSts)) {
                ifWork.addScmDbQty07(invtyQty);
            }
            return;
        }

        if (LOC_STS.DC_STOCK.equals(locSts) && LOC_TP.WAREHOUSE.equals(locTp)) {
            if (RTL_WH_CATG.SHOWROOM.equals(whCatgCd)) {
                ifWork.addScmDbQty12(invtyQty);
            }
            if (isRgrlStkWh(locTp, invtyLocCd) && !RTL_WH_CATG.SHOWROOM.equals(whCatgCd)) {
                if (STK_STS.GOOD.equals(stkSts) && (COST_PCT_100.compareTo(cosPct) == 0)) {
                    ifWork.addScmDbQty05(invtyQty);
                } else if (STK_STS.GOOD.equals(stkSts) || STK_STS.RANK_B.equals(stkSts) || STK_STS.WAITING_FOR_INSPECTION.equals(stkSts)) {
                    ifWork.addScmDbQty06(invtyQty);
                } else if (STK_STS.DEFECTIVE.equals(stkSts)) {
                    ifWork.addScmDbQty07(invtyQty);
                }
            }
            return;
        }

        if (LOC_STS.DC_STOCK.equals(locSts) && LOC_TP.TECHNICIAN.equals(locTp) && !RTL_WH_CATG.SHOWROOM.equals(whCatgCd)) {
            if (STK_STS.GOOD.equals(stkSts) && (COST_PCT_100.compareTo(cosPct) == 0)) {
                ifWork.addScmDbQty05(invtyQty);
            } else if (STK_STS.GOOD.equals(stkSts) || STK_STS.RANK_B.equals(stkSts) || STK_STS.WAITING_FOR_INSPECTION.equals(stkSts)) {
                ifWork.addScmDbQty06(invtyQty);
            } else if (STK_STS.DEFECTIVE.equals(stkSts)) {
                ifWork.addScmDbQty07(invtyQty);
            }
            return;
        }

        if (LOC_STS.WORK_IN_PROCESS_COMPONENT.equals(locSts) && LOC_TP.WAREHOUSE.equals(locTp) && !RTL_WH_CATG.SHOWROOM.equals(whCatgCd)) {
            ifWork.addScmDbQty10(invtyQty);
            return;
        }
        if (LOC_STS.WORK_IN_PROCESS_KIT.equals(locSts) && LOC_TP.WAREHOUSE.equals(locTp) && !RTL_WH_CATG.SHOWROOM.equals(whCatgCd)) {
            ifWork.addScmDbQty10(invtyQty);
            return;
        }

        if ((LOC_STS.TRIAL_SALE.equals(locSts) || LOC_STS.TRIAL_USE.equals(locSts)) && !RTL_WH_CATG.SHOWROOM.equals(whCatgCd)) {
            ifWork.addScmDbQty11(invtyQty);
            return;
        }

        if (LOC_STS.IN_SHED.equals(locSts) && LOC_TP.WAREHOUSE.equals(locTp)) {
            ifWork.addScmDbQty14(invtyQty);
            return;
        }

        if (LOC_STS.IN_SHED.equals(locSts) && LOC_TP.TECHNICIAN.equals(locTp)) {
            ifWork.addScmDbQty14(invtyQty);
            return;
        }
        // Renewal END 2016/07/01 T.Kikuhara V2.0

        // missmatch ?
        // if (EZDDebugOutput.isDebug()) {
        S21InfoLogOutput.println("+++++++++++++++++++++++++++++");
        S21InfoLogOutput.println("++ addQty Missmatch Data?? ++");
        S21InfoLogOutput.println("++ locSts:" + locSts);
        S21InfoLogOutput.println("++ locTp :" + locTp);
        S21InfoLogOutput.println("++ stkSts:" + stkSts);
        S21InfoLogOutput.println("++ invtyQty    :" + invtyQty);
        S21InfoLogOutput.println("++ invtyAvalQty:" + invtyAvalQty);
        S21InfoLogOutput.println("+++++++++++++++++++++++++++++");
        // }

    }

//START ADD S.Yoshida [Def.2928]
    /**
     * is Regurlar Stock Warehouse
     * @param locTp Location Type Code
     * @param invtyLoc Inventory Location Code
     * @return ture is Regurlar Stock Warehouse.
     */
    private boolean isRgrlStkWh(String locTp, String invtyLoc) {

      //START ADD S.Yoshida [Def.3149]
        if (ZYPConstant.FLG_ON_Y.equals(inclTechInvtyFlg)
                && LOC_TP.TECHNICIAN.equals(invtyLoc)) {
            return true;
        }
      //END   ADD S.Yoshida [Def.3149]

// START MOD 12/10/2013 Def.2957
//        return getLocRoleTp(locTp, invtyLoc).contains(LOC_ROLE_TP.RGRL_STK_WH);
        return getLocRoleTp(locTp, invtyLoc).contains(LOC_ROLE_TP.REGULAR_STOCK_WAREHOUSE);
// END   MOD 12/10/2013 Def.2957
    }

    /**
     * is Return Asset Wharehouse
     * @param locTp Location Type Code
     * @param invtyLoc Inventory Location Code
     * @return ture is Return Asset Wharehouse.
     */
    private boolean isRtrnAssetWh(String locTp, String invtyLoc) {
// START MOD 12/10/2013 Def.2957
//        return getLocRoleTp(locTp, invtyLoc).contains(LOC_ROLE_TP.RTRN_ASSET_WH);
        return getLocRoleTp(locTp, invtyLoc).contains(LOC_ROLE_TP.RETURNED_ASSET_WAREHOUSE);
// END   MOD 12/10/2013 Def.2957
    }

    /**
     * Get Location Role Type<br>
     * Location has many location role type.
     * @param locTp Location Type
     * @param locCd Location Code
     * @return Location Role Type List
     */
    private List<String> getLocRoleTp(String locTp, String locCd) {

        List<String> locRoleTpList = new ArrayList<String>();

        if (!LOC_TP.WAREHOUSE.equals(locTp)) {
            return locRoleTpList;
        }

        if (locRoleTpMap.containsKey(locCd)) {
            return locRoleTpMap.get(locCd);
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("whCd", locCd);

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            // Execute search for delete query.
            stmt = ssmLLClient.createPreparedStatement("getLocRoleTp", params);
            rs = stmt.executeQuery();

            while (rs.next()) {
                locRoleTpList.add(rs.getString(COL_LOC_ROLE_TP_CD));
            }

            locRoleTpMap.put(locCd, locRoleTpList);

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

        return locRoleTpList;
    }
//END   ADD S.Yoshida [Def.2928]

    /**
     * processing Interface<br>
     * NLCI0160, NLCI0090, NLCI0170, NLCI0100, NLCI0430
     * @param ifWorkList
     */
    private void procInterface(List<NLCB017001InterfaceWork> ifWorkList) {

        if (hasInterfaceId(INTERFACE_ID_NLCI0160, interfaceIdList)) {

            procAlci0160(ifWorkList);
        }
//START DELETE S.Yoshida [CMEX localize]
//        if (hasInterfaceId(INTERFACE_ID_NLCI0090, interfaceIdList)) {
//
//            procAlci0090(ifWorkList);
//        }
//END   DELETE S.Yoshida [CMEX localize]

        if (hasInterfaceId(INTERFACE_ID_NLCI0170, interfaceIdList)) {

            procAlci0170(ifWorkList);
        }
//START DELETE S.Yoshida [CMEX localize]
//        if (hasInterfaceId(INTERFACE_ID_NLCI0100, interfaceIdList)) {
//
//            procAlci0100(ifWorkList);
//        }
//
//        if (hasInterfaceId(INTERFACE_ID_NLCI0430, interfaceIdList)) {
//
//            procAlci0430(ifWorkList);
//        }
//END   DELETE S.Yoshida [CMEX localize]
    }

//START DELETE S.Yoshida [CMEX localize]
//    /**
//     * processing CAS Interface<br>
//     * NLCI0060, NLCI0070
//     * @param ifWorkCasList
//     */
//    private void procCasInterface(List<NLCB017001InterfaceWork> ifWorkCasList) {
//
//        if (hasInterfaceId(INTERFACE_ID_NLCI0060, interfaceIdList)) {
//
//            procAlci0060(ifWorkCasList);
//        }
//        if (hasInterfaceId(INTERFACE_ID_NLCI0070, interfaceIdList)) {
//
//            procAlci0070(ifWorkCasList);
//        }
//    }
//END DELETE S.Yoshida [CMEX localize]

    /**
     * Insert to the NLCI0160 from the Interface Work List.
     * @param ifWorkList
     */
    private void procAlci0160(List<NLCB017001InterfaceWork> ifWorkList) {

        List<NLCI0160_01TMsg> alci0160List = new ArrayList<NLCI0160_01TMsg>();

        for (NLCB017001InterfaceWork ifWork : ifWorkList) {
            if (isQtyOverFlow(ifWork)) {
                S21InfoLogOutput.println(MSG_ID_ZZM9001E, new String[] {"NLCI0160 SCM_DB_QTY" });
                outputIfWork(ifWork, "Overflow");
                continue;
            }
            alci0160List.add(getAlci0160TMsg(ifWork, ++insertCountAlci0160));
        }
        if (alci0160List.isEmpty()) {
            return;
        }

        int execCnt = S21FastTBLAccessor.insert((NLCI0160_01TMsg[]) alci0160List.toArray(new NLCI0160_01TMsg[0]));
        if (execCnt != alci0160List.size()) {
            S21InfoLogOutput.println(S21StringUtil.concatStrings(MSG_ID_NLCM0053E, ":", INTERFACE_ID_NLCI0160, ":", execCnt, "/", alci0160List.size()));
            throw new S21AbendException(MSG_ID_NLCM0053E);
        }
    }

//START DELETE S.Yoshida [CMEX localize]
//    /**
//     * Insert to the NLCI0090 from the Interface Work List.
//     * @param ifWorkList
//     */
//    private void procAlci0090(List<NLCB017001InterfaceWork> ifWorkList) {
//
//        List<NLCI0090_01TMsg> alci0090List = new ArrayList<NLCI0090_01TMsg>();
//
//        for (NLCB017001InterfaceWork ifWork : ifWorkList) {
//            if (isQtyOverFlow(ifWork)) {
//                S21InfoLogOutput.println(MSG_ID_ZZM9001E, new String[] {"NLCI0090 SCM_DB_QTY" });
//                outputIfWork(ifWork, "Overflow");
//                continue;
//            }
//            alci0090List.add(getAlci0090TMsg(ifWork, ++insertCountAlci0090));
//        }
//        if (alci0090List.isEmpty()) {
//            return;
//        }
//
//        int execCnt = S21FastTBLAccessor.insert((NLCI0090_01TMsg[]) alci0090List.toArray(new NLCI0090_01TMsg[0]));
//        if (execCnt != alci0090List.size()) {
//            S21InfoLogOutput.println(S21StringUtil.concatStrings(MSG_ID_NLCM0053E, ":", INTERFACE_ID_NLCI0090, ":", execCnt, "/", alci0090List.size()));
//            throw new S21AbendException(MSG_ID_NLCM0053E);
//        }
//    }
//END   DELETE S.Yoshida [CMEX localize]

    /**
     * Insert to the NLCI0170 from the Interface Work List.
     * @param ifWorkList
     */
    private void procAlci0170(List<NLCB017001InterfaceWork> ifWorkList) {

        List<NLCI0170_01TMsg> alci0170List = new ArrayList<NLCI0170_01TMsg>();

        for (NLCB017001InterfaceWork ifWork : ifWorkList) {
            if (isQtyOverFlow(ifWork)) {
                S21InfoLogOutput.println(MSG_ID_ZZM9001E, new String[] {"NLCI0170 SCM_DB_QTY" });
                outputIfWork(ifWork, "Overflow");
                continue;
            }
            alci0170List.add(getAlci0170TMsg(ifWork, ++insertCountAlci0170));
        }
        if (alci0170List.isEmpty()) {
            return;
        }

        int execCnt = S21FastTBLAccessor.insert((NLCI0170_01TMsg[]) alci0170List.toArray(new NLCI0170_01TMsg[0]));
        if (execCnt != alci0170List.size()) {
            S21InfoLogOutput.println(S21StringUtil.concatStrings(MSG_ID_NLCM0053E, ":", INTERFACE_ID_NLCI0170, ":", execCnt, "/", alci0170List.size()));
            throw new S21AbendException(MSG_ID_NLCM0053E);
        }
    }

//START DELETE S.Yoshida [CMEX localize]
//    /**
//     * Insert to the NLCI0100 from the Interface Work List.
//     * @param ifWorkList
//     */
//    private void procAlci0100(List<NLCB017001InterfaceWork> ifWorkList) {
//
//        List<NLCI0100_01TMsg> alci0100List = new ArrayList<NLCI0100_01TMsg>();
//
//        for (NLCB017001InterfaceWork ifWork : ifWorkList) {
//            if (isQtyOverFlow(ifWork)) {
//                S21InfoLogOutput.println(MSG_ID_ZZM9001E, new String[] {"NLCI0100 SCM_DB_QTY" });
//                outputIfWork(ifWork, "Overflow");
//                continue;
//            }
//            alci0100List.add(getAlci0100TMsg(ifWork, ++insertCountAlci0100));
//        }
//        if (alci0100List.isEmpty()) {
//            return;
//        }
//
//        int execCnt = S21FastTBLAccessor.insert((NLCI0100_01TMsg[]) alci0100List.toArray(new NLCI0100_01TMsg[0]));
//        if (execCnt != alci0100List.size()) {
//            S21InfoLogOutput.println(S21StringUtil.concatStrings(MSG_ID_NLCM0053E, ":", INTERFACE_ID_NLCI0100, ":", execCnt, "/", alci0100List.size()));
//            throw new S21AbendException(MSG_ID_NLCM0053E);
//        }
//    }
//
//    /**
//     * Insert to the NLCI0060 from the Interface Work CAS List.
//     * @param ifWorkCasList
//     */
//    private void procAlci0060(List<NLCB017001InterfaceWork> ifWorkCasList) {
//
//        List<NLCI0060_01TMsg> alci0060List = new ArrayList<NLCI0060_01TMsg>();
//
//        for (NLCB017001InterfaceWork ifWorkCas : ifWorkCasList) {
//            if (isQtyOverFlow(ifWorkCas)) {
//                S21InfoLogOutput.println(MSG_ID_ZZM9001E, new String[] {"NLCI0060 SCM_DB_QTY" });
//                outputIfWork(ifWorkCas, "Overflow");
//                continue;
//            }
//            alci0060List.add(getAlci0060TMsg(ifWorkCas, ++insertCountAlci0060));
//        }
//        if (alci0060List.isEmpty()) {
//            return;
//        }
//
//        int execCnt = S21FastTBLAccessor.insert((NLCI0060_01TMsg[]) alci0060List.toArray(new NLCI0060_01TMsg[0]));
//        if (execCnt != alci0060List.size()) {
//            S21InfoLogOutput.println(S21StringUtil.concatStrings(MSG_ID_NLCM0053E, ":", INTERFACE_ID_NLCI0060, ":", execCnt, "/", alci0060List.size()));
//            throw new S21AbendException(MSG_ID_NLCM0053E);
//        }
//    }
//
//    /**
//     * Insert to the NLCI0070 from the Interface Work CAS List.
//     * @param ifWorkCasList
//     */
//    private void procAlci0070(List<NLCB017001InterfaceWork> ifWorkCasList) {
//
//        List<NLCI0070_01TMsg> alci0070List = new ArrayList<NLCI0070_01TMsg>();
//
//        for (NLCB017001InterfaceWork ifWorkCas : ifWorkCasList) {
//            if (isQtyOverFlow(ifWorkCas)) {
//                S21InfoLogOutput.println(MSG_ID_ZZM9001E, new String[] {"NLCI0070 SCM_DB_QTY" });
//                outputIfWork(ifWorkCas, "Overflow");
//                continue;
//            }
//            alci0070List.add(getAlci0070TMsg(ifWorkCas));
//        }
//        if (alci0070List.isEmpty()) {
//            return;
//        }
//
//        int execCnt = S21FastTBLAccessor.insert((NLCI0070_01TMsg[]) alci0070List.toArray(new NLCI0070_01TMsg[0]));
//        if (execCnt != alci0070List.size()) {
//            S21InfoLogOutput.println(S21StringUtil.concatStrings(MSG_ID_NLCM0053E, ":", INTERFACE_ID_NLCI0070, ":", execCnt, "/", alci0070List.size()));
//            throw new S21AbendException(MSG_ID_NLCM0053E);
//        }
//    }
//
//    /**
//     * Insert to the NLCI0430 from the Interface Work List.
//     * @param ifWorkList
//     */
//    private void procAlci0430(List<NLCB017001InterfaceWork> ifWorkList) {
//
//        List<NLCI0430_01TMsg> alci0430List = new ArrayList<NLCI0430_01TMsg>();
//
//        for (NLCB017001InterfaceWork ifWork : ifWorkList) {
//            if (isQtyOverFlow(ifWork)) {
//                outputIfWork(ifWork, "Overflow");
//                continue;
//            }
//            alci0430List.add(getAlci0430TMsg(ifWork, ++insertCountAlci0430));
//        }
//        if (alci0430List.isEmpty()) {
//            return;
//        }
//
//        int execCnt = S21FastTBLAccessor.insert((NLCI0430_01TMsg[]) alci0430List.toArray(new NLCI0430_01TMsg[0]));
//        if (execCnt != alci0430List.size()) {
//            S21InfoLogOutput.println(S21StringUtil.concatStrings(MSG_ID_NLCM0053E, ":", INTERFACE_ID_NLCI0090, ":", execCnt, "/", alci0430List.size()));
//            throw new S21AbendException(MSG_ID_NLCM0053E);
//        }
//    }
//END   DELETE S.Yoshida [CMEX localize]

    /**
     * Output with the NLCI0160_01TMsg. The input is from
     * NLCB017001InterfaceWork.
     * @param ifWork NLCB017001InterfaceWork
     * @param unitId unit ID
     * @return NLCI0160_01TMsg
     */
    private NLCI0160_01TMsg getAlci0160TMsg(NLCB017001InterfaceWork ifWork, int unitId) {

        NLCI0160_01TMsg alci0160TMsg = new NLCI0160_01TMsg();

        ZYPEZDItemValueSetter.setValue(alci0160TMsg.interfaceId, INTERFACE_ID_NLCI0160);
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.transactionId, transactionIdAlci0160);
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.unitId, new BigDecimal(unitId));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.seqNumber, BigDecimal.ONE);

        ZYPEZDItemValueSetter.setValue(alci0160TMsg.amendCd, "");
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.glblCmpy3Cd, ifWork.getGlblCmpy3Cd(LENGTH_GLBL_CMPY_3_CD));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.mdse12Cd, ifWork.getMdse12Cd(LENGTH_MDSE_12_CD));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.localMdseCd, ifWork.getLocalMdseCd());
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.intfcWhCd, "");

        ZYPEZDItemValueSetter.setValue(alci0160TMsg.adminYrMth, intfcSlsYrMth);
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.adminDt, ifWork.getAdminDt());

        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_01, getScmDbQty(ifWork.getScmDbQty01()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_02, getScmDbQty(ifWork.getScmDbQty02()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_03, getScmDbQty(ifWork.getScmDbQty03()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_04, getScmDbQty(ifWork.getScmDbQty04()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_05, getScmDbQty(ifWork.getScmDbQty05()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_06, getScmDbQty(ifWork.getScmDbQty06()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_07, getScmDbQty(ifWork.getScmDbQty07()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_08, getScmDbQty(ifWork.getScmDbQty08()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_09, getScmDbQty(ifWork.getScmDbQty09()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_10, getScmDbQty(ifWork.getScmDbQty10()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_11, getScmDbQty(ifWork.getScmDbQty11()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_12, getScmDbQty(ifWork.getScmDbQty12()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_13, getScmDbQty(ifWork.getScmDbQty13()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_14, getScmDbQty(ifWork.getScmDbQty14()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_15, getScmDbQty(ifWork.getScmDbQty15()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_16, getScmDbQty(ifWork.getScmDbQty16()));
        ZYPEZDItemValueSetter.setValue(alci0160TMsg.scmDbQty_17, getScmDbQty(ifWork.getScmDbQty17()));

        ZYPEZDItemValueSetter.setValue(alci0160TMsg.fill25Txt, "");

        return alci0160TMsg;
    }

//START DELETE S.Yoshida [CMEX localize]
//    /**
//     * Output with the NLCI0090_01TMsg. The input is from
//     * NLCB017001InterfaceWork.
//     * @param ifWork NLCB017001InterfaceWork
//     * @param unitId unit ID
//     * @return NLCI0090_01TMsg
//     */
//    private NLCI0090_01TMsg getAlci0090TMsg(NLCB017001InterfaceWork ifWork, int unitId) {
//
//        NLCI0090_01TMsg alci0090TMsg = new NLCI0090_01TMsg();
//
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.interfaceId, INTERFACE_ID_NLCI0090);
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.transactionId, transactionIdAlci0090);
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.segmentId, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.unitId, new BigDecimal(unitId));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.seqNumber, BigDecimal.ONE);
//
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.amendCd, "");
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.glblCmpy3Cd, ifWork.getGlblCmpy3Cd(LENGTH_GLBL_CMPY_3_CD));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.mdse12Cd, ifWork.getMdse12Cd(LENGTH_MDSE_12_CD));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.localMdseCd, ifWork.getLocalMdseCd());
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.intfcWhCd, "");
//
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.adminYrMth, intfcSlsYrMth);
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.adminDt, ifWork.getAdminDt());
//
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_01, getScmDbQty(ifWork.getScmDbQty01()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_02, getScmDbQty(ifWork.getScmDbQty02()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_03, getScmDbQty(ifWork.getScmDbQty03()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_04, getScmDbQty(ifWork.getScmDbQty04()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_05, getScmDbQty(ifWork.getScmDbQty05()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_06, getScmDbQty(ifWork.getScmDbQty06()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_07, getScmDbQty(ifWork.getScmDbQty07()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_08, getScmDbQty(ifWork.getScmDbQty08()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_09, getScmDbQty(ifWork.getScmDbQty09()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_10, getScmDbQty(ifWork.getScmDbQty10()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_11, getScmDbQty(ifWork.getScmDbQty11()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_12, getScmDbQty(ifWork.getScmDbQty12()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_13, getScmDbQty(ifWork.getScmDbQty13()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_14, getScmDbQty(ifWork.getScmDbQty14()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_15, getScmDbQty(ifWork.getScmDbQty15()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_16, getScmDbQty(ifWork.getScmDbQty16()));
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.scmDbQty_17, getScmDbQty(ifWork.getScmDbQty17()));
//
//        ZYPEZDItemValueSetter.setValue(alci0090TMsg.fill25Txt, "");
//
//        return alci0090TMsg;
//    }
//END   DELETE S.Yoshida [CMEX localize]

    /**
     * Output with the NLCI0170_01TMsg. The input is from
     * NLCB017001InterfaceWork.
     * @param ifWork NLCB017001InterfaceWork
     * @param unitId unit ID
     * @return NLCI0170_01TMsg
     */
    private NLCI0170_01TMsg getAlci0170TMsg(NLCB017001InterfaceWork ifWork, int unitId) {

        NLCI0170_01TMsg alci0170TMsg = new NLCI0170_01TMsg();

        // alci0170TMsg and alci0160TMsg are same layout
        EZDMsg.copy(getAlci0160TMsg(ifWork, unitId), null, alci0170TMsg, null);

        ZYPEZDItemValueSetter.setValue(alci0170TMsg.interfaceId, INTERFACE_ID_NLCI0170);
        ZYPEZDItemValueSetter.setValue(alci0170TMsg.transactionId, transactionIdAlci0170);

        return alci0170TMsg;
    }

//START DELETE S.Yoshida [CMEX localize]
//    /**
//     * Output with the NLCI0100_01TMsg. The input is from
//     * NLCB017001InterfaceWork.
//     * @param ifWork NLCB017001InterfaceWork
//     * @param unitId unit ID
//     * @return NLCI0100_01TMsg
//     */
//    private NLCI0100_01TMsg getAlci0100TMsg(NLCB017001InterfaceWork ifWork, int unitId) {
//
//        NLCI0100_01TMsg alci0100TMsg = new NLCI0100_01TMsg();
//
//        // alci0090TMsg and alci0100TMsg are same layout
//        EZDMsg.copy(getAlci0090TMsg(ifWork, unitId), null, alci0100TMsg, null);
//
//        ZYPEZDItemValueSetter.setValue(alci0100TMsg.interfaceId, INTERFACE_ID_NLCI0100);
//        ZYPEZDItemValueSetter.setValue(alci0100TMsg.transactionId, transactionIdAlci0100);
//
//        return alci0100TMsg;
//    }
//
//    /**
//     * Output with the NLCI0060_01TMsg. The input is from
//     * NLCB017001InterfaceWork.
//     * @param ifWorkCas NLCB017001InterfaceWork
//     * @param unitId unit ID
//     * @return NLCI0060_01TMsg
//     */
//    private NLCI0060_01TMsg getAlci0060TMsg(NLCB017001InterfaceWork ifWorkCas, int unitId) {
//
//        NLCI0060_01TMsg alci0060TMsg = new NLCI0060_01TMsg();
//
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.interfaceId, INTERFACE_ID_NLCI0060);
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.transactionId, transactionIdAlci0060);
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.segmentId, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.unitId, new BigDecimal(unitId));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.seqNumber, BigDecimal.ONE);
//
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.amendCd, "");
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.glblCmpy3Cd, ifWorkCas.getGlblCmpy3Cd(LENGTH_GLBL_CMPY_3_CD));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.mdse12Cd, ifWorkCas.getMdse12Cd(LENGTH_MDSE_12_CD));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.localMdseCd, ifWorkCas.getLocalMdseCd());
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.intfcWhCd, "");
//
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.adminYrMth, intfcSlsYrMth);
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.adminDt, ifWorkCas.getAdminDt());
//
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_01, getScmDbQty(ifWorkCas.getScmDbQty01()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_02, getScmDbQty(ifWorkCas.getScmDbQty02()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_03, getScmDbQty(ifWorkCas.getScmDbQty03()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_04, getScmDbQty(ifWorkCas.getScmDbQty04()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_05, getScmDbQty(ifWorkCas.getScmDbQty05()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_06, getScmDbQty(ifWorkCas.getScmDbQty06()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_07, getScmDbQty(ifWorkCas.getScmDbQty07()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_08, getScmDbQty(ifWorkCas.getScmDbQty08()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_09, getScmDbQty(ifWorkCas.getScmDbQty09()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_10, getScmDbQty(ifWorkCas.getScmDbQty10()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_11, getScmDbQty(ifWorkCas.getScmDbQty11()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_12, getScmDbQty(ifWorkCas.getScmDbQty12()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_13, getScmDbQty(ifWorkCas.getScmDbQty13()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_14, getScmDbQty(ifWorkCas.getScmDbQty14()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_15, getScmDbQty(ifWorkCas.getScmDbQty15()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_16, getScmDbQty(ifWorkCas.getScmDbQty16()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_17, getScmDbQty(ifWorkCas.getScmDbQty17()));
//
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.defIntfcWhCd, ifWorkCas.getDefIntfcWhCd());
//
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_18, getScmDbQty(ifWorkCas.getScmDbQty18()));
//        ZYPEZDItemValueSetter.setValue(alci0060TMsg.scmDbQty_19, getScmDbQty(ifWorkCas.getScmDbQty19()));
//
//        return alci0060TMsg;
//    }
//
//    /**
//     * Output with the NLCI0070_01TMsg. The input is from
//     * NLCB017001InterfaceWork.
//     * @param ifWorkCas NLCB017001InterfaceWork
//     * @param unitId unit ID
//     * @return NLCI0070_01TMsg
//     */
//    private NLCI0070_01TMsg getAlci0070TMsg(NLCB017001InterfaceWork ifWorkCas) {
//
//        NLCI0070_01TMsg alci0070TMsg = new NLCI0070_01TMsg();
//
//        // alci0060TMsg and alci0070TMsg are same layout
//        EZDMsg.copy(getAlci0060TMsg(ifWorkCas, ++insertCountAlci0070), null, alci0070TMsg, null);
//
//        ZYPEZDItemValueSetter.setValue(alci0070TMsg.interfaceId, INTERFACE_ID_NLCI0070);
//        ZYPEZDItemValueSetter.setValue(alci0070TMsg.transactionId, transactionIdAlci0070);
//
//        return alci0070TMsg;
//    }
//
//    /**
//     * Output with the NLCI0430_01TMsg. The input is from
//     * NLCB017001InterfaceWork.
//     * @param ifWork NLCB017001InterfaceWork
//     * @param unitId unit ID
//     * @return NLCI0430_01TMsg
//     */
//    private NLCI0430_01TMsg getAlci0430TMsg(NLCB017001InterfaceWork ifWork, int unitId) {
//
//        NLCI0430_01TMsg alci0430TMsg = new NLCI0430_01TMsg();
//
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.interfaceId, INTERFACE_ID_NLCI0430);
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.transactionId, transactionIdAlci0430);
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.segmentId, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.unitId, new BigDecimal(unitId));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.seqNumber, BigDecimal.ONE);
//
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.amendCd, "");
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.glblCmpy3Cd, ifWork.getGlblCmpy3Cd(LENGTH_GLBL_CMPY_3_CD));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.mdse12Cd, ifWork.getMdse12Cd(LENGTH_MDSE_12_CD));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.localMdseCd, ifWork.getLocalMdseCd());
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.intfcWhCd, ifWork.getIntfcWhCd());
//
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.adminYrMth, intfcSlsYrMth);
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.adminDt, ifWork.getAdminDt());
//
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_01, getScmDbQty(ifWork.getScmDbQty01()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_02, getScmDbQty(ifWork.getScmDbQty02()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_03, getScmDbQty(ifWork.getScmDbQty03()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_04, getScmDbQty(ifWork.getScmDbQty04()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_05, getScmDbQty(ifWork.getScmDbQty05()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_06, getScmDbQty(ifWork.getScmDbQty06()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_07, getScmDbQty(ifWork.getScmDbQty07()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_08, getScmDbQty(ifWork.getScmDbQty08()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_09, getScmDbQty(ifWork.getScmDbQty09()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_10, getScmDbQty(ifWork.getScmDbQty10()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_11, getScmDbQty(ifWork.getScmDbQty11()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_12, getScmDbQty(ifWork.getScmDbQty12()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_13, getScmDbQty(ifWork.getScmDbQty13()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_14, getScmDbQty(ifWork.getScmDbQty14()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_15, getScmDbQty(ifWork.getScmDbQty15()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_16, getScmDbQty(ifWork.getScmDbQty16()));
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.scmDbQty_17, getScmDbQty(ifWork.getScmDbQty17()));
//
//        ZYPEZDItemValueSetter.setValue(alci0430TMsg.fill25Txt, "");
//
//        return alci0430TMsg;
//    }
//END   DELETE S.Yoshida [CMEX localize]

    /**
     * Has Interface ID List interface Id?
     * @param id Interface ID
     * @param ifList Interface Id List
     */
    private static boolean hasInterfaceId(String id, List<String> ifList) {

        for (String interfaceId : ifList) {
            if (id.equals(interfaceId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Insert Integration Racord - Interface Transaction by interface
     * id and transaction id -
     */
    private void insertIntegrationRacord() {

        for (String interfaceId : interfaceIdList) {

            // Create INTERFACE_TRANSACTION for send blank data to
            // COMETS.
            // Sending blan file or not is depend on the configuratin
            // of WMB .
            if (INTERFACE_ID_NLCI0160.equals(interfaceId)) {
                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0160);
//START DELETE S.Yoshida [CMEX localize]
//            } else if (INTERFACE_ID_NLCI0090.equals(interfaceId)) {
//                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0090);
//END   DELETE S.Yoshida [CMEX localize]
            } else if (INTERFACE_ID_NLCI0170.equals(interfaceId)) {
                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0170);
//START DELETE S.Yoshida [CMEX localize]
//            } else if (INTERFACE_ID_NLCI0100.equals(interfaceId)) {
//                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0100);
//            } else if (INTERFACE_ID_NLCI0060.equals(interfaceId)) {
//                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0060);
//            } else if (INTERFACE_ID_NLCI0430.equals(interfaceId)) {
//                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0430);
//            } else if (INTERFACE_ID_NLCI0070.equals(interfaceId)) {
//                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0070);
//END   DELETE S.Yoshida [CMEX localize]
            }
        }
        commit();
    }

    /**
     * Is SCM/DB Qty Overflow? - from 01 to 19 -
     * @param interfaceWork.
     */
    private static boolean isQtyOverFlow(NLCB017001InterfaceWork ifWork) {

        if (isQtyOverFlow(ifWork.getScmDbQty01())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty02())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty03())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty04())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty05())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty06())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty07())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty08())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty09())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty10())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty11())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty12())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty13())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty14())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty15())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty16())) {
            return true;
        }
        if (isQtyOverFlow(ifWork.getScmDbQty17())) {
            return true;
        }
//START DEL S.Yoshida [CMEX localize]
//        if (isQtyOverFlow(ifWork.getScmDbQty18())) {
//            return true;
//        }
//        if (isQtyOverFlow(ifWork.getScmDbQty19())) {
//            return true;
//        }
//END   DEL S.Yoshida [CMEX localize]
        return false;
    }

    /**
     * Is SCM/DB Qty Overflow? max : 999,999,999 / min : -999,999,999
     * @param bdQty SCM/DB Qty.
     */
    private static boolean isQtyOverFlow(BigDecimal bdQty) {

        // Overflow
        if ((MAX_WRK_DB_QTY.compareTo(bdQty) < 0) || (MIN_WRK_DB_QTY.compareTo(bdQty) > 0)) {
            return true;
        }
        return false;
    }

    /**
     * Get SCM/DB Qty by Decimal Format<br>
     * Positive qty is formatted to "000000000+".<br>
     * Negative qty is formatted to "000000000-".
     * @param bdQty SCM/DB Qty.
     */
    private static String getScmDbQty(BigDecimal bdQty) {

        // Overflow
        if (isQtyOverFlow(bdQty)) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("000000000'+';000000000-");
        return df.format(bdQty);
    }

    /**
     * Print with NLCB017001InterfaceWork to S21InfoLog.
     * @param ifWork
     * @param msg message
     */
    private static void outputIfWork(NLCB017001InterfaceWork ifWork, String msg) {

        S21InfoLogOutput.println("+++ " + msg + " +++");
        S21InfoLogOutput.println("  amendCd     :" + ifWork.getAmendCd());
        S21InfoLogOutput.println("  glblCmpy3Cd :" + ifWork.getGlblCmpy3Cd());
        S21InfoLogOutput.println("  mdse12Cd    :" + ifWork.getMdse12Cd());
        S21InfoLogOutput.println("  localMdseCd :" + ifWork.getLocalMdseCd());
        S21InfoLogOutput.println("  intfcWhCd   :" + ifWork.getIntfcWhCd());
        S21InfoLogOutput.println("  adminYrMth  :" + ifWork.getAdminYrMth());
        S21InfoLogOutput.println("  adminDt     :" + ifWork.getAdminDt());

        S21InfoLogOutput.println("  scmDbQty01  :" + ifWork.getScmDbQty01());
        S21InfoLogOutput.println("  scmDbQty02  :" + ifWork.getScmDbQty02());
        S21InfoLogOutput.println("  scmDbQty03  :" + ifWork.getScmDbQty03());
        S21InfoLogOutput.println("  scmDbQty04  :" + ifWork.getScmDbQty04());
        S21InfoLogOutput.println("  scmDbQty05  :" + ifWork.getScmDbQty05());
        S21InfoLogOutput.println("  scmDbQty06  :" + ifWork.getScmDbQty06());
        S21InfoLogOutput.println("  scmDbQty07  :" + ifWork.getScmDbQty07());
        S21InfoLogOutput.println("  scmDbQty08  :" + ifWork.getScmDbQty08());
        S21InfoLogOutput.println("  scmDbQty09  :" + ifWork.getScmDbQty09());
        S21InfoLogOutput.println("  scmDbQty10  :" + ifWork.getScmDbQty10());
        S21InfoLogOutput.println("  scmDbQty11  :" + ifWork.getScmDbQty11());
        S21InfoLogOutput.println("  scmDbQty12  :" + ifWork.getScmDbQty12());
        S21InfoLogOutput.println("  scmDbQty13  :" + ifWork.getScmDbQty13());
        S21InfoLogOutput.println("  scmDbQty14  :" + ifWork.getScmDbQty14());
        S21InfoLogOutput.println("  scmDbQty15  :" + ifWork.getScmDbQty15());
        S21InfoLogOutput.println("  scmDbQty16  :" + ifWork.getScmDbQty16());
        S21InfoLogOutput.println("  scmDbQty17  :" + ifWork.getScmDbQty17());
//START DEL S.Yoshida [CMEX localize]
//        S21InfoLogOutput.println("  defIntfcWhCd:" + ifWork.getDefIntfcWhCd());
//        S21InfoLogOutput.println("  scmDbQty18  :" + ifWork.getScmDbQty18());
//        S21InfoLogOutput.println("  scmDbQty19  :" + ifWork.getScmDbQty19());
//END   DEL S.Yoshida [CMEX localize]
        S21InfoLogOutput.println("  fill25Txt   :" + ifWork.getFill25Txt());

        S21InfoLogOutput.println("+++---------------------------------+++");
    }

    /**
     * Get Interface Sales Year Month.
     * 
     * @return Interface Sales Year Month
     */
    private String getIntfcSlsYrMth() {

        INTFC_SLS_MTH_MGTTMsg iFSlsMthTMsg = new INTFC_SLS_MTH_MGTTMsg();
        iFSlsMthTMsg.glblCmpyCd.setValue(glblCmpyCd);
        iFSlsMthTMsg = (INTFC_SLS_MTH_MGTTMsg) S21FastTBLAccessor.findByKey(iFSlsMthTMsg);
        if (iFSlsMthTMsg == null) {
            S21InfoLogOutput.println("Interface Sales Year Month dose not exist.");
            throw new S21AbendException(MSG_ID_NLCM0053E);
        }
        return iFSlsMthTMsg.intfcSlsYrMth.getValue();
    }

    /**
     * Print debug log.
     * @param debugMsg debug message
     */
//    private void printDebugLog(String debugMsg) {
//        if (EZDDebugOutput.isDebug()) {
//            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, debugMsg, this);
//        }
//    }
}
