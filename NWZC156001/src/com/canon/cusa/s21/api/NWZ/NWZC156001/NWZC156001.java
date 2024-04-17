/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC156001;

import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.AMT_SCALE;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.BIG_DECIMAL_100;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.INVTY_OWNER_CD_CSA;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.MAX_PRC_VAL;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.MIN_PRC_VAL;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.MODE_CREDIT;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.MODE_LIST;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.MODE_ONLINE;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.MODE_UPDATE;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM0011E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM0021E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM0027E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM0043E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM0046E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM0089E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM0346E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM0352E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM0977E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM1155E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM1253E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM1406E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM1515E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM1517E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM1518E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM1519E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM1520E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM1522E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.NWZM1523E;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.ORD_LINE_SRC_CATG_CD_INTERNAL;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.ORD_PROC_NODE_PRFL_CD_33;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.PCT_SCALE;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.RATE_SCALE;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.STR_BLANK;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import parts.common.EZDTStringItem;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.CPO_DTLTMsg;
import business.db.ORD_PRFTTMsg;
import business.db.ORD_PRFT_DTLTMsg;
import business.db.ORD_PRFT_MDSE_TP_RULETMsg;
import business.db.PRC_CATGTMsg;
import business.db.RTL_WHTMsg;
import business.db.RTL_WHTMsgArray;
import business.db.SWHTMsg;
import business.parts.NWZC156001PMsg;
import business.parts.NWZC156001_svcConfigRefPMsg;
import business.parts.NWZC156002PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.ORD_PROC_NODE_PRFL;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DISC_ALLOC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PRFT_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PRFT_TRX_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Profitability Calculation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         M.Yamada        Create          CSA
 * 2016/02/20   Fujitsu         Y.Kanefusa      UPDATE          S21_NA#4212
 * 2016/03/10   Fujitsu         S.Yamamoto      Update          S21_NA#2939
 * 2016/03/23   Fujitsu         S.Yamamoto      Update          S21_NA#3315
 * 2016/03/25   Fujitsu         S.Yamamoto      Update          S21_NA#5491,#6083
 * 2016/04/03   Fujitsu         S.Yamamoto      Update          S21_NA#6298
 * 2016/04/28   SRA             E.Inada         Update          S21_NA#7301
 * 2016/05/03   SRA             E.Inada         Update          S21_NA#7810
 * 2016/05/31   SRA             E.Inada         Update          S21_NA#9123
 * 2016/06/08   SRA             E.Inada         Update          S21_NA#9635
 * 2016/06/09   Fujitsu         Y.Taoka         Update          S21_NA#9707
 * 2016/06/16   SRA             E.Inada         Update          S21_NA#10121,#10122
 * 2016/06/17   SRA             E.Inada         Update          S21_NA#10311
 * 2016/06/21   SRA             E.Inada         Update          S21_NA#10457
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/06/24   Fujitsu         S.Yamamoto      Update          S21_NA#10820
 * 2016/06/27   Fujitsu         T.Yoshida       Update          S21_NA#10321 For Performance
 * 2016/06/29   Fujitsu         Y.Taoka         Update          S21_NA#11098
 * 2016/07/19   Fujitsu         Y.Taoka         Update          S21_NA#11981
 * 2016/08/30   Fujitsu         Y.Taoka         Update          S21_NA#13916
 * 2016/08/31   Fujitsu         M.Yamada        Update          S21_NA#12112
 * 2017/01/24   Fujitsu         S.Yamamoto      Update          S21_NA#17003
 * 2017/07/04   Fujitsu         M.Yamada        Update          S21_NA#19721
 * 2017/10/06   Fujitsu         Y.Kanefusa      Update          S21_NA#21664
 * 2017/10/10   Fujitsu         R.Nakamura      Update          S21_NA#21664
 * 2017/10/25   Fujitsu         R.Nakamura      Update          S21_NA#21943
 * 2018/01/10   Fujitsu         Y.Kanefusa      Update          S21_NA#22372
 * 2018/03/08   Fujitsu         Mz.Takahashi    Update          S21_NA#22614
 * </pre>
 */
public class NWZC156001 extends S21ApiCommonBase {

    /** Online Batch Type */
    private ONBATCH_TYPE onBatchType = null;

    /** Global Company Code : set from pMsg */
    private String glblCmpyCd = null;

    /** Sales Date : set from pMsg */
    private String slsDt = null;

    /** Message ID Manager */
    private S21ApiMessageIdMgr msgIdMgr = null;

    /** Current System Time */
    private String currentSystemTime = null;

    /** User ID */
    private String userId = null;

    /** Local Cache (cacheDiscAllocMethMap) */
    private Map<String, String> cacheDiscAllocMethMap = null;

    // For Performance QC#10321 Add Start
    /** Local Cache (cacheMdseMap) */
    private Map<String, ALL_MDSE_VTMsg> cacheMdseMap = new HashMap<String, ALL_MDSE_VTMsg>();
    // For Performance QC#10321 Add End

    /** Constructor */
    public NWZC156001() {
        super();
    }

    public void execute(final NWZC156001PMsg inPMsg //
            , final List<NWZC156002PMsg> pMsg2List //
            , final ONBATCH_TYPE prmOnBatchType) {

        NWZC156001PMsg pMsg = new NWZC156001PMsg();
        try {
            EZDMsg.copy(inPMsg, null, pMsg, null);

            this.msgIdMgr = new S21ApiMessageIdMgr();
            this.onBatchType = prmOnBatchType;
            this.glblCmpyCd = pMsg.glblCmpyCd.getValue();
            this.slsDt = pMsg.slsDt.getValue();

            this.userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
            this.currentSystemTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

            doProcess(pMsg, pMsg2List);
            EZDMsg.copy(pMsg, null, inPMsg, null);

        } finally {
            inPMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount());
            super.updateMessage(inPMsg, this.msgIdMgr);
        }
    }

    private void doProcess(NWZC156001PMsg pMsg, List<NWZC156002PMsg> pMsg2List) {

        parameterCheck(pMsg, pMsg2List);
        if (msgIdMgr.isXxMsgId()) {
            return;
        }

        if (MODE_CREDIT.equals(pMsg.xxModeCd.getValue())) {
            execCreditMode(pMsg, pMsg2List);
        } else {
            execOtherMode(pMsg, pMsg2List);
        }
    }

    private void execOtherMode(NWZC156001PMsg pMsg, List<NWZC156002PMsg> pMsg2List) {
        Map<String, String> map = NWZC156001Query.getInstance().getPrftRuleTp(pMsg);
        if (map == null) {
            setMsgId(pMsg, NWZM1523E);
            return;
        }
        final String ordPrftRuleTpCd = map.get("ORD_PRFT_RULE_TP_CD");
        final String ordProcNodePrflCd = map.get("ORD_PROC_NODE_PRFL_CD");

        BigDecimal newVrsnNum = NWZC156001Query.getInstance().getNewVrsnNum(pMsg);
        ZYPEZDItemValueSetter.setValue(pMsg.ordPrftVrsnNum, newVrsnNum);
//        if (ZYPCommonFunc.hasValue(pMsg.csmpContrNum)) {
        if (ZYPCommonFunc.hasValue(pMsg.csmpContrNum) || ZYPCommonFunc.hasValue(pMsg.dlrRefNum)) { // QC#10121
            ZYPEZDItemValueSetter.setValue(pMsg.csmpOrdFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.csmpOrdFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.lastPrftCalcUsrId, userId);
        ZYPEZDItemValueSetter.setValue(pMsg.lastPrftCalcTs, currentSystemTime);

        NWZC156001HdrBean hdrBean = new NWZC156001HdrBean();
        getOrdPrftHdrInfo(pMsg, hdrBean, ordPrftRuleTpCd);

        List<NWZC156001DtlBean> dtlBeanList = new ArrayList<NWZC156001DtlBean>();
        // QC#21664 2017/10/06 Mod Start
        // getOrdPrftDtlInfo(pMsg, dtlBeanList, ordPrftRuleTpCd);
        getOrdPrftDtlInfo(pMsg, dtlBeanList, ordPrftRuleTpCd, ordProcNodePrflCd);
        // QC#21664 2017/10/06 Mod End

        if (ORD_PRFT_RULE_TP.EQUIPMENT.equals(ordPrftRuleTpCd)) {
            //            if (!ZYPCommonFunc.hasValue(pMsg.funcNegoDealAmt) //
            //                    && !MODE_QUOTE.equals(pMsg.xxModeCd.getValue())) {
            //                setMsgId(pMsg, NWZM1524E);
            //                return;
            //            }
            List<NWZC156001DtlBean> chngOrdInfoList = new ArrayList<NWZC156001DtlBean>();
            if (MODE_ONLINE.equals(pMsg.xxModeCd.getValue()) || MODE_UPDATE.equals(pMsg.xxModeCd.getValue())) {
                chngOrdInfoList = getChngOrdInfo(pMsg, ordPrftRuleTpCd, ordProcNodePrflCd);
                if (S21ApiUtil.isXxMsgId(pMsg)) {
                    return;
                }
                for (NWZC156001DtlBean coInfo : chngOrdInfoList) {
                    dtlBeanList.add(coInfo);
                }
            }
            List<NWZC156001DtlBean> svcOrdInfoList = new ArrayList<NWZC156001DtlBean>();
            svcOrdInfoList = getSvcOrdInfo(pMsg, ordProcNodePrflCd);
            for (NWZC156001DtlBean soInfo : svcOrdInfoList) {
                dtlBeanList.add(soInfo);
            }

            execEquipment(pMsg, pMsg2List, hdrBean, dtlBeanList, ordProcNodePrflCd);

        } else if (ORD_PRFT_RULE_TP.SUPPLY.equals(ordPrftRuleTpCd)) {
            execSupply(pMsg, pMsg2List, hdrBean, dtlBeanList, ordPrftRuleTpCd, ordProcNodePrflCd);

        } else if (ORD_PRFT_RULE_TP.PARTS.equals(ordPrftRuleTpCd)) {
            execParts(pMsg, pMsg2List, hdrBean, dtlBeanList, ordPrftRuleTpCd, ordProcNodePrflCd);
        }
    }

    private void getOrdPrftHdrInfo(NWZC156001PMsg pMsg, NWZC156001HdrBean hdrBean, String ordPrftRuleTpCd) {
        hdrBean.setOrdPrftRuleTpCd(ordPrftRuleTpCd);
        hdrBean.setTrxHdrNum(pMsg.trxHdrNum.getValue());
        hdrBean.setFuncNegoDealAmt(pMsg.funcNegoDealAmt.getValue());
        hdrBean.setCsmpContrNum(pMsg.csmpContrNum.getValue());
        hdrBean.setDlrRefNum(pMsg.dlrRefNum.getValue());
        hdrBean.setCsmpOrdFlg(pMsg.csmpOrdFlg.getValue());// 2016/03/10 S21_NA#2939
        hdrBean.setOrdPrftVrsnNum(pMsg.ordPrftVrsnNum.getValue());
    }

    private List<NWZC156001DtlBean> getSvcOrdInfo(NWZC156001PMsg pMsg, String ordProcNodePrflCd) {
        List<NWZC156001DtlBean> dtlBeanList = new ArrayList<NWZC156001DtlBean>();
        List<Map<String, Object>> mapList = NWZC156001Query.getInstance().getSvcOrdInfo(pMsg, ordProcNodePrflCd);
        if (mapList == null || mapList.size() == 0) {
            return dtlBeanList;
        }
        for (Map<String, Object> map : mapList) {
            // 2016/03/25 S21_NA#6083 Mod Start
            NWZC156001DtlBean dtlBean1 = new NWZC156001DtlBean();
            dtlBean1.setOrdPrftTrxCatgCd(ORD_PRFT_TRX_CATG.SERVICE);
            dtlBean1.setDsOrdPosnNum(((BigDecimal) map.get("SHELL_LINE_NUM")).toString());
            dtlBean1.setDsCpoLineNum(new BigDecimal("1"));
            dtlBean1.setMdseCd((String) map.get("SVC_PGM_MDSE_CD"));
            dtlBean1.setOrigMdseCd((String) map.get("SVC_PGM_MDSE_CD"));
            dtlBean1.setMaintFlPrcCatgCd((String) map.get("MAINT_FL_PRC_CATG_CD"));
            dtlBean1.setFuncNetSellPrcAmt((BigDecimal) map.get("TOT_BASE_PRC_FUNC_AMT"));
            dtlBean1.setFuncNetUnitPrcAmt((BigDecimal) map.get("TOT_BASE_PRC_FUNC_AMT"));

            ALL_MDSE_VTMsg mdseTMsg = getMdseTMsg(dtlBean1.getMdseCd());
            dtlBean1.setMdseNm(mdseTMsg.mdseNm.getValue());
            dtlBean1.setMdseDescShortTxt(mdseTMsg.mdseDescShortTxt.getValue());
            dtlBean1.setCoaMdseTpCd(mdseTMsg.coaMdseTpCd.getValue());
            dtlBean1.setCoaProdCd(mdseTMsg.coaProdCd.getValue());
            dtlBean1.setMdseItemTpCd(mdseTMsg.mdseItemTpCd.getValue());
            dtlBean1.setMdseInvtyCostPct(BigDecimal.ZERO);

            ORD_PRFT_MDSE_TP_RULETMsg mtrTMsg//
            = getOrdPrftMdseTpRuleTMsg(ORD_PROC_NODE_PRFL.EQUIPMENT.getValue(), mdseTMsg.coaMdseTpCd.getValue());
            dtlBean1.setFlPrcCalcInclFlg(mtrTMsg.flPrcCalcInclFlg.getValue());
            dtlBean1.setRepRevCalcInclFlg(mtrTMsg.repRevCalcInclFlg.getValue());
            dtlBean1.setDiscMdseTpFlg(mtrTMsg.discMdseTpFlg.getValue());
            dtlBean1.setRedFlPrcFlg(mtrTMsg.redFlPrcFlg.getValue());
            dtlBean1.setRedRepRevFlg(mtrTMsg.redRepRevFlg.getValue());
            dtlBean1.setDiscAllocMethCd(mtrTMsg.discAllocMethCd.getValue());
            dtlBean1.setDlrCrPrftInclFlg(mtrTMsg.dlrCrPrftInclFlg.getValue());
            dtlBean1.setRedCompAmtFlg(mtrTMsg.redCompAmtFlg.getValue());

            dtlBean1.setOrdQty(BigDecimal.ONE);
            dtlBean1.setOrdCustUomQty(BigDecimal.ONE);

            dtlBeanList.add(dtlBean1);
        }
        getSvcPrcInfo(pMsg, dtlBeanList);
        return dtlBeanList;
    }

    // QC#21664 2017/10/06 Mod Start
    //private void getOrdPrftDtlInfo(NWZC156001PMsg pMsg, List<NWZC156001DtlBean> dtlBeanList, String ordPrftRuleTpCd) {
    private void getOrdPrftDtlInfo(NWZC156001PMsg pMsg, List<NWZC156001DtlBean> dtlBeanList, String ordPrftRuleTpCd, String ordProcNodePrflCd) {
    // QC#21664 2017/10/06 Mod End

        //        List<String> keyList = new ArrayList<String>();
        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
            NWZC156001DtlBean dtlBean = new NWZC156001DtlBean();
            setOrdPrftDtlFromPMsg(dtlBean, pMsg, scrPMsg);

            dtlBean.setDsOrdPosnNum(scrPMsg.dsOrdPosnNum.getValue());
            dtlBean.setDsCpoLineNum(scrPMsg.dsCpoLineNum.getValue());
            dtlBean.setDsCpoLineSubNum(scrPMsg.dsCpoLineSubNum.getValue());

            dtlBean.setBillToCustCd(scrPMsg.billToCustCd.getValue());
            dtlBean.setShipToCustCd(scrPMsg.shipToCustCd.getValue());
            dtlBean.setOrdPrftRuleTpCd(ordPrftRuleTpCd);

            // QC#12112
            dtlBean.setOrigMdseCd(getOrigMdseCd(scrPMsg.mdseCd.getValue(), dtlBean, pMsg));
            ZYPEZDItemValueSetter.setValue(scrPMsg.origMdseCd, dtlBean.getOrigMdseCd());
            ZYPEZDItemValueSetter.setValue(scrPMsg.mdseCd, scrPMsg.origMdseCd);
            dtlBean.setMdseCd(scrPMsg.mdseCd.getValue());

            ALL_MDSE_VTMsg mdseTMsg = getMdseTMsg(scrPMsg.mdseCd.getValue());
            if (mdseTMsg != null) {
                dtlBean.setMdseNm(mdseTMsg.mdseNm.getValue());
                dtlBean.setMdseDescShortTxt(mdseTMsg.mdseDescShortTxt.getValue());// 2016/03/10 S21_NA#2939
                dtlBean.setCoaMdseTpCd(mdseTMsg.coaMdseTpCd.getValue());
                dtlBean.setCoaProdCd(mdseTMsg.coaProdCd.getValue());
                dtlBean.setMdseItemTpCd(mdseTMsg.mdseItemTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitStdCostAmt, mdseTMsg.thisMthTotStdCostAmt);
                dtlBean.setFuncUnitStdCostAmt(mdseTMsg.thisMthTotStdCostAmt.getValue());
            }

            // S21_NA#11981
            BigDecimal mdseInvtyCostPct = null;
            if (isRebillOrderLine(pMsg, scrPMsg)) {
                // For Rebill Order
                mdseInvtyCostPct = getOrigLatestPrftInfo(pMsg, scrPMsg);
            }
            if (!ZYPCommonFunc.hasValue(mdseInvtyCostPct)) {
                SWHTMsg swhTMsg = getSwhTMsg(scrPMsg.rtlWhCd.getValue(), scrPMsg.rtlSwhCd.getValue());// 2016/03/10 S21_NA#2939
                mdseInvtyCostPct = swhTMsg.mdseInvtyCostPct.getValue();
            }
            dtlBean.setMdseInvtyCostPct(mdseInvtyCostPct);
            scrPMsg.mdseInvtyCostPct.setValue(mdseInvtyCostPct);// 2016/03/10 S21_NA#2939

            // QC#21664 2017/10/06 Mod Start
            //ORD_PRFT_MDSE_TP_RULETMsg mtrTMsg//
            // = getOrdPrftMdseTpRuleTMsg(ORD_PROC_NODE_PRFL.EQUIPMENT.getValue(), mdseTMsg.coaMdseTpCd.getValue());
            ORD_PRFT_MDSE_TP_RULETMsg mtrTMsg
            = getOrdPrftMdseTpRuleTMsg(ordProcNodePrflCd, mdseTMsg.coaMdseTpCd.getValue());
            // QC#21664 2017/10/06 Mod End
            dtlBean.setFlPrcCalcInclFlg(mtrTMsg.flPrcCalcInclFlg.getValue());
            dtlBean.setRepRevCalcInclFlg(mtrTMsg.repRevCalcInclFlg.getValue());
            dtlBean.setDiscMdseTpFlg(mtrTMsg.discMdseTpFlg.getValue());
            dtlBean.setRedFlPrcFlg(mtrTMsg.redFlPrcFlg.getValue());
            dtlBean.setRedRepRevFlg(mtrTMsg.redRepRevFlg.getValue());
            dtlBean.setDiscAllocMethCd(mtrTMsg.discAllocMethCd.getValue());
            dtlBean.setDlrCrPrftInclFlg(mtrTMsg.dlrCrPrftInclFlg.getValue());
            dtlBean.setRedCompAmtFlg(mtrTMsg.redCompAmtFlg.getValue());

            PRC_CATGTMsg prcCatgTMsg = getPrcCatgTMsg(scrPMsg.flPrcListCd.getValue());// 2016/03/10 S21_NA#2939
            dtlBean.setFlPrcListNm(prcCatgTMsg.prcCatgNm.getValue());// 2016/03/10 S21_NA#2939
            prcCatgTMsg = getPrcCatgTMsg(scrPMsg.csmpPrcListCd.getValue());// 2016/03/10 S21_NA#2939
            dtlBean.setCsmpPrcListNm(prcCatgTMsg.prcCatgNm.getValue());// 2016/03/10 S21_NA#2939
            prcCatgTMsg = getPrcCatgTMsg(scrPMsg.prcCatgCd.getValue()); // QC#7707
            dtlBean.setPrcCatgNm(prcCatgTMsg.prcCatgNm.getValue()); // QC#7707

            dtlBeanList.add(dtlBean);
            //            keyList.add((String) rsltMap.get("ORD_KEY"));
        }

    }

    private String getOrigMdseCd(String mdseCd, NWZC156001DtlBean dtlBean, NWZC156001PMsg pMsg) {
        String origMdseCd = NWZC156001Query.getInstance().getOrigMdseCd(dtlBean, pMsg);
        if (ZYPCommonFunc.hasValue(origMdseCd)) {
            return origMdseCd;
        }
        return mdseCd;
    }

    private SWHTMsg getSwhTMsg(String rtlWhCd, String rtlSwhCd) {// 2016/03/10 S21_NA#2939

        // 2016/03/10 S21_NA#2939
        if (!ZYPCommonFunc.hasValue(rtlSwhCd)) {
            SWHTMsg tMsg = new SWHTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.mdseInvtyCostPct, new BigDecimal("100"));
            return tMsg;
        }


        SWHTMsg tMsg = new SWHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, rtlSwhCd);

        tMsg = (SWHTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return new SWHTMsg();
        }
        return tMsg;
    }

    private ALL_MDSE_VTMsg getMdseTMsg(String mdseCd) {

        // 01/24/2017 QC#17003 Mod Start
        // For Performance QC#10321 Add Start
        ALL_MDSE_VTMsg tMsg = cacheMdseMap.get(mdseCd);
        if (tMsg != null) {
            return tMsg;
        }
        // For Performance QC#10321 Add End

        ALL_MDSE_VTMsg cond = new ALL_MDSE_VTMsg();
        ALL_MDSE_VTMsgArray outAllMdseVTMsg = null;
        cond.setSQLID("003");
        cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cond.setConditionValue("mdseCd01", mdseCd);

        outAllMdseVTMsg = (ALL_MDSE_VTMsgArray) S21ApiTBLAccessor.findByCondition(cond);
        if (outAllMdseVTMsg == null || outAllMdseVTMsg.length() == 0) {
            return null;
        } else {
            cacheMdseMap.put(mdseCd, (ALL_MDSE_VTMsg)outAllMdseVTMsg.get(0)); // For Performance QC#10321 Add
            return (ALL_MDSE_VTMsg)outAllMdseVTMsg.get(0);
        }

//        // For Performance QC#10321 Add Start
//        ALL_MDSE_VTMsg tMsg = cacheMdseMap.get(mdseCd);
//        if (tMsg != null) {
//            return tMsg;
//        }
//        // For Performance QC#10321 Add End
//
//        tMsg = new ALL_MDSE_VTMsg();
//        S21SsmEZDResult rslt = NWZC156001Query.getInstance().getAllMdseVTMsg(tMsg, glblCmpyCd, mdseCd);
//        if (rslt.isCodeNormal()) {
//            cacheMdseMap.put(mdseCd, tMsg); // For Performance QC#10321 Add
//            return tMsg;
//        }
//        return new ALL_MDSE_VTMsg();
        // 01/24/2017 QC#17003 Mod End
    }

    //    private String getOrdKey(String cpoOrdNum, NWZC156001_svcConfigRefPMsg scrPMsg) {
    //        StringBuilder sb = new StringBuilder(30);
    //
    //        sb.append(cpoOrdNum) //
    //                .append(scrPMsg.dsOrdPosnNum.getValue()) //
    //                .append(scrPMsg.dsCpoLineNum.getValueInt()) //
    //                .append(scrPMsg.dsCpoLineSubNum.getValueInt());
    //        return sb.toString();
    //    }

    private void setOrdPrftDtlFromPMsg(NWZC156001DtlBean dtlBean, NWZC156001PMsg pMsg, NWZC156001_svcConfigRefPMsg scrPMsg) {

        dtlBean.setOrdPrftTrxCatgCd(scrPMsg.ordPrftTrxCatgCd.getValue());
        dtlBean.setTrxLineNum(scrPMsg.trxLineNum.getValue());
        dtlBean.setTrxLineSubNum(scrPMsg.trxLineSubNum.getValue());
        dtlBean.setRtlWhCd(scrPMsg.rtlWhCd.getValue());
        dtlBean.setRtlSwhCd(scrPMsg.rtlSwhCd.getValue());
        dtlBean.setDsOrdLineCatgCd(scrPMsg.dsOrdLineCatgCd.getValue());
        dtlBean.setOrdQty(scrPMsg.ordQty.getValue());
        dtlBean.setCustUomCd(scrPMsg.custUomCd.getValue());
        dtlBean.setFlPrcListCd(scrPMsg.flPrcListCd.getValue());
        dtlBean.setCsmpPrcListCd(scrPMsg.csmpPrcListCd.getValue());
        dtlBean.setPrcCatgCd(scrPMsg.prcCatgCd.getValue()); // QC#7707
        dtlBean.setFuncManFlAdjAmt(scrPMsg.funcManFlAdjAmt.getValue());
        dtlBean.setFuncSvcCostTrnsfAmt(scrPMsg.funcSvcCostTrnsfAmt.getValue());
        dtlBean.setFuncUnitListPrcAmt(scrPMsg.funcUnitListPrcAmt.getValue());
        dtlBean.setFuncNetUnitPrcAmt(scrPMsg.funcNetUnitPrcAmt.getValue());
        dtlBean.setFuncNetSellPrcAmt(scrPMsg.funcNetSellPrcAmt.getValue());
        dtlBean.setFuncManRepRevAdjAmt(scrPMsg.funcManRepRevAdjAmt.getValue());
        dtlBean.setFuncSvcRevTrnsfAmt(scrPMsg.funcSvcRevTrnsfAmt.getValue());
        dtlBean.setFuncUnitStdCostAmt(scrPMsg.funcUnitStdCostAmt.getValue());
        dtlBean.setTrxRefLineNum(scrPMsg.trxRefLineNum.getValue());// 2016/03/10 S21_NA#2939
        dtlBean.setTrxRefLineSubNum(scrPMsg.trxRefLineSubNum.getValue());// 2016/03/10 S21_NA#2939
        dtlBean.setCpoDtlFuncSlsAmt(scrPMsg.cpoDtlFuncSlsAmt.getValue());// 2016/03/10 S21_NA#2939
        dtlBean.setOrdCustUomQty(scrPMsg.ordCustUomQty.getValue());// 2016/03/10 S21_NA#2939
        dtlBean.setCsmpContrNum(scrPMsg.csmpContrNum.getValue());// 2016/03/10 S21_NA#2939
        dtlBean.setDlrRefNum(scrPMsg.dlrRefNum.getValue());// 2016/03/10 S21_NA#2939
    }

    private void execParts(//
            NWZC156001PMsg pMsg //
            , List<NWZC156002PMsg> pMsg2List //
            , NWZC156001HdrBean hdrBean //
            , List<NWZC156001DtlBean> dtlBeanList //
            , String ordPrftRuleTpCd //
            , String ordProcNodePrflCd) {
        // Parts process is same as Supply process.
        execSupply(pMsg, pMsg2List, hdrBean, dtlBeanList, ordPrftRuleTpCd, ordProcNodePrflCd);
    }

    private void execSupply(//
            NWZC156001PMsg pMsg //
            , List<NWZC156002PMsg> pMsg2List //
            , NWZC156001HdrBean hdrBean //
            , List<NWZC156001DtlBean> dtlBeanList //
            , String ordPrftRuleTpCd //
            , String ordProcNodePrflCd) {

        BigDecimal totGpAmt = BigDecimal.ZERO;
        BigDecimal totSellPrcAmt = BigDecimal.ZERO;
        BigDecimal totMsrpAmt = BigDecimal.ZERO;
        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
//            Map<String, BigDecimal> costMap = NWZC156001Query.getInstance().getLatestCost(pMsg, scrPMsg);// 2016/03/10 S21_NA#2939
//            BigDecimal equipPrcAmt = NWZC156001Query.getInstance().getMSRPAmt(pMsg, scrPMsg); // QC#7285
            BigDecimal equipPrcAmt = NWZC156001Query.getInstance().getMSRPAmt(pMsg, scrPMsg.mdseCd.getValue()); // QC#7285
//            if (costMap == null) {// 2016/03/10 S21_NA#2939
//                continue;
//            }
//            BigDecimal stdCost = (BigDecimal) costMap.get("THIS_MTH_TOT_STD_COST_AMT");// 2016/03/10 S21_NA#2939
            BigDecimal stdCost = scrPMsg.funcUnitStdCostAmt.getValue();// 2016/03/10 S21_NA#2939
//            BigDecimal equipPrcAmt = (BigDecimal) costMap.get("PRC_LIST_EQUIP_PRC_AMT");// 2016/03/10 S21_NA#2939
//            BigDecimal ordQty = scrPMsg.ordQty.getValue();// 2016/03/10 S21_NA#2939
            BigDecimal ordCustUomQty = scrPMsg.ordCustUomQty.getValue();// 2016/03/10 S21_NA#2939
            BigDecimal ordQty = scrPMsg.ordQty.getValue();// 2016/03/10 S21_NA#2939
            BigDecimal sellPrcAmt = scrPMsg.funcNetUnitPrcAmt.getValue().multiply(ordCustUomQty);// 2016/03/10 S21_NA#2939
            BigDecimal gpAmt //
            = (scrPMsg.funcNetUnitPrcAmt.getValue()//
                    .subtract(stdCost)) //
//                    .multiply(ordCustUomQty);// 2016/03/10 S21_NA#2939
                    .multiply(ordQty); // QC#7707
            //            BigDecimal gpPct = gpAmt.divide(sellPrcAmt);

            ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitStdCostAmt, stdCost);
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcFinalStdCostAmt //
//                    , stdCost.multiply(scrPMsg.mdseInvtyCostPct.getValue()).multiply(ordCustUomQty));// 2016/03/10 S21_NA#2939
                    , stdCost.multiply(scrPMsg.mdseInvtyCostPct.getValue()).multiply(ordCustUomQty) // QC#7285
                    .divide(BIG_DECIMAL_100, AMT_SCALE, RoundingMode.HALF_UP)); // QC#7285
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcNetSellPrcAmt //
                    , scrPMsg.funcNetUnitPrcAmt.getValue().multiply(ordCustUomQty));// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitMsrpAmt, equipPrcAmt);// 2016/03/10 S21_NA#2939

            totGpAmt = totGpAmt.add(gpAmt);
            totSellPrcAmt = totSellPrcAmt.add(sellPrcAmt);
            totMsrpAmt = totMsrpAmt.add(equipPrcAmt.multiply(ordQty));
        }
        BigDecimal totGpPct = BigDecimal.ZERO;
        if (BigDecimal.ZERO.compareTo(totSellPrcAmt) != 0) {
            totGpPct = totGpAmt.multiply(BIG_DECIMAL_100).divide(totSellPrcAmt, PCT_SCALE, RoundingMode.HALF_UP); // QC#7296
//            totGpPct = totGpAmt.divide(totSellPrcAmt //
//                    , PCT_SCALE, RoundingMode.HALF_UP) // 2016/03/10 S21_NA#2939
//                    .multiply(BIG_DECIMAL_100);// 2016/03/10 S21_NA#2939
        }
        //
        String currTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        editPMsgHdrForSupply(pMsg, totGpAmt, totMsrpAmt, totGpPct, currTs);

        //
        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
            editPMsgDtlForSupply(scrPMsg, pMsg, currTs, ordPrftRuleTpCd, ordProcNodePrflCd);
        }

        if (MODE_UPDATE.equals(pMsg.xxModeCd.getValue())) {
            ORD_PRFTTMsg ordPrftTMsg = createOrdPrftTMsgForSupply(pMsg, currTs);
            List<ORD_PRFT_DTLTMsg> ordPrftDtlTMsgList = createOrdPrftDtlTMsgForSupply(pMsg, ordPrftTMsg);
            S21FastTBLAccessor.insert(ordPrftTMsg);
            int cnt = S21FastTBLAccessor.insert(ordPrftDtlTMsgList.toArray(new ORD_PRFT_DTLTMsg[0]));
            if (cnt != ordPrftDtlTMsgList.size()) {
                NWZC156002PMsg pMsg2 = new NWZC156002PMsg();
                setMsgId2(pMsg2, NWZM1522E);
                pMsg2List.add(pMsg2);
            }
        }
        return;

    }

    private List<ORD_PRFT_DTLTMsg> createOrdPrftDtlTMsgForSupply(NWZC156001PMsg pMsg, ORD_PRFTTMsg ordPrftTMsg) {
        List<ORD_PRFT_DTLTMsg> tMsgList = new ArrayList<ORD_PRFT_DTLTMsg>();

        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
            ORD_PRFT_DTLTMsg tMsg = new ORD_PRFT_DTLTMsg();
            EZDMsg.copy(scrPMsg, null, tMsg, null);

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRFT_DTL_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftPk, ordPrftTMsg.ordPrftPk);
            ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, ordPrftTMsg.trxHdrNum);
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftVrsnNum, ordPrftTMsg.ordPrftVrsnNum);

//            ZYPEZDItemValueSetter.setValue(tMsg.csmpContrNum, ordPrftTMsg.csmpContrNum);
//            ZYPEZDItemValueSetter.setValue(tMsg.dlrRefNum, ordPrftTMsg.dlrRefNum);
            ZYPEZDItemValueSetter.setValue(tMsg.csmpContrStartDt, ordPrftTMsg.csmpContrEndDt);
            ZYPEZDItemValueSetter.setValue(tMsg.csmpContrEndDt, ordPrftTMsg.csmpContrEndDt);

            ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcUsrId, ordPrftTMsg.lastPrftCalcUsrId);
            ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcTs, ordPrftTMsg.lastPrftCalcTs);

            // FLG 2016/03/23 S21_NA#3315
            final Set<EZDTStringItem> flgItemList = new HashSet<EZDTStringItem>();
            flgItemList.add(tMsg.chngOrdFlg);
            flgItemList.add(tMsg.discMdseTpFlg);
            flgItemList.add(tMsg.dlrCrPrftInclFlg);
            flgItemList.add(tMsg.flPrcCalcInclFlg);
            flgItemList.add(tMsg.redCompAmtFlg);
            flgItemList.add(tMsg.redFlPrcFlg);
            flgItemList.add(tMsg.redRepRevFlg);
            flgItemList.add(tMsg.repRevCalcInclFlg);

            for (EZDTStringItem flgItem : flgItemList) {
                if (!ZYPCommonFunc.hasValue(flgItem)) {
                    ZYPEZDItemValueSetter.setValue(flgItem, ZYPConstant.FLG_OFF_N);
                }
            }

            tMsgList.add(tMsg);
        }
        return tMsgList;
    }

    private void editPMsgDtlForSupply(//
            NWZC156001_svcConfigRefPMsg scrPMsg //
            , NWZC156001PMsg pMsg //
            , String currTs //
            , String ordPrftRuleTpCd //
            , String ordProcNodePrflCd) {

        ALL_MDSE_VTMsg mdseTMsg = getMdseTMsg(scrPMsg.mdseCd.getValue());// 2016/03/10 S21_NA#2939
        ORD_PRFT_MDSE_TP_RULETMsg ordPrftMdseTpRuleTMsg //
        = getOrdPrftMdseTpRuleTMsg(ordProcNodePrflCd, mdseTMsg.coaMdseTpCd.getValue());

        ZYPEZDItemValueSetter.setValue(scrPMsg.mdseNm, mdseTMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(scrPMsg.mdseDescShortTxt, mdseTMsg.mdseDescShortTxt);// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcDlrCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcRedCompAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitFlPrcAmt, BigDecimal.ZERO);// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcInitFlPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.lineWtAmtRate, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcGenlFlAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcSpecFlAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcManFlAdjAmt, BigDecimal.ZERO);// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcFlAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcCsmpUnitCrAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(scrPMsg.funcCsmpCrAmt, BigDecimal.ZERO); // QC#7301
        scrPMsg.funcCsmpCrAmt.clear(); // QC#7301
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcCsmpFlPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcSvcCostTrnsfAmt, BigDecimal.ZERO);// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcWtSvcCostTrnsfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcFinalFlPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitListPrcAmt, BigDecimal.ZERO);// 2016/03/10 S21_NA#2939
//        ZYPEZDItemValueSetter.setValue(scrPMsg.funcNetUnitPrcAmt, BigDecimal.ZERO);// 2016/03/10 S21_NA#2939 // QC#9123
        // funcNetSellPrcAmt is not need mapping.
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcInitRepRevAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcGenlRepRevAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcSpecRepRevAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcManRepRevAdjAmt, BigDecimal.ZERO);// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcRepRevAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcSvcRevTrnsfAmt, BigDecimal.ZERO);// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcWtSvcRevTrnsfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrPMsg.funcFinalRepRevAmt, BigDecimal.ZERO);
        // funcUnitMsrpAmt is not need mapping.
        // funcUnitStdCostAmt is not need mapping.
        // funcFinalStdCostAmt is not need mapping.

        if (ZYPCommonFunc.hasValue(pMsg.dlrRefNum)) {
            ZYPEZDItemValueSetter.setValue(scrPMsg.chngOrdFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(scrPMsg.chngOrdFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(scrPMsg.coaMdseTpCd, mdseTMsg.coaMdseTpCd);
        ZYPEZDItemValueSetter.setValue(scrPMsg.coaProdCd, mdseTMsg.coaProdCd);
        ZYPEZDItemValueSetter.setValue(scrPMsg.mdseItemTpCd, mdseTMsg.mdseItemTpCd);

        ZYPEZDItemValueSetter.setValue(scrPMsg.lastPrftCalcUsrId, userId);
        ZYPEZDItemValueSetter.setValue(scrPMsg.lastPrftCalcTs, currTs);

        ZYPEZDItemValueSetter.setValue(scrPMsg.ordPrftRuleTpCd, ordPrftRuleTpCd);
        ZYPEZDItemValueSetter.setValue(scrPMsg.flPrcCalcInclFlg, ordPrftMdseTpRuleTMsg.flPrcCalcInclFlg);
        ZYPEZDItemValueSetter.setValue(scrPMsg.repRevCalcInclFlg, ordPrftMdseTpRuleTMsg.repRevCalcInclFlg);
        ZYPEZDItemValueSetter.setValue(scrPMsg.discMdseTpFlg, ordPrftMdseTpRuleTMsg.discMdseTpFlg);
        ZYPEZDItemValueSetter.setValue(scrPMsg.redFlPrcFlg, ordPrftMdseTpRuleTMsg.redFlPrcFlg);
        ZYPEZDItemValueSetter.setValue(scrPMsg.redRepRevFlg, ordPrftMdseTpRuleTMsg.redRepRevFlg);
        ZYPEZDItemValueSetter.setValue(scrPMsg.discAllocMethCd, ordPrftMdseTpRuleTMsg.discAllocMethCd);
        ZYPEZDItemValueSetter.setValue(scrPMsg.dlrCrPrftInclFlg, ordPrftMdseTpRuleTMsg.dlrCrPrftInclFlg);
        ZYPEZDItemValueSetter.setValue(scrPMsg.redCompAmtFlg, ordPrftMdseTpRuleTMsg.redCompAmtFlg);
    }

    private ORD_PRFT_MDSE_TP_RULETMsg getOrdPrftMdseTpRuleTMsg(String ordProcNodePrflCd, String coaMdseTpCd) {
        ORD_PRFT_MDSE_TP_RULETMsg tMsg = new ORD_PRFT_MDSE_TP_RULETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordProcNodePrflCd, ordProcNodePrflCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaMdseTpCd, coaMdseTpCd);
        tMsg = (ORD_PRFT_MDSE_TP_RULETMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return new ORD_PRFT_MDSE_TP_RULETMsg();
        }
        return tMsg;
    }

//    private MDSETMsg getmdse(String mdseCd) {
//        MDSETMsg tMsg = new MDSETMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
//        tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
//        if (tMsg == null) {
//            return new MDSETMsg();
//        }
//        return tMsg;
//    }

    // 2016/03/10 Delete Start S21_NA#2939
//    private ALL_MDSE_VTMsg getMdse(String mdseCd) {
//        ALL_MDSE_VTMsg tMsg = new ALL_MDSE_VTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
//        tMsg = (ALL_MDSE_VTMsg) S21CacheTBLAccessor.findByKey(tMsg);
//        if (tMsg == null) {
//            return new ALL_MDSE_VTMsg();
//        }
//        return tMsg;
//    }
    // 2016/03/10 Delete End S21_NA#2939

    /**
     * <pre>
     * @param pMsg          NWZC156001PMsg
     * @param totGpAmt      Total Gross Profit Amount
     * @param totMsrpAmt    Total MSRP Amount
     * @param totGpPct      Total Gross Profit Percent
     * @param currTs        Current Time stamp
     * </pre>
     */
    private void editPMsgHdrForSupply(NWZC156001PMsg pMsg, BigDecimal totGpAmt, BigDecimal totMsrpAmt, BigDecimal totGpPct, String currTs) {
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncRepRevAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncRepRevAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncFinalFlAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(pMsg.funcGrsPrftAmt, totGpAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.grsPrftPct, totGpPct);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncMsrpAmt, totMsrpAmt);

        ZYPEZDItemValueSetter.setValue(pMsg.totFuncStdFlAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncFlAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncCsmpCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncCsmpFlAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(pMsg.lastPrftCalcUsrId, userId);
        ZYPEZDItemValueSetter.setValue(pMsg.lastPrftCalcTs, currTs);

        ZYPEZDItemValueSetter.setValue(pMsg.totFuncByotAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncSvcRevTrnsfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncSvcCostTrnsfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncProSvcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncOmMaintBllblAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.funcAltGrsPrftAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.altGrsPrftPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncDlrCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncDlrInvAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncRedCompAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.xxOrdPrftFndrFeeAmt, BigDecimal.ZERO);// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(pMsg.xxOrdPrftIstlCrAmt, BigDecimal.ZERO);// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(pMsg.xxOrdPrftSplyAmt, BigDecimal.ZERO);// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(pMsg.xxOrdPrftSvcAmt, BigDecimal.ZERO);// 2016/03/10 S21_NA#2939

    }

    private ORD_PRFTTMsg createOrdPrftTMsgForSupply(NWZC156001PMsg pMsg, String currTs) {
        ORD_PRFTTMsg tMsg = new ORD_PRFTTMsg();

        EZDMsg.copy(pMsg, null, tMsg, null);

        ZYPEZDItemValueSetter.setValue(tMsg.ordPrftPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRFT_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcUsrId, userId);
        ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcTs, currTs);

        //        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        //        ZYPEZDItemValueSetter.setValue(tMsg.ordPrftPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRFT_SQ));
        //        ZYPEZDItemValueSetter.setValue(tMsg.ordPrftVrsnNum, pMsg.ordPrftVrsnNum);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncRepRevAmt, pMsg.totFuncRepRevAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.funcNegoDealAmt, pMsg.funcNegoDealAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncRepRevAdjAmt, pMsg.totFuncRepRevAdjAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncFinalFlAmt, pMsg.totFuncFinalFlAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.funcGrsPrftAmt, pMsg.funcGrsPrftAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.grsPrftPct, pMsg.grsPrftPct);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncMsrpAmt, pMsg.totFuncMsrpAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncStdFlAmt, pMsg.totFuncStdFlAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncFlAdjAmt, pMsg.totFuncFlAdjAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.csmpOrdFlg, pMsg.csmpOrdFlg);
        //        ZYPEZDItemValueSetter.setValue(tMsg.csmpContrStartDt, pMsg.csmpContrStartDt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.csmpContrEndDt, pMsg.csmpContrEndDt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncCsmpCrAmt, pMsg.totFuncCsmpCrAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncCsmpFlAmt, pMsg.totFuncCsmpFlAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcUsrId, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        //        ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcTs, currTs);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncByotAmt, pMsg.totFuncByotAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncSvcRevTrnsfAmt, pMsg.totFuncSvcRevTrnsfAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncSvcCostTrnsfAmt, pMsg.totFuncSvcCostTrnsfAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncProSvcAmt, pMsg.totFuncProSvcAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncOmMaintBllblAmt, pMsg.totFuncOmMaintBllblAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.funcAltGrsPrftAmt, pMsg.funcAltGrsPrftAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.altGrsPrftPct, pMsg.altGrsPrftPct);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncDlrCrAmt, pMsg.totFuncDlrCrAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncDlrInvAmt, pMsg.totFuncDlrInvAmt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.totFuncRedCompAmt, pMsg.totFuncRedCompAmt);
        return tMsg;
    }

    private void execEquipment(//
            NWZC156001PMsg pMsg //
            , List<NWZC156002PMsg> pMsg2List //
            , NWZC156001HdrBean hdrBean //
            , List<NWZC156001DtlBean> dtlBeanList //
            , String ordProcNodePrflCd) {

        hdrBean.setXxFndrFeeAmt(getFinderFeeAmt(pMsg));
        hdrBean.setXxIstlCrAmt(getIstlCrAmt(pMsg));
//        hdrBean.setTotFuncOmMaintBllblAmt(getSvcAmt(pMsg));
        hdrBean.setTotFuncOmMaintBllblAmt(getSvcAmt(pMsg, dtlBeanList)); // QC#7810
        hdrBean.setXxSplyAmt(getSplyAmt(pMsg));

        derivePrice(hdrBean, dtlBeanList, pMsg, pMsg2List);

        // 2016/03/10 S21_NA#2939
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            // Sell Price
//            dtlBean.setFuncNetSellPrcAmt(dtlBean.getFuncNetUnitPrcAmt().multiply(dtlBean.getOrdCustUomQty()));
            dtlBean.setFuncNetSellPrcAmt(dtlBean.getFuncNetUnitPrcAmt().multiply(dtlBean.getOrdQty())); // QC#7707
        }
        // QC#7810
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            //Initial Rep Revenue
            if (ZYPConstant.FLG_ON_Y.equals(dtlBean.getRepRevCalcInclFlg())) {
//                dtlBean.setFuncFinalUnitRevAmt(dtlBean.getFuncNetUnitPrcAmt()); // QC#7707 // QC#7791
//                dtlBean.setFuncInitRepRevAmt(dtlBean.getFuncNetSellPrcAmt());
                dtlBean.setFuncInitRepRevAmt(dtlBean.getFuncNetUnitPrcAmt()); // QC#7791
            } else {
//                dtlBean.setFuncFinalUnitRevAmt(BigDecimal.ZERO); // QC#7707 // QC#7791
                dtlBean.setFuncInitRepRevAmt(BigDecimal.ZERO);
            }
        }

        BigDecimal totFuncInitFlPrcAmt = BigDecimal.ZERO;
        BigDecimal totExtendedFuncFlPrcAmt = BigDecimal.ZERO; // QC#7791
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            //initial floor price
            dtlBean.setFuncUnitFlPrcAmt(culcFuncUnitFlPrcAmt(dtlBean));

            if (ZYPConstant.FLG_ON_Y.equals(dtlBean.getFlPrcCalcInclFlg())) {
//                dtlBean.setFuncFinalUnitFlPrcAmt(dtlBean.getFuncUnitFlPrcAmt()); // QC#7707 // QC#7791
                dtlBean.setFuncInitFlPrcAmt(culcFuncInitFlPrcAmt(dtlBean));
                totFuncInitFlPrcAmt = totFuncInitFlPrcAmt.add(dtlBean.getFuncInitFlPrcAmt());
                totExtendedFuncFlPrcAmt = totExtendedFuncFlPrcAmt.add(dtlBean.getFuncInitFlPrcAmt().multiply(dtlBean.getOrdQty())); // QC#7791
            } else {
//                dtlBean.setFuncFinalUnitFlPrcAmt(BigDecimal.ZERO); // QC#7707 // QC#7791
                dtlBean.setFuncInitFlPrcAmt(BigDecimal.ZERO);
            }

        }
//        hdrBean.setTotFuncStdFlAmt(totFuncInitFlPrcAmt);
        hdrBean.setTotFuncStdFlAmt(totExtendedFuncFlPrcAmt); // QC#7791

        // calculate the line weight amount rate.
        BigDecimal remain = BigDecimal.ONE.setScale(RATE_SCALE);
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            if (BigDecimal.ZERO.compareTo(hdrBean.getTotFuncStdFlAmt()) != 0) {
                dtlBean.setLineWtAmtRate(//
//                        dtlBean.getFuncUnitFlPrcAmt().multiply(dtlBean.getOrdCustUomQty())// 2016/03/10 S21_NA#2939
//                        dtlBean.getFuncUnitFlPrcAmt().multiply(dtlBean.getOrdQty()) // QC#7707
                        dtlBean.getFuncUnitFlPrcAmt() // QC#7791
//                            .divide(hdrBean.getTotFuncStdFlAmt() //
                            .divide(totFuncInitFlPrcAmt // QC#7791
                                , RATE_SCALE //
                                , RoundingMode.HALF_UP));
            } else {
                dtlBean.setLineWtAmtRate(BigDecimal.ZERO.setScale(RATE_SCALE));
            }
            remain = remain.subtract(dtlBean.getLineWtAmtRate());
        }
        dtlBeanList.get(0).setLineWtAmtRate(//
                dtlBeanList.get(0).getLineWtAmtRate().add(remain));

        // QC#7707
        int adjIndex = 0;
        BigDecimal backWtRate = BigDecimal.ZERO;
        for (int i = 0; i < dtlBeanList.size(); i++) {
            NWZC156001DtlBean dtlBean = dtlBeanList.get(i);
            if (dtlBean.getLineWtAmtRate().compareTo(backWtRate) > 0) {
                adjIndex = i;
                backWtRate = dtlBean.getLineWtAmtRate();
            }
        }

        // calculate the floor adjustment amount.
//        BigDecimal wkGenAdjFlAmt = BigDecimal.ZERO;// 2016/03/10 S21_NA#2939
        BigDecimal totFuncGenlFlAdjAmt = BigDecimal.ZERO; // QC#7290
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            if (ZYPConstant.FLG_OFF_N.equals(dtlBean.getDiscMdseTpFlg()) //
                    || ZYPConstant.FLG_OFF_N.equals(dtlBean.getRedFlPrcFlg())) {
                continue;
            }
            if (DISC_ALLOC_METH.GENERIC.equals(dtlBean.getDiscAllocMethCd())) {
//                hdrBean.setWkTotGenAdjFlAmt(hdrBean.getWkTotGenAdjFlAmt().add(dtlBean.getGenAdjFlAmt()));// 2016/03/10 S21_NA#2939
//                dtlBean.setFuncGenlFlAdjAmt(dtlBean.getFuncInitFlPrcAmt().multiply(dtlBean.getLineWtAmtRate()));
//                dtlBean.setFuncGenlFlAdjAmt(dtlBean.getFuncNetSellPrcAmt().multiply(dtlBean.getLineWtAmtRate()));
                totFuncGenlFlAdjAmt = totFuncGenlFlAdjAmt.add(dtlBean.getFuncNetSellPrcAmt()); // QC#7290

            } else if (DISC_ALLOC_METH.SPECIFIC.equals(dtlBean.getDiscAllocMethCd())) {
                setSpecFlAdjAmtToRefLine(//
                        dtlBeanList //
//                        , dtlBean.getFuncInitFlPrcAmt() //
                        , dtlBean.getFuncNetSellPrcAmt() // QC#7290
                        , dtlBean.getTrxRefLineNum() //
                        , dtlBean.getTrxRefLineSubNum());
            }
        }

        // calculate funcGenlFlAdjAmt // QC#7290
        BigDecimal subTotAmt = BigDecimal.ZERO; // QC#7707
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            dtlBean.setFuncGenlFlAdjAmt(totFuncGenlFlAdjAmt.multiply(dtlBean.getLineWtAmtRate()).setScale(AMT_SCALE, RoundingMode.HALF_UP));
            subTotAmt = subTotAmt.add(dtlBean.getFuncGenlFlAdjAmt()); // QC#7707
        }
        dtlBeanList.get(adjIndex).setFuncGenlFlAdjAmt( //
                dtlBeanList.get(adjIndex).getFuncGenlFlAdjAmt() //
                        .add(totFuncGenlFlAdjAmt.subtract(subTotAmt))); // QC#7707

        // QC#7707 Add Start
        String discAllocMethSRT = getDiscAllocMethCd(pMsg, MDSE_TP_CTX_TP.PRFT_SRT_MDSE_TP.toString());
        String discAllocMethCT = getDiscAllocMethCd(pMsg, MDSE_TP_CTX_TP.PRFT_CT_MDSE_TP.toString());
        String discAllocMethManFl = getDiscAllocMethCd(pMsg, MDSE_TP_CTX_TP.PRFT_MFLR_MDSE_TP.toString());
        String discAllocMethManRep = getDiscAllocMethCd(pMsg, MDSE_TP_CTX_TP.PRFT_MREV_MDSE_TP.toString());

        hdrBean.setTotFuncSvcRevTrnsfAmt(BigDecimal.ZERO);
        hdrBean.setTotFuncSvcCostTrnsfAmt(BigDecimal.ZERO);
        BigDecimal totFuncManFlAdjAmt = BigDecimal.ZERO;
        BigDecimal totFuncManRepRevAdjAmt = BigDecimal.ZERO;

        // SRT
        if (ZYPCommonFunc.hasValue(discAllocMethSRT)) {
            for (NWZC156001DtlBean dtlBean : dtlBeanList) {
                hdrBean.setTotFuncSvcRevTrnsfAmt(hdrBean.getTotFuncSvcRevTrnsfAmt() //
                        .add(getValue(dtlBean.getFuncSvcRevTrnsfAmt()).multiply(dtlBean.getOrdQty())));
            }
        }
        subTotAmt = BigDecimal.ZERO;
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            if (DISC_ALLOC_METH.SPECIFIC.equals(discAllocMethSRT)) {
                dtlBean.setFuncWtSvcRevTrnsfAmt(getValue(dtlBean.getFuncSvcRevTrnsfAmt()).multiply(dtlBean.getOrdQty()));
            } else if (DISC_ALLOC_METH.GENERIC.equals(discAllocMethSRT)) {
                dtlBean.setFuncWtSvcRevTrnsfAmt(getValue(hdrBean.getTotFuncSvcRevTrnsfAmt()) //
                        .multiply(dtlBean.getLineWtAmtRate()).setScale(AMT_SCALE, RoundingMode.HALF_UP));
                subTotAmt = subTotAmt.add(dtlBean.getFuncWtSvcRevTrnsfAmt());
            } else {
                dtlBean.setFuncWtSvcRevTrnsfAmt(BigDecimal.ZERO);
            }
        }
        if (DISC_ALLOC_METH.GENERIC.equals(discAllocMethSRT)) {
            dtlBeanList.get(adjIndex).setFuncWtSvcRevTrnsfAmt( //
                    dtlBeanList.get(adjIndex).getFuncWtSvcRevTrnsfAmt() //
                            .add(hdrBean.getTotFuncSvcRevTrnsfAmt().subtract(subTotAmt)));
        }

        // CT
        if (ZYPCommonFunc.hasValue(discAllocMethCT)) {
            for (NWZC156001DtlBean dtlBean : dtlBeanList) {
                hdrBean.setTotFuncSvcCostTrnsfAmt(hdrBean.getTotFuncSvcCostTrnsfAmt() //
                        .add(getValue(dtlBean.getFuncSvcCostTrnsfAmt()).multiply(dtlBean.getOrdQty())));
            }
        }
        subTotAmt = BigDecimal.ZERO;
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            if (DISC_ALLOC_METH.SPECIFIC.equals(discAllocMethCT)) {
                dtlBean.setFuncWtSvcCostTrnsfAmt(getValue(dtlBean.getFuncSvcCostTrnsfAmt()).multiply(dtlBean.getOrdQty()));
            } else if (DISC_ALLOC_METH.GENERIC.equals(discAllocMethCT)) {
                dtlBean.setFuncWtSvcCostTrnsfAmt(getValue(hdrBean.getTotFuncSvcCostTrnsfAmt()) //
                        .multiply(dtlBean.getLineWtAmtRate()).setScale(AMT_SCALE, RoundingMode.HALF_UP));
                subTotAmt = subTotAmt.add(dtlBean.getFuncWtSvcCostTrnsfAmt());
            } else {
                dtlBean.setFuncWtSvcCostTrnsfAmt(BigDecimal.ZERO);
            }
        }
        if (DISC_ALLOC_METH.GENERIC.equals(discAllocMethCT)) {
            dtlBeanList.get(adjIndex).setFuncWtSvcCostTrnsfAmt( //
                    dtlBeanList.get(adjIndex).getFuncWtSvcCostTrnsfAmt() //
                            .add(hdrBean.getTotFuncSvcCostTrnsfAmt().subtract(subTotAmt)));
        }

        // Man Floor
        if (ZYPCommonFunc.hasValue(discAllocMethManFl)) {
            for (NWZC156001DtlBean dtlBean : dtlBeanList) {
                totFuncManFlAdjAmt = totFuncManFlAdjAmt.add(getValue(dtlBean.getFuncManFlAdjAmt()).multiply(dtlBean.getOrdQty()));
            }
        }
        subTotAmt = BigDecimal.ZERO;
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            if (DISC_ALLOC_METH.SPECIFIC.equals(discAllocMethManFl)) {
                dtlBean.setFuncWtManFlAdjAmt(getValue(dtlBean.getFuncManFlAdjAmt()).multiply(dtlBean.getOrdQty()));
            } else if (DISC_ALLOC_METH.GENERIC.equals(discAllocMethManFl)) {
                dtlBean.setFuncWtManFlAdjAmt(totFuncManFlAdjAmt //
                        .multiply(dtlBean.getLineWtAmtRate()).setScale(AMT_SCALE, RoundingMode.HALF_UP));
                subTotAmt = subTotAmt.add(dtlBean.getFuncWtManFlAdjAmt());
            } else {
                dtlBean.setFuncWtManFlAdjAmt(BigDecimal.ZERO);
            }
        }
        if (DISC_ALLOC_METH.GENERIC.equals(discAllocMethManFl)) {
            dtlBeanList.get(adjIndex).setFuncWtManFlAdjAmt( //
                    dtlBeanList.get(adjIndex).getFuncWtManFlAdjAmt() //
                            .add(totFuncManFlAdjAmt.subtract(subTotAmt)));
        }

        // Man Rep Revenue
        if (ZYPCommonFunc.hasValue(discAllocMethManRep)) {
            for (NWZC156001DtlBean dtlBean : dtlBeanList) {
                totFuncManRepRevAdjAmt = totFuncManRepRevAdjAmt.add(getValue(dtlBean.getFuncManRepRevAdjAmt()).multiply(dtlBean.getOrdQty()));
            }
        }
        subTotAmt = BigDecimal.ZERO;
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            if (DISC_ALLOC_METH.SPECIFIC.equals(discAllocMethManRep)) {
                dtlBean.setFuncWtManRevAdjAmt(getValue(dtlBean.getFuncManRepRevAdjAmt()).multiply(dtlBean.getOrdQty()));
            } else if (DISC_ALLOC_METH.GENERIC.equals(discAllocMethManRep)) {
                dtlBean.setFuncWtManRevAdjAmt(totFuncManRepRevAdjAmt //
                        .multiply(dtlBean.getLineWtAmtRate()).setScale(AMT_SCALE, RoundingMode.HALF_UP));
                subTotAmt = subTotAmt.add(dtlBean.getFuncWtManRevAdjAmt());
            } else {
                dtlBean.setFuncWtManRevAdjAmt(BigDecimal.ZERO);
            }
        }
        if (DISC_ALLOC_METH.GENERIC.equals(discAllocMethManRep)) {
            dtlBeanList.get(adjIndex).setFuncWtManRevAdjAmt( //
                    dtlBeanList.get(adjIndex).getFuncWtManRevAdjAmt()
                    .add(totFuncManRepRevAdjAmt.subtract(subTotAmt)));
        }
        // QC#7707 End

        // 2016/03/10 S21_NA#2939
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
//            BigDecimal funcManFlAdjAmt = getValue(dtlBean.getFuncManFlAdjAmt()); // QC#7290
//            dtlBean.setFuncFlAdjAmt(dtlBean.getFuncInitFlPrcAmt()
//                    .subtract(dtlBean.getFuncGenlFlAdjAmt().add(dtlBean.getFuncSpecFlAdjAmt())));
            dtlBean.setFuncFlAdjAmt(dtlBean.getFuncSpecFlAdjAmt() //
                    .add(dtlBean.getFuncGenlFlAdjAmt()) //
                    .add(getValue(dtlBean.getFuncWtManFlAdjAmt())) //
                    .add(dtlBean.getFuncWtSvcCostTrnsfAmt())); // QC#7707
//                    .add(dtlBean.getFuncGenlFlAdjAmt().add(getValue(dtlBean.getFuncWtManFlAdjAmt())))); // QC#7707
//                    .add(dtlBean.getFuncGenlFlAdjAmt().add(funcManFlAdjAmt))); // QC#7290
            hdrBean.setTotFuncFlAdjAmt(//
                    hdrBean.getTotFuncFlAdjAmt().add(dtlBean.getFuncFlAdjAmt()));
        }

        // 2016/03/10 S21_NA#2939
//        hdrBean.setWkTotGenAdjFlAmt(wkGenAdjFlAmt);
//
//        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
//
//            BigDecimal calcGenAdjFlAmt //
//            = hdrBean.getWkTotGenAdjFlAmt() //
//                    .multiply(dtlBean.getLineWtAmtRate()) //
//                    .setScale(AMT_SCALE, RoundingMode.HALF_UP);
//            dtlBean.setCalcGenAdjFlAmt(calcGenAdjFlAmt);
//            wkGenAdjFlAmt = wkGenAdjFlAmt.subtract(calcGenAdjFlAmt);
//        }
//        dtlBeanList.get(0).setCalcGenAdjFlAmt(//
//                dtlBeanList.get(0).getCalcGenAdjFlAmt().add(wkGenAdjFlAmt));
//
//        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
//            dtlBean.setFuncFlAdjAmt(dtlBean.getCalcGenAdjFlAmt().add(dtlBean.getCalcSpecAdjFlAmt()));
//            hdrBean.setTotFuncFlAdjAmt(//
//                    hdrBean.getTotFuncFlAdjAmt() //
//                            .add(dtlBean.getFuncFlAdjAmt()));
//        }

        // calculate CSMP.
        hdrBean.setTotFuncCsmpCrAmt(BigDecimal.ZERO);
        hdrBean.setTotFuncCsmpFlAmt(BigDecimal.ZERO);
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            dtlBean.setFuncCsmpFlPrcAmt(dtlBean.getFuncInitFlPrcAmt() //
                    .subtract(getValue(dtlBean.getFuncCsmpUnitCrAmt()))); // QC#7791
//                    .subtract(getValue(dtlBean.getFuncCsmpCrAmt()))); // QC#7301
//                    .subtract(dtlBean.getFuncCsmpCrAmt())); // QC#7299
//            dtlBean.setFuncCsmpFlPrcAmt(dtlBean.getFuncUnitFlPrcAmt() //
//                    .multiply(dtlBean.getOrdCustUomQty()) // 2016/03/10 S21_NA#2939
//                        .subtract(dtlBean.getCsmpCrAmt())); // 2016/03/10 S21_NA#2939
//            hdrBean.setTotFuncCsmpCrAmt(hdrBean.getTotFuncCsmpCrAmt().add(dtlBean.getCsmpCrAmt())); // 2016/03/10 S21_NA#2939
//            hdrBean.setTotFuncCsmpCrAmt(hdrBean.getTotFuncCsmpCrAmt().add(dtlBean.getFuncCsmpCrAmt())); // 2016/03/10 S21_NA#2939
            hdrBean.setTotFuncCsmpCrAmt(hdrBean.getTotFuncCsmpCrAmt().add(getValue(dtlBean.getFuncCsmpCrAmt()))); // QC#7301
            hdrBean.setTotFuncCsmpFlAmt(hdrBean.getTotFuncCsmpFlAmt().add(dtlBean.getFuncCsmpFlPrcAmt()));
        }

        // calculate SRT, CT.
        // QC#7707 delete
//        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
//
//            if (dtlBean.getFuncSvcRevTrnsfAmt() == null //
//                    || dtlBean.getLineWtAmtRate() == null) {
//                dtlBean.setFuncWtSvcRevTrnsfAmt(BigDecimal.ZERO);
//            } else {
//                dtlBean.setFuncWtSvcRevTrnsfAmt(dtlBean.getFuncSvcRevTrnsfAmt().multiply(dtlBean.getLineWtAmtRate().setScale(AMT_SCALE, RoundingMode.HALF_UP)));
//            }
//            if (dtlBean.getFuncSvcCostTrnsfAmt() == null //
//                    || dtlBean.getLineWtAmtRate() == null) {
//                dtlBean.setFuncWtSvcCostTrnsfAmt(BigDecimal.ZERO);
//            } else {
//                dtlBean.setFuncWtSvcCostTrnsfAmt(dtlBean.getFuncSvcCostTrnsfAmt().multiply(dtlBean.getLineWtAmtRate().setScale(AMT_SCALE, RoundingMode.HALF_UP)));
//            }
//
//            if (dtlBean.getFuncSvcRevTrnsfAmt() != null) {
//                hdrBean.setTotFuncSvcRevTrnsfAmt(hdrBean.getTotFuncSvcRevTrnsfAmt().add(dtlBean.getFuncSvcRevTrnsfAmt()));
//            }
//            if (dtlBean.getFuncSvcCostTrnsfAmt() != null) {
//                hdrBean.setTotFuncSvcCostTrnsfAmt(hdrBean.getTotFuncSvcCostTrnsfAmt().add(dtlBean.getFuncSvcCostTrnsfAmt()));
//            }
//        }

        // calculate final floor.
        hdrBean.setTotFuncFinalFlAmt(BigDecimal.ZERO);
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
//            if (ZYPCommonFunc.hasValue(pMsg.csmpContrNum)) {// 2016/03/10 S21_NA#2939
//            if (ZYPCommonFunc.hasValue(dtlBean.getCsmpContrNum())) {// 2016/03/10 S21_NA#2939
//                dtlBean.setFinlRepFlAmt(dtlBean.getCsmpUnitNewFlAmt().add(dtlBean.getFuncFlAdjAmt().add(dtlBean.getFuncWtSvcCostTrnsfAmt())));// 2016/03/10 S21_NA#2939
//                dtlBean.setFuncFinalFlPrcAmt(dtlBean.getFuncCsmpFlPrcAmt() // 2016/03/10 S21_NA#2939
//                        .subtract(dtlBean.getFuncFlAdjAmt().subtract(dtlBean.getFuncWtSvcCostTrnsfAmt()))); // QC#7291
//                        .add(dtlBean.getFuncFlAdjAmt().add(dtlBean.getFuncWtSvcCostTrnsfAmt())));
//                hdrBean.setTotFuncFinalFlAmt(hdrBean.getTotFuncFinalFlAmt().add(dtlBean.getFinlRepFlAmt()));// 2016/03/10 S21_NA#2939
            dtlBean.setFuncFinalFlPrcAmt( // QC#7791
                    dtlBean.getFuncCsmpFlPrcAmt().multiply(dtlBean.getOrdQty()) // QC#7791
                            .add(dtlBean.getFuncFlAdjAmt())); // QC#7707
//                                .add(dtlBean.getFuncFlAdjAmt().add(dtlBean.getFuncWtSvcCostTrnsfAmt()))); // QC#7290
            hdrBean.setTotFuncFinalFlAmt(hdrBean.getTotFuncFinalFlAmt().add(dtlBean.getFuncFinalFlPrcAmt()));// 2016/03/10 S21_NA#2939
//            } else { // QC#10122 delete
////                dtlBean.setFinlRepFlAmt(dtlBean.getFuncUnitFlPrcAmt().add(dtlBean.getFuncFlAdjAmt().add(dtlBean.getFuncWtSvcCostTrnsfAmt())));// 2016/03/10 S21_NA#2939
////                dtlBean.setFuncFinalFlPrcAmt(dtlBean.getFuncUnitFlPrcAmt().multiply(dtlBean.getOrdCustUomQty()) //
////                dtlBean.setFuncFinalFlPrcAmt(dtlBean.getFuncInitFlPrcAmt().multiply(dtlBean.getOrdCustUomQty()) //
//                dtlBean.setFuncFinalFlPrcAmt(dtlBean.getFuncInitFlPrcAmt().multiply(dtlBean.getOrdQty()) // QC#7707
//                        .add(dtlBean.getFuncFlAdjAmt())); // QC#7707
////                        .subtract(dtlBean.getFuncFlAdjAmt().subtract(dtlBean.getFuncWtSvcCostTrnsfAmt()))); // QC#7291
////                        .add(dtlBean.getFuncFlAdjAmt().add(dtlBean.getFuncWtSvcCostTrnsfAmt())));
////                        .add(dtlBean.getFuncFlAdjAmt().add(dtlBean.getFuncWtSvcCostTrnsfAmt()))); // QC#7290
//                hdrBean.setTotFuncFinalFlAmt(hdrBean.getTotFuncFinalFlAmt().add(dtlBean.getFuncFinalFlPrcAmt())); // QC#7301
//            }
            // QC#7791
            if (dtlBean.getOrdQty().compareTo(BigDecimal.ZERO) > 0) {
                dtlBean.setFuncFinalUnitFlPrcAmt(dtlBean.getFuncFinalFlPrcAmt() //
                        .divide(dtlBean.getOrdQty(), AMT_SCALE, RoundingMode.DOWN));
            } else {
                dtlBean.setFuncFinalUnitFlPrcAmt(BigDecimal.ZERO);
            }
        }
//        if (!ZYPCommonFunc.hasValue(pMsg.csmpContrNum)) {
//            hdrBean.setTotFuncFinalFlAmt(//
//                    hdrBean.getTotFuncStdFlAmt() //
//                            .add(hdrBean.getTotFuncFlAdjAmt())); // QC#7707
////                                    .add(hdrBean.getTotFuncSvcCostTrnsfAmt())));
//        }

        // calculate rep revenue adjustment.
        hdrBean.setTotFuncRepRevAdjAmt(BigDecimal.ZERO);
        BigDecimal totFuncRepRevAdjAmt = BigDecimal.ZERO; // QC#7290
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            if (ZYPConstant.FLG_OFF_N.equals(dtlBean.getDiscMdseTpFlg()) //
                    || ZYPConstant.FLG_OFF_N.equals(dtlBean.getRedRepRevFlg())) {
                continue;
            }
            if (DISC_ALLOC_METH.GENERIC.equals(dtlBean.getDiscAllocMethCd())) {
//                dtlBean.setFuncGenlRepRevAdjAmt(//
//                        dtlBean.getFuncNetSellPrcAmt() //
//                                .multiply(dtlBean.getLineWtAmtRate()).setScale(AMT_SCALE, RoundingMode.HALF_UP));
                totFuncRepRevAdjAmt = totFuncRepRevAdjAmt.add(dtlBean.getFuncNetSellPrcAmt()); // QC#7290

            } else if (DISC_ALLOC_METH.SPECIFIC.equals(dtlBean.getDiscAllocMethCd())) {
                setSpecRepRevAdjAmtToRefLine(//
                        dtlBeanList //
                        , dtlBean.getFuncNetSellPrcAmt() //
                        , dtlBean.getTrxRefLineNum() //
                        , dtlBean.getTrxRefLineSubNum());
            }
        }

        // calculate funcGenlRepRevAdjAmt // QC#7290
        subTotAmt = BigDecimal.ZERO; // QC#7707
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            dtlBean.setFuncGenlRepRevAdjAmt(totFuncRepRevAdjAmt.multiply(dtlBean.getLineWtAmtRate()).setScale(AMT_SCALE, RoundingMode.HALF_UP));
            subTotAmt = subTotAmt.add(dtlBean.getFuncGenlRepRevAdjAmt()); // QC#7707
        }
        dtlBeanList.get(adjIndex).setFuncGenlRepRevAdjAmt( //
                dtlBeanList.get(adjIndex).getFuncGenlRepRevAdjAmt() //
                        .add(totFuncRepRevAdjAmt.subtract(subTotAmt))); // QC#7707

        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
//            BigDecimal funcManRepRevAdjAmt = getValue(dtlBean.getFuncManRepRevAdjAmt()); // QC#7286
            dtlBean.setFuncRepRevAdjAmt(//
                    dtlBean.getFuncGenlRepRevAdjAmt() //
                            .add(dtlBean.getFuncSpecRepRevAdjAmt()) //
                            .add(getValue(dtlBean.getFuncWtManRevAdjAmt())) // QC#7707
                            .add(dtlBean.getFuncWtSvcRevTrnsfAmt())); //
//                    dtlBean.getFuncGenlRepRevAdjAmt().add(dtlBean.getFuncSpecRepRevAdjAmt()).add(getValue(dtlBean.getFuncWtManRevAdjAmt()))); // QC#7707
//                    dtlBean.getFuncGenlRepRevAdjAmt().add(dtlBean.getFuncSpecRepRevAdjAmt()).add(funcManRepRevAdjAmt)); // QC#7286
//                    dtlBean.getFuncNetSellPrcAmt() //
//                            .subtract(dtlBean.getFuncGenlRepRevAdjAmt().add(dtlBean.getFuncSpecRepRevAdjAmt())));
            hdrBean.setTotFuncRepRevAdjAmt(hdrBean.getTotFuncRepRevAdjAmt().add(dtlBean.getFuncRepRevAdjAmt()));
        }

        // QC#7810 delete
//        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
//            //Initial Rep Revenue
//            if (ZYPConstant.FLG_ON_Y.equals(dtlBean.getRepRevCalcInclFlg())) {
//                dtlBean.setFuncInitRepRevAmt(dtlBean.getCpoDtlFuncSlsAmt());
//            } else {
//                dtlBean.setFuncInitRepRevAmt(BigDecimal.ZERO);
//            }
//        }

        // calculate final rep revenue.
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
//            dtlBean.setFinlRepFlAmt(dtlBean.getFuncInitRepRevAmt().add(dtlBean.getFuncRepRevAdjAmt().subtract(dtlBean.getFuncWtSvcRevTrnsfAmt())));// 2016/03/10 S21_NA#2939
//            dtlBean.setFuncFinalRepRevAmt(dtlBean.getFuncInitRepRevAmt().add(dtlBean.getFuncRepRevAdjAmt().subtract(dtlBean.getFuncWtSvcRevTrnsfAmt())));
//            dtlBean.setFuncFinalRepRevAmt(dtlBean.getFuncInitRepRevAmt().subtract(dtlBean.getFuncRepRevAdjAmt().subtract(dtlBean.getFuncWtSvcRevTrnsfAmt()))); // QC#7286
//            dtlBean.setFuncFinalRepRevAmt(dtlBean.getFuncInitRepRevAmt().add(dtlBean.getFuncRepRevAdjAmt().add(dtlBean.getFuncWtSvcRevTrnsfAmt()))); // QC#7290
            dtlBean.setFuncFinalRepRevAmt( //
                    dtlBean.getFuncInitRepRevAmt().multiply(dtlBean.getOrdQty()) //
                            .add(dtlBean.getFuncRepRevAdjAmt())); // QC#7707
//                            .add(dtlBean.getFuncRepRevAdjAmt().add(dtlBean.getFuncWtSvcRevTrnsfAmt()))); // QC#7791
            hdrBean.setTotFuncRepRevAmt(hdrBean.getTotFuncRepRevAmt().add(dtlBean.getFuncFinalRepRevAmt()));// 2016/03/10 S21_NA#2939

            // QC#7791
            if (dtlBean.getOrdQty().compareTo(BigDecimal.ZERO) > 0) {
                dtlBean.setFuncFinalUnitRevAmt(dtlBean.getFuncFinalRepRevAmt() //
                        .divide(dtlBean.getOrdQty(), AMT_SCALE, RoundingMode.DOWN));
            } else {
                dtlBean.setFuncFinalUnitRevAmt(BigDecimal.ZERO);
            }
        }
//        hdrBean.setTotFuncRepRevAmt(hdrBean.getTotFuncStdFlAmt().add(hdrBean.getTotFuncFlAdjAmt().add(hdrBean.getTotFuncSvcCostTrnsfAmt())));// 2016/03/10 S21_NA#2939

        // buyout
        BigDecimal totByotAmt = NWZC156001Query.getInstance().getTotByotAmt(pMsg);
        if (totByotAmt == null) {
            totByotAmt = BigDecimal.ZERO;
        }
        hdrBean.setTotFuncByotAmt(totByotAmt);

        // Total Professional Service Amount
        List<String> coaMdseTpList = NWZC156001Query.getInstance().getCoaMdseTpList(pMsg);
        BigDecimal totProSvcAmt = BigDecimal.ZERO;
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            totProSvcAmt = totProSvcAmt.add(getProSvcAmt(dtlBean, coaMdseTpList));
        }
        hdrBean.setTotFuncProSvcAmt(totProSvcAmt);

        // QC#7285 Add Start
        // MSRP/CSMP
        BigDecimal totMsrpAmt = BigDecimal.ZERO;
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            BigDecimal equipPrcAmt = NWZC156001Query.getInstance().getMSRPAmt(pMsg, dtlBean.getMdseCd());
            dtlBean.setFuncUnitMsrpAmt(equipPrcAmt);
            dtlBean.setFuncFinalStdCostAmt( //
//                    dtlBean.getFuncUnitStdCostAmt().multiply(dtlBean.getMdseInvtyCostPct()).multiply(dtlBean.getOrdCustUomQty()) //
//                    dtlBean.getFuncUnitStdCostAmt().multiply(dtlBean.getMdseInvtyCostPct()).multiply(dtlBean.getOrdQty()) // QC#7707
                    dtlBean.getFuncUnitStdCostAmt().multiply(dtlBean.getMdseInvtyCostPct()) // QC#7791
                            .divide(BIG_DECIMAL_100, AMT_SCALE, RoundingMode.HALF_UP));

            totMsrpAmt = totMsrpAmt.add(equipPrcAmt.multiply(dtlBean.getOrdQty()));
        }
        hdrBean.setTotFuncMsrpAmt(totMsrpAmt);
        // QC#7285 Add End

        // TPC
        hdrBean.setTotFuncDlrCrAmt(BigDecimal.ZERO);
        hdrBean.setTotFuncRedCompAmt(BigDecimal.ZERO);
        hdrBean.setAltGrsPrftPct(BigDecimal.ZERO.setScale(PCT_SCALE));
        hdrBean.setTotFuncDlrInvAmt(BigDecimal.ZERO);

        boolean isRetailOrder = NWZC156001Query.getInstance().isRetailOrder(pMsg) > 0;
        if (isRetailOrder) {

            for (NWZC156001DtlBean dtlBean : dtlBeanList) {
                if (ZYPConstant.FLG_ON_Y.equals(dtlBean.getDlrCrPrftInclFlg())) {
    //                dtlBean.setFuncDlrCrAmt(dtlBean.getCsmpCrAmt());// 2016/03/10 S21_NA#2939
//                    dtlBean.setFuncDlrCrAmt(getValue(dtlBean.getFuncCsmpCrAmt())); // QC#7301
//                    dtlBean.setFuncDlrCrAmt(getValue(dtlBean.getFuncCsmpUnitCrAmt())); // QC#7791
                    dtlBean.setFuncDlrCrAmt(getValue(dtlBean.getFuncNetUnitPrcAmt())); // QC#10311
                    hdrBean.setTotFuncDlrCrAmt(hdrBean.getTotFuncDlrCrAmt().add(dtlBean.getFuncDlrCrAmt()));
                } else {
                    dtlBean.setFuncDlrCrAmt(BigDecimal.ZERO);
                }

                // Mod Start 2017/10/10 QC#21664
//                if (ZYPConstant.FLG_ON_Y.equals(dtlBean.getRedCompAmtFlg())) {
                if (ZYPConstant.FLG_ON_Y.equals(dtlBean.getRedCompAmtFlg()) && checkReducation(dtlBean, pMsg)) { // 
    //                dtlBean.setFuncRedCompAmt(dtlBean.getFuncUnitListPrcAmt());// 2016/03/10 S21_NA#2939
//                    dtlBean.setFuncRedCompAmt(dtlBean.getFuncNetSellPrcAmt());// 2016/03/10 S21_NA#2939
//                    dtlBean.setFuncRedCompAmt(dtlBean.getFuncNetUnitPrcAmt()); // QC#7791
                    dtlBean.setFuncRedCompAmt(dtlBean.getFuncUnitListPrcAmt().multiply(dtlBean.getOrdQty()));
                    hdrBean.setTotFuncRedCompAmt(hdrBean.getTotFuncRedCompAmt().add(dtlBean.getFuncRedCompAmt()));
                } else {
                    dtlBean.setFuncRedCompAmt(BigDecimal.ZERO);
                }
                // Mod End 2017/10/10 QC#21664
            }
            hdrBean.setFuncAltGrsPrftAmt(hdrBean.getTotFuncDlrCrAmt().subtract(hdrBean.getTotFuncRedCompAmt()));
    
            // 2016/03/10 S21_NA#2939
            for (NWZC156001DtlBean dtlBean : dtlBeanList) {
                hdrBean.setTotFuncDlrInvAmt(hdrBean.getTotFuncDlrInvAmt().add(dtlBean.getFuncNetSellPrcAmt()));
            }
    
            if (BigDecimal.ZERO.compareTo(hdrBean.getTotFuncDlrInvAmt()) != 0) {
                // 2018/03/08 S21_NA#22614 Add Start
                if (BigDecimal.ZERO.compareTo(hdrBean.getFuncNegoDealAmt()) != 0){
                // 2018/03/08 S21_NA#22614 Add End
                    hdrBean.setAltGrsPrftPct(//
                            hdrBean.getFuncAltGrsPrftAmt() //
                                    .multiply(BIG_DECIMAL_100) // QC#7296
                                    .divide(hdrBean.getFuncNegoDealAmt(), PCT_SCALE, RoundingMode.HALF_UP)); // QC#21664 Mod 2017/10/10
    //                                .divide(hdrBean.getTotFuncDlrInvAmt(), PCT_SCALE, RoundingMode.HALF_UP)); // QC#7296
        //                            .divide(hdrBean.getTotFuncDlrInvAmt()) // 2016/03/10 S21_NA#2939
    //                                .divide(hdrBean.getTotFuncDlrInvAmt(), PCT_SCALE, RoundingMode.HALF_UP) // 2016/03/10 S21_NA#2939
        //                            .multiply(BIG_DECIMAL_100).setScale(PCT_SCALE, RoundingMode.HALF_UP));// 2016/03/10 S21_NA#2939
    //                                .multiply(BIG_DECIMAL_100));// 2016/03/10 S21_NA#2939
               // 2018/03/08 S21_NA#22614 Add Start
                } else {
                    hdrBean.setAltGrsPrftPct(BigDecimal.ZERO.setScale(PCT_SCALE));
                }
                // 2018/03/08 S21_NA#22614 Add End
            }
        }

        // GP
        // Mod Start 2017/10/10 QC#21664
        if (!checkOrdProcNodePrflCd(pMsg)) {
            hdrBean.setFuncGrsPrftAmt(hdrBean.getTotFuncRepRevAmt().subtract(hdrBean.getTotFuncFinalFlAmt()));
            if (BigDecimal.ZERO.compareTo(hdrBean.getTotFuncRepRevAmt()) != 0) {
                hdrBean.setGrsPrftPct(hdrBean.getFuncGrsPrftAmt()//
                        .multiply(BIG_DECIMAL_100) // QC#7296
                        .divide(hdrBean.getTotFuncRepRevAmt(), PCT_SCALE, RoundingMode.HALF_UP)); // QC#7296
//                        .divide(hdrBean.getTotFuncRepRevAmt(), PCT_SCALE, RoundingMode.HALF_UP)//
//                        .multiply(BIG_DECIMAL_100));// 2016/03/10 S21_NA#2939
            } else {
                hdrBean.setGrsPrftPct(BigDecimal.ZERO.setScale(PCT_SCALE));
            }
        } else {
            hdrBean.setFuncGrsPrftAmt(BigDecimal.ZERO.setScale(PCT_SCALE));
            hdrBean.setGrsPrftPct(BigDecimal.ZERO.setScale(PCT_SCALE));
        }
        // Mod End 2017/10/10 QC#21664

        // set return value and update DB.
        setReturnValue(pMsg, pMsg2List, hdrBean, dtlBeanList);
        if (MODE_UPDATE.equals(pMsg.xxModeCd.getValue())) {
            updateOrdPrft(pMsg, pMsg2List, hdrBean, dtlBeanList);
        }
    }

    // QC#7810 modify start
//    private BigDecimal getSvcAmt(NWZC156001PMsg pMsg) {
//        BigDecimal svcAmt = BigDecimal.ZERO;
//        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
//            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
//            Integer cnt = NWZC156001Query.getInstance().getSvcCnt(pMsg, scrPMsg);
//            if (cnt == 0) {
//                continue;
//            }
//            svcAmt = svcAmt.add(scrPMsg.funcNetSellPrcAmt.getValue());
//        }
//        return svcAmt;
//    }
    private BigDecimal getSvcAmt(NWZC156001PMsg pMsg, List<NWZC156001DtlBean> dtlBeanList) {
        BigDecimal svcAmt = BigDecimal.ZERO;
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            Integer cnt = NWZC156001Query.getInstance().getSvcCnt(pMsg, dtlBean.getMdseCd());
            if (cnt != 0) {
                svcAmt = svcAmt.add(dtlBean.getFuncNetSellPrcAmt());
            }
        }
        return svcAmt;
    }
    // QC#7810 end

    private BigDecimal getProSvcAmt(NWZC156001DtlBean dtlBean, List<String> coaMdseTpList) {
        if (coaMdseTpList.contains(dtlBean.getCoaMdseTpCd())) {
            return dtlBean.getFuncNetSellPrcAmt();
        }
        return BigDecimal.ZERO;
    }

    private void setSpecRepRevAdjAmtToRefLine(//
            List<NWZC156001DtlBean> dtlBeanList //
            , BigDecimal funcNetSellPrcAmt //
            , String trxRefLineNum //
            , String trxRefLineSubNum) {
        for (NWZC156001DtlBean bean : dtlBeanList) {
            if (trxRefLineNum.equals(bean.getTrxLineNum())) { // QC#9635
//            if (trxRefLineNum.equals(bean.getTrxLineNum()) // 2016/03/10 S21_NA#2939
//                    && trxRefLineSubNum.equals(bean.getTrxLineSubNum())) {
//            if (trxRefLineNum.equals(bean.getTrxRefLineNum()) //
//                    && trxRefLineSubNum.equals(bean.getTrxRefLineSubNum())) {
//                bean.setFuncSpecRepRevAdjAmt(funcNetSellPrcAmt);
                bean.setFuncSpecRepRevAdjAmt(bean.getFuncSpecRepRevAdjAmt().add(funcNetSellPrcAmt));
                return;
            }
        }
        return;
    }

    private void setSpecFlAdjAmtToRefLine(//
            List<NWZC156001DtlBean> dtlBeanList //
            , BigDecimal funcInitFlPrcAmt //
            , String trxRefLineNum //
            , String trxRefLineSubNum) {
        for (NWZC156001DtlBean bean : dtlBeanList) {
            if (trxRefLineNum.equals(bean.getTrxLineNum())) { // QC#9635
//            if (trxRefLineNum.equals(bean.getTrxLineNum()) // 2016/03/10 S21_NA#2939
//                    && trxRefLineSubNum.equals(bean.getTrxLineSubNum())) {
//            if (trxRefLineNum.equals(bean.getTrxRefLineNum()) //
//                    && trxRefLineSubNum.equals(bean.getTrxRefLineSubNum())) {
//                bean.setFuncSpecFlAdjAmt(funcInitFlPrcAmt);
                bean.setFuncSpecFlAdjAmt(bean.getFuncSpecFlAdjAmt().add(funcInitFlPrcAmt));
                return;
            }
        }
        return;
    }

    private void setReturnValue(NWZC156001PMsg pMsg, List<NWZC156002PMsg> pMsg2List, NWZC156001HdrBean hdrBean, List<NWZC156001DtlBean> dtlBeanList) {
        setHdrPMsg(pMsg, hdrBean);

        int i = 0;
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            setDtlPMsg(pMsg.svcConfigRef.no(i++), dtlBean);
            // 2016/03/25 S21_NA#6083 Delete
//            if (i >= pMsg.svcConfigRef.getValidCount()) {
//                break;
//            }
        }
        pMsg.svcConfigRef.setValidCount(i);
    }

    private void setDtlPMsg(NWZC156001_svcConfigRefPMsg scrPsg, NWZC156001DtlBean dtlBean) {
        ZYPEZDItemValueSetter.setValue(scrPsg.ordPrftTrxCatgCd, dtlBean.getOrdPrftTrxCatgCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.trxLineNum, dtlBean.getTrxLineNum());
        ZYPEZDItemValueSetter.setValue(scrPsg.trxLineSubNum, dtlBean.getTrxLineSubNum());
        ZYPEZDItemValueSetter.setValue(scrPsg.dsOrdPosnNum, dtlBean.getDsOrdPosnNum());
        ZYPEZDItemValueSetter.setValue(scrPsg.dsCpoLineNum, dtlBean.getDsCpoLineNum());
        ZYPEZDItemValueSetter.setValue(scrPsg.dsCpoLineSubNum, dtlBean.getDsCpoLineSubNum());
        ZYPEZDItemValueSetter.setValue(scrPsg.mdseCd, dtlBean.getMdseCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.mdseNm, dtlBean.getMdseNm());
        ZYPEZDItemValueSetter.setValue(scrPsg.mdseDescShortTxt, dtlBean.getMdseDescShortTxt());// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(scrPsg.rtlWhCd, dtlBean.getRtlWhCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.rtlSwhCd, dtlBean.getRtlSwhCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.mdseInvtyCostPct, dtlBean.getMdseInvtyCostPct());
        ZYPEZDItemValueSetter.setValue(scrPsg.dsOrdLineCatgCd, dtlBean.getDsOrdLineCatgCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.ordQty, dtlBean.getOrdQty());
        ZYPEZDItemValueSetter.setValue(scrPsg.custUomCd, dtlBean.getCustUomCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.flPrcListCd, dtlBean.getFlPrcListCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.flPrcListNm, dtlBean.getFlPrcListNm());// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(scrPsg.csmpPrcListCd, dtlBean.getCsmpPrcListCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.prcCatgNm, dtlBean.getCsmpPrcListNm());// 2016/03/10 S21_NA#2939
        ZYPEZDItemValueSetter.setValue(scrPsg.prcCatgCd, dtlBean.getPrcCatgCd()); // QC#7707
        ZYPEZDItemValueSetter.setValue(scrPsg.prcCatgNm_P2, dtlBean.getPrcCatgNm()); // QC#7707
        ZYPEZDItemValueSetter.setValue(scrPsg.funcDlrCrAmt, dtlBean.getFuncDlrCrAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcRedCompAmt, dtlBean.getFuncRedCompAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcUnitFlPrcAmt, dtlBean.getFuncUnitFlPrcAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcInitFlPrcAmt, dtlBean.getFuncInitFlPrcAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.lineWtAmtRate, dtlBean.getLineWtAmtRate());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcGenlFlAdjAmt, dtlBean.getFuncGenlFlAdjAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcSpecFlAdjAmt, dtlBean.getFuncSpecFlAdjAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcManFlAdjAmt, dtlBean.getFuncManFlAdjAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcFlAdjAmt, dtlBean.getFuncFlAdjAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcCsmpUnitCrAmt, dtlBean.getFuncCsmpUnitCrAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcCsmpCrAmt, dtlBean.getFuncCsmpCrAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcCsmpFlPrcAmt, dtlBean.getFuncCsmpFlPrcAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcSvcCostTrnsfAmt, dtlBean.getFuncSvcCostTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcWtSvcCostTrnsfAmt, dtlBean.getFuncWtSvcCostTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcFinalFlPrcAmt, dtlBean.getFuncFinalFlPrcAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcUnitListPrcAmt, dtlBean.getFuncUnitListPrcAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcNetUnitPrcAmt, dtlBean.getFuncNetUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcNetSellPrcAmt, dtlBean.getFuncNetSellPrcAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcInitRepRevAmt, dtlBean.getFuncInitRepRevAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcGenlRepRevAdjAmt, dtlBean.getFuncGenlRepRevAdjAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcSpecRepRevAdjAmt, dtlBean.getFuncSpecRepRevAdjAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcManRepRevAdjAmt, dtlBean.getFuncManRepRevAdjAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcRepRevAdjAmt, dtlBean.getFuncRepRevAdjAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcSvcRevTrnsfAmt, dtlBean.getFuncSvcRevTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcWtSvcRevTrnsfAmt, dtlBean.getFuncWtSvcRevTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcFinalRepRevAmt, dtlBean.getFuncFinalRepRevAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcUnitMsrpAmt, dtlBean.getFuncUnitMsrpAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcUnitStdCostAmt, dtlBean.getFuncUnitStdCostAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcFinalStdCostAmt, dtlBean.getFuncFinalStdCostAmt());
        ZYPEZDItemValueSetter.setValue(scrPsg.funcWtManFlAdjAmt, dtlBean.getFuncWtManFlAdjAmt()); // QC#7707
        ZYPEZDItemValueSetter.setValue(scrPsg.funcWtManRevAdjAmt, dtlBean.getFuncWtManRevAdjAmt()); // QC#7707
        ZYPEZDItemValueSetter.setValue(scrPsg.funcFinalUnitFlPrcAmt, dtlBean.getFuncFinalUnitFlPrcAmt()); // QC#7707
        ZYPEZDItemValueSetter.setValue(scrPsg.funcFinalUnitRevAmt, dtlBean.getFuncFinalUnitRevAmt()); // QC#7707

        ZYPEZDItemValueSetter.setValue(scrPsg.csmpContrNum, dtlBean.getCsmpContrNum());
        ZYPEZDItemValueSetter.setValue(scrPsg.dlrRefNum, dtlBean.getDlrRefNum());
        ZYPEZDItemValueSetter.setValue(scrPsg.chngOrdFlg, dtlBean.getChngOrdFlg());
        ZYPEZDItemValueSetter.setValue(scrPsg.coaMdseTpCd, dtlBean.getCoaMdseTpCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.coaProdCd, dtlBean.getCoaProdCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.mdseItemTpCd, dtlBean.getMdseItemTpCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.lastPrftCalcUsrId, userId);
        ZYPEZDItemValueSetter.setValue(scrPsg.lastPrftCalcTs, currentSystemTime);
        ZYPEZDItemValueSetter.setValue(scrPsg.ordPrftRuleTpCd, dtlBean.getOrdPrftRuleTpCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.flPrcCalcInclFlg, dtlBean.getFlPrcCalcInclFlg());
        ZYPEZDItemValueSetter.setValue(scrPsg.repRevCalcInclFlg, dtlBean.getRepRevCalcInclFlg());
        ZYPEZDItemValueSetter.setValue(scrPsg.discMdseTpFlg, dtlBean.getDiscMdseTpFlg());
        ZYPEZDItemValueSetter.setValue(scrPsg.redFlPrcFlg, dtlBean.getRedFlPrcFlg());
        ZYPEZDItemValueSetter.setValue(scrPsg.redRepRevFlg, dtlBean.getRedRepRevFlg());
        ZYPEZDItemValueSetter.setValue(scrPsg.discAllocMethCd, dtlBean.getDiscAllocMethCd());
        ZYPEZDItemValueSetter.setValue(scrPsg.dlrCrPrftInclFlg, dtlBean.getDlrCrPrftInclFlg());
        ZYPEZDItemValueSetter.setValue(scrPsg.redCompAmtFlg, dtlBean.getRedCompAmtFlg());

        return;
    }

    private void setHdrPMsg(NWZC156001PMsg pMsg, NWZC156001HdrBean hdrBean) {
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        //        ZYPEZDItemValueSetter.setValue(pMsg.ordPrftPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRFT_SQ));
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, hdrBean.getTrxHdrNum());
        ZYPEZDItemValueSetter.setValue(pMsg.ordPrftVrsnNum, hdrBean.getOrdPrftVrsnNum());
        ZYPEZDItemValueSetter.setValue(pMsg.funcNegoDealAmt, hdrBean.getFuncNegoDealAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncRepRevAmt, hdrBean.getTotFuncRepRevAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncRepRevAdjAmt, hdrBean.getTotFuncRepRevAdjAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncFinalFlAmt, hdrBean.getTotFuncFinalFlAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.funcGrsPrftAmt, hdrBean.getFuncGrsPrftAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.grsPrftPct, getMaxPctValue(hdrBean.getGrsPrftPct()));
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncMsrpAmt, hdrBean.getTotFuncMsrpAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncStdFlAmt, hdrBean.getTotFuncStdFlAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncFlAdjAmt, hdrBean.getTotFuncFlAdjAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.csmpOrdFlg, hdrBean.getCsmpOrdFlg());
        ZYPEZDItemValueSetter.setValue(pMsg.csmpContrNum, hdrBean.getCsmpContrNum());
        ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, hdrBean.getDlrRefNum());
        ZYPEZDItemValueSetter.setValue(pMsg.csmpContrStartDt, hdrBean.getCsmpContrStartDt());
        ZYPEZDItemValueSetter.setValue(pMsg.csmpContrEndDt, hdrBean.getCsmpContrEndDt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncCsmpCrAmt, hdrBean.getTotFuncCsmpCrAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncCsmpFlAmt, hdrBean.getTotFuncCsmpFlAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.lastPrftCalcUsrId, userId);
        ZYPEZDItemValueSetter.setValue(pMsg.lastPrftCalcTs, currentSystemTime);
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncByotAmt, hdrBean.getTotFuncByotAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncSvcRevTrnsfAmt, hdrBean.getTotFuncSvcRevTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncSvcCostTrnsfAmt, hdrBean.getTotFuncSvcCostTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncProSvcAmt, hdrBean.getTotFuncProSvcAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncOmMaintBllblAmt, hdrBean.getTotFuncOmMaintBllblAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.funcAltGrsPrftAmt, hdrBean.getFuncAltGrsPrftAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.altGrsPrftPct, getMaxPctValue(hdrBean.getAltGrsPrftPct()));
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncDlrCrAmt, hdrBean.getTotFuncDlrCrAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncDlrInvAmt, hdrBean.getTotFuncDlrInvAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.totFuncRedCompAmt, hdrBean.getTotFuncRedCompAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.xxOrdPrftFndrFeeAmt, hdrBean.getXxFndrFeeAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.xxOrdPrftIstlCrAmt, hdrBean.getXxIstlCrAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.xxOrdPrftSplyAmt, hdrBean.getXxSplyAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.xxOrdPrftSvcAmt, hdrBean.getTotFuncOmMaintBllblAmt());

        return;
    }

    private void updateOrdPrft(NWZC156001PMsg pMsg, List<NWZC156002PMsg> pMsg2List, NWZC156001HdrBean hdrBean, List<NWZC156001DtlBean> dtlBeanList) {
        ORD_PRFTTMsg ordPrftTMsg = editOrdPrftTMsg(pMsg, hdrBean);
        S21FastTBLAccessor.insert(ordPrftTMsg);

        List<ORD_PRFT_DTLTMsg> ordPrftDtlTMsgList = editOrdPrftDtlTMsg(pMsg, pMsg2List, ordPrftTMsg, hdrBean, dtlBeanList);
        int cnt = S21FastTBLAccessor.insert(ordPrftDtlTMsgList.toArray(new ORD_PRFT_DTLTMsg[0]));
        if (cnt != ordPrftDtlTMsgList.size()) {
            NWZC156002PMsg pMsg2 = new NWZC156002PMsg();
            setMsgId2(pMsg2, NWZM1522E);
            pMsg2List.add(pMsg2);
        }

    }

    private List<ORD_PRFT_DTLTMsg> editOrdPrftDtlTMsg(NWZC156001PMsg pMsg, List<NWZC156002PMsg> pMsg2List, ORD_PRFTTMsg ordPrftTMsg, NWZC156001HdrBean hdrBean, List<NWZC156001DtlBean> dtlBeanList) {
        List<ORD_PRFT_DTLTMsg> ordPrftDtlTMsgList = new ArrayList<ORD_PRFT_DTLTMsg>();
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            ORD_PRFT_DTLTMsg tMsg = new ORD_PRFT_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRFT_DTL_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftPk, ordPrftTMsg.ordPrftPk);
            ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, ordPrftTMsg.trxHdrNum);
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftVrsnNum, ordPrftTMsg.ordPrftVrsnNum);

            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftTrxCatgCd, dtlBean.getOrdPrftTrxCatgCd());
            ZYPEZDItemValueSetter.setValue(tMsg.trxLineNum, dtlBean.getTrxLineNum());
            ZYPEZDItemValueSetter.setValue(tMsg.trxLineSubNum, dtlBean.getTrxLineSubNum());
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdPosnNum, dtlBean.getDsOrdPosnNum());
            ZYPEZDItemValueSetter.setValue(tMsg.dsCpoLineNum, dtlBean.getDsCpoLineNum());
            ZYPEZDItemValueSetter.setValue(tMsg.dsCpoLineSubNum, dtlBean.getDsCpoLineSubNum());
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, dtlBean.getMdseCd());
            ZYPEZDItemValueSetter.setValue(tMsg.mdseNm, dtlBean.getMdseNm());
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, dtlBean.getRtlWhCd());
            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, dtlBean.getRtlSwhCd());
            ZYPEZDItemValueSetter.setValue(tMsg.mdseInvtyCostPct, dtlBean.getMdseInvtyCostPct());
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdLineCatgCd, dtlBean.getDsOrdLineCatgCd());
            ZYPEZDItemValueSetter.setValue(tMsg.ordQty, dtlBean.getOrdQty());
            ZYPEZDItemValueSetter.setValue(tMsg.ordCustUomQty, dtlBean.getOrdCustUomQty());// 2016/03/25 S21_NA#5491
            ZYPEZDItemValueSetter.setValue(tMsg.custUomCd, dtlBean.getCustUomCd());
            ZYPEZDItemValueSetter.setValue(tMsg.flPrcListCd, dtlBean.getFlPrcListCd());
            ZYPEZDItemValueSetter.setValue(tMsg.csmpPrcListCd, dtlBean.getCsmpPrcListCd());
            ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, dtlBean.getPrcCatgCd()); // QC#7707
            ZYPEZDItemValueSetter.setValue(tMsg.funcDlrCrAmt, dtlBean.getFuncDlrCrAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcRedCompAmt, dtlBean.getFuncRedCompAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcUnitFlPrcAmt, dtlBean.getFuncUnitFlPrcAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcInitFlPrcAmt, dtlBean.getFuncInitFlPrcAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.lineWtAmtRate, dtlBean.getLineWtAmtRate());
            ZYPEZDItemValueSetter.setValue(tMsg.funcGenlFlAdjAmt, dtlBean.getFuncGenlFlAdjAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcSpecFlAdjAmt, dtlBean.getFuncSpecFlAdjAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcManFlAdjAmt, dtlBean.getFuncManFlAdjAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcFlAdjAmt, dtlBean.getFuncFlAdjAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcCsmpUnitCrAmt, dtlBean.getFuncCsmpUnitCrAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcCsmpCrAmt, dtlBean.getFuncCsmpCrAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcCsmpFlPrcAmt, dtlBean.getFuncCsmpFlPrcAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcSvcCostTrnsfAmt, dtlBean.getFuncSvcCostTrnsfAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcWtSvcCostTrnsfAmt, dtlBean.getFuncWtSvcCostTrnsfAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcFinalFlPrcAmt, dtlBean.getFuncFinalFlPrcAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcUnitListPrcAmt, dtlBean.getFuncUnitListPrcAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcNetUnitPrcAmt, dtlBean.getFuncNetUnitPrcAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcNetSellPrcAmt, dtlBean.getFuncNetSellPrcAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcInitRepRevAmt, dtlBean.getFuncInitRepRevAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcGenlRepRevAdjAmt, dtlBean.getFuncGenlRepRevAdjAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcSpecRepRevAdjAmt, dtlBean.getFuncSpecRepRevAdjAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcManRepRevAdjAmt, dtlBean.getFuncManRepRevAdjAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcRepRevAdjAmt, dtlBean.getFuncRepRevAdjAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcSvcRevTrnsfAmt, dtlBean.getFuncSvcRevTrnsfAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcWtSvcRevTrnsfAmt, dtlBean.getFuncWtSvcRevTrnsfAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcFinalRepRevAmt, dtlBean.getFuncFinalRepRevAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcUnitMsrpAmt, dtlBean.getFuncUnitMsrpAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcUnitStdCostAmt, dtlBean.getFuncUnitStdCostAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcFinalStdCostAmt, dtlBean.getFuncFinalStdCostAmt());
            ZYPEZDItemValueSetter.setValue(tMsg.funcWtManFlAdjAmt, dtlBean.getFuncWtManFlAdjAmt()); // QC#7707
            ZYPEZDItemValueSetter.setValue(tMsg.funcWtManRevAdjAmt, dtlBean.getFuncWtManRevAdjAmt()); // QC#7707
            ZYPEZDItemValueSetter.setValue(tMsg.funcFinalUnitFlPrcAmt, dtlBean.getFuncFinalUnitFlPrcAmt()); // QC#7707
            ZYPEZDItemValueSetter.setValue(tMsg.funcFinalUnitRevAmt, dtlBean.getFuncFinalUnitRevAmt()); // QC#7707

            ZYPEZDItemValueSetter.setValue(tMsg.csmpContrNum, dtlBean.getCsmpContrNum());
            ZYPEZDItemValueSetter.setValue(tMsg.dlrRefNum, dtlBean.getDlrRefNum());
            ZYPEZDItemValueSetter.setValue(tMsg.csmpContrStartDt, hdrBean.getCsmpContrStartDt());
            ZYPEZDItemValueSetter.setValue(tMsg.csmpContrEndDt, hdrBean.getCsmpContrEndDt());
            ZYPEZDItemValueSetter.setValue(tMsg.chngOrdFlg, dtlBean.getChngOrdFlg());
            ZYPEZDItemValueSetter.setValue(tMsg.coaMdseTpCd, dtlBean.getCoaMdseTpCd());
            ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, dtlBean.getCoaProdCd());
            ZYPEZDItemValueSetter.setValue(tMsg.mdseItemTpCd, dtlBean.getMdseItemTpCd());
            ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcUsrId, userId);
            ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcTs, currentSystemTime);
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftRuleTpCd, dtlBean.getOrdPrftRuleTpCd());
            ZYPEZDItemValueSetter.setValue(tMsg.flPrcCalcInclFlg, dtlBean.getFlPrcCalcInclFlg());
            ZYPEZDItemValueSetter.setValue(tMsg.repRevCalcInclFlg, dtlBean.getRepRevCalcInclFlg());
            ZYPEZDItemValueSetter.setValue(tMsg.discMdseTpFlg, dtlBean.getDiscMdseTpFlg());
            ZYPEZDItemValueSetter.setValue(tMsg.redFlPrcFlg, dtlBean.getRedFlPrcFlg());
            ZYPEZDItemValueSetter.setValue(tMsg.redRepRevFlg, dtlBean.getRedRepRevFlg());
            ZYPEZDItemValueSetter.setValue(tMsg.discAllocMethCd, dtlBean.getDiscAllocMethCd());
            ZYPEZDItemValueSetter.setValue(tMsg.dlrCrPrftInclFlg, dtlBean.getDlrCrPrftInclFlg());
            ZYPEZDItemValueSetter.setValue(tMsg.redCompAmtFlg, dtlBean.getRedCompAmtFlg());

            // FLG 2016/03/23 S21_NA#3315
            final Set<EZDTStringItem> flgItemList = new HashSet<EZDTStringItem>();
            flgItemList.add(tMsg.chngOrdFlg);
            flgItemList.add(tMsg.discMdseTpFlg);
            flgItemList.add(tMsg.dlrCrPrftInclFlg);
            flgItemList.add(tMsg.flPrcCalcInclFlg);
            flgItemList.add(tMsg.redCompAmtFlg);
            flgItemList.add(tMsg.redFlPrcFlg);
            flgItemList.add(tMsg.redRepRevFlg);
            flgItemList.add(tMsg.repRevCalcInclFlg);

            for (EZDTStringItem flgItem : flgItemList) {
                if (!ZYPCommonFunc.hasValue(flgItem)) {
                    ZYPEZDItemValueSetter.setValue(flgItem, ZYPConstant.FLG_OFF_N);
                }
            }

            ordPrftDtlTMsgList.add(tMsg);
        }
        return ordPrftDtlTMsgList;
    }

    private ORD_PRFTTMsg editOrdPrftTMsg(NWZC156001PMsg pMsg, NWZC156001HdrBean hdrBean) {
        ORD_PRFTTMsg tMsg = new ORD_PRFTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordPrftPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRFT_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, hdrBean.getTrxHdrNum());
        ZYPEZDItemValueSetter.setValue(tMsg.ordPrftVrsnNum, hdrBean.getOrdPrftVrsnNum());
        ZYPEZDItemValueSetter.setValue(tMsg.funcNegoDealAmt, hdrBean.getFuncNegoDealAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncRepRevAmt, hdrBean.getTotFuncRepRevAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncRepRevAdjAmt, hdrBean.getTotFuncRepRevAdjAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncFinalFlAmt, hdrBean.getTotFuncFinalFlAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.funcGrsPrftAmt, hdrBean.getFuncGrsPrftAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.grsPrftPct, getMaxPctValue(hdrBean.getGrsPrftPct()));
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncMsrpAmt, hdrBean.getTotFuncMsrpAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncStdFlAmt, hdrBean.getTotFuncStdFlAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncFlAdjAmt, hdrBean.getTotFuncFlAdjAmt());

        if (ZYPCommonFunc.hasValue(hdrBean.getCsmpOrdFlg())) {
            ZYPEZDItemValueSetter.setValue(tMsg.csmpOrdFlg, hdrBean.getCsmpOrdFlg());
//        } else if (ZYPCommonFunc.hasValue(hdrBean.getCsmpContrNum())) {
        } else if (ZYPCommonFunc.hasValue(hdrBean.getCsmpContrNum()) || ZYPCommonFunc.hasValue(hdrBean.getDlrRefNum())) { // QC#10121
            ZYPEZDItemValueSetter.setValue(tMsg.csmpOrdFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.csmpOrdFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.csmpContrNum, hdrBean.getCsmpContrNum());
        ZYPEZDItemValueSetter.setValue(tMsg.dlrRefNum, hdrBean.getDlrRefNum());
        ZYPEZDItemValueSetter.setValue(tMsg.csmpContrStartDt, hdrBean.getCsmpContrStartDt());
        ZYPEZDItemValueSetter.setValue(tMsg.csmpContrEndDt, hdrBean.getCsmpContrEndDt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncCsmpCrAmt, hdrBean.getTotFuncCsmpCrAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncCsmpFlAmt, hdrBean.getTotFuncCsmpFlAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcUsrId, userId);
        ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcTs, currentSystemTime);
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncByotAmt, hdrBean.getTotFuncByotAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncSvcRevTrnsfAmt, hdrBean.getTotFuncSvcRevTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncSvcCostTrnsfAmt, hdrBean.getTotFuncSvcCostTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncProSvcAmt, hdrBean.getTotFuncProSvcAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncOmMaintBllblAmt, hdrBean.getTotFuncOmMaintBllblAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.funcAltGrsPrftAmt, hdrBean.getFuncAltGrsPrftAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.altGrsPrftPct, getMaxPctValue(hdrBean.getAltGrsPrftPct()));
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncDlrCrAmt, hdrBean.getTotFuncDlrCrAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncDlrInvAmt, hdrBean.getTotFuncDlrInvAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncRedCompAmt, hdrBean.getTotFuncRedCompAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncFndrFeeAmt, hdrBean.getXxFndrFeeAmt());// 2016/04/03 S21_NA#6298
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncIstlCrAmt, hdrBean.getXxIstlCrAmt());// 2016/04/03 S21_NA#6298
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncSplyAmt, hdrBean.getXxSplyAmt());// 2016/04/03 S21_NA#6298

        return tMsg;
    }

    /**
     * @param dtlBean
     * @return culcFuncInitFlPrcAmt
     */
    private BigDecimal culcFuncInitFlPrcAmt(NWZC156001DtlBean dtlBean) {
        if (dtlBean.getFuncUnitFlPrcAmt() == null //
                || dtlBean.getOrdQty() == null) { // 2016/03/10 S21_NA#2939 // QC#7707
//                || dtlBean.getOrdCustUomQty() == null) { // 2016/03/10 S21_NA#2939
            return BigDecimal.ZERO;
        }
//        return dtlBean.getFuncUnitFlPrcAmt().multiply(dtlBean.getOrdQty()); // 2016/03/10 S21_NA#2939 // QC#7707
//        return dtlBean.getFuncUnitFlPrcAmt().multiply(dtlBean.getOrdCustUomQty()); // 2016/03/10 S21_NA#2939
        return dtlBean.getFuncUnitFlPrcAmt(); // QC#7791
    }

    private BigDecimal culcFuncUnitFlPrcAmt(NWZC156001DtlBean dtlBean) {
        if (dtlBean.getFuncUnitFlPrcAmt() == null //
                || dtlBean.getMdseInvtyCostPct() == null) {
            return BigDecimal.ZERO;
        }
        // Mod Start 2017/10/25 QC#21943
//        return (dtlBean.getFuncUnitFlPrcAmt() //
//                .multiply(dtlBean.getMdseInvtyCostPct()) //
//                .divide(BIG_DECIMAL_100, AMT_SCALE, RoundingMode.HALF_UP));
        return (dtlBean.getFuncUnitFlPrcAmt());
        // Mod End 2017/10/25 QC#21943
    }

    private void derivePrice(NWZC156001HdrBean hdrBean, List<NWZC156001DtlBean> dtlBeanList, NWZC156001PMsg pMsg, List<NWZC156002PMsg> pMsg2List) {
        NWZC157001PMsg prcPMsg = new NWZC157001PMsg();
        Boolean floorPriceGetFlg = false;

        ZYPEZDItemValueSetter.setValue(prcPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        // QC#9437 2016/06/21 Mod Start
        // ZYPEZDItemValueSetter.setValue(prcPMsg.prcBaseDt, pMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(prcPMsg.prcBaseDt, pMsg.slsDt);
        // QC#9437 2016/06/21 Mod End
        ZYPEZDItemValueSetter.setValue(prcPMsg.prcCtxTpCd, PRC_CTX_TP.FLOOR_PRICE);
        ZYPEZDItemValueSetter.setValue(prcPMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcPMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcPMsg.lineBizTpCd //
                , NWXC150001DsCheck.getLineBizTpCd(glblCmpyCd, slsDt, pMsg.dsOrdTpCd.getValue())); //QC#4214 Modify
        ZYPEZDItemValueSetter.setValue(prcPMsg.dsAcctNum, pMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(prcPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);

        String dsOrdLineCatgCd = NWZC156001Query.getInstance().getDsOrdLineCatgCd(pMsg);
        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
            NWZC157002PMsg prcPMsg2 = prcPMsg.NWZC157002PMsg.no(i);

            ZYPEZDItemValueSetter.setValue(prcPMsg2.trxLineNum, scrPMsg.trxLineNum);
            ZYPEZDItemValueSetter.setValue(prcPMsg2.trxLineSubNum, scrPMsg.trxLineSubNum);
            ZYPEZDItemValueSetter.setValue(prcPMsg2.prcBaseDt, scrPMsg.prcBaseDt); // QC#9437 2016/06/21 Mod Start
            
            ZYPEZDItemValueSetter.setValue(prcPMsg2.billToCustCd, scrPMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(prcPMsg2.dsAcctNum_SH //
                    , NWXC150001DsCheck.getDefaultAcctFromShipToCust(glblCmpyCd, scrPMsg.shipToCustCd.getValue()));
            ZYPEZDItemValueSetter.setValue(prcPMsg2.dsAcctNum_BL //
                    , NWXC150001DsCheck.getDefaultAcctFromBillToCust(glblCmpyCd, scrPMsg.billToCustCd.getValue()));
            ZYPEZDItemValueSetter.setValue(prcPMsg2.dsOrdLineCatgCd, dsOrdLineCatgCd);
            ZYPEZDItemValueSetter.setValue(prcPMsg2.shipToCustCd, scrPMsg.shipToCustCd);
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.csmpNum, pMsg.csmpContrNum);// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(prcPMsg2.csmpNum, scrPMsg.csmpContrNum);// 2016/03/10 S21_NA#2939
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.dlrRefNum, pMsg.dlrRefNum);// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(prcPMsg2.dlrRefNum, scrPMsg.dlrRefNum);// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(prcPMsg2.mdseCd, scrPMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(prcPMsg2.ordQty, scrPMsg.ordQty);
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.pkgUomCd, "EA");// 2016/03/10 S21_NA#2939
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.ordCustUomQty, scrPMsg.ordQty);// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(prcPMsg2.pkgUomCd, scrPMsg.custUomCd);// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(prcPMsg2.ordCustUomQty, scrPMsg.ordCustUomQty);// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(prcPMsg2.invQty, BigDecimal.ZERO);
//            if (ORD_LINE_STS.CLOSED.equals(scrPMsg.ordLineStsCd.getValue())) {
//                ZYPEZDItemValueSetter.setValue(prcPMsg2.xxPrcCloFlg, ZYPConstant.FLG_ON_Y);
//            } else {
//                ZYPEZDItemValueSetter.setValue(prcPMsg2.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
//            }
            ZYPEZDItemValueSetter.setValue(prcPMsg2.xxPrcCloFlg, ZYPConstant.FLG_OFF_N); // QC#10457
            ZYPEZDItemValueSetter.setValue(prcPMsg2.prcCatgCd, scrPMsg.flPrcListCd);// 2016/03/10 S21_NA#2939
            if (!ZYPCommonFunc.hasValue(scrPMsg.funcUnitFlPrcAmt)) {
                floorPriceGetFlg =  true;
            }
            prcPMsg.NWZC157002PMsg.setValidCount(i + 1);
        }

        NWZC157001PMsg flPMsg = new NWZC157001PMsg();
        EZDMsg.copy(prcPMsg, null, flPMsg, null);

// QC#22372 2018/01/10 Mod Start
//        new NWZC157001().execute(flPMsg, onBatchType);
//        // 2016/03/10 Mod Start S21_NA#2939
//        if (S21ApiUtil.isXxMsgId(flPMsg)) {
////            EZDMsg.copy(flPMsg.xxMsgIdList, null, pMsg.xxMsgIdList, null);
////            for (int i = 0; i < flPMsg.NWZC157002PMsg.getValidCount(); i++) {
////                NWZC157002PMsg flPMsg2 = flPMsg.NWZC157002PMsg.no(i);
////                NWZC156002PMsg pMsg2 = new NWZC156002PMsg();
////                EZDMsg.copy(flPMsg2, null, pMsg2, null);
////                pMsg2List.add(pMsg2);
////            }
////            return;
//
//            // header has error is 0.
//            for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
//                NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
//                NWZC156001DtlBean dtlBean = dtlBeanList.get(i);
//                ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitFlPrcAmt, BigDecimal.ZERO);
//                dtlBean.setFuncUnitFlPrcAmt(BigDecimal.ZERO);
//            }
//        } else {
//            for (int i = 0; i < flPMsg.NWZC157002PMsg.getValidCount(); i++) {
//                NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
//                NWZC156001DtlBean dtlBean = dtlBeanList.get(i);
//                NWZC157002PMsg flPMsg2 = flPMsg.NWZC157002PMsg.no(i);
//                if (S21ApiUtil.isXxMsgId(flPMsg2)) {// detail has error is 0.
//                    ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitFlPrcAmt, BigDecimal.ZERO);
//                    dtlBean.setFuncUnitFlPrcAmt(BigDecimal.ZERO);
//                } else {
//                    NWZC157004PMsg prcPMsg4 = flPMsg.NWZC157004PMsg.no(i);
//                    ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitFlPrcAmt, prcPMsg4.xxUnitNetPrcAmt);
//                    dtlBean.setFuncUnitFlPrcAmt(prcPMsg4.xxUnitNetPrcAmt.getValue());
//                }
//            }
//        }
// QC#22372 2018/01/10 Mod Start
        if (floorPriceGetFlg) {
            new NWZC157001().execute(flPMsg, onBatchType);
            if (S21ApiUtil.isXxMsgId(flPMsg)) {
                // header has error is 0.
                for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
                    NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
                    NWZC156001DtlBean dtlBean = dtlBeanList.get(i);
                    ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitFlPrcAmt, BigDecimal.ZERO);
                    dtlBean.setFuncUnitFlPrcAmt(BigDecimal.ZERO);
                }
            } else {
                for (int i = 0; i < flPMsg.NWZC157002PMsg.getValidCount(); i++) {
                    NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
                    NWZC156001DtlBean dtlBean = dtlBeanList.get(i);
                    NWZC157002PMsg flPMsg2 = flPMsg.NWZC157002PMsg.no(i);
                    if (ZYPCommonFunc.hasValue(scrPMsg.funcUnitFlPrcAmt)) {
                        dtlBean.setFuncUnitFlPrcAmt(scrPMsg.funcUnitFlPrcAmt.getValue());
                    } else {
                        if (S21ApiUtil.isXxMsgId(flPMsg2)) {
                            ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitFlPrcAmt, BigDecimal.ZERO);
                            dtlBean.setFuncUnitFlPrcAmt(BigDecimal.ZERO);
                        } else {
                            NWZC157004PMsg prcPMsg4 = flPMsg.NWZC157004PMsg.no(i);
                            ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitFlPrcAmt, prcPMsg4.xxUnitNetPrcAmt);
                            dtlBean.setFuncUnitFlPrcAmt(prcPMsg4.xxUnitNetPrcAmt.getValue());
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < flPMsg.NWZC157002PMsg.getValidCount(); i++) {
                NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
                NWZC156001DtlBean dtlBean = dtlBeanList.get(i);
                dtlBean.setFuncUnitFlPrcAmt(scrPMsg.funcUnitFlPrcAmt.getValue());
            }
        }
     // QC#22372 2018/01/10 Mod End

        // get CSMP credit
        ZYPEZDItemValueSetter.setValue(prcPMsg.prcCtxTpCd, PRC_CTX_TP.CSMP_CREDIT);
        // 2016/03/10 Add Start   S21_NA#2939
        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
            NWZC157002PMsg prcPMsg2 = prcPMsg.NWZC157002PMsg.no(i);

            ZYPEZDItemValueSetter.setValue(prcPMsg2.prcCatgCd, scrPMsg.csmpPrcListCd);
            prcPMsg.NWZC157002PMsg.setValidCount(i + 1);
        }
        // 2016/03/10 End Start   S21_NA#2939

        NWZC157001PMsg csmpPMsg = new NWZC157001PMsg();
        EZDMsg.copy(prcPMsg, null, csmpPMsg, null);

        new NWZC157001().execute(csmpPMsg, onBatchType);
        // 2016/03/10 Mod Start S21_NA#2939
        if (S21ApiUtil.isXxMsgId(csmpPMsg)) {
//            EZDMsg.copy(csmpPMsg.xxMsgIdList, null, pMsg.xxMsgIdList, null);
//            for (int i = 0; i < csmpPMsg.NWZC157002PMsg.getValidCount(); i++) {
//                NWZC157002PMsg csmpPMsg2 = csmpPMsg.NWZC157002PMsg.no(i);
//                NWZC156002PMsg pMsg2 = new NWZC156002PMsg();
//                EZDMsg.copy(csmpPMsg2, null, pMsg2, null);
//                pMsg2List.add(pMsg2);
//            }
//            return;
            for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
                NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
                NWZC156001DtlBean dtlBean = dtlBeanList.get(i);
                ZYPEZDItemValueSetter.setValue(scrPMsg.funcCsmpUnitCrAmt, BigDecimal.ZERO);
                dtlBean.setFuncCsmpUnitCrAmt(BigDecimal.ZERO);
                dtlBean.setCsmpContrNum(STR_BLANK); // QC#7301
                dtlBean.setCsmpPrcListCd(STR_BLANK); // QC#7301
                dtlBean.setCsmpPrcListNm(STR_BLANK); // QC#7301
                dtlBean.setDlrRefNum(STR_BLANK); // QC#7301
            }
        } else {
            for (int i = 0; i < csmpPMsg.NWZC157002PMsg.getValidCount(); i++) {
                NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
                NWZC156001DtlBean dtlBean = dtlBeanList.get(i);
                NWZC157002PMsg csmpPMsg2 = csmpPMsg.NWZC157002PMsg.no(i);
                if (S21ApiUtil.isXxMsgId(csmpPMsg2)) {
                    ZYPEZDItemValueSetter.setValue(scrPMsg.funcCsmpUnitCrAmt, BigDecimal.ZERO);
                    dtlBean.setFuncCsmpUnitCrAmt(BigDecimal.ZERO);
                    dtlBean.setCsmpContrNum(STR_BLANK); // QC#7301
                    dtlBean.setCsmpPrcListCd(STR_BLANK); // QC#7301
                    dtlBean.setCsmpPrcListNm(STR_BLANK); // QC#7301
                    dtlBean.setDlrRefNum(STR_BLANK); // QC#7301
                } else {
                    NWZC157004PMsg prcPMsg4 = csmpPMsg.NWZC157004PMsg.no(i);
                    if (ZYPCommonFunc.hasValue(dtlBean.getCsmpPrcListCd()) // QC#19721
                            && ZYPCommonFunc.hasValue(prcPMsg4.xxUnitNetPrcAmt) //
                            && ZYPCommonFunc.hasValue(scrPMsg.ordQty)) {
                        ZYPEZDItemValueSetter.setValue(scrPMsg.funcCsmpUnitCrAmt, prcPMsg4.xxUnitNetPrcAmt);
                        dtlBean.setFuncCsmpUnitCrAmt(prcPMsg4.xxUnitNetPrcAmt.getValue());
//                        dtlBean.setFuncCsmpCrAmt(prcPMsg4.xxUnitNetPrcAmt.getValue().multiply(scrPMsg.ordCustUomQty.getValue()));
                        dtlBean.setFuncCsmpCrAmt(prcPMsg4.xxUnitNetPrcAmt.getValue().multiply(scrPMsg.ordQty.getValue())); // QC#7707
                        // QC#7707 Add Start
                        dtlBean.setCsmpPrcListCd(csmpPMsg2.prcCatgCd.getValue());
//                        dtlBean.setCsmpContrNum(csmpPMsg2.prcContrNum.getValue());
                        dtlBean.setDlrRefNum(csmpPMsg2.dlrRefNum.getValue());

                        PRC_CATGTMsg prcCatgTMsg = getPrcCatgTMsg(dtlBean.getCsmpPrcListCd());
                        dtlBean.setCsmpPrcListNm(prcCatgTMsg.prcCatgNm.getValue());
                    }
                    // QC#7707 End
                }
            }
        }
        // 2016/03/10 Mod End S21_NA#2939
        // QC#7301 Add Start
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            if (!ZYPCommonFunc.hasValue(dtlBean.getCsmpPrcListCd())) {
                dtlBean.setFuncCsmpCrAmt(null);
                dtlBean.setFuncCsmpUnitCrAmt(null); // QC#7791
            }
        }
        // QC#7301 End

        return;
    }

    private BigDecimal getSplyAmt(NWZC156001PMsg pMsg) {
        BigDecimal splyAmt = BigDecimal.ZERO;
        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
            Integer cnt = NWZC156001Query.getInstance().getSplyCnt(pMsg, scrPMsg);
            if (cnt == 0) {
                continue;
            }
            splyAmt = splyAmt.add(scrPMsg.funcNetSellPrcAmt.getValue());
        }
        return splyAmt;
    }

    private BigDecimal getIstlCrAmt(NWZC156001PMsg pMsg) {
        BigDecimal istlCrAmt = BigDecimal.ZERO;
        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
            Integer cnt = NWZC156001Query.getInstance().getIstlCrCnt(pMsg, scrPMsg);
            if (cnt == 0) {
                continue;
            }
            istlCrAmt = istlCrAmt.add(scrPMsg.funcNetSellPrcAmt.getValue());
        }
        return istlCrAmt;
    }

    private BigDecimal getFinderFeeAmt(NWZC156001PMsg pMsg) {
        BigDecimal findFeeAmt = BigDecimal.ZERO;
        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
            Integer cnt = NWZC156001Query.getInstance().getFinderFeeCnt(pMsg, scrPMsg);
            if (cnt == 0) {
                continue;
            }
            findFeeAmt = findFeeAmt.add(scrPMsg.funcNetSellPrcAmt.getValue());
        }
        return findFeeAmt;
    }

    // 2016/03/10 Delete S21_NA#2939
//    private NWZC157001PMsg getSvcFlPrc(NWZC156001PMsg pMsg, List<NWZC156001DtlBean> dtlBeanList) {
//        NWZC157001PMsg prcPMsg = new NWZC157001PMsg();
//
//        ZYPEZDItemValueSetter.setValue(prcPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(prcPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
//        ZYPEZDItemValueSetter.setValue(prcPMsg.prcBaseDt, pMsg.prcBaseDt);
//        ZYPEZDItemValueSetter.setValue(prcPMsg.prcCtxTpCd, PRC_CTX_TP.SERVICE_FLOOR_PRICE);
//        ZYPEZDItemValueSetter.setValue(prcPMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
//        ZYPEZDItemValueSetter.setValue(prcPMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
//        ZYPEZDItemValueSetter.setValue(prcPMsg.lineBizTpCd //
//                , NWXC150001DsCheck.getLineBizTpCd(glblCmpyCd, slsDt, pMsg.dsOrdCatgCd.getValue()));
//        ZYPEZDItemValueSetter.setValue(prcPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
//
//        String dsOrdLineCatgCd = NWZC156001Query.getInstance().getDsOrdLineCatgCd(pMsg);
//        int i = 0;
//        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
//            NWZC157002PMsg prcPMsg2 = prcPMsg.NWZC157002PMsg.no(i++);
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.trxLineNum, dtlBean.getTrxLineNum());
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.trxLineSubNum, dtlBean.getTrxLineSubNum());
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.prcCatgCd, dtlBean.getMaintFlPrcCatgCd());
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.dsOrdLineCatgCd, dsOrdLineCatgCd);
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.ccyCd, dtlBean.getCcyCd());
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.mdseCd, dtlBean.getMdseCd());
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.ordQty, BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.invQty, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.mdlId, dtlBean.getMdlId());
//            ZYPEZDItemValueSetter.setValue(prcPMsg2.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
//        }
//        prcPMsg.NWZC157002PMsg.setValidCount(i);
//
//        new NWZC157001().execute(prcPMsg, onBatchType);
//
//        return prcPMsg;
//    }

    // 2016/03/10 Modify S21_NA#2939
    private void getSvcPrcInfo(NWZC156001PMsg pMsg, List<NWZC156001DtlBean> dtlBeanList) {

//        NWZC157001PMsg svcFlPrcPMsg = getSvcFlPrc(pMsg, dtlBeanList);
//        if (S21ApiUtil.isXxMsgId(svcFlPrcPMsg)) {
//            EZDMsg.copy(svcFlPrcPMsg.xxMsgIdList, null, pMsg.xxMsgIdList, null);
//            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
//                final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//                if (xxMsgId.endsWith("E")) {
//                    return;
//                }
//            }
//        }
//        int i = 0;
//        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
//            NWZC157004PMsg prcPMsg = svcFlPrcPMsg.NWZC157004PMsg.no(i);
//            dtlBean.setFuncUnitFlPrcAmt(prcPMsg.xxTotBaseAmt.getValue());
//        }
        for (NWZC156001DtlBean dtlBean : dtlBeanList) {
            dtlBean.setFuncUnitFlPrcAmt(BigDecimal.ZERO);
        }
        return;
    }

    private List<NWZC156001DtlBean> getChngOrdInfo(NWZC156001PMsg pMsg, String ordPrftRuleTpCd, String ordProcNodePrflCd) {
        List<NWZC156001DtlBean> dtlBeanList = new ArrayList<NWZC156001DtlBean>();
        List<Map<String, Object>> mapList = NWZC156001Query.getInstance().getChngOrdInfo(pMsg, ordProcNodePrflCd);
        if (mapList == null || mapList.size() == 0) {// 2016/03/10 Modify S21_NA#2939
            return dtlBeanList;
        }
        for (Map<String, Object> map : mapList) {
            // 2016/04/03 S21_NA#6083 Modify Start
            NWZC156001DtlBean dtlBean = new NWZC156001DtlBean();
            //            ZYPEZDItemValueSetter.setValue(dtlBean.set, (String) map.get("CPO_ORD_NUM"));
            dtlBean.setOrdPrftTrxCatgCd((String) map.get("CONFIG_CATG_CD"));
            dtlBean.setTrxLineNum((String) map.get("TRX_LINE_NUM"));
            dtlBean.setTrxLineSubNum((String) map.get("TRX_LINE_SUB_NUM"));
            dtlBean.setDsOrdPosnNum((String) map.get("DS_ORD_POSN_NUM"));
            dtlBean.setDsCpoLineNum((BigDecimal) map.get("DS_CPO_LINE_NUM"));
            dtlBean.setDsCpoLineSubNum((BigDecimal) map.get("DS_CPO_LINE_SUB_NUM"));
            dtlBean.setMdseCd((String) map.get("MDSE_CD"));
            dtlBean.setRtlWhCd((String) map.get("RTL_WH_CD"));
            dtlBean.setRtlSwhCd((String) map.get("RTL_SWH_CD"));
            dtlBean.setDsOrdLineCatgCd((String) map.get("DS_ORD_LINE_CATG_CD"));
            dtlBean.setOrdQty((BigDecimal) map.get("ORD_QTY"));
            dtlBean.setCustUomCd((String) map.get("CUST_UOM_CD"));
            dtlBean.setFlPrcListCd((String) map.get("FL_PRC_LIST_CD"));
            dtlBean.setCsmpPrcListCd((String) map.get("CSMP_PRC_LIST_CD"));
            dtlBean.setPrcCatgCd((String) map.get("PRC_CATG_CD")); // QC#7707
            dtlBean.setFuncManFlAdjAmt((BigDecimal) map.get("FUNC_MAN_FL_ADJ_AMT"));
            dtlBean.setFuncUnitListPrcAmt((BigDecimal) map.get("FUNC_PRC_LIST_PRC_AMT"));
            dtlBean.setFuncNetUnitPrcAmt((BigDecimal) map.get("FUNC_NET_UNIT_PRC_AMT"));
            dtlBean.setFuncNetSellPrcAmt((BigDecimal) map.get("FUNC_NET_AMT"));
            dtlBean.setFuncSvcCostTrnsfAmt((BigDecimal) map.get("FUNC_SVC_COST_TRNSF_AMT"));
            dtlBean.setFuncManRepRevAdjAmt((BigDecimal) map.get("FUNC_MAN_REP_REV_ADJ_AMT"));
            dtlBean.setFuncSvcRevTrnsfAmt((BigDecimal) map.get("FUNC_SVC_REV_TRNSF_AMT"));
            dtlBean.setCpoDtlFuncSlsAmt((BigDecimal) map.get("CPO_DTL_FUNC_SLS_AMT"));
            dtlBean.setOrdCustUomQty((BigDecimal) map.get("ORD_CUST_UOM_QTY"));
            dtlBean.setCsmpContrNum((String) map.get("DTL_CSMP_CONTR_NUM"));
            dtlBean.setDlrRefNum((String) map.get("DTL_DLR_REF_NUM"));
            dtlBean.setOrdLineStsCd((String) map.get("LINE_STS_CD"));
            dtlBean.setBillToCustCd((String) map.get("BILL_TO_CUST_CD"));
            dtlBean.setShipToCustCd((String) map.get("SHIP_TO_CUST_CD"));
            dtlBean.setFuncUnitStdCostAmt((BigDecimal) map.get("THIS_MTH_TOT_STD_COST_AMT"));

            dtlBean.setOrdPrftRuleTpCd(ordPrftRuleTpCd);

            ALL_MDSE_VTMsg mdseTMsg = getMdseTMsg(dtlBean.getMdseCd());
            dtlBean.setMdseNm(mdseTMsg.mdseNm.getValue());
            dtlBean.setMdseDescShortTxt(mdseTMsg.mdseDescShortTxt.getValue());
            dtlBean.setCoaMdseTpCd(mdseTMsg.coaMdseTpCd.getValue());
            dtlBean.setCoaProdCd(mdseTMsg.coaProdCd.getValue());
            dtlBean.setMdseItemTpCd(mdseTMsg.mdseItemTpCd.getValue());

            SWHTMsg swhTMsg = getSwhTMsg(dtlBean.getRtlWhCd(), dtlBean.getRtlSwhCd());
            dtlBean.setMdseInvtyCostPct(swhTMsg.mdseInvtyCostPct.getValue());

            ORD_PRFT_MDSE_TP_RULETMsg mtrTMsg//
            = getOrdPrftMdseTpRuleTMsg(ORD_PROC_NODE_PRFL.EQUIPMENT.getValue(), mdseTMsg.coaMdseTpCd.getValue());
            dtlBean.setFlPrcCalcInclFlg(mtrTMsg.flPrcCalcInclFlg.getValue());
            dtlBean.setRepRevCalcInclFlg(mtrTMsg.repRevCalcInclFlg.getValue());
            dtlBean.setDiscMdseTpFlg(mtrTMsg.discMdseTpFlg.getValue());
            dtlBean.setRedFlPrcFlg(mtrTMsg.redFlPrcFlg.getValue());
            dtlBean.setRedRepRevFlg(mtrTMsg.redRepRevFlg.getValue());
            dtlBean.setDiscAllocMethCd(mtrTMsg.discAllocMethCd.getValue());
            dtlBean.setDlrCrPrftInclFlg(mtrTMsg.dlrCrPrftInclFlg.getValue());
            dtlBean.setRedCompAmtFlg(mtrTMsg.redCompAmtFlg.getValue());

            PRC_CATGTMsg prcCatgTMsg = getPrcCatgTMsg(dtlBean.getFlPrcListCd());
            dtlBean.setFlPrcListNm(prcCatgTMsg.prcCatgNm.getValue());
            prcCatgTMsg = getPrcCatgTMsg(dtlBean.getCsmpPrcListCd());
            dtlBean.setCsmpPrcListNm(prcCatgTMsg.prcCatgNm.getValue());
            prcCatgTMsg = getPrcCatgTMsg(dtlBean.getPrcCatgCd()); // QC#7707
            dtlBean.setPrcCatgNm(prcCatgTMsg.prcCatgNm.getValue()); // QC#7707

//            dtlBean.setMdseNm((String) map.get("MDSE_NM"));
//            dtlBean.setMdseDescShortTxt((String) map.get("MDSE_DESC_SHORT_TXT"));// 2016/03/10 S21_NA#2939
//            dtlBean.setFuncNetSellPrcAmt((BigDecimal) map.get("CPO_DTL_FUNC_SLS_AMT"));
//            dtlBean.setCsmpPrcListNm((String) map.get("CSMP_PRC_LIST_NM"));
//            dtlBean.setSellToCustCd((String) map.get("SELL_TO_CUST_CD"));
//            dtlBean.setShipToCustAcctCd((String) map.get("SHIP_TO_CUST_ACCT_CD"));
//            dtlBean.setBillToCustAcctCd((String) map.get("BILL_TO_CUST_ACCT_CD"));
//            dtlBean.setCoaMdseTpCd((String) map.get("COA_MDSE_TP_CD"));
//            dtlBean.setCoaProdCd((String) map.get("COA_PROD_CD"));
//            dtlBean.setMdseItemTpCd((String) map.get("MDSE_ITEM_TP_CD"));
//            dtlBean.setMdseInvtyCostPct((BigDecimal) map.get("MDSE_INVTY_COST_PCT"));
//            dtlBean.setFlPrcCalcInclFlg((String) map.get("FL_PRC_CALC_INCL_FLG"));
//            dtlBean.setRepRevCalcInclFlg((String) map.get("REP_REV_CALC_INCL_FLG"));
//            dtlBean.setDiscMdseTpFlg((String) map.get("DISC_MDSE_TP_FLG"));
//            dtlBean.setRedFlPrcFlg((String) map.get("RED_FL_PRC_FLG"));
//            dtlBean.setRedRepRevFlg((String) map.get("RED_REP_REV_FLG"));
//            dtlBean.setDiscAllocMethCd((String) map.get("DISC_ALLOC_METH_CD"));
//            dtlBean.setDlrCrPrftInclFlg((String) map.get("DLR_CR_PRFT_INCL_FLG"));
//            dtlBean.setRedCompAmtFlg((String) map.get("RED_COMP_AMT_FLG"));
            // 2016/04/03 S21_NA#6083 Modify End

            dtlBeanList.add(dtlBean);
        }
        return dtlBeanList;
    }

    private void execCreditMode(NWZC156001PMsg pMsg, List<NWZC156002PMsg> pMsg2List) {
        List<Map<String, Object>> rsltList = NWZC156001Query.getInstance().getLatestPrftInfo(pMsg);
        if (rsltList.isEmpty()) {
//            setMsgId(pMsg, NWZM1521E); // 2016/06/24 S21_NA#10820 Del
            return;
        }

        String currTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        ORD_PRFTTMsg ordPrftTMsg = createOrdPrftTMsgForCredit(rsltList.get(0), currTs, pMsg.trxHdrNum.getValue());
        EZDMsg.copy(ordPrftTMsg, null, pMsg, null);

        List<ORD_PRFT_DTLTMsg> ordPrftDtlTMsgList = createOrdPrftDtlTMsgForCredit(rsltList, ordPrftTMsg, currTs);
        int i = 0;
        for (ORD_PRFT_DTLTMsg tMsg : ordPrftDtlTMsgList) {
            EZDMsg.copy(tMsg, null, pMsg.svcConfigRef.no(i++), null);
        }

        S21FastTBLAccessor.insert(ordPrftTMsg);
        int cnt = S21FastTBLAccessor.insert(ordPrftDtlTMsgList.toArray(new ORD_PRFT_DTLTMsg[0]));
        if (cnt != ordPrftDtlTMsgList.size()) {
            NWZC156002PMsg pMsg2 = new NWZC156002PMsg();
            setMsgId2(pMsg2, NWZM1522E);
            pMsg2List.add(pMsg2);
        }
        return;
    }

    private List<ORD_PRFT_DTLTMsg> createOrdPrftDtlTMsgForCredit(List<Map<String, Object>> rsltList, ORD_PRFTTMsg ordPrftTMsg, String currTs) {
        List<ORD_PRFT_DTLTMsg> ordPrftDtlTMsgList = new ArrayList<ORD_PRFT_DTLTMsg>();
        for (Map<String, Object> rsltMap : rsltList) {
            ORD_PRFT_DTLTMsg tMsg = new ORD_PRFT_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, ((String) rsltMap.get("TRX_HDR_NUM"))); //S21_NA#11098
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRFT_DTL_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftPk, ordPrftTMsg.ordPrftPk);
            ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, ordPrftTMsg.trxHdrNum);
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftVrsnNum, ordPrftTMsg.ordPrftVrsnNum);
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftTrxCatgCd, ((String) rsltMap.get("ORD_PRFT_TRX_CATG_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.trxLineNum, ((String) rsltMap.get("TRX_LINE_NUM")));
            ZYPEZDItemValueSetter.setValue(tMsg.trxLineSubNum, ((String) rsltMap.get("TRX_LINE_SUB_NUM")));
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdPosnNum, ((String) rsltMap.get("DS_ORD_POSN_NUM")));
            ZYPEZDItemValueSetter.setValue(tMsg.dsCpoLineNum, ((BigDecimal) rsltMap.get("DS_CPO_LINE_NUM")));
            ZYPEZDItemValueSetter.setValue(tMsg.dsCpoLineSubNum, ((BigDecimal) rsltMap.get("DS_CPO_LINE_SUB_NUM")));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, ((String) rsltMap.get("MDSE_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseNm, ((String) rsltMap.get("MDSE_NM")));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, ((String) rsltMap.get("RTL_WH_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, ((String) rsltMap.get("RTL_SWH_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseInvtyCostPct, ((BigDecimal) rsltMap.get("MDSE_INVTY_COST_PCT")));
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdLineCatgCd, ((String) rsltMap.get("DS_ORD_LINE_CATG_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.ordQty, ((BigDecimal) rsltMap.get("ORD_QTY")));
            ZYPEZDItemValueSetter.setValue(tMsg.ordCustUomQty, ((BigDecimal) rsltMap.get("ORD_CUST_UOM_QTY"))); // 2016/03/25 S21_NA#5491
            ZYPEZDItemValueSetter.setValue(tMsg.custUomCd, ((String) rsltMap.get("CUST_UOM_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.flPrcListCd, ((String) rsltMap.get("FL_PRC_LIST_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.csmpPrcListCd, ((String) rsltMap.get("CSMP_PRC_LIST_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, ((String) rsltMap.get("PRC_CATG_CD"))); // QC#7707
            ZYPEZDItemValueSetter.setValue(tMsg.funcDlrCrAmt, negate((BigDecimal) rsltMap.get("FUNC_DLR_CR_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcRedCompAmt, negate((BigDecimal) rsltMap.get("FUNC_RED_COMP_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcUnitFlPrcAmt, negate((BigDecimal) rsltMap.get("FUNC_UNIT_FL_PRC_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcInitFlPrcAmt, negate((BigDecimal) rsltMap.get("FUNC_INIT_FL_PRC_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.lineWtAmtRate, negate((BigDecimal) rsltMap.get("LINE_WT_AMT_RATE"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcGenlFlAdjAmt, negate((BigDecimal) rsltMap.get("FUNC_GENL_FL_ADJ_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcSpecFlAdjAmt, negate((BigDecimal) rsltMap.get("FUNC_SPEC_FL_ADJ_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcManFlAdjAmt, negate((BigDecimal) rsltMap.get("FUNC_MAN_FL_ADJ_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcFlAdjAmt, negate((BigDecimal) rsltMap.get("FUNC_FL_ADJ_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcCsmpUnitCrAmt, negate((BigDecimal) rsltMap.get("FUNC_CSMP_UNIT_CR_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcCsmpCrAmt, negate((BigDecimal) rsltMap.get("FUNC_CSMP_CR_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcCsmpFlPrcAmt, negate((BigDecimal) rsltMap.get("FUNC_CSMP_FL_PRC_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcSvcCostTrnsfAmt, negate((BigDecimal) rsltMap.get("FUNC_SVC_COST_TRNSF_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcWtSvcCostTrnsfAmt, negate((BigDecimal) rsltMap.get("FUNC_WT_SVC_COST_TRNSF_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcFinalFlPrcAmt, negate((BigDecimal) rsltMap.get("FUNC_FINAL_FL_PRC_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcUnitListPrcAmt, negate((BigDecimal) rsltMap.get("FUNC_UNIT_LIST_PRC_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcNetUnitPrcAmt, negate((BigDecimal) rsltMap.get("FUNC_NET_UNIT_PRC_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcNetSellPrcAmt, negate((BigDecimal) rsltMap.get("FUNC_NET_SELL_PRC_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcInitRepRevAmt, negate((BigDecimal) rsltMap.get("FUNC_INIT_REP_REV_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcGenlRepRevAdjAmt, negate((BigDecimal) rsltMap.get("FUNC_GENL_REP_REV_ADJ_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcSpecRepRevAdjAmt, negate((BigDecimal) rsltMap.get("FUNC_SPEC_REP_REV_ADJ_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcManRepRevAdjAmt, negate((BigDecimal) rsltMap.get("FUNC_MAN_REP_REV_ADJ_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcRepRevAdjAmt, negate((BigDecimal) rsltMap.get("FUNC_REP_REV_ADJ_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcSvcRevTrnsfAmt, negate((BigDecimal) rsltMap.get("FUNC_SVC_REV_TRNSF_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcWtSvcRevTrnsfAmt, negate((BigDecimal) rsltMap.get("FUNC_WT_SVC_REV_TRNSF_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcFinalRepRevAmt, negate((BigDecimal) rsltMap.get("FUNC_FINAL_REP_REV_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcUnitMsrpAmt, negate((BigDecimal) rsltMap.get("FUNC_UNIT_MSRP_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcUnitStdCostAmt, negate((BigDecimal) rsltMap.get("FUNC_UNIT_STD_COST_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcFinalStdCostAmt, negate((BigDecimal) rsltMap.get("FUNC_FINAL_STD_COST_AMT"))); // QC#9707
            ZYPEZDItemValueSetter.setValue(tMsg.funcWtManFlAdjAmt, negate((BigDecimal) rsltMap.get("FUNC_WT_MAN_FL_ADJ_AMT"))); // QC#9707 // QC#7707
            ZYPEZDItemValueSetter.setValue(tMsg.funcWtManRevAdjAmt, negate((BigDecimal) rsltMap.get("FUNC_WT_MAN_REV_ADJ_AMT"))); // QC#9707 // QC#7707
            ZYPEZDItemValueSetter.setValue(tMsg.funcFinalUnitFlPrcAmt, negate((BigDecimal) rsltMap.get("FUNC_FINAL_UNIT_FL_PRC_AMT"))); // QC#9707 // QC#7707
            ZYPEZDItemValueSetter.setValue(tMsg.funcFinalUnitRevAmt, negate((BigDecimal) rsltMap.get("FUNC_FINAL_UNIT_REV_AMT"))); // QC#9707 // QC#7707
            ZYPEZDItemValueSetter.setValue(tMsg.csmpContrNum, ((String) rsltMap.get("CSMP_CONTR_NUM")));
            ZYPEZDItemValueSetter.setValue(tMsg.dlrRefNum, ((String) rsltMap.get("DLR_REF_NUM")));
            ZYPEZDItemValueSetter.setValue(tMsg.csmpContrStartDt, ((String) rsltMap.get("CSMP_CONTR_START_DT")));
            ZYPEZDItemValueSetter.setValue(tMsg.csmpContrEndDt, ((String) rsltMap.get("CSMP_CONTR_END_DT")));
            ZYPEZDItemValueSetter.setValue(tMsg.chngOrdFlg, ((String) rsltMap.get("CHNG_ORD_FLG")));
            ZYPEZDItemValueSetter.setValue(tMsg.coaMdseTpCd, ((String) rsltMap.get("COA_MDSE_TP_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, ((String) rsltMap.get("COA_PROD_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseItemTpCd, ((String) rsltMap.get("MDSE_ITEM_TP_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcUsrId, userId);
            ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcTs, currTs);
            ZYPEZDItemValueSetter.setValue(tMsg.ordPrftRuleTpCd, ((String) rsltMap.get("ORD_PRFT_RULE_TP_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.flPrcCalcInclFlg, ((String) rsltMap.get("FL_PRC_CALC_INCL_FLG")));
            ZYPEZDItemValueSetter.setValue(tMsg.repRevCalcInclFlg, ((String) rsltMap.get("REP_REV_CALC_INCL_FLG")));
            ZYPEZDItemValueSetter.setValue(tMsg.discMdseTpFlg, ((String) rsltMap.get("DISC_MDSE_TP_FLG")));
            ZYPEZDItemValueSetter.setValue(tMsg.redFlPrcFlg, ((String) rsltMap.get("RED_FL_PRC_FLG")));
            ZYPEZDItemValueSetter.setValue(tMsg.redRepRevFlg, ((String) rsltMap.get("RED_REP_REV_FLG")));
            ZYPEZDItemValueSetter.setValue(tMsg.discAllocMethCd, ((String) rsltMap.get("DISC_ALLOC_METH_CD")));
            ZYPEZDItemValueSetter.setValue(tMsg.dlrCrPrftInclFlg, ((String) rsltMap.get("DLR_CR_PRFT_INCL_FLG")));
            ZYPEZDItemValueSetter.setValue(tMsg.redCompAmtFlg, ((String) rsltMap.get("RED_COMP_AMT_FLG")));

            ordPrftDtlTMsgList.add(tMsg);
        }
        return ordPrftDtlTMsgList;
    }

    private ORD_PRFTTMsg createOrdPrftTMsgForCredit(Map<String, Object> rsltMap, String currTs, String trxHdrNum) {
        ORD_PRFTTMsg tMsg = new ORD_PRFTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordPrftPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRFT_SQ));
//        ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, ((String) rsltMap.get("TRX_HDR_NUM"))); //S21_NA#11098
        ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, trxHdrNum); //S21_NA#11098
        ZYPEZDItemValueSetter.setValue(tMsg.ordPrftVrsnNum, BigDecimal.ONE); //S21_NA#11098
        ZYPEZDItemValueSetter.setValue(tMsg.funcNegoDealAmt, negate((BigDecimal) rsltMap.get("FUNC_NEGO_DEAL_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncRepRevAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_REP_REV_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncRepRevAdjAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_REP_REV_ADJ_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncFinalFlAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_FINAL_FL_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.funcGrsPrftAmt, negate((BigDecimal) rsltMap.get("FUNC_GRS_PRFT_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.grsPrftPct, negate((BigDecimal) rsltMap.get("GRS_PRFT_PCT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncMsrpAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_MSRP_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncStdFlAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_STD_FL_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncFlAdjAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_FL_ADJ_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.csmpOrdFlg, ((String) rsltMap.get("CSMP_ORD_FLG")));
        ZYPEZDItemValueSetter.setValue(tMsg.csmpContrStartDt, ((String) rsltMap.get("CSMP_CONTR_START_DT")));
        ZYPEZDItemValueSetter.setValue(tMsg.csmpContrEndDt, ((String) rsltMap.get("CSMP_CONTR_END_DT")));
        ZYPEZDItemValueSetter.setValue(tMsg.csmpContrNum, ((String) rsltMap.get("CSMP_CONTR_NUM_HDR"))); // S21_NA#11098
        ZYPEZDItemValueSetter.setValue(tMsg.dlrRefNum, ((String) rsltMap.get("DLR_REF_NUM_HDR"))); // S21_NA#11098
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncCsmpCrAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_CSMP_CR_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncCsmpFlAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_CSMP_FL_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcUsrId, userId);
        ZYPEZDItemValueSetter.setValue(tMsg.lastPrftCalcTs, currTs);
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncByotAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_BYOT_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncSvcRevTrnsfAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_SVC_REV_TRNSF_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncSvcCostTrnsfAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_SVC_COST_TRNSF_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncProSvcAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_PRO_SVC_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncOmMaintBllblAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_OM_MAINT_BLLBL_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.funcAltGrsPrftAmt, negate((BigDecimal) rsltMap.get("FUNC_ALT_GRS_PRFT_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.altGrsPrftPct, negate((BigDecimal) rsltMap.get("ALT_GRS_PRFT_PCT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncDlrCrAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_DLR_CR_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncDlrInvAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_DLR_INV_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncRedCompAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_RED_COMP_AMT"))); // QC#9707

        ZYPEZDItemValueSetter.setValue(tMsg.totFuncFndrFeeAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_FNDR_FEE_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncIstlCrAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_ISTL_CR_AMT"))); // QC#9707
        ZYPEZDItemValueSetter.setValue(tMsg.totFuncSplyAmt, negate((BigDecimal) rsltMap.get("TOT_FUNC_SPLY_AMT"))); // QC#9707

        return tMsg;
    }

    private void parameterCheck(NWZC156001PMsg pMsg, List<NWZC156002PMsg> pMsg2List) {
        if (!MODE_LIST.contains(pMsg.xxModeCd.getValue())) {
            setMsgId(pMsg, NWZM0977E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setMsgId(pMsg, NWZM0011E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt) //
                && !MODE_CREDIT.equals(pMsg.xxModeCd.getValue())) {
            setMsgId(pMsg, NWZM0346E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.trxHdrNum)) {
            setMsgId(pMsg, NWZM0027E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.dsOrdCatgCd)) {
            setMsgId(pMsg, NWZM1515E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.dsOrdTpCd)) {
            setMsgId(pMsg, NWZM1253E);
        }
        if (!MODE_CREDIT.equals(pMsg.xxModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.prcBaseDt)) {
                setMsgId(pMsg, NWZM1155E);
            }
// 2016/03/10 S21_NA#2939 Delete Start
//            if (!ZYPCommonFunc.hasValue(pMsg.prcCalcDt)) {
//                setMsgId(pMsg, NWZM1516E);
//            }
// 2016/03/10 S21_NA#2939 Delete End
            for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
                NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
                NWZC156002PMsg pMsg2 = parameterDetailCheck(scrPMsg, pMsg2List);
                if (pMsg2 != null) {
                    pMsg2List.add(pMsg2);
                }
            }
        }
    }

    private NWZC156002PMsg parameterDetailCheck(NWZC156001_svcConfigRefPMsg scrPMsg, List<NWZC156002PMsg> pMsg2List) {
        NWZC156002PMsg pMsg2 = new NWZC156002PMsg();

        if (!ZYPCommonFunc.hasValue(scrPMsg.ordPrftTrxCatgCd)) {
            setMsgId2(pMsg2, NWZM1517E);
        }
        if (!ZYPCommonFunc.hasValue(scrPMsg.trxLineNum)) {
            setMsgId2(pMsg2, NWZM0089E);
        }
        if (!ZYPCommonFunc.hasValue(scrPMsg.trxLineSubNum)) {
            setMsgId2(pMsg2, NWZM0043E);
        }
        if (!ZYPCommonFunc.hasValue(scrPMsg.mdseCd)) {
            setMsgId2(pMsg2, NWZM0021E);
        }
        if (!ZYPCommonFunc.hasValue(scrPMsg.dsOrdLineCatgCd)) {
            setMsgId2(pMsg2, NWZM1518E);
        }
        if (!ZYPCommonFunc.hasValue(scrPMsg.ordQty)) {
            setMsgId2(pMsg2, NWZM0046E);
        }
        if (!ZYPCommonFunc.hasValue(scrPMsg.flPrcListCd)) {
            setMsgId2(pMsg2, NWZM1406E);
        }
        if (!ZYPCommonFunc.hasValue(scrPMsg.funcUnitListPrcAmt)) {
            setMsgId2(pMsg2, NWZM0352E);
        }
        if (!ZYPCommonFunc.hasValue(scrPMsg.funcNetUnitPrcAmt)) {
            setMsgId2(pMsg2, NWZM1519E);
        }
        if (!ZYPCommonFunc.hasValue(scrPMsg.funcNetSellPrcAmt)) {
            setMsgId2(pMsg2, NWZM1520E);
        }

        if (S21ApiUtil.isXxMsgId(pMsg2)) {
            ZYPEZDItemValueSetter.setValue(pMsg2.trxLineNum, scrPMsg.trxLineNum);
            ZYPEZDItemValueSetter.setValue(pMsg2.trxLineSubNum, scrPMsg.trxLineSubNum);
            return pMsg2;
        }
        return null;
    }

    /**
     * <pre>
     * Set the message id.
     * @param pMsg      Input parameters.
     * @param val setting value for Message ID
     * </pre>
     */
    private void setMsgId(NWZC156001PMsg pMsg, String val) {
        this.msgIdMgr.addXxMsgId(val, pMsg);
    }

    /**
     * <pre>
     * Set the message id.
     * @param messages  Message Manager.
     * @param pMsg2     NWZC156002PMsg.
     * @param val setting value for Message ID
     * </pre>
     */
    private void setMsgId2(NWZC156002PMsg pMsg2, String val) {
        int ix = pMsg2.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgId, val);
        pMsg2.xxMsgIdList.setValidCount(ix + 1);
    }

    // 2016/03/10 S21_NA#2939
    private PRC_CATGTMsg getPrcCatgTMsg(String prcCatgCd) {
        if (!ZYPCommonFunc.hasValue(prcCatgCd)) {
            return new PRC_CATGTMsg();
        }
        PRC_CATGTMsg tMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, prcCatgCd);

        tMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return new PRC_CATGTMsg();
        }
        return tMsg;
    }

    private BigDecimal getValue(BigDecimal val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return BigDecimal.ZERO;
        }
        return val;
    }

    private BigDecimal getMaxPctValue(BigDecimal val) {
        if (val == null) {
            return val;
        }
        if (val.compareTo(MAX_PRC_VAL) > 0) {
            return MAX_PRC_VAL;
        } else if (val.compareTo(MIN_PRC_VAL) < 0) {
            return MIN_PRC_VAL;
        }
        return val;
    }

    // QC#7707
    private String getDiscAllocMethCd(NWZC156001PMsg pMsg, String mdseTpCtxTpCd) {
        if (this.cacheDiscAllocMethMap == null) {
            this.cacheDiscAllocMethMap = new HashMap<String, String>();

            List<Map<String, String>> coaMdseTpList = NWZC156001Query.getInstance().getDiscAllocMethList(pMsg, ORD_PRFT_RULE_TP.EQUIPMENT);
            for (Map<String, String> map : coaMdseTpList) {
                String key = map.get("MDSE_TP_CTX_TP_CD");
                if (!this.cacheDiscAllocMethMap.containsKey(key)) {
                    this.cacheDiscAllocMethMap.put(key, map.get("DISC_ALLOC_METH_CD"));
                }
            }
        }
        return this.cacheDiscAllocMethMap.get(mdseTpCtxTpCd);
    }

    //QC#9707 ADD
    /**
     * negate
     * @param val BigDecimal
     * @return BigDecimal
     */
    private BigDecimal negate(BigDecimal val) {
        if (val == null) {
            return val;
        }
        return val.negate();
    }

    // S21_NA#11981 Add
    private boolean isRebillOrderLine(NWZC156001PMsg pMsg, NWZC156001_svcConfigRefPMsg scrPMsg) {
        if (!ORD_PRFT_TRX_CATG.OUTBOUND.equals(scrPMsg.ordPrftTrxCatgCd.getValue())) {
            return false;
        }
        // Search CPO_DTL
        CPO_DTLTMsg cpoDtl = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cpoDtl.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoDtl.cpoOrdNum, pMsg.trxHdrNum);
        ZYPEZDItemValueSetter.setValue(cpoDtl.cpoDtlLineNum, scrPMsg.trxLineNum);
        ZYPEZDItemValueSetter.setValue(cpoDtl.cpoDtlLineSubNum, scrPMsg.trxLineSubNum);
        cpoDtl = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtl);

        if (cpoDtl == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(cpoDtl.getReturnCode())) { // S21_NA#13916 MOD
            return false;

        } else if (CR_REBIL.REBILL.equals(cpoDtl.crRebilCd.getValue())) {
            return true;
        }
        return false;
    }
    // S21_NA#11981 Add
    private BigDecimal getOrigLatestPrftInfo(NWZC156001PMsg pMsg, NWZC156001_svcConfigRefPMsg scrPMsg) {

        Map<String, Object> origLatestPrftInfo = NWZC156001Query.getInstance().getLatestPrftDtl(pMsg, pMsg.trxHdrNum.getValue(), scrPMsg.trxLineNum.getValue(), scrPMsg.trxLineSubNum.getValue(), ORD_PRFT_TRX_CATG.OUTBOUND);
        if (origLatestPrftInfo == null || !ZYPCommonFunc.hasValue((BigDecimal) origLatestPrftInfo.get("MDSE_INVTY_COST_PCT"))) {
            return null;

        } else {
            return (BigDecimal) origLatestPrftInfo.get("MDSE_INVTY_COST_PCT");
        }

    }

    // Add Start 2017/10/10 QC#21664
    private boolean checkReducation(NWZC156001DtlBean dtlBean, NWZC156001PMsg pMsg) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        rtlWhTMsg.setSQLID("002");
        rtlWhTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rtlWhTMsg.setConditionValue("rtlWhCd01", dtlBean.getRtlWhCd());
        RTL_WHTMsgArray outRtlWhTMsg = (RTL_WHTMsgArray) S21ApiTBLAccessor.findByCondition(rtlWhTMsg);

        if (outRtlWhTMsg == null || outRtlWhTMsg.length() == 0) {
            return false;
        } else if (!INVTY_OWNER_CD_CSA.equals(outRtlWhTMsg.no(0).invtyOwnrCd.getValue())) {
            return false;
        }

        String ordLineSrcCatgCd = NWZC156001Query.getInstance().getOrdLineSrcCatgCd(dtlBean, pMsg);

        if (null == ordLineSrcCatgCd) {
            return false;
        } else if (!ORD_LINE_SRC_CATG_CD_INTERNAL.equals(ordLineSrcCatgCd)) {
            return false;
        }

        return true;
    }

    private boolean checkOrdProcNodePrflCd(NWZC156001PMsg pMsg) {

        Map<String, String> map = NWZC156001Query.getInstance().getPrftRuleTp(pMsg);
        if (map == null) {
            return false;
        }

        String ordProcNodePrflCd = (String) map.get("ORD_PROC_NODE_PRFL_CD");
        if (ORD_PROC_NODE_PRFL_CD_33.equals(ordProcNodePrflCd)) {
            return true;
        }

        return false;
    }
    // Add End 2017/10/10 QC#21664
}
