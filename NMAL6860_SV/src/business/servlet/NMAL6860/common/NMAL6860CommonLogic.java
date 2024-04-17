/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860.common;

import static business.servlet.NMAL6860.constant.NMAL6860Constant.ACC_TYPE_LIABILITY;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.ACC_TYPE_PREPAY;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.ACC_TYPE_VNDRETURN;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.ARREFUND_PRNT_VND_TP_CD;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.BIZ_ID;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.ENTRY_SUPPLIER;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.FUCTION_CODE_UPDATE;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.HYPHEN;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.IDX_0;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.IDX_1;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.IDX_2;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.IDX_3;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.IDX_4;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.IDX_5;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.IDX_6;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.IDX_7;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.NEW_SUPPLIER;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.NMAL6860_PRNT_VND_TP_CD_FIXED;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.NMAM0011E;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.NMAM0052E;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.NMAM0808E;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.NMAM8643E;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.NWAM0763E;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.PERIOD;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.SLASH;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.TAB_NM_GENERAL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import business.blap.NMAL6860.NMAL6860CMsg;
import business.servlet.NMAL6860.NMAL6860BMsg;
import business.servlet.NMAL6860.NMAL6860Bean;
import business.servlet.NMAL6860.NMAL6860_ABMsg;
import business.servlet.NMAL6860.NMAL6860_BBMsg;
import business.servlet.NMAL6860.constant.NMAL6860Constant;
import business.servlet.NMAL6860.constant.NMAL6860Constant.BTN_APP;
import business.servlet.NMAL6860.constant.NMAL6860Constant.BTN_CMN;
import business.servlet.NMAL6860.constant.NMAL6860Constant.BTN_STATUS;
import business.servlet.NMAL6860.constant.NMAL6860Constant.FUNC_CD;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRNT_VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/03   CITS            M.Ouchi         Create          N/A
 * 2016/08/04   Hitachi         Y.Takeno        Update          QC#12765
 * 08/09/2016   CITS            S.Endo          Update          QC#10839
 * 2016/08/22   CITS            T.Gotoda        Update          QC#12215
 * 2016/09/12   CITS            T.Gotoda        Update          QC#13128
 * 2016/09/15   CITS            T.Gotoda        Update          QC#13313
 * 2016/09/26   CITS            T.Gotoda        Update          QC#13163
 * 2016/10/03   CITS            R.Shimamoto     Update          QC#12768
 * 2016/11/09   Hitachi         J.Kim           Update          QC#15616
 * 2016/11/24   Hitachi         R.Nakamura      Update          QC#15438
 * 2016/12/19   CITS            R.Shimamoto     Update          QC#16593
 * 2017/02/20   Fujitsu         T.Murai         Update          QC#16081
 * 2018/08/07   CITS            K.Ogino         Update          QC#27280
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55971
 * 2020/10/28   CITS            R.Kurahashi     Update          QC#57732
 * 2020/12/12   CITS            R.Kurahashi     Update          QC#57732-1
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 *</pre>
 */
public class NMAL6860CommonLogic {

    /**
     * <p>
     * Copies scrnMsg(BMsg) to bizMsg(CMsg) for Search.
     * </p>
     * @param scrnMsg (BMsg)
     * @return bizMsg (CMsg)
     */
    public static NMAL6860CMsg copyScrnMsgToBizMsgForSearch(NMAL6860BMsg scrnMsg) {
        NMAL6860CMsg bizMsg = new NMAL6860CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD.SEARCH.getCode());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    /**
     * <p>
     * Copies scrnMsg(BMsg) to bizMsg(CMsg) for Update.
     * </p>
     * @param scrnMsg (BMsg)
     * @return bizMsg (CMsg)
     */
    public static NMAL6860CMsg copyScrnMsgToBizMsgForUpdate(NMAL6860BMsg scrnMsg) {
        NMAL6860CMsg bizMsg = new NMAL6860CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD.UPDATE.getCode());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    // START 2021/03/01 G.Delgado [QC#56057,MOD]
    /**
     * <p>
     * Initializes the button properties.
     * </p>
     * @param handler S21CommonHandler
     * @param scrnMsg BMsg
     */
    // public static void initializeButtonProperties(S21CommonHandler handler) {
    public static void initializeButtonProperties(S21CommonHandler handler, NMAL6860BMsg scrnMsg) {
    // END 2021/03/01 G.Delgado [QC#56057,MOD]
        if (isEntryGranted()) {
            // Application-specific.
            activateButton(BTN_APP.ADD_SUPPLIER_SITE, handler);
            activateButton(BTN_APP.LIABILITY_ACCOUNT, handler);
            // START 2021/03/01 G.Delgado [QC#56057,MOD]
            // activateButton(BTN_APP.ADD_CONTACT_INFO, handler);
            String arRefundPrntVndTpCdConst = ZYPCodeDataUtil.getVarCharConstValue(ARREFUND_PRNT_VND_TP_CD, scrnMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(arRefundPrntVndTpCdConst) && arRefundPrntVndTpCdConst.equals(scrnMsg.prntVndTpCd.getValue())) {
                deactivateButton(BTN_APP.ADD_CONTACT_INFO, handler);
            } else {
                activateButton(BTN_APP.ADD_CONTACT_INFO, handler);
            }
            // END 2021/03/01 G.Delgado [QC#56057,MOD]

            // Common.
            deactivateButton(BTN_CMN.SAVE, handler);
            activateButton(BTN_CMN.SUBMIT, handler);
            deactivateButton(BTN_CMN.APPLY, handler);
            deactivateButton(BTN_CMN.APPROVE, handler);
            deactivateButton(BTN_CMN.REJECT, handler);
            deactivateButton(BTN_CMN.DOWNLOAD, handler);
            deactivateButton(BTN_CMN.DELETE, handler);
            activateButton(BTN_CMN.CLEAR, handler);
            activateButton(BTN_CMN.RESET, handler);
            activateButton(BTN_CMN.RETURN, handler);
        } else {
            // Application-specific.
            deactivateButton(BTN_APP.ADD_SUPPLIER_SITE, handler);
            deactivateButton(BTN_APP.LIABILITY_ACCOUNT, handler);
            deactivateButton(BTN_APP.ADD_CONTACT_INFO, handler);

            // Common.
            deactivateButton(BTN_CMN.SAVE, handler);
            deactivateButton(BTN_CMN.SUBMIT, handler);
            deactivateButton(BTN_CMN.APPLY, handler);
            deactivateButton(BTN_CMN.APPROVE, handler);
            deactivateButton(BTN_CMN.REJECT, handler);
            deactivateButton(BTN_CMN.DOWNLOAD, handler);
            deactivateButton(BTN_CMN.DELETE, handler);
            deactivateButton(BTN_CMN.CLEAR, handler);
            deactivateButton(BTN_CMN.RESET, handler);
            activateButton(BTN_CMN.RETURN, handler);
        }
    }
    
    // START 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
    public static void disabledButtonProperties(S21CommonHandler handler, int lineNo) {
        deactivateButton(BTN_APP.LINK_OPENWIN_CTY_EVENT_NM, handler, lineNo);
        deactivateButton(BTN_APP.BTN_GET_ADDRESS, handler, lineNo);
    }
    // END 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
    
    /**
     * <p>
     * Sets the input protected for Common Header.
     * </p>
     * @param scrnMsg BMsg.
     */
    public static void setInputProtectedForCommonHeader(NMAL6860BMsg scrnMsg) {
        if (!isEntryGranted()) {
            // If entry is NOT granted,
            // protects the input fields on Common Header.
            scrnMsg.prntVndCd.setInputProtected(true);
            scrnMsg.prntVndNm.setInputProtected(true);
            // 2020/02/28 QC#55971 Mod Start
            // scrnMsg.prntVndTpCd.setInputProtected(true);
            scrnMsg.xxLinkAncr_ST.setInputProtected(true);
            scrnMsg.prntVndTpDescTxt.setInputProtected(true);
            // 2020/02/28 QC#55971 Mod End
        }
        if (TAB_NM_GENERAL.equals(scrnMsg.xxDplyTab.getValue())) {
            // If General Tab is selected,
            // unprotects the input fields on Common Header.
            scrnMsg.prntVndCd.setInputProtected(true);
            // QC#27280
            // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
            //if (ZYPCommonFunc.hasValue(scrnMsg.prntVndTpCd_DF) && scrnMsg.prntVndTpCd_DF.getValue().equals(scrnMsg.prntVndTpCd.getValue())) {
            //    scrnMsg.prntVndNm.setInputProtected(true);
            //} else {
            //    scrnMsg.prntVndNm.setInputProtected(false);   
            //}
            if (ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_DF) && scrnMsg.prntVndNm_DF.getValue().equals(ENTRY_SUPPLIER)) {
                scrnMsg.prntVndNm.setInputProtected(true);
            } else {
                scrnMsg.prntVndNm.setInputProtected(false);
            }
            // END 2020/10/28 R.Kurahashi [QC#57732,MOD]
            // QC#13128 Start
            // From AR Refund by check Entry screen
            if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_P)) {
                // 2020/02/28 QC#55971 Mod Start
                // scrnMsg.prntVndTpCd.setInputProtected(true);
                // START 2021/03/01 G.Delgado [QC#56057,DEL]
                // scrnMsg.xxLinkAncr_ST.setInputProtected(true);
                // scrnMsg.prntVndTpDescTxt.setInputProtected(true);
                // END 2021/03/01 G.Delgado [QC#56057,DEL]
                // 2020/02/28 QC#55971 Mod End
                // START 2017/02/20 [QC#16081, ADD]
                for (int i = 0; i < scrnMsg.A.length(); i++) {
                    // QC#27280
                    // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
                    //if (ZYPCommonFunc.hasValue(scrnMsg.prntVndTpCd_DF) && scrnMsg.prntVndTpCd_DF.getValue().equals(scrnMsg.prntVndTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(scrnMsg.prntVndTpCd_DF) && scrnMsg.prntVndTpCd_DF.getValue().equals(scrnMsg.prntVndTpCd.getValue())
                            && !(ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_DF) && scrnMsg.prntVndNm_DF.getValue().equals(NEW_SUPPLIER))) {
                    // END 2020/10/28 R.Kurahashi [QC#57732,MOD]
                        scrnMsg.A.no(i).xxComnScrFirstValTxt_AL.setInputProtected(true);
                        scrnMsg.A.no(i).locNm_A.setInputProtected(true);
                    }
                    // START 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
                    if (scrnMsg.prntVndTpCd.getValue().equals(PRNT_VND_TP.ARREFUND)
                            && !((ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_DF) && scrnMsg.prntVndNm_DF.getValue().equals(NEW_SUPPLIER))
                                || (!ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_DF) && !ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)))) {
                        scrnMsg.A.no(i).ctryCd_A.setInputProtected(true);
                        scrnMsg.A.no(i).xxComnScrFirstValTxt_A.setInputProtected(true);
                        scrnMsg.A.no(i).xxComnScrScdValTxt_A.setInputProtected(true);
                        scrnMsg.A.no(i).ctyAddr_A.setInputProtected(true);
                        scrnMsg.A.no(i).postCd_A.setInputProtected(true);
                        scrnMsg.A.no(i).stCd_A.setInputProtected(true);
                        scrnMsg.A.no(i).cntyNm_A.setInputProtected(true);
                    }
                    // END 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
                    scrnMsg.A.no(i).xxComnScrFirstValTxt_AL.setIndispensable(true);
                }
                // END   2017/02/20 [QC#16081, ADD]
            } else {
                // QC#27280
                for (int i = 0; i < scrnMsg.A.length(); i++) {
                    // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
                    //if (ZYPCommonFunc.hasValue(scrnMsg.prntVndTpCd_DF) && scrnMsg.prntVndTpCd_DF.getValue().equals(scrnMsg.prntVndTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(scrnMsg.prntVndTpCd_DF) && scrnMsg.prntVndTpCd_DF.getValue().equals(scrnMsg.prntVndTpCd.getValue())
                            && !(ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_DF) && scrnMsg.prntVndNm_DF.getValue().equals(NEW_SUPPLIER))) {
                    // END 2020/10/28 R.Kurahashi [QC#57732,MOD]
                        scrnMsg.A.no(i).xxComnScrFirstValTxt_AL.setInputProtected(true);
                        scrnMsg.A.no(i).locNm_A.setInputProtected(true);
                    }
                    // START 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
                    if (scrnMsg.prntVndTpCd.getValue().equals(PRNT_VND_TP.ARREFUND)
                            && !((ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_DF) && scrnMsg.prntVndNm_DF.getValue().equals(NEW_SUPPLIER))
                                || (!ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_DF) && !ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)))) {
                        scrnMsg.A.no(i).ctryCd_A.setInputProtected(true);
                        scrnMsg.A.no(i).xxComnScrFirstValTxt_A.setInputProtected(true);
                        scrnMsg.A.no(i).xxComnScrScdValTxt_A.setInputProtected(true);
                        scrnMsg.A.no(i).ctyAddr_A.setInputProtected(true);
                        scrnMsg.A.no(i).postCd_A.setInputProtected(true);
                        scrnMsg.A.no(i).stCd_A.setInputProtected(true);
                        scrnMsg.A.no(i).cntyNm_A.setInputProtected(true);
                    }
                    // END 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
                }
                // 2020/02/28 QC#55971 Mod Start
                // scrnMsg.prntVndTpCd.setInputProtected(true);
                // START 2021/03/01 G.Delgado [QC#56057,DEL]
                // scrnMsg.xxLinkAncr_ST.setInputProtected(true);
                // scrnMsg.prntVndTpDescTxt.setInputProtected(true);
                // END 2021/03/01 G.Delgado [QC#56057,DEL]
                // 2020/02/28 QC#55971 Mod End
            }
            // QC#13128 End
            // START 2021/03/01 G.Delgado [QC#56057,ADD]
            String prntVndTpCdConst = ZYPCodeDataUtil.getVarCharConstValue(NMAL6860_PRNT_VND_TP_CD_FIXED, scrnMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(prntVndTpCdConst) && ZYPCommonFunc.hasValue(scrnMsg.prntVndTpCd)) {
                String[] fixedPrntVndTpCds = prntVndTpCdConst.split(",");
                if (Arrays.asList(fixedPrntVndTpCds).contains(scrnMsg.prntVndTpCd.getValue())) {
                    scrnMsg.prntVndTpDescTxt.setInputProtected(true);
                    scrnMsg.xxLinkAncr_ST.setInputProtected(true);
                } else {
                    scrnMsg.prntVndTpDescTxt.setInputProtected(false);
                    scrnMsg.xxLinkAncr_ST.setInputProtected(false);
                }
            }
            // END 2021/03/01 G.Delgado [QC#56057,ADD]
        } else {
            // If Detail Tab is selected,
            // protects the input fields on Common Header.
            scrnMsg.prntVndCd.setInputProtected(true);
            scrnMsg.prntVndNm.setInputProtected(true);
            // 2020/02/28 QC#55971 Mod Start
            // scrnMsg.prntVndTpCd.setInputProtected(true);
            scrnMsg.xxLinkAncr_ST.setInputProtected(true);
            scrnMsg.prntVndTpDescTxt.setInputProtected(true);
            // 2020/02/28 QC#55971 Mod End
            // START 2017/02/20 [QC#16081, ADD]
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                scrnMsg.A.no(i).xxComnScrFirstValTxt_AL.setIndispensable(false);
            }
            // END   2017/02/20 [QC#16081, ADD]
        }
    }

    public static void setInputProtectedForGeneralTab(NMAL6860BMsg scrnMsg) {
        // QC#27280
        if (isEntryGranted() && !(ZYPCommonFunc.hasValue(scrnMsg.prntVndTpCd_DF) && scrnMsg.prntVndTpCd_DF.getValue().equals(scrnMsg.prntVndTpCd.getValue()))) {

            scrnMsg.taxPayerId_H1.setInputProtected(false);
            scrnMsg.inacDt_H1.setInputProtected(false);
            scrnMsg.taxPayerRgNum_H1.setInputProtected(false);
            scrnMsg.arcsSplyNum_H1.setInputProtected(false);
            scrnMsg.arcsSplyId_H1.setInputProtected(false);
            scrnMsg.dsAcctNum_H1.setInputProtected(false);

            scrnMsg.indyTpCd_H1.setInputProtected(false);
            scrnMsg.mnrtyOwndTpCd_H1.setInputProtected(false);
            scrnMsg.splyOrgTpCd_H1.setInputProtected(false);
            scrnMsg.splyOneTmFlg_H1.setInputProtected(false);
            scrnMsg.smBizFlg_H1.setInputProtected(false);
            scrnMsg.womenOwndFlg_H1.setInputProtected(false);
            scrnMsg.coaAfflCd_H1.setInputProtected(false);

            scrnMsg.payAloneFlg_H1.setInputProtected(false);
            scrnMsg.discTakeFlg_H1.setInputProtected(false);
            scrnMsg.poPmtHldFlg_H1.setInputProtected(false);
            scrnMsg.vndPmtTermDescTxt_H1.setInputProtected(false);
            scrnMsg.vndPmtMethCd_H1.setInputProtected(false);
            scrnMsg.payGrpCd_H1.setInputProtected(false);

            scrnMsg.fdRptFlg_H1.setInputProtected(false);
            scrnMsg.incTaxTpCd_H1.setInputProtected(false);
            scrnMsg.stTaxFlg_H1.setInputProtected(false);
            scrnMsg.splyRptNm_H1.setInputProtected(false);
            scrnMsg.splyHubZnCd_H1.setInputProtected(false);

            scrnMsg.xxLinkAncr_CN.setInputProtected(false);
            scrnMsg.xxLinkAncr_IC.setInputProtected(false);
            scrnMsg.xxLinkAncr_PT.setInputProtected(false);

            for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                scrnMsg.A.no(index).vndCd_A.setInputProtected(true);
                if (ZYPConstant.FLG_ON_Y.endsWith(scrnMsg.A.no(index).coaEnblFlg_A.getValue())) {
                    scrnMsg.A.no(index).xxComnScrFirstValTxt_AL.setInputProtected(false);
                } else {
                    scrnMsg.A.no(index).xxComnScrFirstValTxt_AL.setInputProtected(true);
                }

                scrnMsg.A.no(index).inacDt_A.setInputProtected(true);
            }
        // QC#27280
        } else if (isEntryGranted() && ZYPCommonFunc.hasValue(scrnMsg.prntVndTpCd_DF) && scrnMsg.prntVndTpCd_DF.getValue().equals(scrnMsg.prntVndTpCd.getValue())) {
            for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                scrnMsg.A.no(index).vndCd_A.setInputProtected(true);
                // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
                //scrnMsg.A.no(index).xxComnScrFirstValTxt_AL.setInputProtected(true);
                //scrnMsg.A.no(index).inacDt_A.setInputProtected(true);
                if (!(ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_DF) && scrnMsg.prntVndNm_DF.getValue().equals(NEW_SUPPLIER))) {
                    scrnMsg.A.no(index).xxComnScrFirstValTxt_AL.setInputProtected(true);
                    scrnMsg.A.no(index).inacDt_A.setInputProtected(true);
                }
                // END 2020/10/28 R.Kurahashi [QC#57732,MOD]
            }
            scrnMsg.taxPayerId_H1.setInputProtected(true);
            scrnMsg.inacDt_H1.setInputProtected(true);
            scrnMsg.taxPayerRgNum_H1.setInputProtected(true);
            scrnMsg.arcsSplyNum_H1.setInputProtected(true);
            scrnMsg.arcsSplyId_H1.setInputProtected(true);
            scrnMsg.dsAcctNum_H1.setInputProtected(true);

            scrnMsg.indyTpCd_H1.setInputProtected(true);
            scrnMsg.mnrtyOwndTpCd_H1.setInputProtected(true);
            scrnMsg.splyOrgTpCd_H1.setInputProtected(true);
            scrnMsg.splyOneTmFlg_H1.setInputProtected(true);
            scrnMsg.smBizFlg_H1.setInputProtected(true);
            scrnMsg.womenOwndFlg_H1.setInputProtected(true);
            scrnMsg.coaAfflCd_H1.setInputProtected(true);

            scrnMsg.payAloneFlg_H1.setInputProtected(true);
            scrnMsg.discTakeFlg_H1.setInputProtected(true);
            scrnMsg.poPmtHldFlg_H1.setInputProtected(true);
            scrnMsg.vndPmtTermDescTxt_H1.setInputProtected(true);
            scrnMsg.vndPmtMethCd_H1.setInputProtected(true);
            scrnMsg.payGrpCd_H1.setInputProtected(true);

            scrnMsg.fdRptFlg_H1.setInputProtected(true);
            scrnMsg.incTaxTpCd_H1.setInputProtected(true);
            scrnMsg.stTaxFlg_H1.setInputProtected(true);
            scrnMsg.splyRptNm_H1.setInputProtected(true);
            scrnMsg.splyHubZnCd_H1.setInputProtected(true);

            scrnMsg.xxLinkAncr_CN.setInputProtected(true);
            scrnMsg.xxLinkAncr_IC.setInputProtected(true);
            scrnMsg.xxLinkAncr_PT.setInputProtected(true);

        } else {
            scrnMsg.taxPayerId_H1.setInputProtected(true);
            scrnMsg.inacDt_H1.setInputProtected(true);
            scrnMsg.taxPayerRgNum_H1.setInputProtected(true);
            scrnMsg.arcsSplyNum_H1.setInputProtected(true);
            scrnMsg.arcsSplyId_H1.setInputProtected(true);
            scrnMsg.dsAcctNum_H1.setInputProtected(true);

            scrnMsg.indyTpCd_H1.setInputProtected(true);
            scrnMsg.mnrtyOwndTpCd_H1.setInputProtected(true);
            scrnMsg.splyOrgTpCd_H1.setInputProtected(true);
            scrnMsg.splyOneTmFlg_H1.setInputProtected(true);
            scrnMsg.smBizFlg_H1.setInputProtected(true);
            scrnMsg.womenOwndFlg_H1.setInputProtected(true);
            scrnMsg.coaAfflCd_H1.setInputProtected(true);

            scrnMsg.payAloneFlg_H1.setInputProtected(true);
            scrnMsg.discTakeFlg_H1.setInputProtected(true);
            scrnMsg.poPmtHldFlg_H1.setInputProtected(true);
            scrnMsg.vndPmtTermDescTxt_H1.setInputProtected(true);
            scrnMsg.vndPmtMethCd_H1.setInputProtected(true);
            scrnMsg.payGrpCd_H1.setInputProtected(true);

            scrnMsg.fdRptFlg_H1.setInputProtected(true);
            scrnMsg.incTaxTpCd_H1.setInputProtected(true);
            scrnMsg.stTaxFlg_H1.setInputProtected(true);
            scrnMsg.splyRptNm_H1.setInputProtected(true);
            scrnMsg.splyHubZnCd_H1.setInputProtected(true);

            scrnMsg.xxLinkAncr_CN.setInputProtected(true);
            scrnMsg.xxLinkAncr_IC.setInputProtected(true);
            scrnMsg.xxLinkAncr_PT.setInputProtected(true);
        }

        setInputProtectedForGeneralTabDetail(scrnMsg);
    }

    public static void setInputProtectedForGeneralTabDetail(NMAL6860BMsg scrnMsg) {

        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            // START 2021/03/01 G.Delgado [QC#56057,MOD]
            // if (isEntryGranted()) {
                // QC#13313 Start
                // if (VND_TP.SUPPLIER.equals(scrnMsg.vndTpCd.getValue())) {
                // scrnMsg.A.no(index).splyPoFlg_A.setInputProtected(true);
                // } else {
                // scrnMsg.A.no(index).splyPoFlg_A.setInputProtected(false);
                // }
                // QC#13313 End
            // } else {
            if (!isEntryGranted()) {
            // END 2021/03/01 G.Delgado [QC#56057,MOD]
                scrnMsg.A.no(index).vndCd_A.setInputProtected(true);
                scrnMsg.A.no(index).locNm_A.setInputProtected(true);
                scrnMsg.A.no(index).ctryCd_A.setInputProtected(true);
                scrnMsg.A.no(index).xxComnScrFirstValTxt_A.setInputProtected(true);
                scrnMsg.A.no(index).xxComnScrScdValTxt_A.setInputProtected(true);
                scrnMsg.A.no(index).ctyAddr_A.setInputProtected(true);
                scrnMsg.A.no(index).postCd_A.setInputProtected(true);
                scrnMsg.A.no(index).stCd_A.setInputProtected(true);
                scrnMsg.A.no(index).cntyNm_A.setInputProtected(true);
                scrnMsg.A.no(index).splyPmtFlg_A.setInputProtected(true);
                scrnMsg.A.no(index).splyPoFlg_A.setInputProtected(true);
                scrnMsg.A.no(index).xxComnScrFirstValTxt_AL.setInputProtected(true);
                scrnMsg.A.no(index).inacDt_A.setInputProtected(true);
            }
        }
    }

    public static void setInputProtectedForDetailTab(NMAL6860BMsg scrnMsg) {
        if (isEntryGranted()) {
            // START 2021/03/01 G.Delgado [QC#56057,ADD]
            String arRefundPrntVndTpCdConst = ZYPCodeDataUtil.getVarCharConstValue(ARREFUND_PRNT_VND_TP_CD, scrnMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(arRefundPrntVndTpCdConst) && arRefundPrntVndTpCdConst.equals(scrnMsg.prntVndTpCd.getValue())) {
                // Disable all items except inactive date
                scrnMsg.vndCd_H2.setInputProtected(true);
                scrnMsg.invMatchTpCd_H2.setInputProtected(true);
                scrnMsg.invTolRate_H2.setInputProtected(true);
                scrnMsg.rcvTolRate_H2.setInputProtected(true);
                scrnMsg.vndPmtTermDescTxt_H2.setInputProtected(true);
                scrnMsg.vndPmtMethCd_H2.setInputProtected(true);
                scrnMsg.payGrpCd_H2.setInputProtected(true);

                scrnMsg.locNm_H2.setInputProtected(true);
                scrnMsg.splyEdiLocCd_H2.setInputProtected(true);
                scrnMsg.splyEdiNum_H2.setInputProtected(true);
                scrnMsg.splySiteDealCd_H2.setInputProtected(true);
                scrnMsg.trsmtMethTpCd_H2.setInputProtected(true);
                scrnMsg.xxComnScrFirstValTxt_H2.setInputProtected(true);
                scrnMsg.xxComnScrScdValTxt_H2.setInputProtected(true);

                scrnMsg.xtrnlRefNum_H2.setInputProtected(true);
                scrnMsg.endCustNum_H2.setInputProtected(true);
                scrnMsg.arcsSplySiteCd_H2.setInputProtected(true);
                scrnMsg.arcsSplySiteId_H2.setInputProtected(true);
                scrnMsg.locNum_H2.setInputProtected(true);
                scrnMsg.invVndCd_H2.setInputProtected(true);

                scrnMsg.B.setInputProtected(true);

            } else {
            // END 2021/03/01 G.Delgado [QC#56057,ADD]
                scrnMsg.vndCd_H2.setInputProtected(true);
                scrnMsg.locNm_H2.setInputProtected(true);
                // QC#15816 Mod.
                // if (ZYPConstant.FLG_ON_Y.endsWith(scrnMsg.A.no(0).coaEnblFlg_A.getValue())) {
                // scrnMsg.xxComnScrFirstValTxt_H2.setInputProtected(false);
                // scrnMsg.xxComnScrScdValTxt_H2.setInputProtected(false);
                // } else {
                // scrnMsg.xxComnScrFirstValTxt_H2.setInputProtected(true);
                // scrnMsg.xxComnScrScdValTxt_H2.setInputProtected(true);
                // }
                if (ZYPConstant.FLG_ON_Y.endsWith(scrnMsg.coaEnblFlg_PR.getValue())) {
                    scrnMsg.xxComnScrFirstValTxt_H2.setInputProtected(false);
                } else {
                    scrnMsg.xxComnScrFirstValTxt_H2.setInputProtected(true);
                }
                if (ZYPConstant.FLG_ON_Y.endsWith(scrnMsg.coaEnblFlg_VR.getValue())) {
                    scrnMsg.xxComnScrScdValTxt_H2.setInputProtected(false);
                } else {
                    scrnMsg.xxComnScrScdValTxt_H2.setInputProtected(true);
                }
            // START 2021/03/01 G.Delgado [QC#56057,ADD]
            }
            // END 2021/03/01 G.Delgado [QC#56057,ADD]

        } else {
            scrnMsg.vndCd_H2.setInputProtected(true);
            scrnMsg.invMatchTpCd_H2.setInputProtected(true);
            scrnMsg.invTolRate_H2.setInputProtected(true);
            scrnMsg.rcvTolRate_H2.setInputProtected(true);
            scrnMsg.vndPmtTermDescTxt_H2.setInputProtected(true);
            scrnMsg.vndPmtMethCd_H2.setInputProtected(true);
            scrnMsg.payGrpCd_H2.setInputProtected(true);

            scrnMsg.locNm_H2.setInputProtected(true);
            scrnMsg.splyEdiLocCd_H2.setInputProtected(true);
            scrnMsg.splyEdiNum_H2.setInputProtected(true);
            scrnMsg.inacDt_H2.setInputProtected(true);
            scrnMsg.splySiteDealCd_H2.setInputProtected(true);
            scrnMsg.trsmtMethTpCd_H2.setInputProtected(true);
            scrnMsg.xxComnScrFirstValTxt_H2.setInputProtected(true);
            scrnMsg.xxComnScrScdValTxt_H2.setInputProtected(true);

            scrnMsg.xtrnlRefNum_H2.setInputProtected(true);
            scrnMsg.endCustNum_H2.setInputProtected(true);
            scrnMsg.arcsSplySiteCd_H2.setInputProtected(true);
            scrnMsg.arcsSplySiteId_H2.setInputProtected(true);
            scrnMsg.billToCustCd_H2.setInputProtected(true);
            scrnMsg.invVndCd_H2.setInputProtected(true);

            // Detail on Detail Tab.
            for (int index = 0; index < scrnMsg.B.getValidCount(); index++) {
                scrnMsg.B.no(index).ctacPsnLastNm_B.setInputProtected(true);
                scrnMsg.B.no(index).ctacPsnFirstNm_B.setInputProtected(true);
                scrnMsg.B.no(index).dsCtacPsnTtlCd_B.setInputProtected(true);
                scrnMsg.B.no(index).dsCtacPntValTxt_BT.setInputProtected(true);
                scrnMsg.B.no(index).dsCtacPntValTxt_BF.setInputProtected(true);
                scrnMsg.B.no(index).dsCtacPsnDeptCd_B.setInputProtected(true);
                scrnMsg.B.no(index).ctacTpCd_B.setInputProtected(true);
                scrnMsg.B.no(index).dsCtacPntValTxt_BE.setInputProtected(true);
                scrnMsg.B.no(index).inacDt_B.setInputProtected(true);
            }
        }
    }

    /***
     * <p>
     * Checks if entry is granted.
     * </p>
     * @return If entry is granted, return true.
     */
    public static final boolean isEntryGranted() {
        S21UserProfileService service = S21UserProfileServiceFactory.getInstance().getService();
        return service.isFunctionGranted(service.getContextUserInfo().getUserId(), FUCTION_CODE_UPDATE);
    }

    /**
     * <p>
     * Activates the common button.
     * </p>
     * @param button the enumeration of common button.
     * @param handler S21CommonHandler
     */
    public static void activateButton(BTN_CMN button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.ENABLED);
    }

    /**
     * <p>
     * Deactivates the common button.
     * </p>
     * @param button the enumeration of common button.
     * @param handler S21CommonHandler
     */
    public static void deactivateButton(BTN_CMN button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.DISABLED);
    }

    /**
     * <p>
     * Sets the properties of common button.
     * </p>
     * @param handler S21CommonHandler
     * @param button the enumeration of common button.
     * @param status the button status.
     */
    private static void setButtonProperties(S21CommonHandler handler, BTN_CMN button, BTN_STATUS status) {
        handler.setButtonProperties(button.getButtonName(), button.getEventName(), button.getLabel(), status.getStatus(), null);
    }

    /**
     * <p>
     * Activates the application button.
     * </p>
     * @param button the enumeration of application button.
     * @param handler S21CommonHandler
     */
    public static void activateButton(BTN_APP button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.ENABLED);
    }

    /**
     * <p>
     * Deactivates the application button.
     * </p>
     * @param button the enumeration of application button.
     * @param handler S21CommonHandler
     */
    public static void deactivateButton(BTN_APP button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.DISABLED);
    }

    /**
     * <p>
     * Sets the properties of common button.
     * </p>
     * @param handler S21CommonHandler
     * @param button the enumeration of common button.
     * @param status the button status.
     */
    private static void setButtonProperties(S21CommonHandler handler, BTN_APP button, BTN_STATUS status) {
        handler.setButtonEnabled(button.getName(), status.isEnabled());
    }
    // START 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
    public static void deactivateButton(BTN_APP button, S21CommonHandler handler, int no) {
        setButtonProperties(handler, button, BTN_STATUS.DISABLED, no);
    }
    
    private static void setButtonProperties(S21CommonHandler handler, BTN_APP button, BTN_STATUS status, int no) {
        handler.setButtonEnabled(button.getName(), no, status.isEnabled());
    }
    // END 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setDisplayNameForMessage(NMAL6860BMsg scrnMsg) {
        // Common Header
        scrnMsg.prntVndCd.setNameForMessage("Supplier Number");
        scrnMsg.prntVndNm.setNameForMessage("Supplier Name");
        // 2020/02/28 QC#55971 Mod Start
        // scrnMsg.prntVndTpCd.setNameForMessage("Supplier Type");
        scrnMsg.prntVndTpDescTxt.setNameForMessage("Supplier Type");
        // 2020/02/28 QC#55971 Mod End

        setDisplayNameForMessageOfGeneralTab(scrnMsg);

        setDisplayNameForMessageOfDetailTab(scrnMsg);
    }

    private static void setDisplayNameForMessageOfGeneralTab(NMAL6860BMsg scrnMsg) {
        // Header on General Tab.
        scrnMsg.taxPayerId_H1.setNameForMessage("Tax Payer ID");
        scrnMsg.inacDt_H1.setNameForMessage("Inactive On");
        scrnMsg.taxPayerRgNum_H1.setNameForMessage("Tax Payer Reg No");
        scrnMsg.arcsSplyNum_H1.setNameForMessage("ARCS Supplier Number");
        scrnMsg.arcsSplyId_H1.setNameForMessage("ARCS Supplier ID");
        scrnMsg.dsAcctNum_H1.setNameForMessage("Customer Number");

        scrnMsg.indyTpCd_H1.setNameForMessage("SIC");
        scrnMsg.mnrtyOwndTpCd_H1.setNameForMessage("Minority Owned");
        scrnMsg.splyOrgTpCd_H1.setNameForMessage("Organization Type");
        scrnMsg.splyOneTmFlg_H1.setNameForMessage("One Time Supplier");
        scrnMsg.smBizFlg_H1.setNameForMessage("Small Business");
        scrnMsg.womenOwndFlg_H1.setNameForMessage("Women Owned");
        // Mod Start 2016/11/24 QC#15438
//        scrnMsg.coaAfflCd_H1.setNameForMessage("Affiliation");
        scrnMsg.coaAfflCd_H1.setNameForMessage("Intercompany");
        // Mod End 2016/11/24 QC#15438

        scrnMsg.payAloneFlg_H1.setNameForMessage("Pay Alone");
        scrnMsg.discTakeFlg_H1.setNameForMessage("Always Take Discount");
        scrnMsg.poPmtHldFlg_H1.setNameForMessage("Hold Flag");
        scrnMsg.vndPmtTermDescTxt_H1.setNameForMessage("Payment Terms");
        scrnMsg.vndPmtMethCd_H1.setNameForMessage("Payment Method");
        scrnMsg.payGrpCd_H1.setNameForMessage("Pay Group");

        scrnMsg.fdRptFlg_H1.setNameForMessage("Federal Tax");
        scrnMsg.incTaxTpCd_H1.setNameForMessage("Income Tax Type");
        scrnMsg.stTaxFlg_H1.setNameForMessage("State Tax");
        scrnMsg.splyRptNm_H1.setNameForMessage("Reporting Name");
        scrnMsg.splyHubZnCd_H1.setNameForMessage("Hub Zone");

        // Detail on General Tab.
        for (int index = 0; index < scrnMsg.A.length(); index++) {
            setDisplayNameForMessageOfSupplierSite(scrnMsg.A.no(index));
        }
    }

    public static void setDisplayNameForMessageOfSupplierSite(NMAL6860_ABMsg scrnRow) {
        scrnRow.vndCd_A.setNameForMessage("Site Code");
        scrnRow.locNm_A.setNameForMessage("Site Name");
        scrnRow.ctryCd_A.setNameForMessage("Country");
        scrnRow.xxComnScrFirstValTxt_A.setNameForMessage("Address1");
        scrnRow.xxComnScrScdValTxt_A.setNameForMessage("Address2");
        scrnRow.ctyAddr_A.setNameForMessage("City");
        scrnRow.postCd_A.setNameForMessage("PostalCode");
        scrnRow.stCd_A.setNameForMessage("State");
        scrnRow.cntyNm_A.setNameForMessage("County");
        scrnRow.splyPmtFlg_A.setNameForMessage("Pay Flag");
        scrnRow.splyPoFlg_A.setNameForMessage("Pur Flag");
        scrnRow.xxComnScrFirstValTxt_AL.setNameForMessage("Liability Account");
        scrnRow.inacDt_A.setNameForMessage("Inactive On");
    }

    public static void setDisplayNameForMessageOfDetailTab(NMAL6860BMsg scrnMsg) {
        scrnMsg.vndCd_H2.setNameForMessage("Supplier Site Code");
        scrnMsg.invMatchTpCd_H2.setNameForMessage("Invoice Match Option");
        scrnMsg.invTolRate_H2.setNameForMessage("Invoice Tolerance %");
        scrnMsg.rcvTolRate_H2.setNameForMessage("Receipt Tolerance %");
        scrnMsg.vndPmtTermDescTxt_H2.setNameForMessage("Payment Terms");
        scrnMsg.vndPmtMethCd_H2.setNameForMessage("Payment Method");
        scrnMsg.payGrpCd_H2.setNameForMessage("Pay Group");

        scrnMsg.locNm_H2.setNameForMessage("Supplier Site Name");
        scrnMsg.splyEdiLocCd_H2.setNameForMessage("EDI Location");
        scrnMsg.splyEdiNum_H2.setNameForMessage("EDI ID Num");
        scrnMsg.inacDt_H2.setNameForMessage("Inactive On");
        scrnMsg.splySiteDealCd_H2.setNameForMessage("CUSA Dealer Code");
        scrnMsg.trsmtMethTpCd_H2.setNameForMessage("Outb PO Trans Method");

        scrnMsg.xxComnScrFirstValTxt_H2.setNameForMessage("PrePay Account");
        scrnMsg.xxComnScrScdValTxt_H2.setNameForMessage("Vnd Return Account");

        scrnMsg.xtrnlRefNum_H2.setNameForMessage("External Reference");
        scrnMsg.endCustNum_H2.setNameForMessage("End Customer Num");
        scrnMsg.arcsSplySiteCd_H2.setNameForMessage("ARCS Supplier Site Number");
        scrnMsg.arcsSplySiteId_H2.setNameForMessage("ARCS Supplier Site ID");
        scrnMsg.locNum_H2.setNameForMessage("Customer Location");
        scrnMsg.invVndCd_H2.setNameForMessage("Invoice Supplier Site");

        // Detail on Detail Tab.
        for (int index = 0; index < scrnMsg.B.length(); index++) {
            setDisplayNameForMessageOfContactInfo(scrnMsg.B.no(index));
        }
    }

    public static void setDisplayNameForMessageOfContactInfo(NMAL6860_BBMsg scrnRow) {
        scrnRow.ctacPsnLastNm_B.setNameForMessage("Last Name");
        scrnRow.ctacPsnFirstNm_B.setNameForMessage("First Name");
        scrnRow.dsCtacPsnTtlCd_B.setNameForMessage("Title");
        scrnRow.dsCtacPntValTxt_BT.setNameForMessage("Telephone#");
        scrnRow.dsCtacPntValTxt_BF.setNameForMessage("Fax#");
        scrnRow.dsCtacPsnDeptCd_B.setNameForMessage("Department");
        scrnRow.ctacTpCd_B.setNameForMessage("Contact Type");
        scrnRow.dsCtacPntValTxt_BE.setNameForMessage("Email Address");
        scrnRow.inacDt_B.setNameForMessage("Inactive On");
    }

    public static Object[] createGLCommonPopupPrams(NMAL6860BMsg scrnMsg, EZDBStringItem strItem, String accType, EZDBStringItem enblFlg) {

        // QC#15816 Mod.
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxFuncId_D, NMAL6860Constant.BIZ_ID);
        if (ACC_TYPE_LIABILITY.equals(accType)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFuncId_D, NMAL6860Constant.BIZ_ID + ACC_TYPE_LIABILITY);
        } else if (ACC_TYPE_PREPAY.equals(accType)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFuncId_D, NMAL6860Constant.BIZ_ID + ACC_TYPE_PREPAY);
        } else if (ACC_TYPE_VNDRETURN.equals(accType)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFuncId_D, NMAL6860Constant.BIZ_ID + ACC_TYPE_VNDRETURN);
        }

        if (ZYPCommonFunc.hasValue(strItem)) {
            String accountStr = strItem.getValue();
            String coaCds[] = accountStr.split("\\.");
            int count = coaCds.length;

            // START 2016/08/04 [QC#12765, MOD]
            if (count > 0 && !"null".equals(coaCds[0])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaCmpyCd_D, coaCds[0]);
            } else {
                scrnMsg.coaCmpyCd_D.clear();
            }
            if (count > 1 && !"null".equals(coaCds[1])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaBrCd_D, coaCds[1]);
            } else {
                scrnMsg.coaBrCd_D.clear();
            }
            if (count > 2 && !"null".equals(coaCds[2])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaCcCd_D, coaCds[2]);
            } else {
                scrnMsg.coaCcCd_D.clear();
            }
            if (count > 3 && !"null".equals(coaCds[3])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaAcctCd_D, coaCds[3]);
            } else {
                scrnMsg.coaAcctCd_D.clear();
            }
            if (count > 4 && !"null".equals(coaCds[4])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd_D, coaCds[4]);
            } else {
                scrnMsg.coaProdCd_D.clear();
            }
            if (count > 5 && !"null".equals(coaCds[5])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaChCd_D, coaCds[5]);
            } else {
                scrnMsg.coaChCd_D.clear();
            }
            if (count > 6 && !"null".equals(coaCds[6])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaAfflCd_D, coaCds[6]);
            } else {
                scrnMsg.coaAfflCd_D.clear();
            }
            if (count > 7 && !"null".equals(coaCds[7])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProjCd_D, coaCds[7]);
            } else {
                scrnMsg.coaProjCd_D.clear();
            }
            if (count > 8 && !"null".equals(coaCds[8])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaExtnCd_D, coaCds[8]);
            } else {
                scrnMsg.coaExtnCd_D.clear();
            }
            // END   2016/08/04 [QC#12765, MOD]
        } else {
            scrnMsg.coaCmpyCd_D.clear();
            scrnMsg.coaAfflCd_D.clear();
            scrnMsg.coaBrCd_D.clear();
            scrnMsg.coaCcCd_D.clear();
            scrnMsg.coaAcctCd_D.clear();
            scrnMsg.coaProdCd_D.clear();
            scrnMsg.coaChCd_D.clear();
            scrnMsg.coaProjCd_D.clear();
            scrnMsg.coaExtnCd_D.clear();
        }

//START 2016/11/09 [QC#15616, DEL]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D1, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D2, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D3, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D4, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D5, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D6, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D7, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D8, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D9, ZYPConstant.FLG_ON_Y);
//END   2016/11/09 [QC#15616, DEL]

        // creates the parameter.
        Object[] params = null;
        if (enblFlg == null) {
            params = new Object[10];
        } else {
            params = new Object[11];
        }
        params[0] = scrnMsg.xxFuncId_D;
        params[1] = scrnMsg.coaCmpyCd_D;
        params[2] = scrnMsg.coaAfflCd_D;
        params[3] = scrnMsg.coaBrCd_D;
        params[4] = scrnMsg.coaCcCd_D;
        params[5] = scrnMsg.coaAcctCd_D;
        params[6] = scrnMsg.coaProdCd_D;
        params[7] = scrnMsg.coaChCd_D;
        params[8] = scrnMsg.coaProjCd_D;
        params[9] = scrnMsg.coaExtnCd_D;
        // QC#27280
        if (ACC_TYPE_LIABILITY.equals(accType) // 
                && ZYPCommonFunc.hasValue(scrnMsg.prntVndTpCd_DF) //
                && scrnMsg.prntVndTpCd_DF.getValue().equals(scrnMsg.prntVndTpCd.getValue())) {
            enblFlg.setValue(ZYPConstant.FLG_ON_Y);
            params[10] = enblFlg;
        }
//START 2016/11/09 [QC#15616, DEL]
//        params[10] = scrnMsg.xxDplyCtrlFlg_D1;
//        params[11] = scrnMsg.xxDplyCtrlFlg_D2;
//        params[12] = scrnMsg.xxDplyCtrlFlg_D3;
//        params[13] = scrnMsg.xxDplyCtrlFlg_D4;
//        params[14] = scrnMsg.xxDplyCtrlFlg_D5;
//        params[15] = scrnMsg.xxDplyCtrlFlg_D6;
//        params[16] = scrnMsg.xxDplyCtrlFlg_D7;
//        params[17] = scrnMsg.xxDplyCtrlFlg_D8;
//        params[18] = scrnMsg.xxDplyCtrlFlg_D9;
//END   2016/11/09 [QC#15616, DEL]

        return params;
    }

    public static String getGLCommonPopupResult(NMAL6860BMsg scrnMsg) {

        String result = "";

        // START 2016/08/04 [QC#12765,MOD]
        boolean nullCheck = false;
        if (ZYPCommonFunc.hasValue(scrnMsg.coaCmpyCd_D)) {
            result += scrnMsg.coaCmpyCd_D.getValue();
            nullCheck = true;
        }
        result += ".";
        if (ZYPCommonFunc.hasValue(scrnMsg.coaBrCd_D)) {
            result += scrnMsg.coaBrCd_D.getValue();
            nullCheck = true;
        }
        result += ".";
        if (ZYPCommonFunc.hasValue(scrnMsg.coaCcCd_D)) {
            result += scrnMsg.coaCcCd_D.getValue();
            nullCheck = true;
        }
        result += ".";
        if (ZYPCommonFunc.hasValue(scrnMsg.coaAcctCd_D)) {
            result += scrnMsg.coaAcctCd_D.getValue();
            nullCheck = true;
        }
        result += ".";
        if (ZYPCommonFunc.hasValue(scrnMsg.coaProdCd_D)) {
            result += scrnMsg.coaProdCd_D.getValue();
            nullCheck = true;
        }
        result += ".";
        if (ZYPCommonFunc.hasValue(scrnMsg.coaChCd_D)) {
            result += scrnMsg.coaChCd_D.getValue();
            nullCheck = true;
        }
        result += ".";
        if (ZYPCommonFunc.hasValue(scrnMsg.coaAfflCd_D)) {
            result += scrnMsg.coaAfflCd_D.getValue();
            nullCheck = true;
        }
        result += ".";
        if (ZYPCommonFunc.hasValue(scrnMsg.coaProjCd_D)) {
            result += scrnMsg.coaProjCd_D.getValue();
            nullCheck = true;
        }
        result += ".";
        if (ZYPCommonFunc.hasValue(scrnMsg.coaExtnCd_D)) {
            result += scrnMsg.coaExtnCd_D.getValue();
            nullCheck = true;
        }
        // END   2016/08/04 [QC#12765,MOD]

        if (nullCheck) {
            return result;
        } else {
            return null;
        }
    }

    public static void setInitParam_Affiliation(NMAL6860BMsg scrnMsg) {

        scrnMsg.xxTblNm_Z1.setValue("COA_AFFL");
        scrnMsg.xxTblCdColNm_Z1.setValue("COA_AFFL_CD");
        scrnMsg.xxTblNmColNm_Z1.setValue("COA_AFFL_NM");
        scrnMsg.xxTblSortColNm_Z1.setValue("COA_AFFL_CD");
        // Mod Start 2016/11/24 QC#15438
//        scrnMsg.xxScrNm_Z1.setValue("Lookup Affiliation");
//        scrnMsg.xxHdrCdLbNm_Z1.setValue("Affiliation Code");
//        scrnMsg.xxHdrNmLbNm_Z1.setValue("Affiliation Name");
//        scrnMsg.xxDtlCdLbNm_Z1.setValue("Affiliation Code");
//        scrnMsg.xxDtlNmLbNm_Z1.setValue("Affiliation Name");
        scrnMsg.xxScrNm_Z1.setValue("Lookup Intercompany");
        scrnMsg.xxHdrCdLbNm_Z1.setValue("Intercompany Code");
        scrnMsg.xxHdrNmLbNm_Z1.setValue("Intercompany Name");
        scrnMsg.xxDtlCdLbNm_Z1.setValue("Intercompany Code");
        scrnMsg.xxDtlNmLbNm_Z1.setValue("Intercompany Name");
        // Mod End 2016/11/24 QC#15438
        if (ZYPCommonFunc.hasValue(scrnMsg.coaAfflCd_H1)) {
            scrnMsg.xxCondCd_Z1.setValue(scrnMsg.coaAfflCd_H1.getValue());
        } else {
            scrnMsg.xxCondCd_Z1.clear();
        }
        scrnMsg.xxCondNm_Z1.clear();
    }

    public static Object[] getParamOpenWin_NMAL6050(NMAL6860BMsg scrnMsg) {

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_Z1;
        params[1] = scrnMsg.xxTblCdColNm_Z1;
        params[2] = scrnMsg.xxTblNmColNm_Z1;
        params[3] = scrnMsg.xxTblSortColNm_Z1;
        params[4] = scrnMsg.xxScrNm_Z1;
        params[5] = scrnMsg.xxHdrCdLbNm_Z1;
        params[6] = scrnMsg.xxHdrNmLbNm_Z1;
        params[7] = scrnMsg.xxDtlCdLbNm_Z1;
        params[8] = scrnMsg.xxDtlNmLbNm_Z1;
        params[9] = scrnMsg.xxCondCd_Z1;
        params[10] = scrnMsg.xxCondNm_Z1;

        return params;
    }

    /**
     * Get Param NWAL1130 For InvoiceSupplier
     * @param scrnMsg NMAL6860BMsg
     * @return Param NWAL1130 For InvoiceSupplier
     */
    public static Object[] getParamNWAL1130ForInvoiceSupplier(NMAL6860BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "Z";
        params[1] = "Invoice Supplier/Supplier Site Search";
        params[2] = "PO_VND_V";

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Supplier Code";
        whereArray0[1] = "PRNT_VND_CD";
        whereArray0[2] = null;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Supplier Name";
        whereArray1[1] = "PRNT_VND_NM";
        whereArray1[2] = null;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Supplier Site Code";
        whereArray2[1] = "VND_CD";
        whereArray2[2] = scrnMsg.invVndCd_H2.getValue();
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Supplier Site Name";
        whereArray3[1] = "VND_NM";
        whereArray3[2] = null;
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Supplier Code";
        columnArray0[1] = "PRNT_VND_CD";
        columnArray0[2] = BigDecimal.valueOf(15);
        columnArray0[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Supplier Name";
        columnArray1[1] = "PRNT_VND_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Supplier Site Code";
        columnArray2[1] = "VND_CD";
        columnArray2[2] = BigDecimal.valueOf(15);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Supplier Site Name";
        columnArray3[1] = "VND_NM";
        columnArray3[2] = BigDecimal.valueOf(30);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "PRNT_VND_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }

    // QC#13163 Start
    /**
     * Get Param NWAL1130 For Terms
     * @param scrnMsg NMAL6860BMsg
     * @return Param NWAL1130 For Payment Term
     */
    public static Object[] getParamNWAL1130ForPaymentTerm(NMAL6860BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "Z";
        params[1] = "Payment Term Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  VPT.EZCANCELFLAG          AS EZCANCELFLAG ");
        sb.append(", VPT.GLBL_CMPY_CD          AS GLBL_CMPY_CD ");
        sb.append(", VPT.VND_PMT_TERM_CD       AS VND_PMT_TERM_CD ");
        sb.append(", VPT.VND_PMT_TERM_DESC_TXT AS VND_PMT_TERM_DESC_TXT ");
        sb.append("FROM ");
        sb.append("  VND_PMT_TERM  VPT ");
        sb.append("WHERE ");
        sb.append("    VPT.EZCANCELFLAG   = '0' ");
        sb.append("AND VPT.GLBL_CMPY_CD   = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Payment Term Code";
        whereArray0[1] = "UPPER(VND_PMT_TERM_CD)";
        whereArray0[2] = null;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Payment Term Name";
        whereArray1[1] = "UPPER(VND_PMT_TERM_DESC_TXT)";
        if (TAB_NM_GENERAL.equals(scrnMsg.xxDplyTab.getValue())) {
            whereArray1[2] = scrnMsg.vndPmtTermDescTxt_H1.getValue();
        } else {
            whereArray1[2] = scrnMsg.vndPmtTermDescTxt_H2.getValue();
        }
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Payment Term Code";
        columnArray0[1] = "VND_PMT_TERM_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Payment Term Name";
        columnArray1[1] = "VND_PMT_TERM_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "VND_PMT_TERM_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "VND_PMT_TERM_DESC_TXT";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }
    // QC#13163 End

    public static void addCheckGeneralTab(NMAL6860BMsg scrnMsg) {

        String slsDt = ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue());

        // checks the mandatory fields.
        scrnMsg.addCheckItem(scrnMsg.prntVndNm);
        // 2020/02/28 QC#55971 Mod Start
        //scrnMsg.addCheckItem(scrnMsg.prntVndTpCd);
        scrnMsg.addCheckItem(scrnMsg.prntVndTpDescTxt);
        // 2020/02/28 QC#55971 Mod End

        scrnMsg.addCheckItem(scrnMsg.taxPayerId_H1);
        scrnMsg.addCheckItem(scrnMsg.inacDt_H1);
        scrnMsg.addCheckItem(scrnMsg.taxPayerRgNum_H1);
        scrnMsg.addCheckItem(scrnMsg.arcsSplyNum_H1);
        scrnMsg.addCheckItem(scrnMsg.arcsSplyId_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);

        scrnMsg.addCheckItem(scrnMsg.indyTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaAfflCd_H1);

        scrnMsg.addCheckItem(scrnMsg.vndPmtTermDescTxt_H1);

        scrnMsg.addCheckItem(scrnMsg.splyRptNm_H1);
        scrnMsg.addCheckItem(scrnMsg.splyHubZnCd_H1);

        // Date check create mode
        if (!ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.inacDt_H1)) {
                if (ZYPDateUtil.compare(scrnMsg.inacDt_H1.getValue(), ZYPDateUtil.addDays(slsDt, 1)) < 0) {
                    scrnMsg.inacDt_H1.setErrorInfo(1, NMAM0808E);
                }
            }
        // 2016/08/23 QC#13165 start
        } else {
            // update mode
            if (ZYPCommonFunc.hasValue(scrnMsg.inacDt_H1)) {
                if (!scrnMsg.inacDt_H1.getValue().equals(scrnMsg.inacDt_G1.getValue())) {
                    if (ZYPDateUtil.compare(scrnMsg.inacDt_H1.getValue(), ZYPDateUtil.addDays(slsDt, 1)) < 0) {
                        scrnMsg.inacDt_H1.setErrorInfo(1, NMAM0808E);
                    }
                }
            }
        }
        // 2016/08/23 QC#13165 end

        // checks if the radio button is selected.
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_A)) {
            scrnMsg.setMessageInfo(NMAM0011E, new String[] {"Supplier Site" });
            scrnMsg.prntVndNm.setErrorInfo(1, NMAM0011E, new String[] {"Supplier Site" });
        }

        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(index).locNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).ctryCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).xxComnScrFirstValTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).xxComnScrScdValTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).ctyAddr_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).postCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).stCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).cntyNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).xxComnScrFirstValTxt_AL); // 2017/02/20 QC#16081 Add
            //QC#12215 Start
            if(CTRY.UNITED_STATES_OF_AMERICA.equals(scrnMsg.A.no(index).ctryCd_A.getValue())) {
                if (!ZYPCommonFunc.hasValue((scrnMsg.A.no(index).postCd_A))) {
                    scrnMsg.A.no(index).postCd_A.setErrorInfo(1, NMAM8643E, new String[] {scrnMsg.A.no(index).postCd_A.getNameForMessage()});
                }

                if (!ZYPCommonFunc.hasValue((scrnMsg.A.no(index).stCd_A))) {
                    scrnMsg.A.no(index).stCd_A.setErrorInfo(1, NMAM8643E, new String[] {scrnMsg.A.no(index).stCd_A.getNameForMessage()});
                }
            }
            //QC#12215 End

        }
    }

    /**
     * <pre>
     * set cursor rule
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setCursorRuleForSwhDetail(NMAL6860BMsg scrnMsg) {
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule("NMAL6860Scrn00", NMAL6860Bean.A);
        scrnMsg.addGUIAttribute(tblFocusRule);

        ZYPGUIFocusRule fRule;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            fRule = new ZYPGUIFocusRule("locNm_A" + "#" + i);
            fRule.setNextId(NMAL6860Bean.ctryCd_A + "#" + i);
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule("ctryCd_A" + "#" + i);
            fRule.setPrevId(NMAL6860Bean.locNm_A + "#" + i);
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule("vndCd_A" + "#" + i);
            if (i > 0) {
                fRule.setPrevId(NMAL6860Bean.inacDt_A + "#" + (i - 1));
            }
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule("inacDt_A" + "#" + i);
            if ((i + 1) != scrnMsg.A.length()) {
                fRule.setNextId(NMAL6860Bean.vndCd_A + "#" + (i + 1));
            }
            tblFocusRule.addRule(fRule);

        }
    }

    public static void addCheckDetailTab(NMAL6860BMsg scrnMsg) {

        String slsDt = ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue());

        // Detail Tab Check
        scrnMsg.addCheckItem(scrnMsg.invTolRate_H2);
        scrnMsg.addCheckItem(scrnMsg.rcvTolRate_H2);
        scrnMsg.addCheckItem(scrnMsg.vndPmtTermDescTxt_H2);

        scrnMsg.addCheckItem(scrnMsg.splyEdiLocCd_H2);
        scrnMsg.addCheckItem(scrnMsg.splyEdiNum_H2);
        scrnMsg.addCheckItem(scrnMsg.splyEdiNum_H2);
        scrnMsg.addCheckItem(scrnMsg.inacDt_H2);
        scrnMsg.addCheckItem(scrnMsg.splySiteDealCd_H2);

        scrnMsg.addCheckItem(scrnMsg.xtrnlRefNum_H2);
        scrnMsg.addCheckItem(scrnMsg.endCustNum_H2);
        scrnMsg.addCheckItem(scrnMsg.arcsSplySiteCd_H2);
        scrnMsg.addCheckItem(scrnMsg.arcsSplySiteId_H2);
        scrnMsg.addCheckItem(scrnMsg.locNum_H2);
        scrnMsg.addCheckItem(scrnMsg.invVndCd_H2);

        // Date check create mode
        if (ZYPCommonFunc.hasValue(scrnMsg.inacDt_H2)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.inacDt_H2)) {
                    if (ZYPDateUtil.compare(scrnMsg.inacDt_H2.getValue(), ZYPDateUtil.addDays(slsDt, 1)) < 0) {
                        scrnMsg.inacDt_H2.setErrorInfo(1, NMAM0808E);
                    }
                }
            // 2016/08/23 QC#13165 start
            } else {
                // update mode
                if (!scrnMsg.inacDt_H2.getValue().equals(scrnMsg.inacDt_G2.getValue())) {
                    if (ZYPDateUtil.compare(scrnMsg.inacDt_H2.getValue(), ZYPDateUtil.addDays(slsDt, 1)) < 0) {
                        scrnMsg.inacDt_H2.setErrorInfo(1, NMAM0808E);
                    }
                }
            }
            // 2016/08/23 QC#13165 end
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.invTolRate_H2)) {
            BigDecimal invTolRate = scrnMsg.invTolRate_H2.getValue();
            if (invTolRate.compareTo(BigDecimal.valueOf(0)) < 0 || invTolRate.compareTo(BigDecimal.valueOf(100)) > 0 || invTolRate.scale() > 2) {
                scrnMsg.invTolRate_H2.setErrorInfo(1, NMAM0052E, new String[] {scrnMsg.invTolRate_H2.getNameForMessage() });

            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.rcvTolRate_H2)) {
            BigDecimal rcvTolRate = scrnMsg.rcvTolRate_H2.getValue();
            if (rcvTolRate.compareTo(BigDecimal.valueOf(0)) < 0 || rcvTolRate.compareTo(BigDecimal.valueOf(100)) > 0 || rcvTolRate.scale() > 2) {
                scrnMsg.rcvTolRate_H2.setErrorInfo(1, NMAM0052E, new String[] {scrnMsg.rcvTolRate_H2.getNameForMessage() });
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.B.no(i).ctacPsnLastNm_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).ctacPsnFirstNm_B);

            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsCtacPntValTxt_BT);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsCtacPntValTxt_BF);

            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsCtacPntValTxt_BE);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).inacDt_B);

            // QC#16593 Mod.
//            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dsCtacPntValTxt_BT)) {
//                if (!NMXC102001_FormatValidation.checkTelFormat(scrnMsg.A.no(scrnMsg.xxRadioBtn_A.getValueInt()).ctryCd_A.getValue(), scrnMsg.B.no(i).dsCtacPntValTxt_BT.getValue())) {
//
//                    scrnMsg.B.no(i).dsCtacPntValTxt_BT.setErrorInfo(1, NMAM8075E, new String[] {TEL_FAX_CORRECT_FORMAT });
//                    scrnMsg.addCheckItem(scrnMsg.B.no(i).dsCtacPntValTxt_BT);
//                }
//            }
//            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dsCtacPntValTxt_BF)) {
//                if (!NMXC102001_FormatValidation.checkTelFormat(scrnMsg.A.no(scrnMsg.xxRadioBtn_A.getValueInt()).ctryCd_A.getValue(), scrnMsg.B.no(i).dsCtacPntValTxt_BF.getValue())) {
//
//                    scrnMsg.B.no(i).dsCtacPntValTxt_BF.setErrorInfo(1, NMAM8075E, new String[] {TEL_FAX_CORRECT_FORMAT });
//                    scrnMsg.addCheckItem(scrnMsg.B.no(i).dsCtacPntValTxt_BF);
//                }
//            }
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dsCtacPntValTxt_BT)) {
                if (!checkTelFormat(scrnMsg.B.no(i).dsCtacPntValTxt_BT)) {
                    scrnMsg.addCheckItem(scrnMsg.B.no(i).dsCtacPntValTxt_BT);
                }
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dsCtacPntValTxt_BF)) {
                if (!checkTelFormat(scrnMsg.B.no(i).dsCtacPntValTxt_BF)) {
                    scrnMsg.addCheckItem(scrnMsg.B.no(i).dsCtacPntValTxt_BF);
                }
            }
        }
    }
    
    /**
     * Get Address Lookup Popup param
     * @param scrnMsg NMAL6860BMsg
     * @param glblCmpyCd String
     * @param idxNum int
     * @return Parameter[ Object[7] ]
     */
    public static Object[] getAddressPopupParam(NMAL6860BMsg scrnMsg, String glblCmpyCd, int idxNum) {
        Object[] params = new Object[IDX_7];
        scrnMsg.P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_AD ,new BigDecimal(idxNum));
        params[IDX_0] = "";
        params[IDX_1] = "Address Lookup Popup";
        StringBuilder baseSql = new StringBuilder();
        baseSql.append("SELECT ");
        baseSql.append("    P.GLBL_CMPY_CD ");
        baseSql.append("  , P.EZCANCELFLAG ");
        baseSql.append("  , P.CTY_ADDR ");
        baseSql.append("  , P.ST_CD ");
        baseSql.append("  , P.POST_CD ");
        baseSql.append("  , C.CNTY_NM ");
        baseSql.append("FROM ");
        baseSql.append("    POST P ");
        baseSql.append("  , CNTY_POST_RELN R ");
        baseSql.append("  , CNTY C ");
        baseSql.append("WHERE ");
        baseSql.append("    P.GLBL_CMPY_CD = '" + glblCmpyCd + "' ");
        baseSql.append("AND P.EZCANCELFLAG = '0' ");
        baseSql.append("AND R.POST_PK(+) = P.POST_PK ");
        baseSql.append("AND R.GLBL_CMPY_CD(+) = P.GLBL_CMPY_CD ");
        baseSql.append("AND R.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.GLBL_CMPY_CD(+) = R.GLBL_CMPY_CD ");
        baseSql.append("AND C.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.CNTY_PK(+) = R.CNTY_PK ");
        String sql = baseSql.toString();
        params[IDX_2] = sql;

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "City";
        whereArray1[IDX_1] = "CTY_ADDR";
        whereArray1[IDX_2] = ((NMAL6860_ABMsg) scrnMsg.A.get(idxNum)).ctyAddr_A.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "State";
        whereArray2[IDX_1] = "ST_CD";
        whereArray2[IDX_2] = ((NMAL6860_ABMsg) scrnMsg.A.get(idxNum)).stCd_A.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Postal Code";
        whereArray3[IDX_1] = "POST_CD";
        whereArray3[IDX_2] = ((NMAL6860_ABMsg) scrnMsg.A.get(idxNum)).postCd_A.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "County";
        whereArray4[IDX_1] = "CNTY_NM";
        String selectedCntyNm = "";
        selectedCntyNm = ((NMAL6860_ABMsg) scrnMsg.A.get(idxNum)).cntyNm_A.getValue();
        whereArray4[IDX_2] = selectedCntyNm;
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[0] = "City";
        columnArray1[1] = "CTY_ADDR";
        columnArray1[2] = BigDecimal.valueOf(25);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[0] = "State";
        columnArray2[1] = "ST_CD";
        columnArray2[2] = BigDecimal.valueOf(5);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[0] = "Postal Code";
        columnArray3[1] = "POST_CD";
        columnArray3[2] = BigDecimal.valueOf(10);
        columnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[0] = "County";
        columnArray4[1] = "CNTY_NM";
        columnArray4[2] = BigDecimal.valueOf(30);
        columnArray4[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray4);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "CTY_ADDR";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "ST_CD";
        sortConditionArray2[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        Object[] sortConditionArray3 = new Object[IDX_2];
        sortConditionArray3[IDX_0] = "POST_CD";
        sortConditionArray3[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray3);

        Object[] sortConditionArray4 = new Object[IDX_2];
        sortConditionArray4[IDX_0] = "CNTY_NM";
        sortConditionArray4[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray4);

        params[IDX_5] = sortConditionList;
        params[IDX_6] = scrnMsg.P;

        return params;
    }
    
    /**QC#16593 Add.
     * checkTelFormat
     * @param item EZDBStringItem
     * @return boolean
     */
    private static boolean checkTelFormat(EZDBStringItem item) {

        if (ZYPCommonFunc.hasValue(item)) {
            String telNum = item.getValue();
            if (telNum.length() < 7 || telNum.length() > 20) {
                item.setErrorInfo(1, NWAM0763E);
                return false;
            } else {
                telNum = telNum.replaceAll(SLASH, HYPHEN);
                telNum = telNum.replaceAll(PERIOD, HYPHEN);
                ZYPEZDItemValueSetter.setValue(item, telNum);
                return true;
            }
        }

        return true;
    }

    // 2020/02/28 QC#55971 Add Start
    public static Object[] getParamNWAL1130ForSupplierType(NMAL6860BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "Z";
        params[1] = "Supplier Type Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  PVT.EZCANCELFLAG         AS EZCANCELFLAG ");
        sb.append(", PVT.GLBL_CMPY_CD         AS GLBL_CMPY_CD ");
        sb.append(", PVT.PRNT_VND_TP_CD       AS PRNT_VND_TP_CD ");
        sb.append(", PVT.PRNT_VND_TP_DESC_TXT AS PRNT_VND_TP_DESC_TXT ");
        sb.append(", PVT.PRNT_VND_TP_SORT_NUM AS PRNT_VND_TP_SORT_NUM ");
        sb.append("FROM ");
        sb.append("  PRNT_VND_TP PVT ");
        sb.append("WHERE ");
        sb.append("    PVT.EZCANCELFLAG   = '0' ");
        sb.append("AND PVT.GLBL_CMPY_CD   = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Supplier Type Code";
        whereArray0[1] = "UPPER(PRNT_VND_TP_CD)";
        whereArray0[2] = scrnMsg.prntVndTpCd.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Supplier Type Name";
        whereArray1[1] = "UPPER(PRNT_VND_TP_DESC_TXT)";
        whereArray1[2] = scrnMsg.prntVndTpDescTxt.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Supplier Type Code";
        columnArray0[1] = "PRNT_VND_TP_CD";
        columnArray0[2] = BigDecimal.valueOf(15);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Supplier Type Name";
        columnArray1[1] = "PRNT_VND_TP_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "PRNT_VND_TP_SORT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }
    // 2020/02/28 QC#55971 Add End
}
