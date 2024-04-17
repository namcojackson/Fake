/*
     * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC403001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.RTL_WHTMsg;
import business.parts.NLZC403001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC403001.constant.NLZC403001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Serial Validation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2015   CSAI            K.Lee           Create          Initial
 * 11/01/2016   CITS            K.Ogino         Update          QC#15714
 * 02/01/2017   CITS            Y.Fujii         Update          QC#17214
 * 02/13/2017   CITS            T.Wada          Update          QC#17339
 * 03/22/2017   CITS            R.Shimamoto     Update          QC#17919
 * 03/22/2017   CITS            T.Wada          Update          QC#20498
 * 09/29/2017   CITS            T.Hakodate      Update          QC#21457
 * 08/10/2018   CITS            T.Hakodate      Update          QC#26585
 *</pre>
 */
public class NLZC403001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    private String checkRtlWhCd = null;

    public NLZC403001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute This can be called method from external class.
     * @param NSZC010001PMsg parameter
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(NLZC403001PMsg param, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(param);
        doProcess();
        msgMap.flush();

    }

    /**
     * execute This can be called method from external class.
     * @param List<NSZC010001PMsg> list
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(List<NLZC403001PMsg> list, final ONBATCH_TYPE onBatchType) {

        for (NLZC403001PMsg param : list) {
            execute(param, onBatchType);
        }
    }

    /**
     * doProcess This is Main process Method.
     */
    private void doProcess() {

        NLZC403001PMsg pMsg = (NLZC403001PMsg) msgMap.getPmsg();
        String whCd = null;
        String sceOrdTpCd = null;
        String serRangeFrom = null;
        String serRangeThru = null;
        int lgSerNum = 0;
        String instlBaseCtrlFlg = null;

        // #######################################
        // 1-1. Mandatory Check
        // #######################################
        if (!checkMandatory(pMsg)) {
            return;
        }

        checkRtlWhCd = pMsg.whCd.getValue();
        Map<String, Object> soRwsInfo = null;

        if (ZYPCommonFunc.hasValue(pMsg.soNum)) {
            soRwsInfo = getSo(pMsg);
            if (soRwsInfo != null) {
                whCd = (String) soRwsInfo.get("WH_CD");
                ZYPEZDItemValueSetter.setValue(pMsg.whCd, whCd);
            } else {
                //The SO# does not exist.
                setErrMsg(pMsg, NLZC403001Constant.NLZM2300E);
                ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                return;
            }
        } else if (ZYPCommonFunc.hasValue(pMsg.rwsRefNum)) {
            soRwsInfo = getRws(pMsg);
            if (soRwsInfo != null) {
                sceOrdTpCd = (String) soRwsInfo.get("SCE_ORD_TP_CD");
                whCd = (String) soRwsInfo.get("WH_CD");
            } else {
                //The RWS# does not exist.
                setErrMsg(pMsg, NLZC403001Constant.NLZM2301E);
                ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                return;
            }
            if (sceOrdTpCd.equals(SCE_ORD_TP.BUY_BACK)) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NLZC403001Constant.MODE_INBOUND_BUYBACK);
            } else if (sceOrdTpCd.equals(SCE_ORD_TP.DC_TRANSFER)) {
// QC#17339
//                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NLZC403001Constant.MODE_INBOUND_BUYBACK);
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NLZC403001Constant.MODE_INBOUND_DC_TRANSFER);
            } else if (sceOrdTpCd.equals(SCE_ORD_TP.RETURN)) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NLZC403001Constant.MODE_INBOUND_RETURN);
// QC#17339
            } else if (sceOrdTpCd.equals(SCE_ORD_TP.REPAIR_SUBCONTRACT)) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NLZC403001Constant.MODE_INBOUND_SUBCONTRACT);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.whCd, whCd);
        }

        Map<String, Object> itemInfo = getItemMaster(pMsg);

        if (itemInfo != null) {
            instlBaseCtrlFlg = (String) itemInfo.get("INSTL_BASE_CTRL_FLG");
        } else {
            //Item does not Exist
            setErrMsg(pMsg, NLZC403001Constant.NLZM2287E);
            return;
        }

        String rcvSerTakeFlg =  (String) itemInfo.get("RCV_SER_TAKE_FLG");
        String shpgSerTakeFlg =  (String) itemInfo.get("SHPG_SER_TAKE_FLG");
        // #######################################
        // 2-1. Serial Range Check
        // #######################################

        if (!isValidatedSerialRange(pMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
            return;
        }

        // QC#15714
        if ((pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_OUTBOUND) //
                || pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND_BUYBACK) //
                || pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND_RETURN) //
                || pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_ADJUSTMENT_DECREASE) //
                || pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND_SUBCONTRACT) //
                || pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND_DC_TRANSFER)) //
                && ZYPConstant.FLG_OFF_N.equals(shpgSerTakeFlg)) {

            // Serial acquisition is not required.
            setErrMsg(pMsg, NLZC403001Constant.NLZM2479E);
            return;

        } else if (pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND) //
                && ZYPConstant.FLG_OFF_N.equals(rcvSerTakeFlg)) {

            // Serial acquisition is not required.
            setErrMsg(pMsg, NLZC403001Constant.NLZM2479E);
            return;
        }

        if (instlBaseCtrlFlg.equals(ZYPConstant.FLG_OFF_N)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_NA);
            setWarningMsg(pMsg, NLZC403001Constant.NLZM2288W);
            return;
        }
        Map<String, Object> ibInfo = getIb(pMsg);

        // #######################################
        // 3-1. Serial Check
        // #######################################
        if (!isValidSerial(itemInfo, soRwsInfo, ibInfo, pMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
            return;
        }

        // QC#17919 Add.
        // ################################################
        // 4-1. Different from the shipped serial check.
        // ################################################
        if (!chkRwsSerial(soRwsInfo, pMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
            setErrMsg(pMsg, NLZC403001Constant.NLBM1359E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_NORMAL);
    }

    /**
     * setHeaderErrMsg
     * @param NSZC010001PMsg pMsg
     * @param String msgId
     */
    private void setErrMsg(NLZC403001PMsg pMsg, String msgId) {
        msgMap.addXxMsgId(msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
        msgMap.flush();
    }

    /**
     * setHeaderErrMsg
     * @param NSZC010001PMsg pMsg
     * @param String msgId
     */
    private void setWarningMsg(NLZC403001PMsg pMsg, String msgId) {
        msgMap.addXxMsgId(msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_NA);
        msgMap.flush();
    }

    /**
     * checkMandatory
     * @return boolean Normal:true, Error:false
     */
    private boolean checkMandatory(NLZC403001PMsg pMsg) {

        if (!hasValue(pMsg.glblCmpyCd)) {
            // Global Company Code is mandatory.
            setErrMsg(pMsg, NLZC403001Constant.NLGM0012E);
            return false;
        }

        if (!hasValue(pMsg.xxModeCd)) {
            // Process mode is not set.
            setErrMsg(pMsg, NLZC403001Constant.NLZM2087E);
            return false;
        } else {
            if (!pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_OUTBOUND) && !pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND)
                    && !pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND_RETURN) && !pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND_BUYBACK)
                    && !pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_ADJUSTMENT_DECREASE)) {
                setErrMsg(pMsg, NLZC403001Constant.NLZM2087E);
                return false;
            }
        }

        if (!hasValue(pMsg.mdseCd)) {
            // Item Code is mandatory.
            setErrMsg(pMsg, NLZC403001Constant.NLZM2289E);
            return false;
        }

        if (!hasValue(pMsg.serNum)) {
            // Input parameter "Mode Code" is a mandatory field.
            setErrMsg(pMsg, NLZC403001Constant.NLZM2091E);
            return false;
        }

        if (pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_OUTBOUND)) {
            if (!hasValue(pMsg.soNum) && !hasValue(pMsg.whCd)) {
                // SO# or Warehouse Code is must be entered on Out-bound Mode.
                setErrMsg(pMsg, NLZC403001Constant.NLZM2290E);
                return false;
            }
        } else if (pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND)) {
            if (!hasValue(pMsg.rwsRefNum) && !hasValue(pMsg.whCd)) {
                // Input parameter "Mode Code" is a mandatory field.
                setErrMsg(pMsg, NLZC403001Constant.NLZM2291E);
                return false;
            }
        }
        return true;
    }

    /**
     * checkMandatory
     * @return boolean Normal:true, Error:false
     */
    private boolean isValidatedSerialRange(NLZC403001PMsg pMsg) {

        List<Map<String, Object>> rangeInfoList = getSerialRangeList(pMsg);

        if (rangeInfoList.size() > 0) {
            boolean hasMatchedRange = false;

            for (Map<String, Object> rangeInfo : rangeInfoList) {
                String serRangeFrom = (String) rangeInfo.get("FROM_SER_NUM");
                String serRangeThru = (String) rangeInfo.get("THRU_SER_NUM");
                BigDecimal lgSerNumDec = (BigDecimal) rangeInfo.get("LG_SER_NUM");
                int lgSerNum = 0;

                if (lgSerNumDec != null) {
                    lgSerNum = lgSerNumDec.intValue();
                }

                if (lgSerNum > 0 && pMsg.serNum.getValue().length() != lgSerNum) {
                    hasMatchedRange = false;

                } else if (ZYPCommonFunc.hasValue(serRangeFrom) && pMsg.serNum.getValue().compareTo(serRangeFrom) < 0) {
                    hasMatchedRange = false;

                } else if (ZYPCommonFunc.hasValue(serRangeThru) && pMsg.serNum.getValue().compareTo(serRangeThru) > 0) {
                    hasMatchedRange = false;

                } else {
                    hasMatchedRange = true;
                    break;
                }
            }

            if (!hasMatchedRange) {
                setErrMsg(pMsg, NLZC403001Constant.NLZM2292E);
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    /**
     * isValidSerial
     * @param itemInfo Map<String, Object>
     * @param soRwsInfo Map<String, Object>
     * @param ibInfo Map<String, Object>
     * @param pMsg NLZC403001PMsg
     * @return boolean
     */
    private boolean isValidSerial(Map<String, Object> itemInfo, Map<String, Object> soRwsInfo, Map<String, Object> ibInfo, NLZC403001PMsg pMsg) {
        
        
        //QC#21574 MOD START
        //QC#21457 ADD START
        // Get SCE order type code.
        // String sceOrdTypCd = (String) soRwsInfo.get("SCE_ORD_TP_CD");
        //QC#21457 ADD END
        //QC#21574 MOD END

        int cntSoSer = 0;
        int cntSoMatchSer = 0;
        int cntRwsSer = 0;
        int cntRwsMatchSer = 0;
        
        String rcvSerTakeFlg =  (String) itemInfo.get("RCV_SER_TAKE_FLG");

        BigDecimal svcMachMstrPk = null;
        String svcMachMstrStsCd = null;
        String curLocNum = null;
        String curLocAcctNum = null;
        String svcMachMaintAvalFlg = null;
        String svcMachLocStsCd = null;

        if (soRwsInfo != null) {
            BigDecimal cntSoSerWk = (BigDecimal) soRwsInfo.get("CNT_SO_SER");
            if (cntSoSerWk != null) {
                cntSoSer = cntSoSerWk.intValue();
            }

            BigDecimal cntSoMatchSerWk = (BigDecimal) soRwsInfo.get("CNT_SO_MATCH_SER");
            if (cntSoMatchSerWk != null) {
                cntSoMatchSer = cntSoMatchSerWk.intValue();
            }

            BigDecimal cntRwsSerWk = (BigDecimal) soRwsInfo.get("CNT_RWS_SER");
            if (cntRwsSerWk != null) {
                cntRwsSer = cntRwsSerWk.intValue();
            }

            BigDecimal cntRwsMatchSerWk = (BigDecimal) soRwsInfo.get("CNT_RWS_MATCH_SER");
            if (cntRwsMatchSerWk != null) {
                cntRwsMatchSer = cntRwsMatchSerWk.intValue();
            }
        }
        if (ibInfo != null) {
            svcMachMstrPk = (BigDecimal) ibInfo.get("SVC_MACH_MSTR_PK");
            svcMachMstrStsCd = (String) ibInfo.get("SVC_MACH_MSTR_STS_CD");
            curLocNum = (String) ibInfo.get("CUR_LOC_NUM");
            curLocAcctNum = (String) ibInfo.get("CUR_LOC_ACCT_NUM");
            svcMachMaintAvalFlg = (String) ibInfo.get("SVC_MACH_MAINT_AVAL_FLG");
            svcMachLocStsCd = (String) ibInfo.get("SVC_MACH_MSTR_LOC_STS_CD");
        }

        if (pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_OUTBOUND)) {
            //Order Entry Validation
            if (!ZYPCommonFunc.hasValue(pMsg.soNum)) {
                if (rcvSerTakeFlg.equals(ZYPConstant.FLG_ON_Y)) {
                    if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                        // Entered serial does not exist in IB Master.
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2293E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    } else if (svcMachMaintAvalFlg.equals(ZYPConstant.FLG_OFF_N)) {
                        // Entered serial already have been allocated.
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2294E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    } else if (!svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.CREATED) && !svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.REMOVED)) {
                        // IB status of entered serial is not [Created] or [Removed].
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2295E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    } else if (!pMsg.whCd.getValue().equals(curLocNum)) {
                        // Entered warehouse is not match with IB.
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2296E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    }

                } else {
                    if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                        // Entered serial does not exist in IB Master.
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2293E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    } else if (svcMachMaintAvalFlg.equals(ZYPConstant.FLG_OFF_N)) {
                        // Entered serial already have been allocated.
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2294E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    } else if (!svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.CREATED) && !svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.REMOVED)) {
                        // IB status of entered serial is not [Created] or [Removed].
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2295E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    } else if (!pMsg.whCd.getValue().equals(curLocNum)) {
                        // Entered warehouse is not match with IB.
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2296E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    }
                }

            //Warehouse Validation 
            } else {
                if (cntSoSer > 0) {
                    if (!svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.CREATED) && !svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.REMOVED)) {
                        // IB status of entered serial is not [Created] or [Removed].
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2295E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    } else if (cntSoMatchSer < 1) {
                        // Entered serial is not matched with SO.
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2297E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    }

                } else if (rcvSerTakeFlg.equals(ZYPConstant.FLG_ON_Y)) {
                    if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                        // Entered serial does not exist in IB Master.
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2293E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    } else if (svcMachMaintAvalFlg.equals(ZYPConstant.FLG_OFF_N)) {
                        // Entered serial already have been allocated.
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2294E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    } else if (!svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.CREATED) && !svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.REMOVED)) {
                        // IB status of entered serial is not [Created] or [Removed].
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2295E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    } else if (!pMsg.whCd.getValue().equals(curLocNum)) {
                        // Entered warehouse is not match with IB.
                        setErrMsg(pMsg, NLZC403001Constant.NLZM2296E);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                        return false;
                    }

                } else {
                    if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                            if (svcMachMaintAvalFlg.equals(ZYPConstant.FLG_OFF_N)) {
                                // Entered serial already have been allocated.
                                setErrMsg(pMsg, NLZC403001Constant.NLZM2294E);
                                ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                                return false;
                            } else if (!svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.CREATED) && !svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.REMOVED)) {
                                // IB status of entered serial is not [Created] or [Removed].
                                setErrMsg(pMsg, NLZC403001Constant.NLZM2295E);
                                ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                                return false;
                            } else if (!pMsg.whCd.getValue().equals(curLocNum)) {
                                // Entered warehouse is not match with IB.
                                setErrMsg(pMsg, NLZC403001Constant.NLZM2296E);
                                ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                                return false;
                            }
                        }
                    }
                }
            }
        } else if (pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND)) {
            if (!ZYPCommonFunc.hasValue(pMsg.rwsRefNum)) {
                if (rcvSerTakeFlg.equals(ZYPConstant.FLG_ON_Y)) {
                    if (ZYPCommonFunc.hasValue(svcMachMstrPk) && !svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.TERMINATED)) {
                        // IB status of entered serial is not [Terminated].
                       setErrMsg(pMsg, NLZC403001Constant.NLZM2299E);
                       ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                       return false;
                    }
                }
            } else {
              
              // QC#21574 ADD START
                String sceOrdTypCd = (String) soRwsInfo.get("SCE_ORD_TP_CD");
                // QC#21574 ADD END
                if (rcvSerTakeFlg.equals(ZYPConstant.FLG_ON_Y)) {

                    // QC#21457 MOD START
                    if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {

                        String[] inbdMmExistsCheck = ZYPCodeDataUtil.getVarCharConstValue("NLZC4030_INBD_MM_EXISTS_CHECK", pMsg.glblCmpyCd.getValue()).split(",");

                        if ((Arrays.asList(inbdMmExistsCheck).contains(sceOrdTypCd))) {

                            if (!(svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.CREATED) || svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.REMOVED))) {

                                // Invalid IB Status
                                setErrMsg(pMsg, NLZC403001Constant.NLZM2295E);
                                ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                                return false;
                            }

                        } else {

                            if (!svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.TERMINATED)) {
                                // Entered serial is already
                                // registered in
                                // IB.
                                setErrMsg(pMsg, NLZC403001Constant.NLZM2299E);
                                ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                                return false;
                            }
                        }
                    } 
                    
                    //QC#21457 MOD START
                    //if (ZYPCommonFunc.hasValue(svcMachMstrPk) && !svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.TERMINATED)) {
                    //    // Entered serial is already registered in IB.
                    //    setErrMsg(pMsg, NLZC403001Constant.NLZM2299E);
                    //    ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                    //   return false;
                    //}
                    //QC#21457 MOD END
                    
                    // QC#20498 Mod Start
                    //if (cntRwsSer > 0) {
                    //    if (cntRwsMatchSer < 1) {
                    //        // Entered serial is not matched with Receiving work sheet.
                    //        setErrMsg(pMsg, NLZC403001Constant.NLZM2298E);
                    //        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                    //        return false;
                    //    }
                    //}
                    // QC#20498 Mod End
                }
            }
        } else if (pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND_RETURN)) {
            if (!ZYPCommonFunc.hasValue(pMsg.rwsRefNum)) {
                if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                    // Entered serial does not exist in IB Master.
                    setErrMsg(pMsg, NLZC403001Constant.NLZM2293E);
                    return false;
                } else if (svcMachMaintAvalFlg.equals(ZYPConstant.FLG_OFF_N)) {
                    // Entered serial already have been allocated.
                    setErrMsg(pMsg, NLZC403001Constant.NLZM2294E);
                    return false;
                } else if (!svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.INSTALLED)) {
                    // Invalid IB Status
                    setErrMsg(pMsg, NLZC403001Constant.NLZM2295E);
                    return false;
                } else if (!pMsg.whCd.getValue().equals(curLocNum)) {
                    // Entered warehouse is not match with IB.
                    setErrMsg(pMsg, NLZC403001Constant.NLZM2296E);
                    return false;
                }
            } else {
               if (cntRwsSer > 0) {
                   if (cntRwsMatchSer < 1) {
                       // Entered serial is not matched with Receiving work sheet.
                       setErrMsg(pMsg, NLZC403001Constant.NLZM2298E);
                       return false;
                   }
               }
            }
        } else if (pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_ADJUSTMENT_DECREASE)) {
                if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                    // Entered serial does not exist in IB Master.
                    setErrMsg(pMsg, NLZC403001Constant.NLZM2293E);
                    return false;
                } else if (svcMachMaintAvalFlg.equals(ZYPConstant.FLG_OFF_N)) {
                    // Entered serial already have been allocated.
                    setErrMsg(pMsg, NLZC403001Constant.NLZM2294E);
                    return false;
                } else if (svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.TERMINATED)) {
                    // Invalid IB Status
                    setErrMsg(pMsg, NLZC403001Constant.NLZM2295E);
                    return false;
                } else if (!pMsg.whCd.getValue().equals(curLocNum)) {
                    // Entered warehouse is not match with IB.
                    setErrMsg(pMsg, NLZC403001Constant.NLZM2296E);
                    return false;
                }
// QC#17339 modify Start
        } else if (pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND_SUBCONTRACT)) {
            // Do not check if Serial of argument matches RWS Serial
            if (cntRwsMatchSer > 0) {
                return true;
            }
            // Do not check if Serial of argument matches SO Serial
            Map<String, Object> soInfo = getSoByRwsRefNum(pMsg);
            if (soInfo != null) {
                String soNum = (String) soInfo.get("SO_NUM");
                if (ZYPCommonFunc.hasValue(soNum)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.soNum, soNum);
                    Map<String, Object> soSerInfo = getSo(pMsg);
                    BigDecimal cntSoMatchSerWk = (BigDecimal) soSerInfo.get("CNT_SO_MATCH_SER");
                    if (cntSoMatchSerWk != null) {
                        cntSoMatchSer = cntSoMatchSerWk.intValue();
                    }
                    if (cntSoMatchSer > 0){
                        return true;
                    }
                }
            }

            // Check if it exists in Machine Master.  If it exists in Machine Master, Status must be TERMINATED
            if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {

                if (svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.TERMINATED)) {
                    return true;
                }
                // QC#26585 MOD START
                //if (svcMachLocStsCd.equals(LOC_STS.IN_TRANSIT)) {
                //    return true;
                //}
                if (svcMachLocStsCd.equals(LOC_STS.WORK_IN_PROCESS_SUBCONTRACT)) {
                    return true;
                }
                // QC#26585 MOD END
                // IB status of entered serial is not [Terminated].
                setErrMsg(pMsg, NLZC403001Constant.NLZM2299E);
                ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                return false;
            }
        } else if (pMsg.xxModeCd.getValue().equals(NLZC403001Constant.MODE_INBOUND_DC_TRANSFER)) {
            // Do not check if Serial of argument matches RWS Serial
            if (cntRwsMatchSer > 0) {
                return true;
            }
            // Check if it exists in Machine Master.  If it exists in Machine Master, Status must be TERMINATED
            if (rcvSerTakeFlg.equals(ZYPConstant.FLG_ON_Y)) {
                if (ZYPCommonFunc.hasValue(svcMachMstrPk) && !svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.TERMINATED)) {
                    // IB status of entered serial is not [Terminated].
                   setErrMsg(pMsg, NLZC403001Constant.NLZM2299E);
                   ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC403001Constant.RETURN_CODE_ERROR);
                   return false;
                }
            }
        }
// QC#17339 modify End

        return true;
    }

    /**
     * getItemMaster
     * @param pMsg NLZC403001PMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getItemMaster(NLZC403001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", pMsg.mdseCd.getValue());

        return (Map<String, Object>) ssmClient.queryObject("getItemMaster", ssmParam);
    }

    /**
     * getSerialRangeList
     * @param pMsg NLZC403001PMsg
     * @return Map<String, Object>
     */
    private List<Map<String, Object>> getSerialRangeList(NLZC403001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", pMsg.mdseCd.getValue());

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getSerialRangeList", ssmParam);
    }

    /**
     * getSo
     * @param pMsg NLZC403001PMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getSo(NLZC403001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("soNum", pMsg.soNum.getValue());
        ssmParam.put("serNum", pMsg.serNum.getValue());
        ssmParam.put("mdseCd", pMsg.mdseCd.getValue());

        return (Map<String, Object>) ssmClient.queryObject("getSo", ssmParam);
    }

    /**
     * getRws
     * @param pMsg NLZC403001PMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getRws(NLZC403001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("rwsRefNum", pMsg.rwsRefNum.getValue());
        ssmParam.put("mdseCd", pMsg.mdseCd.getValue());
        ssmParam.put("serNum", pMsg.serNum.getValue());

        return (Map<String, Object>) ssmClient.queryObject("getRws", ssmParam);
    }

    /**
     * getIB
     * @param pMsg NLZC403001PMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getIb(NLZC403001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", pMsg.mdseCd.getValue());
        ssmParam.put("serNum", pMsg.serNum.getValue());

        return (Map<String, Object>) ssmClient.queryObject("getIb", ssmParam);
    }
    /**
     * getSoByRwsRefNum
     * @param pMsg
     * @return
     */
    private Map<String, Object> getSoByRwsRefNum(NLZC403001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("rwsRefNum", pMsg.rwsRefNum.getValue());

        return (Map<String, Object>) ssmClient.queryObject("getSoByRwsRefNum", ssmParam);
    }
    /**
     * getSo
     * @param glblCmpyCd
     * @param soNum
     * @param serNum
     * @param mdseCd
     * @return
     */
    private Map<String, Object> getSo(String glblCmpyCd, String soNum, String serNum, String mdseCd ) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);
        ssmParam.put("serNum", serNum);
        ssmParam.put("mdseCd", mdseCd);

        return (Map<String, Object>) ssmClient.queryObject("getSo", ssmParam);
    }
    /** QC#17919 Add.
     * Check RWS Serial
     * @param soRwsInfo Map<String, Object>
     * @param pMsg NLZC403001PMsg
     * @return boolean
     */
    private boolean chkRwsSerial(Map<String, Object> soRwsInfo, NLZC403001PMsg pMsg) {

        if (soRwsInfo != null && checkRcvSerChkCtrl(soRwsInfo, pMsg)) {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            queryParam.put("rwsNum", (String)soRwsInfo.get("RWS_NUM"));
            queryParam.put("mdseCd", pMsg.mdseCd.getValue());

            List<String> rwsSerialList = (List<String>) ssmClient.queryObjectList("getRwsSerialList", queryParam);

            if (rwsSerialList != null && !rwsSerialList.isEmpty()) {

                if (!rwsSerialList.contains(pMsg.serNum.getValue())) {

                    return false;
                }
            }
        }

        return true;
    }
    /** QC#17919 Add.
     * Check RCV_SER_CHECK_CTRL
     * @param soRwsInfo Map<String, Object>
     * @param pMsg NLZC403001PMsg
     * @return boolean
     */
    private boolean checkRcvSerChkCtrl(Map<String, Object> soRwsInfo, NLZC403001PMsg pMsg) {

        // Get SCE order type code.
        String sceOrdTypCd = (String) soRwsInfo.get("SCE_ORD_TP_CD");

        if (!ZYPCommonFunc.hasValue(sceOrdTypCd)) {
             return false;
        }

        String whOwnrCd = "";
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, this.checkRtlWhCd);
        rtlWhTMsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg != null) {

            whOwnrCd = rtlWhTMsg.whOwnrCd.getValue();

        } else {

            return false;

        }

        // Get receive serial check control.
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("dsCondConstGrpId", "RCV_SER_CHECK_CTRL");

        List<Map<String, String>> rcvSerCheckCtrlList = (List<Map<String, String>>) ssmClient.queryObjectList("geRcvSerCheckCtrl", queryParam);

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

}