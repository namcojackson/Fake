/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC042001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.api.NLZ.NLZC302001.NLZC302001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Service Machine Master Check
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/27/2016   CSAI            Y.Imazu         Create          CSA
 * 07/01/2016   CSAI            K.Lee           Update          Configuration Change
 * 12/08/2016   CITS            T.Hakodate      UPDATE          QC#16239
 * 02/08/2017   CITS            K.Ogino         UPDATE          QC#17393
 * 03/22/2017   CITS            R.Shimamoto     UPDATE          QC#17919
 * 07/06/2017   CITS            T.Kikuhara      UPDATE          QC#19642
 * 07/07/2017   CITS            Y.Imazu         Update          QC#19730
 * 07/21/2017   CITS            K.Ogino         UPDATE          QC#20008
 * 10/31/2017   CITS            T.Tokutomi      Update          QC#21528
 * 11/16/2017   CITS            T.Tokutomi      Update          QC#22178
 * 10/03/2019   CITS            T.Wada          Update          QC#53385
 * </pre>
 */
public class NLXC042001SvcMachMstrCheck {

    /** S21SsmBatchClient */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NLXC042001SvcMachMstrCheck.class);

    /***************************************************
     * Duplicate Check
     ***************************************************/

    /** Duplicate Check : Serial Only */
    private static final String DUP_CHK_SERIAL = "1";

    /** Duplicate Check : Serial and Model */
    private static final String DUP_CHK_MDL = "2";

    /** Duplicate Check : Serial and MDSE */
    private static final String DUP_CHK_MDSE = "3";

    /** Duplicate Check List */
    private static final String[] DUP_CHK_LIST = {DUP_CHK_SERIAL, DUP_CHK_MDL, DUP_CHK_MDSE };

    /**
     * Get Duplicate Check List
     * @return String[]
     */
    private static String[] getDupChkList() {

        String[] dupChkList = DUP_CHK_LIST;
        return dupChkList;
    }

    /***************************************************
     * SCE Order Type (Ship)
     ***************************************************/

    /** SCE Order Type : CPO */
    private static final String[] SHIP_SCE_ORD_TP_CPO = {SCE_ORD_TP.DIRECT_SALES, SCE_ORD_TP.DC_TRANSFER };

    /** SCE Order Type : Tech Request */
    private static final String[] SHIP_SCE_ORD_TP_TR = {SCE_ORD_TP.TECH_REQUEST };

    /** SCE Order Type : Update */
    private static final String[] SHIP_SCE_ORD_TP_UPD = {SCE_ORD_TP.REPAIR_SUBCONTRACT, SCE_ORD_TP.KITTING, SCE_ORD_TP.UN_KITTING };

    /** SCE Order Type : Completion */
    private static final String[] SHIP_SCE_ORD_TP_COMP = {SCE_ORD_TP.STOCK_STATUS_CHANGE, SCE_ORD_TP.SUB_WH_CHANGE, SCE_ORD_TP.INTERNAL_TRANSFER, SCE_ORD_TP.CONFIG_CHANGE};

    /** SCE Order Type : Disposal */
    private static final String[] SHIP_SCE_ORD_TP_DSPL = {SCE_ORD_TP.REFURBISH_EXPENSE, SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC, SCE_ORD_TP.DISPOSAL, SCE_ORD_TP.ITEM_CHANGE, SCE_ORD_TP.REMAN_ITEM_CHANGE };

    /***************************************************
     * SCE Order Type (Receive)
     ***************************************************/

    /** SCE Order Type : New Creation */
    private static final String[] RCV_SCE_ORD_TP_CRAT = {SCE_ORD_TP.DOMESTIC_PO_EXISTS, SCE_ORD_TP.DOMESTIC_CANON_GROUP, SCE_ORD_TP.DOMESTIC_NON_CSA_INVENTORY
                                                         , SCE_ORD_TP.KITTING, SCE_ORD_TP.UN_KITTING, SCE_ORD_TP.REPAIR_SUBCONTRACT, SCE_ORD_TP.ITEM_CHANGE, SCE_ORD_TP.REMAN_ITEM_CHANGE };

    /** SCE Order Type : Transfer */
    private static final String[] RCV_SCE_ORD_TP_TRNSF = {SCE_ORD_TP.DC_TRANSFER, SCE_ORD_TP.TECH_REQUEST };

    /** SCE Order Type : Return */
    private static final String[] RCV_SCE_ORD_TP_RTRN = {SCE_ORD_TP.RETURN };

    /** SCE Order Type : Back */
    private static final String[] RCV_SCE_ORD_TP_BK = {SCE_ORD_TP.KITTING_CANCEL, SCE_ORD_TP.BUY_BACK };

    /***************************************************
     * SCE Order Type (Receive Duplicate OK)
     ***************************************************/

    /** SCE Order Type : Back */
    private static final String[] RCV_SCE_ORD_TP_DUP_OK = {SCE_ORD_TP.KITTING, SCE_ORD_TP.UN_KITTING, SCE_ORD_TP.REPAIR_SUBCONTRACT, SCE_ORD_TP.ITEM_CHANGE, SCE_ORD_TP.REMAN_ITEM_CHANGE };

    /***************************************************
     * Message
     ***************************************************/

    /** Serial Number is different from the one previously picked in WH. */
    private static final String NLBM2443W = "NLBM2443W";

    /** Serial Number is different from the one previously assigned by order. */
    private static final String NLBM2444W = "NLBM2444W";

    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
    /** The Serial # specified does not exist in this WH. */
    private static final String NLCM0151E = "NLCM0151E";

    /** The specified Serial # already exists. */
    private static final String NLZM2141E = "NLZM2141E";
    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

    /** SO Number is not entered. */
    private static final String NLZM0027E = "NLZM0027E";

    /** Serial # is not set. */
    private static final String NLZM2091E = "NLZM2091E";

    /** Transaction Header Number is not set. */
    private static final String NLZM2093E = "NLZM2093E";

    /** RWS Number is not set. */
    private static final String NLZM2133E = "NLZM2133E";

    /** SCE Order Type is not set. */
    private static final String NLZM2146E = "NLZM2146E";

    /** Global Company Code is not set. */
    private static final String NLZM2259E = "NLZM2259E";

    /** Merchandise Code is not set. */
    private static final String NLZM2260E = "NLZM2260E";

    /** The entered Serial Number is already allocated by other order. */
    private static final String NLZM2317E = "NLZM2317E";

    /** The entered Serial Number is located at customer site. */
    private static final String NLZM2318E = "NLZM2318E";

    /** The entered Serial Number does not include in the specified configuration. */
    private static final String NLZM2319E = "NLZM2319E";

    /** The specified serial number is the component of the other configuration. */
    private static final String NLZM2324E = "NLZM2324E";

    /** Install Base cannot be uniquely specified. */
    private static final String NLZM2436E = "NLZM2436E";

    /** Ship From Location Code is not set. */
    private static final String NLZM2437E = "NLZM2437E";

    /** Undefined value is set in Duplication Check Code. */
    private static final String NLZM2438E = "NLZM2438E";

    /** Undefined value is set in SCE Order Type. */
    private static final String NLZM2439E = "NLZM2439E";

    /** Receive Retail WH Code is not set. */
    private static final String NLZM2440E = "NLZM2440E";

    /** Serial Number is different from the scheduled serial. */
    private static final String NLBM2441W = "NLBM2441W";

    /** The specified Serial# already exists in other WH. */
    private static final String NLZM2442E = "NLZM2442E";

    /** The specified Serial# is during WH Transfer. */
    private static final String NLZM2443E = "NLZM2443E";

    /** The specified Serial# already exists in this WH. */
    private static final String NLZM2444W = "NLZM2444W";

    /** The specified Serial# already exists in other WH. */
    private static final String NLZM2445W = "NLZM2445W";

    /** The specified Serial# doesn't exist in ship from WH. */
    private static final String NLZM2447W = "NLZM2447W";

    /** The specified Serial# already exists as different item.　Please check the Serial#. */
    private static final String NLZM2450E = "NLZM2450E";

    /** Serial Number is different from the shipped serial. */
    private static final String NLBM1359E = "NLBM1359E";

    /** Stock status of the specified Serial number is different from IB. */
    private static final String NLZM2414E = "NLZM2414E";
    /***************************************************
     * Get SCE Order Type List (Ship)
     ***************************************************/

    // QC#22178
    /***************************************************
     * Varchar_const
     ***************************************************/
    /** VARCHAR_CONST : NLXC042001_SKIP_CONF_CHK_ORD */
    private static final String NLXC042001_SKIP_CONF_CHK_ORD = "NLXC042001_SKIP_CONF_CHK_ORD";

    /**
     * Get SCE Order Type : CPO
     * @return String[]
     */
    private static String[] getShipSceOrdTpCpo() {

        String[] sceOrdTp = SHIP_SCE_ORD_TP_CPO;
        return sceOrdTp;
    }

    /**
     * Get SCE Order Type : Tech Request
     * @return String[]
     */
    private static String[] getShipSceOrdTpTr() {

        String[] sceOrdTp = SHIP_SCE_ORD_TP_TR;
        return sceOrdTp;
    }

    /**
     * Get SCE Order Type : Update
     * @return String[]
     */
    private static String[] getShipSceOrdTpUpd() {

        String[] sceOrdTp = SHIP_SCE_ORD_TP_UPD;
        return sceOrdTp;
    }

    /**
     * Get SCE Order Type : Completion
     * @return String[]
     */
    private static String[] getShipSceOrdTpComp() {

        String[] sceOrdTp = SHIP_SCE_ORD_TP_COMP;
        return sceOrdTp;
    }

    /**
     * Get SCE Order Type : Disposal
     * @return String[]
     */
    private static String[] getShipSceOrdTpDspl() {

        String[] sceOrdTp = SHIP_SCE_ORD_TP_DSPL;
        return sceOrdTp;
    }

    /***************************************************
     * Get SCE Order Type List (Receive)
     ***************************************************/

    /**
     * Get SCE Order Type : New Creation
     * @return String[]
     */
    private static String[] getRcvSceOrdTpCrat() {

        String[] sceOrdTp = RCV_SCE_ORD_TP_CRAT;
        return sceOrdTp;
    }

    /**
     * Get SCE Order Type : Transfer
     * @return String[]
     */
    private static String[] getRcvSceOrdTpTrnsf() {

        String[] sceOrdTp = RCV_SCE_ORD_TP_TRNSF;
        return sceOrdTp;
    }

    /**
     * Get SCE Order Type : Return
     * @return String[]
     */
    private static String[] getRcvSceOrdTpRtrn() {

        String[] sceOrdTp = RCV_SCE_ORD_TP_RTRN;
        return sceOrdTp;
    }

    /**
     * Get SCE Order Type : Back
     * @return String[]
     */
    private static String[] getRcvSceOrdTpBk() {

        String[] sceOrdTp = RCV_SCE_ORD_TP_BK;
        return sceOrdTp;
    }

    /***************************************************
     * Get SCE Order Type List (Receive Duplicate OK)
     ***************************************************/

    /**
     * Get SCE Order Type : Duplication OK
     * @return String[]
     */
    private static String[] getRcvSceOrdTpDupOk() {

        String[] sceOrdTp = RCV_SCE_ORD_TP_DUP_OK;
        return sceOrdTp;
    }

    /**
     * Check Service Machine Master for Ship Serial
     * @param serialInfo NLXC042001SerialInfo
     * @return NLXC042001SerialInfo
     */
    public static NLXC042001SerialInfo chkSvcMachMstrForShipSerial(NLXC042001SerialInfo serialInfo) {

        /*************************************************************
         * 1. Check Parameter
         ************************************************************/
        String errMsgId = chkCommonParam(serialInfo);

        if (ZYPCommonFunc.hasValue(errMsgId)) {

            serialInfo.setErrMsgId(errMsgId);
            return serialInfo;
        }

        errMsgId = chkShipParam(serialInfo);

        if (ZYPCommonFunc.hasValue(errMsgId)) {

            serialInfo.setErrMsgId(errMsgId);
            return serialInfo;
        }

        if (!ZYPCommonFunc.hasValue(serialInfo.getDupChkCd())) {

            serialInfo.setDupChkCd(DUP_CHK_SERIAL);
        }

        /*************************************************************
         * 2. Get Service Machine Master
         ************************************************************/
        List<Map<String, Object>> svcMachMstrList = getSvcMachMstrList(serialInfo, true);

        if (svcMachMstrList == null || svcMachMstrList.isEmpty()) {

            svcMachMstrList = getSvcMachMstrList(serialInfo, false);
        }

        /*************************************************************
         * 3. Specify Service Machine Master
         ************************************************************/
        Map<String, Object> rtrnMap = getSvcMachMstrMap(serialInfo, svcMachMstrList);

        if (ZYPCommonFunc.hasValue((String) rtrnMap.get("errMsg"))) {

            serialInfo.setErrMsgId((String) rtrnMap.get("errMsg"));
            return serialInfo;
        }

        Map<String, Object> svcMachMstrMap = (Map<String, Object>) rtrnMap.get("svcMachMstrMap");

        /*********************************************************
         * 4. Duplicate Check (When not exists Machine Master)
         ********************************************************/
        if (svcMachMstrMap == null || svcMachMstrMap.isEmpty()) {

            rtrnMap = chkDupMachMstr(serialInfo, svcMachMstrList);

            if (ZYPCommonFunc.hasValue((String) rtrnMap.get("errMsg"))) {

                serialInfo.setErrMsgId((String) rtrnMap.get("errMsg"));
                return serialInfo;
            }

            svcMachMstrMap = (Map<String, Object>) rtrnMap.get("svcMachMstrMap");
        }

        /*********************************************************
         * 5. Specify & Execute Event
         ********************************************************/
        // CPO
        if (Arrays.asList(getShipSceOrdTpCpo()).contains(serialInfo.getSceOrdTpCd())) {

            serialInfo = chkShipSvcMachMstrForCpo(serialInfo, svcMachMstrMap);

            // Tech Request
        } else if (Arrays.asList(getShipSceOrdTpTr()).contains(serialInfo.getSceOrdTpCd())) {

            serialInfo = chkShipSvcMachMstrForTr(serialInfo, svcMachMstrMap);

            // Update
        } else if (Arrays.asList(getShipSceOrdTpUpd()).contains(serialInfo.getSceOrdTpCd())) {

            serialInfo = chkShipSvcMachMstrForUpd(serialInfo, svcMachMstrMap);

            // Completion
        } else if (Arrays.asList(getShipSceOrdTpComp()).contains(serialInfo.getSceOrdTpCd())) {

            serialInfo = chkShipSvcMachMstrForComp(serialInfo, svcMachMstrMap);

            // Disposal
        } else if (Arrays.asList(getShipSceOrdTpDspl()).contains(serialInfo.getSceOrdTpCd())) {

            serialInfo = chkShipSvcMachMstrForDspl(serialInfo, svcMachMstrMap);

        } else {

            serialInfo.setErrMsgId(NLZM2439E);
        }

        return serialInfo;
    }

    /**
     * Check Service Machine Master for Receive Serial
     * @param serialInfo NLXC042001SerialInfo
     * @return NLXC042001SerialInfo
     */
    public static NLXC042001SerialInfo chkSvcMachMstrForRcvSerial(NLXC042001SerialInfo serialInfo) {

        /*************************************************************
         * 1. Check Parameter
         ************************************************************/
        String errMsgId = chkCommonParam(serialInfo);

        if (ZYPCommonFunc.hasValue(errMsgId)) {

            serialInfo.setErrMsgId(errMsgId);
            return serialInfo;
        }

        errMsgId = chkRcvParam(serialInfo);

        if (ZYPCommonFunc.hasValue(errMsgId)) {

            serialInfo.setErrMsgId(errMsgId);
            return serialInfo;
        }

        if (!ZYPCommonFunc.hasValue(serialInfo.getDupChkCd())) {

            serialInfo.setDupChkCd(DUP_CHK_SERIAL);
        }

        /*************************************************************
         * 2. Get Service Machine Master
         ************************************************************/
        List<Map<String, Object>> svcMachMstrList = getSvcMachMstrList(serialInfo, true);

        if (svcMachMstrList == null || svcMachMstrList.isEmpty()) {

            svcMachMstrList = getSvcMachMstrList(serialInfo, false);
        }

        /*************************************************************
         * 3. Specify Service Machine Master
         ************************************************************/
        Map<String, Object> svcMachMstrMap = new HashMap<String, Object>();

        // QC#21528, 22178 Modify
        String skipConfConst = ZYPCodeDataUtil.getVarCharConstValue(NLXC042001_SKIP_CONF_CHK_ORD, serialInfo.getGlblCmpyCd());
        List<String> skipConfigCheckOrdTp = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(skipConfConst)) {
            skipConfigCheckOrdTp = Arrays.asList(skipConfConst.split(","));
        }

        if (svcMachMstrList != null && !svcMachMstrList.isEmpty()) {

            if (svcMachMstrList.size() == 1) {

                svcMachMstrMap = svcMachMstrList.get(0);

            } else {

                // Get Active Machine Master
                List<Map<String, Object>> actvSvcMachMstrList = getActiveMachMstrList(svcMachMstrList);

                if (actvSvcMachMstrList != null && !actvSvcMachMstrList.isEmpty()) {

                    if (actvSvcMachMstrList.size() > 1) {

                        serialInfo.setErrMsgId(NLZM2436E);
                        return serialInfo;
                    }

                    svcMachMstrMap = actvSvcMachMstrList.get(0);

                // All Terminate Machine Master
                } else {

                    // Get Machine Master (not allocated by other order)
                    List<Map<String, Object>> svcMachMstrListNotAllocOthOrd = getMachMstrListNotAllocOthOrd(svcMachMstrList, serialInfo.getTrxHdrNum());

                    if (svcMachMstrListNotAllocOthOrd != null && !svcMachMstrListNotAllocOthOrd.isEmpty()) {

                        if (svcMachMstrListNotAllocOthOrd.size() == 1) {

                            svcMachMstrMap = svcMachMstrListNotAllocOthOrd.get(0);

                        } else {

                            svcMachMstrMap = svcMachMstrListNotAllocOthOrd.get(svcMachMstrListNotAllocOthOrd.size() - 1);
                        }

                    } else {

                        svcMachMstrMap = null;
                    }
                }
            }

            // Allocation Check
            if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

                String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
                String svcMachMaintAvalFlg = (String) svcMachMstrMap.get("SVC_MACH_MAINT_AVAL_FLG");

                if (ZYPCommonFunc.hasValue(trxHdrNum) && !serialInfo.getTrxHdrNum().equals(trxHdrNum)) {

                    // QC#19642 MOD START
                    if (!checkSubConTrxHdrNum(serialInfo, trxHdrNum)) {

                        serialInfo.setErrMsgId(NLZM2317E);
                        return serialInfo;
                    }
                    // QC#19642 MOD END

                } else if (!ZYPCommonFunc.hasValue(trxHdrNum) && !ZYPConstant.FLG_ON_Y.equals(svcMachMaintAvalFlg)) {

                    serialInfo.setErrMsgId(NLZM2317E);
                    return serialInfo;
                }
            }

            // Config Check
            if (!skipConfigCheckOrdTp.contains(serialInfo.getSceOrdTpCd())) {
                if (ZYPCommonFunc.hasValue(serialInfo.getOrdSvcConfigMstrPk())) {

                    if (svcMachMstrMap == null || svcMachMstrMap.isEmpty()) {

                        serialInfo.setErrMsgId(NLZM2319E);
                        return serialInfo;

                    } else {

                        BigDecimal svcConfigMstrPk = (BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK");

                        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {

                            serialInfo.setErrMsgId(NLZM2319E);
                            return serialInfo;

                        } else if (!serialInfo.getOrdSvcConfigMstrPk().equals(svcConfigMstrPk)) {

                            serialInfo.setErrMsgId(NLZM2324E);
                            return serialInfo;
                        }
                    }
                } else {

                    if (ZYPCommonFunc.hasValue((BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK"))) {

                        serialInfo.setErrMsgId(NLZM2324E);
                        return serialInfo;
                    }
                }
            }

        } else {

            if (!skipConfigCheckOrdTp.contains(serialInfo.getSceOrdTpCd())) {
                if (ZYPCommonFunc.hasValue(serialInfo.getOrdSvcConfigMstrPk())) {

                    serialInfo.setErrMsgId(NLZM2319E);
                    return serialInfo;
                }
            }

            svcMachMstrMap = null;
        }

        /*********************************************************
         * 4. Duplicate Check (When not exists Machine Master)
         ********************************************************/
        if (svcMachMstrMap == null || svcMachMstrMap.isEmpty()) {

            if (DUP_CHK_SERIAL.equals(serialInfo.getDupChkCd()) || DUP_CHK_MDL.equals(serialInfo.getDupChkCd())) {

                svcMachMstrList.clear();

                if (DUP_CHK_SERIAL.equals(serialInfo.getDupChkCd())) {

                    // Check Different MDSE
                    svcMachMstrList = getDefMdseSvcMachMstrList(serialInfo);

                } else if (DUP_CHK_MDL.equals(serialInfo.getDupChkCd())) {

                    // Check Different MDSE
                    svcMachMstrList = getDefMdseSvcMachMstrListForMdlChk(serialInfo);
                }

                if (svcMachMstrList != null && !svcMachMstrList.isEmpty()) {

                    // Get Active Machine Master
                    List<Map<String, Object>> actvSvcMachMstrList = getActiveMachMstrList(svcMachMstrList);

                    if (actvSvcMachMstrList != null && !actvSvcMachMstrList.isEmpty()) {

                        boolean dupErr = true;

                        if (Arrays.asList(getRcvSceOrdTpDupOk()).contains(serialInfo.getSceOrdTpCd())) {

                            for (Map<String, Object> actvSvcMachMstrMap : actvSvcMachMstrList) {

                                String trxHdrNum = (String) actvSvcMachMstrMap.get("TRX_HDR_NUM");

                                if (ZYPCommonFunc.hasValue(trxHdrNum) && trxHdrNum.equals(serialInfo.getTrxHdrNum())) {

                                    dupErr = false;
                                    svcMachMstrMap = actvSvcMachMstrMap;
                                    break;
                                }
                            }
                        }

                        if (dupErr) {

                            serialInfo.setErrMsgId(NLZM2450E);
                            return serialInfo;
                        }
                    }

                    if (svcMachMstrMap == null || svcMachMstrMap.isEmpty()) {

                        // Get Machine Master (not allocated by other order)
                        List<Map<String, Object>> svcMachMstrListNotAllocOthOrd = getMachMstrListNotAllocOthOrd(svcMachMstrList, serialInfo.getTrxHdrNum());

                        if (svcMachMstrListNotAllocOthOrd != null && !svcMachMstrListNotAllocOthOrd.isEmpty()) {

                            if (svcMachMstrListNotAllocOthOrd.size() == 1) {

                                svcMachMstrMap = svcMachMstrListNotAllocOthOrd.get(0);

                            } else {

                                svcMachMstrMap = svcMachMstrListNotAllocOthOrd.get(svcMachMstrListNotAllocOthOrd.size() - 1);
                            }

                        } else {

                            serialInfo.setErrMsgId(NLZM2317E);
                            return serialInfo;
                        }
                    }
                }
            }
        }

        /*********************************************************
         * 5. Specify & Execute Event
         ********************************************************/
        // New Creation
        if (Arrays.asList(getRcvSceOrdTpCrat()).contains(serialInfo.getSceOrdTpCd())) {

            serialInfo = chkRcvSvcMachMstrForCrat(serialInfo, svcMachMstrMap);

        // Transfer
        } else if (Arrays.asList(getRcvSceOrdTpTrnsf()).contains(serialInfo.getSceOrdTpCd())) {

            serialInfo = chkRcvSvcMachMstrForTrnsf(serialInfo, svcMachMstrMap);

        // Return
        } else if (Arrays.asList(getRcvSceOrdTpRtrn()).contains(serialInfo.getSceOrdTpCd())) {

            serialInfo = chkRcvSvcMachMstrForRtrn(serialInfo, svcMachMstrMap);

        // Back
        } else if (Arrays.asList(getRcvSceOrdTpBk()).contains(serialInfo.getSceOrdTpCd())) {

            serialInfo = chkRcvSvcMachMstrForBk(serialInfo, svcMachMstrMap);

        } else {

            serialInfo.setErrMsgId(NLZM2439E);
        }

        return serialInfo;
    }

    /**
     * Check Common Parameter
     * @param serialInfo NLXC042001SerialInfo
     * @return String
     */
    private static String chkCommonParam(NLXC042001SerialInfo serialInfo) {

        if (!ZYPCommonFunc.hasValue(serialInfo.getGlblCmpyCd())) {

            return NLZM2259E;

        } else if (!ZYPCommonFunc.hasValue(serialInfo.getSerNum())) {

            return NLZM2091E;

        } else if (!ZYPCommonFunc.hasValue(serialInfo.getMdseCd())) {

            return NLZM2260E;

        } else if (!ZYPCommonFunc.hasValue(serialInfo.getSceOrdTpCd())) {

            return NLZM2146E;

        } else if (!ZYPCommonFunc.hasValue(serialInfo.getTrxHdrNum())) {

            return NLZM2093E;

        } else if (!ZYPCommonFunc.hasValue(serialInfo.getShipFromLocCd())) {

            return NLZM2437E;

        } else if (ZYPCommonFunc.hasValue(serialInfo.getDupChkCd()) && !Arrays.asList(getDupChkList()).contains(serialInfo.getDupChkCd())) {

            return NLZM2438E;
        }

        return null;
    }

    /**
     * Check Ship Parameter
     * @param serialInfo NLXC042001SerialInfo
     * @return String
     */
    private static String chkShipParam(NLXC042001SerialInfo serialInfo) {

        if (!ZYPCommonFunc.hasValue(serialInfo.getSoNum())) {

            return NLZM0027E;
        }

        return null;
    }

    /**
     * Check Receive Parameter
     * @param serialInfo NLXC042001SerialInfo
     * @return String
     */
    private static String chkRcvParam(NLXC042001SerialInfo serialInfo) {

        if (!ZYPCommonFunc.hasValue(serialInfo.getRwsNum())) {

            return NLZM2133E;

        } else if (!ZYPCommonFunc.hasValue(serialInfo.getRcvRtlWhCd())) {

            return NLZM2440E;
        }

        return null;
    }

    /**
     * Get Service Machine Master List
     * @param serialInfo NLXC042001SerialInfo
     * @param allocFlg boolean
     * @return List<Map<String, Object>>
     */
    private static List<Map<String, Object>> getSvcMachMstrList(NLXC042001SerialInfo serialInfo, boolean allocFlg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", serialInfo.getGlblCmpyCd());
        queryParam.put("serNum", serialInfo.getSerNum());
        queryParam.put("mdseCd", serialInfo.getMdseCd());

        if (allocFlg) {

            queryParam.put("trxHdrNum", serialInfo.getTrxHdrNum());
        }

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcMachMstrList", queryParam);
    }

    /**
     * Get Service Machine Master Map
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrList List<Map<String, Object>>
     * @return Map<String, Object>
     */
    private static Map<String, Object> getSvcMachMstrMap(NLXC042001SerialInfo serialInfo, List<Map<String, Object>> svcMachMstrList) {

        Map<String, Object> rtrnMap = new HashMap<String, Object>();

        if (svcMachMstrList != null && !svcMachMstrList.isEmpty()) {

            if (svcMachMstrList.size() == 1) {

                rtrnMap.put("svcMachMstrMap", svcMachMstrList.get(0));

            } else {

                // Get Active Machine Master
                List<Map<String, Object>> actvSvcMachMstrList = getActiveMachMstrList(svcMachMstrList);

                if (actvSvcMachMstrList != null && !actvSvcMachMstrList.isEmpty()) {

                    if (actvSvcMachMstrList.size() > 1) {

                        rtrnMap.put("errMsg", NLZM2436E);
                        return rtrnMap;
                    }

                    rtrnMap.put("svcMachMstrMap", svcMachMstrList.get(0));

                // All Terminate Machine Master
                } else {

                    // Get Machine Master (not allocated by other order)
                    List<Map<String, Object>> svcMachMstrListNotAllocOthOrd = getMachMstrListNotAllocOthOrd(svcMachMstrList, serialInfo.getTrxHdrNum());

                    if (svcMachMstrListNotAllocOthOrd != null && !svcMachMstrListNotAllocOthOrd.isEmpty()) {

                        if (svcMachMstrListNotAllocOthOrd.size() == 1) {

                            rtrnMap.put("svcMachMstrMap", svcMachMstrListNotAllocOthOrd.get(0));

                        } else {

                            rtrnMap.put("svcMachMstrMap", svcMachMstrListNotAllocOthOrd.get(svcMachMstrListNotAllocOthOrd.size() - 1));
                        }

                    } else {

                        rtrnMap.put("svcMachMstrMap", null);
                    }
                }
            }

            Map<String, Object> svcMachMstrMap = (Map<String, Object>) rtrnMap.get("svcMachMstrMap");

            // Allocation Check
            if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

                String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
                String svcMachMaintAvalFlg = (String) svcMachMstrMap.get("SVC_MACH_MAINT_AVAL_FLG");

                if (ZYPCommonFunc.hasValue(trxHdrNum) && !serialInfo.getTrxHdrNum().equals(trxHdrNum)) {

                    rtrnMap.put("errMsg", NLZM2317E);
                    return rtrnMap;

                } else if (!ZYPCommonFunc.hasValue(trxHdrNum) && !ZYPConstant.FLG_ON_Y.equals(svcMachMaintAvalFlg)) {

                    rtrnMap.put("errMsg", NLZM2317E);
                    return rtrnMap;
                }
            }

            // Config Check
            // QC#53385 Add
            if (!SCE_ORD_TP.CONFIG_CHANGE.equals(serialInfo.getSceOrdTpCd())) {
                if (ZYPCommonFunc.hasValue(serialInfo.getOrdSvcConfigMstrPk())) {
    
                    if (svcMachMstrMap == null || svcMachMstrMap.isEmpty()) {
    
                        rtrnMap.put("errMsg", NLZM2319E);
                        return rtrnMap;
    
                    } else {
    
                        BigDecimal svcConfigMstrPk = (BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK");
                        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
                            rtrnMap.put("errMsg", NLZM2319E);
                            return rtrnMap;
    
                        } else if (!serialInfo.getOrdSvcConfigMstrPk().equals(svcConfigMstrPk)) {
    
                            rtrnMap.put("errMsg", NLZM2324E);
                            return rtrnMap;
                        }
                    }
                } else {
    
                    if (ZYPCommonFunc.hasValue((BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK"))) {
    
                        rtrnMap.put("errMsg", NLZM2324E);
                        return rtrnMap;
                    }
                }
            }
            // QC#20008 Add
            // Stock Status Check
            if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

                if (ZYPCommonFunc.hasValue(serialInfo.getStkStsCd())) {
                    String stkStsCd = (String) svcMachMstrMap.get("STK_STS_CD");

                    if (!serialInfo.getStkStsCd().equals(stkStsCd)) {
                        rtrnMap.put("errMsg", NLZM2414E);
                        return rtrnMap;
                    }
                }
            }

        } else {
            // QC#53385 Add
            if (!SCE_ORD_TP.CONFIG_CHANGE.equals(serialInfo.getSceOrdTpCd())) {

                if (ZYPCommonFunc.hasValue(serialInfo.getOrdSvcConfigMstrPk())) {

                    rtrnMap.put("errMsg", NLZM2319E);
                    return rtrnMap;
                }
            }

            rtrnMap.put("svcMachMstrMap", null);
        }

        return rtrnMap;
    }

    /**
     * Check Duplicated Machine Master
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrList List<Map<String, Object>>
     * @return Map<String, Object>
     */
    private static Map<String, Object> chkDupMachMstr(NLXC042001SerialInfo serialInfo, List<Map<String, Object>> svcMachMstrList) {

        Map<String, Object> rtrnMap = new HashMap<String, Object>();

        if (DUP_CHK_SERIAL.equals(serialInfo.getDupChkCd()) || DUP_CHK_MDL.equals(serialInfo.getDupChkCd())) {

            svcMachMstrList.clear();

            if (DUP_CHK_SERIAL.equals(serialInfo.getDupChkCd())) {

                // Check Different MDSE
                svcMachMstrList = getDefMdseSvcMachMstrList(serialInfo);

            } else if (DUP_CHK_MDL.equals(serialInfo.getDupChkCd())) {

                // Check Different MDSE
                svcMachMstrList = getDefMdseSvcMachMstrListForMdlChk(serialInfo);
            }

            if (svcMachMstrList != null && !svcMachMstrList.isEmpty()) {

                // Get Active Machine Master
                List<Map<String, Object>> actvSvcMachMstrList = getActiveMachMstrList(svcMachMstrList);

                if (actvSvcMachMstrList != null && !actvSvcMachMstrList.isEmpty()) {

                    rtrnMap.put("errMsg", NLZM2450E);
                    return rtrnMap;
                }

                // Get Machine Master (not allocated by other order)
                List<Map<String, Object>> svcMachMstrListNotAllocOthOrd = getMachMstrListNotAllocOthOrd(svcMachMstrList, serialInfo.getTrxHdrNum());

                if (svcMachMstrListNotAllocOthOrd != null && !svcMachMstrListNotAllocOthOrd.isEmpty()) {

                    if (svcMachMstrListNotAllocOthOrd.size() == 1) {

                        rtrnMap.put("svcMachMstrMap", svcMachMstrListNotAllocOthOrd.get(0));
                        return rtrnMap;

                    } else {

                        rtrnMap.put("svcMachMstrMap", svcMachMstrListNotAllocOthOrd.get(svcMachMstrListNotAllocOthOrd.size() - 1));
                        return rtrnMap;
                    }

                } else {

                    rtrnMap.put("errMsg", NLZM2317E);
                    return rtrnMap;
                }
            }
        }

        return rtrnMap;
    }

    /**
     * Get Different Mdse Service Machine Master List
     * @param serialInfo NLXC042001SerialInfo
     * @return List<Map<String, Object>>
     */
    private static List<Map<String, Object>> getDefMdseSvcMachMstrList(NLXC042001SerialInfo serialInfo) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", serialInfo.getGlblCmpyCd());
        queryParam.put("serNum", serialInfo.getSerNum());
        queryParam.put("mdseCd", serialInfo.getMdseCd());

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDefMdseSvcMachMstrList", queryParam);
    }

    /**
     * Get Different Mdse Service Machine Master List (For Model)
     * @param serialInfo NLXC042001SerialInfo
     * @return List<Map<String, Object>>
     */
    private static List<Map<String, Object>> getDefMdseSvcMachMstrListForMdlChk(NLXC042001SerialInfo serialInfo) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", serialInfo.getGlblCmpyCd());
        queryParam.put("serNum", serialInfo.getSerNum());
        queryParam.put("mdseCd", serialInfo.getMdseCd());
        queryParam.put("mdlId", serialInfo.getMdlId());

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDefMdseSvcMachMstrListForMdlChk", queryParam);
    }

    /**
     * Check Install Base Control Mdse for Receving
     * @param serialInfo NLXC042001SerialInfo
     * @return boolean
     */
    private static boolean isRcvInstlBaseCtrlMdse(NLXC042001SerialInfo serialInfo) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", serialInfo.getGlblCmpyCd());
        queryParam.put("mdseCd", serialInfo.getMdseCd());

        Map<String, Object> mdseMap = (Map<String, Object>) ssmBatchClient.queryObject("getMdseInfo", queryParam);

        if (mdseMap != null) {

            String rcvSerTakeFlg = (String) mdseMap.get("RCV_SER_TAKE_FLG");
            String instlBaseCtrlFlg = (String) mdseMap.get("INSTL_BASE_CTRL_FLG");

            if (ZYPConstant.FLG_ON_Y.equals(rcvSerTakeFlg) && ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Get Active Service Machine Master List
     * @param svcMachMstrList List<Map<String, Object>>
     * @return List<Map<String, Object>>
     */
    private static List<Map<String, Object>> getActiveMachMstrList(List<Map<String, Object>> svcMachMstrList) {

        List<Map<String, Object>> activeSvcMachMstrList = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> svcMachMstrMap : svcMachMstrList) {

            if (!SVC_MACH_MSTR_STS.TERMINATED.equals((String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD"))) {

                activeSvcMachMstrList.add(svcMachMstrMap);
            }
        }

        return activeSvcMachMstrList;
    }

    /**
     * Get Machine Master List (Not Allocated by other order)
     * @param svcMachMstrList List<Map<String, Object>>
     * @param trxHdrNum String
     * @return List<Map<String, Object>>
     */
    private static List<Map<String, Object>> getMachMstrListNotAllocOthOrd(List<Map<String, Object>> svcMachMstrList, String trxHdrNum) {

        List<Map<String, Object>> svcMachMstrListNotAllocOthOrd = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> svcMachMstrMap : svcMachMstrList) {

            String svcMachMstrTrxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
            String svcMachMaintAvalFlg = (String) svcMachMstrMap.get("SVC_MACH_MAINT_AVAL_FLG");

            if (!ZYPCommonFunc.hasValue(svcMachMstrTrxHdrNum) && ZYPConstant.FLG_ON_Y.equals(svcMachMaintAvalFlg)) {

                svcMachMstrListNotAllocOthOrd.add(svcMachMstrMap);

            } else if (trxHdrNum.equals(svcMachMstrTrxHdrNum)) {

                svcMachMstrListNotAllocOthOrd.clear();
                svcMachMstrListNotAllocOthOrd.add(svcMachMstrMap);
                return svcMachMstrListNotAllocOthOrd;
            }
        }

        return svcMachMstrListNotAllocOthOrd;
    }

    /**
     * Set Service Machine Master Information
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrMap Map<String, Object>
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo setSvcMachMstrInfo(NLXC042001SerialInfo serialInfo, Map<String, Object> svcMachMstrMap) {

        serialInfo.setSvcMachMstrPk((BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK"));
        serialInfo.setSerAllocOrdNum((String) svcMachMstrMap.get("TRX_HDR_NUM"));
        serialInfo.setSvcConfigMstrPk((BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK"));
        serialInfo.setSvcMachMstrStsCd((String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD"));
        serialInfo.setCurLocNum((String) svcMachMstrMap.get("CUR_LOC_NUM"));

        return serialInfo;
    }

    /**
     * Check SO Serial
     * @param serialInfo NLXC042001SerialInfo
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo chkSoSerial(NLXC042001SerialInfo serialInfo) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", serialInfo.getGlblCmpyCd());
        queryParam.put("soNum", serialInfo.getSoNum());
        queryParam.put("mdseCd", serialInfo.getMdseCd());
        queryParam.put("whPickFlg", ZYPConstant.FLG_ON_Y);

        List<String> whPickSerialList = (List<String>) ssmBatchClient.queryObjectList("getSoSerialList", queryParam);

        if (whPickSerialList != null && !whPickSerialList.isEmpty()) {

            if (!whPickSerialList.contains(serialInfo.getSerNum())) {

                serialInfo.setErrMsgId(NLBM2443W);
                serialInfo.setSerErrStsCd(NLZC302001Constant.NOT_SCHD_ERR);
            }

            return serialInfo;
        }

        queryParam.remove("whPickFlg");
        queryParam.put("ordAsgFlg", ZYPConstant.FLG_ON_Y);

        List<String> ordAdgSerialList = (List<String>) ssmBatchClient.queryObjectList("getSoSerialList", queryParam);

        if (ordAdgSerialList != null && !ordAdgSerialList.isEmpty()) {

            if (!ordAdgSerialList.contains(serialInfo.getSerNum())) {

                serialInfo.setErrMsgId(NLBM2444W);
                serialInfo.setSerErrStsCd(NLZC302001Constant.NOT_SCHD_ERR);
            }
        }

        return serialInfo;
    }

    /**
     * Check RWS Serial
     * @param serialInfo NLXC042001SerialInfo
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo chkRwsSerial(NLXC042001SerialInfo serialInfo) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", serialInfo.getGlblCmpyCd());
        queryParam.put("rwsNum", serialInfo.getRwsNum());
        queryParam.put("mdseCd", serialInfo.getMdseCd());

        List<String> rwsSerialList = (List<String>) ssmBatchClient.queryObjectList("getRwsSerialList", queryParam);

        if (rwsSerialList != null && !rwsSerialList.isEmpty()) {

            if (!rwsSerialList.contains(serialInfo.getSerNum())) {

                // QC#17919 Add.
                if (checkRcvSerChkCtrl(serialInfo)) {

                    serialInfo.setErrMsgId(NLBM1359E);  // Error.
                    serialInfo.setSerErrStsCd(NLZC302001Constant.NOT_SCHD_ERR);

                } else {

                    serialInfo.setErrMsgId(NLBM2441W);  // Warning.
                    serialInfo.setSerErrStsCd(NLZC302001Constant.NOT_SCHD_ERR);

                }

            }
        }

        return serialInfo;
    }

    /**
     * Check Service Machine Master for CPO (Ship)
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrMap Map<String, Object>
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo chkShipSvcMachMstrForCpo(NLXC042001SerialInfo serialInfo, Map<String, Object> svcMachMstrMap) {

        List<String> modeCdList = new ArrayList<String>();
        boolean execSoSerChk = true;

        if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

            // Set Machine Master Info
            serialInfo = setSvcMachMstrInfo(serialInfo, svcMachMstrMap);

            String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 START */
            // String rtlWhCd = (String) svcMachMstrMap.get("RTL_WH_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 END */
            String locStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_LOC_STS_CD");
            String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
            String mdseCd = (String) svcMachMstrMap.get("MDSE_CD");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            String curLocNum = (String) svcMachMstrMap.get("CUR_LOC_NUM");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

            if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                    serialInfo.setErrMsgId(NLCM0151E);
                    return serialInfo;
                }
                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                if (!serialInfo.getMdseCd().equals(mdseCd)) {

                    modeCdList.add(ProcessMode.ITEM_CHANGE.code);

                } else {

                    modeCdList.add(ProcessMode.UPDATE_WAREHOUSE.code);
                }

                if (isRcvInstlBaseCtrlMdse(serialInfo)) {

                    serialInfo.setErrMsgId(NLZM2447W);
                    execSoSerChk = false;
                }

            } else {

                // At Customer
                if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                    serialInfo.setErrMsgId(NLZM2318E);
                    return serialInfo;

                // In Transit (WH)
                } else if (LOC_STS.IN_TRANSIT_WH.equals(locStsCd)) {

                    serialInfo.setErrMsgId(NLZM2443E);
                    return serialInfo;

                // Located at different WH
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 START */
                // } else if (!serialInfo.getShipFromLocCd().equals(rtlWhCd)) {
                } else if (!serialInfo.getShipFromLocCd().equals(curLocNum)) {
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 END */

                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                    if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                        serialInfo.setErrMsgId(NLZM2442E);
                        return serialInfo;
                    }
                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                    // Warning
                    serialInfo.setErrMsgId(NLZM2445W);
                    execSoSerChk = false;
                }
            }

            if (!ZYPCommonFunc.hasValue(trxHdrNum)) {

                modeCdList.add(ProcessMode.ALLOCATION_ON.code);
            }

            serialInfo.setModeCdList(modeCdList);

        } else {

            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                serialInfo.setErrMsgId(NLCM0151E);
                return serialInfo;
                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

            } else if (isRcvInstlBaseCtrlMdse(serialInfo)) {

                serialInfo.setErrMsgId(NLZM2447W);
                execSoSerChk = false;
            }
        }

        // Check SO Serial
        if (execSoSerChk) {

            serialInfo = chkSoSerial(serialInfo);
        }

        return serialInfo;
    }

    /**
     * Check Service Machine Master for Tech Request (Ship)
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrMap Map<String, Object>
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo chkShipSvcMachMstrForTr(NLXC042001SerialInfo serialInfo, Map<String, Object> svcMachMstrMap) {

        List<String> modeCdList = new ArrayList<String>();
        boolean execSoSerChk = true;

        if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

            // Set Machine Master Info
            serialInfo = setSvcMachMstrInfo(serialInfo, svcMachMstrMap);

            String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 START */
            // String rtlWhCd = (String) svcMachMstrMap.get("RTL_WH_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 END */
            String locStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_LOC_STS_CD");
            String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
            String mdseCd = (String) svcMachMstrMap.get("MDSE_CD");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            String curLocNum = (String) svcMachMstrMap.get("CUR_LOC_NUM");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */


            if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                    serialInfo.setErrMsgId(NLCM0151E);
                    return serialInfo;
                }
                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                if (!serialInfo.getMdseCd().equals(mdseCd)) {

                    modeCdList.add(ProcessMode.ITEM_CHANGE.code);

                } else {

                    modeCdList.add(ProcessMode.UPDATE_WAREHOUSE.code);
                }

                if (isRcvInstlBaseCtrlMdse(serialInfo)) {

                    serialInfo.setErrMsgId(NLZM2447W);
                    execSoSerChk = false;
                }

            } else {

                // At Customer
                if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                    serialInfo.setErrMsgId(NLZM2318E);
                    return serialInfo;

                // In Transit (WH)
                } else if (LOC_STS.IN_TRANSIT_WH.equals(locStsCd)) {

                    serialInfo.setErrMsgId(NLZM2443E);
                    return serialInfo;

                // Located at different WH
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 START */
                // } else if (!serialInfo.getShipFromLocCd().equals(rtlWhCd)) {
                } else if (!serialInfo.getShipFromLocCd().equals(curLocNum)) {
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 END */

                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                    if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                        serialInfo.setErrMsgId(NLZM2442E);
                        return serialInfo;
                    }
                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                    // Warning
                    serialInfo.setErrMsgId(NLZM2445W);
                    execSoSerChk = false;
                }
            }

            if (!ZYPCommonFunc.hasValue(trxHdrNum)) {

                modeCdList.add(ProcessMode.ALLOCATION_ON.code);
            }

        } else {

            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                serialInfo.setErrMsgId(NLCM0151E);
                return serialInfo;
                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

            } else if (isRcvInstlBaseCtrlMdse(serialInfo)) {

                serialInfo.setErrMsgId(NLZM2447W);
                execSoSerChk = false;
            }

            modeCdList.add(ProcessMode.INSERT_WAREHOUSE.code);
            modeCdList.add(ProcessMode.ALLOCATION_ON.code);
        }

        modeCdList.add(ProcessMode.TRANSFER_OUT.code);

        // Check SO Serial
        if (execSoSerChk) {

            serialInfo = chkSoSerial(serialInfo);
        }

        serialInfo.setModeCdList(modeCdList);
        return serialInfo;
    }

    /**
     * Check Service Machine Master for Update (Ship)
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrMap Map<String, Object>
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo chkShipSvcMachMstrForUpd(NLXC042001SerialInfo serialInfo, Map<String, Object> svcMachMstrMap) {

        List<String> modeCdList = new ArrayList<String>();
        boolean execSoSerChk = true;

        if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

            // Set Machine Master Info
            serialInfo = setSvcMachMstrInfo(serialInfo, svcMachMstrMap);

            String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 START */
            // String rtlWhCd = (String) svcMachMstrMap.get("RTL_WH_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 END */
            String locStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_LOC_STS_CD");
            String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
            String mdseCd = (String) svcMachMstrMap.get("MDSE_CD");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            String curLocNum = (String) svcMachMstrMap.get("CUR_LOC_NUM");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */


            if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                    serialInfo.setErrMsgId(NLCM0151E);
                    return serialInfo;
                }
                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                if (!serialInfo.getMdseCd().equals(mdseCd)) {

                    modeCdList.add(ProcessMode.ITEM_CHANGE.code);

                } else {

                    modeCdList.add(ProcessMode.UPDATE_WAREHOUSE.code);
                }

                if (isRcvInstlBaseCtrlMdse(serialInfo)) {

                    serialInfo.setErrMsgId(NLZM2447W);
                    execSoSerChk = false;
                }

            } else {

                // At Customer
                if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                    serialInfo.setErrMsgId(NLZM2318E);
                    return serialInfo;

                // In Transit (WH)
                } else if (LOC_STS.IN_TRANSIT_WH.equals(locStsCd)) {

                    serialInfo.setErrMsgId(NLZM2443E);
                    return serialInfo;

                // Located at different WH
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 START */
                // } else if (!serialInfo.getShipFromLocCd().equals(rtlWhCd)) {
                } else if (!serialInfo.getShipFromLocCd().equals(curLocNum)) {
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 END */

                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                    if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                        serialInfo.setErrMsgId(NLZM2442E);
                        return serialInfo;
                    }
                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                    // Warning
                    serialInfo.setErrMsgId(NLZM2445W);
                    execSoSerChk = false;
                }
            }

            if (!ZYPCommonFunc.hasValue(trxHdrNum)) {

                modeCdList.add(ProcessMode.ALLOCATION_ON.code);
            }

        } else {

            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                serialInfo.setErrMsgId(NLCM0151E);
                return serialInfo;
                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

            } else if (isRcvInstlBaseCtrlMdse(serialInfo)) {

                serialInfo.setErrMsgId(NLZM2447W);
                execSoSerChk = false;
            }

            modeCdList.add(ProcessMode.INSERT_WAREHOUSE.code);
            modeCdList.add(ProcessMode.ALLOCATION_ON.code);
        }

        modeCdList.add(ProcessMode.UPDATE_INVENTORY.code);

        // Check SO Serial
        if (execSoSerChk) {

            serialInfo = chkSoSerial(serialInfo);
        }

        serialInfo.setModeCdList(modeCdList);
        return serialInfo;
    }

    /**
     * Check Service Machine Master for Completion (Ship)
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrMap Map<String, Object>
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo chkShipSvcMachMstrForComp(NLXC042001SerialInfo serialInfo, Map<String, Object> svcMachMstrMap) {

        List<String> modeCdList = new ArrayList<String>();
        boolean execSoSerChk = true;

        if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

            // Set Machine Master Info
            serialInfo = setSvcMachMstrInfo(serialInfo, svcMachMstrMap);

            String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 START */
            // String rtlWhCd = (String) svcMachMstrMap.get("RTL_WH_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 END */
            String locStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_LOC_STS_CD");
            String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
            String mdseCd = (String) svcMachMstrMap.get("MDSE_CD");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            String curLocNum = (String) svcMachMstrMap.get("CUR_LOC_NUM");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

            if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                    serialInfo.setErrMsgId(NLCM0151E);
                    return serialInfo;
                }
                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                if (!serialInfo.getMdseCd().equals(mdseCd)) {

                    modeCdList.add(ProcessMode.ITEM_CHANGE.code);

                } else {

                    modeCdList.add(ProcessMode.UPDATE_WAREHOUSE.code);
                }

                if (isRcvInstlBaseCtrlMdse(serialInfo)) {

                    serialInfo.setErrMsgId(NLZM2447W);
                    execSoSerChk = false;
                }

            } else {

                // At Customer
                if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                    serialInfo.setErrMsgId(NLZM2318E);
                    return serialInfo;

                // In Transit (WH)
                } else if (LOC_STS.IN_TRANSIT_WH.equals(locStsCd)) {

                    serialInfo.setErrMsgId(NLZM2443E);
                    return serialInfo;

                // Located at different WH
                    /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 START */
                // } else if (!serialInfo.getShipFromLocCd().equals(rtlWhCd)) {
                } else if (!serialInfo.getShipFromLocCd().equals(curLocNum)) {
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 END */

                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                    if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                        serialInfo.setErrMsgId(NLZM2442E);
                        return serialInfo;
                    }
                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                    // Warning
                    serialInfo.setErrMsgId(NLZM2445W);
                    execSoSerChk = false;
                }
            }

            if (ZYPCommonFunc.hasValue(trxHdrNum)) {

                modeCdList.add(ProcessMode.ALLOCATION_OFF.code);
            }

            modeCdList.add(ProcessMode.UPDATE_INVENTORY.code);

        } else {

            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                serialInfo.setErrMsgId(NLCM0151E);
                return serialInfo;
                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

            } else if (isRcvInstlBaseCtrlMdse(serialInfo)) {

                serialInfo.setErrMsgId(NLZM2447W);
                execSoSerChk = false;
            }

            modeCdList.add(ProcessMode.INSERT_WAREHOUSE.code);
        }

        // Check SO Serial
        if (execSoSerChk) {

            serialInfo = chkSoSerial(serialInfo);
        }

        serialInfo.setModeCdList(modeCdList);
        return serialInfo;
    }

    /**
     * Check Service Machine Master for Disposal (Ship)
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrMap Map<String, Object>
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo chkShipSvcMachMstrForDspl(NLXC042001SerialInfo serialInfo, Map<String, Object> svcMachMstrMap) {

        List<String> modeCdList = new ArrayList<String>();
        boolean execSoSerChk = true;

        if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

            // Set Machine Master Info
            serialInfo = setSvcMachMstrInfo(serialInfo, svcMachMstrMap);

            String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 START */
            // String rtlWhCd = (String) svcMachMstrMap.get("RTL_WH_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 END */
            String locStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_LOC_STS_CD");
            String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            String curLocNum = (String) svcMachMstrMap.get("CUR_LOC_NUM");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

            if (!SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                // At Customer
                if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                    serialInfo.setErrMsgId(NLZM2318E);
                    return serialInfo;

                // In Transit (WH)
                } else if (LOC_STS.IN_TRANSIT_WH.equals(locStsCd)) {

                    serialInfo.setErrMsgId(NLZM2443E);
                    return serialInfo;
                }

            } else {

                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                    serialInfo.setErrMsgId(NLCM0151E);
                    return serialInfo;
                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                } else if (isRcvInstlBaseCtrlMdse(serialInfo)) {

                    serialInfo.setErrMsgId(NLZM2447W);
                    execSoSerChk = false;
                }
            }

            if (ZYPCommonFunc.hasValue(trxHdrNum)) {

                modeCdList.add(ProcessMode.ALLOCATION_OFF.code);
            }


            if (!SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                // Located at different WH
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 START */
                // if (!serialInfo.getShipFromLocCd().equals(rtlWhCd)) {
                if (!serialInfo.getShipFromLocCd().equals(curLocNum)) {
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 END */

                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                    if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                        serialInfo.setErrMsgId(NLZM2442E);
                        return serialInfo;
                    }
                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                    // Warning
                    serialInfo.setErrMsgId(NLZM2445W);
                    execSoSerChk = false;
                }

                modeCdList.add(ProcessMode.DISPOSAL.code);
            }

            serialInfo.setModeCdList(modeCdList);

        } else {

            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                serialInfo.setErrMsgId(NLCM0151E);
                return serialInfo;
                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

            } else if (isRcvInstlBaseCtrlMdse(serialInfo)) {

                serialInfo.setErrMsgId(NLZM2447W);
                execSoSerChk = false;
            }
        }

        // Check SO Serial
        if (execSoSerChk) {

            serialInfo = chkSoSerial(serialInfo);
        }

        return serialInfo;
    }

    /**
     * Check Service Machine Master for New Creation (Receive)
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrMap Map<String, Object>
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo chkRcvSvcMachMstrForCrat(NLXC042001SerialInfo serialInfo, Map<String, Object> svcMachMstrMap) {

        List<String> modeCdList = new ArrayList<String>();
        boolean execRwsSerChk = true;

        if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

            // Set Machine Master Info
            serialInfo = setSvcMachMstrInfo(serialInfo, svcMachMstrMap);

            String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 START */
            // String rtlWhCd = (String) svcMachMstrMap.get("RTL_WH_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 END */
            String locStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_LOC_STS_CD");
            String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
            String mdseCd = (String) svcMachMstrMap.get("MDSE_CD");

            // Check Machine Master
            if (!SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                // At Customer
                if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                    serialInfo.setErrMsgId(NLZM2318E);
                    return serialInfo;

                // In Transit (WH)
                } else if (LOC_STS.IN_TRANSIT_WH.equals(locStsCd)) {

                    serialInfo.setErrMsgId(NLZM2443E);
                    return serialInfo;

                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                } else if (!Arrays.asList(getRcvSceOrdTpDupOk()).contains(serialInfo.getSceOrdTpCd())) {

                    serialInfo.setErrMsgId(NLZM2141E);
                    return serialInfo;

                } else {

                    if (!ZYPCommonFunc.hasValue(trxHdrNum)) {

                        serialInfo.setErrMsgId(NLZM2141E);
                        return serialInfo;

                    } else if (!trxHdrNum.equals(serialInfo.getTrxHdrNum())) {

                        if (!checkSubConTrxHdrNum(serialInfo, trxHdrNum)) {

                            serialInfo.setErrMsgId(NLZM2141E);
                            return serialInfo;
                        }

                    }
                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                  /* 07/07/2017 CSAI Y.Imazu Del QC#19730 START */
//                // Different Warehouse
//                } else if (!serialInfo.getRcvRtlWhCd().equals(rtlWhCd)) {
//
//                    // QC#19642 MOD START
//                    if (!checkSubConRtlWhCd(serialInfo, rtlWhCd)) {
//
//                        serialInfo.setErrMsgId(NLZM2442E);
//                        return serialInfo;
//                    }
//                    // QC#19642 MOD END
                      /* 07/07/2017 CSAI Y.Imazu Del QC#19730 END */
                }
            }

            // Set Machine Master Update API Mode
            if (ZYPCommonFunc.hasValue(trxHdrNum)) {

                modeCdList.add(ProcessMode.ALLOCATION_OFF.code);
            }

            if (!SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                /* 07/07/2017 CSAI Y.Imazu Del QC#19730 START */
                // if (Arrays.asList(getRcvSceOrdTpDupOk()).contains(serialInfo.getSceOrdTpCd())) {
                /* 07/07/2017 CSAI Y.Imazu Del QC#19730 END */

                if (!serialInfo.getMdseCd().equals(mdseCd)) {

                    modeCdList.add(ProcessMode.ITEM_CHANGE.code);

                } else {

                    modeCdList.add(ProcessMode.UPDATE_WAREHOUSE.code);
                }

                /* 07/07/2017 CSAI Y.Imazu Del QC#19730 START */
//                } else {
//
//                    modeCdList.add(ProcessMode.INSERT_WAREHOUSE.code);
//                }

//                // Warning
//                serialInfo.setErrMsgId(NLZM2444W);
//                serialInfo.setSerErrStsCd(NLZC302001Constant.DUP_ERR);
//                execRwsSerChk = false;
                /* 07/07/2017 CSAI Y.Imazu Del QC#19730 END */

            } else {

                if (!serialInfo.getMdseCd().equals(mdseCd)) {

                    modeCdList.add(ProcessMode.ITEM_CHANGE.code);

                } else {

                    modeCdList.add(ProcessMode.UPDATE_WAREHOUSE.code);
                }
            }

        } else {

            modeCdList.add(ProcessMode.INSERT_WAREHOUSE.code);
        }

        // Check RWS Serial
        if (execRwsSerChk) {

            serialInfo = chkRwsSerial(serialInfo);
        }

        serialInfo.setModeCdList(modeCdList);
        return serialInfo;
    }

    /**
     * Check Service Machine Master for Transfer (Receive)
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrMap Map<String, Object>
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo chkRcvSvcMachMstrForTrnsf(NLXC042001SerialInfo serialInfo, Map<String, Object> svcMachMstrMap) {

        List<String> modeCdList = new ArrayList<String>();
        boolean execRwsSerChk = true;

        if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

            // Set Machine Master Info
            serialInfo = setSvcMachMstrInfo(serialInfo, svcMachMstrMap);

            String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
            String rtlWhCd = (String) svcMachMstrMap.get("RTL_WH_CD");
            String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
            String mdseCd = (String) svcMachMstrMap.get("MDSE_CD");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            String locStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_LOC_STS_CD");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

            // Check Machine Master
            if (!SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                // At Customer
                if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                    serialInfo.setErrMsgId(NLZM2318E);
                    return serialInfo;
                }

                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                // Located at WH
                if (LOC_STS.DC_STOCK.equals(locStsCd) && ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                    if (!serialInfo.getRcvRtlWhCd().equals(rtlWhCd)) {

                        serialInfo.setErrMsgId(NLZM2442E);

                    } else {

                        serialInfo.setErrMsgId(NLZM2141E);
                    }

                    return serialInfo;
                }
                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            }

            // Set Machine Master Update API Mode
            if (ZYPCommonFunc.hasValue(trxHdrNum)) {

                modeCdList.add(ProcessMode.ALLOCATION_OFF.code);
            }

            if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                if (!serialInfo.getMdseCd().equals(mdseCd)) {

                    modeCdList.add(ProcessMode.ITEM_CHANGE.code);

                } else {

                    modeCdList.add(ProcessMode.UPDATE_WAREHOUSE.code);
                }

            } else {

                modeCdList.add(ProcessMode.TRANSFER_IN.code);

                // Located at different WH
                if (!serialInfo.getRcvRtlWhCd().equals(rtlWhCd)) {

                    // Warning
                    serialInfo.setErrMsgId(NLZM2445W);
                    serialInfo.setSerErrStsCd(NLZC302001Constant.DUP_ERR);
                    execRwsSerChk = false;
                }
            }

        } else {

            modeCdList.add(ProcessMode.INSERT_WAREHOUSE.code);
        }

        // Check RWS Serial
        if (execRwsSerChk) {

            serialInfo = chkRwsSerial(serialInfo);
        }

        serialInfo.setModeCdList(modeCdList);
        return serialInfo;
    }

    /**
     * Check Service Machine Master for Return (Receive)
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrMap Map<String, Object>
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo chkRcvSvcMachMstrForRtrn(NLXC042001SerialInfo serialInfo, Map<String, Object> svcMachMstrMap) {

        List<String> modeCdList = new ArrayList<String>();
        boolean execRwsSerChk = true;

        if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

            // Set Machine Master Info
            serialInfo = setSvcMachMstrInfo(serialInfo, svcMachMstrMap);

            String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
            String locStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_LOC_STS_CD");
            String rtlWhCd = (String) svcMachMstrMap.get("RTL_WH_CD");
            String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
            String mdseCd = (String) svcMachMstrMap.get("MDSE_CD");

            // Check Machine Master
            if (!SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                // At Customer
                if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                    // QC#16239 START
                    // // Located at order customer
                    // if (!serialInfo.getShipFromLocCd().equals(curLocNum)) {
                    //
                    //     serialInfo.setErrMsgId(NLZM2446E);
                    //     return serialInfo;
                    // }

                    // do nothing
                    // QC#16239 END

                // In Transit (WH)
                } else if (LOC_STS.IN_TRANSIT_WH.equals(locStsCd)) {

                    serialInfo.setErrMsgId(NLZM2443E);
                    return serialInfo;
                }
            }

            // Set Machine Master Update API Mode
            if (ZYPCommonFunc.hasValue(trxHdrNum)) {

                modeCdList.add(ProcessMode.ALLOCATION_OFF.code);
            }

            if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                if (!serialInfo.getMdseCd().equals(mdseCd)) {

                    modeCdList.add(ProcessMode.ITEM_CHANGE.code);

                } else {

                    modeCdList.add(ProcessMode.UPDATE_WAREHOUSE.code);
                }

            // Located at customer
            } else if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                if (!SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {

                    modeCdList.add(ProcessMode.RMA.code);
                }

                modeCdList.add(ProcessMode.RWS.code);

            } else {

                modeCdList.add(ProcessMode.UPDATE_INVENTORY.code);

                // Located at receive WH
                if (serialInfo.getRcvRtlWhCd().equals(rtlWhCd)) {

                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                    if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                        serialInfo.setErrMsgId(NLZM2141E);
                        return serialInfo;
                    }
                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                    // Warning
                    serialInfo.setErrMsgId(NLZM2444W);

                // Located at different WH
                } else {

                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                    if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                        serialInfo.setErrMsgId(NLZM2442E);
                        return serialInfo;
                    }
                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                    // Warning
                    serialInfo.setErrMsgId(NLZM2445W);
                }

                serialInfo.setSerErrStsCd(NLZC302001Constant.DUP_ERR_RTRN_ITEM);
                execRwsSerChk = false;
            }

        } else {

            modeCdList.add(ProcessMode.INSERT_WAREHOUSE.code);
        }

        // Check RWS Serial
        if (execRwsSerChk) {

            serialInfo = chkRwsSerial(serialInfo);
        }

        serialInfo.setModeCdList(modeCdList);
        return serialInfo;
    }

    /**
     * Check Service Machine Master for Back (Receive)
     * @param serialInfo NLXC042001SerialInfo
     * @param svcMachMstrMap Map<String, Object>
     * @return NLXC042001SerialInfo
     */
    private static NLXC042001SerialInfo chkRcvSvcMachMstrForBk(NLXC042001SerialInfo serialInfo, Map<String, Object> svcMachMstrMap) {

        List<String> modeCdList = new ArrayList<String>();
        boolean execRwsSerChk = true;

        if (svcMachMstrMap != null && !svcMachMstrMap.isEmpty()) {

            // Set Machine Master Info
            serialInfo = setSvcMachMstrInfo(serialInfo, svcMachMstrMap);

            String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 START */
            // String rtlWhCd = (String) svcMachMstrMap.get("RTL_WH_CD");
            /* 07/07/2017 CSAI Y.Imazu Del QC#19730 END */
            String locStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_LOC_STS_CD");
            String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
            String mdseCd = (String) svcMachMstrMap.get("MDSE_CD");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            String curLocNum = (String) svcMachMstrMap.get("CUR_LOC_NUM");
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

            // Check Machine Master
            if (!SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                // At Customer
                if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                    serialInfo.setErrMsgId(NLZM2318E);
                    return serialInfo;

                // In Transit (WH)
                } else if (LOC_STS.IN_TRANSIT_WH.equals(locStsCd)) {

                    serialInfo.setErrMsgId(NLZM2443E);
                    return serialInfo;
                }
            }

            // Set Machine Master Update API Mode
            if (ZYPCommonFunc.hasValue(trxHdrNum)) {

                modeCdList.add(ProcessMode.ALLOCATION_OFF.code);
            }

            if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                if (!serialInfo.getMdseCd().equals(mdseCd)) {

                    modeCdList.add(ProcessMode.ITEM_CHANGE.code);

                } else {

                    modeCdList.add(ProcessMode.UPDATE_WAREHOUSE.code);
                }

            } else {

                modeCdList.add(ProcessMode.UPDATE_INVENTORY.code);

                // Located at different Ship From WH
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 START */
                // if (!serialInfo.getShipFromLocCd().equals(rtlWhCd)) {
                if (!serialInfo.getShipFromLocCd().equals(curLocNum)) {
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 END */

                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                    if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                        serialInfo.setErrMsgId(NLZM2442E);
                        return serialInfo;
                    }
                    /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                    // Warning
                    serialInfo.setErrMsgId(NLZM2447W);
                    serialInfo.setSerErrStsCd(NLZC302001Constant.DUP_ERR);
                    execRwsSerChk = false;
                }
            }

        } else {

            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
            if (ONBATCH_TYPE.ONLINE.equals(serialInfo.getOnBatchType())) {

                serialInfo.setErrMsgId(NLCM0151E);
                return serialInfo;
            }
            /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

            modeCdList.add(ProcessMode.INSERT_WAREHOUSE.code);

            // Warning
            serialInfo.setErrMsgId(NLZM2447W);
            serialInfo.setSerErrStsCd(NLZC302001Constant.DUP_ERR);
            execRwsSerChk = false;
        }

        // Check RWS Serial
        if (execRwsSerChk) {

            serialInfo = chkRwsSerial(serialInfo);
        }

        serialInfo.setModeCdList(modeCdList);
        return serialInfo;
    }

    /** QC#17919 Add.
     * Check RCV_SER_CHECK_CTRL
     * @param serialInfo NLXC042001SerialInfo
     * @return boolean
     */
    private static boolean checkRcvSerChkCtrl(NLXC042001SerialInfo serialInfo) {

        // Get SCE order type code.
        String sceOrdTypCd = serialInfo.getSceOrdTpCd();

        if (!ZYPCommonFunc.hasValue(sceOrdTypCd)) {
             return false;
        }

        // Get WH owner code.
        String whOwnrCd = "";
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        rtlWhTMsg.glblCmpyCd.setValue(serialInfo.getGlblCmpyCd());
        rtlWhTMsg.rtlWhCd.setValue(serialInfo.getRcvRtlWhCd());
        rtlWhTMsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg != null) {

            whOwnrCd = rtlWhTMsg.whOwnrCd.getValue();

        } else {

            return false;

        }

        // Get receive serial check control.
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", serialInfo.getGlblCmpyCd());
        queryParam.put("dsCondConstGrpId", "RCV_SER_CHECK_CTRL");

        List<Map<String, String>> rcvSerCheckCtrlList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("geRcvSerCheckCtrl", queryParam);

        if (rcvSerCheckCtrlList != null && !rcvSerCheckCtrlList.isEmpty()) {
            for (Map<String, String> rcvSerCheckCtrl : rcvSerCheckCtrlList) {

                // check start.
                if (sceOrdTypCd.equals(rcvSerCheckCtrl.get("DS_COND_CONST_VAL_TXT_01")) && whOwnrCd.equals(rcvSerCheckCtrl.get("DS_COND_CONST_VAL_TXT_02"))) {
                    return true;
                }
            }
        }

        return false;
    }

    /** QC#19642 ADD
     * Check REPAIR_SUBCONTRACT TRX_HDR_NUM
     * @param serialInfo NLXC042001SerialInfo
     * @param trxHdrNum String
     * @return boolean
     */
    private static boolean checkSubConTrxHdrNum(NLXC042001SerialInfo serialInfo, String trxHdrNum) {

        // Get SCE order type code.
        String sceOrdTypCd = serialInfo.getSceOrdTpCd();

        if (!ZYPCommonFunc.hasValue(sceOrdTypCd)) {

            return false;
        }

        if (!SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTypCd)) {

            return false;
        }

        // Get TRX_HDR_NUM
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", serialInfo.getGlblCmpyCd());
        queryParam.put("rwsNum", serialInfo.getRwsNum());

        String subConTrxHdrNum = (String) ssmBatchClient.queryObject("getTrxHdrNum", queryParam);

        if (!ZYPCommonFunc.hasValue(subConTrxHdrNum)) {

            return false;
        }

        if (!subConTrxHdrNum.equals(trxHdrNum)) {

            return false;
        }

        return true;
    }

    /* 07/07/2017 CSAI Y.Imazu Del QC#19730 START */
//    /** QC#19642 ADD
//     * Check REPAIR_SUBCONTRACT RTL_WH_CD
//     * @param serialInfo NLXC042001SerialInfo
//     * @param rtlWhCd String
//     * @return boolean
//     */
//    private static boolean checkSubConRtlWhCd(NLXC042001SerialInfo serialInfo, String rtlWhCd) {
//
//        // Get SCE order type code.
//        String sceOrdTypCd = serialInfo.getSceOrdTpCd();
//
//        if (!ZYPCommonFunc.hasValue(sceOrdTypCd)) {
//
//            return false;
//        }
//
//        if (!SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTypCd)) {
//
//            return false;
//        }
//
//        // Get RTL_WH_CD
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", serialInfo.getGlblCmpyCd());
//        queryParam.put("rwsNum", serialInfo.getRwsNum());
//
//        String subConrtlWhCd = (String) ssmBatchClient.queryObject("getRtlWhCd", queryParam);
//
//        if (!ZYPCommonFunc.hasValue(subConrtlWhCd)) {
//
//            return false;
//        }
//
//        if (!subConrtlWhCd.equals(rtlWhCd)) {
//
//            return false;
//        }
//
//        return true;
//    }
    /* 07/07/2017 CSAI Y.Imazu Del QC#19730 END */
}
