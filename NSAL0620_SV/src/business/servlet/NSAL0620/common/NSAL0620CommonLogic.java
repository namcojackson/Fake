/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620.common;

import static business.servlet.NSAL0620.constant.NSAL0620Constant.*;
import static business.servlet.NSAL0620.constant.NSAL0620Constant.CHK_BOX.XX_CHK_BOX_A;
import static business.servlet.NSAL0620.constant.NSAL0620Constant.CHK_BOX.XX_CHK_BOX_B;
import static business.servlet.NSAL0620.constant.NSAL0620Constant.CHK_BOX.XX_CHK_BOX_C;
import static business.servlet.NSAL0620.constant.NSAL0620Constant.CHK_BOX.XX_CHK_BOX_D;
import static business.servlet.NSAL0620.constant.NSAL0620Constant.CHK_BOX.XX_CHK_BOX_E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0620.NSAL0620BMsg;
import business.servlet.NSAL0620.NSAL0620_ABMsg;
import business.servlet.NSAL0620.NSAL0620_BBMsg;
import business.servlet.NSAL0620.NSAL0620_CBMsg;
import business.servlet.NSAL0620.NSAL0620_DBMsg;
import business.servlet.NSAL0620.NSAL0620_EBMsg;
import business.servlet.NSAL0620.constant.NSAL0620Constant.BTN_LBL;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/02/18   Hitachi         T.Aoyagi        Update          QC3434
 * 2016/03/25   Hitachi         M.Gotou         Update          QC4595
 * 2016/04/25   Hitachi         M.Gotou         Update          QC4326
 * 2016/05/09   Hitachi         M.Gotou         Update          QC4093
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2016/09/23   Hitachi         Y.Zhang         Update          QC#12582
 * 2017/02/07   Hitachi         K.Kojima        Update          QC#17303
 * 2017/08/21   Hitachi         E.Kameishi      Update          QC#8661
 * 2018/02/06   Hitachi         M.Kidokoro      Update          QC#23375
 * 2018/06/05   Fujitsu         T.Ogura         Update          QC#21159
 * 2018/06/26   Fujitsu         T.Ogura         Update          QC#26336
 * 2018/07/18   CITS            T.Wada          Update          QC#26424
 * 2018/09/05   Hitachi         K.Kojima        Update          QC#28061
 * 2018/11/05   Hitachi         K.Fujimoto      Update          QC#28627
 * 2018/12/25   Hitachi         K.Morita        Update          QC#28749
 * 2019/09/02   Hitachi         T.Aoyagi        Update          QC#53005
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2022/10/13   Hitachi         M.Komatsu       Update          QC#60078,60537
 *</pre>
 */
public class NSAL0620CommonLogic {

    /**
     * addCheckItem
     * @param scrnMsg NSAL0620BMsg
     */
    public static void addCheckItem(NSAL0620BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.dsContrNum_H);
        scrnMsg.addCheckItem(scrnMsg.dsContrCratDt_H1);
        scrnMsg.addCheckItem(scrnMsg.dsContrCratDt_H2);
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt_H2);
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        // mod start 2016/04/25 CSA Defect#4326
        scrnMsg.addCheckItem(scrnMsg.dsContrRptGrpNum_H);
        // mod end 2016/04/25 CSA Defect#4326
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt_H3);
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.addCheckItem(scrnMsg.svcContrRefCmntTxt_H);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_LK);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H);
        scrnMsg.addCheckItem(scrnMsg.xxGenlFldAreaTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H1);
        // add start 2016/07/01 CSA Defect#11261
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_H);
        // add end 2016/07/01 CSA Defect#11261
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H3);
        scrnMsg.addCheckItem(scrnMsg.serNum_H);
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt_H4);
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        // START 2022/10/13 M.Komatsu [QC#60537,ADD]
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H);
        // END 2022/10/13 M.Komatsu [QC#60537,ADD]
        scrnMsg.addCheckItem(scrnMsg.t_MdlNm_H);
        // START 2019/08/30 [QC#53005,ADD]
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk_H);
        // END 2019/08/30 [QC#53005,ADD]
        // mod start 2016/04/25 CSA Defect#4326
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H2);
        // mod end 2016/04/25 CSA Defect#4326
        scrnMsg.addCheckItem(scrnMsg.xxGenlFldAreaTxt_H2);
        scrnMsg.addCheckItem(scrnMsg.locNum_H);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_H);
        scrnMsg.addCheckItem(scrnMsg.xxThruDt_H);
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt_H5);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt_H6);
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
    }

    /**
     * Clear Data
     * @param scrnMsg NSAL0620BMsg
     */
    public static void clearData(NSAL0620BMsg scrnMsg) {
        setValue(scrnMsg.invSeptBaseUsgFlg_H, ZYPConstant.FLG_OFF_N);
        setValue(scrnMsg.xxChkBox_H1, ZYPConstant.FLG_OFF_N);
        setValue(scrnMsg.baseChrgToLeaseCmpyFlg_H, ZYPConstant.FLG_OFF_N);
        setValue(scrnMsg.usgChrgToLeaseCmpyFlg_H, ZYPConstant.FLG_OFF_N);
        setValue(scrnMsg.xxChkBox_H2, ZYPConstant.FLG_OFF_N);
        // START 2018/02/06 M.Kidokoro [QC#23375,ADD]
        setValue(scrnMsg.xxChkBox_H3, ZYPConstant.FLG_OFF_N);
        // END 2018/02/06 M.Kidokoro [QC#23375,ADD]
        ZYPTableUtil.unSelectAll(scrnMsg.B, XX_CHK_BOX_B.getChkBox());
        ZYPTableUtil.unSelectAll(scrnMsg.C, XX_CHK_BOX_C.getChkBox());
        ZYPTableUtil.unSelectAll(scrnMsg.D, XX_CHK_BOX_D.getChkBox());
        ZYPTableUtil.unSelectAll(scrnMsg.E, XX_CHK_BOX_E.getChkBox());
    }
    // ************* For Popup *************
    /**
     * Check lastGuard is 'CLOSE' event.
     * @param lastGuard String
     * @return If lastGuard is 'CLOSE' event.
     */
    public static boolean isClosedEvent(String lastGuard) {
        return BTN_LBL.CLOSE.getGuardNm().toLowerCase().equals(lastGuard.toLowerCase());
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0620BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0620BMsg scrnMsg) {
        initCommonButton(handler);
        controlScreenDetail(handler, scrnMsg);
        controlScreenBottom(handler, scrnMsg, false);
        controlScreenHeader(handler, scrnMsg);
        clearData(scrnMsg);
    }

    /**
     * <pre>
     * The Search Event of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0620BMsg
     */
    public static final void srchControlScreen(EZDCommonHandler handler, NSAL0620BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetail(handler, scrnMsg);
            controlScreenBottom(handler, scrnMsg, true);
        }
    }

    /**
     * Control screen header
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0620BMsg
     */
    private static final void controlScreenHeader(EZDCommonHandler handler, NSAL0620BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.B.getValidCount() > 0) {
            setRowColors(scrnMsg, "B");
        }
        if (scrnMsg.C.getValidCount() > 0) {
            setRowColors(scrnMsg, "C");
        }
        if (scrnMsg.D.getValidCount() > 0) {
            setRowColors(scrnMsg, "D");
        }
        if (scrnMsg.E.getValidCount() > 0) {
            setRowColors(scrnMsg, "E");
        }
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0620BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0620BMsg scrnMsg) {

        scrnMsg.dsContrNum_H.setInputProtected(false);
        scrnMsg.dsContrCratDt_H1.setInputProtected(false);
        scrnMsg.dsContrCratDt_H2.setInputProtected(false);
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.xxComnScrColValTxt_H1.setInputProtected(false);
        scrnMsg.xxComnScrColValTxt_H2.setInputProtected(false);
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        // mod start 2016/04/25 CSA Defect#4326
        scrnMsg.dsContrRptGrpNum_H.setInputProtected(false);
        // mod end 2016/04/25 CSA Defect#4326
        scrnMsg.svcContrRefCmntTxt_H.setInputProtected(false);
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.xxComnScrColValTxt_H3.setInputProtected(false);
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.dsAcctNm_H.setInputProtected(false);
        scrnMsg.dsAcctNm_LK.setInputProtected(false);
        scrnMsg.dsAcctNum_H.setInputProtected(false);
        scrnMsg.xxGenlFldAreaTxt_H1.setInputProtected(false);
        scrnMsg.billToCustCd_H1.setInputProtected(false);
        // add start 2016/07/01 CSA Defect#11261
        scrnMsg.mdseDescShortTxt_H.setInputProtected(false);
        // add end 2016/07/01 CSA Defect#11261
        scrnMsg.dsAcctNm_H3.setInputProtected(false);
        scrnMsg.serNum_H.setInputProtected(false);
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.xxComnScrColValTxt_H4.setInputProtected(false);
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        // START 2022/10/13 M.Komatsu [QC#60537,ADD]
        scrnMsg.mdseCd_H.setInputProtected(false);
        // END 2022/10/13 M.Komatsu [QC#60537,ADD]
        scrnMsg.t_MdlNm_H.setInputProtected(false);
        // START 2019/08/30 [QC#53005,ADD]
        scrnMsg.svcConfigMstrPk_H.setInputProtected(false);
        // END 2019/08/30 [QC#53005,ADD]
        // mod start 2016/04/25 CSA Defect#4326
        scrnMsg.dsAcctNm_H2.setInputProtected(false);
        // mod end 2016/04/25 CSA Defect#4326
        scrnMsg.xxGenlFldAreaTxt_H2.setInputProtected(false);
        scrnMsg.locNum_H.setInputProtected(false);
        scrnMsg.xxFromDt_H.setInputProtected(false);
        scrnMsg.xxThruDt_H.setInputProtected(false);
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.xxComnScrColValTxt_H5.setInputProtected(false);
        scrnMsg.xxComnScrColValTxt_H6.setInputProtected(false);
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.srchOptNm_S.setInputProtected(false);
        scrnMsg.invSeptBaseUsgFlg_H.setInputProtected(false);
        scrnMsg.xxChkBox_H1.setInputProtected(false);
        scrnMsg.baseChrgToLeaseCmpyFlg_H.setInputProtected(false);
        scrnMsg.usgChrgToLeaseCmpyFlg_H.setInputProtected(false);
        scrnMsg.xxChkBox_H2.setInputProtected(false);
        // START 2018/02/06 M.Kidokoro [QC#23375,ADD]
        scrnMsg.xxChkBox_H3.setInputProtected(false);
        // END 2018/02/06 M.Kidokoro [QC#23375,ADD]

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL0620_BBMsg bbMsg = scrnMsg.B.no(i);
            bbMsg.xxChkBox_B.setInputProtected(false);
            bbMsg.svcRgNm_B.setInputProtected(true);
            bbMsg.svcRgDescTxt_B.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NSAL0620_CBMsg cbMsg = scrnMsg.C.no(i);
            cbMsg.xxChkBox_C.setInputProtected(false);
            cbMsg.svcContrBrCd_C.setInputProtected(true);
            cbMsg.svcContrBrDescTxt_C.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NSAL0620_DBMsg dbMsg = scrnMsg.D.no(i);
            dbMsg.xxChkBox_D.setInputProtected(false);
            dbMsg.mtrReadMethCd_D.setInputProtected(true);
            dbMsg.mtrReadMethNm_D.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            NSAL0620_EBMsg ebMsg = scrnMsg.E.no(i);
            ebMsg.xxChkBox_E.setInputProtected(false);
            ebMsg.mtrLbCd_E.setInputProtected(true);
            ebMsg.mtrLbNm_E.setInputProtected(true);
        }
    }

    /**
     * controlScreenDetail
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0620BMsg
     */
    private static void controlScreenDetail(EZDCommonHandler handler, NSAL0620BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            ZYPTableUtil.unSelectAll(scrnMsg.A, XX_CHK_BOX_A.getChkBox());
            setRowColors(scrnMsg, "A");
        }
    }

    /**
     * controlScreenBottom
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0620BMsg
     * @param isEnabled boolean
     */
    private static void controlScreenBottom(EZDCommonHandler handler, NSAL0620BMsg scrnMsg, boolean isEnabled) {
        handler.setButtonEnabled(BTN_LBL.SELECT_ALL_CONTR.getOrgNm(), isEnabled);
        handler.setButtonEnabled(BTN_LBL.UN_SELECT_ALL_CONTR.getOrgNm(), isEnabled);
        handler.setButtonEnabled(BTN_LBL.OPEN_WIN_PREP_MASS_UPD_SCRN.getOrgNm(), isEnabled);
        //START 2017/08/21 E.Kameishi [QC#8661,ADD]
        handler.setButtonEnabled(BTN_LBL.PRINT.getOrgNm(), isEnabled);
        //END 2017/08/21 E.Kameishi [QC#8661,ADD]
        // START 2018/06/26 T.Ogura [QC#26336,ADD]
        handler.setButtonEnabled(BTN_LBL.METER_HISTORY.getOrgNm(), isEnabled);
        // END   2018/06/26 T.Ogura [QC#26336,ADD]
        if (isEnabled) {
            scrnMsg.xxGenlFldAreaTxt_B.setInputProtected(false);
        } else {
            scrnMsg.xxGenlFldAreaTxt_B.setInputProtected(true);
        }
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0620BMsg
     */
    // mod start 2016/03/25 CSA Defect#4595
    public static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0620BMsg scrnMsg) {
        // mod end 2016/03/25 CSA Defect#4595

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0620_ABMsg abMsg = scrnMsg.A.no(i);
            // START 2016/02/18 T.Aoyagi [QC3434, MOD]
//            abMsg.xxChkBox_A.setInputProtected(false);
            if (ZYPCommonFunc.hasValue(abMsg.svcMachMstrPk_A)) {
                abMsg.xxChkBox_A.setInputProtected(false);
                handler.setButtonEnabled(BTN_LBL.COPY.getOrgNm(), i, true);
            } else {
                abMsg.xxChkBox_A.setInputProtected(true);
                handler.setButtonEnabled(BTN_LBL.COPY.getOrgNm(), i, false);
            }
            // END 2016/02/18 T.Aoyagi [QC3434, MOD]
            abMsg.dsContrNum_A.setInputProtected(true);
            // START 2018/06/05 T.Ogura [QC#21159,MOD]
//            abMsg.dsContrCatgNm_A.setInputProtected(true);
            abMsg.dsContrCatgDescTxt_A.setInputProtected(true);
            // END   2018/06/05 T.Ogura [QC#21159,MOD]
            abMsg.dsAcctNm_A.setInputProtected(true);
            abMsg.contrVrsnEffFromDt_A.setInputProtected(true);
            abMsg.contrVrsnEffThruDt_A.setInputProtected(true);
            abMsg.dsContrCtrlStsNm_A.setInputProtected(true);
            abMsg.svcRgNm_A.setInputProtected(true);
            abMsg.svcContrBrCd_A.setInputProtected(true);
            // START 2017/02/07 K.Kojima [QC#17303,DEL]
            // abMsg.serNum_A.setInputProtected(true);
            // END 2017/02/07 K.Kojima [QC#17303,DEL]
            abMsg.t_MdlNm_A.setInputProtected(true);
            abMsg.contrEffFromDt_A.setInputProtected(true);
            abMsg.contrEffThruDt_A.setInputProtected(true);
            abMsg.svcMachMstrStsNm_A.setInputProtected(true);
            abMsg.nextBllgDt_A.setInputProtected(true);

            // START 2018/07/09 T.Wada [QC#26424,ADD]
            abMsg.dsAcctNum_A.setInputProtected(true);
            abMsg.altPayerCustCd_A.setInputProtected(true);
            abMsg.billToLocNm_A.setInputProtected(true);
            abMsg.dsContrStsDescTxt_A.setInputProtected(true);
            abMsg.dsContrClsDescTxt_A.setInputProtected(true);
            abMsg.xxPsnNm_BR.setInputProtected(true);
            abMsg.tocNm_A.setInputProtected(true);
            abMsg.svcContrRefCmntTxt_A.setInputProtected(true);
            abMsg.dsContrRptGrpDescTxt_A.setInputProtected(true);
            abMsg.dsContrNm_A.setInputProtected(true);
            abMsg.contrInvCmntTxt_A.setInputProtected(true);
            abMsg.mtrEstTpDescTxt_A.setInputProtected(true);
            abMsg.dsAcctNm_LS.setInputProtected(true);
            abMsg.baseChrgToLeaseCmpyFlg_A.setInputProtected(true);
            abMsg.usgChrgToLeaseCmpyFlg_A.setInputProtected(true);
            abMsg.cfsLeaseNumCmntTxt_A.setInputProtected(true);
            abMsg.custPoNum_A.setInputProtected(true);
            abMsg.crCardCustRefNum_A.setInputProtected(true);
            abMsg.pmtTermCashDiscDescTxt_A.setInputProtected(true);
            abMsg.contrAutoRnwTpDescTxt_A.setInputProtected(true);
            abMsg.rnwPrcMethDescTxt_A.setInputProtected(true);
            abMsg.basePrcUpRatio_A.setInputProtected(true);
            abMsg.mtrPrcUpRatio_A.setInputProtected(true);
            abMsg.befEndRnwDaysAot_A.setInputProtected(true);
            abMsg.contrUplftTpDescTxt_A.setInputProtected(true);
            abMsg.uplftPrcMethDescTxt_A.setInputProtected(true);
            abMsg.uplftBasePrcUpRatio_A.setInputProtected(true);
            abMsg.uplftMtrPrcUpRatio_A.setInputProtected(true);
            abMsg.uplftBefEndRnwDaysAot_A.setInputProtected(true);
            abMsg.svcMachTpDescTxt_A.setInputProtected(true);
//            abMsg.svcMachMstrPk_IB.setInputProtected(true);
//            abMsg.svcConfigMstrPk_A.setInputProtected(true);
            abMsg.mdseCd_A.setInputProtected(true);
            abMsg.mdseDescShortTxt_A.setInputProtected(true);
            abMsg.shipToCustCd_A.setInputProtected(true);
            abMsg.shipToLocNm_A.setInputProtected(true);
            abMsg.shipToCustLocAddr_A.setInputProtected(true);
            abMsg.svcBrCdDescTxt_A.setInputProtected(true);
            abMsg.mdseDescShortTxt_SA.setInputProtected(true);
            abMsg.mtrReadMethDescTxt_A.setInputProtected(true);
            abMsg.svcMemoRsnDescTxt_A.setInputProtected(true);
            abMsg.contrRnwTotCnt_A.setInputProtected(true);
            abMsg.bllgMtrBillToCustCd_A.setInputProtected(true);
            abMsg.billToLocNm_BS.setInputProtected(true);
            abMsg.billToCustLocAddr_A.setInputProtected(true);
            abMsg.xxPsnNm_BS.setInputProtected(true);
            abMsg.bllgCycleDescTxt_A.setInputProtected(true);
            abMsg.basePrcDealAmt_A.setInputProtected(true);
            abMsg.basePrcTermDealAmtRate_A.setInputProtected(true);
            abMsg.bllgTmgTpDescTxt_A.setInputProtected(true);
            abMsg.xxCntnrTxt_CD.setInputProtected(true);
            abMsg.xxCntnrTxt_BD.setInputProtected(true);
            // START 2022/03/22 [QC#59683, ADD]
            abMsg.dsInvTgtrTpDescTxt_A.setInputProtected(true);
            // END   2022/03/22 [QC#59683, ADD]
            
            abMsg.basePrcUpRatio_A.setAppFracDigit(2);
            abMsg.mtrPrcUpRatio_A.setAppFracDigit(2);
            abMsg.uplftBasePrcUpRatio_A.setAppFracDigit(2);
            abMsg.uplftMtrPrcUpRatio_A.setAppFracDigit(2);
            abMsg.basePrcDealAmt_A.setAppFracDigit(2);
            abMsg.basePrcTermDealAmtRate_A.setAppFracDigit(2);
            if(abMsg.xxCntnrTxt_CD != null && LAST_DAY_OF_A_MONTH.equals(abMsg.xxCntnrTxt_CD.getValue())) {
                abMsg.xxCntnrTxt_CD.setValue(LAST_DAY);
            }
            if(abMsg.xxCntnrTxt_BD != null && LAST_DAY_OF_A_MONTH.equals(abMsg.xxCntnrTxt_BD.getValue())) {
                abMsg.xxCntnrTxt_BD.setValue(LAST_DAY);
            }

            // END 2018/07/09 T.Wada [QC#26424,ADD]
        }
    }

    /**
     * initCommonButton
     * @param handler EZDCommonHandler
     */
    private static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_LBL.SAVE.getOrgNm(), BTN_LBL.SAVE.getGuardNm(), BTN_LBL.SAVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.SUBMIT.getOrgNm(), BTN_LBL.SUBMIT.getGuardNm(), BTN_LBL.SUBMIT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPLY.getOrgNm(), BTN_LBL.APPLY.getGuardNm(), BTN_LBL.APPLY.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPROVE.getOrgNm(), BTN_LBL.APPROVE.getGuardNm(), BTN_LBL.APPROVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.REJECT.getOrgNm(), BTN_LBL.REJECT.getGuardNm(), BTN_LBL.REJECT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DOWNLOAD.getOrgNm(), BTN_LBL.DOWNLOAD.getGuardNm(), BTN_LBL.DOWNLOAD.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DELETE.getOrgNm(), BTN_LBL.DELETE.getGuardNm(), BTN_LBL.DELETE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.CLEAR.getOrgNm(), BTN_LBL.CLEAR.getGuardNm(), BTN_LBL.CLEAR.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.RESET.getOrgNm(), BTN_LBL.RESET.getGuardNm(), BTN_LBL.RESET.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RETURN.getOrgNm(), BTN_LBL.RETURN.getGuardNm(), BTN_LBL.RETURN.getLblNm(), 1, null);

    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0620BMsg
     * @param tblId String
     */
    private static void setRowColors(NSAL0620BMsg scrnMsg, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField(tblId).get(scrnMsg);
            tblColor.setAlternateRowsBG(tblId, table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <pre>
     * The Research Event of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0620BMsg
     */
    public static final void resrchControlScreen(EZDCommonHandler handler, NSAL0620BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg, "A");
            controlScreenBottom(handler, scrnMsg, true);
        }
    }

    // add start 2016/05/09 CSA Defect#4093
    /**
     * inputCheckMondatory
     * @param scrnMsg NSAL0620BMsg
     */
    public static void inputCheckMondatory(NSAL0620BMsg scrnMsg) {

        boolean isError = true;
        if (hasValue(scrnMsg.dsContrNum_H)) {
            isError = false;
        } else if (hasValue(scrnMsg.dsContrCratDt_H1)) {
            isError = false;
        } else if (hasValue(scrnMsg.dsContrCratDt_H2)) {
            isError = false;
            // START 2022/10/13 M.Komatsu [QC#60078,MOD]
            // } else if (hasValue(scrnMsg.dsContrCatgCd_H)) {
        } else if (hasValue(scrnMsg.xxComnScrColValTxt_H1)) {
            // END 2022/10/13 M.Komatsu [QC#60078,MOD]
            isError = false;
        } else if (hasValue(scrnMsg.invSeptBaseUsgFlg_H)) {
            isError = false;
            // START 2022/10/13 M.Komatsu [QC#60078,MOD]
            // } else if (hasValue(scrnMsg.dsContrCtrlStsCd_H)) {
        } else if (hasValue(scrnMsg.xxComnScrColValTxt_H2)) {
            // END 2022/10/13 M.Komatsu [QC#60078,MOD]
            isError = false;
        } else if (hasValue(scrnMsg.xxChkBox_H1)) {
            isError = false;
        } else if (hasValue(scrnMsg.dsContrRptGrpNum_H)) {
            isError = false;
            // START 2022/10/13 M.Komatsu [QC#60078,MOD]
            // } else if (hasValue(scrnMsg.dsContrClsCd_H)) {
        } else if (hasValue(scrnMsg.xxComnScrColValTxt_H3)) {
            // END 2022/10/13 M.Komatsu [QC#60078,MOD]
            isError = false;
        } else if (hasValue(scrnMsg.svcContrRefCmntTxt_H)) {
            isError = false;
        } else if (hasValue(scrnMsg.dsAcctNm_H)) {
            isError = false;
        } else if (hasValue(scrnMsg.dsAcctNum_H)) {
            isError = false;
        } else if (hasValue(scrnMsg.xxGenlFldAreaTxt_H1)) {
            isError = false;
        } else if (hasValue(scrnMsg.billToCustCd_H1)) {
            isError = false;
        // add start 2016/07/01 CSA Defect#11261
//        } else if (hasValue(scrnMsg.svcPgmMdseCd_H)) {
        } else if (hasValue(scrnMsg.mdseDescShortTxt_H)) {
        // add end 2016/07/01 CSA Defect#11261
            isError = false;
        } else if (hasValue(scrnMsg.dsAcctNm_H3)) {
            isError = false;
        } else if (hasValue(scrnMsg.serNum_H)) {
            isError = false;
            // START 2022/10/13 M.Komatsu [QC#60078,MOD]
            // } else if (hasValue(scrnMsg.svcMachMstrStsCd_H)) {
        } else if (hasValue(scrnMsg.xxComnScrColValTxt_H4)) {
            // END 2022/10/13 M.Komatsu [QC#60078,MOD]
            isError = false;
            // START 2022/10/13 M.Komatsu [QC#60537,ADD]
        } else if (hasValue(scrnMsg.mdseCd_H)) {
            isError = false;
            // END 2022/10/13 M.Komatsu [QC#60537,ADD]
        // START 2019/08/30 [QC#53005,ADD]
        } else if (hasValue(scrnMsg.t_MdlNm_H)) {
            isError = false;
        // START 2019/08/30 [QC#53005,ADD]
        } else if (hasValue(scrnMsg.svcConfigMstrPk_H)) {
            isError = false;
        // END 2019/08/30 [QC#53005,ADD]
        } else if (hasValue(scrnMsg.dsAcctNm_H2)) {
            isError = false;
        } else if (hasValue(scrnMsg.xxGenlFldAreaTxt_H2)) {
            isError = false;
        } else if (hasValue(scrnMsg.locNum_H)) {
            isError = false;
        // START 2018/09/05 K.Kojima [QC#28061,MOD]
        // } else if (hasValue(scrnMsg.xxRadioBtn_H1)) {
        //     isError = false;
        // } else if (hasValue(scrnMsg.xxFromDt_H)) {
        //     isError = false;
        // } else if (hasValue(scrnMsg.xxThruDt_H)) {
        //     isError = false;
        } else if (hasValue(scrnMsg.xxRadioBtn_H1) && hasValue(scrnMsg.xxFromDt_H)) {
            isError = false;
        } else if (hasValue(scrnMsg.xxRadioBtn_H1) && hasValue(scrnMsg.xxThruDt_H)) {
            isError = false;
        // END 2018/09/05 K.Kojima [QC#28061,MOD]
            // START 2022/10/13 M.Komatsu [QC#60078,MOD]
            // } else if (hasValue(scrnMsg.bllgCycleCd_HB)) {
        } else if (hasValue(scrnMsg.xxComnScrColValTxt_H5)) {
            // END 2022/10/13 M.Komatsu [QC#60078,MOD]
            isError = false;
            // START 2022/10/13 M.Komatsu [QC#60078,MOD]
            // } else if (hasValue(scrnMsg.bllgCycleCd_HM)) {
        } else if (hasValue(scrnMsg.xxComnScrColValTxt_H6)) {
            // END 2022/10/13 M.Komatsu [QC#60078,MOD]
            isError = false;
        } else if (hasValue(scrnMsg.baseChrgToLeaseCmpyFlg_H)) {
            isError = false;
        } else if (hasValue(scrnMsg.usgChrgToLeaseCmpyFlg_H)) {
            isError = false;
        // START 2018/12/25 K.Morita [QC#28749,MOD]
        } else if (hasValue(scrnMsg.xxRadioBtn_H2) && (scrnMsg.xxRadioBtn_H2.getValue().compareTo(BigDecimal.valueOf(4)) != 0)) {        
            isError = false;
        // END 2018/12/25 K.Morita [QC#28749,MOD]
        } else if (hasValue(scrnMsg.xxChkBox_H2)) {
            isError = false;
        // START 2018/02/06 M.Kidokoro [QC#23375,MOD]
//        }
        } else if (hasValue(scrnMsg.xxChkBox_H3)) {
            isError = false;
        // START 2018/11/05 K.Fujimoto [QC#28627,MOD]
        //}
        } else if (hasValue(scrnMsg.contrLinkNum_H)){
        	isError = false;
        }
        // END  2018/11/05 K.Fujimoto [QC#28627,MOD]
        // END 2018/02/06 M.Kidokoro [QC#23375,MOD]

        if (isError) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                if (hasValue(scrnMsg.B.no(i).xxChkBox_B)) {
                    isError = false;
                    break;
                }
            }
        }
        if (isError) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                if (hasValue(scrnMsg.C.no(i).xxChkBox_C)) {
                    isError = false;
                    break;
                }
            }
        }
        if (isError) {
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                if (hasValue(scrnMsg.D.no(i).xxChkBox_D)) {
                    isError = false;
                    break;
                }
            }
        }
        if (isError) {
            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                if (hasValue(scrnMsg.E.no(i).xxChkBox_E)) {
                    isError = false;
                    break;
                }
            }
        }

        if (isError) {
            scrnMsg.setMessageInfo(NSAM0017E);
        }
    }
    // add end 2016/05/09 CSA Defect#4093
    // add start 2016/07/01 CSA Defect#11261
    /**
     * setSvcPgmCommonPopUpParam
     * @param scrnMsg NSAL0620BMsg
     * @param glblCmpyCd String
     * @param mdseNm String
     * @return Object[]
     */
    public static Object[] setSvcPgmCommonPopUpParam(NSAL0620BMsg scrnMsg, String glblCmpyCd, String mdseNm) {
        scrnMsg.xxScrEventNm.setValue("OpenWin_ServiceProgram");
        ZYPTableUtil.clear(scrnMsg.R);
        Object[] params = new Object[7];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        //  START 2016/09/23 Y.Zhang [QC#12582, MOD]
        // Window title
        params[i++] = "Service Program Item Popup";
        //  END 2016/09/23 Y.Zhang [QC#12582, MOD]
        // Table SQL
        params[i++] = getSvcPgmSelectSQL(scrnMsg, glblCmpyCd);
        // Where List
        params[i++] = getSvcPgmWhereList(scrnMsg, mdseNm);
        // Column List
        params[i++] = getSvcPgmColumnList();
        // Sort Condition List
        params[i++] = getSvcPgmSortConditionList();
        // outPut List
        params[i++] = scrnMsg.R;

        return params;
    }

    private static String getSvcPgmSelectSQL(NSAL0620BMsg scrnMsg, String glblCmpyCd) {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT");
        sb.append("      A.GLBL_CMPY_CD");
        sb.append("     ,A.EZCANCELFLAG");
        sb.append("     ,A.MDSE_CD");
        sb.append("     ,A.MDSE_DESC_SHORT_TXT");
        sb.append("     ,B.SVC_COV_TMPL_TP_SORT_NUM");
        sb.append(" FROM");
        sb.append("      MDSE             A");
        sb.append("     ,SVC_COV_TMPL_TP  B");
        sb.append(" WHERE");
        sb.append("         A.GLBL_CMPY_CD       = '#glblCmpyCd#'");
        sb.append("     AND A.EZCANCELFLAG       = '0'");
        sb.append("     AND A.GLBL_CMPY_CD       = B.GLBL_CMPY_CD");
        sb.append("     AND A.SVC_COV_TMPL_TP_CD = B.SVC_COV_TMPL_TP_CD");
        sb.append("     AND B.EZCANCELFLAG       = '0'");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        return sql;
    }

    private static List<Object[]> getSvcPgmWhereList(NSAL0620BMsg scrnMsg, String mdseNm) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        // START  2016/09/23 Y.Zhang [QC#12582, MOD]
        h0[WLIST_DSP_OBJ_NM] = "Svc Program Item Cd";
        // END  2016/09/23 Y.Zhang [QC#12582, MOD]
        h0[WLIST_OBJ_ID] = "MDSE_CD";
        h0[WLIST_OBJ_VALUE] = null;
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        // START  2016/09/23 Y.Zhang [QC#12582, MOD]
        h1[WLIST_DSP_OBJ_NM] = "Svc Program Item Nm";
        // END  2016/09/23 Y.Zhang [QC#12582, MOD]
        h1[WLIST_OBJ_ID] = "MDSE_DESC_SHORT_TXT";
        h1[WLIST_OBJ_VALUE] = mdseNm;
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    private static List<Object[]> getSvcPgmColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        // START  2016/09/23 Y.Zhang [QC#12582, MOD]
        c0[CLIST_DSP_OBJ_NM] = "Svc Program Item Cd";
        // END  2016/09/23 Y.Zhang [QC#12582, MOD]
        c0[CLIST_OBJ_ID] = "MDSE_CD";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(LEN_16);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        // START  2016/09/23 Y.Zhang [QC#12582, MOD]
        c1[CLIST_DSP_OBJ_NM] = "Svc Program Item Nm";
        // END  2016/09/23 Y.Zhang [QC#12582, MOD]
        c1[CLIST_OBJ_ID] = "MDSE_DESC_SHORT_TXT";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(LEN_50);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        return columnList;
    }

    private static List<Object[]> getSvcPgmSortConditionList() {

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortObj1 = {"SVC_COV_TMPL_TP_SORT_NUM", "ASC" };
        Object[] sortObj2 = {"MDSE_CD", "ASC" };
        sortList.add(sortObj1);
        sortList.add(sortObj2);
        return sortList;
    }
    // add end 2016/07/01 CSA Defect#11261
}
