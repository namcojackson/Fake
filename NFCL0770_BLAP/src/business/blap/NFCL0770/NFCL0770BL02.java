package business.blap.NFCL0770;

import static business.blap.NFCL0770.constant.NFCL0770Constant.AR_ADJ_TP_PRM_NM;
import static business.blap.NFCL0770.constant.NFCL0770Constant.AR_CSV_UPLD_CASHAPP_KEY;
import static business.blap.NFCL0770.constant.NFCL0770Constant.AR_RCPT_SRC_NM;
import static business.blap.NFCL0770.constant.NFCL0770Constant.AR_RCPT_STS_NM;
import static business.blap.NFCL0770.constant.NFCL0770Constant.AR_RCPT_TP_PRM_NM;
import static business.blap.NFCL0770.constant.NFCL0770Constant.AR_RCPT_TRX_TP_PRM_NM;
import static business.blap.NFCL0770.constant.NFCL0770Constant.BIZAPL_DAY_00;
import static business.blap.NFCL0770.constant.NFCL0770Constant.BIZAPL_INT_NUM_0;
import static business.blap.NFCL0770.constant.NFCL0770Constant.BIZAPL_INT_NUM_1;
import static business.blap.NFCL0770.constant.NFCL0770Constant.BIZAPL_INT_NUM_12;
import static business.blap.NFCL0770.constant.NFCL0770Constant.BIZAPL_INT_NUM_4;
import static business.blap.NFCL0770.constant.NFCL0770Constant.BIZAPL_INT_NUM_6;
import static business.blap.NFCL0770.constant.NFCL0770Constant.BIZAPL_INT_NUM_8;
import static business.blap.NFCL0770.constant.NFCL0770Constant.CMN_CLOSE;
import static business.blap.NFCL0770.constant.NFCL0770Constant.CSV;
import static business.blap.NFCL0770.constant.NFCL0770Constant.DEF_RECEIPT_NUM;
import static business.blap.NFCL0770.constant.NFCL0770Constant.DETAIL_MODE_ADJUSTMENT;
import static business.blap.NFCL0770.constant.NFCL0770Constant.DETAIL_MODE_DEDUCTION;
import static business.blap.NFCL0770.constant.NFCL0770Constant.DETAIL_MODE_ONACOUNT;
import static business.blap.NFCL0770.constant.NFCL0770Constant.INIT_DETAIL_COUNT;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MAX_LINE_DISPLAY;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MODE_CANCEL;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MODE_ENTRY;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MODE_NAME_CANCEL;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MODE_NAME_ENTRY;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MODE_NAME_ONE;
import static business.blap.NFCL0770.constant.NFCL0770Constant.MODE_ONE;
import static business.blap.NFCL0770.constant.NFCL0770Constant.SCRN_MODE_CANCEL;
import static business.blap.NFCL0770.constant.NFCL0770Constant.SCRN_STATUS_N;
import static business.blap.NFCL0770.constant.NFCL0770Constant.SCRN_STATUS_Y;
import static business.blap.NFCL0770.constant.NFCL0770Constant.XX_CHK_BOX;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL0770.common.NFCL0770CommonLogic;
import business.blap.NFCL0770.constant.NFCL0770Constant.ADD_LINE;
import business.blap.NFCL0770.constant.NFCL0770Constant.RECORD_STS;
import business.blap.NFCL0770.constant.NFCL0770Constant.ROW_STS;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_ADJ_TPTMsgArray;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.file.NFCL0770F00FMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TRX_TP;
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
 * 2018/11/02   Fujitsu         S.Takami        Update          QC#28289
 * 2022/09/02   Hitachi         A.Kohinata      Update          QC#60403
 * 2022/11/30	Hitachi         R.Takau         Update          QC#57252
 * </pre>
 */
public class NFCL0770BL02 extends S21BusinessHandler {
    /**  */
    private boolean blIni = false;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            blIni = false;

            // NFCL0770_INIT
            if ("NFCL0770_INIT".equals(screenAplID)) {
                blIni = true;
                doProcess_NFCL0770_INIT(cMsg, sMsg);
                addInitDetail(cMsg, sMsg);
            } else if ("NFCL0770_NFCL5050".equals(screenAplID)) {
                doProcess_NFCL0770_NFCL5050(cMsg, sMsg);
                // NFCL0770Scrn00_DownloadCSV
            } else if ("NFCL0770Scrn00_DownloadCSV".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_DownloadCSV(cMsg, sMsg);
            } else if ("NFCL0770Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_PagePrev(cMsg, sMsg);
                // NFCL0770Scrn00_PageNext
            } else if ("NFCL0770Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_PageNext(cMsg, sMsg);
                // NFCL0770Scrn00_CalcGrossAmount
            } else if ("NFCL0770Scrn00_CalcGrossAmount".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_CalcGrossAmount(cMsg, sMsg);
                // NFCL0770Scrn00_NFCL5140
            } else if ("NFCL0770_NFCL5140".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_NFCL5140(cMsg, sMsg);
                // NFCL0770Scrn00_DeleteLines
            } else if ("NFCL0770Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL0770_INIT(cMsg, sMsg);
                doProcess_NFCL0770Scrn00_CalcGrossAmount(cMsg, sMsg);
                // NFCL0770Scrn00_OpenWin_Search
            } else if ("NFCL0770Scrn00_OpenWin_Search".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_OpenWin_Search(cMsg, sMsg);
                // NFCL0770Scrn00_OpenWin_Upload
            } else if ("NFCL0770Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_PageJump(cMsg, sMsg);
                // NFCL0770Scrn00_CMN_Return
            } else if ("NFCL0770Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_CMN_Return(cMsg, sMsg);
                // NFCL0770Scrn00_AddOneLine
            } else if ("NFCL0770Scrn00_AddOneLine".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_AddOneLine(cMsg, sMsg);
                // NFCL0770Scrn00_AddFiveLine
            } else if ("NFCL0770Scrn00_SearchTrxLine".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_SearchTrxLine(cMsg, sMsg);
                // NFCL0770Scrn00_SearchTrxAllLine
            } else if ("NFCL0770Scrn00_OpenWin_New".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_OpenWin_New(cMsg, sMsg);
                // NFCL0770Scrn00_OpenWin_Select
            } else if ("NFCL0770Scrn00_OpenWin_Select".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_OpenWin_Select(cMsg, sMsg);
            } else if ("NFCL0770Scrn00_OnClick_Chk".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_OnClick_Chk(cMsg, sMsg);
            } else if ("NFCL0770Scrn00_CustomerName".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_CustomerName((NFCL0770CMsg) cMsg);
            } else if ("NFCL0770Scrn00_CMN_Clear".equals(screenAplID)) {
                blIni = true;
                ((NFCL0770CMsg) cMsg).clear();
                doProcess_NFCL0770_INIT(cMsg, sMsg);
                addInitDetail(cMsg, sMsg);
            // add start 2022/09/02 QC#60403
            } else if ("NFCL0770Scrn00_OnBlur_Inv".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_OnBlur_Inv(cMsg, sMsg);
            } else if ("NFCL0770Scrn00_Search".equals(screenAplID)) {
                doProcess_NFCL0770Scrn00_Search(cMsg, sMsg);
            // add end 2022/09/02 QC#60403
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
    private void doProcess_NFCL0770_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770_INIT================================START", this);
        EZDDebugOutput.println(1, "doProcess_NFCL0770_INIT===START_TIME:" + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"), this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        globalMsg.clear();
        ZYPTableUtil.clear(globalMsg.A);
        ZYPTableUtil.clear(globalMsg.B);
        ZYPTableUtil.clear(globalMsg.C);
        bizMsg.setCommitSMsg(true);

        boolean isAfterProc = ZYPCommonFunc.hasValue(bizMsg.rcptNum);
        if (isAfterProc) {
            String mode = bizMsg.xxModeInd_H1.getValue();

            if (!MODE_ENTRY.equals(mode) && !MODE_CANCEL.equals(mode) && !MODE_ONE.equals(mode)) {
                bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_N);
                bizMsg.setMessageInfo("NFCM0031E", null);
                return;
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
            }

            // Receipt Transaction Type Name
            bizMsg.arRcptTrxTpNm.setValue(ZYPCodeDataUtil.getName(AR_RCPT_TRX_TP_PRM_NM, glblCmpyCd, bizMsg.arRcptTrxTpCd.getValue()));

            // Receipt Type Name
            bizMsg.arRcptTpNm.setValue(ZYPCodeDataUtil.getName(AR_RCPT_TP_PRM_NM, glblCmpyCd, bizMsg.arRcptTpCd.getValue()));

            // Payer Name
            NFCL0770CommonLogic.setDsAcctNm(bizMsg);

            Map<String, String> inMap = new HashMap<String, String>();
            inMap.put("GLBL_CMPY_CD", bizMsg.glblCmpyCd_H1.getValue());
            inMap.put("AR_CASH_APP_MAN_ENTRY_TP_CD", "2");
            AR_ADJ_TPTMsgArray outArAdjTPMsg = (AR_ADJ_TPTMsgArray) ZYPCodeDataUtil.findByCondition(AR_ADJ_TP_PRM_NM, inMap);

            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "arAdjTpCd");
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "arAdjTpCd");
            ZYPPulldownValueSetter.set(outArAdjTPMsg, tMsgKeys, bizMsg.arAdjTpCd_B1, bizMsg.arAdjTpCd_B2);

            if (MODE_CANCEL.equals(mode) || MODE_ONE.equals(mode)) {

                NFCL0770CommonLogic.findArAcctDtInfo(bizMsg, new AR_ACCT_DTTMsg());

                ArrayList invData = getInvData(globalMsg, bizMsg);
                ArrayList adjAndDedData = getAdjAndDeduction(globalMsg, bizMsg);
                ArrayList sortList = getSortDara((ArrayList) invData.get(1), (ArrayList) adjAndDedData.get(1), globalMsg, bizMsg);

                int vCount = 0;
                for (; vCount < sortList.size(); vCount++) {
                    if (vCount == globalMsg.A.length()) {
                        break;
                    } else {
                        EZDMsg.copy((NFCL0770_ASMsg) sortList.get(vCount), null, globalMsg.A.no(vCount), null);
                        NFCL0770CommonLogic.setDiff(globalMsg.A.no(vCount), MODE_CANCEL);
                    }
                }

                globalMsg.A.setValidCount(vCount);

                if (0 != globalMsg.A.getValidCount()) {

                    int queryResCnt = ((Integer) invData.get(0)).intValue() + ((Integer) adjAndDedData.get(0)).intValue();
                    if (queryResCnt > globalMsg.A.length()) {
                        bizMsg.setMessageInfo("NFCM0110E");
                        queryResCnt = globalMsg.A.length();
                    }

                    for (int index = 0; index < globalMsg.A.getValidCount(); index++) {

                        setScaleOfAmt(globalMsg.A.no(index));

                        globalMsg.A.no(index).xxChkBox.clear();
                        globalMsg.A.no(index).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
                        globalMsg.A.no(index).xxArCashApplyStsTxt.setValue(RECORD_STS.APPLIED.getValue());

                        BigDecimal dealApplyCrAmt = BigDecimal.ZERO;
                        if (ZYPCommonFunc.hasValue(globalMsg.A.no(index).dealApplyCrAmt)) {
                            dealApplyCrAmt = dealApplyCrAmt.add(globalMsg.A.no(index).dealApplyCrAmt.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(globalMsg.A.no(index).dealApplyAdjAmt_B3)) {
                            dealApplyCrAmt = dealApplyCrAmt.add(globalMsg.A.no(index).dealApplyAdjAmt_B3.getValue());
                        }

                        globalMsg.A.no(index).dealApplyCrAmt.setValue(dealApplyCrAmt);
                        globalMsg.A.no(index).xxDealApplyAmtNum_A1.setValue(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(index).dealApplyAmt_BK.getValue()));
                        globalMsg.A.no(index).xxDealApplyAdjAmtNum_A1.setValue(NFCL0770CommonLogic.getNewScale(globalMsg.A.no(index).dealApplyAdjAmt_BK.getValue()));
                        globalMsg.A.no(index).arCustRefNum_BK.setValue(globalMsg.A.no(index).arCustRefNum.getValue());

                        NFCL0770CommonLogic.setDiff(globalMsg.A.no(index), MODE_CANCEL);
                        NFCL0770CommonLogic.setClearItemBySMsg(globalMsg.A.no(index));
                    }

                    NFCL0770CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, 0, 0);

                    bizMsg.xxPageShowFromNum.setValue(1);
                    bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
                    bizMsg.xxPageShowOfNum.setValue(queryResCnt);

                } else {
                    bizMsg.xxPageShowFromNum.clear();
                    bizMsg.xxPageShowToNum.clear();
                    bizMsg.xxPageShowOfNum.clear();
                    bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_Y);
                    return;
                }
                NFCL0770CommonLogic.findArAcctDtInfo(bizMsg, new AR_ACCT_DTTMsg());
            }

            bizMsg.dealRcptAmt.setValue(NFCL0770CommonLogic.getNewScale(bizMsg.dealRcptAmt.getValue()));
            bizMsg.dealApplyAmt_H1.setValue(NFCL0770CommonLogic.getNewScale(bizMsg.dealApplyAmt_H1.getValue()));
            bizMsg.dealApplyAdjAmt_H1.setValue(NFCL0770CommonLogic.getNewScale(bizMsg.dealApplyAdjAmt_H1.getValue()));
            bizMsg.dealRfAmt.setValue(NFCL0770CommonLogic.getNewScale(bizMsg.dealRfAmt.getValue()));
            bizMsg.dealRcptRmngBalAmt.setValue(NFCL0770CommonLogic.getNewScale(bizMsg.dealRcptRmngBalAmt.getValue()));

            NFCL0770CommonLogic.setApplyGrsAmt(globalMsg, bizMsg, blIni, ROW_STS.ALL.toString());

            bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_Y);

            // make cash app GL Date
            AR_ACCT_DTTMsg outArAcctDtMsg = NFCL0770CommonLogic.findArAcctDtInfo(bizMsg, new AR_ACCT_DTTMsg());

            if (null == outArAcctDtMsg || !RTNCD_NORMAL.equals(outArAcctDtMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0041E", null);
                bizMsg.xxPageShowFromNum.clear();
                bizMsg.xxPageShowToNum.clear();
                bizMsg.xxPageShowOfNum.clear();
                bizMsg.xxRsltStsCd_H1.setValue(SCRN_STATUS_N);
                return;
            }

            String depositDate = bizMsg.glDt_H1.getValue();
            String depositYM = NFCL0770CommonLogic.getYearMonth(depositDate);
            String acctYM = NFCL0770CommonLogic.getYearMonth(outArAcctDtMsg.acctDt.getValue());
            String batProcDt = ZYPDateUtil.getBatProcDate(bizMsg.glblCmpyCd_H1.getValue());
            String batProcYM = NFCL0770CommonLogic.getYearMonth(batProcDt);
            String preBatProcYM = NFCL0770CommonLogic.getYearMonth(NFCL0770CommonLogic.getBeforeMonth(batProcDt, "yyyyMMdd"));

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
        } else {
            setInitialData(bizMsg, globalMsg);
        }

        S21UserInfo userInfo = getContextUserInfo();
        NFCL0770CommonLogic.startCustCdLock(bizMsg, userInfo);

        setAftDeclPntDigitNum(bizMsg);

        if (isAfterProc) {
            NFCL0770CommonLogic.setCalc(bizMsg, globalMsg);
        }

        EZDDebugOutput.println(1, "doProcess_NFCL0770_INIT================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770_NFCL5050(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770_NFCL5050================================START", this);
        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        // add start 2022/09/02 QC#60403
        int selectLine = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        // add end 2022/09/02 QC#60403

        if (CMN_CLOSE.equals(bizMsg.xxCtlNm_H1.getValue())) {
            int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
            NFCL0770CommonLogic.checkArCustRefNumAll(globalMsg);
            NFCL0770CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
            return;
        }

        // del start 2022/09/02 QC#60403
        //if (globalMsg.A.getValidCount() == globalMsg.A.length()) {
        //    bizMsg.setMessageInfo("NFCM0110E", null);
        //    return;
        //}
        // del end 2022/09/02 QC#60403

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

        int currentDataCount = 0;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!isBlankLine(globalMsg.A.no(i))) {
                currentDataCount++;
            }
        }
        int allDataCount = currentDataCount + returnValueCount;

        if (MAX_LINE_DISPLAY < allDataCount) {
            bizMsg.setMessageInfo("NFCM0110E", null);
            return;
        }

        if (AR_ADJ_TRX_TP.DEDUCTION.equals(bizMsg.arTrxTpCd_P3.getValue())) {

            NFCL0770SMsg cloneMsg = (NFCL0770SMsg) globalMsg.clone();

            ArrayList adjAndDedData = getAdjAndDeduction(cloneMsg, bizMsg);
            ArrayList adjAndDedDataList = (ArrayList) adjAndDedData.get(1);

            for (int i = 0; i < adjAndDedDataList.size(); i++) {

                NFCL0770_ASMsg adjAndDedSMsg = (NFCL0770_ASMsg) adjAndDedDataList.get(i);

                if (AR_ADJ_TRX_TP.DEDUCTION.equals(adjAndDedSMsg.arAdjTrxTpCd_BK.getValue())) {

                    if (bizMsg.arTrxNum_P3.getValue().equals(adjAndDedSMsg.arTrxNum.getValue())) {
                        bizMsg.setMessageInfo("NFCM0113E", null);
                        return;
                    }
                }
            }
        }

        NFCL0770CommonLogic.setPage(globalMsg, bizMsg);

        Map<String, Object> inFindArTrxBalMap = NFCL0770CommonLogic.setfindArTrxBal(bizMsg);

        globalMsg.B.clear();

        S21SsmEZDResult outTrxBalData = NFCL0770Query.getInstance().findArTrxBal(inFindArTrxBalMap, globalMsg);
        if (outTrxBalData.isCodeNotFound()) {
            bizMsg.setMessageInfo("NFCM0059E", null);
        }

        int dataCount = 0;
        int index = 0;
        int selectIndex = bizMsg.xxCellIdx_H1.getValueInt();
        ArrayList<Integer> listAddRow = new ArrayList<Integer>();

        if (outTrxBalData.isCodeNormal()) {

            int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());

            setScaleOfAmt(globalMsg.A.no(pagenationFrom + selectIndex));

            dataCount = outTrxBalData.getQueryResultCount();
            for (int i = 0; i < outTrxBalData.getQueryResultCount(); i++) {
                if (i == 0) {
                    //Set to Selected Row
                    index = pagenationFrom + selectIndex;
                    listAddRow.add(index);
                    dataCount = globalMsg.A.getValidCount();
                } else {
                    index = pagenationFrom + selectIndex + i;
                    // mod start 2022/09/02 QC#60403
                    //for (; index <= globalMsg.A.length(); index++) {
                    for (; index < globalMsg.A.length(); index++) {
                    // mod end 2022/09/02 QC#60403
                        if (isBlankLine(globalMsg.A.no(index))) {
                            break;
                        }
                    }
                    // add start 2022/09/02 QC#60403
                    if (index >= globalMsg.A.length()) {
                        bizMsg.setMessageInfo("NFCM0110E", null);
                        return;
                    }
                    // add end 2022/09/02 QC#60403
                    globalMsg.A.no(index).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
                    listAddRow.add(index);
                    if (globalMsg.A.getValidCount() <= index) {
                        dataCount = index + 1;
                    }
                }

                globalMsg.A.no(index).xxChkBox.clear();
                globalMsg.A.no(index).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);

                NFCL0770CommonLogic.copyResultMsg(globalMsg, index, i);

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
                }

                String arCashApplyStsCd = "";
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).arCashApplyStsCd_B2)) {
                    arCashApplyStsCd = globalMsg.B.no(i).arCashApplyStsCd_B2.getValue();
                }

                if (NFCL0770CommonLogic.isCashDiscSchd(dealApplyGrsAmt, dealApplyCrAmt, dealApplyAdjAmtA3, dealOrigGrsAmt, dealRmngBalGrsAmt, arCashApplyStsCd)) {

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

                NFCL0770CommonLogic.setDiff(globalMsg.A.no(index), MODE_ENTRY);
                NFCL0770CommonLogic.setClearItemBySMsg(globalMsg.A.no(index));
            }
        }

        globalMsg.A.setValidCount(dataCount);

        NFCL0770CommonLogic.checkArCustRefNumAll(globalMsg);

        NFCL0770CommonLogic.setPageData(globalMsg, bizMsg, globalMsg.A.getValidCount());

        NFCL0770CommonLogic.setCalc(bizMsg, globalMsg);
        NFCL0770CommonLogic.setCalcFor5050(bizMsg, globalMsg, listAddRow);

        // mod start 2022/09/02 QC#60403
        //int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        int pagenationFrom = selectLine;
        // mod end 2022/09/02 QC#60403
        NFCL0770CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        // add start 2022/09/02 QC#60403
        if ((globalMsg.A.getValidCount() % bizMsg.A.length()) > 0) {
            addInitDetail(cMsg, sMsg);
        }
        // add end 2022/09/02 QC#60403

        EZDDebugOutput.println(1, "doProcess_NFCL0770_NFCL5050================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_DownloadCSV(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_DownloadCSV================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        NFCL0770CommonLogic.setPage(globalMsg, bizMsg);

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("NFCL0770"), CSV);

        NFCL0770F00FMsg fMsg = new NFCL0770F00FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(NFCL0770CommonLogic.getCsvHeader());
        globalMsg.C.clear();

        S21SsmEZDResult outRcptData = NFCL0770Query.getInstance().findDownLoadInfoData(bizMsg, globalMsg);

        if (RTNCD_NOT_FOUND.equals(outRcptData.getResultCode())) {
            bizMsg.setMessageInfo("NFCM0053E");
            return;
        }

        for (int i = 0; i < globalMsg.C.getValidCount(); i++) {
            fMsg.clear();
            NFCL0770CommonLogic.copyCSVDown(bizMsg, globalMsg, i);

            EZDMsg.copy(globalMsg.C.no(i), null, fMsg, null);

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

            csvOutFile.write();
        }

        csvOutFile.close();

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_DownloadCSV================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_PagePrev================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        if (!SCRN_MODE_CANCEL.equals(bizMsg.xxModeInd_H1.getValue())) {
            boolean ret = NFCL0770CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
            if (!ret) {
                NFCL0770CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                return;
            }
        }

        pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt()) - bizMsg.A.length();
        NFCL0770CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        NFCL0770CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_PagePrev================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_PageNext================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        if (pagenationFrom != -1) {
            NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            if (!SCRN_MODE_CANCEL.equals(bizMsg.xxModeInd_H1.getValue())) {
                boolean ret = NFCL0770CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
                if (!ret) {
                    NFCL0770CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }
            }
        }

        pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt()) + bizMsg.A.length();
        NFCL0770CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

        NFCL0770CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_PageNext================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_PageJump================================START", this);
        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        int xxPageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt();
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum_H1.getValueInt());

        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(xxPageShowFromNum);
        if (pagenationFrom != -1) {
            NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            if (!SCRN_MODE_CANCEL.equals(bizMsg.xxModeInd_H1.getValue())) {
                boolean ret = NFCL0770CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
                if (!ret) {
                    NFCL0770CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }
            }
        }

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

        NFCL0770CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_PageJump================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_CMN_Return(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_CMN_Return================================START", this);

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_CMN_Return================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_CalcGrossAmount(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_CalcGrossAmount================================START", this);

        NFCL0770CommonLogic.calcGrossAmount((NFCL0770CMsg) cMsg, (NFCL0770SMsg) sMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_CalcGrossAmount================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_NFCL5140(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_NFCL5140================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        if (CMN_CLOSE.equals(bizMsg.xxCtlNm_H1.getValue())) {
            int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
            NFCL0770CommonLogic.checkArCustRefNumAll(globalMsg);
            NFCL0770CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
            return;
        }

        int index = 0;
        for (; index <= globalMsg.A.getValidCount(); index++) {
            if (isBlankLine(globalMsg.A.no(index))) {
                break;
            }
        }

        if (index >= globalMsg.A.length()) {
            bizMsg.setMessageInfo("NFCM0110E", null);
            return;
        }

        NFCL0770CommonLogic.setPage(globalMsg, bizMsg);

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
                }

                globalMsg.A.no(index + i).xxDealApplyAmtNum_A1.setValue(bizMsg.dealApplyAmt_P1.no(i).getValue().setScale(2, BigDecimal.ROUND_DOWN));
                globalMsg.A.no(index + i).arCustRefNum.setValue(bizMsg.arCustRefNum_P1.no(i).getValue());
                globalMsg.A.no(index + i).arCustRefNum_BK.setValue(bizMsg.arCustRefNum_P1.no(i).getValue());
                globalMsg.A.no(index + i).arAdjTpCd_A3.setValue(bizMsg.arAdjTpCd_P1.no(i).getValue());
                String tpNm = NFCL0770CommonLogic.getArAdjTpNm(bizMsg, bizMsg.arAdjTpCd_P1.no(i).getValue());

                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index + i).arAdjTpNm_A3, tpNm);

                globalMsg.A.no(index + i).cashAppGlDt_A1.setValue(bizMsg.cashAppGlDt_BK.getValue());
                globalMsg.A.no(index + i).adjCmntTxt_BK.setValue(bizMsg.adjCmntTxt_P1.no(i).getValue());
                globalMsg.A.no(index + i).tocCd_A1.setValue(bizMsg.tocCd_P1.no(i).getValue());
                globalMsg.A.no(index + i).coaProdCd_BK.setValue(bizMsg.coaProdCd_P1.no(i).getValue());
                // START 2022/11/28 R.Takau [QC#57252,ADD]
                globalMsg.A.no(index + i).xxCmntTxt_A1.setValue(bizMsg.xxCmntTxt_P1.no(i).getValue());
                // END 2022/11/28 R.Takau [QC#57252,ADD]
                globalMsg.A.no(index + i).xxPgFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
                globalMsg.A.no(index + i).xxPgFlg_A2.setValue(ZYPConstant.FLG_OFF_N);
                globalMsg.A.no(index + i).xxPgFlg_A3.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                break;
            }
        }

        if (globalMsg.A.getValidCount() <= index + i) {
            globalMsg.A.setValidCount(index + i);
        }

        NFCL0770CommonLogic.checkArCustRefNumAll(globalMsg);

        NFCL0770CommonLogic.setPageData(globalMsg, bizMsg, globalMsg.A.getValidCount());

        NFCL0770CommonLogic.setCalc(bizMsg, globalMsg);

        // add start 2022/09/02 QC#60403
        if ((globalMsg.A.getValidCount() % bizMsg.A.length()) > 0) {
            addInitDetail(cMsg, sMsg);
        }
        // add end 2022/09/02 QC#60403

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_NFCL5140================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_DeleteLines(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_DeleteLines================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        // NFCL0770CommonLogic.setPage(globalMsg, bizMsg);
        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL0770CommonLogic.deleteBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        int validCount = globalMsg.A.getValidCount();
        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(globalMsg.A, XX_CHK_BOX, ZYPConstant.CHKBOX_ON_Y);
        int outDeleteRows = ZYPTableUtil.deleteRows(globalMsg.A, outGetSelectedRows);
        globalMsg.A.setValidCount(validCount - outDeleteRows);

        pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        if (pagenationFrom > 1 && outDeleteRows == bizMsg.A.getValidCount()) {
            pagenationFrom = pagenationFrom - bizMsg.A.length();
        }
        NFCL0770CommonLogic.setPageData(globalMsg, bizMsg, pagenationFrom);

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_DeleteLines================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_NFCL5040(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_NFCL5040================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        int index = pagenationFrom + Integer.parseInt(bizMsg.xxSelNum.getValue());

        globalMsg.A.no(index).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
        globalMsg.A.no(index).dealCashDiscAmt_A1.setValue(NFCL0770CommonLogic.getNewScale(bizMsg.dealCashDiscAmt_P2.getValue()));
        globalMsg.A.no(index).cashDiscPct_A1.setValue(bizMsg.cashDiscPct_P2.getValue());
        globalMsg.A.no(index).arCashDiscSchdSqNum_BK.setValue(bizMsg.arCashDiscSchdSqNum_P2.getValue());

        BigDecimal cashDiscDetail = NFCL0770CommonLogic.getNewScale(globalMsg.A.no(index).dealCashDiscAmt_A1.getValue());
        BigDecimal trxAdjDetail = NFCL0770CommonLogic.getNewScale(globalMsg.A.no(index).xxDealApplyAdjAmtNum_A1.getValue());
        // Apply Auto calculation
        BigDecimal balanceDetail = NFCL0770CommonLogic.getNewScale(globalMsg.A.no(index).dealRmngBalGrsAmt.getValue());

        BigDecimal applyDetail = balanceDetail.subtract(cashDiscDetail).subtract(trxAdjDetail);
        globalMsg.A.no(index).xxDealApplyAmtNum_A1.setValue(applyDetail);

        NFCL0770CommonLogic.setClearItemBySMsg(globalMsg.A.no(index));

        NFCL0770CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        NFCL0770CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_NFCL5040================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_CMN_Submit================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        if (MODE_ONE.equals(bizMsg.xxModeInd_H1.getValue()) || MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {

            if (NFCL0770CommonLogic.checkArCustRefNumAll(globalMsg)) {

                AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
                for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

                    String xxModeInd = globalMsg.A.no(i).xxModeInd_BK.getValue();

                    if (DETAIL_MODE_DEDUCTION.equals(xxModeInd) || DETAIL_MODE_ADJUSTMENT.equals(xxModeInd) || DETAIL_MODE_ONACOUNT.equals(xxModeInd)) {

                        inArApplyIntfcWrkMsg.clear();
                        inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
                        inArApplyIntfcWrkMsg.applyGrpKey.setValue(globalMsg.A.no(i).applyGrpKey_BK.getValue());
                        inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(globalMsg.A.no(i).applyGrpSubPk_BK.getValue());

                        AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFCL0770CommonLogic.findArApplyIntfcWrkInfo(inArApplyIntfcWrkMsg);

                        if (null != outArApplyIntfcWrkMsg && RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
                            if (DETAIL_MODE_DEDUCTION.equals(xxModeInd) || DETAIL_MODE_ONACOUNT.equals(xxModeInd)) {
                                globalMsg.A.no(i).arTrxNum.setValue(outArApplyIntfcWrkMsg.invNum.getValue());

                            } else {
                                globalMsg.A.no(i).arTrxNum.setValue(outArApplyIntfcWrkMsg.arAdjNum.getValue());
                            }
                        }
                    }
                }
                EZDMsg.copy(globalMsg, null, bizMsg, null);
            }
        }
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_CMN_Submit================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_OpenWin_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_OpenWin_Search================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;
        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());

        if (pagenationFrom > -1) {
            NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            NFCL0770CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
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

        NFCL0770CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);

        NFCL0770CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_OpenWin_Search================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_OpenWin_Upload(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_OpenWin_Upload================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;

        getUpldCsvId(bizMsg, AR_CSV_UPLD_CASHAPP_KEY);

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_OpenWin_Upload================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean checkReceiptNumber(NFCL0770CMsg bizMsg) {

        Map<String, String> inFindRcpDataMap = new HashMap<String, String>();
        inFindRcpDataMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        inFindRcpDataMap.put("rcptNum", bizMsg.rcptNum.getValue());
        inFindRcpDataMap.put("arApplyTp_Adjustment", AR_APPLY_TP.ADJUSTMENT);
        inFindRcpDataMap.put("arAdjTrxTp_OnAccount", AR_ADJ_TRX_TP.ON_ACCOUNT);
        inFindRcpDataMap.put("arCashApplyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        inFindRcpDataMap.put("arCashApplyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        S21SsmEZDResult outRcptData = NFCL0770Query.getInstance().findRcptData(inFindRcpDataMap, bizMsg);

        if (RTNCD_NOT_FOUND.equals(outRcptData.getResultCode())) {
            bizMsg.setMessageInfo("NFCM0029E", null);
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.voidFlg_H1.getValue())) {
            bizMsg.setMessageInfo("NFCM0037E", null);
            return false;
        }

        //if (!bizMsg.dealRfAmt.isClear() && !NFCL0770CommonLogic.isZero(bizMsg.dealRfAmt.getValue())) {
        //    bizMsg.setMessageInfo("NFCM0036E", null);
        //    return false;
        //} else {
        //    // do nothing
        //}

        if (MODE_ENTRY.equals(bizMsg.xxModeInd_H1.getValue())) {

            if (bizMsg.dealRcptRmngBalAmt.isClear() || NFCL0770CommonLogic.isZero(bizMsg.dealRcptRmngBalAmt.getValue())) {
                bizMsg.setMessageInfo("NFCM0034E", null);
                return false;
            }
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @return NFCL0770_ASMsgArray
     */
    private ArrayList getInvData(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg) {

        ArrayList<Serializable> reList = new ArrayList<Serializable>();
        ArrayList<NFCL0770_ASMsg> invDataSMsgList = new ArrayList<NFCL0770_ASMsg>();

        Map<String, String> inFindInvDataMap = new HashMap<String, String>();
        inFindInvDataMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        inFindInvDataMap.put("rcptNum", bizMsg.rcptNum.getValue());
        inFindInvDataMap.put("arScrCancFlg", ZYPConstant.FLG_OFF_N);
        inFindInvDataMap.put("arTrxTpCdAdj", AR_ADJ_TRX_TP.ADJUSTMENT);
        inFindInvDataMap.put("arTrxTpCdDed", AR_ADJ_TRX_TP.DEDUCTION);
        inFindInvDataMap.put("arTrxTpCdAcc", AR_TRX_TP.ON_ACCOUNT);
        inFindInvDataMap.put("arTrxTpCdCrm", AR_TRX_TP.CREDIT_MEMO);
        inFindInvDataMap.put("arTrxTpCdDem", AR_TRX_TP.DEBIT_MEMO);
        inFindInvDataMap.put("arTrxTpCdInv", AR_TRX_TP.INVOICE);

        S21SsmEZDResult outFindInvData = NFCL0770Query.getInstance().findInvData(inFindInvDataMap, globalMsg);

        if (outFindInvData.isCodeNormal()) {
            reList.add(outFindInvData.getQueryResultCount());

            NFCL0770_ASMsgArray outData = (NFCL0770_ASMsgArray) outFindInvData.getResultObject();
            for (int i = 0; i < outFindInvData.getQueryResultCount(); i++) {
                if (i == globalMsg.A.length()) {
                    break;
                } else {
                    NFCL0770_ASMsg msg = (NFCL0770_ASMsg) outData.getMyComponent();
                    EZDMsg.copy(outData.no(i), null, msg, null);
                    invDataSMsgList.add(msg);
                }
            }
        } else {
            reList.add(outFindInvData.getQueryResultCount());
        }

        for (int i = 0; i < invDataSMsgList.size(); i++) {

            NFCL0770_ASMsg invDataSMsg = invDataSMsgList.get(i);

            Map<String, Comparable> inFindInvDataByAdjMap = new HashMap<String, Comparable>();
            inFindInvDataByAdjMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
            inFindInvDataByAdjMap.put("arCashAppPk", invDataSMsg.arCashAppPk_BK.getValue());
            inFindInvDataByAdjMap.put("arTrxNum", invDataSMsg.arTrxNum.getValue());
            inFindInvDataByAdjMap.put("arTrxBalPk", invDataSMsg.arTrxBalPk_BK.getValue());
            inFindInvDataByAdjMap.put("arScrCancFlg", ZYPConstant.FLG_OFF_N);

            S21SsmEZDResult outFindInvDataByAdj = NFCL0770Query.getInstance().findInvDataByAdj(inFindInvDataByAdjMap, invDataSMsg);

            if (outFindInvDataByAdj.isCodeNormal()) {
                NFCL0770_ASMsg invDataByAdjSMsg = (NFCL0770_ASMsg) outFindInvDataByAdj.getResultObject();
                invDataSMsg.arAdjTpCd_A1.setValue(invDataByAdjSMsg.arAdjTpCd_A1.getValue());
                invDataSMsg.arAdjNum_BK.setValue(invDataByAdjSMsg.arAdjNum_BK.getValue());
                invDataSMsg.arAdjTrxTpCd_BK.setValue(invDataByAdjSMsg.arAdjTrxTpCd_BK.getValue());
                invDataSMsg.arAdjTpCd_BK.setValue(invDataByAdjSMsg.arAdjTpCd_BK.getValue());
                invDataSMsg.dealApplyAdjAmt_BK.setValue(invDataByAdjSMsg.dealApplyAdjAmt_BK.getValue());
                invDataSMsg.arAdjTpNm_A1.setValue(invDataByAdjSMsg.arAdjTpNm_A1.getValue());
                invDataSMsg.arAdjTpNm_BK.setValue(invDataByAdjSMsg.arAdjTpNm_BK.getValue());
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
     * @return NFCL0770_ASMsgArray
     */
    private ArrayList getAdjAndDeduction(NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg) {

        ArrayList<Serializable> reList = new ArrayList<Serializable>();
        ArrayList<NFCL0770_ASMsg> adjAndDedSMsgList = new ArrayList<NFCL0770_ASMsg>();

        Map<String, String> inFindAdjAndDeductionMap = new HashMap<String, String>();
        inFindAdjAndDeductionMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        inFindAdjAndDeductionMap.put("rcptNum", bizMsg.rcptNum.getValue());
        inFindAdjAndDeductionMap.put("arScrCancFlg", ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult outFindAdjAndDeduction = NFCL0770Query.getInstance().findAdjAndDeduction(inFindAdjAndDeductionMap, globalMsg);

        if (outFindAdjAndDeduction.isCodeNormal()) {
            reList.add(outFindAdjAndDeduction.getQueryResultCount());

            NFCL0770_ASMsgArray outData = (NFCL0770_ASMsgArray) outFindAdjAndDeduction.getResultObject();
            for (int i = 0; i < outFindAdjAndDeduction.getQueryResultCount(); i++) {
                if (i == globalMsg.A.length()) {
                    break;
                } else {
                    NFCL0770_ASMsg msg = (NFCL0770_ASMsg) outData.getMyComponent();
                    EZDMsg.copy(outData.no(i), null, msg, null);
                    adjAndDedSMsgList.add(msg);
                }
            }
        } else {
            reList.add(outFindAdjAndDeduction.getQueryResultCount());
        }

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();

        for (int i = 0; i < adjAndDedSMsgList.size(); i++) {

            NFCL0770_ASMsg adjAndDedSMsg = adjAndDedSMsgList.get(i);

            if (AR_ADJ_TRX_TP.DEDUCTION.equals(adjAndDedSMsg.arTrxTpCd.getValue()) || AR_TRX_TP.ON_ACCOUNT.equals(adjAndDedSMsg.arTrxTpCd.getValue())) {

                inArTrxBalMsg.clear();
                inArTrxBalMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd_H1.getValue());
                inArTrxBalMsg.setConditionValue("attAdjNum01", adjAndDedSMsg.arAdjNum_BK.getValue());

                AR_TRX_BALTMsgArray outArTrxBalMsg = NFCL0770CommonLogic.findArTrxBalListForDeduction(inArTrxBalMsg);

                if (null != outArTrxBalMsg && 0 != outArTrxBalMsg.length()) {
                    adjAndDedSMsg.arTrxNum.setValue(outArTrxBalMsg.no(0).arTrxNum.getValue());
                    adjAndDedSMsg.grpInvNum.setValue(outArTrxBalMsg.no(0).grpInvNum.getValue());
                    adjAndDedSMsg.arTrxBalPk_BK.setValue(outArTrxBalMsg.no(0).arTrxBalPk.getValue());
                    adjAndDedSMsg.invTrxBalPk_BK.setValue(outArTrxBalMsg.no(0).arTrxBalPk.getValue());
                    adjAndDedSMsg.invTrxBalLastUpdTs_BK.setValue(outArTrxBalMsg.no(0).ezUpTime.getValue());
                    adjAndDedSMsg.invTrxBalTz_BK.setValue(outArTrxBalMsg.no(0).ezUpTimeZone.getValue());
                    adjAndDedSMsg.arCustRefNum.setValue(outArTrxBalMsg.no(0).arCustRefNum.getValue());

                    if (AR_TRX_TP.ON_ACCOUNT.equals(adjAndDedSMsg.arTrxTpCd.getValue())) {
                        adjAndDedSMsg.arCustRefNum.setValue(ZYPCodeDataUtil.getName(AR_TRX_TP.class, getGlobalCompanyCode(), AR_TRX_TP.ON_ACCOUNT));
                    }
                    adjAndDedSMsg.arTrxDt.setValue(outArTrxBalMsg.no(0).arTrxDt.getValue());
                }
                if (AR_ADJ_TRX_TP.DEDUCTION.equals(adjAndDedSMsg.arTrxTpCd.getValue())) {
                    adjAndDedSMsg.xxModeInd_BK.setValue(DETAIL_MODE_DEDUCTION);
                } else {
                    adjAndDedSMsg.xxModeInd_BK.setValue(DETAIL_MODE_ONACOUNT);
                }

            } else {
                adjAndDedSMsg.xxModeInd_BK.setValue(DETAIL_MODE_ADJUSTMENT);
                adjAndDedSMsg.arTrxDt.setValue(adjAndDedSMsg.glDt_A1.getValue());
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
    private void setScaleOfAmt(NFCL0770_ASMsg asMsg) {

        asMsg.dealOrigGrsAmt.setValue(NFCL0770CommonLogic.getNewScale(asMsg.dealOrigGrsAmt.getValue()));
        asMsg.dealApplyGrsAmt.setValue(NFCL0770CommonLogic.getNewScale(asMsg.dealApplyGrsAmt.getValue()));
        asMsg.dealApplyCrAmt.setValue(NFCL0770CommonLogic.getNewScale(asMsg.dealApplyCrAmt.getValue()));
        asMsg.dealNetSlsAmt.setValue(NFCL0770CommonLogic.getNewScale(asMsg.dealNetSlsAmt.getValue()));
        asMsg.dealFrtAmt.setValue(NFCL0770CommonLogic.getNewScale(asMsg.dealFrtAmt.getValue()));
        asMsg.dealTaxAmt.setValue(NFCL0770CommonLogic.getNewScale(asMsg.dealTaxAmt.getValue()));
        asMsg.dealRmngBalGrsAmt.setValue(NFCL0770CommonLogic.getNewScale(asMsg.dealRmngBalGrsAmt.getValue()));
        asMsg.xxDealApplyAmtNum_A1.setValue(NFCL0770CommonLogic.getNewScale(asMsg.xxDealApplyAmtNum_A1.getValue()));
        asMsg.dealCashDiscAmt_A1.setValue(NFCL0770CommonLogic.getNewScale(asMsg.dealCashDiscAmt_A1.getValue()));
        asMsg.cashDiscPct_A1.setValue(NFCL0770CommonLogic.getNewScale(asMsg.cashDiscPct_A1.getValue()));
        asMsg.xxDealApplyAdjAmtNum_A1.setValue(NFCL0770CommonLogic.getNewScale(asMsg.xxDealApplyAdjAmtNum_A1.getValue()));
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param invData ArrayList
     * @param adjAndDedData ArrayList
     * @return NFCL0770_ASMsgArray
     */
    private boolean chkDetailByDate(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg, String glDt) {

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
            }
        } else {

            if (xxBatProcYrMth.compareTo(xxGlYrmth) < 0 || xxBefBatProcYrMth.compareTo(xxGlYrmth) > 0) {
                return false;
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
     * @return NFCL0770_ASMsgArray
     */
    private ArrayList getSortDara(ArrayList invData, ArrayList adjAndDedData, NFCL0770SMsg globalMsg, NFCL0770CMsg bizMsg) {

        ArrayList<NFCL0770_ASMsg> sorList = new ArrayList<NFCL0770_ASMsg>();
        // ACC, ADJ, DED, CRM, DEM, INV
        int invIndex = 0;
        int adjIndex = 0;

        if (MODE_ONE.equals(bizMsg.xxModeInd_H1.getValue())) {
            // INV
            for (; invIndex < invData.size(); invIndex++) {
                if (globalMsg.A.length() > sorList.size()) {
                    NFCL0770_ASMsg msg = (NFCL0770_ASMsg) invData.get(invIndex);
                    if (AR_TRX_TP.INVOICE.equals(msg.arTrxTpCd.getValue())) {
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
                    NFCL0770_ASMsg msg = (NFCL0770_ASMsg) adjAndDedData.get(adjIndex);
                    if (AR_TRX_TP.INVOICE.equals(msg.arTrxTpCd.getValue())) {
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
        }

        // ACC
        for (; invIndex < invData.size(); invIndex++) {
            if (globalMsg.A.length() > sorList.size()) {
                NFCL0770_ASMsg msg = (NFCL0770_ASMsg) invData.get(invIndex);
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
                NFCL0770_ASMsg msg = (NFCL0770_ASMsg) adjAndDedData.get(adjIndex);
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
                NFCL0770_ASMsg msg = (NFCL0770_ASMsg) invData.get(invIndex);
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
                NFCL0770_ASMsg msg = (NFCL0770_ASMsg) adjAndDedData.get(adjIndex);
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
                NFCL0770_ASMsg msg = (NFCL0770_ASMsg) invData.get(invIndex);
                if (AR_ADJ_TRX_TP.DEDUCTION.equals(msg.arTrxTpCd.getValue())) {
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
                NFCL0770_ASMsg msg = (NFCL0770_ASMsg) adjAndDedData.get(adjIndex);
                if (AR_ADJ_TRX_TP.DEDUCTION.equals(msg.arTrxTpCd.getValue())) {
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

        // CRM
        for (; invIndex < invData.size(); invIndex++) {
            if (globalMsg.A.length() > sorList.size()) {
                NFCL0770_ASMsg msg = (NFCL0770_ASMsg) invData.get(invIndex);
                if (!ZYPCommonFunc.hasValue(msg.cashAppGlDt_A1)) {
                    continue;
                }
                sorList.add(msg);
            } else {
                break;
            }
        }

        return sorList;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param arCsvUpldKey String
     * @return void
     */
    private void getUpldCsvId(NFCL0770CMsg bizMsg, String arCsvUpldKey) {
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
    private void doProcess_NFCL0770Scrn00_AddOneLine(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_AddOneLine================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        int index = bizMsg.xxCellIdx_H1.getValueInt();
        if (index >= 0) {
            // mod start 2022/09/02 QC#60403
            //if (BigDecimal.ZERO.compareTo(bizMsg.A.no(index).xxDealApplyAmtNum_A1.getValue()) == 0 && ZYPCommonFunc.hasValue(bizMsg.A.no(index).arCustRefNum)) {
            if (RECORD_STS.NEW.getValue().equals(bizMsg.A.no(index).xxArCashApplyStsTxt.getValue()) && ZYPCommonFunc.hasValue(bizMsg.A.no(index).arCustRefNum)) {
            // mod end 2022/09/02 QC#60403
                doProcess_NFCL0770Scrn00_SearchTrxLine(cMsg, sMsg);
            } else {
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index + pagenationFrom).xxDealApplyAmtNum_A1, bizMsg.A.no(index).xxDealApplyAmtNum_A1);
            }
        }

        boolean rval = true;
        if (pagenationFrom > -1) {
            NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            //rval = NFCL0770CommonLogic.setArTrxAllLine(bizMsg, globalMsg);

            // All>>
            if (!SCRN_MODE_CANCEL.equals(bizMsg.xxModeInd_H1.getValue())) {
                boolean ret = NFCL0770CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
                if (!ret) {
                    NFCL0770CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
                    return;
                }
            }
        }
        NFCL0770CommonLogic.setGlobalMsgToBizMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

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

            bizMsg.xxPageShowFromNum.setValue(NFCL0770CommonLogic.getPagenationFrom(bizMsg, count));
            globalMsg.A.setValidCount(allDataCount);
            globalMsg.A.no(allDataCount - 1).xxArCashApplyStsTxt.setValue(RECORD_STS.NEW.getValue());
        } else {
            bizMsg.setMessageInfo("ZZM9037E", null);
        }

        pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL0770CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);

        if (!"E".equals(bizMsg.getMessageKind())) {
            NFCL0770CommonLogic.setCalc(bizMsg, globalMsg);
        }

        // add start 2022/09/02 QC#60403
        if ((globalMsg.A.getValidCount() % bizMsg.A.length()) > 0) {
            addInitDetail(cMsg, sMsg);
        }
        // add end 2022/09/02 QC#60403

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_AddOneLine================================END", this);

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_SearchTrxLine(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_SearchTrxLine================================START", this);
        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        if (!ZYPCommonFunc.hasValue(bizMsg.payerCustCd)) {
            bizMsg.payerCustCd.setErrorInfo(1, "NFCM0504E", new String[] {"Customer"});
            return;
        } else {
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctNm)) {
                NFCL0770CommonLogic.searchAddressForBillToCustomerAccount(bizMsg);
                if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctNm)) {
                    return;
                }
            }
        }

        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        int index = bizMsg.xxCellIdx_H1.getValueInt();

        String arCustRefNum = bizMsg.A.no(index).arCustRefNum.getValue();
        if (!ZYPCommonFunc.hasValue(arCustRefNum)) {
            bizMsg.A.no(index).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
        }
        if (!bizMsg.A.no(index).arCustRefNum.getValue().equals(bizMsg.A.no(index).arCustRefNum_BK.getValue())) {
            bizMsg.A.no(index).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
            bizMsg.A.no(index).arCustRefNum.setValue(arCustRefNum);
        }

        NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        boolean notError = NFCL0770CommonLogic.getArTrxBalSearch(bizMsg, globalMsg, index + pagenationFrom, bizMsg.xxPageShowFromNum.getValueInt());
        if (notError) {
            globalMsg.A.no(index + pagenationFrom).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
            ArrayList<Integer> listAddRow = new ArrayList<Integer>();
            listAddRow.add(index + pagenationFrom);
            NFCL0770CommonLogic.setCalcFor5050(bizMsg, globalMsg, listAddRow);
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

        NFCL0770CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_SearchTrxLine================================END", this);
    }


    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_OpenWin_New(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_OpenWin_New================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;
        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());

        if (pagenationFrom != -1) {
            pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            NFCL0770CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
            NFCL0770CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
        }

        NFCL0770CommonLogic.setCalc(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_OpenWin_New================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0770Scrn00_OpenWin_Select(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_OpenWin_Select================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;
        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        if (pagenationFrom != -1) {
            pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
            NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

            // All>>
            NFCL0770CommonLogic.setArTrxAllLine(bizMsg, globalMsg);
            NFCL0770CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
        }

        NFCL0770CommonLogic.setCalc(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_OpenWin_Select================================END", this);
    }

    private static void setAftDeclPntDigitNum(NFCL0770CMsg bizMsg) {
        if (bizMsg.ccyCd.isClear()) {
            // This logic shouldn't be used.
            bizMsg.aftDeclPntDigitNum_H1.setValue(new BigDecimal(2));

        } else {

            BigDecimal aftDeclPntDigitNum = NFCCmnMethod.getAftDeclPntDigitNum(bizMsg.glblCmpyCd_H1.getValue(), bizMsg.ccyCd.getValue());

            bizMsg.aftDeclPntDigitNum_H1.setValue(aftDeclPntDigitNum);
        }
    }

    private static void addInitDetail(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        // mod start 2022/09/02 QC#60403
//        if (!AR_CASH_APPLY_STS.APPLIED.equals(bizMsg.arCashApplyStsCd_H1.getValue())) {
//            int addLineCnt = INIT_DETAIL_COUNT;
//            addLineCnt = globalMsg.A.getValidCount() + addLineCnt;
//            for (int i = globalMsg.A.getValidCount(); i < addLineCnt; i++) {
//                globalMsg.A.no(i).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
//                globalMsg.A.no(i).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
//                globalMsg.A.no(i).xxPgFlg_A2.setValue(ZYPConstant.FLG_OFF_N);
//                globalMsg.A.no(i).xxPgFlg_A3.setValue(ZYPConstant.FLG_OFF_N);
//                globalMsg.A.no(i).xxDealApplyAmtNum_A1.setValue(BigDecimal.ZERO);
//                globalMsg.A.no(i).xxArCashApplyStsTxt.setValue(RECORD_STS.NEW.getValue());
//            }
//            globalMsg.A.setValidCount(addLineCnt);
//        }
//        NFCL0770CommonLogic.setCurrentPageOut(bizMsg, globalMsg, 0);

        int addLineCnt = (globalMsg.A.getValidCount() / bizMsg.A.length()) + 1;
        addLineCnt = addLineCnt * bizMsg.A.length();
        if (addLineCnt > globalMsg.A.length()) {
            addLineCnt = globalMsg.A.length();
        }
        for (int i = globalMsg.A.getValidCount() ; i < addLineCnt ; i++) {
            globalMsg.A.no(i).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
            globalMsg.A.no(i).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
            globalMsg.A.no(i).xxPgFlg_A2.setValue(ZYPConstant.FLG_OFF_N);
            globalMsg.A.no(i).xxPgFlg_A3.setValue(ZYPConstant.FLG_OFF_N);
            globalMsg.A.no(i).xxDealApplyAmtNum_A1.setValue(BigDecimal.ZERO);
            globalMsg.A.no(i).xxArCashApplyStsTxt.setValue(RECORD_STS.NEW.getValue());
            globalMsg.A.no(i).dealRmngBalGrsAmt.setValue(BigDecimal.ZERO);
        }
        globalMsg.A.setValidCount(addLineCnt);
        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        NFCL0770CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
        // mod end 2022/09/02 QC#60403
    }


    private void doProcess_NFCL0770Scrn00_OnClick_Chk(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;

        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxChkBox, bizMsg.A.no(i - pagenationFrom).xxChkBox);
            } else {
                break;
            }
        }
        doProcess_NFCL0770Scrn00_CalcGrossAmount(cMsg, sMsg);
    }

    private boolean isBlankLine(NFCL0770_ASMsg asMsg) {
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

    private void setInitialData(NFCL0770CMsg bizMsg, NFCL0770SMsg globalMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        bizMsg.glblCmpyCd_H1.setValue(glblCmpyCd);

        GLBL_CMPYTMsg glblCmpyTMsg = NFCL0770CommonLogic.getGlblCmpy(bizMsg);
        if (glblCmpyTMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, glblCmpyTMsg.stdCcyCd);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.rcptNum)) {
            // Mode
            bizMsg.xxModeInd_H1.setValue(MODE_ONE);
            bizMsg.xxModeNm23Txt.setValue(MODE_NAME_ONE);

            String slsDt = ZYPDateUtil.getSalesDate();
            ZYPEZDItemValueSetter.setValue(bizMsg.rcptDt, slsDt);

            ZYPEZDItemValueSetter.setValue(bizMsg.arRcptTrxTpCd, AR_RCPT_TRX_TP.OFFSET);

            // Receipt Transaction Type Name
            bizMsg.arRcptTrxTpNm.setValue(ZYPCodeDataUtil.getName(AR_RCPT_TRX_TP_PRM_NM, glblCmpyCd, bizMsg.arRcptTrxTpCd.getValue()));

            // Receipt Type Name
            bizMsg.arRcptTpCd.setValue(AR_RCPT_TP.OFFSET);
            bizMsg.arRcptTpNm.setValue(ZYPCodeDataUtil.getName(AR_RCPT_TP_PRM_NM, glblCmpyCd, bizMsg.arRcptTpCd.getValue()));

            // Receipt Status
            bizMsg.arRcptStsCd_H1.setValue(AR_RCPT_STS.UNAPPLIED);
            bizMsg.arRcptStsNm_H1.setValue(ZYPCodeDataUtil.getName(AR_RCPT_STS_NM, glblCmpyCd, bizMsg.arRcptStsCd_H1.getValue()));

            // Receipt Source
            bizMsg.arRcptSrcCd_H1.setValue(AR_RCPT_SRC.SYSTEM_CREATED);
            bizMsg.arRcptSrcNm_H1.setValue(ZYPCodeDataUtil.getName(AR_RCPT_SRC_NM, glblCmpyCd, bizMsg.arRcptSrcCd_H1.getValue()));

            // Accounting Date
            ZYPEZDItemValueSetter.setValue(bizMsg.acctDt, slsDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.glDt_H1, slsDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.glDt_H2, slsDt);

            // Receipt Nnmber
            bizMsg.rcptChkNum_H1.setValue(DEF_RECEIPT_NUM);

            // Receipt Amount
            bizMsg.dealRcptAmt.setValue(0);

            // Applied Amount
            bizMsg.xxTotAmt_H0.setValue(0);

            // Export Flag
            bizMsg.exptFlg_H1.setValue(ZYPConstant.FLG_OFF_N);

            // Search Amount @ NFCL0770
            bizMsg.dealRcptAmt.setValue(BigDecimal.ZERO);
            bizMsg.dealApplyAdjAmt_H1.setValue(BigDecimal.ZERO);
            bizMsg.dealRfAmt.setValue(BigDecimal.ZERO);
            bizMsg.dealRcptRmngBalAmt.setValue(BigDecimal.ZERO);
            bizMsg.xxTotAmt_H0.setValue(BigDecimal.ZERO);
            bizMsg.xxTotAmt_H1.setValue(BigDecimal.ZERO);
            bizMsg.xxTotAmt_H2.setValue(BigDecimal.ZERO);
            bizMsg.xxDedctAmt_S1.setValue(BigDecimal.ZERO);
            bizMsg.dealApplyAmt_H1.setValue(BigDecimal.ZERO);
        }
    }

    private void doProcess_NFCL0770Scrn00_CustomerName(NFCL0770CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.payerCustCd.getValue())) {
            return;
        }
        NFCL0770CommonLogic.searchAddressForBillToCustomerAccount(bizMsg);

    }

    // add start 2022/09/02 QC#60403
    /**
     * doProcess_NFCL0770Scrn00_Search
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NFCL0770Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_Search================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;
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

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_Search================================END", this);
    }

    /**
     * doProcess_NFCL0770Scrn00_OnBlur_Inv
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NFCL0770Scrn00_OnBlur_Inv(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_OnBlur_Inv================================START", this);

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770SMsg globalMsg = (NFCL0770SMsg) sMsg;
        int pagenationFrom = NFCL0770CommonLogic.getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        int index = bizMsg.xxCellIdx_H1.getValueInt();

        String arCustRefNum = bizMsg.A.no(index).arCustRefNum.getValue();
        if (!ZYPCommonFunc.hasValue(arCustRefNum)) {
            bizMsg.A.no(index).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
        }

        if (!arCustRefNum.equals(bizMsg.A.no(index).arCustRefNum_BK.getValue())) {
            bizMsg.A.no(index).xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
            bizMsg.A.no(index).arCustRefNum.setValue(arCustRefNum);
        }

        NFCL0770CommonLogic.setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);

        boolean notError = NFCL0770CommonLogic.getArTrxBalSearch(bizMsg, globalMsg, index + pagenationFrom, bizMsg.xxPageShowFromNum.getValueInt());
        if (notError) {
            globalMsg.A.no(index + pagenationFrom).xxChkBox.setValue(ZYPConstant.FLG_ON_Y);
            ArrayList<Integer> listAddRow = new ArrayList<Integer>();
            listAddRow.add(index + pagenationFrom);
            NFCL0770CommonLogic.setCalcFor5050(bizMsg, globalMsg, listAddRow);
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

        NFCL0770CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);

        EZDDebugOutput.println(1, "doProcess_NFCL0770Scrn00_OnBlur_Inv================================END", this);
    }
    // add end 2022/09/02 QC#60403
}
