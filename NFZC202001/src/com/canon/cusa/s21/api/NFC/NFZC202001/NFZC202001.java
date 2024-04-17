package com.canon.cusa.s21.api.NFC.NFZC202001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CUST_CR_PRFLTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.parts.NFZC202001PMsg;
import business.parts.NMZC600001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC600001.NMZC600001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;

/**
 * <pre>
 * Credit Profile Update (Balance) API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/30/2015   Fujitsu         T.Tanaka        Create          N/A
 * 03/03/2016   Fujitsu         T.Tanaka        Update          Def#4971
 * 03/15/2016   Fujitsu         T.Tanaka        Update          Def#5471
 * 04/28/2016   Fujitsu         T.Tanaka        Update          Def#5676
 * 2016/09/01   Hitachi         K.Kojima        Update          QC#10786
 * 2016/12/08   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/08/08   Fujitsu         H.Ikeda         Update          QC#20428
 * 2017/09/01   Fujitsu         W.Honda         Update          QC#20893
 * 2018/01/24   Fujitsu         T.Murai         Update          QC#22420
 * 2018/01/30   Hitachi         E.Kameishi      Update          QC#23681
 * 2018/02/01   Fujitsu         T.Murai         Update          QC#23880
 * 2018/02/02   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/04/04   Fujitsu         H.Ikeda         Update          QC#23916
 * 2018/05/23   Hitachi         J.Kim           Update          QC#26127
 * 2018/06/21   Fujitsu         Y.Matsui        Update          QC#25862
 * </pre>
 */
public class NFZC202001 extends S21ApiCommonBase {

    /** */
    public static final String MODE_CUST_ACCT = "01";

    /** */
    public static final String MODE_BILL_TO_CUST = "02";

    /** */
    public static final String MODE_CUSTOMER = "03";

    /** */
    public static final String VARCHAR_AR_RGTN_STS_CD = "AR_RGTN_STS_CD";

    /** */
    public static final String AR_BAL_AMT = "AR_BAL_AMT";

    /** */
    public static final String GRACE_PER_DAYS_AOT = "GRACE_PER_DAYS_AOT";
    
    // START 2018/02/02 H.Ikeda [QC#22095,ADD]
    /** */
    public static final String CONTR_GRACE_PER_DAYS_AOT = "CONTR_GRACE_PER_DAYS_AOT";
    // END   2018/02/02 H.Ikeda [QC#22095,ADD]

    /** */
    public static final String OVD_WS_FLG = "OVD_WS_FLG";
    
    /** */
    public static final String OVD_PRT_FLG = "OVD_PRT_FLG";
    
    /** */
    public static final String FUNC_RMNG_BAL_GRS_AMT = "FUNC_RMNG_BAL_GRS_AMT";

    /** */
    public static final String SUM_AMT = "SUM_AMT";
    
    /** */
    public static final String INV_DUE_DT = "INV_DUE_DT";
    
    /** */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** */
    public static final String BILL_TO_CUST_PK = "BILL_TO_CUST_PK";

    /** Entry Parameter Error [@] */
    public static final String NFCM0501E = "NFCM0501E";

    /** System Error */
    public static final String NFCM0503E = "NFCM0503E";

    /** Data does not exist. */
    public static final String NFCM0508E = "NFCM0508E";
    // START 2016/12/08 H.Ikeda [QC#15823,ADD]
    /** It failed to register. */
    public static final String NFZM0013E = "NFZM0013E";
    // END   2016/12/08 H.Ikeda [QC#15823,ADD]
    /** */
    private String arRgtnStsCd = null;
    
    /** */
    private BigDecimal netArBalAmt = BigDecimal.ZERO;

    /** */
    private BigDecimal gracePerDaysAot = BigDecimal.ZERO;

    // START 2018/02/02 H.Ikeda [QC#22095,ADD]
    /** */
    public BigDecimal contrGracePerDaysAot = BigDecimal.ZERO;
    // END   2018/02/02 H.Ikeda [QC#22095,ADD]

    /** */
    private String ovdWsFlg = null;

    /** */
    private String ovdPrtFlg = null;

    /** */
    private BigDecimal rmngBalGlssAmt = BigDecimal.ZERO;

    /** */
    private String invDueDt = null;

    /** */
    private BigDecimal billtoCustPk;

    private String billtoCustCd;

    private String selltoCustCd;

    private boolean skipCreditUpdate = false;
    // START 2018/05/23 J.Kim [QC#26127,DEL]
    //private static final String  DS_CONTR_PK = "DS_CONTR_PK";
    //
    //private static final String  DS_CONTR_SRC_TP_CD = "DS_CONTR_SRC_TP_CD";
    //
    //private static final String  DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";
    //
    //private static final String  DS_CONTR_BLLG_MTR_PK = "DS_CONTR_BLLG_MTR_PK";
    //
    //private static final String  DS_CONTR_PRC_EFF_PK = "DS_CONTR_PRC_EFF_PK";
    // END 2018/05/23 J.Kim [QC#26127,DEL]
    private int apiErrorCount = 0;
    /** */
    private S21SsmBatchClient ssmBatchClient;
    // START 2018/05/23 J.Kim [QC#26127,DEL]
    // private static final int FETCH_SIZE = 100;
    // END 2018/05/23 J.Kim [QC#26127,DEL]
    /**
     */
    public NFZC202001() {
        super();
    }

    /**
     * 
     * @param param
     * @param onBatchType
     */
    public void execute(final NFZC202001PMsg param, final ONBATCH_TYPE onBatchType) {

        if (param == null) {
            return;
        }

        
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Check Input Parameter
        if (!checkParam(msgMap)) {
            msgMap.flush();
            return;
        }

        if(param.xxModeCd.getValue().equals(MODE_CUSTOMER)) {

            if(!processMode3(msgMap,onBatchType)) {
                msgMap.flush();
                return;
            }

            if(!checkAccount_Mode01(msgMap)) {
                msgMap.flush();
                return;
            }
            
            // Mode1 Check AR Transaction
            if(!checkTrx_Mode01(msgMap)) {
                msgMap.flush();
                return;
            }
            // Mode1 Update Credit Profile
            if(!this.skipCreditUpdate) {
                if(!updateCreditProfile_Mode01(msgMap, onBatchType)) {
                    msgMap.flush();
                    return;
                }
            }
            // Mode1 Check Over Due Flag
            if(!checkOverDue_Mode01(msgMap)) {
                msgMap.flush();
                return;
            }
            // Mode1 Update Over Due Flag in Credit Profile
            if(!updateDsAccountCreditProfile_Mode01(msgMap)) {
                msgMap.flush();
                return;
            }
            // START 2018/05/23 J.Kim [QC#26127,DEL]
            //// Mode1 Update Contract Hold Flag(Over Due Flag) in Contract
            // if(!updateDsContract_Mode01(msgMap,onBatchType, ZYPConstant.FLG_OFF_N)) {
            //    msgMap.flush();
            //    return;
            // }
            // if(!updateDsContract_Mode01(msgMap,onBatchType, ZYPConstant.FLG_ON_Y)) {
            //    msgMap.flush();
            //    return;
            // }
            // END 2018/05/23 J.Kim [QC#26127,DEL]

        } else {

            // Check Customer, Credit Profile
            if(!checkAccount(msgMap)) {
                msgMap.flush();
                return;
            }

            // Check AR Transaction
            if(!checkTrx(msgMap)) {
                msgMap.flush();
                return;
            }

            // Update Credit Profile
            if(!updateCreditProfile(msgMap,onBatchType)) {
                msgMap.flush();
                return;
            }

            // Check Over Due Flag
            if(!checkOverDue(msgMap)) {
                msgMap.flush();
                return;
            }
            
            // Update Over Due Flag in Credit Profile
            if(!updateCreditProfile_DueFlag(msgMap)) {
                msgMap.flush();
                return;
            }

            // START 2018/06/21 [QC#25862, ADD]
            if (param.xxModeCd.getValue().equals(MODE_BILL_TO_CUST)) {
                // Account-Level Overdue Check
                if (!ZYPCommonFunc.hasValue(param.sellToCustCd)) {
                    param.sellToCustCd.setValue(this.selltoCustCd);
                }
                if (!checkAccount_Mode01(msgMap)) {
                    msgMap.flush();
                    return;
                }
                if (!checkOverDue_Mode01(msgMap)) {
                    msgMap.flush();
                    return;
                }
                if (!updateDsAccountCreditProfile_Mode01(msgMap)) {
                    msgMap.flush();
                    return;
                }
            }
            // END   2018/06/21 [QC#25862, ADD]

            // START 2018/05/23 J.Kim [QC#26127,DEL]
            //// Update Contract Hold Flag(Over Due Flag) in Contract
            // if(!updateContract(msgMap,onBatchType)) {
            //   msgMap.flush();
            //    return;
            // }
            // END 2018/05/23 J.Kim [QC#26127,DEL]
        }
    }

    /**
     * 
     * @param params
     * @param onBatchType
     */
    public void excute(final List<NFZC202001PMsg> params, final ONBATCH_TYPE onBatchType) {
        
        if (params != null) {
            for (NFZC202001PMsg msg : params) {
                execute(msg, onBatchType);
            }
            return;
        }
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkParam(S21ApiMessageMap msgMap) {
        
        boolean res = true;
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();

        /************************************/
        /* Parameter Check                  */
        /************************************/
        if(S21StringUtil.isEmpty(param.glblCmpyCd.getValue())) {
            msgMap.addXxMsgIdWithPrm(NFCM0501E, new String[]{"GLBL_CMPY_CD"});
            res = false;
        }
        
        if(!S21StringUtil.isEmpty(param.xxModeCd.getValue())) {
            if(!param.xxModeCd.getValue().equals(MODE_CUST_ACCT)) {
                if(!param.xxModeCd.getValue().equals(MODE_BILL_TO_CUST)) {
                    if(!param.xxModeCd.getValue().equals(MODE_CUSTOMER)) {
                        msgMap.addXxMsgIdWithPrm(NFCM0501E, new String[]{"XX_MODE_TP"});
                        res = false;
                    }
                }
            }
        } else {
            msgMap.addXxMsgIdWithPrm(NFCM0501E, new String[]{"XX_MODE_TP"});
            res = false;
        }
        
        if(param.xxModeCd.getValue().equals(MODE_CUST_ACCT) || param.xxModeCd.getValue().equals(MODE_CUSTOMER)) {
            if(S21StringUtil.isEmpty(param.sellToCustCd.getValue())) {
                msgMap.addXxMsgIdWithPrm(NFCM0501E, new String[]{"SELL_TO_CUST_CD"});
                res = false;
            }
        }
        
        if(param.xxModeCd.getValue().equals(MODE_BILL_TO_CUST)) {
            if(S21StringUtil.isEmpty(param.billToCustCd.getValue())) {
                msgMap.addXxMsgIdWithPrm(NFCM0501E, new String[]{"BILL_TO_CUST_CD"});
                res = false;
            }
        }

        if(S21StringUtil.isEmpty(param.procDt.getValue())) {
            msgMap.addXxMsgIdWithPrm(NFCM0501E, new String[]{"PROC_DT"});
            res = false;
        }

        /************************************/
        /* Varchar Const Check              */
        /************************************/
        this.arRgtnStsCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_AR_RGTN_STS_CD, param.glblCmpyCd.getValue());
        if(S21StringUtil.isEmpty(this.arRgtnStsCd)) {
            msgMap.addXxMsgIdWithPrm(NFCM0501E, new String[]{"AR_RGTN_STS_CD"});
            res = false;
        }
        
        this.apiErrorCount = 0;
        return res;
    }


    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkAccount(S21ApiMessageMap msgMap) {
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
        
        if(param.xxModeCd.getValue().equals(MODE_CUST_ACCT)) {
            return checkAccount_Mode01(msgMap);
        } else if(param.xxModeCd.getValue().equals(MODE_BILL_TO_CUST)) {
            return checkAccount_Mode02(msgMap);
        } else {
            return checkAccount_Mode03(msgMap);
        }
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkAccount_Mode01(S21ApiMessageMap msgMap) {
        boolean res = true;
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("sellToCustCd", param.sellToCustCd.getValue());
        queryParam.put("rgtnStsCd", this.arRgtnStsCd);

        Map ssmRes = (Map) ssmBatchClient.queryObject("checkAccount_Mode01", queryParam);
        if (ssmRes.size() == 0) {
            msgMap.addXxMsgId(NFCM0508E);
            res = false;
        } else 
        if(ssmRes==null) {
            msgMap.addXxMsgId(NFCM0508E);
            res = false;
        } else {
            // START 2018/04/04 H.Ikeda [QC#23916,Mod]
            if (ssmRes.get(AR_BAL_AMT) == null) {
                msgMap.addXxMsgId(NFCM0508E);
                return false;
            }
            // END 2018/04/04 H.Ikeda [QC#23916,Mod]
            this.netArBalAmt = new BigDecimal(ssmRes.get(AR_BAL_AMT).toString());

            // START 2018/04/04 H.Ikeda [QC#23916,Mod]
            if (ssmRes.get(GRACE_PER_DAYS_AOT) == null) {
                msgMap.addXxMsgId(NFCM0508E);
                return false;
            }
            // END 2018/04/04 H.Ikeda [QC#23916,Mod]
            this.gracePerDaysAot = new BigDecimal(ssmRes.get(GRACE_PER_DAYS_AOT).toString());

            // START 2018/04/04 H.Ikeda [QC#23916,Mod]
            if (ssmRes.get(OVD_WS_FLG) == null) {
                msgMap.addXxMsgId(NFCM0508E);
                return false;
            }
            // END 2018/04/04 H.Ikeda [QC#23916,Mod]
            this.ovdWsFlg = ssmRes.get("OVD_WS_FLG").toString();

            // START 2018/04/04 H.Ikeda [QC#23916,Mod]
            if (ssmRes.get(OVD_PRT_FLG) == null) {
                msgMap.addXxMsgId(NFCM0508E);
                return false;
            }
            // END 2018/04/04 H.Ikeda [QC#23916,Mod]
            this.ovdPrtFlg = ssmRes.get("OVD_PRT_FLG").toString();

            // START 2018/02/02 H.Ikeda [QC#22095,ADD]
            if (ssmRes.get(CONTR_GRACE_PER_DAYS_AOT) == null) {
                msgMap.addXxMsgId(NFCM0508E);
                return false;
            }
            this.contrGracePerDaysAot = new BigDecimal(ssmRes.get(CONTR_GRACE_PER_DAYS_AOT).toString());
            // END   2018/02/02 H.Ikeda [QC#22095,ADD]
        }
        
        return res;
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkAccount_Mode02(S21ApiMessageMap msgMap) {
        boolean res = true;
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("billToCustCd", param.billToCustCd.getValue());
        queryParam.put("rgtnStsCd", this.arRgtnStsCd);

        Map ssmRes = (Map) ssmBatchClient.queryObject("checkAccount_Mode02", queryParam);
        if(ssmRes==null) {
            msgMap.addXxMsgId(NFCM0508E);
            res = false;
        } else {
            if(ssmRes.get(AR_BAL_AMT)==null) {
                // START 2017/08/08 H.Ikeda [QC#20428,Mod]
                if (ssmRes.get(SELL_TO_CUST_CD) == null) {
                    msgMap.addXxMsgId(NFCM0508E);
                    return false;
                }
                // END 2017/08/08 H.Ikeda [QC#20428,Mod]
                String sellToCustCd = ssmRes.get(SELL_TO_CUST_CD).toString();
                if(!ZYPCommonFunc.hasValue(sellToCustCd)) {
                    msgMap.addXxMsgId(NFCM0508E);
                    return false;
                }

                Map<String, String> queryParam2 = new HashMap<String, String>();
                queryParam2.put("glblCmpyCd", param.glblCmpyCd.getValue());
                queryParam2.put("sellToCustCd", sellToCustCd);
                queryParam2.put("rgtnStsCd", this.arRgtnStsCd);

                Map ssmRes2 = (Map) ssmBatchClient.queryObject("checkAccount_Mode01", queryParam2);
                if(ssmRes2==null) {
                    msgMap.addXxMsgId(NFCM0508E);
                    res = false;
                } else {
                    // START 2017/08/08 H.Ikeda [QC#20428,Mod]
                    if (ssmRes2.get(AR_BAL_AMT) == null) {
                        msgMap.addXxMsgId(NFCM0508E);
                        return false;
                    }
                    // END 2017/08/08 H.Ikeda [QC#20428,Mod]
                    this.netArBalAmt = new BigDecimal(ssmRes2.get(AR_BAL_AMT).toString());
                    // START 2017/08/08 H.Ikeda [QC#20428,Mod]
                    if (ssmRes2.get(GRACE_PER_DAYS_AOT) == null) {
                        msgMap.addXxMsgId(NFCM0508E);
                        return false;
                    }
                    // END 2017/08/08 H.Ikeda [QC#20428,Mod]
                    this.gracePerDaysAot = new BigDecimal(ssmRes2.get(GRACE_PER_DAYS_AOT).toString());
                    // START 2017/08/08 H.Ikeda [QC#20428,Mod]
                    if (ssmRes2.get(OVD_WS_FLG) == null) {
                        msgMap.addXxMsgId(NFCM0508E);
                        return false;
                    }
                    // END 2017/08/08 H.Ikeda [QC#20428,Mod]
                    this.ovdWsFlg = ssmRes2.get("OVD_WS_FLG").toString();
                    // START 2017/08/08 H.Ikeda [QC#20428,Mod]
                    if (ssmRes2.get(OVD_PRT_FLG) == null) {
                        msgMap.addXxMsgId(NFCM0508E);
                        return false;
                    }
                    // END 2017/08/08 H.Ikeda [QC#20428,Mod]
                    this.ovdPrtFlg = ssmRes2.get("OVD_PRT_FLG").toString();
                    // START 2018/02/02 H.Ikeda [QC#22095,ADD]
                    if (ssmRes2.get(CONTR_GRACE_PER_DAYS_AOT) == null) {
                        msgMap.addXxMsgId(NFCM0508E);
                        return false;
                    }
                    this.contrGracePerDaysAot = new BigDecimal(ssmRes2.get(CONTR_GRACE_PER_DAYS_AOT).toString());
                    // END   2018/02/02 H.Ikeda [QC#22095,ADD]
                    param.xxModeCd.setValue(MODE_CUST_ACCT);
                    param.sellToCustCd.setValue(sellToCustCd);
                }

            } else {
                this.netArBalAmt = new BigDecimal(ssmRes.get(AR_BAL_AMT).toString());
                this.gracePerDaysAot = new BigDecimal(ssmRes.get(GRACE_PER_DAYS_AOT).toString());
                this.ovdWsFlg = ssmRes.get("OVD_WS_FLG").toString();
                this.ovdPrtFlg = ssmRes.get("OVD_PRT_FLG").toString();
                this.billtoCustPk = new BigDecimal(ssmRes.get(BILL_TO_CUST_PK).toString());
                this.selltoCustCd = ssmRes.get("SELL_TO_CUST_CD").toString();
                // START 2018/02/02 H.Ikeda [QC#22095,ADD]
                this.contrGracePerDaysAot = new BigDecimal(ssmRes.get(CONTR_GRACE_PER_DAYS_AOT).toString());
                // END   2018/02/02 H.Ikeda [QC#22095,ADD]
            }
        }
        
        return res;
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkAccount_Mode03(S21ApiMessageMap msgMap) {
        boolean res = true;
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("sellToCustCd", param.sellToCustCd.getValue());
        queryParam.put("rgtnStsCd", this.arRgtnStsCd);

        Map ssmRes = (Map) ssmBatchClient.queryObject("checkAccount_Mode03", queryParam);
//        if(ssmRes.isEmpty()) {
        if(ssmRes==null) {
//            msgMap.addXxMsgId(NFCM0508E);
//            res = false;
            res = false;
        } else {
            if(ssmRes.get(AR_BAL_AMT)==null) {
                msgMap.addXxMsgId(NFCM0508E);
                res = false;
            } else {
                this.netArBalAmt = new BigDecimal(ssmRes.get(AR_BAL_AMT).toString());
                this.gracePerDaysAot = new BigDecimal(ssmRes.get(GRACE_PER_DAYS_AOT).toString());
                this.ovdWsFlg = ssmRes.get("OVD_WS_FLG").toString();
                this.ovdPrtFlg = ssmRes.get("OVD_PRT_FLG").toString();
                this.billtoCustCd = ssmRes.get("BILL_TO_CUST_CD").toString();
                // START 2018/02/02 H.Ikeda [QC#22095,ADD]
                this.contrGracePerDaysAot = new BigDecimal(ssmRes.get(CONTR_GRACE_PER_DAYS_AOT).toString());
                // END   2018/02/02 H.Ikeda [QC#22095,ADD]
            }
        }
        
        return res;
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkTrx(S21ApiMessageMap msgMap) {
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
        if(param.xxModeCd.getValue().equals(MODE_CUST_ACCT)) {
            return checkTrx_Mode01(msgMap);
        } else {
            return checkTrx_Mode02(msgMap);
        }
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkTrx_Mode01(S21ApiMessageMap msgMap) {
        boolean res = true;
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

       this.skipCreditUpdate = false;
       
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("sellToCustCd", param.sellToCustCd.getValue());
        queryParam.put("applyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        queryParam.put("applyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put("arTrxTp_Rcpt", AR_TRX_TP.RECEIPT);
        // START 2016/09/01 K.Kojima [QC#10786,ADD]
        queryParam.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
        // END 2016/09/01 K.Kojima [QC#10786,ADD]
        // START 2018/01/30 E.Kameishi [QC#23681,ADD]
        queryParam.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
        // END 2018/01/30 E.Kameishi [QC#23681,ADD]

        Map ssmRes = (Map) ssmBatchClient.queryObject("checkTrx_Mode01", queryParam);
        if(ssmRes==null) {
            this.rmngBalGlssAmt = BigDecimal.ZERO;
        } else {
            if(ssmRes.get(SUM_AMT) == null) {
                this.rmngBalGlssAmt = BigDecimal.ZERO;
                if(this.netArBalAmt.compareTo(this.rmngBalGlssAmt)==0) {
                    this.skipCreditUpdate=true;
                }
                return true;
            }
            this.rmngBalGlssAmt = new BigDecimal(ssmRes.get(SUM_AMT).toString());
        }
        if(this.netArBalAmt.compareTo(this.rmngBalGlssAmt)==0) {
            this.skipCreditUpdate=true;
        }
        return res;
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkTrx_Mode02(S21ApiMessageMap msgMap) {
        boolean res = true;
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();

        this.skipCreditUpdate = false;

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());

        if(param.billToCustCd.getValue().isEmpty()) {
            queryParam.put("billToCustCd", this.billtoCustCd);
        } else {
            queryParam.put("billToCustCd", param.billToCustCd.getValue());
        }
        queryParam.put("applyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        queryParam.put("applyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put("arTrxTp_Rcpt", AR_TRX_TP.RECEIPT);
        // START 2016/09/01 K.Kojima [QC#10786,ADD]
        queryParam.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
        // END 2016/09/01 K.Kojima [QC#10786,ADD]
        // START 2018/01/30 E.Kameishi [QC#23681,ADD]
        queryParam.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
        // END 2018/01/30 E.Kameishi [QC#23681,ADD]

        Map ssmRes = (Map) ssmBatchClient.queryObject("checkTrx_Mode02", queryParam);
        if(ssmRes==null) {
            this.rmngBalGlssAmt = BigDecimal.ZERO;
        } else {
            if(ssmRes.get(SUM_AMT)==null) {
                this.rmngBalGlssAmt = BigDecimal.ZERO;
                if(this.netArBalAmt.compareTo(this.rmngBalGlssAmt)==0) {
                    this.skipCreditUpdate=true;
                }
                return true;
            }
            this.rmngBalGlssAmt = new BigDecimal(ssmRes.get(SUM_AMT).toString());
        }
        if(this.netArBalAmt.compareTo(this.rmngBalGlssAmt)==0) {
            this.skipCreditUpdate = true;
        }
        return res;
    }

    /**
     * 
     * @param msgMap
     * @param onBatchType
     * @return
     */
    private boolean updateCreditProfile(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();

        if(this.skipCreditUpdate) {
            return true;
        }

        if(param.xxModeCd.getValue().equals(MODE_CUST_ACCT)) {
            return updateCreditProfile_Mode01(msgMap, onBatchType);
        } else if(param.xxModeCd.getValue().equals(MODE_BILL_TO_CUST)) {
            return updateCreditProfile_Mode02(msgMap, onBatchType);
        } else {
            return updateCreditProfile_Mode02(msgMap, onBatchType);
        }
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean updateCreditProfile_Mode01(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        boolean res = true;
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
        
        NMZC600001 api = new NMZC600001();
        NMZC600001PMsg apiMsg = new NMZC600001PMsg();
        apiMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        apiMsg.xxModeCd.setValue("01");
        apiMsg.dsAcctNum.setValue(param.sellToCustCd.getValue());
        apiMsg.invAmt.setValue(BigDecimal.ZERO);


        //Def#5471
        //        BigDecimal rcptAmt = this.netArBalAmt.add(this.rmngBalGlssAmt.negate());
        BigDecimal rcptAmt = this.rmngBalGlssAmt.subtract(this.netArBalAmt);
        if(rcptAmt.compareTo(BigDecimal.ZERO)==0) {
            return true;
        }

        apiMsg.rcptAmt.setValue(rcptAmt);
        
        apiMsg.slsDt.setValue(param.procDt.getValue());
        apiMsg.xxReadOnlyFlg.setValue(ZYPConstant.FLG_OFF_N);
        
        api.execute(apiMsg, onBatchType);
        
        if(apiMsg.xxMsgIdList.getValidCount()>0) {
            for(int i=0; i<apiMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(apiMsg.xxMsgIdList.no(this.apiErrorCount).xxMsgId.getValue());
                this.apiErrorCount++;
            }
            return false;
        }

        return true;
    }

    /**
     * 
     * @param msgMap
     * @param onBatchType
     * @return
     */
    private boolean updateCreditProfile_Mode02(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        boolean res = true;
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
        
        NMZC600001 api = new NMZC600001();
        NMZC600001PMsg apiMsg = new NMZC600001PMsg();
        apiMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        apiMsg.xxModeCd.setValue("02");
        apiMsg.billToCustCd.setValue(param.billToCustCd.getValue());
        apiMsg.invAmt.setValue(BigDecimal.ZERO);

        //Def#5471
        //        BigDecimal rcptAmt = this.netArBalAmt.add(this.rmngBalGlssAmt.negate());
        BigDecimal rcptAmt = this.rmngBalGlssAmt.subtract(this.netArBalAmt);
        if(rcptAmt.compareTo(BigDecimal.ZERO)==0) {
            return true;
        }
        apiMsg.rcptAmt.setValue(rcptAmt);

        apiMsg.slsDt.setValue(param.procDt.getValue());
        apiMsg.xxReadOnlyFlg.setValue(ZYPConstant.FLG_OFF_N);
        
        api.execute(apiMsg, onBatchType);
        
        if(apiMsg.xxMsgIdList.getValidCount()>0) {
            for(int i=0; i<apiMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(apiMsg.xxMsgIdList.no(this.apiErrorCount).xxMsgId.getValue());
                this.apiErrorCount++;
            }
            return false;
        }

        return true;
    }
    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkOverDue(S21ApiMessageMap msgMap) {
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
        if(param.xxModeCd.getValue().equals(MODE_CUST_ACCT)) {
            return checkOverDue_Mode01(msgMap);
        } else if(param.xxModeCd.getValue().equals(MODE_BILL_TO_CUST)) {
            return checkOverDue_Mode02(msgMap);
        } else {
            return checkOverDue_Mode01(msgMap);
        }
    }
/**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkOverDue_Mode01(S21ApiMessageMap msgMap) {
        boolean res = true;
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("sellToCustCd", param.sellToCustCd.getValue());
        queryParam.put("applyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        queryParam.put("applyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put("arTrxTp_Inv", AR_TRX_TP.INVOICE);
        // START 2016/09/01 K.Kojima [QC#10786,ADD]
        queryParam.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
        // END 2016/09/01 K.Kojima [QC#10786,ADD]

        Map ssmRes = (Map) ssmBatchClient.queryObject("checkOverDue_Mode01", queryParam);
        if(ssmRes==null) {
            this.invDueDt = param.procDt.getValue();
        } else {
            if(ssmRes.get("INV_DUE_DT")==null) {
                this.invDueDt = param.procDt.getValue();
            } else {
                this.invDueDt = ssmRes.get("INV_DUE_DT").toString();
            }
        }
        
        return res;
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkOverDue_Mode02(S21ApiMessageMap msgMap) {
        boolean res = true;
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("billToCustCd", param.billToCustCd.getValue());
        queryParam.put("applyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        queryParam.put("applyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put("arTrxTp_Inv", AR_TRX_TP.INVOICE);
        // START 2016/09/01 K.Kojima [QC#10786,ADD]
        queryParam.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
        // END 2016/09/01 K.Kojima [QC#10786,ADD]

        Map ssmRes = (Map) ssmBatchClient.queryObject("checkOverDue_Mode02", queryParam);
        if(ssmRes==null) {
            this.invDueDt = param.procDt.getValue();
        } else {
            if(ssmRes.get("INV_DUE_DT")==null) {
                this.invDueDt = param.procDt.getValue();
            } else {
                this.invDueDt = ssmRes.get("INV_DUE_DT").toString();
            }
        }
        return res;
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean updateCreditProfile_DueFlag(S21ApiMessageMap msgMap) {
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
        if(param.xxModeCd.getValue().equals(MODE_CUST_ACCT)) {
            return updateDsAccountCreditProfile_Mode01(msgMap);
        } else if(param.xxModeCd.getValue().equals(MODE_BILL_TO_CUST)) {
            return updateCustCreditProfile_Mode02(msgMap);
        }
        return true;
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean updateDsAccountCreditProfile_Mode01(S21ApiMessageMap msgMap) {

        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
        
        // INV_DUE_DT + GRACE_PER_DAYS_AOT <> PROC_DT
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Integer.parseInt(this.invDueDt.substring(0, 4)), Integer.parseInt(this.invDueDt.substring(4, 6)) - 1, Integer.parseInt(this.invDueDt.substring(6, 8)));
        cal1.add(cal1.DAY_OF_MONTH, this.gracePerDaysAot.intValue());
        String _cal1 = calToString(cal1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Integer.parseInt(param.procDt.getValue().substring(0, 4)), Integer.parseInt(param.procDt.getValue().substring(4, 6)) - 1, Integer.parseInt(param.procDt.getValue().substring(6, 8)));
        String _cal2 = calToString(cal2);

        int diff = _cal1.compareTo(_cal2);
        String ovdFlg = ZYPConstant.FLG_ON_Y;
        if(diff >= 0) {
            ovdFlg = ZYPConstant.FLG_OFF_N;
        }

        if(this.ovdWsFlg.equals(ovdFlg) && this.ovdPrtFlg.equals(ovdFlg)) {
            return true;
        }

        /****************************************/
        /* Update DS_ACCT_CR_PRFL               */
        /****************************************/
        
        DS_ACCT_CR_PRFLTMsg dsAcctCrPrfl = new DS_ACCT_CR_PRFLTMsg();
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.dsAcctNum, param.sellToCustCd.getValue());

        dsAcctCrPrfl = (DS_ACCT_CR_PRFLTMsg) EZDTBLAccessor.findByKeyForUpdate(dsAcctCrPrfl);
        if(dsAcctCrPrfl!=null) {
            dsAcctCrPrfl.ovdWsFlg.setValue(ovdFlg);
            dsAcctCrPrfl.ovdPrtFlg.setValue(ovdFlg);

            EZDTBLAccessor.update(dsAcctCrPrfl);
        }
        return true;
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean updateCustCreditProfile_Mode02(S21ApiMessageMap msgMap) {

        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
        
        // INV_DUE_DT + GRACE_PER_DAYS_AOT <> PROC_DT
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Integer.parseInt(this.invDueDt.substring(0, 4)), Integer.parseInt(this.invDueDt.substring(4, 6)) - 1, Integer.parseInt(this.invDueDt.substring(6, 8)));
        cal1.add(cal1.DAY_OF_MONTH, this.gracePerDaysAot.intValue());
        String _cal1 = calToString(cal1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Integer.parseInt(param.procDt.getValue().substring(0, 4)), Integer.parseInt(param.procDt.getValue().substring(4, 6)) - 1, Integer.parseInt(param.procDt.getValue().substring(6, 8)));
        String _cal2 = calToString(cal2);

        int diff = _cal1.compareTo(_cal2);
        String ovdFlg = ZYPConstant.FLG_ON_Y;
        if(diff >= 0) {
            ovdFlg = ZYPConstant.FLG_OFF_N;
        }

        if(this.ovdWsFlg.equals(ovdFlg) && this.ovdPrtFlg.equals(ovdFlg)) {
            return true;
        }

        /***************************************/
        /* Update CUST_CR_PRFL                 */
        /***************************************/
        
        CUST_CR_PRFLTMsg custCrPrfl = new CUST_CR_PRFLTMsg();
        ZYPEZDItemValueSetter.setValue(custCrPrfl.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(custCrPrfl.billToCustPk, this.billtoCustPk);

        custCrPrfl = (CUST_CR_PRFLTMsg) EZDTBLAccessor.findByKeyForUpdate(custCrPrfl);
        if(custCrPrfl!=null) {
            custCrPrfl.ovdWsFlg.setValue(ovdFlg);
            custCrPrfl.ovdPrtFlg.setValue(ovdFlg);

            EZDTBLAccessor.update(custCrPrfl);
        }
        return true;
    }
/**
     * 
     * @param cal
     * @return
     */
    private String calToString(Calendar cal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(cal.getTime());
    }

    // START 2018/05/23 J.Kim [QC#26127,DEL]
    ///**
    // * 
    // * @param msgMap
    // * @return
    // */
    //private boolean updateContract(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
    //    NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
    //
    //    if(!updateDsContract_Mode01(msgMap,onBatchType, ZYPConstant.FLG_OFF_N)) {
    //        return false;
    //    }
    //    if(!updateDsContract_Mode01(msgMap,onBatchType, ZYPConstant.FLG_ON_Y)) {
    //        return false;
    //    }
    //    return true;
    //}
    // END 2018/05/23 J.Kim [QC#26127,DEL]

    // START 2018/05/23 J.Kim [QC#26127,DEL]
    // /**
    //  * 
    //  * @param msgMap
    //  * @param onBatchType
    //  * @return
    //  */
    // private boolean updateDsContract_Mode01(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, String overDueFlg) {
    //     boolean res = true;
    //     NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
    //
    //     S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    //
    //     S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
    //     ssmParam.setFetchSize(FETCH_SIZE);
    //     ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
    //     ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
    //     ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    //
    //     Map<String, String> queryParam = new HashMap<String, String>();
    //     queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
    //     if(param.sellToCustCd.getValue().isEmpty()) {
    //         queryParam.put("sellToCustCd", this.selltoCustCd);
    //     } else {
    //         queryParam.put("sellToCustCd", param.sellToCustCd.getValue());
    //     }
    //     queryParam.put("applyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
    //     queryParam.put("applyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
    //     queryParam.put("arTrxTp_Inv", AR_TRX_TP.INVOICE);
    //     queryParam.put("overDueFlg", overDueFlg);
    //     // START 2016/09/01 K.Kojima [QC#10786,ADD]
    //     queryParam.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
    //     // END 2016/09/01 K.Kojima [QC#10786,ADD]
    //
    //     Calendar cal = Calendar.getInstance();
    //     cal.set(Integer.parseInt(param.procDt.getValue().substring(0, 4)), Integer.parseInt(param.procDt.getValue().substring(4, 6)) - 1, Integer.parseInt(param.procDt.getValue().substring(6, 8)));
    //     // START 2018/02/02 H.Ikeda [QC#22095,MOD]
    //     //cal.add(cal.DAY_OF_MONTH, this.gracePerDaysAot.negate().intValue());
    //     cal.add(cal.DAY_OF_MONTH, this.contrGracePerDaysAot.negate().intValue());
    //     // END   2018/02/02 H.Ikeda [QC#22095,MOD]
    //     String _cal = calToString(cal);
    //     queryParam.put("procDt", _cal);
    //
    //     PreparedStatement stmt = null;
    //     ResultSet rs = null;
    //     BigDecimal dsContrPk = BigDecimal.ZERO;
    //     BigDecimal dsContrDtlPk = BigDecimal.ZERO;
    //     BigDecimal dsContrBllgMtrPk = BigDecimal.ZERO;
    //     BigDecimal dsContrPrcEffPk = BigDecimal.ZERO;
    //     
    //     String setFlg = ZYPConstant.FLG_ON_Y;
    //     if(overDueFlg.equals(ZYPConstant.FLG_ON_Y)) {
    //         setFlg = ZYPConstant.FLG_OFF_N;
    //     }
    //
    //    try {
    //         stmt = ssmLLClient.createPreparedStatement("updateDsContract_Mode01", queryParam, ssmParam);
    //         rs = stmt.executeQuery();
    //         
    //         int no = 0;
    //         while (rs.next()) {
    //             // START 2017/09/01 W.Honda [QC#20893,ADD]
    //  //             if(dsContrPk.compareTo(BigDecimal.ZERO)==0) {
    //             if(dsContrPk.compareTo(BigDecimal.ZERO)==0 || (dsContrPk.compareTo(rs.getBigDecimal(DS_CONTR_PK)) != 0)) {
    //             // END   2017/09/01 W.Honda [QC#20893,ADD]
    //                 // Header
    //                 dsContrPk = rs.getBigDecimal(DS_CONTR_PK);
    //
    //                 DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
    //                 dsContrTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
    //                 dsContrTMsg.dsContrPk.setValue(dsContrPk);
    //                 dsContrTMsg = (DS_CONTRTMsg)EZDTBLAccessor.findByKeyForUpdate(dsContrTMsg);
    //                 // START 2016/12/08 H.Ikeda [QC#15823,ADD]
    //                 if (dsContrTMsg == null) {
    //                     S21InfoLogOutput.println("findByKeyForUpdate No Data: DS_CONTR glblCmpyCd = " + param.glblCmpyCd.getValue() + " dsContrPk = " + dsContrPk);
    //                     throw new S21AbendException(NFZM0013E);
    //                 }
    //                 // END   2016/12/08 H.Ikeda [QC#15823,ADD]
    //                 dsContrTMsg.contrHldFlg.setValue(setFlg);
    //
    //                 EZDTBLAccessor.update(dsContrTMsg);
    //                 
    //                 // Contract Tracking API
    //                 NSZC077001 api = new NSZC077001();
    //                 NSZC077001PMsg apiMsg = new NSZC077001PMsg();
    //                 apiMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
    //                 apiMsg.xxModeCd.setValue("03");
    //                 apiMsg.dsContrTrkTpCd.setValue(DS_CONTR_TRK_TP.CONTRACT_HEADER);
    //                 apiMsg.dsContrPk.setValue(dsContrPk);
    //                 api.execute(apiMsg, onBatchType);
    //                 if(apiMsg.xxMsgIdList.getValidCount()>0) {
    //                     for(int i=0; i<apiMsg.xxMsgIdList.getValidCount(); i++) {
    //                         msgMap.addXxMsgId(apiMsg.xxMsgIdList.no(this.apiErrorCount).xxMsgId.getValue());
    //                         this.apiErrorCount++;
    //                     }
    //                     return false;
    //                 }
    //             }
    //             if(dsContrDtlPk.compareTo(BigDecimal.ZERO)==0 || dsContrDtlPk.compareTo(rs.getBigDecimal(DS_CONTR_DTL_PK))!=0) {
    //                 // Detail
    //                 dsContrDtlPk = rs.getBigDecimal(DS_CONTR_DTL_PK);
    //
    //                 DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
    //                 dsContrDtlTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
    //                 dsContrDtlTMsg.dsContrDtlPk.setValue(dsContrDtlPk);
    //                 dsContrDtlTMsg.dsContrPk.setValue(dsContrPk);
    //                 dsContrDtlTMsg = (DS_CONTR_DTLTMsg)EZDTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
    //                 // START 2016/12/08 H.Ikeda [QC#15823,ADD]
    //                 if (dsContrDtlTMsg == null) {
    //                     S21InfoLogOutput.println("findByKeyForUpdate No Data: DS_CONTR_DTL glblCmpyCd = " + param.glblCmpyCd.getValue() + " dsContrDtlPk = " + dsContrDtlPk + " dsContrPk = " + dsContrPk );
    //                     throw new S21AbendException(NFZM0013E);
    //                 }
    //                 // END   2016/12/08 H.Ikeda [QC#15823,ADD]
    //                 dsContrDtlTMsg.contrHldFlg.setValue(setFlg);
    //
    //                 EZDTBLAccessor.update(dsContrDtlTMsg);
    //
    //                 // Contract Tracking API
    //                 NSZC077001 api = new NSZC077001();
    //                 NSZC077001PMsg apiMsg = new NSZC077001PMsg();
    //                 apiMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
    //                 apiMsg.xxModeCd.setValue("03");
    //                 apiMsg.dsContrTrkTpCd.setValue(DS_CONTR_TRK_TP.BASE_CHARGE);
    //                 apiMsg.dsContrPk.setValue(dsContrPk);
    //                 apiMsg.dsContrDtlPk.setValue(dsContrDtlPk);
    //                 api.execute(apiMsg, onBatchType);
    //                 if(apiMsg.xxMsgIdList.getValidCount()>0) {
    //                     for(int i=0; i<apiMsg.xxMsgIdList.getValidCount(); i++) {
    //                         msgMap.addXxMsgId(apiMsg.xxMsgIdList.no(this.apiErrorCount).xxMsgId.getValue());
    //                         this.apiErrorCount++;
    //                     }
    //                     return false;
    //                 }
    //             }
    //             if(dsContrBllgMtrPk.compareTo(BigDecimal.ZERO)==0 || dsContrBllgMtrPk.compareTo(rs.getBigDecimal(DS_CONTR_BLLG_MTR_PK))!=0) {
    //                 // Meter
    //                 dsContrBllgMtrPk = rs.getBigDecimal(DS_CONTR_BLLG_MTR_PK);
    //
    //                 DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
    //                 dsContrBllgMtrTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
    //                 dsContrBllgMtrTMsg.dsContrBllgMtrPk.setValue(dsContrBllgMtrPk);
    //                 dsContrBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg)EZDTBLAccessor.findByKeyForUpdate(dsContrBllgMtrTMsg);
    //                 // START 2016/12/08 H.Ikeda [QC#15823,ADD]
    //                 if (dsContrBllgMtrTMsg == null) {
    //                     S21InfoLogOutput.println("findByKeyForUpdate No Data: DS_CONTR_BLLG_MTR glblCmpyCd = " + param.glblCmpyCd.getValue() + " dsContrBllgMtrPk = " + dsContrBllgMtrPk);
    //                     throw new S21AbendException(NFZM0013E);
    //                 }
    //                 // END   2016/12/08 H.Ikeda [QC#15823,ADD]
    //                 dsContrBllgMtrTMsg.contrHldFlg.setValue(setFlg);
    //                 
    //                 EZDTBLAccessor.update(dsContrBllgMtrTMsg);
    //
    //                 NSZC077001 api = new NSZC077001();
    //                 NSZC077001PMsg apiMsg = new NSZC077001PMsg();
    //                 apiMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
    //                 apiMsg.xxModeCd.setValue("03");
    //                 apiMsg.dsContrTrkTpCd.setValue(DS_CONTR_TRK_TP.USAGE_CHARGE);
    //                 apiMsg.dsContrPk.setValue(dsContrPk);
    //                 apiMsg.dsContrDtlPk.setValue(dsContrDtlPk);
    //                 apiMsg.dsContrBllgMtrPk.setValue(dsContrBllgMtrPk);
    //                 api.execute(apiMsg, onBatchType);
    //                 if(apiMsg.xxMsgIdList.getValidCount()>0) {
    //                     for(int i=0; i<apiMsg.xxMsgIdList.getValidCount(); i++) {
    //                         msgMap.addXxMsgId(apiMsg.xxMsgIdList.no(this.apiErrorCount).xxMsgId.getValue());
    //                         this.apiErrorCount++;
    //                     }
    //                     return false;
    //                 }
    //             }
    //             if(dsContrPrcEffPk.compareTo(BigDecimal.ZERO)==0 || dsContrPrcEffPk.compareTo(rs.getBigDecimal(DS_CONTR_PRC_EFF_PK))!=0) {
    //                 // Price
    //                 dsContrPrcEffPk = rs.getBigDecimal(DS_CONTR_PRC_EFF_PK);
    //                 
    //                 DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = new DS_CONTR_PRC_EFFTMsg();
    //                 dsContrPrcEffTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
    //                 dsContrPrcEffTMsg.dsContrPrcEffPk.setValue(dsContrPrcEffPk);
    //                 dsContrPrcEffTMsg = (DS_CONTR_PRC_EFFTMsg)EZDTBLAccessor.findByKeyForUpdate(dsContrPrcEffTMsg);
    //                 // START 2016/12/08 H.Ikeda [QC#15823,ADD]
    //                 if (dsContrPrcEffTMsg == null) {
    //                     S21InfoLogOutput.println("findByKeyForUpdate No Data: DS_CONTR_PRC_EFF glblCmpyCd = " + param.glblCmpyCd.getValue() + " dsContrPrcEffPk = " + dsContrPrcEffPk);
    //                     throw new S21AbendException(NFZM0013E);
    //                 }
    //                 // END   2016/12/08 H.Ikeda [QC#15823,ADD]
    //                 dsContrPrcEffTMsg.contrHldFlg.setValue(setFlg);
    //                 
    //                 EZDTBLAccessor.update(dsContrPrcEffTMsg);
    //
    //                 // Contract Tracking API
    //                 NSZC077001 api = new NSZC077001();
    //                 NSZC077001PMsg apiMsg = new NSZC077001PMsg();
    //                 apiMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
    //                 apiMsg.xxModeCd.setValue("03");
    //                 apiMsg.dsContrTrkTpCd.setValue(DS_CONTR_TRK_TP.CONTRACT_HEADER);
    //                 apiMsg.dsContrPk.setValue(dsContrPk);
    //                 apiMsg.dsContrDtlPk.setValue(dsContrDtlPk);
    //                 apiMsg.dsContrBllgMtrPk.setValue(dsContrBllgMtrPk);
    //                 apiMsg.dsContrPrcEffPk.setValue(dsContrPrcEffPk);
    //                 apiMsg.contrPrcEffFromDt.setValue(dsContrPrcEffTMsg.contrPrcEffFromDt.getValue());
    //                 apiMsg.contrPrcEffThruDt.setValue(dsContrPrcEffTMsg.contrPrcEffThruDt.getValue());
    //                 api.execute(apiMsg, onBatchType);
    //                 if(apiMsg.xxMsgIdList.getValidCount()>0) {
    //                     for(int i=0; i<apiMsg.xxMsgIdList.getValidCount(); i++) {
    //                         msgMap.addXxMsgId(apiMsg.xxMsgIdList.no(this.apiErrorCount).xxMsgId.getValue());
    //                         this.apiErrorCount++;
    //                     }
    //                     return false;
    //                 }
    //             }
    //         }
    //     } catch (SQLException e) {
    //         throw new S21AbendException(e, NFCM0503E); // Mod 2018/01/24 S21_NA#22420
    //     // ADD Start 2018/02/01 S21_NA#23880
    //     } finally {
    //         S21SsmLowLevelCodingClient.closeResource(stmt, rs);
    //     // ADD End 2018/02/01 S21_NA#23880
    //     }
    //     return res;
    // }
    // END 2018/05/23 J.Kim [QC#26127,DEL]

    private boolean processMode3(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("sellToCustCd", param.sellToCustCd.getValue());
        queryParam.put("rgtnStsCd", this.arRgtnStsCd);

        Boolean bRet = (Boolean) ssmBatchClient.queryObject("checkAccount_Mode03", queryParam, ssmParam , new mode3Handler(msgMap,onBatchType,this.apiErrorCount,this.invDueDt,this.billtoCustPk,this.selltoCustCd));
        return bRet;
    }
    private class mode3Handler extends S21SsmBooleanResultSetHandlerSupport {
        private BigDecimal netArBalAmt = BigDecimal.ZERO;
        private BigDecimal gracePerDaysAot = BigDecimal.ZERO;
        // START 2018/02/02 H.Ikeda [QC#22095,ADD]
        private BigDecimal contrGracePerDaysAot = BigDecimal.ZERO;
        // END   2018/02/02 H.Ikeda [QC#22095,ADD]
        private BigDecimal rmngBalGlssAmt = BigDecimal.ZERO;
        private String ovdWsFlg = null;
        private String ovdPrtFlg = null;
        private String billToCustCd = null;
        private boolean skipCreditUpdate = false;
        private NFZC202001PMsg param;
        private S21ApiMessageMap msgMap;
        private int apiErrorCount;
        private String invDueDt;
        private BigDecimal billtoCustPk;
        private String selltoCustCd;
        ONBATCH_TYPE onBatchType;
        private mode3Handler(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, int apiErrorCount, String invDueDt, BigDecimal billtoCustPk,String selltoCustCd) {
            this.msgMap = msgMap;
            this.param = (NFZC202001PMsg) msgMap.getPmsg();
            this.onBatchType = onBatchType;
            this.apiErrorCount = apiErrorCount;
            this.invDueDt = invDueDt;
            this.billtoCustPk = billtoCustPk;
            this.selltoCustCd = selltoCustCd;
        }
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            while (rs.next()) {
                this.netArBalAmt = rs.getBigDecimal(AR_BAL_AMT);
                this.gracePerDaysAot = rs.getBigDecimal(GRACE_PER_DAYS_AOT);
                this.ovdWsFlg = rs.getString(OVD_WS_FLG);
                this.ovdPrtFlg = rs.getString(OVD_PRT_FLG);
                this.billToCustCd = rs.getString(BILL_TO_CUST_CD);
                this.param.billToCustCd.setValue(this.billToCustCd);
                // START 2018/02/02 H.Ikeda [QC#22095,ADD]
                this.contrGracePerDaysAot = rs.getBigDecimal(CONTR_GRACE_PER_DAYS_AOT);
                // END   2018/02/02 H.Ikeda [QC#22095,ADD]

                // Check AR Transaction
                if(!checkTrx_Mode03()) {
                    msgMap.flush();
                    continue;
                }
                // Update Credit Profile
                if(!this.skipCreditUpdate) {
                    if(!updateCreditProfile_Mode03(msgMap,onBatchType)) {
                        msgMap.flush();
                        continue;
                    }
                }
                // Check Over Due Flag
                if(!checkOverDue_Mode03(msgMap)) {
                    msgMap.flush();
                    continue;
                }
                
                // Update Over Due Flag in Credit Profile
                if(!updateCustCreditProfile_Mode03(msgMap)) {
                    msgMap.flush();
                    continue;
                }
                
            }
            return true;
        }

        /**
         * 
         * @return
         */
        public boolean checkTrx_Mode03() {
            boolean res = true;
            this.skipCreditUpdate = false;

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());

            if(param.billToCustCd.getValue().isEmpty()) {
                queryParam.put("billToCustCd", this.billToCustCd);
            } else {
                queryParam.put("billToCustCd", param.billToCustCd.getValue());
            }
            queryParam.put("applyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
            queryParam.put("applyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
            queryParam.put("arTrxTp_Rcpt", AR_TRX_TP.RECEIPT);
            // START 2016/09/01 K.Kojima [QC#10786,ADD]
            queryParam.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
            // END 2016/09/01 K.Kojima [QC#10786,ADD]
            // START 2018/01/30 E.Kameishi [QC#23681,ADD]
            queryParam.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
            // END 2018/01/30 E.Kameishi [QC#23681,ADD]

            Map ssmRes = (Map) ssmBatchClient.queryObject("checkTrx_Mode02", queryParam);
            if(ssmRes==null) {
                this.rmngBalGlssAmt = BigDecimal.ZERO;
            } else {
                if(ssmRes.get(SUM_AMT)==null) {
                    this.rmngBalGlssAmt = BigDecimal.ZERO;
                    if(this.netArBalAmt.compareTo(this.rmngBalGlssAmt)==0) {
                        this.skipCreditUpdate=true;
                    }
                    return true;
                }
                this.rmngBalGlssAmt = new BigDecimal(ssmRes.get(SUM_AMT).toString());
            }
            if(this.netArBalAmt.compareTo(this.rmngBalGlssAmt)==0) {
                this.skipCreditUpdate = true;
            }
            return res;
        }

        /**
         * 
         * @param msgMap
         * @param onBatchType
         * @return
         */
        private boolean updateCreditProfile_Mode03(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
            boolean res = true;
            NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
            
            NMZC600001 api = new NMZC600001();
            NMZC600001PMsg apiMsg = new NMZC600001PMsg();
            apiMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            apiMsg.xxModeCd.setValue("02");
            apiMsg.billToCustCd.setValue(param.billToCustCd.getValue());
            apiMsg.invAmt.setValue(BigDecimal.ZERO);

            //Def#5471
            //        BigDecimal rcptAmt = this.netArBalAmt.add(this.rmngBalGlssAmt.negate());
            BigDecimal rcptAmt = this.rmngBalGlssAmt.subtract(this.netArBalAmt);
            apiMsg.rcptAmt.setValue(rcptAmt);

            apiMsg.slsDt.setValue(param.procDt.getValue());
            apiMsg.xxReadOnlyFlg.setValue(ZYPConstant.FLG_OFF_N);
            
            api.execute(apiMsg, onBatchType);
            
            if(apiMsg.xxMsgIdList.getValidCount()>0) {
                for(int i=0; i<apiMsg.xxMsgIdList.getValidCount(); i++) {
                    msgMap.addXxMsgId(apiMsg.xxMsgIdList.no(this.apiErrorCount).xxMsgId.getValue());
                    this.apiErrorCount++;
                }
                return false;
            }

            return true;
        }

        private boolean checkOverDue_Mode03(S21ApiMessageMap msgMap) {
            boolean res = true;
            NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            queryParam.put("billToCustCd", param.billToCustCd.getValue());
            queryParam.put("applyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
            queryParam.put("applyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
            queryParam.put("arTrxTp_Inv", AR_TRX_TP.INVOICE);
            // START 2016/09/01 K.Kojima [QC#10786,ADD]
            queryParam.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
            // END 2016/09/01 K.Kojima [QC#10786,ADD]

            Map ssmRes = (Map) ssmBatchClient.queryObject("checkOverDue_Mode02", queryParam);
            if(ssmRes==null) {
                this.invDueDt = param.procDt.getValue();
            } else {
                if(ssmRes.get("INV_DUE_DT")==null) {
                    this.invDueDt = param.procDt.getValue();
                } else {
                    this.invDueDt = ssmRes.get("INV_DUE_DT").toString();
                }
            }
            return res;
        }

        /**
         * 
         * @param msgMap
         * @return
         */
        private boolean updateCustCreditProfile_Mode03(S21ApiMessageMap msgMap) {

            NFZC202001PMsg param = (NFZC202001PMsg) msgMap.getPmsg();
            
            // INV_DUE_DT + GRACE_PER_DAYS_AOT <> PROC_DT
            Calendar cal1 = Calendar.getInstance();
            cal1.set(Integer.parseInt(this.invDueDt.substring(0, 4)), Integer.parseInt(this.invDueDt.substring(4, 6)) - 1, Integer.parseInt(this.invDueDt.substring(6, 8)));
            cal1.add(cal1.DAY_OF_MONTH, this.gracePerDaysAot.intValue());
            String _cal1 = calToString(cal1);
            
            Calendar cal2 = Calendar.getInstance();
            cal2.set(Integer.parseInt(param.procDt.getValue().substring(0, 4)), Integer.parseInt(param.procDt.getValue().substring(4, 6)) - 1, Integer.parseInt(param.procDt.getValue().substring(6, 8)));
            String _cal2 = calToString(cal2);

            int diff = _cal1.compareTo(_cal2);
            String ovdFlg = ZYPConstant.FLG_ON_Y;
            if(diff >= 0) {
                ovdFlg = ZYPConstant.FLG_OFF_N;
            }

            if(this.ovdWsFlg.equals(ovdFlg) && this.ovdPrtFlg.equals(ovdFlg)) {
                return true;
            }

            /***************************************/
            /* Update CUST_CR_PRFL                 */
            /***************************************/
            
            CUST_CR_PRFLTMsg custCrPrfl = new CUST_CR_PRFLTMsg();
            ZYPEZDItemValueSetter.setValue(custCrPrfl.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(custCrPrfl.billToCustPk, this.billtoCustPk);

            custCrPrfl = (CUST_CR_PRFLTMsg) EZDTBLAccessor.findByKeyForUpdate(custCrPrfl);
            if(custCrPrfl!=null) {
                custCrPrfl.ovdWsFlg.setValue(ovdFlg);
                custCrPrfl.ovdPrtFlg.setValue(ovdFlg);

                EZDTBLAccessor.update(custCrPrfl);
            }
            return true;
        }
    }
}
