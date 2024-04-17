package business.servlet.NPAL1090.common;

import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_DELETE_SEARCH;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_NEW;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_SAVE_SEARCH;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BTN_SEARCH;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DISP_HRCH_ACCT_CD_SHIP;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DTL_CD_LB_NM_FOR_CARR;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DTL_CD_LB_NM_FOR_TECH;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DTL_NM_LB_NM_FOR_CARR;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.DTL_NM_LB_NM_FOR_TECH;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.FUNC_EDIT;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.HDR_CD_LB_NM_FOR_CARR;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.HDR_CD_LB_NM_FOR_TECH;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.HDR_NM_LB_NM_FOR_CARR;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.HDR_NM_LB_NM_FOR_TECH;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.LIKE_SEARCH_CHAR;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.LOC_ROLE_TP_CDLIST;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.SCREEN_ID;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.SCR_NM_FOR_CARR;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.SCR_NM_FOR_TECH;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.TBL_CD_COL_NM_FOR_CARR;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.TBL_CD_COL_NM_FOR_TECH;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.TBL_NM_COL_NM_FOR_CARR;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.TBL_NM_COL_NM_FOR_TECH;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.TBL_NM_FOR_CARR;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.TBL_NM_FOR_TECH;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.TBL_SORT_COL_NM_FOR_CARR;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.TBL_SORT_COL_NM_FOR_TECH;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1090.NPAL1090BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura       Create          N/A
 * 03/08/2016   CITS       Takeshi Tokutomi     Update          QC#4275
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 * 09/26/2019   Fujitsu         T.Ogura         Update          QC#52362
 *</pre>
 */
public class NPAL1090CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1090BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1090BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Search Option
        scrnMsg.srchOptPk_CD.setInputProtected(false);
        scrnMsg.srchOptNm_DI.setInputProtected(false);
        scrnMsg.srchOptNm_TX.setInputProtected(false);

        // Search Condition
        scrnMsg.prchReqNum_H1.setInputProtected(false);
        scrnMsg.prchReqTpCd_SE.setInputProtected(false);
        scrnMsg.prchReqStsCd_SE.setInputProtected(false);
        scrnMsg.prchReqApvlStsCd_SE.setInputProtected(false);
        scrnMsg.prchReqCratDt_HF.setInputProtected(false);
        scrnMsg.prchReqCratDt_HT.setInputProtected(false);
        scrnMsg.prchReqSrcTpCd_SE.setInputProtected(false);
        scrnMsg.fsrNum_H1.setInputProtected(false);
        scrnMsg.svcTaskNum_H1.setInputProtected(false);
        scrnMsg.svcMachSerNum_H1.setInputProtected(false);
        scrnMsg.rqstRcvDt_HF.setInputProtected(false);
        scrnMsg.rqstRcvDt_HT.setInputProtected(false);
        scrnMsg.techNm_H1.setInputProtected(false);
        scrnMsg.rtlWhCd_H1.setInputProtected(false);
        scrnMsg.rtlSwhCd_H1.setInputProtected(false);
        scrnMsg.shipToCustCd_H1.setInputProtected(false);
        scrnMsg.locNm_H1.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_SE.setInputProtected(false);
        scrnMsg.carrNm_H1.setInputProtected(false);

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // button activation
        handler.setButtonEnabled(BTN_SAVE_SEARCH, true);
        handler.setButtonEnabled(BTN_DELETE_SEARCH, true);
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_NEW, false);

        // common button protection
        // 0 : inactive
        // 1 : active
        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        }
        // QC#16256 add end

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);
    }

    /**
     * Table Column Protect
     * @param scrnMsg NPAL1090BMsg
     */
    public static void setTableScreen(NPAL1090BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prchReqStsDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqApvlStsDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqSrcTpDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).fullPsnNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).shpgSvcLvlDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).carrNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).techNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).locNm_D1.setInputProtected(true);
        }
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1090BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NPAL1090BMsg scrnMsg, List<String> functionList) {

        if (hasRegisterAuthority(functionList)) {
            handler.setButtonEnabled(BTN_NEW, true);
        }
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    private static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            if (FUNC_EDIT.equals(function)) {
                return true;
            }
        }
        return false;
    }

    /**
     * clear table
     * @param scrnMsg NPAL1090BMsg
     */
    public static void clearTable(NPAL1090BMsg scrnMsg) {
        scrnMsg.A.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1090BMsg
     * @return Object[]
     */
    public static Object[] setParamForCarrierPopup(NPAL1090BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.xxCondCd_P1.clear();
        scrnMsg.xxCondNm_P1.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_CARR);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondCd_P1,
        // scrnMsg.carrCd_AC);
        if (ZYPCommonFunc.hasValue(scrnMsg.carrNm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondNm_P1, LIKE_SEARCH_CHAR + scrnMsg.carrNm_H1.getValue() + LIKE_SEARCH_CHAR);
        }

        int paramCount = 0;
        Object[] params = new Object[11];
        params[paramCount++] = scrnMsg.xxTblNm_P1;
        params[paramCount++] = scrnMsg.xxTblCdColNm_P1;
        params[paramCount++] = scrnMsg.xxTblNmColNm_P1;
        params[paramCount++] = scrnMsg.xxTblSortColNm_P1;
        params[paramCount++] = scrnMsg.xxScrNm_P1;
        params[paramCount++] = scrnMsg.xxHdrCdLbNm_P1;
        params[paramCount++] = scrnMsg.xxHdrNmLbNm_P1;
        params[paramCount++] = scrnMsg.xxDtlCdLbNm_P1;
        params[paramCount++] = scrnMsg.xxDtlNmLbNm_P1;
        params[paramCount++] = scrnMsg.xxCondCd_P1;
        params[paramCount++] = scrnMsg.xxCondNm_P1;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1090BMsg
     * @return Object[]
     */
    public static Object[] setParamForTechnicianPopup(NPAL1090BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.xxCondCd_P1.clear();
        scrnMsg.xxCondNm_P1.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_TECH);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondCd_P1,
        // scrnMsg.rqstTechTocCd_AC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondNm_P1, scrnMsg.techNm_H1);

        int paramCount = 0;
        Object[] params = new Object[11];
        params[paramCount++] = scrnMsg.xxTblNm_P1;
        params[paramCount++] = scrnMsg.xxTblCdColNm_P1;
        params[paramCount++] = scrnMsg.xxTblNmColNm_P1;
        params[paramCount++] = scrnMsg.xxTblSortColNm_P1;
        params[paramCount++] = scrnMsg.xxScrNm_P1;
        params[paramCount++] = scrnMsg.xxHdrCdLbNm_P1;
        params[paramCount++] = scrnMsg.xxHdrNmLbNm_P1;
        params[paramCount++] = scrnMsg.xxDtlCdLbNm_P1;
        params[paramCount++] = scrnMsg.xxDtlNmLbNm_P1;
        params[paramCount++] = scrnMsg.xxCondCd_P1;
        params[paramCount++] = scrnMsg.xxCondNm_P1;

        return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1090BMsg
     * @return LocationPopup Param (NPAL1010) Object[]
     */
    public static Object[] setParamForLocationPopup(NPAL1090BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, LOC_ROLE_TP_CDLIST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.rtlWhNm_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.rtlSwhCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.rtlSwhNm_H1);

        int paramCount = 0;
        Object[] params = new Object[11];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1090BMsg
     * @return ShipToCustPopup Param (NMAL6010) Object[]
     */
    public static Object[] setParamForShipToCustPopup(NPAL1090BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.locNm_H1)) {
            // START 2019/09/26 T.Ogura [QC#52362,MOD]
//            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm_P3, scrnMsg.locNm_H1.getValue() + LIKE_SEARCH_CHAR);
            String shipToCustLocNm = scrnMsg.locNm_H1.getValue();
            if (shipToCustLocNm.length() == 60) {
                shipToCustLocNm = shipToCustLocNm.substring(0, 59) + "%";
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm_P3, shipToCustLocNm);
            // END   2019/09/26 T.Ogura [QC#52362,MOD]
        }

        scrnMsg.O.no(0).xxPopPrm_O1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(1).xxPopPrm_O1, scrnMsg.shipToLocNm_P3);
        scrnMsg.O.no(2).xxPopPrm_O1.clear();
        scrnMsg.O.no(3).xxPopPrm_O1.clear();
        scrnMsg.O.no(4).xxPopPrm_O1.clear();
        scrnMsg.O.no(5).xxPopPrm_O1.clear();
        scrnMsg.O.no(6).xxPopPrm_O1.clear();
        scrnMsg.O.no(7).xxPopPrm_O1.clear();
        scrnMsg.O.no(8).xxPopPrm_O1.clear();
        scrnMsg.O.no(9).xxPopPrm_O1.clear();
        scrnMsg.O.no(10).xxPopPrm_O1.clear();
        scrnMsg.O.no(11).xxPopPrm_O1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(12).xxPopPrm_O1, DISP_HRCH_ACCT_CD_SHIP);
        scrnMsg.O.no(13).xxPopPrm_O1.clear();
        scrnMsg.O.no(14).xxPopPrm_O1.clear();
        scrnMsg.O.no(15).xxPopPrm_O1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(16).xxPopPrm_O1, scrnMsg.shipToCustCd_H1);
        scrnMsg.O.no(17).xxPopPrm_O1.clear();
        scrnMsg.O.no(18).xxPopPrm_O1.clear();
        scrnMsg.O.no(19).xxPopPrm_O1.clear();
        scrnMsg.O.no(20).xxPopPrm_O1.clear();
        scrnMsg.O.no(21).xxPopPrm_O1.clear();
        scrnMsg.O.no(22).xxPopPrm_O1.clear();
        scrnMsg.O.no(23).xxPopPrm_O1.clear();

        scrnMsg.O.setValidCount(24);

        Object[] params = new Object[24];
        
        for (int i = 0; i < scrnMsg.O.getValidCount() && i < params.length; i++) {
            params[i] = scrnMsg.O.no(i).xxPopPrm_O1;
        }

        return params;
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        return functionList.contains(FUNC_EDIT);
    }
    // QC#16256 add end

}
