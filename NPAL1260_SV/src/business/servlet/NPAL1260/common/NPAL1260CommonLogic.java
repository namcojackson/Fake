package business.servlet.NPAL1260.common;

import static business.servlet.NPAL1260.constant.NPAL1260Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.LIKE_SEARCH_CHAR;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.OPEN_WIN_SUPPLIER_SQL;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.OPEN_WIN_SUPPLIER_SQL_TECH;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.SCREEN_ID;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.DISP_HRCH_ACCT_CD_SHIP;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1260.NPAL1260BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request Inquiry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/02/2015   CITS       Yasushi Nomura        Create          N/A
 * 03/08/2016   CITS       Takeshi Tokutomi      Update          QC#4275
 * 03/08/2018   CITS       Takeshi Tokutomi      Update          QC#21913
 * 03/26/2018   CITS            S.Katsuma        Update          QC#25055
 * 2018/08/17   CITS       K.Ogino               Update          QC#27601
 * 2019/01/10   Fujitsu         S.Takami        Update          QC#29890
 * 03/17/2020   Fujitsu         R.Nakamura      Update          QC#56104
 *</pre>
 */
public class NPAL1260CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1260BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1260BMsg scrnMsg, List<String> functionList) {

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        // column input protection
        // true : block entry
        // false : input possible
        // Search Option
        scrnMsg.srchOptPk_CD.setInputProtected(false);
        scrnMsg.srchOptNm_DI.setInputProtected(false);
        scrnMsg.srchOptNm_TX.setInputProtected(false);

        // Search Condition
        // Tech Request#
        scrnMsg.prchReqNum_H1.setInputProtected(false);
        // Tech Request Type
        scrnMsg.prchReqTpCd_SE.setInputProtected(false);
        // Item Number
        scrnMsg.mdseCd_H1.setInputProtected(false);
        // Line Type
        scrnMsg.prchReqLineTpCd_SE.setInputProtected(false);
        // Line Status
        scrnMsg.prchReqLineStsCd_SE.setInputProtected(false);
        // Date Created From/To
        scrnMsg.prchReqCratDt_HF.setInputProtected(false);
        scrnMsg.prchReqCratDt_HT.setInputProtected(false);
        // Date Needed From/To
        scrnMsg.rqstRcvDt_HF.setInputProtected(false);
        scrnMsg.rqstRcvDt_HT.setInputProtected(false);
        // Ship Date From/To
        scrnMsg.shipDt_HF.setInputProtected(false);
        scrnMsg.shipDt_HT.setInputProtected(false);
        // Tech Territory
        scrnMsg.lineBizTpCd_SE.setInputProtected(false);
        // Document Source Type
        scrnMsg.prchReqSrcTpCd_SE.setInputProtected(false);
        // Service Request#
        scrnMsg.fsrNum_H1.setInputProtected(false);
        // Service Request Task#
        scrnMsg.svcTaskNum_H1.setInputProtected(false);
        // Service Request Serial#
        scrnMsg.svcMachSerNum_H1.setInputProtected(false);
        // Purchase Order#
        scrnMsg.poOrdNum_H1.setInputProtected(false);
        // Shipping Order#
        // Mod Start 2020/03/17 QC#56104
//        scrnMsg.soNum_H1.setInputProtected(false);
        scrnMsg.rwsRefNum_H1.setInputProtected(false);
        // Mod End 2020/03/17 QC#56104
        // Requisition#
        scrnMsg.prchReqNum_H2.setInputProtected(false);
        // Source WH / SWH
        scrnMsg.rtlWhCd_H1.setInputProtected(false);
        scrnMsg.rtlSwhCd_H1.setInputProtected(false);
        // PO Supplier / Site Name
        scrnMsg.prntVndNm_H1.setInputProtected(false);
        scrnMsg.locNm_H1.setInputProtected(false);
        // Destination WH / SWH
        scrnMsg.destRtlWhCd_H1.setInputProtected(false);
        scrnMsg.destRtlSwhCd_H1.setInputProtected(false);
        // Technician Name
        scrnMsg.techNm_H1.setInputProtected(false);
        // Customer Name
        scrnMsg.dsAcctNm_H1.setInputProtected(false);
        // Requested Carrier
        scrnMsg.carrNm_H1.setInputProtected(false);
        // Actual Service Level
        scrnMsg.shpgSvcLvlCd_SE.setInputProtected(false);
        // Tracking Number
        scrnMsg.proNum_H1.setInputProtected(false);
        // Shipment Line View
        scrnMsg.xxChkBox_H1.setInputProtected(false);
        // Freeze Flag
        scrnMsg.xxChkBox_H2.setInputProtected(false);
        // Insourced Flag
        scrnMsg.xxChkBox_H3.setInputProtected(false);
        // Purchased Flag
        scrnMsg.xxChkBox_H4.setInputProtected(false);

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // button activation
        handler.setButtonEnabled("SaveSearch", true);
        handler.setButtonEnabled("DeleteSearch", true);
        handler.setButtonEnabled("Search", true);
        handler.setButtonEnabled("New", false);

        // common button protection
        // 0 : inactive
        // 1 : active
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

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);
    }

    /**
     * Table Column Protect
     * @param scrnMsg NPAL1260BMsg
     */
    public static void setTableScreen(NPAL1260BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        String trackBtnNm = "OpenWin_Tracking";
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).prchReqTpDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLineTpDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).procrTpDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_D2.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_D2.setInputProtected(true);
            scrnMsg.A.no(i).prchReqSrcTpDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).prntVndNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).locNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).techNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).carrNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).shpgSvcLvlNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).proNum_D1.setInputProtected(true);
            // QC#27601
            scrnMsg.A.no(i).prchReqLineStsDescTxt_D1.setInputProtected(true);

            // QC#21913
            EZDGUIAttribute trackBtn = new EZDGUIAttribute(SCREEN_ID, trackBtnNm + i);
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).vndSoNum_D1)) {
                trackBtn.setVisibility(false);
            // START 2018/03/26 S.Katsuma [QC#25055,ADD]
            } else {
                trackBtn.setVisibility(true);
            }
            scrnMsg.addGUIAttribute(trackBtn);
            // END 2018/03/26 S.Katsuma [QC#25055,ADD]
        }

        // START 2018/03/26 S.Katsuma [QC#25055,ADD]
        if (scrnMsg.A.getValidCount() < scrnMsg.A.length()) {
            for (int i = scrnMsg.A.getValidCount(); i < scrnMsg.A.length(); i++) {
                scrnMsg.clearGUIAttribute(SCREEN_ID, trackBtnNm + i);
            }
        }
        // END 2018/03/26 S.Katsuma [QC#25055,ADD]
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1260BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NPAL1260BMsg scrnMsg, List<String> functionList) {

        if (hasRegisterAuthority(functionList)) {
            handler.setButtonEnabled("New", true);
        }
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    private static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            if ("NPAL1260T020".equals(function)) {
                return true;
            }
        }
        return false;
    }

    /**
     * clear table
     * @param scrnMsg NPAL1260BMsg
     */
    public static void clearTable(NPAL1260BMsg scrnMsg) {
        scrnMsg.A.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1260BMsg
     * @param srcFlag boolean
     * @return LocationPopup Param (NPAL1010) Object[]
     */
    public static Object[] setParamForLocationPopup(NPAL1260BMsg scrnMsg, boolean srcFlag) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();

        if (srcFlag) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.rtlWhCd_H1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.rtlSwhCd_H1);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.destRtlWhCd_H1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.destRtlSwhCd_H1);
        }

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;
        params[7] = scrnMsg.xxPopPrm_P7;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.xxPopPrm_P9;
        params[10] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     * Set Supplier Popup param
     * @param scrnMsg NPAL1260BMsg
     * @return SupplierPopup Param (NWAL1130) Object[]
     */
    public static Object[] setParamForSupplierPopup(NPAL1260BMsg scrnMsg) {
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxUpldCsvTempDirTxt_PX.clear();
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.Q);
        ZYPTableUtil.clear(scrnMsg.R);
        ZYPTableUtil.clear(scrnMsg.S);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, "Supplier/Site Search");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxUpldCsvTempDirTxt_PX, OPEN_WIN_SUPPLIER_SQL);

        // Where list
        int pi = 0;

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrCondLbTxt_H, "Supplier Site Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryFltrTxt_H, "VND_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryLikeFlg_H, ZYPConstant.FLG_ON_Y);
        pi++;

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrCondLbTxt_H, "ARCS SupplierSiteCd");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryFltrTxt_H, "ARCS_SPLY_SITE_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryLikeFlg_H, ZYPConstant.FLG_ON_Y);
        pi++;

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrCondLbTxt_H, "Supplier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryFltrTxt_H, "PRNT_VND_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryLikeFlg_H, ZYPConstant.FLG_ON_Y);
        pi++;

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrCondLbTxt_H, "Supplier Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryFltrTxt_H, "PRNT_VND_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryLikeFlg_H, ZYPConstant.FLG_ON_Y);
        pi++;

        scrnMsg.P.setValidCount(pi);

        // Column list
        int qi = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLbTxt_C, "Supplier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrQueryColNm_C, "PRNT_VND_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLg_C, BigDecimal.valueOf(10));
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxSelFlg_C, ZYPConstant.FLG_ON_Y);
        qi++;

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLbTxt_C, "Supplier Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrQueryColNm_C, "PRNT_VND_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLg_C, BigDecimal.valueOf(30));
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxSelFlg_C, ZYPConstant.FLG_ON_Y);
        qi++;

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLbTxt_C, "Supplier Site Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrQueryColNm_C, "VND_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLg_C, BigDecimal.valueOf(15));
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxSelFlg_C, ZYPConstant.FLG_OFF_N);
        qi++;

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLbTxt_C, "Supplier Site Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrQueryColNm_C, "LOC_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLg_C, BigDecimal.valueOf(30));
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxSelFlg_C, ZYPConstant.FLG_OFF_N);
        qi++;

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLbTxt_C, "ARCS Supplier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrQueryColNm_C, "ARCS_SPLY_SITE_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLg_C, BigDecimal.valueOf(15));
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxSelFlg_C, ZYPConstant.FLG_OFF_N);
        qi++;

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLbTxt_C, "Supplier Site Inactive Date");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrQueryColNm_C, "INAC_DT");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLg_C, BigDecimal.valueOf(15));
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxSelFlg_C, ZYPConstant.FLG_OFF_N);
        qi++;

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLbTxt_C, "Supplier Type Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrQueryColNm_C, "SPLY_TP_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLg_C, BigDecimal.valueOf(12));
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxSelFlg_C, ZYPConstant.FLG_OFF_N);
        qi++;

        scrnMsg.Q.setValidCount(qi);

        // Sort list
        ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(0).xxTblSortColNm_S, "PRNT_VND_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(0).xxSortOrdByTxt_S, "ASC");

        ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(1).xxTblSortColNm_S, "VND_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(1).xxSortOrdByTxt_S, "ASC");
        scrnMsg.S.setValidCount(2);

        // Result Placeholder
        scrnMsg.R.setValidCount(0);

        int cnt = 0;
        Object[] params = new Object[7];
        params[cnt++] = "";
        params[cnt++] = scrnMsg.xxPopPrm_P2;
        params[cnt++] = scrnMsg.xxUpldCsvTempDirTxt_PX;
        params[cnt++] = scrnMsg.P;
        params[cnt++] = scrnMsg.Q;
        params[cnt++] = scrnMsg.S;
        params[cnt++] = scrnMsg.R;

        return params;
    }

    /**
     * Set Technician Popup param
     * @param scrnMsg NPAL1260BMsg
     * @return TechnicianPopup Param (NWAL1130) Object[]
     */
    public static Object[] setParamForTechnicianPopup(NPAL1260BMsg scrnMsg) {
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxUpldCsvTempDirTxt_PX.clear();
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.Q);
        ZYPTableUtil.clear(scrnMsg.R);
        ZYPTableUtil.clear(scrnMsg.S);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, "Technician Search");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxUpldCsvTempDirTxt_PX, OPEN_WIN_SUPPLIER_SQL_TECH);

        // Where list
        int pi = 0;

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrCondLbTxt_H, "Technician Code");
        // 2019/01/10 QC#29890 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryFltrTxt_H, "PSN_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryFltrTxt_H, "UPPER(TECH_TOC_CD)");
        // 2019/01/10 QC#29890 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryLikeFlg_H, ZYPConstant.FLG_ON_Y);
        pi++;

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrCondLbTxt_H, "Technician Name");
        // 2019/01/10 QC#29890 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryFltrTxt_H, "FULL_PSN_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryFltrTxt_H, "UPPER(TECH_NM)");
        // 2019/01/10 QC#29890 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrCondValTxt_H, scrnMsg.techNm_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(pi).xxComnScrQueryLikeFlg_H, ZYPConstant.FLG_ON_Y);
        pi++;

        scrnMsg.P.setValidCount(pi);

        // Column list
        int qi = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLbTxt_C, "Technician Code");
        // 2019/01/10 QC#29890 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrQueryColNm_C, "PSN_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrQueryColNm_C, "TECH_TOC_CD");
        // 2019/01/10 QC#29890 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLg_C, BigDecimal.valueOf(20));
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxSelFlg_C, ZYPConstant.FLG_ON_Y);
        qi++;

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLbTxt_C, "Technician Name");
        // 2019/01/10 QC#29890 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrQueryColNm_C, "FULL_PSN_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrQueryColNm_C, "TECH_NM");
        // 2019/01/10 QC#29890 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxComnScrColLg_C, BigDecimal.valueOf(60));
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(qi).xxSelFlg_C, ZYPConstant.FLG_OFF_N);
        qi++;

        scrnMsg.Q.setValidCount(qi);

        // Sort list
        // 2019/01/10 QC#29890 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(0).xxTblSortColNm_S, "PSN_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(0).xxTblSortColNm_S, "TECH_TOC_CD");
        // 2019/01/10 QC#29890 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(0).xxSortOrdByTxt_S, "ASC");
        scrnMsg.S.setValidCount(1);

        // Result Placeholder
        scrnMsg.R.setValidCount(0);

        int cnt = 0;
        Object[] params = new Object[7];
        params[cnt++] = "";
        params[cnt++] = scrnMsg.xxPopPrm_P2;
        params[cnt++] = scrnMsg.xxUpldCsvTempDirTxt_PX;
        params[cnt++] = scrnMsg.P;
        params[cnt++] = scrnMsg.Q;
        params[cnt++] = scrnMsg.S;
        params[cnt++] = scrnMsg.R;

        return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1260BMsg
     * @return ShipToCustPopup Param (NMAL6010) Object[]
     */
    public static Object[] setParamForShipToCustPopup(NPAL1260BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm_P1, LIKE_SEARCH_CHAR + scrnMsg.dsAcctNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
        }

        scrnMsg.O.no(0).xxPopPrm_O1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(1).xxPopPrm_O1, scrnMsg.shipToLocNm_P1);
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
        ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(16).xxPopPrm_O1, scrnMsg.shipToCustCd_P1);
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

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1260BMsg
     * @return Object[]
     */
    public static Object[] setParamForCarrierPopup(NPAL1260BMsg scrnMsg) {

        scrnMsg.xxTblNm_P2.clear();
        scrnMsg.xxTblCdColNm_P2.clear();
        scrnMsg.xxTblNmColNm_P2.clear();
        scrnMsg.xxTblSortColNm_P2.clear();
        scrnMsg.xxScrNm_P2.clear();
        scrnMsg.xxHdrCdLbNm_P2.clear();
        scrnMsg.xxHdrNmLbNm_P2.clear();
        scrnMsg.xxDtlCdLbNm_P2.clear();
        scrnMsg.xxDtlNmLbNm_P2.clear();
        scrnMsg.xxCondCd_P2.clear();
        scrnMsg.xxCondNm_P2.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P2, "OTBD_CARR_V");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P2, "CARR_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P2, "CARR_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P2, "CARR_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P2, "Carrier Search Popup");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P2, "Carrier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P2, "Carrier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P2, "Carrier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P2, "Carrier Name");
        if (ZYPCommonFunc.hasValue(scrnMsg.carrNm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondNm_P2, LIKE_SEARCH_CHAR + scrnMsg.carrNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
        }

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P2;
        params[1] = scrnMsg.xxTblCdColNm_P2;
        params[2] = scrnMsg.xxTblNmColNm_P2;
        params[3] = scrnMsg.xxTblSortColNm_P2;
        params[4] = scrnMsg.xxScrNm_P2;
        params[5] = scrnMsg.xxHdrCdLbNm_P2;
        params[6] = scrnMsg.xxHdrNmLbNm_P2;
        params[7] = scrnMsg.xxDtlCdLbNm_P2;
        params[8] = scrnMsg.xxDtlNmLbNm_P2;
        params[9] = scrnMsg.xxCondCd_P2;
        params[10] = scrnMsg.xxCondNm_P2;

        return params;
    }
}
