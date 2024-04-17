/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NWB.NWBB400001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CTAC_PSNTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_PRC_DTLTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORDTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NFZC309001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateInputLinePMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040002PMsg;
import business.parts.NWZC040003PMsg;
import business.parts.NWZC040004PMsg;
import business.parts.NWZC040005PMsg;
import business.parts.NWZC040006PMsg;
import business.parts.NWZC056001PMsg;
import business.parts.NWZC056001_runningCountUpdateListPMsg;
import business.parts.NWZC188001PMsg;
import business.parts.NWZC188001_rmaLineListPMsg;

import com.canon.cusa.s21.api.NFC.NFZC309001.NFZC309001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC036101.NWZC036101;
import com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant;
import com.canon.cusa.s21.api.NWZ.NWZC040001.NWZC040001;
import com.canon.cusa.s21.api.NWZ.NWZC056001.NWZC056001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_CHRG_CPLT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PSN_DEPT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EASY_PACK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRINT_STYLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MDL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmListResultSetHandlerSupport;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/** <pre>
 * Credit and Return Finalize Batch.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/05/2015   Fujitsu         M.Hara          Create          N/A
 * 12/23/2015   Fujitsu         M.Hara          Update          QC#2216
 * 03/17/2016   Fujitsu         H.Nagashima     Update          QC#5545
 * 03/25/2016   Fujitsu         H.Nagashima     Update          QC#6049
 * 04/12/2016   Fujitsu         H.Nagashima     Update          QC#6868
 * 04/30/2016   SRAA            K.Aratani       Update          QC#7826
 * 05/26/2016   SRAA            K.Aratani       Update          QC#8890
 * 07/19/2016   SRAA            K.Aratani       Update          QC#12120
 * 09/02/2016   Fujitsu         H.Nagashima     Update          QC#10990
 * 12/07/2016   Fujitsu         H.Nagashima     Update          QC#16282
 * 12/20/2016   Fujitsu         H.Nagashima     Update          QC#16731
 * 02/16/2017   Fujitsu         H.Nagashima     Update          QC#17348
 * 03/01/2017   Fujitsu         H.Nagashima     Update          QC#16531
 * 07/21/2017   Fujitsu         M.Ohno          Update          QC#19929
 * 01/22/2018   Fujitsu         Mz.Takahashi    Update          QC#22105
 * 03/30/2018   Fujitsu         H.Nagashima     Update          QC#23559
 * 2018/05/21   Fujitsu         Y.Kanefusa      Update          S21_NA#21841
 * 06/05/2018   Fujitsu         H.Nagashima     Update          QC#25966
 * 06/22/2018   Fujitsu         H.Tomimatsu     Update          QC#26415
 * 08/21/2018   Fujitsu         T.Aoi           Update          QC#27443
 * 09/10/2018   Fujitsu         Mz.Takahashi    Update          QC#25236
 * 09/20/2018   Fujitsu         K.Ishizuka      Update          QC#28327
 * 10/02/2018   Fujitsu         M.Ohno          Update          QC#28383
 * 10/19/2018   Fujitsu         Y.Kanefusa      Update          S21_NA#28861
 * 11/15/2018   Fujitsu         K.Kato          Update          QC#27954
 * 11/15/2018   Fujitsu         Mz.Takahashi    Update          S21_NA#27299
 * 12/14/2018   Fujitsu         H.Nagashima     Update          QC#29630
 * 12/17/2018   Hitachi         T.Tomita        Update          QC#28383
 * 07/01/2019   Fujitsu         Hd.Sugawara     Update          QC#50984
 * 09/05/2019   Fujitsu         R.Nakamura      Update          QC#53297
 * 10/10/2019   Fujitsu         K.Kato          Update          QC#53596
 * 10/16/2019   Fujitsu         S.Yamamoto      Update          QC#54192
 * 01/11/2021   CITS            K.Ogino         Update          QC#58227
 * 05/11/2022   CITS            R.Azucena       Update          QC#59959
 * </pre>
 */
public class NWBB400001 extends S21BatchMain {

    /**
     *  Message IDs
     *
     */
    private static enum MSG_ID {
        /** [@] is mandatory. */
        ZZZM9025E,
        /** No search results found. */
        ZZOM0011E,
        /** @ ended abnormally. */
        NLBM0024E,
        /** Could not get the [@]. */
        NWBM0031E,
    }

    /**
     * Tax Calculation Type
     *
     */
    private static enum TAX_CALC_TYPE {
        /** Merchandise */
        Mdse,
        /** Freight */
        Freight,
    }

    /** search contact by bill */
    private static final String CONST_SEARCH_TARGET_BILL_TO = "BILL_TO";

    /** Tax Freight Item Code */
    private static final String TAX_FRT_ITEM_CD = "TAX_FRT_ITEM_CD";

    /** Set Item Parent Sub Number */
    private static final String FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM = "000";

    /** freight tax line number */
//    private static final String CONST_FRT_TAX_LINE_NUM = "*";

    /** RMA_LINE_NUM */
    private static final String RMA_LINE_NUM = "RMA_LINE_NUM";

    /** RMA_LINE_SUB_NUM */
    private static final String RMA_LINE_SUB_NUM = "RMA_LINE_SUB_NUM";

    /** SSM EZD Client */
    private S21SsmEZDClient   ssmEzdClient;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Program Name */
    private static final String PROGRAM_NM = "Credit and Return Finalize Batch.";

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** TAX_FRT_ITEM_CD */
    private String taxFrtItemCd = null;

    /** Mail template ID */
    private  String mailTemplateId = null;

    /** All Invoice Import API Bean */
    private LinkedHashMap<String, InvImptApiParamBean> mapInvImptApiBean = null;

    /** Running Toner API Error Message List.*/
    private List<String> runTnrApiErrMsgList = null;

    /** default SVC_ALLOC_TRX_TP_NM */
    private String defaultSvcAllocTrxTpNm = null;

    /** Set Item Line Sub Number */
    private static final String SET_ITEM_LINE_SUB_NUM = "000";

    // 2018/08/21 QC#27443 Add Start
    private static final int CONST_SPLY_PGM_UNIT_AMT_RATE_SCALE = 6;
    // 2018/08/21 QC#27443 Add End

    /** normal record count */
    private int normalRecCnt = 0;

    /** error record count */
    private int errRecCnt = 0;

    // 2018/11/15 S21_NA#27954 Add Start
    private static final String ORD_CATG_CTX_TP_SUPPLIES_ORDER = "SUPPLIES_ORDER";
    private static final String ORD_CATG_CTX_TP_EQUIPMENT_ORDER = "EQUIPMENT_ORDER";
    // 2018/11/15 S21_NA#27954 Add End

    // Mod Start 2019/07/01 QC#50984
    private static final String EVENT_ID_CLOSE = "Close";
    // Mod End 2019/07/01 QC#50984

    /**
     * @param args String[] batch parameters
    */
    public static void main(String[] args) {
        new NWBB400001().executeBatch();
    }

    @Override
    protected void initRoutine() {
        writeStartLogLn("initRoutine");

        try {
            this.ssmEzdClient   = S21SsmEZDClient.getClient(this.getClass());
            this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

            this.runTnrApiErrMsgList = new ArrayList<String>();

            // Global Company Code
            this.glblCmpyCd = getGlobalCompanyCode();
            if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
                throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
            }

            // Sales Date
            this.salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

            // Tax Freight Item Code
            this.taxFrtItemCd = ZYPCodeDataUtil.getVarCharConstValue(TAX_FRT_ITEM_CD, this.glblCmpyCd);

            if (this.taxFrtItemCd == null) {
                throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + TAX_FRT_ITEM_CD);
            }

            this.mailTemplateId = getUserVariable1();
            if (!ZYPCommonFunc.hasValue(mailTemplateId)) {
                throw new S21AbendException(MSG_ID.NWBM0031E.toString(), toArray("Mail Template ID"));
            }
        } finally {
            writeEndLogLn("initRoutine");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void mainRoutine() {
        writeStartLogLn("mainRoutine");

        try {
            // Search Target Data
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("slsDt", this.salesDate);
            ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES_RETURN);
            ssmParam.put("ordHdrSts", ORD_HDR_STS.VALIDATED);
            ssmParam.put("rwsStsRcv", RWS_STS.RECEIVED);
            ssmParam.put("invCmnt", TXT_TP.INVOICE_COMMENT);
            ssmParam.put("mdseTpSet", MDSE_TP.SET); // QC#26657

            ssmParam.put("rtrnLineStsCdBooked", RTRN_LINE_STS.BOOKED);
            ssmParam.put("rtrnLineStsCdReceived", RTRN_LINE_STS.RECEIVED);
            List<String> rtrnStsCdBccList = new ArrayList<String>();
            rtrnStsCdBccList.add(RTRN_LINE_STS.BOOKED);
            rtrnStsCdBccList.add(RTRN_LINE_STS.RECEIVED);
            rtrnStsCdBccList.add(RTRN_LINE_STS.CLOSED);
            rtrnStsCdBccList.add(RTRN_LINE_STS.CANCELLED);
            ssmParam.put("rtrnStsCdBccList", rtrnStsCdBccList.toArray(new String[0]));

            List<String> rtrnStsCdRccList = new ArrayList<String>();
            rtrnStsCdRccList.add(RTRN_LINE_STS.RECEIVED);
            rtrnStsCdRccList.add(RTRN_LINE_STS.CLOSED);
            rtrnStsCdRccList.add(RTRN_LINE_STS.CANCELLED);
            ssmParam.put("rtrnStsCdRccList", rtrnStsCdRccList.toArray(new String[0]));

            // S21_NA#27299 2018/11/15 Add Start
            ssmParam.put("crRebillcredit", CR_REBIL.CREDIT);
            // S21_NA#27299 2018/11/15 Add End

            String hdrGrpKey, stockHdrGrpKey;
            String shipGrpKey, stockShipGrpKey;

            mapInvImptApiBean = new LinkedHashMap<String, InvImptApiParamBean>();
            InvImptApiParamBean invImptApiBean = null;
            OrderCloseBeanListCreator beanCreator = new OrderCloseBeanListCreator();
            LinkedHashMap<String, List<OrderCloseBean>> mapGrouping = new LinkedHashMap<String, List<OrderCloseBean>>();
            List<OrderCloseBean> groupingList;
            Boolean isFirstLoop = true;
            hdrGrpKey = "";
            stockHdrGrpKey = "";
            stockShipGrpKey = "";
            OrderCloseBean prevBean = null;
            // 2018/12/14 QC#29630 add Start
            String invNum = "";
            // 2018/12/14 QC#29630 add End

            NWZC036101PMsg taxCaclHeaderPMsg = new NWZC036101PMsg();
            List<NWZC036101PMsg> taxcalcPMsgList = new ArrayList<NWZC036101PMsg>();
            boolean isSuccess = true;
            List<String> cpoOrdNumList = new ArrayList<String>();
            List<String> errGrpKeyList = new ArrayList<String>();

            List<OrderCloseBean> rslt = (List<OrderCloseBean>) ssmBatchClient.queryObjectList("getOrderCloseData", ssmParam, beanCreator);

            Map<String, List<Map<String, String>>> chgStsOrderMap = new HashMap<String, List<Map<String, String>>>();

            for (OrderCloseBean bean : rslt) {
                // QC#25236 2018/09/10 Add Start
                CTAC_PSNTMsg ctacPsnMsg = getBillAttCntctName(bean, this.salesDate);

                if (ctacPsnMsg != null) {
                    if (ZYPCommonFunc.hasValue(ctacPsnMsg.ctacPsnFirstNm)) {
                        bean.setBillToCtacPsnFirstNm(ctacPsnMsg.ctacPsnFirstNm.getValue());
                    }
                    if (ZYPCommonFunc.hasValue(ctacPsnMsg.ctacPsnMidNm)) {
                        bean.setBillToCtacPsnMidNm(ctacPsnMsg.ctacPsnMidNm.getValue());
                    }
                    if (ZYPCommonFunc.hasValue(ctacPsnMsg.ctacPsnLastNm)) {
                        bean.setBillToCtacPsnLastNm(ctacPsnMsg.ctacPsnLastNm.getValue());
                    }
                }
                // QC#25236 2018/09/10 Add End
                // *****************************************************************
                // Header Grouping
                // *****************************************************************
                hdrGrpKey = bean.getHeaderGroupingKey();
                groupingList = mapGrouping.get(hdrGrpKey);

                if (groupingList == null) {
                    groupingList = new ArrayList<OrderCloseBean>();
                    mapGrouping.put(hdrGrpKey, groupingList);
                }

                if (!stockHdrGrpKey.equals(hdrGrpKey)) {
                    if (!isFirstLoop) {

                        // 2019/10/10 QC#53596 Add Start
                        if (!cpoOrdNumList.isEmpty()) {
                            closeOrder(cpoOrdNumList);
                            cpoOrdNumList.clear();
                        }
                        if (!chgStsOrderMap.isEmpty()) {
                            callStsUpdApi(chgStsOrderMap);
                            chgStsOrderMap.clear();
                        }
                        // 2019/10/10 QC#53596 Mod End

                        // 2019/10/10 QC#53596 Mod Start
                        if (!errGrpKeyList.contains(stockHdrGrpKey)) {
                            if (invImptApiBean.isInvCratFlg()) {

                                // *****************************************************************
                                // Call Tax Calculation API
                                // *****************************************************************
                                isSuccess = callTaxCalcAPI(invImptApiBean, taxcalcPMsgList);
                                if (isSuccess) {
                                    invImptApiBean.setInvNum(invNum);   // 2018/12/14 QC#29630 add

                                    isSuccess = callInvoiceImportApi(invImptApiBean);
                                }
                            //} else {
                            //    isSuccess = !errGrpKeyList.contains(hdrGrpKey);
                            }
                        } else {
                            isSuccess = false;
                        }
                        // 2019/10/10 QC#53596 Mod End

                        if (isSuccess) {
                            commit();
                            normalRecCnt++;
                        } else {
                            rollback();
                            errRecCnt++;
                        }
                    }
                    // 2019/10/10 QC#53596 Mod Start
                    isSuccess = true;
                    // 2019/10/10 QC#53596 Mod End

                    taxcalcPMsgList = new ArrayList<NWZC036101PMsg>();
                    invImptApiBean = new InvImptApiParamBean();
                    mapInvImptApiBean.put(hdrGrpKey, invImptApiBean);

                    if (!bean.isRmaCreditFlag()) {
                        invImptApiBean.setInvCratFlg(false);
                    }
                    // *************************************************************
                    // Set Header Params
                    // *************************************************************
                    setHeaderParams(bean, invImptApiBean);
                    invNum = getInvNum(this.glblCmpyCd,bean.getCrDsInvTpCd()); // 2018/12/14 QC#29630 add
                    stockHdrGrpKey = hdrGrpKey;
                    stockShipGrpKey = "";
                }

                // QC#22105 add Start
                if (bean.isRmaCreditFlag()) {
                    invImptApiBean.setInvCratFlg(true);
                }
                // QC#22105 add End

                isFirstLoop = false;

                // QC#22105 mod Start
                if (bean.isRmaCreditFlag()) {
                //if (invImptApiBean.isInvCratFlg()) {    
                // QC#22105 mod End

                    // *****************************************************************
                    // Shipment Grouping
                    // *****************************************************************
                    shipGrpKey = bean.getShipmentGroupingKey();
                    if (!stockShipGrpKey.equals(shipGrpKey)) {
                        setShipParams(bean, invImptApiBean);
                        invImptApiBean.lineNumCnt = -1;
                        invImptApiBean.lineSubNumCnt = -1;

                        // *****************************************************************
                        // Set Tax(Freight) parameter
                        // *****************************************************************
                        taxCaclHeaderPMsg = setTaxCalcAPIHeaderParam(bean, taxcalcPMsgList, invNum);
                        // QC#21841 2018/05/21 Del Start
                        //setTaxCalcLineParam(bean, invImptApiBean, taxCaclHeaderPMsg, TAX_CALC_TYPE.Freight);
                        // QC#21841 2018/05/21 Del End

                        stockShipGrpKey = shipGrpKey;
                    }
                    // *****************************************************************
                    // Set Line parameter
                    // *****************************************************************
                    setLineParams(rslt, prevBean, bean, invImptApiBean);

                    // *****************************************************************
                    // Set Promotion parameter
                    // *****************************************************************
                    setPromotionParams(bean, invImptApiBean);

                    // *****************************************************************
                    // Set Serial parameter
                    // *****************************************************************
                    setSerialParams(bean, invImptApiBean);

                    // *****************************************************************
                    // Set Tax Calc API parameter(Merchandise)
                    // *****************************************************************
                    setTaxCalcLineParam(bean, invImptApiBean, taxCaclHeaderPMsg, TAX_CALC_TYPE.Mdse);

                    // *****************************************************************
                    // Set Line parameter For Charges
                    // *****************************************************************
                    List<Map<String, Object>> chargeList = getCalcBaseForCharges(bean);
                    List<NWZC040003PMsg> list = setChargeLineParam(chargeList, bean, invImptApiBean);

                    // *****************************************************************
                    // Set Promotion parameter for Charges
                    // *****************************************************************
                    setChagePromotionParam(list, bean, invImptApiBean);

                    // *****************************************************************
                    // Set Tax Calc API parameter for Charges
                    // *****************************************************************
                    setTaxCalcLineParam(list, bean, invImptApiBean, taxCaclHeaderPMsg);
                }

                // *****************************************************************
                // Update Machine Master(ALLOC_OFF)
                // *****************************************************************
                if (hasValue(bean.getSvcMachMstrPk())) {

                    // get SVC_MACH_MSTR.SVC_MACH_MAINT_AVAL_FLG
                    // 2019/10/10 QC#53596 Mod Start
                    //isSuccess = true;
                    boolean isSuccessUpdMachMstr = true;
                    // 2019/10/10 QC#53596 Mod End
                    SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
                    setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
                    setValue(svcMachMstrTMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
                    svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(svcMachMstrTMsg);

                    if (svcMachMstrTMsg == null) {
                        S21InfoLogOutput.println("NWBM0031E", new String[]{new SVC_MACH_MSTRTMsg().getTableName()});
                        // 2019/10/10 QC#53596 Mod Start
                        //isSuccess = false;
                        isSuccessUpdMachMstr = false;
                        // 2019/10/10 QC#53596 Mod End

                    } else if (ZYPConstant.FLG_OFF_N.equals(svcMachMstrTMsg.svcMachMaintAvalFlg.getValue())
                            && checkMacineMasterUpdateTarget(bean, svcMachMstrTMsg) // QC#17348 add
                            // START 2022/05/11 R.Azucena [QC#59959, ADD]
                            && SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())
                            && !isItemAllocated(svcMachMstrTMsg)
                            // END 2022/05/11 R.Azucena [QC#59959, ADD]
                            ) {
                        if (!updateMachMstr(bean)) {
                            // 2019/10/10 QC#53596 Mod Start
                            //isSuccess = false;
                            isSuccessUpdMachMstr = false;
                            // 2019/10/10 QC#53596 Mod End
                        }
                    }

                    // 2019/10/10 QC#53596 Mod Start
                    //if (!isSuccess) {
                    if (!isSuccessUpdMachMstr) {
                        errGrpKeyList.add(hdrGrpKey);
                        //invImptApiBean.setInvCratFlg(false);
                        continue;
                    }
                    // 2019/10/10 QC#53596 Mod End
                }

                // *****************************************************************
                // Close Detail
                // *****************************************************************
                // Mod Start 2019/07/01 QC#50984
                //cpoOrdNumList.add(closeRtnOrdLine(bean, chgStsOrderMap));
                String cpoOrdNum = closeRtnOrdLine(bean, chgStsOrderMap);
                if (!cpoOrdNumList.contains(cpoOrdNum)) {
                    cpoOrdNumList.add(cpoOrdNum);
                }
                // Mod End 2019/07/01 QC#50984

                // *****************************************************************
                // Contract Supply Return Check
                // *****************************************************************
                if (doRunTnrCntUpdateApi(bean)) {
                    // *****************************************************************
                    // Call Running Toner Count Update API
                    // *****************************************************************
                    callRunTnrCntUpApi(bean);
                }


                prevBean = bean;
            }

            // 2019/10/10 QC#53596 Add Start
            if (!cpoOrdNumList.isEmpty()) {
                closeOrder(cpoOrdNumList);
            }
            if (!chgStsOrderMap.isEmpty()) {
                callStsUpdApi(chgStsOrderMap);
            }
            // 2019/10/10 QC#53596 Mod End

            // 2019/10/10 QC#53596 Mod Start
            if (!errGrpKeyList.contains(stockHdrGrpKey)) {
                if (invImptApiBean != null && invImptApiBean.isInvCratFlg()) {

                    // *****************************************************************
                    // Call Tax Calculation API
                    // *****************************************************************
                    isSuccess = callTaxCalcAPI(invImptApiBean, taxcalcPMsgList);
                    if (isSuccess) {
                        invImptApiBean.setInvNum(invNum);   // 2018/12/14 QC#29630 add

                        isSuccess = callInvoiceImportApi(invImptApiBean);
                    }
                }
            } else {
                isSuccess = false;
            }
            // 2019/10/10 QC#53596 Mod End

            if (isSuccess) {
                commit();
                normalRecCnt++;
            } else {
                rollback();
                errRecCnt++;
            }

            // 2019/10/10 QC#53596 Del Start
            //if (cpoOrdNumList.size() > 0) {
            //    closeOrder(cpoOrdNumList);
            //}
            //// Call Change Status API
            //callStsUpdApi(chgStsOrderMap);
            // 2019/10/10 QC#53596 Del End

        } finally {
            writeEndLogLn("mainRoutine");
        }
    }

    @Override
    protected void termRoutine() {
        writeStartLogLn("termRoutine");

        try {

            TERM_CD termCd = TERM_CD.NORMAL_END;

            if (errRecCnt > 0) {
                termCd = TERM_CD.WARNING_END;
            }

            setTermState(termCd, this.normalRecCnt, this.errRecCnt, this.normalRecCnt + this.errRecCnt);

            if (errRecCnt > 0) {
                // post error mail.
                if (!postErrorMail()) {
                    throw new S21AbendException(MSG_ID.NLBM0024E.toString(), toArray(PROGRAM_NM + ":mail sending process"));
                }
            }
        } finally {
            writeEndLogLn("termRoutine");
        }
    }

    /**
     * postErrorMail
     * @return
     */
    private boolean postErrorMail() {
        writeStartLogLn("postErrorMail");

        try {
            final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();

            NWXC001001MailSubstituteString sbsStr;

            sbsStr = new NWXC001001MailSubstituteString();
            sbsStr.setSbstKey("batchId");
            sbsStr.setSbstStr(this.getClass().getSimpleName());
            sbsStrList.add(sbsStr);

            sbsStr = new NWXC001001MailSubstituteString();
            sbsStr.setSbstKey("batchNm");
            sbsStr.setSbstStr(PROGRAM_NM);
            sbsStrList.add(sbsStr);

            sbsStr = new NWXC001001MailSubstituteString();
            sbsStr.setSbstKey("batchProcLogId");
            sbsStr.setSbstStr(super.getBatchProcessLogID());
            sbsStrList.add(sbsStr);

            boolean isNormalEnd = new NWXC001001Mail().postMail(this.glblCmpyCd, "NWBB0020", mailTemplateId, sbsStrList);

            return isNormalEnd;
        } finally {
            writeEndLogLn("isNormalEnd");
        }

    }

    /**
     * closeOrder
     * @param cpoOrdNumList
     */
    private void closeOrder(List<String> cpoOrdNumList) {
        writeStartLogLn("closeOrder");

        try {
            Object objVal;
            CPOTMsg updMsg = new CPOTMsg();
            Map<String, Object> ssmParam;

            List<String> ordLnStsList = new ArrayList<String>();
            ordLnStsList.add(ORD_LINE_STS.CLOSED);
            ordLnStsList.add(ORD_LINE_STS.CANCELLED);

            List<String> rtrnStsCdBccList = new ArrayList<String>();
            rtrnStsCdBccList.add(RTRN_LINE_STS.CLOSED);
            rtrnStsCdBccList.add(RTRN_LINE_STS.CANCELLED);

            for (String cpoOrdNum : cpoOrdNumList) {
                ssmParam = new HashMap<String, Object>();

                ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                ssmParam.put("cpoOrdNum", cpoOrdNum);
                ssmParam.put("ordLnStsList", ordLnStsList.toArray(new String[0]));
                ssmParam.put("rtrnStsCdBccList", rtrnStsCdBccList.toArray(new String[0]));

                objVal = ssmBatchClient.queryObject("getCpoOrdNum", ssmParam);
                if (objVal != null) {
                    ZYPEZDItemValueSetter.setValue(updMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(updMsg.cpoOrdNum, cpoOrdNum);
                    ZYPEZDItemValueSetter.setValue(updMsg.ordHdrStsCd, ORD_HDR_STS.CLOSED);
                    ZYPEZDItemValueSetter.setValue(updMsg.openFlg, ZYPConstant.FLG_OFF_N);

                    EZDTBLAccessor.updateSelectionField(updMsg, new String[] {"ordHdrStsCd", "openFlg" });

                    // Process Log Output
                    S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();
                    bizProcLogMsg.setSubSysId("NWZ");
                    bizProcLogMsg.setProcId("OM");
                    // Mod Start 2019/07/01 QC#50984
                    //bizProcLogMsg.setEventId("Closed");
                    bizProcLogMsg.setEventId(EVENT_ID_CLOSE);
                    // Mod End 2019/07/01 QC#50984
                    bizProcLogMsg.setDocTpCd("OM");
                    bizProcLogMsg.setDocId("");
                    bizProcLogMsg.setPrntDocId(cpoOrdNum);
                    S21BusinessProcessLog.print(bizProcLogMsg);

                }
            }
        } finally {
            writeEndLogLn("closeOrder");
        }
    }

    /**
     * UpdateSetRatioKeepFlg
     * @param invImptApiBea
     */
    @SuppressWarnings("unchecked")
    private void updateSetRatioKeepFlg(InvImptApiParamBean invImptApiBea) {
        writeStartLogLn("updateSetRatioKeepFlg");

        try {
            // 1. Get Paretnt Set
            List<NWZC040003PMsg> parentSetList = new ArrayList<NWZC040003PMsg>();

            List<NWZC040003PMsg> invLineMsgList = invImptApiBea.getInvLinePMsgList();

            for (NWZC040003PMsg lineMsg : invLineMsgList) {
                if (FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM.equals(lineMsg.cpoDtlLineSubNum) && !ZYPCommonFunc.hasValue(lineMsg.setMdseCd)) {
                    parentSetList.add(lineMsg);
                }
            }

            // 2. Loop Parent
            Map<String, Object> ssmParam;
            List<Map<String, Object>> ssmResList;
            BigDecimal dtlParentQty, dtlQty, parentQty, qty;
            Boolean notMatch, allMatch;
            dtlParentQty = BigDecimal.ZERO;
            List<NWZC040003PMsg> keepList;

            for (NWZC040003PMsg lineMsg : parentSetList) {
                ssmParam = new HashMap<String, Object>();

                ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                ssmParam.put("cpoOrdNum", invImptApiBea.getInvHeaderPmsgList().get(0).cpoOrdNum);
                ssmParam.put("cpoDtlLineNum", lineMsg.cpoDtlLineNum);

                ssmResList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getChildSet", ssmParam);

                if (ssmResList != null && ssmResList.size() > 0) {
                    parentQty = convToBigDecimal(lineMsg.ordQty);
                    keepList = new ArrayList<NWZC040003PMsg>();

                    allMatch = true;
                    for (int i = 0; i < ssmResList.size(); i++) {
                        notMatch = true;
                        if (i == 0) {
                            dtlParentQty = convToBigDecimal(ssmResList.get(i).get("ORD_QTY"));
                            continue;
                        } else {
                            for (NWZC040003PMsg checkMsg : invLineMsgList) {
                                if (checkMsg.cpoDtlLineNum.getValue().equals(ssmResList.get(i).get("CPO_DTL_LINE_NUM"))
                                        && checkMsg.cpoDtlLineSubNum.getValue().equals(ssmResList.get(i).get("CPO_DTL_LINE_SUB_NUM"))) {
                                    dtlQty = convToBigDecimal(ssmResList.get(i).get("ORD_QTY"));
                                    qty = checkMsg.ordQty.getValue();

                                    if (dtlQty.divide(dtlParentQty) == qty.divide(parentQty)) {
                                        notMatch = false;
                                        keepList.add(checkMsg);
                                        break;
                                    }
                                }
                            }
                        }
                        if (notMatch) {
                            allMatch = false;
                            break;
                        }
                    }

                    if (allMatch) {
                        for (NWZC040003PMsg keepMsg : keepList) {
                            ZYPEZDItemValueSetter.setValue(keepMsg.setRatioKeepFlg, ZYPConstant.FLG_ON_Y);
                        }
                    }
                }
            }
        } finally {
            writeEndLogLn("updateSetRatioKeepFlg");
        }
    }

    /**
     * Return Order Line CLose
     * @param ordCloseBean
     * @return
     */
    private String closeRtnOrdLine(OrderCloseBean bean, Map<String, List<Map<String, String>>> chgStsOrderMap) {
        writeStartLogLn("closeRtnOrdLine");

        try {
            DS_CPO_RTRN_DTLTMsg updMsg;

            String cpoOrdNum = null;

            updMsg = new DS_CPO_RTRN_DTLTMsg();

            cpoOrdNum = bean.getCpoOrdNum();
            ZYPEZDItemValueSetter.setValue(updMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(updMsg.cpoOrdNum, cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(updMsg.dsCpoRtrnLineNum, bean.getDsCpoRtrnLineNum());
            ZYPEZDItemValueSetter.setValue(updMsg.dsCpoRtrnLineSubNum, bean.getDsCpoRtrnLineSubNum());
            ZYPEZDItemValueSetter.setValue(updMsg.rtrnLineStsCd, RTRN_LINE_STS.CLOSED);
            ZYPEZDItemValueSetter.setValue(updMsg.openFlg, ZYPConstant.FLG_OFF_N);

            EZDTBLAccessor.updateSelectionField(updMsg, new String[] {"rtrnLineStsCd", "openFlg" });

            // LineMap has RMA_LINE_NUM, RMA_LINE_SUB_NUM
            Map<String, String> lineMap = new HashMap<String, String>();
            lineMap.put(RMA_LINE_NUM, bean.getDsCpoRtrnLineNum());
            lineMap.put(RMA_LINE_SUB_NUM, bean.getDsCpoRtrnLineSubNum());

            if (chgStsOrderMap.containsKey(cpoOrdNum)) {
                // 
                List<Map<String, String>> lineNumList = chgStsOrderMap.get(cpoOrdNum);
                lineNumList.add(lineMap);

            } else {
                List<Map<String, String>> lineNumList = new ArrayList<Map<String, String>>();
                lineNumList.add(lineMap);

                chgStsOrderMap.put(cpoOrdNum, lineNumList);
            }

            // Process Log Output
            final S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();
            bizProcLogMsg.setSubSysId("NWZ");
            bizProcLogMsg.setProcId("OM");
            // Mod Start 2019/07/01 QC#50984
            //bizProcLogMsg.setEventId("RMA Closed");
            bizProcLogMsg.setEventId(EVENT_ID_CLOSE);
            // Mod End 2019/07/01 QC#50984
            bizProcLogMsg.setDocTpCd("RT");
            bizProcLogMsg.setDocId(bean.getDsCpoRtrnLineNum() + "." + bean.getDsCpoRtrnLineSubNum());
            bizProcLogMsg.setPrntDocId(cpoOrdNum);
            S21BusinessProcessLog.print(bizProcLogMsg);

            // 2018/10/02 QC#28383 add start
            if (ZYPConstant.FLG_OFF_N.equals(bean.getInvtyCtrlFlg()) && ZYPConstant.FLG_ON_Y.equals(bean.getInstlBaseCtrlFlg()) && !SVC_MDL_TP.SOFTWARE.equals(bean.getSvcMdlTpCd())) {
                NSZC001001 api = new NSZC001001();
                NSZC001001PMsg terminationPMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(terminationPMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(terminationPMsg.slsDt, salesDate);
                // Mod Start 2018/12/17 QC#28383
                // ZYPEZDItemValueSetter.setValue(terminationPMsg.xxModeCd, ProcessMode.UPDATE_TERMINATION.code);
                ZYPEZDItemValueSetter.setValue(terminationPMsg.xxModeCd, ProcessMode.RWS.code);
                // Mod End 2018/12/17 QC#28383
                ZYPEZDItemValueSetter.setValue(terminationPMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
                ZYPEZDItemValueSetter.setValue(terminationPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
                ZYPEZDItemValueSetter.setValue(terminationPMsg.effThruDt, salesDate);
                ZYPEZDItemValueSetter.setValue(terminationPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.GONE);
                ZYPEZDItemValueSetter.setValue(terminationPMsg.svcMachQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(terminationPMsg.svcConfigMstrPk, bean.getSvcConfigMstrPk());
                api.execute(terminationPMsg, ONBATCH_TYPE.BATCH);
                if (terminationPMsg.xxMsgIdList.getValidCount() > 0) {
                    for (int n = 0; n < terminationPMsg.xxMsgIdList.getValidCount(); n++) {
                        String errId = terminationPMsg.xxMsgIdList.no(n).xxMsgId.getValue();
                        S21InfoLogOutput.println(errId);
                    }
                }
            }
            // 2018/10/02 QC#28383 add end

            return cpoOrdNum;
        } finally {
            writeEndLogLn("closeRtnOrdLine");
        }
    }

    /**
     * callInvoiceImportApi
     * @param invImptApiBean
     * @return
     */
    private Boolean callInvoiceImportApi(InvImptApiParamBean invImptApiBean) {
        writeStartLogLn("callInvoiceImportApi");

        try {
            // Update Set Ratio Keep Flag
            updateSetRatioKeepFlg(invImptApiBean);

            NWZC040001 api = new NWZC040001();

            api.execute(invImptApiBean.getInvHeaderPmsgList()
                    , invImptApiBean.getShipmentsPMsgList()
                    , invImptApiBean.getInvLinePMsgList()
                    , invImptApiBean.getPromoPMsgList()
                    , invImptApiBean.getTaxDtlPMsgList()
                    , invImptApiBean.getSerNumPMsgList()
                    , invImptApiBean.getInvSlsCrPMsgList()
                    , ONBATCH_TYPE.BATCH);


            List<NWZC040001PMsg> invHeaderList = invImptApiBean.getInvHeaderPmsgList();
            for (NWZC040001PMsg invHeader : invHeaderList) {
                if (invHeader.xxMsgIdList.getValidCount() > 0) {
                    for (int n = 0; n < invHeader.xxMsgIdList.getValidCount(); n++) {
                        String errId = invHeader.xxMsgIdList.no(n).xxMsgId.getValue();
                        invImptApiBean.errMsgList.add(errId);
                        S21InfoLogOutput.println(errId);
                    }
                }
            }

            List<NWZC040002PMsg> shipmentList = invImptApiBean.getShipmentsPMsgList();
            for (NWZC040002PMsg shipment : shipmentList) {
                if (shipment.xxMsgIdList.getValidCount() > 0) {
                    for (int n = 0; n < shipment.xxMsgIdList.getValidCount(); n++) {
                        String errId = shipment.xxMsgIdList.no(n).xxMsgId.getValue();
                        invImptApiBean.errMsgList.add(errId);
                        S21InfoLogOutput.println(errId);
                    }
                }
            }

            List<NWZC040003PMsg> lineList = invImptApiBean.getInvLinePMsgList();
            for (NWZC040003PMsg line : lineList) {
                if (line.xxMsgIdList.getValidCount() > 0) {
                    for (int n = 0; n < line.xxMsgIdList.getValidCount(); n++) {
                        String errId = line.xxMsgIdList.no(n).xxMsgId.getValue();
                        invImptApiBean.errMsgList.add(errId);
                        S21InfoLogOutput.println(errId);
                    }
                }
            }

            List<NWZC040004PMsg> promoList = invImptApiBean.getPromoPMsgList();
            for (NWZC040004PMsg promo : promoList) {
                if (promo.xxMsgIdList.getValidCount() > 0) {
                    for (int n = 0; n < promo.xxMsgIdList.getValidCount(); n++) {
                        String errId = promo.xxMsgIdList.no(n).xxMsgId.getValue();
                        invImptApiBean.errMsgList.add(errId);
                        S21InfoLogOutput.println(errId);
                    }
                }
            }

            List<NWZC040005PMsg> taxList = invImptApiBean.getTaxDtlPMsgList();
            for (NWZC040005PMsg tax : taxList) {
                if (tax.xxMsgIdList.getValidCount() > 0) {
                    for (int n = 0; n < tax.xxMsgIdList.getValidCount(); n++) {
                        String errId = tax.xxMsgIdList.no(n).xxMsgId.getValue();
                        invImptApiBean.errMsgList.add(errId);
                        S21InfoLogOutput.println(errId);
                    }
                }
            }

            List<NWZC040006PMsg> serNumList = invImptApiBean.getSerNumPMsgList();
            for (NWZC040006PMsg serNum : serNumList) {
                if (serNum.xxMsgIdList.getValidCount() > 0) {
                    for (int n = 0; n < serNum.xxMsgIdList.getValidCount(); n++) {
                        String errId = serNum.xxMsgIdList.no(n).xxMsgId.getValue();
                        invImptApiBean.errMsgList.add(errId);
                        S21InfoLogOutput.println(errId);
                    }
                }
            }

            if (invImptApiBean.errMsgList.size() > 0) {
                return false;
            }
            updateInvoiceNumber(invImptApiBean);

            return true;

        } finally {
            writeEndLogLn("callInvoiceImportApi");
        }
    }

    private void updateInvoiceNumber(InvImptApiParamBean invImptApiBean) {

        List<NWZC040001PMsg> invHeaderList = invImptApiBean.getInvHeaderPmsgList();
        NWZC040001PMsg invHeaderPMsg = invHeaderList.get(0);
        String cpoOrdNum = invHeaderPMsg.cpoOrdNum.getValue();
        String invNum = invHeaderPMsg.invNum.getValue();

        List<NWZC040003PMsg> lineList = invImptApiBean.getInvLinePMsgList();
        for (NWZC040003PMsg linePMsg : lineList) {
            String lineNum = linePMsg.cpoDtlLineNum.getValue();
            String lineSubNum = linePMsg.cpoDtlLineSubNum.getValue();
            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
            setValue(dsCpoRtrnDtlTMsg.glblCmpyCd,           glblCmpyCd);
            setValue(dsCpoRtrnDtlTMsg.cpoOrdNum,            cpoOrdNum);
            setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum,     lineNum);
            setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum,  lineSubNum);
            setValue(dsCpoRtrnDtlTMsg.invNum,               invNum);
            S21ApiTBLAccessor.updateSelectionField(dsCpoRtrnDtlTMsg, new String[]{"invNum"});

        }

    }

    /**
     * Call Running Toner Count Update API
     */
    private Boolean callRunTnrCntUpApi(OrderCloseBean bean) {
        writeStartLogLn("callRunTnrCntUpApi");

        try {
            NWZC056001 api = new NWZC056001();
            NWZC056001PMsg apiMsg = new NWZC056001PMsg();
            NWZC056001_runningCountUpdateListPMsg apilistMsg = apiMsg.runningCountUpdateList.no(0);
            ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(apiMsg.slsDt, this.salesDate);

            ZYPEZDItemValueSetter.setValue(apilistMsg.dsContrNum, bean.getDsContrNum());
            ZYPEZDItemValueSetter.setValue(apilistMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
            ZYPEZDItemValueSetter.setValue(apilistMsg.mdseCd, bean.getMdseCd());
            ZYPEZDItemValueSetter.setValue(apilistMsg.xxRqstQty, bean.getRtrnQty());
            apiMsg.runningCountUpdateList.setValidCount(1);

            api.execute(apiMsg, ONBATCH_TYPE.BATCH);

            if (apiMsg.xxMsgIdList.getValidCount() > 0) {
                for (int n = 0; n < apiMsg.xxMsgIdList.getValidCount(); n++) {
                    String errId = apiMsg.xxMsgIdList.no(n).xxMsgId.getValue();
                    this.runTnrApiErrMsgList.add(errId);
                    S21InfoLogOutput.println(errId);
                }
            }

            return true;

        } finally {
            writeEndLogLn("callRunTnrCntUpApi");
        }
    }


    /**
     * Contract Supply Return Check
     */
    private Boolean doRunTnrCntUpdateApi(OrderCloseBean bean) {

        if (ZYPCommonFunc.hasValue(bean.getDsContrNum()) //
                && !ZYPCommonFunc.hasValue(bean.getCrRebilCd()) //
//                && IMG_SPLY_TP.TONER.equals(bean.getImgSplyTpCd())    //QC#25966 del
                ) {
            return true;
        }

        return false;
    }


    /**
     * setSerialParams
     * @param bean
     * @param invImptApiBean
     * @return
     */
    @SuppressWarnings("unchecked")
    private Boolean setSerialParams(OrderCloseBean bean, InvImptApiParamBean invImptApiBean) {
        writeStartLogLn("setSerialParams");

        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("trxOrdNum", bean.getCpoOrdNum());
            ssmParam.put("trxLineNum", bean.getDsCpoRtrnLineNum());
            ssmParam.put("trxLineSubNum", bean.getDsCpoRtrnLineSubNum());

            List<String> serNoList = (List<String>) ssmBatchClient.queryObjectList("getSerNum", ssmParam);

            if (serNoList == null) {
                return false;
            }

            NWZC040006PMsg param;

            for (String serNum : serNoList) {
                param = new NWZC040006PMsg();

                // #Global Company Code(FK)
                ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
                // Invoice BOL Line Number(FK)
                ZYPEZDItemValueSetter.setValue(param.invBolLineNum, invImptApiBean.getLastPmtInvBolLineNum());
                // Invoice Line Number(FK)
                //ZYPEZDItemValueSetter.setValue(param.invLineNum, invImptApiBean.getLineNumCntStr());
                ZYPEZDItemValueSetter.setValue(param.invLineNum, getLineNumCntStr(bean.getLineNum()));
                // Invoice Line Sub Number(FK)
                //ZYPEZDItemValueSetter.setValue(param.invLineSubNum, invImptApiBean.getLineSubNumCntStr());
                ZYPEZDItemValueSetter.setValue(param.invLineSubNum, getLineSubNumCntStr(bean.getLineSubNum()));
                // Serial Number
                ZYPEZDItemValueSetter.setValue(param.serNum, serNum);

                invImptApiBean.getSerNumPMsgList().add(param);
            }

            return true;
        } finally {
            writeEndLogLn("setSerialParams");
        }
    }


    /**
     * setPromotionParams
     * @param bean
     * @param invImptApiBean
     * @return
     */
    @SuppressWarnings("unchecked")
    private Boolean setPromotionParams(OrderCloseBean bean, InvImptApiParamBean invImptApiBean) {
        writeStartLogLn("setPromotionParams");

        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("cpoOrdNum", bean.getCpoOrdNum());
            ssmParam.put("dsCpoRtrnLineNum", bean.getDsCpoRtrnLineNum());
            ssmParam.put("dsCpoRtrnLineSubNum", bean.getDsCpoRtrnLineSubNum());

            List<DS_CPO_RTRN_PRC_DTLTMsg> dsCpoRtrnPrcDtlList = (List<DS_CPO_RTRN_PRC_DTLTMsg>) ssmBatchClient.queryObjectList("getDsCpoRtnPrcDtl", ssmParam);

            if (dsCpoRtrnPrcDtlList == null) {
                return false;
            }

            NWZC040004PMsg param;

            for (DS_CPO_RTRN_PRC_DTLTMsg dsCpoRtrnPrcDtl : dsCpoRtrnPrcDtlList) {
                param = new NWZC040004PMsg();

                // Global Company Code(FK)
                ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
                // Invoice BOL Line Number(FK)
                ZYPEZDItemValueSetter.setValue(param.invBolLineNum, invImptApiBean.getLastPmtInvBolLineNum());
                // Invoice Line Number(FK)
                //ZYPEZDItemValueSetter.setValue(param.invLineNum, invImptApiBean.getLineNumCntStr());
                ZYPEZDItemValueSetter.setValue(param.invLineNum, getLineNumCntStr(bean.getLineNum()));
                // Invoice Line Sub Number(FK)
                //ZYPEZDItemValueSetter.setValue(param.invLineSubNum, invImptApiBean.getLineSubNumCntStr());
                ZYPEZDItemValueSetter.setValue(param.invLineSubNum, getLineSubNumCntStr(bean.getLineSubNum()));
                // Promotion Category Primary Key
                ZYPEZDItemValueSetter.setValue(param.prmoCatgPk, dsCpoRtrnPrcDtl.prmoCatgPk);
                // Promotion Primary Key(FK)
                ZYPEZDItemValueSetter.setValue(param.prmoPk, dsCpoRtrnPrcDtl.prmoPk);
                // Deal Primary Key
                ZYPEZDItemValueSetter.setValue(param.dealPk, dsCpoRtrnPrcDtl.dealPk);
                // Pricing Date
                ZYPEZDItemValueSetter.setValue(param.prcDt, dsCpoRtrnPrcDtl.prcDt);
                // Promotion Quantity
                ZYPEZDItemValueSetter.setValue(param.prmoQty, changeSign(dsCpoRtrnPrcDtl.shipUnitQty.getValue()));
                // Deal Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.dealUnitPrcAmt, dsCpoRtrnPrcDtl.dealUnitPrcAmt);
                // Deal Last Net Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.dealLastNetUnitPrcAmt, dsCpoRtrnPrcDtl.dealLastNetUnitPrcAmt);
                // Deal Net Amount
                ZYPEZDItemValueSetter.setValue(param.dealNetAmt, changeSign(dsCpoRtrnPrcDtl.dealNetAmt.getValue()));
                // Deal Discount Amount
                ZYPEZDItemValueSetter.setValue(param.dealDiscAmt, changeSign(dsCpoRtrnPrcDtl.dealDiscAmt.getValue()));
                // Deal Promotion Net Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.dealPrmoNetUnitPrcAmt, changeSign(dsCpoRtrnPrcDtl.dealPrmoNetUnitPrcAmt.getValue()));
                // Deal Per Unit Fix Amount
                ZYPEZDItemValueSetter.setValue(param.dealPerUnitFixAmt, changeSign(dsCpoRtrnPrcDtl.dealPerUnitFixAmt.getValue()));
                // Deal Sales Percent Number
                ZYPEZDItemValueSetter.setValue(param.dealSlsPctNum, BigDecimal.ZERO);
                // Function Per Unit Fix Amount
                ZYPEZDItemValueSetter.setValue(param.funcPerUnitFixAmt, changeSign(dsCpoRtrnPrcDtl.funcPerUnitFixAmt.getValue()));
                // Function Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.funcUnitPrcAmt, dsCpoRtrnPrcDtl.funcUnitPrcAmt);
                // Function Last Net Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.funcLastNetUnitPrcAmt, dsCpoRtrnPrcDtl.funcLastNetUnitPrcAmt);
                // Function Net Amount
                ZYPEZDItemValueSetter.setValue(param.funcNetAmt, changeSign(dsCpoRtrnPrcDtl.funcNetAmt.getValue()));
                // Function Discount Amount
                ZYPEZDItemValueSetter.setValue(param.funcDiscAmt, changeSign(dsCpoRtrnPrcDtl.funcDiscAmt.getValue()));
                // Function Promotion Net Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.funcPrmoNetUnitPrcAmt, changeSign(dsCpoRtrnPrcDtl.funcPrmoNetUnitPrcAmt.getValue()));
                // COA Account Code(FK)
                ZYPEZDItemValueSetter.setValue(param.coaAcctCd, dsCpoRtrnPrcDtl.coaAcctCd);
                // Invoice Promotion Information Sequence Number
                ZYPEZDItemValueSetter.setValue(param.invPrmoInfoSqNum, "001");

                invImptApiBean.getPromoPMsgList().add(param);
            }

            return true;
        } finally {
            writeEndLogLn("setPromotionParams");
        }
    }

    /**
     * setLineParams
     * @param rslt
     * @param prevbean
     * @param bean
     * @param invImptApiBean
     * @return
     */
    private Boolean setLineParams(List<OrderCloseBean> rslt, OrderCloseBean prevbean, OrderCloseBean bean, InvImptApiParamBean invImptApiBean) {
        writeStartLogLn("setLineParams");

        try {
            // *********************************************************************
            // Get Order Number/Sub Number
            // *********************************************************************
            int lineNum = invImptApiBean.lineNumCnt;
            int lineSubNum = invImptApiBean.lineSubNumCnt;

            if (prevbean == null || invImptApiBean.lineNumCnt < 0) {
                lineNum = 1;
                if (SET_ITEM_LINE_SUB_NUM.equals(bean.getDsCpoRtrnLineSubNum())) {
                    lineSubNum = 0;
                } else {
                    lineSubNum = 1;
                }
                invImptApiBean.lineNumCnt = lineNum;
                invImptApiBean.lineSubNumCnt = lineSubNum;
            } else if (prevbean.getCpoOrdNum().equals(bean.getCpoOrdNum())
                    && prevbean.getDsCpoRtrnLineNum().equals(bean.getDsCpoRtrnLineNum())) {
                lineNum = prevbean.getLineNum();
                lineSubNum = prevbean.getLineSubNum();
                if (!prevbean.getDsCpoRtrnLineSubNum().equals(bean.getDsCpoRtrnLineSubNum())) {
                    lineSubNum++;
                }
            } else {
                lineNum++;
                if (!prevbean.getDsCpoRtrnLineNum().equals(bean.getDsCpoRtrnLineNum())) {
                    if (SET_ITEM_LINE_SUB_NUM.equals(bean.getDsCpoRtrnLineSubNum())) {
                        lineSubNum = 0;
                    } else {
                        lineSubNum = 1;
                    }
                } else if (!prevbean.getDsCpoRtrnLineSubNum().equals(bean.getDsCpoRtrnLineSubNum())) {
                    lineSubNum++;
                }
                invImptApiBean.lineNumCnt = lineNum;
                invImptApiBean.lineSubNumCnt = lineSubNum;
            }

            //invImptApiBean.lineNumCnt = lineNum;
            //invImptApiBean.lineSubNumCnt = lineSubNum;

            // *********************************************************************
            // Set Line Params
            // *********************************************************************
            NWZC040003PMsg param = new NWZC040003PMsg();

            // Global Company Code(FK)
            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
            // #Invoice BOL Line Number(FK)
            ZYPEZDItemValueSetter.setValue(param.invBolLineNum, invImptApiBean.getLastPmtInvBolLineNum());
            // #Invoice Line Number
            //ZYPEZDItemValueSetter.setValue(param.invLineNum, invImptApiBean.getLineNumCntStr());
            ZYPEZDItemValueSetter.setValue(param.invLineNum, getLineNumCntStr(lineNum));
            // #Invoice Line Sub Number
            //ZYPEZDItemValueSetter.setValue(param.invLineSubNum, invImptApiBean.getLineSubNumCntStr());
            ZYPEZDItemValueSetter.setValue(param.invLineSubNum, getLineSubNumCntStr(lineSubNum));
            // CPO Detail Line Number
            ZYPEZDItemValueSetter.setValue(param.cpoDtlLineNum, bean.getDsCpoRtrnLineNum());
            // CPO Detail Line Sub Number
            ZYPEZDItemValueSetter.setValue(param.cpoDtlLineSubNum, bean.getDsCpoRtrnLineSubNum());
            // Stock Status Code(FK)
            ZYPEZDItemValueSetter.setValue(param.stkStsCd, bean.getStkStsCd());
            // Sales Rep TOC Code(FK)
            ZYPEZDItemValueSetter.setValue(param.slsRepTocCd, bean.getSlsRepTocCd());
            // Merchandise Code(FK)
            ZYPEZDItemValueSetter.setValue(param.mdseCd, bean.getMdseCd());
            // Merchandise Name
            ZYPEZDItemValueSetter.setValue(param.mdseNm, bean.getMdseNm());
            // COA Product Code
            ZYPEZDItemValueSetter.setValue(param.coaProdCd, bean.getCoaProdCd());
            // Transaction Code(FK)
            ZYPEZDItemValueSetter.setValue(param.trxCd, bean.getTrxCd());
            // Transaction Reason Code(FK)
            ZYPEZDItemValueSetter.setValue(param.trxRsnCd, bean.getTrxRsnCd());
            // Order Quantity
            ZYPEZDItemValueSetter.setValue(param.ordQty, changeSign(bean.getOrdQty()));
            // Shipped Quantity
            ZYPEZDItemValueSetter.setValue(param.shipQty, changeSign(bean.getRtrnQty()));
            // Back Order Quantity
            ZYPEZDItemValueSetter.setValue(param.boQty, BigDecimal.ZERO);
            // Manual Pricing Flag
            ZYPEZDItemValueSetter.setValue(param.manPrcFlg, bean.getManPrcFlg());
            // Deal Net Unit Price Amount
            ZYPEZDItemValueSetter.setValue(param.dealNetUnitPrcAmt, bean.getDealNetUnitPrcAmt());
            // Invoice Line Deal Net Amount
            ZYPEZDItemValueSetter.setValue(param.invLineDealNetAmt, changeSign(bean.getCpoDtlDealNetAmt()));
            // Deal Unit Discount Price Amount
            ZYPEZDItemValueSetter.setValue(param.dealDiscUnitPrcAmt, changeSign(bean.getDealDiscAmt()));
            // Func Net Unit Price Amount
            ZYPEZDItemValueSetter.setValue(param.funcNetUnitPrcAmt, changeSign(bean.getFuncNetUnitPrcAmt()));
            // Invoice Line Func Net Amount
            ZYPEZDItemValueSetter.setValue(param.invLineFuncNetAmt, changeSign(bean.getCpoDtlFuncNetAmt()));
            // Func Unit Discount Price Amount
            ZYPEZDItemValueSetter.setValue(param.funcDiscUnitPrcAmt, changeSign(bean.getFuncDiscAmt()));
            // Tax Flag
            ZYPEZDItemValueSetter.setValue(param.taxFlg, bean.getTaxFlg());
            // COA Account Code(FK)
            ZYPEZDItemValueSetter.setValue(param.coaAcctCd, bean.getCoaAcctCd());
            // COA Project Code(FK)
            ZYPEZDItemValueSetter.setValue(param.coaProjCd, bean.getCoaProjCd());
            // Set Merchandise Code(FK)
            ZYPEZDItemValueSetter.setValue(param.setMdseCd, bean.getSetMdseCd());
            // Deal Gross Unit Price Amount
            ZYPEZDItemValueSetter.setValue(param.dealGrsUnitPrcAmt, bean.getDealPrcListPrcAmt());
            // Deal Gross Total Price Amount
            ZYPEZDItemValueSetter.setValue(param.dealGrsTotPrcAmt, changeSign(bean.getCpoDtlDealSlsAmt()));
            // Func Gross Unit Price Amount
            ZYPEZDItemValueSetter.setValue(param.funcGrsUnitPrcAmt, bean.getFuncPrcListPrcAmt());
            // Func Gross Total Price Amount
            ZYPEZDItemValueSetter.setValue(param.funcGrsTotPrcAmt, changeSign(bean.getCpoDtlFuncSlsAmt()));
            // Set Ratio Keep Flag(Default Value Set. Update Last)
            ZYPEZDItemValueSetter.setValue(param.setRatioKeepFlg, ZYPConstant.FLG_OFF_N);
            // Machine Config Number
            ZYPEZDItemValueSetter.setValue(param.svcConfigMstrPk, bean.getMchnConfigNum());
            // Direct Sales Contract Number
            ZYPEZDItemValueSetter.setValue(param.dsContrNum, bean.getDsContrNum());
            // Direct Sales Contract Squence Number
            ZYPEZDItemValueSetter.setValue(param.dsContrSqNum, bean.getDsContrSqNum());
            // Billing Attribute Value Text 1
            ZYPEZDItemValueSetter.setValue(param.firstBllgAttrbValTxt, bean.getFirstBllgAttrbValTxt());
            // Billing Attribute Value Text 2
            ZYPEZDItemValueSetter.setValue(param.scdBllgAttrbValTxt, bean.getScdBllgAttrbValTxt());
            // Billing Attribute Value Text 3
            ZYPEZDItemValueSetter.setValue(param.thirdBllgAttrbValTxt, bean.getThirdBllgAttrbValTxt());
            // Billing Attribute Value Text 4
            ZYPEZDItemValueSetter.setValue(param.frthBllgAttrbValTxt, bean.getFrthBllgAttrbValTxt());
            // Billing Attribute Value Text 5
            ZYPEZDItemValueSetter.setValue(param.fifthBllgAttrbValTxt, bean.getFifthBllgAttrbValTxt());
            // Billing Attribute Value Text 6
            ZYPEZDItemValueSetter.setValue(param.sixthBllgAttrbValTxt, bean.getSixthBllgAttrbValTxt());
            // Unit of Measure Code
            ZYPEZDItemValueSetter.setValue(param.uomCd, bean.getCustUomCd());
            // Tax Calculatted Geo Code+
            NWZC036101_taxCalculateOutputLinePMsg lastMdseTaxMsg = invImptApiBean.getLastMdseTaxResult();
            if (lastMdseTaxMsg != null) {
                ZYPEZDItemValueSetter.setValue(param.taxCalcGeoCd, lastMdseTaxMsg.taxAreaId);
            }
            // DS CPO Position Number
            ZYPEZDItemValueSetter.setValue(param.dsOrdPosnNum, bean.getDsOrdPosnNum());
            // DS CPO Line Number
            ZYPEZDItemValueSetter.setValue(param.dsCpoLineNum, bean.getDsCpoLineNum());
            // DS CPO Line Sub number
            ZYPEZDItemValueSetter.setValue(param.dsCpoLineSubNum, bean.getDsCpoLineSubNum());
            // Price Category Code
            ZYPEZDItemValueSetter.setValue(param.prcCatgCd, bean.getPrcCatgCd());
            // DS Order Line Category Code
            ZYPEZDItemValueSetter.setValue(param.dsOrdLineCatgCd, bean.getDsOrdLineCatgCd());
            // QC#21841 2018/05/21 Add Start
            ZYPEZDItemValueSetter.setValue(param.invLineCatgCd, INV_LINE_CATG.ITEM);
            // QC#21841 2018/05/21 Add End
            // QC#26415 2018/06/22 Add Start
            ZYPEZDItemValueSetter.setValue(param.origOrCustMdseCd, bean.getOrigOrCustMdseCd());
            // QC#26415 2018/06/22 Add End

            // 2018/08/21 QC#27443 Add Start
            BigDecimal splyPgmUnitAmtRate = ZERO;
            BigDecimal splyPgmShipQty     = ZERO;
            MDSETMsg mdseTMsg = getDsMdseInfo(bean.getMdseCd());
            if (hasValue(mdseTMsg.areaOfPaperNum)) {
                splyPgmShipQty = param.shipQty.getValue().multiply(mdseTMsg.areaOfPaperNum.getValue());
                if (!ZERO.equals(splyPgmShipQty)) {
                    splyPgmUnitAmtRate = param.invLineDealNetAmt.getValue().divide(splyPgmShipQty, CONST_SPLY_PGM_UNIT_AMT_RATE_SCALE, HALF_UP);
                }
            }

            if (ZYPCommonFunc.hasValue(mdseTMsg.easyPackTpCd) && EASY_PACK_TP.EASYPAC_TONER.equals(mdseTMsg.easyPackTpCd.getValue())) {
                splyPgmUnitAmtRate = param.dealNetUnitPrcAmt.getValue();
            }

            ZYPEZDItemValueSetter.setValue(param.splyPgmShipQty, splyPgmShipQty);
            ZYPEZDItemValueSetter.setValue(param.splyPgmUnitAmtRate, splyPgmUnitAmtRate);
            // 2018/08/21 QC#27443 Add End

            //Set Line/ SubLine Number 
            bean.setLineNum(lineNum);
            bean.setLineSubNum(lineSubNum);

            invImptApiBean.getInvLinePMsgList().add(param);

            // *********************************************************************
            // Set TaxParams
            // *********************************************************************
            List<NWZC040005PMsg> taxEmptMsg = invImptApiBean.getEmptyInvLineSubNumList();
            for (NWZC040005PMsg taxMsg : taxEmptMsg) {
                // QC#21841 2018/05/21 Mod Start
                //String taxTpCd = taxMsg.dsSlsTaxTpCd.getValue();
                //if (DS_SLS_TAX_TP.FREIGHT_STATE_TAX.equals(taxTpCd)
                // || DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX.equals(taxTpCd)
                // || DS_SLS_TAX_TP.FREIGHT_CITY_TAX.equals(taxTpCd)) {
                //    ZYPEZDItemValueSetter.setValue(taxMsg.invLineNum, "*");
                //    ZYPEZDItemValueSetter.setValue(taxMsg.invLineSubNum, "*");
                //} else {
                //    ZYPEZDItemValueSetter.setValue(taxMsg.invLineNum, invImptApiBean.getLineNumCntStr());
                //    ZYPEZDItemValueSetter.setValue(taxMsg.invLineSubNum, invImptApiBean.getLineSubNumCntStr());
                //}
                ZYPEZDItemValueSetter.setValue(taxMsg.invLineNum, String.format("%05d", lineNum));
                ZYPEZDItemValueSetter.setValue(taxMsg.invLineSubNum, String.format("%03d", lineSubNum));
                // QC#21841 2018/05/21 Mod End
            }

            return true;
        } finally {
            writeEndLogLn("setLineParams");
        }
    }

    private void setHeaderParams(OrderCloseBean bean, InvImptApiParamBean invImptApiBean) {
        writeStartLogLn("setHeaderParams");

        try {
            NWZC040001PMsg param = new NWZC040001PMsg();

            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(param.origInvNum, bean.getOrigInvNum());
            ZYPEZDItemValueSetter.setValue(param.invDt, this.salesDate);
            ZYPEZDItemValueSetter.setValue(param.acctDt, this.salesDate);
            ZYPEZDItemValueSetter.setValue(param.invTpCd, INV_TP.CREDIT_MEMO);
            // 2018/09/20 S21_NA#28327 Mod Start
            // ZYPEZDItemValueSetter.setValue(param.netDueDt, bean.getNetDueDt());
            ZYPEZDItemValueSetter.setValue(param.netDueDt, getNetDueDt(bean));
            // 2018/09/20 S21_NA#28327 Mod End
            ZYPEZDItemValueSetter.setValue(param.cpoOrdNum, bean.getCpoOrdNum());
            ZYPEZDItemValueSetter.setValue(param.ordDt, bean.getCpoOrdTs());
            ZYPEZDItemValueSetter.setValue(param.custIssPoNum, bean.getCustIssPoNum());
            ZYPEZDItemValueSetter.setValue(param.custIssPoDt, bean.getCustIssPoDt());
            ZYPEZDItemValueSetter.setValue(param.billToCustCd, bean.getBillToCustLocCd());
            ZYPEZDItemValueSetter.setValue(param.sellToCustCd, bean.getSellToCustCd());
            ZYPEZDItemValueSetter.setValue(param.sellToLocNm, bean.getSellToCustLocNm());
            ZYPEZDItemValueSetter.setValue(param.sellToAddlLocNm, bean.getSellToCustAddLocNm());
            ZYPEZDItemValueSetter.setValue(param.sellToFirstLineAddr, bean.getSellToCustFirstLineAddr());
            ZYPEZDItemValueSetter.setValue(param.sellToScdLineAddr, bean.getSellToCustScdLineAddr());
            ZYPEZDItemValueSetter.setValue(param.sellToThirdLineAddr, bean.getSellToCustThirdLineAddr());
            ZYPEZDItemValueSetter.setValue(param.sellToFrthLineAddr, bean.getSellToCustFrthLineAddr());
            ZYPEZDItemValueSetter.setValue(param.sellToCtyAddr, bean.getSellToCustCtyAddr());
            ZYPEZDItemValueSetter.setValue(param.sellToCntyNm, bean.getSellToCustCntyNm());
            ZYPEZDItemValueSetter.setValue(param.sellToProvNm, bean.getSellToCustProvNm());
            ZYPEZDItemValueSetter.setValue(param.sellToStCd, bean.getSellToCustStCd());
            ZYPEZDItemValueSetter.setValue(param.sellToPostCd, bean.getSellToCustPostCd());
            ZYPEZDItemValueSetter.setValue(param.sellToCtryCd, bean.getSellToCustCtryCd());
            ZYPEZDItemValueSetter.setValue(param.sellToFirstRefCmntTxt, bean.getSellToFirstRefCmntTxt());
            ZYPEZDItemValueSetter.setValue(param.sellToScdRefCmntTxt, bean.getSellToScdRefCmntTxt());
            ZYPEZDItemValueSetter.setValue(param.payerCustCd, bean.getBillToCustLocCd());
            ZYPEZDItemValueSetter.setValue(param.advRcptNum, bean.getAdvRcptNum());
            ZYPEZDItemValueSetter.setValue(param.pmtTermStartDt, bean.getPmtTermDt());
            ZYPEZDItemValueSetter.setValue(param.pmtTermCd, bean.getPmtTermCd());
            ZYPEZDItemValueSetter.setValue(param.pmtTermNm, bean.getPmtTermNm());
            ZYPEZDItemValueSetter.setValue(param.cashDiscTermCd, bean.getCashDiscTermCd());
            ZYPEZDItemValueSetter.setValue(param.slsAdminPsnCd, bean.getAdminPsnCd());
            ZYPEZDItemValueSetter.setValue(param.invFirstCmntTxt, bean.getInvFirstCmntTxt());
            ZYPEZDItemValueSetter.setValue(param.invScdCmntTxt, bean.getInvScdCmntTxt());
            ZYPEZDItemValueSetter.setValue(param.invThirdCmntTxt, bean.getInvThirdCmntTxt());
            ZYPEZDItemValueSetter.setValue(param.invFrthCmntTxt, bean.getInvFrthCmntTxt());
            ZYPEZDItemValueSetter.setValue(param.dealCcyCd, bean.getDealCcyCd());
            ZYPEZDItemValueSetter.setValue(param.actlApplyExchRate, bean.getActApplExchRate());
            ZYPEZDItemValueSetter.setValue(param.flPlnFlg, bean.getFlPlnCmpyFlg());
            ZYPEZDItemValueSetter.setValue(param.invPrintStsCd, "1");
            ZYPEZDItemValueSetter.setValue(param.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES_RETURN);
            ZYPEZDItemValueSetter.setValue(param.invMlSendStsCd, "2");
            ZYPEZDItemValueSetter.setValue(param.invEdiSendStsCd, "2");
            ZYPEZDItemValueSetter.setValue(param.invFaxSendStsCd, "2");
            ZYPEZDItemValueSetter.setValue(param.invEmlSendStsCd, "2");
            ZYPEZDItemValueSetter.setValue(param.sysSrcCd, bean.getSysSrcCd());
            ZYPEZDItemValueSetter.setValue(param.pmtTermCashDiscCd, bean.getPmtTermCashDiscCd());
            ZYPEZDItemValueSetter.setValue(param.dsOrdTpCd, bean.getDsOrdTpCd());
            ZYPEZDItemValueSetter.setValue(param.dsOrdRsnCd, bean.getDsOrdRsnCd());
            ZYPEZDItemValueSetter.setValue(param.invRcpntCustCd, bean.getInvRcpntCustCd());

            String crCardChrgCpltCd = getCrCardChrgCpltCd(bean.getPmtTermCashDiscCd());
            ZYPEZDItemValueSetter.setValue(param.crCardChrgCpltCd, crCardChrgCpltCd);
            ZYPEZDItemValueSetter.setValue(param.crCardCustRefNum, bean.getCrCardCustRefNum());
            ZYPEZDItemValueSetter.setValue(param.crCardAuthRefNum, bean.getCrCardAuthRefNum());
            ZYPEZDItemValueSetter.setValue(param.crCardAuthDt, bean.getCrCardAuthDt());
            ZYPEZDItemValueSetter.setValue(param.crCardTpCd, bean.getCrCardTpCd());
            // Mod Start 2019/09/05 QC#53297
//            ZYPEZDItemValueSetter.setValue(param.dsInvExprDt, bean.getCrCardExprYrMth());
            if (ZYPCommonFunc.hasValue(bean.getCrCardExprYrMth())) {
                ZYPEZDItemValueSetter.setValue(param.dsInvExprDt, bean.getCrCardExprYrMth() + "01");
            }
            // Mod End 2019/09/05 QC#53297
            ZYPEZDItemValueSetter.setValue(param.dsInvTpCd, bean.getCrDsInvTpCd());
            ZYPEZDItemValueSetter.setValue(param.srcSysDocNum, bean.getCpoOrdNum());
            ZYPEZDItemValueSetter.setValue(param.slsRepTocCd, bean.getSlsRepTocCd());
            ZYPEZDItemValueSetter.setValue(param.itrlInvErrFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(param.billToCustAcctCd, bean.getBillToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(param.soldToCustLocCd, bean.getSoldToCustLocCd());
            ZYPEZDItemValueSetter.setValue(param.lineBizTpCd, bean.getLineBizTpCd());
            ZYPEZDItemValueSetter.setValue(param.dsBizAreaCd, getBizAreaCd(bean.getDsOrdCatgCd(), bean.getDsOrdTpCd(), bean.getDsOrdRsnCd()));
            ZYPEZDItemValueSetter.setValue(param.dsOrdCatgCd, bean.getDsOrdCatgCd());
            ZYPEZDItemValueSetter.setValue(param.crRebilRsnCatgCd, bean.getCrRebilRsnCatgCd()); //QC#16731 add

            if (INV_PRINT_STYLE.SUMMARY.equals(bean.getInvPrintStyleCd())) {
                ZYPEZDItemValueSetter.setValue(param.dplyMdseDtlFlg, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(param.dplyMdseDtlFlg, ZYPConstant.FLG_ON_Y);
            }

            CTAC_PSNTMsg ctacPsnMsg = getBillAttCntctName(bean, this.salesDate);

            if (ctacPsnMsg != null) {
                ZYPEZDItemValueSetter.setValue(param.billToCtacPsnFirstNm, ctacPsnMsg.ctacPsnFirstNm.getValue());
                ZYPEZDItemValueSetter.setValue(param.billToCtacPsnMidNm, ctacPsnMsg.ctacPsnMidNm.getValue());
                ZYPEZDItemValueSetter.setValue(param.billToCtacPsnLastNm, ctacPsnMsg.ctacPsnLastNm.getValue());
            }

            invImptApiBean.getInvHeaderPmsgList().add(param);
        } finally {
            writeEndLogLn("setHeaderParams");
        }
    }

    /**
     * getCrCardChrgCpltCd
     * @param pmtTermCashDiscCd
     * @return
     */
    private String getCrCardChrgCpltCd(String pmtTermCashDiscCd) {

        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = new PMT_TERM_CASH_DISCTMsg();

        ZYPEZDItemValueSetter.setValue(pmtTermCashDiscTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmtTermCashDiscTMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);

        pmtTermCashDiscTMsg = (PMT_TERM_CASH_DISCTMsg) S21CodeTableAccessor.findByKey(pmtTermCashDiscTMsg);

        if (pmtTermCashDiscTMsg != null && ZYPConstant.FLG_ON_Y.equals(pmtTermCashDiscTMsg.pmtCcFlg)) {
            return CR_CARD_CHRG_CPLT.WAITING_FOR_CREDIT_CARD_CHARGE;
        } else {
            return CR_CARD_CHRG_CPLT.NO_NEED_CREDIT_CARD_CHARGE;
        }
    }

    /**
     * setLineFreightCharge
     * @param bean
     * @param invImptApiBean
     * @param paramLine
     * @return
     */
    @SuppressWarnings("unchecked")
    private Boolean setLineFreightCharge(OrderCloseBean bean, InvImptApiParamBean invImptApiBean, NWZC036101_taxCalculateInputLinePMsg paramLine) {
        writeStartLogLn("setLineFreightCharge");

        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("cpoOrdNum", bean.getCpoOrdNum());
            ssmParam.put("dSCpoRtrnLineNum", bean.getDsCpoRtrnLineNum());
            ssmParam.put("dsCpoRtrnLineSubNum", bean.getDsCpoRtrnLineSubNum());

            Map<String, Object> prntGrsAmt = (Map<String, Object>) ssmBatchClient.queryObject("getFreightCharge", ssmParam);

            BigDecimal unitPrcAmt;
            BigDecimal calcPrcAmtRate;
            if (prntGrsAmt == null) {
                unitPrcAmt = BigDecimal.ZERO;
                calcPrcAmtRate = BigDecimal.ZERO;
            } else {
                unitPrcAmt = (BigDecimal) prntGrsAmt.get("UNIT_PRC_AMT");
                calcPrcAmtRate = (BigDecimal) prntGrsAmt.get("CALC_PRC_AMT_RATE");
            }

            BigDecimal funcNetUnitPrcAmt = convToBigDecimal(unitPrcAmt);
            ZYPEZDItemValueSetter.setValue(paramLine.funcNetUnitPrcAmt_A, funcNetUnitPrcAmt);
            ZYPEZDItemValueSetter.setValue(paramLine.slsAmt_A, convToBigDecimal(calcPrcAmtRate));

            // Set Shipment Parameter
            String invBolLineNum = invImptApiBean.getLastPmtInvBolLineNum();
            NWZC040002PMsg shipPMsg = invImptApiBean.getShipMsgByInvBolLineNum(invBolLineNum);

            if (shipPMsg != null) {
                // Freight Deal Tax Amount
                BigDecimal shipDealFrtAmt = convToBigDecimal(shipPMsg.shipDealFrtAmt.getValue());
                shipDealFrtAmt = shipDealFrtAmt.add(funcNetUnitPrcAmt);
                shipPMsg.shipDealFrtAmt.setValue(shipDealFrtAmt);
                shipPMsg.shipFuncFrtAmt.setValue(shipDealFrtAmt);
            }

            return true;
        } finally {
            writeEndLogLn("setLineFreightCharge");
        }
    }

    private CTAC_PSNTMsg getBillAttCntctName(OrderCloseBean bean, String slsDt) {
        writeStartLogLn("getBillAttCntctName");

        try {
            String ctacTpCd;
            CTAC_PSNTMsg headerParam;
            S21LRUMap<String, Object> cache;
            // QC#25236 2018/09/10 Mod Start
//            if (DS_INV_TP.INVOICE_SUPPLIES.equals(bean.getDsInvTpCd())) {
            //if (DS_INV_TP.CREDIT_SUPPLIES.equals(bean.getDsInvTpCd())) {        //QC#23559 mod
            // QC#27954 2018/11/15 Mod Start
//            if (DS_INV_TP.CREDIT_SUPPLIES.equals(bean.getDsInvTpCd()) || DS_INV_TP.INVOICE_SUPPLIES.equals(bean.getDsInvTpCd())) {
//            // QC#25236 2018/09/10 Mod End
//                ctacTpCd = CTAC_TP.BILL_TO_SUPPLIES;
//            } else {
////                ctacTpCd = CTAC_TP.BILL_TO_CONTACT;
//                ctacTpCd = CTAC_TP.BILL_TO_CONTACT_EQUIPMENT;    //QC#23559 mod
//            }
            ctacTpCd = setOrdCatgBizCtx(bean);
            // QC#27954 2018/11/15 Mod End
            cache = new S21LRUMap<String, Object>();

            headerParam = getCtacPsnNm(CONST_SEARCH_TARGET_BILL_TO
                                        , bean.getDsCpoConfigPk()
                                        , bean.getCpoOrdNum()
                                        , bean.getBillToCustLocCd()
                                        , bean.getBillToCustAcctCd()
                                        , ctacTpCd
                                        , slsDt
                                        , cache);

            return headerParam;
        } finally {
            writeEndLogLn("getBillAttCntctName");
        }
    }


    /**
     * getCtacPsnNm
     * @param searchTargetStr
     * @param dsCpoConfigPk
     * @param cpoOrdNum
     * @param custLocCd
     * @param custAcctCd
     * @param ctacTpCd
     * @param invDt
     * @param s21LRUMap
     * @return
     */
    private CTAC_PSNTMsg getCtacPsnNm(String searchTargetStr, String dsCpoConfigPk
                                        , String cpoOrdNum, String custLocCd, String custAcctCd, String ctacTpCd, String invDt, S21LRUMap<String, Object> s21LRUMap) {
        writeStartLogLn("getCtacPsnNm");

        try {
            //get Contact from cache
            String cacheKey1 = createCacheKey("getCtacPsnFromCpoConfig",        searchTargetStr, dsCpoConfigPk);
            String cacheKey2 = createCacheKey("getCtacPsnFromCpoOrder",         searchTargetStr, cpoOrdNum);
            String cacheKey3 = createCacheKey("getCtacPsnFromCustomerLocation", searchTargetStr, custLocCd);
            String cacheKey4 = createCacheKey("getCtacPsnFromCustomerAccount",  searchTargetStr, custAcctCd);

            CTAC_PSNTMsg ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey1);
            if (ctacPsnTmsg != null) {
                return ctacPsnTmsg;
            }
            ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey2);
            if (ctacPsnTmsg != null) {
                return ctacPsnTmsg;
            }
            ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey3);
            if (ctacPsnTmsg != null) {
                return ctacPsnTmsg;
            }
            ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey4);
            if (ctacPsnTmsg != null) {
                return ctacPsnTmsg;
            }

            //searche table
            // 1.by config
            ctacPsnTmsg = new CTAC_PSNTMsg();
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd",    glblCmpyCd);
            mapParam.put("ctacTpCd",      ctacTpCd);
            mapParam.put("dsCpoConfigPk", dsCpoConfigPk);
            mapParam.put("cpoOrdNum",     cpoOrdNum);               //QC#23559 add
            mapParam.put("scdCtacTpCd",   CTAC_TP.BILL_TO_CONTACT); //QC#23559 add
            S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("getCtacPsnFromCpo", mapParam, ctacPsnTmsg);

            int resultCount = ssmRes.getQueryResultCount();
            if (resultCount != 0) {
                s21LRUMap.put(cacheKey1, ctacPsnTmsg);
                return ctacPsnTmsg;
            }

            // 2.by Order Num
            ctacPsnTmsg = new CTAC_PSNTMsg();
            mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd",   glblCmpyCd);
            mapParam.put("ctacTpCd",     ctacTpCd);
            mapParam.put("cpoOrdNum",    cpoOrdNum);
            mapParam.put("scdCtacTpCd",  CTAC_TP.BILL_TO_CONTACT);  //QC#23559 add
            ssmRes = ssmEzdClient.queryEZDMsg("getCtacPsnFromCpo", mapParam, ctacPsnTmsg);

            resultCount = ssmRes.getQueryResultCount();
            if (resultCount != 0) {
                s21LRUMap.put(cacheKey2, ctacPsnTmsg);
                return ctacPsnTmsg;
            }

            // 3. by location
            ctacPsnTmsg = new CTAC_PSNTMsg();
            mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd",   glblCmpyCd);
            mapParam.put("ctacTpCd",     ctacTpCd);
            mapParam.put("custLocCd",    custLocCd);
            mapParam.put("activeFlg",    ZYPConstant.FLG_ON_Y);
            mapParam.put("invDt",        invDt);
            if (CONST_SEARCH_TARGET_BILL_TO.equals(searchTargetStr)) {
                mapParam.put("dsCtacPsnDeptCd", DS_CTAC_PSN_DEPT.ACCOUNTS_PAYABLE);
                mapParam.put("scdCtacTpCd", CTAC_TP.BILL_TO_CONTACT); //QC#23559 add
            }
            ssmRes = ssmEzdClient.queryEZDMsg("getCtacPsnFromCustomer", mapParam, ctacPsnTmsg);

            resultCount = ssmRes.getQueryResultCount();
            if (resultCount != 0) {
                s21LRUMap.put(cacheKey3, ctacPsnTmsg);
                return ctacPsnTmsg;
            }

            // 4. by account
            ctacPsnTmsg = new CTAC_PSNTMsg();
            mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd",   glblCmpyCd);
            mapParam.put("ctacTpCd",     ctacTpCd);
            mapParam.put("custAcctCd",   custAcctCd);
            mapParam.put("activeFlg",    ZYPConstant.FLG_ON_Y);
            mapParam.put("invDt",        invDt);
            if (CONST_SEARCH_TARGET_BILL_TO.equals(searchTargetStr)) {
                mapParam.put("dsCtacPsnDeptCd", DS_CTAC_PSN_DEPT.ACCOUNTS_PAYABLE);
                mapParam.put("scdCtacTpCd", CTAC_TP.BILL_TO_CONTACT); //QC#23559 add
            }
            ssmRes = ssmEzdClient.queryEZDMsg("getCtacPsnFromCustomer", mapParam, ctacPsnTmsg);

            resultCount = ssmRes.getQueryResultCount();
            if (resultCount != 0) {
                s21LRUMap.put(cacheKey4, ctacPsnTmsg);
                return ctacPsnTmsg;
            }

            return ctacPsnTmsg;
        } finally {
            writeEndLogLn("getCtacPsnNm");
        }
    }

    /**
     * setShipParams
     * @param bean
     * @param invImptApiBean
     */
    private void setShipParams(OrderCloseBean bean, InvImptApiParamBean invImptApiBean) {
        writeStartLogLn("setShipParams");

        try {
            NWZC040002PMsg param = new NWZC040002PMsg();

            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
            int invBolLineNum = invImptApiBean.getShipmentsPMsgList().size() + 1;
            ZYPEZDItemValueSetter.setValue(param.invBolLineNum, String.format("%03d", invBolLineNum));
            ZYPEZDItemValueSetter.setValue(param.shipFromInvtyLocCd, bean.getInvtyLocCd());
            ZYPEZDItemValueSetter.setValue(param.shipToCustCd, bean.getShipToCustLocCd());
            ZYPEZDItemValueSetter.setValue(param.dropShipFlg, bean.getDropShipFlg());
            ZYPEZDItemValueSetter.setValue(param.shipToLocNm, bean.getShipToLocNm());
            ZYPEZDItemValueSetter.setValue(param.shipToAddlLocNm, bean.getShipToAddlLocNm());
            ZYPEZDItemValueSetter.setValue(param.shipToFirstLineAddr, bean.getShipToFirstLineAddr());
            ZYPEZDItemValueSetter.setValue(param.shipToScdLineAddr, bean.getShipToScdLineAddr());
            ZYPEZDItemValueSetter.setValue(param.shipToThirdLineAddr, bean.getShipToThirdLineAddr());
            ZYPEZDItemValueSetter.setValue(param.shipToFrthLineAddr, bean.getShipToFrthLineAddr());
            ZYPEZDItemValueSetter.setValue(param.shipToCtyAddr, bean.getShipToCtyAddr());
            ZYPEZDItemValueSetter.setValue(param.shipToCntyNm, bean.getShipToCntyNm());
            ZYPEZDItemValueSetter.setValue(param.shipToProvNm, bean.getShipToProvNm());
            ZYPEZDItemValueSetter.setValue(param.shipToStCd, bean.getShipToStCd());
            ZYPEZDItemValueSetter.setValue(param.shipToPostCd, bean.getShipToPostCd());
            ZYPEZDItemValueSetter.setValue(param.shipToCtryCd, bean.getShipToCtryCd());
            ZYPEZDItemValueSetter.setValue(param.shipToFirstRefCmntTxt, bean.getShipToFirstRefCmntTxt());
            ZYPEZDItemValueSetter.setValue(param.shipToScdRefCmntTxt, bean.getShipToScdRefCmntTxt());
            if (isExportForCtry(this.glblCmpyCd,  bean.getShipToCtryCd())) {
                ZYPEZDItemValueSetter.setValue(param.exptFlg, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(param.exptFlg, ZYPConstant.FLG_ON_Y);
            }

            ZYPEZDItemValueSetter.setValue(param.shipToCustAcctCd, bean.getShipToCustAcctCd());

            // S21_NA#27299 2018/11/15 Add Start
            if (bean.isMixOrder()) {
                ZYPEZDItemValueSetter.setValue(param.origInvtyLocCd, bean.getOrigInvtyLocCd());
                ZYPEZDItemValueSetter.setValue(param.origShipToCustCd, bean.getOrigShipToCustLocCd());
                ZYPEZDItemValueSetter.setValue(param.origShipToLocNm, bean.getOrigShipToLocNm());
                ZYPEZDItemValueSetter.setValue(param.origShipToAddlLocNm, bean.getOrigShipToAddlLocNm());
                ZYPEZDItemValueSetter.setValue(param.origShipFirstLineAddr, bean.getOrigShipToFirstLineAddr());
                ZYPEZDItemValueSetter.setValue(param.origShipScdLineAddr, bean.getOrigShipToScdLineAddr());
                ZYPEZDItemValueSetter.setValue(param.origShipThirdLineAddr, bean.getOrigShipToThirdLineAddr());
                ZYPEZDItemValueSetter.setValue(param.origShipFrthLineAddr, bean.getOrigShipToFrthLineAddr());
                ZYPEZDItemValueSetter.setValue(param.origShipToCtyAddr, bean.getOrigShipToCtyAddr());
                ZYPEZDItemValueSetter.setValue(param.origShipToCntyNm, bean.getOrigShipToCntyNm());
                ZYPEZDItemValueSetter.setValue(param.origShipToStCd, bean.getOrigShipToStCd());
                ZYPEZDItemValueSetter.setValue(param.origShipToPostCd, bean.getOrigShipToPostCd());
                ZYPEZDItemValueSetter.setValue(param.origShipToCtryCd, bean.getOrigShipToCtryCd());
            } else {
                ZYPEZDItemValueSetter.setValue(param.origInvtyLocCd, bean.getInvtyLocCd());
                ZYPEZDItemValueSetter.setValue(param.origShipToCustCd, bean.getShipToCustLocCd());
                ZYPEZDItemValueSetter.setValue(param.origShipToLocNm, bean.getShipToLocNm());
                ZYPEZDItemValueSetter.setValue(param.origShipToAddlLocNm, bean.getShipToAddlLocNm());
                ZYPEZDItemValueSetter.setValue(param.origShipFirstLineAddr, bean.getShipToFirstLineAddr());
                ZYPEZDItemValueSetter.setValue(param.origShipScdLineAddr, bean.getShipToScdLineAddr());
                ZYPEZDItemValueSetter.setValue(param.origShipThirdLineAddr, bean.getShipToThirdLineAddr());
                ZYPEZDItemValueSetter.setValue(param.origShipFrthLineAddr, bean.getShipToFrthLineAddr());
                ZYPEZDItemValueSetter.setValue(param.origShipToCtyAddr, bean.getShipToCtyAddr());
                ZYPEZDItemValueSetter.setValue(param.origShipToCntyNm, bean.getShipToCntyNm());
                ZYPEZDItemValueSetter.setValue(param.origShipToStCd, bean.getShipToStCd());
                ZYPEZDItemValueSetter.setValue(param.origShipToPostCd, bean.getShipToPostCd());
                ZYPEZDItemValueSetter.setValue(param.origShipToCtryCd, bean.getShipToCtryCd());
            }
            // S21_NA#27299 2018/11/15 Add End

            invImptApiBean.getShipmentsPMsgList().add(param);
        } finally {
            writeEndLogLn("setShipParams");
        }
    }

    /**
     * <pre>
     * When the country of Global Company and the country of the shipment destination are different, it is assumed Export.
     * </pre>
     * 
     * @param   glblCmpyCd      GLBL_CMPY_CD
     * @param   ctryCd          CTRY_CD
     * @return  true:export / false: not export
     */
    public static boolean isExportForCtry(String glblCmpyCd, String ctryCd) {

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);

        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        final String glblCmpyCtryCd;
        if (glblCmpyTMsg != null) {
            glblCmpyCtryCd = glblCmpyTMsg.ctryCd.getValue();
        } else {
            glblCmpyCtryCd = "";
        }

        return !glblCmpyCtryCd.equals(ctryCd);
    }


    /**
     * OrderCloseBeanListCreator
     * @author 
     *
     */
    protected class OrderCloseBeanListCreator extends S21SsmListResultSetHandlerSupport {

        /**
         * doProcessQueryResult
         */
        @Override
        protected List<OrderCloseBean> doProcessQueryResult(ResultSet rs) throws SQLException {
            writeStartLogLn("doProcessQueryResult");

            try {
                List<OrderCloseBean> beanList = new ArrayList<OrderCloseBean>();

                rs.last();
                int recCnt = rs.getRow();
                rs.first();
                if (0 == recCnt) {
                    S21InfoLogOutput.println(MSG_ID.ZZOM0011E.toString());
                } else {
                    setOrderCloseBeanList(rs, beanList, recCnt);
                }

                return beanList;
            } finally {
                writeEndLogLn("doProcessQueryResult");
            }
        }

        void setOrderCloseBeanList(ResultSet rs, List<OrderCloseBean> beanList, int recAllCnt) throws SQLException {
            writeStartLogLn("setOrderCloseBeanList");

            try {
                int recCnt = 0;
                rs.first();

                do {
                    OrderCloseBean orderBean = new OrderCloseBean();

                    orderBean.setOrigInvNum(convToString(rs.getString("ORIG_INV_NUM")));
                    orderBean.setNetDueDt(convToString(rs.getString("NET_DUE_DT")));
                    orderBean.setCpoOrdNum(convToString(rs.getString("CPO_ORD_NUM")));
                    orderBean.setCpoOrdTs(convToString(rs.getString("CPO_ORD_TS")));
                    orderBean.setCustIssPoNum(convToString(rs.getString("CUST_ISS_PO_NUM")));
                    orderBean.setCustIssPoDt(convToString(rs.getString("CUST_ISS_PO_DT")));
                    orderBean.setBillToCustLocCd(convToString(rs.getString("BILL_TO_CUST_LOC_CD")));
                    orderBean.setSellToCustCd(convToString(rs.getString("SELL_TO_CUST_CD")));
                    orderBean.setSellToCustLocNm(convToString(rs.getString("LOC_NM")));
                    orderBean.setSellToCustAddLocNm(convToString(rs.getString("ADDL_LOC_NM")));
                    orderBean.setSellToCustFirstLineAddr(convToString(rs.getString("FIRST_LINE_ADDR")));
                    orderBean.setSellToCustScdLineAddr(convToString(rs.getString("SCD_LINE_ADDR")));
                    orderBean.setSellToCustThirdLineAddr(convToString(rs.getString("THIRD_LINE_ADDR")));
                    orderBean.setSellToCustFrthLineAddr(convToString(rs.getString("FRTH_LINE_ADDR")));
                    orderBean.setSellToCustCtyAddr(convToString(rs.getString("CTY_ADDR")));
                    orderBean.setSellToCustProvNm(convToString(rs.getString("PROV_NM")));
                    orderBean.setSellToCustCntyNm(convToString(rs.getString("CNTY_NM_STC_C")));
                    orderBean.setSellToCustStCd(convToString(rs.getString("ST_CD")));
                    orderBean.setSellToCustPostCd(convToString(rs.getString("POST_CD")));
                    orderBean.setSellToCustCtryCd(convToString(rs.getString("CTRY_CD")));
                    orderBean.setSellToFirstRefCmntTxt(convToString(rs.getString("SELL_TO_FIRST_REF_CMNT_TXT")));
                    orderBean.setSellToScdRefCmntTxt(convToString(rs.getString("SELL_TO_SCD_REF_CMNT_TXT")));
                    orderBean.setAdvRcptNum(convToString(rs.getString("ADV_RCPT_NUM")));
                    orderBean.setPmtTermDt(convToString(rs.getString("INV_DT")));
                    orderBean.setPmtTermCd(convToString(rs.getString("PMT_TERM_CD")));
                    orderBean.setPmtTermNm(convToString(rs.getString("PMT_TERM_NM")));
                    orderBean.setCashDiscTermCd(convToString(rs.getString("CASH_DISC_TERM_CD")));
                    orderBean.setAdminPsnCd(convToString(rs.getString("ADMIN_PSN_CD")));
                    orderBean.setInvFirstCmntTxt(convToString(rs.getString("MSG_TXT_INFO_TXT1")));
                    orderBean.setInvScdCmntTxt(convToString(rs.getString("MSG_TXT_INFO_TXT2")));
                    orderBean.setInvThirdCmntTxt(convToString(rs.getString("MSG_TXT_INFO_TXT3")));
                    orderBean.setInvFrthCmntTxt(convToString(rs.getString("MSG_TXT_INFO_TXT4")));
                    orderBean.setDealCcyCd(convToString(rs.getString("CCY_CD")));
                    orderBean.setActApplExchRate(convToBigDecimal(rs.getBigDecimal("EXCH_RATE")));
                    orderBean.setFlPlnCmpyFlg(convToString(rs.getString("FL_PLN_CMPY_FLG")));
                    orderBean.setSysSrcCd(convToString(rs.getString("SYS_SRC_CD")));
                    orderBean.setPmtTermCashDiscCd(convToString(rs.getString("PMT_TERM_CASH_DISC_CD")));
                    orderBean.setDsOrdTpCd(convToString(rs.getString("DS_ORD_TP_CD")));
                    orderBean.setDsOrdRsnCd(convToString(rs.getString("DS_ORD_RSN_CD")));
                    orderBean.setInvRcpntCustCd(convToString(rs.getString("INV_RCPNT_CUST_CD")));
                    orderBean.setCrCardCustRefNum(convToString(rs.getString("CR_CARD_CUST_REF_NUM")));
                    orderBean.setCrCardTpCd(convToString(rs.getString("CR_CARD_TP_CD")));
                    orderBean.setCrCardExprYrMth(convToString(rs.getString("CR_CARD_EXPR_YR_MTH")));
                    orderBean.setDsInvTpCd(convToString(rs.getString("DS_INV_TP_CD")));
                    orderBean.setSlsRepTocCd(convToString(rs.getString("SLS_REP_TOC_CD")));
                    orderBean.setBillToCustAcctCd(convToString(rs.getString("BILL_TO_CUST_ACCT_CD")));
                    orderBean.setSoldToCustLocCd(convToString(rs.getString("SOLD_TO_CUST_LOC_CD")));
                    orderBean.setLineBizTpCd(convToString(rs.getString("LINE_BIZ_TP_CD")));
                    orderBean.setInvPrintStyleCd(convToString(rs.getString("INV_PRINT_STYLE_CD")));
                    orderBean.setInvtyLocCd(convToString(rs.getString("INVTY_LOC_CD")));
                    orderBean.setShipToCustLocCd(convToString(rs.getString("SHIP_TO_CUST_LOC_CD")));
                    orderBean.setDropShipFlg(convToString(rs.getString("DROP_SHIP_FLG")));
                    orderBean.setShipToLocNm(convToString(rs.getString("SHIP_TO_LOC_NM")));
                    orderBean.setShipToAddlLocNm(convToString(rs.getString("SHIP_TO_ADDL_LOC_NM")));
                    orderBean.setShipToFirstLineAddr(convToString(rs.getString("SHIP_TO_FIRST_LINE_ADDR")));
                    orderBean.setShipToScdLineAddr(convToString(rs.getString("SHIP_TO_SCD_LINE_ADDR")));
                    orderBean.setShipToThirdLineAddr(convToString(rs.getString("SHIP_TO_THIRD_LINE_ADDR")));
                    orderBean.setShipToFrthLineAddr(convToString(rs.getString("SHIP_TO_FRTH_LINE_ADDR")));
                    orderBean.setShipToCtyAddr(convToString(rs.getString("SHIP_TO_CTY_ADDR")));
                    orderBean.setShipToCntyNm(convToString(rs.getString("SHIP_TO_CNTY_NM")));
                    orderBean.setShipToProvNm(convToString(rs.getString("SHIP_TO_PROV_NM")));
                    orderBean.setShipToStCd(convToString(rs.getString("SHIP_TO_ST_CD")));
                    orderBean.setShipToPostCd(convToString(rs.getString("SHIP_TO_POST_CD")));
                    orderBean.setShipToCtryCd(convToString(rs.getString("SHIP_TO_CTRY_CD")));
                    orderBean.setShipToFirstRefCmntTxt(convToString(rs.getString("SHIP_TO_FIRST_REF_CMNT_TXT")));
                    orderBean.setShipToScdRefCmntTxt(convToString(rs.getString("SHIP_TO_SCD_REF_CMNT_TXT")));
                    orderBean.setShipToCustAcctCd(convToString(rs.getString("SHIP_TO_CUST_ACCT_CD")));
                    orderBean.setDsCpoRtrnLineNum(convToString(rs.getString("DS_CPO_RTRN_LINE_NUM")));
                    orderBean.setDsCpoRtrnLineSubNum(convToString(rs.getString("DS_CPO_RTRN_LINE_SUB_NUM")));
                    orderBean.setStkStsCd(convToString(rs.getString("STK_STS_CD")));
                    orderBean.setSlsRepOrSlsTeamTocCd(convToString(rs.getString("SLS_REP_OR_SLS_TEAM_TOC_CD")));
                    orderBean.setMdseCd(convToString(rs.getString("MDSE_CD")));
                    orderBean.setMdseNm(convToString(rs.getString("MDSE_NM")));
                    orderBean.setCoaProdCd(convToString(rs.getString("COA_PROD_CD")));
                    orderBean.setTrxCd(convToString(rs.getString("TRX_CD")));
                    orderBean.setTrxRsnCd(convToString(rs.getString("TRX_RSN_CD")));
                    orderBean.setOrdQty(convToBigDecimal(rs.getBigDecimal("ORD_QTY")));
                    orderBean.setRtrnQty(convToBigDecimal(rs.getBigDecimal("RTRN_QTY")));
                    orderBean.setManPrcFlg(convToString(rs.getString("MAN_PRC_FLG")));
                    orderBean.setDealNetUnitPrcAmt(convToBigDecimal(rs.getBigDecimal("ENT_DEAL_NET_UNIT_PRC_AMT")));
                    orderBean.setCpoDtlDealNetAmt(convToBigDecimal(rs.getBigDecimal("ENT_CPO_DTL_DEAL_NET_AMT")));
                    orderBean.setDealDiscAmt(convToBigDecimal(rs.getBigDecimal("DEAL_DISC_AMT")));
                    orderBean.setFuncNetUnitPrcAmt(convToBigDecimal(rs.getBigDecimal("ENT_FUNC_NET_UNIT_PRC_AMT")));
                    orderBean.setCpoDtlFuncNetAmt(convToBigDecimal(rs.getBigDecimal("ENT_CPO_DTL_FUNC_NET_AMT")));
                    orderBean.setFuncDiscAmt(convToBigDecimal(rs.getBigDecimal("FUNC_DISC_AMT")));
                    orderBean.setTaxFlg(convToString(rs.getString("TAX_FLG")));
                    orderBean.setCoaAcctCd(convToString(rs.getString("COA_ACCT_CD")));
                    orderBean.setCoaProjCd(convToString(rs.getString("COA_PROJ_CD")));
                    orderBean.setSetMdseCd(convToString(rs.getString("SET_MDSE_CD")));
                    orderBean.setDealPrcListPrcAmt(convToBigDecimal(rs.getBigDecimal("DEAL_PRC_LIST_PRC_AMT")));
                    orderBean.setCpoDtlDealSlsAmt(convToBigDecimal(rs.getBigDecimal("ENT_CPO_DTL_DEAL_SLS_AMT")));
                    orderBean.setFuncPrcListPrcAmt(convToBigDecimal(rs.getBigDecimal("FUNC_PRC_LIST_PRC_AMT")));
                    orderBean.setCpoDtlFuncSlsAmt(convToBigDecimal(rs.getBigDecimal("ENT_CPO_DTL_FUNC_SLS_AMT")));
                    orderBean.setMchnConfigNum(rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                    orderBean.setDsContrNum(convToString(rs.getString("DS_CONTR_NUM")));
                    orderBean.setDsContrSqNum(convToString(rs.getString("DS_CONTR_SQ_NUM")));
                    orderBean.setFirstBllgAttrbValTxt(convToString(rs.getString("FIRST_BLLG_ATTRB_VAL_TXT")));
                    orderBean.setScdBllgAttrbValTxt(convToString(rs.getString("SCD_BLLG_ATTRB_VAL_TXT")));
                    orderBean.setThirdBllgAttrbValTxt(convToString(rs.getString("THIRD_BLLG_ATTRB_VAL_TXT")));
                    orderBean.setFrthBllgAttrbValTxt(convToString(rs.getString("FRTH_BLLG_ATTRB_VAL_TXT")));
                    orderBean.setFifthBllgAttrbValTxt(convToString(rs.getString("FIFTH_BLLG_ATTRB_VAL_TXT")));
                    orderBean.setSixthBllgAttrbValTxt(convToString(rs.getString("SIXTH_BLLG_ATTRB_VAL_TXT")));
                    orderBean.setCustUomCd(convToString(rs.getString("CUST_UOM_CD")));
                    orderBean.setDsOrdPosnNum(rs.getString("DS_ORD_POSN_NUM"));
                    orderBean.setDsCpoLineNum(convToBigDecimal(rs.getBigDecimal("DS_CPO_LINE_NUM")));
                    orderBean.setDsCpoLineSubNum(rs.getBigDecimal("DS_CPO_LINE_SUB_NUM"));
                    orderBean.setTaxCalcFlg(convToString(rs.getString("TAX_CALC_FLG")));
                    orderBean.setTaxExemFlg(convToString(rs.getString("TAX_EXEM_FLG")));
                    orderBean.setTaxExemRsnCd(convToString(rs.getString("TAX_EXEM_RSN_CD")));
                    orderBean.setCrDsInvTpCd(convToString(rs.getString("CR_DS_INV_TP_CD")));    // 2016/09/02 QC#10990 add
                    orderBean.setLeaseEndTermPrchOptCd(convToString(rs.getString("LEASE_END_TERM_PRCH_OPT_CD")));
                    orderBean.setTrxDt(convToString(rs.getString("INV_DT_ORIG_I")));
                    orderBean.setSvcAllocTpCd(convToString(rs.getString("SVC_ALLOC_TP_CD")));
                    orderBean.setTrxTpNm(convToString(rs.getString("SVC_ALLOC_TRX_TP_NM")));
                    orderBean.setTaxExemTpCd(convToString(rs.getString("TAX_EXEM_TP_CD")));
                    orderBean.setDsTaxGrpExemCdBlLoc(convToString(rs.getString("DS_TAX_GRP_EXEM_CD_DBTC")));
                    orderBean.setDsTaxGrpExemCdBlAcct(convToString(rs.getString("DS_TAX_GRP_EXEM_CD_DAC_B")));
                    orderBean.setDsTaxGrpExemReslFlgLoc(convToString(rs.getString("DS_TAX_GRP_EXEM_RESL_FLG_L")));
                    orderBean.setDsTaxGrpExemReslFlgAcct(convToString(rs.getString("DS_TAX_GRP_EXEM_RESL_FLG_A")));
                    orderBean.setDsTaxGrpExemCdSlLoc(convToString(rs.getString("DS_TAX_GRP_EXEM_CD_DSTC")));
                    orderBean.setDsTaxGrpExemCdSlAcct(convToString(rs.getString("DS_TAX_GRP_EXEM_CD_DAC_S")));
                    orderBean.setDsCpoConfigPk(convToString(rs.getString("DS_CPO_CONFIG_PK")));
                    orderBean.setLocNum(convToString(rs.getString("LOC_NUM")));
                    orderBean.setInvCratFlg(convToString(rs.getString("INV_CRAT_FLG")));
                    orderBean.setCrRebilCd(convToString(rs.getString("CR_REBIL_CD")));
                    orderBean.setImgSplyTpCd(convToString(rs.getString("IMG_SPLY_TP_CD")));
                    orderBean.setShipToTaxAreaId(convToString(rs.getString("GEO_CD")));
                    orderBean.setShipToInsdCtyLimitFlg(convToString(rs.getString("DS_INSD_CTY_LIMIT_FLG")));
                    orderBean.setShipToSpclTaxAreaId(convToString(rs.getString("TAX_AREA_ID")));
                    orderBean.setSlsReqTaxAreaId(convToString(rs.getString("GEO_CD_S21P")));
                    orderBean.setSlsReqInsdCtyLimitFlg(convToString(rs.getString("DS_INSD_CTY_LIMIT_FLG_S21P")));
                    orderBean.setSlsReqFirstLineAddr(convToString(rs.getString("FIRST_LINE_ADDR_S21P")));
                    orderBean.setSlsReqScdLineAddr(convToString(rs.getString("SCD_LINE_ADDR_S21P")));
                    orderBean.setSlsReqCtyAddr(convToString(rs.getString("CTY_ADDR_S21P")));
                    orderBean.setSlsReqStCd(convToString(rs.getString("ST_CD_S21P")));
                    orderBean.setSlsReqCntyNm(convToString(rs.getString("CNTY_NM_S21P")));
                    orderBean.setSlsReqPostCd(convToString(rs.getString("POST_CD_S21P")));
                    orderBean.setSlsReqCtryCd(convToString(rs.getString("CTRY_CD_S21P")));
                    orderBean.setShipFromTaxAreaId(convToString(rs.getString("GEO_CD_RW")));
                    orderBean.setShipFromWhCd(convToString(rs.getString("RTL_WH_CD")));
                    orderBean.setShipFromFirstLineAddr(convToString(rs.getString("FIRST_LINE_ADDR_RW")));
                    orderBean.setShipFromScdLineAddr(convToString(rs.getString("SCD_LINE_ADDR_RW")));
                    orderBean.setShipFromCtyAddr(convToString(rs.getString("CTY_ADDR_RW")));
                    orderBean.setShipFromStCd(convToString(rs.getString("ST_CD_RW")));
                    orderBean.setShipFromCntyNm(convToString(rs.getString("CNTY_NM")));
                    orderBean.setShipFromPostCd(convToString(rs.getString("POST_CD_RW")));
                    orderBean.setShipFromCtryCd(convToString(rs.getString("CTRY_CD_RW")));
                    orderBean.setSvcMachMstrPk(rs.getBigDecimal("SVC_MACH_MSTR_PK"));
                    orderBean.setPrcCatgCd(convToString(rs.getString("PRC_CATG_CD")));
                    orderBean.setDsOrdCatgCd(convToString(rs.getString("DS_ORD_CATG_CD")));
                    orderBean.setDsOrdLineCatgCd(convToString(rs.getString("DS_ORD_LINE_CATG_CD")));
                    orderBean.setCrRebilRsnCatgCd(rs.getString("CR_REBIL_RSN_CATG_CD"));
                    orderBean.setShpgSerTakeFlg(rs.getString("SHPG_SER_TAKE_FLG"));
                    // QC#26415 2018/06/22 Add Start
                    orderBean.setOrigOrCustMdseCd(rs.getString("ORIG_OR_CUST_MDSE_CD"));
                    // QC#26415 2018/06/22 Add End
                    // 2018/10/02 QC#28383 add start
                    orderBean.setInvtyCtrlFlg(convToString(rs.getString("INVTY_CTRL_FLG")));
                    orderBean.setInstlBaseCtrlFlg(convToString(rs.getString("INSTL_BASE_CTRL_FLG")));
                    orderBean.setDsOrdLineCatgCd(convToString(rs.getString("SVC_MDL_TP_CD")));
                    orderBean.setSvcConfigMstrPk(convToBigDecimal(rs.getBigDecimal("SVC_CONFIG_MSTR_PK")));
                    // 2018/10/02 QC#28383 add end
                    
                    // S21_NA#27299 2018/11/15 Add Start
                    orderBean.setOrdMixFlg(convToString(rs.getString("ORD_MIX_FLG")));
                    orderBean.setOrigInvtyLocCd(convToString(rs.getString("ORIG_INVTY_LOC_CD")));
                    orderBean.setOrigShipToTaxAreaId(convToString(rs.getString("ORIG_GEO_CD")));
                    orderBean.setOrigShipToInsdCtyLimitFlg(convToString(rs.getString("ORIG_DS_INSD_CTY_LIMIT_FLG")));
                    orderBean.setOrigShipToCustLocCd(convToString(rs.getString("ORIG_SHIP_TO_CUST_LOC_CD")));
                    orderBean.setOrigShipToLocNm(convToString(rs.getString("ORIG_SHIP_TO_LOC_NM")));
                    orderBean.setOrigShipToAddlLocNm(convToString(rs.getString("ORIG_SHIP_TO_ADDL_LOC_NM")));
                    orderBean.setOrigShipToFirstLineAddr(convToString(rs.getString("ORIG_SHIP_FIRST_LINE_ADDR")));
                    orderBean.setOrigShipToScdLineAddr(convToString(rs.getString("ORIG_SHIP_SCD_LINE_ADDR")));
                    orderBean.setOrigShipToThirdLineAddr(convToString(rs.getString("ORIG_SHIP_THIRD_LINE_ADDR")));
                    orderBean.setOrigShipToFrthLineAddr(convToString(rs.getString("ORIG_SHIP_FRTH_LINE_ADDR")));
                    orderBean.setOrigShipToCtyAddr(convToString(rs.getString("ORIG_SHIP_TO_CTY_ADDR")));
                    orderBean.setOrigShipToCntyNm(convToString(rs.getString("ORIG_SHIP_TO_CNTY_NM")));
                    orderBean.setOrigShipToStCd(convToString(rs.getString("ORIG_SHIP_TO_ST_CD")));
                    orderBean.setOrigShipToPostCd(convToString(rs.getString("ORIG_SHIP_TO_POST_CD")));
                    orderBean.setOrigShipToCtryCd(convToString(rs.getString("ORIG_SHIP_TO_CTRY_CD")));
                    orderBean.setOrigShipToCustAcctCd(convToString(rs.getString("ORIG_SHIP_TO_CUST_ACCT_CD")));
                    orderBean.setOrigDsTaxGrpExemCdSlLoc(convToString(rs.getString("ORIG_DS_TAX_GRP_EXEM_CD_DSTC")));
                    orderBean.setOrigDsTaxGrpExemCdSlAcct(convToString(rs.getString("ORIG_DS_TAX_GRP_EXEM_CD_DAC_S")));
                    orderBean.setOrigShipToSpclTaxAreaId(convToString(rs.getString("ORIG_TAX_AREA_ID")));
                    orderBean.setOrigShipFromTaxAreaId(convToString(rs.getString("ORIG_GEO_CD_RW")));
                    orderBean.setOrigShipFromWhCd(convToString(rs.getString("ORIG_RTL_WH_CD")));
                    orderBean.setOrigShipFromFirstLineAddr(convToString(rs.getString("ORIG_FIRST_LINE_ADDR_RW")));
                    orderBean.setOrigShipFromScdLineAddr(convToString(rs.getString("ORIG_SCD_LINE_ADDR_RW")));
                    orderBean.setOrigShipFromCtyAddr(convToString(rs.getString("ORIG_CTY_ADDR_RW")));
                    orderBean.setOrigShipFromStCd(convToString(rs.getString("ORIG_ST_CD_RW")));
                    orderBean.setOrigShipFromPostCd(convToString(rs.getString("ORIG_POST_CD_RW")));
                    orderBean.setOrigShipFromCtryCd(convToString(rs.getString("ORIG_CTRY_CD_RW")));
                    orderBean.setOrigShipFromCntyNm(convToString(rs.getString("ORIG_CNTY_NM")));
                    // S21_NA#27299 2018/11/15 Add End

                    beanList.add(orderBean);

                    writeLogLn("\r\n***** [%d/%d Recourd] %s *****", ++recCnt, recAllCnt, this.getClass().getSimpleName());
                    writeLogLn(orderBean.toString());

                } while (rs.next());
            } finally {
                writeEndLogLn("setOrderCloseBeanList");
            }
        }
    }

    private static String convToString(String val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return String.valueOf("");
        }
    }

    private static BigDecimal convToBigDecimal(Object val) {
        if (null == val) {
            return BigDecimal.ZERO;
        } else if (val instanceof BigDecimal) {
            return (BigDecimal) val;
        } else {
            return new BigDecimal(val.toString());
        }
    }

    private static BigDecimal changeSign(BigDecimal val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val.negate();
        }
        return null;

    }

    private static <T> T[] toArray(T... param) {
        return param;
    }

    /**
     * writeStartLogLn
     * @param methodNm
     */
    private void writeStartLogLn(String methodNm) {
        writeLogLn("[START] %s", methodNm);
    }

    /**
     * writeEndLogLn
     * @param methodNm
     */
    private void writeEndLogLn(String methodNm) {
        writeLogLn("[END] %s\r\n", methodNm);
    }

    /**
     * getSimpleClsNm
     * @param caller
     * @return
     */
    @SuppressWarnings("unchecked")
    private static String getSimpleClsNm(Class caller) {
        return caller.getSimpleName();
    }

    /**
     * WriteLogLn
     * @param format
     * @param param
     */
    private void writeLogLn(String format, Object... param) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("[%s]", getSimpleClsNm(this.getClass())));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }

    /**
     * setTaxCalcAPIHeaderParam
     * @pram bean
     * @param taxcalcPMsgList
     * @param invNum
     * @return NWZC036101PMsg
     */
    private NWZC036101PMsg setTaxCalcAPIHeaderParam(OrderCloseBean bean, List<NWZC036101PMsg> taxcalcPMsgList
            ,String invNum  // 2018/12/14 QC#29630 add
    ) {

        NWZC036101PMsg param = new NWZC036101PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(param.xxModeCd, NWZC036101Constant.PROC_MODE_INVOICE);
        //Sell To Account Number
        ZYPEZDItemValueSetter.setValue(param.dsAcctNum_SE, bean.getSellToCustCd());
        //Bill To Account Number
        ZYPEZDItemValueSetter.setValue(param.billToAcctNum, bean.getBillToCustAcctCd());
        //Bill To Location Number
        ZYPEZDItemValueSetter.setValue(param.billToLocNum, bean.getBillToCustLocCd());
        //Bill to  Vertex Group Exemption
        if (ZYPCommonFunc.hasValue(bean.getDsTaxGrpExemCdBlLoc())) {
            ZYPEZDItemValueSetter.setValue(param.dsTaxGrpExemCd_B, bean.getDsTaxGrpExemCdBlLoc());
        } else {
            ZYPEZDItemValueSetter.setValue(param.dsTaxGrpExemCd_B, bean.getDsTaxGrpExemCdBlAcct());
        }
        //Bill to  Vertex Group Exemption Resale Flg
        if (ZYPCommonFunc.hasValue(bean.getDsTaxGrpExemReslFlgLoc())) {
            ZYPEZDItemValueSetter.setValue(param.dsTaxGrpExemReslFlg_B, bean.getDsTaxGrpExemReslFlgLoc());
        } else {
            ZYPEZDItemValueSetter.setValue(param.dsTaxGrpExemReslFlg_B, bean.getDsTaxGrpExemReslFlgAcct());
        }
        //Ship To Account Number
        ZYPEZDItemValueSetter.setValue(param.dsAcctNum_ST, bean.getShipToCustAcctCd());

        // S21_NA#27299 2018/11/15 Mod Start
        if (bean.isMixOrder()) {
            //Ship To Location Number
            ZYPEZDItemValueSetter.setValue(param.shipToLocNum, bean.getOrigShipToCustLocCd());
            //Ship to Vertex Group Exemption
            if (ZYPCommonFunc.hasValue(bean.getOrigDsTaxGrpExemCdSlLoc())) {
                ZYPEZDItemValueSetter.setValue(param.dsTaxGrpExemCd_ST, bean.getOrigDsTaxGrpExemCdSlLoc());
            } else {
                ZYPEZDItemValueSetter.setValue(param.dsTaxGrpExemCd_ST, bean.getOrigDsTaxGrpExemCdSlAcct());
            }
        } else {
        //Ship To Location Number
        ZYPEZDItemValueSetter.setValue(param.shipToLocNum, bean.getShipToCustLocCd());
        //Ship to Vertex Group Exemption
        if (ZYPCommonFunc.hasValue(bean.getDsTaxGrpExemCdSlLoc())) {
            ZYPEZDItemValueSetter.setValue(param.dsTaxGrpExemCd_ST, bean.getDsTaxGrpExemCdSlLoc());
        } else {
            ZYPEZDItemValueSetter.setValue(param.dsTaxGrpExemCd_ST, bean.getDsTaxGrpExemCdSlAcct());
        }
        }
        // S21_NA#27299 2018/11/15 Mod End

        //Tax Calculation Flag
        ZYPEZDItemValueSetter.setValue(param.taxCalcFlg, bean.getTaxCalcFlg());
        //Tax Exemption
        ZYPEZDItemValueSetter.setValue(param.taxExemFlg, bean.getTaxExemFlg());
        //Lease Option Code
        ZYPEZDItemValueSetter.setValue(param.leasePrchOptCd, bean.getLeaseEndTermPrchOptCd());
        //Transaction Date
        ZYPEZDItemValueSetter.setValue(param.trxDt, bean.getTrxDt());
        //Tax Calculate Header Num
        // 2018/12/14 QC#29630 mod Start
        //ZYPEZDItemValueSetter.setValue(param.xxTaxCalcHdrNum, bean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(param.xxTaxCalcHdrNum, invNum);
        // 2018/12/14 QC#29630 mod End
        //Tax Exemption Reason Code
        ZYPEZDItemValueSetter.setValue(param.taxExemRsnCd, bean.getTaxExemRsnCd()); //QC#19929 add

        // S21_NA#27299 2018/11/15 Mod Start
        if (bean.isMixOrder()) {
            //Ship To Tax Area ID
            ZYPEZDItemValueSetter.setValue(param.geoCd_ST, bean.getOrigShipToTaxAreaId());
            //Ship To Inside City Limit Flag
            ZYPEZDItemValueSetter.setValue(param.dsInsdCtyLimitFlg_ST, bean.getOrigShipToInsdCtyLimitFlg());
            //Ship To Special Tax Area ID
            ZYPEZDItemValueSetter.setValue(param.taxAreaId_ST, bean.getOrigShipToSpclTaxAreaId());
            //Ship to First Line Address
            ZYPEZDItemValueSetter.setValue(param.firstLineAddr_ST, bean.getOrigShipToFirstLineAddr());
            //Ship to Second Line Address
            ZYPEZDItemValueSetter.setValue(param.scdLineAddr_ST, bean.getOrigShipToScdLineAddr());
            //Ship to City Address
            ZYPEZDItemValueSetter.setValue(param.ctyAddr_ST, bean.getOrigShipToCtyAddr());
            //Ship to State Code
            ZYPEZDItemValueSetter.setValue(param.stCd_ST, bean.getOrigShipToStCd());
            //Ship To County Name
            ZYPEZDItemValueSetter.setValue(param.cntyNm_ST, bean.getOrigShipToCntyNm());
            //Ship To Post Code
            ZYPEZDItemValueSetter.setValue(param.postCd_ST, bean.getOrigShipToPostCd());
            //Ship To Country Code
            ZYPEZDItemValueSetter.setValue(param.ctryCd_ST, bean.getOrigShipToCtryCd());
        } else {
        //Ship To Tax Area ID
        ZYPEZDItemValueSetter.setValue(param.geoCd_ST, bean.getShipToTaxAreaId());
        //Ship To Inside City Limit Flag
        ZYPEZDItemValueSetter.setValue(param.dsInsdCtyLimitFlg_ST, bean.getShipToInsdCtyLimitFlg());
        //Ship To Special Tax Area ID
        ZYPEZDItemValueSetter.setValue(param.taxAreaId_ST, bean.getShipToSpclTaxAreaId());
        //Ship to First Line Address
        ZYPEZDItemValueSetter.setValue(param.firstLineAddr_ST, bean.getShipToFirstLineAddr());
        //Ship to Second Line Address
        ZYPEZDItemValueSetter.setValue(param.scdLineAddr_ST, bean.getShipToScdLineAddr());
        //Ship to City Address
        ZYPEZDItemValueSetter.setValue(param.ctyAddr_ST, bean.getShipToCtyAddr());
        //Ship to State Code
        ZYPEZDItemValueSetter.setValue(param.stCd_ST, bean.getShipToStCd());
        //Ship To County Name
        ZYPEZDItemValueSetter.setValue(param.cntyNm_ST, bean.getShipToCntyNm());
        //Ship To Post Code
        ZYPEZDItemValueSetter.setValue(param.postCd_ST, bean.getShipToPostCd());
        //Ship To Country Code
        ZYPEZDItemValueSetter.setValue(param.ctryCd_ST, bean.getShipToCtryCd());
        }
        // S21_NA#27299 2018/11/15 Mod End

        // Sales Rep Tax Area ID
        ZYPEZDItemValueSetter.setValue(param.geoCd_SR, bean.getSlsReqTaxAreaId());
        // Sales Rep Inside City Limit Flag
        ZYPEZDItemValueSetter.setValue(param.dsInsdCtyLimitFlg_SR, bean.getSlsReqInsdCtyLimitFlg());
        // Sales Rep First Line Address
        ZYPEZDItemValueSetter.setValue(param.firstLineAddr_SR, bean.getSlsReqFirstLineAddr());
        // Sales Rep Second Line Address
        ZYPEZDItemValueSetter.setValue(param.scdLineAddr_SR, bean.getSlsReqScdLineAddr());
        // Sales Rep City Address
        ZYPEZDItemValueSetter.setValue(param.ctyAddr_SR, bean.getSlsReqCtyAddr());
        // Sales Rep State Code
        ZYPEZDItemValueSetter.setValue(param.stCd_SR, bean.getSlsReqStCd());
        // Sales Rep County Name
        ZYPEZDItemValueSetter.setValue(param.cntyNm_SR, bean.getSlsReqCntyNm());
        // Sales Rep Post Code
        ZYPEZDItemValueSetter.setValue(param.postCd_SR, bean.getSlsReqPostCd());
        // Sales Rep Country Code
        ZYPEZDItemValueSetter.setValue(param.ctryCd_SR, bean.getSlsReqCtryCd());

        // S21_NA#27299 2018/11/15 Mod Start
        if (bean.isMixOrder()) {
            // Ship from Tax Area ID
            ZYPEZDItemValueSetter.setValue(param.geoCd_SF, bean.getOrigShipFromTaxAreaId());
            // Ship from WH Code
            ZYPEZDItemValueSetter.setValue(param.whCd_SF, bean.getOrigShipFromWhCd());
            // Ship from First Line Address
            ZYPEZDItemValueSetter.setValue(param.firstLineAddr_SF, bean.getOrigShipFromFirstLineAddr());
            // Ship from Second Line Address
            ZYPEZDItemValueSetter.setValue(param.scdLineAddr_SF, bean.getOrigShipFromScdLineAddr());
            // Ship from City Address
            ZYPEZDItemValueSetter.setValue(param.ctyAddr_SF, bean.getOrigShipFromCtyAddr());
            // Ship from State Code
            ZYPEZDItemValueSetter.setValue(param.stCd_SF, bean.getOrigShipFromStCd());
            // Ship from County Name
            ZYPEZDItemValueSetter.setValue(param.cntyNm_SF, bean.getOrigShipFromCntyNm());
            // Ship from Post Code
            ZYPEZDItemValueSetter.setValue(param.postCd_SF, bean.getOrigShipFromPostCd());
            // Ship from Country Code
            ZYPEZDItemValueSetter.setValue(param.ctryCd_SF, bean.getOrigShipFromCtryCd());
        } else {
        // Ship from Tax Area ID
        ZYPEZDItemValueSetter.setValue(param.geoCd_SF, bean.getShipFromTaxAreaId());
        // Ship from WH Code
        ZYPEZDItemValueSetter.setValue(param.whCd_SF, bean.getShipFromWhCd());
        // Ship from First Line Address
        ZYPEZDItemValueSetter.setValue(param.firstLineAddr_SF, bean.getShipFromFirstLineAddr());
        // Ship from Second Line Address
        ZYPEZDItemValueSetter.setValue(param.scdLineAddr_SF, bean.getShipFromScdLineAddr());
        // Ship from City Address
        ZYPEZDItemValueSetter.setValue(param.ctyAddr_SF, bean.getShipFromCtyAddr());
        // Ship from State Code
        ZYPEZDItemValueSetter.setValue(param.stCd_SF, bean.getShipFromStCd());
        // Ship from County Name
        ZYPEZDItemValueSetter.setValue(param.cntyNm_SF, bean.getShipFromCntyNm());
        // Ship from Post Code
        ZYPEZDItemValueSetter.setValue(param.postCd_SF, bean.getShipFromPostCd());
        // Ship from Country Code
        ZYPEZDItemValueSetter.setValue(param.ctryCd_SF, bean.getShipFromCtryCd());
        }
        // S21_NA#27299 2018/11/15 Mod End

        taxcalcPMsgList.add(param);

        return param;
    }

    /**
     * setTaxCalcLineParam
     * @param bean
     * @param invImptApiBean
     * @param param
     * @param type
     */
    private void setTaxCalcLineParam(OrderCloseBean bean,  InvImptApiParamBean invImptApiBean, NWZC036101PMsg param, TAX_CALC_TYPE type) {

        int taxCalcLineNum = param.taxCalculateInputLine.getValidCount();
        NWZC036101_taxCalculateInputLinePMsg paramLine = (NWZC036101_taxCalculateInputLinePMsg) param.taxCalculateInputLine.no(taxCalcLineNum++);
        param.taxCalculateInputLine.setValidCount(taxCalcLineNum);

        ZYPEZDItemValueSetter.setValue(paramLine.xxTaxCalcLineNum_A, String.valueOf(taxCalcLineNum));
        // Merchandise Code
        if (type == TAX_CALC_TYPE.Freight) {
            ZYPEZDItemValueSetter.setValue(paramLine.mdseCd_A, this.taxFrtItemCd);
        } else {
            ZYPEZDItemValueSetter.setValue(paramLine.mdseCd_A, bean.getMdseCd());
        }
        // Service Allocation Type
        ZYPEZDItemValueSetter.setValue(paramLine.svcAllocTpCd_A, bean.getSvcAllocTpCd());
        // Trx Type
        String svcAllocTrxTpNm = bean.getTrxTpNm();
        if (!hasValue(svcAllocTrxTpNm)) {
            if (this.defaultSvcAllocTrxTpNm == null) {
                this.defaultSvcAllocTrxTpNm = ZYPCodeDataUtil.getVarCharConstValue("DEFAULT_TAX_TRX_TP", glblCmpyCd);
            }
            svcAllocTrxTpNm = defaultSvcAllocTrxTpNm;
        }
        ZYPEZDItemValueSetter.setValue(paramLine.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
        // Product Tax Code
        ZYPEZDItemValueSetter.setValue(paramLine.taxExemTpCd_A, bean.getTaxExemTpCd());
        // Shipped Quantity
        if (type == TAX_CALC_TYPE.Freight) {
            ZYPEZDItemValueSetter.setValue(paramLine.shipQty_A, bean.getRtrnQty());
        } else {
            // QC#58227
            if (ZYPConstant.FLG_ON_Y.equals(bean.getInvtyCtrlFlg())) {
                ZYPEZDItemValueSetter.setValue(paramLine.shipQty_A, bean.getRtrnQty());
            } else {
                ZYPEZDItemValueSetter.setValue(paramLine.shipQty_A, bean.getOrdQty());
            }
        }
        // Function Net Unit Price Amount
        // Sales Amount
        if (type == TAX_CALC_TYPE.Freight) {
            setLineFreightCharge(bean, invImptApiBean, paramLine);
        } else {
            ZYPEZDItemValueSetter.setValue(paramLine.funcNetUnitPrcAmt_A, bean.getFuncNetUnitPrcAmt());
            ZYPEZDItemValueSetter.setValue(paramLine.slsAmt_A, bean.getCpoDtlFuncNetAmt());
        }
        // Bill To Account Number
        ZYPEZDItemValueSetter.setValue(paramLine.billToAcctNum_A, bean.getBillToCustAcctCd());
        // Bill To Location Number
        ZYPEZDItemValueSetter.setValue(paramLine.billToLocNum_A, bean.getBillToCustLocCd());
        // Bill to Vertex Group Exemption
        if (ZYPCommonFunc.hasValue(bean.getDsTaxGrpExemCdBlLoc())) {
            ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AB, bean.getDsTaxGrpExemCdBlLoc());
        } else {
            ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AB, bean.getDsTaxGrpExemCdBlAcct());
        }

        // Bill to Vertex Group Exemption Resale Flg
        if (ZYPCommonFunc.hasValue(bean.getDsTaxGrpExemReslFlgLoc())) {
            ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemReslFlg_AB, bean.getDsTaxGrpExemReslFlgLoc());
        } else {
            ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemReslFlg_AB, bean.getDsTaxGrpExemReslFlgAcct());
        }

        // S21_NA#27299 2018/11/15 Mod Start
        if (bean.isMixOrder()) {
            // Ship To Account Number
            ZYPEZDItemValueSetter.setValue(paramLine.dsAcctNum_AT, bean.getOrigShipToCustAcctCd());
            // Ship To Location Number
            ZYPEZDItemValueSetter.setValue(paramLine.shipToLocNum_A, bean.getOrigShipToCustLocCd());
            // Ship to Vertex Group Exemption
            if (ZYPCommonFunc.hasValue(bean.getOrigDsTaxGrpExemCdSlLoc())) {
                ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AT, bean.getOrigDsTaxGrpExemCdSlLoc());
            } else {
                ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AT, bean.getOrigDsTaxGrpExemCdSlAcct());
            }
            // Ship To Tax Area ID
            ZYPEZDItemValueSetter.setValue(paramLine.geoCd_AT, bean.getOrigShipToTaxAreaId());
            // Ship To Inside City Limit Flag
            ZYPEZDItemValueSetter.setValue(paramLine.dsInsdCtyLimitFlg_AT, bean.getOrigShipToInsdCtyLimitFlg());
            // Ship To Special Tax Area ID
            ZYPEZDItemValueSetter.setValue(paramLine.taxAreaId_AT, bean.getOrigShipToSpclTaxAreaId());
            // Ship to First Line Address
            ZYPEZDItemValueSetter.setValue(paramLine.firstLineAddr_AT, bean.getOrigShipToFirstLineAddr());
            // Ship to Second Line Address
            ZYPEZDItemValueSetter.setValue(paramLine.scdLineAddr_AT, bean.getOrigShipToScdLineAddr());
            // Ship to City Address
            ZYPEZDItemValueSetter.setValue(paramLine.ctyAddr_AT, bean.getOrigShipToCtyAddr());
            // Ship to State Code
            ZYPEZDItemValueSetter.setValue(paramLine.stCd_AT, bean.getOrigShipToStCd());
            // Ship To County Name
            ZYPEZDItemValueSetter.setValue(paramLine.cntyNm_AT, bean.getShipToCntyNm());
            // Ship To Post Code
            ZYPEZDItemValueSetter.setValue(paramLine.postCd_AT, bean.getOrigShipToPostCd());
            // Ship To Country Code
            ZYPEZDItemValueSetter.setValue(paramLine.ctryCd_AT, bean.getOrigShipToCtryCd());
        } else {
        // Ship To Account Number
        ZYPEZDItemValueSetter.setValue(paramLine.dsAcctNum_AT, bean.getShipToCustAcctCd());
        // Ship To Location Number
        ZYPEZDItemValueSetter.setValue(paramLine.shipToLocNum_A, bean.getShipToCustLocCd());
        // Ship to Vertex Group Exemption
        if (ZYPCommonFunc.hasValue(bean.getDsTaxGrpExemCdSlLoc())) {
            ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AT, bean.getDsTaxGrpExemCdSlLoc());
        } else {
            ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AT, bean.getDsTaxGrpExemCdSlAcct());
        }
        // Ship To Tax Area ID
        ZYPEZDItemValueSetter.setValue(paramLine.geoCd_AT, bean.getShipToTaxAreaId());
        // Ship To Inside City Limit Flag
        ZYPEZDItemValueSetter.setValue(paramLine.dsInsdCtyLimitFlg_AT, bean.getShipToInsdCtyLimitFlg());
        // Ship To Special Tax Area ID
        ZYPEZDItemValueSetter.setValue(paramLine.taxAreaId_AT, bean.getShipToSpclTaxAreaId());
        // Ship to First Line Address
        ZYPEZDItemValueSetter.setValue(paramLine.firstLineAddr_AT, bean.getShipToFirstLineAddr());
        // Ship to Second Line Address
        ZYPEZDItemValueSetter.setValue(paramLine.scdLineAddr_AT, bean.getShipToScdLineAddr());
        // Ship to City Address
        ZYPEZDItemValueSetter.setValue(paramLine.ctyAddr_AT, bean.getShipToCtyAddr());
        // Ship to State Code
        ZYPEZDItemValueSetter.setValue(paramLine.stCd_AT, bean.getShipToStCd());
        // Ship To County Name
        ZYPEZDItemValueSetter.setValue(paramLine.cntyNm_AT, bean.getShipToCntyNm());
        // Ship To Post Code
        ZYPEZDItemValueSetter.setValue(paramLine.postCd_AT, bean.getShipToPostCd());
        // Ship To Country Code
        ZYPEZDItemValueSetter.setValue(paramLine.ctryCd_AT, bean.getShipToCtryCd());
        }
        // S21_NA#27299 2018/11/15 Mod End

        // Sales Rep Tax Area ID
        ZYPEZDItemValueSetter.setValue(paramLine.geoCd_AR, bean.getSlsReqTaxAreaId());
        // Sales Rep Inside City Limit Flag
        ZYPEZDItemValueSetter.setValue(paramLine.dsInsdCtyLimitFlg_AR, bean.getSlsReqInsdCtyLimitFlg());
        // Sales Rep First Line Address
        ZYPEZDItemValueSetter.setValue(paramLine.firstLineAddr_AR, bean.getSlsReqFirstLineAddr());
        // Sales Rep Second Line Address
        ZYPEZDItemValueSetter.setValue(paramLine.scdLineAddr_AR, bean.getSlsReqScdLineAddr());
        // Sales Rep City Address
        ZYPEZDItemValueSetter.setValue(paramLine.ctyAddr_AR, bean.getSlsReqCtyAddr());
        // Sales Rep State Code
        ZYPEZDItemValueSetter.setValue(paramLine.stCd_AR, bean.getSlsReqStCd());
        // Sales Rep County Name
        ZYPEZDItemValueSetter.setValue(paramLine.cntyNm_AR, bean.getSlsReqCntyNm());
        // Sales Rep Post Code
        ZYPEZDItemValueSetter.setValue(paramLine.postCd_AR, bean.getSlsReqPostCd());
        // Sales Rep Country Code
        ZYPEZDItemValueSetter.setValue(paramLine.ctryCd_AR, bean.getSlsReqCtryCd());

        // S21_NA#27299 2018/11/15 Mod Start
        if (bean.isMixOrder()) {
            // Ship from Tax Area ID
            ZYPEZDItemValueSetter.setValue(paramLine.geoCd_AF, bean.getOrigShipFromTaxAreaId());
            // Ship from WH Code
            ZYPEZDItemValueSetter.setValue(paramLine.whCd_AF, bean.getOrigShipFromWhCd());
            // Ship from First Line Address
            ZYPEZDItemValueSetter.setValue(paramLine.firstLineAddr_AF, bean.getOrigShipFromFirstLineAddr());
            // Ship from Second Line Address
            ZYPEZDItemValueSetter.setValue(paramLine.scdLineAddr_AF, bean.getOrigShipFromScdLineAddr());
            // Ship from City Address
            ZYPEZDItemValueSetter.setValue(paramLine.ctyAddr_AF, bean.getOrigShipFromCtyAddr());
            // Ship from State Code
            ZYPEZDItemValueSetter.setValue(paramLine.stCd_AF, bean.getOrigShipFromStCd());
            // Ship from County Name
            ZYPEZDItemValueSetter.setValue(paramLine.cntyNm_AF, bean.getOrigShipFromCntyNm());
            // Ship from Post Code
            ZYPEZDItemValueSetter.setValue(paramLine.postCd_AF, bean.getOrigShipFromPostCd());
            // Ship from Country Code
            ZYPEZDItemValueSetter.setValue(paramLine.ctryCd_AF, bean.getOrigShipFromCtryCd());
        } else {
        // Ship from Tax Area ID
        ZYPEZDItemValueSetter.setValue(paramLine.geoCd_AF, bean.getShipFromTaxAreaId());
        // Ship from WH Code
        ZYPEZDItemValueSetter.setValue(paramLine.whCd_AF, bean.getShipFromWhCd());
        // Ship from First Line Address
        ZYPEZDItemValueSetter.setValue(paramLine.firstLineAddr_AF, bean.getShipFromFirstLineAddr());
        // Ship from Second Line Address
        ZYPEZDItemValueSetter.setValue(paramLine.scdLineAddr_AF, bean.getShipFromScdLineAddr());
        // Ship from City Address
        ZYPEZDItemValueSetter.setValue(paramLine.ctyAddr_AF, bean.getShipFromCtyAddr());
        // Ship from State Code
        ZYPEZDItemValueSetter.setValue(paramLine.stCd_AF, bean.getShipFromStCd());
        // Ship from County Name
        ZYPEZDItemValueSetter.setValue(paramLine.cntyNm_AF, bean.getShipFromCntyNm());
        // Ship from Post Code
        ZYPEZDItemValueSetter.setValue(paramLine.postCd_AF, bean.getShipFromPostCd());
        // Ship from Country Code
        ZYPEZDItemValueSetter.setValue(paramLine.ctryCd_AF, bean.getShipFromCtryCd());
        }
        // S21_NA#27299 2018/11/15 Mod End
    }

    /**
     * callTaxCalcAPI
     * @param invImptApiBean
     * @param taxcalcPMsgList
     * @return Boolean
     */
    private Boolean callTaxCalcAPI(InvImptApiParamBean invImptApiBean, List<NWZC036101PMsg> taxcalcPMsgList) {
        writeStartLogLn("callTaxCalcAPI");

        boolean isSuccess = true;
        try {
            NWZC036101 taxCalculationAPI = new NWZC036101();
            for (NWZC036101PMsg taxcalcPMsg : taxcalcPMsgList) {
                taxCalculationAPI.execute(taxcalcPMsg, ONBATCH_TYPE.BATCH);
            }

            List<NWZC040005PMsg> taxDtlPMsgList = new ArrayList<NWZC040005PMsg>();

            taxDtlPMsgList = getTaxCalculationAPIResult(invImptApiBean, taxcalcPMsgList);
            if (taxDtlPMsgList == null) {
                isSuccess = false;
            } else {
                invImptApiBean.setTaxDtlPMsgList(taxDtlPMsgList);
            }

            return isSuccess;
        } finally {
            writeEndLogLn("callTaxCalcAPI");
        }
    }

    /**
     * getTaxCalculationAPIResult
     * @param invImptApiBean
     * @param taxcalcPMsgList
     * @return List<NWZC040005PMsg>
     */
    private List<NWZC040005PMsg> getTaxCalculationAPIResult(InvImptApiParamBean invImptApiBean, List<NWZC036101PMsg> taxcalcPMsgList) {
        writeStartLogLn("getTaxCalculationAPIResult");

        try {
            boolean isSuccess = true;

            List<NWZC040002PMsg> shipmentsPMsgList = invImptApiBean.getShipmentsPMsgList();
            List<NWZC040003PMsg> linePMsgList = invImptApiBean.getInvLinePMsgList();
            List<NWZC040005PMsg> taxDtlPMsgList = new ArrayList<NWZC040005PMsg>();

            NWZC040005PMsg taxDtlPMsg;
            NWZC036101_taxCalculateOutputLinePMsg taxcalcOutputLinePMsg;
            BigDecimal taxAmt;
            BigDecimal taxPct;
            List<BigDecimal> taxAmtList;
            List<BigDecimal> taxPctList;
            List<String> taxResultList;
            List<String> frtTaxTpList = new ArrayList<String>();
            frtTaxTpList.add(DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
            frtTaxTpList.add(DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
            frtTaxTpList.add(DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
            List<String> slsTaxTpList = new ArrayList<String>();
            slsTaxTpList.add(DS_SLS_TAX_TP.STATE_TAX);
            slsTaxTpList.add(DS_SLS_TAX_TP.COUNTY_TAX);
            slsTaxTpList.add(DS_SLS_TAX_TP.CITY_TAX);

            List<String> taxTpList;
            String lineNum;
            String lineSubNum;
            String lineSubTrxNum;

            for (NWZC036101PMsg taxcalcPMsg : taxcalcPMsgList) {
                if (taxcalcPMsg.xxMsgIdList.getValidCount() > 0) {
                    for (int i = 0; i < taxcalcPMsg.xxMsgIdList.getValidCount(); i++) {
                        String errId = taxcalcPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                        invImptApiBean.errMsgList.add(errId);
                        S21InfoLogOutput.println(errId);
                    }
                    isSuccess = false;
                }
            }
            if (!isSuccess) {
                return null;
            }

            int idx = 0;
            int lineIdx = 0;
            for (NWZC040002PMsg shipPMsg : shipmentsPMsgList) {

//                boolean firstLineFlg = true;
                String invNum = shipPMsg.invNum.getValue();
                String invBolLineNum = shipPMsg.invBolLineNum.getValue();
                NWZC036101PMsg taxcalcPMsg = taxcalcPMsgList.get(idx++);
                // QC#21841
                if (!ZYPCommonFunc.hasValue(shipPMsg.frtFuncTaxAmt)) {
                    shipPMsg.frtFuncTaxAmt.setValue(BigDecimal.ZERO);
                }
                if (!ZYPCommonFunc.hasValue(shipPMsg.frtDealTaxAmt)) {
                    shipPMsg.frtDealTaxAmt.setValue(BigDecimal.ZERO);
                }

                for (int taxCalcLineNum = 0; taxCalcLineNum < taxcalcPMsg.taxCalculateOutputLine.getValidCount(); taxCalcLineNum++) {
                    taxcalcOutputLinePMsg = (NWZC036101_taxCalculateOutputLinePMsg) taxcalcPMsg.taxCalculateOutputLine.no(taxCalcLineNum);
                    taxAmt  = taxcalcOutputLinePMsg.invLineFuncTaxAmt.getValue();
                    taxPct  = taxcalcOutputLinePMsg.xxTaxCalcLineTaxPct.getValue();

                    if (taxAmt == null) {
                        continue;
                    }
                    taxAmt = taxAmt.negate();

                    taxAmtList = new ArrayList<BigDecimal>();
                    taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_01.getValue());
                    taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_02.getValue());
                    taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_03.getValue());
                    taxPctList = new ArrayList<BigDecimal>();
                    taxPctList.add(taxcalcOutputLinePMsg.taxPct_01.getValue());
                    taxPctList.add(taxcalcOutputLinePMsg.taxPct_02.getValue());
                    taxPctList.add(taxcalcOutputLinePMsg.taxPct_03.getValue());
                    taxResultList = new ArrayList<String>();
                    taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_01.getValue());
                    taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_02.getValue());
                    taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_03.getValue());

                    // QC#21841 2018/05/21 Add Start
                    //if (firstLineFlg) {
                    //    firstLineFlg = false;
                    //    // freight tax
                    //    setValue(shipPMsg.frtFuncTaxAmt, taxAmt);
                    //    setValue(shipPMsg.frtDealTaxAmt, taxAmt);
                    //    setValue(shipPMsg.frtTaxPct, taxPct);
                    //    lineNum = CONST_FRT_TAX_LINE_NUM;
                    //    lineSubNum = CONST_FRT_TAX_LINE_NUM;
                    //    lineSubTrxNum = CONST_FRT_TAX_LINE_NUM;
                    //    taxTpList = frtTaxTpList;
                    //} else {
                    //    // sales tax
                    //    NWZC040003PMsg linePMsg = linePMsgList.get(lineIdx++);
                    //    setValue(linePMsg.invLineFuncTaxAmt, taxAmt);
                    //    setValue(linePMsg.invLineDealTaxAmt, taxAmt);
                    //    setValue(linePMsg.taxPct, taxPct);
                    //    setValue(linePMsg.taxCalcGeoCd, taxcalcOutputLinePMsg.taxAreaId.getValue());
                    //    lineNum = linePMsg.invLineNum.getValue();
                    //    lineSubNum = linePMsg.invLineSubNum.getValue();
                    //    lineSubTrxNum = linePMsg.invLineSubTrxNum.getValue();
                    //    taxTpList = slsTaxTpList;
                    //}
                    NWZC040003PMsg linePMsg = linePMsgList.get(lineIdx++);
                    setValue(linePMsg.invLineFuncTaxAmt, taxAmt);
                    setValue(linePMsg.invLineDealTaxAmt, taxAmt);
                    setValue(linePMsg.taxPct, taxPct);
                    setValue(linePMsg.taxCalcGeoCd, taxcalcOutputLinePMsg.taxAreaId.getValue());
                    lineNum = linePMsg.invLineNum.getValue();
                    lineSubNum = linePMsg.invLineSubNum.getValue();
                    lineSubTrxNum = linePMsg.invLineSubTrxNum.getValue();
                    if(INV_LINE_CATG.FREIGHT.equals(linePMsg.invLineCatgCd.getValue())){
                        setValue(shipPMsg.frtFuncTaxAmt, shipPMsg.frtFuncTaxAmt.getValue().add(taxAmt));
                        setValue(shipPMsg.frtDealTaxAmt, shipPMsg.frtDealTaxAmt.getValue().add(taxAmt));
                         taxTpList = frtTaxTpList;
                    }else{
                         taxTpList = slsTaxTpList;
                    }
                    
                    taxTpList = slsTaxTpList;
                    // QC#21841 2018/05/21 Add End

                    for (int i = 0; i < taxResultList.size(); i++) {
                        taxAmt = taxAmtList.get(i);
                        taxPct = taxPctList.get(i);
                        if (taxAmt != null) {
                            taxAmt = taxAmt.negate();
                            taxDtlPMsg = new NWZC040005PMsg();
                            setValue(taxDtlPMsg.glblCmpyCd,       glblCmpyCd);
                            setValue(taxDtlPMsg.invNum,           invNum);
                            setValue(taxDtlPMsg.invBolLineNum,    invBolLineNum);
                            setValue(taxDtlPMsg.invLineNum,       lineNum);
                            setValue(taxDtlPMsg.invLineSubNum,    lineSubNum);
                            setValue(taxDtlPMsg.invTrxLineSubNum, lineSubTrxNum);
                            setValue(taxDtlPMsg.dsSlsTaxTpCd,     taxTpList.get(i));
                            setValue(taxDtlPMsg.funcSlsTaxAmt,    taxAmt);
                            setValue(taxDtlPMsg.dealSlsTaxAmt,    taxAmt);
                            setValue(taxDtlPMsg.slsTaxPct, taxPct);
                            setValue(taxDtlPMsg.taxAreaId, taxcalcOutputLinePMsg.taxAreaId);
                            setValue(taxDtlPMsg.taxRsltDescTxt, taxResultList.get(i));
                            taxDtlPMsgList.add(taxDtlPMsg);
                        }
                    }
                }

            }

            return taxDtlPMsgList;
        } finally {
            writeEndLogLn("getTaxCalculationAPIResult");
        }
    }
    private boolean updateMachMstr(OrderCloseBean bean) {

        boolean isSuccess = true;

        NSZC001001PMsg pmsg = new NSZC001001PMsg();
        setValue(pmsg.glblCmpyCd, glblCmpyCd);
        setValue(pmsg.slsDt, salesDate);
        setValue(pmsg.xxModeCd, "19");
        setValue(pmsg.svcMachMstrPk, bean.getSvcMachMstrPk());

        NSZC001001 api = new NSZC001001();
        api.execute(pmsg, ONBATCH_TYPE.BATCH);

        if (pmsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pmsg.xxMsgIdList.getValidCount(); i++) {
                String errId = pmsg.xxMsgIdList.no(i).xxMsgId.getValue();
                S21InfoLogOutput.println(errId);
            }
            isSuccess = false;
        }

        return isSuccess;
    }

    private String getBizAreaCd(String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd",  glblCmpyCd);
        List<String> ordCatgCtxTpCdList = new ArrayList<String>();
        ordCatgCtxTpCdList.add("EQUIPMENT_ORDER");
        ordCatgCtxTpCdList.add("SUPPLIES_ORDER");
        mapParam.put("ordCatgCtxTpCdList", ordCatgCtxTpCdList.toArray(new String[0]));
        mapParam.put("dsOrdCatgCd", dsOrdCatgCd);
        mapParam.put("dsOrdTpCd",   dsOrdTpCd);
        if (hasValue(dsOrdRsnCd)) {
            mapParam.put("dsOrdRsnCd",  dsOrdRsnCd);
        }
        return (String) ssmBatchClient.queryObject("getBizAreaCd", mapParam);
    }

    private boolean checkMacineMasterUpdateTarget(OrderCloseBean bean, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

     // 2019/10/16 QC#54192 Add Start
        if (!bean.getCpoOrdNum().equals(svcMachMstrTMsg.cpoOrdNum.getValue())) {
            return false;
        }
     // 2019/10/16 QC#54192 Add End

        if (ZYPConstant.FLG_ON_Y.equals(bean.getShpgSerTakeFlg())) {
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd",      glblCmpyCd);
            mapParam.put("trxOrdNum",       bean.getCpoOrdNum());
            mapParam.put("trxLineNum",      bean.getDsCpoRtrnLineNum());
            mapParam.put("trxLineSubNum",   bean.getDsCpoRtrnLineSubNum());
            mapParam.put("mdseCd",          bean.getMdseCd());
            mapParam.put("serNum",          svcMachMstrTMsg.serNum.getValue());
            Integer cnt = (Integer) ssmBatchClient.queryObject("checkPutAwaySerial", mapParam);

            if (cnt > 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Call NWZC188001 Display Order Line Status Update API
     * @param chgStsOrderMap Map<String,List<Map<String,String>>>
     * @return boolean 
     */
    private boolean callStsUpdApi(Map<String, List<Map<String, String>>> chgStsOrderMap) {

        Set<String> keySet = chgStsOrderMap.keySet();
        List<NWZC188001PMsg> pMsgList = new ArrayList<NWZC188001PMsg>();

        for (String cpoOrdNum : keySet) {

            NWZC188001PMsg pMsg = new NWZC188001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoOrdNum);

            List<Map<String, String>> rmaLineNumList = chgStsOrderMap.get(cpoOrdNum);

            for (int i = 0; i < rmaLineNumList.size(); i++) {

                Map<String, String> lineNumMap = rmaLineNumList.get(i);
                NWZC188001_rmaLineListPMsg rmaListPmsg = pMsg.rmaLineList.no(i);

                ZYPEZDItemValueSetter.setValue(rmaListPmsg.dsCpoRtrnLineNum, lineNumMap.get(RMA_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(rmaListPmsg.dsCpoRtrnLineSubNum, lineNumMap.get(RMA_LINE_SUB_NUM));
                pMsg.rmaLineList.setValidCount(i + 1);
            }
            pMsgList.add(pMsg);
        }

        if (pMsgList.size() == 0) {
            return true;
        }
        new NWZC188001().execute(pMsgList, ONBATCH_TYPE.BATCH);

        for (NWZC188001PMsg pMsg : pMsgList) {
            // Error Check
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                errRecCnt++;
            }

            for (int i = 0; i < pMsg.rmaLineList.getValidCount(); i++) {
                // RMA List: Error check
                NWZC188001_rmaLineListPMsg rmaListPmsg = pMsg.rmaLineList.no(i);

                if (hasValue(rmaListPmsg.xxMsgId)) {
                    S21InfoLogOutput.println(rmaListPmsg.xxMsgId.getValue());
                    errRecCnt++;
                    break;
                }
            }
        }

        return true;
    }
    // QC#21841 2018/05/21 Add Start
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getCalcBaseForCharges(OrderCloseBean bean) {

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd",          glblCmpyCd);
        mapParam.put("cpoOrdNum",           bean.getCpoOrdNum());
        mapParam.put("cpoDtlLineNum",       bean.getDsCpoRtrnLineNum());
        mapParam.put("cpoDtlLineSubNum",    bean.getDsCpoRtrnLineSubNum());
        List<String> list = new ArrayList<String>();
        list.add(PRC_DTL_GRP.FREIGHT);
        list.add(PRC_DTL_GRP.HANDLING_FEE);
        list.add(PRC_DTL_GRP.FUEL_SURCHARGE);
        list.add(PRC_DTL_GRP.SHIPPING_FEE);
        list.add(PRC_DTL_GRP.RESTOCKING_FEE); // QC#27479 2018/08/03 Add
        mapParam.put("prcDtlGrpCdList",     list);
        List<String> listRnd = new ArrayList<String>();
        listRnd.add(PRC_DTL_GRP.ROUNDING_FACTOR_1);
        listRnd.add(PRC_DTL_GRP.ROUNDING_FACTOR_2);
        mapParam.put("prcDtlGrpCdRnd",      listRnd);
        mapParam.put("delFlg",              ZYPConstant.FLG_OFF_N);
        mapParam.put("applyFlg",            ZYPConstant.FLG_ON_Y);
        List<Map<String, Object>> resMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getCalcBaseForCharges", mapParam);

        return resMapList;
    }
    public String getLineNumCntStr(int lineNumCnt) {
        return String.format("%05d", lineNumCnt);
    }

    public String getLineSubNumCntStr(int lineSubNumCnt) {
        return String.format("%03d", lineSubNumCnt);
    }

    private List<NWZC040003PMsg> setChargeLineParam(List<Map<String, Object>> chargeList, OrderCloseBean bean, InvImptApiParamBean invImptApiBean) {
        writeStartLogLn("setChargeLineParam");
        List<NWZC040003PMsg> addList = new ArrayList<NWZC040003PMsg>();

        try {

            // *********************************************************************
            // Set Line Params
            // *********************************************************************
            for (Map<String, Object> map : chargeList) {
                NWZC040003PMsg param = new NWZC040003PMsg();
                
//                BigDecimal calcPrcAmt = (BigDecimal) map.get("CALC_PRC_AMT_RATE");
                invImptApiBean.lineNumCnt++;
                invImptApiBean.lineSubNumCnt = 1;

                // Global Company Code(FK)
                ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
                // #Invoice BOL Line Number(FK)
                ZYPEZDItemValueSetter.setValue(param.invBolLineNum, invImptApiBean.getLastPmtInvBolLineNum());
                // #Invoice Line Number
                ZYPEZDItemValueSetter.setValue(param.invLineNum, invImptApiBean.getLineNumCntStr());
                // #Invoice Line Sub Number
                ZYPEZDItemValueSetter.setValue(param.invLineSubNum, invImptApiBean.getLineSubNumCntStr());
                // CPO Detail Line Number
                ZYPEZDItemValueSetter.setValue(param.cpoDtlLineNum, bean.getDsCpoRtrnLineNum());
                // CPO Detail Line Sub Number
                ZYPEZDItemValueSetter.setValue(param.cpoDtlLineSubNum, bean.getDsCpoRtrnLineSubNum());
                // Stock Status Code(FK)
                ZYPEZDItemValueSetter.setValue(param.stkStsCd, STK_STS.GOOD);
                // Sales Rep TOC Code(FK)
                ZYPEZDItemValueSetter.setValue(param.slsRepTocCd, bean.getSlsRepTocCd());
                // Merchandise Code(FK)
                ZYPEZDItemValueSetter.setValue(param.mdseCd, (String) map.get("CHRG_MDSE_CD"));
                // Merchandise Name
                ZYPEZDItemValueSetter.setValue(param.mdseNm, (String) map.get("MDSE_NM"));
                // COA Product Code
                ZYPEZDItemValueSetter.setValue(param.coaProdCd, (String) map.get("COA_PROD_CD"));
                // Transaction Code(FK)
                ZYPEZDItemValueSetter.setValue(param.trxCd, bean.getTrxCd());
                // Transaction Reason Code(FK)
                ZYPEZDItemValueSetter.setValue(param.trxRsnCd, bean.getTrxRsnCd());
                // Order Quantity
                ZYPEZDItemValueSetter.setValue(param.ordQty, changeSign(bean.getOrdQty()));
                // Shipped Quantity
                ZYPEZDItemValueSetter.setValue(param.shipQty, changeSign(bean.getRtrnQty()));
                // Back Order Quantity
                ZYPEZDItemValueSetter.setValue(param.boQty, BigDecimal.ZERO);
                // Manual Pricing Flag
                ZYPEZDItemValueSetter.setValue(param.manPrcFlg, bean.getManPrcFlg());
                // Deal Net Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.dealNetUnitPrcAmt, (BigDecimal) map.get("UNIT_PRC_AMT"));
                // Invoice Line Deal Net Amount
                ZYPEZDItemValueSetter.setValue(param.invLineDealNetAmt, changeSign((BigDecimal) map.get("CALC_PRC_AMT_RATE")));
                // Deal Unit Discount Price Amount
                ZYPEZDItemValueSetter.setValue(param.dealDiscUnitPrcAmt, BigDecimal.ZERO);
                // Func Net Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.funcNetUnitPrcAmt, (BigDecimal) map.get("UNIT_PRC_AMT"));
                // Invoice Line Func Net Amount
                ZYPEZDItemValueSetter.setValue(param.invLineFuncNetAmt, changeSign((BigDecimal) map.get("CALC_PRC_AMT_RATE")));
                // Func Unit Discount Price Amount
                ZYPEZDItemValueSetter.setValue(param.funcDiscUnitPrcAmt, BigDecimal.ZERO);
                // Tax Flag
                ZYPEZDItemValueSetter.setValue(param.taxFlg, bean.getTaxFlg());
                // COA Account Code(FK)
                ZYPEZDItemValueSetter.setValue(param.coaAcctCd, bean.getCoaAcctCd());
                // COA Project Code(FK)
                ZYPEZDItemValueSetter.setValue(param.coaProjCd, bean.getCoaProjCd());
                // Set Merchandise Code(FK)
                param.setMdseCd.clear();
                // Deal Gross Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.dealGrsUnitPrcAmt, (BigDecimal) map.get("UNIT_PRC_AMT"));
                // Deal Gross Total Price Amount
                ZYPEZDItemValueSetter.setValue(param.dealGrsTotPrcAmt, changeSign((BigDecimal) map.get("CALC_PRC_AMT_RATE")));
                // Func Gross Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.funcGrsUnitPrcAmt, (BigDecimal) map.get("UNIT_PRC_AMT"));
                // Func Gross Total Price Amount
                ZYPEZDItemValueSetter.setValue(param.funcGrsTotPrcAmt, changeSign((BigDecimal) map.get("CALC_PRC_AMT_RATE")));
                // Set Ratio Keep Flag(Default Value Set. Update Last)
                ZYPEZDItemValueSetter.setValue(param.setRatioKeepFlg, ZYPConstant.FLG_OFF_N);
                // Machine Config Number
                param.svcConfigMstrPk.clear();
                // Direct Sales Contract Number
                param.dsContrNum.clear();
                // Direct Sales Contract Squence Number
                param.dsContrSqNum.clear();
                // Billing Attribute Value Text 1
                ZYPEZDItemValueSetter.setValue(param.firstBllgAttrbValTxt, bean.getFirstBllgAttrbValTxt());
                // Billing Attribute Value Text 2
                ZYPEZDItemValueSetter.setValue(param.scdBllgAttrbValTxt, bean.getScdBllgAttrbValTxt());
                // Billing Attribute Value Text 3
                ZYPEZDItemValueSetter.setValue(param.thirdBllgAttrbValTxt, bean.getThirdBllgAttrbValTxt());
                // Billing Attribute Value Text 4
                ZYPEZDItemValueSetter.setValue(param.frthBllgAttrbValTxt, bean.getFrthBllgAttrbValTxt());
                // Billing Attribute Value Text 5
                ZYPEZDItemValueSetter.setValue(param.fifthBllgAttrbValTxt, bean.getFifthBllgAttrbValTxt());
                // Billing Attribute Value Text 6
                ZYPEZDItemValueSetter.setValue(param.sixthBllgAttrbValTxt, bean.getSixthBllgAttrbValTxt());
                // Unit of Measure Code
                ZYPEZDItemValueSetter.setValue(param.uomCd, bean.getCustUomCd());
                // Tax Calculatted Geo Code+
                NWZC036101_taxCalculateOutputLinePMsg lastMdseTaxMsg = invImptApiBean.getLastMdseTaxResult();
                if (lastMdseTaxMsg != null) {
                    ZYPEZDItemValueSetter.setValue(param.taxCalcGeoCd, lastMdseTaxMsg.taxAreaId);
                }
                // DS CPO Position Number
                ZYPEZDItemValueSetter.setValue(param.dsOrdPosnNum, bean.getDsOrdPosnNum());
                // DS CPO Line Number
                ZYPEZDItemValueSetter.setValue(param.dsCpoLineNum, bean.getDsCpoLineNum());
                // DS CPO Line Sub number
                ZYPEZDItemValueSetter.setValue(param.dsCpoLineSubNum, bean.getDsCpoLineSubNum());
                // Price Category Code
                ZYPEZDItemValueSetter.setValue(param.prcCatgCd, bean.getPrcCatgCd());
                // DS Order Line Category Code
                ZYPEZDItemValueSetter.setValue(param.dsOrdLineCatgCd, bean.getDsOrdLineCatgCd());
                if (PRC_DTL_GRP.FREIGHT.equals((String) map.get("PRC_DTL_GRP_CD"))) {
                    ZYPEZDItemValueSetter.setValue(param.invLineCatgCd, INV_LINE_CATG.FREIGHT);
                } else {
                    ZYPEZDItemValueSetter.setValue(param.invLineCatgCd, INV_LINE_CATG.CHARGE);
                }

                invImptApiBean.getInvLinePMsgList().add(param);
                addList.add(param);
            }

            return addList;
        } finally {
            writeEndLogLn("setChargeLineParam");
        }
    }

    private Boolean setChagePromotionParam(List<NWZC040003PMsg> chargeList, OrderCloseBean bean, InvImptApiParamBean invImptApiBean) {
        writeStartLogLn("setChagePromotionParam");

        try {

            NWZC040004PMsg param;

            for (NWZC040003PMsg data : chargeList) {
                param = new NWZC040004PMsg();

                // Global Company Code(FK)
                ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
                // Invoice BOL Line Number(FK)
                ZYPEZDItemValueSetter.setValue(param.invBolLineNum, data.invBolLineNum);
                // Invoice Line Number(FK)
                ZYPEZDItemValueSetter.setValue(param.invLineNum, data.invLineNum);
                // Invoice Line Sub Number(FK)
                ZYPEZDItemValueSetter.setValue(param.invLineSubNum, data.invLineSubNum);
                // Promotion Category Primary Key
                param.prmoCatgPk.clear();
                // Promotion Primary Key(FK)
                param.prmoPk.clear();
                // Deal Primary Key
                param.dealPk.clear();
                // Pricing Date
                param.prcDt.clear();
                // Promotion Quantity
                ZYPEZDItemValueSetter.setValue(param.prmoQty, data.shipQty);
                // Deal Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.dealUnitPrcAmt, data.dealGrsUnitPrcAmt);
                // Deal Last Net Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.dealLastNetUnitPrcAmt, data.dealGrsUnitPrcAmt);
                // Deal Net Amount
                ZYPEZDItemValueSetter.setValue(param.dealNetAmt, data.invLineDealNetAmt);
                // Deal Discount Amount
                ZYPEZDItemValueSetter.setValue(param.dealDiscAmt, BigDecimal.ZERO);
                // Deal Promotion Net Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.dealPrmoNetUnitPrcAmt, changeSign(data.dealNetUnitPrcAmt.getValue()));
                // Deal Per Unit Fix Amount
                ZYPEZDItemValueSetter.setValue(param.dealPerUnitFixAmt, BigDecimal.ZERO);
                // Deal Sales Percent Number
                ZYPEZDItemValueSetter.setValue(param.dealSlsPctNum, BigDecimal.ZERO);
                // Function Per Unit Fix Amount
                ZYPEZDItemValueSetter.setValue(param.funcPerUnitFixAmt,  BigDecimal.ZERO);
                // Function Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.funcUnitPrcAmt, data.funcGrsUnitPrcAmt);
                // Function Last Net Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.funcLastNetUnitPrcAmt, data.funcGrsUnitPrcAmt);
                // Function Net Amount
                ZYPEZDItemValueSetter.setValue(param.funcNetAmt, data.invLineFuncNetAmt);
                // Function Discount Amount
                ZYPEZDItemValueSetter.setValue(param.funcDiscAmt, data.funcDiscUnitPrcAmt);
                // Function Promotion Net Unit Price Amount
                ZYPEZDItemValueSetter.setValue(param.funcPrmoNetUnitPrcAmt, changeSign(data.funcNetUnitPrcAmt.getValue()));
                // COA Account Code(FK)
                param.coaAcctCd.clear();
                // Invoice Promotion Information Sequence Number
                ZYPEZDItemValueSetter.setValue(param.invPrmoInfoSqNum, "001");

                invImptApiBean.getPromoPMsgList().add(param);
            }

            return true;
        } finally {
            writeEndLogLn("setChagePromotionParam");
        }
    }
    
    private void setTaxCalcLineParam(List<NWZC040003PMsg> chargeList, OrderCloseBean bean, InvImptApiParamBean invImptApiBean, NWZC036101PMsg param) {

        int taxCalcLineNum = param.taxCalculateInputLine.getValidCount();
        for (NWZC040003PMsg data : chargeList) {
            NWZC036101_taxCalculateInputLinePMsg paramLine = (NWZC036101_taxCalculateInputLinePMsg) param.taxCalculateInputLine.no(taxCalcLineNum++);
            param.taxCalculateInputLine.setValidCount(taxCalcLineNum);

            ZYPEZDItemValueSetter.setValue(paramLine.xxTaxCalcLineNum_A, String.valueOf(taxCalcLineNum));
            // Merchandise Code
            ZYPEZDItemValueSetter.setValue(paramLine.mdseCd_A, data.mdseCd);
            // Service Allocation Type
            ZYPEZDItemValueSetter.setValue(paramLine.svcAllocTpCd_A, bean.getSvcAllocTpCd());
            // Trx Type
            String svcAllocTrxTpNm = bean.getTrxTpNm();
            if (!hasValue(svcAllocTrxTpNm)) {
                if (this.defaultSvcAllocTrxTpNm == null) {
                    this.defaultSvcAllocTrxTpNm = ZYPCodeDataUtil.getVarCharConstValue("DEFAULT_TAX_TRX_TP", glblCmpyCd);
                }
                svcAllocTrxTpNm = defaultSvcAllocTrxTpNm;
            }
            ZYPEZDItemValueSetter.setValue(paramLine.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
            // Product Tax Code
            ZYPEZDItemValueSetter.setValue(paramLine.taxExemTpCd_A, bean.getTaxExemTpCd());
            // Shipped Quantity
            // QC#28861 2018/10/19 Add Start
            // ZYPEZDItemValueSetter.setValue(paramLine.shipQty_A, data.shipQty);
            ZYPEZDItemValueSetter.setValue(paramLine.shipQty_A, changeSign(data.shipQty.getValue()));
            // QC#28861 2018/10/19 Add End
            // Function Net Unit Price Amount
            // Sales Amount
            ZYPEZDItemValueSetter.setValue(paramLine.funcNetUnitPrcAmt_A, data.funcNetUnitPrcAmt);
            // QC#28861 2018/10/19 Add Start
            // ZYPEZDItemValueSetter.setValue(paramLine.slsAmt_A, data.invLineDealNetAmt);
            ZYPEZDItemValueSetter.setValue(paramLine.slsAmt_A, changeSign(data.invLineDealNetAmt.getValue()));
            // QC#28861 2018/10/19 Add End
            // Bill To Account Number
            ZYPEZDItemValueSetter.setValue(paramLine.billToAcctNum_A, bean.getBillToCustAcctCd());
            // Bill To Location Number
            ZYPEZDItemValueSetter.setValue(paramLine.billToLocNum_A, bean.getBillToCustLocCd());
            // Bill to Vertex Group Exemption
            if (ZYPCommonFunc.hasValue(bean.getDsTaxGrpExemCdBlLoc())) {
                ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AB, bean.getDsTaxGrpExemCdBlLoc());
            } else {
                ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AB, bean.getDsTaxGrpExemCdBlAcct());
            }

            // Bill to Vertex Group Exemption Resale Flg
            if (ZYPCommonFunc.hasValue(bean.getDsTaxGrpExemReslFlgLoc())) {
                ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemReslFlg_AB, bean.getDsTaxGrpExemReslFlgLoc());
            } else {
                ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemReslFlg_AB, bean.getDsTaxGrpExemReslFlgAcct());
            }

            // S21_NA#27299 2018/11/15 Mod Start
            if (bean.isMixOrder()) {
                // Ship To Account Number
                ZYPEZDItemValueSetter.setValue(paramLine.dsAcctNum_AT, bean.getOrigShipToCustAcctCd());
                // Ship To Location Number
                ZYPEZDItemValueSetter.setValue(paramLine.shipToLocNum_A, bean.getOrigShipToCustLocCd());
                // Ship to Vertex Group Exemption
                if (ZYPCommonFunc.hasValue(bean.getOrigDsTaxGrpExemCdSlLoc())) {
                    ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AT, bean.getOrigDsTaxGrpExemCdSlLoc());
                } else {
                    ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AT, bean.getOrigDsTaxGrpExemCdSlAcct());
                }
                // Ship To Tax Area ID
                ZYPEZDItemValueSetter.setValue(paramLine.geoCd_AT, bean.getOrigShipToTaxAreaId());
                // Ship To Inside City Limit Flag
                ZYPEZDItemValueSetter.setValue(paramLine.dsInsdCtyLimitFlg_AT, bean.getOrigShipToInsdCtyLimitFlg());
                // Ship To Special Tax Area ID
                ZYPEZDItemValueSetter.setValue(paramLine.taxAreaId_AT, bean.getOrigShipToSpclTaxAreaId());
                // Ship to First Line Address
                ZYPEZDItemValueSetter.setValue(paramLine.firstLineAddr_AT, bean.getOrigShipToFirstLineAddr());
                // Ship to Second Line Address
                ZYPEZDItemValueSetter.setValue(paramLine.scdLineAddr_AT, bean.getOrigShipToScdLineAddr());
                // Ship to City Address
                ZYPEZDItemValueSetter.setValue(paramLine.ctyAddr_AT, bean.getOrigShipToCtyAddr());
                // Ship to State Code
                ZYPEZDItemValueSetter.setValue(paramLine.stCd_AT, bean.getOrigShipToStCd());
                // Ship To County Name
                ZYPEZDItemValueSetter.setValue(paramLine.cntyNm_AT, bean.getOrigShipToCntyNm());
                // Ship To Post Code
                ZYPEZDItemValueSetter.setValue(paramLine.postCd_AT, bean.getOrigShipToPostCd());
                // Ship To Country Code
                ZYPEZDItemValueSetter.setValue(paramLine.ctryCd_AT, bean.getOrigShipToCtryCd());
            } else {
            // Ship To Account Number
            ZYPEZDItemValueSetter.setValue(paramLine.dsAcctNum_AT, bean.getShipToCustAcctCd());
            // Ship To Location Number
            ZYPEZDItemValueSetter.setValue(paramLine.shipToLocNum_A, bean.getShipToCustLocCd());
            // Ship to Vertex Group Exemption
            if (ZYPCommonFunc.hasValue(bean.getDsTaxGrpExemCdSlLoc())) {
                ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AT, bean.getDsTaxGrpExemCdSlLoc());
            } else {
                ZYPEZDItemValueSetter.setValue(paramLine.dsTaxGrpExemCd_AT, bean.getDsTaxGrpExemCdSlAcct());
            }
            // Ship To Tax Area ID
            ZYPEZDItemValueSetter.setValue(paramLine.geoCd_AT, bean.getShipToTaxAreaId());
            // Ship To Inside City Limit Flag
            ZYPEZDItemValueSetter.setValue(paramLine.dsInsdCtyLimitFlg_AT, bean.getShipToInsdCtyLimitFlg());
            // Ship To Special Tax Area ID
            ZYPEZDItemValueSetter.setValue(paramLine.taxAreaId_AT, bean.getShipToSpclTaxAreaId());
            // Ship to First Line Address
            ZYPEZDItemValueSetter.setValue(paramLine.firstLineAddr_AT, bean.getShipToFirstLineAddr());
            // Ship to Second Line Address
            ZYPEZDItemValueSetter.setValue(paramLine.scdLineAddr_AT, bean.getShipToScdLineAddr());
            // Ship to City Address
            ZYPEZDItemValueSetter.setValue(paramLine.ctyAddr_AT, bean.getShipToCtyAddr());
            // Ship to State Code
            ZYPEZDItemValueSetter.setValue(paramLine.stCd_AT, bean.getShipToStCd());
            // Ship To County Name
            ZYPEZDItemValueSetter.setValue(paramLine.cntyNm_AT, bean.getShipToCntyNm());
            // Ship To Post Code
            ZYPEZDItemValueSetter.setValue(paramLine.postCd_AT, bean.getShipToPostCd());
            // Ship To Country Code
            ZYPEZDItemValueSetter.setValue(paramLine.ctryCd_AT, bean.getShipToCtryCd());
            }

            // Sales Rep Tax Area ID
            ZYPEZDItemValueSetter.setValue(paramLine.geoCd_AR, bean.getSlsReqTaxAreaId());
            // Sales Rep Inside City Limit Flag
            ZYPEZDItemValueSetter.setValue(paramLine.dsInsdCtyLimitFlg_AR, bean.getSlsReqInsdCtyLimitFlg());
            // Sales Rep First Line Address
            ZYPEZDItemValueSetter.setValue(paramLine.firstLineAddr_AR, bean.getSlsReqFirstLineAddr());
            // Sales Rep Second Line Address
            ZYPEZDItemValueSetter.setValue(paramLine.scdLineAddr_AR, bean.getSlsReqScdLineAddr());
            // Sales Rep City Address
            ZYPEZDItemValueSetter.setValue(paramLine.ctyAddr_AR, bean.getSlsReqCtyAddr());
            // Sales Rep State Code
            ZYPEZDItemValueSetter.setValue(paramLine.stCd_AR, bean.getSlsReqStCd());
            // Sales Rep County Name
            ZYPEZDItemValueSetter.setValue(paramLine.cntyNm_AR, bean.getSlsReqCntyNm());
            // Sales Rep Post Code
            ZYPEZDItemValueSetter.setValue(paramLine.postCd_AR, bean.getSlsReqPostCd());
            // Sales Rep Country Code
            ZYPEZDItemValueSetter.setValue(paramLine.ctryCd_AR, bean.getSlsReqCtryCd());

            // S21_NA#27299 2018/11/15 Mod Start
            if (bean.isMixOrder()) {
                // Ship from Tax Area ID
                ZYPEZDItemValueSetter.setValue(paramLine.geoCd_AF, bean.getOrigShipFromTaxAreaId());
                // Ship from WH Code
                ZYPEZDItemValueSetter.setValue(paramLine.whCd_AF, bean.getOrigShipFromWhCd());
                // Ship from First Line Address
                ZYPEZDItemValueSetter.setValue(paramLine.firstLineAddr_AF, bean.getOrigShipFromFirstLineAddr());
                // Ship from Second Line Address
                ZYPEZDItemValueSetter.setValue(paramLine.scdLineAddr_AF, bean.getOrigShipFromScdLineAddr());
                // Ship from City Address
                ZYPEZDItemValueSetter.setValue(paramLine.ctyAddr_AF, bean.getOrigShipFromCtyAddr());
                // Ship from State Code
                ZYPEZDItemValueSetter.setValue(paramLine.stCd_AF, bean.getOrigShipFromStCd());
                // Ship from County Name
                ZYPEZDItemValueSetter.setValue(paramLine.cntyNm_AF, bean.getOrigShipFromCntyNm());
                // Ship from Post Code
                ZYPEZDItemValueSetter.setValue(paramLine.postCd_AF, bean.getOrigShipFromPostCd());
                // Ship from Country Code
                ZYPEZDItemValueSetter.setValue(paramLine.ctryCd_AF, bean.getOrigShipFromCtryCd());
            } else {
            // Ship from Tax Area ID
            ZYPEZDItemValueSetter.setValue(paramLine.geoCd_AF, bean.getShipFromTaxAreaId());
            // Ship from WH Code
            ZYPEZDItemValueSetter.setValue(paramLine.whCd_AF, bean.getShipFromWhCd());
            // Ship from First Line Address
            ZYPEZDItemValueSetter.setValue(paramLine.firstLineAddr_AF, bean.getShipFromFirstLineAddr());
            // Ship from Second Line Address
            ZYPEZDItemValueSetter.setValue(paramLine.scdLineAddr_AF, bean.getShipFromScdLineAddr());
            // Ship from City Address
            ZYPEZDItemValueSetter.setValue(paramLine.ctyAddr_AF, bean.getShipFromCtyAddr());
            // Ship from State Code
            ZYPEZDItemValueSetter.setValue(paramLine.stCd_AF, bean.getShipFromStCd());
            // Ship from County Name
            ZYPEZDItemValueSetter.setValue(paramLine.cntyNm_AF, bean.getShipFromCntyNm());
            // Ship from Post Code
            ZYPEZDItemValueSetter.setValue(paramLine.postCd_AF, bean.getShipFromPostCd());
            // Ship from Country Code
            ZYPEZDItemValueSetter.setValue(paramLine.ctryCd_AF, bean.getShipFromCtryCd());
        }
        }

    }
    // QC#21841 2018/05/21 Add End

    // 2018/08/21 QC#27443 Add Start
    private MDSETMsg getDsMdseInfo(String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        setValue(mdseTMsg.mdseCd, mdseCd);
        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            // get mdsecd
            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
            setValue(ordTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
            setValue(ordTakeMdseTMsg.ordTakeMdseCd, mdseCd);
            ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);

            mdseTMsg = new MDSETMsg();
            setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
            setValue(mdseTMsg.mdseCd, ordTakeMdseTMsg.mdseCd.getValue());
            mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        }

        return mdseTMsg;

    }
    // 2018/08/21 QC#27443 Add End
    
    // 2018/09/20 S21_NA#28327 Add Start
    private String getNetDueDt(OrderCloseBean bean){
     // Due Date Calculation API
        NFZC309001PMsg param = new NFZC309001PMsg();
        setValue(param.glblCmpyCd, glblCmpyCd);
        setValue(param.pmtTermCashDiscCd, bean.getPmtTermCashDiscCd());
        setValue(param.trxDt, this.salesDate);
 
        NFZC309001 dueDateCalculationAPI = new NFZC309001();
        dueDateCalculationAPI.execute(param, ONBATCH_TYPE.BATCH);
        if (param.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < param.xxMsgIdList.getValidCount(); i++) {
                S21InfoLogOutput.println(param.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return null;
        }
        return param.dueDt.getValue();
    }
    // 2018/09/20 S21_NA#28327 Add End

    // 2018/11/15 S21_NA#27954 Add Start
    private String setOrdCatgBizCtx(OrderCloseBean bean) {

        List<Map<String, Object>> ordCatgBizCtxList = new ArrayList<Map<String, Object>>();
        Map<String, Object> ordCatgBizCtxMap = new HashMap<String, Object>();

        String ctacTpCd = null;

        ordCatgBizCtxList = getOrdCatgBizCtxList(glblCmpyCd, ORD_CATG_CTX_TP_SUPPLIES_ORDER, bean);

        if (!ordCatgBizCtxList.isEmpty()) {
            ordCatgBizCtxMap = getOrdCatgBizCtx(ordCatgBizCtxList, bean);

            if (!ordCatgBizCtxMap.isEmpty()) {
                if (ZYPCommonFunc.hasValue((String) ordCatgBizCtxMap.get("FIRST_BIZ_CTX_ATTRB_TXT"))) {
                    String ordCatgBizCtx = (String) ordCatgBizCtxMap.get("FIRST_BIZ_CTX_ATTRB_TXT");
                    if (DS_TRX_RULE_TP.PARTS.equals(ordCatgBizCtx)) {
                        ctacTpCd = CTAC_TP.BILL_TO_CONTACT_PARTS;
                    } else if (DS_TRX_RULE_TP.SUPPLIES.equals(ordCatgBizCtx)) {
                        ctacTpCd = CTAC_TP.BILL_TO_SUPPLIES;
                    }
                }
            }
        }
  
        if (!ZYPCommonFunc.hasValue(ctacTpCd)) {
            ordCatgBizCtxList = getOrdCatgBizCtxList(glblCmpyCd, ORD_CATG_CTX_TP_EQUIPMENT_ORDER, bean);

            if (!ordCatgBizCtxList.isEmpty()) {
                ordCatgBizCtxMap = getOrdCatgBizCtx(ordCatgBizCtxList, bean);
                
                if (!ordCatgBizCtxMap.isEmpty()) {
                    ctacTpCd = CTAC_TP.BILL_TO_CONTACT_EQUIPMENT;
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(ctacTpCd)) {
            ctacTpCd = CTAC_TP.BILL_TO_CONTACT_EQUIPMENT;
        }
        
        return ctacTpCd;
    }

    private Map<String, Object> getOrdCatgBizCtx(List<Map<String, Object>> ordCatgBizCtxList, OrderCloseBean bean) {

        Map<String, Object> resMap = new HashMap<String, Object>();

        String dsOrdTpCd = bean.getDsOrdTpCd();
        String dsOrdRsnCd = bean.getDsOrdRsnCd();

        resMap = getOrdCatgBizCtxMap(ordCatgBizCtxList, dsOrdTpCd, dsOrdRsnCd);

        if (resMap.isEmpty()) {
            resMap = getOrdCatgBizCtxMap(ordCatgBizCtxList, dsOrdTpCd, null);
        }


        if (resMap.isEmpty()) {
            resMap = getOrdCatgBizCtxMap(ordCatgBizCtxList, null, null);
        }

        return resMap;
    }

    private Map<String, Object> getOrdCatgBizCtxMap(List<Map<String, Object>> ordCatgBizCtxList, String dsOrdTpCd, String dsOrdRsnCd) {

        Map<String, Object> resMap = new HashMap<String, Object>();

        if (ZYPCommonFunc.hasValue(dsOrdTpCd) && ZYPCommonFunc.hasValue(dsOrdRsnCd)) {
            for (Map<String, Object> map : ordCatgBizCtxList) {
                if (dsOrdTpCd.equals((String) map.get("DS_ORD_TP_CD"))
                 && dsOrdRsnCd.equals((String) map.get("DS_ORD_RSN_CD"))) {
                    resMap = map;
                    break;
                }
            }

        } else if (ZYPCommonFunc.hasValue(dsOrdTpCd) && !ZYPCommonFunc.hasValue(dsOrdRsnCd)) {
            for (Map<String, Object> map : ordCatgBizCtxList) {
                if (dsOrdTpCd.equals((String) map.get("DS_ORD_TP_CD"))
                 && !ZYPCommonFunc.hasValue((String) map.get("DS_ORD_RSN_CD"))) {
                    resMap = map;
                    break;
                }
            }

        } else if (!ZYPCommonFunc.hasValue(dsOrdTpCd) && !ZYPCommonFunc.hasValue(dsOrdRsnCd)) {
            for (Map<String, Object> map : ordCatgBizCtxList) {
                if (!ZYPCommonFunc.hasValue((String) map.get("DS_ORD_TP_CD"))
                 && !ZYPCommonFunc.hasValue((String) map.get("DS_ORD_RSN_CD"))) {
                    resMap = map;
                    break;
                }
            }

        }
        
        return resMap;
    }

    private List<Map<String, Object>> getOrdCatgBizCtxList(String glblCmpyCd, String OrdCatgCtxTpCd, OrderCloseBean bean) {

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("OrdCatgCtxTpCd", OrdCatgCtxTpCd);
        mapParam.put("dsOrdCatgCd", bean.getDsOrdCatgCd());
        List<Map<String, Object>> resMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOrdCatgBizCtx", mapParam);

        return resMapList;
    }
    // 2018/11/15 S21_NA#27954 Add End
    // 2018/12/14 QC#29630 add Start
    private String getInvNum(String glblCmpyCd, String dsInvTpCd) {

        DS_INV_TPTMsg dsInvTpTMsg = new DS_INV_TPTMsg();
        setValue(dsInvTpTMsg.glblCmpyCd, glblCmpyCd);
        setValue(dsInvTpTMsg.dsInvTpCd,  dsInvTpCd);
        dsInvTpTMsg = (DS_INV_TPTMsg) S21CacheTBLAccessor.findByKey(dsInvTpTMsg);

        return ZYPMaxTenDigitsNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue());
    }
    // 2018/12/14 QC#29630 add End

    // START 2022/05/11 R.Azucena [QC#59959, ADD]
    /**
     * isItemAllocated
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @return boolean
     */
    private boolean isItemAllocated(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        if (hasValue(svcMachMstrTMsg.trxHdrNum)) {
            SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
            shpgOrdTMsg.setSQLID("001");
            shpgOrdTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            shpgOrdTMsg.setConditionValue("trxHdrNum01", svcMachMstrTMsg.trxHdrNum.getValue());
            SHPG_ORDTMsgArray shpgOrdTMsgList = (SHPG_ORDTMsgArray) EZDTBLAccessor.findByCondition(shpgOrdTMsg);

            if (shpgOrdTMsgList != null && shpgOrdTMsgList.getValidCount() > 0
                    && !TRX_SRC_TP.WHOLE_SALES_RETURN.equals(shpgOrdTMsgList.no(0).trxSrcTpCd.getValue())) {
                return true;
            }
        }

        return false;
    }
    // END 2022/05/11 R.Azucena [QC#59959, ADD]
}
