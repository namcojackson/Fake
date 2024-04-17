package com.canon.cusa.s21.api.NPZ.NPZC130001;

import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.AM;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.APVL_HRCH_TP_CD_EMPLOYEE;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.APVL_HRCH_TP_CD_POSITIONAL;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.BIZ_ID_NPZC13000101;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.BIZ_ID_NPZC13000102;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.BIZ_ID_NPZC13000103;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.BIZ_ID_NPZC13000104;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.BIZ_ID_NPZC13000105;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.BIZ_SCRN_ID_NPAL1080;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.BIZ_SCRN_ID_NPAL1280;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.BIZ_SCRN_ID_NPAL1500;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.BIZ_SCRN_ID_NPBL0020;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.DT_FMT_HH12MI;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.DT_FMT_YYYYMM;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.DT_FMT_YYYYMMDD;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.DT_FMT_YYYYMMDDHHMMSS;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.FIRST_DT_OF_MONTH;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.INV_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.LOC_STS_CD_DC_STOCK;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.LOC_STS_CD_IN_TRANSIT;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.LOC_STS_CD_IN_TRANSIT_WH;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NFDM0008E;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPAM0059E;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPAM1603E;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0035E;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0179E;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0180E;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0190E;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0210E;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0211E;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0212E;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0213E;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0314I;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0315I;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0316I;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.NPZM0317I;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.PARTS_ITEM;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.PM;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.PO_CUST_DROP_SHIP_QLFY_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.PO_MDSE_CMPSN_TP_CD_CHILD;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.PO_MDSE_TP_CD_MDSE;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.PO_MDSE_TP_CD_PARTS;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.PO_MSG_TP_CD_INTERNAL_MSG;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.PO_STS_CD_CANCELED;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.PO_WF_NTFY_OTPT_REC_CNT;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.PRCH_REQ_LINE_STS_CANCELED;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.RTRN_CD_NORMAL;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.STK_STS_CD_GOOD;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WFMD_INVTY_REQ;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WFMD_PO;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WFMD_PO_REQ;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WFMD_TECH_REQ;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WFMD_TECH_REQ_RUSH;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WFNM_INVTY_REQ;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WFNM_PO;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WFNM_PO_REQ;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WFNM_TECH_REQ;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WFNM_TECH_REQ_RUSH;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WF_LBL_1_PO;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WF_LBL_1_PRCH;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WF_LBL_2_PO;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WF_LBL_2_PRCH;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WF_LBL_3;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WF_LBL_4_PO;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WF_LBL_4_PRCH;
import static com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant.WF_LBL_5;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMessageInfo;
import parts.dbcommon.EZDTBLAccessor;
import business.db.GLBL_CMPYTMsg;
import business.db.NUM_CONSTTMsg;
import business.db.POTMsg;
import business.db.PRCH_REQTMsg;
import business.db.RTL_WHTMsg;
import business.parts.NPZC130001PMsg;

import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistory;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistoryBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HRCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRT_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfAuthException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * PO/PR Approval to WF API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/05/2016   CITS            M.Ito           Create          N/A
 * 2016/04/15   CITS            K.Ogino         Update          QC#6953
 * 2016/04/20   CITS            K.Ogino         Update          QC#7237
 * 2016/04/20   CITS            K.Ogino         Update          QC#7238
 * 04/26/2016   CITS            Hisashi         Update          QC#7314
 * 05/04/2016   CSAI            K.Lee           Update          QC#5841
 * 08/12/2016   CITS            K.Ogino         Update          QC#13325
 * 01/27/2017   CITS            R.Shimamoto     Update          QC#17220
 * 02/02/2017   CITS            R.Shimamoto     Update          QC#17220-1
 * 09/01/2017   CITS            R.Shimamoto     Update          QC#20439
 * 02/18/2019   CITS            K.Ogino         Update          QC#30355
 * 01/23/2023   CITS            T.Suzuki        Update          QC#61093
 * 02/17/2023   CITS            A.Cullano       Update          QC#61175
 * 04/27/2023   Hitachi         T.Kuroda        Update          QC#61245
 * 05/19/2023   Hitachi         T.Kuroda        Update          QC#61211
 * 08/29/2023   Hitachi         M.Kikushima     Update          QC#61590
 *</pre>
 */
public class NPZC130001 extends S21ApiCommonBase {

    // -------------------------------------------------
    // Instance Fields
    // -------------------------------------------------

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Online Batch Type Code */
    private ONBATCH_TYPE onBatTpCd = null;

    /** S21User */
    private S21UserInfo userInfo = null;

    /** GLBL_CMPY_CD */
    private String glblCmpyCd = null;

    /** SLS_DT */
    private String slsDt = null;

    /** XX_MODE */
    private String xxModeCd = null;

    /** XX_PROC_TP_CD */
    private String xxProcTpCd = null;

    /** TRX_REF_NUM */
    private String trxRefNum = null;

    /** APVL_HIST_SRC_TP_CD */
    private String apvlHistSrcTpCd = null;

    /** Work Flow Definition Name */
    private String wfDefNm = null;

    /**
     * Constructor
     */
    public NPZC130001() {
        super();
    }

    /**
     * PO/PR Approval to WF API
     * @param param pMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC130001PMsg param, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(param);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.onBatTpCd = onBatchType;
        this.userInfo = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo();

        NPZC130001PMsg pMsg = (NPZC130001PMsg) this.msgMap.getPmsg();
        doProcess(pMsg);
        this.msgMap.flush();
    }

    /**
     * @param pMsg
     */
    private void doProcess(NPZC130001PMsg pMsg) {
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Approve API Start. ", this);

        checkInputParam(pMsg);

        if (hasError()) {
            return;
        }

        setInputParam(pMsg);

        checkExistActiveWF();

        NPZC130001TokenObject tokenBiz = startWorkflowPreparing();

        if (hasError()) {
            return;
        }

        if (tokenBiz != null) {
            startWorkflow(this.wfDefNm, tokenBiz);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Approve API End. ", this);
    }

    private void checkExistActiveWF() {
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process start [checkExistActiveWF]", this);

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;

        try {
            context = factory.getContex();

            // Search the workflow ID of same process number.
            // getProcess param: Workflow Name, DocumetnId(process
            // number)
            List<S21NwfProcess> processes = context.getProcess(this.wfDefNm, this.trxRefNum);

            for (S21NwfProcess p : processes) {
                if (p.isActive()) {
                    // Cancel active Workflow
                    p.getToken().cancel();
                }
            }
        // START 2017/09/01 QC#20439
        } catch (S21NwfAuthException e) {
        	// Auth Error
        	addError(NPAM1603E);
            return;
        // END 2017/09/01 QC#20439
        } catch (S21NwfSystemException e) {
            // System Error Logic
            addError(NFDM0008E);
            return;
        } catch (S21NwfBizException e) {
            this.msgMap.addXxMsgIdWithPrm(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
        } catch (S21NwfException e) {
            addError(NFDM0008E);
            return;
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process end [checkExistActiveWF]", this);
    }

    private NPZC130001TokenObject startWorkflowPreparing() {
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process start [startWorkflowPreparing]", this);

        // for set to TokenObject
        Map<String, Object> mapResHdr = null;
        List<Map<String, Object>> mapResList = null;
        List<BigDecimal> avalQtyForPOReqList = new ArrayList<BigDecimal>();
        List<BigDecimal> curMthAmtForPOReqList = new ArrayList<BigDecimal>();
        List<BigDecimal> preMthAmtForPOReqList = new ArrayList<BigDecimal>();
        SvcTimeZoneInfo info = null;
        List<BigDecimal> minQtyForTechReqRushList = new ArrayList<BigDecimal>();
        List<BigDecimal> maxQtyForTechReqRushList = new ArrayList<BigDecimal>();
        List<BigDecimal> transitQtyForTechReqRushList = new ArrayList<BigDecimal>();
        List<BigDecimal> avalQtyForTechReqRushList = new ArrayList<BigDecimal>();
        BigDecimal totDealNetAmt = new BigDecimal(0);
        List<String> prchReqLineNumList = new ArrayList<String>();
        List<BigDecimal> prchReqLineSubNumList = new ArrayList<BigDecimal>();
        List<BigDecimal> unitPrcAmtList = new ArrayList<BigDecimal>();
        List<BigDecimal> totPrcAmtList = new ArrayList<BigDecimal>();
        String dealCcyCdForPOTechReqRush = "";
        BigDecimal numConstVal = new BigDecimal(0);

        // **********************
        // Get TokenObject data
        // **********************

        if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.PO_ENTRY)) {
            // ***************************************************************************
            // Approval history Source Type Code : PO Entry
            // ***************************************************************************

            // ### get header data ###
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("poOrdNum", this.trxRefNum);
            paramMap.put("mdseTpCtxTpCd", PARTS_ITEM);
            paramMap.put("poStsCd", PO_STS_CD_CANCELED);
            paramMap.put("poMsgTpCd", PO_MSG_TP_CD_INTERNAL_MSG);
            paramMap.put("apvlHrchTpCd", APVL_HRCH_TP_CD_POSITIONAL);
            paramMap.put("parts", PO_MDSE_TP_CD_PARTS);
            paramMap.put("mdse", PO_MDSE_TP_CD_MDSE);

            mapResHdr = getSsmQueryObject("getPOReqHdrData", paramMap);
            if (mapResHdr == null) {
                addError(NPAM0059E);
                return null;
            }

            // ### get line data ###
            paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("poOrdNum", this.trxRefNum);

            mapResList = getSsmQueryObjectList("getPOReqLineData", paramMap);

            // judging if it is Customer Drop Ship or not
            boolean isNotCustDropShip = false;

            POTMsg poTMsg = new POTMsg();
            ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, this.trxRefNum);
            poTMsg = (POTMsg) EZDTBLAccessor.findByKeyForUpdate(poTMsg);

            if (poTMsg != null) {
                String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(PO_CUST_DROP_SHIP_QLFY_CD, this.glblCmpyCd);
                if (!varCharConstVal.equals(poTMsg.poQlfyCd.getValue())) {
                    isNotCustDropShip = true;
                }
            }

            for (Map<String, Object> mapRes : mapResList) {

                // CUR_INVTY_AVAL_QTY
                if (isNotCustDropShip) {
                    paramMap = new HashMap<String, Object>();
                    paramMap.put("glblCmpyCd", this.glblCmpyCd);
                    paramMap.put("poOrdNum", this.trxRefNum);
                    paramMap.put("poOrdDtlLineNum", (String) mapRes.get("TRX_REF_LINE_NUM"));
                    paramMap.put("locStsCd", LOC_STS_CD_DC_STOCK);
                    paramMap.put("stkStsCd", STK_STS_CD_GOOD);
                    Map<String, Object> mapResOther1 = getSsmQueryObject("getAvalQtyForPOReq", paramMap);
                    if (mapResOther1 != null) {
                        avalQtyForPOReqList.add((BigDecimal) mapResOther1.get("INVTY_AVAL_QTY"));
                    } else {
                        avalQtyForPOReqList.add(BigDecimal.ZERO);
                    }
                } else {
                    avalQtyForPOReqList.add(BigDecimal.ZERO);
                }

                // CUR_MTH_SALES_AMT, PREV_MTH_SALES_AMT
                paramMap = new HashMap<String, Object>();
                paramMap.put("glblCmpyCd", this.glblCmpyCd);
                paramMap.put("slsDt", this.slsDt);
                paramMap.put("first", FIRST_DT_OF_MONTH);
                paramMap.put("invTpCd", INV_TP_CD);
                paramMap.put("mdseCd", (String) mapRes.get("MDSE_CD"));
                paramMap.put("dtFmt", DT_FMT_YYYYMMDD);
                paramMap.put("yyyymm", DT_FMT_YYYYMM);
                // QC#17220-1 Add.
                paramMap.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
                Map<String, Object> mapResOther2 = getSsmQueryObject("getCurSlsAmtForPOReq", paramMap);
                Map<String, Object> mapResOther3 = getSsmQueryObject("getPreSlsAmtForPOReq", paramMap);
                if (mapResOther2 != null) {
                    // QC#17220-1 Mod.
//                    if (this.slsDt.substring(0, 6).equals((String) mapResOther2.get("INV_YR_MTH"))) {
//                        curMthAmtForPOReqList.add((BigDecimal) mapResOther2.get("SUM_INV_LINE_FUNC_NET_AMT"));
//                    } else {
//                        curMthAmtForPOReqList.add(BigDecimal.ZERO);
//                    }
                    curMthAmtForPOReqList.add((BigDecimal) mapResOther2.get("SUM_INV_LINE_FUNC_NET_AMT"));
                } else {
                    curMthAmtForPOReqList.add(BigDecimal.ZERO);
                }
                if (mapResOther3 != null) {
                    // QC#17220-1 Mod.
//                    if (this.slsDt.substring(0, 6).equals((String) mapResOther3.get("INV_YR_MTH"))) {
//                        preMthAmtForPOReqList.add((BigDecimal) mapResOther3.get("SUM_INV_LINE_FUNC_NET_AMT"));
//                    } else {
//                        preMthAmtForPOReqList.add(BigDecimal.ZERO);
//                    }
                    preMthAmtForPOReqList.add((BigDecimal) mapResOther3.get("SUM_INV_LINE_FUNC_NET_AMT"));
                } else {
                    preMthAmtForPOReqList.add(BigDecimal.ZERO);
                }
            }

        } else if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.PO_REQUISITION)) {
            // ***************************************************************************
            // Approval history Source Type Code : PO Requisition
            // ***************************************************************************

            // ### get header data ###
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("prchReqNum", this.trxRefNum);
            paramMap.put("prchReqLineSts", PRCH_REQ_LINE_STS_CANCELED);
            paramMap.put("mdseTpCtxTpCd", PARTS_ITEM);
            paramMap.put("apvlHrchTpCd", APVL_HRCH_TP_CD_POSITIONAL);
            paramMap.put("parts", PO_MDSE_TP_CD_PARTS);
            paramMap.put("mdse", PO_MDSE_TP_CD_MDSE);

            // judging if it is Customer Drop Ship or not
            boolean isNotCustDropShip = true;

            PRCH_REQTMsg prchReqTMsg = new PRCH_REQTMsg();
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqNum, this.trxRefNum);
            prchReqTMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKeyForUpdate(prchReqTMsg);

            if (prchReqTMsg != null) {
                String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(PO_CUST_DROP_SHIP_QLFY_CD, this.glblCmpyCd);
                if (ZYPCommonFunc.hasValue(prchReqTMsg.poQlfyCd) && varCharConstVal.equals(prchReqTMsg.poQlfyCd.getValue())) {
                    paramMap.put("custDropShip", "1");
                    isNotCustDropShip = false;
                } else {
                    paramMap.put("custDropShip", "0");
                }
            } else {
                paramMap.put("custDropShip", "0");
            }

            mapResHdr = getSsmQueryObject("getTechReqRushHdrData", paramMap);
            if (mapResHdr == null) {
                addError(NPAM0059E);
                return null;
            }

            // Change time zone of Need By Time
            if (ZYPCommonFunc.hasValue((String) mapResHdr.get("SYS_RQST_RCV_TM"))) {
                RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
                ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, (String) mapResHdr.get("DEST_RTL_WH_CD"));
                rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKeyForUpdate(rtlWhTMsg);

                if (rtlWhTMsg != null) {
                    info = NSXC001001SvcTimeZone.convertTime(1, (String) mapResHdr.get("SYS_RQST_RCV_TM"), rtlWhTMsg.ctryCd.getValue(), rtlWhTMsg.postCd.getValue());
                }
            }

            // ### get line data ###
            paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("prchReqNum", this.trxRefNum);
            paramMap.put("poMdseCmpsnTpCd", PO_MDSE_CMPSN_TP_CD_CHILD);

            mapResList = getSsmQueryObjectList("getTechReqRushLineData", paramMap);

            for (Map<String, Object> mapRes : mapResList) {

                // CUR_INVTY_AVAL_QTY
                if (isNotCustDropShip) {
                    paramMap = new HashMap<String, Object>();
                    paramMap.put("glblCmpyCd", this.glblCmpyCd);
                    paramMap.put("mdseCd", (String) mapRes.get("MDSE_CD"));
                    paramMap.put("invtyLocCd", (String) mapResHdr.get("DEST_INVTY_LOC_CD"));
                    paramMap.put("dcStock", LOC_STS_CD_DC_STOCK);
                    paramMap.put("stkStsCd", STK_STS_CD_GOOD);
                    Map<String, Object> mapResOther3 = getSsmQueryObject("getAvalQtyForTechReqRush", paramMap);
                    if (mapResOther3 != null) {
                        avalQtyForTechReqRushList.add((BigDecimal) mapResOther3.get("INVTY_AVAL_QTY"));
                    } else {
                        avalQtyForTechReqRushList.add(BigDecimal.ZERO);
                    }
                } else {
                    avalQtyForTechReqRushList.add(BigDecimal.ZERO);
                }

                // QC#17220-1 Add.
                // CUR_MTH_SALES_AMT, PREV_MTH_SALES_AMT
                paramMap = new HashMap<String, Object>();
                paramMap.put("glblCmpyCd", this.glblCmpyCd);
                paramMap.put("slsDt", this.slsDt);
                paramMap.put("first", FIRST_DT_OF_MONTH);
                paramMap.put("invTpCd", INV_TP_CD);
                paramMap.put("mdseCd", (String) mapRes.get("MDSE_CD"));
                paramMap.put("dtFmt", DT_FMT_YYYYMMDD);
                paramMap.put("yyyymm", DT_FMT_YYYYMM);
                paramMap.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
                Map<String, Object> mapResOther2 = getSsmQueryObject("getCurSlsAmtForPOReq", paramMap);
                Map<String, Object> mapResOther3 = getSsmQueryObject("getPreSlsAmtForPOReq", paramMap);
                if (mapResOther2 != null) {
                    curMthAmtForPOReqList.add((BigDecimal) mapResOther2.get("SUM_INV_LINE_FUNC_NET_AMT"));
                } else {
                    curMthAmtForPOReqList.add(BigDecimal.ZERO);
                }
                if (mapResOther3 != null) {
                    preMthAmtForPOReqList.add((BigDecimal) mapResOther3.get("SUM_INV_LINE_FUNC_NET_AMT"));
                } else {
                    preMthAmtForPOReqList.add(BigDecimal.ZERO);
                }
            }

        } else if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.TECH_REQUEST) || this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.INVENTORY_REQUEST)) {
            // ***************************************************************************
            // Approval history Source Type Code : Tech Request and
            // Inventory Request
            // ***************************************************************************

            // ### get header data ###
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("prchReqNum", this.trxRefNum);
            // QC#30355
            if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.TECH_REQUEST)) {
                paramMap.put("techReq", ZYPConstant.FLG_ON_Y);
            }
            List<Map<String, Object>> mapResPrchReqInfo = getSsmQueryObjectList("getPrchReqDtlInfo", paramMap);

            for (Map<String, Object> rs : mapResPrchReqInfo) {
                NLXC001001GetInventoryItemCostBean nlzc001001Bean = new NLXC001001GetInventoryItemCostBean();
                nlzc001001Bean.setGlblCmpyCd(this.glblCmpyCd);
                // TECH_REQUEST:SRC_INVTY_LOC_CD = Tech WH Code is
                // NLXC001001 API result ABEND
                if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.TECH_REQUEST)) {
                    nlzc001001Bean.setInvtyLocCd((String) rs.get("DEST_INVTY_LOC_CD"));
                } else {
                    nlzc001001Bean.setInvtyLocCd((String) rs.get("SRC_INVTY_LOC_CD"));
                }
                nlzc001001Bean.setMdseCd((String) rs.get("MDSE_CD"));
                nlzc001001Bean.setQty((BigDecimal) rs.get("QTY"));
                NLXC001001GetInventoryItemCost.getInventoryItemCost(nlzc001001Bean);

                if (!PRCH_REQ_LINE_STS_CANCELED.equals((String) rs.get("PRCH_REQ_LINE_STS_CD"))) {
                    totDealNetAmt = totDealNetAmt.add(nlzc001001Bean.getTotPrcAmt());
                }
                prchReqLineNumList.add((String) rs.get("PRCH_REQ_LINE_NUM"));
                prchReqLineSubNumList.add((BigDecimal) rs.get("PRCH_REQ_LINE_SUB_NUM"));
                unitPrcAmtList.add(nlzc001001Bean.getUnitPrcAmt());
                totPrcAmtList.add(nlzc001001Bean.getTotPrcAmt());
            }

            GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
            ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
            // MOD START 01/23/2023 QC#61093
            //glblCmpyTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKeyForUpdate(glblCmpyTMsg);
            glblCmpyTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTMsg);
            // MOD END   01/23/2023 QC#61093
            if (glblCmpyTMsg != null) {
                dealCcyCdForPOTechReqRush = glblCmpyTMsg.stdCcyCd.getValue();
            }

            paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("prchReqNum", this.trxRefNum);
            paramMap.put("mdseTpCtxTpCd", PARTS_ITEM);
            paramMap.put("prchReqLineSts", PRCH_REQ_LINE_STS_CANCELED);
            paramMap.put("totDealNetAmt", totDealNetAmt);
            paramMap.put("employee", APVL_HRCH_TP_CD_EMPLOYEE);
            paramMap.put("positional", APVL_HRCH_TP_CD_POSITIONAL);
            paramMap.put("parts", PO_MDSE_TP_CD_PARTS);
            paramMap.put("mdse", PO_MDSE_TP_CD_MDSE);
            paramMap.put("apvlHistSrcTpCd", this.apvlHistSrcTpCd);

            // judging if it is Customer Drop Ship or not
            PRCH_REQTMsg prchReqTMsg = new PRCH_REQTMsg();
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqNum, this.trxRefNum);
            prchReqTMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKeyForUpdate(prchReqTMsg);

            boolean isNotCustDropShip = true;

            if (prchReqTMsg != null) {
                String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(PO_CUST_DROP_SHIP_QLFY_CD, this.glblCmpyCd);
                if (ZYPCommonFunc.hasValue(prchReqTMsg.poQlfyCd) && varCharConstVal.equals(prchReqTMsg.poQlfyCd.getValue())) {
                    paramMap.put("custDropShip", "1");
                    isNotCustDropShip = false;
                } else {
                    paramMap.put("custDropShip", "0");
                }
            } else {
                paramMap.put("custDropShip", "0");
            }

            mapResHdr = getSsmQueryObject("getPOTechReqRushHdrData", paramMap);
            if (mapResHdr == null) {
                addError(NPAM0059E);
                return null;
            }
            if (APVL_HIST_SRC_TP.INVENTORY_REQUEST.equals(this.apvlHistSrcTpCd)) {
                mapResHdr.put("TRX_REF_NUM", this.trxRefNum);
            }

            // Change time zone of Need By Time
            if (ZYPCommonFunc.hasValue((String) mapResHdr.get("SYS_RQST_RCV_TM"))) {
                RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
                ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, (String) mapResHdr.get("DEST_RTL_WH_CD"));
                rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKeyForUpdate(rtlWhTMsg);

                if (rtlWhTMsg != null) {
                    info = NSXC001001SvcTimeZone.convertTime(1, (String) mapResHdr.get("SYS_RQST_RCV_TM"), rtlWhTMsg.ctryCd.getValue(), rtlWhTMsg.postCd.getValue());
                }
            }

            // ### get line data ###
            paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("prchReqNum", this.trxRefNum);
            paramMap.put("poMdseCmpsnTpCd", PO_MDSE_CMPSN_TP_CD_CHILD);
            paramMap.put("apvlHistSrcTpCd", this.apvlHistSrcTpCd);

            mapResList = getSsmQueryObjectList("getPOTechReqRushLineData", paramMap);

            // QC#17220 Add.
            for (Map<String, Object> mapRes : mapResList) {

                // CUR_INVTY_AVAL_QTY
                if (isNotCustDropShip) {
                    paramMap = new HashMap<String, Object>();
                    paramMap.put("glblCmpyCd", this.glblCmpyCd);
                    paramMap.put("mdseCd", (String) mapRes.get("MDSE_CD"));
                    paramMap.put("dcStock", LOC_STS_CD_DC_STOCK);
                    paramMap.put("stkStsCd", STK_STS_CD_GOOD);

                    if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.TECH_REQUEST)) {
                        paramMap.put("invtyLocCd", (String) mapResHdr.get("DEST_INVTY_LOC_CD"));
                    } else if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.INVENTORY_REQUEST) && mapResHdr.get("DEST_INVTY_LOC_CD") != null) {
                        paramMap.put("invtyLocCd", (String) mapResHdr.get("DEST_INVTY_LOC_CD"));
                    } else {
                        paramMap.put("invtyLocCd", (String) mapResHdr.get("SRC_INVTY_LOC_CD"));
                    }
                    Map<String, Object> mapResOther3 = getSsmQueryObject("getAvalQtyForTechReqRush", paramMap);
                    if (mapResOther3 != null) {
                        avalQtyForTechReqRushList.add((BigDecimal) mapResOther3.get("INVTY_AVAL_QTY"));
                    } else {
                        avalQtyForTechReqRushList.add(BigDecimal.ZERO);
                    }
                } else {
                    avalQtyForTechReqRushList.add(BigDecimal.ZERO);
                }

                // CUR_IN_TRNST_QTY
                paramMap = new HashMap<String, Object>();
                paramMap.put("glblCmpyCd", this.glblCmpyCd);
                paramMap.put("mdseCd", (String) mapRes.get("MDSE_CD"));
                paramMap.put("inTransit", LOC_STS_CD_IN_TRANSIT);
                paramMap.put("inTransitWh", LOC_STS_CD_IN_TRANSIT_WH);
                paramMap.put("stkStsCd", STK_STS_CD_GOOD);

                if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.TECH_REQUEST)) {
                    paramMap.put("invtyLocCd", (String) mapResHdr.get("DEST_INVTY_LOC_CD"));
                } else if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.INVENTORY_REQUEST) && mapResHdr.get("DEST_INVTY_LOC_CD") != null) {
                    paramMap.put("invtyLocCd", (String) mapResHdr.get("DEST_INVTY_LOC_CD"));
                } else {
                    paramMap.put("invtyLocCd", (String) mapResHdr.get("SRC_INVTY_LOC_CD"));
                }
                Map<String, Object> mapResInTranst = getSsmQueryObject("getTranstQtyForTechReqRush", paramMap);
                if (mapResInTranst != null) {
                    transitQtyForTechReqRushList.add((BigDecimal) mapResInTranst.get("CUR_IN_TRNST_QTY"));
                } else {
                    transitQtyForTechReqRushList.add(BigDecimal.ZERO);
                }

                // CUR_MIN_ORD_QTY、CUR_MAX_ORD_QTY
                paramMap = new HashMap<String, Object>();
                paramMap.put("glblCmpyCd", this.glblCmpyCd);
                paramMap.put("mdseCd", (String) mapRes.get("MDSE_CD"));
                // START 2023/02/17 A.Cullano [QC#61175, ADD]
                paramMap.put("termRgtnStsCd", (String) MRP_INFO_RGTN_STS.TERMINATED);
                // END 2023/02/17 A.Cullano [QC#61175, ADD]

                if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.TECH_REQUEST)) {
                    paramMap.put("invtyLocCd", (String) mapResHdr.get("DEST_INVTY_LOC_CD"));
                } else if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.INVENTORY_REQUEST) && mapResHdr.get("DEST_INVTY_LOC_CD") != null) {
                    paramMap.put("invtyLocCd", (String) mapResHdr.get("DEST_INVTY_LOC_CD"));
                } else {
                    paramMap.put("invtyLocCd", (String) mapResHdr.get("SRC_INVTY_LOC_CD"));
                }
                Map<String, Object> mapResQtyMinMax = getSsmQueryObject("getOrdQtyForTechReqRush", paramMap);
                if (mapResQtyMinMax != null) {
                    minQtyForTechReqRushList.add((BigDecimal) mapResQtyMinMax.get("CUR_MIN_ORD_QTY"));
                    maxQtyForTechReqRushList.add((BigDecimal) mapResQtyMinMax.get("CUR_MAX_ORD_QTY"));
                } else {
                    minQtyForTechReqRushList.add(BigDecimal.ZERO);
                    maxQtyForTechReqRushList.add(BigDecimal.ZERO);
                }

                // CUR_MTH_SALES_AMT, PREV_MTH_SALES_AMT
                paramMap = new HashMap<String, Object>();
                paramMap.put("glblCmpyCd", this.glblCmpyCd);
                paramMap.put("slsDt", this.slsDt);
                paramMap.put("first", FIRST_DT_OF_MONTH);
                paramMap.put("invTpCd", INV_TP_CD);
                paramMap.put("mdseCd", (String) mapRes.get("MDSE_CD"));
                paramMap.put("dtFmt", DT_FMT_YYYYMMDD);
                paramMap.put("yyyymm", DT_FMT_YYYYMM);
                paramMap.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
                Map<String, Object> mapResOther2 = getSsmQueryObject("getCurSlsAmtForPOReq", paramMap);
                Map<String, Object> mapResOther3 = getSsmQueryObject("getPreSlsAmtForPOReq", paramMap);
                if (mapResOther2 != null) {
                    curMthAmtForPOReqList.add((BigDecimal) mapResOther2.get("SUM_INV_LINE_FUNC_NET_AMT"));
                } else {
                    curMthAmtForPOReqList.add(BigDecimal.ZERO);
                }
                if (mapResOther3 != null) {
                    preMthAmtForPOReqList.add((BigDecimal) mapResOther3.get("SUM_INV_LINE_FUNC_NET_AMT"));
                } else {
                    preMthAmtForPOReqList.add(BigDecimal.ZERO);
                }
            }

            // Get Notification Info
            NUM_CONSTTMsg numConstTMsg = new NUM_CONSTTMsg();
            ZYPEZDItemValueSetter.setValue(numConstTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(numConstTMsg.numConstNm, PO_WF_NTFY_OTPT_REC_CNT);
            numConstTMsg = (NUM_CONSTTMsg) EZDTBLAccessor.findByKeyForUpdate(numConstTMsg);

            if (numConstTMsg != null) {
                numConstVal = numConstTMsg.numConstVal.getValue();
            }
        }

        // *************************
        // Create Approval History
        // *************************
        NPXC001001CreateApprovalHistoryBean inParam = new NPXC001001CreateApprovalHistoryBean();
        inParam.setGlblCmpyCd(this.glblCmpyCd);
        inParam.setApvlHistSrcTpCd(this.apvlHistSrcTpCd);
        inParam.setTrxRefNum(this.trxRefNum);
        inParam.setApvlHistInfoTs(null);
        inParam.setApvlHistActTpCd(APVL_HIST_ACT_TP.SUBMIT);
        if (!APVL_HIST_SRC_TP.TECH_REQUEST.equals(this.apvlHistSrcTpCd)) {
            inParam.setApvlByPsnCd((String) mapResHdr.get("RQST_PSN_CD"));
        } else {
            inParam.setApvlByPsnCd((String) mapResHdr.get("SUBMT_PSN_CD"));
        }
        String rtrnCd = NPXC001001CreateApprovalHistory.createApprovalHistory(inParam);

        if (!rtrnCd.equals(RTRN_CD_NORMAL)) {
            addError(NPZM0212E);
            return null;
        }

        // **********************
        // Set TokenObject data
        // **********************
        NPZC130001TokenObject tokenBiz = new NPZC130001TokenObject();

        // ### Set Attribute ###
        if (this.xxModeCd.equals(WFMD_PO)) {
            setPOAttrb(tokenBiz, mapResHdr);
        } else if (this.xxModeCd.equals(WFMD_TECH_REQ)) {
            setPRCHAttrb(tokenBiz, mapResHdr, dealCcyCdForPOTechReqRush);
        } else if (this.xxModeCd.equals(WFMD_TECH_REQ_RUSH)) {
            setPRCHAttrb(tokenBiz, mapResHdr, dealCcyCdForPOTechReqRush);
        } else if (this.xxModeCd.equals(WFMD_PO_REQ)) {
            setPRCHAttrb(tokenBiz, mapResHdr, null);
        } else if (this.xxModeCd.equals(WFMD_INVTY_REQ)) {
            setPRCHAttrb(tokenBiz, mapResHdr, null);
        }

        // ### Set Detail Attribute ###
        setHdr(tokenBiz, mapResHdr, numConstVal);
        if (this.xxModeCd.equals(WFMD_PO)) {
            tokenBiz.setHdrAttrb10((String) mapResHdr.get("DEAL_CCY_CD"));
        } else if (this.xxModeCd.equals(WFMD_TECH_REQ)) {
            tokenBiz.setHdrAttrb10(dealCcyCdForPOTechReqRush);
        } else if (this.xxModeCd.equals(WFMD_TECH_REQ_RUSH)) {
            tokenBiz.setHdrAttrb10(dealCcyCdForPOTechReqRush);
        } else if (this.xxModeCd.equals(WFMD_PO_REQ)) {
            tokenBiz.setHdrAttrb10((String) mapResHdr.get("DEAL_CCY_CD"));
        } else if (this.xxModeCd.equals(WFMD_INVTY_REQ)) {
            tokenBiz.setHdrAttrb10((String) mapResHdr.get("DEAL_CCY_CD"));
        }
        if (!this.xxModeCd.equals(WFMD_PO) && info != null) {

            try {
                SimpleDateFormat sdfmtBf = new SimpleDateFormat(DT_FMT_YYYYMMDDHHMMSS);
                Date dtBf = sdfmtBf.parse(this.slsDt + info.getDateTime().substring(0, 6));
                SimpleDateFormat sdfmtAf = new SimpleDateFormat(DT_FMT_HH12MI);
                tokenBiz.setHdrAttrb31(sdfmtAf.format(dtBf));

                Calendar cal1 = new GregorianCalendar();
                cal1.setTime(dtBf);
                int hour = cal1.get(Calendar.AM_PM);
                if (hour <= 0) {
                    tokenBiz.setHdrAttrb32(AM);
                } else {
                    tokenBiz.setHdrAttrb32(PM);
                }
            } catch (ParseException e) {
                addError(NPZM0212E);
            }
        }

        // ### Set Line Data ###
        int i = 0;
        for (Map<String, Object> mapRes : mapResList) {
            NPZC130001TokenObjectLine lineData = new NPZC130001TokenObjectLine();

            BigDecimal avalQty = BigDecimal.ZERO;
            if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.PO_ENTRY)) {
                avalQty = (BigDecimal) avalQtyForPOReqList.get(i);
            } else if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.PO_REQUISITION)
                    || this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.TECH_REQUEST)
                    || this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.INVENTORY_REQUEST)) {
                avalQty = (BigDecimal) avalQtyForTechReqRushList.get(i);
            }
            setDtl(lineData, mapRes, avalQty);

            if (this.xxModeCd.equals(WFMD_PO)) {
                // QC#17220 Mod.
//                lineData.setDtlAttrb12(bigDecimalToString(unitPrcAmtList, i));
//                lineData.setDtlAttrb13(bigDecimalToString(totPrcAmtList, i));
                lineData.setDtlAttrb12(bigDecimalToStringFromAMT(mapRes, "ENT_DEAL_NET_UNIT_PRC_AMT"));
                lineData.setDtlAttrb13(bigDecimalToStringFromAMT(mapRes, "ENT_PO_DTL_DEAL_NET_AMT"));

                lineData.setDtlAttrb17(bigDecimalToString(curMthAmtForPOReqList, i));
                lineData.setDtlAttrb18(bigDecimalToString(preMthAmtForPOReqList, i));
            } else if (this.xxModeCd.equals(WFMD_TECH_REQ)) {
                lineData.setDtlAttrb12(bigDecimalToString(unitPrcAmtList, i));
                lineData.setDtlAttrb13(bigDecimalToString(totPrcAmtList, i));
                lineData.setDtlAttrb19(bigDecimalToString(minQtyForTechReqRushList, i));
                lineData.setDtlAttrb20(bigDecimalToString(maxQtyForTechReqRushList, i));
                lineData.setDtlAttrb21(bigDecimalToString(transitQtyForTechReqRushList, i));
            } else if (this.xxModeCd.equals(WFMD_TECH_REQ_RUSH)) {
                // QC#17220-1 Mod.
//                lineData.setDtlAttrb12(bigDecimalToStringFromAMT(mapRes, "ENT_DEAL_NET_UNIT_PRC_AMT"));
//                lineData.setDtlAttrb13(bigDecimalToStringFromAMT(mapRes, "ENT_PO_DTL_DEAL_NET_AMT"));
                lineData.setDtlAttrb12(bigDecimalToString(unitPrcAmtList, i));
                lineData.setDtlAttrb13(bigDecimalToString(totPrcAmtList, i));
                lineData.setDtlAttrb19(bigDecimalToString(minQtyForTechReqRushList, i));
                lineData.setDtlAttrb20(bigDecimalToString(maxQtyForTechReqRushList, i));
                lineData.setDtlAttrb21(bigDecimalToString(transitQtyForTechReqRushList, i));
            } else if (this.xxModeCd.equals(WFMD_PO_REQ)) {
                lineData.setDtlAttrb12(bigDecimalToStringFromAMT(mapRes, "ENT_DEAL_NET_UNIT_PRC_AMT"));
                lineData.setDtlAttrb13(bigDecimalToStringFromAMT(mapRes, "ENT_PO_DTL_DEAL_NET_AMT"));
                lineData.setDtlAttrb17(bigDecimalToString(curMthAmtForPOReqList, i));
                lineData.setDtlAttrb18(bigDecimalToString(preMthAmtForPOReqList, i));
            } else if (this.xxModeCd.equals(WFMD_INVTY_REQ)) {
                lineData.setDtlAttrb12(bigDecimalToString(unitPrcAmtList, i));
                lineData.setDtlAttrb13(bigDecimalToString(totPrcAmtList, i));
                lineData.setDtlAttrb17(bigDecimalToString(curMthAmtForPOReqList, i));
                lineData.setDtlAttrb18(bigDecimalToString(preMthAmtForPOReqList, i));
            }

            // ### Set data (Line) ###
            lineData.setTrxRefLineNum((String) mapRes.get("TRX_REF_LINE_NUM"));

            tokenBiz.addLineData(lineData);
            i++;
        }

        // ### Set data ###
        tokenBiz.setApvlHistSrcTpCd(this.apvlHistSrcTpCd);
        tokenBiz.setTrxRefNum(this.trxRefNum);
        tokenBiz.setSlsDt(this.slsDt);
        tokenBiz.setXxProcTpCd(this.xxProcTpCd);

        // ### Set Condition Data ###
        if (this.xxModeCd.equals(WFMD_PO)) {
            tokenBiz.setBizId(BIZ_ID_NPZC13000101);
            tokenBiz.setBizScreenId(BIZ_SCRN_ID_NPAL1500);
        } else if (this.xxModeCd.equals(WFMD_TECH_REQ)) {
            tokenBiz.setBizId(BIZ_ID_NPZC13000102);
            tokenBiz.setBizScreenId(BIZ_SCRN_ID_NPAL1080);

            // START 2023/04/27 T.Kuroda [QC#61245, ADD]
            Map<String, Object> mapRes = getWorkFlowReason(totDealNetAmt);
            if (mapRes != null) {
                if (ZYPConstant.FLG_ON_Y.equals((String)mapRes.get("TECH_APVL_LIMIT_FLG"))) {
                    tokenBiz.setComment(getRtnMsg(NPZM0316I, new String[]{((BigDecimal)mapRes.get("TECH_APVL_LIMIT_AMT")).toString()}));
                } else if (ZYPConstant.FLG_ON_Y.equals((String)mapRes.get("THRHD_APVL_LIMIT_FLG"))) {
                    tokenBiz.setComment(getRtnMsg(NPZM0316I, new String[]{((BigDecimal)mapRes.get("THRHD_APVL_LIMIT_AMT")).toString()}));
                }
            }
            // END   2023/04/27 T.Kuroda [QC#61245, ADD]
        } else if (this.xxModeCd.equals(WFMD_TECH_REQ_RUSH)) {
            tokenBiz.setBizId(BIZ_ID_NPZC13000103);
            tokenBiz.setBizScreenId(BIZ_SCRN_ID_NPAL1080);

            // START 2023/04/27 T.Kuroda [QC#61245, ADD]
            Map<String, Object> mapRes = getWorkFlowReason(totDealNetAmt);
            if (mapRes != null) {
                // START 2023/05/19 T.Kuroda [QC#61211, MOD]
                // if (ZYPConstant.FLG_ON_Y.equals((String)mapRes.get("TOOL_FLG"))) {
                if (PRCH_REQ_TP.PREMIUM_RUSH.equals((String)mapRes.get("PRCH_REQ_TP_CD"))) {
                    tokenBiz.setComment(getRtnMsg(NPZM0317I, new String[]{((BigDecimal)mapRes.get("PREMIUM_RUSH_APVL_LIMIT_AMT")).toString()}));
                } else if (ZYPConstant.FLG_ON_Y.equals((String)mapRes.get("TOOL_FLG"))) {
                // END   2023/05/19 T.Kuroda [QC#61211, MOD]
                    tokenBiz.setComment(getRtnMsg(NPZM0314I));
                } else {
                    tokenBiz.setComment(getRtnMsg(NPZM0315I));
                }
            }
            // END   2023/04/27 T.Kuroda [QC#61245, ADD]
        } else if (this.xxModeCd.equals(WFMD_PO_REQ)) {
            tokenBiz.setBizId(BIZ_ID_NPZC13000104);
            tokenBiz.setBizScreenId(BIZ_SCRN_ID_NPAL1280);
        } else if (this.xxModeCd.equals(WFMD_INVTY_REQ)) {
            tokenBiz.setBizId(BIZ_ID_NPZC13000105);
            tokenBiz.setBizScreenId(BIZ_SCRN_ID_NPBL0020);
        }
        setCondition(tokenBiz, mapResHdr);

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process end [startWorkflowPreparing]", this);
        return tokenBiz;
    }

    private void startWorkflow(String wfId, NPZC130001TokenObject tokenBiz) {
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process start [startWorkflow]", this);

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;

        try {
            context = factory.getContex();
            process = context.newProcess(wfId);
        } catch (S21NwfSystemException e) {
            addError(NPZM0213E);
            return;
        }

        S21NwfToken token = process.getToken();
        token.setAutoDelegateUser(tokenBiz.getCondStr1());
        process.setDocumentId(this.trxRefNum);
        token.setTokenObj(tokenBiz);

        try {
            // Start Workflow
            token.start();

        } catch (S21NwfSystemException e) {
            addError(NPZM0213E);
            return;

        } catch (S21NwfBizException e) {
            // Business Error Logic
            // Auto Approve Process Call APIs
            // Approve API / Reject API / Process End API Error

            this.msgMap.addXxMsgIdWithPrm(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            return;

        } catch (S21NwfException e) {
            // System Error Logic
            addError(NFDM0008E);
            return;
        }

        List<EZDMessageInfo> list = token.getLastBizMessage();

        if ((list != null) && (list.size() > 0)) {

            addError(list.get(0).getCode());

        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process end [startWorkflow]", this);
    }

    private void setPOAttrb(NPZC130001TokenObject tokenBiz, Map<String, Object> mapResHdr) {
        tokenBiz.setAttribute1Lbl(WF_LBL_1_PO);
        tokenBiz.setAttribute1(this.trxRefNum);
        tokenBiz.setAttribute2Lbl(WF_LBL_2_PO);
        tokenBiz.setAttribute2(concatWithSpace((String) mapResHdr.get("RQST_PSN_CD"), (String) mapResHdr.get("RQST_PSN_NM")));
        tokenBiz.setAttribute3Lbl(WF_LBL_3);
        tokenBiz.setAttribute3(concatWithSpace(convAmountData(mapResHdr, "TOT_DEAL_NET_AMT"), (String) mapResHdr.get("DEAL_CCY_CD")));
        tokenBiz.setAttribute4Lbl(WF_LBL_4_PO);
        tokenBiz.setAttribute4((String) mapResHdr.get("RQST_ORD_TP_DESC_TXT"));
        tokenBiz.setAttribute5Lbl(WF_LBL_5);
        tokenBiz.setAttribute5((String) mapResHdr.get("RQST_ORD_SRC_DESC_TXT"));
    }

    private void setPRCHAttrb(NPZC130001TokenObject tokenBiz, Map<String, Object> mapResHdr, String ccyCd) {
        tokenBiz.setAttribute1Lbl(WF_LBL_1_PRCH);
        tokenBiz.setAttribute1(this.trxRefNum);
        tokenBiz.setAttribute2Lbl(WF_LBL_2_PRCH);
        tokenBiz.setAttribute2(concatWithSpace((String) mapResHdr.get("RQST_PSN_CD"), (String) mapResHdr.get("RQST_PSN_NM")));
        tokenBiz.setAttribute3Lbl(WF_LBL_3);
        if (ccyCd != null) {
            tokenBiz.setAttribute3(concatWithSpace(convAmountData(mapResHdr, "TOT_DEAL_NET_AMT"), ccyCd));
        } else {
            tokenBiz.setAttribute3(concatWithSpace(convAmountData(mapResHdr, "TOT_DEAL_NET_AMT"), (String) mapResHdr.get("DEAL_CCY_CD")));
        }
        tokenBiz.setAttribute4Lbl(WF_LBL_4_PRCH);
        tokenBiz.setAttribute4((String) mapResHdr.get("RQST_ORD_TP_DESC_TXT"));
        tokenBiz.setAttribute5Lbl(WF_LBL_5);
        tokenBiz.setAttribute5((String) mapResHdr.get("RQST_ORD_SRC_DESC_TXT"));
    }

    private void setHdr(NPZC130001TokenObject tokenBiz, Map<String, Object> mapResHdr, BigDecimal numConstVal) {
        tokenBiz.setHdrAttrb1(this.xxProcTpCd);
        tokenBiz.setHdrAttrb2(this.apvlHistSrcTpCd);
        tokenBiz.setHdrAttrb3(this.trxRefNum);
        tokenBiz.setHdrAttrb4((String) mapResHdr.get("RQST_PSN_CD"));
        tokenBiz.setHdrAttrb5((String) mapResHdr.get("RQST_PSN_NM"));
        tokenBiz.setHdrAttrb7((String) mapResHdr.get("RQST_ORD_CRAT_DT"));
        tokenBiz.setHdrAttrb8((String) mapResHdr.get("RQST_ORD_CRAT_TM"));
        tokenBiz.setHdrAttrb9(convAmountData(mapResHdr, "TOT_DEAL_NET_AMT"));
        tokenBiz.setHdrAttrb11((String) mapResHdr.get("RQST_ORD_TP_CD"));
        tokenBiz.setHdrAttrb12((String) mapResHdr.get("RQST_ORD_TP_DESC_TXT"));
        tokenBiz.setHdrAttrb13((String) mapResHdr.get("RQST_ORD_SRC_CD"));
        tokenBiz.setHdrAttrb14((String) mapResHdr.get("RQST_ORD_SRC_DESC_TXT"));
        tokenBiz.setHdrAttrb15((String) mapResHdr.get("APVL_HRCH_TP_CD"));
        tokenBiz.setHdrAttrb16((String) mapResHdr.get("PRCH_GRP_CD"));
        tokenBiz.setHdrAttrb17((String) mapResHdr.get("DEST_RTL_WH_CD"));
        tokenBiz.setHdrAttrb18((String) mapResHdr.get("DEST_RTL_WH_NM"));
        tokenBiz.setHdrAttrb19((String) mapResHdr.get("PO_MDSE_TP_CD"));
        tokenBiz.setHdrAttrb20(bigDecimalToString(mapResHdr, "ORD_TOT_LINE_CNT"));
        tokenBiz.setHdrAttrb21(bigDecimalToString(mapResHdr, "ORD_TOT_QTY"));
        tokenBiz.setHdrAttrb22((String) mapResHdr.get("PO_QLFY_CD"));
        tokenBiz.setHdrAttrb23((String) mapResHdr.get("SHIP_TO_CUST_CD"));
        tokenBiz.setHdrAttrb24((String) mapResHdr.get("SHIP_TO_LOC_NM"));
        tokenBiz.setHdrAttrb25((String) mapResHdr.get("SHIP_TO_ACCT_NM"));
        tokenBiz.setHdrAttrb26((String) mapResHdr.get("PRNT_VND_CD"));
        tokenBiz.setHdrAttrb27((String) mapResHdr.get("PRNT_VND_NM"));
        tokenBiz.setHdrAttrb28((String) mapResHdr.get("VND_CD"));
        tokenBiz.setHdrAttrb29((String) mapResHdr.get("VND_NM"));
        tokenBiz.setHdrAttrb30((String) mapResHdr.get("H_RQST_RCV_DT"));
        tokenBiz.setHdrAttrb33((String) mapResHdr.get("DEST_INVTY_LOC_CD"));
        tokenBiz.setHdrAttrb34(numConstVal.toString());
    }

    private void setDtl(NPZC130001TokenObjectLine lineData, Map<String, Object> mapRes, BigDecimal avalQty) {
        lineData.setDtlAttrb1((String) mapRes.get("TRX_REF_LINE_NUM"));
        lineData.setDtlAttrb2(bigDecimalToString(mapRes, "TRX_REF_LINE_SUB_NUM"));
        lineData.setDtlAttrb3((String) mapRes.get("TRX_ORD_DISP_LINE_NUM"));
        lineData.setDtlAttrb4((String) mapRes.get("PO_MDSE_CMPSN_TP_CD"));
        lineData.setDtlAttrb5((String) mapRes.get("SET_PO_ORD_DTL_LINE_NUM"));
        lineData.setDtlAttrb6((String) mapRes.get("MDSE_CD"));
        lineData.setDtlAttrb7((String) mapRes.get("MDSE_DESC_SHORT_TXT"));
        lineData.setDtlAttrb8((String) mapRes.get("DISP_UOM_CD"));
        lineData.setDtlAttrb9(bigDecimalToString(mapRes, "TRX_ORD_DISP_QTY"));
        lineData.setDtlAttrb10(bigDecimalToString(mapRes, "TRX_ORD_QTY"));
        lineData.setDtlAttrb11(bigDecimalToString(mapRes, "TRX_ORD_CANC_QTY"));
        lineData.setDtlAttrb14((String) mapRes.get("RQST_RCV_DT"));
        lineData.setDtlAttrb15((String) mapRes.get("PO_SEND_TS"));
        lineData.setDtlAttrb16(avalQty.toString());
    }

    private void setCondition(NPZC130001TokenObject tokenBiz, Map<String, Object> mapResHdr) {
        tokenBiz.setBizScreenParams(this.trxRefNum);
        tokenBiz.setCondStr1((String) mapResHdr.get("RQST_PSN_CD"));
        tokenBiz.setCondStr2((String) mapResHdr.get("APVL_HRCH_TP_CD"));
        tokenBiz.setCondStr3(this.apvlHistSrcTpCd);
        tokenBiz.setCondStr4((String) mapResHdr.get("PRCH_GRP_CD"));
        tokenBiz.setCondStr5((String) mapResHdr.get("DEST_RTL_WH_CD"));
        tokenBiz.setCondStr6((String) mapResHdr.get("PO_MDSE_TP_CD"));
        tokenBiz.setCondNum1((BigDecimal) mapResHdr.get("TOT_DEAL_NET_AMT"));
        // START 2023/08/29 M.Kikushima [QC#61590, ADD]
        if (this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.PO_REQUISITION) || this.apvlHistSrcTpCd.equals(APVL_HIST_SRC_TP.INVENTORY_REQUEST)) {
            if(checkApvlData(mapResHdr)) {
                tokenBiz.setCondStr7((String) mapResHdr.get("PRCH_REQ_TP_CD"));
            }
        }
        // START 2023/08/29 M.Kikushima [QC#61590, ADD]
        tokenBiz.setGlblCmpyCd(this.glblCmpyCd);
    }

    // START 2023/04/27 T.Kuroda [QC#61245, ADD]
    private Map<String, Object> getWorkFlowReason(BigDecimal totDealNetAmt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("prchReqNum", this.trxRefNum);
        paramMap.put("prtItemTpIsTool", PRT_ITEM_TP.TOOL);
        paramMap.put("totDealNetAmt", totDealNetAmt);
        paramMap.put("apvlHrchTpCd", APVL_HRCH_TP.EMPLOYEE);
        paramMap.put("apvlHistSrcTpCd", APVL_HIST_SRC_TP.TECH_REQUEST);

        List<Map<String, Object>> mapResList = getSsmQueryObjectList("getTechReqLimitAmtData", paramMap);
        Map<String, Object> mapRes = null;
        if(!mapResList.isEmpty()) {
            mapRes = mapResList.get(0);
        }
        return mapRes;
    }
    // END   2023/04/27 T.Kuroda [QC#61245, ADD]

    // -----------------------------------------------------------
    // Util
    // -----------------------------------------------------------

    private boolean hasError() {
        return this.msgMap.getMsgMgr().isXxMsgId();
    }

    private void addError(String xxMsgId) {
        this.msgMap.addXxMsgId(xxMsgId);
    }

    private String concatWithSpace(String val1, String val2) {
        if(val1 == null){
            val1 = "";
        }
        if(val2 == null){
            val2 = "";
        }
        return val1 + " " + val2;
    }

    private Map<String, Object> getSsmQueryObject(String sqlId, Map<String, Object> paramMap) {
        Map<String, Object> mapRes = (Map<String, Object>) ssmBatchClient.queryObject(sqlId, paramMap);
        return mapRes;
    }

    private List<Map<String, Object>> getSsmQueryObjectList(String sqlId, Map<String, Object> paramMap) {
        List<Map<String, Object>> mapResList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(sqlId, paramMap);
        return mapResList;
    }

    private String bigDecimalToString(List<BigDecimal> list, int i) {
        BigDecimal value = new BigDecimal(0);
        if (list != null && !list.isEmpty()) {
            value = (BigDecimal) list.get(i);
        }
        // START 2023/02/17 A.Cullano [QC#61175, MOD]
        // return ZYPCommonFunc.toCommaHensyu(value.toPlainString(), false);
        if (ZYPCommonFunc.hasValue(value)) {
            return ZYPCommonFunc.toCommaHensyu(value.toPlainString(), false);
        } else {
            return "";
        }
        // END 2023/02/17 A.Cullano [QC#61175, MOD]
    }

    private String bigDecimalToString(Map<String, Object> mapRes, String object) {
        BigDecimal value = BigDecimal.ZERO;
        if (mapRes != null && (BigDecimal) mapRes.get(object) != null) {
            value = (BigDecimal) mapRes.get(object);
        }

        return ZYPCommonFunc.toCommaHensyu(value.toPlainString(), false);
    }

    private String bigDecimalToStringFromAMT(Map<String, Object> mapRes, String object) {
        BigDecimal value = BigDecimal.ZERO;
        String str = "";
        if (mapRes != null && (BigDecimal) mapRes.get(object) != null) {
            value = (BigDecimal) mapRes.get(object);
        }
        str = ZYPCommonFunc.toCommaHensyu(ZYPCommonFunc.toEditShosu(value.toPlainString(), 2), false);

        return str;
    }

    private String convAmountData(Map<String, Object> mapRes, String object) {
        BigDecimal value = BigDecimal.ZERO.setScale(2);
        if (mapRes != null && (BigDecimal) mapRes.get(object) != null) {
            value = (BigDecimal) mapRes.get(object);
        }
        // convert
        if(value.scale() < 2){
            value = value.setScale(2);
        }
        String exprsAmt = "$" + ZYPCommonFunc.toCommaHensyu(value.toPlainString(), false);

        return exprsAmt;
    }

    // START 2023/04/27 T.Kuroda [QC#61245, ADD]
    private String getRtnMsg(String msgId) {
        String rtnVal = "";
        if (ZYPCommonFunc.hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    private String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (ZYPCommonFunc.hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    // END   2023/04/27 T.Kuroda [QC#61245, ADD]

    // -----------------------------------------------------------
    // Validation
    // -----------------------------------------------------------

    // START 2023/09/13 M.Kikushima [QC#61590, ADD]
    private boolean checkApvlData(Map<String, Object> mapResHdr) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("psnCd", mapResHdr.get("RQST_PSN_CD"));
        paramMap.put("prchGrpCd", mapResHdr.get("PRCH_GRP_CD"));
        paramMap.put("apvlHistSrcTpCd", this.apvlHistSrcTpCd);
        paramMap.put("prchReqTpCd", mapResHdr.get("PRCH_REQ_TP_CD"));
        if((Integer)ssmBatchClient.queryObject("getApvlDataCheck", paramMap) > 0) {
            return true;
        } else {
            return false;
        }
    }
    // END   2023/09/13 M.Kikushima [QC#61590, ADD]

    /**
     * @param pMsg
     */
    private void checkInputParam(NPZC130001PMsg pMsg) {
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process start [checkInputParam]", this);

        if (!hasValue(pMsg.glblCmpyCd)) {
            addError(NPZM0179E);
        }
        if (!hasValue(pMsg.slsDt)) {
            addError(NPZM0180E);
        }
        if (!hasValue(pMsg.xxModeCd)) {
            addError(NPZM0035E);
        }
        if (!hasValue(pMsg.xxProcTpCd)) {
            addError(NPZM0210E);
        }
        if (!hasValue(pMsg.trxRefNum)) {
            addError(NPZM0190E);
        }
        if (!hasValue(pMsg.apvlHistSrcTpCd)) {
            addError(NPZM0211E);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process end [checkInputParam]", this);
    }

    // -----------------------------------------------------------
    // Set Parameter
    // -----------------------------------------------------------

    /**
     * @param pMsg
     */
    private void setInputParam(NPZC130001PMsg pMsg) {
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process start [setInputParam]", this);

        this.glblCmpyCd = pMsg.glblCmpyCd.getValue();
        this.slsDt = pMsg.slsDt.getValue();
        this.xxModeCd = pMsg.xxModeCd.getValue();
        this.xxProcTpCd = pMsg.xxProcTpCd.getValue();
        this.trxRefNum = pMsg.trxRefNum.getValue();
        this.apvlHistSrcTpCd = pMsg.apvlHistSrcTpCd.getValue();

        if (this.xxModeCd.equals(WFMD_PO)) {
            this.wfDefNm = WFNM_PO;
        } else if (this.xxModeCd.equals(WFMD_TECH_REQ)) {
            this.wfDefNm = WFNM_TECH_REQ;
        } else if (this.xxModeCd.equals(WFMD_TECH_REQ_RUSH)) {
            this.wfDefNm = WFNM_TECH_REQ_RUSH;
        } else if (this.xxModeCd.equals(WFMD_PO_REQ)) {
            this.wfDefNm = WFNM_PO_REQ;
        } else if (this.xxModeCd.equals(WFMD_INVTY_REQ)) {
            this.wfDefNm = WFNM_INVTY_REQ;
        } else {
            this.wfDefNm = "";
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process end [setInputParam]", this);
    }

}
