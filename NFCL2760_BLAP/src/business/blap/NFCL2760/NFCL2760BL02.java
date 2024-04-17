package business.blap.NFCL2760;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL2760.common.NFCL2760CommonLogic;
import business.blap.NFCL2760.constant.NFCL2760Constant;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_ADJ_TPTMsg;
import business.db.AR_ADJ_TPTMsgArray;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.file.NFCL2760F00FMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/04   Hitachi         K.Kojima        Update          QC#6466
 * 2016/08/26   Hitachi         T.Tsuchida      Update          QC#18383
 * 2016/09/07   Hitachi         J.Kim           Update          QC#13793
 * 2016/11/07   Fujitsu         S.Fujita        Update          QC#13580
 * 2017/01/11   Fujitsu         T.Murai         Update          QC#16952
 * 2017/02/15   Fujitsu         T.Murai         Update          QC#17624
 * 2017/08/28   Hitachi         T.Tsuchida      Update          QC#20823
 * 2017/11/16   Fujitsu         M.Ohno          Update          QC#21402
 * 2018/01/11   Fujitsu         T.Murai         Update          QC#23134
 * 2018/02/08   Fujitsu         H.Ikeda         Update          QC#23657
 * 2018/05/08   Fujitsu         Y.Matsui        Update          QC#25702
 * 2018/06/06   Fujitsu         Y.Matsui        Update          QC#25369,QC#25737
 * 2018/07/19   Fujitsu         Y.Matsui        Update          QC#26871
 * 2018/07/20   Fujitsu         Y.Matsui        Update          QC#26985
 * 2018/08/02   Fujitsu         Y.Matsui        Update          QC#26985-1
 * 2018/10/24   Fujitsu         T.Ogura         Update          QC#28168-1
 * 2019/09/02   Fujitsu         H.Ikeda         Update          QC#53138
 * 2020/01/23   Fujitsu         H.Ikeda         Update          QC#54902
 * 2021/06/21   CITS            H.Dimay         Update          QC#58639
 * 2022/01/06   CITS            G.Delgado       Update          QC#59329
 * 2022/01/26   Hitachi         A.Kohinata      Update          QC#59641
 * 2022/02/22   CITS            D.Mamaril       Update          QC#59733
 * 2022/04/22   CITS            D.Mamaril       Update          QC#59333
 * 2022/09/12   Hitachi         A.Kohinata      Update          QC#60541
 * 2022/11/30	Hitachi			R.Takau			UpDate			QC#57252
 * 2024/02/20   Hitachi         TZ.Win          Update          QC#63450
 * </pre>
 */
public class NFCL2760BL02 extends S21BusinessHandler implements NFCL2760Constant {
    private boolean blIni = false;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            blIni = false;

            // NFCL2760_INIT
            if ("NFCL2760_INIT".equals(screenAplID)) {
                blIni = true;
                doProcess_NFCL2760_INIT(cMsg, sMsg);
                // START 2017/08/28 T.Tsuchida [QC#20823,ADD]
                doProcess_NFCL2760Scrn00_CalcGrossAmount(cMsg, sMsg);
                // END 2017/08/28 T.Tsuchida [QC#20823,ADD]
                // NFCL2760_NFCL5050
                // 2017/11/16 QC#21402 add start
                addInitDetail(cMsg, sMsg);
                // 2017/11/16 QC#21402 add end
            } else if ("NFCL2760_NFCL5050".equals(screenAplID)) {
                doProcess_NFCL2760_NFCL5050(cMsg, sMsg);
                // NFCL2760Scrn00_DownloadCSV
                // START 2016/08/04 K.Kojima [QC#6466,ADD]
            } else if ("NFCL2760Scrn00_DownloadCSV".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_DownloadCSV(cMsg, sMsg);
                // END 2016/08/04 K.Kojima [QC#6466,ADD]
            } else if ("NFCL2760Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_PagePrev(cMsg, sMsg);
                // NFCL2760Scrn00_PageNext
            } else if ("NFCL2760Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_PageNext(cMsg, sMsg);
                // NFCL2760Scrn00_CalcGrossAmount
            } else if ("NFCL2760Scrn00_CalcGrossAmount".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_CalcGrossAmount(cMsg, sMsg);
                // NFCL2760Scrn00_NFCL5140
            } else if ("NFCL2760_NFCL5140".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_NFCL5140(cMsg, sMsg);
                //doProcess_NFCL2760Scrn00_CalcGrossAmount(cMsg, sMsg);
                // NFCL2760Scrn00_DeleteLines
            } else if ("NFCL2760Scrn00_CMN_Submit".equals(screenAplID)) {
                //doProcess_NFCL2760Scrn00_CMN_Submit(cMsg, sMsg);
                doProcess_NFCL2760_INIT(cMsg, sMsg);
                // START 2017/08/28 T.Tsuchida [QC#20823,ADD]
                doProcess_NFCL2760Scrn00_CalcGrossAmount(cMsg, sMsg);
                // END 2017/08/28 T.Tsuchida [QC#20823,ADD]
                // add start 2022/01/26 QC#59641
                addInitDetail(cMsg, sMsg);
                // add end 2022/01/26 QC#59641
                // NFCL2760Scrn00_OpenWin_Search
            } else if ("NFCL2760Scrn00_OpenWin_Search".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_OpenWin_Search(cMsg, sMsg);
                // NFCL2760Scrn00_OpenWin_Upload
            } else if ("NFCL2760Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_PageJump(cMsg, sMsg);
                // NFCL2760Scrn00_CMN_Return
            } else if ("NFCL2760Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_CMN_Return(cMsg, sMsg);
                // NFCL2760Scrn00_AddOneLine
            } else if ("NFCL2760Scrn00_AddOneLine".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_AddOneLine(cMsg, sMsg);
                // NFCL2760Scrn00_AddFiveLine
            } else if ("NFCL2760Scrn00_SearchTrxLine".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_SearchTrxLine(cMsg, sMsg);
                // NFCL2760Scrn00_SearchTrxAllLine
            } else if ("NFCL2760Scrn00_OpenWin_New".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_OpenWin_New(cMsg, sMsg);
                // NFCL2760Scrn00_OpenWin_Select
            } else if ("NFCL2760Scrn00_OpenWin_Select".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_OpenWin_Select(cMsg, sMsg);
            } else if ("NFCL2760Scrn00_OnClick_Chk".equals(screenAplID)) {
                // START 2018/06/06 Y.Matsui [QC#25737,MOD]
                // doProcess_NFCL2760Scrn00_CalcGrossAmount(cMsg, sMsg);
                doProcess_NFCL2760Scrn00_OnClick_Chk(cMsg, sMsg);
                // END   2018/06/06 Y.Matsui [QC#25737,MOD]
            // START 2020/01/20 H.Ikeda [QC#54902,ADD]
            } else if ("NFCL2760Scrn00_OnBlur_Inv".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_OnBlur_Inv(cMsg, sMsg);
            } else if ("NFCL2760Scrn00_Search".equals(screenAplID)) {
                doProcess_NFCL2760Scrn00_Search(cMsg, sMsg);
            // END   2020/01/20 H.Ikeda [QC#54902,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760_INIT================================START", this);
        EZDDebugOutput.println(1, "doProcess_NFCL2760_INIT===START_TIME:" + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"), this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        // START 2022/02/22 D.Mamaril [QC#59733, ADD]
        boolean newInvProcess = false;
        if ("NFCL2760Scrn00_CMN_Submit".equals(bizMsg.getScreenAplID())) {
            newInvProcess = hasProcess(globalMsg);
        }
        // END 2022/02/22 D.Mamaril [QC#59733, ADD]

        globalMsg.clear();
        ZYPTableUtil.clear(globalMsg.A);
        ZYPTableUtil.clear(globalMsg.B);
        ZYPTableUtil.clear(globalMsg.C);
        bizMsg.setCommitSMsg(true);

        String mode = bizMsg.xxModeInd_H1.getValue();

        if (!MODE_ENTRY.equals(mode) && !MODE_CANCEL.equals(mode) && !MODE_ONE.equals(mode)) {
            bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_N);
            bizMsg.setMessageInfo("NFCM0031E", null);
            return;
        } else {
            // do nothing
        }

        if (MODE_ENTRY.equals(mode)) {
            bizMsg.xxModeNm23Txt.setValue(MODE_NAME_ENTRY);
        } else if (MODE_CANCEL.equals(mode)) {
            bizMsg.xxModeNm23Txt.setValue(MODE_NAME_CANCEL);
        } else {
            bizMsg.xxModeNm23Txt.setValue(MODE_NAME_ONE);
        }

        String glblCmpyCd = getGlobalCompanyCode();
        bizMsg.glblCmpyCd_H1.setValue(glblCmpyCd);

        if (!checkReceiptNumber(bizMsg)) {
            bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_N);
            return;
        } else {
            // do nothing
        }

        // Receipt Transaction Type Name
        bizMsg.arRcptTrxTpNm.setValue(ZYPCodeDataUtil.getName(AR_RCPT_TRX_TP_PRM_NM, glblCmpyCd, bizMsg.arRcptTrxTpCd.getValue()));

        // Receipt Type Name
        bizMsg.arRcptTpNm.setValue(ZYPCodeDataUtil.getName(AR_RCPT_TP_PRM_NM, glblCmpyCd, bizMsg.arRcptTpCd.getValue()));

        // Payer Name
        //NFCL2760CommonLogic.findBillToCustList(bizMsg, new BILL_TO_CUSTTMsg());
        NFCL2760CommonLogic.setDsAcctNm(bizMsg);

        Map<String, String> inMap = new HashMap<String, String>();
        inMap.put("GLBL_CMPY_CD", bizMsg.glblCmpyCd_H1.getValue());
        inMap.put("AR_CASH_APP_MAN_ENTRY_TP_CD", "2");
        AR_ADJ_TPTMsgArray outArAdjTPMsg = (AR_ADJ_TPTMsgArray) ZYPCodeDataUtil.findByCondition(AR_ADJ_TP_PRM_NM, inMap);

        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "arAdjTpCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "arAdjTpCd");
        ZYPPulldownValueSetter.set(outArAdjTPMsg, tMsgKeys, bizMsg.arAdjTpCd_B1, bizMsg.arAdjTpCd_B2);

        if (MODE_CANCEL.equals(mode) || MODE_ONE.equals(mode)) {

            NFCL2760CommonLogic.findArAcctDtInfo(bizMsg, new AR_ACCT_DTTMsg());

            // START 2022/02/22 D.Mamaril [QC#59733, MOD]
            //ArrayList invData = getInvData(globalMsg, bizMsg);
            ArrayList invData = getInvData(globalMsg, bizMsg, newInvProcess);
            // END 2022/02/22 D.Mamaril [QC#59733, MOD]
            ArrayList adjAndDedData = getAdjAndDeduction(globalMsg, bizMsg);
            ArrayList sortList = getSortDara((ArrayList) invData.get(1), (ArrayList) adjAndDedData.get(1), globalMsg, bizMsg);

            int vCount = 0;
            for (; vCount < sortList.size(); vCount++) {
                if (vCount == globalMsg.A.length()) {
                    break;
                } else {
                    EZDMsg.copy((NFCL2760_ASMsg) sortList.get(vCount), null, globalMsg.A.no(vCount), null);
                    NFCL2760CommonLogic.setDiff(globalMsg.A.no(vCount), MODE_CANCEL);
                }
            }

            globalMsg.A.setValidCount(vCount);

            if (0 != globalMsg.A.getValidCount()) {

                int queryResCnt = ((Integer) invData.get(0)).intValue() + ((Integer) adjAndDedData.get(0)).intValue();
                if (queryResCnt > globalMsg.A.length()) {
                    bizMsg.setMessageInfo("NFCM0110E");
                    queryResCnt = globalMsg.A.length();
                } else {
                    // do nothing
                }

                for (int index = 0; index < globalMsg.A.getValidCount(); index++) {

                    setScaleOfAmt(globalMsg.A.no(index));

                    globalMsg.A.no(index).xxChkBox.clear();
                    // START 2022/01/06 G.Delgado [QC#59329, ADD]
                    if (!RECORD_STS.NEW.getValue().equals(globalMsg.A.no(index).xxArCashApplyStsTxt.getValue())) {
                    // END 2022/01/06 G.Delgado [QC#59329, ADD]
                        globalMsg.A.no(index).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
                        globalMsg.A.no(index).xxArCashApplyStsTxt.setValue(RECORD_STS.APPLIED.getValue());
                    // START 2022/01/06 G.Delgado [QC#59329, ADD]
                    }
                    // END 2022/01/06 G.Delgado [QC#59329, ADD]

                    BigDecimal dealApplyCrAmt = BigDecimal.ZERO;
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(index).dealApplyCrAmt)) {
                        dealApplyCrAmt = dealApplyCrAmt.add(globalMsg.A.no(index).dealApplyCrAmt.getValue());
                    }
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(index).dealApplyAdjAmt_B3)) {
                        dealApplyCrAmt = dealApplyCrAmt.add(globalMsg.A.no(index).dealApplyAdjAmt_B3.getValue());
                    }

                    globalMsg.A.no(index).dealApplyCrAmt.setValue(dealApplyCrAmt);
                    globalMsg.A.no(index).xxDealApplyAmtNum_A1.setValue(NFCL2760CommonLogic.getNewScale(globalMsg.A.no(index).dealApplyAmt_BK.getValue()));
                    globalMsg.A.no(index).xxDealApplyAdjAmtNum_A1.setValue(NFCL2760CommonLogic.getNewScale(globalMsg.A.no(index).dealApplyAdjAmt_BK.getValue()));
                    globalMsg.A.no(index).arCustRefNum_BK.setValue(globalMsg.A.no(index).arCustRefNum.getValue());

                    NFCL2760CommonLogic.setDiff(globalMsg.A.no(index), MODE_CANCEL);
                    NFCL2760CommonLogic.setClearItemBySMsg(globalMsg.A.no(index));
                }

                NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, 0, 0);

                bizMsg.xxPageShowFromNum.setValue(1);
                bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
                bizMsg.xxPageShowOfNum.setValue(queryResCnt);

            } else {
                //bizMsg.setMessageInfo("NZZM0000E");
                bizMsg.xxPageShowFromNum.clear();
                bizMsg.xxPageShowToNum.clear();
                bizMsg.xxPageShowOfNum.clear();
                // START 2018/08/02 Y.Matsui [QC#26985-1, MOD]
                bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_Y);
                // END   2018/08/02 Y.Matsui [QC#26985-1, MOD]
                return;
            }
            NFCL2760CommonLogic.findArAcctDtInfo(bizMsg, new AR_ACCT_DTTMsg());
        } else {
            // do nothing
        }

        bizMsg.dealRcptAmt.setValue(NFCL2760CommonLogic.getNewScale(bizMsg.dealRcptAmt.getValue()));
        bizMsg.dealApplyAmt_H1.setValue(NFCL2760CommonLogic.getNewScale(bizMsg.dealApplyAmt_H1.getValue()));
        bizMsg.dealApplyAdjAmt_H1.setValue(NFCL2760CommonLogic.getNewScale(bizMsg.dealApplyAdjAmt_H1.getValue()));
        bizMsg.dealRfAmt.setValue(NFCL2760CommonLogic.getNewScale(bizMsg.dealRfAmt.getValue()));
        bizMsg.dealRcptRmngBalAmt.setValue(NFCL2760CommonLogic.getNewScale(bizMsg.dealRcptRmngBalAmt.getValue()));

        NFCL2760CommonLogic.setApplyGrsAmt(globalMsg, bizMsg, blIni, ROW_STS.ALL.toString());

        bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_Y);

        // make cash app GL Date
        AR_ACCT_DTTMsg outArAcctDtMsg = NFCL2760CommonLogic.findArAcctDtInfo(bizMsg, new AR_ACCT_DTTMsg());

        if (null == outArAcctDtMsg || !RTNCD_NORMAL.equals(outArAcctDtMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0041E", null);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_N);
            return;
        }

        String depositDate = bizMsg.glDt_H1.getValue();
        String depositYM = NFCL2760CommonLogic.getYearMonth(depositDate);
        String acctYM = NFCL2760CommonLogic.getYearMonth(outArAcctDtMsg.acctDt.getValue());
        String batProcDt = ZYPDateUtil.getBatProcDate(bizMsg.glblCmpyCd_H1.getValue());
        String batProcYM = NFCL2760CommonLogic.getYearMonth(batProcDt);
        String preBatProcYM = NFCL2760CommonLogic.getYearMonth(NFCL2760CommonLogic.getBeforeMonth(batProcDt, "yyyyMMdd"));

        if (acctYM.equals(batProcYM)) {

            if (!batProcYM.equals(depositYM)) {
                bizMsg.cashAppGlDt_BK.setValue(outArAcctDtMsg.acctDt.getValue());
            } else {
                bizMsg.cashAppGlDt_BK.setValue(depositDate);
            }
        } else {

            if (!batProcYM.equals(depositYM) && !preBatProcYM.equals(depositYM)) {
                bizMsg.cashAppGlDt_BK.setValue(outArAcctDtMsg.acctDt.getValue());
            } else {
                bizMsg.cashAppGlDt_BK.setValue(depositDate);
            }
        }

        S21UserInfo userInfo = getContextUserInfo();
        NFCL2760CommonLogic.startCustCdLock(bizMsg, userInfo);

        setAftDeclPntDigitNum(bizMsg);

        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL2760_INIT================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760_NFCL5050(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760_NFCL5050================================START", this);
        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        // START 2020/01/29 H.Ikeda [QC#54902,ADD]
        int selectLine = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        // END   2020/01/29 H.Ikeda [QC#54902,ADD]
        
        if (CMN_CLOSE.equals(bizMsg.xxCtlNm_H1.getValue())) {
            int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
            NFCL2760CommonLogic.checkArCustRefNumAll(globalMsg);
            NFCL2760CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
            return;
        }

        // del start 2022/01/26 QC#59641
        //if (globalMsg.A.getValidCount() == globalMsg.A.length()) {
        //    bizMsg.setMessageInfo("NFCM0110E", null);
        //    return;
        //}
        // del end 2022/01/26 QC#59641

        if (ZYPCommonFunc.hasValue(bizMsg.grpInvNum_H1.getValue())) {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (globalMsg.A.no(i).grpInvNum.getValue().equals(bizMsg.grpInvNum_H1.getValue())) {
                    bizMsg.setMessageInfo("NFCM0163E", null);
                    return;
                }
            }
        }

        int returnValueCount = 0;

        for (int i = 0; i < bizMsg.arTrxBalPk_B1.length(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.arTrxBalPk_B1.no(i).getValue())) {
                returnValueCount = i + 1;
            } else {
                break;
            }
        }

        // START 2018/07/20 Y.Matsui [QC#26985, MOD]
        int currentDataCount = 0;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!isBlankLine(globalMsg.A.no(i))) {
                currentDataCount++;
            }
        }
        int allDataCount = currentDataCount + returnValueCount;
        // END   2018/07/20 Y.Matsui [QC#26985, MOD]

        if (MAX_LINE_DISPLAY < allDataCount) {
            bizMsg.setMessageInfo("NFCM0110E", null);
            return;
        }

        if (AR_ADJ_TRX_TP.DEDUCTION.equals(bizMsg.arTrxTpCd_P3.getValue())) {

            NFCL2760SMsg cloneMsg = (NFCL2760SMsg) globalMsg.clone();

            ArrayList adjAndDedData = getAdjAndDeduction(cloneMsg, bizMsg);
            ArrayList adjAndDedDataList = (ArrayList) adjAndDedData.get(1);

            for (int i = 0; i < adjAndDedDataList.size(); i++) {

                NFCL2760_ASMsg adjAndDedSMsg = (NFCL2760_ASMsg) adjAndDedDataList.get(i);

                if (AR_ADJ_TRX_TP.DEDUCTION.equals(adjAndDedSMsg.arAdjTrxTpCd_BK.getValue())) {

                    if (bizMsg.arTrxNum_P3.getValue().equals(adjAndDedSMsg.arTrxNum.getValue())) {
                        bizMsg.setMessageInfo("NFCM0113E", null);
                        return;
                    }
                }
            }
        }

        NFCL2760CommonLogic.setPage(globalMsg, bizMsg);

        Map<String, Object> inFindArTrxBalMap = NFCL2760CommonLogic.setfindArTrxBal(bizMsg);

        globalMsg.B.clear();

        S21SsmEZDResult outTrxBalData = NFCL2760Query.getInstance().findArTrxBal(inFindArTrxBalMap, globalMsg);
        if (outTrxBalData.isCodeNotFound()) {
            bizMsg.setMessageInfo("NFCM0059E", null);
        }

        int dataCount = 0;
        int index = 0;
        int selectIndex = bizMsg.xxCellIdx_H1.getValueInt();
        ArrayList<Integer> listAddRow = new ArrayList<Integer>();

        if (outTrxBalData.isCodeNormal()) {

            int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());

            setScaleOfAmt(globalMsg.A.no(pagenationFrom + selectIndex));

            dataCount = outTrxBalData.getQueryResultCount();
            for (int i = 0; i < outTrxBalData.getQueryResultCount(); i++) {
                if ( i == 0 ) {
                    //Set to Selected Row
                    index = pagenationFrom + selectIndex;
                    listAddRow.add(index);
                    dataCount = globalMsg.A.getValidCount();
                } else {
                    // START 2018/06/06 Y.Matsui [QC#25369,MOD]
                    //Set to Additional Row
                    // index = globalMsg.A.getValidCount() -1 + i;
                    // listAddRow.add(index);
                    // dataCount = globalMsg.A.getValidCount() + i;
                    index = pagenationFrom + selectIndex + i;
                    // mod start 2022/01/26 QC#59641
                    //for (; index <= globalMsg.A.length(); index++) {
                    for (; index < globalMsg.A.length(); index++) {
                    // mod end 2022/01/26 QC#59641
                        if (isBlankLine(globalMsg.A.no(index))) {
                            break;
                        }
                    }
                    // add start 2022/01/26 QC#59641
                    if (index >= globalMsg.A.length()) {
                        bizMsg.setMessageInfo("NFCM0110E", null);
                        return;
                    }
                    // add end 2022/01/26 QC#59641
                    globalMsg.A.no(index).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
                    listAddRow.add(index);
                    if (globalMsg.A.getValidCount() <= index) {
                        dataCount = index + 1;
                    }
                    // END   2018/06/06 Y.Matsui [QC#25369,MOD]
                }

                globalMsg.A.no(index).xxChkBox.clear();
                globalMsg.A.no(index).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);

                NFCL2760CommonLogic.copyResultMsg(globalMsg, index, i);

                BigDecimal dealApplyGrsAmt = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).dealApplyGrsAmt_B2)) {
                    dealApplyGrsAmt = globalMsg.B.no(i).dealApplyGrsAmt_B2.getValue();
                }

                BigDecimal dealApplyCrAmt = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).dealApplyCrAmt_B2)) {
                    dealApplyCrAmt = globalMsg.B.no(i).dealApplyCrAmt_B2.getValue();
                }

                BigDecimal dealApplyAdjAmtA3 = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).dealApplyAdjAmt_B3)) {
                    dealApplyAdjAmtA3 = globalMsg.B.no(i).dealApplyAdjAmt_B3.getValue();
                }

                BigDecimal dealOrigGrsAmt = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).dealOrigGrsAmt_B2)) {
                    dealOrigGrsAmt = globalMsg.B.no(i).dealOrigGrsAmt_B2.getValue();
                }

                BigDecimal dealRmngBalGrsAmt = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).dealRmngBalGrsAmt_B2)) {
                    dealRmngBalGrsAmt = globalMsg.B.no(i).dealRmngBalGrsAmt_B2.getValue();
                } else {
                    // do nothing
                }

                String arCashApplyStsCd = "";
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).arCashApplyStsCd_B2)) {
                    arCashApplyStsCd = globalMsg.B.no(i).arCashApplyStsCd_B2.getValue();
                } else {
                    // do nothing
                }

                if (NFCL2760CommonLogic.isCashDiscSchd(dealApplyGrsAmt, dealApplyCrAmt, dealApplyAdjAmtA3, dealOrigGrsAmt, dealRmngBalGrsAmt, arCashApplyStsCd)) {

                    BigDecimal dealApplyAmt = BigDecimal.ZERO;
                    if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).dealCashDiscAmt_B2)) {
                        dealApplyAmt = dealApplyAmt.add(dealRmngBalGrsAmt).subtract(globalMsg.B.no(i).dealCashDiscAmt_B2.getValue());
                    } else {
                        dealApplyAmt = dealApplyAmt.add(dealRmngBalGrsAmt);
                    }

                    globalMsg.A.no(index).xxDealApplyAmtNum_A1.setValue(dealApplyAmt);

                } else {
                    globalMsg.A.no(index).dealCashDiscAmt_A1.clear();
                    globalMsg.A.no(index).cashDiscPct_A1.clear();
                    globalMsg.A.no(index).xxDealApplyAmtNum_A1.setValue(dealRmngBalGrsAmt);
                }

                BigDecimal others = BigDecimal.ZERO;
                others = others.add(dealApplyCrAmt).add(dealApplyAdjAmtA3);
                globalMsg.A.no(index).dealApplyCrAmt.setValue(others);

                globalMsg.A.no(index).cashAppGlDt_A1.setValue(bizMsg.cashAppGlDt_BK.getValue());

                if (ZYPCommonFunc.hasValue(globalMsg.A.no(index).invPmtMethCd_A1)) {
                    String outConcatString = ZYPCommonFunc.concatString(globalMsg.A.no(index).invPmtMethCd_A1.getValue(), ":", globalMsg.A.no(index).arExpPmtMethNm_A1.getValue());
                    globalMsg.A.no(index).xxShpgMethTpTxt_A1.setValue(outConcatString);
                }

                if (ZYPCommonFunc.hasValue(globalMsg.A.no(index).invPmtCondCd_A1)) {
                    String outConcatString = ZYPCommonFunc.concatString(globalMsg.A.no(index).invPmtCondCd_A1.getValue(), ":", globalMsg.A.no(index).arExpPmtCondNm_A1.getValue());
                    globalMsg.A.no(index).xxShpgMethTpTxt_A2.setValue(outConcatString);
                }

                globalMsg.A.no(index).xxPgFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
                globalMsg.A.no(index).xxPgFlg_A2.setValue(ZYPConstant.FLG_ON_Y);
                globalMsg.A.no(index).xxPgFlg_A3.setValue(ZYPConstant.FLG_OFF_N);

                globalMsg.A.no(index).arCustRefNum_BK.setValue(globalMsg.A.no(index).arCustRefNum.getValue());

                NFCL2760CommonLogic.setDiff(globalMsg.A.no(index), MODE_ENTRY);
                NFCL2760CommonLogic.setClearItemBySMsg(globalMsg.A.no(index));
            }
        }

        globalMsg.A.setValidCount(dataCount);

        NFCL2760CommonLogic.checkArCustRefNumAll(globalMsg);

        NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, globalMsg.A.getValidCount());

        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);
        NFCL2760CommonLogic.setCalcFor5050(bizMsg, globalMsg, listAddRow);

        // START 2020/01/29 H.Ikeda [QC#54902,MOD]
        //int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        int pagenationFrom = selectLine;
        // END   2020/01/29 H.Ikeda [QC#54902,MOD]
        NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        // add start 2022/01/26 QC#59641
        if ((globalMsg.A.getValidCount() % bizMsg.A.length()) > 0) {
            addInitDetail(cMsg, sMsg);
        }
        // add end 2022/01/26 QC#59641

        EZDDebugOutput.println(1, "doProcess_NFCL2760_NFCL5050================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_DownloadCSV(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_DownloadCSV================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        NFCL2760CommonLogic.setPage(globalMsg, bizMsg);

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("NFCL2760"), CSV);

        NFCL2760F00FMsg fMsg = new NFCL2760F00FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(NFCL2760CommonLogic.getCsvHeader());
        globalMsg.C.clear();
        // START 2016/09/06 J.Kim [QC#13793,MOD]
        // globalMsg.C.setValidCount(globalMsg.A.getValidCount());

        S21SsmEZDResult outRcptData = NFCL2760Query.getInstance().findDownLoadInfoData(bizMsg, globalMsg);

        if (RTNCD_NOT_FOUND.equals(outRcptData.getResultCode())) {
            bizMsg.setMessageInfo("NFCM0053E");
            return;
        }
        // END 2016/09/06 J.Kim [QC#13793,MOD]

        for (int i = 0; i < globalMsg.C.getValidCount(); i++) {
            fMsg.clear();
            NFCL2760CommonLogic.copyCSVDown(bizMsg, globalMsg, i);

            EZDMsg.copy(globalMsg.C.no(i), null, fMsg, null);
            // START 2016/09/06 J.Kim [QC#13793,MOD]
            // if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).invPmtMethCd_A1.getValue())) {
            //    String outConcatString = ZYPCommonFunc.concatString(globalMsg.A.no(i).invPmtMethCd_A1.getValue(), ":", globalMsg.A.no(i).arExpPmtMethNm_A1.getValue());
            //    fMsg.xxScrItem30Txt_A1.setValue(outConcatString);
            // } else {
            //    fMsg.xxScrItem30Txt_A1.clear();
            // }
            //
            // if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).invPmtCondCd_A1.getValue())) {
            //    String outConcatString = ZYPCommonFunc.concatString(globalMsg.A.no(i).invPmtCondCd_A1.getValue(), ":", globalMsg.A.no(i).arExpPmtCondNm_A1.getValue());
            //    fMsg.xxScrItem30Txt_A2.setValue(outConcatString);
            // } else {
            //    fMsg.xxScrItem30Txt_A2.clear();
            // }

            if (ZYPCommonFunc.hasValue(globalMsg.C.no(i).invPmtMethCd_C1)) {
                String outConcatString = ZYPCommonFunc.concatString(globalMsg.C.no(i).invPmtMethCd_C1.getValue(), ":", globalMsg.C.no(i).arExpPmtMethNm_C1.getValue());
                fMsg.xxScrItem30Txt_C1.setValue(outConcatString);
            } else {
                fMsg.xxScrItem30Txt_C1.clear();
            }

            if (ZYPCommonFunc.hasValue(globalMsg.C.no(i).invPmtCondCd_C1)) {
                String outConcatString = ZYPCommonFunc.concatString(globalMsg.C.no(i).invPmtCondCd_C1.getValue(), ":", globalMsg.C.no(i).arExpPmtCondNm_C1.getValue());
                fMsg.xxScrItem30Txt_C2.setValue(outConcatString);
            } else {
                fMsg.xxScrItem30Txt_C2.clear();
            }
            // END 2016/09/06 J.Kim [QC#13793,MOD]

            csvOutFile.write();
        }

        csvOutFile.close();

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_DownloadCSV================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_PagePrev================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        if (!SCRN_MODE_CANCEL.equals(bizMsg.xxModeInd_H1.getValue())) {
            boolean ret = NFCL2760CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
            if (!ret) {
                NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                return;
            }
        }

        pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt()) - bizMsg.A.length();
        NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);
        
        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_PagePrev================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_PageNext================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        if (pagenationFrom != -1) {
            NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            if (!SCRN_MODE_CANCEL.equals(bizMsg.xxModeInd_H1.getValue())) {
                boolean ret = NFCL2760CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
                if (!ret) {
                    NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }
            }
        }

        pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt()) + bizMsg.A.length();
        NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
        
        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_PageNext================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_PageJump================================START", this);
        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        // START 2018/07/20 Y.Matsui [QC#26985, MOD]
        int xxPageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt();
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum_H1.getValueInt());

        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(xxPageShowFromNum);
        if (pagenationFrom != -1) {
            NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            if (!SCRN_MODE_CANCEL.equals(bizMsg.xxModeInd_H1.getValue())) {
                boolean ret = NFCL2760CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
                if (!ret) {
                    NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }
            }
        }
        // END   2018/07/20 Y.Matsui [QC#26985, MOD]

        // copy data from SMsg onto CMsg
        pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to page nation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
        
        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);
        
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_PageJump================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_CMN_Return(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_CMN_Return================================START", this);

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_CMN_Return================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_CalcGrossAmount(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_CalcGrossAmount================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;
        bizMsg.setCommitSMsg(true);

        BigDecimal balance = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if ( ZYPCommonFunc.hasValue(bizMsg.A.no(i).dealRmngBalGrsAmt) ) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxDealApplyAmtNum_A1)
                        // START 2021/06/21 H.Dimay [QC#58639, ADD]
                        && !bizMsg.A.no(i).arAdjTrxTpCd_BK.equals(NFCConst.CST_AR_ADJ_TRX_TP_CD_REFUND)) {
                    // END 2021/06/21 H.Dimay [QC#58639, ADD]
                    balance = balance.add(bizMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
                }
            } else {
                bizMsg.A.no(i).dealRmngBalGrsAmt.setValue(BigDecimal.ZERO);
            }
        }
        // Calculation
        //bizMsg.xxTotAmt_H0.setValue(bizMsg.dealRcptAmt.getValue().subtract(balance));

        NFCL2760CommonLogic.setPage(globalMsg, bizMsg);

        for (int iCnt=0; iCnt<globalMsg.A.getValidCount(); iCnt++) {
            //
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(iCnt).xxChkBox) || globalMsg.A.no(iCnt).xxChkBox.getValue().equals("Y")) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(iCnt).xxDealApplyAmtNum_A1)) {
                    if ( globalMsg.A.no(iCnt).arTrxTpCd.getValue().equals(NFCConst.CST_AR_TRX_TP_CD_INVOICE)
                           || globalMsg.A.no(iCnt).arTrxTpCd.getValue().equals(NFCConst.CST_AR_TRX_TP_CD_DEBITMEMO)
                           || globalMsg.A.no(iCnt).arTrxTpCd.getValue().equals(NFCConst.CST_AR_TRX_TP_CD_DEDUCTION)) {
                        balance = balance.subtract(globalMsg.A.no(iCnt).xxDealApplyAmtNum_A1.getValue());
                    // START 2021/06/21 H.Dimay [QC#58639, MOD]
                    //} else {
                    } else if (!(globalMsg.A.no(iCnt).arAdjTrxTpCd_BK.getValue().equals(NFCConst.CST_AR_ADJ_TRX_TP_CD_REFUND))) {
                    // END 2021/06/21 H.Dimay [QC#58639, MOD]
                        balance = balance.add(globalMsg.A.no(iCnt).xxDealApplyAmtNum_A1.getValue());
                    }

                    // START 2016/08/26 T.Tsuchida [QC#18383,MOD]
                    //if ( !globalMsg.A.no(iCnt).dealRmngBalGrsAmt.getValue().equals(BigDecimal.ZERO) ) {
                    if ( ZYPCommonFunc.hasValue(globalMsg.A.no(iCnt).dealRmngBalGrsAmt)
                            && BigDecimal.ZERO.compareTo(globalMsg.A.no(iCnt).dealRmngBalGrsAmt.getValue()) != 0 ) {
                    // END 2016/08/26 T.Tsuchida [QC#18383,MOD]
                        globalMsg.A.no(iCnt).xxDtlDiffAmt_A1.setValue(globalMsg.A.no(iCnt).dealRmngBalGrsAmt.getValue().subtract(globalMsg.A.no(iCnt).xxDealApplyAmtNum_A1.getValue())); 
                    } else {
                        globalMsg.A.no(iCnt).xxDtlDiffAmt_A1.setValue(BigDecimal.ZERO);
                    }
                }
            }
        }
        
        //Calculation
        if (!ZYPCommonFunc.hasValue(bizMsg.dealRcptAmt)) {
            bizMsg.dealRcptAmt.setValue(BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.dealRfAmt)) {
            bizMsg.dealRfAmt.setValue(BigDecimal.ZERO);
        }
        bizMsg.xxTotAmt_H1.setValue(bizMsg.dealRcptAmt.getValue().subtract(balance).subtract(bizMsg.dealRfAmt.getValue()));

        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());

        pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
        
        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_CalcGrossAmount================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_NFCL5140(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_NFCL5140================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        if (CMN_CLOSE.equals(bizMsg.xxCtlNm_H1.getValue())) {
            int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
            NFCL2760CommonLogic.checkArCustRefNumAll(globalMsg);
            NFCL2760CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
            return;
        }

        // START 2018/07/19 Y.Matsui [QC#26871, MOD]
        int index = 0;
        for (; index <= globalMsg.A.getValidCount(); index++) {
            if (isBlankLine(globalMsg.A.no(index))) {
                break;
            }
        }
        // END   2018/07/19 Y.Matsui [QC#26871, MOD]

        if (index >= globalMsg.A.length()) {
            bizMsg.setMessageInfo("NFCM0110E", null);
            return;
        } else {
            // do nothing
        }

        NFCL2760CommonLogic.setPage(globalMsg, bizMsg);

        int i = 0;
        for (; i < bizMsg.xxModeInd_P1.length(); i++) {
            if (null != bizMsg.xxModeInd_P1.no(i) && ZYPCommonFunc.hasValue(bizMsg.xxModeInd_P1.no(i))) {
                globalMsg.A.no(index + i).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
                globalMsg.A.no(index + i).xxModeInd_BK.setValue(bizMsg.xxModeInd_P1.no(i).getValue());
                globalMsg.A.no(index + i).xxArCashApplyStsTxt.setValue(RECORD_STS.NEW.getValue());

                if (DETAIL_MODE_DEDUCTION.equals(bizMsg.xxModeInd_P1.no(i).getValue())) {
                    globalMsg.A.no(index + i).arTrxTpCd.setValue(AR_ADJ_TRX_TP.DEDUCTION);
                } else if (DETAIL_MODE_ADJUSTMENT.equals(bizMsg.xxModeInd_P1.no(i).getValue())) {
                    globalMsg.A.no(index + i).arTrxTpCd.setValue(AR_ADJ_TRX_TP.ADJUSTMENT);
                } else if (DETAIL_MODE_ONACOUNT.equals(bizMsg.xxModeInd_P1.no(i).getValue())) {
                    globalMsg.A.no(index + i).arTrxTpCd.setValue(AR_TRX_TP.ON_ACCOUNT);
                } else {
                    // do nothing
                }

                globalMsg.A.no(index + i).xxDealApplyAmtNum_A1.setValue(bizMsg.dealApplyAmt_P1.no(i).getValue().setScale(2, BigDecimal.ROUND_DOWN));
                globalMsg.A.no(index + i).arCustRefNum.setValue(bizMsg.arCustRefNum_P1.no(i).getValue());
                globalMsg.A.no(index + i).arCustRefNum_BK.setValue(bizMsg.arCustRefNum_P1.no(i).getValue());
                globalMsg.A.no(index + i).arAdjTpCd_A3.setValue(bizMsg.arAdjTpCd_P1.no(i).getValue());
                // START 2017/01/11 T.Murai [QC#16952,ADD]
                String tpNm = NFCL2760CommonLogic.getArAdjTpNm(bizMsg, bizMsg.arAdjTpCd_P1.no(i).getValue());

                // MOD 2017/02/15 [QC#17624]
                // globalMsg.A.no(index + i).arAdjTpNm_A3.setValue(tpNm);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index + i).arAdjTpNm_A3, tpNm);

                // END   2017/01/11 T.Murai [QC#16952,ADD]
                globalMsg.A.no(index + i).cashAppGlDt_A1.setValue(bizMsg.cashAppGlDt_BK.getValue());
                globalMsg.A.no(index + i).adjCmntTxt_BK.setValue(bizMsg.adjCmntTxt_P1.no(i).getValue());
                globalMsg.A.no(index + i).tocCd_A1.setValue(bizMsg.tocCd_P1.no(i).getValue());
                globalMsg.A.no(index + i).coaProdCd_BK.setValue(bizMsg.coaProdCd_P1.no(i).getValue());
                // START 2022/11/28 R.Takau [QC#57272,ADD]
                globalMsg.A.no(index + i).xxCmntTxt_A1.setValue(bizMsg.xxCmntTxt_P1.no(i).getValue());
                // END 2022/11/28 R.Takau [QC#57272,ADD]
                globalMsg.A.no(index + i).xxPgFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
                globalMsg.A.no(index + i).xxPgFlg_A2.setValue(ZYPConstant.FLG_OFF_N);
                globalMsg.A.no(index + i).xxPgFlg_A3.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                break;
            }
         }

        // START 2018/07/19 Y.Matsui [QC#26871, MOD]
        if (globalMsg.A.getValidCount() <= index + i) {
            globalMsg.A.setValidCount(index + i);
        }
        // END   2018/07/19 Y.Matsui [QC#26871, MOD]

        NFCL2760CommonLogic.checkArCustRefNumAll(globalMsg);

        NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, globalMsg.A.getValidCount());
        
        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);

        // add start 2022/01/26 QC#59641
        if ((globalMsg.A.getValidCount() % bizMsg.A.length()) > 0) {
            addInitDetail(cMsg, sMsg);
        }
        // add end 2022/01/26 QC#59641

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_NFCL5140================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_DeleteLines(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_DeleteLines================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        // NFCL2760CommonLogic.setPage(globalMsg, bizMsg);
        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL2760CommonLogic.deleteBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        int validCount = globalMsg.A.getValidCount();
        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(globalMsg.A, XX_CHK_BOX, ZYPConstant.CHKBOX_ON_Y);
        int outDeleteRows = ZYPTableUtil.deleteRows(globalMsg.A, outGetSelectedRows);
        globalMsg.A.setValidCount(validCount - outDeleteRows);

        pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        if (pagenationFrom > 1 && outDeleteRows == bizMsg.A.getValidCount()) {
            pagenationFrom = pagenationFrom - bizMsg.A.length();
        } else {
            // do nothing.
        }
        NFCL2760CommonLogic.setPageData(globalMsg, bizMsg, pagenationFrom);

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_DeleteLines================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_NFCL5040(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_NFCL5040================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        int index = pagenationFrom + Integer.parseInt(bizMsg.xxSelNum.getValue());

        globalMsg.A.no(index).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
        globalMsg.A.no(index).dealCashDiscAmt_A1.setValue(NFCL2760CommonLogic.getNewScale(bizMsg.dealCashDiscAmt_P2.getValue()));
        globalMsg.A.no(index).cashDiscPct_A1.setValue(bizMsg.cashDiscPct_P2.getValue());
        globalMsg.A.no(index).arCashDiscSchdSqNum_BK.setValue(bizMsg.arCashDiscSchdSqNum_P2.getValue());

        BigDecimal cashDiscDetail = NFCL2760CommonLogic.getNewScale(globalMsg.A.no(index).dealCashDiscAmt_A1.getValue());
        BigDecimal trxAdjDetail = NFCL2760CommonLogic.getNewScale(globalMsg.A.no(index).xxDealApplyAdjAmtNum_A1.getValue());
        // Apply Auto calculation
        BigDecimal balanceDetail = NFCL2760CommonLogic.getNewScale(globalMsg.A.no(index).dealRmngBalGrsAmt.getValue());

        BigDecimal applyDetail = balanceDetail.subtract(cashDiscDetail).subtract(trxAdjDetail);
        globalMsg.A.no(index).xxDealApplyAmtNum_A1.setValue(applyDetail);

        NFCL2760CommonLogic.setClearItemBySMsg(globalMsg.A.no(index));

        NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
        
        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_NFCL5040================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_CMN_Submit================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        if (MODE_ONE.equals(bizMsg.xxModeInd_H1.getValue()) || MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {

            if (NFCL2760CommonLogic.checkArCustRefNumAll(globalMsg)) {

                AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
                for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

                    String xxModeInd = globalMsg.A.no(i).xxModeInd_BK.getValue();

                    if (DETAIL_MODE_DEDUCTION.equals(xxModeInd) || DETAIL_MODE_ADJUSTMENT.equals(xxModeInd) || DETAIL_MODE_ONACOUNT.equals(xxModeInd)) {

                        inArApplyIntfcWrkMsg.clear();
                        inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
                        inArApplyIntfcWrkMsg.applyGrpKey.setValue(globalMsg.A.no(i).applyGrpKey_BK.getValue());
                        inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(globalMsg.A.no(i).applyGrpSubPk_BK.getValue());

                        AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFCL2760CommonLogic.findArApplyIntfcWrkInfo(inArApplyIntfcWrkMsg);

                        if (null != outArApplyIntfcWrkMsg && RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
                            if (DETAIL_MODE_DEDUCTION.equals(xxModeInd) || DETAIL_MODE_ONACOUNT.equals(xxModeInd)) {
                                globalMsg.A.no(i).arTrxNum.setValue(outArApplyIntfcWrkMsg.invNum.getValue());

                            } else {
                                globalMsg.A.no(i).arTrxNum.setValue(outArApplyIntfcWrkMsg.arAdjNum.getValue());
                            }
                        } else {
                            // do nothing
                        }
                    } else {
                        // do nothing
                    }
                }
                EZDMsg.copy(globalMsg, null, bizMsg, null);
            } else {
                // do nothing
            }

        } else {
            // do nothing
        }
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_CMN_Submit================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_OpenWin_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_OpenWin_Search================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;
        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());

        if (pagenationFrom > -1) {
            NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            NFCL2760CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
        }

        bizMsg.C.clear();

        int count = globalMsg.A.getValidCount();
        int j = 0;

        for (int i = 0; i < count; i++) {
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxNum)) {
                continue;
            }

            bizMsg.C.no(j).arTrxNum_C1.setValue(globalMsg.A.no(i).arTrxNum.getValue());
            bizMsg.C.no(j).arTrxTpCd_C1.setValue(globalMsg.A.no(i).arTrxTpCd.getValue());
            bizMsg.C.no(j).arTrxBalPk_C1.setValue(globalMsg.A.no(i).arTrxBalPk_BK.getValue());
            j++;
        }

        bizMsg.C.setValidCount(j);

        NFCL2760CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
        
        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);
        
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_OpenWin_Search================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_OpenWin_Upload(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_OpenWin_Upload================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;

        getUpldCsvId(bizMsg, AR_CSV_UPLD_CASHAPP_KEY);

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_OpenWin_Upload================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean checkReceiptNumber(NFCL2760CMsg bizMsg) {

        Map<String, String> inFindRcpDataMap = new HashMap<String, String>();
        inFindRcpDataMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        inFindRcpDataMap.put("rcptNum", bizMsg.rcptNum.getValue());
        inFindRcpDataMap.put("arApplyTp_Adjustment", AR_APPLY_TP.ADJUSTMENT);
        inFindRcpDataMap.put("arAdjTrxTp_OnAccount", AR_ADJ_TRX_TP.ON_ACCOUNT);
        inFindRcpDataMap.put("arCashApplyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        inFindRcpDataMap.put("arCashApplyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        S21SsmEZDResult outRcptData = NFCL2760Query.getInstance().findRcptData(inFindRcpDataMap, bizMsg);

        if (RTNCD_NOT_FOUND.equals(outRcptData.getResultCode())) {
            bizMsg.setMessageInfo("NFCM0029E", null);
            return false;
        } else {
            // do nothing
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.voidFlg_H1.getValue())) {
            bizMsg.setMessageInfo("NFCM0037E", null);
            return false;
        } else {
            // do nothing
        }

        //if (!bizMsg.dealRfAmt.isClear() && !NFCL2760CommonLogic.isZero(bizMsg.dealRfAmt.getValue())) {
        //    bizMsg.setMessageInfo("NFCM0036E", null);
        //    return false;
        //} else {
        //    // do nothing
        //}

        if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {

            if (bizMsg.dealRcptRmngBalAmt.isClear() || NFCL2760CommonLogic.isZero(bizMsg.dealRcptRmngBalAmt.getValue())) {
                bizMsg.setMessageInfo("NFCM0034E", null);
                return false;
            } else {
                // do nothing
            }
        } else {
            // do nothing
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param newInvProcess if new transaction is processed
     * @return NFCL2760_ASMsgArray
     */
    // START 2022/02/22 D.Mamaril [QC#59733, MOD]
    //private ArrayList getInvData(NFCL2760SMsg globalMsg, NFCL2760CMsg bizMsg) {
    private ArrayList getInvData(NFCL2760SMsg globalMsg, NFCL2760CMsg bizMsg, boolean newInvProcess) {
    // START 2022/02/22 D.Mamaril [QC#59733, MOD]

        ArrayList<Serializable> reList = new ArrayList<Serializable>();
        ArrayList<NFCL2760_ASMsg> invDataSMsgList = new ArrayList<NFCL2760_ASMsg>();

        Map<String, String> inFindInvDataMap = new HashMap<String, String>();
        inFindInvDataMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        inFindInvDataMap.put("rcptNum", bizMsg.rcptNum.getValue());
        // START 2022/01/06 G.Delgado [QC#59329, MOD]
        // inFindInvDataMap.put("arScrCancFlg", ZYPConstant.FLG_OFF_N);
        inFindInvDataMap.put("arScrCancFlgN", ZYPConstant.FLG_OFF_N);
        inFindInvDataMap.put("arScrCancFlgY", ZYPConstant.FLG_ON_Y);
        inFindInvDataMap.put("slsdt", ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd_H1.getValue()));
        inFindInvDataMap.put("arCashApplyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        inFindInvDataMap.put("arCashApplyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        // END 2022/01/06 G.Delgado [QC#59329, MOD]
        inFindInvDataMap.put("arTrxTpCdAdj", AR_ADJ_TRX_TP.ADJUSTMENT);
        inFindInvDataMap.put("arTrxTpCdDed", AR_ADJ_TRX_TP.DEDUCTION);
        inFindInvDataMap.put("arTrxTpCdAcc", AR_TRX_TP.ON_ACCOUNT);
        inFindInvDataMap.put("arTrxTpCdCrm", AR_TRX_TP.CREDIT_MEMO);
        inFindInvDataMap.put("arTrxTpCdDem", AR_TRX_TP.DEBIT_MEMO);
        inFindInvDataMap.put("arTrxTpCdInv", AR_TRX_TP.INVOICE);

        // START 2022/02/22 D.Mamaril [QC#59733, ADD]
        inFindInvDataMap.put("rcptLstUpdDt", bizMsg.rcptHdrLastUpdTs_H1.getValue());
        if (newInvProcess) {
            inFindInvDataMap.put("fromSubmit", ZYPConstant.FLG_ON_1);
        }
        // END 2022/02/22 D.Mamaril [QC#59733, ADD]

        S21SsmEZDResult outFindInvData = NFCL2760Query.getInstance().findInvData(inFindInvDataMap, globalMsg);

        if (outFindInvData.isCodeNormal()) {
            reList.add(outFindInvData.getQueryResultCount());

            NFCL2760_ASMsgArray outData = (NFCL2760_ASMsgArray) outFindInvData.getResultObject();
            for (int i = 0; i < outFindInvData.getQueryResultCount(); i++) {
                if (i == globalMsg.A.length()) {
                    break;
                } else {
                    NFCL2760_ASMsg msg = (NFCL2760_ASMsg) outData.getMyComponent();
                    EZDMsg.copy(outData.no(i), null, msg, null);
                    invDataSMsgList.add(msg);
                }
            }
        } else {
            reList.add(outFindInvData.getQueryResultCount());
        }

        for (int i = 0; i < invDataSMsgList.size(); i++) {

            NFCL2760_ASMsg invDataSMsg = invDataSMsgList.get(i);

            Map<String, Comparable> inFindInvDataByAdjMap = new HashMap<String, Comparable>();
            inFindInvDataByAdjMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
            inFindInvDataByAdjMap.put("arCashAppPk", invDataSMsg.arCashAppPk_BK.getValue());
            inFindInvDataByAdjMap.put("arTrxNum", invDataSMsg.arTrxNum.getValue());
            inFindInvDataByAdjMap.put("arTrxBalPk", invDataSMsg.arTrxBalPk_BK.getValue());
            inFindInvDataByAdjMap.put("arScrCancFlg", ZYPConstant.FLG_OFF_N);

            S21SsmEZDResult outFindInvDataByAdj = NFCL2760Query.getInstance().findInvDataByAdj(inFindInvDataByAdjMap, invDataSMsg);

            if (outFindInvDataByAdj.isCodeNormal()) {
                NFCL2760_ASMsg invDataByAdjSMsg = (NFCL2760_ASMsg) outFindInvDataByAdj.getResultObject();
                invDataSMsg.arAdjTpCd_A1.setValue(invDataByAdjSMsg.arAdjTpCd_A1.getValue());
                invDataSMsg.arAdjNum_BK.setValue(invDataByAdjSMsg.arAdjNum_BK.getValue());
                invDataSMsg.arAdjTrxTpCd_BK.setValue(invDataByAdjSMsg.arAdjTrxTpCd_BK.getValue());
                invDataSMsg.arAdjTpCd_BK.setValue(invDataByAdjSMsg.arAdjTpCd_BK.getValue());
                invDataSMsg.dealApplyAdjAmt_BK.setValue(invDataByAdjSMsg.dealApplyAdjAmt_BK.getValue());
                // START 2017/01/11 T.Murai [QC#16952,ADD]
                invDataSMsg.arAdjTpNm_A1.setValue(invDataByAdjSMsg.arAdjTpNm_A1.getValue());
                invDataSMsg.arAdjTpNm_BK.setValue(invDataByAdjSMsg.arAdjTpNm_BK.getValue());
                // END   2017/01/11 T.Murai [QC#16952,ADD]
            } else {
                // do nothing
            }
        }

        ZYPTableUtil.clear(globalMsg.A);
        reList.add(invDataSMsgList);
        return reList;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @return NFCL2760_ASMsgArray
     */
    private ArrayList getAdjAndDeduction(NFCL2760SMsg globalMsg, NFCL2760CMsg bizMsg) {

        ArrayList<Serializable> reList = new ArrayList<Serializable>();
        ArrayList<NFCL2760_ASMsg> adjAndDedSMsgList = new ArrayList<NFCL2760_ASMsg>();

        Map<String, String> inFindAdjAndDeductionMap = new HashMap<String, String>();
        inFindAdjAndDeductionMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        inFindAdjAndDeductionMap.put("rcptNum", bizMsg.rcptNum.getValue());
        inFindAdjAndDeductionMap.put("arScrCancFlg", ZYPConstant.FLG_OFF_N);

        // START 2024/02/20 TZ.Win [QC#63450, ADD]
        inFindAdjAndDeductionMap.put("arAdjTrxTpCdAcc", AR_TRX_TP.ON_ACCOUNT);
        inFindAdjAndDeductionMap.put("arAdjTrxTpCdAdj", AR_ADJ_TRX_TP.ADJUSTMENT);
        // END 2024/02/20 TZ.Win [QC#63450, ADD]

        S21SsmEZDResult outFindAdjAndDeduction = NFCL2760Query.getInstance().findAdjAndDeduction(inFindAdjAndDeductionMap, globalMsg);

        if (outFindAdjAndDeduction.isCodeNormal()) {
            reList.add(outFindAdjAndDeduction.getQueryResultCount());

            NFCL2760_ASMsgArray outData = (NFCL2760_ASMsgArray) outFindAdjAndDeduction.getResultObject();
            for (int i = 0; i < outFindAdjAndDeduction.getQueryResultCount(); i++) {
                if (i == globalMsg.A.length()) {
                    break;
                } else {
                    NFCL2760_ASMsg msg = (NFCL2760_ASMsg) outData.getMyComponent();
                    EZDMsg.copy(outData.no(i), null, msg, null);
                    adjAndDedSMsgList.add(msg);
                }
            }
        } else {
            reList.add(outFindAdjAndDeduction.getQueryResultCount());
        }

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();

        for (int i = 0; i < adjAndDedSMsgList.size(); i++) {

            NFCL2760_ASMsg adjAndDedSMsg = adjAndDedSMsgList.get(i);

            if (AR_ADJ_TRX_TP.DEDUCTION.equals(adjAndDedSMsg.arTrxTpCd.getValue()) || AR_TRX_TP.ON_ACCOUNT.equals(adjAndDedSMsg.arTrxTpCd.getValue())) {

                inArTrxBalMsg.clear();
                inArTrxBalMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
                inArTrxBalMsg.setConditionValue("attAdjNum01", adjAndDedSMsg.arAdjNum_BK.getValue());

                AR_TRX_BALTMsgArray outArTrxBalMsg = NFCL2760CommonLogic.findArTrxBalListForDeduction(inArTrxBalMsg);

                if (null != outArTrxBalMsg && 0 != outArTrxBalMsg.length()) {
                    adjAndDedSMsg.arTrxNum.setValue(outArTrxBalMsg.no(0).arTrxNum.getValue());
                    adjAndDedSMsg.grpInvNum.setValue(outArTrxBalMsg.no(0).grpInvNum.getValue());
                    adjAndDedSMsg.arTrxBalPk_BK.setValue(outArTrxBalMsg.no(0).arTrxBalPk.getValue());
                    adjAndDedSMsg.invTrxBalPk_BK.setValue(outArTrxBalMsg.no(0).arTrxBalPk.getValue());
                    adjAndDedSMsg.invTrxBalLastUpdTs_BK.setValue(outArTrxBalMsg.no(0).ezUpTime.getValue());
                    adjAndDedSMsg.invTrxBalTz_BK.setValue(outArTrxBalMsg.no(0).ezUpTimeZone.getValue());
                    adjAndDedSMsg.arCustRefNum.setValue(outArTrxBalMsg.no(0).arCustRefNum.getValue());
                    // START 2018/07/19 Y.Matsui [QC#26871, ADD]
                    if (AR_TRX_TP.ON_ACCOUNT.equals(adjAndDedSMsg.arTrxTpCd.getValue())) {
                        adjAndDedSMsg.arCustRefNum.setValue(ZYPCodeDataUtil.getName(AR_TRX_TP.class, getGlobalCompanyCode(), AR_TRX_TP.ON_ACCOUNT));
                        // START 2022/04/22 D.Mamaril [QC#59333, ADD]
                        String glblCmpyCd = bizMsg.glblCmpyCd_H1.getValue();
                        String arTrxNum = adjAndDedSMsg.arTrxNum.getValue();
                        BigDecimal applyingRefundCount = (BigDecimal) NFCL2760Query.getInstance().getApplyingRefundCount(glblCmpyCd, arTrxNum).getResultObject();

                        if (ZYPCommonFunc.hasValue(applyingRefundCount) && BigDecimal.ZERO.compareTo(applyingRefundCount) < 0) {                          
                            ZYPEZDItemValueSetter.setValue(adjAndDedSMsg.xxModeInd_BK, DETAIL_MODE_REFUND_ONGOING);
                            ZYPEZDItemValueSetter.setValue(adjAndDedSMsg.arTrxTpCd, AR_ADJ_TRX_TP.REFUND);
                            ZYPEZDItemValueSetter.setValue(adjAndDedSMsg.arAdjTrxTpCd_BK, AR_ADJ_TRX_TP.REFUND);
                            ZYPEZDItemValueSetter.setValue(adjAndDedSMsg.arAdjTpCd_A3, AR_ADJ_TP.A_OR_R_CASH_REFUND);
                            ZYPEZDItemValueSetter.setValue(adjAndDedSMsg.arTrxDt, outArTrxBalMsg.no(0).arTrxDt.getValue());
                            adjAndDedSMsg.arCustRefNum.clear();

                            String arAdjTpNm = ZYPCodeDataUtil.getName(AR_ADJ_TP.class, getGlobalCompanyCode(), AR_ADJ_TP.A_OR_R_CASH_REFUND);
                            if(ZYPCommonFunc.hasValue(arAdjTpNm)) {
                                adjAndDedSMsg.arAdjTpNm_A3.setValue(arAdjTpNm);
                            }

                            continue;
                        }
                        // END 2022/04/22 D.Mamaril [QC#59333, ADD]
                    }
                    // END   2018/07/19 Y.Matsui [QC#26871, ADD]
                    // START 2016/11/07 S.Fujita [QC#13580,ADD]
                    adjAndDedSMsg.arTrxDt.setValue(outArTrxBalMsg.no(0).arTrxDt.getValue());
                    // END   2016/11/07 S.Fujita [QC#13580,ADD]
                } else {
                    // do nothing
                }
                if (AR_ADJ_TRX_TP.DEDUCTION.equals(adjAndDedSMsg.arTrxTpCd.getValue())) {
                    adjAndDedSMsg.xxModeInd_BK.setValue(DETAIL_MODE_DEDUCTION);
                } else {
                    adjAndDedSMsg.xxModeInd_BK.setValue(DETAIL_MODE_ONACOUNT);
                }

            } else {
                // START 2021/06/21 H.Dimay [QC#58639, ADD]
                if (AR_ADJ_TRX_TP.REFUND.equals(adjAndDedSMsg.arTrxTpCd.getValue())) {
                    adjAndDedSMsg.xxModeInd_BK.setValue(DETAIL_MODE_REFUND);
                } else {
                // END2021/06/21 H.Dimay [QC#58639, ADD]
                	adjAndDedSMsg.xxModeInd_BK.setValue(DETAIL_MODE_ADJUSTMENT);
                // START 2021/06/21 H.Dimay [QC#58639, ADD]
                }
                // END2021/06/21 H.Dimay [QC#58639, ADD]
                // START 2016/11/07 S.Fujita [QC#13580,ADD]
                adjAndDedSMsg.arTrxDt.setValue(adjAndDedSMsg.glDt_A1.getValue());
                // END   2016/11/07 S.Fujita [QC#13580,ADD]
            }
        }

        ZYPTableUtil.clear(globalMsg.A);
        reList.add(adjAndDedSMsgList);
        return reList;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param asMsg Business Component Interface Message
     */
    private void setScaleOfAmt(NFCL2760_ASMsg asMsg) {

        asMsg.dealOrigGrsAmt.setValue(NFCL2760CommonLogic.getNewScale(asMsg.dealOrigGrsAmt.getValue()));
        asMsg.dealApplyGrsAmt.setValue(NFCL2760CommonLogic.getNewScale(asMsg.dealApplyGrsAmt.getValue()));
        asMsg.dealApplyCrAmt.setValue(NFCL2760CommonLogic.getNewScale(asMsg.dealApplyCrAmt.getValue()));
        asMsg.dealNetSlsAmt.setValue(NFCL2760CommonLogic.getNewScale(asMsg.dealNetSlsAmt.getValue()));
        asMsg.dealFrtAmt.setValue(NFCL2760CommonLogic.getNewScale(asMsg.dealFrtAmt.getValue()));
        asMsg.dealTaxAmt.setValue(NFCL2760CommonLogic.getNewScale(asMsg.dealTaxAmt.getValue()));
        asMsg.dealRmngBalGrsAmt.setValue(NFCL2760CommonLogic.getNewScale(asMsg.dealRmngBalGrsAmt.getValue()));
        asMsg.xxDealApplyAmtNum_A1.setValue(NFCL2760CommonLogic.getNewScale(asMsg.xxDealApplyAmtNum_A1.getValue()));
        asMsg.dealCashDiscAmt_A1.setValue(NFCL2760CommonLogic.getNewScale(asMsg.dealCashDiscAmt_A1.getValue()));
        asMsg.cashDiscPct_A1.setValue(NFCL2760CommonLogic.getNewScale(asMsg.cashDiscPct_A1.getValue()));
        asMsg.xxDealApplyAdjAmtNum_A1.setValue(NFCL2760CommonLogic.getNewScale(asMsg.xxDealApplyAdjAmtNum_A1.getValue()));
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param invData ArrayList
     * @param adjAndDedData ArrayList
     * @return NFCL2760_ASMsgArray
     */
    private boolean chkDetailByDate(NFCL2760CMsg bizMsg, NFCL2760SMsg globalMsg, String glDt) {

        String xxBatProcDt = "";
        String xxBatProcYrMth = "";
        String xxAcctYrMth = "";
        String xxBefBatProcYrMth = "";
        String xxGlYrmth = "";

        xxBatProcDt = ZYPDateUtil.getBatProcDate();

        String outConvertFormat = ZYPDateUtil.convertFormat(xxBatProcDt, "yyyyMMdd", "yyyyMM", null);
        xxBatProcYrMth = outConvertFormat + BIZAPL_DAY_00;

        String outGetAcctYrMth = bizMsg.acctYrMth.getValue();
        if ("".equals(outGetAcctYrMth)) {
            return false;
        } else {
            xxAcctYrMth = outGetAcctYrMth + BIZAPL_DAY_00;
        }

        xxBefBatProcYrMth = getprevYearMon(xxBatProcYrMth) + BIZAPL_DAY_00;

        int outCompare = ZYPDateUtil.compare(glDt, xxBatProcDt);
        if (0 < outCompare) {
            return false;
        }

        outConvertFormat = ZYPDateUtil.convertFormat(glDt, "yyyyMMdd", "yyyyMM", null);
        xxGlYrmth = outConvertFormat + BIZAPL_DAY_00;

        if (ZYPDateUtil.compare(xxAcctYrMth, xxBatProcYrMth) == 0) {

            if (ZYPDateUtil.compare(xxBatProcYrMth, xxGlYrmth) != 0) {
                return false;
            } else {
                // do nothig
            }
        } else {

            if (xxBatProcYrMth.compareTo(xxGlYrmth) < 0 || xxBefBatProcYrMth.compareTo(xxGlYrmth) > 0) {
                return false;
            } else {
                // do nothing
            }
        }
        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message date
     * @return String
     */
    private String getprevYearMon(String date) {
        if (date.length() != BIZAPL_INT_NUM_8) {
            return "";
        }
        String prevYeanMon = "";
        int year = Integer.parseInt(date.substring(BIZAPL_INT_NUM_0, BIZAPL_INT_NUM_4));
        int month = Integer.parseInt(date.substring(BIZAPL_INT_NUM_4, BIZAPL_INT_NUM_6));
        month = month - BIZAPL_INT_NUM_1;
        if (month < BIZAPL_INT_NUM_1) {
            month = BIZAPL_INT_NUM_12;
            year = year - BIZAPL_INT_NUM_1;
        }
        DecimalFormat monthFormat = new DecimalFormat(BIZAPL_DAY_00);

        prevYeanMon = String.valueOf(year) + monthFormat.format(month);
        return prevYeanMon;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param invData ArrayList
     * @param adjAndDedData ArrayList
     * @return NFCL2760_ASMsgArray
     */
    private ArrayList getSortDara(ArrayList invData, ArrayList adjAndDedData, NFCL2760SMsg globalMsg, NFCL2760CMsg bizMsg) {

        ArrayList<NFCL2760_ASMsg> sorList = new ArrayList<NFCL2760_ASMsg>();
        // START 2024/02/20 TZ.Win [QC#63450, ADD]
        Map<BigDecimal, NFCL2760_ASMsg> sortNumMap = new HashMap<BigDecimal, NFCL2760_ASMsg>();
        // END 2024/02/20 TZ.Win [QC#63450, ADD]
        // ACC, ADJ, DED, CRM, DEM, INV
        int invIndex = 0;
        int adjIndex = 0;

        // del start 2022/09/12 QC#60541
//        if (MODE_ONE.equals(bizMsg.xxModeInd_H1.getValue())){
//            // INV
//            for (; invIndex < invData.size(); invIndex++) {
//                if (globalMsg.A.length() > sorList.size()) {
//                    NFCL2760_ASMsg msg = (NFCL2760_ASMsg) invData.get(invIndex);
//                    if (AR_TRX_TP.INVOICE.equals(msg.arTrxTpCd.getValue())) {
//                        if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
//                            continue;
//                        }
//                        sorList.add(msg);
//                    } else {
//                        break;
//                }
//            } else {
//                break;
//            }
//        }
//
//            for (; adjIndex < adjAndDedData.size(); adjIndex++) {
//                if (globalMsg.A.length() > sorList.size()) {
//                    NFCL2760_ASMsg msg = (NFCL2760_ASMsg) adjAndDedData.get(adjIndex);
//                    if (AR_TRX_TP.INVOICE.equals(msg.arTrxTpCd.getValue())) {
//                        if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
//                            continue;
//                        }
//                        sorList.add(msg);
//                    } else {
//                        break;
//                    }
//               } else {
//                    break;
//               }
//            }
//        }
        // del end 2022/09/12 QC#60541

        // ACC
        for (; invIndex < invData.size(); invIndex++) {
            if (globalMsg.A.length() > sorList.size()) {
                NFCL2760_ASMsg msg = (NFCL2760_ASMsg) invData.get(invIndex);
                if (AR_TRX_TP.ON_ACCOUNT.equals(msg.arTrxTpCd.getValue())) {
                    if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
                        continue;
                    }
                    sorList.add(msg);
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (; adjIndex < adjAndDedData.size(); adjIndex++) {
            if (globalMsg.A.length() > sorList.size()) {
                NFCL2760_ASMsg msg = (NFCL2760_ASMsg) adjAndDedData.get(adjIndex);
                if (AR_TRX_TP.ON_ACCOUNT.equals(msg.arTrxTpCd.getValue())) {
                    if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
                        continue;
                    }
                    sorList.add(msg);
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        // ADJ
        for (; invIndex < invData.size(); invIndex++) {
            if (globalMsg.A.length() > sorList.size()) {
                NFCL2760_ASMsg msg = (NFCL2760_ASMsg) invData.get(invIndex);
                if (AR_ADJ_TRX_TP.ADJUSTMENT.equals(msg.arTrxTpCd.getValue())) {
                    if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
                        continue;
                    }
                    sorList.add(msg);
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (; adjIndex < adjAndDedData.size(); adjIndex++) {
            if (globalMsg.A.length() > sorList.size()) {
                NFCL2760_ASMsg msg = (NFCL2760_ASMsg) adjAndDedData.get(adjIndex);
                if (AR_ADJ_TRX_TP.ADJUSTMENT.equals(msg.arTrxTpCd.getValue())) {
                    if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
                        continue;
                    }
                    sorList.add(msg);
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        // DED
        for (; invIndex < invData.size(); invIndex++) {
            if (globalMsg.A.length() > sorList.size()) {
                NFCL2760_ASMsg msg = (NFCL2760_ASMsg) invData.get(invIndex);
                // START 2024/02/20 TZ.Win [QC#63450, MOD]
                if (AR_ADJ_TRX_TP.DEDUCTION.equals(msg.arTrxTpCd.getValue())
                        && !ZYPCommonFunc.hasValue(msg.arCashAppSortNum_UP)) {
                // END 2024/02/20 TZ.Win [QC#63450, MOD]
                    if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
                        continue;
                    }
                    sorList.add(msg);
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (; adjIndex < adjAndDedData.size(); adjIndex++) {
            if (globalMsg.A.length() > sorList.size()) {
                NFCL2760_ASMsg msg = (NFCL2760_ASMsg) adjAndDedData.get(adjIndex);
                // START 2024/02/20 TZ.Win [QC#63450, MOD]
                if (AR_ADJ_TRX_TP.DEDUCTION.equals(msg.arTrxTpCd.getValue())
                        && !ZYPCommonFunc.hasValue(msg.arCashAppSortNum_UP)) {
                // END 2024/02/20 TZ.Win [QC#63450, MOD]
                    if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
                        continue;
                    }
                    sorList.add(msg);
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        // CRM, DEM, INV
        for (; invIndex < invData.size(); invIndex++) {
            if (globalMsg.A.length() > sorList.size()) {
                NFCL2760_ASMsg msg = (NFCL2760_ASMsg) invData.get(invIndex);
                // START 2024/02/20 TZ.Win [QC#63450, ADD]
                if (!ZYPCommonFunc.hasValue(msg.arCashAppSortNum_UP)) {
                // END 2024/02/20 TZ.Win [QC#63450, ADD]
                    if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
                        continue;
                    }
                    sorList.add(msg);
                // START 2024/02/20 TZ.Win [QC#63450, ADD]
                } else {
                    break;
                }
                // END 2024/02/20 TZ.Win [QC#63450, ADD]
            } else {
                break;
            }
        }

        // START 06/21/2021 H.Dimay [QC#58639, ADD]
        // For RFD (Refund) status
        for (; adjIndex < adjAndDedData.size(); adjIndex++) {
            if (globalMsg.A.length() > sorList.size()) {
                NFCL2760_ASMsg msg = (NFCL2760_ASMsg) adjAndDedData.get(adjIndex);
                // START 2024/02/20 TZ.Win [QC#63450, MOD]
                if (AR_ADJ_TRX_TP.REFUND.equals(msg.arTrxTpCd.getValue())
                        && !ZYPCommonFunc.hasValue(msg.arCashAppSortNum_UP)) {
                // END 2024/02/20 TZ.Win [QC#63450, MOD]
                    if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
                        continue;
                    }
                    sorList.add(msg);
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        // END 06/21/2021 H.Dimay [QC#58639, ADD]

         // START 2024/02/20 TZ.Win [QC#63450, ADD]
        for (; invIndex < invData.size(); invIndex++) {
            NFCL2760_ASMsg msg = (NFCL2760_ASMsg) invData.get(invIndex);
            if (ZYPCommonFunc.hasValue(msg.arCashAppSortNum_UP)) {
                sortNumMap.put(msg.arCashAppSortNum_UP.getValue(), msg);
            } else {
                break;
            }
        }

        for (; adjIndex < adjAndDedData.size(); adjIndex++) {
            NFCL2760_ASMsg msg = (NFCL2760_ASMsg) adjAndDedData.get(adjIndex);
            if (ZYPCommonFunc.hasValue(msg.arCashAppSortNum_UP)) {
                sortNumMap.put(msg.arCashAppSortNum_UP.getValue(), msg);
            } else {
                break;
            }
        }

        Map<BigDecimal, NFCL2760_ASMsg> sortNumDataMap = new TreeMap<BigDecimal, NFCL2760_ASMsg>(sortNumMap);

        for (Map.Entry<BigDecimal, NFCL2760_ASMsg> sortNumData : sortNumDataMap.entrySet()) {
            if (globalMsg.A.length() > sorList.size()) {
                NFCL2760_ASMsg msg = (NFCL2760_ASMsg) sortNumData.getValue();
                if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
                    continue;
                }
                sorList.add(msg);
            } else {
                break;
            }
        }
        // END 2024/02/20 TZ.Win [QC#63450, ADD]
        return sorList;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param arCsvUpldKey String
     * @return void
     */
    private void getUpldCsvId(NFCL2760CMsg bizMsg, String arCsvUpldKey) {
        EZDDebugOutput.println(1, "        getUpldCsvId===START", this);

        String arCsvUpldId = ZYPCodeDataUtil.getVarCharConstValue(arCsvUpldKey, bizMsg.glblCmpyCd_H1.getValue());
        bizMsg.upldCsvId_P4.setValue(arCsvUpldId);

    }

    /**
     * Add 2010/06/08 T.Tanaka /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_AddOneLine(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_AddOneLine================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        // 2017/11/16 QC#21402 add start
        int index = bizMsg.xxCellIdx_H1.getValueInt();
        if (index >= 0) { // 2018/01/11 QC#23134 Add
            // START 2019/09/03 H.Ikeda [QC#53138, MOD]
            //if (BigDecimal.ZERO.compareTo(bizMsg.A.no(index).xxDealApplyAmtNum_A1.getValue()) == 0 && ZYPCommonFunc.hasValue(bizMsg.A.no(index).arCustRefNum)) {
            if (RECORD_STS.NEW.getValue().equals(bizMsg.A.no(index).xxArCashApplyStsTxt.getValue()) && ZYPCommonFunc.hasValue(bizMsg.A.no(index).arCustRefNum)) {
            // END   2019/09/03 H.Ikeda [QC#53138, MOD]
                doProcess_NFCL2760Scrn00_SearchTrxLine(cMsg, sMsg);
            } else {
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index + pagenationFrom).xxDealApplyAmtNum_A1 ,bizMsg.A.no(index).xxDealApplyAmtNum_A1);
            }
        } // 2018/01/11 QC#23134 Add
        // 2017/11/16 QC#21402 add end

        boolean rval = true;
        if (pagenationFrom > -1) {
            NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            //rval = NFCL2760CommonLogic.setArTrxAllLine(bizMsg, globalMsg);

            // START 2018/10/24 T.Ogura [QC#28168-1,ADD]
            // All>>
            if (!SCRN_MODE_CANCEL.equals(bizMsg.xxModeInd_H1.getValue())) {
                boolean ret = NFCL2760CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
                if (!ret) {
                    NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }
            }
            // END   2018/10/24 T.Ogura [QC#28168-1,ADD]
        }
        NFCL2760CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        if (rval) {
            // Add line
            int count = globalMsg.A.getValidCount();
            int allDataCount = count + ADD_LINE.NUM_OF_ADD_LINE_ONE.getValue();
            if (globalMsg.A.length() < allDataCount) {
                bizMsg.setMessageInfo("NFCM0110E", null);
                bizMsg.setMessageInfo("ZZM9037E", null);
                return;
            }
            globalMsg.A.no(count).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
            globalMsg.A.no(count).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
            globalMsg.A.no(count).xxPgFlg_A2.setValue(ZYPConstant.FLG_OFF_N);
            globalMsg.A.no(count).xxPgFlg_A3.setValue(ZYPConstant.FLG_OFF_N);

            bizMsg.xxPageShowFromNum.setValue(NFCL2760CommonLogic.getPagenationFrom(bizMsg, count));
            globalMsg.A.setValidCount(allDataCount);
            globalMsg.A.no(allDataCount - 1).xxArCashApplyStsTxt.setValue(RECORD_STS.NEW.getValue());
        } else {
            bizMsg.setMessageInfo("ZZM9037E", null);
        }

        pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL2760CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
        
        if (!"E".equals(bizMsg.getMessageKind())) {
            NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);
        }
        // add start 2022/01/26 QC#59641
        if ((globalMsg.A.getValidCount() % bizMsg.A.length()) > 0) {
            addInitDetail(cMsg, sMsg);
        }
        // add end 2022/01/26 QC#59641

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_AddOneLine================================END", this);

    }

    // START 2020/01/20 H.Ikeda [QC#54902,ADD]
    /**
     * doProcess_NFCL2760Scrn00_Search
     * 
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NFCL2760Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_Search================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;
        int pagenationFrom = 0;
        boolean workFlg = false;
        
        String arCustRefNumH = bizMsg.arCustRefNum_H1.getValue();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arCustRefNum)) {
                if (arCustRefNumH.equals(globalMsg.A.no(i).arCustRefNum.getValue())) {
                    if (i < bizMsg.A.length()) {
                        bizMsg.xxCellIdx_H1.setValue(i);
                    } else {
                        bizMsg.xxCellIdx_H1.setValue(i % bizMsg.A.length());
                        pagenationFrom = i - (i % bizMsg.A.length());
                    }
                    workFlg = true;
                    break;
                }
            }
        }

        if (workFlg) {
            int i = pagenationFrom;
            for (; i < pagenationFrom + bizMsg.A.length(); i++) {
                if (i < globalMsg.A.getValidCount()) {
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            bizMsg.A.setValidCount(i - pagenationFrom);

            // set value to page nation items
            bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
        } else {
            bizMsg.arCustRefNum_H1.setErrorInfo(1, "ZZZM9001E");
            bizMsg.setMessageInfo("ZZM9037E");
        }

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_Search================================END", this);
    }

    
    /**
     * doProcess_NFCL2760Scrn00_OnBlur_Inv
     * 
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NFCL2760Scrn00_OnBlur_Inv(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_OnBlur_Inv================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;
        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        int index = bizMsg.xxCellIdx_H1.getValueInt();

        String arCustRefNum = bizMsg.A.no(index).arCustRefNum.getValue();
        if (!ZYPCommonFunc.hasValue(arCustRefNum)) {
            bizMsg.A.no(index).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
        }

        if (!arCustRefNum.equals(bizMsg.A.no(index).arCustRefNum_BK.getValue())) {
            bizMsg.A.no(index).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
            bizMsg.A.no(index).arCustRefNum.setValue(arCustRefNum);
        }

        NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        boolean notError = NFCL2760CommonLogic.getArTrxBalSearch(bizMsg, globalMsg, index + pagenationFrom, bizMsg.xxPageShowFromNum.getValueInt());
        if (notError) {
            globalMsg.A.no(index + pagenationFrom).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
            ArrayList<Integer> listAddRow = new ArrayList<Integer>();
            listAddRow.add(index + pagenationFrom);
            NFCL2760CommonLogic.setCalcFor5050(bizMsg, globalMsg, listAddRow);
        }

        int arTrxBalPkIndex = 0;
        bizMsg.arTrxBalPk_B1.clear();
        bizMsg.arTrxNum_B1.clear();
        bizMsg.arTrxTpCd_B1.clear();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxBalPk_BK)) {
                bizMsg.arTrxBalPk_B1.no(i).setValue(globalMsg.A.no(i).arTrxBalPk_BK.getValue());
                bizMsg.arTrxNum_B1.no(i).setValue(globalMsg.A.no(i).arTrxNum.getValue());
                bizMsg.arTrxTpCd_B1.no(i).setValue(globalMsg.A.no(i).arTrxTpCd.getValue());
                arTrxBalPkIndex++;
            }
        }

        NFCL2760CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_OnBlur_Inv================================END", this);
    }
    // END   2020/01/20 H.Ikeda [QC#54902,ADD]

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_SearchTrxLine(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_SearchTrxLine================================START", this);
        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;
        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        int index = bizMsg.xxCellIdx_H1.getValueInt();

        String arCustRefNum = bizMsg.A.no(index).arCustRefNum.getValue();
        if (!ZYPCommonFunc.hasValue(arCustRefNum)) {
            bizMsg.A.no(index).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
        }
        if (!bizMsg.A.no(index).arCustRefNum.getValue().equals(bizMsg.A.no(index).arCustRefNum_BK.getValue())) {
            bizMsg.A.no(index).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
            bizMsg.A.no(index).arCustRefNum.setValue(arCustRefNum);
        }

        NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        boolean notError = NFCL2760CommonLogic.getArTrxBalSearch(bizMsg, globalMsg, index + pagenationFrom, bizMsg.xxPageShowFromNum.getValueInt());
        // 2017/11/15 QC#21402 add start
        if (notError) {
            // START 2018/06/06 Y.Matsui [QC#25737,ADD]
            globalMsg.A.no(index + pagenationFrom).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
            // END   2018/06/06 Y.Matsui [QC#25737,ADD]
            ArrayList<Integer> listAddRow = new ArrayList<Integer>();
            listAddRow.add(index + pagenationFrom);
            NFCL2760CommonLogic.setCalcFor5050(bizMsg, globalMsg, listAddRow);
        }
        // 2017/11/15 QC#21402 add end

        // START 2018/08/02 Y.Matsui [QC#26985-1, ADD]
        int arTrxBalPkIndex = 0;
        bizMsg.arTrxBalPk_B1.clear();
        bizMsg.arTrxNum_B1.clear();
        bizMsg.arTrxTpCd_B1.clear();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxBalPk_BK)) {
                bizMsg.arTrxBalPk_B1.no(i).setValue(globalMsg.A.no(i).arTrxBalPk_BK.getValue());
                bizMsg.arTrxNum_B1.no(i).setValue(globalMsg.A.no(i).arTrxNum.getValue());
                bizMsg.arTrxTpCd_B1.no(i).setValue(globalMsg.A.no(i).arTrxTpCd.getValue());
                arTrxBalPkIndex++;
            }
        }
        // END   2018/08/02 Y.Matsui [QC#26985-1, ADD]

        NFCL2760CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_SearchTrxLine================================END", this);
    }


    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_OpenWin_New(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_OpenWin_New================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;
        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());

        if (pagenationFrom != -1) {
            pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            NFCL2760CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
            NFCL2760CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
        }
        
        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_OpenWin_New================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL2760Scrn00_OpenWin_Select(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_OpenWin_Select================================START", this);

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;
        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        if (pagenationFrom != -1) {
            pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL2760CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            NFCL2760CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
            NFCL2760CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
        }
        
        NFCL2760CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL2760Scrn00_OpenWin_Select================================END", this);
    }

    private static void setAftDeclPntDigitNum(NFCL2760CMsg bizMsg) {
        if (bizMsg.ccyCd.isClear()) {
            // This logic shouldn't be used.
            bizMsg.aftDeclPntDigitNum_H1.setValue(new BigDecimal(2));

        } else {

            BigDecimal aftDeclPntDigitNum = NFCCmnMethod.getAftDeclPntDigitNum(bizMsg.glblCmpyCd_H1.getValue(), bizMsg.ccyCd.getValue());

            bizMsg.aftDeclPntDigitNum_H1.setValue(aftDeclPntDigitNum);
        }
    }

    // 2017/11/15 QC#21402 add start
    private static void addInitDetail(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        // del start 2022/01/26 QC#59641
        //if (!AR_CASH_APPLY_STS.APPLIED.equals(bizMsg.arCashApplyStsCd_H1.getValue())) {
        // del end 2022/01/26 QC#59641
            // START 2022/01/06 G.Delgado [QC#59329, MOD]
            // int addLineCnt = INIT_DETAIL_COUNT;
            int addLineCnt = (globalMsg.A.getValidCount() / bizMsg.A.length()) + 1;
            addLineCnt = addLineCnt * bizMsg.A.length();
            // END 2022/01/06 G.Delgado [QC#59329, MOD]
         // 2018/02/08 QC#23657 MOD START
         //   if (bizMsg.A.length() - bizMsg.A.getValidCount() < addLineCnt) {
         //       addLineCnt = bizMsg.A.length() - bizMsg.A.getValidCount();
         //   }
         // 2018/05/08 QC#25702 MOD START
         //   addLineCnt = bizMsg.A.getValidCount() + addLineCnt;
            // START 2022/01/06 G.Delgado [QC#59329, DEL]
            // addLineCnt = globalMsg.A.getValidCount() + addLineCnt;
            // END 2022/01/06 G.Delgado [QC#59329, DEL]
         // 2018/05/08 QC#25702 MOD END
         // 2018/02/08 QC#23657 MOD END
        // add start 2022/01/26 QC#59641
        if (addLineCnt > globalMsg.A.length()) {
            addLineCnt = globalMsg.A.length();
        }
        // add end 2022/01/26 QC#59641
            for (int i = globalMsg.A.getValidCount() ; i < addLineCnt ; i++) {
                globalMsg.A.no(i).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
                globalMsg.A.no(i).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
                globalMsg.A.no(i).xxPgFlg_A2.setValue(ZYPConstant.FLG_OFF_N);
                globalMsg.A.no(i).xxPgFlg_A3.setValue(ZYPConstant.FLG_OFF_N);
                globalMsg.A.no(i).xxDealApplyAmtNum_A1.setValue(BigDecimal.ZERO);
                globalMsg.A.no(i).xxArCashApplyStsTxt.setValue(RECORD_STS.NEW.getValue());
                // add start 2022/01/26 QC#59641
                globalMsg.A.no(i).dealRmngBalGrsAmt.setValue(BigDecimal.ZERO);
                // add end 2022/01/26 QC#59641
            }
            // 2018/02/08 QC#23657 MOD START
            globalMsg.A.setValidCount(addLineCnt);
            // 2018/02/08 QC#23657 MOD END
        // del start 2022/01/26 QC#59641
        //}
        // del end 2022/01/26 QC#59641
        // mod start 2022/01/26 QC#59641
        //NFCL2760CommonLogic.setCurrentPageOut(bizMsg, globalMsg, 0);
        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL2760CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
        // mod end 2022/01/26 QC#59641
    }
    // 2017/11/15 QC#21402 add end


    // START 2018/06/06 Y.Matsui [QC#25737,ADD]
    private void doProcess_NFCL2760Scrn00_OnClick_Chk(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760SMsg globalMsg = (NFCL2760SMsg) sMsg;

        int pagenationFrom = NFCL2760CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxChkBox, bizMsg.A.no(i - pagenationFrom).xxChkBox);
            } else {
                break;
            }
        }
        doProcess_NFCL2760Scrn00_CalcGrossAmount(cMsg, sMsg);
    }
    // END   2018/06/06 Y.Matsui [QC#25737,ADD]

    // START 2018/06/06 Y.Matsui [QC#25369,ADD]
    private boolean isBlankLine(NFCL2760_ASMsg asMsg) {
        if (asMsg.xxArCashApplyStsTxt.getValue().equals(RECORD_STS.APPLIED.getValue())) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(asMsg.arTrxTpCd)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(asMsg.arCustRefNum)) {
            return false;
        }
        return true;
    }
    // END 2018/06/06 Y.Matsui [QC#25369,ADD]

    // START 2022/02/22 D.Mamaril [QC#59733, ADD]
    /**
     * <dd>Check if there is an additional transaction selected.
     * @param globalMsg Global area information
     * @return boolean
     */
    private boolean hasProcess(NFCL2760SMsg globalMsg) {
        for (int iCnt = 0; iCnt < globalMsg.A.getValidCount(); iCnt++) {
            if (ROW_STS.CANCEL.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(iCnt).xxChkBox.getValue(), globalMsg.A.no(iCnt).xxArCashApplyStsTxt.getValue()))
                    || ROW_STS.CASH_APPLICATION.toString().equals(NFCL2760CommonLogic.isStatusOfRow(globalMsg.A.no(iCnt).xxChkBox.getValue(), globalMsg.A.no(iCnt).xxArCashApplyStsTxt.getValue()))) {
                return true;
            }
        }
        return false;
    }
    // END 2022/02/22 D.Mamaril [QC#59733, ADD]
}
