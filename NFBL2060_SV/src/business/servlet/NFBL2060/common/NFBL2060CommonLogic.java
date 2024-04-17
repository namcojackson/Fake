/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFBL2060.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.db.CM_INV_ACCT_DISTTMsg;
import business.servlet.NFBL2060.NFBL2060BMsg;
import business.servlet.NFBL2060.NFBL2060_DBMsg;
import business.servlet.NFBL2060.NFBL2060_DBMsgArray;
import business.servlet.NFBL2060.NFBL2060_SBMsg;
import business.servlet.NFBL2060.NFBL2060_SBMsgArray;
import business.servlet.NFBL2060.constant.NFBL2060Constant;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/21   Hitachi         T.Tsuchida      Update          QC#12116
 * 2016/07/22   Hitachi         Y.Tsuchimoto    Update          QC#12008
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Update          QC#11999
 * 2016/07/26   Hitachi         T.Tsuchida      Update          QC#12239
 * 2016/07/27   Hitachi         T.Tsuchida      Update          QC#12002
 * 2016/08/05   Fujitsu         C.Tanaka        Update          QC#12951
 * 2016/08/08   Fujitsu         C.Tanaka        Update          QC#12040
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#5521
 * 2016/09/14   Hitachi         Y.Tsuchimoto    Update          QC#13544
 * 2016/09/27   Hitachi         Y.Tsuchimoto    Update          QC#14797
 * 2016/09/30   Hitachi         T.Tsuchida      Update          QC#14498
 * 2016/10/03   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/06   Hitachi         Y.Tsuchimoto    Update          QC#15036
 * 2016/10/24   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2017/01/05   Fujitsu         H.Ikeda         Update          QC#12865
 * 2017/05/22   CITS            M.Naito         Update          Merge DS Lv2
 * 2018/03/08   Hitachi         Y.Takeno        Update          QC#22755
 * 2018/03/23   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/03/23   Hitachi         Y.Takeno        Update          QC#22350
 * 2018/05/28   CITS            K.Ogino         Update          QC#25902
 * 2018/09/03   Hitachi         Y.Takeno        Update          QC#28065
 * 2019/04/17   Hitachi         Y.Takeno        Update          QC#31071
 * 2020/01/01   Fujitsu         M.Ishii         Update          QC#55233
 * 2020/03/03   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 * 2024/02/05   Hitachi         S.Ikariya       Update          QC#63451
 * </pre>
 */
public class NFBL2060CommonLogic implements NFBL2060Constant {

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2060BMsg
     */
    @SuppressWarnings("unchecked")
    public static void initControl(EZDCommonHandler handler, NFBL2060BMsg scrnMsg) {

        List funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);

        boolean protect = true;
        // Start 2017/01/05 H.Ikeda [QC#12865,MOD]
        if (funcList.contains(FUNC_ID_NFBL2060T010) && funcList.contains(FUNC_ID_NFBL2060T020)) {
            protect = false;
        } else if (funcList.contains(FUNC_ID_NFBL2060T010)) {
            //protect = false;
            protect = true;
        } else if (funcList.contains(FUNC_ID_NFBL2060T020)) {
            //protect = true;
            protect = false;
        }

        if (protect) {
            handler.setButtonEnabled(BTN_NORMAL_NEW, false);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_NEW, true);
        }
        // Input Protection for Header Vendor Section
        scrnMsg.xxLinkProt_V1.setInputProtected(false);
        scrnMsg.dplyVndNm.setInputProtected(false);
        scrnMsg.xxLinkProt_V2.setInputProtected(false);
        scrnMsg.apVndCd.setInputProtected(false);
        scrnMsg.xxLinkProt_V3.setInputProtected(false);
        scrnMsg.prntVndCd.setInputProtected(false);
        // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.poNum.setInputProtected(false);
        // QC#25902
        scrnMsg.vndRtrnNum.setInputProtected(false);
        scrnMsg.vndRtrnSubmtDt_FR.setInputProtected(false);
        scrnMsg.vndRtrnSubmtDt_TO.setInputProtected(false);
        scrnMsg.xxLinkProt_V7.setInputProtected(false);
        // QC#25902 End
        // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.delyOrdNum.setInputProtected(false);
        scrnMsg.poDt_FR.setInputProtected(false);
        scrnMsg.poDt_TO.setInputProtected(false);
        scrnMsg.xxLinkProt_V4.setInputProtected(false);
        // START 2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
        scrnMsg.xxLinkProt_V5.setInputProtected(false);
        // END   2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
        // START 2016/07/27 T.Tsuchida [QC#12002,ADD]
        scrnMsg.xxLinkProt_V6.setInputProtected(false);
        // END 2016/07/27 T.Tsuchida [QC#12002,ADD]
        scrnMsg.vndPmtTermDescTxt.setInputProtected(false);
        // Input Protection for Header Invoice Section
        scrnMsg.apVndInvNum.setInputProtected(false);
        scrnMsg.acctInvStsCd_S.setInputProtected(false);
        scrnMsg.invDt_FR.setInputProtected(false);
        scrnMsg.invDt_TO.setInputProtected(false);
        // START 2024/02/05 S.Ikariya [QC#63451, ADD]
        scrnMsg.acInvTotCostAmt_FR.setInputProtected(false);
        scrnMsg.acInvTotCostAmt_TO.setInputProtected(false);
        // END 2024/02/05 S.Ikariya [QC#63451, ADD]
        scrnMsg.xxCmntTxt_FR.setInputProtected(false);
        scrnMsg.xxCmntTxt_TO.setInputProtected(false);
        scrnMsg.xxLinkProt_I1.setInputProtected(false);
        // START 2016/07/21 T.Tsuchida [QC#12116,MOD]
        //scrnMsg.dsPmtMethNm.setInputProtected(false);
        scrnMsg.vndPmtMethDescTxt.setInputProtected(false);
        // END 2016/07/21 T.Tsuchida [QC#12116,MOD]
        scrnMsg.apInvCatgCd_S.setInputProtected(false);
        scrnMsg.apPmtChkNum.setInputProtected(false);
        scrnMsg.pmtDt_FR.setInputProtected(false);
        scrnMsg.pmtDt_TO.setInputProtected(false);
        // Input Protection for Header Invoice With Section
        scrnMsg.xxChkBox_01.setInputProtected(false);
        scrnMsg.xxChkBox_02.setInputProtected(false);
        // START 2018/03/23 [QC#22350, ADD]
        scrnMsg.dispPoDtlLineNum.setInputProtected(false);
        // END   2018/03/23 [QC#22350, ADD]
        // START 2018/10/18 J.Kim [QC#28816,ADD]
        scrnMsg.invAuthDt_FR.setInputProtected(false);
        scrnMsg.invAuthDt_TO.setInputProtected(false);
        // END 2018/10/18 J.Kim [QC#28816,ADD]

        // Normal Button
        handler.setButtonEnabled(BTN_NORMAL_SEARCH, true);
        handler.setButtonEnabled(BTN_NORMAL_DETAILED_TAB_DONWLOAD_BUTTON, true);
        handler.setButtonEnabled(BTN_NORMAL_SUMMARY_TAB_DONWLOAD_BUTTON, true);
        // Common Button
        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK2[0], BTN_CMN_BLANK2[1], BTN_CMN_BLANK2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK4[0], BTN_CMN_BLANK4[1], BTN_CMN_BLANK4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        // End  2017/01/05 H.Ikeda [QC#12865,MOD]

        // Detailed Tab
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).apInvCatgDescTxt_D1.setInputProtected(true);
            scrnMsg.D.no(i).invDt_D1.setInputProtected(true);
            // START 2018/10/18 J.Kim [QC#28816,ADD]
            scrnMsg.D.no(i).invAuthDt_D1.setInputProtected(true);
            // END 2018/10/18 J.Kim [QC#18816,ADD]
            scrnMsg.D.no(i).prntVndCd_D1.setInputProtected(true);
            scrnMsg.D.no(i).dplyVndNm_D1.setInputProtected(true);
            scrnMsg.D.no(i).apVndCd_D1.setInputProtected(true);
            scrnMsg.D.no(i).vndPmtTermDescTxt_D1.setInputProtected(true);
            scrnMsg.D.no(i).acInvTotCostAmt_D1.setInputProtected(true);
            scrnMsg.D.no(i).invHdrDescTxt_D1.setInputProtected(true);
            scrnMsg.D.no(i).acctInvStsDescTxt_D1.setInputProtected(true);
            scrnMsg.D.no(i).poNum_D1.setInputProtected(true);
            // QC#25902
            scrnMsg.D.no(i).vndRtrnNum_D1.setInputProtected(true);
            scrnMsg.D.no(i).vndRtrnSubmtDt_D1.setInputProtected(true);
            // QC#25902 End
            scrnMsg.D.no(i).poDt_D1.setInputProtected(true);
            scrnMsg.D.no(i).delyOrdNum_D1.setInputProtected(true);
            // START 2018/03/23 [QC#20316,MOD]
            // START 2018/03/23 [QC#22350, ADD]
            scrnMsg.D.no(i).dispPoDtlLineNum_D1.setInputProtected(true);
            // END   2018/03/23 [QC#22350, ADD]
            // START 2016/09/27 Y.Tsuchimoto [QC#14797,MOD]
            scrnMsg.D.no(i).apVndInvLineNum_D1.setInputProtected(true);
            // END   2018/03/23 [QC#20316,MOD]
            scrnMsg.D.no(i).apLineTpDescTxt_D1.setInputProtected(true);
            // START 2016/09/27 Y.Tsuchimoto [QC#14797,MOD]
            //scrnMsg.D.no(i).invLineDescTxt_D1.setInputProtected(true);
            scrnMsg.D.no(i).mdseDescShortTxt_D1.setInputProtected(true);
            // END   2016/09/27 Y.Tsuchimoto [QC#14797,MOD]
            scrnMsg.D.no(i).acInvJrnlCostAmt_D1.setInputProtected(true);
            scrnMsg.D.no(i).acInvTaxAmt_D1.setInputProtected(true);
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).xxFldValTxt_D1.setInputProtected(true);
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).xxCmntTxt_D2.setInputProtected(true);
            scrnMsg.D.no(i).invQty_D1.setInputProtected(true);
            scrnMsg.D.no(i).invRcvQty_D1.setInputProtected(true);
            scrnMsg.D.no(i).apBillQty_D1.setInputProtected(true);
            scrnMsg.D.no(i).apPmtChkNum_D1.setInputProtected(true);
            scrnMsg.D.no(i).pmtDt_D1.setInputProtected(true);
            scrnMsg.D.no(i).contrNum_D1.setInputProtected(true);
            scrnMsg.D.no(i).custDlrCd_D1.setInputProtected(true);
            scrnMsg.D.no(i).serNum_D1.setInputProtected(true);
            scrnMsg.D.no(i).csmpInvNum_D1.setInputProtected(true);
            scrnMsg.D.no(i).istlLocCtyAddr_D1.setInputProtected(true);
        }

        // START 2016/09/14 Y.Tsuchimoto [QC#13544,MOD]
        // Summary Tab
        for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {
            scrnMsg.S.no(i).apInvCatgDescTxt_S1.setInputProtected(true);
            scrnMsg.S.no(i).invDt_S1.setInputProtected(true);
            // START 2018/10/18 J.Kim [QC#28816,ADD]
            scrnMsg.S.no(i).invAuthDt_S1.setInputProtected(true);
            // END 2018/10/18 J.Kim [QC#18816,ADD]
            scrnMsg.S.no(i).prntVndCd_S1.setInputProtected(true);
            scrnMsg.S.no(i).dplyVndNm_S1.setInputProtected(true);
            scrnMsg.S.no(i).apVndCd_S1.setInputProtected(true);
            scrnMsg.S.no(i).invAmt_S1.setInputProtected(true);
            scrnMsg.S.no(i).invHdrDescTxt_S1.setInputProtected(true);
            scrnMsg.S.no(i).acctInvStsDescTxt_S1.setInputProtected(true);
            // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
            scrnMsg.S.no(i).poNum_S1.setInputProtected(true);
            // QC#25902
            // mod start 2022/02/15 QC#57090
            //scrnMsg.S.no(i).vndRtrnNum_S1.setInputProtected(true);
            scrnMsg.S.no(i).xxDplyTrxNumTxt_S1.setInputProtected(true);
            // mod end 2022/02/15 QC#57090
            scrnMsg.S.no(i).vndRtrnSubmtDt_S1.setInputProtected(true);
            // QC#25902 End
            // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
            scrnMsg.S.no(i).poApvlDt_S1.setInputProtected(true);
            scrnMsg.S.no(i).entPoDtlDealNetAmt_S1.setInputProtected(true);
            scrnMsg.S.no(i).apPmtChkNum_S1.setInputProtected(true);
            scrnMsg.S.no(i).pmtDt_S1.setInputProtected(true);
            scrnMsg.S.no(i).vndPmtTermDescTxt_S1.setInputProtected(true);
        }
        // END   2016/09/14 Y.Tsuchimoto [QC#13544,MOD]
        initAppFracDigit(scrnMsg);
    }

    /**
     * Method name: setButtonConfirmMsg
     * <dd>The method explanation: Set Button Confirm Message.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static void setButtonConfirmMsg(EZDCommonHandler handler) {
        // Button Confirm Message Settings
        handler.setButtonConfirmMsg(BTN_CMN_CLEAR[1], ZZM8101I, new String[] {BTN_CMN_CLEAR[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RETURN[1], ZZM8101I, new String[] {BTN_CMN_RETURN[2] }, 0);
    }

    /**
     * Get Param NWAL1130 For Terms
     * @param scrnMsg NFBL2060BMsg
     * @param glblCmpyCd String
     * @return Param NWAL1130 For Payment Term
     */
    public static Object[] getParamNWAL1130ForTermsLink(NFBL2060BMsg scrnMsg, String glblCmpyCd) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = EMPTY_STRING;
        params[IDX_1] = "Payment Term Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  VPT.EZCANCELFLAG        AS EZCANCELFLAG ");
        sb.append(", VPT.GLBL_CMPY_CD        AS GLBL_CMPY_CD ");
        sb.append(", VPT.VND_PMT_TERM_CD     AS VND_PMT_TERM_CD ");
        sb.append(", VPT.VND_PMT_TERM_DESC_TXT     AS VND_PMT_TERM_DESC_TXT ");
        sb.append("FROM ");
        sb.append("  VND_PMT_TERM  VPT ");
        sb.append("WHERE ");
        sb.append("    VPT.EZCANCELFLAG   = '0' ");
        sb.append("AND VPT.GLBL_CMPY_CD   = '").append(glblCmpyCd).append("' ");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Payment Term Code";
        whereArray0[IDX_1] = "UPPER(VND_PMT_TERM_CD)";
        // START 2016/07/26 T.Tsuchida [QC#12239,MOD]
        //whereArray0[IDX_2] = scrnMsg.vndPmtTermCd.getValue();
        whereArray0[IDX_2] = null;
        // END 2016/07/26 T.Tsuchida [QC#12239,MOD]
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Payment Term Name";
        whereArray1[IDX_1] = "UPPER(VND_PMT_TERM_DESC_TXT)";
        // START 2016/07/26 T.Tsuchida [QC#12239,MOD]
        //whereArray1[IDX_2] = null;
        whereArray1[IDX_2] = scrnMsg.vndPmtTermDescTxt.getValue();
        // END 2016/07/26 T.Tsuchida [QC#12239,MOD]
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Payment Term Code";
        columnArray0[IDX_1] = "VND_PMT_TERM_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Payment Term Name";
        columnArray1[IDX_1] = "VND_PMT_TERM_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "VND_PMT_TERM_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "VND_PMT_TERM_DESC_TXT";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For Payment Method
     * @param scrnMsg NFBL2060BMsg
     * @param glblCmpyCd String
     * @return Param NWAL1130 For Payment Method
     */
    public static Object[] getParamNWAL1130ForPmtMethLink(NFBL2060BMsg scrnMsg, String glblCmpyCd) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = EMPTY_STRING;
        params[IDX_1] = "Payment Method Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  VPM.EZCANCELFLAG        AS EZCANCELFLAG ");
        sb.append(", VPM.GLBL_CMPY_CD        AS GLBL_CMPY_CD ");
        sb.append(", VPM.VND_PMT_METH_CD     AS VND_PMT_METH_CD ");
        sb.append(", VPM.VND_PMT_METH_DESC_TXT     AS VND_PMT_METH_DESC_TXT ");
        sb.append("FROM ");
        sb.append("  VND_PMT_METH  VPM ");
        sb.append("WHERE ");
        sb.append("    VPM.EZCANCELFLAG   = '0' ");
        sb.append("AND VPM.GLBL_CMPY_CD   = '").append(glblCmpyCd).append("' ");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Payment Method Code";
        whereArray0[IDX_1] = "UPPER(VND_PMT_METH_CD)";
        // START 2016/07/21 T.Tsuchida [QC#12116,MOD]
        //whereArray0[IDX_2] = scrnMsg.vndPmtMethCd.getValue();
        whereArray0[IDX_2] = null;
        // END 2016/07/21 T.Tsuchida [QC#12116,MOD]
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Payment Method Name";
        whereArray1[IDX_1] = "UPPER(VND_PMT_METH_DESC_TXT)";
        // START 2016/07/21 T.Tsuchida [QC#12116,MOD]
        //whereArray1[IDX_2] = null;
        whereArray1[IDX_2] = scrnMsg.vndPmtMethDescTxt.getValue();
        // END 2016/07/21 T.Tsuchida [QC#12116,MOD]
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Payment Method Number";
        columnArray0[IDX_1] = "VND_PMT_METH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Payment Method Name";
        columnArray1[IDX_1] = "VND_PMT_METH_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "VND_PMT_METH_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "VND_PMT_METH_DESC_TXT";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    // START 2016/07/22 Y.Tsuchimoto [QC#12008,ADD]
    /**
     * Get Param NWAL1130 For Supplier
     * @param scrnMsg NFBL2060BMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @return Param NWAL1130 For Supplier
     */
    public static Object[] getParamNWAL1130ForSupplier(NFBL2060BMsg scrnMsg, String glblCmpyCd, String salesDate) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Supplier/Supplier Site Search";

        StringBuilder sb = new StringBuilder();
        // Start 2019/01/01 M.Ishii [QC#55233,MOD]
//        sb.append(" SELECT");
        sb.append(" SELECT DISTINCT");
        // End 2019/01/01 M.Ishii [QC#55233,MOD]
        sb.append("    V.EZCANCELFLAG,");
        sb.append("    V.GLBL_CMPY_CD,");
        sb.append("    V.VND_CD,");
        sb.append("    V.LOC_NM,");
        sb.append("    V.INAC_DT,");
        sb.append("    PV.PRNT_VND_CD,");
        sb.append("    PV.PRNT_VND_NM,");
        sb.append("    PV.PRNT_VND_TP_CD");
        sb.append(" FROM");
        sb.append("    PRNT_VND PV,");
        sb.append("    VND      V,");
        sb.append("    VND_TP_RELN VTR");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND V.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("  AND V.EZCANCELFLAG = '0'");
        sb.append("  AND V.RGTN_STS_CD  = '");
        sb.append(RGTN_STS.READY_FOR_ORDER_TAKING);
        sb.append("'");
        sb.append("  AND V.EFF_THRU_DT  >= '").append(salesDate).append("'");
        sb.append("  AND V.VND_CD        = VTR.VND_CD");
        sb.append("  AND V.GLBL_CMPY_CD  = VTR.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG  = VTR.EZCANCELFLAG");
        // Start 2019/01/01 M.Ishii [QC#55233,MOD]
//        sb.append("  AND VTR.VND_TP_CD   = '");
//        sb.append(VND_TP.SUPPLIER);
//        sb.append("'");
        sb.append("  AND VTR.VND_TP_CD   IN ('");
        sb.append(VND_TP.SUPPLIER);
        sb.append("','");
        sb.append(VND_TP.PAYMENT_SUPPLIER);
        sb.append("')");
        // End 2019/01/01 M.Ishii [QC#55233,MOD]
        sb.append("  AND V.GLBL_CMPY_CD = PV.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG = PV.EZCANCELFLAG");
        sb.append("  AND V.PRNT_VND_PK  = PV.PRNT_VND_PK");
        sb.append("  AND EXISTS (");
        sb.append("         SELECT");
        sb.append("             1");
        sb.append("         FROM");
        sb.append("             CM_AP_INV_HDR HDR");
        sb.append("         WHERE");
        sb.append("                 HDR.GLBL_CMPY_CD  = V.GLBL_CMPY_CD");
        sb.append("             AND HDR.AP_VND_CD     = V.VND_CD");
        sb.append("             AND HDR.EZCANCELFLAG  = '0'");
        sb.append("      )");
        sb.append("  AND (PV.INAC_DT IS NULL OR PV.INAC_DT > '").append(salesDate).append("')");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        String[] vndNmArray = new String[2];
        if (ZYPCommonFunc.hasValue(scrnMsg.dplyVndNm)) {
            vndNmArray = scrnMsg.dplyVndNm.getValue().split(DIV_DPLY_VND_NM);
        }

        // START 2017/12/22 [QC#22831, MOD]
        whereArray1[IDX_0] = "Supplier Number";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray1[IDX_1] = "PRNT_VND_CD";
        whereArray1[IDX_2] = scrnMsg.prntVndCd.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        whereArray2[IDX_0] = "Supplier Name";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray2[IDX_1] = "PRNT_VND_NM";
        whereArray2[IDX_2] = "";
        if (vndNmArray.length >= 1) {
            whereArray2[IDX_2] = vndNmArray[0];
        }
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray3 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        whereArray3[IDX_0] = "Supplier Site Code";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray3[IDX_1] = "VND_CD";
        whereArray3[IDX_2] = scrnMsg.apVndCd.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray4 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        whereArray4[IDX_0] = "Supplier Site Name";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray4[IDX_1] = "LOC_NM";
        whereArray4[IDX_2] = "";
        if (vndNmArray.length >= 2) {
            whereArray4[IDX_2] = vndNmArray[1];
        }
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        whereList.add(whereArray3);
        whereList.add(whereArray4);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray1[IDX_0] = "Supplier Number";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray1[IDX_1] = "PRNT_VND_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray2 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray2[IDX_0] = "Supplier Name";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray2[IDX_1] = "PRNT_VND_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray3 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray3[IDX_0] = "Supplier Site Code";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray3[IDX_1] = "VND_CD";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray4 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray4[IDX_0] = "Supplier Site Name";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray4[IDX_1] = "LOC_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PRNT_VND_CD";
        sortConditionArray1[IDX_1] = "ASC";
        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "VND_CD";
        sortConditionArray2[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        sortList.add(sortConditionArray2);
        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;
        return params;
    }
    // END   2016/07/22 Y.Tsuchimoto [QC#12008,ADD]

    // START 2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
    /**
     * Get Param NWAL1130 For Purchase Orde
     * @param scrnMsg NFBL2060BMsg
     * @param glblCmpyCd String
     * @return Param NWAL1130 For Purchase Orde
     */
    public static Object[] getParamNWAL1130ForPurchaseOrder(NFBL2060BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "PO Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("      P.PO_ORD_NUM             PO_ORD_NUM");
        sb.append("     ,DPT.DS_PO_TP_DESC_TXT    DS_PO_TP_DESC_TXT");
        sb.append("     ,PHS.PO_HDR_STS_DESC_TXT  PO_HDR_STS_DESC_TXT");
        sb.append("     ,PAS.PO_APVL_STS_DESC_TXT PO_APVL_STS_DESC_TXT");
        sb.append("     ,P.GLBL_CMPY_CD           GLBL_CMPY_CD");
        sb.append("     ,P.EZCANCELFLAG           EZCANCELFLAG");
        sb.append(" FROM");
        sb.append("      PO         P");
        sb.append("     ,DS_PO_TP   DPT");
        sb.append("     ,PO_HDR_STS PHS");
        sb.append("     ,PO_APVL_STS PAS");
        sb.append(" WHERE");
        sb.append("         P.GLBL_CMPY_CD      = '").append(glblCmpyCd).append("' ");
        sb.append("     AND P.EZCANCELFLAG      = '0'");
        sb.append("     AND P.GLBL_CMPY_CD      = DPT.GLBL_CMPY_CD");
        sb.append("     AND P.DS_PO_TP_CD       = DPT.DS_PO_TP_CD");
        sb.append("     AND DPT.EZCANCELFLAG    = '0'");
        sb.append("     AND P.GLBL_CMPY_CD      = PHS.GLBL_CMPY_CD");
        sb.append("     AND P.PO_HDR_STS_CD     = PHS.PO_HDR_STS_CD");
        sb.append("     AND PHS.EZCANCELFLAG    = '0'");
        sb.append("     AND P.GLBL_CMPY_CD      = PAS.GLBL_CMPY_CD");
        sb.append("     AND P.PO_APVL_STS_CD    = PAS.PO_APVL_STS_CD");
        sb.append("     AND PAS.EZCANCELFLAG    = '0'");
        sb.append("     AND EXISTS (");
        sb.append("             SELECT");
        sb.append("                 1");
        sb.append("             FROM");
        sb.append("                 CM_AP_INV_HDR HDR");
        sb.append("             WHERE");
        sb.append("                     HDR.GLBL_CMPY_CD = P.GLBL_CMPY_CD");
        sb.append("                 AND HDR.PO_NUM       = P.PO_ORD_NUM");
        sb.append("                 AND HDR.EZCANCELFLAG = '0'");
        sb.append(" )");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "PO Number";
        whereArray1[IDX_1] = "PO_ORD_NUM";
        // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        whereArray1[IDX_2] = scrnMsg.poNum.getValue();
        // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "PO Number";
        columnArray1[IDX_1] = "PO_ORD_NUM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "PO Type";
        columnArray2[IDX_1] = "DS_PO_TP_DESC_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Header Status";
        columnArray3[IDX_1] = "PO_HDR_STS_DESC_TXT";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Approval Status";
        columnArray4[IDX_1] = "PO_APVL_STS_DESC_TXT";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        params[IDX_4] = columnList;

         List<Object[]> sortList = new ArrayList<Object[]>();
         Object[] sortConditionArray1 = new Object[IDX_2];
         sortConditionArray1[IDX_0] = "PO_ORD_NUM";
         sortConditionArray1[IDX_1] = "ASC";
         sortList.add(sortConditionArray1);
         params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;
        return params;
    }
    // START 2016/07/25 Y.Tsuchimoto [QC#11999,ADD]

    /**
     * Add QC#25902
     * Get Param NWAL1130 For Receipt
     * @param scrnMsg NFBL2060BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] getParamNWAL1130ForVndRtrnReceipt(NFBL2060BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Receipt Search";
        params[IDX_2] = getQueryNWAL1130ForVndRtrnReceipt(scrnMsg, glblCmpyCd);
        params[IDX_3] = getWhereListNWAL1130ForVndRtrnReceipt(scrnMsg, glblCmpyCd);
        params[IDX_4] = getColumnListNWAL1130ForVndRtrnReceipt(scrnMsg, glblCmpyCd);
        params[IDX_5] = getSortListNWAL1130ForVndRtrnReceipt(scrnMsg, glblCmpyCd);

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    private static String getQueryNWAL1130ForVndRtrnReceipt(NFBL2060BMsg scrnMsg, String glblCmpyCd) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("      CSI.GLBL_CMPY_CD         GLBL_CMPY_CD");
        sb.append("     ,CSI.EZCANCELFLAG         EZCANCELFLAG");
        sb.append("     ,CSI.PO_ORD_NUM           PO_ORD_NUM");
        sb.append("     ,CSI.DELY_ORD_NUM         DELY_ORD_NUM");
        sb.append("     ,'Vendor Return'          ORD_TYPE_NM");
        sb.append(" FROM");
        sb.append("      CM_STK_IN CSI");
        sb.append("     ,INVTY_TRX IT");
        sb.append(" WHERE");
        sb.append("         CSI.GLBL_CMPY_CD    = '").append(glblCmpyCd).append("' ");
        sb.append("     AND CSI.EZCANCELFLAG    = '0'");
        sb.append("     AND CSI.TRX_CD          = '").append(TRX.PURCHASE_STOCK_IN ).append("' ");
        sb.append("     AND CSI.TRX_RSN_CD      = '").append(TRX_RSN.DOMESTIC_VENDOR_RETURN).append("' ");
        sb.append("     AND CSI.GLBL_CMPY_CD    = IT.GLBL_CMPY_CD  (+)");
        sb.append("     AND CSI.DELY_ORD_NUM    = IT.RWS_REF_NUM   (+)");
        sb.append("     AND IT.EZCANCELFLAG (+) = '0'");
        sb.append(" GROUP BY CSI.GLBL_CMPY_CD, CSI.EZCANCELFLAG, CSI.PO_ORD_NUM, CSI.DELY_ORD_NUM");
        sb.append(" UNION ALL SELECT");
        sb.append("      CSI.GLBL_CMPY_CD         GLBL_CMPY_CD");
        sb.append("     ,CSI.EZCANCELFLAG         EZCANCELFLAG");
        sb.append("     ,CSI.PO_ORD_NUM           PO_ORD_NUM");
        sb.append("     ,CSI.DELY_ORD_NUM         DELY_ORD_NUM");
        sb.append("     ,'PO'                     ORD_TYPE_NM");
        sb.append(" FROM");
        sb.append("      CM_STK_IN CSI");
        sb.append("     ,INVTY_TRX IT");
        sb.append("     ,RWS_HDR   RWS");
        sb.append(" WHERE");
        sb.append("         CSI.GLBL_CMPY_CD    = '").append(glblCmpyCd).append("' ");
        sb.append("     AND CSI.EZCANCELFLAG    = '0'");
        sb.append("     AND CSI.TRX_CD          = '").append(TRX.PURCHASE_STOCK_IN).append("' ");
        sb.append("     AND CSI.TRX_RSN_CD      = '").append(TRX_RSN.PURCHASE_STOCK_IN).append("' ");
        sb.append("     AND CSI.GLBL_CMPY_CD    = IT.GLBL_CMPY_CD  (+)");
        sb.append("     AND CSI.DELY_ORD_NUM    = IT.RWS_REF_NUM   (+)");
        sb.append("     AND IT.EZCANCELFLAG (+) = '0'");
        sb.append("     AND IT.GLBL_CMPY_CD     = RWS.GLBL_CMPY_CD (+)");
        sb.append("     AND IT.RWS_NUM          = RWS.RWS_NUM      (+)");
        sb.append("     AND RWS.EZCANCELFLAG(+) = '0'");
        sb.append(" GROUP BY CSI.GLBL_CMPY_CD, CSI.EZCANCELFLAG, CSI.PO_ORD_NUM, CSI.DELY_ORD_NUM, RWS.RWS_NUM");

        return sb.toString();
    }

    private static List<Object[]> getWhereListNWAL1130ForVndRtrnReceipt(NFBL2060BMsg scrnMsg, String glblCmpyCd) {
        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Order Number";
        whereArray1[IDX_1] = "PO_ORD_NUM";
        if (ZYPCommonFunc.hasValue(scrnMsg.poNum)) {
            whereArray1[IDX_2] = scrnMsg.poNum.getValue();
        } else {
            whereArray1[IDX_2] = scrnMsg.vndRtrnNum.getValue();
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Receipt Number";
        whereArray2[IDX_1] = "DELY_ORD_NUM";
        whereArray2[IDX_2] = scrnMsg.delyOrdNum.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        return whereList;
    }

    private static List<Object[]> getColumnListNWAL1130ForVndRtrnReceipt(NFBL2060BMsg scrnMsg, String glblCmpyCd) {
        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Order Number";
        columnArray1[IDX_1] = "PO_ORD_NUM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Receipt Number";
        columnArray2[IDX_1] = "DELY_ORD_NUM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Order Type";
        columnArray3[IDX_1] = "ORD_TYPE_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        return columnList;
    }

    private static List<Object[]> getSortListNWAL1130ForVndRtrnReceipt(NFBL2060BMsg scrnMsg, String glblCmpyCd) {
        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PO_ORD_NUM";
        sortConditionArray1[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "DELY_ORD_NUM";
        sortConditionArray2[IDX_1] = "ASC";
        sortList.add(sortConditionArray2);

        return sortList;
    }

    /**
     * Method name: initAppFracDigit
     * <dd>The method explanation: Init App Frac Digit.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void initAppFracDigit(EZDBMsg bMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        // Detailed tab
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).acInvTotCostAmt_D1.setAppFracDigit(FRAC_DIGIT_2);
            scrnMsg.D.no(i).acInvJrnlCostAmt_D1.setAppFracDigit(FRAC_DIGIT_2);
            scrnMsg.D.no(i).acInvTaxAmt_D1.setAppFracDigit(FRAC_DIGIT_2);
        }
    }

    // QC#12951 ADD Start
    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFBL2060BMsg
     * @param scrnDMsgAry NFBL2060_DBMsgArray
     * @param scrnSMsgAry NFBL2060_SBMsgArray
     */
    public static void setRowsBGWithClear(NFBL2060BMsg scrnMsg, NFBL2060_DBMsgArray scrnDMsgAry, NFBL2060_SBMsgArray scrnSMsgAry) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // QC#5521 MOD Start
        tblColor.clearRowsBG(TBL_ID_D, scrnDMsgAry);
        tblColor.clearRowsBG(TBL_ID_S, scrnSMsgAry);

        if (scrnDMsgAry != null && scrnDMsgAry.getValidCount() > 0) {
            tblColor.setAlternateRowsBG(TBL_ID_D, scrnDMsgAry, 1);
        } else if (scrnSMsgAry != null && scrnSMsgAry.getValidCount() > 0) {
            tblColor.setAlternateRowsBG(TBL_ID_S, scrnSMsgAry, 1);
        }
        // QC#5521 MOD End
    }
    // QC#12951 ADD End

    // QC#12040 ADD Start
    /**
     * Get Param NMAL2550 For Charge Account
     * @param scrnMsg NFBL2060BMsg
     * @param xxCmntTxt EZDBStringItem
     * @return Param NMAL2550 For Payment Term
     */
    public static Object[] getParamForChargeAccount(NFBL2060BMsg scrnMsg, EZDBStringItem xxCmntTxt) {

        scrnMsg.P.clear();
        if (!splitCOA9Seg(scrnMsg, xxCmntTxt)) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, BIZ_ID);
        Object[] params = new Object[PARAM_INDEX_10];
        int index = 0;
        params[index++] = scrnMsg.P.no(0).xxPopPrm;
        params[index++] = scrnMsg.P.no(1).xxPopPrm;
        params[index++] = scrnMsg.P.no(2).xxPopPrm;
        params[index++] = scrnMsg.P.no(3).xxPopPrm;
        params[index++] = scrnMsg.P.no(4).xxPopPrm;
        params[index++] = scrnMsg.P.no(5).xxPopPrm;
        params[index++] = scrnMsg.P.no(6).xxPopPrm;
        params[index++] = scrnMsg.P.no(7).xxPopPrm;
        params[index++] = scrnMsg.P.no(8).xxPopPrm;
        params[index++] = scrnMsg.P.no(9).xxPopPrm;
        return params;
    }

    private static boolean splitCOA9Seg(NFBL2060BMsg scrnMsg, EZDBStringItem xxCmntTxt) {

        if (!ZYPCommonFunc.hasValue(xxCmntTxt)) {
            return true;
        }
        String[] coa = xxCmntTxt.getValue().split("\\.", PARAM_INDEX_9);
        if (coa.length != PARAM_INDEX_9) {
            String errMsg = "9 segments format";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[] {errMsg });
            return false;
        }
        if (!check9Seg(scrnMsg, xxCmntTxt, coa)) {
            return false;
        }

        int index = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, coa[index++]);

        return true;
    }

    private static boolean check9Seg(NFBL2060BMsg scrnMsg, EZDBStringItem xxCmntTxt, String[] coa) {
        CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = new CM_INV_ACCT_DISTTMsg();
        // START 2018/09/03 [QC#28065, MOD]
        // int coaCmpyCdDigit = cmInvAcctDistTMsg.getAttr("coaCmpyCd").getDigit();
        // int coaBrCdDigit = cmInvAcctDistTMsg.getAttr("coaBrCd").getDigit();
        // int coaCcCdDigit = cmInvAcctDistTMsg.getAttr("coaCcCd").getDigit();
        // int coaAcctCdDigit = cmInvAcctDistTMsg.getAttr("coaAcctCd").getDigit();
        // int coaProdCdDigit = cmInvAcctDistTMsg.getAttr("coaProdCd").getDigit();
        // int coaChCdDigit = cmInvAcctDistTMsg.getAttr("coaChCd").getDigit();
        // int coaAfflCdDigit = cmInvAcctDistTMsg.getAttr("coaAfflCd").getDigit();
        // int coaProjCdDigit = cmInvAcctDistTMsg.getAttr("coaProjCd").getDigit();
        // int coaExtnCdDigit = cmInvAcctDistTMsg.getAttr("coaExtnCd").getDigit();
        int coaCmpyCdDigit = cmInvAcctDistTMsg.getAttr("drCoaCmpyCd").getDigit();
        int coaBrCdDigit = cmInvAcctDistTMsg.getAttr("drCoaBrCd").getDigit();
        int coaCcCdDigit = cmInvAcctDistTMsg.getAttr("drCoaCcCd").getDigit();
        int coaAcctCdDigit = cmInvAcctDistTMsg.getAttr("drCoaAcctCd").getDigit();
        int coaProdCdDigit = cmInvAcctDistTMsg.getAttr("drCoaProdCd").getDigit();
        int coaChCdDigit = cmInvAcctDistTMsg.getAttr("drCoaChCd").getDigit();
        int coaAfflCdDigit = cmInvAcctDistTMsg.getAttr("drCoaAfflCd").getDigit();
        int coaProjCdDigit = cmInvAcctDistTMsg.getAttr("drCoaProjCd").getDigit();
        int coaExtnCdDigit = cmInvAcctDistTMsg.getAttr("drCoaExtnCd").getDigit();
        // END   2018/09/03 [QC#28065, MOD]
        int coaIdx = 0;

        if (coa.length != PARAM_INDEX_9) {
            String errMsg = "9 segments";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[] {errMsg });
            return false;
        }
        if (coa[coaIdx++].length() > coaCmpyCdDigit) {
            xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[] {"Company" });
            return false;
        }
        if (coa[coaIdx++].length() > coaBrCdDigit) {
            xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[] {"Branch" });
            return false;
        }
        if (coa[coaIdx++].length() > coaCcCdDigit) {
            xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[] {"Cost Center" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAcctCdDigit) {
            xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[] {"Account" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProdCdDigit) {
            xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[] {"Product" });
            return false;
        }
        if (coa[coaIdx++].length() > coaChCdDigit) {
            xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[] {"Channel" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAfflCdDigit) {
            xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[] {"Intercompany" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProjCdDigit) {
            xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[] {"Merchandise" });
            return false;
        }
        if (coa[coaIdx++].length() > coaExtnCdDigit) {
            xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[] {"Business Unit" });
            return false;
        }
        return true;
    }
    // QC#12040 ADD End

    // START 2018/03/08 [QC#22755, ADD]
    /**
     * isValidPO
     * @param scrnMsg NFBL2060BMsg
     * @param sBMsg NFBL2060_SBMsg
     * @return boolean
     */
    // START 2020/03/03 [QC#53413, MOD]
//    public static boolean isValidPoNumAnchor(NFBL2060BMsg scrnMsg, NFBL2060_SBMsg sBMsg) {
//        return ZYPCommonFunc.hasValue(sBMsg.poApvlDt_S1) && ZYPCommonFunc.hasValue(sBMsg.poNum_S1);
//    }
    public static boolean isValidPoNumAnchor(NFBL2060BMsg scrnMsg, NFBL2060_SBMsg sBMsg) {
        if (ZYPCommonFunc.hasValue(sBMsg.poApvlDt_S1) && ZYPCommonFunc.hasValue(sBMsg.poNum_S1)) {
            // mod start 2022/02/15 QC#57090
            //if (!sBMsg.poNum_S1.getValue().endsWith(MULTI_PO_NUM_ITEM)) {
            if (!sBMsg.poNum_S1.getValue().endsWith(MULTI_ITEM)) {
            // mod end 2022/02/15 QC#57090
                return true;
            }
        }
        return false;
    }
    // END   2020/03/03 [QC#53413, MOD]
    /**
     * isValidPO
     * @param scrnMsg NFBL2060BMsg
     * @param sDMsg NFBL2060_DBMsg
     * @return boolean
     */
    public static boolean isValidPoNumAnchor(NFBL2060BMsg scrnMsg, NFBL2060_DBMsg sDMsg) {
        return ZYPCommonFunc.hasValue(sDMsg.poDt_D1) && ZYPCommonFunc.hasValue(sDMsg.poNum_D1);
    }
    // END   2018/03/08 [QC#22755, ADD]

    /**
     * Add QC#25902
     * Get Param NWAL1130 For VndRtrn
     * @param scrnMsg NFBL2060BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] getParamNWAL1130ForVndRtrn(NFBL2060BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Vendor Return Search";
        params[IDX_2] = getQueryNWAL1130ForVndRtrn(scrnMsg, glblCmpyCd);
        params[IDX_3] = getWhereListNWAL1130ForVndRtrn(scrnMsg, glblCmpyCd);
        params[IDX_4] = getColumnListNWAL1130ForVndRtrn(scrnMsg, glblCmpyCd);
        params[IDX_5] = getSortListNWAL1130ForVndRtrn(scrnMsg, glblCmpyCd);

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    private static String getQueryNWAL1130ForVndRtrn(NFBL2060BMsg scrnMsg, String glblCmpyCd) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("      VR.VND_RTRN_NUM           VND_RTRN_NUM");
        sb.append("     ,PV.PRNT_VND_CD            PRNT_VND_CD");
        sb.append("     ,PV.PRNT_VND_NM            PRNT_VND_NM");
        sb.append("     ,VRT.VND_RTRN_TP_DESC_TXT  VND_RTRN_TP_DESC_TXT");
        sb.append("     ,(CASE WHEN EXISTS (");
        sb.append("         SELECT 1");
        sb.append("         FROM");
        sb.append("             CM_AP_INV_HDR    CAIH");
        sb.append("         ,   CM_INV_ACCT_DIST CIAD");
        sb.append("         WHERE");
        sb.append("             CAIH.GLBL_CMPY_CD       = '").append(glblCmpyCd).append("' ");
        sb.append("         AND CAIH.EZCANCELFLAG       = '0'");
        sb.append("         AND CAIH.GLBL_CMPY_CD       = CIAD.GLBL_CMPY_CD");
        sb.append("         AND CAIH.AP_VND_CD          = CIAD.AP_VND_CD");
        sb.append("         AND CAIH.AP_VND_INV_NUM     = CIAD.AP_VND_INV_NUM");
        sb.append("         AND CAIH.AP_VND_INV_SQ_NUM  = CIAD.AP_VND_INV_SQ_NUM");
        sb.append("         AND CAIH.ACCT_INV_STS_CD   != '").append(ACCT_INV_STS.CANCEL).append("' ");
        sb.append("         AND CIAD.EZCANCELFLAG       = '0'");
        sb.append("         AND CIAD.GLBL_CMPY_CD       = VR.GLBL_CMPY_CD");
        // START 2019/04/17 [QC#31071, MOD]
        // sb.append("         AND CIAD.PO_NUM             = VR.VND_RTRN_NUM");
        sb.append("         AND CIAD.VND_RTRN_NUM       = VR.VND_RTRN_NUM");
        // END   2019/04/17 [QC#31071, MOD]
        sb.append("      ) THEN '").append("Credited").append("'");
        sb.append("        ELSE '").append("Open").append("'");
        sb.append("      END)                      VND_RTRN_STS_DESC_TXT");
        sb.append("     ,VR.GLBL_CMPY_CD           GLBL_CMPY_CD");
        sb.append("     ,VR.EZCANCELFLAG           EZCANCELFLAG");
        sb.append(" FROM");
        sb.append("      VND_RTRN     VR");
        sb.append("     ,VND          V");
        sb.append("     ,PRNT_VND     PV");
        sb.append("     ,VND_RTRN_TP  VRT");
        // START 2019/04/17 [QC#31071, DEL]
        // sb.append("     ,PRCH_REQ     PR");
        // END   2019/04/17 [QC#31071, DEL]
        sb.append(" WHERE");
        sb.append("         VR.GLBL_CMPY_CD       = '").append(glblCmpyCd).append("' ");
        sb.append("     AND VR.EZCANCELFLAG       = '0'");
        sb.append("     AND VR.GLBL_CMPY_CD       = V.GLBL_CMPY_CD");
        sb.append("     AND VR.BILL_TO_VND_CD     = V.VND_CD");
        sb.append("     AND V.EZCANCELFLAG        = '0'");
        sb.append("     AND V.GLBL_CMPY_CD        = PV.GLBL_CMPY_CD");
        sb.append("     AND V.PRNT_VND_PK         = PV.PRNT_VND_PK");
        sb.append("     AND PV.EZCANCELFLAG       = '0'");
        sb.append("     AND VR.GLBL_CMPY_CD       = VRT.GLBL_CMPY_CD");
        sb.append("     AND VR.VND_RTRN_TP_CD     = VRT.VND_RTRN_TP_CD");
        sb.append("     AND VRT.EZCANCELFLAG      = '0'");
        // START 2019/04/17 [QC#31071, MOD]
        // sb.append("     AND VR.GLBL_CMPY_CD       = PR.GLBL_CMPY_CD");
        // sb.append("     AND VR.VND_RTRN_NUM       = PR.PRCH_REQ_NUM");
        // sb.append("     AND PR.PRCH_REQ_STS_CD    = '").append(PRCH_REQ_STS.CLOSED).append("' ");
        // sb.append("     AND PR.EZCANCELFLAG       = '0'");
        sb.append("     AND EXISTS ( SELECT 1");
        sb.append("                     FROM CM_STK_IN CSI");
        sb.append("                     WHERE ");
        sb.append("                         CSI.GLBL_CMPY_CD = VR.GLBL_CMPY_CD");
        sb.append("                     AND CSI.TRX_CD       = '").append(TRX.PURCHASE_STOCK_IN ).append("' ");
        sb.append("                     AND CSI.TRX_RSN_CD   = '").append(TRX_RSN.DOMESTIC_VENDOR_RETURN).append("' ");
        sb.append("                     AND CSI.VND_RTRN_NUM = VR.VND_RTRN_NUM");
        sb.append("                     AND CSI.EZCANCELFLAG = VR.EZCANCELFLAG");
        sb.append("     )");
        // END   2019/04/17 [QC#31071, MOD]
      
        return sb.toString();
    }

    private static List<Object[]> getWhereListNWAL1130ForVndRtrn(NFBL2060BMsg scrnMsg, String glblCmpyCd) {
        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Vendor Return Number";
        whereArray1[IDX_1] = "VND_RTRN_NUM";
        whereArray1[IDX_2] = scrnMsg.vndRtrnNum.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }

    private static List<Object[]> getColumnListNWAL1130ForVndRtrn(NFBL2060BMsg scrnMsg, String glblCmpyCd) {
        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Vendor Return Number";
        columnArray1[IDX_1] = "VND_RTRN_NUM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Supplier Number";
        columnArray2[IDX_1] = "PRNT_VND_CD";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Supplier Name";
        columnArray3[IDX_1] = "PRNT_VND_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Vendor Return Type";
        columnArray4[IDX_1] = "VND_RTRN_TP_DESC_TXT";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "Status";
        columnArray5[IDX_1] = "VND_RTRN_STS_DESC_TXT";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;

        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        columnList.add(columnArray5);

        return columnList;
    }

    private static List<Object[]> getSortListNWAL1130ForVndRtrn(NFBL2060BMsg scrnMsg, String glblCmpyCd) {
        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "VND_RTRN_NUM";
        sortConditionArray1[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);

        return sortList;
    }

    // add start 2022/02/15 QC#57090
    /**
     * isValidVndRtrnNumAnchor
     * @param sBMsg NFBL2060_SBMsg
     * @return boolean
     */
    public static boolean isValidVndRtrnNumAnchor(NFBL2060_SBMsg sBMsg) {
        if (ZYPCommonFunc.hasValue(sBMsg.xxDplyTrxNumTxt_S1) && !sBMsg.xxDplyTrxNumTxt_S1.getValue().endsWith(MULTI_ITEM)) {
            return true;
        }
        return false;
    }
    // add end 2022/02/15 QC#57090
}
