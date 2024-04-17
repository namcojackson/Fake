/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1540.common;

import static business.servlet.NWAL1540.constant.NWAL1540Constant.BIZ_ID;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_CMN_APL;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_CMN_APR;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_CMN_DEL;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_CMN_DWL;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_CMN_RJT;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_CMN_RST;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_CMN_RTN;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_CMN_SAV;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_CMN_SUB;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_RECALC;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_SEARCH;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_STS_ACTIVE;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.BTN_STS_INACTIVE;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.SCRN_ID_00;

import java.util.List;

import parts.common.EZDGUIAttribute;
import business.servlet.NWAL1540.NWAL1540BMsg;
import business.servlet.NWAL1540.NWAL1540Bean;
import business.servlet.NWAL1540.NWAL1540_ABMsg;
import business.servlet.NWAL1540.NWAL1540_ABMsgArray;
import business.servlet.NWAL1540.constant.NWAL1540Constant.MODE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL1540CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         M.Yamada        Create          N/A
 * 2016/03/10   Fujitsu         S.Yamamoto      Create          S21_NA#2939
 * 2016/05/31   SRA             E.Inada         Update          QC#9123
 * 2016/08/03   Fujitsu         Y.Taoka         Update          QC#11636
 * 2016/09/28   Fujitsu         N.Sugiura       Update          QC#12187
 * 2016/10/18   Fujitsu         W.Honda         Update          S21_NA#15193
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWAL1540CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
//        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        // END 2022/04/18 J.Evangelista [QC#59934,MOD]
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * <pre>
     * @param handler   S21CommonHandler
     * @param scrnMsg   NWAL1540BMsg
     * </pre>
     */
    public static void initBizBtnProp(S21CommonHandler handler, NWAL1540BMsg scrnMsg) {
        setBtnProp(handler, BTN_RECALC, BTN_STS_INACTIVE);
        if (ZYPCommonFunc.hasValue(scrnMsg.trxHdrNum_IN)) {
            setBtnProp(handler, BTN_SEARCH, BTN_STS_INACTIVE);
        } else {
            setBtnProp(handler, BTN_SEARCH, BTN_STS_ACTIVE);
        }
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1540BMsg
     * @param scrnAMsgAry NWAL1540_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1540BMsg scrnMsg, NWAL1540_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1540BMsg
     * @param scrnAMsgAry NWAL1540_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1540BMsg scrnMsg, NWAL1540_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWAL1540BMsg
     * @param scrnAMsgAry NWAL1540_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1540BMsg scrnMsg, NWAL1540_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemBizLayerErr
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NWAL1540BMsg scrnMsg) {

        // Header
        scrnMsg.addCheckItem(scrnMsg.trxHdrNum);

    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException("ZZSM4300E", new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        if (funcList.contains("NWAL1540T020")) {
            return true;
        }

        return false;
    }

    /**
     * <pre>
     * @param scrnMsg   NWAL1540BMsg
     * </pre>
     */
    public static void setInputControl(NWAL1540BMsg scrnMsg) {
        boolean b = ZYPCommonFunc.hasValue(scrnMsg.xxModeCd_IN);
        scrnMsg.xxModeCd.setInputProtected(b);
        scrnMsg.trxHdrNum.setInputProtected(b);
        scrnMsg.trxHdrNum.setInputProtected(b);

        b = !MODE.ORDER.getValue().equals(scrnMsg.xxModeCd.getValue());
        scrnMsg.ordPrftVrsnNum.setInputProtected(b);

        // 2016/08/03 QC#11636
        if (CR_REBIL.CREDIT.equals(scrnMsg.crRebilCd.getValue())) {
            scrnMsg.ordPrftVrsnNum.setInputProtected(true);
        }

        scrnMsg.funcNegoDealAmt.setInputProtected(true);
        scrnMsg.totInvAmt.setInputProtected(true); // QC#9123
        scrnMsg.totFuncRepRevAmt.setInputProtected(true);
        scrnMsg.totFuncFinalFlAmt.setInputProtected(true);
        scrnMsg.totFuncRepRevAdjAmt.setInputProtected(true); // QC#7707
        scrnMsg.grsPrftPct.setInputProtected(true);
        scrnMsg.funcGrsPrftAmt.setInputProtected(true);// 2016/03/10 S21_NA#2939
        scrnMsg.xxOrdPrftFndrFeeAmt.setInputProtected(true);
        scrnMsg.xxOrdPrftIstlCrAmt.setInputProtected(true);
        scrnMsg.totFuncDlrCrAmt.setInputProtected(true);
        scrnMsg.funcAltGrsPrftAmt.setInputProtected(true);
        scrnMsg.altGrsPrftPct.setInputProtected(true);

        scrnMsg.funcAltGrsPrftAmt_RE.setInputProtected(true);
        scrnMsg.altGrsPrftPct_RE.setInputProtected(true);

        scrnMsg.totFuncMsrpAmt.setInputProtected(true);
        scrnMsg.totCostAmt.setInputProtected(true); // QC#9123
        scrnMsg.totFuncStdFlAmt.setInputProtected(true);
        scrnMsg.totFuncFlAdjAmt.setInputProtected(true);
        scrnMsg.csmpOrdFlg.setInputProtected(true);
        scrnMsg.totFuncCsmpCrAmt.setInputProtected(true);
        scrnMsg.csmpContrNum.setInputProtected(true);

        scrnMsg.totFuncByotAmt.setInputProtected(true);
        scrnMsg.totFuncSvcRevTrnsfAmt.setInputProtected(true);
        scrnMsg.totFuncSvcCostTrnsfAmt.setInputProtected(true);
        scrnMsg.totFuncProSvcAmt.setInputProtected(true);
        scrnMsg.xxOrdPrftSvcAmt.setInputProtected(true);
        scrnMsg.xxOrdPrftSplyAmt.setInputProtected(true);

        scrnMsg.xxScrItem81Txt.setInputProtected(true);
        scrnMsg.xxScrItem19Txt.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL1540_ABMsg abMsg = scrnMsg.A.no(i);

            abMsg.csmpContrNum_A.setInputProtected(true);
            abMsg.csmpPrcListCd_A.setInputProtected(true);
            abMsg.dlrRefNum_A.setInputProtected(true);
            abMsg.flPrcListCd_A.setInputProtected(true);
            abMsg.funcCsmpCrAmt_A.setInputProtected(true);
            abMsg.funcCsmpFlPrcAmt_A.setInputProtected(true);
            abMsg.funcFinalFlPrcAmt_A.setInputProtected(true);
            abMsg.funcFinalRepRevAmt_A.setInputProtected(true);
            abMsg.funcFlAdjAmt_A.setInputProtected(true);
            abMsg.funcNetSellPrcAmt_A.setInputProtected(true);
            abMsg.funcRepRevAdjAmt_A.setInputProtected(true);
            abMsg.funcUnitFlPrcAmt_A.setInputProtected(true);
            abMsg.funcUnitMsrpAmt_A.setInputProtected(true);
            abMsg.funcUnitStdCostAmt_A.setInputProtected(true);
            abMsg.lineWtAmtRate_A.setInputProtected(true);
            abMsg.mdseCd_A.setInputProtected(true);
            abMsg.mdseDescShortTxt_A.setInputProtected(true);
            abMsg.ordPrftTrxCatgCd_A.setInputProtected(true);
            abMsg.xxLineNum_A.setInputProtected(true);
            abMsg.prcCatgNm_A.setInputProtected(true);// 2016/03/10 S21_NA#2939
            abMsg.flPrcListNm_A.setInputProtected(true);// 2016/03/10 S21_NA#2939
            abMsg.dsOrdPosnNum_A.setInputProtected(true); // QC#7707
            // 2016/10/18 S21_NA#15193 Mod Start
//            abMsg.coaMdseTpNm_A.setInputProtected(true); // QC#7707
            abMsg.coaProjNm_A.setInputProtected(true);
            // 2016/10/18 S21_NA#15193 Mod End
            abMsg.ordQty_A.setInputProtected(true); // QC#7707
            abMsg.funcNetUnitPrcAmt_A.setInputProtected(true); // QC#7707
            abMsg.funcFinalUnitFlPrcAmt_A.setInputProtected(true); // QC#7707
            abMsg.funcFinalUnitRevAmt_A.setInputProtected(true); // QC#7707
            abMsg.funcUnitListPrcAmt_A.setInputProtected(true); // QC#7707
            abMsg.prcCatgNm_A2.setInputProtected(true); // QC#7707
            abMsg.funcWtSvcRevTrnsfAmt_A.setInputProtected(true); // QC#7707
            abMsg.funcWtSvcCostTrnsfAmt_A.setInputProtected(true); // QC#7707
            abMsg.funcWtManFlAdjAmt_A.setInputProtected(true); // QC#7707
            abMsg.funcWtManRevAdjAmt_A.setInputProtected(true); // QC#7707
        }

    }

    public static void setGpControl(S21CommonHandler handler, NWAL1540BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute("NWAL1540Scrn00");

        int verPullCnt = 0;
        for (int i = 0; i < scrnMsg.xxScrItem13Txt_NM.length(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem13Txt_NM.no(i).getValue())) {
                verPullCnt++;
            } else {
                break;
            }
        }

        if (!"Online".equals(scrnMsg.xxScrItem13Txt_NM.no(scrnMsg.ordPrftVrsnNum.getValueInt()).getValue())
                || verPullCnt == 1) {
            // 2016/09/28 S21_NA#12187 Mod Start    
            scrnMsg.altGrsPrftPct_RE.clear();
            scrnMsg.funcAltGrsPrftAmt_RE.clear();
            // 2016/09/28 S21_NA#12187 Mod End
            return;
        }

        EZDGUIAttribute gpPct = new EZDGUIAttribute("NWAL1540Scrn00", NWAL1540Bean.altGrsPrftPct_RE);
        EZDGUIAttribute gpAmt = new EZDGUIAttribute("NWAL1540Scrn00", NWAL1540Bean.funcAltGrsPrftAmt_RE);

        boolean isDifferent = false;
        if (ZYPCommonFunc.hasValue(scrnMsg.altGrsPrftPct_RE) // 2016/09/28 S21_NA#12187 Mod
            && scrnMsg.altGrsPrftPct_MV.getValue().compareTo(scrnMsg.altGrsPrftPct_RE.getValue()) != 0) {
            gpPct.setStyleAttribute("color", "red");
            scrnMsg.addGUIAttribute(gpPct);
            isDifferent = true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.funcAltGrsPrftAmt_RE) // 2016/09/28 S21_NA#12187 Mod
            && scrnMsg.funcAltGrsPrftAmt_MV.getValue().compareTo(scrnMsg.funcAltGrsPrftAmt_RE.getValue()) != 0) {
            gpAmt.setStyleAttribute("color", "red");
            scrnMsg.addGUIAttribute(gpAmt);
            isDifferent = true;
        }

        if (isDifferent) {
            if (ORD_HDR_STS.CLOSED.equals(scrnMsg.ordHdrStsCd.getValue())) {
                return;
            }
            setBtnProp(handler, BTN_RECALC, BTN_STS_ACTIVE);
        }
    }

    public static void setSearchControl(S21CommonHandler handler, NWAL1540BMsg scrnMsg) {
        scrnMsg.xxModeCd.setInputProtected(true);
        scrnMsg.trxHdrNum.setInputProtected(true);

        setBtnProp(handler, BTN_SEARCH, BTN_STS_INACTIVE);
    }

    public static void setAppFracDigit(NWAL1540BMsg scrnMsg) {

        scrnMsg.funcNegoDealAmt.setAppFracDigit(2);
        scrnMsg.totInvAmt.setAppFracDigit(2); // QC#9123
        scrnMsg.totFuncRepRevAmt.setAppFracDigit(2);
        scrnMsg.totFuncFinalFlAmt.setAppFracDigit(2);
        scrnMsg.grsPrftPct.setAppFracDigit(2);
        scrnMsg.funcGrsPrftAmt.setAppFracDigit(2);// 2016/03/10 S21_NA#2939
        scrnMsg.xxOrdPrftFndrFeeAmt.setAppFracDigit(2);
        scrnMsg.xxOrdPrftIstlCrAmt.setAppFracDigit(2);
        scrnMsg.totFuncDlrCrAmt.setAppFracDigit(2);
        scrnMsg.funcAltGrsPrftAmt.setAppFracDigit(2);
        scrnMsg.altGrsPrftPct.setAppFracDigit(2);
        scrnMsg.funcAltGrsPrftAmt_RE.setAppFracDigit(2);
        scrnMsg.altGrsPrftPct_RE.setAppFracDigit(2);
        scrnMsg.totFuncMsrpAmt.setAppFracDigit(2);
        scrnMsg.totCostAmt.setAppFracDigit(2); // QC#9123
        scrnMsg.totFuncStdFlAmt.setAppFracDigit(2);
        scrnMsg.totFuncFlAdjAmt.setAppFracDigit(2);
        scrnMsg.totFuncCsmpCrAmt.setAppFracDigit(2);

        scrnMsg.totFuncByotAmt.setAppFracDigit(2);
        scrnMsg.totFuncSvcRevTrnsfAmt.setAppFracDigit(2);
        scrnMsg.totFuncSvcCostTrnsfAmt.setAppFracDigit(2);
        scrnMsg.totFuncProSvcAmt.setAppFracDigit(2);
        scrnMsg.xxOrdPrftSvcAmt.setAppFracDigit(2);
        scrnMsg.xxOrdPrftSplyAmt.setAppFracDigit(2);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).funcCsmpFlPrcAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcNetSellPrcAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcFinalFlPrcAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcFinalRepRevAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcCsmpCrAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcUnitFlPrcAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcUnitMsrpAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).lineWtAmtRate_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcFlAdjAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcRepRevAdjAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcUnitStdCostAmt_A.setAppFracDigit(2);
        }
    }

}
